package com.itany.p2p.mvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import com.itany.p2p.config.ActionConfig;
import com.itany.p2p.config.ActionMappingsConfig;
import com.itany.p2p.config.IncludeConfig;
import com.itany.p2p.config.PackageConfig;
import com.itany.p2p.config.ResultConfig;

/**
 * 
 * 文 件 名: CenterController.java
 * 版 权: Copyright 2014-, All rights reserved 
 * 描 述:
 * <中心控制器，用于解析请求转发， 模拟struts1配置解析 实现MVC分离> 
 * 创 建 人:cuiyi@itany.com 崔译 
 * 版本：V1.0.0
 */
public class CenterController extends HttpServlet {

	/**
	 * long CenterController.java
	 */
	private static final long serialVersionUID = 1L;

	// 保存struts-*.xml中相应配置，每个xml一个ActionMappingsConfig
	private List<ActionMappingsConfig> mappingConfigs = new ArrayList<ActionMappingsConfig>();

	// 单例模式，每个action只有一个对象，在第一次访问某个方法的时候生成，放入Map中
	private Map<String, Object> actions = new HashMap<String, Object>();

	// actionConfig缓存，通过nameSpace+/+name获取actionConfig
	private Map<String, ActionConfig> actionConfigs = new HashMap<String, ActionConfig>();

	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * 初始化方法，在服务器开启后执行一次， 获取struts-*.xml配置，解析成对象map
	 */
	@Override
	public void init() throws ServletException {
		logger.info("init CenterController...");
		// 加载struts-*.xml规则
		Digester digester = DigesterLoader
				.createDigester(CenterController.class.getClassLoader()
						.getResource("rule.xml"));
		ActionMappingsConfig mappingConfig = new ActionMappingsConfig();
		// 把mappingConfig压栈
		digester.push(mappingConfig);
		try {
			// 将struts.xml转换成mappingConfig对象
			digester.parse(CenterController.class.getClassLoader()
					.getResourceAsStream("struts.xml"));
			// 将对象存入list
			mappingConfigs.add(mappingConfig);
			// 获取struts.xml中所有include配置
			List<IncludeConfig> includes = mappingConfig.getIncludes();
			// 重复上面步骤，对于每个独立的xml文件，生成一个mappingConfig对象，放入mappingConfigs中
			if (includes.size() > 0) {
				for (int i = 0; i < includes.size(); i++) {
					String file = includes.get(i).getFile();
					digester.push(mappingConfig);
					digester.parse(CenterController.class.getClassLoader()
							.getResourceAsStream(file));
					mappingConfigs.add(mappingConfig);
				}
			}
		} catch (Exception e) {
			logger.error("执行CenterController中init()方法出错", e);
		}
	}

	/**
	 * 请求解析 根据xml文件中的配置，获取请求对应的类方法，并根据方法的返回值确定下一步操作
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		String nameSpace = "";
		try {
			name = request.getServletPath();
			logger.info("拦截到请求：" + name);
			// 截取nameSpace，命名空间
			nameSpace = name.substring(0, name.lastIndexOf("/"));
			// 截取.do之前的，即：action的name
			name = name.substring(name.lastIndexOf("/") + 1,
					name.lastIndexOf(".do"));
			// 通过nameSpace和name获取对应action 的配置
			ActionConfig actionConfig = getActionByKey(nameSpace, name);
			// Important！！！如果为空，则未做此配置（根据请求，未找到对应方法），返回404 error
			// 或者返回相应错误页面
			if (actionConfig == null) {
				response.sendError(404);
				logger.error("解析请求方法" + name + "出错 ,未找到对应action配置,请求url:"
						+ request.getContextPath() + request.getServletPath());
				return;
			}

			// 如果没有配置classPath，说明是页面跳转。
			if (actionConfig.getClassPath() == null) {
				request.getRequestDispatcher(actionConfig.getForward())
						.forward(request, response);
				return;
			}

			// 通过classPath从actions中获取对象，单例。Why is Object?
			Object action = actions.get(actionConfig.getClassPath());
			if (action == null) {
				String path = actionConfig.getClassPath();
				actions.put(path, action = (Class.forName(path).newInstance()));
			}

			// 获取action中的方法配置，执行该方法，为空 返回 404
			String method = actionConfig.getMethod();
			if (method == null || "".equals(method.trim())) {
				response.sendError(404);
				logger.error("解析请求方法" + name + "出错 ,未在 "
						+ actionConfig.getName() + "中找到对应method："
						+ actionConfig.getMethod() + "，请求url:"
						+ request.getContextPath() + request.getServletPath());
				return;
			}
			Method m = action.getClass().getMethod(method,
					HttpServletRequest.class, HttpServletResponse.class);
			String returnName = (String) m.invoke(action, request, response);

			// 根据返回的字符串确定相应的result配置
			ResultConfig resultConfig = actionConfig.getResultByKey(returnName);
			
			if(resultConfig == null || "".equals(resultConfig))
			{
				logger.error("解析请求方法" + name + "出错 ,未在 "
						+ actionConfig.getName() + "中找到对应result："
						+ returnName + "，请求url:"
						+ request.getContextPath() + request.getServletPath());
				return;
			}
			
			// 获取result中的返回路径
			String resultpath = resultConfig.getPath();
			// 获取返回类型
			String type = resultConfig.getType();
			// 根据返回的类型，确定执行的方法：
			// "" "dispatcher" ---> 转发
			// "json" ---> 返回的是json字符串
			// "redirect" ---> 重定向
			if ("".equals(type) || "dispatcher".equals(type) || null == type) {
				request.getRequestDispatcher(resultpath).forward(request,
						response);
			}

			if ("json".equalsIgnoreCase(type)) {
				return;
			}

			if ("redirect".equals(type)) {
				response.sendRedirect(request.getContextPath() + resultpath);
			}

		} catch (Exception e) {
			logger.error(
					"解析请求方法" + name + "出错 ,请求url:" + request.getContextPath()
							+ request.getServletPath(), e);
		}

	}

	/**
	 * 私有化方法 根据nameSpace和name从actionConfigs中获取对应的action配置
	 * 
	 * @param nameSpace
	 *            请求中的命名空间
	 * @param name
	 *            请求名
	 * @return 返回ActionConfig
	 */
	private ActionConfig getActionByKey(String nameSpace, String name) {
		try {
			ActionConfig acf = actionConfigs.get(nameSpace + "/" + name);
			if (acf != null) {
				return acf;
			}
			for (int i = 0; i < mappingConfigs.size(); i++) {
				ActionMappingsConfig config = mappingConfigs.get(i);
				PackageConfig pc = config.getPackageByKey(nameSpace);
				if (pc != null) {
					acf = pc.getActionByKey(name);
					if (acf != null) {
						actionConfigs.put(nameSpace + "/" + name, acf);
					}
					return acf;
				}
			}
		} catch (Exception e) {
			logger.error("获取ActionConfig时产生异常：\nnameSpace:" + nameSpace
					+ "\nname:" + name, e);
		}
		return null;
	}
}
