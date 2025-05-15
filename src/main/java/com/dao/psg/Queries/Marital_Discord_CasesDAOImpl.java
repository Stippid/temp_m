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

public class Marital_Discord_CasesDAOImpl implements Marital_Discord_CasesDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getMarital_Discord_Cases_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select app.cmd_unit,\r\n"
					+ "       app.corp_unit,\r\n"
					+ "       app.div_unit,\r\n"
					+ "       app.bde_unit,\r\n"
					+ "       app.unit_name,\r\n"
					+ "       app.unit_sus_no,\r\n"
					+ "       app.offr,\r\n"
					+ "       app.jco,\r\n"
					+ "       app.or1,\r\n"
					+ "       app.total,\r\n"
					+ "       app.form_code_control,\r\n"
					+ "       app.permanent_state,\r\n"
					+ "       app.permanent_country,\r\n"
					+ "       app.dt_of_comp,\r\n"
					+ "       app.state_name,\r\n"
					+ "       app.district_name\r\n"
					+ "  from (\r\n"
					+ "        select distinct ab.cmd_name as cmd_unit,\r\n"
					+ "               ac.coprs_name as corp_unit,\r\n"
					+ "               ad.div_name as div_unit,\r\n"
					+ "               ae.bde_name as bde_unit,\r\n"
					+ "               orb.unit_name,\r\n"
					+ "               hld.unit_sus_no,\r\n"
					+ "               orb.form_code_control,\r\n"
					+ "               hld.permanent_state,\r\n"
					+ "               hld.permanent_country,\r\n"
					+ "               st.state_name,\r\n"
					+ "               dt.district_name,\r\n"
					+ "               count(hld.unit_sus_no) filter (where hld.typ='OFFR') as offr,\r\n"
					+ "               count(hld.unit_sus_no) filter (where hld.typ='JCO') as jco,\r\n"
					+ "               count(hld.unit_sus_no) filter (where hld.typ='OR') as or1,\r\n"
					+ "               count(hld.unit_sus_no) as total,\r\n"
					+ "               to_char(hld.dt_of_comp,'dd-mm-yyyy') as dt_of_comp\r\n"
					+ "          from (\r\n"
					+ "                select c.name,\r\n"
					+ "                       c.personnel_no as army_no,\r\n"
					+ "                       d.description,\r\n"
					+ "                       ad.permanent_state,\r\n"
					+ "                       ad.permanent_district,\r\n"
					+ "                       ad.permanent_country,\r\n"
					+ "                       md.name_lady,\r\n"
					+ "                       md.complaint,\r\n"
					+ "                       md.dt_of_comp,\r\n"
					+ "                       cmd.status_of_case,\r\n"
					+ "                       md.modified_date as date_of_status,\r\n"
					+ "                       c.unit_sus_no,\r\n"
					+ "                       'OFFR' as typ\r\n"
					+ "                  from tb_psg_trans_proposed_comm_letter c\r\n"
					+ "                 inner join tb_psg_census_detail_p cd\r\n"
					+ "                    on c.id = cd.comm_id\r\n"
					+ "                   and c.status in ('1','5')\r\n"
					+ "                 inner join cue_tb_psg_rank_app_master d\r\n"
					+ "                    on d.id = c.rank\r\n"
					+ "                   and upper(d.level_in_hierarchy) = 'RANK'\r\n"
					+ "                 inner join tb_psg_maritial_discord md\r\n"
					+ "                    on c.id= md.comm_id\r\n"
					+ "                 inner join tb_psg_maritial_discord_status_child cmd\r\n"
					+ "                    on md.id = cmd.maritial_id\r\n"
					+ "                   and cmd.status=1\r\n"
					+ "                 inner join tb_psg_census_address ad\r\n"
					+ "                    on ad.comm_id = c.id\r\n"
					+ "                   and ad.status = 1\r\n"
					+ "             union all select j.full_name as name,\r\n"
					+ "                       j.army_no,\r\n"
					+ "                       r.rank as description,\r\n"
					+ "                       ad_j.permanent_state,\r\n"
					+ "                       ad_j.permanent_district,\r\n"
					+ "                       ad_j.permanent_country,\r\n"
					+ "                       md_j.name_lady,\r\n"
					+ "                       md_j.complaint,\r\n"
					+ "                       md_j.dt_of_complaint,\r\n"
					+ "                       cmd_j.status_of_case,\r\n"
					+ "                       md_j.modified_date as date_of_status,\r\n"
					+ "                       j.unit_sus_no,\r\n"
					+ "                       case when j.category = 'JCO' then 'JCO'\r\n"
					+ "                            else 'OR'\r\n"
					+ "                             end as typ\r\n"
					+ "                  from tb_psg_census_jco_or_p j\r\n"
					+ "                 inner join tb_psg_mstr_rank_jco r\r\n"
					+ "                    on r.id = j.rank\r\n"
					+ "                   and j.status in ('1','5')\r\n"
					+ "                 inner join tb_psg_marital_discord_jco md_j\r\n"
					+ "                    on j.id= md_j.jco_id\r\n"
					+ "                 inner join tb_psg_marital_discord_status_child_jco cmd_j\r\n"
					+ "                    on md_j.id = cmd_j.marital_id\r\n"
					+ "                   and cmd_j.status=1\r\n"
					+ "                  left join tb_psg_census_address_jco ad_j\r\n"
					+ "                    on ad_j.jco_id = j.id\r\n"
					+ "                   and ad_j.status = 1\r\n"
					+ "               ) hld\r\n"
					+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
					+ "            on orb.sus_no = hld.unit_sus_no\r\n"
					+ "           and orb.status_sus_no='Active'\r\n"
					+ "          left join tb_psg_mstr_state st\r\n"
					+ "            on st.state_id = hld.permanent_state\r\n"
					+ "          left join tb_psg_mstr_district dt\r\n"
					+ "            on dt.district_id = hld.permanent_district\r\n"
					+ "          left join orbat_view_corps ac\r\n"
					+ "            on substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "          left join orbat_view_cmd ab\r\n"
					+ "            on substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "          left join orbat_view_div ad\r\n"
					+ "            on substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "          left join orbat_view_bde ae\r\n"
					+ "            on substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ "         group by cmd_unit,\r\n"
					+ "                  corp_unit,\r\n"
					+ "                  div_unit,\r\n"
					+ "                  bde_unit,\r\n"
					+ "                  orb.unit_name,\r\n"
					+ "                  hld.unit_sus_no,\r\n"
					+ "                  orb.form_code_control,\r\n"
					+ "                  hld.permanent_state,\r\n"
					+ "                  hld.permanent_country,\r\n"
					+ "                  hld.dt_of_comp,\r\n"
					+ "                  st.state_name,\r\n"
					+ "                  dt.district_name\r\n"
					+ "       ) app" +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
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
	 public long getMarital_Discord_Cases_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from (\r\n" +
						"select app.cmd_unit,\r\n"
						+ "       app.corp_unit,\r\n"
						+ "       app.div_unit,\r\n"
						+ "       app.bde_unit,\r\n"
						+ "       app.unit_name,\r\n"
						+ "       app.unit_sus_no,\r\n"
						+ "       app.offr,\r\n"
						+ "       app.jco,\r\n"
						+ "       app.or1,\r\n"
						+ "       app.total,\r\n"
						+ "       app.form_code_control,\r\n"
						+ "       app.permanent_state,\r\n"
						+ "       app.permanent_country,\r\n"
						+ "       app.dt_of_comp,\r\n"
						+ "       app.state_name,\r\n"
						+ "       app.district_name\r\n"
						+ "  from (\r\n"
						+ "        select distinct ab.cmd_name as cmd_unit,\r\n"
						+ "               ac.coprs_name as corp_unit,\r\n"
						+ "               ad.div_name as div_unit,\r\n"
						+ "               ae.bde_name as bde_unit,\r\n"
						+ "               orb.unit_name,\r\n"
						+ "               hld.unit_sus_no,\r\n"
						+ "               orb.form_code_control,\r\n"
						+ "               hld.permanent_state,\r\n"
						+ "               hld.permanent_country,\r\n"
						+ "               st.state_name,\r\n"
						+ "               dt.district_name,\r\n"
						+ "               count(hld.unit_sus_no) filter (where hld.typ='OFFR') as offr,\r\n"
						+ "               count(hld.unit_sus_no) filter (where hld.typ='JCO') as jco,\r\n"
						+ "               count(hld.unit_sus_no) filter (where hld.typ='OR') as or1,\r\n"
						+ "               count(hld.unit_sus_no) as total,\r\n"
						+ "               to_char(hld.dt_of_comp,'dd-mm-yyyy') as dt_of_comp\r\n"
						+ "          from (\r\n"
						+ "                select c.name,\r\n"
						+ "                       c.personnel_no as army_no,\r\n"
						+ "                       d.description,\r\n"
						+ "                       ad.permanent_state,\r\n"
						+ "                       ad.permanent_district,\r\n"
						+ "                       ad.permanent_country,\r\n"
						+ "                       md.name_lady,\r\n"
						+ "                       md.complaint,\r\n"
						+ "                       md.dt_of_comp,\r\n"
						+ "                       cmd.status_of_case,\r\n"
						+ "                       md.modified_date as date_of_status,\r\n"
						+ "                       c.unit_sus_no,\r\n"
						+ "                       'OFFR' as typ\r\n"
						+ "                  from tb_psg_trans_proposed_comm_letter c\r\n"
						+ "                 inner join tb_psg_census_detail_p cd\r\n"
						+ "                    on c.id = cd.comm_id\r\n"
						+ "                   and c.status in ('1','5')\r\n"
						+ "                 inner join cue_tb_psg_rank_app_master d\r\n"
						+ "                    on d.id = c.rank\r\n"
						+ "                   and upper(d.level_in_hierarchy) = 'RANK'\r\n"
						+ "                 inner join tb_psg_maritial_discord md\r\n"
						+ "                    on c.id= md.comm_id\r\n"
						+ "                 inner join tb_psg_maritial_discord_status_child cmd\r\n"
						+ "                    on md.id = cmd.maritial_id\r\n"
						+ "                   and cmd.status=1\r\n"
						+ "                 inner join tb_psg_census_address ad\r\n"
						+ "                    on ad.comm_id = c.id\r\n"
						+ "                   and ad.status = 1\r\n"
						+ "             union all select j.full_name as name,\r\n"
						+ "                       j.army_no,\r\n"
						+ "                       r.rank as description,\r\n"
						+ "                       ad_j.permanent_state,\r\n"
						+ "                       ad_j.permanent_district,\r\n"
						+ "                       ad_j.permanent_country,\r\n"
						+ "                       md_j.name_lady,\r\n"
						+ "                       md_j.complaint,\r\n"
						+ "                       md_j.dt_of_complaint,\r\n"
						+ "                       cmd_j.status_of_case,\r\n"
						+ "                       md_j.modified_date as date_of_status,\r\n"
						+ "                       j.unit_sus_no,\r\n"
						+ "                       case when j.category = 'JCO' then 'JCO'\r\n"
						+ "                            else 'OR'\r\n"
						+ "                             end as typ\r\n"
						+ "                  from tb_psg_census_jco_or_p j\r\n"
						+ "                 inner join tb_psg_mstr_rank_jco r\r\n"
						+ "                    on r.id = j.rank\r\n"
						+ "                   and j.status in ('1','5')\r\n"
						+ "                 inner join tb_psg_marital_discord_jco md_j\r\n"
						+ "                    on j.id= md_j.jco_id\r\n"
						+ "                 inner join tb_psg_marital_discord_status_child_jco cmd_j\r\n"
						+ "                    on md_j.id = cmd_j.marital_id\r\n"
						+ "                   and cmd_j.status=1\r\n"
						+ "                  left join tb_psg_census_address_jco ad_j\r\n"
						+ "                    on ad_j.jco_id = j.id\r\n"
						+ "                   and ad_j.status = 1\r\n"
						+ "               ) hld\r\n"
						+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
						+ "            on orb.sus_no = hld.unit_sus_no\r\n"
						+ "           and orb.status_sus_no='Active'\r\n"
						+ "          left join tb_psg_mstr_state st\r\n"
						+ "            on st.state_id = hld.permanent_state\r\n"
						+ "          left join tb_psg_mstr_district dt\r\n"
						+ "            on dt.district_id = hld.permanent_district\r\n"
						+ "          left join orbat_view_corps ac\r\n"
						+ "            on substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
						+ "          left join orbat_view_cmd ab\r\n"
						+ "            on substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
						+ "          left join orbat_view_div ad\r\n"
						+ "            on substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
						+ "          left join orbat_view_bde ae\r\n"
						+ "            on substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
						+ "         group by cmd_unit,\r\n"
						+ "                  corp_unit,\r\n"
						+ "                  div_unit,\r\n"
						+ "                  bde_unit,\r\n"
						+ "                  orb.unit_name,\r\n"
						+ "                  hld.unit_sus_no,\r\n"
						+ "                  orb.form_code_control,\r\n"
						+ "                  hld.permanent_state,\r\n"
						+ "                  hld.permanent_country,\r\n"
						+ "                  hld.dt_of_comp,\r\n"
						+ "                  st.state_name,\r\n"
						+ "                  dt.district_name\r\n"
						+ "       ) app" +SearchValue +") app1";
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
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
	  
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? or cast(app.total as character varying) = ? "
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
    	if(!country_birth.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.permanent_country as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.permanent_country as character varying) = ? ";
			}
    	}
    	if(!state_birth.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.permanent_state as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.permanent_state as character varying) = ? ";
			}
    	}
    	
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(app.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(app.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			}
		}
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
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
			if(!country_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, country_birth);
			}
			if(!state_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, state_birth);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
			}
 			
 		}catch (Exception e) {}
 		return stmt;
 	}
  
 
  
  
  
  
  public List<Map<String, Object>> getMarital_Discord_Cases_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
					+ "       ac.coprs_name AS corp_unit,\r\n"
					+ "       ad.div_name AS div_unit,\r\n"
					+ "       ae.bde_name AS bde_unit,\r\n"
					+ "       orb.unit_name,\r\n"
					+ "       hld.unit_sus_no,\r\n"
					+ "       hld.army_no,\r\n"
					+ "       hld.description,\r\n"
					+ "       hld.name,\r\n"
					+ "       st.state_name,\r\n"
					+ "       dt.district_name,\r\n"
					+ "       hld.name_lady,\r\n"
					+ "       hld.complaint,\r\n"
					+ "       to_char(hld.dt_of_comp,'DD-MON-YYYY') AS dt_of_comp,\r\n"
					+ "       to_char(hld.date_of_status,'DD-MON-YYYY') AS date_of_status,\r\n"
					+ "       hld.permanent_state,\r\n"
					+ "       hld.permanent_country\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT c.name,\r\n"
					+ "               c.personnel_no AS army_no,\r\n"
					+ "               d.description,\r\n"
					+ "               ad.permanent_state,\r\n"
					+ "               ad.permanent_district,\r\n"
					+ "               ad.permanent_country,\r\n"
					+ "               md.name_lady,\r\n"
					+ "               md.complaint,\r\n"
					+ "               md.dt_of_comp,\r\n"
					+ "               md.modified_date AS date_of_status,\r\n"
					+ "               c.unit_sus_no,\r\n"
					+ "               'OFFR' AS typ\r\n"
					+ "          FROM tb_psg_trans_proposed_comm_letter c\r\n"
					+ "         INNER JOIN tb_psg_census_detail_p cd\r\n"
					+ "            ON c.id = cd.comm_id\r\n"
					+ "           AND c.status IN ('1','5')\r\n"
					+ "         INNER JOIN cue_tb_psg_rank_app_master d\r\n"
					+ "            ON d.id = c.rank\r\n"
					+ "           AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
					+ "         INNER JOIN tb_psg_maritial_discord md\r\n"
					+ "            ON c.id = md.comm_id\r\n"
					+ "         INNER JOIN tb_psg_maritial_discord_status_child cmd\r\n"
					+ "            ON md.id = cmd.maritial_id\r\n"
					+ "           AND cmd.status = 1\r\n"
					+ "         INNER JOIN tb_psg_census_address ad\r\n"
					+ "            ON ad.comm_id = c.id\r\n"
					+ "           AND ad.status = 1\r\n"
					+ "     UNION ALL SELECT j.full_name AS name,\r\n"
					+ "               j.army_no,\r\n"
					+ "               r.rank AS description,\r\n"
					+ "               ad_j.permanent_state,\r\n"
					+ "               ad_j.permanent_district,\r\n"
					+ "               ad_j.permanent_country,\r\n"
					+ "               md_j.name_lady,\r\n"
					+ "               md_j.complaint,\r\n"
					+ "               md_j.dt_of_complaint,\r\n"
					+ "               md_j.modified_date AS date_of_status,\r\n"
					+ "               j.unit_sus_no,\r\n"
					+ "               CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
					+ "                    ELSE 'OR'\r\n"
					+ "                     END AS typ\r\n"
					+ "          FROM tb_psg_census_jco_or_p j\r\n"
					+ "         INNER JOIN tb_psg_mstr_rank_jco r\r\n"
					+ "            ON r.id = j.rank\r\n"
					+ "           AND j.status IN ('1','5')\r\n"
					+ "         INNER JOIN tb_psg_marital_discord_jco md_j\r\n"
					+ "            ON j.id = md_j.jco_id\r\n"
					+ "         INNER JOIN tb_psg_marital_discord_status_child_jco cmd_j\r\n"
					+ "            ON md_j.id = cmd_j.marital_id\r\n"
					+ "           AND cmd_j.status = 1\r\n"
					+ "          LEFT JOIN tb_psg_census_address_jco ad_j\r\n"
					+ "            ON ad_j.jco_id = j.id\r\n"
					+ "           AND ad_j.status = 1\r\n"
					+ "       ) hld\r\n"
					+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
					+ "    ON orb.sus_no = hld.unit_sus_no\r\n"
					+ "   AND orb.status_sus_no = 'Active'\r\n"
					+ "  LEFT JOIN tb_psg_mstr_state st\r\n"
					+ "    ON st.state_id = hld.permanent_state\r\n"
					+ "  LEFT JOIN tb_psg_mstr_district dt\r\n"
					+ "    ON dt.district_id = hld.permanent_district\r\n"
					+ "  LEFT JOIN orbat_view_corps ac\r\n"
					+ "    ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "  LEFT JOIN orbat_view_cmd ab\r\n"
					+ "    ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "  LEFT JOIN orbat_view_div ad\r\n"
					+ "    ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "  LEFT JOIN orbat_view_bde ae\r\n"
					+ "    ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text " +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
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

