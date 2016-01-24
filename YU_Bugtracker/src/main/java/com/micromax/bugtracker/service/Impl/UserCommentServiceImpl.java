package com.micromax.bugtracker.service.Impl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.UserComment;
import com.micromax.bugtracker.dao.service.UserCommentDAOService;
import com.micromax.bugtracker.service.UserCommentService;


@Service
public class UserCommentServiceImpl implements UserCommentService{

	
	@Autowired
	UserCommentDAOService userCommentDAOService;
	
	public JSONObject addUserComment(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return userCommentDAOService.addUserComment(jsonObject);
	}

	public JSONObject editMyIssueTitle(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return userCommentDAOService.editMyIssueTitle(jsonObject);
	}

	public List<JSONObject>  getUSerCommentList(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userCommentDAOService.getUSerCommentList(id);
	}

}
