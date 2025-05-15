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

public class LMC_State_DAOImpl implements LMC_State_DAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	// LMC posted count dose not match with total of fit, temp, permt because of data not enter in table of tb_psg_census_detail_p 
	 
	public List<Map<String, Object>> getLMC_State_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			
			q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,"
					+ "app.off_total_auth,app.offr_held,app.fit_offr,app.permt_offr,app.temp_offr,"
					+ "app.jco_total_auth,app.jco_held,app.fit_jco,app.permt_jco,app.temp_jco,"
					+ "app.or_total_auth,app.or_held,app.fit_or,app.permt_or,app.temp_or,app.total\r\n" + 
					"		from (	select 	\r\n" + 
					"				 	m_view.cmd_unit,\r\n" + 
					"					m_view.corp_unit,\r\n" + 
					"					m_view.div_unit,\r\n" + 
					"					m_view.bde_unit,\r\n" + 
					"					m_view.unit_name,\r\n" + 
					"					hld.unit_sus_no,\r\n" + 
					"					orb.form_code_control,\r\n" + 
					"					COALESCE (m_view.off_total_auth,'0') as off_total_auth,COALESCE (m_view.offr,'0') as offr_held,\r\n" + 
					"					COALESCE (m_view.jco_total_auth,'0') as jco_total_auth,COALESCE (m_view.jco,'0') as jco_held,\r\n" + 
					"					COALESCE (m_view.or_total_auth,'0') as or_total_auth,COALESCE (m_view.\"or\",'0') as or_held,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OFFR') as fit_offr,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OFFR') as permt_offr,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OFFR' ) as temp_offr,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='JCO') as fit_jco,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='JCO') as permt_jco,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='JCO') as temp_jco,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OR') as fit_or,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OR') as permt_or,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OR') as temp_or,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) as total\r\n" + 
					"					from(\r\n" + 
					"						select distinct \r\n" + 
					"						c.unit_sus_no,\r\n" + 
					"						c.personnel_no as army_no,\r\n" + 
					"						d.description as rank,\r\n" + 
					"						c.name,\r\n" + 
					"						me.med_classification_lmc,\r\n" + 
					"						'OFFR' as typ\r\n" + 
					"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
					"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1' and me.med_classification_lmc!=''\r\n" + 
					"					union all\r\n" + 
					"						select distinct \r\n" + 
					"						jc.unit_sus_no,\r\n" + 
					"						jc.army_no,\r\n" + 
					"						d.rank as rank,\r\n" + 
					"						jc.full_name,\r\n" + 
					"						jme.med_classification_lmc,\r\n" + 
					"						case when jC.category = 'JCO' then 'JCO' else 'OR' END AS typ\r\n" + 
					"						from  tb_psg_census_jco_or_p jc\r\n" + 
					"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
					"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1' and jme.med_classification_lmc!=''				\r\n" + 
					"					) hld \r\n" + 
					"				inner join cue_psg_auth_held m_view on m_view.unit_sus_no = hld.unit_sus_no\r\n" + 
					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				group by m_view.cmd_unit,m_view.corp_unit,m_view.div_unit,m_view.bde_unit,m_view.unit_name,hld.unit_sus_no,orb.form_code_control,\r\n" + 
					"				m_view.off_total_auth,m_view.offr,m_view.jco_total_auth,m_view.jco,m_view.or_total_auth,m_view.\"or\" ) app  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			System.err.println("query for lmc \n" + stmt);
			
			
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
	
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or "
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? or lower(app.bde_unit) like ? or "
 					+ " cast(app.off_total_auth as character varying) = ? or cast(app.offr_held as character varying) = ? or cast(app.fit_offr as character varying) = ? or cast(app.permt_offr as character varying) = ? or cast(app.temp_offr as character varying) = ? or "
 					+ " cast(app.jco_total_auth as character varying) = ? or cast(app.jco_held as character varying) = ? or cast(app.fit_jco as character varying) = ? or cast(app.permt_jco as character varying) = ? or cast(app.temp_jco as character varying) = ? or "
 					+ " cast(app.or_total_auth as character varying) = ? or cast(app.or_held as character varying) = ? or cast(app.fit_or as character varying) = ? or cast(app.permt_or as character varying) = ? or cast(app.temp_or as character varying) = ? or "
 					+ " cast(app.total as character varying) = ? "
 					+ " )";
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
  
  public long getLMC_State_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from (\r\n" + 
					" select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no," +
				    "app.off_total_auth,app.offr_held,app.fit_offr,app.permt_offr,app.temp_offr," +
				    "app.jco_total_auth,app.jco_held,app.fit_jco,app.permt_jco,app.temp_jco," +
				    "app.or_total_auth,app.or_held,app.fit_or,app.permt_or,app.temp_or,app.total\r\n" + 
					"			from (	select 	\r\n" + 
					"				 	m_view.cmd_unit,\r\n" + 
					"					m_view.corp_unit,\r\n" + 
					"					m_view.div_unit,\r\n" + 
					"					m_view.bde_unit,\r\n" + 
					"					m_view.unit_name,\r\n" + 
					"					hld.unit_sus_no,\r\n" + 
					"					orb.form_code_control,\r\n" + 
					"					COALESCE (m_view.off_total_auth,'0') as off_total_auth,COALESCE (m_view.offr,'0') as offr_held,\r\n" + 
					"					COALESCE (m_view.jco_total_auth,'0') as jco_total_auth,COALESCE (m_view.jco,'0') as jco_held,\r\n" + 
					"					COALESCE (m_view.or_total_auth,'0') as or_total_auth,COALESCE (m_view.\"or\",'0') as or_held,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OFFR') as fit_offr,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OFFR') as permt_offr,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OFFR' ) as temp_offr,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='JCO') as fit_jco,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='JCO') as permt_jco,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='JCO') as temp_jco,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OR') as fit_or,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OR') as permt_or,\r\n" + 
					"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OR') as temp_or,\r\n" + 
					"					\r\n" + 
					"					count(hld.*) as total\r\n" + 
					"					from(\r\n" + 
					"						select distinct \r\n" + 
					"						c.unit_sus_no,\r\n" + 
					"						c.personnel_no as army_no,\r\n" + 
					"						d.description as rank,\r\n" + 
					"						c.name,\r\n" + 
					"						me.med_classification_lmc,\r\n" + 
					"						'OFFR' as typ\r\n" + 
					"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
					"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1' and me.med_classification_lmc!=''\r\n" + 
					"					union all\r\n" + 
					"						select distinct \r\n" + 
					"						jc.unit_sus_no,\r\n" + 
					"						jc.army_no,\r\n" + 
					"						d.rank as rank,\r\n" + 
					"						jc.full_name,\r\n" + 
					"						jme.med_classification_lmc,\r\n" + 
					"						case when jC.category = 'JCO' then 'JCO' else 'OR' END AS typ\r\n" + 
					"						from  tb_psg_census_jco_or_p jc\r\n" + 
					"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
					"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1' and jme.med_classification_lmc!=''				\r\n" + 
					"					) hld \r\n" + 
					"				inner join cue_psg_auth_held m_view on m_view.unit_sus_no = hld.unit_sus_no\r\n" + 
					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				group by m_view.cmd_unit,m_view.corp_unit,m_view.div_unit,m_view.bde_unit,m_view.unit_name,hld.unit_sus_no,orb.form_code_control,\r\n" + 
					"				m_view.off_total_auth,m_view.offr,m_view.jco_total_auth,m_view.jco,m_view.or_total_auth,m_view.\"or\" ) app " +SearchValue +") app1";
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
  
  
  
  
  
  public List<Map<String, Object>> getLMC_State_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
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
			q = "select 	fv.unit_name as cmd_unit,\r\n" + 
					"					fvm.unit_name as corp_unit,\r\n" + 
					"					div.unit_name as div_unit,\r\n" + 
					"					bde.unit_name as bde_unit,\r\n" + 
					"					orb.unit_name,\r\n" + 
					"					hld.unit_sus_no,\r\n" + 
					"					hld.army_no,\r\n" + 
					"					hld.rank,\r\n" + 
					"					hld.name,\r\n" + 
					"					hld.med_classification_lmc\r\n" + 
					"					from(\r\n" + 
					"						select distinct \r\n" + 
					"						c.unit_sus_no,\r\n" + 
					"						c.personnel_no as army_no,\r\n" + 
					"						d.description as rank,\r\n" + 
					"						c.name,\r\n" + 
					"						me.med_classification_lmc,\r\n" + 
					"						'OFFR' as typ\r\n" + 
					"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
					"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1'\r\n" + 
					"					union all\r\n" + 
					"						select distinct \r\n" + 
					"						jc.unit_sus_no,\r\n" + 
					"						jc.army_no,\r\n" + 
					"						d.rank as rank,\r\n" + 
					"						jc.full_name,\r\n" + 
					"						jme.med_classification_lmc,\r\n" + 
					"						case when jC.category = 'JCO' then 'JCO' else 'OR'  END AS typ\r\n" + 
					"						from  tb_psg_census_jco_or_p jc\r\n" + 
					"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
					"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1'				\r\n" + 
					"					) hld \r\n" + 
					"				inner join  tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' " +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
			System.err.println("query for lmc pers: \n" + stmt);
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

public long getLMC_State_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app.*) from\r\n" + 
					"	(select 	fv.unit_name as cmd_unit,\r\n" + 
					"					fvm.unit_name as corp_unit,\r\n" + 
					"					div.unit_name as div_unit,\r\n" + 
					"					bde.unit_name as bde_unit,\r\n" + 
					"					orb.unit_name,\r\n" + 
					"					hld.unit_sus_no,\r\n" + 
					"					hld.army_no,\r\n" + 
					"					hld.rank,\r\n" + 
					"					hld.name,\r\n" + 
					"					hld.med_classification_lmc\r\n" + 
					"					from(\r\n" + 
					"						select distinct \r\n" + 
					"						c.unit_sus_no,\r\n" + 
					"						c.personnel_no as army_no,\r\n" + 
					"						d.description as rank,\r\n" + 
					"						c.name,\r\n" + 
					"						me.med_classification_lmc,\r\n" + 
					"						'OFFR' as typ\r\n" + 
					"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
					"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1'\r\n" + 
					"					union all\r\n" + 
					"						select distinct \r\n" + 
					"						jc.unit_sus_no,\r\n" + 
					"						jc.army_no,\r\n" + 
					"						d.rank as rank,\r\n" + 
					"						jc.full_name,\r\n" + 
					"						jme.med_classification_lmc,\r\n" + 
					"						case when jC.category = 'JCO' then 'JCO' else 'OR'  END AS typ\r\n" + 
					"						from  tb_psg_census_jco_or_p jc\r\n" + 
					"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
					"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1'				\r\n" + 
					"					) hld \r\n" + 
					"				inner join  tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' " + 
					"" + SearchValue +
					") app  " ;
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
			SearchValue +=" lower(hld.unit_sus_no) like ? or lower(orb.unit_name) like ?  or "
					+ "lower(fv.unit_name) like ? or lower(fvm.unit_name) like ?  or lower(div.unit_name) like ? " 
					+ "or lower(bde.unit_name) like ? "
					+ "or lower(hld.army_no) like ? "
					+ "or lower(hld.rank) like ? or lower(hld.name) like ? or lower(hld.med_classification_lmc) like ? "
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




