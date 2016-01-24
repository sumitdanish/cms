package com.micromax.bugtracker.util;

import java.util.Properties;

public class CommonUtils {

	
	private volatile static CommonUtils commonUtils;
	private String value;
	private Properties properties;
	private CommonUtils(){
		init();
	}
	
	
	public static synchronized CommonUtils getCommonUtils(){
		try{
			if(commonUtils == null){
				synchronized (CommonUtils.class) {
					if(commonUtils == null){
						commonUtils = new CommonUtils();
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return commonUtils;
	}
	
	
	
	private void init(){
		try{
			properties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        	properties.load(classLoader.getResourceAsStream("key.properties"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public String getValue(String key){
		return properties.get(key).toString();
	}
	
}
