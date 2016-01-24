package com.micromax.bugtracker.service.Impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.dao.service.FollowDAOService;
import com.micromax.bugtracker.service.FollowService;


@Service
public class FollowServiceImpl implements FollowService{
	
	
	@Autowired
	FollowDAOService fallowDAOService;
	public JSONObject followUserIssue(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return fallowDAOService.followUserIssue(jsonObject);
	}

}
