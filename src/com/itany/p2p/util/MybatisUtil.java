package com.itany.p2p.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.itany.p2p.exception.DataAccessException;



public class MybatisUtil {
	private static SqlSessionFactory sf;
	private static ThreadLocal<SqlSession> threadLocal;
	
	static{
		try {
			threadLocal=new ThreadLocal<SqlSession>();
			sf=new SqlSessionFactoryBuilder()
				.build(MybatisUtil.class
								  .getClassLoader()
								  .getResourceAsStream("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("MybatisUtil初始化错误");
		}
	}
	
	public static SqlSession getSession(){
		SqlSession session=threadLocal.get();
		if(session==null){
			try {
				session=sf.openSession();
				threadLocal.set(session);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataAccessException("数据访问失败",e);
			}
		}
		return session;
	}
	
	public static void closeSession(){
		SqlSession session=threadLocal.get();
		try {
			if(session!=null){
				threadLocal.remove();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
