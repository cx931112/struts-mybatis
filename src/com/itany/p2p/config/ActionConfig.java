package com.itany.p2p.config;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 文 件 名: ActionConfig.java
 * 版 权: Copyright 2014-, All rights reserved
 * 描 述: <描述>
 * 创 建 人:cuiyi@itany.com 崔译
 * 版本：V1.0.0
 */
public class ActionConfig {
	
	private String name;
	
	private String classPath;
	
	private String method;
	
	private String forward;
	
	private Map<String,ResultConfig> results = new HashMap<String, ResultConfig>();
	
	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ResultConfig getResultByKey(String key) {
		return results.get(key);
	}

	public void addResult(ResultConfig result) {
		results.put(result.getName(), result);
	}
	
	
}
