package com.dao.tms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class Upload_Excel_Daoimpl implements Upload_Excel_Dao{
	
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<ArrayList<String>> getexcel_print(String ba_no, String old_chasis_no, String old_eng_no,
			String new_chasis_no, String new_eng_no) throws Exception {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			String q1 = "";
			String q2 = "";
			String q3 = "";
			String q4 = "";
			String q5 = "";
			
//			String form_code = adg.substring(0,1); 
//			if (!ba_no.equals("0")) {
//				q1 += " and ba_no like ? ";
//			}
//			if (!old_chasis_no.equals("0")) {
//				q2 += " and u.project_id = ? ";
//			}
//			if (!old_eng_no.equals("0")) {
//				q3 += " and u.sub_category_id = ? ";
//			}
//			if (!new_chasis_no.equals("") && new_chasis_no != "0") {
//				q4 += " and u.ba_em_no = ? ";
//			}
//			if (!new_eng_no.equals("") && new_eng_no != "0") {
//				q4 += " and u.ba_em_no = ? ";
//			}
//			if (!quarter.equals("0")) {
//				String temp="";
//				String[] data = quarter.split(",");
//				for(int i = 0; i < data.length;i++) {
//					if(i == 0)
//					temp = temp + "?";
//					else
//					temp = temp + ",?";
//				}
//				q5 += " and u.quarter in (" + temp + ")  ";
//			}
//			if (!quarter.equals("0")) {
//				q5 += " and u.quarter = ? ";
//			}
			
			
			q = "update Tms_Banum_Req_Child c set c.engine_no=:engine_no,c.chasis_no=:chasis_no where c.ba_no = :ba_no  " + q1 + q2 + q3 + q4 + q5 ;
//					+ "   -- where  u.project_id::character varying = ? "
//					+ "	-- and u.sub_category_id::character varying = ? and u.ba_em_no::character varying = ? and u.quarter::character varying = ? ";
			
				stmt=conn.prepareStatement(q);
//				stmt.setString(1, project_id);
//				stmt.setString(2, sub_category_id);						
//				stmt.setString(3, ba_em_no);
//				stmt.setString(4, quarter);
//				stmt.setInt(5, Integer.parseInt(status));
				int j = 1;
				String form_code = ba_no.substring(0,1); 
				if (!ba_no.equals("0")) {
					stmt.setString(j,  form_code + "%");
					j += 1;
				}
				if (!old_chasis_no.equals("0")) {
					stmt.setInt(j, Integer.parseInt(old_chasis_no));
					j += 1;
				}
				if (!old_eng_no.equals("0")) {
					stmt.setInt(j, Integer.parseInt(old_eng_no));
					j += 1;
				}
				if (!new_chasis_no.equals("") && new_chasis_no != "0") {
					stmt.setString(j, new_chasis_no);
					j += 1;
				}
				if (!new_eng_no.equals("") && new_eng_no != "0") {
					stmt.setString(j, new_eng_no);
					j += 1;
				}
//				if (!quarter.equals("0")) {
//					String[] data1 = quarter.split(",");
//					for(int i = 0; i < data1.length;i++) {
//						stmt.setObject(j, Integer.parseInt(data1[i]));
//						j += 1;
//					}
//				}
//				if (!quarter.equals("0")) {
//					stmt.setInt(j, Integer.parseInt(quarter));
//					j += 1;
//				}
			
				ResultSet rs = stmt.executeQuery();
				int i =1;  
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(String.valueOf(i++)); //0
//					list.add(rs.getString("quarter")); // 0
					list.add(rs.getString("ba_no")); // 1
					list.add(rs.getString("old_chasis_no")); // 2
					list.add(rs.getString("old_eng_no")); // 3
					list.add(rs.getString("new_chasis_no")); // 4
					list.add(rs.getString("new_eng_no")); // 5
					
					alist.add(list);
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
		return alist;
	}
}
