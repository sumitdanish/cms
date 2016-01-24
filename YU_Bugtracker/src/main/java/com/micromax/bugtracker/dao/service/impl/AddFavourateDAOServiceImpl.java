package com.micromax.bugtracker.dao.service.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.AddFavouraite;
import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.dao.service.AddFavourateDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;



@Repository ("addfavoraite")
public class AddFavourateDAOServiceImpl implements AddFavourateDAOService{

	public JSONObject addFavoraite(JSONObject jsonObject) throws Exception {
		Session session = null;
		Transaction tranx = null;
		JSONObject jsonResult = new JSONObject();
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Issue issue = new Issue(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.ISSUE_ID))))));
			User user = new User(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID))))));   
			AddFavouraite addFavouraite = new AddFavouraite();
			addFavouraite.setIssue(issue);
			addFavouraite.setUser(user);
			addFavouraite.setCreatedTime(new Date());
			session.save(addFavouraite);
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

