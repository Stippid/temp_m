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

public class vehiclestatus_nodaldteDAOImp implements vehiclestatus_nodaldteDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public ArrayList<ArrayList<String>> vehiclestatusDetails_nodal_dte(String cmd,String mct_main_list,String prf_code ,String type,String sus_no,String line_dte_sus1) {
		String whr ="";
		if(!mct_main_list.equals("")) {
			String[] mct_main_array = mct_main_list.split(":");
			if(mct_main_array.length>0) {
				whr +=" and ( ";
			}
			for(int i=0;i<mct_main_array.length;i++) {
				if(i==0) {
					whr +=" a.mct_main_id = ? ";	
				}else {
					whr +=" or a.mct_main_id = ? ";
				}
			}
			if(mct_main_array.length>0) {
				whr +=" ) ";
			}
		}
		
		if(!sus_no.equals("")) {
			whr += " and a.sus_no = ? ";
		}else {
			if(!cmd.equals("")) {
				whr += " and a.form_code_control like ? ";
			}
		}
		if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
    		whr +=" and b.sus_no = ? ";
    	}
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select b.mct_nomen,\r\n" + 
					"	c.short_form as comd,\r\n" + 
					"	corps.coprs_name as corps,\r\n" + 
					"	div.div_name as div,\r\n" + 
					"	bde.bde_name as bde,\r\n" + 
					"	u.unit_name,\r\n"+ 
					"   u1.line_dte_name as line_dte,"+
					"	sum(a.ue) as ue,\r\n"+
					"	sum(a.against_ue_s) as against_ue_s,\r\n"+
					"	sum(a.against_ue_u) as against_ue_u,\r\n"+
					"	sum(a.over_ue_s) as over_ue_s,\r\n"+
					"	sum(a.over_ue_u) as over_ue_u,\r\n"+
					"	sum(a.loan_s) as loan_s,\r\n"+
					"	sum(a.loan_u) as loan_u,\r\n"+
					"	sum(sec_store_s) as sec_store_s,\r\n"+
					"	sum(sec_store_u) as sec_store_u,\r\n"+
					"	sum(a.acsfp_s) as acsfp_s,\r\n"+
					"	sum(a.acsfp_u) as acsfp_u,\r\n"+
					"	sum(a.fresh_release) as fresh_release,\r\n"+
					"	sum(gift_s) as gift_s,\r\n"+
					"	sum(gift_u) as gift_u,\r\n"+
					"	sum(pbd_s) as pbd_s,\r\n"+
					"	sum(pbd_u) as pbd_u,\r\n"+
					"	sum(op_s) as op_s,\r\n"+
					"	sum(op_u) as op_u,\r\n"+
					"	sum(trg_s) as trg_s,\r\n"+
					"	sum(trg_u) as trg_u,\r\n"+
					"	sum(wwr_s) as wwr_s,\r\n"+
					"	sum(wwr_u) as wwr_u,\r\n"+
					"	sum(opwks_s) as opwks_s,\r\n"+
					"	sum(opwks_u) as opwks_u,\r\n"+
					"	sum(others_s) as other_s,\r\n"+
					"	sum(others_u) as other_u,\r\n"+
					"	sum(a.total_uh_s) as total_uh_s,\r\n"+
					"	sum(a.total_uh_u) as total_uh_u,\r\n"+
					"	sum(a.total_uh) as total_uh\r\n"+
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code  "+
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where a.type = ? "+whr+ 
					" group by 1,2,3,4,5,6,7 \r\n" +
					" order by u.unit_name";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			int flag = 1;
			stmt.setString(flag, type);
			
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
			
			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("mct_nomen")); //0
				list1.add(rs.getString("comd")); //1
				list1.add(rs.getString("corps"));//2
				list1.add(rs.getString("div"));//3
				list1.add(rs.getString("bde"));//4
				list1.add(rs.getString("unit_name"));//5
				list1.add(rs.getString("ue"));//6
				list1.add(rs.getString("against_ue_s"));//7
				list1.add(rs.getString("against_ue_u"));//8
				list1.add(rs.getString("over_ue_s"));//9
				list1.add(rs.getString("over_ue_u"));//10
				list1.add(rs.getString("loan_s"));//11
				list1.add(rs.getString("loan_u"));//12
				list1.add(rs.getString("sec_store_s"));//13
				list1.add(rs.getString("sec_store_u"));//14
				list1.add(rs.getString("acsfp_s"));//15
				list1.add(rs.getString("acsfp_u"));//16
				list1.add(rs.getString("pbd_s"));//17
				list1.add(rs.getString("pbd_u"));//18
				list1.add(rs.getString("fresh_release"));//19
				list1.add(rs.getString("gift_s"));//20
				list1.add(rs.getString("gift_u"));//21
				list1.add(rs.getString("total_uh_s"));//22
				list1.add(rs.getString("total_uh_u"));//23
				list1.add(rs.getString("total_uh"));//24
				
				
				
				int uh = 0;
				int ue = 0;
				if(rs.getString("total_uh") == null){
					uh = 0;
				}else{
					uh = Integer.parseInt(rs.getString("total_uh"));
				}
				if(rs.getString("ue") == null){
					ue = 0;
				}else{
					ue = Integer.parseInt(rs.getString("ue"));
				}
				if(ue >= 0 && uh >= 0){
					int diff = uh - ue;
					if(diff >= 0){
						sur = diff;
						defi = 0;
					}else{
						defi = diff;
						sur = 0;
					}
				}
				list1.add(String.valueOf(Math.abs(defi))); //25 
				defi = 0; 
				list1.add(String.valueOf(Math.abs(sur))); //26
				sur = 0;
				
				list1.add(rs.getString("op_s"));//27
				list1.add(rs.getString("op_u"));//28
				list1.add(rs.getString("trg_s"));//29
				list1.add(rs.getString("trg_u"));//30
				list1.add(rs.getString("wwr_s"));//31
				list1.add(rs.getString("wwr_u"));//32
				
				list1.add(rs.getString("opwks_s"));//33
				list1.add(rs.getString("opwks_u"));//34
				list1.add(rs.getString("other_s"));//35
				list1.add(rs.getString("other_u"));//36
				list1.add(rs.getString("line_dte"));//37
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
	
	
	public List<Map<String, Object>> getMCtMain_from_prf_codenodal_dte(String type_veh,HttpSession session,String line_dte_sus)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="",whr ="",veh_type = "";
			if(type_veh.equals("0")) {veh_type ="A";}
			if(type_veh.equals("1")) {veh_type ="B";}
			if(type_veh.equals("2")) {	veh_type ="C";}
  			if(!line_dte_sus.equals("")) {
				whr = " and s.sus_no= ? \r\n";
			}
			qry ="select distinct s.mct_main_id,s.mct_nomen from tb_tms_mct_main_master s \r\n"; 
			qry +=" inner join tms_vehicle_status_a_b_c_with_ue_uh ue_uh on ue_uh.mct_main_id = s.mct_main_id and ue_uh.type::text = ? \r\n";
			qry +=" where s.mct_main_id is not null and s.mct_nomen is not null "+whr+"\r\n";
			qry +=" order by  s.mct_nomen \r\n";
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
	
	
}