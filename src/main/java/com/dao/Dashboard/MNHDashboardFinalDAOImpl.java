package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.mnh.Med_principle_dis;
import com.models.mnh.Medical_Cmd_Corp_Disease_Details_View;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class MNHDashboardFinalDAOImpl implements MNHDashboardFinalDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<List<String>> getCountAllImportantDis(int yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = "select distinct c.d_desc,sum(c.count) as count from med_life_disease_count c where c.yr = ?  group by c.d_desc";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, yr);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				String d_desc = rs1.getString("d_desc");
				String count = rs1.getString("count");
				list.add(d_desc);
				list.add(count);
				listA.add(list);
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
		return listA;
	}

	public String getCommand_wise_count_List(int yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select \r\n" + "	distinct c.short_form as command,\r\n" + "	sum(b.count) as count ,\r\n"
					+ "	c.cmd_code as cmd_code\r\n" + "from  \r\n" + "	med_cmd_mn_count b\r\n"
					+ "	left join orbat_view_cmd c on c.cmd_code = substr(b.form_code_control,1,1)\r\n"
					+ "	where b.yr = ? and upper(b.relationship) like 'SELF%' \r\n"
					+ "and b.service in ('AR','RR','DS','DA','MC','MA') --and b.command is not null \r\n"
					+ "   GROUP BY c.short_form,3";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, yr);
			rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("Command"));
				list.add(rs1.getString("count"));
				list.add(rs1.getString("cmd_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			if (i == 0) {
				list += "{'Command' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1)
						+ ",'cmd_code' : '" + listA.get(i).get(2) + "'}";
			} else {
				list += ",{'Command' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1)
						+ ",'cmd_code' : '" + listA.get(i).get(2) + "'}";
			}
		}
		list += "]";
		return list;
	}
	
	public ArrayList<List<String>> getCommand_wise_count_List1(int yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select \r\n" + "	distinct c.short_form as command,\r\n" + "	sum(b.count) as count ,\r\n"
					+ "	c.cmd_code as cmd_code\r\n" + "from  \r\n" + "	med_cmd_mn_count b\r\n"
					+ "	left join orbat_view_cmd c on c.cmd_code = substr(b.form_code_control,1,1)\r\n"
					+ "	where b.yr = ? and upper(b.relationship) like 'SELF%' \r\n"
					+ "and b.service in ('AR','RR','DS','DA','MC','MA') --and b.command is not null \r\n"
					+ "   GROUP BY c.short_form,3";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, yr);
			rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("Command"));
				list.add(rs1.getString("count"));
				list.add(rs1.getString("cmd_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	

	public ArrayList<List<String>> getCorpsWise_Count_List(String Command) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			if (Command.equals("4")) {
				sql1 = "select \r\n" + "	distinct d.div_name as div,\r\n" + "	sum(b.count) as count,\r\n"
						+ "	substr(b.form_code_control,1,6) as div_code\r\n" + "from \r\n" + "	med_cmd_mn_count b\r\n"
						+ "	inner join orbat_view_div d on d.div_code = substr(b.form_code_control,4,3)\r\n"
						+ "	where b.yr = extract(year from now()) and upper(b.relationship) like 'SELF%' \r\n"
						+ "and b.service in ('AR','RR','DS','DA','MC','MA') and b.form_code_control like ? \r\n"
						+ "GROUP BY d.div_name,3";
			} else {
				sql1 = "select \r\n" + "	distinct c.coprs_name as corps,\r\n" + "	sum(b.count) as count,\r\n"
						+ "	substr(b.form_code_control,1,3) as corps_code\r\n" + "from \r\n"
						+ "	med_cmd_mn_count b\r\n"
						+ "	left join orbat_view_corps c on c.corps_code = substr(b.form_code_control,2,2)\r\n"
						+ "	inner join orbat_view_cmd p on p.cmd_code like ? \r\n"
						+ "	where b.yr = extract(year from now()) and upper(b.relationship) like 'SELF%' \r\n"
						+ "and b.service in ('AR','RR','DS','DA','MC','MA')\r\n" + "GROUP BY c.coprs_name,3";
			}
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, Command + "%");
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("corps"));
				list.add(rs1.getString("count"));
				list.add(rs1.getString("corps_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	public ArrayList<List<String>> getDivsWise_Count_List(String corps) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sql1 = "";
			sql1 = "select \r\n" + "	distinct d.div_name as div,\r\n" + "	sum(b.count) as count,\r\n"
					+ "	substr(b.form_code_control,1,6) as div_code\r\n" + "from \r\n" + "	med_cmd_mn_count b\r\n"
					+ "	inner join orbat_view_div d on d.div_code = substr(b.form_code_control,4,3)\r\n"
					+ "	where b.yr = extract(year from now()) and upper(b.relationship) like 'SELF%' \r\n"
					+ "and b.service in ('AR','RR','DS','DA','MC','MA') and b.form_code_control like ? \r\n"
					+ "GROUP BY d.div_name,3";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, corps + "%");
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("div"));
				list.add(rs1.getString("count"));
				list.add(rs1.getString("div_code"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	public ArrayList<List<String>> getBdesWise_Count_List(String div) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select \r\n" + "	distinct d.bde_name as bde,\r\n" + "	sum(b.count) as count\r\n" + "from \r\n"
					+ "	med_cmd_mn_count b\r\n"
					+ "	inner join orbat_view_bde d on d.bde_code = substr(b.form_code_control,7,4)\r\n"
					+ "	where b.yr = extract(year from now()) and upper(b.relationship) like 'SELF%' \r\n"
					+ "and b.service in ('AR','RR','DS','DA','MC','MA') and b.form_code_control like ? \r\n"
					+ "GROUP BY d.bde_name";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, div + "%");
			rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("bde"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	// Chart 1 Methods
	public String getChart1List() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();

			String sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and yr = extract(year from now()) group by upper(category)";
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			String command = "";
			if (listA.get(i).get(0).equals("OFFICER")) {
				command = "OFFR";
			}
			if (listA.get(i).get(0).equals("MNS")) {
				command = "MNS";
			}
			if (listA.get(i).get(0).equals("JCOs")) {
				command = "JCO";
			}
			if (listA.get(i).get(0).equals("OR")) {
				command = "OR";
			}

			if (i == 0) {
				if (!command.equals("")) {
					list += "{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += "{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			} else {
				if (!command.equals("")) {
					list += ",{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += ",{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			}
		}
		list += "]";

		return list;
	}
	
	public String getMorbility_with_Array(int year) {
		ArrayList<List<String>> listA = getMorbility(year);
		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			String command = "";
			if (listA.get(i).get(0).equals("OFFICER")) {
				command = "OFFR";
			}
			if (listA.get(i).get(0).equals("MNS")) {
				command = "MNS";
			}
			if (listA.get(i).get(0).equals("JCOs")) {
				command = "JCO";
			}
			if (listA.get(i).get(0).equals("OR")) {
				command = "OR";
			}
			if (i == 0) {
				if (!command.equals("")) {
					list += "{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += "{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			} else {
				if (!command.equals("")) {
					list += ",{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += ",{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			}
		}
		list += "]";
		return list;
	}
	
	public ArrayList<List<String>> getMorbility(int year) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count "
					+ "where (category is not null or category <> '') and upper(relationship) like 'SELF%' "
					+ "and service in ('AR','RR','DS','DA','MC','MA') and yr = ?  group by upper(category)"; //extract(year from now())
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, year);
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	// Ex-Serv Chart
	public String getChart1ExList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where service in ('XR', 'XE','XF','XH','XN','XS') "
					+ "and upper(relationship) like 'SELF%' and (category is not null or category <> '') "
					+ "and yr = extract(year from now()) group by upper(category)";
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			String command = "";
			if (listA.get(i).get(0).equals("OFFICER")) {
				command = "OFFR";
			}
			if (listA.get(i).get(0).equals("MNS")) {
				command = "MNS";
			}
			if (listA.get(i).get(0).equals("JCOs")) {
				command = "JCO";
			}
			if (listA.get(i).get(0).equals("OR")) {
				command = "OR";
			}
			if (i == 0) {
				if (!command.equals("")) {
					list += "{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += "{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			} else {
				if (!command.equals("")) {
					list += ",{'cat' : '" + command + "' ,'count' : " + listA.get(i).get(1) + "}";
				} else {
					list += ",{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
				}
			}
		}
		list += "]";
		return list;
	}

	// Start Chart 4 TOP 10 Principal Cause
	public String getTop10PCList() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select distinct disease_principal as cat, sum(count) as count from med_cmd_mn_count \r\n"
					+ "where yr = extract(year from now()) and upper(relationship) like 'SELF%' \r\n"
					+ "and service in ('AR','RR','DS','DA','MC','MA') and disease_principal is not null and disease_principal != ''\r\n"
					+ "group by disease_principal order by count desc limit 10";
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String list = "[";
		for (int i = listA.size() - 1; i >= 0; i--) {
			if (i == listA.size() - 1) {
				list += "{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
			} else {
				list += ",{'cat' : '" + listA.get(i).get(0) + "' ,'count' : " + listA.get(i).get(1) + "}";
			}
		}
		list += "]";
		return list;
	}

	/////////// Start Daily Bed State///////
	public String getBED_STATE_ARMY_List() {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select distinct c.short_form as Command, sum(f.off) as off,sum(f.jco_or) as jco_or from \r\n"
					+ " (select distinct substr(b.form_code_control,1,1) as Command ,sum(a.off_army) as off,sum(a.jco_or_army) as jco_or\r\n"
					+ " from tb_med_daily_bed_occupancy a, tb_miso_orbat_unt_dtl b where a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null \r\n"
					+ " and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
					+ " group by b.form_code_control ,a.off_army,a.jco_or_army) f inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.short_form";
			stmt = conn.prepareStatement(sql1);
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("Command"));
				list.add(rs1.getString("off"));
				list.add(rs1.getString("jco_or"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String list = "[";
		for (int i = 0; i < listA.size(); i++) {
			if (i == 0) {
				list += "{'Command' : '" + listA.get(i).get(0) + "','off' : " + listA.get(i).get(1) + ",'jco_or' : "+ listA.get(i).get(2) + "}";
			} else {
				list += ",{'Command' : '" + listA.get(i).get(0) + "','off' : " + listA.get(i).get(1)+ ",'jco_or' : " + listA.get(i).get(2) + "}";
			}
		}
		list += "]";
		return list;
	}

	public ArrayList<List<String>> getLMCChart1ListWithParameter(String from_date, String to_date) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			if (from_date.equals("") && to_date.equals("")) {
				sql1 = "select \r\n" + "	distinct c.cmd_name as Command, \r\n" + "	sum(f.off) as off,\r\n"
						+ "	sum(f.jco_or) as jco_or \r\n" + "from \r\n" + "	(select \r\n"
						+ "		distinct substr(b.form_code_control,1,1) as Command ,\r\n"
						+ "		sum(a.off_army) as off,\r\n" + "		sum(a.jco_or_army) as jco_or\r\n"
						+ "	from 	tb_med_daily_bed_occupancy a\r\n"
						+ "		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n"
						+ "	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
				stmt = conn.prepareStatement(sql1);
			}
			if (!from_date.equals("") && to_date.equals("")) {
				sql1 = "select \r\n" + "	distinct c.cmd_name as Command, \r\n" + "	sum(f.off) as off,\r\n"
						+ "	sum(f.jco_or) as jco_or \r\n" + "from \r\n" + "	(select \r\n"
						+ "		distinct substr(b.form_code_control,1,1) as Command ,\r\n"
						+ "		sum(a.off_army) as off,\r\n" + "		sum(a.jco_or_army) as jco_or\r\n"
						+ "	from 	tb_med_daily_bed_occupancy a\r\n"
						+ "		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "		where a.dt between date(?) and now()\r\n"
						+ "	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n"
						+ "	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";

				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, from_date);
			}
			if (!from_date.equals("") && !to_date.equals("")) {
				sql1 = "select \r\n" + "	distinct c.cmd_name as Command, \r\n" + "	sum(f.off) as off,\r\n"
						+ "	sum(f.jco_or) as jco_or \r\n" + "from \r\n" + "	(select \r\n"
						+ "		distinct substr(b.form_code_control,1,1) as Command ,\r\n"
						+ "		sum(a.off_army) as off,\r\n" + "		sum(a.jco_or_army) as jco_or\r\n"
						+ "	from 	tb_med_daily_bed_occupancy a\r\n"
						+ "		inner join tb_miso_orbat_unt_dtl b on 	a.sus_no=b.sus_no and b.status_sus_no = 'Active' and b.form_code_control is not null and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "		where a.dt between date(?) and date(?) \r\n"
						+ "	group by b.form_code_control ,a.off_army,a.jco_or_army) f \r\n"
						+ "	inner join orbat_view_cmd c on c.cmd_code = f.Command group by c.cmd_name";
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, from_date);
				stmt.setString(2, to_date);
			}
	
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				String Command = "";
				if (rs1.getString("Command").equals("HQ SOUTH WESTERN COMMAND")) {
					Command = "SWC";
				}
				if (rs1.getString("Command").equals("MIN OF DEFENCE")) {
					Command = "MOD";
				}
				if (rs1.getString("Command").equals("HQ ARMY TRG COMMAND (ARTRAC)")) {
					Command = "ARTRAC";
				}
				if (rs1.getString("Command").equals("HQ EASTERN COMMAND")) {
					Command = "EC";
				}
				if (rs1.getString("Command").equals("HQ UNITED NATION")) {
					Command = "UN";
				}
				if (rs1.getString("Command").equals("HQ SOUTHERN COMMAND")) {
					Command = "SC";
				}
				if (rs1.getString("Command").equals("HQ STRATEGIC FORCES COMMAND (SFC)")) {
					Command = "SFC";
				}
				if (rs1.getString("Command").equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) {
					Command = "ANC";
				}
				if (rs1.getString("Command").equals("HQ CENTRAL COMMAND")) {
					Command = "CC";
				}
				if (rs1.getString("Command").equals("HQ NORTHERN COMMAND")) {
					Command = "NC";
				}
				if (rs1.getString("Command").equals("HQ WESTERN COMMAND")) {
					Command = "WC";
				}
				if (Command.equals("")) {
					list.add(rs1.getString("Command"));
				} else {
					list.add(Command);
				}
				list.add(rs1.getString("off"));
				list.add(rs1.getString("jco_or"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	/////////// End Daily Bed State///////

	// Data Approved Status
	public ArrayList<List<String>> dataappstatusList() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select distinct patient_status as patient_status, count(apprvr_at_unit_by) as unit_app, count(fmn_approved_by) as fmn_app, count(apprvr_at_miso_by) as miso_app from tb_med_patient_details where extract(year from admsn_date)=2019 group by patient_status";
			stmt = conn.prepareStatement(sql1);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("patient_status"));
				list.add(rs1.getString("unit_app"));
				list.add(rs1.getString("fmn_app"));
				list.add(rs1.getString("miso_app"));
				lista.add(list);
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
		return lista;
	}

	// Data Upload Status
	public ArrayList<List<String>> datauploadstatusList() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();

			String sql1 = "select c.cmd_name as Command, \r\n" + "	f.month as mn, \r\n"
					+ "	f.count as uploaded,\r\n" + "	sum(total.t) - f.count as pending,\r\n"
					+ "	sum(total.t) as total\r\n" + "from\r\n" + "	(select \r\n"
					+ "		distinct substr(b.form_code_control,1,1) as Command ,\r\n" + "		a.month,\r\n"
					+ "		count(a.*) as count\r\n" + "	from 	tb_med_upload_data a  \r\n"
					+ "		inner join tb_miso_orbat_unt_dtl b on a.sus_no=b.sus_no and b.status_sus_no = 'Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
					+ "	where 	a.year = cast(extract(year from now()) as character varying)\r\n"
					+ "	group by b.form_code_control ,a.month\r\n" + "	) f \r\n"
					+ "	inner join orbat_view_cmd c on c.cmd_code = f.Command\r\n"
					+ "	left join (select substr(a.form_code_control,1,1) as f,1 as t from tb_miso_orbat_unt_dtl a\r\n"
					+ "			inner join tb_miso_orbat_codesform b on b.level_in_hierarchy='Unit' and a.sus_no = b.sus_no\r\n"
					+ "			where a.status_sus_no='Active' and substring(b.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
					+ "		) as total on total.f = c.cmd_code\r\n" + "	group by c.cmd_name,f.month,f.count";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("Command"));
				list.add(rs1.getString("mn"));
				list.add(rs1.getString("uploaded"));
				list.add(rs1.getString("pending"));
				list.add(rs1.getString("total"));
				lista.add(list);
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
		return lista;
	}

	public ArrayList<List<String>> UnitInfoTblList() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "select distinct uname, sum(count) as count from med_unit_mn_count group by uname order by count desc";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();

				list.add(rs1.getString("uname"));
				list.add(rs1.getString("count"));
				lista.add(list);
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
		return lista;
	}

	// Disease Principal
	public ArrayList<List<String>> DiseasePrincipalTblList() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			ResultSet rs1 = null;
			sql1 = "select distinct disease_principal as b_desc, sum(count)  as count from med_cmd_mn_count where yr = extract(year from now()) group by disease_principal order by disease_principal";
			rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();

				list.add(rs1.getString("b_desc"));
				list.add(rs1.getString("count"));
				lista.add(list);
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
		return lista;
	}

	// ESM
	public ArrayList<List<String>> getChartESMRelationship(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		String sql1 = "";
		int year = 2020;
		if (yr != null && !yr.equals("")) {
			year = Integer.parseInt(yr);
		}
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			if (yr.equals("") || (relationship.equals("SELF"))) {
				sql1 = "select distinct upper(category) as cat, sum(count)  as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "
						+ year
						+ " and upper(relationship) like 'SELF%' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
				
				
			} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1 = "select distinct upper(category) as cat, sum(count)  as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "
						+ year
						+ " and relationship !='SELF' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
				
			
			} else {
				sql1 = "select distinct upper(category) as cat, sum(count)  as count from med_cmd_mn_count where (category is not null or category <> '') and yr = extract(year from now()) and upper(relationship) like 'SELF%' and service in ('XR', 'XE','XF','XH','XN','XS') group by upper(category)";
			
				
			
			}
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	// Graph serving & esm refresh STart here
	public ArrayList<List<String>> getChartRelationship(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		String sql1 = "";
		int year = 2020;
		if (yr != null && !yr.equals("")) {
			year = Integer.parseInt(yr);
		}
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			if (yr.equals("") || (relationship.equals("SELF"))) {
				sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "
						+ year
						+ " and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA','DA') group by upper(category)";
				
				
			} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = "
						+ year
						+ " and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA','DA') group by upper(category)";
				
			} else {
				sql1 = "select distinct upper(category) as cat, sum(count) as count from med_cmd_mn_count where (category is not null or category <> '') and yr = extract(year from now()) and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA','DA') group by upper(category)";
			
			}
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("cat"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	public List<Med_principle_dis> getmnhPrincipalList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(
				"SELECT distinct disease_principal as principal_cause FROM Tb_Med_Disease_Cause where disease_principal is not null and disease_principal != '' order by disease_principal");
		@SuppressWarnings("unchecked")
		List<Med_principle_dis> count = (List<Med_principle_dis>) query.list();
		tx.commit();
		session.close();
		return count;
	}
	public ArrayList<List<String>> hospital_datastatus(int year) {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		
	
		String yr ="";
		yr =String.valueOf(year).substring(2,4);
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select distinct a.sus_no,a.unit_name as unit_name,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '01') as jan,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '02') as feb,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '03') as mar,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '04') as apr,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '05') as may,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '06') as jun,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '07') as jul,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '08') as aug,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '09') as sep,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '10') as oct,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '11') as nov,\r\n"
					+ " count(b.medical_unit) filter (where split_part (and_no, '/', 3) = '12') as dec,\r\n"
					+ " count(b.medical_unit) as total\r\n" + " from tb_med_patient_details b\r\n"
					+ " right join (select distinct sus_no,unit_name from tb_miso_orbat_unt_dtl a \r\n"
					+ " where a.sus_no in ('3747005Y','3747009P','3747013X','3742110L','3742054X','3742052M','3742135X','3742122A','3742124L','3742121X','3742123H','3755057K','3755061M','3755063X','3748032P','3755062P','3748034A','3747011M','3742056H','3742058N','3747006F','3742109W','3742125N','3755115H','3742038A','3742039H','3742040X','3742053P','3742041A','3742018M','3742017K','3742020K','3742044N','3742016F','3755134N','3742033F','3742074L','3742112W','3747010K','3747015H','3742104X','3742116M','3742066M','3742070P','3742032Y','3742084P','3742090H','3747008M','3742113Y','3742136A','3742133M','3742132K','3755068W','3755067N','3755114A','3755139M','3755086Y','3755098N','3755094X','3755097L','3755133L','3747002L','3742137H','3742078F','3742076W','3742081F','3742079K','3742134P','3742119A','3742129K','3755131A','3742096K','3742059W','3742005P','3742036P','3742037X','3742075N','3747003N','3742131F','3742126W','3742120P','3742014W','3742023X','3742047F','3742049M','3742068X','3742067P','3742062W','3742004M','3742043L','3742046Y','3742025H','3742051K','3742061N','6323001Y','3742028W','3742024A','3742099X','3742097M','3742095F','3742034K','3742111N','3742098P','3742085X','3742206N','3742091L','3742088L','3742087H','3742042H','3742094Y','3742093W','3742021M','3742022P','3742100F','3742108N','3742107L','3755103N','3747012P','3742064F','3742103P','3742205L','3742063Y','3742065K','3742072A','3742057L','3742073H','3742045W','3742130Y','3742128F','3742127Y','3742035M'\r\n"
					+ " ) and status_sus_no='Active'  \r\n"
					+ " and arm_code not in ('2060')) a on b.medical_unit=a.sus_no and  split_part (and_no, '/', 4) = cast (? as character varying) \r\n"
					+ " group by a.sus_no,a.unit_name order by a.unit_name\r\n";
		
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, Integer.parseInt(yr));
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("jan"));
				list.add(rs1.getString("feb"));
				list.add(rs1.getString("mar"));
				list.add(rs1.getString("apr"));
				list.add(rs1.getString("may"));
				list.add(rs1.getString("jun"));
				list.add(rs1.getString("jul"));
				list.add(rs1.getString("aug"));
				list.add(rs1.getString("sep"));
				list.add(rs1.getString("oct"));
				list.add(rs1.getString("nov"));
				list.add(rs1.getString("dec"));
				list.add(rs1.getString("total"));
			
				lista.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	

	public ArrayList<List<String>> getlowmadicalchartWithParameter(String from_date, String to_date) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			if (from_date.equals("") && to_date.equals("")) {
				sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n"
						+ "sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n"
						+ "from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where a.year = extract(year from now()) and substring(a.cmd,1,1)=b.cmd_code\r\n"
						+ "group by b.cmd_name";
				stmt = conn.prepareStatement(sql1);

			} else {
				if (!from_date.equals("") && to_date.equals("")) {
					sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n"
							+ "	sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n"
							+ "	from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where substring(a.cmd,1,1)=b.cmd_code and date_of_board between date(?) and now() \r\n"
							+ "	group by b.cmd_name";
					stmt = conn.prepareStatement(sql1);
					stmt.setString(1, from_date);
				}
				if (!from_date.equals("") && !to_date.equals("")) {
					sql1 += "select distinct b.cmd_name as command, sum(a.off_male+a.off_female+a.cadet+a.lady_cadet+a.mns+a.mns_cadet) as off_total,\r\n"
							+ "	sum(a.jco+a.dsc_jco) as jco,  sum(a.ort+a.dsc_or+a.rect) as ort, sum(a.total) as total\r\n"
							+ "	from tb_med_strength a, orbat_view_cmd b, tb_med_low_medical_category c where substring(a.cmd,1,1)=b.cmd_code and date_of_board between date(?) and date(?) \r\n"
							+ "	group by b.cmd_name";
					stmt = conn.prepareStatement(sql1);
					stmt.setString(1, from_date);
					stmt.setString(2, to_date);
				}
			}
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				String Command = "";
				if (rs1.getString("command").equals("HQ SOUTH WESTERN COMMAND")) {
					Command = "SWC";
				}
				if (rs1.getString("command").equals("MIN OF DEFENCE")) {
					Command = "MOD";
				}
				if (rs1.getString("command").equals("HQ ARMY TRG COMMAND (ARTRAC)")) {
					Command = "ARTRAC";
				}
				if (rs1.getString("command").equals("HQ EASTERN COMMAND")) {
					Command = "EC";
				}
				if (rs1.getString("command").equals("HQ UNITED NATION")) {
					Command = "UN";
				}
				if (rs1.getString("command").equals("HQ SOUTHERN COMMAND")) {
					Command = "SC";
				}
				if (rs1.getString("command").equals("HQ STRATEGIC FORCES COMMAND (SFC)")) {
					Command = "SFC";
				}
				if (rs1.getString("command").equals("HQ ANDAMAN & NICOBAR COMMAND (ANC)")) {
					Command = "ANC";
				}
				if (rs1.getString("command").equals("HQ CENTRAL COMMAND")) {
					Command = "CC";
				}
				if (rs1.getString("command").equals("HQ NORTHERN COMMAND")) {
					Command = "NC";
				}
				if (rs1.getString("command").equals("HQ WESTERN COMMAND")) {
					Command = "WC";
				}

				if (Command.equals("")) {
					list.add(rs1.getString("command"));
				} else {
					list.add(Command);
				}

				list.add(rs1.getString("off_total"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("ort"));
				list.add(rs1.getString("total"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	//////////////////////////////// End low madical category //////////////////////

	/// Top 10 Principal refresh graph

	@SuppressWarnings("null")
	public ArrayList<List<String>> getChartTopPrincipal(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			int year = 2020;
			if (yr != null && !yr.equals("")) {
				year = Integer.parseInt(yr);
			}
			String sql1 = "select distinct disease_principal as command, sum(count) as count from med_cmd_mn_count \r\n"
					+ "where yr = ? and upper(relationship) = ? \r\n"
					+ "and service in ('AR','RR','DS','DA','MC','MA') and disease_principal is not null and disease_principal != ''\r\n"
					+ "group by disease_principal order by count desc limit 10";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, year);
			stmt.setString(2, relationship.toUpperCase());
			ResultSet rs1 = stmt.executeQuery();
            
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				String Command = "";
				if (rs1.getString("command").equals("OFFICER")) {
					Command = "OFFR";
				}
				if (rs1.getString("command").equals("MNS")) {
					Command = "MNS";
				}
				if (rs1.getString("command").equals("JCOs")) {
					Command = "JCO";
				}
				if (rs1.getString("command").equals("OR")) {
					Command = "OR";
				}

				if (Command.equals("")) {
					list.add(rs1.getString("command"));
				} else {
					list.add(Command);
				}
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	// Top 10 Principal

	public List<String> getMNHCorps(String a1) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select distinct coprs_name from orbat_all_details_view_mnh where sus_no=? order by coprs_name";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, a1);
			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("coprs_name");
				str1 = str1 + ",";
			}
			l.add(str1);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<Map<String, Object>> getAllReportListJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	// Refresh graph command & Corps wise
	public ArrayList<List<String>> getCorpsWise_Count_List1(String command, String relationship, String yr) {
		if (command.equals("MOD")) {
			command = "0";
		}
		if (command.equals("SC")) {
			command = "1";
		}
		if (command.equals("EC")) {
			command = "2";
		}
		if (command.equals("SWC")) {
			command = "8";
		}
		if (command.equals("ARTRAC")) {
			command = "6";
		}
		if (command.equals("UN")) {
			command = "9";
		}
		if (command.equals("SFC")) {
			command = "A";
		}
		if (command.equals("ANC")) {
			command = "7";
		}
		if (command.equals("CC")) {
			command = "4";
		}
		if (command.equals("NC")) {
			command = "5";
		}
		if (command.equals("WC")) {
			command = "3";
		}
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			if (command.equals("4")) {
				if (yr.equals("") || (relationship.equals("SELF"))) {
					sql1 = "select distinct b.div_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = " + yr + " and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command
							+ "' and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
					sql1 = "select distinct b.div_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = " + yr + " and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command
							+ "' and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else {
					sql1 = "select distinct b.div_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command + "' and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				}
			} else {
				if (yr.equals("") || (relationship.equals("SELF"))) {
					sql1 = "select distinct b.coprs_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = " + yr + " and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command
							+ "' and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and b.coprs_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.coprs_name";
				} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
					sql1 = "select distinct b.coprs_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = " + yr + " and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command
							+ "' and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA') and b.coprs_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.coprs_name";
				} else {
					sql1 = "select distinct b.coprs_name as command, sum(count) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and substr(b.form_code_control,1,1) = '" + command + "' and b.coprs_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.coprs_name";
				}
			}
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	// Refresh Graph Corps/Div wise
	public ArrayList<List<String>> getDivsWise_Count_List1(String command, String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			if (command.equals("HQ UTTAR BHARAT AREA") || command.equals("HQ MADHYA BHARAT AREA")) {
				if (yr.equals("") || (relationship.equals("SELF"))) {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and div_name = '" + command
							+ "' and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and div_name = '" + command
							+ "' and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and div_name = '" + command + "' and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				}
			} else {
				if (yr.equals("") || (relationship.equals("SELF"))) {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and coprs_name = '" + command
							+ "' and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and coprs_name = '" + command
							+ "' and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				} else {
					sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
							+ "where yr = extract(year from now()) and command is not null \r\n"
							+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
							+ "and coprs_name = '" + command + "' and b.div_name is not null\r\n"
							+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
				}
			}
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	// Refresh Graph Bde wise
	public ArrayList<List<String>> getBdesWise_Count_List1(String command, String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			if (yr.equals("") || (relationship.equals("SELF"))) {
				sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
						+ "where yr = extract(year from now()) and command is not null \r\n"
						+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "and div_name = '" + command
						+ "' and upper(relationship) like 'SELF%' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
						+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
			} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
						+ "where yr = extract(year from now()) and command is not null \r\n"
						+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "and div_name = '" + command
						+ "' and relationship !='SELF' and service in ('AR','RR','DS','DA','MC','MA') and b.div_name is not null\r\n"
						+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
			} else {
				sql1 = "select distinct b.div_name as command, count(*) as count from med_cmd_mn_count a, orbat_all_details_view_mnh b\r\n"
						+ "where yr = extract(year from now()) and command is not null \r\n"
						+ "and substring(a.sus_no,1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','3748')\r\n"
						+ "and div_name = '" + command + "' and b.div_name is not null\r\n"
						+ "and a.sus_no=b.sus_no\r\n" + "group by b.div_name";
			}

			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("command"));
				list.add(rs1.getString("count"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}

	public DataSet<Medical_Cmd_Corp_Disease_Details_View> DatatablesCriteriasCmdCorps(DatatablesCriterias criterias,
			String whr) {
		List<Medical_Cmd_Corp_Disease_Details_View> metadata = findCmdCorpsCriteriasihd(criterias, whr);
		Long countFiltered = getFilteredCmdCorpsihd(criterias, whr);
		Long count = getTotalCmdCorpsihd(whr);
		return new DataSet<Medical_Cmd_Corp_Disease_Details_View>(metadata, count, countFiltered);
	}
	@SuppressWarnings("unchecked")
	private List<Medical_Cmd_Corp_Disease_Details_View> findCmdCorpsCriteriasihd(DatatablesCriterias criterias,
			String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Medical_Cmd_Corp_Disease_Details_View d " + whr);
		queryBuilder.append(getFilterQueryhd(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				if (columnDef.getName().equals("")) {
					String st = " ORDER BY";
					int i = queryBuilder.indexOf(st);
					if (i != -1) {
						queryBuilder.delete(i, i + st.length());
					}
				} else if (columnDef.getName().contains("hodProfile.fullName")) {
					orderParams.add("d.hodProfile.firstName" + columnDef.getSortDirection());
				} else {
					orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
				}
			}
			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());
		List<Medical_Cmd_Corp_Disease_Details_View> list = (List<Medical_Cmd_Corp_Disease_Details_View>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCmdCorpsihd(DatatablesCriterias criterias, String whr) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder("SELECT d FROM Medical_Cmd_Corp_Disease_Details_View d " + whr);
		queryBuilder.append(getFilterQueryhd(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<Medical_Cmd_Corp_Disease_Details_View> list = (List<Medical_Cmd_Corp_Disease_Details_View>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}

	private Long getTotalCmdCorpsihd(String whr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("SELECT COUNT(d) FROM Medical_Cmd_Corp_Disease_Details_View d " + whr);
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	private static StringBuilder getFilterQueryhd(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("off") || columnDef.getName().equalsIgnoreCase("mns")
							|| columnDef.getName().equalsIgnoreCase("jco") || columnDef.getName().equalsIgnoreCase("or")
							|| columnDef.getName().equalsIgnoreCase("count")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
				}
			}
			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	public ArrayList<List<String>> getBedCorpsWise_Count_List(String Command) {
		if (Command.equals("MOD")) {
			Command = "0";
		}
		if (Command.equals("SC")) {
			Command = "1";
		}
		if (Command.equals("EC")) {
			Command = "2";
		}
		if (Command.equals("SWC")) {
			Command = "8";
		}
		if (Command.equals("ARTRAC")) {
			Command = "6";
		}
		if (Command.equals("UN")) {
			Command = "9";
		}
		if (Command.equals("SFC")) {
			Command = "A";
		}
		if (Command.equals("ANC")) {
			Command = "7";
		}
		if (Command.equals("CC")) {
			Command = "4";
		}
		if (Command.equals("NC")) {
			Command = "5";
		}
		if (Command.equals("WC")) {
			Command = "3";
		}
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			if (Command.equals("4")) {
				sql1 = "SELECT distinct a_1.unit_name AS Command,sum(c_1.off_army) as off,sum(c_1.jco_or_army) as jco_or FROM tb_miso_orbat_unt_dtl a_1,tb_miso_orbat_codesform b_1, tb_med_daily_bed_occupancy c_1"
						+ " WHERE b_1.level_in_hierarchy::text = 'Corps'::text AND a_1.sus_no::text = b_1.sus_no::text AND a_1.status_sus_no::text = 'Active'::text and substr(a_1.form_code_control,1,1) ='"
						+ Command + "'" + " group by a_1.unit_name,a_1.form_code_admin";
			} else {
				sql1 = "SELECT distinct a_1.unit_name AS Command,sum(c_1.off_army) as off,sum(c_1.jco_or_army) as jco_or FROM tb_miso_orbat_unt_dtl a_1,tb_miso_orbat_codesform b_1, tb_med_daily_bed_occupancy c_1"
						+ " WHERE b_1.level_in_hierarchy::text = 'Corps'::text AND a_1.sus_no::text = b_1.sus_no::text AND a_1.status_sus_no::text = 'Active'::text and substr(a_1.form_code_control,1,1) ='"
						+ Command + "'" + " group by a_1.unit_name,a_1.form_code_admin";
			}
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("Command"));
				list.add(rs1.getString("off"));
				list.add(rs1.getString("jco_or"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	
	
	public ArrayList<List<String>> daily_unusual_occurence() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			/*sql1 = "select m.unit_name as medical_unit,concat(a.persnl_no,'/',r.rank_desc,'/',a.persnl_name) as no_rank_name,a.persnl_unit,''::text as fmn,a.diagnosis,a.remarks as details_of_occurence\r\n" + 
					" ,m.level_c from tb_med_unusual_occurrence a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE";*/
			
			sql1 = "select distinct m.unit_name as medical_unit,case when a.persnl_no = '-1' and  a.persnl_name = '' and r.rank_desc = 'SELECT' then '' \r\n" + 
					"else concat(case when a.persnl_no = '-1' then null else a.persnl_no end,'/',r.rank_desc,'/',a.persnl_name)  end as no_rank_name,a.persnl_unit,''::text as fmn,a.diagnosis,a.remarks as details_of_occurence\r\n" + 
					" ,m.cmd_name from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no and m.status_sus_no='Active'\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE   and a.persnl_no != '-2'";
		
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("medical_unit"));
				list.add(rs1.getString("no_rank_name"));
				list.add(rs1.getString("persnl_unit"));
				list.add(rs1.getString("fmn"));
				list.add(rs1.getString("diagnosis"));
				list.add(rs1.getString("details_of_occurence"));
				list.add(rs1.getString("cmd_name"));
				lista.add(list);
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
		
		return lista;
	}
	
	

	public ArrayList<List<String>> daily_vip_admission() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			/*sql1 = "select  m.unit_name as medical_unit,concat(a.persnl_no,'/',r.rank_desc,'/',a.persnl_name) as no_rank_name,a.persnl_unit,''::text as fmn,"
					+ "a.diagnosis_code1d as diagnosis,a.remarks as details_of_occurence,m.level_c \r\n" + 
					"from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE";*/
			sql1 = "select distinct  m.unit_name as medical_unit,case when a.persnl_no = '-1' and  a.persnl_name = '' and r.rank_desc = 'SELECT' then '' \r\n" + 
					"else concat(case when a.persnl_no = '-1' then null else a.persnl_no end  ,'/',r.rank_desc,'/',a.persnl_name)  end as no_rank_name,a.persnl_unit,''::text as fmn,a.diagnosis_code1d as diagnosis,a.remarks as details_of_occurence,m.cmd_name \r\n" + 
					"from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no and m.status_sus_no='Active'\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE   and a.persnl_no != '-2'";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("medical_unit"));
				list.add(rs1.getString("no_rank_name"));
				list.add(rs1.getString("persnl_unit"));
				list.add(rs1.getString("fmn"));
				list.add(rs1.getString("diagnosis"));
				list.add(rs1.getString("cmd_name"));
				lista.add(list);
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
	
		return lista;
	}
	
	
	public ArrayList<List<String>> daily_amc_adm_admission() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			/*sql1 = "select  m.unit_name as medical_unit,concat(a.persnl_no,'/',r.rank_desc,'/',a.persnl_name) as no_rank_name,"
					+ "a.persnl_unit,''::text as fmn,a.diagnosis_code1d as diagnosis,a.remarks as details_of_occurence,m.level_c \r\n" + 
					"from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE";*/
			sql1 = "select  distinct m.unit_name as medical_unit,case when a.persnl_no = '-1' and  a.persnl_name = '' and r.rank_desc = 'SELECT' then '' \r\n" + 
					"else concat(case when a.persnl_no = '-1' then null else a.persnl_no end,'/',r.rank_desc,'/',a.persnl_name)  "
					+ "end as no_rank_name,a.persnl_unit,''::text as fmn,a.diagnosis_code1d as diagnosis,a.remarks as details_of_occurence,"
					+ "m.cmd_name \r\n" + 
					"from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no and m.status_sus_no='Active' \r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no != '-2'";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("medical_unit"));
				list.add(rs1.getString("no_rank_name"));
				list.add(rs1.getString("persnl_unit"));
				list.add(rs1.getString("fmn"));
				list.add(rs1.getString("diagnosis"));
				list.add(rs1.getString("cmd_name"));
				lista.add(list);
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
		
		return lista;
	}
	public List<Map<String, Object>>daily_bed_defaulterlist(){
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	//public ArrayList<List<String>> daily_bed_defaulterlist() {
		//ArrayList<List<String>> lista = new ArrayList<List<String>>();
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			/*sql1 = "select  distinct b.sus_no,m.unit_name,m.level_c from tb_miso_orbat_unt_dtl m\r\n" + 
					"left join tb_med_daily_bed_occupancy  b on \r\n" + 
					"m.sus_no = b.sus_no\r\n" + 
					"where  m.status_sus_no  = 'Active' and substring(m.sus_no, 1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') \r\n" + 
					"and m.sus_no not in (select  sus_no from tb_med_daily_bed_occupancy where dt = CURRENT_DATE)"; */
					
					/*sql1 = "  (select distinct a.sus_no,m.unit_name,m.level_c from tb_med_daily_disease_surv_details a\r\n" + 
							"	left join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no where a.persnl_no ='-1' and a.dt_report = CURRENT_DATE)	\r\n" + 
							"	union all\r\n" + 
							"	(select distinct a.sus_no,m.unit_name,m.level_c from tb_med_daily_hosp_adm_off_vip a\r\n" + 
							"	left join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no	  where a.persnl_no ='-1'  and a.dt_report = CURRENT_DATE)	\r\n" + 
							"	union all\r\n" + 
							"	(select  distinct a.sus_no,m.unit_name,m.level_c from tb_med_daily_hosp_adm_amc  a\r\n" + 
							"	left join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no	  where a.persnl_no ='-1'  and a.dt_report = CURRENT_DATE)	\r\n" + 
							"	union all\r\n" + 
							"   (select distinct a.sus_no,m.unit_name,m.level_c from tb_med_unusual_occurrence a\r\n" + 
							"	left join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no	  where a.persnl_no ='-1' and a.dt_report = CURRENT_DATE)	\r\n" + 
							"	union all\r\n" + 
							"	 (select distinct a.sus_no,m.unit_name,m.level_c from tb_med_daily_bed_occupancy a\r\n" + 
							"	left join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no	 where total_no_of_patients =0	and a.dt = CURRENT_DATE)	"; 	*/
			
			
			 sql1 = "  select  distinct m.sus_no,m.unit_name,m.cmd_name as level_c from orbat_all_details_view m\r\n" + 
                     "                                        left join tb_med_daily_disease_surv_details  d on m.sus_no = d.sus_no\r\n" + 
                     "                                        left join tb_med_daily_hosp_adm_off_vip  v on m.sus_no = v.sus_no\r\n" + 
                     "                                        left join tb_med_daily_hosp_adm_amc a on m.sus_no = a.sus_no\r\n" + 
                     "                                        left join tb_med_unusual_occurrence  u on m.sus_no = u.sus_no\r\n" + 
                     "                                        left join tb_med_daily_bed_occupancy  b on m.sus_no = b.sus_no\r\n" + 
                     "                                        where  m.status_sus_no  = 'Active' and m.sus_no in ('3747005Y','3747009P','3747013X','3742110L','3742054X','3742052M','3742135X','3742122A','3742124L','3742121X','3742123H','3755057K','3755061M','3755063X','3748032P','3755062P','3748034A','3747011M','3742056H','3742058N','3747006F','3742109W','3742125N','3755115H','3742038A','3742039H','3742040X','3742053P','3742041A','3742018M','3742017K','3742020K','3742044N','3742016F','3755134N','3742033F','3742074L','3742112W','3747010K','3747015H','3742104X','3742116M','3742066M','3742070P','3742032Y','3742084P','3742090H','3747008M','3742113Y','3742136A','3742133M','3742132K','3755068W','3755067N','3755114A','3755139M','3755086Y','3755098N','3755094X','3755097L','3755133L','3747002L','3742137H','3742078F','3742076W','3742081F','3742079K','3742134P','3742119A','3742129K','3755131A','3742096K','3742059W','3742005P','3742036P','3742037X','3742075N','3747003N','3742131F','3742126W','3742120P','3742014W','3742023X','3742047F','3742049M','3742068X','3742067P','3742062W','3742004M','3742043L','3742046Y','3742025H','3742051K','3742061N','6323001Y','3742028W','3742024A','3742099X','3742097M','3742095F','3742034K','3742111N','3742098P','3742085X','3742206N','3742091L','3742088L','3742087H','3742042H','3742094Y','3742093W','3742021M','3742022P','3742100F','3742108N','3742107L','3755103N','3747012P','3742064F','3742103P','3742205L','3742063Y','3742065K','3742072A','3742057L','3742073H','3742045W','3742130Y','3742128F','3742127Y','3742035M') \r\n" + 
                     "                                        and substring(m.sus_no, 1,4) in ('9609','9709','3742','6323','3755','3738','3735','3747','1234') \r\n" ;
             //        "                                        and m.sus_no not in (select  sus_no as p from tb_med_daily_disease_surv_details where dt_report = CURRENT_DATE ) \r\n" + 
             //        "                                        and m.sus_no not in (select  sus_no as q  from tb_med_daily_hosp_adm_off_vip where dt_report = CURRENT_DATE ) \r\n" + 
             //        "                                        and m.sus_no not in (select  sus_no as r from  tb_med_daily_hosp_adm_amc where dt_report = CURRENT_DATE ) \r\n" + 
             //        "                                        and m.sus_no not in (select  sus_no as s from tb_med_unusual_occurrence where dt_report = CURRENT_DATE ) \r\n" + 
             //        "                                        and m.sus_no not in (select  sus_no as t from tb_med_daily_bed_occupancy where dt = CURRENT_DATE ) ";    
			

			
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			/*while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("unit_name"));
				
				lista.add(list);
			}
			*/
			
			
			ResultSetMetaData metaData = rs1.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs1.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for(int i = 1; i <= columnCount; i++){
		 	    	columns.put(metaData.getColumnLabel(i), rs1.getObject(i));
		 	    }
 	            String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs1.getObject(1)+")}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
				
 	  			String f = "";
				f += updateButton;
			
  				columns.put(metaData.getColumnLabel(1), f);
 	           
 	   
		
			list.add(columns);
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
	
		return list;
	}
	

	
	public ArrayList<List<String>> getdiseaseRelationship(String relationship, String yr) {
		ArrayList<List<String>> listA = new ArrayList<List<String>>();
		
		
		Connection conn = null;
		String sql1 = "";
		int year = 2020;
		if (yr != null && !yr.equals("")) {
			year = Integer.parseInt(yr);
		}
		try {
			conn = dataSource.getConnection();
			//Statement stmt = (Statement) conn.createStatement();
			if (yr.equals("") || (relationship.equals("SELF"))) {
				sql1 = "select distinct count(a.id) as dis,(d.disease_principal) as disease_name from tb_med_patient_details a  \r\n" + 
						"   inner join tb_med_d_disease_cause d on d.icd_code = a.diagnosis_code1d  \r\n" + 
						"	where  split_part(a.and_no, '/', 4) = ? and a.relationship ='SELF' \r\n" + 
						"	and(d.icd_code like 'A00%' or d.icd_code like 'A05%' \r\n" + 
						"	or d.icd_code like 'B15%' or d.icd_code like 'B19%' \r\n" + 
						"	or d.icd_code like 'U07.1%' or d.icd_code like 'U07.2%' \r\n" + 
						"	or d.icd_code like 'J10%' or d.icd_code like 'J18%' \r\n" + 
						"	or d.icd_code like 'B50%' or d.icd_code like 'B54%' \r\n" + 
						"	or d.icd_code like 'A90%' or d.icd_code like 'A91%' \r\n" + 
						"	or d.icd_code like 'A75.3%' or d.icd_code like 'A39.0%' \r\n" + 
						"	or d.icd_code like 'A83.0%' ) \r\n" + 
						"	group by 2 order by 1 desc ";
			
			} else if (yr.equals("") || (relationship.equals("DEPENDENT"))) {
				sql1 = "select distinct count(a.id) as dis,(d.disease_principal) as disease_name from tb_med_patient_details a  \r\n" + 
						"   inner join tb_med_d_disease_cause d on d.icd_code = a.diagnosis_code1d  \r\n" + 
						"	where  split_part(a.and_no, '/', 4) = ? and a.relationship not in ('SELF') \r\n" + 
						"	and(d.icd_code like 'A00%' or d.icd_code like 'A05%' \r\n" + 
						"	or d.icd_code like 'B15%' or d.icd_code like 'B19%' \r\n" + 
						"	or d.icd_code like 'U07.1%' or d.icd_code like 'U07.2%' \r\n" + 
						"	or d.icd_code like 'J10%' or d.icd_code like 'J18%' \r\n" + 
						"	or d.icd_code like 'B50%' or d.icd_code like 'B54%' \r\n" + 
						"	or d.icd_code like 'A90%' or d.icd_code like 'A91%' \r\n" + 
						"	or d.icd_code like 'A75.3%' or d.icd_code like 'A39.0%' \r\n" + 
						"	or d.icd_code like 'A83.0%' ) \r\n" + 
						"	group by 2 order by 1 desc ";
				
			} 
			else {
				
				sql1 = "select distinct count(a.id) as dis,(d.disease_principal) as disease_name from tb_med_patient_details a  \r\n" + 
						"   inner join tb_med_d_disease_cause d on d.icd_code = a.diagnosis_code1d  \r\n" + 
						"	where  split_part(a.and_no, '/', 4) = ?  \r\n" + 
						"	and(d.icd_code like 'A00%' or d.icd_code like 'A05%' \r\n" + 
						"	or d.icd_code like 'B15%' or d.icd_code like 'B19%' \r\n" + 
						"	or d.icd_code like 'U07.1%' or d.icd_code like 'U07.2%' \r\n" + 
						"	or d.icd_code like 'J10%' or d.icd_code like 'J18%' \r\n" + 
						"	or d.icd_code like 'B50%' or d.icd_code like 'B54%' \r\n" + 
						"	or d.icd_code like 'A90%' or d.icd_code like 'A91%' \r\n" + 
						"	or d.icd_code like 'A75.3%' or d.icd_code like 'A39.0%' \r\n" + 
						"	or d.icd_code like 'A83.0%' ) \r\n" + 
						"	group by 2 order by 1 desc ";
				
			}
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, yr);
			
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("dis"));
				list.add(rs1.getString("disease_name"));
				listA.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listA;
	}
	
	
	
	public String Getsuicide_caseslist(String relationship,String yr) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		
		
		
		try {
			

		
		/*	if(!yr.equals("")) {
			
				q += "and  split_part(p.and_no, '/', 4) = ? ";
			}*/
			conn = dataSource.getConnection();
			qry = "select concat(m.yr,'',m.and_no) as mainyear,m.male,m.female from  \r\n" + 
					"(select 20 as yr,split_part(p.and_no, '/', 4)  as and_no,\r\n" + 
					" count(*) filter (where p.sex = 'M') as male,\r\n" + 
					" count(*) filter (where p.sex = 'F') as female\r\n" + 
					" from tb_med_patient_details p \r\n" + 
					" left join tb_med_d_disease_cause d  on d.icd_code =p.diagnosis_code1d where  (p.diagnosis_code1d like 'X60%' or p.diagnosis_code1d like 'X84%')\r\n" + 
					/*" "+q+"  \r\n" + */ 
					"group by 1,2)m ";
					
					
			PreparedStatement stmt = conn.prepareStatement(qry);
			int j =1;
			int flag =0;
			
          /* 
            if(!yr.equals("")) {

 			stmt.setString(j,yr);
      		j += 1;
					
                        
			}*/
            
			ResultSet rs = stmt.executeQuery();
			
	
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'datas': '"+rs.getString("mainyear") +"', 'datas1': " + rs.getString("male") + ", 'datas2':  " + rs.getString("female") + " } ";
				
			}
			list += "]";
			
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
	
	
	public String notifiable_diseaselist(String yr) {
	
		
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			 qry = "select distinct count(a.id) as dis,(d.disease_principal) as disease_name from tb_med_patient_details a \r\n" + 
			 		"			 inner join tb_med_d_disease_cause d on d.icd_code = a.diagnosis_code1d \r\n" + 
			 		"			 where  split_part(a.and_no, '/', 4) = ? \r\n" + 
			 		"			 and(d.icd_code like 'A00%' or d.icd_code like 'A05%'\r\n" + 
			 		"			 or d.icd_code like 'B15%' or d.icd_code like 'B19%'\r\n" + 
			 		"			 or d.icd_code like 'U07.1%' or d.icd_code like 'U07.2%'\r\n" + 
			 		"			 or d.icd_code like 'J10%' or d.icd_code like 'J18%'\r\n" + 
			 		"			 or d.icd_code like 'B50%' or d.icd_code like 'B54%'\r\n" + 
			 		"			 or d.icd_code like 'A90%' or d.icd_code like 'A91%'\r\n" + 
			 		"			 or d.icd_code like 'A75.3%' or d.icd_code like 'A39.0%'\r\n" + 
			 		"			 or d.icd_code like 'A83.0%' )\r\n" + 
			 		"			 group by 2 order by 1 desc  ";
			 
			 
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, yr);
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("dis")+"', 'data2': '" + rs.getString("disease_name") + "' } ";
			}
			list += "]";
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
	//SANA
	public String daily_notifiable_disease() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			 qry = "select distinct count(a.disease) as dis,(d.disease_name) as disease_name from tb_med_eir a \r\n" + 
	            		"inner join tb_med_daily_surv_disease_mstr d on d.id = a.disease \r\n" + 
	            		"where datee  = CURRENT_DATE \r\n" + 
	            		"group by d.disease_name order by 1 desc limit 10";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("dis")+"', 'data2': '" + rs.getString("disease_name") + "' } ";
			}
			list += "]";
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
	
	public String daily_bed_occupied_army() {
		String list = "";
		Connection conn = null;
		String qry = "";
		
		String q = "";
		try {
			
				
			
			conn = dataSource.getConnection();
		/*	 qry = "select sum(beds_laid_out-total_no_of_patients) as balanced,sum(total_no_of_patients) as occupied from tb_med_daily_bed_occupancy \r\n" + 
			 		"where dt = CURRENT_DATE";*/
			
			qry = "select  \r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ SOUTHERN COMMAND') as SC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ WESTERN COMMAND') as WC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ EASTERN COMMAND') as EC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ CENTRAL COMMAND') as CC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ NORTHERN COMMAND') as NC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC\r\n" + 
					"\r\n" + 
					"from tb_med_daily_bed_occupancy a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt  = CURRENT_DATE\r\n" + 
					"                                        \r\n" + 
					"									\r\n" + 
					"";
			PreparedStatement stmt = conn.prepareStatement(qry);
			
			
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data11': '"+rs.getString("SC")+"', 'data21': '" + rs.getString("WC") + "','data31': '" + rs.getString("EC") + "',"
						+ "'data41': '" + rs.getString("CC") + "','data51': '" + rs.getString("NC") + "','data61': '" + rs.getString("SWC") + "','data71': '" + rs.getString("ARTRAC") + "' } ";
				
			}
			list += "]";
			
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
	public String suicides_list() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			 qry = "select \r\n" + 
			 		"count(*) filter (where g.gender_name = 'Male') as Male,\r\n" + 
			 		"count(*) filter (where g.gender_name = 'Female') as Female\r\n" + 
			 		"from tb_psg_census_battle_physical_casuality b\r\n" + 
			 		"inner join tb_psg_mstr_casuality1 c on c.id =cast (b.cause_of_casuality_1 as int)\r\n" + 
			 		"inner join tb_psg_trans_proposed_comm_letter p on p.id =b.comm_id\r\n" + 
			 		"inner join tb_psg_mstr_gender g on g.id =p.gender\r\n" + 
			 		"where  split_part(created_on::TEXT, '-', 1)::int = 2022 and c.id =35 \r\n" + 
			 		"";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("Male")+"', 'data2': '" + rs.getString("Female") + "' } ";
			}
			
			list += "]";
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
	
	
	public ArrayList<List<String>> daily_disease_surve() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			/*sql1 = "select distinct m.unit_name as medical_unit,concat(a.persnl_no,'/',r.rank_desc,'/',a.persnl_name) as no_rank_name,a.persnl_unit,''::text as fmn,"
					+ "b.disease_name as diagnosis,a.remarks as details_of_daily_disease,m.level_c \r\n" + 
					"from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"inner join tb_med_daily_surv_disease_mstr b on b.id= a.diagnosis\r\n"+
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE";*/
			sql1 = "select distinct m.unit_name as medical_unit,case when a.persnl_no = '-2' and  a.persnl_name = '' and r.rank_desc = 'SELECT' then '' \r\n" + 
					"else concat(case when a.persnl_no = '-1' then null else a.persnl_no end  ,'/',r.rank_desc,'/',a.persnl_name)   end as no_rank_name,a.persnl_unit,''::text as fmn,b.disease_name as diagnosis,a.remarks as details_of_daily_disease,m.cmd_name \r\n" + 
					"from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no and m.status_sus_no='Active'\r\n" + 
					"inner join tb_med_daily_surv_disease_mstr b on b.id= a.diagnosis\r\n" + 
					"inner join tb_med_rankcode r on a.rank = r.id\r\n" + 
					"where  a.dt_report  = CURRENT_DATE  and a.persnl_no != '-2'";
		
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("medical_unit"));
				list.add(rs1.getString("no_rank_name"));
				list.add(rs1.getString("persnl_unit"));
				list.add(rs1.getString("fmn"));
				list.add(rs1.getString("diagnosis"));
				list.add(rs1.getString("cmd_name"));
				lista.add(list);
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
		
		return lista;
	}
	
	
	
	
	public ArrayList<List<String>> daily_bed_statelist() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			sql1 = "select distinct m.unit_name as medical_unit,m.cmd_name as level_c, \r\n" + 
                    "     off_army,\r\n" + 
                    "         off_fam_army,\r\n" + 
                    "         jco_or_army,\r\n" + 
                    "         jco_or_fam_army,\r\n" + 
                    "         ex_off_army,\r\n" + 
                    "         ex_off_fam_army,\r\n" + 
                    "         ex_jco_or_army,\r\n" + 
                    "         ex_jco_or_fam_army,\r\n" +  
                    "         cadet,\r\n" + 
                    "         rect_agniveer,\r\n" + 
                    "         auth_beds,\r\n" + 
                    "         beds_laid_out,\r\n" + 
                    "         total_no_of_patients,\r\n" + 
                    "         bed_occ_per_as_per_auth_bed,\r\n" + 
                    "        bed_occ_per_as_per_laid_out_bed,para_mil_pers,para_family,\r\n" + 
                    "        other_ne,other_family \r\n" + 
                    
                    "from tb_med_daily_bed_occupancy a\r\n" + 
                    "inner join orbat_all_details_view m on m.sus_no = a.sus_no and m.status_sus_no='Active' \r\n" + 
                    "where  a.dt  = CURRENT_DATE and a.total_no_of_patients != '0'";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
		
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("medical_unit")); //0
				list.add(rs1.getString("level_c")); //1			
				list.add(rs1.getString("off_army"));  //2
				list.add(rs1.getString("off_fam_army"));  //3
				list.add(rs1.getString("jco_or_army"));  //4
				list.add(rs1.getString("jco_or_fam_army"));  //5
				list.add(rs1.getString("ex_off_army")); //6
				list.add(rs1.getString("ex_off_fam_army"));	 //7	
				list.add(rs1.getString("ex_jco_or_army")); //8
				list.add(rs1.getString("ex_jco_or_fam_army")); //9
				
				
		
				list.add(rs1.getString("auth_beds")); //26
				list.add(rs1.getString("beds_laid_out"));	 //27	
				list.add(rs1.getString("total_no_of_patients")); //28
				list.add(rs1.getString("bed_occ_per_as_per_laid_out_bed")); //29
				list.add(rs1.getString("bed_occ_per_as_per_auth_bed")); //30
				
				list.add(rs1.getString("para_mil_pers")); //31
				list.add(rs1.getString("para_family"));  //32
				list.add(rs1.getString("other_ne")); //33
				list.add(rs1.getString("other_family")); //34
				list.add(rs1.getString("para_mil_pers")); //35
				list.add(rs1.getString("para_family")); //36
				list.add(rs1.getString("other_ne")); //37
				list.add(rs1.getString("other_family")); //38
				
				
				
				lista.add(list);
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
		
		return lista;
	}
	
	public ArrayList<List<String>> daily_bed_statelist_army() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			 sql1 = "select  \r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ SOUTHERN COMMAND') as SC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ WESTERN COMMAND') as WC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ EASTERN COMMAND') as EC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ CENTRAL COMMAND') as CC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ NORTHERN COMMAND') as NC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC,\r\n" + 
                     "count(a.sus_no) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC\r\n" + 
                     "\r\n" + 
                     "from tb_med_daily_bed_occupancy a\r\n" + 
                     "inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
                     "where  a.dt  = CURRENT_DATE\r\n" + 
                     "                                        \r\n" + 
                     "                                                                        \r\n" + 
                     "";
			
			ResultSet rs1 = stmt.executeQuery(sql1);
		
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("SC")); //0
				list.add(rs1.getString("WC")); //1
				list.add(rs1.getString("EC")); //2
				list.add(rs1.getString("CC")); //3
				list.add(rs1.getString("NC")); //4
				list.add(rs1.getString("SWC")); //5
				list.add(rs1.getString("ARTRAC")); //6
				
				
				
				lista.add(list);
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
		
		return lista;
	}
	
	public ArrayList<List<String>> daily_vip_admission_army() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			sql1 = "select  \r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ SOUTHERN COMMAND') as SC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ WESTERN COMMAND') as WC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ EASTERN COMMAND') as EC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ CENTRAL COMMAND') as CC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ NORTHERN COMMAND') as NC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC,\r\n" + 
                    "count(a.sus_no) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC\r\n" + 
                    "\r\n" + 
                    "from tb_med_daily_hosp_adm_off_vip a\r\n" + 
                    "inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
                    "where  a.dt_report  = CURRENT_DATE\r\n" + 
                    "                                        \r\n" + 
                    "                                                                        \r\n" + 
                    "";
			
		
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("SC")); //0
				list.add(rs1.getString("WC")); //1
				list.add(rs1.getString("EC")); //2
				list.add(rs1.getString("CC")); //3
				list.add(rs1.getString("NC")); //4
				list.add(rs1.getString("SWC")); //5
				list.add(rs1.getString("ARTRAC")); //6
				
				lista.add(list);
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
		
		return lista;
	}
	
	
	public ArrayList<List<String>> daily_infection_disease_army() {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String sql1 = "";
			sql1 = "select distinct \r\n" + 
					"d.disease_name ,count(d.disease_name) filter (where level_c ='HQ SOUTHERN COMMAND' ) as SC,"
					+ "count(d.disease_name) filter (where level_c ='HQ WESTERN COMMAND' ) as WC,\r\n" + 
					"count(d.disease_name) filter (where level_c ='HQ EASTERN COMMAND') as EC,\r\n" + 
					"count(d.disease_name) filter (where level_c ='HQ CENTRAL COMMAND') as CC,\r\n" + 
					"count(d.disease_name) filter (where level_c ='HQ NORTHERN COMMAND') as NC,\r\n" + 
					"count(d.disease_name) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC,\r\n" + 
					"count(d.disease_name) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC\r\n" + 
					"\r\n" + 
					"from tb_med_eir a \r\n" + 
					"inner join tb_med_daily_surv_disease_mstr d on d.id = a.disease\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no \r\n" + 
					"where datee  = CURRENT_DATE  and (d.disease_name ='MALARIA' or d.disease_name ='DENGUE' or d.disease_name ='CHICKENPOX'\r\n" + 
					"or d.disease_name ='OTHERS')\r\n" + 
					"group by d.disease_name ";
			
		
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			while (rs1.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs1.getString("disease_name")); //0
				list.add(rs1.getString("SC")); //1
				list.add(rs1.getString("WC")); //2
				list.add(rs1.getString("EC")); //3
				list.add(rs1.getString("CC")); //4
				list.add(rs1.getString("NC")); //5
				list.add(rs1.getString("SWC")); //6
				list.add(rs1.getString("ARTRAC")); //7
				
				lista.add(list);
				
				
				//lista += "{'data1': '"+rs1.getString("dis")+"', 'data2': '" + rs1.getString("disease_name") + "' } ";
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
		
		return lista;
	}
	public String daily_vip_admission_army_bar() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			 qry = "select distinct count(a.disease) as dis,(d.disease_name) as disease_name from tb_med_eir a \r\n" + 
	            		"inner join tb_med_daily_surv_disease_mstr d on d.id = a.disease \r\n" + 
	            		"where datee  = CURRENT_DATE \r\n" + 
	            		"group by d.disease_name order by 1 desc limit 10";
			 
			
			qry = "select  \r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ SOUTHERN COMMAND') as SC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ WESTERN COMMAND') as WC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ EASTERN COMMAND') as EC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ CENTRAL COMMAND') as CC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ NORTHERN COMMAND') as NC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC,\r\n" + 
					"count(a.sus_no) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC\r\n" + 
					"\r\n" + 
					"from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE\r\n" + 
					"                                        \r\n" + 
					"									\r\n" + 
					"";
			qry = "select * from (\r\n" + 
					"(select  \r\n" + 
					"'SC' as command,count(a.sus_no) filter (where level_c ='HQ SOUTHERN COMMAND') as SC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"	UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'WC' as command,count(a.sus_no) filter (where level_c ='HQ WESTERN COMMAND') as WC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'EC' as command,count(a.sus_no) filter (where level_c ='HQ EASTERN COMMAND') as EC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'CC' as command,count(a.sus_no) filter (where level_c ='HQ CENTRAL COMMAND') as CC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'NC' as command,count(a.sus_no) filter (where level_c ='HQ NORTHERN COMMAND') as NC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'SWC' as command,count(a.sus_no) filter (where level_c ='HQ SOUTH WESTERN COMMAND') as SWC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"UNION ALL\r\n" + 
					"(select 'ARTRAC' as command,count(a.sus_no) filter (where level_c ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC \r\n" + 
					"from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join tb_miso_orbat_unt_dtl m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE)\r\n" + 
					"\r\n" + 
					" ) a\r\n" + 
					"      ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("sc")+"', 'data2': '" + rs.getString("command") + "' } ";
				
				
			}
			list += "]";
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
	
	
	
	
	
	
	//dgms
	
	
	
	public String daily_vip_admission_army_dgms() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
		
			qry = "select * from (\r\n" + 
					"(select  \r\n" + 
					"'SC' as command,count(a.sus_no) filter (where cmd_name='HQ SOUTHERN COMMAND') as SC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"	UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'WC' as command,count(a.sus_no) filter (where cmd_name ='HQ WESTERN COMMAND') as WC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'EC' as command,count(a.sus_no) filter (where cmd_name ='HQ EASTERN COMMAND') as EC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'CC' as command,count(a.sus_no) filter (where cmd_name ='HQ CENTRAL COMMAND') as CC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'NC' as command,count(a.sus_no) filter (where cmd_name ='HQ NORTHERN COMMAND') as NC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'SWC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTH WESTERN COMMAND') as SWC from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"(select 'ARTRAC' as command,count(a.sus_no) filter (where cmd_name ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC \r\n" + 
					"from tb_med_daily_hosp_adm_off_vip a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"\r\n" + 
					" ) a\r\n" + 
					"      ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("sc")+"', 'data2': '" + rs.getString("command") + "' } ";
				
				
			}
			list += "]";
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
	
	
	public String daily_adm_amc_army_dgms() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();

			qry = "select * from (\r\n" + 
					"(select  \r\n" + 
					"'SC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTHERN COMMAND') as SC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"	UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'WC' as command,count(a.sus_no) filter (where cmd_name ='HQ WESTERN COMMAND') as WC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'EC' as command,count(a.sus_no) filter (where cmd_name ='HQ EASTERN COMMAND') as EC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'CC' as command,count(a.sus_no) filter (where cmd_name ='HQ CENTRAL COMMAND') as CC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'NC' as command,count(a.sus_no) filter (where cmd_name ='HQ NORTHERN COMMAND') as NC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'SWC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTH WESTERN COMMAND') as SWC from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"(select 'ARTRAC' as command,count(a.sus_no) filter (where cmd_name ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC \r\n" + 
					"from tb_med_daily_hosp_adm_amc a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"\r\n" + 
					" ) a ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
		
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("sc")+"', 'data2': '" + rs.getString("command") + "' } ";
				
				
			}
			list += "]";
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
	
	
	public String daily_unusual_occur_dgms() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();

			qry = "select * from (\r\n" + 
					"(select  \r\n" + 
					"'SC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTHERN COMMAND') as SC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"	UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'WC' as command,count(a.sus_no) filter (where cmd_name ='HQ WESTERN COMMAND') as WC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'EC' as command,count(a.sus_no) filter (where cmd_name ='HQ EASTERN COMMAND') as EC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'CC' as command,count(a.sus_no) filter (where cmd_name ='HQ CENTRAL COMMAND') as CC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'NC' as command,count(a.sus_no) filter (where cmd_name ='HQ NORTHERN COMMAND') as NC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'SWC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTH WESTERN COMMAND') as SWC from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"(select 'ARTRAC' as command,count(a.sus_no) filter (where cmd_name ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC \r\n" + 
					"from tb_med_unusual_occurrence a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2')  and m.status_sus_no = 'Active')\r\n" + 
					"\r\n" + 
					" ) a ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
		
		
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("sc")+"', 'data2': '" + rs.getString("command") + "' } ";
				
				
			}
			list += "]";
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
	
	
	public String daily_disease_surv_dtls_dgms() {
		String list = "";
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();

			qry = "select * from (\r\n" + 
					"(select  \r\n" + 
					"'SC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTHERN COMMAND') as SC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"	UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'WC' as command,count(a.sus_no) filter (where cmd_name ='HQ WESTERN COMMAND') as WC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'EC' as command,count(a.sus_no) filter (where cmd_name ='HQ EASTERN COMMAND') as EC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'CC' as command,count(a.sus_no) filter (where cmd_name ='HQ CENTRAL COMMAND') as CC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"	\r\n" + 
					"(select 'NC' as command,count(a.sus_no) filter (where cmd_name ='HQ NORTHERN COMMAND') as NC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"\r\n" + 
					"(select 'SWC' as command,count(a.sus_no) filter (where cmd_name ='HQ SOUTH WESTERN COMMAND') as SWC from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"UNION ALL\r\n" + 
					"(select 'ARTRAC' as command,count(a.sus_no) filter (where cmd_name ='HQ ARMY TRG COMMAND ARTRAC') as ARTRAC \r\n" + 
					"from tb_med_daily_disease_surv_details a\r\n" + 
					"inner join orbat_all_details_view m on m.sus_no = a.sus_no\r\n" + 
					"where  a.dt_report  = CURRENT_DATE and a.persnl_no not in ('-2') and m.status_sus_no = 'Active')\r\n" + 
					"\r\n" + 
					" ) a\r\n" + 
					"      \r\n" + 
					"";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();
			
			list += "[";
			int rowx=0;
			while (rs.next()) {
				if(rowx > 0)
				{
					list += ",";
				} else {
					rowx ++;
				}
				list += "{'data1': '"+rs.getString("sc")+"', 'data2': '" + rs.getString("command") + "' } ";
				
				
			}
			list += "]";
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
	
	public String daily_bed_occupied() {
        String list = "";
        Connection conn = null;
        String qry = "";
        
        String q = "";
        try {
                
                        
                
                conn = dataSource.getConnection();
                 qry = "select sum(beds_laid_out-total_no_of_patients) as balanced,sum(total_no_of_patients) as occupied from tb_med_daily_bed_occupancy \r\n" + 
                                 "where dt = CURRENT_DATE";
                PreparedStatement stmt = conn.prepareStatement(qry);
                
                
                ResultSet rs = stmt.executeQuery();
                list += "[";
                int rowx=0;
                while (rs.next()) {
                        
                        if(rowx > 0)
                        {
                                list += ",";
                        } else {
                        rowx ++;
                        }
                        list += "{'data99': '"+rs.getString("balanced")+"', 'data88': '" + rs.getString("occupied") + "' } ";
                        
                }
                list += "]";
                
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
}