package com.controller.state;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.State;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/","user"}) 
public class StateController {
	
	@RequestMapping(value = "/getStateList"  , method = RequestMethod.POST)
	public @ResponseBody List<State> getStateList() {		
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from State order by stname");
		@SuppressWarnings("unchecked")
		List<State> list = (List<State>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	public @ResponseBody List<State> getStateNameFromSTCode(String stCode) {		
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from State where stcode11=:stCode");
		q.setParameter("stCode", stCode);
		@SuppressWarnings("unchecked")
		List<State> list = (List<State>) q.list();
		tx.commit();
		session.close();
		return list;
	}
}