public long getMarital_Discord_Cases_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app.*) from (SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
					+ "       ac.coprs_name AS corp_unit,\r\n"
					+ "       ad.div_name AS div_unit,\r\n"
					+ "       ae.bde_name AS bde_unit,\r\n"
					+ "       orb.unit_name,\r\n"
					+ "       hld.unit_sus_no,\r\n"
					+ "       hld.army_no,\r\n"
					+ "       hld.description,\r\n"
					+ "       hld.name,\r\n"
					+ "       st.state_name,\r\n"
					+ "       dt.district_name,\r\n"
					+ "       hld.name_lady,\r\n"
					+ "       hld.complaint,\r\n"
					+ "       to_char(hld.dt_of_comp,'DD-MON-YYYY') AS dt_of_comp,\r\n"
					+ "       to_char(hld.date_of_status,'DD-MON-YYYY') AS date_of_status,\r\n"
					+ "       hld.permanent_state,\r\n"
					+ "       hld.permanent_country\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT c.name,\r\n"
					+ "               c.personnel_no AS army_no,\r\n"
					+ "               d.description,\r\n"
					+ "               ad.permanent_state,\r\n"
					+ "               ad.permanent_district,\r\n"
					+ "               ad.permanent_country,\r\n"
					+ "               md.name_lady,\r\n"
					+ "               md.complaint,\r\n"
					+ "               md.dt_of_comp,\r\n"
					+ "               md.modified_date AS date_of_status,\r\n"
					+ "               c.unit_sus_no,\r\n"
					+ "               'OFFR' AS typ\r\n"
					+ "          FROM tb_psg_trans_proposed_comm_letter c\r\n"
					+ "         INNER JOIN tb_psg_census_detail_p cd\r\n"
					+ "            ON c.id = cd.comm_id\r\n"
					+ "           AND c.status IN ('1','5')\r\n"
					+ "         INNER JOIN cue_tb_psg_rank_app_master d\r\n"
					+ "            ON d.id = c.rank\r\n"
					+ "           AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
					+ "         INNER JOIN tb_psg_maritial_discord md\r\n"
					+ "            ON c.id = md.comm_id\r\n"
					+ "         INNER JOIN tb_psg_maritial_discord_status_child cmd\r\n"
					+ "            ON md.id = cmd.maritial_id\r\n"
					+ "           AND cmd.status = 1\r\n"
					+ "         INNER JOIN tb_psg_census_address ad\r\n"
					+ "            ON ad.comm_id = c.id\r\n"
					+ "           AND ad.status = 1\r\n"
					+ "     UNION ALL SELECT j.full_name AS name,\r\n"
					+ "               j.army_no,\r\n"
					+ "               r.rank AS description,\r\n"
					+ "               ad_j.permanent_state,\r\n"
					+ "               ad_j.permanent_district,\r\n"
					+ "               ad_j.permanent_country,\r\n"
					+ "               md_j.name_lady,\r\n"
					+ "               md_j.complaint,\r\n"
					+ "               md_j.dt_of_complaint,\r\n"
					+ "               md_j.modified_date AS date_of_status,\r\n"
					+ "               j.unit_sus_no,\r\n"
					+ "               CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
					+ "                    ELSE 'OR'\r\n"
					+ "                     END AS typ\r\n"
					+ "          FROM tb_psg_census_jco_or_p j\r\n"
					+ "         INNER JOIN tb_psg_mstr_rank_jco r\r\n"
					+ "            ON r.id = j.rank\r\n"
					+ "           AND j.status IN ('1','5')\r\n"
					+ "         INNER JOIN tb_psg_marital_discord_jco md_j\r\n"
					+ "            ON j.id = md_j.jco_id\r\n"
					+ "         INNER JOIN tb_psg_marital_discord_status_child_jco cmd_j\r\n"
					+ "            ON md_j.id = cmd_j.marital_id\r\n"
					+ "           AND cmd_j.status = 1\r\n"
					+ "          LEFT JOIN tb_psg_census_address_jco ad_j\r\n"
					+ "            ON ad_j.jco_id = j.id\r\n"
					+ "           AND ad_j.status = 1\r\n"
					+ "       ) hld\r\n"
					+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
					+ "    ON orb.sus_no = hld.unit_sus_no\r\n"
					+ "   AND orb.status_sus_no = 'Active'\r\n"
					+ "  LEFT JOIN tb_psg_mstr_state st\r\n"
					+ "    ON st.state_id = hld.permanent_state\r\n"
					+ "  LEFT JOIN tb_psg_mstr_district dt\r\n"
					+ "    ON dt.district_id = hld.permanent_district\r\n"
					+ "  LEFT JOIN orbat_view_corps ac\r\n"
					+ "    ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "  LEFT JOIN orbat_view_cmd ab\r\n"
					+ "    ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "  LEFT JOIN orbat_view_div ad\r\n"
					+ "    ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "  LEFT JOIN orbat_view_bde ae\r\n"
					+ "    ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text"+ 
					"" + SearchValue +
					") app  " ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,country_birth,state_birth,dt_from,dt_to);
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


	public String GenerateQueryWhereClause_SQL_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +=" lower(ab.cmd_name) like ? or lower( ac.coprs_name) like ?  or lower(ad.div_name) like ? " + 
					" or lower(ae.bde_name) like ? or lower(hld.unit_sus_no) like ? or lower(orb.unit_name) like ?  or lower(hld.army_no) like ? "
					+ "or lower(hld.description) like ? or lower(hld.name) like ?  "
					+ "or lower(st.state_name) like ? or lower(dt.district_name) like ?  "
					+ "or lower(hld.name_lady) like ? or lower(hld.complaint) like ?  "
					+ "or lower((TO_CHAR(cast(hld.dt_of_comp  as  date),'DD-MON-YYYY'))) like ? "
					+ "or lower((TO_CHAR(cast(hld.date_of_status  as  date),'DD-MON-YYYY'))) like ? "
					+ ")";
			
		}
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
 			SearchValue +=" and lower(hld.unit_sus_no) = ? ";
			} else {
				SearchValue += " where lower(hld.unit_sus_no) = ? ";
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
 	
 	if(!country_birth.equals("0")){
 		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.permanent_country = cast(? as integer) ";
			} else {
				SearchValue += " where hld.permanent_country = cast(? as integer) ";
			}
 	}
 	if(!state_birth.equals("0")){
 		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.permanent_state = cast(? as integer) ";
			} else {
				SearchValue += " where hld.permanent_state = cast(? as integer) ";
			}
 	}
 	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(hld.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(hld.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			}
		}
		
