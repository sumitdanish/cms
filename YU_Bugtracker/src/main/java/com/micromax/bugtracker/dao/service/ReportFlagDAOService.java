package com.micromax.bugtracker.dao.service;

import org.json.simple.JSONObject;

public interface ReportFlagDAOService {

	public JSONObject reportToModerator(JSONObject jsonObject) throws Exception;
	
	
}
