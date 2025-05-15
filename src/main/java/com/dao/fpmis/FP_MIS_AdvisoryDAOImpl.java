package com.dao.fpmis;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtil;

public class FP_MIS_AdvisoryDAOImpl implements FP_MIS_AdvisoryDAO {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean UploadDataSave(String roleSusNo, String advisory_in_details, String file_name,String adv_type, Date upload_from_date,
			Date upload_to_date, String username) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into fp.fp_tb_advisory (sus_no,advisory_in_details,file_name,upload_from_date,upload_to_date,upload_date,upload_by,adv_type) values(?,?,?,?,?,now(),?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleSusNo);
			stmt.setString(2, advisory_in_details);
			stmt.setString(3, file_name);
			stmt.setDate(4, new java.sql.Date(upload_from_date.getTime()));
			stmt.setDate(5, new java.sql.Date(upload_to_date.getTime()));
			stmt.setString(6, username);
			stmt.setString(7, adv_type);
			stmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public String getAdvisoryFileName(int file_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String fileName = "";
		try {
			conn = dataSource.getConnection();
			String sql = "select file_name from fp.fp_tb_advisory where id = ? and now()::date between upload_from_date and upload_to_date";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, file_id);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				fileName = rs.getString("file_name");
			}
			
		} catch (SQLException e) {
			fileName = "";
		} finally {
			if (conn != null) {
				try {
					rs.close();
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					fileName = "";
				}
			}
		}
		return fileName;
	}
	
	
	public ArrayList<ArrayList<String>> getSearchAdvisoryData(String advisory, String frm_dt, String to_dt,String adv_type) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			 frm_dt = frm_dt.length() == 0 ? null : frm_dt;
			 to_dt = to_dt.length() == 0 ? null : to_dt;
			 adv_type = adv_type.length() == 0 ? null : adv_type;
			 advisory = advisory == null ? "" : advisory;

			q = "select id,advisory_in_details adv_dtl,adv_type,to_char(upload_from_date,'dd-mm-yyyy') as upload_from_date from fp.fp_tb_advisory where upload_from_date::date >= "
					+ "coalesce(?::date,upload_from_date) and upload_to_date::date <= coalesce(?::date,upload_to_date)\r\n"
					+ "and advisory_in_details like coalesce(?,advisory_in_details) and adv_type = coalesce(?,adv_type) and now()::date between upload_from_date::date and upload_to_date::date "
					+ "order by adv_type desc,upload_from_date desc,upload_to_date desc,advisory_in_details";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, frm_dt);
			stmt.setString(2, to_dt);
			stmt.setString(3, "%" + advisory + "%");
			stmt.setString(4, adv_type);

			ResultSet rs = stmt.executeQuery();
			ArrayList<String> list = null;
			while (rs.next()) {
				list = new ArrayList<String>();
				list.add(rs.getString("adv_dtl"));
				list.add(rs.getString("adv_type"));
				list.add(rs.getString("id"));
				list.add(rs.getString("upload_from_date"));
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
	
	@SuppressWarnings("deprecation")
	public String msg_save(String roleSusNo, String msg1, String msg_to, String datefrom,
			 String dateto, String msgtype) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into fp.mms_tb_msg_mstr (msg_id,msg_desc,msg_status,msg_type,data_cr_date,data_cr_by) ";
			sql += " values ((select coalesce(max(msg_id),0)+1 as id from fp.mms_tb_msg_mstr),?,'1',?,now(),?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, msg1);
			stmt.setString(2, msgtype);
			stmt.setString(3, roleSusNo);
			stmt.executeUpdate();
			
			String sql1 = "insert into fp.mms_tb_msg_hld_detl (id,msg_id,msg_status,msg_src,msg_target,msg_target_sus,msg_start_date,msg_stop_date) ";
			sql1 += " values ((select coalesce(max(id),1)+1 as id from fp.mms_tb_msg_hld_detl),(select coalesce(max(msg_id),1) as id from fp.mms_tb_msg_mstr),'1',?,?,?,?::date,?::date)";
			
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, roleSusNo);
			stmt1.setString(2, msg_to);
			stmt1.setString(3, msg_to);
			stmt1.setString(4, datefrom);
			stmt1.setString(5, dateto);
			stmt1.executeUpdate();			
			conn.close();
			return "1";
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}

	}
	
	public ArrayList<ArrayList<String>> getSearchMsgData() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select a.msg_desc,to_char(b.msg_start_date::date,'dd-mm-yyyy') as msg_start_date,to_char(b.msg_stop_date::date,'dd-mm-yyyy') as msg_stop_date,b.msg_status from fp.mms_tb_msg_mstr a " + 
					" inner join fp.mms_tb_msg_hld_detl b on a.msg_id=b.msg_id " + 
					" order by b.msg_start_date,b.msg_stop_date";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> list = null;
			while (rs.next()) {
				list = new ArrayList<String>();
				list.add(rs.getString("msg_desc"));
				list.add(rs.getString("msg_start_date"));
				list.add(rs.getString("msg_stop_date"));
				list.add(rs.getString("msg_status"));
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
}