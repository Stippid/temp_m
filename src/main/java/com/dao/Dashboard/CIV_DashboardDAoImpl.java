package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CIV_DashboardDAoImpl  implements CIV_DashboardDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public String Getrk_gen_regular(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (!cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(",");

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(fv.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (!cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(",");

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(fvm.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (!cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(",");

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(div.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (!cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(",");

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and bde.form_code_control in (" + temp3 + ")  ";
			}
			if (!rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.designation::varchar in (" + temp4 + ")   ";
			}
			
//			if (!parent_arm1.equals("")) {
//				String temp5 = "";
//				String[] datarank = parent_arm1.split(",");
//
//				for (int i = 0; i < datarank.length; i++) {
//
//					if (i == 0) {
//
//						temp5 = temp5 + "?";
//					} else {
//
//						temp5 = temp5 + ",?";
//					}
//				}
//				q += "and c.parent_arm in (" + temp5 + ")   ";
//			}

			if (!unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			conn = dataSource.getConnection();


			qry="select t.label as classification,"
					+ " count (c.id) filter (where c.gender = 6) as male, "
					+ " count (c.id) filter (where c.gender = 7) as female"
					+ " from tb_psg_civilian_dtl_main c "
					+ "inner join T_Domain_Value t on t.codevalue=c.classification_services and domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "left JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 1) = substring(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text	\r\n"
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 3) =substring(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 6) = substring(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text \r\n"
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
			+ " where c.status in ('1','5')"
			+q+ " group by 1";
			
			
			
	
			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

		if (!cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(",");
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (!cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(",");
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (!cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(",");
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (!cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(",");
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (!rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			/*
			 * if (!parent_arm1.equals("")) {
			 * 
			 * String[] datarank = parent_arm1.split(","); for (int i = 0; i <
			 * datarank.length; i++) { stmt.setString(j, datarank[i]); j += 1;
			 * 
			 * } }
			 */
			
			if (!unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}

			ResultSet rs = stmt.executeQuery();

			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				list += "{'datacivgen': '" + rs.getString("classification") + "', 'datacivgen1': " + rs.getString("male")
						+ ", 'datacivgen2':  " + rs.getString("female") + " } ";

			}
			list += "]";
	
			rs.close();
			stmt.close();
			
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
	public String Getrk_cmdlist2(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1, String rank1,
			String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1, String regs1,
			String parent_arm1, String unit_name1) {
	
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (!cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(",");

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(fv.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (!cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(",");

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(fvm.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (!cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(",");

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(div.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (!cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(",");

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and bde.form_code_control in (" + temp3 + ")  ";
			}
			if (!rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.designation::varchar in (" + temp4 + ")   ";
			}
			
//			if (!parent_arm1.equals("")) {
//				String temp5 = "";
//				String[] datarank = parent_arm1.split(",");
//
//				for (int i = 0; i < datarank.length; i++) {
//
//					if (i == 0) {
//
//						temp5 = temp5 + "?";
//					} else {
//
//						temp5 = temp5 + ",?";
//					}
//				}
//				q += "and c.parent_arm in (" + temp5 + ")   ";
//			}

			if (!unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			conn = dataSource.getConnection();


			qry=" SELECT fv.unit_name AS command, "
					+ " count(c.id) filter (where t.label='GAZETTED' ) as GAZETTED, "
					+ "  count(c.id) filter (where t.label='NON GAZETTED' ) as NON_GAZETTED "
					+ " from tb_psg_civilian_dtl_main c \r\n"
					+ " inner join T_Domain_Value t on t.codevalue=c.classification_services and domainid='CLASSIFICATION_OF_SERVICES'  "
					+ "left JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.sus_no::text AND orb.status_sus_no::text = 'Active'::text "
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 1) = substring(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text	 "
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 3) =substring(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text "
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 6) = substring(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text "
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text "
					+ " where c.status in ('1','5')"
			+q+ " group by 1";
			
			
			

			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

		if (!cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(",");
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (!cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(",");
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (!cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(",");
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (!cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(",");
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (!rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			/*
			 * if (!parent_arm1.equals("")) {
			 * 
			 * String[] datarank = parent_arm1.split(","); for (int i = 0; i <
			 * datarank.length; i++) { stmt.setString(j, datarank[i]); j += 1;
			 * 
			 * } }
			 */
			
			if (!unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
		
			ResultSet rs = stmt.executeQuery();

			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				list += "{'datacivcomd': '" + rs.getString("command") + "', 'datacivcomd1': " + rs.getString("GAZETTED")
						+ ", 'datacivcomd2':  " + rs.getString("NON_GAZETTED") + " } ";

			}
			list += "]";
			
			rs.close();
			stmt.close();
//			conn.close();
			
			  } catch (SQLException e) { e.printStackTrace(); } finally { if (conn != null)
			  { try { conn.close(); } catch (SQLException e) { } } }
			 
	
		return list;
	}
	
	public String Getrk_gazelist(String cont_comd1, String cont_corps1, String cont_div1, String cont_bde1,
			String rank1, String arm1, String parm1, String cmd1, String div1, String corps1, String bdes1,
			String regs1,String parent_arm1,String unit_name1) {
		String list = "";
		Connection conn = null;
		String qry = "";

		String q = "";
		try {

			if (!cont_comd1.equals("")) {
				String temp = "";
				String[] datacmd = cont_comd1.split(",");

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				q += "and SUBSTR(fv.form_code_control,1,1) in (" + temp + ")  ";
			}
			if (!cont_corps1.equals("")) {
				String temp1 = "";
				String[] datacr = cont_corps1.split(",");

				for (int i = 0; i < datacr.length; i++) {

					if (i == 0) {

						temp1 = temp1 + "?";
					} else {

						temp1 = temp1 + ",?";
					}
				}

				q += "and SUBSTR(fvm.form_code_control,1,3) in (" + temp1 + ")  ";
			}
			if (!cont_div1.equals("")) {
				String temp2 = "";
				String[] datadiv = cont_div1.split(",");

				for (int i = 0; i < datadiv.length; i++) {

					if (i == 0) {

						temp2 = temp2 + "?";
					} else {

						temp2 = temp2 + ",?";
					}
				}
				q += "and SUBSTR(div.form_code_control,1,6) in (" + temp2 + ") ";
			}
			if (!cont_bde1.equals("")) {
				String temp3 = "";
				String[] databde = cont_bde1.split(",");

				for (int i = 0; i < databde.length; i++) {

					if (i == 0) {

						temp3 = temp3 + "?";
					} else {

						temp3 = temp3 + ",?";
					}
				}
				q += "and bde.form_code_control in (" + temp3 + ")  ";
			}
			if (!rank1.equals("")) {

				String temp4 = "";
				String[] datarank = rank1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp4 = temp4 + "?";
					} else {

						temp4 = temp4 + ",?";
					}
				}
				q += "and c.designation::varchar in (" + temp4 + ")   ";
			}
			
			/*
			 * if (!parent_arm1.equals("")) { String temp5 = ""; String[] datarank =
			 * parent_arm1.split(",");
			 * 
			 * for (int i = 0; i < datarank.length; i++) {
			 * 
			 * if (i == 0) {
			 * 
			 * temp5 = temp5 + "?"; } else {
			 * 
			 * temp5 = temp5 + ",?"; } } q += "and c.parent_arm in (" + temp5 + ")   "; }
			 */

			if (!unit_name1.equals("")) {

				String temp6 = "";
				String[] datarank = unit_name1.split(",");

				for (int i = 0; i < datarank.length; i++) {

					if (i == 0) {

						temp6 = temp6 + "?";
					} else {

						temp6 = temp6 + ",?";
					}
				}
				q += "and orb.unit_name::varchar in (" + temp6 + ")   ";
			}
			conn = dataSource.getConnection();
		

			qry = "select t.label as classification, "
					+ " count (c.id) filter (where c.civ_group = 'A') as A,  "
					+ " count (c.id) filter (where c.civ_group = 'B') as B, "
					+ " count (c.id) filter (where c.civ_group = 'C') as C,  "
					+ " count (c.id) filter (where c.civ_group = 'D') as D  "
					+ " from tb_psg_civilian_dtl_main c "
					+"inner join T_Domain_Value t on t.codevalue=c.classification_services and domainid='CLASSIFICATION_OF_SERVICES' \r\n"
					+ "left JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = c.sus_no::text AND orb.status_sus_no::text = 'Active'::text\r\n"
					+ "LEFT JOIN all_fmn_view fv ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 1) = substring(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = 'Command'::text	\r\n"
					+ "LEFT JOIN all_fmn_view fvm ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 3) =substring(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = 'Corps'::text\r\n"
					+ "LEFT JOIN all_fmn_view div ON orb.sus_no::text = c.sus_no::text AND substring(orb.form_code_control::text, 1, 6) = substring(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = 'Division'::text \r\n"
					+ "LEFT JOIN all_fmn_view bde ON orb.sus_no::text = c.sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = 'Brigade'::text\r\n"
					+ " where c.status in ('1','5') "+q
					+ "group by  t.label  ";

	
			PreparedStatement stmt = conn.prepareStatement(qry);
			int j = 1;

			if (!cont_comd1.equals("")) {

				String[] datacmd = cont_comd1.split(",");
				for (int i = 0; i < datacmd.length; i++) {
					stmt.setString(j, datacmd[i]);
					j += 1;

				}
			}
			if (!cont_corps1.equals("")) {

				String[] datacr = cont_corps1.split(",");
				for (int i = 0; i < datacr.length; i++) {
					stmt.setString(j, datacr[i]);
					j += 1;

				}

			}

			if (!cont_div1.equals("")) {

				String[] datadiv = cont_div1.split(",");
				for (int i = 0; i < datadiv.length; i++) {
					stmt.setString(j, datadiv[i]);
					j += 1;

				}
			}
			if (!cont_bde1.equals("")) {

				String[] databde = cont_bde1.split(",");
				for (int i = 0; i < databde.length; i++) {
					stmt.setString(j, databde[i]);
					j += 1;

				}
			}
			if (!rank1.equals("")) {

				String[] datarank = rank1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
//			if (!parent_arm1.equals("")) {
//
//				String[] datarank = parent_arm1.split(",");
//				for (int i = 0; i < datarank.length; i++) {
//					stmt.setString(j, datarank[i]);
//					j += 1;
//
//				}
//			}
			
			if (!unit_name1.equals("")) {

				String[] datarank = unit_name1.split(",");
				for (int i = 0; i < datarank.length; i++) {
					stmt.setString(j, datarank[i]);
					j += 1;

				}
			}
			
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx = 0;
			while (rs.next()) {

				if (rowx > 0) {
					list += ",";
				} else {
					rowx++;
				}
				

				list += "{'datagrp': '" + rs.getString("classification") + "','datagrp1': " + rs.getString("A")
				+ ",'datagrp2': " + rs.getString("B") + ",'datagrp3': " + rs.getString("C") + ","
				+ "'datagrp4': " + rs.getString("D") +  " } ";
				
				
			}
			list += "]";

			rs.close();
			stmt.close();
//			conn.close();
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
	

}
