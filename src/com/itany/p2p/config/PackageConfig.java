package com.itany.p2p.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 文 件 名: PackageConfig.java
 * 版 权: Copyright 2014-, All rights reserved
 * 描 述: <描述>
 * 创 建 人:cuiyi@itany.com 崔译
 * 版本：V1.0.0
 */
public class PackageConfig {
	
	private String name;
	
	private String namespace;
	
	private Map<String,ActionConfig> actions = new HashMap<String, ActionConfig>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void addAction(ActionConfig action){
		actions.put(action.getName(), action);
	}
	
	public ActionConfig getActionByKey(String key){
		return actions.get(key);
	}
}
