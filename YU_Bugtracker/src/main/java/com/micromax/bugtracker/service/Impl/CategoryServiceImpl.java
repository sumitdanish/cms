package com.micromax.bugtracker.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.ProdVersion;

import com.micromax.bugtracker.dao.service.CategoryDAOService;
import com.micromax.bugtracker.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	CategoryDAOService categoryDAOService;
	
	public Category addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("category in : "+category.getCategoryType());
		return categoryDAOService.addCategory(category);
	}

	public List<Category> getCategoryList(String type) throws Exception {
		// TODO Auto-generated method stub
		return categoryDAOService.getCategories(type);
	}

	public Category getCategory(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProdVersion> getProdVersion(Integer catId) throws Exception {
		// TODO Auto-generated method stub
		return categoryDAOService.getProdVerion(catId);
	}

}
