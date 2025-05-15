package com.dao.cue;

import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.models.UserLogin;
import com.models.cue_wepe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_WE_PE_UploadDAOImpl  implements Cue_WE_PE_UploadDAO {

	NotificationController notification = new NotificationController();
	Posting_Out_Controller post = new Posting_Out_Controller();
	
	
	
	
	public String setApprovedARM(int appid, String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		String hqlUpdate = "update cue_wepe c set c.status = :status, approved_rejected_by=:approved_rejected_by, date_of_apprv_rejc=:date where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		
		cue_wepe notit = getlastwepeno(appid);
		List<UserLogin> userlist = post.getPostIN_outuseridlist(notit.getSponsor_dire());	
		
		//UserLogin  us_id= getnotuserid(notit.getSponsor_dire());
      
		
         String user_id = "";
      		for (int i = 0; i < userlist.size(); i++) {
      			if(i==0) {
      				user_id += 	String.valueOf(userlist.get(i).getUserId());
      			}
      			
      			else {
      				user_id += ","+String.valueOf(userlist.get(i).getUserId());;
      			}
      					
				}
		
		
		
      		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
				   String we_pe_no = notit.getWe_pe_no();
					
		           String title = "SD-6 Uploaded New WE/PE" ;
                 String description = "A new WE/PE is uploaded by SD-6 and WE No is "+we_pe_no+". Pl add data as per revised WE/PE" ;
				         Boolean d = notification.sendNotification(title, description,user_id,notit.getCreated_by());
      		}   
		if(app > 0) {
			return "Approved Successfully";
			
          
			
		}else {
			return "Approved not Successfully";
		}
	}

	
	
	private cue_wepe getlastwepeno(int appid) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		cue_wepe id = (cue_wepe) sessionHQL

				.get(cue_wepe.class, appid);

		sessionHQL.getTransaction().commit();

		sessionHQL.close();

		return id;

	}
	
	public String setDeleteARM(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from cue_wepe where id = :id";
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
	 
	public cue_wepe getcue_wepeByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from cue_wepe where id=:id");
		q.setParameter("id", id);
		cue_wepe list = (cue_wepe) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
	}
	
	public String UpdateArtAttValue(cue_wepe artAttValues, String username,String from_date,String to_date,String table_title) {
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update cue_wepe  set sponsor_dire=:sponsor_dire,suprcdd_we_pe_no=:suprcdd_we_pe_no,arm=:arm,eff_to_date=:eff_to_date,eff_frm_date=:eff_frm_date,table_title=:table_title,doc_type=:doc_type,doc_path=:doc_path,remarks=:remarks,modified_by=:modified_by,modified_on=:modified_on,status ='0' where id=:id";
	    Query query = session.createQuery(hql).setString("sponsor_dire",artAttValues.getSponsor_dire()).setString("doc_type",artAttValues.getDoc_type())
	    		.setString("suprcdd_we_pe_no",artAttValues.getSuprcdd_we_pe_no()).setString("arm",artAttValues.getArm())
	    		.setString("doc_path",artAttValues.getDoc_path()).setInteger("id",artAttValues.getId()).setString("modified_by", username)
	    		.setString("modified_on", modifydate).setString("remarks",artAttValues.getRemarks()).setString("eff_frm_date",from_date).setString("eff_to_date",to_date).setString("table_title",table_title);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}		
	}
		
	public String UpdateArtAttValue1(cue_wet_pet artAttValues) {
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

	public String UpdateArtAttValue1(cue_wepe artAttValues) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update cue_wepe  set sponsor_dire=:sponsor_dire,suprcdd_we_pe_no=:suprcdd_we_pe_no,arm=:arm,table_title=:table_title,status ='0',doc_type=:doc_type,doc_path=:doc_path where id=:id";
	    Query query = session.createQuery(hql).setString("suprcdd_we_pe_no",artAttValues.getSuprcdd_we_pe_no()).setString("sponsor_dire",artAttValues.getSponsor_dire()).setString("table_title",artAttValues.getTable_title()).setString("arm",artAttValues.getArm()).setString("doc_type",artAttValues.getDoc_type()).setString("doc_path",artAttValues.getDoc_path()).setInteger("id",artAttValues.getId());
	    
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}		
	}	
	
	//////////////dwnld img/////////////////////////////
	public List<cue_wepe> getWe_Pe_DownloadByid(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from cue_wepe where id=:id");
       q.setParameter("id", id);
       @SuppressWarnings("unchecked")
       List<cue_wepe> list = (List<cue_wepe>) q.list();
       tx.commit();
       session.close();
       return list;        
    }

}
