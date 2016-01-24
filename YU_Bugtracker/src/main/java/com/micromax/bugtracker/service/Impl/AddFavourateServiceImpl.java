package com.micromax.bugtracker.service.Impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.dao.service.AddFavourateDAOService;
import com.micromax.bugtracker.service.AddFavourateService;



@Service
public class AddFavourateServiceImpl implements AddFavourateService{

	
	@Autowired
	AddFavourateDAOService addFavourateDAOService;
	public JSONObject addFavoraite(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return addFavourateDAOService.addFavoraite(jsonObject);
	}

}
