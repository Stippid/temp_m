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

public class NOK_Details_ContactNo_DAOImpl implements NOK_Details_ContactNo_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getNOK_Details_ContactNo_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			
			q = " SELECT app.cmd_unit,\r\n"
					+ "       app.corp_unit,\r\n"
					+ "       app.div_unit,\r\n"
					+ "       app.bde_unit,\r\n"
					+ "       app.unit_name,\r\n"
					+ "       app.unit_sus_no,\r\n"
					+ "       app.unit_name,\r\n"
					+ "       app.form_code_control,\r\n"
					+ "       app.offr,\r\n"
					+ "       app.jco,\r\n"
					+ "       app.or1,\r\n"
					+ "       app.ent,\r\n"
					+ "       app.not_ent\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
					+ "               ac.coprs_name AS corp_unit,\r\n"
					+ "               ad.div_name AS div_unit,\r\n"
					+ "               ae.bde_name AS bde_unit,\r\n"
					+ "               orb.unit_name,\r\n"
					+ "               hld.unit_sus_no,\r\n"
					+ "               orb.form_code_control,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OFFR' AND hld.nok_id != 0) AS offr,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'JCO' AND hld.nok_id != 0) AS jco,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OR' AND hld.nok_id != 0) AS or1,\r\n"
					+ "               count(hld.nok_id) filter (WHERE hld.nok_id != 0) AS ent,\r\n"
					+ "               count(hld.nok_id) filter (WHERE hld.nok_id = 0) AS not_ent\r\n"
					+ "          FROM (\r\n"
					+ "                SELECT c.name,\r\n"
					+ "                       c.personnel_no AS army_no,\r\n"
					+ "                       d.description,\r\n"
					+ "                       n.nok_name,\r\n"
					+ "                       n.nok_relation,\r\n"
					+ "                       pgp_sym_decrypt(n.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
					+ "                       c.unit_sus_no,\r\n"
					+ "                       'OFFR' AS typ,\r\n"
					+ "                       CASE WHEN n.id IS NULL THEN 0\r\n"
					+ "                            ELSE n.id\r\n"
					+ "                             END AS nok_id\r\n"
					+ "                  FROM tb_psg_trans_proposed_comm_letter c\r\n"
					+ "                 INNER JOIN cue_tb_psg_rank_app_master d\r\n"
					+ "                    ON d.id = c.rank\r\n"
					+ "                   AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
					+ "                  LEFT JOIN tb_psg_census_nok n\r\n"
					+ "                    ON c.id = n.comm_id\r\n"
					+ "                   AND n.status = 1\r\n"
					+ "                   AND c.status IN ('1','5')\r\n"
					+ "             UNION ALL SELECT j.full_name AS name,\r\n"
					+ "                       j.army_no,\r\n"
					+ "                       r.rank AS description,\r\n"
					+ "                       n_j.nok_name,\r\n"
					+ "                       n_j.nok_relation,\r\n"
					+ "                       pgp_sym_decrypt(n_j.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
					+ "                       j.unit_sus_no,\r\n"
					+ "                       CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
					+ "                            ELSE 'OR'\r\n"
					+ "                             END AS typ,\r\n"
					+ "                       CASE WHEN n_j.id IS NULL THEN 0\r\n"
					+ "                            ELSE n_j.id\r\n"
					+ "                             END AS nok_id\r\n"
					+ "                  FROM tb_psg_census_jco_or_p j\r\n"
					+ "                 INNER JOIN tb_psg_mstr_rank_jco r\r\n"
					+ "                    ON r.id = j.rank\r\n"
					+ "                   AND j.status IN ('1','5')\r\n"
					+ "                  LEFT JOIN tb_psg_census_nok_jco n_j\r\n"
					+ "                    ON j.id = n_j.jco_id\r\n"
					+ "                   AND n_j.status = 1\r\n"
					+ "               ) hld\r\n"
					+ "         INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
					+ "            ON orb.sus_no = hld.unit_sus_no\r\n"
					+ "           AND orb.status_sus_no = 'Active'\r\n"
					+ "          LEFT JOIN tb_psg_mstr_relation rel\r\n"
					+ "            ON rel.id = hld.nok_relation\r\n"
					+ "          LEFT JOIN orbat_view_corps ac\r\n"
					+ "            ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "          LEFT JOIN orbat_view_cmd ab\r\n"
					+ "            ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "          LEFT JOIN orbat_view_div ad\r\n"
					+ "            ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "          LEFT JOIN orbat_view_bde ae\r\n"
					+ "            ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ "         GROUP BY cmd_unit,\r\n"
					+ "                  corp_unit,\r\n"
					+ "                  div_unit,\r\n"
					+ "                  bde_unit,\r\n"
					+ "                  orb.unit_name,\r\n"
					+ "                  hld.unit_sus_no,\r\n"
					+ "                  orb.form_code_control\r\n"
					+ "       ) app\r\n"
					+ "" +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
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
	
	public long getNOK_Details_ContactNo_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"			(SELECT app.cmd_unit,\r\n"
					+ "       app.corp_unit,\r\n"
					+ "       app.div_unit,\r\n"
					+ "       app.bde_unit,\r\n"
					+ "       app.unit_name,\r\n"
					+ "       app.unit_sus_no,\r\n"
					+ "       app.unit_name,\r\n"
					+ "       app.form_code_control,\r\n"
					+ "       app.offr,\r\n"
					+ "       app.jco,\r\n"
					+ "       app.or1,\r\n"
					+ "       app.ent,\r\n"
					+ "       app.not_ent\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
					+ "               ac.coprs_name AS corp_unit,\r\n"
					+ "               ad.div_name AS div_unit,\r\n"
					+ "               ae.bde_name AS bde_unit,\r\n"
					+ "               orb.unit_name,\r\n"
					+ "               hld.unit_sus_no,\r\n"
					+ "               orb.form_code_control,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OFFR' AND hld.nok_id != 0) AS offr,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'JCO' AND hld.nok_id != 0) AS jco,\r\n"
					+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OR' AND hld.nok_id != 0) AS or1,\r\n"
					+ "               count(hld.nok_id) filter (WHERE hld.nok_id != 0) AS ent,\r\n"
					+ "               count(hld.nok_id) filter (WHERE hld.nok_id = 0) AS not_ent\r\n"
					+ "          FROM (\r\n"
					+ "                SELECT c.name,\r\n"
					+ "                       c.personnel_no AS army_no,\r\n"
					+ "                       d.description,\r\n"
					+ "                       n.nok_name,\r\n"
					+ "                       n.nok_relation,\r\n"
					+ "                       pgp_sym_decrypt(n.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
					+ "                       c.unit_sus_no,\r\n"
					+ "                       'OFFR' AS typ,\r\n"
					+ "                       CASE WHEN n.id IS NULL THEN 0\r\n"
					+ "                            ELSE n.id\r\n"
					+ "                             END AS nok_id\r\n"
					+ "                  FROM tb_psg_trans_proposed_comm_letter c\r\n"
					+ "                 INNER JOIN cue_tb_psg_rank_app_master d\r\n"
					+ "                    ON d.id = c.rank\r\n"
					+ "                   AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
					+ "                  LEFT JOIN tb_psg_census_nok n\r\n"
					+ "                    ON c.id = n.comm_id\r\n"
					+ "                   AND n.status = 1\r\n"
					+ "                   AND c.status IN ('1','5')\r\n"
					+ "             UNION ALL SELECT j.full_name AS name,\r\n"
					+ "                       j.army_no,\r\n"
					+ "                       r.rank AS description,\r\n"
					+ "                       n_j.nok_name,\r\n"
					+ "                       n_j.nok_relation,\r\n"
					+ "                       pgp_sym_decrypt(n_j.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
					+ "                       j.unit_sus_no,\r\n"
					+ "                       CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
					+ "                            ELSE 'OR'\r\n"
					+ "                             END AS typ,\r\n"
					+ "                       CASE WHEN n_j.id IS NULL THEN 0\r\n"
					+ "                            ELSE n_j.id\r\n"
					+ "                             END AS nok_id\r\n"
					+ "                  FROM tb_psg_census_jco_or_p j\r\n"
					+ "                 INNER JOIN tb_psg_mstr_rank_jco r\r\n"
					+ "                    ON r.id = j.rank\r\n"
					+ "                   AND j.status IN ('1','5')\r\n"
					+ "                  LEFT JOIN tb_psg_census_nok_jco n_j\r\n"
					+ "                    ON j.id = n_j.jco_id\r\n"
					+ "                   AND n_j.status = 1\r\n"
					+ "               ) hld\r\n"
					+ "         INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
					+ "            ON orb.sus_no = hld.unit_sus_no\r\n"
					+ "           AND orb.status_sus_no = 'Active'\r\n"
					+ "          LEFT JOIN tb_psg_mstr_relation rel\r\n"
					+ "            ON rel.id = hld.nok_relation\r\n"
					+ "          LEFT JOIN orbat_view_corps ac\r\n"
					+ "            ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "          LEFT JOIN orbat_view_cmd ab\r\n"
					+ "            ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "          LEFT JOIN orbat_view_div ad\r\n"
					+ "            ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "          LEFT JOIN orbat_view_bde ae\r\n"
					+ "            ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ "         GROUP BY cmd_unit,\r\n"
					+ "                  corp_unit,\r\n"
					+ "                  div_unit,\r\n"
					+ "                  bde_unit,\r\n"
					+ "                  orb.unit_name,\r\n"
					+ "                  hld.unit_sus_no,\r\n"
					+ "                  orb.form_code_control\r\n"
					+ "       ) app\r\n"
					+ "" +SearchValue +") app1";
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
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? "
 					+ "or cast(app.offr as character varying) = ? or cast(app.jco as character varying) = ? "
 					+ "or cast(app.or1 as character varying) = ? )";
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
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
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
  
  
  
  
  
  
  
  public List<Map<String, Object>> getNOK_Details_ContactNo_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.unit_name,app.form_code_control,\r\n" + 
					"app.army_no,app.description,app.name,app.nok_name,app.relation_name,app.nok_mobile_no\r\n" + 
					"	from (	\r\n" + 
					"			select distinct \r\n" + 
					"				fv.unit_name as cmd_unit,\r\n" + 
					"				fvm.unit_name as corp_unit,\r\n" + 
					"				div.unit_name as div_unit,\r\n" + 
					"				bde.unit_name as bde_unit,\r\n" + 
					"				orb.unit_name,\r\n" + 
					"				hld.unit_sus_no,\r\n" + 
					"				hld.army_no,\r\n" + 
					"				hld.description,\r\n" + 
					"				hld.name,\r\n" + 
					"				hld.nok_name,\r\n" + 
					"				rel.relation_name,\r\n" + 
					"				hld.nok_mobile_no,\r\n" + 
					"				orb.form_code_control\r\n" + 
					"				from (\r\n" + 
					"						select c.name,c.personnel_no as army_no,d.description,n.nok_name,n.nok_relation,PGP_SYM_DECRYPT(n.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
					"							 c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"						 inner join tb_psg_census_nok n on c.id= n.comm_id and n.status = 1\r\n" + 
					"					union all	 \r\n" + 
					"						 select j.full_name as name,j.army_no,r.rank as description,n_j.nok_name,n_j.nok_relation,PGP_SYM_DECRYPT(n_j.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
					"							 j.unit_sus_no, case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"						 inner join tb_psg_mstr_rank_jco r on r.id = j.rank and j.status in ('1','5')\r\n" + 
					"						 inner join tb_psg_census_nok_jco n_j on j.id= n_j.jco_id and n_j.status = 1\r\n" + 
					"				) hld 						\r\n" + 
					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				inner join tb_psg_mstr_relation rel on rel.id = hld.nok_relation\r\n" + 
					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"				group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,hld.army_no,\r\n" + 
					"				hld.description,hld.name,hld.nok_name,rel.relation_name,hld.nok_mobile_no,orb.form_code_control ) app " +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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

