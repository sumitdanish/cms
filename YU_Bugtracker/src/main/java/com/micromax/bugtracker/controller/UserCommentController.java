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

import com.micromax.bugtracker.UserComment;
import com.micromax.bugtracker.service.UserCommentService;

@Controller
@RequestMapping("/bugforum/postComment")
public class UserCommentController {

	@Autowired
	UserCommentService userCommentService;
	
	@RequestMapping (value="/comment",method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> reportToModerator(@RequestBody JSONObject jsonObject) throws Exception{
		return new ResponseEntity<JSONObject>(userCommentService.addUserComment(jsonObject),HttpStatus.OK);
	}
	
	@RequestMapping (value="/get_comment/{issue_id}",method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<JSONObject>> getCommentList(@PathVariable ("issue_id") Integer id) throws Exception{
		return new ResponseEntity<List<JSONObject>>(userCommentService.getUSerCommentList(id),HttpStatus.OK);
	}
	
	@RequestMapping (value="/edit",method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> editMyIssueTitle(@RequestBody JSONObject jsonObject) throws Exception{
		return new ResponseEntity<JSONObject>(userCommentService.editMyIssueTitle(jsonObject),HttpStatus.OK);
	}
}
