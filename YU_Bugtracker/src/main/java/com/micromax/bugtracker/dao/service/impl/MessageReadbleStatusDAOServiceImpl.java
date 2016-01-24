package com.micromax.bugtracker.dao.service.impl;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.MessageReadbleStatus;
import com.micromax.bugtracker.dao.service.MessageReadbleStatusDAOService;

@Repository("messageReadble")
public class MessageReadbleStatusDAOServiceImpl implements MessageReadbleStatusDAOService{

	public JSONObject updateMessageStatus(
			JSONObject messageReadbleStatus) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCountOfUnreadbleMessage() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
