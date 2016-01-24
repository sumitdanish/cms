package com.micromax.bugtracker.dao.service;

import java.util.HashMap;
import java.util.List;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.ProdVersion;

public interface CategoryDAOService {

	
	public Category addCategory(Category category) throws Exception;
	public List<Category> getCategories(String type) throws Exception;
	public List<ProdVersion> getProdVerion(Integer catId) throws Exception;
	
}
