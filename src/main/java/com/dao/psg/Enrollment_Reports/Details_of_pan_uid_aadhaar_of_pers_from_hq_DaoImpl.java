package com.dao.psg.Enrollment_Reports;

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


public class Details_of_pan_uid_aadhaar_of_pers_from_hq_DaoImpl implements Details_of_pan_uid_aadhaar_of_pers_from_hq_Dao{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> Details_of_pan_uid_aadhaar_of_pers_from_hqList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			String state_id,String district_id,String off_cat)
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, state_id, district_id,off_cat); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	
	String pageL = "";
    if(pageLength == -1){
		pageL = "ALL";
	}else {
		pageL = String.valueOf(pageLength);
		
	}

	try{	
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (off_cat.equals("1")) {
				
			
			q="select distinct orb.unit_name,comm.unit_sus_no,comm.personnel_no,\n"
					+ "rk.description,comm.name,\n"
					+ "COALESCE(PGP_SYM_DECRYPT(p.adhar_number ::bytea,current_setting('miso.version')),'NULL') as adhar_number,\n"
					+ "COALESCE(PGP_SYM_DECRYPT(p.pancard_number ::bytea,current_setting('miso.version')),'NULL') as pancard_number,\n"
					+ "COALESCE(PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')),'0') as mobile_no\n"
					+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "inner join tb_psg_census_detail_p p on comm.id=p.comm_id and  p.status = 1 \n"
					+ "left join tb_psg_census_contact_cda_account_details cda on cda.comm_id= comm.id \n"
					+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "and cda.status='1' where comm.status in ('1','5') and orb.status_sus_no='Active' \n"
					+SearchValue +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}		
			if (off_cat.equals("2")) {
			
				q = "SELECT orb.unit_name,\n"
						+ "       comm.unit_sus_no,\n"
						+ "       comm.army_no,\n"
						+ "       rk.rank,\n"
						+ "       comm.full_name,\n"
						+ "  	 COALESCE(PGP_SYM_DECRYPT(adh.aadhar_no ::bytea,current_setting('miso.version')),'NULL') as aadhar_no,\n"
						+ "	 COALESCE(PGP_SYM_DECRYPT(pan.pan_no ::bytea,current_setting('miso.version')),'NULL') as pan_no,\n"
						+ "	 COALESCE(PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')),'0') as mobile_no\n"
						+ "	FROM tb_psg_census_jco_or_p comm\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl orb\n"
						+ "    ON orb.sus_no = comm.unit_sus_no\n"
						+ "   AND orb.status_sus_no = 'Active'\n"
						+ " INNER JOIN tb_psg_mstr_rank_jco rk\n"
						+ "    ON comm.rank = rk.id\n"
						+ "  LEFT JOIN tb_psg_update_census_pan_no_jco pan\n"
						+ "    ON pan.jco_id = comm.id\n"
						+ "   AND pan.status = '1'\n"
						+ "  LEFT JOIN tb_psg_update_census_aadharno_jco adh\n"
						+ "    ON adh.jco_id = comm.id\n"
						+ "   AND adh.status = '1'\n"
						+ "  LEFT JOIN tb_psg_census_contact_cda_account_details_jco cda\n"
						+ "    ON cda.jco_id = comm.id\n"
						+ "   AND cda.status = '1'\n"
						+ "    WHERE comm.status IN ('1','5')\n"
						+ "   AND comm.update_census_status = '1'   \n"
						+SearchValue +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no,state_id, district_id,off_cat);
			ResultSet rs = stmt.executeQuery();   
	      ResultSetMetaData metaData = rs.getMetaData();
	     int k = startPage + 1;
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
			k ++;
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
	
	public long Details_of_pan_uid_aadhaar_of_pers_from_hq_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String state_id,String district_id,String off_cat) {
	 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, state_id, district_id,off_cat); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			
				
				if (off_cat.equals("1")) {
			
				  q="select count(app.*) from(\r\n"
					  		+ "select distinct orb.unit_name,comm.unit_sus_no,comm.personnel_no,\n"
					  		+ "rk.description,comm.name,\n"
					  		+ "COALESCE(PGP_SYM_DECRYPT(p.adhar_number ::bytea,current_setting('miso.version')),'NULL') as adhar_number,\n"
					  		+ "COALESCE(PGP_SYM_DECRYPT(p.pancard_number ::bytea,current_setting('miso.version')),'NULL') as pancard_number,\n"
					  		+ "COALESCE(PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')),'0') as mobile_no\n"
					  		+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					  		+ "inner join tb_psg_census_detail_p p on comm.id=p.comm_id and  p.status = 1 \n"
					  		+ "left join tb_psg_census_contact_cda_account_details cda on cda.comm_id= comm.id \n"
					  		+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					  		+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					  		+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					  		+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					  		+ "and cda.status='1' where comm.status in ('1','5') and orb.status_sus_no='Active'  \n"
					  		+SearchValue+" ) app" ;
				}
				if (off_cat.equals("2")) {
					
					 q="select count(app.army_no) from(SELECT orb.unit_name,\n"
					 		+ "       comm.unit_sus_no,\n"
					 		+ "       comm.army_no,\n"
					 		+ "       rk.rank,\n"
					 		+ "       comm.full_name,\n"
					 		+ "  	 COALESCE(PGP_SYM_DECRYPT(adh.aadhar_no ::bytea,current_setting('miso.version')),'NULL') as aadhar_no,\n"
					 		+ "	 COALESCE(PGP_SYM_DECRYPT(pan.pan_no ::bytea,current_setting('miso.version')),'NULL') as pan_no,\n"
					 		+ "	 COALESCE(PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')),'0') as mobile_no\n"
					 		+ "	FROM tb_psg_census_jco_or_p comm\n"
					 		+ " INNER JOIN tb_miso_orbat_unt_dtl orb\n"
					 		+ "    ON orb.sus_no = comm.unit_sus_no\n"
					 		+ "   AND orb.status_sus_no = 'Active'\n"
					 		+ " INNER JOIN tb_psg_mstr_rank_jco rk\n"
					 		+ "    ON comm.rank = rk.id\n"
					 		+ "  LEFT JOIN tb_psg_update_census_pan_no_jco pan\n"
					 		+ "    ON pan.jco_id = comm.id\n"
					 		+ "   AND pan.status = '1'\n"
					 		+ "  LEFT JOIN tb_psg_update_census_aadharno_jco adh\n"
					 		+ "    ON adh.jco_id = comm.id\n"
					 		+ "   AND adh.status = '1'\n"
					 		+ "  LEFT JOIN tb_psg_census_contact_cda_account_details_jco cda\n"
					 		+ "    ON cda.jco_id = comm.id\n"
					 		+ "   AND cda.status = '1'\n"
					 		+ "    WHERE comm.status IN ('1','5')\n"
					 		+ "   AND comm.update_census_status = '1'   " +SearchValue+") app" ;
					
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, state_id, district_id,off_cat);
				
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
	
	
	public List<Map<String, Object>> Details_of_pan_uid_aadhaar_of_pers_from_hq_Summary_List(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no,
			String state_id,String district_id,String off_cat)
	{
	 String SearchValue = GenerateQueryWhereClause_SQL_Summary(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, state_id, district_id,off_cat); 
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	
	String pageL = "";
 if(pageLength == -1){
		pageL = "ALL";
	}else {
		pageL = String.valueOf(pageLength);
		
	}

	try{	
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if (off_cat.equals("1")) {
				
			
			q="select distinct \n"
					+ "orb.unit_name,comm.unit_sus_no,\n"
					+ "count(comm.id) as officer,\n"
					+ "count(comm.id) as total\n"
					+ "from  tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "left join tb_psg_census_contact_cda_account_details cda on cda.comm_id= comm.id\n"
					+ "inner join tb_psg_census_detail_p p on comm.id=p.comm_id\n"
					+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "and cda.status='1' where comm.status in ('1','5') and  p.status = 1 and orb.status_sus_no='Active'  \n"
					+SearchValue + " group by 1,2  "+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}		
			if (off_cat.equals("2")) {
			
				q = "  select orb.unit_name,comm.unit_sus_no,\n"
						+ "count(comm.id) FILTER (where comm.status in ('1','5') and comm.category='JCO'  )as jco,\n"
						+ "count(comm.id) FILTER (where comm.status in ('1','5') and comm.category='OR'  )as ors,\n"
						+ "count(comm.id) as total\n"
						+ "from tb_psg_census_jco_or_p comm    \n"
						+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no  and orb.status_sus_no='Active'\n"
						+ "INNER JOIN tb_psg_mstr_rank_jco     rk on comm.rank=rk.id\n"
						+ "left join tb_psg_update_census_pan_no_jco pan on pan.jco_id=comm.id  and pan.status='1' \n"
						+ "left join tb_psg_update_census_aadharno_jco adh on adh.jco_id=comm.id  and adh.status ='1'\n"
						+ "LEFT join tb_psg_census_contact_cda_account_details_jco cda on cda.jco_id= comm.id and cda.status='1'\n"
						+ "where comm.status in ('1','5') and update_census_status ='1'  \n"
				+SearchValue + "group by 1,2  " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage  ;
			}
			
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL_Summary(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, state_id, district_id,off_cat);
	      ResultSet rs = stmt.executeQuery();   
	      ResultSetMetaData metaData = rs.getMetaData();
	     int k = startPage + 1;
	      int columnCount = metaData.getColumnCount();
	      
	  	while (rs.next()) {
	  		
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			
			list.add(columns);
			k ++;
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
	
	
	public long Details_of_pan_uid_aadhaar_of_pers_from_hq_Summary_Count(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String state_id,String district_id,String off_cat) {
	 String SearchValue = GenerateQueryWhereClause_SQL_Summary(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, state_id ,district_id,off_cat); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			
				
				if (off_cat.equals("1")) {
			
			q="select count(app.*) from(\r\n"
				+ "select distinct \n"
				+ "orb.unit_name,comm.unit_sus_no,\n"
				+ "count(comm.id) as officer,\n"
				+ "count(comm.id) as total\n"
				+ "from  tb_psg_trans_proposed_comm_letter comm \n"
				+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
				+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
				+ "left join tb_psg_census_contact_cda_account_details cda on cda.comm_id= comm.id\n"
				+ "inner join tb_psg_census_detail_p p on comm.id=p.comm_id\n"
				+ "left join all_fmn_view fv   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
				+ "left join all_fmn_view fvm   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
				+ "left join all_fmn_view div   on orb.sus_no = comm.unit_sus_no   and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
				+ "left join all_fmn_view bde   on orb.sus_no = comm.unit_sus_no   and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
				+ "and cda.status='1' where comm.status in ('1','5') and  p.status = 1 and orb.status_sus_no='Active'   \n"
		  		+ SearchValue +" group by 1,2	 ) app" ;
				}
				if (off_cat.equals("2")) {
					
				 q="select count(app.*) from(select orb.unit_name,comm.unit_sus_no,\n"
				 		+ "count(comm.id) FILTER (where comm.status in ('1','5') and comm.category='JCO'  )as jco,\n"
				 		+ "count(comm.id) FILTER (where comm.status in ('1','5') and comm.category='OR'  )as ors,\n"
				 		+ "count(comm.id) as total\n"
				 		+ "from tb_psg_census_jco_or_p comm    \n"
				 		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no  and orb.status_sus_no='Active'\n"
				 		+ "INNER JOIN tb_psg_mstr_rank_jco     rk on comm.rank=rk.id\n"
				 		+ "left join tb_psg_update_census_pan_no_jco pan on pan.jco_id=comm.id  and pan.status='1' \n"
				 		+ "left join tb_psg_update_census_aadharno_jco adh on adh.jco_id=comm.id  and adh.status ='1'\n"
				 		+ "LEFT join tb_psg_census_contact_cda_account_details_jco cda on cda.jco_id= comm.id and cda.status='1'\n"
				 		+ "where comm.status in ('1','5') and update_census_status ='1'  \n"
			  		+SearchValue+"group by 1,2 ) app" ;
					
				}
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL_Summary(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, state_id, district_id,off_cat);
				
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
	
	
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,String state_id,String district_id,String off_cat) {
	 
	
		String SearchValue ="";
		
		if (off_cat.equals("1")) {
		if(!Search.equals("")) { // for Input Filter 12
			SearchValue =" and  ";
			SearchValue +="( upper(orb.unit_name) like ? "
					+ " or upper(comm.unit_sus_no) like ?"
					+ " or upper(comm.personnel_no) like ? "
					+ "	or upper(rk.description) like ? "
					+ " or upper(comm.name) like ? "
//					+ " or upper(comm.personnel_no) like ?"
//					+ " or PGP_SYM_DECRYPT(p.adhar_number ::bytea,current_setting('miso.version')) like ? "
//					+ " or PGP_SYM_DECRYPT(p.pancard_number ::bytea,current_setting('miso.version')) like ? "
//					+ " or PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')) like ? "
					+ ") ";
		}
		
		if( !cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and orb.form_code_control like ? ";
			}
		}
		if( !cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		if( !cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		
		if( !cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and  orb.form_code_control like ?  ";	
			}
			else {
				SearchValue += " and  orb.form_code_control like ? ";
			}
		}
		
//		if( !unit_name.equals("")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  orb.unit_name = ? ";	
//			}
//			else {
//				SearchValue += " and orb.unit_name like ?";
//			}
//		}
		
		
		if( !unit_sus_no.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and orb.sus_no = ? ";	
			}
			else {
				SearchValue += " and orb.sus_no = ? ";
			}
		}
		
//		if( !state_id.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and la.permanent_state::text = ?  ";	
//			}
//			else {
//				SearchValue += " and la.permanent_state::text = ? ";
//			}
//		}
//		
//		if( !district_id.equals("0")) {
//			if (SearchValue.contains("where")) {
//				SearchValue += " and  la.permanent_district::text = ?  ";	
//			}
//			else {
//				SearchValue += " and la.permanent_district::text = ? ";
//			}
//		}
	}

		if (off_cat.equals("2")) {
			if(!Search.equals("")) { // for Input Filter 12
				SearchValue =" and  ";
				SearchValue +="(upper(orb.unit_name) like ?  "
						+ " or upper(comm.unit_sus_no) like ? "
						+ " or upper(comm.army_no) like ? "
						+ "	or upper(rk.rank) like ? "
						+" or upper(comm.full_name) like ? "
//						+ " or PGP_SYM_DECRYPT(adh.aadhar_no ::bytea,current_setting('miso.version')) like ? "
//						+ " or PGP_SYM_DECRYPT(pan.pan_no ::bytea,current_setting('miso.version')) like ? "
//						+ " or PGP_SYM_DECRYPT(cda.mobile_no ::bytea,current_setting('miso.version')) like ? "
						+ " ) ";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and orb.form_code_control like ? ";
				}
			}
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  orb.unit_name = ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
//				}
//			}
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and orb.sus_no = ? ";	
				}
				else {
					SearchValue += " and orb.sus_no = ? ";
				}
			}
			
//			if( !state_id.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and la.permanent_state::text = ?  ";	
//				}
//				else {
//					SearchValue += " and la.permanent_state::text = ? ";
//				}
//			}
//			
//			if( !district_id.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  la.permanent_district::text = ?  ";	
//				}
//				else {
//					SearchValue += " and la.permanent_district::text = ? ";
//				}
//			}
		}
			
		
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,String state_id,String district_id,String off_cat){
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
//				flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
//				
//				
//				flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
//				
//				flag += 1;
//				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				

				
			}

			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
			}
			if(!cont_corps.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
			}
			if(!cont_div.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
			}
			if(!cont_bde.equals("0")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
			}
