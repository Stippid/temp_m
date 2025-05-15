package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class vehicleDetailsDAOImp implements vehicleDetailsDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> vehiclestatusDetails_line_dte(String cmd,String mct_main_list,String prf_code ,String type,String sus_no,String line_dte_sus1) {
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
		//naren
		if(!prf_code.equals("0")) {
			
			String prfcode=prf_code.replaceAll(",","','");
			
			if(prf_code.toUpperCase().indexOf("XNR")<=-1) {
				whr += " and a.prf_code in ('"+prfcode+"') ";
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
    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
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
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					" where a.type = ? "+whr+ 
					" group by 1,2,3,4,5,6 \r\n" +
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
	System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh: " + stmt);
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
			String sql = "select b.mct_nomen,\r\n" + 
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
	
	
	
	  public ArrayList<List<String>> getAllPRFList() {
	        ArrayList<List<String>> alist = new ArrayList<List<String>>();
	        Connection conn = null;
	        try {

	            conn = dataSource.getConnection();
	            String sql = "select distinct s.prf_code,s.group_name from tb_tms_mct_slot_master s\r\n"
	                    + "inner join tb_tms_mct_master m on s.prf_code=m.prf_group and m.status = 'ACTIVE' order by   s.group_name";
	            PreparedStatement stmt = conn.prepareStatement(sql);

	            ResultSet rs1 = stmt.executeQuery();
	            while (rs1.next()) {
	                ArrayList<String> list = new ArrayList<String>();
	                list.add(rs1.getString("prf_code"));
	                list.add(rs1.getString("group_name"));
	                alist.add(list);
	            }
	            rs1.close();
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return alist;
	    }

	  public ArrayList<ArrayList<String>> transport_ue(String cmd,String mct_main_list,String prf_code ,String sus_no,String line_dte_sus1, String type_force12) {
			String whr ="";
			if(!mct_main_list.equals("")) {
						whr +=" and a.mct_main_id = ? ";	
			}
			//naren
			if(!prf_code.equals("")) {
					whr += " and a.prf_code = ? ";
			}
			if(!sus_no.equals("")) {
				whr += " and a.sus_no = ? ";
			}else {
				if(!cmd.equals("")) {
					whr += " and a.form_code_control like ? ";
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
	    		whr +=" and u.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
	    	}
			System.out.println("if: " + type_force12);
			if (!type_force12.equals("")) {
				whr += " and u.type_force = ? ";
			}
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "select b.mct_nomen,\r\n"
						+ "      c.short_form as comd,\r\n"
						+ "      corps.coprs_name as corps,\r\n"
						+ "      div.div_name as div,\r\n"
						+ "      bde.bde_name as bde,\r\n"
						+ "      u.unit_name,\r\n"
						+ "      sum(a.ue) as ue\r\n"
						+ "from tms_vehicle_status_a_b_c_with_ue_uh a\r\n"
						+ "inner join tb_tms_mct_main_master b on     b.mct_main_id = a.mct_main_id\r\n"
						+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n"
						+ "left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n"
						+ "left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n"
						+ "left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n"
						+ "left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n"
						+ " where u.status_sus_no = 'Active'" +whr+ 
						" group by 1,2,3,4,5,6 \r\n" +
						" order by u.unit_name";

				PreparedStatement stmt = conn.prepareStatement(sql);
				
				int flag = 0;
				
				if(!mct_main_list.equals("") & !mct_main_list.equals("0")){
					flag = flag + 1;
	        		stmt.setString(flag, mct_main_list);
	        	}
				if(!prf_code.equals("") & !prf_code.equals("0")){
					flag = flag + 1;
	        		stmt.setString(flag, prf_code);
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
				if(!type_force12.equals("") ){
					flag = flag + 1;
	        		stmt.setString(flag, type_force12);
	        	}
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh: " + stmt);
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
//					list1.add(rs.getString("against_ue_s"));//7
//					list1.add(rs.getString("against_ue_u"));//8
//					list1.add(rs.getString("over_ue_s"));//9
//					list1.add(rs.getString("over_ue_u"));//10
//					list1.add(rs.getString("loan_s"));//11
//					list1.add(rs.getString("loan_u"));//12
//					list1.add(rs.getString("sec_store_s"));//13
//					list1.add(rs.getString("sec_store_u"));//14
//					list1.add(rs.getString("acsfp_s"));//15
//					list1.add(rs.getString("acsfp_u"));//16
//					list1.add(rs.getString("pbd_s"));//17
//					list1.add(rs.getString("pbd_u"));//18
//					list1.add(rs.getString("fresh_release"));//19
//					list1.add(rs.getString("gift_s"));//20
//					list1.add(rs.getString("gift_u"));//21
//					list1.add(rs.getString("total_uh_s"));//22
//					list1.add(rs.getString("total_uh_u"));//23
//					list1.add(rs.getString("total_uh"));//24
					
					
					int uh = 0;
					int ue = 0;
					
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
//					
//					list1.add(rs.getString("op_s"));//27
//					list1.add(rs.getString("op_u"));//28
//					list1.add(rs.getString("trg_s"));//29
//					list1.add(rs.getString("trg_u"));//30
//					list1.add(rs.getString("wwr_s"));//31
//					list1.add(rs.getString("wwr_u"));//32
//					
//					list1.add(rs.getString("opwks_s"));//33
//					list1.add(rs.getString("opwks_u"));//34
//					list1.add(rs.getString("other_s"));//35
//					list1.add(rs.getString("other_u"));//36
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
	
	
	  public String formatForSqlIn(String values) {
			if (values == null || values.trim().isEmpty()) {
				return "";
			}

			String[] items = values.split(",");

			return Arrays.stream(items)
					.map(String::trim)
					.map(item -> "'" + item + "'")
					.collect(Collectors.joining(","));
		}


	  @Override
		public List<Map<String, Object>> transport_ue(Integer startPage,String pageLength,String Search,String orderColunm,String orderType,
				String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12) {
			String whr ="";
			
			String pageL = "";
  	        
  	        if(Integer.parseInt(pageLength) == -1){
  				pageL = "ALL";
  			}else {
  				pageL = String.valueOf(pageLength);
  			}
			
			if(!Search.equals("")) {
				whr =" and  ";
				whr +="( upper(group_name) like ? or upper(mct_nomen) like ? ) ";
			}
			
			whr += " where comd_sus is not null ";
			
			if (!type_force12.equals("")) {
				whr += " and type_of_force = ? ";
			}

			if(!prf_code.equals("") && !prf_code.equals("0") && !prf_code.equals("ALL")) {
				whr += " and prf_code = ? ";
			}
			if(!sus_no.equals("")) {
				whr += " and unit_sus = ? ";
			}else {
				if(!cont_comd12.equals("")) {
					whr += " and substring(form_code_control,1,1) = ? ";
				}
				if(!cont_corps12.equals("0")) {
					whr += " and substring(form_code_control,1,3) = ? ";
				}
				if(!cont_div12.equals("0")) {
					whr += " and substring(form_code_control,1,6) = ? ";
				}
				if(!cont_bde12.equals("0")) {
					whr += " and form_code_control =  ? ";
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				whr +=" and arm_code in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
			}


			if (!std_nomclature12.equals("")) {
				String formattedValues = formatForSqlIn(std_nomclature12);
				whr += " and mct_no in (" + formattedValues + ") ";
			}

			if (!we_pe_no12.equals("")) {
				whr += " and we_pe_no = ? ";
				if (!table_title12.equals("")) {
					whr += " and table_title = ? ";
				}

				
			}

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "select distinct group_name,\r\n" + 
						"COALESCE (comd_name,'') AS comd_name,\r\n" + 
						"COALESCE (corps_name,'') AS corps_name,\r\n" + 
						"COALESCE (div_name,'') AS div_name,\r\n" + 
						"COALESCE (bde_name,'') AS bde_name,\r\n" + 
						"unit_name,\r\n" + 
						"mct_nomen,\r\n" + 
						"we_pe,\r\n" + 
						"we_pe_no,\r\n" + 
						"COALESCE(modif,'') as modification,\r\n" + 
						"base,\r\n" + 
						"mod,\r\n" + 
						"gennotes,\r\n" + 
						"total\r\n" + 	
						"from ue_tpt_for_zoho_new_matview \r\n"
						+ whr + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageL+" OFFSET "+startPage;

				PreparedStatement stmt = conn.prepareStatement(sql);

				int flag = 0;

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

				}

				if(!type_force12.equals("") ){
					if(type_force12.equals("0")) {
						type_force12 = "FF";
					} else if (type_force12.equals("1")) {
						type_force12 = "NFF";
					} else if (type_force12.equals("2")) {
						type_force12 = "FFC";
					} else if (type_force12.equals("3")) {
						type_force12 = "TRG EST";
					}

					flag = flag + 1;
					stmt.setString(flag, type_force12);
				}

				if(!prf_code.equals("") & !prf_code.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, prf_code);
				}

				if(!sus_no.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,sus_no);
				}else {
					if(!cont_comd12.equals("")) {
						/*flag = flag + 1;
						stmt.setString(flag,cont_comd12+"%");*/
						flag = flag + 1;
						stmt.setString(flag,cont_comd12);

					}
					if(!cont_corps12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_corps12);

					}
					if(!cont_div12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_div12);

					}
					if(!cont_bde12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_bde12);

					}
				}

				if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, line_dte_sus1);
				}

				if(!we_pe_no12.equals("") ){
					flag = flag + 1;
					stmt.setString(flag, we_pe_no12);
					if(!table_title12.equals("")) {
						flag = flag + 1;
						stmt.setString(flag, table_title12);
					}
					
				}

				System.out.println("Transport UE Table: " + stmt);
				ResultSet rs = stmt.executeQuery();
				int sur = 0;
				int defi = 0;
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

		@Override
		public long transport_ue_count(String Search, String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12) {
			int total = 0;
			String whr ="";

			if(!Search.equals("")) {
				whr =" and  ";
				whr +="( upper(group_name) like ? or upper(mct_nomen) like ? ) ";
			}
			
			whr += " where comd_sus is not null ";
			
			if (!type_force12.equals("")) {
				whr += " and type_of_force = ? ";
			}

			if(!prf_code.equals("") && !prf_code.equals("0") && !prf_code.equals("ALL")) {
				whr += " and prf_code = ? ";
			}
			if(!sus_no.equals("")) {
				whr += " and unit_sus = ? ";
			}else {
				if(!cont_comd12.equals("")) {
					whr += " and substring(form_code_control,1,1) = ? ";
				}
				if(!cont_corps12.equals("0")) {
					whr += " and substring(form_code_control,1,3) = ? ";
				}
				if(!cont_div12.equals("0")) {
					whr += " and substring(form_code_control,1,6) = ? ";
				}
				if(!cont_bde12.equals("0")) {
					whr += " and form_code_control =  ? ";
				}
			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				whr +=" and arm_code in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
			}


			if (!std_nomclature12.equals("")) {
				String formattedValues = formatForSqlIn(std_nomclature12);
				whr += " and mct_no in (" + formattedValues + ") ";
			}

			if (!we_pe_no12.equals("")) {
				whr += " and we_pe_no = ? ";
				if (!table_title12.equals("")) {
					whr += " and table_title = ? ";
				}

				
			}

			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "select count (app.*) from (select distinct group_name,\r\n" + 
						"COALESCE (comd_name,'') AS comd_name, \r\n" + 
						"COALESCE (corps_name,'') AS corps_name, \r\n" + 
						"COALESCE (div_name,'') AS div_name, \r\n" + 
						"COALESCE (bde_name,'') AS bde_name, \r\n" + 
						"unit_name, \r\n" + 
						"mct_nomen,\r\n" + 
						"we_pe,\r\n" + 
						"we_pe_no,\r\n" + 
						"COALESCE(modif,'') as modification, \r\n" + 
						"base, \r\n" + 
						"mod, \r\n" + 
						"gennotes, \r\n" + 
						"total \r\n" + 
						"from ue_tpt_for_zoho_new_matview " +whr+ ") app";

				PreparedStatement stmt = conn.prepareStatement(sql);

				int flag = 0;

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

				}

				if(!type_force12.equals("") ){
					if(type_force12.equals("0")) {
						type_force12 = "FF";
					} else if (type_force12.equals("1")) {
						type_force12 = "NFF";
					} else if (type_force12.equals("2")) {
						type_force12 = "FFC";
					} else if (type_force12.equals("3")) {
						type_force12 = "TRG EST";
					}

					flag = flag + 1;
					stmt.setString(flag, type_force12);
				}

				if(!prf_code.equals("") & !prf_code.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, prf_code);
				}

				if(!sus_no.equals("")) {
					flag = flag + 1;
					stmt.setString(flag,sus_no);
				}else {
/*					if(!cont_comd12.equals("")) {
						flag = flag + 1;
						stmt.setString(flag,cont_comd12+"%");
					}
					if(!cont_corps12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_corps12+"%");
					}
					if(!cont_div12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_div12+"%");
					}
					if(!cont_bde12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_bde12+"%");
					}*/
					
					if(!cont_comd12.equals("")) {
						/*flag = flag + 1;
						stmt.setString(flag,cont_comd12+"%");*/
						flag = flag + 1;
						stmt.setString(flag,cont_comd12);

					}
					if(!cont_corps12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_corps12);

					}
					if(!cont_div12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_div12);

					}
					if(!cont_bde12.equals("0")) {
						flag = flag + 1;
						stmt.setString(flag,cont_bde12);

					}
				}

				if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, line_dte_sus1);
				}

				if(!we_pe_no12.equals("") ){
					flag = flag + 1;
					stmt.setString(flag, we_pe_no12);
					if(!table_title12.equals("")) {
						flag = flag + 1;
						stmt.setString(flag, table_title12);
					}
					
				}

				System.out.println("Transport UE count: " + stmt);
				ResultSet rs = stmt.executeQuery();
				int sur = 0;
				int defi = 0;
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
			return total;
		}

		
		@Override
		public List<Map<String, Object>> transport_ue_summary(Integer startPage,String pageLength,String Search,String orderColunm,String orderType,
				String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12) {
			String whr ="";
			System.err.println("comd " + cont_comd12 );
			System.err.println("comd " + cont_corps12 );
			System.err.println("div " + cont_div12 );
			System.err.println("bde " + cont_bde12 );
			System.err.println("prf code  " + prf_code );
			System.err.println("sus no  " + sus_no );
			System.err.println("line dte" + line_dte_sus1 );
			System.err.println("force type  " + type_force12 );
			System.err.println("eff " + eff_to12 );
			System.err.println("mod  " + modification12 );
			System.err.println("wepe no " + we_pe_no12 );
			System.err.println("table title " + table_title12 );
			System.err.println("nomen  " + std_nomclature12 );
			if(!Search.equals("")) {
				whr =" and  ";
				whr +="( upper(group_name) like ? or upper(mct_nomen) like ? ) ";
			}
			
			String pageL = "";
	        
	        if(Integer.parseInt(pageLength) == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
	        
	        whr += " where comd_sus is not null ";
	        
	        if (!type_force12.equals("")) {
				whr += " and type_of_force = ? ";
			}
	        
			if(!prf_code.equals("") && !prf_code.equals("0") && !prf_code.equals("ALL")) {
				whr += " and prf_code = ? ";
			}
			if(!sus_no.equals("")) {
  				whr += " and unit_sus = ? ";
  			}else {
  				if(!cont_comd12.equals("")) {
  					whr += " and substring(form_code_control,1,1) = ? ";
  				}
  				if(!cont_corps12.equals("0")) {
  					whr += " and substring(form_code_control,1,3) = ? ";
  				}
  				if(!cont_div12.equals("0")) {
  					whr += " and substring(form_code_control,1,6) = ? ";
  				}
  				if(!cont_bde12.equals("0")) {
  					whr += " and form_code_control =  ? ";
  				}
  			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				whr +=" and arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
			}
			

			if (!std_nomclature12.equals("")) {
				String formattedValues = formatForSqlIn(std_nomclature12);
				whr += " and mct_no in (" + formattedValues + ") ";
			}

			if (!we_pe_no12.equals("")) {
				whr += " and we_pe_no = ? ";
				if (!table_title12.equals("")) {
					whr += " and table_title = ? ";
				}

				
			}

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				String sql = " \r\n" + 
						" select prf_code,group_name,mct_no,mct_nomen, \r\n" + 
						" coalesce (sum(total) filter (where type_of_force='FF'),0) as FF, \r\n" + 
						" coalesce (sum(total) filter (where type_of_force in ('NFF','TRG EST','FFC')),0) as NFF ,\r\n" + 
						" round ((case when prf_code in ('101') and type_of_veh ='B' then sum(total)*10/100\r\n" + 
						"  	   when prf_code in ('102','103','130') and type_of_veh ='B' then sum(total)*5/100 \r\n" + 
						"  	   when prf_code not in ('101','102','103','130') and type_of_veh ='B' then sum(total)*15/100 \r\n" + 
						"  	   else 0 end),0) as reserve,											\r\n" + 
						" round (coalesce (sum(total) filter (where type_of_force='FF'),0) + \r\n" + 
						" coalesce (sum(total) filter (where type_of_force in ('NFF','TRG EST','FFC')),0) +\r\n" + 
						"(case when prf_code in ('101') and type_of_veh ='B' then sum(total)*10/100\r\n" + 
						"  	   when prf_code in ('102','103','130') and type_of_veh ='B' then sum(total)*5/100 \r\n" + 
						"  	   when prf_code not in ('101','102','103','130') and type_of_veh ='B' then sum(total)*15/100 \r\n" + 
						"  	   else 0 end))  as Total \r\n" + 
						" from ue_tpt_for_zoho_new_matview \r\n" + whr +
						//" group by type_of_veh,prf_code,group_name,mct_no,mct_nomen\r\n" + 
						//" order by type_of_veh,prf_code,mct_no" +whr+
						" group by type_of_veh,prf_code,group_name,mct_no,mct_nomen\r\n" + 
						" ORDER BY type_of_veh,prf_code,mct_no"+" "+" limit " +pageL+" OFFSET "+startPage;

				PreparedStatement stmt = conn.prepareStatement(sql);

				int flag = 0;

				if(!Search.equals("")) {
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");

					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
				}
				
				if(!type_force12.equals("") ){
  					if(type_force12.equals("0")) {
  						type_force12 = "FF";
  					} else if (type_force12.equals("1")) {
  						type_force12 = "NFF";
  					} else if (type_force12.equals("2")) {
  						type_force12 = "FFC";
  					} else if (type_force12.equals("3")) {
  						type_force12 = "TRG EST";
  					}

  					flag = flag + 1;
  					stmt.setString(flag, type_force12);
  				}

				if(!prf_code.equals("") & !prf_code.equals("0") && !prf_code.equals("ALL")){
					flag = flag + 1;
					stmt.setString(flag, prf_code);
				}
				if(!sus_no.equals("")) {
  					flag = flag + 1;
  					stmt.setString(flag,sus_no);
  				}else {
  					if(!cont_comd12.equals("")) {
  						/*flag = flag + 1;
  						stmt.setString(flag,cont_comd12+"%");*/
  						flag = flag + 1;
  						stmt.setString(flag,cont_comd12);

  					}
  					if(!cont_corps12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_corps12);

  					}
  					if(!cont_div12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_div12);

  					}
  					if(!cont_bde12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_bde12);

  					}
  				}
				if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, line_dte_sus1);
				}
				

				if(!we_pe_no12.equals("") ){
					flag = flag + 1;
					stmt.setString(flag, we_pe_no12);
					if(!table_title12.equals("")) {
						flag = flag + 1;
						stmt.setString(flag, table_title12);
					}
					
				}

				System.out.println("Transport Summary Table: " + stmt);
				ResultSet rs = stmt.executeQuery();
				int sur = 0;
				int defi = 0;
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

		@Override
		public long transport_ue_summary_count(String Search, String cont_comd12, String cont_corps12, String cont_div12, String cont_bde12, String prf_code,String sus_no,String line_dte_sus1,String type_force12,
				String eff_from12, String eff_to12, String modification12, String we_pe_no12, String table_title12, String std_nomclature12) {
			int total = 0;
			String whr ="";

			if(!Search.equals("")) {
				whr =" and  ";
				whr +="( upper(group_name) like ? or upper(mct_nomen) like ? ) ";
			}
			
			String pageL = "";
	        
			whr += " where comd_sus is not null ";
			
	        if (!type_force12.equals("")) {
				whr += " and type_of_force = ? ";
			}
	        
			if(!prf_code.equals("") && !prf_code.equals("0") && !prf_code.equals("ALL")) {
				whr += " and prf_code = ? ";
			}
			if(!sus_no.equals("")) {
  				whr += " and unit_sus = ? ";
  			}else {
  				if(!cont_comd12.equals("")) {
  					whr += " and substring(form_code_control,1,1) = ? ";
  				}
  				if(!cont_corps12.equals("0")) {
  					whr += " and substring(form_code_control,1,3) = ? ";
  				}
  				if(!cont_div12.equals("0")) {
  					whr += " and substring(form_code_control,1,6) = ? ";
  				}
  				if(!cont_bde12.equals("0")) {
  					whr += " and form_code_control =  ? ";
  				}
  			}
			if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
				whr +=" and arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
			}
			

			if (!std_nomclature12.equals("")) {
				String formattedValues = formatForSqlIn(std_nomclature12);
				whr += " and mct_no in (" + formattedValues + ") ";
			}

			if (!we_pe_no12.equals("")) {
				whr += " and we_pe_no = ? ";
				if (!table_title12.equals("")) {
					whr += " and table_title = ? ";
				}

				
			}

			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				String sql = "select count (app.*) from( \r\n" + 
						" select prf_code,group_name,mct_no,mct_nomen, \r\n" + 
						" coalesce (sum(total) filter (where type_of_force='FF'),0) as FF, \r\n" + 
						" coalesce (sum(total) filter (where type_of_force in ('NFF','TRG EST','FFC')),0) as NFF ,\r\n" + 
						" round ((case when prf_code in ('101') and type_of_veh ='B' then sum(total)*10/100\r\n" + 
						"  	   when prf_code in ('102','103','130') and type_of_veh ='B' then sum(total)*5/100 \r\n" + 
						"  	   when prf_code not in ('101','102','103','130') and type_of_veh ='B' then sum(total)*15/100 \r\n" + 
						"  	   else 0 end),0) as reserve,											\r\n" + 
						" round (coalesce (sum(total) filter (where type_of_force='FF'),0) + \r\n" + 
						" coalesce (sum(total) filter (where type_of_force in ('NFF','TRG EST','FFC')),0) +\r\n" + 
						"(case when prf_code in ('101') and type_of_veh ='B' then sum(total)*10/100\r\n" + 
						"  	   when prf_code in ('102','103','130') and type_of_veh ='B' then sum(total)*5/100 \r\n" + 
						"  	   when prf_code not in ('101','102','103','130') and type_of_veh ='B' then sum(total)*15/100 \r\n" + 
						"  	   else 0 end))  as Total \r\n" + 
						" from ue_tpt_for_zoho_new_matview \r\n" + whr +
						" group by type_of_veh,prf_code,group_name,mct_no,mct_nomen\r\n" + 
						" order by type_of_veh,prf_code,mct_no ) app";

				PreparedStatement stmt = conn.prepareStatement(sql);

				int flag = 0;

				if(!Search.equals("")) {
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");

					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
				}
				
				if(!type_force12.equals("") ){
  					if(type_force12.equals("0")) {
  						type_force12 = "FF";
  					} else if (type_force12.equals("1")) {
  						type_force12 = "NFF";
  					} else if (type_force12.equals("2")) {
  						type_force12 = "FFC";
  					} else if (type_force12.equals("3")) {
  						type_force12 = "TRG EST";
  					}

  					flag = flag + 1;
  					stmt.setString(flag, type_force12);
  				}

				if(!prf_code.equals("") && !prf_code.equals("0") && !prf_code.equals("ALL")){
					flag = flag + 1;
					stmt.setString(flag, prf_code);
				}
				if(!sus_no.equals("")) {
  					flag = flag + 1;
  					stmt.setString(flag,sus_no);
  				}else {
  					if(!cont_comd12.equals("")) {
  						/*flag = flag + 1;
  						stmt.setString(flag,cont_comd12+"%");*/
  						flag = flag + 1;
  						stmt.setString(flag,cont_comd12);

  					}
  					if(!cont_corps12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_corps12);

  					}
  					if(!cont_div12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_div12);

  					}
  					if(!cont_bde12.equals("0")) {
  						flag = flag + 1;
  						stmt.setString(flag,cont_bde12);

  					}
  				}
				if(!line_dte_sus1.equals("") & !line_dte_sus1.equals("0")){
					flag = flag + 1;
					stmt.setString(flag, line_dte_sus1);
				}
				

				if(!we_pe_no12.equals("") ){
					flag = flag + 1;
					stmt.setString(flag, we_pe_no12);
					if(!table_title12.equals("")) {
						flag = flag + 1;
						stmt.setString(flag, table_title12);
					}
					
				}

				System.out.println("Transport Summary count: " + stmt);
				ResultSet rs = stmt.executeQuery();
				int sur = 0;
				int defi = 0;
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
			return total;
		}


	
	
	
	
}