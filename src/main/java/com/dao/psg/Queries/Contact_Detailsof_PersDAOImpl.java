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

public class Contact_Detailsof_PersDAOImpl implements Contact_Detailsof_PersDAO{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getContact_Detailsof_Pers_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
					"	app.offr,app.jco,app.or1,app.form_code_control from \r\n" + 
					"	(select distinct \r\n" + 
					"		fv.unit_name as cmd_unit,\r\n" + 
					"		fvm.unit_name as corp_unit,\r\n" + 
					"		div.unit_name as div_unit,\r\n" + 
					"		bde.unit_name as bde_unit,\r\n" + 
					"		orb.unit_name,\r\n" + 
					"		hld.unit_sus_no,\r\n" + 
					"		orb.form_code_control,	\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='JCO') as jco,\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='OR') as or1	\r\n" + 
					"		 from (\r\n" + 
					"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,PGP_SYM_DECRYPT(cn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(cn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
					"			  union all\r\n" + 
					"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,PGP_SYM_DECRYPT(jcn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(jcn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(jcn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
					"			 	 from tb_psg_census_jco_or_p j \r\n" + 
					"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'\r\n" + 
					"		) hld \r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control) app " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
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
	
 public long getContact_Detailsof_Pers_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"	(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
					"	app.offr,app.jco,app.or1,app.form_code_control from\r\n" + 
					"	(select distinct \r\n" + 
					"		fv.unit_name as cmd_unit,\r\n" + 
					"		fvm.unit_name as corp_unit,\r\n" + 
					"		div.unit_name as div_unit,\r\n" + 
					"		bde.unit_name as bde_unit,\r\n" + 
					"		orb.unit_name,\r\n" + 
					"		hld.unit_sus_no,\r\n" + 
					"		orb.form_code_control,	\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='JCO') as jco,\r\n" + 
					"		count(hld.*) FILTER (where hld.typ='OR') as or1	\r\n" + 
					"		 from (\r\n" + 
					"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,cn.gmail,cn.mobile_no,cn.nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
					"			  union all\r\n" + 
					"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,jcn.gmail,jcn.mobile_no,jcn.nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
					"			 	 from tb_psg_census_jco_or_p j \r\n" + 
					"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'\r\n" + 
					"		) hld \r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control ) app " +SearchValue +") app1";
			
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
 					+ " or lower(app.bde_unit) like ? or cast(app.offr as character varying) = ? "
 					+ " or cast(app.jco as character varying) = ? or cast(app.or1 as character varying) = ? )";
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
 
  public List<Map<String, Object>>getSummaryof_Total_Number_ReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
					"	app.form_code_control,app.army_no,app.description,app.name,app.mobile_no,app.gmail,app.nic_mail from\r\n" + 
					"	(select distinct \r\n" + 
					"		fv.unit_name as cmd_unit,\r\n" + 
					"		fvm.unit_name as corp_unit,\r\n" + 
					"		div.unit_name as div_unit,\r\n" + 
					"		bde.unit_name as bde_unit,\r\n" + 
					"		orb.unit_name,\r\n" + 
					"		hld.unit_sus_no,\r\n" + 
					"		orb.form_code_control,	\r\n" + 
					"		hld.army_no,\r\n" + 
					"		hld.description,\r\n" + 
					"		hld.name,\r\n" + 
					"		hld.mobile_no,\r\n" + 
					"		hld.gmail,\r\n" + 
					"		hld.nic_mail\r\n" + 
					"		 from (\r\n" + 
					"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,PGP_SYM_DECRYPT(cn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(cn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
					"			  union all\r\n" + 
					"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,PGP_SYM_DECRYPT(jcn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(jcn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(jcn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
					"			 	 from tb_psg_census_jco_or_p j \r\n" + 
					"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'			 \r\n" + 
					"		) hld \r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	\r\n" + 
					"	group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.army_no," +
					"   hld.gmail,hld.mobile_no,hld.nic_mail,hld.description,hld.name) app" +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
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

