package com.dao.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.controller.notification.NotificationController;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.orbat.ReportsDAO;
import com.models.UserLogin;
import com.models.psg.Civilian_Master.TB_PSG_MSTR_CADRE_CIVILIAN;

import com.persistance.util.HibernateUtil;

public class notificatioDAOImpl implements notificatioDAO{
	
	@Autowired
	ReportsDAO  UOD;
	Psg_CommonController comm = new Psg_CommonController();
	NotificationController notification = new NotificationController();

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	
	public ArrayList<ArrayList<String>> getnotificationList(Integer userid)
	{

	 
	 
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "p.id !=  0";
			
		
				 q="SELECT * from tb_notification noti where ? in ( select unnest(string_to_array(ni.receiver_id, ',')) from tb_notification ni where ni.id=noti.id ) " + 
				 		"order by noti.id desc" ;
						 
				
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  
				int j =1;
			
					stmt.setString(1, String.valueOf(userid));
								
			
			}
			
	      ResultSet rs = stmt.executeQuery();   
	      
	     
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      int i = 0;
	      
	     
	      while (rs.next()) {
	    	 
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("title"));//0
	    	  list.add(rs.getString("description"));//1
	    	  list.add(rs.getString("receiver_id"));//2
	    	  list.add(rs.getString("seen_by"));//3
	    	  list.add(rs.getString("created_date"));//4
	    	  list.add(rs.getString("id"));//5
	    	  list.add(rs.getString("common_id"));//6
	    	
	    	  
				alist.add(list);
				
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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
	

public List<String> getUserIdForNotif(Integer module_id){
		
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String qry="";
		try{	  
			
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry +="Select user_id from public.userroleinformation where role_id in \r\n"
					+ "(select distinct roleid from tb_ldap_rolemaster where moduleid = ?  and roleid in \r\n"
					+ " (select role_id from public.roleinformation where role_type='ALL'))";
			
			stmt=conn.prepareStatement(qry);
			stmt.setInt(1, module_id);
			
			ResultSet rs = stmt.executeQuery();  
			  while (rs.next()) {
				  list.add(rs.getString("user_id"));
			  }
		
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}		
		return list;
	}
	}
