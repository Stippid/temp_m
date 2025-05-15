package com.dao.tms;

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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_IUT;
import com.models.psg.Master.TB_PSG_AGENCY_MASTER;
import com.persistance.util.HibernateUtil;

public class abc_vehicleDetailsDAOImp implements abc_vehicleDetailsDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<ArrayList<String>> abc_vehiclestatusDetails_line_dte(String cmd,String mct_main_list,String prf_code ,String type,String sus_no,String line_dte_sus1,int kms, int vintage, String type_of_force) {
		String whr ="";
		
		if(!prf_code.equals("0")) {
			String prfcode=prf_code.replaceAll(",","','");
			if(prf_code.toUpperCase().indexOf("XNR")<=-1) {
				whr += " b.prf_code in ('"+prfcode+"') ";
			}				
		}
		
		if(!mct_main_list.equals("")) {
			String[] mct_main_array = mct_main_list.split(":");
			if(mct_main_array.length>0) {
				whr +=" and ( ";
			}
			for(int i=0;i<mct_main_array.length;i++) {
				if(i==0) {
					whr +=" b.mct_main_id = ? ";	
				}else {
					whr +=" or b.mct_main_id = ? ";
				}
			}
			if(mct_main_array.length>0) {
				whr +=" ) ";
			}
		}
		
		
		if(!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		}else {
			if(!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}
		
		if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!type_of_force.equals("") & !type_of_force.equals("0")){
    		whr +=" and e.type_of_force = ? ";
    	}
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;	
		String sql = "";
		try {			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(type.equals("A")) {
				if(kms >0) { whr += " and round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? "; }
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
			/* sql = "select b.mct_nomen,\r\n" + 
					"	c.short_form as comd,\r\n" + 
					"	corps.coprs_name as corps,\r\n" + 
					"	div.div_name as div,\r\n" + 
					"	bde.bde_name as bde,\r\n" + 
					"	u.unit_name,u.sus_no,part.ba_no,\r\n"+
					"   round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run , \r\n"+
					"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,part.aux_engn_clasfctn as classification \r\n"+
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"inner join tb_tms_census_retrn part on u.sus_no = part.sus_no\r\n"+
					"inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n"+
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where "+whr+ 
					" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
					" order by u.unit_name";*/
				sql="select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.ba_no,\r\n" + 
						"    round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part. vehcl_classfctn as classification\r\n" + 
						" \r\n" + 
						" from tb_tms_census_retrn part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(bd.mct::varchar,1,4) = b.mct_main_id\r\n" +
						"  inner join tms_aveh_mcr_update_state_view e on  u.sus_no = e.sus_no \r\n" +
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code where "+whr+"  ";
			}
			if(type.equals("B")) {
				if(kms >0) { whr += " and round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? ";}
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
				/*sql = "select b.mct_nomen,\r\n" + 
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						"	u.unit_name,u.sus_no,part.ba_no,\r\n"+
						"    round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n"+
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification\r\n"+
						"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
						"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
						"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
						"inner join tb_tms_mvcr_parta_dtl part on u.sus_no = part.sus_no\r\n"+
						"inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n"+
						"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
						"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
						"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
						"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where a.type = ? "+whr+ 
						" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
						" order by u.unit_name";*/
				
				sql = "select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.ba_no,\r\n" + 
						"    round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification, \r\n" + 
						"   d.km_criteria,\r\n" + 
						"   d.vintage_criteria,\r\n" + 
						"   a.discard_currentyear,\r\n" + 
						"   a.discard_one,\r\n" + 
						"   a.discard_two,\r\n" + 
						"   a.discard_three,\r\n" + 
						"   a.discard_four,\r\n" + 
						"   a.discard_five\r\n" + 
						" \r\n" + 
						" from tb_tms_mvcr_parta_dtl part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n" + 
						" left join banowise_discard_calculation a on bd.ba_no= a.bano\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(mct::varchar,1,4) = b.mct_main_id\r\n" + 
						" left join tms_bveh_mvcr_update_state_view e on  u.sus_no = e.sus_no\r\n" + 
						" left join mct_discard_criteria d on d.mctno::text = b.mct_main_id and e.type_of_force = d.type_of_force\r\n" + 
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						" left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						" left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where "+whr+" and part.issue_type!='4' ";
				
			}
           if(type.equals("C")) {
        	   if(kms >0) {whr += " and round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? ";}
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
        	   
        	  /* sql = "select b.mct_nomen,\r\n" + 
   					"	c.short_form as comd,\r\n" + 
   					"	corps.coprs_name as corps,\r\n" + 
   					"	div.div_name as div,\r\n" + 
   					"	bde.bde_name as bde,\r\n" + 
   					"	u.unit_name,u.sus_no,part.em_no as ba_no,\r\n"+
   					"   round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,  \r\n"+
   					"    cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,part.classification as classification\r\n"+
   					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
   					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
   					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
   					"inner join tb_tms_emar_report part on u.sus_no = part.sus_no\r\n"+
    					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
   					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
   					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
   					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
   					" where "+whr+ 
   					" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
   					" order by u.unit_name";*/
				sql = "select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.em_no as ba_no,\r\n" + 
						"    round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification\r\n" + 
						" \r\n" + 
						" from tb_tms_emar_report part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.em_no = bd.ba_no\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(mct::varchar,1,4) = b.mct_main_id\r\n" + 
						"  inner join tms_cveh_mcr_update_state_view e on u.sus_no = e.sus_no \r\n" +
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						" left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						" left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where "+whr+" ";
				
			}
			 stmt = conn.prepareStatement(sql);
			
			int flag = 0;
			if(!mct_main_list.equals("")){
				String[] mct_main_array = mct_main_list.split(":");
				for(int i=0;i<mct_main_array.length;i++) {
					flag = flag + 1;
					stmt.setString(flag,mct_main_array[i]);
				}
			}
			if(!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag,sus_no);
			}else {
				if(!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,cmd+"%");
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, line_dte_sus1);
        	}
			if(!type_of_force.equals("") & !type_of_force.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, type_of_force);
			}
			if(kms >0) {
				flag = flag + 1;
				stmt.setInt(flag, kms);
			}
			if(vintage >0) {
				flag = flag + 1;
				stmt.setInt(flag, vintage);
			}
		
			System.out.println("-----"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));//0
				list1.add(rs.getString("corps"));//1
				list1.add(rs.getString("div"));//2
				list1.add(rs.getString("bde"));//3
				list1.add(rs.getString("unit_name"));//4
				list1.add(rs.getString("sus_no"));//5
				list1.add(rs.getString("mct_nomen"));//6
				list1.add(rs.getString("ba_no"));//7
				list1.add(rs.getString("kms_run"));//8
				list1.add(rs.getString("vintage"));//9
				list1.add(rs.getString("classification"));//10			
				list1.add(rs.getString("std_nomclature"));//11	
				if(type.equals("B")) {
				list1.add(rs.getString("discard_currentyear"));//12
				list1.add(rs.getString("discard_one"));//13
				list1.add(rs.getString("discard_two"));//14
				list1.add(rs.getString("discard_three"));//15
				list1.add(rs.getString("discard_four"));//16
				list1.add(rs.getString("discard_five"));//17
				list1.add(rs.getString("km_criteria"));//18
				list1.add(rs.getString("vintage_criteria"));//19
				}
				list.add(list1);
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

	
	
	public List<Map<String, Object>> vehiclestatusDetails_line_dte_coas(int startPage,String pageLength,String Search,String orderColunm,String orderType,String cmd,String mct_main,String prf_code ,String type,String sus_no_list,String line_dte_sus1) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String whr ="";
		if(!mct_main.equals("0")) {
			whr +=" and a.mct_main_id = ? ";
		}
		if(!prf_code.equals("0")) {
			whr += " and a.prf_code = ? ";
		}
		
		if(!sus_no_list.equals("")) {
			String sus_noArray[] = sus_no_list.split(":");
			for(int i =0;i<sus_noArray.length;i++) {
				if(i==0) {
					whr += " and ( a.sus_no = ? ";
				}else {
					whr += " or a.sus_no = ? ";
				}
				
				if(i == (sus_noArray.length-1)) {
					whr += " ) ";
				}
			}
		}else {
			if(!cmd.equals("")) {
				whr += " and a.form_code_control like ? ";
			}
		}
		if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select DISTINCT b.mct_nomen,\r\n" + 
					"	COALESCE(c.short_form,'') as comd,\r\n" + 
					"	COALESCE(corps.coprs_name,'') as corps,\r\n" + 
					"	COALESCE(div.div_name,'') as div,\r\n" + 
					"	COALESCE(bde.bde_name,'') as bde,\r\n" + 
					"	COALESCE(u.unit_name,'') as unit_name,\r\n" + 
					"	COALESCE(sum(a.ue),'0') as ue,\r\n" + 
					" sum(a.against_ue_s+a.against_ue_u) as against_ue,\r\n" + 
					"	sum(a.over_ue_s+a.over_ue_u) as over_ue,\r\n" + 
					"	sum(a.loan_s +a.loan_u ) as loan,\r\n" + 
					"	sum(sec_store_s+sec_store_u) as sec_store,\r\n" + 
					"	sum(a.acsfp_s+a.acsfp_u) as acsfp,\r\n" + 
					"	sum(gift_s+gift_u) as gift," +
					"	sum(a.total_uh) as total_uh\r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where a.type = ? "+whr +  
					" group by 1,2,3,4,5,6 \r\n" +
					" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage;

			PreparedStatement stmt = conn.prepareStatement(sql);
			int flag = 1;
			stmt.setString(flag, type);
			if(!mct_main.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag,mct_main);
			}
			if(!prf_code.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag,prf_code);
			}
			if(!sus_no_list.equals("")) {
				String sus_noArray[] = sus_no_list.split(":");
				for(int i =0;i<sus_noArray.length;i++) {
					flag = flag + 1;
					stmt.setString(flag,sus_noArray[i]);
				}
			}else {
				if(!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,cmd+"%");
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, line_dte_sus1);
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
		} catch (SQLException e) {
			return list;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}catch (SQLException e) {	}
			}
		}
		return list;
	}
	
	public long vehiclestatusDetails_line_dte_coas_Count(String Search,String cmd,String mct_main,String prf_code ,String type,String sus_no_list,String line_dte_sus1) {
		int total = 0;
		String whr ="";
		if(!mct_main.equals("0")) {
			whr +=" and a.mct_main_id = ? ";
		}
		if(!prf_code.equals("0")) {
			whr += " and a.prf_code = ? ";
		}
		
		if(!sus_no_list.equals("")) {
			String sus_noArray[] = sus_no_list.split(":");
			for(int i =0;i<sus_noArray.length;i++) {
				if(i==0) {
					whr += " and ( a.sus_no = ? ";
				}else {
					whr += " or a.sus_no = ? ";
				}
				
				if(i == (sus_noArray.length-1)) {
					whr += " ) ";
				}
			}
		}else {
			if(!cmd.equals("")) {
				whr += " and a.form_code_control like ? ";
			}
		}
		
		if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = 
					" select count(*) as total "+
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					" where a.type = ? "+whr ;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			int flag = 1;
			stmt.setString(flag, type);
			if(!mct_main.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag,mct_main);
			}
			if(!prf_code.equals("0")) {
				flag = flag + 1;
				stmt.setString(flag,prf_code);
			}
			
			if(!sus_no_list.equals("")) {
				String sus_noArray[] = sus_no_list.split(":");
				for(int i =0;i<sus_noArray.length;i++) {
					flag = flag + 1;
					stmt.setString(flag,sus_noArray[i]);
				}
			}else {
				if(!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,cmd+"%");
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, line_dte_sus1);
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
	
	public List<Map<String, Object>> getMCtMain_from_Type_of_Veh(String prf_code,HttpSession session)
  	{		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		String prfcode=prf_code.replaceAll(":","','");
		
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="";
  			
			qry ="select distinct s.mct_main_id,s.mct_nomen from tb_tms_mct_main_master s"; 
			qry +=" inner join tms_vehicle_status_a_b_c_with_ue_uh ue_uh on ue_uh.mct_main_id = s.mct_main_id ";
			qry +=" inner join tb_tms_mct_master m on s.prf_code=m.prf_group and m.status = 'ACTIVE'"; 
			qry +=" inner join tb_tms_mct_slot_master prf on s.prf_code=prf.prf_code"; 
			qry +=" where s.mct_main_id is not null and s.mct_nomen is not null ";
			if (!prf_code.equalsIgnoreCase("XNR")) { 
				qry +=" and s.prf_code in ('"+prfcode+"')";
			}			
			qry +=" order by  s.mct_nomen";
			
 			PreparedStatement stmt=conn.prepareStatement(qry); 			 			
 			//stmt.setString(1, prfcode);
 			
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
	
	
//Added by Mitesh 02-08-24
	
	@Override
	public ArrayList<ArrayList<String>> abc_vehiclestatusDetails_nodal_dte(String cmd,String mct_main_list,String type,String sus_no,String line_dte_sus1,int kms, int vintage, String type_of_force) {
		String whr ="";
		
		/*if(!prf_code.equals("0")) {
			String prfcode=prf_code.replaceAll(",","','");
			if(prf_code.toUpperCase().indexOf("XNR")<=-1) {
				whr += " b.prf_code in ('"+prfcode+"') ";
			}				
		}
		*/
		if(!mct_main_list.equals("")) {
			String[] mct_main_array = mct_main_list.split(":");
			if(mct_main_array.length>0) {
				whr +="  ( ";
			}
			for(int i=0;i<mct_main_array.length;i++) {
				if(i==0) {
					whr +=" b.mct_main_id = ? ";	
				}else {
					whr +=" or b.mct_main_id = ? ";
				}
			}
			if(mct_main_array.length>0) {
				whr +=" ) ";
			}
		}
		
		
		if(!sus_no.equals("")) {
			whr += " and u.sus_no = ? ";
		}else {
			if(!cmd.equals("")) {
				whr += " and u.form_code_control like ? ";
			}
		}
		
	/*	if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		*/
		if(!type_of_force.equals("") & !type_of_force.equals("0")){
    		whr +=" and e.type_of_force = ? ";
    	}
		
		ArrayList<ArrayList<String>> list11 = new ArrayList<ArrayList<String>>();
		Connection conn = null;	
		String sql = "";
		try {			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if(type.equals("A")) {
				if(kms >0) { whr += " and round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? "; }
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
			/* sql = "select b.mct_nomen,\r\n" + 
					"	c.short_form as comd,\r\n" + 
					"	corps.coprs_name as corps,\r\n" + 
					"	div.div_name as div,\r\n" + 
					"	bde.bde_name as bde,\r\n" + 
					"	u.unit_name,u.sus_no,part.ba_no,\r\n"+
					"   round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run , \r\n"+
					"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,part.aux_engn_clasfctn as classification \r\n"+
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"inner join tb_tms_census_retrn part on u.sus_no = part.sus_no\r\n"+
					"inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n"+
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where "+whr+ 
					" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
					" order by u.unit_name";*/
				sql="select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.ba_no,\r\n" + 
						"    round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part. vehcl_classfctn as classification\r\n" + 
						" \r\n" + 
						" from tb_tms_census_retrn part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(bd.mct::varchar,1,4) = b.mct_main_id\r\n" +
						"  inner join tms_aveh_mcr_update_state_view e on  u.sus_no = e.sus_no \r\n" +
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						"left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						"left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						"left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code where "+whr+"  ";
			}
			if(type.equals("B")) {
				if(kms >0) { whr += " and round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? ";}
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
				/*sql = "select b.mct_nomen,\r\n" + 
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						"	u.unit_name,u.sus_no,part.ba_no,\r\n"+
						"    round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n"+
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification\r\n"+
						"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
						"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
						"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
						"inner join tb_tms_mvcr_parta_dtl part on u.sus_no = part.sus_no\r\n"+
						"inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n"+
						"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
						"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
						"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
						"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where a.type = ? "+whr+ 
						" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
						" order by u.unit_name";*/
				
				sql = "select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.ba_no,\r\n" + 
						"    round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification, \r\n" + 
						"   d.km_criteria,\r\n" + 
						"   d.vintage_criteria,\r\n" + 
						"   a.discard_currentyear,\r\n" + 
						"   a.discard_one,\r\n" + 
						"   a.discard_two,\r\n" + 
						"   a.discard_three,\r\n" + 
						"   a.discard_four,\r\n" + 
						"   a.discard_five\r\n" + 
						" \r\n" + 
						" from tb_tms_mvcr_parta_dtl part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.ba_no = bd.ba_no\r\n" + 
						" left join banowise_discard_calculation a on bd.ba_no= a.bano\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(mct::varchar,1,4) = b.mct_main_id\r\n" + 
						" left join tms_bveh_mvcr_update_state_view e on  u.sus_no = e.sus_no\r\n" + 
						" left join mct_discard_criteria d on d.mctno::text = b.mct_main_id and e.type_of_force = d.type_of_force\r\n" + 
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						" left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						" left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where "+whr+" and part.issue_type!='4' ";
				
			}
           if(type.equals("C")) {
        	   if(kms >0) {whr += " and round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) >= ? ";}
				if(vintage >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 >= ? ";}
        	   
        	  /* sql = "select b.mct_nomen,\r\n" + 
   					"	c.short_form as comd,\r\n" + 
   					"	corps.coprs_name as corps,\r\n" + 
   					"	div.div_name as div,\r\n" + 
   					"	bde.bde_name as bde,\r\n" + 
   					"	u.unit_name,u.sus_no,part.em_no as ba_no,\r\n"+
   					"   round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,  \r\n"+
   					"    cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage ,part.classification as classification\r\n"+
   					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
   					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
   					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
   					"inner join tb_tms_emar_report part on u.sus_no = part.sus_no\r\n"+
    					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
   					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
   					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
   					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
   					" where "+whr+ 
   					" group by 1,2,3,4,5,6 ,7,8,9,10,11 \r\n" +
   					" order by u.unit_name";*/
				sql = "select DISTINCT \r\n" + 
						" 	b.mct_nomen,\r\n" + 
						" 	f.std_nomclature,\r\n"+
						"	c.short_form as comd,\r\n" + 
						"	corps.coprs_name as corps,\r\n" + 
						"	div.div_name as div,\r\n" + 
						"	bde.bde_name as bde,\r\n" + 
						" 	u.unit_name,\r\n" + 
						"	u.sus_no,\r\n" + 
						"	part.em_no as ba_no,\r\n" + 
						"    round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*0),0) as kms_run ,    \r\n" + 
						"   cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 as vintage,part.classification as classification\r\n" + 
						" \r\n" + 
						" from tb_tms_emar_report part\r\n" + 
						" inner join tb_tms_banum_dirctry bd on part.em_no = bd.ba_no\r\n" + 
						" inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and part.sus_no = u.sus_no\r\n" + 
						" inner join tb_tms_mct_main_master b on  substr(mct::varchar,1,4) = b.mct_main_id\r\n" + 
						"  inner join tms_cveh_mcr_update_state_view e on u.sus_no = e.sus_no \r\n" +
						" inner join tb_tms_mct_master f on f.mct = bd.mct" + 
						" left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n" + 
						" left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n" + 
						" left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n" + 
						" left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n" + 
						" where "+whr+" ";
				
			}
			 stmt = conn.prepareStatement(sql);
			
			int flag = 0;
			if(!mct_main_list.equals("")){
				String[] mct_main_array = mct_main_list.split(":");
				for(int i=0;i<mct_main_array.length;i++) {
					flag = flag + 1;
					stmt.setString(flag,mct_main_array[i]);
				}
			}
			if(!sus_no.equals("")) {
				flag = flag + 1;
				stmt.setString(flag,sus_no);
			}else {
				if(!cmd.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,cmd+"%");
				}
			}
			/*if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, line_dte_sus1);
        	}*/
			if(!type_of_force.equals("") & !type_of_force.equals("0")){
				flag = flag + 1;
        		stmt.setString(flag, type_of_force);
			}
			if(kms >0) {
				flag = flag + 1;
				stmt.setInt(flag, kms);
			}
			if(vintage >0) {
				flag = flag + 1;
				stmt.setInt(flag, vintage);
			}
		
			System.out.println("-----"+stmt);
			
			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));//0
				list1.add(rs.getString("corps"));//1
				list1.add(rs.getString("div"));//2
				list1.add(rs.getString("bde"));//3
				list1.add(rs.getString("unit_name"));//4
				list1.add(rs.getString("sus_no"));//5
				list1.add(rs.getString("mct_nomen"));//6
				list1.add(rs.getString("ba_no"));//7
				list1.add(rs.getString("kms_run"));//8
				list1.add(rs.getString("vintage"));//9
				list1.add(rs.getString("classification"));//10			
				list1.add(rs.getString("std_nomclature"));//11	
				if(type.equals("B")) {
				list1.add(rs.getString("discard_currentyear"));//12
				list1.add(rs.getString("discard_one"));//13
				list1.add(rs.getString("discard_two"));//14
				list1.add(rs.getString("discard_three"));//15
				list1.add(rs.getString("discard_four"));//16
				list1.add(rs.getString("discard_five"));//17
				list1.add(rs.getString("km_criteria"));//18
				list1.add(rs.getString("vintage_criteria"));//19
				}
				list11.add(list1);
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
		return list11;
	}

		
}