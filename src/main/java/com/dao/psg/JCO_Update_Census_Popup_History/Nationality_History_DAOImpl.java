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

public class Nationality_History_DAOImpl implements Nationality_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Nationality_history(int n_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q =	"SELECT t_n.authority, ltrim(TO_CHAR(t_n.date_of_authority ,'DD-MON-YYYY'),'0') as date_of_authority,\r\n" + 
	        		"t_n.created_by,\r\n" + 
	        		"	   ltrim(TO_CHAR(t_n.created_date ,'DD-MON-YYYY'),'0') as created_date,\r\n" + 
	        		"	   t_n.modified_by as approved_by,\r\n" + 
	        		"	   ltrim(TO_CHAR(t_n.modified_date ,'DD-MON-YYYY'),'0') as  approved_date,\r\n" + 
	        		"	   case when  UPPER(t_n.nationality_other) = UPPER('OTHERS') then t_n.nationality_other\r\n" + 
	        		"				else m_n.nationality_name end as nationality	     \r\n" + 
	        		"FROM tb_psg_census_jco_or_p p\r\n" + 
	        		"INNER JOIN tb_psg_update_census_nationality_jco t_n ON p.id =t_n.jco_id  and p.status in ('1','5') and t_n.status in ('1','2')\r\n" + 
	        		"INNER JOIN tb_psg_mstr_nationality m_n ON t_n.nationality  = m_n.nationality_id\r\n" + 
	        		"WHERE t_n.jco_id = ? ";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, n_jco_id);
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