return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL_pers(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to) {
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
		if(!country_birth.equals("0")) {
			flag += 1;
			stmt.setString(flag, country_birth);
		}
		if(!state_birth.equals("0")) {
			flag += 1;
			stmt.setString(flag, state_birth);
		}
		if(!dt_from.equals("") && !dt_to.equals("")){
			flag+=1;
			stmt.setString(flag, dt_from);
			flag+=1;
			stmt.setString(flag, dt_to);
		}
			
		}catch (Exception e) {}
		return stmt;
	}


public ArrayList<ArrayList<String>> pdf_getMarital_Discord_Cases_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to)
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
    	if(!country_birth.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.permanent_country as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.permanent_country as character varying) = ? ";
			}
    	}
    	if(!state_birth.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.permanent_state as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.permanent_state as character varying) = ? ";
			}
    	}
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(app.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(app.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			}
		}
		
		q= "select app.cmd_unit,\r\n"
				+ "       app.corp_unit,\r\n"
				+ "       app.div_unit,\r\n"
				+ "       app.bde_unit,\r\n"
				+ "       app.unit_name,\r\n"
				+ "       app.unit_sus_no,\r\n"
				+ "       app.offr,\r\n"
				+ "       app.jco,\r\n"
				+ "       app.or1,\r\n"
				+ "       app.total,\r\n"
				+ "       app.form_code_control,\r\n"
				+ "       app.permanent_state,\r\n"
				+ "       app.permanent_country,\r\n"
				+ "       app.dt_of_comp,\r\n"
				+ "       app.state_name,\r\n"
				+ "       app.district_name\r\n"
				+ "  from (\r\n"
				+ "        select distinct ab.cmd_name as cmd_unit,\r\n"
				+ "               ac.coprs_name as corp_unit,\r\n"
				+ "               ad.div_name as div_unit,\r\n"
				+ "               ae.bde_name as bde_unit,\r\n"
				+ "               orb.unit_name,\r\n"
				+ "               hld.unit_sus_no,\r\n"
				+ "               orb.form_code_control,\r\n"
				+ "               hld.permanent_state,\r\n"
				+ "               hld.permanent_country,\r\n"
				+ "               st.state_name,\r\n"
				+ "               dt.district_name,\r\n"
				+ "               count(hld.unit_sus_no) filter (where hld.typ='OFFR') as offr,\r\n"
				+ "               count(hld.unit_sus_no) filter (where hld.typ='JCO') as jco,\r\n"
				+ "               count(hld.unit_sus_no) filter (where hld.typ='OR') as or1,\r\n"
				+ "               count(hld.unit_sus_no) as total,\r\n"
				+ "               to_char(hld.dt_of_comp,'dd-mm-yyyy') as dt_of_comp\r\n"
				+ "          from (\r\n"
				+ "                select c.name,\r\n"
				+ "                       c.personnel_no as army_no,\r\n"
				+ "                       d.description,\r\n"
				+ "                       ad.permanent_state,\r\n"
				+ "                       ad.permanent_district,\r\n"
				+ "                       ad.permanent_country,\r\n"
				+ "                       md.name_lady,\r\n"
				+ "                       md.complaint,\r\n"
				+ "                       md.dt_of_comp,\r\n"
				+ "                       cmd.status_of_case,\r\n"
				+ "                       md.modified_date as date_of_status,\r\n"
				+ "                       c.unit_sus_no,\r\n"
				+ "                       'OFFR' as typ\r\n"
				+ "                  from tb_psg_trans_proposed_comm_letter c\r\n"
				+ "                 inner join tb_psg_census_detail_p cd\r\n"
				+ "                    on c.id = cd.comm_id\r\n"
				+ "                   and c.status in ('1','5')\r\n"
				+ "                 inner join cue_tb_psg_rank_app_master d\r\n"
				+ "                    on d.id = c.rank\r\n"
				+ "                   and upper(d.level_in_hierarchy) = 'RANK'\r\n"
				+ "                 inner join tb_psg_maritial_discord md\r\n"
				+ "                    on c.id= md.comm_id\r\n"
				+ "                 inner join tb_psg_maritial_discord_status_child cmd\r\n"
				+ "                    on md.id = cmd.maritial_id\r\n"
				+ "                   and cmd.status=1\r\n"
				+ "                 inner join tb_psg_census_address ad\r\n"
				+ "                    on ad.comm_id = c.id\r\n"
				+ "                   and ad.status = 1\r\n"
				+ "             union all select j.full_name as name,\r\n"
				+ "                       j.army_no,\r\n"
				+ "                       r.rank as description,\r\n"
				+ "                       ad_j.permanent_state,\r\n"
				+ "                       ad_j.permanent_district,\r\n"
				+ "                       ad_j.permanent_country,\r\n"
				+ "                       md_j.name_lady,\r\n"
				+ "                       md_j.complaint,\r\n"
				+ "                       md_j.dt_of_complaint,\r\n"
				+ "                       cmd_j.status_of_case,\r\n"
				+ "                       md_j.modified_date as date_of_status,\r\n"
				+ "                       j.unit_sus_no,\r\n"
				+ "                       case when j.category = 'JCO' then 'JCO'\r\n"
				+ "                            else 'OR'\r\n"
				+ "                             end as typ\r\n"
				+ "                  from tb_psg_census_jco_or_p j\r\n"
				+ "                 inner join tb_psg_mstr_rank_jco r\r\n"
				+ "                    on r.id = j.rank\r\n"
				+ "                   and j.status in ('1','5')\r\n"
				+ "                 inner join tb_psg_marital_discord_jco md_j\r\n"
				+ "                    on j.id= md_j.jco_id\r\n"
				+ "                 inner join tb_psg_marital_discord_status_child_jco cmd_j\r\n"
				+ "                    on md_j.id = cmd_j.marital_id\r\n"
				+ "                   and cmd_j.status=1\r\n"
				+ "                  left join tb_psg_census_address_jco ad_j\r\n"
				+ "                    on ad_j.jco_id = j.id\r\n"
				+ "                   and ad_j.status = 1\r\n"
				+ "               ) hld\r\n"
				+ "         inner join tb_miso_orbat_unt_dtl orb\r\n"
				+ "            on orb.sus_no = hld.unit_sus_no\r\n"
				+ "           and orb.status_sus_no='Active'\r\n"
				+ "          left join tb_psg_mstr_state st\r\n"
				+ "            on st.state_id = hld.permanent_state\r\n"
				+ "          left join tb_psg_mstr_district dt\r\n"
				+ "            on dt.district_id = hld.permanent_district\r\n"
				+ "          left join orbat_view_corps ac\r\n"
				+ "            on substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
				+ "          left join orbat_view_cmd ab\r\n"
				+ "            on substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
				+ "          left join orbat_view_div ad\r\n"
				+ "            on substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
				+ "          left join orbat_view_bde ae\r\n"
				+ "            on substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
				+ "         group by cmd_unit,\r\n"
				+ "                  corp_unit,\r\n"
				+ "                  div_unit,\r\n"
				+ "                  bde_unit,\r\n"
				+ "                  orb.unit_name,\r\n"
				+ "                  hld.unit_sus_no,\r\n"
				+ "                  orb.form_code_control,\r\n"
				+ "                  hld.permanent_state,\r\n"
				+ "                  hld.permanent_country,\r\n"
				+ "                  hld.dt_of_comp,\r\n"
				+ "                  st.state_name,\r\n"
				+ "                  dt.district_name\r\n"
				+ "       ) app  " + SearchValue ;
		
		
		
		
		
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
			if(!country_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, country_birth);
			}
			if(!state_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, state_birth);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
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
				list.add(rs.getString("total"));//9
							
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
	
