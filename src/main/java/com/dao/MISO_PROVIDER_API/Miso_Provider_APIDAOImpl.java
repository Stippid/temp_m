package com.dao.MISO_PROVIDER_API;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.persistance.util.HibernateUtilNA;

public class Miso_Provider_APIDAOImpl implements Miso_Provider_APIDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@SuppressWarnings("deprecation")
	public List<Map<String, Object>> getUnitDetailsFromSusNo(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			query1 = session1.createSQLQuery("select u.sus_no,u.unit_name,c.level_in_hierarchy,\r\n" + 
					"	case when c.level_in_hierarchy='Command' then substr(form_code_control,1,1)\r\n" + 
					"	when c.level_in_hierarchy='Corps' then substr(form_code_control,1,3)\r\n" + 
					"	when c.level_in_hierarchy='Division' then substr(form_code_control,1,6)\r\n" + 
					"	when c.level_in_hierarchy='Brigade' then form_code_control\r\n" + 
					"	else '0000000000'\r\n" + 
					"	end as form_code_control\r\n" + 
					"		from tb_miso_orbat_unt_dtl u\r\n" + 
					"inner join tb_miso_orbat_codesform c on  u.sus_no = c.sus_no AND u.status_sus_no = 'Active' \r\n" + 
					"where u.sus_no=:sus_no");
			query1.setParameter("sus_no", sus_no);
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("sus_no",row[0].toString());
		    	columns.put("unit_name",row[1].toString());
		    	columns.put("level_in_hierarchy",row[2].toString());
		    	columns.put("form_code_control",row[3].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	
	/*	
	
	public List<Map<String, Object>> getManpowerOffrDtls(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String column = " c.short_form as unit_name ";
			String column_sus_no = " c.sus_no ";
			String formationwise_whr =" inner join orbat_view_cmd c on c.cmd_code = substr(orb.form_code_control,1,1)\r\n";
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " and orb.sus_no = '"+susno+"' ";
				}else {
					whr = " and orb.form_code_control like '"+form_code_control+"%' ";
				}
				
				if (lvl_in_hierarchy.equals("Command")) {
					formationwise_whr = " inner join orbat_view_corps c on c.corps_code = substr(orb.form_code_control,2,2) \r\n";
					column = " c.coprs_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Corps")) {
					formationwise_whr = " inner join orbat_view_div c on c.div_code = substr(orb.form_code_control,4,3) \r\n";
					column = " c.div_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Division")) {
					formationwise_whr = " inner join orbat_view_bde c on c.bde_code = substr(orb.form_code_control,7,4) \r\n";
					column = " c.bde_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Brigade")) {
					formationwise_whr = "";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}else if (lvl_in_hierarchy.equals("Unit")) {
					formationwise_whr = "";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}
			}
			query1 = session1.createSQLQuery("SELECT "+column+",\r\n" + 
					"       sum(app.total)- sum(app.permt_offr)- sum(app.temp_offr) as fit,\r\n" + 
					"       sum(app.permt_offr) as permt,\r\n" + 
					"       sum(app.temp_offr) as temp,\r\n" + 
					"       0 as desserter,\r\n" + 
					"       0  as re_employed,\r\n" + 
					"       "+column_sus_no+" \r\n" + 
					"FROM (\r\n" + 
					"    SELECT c.unit_sus_no,count(*) as total,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'FIT') AS fit_offr,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'PERMANENT') AS permt_offr,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'TEMPORARY') AS temp_offr\r\n" + 			
					"    FROM tb_psg_trans_proposed_comm_letter c\r\n" + 
					"    left JOIN tb_psg_medical_categoryhistory hld ON c.id = hld.comm_id and hld.status ='1' \r\n" + 	
					"	Inner join tb_miso_orbat_unt_dtl u on c.unit_sus_no=u.sus_no\r\n" + 
					"	inner join tb_miso_orbat_codesform code on  u.sus_no = code.sus_no AND u.status_sus_no = 'Active' \r\n" + 
					"	where c.status in ('1','5') and c.type_of_comm_granted != '20' \r\n"+
					"    GROUP BY c.unit_sus_no\r\n" +
					") app\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = app.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					formationwise_whr + 
					whr +
					"group by 1,7\r\n" + 
					"order by 1");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("unit_name",row[0].toString());
		    	columns.put("fit",row[1].toString());
		    	columns.put("permt",row[2].toString());
		    	columns.put("temp",row[3].toString());
		    	columns.put("desserter",row[4].toString());
		    	columns.put("re_employed",row[5].toString());
		    	columns.put("sus_no",row[6].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getManpowerOffrDtls: "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getManpowerJCO_ORDtls(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String column = " c.short_form as unit_name ";
			String column_sus_no = " c.sus_no ";
			String formationwise_whr =" inner join orbat_view_cmd c on c.cmd_code = substr(orb.form_code_control,1,1)\r\n";
			
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " and orb.sus_no = '"+susno+"' ";
				}else {
					whr = " and orb.form_code_control like '"+form_code_control+"%' ";
				}
				
				if (lvl_in_hierarchy.equals("Command")) {
					formationwise_whr = " inner join orbat_view_corps c on c.corps_code = substr(app.form_code_control,2,2) \r\n";
					column = " c.coprs_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Corps")) {
					formationwise_whr = " inner join orbat_view_div c on c.div_code = substr(app.form_code_control,4,3) \r\n";
					column = " c.div_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Division")) {
					formationwise_whr = " inner join orbat_view_bde c on c.bde_code = substr(app.form_code_control,7,4) \r\n";
					column = " c.bde_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Brigade")) {
					formationwise_whr = "  ";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}else if (lvl_in_hierarchy.equals("Unit")) {
					formationwise_whr = "  ";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}
			}
			query1 = session1.createSQLQuery("select "+column+",\r\n" + 
					"	sum(app.jco) as jco,\r\n" + 
					"	sum(app.or) as or, \r\n" + 
					"	sum(app.rects) as rects,\r\n" +
					"	"+column_sus_no+" "+
					"from\r\n" + 
					"(select ce.unit_sus_no,orb.form_code_control,\r\n" + 
					"	count(*) FILTER (where ce.category='JCO') as jco,\r\n" + 
					"	count(*) FILTER (where ce.category='OR') as or, \r\n" + 
					"	count(*) FILTER (where ce.rank='17') as rects\r\n" + 
					"from tb_psg_census_jco_or_p ce\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no=ce.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					"where ce.status='1' and orb.status_sus_no='Active'\r\n" + 
					"group by 1,2) app\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = app.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					formationwise_whr + 
					whr +
					"group by 1,5 ");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("unit_name",row[0].toString());
		    	columns.put("jco",row[1].toString());
		    	columns.put("or",row[2].toString());
		    	columns.put("rects",row[3].toString());
		    	columns.put("sus_no",row[4].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getManpowerOffrDtls: "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getVehiclesDtls(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = " where ";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr += "  a.sus_no = '"+susno+"' and ";
				}else {
					whr += "  a.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery( "select b.mct_nomen as nomen,COALESCE(sum(a.ue),'0') as ue,COALESCE(sum(a.total_uh),'0') as uh\r\n" + 
					"		from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"		inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
						whr + 
						"  ((upper(b.mct_nomen) like '%HMV%' and upper(b.mct_nomen) like '%GS%')\r\n" + 
						"	  or b.mct_nomen like '%HIGH MOBILITY VEH [HMV] 8x8 WITH MATERIAL HANDLING CRANE [MHC] 1.5/2 TON%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 CARRIAGE & HANDLING OF SMERCH AMN W/O CRANE%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 CARRIAGE & HANDLING OF SMERCH AMN WITH CRANE%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 LAUNCHER VEH%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%ARJUN%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%MPV%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%T-72%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%T-90%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%BMP%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%AMBULANCE%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%GUN TOWING%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%FAT%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%MINE PROTECTED VEH%')" +
					"		 group by 1\r\n" + 
					"		 order by 3 desc ");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("nomen",row[0].toString());
		    	columns.put("ue",row[1].toString());
		    	columns.put("uh",row[2].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getWeaponCategory(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " u.sus_no = '"+susno+"' and ";
				}else {
					whr = " u.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery("select \r\n" + 
					"	pr.prf_group as wpn_cat\r\n" + 
					"from (\r\n" + 
					"	select p.prf_code\r\n" + 
					"	from (select 	b.prf_code\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	prf_group_code as prf_code\r\n" + 
					"			from mms_ue_mview u\r\n" + 
					"		where "+whr+" u.total_ue_qty is not null ) p  \r\n" + 
					"	group by p.prf_code\r\n" + 
					"	) r  \r\n" + 
					"	inner join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	order by pr.prf_group");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (int i =0;i < rows.size();i++) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("wpn_cat",rows.get(i));
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getWeaponCategory : "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getWeaponState(String sus_no,String wpn_cat){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " u.sus_no = '"+susno+"' and ";
				}else {
					whr = " u.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery("select \r\n" + 
					"	i.item_type as nomen,\r\n" + 
					"	cast(r.totue as varchar) as ue,\r\n" + 
					"	r.totuh as uh \r\n" + 
					"from (\r\n" + 
					"	select p.prf_code,\r\n" + 
					"			p.item_code,\r\n" + 
					"			'A0' as type_of_hldg,\r\n" + 
					"			p.type_of_eqpt,\r\n" + 
					"			sum(ueqty) as totue,\r\n" + 
					"			sum(uhqty) as totuh\r\n" + 
					"	from (select 	b.prf_code,\r\n" + 
					"					b.item_code,\r\n" + 
					"					a.census_no,\r\n" + 
					"					a.type_of_eqpt,\r\n" + 
					"					0 as ueqty,\r\n" + 
					"					a.tothol as uhqty\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	u.prf_group_code as prf_code,\r\n" + 
					"					u.item_code,\r\n" + 
					"					'' as census_no,\r\n" + 
					"					(case when upper(u.type)='CES' then '2' else '1' end) as type_of_eqpt,\r\n" + 
					"					u.total_ue_qty as ueqty,\r\n" + 
					"					0 as uhqty\r\n" + 
					"			from mms_ue_mview u\r\n" + 
					"		where "+whr+" u.total_ue_qty is not null) p  \r\n" + 
					"	group by p.prf_code,p.item_code,p.type_of_eqpt\r\n" + 
					"	) r  \r\n" + 
					"	inner join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code  \r\n" + 
					"	inner join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	where pr.prf_group='"+wpn_cat+"' "+
					"	order by r.item_code");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("nomen",row[0].toString());
		    	columns.put("ue",row[1].toString());
		    	columns.put("uh",row[2].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getWeaponState: "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getOrbatUnitDtls(String last_created_date){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String whr = " ";
			if(!last_created_date.equals("")){
				 whr = " and u.creation_on >:last_created_date ";
			}
			query1 = session1.createSQLQuery("select   distinct                        ab.cmd_name as cmd_name,  \r\n" + 
					"	            			            		     ac.coprs_name as corps_name,  \r\n" + 
					"	            			            		    ad.div_name as div_name,  \r\n" + 
					"	            			            		    ae.bde_name as bde_name,  \r\n" + 
					"	            			            		    u.unit_name as unit_name ,\r\n" + 
					"																				u.sus_no as sus_no, u.creation_on,c.level_in_hierarchy \r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_codesform c on u.sus_no=c.sus_no\r\n"
					+ "inner join orbat_view_cmd ab ON substr(u.form_code_control, 1, 1) = ab.cmd_code  \r\n" + 
					"	       left join orbat_view_corps ac ON substr(u.form_code_control, 2, 2) = ac.corps_code  \r\n" + 
					"	      left join orbat_view_div ad ON substr(u.form_code_control, 4, 3) = ad.div_code  \r\n" + 
					"	       left join orbat_view_bde ae ON substr(u.form_code_control, 7, 4) = ae.bde_code"
					+ "where u.status_sus_no = 'Active' \r\n"
					+ "	and u.sus_no is not null \r\n"
					+ "	and u.unit_name is not null \r\n"
					+ " and c.level_in_hierarchy is not null"
					+ "	and u.creation_on is not null "					
					+ whr + " order by u.creation_on ");
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date created_date;
			try {
				if(!last_created_date.equals("")){
					created_date = format.parse(last_created_date);
					query1.setDate("last_created_date", created_date);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
			for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("cmd_name", row[0].toString());
                columns.put("corps_name", row[1].toString());
                columns.put("div_name",row[2].toString());
                columns.put("bde_name",  row[3].toString());
                columns.put("unit_name",  row[4].toString());
                columns.put("sus_no",  row[5].toString());
                columns.put("creation_on",  row[6].toString());
                columns.put("level_in_hierarchy", row[7].toString());
		    	list.add(columns);
		    }   
		    tx1.commit();
		    session1.close();
		} catch (NullPointerException e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}*/
	
	public List<Map<String, Object>> getNRSDetails_OL(String last_updated_date){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		System.err.println("in miso nrs db sdsdsd "+last_updated_date);
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try { 
			System.err.println("in miso nrs db "+last_updated_date);
			query1 = session1.createSQLQuery("select code_value as nrs_name,nrs_code,created_on as last_updated_date from Tb_Miso_Orbat_Code where code_type = 'Location' and created_on >'"+last_updated_date+"'");
			@SuppressWarnings("unchecked")
	
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("nrs_name",row[0].toString());
		    	columns.put("nrs_code",row[1].toString());
		    	columns.put("last_updated_date",row[2].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getUnitDetails_OL(String last_updated_date){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try { 
			System.err.println("in miso db");
			query1 = session1.createSQLQuery("select sus_no,unit_name,creation_on as last_updated_date,status_sus_no from "
					+ "orbat_all_details_view where creation_on >'"+last_updated_date+"' and status_sus_no='Active' order by creation_on");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("sus_no",row[0].toString());
		    	columns.put("unit_name",row[1].toString());
		    	columns.put("last_updated_date",row[2].toString());
		    	columns.put("status_sus_no",row[3].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	public List<Map<String, Object>> getOrbatDetails_OL(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try { 
			query1 = session1.createSQLQuery(" select coalesce(ab.sus_no,'') as cmd_sus,\r\n" + 
					"    coalesce(ac.sus_no,'') as corps_sus,\r\n" + 
					"    coalesce(ad.sus_no,'') as div_sus,\r\n" + 
					"    coalesce(ae.sus_no,'') as bde_sus,\r\n" + 
					"    coalesce(a.nrs_code,'') as nrs_code "
					+ "   from tb_miso_orbat_unt_dtl a\r\n"
					+ "     join tb_miso_orbat_shdul_detl b on a.id = b.letter_id\r\n"
					+ "     left join orbat_view_cmd ab ON substr(a.form_code_control, 1, 1) = ab.cmd_code\r\n"
					+ "     left join orbat_view_corps ac ON substr(a.form_code_control, 2, 2) = ac.corps_code\r\n"
					+ "     left join orbat_view_div ad ON substr(a.form_code_control, 4, 3) = ad.div_code\r\n"
					+ "     left join orbat_view_bde ae ON substr(a.form_code_control, 7, 4) = ae.bde_code where a.sus_no='"+sus_no+"' and a.status_sus_no='Active'");
			System.out.println(query1.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("cmd_sus",row[0].toString());
		    	columns.put("corps_sus",row[1].toString());
		    	columns.put("div_sus",row[2].toString());
		    	columns.put("bde_sus",row[3].toString());
		    	columns.put("nrs_code",row[4].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getOrbatDetails_OLWithName(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try { 
			System.err.println("with name in MISO function name is getOrbatDetails_OLWithName  "+sus_no);
			query1 = session1.createSQLQuery(" select ab.cmd_name as cmd_name,\r\n" + 
					"    ac.coprs_name as corps_name,\r\n" + 
					"    ad.div_name as div_name,\r\n" + 
					"    ae.bde_name as bde_name,\r\n" + 
					"    n.code_value as nrs, a.unit_name as unit_name "
					+ "   from tb_miso_orbat_unt_dtl a\r\n"
					+ "   join tb_miso_orbat_shdul_detl b on a.id = b.letter_id\r\n"
					+ "   left join orbat_view_cmd ab ON substr(a.form_code_control, 1, 1) = ab.cmd_code\r\n"
					+ "   left join orbat_view_corps ac ON substr(a.form_code_control, 2, 2) = ac.corps_code\r\n"
					+ "   left join orbat_view_div ad ON substr(a.form_code_control, 4, 3) = ad.div_code\r\n"
					+ "   left join orbat_view_bde ae ON substr(a.form_code_control, 7, 4) = ae.bde_code "
					+ "LEFT JOIN tb_miso_orbat_code n on n.code=a.nrs_code and n.code_type= 'Location'"
					+ "where a.sus_no='"+sus_no+"' and a.status_sus_no='Active'"); //  q.setParameter("roleFormationNo", roleFormationNo + "%");
				//+ "where a.sus_no like :sus_no and a.status_sus_no='Active'");
		//	query1.setParameter("sus_no", sus_no);
			System.out.println("in miso app setting orbat detaills "+query1.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("cmd_name",row[0].toString());
		    	columns.put("corps_name",row[1].toString());
		    	columns.put("div_name",row[2].toString());
		    	columns.put("bde_name",row[3].toString());
		    	columns.put("nrs",row[4].toString());
		    	columns.put("unit_name",row[5].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	/////////////////////For NEW API Gateway
	public List<Map<String, Object>> getVehiclesDtlsNew(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = " where ";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr += "  a.sus_no = '"+susno+"' and ";
				}else {
					whr += "  a.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery( "select b.mct_nomen as nomen,COALESCE(sum(a.ue),'0') as ue,COALESCE(sum(a.total_uh),'0') as uh\r\n" + 
					"		from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"		inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
						whr + 
						"  ((upper(b.mct_nomen) like '%HMV%' and upper(b.mct_nomen) like '%GS%')\r\n" + 
						"	  or b.mct_nomen like '%HIGH MOBILITY VEH [HMV] 8x8 WITH MATERIAL HANDLING CRANE [MHC] 1.5/2 TON%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 CARRIAGE & HANDLING OF SMERCH AMN W/O CRANE%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 CARRIAGE & HANDLING OF SMERCH AMN WITH CRANE%'\r\n" + 
						"	 or b.mct_nomen like '%HMV 10X10 LAUNCHER VEH%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%ARJUN%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%MPV%' \r\n" + 
						"	 or upper(b.mct_nomen) like '%T-72%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%T-90%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%BMP%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%AMBULANCE%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%GUN TOWING%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%FAT%'\r\n" + 
						"	 or upper(b.mct_nomen) like '%MINE PROTECTED VEH%')" +
					"		 group by 1\r\n" + 
					"		 order by 3 desc ");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("nomen",row[0].toString());
		    	columns.put("ue",row[1].toString());
		    	columns.put("uh",row[2].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}

	public List<Map<String, Object>> getManpowerOffrDtlsNew(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String column = " c.short_form as unit_name ";
			String column_sus_no = " c.sus_no ";
			String formationwise_whr =" inner join orbat_view_cmd c on c.cmd_code = substr(orb.form_code_control,1,1)\r\n";
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " and orb.sus_no = '"+susno+"' ";
				}else {
					whr = " and orb.form_code_control like '"+form_code_control+"%' ";
				}
				
				if (lvl_in_hierarchy.equals("Command")) {
					formationwise_whr = " inner join orbat_view_corps c on c.corps_code = substr(orb.form_code_control,2,2) \r\n";
					column = " c.coprs_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Corps")) {
					formationwise_whr = " inner join orbat_view_div c on c.div_code = substr(orb.form_code_control,4,3) \r\n";
					column = " c.div_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Division")) {
					formationwise_whr = " inner join orbat_view_bde c on c.bde_code = substr(orb.form_code_control,7,4) \r\n";
					column = " c.bde_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Brigade")) {
					formationwise_whr = "";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}else if (lvl_in_hierarchy.equals("Unit")) {
					formationwise_whr = "";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}
			}
			query1 = session1.createSQLQuery("SELECT "+column+",\r\n" + 
					"       sum(app.total)- sum(app.permt_offr)- sum(app.temp_offr) as fit,\r\n" + 
					"       sum(app.permt_offr) as permt,\r\n" + 
					"       sum(app.temp_offr) as temp,\r\n" + 
					"       0 as desserter,\r\n" + 
					"       0  as re_employed,\r\n" + 
					"       "+column_sus_no+" \r\n" + 
					"FROM (\r\n" + 
					"    SELECT c.unit_sus_no,count(*) as total,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'FIT') AS fit_offr,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'PERMANENT') AS permt_offr,\r\n" + 
					"           count(*) FILTER (WHERE hld.med_classification_lmc = 'TEMPORARY') AS temp_offr\r\n" + 			
					"    FROM tb_psg_trans_proposed_comm_letter c\r\n" + 
					"    left JOIN tb_psg_medical_categoryhistory hld ON c.id = hld.comm_id and hld.status ='1' \r\n" + 	
					"	Inner join tb_miso_orbat_unt_dtl u on c.unit_sus_no=u.sus_no\r\n" + 
					"	inner join tb_miso_orbat_codesform code on  u.sus_no = code.sus_no AND u.status_sus_no = 'Active' \r\n" + 
					"	where c.status in ('1','5') and c.type_of_comm_granted != '20' \r\n"+
					"    GROUP BY c.unit_sus_no\r\n" +
					") app\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = app.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					formationwise_whr + 
					whr +
					"group by 1,7\r\n" + 
					"order by 1");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("unit_name",row[0].toString());
		    	columns.put("fit",row[1].toString());
		    	columns.put("permt",row[2].toString());
		    	columns.put("temp",row[3].toString());
		    	columns.put("desserter",row[4].toString());
		    	columns.put("re_employed",row[5].toString());
		    	columns.put("sus_no",row[6].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getManpowerOffrDtls: "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getManpowerJCO_ORDtlsNew(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String column = " c.short_form as unit_name ";
			String column_sus_no = " c.sus_no ";
			String formationwise_whr =" inner join orbat_view_cmd c on c.cmd_code = substr(orb.form_code_control,1,1)\r\n";
			
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " and orb.sus_no = '"+susno+"' ";
				}else {
					whr = " and orb.form_code_control like '"+form_code_control+"%' ";
				}
				
				if (lvl_in_hierarchy.equals("Command")) {
					formationwise_whr = " inner join orbat_view_corps c on c.corps_code = substr(app.form_code_control,2,2) \r\n";
					column = " c.coprs_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Corps")) {
					formationwise_whr = " inner join orbat_view_div c on c.div_code = substr(app.form_code_control,4,3) \r\n";
					column = " c.div_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Division")) {
					formationwise_whr = " inner join orbat_view_bde c on c.bde_code = substr(app.form_code_control,7,4) \r\n";
					column = " c.bde_name as unit_name ";
				} else if (lvl_in_hierarchy.equals("Brigade")) {
					formationwise_whr = "  ";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}else if (lvl_in_hierarchy.equals("Unit")) {
					formationwise_whr = "  ";
					column = " orb.unit_name ";
					column_sus_no = " orb.sus_no ";
				}
			}
			query1 = session1.createSQLQuery("select "+column+",\r\n" + 
					"	sum(app.jco) as jco,\r\n" + 
					"	sum(app.or) as or, \r\n" + 
					"	sum(app.rects) as rects,\r\n" +
					"	"+column_sus_no+" "+
					"from\r\n" + 
					"(select ce.unit_sus_no,orb.form_code_control,\r\n" + 
					"	count(*) FILTER (where ce.category='JCO') as jco,\r\n" + 
					"	count(*) FILTER (where ce.category='OR') as or, \r\n" + 
					"	count(*) FILTER (where ce.rank='17') as rects\r\n" + 
					"from tb_psg_census_jco_or_p ce\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no=ce.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					"where ce.status='1' and orb.status_sus_no='Active'\r\n" + 
					"group by 1,2) app\r\n" + 
					"INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = app.unit_sus_no AND orb.status_sus_no = 'Active'\r\n" + 
					formationwise_whr + 
					whr +
					"group by 1,5 ");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("unit_name",row[0].toString());
		    	columns.put("jco",row[1].toString());
		    	columns.put("or",row[2].toString());
		    	columns.put("rects",row[3].toString());
		    	columns.put("sus_no",row[4].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getManpowerOffrDtlsNew: "+e.getMessage());
		}
		return list;
	}
	public List<Map<String, Object>> getWeaponCategoryNew(String sus_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " u.sus_no = '"+susno+"' and ";
				}else {
					whr = " u.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery("select \r\n" + 
					"	pr.prf_group as wpn_cat\r\n" + 
					"from (\r\n" + 
					"	select p.prf_code\r\n" + 
					"	from (select 	b.prf_code\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				Inner JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	prf_group_code as prf_code\r\n" + 
					"			from mms_ue_mview u\r\n" + 
					"		where "+whr+" u.total_ue_qty is not null ) p  \r\n" + 
					"	group by p.prf_code\r\n" + 
					"	) r  \r\n" + 
					"	inner join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	order by pr.prf_group");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (int i =0;i < rows.size();i++) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("wpn_cat",rows.get(i));
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getWeaponCategoryNew : "+e.getMessage());
		}
		return list;
	}
	
	public List<Map<String, Object>> getWeaponStateNew(String sus_no,String wpn_cat){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		String whr = "";
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			List<Map<String, Object>> unitDetails = getUnitDetailsFromSusNo(sus_no);
			if(!sus_no.equals("") && unitDetails.size() == 0) {
				return list;
			}
			if(unitDetails.size() > 0) {
				String lvl_in_hierarchy = unitDetails.get(0).get("level_in_hierarchy").toString();
				String form_code_control = unitDetails.get(0).get("form_code_control").toString();
				String susno = unitDetails.get(0).get("sus_no").toString();
				if(lvl_in_hierarchy.trim().equals("Unit")){
					whr = " u.sus_no = '"+susno+"' and ";
				}else {
					whr = " u.form_code_control like '"+form_code_control+"%' and ";
				}
			}
			query1 = session1.createSQLQuery("select \r\n" + 
					"	i.item_type as nomen,\r\n" + 
					"	cast(r.totue as varchar) as ue,\r\n" + 
					"	r.totuh as uh \r\n" + 
					"from (\r\n" + 
					"	select p.prf_code,\r\n" + 
					"			p.item_code,\r\n" + 
					"			'A0' as type_of_hldg,\r\n" + 
					"			p.type_of_eqpt,\r\n" + 
					"			sum(ueqty) as totue,\r\n" + 
					"			sum(uhqty) as totuh\r\n" + 
					"	from (select 	b.prf_code,\r\n" + 
					"					b.item_code,\r\n" + 
					"					a.census_no,\r\n" + 
					"					a.type_of_eqpt,\r\n" + 
					"					0 as ueqty,\r\n" + 
					"					a.tothol as uhqty\r\n" + 
					"		  from (\r\n" + 
					"				select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1  as tothol\r\n" + 
					"				from mms_tb_regn_mstr_detl a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as tothol\r\n" + 
					"				from mms_tb_depot_regn_mstr_detl a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and a.type_of_hldg='A0'\r\n" + 
					"			union All\r\n" + 
					"			select a.census_no,\r\n" + 
					"				a.type_of_eqpt,\r\n" + 
					"				1 as uh\r\n" + 
					"				from mms_tb_regn_oth_mstr a \r\n" + 
					"				INNER JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n" + 
					"				where "+whr+" a.op_status='1' and  a.type_of_hldg='A0'\r\n" + 
					"		  ) as a\r\n" + 
					"				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n" + 
					"		union all   \r\n" + 
					"			select 	u.prf_group_code as prf_code,\r\n" + 
					"					u.item_code,\r\n" + 
					"					'' as census_no,\r\n" + 
					"					(case when upper(u.type)='CES' then '2' else '1' end) as type_of_eqpt,\r\n" + 
					"					u.total_ue_qty as ueqty,\r\n" + 
					"					0 as uhqty\r\n" + 
					"			from mms_ue_mview u\r\n" + 
					"		where "+whr+" u.total_ue_qty is not null) p  \r\n" + 
					"	group by p.prf_code,p.item_code,p.type_of_eqpt\r\n" + 
					"	) r  \r\n" + 
					"	inner join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code  \r\n" + 
					"	inner join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n" + 
					"	where pr.prf_group='"+wpn_cat+"' "+
					"	order by r.item_code");
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
		    for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("nomen",row[0].toString());
		    	columns.put("ue",row[1].toString());
		    	columns.put("uh",row[2].toString());
		    	list.add(columns);
		    }
		    tx1.commit();
		    session1.close();
		} catch (Exception e) {
			System.out.println("CATCH getWeaponStateNew: "+e.getMessage());
		}
		return list;
	}
	/*public List<Map<String, Object>> getOrbatUnitDtlsNew(String last_created_date){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String whr = " ";
			if(!last_created_date.equals("")){
				 whr = " and creation_on >:last_created_date ";
			}
			query1 = session1.createSQLQuery("select u.sus_no,u.unit_name,u.form_code_control,u.creation_on,u.status_sus_no,c.level_in_hierarchy \r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_codesform c on u.sus_no=c.sus_no\r\n"
					+ "where u.status_sus_no = 'Active' \r\n"
					+ "	and u.sus_no is not null \r\n"
					+ "	and u.unit_name is not null \r\n"
					+ " and c.level_in_hierarchy is not null"
					+ "	and u.creation_on is not null "
					+ whr + " order by creation_on ");
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date created_date;
			try {
				if(!last_created_date.equals("")){
					created_date = format.parse(last_created_date);
					query1.setDate("last_created_date", created_date);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
			for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("sus_no",row[0].toString());
		    	columns.put("unit_name",row[1].toString());
		    	columns.put("form_code_control",row[2].toString());
		    	columns.put("creation_on",row[3].toString());
		    	columns.put("status_sus_no",row[4].toString());
		    	columns.put("level_in_hierarchy",row[5].toString());
		    	list.add(columns);
		    }   
		    tx1.commit();
		    session1.close();
		} catch (NullPointerException e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}*/
	public List<Map<String, Object>> getOrbatUnitDtlsNew(String last_created_date){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Query query1= null;
		Session session1 = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		try {
			String whr = " ";
			if(!last_created_date.equals("")){
				 whr = " and u.creation_on >:last_created_date ";
			}
			query1 = session1.createSQLQuery("select   distinct                        ab.cmd_name as cmd_name,  \r\n" + 
					"	            			            		     ac.coprs_name as corps_name,  \r\n" + 
					"	            			            		    ad.div_name as div_name,  \r\n" + 
					"	            			            		    ae.bde_name as bde_name,  \r\n" + 
					"	            			            		    u.unit_name as unit_name ,\r\n" + 
					"																				u.sus_no as sus_no, u.creation_on,c.level_in_hierarchy \r\n"
					+ "from tb_miso_orbat_unt_dtl u\r\n"
					+ "inner join tb_miso_orbat_codesform c on u.sus_no=c.sus_no\r\n"
					+ "inner join orbat_view_cmd ab ON substr(u.form_code_control, 1, 1) = ab.cmd_code  \r\n" + 
					"	       left join orbat_view_corps ac ON substr(u.form_code_control, 2, 2) = ac.corps_code  \r\n" + 
					"	      left join orbat_view_div ad ON substr(u.form_code_control, 4, 3) = ad.div_code  \r\n" + 
					"	       left join orbat_view_bde ae ON substr(u.form_code_control, 7, 4) = ae.bde_code"
					+ "where u.status_sus_no = 'Active' \r\n"
					+ "	and u.sus_no is not null \r\n"
					+ "	and u.unit_name is not null \r\n"
					+ " and c.level_in_hierarchy is not null"
					+ "	and u.creation_on is not null "					
					+ whr + " order by u.creation_on ");
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date created_date;
			try {
				if(!last_created_date.equals("")){
					created_date = format.parse(last_created_date);
					query1.setDate("last_created_date", created_date);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query1.list();
			for (Object[] row : rows) {
		    	Map<String, Object> columns = new LinkedHashMap<String, Object>();
		    	columns.put("cmd_name", row[0].toString());
                columns.put("corps_name", row[1].toString());
                columns.put("div_name",row[2].toString());
                columns.put("bde_name",  row[3].toString());
                columns.put("unit_name",  row[4].toString());
                columns.put("sus_no",  row[5].toString());
                columns.put("creation_on",  row[6].toString());
                columns.put("level_in_hierarchy", row[7].toString());
		    	list.add(columns);
		    }   
		    tx1.commit();
		    session1.close();
		} catch (NullPointerException e) {
			System.out.println("CATCH : "+e.getMessage());
		}
		return list;
	}
	
}