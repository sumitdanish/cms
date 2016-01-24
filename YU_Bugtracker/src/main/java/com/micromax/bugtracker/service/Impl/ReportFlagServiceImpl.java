package com.micromax.bugtracker.service.Impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromax.bugtracker.dao.service.ReportFlagDAOService;
import com.micromax.bugtracker.service.ReportFlagService;


@Service
public class ReportFlagServiceImpl implements ReportFlagService{

	
	@Autowired
	ReportFlagDAOService reportFlagDAOService;
	public JSONObject reportToModerator(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		return reportFlagDAOService.reportToModerator(jsonObject);
	}

}
