package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.Assets.interUnitTransf_DAO;
import com.models.MMS_RIO_Generation;
import com.models.MMS_RO_Generation;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class MMS_RO_RIO_GenerationDAOImpl implements MMS_RO_RIO_GenerationDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getmaxroCode() {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String query = null;
			query = "select max(right(ro_no,5)) from mms_tb_ro_mstr";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("max");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public ArrayList<ArrayList<String>> RoApproverScreen(String formcodeType, String formcode, String p_code,
			String d_from, String d_to, String ro_status) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cnd = "N";
			String cndValue = "";
			sql1 = "select a.ro_agency,a.ro_no,a.ro_type,t4.label as ro_type_n,a.type_of_issue,t6.label as type_of_issue_n,a.ro_for,t5.label as ro_for_n,a.to_sus_no,or1.unit_name as to_unit_name,a.to_comd_sus,a.prf_code,prf.prf_group, a.type_of_stk,t1.label as type_of_stk_n,"
					+ " a.ro_qty,a.rel_depot_sus,or2.unit_name as depot_name, a.op_status,t3.label as op_status_n,a.id,a.ro_date,a.ro_valid_upto "
					+ " from mms_tb_ro_mstr a left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active')"
					+ " left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active') "
					+ " left join mms_domain_values t1 on (a.type_of_stk=t1.codevalue and t1.domainid='MMSROSTOCK') "
					+ " left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS') "
					+ " left join mms_domain_values t4 on (a.ro_for=t4.codevalue and t4.domainid='MMSTYPEOFRO') "
					+ " left join mms_domain_values t5 on (a.ro_type=t5.codevalue and t5.domainid='MMSRO')"
					+ " left join mms_domain_values t6 on (a.type_of_issue=t6.codevalue and t6.domainid='MMSTYPEOFISSUE') "
					+ " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code ";

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RO")) {
					cndValue = " a.ro_no=?";
					sql1 = sql1 + " where " + cndValue;
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue = "substr(form_code_control,1,10)=?)";
					}
					sql1 = sql1
							+ " where or1.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
					cnd = "Y";
				}
			}

			if (!formcodeType.equalsIgnoreCase("RO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " prf.prf_group_code=?";
					cnd = "Y";
				}

				if (!ro_status.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " (a.op_status=? or a.op_status='2')";
					cnd = "Y";
				}

				if (cnd.equalsIgnoreCase("N")) {
					sql1 = sql1 + " where ";
				} else {
					sql1 = sql1 + " and ";
				}

				if (d_from.equalsIgnoreCase(d_to)) {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				} else {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				}

				sql1 = sql1 + " order by data_upd_date desc";
			}
			PreparedStatement stmt = conn.prepareStatement(sql1);

			int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RO")) {
					stmt.setString(g1, formcode);
					g1++;
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					}
					g1++;
				}
			}

			if (!formcodeType.equalsIgnoreCase("RO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, p_code);
					g1++;
				}

				if (!ro_status.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, ro_status);
					g1++;
				}

				stmt.setString(g1, d_from);
				g1++;
				stmt.setString(g1, d_to);
			}

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("op_status_n"));
					list.add(rs.getString("id"));
					list.add(rs.getString("ro_type"));
					list.add(rs.getString("ro_for"));
					list.add(rs.getString("type_of_issue_n"));
					list.add(rs.getString("type_of_stk_n"));
					list.add(rs.getString("ro_date"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> CmndGnrtScreen(String q) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";

			sql1 = "select a.id,a.ro_agency,a.ro_no,a.ro_type,t4.label as ro_type_n,a.to_sus_no,or1.unit_name as to_unit_name,a.to_comd_sus,a.prf_code,prf.prf_group,"
					+ " a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.ro_qty,SUM(t6.ro_qty) as cur_qty,a.rel_depot_sus,or2.unit_name as depot_name,"
					+ " a.op_status,t3.label as op_status_n,a.ro_for,t5.label as ro_for_n" + " from mms_tb_ro_mstr a"
					+ " left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active')"
					+ " left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active')"
					+ " left join mms_domain_values t1 on (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')"
					+ " left join mms_domain_values t2 on (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')"
					+ " left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS')"
					+ " left join mms_domain_values t4 on (a.ro_type=t4.codevalue and t4.domainid='MMSRO')"
					+ " left join mms_domain_values t5 on (a.ro_for=t5.codevalue and t5.domainid='MMSTYPEOFRO')"
					+ " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code"
					+ " left join mms_tb_comd_ro_mstr t6 on(a.ro_no = t6.ro_no)"
					+ " where a.ro_type='2' and a.op_status='1'"
					+ " group by a.id, t4.label, or1.unit_name, prf.prf_group, t1.label, t2.label, or2.unit_name, t3.label, t5.label";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("op_status_n"));

					int a = Integer.parseInt(rs.getString("ro_qty"));
					int b = 0;
					if (rs.getString("cur_qty") != null) {
						b = Integer.parseInt(rs.getString("cur_qty"));
						list.add(rs.getString("cur_qty"));
					} else {
						b = 0;
						list.add("0");
					}
					list.add(String.valueOf(a - b));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> CmndRoApproverScreen(String formcodeType, String formcode, String p_code,
			String d_from, String d_to, String ro_status) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cnd = "N";
			String cndValue = "";

			sql1 = "select a.ro_agency,a.comd_ro_no,a.ro_type,t4.label as ro_type_n,a.to_sus_no,or1.unit_name as to_unit_name,a.from_comd_sus,a.prf_code,prf.prf_group, \r\n"
					+ "a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.ro_qty,a.rel_depot_sus,or2.unit_name as depot_name, \r\n"
					+ "a.op_status,t3.label as op_status_n,ro_for,t5.label as ro_for_n,a.id from mms_tb_comd_ro_mstr a \r\n"
					+ "left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active') \r\n"
					+ "left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active') \r\n"
					+ "left join mms_domain_values t1 on (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING') \r\n"
					+ "left join mms_domain_values t2 on (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT') \r\n"
					+ "left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS') \r\n"
					+ "left join mms_domain_values t4 on (a.ro_type=t4.codevalue and t4.domainid='MMSRO') \r\n"
					+ "left join mms_domain_values t5 on (a.ro_for=t5.codevalue and t5.domainid='MMSTYPEOFRO') \r\n"
					+ "left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code ";

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RO")) {
					cndValue = " a.ro_no=?";
					sql1 = sql1 + " where " + cndValue;
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue = "substr(form_code_control,1,10)=?)";
					}
					sql1 = sql1
							+ " where or1.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
					cnd = "Y";
				}
			}

			if (!formcodeType.equalsIgnoreCase("RO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " prf.prf_group_code=?";
					cnd = "Y";
				}

				if (!ro_status.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " a.op_status=?";
					cnd = "Y";
				}

				if (cnd.equalsIgnoreCase("N")) {
					sql1 = sql1 + " where ";
				} else {
					sql1 = sql1 + " and ";
				}

				if (d_from.equalsIgnoreCase(d_to)) {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				} else {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				}
				sql1 = sql1 + " order by data_upd_date desc";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);

			int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RO")) {
					stmt.setString(g1, formcode);
					g1++;
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					}
					g1++;
				}
			}

			if (!formcodeType.equalsIgnoreCase("RO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, p_code);
					g1++;
				}

				if (!ro_status.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, ro_status);
					g1++;
				}

				stmt.setString(g1, d_from);
				g1++;
				stmt.setString(g1, d_to);
				g1++;
			}

			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("comd_ro_no"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("op_status_n"));
					list.add(rs.getString("id"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public String approveRORqst(String ro_no, String ro_type, String para, String username) {
		String[] rodiv = ro_no.split(",");
		String[] roall = null;

		if (para.equalsIgnoreCase("RO")) {
			roall = ro_type.split(",");
		}

		Connection conn = null;
		Integer out = 0;

		try {
			conn = dataSource.getConnection();
			if (para.equalsIgnoreCase("RO-1")) {
				for (int i = 0; i < rodiv.length; i++) {
					String ro_no_i = rodiv[i].substring(6, rodiv[i].length());
					PreparedStatement stmt = conn.prepareStatement(
							"update mms_tb_ro_mstr set op_status = 1,data_app_by=?,data_app_date = localtimestamp where ro_no=? and op_status='2'");
					stmt.setString(1, username);
					stmt.setString(2, ro_no_i);
					out = stmt.executeUpdate();
				}
			}

			if (para.equalsIgnoreCase("RO")) {
				for (int j = 0; j < roall.length; j++) {
					String r_no = rodiv[j].substring(6, rodiv[j].length());
					String[] t_ro = roall[j].split(":");
					if (t_ro[1].equalsIgnoreCase("2")) {
						PreparedStatement stmt = conn.prepareStatement(
								"update mms_tb_ro_mstr set op_status = 2,data_app_by=?,data_app_date = localtimestamp where ro_no=? and op_status='0'");
						stmt.setString(1, username);
						stmt.setString(2, r_no);
						out = stmt.executeUpdate();
					} else {
						PreparedStatement stmt = conn.prepareStatement(
								"update mms_tb_ro_mstr set op_status = 1,data_app_by=?,data_app_date = localtimestamp where ro_no=? and op_status='0'");
						stmt.setString(1, username);
						stmt.setString(2, r_no);
						out = stmt.executeUpdate();
					}
				}
			}

			if (para.equalsIgnoreCase("CMDRO")) {
				for (int i = 0; i < rodiv.length; i++) {
					String comd_ro_no = rodiv[i].substring(6, rodiv[i].length());
					PreparedStatement stmt = conn.prepareStatement(
							"update mms_tb_comd_ro_mstr set op_status = 1,data_app_by=?,data_app_date = localtimestamp where comd_ro_no=? and op_status='0'");
					stmt.setString(1, username);
					stmt.setString(2, comd_ro_no);
					out = stmt.executeUpdate();
				}
			}

			if (para.equalsIgnoreCase("RIO")) {
				for (int i = 0; i < rodiv.length; i++) {
					String rio_no = rodiv[i].substring(6, rodiv[i].length());
					PreparedStatement stmt = conn.prepareStatement(
							"update mms_tb_rio_mstr set op_status = 1,data_app_by=?,data_app_date = localtimestamp where rio_no=? and op_status='0'");
					stmt.setString(1, username);
					stmt.setString(2, rio_no);
					out = stmt.executeUpdate();
				}
			}

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

		if (out > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}

	public String Extendro_date(String ro_no, String para, int ro_id) {
		Connection conn = null;
		Integer out = 0;

		try {
			conn = dataSource.getConnection();

			if (para.equalsIgnoreCase("RO")) {
				PreparedStatement stmt = conn.prepareStatement(
						"update mms_tb_ro_mstr set ro_valid_upto = (select NOW() + INTERVAL '90 days' as ndate) where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}

			if (para.equalsIgnoreCase("RIO")) {
				PreparedStatement stmt = conn.prepareStatement(
						"update mms_tb_rio_mstr set rio_valid_upto = (select NOW() + INTERVAL '90 days' as ndate) where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}

			if (para.equalsIgnoreCase("CMDRO")) {
				PreparedStatement stmt = conn.prepareStatement(
						"update mms_tb_comd_ro_mstr set ro_valid_upto = (select NOW() + INTERVAL '90 days' as ndate) where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}
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

		if (out > 0) {
			return "Number Extend Successfully";
		} else {
			return "Number Extend Denied";
		}
	}

	public String ROCancelApprover(String ro_no, String para, int ro_id) {
		Connection conn = null;
		Integer out = 0;

		try {
			conn = dataSource.getConnection();

			if (para.equalsIgnoreCase("RO")) {
				PreparedStatement stmt = conn
						.prepareStatement("update mms_tb_ro_mstr set op_status =" + 21 + " where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}

			if (para.equalsIgnoreCase("CMDRO")) {
				PreparedStatement stmt = conn
						.prepareStatement("update mms_tb_comd_ro_mstr set op_status =" + 21 + " where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}

			if (para.equalsIgnoreCase("RIO")) {
				PreparedStatement stmt = conn
						.prepareStatement("update mms_tb_rio_mstr set op_status =" + 21 + " where id=?");
				stmt.setInt(1, ro_id);
				out = stmt.executeUpdate();
			}
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

		if (out > 0) {
			return "Number Cancelled Successfully.";
		} else {
			return "Number Cancel Denied";
		}
	}

	public MMS_RO_Generation viewMMSCMNDRo(int ro_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		// MMS_RO_Generation ro = (MMS_RO_Generation)
		// session.get(MMS_RO_Generation.class,ro_no);
		Query q = session.createQuery("from MMS_RO_Generation where id=:id");
		q.setParameter("id", ro_no);
		MMS_RO_Generation list = (MMS_RO_Generation) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public ArrayList<ArrayList<String>> RoPrintScreen(String ro_no) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cnd = "N";
			String cndValue = "";
			sql1 = "select a.ref_no,a.head,a.section,a.ro_agency,a.ro_no,a.ro_type,t4.label as ro_type_n,a.type_of_issue,t6.label as type_of_issue_n,";
			sql1 = sql1 + " a.ro_for,t5.label as ro_for_n,a.to_sus_no,or1.unit_name as to_unit_name,";
			sql1 = sql1
					+ " hq.sus_no as hq_sus,hq.unit_name as hq_name, hq1.sus_no as to_comd_sus, hq1.unit_name as to_comd_name,";
			sql1 = sql1
					+ " a.prf_code,prf.prf_group,a.census_no,m.nomen,a.ro_qty,a.rel_depot_sus,or2.unit_name as depot_name,";
			sql1 = sql1
					+ " a.op_status,t3.label as op_status_n,a.id,a.ro_date,a.ro_valid_upto,a.data_upd_date,a.spl_remarks,";
			sql1 = sql1 + " a.type_of_stk,t7.label as type_of_stk_n,a.head,t8.label as head_n,m.au,t8.label as au_n";
			sql1 = sql1
					+ " from mms_tb_ro_mstr a left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active')";
			sql1 = sql1 + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
			sql1 = sql1
					+ " left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active')";
			sql1 = sql1
					+ " left join mms_domain_values t1 on (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')";
			sql1 = sql1
					+ " left join mms_domain_values t2 on (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')";
			sql1 = sql1 + " left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS')";
			sql1 = sql1 + " left join mms_domain_values t4 on (a.ro_type=t4.codevalue and t4.domainid='MMSRO')";
			sql1 = sql1 + " left join mms_domain_values t5 on (a.ro_for=t5.codevalue and t5.domainid='MMSTYPEOFRO')";
			sql1 = sql1
					+ " left join mms_domain_values t6 on (a.type_of_issue=t6.codevalue and t6.domainid='MMSTYPEOFISSUE')";
			sql1 = sql1
					+ " left join mms_domain_values t7 on (a.type_of_stk=t7.codevalue and t7.domainid='MMSROSTOCK')";
			sql1 = sql1 + " left join mms_domain_values t8 on (a.head=t8.codevalue and t8.domainid='PBDACCOUNTS')";
			sql1 = sql1 + " left join mms_domain_values t9 on (m.au=t9.codevalue and t9.domainid='ACCOUNTINGUNITS')";
			sql1 = sql1 + " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code";
			sql1 = sql1 + " left join nrv_hq hq on or1.form_code_control=hq.form_code_control";
			sql1 = sql1
					+ " left join nrv_hq hq1 on concat(substring(or1.form_code_control,1,1),'000000000')=hq1.form_code_control where a.ro_no::character varying=?";

			PreparedStatement stmt = conn.prepareStatement(sql1);

			stmt.setString(1, ro_no);

			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_date"));
					list.add(rs.getString("ro_valid_upto"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("ro_type"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("prf_code"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("hq_name"));
					list.add(rs.getString("to_comd_name"));
					list.add(rs.getString("type_of_issue_n"));
					list.add(rs.getString("depot_name"));
					list.add(rs.getString("op_status_n"));
					list.add(rs.getString("id"));
					list.add(rs.getString("ref_no"));
					list.add(rs.getString("ro_agency"));
					list.add(rs.getString("spl_remarks"));
					list.add(rs.getString("type_of_stk_n"));
					list.add(rs.getString("head_n"));
					list.add(rs.getString("au_n"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public List<MMS_RO_Generation> printMMSRo(String ro_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from MMS_RO_Generation where ro_no=:ro_no");
		q.setParameter("ro_no", ro_no);
		@SuppressWarnings("unchecked")
		List<MMS_RO_Generation> list = (List<MMS_RO_Generation>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<MMS_RIO_Generation> printMMSRio(String rio_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from MMS_RIO_Generation where rio_no=:rio_no");
		q.setParameter("rio_no", rio_no);
		@SuppressWarnings("unchecked")
		List<MMS_RIO_Generation> list = (List<MMS_RIO_Generation>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> GetRioGenerationFromRO(int id, String t_name) {
		String comd;
		if (t_name.equalsIgnoreCase("mms_tb_ro_mstr")) {
			comd = "to_comd_sus";
		} else {
			comd = "from_comd_sus";
		}

		List<String> nrList = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			nrSql = "select a.id,a.ro_no,a.ro_agency,a.ro_type,t1.label as ro_type_n,ro_for,t2.label as ro_for_n,"
					+ "type_of_issue,t3.label as type_of_issue_n,to_sus_no,t4.unit_name as to_unit_name,"
					+ "a.prf_code,t5.prf_group as prf_group,a.rel_depot_sus,t6.unit_name as depot_unit_name,"
					+ "a.census_no,t7.nomen as nomen,a.ro_ue,a.ro_uh,a.remarks,a.ro_qty,a.ro_date,a.type_of_hldg,t8.label as type_of_hldg_n,a."
					+ comd + " as comd_sus," + "a.type_of_stk,t9.label as type_of_stk_n" + " from " + t_name + " a "
					+ "left join mms_domain_values t1 on a.ro_type=t1.codevalue and t1.domainid='MMSRO' "
					+ "left join mms_domain_values t2 on a.ro_for=t2.codevalue and t2.domainid='MMSTYPEOFRO' "
					+ "left join mms_domain_values t3 on a.type_of_issue=t3.codevalue and t3.domainid='MMSTYPEOFISSUE' "
					+ "left join tb_miso_orbat_unt_dtl t4 on a.to_sus_no=t4.sus_no and t4.status_sus_no='Active' "
					+ "left join cue_tb_miso_prf_group_mst t5 on a.prf_code=t5.prf_group_code and t5.status_active='Active' "
					+ "left join tb_miso_orbat_unt_dtl t6 on a.rel_depot_sus=t6.sus_no and t6.status_sus_no='Active' "
					+ "left join mms_tb_mlccs_mstr_detl t7 on a.census_no=t7.census_no "
					+ "left join mms_domain_values t8 on a.type_of_hldg=t8.codevalue and t8.domainid='TYPEOFHOLDING' "
					+ "left join mms_domain_values t9 on a.type_of_stk=t9.codevalue and t9.domainid='MMSROSTOCK' "
					+ "where a.id=?";
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				nrList.add(rs.getString("ro_no"));
				nrList.add(rs.getString("ro_agency"));
				nrList.add(rs.getString("ro_type"));
				nrList.add(rs.getString("ro_type_n"));
				nrList.add(rs.getString("ro_for"));
				nrList.add(rs.getString("ro_for_n"));
				nrList.add(rs.getString("type_of_issue"));
				nrList.add(rs.getString("type_of_issue_n"));
				nrList.add(rs.getString("to_sus_no"));
				nrList.add(rs.getString("to_unit_name"));
				nrList.add(rs.getString("prf_code"));
				nrList.add(rs.getString("prf_group"));
				nrList.add(rs.getString("rel_depot_sus"));
				nrList.add(rs.getString("depot_unit_name"));
				nrList.add(rs.getString("census_no"));
				nrList.add(rs.getString("nomen"));
				nrList.add(rs.getString("ro_ue"));
				nrList.add(rs.getString("ro_uh"));
				nrList.add(rs.getString("remarks"));
				nrList.add(rs.getString("ro_qty"));
				nrList.add(rs.getString("ro_date"));
				nrList.add(rs.getString("type_of_hldg"));
				nrList.add(rs.getString("type_of_hldg_n"));
				nrList.add(rs.getString("comd_sus"));
				nrList.add(rs.getString("id"));
				nrList.add(rs.getString("type_of_stk_n"));
				nrList.add(rs.getString("type_of_stk"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrList;
	}

	public ArrayList<ArrayList<String>> RioRequestScreen() {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";

			/*
			 * sql1 =
			 * "select d.ro_agency,d.ro_no,d.ro_type,t5.label as ro_type_n,d.type_of_issue,"
			 * + "t6.label as type_of_issue_n,d.ro_for,t4.label as ro_for_n,d.to_sus_no," +
			 * "or1.unit_name as to_unit_name,d.prf_code,prf.prf_group,d.ro_qty,d.rel_depot_sus,or2.unit_name as depot_name,"
			 * + "d.op_status,t3.label as op_status_n,d.id,d.ro_date from" +
			 * " (select ro_agency,ro_no,ro_type,type_of_issue,ro_for,to_sus_no,prf_code,ro_qty,"
			 * +
			 * "rel_depot_sus,op_status,id,ro_date from mms_tb_ro_mstr where op_status='1' and ro_type in ('1','3','4')"
			 * + " union all " +
			 * "select ro_agency,ro_no,ro_type,type_of_issue,ro_for,to_sus_no,prf_code,ro_qty,"
			 * +
			 * "rel_depot_sus,op_status,id,ro_date from mms_tb_comd_ro_mstr where op_status='1') d"
			 * +
			 * " left join tb_miso_orbat_unt_dtl or1 on (d.to_sus_no=or1.sus_no and or1.status_sus_no='Active')"
			 * + " left join cue_tb_miso_prf_group_mst prf on d.prf_code=prf.prf_group_code"
			 * +
			 * " left join tb_miso_orbat_unt_dtl or2 on (d.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active')"
			 * +
			 * " left join mms_domain_values t3 on (d.op_status=t3.codevalue and t3.domainid='OPSTATUS')"
			 * +
			 * " left join mms_domain_values t4 on (d.ro_for=t4.codevalue and t4.domainid='MMSTYPEOFRO')"
			 * +
			 * " left join mms_domain_values t5 on (d.ro_type=t5.codevalue and t5.domainid='MMSRO')"
			 * +
			 * " left join mms_domain_values t6 on (d.type_of_issue=t6.codevalue and t6.domainid='MMSTYPEOFISSUE')"
			 * +
			 * " where d.id NOT IN (select distinct z.ro_id from mms_tb_rio_mstr z where z.ro_id is not null)"
			 * + " order by ro_date desc";
			 */

			sql1 = "select d.ro_no, \r\n" + "	t5.label as ro_type_n, \r\n" + "	t4.label as ro_for_n,\r\n"
					+ "	or1.unit_name as to_unit_name,\r\n" + "	prf.prf_group,\r\n" + "	d.ro_qty,\r\n" + "	d.id,\r\n"
					+ "	d.table\r\n" + "	from\r\n" + "		(select \r\n" + "			ro_no,\r\n"
					+ "			ro_type,\r\n" + "			type_of_issue,\r\n" + "			ro_for,\r\n"
					+ "			to_sus_no,\r\n" + "			prf_code,\r\n" + "			ro_qty,\r\n"
					+ "			op_status,\r\n" + "			id,\r\n" + "			'ro_mst' as table\r\n"
					+ "		from mms_tb_ro_mstr where op_status='1' and ro_type in ('1','3','4')\r\n"
					+ "		union all \r\n" + "		select \r\n" + "			ro_no,\r\n" + "			ro_type,\r\n"
					+ "			type_of_issue, \r\n" + "			ro_for,\r\n" + "			to_sus_no, \r\n"
					+ "			prf_code, \r\n" + "			ro_qty, \r\n" + "			op_status, \r\n"
					+ "			id, \r\n" + "			'c_ro_mst' as table\r\n"
					+ "		from mms_tb_comd_ro_mstr where op_status='1') d\r\n"
					+ "		left join tb_miso_orbat_unt_dtl or1 on (d.to_sus_no=or1.sus_no and or1.status_sus_no='Active')\r\n"
					+ "		left join cue_tb_miso_prf_group_mst prf on d.prf_code=prf.prf_group_code\r\n"
					+ "		left join mms_domain_values t4 on (d.ro_for=t4.codevalue and t4.domainid='MMSTYPEOFRO')\r\n"
					+ "		left join mms_domain_values t5 on (d.ro_type=t5.codevalue and t5.domainid='MMSRO')\r\n"
					+ "		where d.id NOT IN (select distinct z.ro_id from mms_tb_rio_mstr z where z.ro_id is not null)\r\n"
					+ "		order by d.ro_no desc";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("id"));
					list.add(rs.getString("table"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public String getmaxrioCode() {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String query = null;
			query = "select max(right(rio_no,5)) from mms_tb_rio_mstr where TO_CHAR(data_cr_date :: DATE , 'yyyy') like concat('%',TO_CHAR(NOW() :: DATE , 'yyyy'),'%')";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("max");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public ArrayList<ArrayList<String>> RioApproverScreen(String formcodeType, String formcode, String p_code,
			String d_from, String d_to, String rio_status) {

		String q = "";
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cnd = "N";
			String cndValue = "";

			sql1 = "select a.rio_no,a.ro_agency,a.ro_no,a.ro_type,t4.label as ro_type_n,a.to_sus_no,or1.unit_name as to_unit_name,a.to_comd_sus,a.prf_code,prf.prf_group,"
					+ " a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.ro_qty,a.rel_depot_sus,or2.unit_name as depot_name,"
					+ " a.op_status,t3.label as op_status_n,a.id,a.ro_valid_upto,a.ro_for,t5.label as ro_for_n"
					+ " from mms_tb_rio_mstr a"
					+ " left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active')"
					+ " left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active')"
					+ " left join mms_domain_values t1 on (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')"
					+ " left join mms_domain_values t2 on (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')"
					+ " left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS')"
					+ " left join mms_domain_values t5 on (a.ro_for=t5.codevalue and t5.domainid='MMSTYPEOFRO')"
					+ " left join mms_domain_values t4 on (a.ro_type=t4.codevalue and t4.domainid='MMSRO')"
					+ " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code";

			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RIO")) {
					cndValue = " a.rio_no=?";
					sql1 = sql1 + " where " + cndValue;
					cnd = "Y";
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						cndValue = "substr(form_code_control,1,1)=?)";
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						cndValue = "substr(form_code_control,1,3)=?)";
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						cndValue = "substr(form_code_control,1,6)=?)";
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						cndValue = "substr(form_code_control,1,10)=?)";
					}
					sql1 = sql1
							+ " where or1.sus_no in (select sus_no from tb_miso_orbat_unt_dtl where status_sus_no='Active' and "
							+ cndValue;
					cnd = "Y";
				}
			}

			if (!formcodeType.equalsIgnoreCase("RIO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " prf.prf_group_code=?";
					cnd = "Y";
				}

				if (!rio_status.equalsIgnoreCase("ALL")) {
					if (cnd.equalsIgnoreCase("N")) {
						sql1 = sql1 + " where ";
					} else {
						sql1 = sql1 + " and ";
					}
					sql1 = sql1 + " a.op_status=?";
					cnd = "Y";
				}

				if (cnd.equalsIgnoreCase("N")) {
					sql1 = sql1 + " where ";
				} else {
					sql1 = sql1 + " and ";
				}

				if (d_from.equalsIgnoreCase(d_to)) {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				} else {
					sql1 = sql1 + " data_cr_date BETWEEN DATE(?) and DATE(?) + 1";
				}

				sql1 = sql1 + " order by data_cr_date desc";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);

			int g1 = 1;
			if (formcode.length() > 0) {
				if (formcodeType.equalsIgnoreCase("RIO")) {
					stmt.setString(g1, formcode);
					g1++;
				} else {
					if (formcodeType.equalsIgnoreCase("COMMAND")) {
						stmt.setString(g1, formcode.substring(0, 1));
					} else if (formcodeType.equalsIgnoreCase("CORPS")) {
						stmt.setString(g1, formcode.substring(0, 3));
					} else if (formcodeType.equalsIgnoreCase("DIV")) {
						stmt.setString(g1, formcode.substring(0, 6));
					} else if (formcodeType.equalsIgnoreCase("BDE")) {
						stmt.setString(g1, formcode.substring(0, 10));
					}
					g1++;
				}
			}

			if (!formcodeType.equalsIgnoreCase("RIO")) {
				if (!p_code.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, p_code);
					g1++;
				}

				if (!rio_status.equalsIgnoreCase("ALL")) {
					stmt.setString(g1, rio_status);
					g1++;
				}

				stmt.setString(g1, d_from);
				g1++;
				stmt.setString(g1, d_to);
			}

			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("rio_no"));
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("ro_for_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("op_status_n"));
					list.add(rs.getString("id"));
					li.add(list);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<ArrayList<String>> RioPrintScreen(String rio_no) {

		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String cnd = "N";
			String cndValue = "";

			sql1 = "select a.rio_no,a.ro_agency,a.census_no,m.nomen,a.ro_no,a.ro_date,a.ro_type,t4.label as ro_type_n,a.to_sus_no,or1.unit_name as to_unit_name,a.to_comd_sus,or3.unit_name as comd_name,a.prf_code,prf.prf_group,"
					+ " a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,a.ro_qty,a.rel_depot_sus,or2.unit_name as depot_name,"
					+ " a.op_status,t3.label as op_status_n,a.id,a.rio_valid_upto,a.type_of_issue,t5.label as type_of_issue_n,a.data_cr_date,m.au,t6.label as au_n,a.rio_date,a.type_of_stk,t7.label as type_of_stk_n,m.cat_part_no,a.remarks "
					+ " from mms_tb_rio_mstr a"
					+ " left join tb_miso_orbat_unt_dtl or1 on (a.to_sus_no=or1.sus_no and or1.status_sus_no='Active')"
					+ " left join tb_miso_orbat_unt_dtl or2 on (a.rel_depot_sus=or2.sus_no and or2.status_sus_no='Active')"
					+ " left join tb_miso_orbat_unt_dtl or3 on (a.to_comd_sus=or3.sus_no and or3.status_sus_no='Active')"
					+ " left join mms_domain_values t1 on (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')"
					+ " left join mms_domain_values t2 on (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')"
					+ " left join mms_domain_values t3 on (a.op_status=t3.codevalue and t3.domainid='OPSTATUS')"
					+ " left join mms_domain_values t4 on (a.ro_type=t4.codevalue and t4.domainid='MMSTYPEOFRO')"
					+ " left join mms_domain_values t5 on (a.type_of_issue=t5.codevalue and t5.domainid='MMSTYPEOFISSUE')"
					+ " left join cue_tb_miso_prf_group_mst prf on a.prf_code=prf.prf_group_code"
					+ " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no"
					+ " left join mms_domain_values t6 on (m.au=t6.codevalue and t6.domainid='ACCOUNTINGUNITS') "
					+ "left join mms_domain_values t7 on (a.type_of_stk=t7.codevalue and t7.domainid='MMSROSTOCK') "
					+ "where a.rio_no=?";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, rio_no);

			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("rio_no"));
					list.add(rs.getString("ro_no"));
					list.add(rs.getString("ro_type_n"));
					list.add(rs.getString("to_unit_name"));
					list.add(rs.getString("type_of_issue_n"));
					list.add(rs.getString("prf_group"));
					list.add(rs.getString("ro_qty"));
					list.add(rs.getString("op_status_n"));
					list.add(rs.getString("id"));
					list.add(rs.getString("ro_date"));
					list.add(rs.getString("ro_agency"));
					list.add(rs.getString("comd_name"));
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("depot_name"));
					list.add(rs.getString("rio_valid_upto"));
					list.add(rs.getString("data_cr_date"));
					list.add(rs.getString("au_n"));
					list.add(rs.getString("rio_date"));
					list.add(rs.getString("type_of_stk_n"));
					list.add(rs.getString("cat_part_no"));
					list.add(rs.getString("remarks"));
					li.add(list);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public List<String> ifExistRORIONo(String ro_no, String to_sus_no) {
		String q = "from MMS_RIO_Generation where ro_no=:ro_no and to_sus_no=:to_sus_no";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(q);
		query.setParameter("ro_no", ro_no).setParameter("to_sus_no", to_sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) query.list();
		tx.commit();
		session.close();
		return list;
	}

//	public ArrayList<ArrayList<String>> getUnit_ue_uh(String unit_id, String prfcode) {
//	    ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//	    Connection conn = null;
//	    String q = "";
//
//	    try {
//	        conn = dataSource.getConnection();
//	        PreparedStatement stmt = null;
//
//	        // Split unit_id and prfcode into an array of strings
//	        String[] unitIds = unit_id.split(",");
//	        String[] prfcodes = prfcode.split(",");
//
//	        // Generating placeholders for unitIds and prfcodes
//	        String unitIdPlaceholders = generatePlaceholders(unitIds.length);
//	        String prfcodePlaceholders = generatePlaceholders(prfcodes.length);
//
//	        q = "SELECT p.sus_no, orb.unit_name, p.prf_code, sum(p.tothol) AS tothol, sum(p.totue) AS totue " +
//	                "FROM (" +
//	                "       SELECT a.sus_no, m.prf_code, a.tothol, 0 AS totue " +
//	                "       FROM mms_tv_regn_mstr a " +
//	                "       LEFT JOIN mms_tb_mlccs_mstr_detl m ON a.census_no = m.census_no " +
//	                "       WHERE sus_no IN (" + unitIdPlaceholders + ") " +
//	                "           AND m.prf_code IN(" + prfcodePlaceholders + ") " +
//	                "           AND a.type_of_hldg = 'A0' " +
//	                "           AND a.type_of_eqpt = '1' " +
//	                "       UNION ALL " +
//	                "       SELECT sus_no, prf_group_code AS prf_code, 0 AS tothol, total_ue_qty AS totue " +
//	                "       FROM mms_ue_mview " +
//	                "       WHERE sus_no IN (" + unitIdPlaceholders + ") " +
//	                "           AND prf_group_code IN(" + prfcodePlaceholders + ") " +
//	                "     ) p " +
//	                "INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = p.sus_no AND orb.status_sus_no = 'Active' " +
//	                "GROUP BY p.sus_no, orb.unit_name, p.prf_code " +
//	                "ORDER BY p.sus_no DESC";
//
//	        stmt = conn.prepareStatement(q);
//
//	        // Set values for unitIds and prfcodes
//	        int parameterIndex = 1;
//	        for (String id : unitIds) {
//	            stmt.setString(parameterIndex++, id);
//	        }
//	        for (String code : prfcodes) {
//	            stmt.setString(parameterIndex++, code);
//	        }
//
//	        ResultSet rs = stmt.executeQuery();
//	        while (rs.next()) {
//	            ArrayList<String> list = new ArrayList<String>();
//	            list.add(rs.getString("sus_no"));
//	            list.add(rs.getString("unit_name"));
//	            list.add(rs.getString("prf_code"));
//	            list.add(rs.getString("tothol"));
//	            list.add(rs.getString("totue"));
//	            alist.add(list);
//	        }
//	        rs.close();
//	        stmt.close();
//	        conn.close();
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        if (conn != null) {
//	            try {
//	                conn.close();
//	            } catch (SQLException e) {
//	                // Handle the exception
//	            }
//	        }
//	    }
//	    return alist;
//	}

	
	public List<Map<String, Object>> getUnit_ue_uh(String unit_id, String prfcode) throws SQLException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String[] unitIds = unit_id.split(",");
			String[] prfcodes = prfcode.split(",");
			String unitIdPlaceholders = generatePlaceholders(unitIds.length);
			String prfcodePlaceholders = generatePlaceholders(prfcodes.length);
			
			StringBuilder queryBuilder = new StringBuilder();
			for(String pcode : prfcodes) {
		    for (String id : unitIds) {
		      
				String unitQuery = "SELECT pr.prf_group,"
				    + "       pr.prf_group_code,"
				    + "       i.item_type AS item_nomen,"
				    + "       i.item_code,"
				    + "      	0 as totue,\r\n"
				    + "	           0 as totuh,\r\n"
				    + "	           0 as defi,\r\n"
				    + "               0as sur,\r\n"
				    + "	            0 as type_of_eqpt,"
				    + "       (SELECT DISTINCT unit_name"
				    + "        FROM tb_miso_orbat_unt_dtl"
				    + "        WHERE sus_no = '" + id + "') AS unit_name,"
				    + "       '" + id + "' AS sus_no"
				    + " FROM cue_tb_miso_mms_item_mstr i"
				    + " LEFT JOIN cue_tb_miso_prf_group_mst pr ON pr.prf_group_code = i.prf_group_code"
				    + "     where i.prf_group_code IN('" + pcode + "')\r\n"
				    + " GROUP BY pr.prf_group,"
				    + "          pr.prf_group_code,"
				    + "          i.item_type,"
				    + "          i.item_code";

				queryBuilder.append(unitQuery).append(" UNION ALL ");
				
		    }
	}
			
			
			if (queryBuilder.length() > 0) {
		        queryBuilder.setLength(queryBuilder.length() - " UNION ALL ".length());
		    }

		    q = "WITH all_item_pfrwise AS (" + queryBuilder.toString() + ")"
		        + "select m.prf_group,m.prf_group_code,m.item_nomen,m.item_code,sum(m.totue) as totue,sum(m.totuh) as totuh,sum(m.defi) as defi,sum(m.sur) as sur,sum(m.type_of_eqpt) as type_of_eqpt,m.unit_name,m.sus_no from (	\r\n"
		        + "\r\n"
		        + "select prf_group,prf_group_code,item_nomen,item_code,totue,totuh,defi,sur,type_of_eqpt,unit_name,sus_no from all_item_pfrwise\r\n"
		        + "union all \r\n"
		        + "\r\n"
		        + "\r\n"
		        + "SELECT pr.PRF_GROUP,\r\n"
		        + "       pr.prf_group_code,\r\n"
		        + "       i.item_type AS item_nomen, i.item_code,\r\n"
		        + "       sum(r.totue) AS totue,\r\n"
		        + "       sum(r.totuh) as totuh, case when (r.totue - r.totuh) >0 then (r.totue - r.totuh) else '0' end as defi,\r\n"
		        + "       case when (r.totuh -r.totue) >0 then (r.totuh -r.totue) else '0' end as sur, CAST(r.type_of_eqpt AS INTEGER)\r\n"
		        + "	 ,r.unit_name,r.sus_no\r\n"
		        + "     \r\n"
		        + "  FROM (\r\n"
		        + "        SELECT p.prf_code,\r\n"
		        + "               p.item_code,\r\n"
		        + "               'A0' AS type_of_hldg,\r\n"
		        + "               p.type_of_eqpt,\r\n"
		        + "               sum(ueqty) AS totue,\r\n"
		        + "               sum(uhqty) AS totuh,\r\n"
		        + "               max(upd_date) AS upd_date,\r\n"
		        + "               p.unit_name, p.sus_no\r\n"
		        + "          FROM (\r\n"
		        + "                SELECT b.prf_code,\r\n"
		        + "                       b.item_code,\r\n"
		        + "                       a.census_no,\r\n"
		        + "                       a.type_of_eqpt,\r\n"
		        + "                       0 AS ueqty,\r\n"
		        + "                       a.tothol AS uhqty,\r\n"
		        + "                       a.data_app_date AS upd_date,\r\n"
		        + "            a.unit_name, a.sus_no\r\n"
		        + "                  FROM (\r\n"
		        + "                        SELECT a.census_no,\r\n"
		        + "                               a.type_of_eqpt,\r\n"
		        + "                               1 AS tothol,\r\n"
		        + "                               data_app_date,\r\n"
		        + "                               u.unit_name, u.sus_no\r\n"
		        + "                          FROM mms_tb_regn_mstr_detl a\r\n"
		        + "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
		        + "                            ON a.sus_no::text = u.sus_no::text\r\n"
		        + "                           AND u.status_sus_no::text = 'Active'::text\r\n"
		        + "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
		        + "                           AND a.op_status = '1'\r\n"
		        + "                           AND a.type_of_hldg = 'A0'\r\n"
		        + "                     UNION ALL SELECT a.census_no,\r\n"
		        + "                               a.type_of_eqpt,\r\n"
		        + "                               1 AS tothol,\r\n"
		        + "                               data_app_date,\r\n"
		        + "                             '' as unit_name,'' AS sus_no\r\n"
		        + "                          FROM mms_tb_depot_regn_mstr_detl a\r\n"
		        + "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
		        + "                            ON a.sus_no::text = u.sus_no::text\r\n"
		        + "                           AND u.status_sus_no::text = 'Active'::text\r\n"
		        + "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
		        + "                           AND a.op_status = '1'\r\n"
		        + "                           AND a.type_of_hldg = 'A0'\r\n"
		        + "                     UNION ALL SELECT a.census_no,\r\n"
		        + "                               a.type_of_eqpt,\r\n"
		        + "                               1 AS uh,\r\n"
		        + "                               data_app_date,\r\n"
		        + "                              u.unit_name,u.sus_no\r\n"
		        + "                          FROM mms_tb_regn_oth_mstr a\r\n"
		        + "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
		        + "                            ON a.to_sus_no::text = u.sus_no::text\r\n"
		        + "                           AND u.status_sus_no::text = 'Active'::text\r\n"
		        + "                         WHERE a.to_sus_no IN (" + unitIdPlaceholders + ") \r\n"
		        + "                           AND a.op_status = '1'\r\n"
		        + "                           AND a.type_of_hldg = 'A0'\r\n"
		        + "                       ) AS a\r\n"
		        + "                 INNER JOIN mms_tb_mlccs_mstr_detl b\r\n"
		        + "                    ON a.census_no = b.census_no\r\n"
		        + "             UNION ALL SELECT prf_group_code AS prf_code,\r\n"
		        + "                       item_code,\r\n"
		        + "                       '' AS census_no,\r\n"
		        + "                       (CASE WHEN upper(TYPE) = 'CES' THEN '2' ELSE '1' END) AS type_of_eqpt,\r\n"
		        + "                       total_ue_qty AS ueqty,\r\n"
		        + "                       0 AS uhqty,\r\n"
		        + "                       NULL AS upd_date,\r\n"
		        + "                   u.unit_name, u.sus_no\r\n"
		        + "                FROM mms_ue_mview  uev\r\n"
		        + "                inner JOIN tb_miso_orbat_unt_dtl u\r\n"
		        + "                            ON uev.sus_no::text = u.sus_no::text\r\n"
		        + "                           AND u.status_sus_no::text = 'Active'::text\r\n"
		    	+ "                 WHERE uev.sus_no IN (" + unitIdPlaceholders + ") \r\n"
		        + "               ) p\r\n"
		        + "         GROUP BY p.prf_code,\r\n"
		        + "                  p.item_code,\r\n"
		        + "                  p.type_of_eqpt,\r\n"
		        + "    p.unit_name,p.sus_no\r\n"
		        + "       ) r\r\n"
		        + "  LEFT JOIN cue_tb_miso_mms_item_mstr i\r\n"
		        + "    ON r.item_code = i.item_code\r\n"
		        + "  LEFT JOIN cue_tb_miso_prf_group_mst pr\r\n"
		        + "    ON r.prf_code = pr.prf_group_code \r\n"		       
		    	+ "     where pr.prf_group_code IN(" + prfcodePlaceholders + ")\r\n"
		        + " group by pr.prf_group,pr.prf_group_code,i.item_type,r.upd_date,r.unit_name,r.item_code,i.item_code, r.sus_no,r.totue,r.totuh,r.type_of_eqpt ) m\r\n"
		        + " group by m.prf_group,m.prf_group_code,m.item_nomen,m.item_code,m.unit_name,m.sus_no order by m.unit_name,m.sus_no";
			 

			
			
			
			
//			q = "SELECT distinct pr.prf_group,\r\n"
//					+ "       pr.prf_group_code,\r\n"
//					+ "       i.item_type AS item_nomen,"
//					+ " i.item_code,\r\n"				
//					+ "       sum(r.totue) AS totue,\r\n"
//					+ "       sum(r.totuh) as totuh,"
//					+ " case when (r.totue - r.totuh) >0 then (r.totue - r.totuh) else '0' end as defi,\r\n"
//					+ "       case when (r.totuh -r.totue) >0 then (r.totuh -r.totue) else '0' end as sur,"
//					+ " r.type_of_eqpt,\r\n"
//					+ "       to_char(r.upd_date, 'dd-mm-yyyy') AS upd_date,\r\n"
//					+ "       r.unit_name, r.sus_no\r\n"
//					+ "  FROM (\r\n"
//					+ "        SELECT p.prf_code,\r\n"
//					+ "               p.item_code,\r\n"
//					+ "               'A0' AS type_of_hldg,\r\n"
//					+ "               p.type_of_eqpt,\r\n"
//					+ "               sum(ueqty) AS totue,\r\n"
//					+ "               sum(uhqty) AS totuh,\r\n"
//					+ "               max(upd_date) AS upd_date,\r\n"
//					+ "               p.unit_name, p.sus_no\r\n"
//					+ "          FROM (\r\n"
//					+ "                SELECT b.prf_code,\r\n"
//					+ "                       b.item_code,\r\n"
//					+ "                       a.census_no,\r\n"
//					+ "                       a.type_of_eqpt,\r\n"
//					+ "                       0 AS ueqty,\r\n"
//					+ "                       a.tothol AS uhqty,\r\n"
//					+ "                       a.data_app_date AS upd_date,\r\n"
//					+ "            a.unit_name, a.sus_no\r\n"
//					+ "                  FROM (\r\n"
//					+ "                        SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS tothol,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                               u.unit_name, u.sus_no\r\n"
//					+ "                          FROM mms_tb_regn_mstr_detl a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                     UNION ALL SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS tothol,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                             '' as unit_name,'' AS sus_no\r\n"
//					+ "                          FROM mms_tb_depot_regn_mstr_detl a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                     UNION ALL SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS uh,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                              u.unit_name,u.sus_no\r\n"
//					+ "                          FROM mms_tb_regn_oth_mstr a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.to_sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.to_sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                       ) AS a\r\n"
//					+ "                 INNER JOIN mms_tb_mlccs_mstr_detl b\r\n"
//					+ "                    ON a.census_no = b.census_no\r\n"
//					+ "             UNION ALL SELECT prf_group_code AS prf_code,\r\n"
//					+ "                       item_code,\r\n"
//					+ "                       '' AS census_no,\r\n"
//					+ "                       (CASE WHEN upper(TYPE) = 'CES' THEN '2' ELSE '1' END) AS type_of_eqpt,\r\n"
//					+ "                       total_ue_qty AS ueqty,\r\n"
//					+ "                       0 AS uhqty,\r\n"
//					+ "                       NULL AS upd_date,\r\n"
//					+ "                   u.unit_name, u.sus_no\r\n"
//					+ "                FROM mms_ue_mview  uev\r\n"
//					+ "                inner JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON uev.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                 WHERE uev.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "               ) p\r\n"
//					+ "         GROUP BY p.prf_code,\r\n"
//					+ "                  p.item_code,\r\n"
//					+ "                  p.type_of_eqpt,\r\n"
//					+ "    p.unit_name,p.sus_no\r\n"
//					+ "       ) r\r\n"
//					+ "  LEFT JOIN cue_tb_miso_mms_item_mstr i\r\n"
//					+ "    ON r.item_code = i.item_code\r\n"
//					+ "  LEFT JOIN cue_tb_miso_prf_group_mst pr\r\n"
//					+ "    ON r.prf_code = pr.prf_group_code\r\n"
//					+ "  LEFT JOIN mms_domain_values t1\r\n"
//					+ "    ON r.type_of_hldg = t1.codevalue\r\n"
//					+ "   AND t1.domainid = 'TYPEOFHOLDING'\r\n"
//					+ "  LEFT JOIN mms_domain_values t2\r\n"
//					+ "    ON r.type_of_eqpt = t2.codevalue\r\n"
//					+ "   AND t2.domainid = 'TYPEOFEQPT'\r\n"
//					+ "     where pr.prf_group_code IN(" + prfcodePlaceholders + ")\r\n"
//							+ " group by pr.prf_group,pr.prf_group_code,i.item_type,r.upd_date,r.unit_name,r.item_code,i.item_code, r.sus_no,r.totue,r.totuh,r.type_of_eqpt"
//					+ " ORDER BY  r.sus_no,pr.prf_group,\r\n"
//					+ "          r.item_code";

			
//			
//			q = "     select a.PRF_GROUP,a.PRF_GROUP_CODE,a.ITEM_NOMEN,a.ITEM_CODE,sum(a.totue) as  totue,sum(a.totuh) AS totuh,sum(a.defi)  as defi,sum(a.sur) as sur,\r\n"
//					+ "sum(a.type_of_eqpt) as type_of_eqpt ,a.unit_name,a.sus_no\r\n"
//					+ "from\r\n"
//					+ "(\r\n"
//					+ "	SELECT PR.PRF_GROUP,\r\n"
//					+ "	PR.PRF_GROUP_CODE,\r\n"
//					+ "	I.ITEM_TYPE AS ITEM_NOMEN,\r\n"
//					+ "	I.ITEM_CODE,\r\n"
//					+ "	0 AS totue,\r\n"
//					+ "     0 as totuh, 0 as defi,\r\n"
//					+ "     0 as sur, \r\n"
//					+ "	  0 as type_of_eqpt  ,\r\n"
//					+ "	(select DISTINCT unit_name from tb_miso_orbat_unt_dtl where sus_no IN (" + unitIdPlaceholders + ") ) as  unit_name, \r\n"
//					+ "	(select DISTINCT sus_no from tb_miso_orbat_unt_dtl where sus_no IN (" + unitIdPlaceholders + ") ) as  sus_no\r\n"
//					+ "FROM CUE_TB_MISO_PRF_GROUP_MST PR\r\n"
//					+ "INNER JOIN CUE_TB_MISO_MMS_ITEM_MSTR I ON PR.PRF_GROUP_CODE = I.PRF_GROUP_CODE\r\n"
//					+ "WHERE I.PRF_GROUP_CODE IN(" + prfcodePlaceholders + ")\r\n"
//					+ "\r\n"
//					+ "union all  SELECT pr.prf_group,\r\n"
//					+ "       pr.prf_group_code,\r\n"
//					+ "       i.item_type AS item_nomen,"
//					+ " i.item_code,\r\n"				
//					+ "       sum(r.totue) AS totue,\r\n"
//					+ "       sum(r.totuh) as totuh,"
//					+ " case when (r.totue - r.totuh) >0 then (r.totue - r.totuh) else '0' end as defi,\r\n"
//					+ "       case when (r.totuh -r.totue) >0 then (r.totuh -r.totue) else '0' end as sur,"
//					+ " CAST(r.type_of_eqpt AS INTEGER),\r\n"					
//					+ "       r.unit_name, r.sus_no\r\n"
//					+ "  FROM (\r\n"
//					+ "        SELECT p.prf_code,\r\n"
//					+ "               p.item_code,\r\n"
//					+ "               'A0' AS type_of_hldg,\r\n"
//					+ "               p.type_of_eqpt,\r\n"
//					+ "               sum(ueqty) AS totue,\r\n"
//					+ "               sum(uhqty) AS totuh,\r\n"
//					+ "               max(upd_date) AS upd_date,\r\n"
//					+ "               p.unit_name, p.sus_no\r\n"
//					+ "          FROM (\r\n"
//					+ "                SELECT b.prf_code,\r\n"
//					+ "                       b.item_code,\r\n"
//					+ "                       a.census_no,\r\n"
//					+ "                       a.type_of_eqpt,\r\n"
//					+ "                       0 AS ueqty,\r\n"
//					+ "                       a.tothol AS uhqty,\r\n"
//					+ "                       a.data_app_date AS upd_date,\r\n"
//					+ "            a.unit_name, a.sus_no\r\n"
//					+ "                  FROM (\r\n"
//					+ "                        SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS tothol,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                               u.unit_name, u.sus_no\r\n"
//					+ "                          FROM mms_tb_regn_mstr_detl a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                     UNION ALL SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS tothol,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                             '' as unit_name,'' AS sus_no\r\n"
//					+ "                          FROM mms_tb_depot_regn_mstr_detl a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                     UNION ALL SELECT a.census_no,\r\n"
//					+ "                               a.type_of_eqpt,\r\n"
//					+ "                               1 AS uh,\r\n"
//					+ "                               data_app_date,\r\n"
//					+ "                              u.unit_name,u.sus_no\r\n"
//					+ "                          FROM mms_tb_regn_oth_mstr a\r\n"
//					+ "                          LEFT JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON a.to_sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                         WHERE a.to_sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "                           AND a.op_status = '1'\r\n"
//					+ "                           AND a.type_of_hldg = 'A0'\r\n"
//					+ "                       ) AS a\r\n"
//					+ "                 INNER JOIN mms_tb_mlccs_mstr_detl b\r\n"
//					+ "                    ON a.census_no = b.census_no\r\n"
//					+ "             UNION ALL SELECT prf_group_code AS prf_code,\r\n"
//					+ "                       item_code,\r\n"
//					+ "                       '' AS census_no,\r\n"
//					+ "                       (CASE WHEN upper(TYPE) = 'CES' THEN '2' ELSE '1' END) AS type_of_eqpt,\r\n"
//					+ "                       total_ue_qty AS ueqty,\r\n"
//					+ "                       0 AS uhqty,\r\n"
//					+ "                       NULL AS upd_date,\r\n"
//					+ "                   u.unit_name, u.sus_no\r\n"
//					+ "                FROM mms_ue_mview  uev\r\n"
//					+ "                inner JOIN tb_miso_orbat_unt_dtl u\r\n"
//					+ "                            ON uev.sus_no::text = u.sus_no::text\r\n"
//					+ "                           AND u.status_sus_no::text = 'Active'::text\r\n"
//					+ "                 WHERE uev.sus_no IN (" + unitIdPlaceholders + ") \r\n"
//					+ "               ) p\r\n"
//					+ "         GROUP BY p.prf_code,\r\n"
//					+ "                  p.item_code,\r\n"
//					+ "                  p.type_of_eqpt,\r\n"
//					+ "    p.unit_name,p.sus_no\r\n"
//					+ "       ) r\r\n"
//					+ "  LEFT JOIN cue_tb_miso_mms_item_mstr i\r\n"
//					+ "    ON r.item_code = i.item_code\r\n"
//					+ "  LEFT JOIN cue_tb_miso_prf_group_mst pr\r\n"
//					+ "    ON r.prf_code = pr.prf_group_code\r\n"
//					+ "  LEFT JOIN mms_domain_values t1\r\n"
//					+ "    ON r.type_of_hldg = t1.codevalue\r\n"
//					+ "   AND t1.domainid = 'TYPEOFHOLDING'\r\n"
//					+ "  LEFT JOIN mms_domain_values t2\r\n"
//					+ "    ON r.type_of_eqpt = t2.codevalue\r\n"
//					+ "   AND t2.domainid = 'TYPEOFEQPT'\r\n"
//					+ "     where pr.prf_group_code IN(" + prfcodePlaceholders + ")\r\n"
//							+ "  group by pr.prf_group,pr.prf_group_code,i.item_type,r.upd_date,r.unit_name,r.item_code,i.item_code, r.sus_no,r.totue,r.totuh,r.type_of_eqpt )a\r\n"
//							+ " group by a.PRF_GROUP,a.PRF_GROUP_CODE,a.ITEM_NOMEN,a.ITEM_CODE,a.unit_name,a.sus_no";
			
			stmt = conn.prepareStatement(q);
			
			  int parameterIndex = 1;
		        for (String id : unitIds) {
		            stmt.setString(parameterIndex++, id);
		        }
		        for (String id : unitIds) {
		            stmt.setString(parameterIndex++, id);
		        }
		        for (String id : unitIds) {
		            stmt.setString(parameterIndex++, id);
		        }
		        for (String id : unitIds) {
		            stmt.setString(parameterIndex++, id);
		        }
		        for (String code : prfcodes) {
		            stmt.setString(parameterIndex++, code);
		        }
//		        for (String id : unitIds) {
//		            stmt.setString(parameterIndex++, id);
//		        }
//		        for (String code : prfcodes) {
//		            stmt.setString(parameterIndex++, code);
//		        }

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
	
	// Method to generate placeholders
		private String generatePlaceholders(int length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append("?");
			}
			return sb.toString();
		}
		
		
		
		public List<Map<String, Object>> getprfgroup_wise_depo(String prfcode) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] prfcodes = prfcode.split(",");
			String prfcodePlaceholders = generatePlaceholders(prfcodes.length);

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;		
				
				
			

				q = "SELECT DISTINCT ds.depot_sus,obt.unit_name\r\n"
						+ "  FROM mms_tb_imp_drr_newformate ds\r\n"
						+ " inner join tb_miso_orbat_unt_dtl obt "
						+ " ON obt.sus_no = ds.depot_sus  "
						+ " WHERE census_no IN (\r\n"
						+ "        SELECT census_no\r\n"
						+ "          FROM mms_tb_mlccs_mstr_detl "
					+ " WHERE prf_code IN (" + prfcodePlaceholders + ")  and status_sus_no ='Active' \r\n"
						+ "       )      \r\n"
						+ "       order by depot_sus ";
				
//				q = "SELECT DISTINCT ds.depot_sus,obt.unit_name \r\n"					
//						+ "  FROM mms_tb_imp_drr_newformate ds\r\n"
//						+ " INNER JOIN tb_miso_orbat_unt_dtl obt\r\n"
//						+ "    ON obt.sus_no = ds.depot_sus\r\n"
//						+ " WHERE ds.census_no IN ('03010009L','02010190X','02010191A','02010135F','02010402X','03020004A','03010011H','02010194N','23033402P','22050412Y','22050213L','22052433N','25011702H','22050204K','23035915H')\r\n"
//						+ " ORDER BY depot_sus";

				stmt = conn.prepareStatement(q);	
				 int parameterIndex = 1;
				for (String code : prfcodes) {
		           stmt.setString(parameterIndex++, code);
		           
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
		
		public List<Map<String, Object>> getdepot_item(String prfcode,String depotsus) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] prfcodes = prfcode.split(",");		
			String prfcodePlaceholders = generatePlaceholders(prfcodes.length);			

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;		
				
				
			

//				q = "SELECT ds.depot_sus,ds.nomen,depot_stock\r\n"
//						+ "  FROM mms_tb_imp_drr_newformate ds\r\n"
//						+ " WHERE census_no IN (\r\n"
//						+ "        SELECT census_no\r\n"
//						+ "          FROM mms_tb_mlccs_mstr_detl "
//						//+ " WHERE prf_code IN (" + prfcodePlaceholders + ")  \r\n"
//						+ "       )      \r\n"
//						+ "       order by depot_sus ";
				
				q = "SELECT DISTINCT ds.recv_sus_no as depot_sus,obt.unit_name,\r\n"
						+ "       ds.nomen,\r\n"
						+ "       depot_stock,\r\n"
						+ "       ds.census_no\r\n"
						+ "  FROM mms_tb_imp_drr_newformate ds\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl obt\r\n"
						+ "    ON obt.sus_no = ds.recv_sus_no\r\n"
						+ " WHERE  ds.recv_sus_no = ? "
						+ "and ds.census_no IN (select census_no from mms_tb_mlccs_mstr_detl where prf_code IN ("+prfcodePlaceholders+") and status_sus_no ='Active')\r\n"
						
						+ "  and ds.condition='S' ORDER BY ds.recv_sus_no, ds.census_no desc";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, depotsus);
				 int parameterIndex = 2;
				for (String code : prfcodes) {
		            stmt.setString(parameterIndex++, code);
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
		
		
		
		
		public List<Map<String, Object>> getdept_wiseitem(String prfcode,String Search_value) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";		
			String[] prfcodes = prfcode.split(",");		
			String prfcodePlaceholders = generatePlaceholders(prfcodes.length);			
			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;		
				

				
				q = "SELECT DISTINCT ds.recv_sus_no as depot_sus,obt.unit_name,\r\n"
						+ "       ds.nomen,\r\n"
						+ "       depot_stock,\r\n"
						+ "       ds.census_no\r\n"
						+ "  FROM mms_tb_imp_drr_newformate ds\r\n"
						+ " INNER JOIN tb_miso_orbat_unt_dtl obt\r\n"
						+ "    ON obt.sus_no = ds.recv_sus_no\r\n"
						+ " WHERE obt.unit_name ilike '%"+Search_value+"%' and ds.census_no IN (select census_no from mms_tb_mlccs_mstr_detl where prf_code IN ("+prfcodePlaceholders+") and status_sus_no ='Active')\r\n"
								+ " and ds.condition='S' "						
						+ " ORDER BY ds.recv_sus_no, ds.census_no desc";

				stmt = conn.prepareStatement(q);			
				 int parameterIndex = 1;
				for (String code : prfcodes) {
		            stmt.setString(parameterIndex++, code);
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
		
		
		public List<Map<String, Object>> unitanditemcode_wise_census(String itemcode, String unit_sus,String type_of_eqpt) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				
//				q = "SELECT p.sus_no,\r\n"
//						+ "       p.prf_code,\r\n"
//						+ "       p.item_code,\r\n"
//						+ "       max(p.census_no) AS census_no,\r\n"
//						+ "       p.nomen,\r\n"
//						+ "       p.type_of_eqpt,\r\n"
//						+ "       sum(CAST (uhqty AS int)) AS totuh\r\n"
//						+ "  FROM (\r\n"
//						+ "        SELECT a.sus_no,\r\n"
//						+ "               b.prf_code,\r\n"
//						+ "               b.item_code,\r\n"
//						+ "               a.census_no,\r\n"
//						+ "               b.nomen,\r\n"
//						+ "               a.type_of_hldg,\r\n"
//						+ "               a.type_of_eqpt,\r\n"
//						+ "               0 AS ueqty,\r\n"
//						+ "               a.tothol AS uhqty\r\n"
//						+ "          FROM mms_tv_regn_mstr a,\r\n"
//						+ "               mms_tb_mlccs_mstr_detl b\r\n"
//						+ "         WHERE a.census_no = b.census_no\r\n"
//						+ "           AND a.sus_no = ?\r\n"
//						+ "       ) p\r\n"
//						+ " WHERE p.item_code = ? and  p.type_of_eqpt=?\r\n"
//						+ " GROUP BY p.sus_no,\r\n"
//						+ "          p.prf_code,\r\n"
//						+ "          p.item_code,\r\n"
//						+ "          p.type_of_eqpt,\r\n"
//						+ "          p.nomen      order by  census_no desc\r\n";
				
				q = "select a.sus_no,a.prf_code,a.item_code,a.census_no,a.nomen,sum(totuh) as totuh from (\r\n"
						+ "select \r\n"
						+ "    ? as sus_no,\r\n"
						+ "	prf_code,\r\n"
						+ "	item_code,\r\n"
						+ "	census_no,\r\n"
						+ "	nomen,\r\n"
						+ "   0 as totuh\r\n"
						+ "from mms_tb_mlccs_mstr_detl mlccs\r\n"
						+ "where item_code = ?\r\n"
//						+ "	and active_status = 'Active'\r\n"
						+ "	\r\n"
						+ "union all \r\n"
						+ "\r\n"
						+ "SELECT         a.sus_no,\r\n"
						+ "               b.prf_code,\r\n"
						+ "               b.item_code,\r\n"
						+ "               a.census_no,\r\n"
						+ "               b.nomen,           \r\n"
						+ "              sum(a.tothol) AS uhqty\r\n"
						+ "          FROM mms_tv_regn_mstr a,\r\n"
						+ "               mms_tb_mlccs_mstr_detl b\r\n"
						+ "         WHERE a.census_no = b.census_no\r\n"
						+ "           AND a.sus_no = ?\r\n"
						+ "		   and b.item_code=?\r\n"
						+ "		   group by  a.sus_no,\r\n"
						+ "               b.prf_code,\r\n"
						+ "               b.item_code,\r\n"
						+ "               a.census_no,\r\n"
						+ "               b.nomen)a\r\n"
						+ "			   group by a.sus_no,a.prf_code,a.item_code,a.census_no,a.nomen";
					
					

				stmt = conn.prepareStatement(q);				
				
				stmt.setString(1,unit_sus);
			    stmt.setString(2,itemcode);
			    stmt.setString(3,unit_sus);
			    stmt.setString(4,itemcode);
			    //stmt.setString(3,type_of_eqpt);
			      
			    System.err.println(stmt);
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
	
		
		
		public List<Map<String, Object>> get_corps(String fomationcode) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] fomationcodes = fomationcode.split(",");
			String formationPlaceholders = generatePlaceholders(fomationcodes.length);

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;					
			

				
				 q= "select SUBSTR(form_code_control,1,3) as form_code_control,unit_name FROM tb_miso_orbat_unt_dtl " + 
						" where sus_no in (select sus_no from tb_miso_orbat_codesform where level_in_hierarchy ='Corps') and " + 
						" substr(form_code_control::text, 1, 1) in("+formationPlaceholders+")  and status_sus_no = 'Active'";					
//		
				stmt = conn.prepareStatement(q);
				 int parameterIndex = 1;
				for (String code : fomationcodes) {
		           stmt.setString(parameterIndex++, code);
		           
		        }
				 System.err.println(stmt);
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
		
		public List<Map<String, Object>> get_division(String fomationcode) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] fomationcodes = fomationcode.split(",");
			String formationPlaceholders = generatePlaceholders(fomationcodes.length);

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;					
			

				
				 q= "select substr(form_code_control,1,6) as form_code_control,unit_name FROM tb_miso_orbat_unt_dtl " + 
						" where sus_no in (select sus_no from tb_miso_orbat_codesform where level_in_hierarchy ='Division') and " + 
						" substr(form_code_control::text, 1, 3) in("+formationPlaceholders+")  and status_sus_no = 'Active'";					
//		
				stmt = conn.prepareStatement(q);
				 int parameterIndex = 1;
				for (String code : fomationcodes) {
		           stmt.setString(parameterIndex++, code);
		           
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
		
		public List<Map<String, Object>> get_bde(String fomationcode) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] fomationcodes = fomationcode.split(",");
			String formationPlaceholders = generatePlaceholders(fomationcodes.length);

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;					
			

				
				 q= "select substr(form_code_control,1,10) as form_code_control,unit_name FROM tb_miso_orbat_unt_dtl " + 
						" where sus_no in (select sus_no from tb_miso_orbat_codesform where level_in_hierarchy ='Brigade') and " + 
						" substr(form_code_control::text, 1, 6) in("+formationPlaceholders+")  and status_sus_no = 'Active'";					
//		
				stmt = conn.prepareStatement(q);
				 int parameterIndex = 1;
				for (String code : fomationcodes) {
		           stmt.setString(parameterIndex++, code);
		           
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
		
		public List<Map<String, Object>> get_unit(String fomationcode) throws SQLException {

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String[] fomationcodes = fomationcode.split(",");
			String formationPlaceholders = generatePlaceholders(fomationcodes.length);

			try {

				conn = dataSource.getConnection();
				PreparedStatement stmt = null;					
			

				
				 q= "select sus_no,unit_name FROM tb_miso_orbat_unt_dtl " + 
						" where sus_no in (select sus_no from tb_miso_orbat_codesform where level_in_hierarchy ='Unit') and " + 
						" substr(form_code_control::text, 1, 10) in("+formationPlaceholders+")  and status_sus_no = 'Active'";					
//		
				stmt = conn.prepareStatement(q);
				 int parameterIndex = 1;
				for (String code : fomationcodes) {
		           stmt.setString(parameterIndex++, code);
		           
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
		
		
		public ArrayList<ArrayList<String>> getROReport(String sus_no,String fdate ,String tdate ,Integer status,String roleType,String depot_sus_no,HttpSession session){
			 ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >();  
			 String qry = "";
			 String today= "";
			 String query1 = "";
			 if(status.equals(0)) {
				 query1= "and r.ro_issue_qnty=r.yet_to_collect and r.status = 0";				 
			 }
			 if(status.equals(1)) {
				 query1= "and r.yet_to_collect =0";				 
			}
			 if(status.equals(2)) {
				 query1= "and r.ro_issue_qnty>r.yet_to_collect and r.yet_to_collect!=0";				 
			}
			 if(status.equals(3)) {
				 query1= " and r.status = 3";				 
			}
			 
			 if(!sus_no.equals("")) {
				 qry += " where r.unit_sus_no = ? ";
			 }
			 if(!fdate.equals("") & tdate.equals("")) {
				 if(qry.contains("where")) {
					 qry += " and CAST(r.created_date AS DATE) between cast(? as date) and  cast(? as date) ";
				 }else {
					 qry += " where CAST(r.created_date AS DATE) between cast(? as date) and  cast(? as date) ";
				 }
			 }
			 if(!tdate.equals("") & fdate.equals("")) {
				 if(qry.contains("where")) {
					 qry += " and cast(r.created_date as date) >= cast(? as date) ";
				 }else {
					 qry += " where cast(r.created_date as date) >= cast(? as date) ";
				
				 }
			 }
			 if(!fdate.equals("") & !tdate.equals("")) { 
				 if(qry.contains("where")) {
					qry += " and CAST(r.created_date AS DATE) between cast(? as date) and  cast(? as date) ";
				 }else {
					qry += " where CAST(r.created_date AS DATE) between cast(? as date) and  cast(? as date) ";
				 }
			 }		
			 
			 if(!depot_sus_no.equals("")){
				 if(qry.contains("where")) {
					 qry += " and r.depots_sus_no = ?";
				 }else {
					 qry += " where r.depots_sus_no = ?";
				 } 
			 }	
			String roleFormationNo = session.getAttribute("roleFormationNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
			

			 
			 Connection conn = null;
			 try {	
				conn = dataSource.getConnection();
				Statement stmt = (Statement)conn.createStatement();
		 			
		 		String query = "SELECT r.id,r.ro_no,d1.unit_name AS depots_name,\r\n"
		 				+ "       u1.unit_name,\r\n"
		 				+ "       mlcc.nomen,r.depots_sus_no,r.unit_sus_no,r.census_no,\r\n"
		 				+ "       r.ro_issue_qnty,\r\n"
		 				+ "       i.item_type AS item_nomen,\r\n"
		 				+ "       r.item_ue,\r\n"
		 				+ "       r.item_uh,r.issue_qnty,r.yet_to_collect,\r\n"
		 				+ "        ltrim(to_char(r.created_date,'dd-mm-yyyy'),'0') AS issue_date\r\n"
		 				+ "\r\n"
		 				+ "  FROM mms_tb_ro_generate_dtl r\r\n"
		 				+ " INNER JOIN tb_miso_orbat_unt_dtl d1\r\n"
		 				+ "    ON d1.sus_no = r.depots_sus_no\r\n"
		 				+ "   AND d1.status_sus_no = 'Active'\r\n"
		 				+ " INNER JOIN tb_miso_orbat_unt_dtl u1\r\n"
		 				+ "    ON u1.sus_no = r.unit_sus_no\r\n"
		 				+ "   AND u1.status_sus_no = 'Active'\r\n"
		 				+ " INNER JOIN mms_tb_mlccs_mstr_detl mlcc\r\n"
		 				+ "    ON mlcc.census_no = r.census_no\r\n"
		 				+ " INNER JOIN cue_tb_miso_mms_item_mstr i\r\n"
		 				+ "    ON r.item_code = i.item_code" +
		 				"  "+qry+""+query1+" order by r.id";
		 		
		 			PreparedStatement stmtt=conn.prepareStatement(query);
					int j =1;
					if(!sus_no.equals("")) {	
						stmtt.setString(j, sus_no);
						j += 1;
					}		
					if(!fdate.equals("") && tdate.equals("")) {
						stmtt.setString(j, fdate);
						j += 1;
						Date to_date1 = Calendar.getInstance().getTime();
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						today = dateFormat.format(to_date1);
						stmtt.setString(j, today);
						j += 1;
					}
					if(!tdate.equals("") && fdate.equals("")) {
						stmtt.setString(j, tdate);
						j += 1;
					}
					if(!fdate.equals("") && !tdate.equals("")) { 
						stmtt.setString(j, fdate);
						j += 1;
						stmtt.setString(j, tdate);
						j += 1;
					}					
					if(!depot_sus_no.equals("")) {
						stmtt.setString(j, depot_sus_no);
						j += 1;
					}
				 	ResultSet rs = stmtt.executeQuery();
				
					while(rs.next()){
						ArrayList<String> list1 = new ArrayList<String>(); 
			        				
			        	
			        	list1.add(rs.getString("ro_no"));
			        	list1.add(rs.getString("depots_name"));
			        	list1.add(rs.getString("unit_name"));
			        	list1.add(rs.getString("nomen"));
			        	list1.add(rs.getString("ro_issue_qnty"));
			        	list1.add(rs.getString("issue_qnty"));
			        	list1.add(rs.getString("yet_to_collect"));
			        	list1.add(rs.getString("item_nomen"));
			        	list1.add(rs.getString("item_ue"));
			        	list1.add(rs.getString("item_uh"));
			        	list1.add(rs.getString("issue_date"));
			        	
			        	
			        
			        	
			        	String f="";	        	        	
			        	
		
			        	if(status == 2 || status.equals(2) || status == 0 || status.equals(0) ) {
			        	String Reject = "onclick=\"if(confirm('Are You Sure you want to Reject?')){reject_ro("
			                    + rs.getString("id") + ",'" + rs.getString("ro_issue_qnty") + "','" + rs.getString("depots_sus_no")+"','"+rs.getString("unit_sus_no")+"','"+rs.getString("census_no")+"','"+rs.getString("yet_to_collect")+"');}else{return false;}\"";
                            String rejectButton = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
             f += rejectButton;
			        	}				
							
					

						list1.add(f);						
						aList.add(list1);
					}
			  	    rs.close();
			  	    stmt.close();
					conn.close();
				} catch (SQLException e) {
		   			e.printStackTrace();
		   	} 
			return aList;
		}
		public boolean delete_reg_no(int id ,int yet_to_collect) {
		    Connection conn = null;
		    String q = "";
		    String qry = "";
		    String regqry="";

		    try {
		    	if(yet_to_collect==1) {
		        conn = dataSource.getConnection();
		        PreparedStatement stmt = null;
		        q = "SELECT eqpt_regn_no FROM mms_tb_ro_generate_dtl WHERE id=?";
		        stmt = conn.prepareStatement(q);
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        String eqptRegnNo = null;
		        if (rs.next()) {
		            eqptRegnNo = rs.getString("eqpt_regn_no");
		        }
		        rs.close();
		        stmt.close();		        
		        
		        regqry = "DELETE FROM mms_tb_tempregn_rowise_dtl WHERE ro_no IN (select ro_no from mms_tb_ro_generate_dtl where id=?  )";
	            PreparedStatement stmt2 = conn.prepareStatement(regqry);	         
	            stmt2.setInt(1, id);		          
	            stmt2.executeUpdate();
	            stmt2.close();  
	            

		        if (eqptRegnNo != null) {
		            String[] eqpregno = eqptRegnNo.split(",");
		            String eqpregnoPlaceholders = generatePlaceholders(eqpregno.length);
		            qry = "DELETE FROM mms_tb_regn_mstr_detl WHERE eqpt_regn_no IN (" + eqpregnoPlaceholders + ")";
		            PreparedStatement stmt1 = conn.prepareStatement(qry);
		            int parameterIndex = 1;
		            for (String code : eqpregno) {
		                stmt1.setString(parameterIndex++, code);
		            }
		            int rowsAffected = stmt1.executeUpdate();
		            stmt1.close();
		            if (rowsAffected > 0) {
		                conn.close();
		                return true;
		            }
		        }    		 
		        
		        conn.close();
		    	}
		    	else {
		    		 conn = dataSource.getConnection();
				        PreparedStatement stmt = null;		        
				        qry = "DELETE FROM mms_tb_regn_mstr_detl WHERE eqpt_regn_no IN (select d.regn_no from mms_tb_tempregn_rowise_dtl d\r\n"
				        		+ "inner join mms_tb_ro_generate_dtl ge\r\n"
				        		+ "on d.ro_no=ge.ro_no\r\n"
				        		+ "and d.status=0\r\n"
				        		+ "and ge.id=?)";
			            PreparedStatement stmt1 = conn.prepareStatement(qry);
			            stmt1.setInt(1, id);			         
			       stmt1.executeUpdate();
			            stmt1.close();
			        
				        
				        regqry = "DELETE FROM mms_tb_tempregn_rowise_dtl WHERE ro_no IN (select ro_no from mms_tb_ro_generate_dtl where id=?  ) and status=:0";
			            PreparedStatement stmt2 = conn.prepareStatement(regqry);	         
			            stmt2.setInt(1, id);	
			            stmt2.setInt(2,0);	
			            int rowsAffected =  stmt2.executeUpdate();
			            if (rowsAffected > 0) {
			                conn.close();
			                return true;
			            }
			            stmt2.close();            

				         
				        }    		 
				        
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
		    return false;
		}

		



}
