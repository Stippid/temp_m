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

public class Update_Awards_Medal_History_DAOImpl implements Update_Awards_Medal_History_DAO {

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<Map<String, String>> update_award_medal_history(BigInteger comm_id, int census_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			

q = "select am.id,td.award_cat as category_8 ,mm.medal_name,am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\n" + 
					"ltrim(TO_CHAR(am.date_of_award  ,'DD-MON-YYYY'),'0') AS date_of_award,"
					+ "am.created_by,\n"
	        		+ "ltrim(TO_CHAR(am.created_on  ,'DD-MON-YYYY'),'0') AS created_date,\n"
					+ "am.authority,ltrim(TO_CHAR(am.date_of_authority  ,'DD-MON-YYYY'),'0') AS date_of_authority,"
					+ "am.modify_by,\n"
	        		+ "ltrim(TO_CHAR(am.modify_on  ,'DD-MON-YYYY'),'0') AS modify_on\n"
					+ ",am.census_id,am.status from tb_psg_census_awardsnmedal am\n" + 
					"inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n" + 
					"LEFT join orbat_view_corps co on co.sus_no=am.corps_area\n" + 
					"LEFT join orbat_view_div di on di.sus_no=am.div_subarea\n" + 
					"LEFT join orbat_view_bde bd on bd.sus_no=am.bde\n" + 
					"inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n" + 
					"inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n" + 
					 "where am.census_id=? and cast(comm_id as character varying)=? and am.status in (1,2) order by am.id"; 
					 
			
//	        q = "select am.id,td.award_cat as category_8 ,mm.medal_name,\n"
//	        		+ "am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\n"
//	        		+ "am.date_of_award,am.authority,\n"
//	        		+ "ltrim(TO_CHAR(am.date_of_authority  ,'DD-MON-YYYY'),'0') AS date_of_authority,\n"
//	        		+ "am.created_by,\n"
//	        		+ "ltrim(TO_CHAR(am.created_on  ,'DD-MON-YYYY'),'0') AS created_date,\n"
//	        		+ "am.modify_by,\n"
//	        		+ "ltrim(TO_CHAR(am.modify_on  ,'DD-MON-YYYY'),'0') AS modify_on\n"
//	        		+ "from tb_psg_census_awardsnmedal am\n"
//	        		+ "inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n"
//	        		+ "inner join orbat_view_corps co on co.sus_no=am.corps_area\n"
//	        		+ "inner join orbat_view_div di on di.sus_no=am.div_subarea\n"
//	        		+ "inner join orbat_view_bde bd on bd.sus_no=am.bde\n"
//	        		+ "inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n"
//	        		+ "inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n"
//	        		+ "where am.census_id=? and comm_id=? and am.status in (1,2)";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setString(2, String.valueOf(comm_id));
		    stmt.setInt(1, census_id);
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
