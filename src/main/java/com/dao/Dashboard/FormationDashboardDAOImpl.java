package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.Date;
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

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class FormationDashboardDAOImpl implements FormationDashboardDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }	
			
	public List<Map<String, Object>> getTptSummaryInPrfWithTypeVehData(HttpSession sessionA,int type,String prf_code)
  	{				
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String whr="",qry="",tbl="" , veh_type="";
  			if(type == 0) {
				tbl="tb_tms_census_retrn"; 
  				veh_type ="A";
  			}
			if(type == 1) {
				tbl="tb_tms_mvcr_parta_dtl";
				veh_type ="B";
			}
			if(type == 2) {
				tbl="tb_tms_emar_report";
				veh_type ="C";
			}
			String prfWhr=""; 
			if(!prf_code.equals("0")) {
				prfWhr = " and m.prf_group= ? ";
			}
			
			if(roleAccess.equals("HeadQuarter")) {
				if(veh_type == "A" || veh_type == "B") {
				qry="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,cmd.short_form as colname,prf_ue_uh.form_code as colcode\r\n" + 
						"	from\r\n" + 
						"	(SELECT count(pd.ba_no) AS uh, \r\n" + 
						"			ue.total as ue,\r\n" + 
						"			substr(form_code_control,1,1) as form_code\r\n" + 
						"	   FROM tb_tms_banum_dirctry d \r\n" + 
						"		 JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text \r\n" + 
						"		 JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
						"		 LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
						"		 JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
						"\r\n" + 
						"	  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
						"	  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
						"	  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
						"	  inner join orbat_view_cmd cmd on substr(prf_ue_uh.form_code,1,1) = cmd.cmd_code\r\n" + 
						"	GROUP BY 3,4";
				}
				if(veh_type == "C") {
					qry="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,cmd.cmd_name as colname,prf_ue_uh.form_code as colcode\r\n" + 
							"	from\r\n" + 
							"	(SELECT count(pd.em_no) AS uh, \r\n" + 
							"			ue.total as ue,\r\n" + 
							"			substr(form_code_control,1,1) as form_code\r\n" + 
							"	   FROM tb_tms_banum_dirctry d \r\n" + 
							"		 JOIN $tableName pd ON d.ba_no::text = pd.em_no::text \r\n" + 
							"		 JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
							"		 LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
							"		 JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
							"\r\n" + 
							"	  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
							"	  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
							"	  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
							"	  inner join orbat_view_cmd cmd on substr(prf_ue_uh.form_code,1,1) = cmd.cmd_code\r\n" + 
							"	GROUP BY 3,4";
					}
			}else if(roleAccess.equals("Line_dte")) {
				if(veh_type == "A" || veh_type == "B") {
				qry="select coalesce(sum(prf_ue_uh.ue),'0') as ue,coalesce(sum(prf_ue_uh.uh),'0') as uh,cmd.short_form as colname,prf_ue_uh.form_code as colcode\r\n" + 
						"	from\r\n" + 
						"	(SELECT count(pd.ba_no) AS uh, \r\n" + 
						"			ue.total as ue,\r\n" + 
						"			substr(form_code_control,1,1) as form_code\r\n" + 
						"	FROM tb_tms_banum_dirctry d \r\n" + 
						"		JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text \r\n" + 
						"		JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
						"		LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
						"		JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
						"		and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) \r\n"+
						"\r\n" + 
						"	  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
						"	  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
						"	  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
						"	  inner join orbat_view_cmd cmd on substr(prf_ue_uh.form_code,1,1) = cmd.cmd_code\r\n" + 
						"	GROUP BY 3,4";
				}
				if(veh_type == "C") {
					qry="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,cmd.cmd_name as colname,prf_ue_uh.form_code as colcode\r\n" + 
							"	from\r\n" + 
							"	(SELECT count(pd.em_no) AS uh, \r\n" + 
							"			ue.total as ue,\r\n" + 
							"			substr(form_code_control,1,1) as form_code\r\n" + 
							"	   FROM tb_tms_banum_dirctry d \r\n" + 
							"		 JOIN $tableName pd ON d.ba_no::text = pd.em_no::text \r\n" + 
							"		 JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
							"		 LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
							"		 JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
							"\r\n" + 
							"	  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
							"	  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
							"	  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
							"	  inner join orbat_view_cmd cmd on substr(prf_ue_uh.form_code,1,1) = cmd.cmd_code\r\n" + 
							"	GROUP BY 3,4";
					}
			}else if(roleAccess.equals("Formation")) {
  				if(roleSubAccess.equals("Command")) {
  					whr=String.valueOf(roleFormationNo.charAt(0));
  					if(veh_type == "A" || veh_type == "B"){
  						qry="(select sum(prf_ue_uh.ue) as ue,\r\n" + 
  		  						"		sum(prf_ue_uh.uh) as uh,\r\n" + 
  		  						"	(case when right(prf_ue_uh.form_code,2)='00' " + 
  		  						" 		then (select distinct cmd_name from orbat_view_cmd where substr(prf_ue_uh.form_code,1,1) = cmd_code ) \r\n" + 
  		  						"	 	when right(prf_ue_uh.form_code,4)='0000' then (select distinct area_name from orbat_view_area where substr(prf_ue_uh.form_code,4,3) = area_code)\r\n" + 
  		  						" 		else (select distinct coprs_name from orbat_view_corps where substr(prf_ue_uh.form_code,2,2) =  corps_code) end)  as colname,\r\n" + 
  		  						"	prf_ue_uh.form_code as colcode\r\n" + 
  		  						"from\r\n" + 
  		  						"(SELECT m.prf_group,\r\n" + 
  		  						"    count(pd.ba_no) AS uh,\r\n" + 
  		  						"    ue.total as ue,\r\n" + 
  		  						" 	substr(form_code_control,1,3) as form_code\r\n" + 
  		  						"	--form_code_control as form_code\r\n" + 
  		  						"   FROM tb_tms_banum_dirctry d\r\n" + 
  		  						"     JOIN tb_tms_mvcr_parta_dtl pd ON d.ba_no::text = pd.ba_no::text\r\n" + 
  		  						"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
  		  						"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  		  						"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  		  						"	 	and u.form_code_control like ? \r\n" + 
  		  						"  WHERE d.veh_cat::text = '"+veh_type+"' AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL \r\n" + 
  		  						"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  		  						"  ORDER BY pd.sus_no) as prf_ue_uh \r\n" + 
  		  						"GROUP BY 4)\r\n" + 
  		  						"\r\n" + 
  		  						"UNION ALL\r\n" + 
  		  						"\r\n" + 
  		  						"(select sum(prf_ue_uh.ue) as ue,\r\n" + 
  		  						"		sum(prf_ue_uh.uh) as uh,\r\n" + 
  		  						"	 (select distinct area_name from orbat_view_area where substr(prf_ue_uh.form_code,4,3) = area_code)  as colname,\r\n" + 
  		  						"	prf_ue_uh.form_code as colcode\r\n" + 
  		  						"from\r\n" + 
  		  						"(SELECT m.prf_group,\r\n" + 
  		  						"    count(pd.ba_no) AS uh,\r\n" + 
  		  						"    ue.total as ue,\r\n" + 
  		  						"	form_code_control as form_code\r\n" + 
  		  						"   FROM tb_tms_banum_dirctry d\r\n" + 
  		  						"     JOIN tb_tms_mvcr_parta_dtl pd ON d.ba_no::text = pd.ba_no::text\r\n" + 
  		  						"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
  		  						"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  		  						"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  		  						"	 	and u.form_code_control like ? \r\n" + 
  		  						"  WHERE d.veh_cat::text = '"+veh_type+"' AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL \r\n" + 
  		  						"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  		  						"  ORDER BY pd.sus_no) as prf_ue_uh where prf_ue_uh.form_code like '_00%' and  prf_ue_uh.form_code like '%0000' and  prf_ue_uh.form_code not like '%000000000'\r\n" + 
  		  						"GROUP BY 4)";
	  					/*qry="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n"+
	  							"	(case when right(prf_ue_uh.form_code,2)='00' \r\n"+
	  							" 		then (select distinct cmd_name from orbat_view_cmd where substr(prf_ue_uh.form_code,1,1) = cmd_code ) \r\n"+
	  							" 		else (select distinct coprs_name from orbat_view_corps where substr(prf_ue_uh.form_code,2,2) =  corps_code) end)  as colname,\r\n"+
	  							"	prf_ue_uh.form_code as colcode\r\n"+
	  							"from\r\n"+
	  							"(SELECT --m.prf_group,\r\n"+
	  							"    count(pd.ba_no) AS uh,\r\n"+
	  							"    ue.total as ue,\r\n"+
	  							"	substr(form_code_control,1,3) as form_code\r\n"+
	  							"   FROM tb_tms_banum_dirctry d\r\n"+
	  							"     JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text\r\n"+
	  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n"+
	  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n"+
	  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n"+
	  							"	 	and u.form_code_control like ? \r\n"+
	  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n"+
	  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n"+
	  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n"+
	  							"GROUP BY 4";*/
  					}
  					if(veh_type == "C") {
  						qry = "select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  								"	(case when right(prf_ue_uh.form_code,2)='00' \r\n" + 
  								" 		then (select distinct cmd_name from orbat_view_cmd where substr(prf_ue_uh.form_code,1,1) = cmd_code ) \r\n" + 
  								" 		else (select distinct coprs_name from orbat_view_corps where substr(prf_ue_uh.form_code,2,2) =  corps_code) end)  as colname,\r\n" + 
  								"	prf_ue_uh.form_code as colcode\r\n" + 
  								"from\r\n" + 
  								"(SELECT --m.prf_group,\r\n" + 
  								"    count(pd.em_no) AS uh,\r\n" + 
  								"    ue.total as ue,\r\n" + 
  								"	substr(form_code_control,1,3) as form_code\r\n" + 
  								"   FROM tb_tms_banum_dirctry d\r\n" + 
  								"     JOIN $tableName pd ON d.ba_no::text = pd.em_no::text\r\n" + 
  								"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
  								"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  								"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  								"	 	and u.form_code_control like ? \r\n" + 
  								"  WHERE d.veh_cat::text = '"+veh_type+"' AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  								"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  								"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  								"GROUP BY 4";
  					}
  					
  				}
  				if(roleSubAccess.equals("Corps")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
  					
  					if(veh_type == "A" || veh_type == "B") {
  						qry ="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(case when right(prf_ue_uh.form_code,3)='000' \r\n" + 
  							"	 	then (select distinct coprs_name from orbat_view_corps where substr(prf_ue_uh.form_code,2,2) =  corps_code) \r\n" + 
  							"	 	else (select distinct div_name from orbat_view_div where substr(prf_ue_uh.form_code,4,3) = div_code ) end)  as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.ba_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	substr(form_code_control,1,6) as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"'  "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ?\r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4";
  					}
  					if(veh_type == "C") {
  						qry ="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(case when right(prf_ue_uh.form_code,3)='000' \r\n" + 
  							"	 	then (select distinct coprs_name from orbat_view_corps where substr(prf_ue_uh.form_code,2,2) =  corps_code) \r\n" + 
  							"	 	else (select distinct div_name from orbat_view_div where substr(prf_ue_uh.form_code,4,3) = div_code ) end)  as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.em_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	substr(form_code_control,1,6) as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.em_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"'  "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ?\r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4";
  	  				}
  					
  				}
  				if(roleSubAccess.equals("Division")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
  					
  					if(veh_type == "A" || veh_type == "B") {
  						qry = "select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(case when right(prf_ue_uh.form_code,3)='000' \r\n" + 
  							"	 	then (select distinct div_name from orbat_view_div where substr(prf_ue_uh.form_code,4,3) = div_code) \r\n" + 
  							"	 	else (select distinct bde_name from orbat_view_bde where substr(prf_ue_uh.form_code,7,4) = bde_code) end) as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.ba_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	form_code_control as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"'  "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ? \r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4";
  					}
  					if(veh_type == "C") {
  						qry = "select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(case when right(prf_ue_uh.form_code,3)='000' \r\n" + 
  							"	 	then (select distinct div_name from orbat_view_div where substr(prf_ue_uh.form_code,4,3) = div_code) \r\n" + 
  							"	 	else (select distinct bde_name from orbat_view_bde where substr(prf_ue_uh.form_code,7,4) = bde_code) end) as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.em_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	form_code_control as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.em_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"'  "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ? \r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4";
  					}
  				}
  				if(roleSubAccess.equals("Brigade")) {
  					whr=roleFormationNo;
  					
  					if(veh_type == "A" || veh_type == "B") {
  						qry ="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(select distinct bde_name from orbat_view_bde where substr(prf_ue_uh.form_code,7,4) = bde_code)  as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.ba_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	form_code_control as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.ba_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ? \r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4" ;
  					}
  					if(veh_type == "C") {
  						qry ="select sum(prf_ue_uh.ue) as ue,sum(prf_ue_uh.uh) as uh,\r\n" + 
  							"	(select distinct bde_name from orbat_view_bde where substr(prf_ue_uh.form_code,7,4) = bde_code)  as colname,\r\n" + 
  							"	prf_ue_uh.form_code as colcode\r\n" + 
  							"from\r\n" + 
  							"(SELECT --m.prf_group,\r\n" + 
  							"    count(pd.em_no) AS uh,\r\n" + 
  							"    ue.total as ue,\r\n" + 
  							"	form_code_control as form_code\r\n" + 
  							"   FROM tb_tms_banum_dirctry d\r\n" + 
  							"     JOIN $tableName pd ON d.ba_no::text = pd.em_no::text\r\n" + 
  							"     JOIN tb_tms_mct_master m ON d.mct = m.mct AND m.status::text = 'ACTIVE'::text AND m.type_of_vehicle::text = '"+veh_type+"' "+prfWhr+" \r\n" + 
  							"     LEFT JOIN cue_transport_ue_final ue ON substr(m.mct::character varying::text, 1, 4) = ue.mct_no::text AND pd.sus_no::text = ue.sus_no::text\r\n" + 
  							"     JOIN tb_miso_orbat_unt_dtl u ON u.status_sus_no::text = 'Active'::text AND pd.sus_no::text = u.sus_no::text \r\n" + 
  							"	 	and u.form_code_control like ? \r\n" + 
  							"  WHERE d.veh_cat::text = '"+veh_type+"'::text AND d.status::text = 'Active'::text AND d.ba_no IS NOT NULL\r\n" + 
  							"  GROUP BY pd.sus_no, (substr(m.mct::character varying::text, 1, 4)), m.prf_group, ue.total, u.form_code_control\r\n" + 
  							"  ORDER BY pd.sus_no) as prf_ue_uh\r\n" + 
  							"GROUP BY 4" ;
  					}
  				}
  			} else {
  				return null;
  			}
  			
			
			
  			qry =qry.replace("$tableName",tbl);
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			int flag = 1;
  			
  			
  			if(roleAccess.equals("HeadQuarter")) {
  				if(!prf_code.equals("0")){
  	  				stmt.setString(flag, prf_code);
  	  			}
  			}else if(roleAccess.equals("Line_dte")) {
  				if(!prf_code.equals("0")){
  	  				stmt.setString(1, prf_code);
  	  				flag = flag+1;
  	  			}
  				stmt.setString(flag, roleSusNo);
  			}
  			else if(roleAccess.equals("Formation")) {
  				if(roleSubAccess.equals("Command")) {
  					if(veh_type == "A" || veh_type == "B"){
  						if(!prf_code.equals("0")){
  		  	  				stmt.setString(flag, prf_code);
  		  	  				flag = flag+1;
  		  	  			}
  						stmt.setString(flag, whr+"%");
  						flag = flag+1;
  						if(!prf_code.equals("0")){
  		  	  				stmt.setString(flag, prf_code);
  		  	  				flag = flag+1;
  		  	  			}
  						stmt.setString(flag, whr+"%");
  					}
  					if(veh_type == "C") {
  						if(!prf_code.equals("0")){
  		  	  				stmt.setString(flag, prf_code);
  		  	  				flag = flag+1;
  		  	  			}
  						stmt.setString(flag, whr+"%");
  					}
  				}else {
  					if(!prf_code.equals("0")){
		  	  			stmt.setString(flag, prf_code);
		  	  			flag = flag+1;
		  	  		}
					stmt.setString(flag, whr+"%");
  				}
  			}
  			
  			
  			
  			
  			
  			
  			
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
	
	public List<Map<String, Object>> getVehWiseAvgKMSData(int type,String form_code,String prf_code,HttpSession sessionA)
	{
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String tbl="",qry="";
  			if(type == 0)
				tbl="tms_a_veh_all_details";
			if(type == 1)
				tbl="tms_b_veh_all_details";
			if(type == 2)
				tbl="tms_c_veh_all_details";
  			
			if(form_code !="" && prf_code != "")
  			{
				String whr ="";
				if(roleAccess.equals("Line_dte")) {
					whr =" inner join tb_miso_orbat_unt_dtl u on b.unit_sus=u.sus_no and u.status_sus_no='Active' and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus=? ) \r\n";
				}
  				qry="select round((sum(kms_run)/count(kms_run)),0) as avg ,sum(kms_run) as total,count(kms_run) as count from $tableName b\r\n" + 
					" inner join cue_transport_ue_final t1 on b.mct_main=t1.mct_no and b.unit_sus=t1.sus_no\r\n" +
  					whr +
  					" WHERE b.fco like ? and b.prf_code=?";
  			  	
	  			qry =qry.replace("$tableName",tbl);
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			int flag = 1;
	  			if(roleAccess.equals("Line_dte")) {
	  				stmt.setString(flag, roleSusNo);
  				}
	  			flag = flag +1;
	  			stmt.setString(flag,form_code+"%");
	  			flag = flag +1;
	  			stmt.setString(flag,prf_code);
	  			
	  			
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
	  		}
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
	
	///////////////////////////////
	public List<Map<String, Object>> getCommWiseUnitMovFormation(String roleFormationNo,Date fromDate,Date toDate,HttpSession sessionA)
    {
        String whr ="";
        String roleAccess = sessionA.getAttribute("roleAccess").toString();
        String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
        String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
        
        if(roleAccess.equals("Formation")) {
            if(roleSubAccess.equals("Command")) {
                whr =" and (substr(cur_ab.cmd_code,1,1)=substr(?,1,1) or substr(a.imdt_fmn ,1,1)=substr(?,1,1)) ";
            }
        }else if(roleAccess.equals("HeadQuarter")){
            whr ="";
        }else if(roleAccess.equals("Line_dte")){
            whr =" and cur.arm_code   in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus=? ) \r\n";
        }else {
            return null;
        }
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Connection conn = null;
            try{       
                conn = dataSource.getConnection();        
                String qry = "select distinct cur_ab.cmd_name as cmd_name,substr(cur.form_code_control,1,1) as cmd_code,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '1'), 0::numeric) as count1,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '2'), 0::numeric) as count2,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '3'), 0::numeric) as count3,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '4'), 0::numeric) as count4,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '5'), 0::numeric) as count5,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '6'), 0::numeric) as count6,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '7'), 0::numeric) as count7,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '8'), 0::numeric) as count8,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '9'), 0::numeric) as count9,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '0'), 0::numeric) as count10,   \r\n" + 
                        "       COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = 'A'), 0::numeric) as count11   \r\n" + 
                        "       from tb_miso_orbat_relief_prgm a\r\n" + 
                        "    JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text \r\n" + 
                        "    LEFT JOIN orbat_view_cmd cur_ab ON substr(a.imdt_fmn::text, 1, 1) = cur_ab.cmd_code::text\r\n" + 
                        "    WHERE sd_status='1'     and (a.miso_status::text = '0'::text OR a.miso_status::text = ''::text OR a.miso_status IS NULL)\r\n" + 
                        "    and cast(a.nmb_date as Date) between cast(? as Date) and cast(? as date) "+whr+"\r\n" + 
                        "       GROUP BY 1,2 ORDER BY 2";
                
                PreparedStatement stmt=conn.prepareStatement(qry);
                int flag = 0;
                
                flag += 1;
                stmt.setDate(flag, fromDate);
                flag += 1;
                stmt.setDate(flag, toDate);
                if(roleAccess.equals("Formation")) {
                    if(roleSubAccess.equals("Command")) {
                        flag += 1;
                        stmt.setString(flag, roleFormationNo);
                        flag += 1;
                        stmt.setString(flag, roleFormationNo);
                    }
                }
                if(roleAccess.equals("Line_dte")) {
                    flag += 1;
                    stmt.setString(flag, roleSusNo);
            }
                
          
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
	
	public List<Map<String, Object>> getUnitMovReport(String roleFormationNo, int startPage,String pageLength,String Search,String orderColunm,String orderType, HttpSession sessionA, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div,
			String to_cont_bde, String fromDate, String toDate)
	{
		 String SearchValue = GenerateQueryWhere_SQL( roleFormationNo,  Search, cont_comd,  cont_corps,
					 cont_div,  cont_bde,  to_cont_comd,  to_cont_corps,  to_cont_div,
					 to_cont_bde,  fromDate,  toDate);
		String whr ="";
		String whr1 ="";
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		/*
		 * if(roleAccess.equals("Formation")) { whr
		 * =" and ( a.from_formation like ? or a.to_formation like ? ) "; }else
		 */ if(roleAccess.equals("HeadQuarter")){
			whr ="";
		}else if(roleAccess.equals("Line_dte")){
			whr1 =" inner JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text and cur.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus=? )  \r\n";
		}else {
			
		
			whr1 ="";
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();		
  			String qry="";
  			qry="select distinct a.sus_no, a.unit_name, a.frm_cmd_name, a.frm_coprs_name, a.frm_div_name,a.frm_bde_name,a.to_cmd_name,a.to_coprs_name,\r\n"
  					+ "a.to_div_name,a.to_bde_name,to_char(a.nmb_date,'dd-mm-yyyy') as nmb_date,cur.ct_part_i_ii,d.arm_desc, x.label AS type_of_force from view_relief_report a \r\n"
  					+ "JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text\r\n"
  					+ "	 LEFT JOIN tb_miso_orbat_arm_code d ON cur.arm_code::text = d.arm_code::text\r\n"
  					+ "	 LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = cur.type_force::text\r\n"
  					+ whr1
  					+ " WHERE a.sd_status='1' "+whr+SearchValue+" order by nmb_date " ;
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			 stmt = setQueryWhereClause_SQL1(stmt,roleFormationNo,  Search, cont_comd,  cont_corps,
					 cont_div,  cont_bde,  to_cont_comd,  to_cont_corps,  to_cont_div,
					 to_cont_bde,  fromDate,  toDate);
  			int flag = 0;
  			if(roleAccess.equals("Line_dte")) {
  				flag += 1;
  				stmt.setString(flag, roleSusNo);
			}
  			
			/*
			 * if(roleAccess.equals("Formation")) { flag += 1; stmt.setString(flag,
			 * roleFormationNo+"%"); flag += 1; stmt.setString(flag, roleFormationNo+"%"); }
			 */
  			
  			System.err.println("interrrrrrrrr: " + stmt);
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
	
	
	public List<Map<String, Object>> getUnitMovReportcount(String roleFormationNo,String Search,
			 HttpSession sessionA, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div,
			String to_cont_bde, String fromDate, String toDate)
	{
		 String SearchValue = GenerateQueryWhere_SQL( roleFormationNo,  Search, cont_comd,  cont_corps,
					 cont_div,  cont_bde,  to_cont_comd,  to_cont_corps,  to_cont_div,
					 to_cont_bde,  fromDate,  toDate);
		String whr ="";
		String whr1 ="";
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		/*
		 * if(roleAccess.equals("Formation")) { whr
		 * =" and ( a.from_formation like ? or a.to_formation like ? ) "; }else
		 */if(roleAccess.equals("HeadQuarter")){
			whr ="";
		}else if(roleAccess.equals("Line_dte")){
			whr1 =" inner JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text and cur.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus=? )  \r\n";
		}else {
			
		
			whr1 ="";
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();		
  			String qry="";
  			qry="select count (app.*) as count from(select distinct a.sus_no, a.unit_name, a.frm_cmd_name, a.frm_coprs_name, a.frm_div_name,a.frm_bde_name,a.to_cmd_name,a.to_coprs_name,\r\n"
  					+ "a.to_div_name,a.to_bde_name,to_char(a.nmb_date,'dd-mm-yyyy') as nmb_date,cur.ct_part_i_ii,d.arm_desc, x.label AS type_of_force from view_relief_report a \r\n"
  					+ "JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text\r\n"
  					+ "	 LEFT JOIN tb_miso_orbat_arm_code d ON cur.arm_code::text = d.arm_code::text\r\n"
  					+ "	 LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = cur.type_force::text\r\n"
  					+ whr1
  					+ " WHERE a.sd_status='1' "+whr+SearchValue+" order by nmb_date ) app ";
  					
  					
  					
//  					"select count (app.*) as count from(select distinct a.sus_no, a.unit_name, a.frm_cmd_name, a.frm_coprs_name, a.frm_div_name,a.frm_bde_name,a.to_cmd_name,a.to_coprs_name,\r\n"
//  					+ "a.to_div_name,a.to_bde_name,to_char(a.nmb_date,'dd-mm-yyyy') as nmb_date from view_relief_report a \r\n"
//  					+ whr1
//  					+ " WHERE a.sd_status='1' "+whr+SearchValue+" order by nmb_date ) app ";
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			 stmt = setQueryWhereClause_SQL1(stmt,roleFormationNo,  Search, cont_comd,  cont_corps,
					 cont_div,  cont_bde,  to_cont_comd,  to_cont_corps,  to_cont_div,
					 to_cont_bde,  fromDate,  toDate);
  			int flag = 0;
  			if(roleAccess.equals("Line_dte")) {
  				flag += 1;
  				stmt.setString(flag, roleSusNo);
			}
  			
			/*
			 * if(roleAccess.equals("Formation")) { flag += 1; stmt.setString(flag,
			 * roleFormationNo+"%"); flag += 1; stmt.setString(flag, roleFormationNo+"%"); }
			 */
  			
  			System.err.println("interr----count:  \n"+ stmt);
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
	public String GenerateQueryWhere_SQL(String roleFormationNo,String  Search,String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div,
			String to_cont_bde, String fromDate, String toDate) {
		String SearchValue ="";
		
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and  ";
			
			SearchValue +="( upper(a.sus_no) like ? or upper(a.unit_name) like ? or upper(a.frm_cmd_name) like ? or upper(a.frm_coprs_name) like ? "
					+ "or upper(a.frm_div_name) like ? or upper( a.frm_bde_name) like ? or upper(a.to_cmd_name) like ? "
					+ " or upper(a.to_coprs_name) like ? or upper(a.to_div_name) like ?  or upper(a.to_bde_name) like ?"
					+ "or upper(ltrim(TO_CHAR(a.nmb_date,'DD-MON-YYYY'),'0')) like ? or upper(x.label) like ? or upper(d.arm_desc) like ?) ";
		}
		
		if( !cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and a.frm_cmd_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Command') \r\n"
						+ "											and SUBSTR(form_code_control,1,1) like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += "and a.frm_cmd_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Command') \r\n"
						+ "											and SUBSTR(form_code_control,1,1) like ? and status_sus_no='Active') ";
			}
		}
		if( !cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.frm_coprs_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Corps') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += " and a.frm_coprs_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Corps') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		if( !cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.frm_div_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Division') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += " and a.frm_div_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Division') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		if( !cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.frm_bde_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Brigade') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
			else {
				SearchValue += " and a.frm_bde_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Brigade') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		
		if( !to_cont_comd.equals("")) {
			if (SearchValue.contains("and")) {
				SearchValue += " and a.to_cmd_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Command') \r\n"
						+ "											and SUBSTR(form_code_control,1,1) like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += "and a.to_cmd_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Command') \r\n"
						+ "											and SUBSTR(form_code_control,1,1) like ? and status_sus_no='Active') ";
			}
		}
		if( !to_cont_corps.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.to_coprs_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Corps') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += " and a.to_coprs_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Corps') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		if( !to_cont_div.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.to_div_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Division') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";	
			}
			else {
				SearchValue += " and a.to_div_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Division') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		if( !to_cont_bde.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and a.to_bde_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Brigade') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
			else {
				SearchValue += " and a.to_bde_name in (select unit_name from tb_miso_orbat_unt_dtl \r\n"
						+ "	where sus_no in(select sus_no from tb_miso_orbat_codesform where level_in_hierarchy='Brigade') \r\n"
						+ "											and form_code_control like ? and status_sus_no='Active')";
			}
		}
		
//		if(!fromDate.equals("") && !toDate.equals("") && !fromDate.equals("DD/MM/YYYY") && !toDate.equals("DD/MM/YYYY")){ 
//			  if (SearchValue.contains("where")) {
//		  SearchValue +=  " and cast(a.nmb_date as date) >= cast(? as date) and cast(a.nmb_date as date) <= cast(? as date)"; 
//		  } 
//			else { SearchValue += " and cast(a.nmb_date as date) >= cast(? as date) and cast(a.nmb_date as date) <= cast(? as date)"; 
//			}
//		}
		
		if(!fromDate.equals("") && !fromDate.equals("0") && !fromDate.equals("DD/MM/YYYY")) {
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and a.nmb_date >= to_date(?,'dd/mm/yyyy')"; 
		  } 
			else { SearchValue += "and a.nmb_date >= to_date(?,'dd/mm/yyyy')"; 
			}
		}
		
		if(!toDate.equals("") && !toDate.equals("0") && !toDate.equals("DD/MM/YYYY")) {
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and a.nmb_date <= to_date(?,'dd/mm/yyyy')"; 
		  } 
			else { SearchValue += " and a.nmb_date <= to_date(?,'dd/mm/yyyy')"; 
			}
		}
		
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String roleFormationNo,String Search,String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String to_cont_comd, String to_cont_corps, String to_cont_div,
			String to_cont_bde, String fromDate, String toDate) {
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
			if(!to_cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, to_cont_comd.toUpperCase()+"%");
				
			}
			if(!to_cont_corps.equals("0")) {
				flag += 1;
				stmt.setString(flag, to_cont_corps.toUpperCase()+"%");
				
			}
			if(!to_cont_div.equals("0")) {
				flag += 1;
				stmt.setString(flag, to_cont_div.toUpperCase()+"%");
				
			}
			if(!to_cont_bde.equals("0")) {
				flag += 1;
				stmt.setString(flag, to_cont_bde.toUpperCase()+"%");
				
			}
			if(!fromDate.equals("") && !fromDate.equals("0") && !fromDate.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, fromDate);
				
			}
			if(!toDate.equals("") && !toDate.equals("0") && !toDate.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, toDate);
				
			}
//			if(!fromDate.equals("") && !toDate.equals("") && !fromDate.equals("DD/MM/YYYY")  && !toDate.equals("DD/MM/YYYY")) { 
//				  flag += 1; 	
//				  stmt.setString(flag , fromDate);
//				   
//				  flag += 1;	
//	              stmt.setString(flag, toDate);
//			}
		}catch (Exception e) {}
		
		return stmt;
	}
	public List<Map<String, Object>> getTptSummaryData(String roleAccess,String roleSubAccess,String roleFormationNo,int type)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String whr="",qry="",tbl="";
  			
  			if(type == 0)
				tbl="tms_aveh_command_wise_transport_ue_uh_view";
			if(type == 1)
				tbl="tms_bveh_command_wise_transport_ue_uh_view";
			if(type == 2)
				tbl="tms_cveh_command_wise_transport_ue_uh_view";
  			
  			if(roleAccess.equals("Formation")) {
  				if(roleSubAccess.equals("Command")) {
  					whr=String.valueOf(roleFormationNo.charAt(0));
  					
  					qry="select COALESCE(t.ue, 0::numeric) as ue,COALESCE(t.uh, 0::numeric) as uh,t.form_code as colcode,"
  						+ "(case when right(t.form_code,2)='00' then (select distinct cmd_name from orbat_view_cmd where cmd_code = substr(t.form_code,1,1)) else (select distinct coprs_name from orbat_view_corps where corps_code = right(t.form_code,2)) end) as colname from \r\n" + 
  						" (select sum(ue) as ue,sum(uh) as uh,substr(form_code_control,1,3) as form_code\r\n" + 
  						" from $tableName \r\n" + 
  						" WHERE substr(form_code_control,1,1) =? GROUP BY substr(form_code_control,1,3)) t ORDER BY form_code";
  				}
  				if(roleSubAccess.equals("Corps")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
  					
  					qry="select COALESCE(t.ue, 0::numeric) as ue,COALESCE(t.uh, 0::numeric) as uh,t.form_code as colcode,"
  						+ "(case when right(t.form_code,2)='00' then (select distinct coprs_name from orbat_view_corps where corps_code = substr(t.form_code,2,2)) else (select distinct div_name from orbat_view_div where div_code = substr(t.form_code,4,3)) end) as colname from \r\n" + 
  						" (select sum(ue) as ue,sum(uh) as uh,substr(form_code_control,1,6) as form_code\r\n" + 
  						" from $tableName \r\n" + 
  						" WHERE substr(form_code_control,1,3) =? GROUP BY substr(form_code_control,1,6)) t ORDER BY form_code";
  				}
  				if(roleSubAccess.equals("Division")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
  					
  					qry="select COALESCE(t.ue, 0::numeric) as ue,COALESCE(t.uh, 0::numeric) as uh,t.form_code as colcode,"
						+ "(case when right(t.form_code,2)='00' then (select distinct div_name from orbat_view_div where div_code = substr(t.form_code,4,3)) else (select distinct bde_name from orbat_view_bde where bde_code = substr(t.form_code,7,4)) end) as colname from \r\n" + 
  						" (select sum(ue) as ue,sum(uh) as uh,form_code_control as form_code\r\n" + 
  						" from $tableName \r\n" + 
  						" WHERE substr(form_code_control,1,6) =? GROUP BY form_code_control) t ORDER BY form_code";
  				}
  				if(roleSubAccess.equals("Brigade")) {  	
  					whr=roleFormationNo;
  					qry ="select COALESCE(ue, 0::numeric) as ue,COALESCE(uh, 0::numeric) as uh, form_code_control as colcode ,unit_name as colname " + 
  						" from $tableName" +  
  						" WHERE  form_code_control = ? ORDER BY form_code_control";
  				}
  			}
 			qry =qry.replace("$tableName",tbl);
 			PreparedStatement stmt=conn.prepareStatement(qry);
  			stmt.setString(1, whr);
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
	
	public List<Map<String, Object>> getTptSummaryInPRFList_nodal_dte(int type,HttpSession session,String line_dte_sus){		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{
  			conn = dataSource.getConnection();	
  			String qry="",whr ="",veh_type = "";
			if(type == 0) {veh_type ="A";}
			if(type == 1) {veh_type ="B";}
			if(type == 2) {	veh_type ="C";}
			if(!line_dte_sus.equals("")) {
				whr = " inner join tb_tms_mct_main_master main on s.prf_code = main.prf_code and main.sus_no= ? ";
			}
			qry="select distinct s.prf_code,s.group_name from tb_tms_mct_slot_master s\r\n" + 
					"inner join tb_tms_mct_master m on s.prf_code=m.prf_group and m.status = 'ACTIVE' and m.type_of_vehicle::text = ? \r\n" +
						whr +
					"order by  s.group_name";
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setString(1, veh_type);
			if(!line_dte_sus.equals("")) {
				stmt.setString(2, line_dte_sus);
			}
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
	
	public List<Map<String, Object>> getTptSummaryInPRFList(int type,HttpSession session)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="",veh_type = "";
  			if(type == 0) {veh_type ="A";}
			if(type == 1) {veh_type ="B";}
			if(type == 2) {	veh_type ="C";}
			qry="select distinct s.prf_code,s.group_name from tb_tms_mct_slot_master s\r\n" + 
					"inner join tb_tms_mct_master m on s.prf_code=m.prf_group and m.status = 'ACTIVE' and m.type_of_vehicle::text = ? \r\n" + 
					"order by  s.group_name";
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setString(1, veh_type);
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
	
	public List<Map<String, Object>> getPRFWiseTptClassData(HttpSession sessionA,int type,String prf)
  	{
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String whr="",qry="",tbl="",whrprf="";
  			if(type == 0) {
				tbl="tms_a_veh_all_details";
  			}
			if(type == 1) {
				tbl="tms_b_veh_all_details";
			}
			if(type == 2) {
				tbl="tms_c_veh_all_details";
			}
			
			if(!prf.equals("0")) {
  				whrprf = " and (cast(b.prf_code as Integer) = ? or cast(b.prf_code as Integer) in ('800','5','101','50','10'))";
  			}else {
  				whrprf = " and (cast(b.prf_code as Integer) in ('800','5','101','50','10'))";
  			}
			
  			if(roleAccess.equals("Formation")) {
  				if(roleSubAccess.equals("Command")) {
  					whr=String.valueOf(roleFormationNo.charAt(0));
  					qry="select a.prf_code,\r\n" + 
  	  						"   ms.group_name,\r\n" + 
  	  						"   a.cl1,\r\n" + 
  	  						"   a.cl2,\r\n" + 
  	  						"   a.cl3,\r\n" + 
  	  						"   a.cl4 from\r\n" + 
  	  						"(select b.prf_code,\r\n" + 
  	  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  	  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  	  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  	  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  	  						" from $tableName b \r\n" + 
  	  						" where b.ud='UNIT' and substr(b.fco,1,1) =? "+whrprf+" \r\n" + 
  	  						" Group by 1\r\n" + 
  	  						" order by 1 limit 10) as a\r\n" + 
  	  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  	  						"Group by 1,2,3,4,5,6 \r\n"
  	  						+ "order by  a.cl1 desc";
  				}
  				if(roleSubAccess.equals("Corps")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
  					qry="select a.prf_code,\r\n" + 
  	  						"   ms.group_name,\r\n" + 
  	  						"   a.cl1,\r\n" + 
  	  						"   a.cl2,\r\n" + 
  	  						"   a.cl3,\r\n" + 
  	  						"   a.cl4 from\r\n" + 
  	  						"(select b.prf_code,\r\n" + 
  	  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  	  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  	  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  	  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  	  						" from $tableName b \r\n" + 
  	  						" where b.ud='UNIT' and substr(b.fco,1,3) =? "+whrprf+" \r\n" + 
  	  						" Group by 1\r\n" + 
  	  						" order by 1 limit 10) as a\r\n" + 
  	  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  	  						"Group by 1,2,3,4,5,6 \r\n"
  	  						+ "order by  a.cl1 desc";
  				}
  				if(roleSubAccess.equals("Division")) {
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
  					qry="select a.prf_code,\r\n" + 
  	  						"   ms.group_name,\r\n" + 
  	  						"   a.cl1,\r\n" + 
  	  						"   a.cl2,\r\n" + 
  	  						"   a.cl3,\r\n" + 
  	  						"   a.cl4 from\r\n" + 
  	  						"(select b.prf_code,\r\n" + 
  	  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  	  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  	  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  	  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  	  						" from $tableName b \r\n" + 
  	  						" where b.ud='UNIT' and substr(b.fco,1,6) =? "+whrprf+" \r\n" + 
  	  						" Group by 1\r\n" + 
  	  						" order by 1 limit 10) as a\r\n" + 
  	  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  	  						"Group by 1,2,3,4,5,6 \r\n"
  	  						+ "order by  a.cl1 desc";
  				}
  				if(roleSubAccess.equals("Brigade")) {
  					whr=roleFormationNo;
  					qry="select a.prf_code,\r\n" + 
  	  						"   ms.group_name,\r\n" + 
  	  						"   a.cl1,\r\n" + 
  	  						"   a.cl2,\r\n" + 
  	  						"   a.cl3,\r\n" + 
  	  						"   a.cl4 from\r\n" + 
  	  						"(select b.prf_code,\r\n" + 
  	  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  	  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  	  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  	  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  	  						" from $tableName b \r\n" + 
  	  						" where b.ud='UNIT' and  b.fco = ?  "+whrprf+" \r\n" + 
  	  						" Group by 1\r\n" + 
  	  						" order by 1 limit 10) as a\r\n" + 
  	  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  	  						"Group by 1,2,3,4,5,6 \r\n"
  	  						+ "order by  a.cl1 desc";
  				}
  			}else if(roleAccess.equals("HeadQuarter")){
  				whr = "";
  				qry="select a.prf_code,\r\n" + 
  						"   ms.group_name,\r\n" + 
  						"   a.cl1,\r\n" + 
  						"   a.cl2,\r\n" + 
  						"   a.cl3,\r\n" + 
  						"   a.cl4 from\r\n" + 
  						"(select b.prf_code,\r\n" + 
  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  						" from $tableName b \r\n" + 
  						" where b.ud='UNIT' and  substr(b.fco,1,1) like '%'  "+whrprf+" \r\n" + 
  						" Group by 1\r\n" + 
  						" order by 1 limit 10) as a\r\n" + 
  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  						"Group by 1,2,3,4,5,6 \r\n"
  						+ "order by  a.cl1 desc";
  			}else if(roleAccess.equals("Line_dte")){
  				whr = "";
  				qry="select a.prf_code,\r\n" + 
  						"   ms.group_name,\r\n" + 
  						"   a.cl1,\r\n" + 
  						"   a.cl2,\r\n" + 
  						"   a.cl3,\r\n" + 
  						"   a.cl4 from\r\n" + 
  						"(select b.prf_code,\r\n" + 
  						"   count(case when b.classification='1' then b.prf_code end) as cl1,\r\n" + 
  						"   count(case when b.classification='2' then b.prf_code end) as cl2,\r\n" + 
  						"   count(case when b.classification='3' then b.prf_code end) as cl3,\r\n" + 
  						"   count(case when b.classification='4' then b.prf_code end) as cl4\r\n" + 
  						" from $tableName b \r\n" + 
  						" inner join tb_miso_orbat_unt_dtl u on b.unit_sus=u.sus_no and u.status_sus_no='Active' \r\n" + 
  						" 	and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) \r\n" +
  						" where b.ud='UNIT' and  substr(b.fco,1,1) like '%'  "+whrprf+" \r\n" + 
  						" Group by 1\r\n" + 
  						" order by 1 limit 10) as a\r\n" + 
  						"inner JOIN tb_tms_mct_slot_master ms ON a.prf_code = ms.prf_code  \r\n" + 
  						"Group by 1,2,3,4,5,6 \r\n"
  						+ "order by  a.cl1 desc";
  			} else {
  				
  			}
  			
  			qry =qry.replace("$tableName",tbl);
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			int flag = 0;
  			if(roleAccess.equals("Formation")){
  				flag += 1;
  				stmt.setString(flag, whr);
  			}
  			if(roleAccess.equals("Line_dte")){
  				flag += 1;
  				stmt.setString(flag, roleSusNo);
  			}
  			if(!prf.equals("0")){
  				flag += 1;
  				stmt.setInt(flag, Integer.parseInt(prf));
  			}
  			
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
		
	public List<Map<String, Object>> getUEManpowerData(HttpSession sessionA)
	{
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{
  			conn = dataSource.getConnection();
  			String whr="",qry="";
  			if(roleAccess.equals("Formation")) {
  				if(roleSubAccess.equals("Command")) {
  					whr=String.valueOf(roleFormationNo.charAt(0));
  					qry = "(select distinct t.officer,t.jco,t.or,t.form_code,(select distinct unit_name from all_fmn_view where form_code_control = concat(t.form_code,'0000000')) as colname from \r\n" + 
  							"	  					(select COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer,\r\n" + 
  							"	  					COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco,\r\n" + 
  							"	  					COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
  							"	  					substr(u.form_code_control,1,3) as form_code\r\n" + 
  							"	  					from sus_pers_stdauth ue left join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active'  \r\n" + 
  							"	  					 WHERE substr(u.form_code_control,1,1) =? \r\n" + 
  							"	  					 group by substr(u.form_code_control,1,3)) t ORDER BY form_code)\r\n" + 
  							"										 \r\n" + 
  							"										 \r\n" + 
  							"										 union all\r\n" + 
  							"										 \r\n" + 
  							"				(select distinct t.officer,t.jco,t.or,t.form_code, (select distinct unit_name from all_fmn_view  where \r\n" + 
  							"						form_code_control = concat(t.form_code1,t.form_code,'0000')) as colname from \r\n" + 
  							"	  					(select COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer,\r\n" + 
  							"	  					COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco,\r\n" + 
  							"	  					COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
  							"	  					 substr(u.form_code_control,1,1) as form_code1,\r\n" + 
  							"						 --substr(u.form_code_control,2,2) as form_code1,\r\n" + 
  							"						 substr(u.form_code_control,2,5) as form_code\r\n" + 
  							"						 from sus_pers_stdauth ue left join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active'  \r\n" + 
  							"	  					 WHERE substr(u.form_code_control,1,1) =? \r\n" + 
  							"	  					 group by substr(u.form_code_control,2,5),substr(u.form_code_control,1,1)) t where t.form_code like '00%' and t.form_code not like '00000'\r\n" + 
  							"	ORDER BY form_code)";
  					
  				
  					
  				}
  				if(roleSubAccess.equals("Corps")) {
  					whr = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
  					qry = "	select \r\n"+
  							"		t.officer,\r\n"+ 
  							"		t.jco,\r\n"+ 
  							"		t.or,\r\n"+ 
  							"		t.form_code,\r\n"+
  							"		(select distinct unit_name from all_fmn_view where form_code_control = concat(t.form_code,'0000')) as colname \r\n"+
  							"	from \r\n"+
  							"	(select \r\n"+
  							"		COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer, \r\n" + 
  							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco, \r\n" + 
  							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
  							"		substr(u.form_code_control,1,6) as form_code\r\n" + 
  							"	from sus_pers_stdauth ue \r\n" + 
  							"	left join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active' \r\n" + 
  							"	WHERE substr(u.form_code_control,1,3) = ? \r\n" + 
  							"		 group by substr(u.form_code_control,1,6)) t ORDER BY form_code";
  				}
  				
				if(roleSubAccess.equals("Division")) {
					whr = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					qry = "	select \r\n" + 
							"		t.officer,\r\n" + 
							"		t.jco,\r\n" + 
							"		t.or,\r\n" + 
							"		t.form_code,\r\n" + 
							"		(select distinct unit_name from all_fmn_view where form_code_control = concat(t.form_code,'')) as colname \r\n" + 
							"	from \r\n" + 
							"	(select \r\n" + 
							"		COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer, \r\n" + 
							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco, \r\n" + 
							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
							"		u.form_code_control as form_code\r\n" + 
							"	from sus_pers_stdauth ue \r\n" + 
							"	left join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active' \r\n" + 
							"	WHERE substr(u.form_code_control,1,6) = ? \r\n" + 
							"		 group by u.form_code_control) t ORDER BY form_code";
				}
				if(roleSubAccess.equals("Brigade")) {
					whr = roleFormationNo;
					qry = "select \r\n" + 
							"		COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer, \r\n" + 
							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco, \r\n" + 
							"	  	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
							"		u.unit_name as colname\r\n" + 
							"	from sus_pers_stdauth ue \r\n" + 
							"	left join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active' \r\n" + 
							"	WHERE u.form_code_control = ? \r\n" + 
							"		 group by u.unit_name";
				}
  			}else if(roleAccess.equals("HeadQuarter")){
  				qry ="select t.officer,t.jco,t.or,t.form_code,short_form as colname from \r\n" + 
  						"	 (select COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer,\r\n" + 
  						"	 COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco, \r\n" + 
  						"	 COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
  						"	 substr(u.form_code_control,1,1) as form_code\r\n" + 
  						"	 from sus_pers_stdauth ue \r\n" + 
  						"	 inner join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active' \r\n" + 
  						"	 group by substr(u.form_code_control,1,1)) t \r\n" + 
  						"	 inner join orbat_view_cmd cmd on t.form_code = cmd.cmd_code\r\n" + 
  						"	 ORDER BY form_code";
  			}else if(roleAccess.equals("Line_dte")){
  				qry ="select t.officer,t.jco,t.or,t.form_code,short_form as colname from \r\n" + 
  						"	 (select COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer,\r\n" + 
  						"	 COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco, \r\n" + 
  						"	 COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or, \r\n" + 
  						"	 substr(u.form_code_control,1,1) as form_code\r\n" + 
  						"	 from sus_pers_stdauth ue \r\n" + 
  						"	 inner join tb_miso_orbat_unt_dtl u on ue.sus_no=u.sus_no and u.status_sus_no='Active' and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) \r\n" + 
  						"	 group by substr(u.form_code_control,1,1)) t \r\n" + 
  						"	 inner join orbat_view_cmd cmd on t.form_code = cmd.cmd_code\r\n" + 
  						"	 ORDER BY form_code";
  				
  			}else {
  				return list;
  			}
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			if(roleAccess.equals("Formation")){
  				if(roleSubAccess.equals("Command")){
  					stmt.setString(1, whr);
  					stmt.setString(2, whr);
  				}else{
  					stmt.setString(1, whr);
  				}
  			}
  			if(roleAccess.equals("Line_dte")){
  				stmt.setString(1, roleSusNo);
  			}
		  		 			
  
  			
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
	
	public List<Map<String, Object>> getHoldingStateData(HttpSession sessionA,String type){
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String whr="",qry="";
  			
  			String priority="";
  			if(type.equals("WET")) {
  				priority =" prf_m.priority_id > 100";
  			}else {
  				priority =" prf_m.priority_id > 0 and prf_m.priority_id < 100";
  			}
  			
  			if(roleAccess.equals("Formation")){
  				if(roleSubAccess.equals("Command")) {
  					whr=String.valueOf(roleFormationNo.charAt(0));
  				}
  				if(roleSubAccess.equals("Corps")){
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
  				}      
  				if(roleSubAccess.equals("Division")){
  					whr=String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
  				}
  				if(roleSubAccess.equals("Brigade")){
  					whr=roleFormationNo;
  				}
  				
  				qry ="select p.prf_code,\r\n" + 
  						"	c.prf_grp_short,\r\n" + 
  						"	coalesce(sum(p.totalue),0) as ue,\r\n" + 
  						"	coalesce(sum(p.totaluh),0) as uh,\r\n" + 
  						"	coalesce(sum(p.totalss),0) as ss,\r\n" + 
  						"	coalesce(sum(p.totalls),0) as ls,\r\n" + 
  						"	coalesce(sum(p.totalac),0) as ac,\r\n" + 
  						"	coalesce(sum(p.totalwwr),0) as res \r\n" + 
  						"from\r\n" + 
  						"	(select \r\n" + 
  						"		(case when a.prf_code  is null then b.prf_code else a.prf_code  end) as prf_code,\r\n" + 
  						"		(case when b.type_of_hldg='A0' then totalue else 0 end) as totalue,\r\n" + 
  						"		b.totaluh,\r\n" + 
  						"		b.totalss,\r\n" + 
  						"		b.totalls,\r\n" + 
  						"		b.totalac,\r\n" + 
  						"		b.totalwwr \r\n" + 
  						"	 from\r\n" + 
  						"		(select mview.prf_group_code as prf_code,sum(mview.total_ue_qty) as totalue \r\n" + 
  						"			from mms_ue_mview mview\r\n" + 
  						"			Inner Join cue_tb_miso_prf_group_mst prf_m on mview.prf_group_code=prf_m.prf_group_code and "+priority+"	\r\n" + 
  						"			WHERE "
  						//+ "			typeofauth= ? and "
  						+ "     	form_code_control like ?  \r\n" + 
  						"			group by mview.prf_group_code) a\r\n" + 
  						"		full outer join \r\n" + 
  						"		(select m.prf_code,type_of_hldg,\r\n" + 
  						"			(case when type_of_hldg='A0' then sum(a.tothol) else 0 end) as totaluh,\r\n" + 
  						"			(case when type_of_hldg='A5' then sum(a.tothol) else 0 end) as totalss,\r\n" + 
  						"			(case when type_of_hldg='A14' then sum(a.tothol) else 0 end) as totalls,\r\n" + 
  						"			(case when type_of_hldg='A16' then sum(a.tothol) else 0 end) as totalac,\r\n" + 
  						"			(case when type_of_hldg like 'R%' and max(d_type) in ('UH','OT') then sum(a.tothol) else 0 end) as totalwwr\r\n" + 
  						"		from mms_tv_regn_mstr a \r\n" + 
  						"		Inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
  						"		Inner Join cue_tb_miso_prf_group_mst prf_m on m.prf_code=prf_m.prf_group_code and "+priority+"\r\n" + 
  						"		where a.form_code_control like ? \r\n" + 
  						"		group by m.prf_code,type_of_hldg) b \r\n" + 
  						"	on a.prf_code = b.prf_code ) p\r\n" + 
  						"	inner join \r\n" + 
  						"	(select prf_m.prf_grp_short,\r\n" + 
  						"	 	prf_m.prf_group_code \r\n" + 
  						"	 from cue_tb_miso_prf_group_mst prf_m \r\n" + 
  						"	 where "+priority+" ) c on p.prf_code=c.prf_group_code \r\n" + 
  						"group by p.prf_code,c.prf_grp_short\r\n" + 
  						"order by p.prf_code";
  				
  			}else if(roleAccess.equals("HeadQuarter")){
  				qry ="select p.prf_code,\r\n" + 
  						"	c.prf_grp_short,\r\n" + 
  						"	coalesce(sum(p.totalue),0) as ue,\r\n" + 
  						"	coalesce(sum(p.totaluh),0) as uh,\r\n" + 
  						"	coalesce(sum(p.totalss),0) as ss,\r\n" + 
  						"	coalesce(sum(p.totalls),0) as ls,\r\n" + 
  						"	coalesce(sum(p.totalac),0) as ac,\r\n" + 
  						"	coalesce(sum(p.totalwwr),0) as res \r\n" + 
  						"from\r\n" + 
  						"	(select \r\n" + 
  						"		(case when a.prf_code  is null then b.prf_code else a.prf_code  end) as prf_code,\r\n" + 
  						"		(case when b.type_of_hldg='A0' then totalue else 0 end) as totalue,\r\n" + 
  						"		b.totaluh,\r\n" + 
  						"		b.totalss,\r\n" + 
  						"		b.totalls,\r\n" + 
  						"		b.totalac,\r\n" + 
  						"		b.totalwwr \r\n" + 
  						"	 from\r\n" + 
  						"		(select mview.prf_group_code as prf_code,sum(mview.total_ue_qty) as totalue \r\n" + 
  						"			from mms_ue_mview mview\r\n" + 
  						"			Inner Join cue_tb_miso_prf_group_mst prf_m on mview.prf_group_code=prf_m.prf_group_code and "+priority+"	\r\n" + 
  						"			group by mview.prf_group_code) a\r\n" + 
  						"		full outer join \r\n" + 
  						"		(select m.prf_code,type_of_hldg,\r\n" + 
  						"			(case when type_of_hldg='A0' then sum(a.tothol) else 0 end) as totaluh,\r\n" + 
  						"			(case when type_of_hldg='A5' then sum(a.tothol) else 0 end) as totalss,\r\n" + 
  						"			(case when type_of_hldg='A14' then sum(a.tothol) else 0 end) as totalls,\r\n" + 
  						"			(case when type_of_hldg='A16' then sum(a.tothol) else 0 end) as totalac,\r\n" + 
  						"			(case when type_of_hldg like 'R%' and max(d_type) in ('UH','OT') then sum(a.tothol) else 0 end) as totalwwr\r\n" + 
  						"		from mms_tv_regn_mstr a \r\n" + 
  						"		Inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
  						"		Inner Join cue_tb_miso_prf_group_mst prf_m on m.prf_code=prf_m.prf_group_code and "+priority+"\r\n" + 
  						"		group by m.prf_code,type_of_hldg) b \r\n" + 
  						"	on a.prf_code = b.prf_code ) p\r\n" + 
  						"	inner join \r\n" + 
  						"	(select prf_m.prf_grp_short,\r\n" + 
  						"	 	prf_m.prf_group_code \r\n" + 
  						"	 from cue_tb_miso_prf_group_mst prf_m \r\n" + 
  						"	 where "+priority+" ) c on p.prf_code=c.prf_group_code \r\n" + 
  						"group by p.prf_code,c.prf_grp_short\r\n" + 
  						"order by p.prf_code";
  			}else if(roleAccess.equals("Line_dte")){
  			
  				qry ="select p.prf_code,\r\n" + 
  						"	c.prf_grp_short,\r\n" + 
  						"	coalesce(sum(p.totalue),0) as ue,\r\n" + 
  						"	coalesce(sum(p.totaluh),0) as uh,\r\n" + 
  						"	coalesce(sum(p.totalss),0) as ss,\r\n" + 
  						"	coalesce(sum(p.totalls),0) as ls,\r\n" + 
  						"	coalesce(sum(p.totalac),0) as ac,\r\n" + 
  						"	coalesce(sum(p.totalwwr),0) as res \r\n" + 
  						"from\r\n" + 
  						"	(select \r\n" + 
  						"		(case when a.prf_code  is null then b.prf_code else a.prf_code  end) as prf_code,\r\n" + 
  						"		(case when b.type_of_hldg='A0' then totalue else 0 end) as totalue,\r\n" + 
  						"		b.totaluh,\r\n" + 
  						"		b.totalss,\r\n" + 
  						"		b.totalls,\r\n" + 
  						"		b.totalac,\r\n" + 
  						"		b.totalwwr \r\n" + 
  						"	 from\r\n" + 
  						"		(select mview.prf_group_code as prf_code,sum(mview.total_ue_qty) as totalue \r\n" + 
  						"			from mms_ue_mview mview\r\n" + 
  						"			Inner Join cue_tb_miso_prf_group_mst prf_m on mview.prf_group_code=prf_m.prf_group_code and "+priority+"	\r\n" + 
  						"			Inner join tb_miso_orbat_unt_dtl u on mview.sus_no=u.sus_no and u.status_sus_no='Active' and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? )" +	
  						"			group by mview.prf_group_code) a\r\n" + 
  						"		full outer join \r\n" + 
  						"		(select m.prf_code,type_of_hldg,\r\n" + 
  						"			(case when type_of_hldg='A0' then sum(a.tothol) else 0 end) as totaluh,\r\n" + 
  						"			(case when type_of_hldg='A5' then sum(a.tothol) else 0 end) as totalss,\r\n" + 
  						"			(case when type_of_hldg='A14' then sum(a.tothol) else 0 end) as totalls,\r\n" + 
  						"			(case when type_of_hldg='A16' then sum(a.tothol) else 0 end) as totalac,\r\n" + 
  						"			(case when type_of_hldg like 'R%' and max(d_type) in ('UH','OT') then sum(a.tothol) else 0 end) as totalwwr\r\n" + 
  						"		from mms_tv_regn_mstr a \r\n" + 
  						"			Inner join tb_miso_orbat_unt_dtl u on a.sus_no=u.sus_no and u.status_sus_no='Active' and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? )" +
  						"			Inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no\r\n" + 
  						"			Inner Join cue_tb_miso_prf_group_mst prf_m on m.prf_code=prf_m.prf_group_code and "+priority+"\r\n" + 
  						"		group by m.prf_code,type_of_hldg) b \r\n" + 
  						"	on a.prf_code = b.prf_code ) p\r\n" + 
  						"	inner join \r\n" + 
  						"	(select prf_m.prf_grp_short,\r\n" + 
  						"	 	prf_m.prf_group_code \r\n" + 
  						"	 from cue_tb_miso_prf_group_mst prf_m \r\n" + 
  						"	 where "+priority+" ) c on p.prf_code=c.prf_group_code \r\n" + 
  						"group by p.prf_code,c.prf_grp_short\r\n" + 
  						"order by p.prf_code";
  			}else {
  				return list;
  			}
  			
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
 			if(roleAccess.equals("Formation")) {
 				stmt.setString(1, whr+"%");
  				stmt.setString(2, whr+"%");
 			}
 			if(roleAccess.equals("Line_dte")){
  				stmt.setString(1, roleSusNo);
  				stmt.setString(2, roleSusNo);
  			}
 			
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
  			 
  			return list;
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
	
	public List<Map<String, Object>> Hospital_Admissions_Col_Above(String form_code)
  	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="";
  			qry ="SELECT \r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission = now()) AS Daily,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 MONTH')) AS Monthly,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 YEAR')) AS Annual\r\n" + 
  				"FROM \r\n" + 
  				"tb_med_daily_hosp_adm_off_vip a\r\n" + 
  				"inner join orbat_all_details_view_mnh b on a.sus_no=b.sus_no and b.form_code_control like ? ";
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			stmt.setString(1, form_code+"%");
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
	
	public List<Map<String, Object>> AMC_ADC_MNS_Admissions(String form_code)
  	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="";
  			qry ="SELECT \r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission = now()) AS Daily,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 MONTH')) AS Monthly,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 YEAR')) AS Annual\r\n" + 
  				"FROM \r\n" + 
  				"tb_med_daily_hosp_adm_amc a\r\n" + 
  				"inner join orbat_all_details_view_mnh b on a.sus_no=b.sus_no and b.form_code_control like ? ";
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			stmt.setString(1, form_code+"%");
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
	
	public List<Map<String, Object>> Unusual_Occurrence(String form_code)
  	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="";
  			qry ="SELECT \r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission = now()) AS Daily,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 MONTH')) AS Monthly,\r\n" + 
  				"	count(a.sus_no) FILTER (WHERE a.date_time_admission > DATE(NOW()-INTERVAL '1 YEAR')) AS Annual\r\n" + 
  				"FROM \r\n" + 
  				"tb_med_unusual_occurrence a\r\n" + 
  				"inner join orbat_all_details_view_mnh b on a.sus_no=b.sus_no and b.form_code_control like ? ";
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			stmt.setString(1, form_code+"%");
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

	@Override
public List<Map<String, Object>> getComputerData(HttpSession sessionA) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;

	String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		try{	  
			conn = dataSource.getConnection();	
			String qry="";
			qry ="select distinct orb.unit_name ,count(am.id) as count,ma.assets_name from tb_asset_main am\r\n"
					+ "				left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1			 \r\n"
					+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active'		\r\n"
					+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
					+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
					+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=?) and am.status=1 \r\n"
					+ "				group by orb.unit_name, ma.assets_name";
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setString(1, roleFormationNo);
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

@Override
public long getUnitReportWpnEqptAuthcount(String search, String orderColunm, String orderType,
		HttpSession sessionUserId, String type_mtrls, String roleFormationNo) {
	int total = 0;
	String q = null;
	Connection conn = null;
	  String searchval="";
  	if(search!=""){
  		searchval ="  and (upper(orb.unit_name) like ?  or upper(ma.assets_name) like ? )  ";
  	}
	try {
		conn = dataSource.getConnection();
	
			q= "select count (app.*) from(     "
					+ " 	select distinct orb.unit_name ,count(am.id) as count2,ma.assets_name from tb_asset_main am\r\n"
					+ "				left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1			 \r\n"
					+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active'		\r\n"
					+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
					+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
					+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=?  "
					
					+ ")"
					+ searchval
					+ "	and am.status=1			group by orb.unit_name, ma.assets_name"
					+ ") app ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setString(1,roleFormationNo );
		int flag=1;
		  if(!search.equals("")) {            
                
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");

		  }


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

@Override
public List<Map<String, Object>> getUnitReportWpnEqptAuth(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleFormationNo) {
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
        String searchval="";
        	if(search!=""){
        		searchval =" and (upper(orb.unit_name) like ?  or upper(ma.assets_name) like ? ) ";
        	}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
				q=    " 	select distinct orb.unit_name ,count(am.id) as count2 ,ma.assets_name from tb_asset_main am\r\n"
						+ "				left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1			 \r\n"
						+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active'		\r\n"
						+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
						+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
						+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
						+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=?     "
					
						+ " )\r\n"
						+searchval
						+ "	and am.status=1			group by orb.unit_name, ma.assets_name ";
			
			stmt=conn.prepareStatement(q);
			stmt.setString(1,roleFormationNo);
			int flag=1;
			  if(!search.equals("")) {            
	                
	                flag += 1;
	                stmt.setString(flag, "%"+search.toUpperCase()+"%");
	                flag += 1;
	                stmt.setString(flag, "%"+search.toUpperCase()+"%");

			  }

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

@Override
public List<Map<String, Object>> getperipheralData(HttpSession sessionA) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
//	String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		try{	  
			conn = dataSource.getConnection();	
			String qry="";
			qry =" select orb.unit_name,count(pt.assets_name),pt.assets_name,th.type_of_hw\r\n"
					+ "				 from it_asset_peripherals ap\r\n"
					+ "				left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "				left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
					+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'\r\n"
					+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
					+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
					+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=? ) and ap.status=1 and  ap.id!=0 \r\n"
					+ "				group by orb.unit_name,pt.assets_name,th.type_of_hw ";
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setString(1, roleFormationNo);
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

@Override
public long getperipheralcount(String search, String orderColunm, String orderType, HttpSession sessionUserId,
		String type_mtrls, String roleFormationNo) {
	int total = 0;
	String q = null;
	Connection conn = null;
	  String searchval="";
  	if(search!=""){
  		searchval =" and (upper(orb.unit_name) like ?  or upper(pt.assets_name) like ?  or upper(th.type_of_hw) like ?     ) ";
  	}
	try {
		conn = dataSource.getConnection();
	
			q= "select count (app.*) from( "
				+"  select orb.unit_name,count(pt.assets_name)as count2,pt.assets_name,th.type_of_hw\r\n"
				+ "				 from it_asset_peripherals ap\r\n"
				+ "				left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
				+ "				left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
				+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'\r\n"
				+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
				+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
				+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
				+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=? ) and ap.status=1 and  ap.id!=0  "
				+ searchval
				+ "				group by orb.unit_name,pt.assets_name,th.type_of_hw   "
					+ ") app ";
		
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setString(1,roleFormationNo );
		int flag=1;
		  if(!search.equals("")) {            
                
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
		  }
	
//		stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
	
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

@Override
public List<Map<String, Object>> getperipheral(int startPage, int pageLength, String search,
		String orderColunm, String orderType, HttpSession sessionUserId, String type_mtrls, String roleFormationNo){

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
    String searchval="";
    	if(search!=""){
    		searchval =" and (upper(orb.unit_name) like ?  or upper(pt.assets_name) like ?  or upper(th.type_of_hw) like ?     ) ";
    	}
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
	
			q=    " 	select orb.unit_name,count(pt.assets_name)as count2,pt.assets_name,th.type_of_hw\r\n"
					+ "				 from it_asset_peripherals ap\r\n"
					+ "				left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "				left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
					+ "				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active'\r\n"
					+ "				where   orb.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
					+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
					+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
					+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=? "
					+ " ) and ap.status=1 and  ap.id!=0 \r\n"
					+searchval 
					+ "				group by orb.unit_name,pt.assets_name,th.type_of_hw"+
			"  limit " +pageL+" OFFSET "+startPage+"";
				
		
		stmt=conn.prepareStatement(q);
		stmt.setString(1,roleFormationNo);
		int flag=1;
		  if(!search.equals("")) {            
                
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
                flag += 1;
                stmt.setString(flag, "%"+search.toUpperCase()+"%");
		  }

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





public  ArrayList<ArrayList<String>> getunitwithmcrobsn(String formation) {
		   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	    		Connection conn = null;
	    		try{	  
	    			
	    			conn = dataSource.getConnection();			 
	    			PreparedStatement stmt=null;
	    			
	    			String sql = " 				select \r\n"
	    					+ "	count(distinct o.sus_no) as totunits,\r\n"
	    					+ "	count(distinct o.sus_no) filter(where obsn.status ='OBSN') as totnres,\r\n"
	    					+ "	count(distinct o.sus_no) - count(distinct o.sus_no) filter(where obsn.status ='OBSN') as totres\r\n"
	    					+ "from tb_miso_orbat_unt_dtl o  \r\n"
	    					+ "inner join sus_weapon_wepe_with_wetpet c on c.sus_no = o.sus_no \r\n"
	    					+ "left join all_fmn_view fmn on o.form_code_control = fmn.form_code_control\r\n"
	    					+ "left join baseunits b on o.sus_no=b.b_sus_no \r\n"
	    					+ "left join (select sus_no,'OBSN' as status\r\n"
	    					+ "			from mms_tb_unit_upload_document\r\n"
	    					+ "			where TO_CHAR(created_on,'yyyy-mm') = TO_CHAR(now(),'yyyy-mm') and doc is not null\r\n"
	    					+ "			group by 1) as obsn on o.sus_no=obsn.sus_no \r\n"
	    					+ "where o.status_sus_no='Active' AND o.sus_no  in ( select orb.sus_no from  tb_miso_orbat_unt_dtl orb\r\n"
	    					+ "				left join all_fmn_view fv ON  substring(orb.form_code_control::text, 1, 1) = substring (fv.form_code_control::text, 1, 1) \r\n"
	    					+ "				AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
	    					+ "				where  orb.status_sus_no::text = 'Active'::text and fv.form_code_control=? )";
	    			
	    		
	    			stmt=conn.prepareStatement(sql);
	    			stmt.setString(1,formation);
	    			ResultSet rs1 = stmt.executeQuery();     
	    			
	    			while(rs1.next()){
	    				ArrayList<String> list1 = new ArrayList<String>();
	    				list1.add(rs1.getString("totunits"));
	    				list1.add(rs1.getString("totnres"));
	    				list1.add(rs1.getString("totres"));
	    				list.add(list1);
	    	        }
	    		    rs1.close();
	    		    stmt.close();	     
	    		}catch (SQLException e) {
	    				//throw new RuntimeException(e);
	    				e.printStackTrace();
	    		} finally {
	    			if (conn != null) {
	    				try {
	    					conn.close();
	    				} 
	    				catch (SQLException e) {
	    				}
	    			}
	    		}
	    	return list;
	        }



@Override
public ArrayList<ArrayList<String>> getunititobsn(String formation) {
		   ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	    		Connection conn = null;
	    		try{	  
	    			
	    			conn = dataSource.getConnection();			 
	    			PreparedStatement stmt=null;
	    			
	    			String sql = " 			SELECT\r\n"
	    					+ "    COUNT(DISTINCT orb.sus_no) AS total_count,\r\n"
	    					+ "    COUNT(DISTINCT CASE WHEN am.sus_no IS NOT NULL THEN orb.sus_no END) AS matched_count,\r\n"
	    					+ "    COUNT(DISTINCT CASE WHEN am.sus_no IS NULL THEN orb.sus_no END) AS unmatched_count\r\n"
	    					+ "FROM\r\n"
	    					+ "    tb_miso_orbat_unt_dtl orb\r\n"
	    					+ "LEFT JOIN\r\n"
	    					+ "    all_fmn_view fv ON substring(orb.form_code_control::text, 1, 1) = substring(fv.form_code_control::text, 1, 1)\r\n"
	    					+ "                     AND fv.level_in_hierarchy::text = 'Command'::text\r\n"
	    					+ "LEFT JOIN\r\n"
	    					+ "    (\r\n"
	    					+ "        SELECT DISTINCT sus_no\r\n"
	    					+ "        FROM tb_asset_main\r\n"
	    					+ "        WHERE status = 1\r\n"
	    					+ "    ) am ON orb.sus_no = am.sus_no\r\n"
	    					+ "WHERE\r\n"
	    					+ "    orb.status_sus_no::text = 'Active'::text\r\n"
	    					+ "    AND fv.form_code_control = ? ;\r\n"
	    					+ "								";
	    			
	    		
	    			stmt=conn.prepareStatement(sql);
	    			stmt.setString(1,formation);
	    			ResultSet rs1 = stmt.executeQuery();     
	    			
	    			while(rs1.next()){
	    				ArrayList<String> list1 = new ArrayList<String>();
	    				list1.add(rs1.getString("total_count"));
	    				list1.add(rs1.getString("matched_count"));
	    				list1.add(rs1.getString("unmatched_count"));
	    				list.add(list1);
	    	        }
	    		    rs1.close();
	    		    stmt.close();	     
	    		}catch (SQLException e) {
	    				//throw new RuntimeException(e);
	    				e.printStackTrace();
	    		} finally {
	    			if (conn != null) {
	    				try {
	    					conn.close();
	    				} 
	    				catch (SQLException e) {
	    				}
	    			}
	    		}
	    	return list;
	     
}





}