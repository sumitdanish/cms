package com.micromax.bugtracker.dao.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.Issue;
import com.micromax.bugtracker.ProdVersion;
import com.micromax.bugtracker.User;
import com.micromax.bugtracker.dao.service.IssueDAOService;
import com.micromax.bugtracker.service.IssueService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;



@Repository ("issue")
public class IssueDAOServiceImpl implements IssueDAOService{

	public List<Issue> getIssueTitles(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Issue> getToptrendingBugs() throws Exception {
		List<Issue> issueList = null;
		Session session = null;
		Transaction tranx = null; 
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Query query = session.createQuery("From Issue where isOpenAgain=:is_open_again and isOpen=:is_open");
			query.setParameter("is_open_again", Byte.parseByte(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.IS_OPEN_AGAIN))));
			query.setParameter("is_open",Byte.parseByte(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.IS_OPEN))));
			issueList = query.list();
			System.out.println("List Size : "+issueList.size());
			tranx.commit();
		}catch(Exception ex){
			tranx.rollback();
			issueList = null;
			throw ex;
		}finally{
			if(session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return issueList;
	}

	public JSONObject saveBugDetail(JSONObject bugDetails) throws Exception {
		//System.out.println(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.DESCRIPTION))));
		Transaction tranx = null;
		Session session = null;
		Issue issue = null;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			issue = new Issue();
			if(Boolean.parseBoolean(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.IS_DUPLICATE)))))){
				
			}
			Category cateId = new Category(Integer.parseInt(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.CATE_ID))))));
			Category prodCateId = new Category(Integer.parseInt(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.PROD_CATE_ID))))));
			ProdVersion prodVersionId = new ProdVersion(Integer.parseInt(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.PROD_VERSION_ID))))));
			User user = new User(Integer.parseInt(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.USER_ID))))));
			issue.setDescription(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.DESCRIPTION)))));
			issue.setTitle(String.valueOf(bugDetails.get(String.valueOf(CommonUtils.getCommonUtils().getValue(PropertiesConstants.TITLE)))));
			issue.setCategoryByCtId(cateId);
			issue.setCategoryByCt1Id(prodCateId);
			issue.setProdVersion(prodVersionId);
			issue.setUser(user);
			issue.setCreatedTime(new Date());
			issue.setIsOpen(Byte.valueOf(String.valueOf(1)));
			issue.setIsOpenAgain(Byte.valueOf(String.valueOf(0)));
			session.save(issue);
			tranx.commit();
			bugDetails.put("success","success");
			bugDetails.put("issue",issue);
		}catch(Exception ex){
			if(tranx != null){
				tranx.rollback();
			}
			bugDetails.put("fail","fail");
			ex.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return bugDetails;
	}

	public JSONObject duplicateIssueDetails(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject openIssueAgain(JSONObject josnObject) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Print : "+josnObject.get("issueId"));
		Transaction tranx = null;
		Session session = null;
		JSONObject jsonObject = null;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			jsonObject = new JSONObject();
			Issue issue = (Issue)session.get(Issue.class,(Serializable) josnObject.get("issueId"));
			System.out.println(issue.getTitle());
			issue.setIsOpenAgain(Byte.parseByte(String.valueOf(1)));
			session.update(issue);
			tranx.commit();
			jsonObject.put("success", "success");
		}catch(Exception ex){
			jsonObject = null;
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
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getSearchIssueList() throws Exception {
		List<Issue> issueList = null;
		Session session = null;
		Transaction tranx = null;
		try{
			/*JSONArray jsonObject = (JSONArray)new JSONParser().parse(id);
			System.out.println(jsonObject.toString());*/
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Issue.class,"isu").addOrder(Order.desc("isu.createdTime"));
			criteria.createAlias("user","user",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCtId","categoryByCtId",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCt1Id","categoryByCt1Id",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("prodVersion","prodVersions",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("isu.id"));//0
			projection.add(Projections.property("isu.title"));//1
			projection.add(Projections.property("isu.description"));//2
			projection.add(Projections.property("isu.categoryByCtId.id"));//3
			projection.add(Projections.property("isu.categoryByCt1Id.id"));//4
			//projection.add(Projections.property("isu.categoryByCtId.categoryName"));//3
			//projection.add(Projections.property("isu.categoryByCt1Id.categoryName"));//4
			projection.add(Projections.property("isu.issue.id"));//5
			projection.add(Projections.property("isu.user.id"));//6
			projection.add(Projections.property("isu.createdTime"));//7
			projection.add(Projections.property("isu.moderatorId"));//8
			projection.add(Projections.property("isu.isOpen"));//9
			projection.add(Projections.property("isu.isOpenAgain"));//10
			projection.add(Projections.property("isu.prodVersion.id"));//11
			projection.add(Projections.property("prodVersions.productVersion"));//12
			projection.add(Projections.property("prodVersions.id"));//13
			criteria.setProjection(projection);
			criteria.add(Restrictions.eq("isu.isOpenAgain",Byte.parseByte(String.valueOf(0))));
			criteria.add(Restrictions.eq("isu.isOpen",Byte.parseByte(String.valueOf(0))));
			/*if(idList != null){
				criteria.add(Restrictions.eq("isu.categoryByCt1Id.id", Integer.parseInt(idList[0])));
				criteria.add(Restrictions.eq("isu.prodVersion.id", Integer.parseInt(idList[1])));
			}*/
			/*criteria.add(Restrictions.eq("prodVersions.id","issue.prodVersion.id"));*/
			List<?> list = criteria.list();
			Iterator<?> it = list.iterator();
			issueList = new ArrayList<Issue>();
			while(it.hasNext()){
				Object[] obj = (Object[])it.next();
				Issue issue = new Issue();
				User user = new User((Integer)obj[6]);
				issue.setId((Integer)obj[0]);
				issue.setTitle(String.valueOf(obj[1]));
				issue.setDescription(String.valueOf(obj[2]));
				Category catid = new Category((Integer)obj[3]);
				Category cat1id = new Category((Integer)obj[4]);
				issue.setCategoryByCtId(catid);
				issue.setCategoryByCt1Id(cat1id);
				issue.setIssue(new Issue((Integer)obj[5]));
				issue.setUser(user);
				issue.setCreatedTime((Date)obj[7]);
				issue.setModeratorId(String.valueOf(obj[8]));
				issue.setIsOpen(Byte.parseByte(String.valueOf(obj[9])));
				issue.setIsOpenAgain(Byte.parseByte(String.valueOf(obj[10])));
				issue.setProdVersion(new ProdVersion((Integer)obj[11]));
				issueList.add(issue);
			}
			//jsonObject.put("success","success");
			//jsonObject.put("searchList",issueList);
			tranx.commit();
		}catch(Exception ex){
			//jsonObject.put("fail","fail");
			if(tranx != null){
				tranx.rollback();
			}
			issueList = null;
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		
		return issueList;
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getSearchByTitleList(String title) throws Exception {
		List<Issue> issueList = null;
		Session session = null;
		Transaction tranx = null;
		try{
			/*if(session != null && !session.isOpen()){
				session = HibernateUtil.getSession();
			}*/
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Issue.class,"isu").addOrder(Order.desc("isu.createdTime"));
			criteria.createAlias("user","user",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCtId","categoryByCtId",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCt1Id","categoryByCt1Id",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("prodVersion","prodVersions",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("isu.id"));//0
			projection.add(Projections.property("isu.title"));//1
			projection.add(Projections.property("isu.description"));//2
			projection.add(Projections.property("isu.categoryByCtId.id"));//3
			projection.add(Projections.property("isu.categoryByCt1Id.id"));//4
			//projection.add(Projections.property("isu.categoryByCtId.categoryName"));//3
			//projection.add(Projections.property("isu.categoryByCt1Id.categoryName"));//4
			projection.add(Projections.property("isu.issue.id"));//5
			projection.add(Projections.property("isu.user.id"));//6
			projection.add(Projections.property("isu.createdTime"));//7
			projection.add(Projections.property("isu.moderatorId"));//8
			projection.add(Projections.property("isu.isOpen"));//9
			projection.add(Projections.property("isu.isOpenAgain"));//10
			projection.add(Projections.property("isu.prodVersion.id"));//11
			projection.add(Projections.property("prodVersions.productVersion"));//12
			projection.add(Projections.property("prodVersions.id"));//13
			criteria.setProjection(projection);
			criteria.add(Restrictions.eq("isu.isOpenAgain",Byte.parseByte(String.valueOf(0))));
			criteria.add(Restrictions.eq("isu.isOpen",Byte.parseByte(String.valueOf(1))));
			criteria.add(Restrictions.ilike("isu.title", title, MatchMode.ANYWHERE));
			/*if(idList != null){
				criteria.add(Restrictions.eq("isu.categoryByCt1Id.id", Integer.parseInt(idList[0])));
				criteria.add(Restrictions.eq("isu.prodVersion.id", Integer.parseInt(idList[1])));
			}*/
			/*criteria.add(Restrictions.eq("prodVersions.id","issue.prodVersion.id"));*/
			List<?> list = criteria.list();
			Iterator<?> it = list.iterator();
			issueList = new ArrayList<Issue>();
			while(it.hasNext()){
				Object[] obj = (Object[])it.next();
				Issue issue = new Issue();
				User user = new User((Integer)obj[6]);
				issue.setId((Integer)obj[0]);
				issue.setTitle(String.valueOf(obj[1]));
				issue.setDescription(String.valueOf(obj[2]));
				Category catid = new Category((Integer)obj[3]);
				Category cat1id = new Category((Integer)obj[4]);
				issue.setCategoryByCtId(catid);
				issue.setCategoryByCt1Id(cat1id);
				issue.setIssue(new Issue((Integer)obj[5]));
				issue.setUser(user);
				issue.setCreatedTime((Date)obj[7]);
				issue.setModeratorId(String.valueOf(obj[8]));
				issue.setIsOpen(Byte.parseByte(String.valueOf(obj[9])));
				issue.setIsOpenAgain(Byte.parseByte(String.valueOf(obj[10])));
				issue.setProdVersion(new ProdVersion((Integer)obj[11]));
				issueList.add(issue);
			}
			//jsonObject.put("success","success");
			//jsonObject.put("searchList",issueList);
			tranx.commit();
		}catch(Exception ex){
			//jsonObject.put("fail","fail");
			if(tranx != null){
				tranx.rollback();
			}
			issueList = null;
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		
		return issueList;
	}

	
	public List<JSONObject> getIssueDetails(Integer id) throws Exception {
		Session session = null;
		Transaction tranx = null;
		List<JSONObject> issueList = null;
		JSONObject json = new JSONObject();
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Issue.class,"isu").addOrder(Order.desc("isu.createdTime"));
			criteria.createAlias("user","user",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCtId","categoryByCtId",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("categoryByCt1Id","categoryByCt1Id",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("prodVersion","prodVersions",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("isu.id"));//0
			projection.add(Projections.property("isu.title"));//1
			projection.add(Projections.property("isu.description"));//2
			projection.add(Projections.property("isu.categoryByCtId.id"));//3
			projection.add(Projections.property("isu.categoryByCt1Id.id"));//4
			//projection.add(Projections.property("isu.categoryByCtId.categoryName"));//3
			//projection.add(Projections.property("isu.categoryByCt1Id.categoryName"));//4
			projection.add(Projections.property("isu.issue.id"));//5
			projection.add(Projections.property("isu.user.id"));//6
			projection.add(Projections.property("isu.createdTime"));//7
			projection.add(Projections.property("isu.moderatorId"));//8
			projection.add(Projections.property("isu.isOpen"));//9
			projection.add(Projections.property("isu.isOpenAgain"));//10
			projection.add(Projections.property("isu.prodVersion.id"));//11
			projection.add(Projections.property("prodVersions.productVersion"));//12
			projection.add(Projections.property("prodVersions.id"));//13
			projection.add(Projections.property("categoryByCtId.categoryName"));//14
			projection.add(Projections.property("categoryByCt1Id.categoryName"));//15
			projection.add(Projections.property("user.emailId"));//16
			criteria.setProjection(projection);
			criteria.add(Restrictions.eq("isu.id",id));
			List list = criteria.list();
			Iterator it = list.iterator();
			issueList = new ArrayList<JSONObject>();
			while(it.hasNext()){
				Object[] obj = (Object[])it.next();
				JSONObject jsonObject = new JSONObject();
				Issue issue = new Issue();
				User user = new User((Integer)obj[6],String.valueOf(obj[16]));
				ProdVersion prodVersion = new ProdVersion((Integer)obj[11],String.valueOf(obj[12]));
				issue.setId((Integer)obj[0]);
				issue.setTitle(String.valueOf(obj[1]));
				issue.setDescription(String.valueOf(obj[2]));
				Category catid = new Category((Integer)obj[3],String.valueOf(obj[14]));
				Category cat1id = new Category((Integer)obj[4],String.valueOf(obj[15]));
				issue.setCategoryByCtId(catid);
				issue.setCategoryByCt1Id(cat1id);
				issue.setIssue(new Issue((Integer)obj[5],String.valueOf(obj[1]),String.valueOf(obj[2])));
				issue.setUser(user);
				issue.setCreatedTime((Date)obj[7]);
				issue.setModeratorId(String.valueOf(obj[8]));
				issue.setIsOpen(Byte.parseByte(String.valueOf(obj[9])));
				issue.setIsOpenAgain(Byte.parseByte(String.valueOf(obj[10])));
				issue.setProdVersion(prodVersion);
				jsonObject.put("catid", catid);
				jsonObject.put("cat1id", cat1id);
				jsonObject.put("prod", prodVersion);
				jsonObject.put("user",user);
				jsonObject.put("issue", issue);
				issueList.add(jsonObject);
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
		
		return issueList;
	}

	
	
	


}
