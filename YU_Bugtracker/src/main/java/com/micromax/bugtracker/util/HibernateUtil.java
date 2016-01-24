package com.micromax.bugtracker.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.Serializable;

import com.micromax.bugtracker.util.SessionFacorySingleton;

public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = -1417259927173663654L;
	private static SessionFactory sessionFactory;

	public HibernateUtil() {

	}

	public static Session getSession() throws HibernateException {
		return getSessionFactory().openSession();
	}

	private static SessionFactory getSessionFactory() {
		sessionFactory = SessionFacorySingleton.getsessionFactorySingleton().getProductSessionFactory();
		return sessionFactory;
	}
}
