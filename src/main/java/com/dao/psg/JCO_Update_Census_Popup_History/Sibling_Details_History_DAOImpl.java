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

public class Sibling_Details_History_DAOImpl implements Sibling_Details_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Sibling_Details_history(int sd_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q =	" SELECT p.name AS s_name,\r\n" + 
	        		"		ltrim(TO_CHAR(p.date_of_birth ,'DD-MON-YYYY'),'0') AS date_of_birth ,\r\n" + 
	        		"		tdc.label as reletion,\r\n" + 
	        		"		PGP_SYM_DECRYPT(p.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,PGP_SYM_DECRYPT(p.pan_no ::bytea,current_setting('miso.version'))  as pan_no,\r\n" + 
	        		"		p.authority, \r\n" + 
	        		"		ltrim(TO_CHAR(p.date_of_authority ,'DD-MON-YYYY'),'0') AS date_of_authority, \r\n" + 
	        		"		p.created_by, \r\n" + 
	        		"		ltrim(TO_CHAR(p.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
	        		"		p.modified_by AS approved_by, \r\n" + 
	        		"		ltrim(TO_CHAR(p.modified_date ,'DD-MON-YYYY'),'0') AS approved_date \r\n" + 
	        		"FROM tb_psg_census_jco_or_siblings p \r\n" + 
	        		"inner join t_domain_value tdc on tdc.codevalue=cast(p.relationship as char) and tdc.domainid='RELATIONSHIP'\r\n" + 
	        		"WHERE p.jco_id = ? and p.status in ('1','5')";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, sd_jco_id);
		    
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
