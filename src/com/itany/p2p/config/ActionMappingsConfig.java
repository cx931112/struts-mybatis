package com.itany.p2p.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 文 件 名: ActionMappingsConfig.java
 * 版 权: Copyright 2014-, All rights reserved
 * 描 述: <描述>
 * 创 建 人:cuiyi@itany.com 崔译
 * 版本：V1.0.0
 */
public class ActionMappingsConfig {
	
	private Map<String, PackageConfig> packages = new HashMap<String, PackageConfig>();
	
	
	private List<IncludeConfig> includes = new ArrayList<IncludeConfig>();
	
	public void addPackage(PackageConfig packageConfig){
		packages.put(packageConfig.getNamespace(), packageConfig);
	}
	
	public PackageConfig getPackageByKey(String key){
		return packages.get(key);
	}
	
	public void addInclude(IncludeConfig include){
		includes.add(include);
	}
	
	public List<IncludeConfig> getIncludes(){
		return includes;
	}
}