public long getNOK_Details_ContactNo_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"	(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.unit_name,app.form_code_control,\r\n" + 
					"app.army_no,app.description,app.name,app.nok_name,app.relation_name,app.nok_mobile_no\r\n" + 
					"	from (	\r\n" + 
					"			select distinct \r\n" + 
					"				fv.unit_name as cmd_unit,\r\n" + 
					"				fvm.unit_name as corp_unit,\r\n" + 
					"				div.unit_name as div_unit,\r\n" + 
					"				bde.unit_name as bde_unit,\r\n" + 
					"				orb.unit_name,\r\n" + 
					"				hld.unit_sus_no,\r\n" + 
					"				hld.army_no,\r\n" + 
					"				hld.description,\r\n" + 
					"				hld.name,\r\n" + 
					"				hld.nok_name,\r\n" + 
					"				rel.relation_name,\r\n" + 
					"				hld.nok_mobile_no,\r\n" + 
					"				orb.form_code_control\r\n" + 
					"				from (\r\n" + 
					"						select c.name,c.personnel_no as army_no,d.description,n.nok_name,n.nok_relation,PGP_SYM_DECRYPT(n.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
					"							 c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"						 inner join tb_psg_census_nok n on c.id= n.comm_id and n.status = 1\r\n" + 
					"					union all	 \r\n" + 
					"						 select j.full_name as name,j.army_no,r.rank as description,n_j.nok_name,n_j.nok_relation,PGP_SYM_DECRYPT(n_j.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
					"							 j.unit_sus_no, case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"						 inner join tb_psg_mstr_rank_jco r on r.id = j.rank and j.status in ('1','5')\r\n" + 
					"						 inner join tb_psg_census_nok_jco n_j on j.id= n_j.jco_id and n_j.status = 1\r\n" + 
					"				) hld 						\r\n" + 
					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				inner join tb_psg_mstr_relation rel on rel.id = hld.nok_relation\r\n" + 
					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"				group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,hld.army_no,\r\n" + 
					"				hld.description,hld.name,hld.nok_name,rel.relation_name,hld.nok_mobile_no,orb.form_code_control ) app " + 
					"" + SearchValue +
					") app1  " ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
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


	public String GenerateQueryWhereClause_SQL_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +=" lower(app.unit_sus_no) like ? or lower(app.unit_name) like ?  or "
					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? " 
					+ "or lower(app.bde_unit) like ? or lower(app.army_no) like ? "
					+ "or lower(app.description) like ? or lower(app.name) like ? or lower(app.nok_name) like ? "
					+ "or lower(app.relation_name) like ? or cast(app.nok_mobile_no as character varying) like ?"
					+ ")";
			
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


