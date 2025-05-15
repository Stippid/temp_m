package com.dao.psg.Queries;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Auth_and_Posted_StrengthDAOImpl implements Auth_and_Posted_StrengthDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	 public List<Map<String, Object>> getAuth_and_Posted_StrengthReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select distinct \r\n"
					+ "       ab.cmd_name as cmd_unit,\r\n"
					+ "       ac.coprs_name as corp_unit,\r\n"
					+ "       ad.div_name as div_unit,\r\n"
					+ "       ae.bde_name as bde_unit,\r\n"
					+ "       a.unit_name,\r\n"
					+ "       a.form_code_control,\r\n"
					+ "       app.sus_no,\r\n"
					+ "       sum(app.offr_auth) as off_total_auth,\r\n"
					+ "       sum(app.joc_auth) as jco_total_auth,\r\n"
					+ "       sum(app.or_auth) as or_total_auth,\r\n"
					+ "       sum(app.civ_auth) as civ_total_auth,\r\n"
					+ "       sum(app.offr_held) as offr,\r\n"
					+ "       sum(app.jco_held) as jco,\r\n"
					+ "       sum(app.or_held) as or1, \r\n"
					+ "       sum(app.civ_held) as civ,\r\n"
					+ "        sum(app.offr_auth) + sum(app.joc_auth) + sum(app.or_auth) + sum(app.civ_auth) as auth_total,\r\n"
					+ "         sum(app.offr_held) + sum(app.jco_held) + sum(app.or_held) + sum(app.civ_held) as held_total"
					+ "  from (\r\n"
					+ "        select orb.sus_no,\r\n"
					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '0') as offr_auth,\r\n"
					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '1') as joc_auth,\r\n"
					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('2','3')) as or_auth,\r\n"
					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('4','5','6','9','7','8','10','11','12')) as civ_auth,\r\n"
					+ "               0 as offr_held,\r\n"
					+ "               0 as jco_held,\r\n"
					+ "               0 as or_held,\r\n"
					+ "               0 as civ_held\r\n"
					+ "          from sus_pers_stdauth a\r\n"
					+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
					+ "            on orb.sus_no = a.sus_no\r\n"
					+ "           and orb.status_sus_no='Active'\r\n"
					+ "          left join tb_miso_orbat_arm_code b\r\n"
					+ "            on b.arm_code = a.arm\r\n"
					+ "          left join t_domain_value c\r\n"
					+ "            on c.codevalue = a.cat_id\r\n"
					+ "           and c.domainid = 'PERSON_CAT'\r\n"
					+ "           and a.cat_id in('1','2')\r\n"
					+ "         group by orb.sus_no\r\n"
					+ "     union all select unit_sus_no as sus_no,\r\n"
					+ "               0 as offr_auth,\r\n"
					+ "               0 as joc_auth,\r\n"
					+ "               0 as or_auth,\r\n"
					+ "               0 as civ_auth,\r\n"
					+ "               count(*) as offr_held,\r\n"
					+ "               0 as jco_held,\r\n"
					+ "               0 as or_held,\r\n"
					+ "               0 as civ_held\r\n"
					+ "          from tb_psg_trans_proposed_comm_letter\r\n"
					+ "         where status in ('1', '5')\r\n"
					+ "         group by unit_sus_no\r\n"
					+ "     union all select unit_sus_no as sus_no,\r\n"
					+ "               0 as offr_auth,\r\n"
					+ "               0 as joc_auth,\r\n"
					+ "               0 as or_auth,\r\n"
					+ "               0 as civ_auth,\r\n"
					+ "               0 as offr_held,\r\n"
					+ "               count(*) as jco_held,\r\n"
					+ "               0 as or_held,\r\n"
					+ "               0 as civ_held\r\n"
					+ "          from tb_psg_census_jco_or_p\r\n"
					+ "         where category = 'JCO'\r\n"
					+ "           and status = '1'\r\n"
					+ "         group by unit_sus_no\r\n"
					+ "     union all select unit_sus_no as sus_no,\r\n"
					+ "               0 as offr_auth,\r\n"
					+ "               0 as joc_auth,\r\n"
					+ "               0 as or_auth,\r\n"
					+ "               0 as civ_auth,\r\n"
					+ "               0 as offr_held,\r\n"
					+ "               0 as jco_held,\r\n"
					+ "               count(*) as or_held,\r\n"
					+ "               0 as civ_held\r\n"
					+ "          from tb_psg_census_jco_or_p\r\n"
					+ "         where category = 'OR'\r\n"
					+ "           and status = '1'\r\n"
					+ "         group by unit_sus_no\r\n"
					+ "     union all select a.sus_no as sus_no,\r\n"
					+ "               0 as offr_auth,\r\n"
					+ "               0 as joc_auth,\r\n"
					+ "               0 as or_auth,\r\n"
					+ "               0 as civ_auth,\r\n"
					+ "               0 as offr_held,\r\n"
					+ "               0 as jco_held,\r\n"
					+ "               0 as or_held,\r\n"
					+ "               count(*) as civ_held\r\n"
					+ "          from tb_psg_civilian_dtl_main a\r\n"
					+ "         where a.status = 1\r\n"
					+ "         group by a.sus_no\r\n"
					+ "       )app \r\n"
					+ "  inner join   tb_miso_orbat_unt_dtl a\r\n"
					+ "  on app.sus_no=a.sus_no\r\n"
					+ "  and a.status_sus_no = 'Active'\r\n"
					+ "  left join orbat_view_corps ac\r\n"
					+ "    on substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "  left join orbat_view_cmd ab\r\n"
					+ "    on substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "  left join orbat_view_div ad\r\n"
					+ "    on substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "  left join orbat_view_bde ae\r\n"
					+ "    on substr(a.form_code_control::text, 7, 4) = ae.bde_code::text   \r\n"
					+ ""+SearchValue+""
					+ " group by app.sus_no,ab.cmd_name ,\r\n"
					+ "       ac.coprs_name,\r\n"
					+ "       ad.div_name ,\r\n"
					+ "       ae.bde_name ,\r\n"
					+ "       a.unit_name,a.form_code_control\r\n"
					+ " ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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


	 public long getAuth_and_Posted_StrengthTotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
	 		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
	 		int total = 0;
	 		String q = null;
	 		Connection conn = null;
	 		try {
	 			conn = dataSource.getConnection();
	 			q ="select count(app1.*) from\r\n" + 
	 					"(select distinct \r\n"
	 					+ "       ab.cmd_name as cmd_unit,\r\n"
	 					+ "       ac.coprs_name as corp_unit,\r\n"
	 					+ "       ad.div_name as div_unit,\r\n"
	 					+ "       ae.bde_name as bde_unit,\r\n"
	 					+ "       a.unit_name,\r\n"
	 					+ "       a.form_code_control,\r\n"
	 					+ "       app.sus_no,\r\n"
	 					+ "       sum(app.offr_auth) as off_total_auth,\r\n"
	 					+ "       sum(app.joc_auth) as jco_total_auth,\r\n"
	 					+ "       sum(app.or_auth) as or_total_auth,\r\n"
	 					+ "       sum(app.civ_auth) as civ_total_auth,\r\n"
	 					+ "       sum(app.offr_held) as offr,\r\n"
	 					+ "       sum(app.jco_held) as jco,\r\n"
	 					+ "       sum(app.or_held) as or1, \r\n"
	 					+ "       sum(app.civ_held) as civ\r\n"
	 					+ "  from (\r\n"
	 					+ "        select orb.sus_no,\r\n"
	 					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '0') as offr_auth,\r\n"
	 					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '1') as joc_auth,\r\n"
	 					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('2','3')) as or_auth,\r\n"
	 					+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('4','5','6','9','7','8','10','11','12')) as civ_auth,\r\n"
	 					+ "               0 as offr_held,\r\n"
	 					+ "               0 as jco_held,\r\n"
	 					+ "               0 as or_held,\r\n"
	 					+ "               0 as civ_held\r\n"
	 					+ "          from sus_pers_stdauth a\r\n"
	 					+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
	 					+ "            on orb.sus_no = a.sus_no\r\n"
	 					+ "           and orb.status_sus_no='Active'\r\n"
	 					+ "          left join tb_miso_orbat_arm_code b\r\n"
	 					+ "            on b.arm_code = a.arm\r\n"
	 					+ "          left join t_domain_value c\r\n"
	 					+ "            on c.codevalue = a.cat_id\r\n"
	 					+ "           and c.domainid = 'PERSON_CAT'\r\n"
	 					+ "           and a.cat_id in('1','2')\r\n"
	 					+ "         group by orb.sus_no\r\n"
	 					+ "     union all select unit_sus_no as sus_no,\r\n"
	 					+ "               0 as offr_auth,\r\n"
	 					+ "               0 as joc_auth,\r\n"
	 					+ "               0 as or_auth,\r\n"
	 					+ "               0 as civ_auth,\r\n"
	 					+ "               count(*) as offr_held,\r\n"
	 					+ "               0 as jco_held,\r\n"
	 					+ "               0 as or_held,\r\n"
	 					+ "               0 as civ_held\r\n"
	 					+ "          from tb_psg_trans_proposed_comm_letter\r\n"
	 					+ "         where status in ('1', '5')\r\n"
	 					+ "         group by unit_sus_no\r\n"
	 					+ "     union all select unit_sus_no as sus_no,\r\n"
	 					+ "               0 as offr_auth,\r\n"
	 					+ "               0 as joc_auth,\r\n"
	 					+ "               0 as or_auth,\r\n"
	 					+ "               0 as civ_auth,\r\n"
	 					+ "               0 as offr_held,\r\n"
	 					+ "               count(*) as jco_held,\r\n"
	 					+ "               0 as or_held,\r\n"
	 					+ "               0 as civ_held\r\n"
	 					+ "          from tb_psg_census_jco_or_p\r\n"
	 					+ "         where category = 'JCO'\r\n"
	 					+ "           and status = '1'\r\n"
	 					+ "         group by unit_sus_no\r\n"
	 					+ "     union all select unit_sus_no as sus_no,\r\n"
	 					+ "               0 as offr_auth,\r\n"
	 					+ "               0 as joc_auth,\r\n"
	 					+ "               0 as or_auth,\r\n"
	 					+ "               0 as civ_auth,\r\n"
	 					+ "               0 as offr_held,\r\n"
	 					+ "               0 as jco_held,\r\n"
	 					+ "               count(*) as or_held,\r\n"
	 					+ "               0 as civ_held\r\n"
	 					+ "          from tb_psg_census_jco_or_p\r\n"
	 					+ "         where category = 'OR'\r\n"
	 					+ "           and status = '1'\r\n"
	 					+ "         group by unit_sus_no\r\n"
	 					+ "     union all select a.sus_no as sus_no,\r\n"
	 					+ "               0 as offr_auth,\r\n"
	 					+ "               0 as joc_auth,\r\n"
	 					+ "               0 as or_auth,\r\n"
	 					+ "               0 as civ_auth,\r\n"
	 					+ "               0 as offr_held,\r\n"
	 					+ "               0 as jco_held,\r\n"
	 					+ "               0 as or_held,\r\n"
	 					+ "               count(*) as civ_held\r\n"
	 					+ "          from tb_psg_civilian_dtl_main a\r\n"
	 					+ "         where a.status = 1\r\n"
	 					+ "         group by a.sus_no\r\n"
	 					+ "       )app \r\n"
	 					+ "  inner join   tb_miso_orbat_unt_dtl a\r\n"
	 					+ "  on app.sus_no=a.sus_no\r\n"
	 					+ "  and a.status_sus_no = 'Active'\r\n"
	 					+ "  left join orbat_view_corps ac\r\n"
	 					+ "    on substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
	 					+ "  left join orbat_view_cmd ab\r\n"
	 					+ "    on substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
	 					+ "  left join orbat_view_div ad\r\n"
	 					+ "    on substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
	 					+ "  left join orbat_view_bde ae\r\n"
	 					+ "    on substr(a.form_code_control::text, 7, 4) = ae.bde_code::text   \r\n"
	 					+ ""+SearchValue+""
	 					+ " group by app.sus_no,ab.cmd_name ,\r\n"
	 					+ "       ac.coprs_name,\r\n"
	 					+ "       ad.div_name ,\r\n"
	 					+ "       ae.bde_name ,\r\n"
	 					+ "       a.unit_name,a.form_code_control) app1" ;
	 			PreparedStatement stmt = conn.prepareStatement(q);
	 			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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


