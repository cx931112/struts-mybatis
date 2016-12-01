package com.itany.p2p.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.p2p.entity.Apply;
import com.itany.p2p.entity.Test;
import com.itany.p2p.factory.ObjectFactory;
import com.itany.p2p.service.TestService;

public class TestAction extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	private	TestService	testService=(TestService)ObjectFactory.getObject("testService");
	public String	getAllTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			List<Test>	allTest=testService.getAllTest();
			request.setAttribute("allTest",allTest);
			return	"success";
			
	}
	public	void	applyCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			String	userName=request.getParameter("userName");
			String	testName=request.getParameter("testName");
			PrintWriter	out=response.getWriter();
			Apply	apply=new	Apply();
			apply.setTestName(testName);
			apply.setUserName(userName);
			int	count=testService.getApply(apply);
			if(count==0){
				testService.apply(apply);
				String	json="{\"result\":false}";
				out.print(json);
			}
			else{
				String	json="{\"result\":true}";
				out.print(json);
			}
		
	}
	public	String	getHaveApplyTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String	userName=(String)request.getSession().getAttribute("userName");
		Apply	apply=new	Apply();
		apply.setUserName(userName);
		List<Apply>	applys=testService.getHaveApply(apply);
		request.setAttribute("applys", applys);
		return	"success";
		
	}
	public	void	deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Test	test=new	Test();
		int	id=Integer.valueOf(request.getParameter("id"));
		test.setId(id);
		testService.deleteTest(test);
		
	}
	public	void	testCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter	out=response.getWriter();
		Test	test=new	Test();
		String	testName=request.getParameter("testName");
		test.setTestName(testName);
		int	count=testService.testCheck(test);
		if(count==0){
			String	json="{\"result\":true}";
			out.print(json);
		}
		else{
			String	json="{\"result\":false}";
			out.print(json);
		}
		
	}
	public	String	testAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
	String	testName=request.getParameter("testName");
	String	testStart=request.getParameter("testStart");
	String	testEnd=request.getParameter("testEnd");
	Test	test=new	Test();
	test.setTestName(testName);
	test.setTestStart(testStart);
	test.setTestEnd(testEnd);
	System.out.println(test.getTestName());
	testService.testAdd(test);
	return	"success";
	}
	

}
