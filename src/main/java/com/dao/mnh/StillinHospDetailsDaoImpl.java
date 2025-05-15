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

public class StillinHospDetailsDaoImpl implements StillinHospDetailsDao{
	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
		/////////////// still
		
		public  List<Map<String, Object>> Searchstillrpt(String sus1,String from_date1,String to_date1,String persnl_no1,String and_no1){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			
			if(!sus1.equals("")){
				qry += " and medical_unit =?";
			} 
			if(!from_date1.equals("") && !to_date1.equals("")) {
				qry +=" and cast(admsn_date as DATE) between cast(? as DATE) and cast(? as DATE)";
			}else {
				if(!from_date1.equals("") && !from_date1.equals(null)){
			 		qry += " and cast(admsn_date as DATE)  >= cast(? as DATE)";
			 	}
			 		
				if(!to_date1.equals("") && !to_date1.equals(null)){
					qry += " and cast(admsn_date as DATE)  <= cast(? as DATE)";
			 	} 
			}
			if(!persnl_no1.equals("") && !persnl_no1.equals(null)){
				qry += " and persnl_no =?";
			} 
			if(!and_no1.equals("") && !and_no1.equals(null)){
				qry += " and and_no =?";
			} 
			if(sus1.equals("") && from_date1.equals("") && from_date1.equals(null) && to_date1.equals("") && to_date1.equals(null))
			{
				qry ="";
			}
			if(qry.equals(""))
			{
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				
				/*q="select distinct id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = medical_unit and status_sus_no ='Active'),"
						+ "persnl_no,name,relationship,admsn_type,and_no from tb_med_patient_details where dschrg_date is null order by id desc" ;
						 */
				q="select distinct id,(select unit_name from orbat_all_details_view_mnh  where sus_no = medical_unit and status_sus_no ='Active'),"
						+ "persnl_no,name,relationship,admsn_type,and_no from tb_med_patient_details where dschrg_date is null order by id desc" ; 
			}
			else
			{
				/*q="select distinct id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = medical_unit and status_sus_no ='Active'),"
						+ "persnl_no,name,relationship,admsn_type,and_no from tb_med_patient_details where dschrg_date is null" +qry+ " order by id desc" ;*/
			
				q="select distinct id,(select unit_name from orbat_all_details_view_mnh where sus_no = medical_unit and status_sus_no ='Active'),"
						+ "persnl_no,name,relationship,admsn_type,and_no from tb_med_patient_details where dschrg_date is null" +qry+ " order by id desc" ;
			}
			stmt=conn.prepareStatement(q);
			int j=1;  
			if(!sus1.equals("")){
				stmt.setString(j, sus1);
			}
			if(!from_date1.equals("") && !from_date1.equals(null)){
				j+=1;
				stmt.setString(j, from_date1);
			} 
			if(!to_date1.equals("") && !to_date1.equals(null)){
				j+=1;
				stmt.setString(j, to_date1);
			}
			if(!persnl_no1.equals("") && !persnl_no1.equals(null)){
				j+=1;
				stmt.setString(j, persnl_no1);
			}
			if(!and_no1.equals("") && !and_no1.equals(null)){
				j+=1;
				stmt.setString(j, and_no1);
			}
		
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
			    for(int i = 1; i <= columnCount; i++){
			    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			    }
			    
				String f = "";
				
				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update?') ){editdata("
						+ rs.getObject(1) + "," + j + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				f += updateButton;
				columns.put("edit", f);
				list.add(columns);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
