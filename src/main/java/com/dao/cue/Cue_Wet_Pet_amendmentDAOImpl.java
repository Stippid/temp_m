

package com.dao.cue;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtilNA;

public class Cue_Wet_Pet_amendmentDAOImpl implements Cue_Wet_Pet_amendmentDAO  {
	
	public String setApprovedWet_Pet_amendment(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update cue_wet_pet c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteWet_Pet_amendment(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from cue_wet_pet where id = :id";
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
	
	public String UpdateWet_Pet_amendment(cue_wet_pet artAttValues) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update cue_wet_pet  set wet_pet=:wet_pet,wet_pet_no =:wet_pet_no ,sponsor_dire=:sponsor_dire,arm=:arm,doc_type=:doc_type,status ='0' where id=:id";
	    Query query = session.createQuery(hql).setString("wet_pet", artAttValues.getWet_pet()).setString("wet_pet_no",artAttValues.getWet_pet_no()).setString("sponsor_dire",artAttValues.getSponsor_dire()).setString("doc_type",artAttValues.getDoc_type()).setString("arm",artAttValues.getArm()).setInteger("id",artAttValues.getId());
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
