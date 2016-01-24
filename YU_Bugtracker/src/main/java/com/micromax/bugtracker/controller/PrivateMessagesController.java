package com.micromax.bugtracker.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micromax.bugtracker.PrivateMessages;
import com.micromax.bugtracker.service.PrivateMessagesService;



@Controller
@RequestMapping ("bugforum/private")
public class PrivateMessagesController {

	@Autowired
	PrivateMessagesService privateMessagesService;
	
	@RequestMapping (value = "/messages",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> sendMessage(@RequestBody JSONObject jsonObject) throws Exception{
		return new ResponseEntity<JSONObject>(privateMessagesService.addPrivateMessages(jsonObject),HttpStatus.OK);
	}
	
	@RequestMapping (value = "/myInbox/{message_type}/{user_id_or_message_id}",method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<PrivateMessages>> getMyInbox(@PathVariable ("message_type") String messageType, 
															@PathVariable ("user_id_or_message_id") Integer id) throws Exception{
		return new ResponseEntity<List<PrivateMessages>>(privateMessagesService.getMyInbox(id,messageType),HttpStatus.OK);
	}
	
}
