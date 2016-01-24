package com.micromax.bugtracker.dao.service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.AddFavouraite;
import com.micromax.bugtracker.Fallow;
import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.dao.service.FollowDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;


@Repository ("fallow")
public class FollowDAOServiceImpl implements FollowDAOService{

	public JSONObject followUserIssue(JSONObject jsonObject) throws Exception {
		Session session = null;
		Transaction tranx = null;
		JSONObject jsonResult = new JSONObject();
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Issue issue = new Issue(Integer.parseInt(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(String.valueOf(PropertiesConstants.ISSUE_ID))))));
			User user = new User(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID))))));   
			Fallow fallow = new Fallow();
			fallow.setIssue(issue);
			fallow.setUser(user);
			fallow.setCreatedTime(new Date());
			session.save(fallow);
			tranx.commit();
			jsonResult.put("success","success");
		}catch(Exception ex){
			if(tranx!=null){
				tranx.rollback();
				jsonResult.put("fail", "fail");
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
