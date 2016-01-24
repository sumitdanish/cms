package com.micromax.bugtracker.service.Impl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.PrivateMessages;
import com.micromax.bugtracker.dao.service.PrivateMessagesDAOService;
import com.micromax.bugtracker.service.PrivateMessagesService;

@Service
public class PrivateMessagesServiceImpl implements PrivateMessagesService{

	
	@Autowired
	PrivateMessagesDAOService privateMessagesDAOService;

	public JSONObject addPrivateMessages(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return privateMessagesDAOService.addPrivateMessages(jsonObject);
	}

	public List<PrivateMessages> getMyInbox(Integer id,String messageType) throws Exception {
		// TODO Auto-generated method stub
		return privateMessagesDAOService.getMyInbox(id,messageType);
	}

	public PrivateMessages getMyMessages(Integer messagesId) throws Exception {
		// TODO Auto-generated method stub
		return privateMessagesDAOService.getMyMessages(messagesId);
	}

	public JSONObject updateMessageReadbleStatus(JSONObject jsonObject)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
