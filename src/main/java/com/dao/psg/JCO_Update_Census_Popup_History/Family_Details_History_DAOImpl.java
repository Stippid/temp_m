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

public class Family_Details_History_DAOImpl implements Family_Details_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Map<String, String>> Family_Details_history(int fd_jco_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
	   try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
	        q =	"SELECT t_f.father_name, ltrim(TO_CHAR(t_f.father_dob ,'DD-MON-YYYY'),'0') AS father_dob, t_f.father_place_birth, \r\n" + 
	        		"		case when  UPPER(t_f.father_other) = UPPER('OTHERS') then t_f.father_other else t_f.father_service end as father_service , \r\n" + 
	        		"		m_p1.profession_name AS f_profession_name,\r\n" + 
	        		"		t_f.mother_name, ltrim(TO_CHAR(t_f.mother_dob ,'DD-MON-YYYY'),'0') AS mother_dob, t_f.mother_place_birth,\r\n" + 
	        		"		case when  UPPER(t_f.mother_other) = UPPER('OTHERS') then t_f.father_other else t_f.mother_service end as  mother_service , \r\n" + 
	        		"		m_p2.profession_name AS m_profession_name, \r\n" + 
	        		"		t_f.authority,ltrim(TO_CHAR(t_f.date_of_authority ,'DD-MON-YYYY'),'0') AS  date_of_authority, \r\n" + 
	        		"		t_f.created_by,ltrim(TO_CHAR(t_f.created_date ,'DD-MON-YYYY'),'0') AS created_date, \r\n" + 
	        		"	    t_f.modified_by AS approved_by, ltrim(TO_CHAR(t_f.modified_date ,'DD-MON-YYYY'),'0') AS approved_date \r\n" + 
	        		"FROM tb_psg_census_jco_or_p p	\r\n" + 
	        		"	INNER JOIN tb_psg_update_census_family_details_jco t_f ON p.id =t_f.jco_id and p.status in ('1','5') and t_f.status in ('1','2')\r\n" + 
	        		"	left JOIN tb_psg_mstr_profession m_p1 ON t_f.father_profession = m_p1.id\r\n" + 
	        		"	left JOIN tb_psg_mstr_profession m_p2 ON t_f.mother_profession = m_p2.id\r\n" + 
	        		"	WHERE t_f.jco_id = ? ";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, fd_jco_id);
		 
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
