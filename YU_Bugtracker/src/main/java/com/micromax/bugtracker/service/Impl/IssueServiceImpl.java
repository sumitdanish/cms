package com.micromax.bugtracker.service.Impl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.dao.service.IssueDAOService;
import com.micromax.bugtracker.service.IssueService;


@Service
public class IssueServiceImpl implements IssueService {

	
	@Autowired
	IssueDAOService issueDAOService;
	public List<Issue> getIssueTitles(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Issue> getTopTrendingBugs() throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.getToptrendingBugs();
	}

	public JSONObject saveBugDetail(JSONObject bugDetails) throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.saveBugDetail(bugDetails);
	}

	public JSONObject duplicateIssueDetails(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.duplicateIssueDetails(id);
	}

	public JSONObject openIssueAgain(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.openIssueAgain(jsonObject);
	}

	public List<Issue> getSearchIssueList() throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.getSearchIssueList();
	}

	public List<Issue> getSearchByTitleList(String title) throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.getSearchByTitleList(title);
	}

	
	public List<JSONObject> getIssueDetails(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return issueDAOService.getIssueDetails(id);
	}

	

	

}
