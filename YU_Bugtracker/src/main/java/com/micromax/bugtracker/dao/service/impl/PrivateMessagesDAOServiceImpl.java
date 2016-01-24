package com.micromax.bugtracker.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.json.JsonReadContext;
import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.MessageReadbleStatus;
import com.micromax.bugtracker.PrivateMessages;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.dao.service.PrivateMessagesDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;


@Repository("messages")
public class PrivateMessagesDAOServiceImpl implements PrivateMessagesDAOService{

	public JSONObject addPrivateMessages(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tranx = null;
		JSONObject jsonResult = new JSONObject();
		try{
			
			StringBuilder sb = new StringBuilder();
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			PrivateMessages privateMessages = new PrivateMessages();
			String userIdList = sb.append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.TO_ID))))
					.append(",").append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.BCC_ID))))
					.append(",").append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.CC_ID)))).toString();
			
			
			HashSet<MessageReadbleStatus> messageSet = new HashSet<MessageReadbleStatus>();
			User user = new User(Integer.parseInt(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.FROM_ID)))));
			Issue issue = new Issue(Integer.parseInt(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.ISSUE_ID)))));
			privateMessages.setToId(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.TO_ID))));
			privateMessages.setUser(user);
			privateMessages.setBcc(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.BCC_ID))));
			privateMessages.setCcId(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.CC_ID))));
			privateMessages.setMessageBody(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.PRIVATE_MESSAGES_BODY))));
			privateMessages.setCreateDate(new Date());
			privateMessages.setIssue(issue);
			MessageReadbleStatus mesa = new MessageReadbleStatus();
			String[] s = userIdList.split(",");
			privateMessages.setMessageReadbleStatuses(messageSet);
			mesa.setPrivateMessages(privateMessages);
			session.save(privateMessages);
			/*for(String userid : userIdList.split(",")){
				MessageReadbleStatus mesa = new MessageReadbleStatus();
				HashSet<MessageReadbleStatus> messageSet = new HashSet<MessageReadbleStatus>();
				MessageReadbleStatus me = new MessageReadbleStatus();
				me.setUserId(Integer.parseInt(userid.trim()));
				me.setIsReadble(Byte.parseByte(String.valueOf("1")));
				me.setPrivateMessages(privateMessages);
				messageSet.add(me);
				privateMessages.setMessageReadbleStatuses(messageSet);
				mesa.setPrivateMessages(privateMessages);
				session.save(mesa);
			}*/
			session.save(mesa);
			tranx.commit();
			/*try{
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if(session != null && session.isOpen()){
					session.clear();
					session.close();
				}
			}*/
			
			/*String userIdList = sb.append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.TO_ID))))
								.append(",").append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.BCC_ID))))
								.append(",").append(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.CC_ID)))).toString();*/
			/*JSONObject privateMesasgeJson = new JSONObject();
			privateMesasgeJson.put("user_Id_List", userIdList);
			privateMesasgeJson.put("message_id", String.valueOf(privateMessages.getId()));
			String updateStatus = updateMessageReadbleSta(privateMesasgeJson).get("success").toString();*/
			jsonResult.put("success","success");
			/*if(updateStatus.equals("success")){
				
			}else{
				jsonResult.put("fail","fail");
			}*/
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

	public List<PrivateMessages> getMyInbox(Integer id,String messageType) {
		Session session = null;
		Transaction tranx = null;
		List<PrivateMessages> myInboxList = null;
		try{
			System.out.println("Message type : "+String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.GET_INBOX)));
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			//String toId = String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID)));
			Criteria criteria = session.createCriteria(PrivateMessages.class,"privateMessages").addOrder(Order.desc("privateMessages.createDate"));
			criteria.createAlias("user","user",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("issue","issue",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("privateMessages.id"));//0
			projection.add(Projections.property("privateMessages.issue.id"));//1
			projection.add(Projections.property("privateMessages.user.id"));//2
			projection.add(Projections.property("privateMessages.createDate"));//3
			projection.add(Projections.property("privateMessages.toId"));//4
			projection.add(Projections.property("user.emailId"));//5
			projection.add(Projections.property("issue.title"));//6
			projection.add(Projections.property("privateMessages.messageBody"));//7
			criteria.setProjection(projection);
			if(messageType.equals(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.GET_INBOX)))){
				criteria.add(Restrictions.eq("privateMessages.id",id));
			}else{
				criteria.add(Restrictions.like("privateMessages.toId",String.valueOf(id),MatchMode.ANYWHERE));
			}
			List list = criteria.list();
			Iterator iterator = list.iterator();
			myInboxList = new ArrayList<PrivateMessages>();
			while(iterator.hasNext()){
				PrivateMessages privateMessages = new PrivateMessages();
				Object[] obj = (Object[])iterator.next();
				Issue issue = new Issue(Integer.parseInt(String.valueOf(obj[1])),String.valueOf(obj[6]));
				User user = new User(Integer.parseInt(String.valueOf(obj[2])),String.valueOf(obj[5]));
				privateMessages.setId(Integer.parseInt(String.valueOf(obj[0])));
				privateMessages.setCreateDate((Date)obj[3]);
				privateMessages.setMessageBody(String.valueOf(obj[7]));
				myInboxList.add(privateMessages);
			}
		}catch(Exception ex){
			if(tranx != null){
				tranx.rollback();
			}
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return myInboxList;
	}
	
	private JSONObject updateMessageReadbleSta(JSONObject jsonObject) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonResult = null;
		Session session = null;
		Transaction tranx = null;
		boolean isCommited = false;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			String[] userIdList = String.valueOf(jsonObject.get("user_Id_List")).split(",");
			int bufferCount = 0;
			for(String userId : userIdList){
				MessageReadbleStatus messageReadbleStatus = new MessageReadbleStatus();
				PrivateMessages privateMessage = new PrivateMessages(Integer.parseInt(String.valueOf(jsonObject.get("message_id"))));
				messageReadbleStatus.setPrivateMessages(privateMessage);
				messageReadbleStatus.setIsReadble(Byte.parseByte(String.valueOf("1")));
				messageReadbleStatus.setUserId(Integer.parseInt(userId));
				session.save(messageReadbleStatus);
				if(bufferCount % 3 ==0){
					
				}
				//tranx.commit();
				bufferCount++;
				
			}
			
			
			
			jsonResult.put("success", "success");
		}catch(Exception ex){
			jsonResult.put("fail","fail");
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return jsonResult;
	}
	
	
	public PrivateMessages getMyMessages(Integer messagesId) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	public JSONObject updateMessageReadbleStatus(JSONObject jsonObject)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
