package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class MNH_DataStatusDAOImpl implements MNH_DataStatusDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> search_hospital_datastatus(String sus1,String cmd1,String yr1,String role,String userId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			if(role.equals("mnh")) {
			
				if(!sus1.equals("")){
					qry += " and b.medical_unit = ?";
				}else {
					if(!cmd1.equals("ALL")){
						qry += " and substr(a.form_code_control,?,?)=?";
				 	} 
				}
				
				if(!yr1.equals("")) {
					qry += " and substring(b.admsn_date::text,?,?)=?";
				}
			
				q = "select distinct a.sus_no,a.unit_name,"+
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '01') as jan," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '02') as feb," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '03') as mar," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '04') as apr," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '05') as may," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '06') as jun," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '07') as jul," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '08') as aug," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '09') as sep," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '10') as oct," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '11') as nov," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '12') as dec," + 
			    		" count(b.medical_unit) as total"+
			    		" from tb_med_patient_details b"+
			    		" left join orbat_all_details_view_mnh a on a.sus_no=b.medical_unit"+
			    		" left join tb_med_hosp_assign h on h.sus_no=a.sus_no "+
			    		" left join logininformation l on l.userid=h.user_id "+
			    		" left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control"+ 
			    		" where a.status_sus_no='Active' " +qry+
			    		" group by a.sus_no,a.unit_name";
				stmt=conn.prepareStatement(q);   	
			    
			    stmt.setInt(1, 1);
			    stmt.setInt(2, 1);
			    stmt.setString(3, "000000000");

			    
			    int j = 4;
			    if(!sus1.equals("")){
			    	stmt.setString(j, sus1);
			    	j++;
			    }else {
			    	if(!cmd1.equals("ALL")){
			    		stmt.setInt(j, 1);
			    		j++;
					    stmt.setInt(j, 1);
					    j++;
				    	stmt.setString(j, cmd1.substring(0,1));
				    	j++;
				    }
			    }
			    
			    if(!yr1.equals("")) {
			    	stmt.setInt(j, 1);
			    	j++;
			    	stmt.setInt(j, 4);
			    	j++;
			    	stmt.setString(j, yr1);
			    	j++;
			    }
			}
		
			else
			{
			
				if(!sus1.equals("")){
					qry += " and b.medical_unit = ?";
				}else {
					if(!cmd1.equals("ALL")){
						qry += " and substr(a.form_code_control,?,?)=?";
				 	} 
				}
				
				if(!yr1.equals("")) {
					qry += " and substring(b.admsn_date::text,?,?)=?";
				}
				if(!userId.equals("")) {
					if(!role.equals("mnh_gso1")) {
						qry += " and cast (h.user_id as integer) = ? ";
					}
}
				q = "select distinct a.sus_no,a.unit_name,"+
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '01') as jan," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '02') as feb," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '03') as mar," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '04') as apr," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '05') as may," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '06') as jun," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '07') as jul," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '08') as aug," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '09') as sep," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '10') as oct," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '11') as nov," + 
			    		" count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '12') as dec," + 
			    		" count(b.medical_unit) as total"+
			    		" from tb_med_patient_details b"+
			    		" left join orbat_all_details_view_mnh a on a.sus_no=b.medical_unit"+
			    		" left join tb_med_hosp_assign h on h.sus_no=a.sus_no "+
			    		" left join logininformation l on l.userid=h.user_id "+
			    		" left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control"+ 
			    		" where a.status_sus_no='Active' " +qry+
			    		" group by a.sus_no,a.unit_name";
			
				stmt=conn.prepareStatement(q);   	
			    
			    stmt.setInt(1, 1);
			    stmt.setInt(2, 1);
			    stmt.setString(3, "000000000");
			  //  stmt.setString(4, userId);

			    
			    int j = 4;
			    if(!sus1.equals("")){
			    	stmt.setString(j, sus1);
			    	j++;
			    }else {
			    	if(!cmd1.equals("ALL")){
			    		stmt.setInt(j, 1);
			    		j++;
					    stmt.setInt(j, 1);
					    j++;
				    	stmt.setString(j, cmd1.substring(0,1));
				    	j++;
				    }
			    }
			    
			    if(!yr1.equals("")) {
			    	stmt.setInt(j, 1);
			    	j++;
			    	stmt.setInt(j, 4);
			    	j++;
			    	stmt.setString(j, yr1);
			    	j++;
			    }
			    if(!userId.equals("")) {
			    	if(!role.equals("mnh_gso1")) {
			    		stmt.setInt(j, Integer.parseInt(userId));
				    	j++;
			    	}
			    	
			    
			    }
			}
		    
	
		     	
		    ResultSet rs = stmt.executeQuery();   
		   System.err.println(stmt);
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
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
	}

	public List<Map<String, Object>> search_approval_datastatus(String cmd1, String user1, String mth_yr1,
			String para1,String sus1, String frm_dt1,String to_dt1) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			if(para1.equalsIgnoreCase("UNIT")) {
				if(!sus1.equals("")){
					qry += " and a.medical_unit = ?";
				}else {
					if(!cmd1.equals("ALL")){
						qry += " and substr(b.form_code_control,?,?)=?";
				 	} 
				}
			}
			if(para1.equalsIgnoreCase("MISO")) {
				if(!sus1.equals("")){
					qry += " and a.medical_unit = ?";
				}
			
				if(!user1.equals("-1")) {
					qry += " and l.username=?";
				} 
			}
			
			if(!mth_yr1.equals("")) {
				qry += " and substring(cast(a.admsn_date as text),?,?)=?";
			}
		
			if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
				qry += " and a.admsn_date BETWEEN DATE(?)";
		 		
		 		if(!to_dt1.equals("") && !to_dt1.equals(null)){
			 		qry += " and DATE(?)"; 		
			 	} 
		 	} 

			
			if(para1.equalsIgnoreCase("UNIT")) {
				
				/*q = "select l.username,a.medical_unit,b.unit_name,count(a.apprvr_at_unit_by) as app,count(a.rejection_reason) filter (where (a.rejection_reason != '') and (a.rejection_reason is not null)) as reject,\r\n" + 
						" (count(*) - count(a.apprvr_at_unit_by)) as total_bal,\r\n" + 
						" count(a.*) filter (where (a.disposal = '' or a.disposal is null) and  ( a.dschrg_date is null)) as still,count(*) as total \r\n" + 
						" from tb_med_patient_details a \r\n" + 
						" left join tb_miso_orbat_unt_dtl b on b.sus_no=a.medical_unit "
						+ " left join nrv_hq c on concat(substring(b.form_code_control,?,?),?)=c.form_code_control"
						+ " left join tb_med_hosp_assign d on d.sus_no = a.medical_unit"
						+ " inner join logininformation l on l.userid = d.user_id"
						+ " where b.status_sus_no='Active' and substring(b.sus_no,?,?) in (?,?,?,?,?,?,?,?,?) "+qry
						+ " group by b.unit_name,a.medical_unit,l.username  order by l.username,b.unit_name";*/
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				q = "select l.username,a.medical_unit,b.unit_name,count(a.apprvr_at_unit_by) as app,count(a.rejection_reason) filter (where (a.rejection_reason != '') and (a.rejection_reason is not null)) as reject,\r\n" + 
						" (count(a.apprvr_at_unit_by is not null)) as total_bal,\r\n" + 
						" count(a.*) filter (where (a.disposal = '' or a.disposal is null) and  ( a.dschrg_date is null)) as still,count(*) as total \r\n" + 
						" from tb_med_patient_details a \r\n" + 
						" left join orbat_all_details_view_mnh b on b.sus_no=a.medical_unit "
						+ " left join nrv_hq c on concat(substring(b.form_code_control,?,?),?)=c.form_code_control"
						+ " left join tb_med_hosp_assign d on d.sus_no = a.medical_unit"
						+ " left join logininformation l on l.userid = d.user_id"
						+ " where b.status_sus_no='Active' and (a.apprvr_at_unit_by is not null or a.apprvr_at_unit_by !='')  "+qry
						+ " group by b.unit_name,a.medical_unit,l.username  order by l.username,b.unit_name";
				
						}
	
			
			else if(para1.equalsIgnoreCase("MISO")) {
				
				
				/*q = "select l.username,a.medical_unit,b.unit_name,count(a.apprvr_at_miso_by) as app,count(a.rejection_reason) filter (where (a.rejection_reason != '') and (a.rejection_reason is not null)) as reject,\r\n" + 
				" (count(*) - (count(a.apprvr_at_miso_by))) \r\n" + 
				" as total_bal,count(a.*) filter (where (a.disposal = '' or a.disposal is null) and  ( a.dschrg_date is null)) as still,count(*) as total \r\n" + 
				" from tb_med_patient_details a \r\n" + 
				" left join tb_miso_orbat_unt_dtl b on b.sus_no=a.medical_unit"
				+ " left join nrv_hq c on concat(substring(b.form_code_control,?,?),?)=c.form_code_control"
				+ " left join tb_med_hosp_assign d on d.sus_no = a.medical_unit"
				+ " inner join logininformation l on l.userid = d.user_id"
				+ " where b.status_sus_no='Active' and substring(b.sus_no,?,?) in (?,?,?,?,?,?,?,?,?) "+qry
				+ " group by b.unit_name,a.medical_unit,l.username  order by l.username,b.unit_name";*/
				
				q = "select l.username,a.medical_unit,b.unit_name,count(a.apprvr_at_miso_by) as app,count(a.rejection_reason) filter (where (a.rejection_reason != '') and (a.rejection_reason is not null)) as reject,\r\n" + 
						" (count(a.apprvr_at_miso_by is not null)) \r\n" + 
						" as total_bal,count(a.*) filter (where (a.disposal = '' or a.disposal is null) and  ( a.dschrg_date is null)) as still,count(*) as total \r\n" + 
						" from tb_med_patient_details a \r\n" + 
						" left join orbat_all_details_view_mnh b on b.sus_no=a.medical_unit"
						+ " left join nrv_hq c on concat(substring(b.form_code_control,?,?),?)=c.form_code_control"
						+ " left join tb_med_hosp_assign d on d.sus_no = a.medical_unit"
						+ " left join logininformation l on l.userid = d.user_id"
						+ " where b.status_sus_no='Active' and (a.apprvr_at_miso_by is not null and a.apprvr_at_miso_by !='')"+qry
						+ " group by b.unit_name,a.medical_unit,l.username  order by l.username,b.unit_name";
				
		}
			 
			stmt=conn.prepareStatement(q);   	
		   
		    stmt.setInt(1, 1);
		    stmt.setInt(2, 1);
		    stmt.setString(3, "000000000");
		    
		   /* stmt.setInt(4, 1);
	    	stmt.setInt(5, 4);
	    	stmt.setString(6, "9609");
	    	stmt.setString(7, "9709");
	    	stmt.setString(8, "3742");
	    	stmt.setString(9, "6323");
	    	stmt.setString(10, "3755");
	    	stmt.setString(11, "3738");
	    	stmt.setString(12, "3735");
	    	stmt.setString(13, "3747");
	    	stmt.setString(14, "1234");*/
		    
	    	int j = 4;
		    if(para1.equalsIgnoreCase("UNIT")) {
		    	if(!sus1.equals("")){
		    		stmt.setString(j, sus1);
				    j++;
				}else{
					if(!cmd1.equals("ALL")){
						stmt.setInt(j, 1);
				    	j++;
						stmt.setInt(j, 1);
						j++;
					    stmt.setString(j, cmd1.substring(0,1));
					    j++;
					}
				}
		    }
		    if(para1.equalsIgnoreCase("MISO")) {
		    	if(!sus1.equals("")){
		    		stmt.setString(j, sus1);
				    j++;
				}
		    
		    
           
            	if(!user1.equals("-1")) {
            		stmt.setString(j, user1);
    	    		j++;
            	}
			}
		    
		    if(!mth_yr1.equals("")) {
		    	stmt.setInt(j, 0);
	    		j++;
			    stmt.setInt(j, 8);
			    j++;
			    stmt.setString(j, mth_yr1);
		    	j++;
		    }
		    if(!frm_dt1.equals("") && !frm_dt1.equals(null)){
		 		stmt.setString(j, frm_dt1);
		 		j++;
		 	} 
			if(!to_dt1.equals("") && !to_dt1.equals(null)){
		 		stmt.setString(j, to_dt1);
		 		j++;
		 	}
		    ResultSet rs = stmt.executeQuery();     
		    System.err.println(stmt);
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
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
	}

	public List<Map<String, Object>> search_datastatus(String sus1, String frm_dt1, String to_dt1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";

			if (!sus1.equals("")) {
				qry += " and b.medical_unit = ?";
				flag = "N";
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " and to_char(b.apprvd_at_miso_on, 'YYYY-MM-DD') >= ?";
					flag = "N";
				} else {
					qry += " and to_char(b.apprvd_at_miso_on, 'YYYY-MM-DD') >= ?";
				}
			}
			if (!to_dt1.equals("") && !to_dt1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " and to_char(b.apprvd_at_miso_on, 'YYYY-MM-DD') <= ?";
					flag = "N";
				} else {
					qry += " and to_char(b.apprvd_at_miso_on, 'YYYY-MM-DD') <= ?";
				}
			}

			/*
			 * q=
			 * " SELECT ltrim(TO_CHAR(b.apprvd_at_miso_on ,'DD-MM-YYYY'),'0') as apprvd_at_miso_on,a.unit_name,b.apprvr_at_miso_by,count(b.apprvr_at_miso_by) \r\n"
			 * + " FROM tb_med_patient_details b \r\n" +
			 * " left join tb_miso_orbat_unt_dtl a on a.sus_no=b.medical_unit\r\n" +
			 * " where b.apprvr_at_miso_by is not null and b.apprvd_at_miso_on is not null and a.sus_no is not null "
			 * + qry+ " group by 1,2,3" + "order by apprvd_at_miso_on desc";
			 */

			q = " SELECT distinct ltrim(TO_CHAR(b.apprvd_at_miso_on ,'DD-MM-YYYY'),'0') as apprvd_at_miso_on,b.apprvr_at_miso_by,count(b.apprvr_at_miso_by) \r\n"
					+ " FROM tb_med_patient_details b \r\n"
					+ " left join orbat_all_details_view_mnh a on a.sus_no=b.medical_unit\r\n"
					+ " where b.apprvr_at_miso_by is not null and b.apprvd_at_miso_on is not null and a.sus_no is not null "
					+ qry + " group by 1,2" + "order by apprvd_at_miso_on desc";

			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}

			if (!frm_dt1.equals("") && !frm_dt1.equals(null)) {
				stmt.setString(j, frm_dt1);
				j++;
			}

			if (!to_dt1.equals("") && !to_dt1.equals(null)) {
				stmt.setString(j, to_dt1);
				j++;
			}

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
}
