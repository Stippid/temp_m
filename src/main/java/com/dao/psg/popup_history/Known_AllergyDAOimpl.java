package com.dao.psg.popup_history;

import java.math.BigInteger;
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

public class Known_AllergyDAOimpl implements Known_AllergyDAO {
	
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public List<Map<String, String>> known_allergy_history(BigInteger comm_id,int census_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q = "select id,medicine, created_by, "
	        		+ "ltrim(TO_CHAR(created_date  ,'DD-MON-YYYY'),'0') as created_date,"
	        		+ "ltrim(TO_CHAR(modified_date  ,'DD-MON-YYYY'),'0') as modified_date,modified_by from tb_psg_allergic_to_medicine\n"
	        		+ "where  cast(comm_id  as character varying)=? and cen_id=? and status in (1,2) order by id";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setString(1, String.valueOf(comm_id));
		    stmt.setInt(2, census_id);
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
