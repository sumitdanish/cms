package com.micromax.bugtracker.dao.service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.ReportFlag;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.dao.service.ReportFlagDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;

@Repository ("report_flag")
public class ReportFlagDAOServiceImpl implements ReportFlagDAOService{

	public JSONObject reportToModerator(JSONObject jsonObject) throws Exception {
		JSONObject jsonResult = new JSONObject();
		Session session = null;
		Transaction tranx = null;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			ReportFlag reportFlag = new ReportFlag();
			Issue issue = new Issue(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.ISSUE_ID))))));
			User user = new User(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID))))));
			reportFlag.setIssue(issue);
			reportFlag.setUser(user);;
			reportFlag.setCreatedTime(new Date());
			session.save(reportFlag);
			tranx.commit();
			jsonResult.put("success","success");
		}catch(Exception ex){
			if(tranx != null){
				tranx.rollback();
				jsonResult.put("fail","fail");
			}
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return jsonResult;
	}

}
