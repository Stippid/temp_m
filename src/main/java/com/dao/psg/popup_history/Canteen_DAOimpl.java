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

public class Canteen_DAOimpl implements Canteen_DAO {
	
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public List<Map<String, String>> Canteen_history(BigInteger comm_id,int census_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q = "select cd.id,\n" + 
	        		"	t.label,\n" + 
	        		"	cd.name,\n" + 
	        		"	ltrim(TO_CHAR(cd.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\n" + 
	        		"	cd.type_of_card,\n" + 
	        		"	cd.card_no,\n" + 
	        		"	cd.created_by,\n" + 
	        		"	ltrim(TO_CHAR(cd.created_date  ,'DD-MON-YYYY'),'0') as created_date,\n" + 
	        		"	cd.modified_by,\n" + 
	        		"	ltrim(TO_CHAR(cd.modified_date  ,'DD-MON-YYYY'),'0') as modified_date \n" + 
	        		"	from tb_psg_canteen_card_details1 cd\n" + 
	        		"	inner join t_domain_value t on t.codevalue = cast(cd.relation as char) and t.domainid='CSD_CARD'\n" + 
	        		"	where cast(cd.comm_id  as character varying)=? and cd.census_id = ? and cd.status in (1,2) order by cd.id";   
	        
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
