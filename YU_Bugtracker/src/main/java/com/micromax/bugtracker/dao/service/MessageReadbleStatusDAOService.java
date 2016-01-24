package com.micromax.bugtracker.dao.service;

import org.json.simple.JSONObject;

import com.micromax.bugtracker.MessageReadbleStatus;

public interface MessageReadbleStatusDAOService {

	public JSONObject updateMessageStatus(JSONObject messageReadbleStatus) throws Exception;
	public int getCountOfUnreadbleMessage() throws Exception;
	
}
