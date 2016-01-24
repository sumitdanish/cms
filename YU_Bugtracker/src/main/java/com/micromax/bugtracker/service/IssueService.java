package com.micromax.bugtracker.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.micromax.bugtracker.Issue;

public interface IssueService {

	public List<Issue> getIssueTitles(String userId) throws Exception;
	public List<Issue> getTopTrendingBugs() throws Exception;
	public JSONObject saveBugDetail(JSONObject bugDetails) throws Exception;
	public JSONObject duplicateIssueDetails(Integer id) throws Exception;
	public JSONObject openIssueAgain(JSONObject bugDetails) throws Exception;
	public List<Issue> getSearchIssueList() throws Exception;
	public List<Issue> getSearchByTitleList(String title) throws Exception;
	
	
	public List<JSONObject> getIssueDetails(Integer id) throws Exception;
	
	
	
}
