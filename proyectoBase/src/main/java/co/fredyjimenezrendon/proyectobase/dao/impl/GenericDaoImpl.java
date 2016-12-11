package co.fredyjimenezrendon.proyectobase.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.fredyjimenezrendon.proyectobase.dao.GenericDao;


@Repository
public class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl(){
		//Contructor vacio para hibernate
	}
	
	public GenericDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public T find(Class<T> type, Integer id) {
		return (T) sessionFactory.getCurrentSession().get(type, id);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> type) {
		return sessionFactory.getCurrentSession().createCriteria(type).list();
	}

	@SuppressWarnings("unchecked")
	public T save(T t) {
		return (T) sessionFactory.getCurrentSession().save(t);
		
	}

	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
		
	}

	public void delete(T t) {
		sessionFactory.getCurrentSession().delete(t);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBy(Class<T> type, List<Criterion> criterions) {
		List<T> ts;		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		ts = criteria.list();		
	
		return ts;
	}

}
