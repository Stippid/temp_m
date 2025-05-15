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

import javax.sql.DataSource;

public class B_VehTmsDailyRecieptDAOImpl implements B_VehTmsDailyRecieptDao  {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Map<String, String>> get_B_vech_daily(String sus_no_aprove, String ser_no_approve) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select a.unit_sus_no,c.std_nomclature,CAST(count(*) AS INT) as counts from tb_tms_drr_dir_dtl a "
                    + " inner join tb_tms_banum_dirctry b on  a.ba_no=b.ba_no "
                    + " inner join tb_tms_mct_master c on b.mct=c.mct "
                    + " where a.drr_dir_ser_no=?  and  a.sus_no=? group by c.std_nomclature ,a.unit_sus_no ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ser_no_approve);
			stmt.setString(2, sus_no_aprove);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getString(i));
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

}
