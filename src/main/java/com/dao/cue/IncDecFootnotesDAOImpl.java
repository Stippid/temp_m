package com.dao.cue;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.persistance.util.HibernateUtilNA;

public class IncDecFootnotesDAOImpl implements IncDecFootnotesDAO {
		
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	public String setApprovedINC(int appid,String username){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();	
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPE_PERS_FOOTNOTES  set status = :status,date_of_apprv_rejc =:date_of_apprv_rejc,aprv_rejc_by=:aprv_rejc_by  where id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).setString("date_of_apprv_rejc", date).setString("aprv_rejc_by", username).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setDeleteINC(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where id = :id";
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

	public CUE_TB_MISO_WEPE_PERS_FOOTNOTES getCUE_TB_MISO_WEPE_PERS_FOOTNOTESbyid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_PERS_FOOTNOTES list = (CUE_TB_MISO_WEPE_PERS_FOOTNOTES) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;	
	}

	public List<Map<String, Object>> AttributeReportSearchFootnotes(String we_pe,String status,String we_pe_no,String category_of_personnel,String rank_cat,String appt_trade,String rank,String roleType, String roleAccess) 
	 {				
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt =null;
				if(status != ""  && status != null && status !="null" ) {	
					if(!status.equals("all")){
						qry += " and a.status = ?";
					}
				}
				if(we_pe_no != "" && we_pe_no != null) {				
					 qry += " and  a.we_pe_no = ?";
				}
				if(!category_of_personnel.trim().equals("") ) {
					qry += " and a.category_of_personnel = ?";
				}
				if(!rank_cat.trim().equals("") && rank_cat.trim() != null && rank_cat.trim()!=""  && !rank_cat.trim().equals(null) ) {
					qry += " and a.rank_cat = ?";
					 
					}			
				if(appt_trade != "" && appt_trade != null && !appt_trade.equals("0") && appt_trade !="null") {				
					qry += " and rk1.description = ?";
					}
				if(rank != "" && rank != null && !rank.equals("0")) {				
					qry += " and a.rank = ?";
					
					}
					qry= " where "+qry.substring(4,qry.length());
			
			 q = 	" select  a.we_pe_no,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat, " + 
			 		" a.scenario, ba.auth_amt::int as base_amt, a.id, concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no,a.condition,a.status ,\r\n"
			 		+ "a.created_by as cr_by,a.created_on as cr_on,a.aprv_rejc_by as app_by,a.date_of_apprv_rejc as app_on,\r\n"
			 		+ "a.modified_by as modi_by,a.modified_on as modi_on   from cue_tb_miso_wepe_pers_footnotes a " + 
			 		" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active'  left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' " + 
			 		" inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text  inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text  " + 
			 		" inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  inner join t_domain_value dv1   on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.category_of_personnel " + 
			 		" inner join t_domain_value dv2  on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat " + 
			 		" left join cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = category_of_personnel " + 
			 		" and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no " + qry + "  order by a.we_pe_no, a.status ";
			
		      stmt=conn.prepareStatement(q);
		      int j=1;
		      if(status != ""  && status != null && status !="null" ) {	
					if(!status.equals("all")){
						stmt.setString(j, status);
						j+=1;
					}
				}
				if(we_pe_no != "" && we_pe_no != null) {				
					stmt.setString(j, we_pe_no);
					j+=1;
				}
				if(!category_of_personnel.trim().equals("") ) {
					stmt.setString(j, category_of_personnel);
					j+=1;
				}
				if(!rank_cat.trim().equals("") && rank_cat.trim() != null && rank_cat.trim()!=""  && !rank_cat.trim().equals(null) ) {
					stmt.setString(j, rank_cat);
					j+=1;
				}			
			
				if(appt_trade != "" && appt_trade != null && !appt_trade.equals("0") && appt_trade !="null") {				
					stmt.setString(j, appt_trade);
					j+=1;
				}
				if(rank != "" && rank != null && !rank.equals("0")) {				
					stmt.setString(j, rank);
					j+=1;
				}
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

	 	            for (int i = 1; i <= columnCount; i++) {
	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	 	            }
	 	            
	 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(10)+")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
					
					
					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(10)+");$('#rrr"+rs.getObject(10)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton ="<i id='rrr"+rs.getObject(10)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(10)+")}else{ return false;}\"";
					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
					
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(10)+")}else{ return false;}\"";
					String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
					
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					
					String f = "";
	 	  			if(status.equals("0")){
	 	  				if(roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}
						if(roleType.equals("APP")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
							}
						if(roleType.equals("DEO")) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
							}
						if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
  							f += appButton;
  							f += rejectButton;
  							f += LogButton;
  							}
  						if(  roleAccess.equals("Line_dte") ) {
  							f += deleteButton;
  							f += updateButton;
  						//	f += LogButton;
  							}
	 	      	    		
	 	  			}else if(status.equals("1")){
	 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
	 	  					f += rejectButton;
	 	  					f += LogButton1;
	 	  				}
	 	  					if(roleType.equals("DEO")){
	 	  						f += appButton1;
	 	  						f += LogButton1;
	 	  					}
	 	  				
	 	  			}else if(status.equals("2")){
	 	  				if(roleType.equals("APP") || roleType.equals("ALL")){
	 	  					f += appButton;	
	 	  				}
	 	  				if(roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")){
	 	  						
	 	  					f += deleteButton;
							f += updateButton;		
	 	  					}
	 	  					
	 	      				
	 	  				}
	 	  			
	 	  				columns.put(metaData.getColumnLabel(10), f);
	 	           
	 	            
	 	            list.add(columns);
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
		return list;
	}

	public List<Map<String, Object>> AttributeReportSearchFootnotes1(String we_pe_no,String category_of_personnel,String rank_cat,String appt_trade,String rank,String status,String roleType,String roleAccess ) {
		  {				
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;

				if(!status.equals("all")){
					qry += " and (a.status = '0' or a.status = '2' or a.status = '1')  ";
				}
				
				
				if(we_pe_no != "" && we_pe_no != null) {				
					 qry += " and  a.we_pe_no = ?";
				}
				if(!category_of_personnel.trim().equals("") ) {
					qry += " and a.category_of_personnel = ?";
					
				}
				if(!rank_cat.trim().equals("") && rank_cat.trim() != null && rank_cat.trim()!=""  && !rank_cat.trim().equals(null) ) {
					qry += " and a.rank_cat = ?";
					
					}			
			
				if(appt_trade != "" && appt_trade != null && !appt_trade.equals("0") && appt_trade !="null") {				
					qry += " and rk1.description = ?";
					
					}
				if(rank != "" && rank != null && !rank.equals("0")) {				
					qry += " and a.rank = ?";
					
					}
				
				if(qry !="")
					qry= " where "+qry.substring(4,qry.length());
				
				
			 q = 	" select  a.we_pe_no,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat, " + 
			 		" a.scenario, ba.auth_amt::int as base_amt, a.id, concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no,a.condition,a.status  from cue_tb_miso_wepe_pers_footnotes a " + 
			 		" left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active'  left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' " + 
			 		" inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text  inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text  " + 
			 		" inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  inner join t_domain_value dv1   on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.category_of_personnel " + 
			 		" inner join t_domain_value dv2  on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat " + 
			 		" left join cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = category_of_personnel " + 
			 		" and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no " + qry + "  order by a.we_pe_no, a.status ";
			
		       stmt=conn.prepareStatement(q);
		      int j=1;
		      if(we_pe_no != "" && we_pe_no != null) {				
		    	  stmt.setString(j, we_pe_no);
					j += 1;
				}
				if(!category_of_personnel.trim().equals("") ) {
					stmt.setString(j, category_of_personnel);
					j += 1;	
				}
				if(!rank_cat.trim().equals("") && rank_cat.trim() != null && rank_cat.trim()!=""  && !rank_cat.trim().equals(null) ) {
					stmt.setString(j, rank_cat);
					j += 1;
					}			
			
				if(appt_trade != "" && appt_trade != null && !appt_trade.equals("0") && appt_trade !="null") {				
					stmt.setString(j, appt_trade);
					j += 1;
					}
				if(rank != "" && rank != null && !rank.equals("0")) {				
					stmt.setString(j, rank);
					j += 1;
					}
				
				
		      ResultSet rs = stmt.executeQuery();      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
	 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

	 	            for (int i = 1; i <= columnCount; i++) {
	 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	 	            }
	 	            
	 	           String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(10)+")}else{ return false;}\"";
					String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
					
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(10)+")}else{ return false;}\"";
					String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
	 	  			String f = "";
	 	  			if(rs.getObject(15).equals("1")){
	 	  				if(roleType.equals("ALL")) {
							f += appButton;
							//f += rejectButton;
							f += deleteButton;
							f += updateButton;
							//f += LogButton;
						}
						if(roleType.equals("APP")) {
							f += appButton;
							f += deleteButton;
							f += updateButton;
														}
						if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
							//f += deleteButton;
							//f += updateButton;
							f += appButton;
							//f += LogButton;
							}
						if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
  							f += appButton;
  							//f += rejectButton;
  							//f += LogButton;
  							}	
	 	  			}
						
	 	  			else {
	 	  				
	 	  				f += deleteButton;
						f += updateButton;		
	 	  			}
						
	 	  			
	 	  				columns.put(metaData.getColumnLabel(10), f);
	 	           
	 	            
	 	            list.add(columns);
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
		return list;
	}
	  }
	
	
	public String updatepersonfootnotesvalue(CUE_TB_MISO_WEPE_PERS_FOOTNOTES footnotes) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "update CUE_TB_MISO_WEPE_PERS_FOOTNOTES  set location=:location,formation=:formation,amt_inc_dec=:amt_inc_dec, condition=:condition,scenario=:scenario, status='0' where id=:id";
	    Query query = session.createQuery(hql).setString("scenario", footnotes.getScenario()).setString("condition", footnotes.getCondition()).setString("location", footnotes.getLocation()).setString("formation", footnotes.getFormation()).setDouble("amt_inc_dec", footnotes.getAmt_inc_dec()).setInteger("id",footnotes.getId());
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
