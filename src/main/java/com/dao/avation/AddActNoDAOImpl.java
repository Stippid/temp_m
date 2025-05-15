package com.dao.avation;

import java.math.BigInteger;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_AVIATION_ACT_MASTER;
import com.models.TB_AVIATION_RPAS_ACT_MASTER;
import com.persistance.util.HibernateUtil;

public class AddActNoDAOImpl implements AddActNoDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	} 
	
	@SuppressWarnings("unchecked")
	public Boolean ifExistActNo(BigInteger act) {
		String q = "from TB_AVIATION_ACT_MASTER where act =:act";
   	    List<TB_AVIATION_ACT_MASTER> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("act", act);
	   		created_by = (List<TB_AVIATION_ACT_MASTER>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    e.printStackTrace();
   		    return null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }
	
	@SuppressWarnings("unchecked")
	public Boolean ifExistRPASActNo(BigInteger act) {
		String q = "from TB_AVIATION_RPAS_ACT_MASTER where act =:act";
   	    List<TB_AVIATION_RPAS_ACT_MASTER> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("act", act);
	   		created_by = (List<TB_AVIATION_RPAS_ACT_MASTER>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    e.printStackTrace();
   		    return null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }


}
