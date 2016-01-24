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

import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.service.IssueService;


@Controller
@RequestMapping("/bugforum/issue")
public class IssueController {

	
	@Autowired 
	IssueService issueService;
	
	@RequestMapping (value = "/topTrendingBug",method = RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Issue>> getTopTrendingBugs() throws Exception{
		return new ResponseEntity<List<Issue>>(issueService.getTopTrendingBugs(),HttpStatus.OK);
	}
	
	@RequestMapping (value = "/savedetails",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> saveDetails(@RequestBody JSONObject bugDetails) throws Exception{
		return new ResponseEntity<JSONObject>(issueService.saveBugDetail(bugDetails),HttpStatus.OK);
	}
	
	@RequestMapping (value="/openissue",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JSONObject> openIssueAgain(@RequestBody JSONObject jsonObject) throws Exception{
		//System.out.println(jsonObject);
		return new ResponseEntity<JSONObject>(issueService.openIssueAgain(jsonObject),HttpStatus.OK);
	}
	
	@RequestMapping (value="/searchissue",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Issue>> getSearchIssueList() throws Exception{
		return new ResponseEntity<List<Issue>>(issueService.getSearchIssueList(),HttpStatus.OK);
	}
	
	@RequestMapping (value="/searchTitle/{title}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Issue>> getSearchByTitleList(@PathVariable ("title") String title) throws Exception{
		return new ResponseEntity<List<Issue>>(issueService.getSearchByTitleList(title),HttpStatus.OK);
	}
	
	@RequestMapping (value="/userIssue/{issue_id}")
	public ResponseEntity<List<JSONObject>> getSearchByTitleList(@PathVariable ("issue_id") Integer id) throws Exception{
		return new ResponseEntity<List<JSONObject>>(issueService.getIssueDetails(id),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