public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue ="";
		if(!Search.equals("")) {
		Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(app.sus_no) like ? or lower(a.unit_name) like ? or"
					+ " lower(ab.cmd_name) like ? or lower(ac.coprs_name) like ?  or lower(ac.coprs_name) like ? "
					+ " or lower(ae.bde_name) like ? "
					+ "or cast(app.offr_auth as character varying) = ? or cast(app.joc_auth as character varying) = ? "
					+ "or cast(app.or_auth as character varying) = ? or cast(app.civ_auth as character varying) = ? "
					+ "or cast(app.offr_held as character varying) = ? or cast(app.jco_held as character varying) = ?"
					+ " or cast(app.or_held as character varying) = ?  or cast(app.civ_held as character varying) = ? )";
		}
		
		
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(app.sus_no) like ?";
		} else {
			SearchValue += " where lower(app.sus_no) like ? ";
		}
			
	}
	if(!unit_name.equals("0") && !unit_name.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(a.unit_name) like ? ";
		} else {
			SearchValue += " where lower(a.unit_name) like ? ";
		}
	} 
	
	if(!cont_bde.equals("0") && !cont_bde.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and a.form_code_control = ? ";
		} else {
			SearchValue += " where a.form_code_control = ? ";
		}
	}else {
		if(!cont_div.equals("0") && !cont_div.equals("")){
			if (SearchValue.contains("where")) {
    			SearchValue +=" and a.form_code_control like ? ";
			} else {
				SearchValue += " where a.form_code_control like ? ";
			}
    	}else {
    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and a.form_code_control like ? ";
    			} else {
    				SearchValue += " where a.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
	    			if (SearchValue.contains("where")) {
	    				SearchValue +=" and a.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where a.form_code_control like ? ";
	    			}
		    	}
	    	}
	    }
    }
	
		/*if(!dt_from.equals("") && !dt_to.equals("")) {
		SearchValue +="and cast(c.rv_dt as date) between cast(? as date) and cast(? as date)";
	}*/
