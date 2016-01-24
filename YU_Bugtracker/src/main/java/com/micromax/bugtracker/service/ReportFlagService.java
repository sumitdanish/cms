package com.micromax.bugtracker.service;

import org.json.simple.JSONObject;

public interface ReportFlagService {
	public JSONObject reportToModerator(JSONObject jsonObject) throws Exception;
}