public String getUnitNameFromUserId(int user_id) {
	Connection conn = null;
	String qry="";
	String unit_name = "";
	try{	  
		
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		qry +="select a.unit_name  as unit_name from public.tb_miso_orbat_unt_dtl a \r\n"
				+ "inner join public.logininformation b on a.sus_no = b.user_sus_no\r\n"
				+ "where b.userid= ?";
		
		stmt=conn.prepareStatement(qry);
		stmt.setInt(1, user_id);
		
		ResultSet rs = stmt.executeQuery();  
		  while (rs.next()) {
			  unit_name = rs.getString("unit_name");
		  }
	
      rs.close();
      stmt.close();
      conn.close();
   }catch (SQLException e) {
		//throw new RuntimeException(e);
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
		  }
		}		
	return unit_name;
}
}
public ArrayList<ArrayList<String>> getnotificationList_new(Integer userid)
{

 
 
ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
Connection conn = null;
String q="";
String qry="";

try{	  
	
	
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		qry = "p.id !=  0";
		
	
			 q="\r\n"
			 		+ "SELECT *,  ltrim(TO_CHAR(noti.created_date ,'DD-MON-YYYY'),'0') AS created_date_formated \r\n"
			 		+ "FROM tb_notification noti \r\n"
			 		+ "WHERE ? IN (\r\n"
			 		+ "    SELECT unnest(string_to_array(ni.receiver_id, ',')) \r\n"
			 		+ "    FROM tb_notification ni \r\n"
			 		+ "    WHERE ni.id = noti.id\r\n"
			 		+ ") \r\n"
			 		+ "AND(noti.created_date >= current_date - interval '1 month'\r\n"
			 		+ "or noti.created_date = current_date or noti.seen_by is null)\r\n"
			 		+ "ORDER BY noti.id DESC" ;
					 
			//System.err.println("notification   " + );
		stmt=conn.prepareStatement(q);
		
		if(!qry.equals(""))     
		{  
			int j =1;
		
				stmt.setString(1, String.valueOf(userid));
							
		
		}

      ResultSet rs = stmt.executeQuery();   
      System.err.println("notification   " + stmt);
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();
      int i = 0;
      
     
      while (rs.next()) {
    	 
    	  ArrayList<String> list = new ArrayList<String>();
    	  list.add(rs.getString("title"));//0
    	  list.add(rs.getString("description"));//1
    	  list.add(rs.getString("receiver_id"));//2
    	  list.add(rs.getString("seen_by"));//3
    	  list.add(rs.getString("created_date_formated"));//4
    	  list.add(rs.getString("id"));//5
    	  list.add(rs.getString("common_id"));//6
    	  
    	
    	  
			alist.add(list);
			
	        }
      rs.close();
      stmt.close();
      conn.close();
   }catch (SQLException e) {
		//throw new RuntimeException(e);
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

@Override
public String sendnotificationSDMOV() {

	String result="";
	
	 
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "p.id !=  0";
			
		
				 q="SELECT r.unit_status ,r.sd_status ,r.miso_status ,a.sus_no,r.imdt_fmn,a.unit_name\r\n"
				 		+ "FROM tb_miso_orbat_relief_prgm r \r\n"
				 		+ "inner join tb_miso_orbat_unt_dtl a on a.sus_no =r.sus_no and status_sus_no='Active'\r\n"
				 		+ "WHERE sd_status = '1' \r\n"
				 		+ "  AND (unit_status IS NULL OR unit_status = '' or unit_status ='0' or unit_status='1') \r\n"
				 		+ "  and (miso_status is null or miso_status = '')\r\n"
				 		+ "  AND CURRENT_DATE >= nmb_date::date + INTERVAL '7 days' and ( nmb_date IS NOT NULL \r\n"
				 		+ "  or nmb_date <> '')";
				 	
						 
				
			stmt=conn.prepareStatement(q);
		
			
	      ResultSet rs = stmt.executeQuery();   
	      
	     
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	   
	      
	     
	      while (rs.next()) {
	    	 
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("sus_no"));//0
	    	  list.add(rs.getString("imdt_fmn"));//1
	    	  list.add(rs.getString("unit_name"));//2
	    	 
	    	  if(rs.getString("sus_no")!="")
	    	  {
	    		  
	    		  List<Map<String, Object>> notit=UOD.getformationorbat(rs.getString("sus_no"));
			    	String cmd_sus_no = "";
			    	String corps_sus_no  = ""; 
			    	String div_sus_no = "";
			    	String bde_sus_no = "";
			    	 String title = "SD Relief Move Status :" ;
                     String description = "SD move of "+rs.getString("unit_name")+" has not been Updt. " ;
			    	if(notit.get(0).get("cmd")!=null) {
			    		cmd_sus_no = notit.get(0).get("cmd").toString();			
			    	}
			    	if(notit.get(0).get("corps")!=null) {
			    		corps_sus_no = notit.get(0).get("corps").toString();			
			    	}
			    	if(notit.get(0).get("div")!=null) {
			    		div_sus_no = notit.get(0).get("div").toString();			
			    	}
			    	if(notit.get(0).get("bde")!=null) {
			    		bde_sus_no = notit.get(0).get("bde").toString();			
			    	}
			    	

			    	 if (rs.getString("sus_no") != null   && !rs.getString("sus_no").trim().equals("")) {
			    	      List<UserLogin> userlist = getUseridlistRoleWise(rs.getString("sus_no"));
			    	                      String user_id = "";
			    	                for (int i = 0; i < userlist.size(); i++) {
			    	                if(i==0) {
			    	                user_id += userlist.get(i).getUserId();
			    	                }
			    	                
			    	                else {
			    	                user_id += ","+userlist.get(i).getUserId();
			    	                }
			    	                
			    	}
			    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
			    	    //String per_no = notit.get(0).get("personnel_no").toString();
			    	                     System.out.println("-*/-*/*/--*/-*/-*/-*/"+user_id );
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, "",0);
			    	                }
			    	      }
			    	      if (cmd_sus_no != null   && !cmd_sus_no.trim().equals("")) {
			    	      if( cmd_sus_no != rs.getString("sus_no") && !cmd_sus_no.trim().equals(rs.getString("sus_no"))) {
			    	      List<UserLogin> userlist = getUseridlistRoleWise(cmd_sus_no);
			    	                      String user_id = "";
			    	                for (int i = 0; i < userlist.size(); i++) {
			    	                if(i==0) {
			    	                user_id += userlist.get(i).getUserId();
			    	                }
			    	                
			    	                else {
			    	                user_id += ","+userlist.get(i).getUserId();
			    	                }
			    	                
			    	}
			    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
			    	    //String per_no = notit.get(0).get("personnel_no").toString();
			    	                	 
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, "",0);
			    	                }
			    	      }
			    	      }
			    	      if (div_sus_no != null   && !div_sus_no.trim().equals("")) { 
			    	      if( div_sus_no != rs.getString("sus_no") && !div_sus_no.trim().equals(rs.getString("sus_no"))) {
			    	      List<UserLogin> userlist = getUseridlistRoleWise(div_sus_no);
			    	                      String user_id = "";
			    	                for (int i = 0; i < userlist.size(); i++) {
			    	                if(i==0) {
			    	                user_id += userlist.get(i).getUserId();
			    	                }
			    	                
			    	                else {
			    	                user_id += ","+userlist.get(i).getUserId();
			    	                }
			    	                
			    	}
			    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
			    	    //String per_no = notit.get(0).get("personnel_no").toString();
			    	                	 
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, "",0);
			    	                }
			    	      }
			    	      }
			    	      if (bde_sus_no != null   && !bde_sus_no.trim().equals("")) {
			    	      if( bde_sus_no != rs.getString("sus_no") && !bde_sus_no.trim().equals(rs.getString("sus_no"))) {
			    	      
			    	      List<UserLogin> userlist = getUseridlistRoleWise(bde_sus_no);
			    	                      String user_id = "";
			    	                for (int i = 0; i < userlist.size(); i++) {
			    	                if(i==0) {
			    	                user_id += userlist.get(i).getUserId();
			    	                }
			    	                
			    	                else {
			    	                user_id += ","+userlist.get(i).getUserId();
			    	                }
			    	                
			    	}
			    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
			    	                	
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, "",0);
			    	                }
			    	      }
			    	      }
			    	      if (corps_sus_no != null   && !corps_sus_no.trim().equals("")) {
			    	      if( corps_sus_no != rs.getString("sus_no") && !corps_sus_no.trim().equals(rs.getString("sus_no"))) {
			    	                      List<UserLogin> userlist = getUseridlistRoleWise(corps_sus_no);
			    	                      String user_id = "";
			    	                for (int i = 0; i < userlist.size(); i++) {
			    	                if(i==0) {
			    	                user_id += userlist.get(i).getUserId();
			    	                }
			    	                
			    	                else {
			    	                user_id += ","+userlist.get(i).getUserId();
			    	                }
			    	                
			    	}
			    	                if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
			    	                	
			    	      Boolean d = notification.sendNotification_tms(title, description,user_id, "",0);
			    	                }
			    	      }
			    	      }
			    	
	    		  
	    	  }
	    	 
	    	  
	    	  alist.add(list);
				
	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return result;
}


public List<UserLogin> getUseridlistRoleWise(String user_sus_no) {

	
	 
ArrayList<UserLogin> alist = new ArrayList<UserLogin>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "p.id !=  0";
			
		
				 q="select * from logininformation \r\n"
				 		+ " where userid in( select user_id from userroleinformation where "
				 		+ "role_id in \r\n"
				 		+ "( select role_id from roleinformation where role ilike '%sdreliefapp%' or role ilike 'unit_app' or role ilike'unit_deo'))\r\n"
				 		+ "and user_sus_no=?";
				 	
						 
				
			stmt=conn.prepareStatement(q);
		stmt.setString(1, user_sus_no);
			
	      ResultSet rs = stmt.executeQuery();   
	      
	     
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	   
	      
	     
	      while (rs.next()) {
	    	  UserLogin ul= new UserLogin();
	    	  ul.setUserId(Integer.parseInt(rs.getString("userid")));//0
	    	
	    	  alist.add(ul);
				
	      } 
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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

}
