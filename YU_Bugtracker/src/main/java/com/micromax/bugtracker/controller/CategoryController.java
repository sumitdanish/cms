package com.micromax.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.ProdVersion;
import com.micromax.bugtracker.service.CategoryService;



@Controller
@RequestMapping("/bugforum/category")
public class CategoryController{
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping (value="/categories/{ca}",method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Category>> getCategories(@PathVariable("ca") String type) throws Exception{
		return new ResponseEntity<List<Category>>(categoryService.getCategoryList(type), HttpStatus.OK);
	}
	
	@RequestMapping (value="/addcategory",method=RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Category> addCategory(@RequestBody Category category) throws Exception{
		System.out.println("Category : "+category.getCategoryType());
		if(category != null){
			categoryService.addCategory(category);
		}
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}	
	@RequestMapping (value="/prodversion/{catId}",method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ProdVersion>> getProdVersion(@PathVariable("catId") Integer catId) throws Exception{
		return new ResponseEntity<List<ProdVersion>>(categoryService.getProdVersion(catId),HttpStatus.OK);
	}
	
	
}
