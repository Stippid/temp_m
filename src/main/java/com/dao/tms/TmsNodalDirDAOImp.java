package com.dao.tms;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_MCT_NODAL_DIR_MASTER;
import com.persistance.util.HibernateUtil;

public class TmsNodalDirDAOImp implements TmsNodalDirDAO{

	
	@SuppressWarnings("unchecked")
	public Boolean ifExist(String type_of_agncy,String sus_no,String depot_code,String type_of_veh ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<TB_TMS_MCT_NODAL_DIR_MASTER> created_by = null;
		if(type_of_agncy.equals("Depot") || type_of_agncy == "Depot" )
		{
			try {
	   	    	String q = "from TB_TMS_MCT_NODAL_DIR_MASTER where sus_no=:sus_no and type_of_agncy=:type_of_agncy and type_of_veh =:type_of_veh";
	   	    	Query query = session.createQuery(q);
		   		query.setParameter("sus_no", sus_no);
		   		query.setParameter("type_of_veh", type_of_veh);
		   		query.setParameter("type_of_agncy", type_of_agncy);
		   		created_by = (List<TB_TMS_MCT_NODAL_DIR_MASTER>) query.list();
		   		tx.commit();
		   	}catch(Exception e) {
	   	    	session.getTransaction().rollback();
	   		    return null; 
	   	    }
	   	    finally
	   	    {
	   	    	session.close();
	   	    }
		}
		else
		{
		 	try {
	   	    	String q = "from TB_TMS_MCT_NODAL_DIR_MASTER where sus_no =:sus_no and type_of_agncy=:type_of_agncy ";
	   	    	Query query = session.createQuery(q);
		   		query.setParameter("sus_no", sus_no);
		   		query.setParameter("type_of_agncy", type_of_agncy);
		   		 created_by = (List<TB_TMS_MCT_NODAL_DIR_MASTER>) query.list();
		   		tx.commit();
		    }catch(Exception e) {
	   	    	session.getTransaction().rollback();
	   		    return null; 
	   	    }
	   	    finally
	   	    {
	   	    	session.close();
	   	    }
		}
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }
}