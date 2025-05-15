package com.dao.orbat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Comd_and_cont_instDAOImpl implements Comd_and_cont_inst_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public ArrayList<ArrayList<String>> SearComdAndContInstDtlForDistribution(String status, String issue_date,
			String to_date, String arm, String sus_no, String command_name,
			HttpSession session) {
		
		String roleType = session.getAttribute("roleType").toString();	

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			String qry = "";

			if (status != "") {
				qry += " c.sd9_distribute_status =  ?";
			}
			if (!sus_no.equals("")) {
				qry += " and c.unit_sus_no = ?";
			}
			if (!command_name.equals("") && !command_name.equals("0")) {
				qry += " and dtl.unit_name = ?";
			}
			if (!issue_date.equals("") && !to_date.equals("")) {
				qry += " and c.from_date between cast(? as date)" + " and " + "cast(? as date)";
			}
			if (!issue_date.equals("") && to_date.equals("")) {

				Date to_date1 = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				to_date = dateFormat.format(to_date1);
				qry += " and c.from_date between  cast(? as date) and  cast(? as date) ";
			}
//			if (!arm.equals("") && !arm.equals("0")) {
//				qry += "  and d.type_of_issue = ?";
//			}
//		

		

			sql1 = "select\r\n"
					+ "	c.id,\r\n"
					+ "	c.auth_letter_no,\r\n"
					+ "	dtl.unit_name,\r\n"
					+ "		dtl1.unit_name as commandname\r\n"
					+ "from\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl dtl on dtl.sus_no=c.unit_sus_no\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl dtl1 on dtl1.sus_no=c.created_cmd_susno\r\n"
					+ "	and dtl1.status_sus_no='Active'"
					+ "	and dtl.status_sus_no='Active' where "+qry+"  ";

			stmt = conn.prepareStatement(sql1);
			int j = 1;
			if (status != "") {
				stmt.setInt(j, Integer.parseInt(status));
				j += 1;
			}
			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}
			if (!command_name.equals("")) {
				stmt.setString(j, command_name);
				j += 1;
			}
			if (!issue_date.equals("") && !to_date.equals("")) {
				stmt.setString(j, issue_date);
				j += 1;
				stmt.setString(j, to_date);
				j += 1;
			}
			if (!issue_date.equals("") && to_date.equals("")) {
				Date to_date1 = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				to_date = dateFormat.format(to_date1);
				stmt.setString(j, issue_date);
				j += 1;
				stmt.setString(j, to_date);
				j += 1;
			}
//			if (!arm.equals("")) {
//				stmt.setString(j, arm);
//				j += 1;
//			}
			
			ResultSet rs1 = stmt.executeQuery();
			String checkbox = "";
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("auth_letter_no"));// 0
				list.add(rs1.getString("unit_name"));// 1
				list.add(rs1.getString("commandname"));// 2					
				
				String viewAction = "onclick=\"viewComdAndContDetails(" + rs1.getString("id") + ")\"";
				String viewBtn = "<i class='action_icons action_view' " + viewAction + " title='View Comd And Cont Inst Data'></i>";	
		
			
					checkbox = "<input type='checkbox' name='checkbox_name1[]' class='nrCheckBox' id='NRIN "
							+ rs1.getString("id") + "' value =" + rs1.getString("id") + " onclick='setid("
							+ rs1.getString("id") + ") onclick='findselected();' />";
					list.add(viewBtn);//3
					if(roleType.equals("APP")) {					
						list.add(checkbox);// 4	
					}		
			
				aList.add(list);
			}
			rs1.close();
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
		return aList;
	}
	
	
	public List<Map<String, Object>> getComdAndContInstDetails(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select\r\n"
					+ "c.auth_letter_no,\r\n"
					+ "c.auth_letter_date,\r\n"
					+ "(select distinct code_value from tb_miso_orbat_code where code = c.loc_code and status_record = '1' )as loc,\r\n"
					+ "(select DISTINCT code_value from tb_miso_orbat_code where code = c.nrs_code and status_record = '1' ) as nrs,"
					+ "c.loc_code, c.nrs_code ,\r\n"
					+ "c.unit_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.unit_sus_no  and status_sus_no ='Active') as unit_name,\r\n"
					+ "c.ops_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.ops_sus_no  and status_sus_no ='Active') as ops_name,\r\n"
					+ "c.is_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.is_sus_no  and status_sus_no ='Active') as is_name,\r\n"
					+ "c.mil_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.mil_sus_no  and status_sus_no ='Active') as mil_name,\r\n"
					+ "c.techcont_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.techcont_sus_no  and status_sus_no ='Active') as techcont_name,\r\n"
					+ "c.discp_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.discp_sus_no  and status_sus_no ='Active') as discp_name,\r\n"
					+ "c.local_adm_sus_no,\r\n"
					+ "(select distinct unit_name from tb_miso_orbat_unt_dtl where sus_no =c.local_adm_sus_no  and status_sus_no ='Active') as local_adm_name,\r\n"
					+ "c.from_date,\r\n"
					+ "c.to_date	\r\n"
					+ "from\r\n"
					+ "	tb_miso_orbat_comd_cont c\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl dtl on dtl.sus_no=c.unit_sus_no\r\n"
					+ "	and dtl.status_sus_no='Active'\r\n"				
					+ "	where c.id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
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
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> getSdMoveDtl(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select\r\n"
					+ "	a.sus_no,\r\n"
					+ "	a.unit_name,\r\n"
					+ "	ab.cmd_name,\r\n"
					+ "	ac.coprs_name,\r\n"
					+ "	ad.div_name,\r\n"
					+ "	ae.bde_name\r\n"
					+ "from\r\n"
					+ "	tb_miso_orbat_unt_dtl a\r\n"
					+ "	LEFT JOIN orbat_view_corps ac ON SUBSTR(\r\n"
					+ "		a.form_code_control::TEXT,\r\n"
					+ "		2,\r\n"
					+ "		2\r\n"
					+ "	)=ac.corps_code::TEXT\r\n"
					+ "	LEFT JOIN orbat_view_cmd ab ON SUBSTR(\r\n"
					+ "		a.form_code_control::TEXT,\r\n"
					+ "		1,\r\n"
					+ "		1\r\n"
					+ "	)=ab.cmd_code::TEXT\r\n"
					+ "	LEFT JOIN orbat_view_div ad ON SUBSTR(\r\n"
					+ "		a.form_code_control::TEXT,\r\n"
					+ "		4,\r\n"
					+ "		3\r\n"
					+ "	)=ad.div_code::TEXT\r\n"
					+ "	LEFT JOIN orbat_view_bde ae ON SUBSTR(\r\n"
					+ "		a.form_code_control::TEXT,\r\n"
					+ "		7,\r\n"
					+ "		4\r\n"
					+ "	)=ae.bde_code::TEXT\r\n"
					+ "where\r\n"
					+ "	status_sus_no='Active'\r\n"
					+ "	AND a.sus_no=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
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
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	


}