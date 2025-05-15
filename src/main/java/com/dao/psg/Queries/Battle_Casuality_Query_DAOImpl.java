package com.dao.psg.Queries;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Battle_Casuality_Query_DAOImpl implements Battle_Casuality_Query_DAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}		
	
	public List<Map<String, Object>> getBattle_Casuality_Officer_ReportDataList_Details(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_Officer(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();		
				q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.personnel_no,app.rank,app.date_of_casuality,app.casuality_type,app.form_code_control,app.id as comm_id\r\n" + 
						"	from (\r\n" + 
						"	select distinct\r\n" + 
						"	fv.unit_name as cmd_unit,\r\n" + 
						"	fvm.unit_name as corp_unit,\r\n" + 
						"	div.unit_name as div_unit,\r\n" + 
						"	bde.unit_name as bde_unit,\r\n" + 
						"	orb.unit_name,\r\n" + 
						"	c.unit_sus_no,\r\n" + 
						"	orb.form_code_control,\r\n" + 
						"	c.personnel_no, \r\n" + 
						"	cue.description as rank, \r\n" + 
						"	to_char(sd.date_of_casuality,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
						"	t.label as casuality_type,c.id\r\n" + 
						"	from tb_psg_census_battle_physical_casuality sd\r\n" + 
						"	inner join tb_psg_trans_proposed_comm_letter c on sd.comm_id=c.id and sd.status in (1,2)\r\n" + 
						"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active' \r\n" + 
						"	inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=c.rank and cue.status_active = 'Active'\r\n" + 
						"	inner join t_domain_value t on sd.classification_of_casuality = t.codevalue and t.domainid = 'CLASSIFICATION_OF_CASUALITY'\r\n" + 
						"	left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"	left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"	left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"	left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade')app " 
						+SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_Officer(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
                      
				String f = "";
				String downloadData = "onclick=\" downloaddata1('"+ rs.getString("comm_id") +"');\"";
				f = "<i class='fa fa-download' style='font-size:18px; color:black;'  " + downloadData + " title='Download Data'></i>";
               
			    columns.put("action", f);
			    list.add(columns);
			    
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
	return list;
}
	
 public long getBattle_Casuality_Officer_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL_Officer(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				q ="select count(app1.*) from\r\n" + 
					"	(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.personnel_no,app.rank,app.date_of_casuality,app.casuality_type,app.form_code_control\r\n" + 
					"	from (\r\n" + 
					"	select distinct\r\n" + 
					"	fv.unit_name as cmd_unit,\r\n" + 
					"	fvm.unit_name as corp_unit,\r\n" + 
					"	div.unit_name as div_unit,\r\n" + 
					"	bde.unit_name as bde_unit,\r\n" + 
					"	orb.unit_name,\r\n" + 
					"	c.unit_sus_no,\r\n" + 
					"	orb.form_code_control,\r\n" + 
					"	c.personnel_no, \r\n" + 
					"	cue.description as rank, \r\n" + 
					"	to_char(sd.date_of_casuality,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
					"	t.label as casuality_type\r\n" + 
					"	from tb_psg_census_battle_physical_casuality sd\r\n" + 
					"	inner join tb_psg_trans_proposed_comm_letter c on sd.comm_id=c.id and sd.status in (1,2)\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active' \r\n" + 
					"	inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=c.rank and cue.status_active = 'Active'\r\n" + 
					"	inner join t_domain_value t on sd.classification_of_casuality = t.codevalue and t.domainid = 'CLASSIFICATION_OF_CASUALITY'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade')app " +SearchValue +") app1";
			
			PreparedStatement stmt = conn.prepareStatement(q);		
			stmt = setQueryWhereClause_SQL_Officer(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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
	  
	public String GenerateQueryWhereClause_SQL_Officer(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? or lower(app.personnel_no) like ? "
 					+ " or lower(app.rank) like ? or lower(app.date_of_casuality) like ? or lower(app.casuality_type) like ? )";
 		}
  		 		
  		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
  				SearchValue +=" and lower(app.unit_sus_no) = ?";
			} else {
				SearchValue += " where lower(app.unit_sus_no) = ? ";
			}			
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
    	}     	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and app.form_code_control = ? ";
			} else {
				SearchValue += " where app.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and app.form_code_control like ? ";
    			} else {
    				SearchValue += " where app.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and app.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where app.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }

   return SearchValue;
 }

  public PreparedStatement setQueryWhereClause_SQL_Officer(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				
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
 			
 		}catch (Exception e) {}
 		return stmt;
 	}
 
  public List<Map<String, Object>>getBattle_Casuality_JCO_ReportDataList_Details(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_JCO(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
				q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.personnel_no,app.rank,app.date_of_casuality,app.casuality_type,app.form_code_control,app.id as jco_id\r\n" + 
						"	from (\r\n" + 
						"	select distinct\r\n" + 
						"	fv.unit_name as cmd_unit,\r\n" + 
						"	fvm.unit_name as corp_unit,\r\n" + 
						"	div.unit_name as div_unit,\r\n" + 
						"	bde.unit_name as bde_unit,\r\n" + 
						"	orb.unit_name,\r\n" + 
						"	c.unit_sus_no,\r\n" + 
						"	orb.form_code_control,\r\n" + 
						"	c.army_no as personnel_no, \r\n" + 
						"	cue.description as rank, \r\n" + 
						"	to_char(sd_j.date_of_casuality,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
						"	t.label as casuality_type,c.id\r\n" + 
						"	from tb_psg_census_battle_physical_casuality_jco sd_j\r\n" + 
						"	inner join tb_psg_census_jco_or_p c on sd_j.jco_id=c.id and sd_j.status in (1,2)\r\n" + 
						"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active' \r\n" + 
						"	inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=c.rank and cue.status_active = 'Active'\r\n" + 
						"	inner join t_domain_value t on sd_j.classification_of_casuality = t.codevalue and t.domainid = 'CLASSIFICATION_OF_CASUALITY'\r\n" + 
						"	left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"	left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"	left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"	left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade')app" 
						+SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_JCO(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String f = "";
				String downloadData = "onclick=\" downloaddata2('"+ rs.getString("jco_id") +"');\"";
				f = "<i class='fa fa-download' style='font-size:18px; color:black;'  " + downloadData + " title='Download Data'></i>";
				
				  columns.put("action", f);
				  list.add(columns);
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
	return list;
}

  public long getBattle_Casuality_JCO_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL_JCO(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				q ="select count(app1.*) from \r\n" + 
					"	(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.personnel_no,app.rank,app.date_of_casuality,app.casuality_type,app.form_code_control\r\n" + 
					"	from (\r\n" + 
					"	select distinct\r\n" + 
					"	fv.unit_name as cmd_unit,\r\n" + 
					"	fvm.unit_name as corp_unit,\r\n" + 
					"	div.unit_name as div_unit,\r\n" + 
					"	bde.unit_name as bde_unit,\r\n" + 
					"	orb.unit_name,\r\n" + 
					"	c.unit_sus_no,\r\n" + 
					"	orb.form_code_control,\r\n" + 
					"	c.army_no as personnel_no, \r\n" + 
					"	cue.description as rank, \r\n" + 
					"	to_char(sd_j.date_of_casuality,'DD-MON-YYYY') as date_of_casuality,\r\n" + 
					"	t.label as casuality_type\r\n" + 
					"	from tb_psg_census_battle_physical_casuality_jco sd_j\r\n" + 
					"	inner join tb_psg_census_jco_or_p c on sd_j.jco_id=c.id and sd_j.status in (1,2)\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and orb.status_sus_no='Active' \r\n" + 
					"	inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=c.rank and cue.status_active = 'Active'\r\n" + 
					"	inner join t_domain_value t on sd_j.classification_of_casuality = t.codevalue and t.domainid = 'CLASSIFICATION_OF_CASUALITY'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = c.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade')app" +SearchValue +") app1" ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_JCO(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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


	public String GenerateQueryWhereClause_SQL_JCO(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? or lower(app.personnel_no) like ? "
 					+ " or lower(app.rank) like ? or lower(app.date_of_casuality) like ? or lower(app.casuality_type) like ? )";
			
		}
		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
  				SearchValue +=" and lower(app.unit_sus_no) = ?";
			} else {
				SearchValue += " where lower(app.unit_sus_no) = ? ";
			}			
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
    	}     	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and app.form_code_control = ? ";
			} else {
				SearchValue += " where app.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and app.form_code_control like ? ";
    			} else {
    				SearchValue += " where app.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and app.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where app.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }
 	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL_JCO(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		int flag = 0;
		try {
			if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
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
		}catch (Exception e) {}
		return stmt;
	}
}
