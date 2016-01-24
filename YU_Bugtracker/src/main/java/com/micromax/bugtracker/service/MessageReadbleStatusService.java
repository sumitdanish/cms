package com.micromax.bugtracker.service;

import org.json.simple.JSONObject;

import com.micromax.bugtracker.MessageReadbleStatus;

public interface MessageReadbleStatusService {

	public JSONObject updateMessageStatus(JSONObject messageReadbleStatus) throws Exception;
	public int getCountOfUnreadbleMessage() throws Exception;
	
}
