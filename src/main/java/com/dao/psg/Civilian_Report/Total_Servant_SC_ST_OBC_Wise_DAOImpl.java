package com.dao.psg.Civilian_Report;

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

public class Total_Servant_SC_ST_OBC_Wise_DAOImpl implements Total_Servant_SC_ST_OBC_Wise_DAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}

	 public List<Map<String, Object>> getPer_Temp_SC_ST_OBC_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		String SearchValue1 = GenerateQueryWhereClause_SQL1(Search);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select app.arm_desc,app.total,app.gp_a_sc,app.gp_a_st,app.gp_a_obc,\r\n" + 
					"	app.gp_b_sc,app.gp_b_st,app.gp_b_obc,\r\n" + 
					"	app.gp_c_sc,app.gp_c_st,app.gp_c_obc\r\n" + 
					"	from\r\n" + 
					"		(select distinct\r\n" + 
					"		a.arm_desc,		\r\n" + 
					"		count(*) filter (where c.civ_group in ('A','B','C') and c.category_belongs in ('1','2','3','4')) as total, \r\n" + 
					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '2') as gp_a_sc,\r\n" + 
					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '3') as gp_a_st,\r\n" + 
					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '4') as gp_a_obc,\r\n" + 
					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '2') as gp_b_sc,\r\n" + 
					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '3') as gp_b_st,\r\n" + 
					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '4') as gp_b_obc,\r\n" + 
					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '2') as gp_c_sc,\r\n" + 
					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '3') as gp_c_st,\r\n" + 
					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '4') as gp_c_obc\r\n" + 
					"	from tb_psg_civilian_dtl_main c\r\n" + 
					"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'R'\r\n" + 
					"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + SearchValue +
					"	group by a.arm_desc) app  " 
					+ SearchValue1 +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,SearchValue1);
			System.err.println("query total ser regular \n " + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
                      
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

public long getPer_Temp_SC_ST_OBC_TotalCountDetail(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status) {
 		String SearchValue = GenerateQueryWhereClause_SQL(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
 		String SearchValue1 = GenerateQueryWhereClause_SQL1(Search);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(app1.*) from\r\n" + 
 					"(select app.arm_desc,app.total,app.gp_a_sc,app.gp_a_st,app.gp_a_obc,\r\n" + 
 					"	app.gp_b_sc,app.gp_b_st,app.gp_b_obc,\r\n" + 
 					"	app.gp_c_sc,app.gp_c_st,app.gp_c_obc\r\n" + 
 					"	from\r\n" + 
 					"		(select distinct\r\n" + 
 					"		a.arm_desc,		\r\n" + 
 					"		count(*) filter (where c.civ_group in ('A','B','C') and c.category_belongs in ('1','2','3','4')) as total, \r\n" + 
 					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '2') as gp_a_sc,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '3') as gp_a_st,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '4') as gp_a_obc,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '2') as gp_b_sc,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '3') as gp_b_st,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '4') as gp_b_obc,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '2') as gp_c_sc,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '3') as gp_c_st,\r\n" + 
 					"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '4') as gp_c_obc\r\n" + 
 					"	from tb_psg_civilian_dtl_main c\r\n" + 
 					"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'R'\r\n" + 
 					"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
 					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
 					"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
 					"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
 					"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
 					"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + SearchValue +
 					"	group by a.arm_desc) app  " + SearchValue1 +") app1";
 			
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,service_status);
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


  	public String GenerateQueryWhereClause_SQL(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
 		String SearchValue ="";
  		
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
    	
   return SearchValue;
 }
	public String GenerateQueryWhereClause_SQL1(String Search) {
 		String SearchValue1 ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue1 =" where ( ";
 			SearchValue1 +=" lower(app.arm_desc) like ? "
 					+ " or cast(app.total as character varying) = ? "
 					+ " or cast(app.gp_a_sc as character varying) = ? or cast(app.gp_a_st as character varying) = ? or cast(app.gp_a_obc as character varying) = ? "
 					+ " or cast(app.gp_b_sc as character varying) = ? or cast(app.gp_b_st as character varying) = ? or cast(app.gp_b_obc as character varying) = ? "
 					+ " or cast(app.gp_c_sc as character varying) = ? or cast(app.gp_c_st as character varying) = ? or cast(app.gp_c_obc as character varying) = ? )";
 		}
  	
   return SearchValue1;
 }

  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String SearchValue1) {
 		int flag = 0;
 		try {
    			
			
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
			
			if(!SearchValue1.equals("")) {
 				
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
    		}
 		}catch (Exception e) {}
 		return stmt;
 	}

	public List<Map<String, Object>> getNon_Regular_SC_ST_OBC_ReportDataListDetail(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_Non_Reg(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		String SearchValue1 = GenerateQueryWhereClause_SQL_Non_Reg1(Search);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select app.arm_desc,app.total,app.sc,app.st,app.obc\r\n" + 
					"from\r\n" + 
					"	(select \r\n" + 
					"		a.arm_desc,	\r\n" + 
					"		count(*) filter (where  c.category_belongs in ('1','2','3','4')) as total,\r\n" + 
					"		count(*) filter (where  c.category_belongs = '2') as sc,\r\n" + 
					"		count(*) filter (where c.category_belongs = '3') as st,\r\n" + 
					"		count(*) filter (where  c.category_belongs = '4') as obc\r\n" + 
					"	from tb_psg_civilian_dtl_main c\r\n" + 
					"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'NR'\r\n" + 
					"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + SearchValue +
					"	group by a.arm_desc) app  " + SearchValue1 + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_Non_Reg(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,SearchValue1);
			System.err.println("query total ser non-regular \n " + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
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

public long getNon_Regular_SC_ST_OBC_TotalCountDetail(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
	String SearchValue = GenerateQueryWhereClause_SQL_Non_Reg(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
	String SearchValue1 = GenerateQueryWhereClause_SQL_Non_Reg1(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"(select app.arm_desc,app.total,app.sc,app.st,app.obc\r\n" + 
					"from\r\n" + 
					"	(select \r\n" + 
					"		a.arm_desc,	\r\n" + 
					"		count(*) filter (where  c.category_belongs in ('1','2','3','4')) as total,\r\n" + 
					"		count(*) filter (where  c.category_belongs = '2') as sc,\r\n" + 
					"		count(*) filter (where  c.category_belongs = '3') as st,\r\n" + 
					"		count(*) filter (where  c.category_belongs = '4') as obc\r\n" + 
					"	from tb_psg_civilian_dtl_main c\r\n" + 
					"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'NR'\r\n" + 
					"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + SearchValue +
					"	group by a.arm_desc) app  " + 
					"" + SearchValue1 +
					") app1  " ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_Non_Reg(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,SearchValue1);
			
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


	public String GenerateQueryWhereClause_SQL_Non_Reg(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue ="";		
			
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
		
 return SearchValue;
}
	
	public String GenerateQueryWhereClause_SQL_Non_Reg1(String Search) {
		String SearchValue1 ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue1 =" where ( ";
 			SearchValue1 +=" lower(app.arm_desc) like ? or cast(app.total as character varying) = ?  "
 					+ "or cast(app.sc as character varying) = ? or cast(app.st as character varying) = ? or cast(app.obc as character varying) = ? )";
 		}
						
 return SearchValue1;
}


public PreparedStatement setQueryWhereClause_SQL_Non_Reg(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String SearchValue1) {
		int flag = 0;
		try {
		
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
		
		if(!SearchValue1.equals("")) {
  			flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;			
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
				flag += 1;
				stmt.setString(flag, Search);
  		}	
		}catch (Exception e) {}
		return stmt;
	}

public ArrayList<ArrayList<String>> PDF_getPer_Temp_SC_ST_OBC_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String service_status)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
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
    	
		
		q="select app.arm_desc,app.total,app.gp_a_sc,app.gp_a_st,app.gp_a_obc,\r\n" + 
				"	app.gp_b_sc,app.gp_b_st,app.gp_b_obc,\r\n" + 
				"	app.gp_c_sc,app.gp_c_st,app.gp_c_obc\r\n" + 
				"	from\r\n" + 
				"		(select distinct\r\n" + 
				"		a.arm_desc,		\r\n" + 
				"		count(*) filter (where c.civ_group in ('A','B','C') and c.category_belongs in ('1','2','3','4')) as total, \r\n" + 
				"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '2') as gp_a_sc,\r\n" + 
				"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '3') as gp_a_st,\r\n" + 
				"		count(*) filter (where c.civ_group = 'A' and c.category_belongs = '4') as gp_a_obc,\r\n" + 
				"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '2') as gp_b_sc,\r\n" + 
				"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '3') as gp_b_st,\r\n" + 
				"		count(*) filter (where c.civ_group = 'B' and c.category_belongs = '4') as gp_b_obc,\r\n" + 
				"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '2') as gp_c_sc,\r\n" + 
				"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '3') as gp_c_st,\r\n" + 
				"		count(*) filter (where c.civ_group = 'C' and c.category_belongs = '4') as gp_c_obc\r\n" + 
				"	from tb_psg_civilian_dtl_main c\r\n" + 
				"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'R'\r\n" + 
				"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
				"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
				"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
				"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + SearchValue +
				"	group by a.arm_desc) app  "  ;
		
			stmt=conn.prepareStatement(q);
			int flag =0;
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
		
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("total"));//2
				list.add(rs.getString("gp_a_sc"));//3
				list.add(rs.getString("gp_a_st"));//4
				list.add(rs.getString("gp_a_obc"));//5
				list.add(rs.getString("gp_b_sc"));//6
				list.add(rs.getString("gp_b_st"));//7
				list.add(rs.getString("gp_b_obc"));//8
				list.add(rs.getString("gp_c_sc"));//9
				list.add(rs.getString("gp_c_st"));//10
				list.add(rs.getString("gp_c_obc"));//11
							
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}
	
public ArrayList<ArrayList<String>> PDF_getNon_Regular_SC_ST_OBC_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
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
			
		q=" select app.arm_desc,app.total,app.sc,app.st,app.obc\r\n" + 
				"from\r\n" + 
				"	(select \r\n" + 
				"		a.arm_desc,	\r\n" + 
				"		count(*) filter (where  c.category_belongs in ('1','2','3','4')) as total,\r\n" + 
				"		count(*) filter (where  c.category_belongs = '2') as sc,\r\n" + 
				"		count(*) filter (where  c.category_belongs = '3') as st,\r\n" + 
				"		count(*) filter (where  c.category_belongs = '4') as obc\r\n" + 
				"	from tb_psg_civilian_dtl_main c\r\n" + 
				"	inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no and  c.status = '1' and c.civilian_status = 'NR'\r\n" + 
				"	inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
				"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active' \r\n" + 
				"	left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"	left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"	left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
				"	left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" +SearchValue + 
				"	group by a.arm_desc) app  "  ;
		
			stmt=conn.prepareStatement(q);
			
			int flag =0;
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
		
			ResultSet rs = stmt.executeQuery(); 
			int i =1;    
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("total"));//2
				list.add(rs.getString("sc"));//3
				list.add(rs.getString("st"));//4
				list.add(rs.getString("obc"));//5
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
			  }
			}
		}
	return alist;
}

}
