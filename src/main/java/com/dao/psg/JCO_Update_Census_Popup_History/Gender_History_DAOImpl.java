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

public class Gender_History_DAOImpl implements Gender_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Gender_history(int gen_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q = "SELECT \r\n" + 
	        		"     m_g.gender_name as gender, t_g.authority, ltrim(TO_CHAR(t_g.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
	        		"	 t_g.created_by, ltrim(TO_CHAR(t_g.created_date ,'DD-MON-YYYY'),'0') as created_date  , t_g.modified_by AS approved_by, ltrim(TO_CHAR(t_g.modified_date ,'DD-MON-YYYY'),'0') as approved_date\r\n" + 
	        		"	FROM \r\n" + 
	        		"	tb_psg_census_jco_or_p p inner join\r\n" + 
	        		"	tb_psg_update_census_gender_jco t_g on p.id =t_g.jco_id and p.status in ('1','5') and t_g.status in ('1','2')\r\n" + 
	        		"	INNER JOIN tb_psg_mstr_gender m_g ON t_g.gender = m_g.id \r\n" + 
	        		"	WHERE t_g.jco_id = ? ";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, gen_jco_id);
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
