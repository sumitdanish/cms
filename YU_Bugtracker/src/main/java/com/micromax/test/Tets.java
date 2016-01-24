package com.micromax.test;

import com.micromax.bugtracker.service.Impl.CategoryServiceImpl;

public class Tets {

	public static void main(String[] args) throws Exception {
		CategoryServiceImpl c = new CategoryServiceImpl();
		System.out.println(c.getCategoryList("ca").size());

	}

}