public PreparedStatement setQueryWhereClause_SQL_pers(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
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


public ArrayList<ArrayList<String>> pdf_getNOK_Details_ContactNo_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
		
		q="	SELECT app.cmd_unit,\r\n"
				+ "       app.corp_unit,\r\n"
				+ "       app.div_unit,\r\n"
				+ "       app.bde_unit,\r\n"
				+ "       app.unit_name,\r\n"
				+ "       app.unit_sus_no,\r\n"
				+ "       app.unit_name,\r\n"
				+ "       app.form_code_control,\r\n"
				+ "       app.offr,\r\n"
				+ "       app.jco,\r\n"
				+ "       app.or1,\r\n"
				+ "       app.ent,\r\n"
				+ "       app.not_ent\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
				+ "               ac.coprs_name AS corp_unit,\r\n"
				+ "               ad.div_name AS div_unit,\r\n"
				+ "               ae.bde_name AS bde_unit,\r\n"
				+ "               orb.unit_name,\r\n"
				+ "               hld.unit_sus_no,\r\n"
				+ "               orb.form_code_control,\r\n"
				+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OFFR' AND hld.nok_id != 0) AS offr,\r\n"
				+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'JCO' AND hld.nok_id != 0) AS jco,\r\n"
				+ "               count(hld.unit_sus_no) filter (WHERE hld.typ = 'OR' AND hld.nok_id != 0) AS or1,\r\n"
				+ "               count(hld.nok_id) filter (WHERE hld.nok_id != 0) AS ent,\r\n"
				+ "               count(hld.nok_id) filter (WHERE hld.nok_id = 0) AS not_ent\r\n"
				+ "          FROM (\r\n"
				+ "                SELECT c.name,\r\n"
				+ "                       c.personnel_no AS army_no,\r\n"
				+ "                       d.description,\r\n"
				+ "                       n.nok_name,\r\n"
				+ "                       n.nok_relation,\r\n"
				+ "                       pgp_sym_decrypt(n.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
				+ "                       c.unit_sus_no,\r\n"
				+ "                       'OFFR' AS typ,\r\n"
				+ "                       CASE WHEN n.id IS NULL THEN 0\r\n"
				+ "                            ELSE n.id\r\n"
				+ "                             END AS nok_id\r\n"
				+ "                  FROM tb_psg_trans_proposed_comm_letter c\r\n"
				+ "                 INNER JOIN cue_tb_psg_rank_app_master d\r\n"
				+ "                    ON d.id = c.rank\r\n"
				+ "                   AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
				+ "                  LEFT JOIN tb_psg_census_nok n\r\n"
				+ "                    ON c.id = n.comm_id\r\n"
				+ "                   AND n.status = 1\r\n"
				+ "                   AND c.status IN ('1','5')\r\n"
				+ "             UNION ALL SELECT j.full_name AS name,\r\n"
				+ "                       j.army_no,\r\n"
				+ "                       r.rank AS description,\r\n"
				+ "                       n_j.nok_name,\r\n"
				+ "                       n_j.nok_relation,\r\n"
				+ "                       pgp_sym_decrypt(n_j.nok_mobile_no ::BYTEA,current_setting('miso.version')) AS nok_mobile_no,\r\n"
				+ "                       j.unit_sus_no,\r\n"
				+ "                       CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
				+ "                            ELSE 'OR'\r\n"
				+ "                             END AS typ,\r\n"
				+ "                       CASE WHEN n_j.id IS NULL THEN 0\r\n"
				+ "                            ELSE n_j.id\r\n"
				+ "                             END AS nok_id\r\n"
				+ "                  FROM tb_psg_census_jco_or_p j\r\n"
				+ "                 INNER JOIN tb_psg_mstr_rank_jco r\r\n"
				+ "                    ON r.id = j.rank\r\n"
				+ "                   AND j.status IN ('1','5')\r\n"
				+ "                  LEFT JOIN tb_psg_census_nok_jco n_j\r\n"
				+ "                    ON j.id = n_j.jco_id\r\n"
				+ "                   AND n_j.status = 1\r\n"
				+ "               ) hld\r\n"
				+ "         INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
				+ "            ON orb.sus_no = hld.unit_sus_no\r\n"
				+ "           AND orb.status_sus_no = 'Active'\r\n"
				+ "          LEFT JOIN tb_psg_mstr_relation rel\r\n"
				+ "            ON rel.id = hld.nok_relation\r\n"
				+ "          LEFT JOIN orbat_view_corps ac\r\n"
				+ "            ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
				+ "          LEFT JOIN orbat_view_cmd ab\r\n"
				+ "            ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
				+ "          LEFT JOIN orbat_view_div ad\r\n"
				+ "            ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
				+ "          LEFT JOIN orbat_view_bde ae\r\n"
				+ "            ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
				+ "         GROUP BY cmd_unit,\r\n"
				+ "                  corp_unit,\r\n"
				+ "                  div_unit,\r\n"
				+ "                  bde_unit,\r\n"
				+ "                  orb.unit_name,\r\n"
				+ "                  hld.unit_sus_no,\r\n"
				+ "                  orb.form_code_control\r\n"
				+ "       ) app\r\n"
				+ "" + SearchValue ;
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
				list.add(rs.getString("cmd_unit"));//0
				list.add(rs.getString("corp_unit"));//1
				list.add(rs.getString("div_unit"));//2
				list.add(rs.getString("bde_unit"));//3
				list.add(rs.getString("unit_name"));//4
