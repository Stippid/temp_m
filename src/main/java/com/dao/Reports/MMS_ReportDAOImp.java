package com.dao.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MMS_ReportDAOImp implements MMS_ReportDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> WAREHOUSE_REPORT(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + "	census_no,\r\n" + "	nomen,\r\n" + "	au,\r\n"
					+ "	sum(case when type_of_hldg='A0' then tothol else 0 end) as w_normal,\r\n"
					+ "	sum(case when type_of_hldg in ('A0') then tothol else 0 end) as w_bc,\r\n"
					+ "	sum(case when type_of_hldg='A0' then tothol else 0 end) as w_oth,\r\n"
					+ "sum(tothol) as total	,\r\n"
					+ "sum(case when type_of_hldg='D4' then tothol else 0 end) as w_depot\r\n" + "from olap_mms_wuh\r\n"
					+ "where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n"
					+ "group  by 1,2,3";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("census_no"));
				list.add(rs1.getString("nomen"));
				list.add(rs1.getString("au"));
				list.add(rs1.getString("w_normal"));
				list.add(rs1.getString("w_bc"));
				list.add(rs1.getString("w_oth"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("w_depot"));
				alist.add(list);
			}

			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	      
	public ArrayList<ArrayList<String>> ALL_INDIA_HOLDING(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + "        cos_sec,\r\n" + "        prf_group,\r\n" + "        census_no,\r\n"
					+ "        nomen,\r\n" + "        cat_part_no,\r\n" + "        au_n,\r\n"
					+ "        item_status_n,\r\n" + "        type_of_hldg_n,\r\n"
					+ "        (case when type_of_eqpt_n='MAIN EQPT' then type_of_eqpt_n else null end) as main_hold,\r\n"
					+ "        (case when type_of_eqpt_n='CES / CCES  EQPT' then type_of_eqpt_n else null end) as ces_hold,\r\n"
					+ "        sum(ue) as total_ue,\r\n" + "        sum(uh_c+uh_m) as total_uh\r\n"
					+ "from olap_mms_aih\r\n"
					+ "where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n"
					+ "group by 1,2,3,4,5,6,7,8,9,10";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("cos_sec"));
				list.add(rs1.getString("prf_group"));
				list.add(rs1.getString("census_no"));
				list.add(rs1.getString("nomen"));
				list.add(rs1.getString("cat_part_no"));
				list.add(rs1.getString("au_n"));
				list.add(rs1.getString("item_status_n"));
				list.add(rs1.getString("type_of_hldg_n"));
				list.add(rs1.getString("main_hold"));
				list.add(rs1.getString("ces_hold"));
				list.add(rs1.getString("total_ue"));
				list.add(rs1.getString("total_uh"));
				alist.add(list);
			}

			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
}