//			if(!unit_name.equals("")) {
//				flag += 1;
//				stmt.setString(flag, unit_name.toUpperCase());
//			}
			if(!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());
			}
			if(!state_id.equals("0")) {
				flag += 1;
				stmt.setString(flag, state_id);
			}
			if(!district_id.equals("0")) {
				flag += 1;
				stmt.setString(flag, district_id);
			}
			

		}catch (Exception e) {}
		
		return stmt;
		
	}

	  
	
	
	 
	 	
	 
	 
	 
	 public String GenerateQueryWhereClause_SQL_Summary(String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String unit_sus_no,
			 String state_id,String district_id,String off_cat) {
		 
		
			String SearchValue ="";
			
			if (off_cat.equals("1")) {
			if(!Search.equals("")) { // for Input Filter 6
				SearchValue =" and  ";
				SearchValue +="( upper(orb.unit_name) like ? "
						+ " or upper(comm.unit_sus_no) like ?)";
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
					
				}
				else {
					SearchValue += " and orb.form_code_control like ? ";
				}
			}
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  orb.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  orb.form_code_control like ? ";
				}
			}
			
//			if( !unit_name.equals("")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  orb.unit_name = ? ";	
//				}
//				else {
//					SearchValue += " and orb.unit_name like ?";
//				}
//			}
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and orb.sus_no = ? ";	
				}
				else {
					SearchValue += " and orb.sus_no = ? ";
				}
			}
			
