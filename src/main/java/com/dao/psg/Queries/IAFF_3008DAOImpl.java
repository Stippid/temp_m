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

public class IAFF_3008DAOImpl implements IAFF_3008DAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	
	public List<Map<String, Object>>getIAFF_3008_Serving_Report_ListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select distinct sr.personal_no,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
					"ltrim(TO_CHAR(sr.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
					"sr.sus_no,sr.unit_name,sr.parent_arm,sr.regiment,sr.cda_acc_no,sr.rank,sr.appointment,sr.name,sr.med_cat,\r\n" + 
					"sr.month,sr.year,sr.version,sr.status from  tb_psg_iaff_3008_serving sr\r\n" + 
					"inner join  tb_psg_iaff_3008_main_details md on sr.month= md.month and sr.year= md.year and sr.version= md.version  "
				    +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public long getIAFF_3008_Serving_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"(select distinct sr.personal_no,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
					"ltrim(TO_CHAR(sr.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
					"ltrim(TO_CHAR(sr.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
					"sr.sus_no,sr.unit_name,sr.parent_arm,sr.regiment,sr.cda_acc_no,sr.rank,sr.appointment,sr.name,sr.med_cat,\r\n" + 
					"sr.month,sr.year,sr.version,sr.status from  tb_psg_iaff_3008_serving sr\r\n" + 
					"inner join  tb_psg_iaff_3008_main_details md on sr.month= md.month and sr.year= md.year and sr.version= md.version " 
					+ SearchValue +") app1" ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
		String SearchValue ="";
		if(!Search.equals("")) {
		Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(md.sus_no) like ? or lower(sr.unit_name) like ?  or"
					+ " lower(md.cmd_unit) like ? or lower(md.corp_unit) like ?  or lower(md.div_unit) like ? "
					+ " or lower(md.bde_unit) like ? or cast(md.month as character varying) = ?  or cast(md.year as character varying) = ? )";
		}
		
		
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(md.sus_no) like ?";
		} else {
			SearchValue += " where lower(md.sus_no) like ? ";
		}
			
	}
	if(!unit_name.equals("0") && !unit_name.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(sr.unit_name) like ? ";
		} else {
			SearchValue += " where lower(sr.unit_name) like ? ";
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
	if(!month.equals("0")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.month as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.month as character varying) = ? ";
		}
	}
	if(!year.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.year as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.year as character varying)  = ?  ";
		}
	}
return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
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
			stmt.setString(flag, sus_no.toLowerCase()+"%");
		}
		if(!unit_name.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_name.toLowerCase()+"%");
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
		if(!month.equals("0")) {
			flag += 1;
			stmt.setString(flag, month);
		}
		if(!year.equals("")) {
			flag += 1;
			stmt.setString(flag, year);
		}
						
		}catch (Exception e) {}
		return stmt;
	}
