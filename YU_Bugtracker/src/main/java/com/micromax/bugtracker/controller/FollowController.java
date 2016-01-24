package com.micromax.bugtracker.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micromax.bugtracker.service.FollowService;



@Controller
@RequestMapping("bugforum/follow")
public class FollowController {

	@Autowired
	FollowService followService;
	
	@RequestMapping (value="/issue",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> followUserIssue(@RequestBody JSONObject jsonObject) throws Exception{
		return new ResponseEntity<JSONObject>(followService.followUserIssue(jsonObject),HttpStatus.OK);
	}
	
	
}
