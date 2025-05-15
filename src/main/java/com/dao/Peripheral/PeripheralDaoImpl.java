package com.dao.Peripheral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;

public class PeripheralDaoImpl  implements PeripheralDao{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> Search_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,HttpSession sessionUserId)
	{
	
	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no,unit_name,sessionUserId);
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";
	
		try{	
			String pageL = "";
	        
	        if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
		
			q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n" + 
					"ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
//					"inner join  tb_mstr_make mm on  mm.id=ap.make_name\r\n"+ 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'"
					+ "where ap.id!=0 "+ SearchValue +" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no,unit_name,sessionUserId);
			 ResultSet rs = stmt.executeQuery();   
		      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();			
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					
				}
	      
	      String CheckBox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//10
			+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
	      
	     
	      
	      String CheckBoxid="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//11
					+ "' value='" + rs.getObject(1) + "'   />";
	      
	      String chekboxaction="";
	      
	      chekboxaction+=CheckBox;
	      chekboxaction+=CheckBoxid;
	      
	  	columns.put("chekboxaction", chekboxaction);

				
				String f1 = "";				
				String f2 = "";
				String f3 = "";
				
			
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Information ?') ){viewData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					
					f3 = "<i class='fa fa-eye' " + View + " title='View Data'></i>";

				
			
				String action="";
					
				
				// raj & pranay 25.06   for edit approved data at miso level
				String roleAccesss = sessionUserId.getAttribute("roleAccess").toString();
				
											
				if (status == "1"  || status.equals("1")) {
					 if(roleAccesss.equals("MISO") || roleAccesss == "MISO" ){
					 columns.put("action",f1);//15
					 action+=f1;
					 action+=f2;
					 
					}
				}
				
				
			    if (!status.equals("1")) {
			    	action+=f1;
				}
				

				
				action+=f3;
				columns.put("action", action);
			
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
	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId){
    	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state, unit_sus_no, unit_name,sessionUserId);
	
	
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) "
					+ "from it_asset_peripherals ap\r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
