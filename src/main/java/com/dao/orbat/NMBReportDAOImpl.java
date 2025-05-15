package com.dao.orbat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class NMBReportDAOImpl implements NMBReportDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> getReliefReportList(String period_from, String period_to, String status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			String qry = "";
			if (!status.equals("") & !status.equals("")) {
				if (status.equals("pending")) {
					qry += " sd_status='1' and unit_status=? and (miso_status is null or miso_status = '') ";
					status = "0";
				} else if (status.equals("approve")) {
					qry += " sd_status='1' and unit_status=? and (miso_status is null or miso_status = '')";
					status = "1";
				} else {
					qry += " sd_status=? and (unit_status is null or unit_status='') "

							+ " and (miso_status is null or miso_status = '') ";
				}
			}
			sql1 = "SELECT distinct *FROM view_relief_report where nmb_date between cast(? as Date) and cast(? as Date) and "
					+ qry + "order by frm_cmd_name";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, period_from);
			stmt.setString(2, period_to);
			stmt.setString(3, status);

			System.out.println("ddd"+ stmt);
			SimpleDateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat outputDate = new SimpleDateFormat("dd-MM-yyyy");
	        String formattedDate = "";
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString(1));
				list.add(rs1.getString(2));
				list.add(rs1.getString(3));
				list.add(rs1.getString(4));
				list.add(rs1.getString(5));
				list.add(rs1.getString(6));
				list.add(rs1.getString(7));
				list.add(rs1.getString(8));
				list.add(rs1.getString(9));
				list.add(rs1.getString(10));
				list.add(rs1.getString(11));
				list.add(rs1.getString(12));
				String nmbDate = rs1.getString(16);
				 System.out.println("Date from database: " + nmbDate); 
				Date date = inputDate.parse(nmbDate);
	            formattedDate = outputDate.format(date);
	            System.out.println("from:"+ formattedDate);
	            list.add(formattedDate);
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (Exception e) {
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