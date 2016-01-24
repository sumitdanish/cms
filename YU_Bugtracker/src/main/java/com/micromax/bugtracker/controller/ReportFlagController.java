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

import com.micromax.bugtracker.service.ReportFlagService;


@Controller
@RequestMapping ("bugforum/re")
public class ReportFlagController {

	
	@Autowired
	ReportFlagService reportFlagService;
	@RequestMapping (value="/flag",method = RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE},consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> reportToModerator(@RequestBody JSONObject jsonObject) throws Exception{
		return new ResponseEntity<JSONObject>(reportFlagService.reportToModerator(jsonObject),HttpStatus.OK);
	}
}
