package com.dao.Reports;

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

public class CustomQueryDAOImpl implements CustomQueryDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> CustomQueryTPTList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String cmd,String mct_main,String prf_code ,String type,String sus_no,String line_dte_sus1) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String whr ="";
		if(!mct_main.equals("0")) {
			whr +=" and a.mct_main_id = ? ";
		}
		if(!prf_code.equals("0")) {
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
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select a.mct_main_id,b.mct_nomen,\r\n" + 
					"	COALESCE(sum(a.ue),'0') as ue,\r\n" + 
					"	sum(a.total_uh) as total_uh\r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					" where a.type = ? "+whr +  
					" group by 1,2 \r\n" +
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
	
	public long CustomQueryTPTCount(String Search,String cmd,String mct_main,String prf_code ,String type,String sus_no,String line_dte_sus1) {
		int total = 0;
		String whr ="";
		if(!mct_main.equals("0")) {
			whr +=" and a.mct_main_id = ? ";
		}
		if(!prf_code.equals("0")) {
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
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			String sql = "select count(distinct a.mct_main_id) \r\n" + 
					"from tms_vehicle_status_a_b_c_with_ue_uh a\r\n" + 
					"inner join tb_tms_mct_main_master b on  b.mct_main_id = a.mct_main_id\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.sus_no = a.sus_no\r\n" + 
					" where a.type = ? "+whr;  
			

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
			System.out.println("====cue==report=="+stmt);
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

}