//					"inner join  tb_mstr_make mm on  mm.id=ap.make_name\r\n"+ 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active' where ap.id!=0 "+ SearchValue ;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no, unit_name,sessionUserId);
	
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {  
				total = rs.getInt(1);
			}
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
		return (long) total;
	}


	
	public String GenerateQueryWhereClause_SQL2(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,String status,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId) {
		String SearchValue ="";
		
		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += " pt.assets_name like ? or upper(th.type_of_hw)  like ? or upper(ap.year_of_proc)  like ? or "
					+ " ap.year_of_manufacturing like ? "
					+ " or upper(ap.b_cost) like ? or upper(ap.machine_no) like ? or upper(ap.model_no) like ? or upper(ap.remarks) like ? )";
			
		}
		
				if(!status.equals(""))
				{
					SearchValue += "  and cast(ap.status as character varying) = ? ";
				}
			
				if(!assets_type.equals("0")) {
					SearchValue += "  and upper( cast(ap.assets_type as character varying)) = ? ";
				}	
				if(!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
					SearchValue += "  and cast(ap.year_of_manufacturing as character varying) = ? ";
				}		
				if(!machine_no.equals("")) { 
					SearchValue += "  and upper(ap.machine_no) like ? ";
				}			
				if (model_no !=null && !model_no.equals("")) {
					SearchValue += " and upper(ap.model_no) like ? ";
				}
				if(!from_date.equals("") && !to_date.equals(""))
				{
					SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
				}
				if(!s_state.equals("0")) {
					SearchValue += "  and cast(ap.s_state as character varying) = ? ";
				}
				 String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					if (roleAccess.equals("Unit") ) {
							SearchValue += "  and ap.sus_no = ? ";
					}
					if( !unit_sus_no.equals("")) {
						SearchValue += " and  orb.sus_no = ?";
						
					}
					if( !unit_name.equals("")) {
						SearchValue += " and  orb.unit_name = ? ";
						
					}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,String status,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId) {
		

		
		int flag = 0;
		try {
			if (!Search.equals("") || !Search.equals(null)) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				
				
			}
			
			if (!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}

			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			
			}
				if (!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
					flag +=1;
					stmt.setString(flag, year_of_manufacturing);
				}
			if (!machine_no.equals("")) {
				flag += 1;
				stmt.setString(flag, machine_no.toUpperCase() + "%");
				
			}
			if (model_no!=null && !model_no.equals("")) {
				flag += 1;
				stmt.setString(flag, model_no.toUpperCase() + "%");
				
			}
			if (!from_date.equals("") && !to_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
				flag += 1;
				stmt.setString(flag, to_date);
				
			}
			
			if (!s_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, s_state);
			}
			 String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
				if (roleAccess.equals("Unit") ) {
					flag += 1;
					stmt.setString(flag, roleSusNo);   
				}
				
				if(!unit_sus_no.equals("")) {
					flag += 1;
					
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				
				
				if(!unit_name.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_name.toUpperCase());
				}
			 
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	
	
	public String approve_AssetsData(String a,String user_sus,String status,String username) {
		String[] id_list = a.split(":");
		///BISAG V2 240822///
		String event_status= status;
		Connection conn = null;
		Integer app = 0;
		Date date = new Date();
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
				for (int i = 0; i < id_list.length; i++) {
					
					
					
					int id = Integer.parseInt(id_list[i]);
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 It_Asset_Peripherals assetupd =(It_Asset_Peripherals)sessionHQL.get(It_Asset_Peripherals.class, id);
					
					
					String hql = "update It_Asset_Peripherals set modified_by=:modified_by ,status=:status,modified_date=:modified_date where id=:id";
					if(event_status == "1" || event_status.equals("1"))
					{
						
						if(assetupd.getS_state() == "1" || assetupd.getS_state().equals("1"))
						{
							status="1";
						}
						
						if(assetupd.getS_state() == "2" || assetupd.getS_state().equals("2"))
						{
							if(assetupd.getUnserviceable_state() == 1)
							{
								status="4";
							}
							if(assetupd.getUnserviceable_state() == 3 || assetupd.getUnserviceable_state() == 2)
							{
								status="1";
							}
						}
					
					}
					if(event_status == "3" || event_status.equals("3"))
					{
						status="3";
					}
					///BISAG V2 240822 end///
					// Changes by Pranay 08-04-2025
					
					 app = sessionHQL.createQuery(hql).setInteger("status", Integer.parseInt(status))
							.setInteger("id", id).setString("modified_by", username).setTimestamp("modified_date", date).executeUpdate();
					
					 String hqlUpdate = "update TB_PERIPHERAL_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date  where p_id=:id ";
						int app1 = sessionHQL.createQuery(hqlUpdate).setInteger("status", Integer.parseInt(status)).setString("modified_by", username)
								.setDate("modified_date", date).setInteger("id", id).executeUpdate();
					
					
						PreparedStatement stmt1=null;
						 
						
						
						tx.commit();
						sessionHQL.close();
 
						}

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

		if (app > 0) {
			if(status.equals("1")) {
				return "Approved Successfully";
				}
				else if(status.equals("3")) {
					return "Rejected Successfully";
					}
				else
					return "UnSuccessfully";
			} else {
				if(status.equals("1")) {
					return "Approved not Successfully";
					}
				else if(status.equals("3")) {
					return "Rejected not Successfully";
					}
				else
				return "UnSuccessfully";
			}
	}
	

	public List<Map<String, Object>> getAppPeripheralList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,String status,HttpSession sessionUserId) throws SQLException
    {             
		
		String roleType = sessionUserId.getAttribute("roleType").toString();
    
    	String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
    	String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Connection conn = null;
            try{          
                    conn = dataSource.getConnection();     
                    PreparedStatement stmt =null;
                    String qry="";                
           
                    String pageL = "";
                   
                    if(pageLength == -1){
            			pageL = "ALL";
            		}else {
            			pageL = String.valueOf(pageLength);
            		}
                    
                    if(status.equals("0")) {
                    	qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
        						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,\r\n" + 
        						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
        						" td.label   as service_state,\r\n" + 
        						" td2.label   as unservice_state  ,\r\n" + 
        						" td.codevalue  as ser, ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" + 
        						" td2.codevalue   as unsv from it_asset_peripherals ap\r\n" + 
        						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " + 
        						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
        						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
        						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
        						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
        						" left join t_domain_value td2 on  td2.codevalue:: character varying = ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
        						"where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end) " + SearchValue+
        						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
        						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,ap.warranty,\r\n" + 
        						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id"
                         		+ " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage; 
    			
                    	stmt=conn.prepareStatement(qry);
                    	stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
                    
                    }
                    else {
                    qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
    						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,\r\n" + 
    						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
    						" td.label   as service_state,\r\n" + 
    						" td2.label   as unservice_state  ,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" + 
    						" td.codevalue  as ser, \r\n" + 
    						" td2.codevalue   as unsv, ch_am.id as ch_id from it_asset_peripherals ap\r\n" + 
    						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
    						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
    						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
    						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
    						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
    						" left join t_domain_value td2 on  td2.codevalue:: character varying =ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
    						"where ap.id!=0 and ap.status='1'" +  SearchValue1+" " +SearchValue+
    						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
    						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,ap.warranty,\r\n" + 
    						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id"
                     		+ " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage; 
                   
                    stmt=conn.prepareStatement(qry);
                   
        			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
        			stmt = setQueryWhereClause_SQL1(stmt,Search,status);
        			
                    }
			

			

		    ResultSet rs = null ;
		   try {
				rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();
	
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();			
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						
					}
					 String updateButton="";
						
					 String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("+ rs.getInt("id") +","+ rs.getInt("ch_id") + ")}else{ return false;}\"";
					 updateButton = "<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
					
					
						String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This Information ?') ){Approve("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						
						String ApproveButton = "<i class='action_icons action_approve' " + Approve + " title='Approve Data'></i>";
		
						
					     String Reject = "onclick=\"  if (confirm('Are You Sure You Want to Reject This Information ?') ){Reject("
									+ rs.getInt("id") + ")}else{ return false;}\"";
						 String RejectButton  = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

					
					
					 String f = "";
					 String ch_status ="1";
					 List<Map<String, Object>> ls = getPeriChildStatus( rs.getInt("id"));
					 if(ls.size() > 0)
					 {
						  ch_status = String.valueOf(ls.get(0).get("status")) ;
					 }
					 
					if (roleType.equals("DEO") && (status == "0"  || status.equals("0") || status == "3"  || status.equals("3"))) {
					
						 f += updateButton;
					}
					
					if (roleType.equals("DEO") && (status == "1"  || status.equals("1") )) {
						
						 f += updateButton;
					}
					
					if (roleType.equals("APP") && ((status == "0"  || status.equals("0")) && (ch_status == "0"  || ch_status.equals("0"))) ) {
						
						f += updateButton;
					
						 f += ApproveButton;
	                      f += RejectButton;
					}
					if (roleType.equals("APP") && ((status == "0"  || status.equals("0")) && (ch_status == "1"  || ch_status.equals("1"))) ) {
						
						f += updateButton;
					
						
					}
					
					if (roleType.equals("APP") && (status == "1"  || status.equals("1")) ) {
						
						f += updateButton;
					
						 
					}
					
					if (roleType.equals("APP") && (status == "3"  || status.equals("3")) ) {
						
						f += updateButton;
					
						 
					}
					columns.put("action", f);
					
					
					

					list.add(columns);
					
				}
				rs.close();
				stmt.close();
				conn.close();
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
	
	
	public long getAppPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,HttpSession sessionUserId)throws SQLException {
    	String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
    	String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);
		
		 PreparedStatement stmt =null;
		int total = 0;
		String q = null;
		Connection conn = null;
	
		try {
			conn = dataSource.getConnection();

			 if(status.equals("0")) {
             	q="select"+
             			" count(app.*)\r\n"
             			+ "from(select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
 						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,\r\n" + 
 						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
 						" td.label   as service_state,\r\n" + 
 						" td2.label   as unservice_state  ,\r\n" + 
 						" td.codevalue  as ser, ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" + 
 						" td2.codevalue   as unsv from it_asset_peripherals ap\r\n" + 
 						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " + 
 						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
 						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
 						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
 						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
 						" left join t_domain_value td2 on  td2.codevalue:: character varying = ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
 						"where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end) " + SearchValue+
 						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
 						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,ap.warranty,\r\n" + 
 						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id  ) app";
			
             	stmt=conn.prepareStatement(q);
            	stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
			 }
			
             	else {
                    q="select"
                    		+ 
                    		" count(app.*)\r\n" + 
                    		 "from(select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
    						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,\r\n" + 
    						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
    						" td.label   as service_state,\r\n" + 
    						" td2.label   as unservice_state  ,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" + 
    						" td.codevalue  as ser, \r\n" + 
    						" td2.codevalue   as unsv, ch_am.id as ch_id from it_asset_peripherals ap\r\n" + 
    						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
    						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
    						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
    						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
    						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
    						" left join t_domain_value td2 on  td2.codevalue:: character varying =ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
    						"where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end)" +  SearchValue1+" " +SearchValue+
    						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
    						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.model_no,ap.remarks,ap.warranty,\r\n" + 
    						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id) app";
                    		stmt=conn.prepareStatement(q);
                   
                   
        		
        			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,unit_sus_no,unit_name,from_date,to_date,status,sessionUserId);
        			stmt = setQueryWhereClause_SQL1(stmt,Search,status);
             	}
			

			
	
		
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {  
				total = rs.getInt(1);
			}
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
		return (long) total;
	}

	

	public String GenerateQueryWhereClause_SQL(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,String status,HttpSession sessionUserId) {
		String SearchValue ="";
		
		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += "cast(pt.assets_name as character varying) like ? or th.type_of_hw  like ? or ap.year_of_proc  like ? or ap.year_of_manufacturing like ? or ap.b_cost like ? or mm.make_name like ? or ap.machine_no like ? or upper(ap.model_no) like ? or upper(ap.remarks) like ? " + 
					" or coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'') like ? " + 
					" or upper(td.label) like ? " + 
					" or td2.label like ? " + 
					" or upper(td.codevalue) like ? " + 
					" or td2.codevalue like ? )";
			
		}
		
          if(!assets_type.equals("0")) {
        	  SearchValue += "  and cast(ap.assets_type as character varying) = ? ";
                }        
                if(!year_of_manufacturing.equals("")) {
                	SearchValue += "  and upper(ap.year_of_manufacturing)  like ? ";
                }                
                if(!machine_no.equals("")) {
                	SearchValue += "  and upper(ap.machine_no) like ? ";
                }                        

                if (!model_no.equals("")) {
                	SearchValue += " and upper(ap.model_no) like ? ";
                }
                if(!from_date.equals("") && !to_date.equals(""))
                {
                	SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
                }
                String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
    			if (roleAccess.equals("Unit") ) {
    					SearchValue += "  and ap.sus_no = ? ";
    			}
               
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String unit_sus_no,String unit_name,String from_date,String to_date,String status,HttpSession sessionUserId) {
		
		
		
		int flag = 0;
		if(status.equals("1"))
		{
			flag = 1;
		}
		
		try {
			if (!Search.equals("") || !Search.equals(null)) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				
				
			}

			

				if (!assets_type.equals("0")) {
					flag += 1;
					stmt.setString(flag, assets_type);
					
					
				}
				if (!year_of_manufacturing.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+  year_of_manufacturing + "%");
					
				}
				if (!machine_no.equals("")) {
					flag += 1;
					stmt.setString(flag, "%"+ machine_no.toUpperCase() + "%");
					
				}
				if (!model_no.equals("")) {
					flag += 1;
					stmt.setString(flag, "%"+ model_no.toUpperCase() + "%");
					
				}
				if (!from_date.equals("") && !to_date.equals("")) {
					flag += 1;
					stmt.setString(flag, from_date);
					flag += 1;
					stmt.setString(flag, to_date);
					
				}	
				 String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					if (roleAccess.equals("Unit") ) {
						flag += 1;
						stmt.setString(flag, roleSusNo);   
					}
				
		}catch (Exception e) {}
		return stmt;
	}
	
	
	public String GenerateQueryWhereClause_SQL1(String Search, String status) {
		String SearchValue1 ="";
		

        if(status == "0" ||  status.equals("0"))
        {
        	
        	SearchValue1="and ch_am.status = ? ";
        }
        
        if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
        {
        	  SearchValue1=" and ch_am.status = ? ";
        }
        
		return SearchValue1;
	}
	
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String status) {
		
		int flag = 0;

		try {
			if (!Search.equals("") || !Search.equals(null)) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
			}

			 if(status == "0" ||  status.equals("0"))
             {
				 stmt.setInt(1, Integer.parseInt(status) );
             }
			 
			 if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
	            {
	           	 stmt.setInt(1, Integer.parseInt(status) );
				
	            }
			 
			
		}catch (Exception e) {}
		return stmt;
	}





	public List<Map<String, Object>> getAppPeripheralChildList(String ch_id)
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		

			
			qry="select * from tb_peripheral_child where p_id=? order by id desc limit 1" ;
			
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,Integer.parseInt(ch_id));
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();
	
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {

			
					Map<String, Object> columns = new LinkedHashMap<String, Object>();			
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));

					}

					list.add(columns);
					
				}
				rs.close();
		    }catch (Exception e) {
				// TODO: handle exception
			}
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



	public List<Map<String, Object>> getPeriChildStatus (int id )
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
			
			qry="select status  from  tb_peripheral_child where p_id =? order by id desc limit 1"; 
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1, id);
		
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();			
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						
					}
					list.add(columns);
				}
				rs.close();
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
	
	
	



