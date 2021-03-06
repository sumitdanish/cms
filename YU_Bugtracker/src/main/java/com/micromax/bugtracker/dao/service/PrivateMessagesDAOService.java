package com.micromax.bugtracker.dao.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.micromax.bugtracker.PrivateMessages;

public interface PrivateMessagesDAOService {

	public JSONObject addPrivateMessages(JSONObject jsonObject) throws Exception;
	public List<PrivateMessages> getMyInbox(Integer id,String messageType) throws Exception;
	public PrivateMessages getMyMessages(Integer messagesId) throws Exception;
	public JSONObject updateMessageReadbleStatus(JSONObject jsonObject) throws Exception;
}
