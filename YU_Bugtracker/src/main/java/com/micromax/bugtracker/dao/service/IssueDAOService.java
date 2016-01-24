package com.micromax.bugtracker.dao.service;

import java.util.List;

import org.json.simple.*;

import com.micromax.bugtracker.Issue;

public interface IssueDAOService {

	
	public List<Issue> getIssueTitles(String userId) throws Exception;
	public List<Issue> getToptrendingBugs() throws Exception;
	public JSONObject saveBugDetail(JSONObject bugDetails) throws Exception;
	public JSONObject duplicateIssueDetails(Integer id) throws Exception;
	public JSONObject openIssueAgain(JSONObject jsonObject) throws Exception;
	public List<Issue> getSearchIssueList() throws Exception;
	public List<Issue> getSearchByTitleList(String id) throws Exception;
	
	public List<JSONObject> getIssueDetails(Integer id) throws Exception;
	
	
}