////----------------------SUPER NUMARARY
public List<Map<String, Object>>getIAFF_3008_Supernumerary_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	if(pageLength.equals("-1")){
		pageLength = "ALL";
	}
	String SearchValue = GenerateQueryWhereClause_SQL_Supernumerary(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		
		q = "select distinct sp.personal_no,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(sp.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"sp.sus_no,sp.unit_name,sp.parent_arm,sp.regiment,sp.cda_acc_no,sp.rank,sp.appointment,sp.name,sp.med_cat,\r\n" + 
				"sp.month,sp.year,sp.version,sp.status from  tb_psg_iaff_3008_supernumerary sp\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on sp.month= md.month and sp.year= md.year and sp.version= md.version" 
				 +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Supernumerary(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public long getIAFF_3008_Supernumerary_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
	String SearchValue = GenerateQueryWhereClause_SQL_Supernumerary(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		q ="select count(app1.*) from\r\n" + 
				"(select distinct sp.personal_no,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(sp.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(sp.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"sp.sus_no,sp.unit_name,sp.parent_arm,sp.regiment,sp.cda_acc_no,sp.rank,sp.appointment,sp.name,sp.med_cat,\r\n" + 
				"sp.month,sp.year,sp.version,sp.status from  tb_psg_iaff_3008_supernumerary sp\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on sp.month= md.month and sp.year= md.year and sp.version= md.version " 
			     + SearchValue +") app1" ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Supernumerary(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

	public String GenerateQueryWhereClause_SQL_Supernumerary(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
		String SearchValue ="";
		if(!Search.equals("")) {
		Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(md.sus_no) like ? or lower(sp.unit_name) like ?  or"
					+ " lower(md.cmd_unit) like ? or lower(md.corp_unit) like ?  or lower(md.div_unit) like ? "
					+ " or lower(md.bde_unit) like ? or cast(md.month as character varying) = ?  or cast(md.year as character varying) = ? )";
		}
		
		
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(md.sus_no) like ?";
		} else {
			SearchValue += " where lower(md.sus_no) like ? ";
		}
			
	}
	if(!unit_name.equals("0") && !unit_name.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(sp.unit_name) like ? ";
		} else {
			SearchValue += " where lower(sp.unit_name) like ? ";
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
	if(!month.equals("0")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.month as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.month as character varying) = ? ";
		}
	}
	if(!year.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.year as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.year as character varying)  = ?  ";
		}
	}
	return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL_Supernumerary(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
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
		stmt.setString(flag, sus_no.toLowerCase()+"%");
	}
	if(!unit_name.equals("")) {
		flag += 1;
		stmt.setString(flag, unit_name.toLowerCase()+"%");
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
	if(!month.equals("0")) {
		flag += 1;
		stmt.setString(flag, month);
	}
	if(!year.equals("")) {
		flag += 1;
		stmt.setString(flag, year);
	}
					
	}catch (Exception e) {}
	return stmt;
}
//----RE EMPLOYEE MENT
public List<Map<String, Object>>getIAFF_3008_Re_Employeement_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	if(pageLength.equals("-1")){
		pageLength = "ALL";
	}
	String SearchValue = GenerateQueryWhereClause_SQL_Re_Employeement(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		
		q = "select distinct re.personal_no,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(re.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"re.sus_no,re.unit_name,re.parent_arm,re.regiment,re.cda_acc_no,re.rank,re.appointment,re.name,re.med_cat,\r\n" + 
				"re.month,re.year,re.version,re.status from  tb_psg_iaff_3008_re_employeement re\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on re.month= md.month and re.year= md.year and re.version= md.version"
				+SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Re_Employeement(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public long getIAFF_3008_Re_Employeement_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
	String SearchValue = GenerateQueryWhereClause_SQL_Re_Employeement(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		q ="select count(app1.*) from (select distinct re.personal_no,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(re.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(re.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"re.sus_no,re.unit_name,re.parent_arm,re.regiment,re.cda_acc_no,re.rank,re.appointment,re.name,re.med_cat,\r\n" + 
				"re.month,re.year,re.version,re.status from  tb_psg_iaff_3008_re_employeement re\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on re.month= md.month and re.year= md.year and re.version= md.version " + SearchValue +") app1" ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Re_Employeement(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public String GenerateQueryWhereClause_SQL_Re_Employeement(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
	String SearchValue ="";
	if(!Search.equals("")) {
		Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(md.sus_no) like ? or lower(re.unit_name) like ?  or"
					+ " lower(md.cmd_unit) like ? or lower(md.corp_unit) like ?  or lower(md.div_unit) like ? "
					+ " or lower(md.bde_unit) like ? or cast(md.month as character varying) = ?  or cast(md.year as character varying) = ? )";
		}
		
		
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(md.sus_no) like ?";
		} else {
			SearchValue += " where lower(md.sus_no) like ? ";
		}
			
	}
	if(!unit_name.equals("0") && !unit_name.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(re.unit_name) like ? ";
		} else {
			SearchValue += " where lower(re.unit_name) like ? ";
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
	if(!month.equals("0")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.month as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.month as character varying) = ? ";
		}
	}
	if(!year.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and cast(md.year as character varying)  = ? ";
		} else {
			SearchValue += " where cast(md.year as character varying)  = ?  ";
		}
	}
	return SearchValue;
	}


