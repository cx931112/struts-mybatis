package com.itany.p2p.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 文 件 名: JsonUtil.java
 * 版 权: Copyright 2014-, All rights reserved
 * 描 述: <描述>
 * 创 建 人:cuiyi@itany.com 崔译
 * 版本：V1.0.0
 */
public class JsonUtil
{
    
    public static Object toBean(String json, Class beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject, beanClass);
        return bean;
        
    }
    
    public static String toJSON(Object bean)
    {
        JSONObject jsonObject = JSONObject.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }

    public static List toList(String json, Class beanClass)
    {
        JSONArray jsonObject = JSONArray.fromObject(json);
        List bean = JSONArray.toList(jsonObject, beanClass);
        return bean;
        
    }
    
    
    public static String toJSON(List bean)
    {
        JSONArray jsonObject = JSONArray.fromObject(bean);
        String json = jsonObject.toString();
        return json;
    }
    
   
    
   
    
}
