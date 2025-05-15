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


public class Pers_Foreign_LanguageDAOImpl implements Pers_Foreign_LanguageDAO{

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> getsearch_foreign_language_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String languageID, String stdID,
			String rankID, String rankjcoID, String typeID, String posn_date) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, /*user_role_id,*/ languageID, stdID, rankID, rankjcoID, typeID, posn_date); 
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
			
			if (typeID.equals("1")) {
			q=" select distinct \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "		fvm.unit_name as corp_unit,\n"
					+ "		div.unit_name as div_unit,\n"
					+ "		bde.unit_name as bde_unit,\n"
					+ "		orb.unit_name,\n"
					+ "		comm.unit_sus_no,\n"
					+ "comm.personnel_no,rk.description,comm.name, CASE \n"
					+ "    WHEN ms.id = '30' THEN la.f_other_language\n"
					+ "    ELSE ms.foreign_language_name\n"
					+ "  END AS foreign_language_name,nm.name as lang_std \n"
					+ "from tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_psg_census_language la on la.comm_id =comm.id \n"
					+ "inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "inner join tb_psg_lang_std nm on nm.id = la.lang_std\n"
					+ "inner join tb_psg_census_detail_p cen ON comm.id=cen.comm_id and cen.status = '1'\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
					+ "where comm.status in ('1','5') and la.status in ('1','2') and la.foreign_language !='0' and orb.status_sus_no='Active' \n" 
					+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q=" select distinct \n" + 
						"fv.unit_name as cmd_unit,\n" + 
						"		fvm.unit_name as corp_unit,\n" + 
						"		div.unit_name as div_unit,\n" + 
						"		bde.unit_name as bde_unit,\n" + 
						"		orb.unit_name,\n" + 
						"		jco.unit_sus_no,\n" + 
						"jco.army_no,rk.rank,jco.full_name, CASE \n"
						+ "    WHEN ms.id = '30' THEN la.f_other_language\n"
						+ "    ELSE ms.foreign_language_name\n"
						+ "  END AS foreign_language_name,nm.name as lang_std\n" + 
						"from tb_psg_census_jco_or_p jco\n" + 
						"inner join tb_psg_census_language_jco la on la.jco_id =jco.id \n" + 
						"inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id \n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id\n" + 
						"inner join tb_psg_lang_std nm on nm.id = la.lang_std\n" + 
						"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no \n" + 
						"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  \n" + 
						"and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
						"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  \n" + 
						"and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n" + 
						"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  \n" + 
						"and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \n" + 
						"where jco.status in ('1','5') and la.status in ('1','2') and  la.foreign_language !='0' and orb.status_sus_no='Active' \n"
						+SearchValue+" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, /*user_role_id, */languageID, stdID, rankID, rankjcoID, typeID, posn_date);
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
 
 
 
	public long getsearch_foreign_language_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String languageID,String stdID,String rankID, String rankjcoID, String typeID, String posn_date) {
	 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, /*user_role_id,*/ languageID, stdID, rankID, rankjcoID, typeID, posn_date);
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				if (typeID.equals("1")) {
			  q="select count(app.*) from( select distinct \n"
			  		+ "fv.unit_name as cmd_unit,\n"
			  		+ "		fvm.unit_name as corp_unit,\n"
			  		+ "		div.unit_name as div_unit,\n"
			  		+ "		bde.unit_name as bde_unit,\n"
			  		+ "		orb.unit_name,\n"
			  		+ "		comm.unit_sus_no,\n"
			  		+ "comm.personnel_no,rk.description,comm.name, CASE \n"
			  		+ "    WHEN ms.id = '30' THEN la.f_other_language\n"
			  		+ "    ELSE ms.foreign_language_name\n"
			  		+ "  END AS foreign_language_name,nm.name as lang_std \n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm \n"
			  		+ "inner join tb_psg_census_language la on la.comm_id =comm.id \n"
			  		+ "inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
			  		+ "inner join tb_psg_lang_std nm on nm.id = la.lang_std\n"
			  		+ "inner join tb_psg_census_detail_p cen ON comm.id=cen.comm_id and cen.status = '1'\n"
			  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
			  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
			  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
			  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n"
			  		+ "where comm.status in ('1','5') and la.status in ('1','2') and la.foreign_language !='0' and orb.status_sus_no='Active'  \n" + 
			  		 ""+SearchValue+" ) app" ;
				}
				if (typeID.equals("2")) {
					q="select count(app.*) from(select distinct \n" + 
							"fv.unit_name as cmd_unit,\n" + 
							"		fvm.unit_name as corp_unit,\n" + 
							"		div.unit_name as div_unit,\n" + 
							"		bde.unit_name as bde_unit,\n" + 
							"		orb.unit_name,\n" + 
							"		jco.unit_sus_no,\n" + 
							"jco.army_no,rk.rank,jco.full_name, CASE \n"
							+ "    WHEN ms.id = '30' THEN la.f_other_language\n"
							+ "    ELSE ms.foreign_language_name\n"
							+ "  END AS foreign_language_name,nm.name as lang_std\n" + 
							"from tb_psg_census_jco_or_p jco\n" + 
							"inner join tb_psg_census_language_jco la on la.jco_id =jco.id \n" + 
							"inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id \n" + 
							"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \n" + 
							"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id\n" + 
							"inner join tb_psg_lang_std nm on nm.id = la.lang_std\n" + 
							"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no \n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  \n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
							"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  \n" + 
							"and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n" + 
							"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  \n" + 
							"and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \n" + 
							"where jco.status in ('1','5') and la.status in ('1','2') and  la.foreign_language !='0' and orb.status_sus_no='Active' \r\n"
					  		+ ""+SearchValue+" ) app" ;
				}
				PreparedStatement stmt = conn.prepareStatement(q);
			
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, /*user_role_id, */languageID, stdID, rankID, rankjcoID, typeID, posn_date);
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
 
 
 
	public List<Map<String, Object>> getsearch_foreign_language_tablesum(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String unit_name, String unit_sus_no, String languageID, String stdID, String rankID, String rankjcoID, String typeID, String posn_date ) 
	{
	 String SearchValue = GenerateQueryWhereClause_SQL(search, cont_comd, cont_corps,cont_div, cont_bde,
			 unit_name, unit_sus_no, /*user_role_id,*/ languageID, stdID, rankID, rankjcoID, typeID, posn_date); 
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
			
			if (typeID.equals("1")) {
			q=" select \n"
					+ "fv.unit_name as cmd_unit,\n"
					+ "		fvm.unit_name as corp_unit,\n"
					+ "		div.unit_name as div_unit,\n"
					+ "		bde.unit_name as bde_unit,\n"
					+ "		orb.unit_name,\n"
					+ "		comm.unit_sus_no,\n"
					+ "		count(la.comm_id)  as officer,\n"
					+ "ms.foreign_language_name as foreign_language, \n"
					+ "count(la.comm_id)  as total \n"
					+ "from tb_psg_trans_proposed_comm_letter comm \n"
					+ "inner join tb_psg_census_language la on la.comm_id =comm.id \n"
					+ "inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
					+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
					+ "inner join tb_psg_lang_std nm on nm.id = la.lang_std\n"
					+ "left join tb_psg_census_detail_p cen ON comm.id=cen.comm_id and cen.status = '1'\n"
					+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
					+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
					+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
					+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  \n"
					+ "and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'where comm.status in ('1','5') and la.status in ('1','2')  and la.foreign_language !='0' and orb.status_sus_no='Active'\n" 
					+SearchValue+"   group by 1,2,3,4,5,6,8 " +" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			if (typeID.equals("2")) {
				q=" select \n" + 
						"fv.unit_name as cmd_unit,\n" + 
						"                fvm.unit_name as corp_unit,\n" + 
						"                div.unit_name as div_unit,\n" + 
						"                bde.unit_name as bde_unit,\n" + 
						"                orb.unit_name,\n" + 
						"                jco.unit_sus_no,\n" + 
						"count(la.jco_id) FILTER (where  jco.category='JCO'  )as jco,\n" + 
						"count(la.jco_id) FILTER (where  jco.category='OR'  )as ors,\n" + 
						"ms.foreign_language_name as foreign_language,\n" +
						"count(la.jco_id)  as total\n" + 
						"from tb_psg_census_jco_or_p jco\n" + 
						"inner join tb_psg_census_language_jco la on la.jco_id =jco.id \n" + 
						"inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id \n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id\n" + 
						"inner join tb_psg_lang_std nm on nm.id = la.lang_std\n" + 
						"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
						"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n" + 
						"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
						"where jco.status in ('1','5') and la.status in ('1','2') and  la.foreign_language !='0' and orb.status_sus_no='Active' \n"
						+SearchValue+" group by 1,2,3,4,5,6,9\n" + 
								" ORDER BY "+ orderColunm +""+orderType +" limit " +pageL+" OFFSET "+startPage ;
			}
			stmt=conn.prepareStatement(q);
		
			stmt = setQueryWhereClause_SQL(stmt,search, cont_comd, cont_corps, cont_div, cont_bde,
					 unit_name, unit_sus_no, /*user_role_id, */languageID, stdID, rankID, rankjcoID, typeID, posn_date);
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

 
	public long getsearch_foreign_language_tablecountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String unit_sus_no,/*String user_role_id,*/String languageID,String stdID, String rankID, String rankjcoID, String typeID, String posn_date) {
	 String SearchValue = GenerateQueryWhereClause_SQL(Search, cont_comd, cont_corps,cont_div, cont_bde,
				 unit_name, unit_sus_no, /*user_role_id,*/ languageID, stdID, rankID, rankjcoID, typeID, posn_date); 
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				if (typeID.equals("1")) {
			  q="select count(app.*) from(select \n"
			  		+ "fv.unit_name as cmd_unit,\n"
			  		+ "		fvm.unit_name as corp_unit,\n"
			  		+ "		div.unit_name as div_unit,\n"
			  		+ "		bde.unit_name as bde_unit,\n"
			  		+ "		orb.unit_name,\n"
			  		+ "		comm.unit_sus_no,\n"
			  		+ "		count(la.comm_id)  as officer,\n"
			  		+ "ms.foreign_language_name as foreign_language, \n"
			  		+ "count(la.comm_id)  as total \n"
			  		+ "from tb_psg_trans_proposed_comm_letter comm \n"
			  		+ "inner join tb_psg_census_language la on la.comm_id =comm.id \n"
			  		+ "inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id\n"
			  		+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = comm.unit_sus_no \n"
			  		+ "INNER JOIN cue_tb_psg_rank_app_master rk on comm.rank=rk.id\n"
			  		+ "inner join tb_psg_lang_std nm on nm.id = la.lang_std\n"
			  		+ "left join tb_psg_census_detail_p cen ON comm.id=cen.comm_id and cen.status = '1'\n"
			  		+ "left join all_fmn_view fv  on orb.sus_no = comm.unit_sus_no \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n"
			  		+ "left join all_fmn_view fvm  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n"
			  		+ "left join all_fmn_view div  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n"
			  		+ "left join all_fmn_view bde  on orb.sus_no = comm.unit_sus_no  \n"
			  		+ "and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'where comm.status in ('1','5') and la.status in ('1','2')  and la.foreign_language !='0' and orb.status_sus_no='Active' \r\n"
			  		+ ""+SearchValue+" group by 1,2,3,4,5,6,8  ) app" ;
			}
			if (typeID.equals("2")) {
				q="select count(app.*) from(select  \n" + 
						"fv.unit_name as cmd_unit,\n" + 
						"                fvm.unit_name as corp_unit,\n" + 
						"                div.unit_name as div_unit,\n" + 
						"                bde.unit_name as bde_unit,\n" + 
						"                orb.unit_name,\n" + 
						"                jco.unit_sus_no,\n" + 
						"count(la.jco_id) FILTER (where  jco.category='JCO'  )as jco,\n" + 
						"count(la.jco_id) FILTER (where  jco.category='OR'  )as ors,\n" + 
						"ms.foreign_language_name as foreign_language, \n" +
						"count(la.jco_id) as total\n" + 
						"from tb_psg_census_jco_or_p jco\n" + 
						"inner join tb_psg_census_language_jco la on la.jco_id =jco.id \n" + 
						"inner join tb_psg_mstr_foreign_language ms on la.foreign_language= ms.id \n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = jco.unit_sus_no \n" + 
						"INNER JOIN tb_psg_mstr_rank_jco rk on jco.rank=rk.id\n" + 
						"inner join tb_psg_lang_std nm on nm.id = la.lang_std\n" + 
						"left join all_fmn_view fv  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\n" + 
						"left join all_fmn_view div  on orb.sus_no = jco.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\n" + 
						"left join all_fmn_view bde  on orb.sus_no = jco.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\n" + 
						"where jco.status in ('1','5') and la.status in ('1','2') and  la.foreign_language !='0' and orb.status_sus_no='Active' \r\n"
				  		+ ""+SearchValue+" group by 1,2,3,4,5,6,9 "
				  		+ " order by fv.unit_name ) app" ;
			}
				PreparedStatement stmt = conn.prepareStatement(q);
			
				stmt = setQueryWhereClause_SQL(stmt,Search, cont_comd, cont_corps, cont_div, cont_bde,
						 unit_name, unit_sus_no, /*user_role_id, */languageID, stdID, rankID, rankjcoID, typeID, posn_date);
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
			String unit_name,String unit_sus_no,/*String user_role_id,*/String languageID, String stdID, String rankID, String rankjcoID, String typeID, String posn_date) {
	 
	
		String SearchValue ="";
		
		if (typeID.equals("1")) {
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and  ";
			
			SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
					+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ?  or upper(comm.unit_sus_no) like ?"
					+ " or upper(comm.personnel_no) like ? or upper(rk.description) like ? or upper(comm.name) like ?"
					+ " or upper(ms.foreign_language_name) like ? or upper(nm.name) like ?) ";
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
				SearchValue += " and orb.form_code_control like ? ";
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
		
		
		if( !languageID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and ms.id::text = ? ";	
			}
			else {
				SearchValue += " and ms.id::text = ? ";
			}
		}
		
		
		if( !stdID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and nm.id::text = ? ";	
			}
			else {
				SearchValue += " and nm.id::text = ? ";
			}
		}
		
		
		if( !rankID.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and rk.id::text = ? ";	
			}
			else {
				SearchValue += " and rk.id::text = ? ";
			}
		}
		
		if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
			if (SearchValue.contains("where")) {
				  SearchValue +=  " and la.f_exam_pass::text <= EXTRACT(YEAR FROM ?::date)::text"; 
//				  and ltrim(la.f_exam_pass, 'yyyy'::text) <= ltrim(?, 'yyyy'::text)  
			} 
			else { 
				  SearchValue += " and la.f_exam_pass::text <= EXTRACT(YEAR FROM ?::date)::text"; 
			  }
		}
		/*if( !user_role_id.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(lm.userId as character varying) = ? ";	
			}
			else {
				SearchValue += " where cast(lm.userId as character varying) = ? ";
			}
		}*/
		}
		if (typeID.equals("2")) {
			
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(fv.unit_name) like ? or upper(fvm.unit_name) like ? or upper(div.unit_name) like ? "
						+ " or upper(bde.unit_name) like ? or upper(orb.unit_name) like ?  or upper(jco.unit_sus_no) like ?"
						+ " or upper(jco.army_no) like ? or upper(rk.rank) like ? or upper(jco.full_name) like ?"
						+ " or upper(ms.foreign_language_name) like ? or upper(nm.name) like ?) ";
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
			
			
			if( !languageID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and ms.id::text = ? ";	
				}
				else {
					SearchValue += " and ms.id::text = ? ";
				}
			}
			
			
			if( !stdID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and nm.id::text = ? ";	
				}
				else {
					SearchValue += " and nm.id::text = ? ";
				}
			}
			
			
			if( !rankjcoID.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and rk.id::text = ? ";	
				}
				else {
					SearchValue += " and rk.id::text = ? ";
				}
			}
			
			if(!posn_date.equals("") && !posn_date.equals("DD/MM/YYYY")){ 
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and la.f_exam_pass::text <= EXTRACT(YEAR FROM ?::date)::text"; 
				  } 
				else { 
					  SearchValue += "and la.f_exam_pass::text <= EXTRACT(YEAR FROM ?::date)::text"; 
				  }
			}
		}
		String prf_whr = "";
		String prf_whr1 = ""; 
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
			  stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
				String unit_name,String unit_sus_no,/*String user_role_id,*/String languageID,String stdID,String rankID, String rankjcoID, String typeID, String posn_date) {
		int flag = 0;
		try {
			if(!Search.equals("")) {			
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				

				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, Search.toUpperCase()+"%");
				
				
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
//				
//			}
			if(!unit_sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_sus_no.toUpperCase());
				
			}
			if(!languageID.equals("0")) {
				flag += 1;
				stmt.setString(flag, languageID.toUpperCase());
				
			}
			if(!stdID.equals("0")) { 
				flag += 1; stmt.setString(flag, stdID.toUpperCase());
			
			  }
			 
			if(!rankID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankID.toUpperCase());
				
			}
			
			if(!rankjcoID.equals("0")) {
				flag += 1;
				stmt.setString(flag, rankjcoID.toUpperCase());
			}
			
			if(!posn_date.equals("")) {
				flag += 1;
				stmt.setString(flag, posn_date);
				
			}
			/*if(!user_role_id.equals("")) {
				flag += 1;
				stmt.setString(flag, user_role_id);
				
			}*/
			

		}catch (Exception e) {}
		
		return stmt;
		
	}
		
}