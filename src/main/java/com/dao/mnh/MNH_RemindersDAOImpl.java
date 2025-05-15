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

public class MNH_RemindersDAOImpl implements MNH_RemindersDAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> search_reminder_input(String dt_frm, String dt_to, String qtr,
			String tbl_name,String cmd1,String yr1) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		String tbl="";
		String unit="";
		
		try{
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			String flag = "Y";
			
			if(tbl_name.equalsIgnoreCase("A&D")) {
				tbl = "tb_med_patient_details";
				unit = "medical_unit";
			}else if(tbl_name.equalsIgnoreCase("OPD")) {
				tbl=" tb_med_opdcases ";	
				unit = "sus_no";
			}else if(tbl_name.equalsIgnoreCase("BO")) {
				tbl=" tb_med_daily_bed_occupancy ";	
				unit = "sus_no";
			}else if(tbl_name.equalsIgnoreCase("OSP")) {
				tbl=" tb_med_opd_spl ";	
				unit = "sus_no";
			}else if(tbl_name.equalsIgnoreCase("Mortality")) {
				tbl=" tb_med_death ";	
				unit = "sus_no";
			}else if(tbl_name.equalsIgnoreCase("IMB")) {
				tbl=" tb_med_imb ";	
				unit = "sus_no";
			}
			
			if(tbl_name.equalsIgnoreCase("A&D")) {
				qry += " where admsn_date BETWEEN DATE(?) and DATE(?)";	
			}
			
			if(tbl_name.equalsIgnoreCase("OPD")) {
				qry += " where qtr=? and year=?";
			}
			if(tbl_name.equalsIgnoreCase("BO")) {
				
			}
			if(tbl_name.equalsIgnoreCase("OSP")) {
				qry += " where quater=? and yr=?";
			}
			if(tbl_name.equalsIgnoreCase("Mortality")) {
				
			}
			if(tbl_name.equalsIgnoreCase("IMB")) {
				
			}
			
			if(!cmd1.equals("ALL")){
				qry1 += " and substr(form_code_control,?,?)=?";
		 	} 
			
			/*q = "select distinct sus_no,unit_name from tb_miso_orbat_unt_dtl where status_sus_no='Active' and substring(sus_no,?,?)"
					+ "in (?,?,?,?,?,?,?,?,?) and sus_no not in"
					+ "(select distinct "+unit+" from "+tbl+" "+qry+") "+qry1
					+ "order by sus_no,unit_name"; 
			*/
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			
			q = "select distinct sus_no,unit_name from orbat_all_details_view_mnh  where status_sus_no='Active' and "
					+ " sus_no not in"
					+ " (select distinct "+unit+" from "+tbl+" "+qry+") "+qry1
					+ " order by sus_no,unit_name"; 
		   
			stmt=conn.prepareStatement(q);   
		   
		    /*stmt.setInt(1, 1);
	    	stmt.setInt(2, 4);
	    	stmt.setString(3, "9609");
	    	stmt.setString(4, "9709");
	    	stmt.setString(5, "3742");
	    	stmt.setString(6, "6323");
	    	stmt.setString(7, "3755");
	    	stmt.setString(8, "3738");
	    	stmt.setString(9, "3735");
	    	stmt.setString(10, "3747");
	    	stmt.setString(11, "1234");*/
	    	
	    	int j = 1;
	    	if(tbl_name.equalsIgnoreCase("A&D")) {
	    		stmt.setString(j, dt_frm);
	    		j++;
		    	stmt.setString(j, dt_to);
		    	j++;
		    }
	    	
	    	if(tbl_name.equalsIgnoreCase("OPD")) {
	    		stmt.setString(j, qtr);
	    		j++;
	    		stmt.setInt(j, Integer.parseInt(yr1));
	    		j++;
	    	}
	    	
	    	if(tbl_name.equalsIgnoreCase("OSP")) {
	    		stmt.setString(j, qtr);
	    		j++;
	    		stmt.setString(j, yr1);
	    		j++;
	    	}
	    	
	    	if(!cmd1.equals("ALL")){
	    		stmt.setInt(j, 1);
	    		j++;
			    stmt.setInt(j, 1);
			    j++;
		    	stmt.setString(j, cmd1.substring(0,1));
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

	public List<Map<String, Object>> search_reminder_discharge(String sus1, String cmd1, String dt_frm, String dt_to) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		
		try{
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			String flag = "Y";
			 
			if(!sus1.equals("")){
				qry += " and b.medical_unit = ?";
			}else {
				if(!cmd1.equals("ALL")){
					qry += " and substr(a.form_code_control,?,?)=?";
			 	} 
			}
			
			if(!dt_frm.equals("") && !dt_to.equals("")){
				qry += " and admsn_date BETWEEN DATE(?) and DATE(?)";
			}
			
			
			q = "select b.and_no,b.name,to_char(b.admsn_date,'DD-MM-YYYY') as admsn_date,b.persnl_no,b.persnl_name,b.rank,b.discharge_remarks,b.diagnosis_code1d,"
					+ "b.icd_cause_code1d,to_char(b.dschrg_date,'DD-MM-YYYY') as dschrg_date,b.disposal from tb_med_patient_details b"
					+ " left join orbat_all_details_view_mnh a on a.sus_no = b.medical_unit"
					+ " left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control"
					+ " where a.status_sus_no='Active' and (b.dschrg_date is null) and (b.disposal is null or b.disposal = '')"+qry
					+ " order by admsn_date,and_no";
			
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
		    
		    if(!dt_frm.equals("") && !dt_to.equals("")){
		    	stmt.setString(j, dt_frm);
		    	j++;
		    	stmt.setString(j, dt_to);
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
	
	//==
	
	public List<Map<String, Object>> search_scrutiny_discharge(String dschrg_date1,String disposal1,String diagnosis_code1a1,String icd_cause_code1a1,String discharge_remarks1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		
		try{
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			String flag = "Y";
			
			if(!dschrg_date1.equals("")){ 
				qry += " where  to_char(dschrg_date, 'YYYY-MM-DD') = ?";	
			}
			if(!disposal1.equals("")){
				
				qry += " and Upper(disposal) like ?";
			}
			if(!diagnosis_code1a1.equals("")){
				qry += " and (lower(diagnosis_code1a) like ? or Upper(diagnosis_code1a) like ?)";				
			}
			if(!icd_cause_code1a1.equals("")){
				qry += " and (lower(icd_cause_code1a) like ? or Upper(icd_cause_code1a) like ?)";	
			}
			if(!discharge_remarks1.equals("")){
			    qry += " and (lower(discharge_remarks) like ? or Upper(discharge_remarks) like ?)";	 
			}
		
			q = "select id,ltrim(TO_CHAR(dschrg_date ,'DD-MM-YYYY'),'0') as dschrg_date,disposal,diagnosis_code1a,icd_cause_code1a, discharge_remarks from tb_med_patient_details "+qry;
		    stmt=conn.prepareStatement(q); 
		    
		    int j = 1;
		    if(!dschrg_date1.equals("") ){
		    	stmt.setString(j, dschrg_date1);
		    	j++;
		    }
		    if(!disposal1.equals("")){
		    	/*stmt.setString(j, '%'+disposal1.toLowerCase()+'%');		    	
		    	j++;*/
		    	stmt.setString(j, '%'+disposal1.toUpperCase()+'%');
		    	j++;
		    }
		    if(!diagnosis_code1a1.equals("")){
		    	stmt.setString(j, '%'+diagnosis_code1a1.toLowerCase()+'%');		    	
		    	j++;
		    	stmt.setString(j, '%'+diagnosis_code1a1.toUpperCase()+'%');
		    	j++;
		    }
		    if(!icd_cause_code1a1.equals("")){
		    	stmt.setString(j, '%'+icd_cause_code1a1.toLowerCase()+'%');		    	
		    	j++;
		    	stmt.setString(j, '%'+icd_cause_code1a1.toUpperCase()+'%');
		    	j++;
		    }
		    if(!discharge_remarks1.equals("")){
		    	stmt.setString(j, '%'+discharge_remarks1.toLowerCase()+'%');		 
		    	j++;
		    	stmt.setString(j, '%'+discharge_remarks1.toUpperCase()+'%');
		    	j++;
		    }
	   	
		    ResultSet rs = stmt.executeQuery();      
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for(int i = 1; i <= columnCount; i++){
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	    String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getObject(2)+"','"+rs.getObject(3)+"','"+rs.getObject(4)+"','"+rs.getObject(5)+"','"+rs.getObject(6)+"')}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
				String f = "";
				f += updateButton;
				
 				columns.put(metaData.getColumnLabel(1), f);
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

	public ArrayList<ArrayList<String>> Search_discharge(String sus1, String cmd1, String dt_frm, String dt_to)
	{
	 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		
		try{
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			String flag = "Y";
			 
			if(!sus1.equals("")){
				qry += " and b.medical_unit = ?";
			}else {
				if(!cmd1.equals("ALL")){
					qry += " and substr(a.form_code_control,?,?)=?";
			 	} 
			}
			
			if(!dt_frm.equals("") && !dt_to.equals("")){
				qry += " and admsn_date BETWEEN DATE(?) and DATE(?)";
			}
			
			
			q = "select b.and_no,b.name,to_char(b.admsn_date,'DD-MM-YYYY') as admsn_date,b.persnl_no,b.persnl_name,b.rank,b.discharge_remarks,b.diagnosis_code1d,"
					+ "b.icd_cause_code1d,to_char(b.dschrg_date,'DD-MM-YYYY') as dschrg_date,b.disposal from tb_med_patient_details b"
					+ " left join orbat_all_details_view_mnh a on a.sus_no = b.medical_unit"
					+ " left join nrv_hq c on concat(substring(a.form_code_control,?,?),?)=c.form_code_control"
					+ " where a.status_sus_no='Active' and (b.dschrg_date is null) and (b.disposal is null or b.disposal = '')"+qry
					+ " order by admsn_date,and_no";
			
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
		    
		    if(!dt_frm.equals("") && !dt_to.equals("")){
		    	stmt.setString(j, dt_frm);
		    	j++;
		    	stmt.setString(j, dt_to);
		    }
		    	
		    ResultSet rs = stmt.executeQuery();  
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("and_no"));//0
		    	  list.add(rs.getString("name"));//1
		    	  list.add(rs.getString("admsn_date"));//2
		    	  list.add(rs.getString("persnl_no"));//3
		    	  list.add(rs.getString("persnl_name"));//4
		    	  list.add(rs.getString("rank"));//5
				  list.add(rs.getString("discharge_remarks"));//6
				  list.add(rs.getString("diagnosis_code1d"));//7
				  list.add(rs.getString("icd_cause_code1d"));//8
		    	  list.add(rs.getString("dschrg_date"));//9
		    	  list.add(rs.getString("disposal"));//10
			      alist.add(list);
					
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
		 return alist;
	}
 
	
	
	
	
	
}
