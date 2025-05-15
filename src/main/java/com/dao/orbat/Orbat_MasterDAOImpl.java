package com.dao.orbat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Tb_Miso_Orabt_Arm_Code;
import com.models.Tb_Miso_Orbat_Code;
import com.models.Tb_Miso_Orbat_Mast_Fmn;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Orbat_MasterDAOImpl implements Orbat_MasterDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public String setApprovedARM(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update Tb_Miso_Orabt_Arm_Code c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Data Approved Successfully";
		}else {
			return "Data not Approved";
		}
	}
	public String setRejectARM(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update Tb_Miso_Orabt_Arm_Code c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Data Rejected Successfully";
		}else {
			return "Data not Rejected";
		}
	}
	public String setDeleteARM(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Tb_Miso_Orabt_Arm_Code where id = :id";
	    Query query = session.createQuery(hql);
	    query.setInteger("id",appid);
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Data Deleted Successfully";
		}else {
			return "Data not Deleted";
		}
		
	}
	
	
	 public Tb_Miso_Orabt_Arm_Code getTb_Miso_Orabt_Arm_CodeByid(int id) {
	    	Session session = HibernateUtilNA.getSessionFactory().openSession();
			session.beginTransaction();
			Query q = session.createQuery("from Tb_Miso_Orabt_Arm_Code where id=:id");
			q.setParameter("id", id);
			Tb_Miso_Orabt_Arm_Code list = (Tb_Miso_Orabt_Arm_Code) q.list().get(0);
			session.getTransaction().commit();
			session.close();
			return list;
	 }
	 
	 
	 
	 public String Updatearmcode(Tb_Miso_Orabt_Arm_Code tb_miso,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Tb_Miso_Orabt_Arm_Code  set status='0',modified_by=:modified_by,modified_on=:modified_on,arm_desc=:arm_desc where id=:id and (status='0' or status='2')  ";
		int rowCount = 0;
		try {   
			Query query = session.createQuery(hql).setString("arm_desc", tb_miso.getArm_desc()).setString("modified_by", username).setDate("modified_on", new Date()).setInteger("id", tb_miso.getId());
		    rowCount = query.executeUpdate();
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			
		}finally {
			session.close();
		}
		if(rowCount > 0) {
			return "Data Updated Successfully";
		}else {
			return "Data not Updated";
		}
	}
	public String setApprovedTypeOfARM(int appid,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.status_record = :status_record ,approved_rejected_on=:approved_rejected_on,approved_rejected_by=:approved_rejected_by where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status_record", "1" ).setDate( "approved_rejected_on", new Date()).setString( "approved_rejected_by", username).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Data Approved Successfully";
		}else {
			return "Data not Approved";
		}
	}
		
		public String setRejectTypeOfARM(int appid){
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.status_record=:status_record where c.id =:id";
			int app = session.createQuery( hqlUpdate ).setString( "status_record", "2" ).setInteger( "id", appid ).executeUpdate();
			tx.commit();
			session.close();
			if(app > 0) {
				return "Data Rejected Successfully";
			}else {
				return "Data not Rejected";
			}
		}
		
		public String setDeleteTypeOfARM(int appid){
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "delete from Tb_Miso_Orbat_Code where id =:id";
		    Query query = session.createQuery(hql);
		    query.setInteger("id",appid);
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				return "Data Deleted Successfully";
			}else {
				return "Data not Deleted";
			}
			
		}
		
		public Tb_Miso_Orbat_Code getTb_Miso_Orbat_CodeByid(int id) {
		    	Session session = HibernateUtilNA.getSessionFactory().openSession();
				session.beginTransaction();
				Query q = session.createQuery("from Tb_Miso_Orbat_Code where id=:id");
				q.setParameter("id", id);
				Tb_Miso_Orbat_Code list = (Tb_Miso_Orbat_Code) q.list().get(0);
				session.getTransaction().commit();
				session.close();
				return list;
		 }

		public String setUpdateTypeOfARM(Tb_Miso_Orbat_Code ac){
				
			 	Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hql = "update Tb_Miso_Orbat_Code  set code_value=:code_value ,modified_on=:modified_on,modified_by=:modified_by , status_record='0' where id=:id";
			    Query query = session.createQuery(hql).setString("code_value", ac.getCode_value()).setString("modified_by", ac.getModified_by()).setDate("modified_on",ac.getModified_on()).setInteger("id",ac.getId());
			    int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if(rowCount > 0) {
					return "Data Updated Successfully";
				}else {
					return "Data not Updated";
				}
		}
		
		////
		
		public String setApprovedPARM(int appid,String username){
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
	    		String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.approved_rejected_on=:approved_rejected_on , c.approved_rejected_by=:approved_rejected_by , c.status_record=:status_record where c.id =:id and c.status_record='0'";
				sessionHQL.createQuery( hqlUpdate ).setDate("approved_rejected_on", new Date()).setString("approved_rejected_by", username).setString( "status_record", "1" ).setInteger( "id", appid ).executeUpdate();
				tx.commit();
				return "Data Approved Successfully";
	    	}catch(RuntimeException e){
    			tx.rollback();
    			return "Data not Approved";
	    	}finally{
	    		if(sessionHQL!=null){
	    			sessionHQL.close();
	    		}
	    	}
		}
		
		public String setRejectPARM(int appid){
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
	    		String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.status_record = :status_record where c.id = :id and c.status_record != '2'";
	    		sessionHQL.createQuery( hqlUpdate ).setString( "status_record", "2" ).setInteger( "id", appid ).executeUpdate();
	    		tx.commit();
	    		return "Data Rejected Successfully";
	    	}catch(RuntimeException e){
    			tx.rollback();
    			return "Data not Rejected";
	    	}finally{
	    		if(sessionHQL!=null){
	    			sessionHQL.close();
	    		}
	    	}
	    }
		
		public String setDeletePARM(int appid){
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
				String hql = "delete from Tb_Miso_Orbat_Code where id =:id and (status_record='0' or status_record='2')";
			    Query query = sessionHQL.createQuery(hql);
			    query.setInteger("id",appid);
			    query.executeUpdate();
				tx.commit();
				return "Data Deleted Successfully";
	    	}catch(RuntimeException e){
    			tx.rollback();
    			return "Data not Deleted";
	    	}finally{
	    		if(sessionHQL!=null){
	    			sessionHQL.close();
	    		}
	    	}	
		}
		
		public String UpdateParentARM(Tb_Miso_Orbat_Code artAttValues){
			Session sessionHQL = null;
		   	Transaction tx = null;
		   	try{
		   		sessionHQL = HibernateUtil.getSessionFactory().openSession();
		   		tx = sessionHQL.beginTransaction();
		   		String hql = "update Tb_Miso_Orbat_Code  set code_value=:code_value , status_record='0' where id=:id";
		   		Query query = sessionHQL.createQuery(hql).setString("code_value", artAttValues.getCode_value()).setInteger("id",artAttValues.getId());
		   		query.executeUpdate();
		   		tx.commit();
		   		return "Data Updated Successfully";
		   	}catch(RuntimeException e){
	    		tx.rollback();
				return "Data not Updated";
		   	}finally{
		   		if(sessionHQL!=null){
		   			sessionHQL.close();
		   		}
		   	}		
		}
		 ////
		 
		 public String setApprovedLOC_NRS(int appid,String username){
			 Session sessionHQL = null;
			 Transaction tx = null;
			 try{
			 	sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	tx = sessionHQL.beginTransaction();
			   	String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.status_record=:status_record ,approved_rejected_by=:approved_rejected_by,approved_rejected_on=:approved_rejected_on where c.id = :id and c.status_record='0'";
				sessionHQL.createQuery( hqlUpdate ).setString( "status_record", "1" ).setString("approved_rejected_by",username).setDate("approved_rejected_on", new Date()).setInteger( "id", appid ).executeUpdate();
				tx.commit();
				return "Data Approved Successfully";
			 }catch(RuntimeException e){
		    	tx.rollback();
		    	return "Data not Approved";
			 }finally{
			 	if(sessionHQL!=null){
			 		sessionHQL.close();
			 	}
			 }	
		}
			
		public String setRejectLOC_NRS(int appid){
			Session sessionHQL = null;
			Transaction tx = null;
			try{
			 	sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	tx = sessionHQL.beginTransaction();
			 	String hqlUpdate = "update Tb_Miso_Orbat_Code c set c.status_record=:status_record where c.id = :id and c.status_record != '2'";
			 	sessionHQL.createQuery( hqlUpdate ).setString( "status_record", "2" ).setInteger( "id", appid ).executeUpdate();
			 	tx.commit();
			 	return "Data Rejected Successfully";
			}catch(RuntimeException e){
			    tx.rollback();
			    return "Data not Rejected";
			}finally{
			 	if(sessionHQL!=null){
			 		sessionHQL.close();
			 	}
			}		
		}
			
		public String setDeleteLOC_NRS(int appid){
			Session sessionHQL = null;
			Transaction tx = null;
			try{
			 	sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	tx = sessionHQL.beginTransaction();
			 	String hql = "delete from Tb_Miso_Orbat_Code where id = :id and status_record='0'";
			 	Query query = sessionHQL.createQuery(hql);
			 	query.setInteger("id",appid);
			 	query.executeUpdate();
			 	tx.commit();
			 	return "Data Deleted Successfully";
			}catch(RuntimeException e){
			    tx.rollback();
			    return "Data not Deleted";
			}finally{
			 	if(sessionHQL!=null){
			 		sessionHQL.close();
			 	}
			}	
		}
		 
		public String UpdateLocAndNRS(Tb_Miso_Orbat_Code artAttValues,String username, String status_record){
			Session sessionHQL = null;
			Transaction tx = null;
			try{
			 	sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 	tx = sessionHQL.beginTransaction();
			 	String hql = "update Tb_Miso_Orbat_Code  set code_value=:code_value ,mod_desc=:mod_desc, nrs_code=:nrs_code, type_loc=:type_loc ,modified_on=:modified_on,modified_by=:modified_by, status_record =:status where id=:id";
//			 	Query query = sessionHQL.createQuery(hql).setString("code_value", artAttValues.getCode_value().toUpperCase()).setString("mod_desc",artAttValues.getMod_desc()).setString("modified_by",username).setDate("modified_on", new Date()).setString("nrs_code",artAttValues.getNrs_code().toUpperCase())
//			 			
//			 			.setString("type_loc",artAttValues.getType_loc())
//			 			.setInteger("id",artAttValues.getId());
			 	if (status_record.equals("1")) {
			 		Query query = sessionHQL.createQuery(hql).setString("code_value", artAttValues.getCode_value().toUpperCase()).setString("mod_desc",artAttValues.getMod_desc()).setString("modified_by",username).setDate("modified_on", new Date()).setString("nrs_code",artAttValues.getNrs_code().toUpperCase())
				 			.setString("type_loc",artAttValues.getType_loc())
				 			.setInteger("id",artAttValues.getId())
	 				.setString("status","1");
			 		query.executeUpdate();
	 			}else {
	 				Query query = sessionHQL.createQuery(hql).setString("code_value", artAttValues.getCode_value().toUpperCase()).setString("mod_desc",artAttValues.getMod_desc()).setString("modified_by",username).setDate("modified_on", new Date()).setString("nrs_code",artAttValues.getNrs_code().toUpperCase())
				 			.setString("type_loc",artAttValues.getType_loc())
				 			.setInteger("id",artAttValues.getId())
	 				.setString("status","0");
	 				query.executeUpdate();
	 			}
//			 	query.executeUpdate();
			 	tx.commit();
			 	return "Data Updated Successfully";
			}catch(RuntimeException e){
			    tx.rollback();
			    return "Data not Updated";
			}finally{
			 	if(sessionHQL!=null){
			 		sessionHQL.close();
			 	}
			}	 	
		}
			 
			 
	/// if Exist Methods 
	//if Exist Code Value in Tb_Miso_Orbat_Code
			
	@SuppressWarnings("unchecked")
	public Boolean ifExistCodeValue(String code_value,String code_type) {
		List<Tb_Miso_Orbat_Code> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql="from Tb_Miso_Orbat_Code where code_type=:code_type and code_value =:code_value";
			Query query= session.createQuery(hql);
			query.setParameter("code_value", code_value);
			query.setParameter("code_type", code_type);
			users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
			 
	@SuppressWarnings("unchecked")
	public Boolean ifExistCode(String code) {
		List<Tb_Miso_Orbat_Code> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql="from Tb_Miso_Orbat_Code where code_type='Parent Arm' and code =:code";
			Query query= session.createQuery(hql);
			query.setParameter("code", code);
			users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
			 
	@SuppressWarnings("unchecked")
	public Boolean ifExistCodeValueLocation(String code_value,int id, String code) {
		List<Tb_Miso_Orbat_Code> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query=null;
			if(id != 0) {
				query = session.createQuery("from Tb_Miso_Orbat_Code where code_type='Location' and code_value =:code_value and code=:code and id != :id");
				query.setParameter("id", id);
				
			}else {
				query = session.createQuery("from Tb_Miso_Orbat_Code where code_type='Location' and code=:code and code_value =:code_value");
			}
			query.setParameter("code_value", code_value);
			query.setParameter("code", code);
			users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
				 
	@SuppressWarnings("unchecked")
	public Boolean ifExistCodeLocation(String code) {
		List<Tb_Miso_Orbat_Code> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql="from Tb_Miso_Orbat_Code where code_type='Location' and code =:code";
			Query query= session.createQuery(hql);
			query.setParameter("code", code);
			users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
				 
				
	public List<String> getmaxprantCode(String p_code){
		List<String> whr= new ArrayList<>();
	    Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query="select min(ser) from generate_series(1,99) ser left join(select code,(right(code,2):: integer )as rightval from tb_miso_orbat_code where left(code,2)= ? and code_type= 'Type of Arm') as seq on ser = seq.rightval where rightval is null";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, p_code);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String whr1 = String.format("%02d", Integer.parseInt(rs.getString("min")));
				whr.add(whr1);
			}
			rs.close();
			stmt.close();
			conn.close();
        
		} catch (SQLException e) {
     		e.printStackTrace();
     	}	
        return whr;
        
        
	}
				
				
	@SuppressWarnings("unchecked")
	public Boolean ifExistArmCode_name(String a_name,String code,int id) {
		List<Tb_Miso_Orbat_Code> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query=null;
			if(id != 0) {
				query = session.createQuery("from Tb_Miso_Orabt_Arm_Code where (arm_code=:arm_code or arm_desc=:arm_desc) and id != :id");
				query.setParameter("id", id);
			}else {
				query = session.createQuery("from Tb_Miso_Orabt_Arm_Code where (arm_code=:arm_code or arm_desc=:arm_desc)");
			}
			query.setParameter("arm_code", code);
			query.setParameter("arm_desc", a_name);
			users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	
	//RAJ 28.10.2024
	
	

	//FMN APPROVE
	public String setApprovedFMN(int appid,String username){
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
    		String hqlUpdate = "update Tb_Miso_Orbat_Mast_Fmn c set c.approved_rejected_on=:approved_rejected_on , c.approved_rejected_by=:approved_rejected_by , c.status_record=:status_record where c.id =:id and c.status_record='0'";
			sessionHQL.createQuery( hqlUpdate ).setDate("approved_rejected_on", new Date()).setString("approved_rejected_by", username).setString( "status_record", "1" ).setInteger( "id", appid ).executeUpdate();
			tx.commit();
			return "Data Approved Successfully";
    	}catch(RuntimeException e){
			tx.rollback();
			return "Data not Approved";
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}
	}
	
	public String setRejectFMN(int appid,String username){
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
    		String hqlUpdate = "update Tb_Miso_Orbat_Mast_Fmn c set c.approved_rejected_on=:approved_rejected_on , c.approved_rejected_by=:approved_rejected_by , c.status_record = :status_record where c.id = :id and c.status_record != '2'";
    		sessionHQL.createQuery( hqlUpdate ).setDate("approved_rejected_on", new Date()).setString("approved_rejected_by", username).setString( "status_record", "2" ).setInteger( "id", appid ).executeUpdate();
    		tx.commit();
    		return "Data Rejected Successfully";
    	}catch(RuntimeException e){
			tx.rollback();
			return "Data not Rejected";
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}
    }
	
	public String setDeleteFMN(int appid){
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
			String hql = "delete from Tb_Miso_Orbat_Mast_Fmn where id =:id and (status_record='0' or status_record='2')";
		    Query query = sessionHQL.createQuery(hql);
		    query.setInteger("id",appid);
		    query.executeUpdate();
			tx.commit();
			return "Data Deleted Successfully";
    	}catch(RuntimeException e){
			tx.rollback();
			return "Data not Deleted";
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}	
	}
	
	public Tb_Miso_Orbat_Mast_Fmn getTb_Miso_Orbat_Mast_FmnByid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tb_Miso_Orbat_Mast_Fmn where id=:id");
		q.setParameter("id", id);
		Tb_Miso_Orbat_Mast_Fmn list = (Tb_Miso_Orbat_Mast_Fmn) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
 }
	
	public String UpdateFMN(Tb_Miso_Orbat_Mast_Fmn artAttValues,String username,String level_1,String level_2,String level_3,String level_4,
			String level_5,String level_6,String level_7,String level_8,String level_9,String level_10,String arm_code,String fmn_code,String fmn_name){
		Session sessionHQL = null;
	   	Transaction tx = null;
	   	try{
	   		int id = artAttValues.getId();
	   		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   		tx = sessionHQL.beginTransaction();
	   		String hql = "update Tb_Miso_Orbat_Mast_Fmn  set level1=:level1 ,level2=:level2 , level3=:level3 ,level4=:level4,level5=:level5,level6=:level6,level7=:level7,level8=:level8,level9=:level9,level10=:level10,fmn_code=:fmn_code ,fmn_name=:fmn_name ,arm_code=:arm_code ,modified_by=:modified_by ,modified_on=:modified_on ,status_record='0' where id=:id";
	   		Query query = sessionHQL.createQuery(hql).setString("level1", level_1).setString("level2", level_2).setString("level3",level_3).setString("level4", level_4).setString("level5", level_5).setString("level6", level_6).setString("level7", level_7).setString("level8", level_8).setString("level9", level_9).setString("level10", level_10)
	   				.setString("fmn_code", fmn_code).setString("fmn_name", fmn_name).setString("arm_code", arm_code).setDate("modified_on", new Date()).setString("modified_by", username).setInteger("id",artAttValues.getId());
	   		query.executeUpdate();
	   		tx.commit();
	   		return "Data Updated Successfully";
	   	}catch(RuntimeException e){
    		tx.rollback();
			return "Data not Updated";
	   	}finally{
	   		if(sessionHQL!=null){
	   			sessionHQL.close();
	   		}
	   	}		
	}
	
	
	//alredy exist fmn_code check 
	@SuppressWarnings("unchecked")
	public Boolean ifExistfmncode(String fmn_code) {
		List<Tb_Miso_Orbat_Mast_Fmn> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql="from Tb_Miso_Orbat_Mast_Fmn where fmn_code =:code";
			Query query= session.createQuery(hql);
			query.setParameter("code", fmn_code);
			users = (List<Tb_Miso_Orbat_Mast_Fmn>) query.list();
			tx.commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally{
		 	if(session!=null){
		 		session.close();
		 	}
		}	 
		if(users.size()>0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	
	//alredy exist fmn_name check 
		@SuppressWarnings("unchecked")
		public Boolean ifExistfmnname(String fmn_name) {
			List<Tb_Miso_Orbat_Mast_Fmn> users = null;
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try {
				String hql="from Tb_Miso_Orbat_Mast_Fmn where fmn_name =:name";
				Query query= session.createQuery(hql);
				query.setParameter("name", fmn_name);
				users = (List<Tb_Miso_Orbat_Mast_Fmn>) query.list();
				tx.commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
				return null;
			}finally{
			 	if(session!=null){
			 		session.close();
			 	}
			}	 
			if(users.size()>0)
			{
				return true;
			}else {
				return false;
			}
		}
}