//				list.add(rs.getString("unit_sus_no"));//5
				list.add(rs.getString("offr"));//6
				list.add(rs.getString("jco"));//7
				list.add(rs.getString("or1"));//8
				list.add(rs.getString("ent"));//9
				list.add(rs.getString("not_ent"));//10		
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
	
public ArrayList<ArrayList<String>> pdf_getNOK_Details_ContactNo_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
			
		q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.unit_name,app.form_code_control,\r\n" + 
				"app.army_no,app.description,app.name,app.nok_name,app.relation_name,app.nok_mobile_no\r\n" + 
				"	from (	\r\n" + 
				"			select distinct \r\n" + 
				"				fv.unit_name as cmd_unit,\r\n" + 
				"				fvm.unit_name as corp_unit,\r\n" + 
				"				div.unit_name as div_unit,\r\n" + 
				"				bde.unit_name as bde_unit,\r\n" + 
				"				orb.unit_name,\r\n" + 
				"				hld.unit_sus_no,\r\n" + 
				"				hld.army_no,\r\n" + 
				"				hld.description,\r\n" + 
				"				hld.name,\r\n" + 
				"				hld.nok_name,\r\n" + 
				"				rel.relation_name,\r\n" + 
				"				hld.nok_mobile_no,\r\n" + 
				"				orb.form_code_control\r\n" + 
				"				from (\r\n" + 
				"						select c.name,c.personnel_no as army_no,d.description,n.nok_name,n.nok_relation,PGP_SYM_DECRYPT(n.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
				"							 c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"						 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"						 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
				"						 inner join tb_psg_census_nok n on c.id= n.comm_id and n.status = 1\r\n" + 
				"					union all	 \r\n" + 
				"						 select j.full_name as name,j.army_no,r.rank as description,n_j.nok_name,n_j.nok_relation,PGP_SYM_DECRYPT(n_j.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,\r\n" + 
				"							 j.unit_sus_no, case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ from tb_psg_census_jco_or_p j \r\n" + 
				"						 inner join tb_psg_mstr_rank_jco r on r.id = j.rank and j.status in ('1','5')\r\n" + 
				"						 inner join tb_psg_census_nok_jco n_j on j.id= n_j.jco_id and n_j.status = 1\r\n" + 
				"				) hld 						\r\n" + 
				"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"				inner join tb_psg_mstr_relation rel on rel.id = hld.nok_relation\r\n" + 
				"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
				"				group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,hld.army_no,\r\n" + 
				"				hld.description,hld.name,hld.nok_name,rel.relation_name,hld.nok_mobile_no,orb.form_code_control ) app " +SearchValue ;
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
				list.add(rs.getString("cmd_unit"));//0
				list.add(rs.getString("corp_unit"));//1
				list.add(rs.getString("div_unit"));//2
				list.add(rs.getString("bde_unit"));//3
				list.add(rs.getString("unit_name"));//4
//				list.add(rs.getString("unit_sus_no"));//5
				list.add(rs.getString("army_no"));//6
				list.add(rs.getString("description"));//3
				list.add(rs.getString("name"));//4
				list.add(rs.getString("nok_name"));//5
				list.add(rs.getString("relation_name"));//6
				list.add(rs.getString("nok_mobile_no"));//6
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