//			if( !state_id.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and la.permanent_state::text = ?  ";	
//				}
//				else {
//					SearchValue += " and la.permanent_state::text = ? ";
//				}
//			}
//			
//			if( !district_id.equals("0")) {
//				if (SearchValue.contains("where")) {
//					SearchValue += " and  la.permanent_district::text = ?  ";	
//				}
//				else {
//					SearchValue += " and la.permanent_district::text = ? ";
//				}
//			}
			
			
			

			}
			
			if (off_cat.equals("2")) {
				if(!Search.equals("")) { // for Input Filter 6
					SearchValue =" and  ";
					SearchValue +="(upper(orb.unit_name) like ?  "
							+ " or upper(comm.unit_sus_no) like ?"
							+ ")";
				}
				
				if( !cont_comd.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and orb.form_code_control like ? ";
					}
				}
				if( !cont_corps.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				if( !cont_div.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				if( !cont_bde.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.form_code_control like ?  ";	
					}
					else {
						SearchValue += " and  orb.form_code_control like ? ";
					}
				}
				
//				if( !unit_name.equals("")) {
//					if (SearchValue.contains("where")) {
//						SearchValue += " and  orb.unit_name = ? ";	
//					}
//					else {
//						SearchValue += " and orb.unit_name like ?";
//					}
//				}
				
				
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and orb.sus_no = ? ";	
					}
					else {
						SearchValue += " and orb.sus_no = ? ";
					}
				}
