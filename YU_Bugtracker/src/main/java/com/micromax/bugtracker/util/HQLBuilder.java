package com.micromax.bugtracker.util;

public class HQLBuilder {

	
	public String HQLBuilderCatalog(int id){
		String hqlBuilder = null;
		StringBuilder sb = new StringBuilder();
		try{
			sb = sb.append("From Catalog").append(" ").append("where");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return hqlBuilder;
	}
	
	
}