public List<Map<String, Object>> getAppChildPeriList(String p_id)
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
			
				qry="select * from it_asset_peripherals where status=0 and p_id=?" ;
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,Integer.parseInt(p_id));
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();
	
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();	
					
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));		
					}
					list.add(columns);
				}
				rs.close();
		    }catch (Exception e) {
				// TODO: handle exception
			}
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




public List<Map<String, Object>> GetdataPeri(int id) throws SQLException {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			q = "select warranty,unservice_state,service_state,unsv_from_dt,unsv_to_dt,is_networked,ip_address,"
					+ "connectivity_type,hw_interface,ethernet_port,management_layer,network_features from tb_peripheral_child where p_id=? and status=0";
	
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
	
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
	
				list.add(columns);
			}
	
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
		return list;
	}


public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id){
	
	ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select 	 array_to_string(ARRAY(select sub.hardware_interface \n" + 
				"from unnest(string_to_array((select hw_interface from it_asset_peripherals where id=nmk.id), ',')) qsub \n" + 
				" inner join TB_MSTR_HARDWARE_INTERFACE sub on sub.id=cast(qsub as integer)),',') as hw_inf \n" + 
				" from it_asset_peripherals  nmk where nmk.id=?";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		

		while (rs.next()) {
			
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("hw_inf"));
			list1.add(list);		
		}

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
	return list1;
}


public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id){
	
	ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";

	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select 	 array_to_string(ARRAY(select sub.network_features \n" + 
				"from unnest(string_to_array((select network_features from it_asset_peripherals where id=nmk.id), ',')) qsub\n" + 
				"inner join tb_mstr_network_features sub on sub.id=cast(qsub as integer)),',') as subject\n" + 
				"from it_asset_peripherals  nmk where nmk.id=?";

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		

		while (rs.next()) {
			
			ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("subject"));
			list1.add(list);	
		}

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
	return list1;
}

// bisag v4 18-11-2022 (delete assets)
public List<Map<String, Object>> SearchDelete_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
		String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
		String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,HttpSession sessionUserId)
{

String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no,unit_name,sessionUserId);
List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";

	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
	
		q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n" + 
				"ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n" + 
				"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
				"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
