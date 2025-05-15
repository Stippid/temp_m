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
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.persistance.util.HibernateUtilNA;

public class ItemMasterDAOImpl implements ItemMasterDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	public String setApprovedItem(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_MMS_ITEM_MSTR c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "1" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Approved Successfully";
		}else {
			return "Approved not Successfully";
		}
	}
	
	public String setRejectItem(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_MMS_ITEM_MSTR c set c.status = :status where c.id = :id";
		int app = session.createQuery( hqlUpdate ).setString( "status", "2" ).setInteger( "id", appid ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Rejected Successfully";
		}else {
			return "Rejected not Successfully";
		}
	}
	
	public String setDeleteItem(int appid){		
		 	Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "update CUE_TB_MISO_MMS_ITEM_MSTR c set c.status_active ='Inactive' , c.softdelete = 'Inactive' where id=:id";
		    Query query = session.createQuery(hql).setInteger("id",appid);
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				return "Deleted Successfully";
			}else {
				return "Deleted not Successfully";
			}
	}
	
	public CUE_TB_MISO_MMS_ITEM_MSTR getCUE_TB_MISO_MMS_ITEM_MSTRid(int id) {
    	Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_MMS_ITEM_MSTR where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_MMS_ITEM_MSTR list = (CUE_TB_MISO_MMS_ITEM_MSTR) q.list().get(0);
		session.getTransaction().commit();
		session.close();		
		return list;
 }
	
	public String UpdateItemMasterValue(CUE_TB_MISO_MMS_ITEM_MSTR ItemMasterValue,String username){
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 	
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_MMS_ITEM_MSTR  set  item_type =:item_type, item_group =:item_group, category_code =:category_code,"
				+ " critical_equipment =:critical_equipment, engr_stores_origin =:engr_stores_origin, acc_units =:acc_units, class_item =:class_item,"
				+ " remarks =:remarks,modified_by=:modified_by,modified_on=:modified_on, status ='0',status_active='Active',nodal_dte =:nodal_dte where id=:id";
	    Query query = session.createQuery(hql).setString("item_type",ItemMasterValue.getItem_type()).
	    		setString("item_group",ItemMasterValue.getItem_group()).setString("category_code",ItemMasterValue.getCategory_code()).
	    		setString("critical_equipment",ItemMasterValue.getCritical_equipment()).
	    		setString("engr_stores_origin",ItemMasterValue.getEngr_stores_origin()).setString("acc_units",ItemMasterValue.getAcc_units()).
	    		setString("class_item",ItemMasterValue.getClass_item()).setString("remarks",ItemMasterValue.getRemarks()).
	    		setString("modified_by", username).setTimestamp("modified_on",new Date()).setString("nodal_dte",ItemMasterValue.getNodal_dte()).setInteger("id",ItemMasterValue.getId());
	    int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}
	}

	public @ResponseBody List<Map<String, Object>> getAttributeFromItemMaster(String prf_group, String item_type, String item_group, String class_item,String  status, String roleType){
		  {
			  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q="";
			String qry="";
			   try{	  
				conn = dataSource.getConnection();	
				 PreparedStatement stmt=null;
				if(!status.equals("all")){
					qry += " and (status = '0' or status = '2' or status = '1') and status_active='Active' ";
				}
				if(prf_group != "") {
					qry += " and prf_group = ?";
				}
				if(item_type != "") {			
					qry += " and item_type = ?";
				}
				if(!item_group.equals("0") && !item_group.equals("")) {
					qry += " and item_group = ?";
				}
				if(!class_item.equals("0") && !class_item.equals("")) {
					qry += " and class_item = ?";
				}
				if(qry !="")
					qry=" where "+qry.substring(4,qry.length());
				
			    q = " select distinct c.item_code,c.prf_group,c.item_type,c.item_group,c.class_item,c.status,c.id,m.line_dte_name,t.label from cue_tb_miso_mms_item_mstr c\r\n" + 
			    		"left join t_domain_value t on t.codevalue =c.item_group  and domainid='ITEMGROUP' " +
			    		"left join tb_miso_orbat_line_dte m on m.line_dte_sus =c.nodal_dte  "+ qry+ " order by status,prf_group,item_type ";
			    
			       stmt=conn.prepareStatement(q);
			       int j=1;
			       if(prf_group != "" && prf_group !=null) {
						stmt.setString(j, prf_group);
						j+=1;
					}
			       
			       if(item_type != "") {	
						stmt.setString(j, item_type);
						j+=1;
					}
			       
			       if(!item_group.equals("0") && !item_group.equals("")) {
						stmt.setString(j, item_group);
						j+=1;
						
					}
			       if(!class_item.equals("0") && !class_item.equals("")) {
						stmt.setString(j, class_item);
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
						
						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(7)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(7)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
		 	  			String f = "";
		 	  			if(rs.getObject(6).equals("1")){
								f += appButton;
							}
		 	  			else {
		 	  				f += deleteButton;
							f += updateButton;		
		 	  			}
							
		 	  			
		 	  				columns.put(metaData.getColumnLabel(7), f);
		 	           
		 	            
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
	
	public @ResponseBody List<Map<String, Object>> getAttributeFromItemMaster1(String prf_group,String  item_type,String  item_group,String  class_item,String  status, String roleType){
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
						qry += " and status = ?";
					}
					
				}		
			
				if(prf_group != "") {
					qry += " and prf_group = ?";
					
				}
				
				if(item_type != "") {			
					qry += " and item_type = ?";
					
				}
				if(!item_group.equals("0") && !item_group.equals("")) {
					
					qry += " and item_group = ?";
					
				}
				if(!class_item.equals("0") && !class_item.equals("")) {
					qry += " and class_item = ?";
					
				}
				
				if(qry !="")
					qry=" where "+qry.substring(4,qry.length());
				
			    q = " select distinct item_code,prf_group,item_type,item_group,class_item,status,id,\r\n"
			    		+ "	creation_by as cr_by,creation_date as cr_on,\r\n"
			    		+ "	modified_by as modi_by,modified_on as modi_on from CUE_TB_MISO_MMS_ITEM_MSTR "+ qry+ " order by status,prf_group,item_type ";
		        
			      stmt=conn.prepareStatement(q);
			      
			      int j = 1;
			       
			       if(status != ""  && status != null && status !="null" ) {	
						if(!status.equals("all")){
							stmt.setString(j, status);
							j += 1;
						}
						
					}			
				
					if(prf_group != "") {
						stmt.setString(j, prf_group);
						j += 1;	
					}
					
					if(item_type != "") {			
						stmt.setString(j, item_type);
						j += 1;
					}
					if(!item_group.equals("0") && !item_group.equals("")) {
						stmt.setString(j, item_group);
						j += 1;
						
					}
					if(!class_item.equals("0") && !class_item.equals("")) {
						stmt.setString(j, class_item);
					}
			      
			      ResultSet rs = stmt.executeQuery();      
			      ResultSetMetaData metaData = rs.getMetaData();

			      int columnCount = metaData.getColumnCount();
			      while (rs.next()) {
		 	            Map<String, Object> columns = new LinkedHashMap<String, Object>();

		 	            for (int i = 1; i <= columnCount; i++) {
		 	            	 columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	            }
		 	            String LogButton = cueContoller.Get_button_info(metaData,rs);
		                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
		 	           String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("+rs.getObject(7)+")}else{ return false;}\"";
						String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("+rs.getObject(7)+");$('#rrr"+rs.getObject(7)+"').attr('data-target','#rejectModal')}else{ return false;}\"";
						String rejectButton ="<i id='rrr"+rs.getObject(7)+"' class='action_icons action_reject' "+Reject+" title='Reject Data' data-toggle='modal'  ></i>";

						String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("+rs.getObject(7)+")}else{ return false;}\"";
						String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
						
						String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("+rs.getObject(7)+")}else{ return false;}\"";
						String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
						
						String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
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
		 	  				if(roleType.equals("DEO") || roleType.equals("ALL")){
		 	  						
		 	  					f += deleteButton;
								f += updateButton;		
		 	  					}
		 	  					
		 	      				
		 	  				}
		 	  			
		 	  				columns.put(metaData.getColumnLabel(7), f);
		 	           
		 	            
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
	
	
}
