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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtilNA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.http.HttpSession;

public class Capture_critical_storeDAOImpl implements Capture_critical_storeDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}

	public List<Map<String, Object>> getReportListCapture_critical_store(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,c_type,category,code,nomenc from cue_tb_miso_capture_crititcal_store " + SearchValue
					+ " ORDER BY " + orderColunm + " " + orderType + " limit " + pageLength + " OFFSET " + startPage;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String enckey = "commonPwdEncKeys";
				Cipher c = hex_asciiDao.EncryptionSHA256Algo(session, enckey);
				String EncryptedPk = new String(
						Base64.encodeBase64(c.doFinal(rs.getString("id").toString().getBytes())));
				String Delete = "onclick=\"  if (confirm('Are you sure you want to Delete?') ){deleteData('"
						+ EncryptedPk + "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>";
				// String Update = "onclick=\" if (confirm('Are you sure you want to Update?')
				// ){editData('"+EncryptedPk+"')}else{ return false;}\"";
				// String updateButton = "<i class='action_icons action_update' " + Update + "
				// title='Edit Data'></i>";
				String f = "";

				// f += updateButton;
				f += deleteButton;
				columns.put(metaData.getColumnLabel(1), f);
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

	public long getReportListCapture_critical_storeTotalCount(String Search) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from cue_tb_miso_capture_crititcal_store  " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search);
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

	public String GenerateQueryWhereClause_SQL(String Search) {
		String SearchValue = "";
		if (!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue = " where ( ";
			if (checkIsIntegerValue(Search)) {
				SearchValue += " id=? or ";
			}
			SearchValue += " lower(c_type) like ? or lower(category) like ? or lower(code) like ? or lower(nomenc) like ?  )";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search) {
		int flag = 0;
		try {
			if (!Search.equals("")) {
				if (checkIsIntegerValue(Search)) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(Search));
				}
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public String Deletecapture_critical_store(String deleteid, HttpSession session1) {

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) deleteid, enckey, session1);
		String hql = "Delete from CUE_TB_MISO_CAPTURE_CRITITCAL_STORE  where cast(id as string) = :deleteid";
		Query q = session.createQuery(hql).setString("deleteid", DcryptedPk);
		int rowCount = q.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}

	/////////////////////////////////////////// report/////////////////////////////////////////////////////

	public List<Map<String, Object>> getReport_by_typelist(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session, String c_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_type(Search, c_type);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select c.code,c.nomenc,c.category,COALESCE(sum(v.ue),'0') as ue,COALESCE(sum(v.total_uh),'0') as total_uh\n"
					+ "from cue_tb_miso_capture_crititcal_store c\n"
					+ "left join tms_vehicle_status_a_b_c_with_ue_uh v on c.code = v.mct_main_id\n" + SearchValue
					+ "  group by 1,2,3 ORDER BY " + orderColunm + " " + orderType + " limit " + pageLength + " OFFSET "
					+ startPage;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_type(stmt, Search, c_type);
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

	public long getReportListBy_typeTotalCount(String Search, String c_type) {
		String SearchValue = GenerateQueryWhereClause_SQL_type(Search, c_type);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(distinct c.code) from cue_tb_miso_capture_crititcal_store c " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_type(stmt, Search, c_type);
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

	public String GenerateQueryWhereClause_SQL_type(String Search, String c_type) {
		String SearchValue = "";
		if (!c_type.equals("")) {
			SearchValue += "where c.c_type=?";
		}
		if (!Search.equals("")) {
			SearchValue += " lower(c.c_type) like ? or lower(c.category) like ? or lower(c.code) like ? or lower(c.nomenc) like ?  )";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_type(PreparedStatement stmt, String Search, String c_type) {
		int flag = 0;
		try {
			if (!c_type.equals("")) {
				flag += 1;
				stmt.setString(flag, c_type);
			}
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public List<Map<String, Object>> getAllCapture_critical_storeItems(String c_type,String code){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select * from cue_tb_miso_capture_crititcal_store where c_type=? and code=? ";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, c_type);
			stmt.setString(2, code);
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