return SearchValue;
}
		

  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
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
			
			/*if(!crv_dt_from.equals("") && !crv_dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, crv_dt_from);
				flag+=1;
				stmt.setString(flag, crv_dt_to);
			}*/
 				
 			
 		}catch (Exception e) {}
 		return stmt;
 	}
  
  public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name, String sus_no)
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
    				SearchValue +=" and lower(app.sus_no) = ?";
  			} else {
  				SearchValue += " where lower(app.sus_no) = ? ";
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
      
  		
  		q="select app.* from ( select distinct \r\n"
  				+ "       ab.cmd_name as cmd_unit,\r\n"
  				+ "       ac.coprs_name as corp_unit,\r\n"
  				+ "       ad.div_name as div_unit,\r\n"
  				+ "       ae.bde_name as bde_unit,\r\n"
  				+ "       a.unit_name,\r\n"
  				+ "       a.form_code_control,\r\n"
  				+ "       app.sus_no,\r\n"
  				+ "       sum(app.offr_auth) as off_total_auth,\r\n"
  				+ "       sum(app.joc_auth) as jco_total_auth,\r\n"
  				+ "       sum(app.or_auth) as or_total_auth,\r\n"
  				+ "       sum(app.civ_auth) as civ_total_auth,\r\n"
  				+ "       sum(app.offr_held) as offr,\r\n"
  				+ "       sum(app.jco_held) as jco,\r\n"
  				+ "       sum(app.or_held) as or1, \r\n"
  				+ "       sum(app.civ_held) as civ,\r\n"
  				+ "        sum(app.offr_auth) + sum(app.joc_auth) + sum(app.or_auth) + sum(app.civ_auth) as auth_total,\r\n"
  				+ "         sum(app.offr_held) + sum(app.jco_held) + sum(app.or_held) + sum(app.civ_held) as held_total  from (\r\n"
  				+ "        select orb.sus_no,\r\n"
  				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '0') as offr_auth,\r\n"
  				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat = '1') as joc_auth,\r\n"
  				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('2','3')) as or_auth,\r\n"
  				+ "               sum(a.base_auth + a.mod_auth + a.foot_auth) filter (where a.rank_cat in ('4','5','6','9','7','8','10','11','12')) as civ_auth,\r\n"
  				+ "               0 as offr_held,\r\n"
  				+ "               0 as jco_held,\r\n"
  				+ "               0 as or_held,\r\n"
  				+ "               0 as civ_held\r\n"
  				+ "          from sus_pers_stdauth a\r\n"
  				+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
  				+ "            on orb.sus_no = a.sus_no\r\n"
  				+ "           and orb.status_sus_no='Active'\r\n"
  				+ "          left join tb_miso_orbat_arm_code b\r\n"
  				+ "            on b.arm_code = a.arm\r\n"
  				+ "          left join t_domain_value c\r\n"
  				+ "            on c.codevalue = a.cat_id\r\n"
  				+ "           and c.domainid = 'PERSON_CAT'\r\n"
  				+ "           and a.cat_id in('1','2')\r\n"
  				+ "         group by orb.sus_no\r\n"
  				+ "     union all select unit_sus_no as sus_no,\r\n"
  				+ "               0 as offr_auth,\r\n"
  				+ "               0 as joc_auth,\r\n"
  				+ "               0 as or_auth,\r\n"
  				+ "               0 as civ_auth,\r\n"
  				+ "               count(*) as offr_held,\r\n"
  				+ "               0 as jco_held,\r\n"
  				+ "               0 as or_held,\r\n"
  				+ "               0 as civ_held\r\n"
  				+ "          from tb_psg_trans_proposed_comm_letter\r\n"
  				+ "         where status in ('1', '5')\r\n"
  				+ "         group by unit_sus_no\r\n"
  				+ "     union all select unit_sus_no as sus_no,\r\n"
  				+ "               0 as offr_auth,\r\n"
  				+ "               0 as joc_auth,\r\n"
  				+ "               0 as or_auth,\r\n"
  				+ "               0 as civ_auth,\r\n"
  				+ "               0 as offr_held,\r\n"
  				+ "               count(*) as jco_held,\r\n"
  				+ "               0 as or_held,\r\n"
  				+ "               0 as civ_held\r\n"
  				+ "          from tb_psg_census_jco_or_p\r\n"
  				+ "         where category = 'JCO'\r\n"
  				+ "           and status = '1'\r\n"
  				+ "         group by unit_sus_no\r\n"
  				+ "     union all select unit_sus_no as sus_no,\r\n"
  				+ "               0 as offr_auth,\r\n"
  				+ "               0 as joc_auth,\r\n"
  				+ "               0 as or_auth,\r\n"
  				+ "               0 as civ_auth,\r\n"
  				+ "               0 as offr_held,\r\n"
  				+ "               0 as jco_held,\r\n"
  				+ "               count(*) as or_held,\r\n"
  				+ "               0 as civ_held\r\n"
  				+ "          from tb_psg_census_jco_or_p\r\n"
  				+ "         where category = 'OR'\r\n"
  				+ "           and status = '1'\r\n"
  				+ "         group by unit_sus_no\r\n"
  				+ "     union all select a.sus_no as sus_no,\r\n"
  				+ "               0 as offr_auth,\r\n"
  				+ "               0 as joc_auth,\r\n"
  				+ "               0 as or_auth,\r\n"
  				+ "               0 as civ_auth,\r\n"
  				+ "               0 as offr_held,\r\n"
  				+ "               0 as jco_held,\r\n"
  				+ "               0 as or_held,\r\n"
  				+ "               count(*) as civ_held\r\n"
  				+ "          from tb_psg_civilian_dtl_main a\r\n"
  				+ "         where a.status = 1\r\n"
  				+ "         group by a.sus_no\r\n"
  				+ "       )app \r\n"
  				+ "  inner join   tb_miso_orbat_unt_dtl a\r\n"
  				+ "  on app.sus_no=a.sus_no\r\n"
  				+ "  and a.status_sus_no = 'Active'\r\n"
  				+ "  left join orbat_view_corps ac\r\n"
  				+ "    on substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
  				+ "  left join orbat_view_cmd ab\r\n"
  				+ "    on substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
  				+ "  left join orbat_view_div ad\r\n"
  				+ "    on substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
  				+ "  left join orbat_view_bde ae\r\n"
  				+ "    on substr(a.form_code_control::text, 7, 4) = ae.bde_code::text   \r\n"
  				+ " group by app.sus_no,ab.cmd_name ,\r\n"
  				+ "       ac.coprs_name,\r\n"
  				+ "       ad.div_name ,\r\n"
  				+ "       ae.bde_name ,\r\n"
  				+ "       a.unit_name,a.form_code_control) app " + SearchValue ;
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
  				list.add(rs.getString("cmd_unit"));//1
  				list.add(rs.getString("corp_unit"));//2
  				list.add(rs.getString("div_unit"));//3
  				list.add(rs.getString("bde_unit"));//4
  				list.add(rs.getString("unit_name"));//5
//  				list.add(rs.getString("unit_sus_no"));//6
  				list.add(rs.getString("off_total_auth"));//7
  				list.add(rs.getString("offr"));//8
  				list.add(rs.getString("jco_total_auth"));//9
  				list.add(rs.getString("jco"));//10
  				list.add(rs.getString("or_total_auth"));//11
  				list.add(rs.getString("or1"));//12
  				list.add(rs.getString("civ_total_auth"));//13
  				list.add(rs.getString("civ"));//14
  				list.add(rs.getString("auth_total"));//15
  				list.add(rs.getString("held_total"));//16
  							
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
