package com.micromax.bugtracker.util;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFacorySingleton implements Serializable {

	private static final long serialVersionUID = 7680395768899336336L;

	private final SessionFactory sessionFactory;

	private static SessionFacorySingleton sessionFactorySingleton = null;

	private SessionFacorySingleton() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static SessionFacorySingleton getsessionFactorySingleton() {
		try {
			if (sessionFactorySingleton == null) {
				synchronized (SessionFacorySingleton.class) {
					if (sessionFactorySingleton == null) {
						sessionFactorySingleton = new SessionFacorySingleton();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactorySingleton;
	}

	public SessionFactory getProductSessionFactory() {
		return sessionFactory;
	}
}
