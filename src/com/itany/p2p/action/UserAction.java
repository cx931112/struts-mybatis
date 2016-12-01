package com.itany.p2p.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.alibaba.fastjson.JSON;
import com.itany.p2p.entity.User;
import com.itany.p2p.factory.ObjectFactory;
import com.itany.p2p.service.UserService;

public class UserAction extends HttpServlet {

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
	private	UserService	userService=(UserService)ObjectFactory.getObject("userService");
	public void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<User>	users=userService.getAllUserName();
		String	username=request.getParameter("username");
		int	count=0;
			for(User	user:users){
				if(user.getUserName().equals(username)){
					count++;
				}

			}
			if(count==0){
				String	json="{\"result\":true}";
				out.print(json);
			}
			else{
				String	json="{\"result\":false}";
				out.print(json);
			}
			
		
	
	}
	public	String	regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String	username=request.getParameter("username");
		
		String	password=request.getParameter("password");
		String	email=request.getParameter("email");
		String	identity=request.getParameter("identity");
		User	user=new	User();
		user.setUserName(username);
		user.setPassWord(password);
		user.setEmail(email);
		user.setIdentity(identity);
		userService.regist(user);
		return	"success";
		
		
	}
	public	void	loginCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String	username=request.getParameter("username");
		String	password=request.getParameter("password");
		User	user=new	User();
		user.setUserName(username);
		user.setPassWord(password);
		int	count=userService.loginCheck(user);
		if(count==0){
			String	json="{\"result\":false}";
			out.print(json);
			
		}
		else{
			String	json="{\"result\":true}";
			out.print(json);
		}
		
	}
	public	String	toLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String	username=request.getParameter("username");
		String	password=request.getParameter("password");
		User	user=new	User();
		user.setUserName(username);
		user.setPassWord(password);
		User	userGet=userService.toLogin(user);
		if(userGet.getStatus()==0){
			request.getSession().setAttribute("userName", username);
			return	"fail";
		}
		else{
			request.getSession().setAttribute("userName", username);
			
			return	"success";
		}
		
		
	}
	public	String	getAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List<User>	users=userService.getAllUser();
		request.setAttribute("users", users);
		return	"success";
	}
	public	void	deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int	id=Integer.valueOf(request.getParameter("id"));
		User	user=new	User();
		user.setId(id);
		userService.deleteUser(user);
		
	}
	public	void	checkAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter	out=response.getWriter();
		String	userName=request.getParameter("username");
		User	user=new	User();
		user.setUserName(userName);
		int	count=userService.checkAdmin(user);
		if(count==0){
			String	json="{\"result\":true}";
			out.print(json);
			
		}
		else{
			String	json="{\"result\":false}";
			out.print(json);
		}
	}
	public	String	adminAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String	userName=request.getParameter("username");
		String	passWord=request.getParameter("password");
		User	user=new	User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setStatus(1);
		userService.adminAdd(user);
		return	"success";
	}
	public	void	changeStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int	id=Integer.valueOf(request.getParameter("id"));
		User	user=new	User();
		user.setId(id);
		user.setStatus(1);
		userService.changeStatus(user);
		
	}
	public	void	selectUserByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter	out=response.getWriter();
		String loginName=request.getParameter("loginName");
		String oldPwd=request.getParameter("oldPwd");
		User	user=new	User();
		user.setUserName(loginName);
		User	userGet=userService.selcetPassByName(user);
		if(!userGet.getPassWord().equals(oldPwd)){
			String	json="{\"result\":false}";
			out.print(json);
		}
		else{
			String	json="{\"result\":true}";
			out.print(json);
		}
		
		
		
	}
	public	void	passChange(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter	out=response.getWriter();
		String loginName=request.getParameter("loginName");
		String newPwd=request.getParameter("newPwd");
		User	user=new	User();
		user.setUserName(loginName);
		user.setPassWord(newPwd);
		userService.passChange(user);
	}
	public	String	loginAgin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		return	"success";
	}

}
