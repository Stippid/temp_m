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


public class Address_Of_Birth_Pop_History_DAOImpl implements Address_Of_Birth_History_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Address_Of_Birth_history(int dob_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q = "SELECT\r\n" + 
	        		"     t_a.place_of_birth, \r\n" + 
	        		"	 case when  UPPER(t_a.country_other) = UPPER('OTHERS') then t_a.country_other else m_c.name end as country , \r\n" + 
	        		"	 case when  UPPER(t_a.state_other) = UPPER('OTHERS') then t_a.state_other else m_s.state_name end as state , \r\n" + 
	        		"	 case when  UPPER(t_a.district_other) = UPPER('OTHERS') then t_a.district_other else  m_d.district_name end as district , \r\n" + 
	        		"	 t_a.authority,ltrim(TO_CHAR(t_a.date_of_authority ,'DD-MON-YYYY'),'0') as  date_of_authority, t_a.created_by, \r\n" + 
	        		"	 ltrim(TO_CHAR(t_a.created_date ,'DD-MON-YYYY'),'0') as created_date, t_a.modified_by AS approved_by,\r\n" + 
	        		"	 ltrim(TO_CHAR(t_a.modified_date ,'DD-MON-YYYY'),'0') as approved_date\r\n" + 
	        		"   FROM tb_psg_census_jco_or_p p\r\n" + 
	        		"	INNER JOIN tb_psg_update_census_address_of_birth_jco t_a ON p.id =t_a.jco_id and p.status in ('1','5') and t_a.status in ('1','2')\r\n" + 
	        		"	INNER JOIN tb_psg_mstr_country m_c ON t_a.country_of_birth = m_c.id\r\n" + 
	        		"	INNER JOIN tb_psg_mstr_state m_s ON t_a.state_of_birth = m_s.state_id\r\n" + 
	        		"	INNER JOIN tb_psg_mstr_district m_d ON t_a.district_of_birth = m_d.district_id\r\n" + 
	        		"	WHERE t_a.jco_id =  ?";   
	        
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
