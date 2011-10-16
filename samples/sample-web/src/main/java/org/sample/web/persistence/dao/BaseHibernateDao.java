package org.sample.web.persistence.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.easyssh.framework.orm.hibernate.HibernateDao;

/**
 * DAO基类
 */
public abstract class BaseHibernateDao<T, PK extends Serializable> extends HibernateDao<T, PK> {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public Session getSession() {
		return SessionFactoryUtils.getSession(sessionFactory, true);
	}
}
