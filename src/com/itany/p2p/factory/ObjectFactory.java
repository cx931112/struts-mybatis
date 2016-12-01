package com.itany.p2p.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.itany.p2p.util.MybatisUtil;



public class ObjectFactory {
	
	private static Map<String,Object> objects=new HashMap<String, Object>();
	
	static{
		BufferedReader br=null;
		try {
			br=new BufferedReader(
					new InputStreamReader(
							ObjectFactory.class
										 .getClassLoader()
										 .getResourceAsStream("objects-mybatis.txt")));
			
			String s=null;
			
			while((s=br.readLine())!=null){
				String[] entry=s.split("=");
				if("mybatisDao".equals(entry[0])){
					
					String daoPackage=entry[1];
					
					daoPackage=daoPackage.replace(".", "/");
					
					URL url=ObjectFactory.class
										 .getClassLoader()
										 .getResource(daoPackage);
//					System.out.println(entry[1]);
//					System.out.println(url);
					
					String urlStr=URLDecoder.decode(url.toString(), "utf-8");
					
					
//					System.out.println(urlStr);
					
//					String dirStr=urlStr.substring("file:/".length());
//					System.out.println(dirStr);
					
					File dir=new File(new URL(urlStr).toURI());
					
					//File dir=new File(dirStr);
					
					String[] fileNames=dir.list();
					
					for (String fileName : fileNames) {
//						System.out.println(fileName);
						if(fileName.endsWith(".class")){
							String daoClassName=entry[1]+"."+fileName.substring(0,fileName.lastIndexOf(".class"));
							Class daoClass=Class.forName(daoClassName);
							
							String key=daoClass.getSimpleName();
							
							String firstStr=key.charAt(0)+"";
							
							key=firstStr.toLowerCase()+key.substring(1);
							
							SqlSession session=MybatisUtil.getSession();
							Object dao=session.getMapper(daoClass);
							
							objects.put(key, dao);
						}
					}
					
					continue;
				}
				objects.put(entry[0], Class.forName(entry[1]).newInstance());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化错误");
		} finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static Object getObject(String key){
		return objects.get(key);
	}
	
	public static void main(String[] args) {
	}
	
	
}
