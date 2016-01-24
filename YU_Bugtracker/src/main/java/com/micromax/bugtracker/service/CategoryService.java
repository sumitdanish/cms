package com.micromax.bugtracker.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.ProdVersion;

public interface CategoryService {

	public Category addCategory(Category category) throws Exception;
	public List<Category> getCategoryList(String type) throws Exception;
	public List<ProdVersion> getProdVersion(Integer catId) throws Exception;
	public Category getCategory(String categoryName);
	
}
