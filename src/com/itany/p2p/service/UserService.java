package com.itany.p2p.service;

import java.util.List;

import com.itany.p2p.entity.User;

public interface UserService {
	public	List<User>	getAllUserName();
	public	void	regist(User	user);
	public	int	loginCheck(User	user);
	public	User	toLogin(User	user);
	public	List<User>	getAllUser();
	public	void	deleteUser(User	user);
	public	int	checkAdmin(User	user);
	public	void	adminAdd(User	user);
	public	void	changeStatus(User	user);
	public	User	selcetPassByName(User	user);
	public	void	passChange(User	user);
}
