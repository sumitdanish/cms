package com.micromax.bugtracker.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.ProdVersion;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.UserComment;
import com.micromax.bugtracker.dao.service.UserCommentDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.MessageConsumer;
import com.micromax.bugtracker.util.MessageProducer;
import com.micromax.bugtracker.util.MessageQueue;
import com.micromax.bugtracker.util.PropertiesConstants;


@Repository("user_comment")
public class UserCommentDAOServiceImpl implements UserCommentDAOService {

	BlockingQueue<UserComment> queue = MessageQueue.getMessageQueue().getUserCommentBlockingQueue();
	@SuppressWarnings("unchecked")
	public JSONObject addUserComment(JSONObject jsonObject) throws Exception {
		Session session = null;
		Transaction tranx = null;
		JSONObject jsonResult = new JSONObject();
		try{
			
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			UserComment userComment = new UserComment();
			userComment.setIssue(new Issue(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.ISSUE_ID)))))));
			userComment.setUser(new User(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID)))))));
			userComment.setCreatedTime(new Date());
			userComment.setDescription(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_COMMENT)))));
			session.save(userComment);
			jsonResult.put("success", "success");
			tranx.commit();
		}catch(Exception ex){
			if(tranx != null){
				tranx.rollback();
			}
			ex.printStackTrace();
			jsonResult.put("fail","fail");
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return jsonResult;
	}

	
	
	public JSONObject editMyIssueTitle(JSONObject jsonObject) throws Exception {
		Session session = null;
		Transaction tranx = null;
		JSONObject jsonResule = new JSONObject();
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Issue issue = new Issue();
			issue.setId((Integer)jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.ISSUE_ID))));
			issue.setTitle(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.TITLE)))));
			issue.setCategoryByCtId(new Category(Integer.parseInt(String.valueOf(jsonObject.get(CommonUtils.getCommonUtils().getValue(PropertiesConstants.CATE_ID))))));
			issue.setCategoryByCt1Id(new Category(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.PROD_CATE_ID)))))));
			issue.setProdVersion(new ProdVersion(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.PROD_VERSION_ID)))))));
			issue.setUser(new User(Integer.parseInt(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID)))))));
			issue.setIsOpen(Byte.parseByte(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.IS_OPEN_TEXT))))));
			issue.setIsOpenAgain(Byte.parseByte(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.IS_OPEN_AGAIN_TEXT))))));
			issue.setDescription(String.valueOf(jsonObject.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.DESCRIPTION))))); 
			issue.setCreatedTime(new Date());
			session.update(issue);
			tranx.commit();
			jsonResule.put("success", "success");
		}catch(Exception ex){
			if(tranx != null){
				tranx.rollback();
			}
			ex.printStackTrace();
			jsonResule.put("fail","fail");
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return jsonResule;
	}

	public List<JSONObject> getUSerCommentList(Integer id) throws Exception {
		Session session = null;
		Transaction tranx = null;
		List<JSONObject> listJSON = null;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserComment.class,"userComment");
			criteria.createAlias("user", "user",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("issue", "issue",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("userComment.id"));//0
			projection.add(Projections.property("userComment.issue.id"));//1
			projection.add(Projections.property("userComment.description"));//2
			projection.add(Projections.property("userComment.user.id"));//3
			projection.add(Projections.property("user.emailId"));//4
			projection.add(Projections.property("issue.title"));//5
			projection.add(Projections.property("userComment.createdTime"));//6
			criteria.setProjection(projection);
			criteria.add(Restrictions.eq("userComment.issue.id",id));
			List<?> list = criteria.list();
			
			listJSON = new ArrayList<JSONObject>();
			Iterator<?> it = list.iterator();
			while(it.hasNext()){
				JSONObject jsonObject = new JSONObject();
				Object[] obj = (Object[])it.next();
				UserComment userComment = new UserComment();
				Issue issue = new Issue((Integer)obj[1],String.valueOf(obj[5]));
				User user = new User((Integer)obj[3],String.valueOf(obj[4]));
				userComment.setId((Integer)obj[0]);
				userComment.setIssue(issue);
				userComment.setDescription(String.valueOf(obj[2]));
				userComment.setCreatedTime((Date)obj[6]);
				userComment.setUser(user);
				jsonObject.put("user", user);
				jsonObject.put("userComment", userComment);
				listJSON.add(jsonObject);
			}
			tranx.commit();
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
		return listJSON;
	}
	/*class MessageConsumer implements Runnable{
		BlockingQueue<UserComment> queue;
		public MessageConsumer(BlockingQueue<UserComment> queue) {
			this.queue = queue;
		}
		public void run() {
			try{
				System.out.println("Queue size : "+queue.size());
				for(UserComment comment : queue){
					if(comment != null){
						Thread.sleep(Integer.parseInt(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.THREAD_SLEEP))));
						System.out.println("We are consuling the messages"+comment.getIssue().getTitle());
						
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}


	class MessageProducer implements Runnable{

		BlockingQueue<UserComment> queue;
		UserComment userComment;
		public MessageProducer(BlockingQueue<UserComment> queue,UserComment userComment){
			this.queue = queue;
			this.userComment = userComment;
		}
		public void run() {
			try{
				int sleepTime = Integer.parseInt(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.THREAD_SLEEP)));
				//Thread.sleep(sleepTime);
				System.out.println("We are producer the message");
				queue.put(userComment);
				System.out.println("P Que : "+queue.size());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}*/
}

