package com.dao.psg.JCO_Update_Census_Popup_History;

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


public class Date_Of_Birth_Pop_History_DAOImpl implements Date_Of_Birth_Pop_History_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Date_Of_Birth_history(int dob_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q = "SELECT ltrim(TO_CHAR(date_of_birth ,'DD-MON-YYYY'),'0') as  date_of_birth , authority,ltrim(TO_CHAR(date_of_authority ,'DD-MON-YYYY'),'0') as  date_of_authority, created_by, ltrim(TO_CHAR(created_date ,'DD-MON-YYYY'),'0') as created_date, modified_by AS approved_by,  ltrim(TO_CHAR(modified_date ,'DD-MON-YYYY'),'0') as approved_date\r\n" + 
	        		"	FROM public.tb_psg_update_census_birth_jco WHERE jco_id = ?";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, dob_jco_id);
		  
		    ResultSet rs = stmt.executeQuery();    
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getString(i));
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

	

}
