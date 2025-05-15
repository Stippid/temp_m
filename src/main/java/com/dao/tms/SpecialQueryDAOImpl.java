package com.dao.tms;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_RELIEF_PROG_HISTORY_A;
import com.models.Tms_Banum_Req_Child;
import com.persistance.util.HibernateUtil;

public class SpecialQueryDAOImpl implements SpecialQueryDAO{

	public Boolean ifExistbano(String ba_no) {
		String q = "from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no";
   	    List<TB_TMS_BANUM_DIRCTRY> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<TB_TMS_BANUM_DIRCTRY>) query.list();
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

	public Boolean ifExistbano(String vehicle_category, String ba_no) {
		String q = "from Tms_Banum_Req_Child where veh_cat =:veh_cat and ba_no=:ba_no";
   	    List<Tms_Banum_Req_Child> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("veh_cat", vehicle_category).setParameter("ba_no", ba_no);
	   		created_by = (List<Tms_Banum_Req_Child>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    return null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    else
   	    {
   	    	return false;
   	    }
   	   
	}
	
	public Boolean RevertBaNoDetails(String vehicle_category, String ba_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try{
   	    	if(vehicle_category.equals("A")) {
   	    		///Delete TB_TMS_CENSUS_RETURN DETAILS
	   	    	Query query1 = session.createQuery("delete from TB_TMS_CENSUS_RETURN where ba_no=:ba_no");
	   			query1.setParameter("ba_no", ba_no);
	   			query1.executeUpdate();
	   			
	   			//TB_TMS_CENSUS_DRR_DIR_DTL
	   			Query query2 = session.createQuery("delete from TB_TMS_CENSUS_DRR_DIR_DTL where ba_no=:ba_no");
	   			query2.setParameter("ba_no", ba_no);
	   			query2.executeUpdate();
	   			
	   			//TB TMS RELIEF PROG HISTORY A
	   			Query query3 = session.createQuery("delete from TB_TMS_RELIEF_PROG_HISTORY_A where ba_no=:ba_no");
	   			query3.setParameter("ba_no", ba_no);
	   			query3.executeUpdate();
	   			
	   			//TB_TMS_BANUM_DIRCTRY
	   			Query query4 = session.createQuery("update TB_TMS_BANUM_DIRCTRY set status='Active' where ba_no=:ba_no");
	   			query4.setParameter("ba_no", ba_no);
	   			query4.executeUpdate();
	   			
	   			tx.commit();
		   		return true;
   	    	}
   	    	if(vehicle_category.equals("B")) {
	   	    	///Delete MVCR PARTA DETAILS
	   	    	Query query1 = session.createQuery("delete from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no");
	   			query1.setParameter("ba_no", ba_no);
	   			query1.executeUpdate();
	   			
	   			//DRR DIR DTL
	   			Query query2 = session.createQuery("delete from TB_TMS_DRR_DIR_DTL where ba_no=:ba_no");
	   			query2.setParameter("ba_no", ba_no);
	   			query2.executeUpdate();
	   			
	   			//TB TMS RELIEF PROG HISTORY
	   			Query query3 = session.createQuery("delete from TB_TMS_RELIEF_PROG_HISTORY where ba_no=:ba_no");
	   			query3.setParameter("ba_no", ba_no);
	   			query3.executeUpdate();
	   			
	   			//TB_TMS_BANUM_DIRCTRY
	   			Query query4 = session.createQuery("update TB_TMS_BANUM_DIRCTRY set status='Active' where ba_no=:ba_no");
	   			query4.setParameter("ba_no", ba_no);
	   			query4.executeUpdate();
	   			
	   			tx.commit();
		   		return true;
	   		}
   	    	if(vehicle_category.equals("C")) {
   	    		///Delete TB_TMS_EMAR_REPORT
	   	    	Query query1 = session.createQuery("delete from TB_TMS_EMAR_REPORT where em_no=:ba_no");
	   			query1.setParameter("ba_no", ba_no);
	   			query1.executeUpdate();
	   			
	   			//TB_TMS_EMAR_DRR_DIR_DTL
	   			Query query2 = session.createQuery("delete from TB_TMS_EMAR_DRR_DIR_DTL where em_no=:ba_no");
	   			query2.setParameter("ba_no", ba_no);
	   			query2.executeUpdate();
	   			
	   			//TB TMS RELIEF PROG HISTORY C
	   			Query query3 = session.createQuery("delete from TB_TMS_RELIEF_PROG_HISTORY_C where ba_no=:ba_no");
	   			query3.setParameter("ba_no", ba_no);
	   			query3.executeUpdate();
	   			
	   			//TB_TMS_BANUM_DIRCTRY
	   			Query query4 = session.createQuery("update TB_TMS_BANUM_DIRCTRY set status='Active' where ba_no=:ba_no");
	   			query4.setParameter("ba_no", ba_no);
	   			query4.executeUpdate();
	   			
	   			tx.commit();
		   		return true;
   	    	}else {
   	    		return false;
   	    	}
   	   }catch(Exception e){
   	    	session.getTransaction().rollback();
   	    	return false; 
   	    }finally{
   	    	session.close();
		}
   	}

	public String baNoGeneration(int serialNo, String veh_code) {
		String serial = String.format("%06d", serialNo);
		int sum = 0;
		int i = 0;
		for(int iq=7; iq>1; iq--){
			String multi = String.valueOf(serial.charAt(i)); 
            int ans = Integer.parseInt(multi) * iq;
            sum +=ans;
            i++;
        }
		
		int mod = sum % 11;
		int mini = 11 - mod ;
		String last_cht = "";
		
		if(mini == 0)last_cht = "A";
		if(mini == 1)last_cht = "E";
		if(mini == 2)last_cht = "H";
		if(mini == 3)last_cht = "K";
		if(mini == 4)last_cht = "L";
		if(mini == 5)last_cht = "M";
		if(mini == 6)last_cht = "N";
		if(mini == 7)last_cht = "P";
		if(mini == 8)last_cht = "W";
		if(mini == 9)last_cht = "X";
		if(mini >= 10)last_cht = "Y";
		
		String baNo = veh_code +serial+last_cht;
		return baNo;
	}

	public Boolean ifExistbanoChild(String ba_no) {
		String q = "from Tms_Banum_Req_Child where ba_no=:ba_no";
   	    List<Tms_Banum_Req_Child> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<Tms_Banum_Req_Child>) query.list();
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

	public Boolean ifExistbanoMVCR(String ba_no) {
		String q = "from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no";
   	    List<TB_TMS_MVCR_PARTA_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<TB_TMS_MVCR_PARTA_DTL>) query.list();
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

	public Boolean ifExistbanoDRRDIR(String ba_no) {
		String q = "from TB_TMS_DRR_DIR_DTL where ba_no=:ba_no";
   	    List<TB_TMS_DRR_DIR_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<TB_TMS_DRR_DIR_DTL>) query.list();
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
	
	public String getvehcatfromBANo(String ba_no,HttpSession session) {

        String veh_cat="";
        
        String q = "from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no";
   	    List<TB_TMS_BANUM_DIRCTRY> created_by = null;
   	    Session session2 = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session2.beginTransaction();
   	    try {
   	    	Query query = session2.createQuery(q);
	   		query.setParameter("ba_no", ba_no);
	   		created_by = (List<TB_TMS_BANUM_DIRCTRY>) query.list();
	   		tx.commit();
	   		session2.close();
   	    }catch(Exception e) {
   	    	session2.getTransaction().rollback();
   		    e.printStackTrace();
   		    return null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	veh_cat=created_by.get(0).getVeh_cat();
   	    	return veh_cat;
   	    }
   	    return veh_cat;

 }
}