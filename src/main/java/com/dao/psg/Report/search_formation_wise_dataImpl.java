package com.dao.psg.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


public class search_formation_wise_dataImpl implements search_formation_wise_dataDao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> getallformationbattlecass(int startPage,int pageLength,String Search,String orderColunm,String orderType,String personnel_no,
			String rank,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) throws SQLException
	{
		
	
		
    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			String group_str=" group by pc.id,pc.personnel_no,pc.rank,am.authority,am.date_of_authority,orb.unit_name,am.date_of_casualty ";
		
		
		
				 q="select distinct cs.comm_id,pc.personnel_no,r.description as rank,pc.name,orb.unit_name,ltrim(TO_CHAR(cs.date_of_casuality ,'DD-Mon-YYYY'),'0') as date_of_casuality,\r\n" + 
				 		"case when cs.classification_of_casuality like 'physical_casuality' then 'PC' else 'BC' end as classification_of_casuality,am.status_by_mp\r\n" + 
				 		"from tb_psg_census_battle_physical_casuality cs inner join \r\n" + 
				 		"tb_psg_trans_proposed_comm_letter pc on pc.id=cs.comm_id\r\n" + 
				 		"inner join  (select max(id) as id ,comm_id from tb_psg_census_battle_physical_casuality where status=1 group by comm_id) ng1 on cs.id=ng1.id\r\n" + 
				 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pc.unit_sus_no and orb.status_sus_no='Active'	\r\n" + 
				 		"inner join cue_tb_psg_rank_app_master r on r.id = pc.rank  and r.status_active='Active'\r\n" + 
				 		"left join  (select max(id) as id ,comm_id from tb_psg_declared_ba_ca where status=1 group by comm_id) ng on cs.comm_id=ng.comm_id\r\n" + 
				 		"left join tb_psg_declared_ba_ca am on ng.id=am.id	\r\n" + 
				 		"left join all_fmn_view fv  on orb.sus_no = pc.unit_sus_no\r\n" + 
				 		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
				 		"left join all_fmn_view fvm  on orb.sus_no = pc.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
				 		"left join all_fmn_view div  on orb.sus_no = pc.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
				 		"left join all_fmn_view bde  on orb.sus_no = pc.unit_sus_no  \r\n" + 
				 		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' " 
				 		+SearchValue +"and cs.status=1  ORDER BY date_of_casuality desc limit 10 OFFSET 0 ";
				 				
			
						
			
			
			
			
		
		
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank);
			
	      ResultSet rs = stmt.executeQuery();   
	    
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  
	    	  String f5 = "";
		      String action = "";
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    		  String downloadData = "onclick=\" downloaddata('"+ rs.getString("personnel_no") +"');\"";
					f5 = "<i class='action_icons action_download'  " + downloadData + " title='Download Data'></i>";
	    	  }
	    	  action = f5;
				columns.put("action", action);

	    	  list.add(columns); 
 	        }
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	      

	   }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
		return list;
	}
	
	 public long getallformationbattlecassCountList(String Search,String orderColunm,String orderType,String personnel_no,String rank,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no)throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				//String group_str=" group by pc.id,pc.personnel_no,pc.rank,am.authority,am.date_of_authority,orb.unit_name,am.date_of_casualty ";
			  q="select count(app.*) from(select distinct cs.comm_id,pc.personnel_no,r.description as rank,pc.name,orb.unit_name,ltrim(TO_CHAR(cs.date_of_casuality ,'DD-Mon-YYYY'),'0') as date_of_casuality,\r\n" + 
			  		"case when cs.classification_of_casuality like 'physical_casuality' then 'PC' else 'BC' end as classification_of_casuality,am.status_by_mp\r\n" + 
			  		"from tb_psg_census_battle_physical_casuality cs inner join \r\n" + 
			  		"tb_psg_trans_proposed_comm_letter pc on pc.id=cs.comm_id\r\n" + 
			  		"inner join  (select max(id) as id ,comm_id from tb_psg_census_battle_physical_casuality where status=1 group by comm_id) ng1 on cs.id=ng1.id\r\n" + 
			  		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pc.unit_sus_no and orb.status_sus_no='Active'	\r\n" + 
			  		"inner join cue_tb_psg_rank_app_master r on r.id = pc.rank  and r.status_active='Active'\r\n" + 
			  		"left join  (select max(id) as id ,comm_id from tb_psg_declared_ba_ca where status=1 group by comm_id) ng on cs.comm_id=ng.comm_id\r\n" + 
			  		"left join tb_psg_declared_ba_ca am on ng.id=am.id	\r\n" + 
			  		"left join all_fmn_view fv  on orb.sus_no = pc.unit_sus_no\r\n" + 
			  		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
			  		"left join all_fmn_view fvm  on orb.sus_no = pc.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
			  		"left join all_fmn_view div  on orb.sus_no = pc.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
			  		"left join all_fmn_view bde  on orb.sus_no = pc.unit_sus_no  \r\n" + 
			  		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'" 
			  +SearchValue +" and cs.status=1  ORDER BY date_of_casuality desc limit 10 OFFSET 0 ) app";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {  
					total = rs.getInt(1);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return (long) total;
		}
	 public String GenerateQueryWhereClause_SQL4(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no,
				String rank) {
		 String SearchValue ="";
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  { // for Input Filter
				SearchValue += " where  ( ";
				
				SearchValue += " pc.personnel_no like ? or  "
								+ "cast(r.id as character varying) like ?    )";
				
			}
			if(!sus_no.equals("")){
	  			if (SearchValue.contains("where")) {
	  				SearchValue +=" and lower(orb.sus_no) = ?";
				} else {
					SearchValue += " where lower(orb.sus_no) = ? ";
				}
	  			
	    	}
	    	if(!unit_name.equals("0") && !unit_name.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and lower(orb.unit_name) = ? ";
				} else {
					SearchValue += " where lower(orb.unit_name) = ? ";
				}
	    	} 
	    	
	    	
	    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and orb.form_code_control = ? ";
				} else {
					SearchValue += " where orb.form_code_control = ? ";
				}
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and orb.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where orb.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			if (SearchValue.contains("where")) {
		        			SearchValue +=" and orb.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where orb.form_code_control like ? ";
		    			}
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			if (SearchValue.contains("where")) {
			    				SearchValue +=" and orb.form_code_control like ? ";
			    			} else {
			    				SearchValue += " where orb.form_code_control like ? ";
			    			}
				    	}
			    	}
			    }
		    }
			
	    	if(!personnel_no.equals("0") && !personnel_no.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and pc.personnel_no = ? ";
				}else {
					SearchValue +=" where pc.personnel_no = ? ";
				}
			}	
			
			if(!rank.equals("0") && !rank.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and r.id = ? ";
				}else {
					SearchValue +=" where r.id = ? ";
				}
			}	
		
			
	

			return SearchValue;
		}
	 
	 public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no,
				String rank
				) {
			
			int flag = 0;
			
			try {
				if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
					/*flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;*/
				}
				
				if(!sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, sus_no.toLowerCase());
					
				}
				if(!unit_name.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_name.toLowerCase());
					
				}
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					flag += 1;
					stmt.setString(flag, cont_bde);
					
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_div+"%");
	 					
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_corps+"%");
		 					
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			flag += 1;
			 					stmt.setString(flag, cont_comd+"%");
			 					
					    	}
				    	}
				    }
			    }
				if(!personnel_no.equals("0") && !personnel_no.equals(""))
				{
					flag += 1;
					stmt.setString(flag, personnel_no);	
					
				}
				
				
				if (!rank.equals("0")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(rank));
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	
	
	

	
	
	

}
