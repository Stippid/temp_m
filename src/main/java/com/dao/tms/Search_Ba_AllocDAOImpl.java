package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_CENSUS_DRR_DIR_MAIN;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_MAIN;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.Tms_Banum_Req_Child;
import com.models.Tms_Banum_Req_Prnt;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Search_Ba_AllocDAOImpl implements Search_Ba_AllocDAO {
	
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public  ArrayList<List<String>> getSearchBA_Alloc(String status,String sus_no,String type_of_req,String date,String veh_cat,String ba_no_type,String roleType,String roleAccess,String vehicle_clas_code)
  	{
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
		try{	  
		   conn = dataSource.getConnection();
		   PreparedStatement stmt =null;
		   String qry="";
			if(status != "") {
				qry += " and B.status = ?";
			}
			
			if(vehicle_clas_code != "") {
				qry += " and A.vehicle_clas_code = ?";
			}
			if(sus_no != "") {	
				
				qry += " and B.sus_no = ?";
			}		
			if(type_of_req != "") {		
				
				qry += " and B.type_of_request = ?";	
			}
			if(date != "") {			
				if(status.equals("19")) {
					qry += " and cast(A.modify_date as date) >= cast(? as date) ";
				}else if(status.equals("6")) {
					qry += " and cast(A.approve_date as date) >= cast(? as date) ";
				}else {
					qry += " and cast(A.creation_date as date) >= cast(? as date) ";
				}
					
			}
			if(veh_cat != "") {			
				
				qry += " and A.veh_cat = ?";
			}
			if(ba_no_type != "") {			
				
				qry += " and B.ba_no_type = ?";	
			}
		  
			String sql = "select 	distinct on (A.parent_req_id)A.parent_req_id,  \r\n" + 
					"	B.requesting_agency as req_agency, \r\n" + 
					"	A.id, \r\n" + 
					"	A.veh_cat as veh_cat,\r\n" + 
					"	B.ba_no_type as ba_no_type , \r\n" + 
					"	A.auth_letter_no as auth_letter_no, \r\n" + 
					"	A.quantity as quantity, \r\n" + 
					"	ltrim(TO_CHAR(A.approve_date,'dd-mm-yyyy'),'0') as approve_date , \r\n" + 
					"	B.status as status ,A.remarks \r\n" + 
					" from tb_tms_banum_req_child A \r\n" + 
					" inner join tb_tms_banum_req_prnt B on A.parent_req_id=B.parent_req_id \r\n"+
					" where A.old_ba_no is null and B.requesting_agency!='OTHERS' " +qry +" order by A.parent_req_id desc";
			stmt=conn.prepareStatement(sql);
			int j =1;
			if(status != "") {		
				stmt.setString(j, status);
				j += 1;	
			}
			if(vehicle_clas_code != ""){
				stmt.setString(j, vehicle_clas_code);
				j += 1;
			}
			if(sus_no != ""){	
				stmt.setString(j, sus_no);
				j += 1;
			}		
			if(type_of_req != "") {	
				stmt.setString(j, type_of_req);
				j += 1;	
			}
			if(date != "") {	
				stmt.setString(j, date);
				j += 1;
			}
			if(veh_cat != "") {	
				stmt.setString(j, veh_cat);
				j += 1;	
			}
			if(ba_no_type != "") {	
				stmt.setInt(j,Integer.parseInt(ba_no_type));
				j += 1;	
			}
			System.out.println("stmt=-===-==-=-=="+stmt);
		   ResultSet rs = stmt.executeQuery();
		   while(rs.next()){
	          List<String> list = new  ArrayList<String>();
	          list.add(rs.getString("parent_req_id"));//0
	    	  list.add(rs.getString("req_agency"));//1
	    	  list.add(rs.getString("id"));//2
	    	  list.add(rs.getString("veh_cat"));//3
	    	  if(rs.getString("ba_no_type") == "0" || rs.getString("ba_no_type").equals("0"))
	    	  {
	    		  list.add("Army");//4
	    	  }
	    	  else
	    	  {
	    		  list.add("Non Army");//4
	    	  }
	    	  list.add(rs.getString("auth_letter_no"));//5
	    	  list.add(rs.getString("quantity"));//6
	    	  list.add(rs.getString("approve_date"));//7
	    	  list.add(rs.getString("status"));//8
	    	  list.add(rs.getString("remarks"));//9
	    	  String f = "";
			   if(status.equals("0")){
				   if(roleType.equals("ALL")) {
						String Vetted = "onclick=\"  if (confirm('Are You Sure you want to Verified?') ){Vetted('"+ rs.getString("id") +"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
		 	    		f +="<i class='action_icons action_approve' "+Vetted+" title='Vetted BA No Allocation Details'></i>";
		 	    		String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete?') ){Delete1('"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_delete' "+Delete1+" title='Delete BA No Allocation Details'></i>";
	 	    			String View = "onclick=\"  if (confirm('Are You Sure you want to View Details?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
	 	    			String Rejected = "onclick=\"  if (confirm('Are You Sure you want to reject?') ){Rejected('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_reject' "+Rejected+" title='Reject BA No Allocation Details'></i>";

				   }
				   if(roleType.equals("APP")) {
					   
					   String Vetted = "onclick=\"  if (confirm('Are You Sure you want to Verified?') ){Vetted('"+ rs.getString("id") +"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
					   f +="<i class='action_icons action_approve' "+Vetted+" title='Vetted BA No Allocation Details'></i>";
					   String View = "onclick=\"  if (confirm('Are You Sure you want to View Details?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
					   f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
		 	 	   }
				   if(roleType.equals("DEO")) {
					   	String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){Delete1('"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_delete' "+Delete1+" title='Delete BA No Allocation Details'></i>";
				   }
			   }else if(status.equals("19")){
				   
				   if(roleType.equals("ALL") || roleType.equals("APP")) {
					   
					   if(roleAccess.equals("MISO")){
						   String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approved ?') ){Approved('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
						   f +="<i class='action_icons action_approve' "+Approved+" title='Approve BA No Allocation'></i>";
					   }
					   String View = "onclick=\"  if (confirm('Are You Sure you want to View Details ?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
					   f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
				   } if(roleType.equals("DEO")) {
					   String View = "onclick=\"  if (confirm('Are You Sure you want to View Details ?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
				   }
			   } else if(status.equals("6")){
				   if(roleType.equals("DEO") || roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("VETTING") ) {
						String View = "onclick=\"  if (confirm('Are You Sure you want to View Details ?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
			       }
			   }
			   else if(status.equals("2")){
				   if(roleType.equals("DEO") || roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("VETTING") ) {
						String View = "onclick=\"  if (confirm('Are You Sure you want to View Details ?') ){View('"+rs.getString("id")+"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
	 	    			f +="<i class='action_icons action_view' "+View+" title='View BA No Allocation Details'></i>";
	 	    			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update?') ){Update('"+ rs.getString("id") +"','"+rs.getString("parent_req_id")+"')}else{ return false;}\"";
						   f +="<i class='action_icons action_update' "+Update+" title='Update BA No Allocation Details'></i>";
			       }
			   }


			   list.add(f);
			   alist.add(list);
		   	}
		   	rs.close();
		   	stmt.close();
		   	conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return alist;
	}

    
    public  ArrayList<ArrayList<String>> getSearchBA_AllocPrint(String status,String sus_no,String type_of_req,String date,String veh_cat,String ba_no_type,String roleAccess,String vehicle_clas_code)
  	{
    	ArrayList<ArrayList<String>> printlist = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
			try{	  
			   conn = dataSource.getConnection();
			   String sql="";
		    String qry="";
			
			if(sus_no != "") {	
				qry += " and B.sus_no = ?";
			}
			if(vehicle_clas_code != "") {
				qry += " and A.vehicle_clas_code = ?";
			}
			if(type_of_req != "") {		
				qry += " and B.type_of_request = ?";	
			}
			if(date != "") {			
				if(status.equals("19")) {
					qry += " and cast(A.modify_date as date) >= cast(? as date) ";
				}else if(status.equals("6")) {
					qry += " and cast(A.approve_date as date) >= cast(? as date) ";
				}else {
					qry += " and cast(A.creation_date as date) >= cast(? as date) ";
				}
			}
			if(veh_cat != "") {			
				qry += " and A.veh_cat = ?";
			}
			if(ba_no_type != "") {			
				qry += " and B.ba_no_type = ?";	
			}
			
			sql ="select\r\n" + 
					"	B.requesting_agency as req_agency,\r\n" + 
					"	m.std_nomclature as nomen,\r\n" + 
					"	A.engine_no as engine,\r\n" + 
					"	A.chasis_no as chasis,\r\n" + 
					"	A.ba_no as ba_no  \r\n" + 
					"from tb_tms_banum_req_child A\r\n" + 
					"	inner join tb_tms_banum_req_prnt B on A.parent_req_id=B.parent_req_id  \r\n" + 
					"	left join tb_tms_mct_master m on m.mct = A.mct_number " +
					"  	where A.old_ba_no is null and B.status = '6' and B.requesting_agency!='OTHERS' "+qry+"" +
					" 	order by A.ba_no ";
			
			PreparedStatement stmt=conn.prepareStatement(sql);
		    stmt=conn.prepareStatement(sql);
		   int j =1;
		   
		   if(sus_no != "") {	
			   stmt.setString(j, sus_no);
			   j += 1;
		   }		
		   if(vehicle_clas_code != ""){
				stmt.setString(j, vehicle_clas_code);
				j += 1;
			}
		   if(type_of_req != "") {	
			   stmt.setString(j, type_of_req);
			   j += 1;	
		   }
		   if(date != "") {	
			stmt.setString(j, date);
			j += 1;
		}
		if(veh_cat != "") {	
				stmt.setString(j, veh_cat);
				j += 1;	
				 
			}
			if(ba_no_type != "") {	
				stmt.setInt(j,Integer.parseInt(ba_no_type));
				j += 1;	
				
			}
		   ResultSet rs = stmt.executeQuery();
			   while(rs.next()){
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("req_agency"));
		    	  list.add(rs.getString("nomen"));
		    	  list.add(rs.getString("engine"));
		    	  list.add(rs.getString("chasis"));
		    	  list.add(rs.getString("ba_no"));
		    	  printlist.add(list);	    
    	      }
    	      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		return printlist;
  	}
    
   public Tms_Banum_Req_Child getTms_Banum_Req_Childid(int id) {
	   Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tms_Banum_Req_Child where id=:id");
		q.setParameter("id", id);
		Tms_Banum_Req_Child list = (Tms_Banum_Req_Child) q.list().get(0);
		session.getTransaction().commit();
		session.close();			
		return list;
	}
	   
   public Tms_Banum_Req_Prnt getTms_Banum_Req_Prntid(int parent_req_id) {
	    Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tms_Banum_Req_Prnt where parent_req_id=:id");
		q.setParameter("id", parent_req_id);
		Tms_Banum_Req_Prnt list = (Tms_Banum_Req_Prnt) q.list().get(0);
		session.getTransaction().commit();
		session.close();			
		return list;
	}
	   
   public List<Tms_Banum_Req_Child> getTms_Banum_Req_engin_chasisid(int parent_req_id) {
	   Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Tms_Banum_Req_Child where parent_req_id=:parent_req_id ");
		q.setParameter("parent_req_id", parent_req_id);
		@SuppressWarnings("unchecked")
		List<Tms_Banum_Req_Child> list = (List<Tms_Banum_Req_Child>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	   
   public String getImagePath(int id,String fildname) {		
  	   	String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select * from tb_tms_banum_req_child where id=? ";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
	        while(rs.next()){
	           whr=rs.getString(fildname);             	
	        }
		    rs.close();
	    	stmt.close();
			conn.close();
    	} catch (SQLException e) {
    			e.printStackTrace();
    	}	
		return whr;
	}  
   
   public String updateReqChild(Tms_Banum_Req_Child updateid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update Tms_Banum_Req_Child c set "
				+ "c.appr_rej_remarks=:appr_rej_remarks , "
				+ "c.mct_number=:mct_number , "
				+ "c.modify_by=:modify_by , "
				+ "c.modify_date=:modify_date , "
				+ "c.type_of_fuel=:type_of_fuel , "
				+ "c.wheel_track=:wheel_track , "
				+ "c.no_of_wheels=:no_of_wheels , "
				+ "c.no_of_axles=:no_of_axles, "
				+ "c.drive=:drive , "
				+ "c.tonnage=:tonnage, "
				+ "c.towing_capcty=:towing_capcty, "
				+ "c.lift_capcty=:lift_capcty ,"
				+ "c.sponsoring_dte=:sponsoring_dte, "
				+ "c.version_no=:version_no,c.no_of_bogie_wheel=:no_of_bogie_wheel "
				+ "where c.parent_req_id = :parent_req_id";	
		int app = session.createQuery( hqlUpdate ).setString( "appr_rej_remarks", "Vetted" ).setInteger( "parent_req_id", updateid.getParent_req_id() ).setString("modify_by", updateid.getModify_by()).setDate("modify_date", updateid.getModify_date()).setBigInteger( "mct_number",updateid.getMct_number()).setString( "type_of_fuel",updateid.getType_of_fuel())
				.setString( "wheel_track",updateid.getWheel_track()).setInteger( "no_of_wheels",updateid.getNo_of_wheels()).setInteger( "no_of_axles",updateid.getNo_of_axles()).setString( "drive",updateid.getDrive()).setInteger( "tonnage",updateid.getTonnage()).setInteger( "towing_capcty",updateid.getTowing_capcty())
				.setInteger( "lift_capcty",updateid.getLift_capcty()).setInteger("version_no",3).setString( "sponsoring_dte",updateid.getSponsoring_dte()).setString("no_of_bogie_wheel", updateid.getNo_of_bogie_wheel()).executeUpdate();
		String hqlUpdate2 = "update Tms_Banum_Req_Prnt p set p.status=:status ,p.modify_by=:modify_by ,p.modify_date=:modify_date where p.parent_req_id = :parent_req_id";
		int app2 = session.createQuery( hqlUpdate2 ).setString( "status","19").setInteger( "parent_req_id",updateid.getParent_req_id() ).setString("modify_by", updateid.getModify_by()).setDate("modify_date", updateid.getModify_date()).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0 && app2 >0) {
			return "Verified Successfully.";
		}else {
			return "Not Verified Successfully.";
		}
	}
 
	public String setDeleteprfMst(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String retMsg = "";
		try
		{
			String hql = "delete from Tms_Banum_Req_Prnt where id = :id and status = :status";
		    Query query = session.createQuery(hql);
		    query.setInteger("id",appid);
		    query.setString("status","0");
		    int rowCount = query.executeUpdate();
			if(rowCount > 0)
			{
				String hql1 = "delete from Tms_Banum_Req_Child where parent_req_id = :id";
			    Query query1 = session.createQuery(hql1);
			    query1.setInteger("id",appid);
			    int rowCount1 = query1.executeUpdate();
				
				if(rowCount1 > 0) {
					tx.commit();
					retMsg = "Delete Successfully.";
					
					
				}else {
					retMsg = "Delete not Successfully.";
				}
			}
			else
			{
				retMsg = "Deleted not Successfully.";
			}
		}
		catch(Exception e)
		{
			retMsg = "Deleted not Successfully.";
			session.getTransaction().rollback();
			
		}
		finally
		{
			session.close();
		}
		return retMsg;
	}
   	
   	public String setApprovedprfMst(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PRF_Mst c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully.";
		}else {
			return "Approved not Successfully.";
		}
	}
	
	public String getTms_Banum_approve_upadte(int id,int prnt_id,Date date,String ba_no,int id_child,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update Tms_Banum_Req_Child c set c.ba_no = :ba_no, c.appr_rej_remarks = :appr_rej_remarks ,c.approved_by=:approved_by ,c.approve_date=:approve_date ,c.version_no =:version_no where c.id = :id";	
		int app = session.createQuery( hqlUpdate ).setString( "ba_no",ba_no ).setString( "appr_rej_remarks", "Approved" ).setString("approved_by", username).setDate("approve_date", date).setInteger( "version_no", 3 ).setInteger( "id", id ).executeUpdate();
		String hqlUpdate2 = "update Tms_Banum_Req_Prnt p set p.status = :status where p.parent_req_id = :parent_req_id and p.status = '19'";		
		int app2 = session.createQuery( hqlUpdate2 ).setString( "status","6").setInteger( "parent_req_id", prnt_id ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0 && app2 >0) {
			return "Approved Successfully.";
		}else {
			return "Not Approved Successfully.";
		}
	}
	
	public void insertDIR_DRR_DTL_Main_B_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no) {
		 String drr_dir_ser_no;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 drr_dir_ser_no = year + "-DRR-";
		 TB_TMS_DRR_DIR_MAIN main = new TB_TMS_DRR_DIR_MAIN();
		 main.setApproved_by(username);
		 main.setApprove_date(new Date());
		 main.setCreation_by(username);
		 main.setCreation_date(new Date());
		 main.setDt_of_retrn(new Date());
		 main.setStatus("1");
		 main.setDrr_dir_ser_no(drr_dir_ser_no);
		 main.setSus_no(im.getSus_no());
		 main.setVersion_no(0);
		 main.setType_of_stock("0");
		 Session sessionMain = HibernateUtil.getSessionFactory().openSession();
		 sessionMain.beginTransaction();
		 sessionMain.save(main);
		 sessionMain.getTransaction().commit();
		 sessionMain.close();
		 
		 
		 TB_TMS_DRR_DIR_DTL dtl = new TB_TMS_DRR_DIR_DTL();
		 dtl.setApproved_by(username);
		 dtl.setApprove_date(new Date());
		 dtl.setCreation_by(username);
		 dtl.setCreation_date(new Date());
		 dtl.setStatus("1");
		 dtl.setDrr_dir_ser_no(drr_dir_ser_no);
		 dtl.setSus_no(im.getSus_no());
		 dtl.setTyp_of_retrn("0");
		 dtl.setType_of_issue("1");
		 dtl.setBa_no(ba_no);
		 dtl.setKms_run(0);
		 dtl.setClassification("1");
		 dtl.setRemarks("");
		 dtl.setAuthority("");
		 dtl.setType_of_stock("0");
		 Session sessionDtl = HibernateUtil.getSessionFactory().openSession();
		 sessionDtl.beginTransaction();
		 sessionDtl.save(dtl);
		 sessionDtl.getTransaction().commit();
		 sessionDtl.close();
	}
	
	
	public void insertCencusDIR_DRR_DTL_main_A_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no) {
		 String drr_dir_ser_no;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 drr_dir_ser_no = year + "-DRR";
		 TB_TMS_CENSUS_DRR_DIR_MAIN main = new TB_TMS_CENSUS_DRR_DIR_MAIN();
		 main.setApproved_by(username);
		 main.setApprove_date(new Date());
		 main.setCreation_by(username);
		 main.setCreation_date(new Date());
		 main.setDt_of_retrn(new Date());
		 main.setStatus("1");
		 main.setDrr_dir_ser_no(drr_dir_ser_no);
		 main.setSus_no(im.getSus_no());
		 main.setType_of_stock("0");
	         
		 main.setVersion_no(0);	 
		 Session sessionMain = HibernateUtil.getSessionFactory().openSession();
		 sessionMain.beginTransaction();
		 sessionMain.save(main);
		 sessionMain.getTransaction().commit();
		 sessionMain.close();
		 
		 TB_TMS_CENSUS_DRR_DIR_DTL census_dtl = new TB_TMS_CENSUS_DRR_DIR_DTL();
		 census_dtl.setApproved_by(username);
		 census_dtl.setApproved_date(new Date());
		 census_dtl.setCreation_by(username);
		 census_dtl.setCreation_date(new Date());
		 census_dtl.setStatus("1");
		 census_dtl.setDrr_dir_ser_no(drr_dir_ser_no);
		 census_dtl.setSus_no(im.getSus_no());
		 census_dtl.setIssue_condition("1");
		 census_dtl.setIssue_agnst_rio("");	 
		 census_dtl.setBa_no(ba_no);
		 census_dtl.setKms_run(0);
		 census_dtl.setClassification("1");
		 census_dtl.setRemarks("");
		 census_dtl.setAuthority("");
		 census_dtl.setOther_agency("");
		 census_dtl.setType_of_stock("0");

		 
		 Session sessionDtl = HibernateUtil.getSessionFactory().openSession();
		 sessionDtl.beginTransaction();
		 sessionDtl.save(census_dtl);
		 sessionDtl.getTransaction().commit();
		 sessionDtl.close();
	}
	
	public void insertEMAR_DRR_DIR_DTL_main_C_veh(TB_TMS_BANUM_DIRCTRY im,String username,String ba_no) {
		// Add In DRR / DIR Table 
		 String drr_dir_ser_no;
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		 drr_dir_ser_no = year + "-DRR";
		 TB_TMS_EMAR_DRR_DIR_MAIN e = new TB_TMS_EMAR_DRR_DIR_MAIN();
		 e.setApproved_by(username);
		 e.setApprove_date(new Date());
		 e.setCreation_by(username);
		 e.setCreation_date(new Date());
		 e.setDt_of_retrn(new Date());
		 e.setStatus("1");
		 e.setDrr_dir_ser_no(drr_dir_ser_no);
		 e.setSus_no(im.getSus_no());
		
         e.setType_of_stock("0");
        
		 Session sessionMain = HibernateUtil.getSessionFactory().openSession();
		 sessionMain.beginTransaction();
		 sessionMain.save(e);
		 sessionMain.getTransaction().commit();
		 sessionMain.close();
		
		 TB_TMS_EMAR_DRR_DIR_DTL emar = new TB_TMS_EMAR_DRR_DIR_DTL();
		 BigInteger t = BigInteger.valueOf(0);

		 emar.setApproved_by(username);
		 emar.setApprove_date(new Date());
		 emar.setCreation_by(username);
		 emar.setCreation_date(new Date());
		 emar.setStatus("1");
		 emar.setDrr_dir_ser_no(drr_dir_ser_no);
		 emar.setSus_no(im.getSus_no());
		 emar.setIssue_condition("1");
		 emar.setIssue_agnst_rio("");
		 emar.setEm_no(ba_no);
		 emar.setKms_run(t);
		 emar.setClassification("1");
		 emar.setRemarks("");
		 emar.setAuthority("");
		 emar.setOther_agency("");
		 emar.setType_of_stock("0");
		 Session sessionDtl = HibernateUtil.getSessionFactory().openSession();
		 sessionDtl.beginTransaction();
		 sessionDtl.save(emar);
		 sessionDtl.getTransaction().commit();
		 sessionDtl.close();
	}
	
	public ArrayList<List<String>> getAttributeDataSearchallotmentofbanum1(String mct, String f_date, String t_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		String q = null;
		try {
			conn = dataSource.getConnection();

			q="select 	m.mct,m.std_nomclature as nomen,\r\n" + 
					"		(select count(ba_no) from tb_tms_banum_dirctry  where status='Active' and cast(mct as character varying) = ? and approve_date between cast(? as date) and cast(? as date)) as qty,\r\n" + 
					"		tb.ba_no \r\n" + 
					"	from \r\n" + 
					"	(select distinct b.id,b.ba_no from tb_tms_banum_dirctry b where status='Active' and cast(b.mct as character varying) = ? and approve_date between cast(? as date) and cast(? as date) ORDER BY ba_no ) tb \r\n" + 
					"	inner join tb_tms_mct_master m on cast(m.mct as character varying)= ? \r\n" + 
					"	\r\n" + 
					"	where tb.id = (SELECT id FROM tb_tms_banum_dirctry where status='Active' and cast(mct as character varying)= ? and approve_date between cast(? as date) and cast(? as date) ORDER BY ba_no  LIMIT 1) \r\n" + 
					"	   or tb.id = (SELECT id FROM tb_tms_banum_dirctry where status='Active' and cast(mct as character varying)= ? and approve_date between cast(? as date) and cast(? as date) ORDER BY ba_no DESC LIMIT 1)\r\n" + 
					"				 group by 1,2,3,4\r\n" + 
					"				 ORDER BY tb.ba_no ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, mct);
			stmt.setString(2, f_date);
			stmt.setString(3, t_date);

			stmt.setString(4, mct);
			stmt.setString(5, f_date);
			stmt.setString(6, t_date);

			stmt.setString(7, mct);

			stmt.setString(8, mct);
			stmt.setString(9, f_date);
			stmt.setString(10, t_date);

			stmt.setString(11, mct);
			stmt.setString(12, f_date);
			stmt.setString(13, t_date);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			List<String> list = new ArrayList<String>();
			list.add(rs.getString("nomen"));
			list.add(rs.getString("mct"));
			list.add(rs.getString("qty"));
			String ba_no = rs.getString("ba_no");
			rs.next();

			String ba_no1 = rs.getString("ba_no");
			String ba_no_list = "<b>From</b>&nbsp;&nbsp;" + ba_no + "&nbsp;&nbsp;<b>To</b>&nbsp;&nbsp;" + ba_no1;
			list.add(ba_no_list);
			alist.add(list);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return alist;
	}
	
	// get MCT Main Details from MCT first 4 digits
	 public List<TB_TMS_MCT_MAIN_MASTER> getTms_Banum_Req_engin_chasisid(String mct_main_id) {
		   Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:mct_main_id ");
			q.setParameter("mct_main_id", mct_main_id);
			@SuppressWarnings("unchecked")
			List<TB_TMS_MCT_MAIN_MASTER> list = (List<TB_TMS_MCT_MAIN_MASTER>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	 
	 public String editReqChild(Tms_Banum_Req_Child Updateid){
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update Tms_Banum_Req_Child c set "
					+ "c.appr_rej_remarks=:appr_rej_remarks , "
					+ "c.mct_number=:mct_number , "
					+ "c.modify_by=:modify_by , "
					+ "c.modify_date=:modify_date , "
					+ "c.type_of_fuel=:type_of_fuel , "
					+ "c.wheel_track=:wheel_track , "
					+ "c.no_of_wheels=:no_of_wheels , "
					+ "c.no_of_axles=:no_of_axles, "
					+ "c.drive=:drive , "
					+ "c.tonnage=:tonnage, "
					+ "c.towing_capcty=:towing_capcty, "
					+ "c.lift_capcty=:lift_capcty ,"
					+ "c.sponsoring_dte=:sponsoring_dte, "
					+ "c.version_no=:version_no,c.no_of_bogie_wheel=:no_of_bogie_wheel "
					+ "where c.parent_req_id = :parent_req_id";	
			int app = session.createQuery( hqlUpdate ).setString( "appr_rej_remarks", "Edited" ).setInteger( "parent_req_id", Updateid.getParent_req_id() ).setString("modify_by", Updateid.getModify_by()).setDate("modify_date", Updateid.getModify_date()).setBigInteger( "mct_number",Updateid.getMct_number()).setString( "type_of_fuel",Updateid.getType_of_fuel())
					.setString( "wheel_track",Updateid.getWheel_track()).setInteger( "no_of_wheels",Updateid.getNo_of_wheels()).setInteger( "no_of_axles",Updateid.getNo_of_axles()).setString( "drive",Updateid.getDrive()).setInteger( "tonnage",Updateid.getTonnage()).setInteger( "towing_capcty",Updateid.getTowing_capcty())
					.setInteger( "lift_capcty",Updateid.getLift_capcty()).setInteger("version_no",3).setString( "sponsoring_dte",Updateid.getSponsoring_dte()).setString("no_of_bogie_wheel", Updateid.getNo_of_bogie_wheel()).executeUpdate();
			String hqlUpdate2 = "update Tms_Banum_Req_Prnt p set p.status=:status ,p.modify_by=:modify_by ,p.modify_date=:modify_date where p.parent_req_id = :parent_req_id";
			int app2 = session.createQuery( hqlUpdate2 ).setString( "status","0").setInteger( "parent_req_id",Updateid.getParent_req_id() ).setString("modify_by", Updateid.getModify_by()).setDate("modify_date", Updateid.getModify_date()).executeUpdate();
			tx.commit();
			session.close();
			if(app > 0 && app2 >0) {
				return "Edited Successfully.";
			} else {
				return "Not Edited Successfully.";
			}
		}
	 public String rejectReqChild(Tms_Banum_Req_Child updateid){
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "update Tms_Banum_Req_Child c set "
					+ "c.appr_rej_remarks=:appr_rej_remarks , "
					+ "c.mct_number=:mct_number , "
					+ "c.modify_by=:modify_by , "
					+ "c.modify_date=:modify_date , "
					+ "c.type_of_fuel=:type_of_fuel , "
					+ "c.wheel_track=:wheel_track , "
					+ "c.no_of_wheels=:no_of_wheels , "
					+ "c.no_of_axles=:no_of_axles, "
					+ "c.drive=:drive , "
					+ "c.tonnage=:tonnage, "
					+ "c.towing_capcty=:towing_capcty, "
					+ "c.lift_capcty=:lift_capcty ,"
					+ "c.sponsoring_dte=:sponsoring_dte, "
					+ "c.version_no=:version_no,c.no_of_bogie_wheel=:no_of_bogie_wheel,c.remarks=:remarks "
					+ "where c.parent_req_id = :parent_req_id";	
			int app = session.createQuery( hqlUpdate ).setString( "appr_rej_remarks", "Rejected" ).setInteger( "parent_req_id", updateid.getParent_req_id() ).setString("modify_by", updateid.getModify_by()).setDate("modify_date", updateid.getModify_date()).setBigInteger( "mct_number",updateid.getMct_number()).setString( "type_of_fuel",updateid.getType_of_fuel())
					.setString( "wheel_track",updateid.getWheel_track()).setInteger( "no_of_wheels",updateid.getNo_of_wheels()).setInteger( "no_of_axles",updateid.getNo_of_axles()).setString( "drive",updateid.getDrive()).setInteger( "tonnage",updateid.getTonnage()).setInteger( "towing_capcty",updateid.getTowing_capcty())
					.setInteger( "lift_capcty",updateid.getLift_capcty()).setInteger("version_no",3).setString( "sponsoring_dte",updateid.getSponsoring_dte()).setString("no_of_bogie_wheel", updateid.getNo_of_bogie_wheel()).setString("remarks", updateid.getRemarks()).executeUpdate();
			String hqlUpdate2 = "update Tms_Banum_Req_Prnt p set p.status=:status ,p.modify_by=:modify_by ,p.modify_date=:modify_date where p.parent_req_id = :parent_req_id";
			int app2 = session.createQuery( hqlUpdate2 ).setString( "status","2").setInteger( "parent_req_id",updateid.getParent_req_id() ).setString("modify_by", updateid.getModify_by()).setDate("modify_date", updateid.getModify_date()).executeUpdate();
			tx.commit();
			session.close();
			if(app > 0 && app2 >0) {
				return "Rejected Successfully.";
			}else {
				return "Not Rejected Successfully.";
			}
		}
	 
}