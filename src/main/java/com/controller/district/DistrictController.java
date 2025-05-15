package com.controller.district;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.District;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/","user"}) 
public class DistrictController {
	
	@RequestMapping(value = "/getStateWiseDistrictNames" , method = RequestMethod.POST)
	public @ResponseBody List<District> getDistrictNames(String stateId) {
		
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from District where stateId =:stateId order by distName");
		q.setParameter("stateId", stateId);
		@SuppressWarnings("unchecked")
		List<District> list = (List<District>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
	public List<District> getDistrictNamesFromDistCode(String distcode) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from District where distId=:distcode");
		q.setParameter("distcode", distcode);
		@SuppressWarnings("unchecked")
		List<District> list = (List<District>) q.list();
		tx.commit();
		session.close();
		return list;
	}	
}