public ArrayList<ArrayList<String>> pdf_getLMC_State_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
		
		q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,"
				+ "app.unit_sus_no,\r\n" 
				+ 
				"app.off_total_auth,app.offr_held,app.fit_offr,app.permt_offr,app.temp_offr,\r\n" + 
				"app.jco_total_auth,app.jco_held,app.fit_jco,app.permt_jco,app.temp_jco,\r\n" + 
				"app.or_total_auth,app.or_held,app.fit_or,app.permt_or,app.temp_or,app.total\r\n" + 
				"		from (	select 	\r\n" + 
				"				 	m_view.cmd_unit,\r\n" + 
				"					m_view.corp_unit,\r\n" + 
				"					m_view.div_unit,\r\n" + 
				"					m_view.bde_unit,\r\n" + 
				"					m_view.unit_name,\r\n" + 
				"					hld.unit_sus_no,\r\n" + 
				"					orb.form_code_control,\r\n" + 
				"					COALESCE (m_view.off_total_auth,'0') as off_total_auth,COALESCE (m_view.offr,'0') as offr_held,\r\n" + 
				"					COALESCE (m_view.jco_total_auth,'0') as jco_total_auth,COALESCE (m_view.jco,'0') as jco_held,\r\n" + 
				"					COALESCE (m_view.or_total_auth,'0') as or_total_auth,COALESCE (m_view.\"or\",'0') as or_held,\r\n" + 
				"					\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OFFR') as fit_offr,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OFFR') as permt_offr,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OFFR' ) as temp_offr,\r\n" + 
				"					\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='JCO') as fit_jco,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='JCO') as permt_jco,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='JCO') as temp_jco,\r\n" + 
				"					\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='FIT' and hld.typ='OR') as fit_or,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='PERMANENT' and hld.typ='OR') as permt_or,\r\n" + 
				"					count(hld.*) FILTER (where hld.med_classification_lmc='TEMPORARY' and hld.typ='OR') as temp_or,\r\n" + 
				"					\r\n" + 
				"					count(hld.*) as total\r\n" + 
				"					from(\r\n" + 
				"						select distinct \r\n" + 
				"						c.unit_sus_no,\r\n" + 
				"						c.personnel_no as army_no,\r\n" + 
				"						d.description as rank,\r\n" + 
				"						c.name,\r\n" + 
				"						me.med_classification_lmc,\r\n" + 
				"						'OFFR' as typ\r\n" + 
				"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
				"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1' and me.med_classification_lmc!=''\r\n" + 
				"					union all\r\n" + 
				"						select distinct \r\n" + 
				"						jc.unit_sus_no,\r\n" + 
				"						jc.army_no,\r\n" + 
				"						d.rank as rank,\r\n" + 
				"						jc.full_name,\r\n" + 
				"						jme.med_classification_lmc,\r\n" + 
				"						case when jC.category = 'JCO' then 'JCO' else 'OR' END AS typ\r\n" + 
				"						from  tb_psg_census_jco_or_p jc\r\n" + 
				"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
				"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1' and jme.med_classification_lmc!=''				\r\n" + 
				"					) hld \r\n" + 
				"				inner join cue_psg_auth_held m_view on m_view.unit_sus_no = hld.unit_sus_no\r\n" + 
				"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"				group by m_view.cmd_unit,m_view.corp_unit,m_view.div_unit,m_view.bde_unit,m_view.unit_name,"
				+ "hld.unit_sus_no"
				+ ","
				+ "orb.form_code_control,\r\n" + 
				"				m_view.off_total_auth,m_view.offr,m_view.jco_total_auth,m_view.jco,m_view.or_total_auth,m_view.\"or\" ) app " + SearchValue ;
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
//				list.add(rs.getString("unit_sus_no"));//6
				list.add(rs.getString("off_total_auth"));//7
				list.add(rs.getString("offr_held"));//8
				list.add(rs.getString("fit_offr"));//9
				list.add(rs.getString("permt_offr"));//10
				list.add(rs.getString("temp_offr"));//11
				
				list.add(rs.getString("jco_total_auth"));//12
				list.add(rs.getString("jco_held"));//13
				list.add(rs.getString("fit_jco"));//14
				list.add(rs.getString("permt_jco"));//15
				list.add(rs.getString("temp_jco"));//16
				
				list.add(rs.getString("or_total_auth"));//17
				list.add(rs.getString("or_held"));//18
				list.add(rs.getString("fit_or"));//19
				list.add(rs.getString("permt_or"));//20
				list.add(rs.getString("temp_or"));//21
				
				list.add(rs.getString("total"));//22
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
	
