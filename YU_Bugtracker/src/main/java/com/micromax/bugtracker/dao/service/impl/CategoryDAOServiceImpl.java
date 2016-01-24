package com.micromax.bugtracker.dao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.micromax.bugtracker.Category;
import com.micromax.bugtracker.ProdVersion;
import com.micromax.bugtracker.dao.service.CategoryDAOService;
import com.micromax.bugtracker.util.CommonUtils;
import com.micromax.bugtracker.util.HibernateUtil;
import com.micromax.bugtracker.util.PropertiesConstants;


@Repository ("categoryRepo")
public class CategoryDAOServiceImpl implements CategoryDAOService{

	public Category addCategory(Category category) throws Exception {
		
		Session session = null;
		Transaction tranx = null;
		try{
			
			session =HibernateUtil.getSession();
			tranx = session.beginTransaction();
			session.save(category);
			if(!tranx.wasCommitted()){
				tranx.commit();
			}
			
		}catch(Exception ex){
			category = null;
			tranx.rollback();
			ex.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return category;
	}

	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Category> getCategories(String type) throws Exception {
		List<Category> categoryList = new ArrayList<Category>();
		Transaction tranx = null;
		Session session = null;
		
		try{
			boolean flag = false;
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Category.class, "cateChild");
			criteria.createAlias("category","cateParent",CriteriaSpecification.LEFT_JOIN);
			criteria.createAlias("prodVersions", "prodVer",CriteriaSpecification.LEFT_JOIN);
			ProjectionList projection = Projections.projectionList();
			projection.add(Projections.property("cateChild.id"));
			projection.add(Projections.property("cateParent.id"));
			projection.add(Projections.property("cateParent.categoryName"));
			projection.add(Projections.property("cateChild.categoryName"));
			projection.add(Projections.property("prodVer.productVersion"));
			projection.add(Projections.property("prodVer.id"));
			//projection.add(Projections.property("cateChild.category.isProduct"));
			projection.add(Projections.property("cateParent.isProduct"));
			criteria.setProjection(projection);
			if((null != type || !"null".equals(type))&& "ca".equals(type) && !flag){
				criteria.add(Restrictions.eq("cateParent.isProduct",type));
				flag =		 true;
			}
			criteria.add(Restrictions.isNotNull("cateChild.category.id"));
			if(!flag){
				//criteria.add(Projections.groupProperty("cateParent.isProduct"));
			}
			List<Category> list = (List<Category>)criteria.list();
			Iterator iterator = list.iterator();System.out.println(list.size());
			while(iterator.hasNext()){
				final Object[] obj = (Object[])iterator.next();
				HashSet<ProdVersion> set = new HashSet<ProdVersion>();
				if(String.valueOf(obj[4])!=null && obj[5] != null)
					set.add(new ProdVersion((Integer)obj[5],String.valueOf(obj[4])));
				Category cat = new Category();
				cat.setCategory(new Category((Integer)obj[0],String.valueOf(obj[3])));
				cat.setId((Integer)obj[1]);
				cat.setCategoryName(String.valueOf(obj[2]));
				cat.setProdVersions(set);
				cat.setIsProduct(String.valueOf(obj[6]));
				categoryList.add(cat);
			}
		}catch(Exception ex){
			tranx.rollback();
			ex.printStackTrace();
			throw ex;
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return categoryList;
	}
	
	
	public List<ProdVersion> getProdVerion(Integer catId) throws Exception {
		List<ProdVersion> prodVersionList = null;
		Transaction tranx = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			tranx = session.beginTransaction();
			Query query = session.createQuery("From ProdVersion as prodVersion where prodVersion.category.id=:catId");
			query.setParameter("catId",catId);
			prodVersionList = query.list();
		}catch(Exception ex){
			tranx.rollback();
			prodVersionList = null;
			throw ex;
		}finally{
			if(session != null && session.isOpen()){
				session.clear();
				session.close();
			}
		}
		return prodVersionList;
	}
	
	private HashMap<String,HashMap<Integer,HashMap<Integer,String>>> modelCategory(List list) throws Exception{
		try{
			HashMap<String,HashMap<Integer,HashMap<Integer,String>>> cate = 
					new HashMap<String,HashMap<Integer,HashMap<Integer,String>>>();
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				final Object[] obj = (Object[]) iterator.next();
				if(cate.containsKey(String.valueOf(obj[1]))){
					if(cate.get(String.valueOf(obj[1])).containsKey(((Integer)obj[0]))){
						if(!cate.get(String.valueOf(obj[1])).get(((Integer)obj[0])).containsKey((Integer)obj[3])){
							cate.get(String.valueOf(obj[1])).get(((Integer)obj[0])).put((Integer)obj[3],String.valueOf(obj[2]));
						}
					}else{
								cate.get(String.valueOf(obj[1])).put((Integer)obj[0],new HashMap<Integer, String>()
								{{
									put((Integer)obj[3],String.valueOf(obj[2]));
								}});
					}
				}else{
					cate.put(String.valueOf(obj[1]),new HashMap<Integer, HashMap<Integer,String>>()
							{{
								put((Integer)obj[0],new HashMap<Integer, String>(){{
												put((Integer)obj[3],String.valueOf(obj[2]));
											}});
							}});
				}
			}
			return cate;
		}catch(Exception ex){
			
		}
		return null;
	}



	

}
