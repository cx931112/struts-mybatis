package com.itany.p2p.service.impl;

import java.util.List;

import com.itany.p2p.dao.UserDao;
import com.itany.p2p.entity.User;
import com.itany.p2p.factory.ObjectFactory;
import com.itany.p2p.service.UserService;
import com.itany.p2p.transaction.ITransaction;

public class UserServiceImpl implements	UserService{
	private static ITransaction transAction=(ITransaction) ObjectFactory.getObject("transAction");
	private	UserDao	userDao=(UserDao)ObjectFactory.getObject("userDao");
	public List<User> getAllUserName() {
		// TODO Auto-generated method stub
		List<User>	users=userDao.getAllUserName();
		return	users;
		
	}
	public void regist(User user) {
		// TODO Auto-generated method stub
		try{
		transAction.beginTransaction();
		userDao.regist(user);
		System.out.println(user.getUserName());
		transAction.commit();
		
		}catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
		
		
	}
	public int loginCheck(User user) {
		// TODO Auto-generated method stub
		int	count=userDao.loginCheck(user);
		return	count;
	}
	public User toLogin(User user) {
		// TODO Auto-generated method stub
		User	userGet=userDao.toLogin(user);
		return	userGet;
	}
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User>	users=userDao.getAllUser();
		return	users;
	}
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		try{
		transAction.beginTransaction();
		userDao.deleteUser(user);
		transAction.commit();
		
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
			
		}
	}
	public int checkAdmin(User user) {
		// TODO Auto-generated method stub
		int	count=userDao.checkAdmin(user);
		
		return	count;
	}
	public void adminAdd(User user) {
		// TODO Auto-generated method stub
		try	{
			transAction.beginTransaction();
			userDao.adminAdd(user);
			transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
		}
	public void changeStatus(User user) {
		// TODO Auto-generated method stub
		try{
			transAction.beginTransaction();
			userDao.changeStatus(user);
			transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
	}
	public User selcetPassByName(User user) {
		// TODO Auto-generated method stub
		User	userGet=userDao.selectPassByName(user);
		return	userGet;
	}
	public void passChange(User user) {
		// TODO Auto-generated method stub
		try{
			transAction.beginTransaction();
			userDao.passChange(user);
			transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
	}
	
}
