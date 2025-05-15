package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Veh_cat_link_DAOImpl implements Veh_cat_link_DAO {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_veh_name(String type_veh,String prf_code,String mct_main,String line_dte_sus) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			
			if (type_veh.equals("0")) {
				qry += "where a.type = 'A' ";
			}
			if (type_veh.equals("1")) {
				qry += " where a.type = 'B' ";
			}
			if (type_veh.equals("2")) {
				qry += " where a.type = 'C'  ";
			}
			/*if (!prf_code.equals("")) {
				if (qry.contains("where")) {
					qry += " and upper(a.prf_code) = ? ";
				} else {
					qry += " where upper(a.prf_code) = ? ";
				}
			}
			if (mct_main.equals("0")) {
				qry += "and a.mct_code = '?' ";
			}
			
			if (line_dte_sus.equals("0")) {
				qry += "and a.line_dte = '?' ";
			}
			*/
		   
			q = "select b.mct_nomen,\r\n" + 
					"        c.short_form as comd,\r\n" + 
					"        corps.coprs_name as corps,\r\n" + 
					"        div.div_name as div,\r\n" + 
					"        bde.bde_name as bde,\r\n" + 
					"        u.unit_name,\r\n" + 
					"        sum(a.ue) as ue,\r\n" + 
					"        sum(a.against_ue) as against_ue,\r\n" + 
					"        sum(a.over_ue) as over_ue,\r\n" + 
					"        sum(a.loan) as loan,\r\n" + 
					"        sum(sec_store) as sec_store,\r\n" + 
					"        sum(a.acsfp) as acsfp,\r\n" + 
					"        sum(a.fresh_release) as fresh_release,\r\n" + 
					"        sum(gift) as gift,\r\n" + 
					"        sum(a.total_uh) as total_uh\r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.status_sus_no = 'Active' and a.sus_no = u.sus_no\r\n" + 
					"left join orbat_view_cmd c on substr(a.form_code_control,1,1) = c.cmd_code\r\n" + 
					"left join orbat_view_corps corps on substr(a.form_code_control,2,2) = corps.corps_code\r\n" + 
					"left join orbat_view_div div on substr(a.form_code_control,4,3) = div.div_code\r\n" + 
					"left join orbat_view_bde bde on substr(a.form_code_control,7,4) = bde.bde_code\r\n" + 
					"inner join tb_tms_veh_cat_line_dte bl on  a.mct_main_id = bl.mct_code\r\n" + 
				    " "+ qry + " group by 1,2,3,4,5,6 "+
					 "order by u.unit_name ";

			
			stmt = conn.prepareStatement(q);
			int j = 1;

			/*if (!type_veh.equals("")) {
				stmt.setString(j, type_veh.toUpperCase());
				j += 1;
			}*/
		
			
			ResultSet rs = stmt.executeQuery();
			int sur = 0;
			int defi = 0;
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("mct_nomen"));
				list1.add(rs.getString("comd"));
				list1.add(rs.getString("corps"));
				list1.add(rs.getString("div"));
				list1.add(rs.getString("bde"));
				list1.add(rs.getString("unit_name"));
				list1.add(rs.getString("ue"));
				list1.add(rs.getString("against_ue"));
				list1.add(rs.getString("over_ue"));
				list1.add(rs.getString("loan"));
				list1.add(rs.getString("sec_store"));
				list1.add(rs.getString("acsfp"));
				list1.add(rs.getString("fresh_release"));
				list1.add(rs.getString("gift"));
				list1.add(rs.getString("total_uh"));
				
//				int uh = 0;
//				int ue = 0;
//				if (rs.getString("total_uh") == null) {
//					uh = 0;
//				} else {
//					uh = Integer.parseInt(rs.getString("total_uh"));
//				}
//				if (rs.getString("ue") == null) {
//					ue = 0;
//				} else {
//					ue = Integer.parseInt(rs.getString("ue"));
//				}
//				
//				if (ue >= 0 && uh >= 0) {
//					int diff = uh - ue;
//					if (diff >= 0) {
//						sur = diff;
//						defi = 0;
//					} else {
//						defi = diff;
//						sur = 0;
//					}
//				}
//				list1.add(String.valueOf(Math.abs(defi)));
//				list1.add(String.valueOf(Math.abs(sur)));
				alist.add(list1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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
