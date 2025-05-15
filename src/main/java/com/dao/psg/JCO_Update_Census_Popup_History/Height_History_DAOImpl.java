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

public class Height_History_DAOImpl implements Height_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Height_history(int h_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q =	"SELECT case when  h.height_id=0 then '' else h.centimeter end AS cm ,case when  h.height_id=0 then '' else h.inch end as inch ,t_h.authority,ltrim(TO_CHAR(t_h.date_of_authority ,'DD-MON-YYYY'),'0') AS date_of_authority,t_h.created_by,\r\n" + 
	        		"	  ltrim(TO_CHAR(t_h.created_date ,'DD-MON-YYYY'),'0') as created_date,t_h.modified_by as approved_by,\r\n" + 
	        		"	   ltrim(TO_CHAR(t_h.modified_date ,'DD-MON-YYYY'),'0') as approved_date\r\n" + 
	        		"FROM tb_psg_census_jco_or_p p \r\n" + 
	        		"INNER JOIN tb_psg_update_census_height_jco t_h ON p.id =t_h.jco_id and p.status in ('1','5') and t_h.status in ('1','2') \r\n" + 
	        		"INNER JOIN tb_psg_mstr_height h ON t_h.height = h.height_id\r\n" + 
	        		"WHERE t_h.jco_id = ? ";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, h_jco_id);
		  
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
