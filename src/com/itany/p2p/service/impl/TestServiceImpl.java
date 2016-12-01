package com.itany.p2p.service.impl;

import java.util.List;

import com.itany.p2p.dao.TestDao;
import com.itany.p2p.entity.Apply;
import com.itany.p2p.entity.Test;
import com.itany.p2p.factory.ObjectFactory;
import com.itany.p2p.service.TestService;
import com.itany.p2p.transaction.ITransaction;

public class TestServiceImpl	implements	TestService {
	private static ITransaction transAction=(ITransaction) ObjectFactory.getObject("transAction");
	private	TestDao	testDao=(TestDao)ObjectFactory.getObject("testDao");
	public List<Test> getAllTest() {
		// TODO Auto-generated method stub
		List<Test>	allTest=testDao.getAllTest();
		return	allTest;
	}
	public int getApply(Apply apply) {
		// TODO Auto-generated method stub
		int	count=testDao.getApply(apply);
		return	count;
	}
	public void apply(Apply apply) {
		// TODO Auto-generated method stub
		try{
			transAction.beginTransaction();
		testDao.apply(apply);
		transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
	}
	public List<Apply> getHaveApply(Apply apply) {
		// TODO Auto-generated method stub
		List<Apply>	applys=testDao.getHaveApply(apply);
		return	applys;
	}
	public void deleteTest(Test test) {
		// TODO Auto-generated method stub
		try{
			transAction.beginTransaction();
			testDao.deleteTest(test);
			transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
	}
	public int testCheck(Test test) {
		// TODO Auto-generated method stub
		int	count=testDao.testCheck(test);
		return	count;
	}
	public void testAdd(Test test) {
		// TODO Auto-generated method stub
		try{
			transAction.beginTransaction();
			testDao.testAdd(test);
			transAction.commit();
		}
		catch(Exception	e){
			e.printStackTrace();
			transAction.rollback();
		}
	}

}