public ArrayList<ArrayList<String>> pdf_getLMC_State_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
			
		q="select 	fv.unit_name as cmd_unit,\r\n" + 
				"					fvm.unit_name as corp_unit,\r\n" + 
				"					div.unit_name as div_unit,\r\n" + 
				"					bde.unit_name as bde_unit,\r\n" + 
				"					orb.unit_name,\r\n" + 
				"					hld.unit_sus_no,\r\n" + 
				"					hld.army_no,\r\n" + 
				"					hld.rank,\r\n" + 
				"					hld.name,\r\n" + 
				"					hld.med_classification_lmc\r\n" + 
				"					from(\r\n" + 
				"						select distinct \r\n" + 
				"						c.unit_sus_no,\r\n" + 
				"						c.personnel_no as army_no,\r\n" + 
				"						d.description as rank,\r\n" + 
				"						c.name,\r\n" + 
				"						me.med_classification_lmc,\r\n" + 
				"						'OFFR' as typ\r\n" + 
				"						from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"						inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"						inner join cue_tb_psg_rank_app_master d on cast(c.rank  as character varying) = cast(d.id as character varying)\r\n" + 
				"						inner join tb_psg_medical_categoryhistory me on me.comm_id = c.id and me.status='1'\r\n" + 
				"					union all\r\n" + 
				"						select distinct \r\n" + 
				"						jc.unit_sus_no,\r\n" + 
				"						jc.army_no,\r\n" + 
				"						d.rank as rank,\r\n" + 
				"						jc.full_name,\r\n" + 
				"						jme.med_classification_lmc,\r\n" + 
				"						case when jC.category = 'JCO' then 'JCO' else 'OR'  END AS typ\r\n" + 
				"						from  tb_psg_census_jco_or_p jc\r\n" + 
				"						inner join tb_psg_mstr_rank_jco d on cast(jc.rank as character varying) = cast(d.id as character varying) and jc.status in ('1','5') \r\n" + 
				"						inner join tb_psg_medical_categoryhistory_jco jme on jme.jco_id = jc.id and jme.status='1'				\r\n" + 
				"					) hld \r\n" + 
				"				inner join  tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
				" " +SearchValue ;
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
//				list.add(rs.getString("unit_sus_no"));//6
				list.add(rs.getString("army_no"));//7
				list.add(rs.getString("rank"));//8
				list.add(rs.getString("name"));//9
				list.add(rs.getString("med_classification_lmc"));//10
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