  public long getSummaryof_Total_Number_TotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from \r\n" + 
					"(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
					"	app.form_code_control,app.army_no,app.description,app.name,app.mobile_no,app.gmail,app.nic_mail from\r\n" + 
					"	(select distinct \r\n" + 
					"		fv.unit_name as cmd_unit,\r\n" + 
					"		fvm.unit_name as corp_unit,\r\n" + 
					"		div.unit_name as div_unit,\r\n" + 
					"		bde.unit_name as bde_unit,\r\n" + 
					"		orb.unit_name,\r\n" + 
					"		hld.unit_sus_no,\r\n" + 
					"		orb.form_code_control,	\r\n" + 
					"		hld.army_no,\r\n" + 
					"		hld.description,\r\n" + 
					"		hld.name,\r\n" + 
					"		hld.mobile_no,\r\n" + 
					"		hld.gmail,\r\n" + 
					"		hld.nic_mail\r\n" + 
					"		 from (\r\n" + 
					"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,PGP_SYM_DECRYPT(cn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(cn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
					"			  union all\r\n" + 
					"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,PGP_SYM_DECRYPT(jcn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(jcn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(jcn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
					"			 	 from tb_psg_census_jco_or_p j \r\n" + 
					"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'			 \r\n" + 
					"		) hld \r\n" + 
					"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	\r\n" + 
					"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.army_no," + 
					"	hld.gmail,hld.mobile_no,hld.nic_mail,hld.description,hld.name) app " +SearchValue +") app1" ;
			
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
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? or lower(app.army_no) like ? "
 					+ " or lower(app.description) like ? or lower(app.name) like ? "
 					+ " or lower(app.gmail) like ? or cast(app.mobile_no as character varying) = ? or lower(app.nic_mail) like ?)";
			
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


public ArrayList<ArrayList<String>> pdf_getContact_Detailsof_Pers_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
		
		q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
				"	app.offr,app.jco,app.or1,app.form_code_control from\r\n" + 
				"	(select distinct \r\n" + 
				"		fv.unit_name as cmd_unit,\r\n" + 
				"		fvm.unit_name as corp_unit,\r\n" + 
				"		div.unit_name as div_unit,\r\n" + 
				"		bde.unit_name as bde_unit,\r\n" + 
				"		orb.unit_name,\r\n" + 
				"		hld.unit_sus_no,\r\n" + 
				"		orb.form_code_control,	\r\n" + 
				"		count(hld.*) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
				"		count(hld.*) FILTER (where hld.typ='JCO') as jco,\r\n" + 
				"		count(hld.*) FILTER (where hld.typ='OR') as or1	\r\n" + 
				"		 from (\r\n" + 
				"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,PGP_SYM_DECRYPT(cn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(cn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
				"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
				"			  union all\r\n" + 
				"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,PGP_SYM_DECRYPT(jcn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(jcn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(jcn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
				"			 	 from tb_psg_census_jco_or_p j \r\n" + 
				"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
				"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'\r\n" + 
				"		) hld \r\n" + 
				"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
				"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control) app " + SearchValue ;
		
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
	
public ArrayList<ArrayList<String>> pdf_getSummaryof_Total_Number_ReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)
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
			
		q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,\r\n" + 
				"	app.form_code_control,app.army_no,app.description,app.name,app.mobile_no,app.gmail,app.nic_mail from\r\n" + 
				"	(select distinct \r\n" + 
				"		fv.unit_name as cmd_unit,\r\n" + 
				"		fvm.unit_name as corp_unit,\r\n" + 
				"		div.unit_name as div_unit,\r\n" + 
				"		bde.unit_name as bde_unit,\r\n" + 
				"		orb.unit_name,\r\n" + 
				"		hld.unit_sus_no,\r\n" + 
				"		orb.form_code_control,	\r\n" + 
				"		hld.army_no,\r\n" + 
				"		hld.description,\r\n" + 
				"		hld.name,\r\n" + 
				"		hld.mobile_no,\r\n" + 
				"		hld.gmail,\r\n" + 
				"		hld.nic_mail\r\n" + 
				"		 from (\r\n" + 
				"				 select c.personnel_no as army_no,c.unit_sus_no,c.name,d.description,PGP_SYM_DECRYPT(cn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(cn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"				 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"				 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK' \r\n" + 
				"				 inner join tb_psg_census_contact_cda_account_details cn on cn.comm_id = c.id  and cn.status='1'\r\n" + 
				"			  union all\r\n" + 
				"				 select j.army_no,j.unit_sus_no,j.full_name,r.rank as description,PGP_SYM_DECRYPT(jcn.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(jcn.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,PGP_SYM_DECRYPT(jcn.nic_mail ::bytea,current_setting('miso.version'))  as nic_mail,case when j.category = 'JCO' then 'JCO' else 'OR'  END AS typ \r\n" + 
				"			 	 from tb_psg_census_jco_or_p j \r\n" + 
				"				 inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
				"				 inner join tb_psg_census_contact_cda_account_details_jco jcn on jcn.jco_id = j.id and jcn.status='1'			 \r\n" + 
				"		) hld \r\n" + 
				"	inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"	left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"	left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"	left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"	left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	\r\n" + 
				"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.army_no,\r\n" + 
				"	 hld.gmail,hld.mobile_no,hld.nic_mail,hld.description,hld.name) app " +SearchValue ;
		
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
				list.add(rs.getString("description"));//7
				list.add(rs.getString("name"));//8
				list.add(rs.getString("mobile_no"));//9
				list.add(rs.getString("gmail"));//10
				list.add(rs.getString("nic_mail"));//11
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
