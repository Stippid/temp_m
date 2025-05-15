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


public class search_agencies_formationImpl implements search_agencies_formationDao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> getallSearchagencies(int startPage,int pageLength,String Search,String orderColunm,String orderType,String personnel_no,
			String rank,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id) throws SQLException
	{
		
	
		
    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank, dt_of_casuality, scase,agency_id,type_of_benefits_id);
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
			
			
		
		
				 q="select distinct pc.id as comm_id\r\n" + 
				 		",pc.personnel_no,mc.agency_name,bm.benefits_name,cs.amount_due_v,cs.amount_rel_v , \r\n" + 
				 		"cs.reason,cs.updated_as_on \r\n" + 
				 		"from tb_psg_emolument_parent_offcr cs \r\n" + 
				 		"inner join tb_psg_trans_proposed_comm_letter pc on pc.id=cs.comm_id\r\n" + 
				 		"inner join tb_psg_agency_mstr mc on mc.id=cs.agency_id\r\n" + 
				 		"inner join tb_psg_benefits_mstr bm on bm.id=cs.type_of_benefits_id\r\n" + 
				 		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pc.unit_sus_no and orb.status_sus_no='Active'											  	\r\n" + 
				 		"left join all_fmn_view fv  on orb.sus_no = pc.unit_sus_no\r\n" + 
				 		"inner join cue_tb_psg_rank_app_master r on r.id = pc.rank  and r.status_active='Active'\r\n" + 
				 		"inner join tb_psg_census_battle_physical_casuality bc   on bc.comm_id = pc.id\r\n" + 
				 		"inner join  (select max(id) as id ,comm_id from tb_psg_census_battle_physical_casuality\r\n" + 
				 		" where status=1 group by comm_id) ng1 on bc.id=ng1.id\r\n" + 
				 		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) \r\n" + 
				 		"and fv.level_in_hierarchy = 'Command' \r\n" + 
				 		"left join all_fmn_view fvm  on orb.sus_no = pc.unit_sus_no  \r\n" + 
				 		"and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
				 		"left join all_fmn_view div  on orb.sus_no = pc.unit_sus_no  \r\n" + 
				 		"and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
				 		"left join all_fmn_view bde  on orb.sus_no = pc.unit_sus_no  \r\n" + 
				 		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'" 
				 		+SearchValue +"   ORDER BY comm_id asc limit 10 OFFSET 0";
				 				
			
			
			
			
			
			
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank, dt_of_casuality, scase,agency_id,type_of_benefits_id);
			
	      ResultSet rs = stmt.executeQuery();   
	      
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  
	    	  String f5 = "";
		      String action = "";
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//	    		  String View1 = "onclick=\"  {AppViewData("+ rs.getString("comm_id") + ")}\"";
//			        f5 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
	    	  }
	    	 /* action = f5;
				columns.put("action", action);*/

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
	
	 public long getSearchagenciesCount(String Search,String orderColunm,String orderType,String personnel_no,String rank,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id)throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank, dt_of_casuality,scase,agency_id,type_of_benefits_id);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				//String group_str=" group by pc.id,pc.personnel_no,pc.rank,am.authority,am.date_of_authority,orb.unit_name,am.date_of_casualty ";
			  q="select count(app.*) from( select distinct pc.id as comm_id\r\n" + 
			  		",pc.personnel_no,mc.agency_name,bm.benefits_name,cs.amount_due_v,cs.amount_rel_v , \r\n" + 
			  		"cs.reason,cs.updated_as_on \r\n" + 
			  		"from tb_psg_emolument_parent_offcr cs \r\n" + 
			  		"inner join tb_psg_trans_proposed_comm_letter pc on pc.id=cs.comm_id\r\n" + 
			  		"inner join tb_psg_agency_mstr mc on mc.id=cs.agency_id\r\n" + 
			  		"inner join tb_psg_benefits_mstr bm on bm.id=cs.type_of_benefits_id\r\n" + 
			  		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pc.unit_sus_no and orb.status_sus_no='Active'											  	\r\n" + 
			  		"left join all_fmn_view fv  on orb.sus_no = pc.unit_sus_no\r\n" + 
			  		"inner join cue_tb_psg_rank_app_master r on r.id = pc.rank  and r.status_active='Active'\r\n" + 
			  		"inner join tb_psg_census_battle_physical_casuality bc   on bc.comm_id = pc.id\r\n" + 
			  		"inner join  (select max(id) as id ,comm_id from tb_psg_census_battle_physical_casuality\r\n" + 
			  		" where status=1 group by comm_id) ng1 on bc.id=ng1.id\r\n" + 
			  		"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) \r\n" + 
			  		"and fv.level_in_hierarchy = 'Command' \r\n" + 
			  		"left join all_fmn_view fvm  on orb.sus_no = pc.unit_sus_no  \r\n" + 
			  		"and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
			  		"left join all_fmn_view div  on orb.sus_no = pc.unit_sus_no  \r\n" + 
			  		"and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
			  		"left join all_fmn_view bde  on orb.sus_no = pc.unit_sus_no  \r\n" + 
			  		" and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"+SearchValue +"   ORDER BY comm_id asc limit 10 OFFSET 0 ) app ";
				
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no,rank, dt_of_casuality,scase,agency_id,type_of_benefits_id);
			
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
				String rank,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id) {
		 String SearchValue ="";
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  { // for Input Filter
				SearchValue += " where  ( ";
				
				SearchValue += " pc.personnel_no like ? or  "
								+ "cast(r.id as character varying) like ?   "
								+ "to_char(bc.date_of_casuality, 'DD/MM/YYYY') = ? )"
								+ "cast(mc.id as character varying) like ?   "
								+ "cast(bm.id as character varying) like ?   ";
				
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
			 if (!dt_of_casuality.equals("")) {
				
				 if(SearchValue.contains("where")) {
						SearchValue +=" and to_char(bc.date_of_casuality, 'DD/MM/YYYY') = ? ";
					}else {
						SearchValue +=" where  to_char(bc.date_of_casuality, 'DD/MM/YYYY') = ? ";
					}
				}	
			 if(scase.equals("1"))  {
					if(SearchValue.contains("where")) {
						SearchValue +=" and cs.status = '1' and cs.update_status = '-1' and cs.close_status = '-1' ";
					}
				}	
				if(scase.equals("2"))  {
					if(SearchValue.contains("where")) {
						SearchValue +=" and cs.status = '1' and cs.itteration_status > '0' and cs.close_status = '-1' ";
					}
				}	
				if(scase.equals("3"))  {
					if(SearchValue.contains("where")) {
						SearchValue +=" and  cs.status = '4' ";
					}
				}	
	
			if(!agency_id.equals("0") && !agency_id.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and cast(mc.id as character varying) like ?";
				}else {
					SearchValue +=" where cast(mc.id as character varying) like ? ";
				}
			}	
			if(!type_of_benefits_id.equals("0") && !type_of_benefits_id.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and cast(bm.id as character varying) like ? ";
				}else {
					SearchValue +=" where cast(bm.id as character varying) like ? ";
				}
			}	
			return SearchValue;
		}
	 
	 public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no,
				String rank,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id
				) {
			
			int flag = 0;
			
			try {
				if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					

					
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
				if (!dt_of_casuality.equals("")) {
	                 flag += 1;
						stmt.setString(flag, dt_of_casuality);
					}
					
				if (!agency_id.equals("0")) {
	                 flag += 1;
						stmt.setString(flag,agency_id);
					}
				if (!type_of_benefits_id.equals("0")) {
	                 flag += 1;
						stmt.setString(flag,type_of_benefits_id);
					}
			/*	if (scase.equals("1")) {
	                 flag += 1;
						stmt.setString(flag, scase);
					}
				if (scase.equals("2")) {
	                 flag += 1;
						stmt.setString(flag, scase);
					}
				if (scase.equals("3")) {
	                 flag += 1;
						stmt.setString(flag, scase);
					}*/
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	
	
	

	
	
	
	
	 }
