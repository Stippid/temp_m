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

public class PanNo_History_DAOImpl implements PanNo_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> PanNo_history(int p_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q =	"SELECT PGP_SYM_DECRYPT(t_p.pan_no ::bytea,current_setting('miso.version'))  as pan_no\r\n" + 
	        		", t_p.authority, ltrim(TO_CHAR(t_p.date_of_authority ,'DD-MON-YYYY'),'0') AS date_of_authority, t_p.created_by, \r\n" + 
	        		"      ltrim(TO_CHAR(t_p.created_date ,'DD-MON-YYYY'),'0') AS created_date, t_p.modified_by AS approved_by, \r\n" + 
	        		"	  ltrim(TO_CHAR(t_p.modified_date ,'DD-MON-YYYY'),'0') as approved_date\r\n" + 
	        		"FROM tb_psg_census_jco_or_p p	\r\n" + 
	        		"INNER JOIN	tb_psg_update_census_pan_no_jco t_p ON p.id =t_p.jco_id and p.status in ('1','5') and t_p.status in ('1','2') \r\n" + 
	        		"WHERE t_p.jco_id = ? ";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, p_jco_id);
		  
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