public ArrayList<ArrayList<String>> pdf_getMarital_Discord_Cases_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String country_birth,String state_birth,String dt_from,String dt_to)
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
    			SearchValue +=" and lower(hld.unit_sus_no) = ? ";
			} else {
				SearchValue += " where lower(hld.unit_sus_no) = ? ";
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
    	
    	if(!country_birth.equals("0")){
    		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.permanent_country = cast(? as integer) ";
			} else {
				SearchValue += " where hld.permanent_country = cast(? as integer) ";
			}
    	}
    	if(!state_birth.equals("0")){
    		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.permanent_state = cast(? as integer) ";
			} else {
				SearchValue += " where hld.permanent_state = cast(? as integer) ";
			}
    	}
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(hld.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(hld.dt_of_comp as date) between cast(? as date) and cast(? as date)";
			}
		}
			
		q= "SELECT DISTINCT ab.cmd_name AS cmd_unit,\r\n"
				+ "       ac.coprs_name AS corp_unit,\r\n"
				+ "       ad.div_name AS div_unit,\r\n"
				+ "       ae.bde_name AS bde_unit,\r\n"
				+ "       orb.unit_name,\r\n"
				+ "       hld.unit_sus_no,\r\n"
				+ "       hld.army_no,\r\n"
				+ "       hld.description,\r\n"
				+ "       hld.name,\r\n"
				+ "       st.state_name,\r\n"
				+ "       dt.district_name,\r\n"
				+ "       hld.name_lady,\r\n"
				+ "       hld.complaint,\r\n"
				+ "       to_char(hld.dt_of_comp,'DD-MON-YYYY') AS dt_of_comp,\r\n"
				+ "       to_char(hld.date_of_status,'DD-MON-YYYY') AS date_of_status,\r\n"
				+ "       hld.permanent_state,\r\n"
				+ "       hld.permanent_country\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT c.name,\r\n"
				+ "               c.personnel_no AS army_no,\r\n"
				+ "               d.description,\r\n"
				+ "               ad.permanent_state,\r\n"
				+ "               ad.permanent_district,\r\n"
				+ "               ad.permanent_country,\r\n"
				+ "               md.name_lady,\r\n"
				+ "               md.complaint,\r\n"
				+ "               md.dt_of_comp,\r\n"
				+ "               md.modified_date AS date_of_status,\r\n"
				+ "               c.unit_sus_no,\r\n"
				+ "               'OFFR' AS typ\r\n"
				+ "          FROM tb_psg_trans_proposed_comm_letter c\r\n"
				+ "         INNER JOIN tb_psg_census_detail_p cd\r\n"
				+ "            ON c.id = cd.comm_id\r\n"
				+ "           AND c.status IN ('1','5')\r\n"
				+ "         INNER JOIN cue_tb_psg_rank_app_master d\r\n"
				+ "            ON d.id = c.rank\r\n"
				+ "           AND upper(d.level_in_hierarchy) = 'RANK'\r\n"
				+ "         INNER JOIN tb_psg_maritial_discord md\r\n"
				+ "            ON c.id = md.comm_id\r\n"
				+ "         INNER JOIN tb_psg_maritial_discord_status_child cmd\r\n"
				+ "            ON md.id = cmd.maritial_id\r\n"
				+ "           AND cmd.status = 1\r\n"
				+ "         INNER JOIN tb_psg_census_address ad\r\n"
				+ "            ON ad.comm_id = c.id\r\n"
				+ "           AND ad.status = 1\r\n"
				+ "     UNION ALL SELECT j.full_name AS name,\r\n"
				+ "               j.army_no,\r\n"
				+ "               r.rank AS description,\r\n"
				+ "               ad_j.permanent_state,\r\n"
				+ "               ad_j.permanent_district,\r\n"
				+ "               ad_j.permanent_country,\r\n"
				+ "               md_j.name_lady,\r\n"
				+ "               md_j.complaint,\r\n"
				+ "               md_j.dt_of_complaint,\r\n"
				+ "               md_j.modified_date AS date_of_status,\r\n"
				+ "               j.unit_sus_no,\r\n"
				+ "               CASE WHEN j.category = 'JCO' THEN 'JCO'\r\n"
				+ "                    ELSE 'OR'\r\n"
				+ "                     END AS typ\r\n"
				+ "          FROM tb_psg_census_jco_or_p j\r\n"
				+ "         INNER JOIN tb_psg_mstr_rank_jco r\r\n"
				+ "            ON r.id = j.rank\r\n"
				+ "           AND j.status IN ('1','5')\r\n"
				+ "         INNER JOIN tb_psg_marital_discord_jco md_j\r\n"
				+ "            ON j.id = md_j.jco_id\r\n"
				+ "         INNER JOIN tb_psg_marital_discord_status_child_jco cmd_j\r\n"
				+ "            ON md_j.id = cmd_j.marital_id\r\n"
				+ "           AND cmd_j.status = 1\r\n"
				+ "          LEFT JOIN tb_psg_census_address_jco ad_j\r\n"
				+ "            ON ad_j.jco_id = j.id\r\n"
				+ "           AND ad_j.status = 1\r\n"
				+ "       ) hld\r\n"
				+ " INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
				+ "    ON orb.sus_no = hld.unit_sus_no\r\n"
				+ "   AND orb.status_sus_no = 'Active'\r\n"
				+ "  LEFT JOIN tb_psg_mstr_state st\r\n"
				+ "    ON st.state_id = hld.permanent_state\r\n"
				+ "  LEFT JOIN tb_psg_mstr_district dt\r\n"
				+ "    ON dt.district_id = hld.permanent_district\r\n"
				+ "  LEFT JOIN orbat_view_corps ac\r\n"
				+ "    ON substr(orb.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
				+ "  LEFT JOIN orbat_view_cmd ab\r\n"
				+ "    ON substr(orb.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
				+ "  LEFT JOIN orbat_view_div ad\r\n"
				+ "    ON substr(orb.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
				+ "  LEFT JOIN orbat_view_bde ae\r\n"
				+ "    ON substr(orb.form_code_control::text, 7, 4) = ae.bde_code::text " +SearchValue ;
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
			if(!country_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, country_birth);
			}
			if(!state_birth.equals("0")) {
				flag += 1;
				stmt.setString(flag, state_birth);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
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
//				list.add(rs.getString("unit_sus_no"));//6
				list.add(rs.getString("army_no"));//7
				list.add(rs.getString("description"));//8
				list.add(rs.getString("name"));//9
				list.add(rs.getString("state_name"));//10
				list.add(rs.getString("district_name"));//11
				list.add(rs.getString("name_lady"));//12
				list.add(rs.getString("complaint"));//13
				list.add(rs.getString("dt_of_comp"));//14
				list.add(rs.getString("date_of_status"));//15
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