//				"inner join  tb_mstr_make mm on  mm.id=ap.make_name\r\n"+ 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'"
				+ "where ap.id!=0 "+ SearchValue +" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
		
		stmt=conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no,unit_name,sessionUserId);
		 ResultSet rs = stmt.executeQuery();   
	      
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();

		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				
			}
      
      String CheckBox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//10
		+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
      
     
      
      String CheckBoxid="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//11
				+ "' value='" + rs.getObject(1) + "'   />";
      
      String chekboxaction="";
      
      chekboxaction+=CheckBox;
      chekboxaction+=CheckBoxid;
      
  	columns.put("chekboxaction", chekboxaction);

			
			String f1 = "";				
			String f2 = "";
			String f3 = "";
			
		
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Information ?') ){viewData("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				
				f3 = "<i class='fa fa-eye' " + View + " title='View Data'></i>";

			
		
			String action="";
				
//		    if (!status.equals("1")) {
//		    	action+=f1;
//			}
			

			action+=f2;
//			action+=f3;
			columns.put("action", action);
		
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
public long getPeripheralCountListDelete(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
		String model_no,String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId){
	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state, unit_sus_no, unit_name,sessionUserId);


	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		q ="select count(*) "
				+ "from it_asset_peripherals ap\r\n" + 
				"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
				"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
