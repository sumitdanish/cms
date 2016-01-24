package com.micromax.bugtracker.service.Impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.MessageReadbleStatus;
import com.micromax.bugtracker.dao.service.MessageReadbleStatusDAOService;
import com.micromax.bugtracker.service.MessageReadbleStatusService;


@Service
public class MessageReadbleStatusImpl implements MessageReadbleStatusService{

	@Autowired
	MessageReadbleStatusDAOService messageReadbleStatusDAOService;

	public JSONObject updateMessageStatus(
			JSONObject messageReadbleStatus) throws Exception {
		// TODO Auto-generated method stub
		return messageReadbleStatusDAOService.updateMessageStatus(messageReadbleStatus);
	}

	public int getCountOfUnreadbleMessage() throws Exception {
		// TODO Auto-generated method stub
		return messageReadbleStatusDAOService.getCountOfUnreadbleMessage();
	}
	
}