//				if( !state_id.equals("0")) {
//					if (SearchValue.contains("where")) {
//						SearchValue += " and la.permanent_state::text = ?  ";	
//					}
//					else {
//						SearchValue += " and la.permanent_state::text = ? ";
//					}
//				}
//				
//				if( !district_id.equals("0")) {
//					if (SearchValue.contains("where")) {
//						SearchValue += " and  la.permanent_district::text = ?  ";	
//					}
//					else {
//						SearchValue += " and la.permanent_district::text = ? ";
//					}
//				}
				

				}
			
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL_Summary(PreparedStatement
				  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
					String unit_name,String unit_sus_no,String state_id,String district_id,String off_cat) {
			int flag = 0;
			try {
				if(!Search.equals("")) {			
					
				
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
				}

				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
				if(!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps.toUpperCase()+"%");
					
				}
				if(!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div.toUpperCase()+"%");
					
				}
				if(!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde.toUpperCase()+"%");
					
				}
//				if(!unit_name.equals("")) {
//					flag += 1;
//					stmt.setString(flag, unit_name.toUpperCase());
//					
//				}
				if(!unit_sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_sus_no.toUpperCase());
					
				}
				if(!state_id.equals("0")) {
					flag += 1;
					stmt.setString(flag, state_id);
				}
				if(!district_id.equals("0")) {
					flag += 1;
					stmt.setString(flag, district_id);
				}
				

			}catch (Exception e) {}
			
			return stmt;
			
		}

	 }
