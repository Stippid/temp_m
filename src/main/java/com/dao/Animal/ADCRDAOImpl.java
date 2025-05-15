package com.dao.Animal;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Animal.TB_ANIMAL_ADCR_DTLS_HISTORY;
import com.model.Animal.TB_ANIMAL_CENSUS_DTLS;
import com.models.TB_AVIATION_DAILY_BASIS;
import com.models.TB_TMS_CENSUS_RETURN;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class ADCRDAOImpl implements ADCRDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<List<String>> getADCRReportList(String sus_no, String roleType) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			sql = "select distinct r.unit_sus_no as sus_no,(select unit_name from  tb_miso_orbat_unt_dtl where sus_no = r.unit_sus_no and status_sus_no = 'Active') as unit_name from tb_animal_census_dtls r where unit_sus_no=? and status='1' and animal_type='Army Dog'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			System.err.println("!--" + stmt);
			while (rs.next()) {

				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no")); // 0
				list.add(rs.getString("unit_name"));// 1

				String Approved = "onclick=\" View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='btn btn-default btn-xs' " + Approved + " title='View Data'>View</i>";

				String f = "";
				f += appButton;

				list.add(f);
				alist.add(list);
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
		return alist;
	}

	public ArrayList<List<String>> getarmydogList(String sus_no, String roleAccess) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT a.army_no, a.name, " + "ltrim(TO_CHAR(a.date_of_birth, 'dd-mm-yyyy'),'') AS date_of_birth, "
					+ "b.type_splztn, c.type_color, d.type_breed, e.fitness_status, " + "a.microchip_no, a.gender, "
					+ "ltrim(TO_CHAR(a.date_of_tos, 'dd-mm-yyyy'),'') AS date_of_tos, "
					+ "EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.date_of_birth)) AS age " + "FROM tb_animal_census_dtls a "
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization "
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color "
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed "
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status "
					+ "WHERE a.unit_sus_no = ? AND a.status = '1' AND a.animal_type = 'Army Dog'";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			System.out.println("doglist--" + stmt);
			ResultSet rs = stmt.executeQuery();

			String open = "";
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("army_no")); // 0
				list.add(rs.getString("name")); // 1
				list.add(rs.getString("date_of_birth")); // 2
				list.add(rs.getString("type_splztn")); // 3
				list.add(rs.getString("type_color")); // 4
				list.add(rs.getString("type_breed")); // 5
				list.add(rs.getString("fitness_status")); // 6
				list.add(rs.getString("microchip_no")); // 7
				list.add(rs.getString("gender")); // 8
				list.add(rs.getString("date_of_tos")); // 9
				list.add(rs.getString("age")); // 10

				open = "<button class='btn btn-primary btn-sm' onclick=open1('" + rs.getString("army_no")
						+ "')>Update</button> ";

				if (roleAccess.equals("MISO") || roleAccess.equals("Unit")) {
					list.add(open); // 11
				} else {
					list.add("NA");// 11
				}

				alist.add(list);
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
		return alist;
	}

	public TB_ANIMAL_CENSUS_DTLS editadcr(String ArmyNo) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_ANIMAL_CENSUS_DTLS where army_no=:army_no");
		q.setParameter("army_no", ArmyNo);
		TB_ANIMAL_CENSUS_DTLS list = (TB_ANIMAL_CENSUS_DTLS) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public List<String> getAnimalTypesByArmyNo(String armyNo) {
		List<String> animalTypes = new ArrayList<>();
		String query = "SELECT DISTINCT animal_type FROM tb_animal_census_dtls WHERE army_no = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, armyNo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					animalTypes.add(rs.getString("animal_type"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animalTypes;
	}

	public List<String> getAnimalCensusIDByArmyNo(String army_num) {
		List<String> censusId = new ArrayList<>();
		String query = "SELECT DISTINCT id FROM tb_animal_census_dtls WHERE army_no = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, army_num);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					censusId.add(rs.getString("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return censusId;
	}

	public ArrayList<List<String>> getSearchAttributeReportAdcr(String status, String sus_no, String roleType,
			String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";

			if (!status.isEmpty()) {
				qry += " and n.status = ?";
			}

			if (!sus_no.isEmpty()) {
				qry += " and n.sus_no = ?";
			}

			if (!issue_date.isEmpty()) {
				qry += " and cast(n.ason_date as date) = cast(? as date)";
			}

			sql = "select distinct m.sus_no, m.unit_name, ltrim(TO_CHAR(n.ason_date,'dd-mm-yyyy'),'') as ason_dt, ltrim(TO_CHAR(n.approved_dt,'dd-mm-yyyy'),'') as approved_dt "
					+ "from tb_animal_adcr_dtls_history n "
					+ "inner join tb_miso_orbat_unt_dtl m on n.sus_no = m.sus_no where m.status_sus_no = 'Active'"
					+ qry;

			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;

			if (!status.isEmpty()) {
				stmt.setInt(j, Integer.parseInt(status));
				j++;
			}

			if (!sus_no.isEmpty()) {
				stmt.setString(j, sus_no);
				j++;
			}

			if (!issue_date.isEmpty()) {
				stmt.setString(j, issue_date);
				j++;
			}
			System.err.println("searchadcr---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("approved_dt"));
				list.add(rs.getString("ason_dt"));

				String View = "onclick=\"View('" + rs.getString("sus_no") + "')\"";
				String appButton = "<i class='action_icons action_approve' " + View + " title='Approve Data'></i>";

				String seen = "onclick=\"Seen('" + rs.getString("sus_no") + "')\"";
				String seenButton = "<i class='btn btn-default btn-xs' " + seen + " title='View Data'>ADCR PART A</i>";

				String PartB = "onclick=\" ViewB('" + rs.getString("sus_no") + "')\"";
				String PartBButton = "<i class='btn btn-default btn-xs' " + PartB
						+ " title='View Data'>ADCR PART B</i>";

				String f = "";

				if (status.equals("0")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += appButton;
					}
				}

				if (status.equals("1")) {
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						f += seenButton;
						/*f += PartBButton;*/
					}
				}

				if (status.equals("2")) {
					if (roleType.equals("DEO") || roleType.equals("ALL")) {
						f += "No Action Required";
					}
				}
				list.add(f);
				alist.add(list);
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
					e.printStackTrace();
				}
			}
		}
		return alist;
	}

	public ArrayList<List<String>> getADCRReportListForApproval1(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT a.id,a.army_no, a.name, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_birth, 'dd-mm-yyyy'),'') AS date_of_birth, \r\n"
					+ "b.type_splztn, c.type_color, d.type_breed, e.fitness_status, a.microchip_no, a.gender, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_tos, 'dd-mm-yyyy'),'') AS date_of_tos, \r\n"
					+ "EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.date_of_birth)) AS age,\r\n"
					+ "ltrim(TO_CHAR(a.ason_date, 'dd-mm-yyyy'),'') AS ason_dt \r\n"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '0' AND a.animal_type = 'Army Dog'";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("Pendingadcr---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("army_no"));// 1
				list.add(rs.getString("name"));// 2
				list.add(rs.getString("date_of_birth"));// 3
				list.add(rs.getString("type_splztn")); // 4
				list.add(rs.getString("type_color")); // 5
				list.add(rs.getString("type_breed")); // 6
				list.add(rs.getString("fitness_status")); // 7
				list.add(rs.getString("microchip_no")); // 8
				list.add(rs.getString("gender")); // 9
				list.add(rs.getString("date_of_tos")); // 10
				list.add(rs.getString("age")); // 11
				list.add(rs.getString("ason_dt")); // 12
				alist.add(list);
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
		return alist;
	}

	public String setApprovedadcr(String sus_no, String username, String issue_date) {
		Session session = null;
		Transaction tx = null;
		int app = 0;
		int archived = 0;
		Date dt = new Date();
		Date asonDate = null;

		String timeZone = "Asia/Kolkata";

		
		String[] tableNames = { "tb_animal_adcr_dtls_history",
								"tb_animal_employment_history",
								"tb_animal_utilization_history", 
								"tb_animal_hosp_history",
								"tb_animal_vaccination_history",
								"tb_animal_training_history",
								"tb_animal_drug_history", 
								"tb_animal_award_history" };

		try {
			
			asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			System.err.println("asonDate-" + asonDate);
			if (asonDate == null) {
				return "ADCR not Approved due to invalid date format.";
			}

			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (String tableName : tableNames) {

				
				String countStatus0Query = "select count(id) from " + tableName
						+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = 0";
				Query query0 = session.createSQLQuery(countStatus0Query);
				query0.setParameter("sus_no", sus_no);
				query0.setParameter("ason_date", asonDate);
				BigInteger count0 = (BigInteger) query0.uniqueResult();

				
				String countStatus1Query = "select count(id) from " + tableName
						+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = 1";
				Query query1 = session.createSQLQuery(countStatus1Query);
				query1.setParameter("sus_no", sus_no);
				query1.setParameter("ason_date", asonDate);
				BigInteger count1 = (BigInteger) query1.uniqueResult();

				
				if (count0.longValue() > 0 && count1.longValue() > 0) {
					
					String update1To2 = "update " + tableName
							+ " set status = 2, modified_date = :modified_date, modified_by = :modified_by "
							+ "where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = 1";
					Query updateQuery1 = session.createSQLQuery(update1To2);
					updateQuery1.setParameter("modified_date", dt);
					updateQuery1.setParameter("modified_by", username);
					updateQuery1.setParameter("sus_no", sus_no);
					updateQuery1.setParameter("ason_date", asonDate);
					int affected = updateQuery1.executeUpdate();
					archived += affected;

				} 
			}

			for (String tableName : tableNames) {

				String countQuery = "select count(id) from " + tableName
						+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = :status";
				Query countQ = session.createSQLQuery(countQuery);
				countQ.setParameter("sus_no", sus_no);
				countQ.setParameter("ason_date", asonDate);
				countQ.setParameter("status", 0);
				BigInteger result = (BigInteger) countQ.uniqueResult();
				Long count = result.longValue();

				if (count > 0) {
					
					String updateQuery = "update " + tableName
							+ " set status = :status, approved_dt = :approved_dt, approved_by = :approved_by "
							+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status ='0'";

					Query updateQ = session.createSQLQuery(updateQuery);
					updateQ.setParameter("status", 1); // Approved status
					updateQ.setParameter("approved_dt", dt);
					updateQ.setParameter("approved_by", username);
					updateQ.setParameter("sus_no", sus_no);
					updateQ.setParameter("ason_date", asonDate);

					int rowsAffected = updateQ.executeUpdate();
					System.out.println("Rows affected in " + tableName + ": " + rowsAffected);

					app += rowsAffected;
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return "ADCR not Approved due to error: " + e.getMessage();
		} finally {
			if (session != null)
				session.close();
		}

		if (archived > 0 && app > 0) {
			return "ADCR Approved Successfully.";
		} else if (archived == 0 && app > 0) {
			return "ADCR Approved Successfully.";
		} else {
			return "ADCR not Approved";
		}
	}

	/*public String setApprovedadcr(String sus_no, String username, String issue_date) {
		Session session = null;
		Transaction tx = null;
		int app = 0;
		Date dt = new Date();
		Date asonDate = null;

		String timeZone = "Asia/Kolkata";

		
		String[] tableNames = { "tb_animal_adcr_dtls_history",
								"tb_animal_employment_history",
								"tb_animal_utilization_history", 
								"tb_animal_hosp_history",
								"tb_animal_vaccination_history",
								"tb_animal_training_history",
								"tb_animal_drug_history", 
								"tb_animal_award_history" };

		try {
			
			asonDate = parseDateWithTimeZone(issue_date, "dd-MM-yyyy", timeZone);
			System.err.println("asonDate-" + asonDate);
			if (asonDate == null) {
				return "ADCR not Approved due to invalid date format.";
			}

			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			for (String tableName : tableNames) {

				String countQuery1 = "select count(id) from " + tableName
						+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = :status";
				Query countQ1 = session.createSQLQuery(countQuery1);
				countQ1.setParameter("sus_no", sus_no);
				countQ1.setParameter("ason_date", asonDate);
				countQ1.setParameter("status", 1);
				BigInteger result1 = (BigInteger) countQ1.uniqueResult();
				Long count1 = result1.longValue();

				if (count1 > 0) {
					
					String updateQuery1 = "update " + tableName
							+ " set status = :status, modified_date = :modified_date, modified_by = :modified_by "
							+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status ='1'";

					Query updateQ1 = session.createSQLQuery(updateQuery1);
					updateQ1.setParameter("status", 2); // Maintain History status
					updateQ1.setParameter("modified_date", dt);
					updateQ1.setParameter("modified_by", username);
					updateQ1.setParameter("sus_no", sus_no);
					updateQ1.setParameter("ason_date", asonDate);
					
					int rowsAffected1 = updateQ1.executeUpdate();
					System.out.println("Rows affected1 in " + tableName + ": " + rowsAffected1);

				}
			}

			for (String tableName : tableNames) {

				String countQuery = "select count(id) from " + tableName
						+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status = :status";
				Query countQ = session.createSQLQuery(countQuery);
				countQ.setParameter("sus_no", sus_no);
				countQ.setParameter("ason_date", asonDate);
				countQ.setParameter("status", 0);
				BigInteger result = (BigInteger) countQ.uniqueResult();
				Long count = result.longValue();

				if (count > 0) {
					
					String updateQuery = "update " + tableName
							+ " set status = :status, approved_dt = :approved_dt, approved_by = :approved_by "
							+ " where sus_no = :sus_no and cast(ason_date as date) = :ason_date and status ='0'";

					Query updateQ = session.createSQLQuery(updateQuery);
					updateQ.setParameter("status", 1); // Approved status
					updateQ.setParameter("approved_dt", dt);
					updateQ.setParameter("approved_by", username);
					updateQ.setParameter("sus_no", sus_no);
					updateQ.setParameter("ason_date", asonDate);

					int rowsAffected = updateQ.executeUpdate();
					System.out.println("Rows affected in " + tableName + ": " + rowsAffected);

					app += rowsAffected;
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return "ADCR not Approved due to error: " + e.getMessage();
		} finally {
			if (session != null)
				session.close();
		}

		if (app > 0) {
			return "ADCR Approved Successfully.";
		} else {
			return "ADCR not Approved";
		}
	}*/
	
	// Helper method to parse date
	private Date parseDateWithTimeZone(String dateString, String format, String timeZone) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public @ResponseBody List<String> getwepeno(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select wepe_trans_no from CUE_TB_WEPE_link_sus_perstransweapon where sus_no =:sus_no");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getFormationDetailsFromSusNo(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select o.unit_name, coalesce(o.cmd_name,'-') as cmd_name,\r\n"
					+ "	coalesce(o.coprs_name,'-') as coprs_name,coalesce(o.div_name,'-') as div_name,\r\n"
					+ "	coalesce(o.bde_name,'-') as bde_name ,\r\n"
					+ "	coalesce(string_agg(c.modification,','),'NA') as mod, \r\n"
					+ "	coalesce(o.location,'-') as loc,\r\n" + "	coalesce(o.nrs,'-') as nrs"
					+ "	from orbat_all_details_view o\r\n"
					+ "	left join cue_tb_wepe_link_sus_trans_mdfs c on o.sus_no = c.sus_no\r\n"
					+ "	where status_sus_no='Active' and o.sus_no = ? \r\n" + "group by 1,2,3,4,5,7,8";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("error", "An Error Occure" + e.getMessage());
			list.add(columns);
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
	
	public ArrayList<List<String>> getADCRReportListForApproval(String sus_no, String roleType, String issue_date) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "SELECT a.id,a.army_no, a.name, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_birth, 'dd-mm-yyyy'),'') AS date_of_birth, \r\n"
					+ "b.type_splztn, c.type_color, d.type_breed, e.fitness_status, a.microchip_no, a.gender, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_tos, 'dd-mm-yyyy'),'') AS date_of_tos, \r\n"
					+ "EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.date_of_birth)) AS age,\r\n"
					+ "ltrim(TO_CHAR(a.ason_date, 'dd-mm-yyyy'),'') AS ason_dt \r\n"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '1' AND a.animal_type = 'Army Dog'";

			if (!issue_date.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!issue_date.equals("")) {
				stmt.setString(2, issue_date);
			}
			System.err.println("Appadcr---" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("army_no"));// 1
				list.add(rs.getString("name"));// 2
				list.add(rs.getString("date_of_birth"));// 3
				list.add(rs.getString("type_splztn")); // 4
				list.add(rs.getString("type_color")); // 5
				list.add(rs.getString("type_breed")); // 6
				list.add(rs.getString("fitness_status")); // 7
				list.add(rs.getString("microchip_no")); // 8
				list.add(rs.getString("gender")); // 9
				list.add(rs.getString("date_of_tos")); // 10
				list.add(rs.getString("age")); // 11
				list.add(rs.getString("ason_dt")); // 12
				alist.add(list);
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
		return alist;
	}
	//pending
	public List<Map<String, Object>> getbasicdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "SELECT distinct a.id,a.army_no, a.name, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_birth, 'dd-mm-yyyy'),'') AS date_of_birth, \r\n"
					+ "b.type_splztn, c.type_color, d.type_breed, e.fitness_status, a.microchip_no, a.gender, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_tos, 'dd-mm-yyyy'),'') AS date_of_tos, \r\n"
					+ "EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.date_of_birth)) AS age \r\n"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '0' AND a.animal_type = 'Army Dog'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Appadcr11---" + stmt);
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
	
	@Override
	public long getbasicdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "SELECT count(a.*)"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '0' AND a.animal_type = 'Army Dog'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Appadcr---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getdeploumentdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,e.fitness_status,\r\n" + 
					"ltrim(TO_CHAR(a.date_of_admited, 'dd-mm-yyyy'),'') AS date_of_admited,\r\n" + 
					"d.emp_type,a.loc,a.emp_name,a.emp_no,c.unit_name \r\n" + 
					"from tb_animal_employment_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.emp_sus = c.sus_no \r\n" + 
					"left join tb_tms_employment_master d on a.employment = d.id\r\n" + 
					"left join tb_tms_animal_fitness_status e ON e.id = a.fit_status\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dep1---" + stmt);
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
	
	@Override
	public long getdeploymentdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*) " +
					"from tb_animal_employment_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.emp_sus = c.sus_no \r\n" + 
					"left join tb_tms_employment_master d on a.employment = d.id\r\n" + 
					"left join tb_tms_animal_fitness_status e ON e.id = a.fit_status\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dep---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getHMdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,d.type_hospital ,a.agr, a.albun,\r\n" + 
					"a.bill, a.bun, a.creatinine, a.dlc, a.hemoglobin,\r\n" + 
					"a.pcv,a.platelet, a.protein, a.sgot, a.stool, a.tlc,\r\n" + 
					"a.trbc, a.urine, a.weight \r\n" +
					"from tb_animal_hosp_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_tms_animal_hospital_master d on a.hosp_name = d.id\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Hm1---" + stmt);
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
	
	@Override
	public long getHMdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_hosp_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_tms_animal_hospital_master d on a.hosp_name = d.id\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("hm---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getVCdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, c.vaccine_name,c.frequency,a.allergy \r\n" + 
					"from tb_animal_vaccination_history a\r\n" + 
					"left join tb_animal_census_dtls b on b.id=a.census_id \r\n" + 
					"left join tb_tms_vaccine_master c on c.id = a.vaccinet \r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vc1---" + stmt);
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
	
	@Override
	public long getVCdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_vaccination_history a \r\n" + 
					"left join tb_animal_census_dtls b on b.id=a.census_id \r\n" + 
					"left join tb_tms_vaccine_master c on c.id = a.vaccinet \r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vc---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getTRGdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,c.unit_name as trainer_unit,a.trainer_perno,\r\n" + 
					"a.trainer_name,a.trainer_rank,a.trainer_mobno,\r\n" +
					"case \r\n" + 
					"	when a.trg_status='1' then 'Satisfactory' \r\n" + 
					"	when a.trg_status='2' then 'Good' \r\n" + 
					"	when a.trg_status='3' then 'Excellent' \r\n" + 
					"	ELSE 'NA' END as trg_status, \r\n" + 
					"ltrim(TO_CHAR(a.training_from_date, 'dd-mm-yyyy'),'') AS trg_from,\r\n" + 
					"ltrim(TO_CHAR(a.training_to_date, 'dd-mm-yyyy'),'') AS trg_to \r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.trainer_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("trg1---" + stmt);
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
	
	@Override
	public long getTRGdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.trainer_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("trg---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getVDdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,c.unit_name as tester_unit,a.tester_perno,\r\n" + 
					"a.tester_name,a.tester_rank,\r\n" + 
					"case \r\n" + 
					"	when a.exam_status='1' then 'Satisfactory' \r\n" + 
					"	when a.exam_status='2' then 'Good' \r\n" + 
					"	when a.exam_status='3' then 'Excellent' \r\n" + 
					"	ELSE 'NA' END as exam_status, \r\n" + 
					"a.tester_remark \r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.tester_sus = c.sus_no \r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vd1---" + stmt);
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
	
	@Override
	public long getVDdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.tester_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '0'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vd---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getDWdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, a.used_drug, a.allergy\r\n" + 
					"from tb_animal_drug_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"where a.sus_no = ? and  a.status = '0' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dw1---" + stmt);
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
	
	@Override
	public long getDWdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_drug_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"where a.sus_no = ? and  a.status = '0' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dw---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getawdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, c.award_type, a.remarks\r\n" + 
					"from tb_animal_award_history a \r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_animals_award_master c on a.award = c.id\r\n" + 
					"where a.sus_no = ? and  a.status = '0' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("aw1---" + stmt);
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
	
	@Override
	public long getawdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_award_history a \r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_animals_award_master c on a.award = c.id\r\n" + 
					"where a.sus_no = ? and  a.status = '0' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("aw---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getutdataTablePending(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,a.area_kms,a.detected_avalnches,a.detected_debris,\r\n" + 
					"a.detected_escape,a.detected_explosive,a.detected_mine,a.esitrep_num,a.hideout_enemy,\r\n" + 
					"a.no_of_arms,a.no_of_article,a.no_of_reftrg,a.no_of_shbo,a.no_of_veh,a.ops_apprehended,\r\n" + 
					"a.ops_killed,a.ops_room,a.ptr_km,a.sanitisation_duty,a.total_guard,a.total_rop,a.v_duties,a.remarks \r\n" + 
					"from tb_animal_utilization_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"where a.sus_no = ? and a.status = '0' and b.specialization= ? ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setInt(2, Integer.parseInt(spl));
			if (!asondate.equals("")) {
				stmt.setString(3, asondate);
			}
			System.err.println("ut1---" + stmt);
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
	
	@Override
	public long getutdataTableCountPending(String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_utilization_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"where a.sus_no = ? and a.status = '0' and b.specialization= ? ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setInt(2, Integer.parseInt(spl));
			if (!asondate.equals("")) {
				stmt.setString(3, asondate);
			}
			System.err.println("ut---" + stmt);
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
		return total;
	}

	
	//aprroved
	public List<Map<String, Object>> getbasicdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "SELECT distinct a.id,a.army_no, a.name, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_birth, 'dd-mm-yyyy'),'') AS date_of_birth, \r\n"
					+ "b.type_splztn, c.type_color, d.type_breed, e.fitness_status, a.microchip_no, a.gender, \r\n"
					+ "ltrim(TO_CHAR(a.date_of_tos, 'dd-mm-yyyy'),'') AS date_of_tos, \r\n"
					+ "EXTRACT(YEAR FROM AGE(CURRENT_DATE, a.date_of_birth)) AS age \r\n"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '1' AND a.animal_type = 'Army Dog'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Appadcr11---" + stmt);
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
	
	@Override
	public long getbasicdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "SELECT count(a.*)"
					+ "FROM tb_animal_adcr_dtls_history a \r\n"
					+ "LEFT JOIN tb_tms_specialization_master b ON b.id = a.specialization \r\n"
					+ "LEFT JOIN tb_tms_color_master c ON c.id = a.color \r\n"
					+ "LEFT JOIN tb_tms_breed_master d ON d.id = a.breed \r\n"
					+ "LEFT JOIN tb_tms_animal_fitness_status e ON e.id = a.fitness_status \r\n"
					+ "WHERE a.sus_no = ? and a.status = '1' AND a.animal_type = 'Army Dog'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Appadcr---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getdeploumentdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,e.fitness_status,\r\n" + 
					"ltrim(TO_CHAR(a.date_of_admited, 'dd-mm-yyyy'),'') AS date_of_admited,\r\n" + 
					"d.emp_type,a.loc,a.emp_name,a.emp_no,c.unit_name \r\n" + 
					"from tb_animal_employment_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.emp_sus = c.sus_no \r\n" + 
					"left join tb_tms_employment_master d on a.employment = d.id\r\n" + 
					"left join tb_tms_animal_fitness_status e ON e.id = a.fit_status\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dep1---" + stmt);
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
	
	@Override
	public long getdeploymentdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*) " +
					"from tb_animal_employment_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.emp_sus = c.sus_no \r\n" + 
					"left join tb_tms_employment_master d on a.employment = d.id\r\n" + 
					"left join tb_tms_animal_fitness_status e ON e.id = a.fit_status\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dep---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getHMdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,d.type_hospital ,a.agr, a.albun,\r\n" + 
					"a.bill, a.bun, a.creatinine, a.dlc, a.hemoglobin,\r\n" + 
					"a.pcv,a.platelet, a.protein, a.sgot, a.stool, a.tlc,\r\n" + 
					"a.trbc, a.urine, a.weight \r\n" +
					"from tb_animal_hosp_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_tms_animal_hospital_master d on a.hosp_name = d.id\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("Hm1---" + stmt);
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
	
	@Override
	public long getHMdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_hosp_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_tms_animal_hospital_master d on a.hosp_name = d.id\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("hm---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getVCdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, c.vaccine_name,c.frequency,a.allergy \r\n" + 
					"from tb_animal_vaccination_history a\r\n" + 
					"left join tb_animal_census_dtls b on b.id=a.census_id \r\n" + 
					"left join tb_tms_vaccine_master c on c.id = a.vaccinet \r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vc1---" + stmt);
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
	
	@Override
	public long getVCdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_vaccination_history a \r\n" + 
					"left join tb_animal_census_dtls b on b.id=a.census_id \r\n" + 
					"left join tb_tms_vaccine_master c on c.id = a.vaccinet \r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vc---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getTRGdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,c.unit_name as trainer_unit,a.trainer_perno,\r\n" + 
					"a.trainer_name,a.trainer_rank,a.trainer_mobno,\r\n" +
					"case \r\n" + 
					"	when a.trg_status='1' then 'Satisfactory' \r\n" + 
					"	when a.trg_status='2' then 'Good' \r\n" + 
					"	when a.trg_status='3' then 'Excellent' \r\n" + 
					"	ELSE 'NA' END as trg_status, \r\n" + 
					"ltrim(TO_CHAR(a.training_from_date, 'dd-mm-yyyy'),'') AS trg_from,\r\n" + 
					"ltrim(TO_CHAR(a.training_to_date, 'dd-mm-yyyy'),'') AS trg_to \r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.trainer_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("trg1---" + stmt);
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
	
	@Override
	public long getTRGdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.trainer_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("trg---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getVDdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,c.unit_name as tester_unit,a.tester_perno,\r\n" + 
					"a.tester_name,a.tester_rank,\r\n" + 
					"case \r\n" + 
					"	when a.exam_status='1' then 'Satisfactory' \r\n" + 
					"	when a.exam_status='2' then 'Good' \r\n" + 
					"	when a.exam_status='3' then 'Excellent' \r\n" + 
					"	ELSE 'NA' END as exam_status, \r\n" + 
					"a.tester_remark \r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.tester_sus = c.sus_no \r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vd1---" + stmt);
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
	
	@Override
	public long getVDdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_training_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"left join tb_miso_orbat_unt_dtl c on a.tester_sus = c.sus_no\r\n" + 
					"where a.sus_no = ? and a.status = '1'";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("vd---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getDWdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, a.used_drug, a.allergy\r\n" + 
					"from tb_animal_drug_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"where a.sus_no = ? and  a.status = '1' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dw1---" + stmt);
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
	
	@Override
	public long getDWdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_drug_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id \r\n" + 
					"where a.sus_no = ? and  a.status = '1' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("dw---" + stmt);
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
		return total;
	}

	public List<Map<String, Object>> getawdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name, c.award_type, a.remarks\r\n" + 
					"from tb_animal_award_history a \r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_animals_award_master c on a.award = c.id\r\n" + 
					"where a.sus_no = ? and  a.status = '1' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("aw1---" + stmt);
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
	
	@Override
	public long getawdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_award_history a \r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"left join tb_animals_award_master c on a.award = c.id\r\n" + 
					"where a.sus_no = ? and  a.status = '1' ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			if (!asondate.equals("")) {
				stmt.setString(2, asondate);
			}
			System.err.println("aw---" + stmt);
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
		return total;
	}
	
	public List<Map<String, Object>> getutdataTable(int startPage, int pageLength,
			String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";
		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			sql = "select distinct a.id,b.army_no, b.name,a.area_kms,a.detected_avalnches,a.detected_debris,\r\n" + 
					"a.detected_escape,a.detected_explosive,a.detected_mine,a.esitrep_num,a.hideout_enemy,\r\n" + 
					"a.no_of_arms,a.no_of_article,a.no_of_reftrg,a.no_of_shbo,a.no_of_veh,a.ops_apprehended,\r\n" + 
					"a.ops_killed,a.ops_room,a.ptr_km,a.sanitisation_duty,a.total_guard,a.total_rop,a.v_duties,a.remarks \r\n" + 
					"from tb_animal_utilization_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"where a.sus_no = ? and a.status = '1' and b.specialization= ? ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date) order by a.id asc";

			}

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setInt(2, Integer.parseInt(spl));
			if (!asondate.equals("")) {
				stmt.setString(3, asondate);
			}
			System.err.println("ut1---" + stmt);
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
	
	@Override
	public long getutdataTableCount(String orderColunm, String orderType, String sus_no, String unitname, String spl, String asondate,
			HttpSession sessionUserId) {
		

		int total = 0;
		String sql = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			sql = "select count(a.*)\r\n" + 
					"from tb_animal_utilization_history a\r\n" + 
					"left join tb_animal_census_dtls b on  b.id=a.census_id\r\n" + 
					"where a.sus_no = ? and a.status = '1' and b.specialization= ? ";

			if (!asondate.equals("")) {
				sql += " and cast(a.ason_date as date) = cast(? as date)";

			}


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setInt(2, Integer.parseInt(spl));
			if (!asondate.equals("")) {
				stmt.setString(3, asondate);
			}
			System.err.println("ut---" + stmt);
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
		return total;
	}


}
