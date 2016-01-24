package com.micromax.bugtracker.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.micromax.bugtracker.UserComment;

public interface UserCommentService {

	
	public JSONObject addUserComment(JSONObject jsonObject) throws Exception ;
	public JSONObject editMyIssueTitle(JSONObject jsonObject) throws Exception;
	public List<JSONObject>  getUSerCommentList(Integer id) throws Exception;
	
}
