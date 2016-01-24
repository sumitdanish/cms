package com.micromax.bugtracker.service;

import org.json.simple.JSONObject;

public interface FollowService {
	public JSONObject followUserIssue(JSONObject jsonObject) throws Exception;
}
