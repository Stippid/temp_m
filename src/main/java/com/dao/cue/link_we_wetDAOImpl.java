package com.dao.cue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.models.cue_wepe;
import com.persistance.util.HibernateUtilNA;

public class link_we_wetDAOImpl implements link_we_wetDAO {
	
public String setApprovedWELinkwithWET(int appid, String username) {
		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//		String hqlUpdate = "update cue_wepe c set c.wet_link_status = :status where c.id = :id";
		String hqlUpdate = "update cue_wepe c set c.wet_link_status = :status, approved_rejected_by=:approved_rejected_by, date_of_apprv_rejc=:date where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}

public String setRejectWELinkwithWET(int appid) {	
	Session session = HibernateUtilNA.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	String hqlUpdate = "update cue_wepe c set c.wet_link_status=:status where c.id = :id";
	int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
	tx.commit();
	session.close();
	if(app > 0) {
		return "Rejected Successfully";
	}else {
		return "Rejected not Successfully";
	}	
}

public String setDeleteWELinkwithWET(int appid) {	
	Session session = HibernateUtilNA.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	String hql="update cue_wepe set wet_pet_no ='',unit_visible ='',wet_link_status ='' where id=:id";
    Query query = session.createQuery(hql);
    query.setInteger("id",appid);
    int rowCount = query.executeUpdate();
	tx.commit();
	session.close();
	if(rowCount > 0) {
		return "Deleted Successfully";
	}else {
		return "Deleted not Successfully";
	}		
}

public cue_wepe getWELinkwithWETByid(int id) {
	Session session = HibernateUtilNA.getSessionFactory().openSession();
	session.beginTransaction();	
	Query q = session.createQuery("from cue_wepe where id=:id");
	q.setParameter("id", id);
	cue_wepe list = (cue_wepe) q.list().get(0);
	session.getTransaction().commit();
	session.close();
	return list;	
}

public String UpdateWELinkwithWET(cue_wepe artAttValues,String username) {
	Session session = HibernateUtilNA.getSessionFactory().openSession();
	String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	Transaction tx = session.beginTransaction();
	String hql = "update cue_wepe set wet_pet_no =:wet_pet_no,unit_visible =:unit_visible,modified_by=:modified_by,modified_on=:modified_on,wet_link_status ='0' where id=:id";
    Query query = session.createQuery(hql).setString("wet_pet_no",artAttValues.getWet_pet_no()).setString("unit_visible",artAttValues.getUnit_visible()).setString("modified_by",username).setString("modified_on",  modifydate).setInteger("id",artAttValues.getId());
    int rowCount = query.executeUpdate();
	tx.commit();
	session.close();
	if(rowCount > 0) {
		return "Updated Successfully";
	}else {
		return "Updated not Successfully";
	}	
}


}