public PreparedStatement setQueryWhereClause_SQL_Re_Employeement(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
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
		stmt.setString(flag, sus_no.toLowerCase()+"%");
	}
	if(!unit_name.equals("")) {
		flag += 1;
		stmt.setString(flag, unit_name.toLowerCase()+"%");
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
	if(!month.equals("0")) {
		flag += 1;
		stmt.setString(flag, month);
	}
	if(!year.equals("")) {
		flag += 1;
		stmt.setString(flag, year);
	}
					
	}catch (Exception e) {}
	return stmt;
 }
//----DESERTER
public List<Map<String, Object>>getIAFF_3008_Deserter_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	if(pageLength.equals("-1")){
		pageLength = "ALL";
	}
	String SearchValue = GenerateQueryWhereClause_SQL_Deserter(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		
		q = "select distinct ds.personal_no,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(ds.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"ds.sus_no,ds.unit_name,ds.parent_arm,ds.regiment,ds.cda_acc_no,ds.rank,ds.appointment,ds.name,ds.med_cat,\r\n" + 
				"ds.month,ds.year,ds.version,ds.status from  tb_psg_iaff_3008_deserter ds\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on ds.month= md.month and ds.year= md.year and ds.version= md.version "
				+SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Deserter(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

public long getIAFF_3008_Deserter_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
	String SearchValue = GenerateQueryWhereClause_SQL_Deserter(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
	int total = 0;
	String q = null;
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		q ="select count(app1.*) from (select distinct ds.personal_no,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_tos ,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
				"ltrim(TO_CHAR(ds.tenure_date ,'DD-MON-YYYY'),'0') as tenure_date,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_birth ,'DD-MON-YYYY'),'0') as dt_of_birth,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_seniority ,'DD-MON-YYYY'),'0') as dt_of_seniority,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_commission ,'DD-MON-YYYY'),'0') as dt_of_commission,\r\n" + 
				"ltrim(TO_CHAR(ds.date_of_appointment ,'DD-MON-YYYY'),'0') as dt_of_appointment,\r\n" + 
				"ds.sus_no,ds.unit_name,ds.parent_arm,ds.regiment,ds.cda_acc_no,ds.rank,ds.appointment,ds.name,ds.med_cat,\r\n" + 
				"ds.month,ds.year,ds.version,ds.status from  tb_psg_iaff_3008_deserter ds\r\n" + 
				"inner join  tb_psg_iaff_3008_main_details md on ds.month= md.month and ds.year= md.year and ds.version= md.version "
				 + SearchValue +") app1" ;
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL_Deserter(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,month,year);
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

	public String GenerateQueryWhereClause_SQL_Deserter(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
				SearchValue =" where ( ";
				SearchValue +="lower(md.sus_no) like ? or lower(ds.unit_name) like ?  or"
						+ " lower(md.cmd_unit) like ? or lower(md.corp_unit) like ?  or lower(md.div_unit) like ? "
						+ " or lower(md.bde_unit) like ? or cast(md.month as character varying) = ?  or cast(md.year as character varying) = ? )";
			}
			
			
			if(!sus_no.equals("")){
				if (SearchValue.contains("where")) {
					SearchValue +=" and lower(md.sus_no) like ?";
			} else {
				SearchValue += " where lower(md.sus_no) like ? ";
			}
				
		}
		if(!unit_name.equals("0") && !unit_name.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(ds.unit_name) like ? ";
			} else {
				SearchValue += " where lower(ds.unit_name) like ? ";
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
		if(!month.equals("0")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and cast(md.month as character varying)  = ? ";
			} else {
				SearchValue += " where cast(md.month as character varying) = ? ";
			}
		}
		if(!year.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and cast(md.year as character varying)  = ? ";
			} else {
				SearchValue += " where cast(md.year as character varying)  = ?  ";
			}
		}
	return SearchValue;
	}


public PreparedStatement setQueryWhereClause_SQL_Deserter(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String month,String year) {
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
		stmt.setString(flag, sus_no.toLowerCase()+"%");
	}
	if(!unit_name.equals("")) {
		flag += 1;
		stmt.setString(flag, unit_name.toLowerCase()+"%");
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
	if(!month.equals("0")) {
		flag += 1;
		stmt.setString(flag, month);
	}
	if(!year.equals("")) {
		flag += 1;
		stmt.setString(flag, year.toLowerCase()+"%");
	}
					
	}catch (Exception e) {}
	return stmt;
}


}