//				"inner join  tb_mstr_make mm on  mm.id=ap.make_name\r\n"+ 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active' where ap.id!=0 "+ SearchValue ;

		PreparedStatement stmt = conn.prepareStatement(q);
		
		stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status,s_state,unit_sus_no, unit_name,sessionUserId);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {  
			total = rs.getInt(1);
		}
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
	return (long) total;
}

@Override
public List<Map<String, Object>> getDeletedPeripheralData(int startPage,int pageLength,String Search,String orderColunm,
		String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
		String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,HttpSession sessionUserId)
{

	String SearchValue = GenerateQueryWhereClause_SQL3(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,s_state,unit_sus_no,unit_name,sessionUserId);
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q="";

	try{
		String pageL = "";

		if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
		conn = dataSource.getConnection();
		PreparedStatement stmt=null;

		q="select ap.id, machine_no, ap.sus_no, ltrim(TO_CHAR(deleted_date,'DD-MON-YYYY'),'0') as deleted_date, deleted_by from tb_peripherals_deleted ap "
				+"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
				+"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'"
				+ "where ap.id != 0 "
				+ SearchValue
				+" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;

		stmt=conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL3(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,s_state,unit_sus_no,unit_name,sessionUserId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Peri Query --> " + stmt);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));

			}

			String f3 = "";

			String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Information ?') ){viewDeletedData("
					+ rs.getInt("id") + ")}else{ return false;}\"";
			f3 = "<i class='fa fa-eye' " + View + " title='View Data'></i>";

			String action="";

			action+=f3;
			columns.put("action", action);

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
@Override
public long getDeletedPeripheralCount(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
		String model_no,String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId){
	String SearchValue = GenerateQueryWhereClause_SQL3(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,s_state, unit_sus_no, unit_name,sessionUserId);


	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		q ="select count(app.*) from(select ap.id, machine_no, ap.sus_no, ltrim(TO_CHAR(deleted_date,'DD-MON-YYYY'),'0') as deleted_date, deleted_by from tb_peripherals_deleted ap "
				+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
				+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'"
				+ SearchValue
				+ ") app ";

		PreparedStatement stmt = conn.prepareStatement(q);

		stmt = setQueryWhereClause_SQL3(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,s_state,unit_sus_no, unit_name,sessionUserId);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			total = rs.getInt(1);
		}
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
	return total;
}






public String GenerateQueryWhereClause_SQL3(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
		String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId) {
	String SearchValue ="";

	if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
		SearchValue = " and ( ";
		SearchValue += " machine_no like ? or upper(ap.sus_no)  like ? or TO_CHAR(deleted_date, 'YYYY-MM-DD') like ? or deleted_by like ? )";
	}

	if(!assets_type.equals("0")) {
		SearchValue += "  and upper( cast(ap.assets_type as character varying)) = ? ";
	}
	if(!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
		SearchValue += "  and cast(ap.year_of_manufacturing as character varying) = ? ";
	}
	if(!machine_no.equals("")) {
		SearchValue += "  and upper(ap.machine_no) like ? ";
	}
	if (model_no !=null && !model_no.equals("")) {
		SearchValue += " and upper(ap.model_no) like ? ";
	}
	if(!from_date.equals("") && !to_date.equals(""))
	{
		SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
	}
	if(!s_state.equals("0")) {
		SearchValue += "  and cast(ap.s_state as character varying) = ? ";
	}
	String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	if (roleAccess.equals("Unit") ) {
		SearchValue += "  and ap.sus_no = ? ";
	}
	if( !unit_sus_no.equals("")) {
		SearchValue += " and  orb.sus_no = ?";

	}
	if( !unit_name.equals("")) {
		SearchValue += " and  orb.unit_name = ? ";

	}

	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
		String model_no,String from_date,String to_date,String s_state,String unit_sus_no,String unit_name,HttpSession sessionUserId) {

	int flag = 0;
	try {
		if (!Search.equals("") || !Search.equals(null)) {
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");

		}

		if (!assets_type.equals("0")) {
			flag += 1;
			stmt.setString(flag, assets_type);
		}
		if (!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
			flag +=1;
			stmt.setString(flag, year_of_manufacturing);
		}
		if (!machine_no.equals("")) {
			flag += 1;
			stmt.setString(flag, machine_no.toUpperCase() + "%");
		}
		if (model_no!=null && !model_no.equals("")) {
			flag += 1;
			stmt.setString(flag, model_no.toUpperCase() + "%");
		}
		if (!from_date.equals("") && !to_date.equals("")) {
			flag += 1;
			stmt.setString(flag, from_date);
			flag += 1;
			stmt.setString(flag, to_date);
		}

		if (!s_state.equals("0")) {
			flag += 1;
			stmt.setString(flag, s_state);
		}
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") ) {
			flag += 1;
			stmt.setString(flag, roleSusNo);
		}

		if(!unit_sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_sus_no.toUpperCase());
		}

		if(!unit_name.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_name.toUpperCase());
		}

	}catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
}

}