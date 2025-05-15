package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MCT_NODAL_DIR_MASTER;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_MAIN;
import com.models.TB_TMS_RO_VEHICLE_DTLS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Service
@Repository
public class Capture_rel_orderDAOImpl implements Capture_rel_orderDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<TB_TMS_MCT_MASTER> getMctNoDetailList(String type_of_vehicle, String mct) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct mct from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle");
		q.setParameter("type_of_vehicle", type_of_vehicle);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_TMS_MCT_MASTER> getStdNomenclatureListFromVeh_cat(String type_of_vehicle) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct std_nomclature from TB_TMS_MCT_MASTER where type_of_vehicle=:type_of_vehicle");
		q.setParameter("type_of_vehicle", type_of_vehicle);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getStdNomenclature(BigInteger mct) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct std_nomclature from TB_TMS_MCT_MASTER where mct=:mct");
		q.setParameter("mct", mct);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_TMS_RO_MAIN> getRONoDetailList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct ro_no from TB_TMS_RO_MAIN");
		@SuppressWarnings("unchecked")
		List<TB_TMS_RO_MAIN> list = (List<TB_TMS_RO_MAIN>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Miso_Orbat_Unt_Dtl> getSUSNoDetailList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where status_sus_no = 'Active'");
		@SuppressWarnings("unchecked")
		List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getUnitNameFromSus(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	/////////
	public List<String> getComdSusNoFromSus(String sus_no) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select c.sus_no from orbat_view_cmd c  \r\n"
					+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no='Active' and u.sus_no=? \r\n"
					+ "where substring(u.form_code_operation,1,1) = c.cmd_code limit 1";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("sus_no"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}

		return list1;
	}

	//// ---------------------------------P & M
	// public List<String> getComdnameFromSus(String sus_no) {
	// List<String> list1 = new ArrayList<String>();
	// Connection conn = null;
	// try {
	// conn = dataSource.getConnection();
	// PreparedStatement stmt = null;
	// String sql1 = "select c.cmd_name from orbat_view_cmd c \r\n"
	// + "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no='Active' and
	//// u.sus_no=? \r\n"
	// + "where substring(u.form_code_operation,1,1) = c.cmd_code limit 1";
	// stmt = conn.prepareStatement(sql1);
	// stmt.setString(1, sus_no);
	// ResultSet rs1 = stmt.executeQuery();
	// while (rs1.next()) {
	// list1.add(rs1.getString("sus_no"));
	// }
	// rs1.close();
	// stmt.close();
	// } catch (SQLException e) {
	//
	// } finally {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// return list1;
	// }
	// }
	//
	// return list1;
	// }
	//
	//// ----------------------------------------

	public List<String> getCorpSusNoFromSus(String sus_no) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select c.sus_no from orbat_view_corps c  \r\n"
					+ "inner join tb_miso_orbat_unt_dtl u on u.status_sus_no='Active' and u.sus_no=? \r\n"
					+ "where substring(u.form_code_operation,2,2) = c.corps_code limit 1";
			stmt = conn.prepareStatement(sql1);
			System.err.println("query:...get corps sus:: \n " + stmt);
			stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("sus_no"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}

		return list1;
	}

	////////

	public List<String> getNRSFromSus(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select code_value from Tb_Miso_Orbat_Code where code in (select nrs_code  from Miso_Orbat_Unt_Dtl where status_sus_no = 'Active' and sus_no=:sus_no)");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotSUSFromDepotName(String nodal_dir) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sus_no from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_MCT_NODAL_DIR_MASTER )  and upper(unit_name) =:nodal_dir and status_sus_no='Active'");
		q.setParameter("nodal_dir", nodal_dir.toUpperCase());
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_NODAL_DIR_MASTER> list = (List<TB_TMS_MCT_NODAL_DIR_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_TMS_MCT_NODAL_DIR_MASTER> getDepotNameFromDepotSusNo(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select unit_name from Miso_Orbat_Unt_Dtl where sus_no =:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_NODAL_DIR_MASTER> list = (List<TB_TMS_MCT_NODAL_DIR_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	/*
	 * public String setApprovedItem(int appid, String ro_no, String user_id) {
	 * Session sessionHQL = null; Transaction tx = null; try { sessionHQL =
	 * HibernateUtil.getSessionFactory().openSession(); tx =
	 * sessionHQL.beginTransaction(); String hqlUpdate =
	 * "update TB_TMS_RO_VEHICLE_DTLS c set c.status = :status  ,c.modify_by=:user_id where c.id = :id"
	 * ; int app = sessionHQL.createQuery(hqlUpdate).setString("status",
	 * "1").setString("user_id", user_id) .setInteger("id", appid).executeUpdate();
	 * sessionHQL.flush(); sessionHQL.clear(); if (app > 0) { String hqlUpdateRO =
	 * "update TB_TMS_RO_MAIN c set c.status=:status,approve_date=:approve_date where c.ro_no =:ro_no"
	 * ; sessionHQL.createQuery(hqlUpdateRO).setString("status",
	 * "1").setDate("approve_date", new Date()) .setString("ro_no",
	 * ro_no).executeUpdate(); sessionHQL.flush(); sessionHQL.clear(); }
	 * tx.commit(); return "Release Order Approved Successfully."; } catch
	 * (RuntimeException e) { tx.rollback(); return
	 * "Release Order Approved not Successfully."; } finally { if (sessionHQL !=
	 * null) { sessionHQL.close(); } } }
	 */

	public String setApprovedItem(int appid, String ro_no, String user_id, String type_of_veh) {
		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();

			// Update status in TB_TMS_RO_VEHICLE_DTLS
			String hqlUpdate = "update TB_TMS_RO_VEHICLE_DTLS c set c.status = :status, c.modify_by = :user_id where c.id = :id";
			int app = sessionHQL.createQuery(hqlUpdate).setString("status", "1").setString("user_id", user_id)
					.setInteger("id", appid).executeUpdate();
			sessionHQL.flush();
			sessionHQL.clear();

			if (app > 0) {
				// Get the current timestamp for approve_date
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				Timestamp validUptoDate = null;
				
				// Calculate valid_upto date (45 days from approve_date)
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentTimestamp); // Set the calendar to current time
				calendar.add(Calendar.DAY_OF_YEAR, 45);
				Timestamp validUpto = new Timestamp(calendar.getTimeInMillis());
				System.err.println(type_of_veh);
				if(type_of_veh.equals("B")) {
				 validUptoDate = validUpto;
				}else {
					 validUptoDate = currentTimestamp;
				}
				// Update status, approve_date, and valid_upto in TB_TMS_RO_MAIN
				String hqlUpdateRO = "update TB_TMS_RO_MAIN c set c.status = :status, c.approve_date = :approve_date, c.valid_upto = :valid_upto where c.ro_no = :ro_no";
				sessionHQL.createQuery(hqlUpdateRO).setString("status", "1")
						.setTimestamp("approve_date", currentTimestamp) // Set approve_date with timestamp
						.setTimestamp("valid_upto", validUptoDate) // Set valid_upto with timestamp
						.setString("ro_no", ro_no).executeUpdate();
				sessionHQL.flush();
				sessionHQL.clear();
			}

			tx.commit();
			return "Release Order Approved Successfully.";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			return "Release Order Approved not Successfully.";
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	public String setRejectItem(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_RO_VEHICLE_DTLS c set c.status = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Release Order Rejected Successfully.";
		} else {
			return "Release Order not Rejected.";
		}
	}

	public String setDeleteItem(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from TB_TMS_RO_VEHICLE_DTLS where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", appid);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Release Order Deleted Successfully.";
		} else {
			return "Release Order not Deleted.";
		}
	}

	public TB_TMS_RO_VEHICLE_DTLS getRIOVehDtls(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_RO_VEHICLE_DTLS where id=:id");
		q.setParameter("id", id);
		TB_TMS_RO_VEHICLE_DTLS getDrrDir = (TB_TMS_RO_VEHICLE_DTLS) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return getDrrDir;
	}

	public TB_TMS_RIO_VEHICLE_DTLS getRIOVehAllDtls(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from TB_TMS_RIO_VEHICLE_DTLS where id=:id");
		q.setParameter("id", id);
		TB_TMS_RIO_VEHICLE_DTLS getrio = (TB_TMS_RIO_VEHICLE_DTLS) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return getrio;
	}

	public List<String> getUHFromMCT(BigInteger mct, String sus_no) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select count(b.ba_no) from  TB_TMS_MVCR_PARTA_DTL p,TB_TMS_BANUM_DIRCTRY b where  p.ba_no = b.ba_no and left(cast(b.mct as character varying), 4) = left(cast(? as character varying), 4) and cast(p.classification as integer) < 5 and p.sus_no = ?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, String.valueOf(mct));
			stmt.setString(2, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				String id = rs1.getString("count");
				list1.add(id);
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
		return list1;
	}

	public List<String> getPrfUHFromMCT(BigInteger mct, String sus_no) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String sql1 = "";
			String prf = "";

			sql = "select m.prf_code as prf from tb_tms_mct_main_master m where left(cast(m.mct_main_id as character varying), 4) = left(cast(? as character varying), 4) ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(mct));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				prf = rs.getString("prf");
			}
			rs.close();

			sql1 = "select count(b.mct) from tb_tms_mct_main_master m, tb_tms_mvcr_parta_dtl p,tb_tms_banum_dirctry b \r\n"
					+ "where  p.ba_no = b.ba_no and left(cast(m.mct_main_id as character varying), 4) = left(cast(b.mct as character varying), 4)  and cast(p.classification as integer) < 5 and p.sus_no = ? and m.prf_code = ? ";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			stmt.setString(2, prf);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				String id = rs1.getString("count");
				list1.add(id);
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
		return list1;
	}

	public List<String> getPrfUEFromMCT(BigInteger mct, String sus_no) {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;

		try {

			// get Prf from mct
			Session sessionPrf = HibernateUtil.getSessionFactory().openSession();
			Transaction txPrf = sessionPrf.beginTransaction();
			Query qPrf = sessionPrf.createQuery(
					"select m.prf_code as prf from TB_TMS_MCT_MAIN_MASTER m where m.mct_main_id = SUBSTR(:mct,1,4) ");
			qPrf.setParameter("mct", String.valueOf(mct));
			@SuppressWarnings("unchecked")
			List<String> prf = (List<String>) qPrf.list();
			txPrf.commit();
			sessionPrf.close();
			String prf_code = prf.get(0).toString();

			if (!prf_code.equals("") & !sus_no.equals("")) {
				// get Prf wise UE
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				// Change Qry on 22-09-2020 by Paresh
				String sql1 = "SELECT \r\n" + "					--a.mct_no,\r\n"
						+ "    				sum(a.auth_amt) AS total\r\n"
						+ "   				FROM (SELECT b_1.sus_no,\r\n"
						+ "							a_1.we_pe_no,\r\n" + "							a_1.mct_no,\r\n"
						+ "							a_1.auth_amt,\r\n"
						+ "							' '::text AS condition,\r\n"
						+ "							'BASEAUTH'::text AS typeofauth\r\n"
						+ "						   FROM cue_tb_miso_wepe_transport_det a_1\r\n"
						+ "						   JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text\r\n"
						+ "						 where b_1.sus_no = ?\r\n" + "						UNION\r\n"
						+ "						 SELECT a_1.sus_no,\r\n"
						+ "							a_1.we_pe_no,\r\n" + "							b_1.mct_no,\r\n"
						+ "							sum(b_1.amt_inc_dec) AS sum,\r\n"
						+ "							''::text AS condition,\r\n"
						+ "							'MOD'::text AS typeofauth\r\n"
						+ "						   FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n"
						+ "							 JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n"
						+ "						  WHERE a_1.status::text = '1'::text and a_1.sus_no = ?\r\n"
						+ "						  GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n"
						+ "						UNION\r\n" + "						 SELECT a_1.sus_no,\r\n"
						+ "							b_1.we_pe_no,\r\n"
						+ "							b_1.in_lieu_mct,\r\n"
						+ "							b_1.actual_inlieu_auth,\r\n"
						+ "							b_1.condition,\r\n"
						+ "							'INLIEU'::text AS typeofauth\r\n"
						+ "						   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
						+ "							 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
						+ "							 JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n"
						+ "							 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
						+ "					  		where a_1.sus_no = ?\r\n" + "						UNION\r\n"
						+ "						 SELECT a_1.sus_no,\r\n"
						+ "							b_1.we_pe_no,\r\n" + "							b_1.mct_no,\r\n"
						+ "							b_1.amt_inc_dec,\r\n"
						+ "							b_1.condition,\r\n"
						+ "							'INCDEC'::text AS typeofauth\r\n"
						+ "						   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
						+ "							 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n"
						+ "							 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
						+ "					  		where a_1.sus_no = ?\r\n" + "						UNION\r\n"
						+ "						 SELECT DISTINCT a_1.sus_no,\r\n"
						+ "							b_1.we_pe_no,\r\n" + "							b_1.mct_no,\r\n"
						+ "							sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n"
						+ "							' '::text AS condition,\r\n"
						+ "							'REDUCEDUEINLIEU'::text AS typeofauth\r\n"
						+ "						   FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
						+ "							 JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
						+ "							 JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n"
						+ "							 JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
						+ "					  		where a_1.sus_no = ?\r\n"
						+ "						  GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no) a\r\n"
						+ "					 JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text and b.prf_code = ?\r\n"
						+ "				  GROUP BY a.sus_no, a.we_pe_no --,a.mct_no\r\n"
						+ "				  ORDER BY a.we_pe_no";

				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
				stmt.setString(3, sus_no);
				stmt.setString(4, sus_no);
				stmt.setString(5, sus_no);
				stmt.setString(6, prf_code);
				ResultSet rs1 = stmt.executeQuery();
				while (rs1.next()) {
					String id = rs1.getString("total");
					list1.add(id);
				}
				rs1.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list1;
	}

	// changes by Mitesh(22-10-2024)
	public ArrayList<ArrayList<String>> SearchgetAttributeFromROMainAndVehDtl(String status, String issue_date,
			String to_date, String type_of_issue, String type_of_ro, String sus_no, String depot_sus_no,
			HttpSession session) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			String qry = "";

			if (status != "") {
				qry += "  d.status =  ?";
			}
			if (!sus_no.equals("")) {
				qry += " and d.sus_no = ?";
			}
			if (!depot_sus_no.equals("")) {
				qry += " and d.depot_sus_no = ?";
			}
			if (!issue_date.equals("") && !to_date.equals("")) {
				qry += " and d.date_of_submission between cast(? as date)" + " and " + "cast(? as date)";
			}
			if (!issue_date.equals("") && to_date.equals("")) {

				Date to_date1 = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				to_date = dateFormat.format(to_date1);
				qry += " and d.date_of_submission between  cast(? as date) and  cast(? as date) ";
			}
			if (!type_of_issue.equals("")) {
				qry += "  and d.type_of_issue = ?";
			}
			if (!type_of_ro.equals("")) {
				qry += "  and d.type_of_ro = ?";
			}

			// changed for RIO details display only under formation
			String roleFormationNo = session.getAttribute("roleFormationNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();

			String fcode = "";
			if (roleAccess.equals("Formation")) {
				if (roleSubAccess.equals("Command")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Corps")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0))
							+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Division")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0))
							+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
							+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
							+ String.valueOf(roleFormationNo.charAt(5));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Brigade")) {
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
			}

			sql1 = "select distinct d.ro_no,d.type_of_vehicle,u1.unit_name,cmd.short_form as command_unit_name,\r\n"
					+ "	d.mct,d.std_nomclature,d.quantity,t.label as type_of_issue ,\r\n"
					+ "	ltrim(TO_CHAR( d.date_of_submission,'dd-mm-yyyy'),'0') as  date_of_submission, \r\n"
					+ "	ltrim(TO_CHAR( m.valid_upto,'dd-mm-yyyy'),'') as valid_upto, \r\n"
					+ "	d.inliuemct,d.id,u3.unit_name as depot_unit_name,d.status \r\n"
					+ " from TB_TMS_RO_VEHICLE_DTLS d \r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = d.sus_no and u1.status_sus_no = 'Active' "
					+ fcode + " \r\n"
					+ " inner join orbat_view_cmd cmd on cmd.cmd_code = Substr(u1.form_code_control,1,1) \r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u3 on u3.sus_no = d.depot_sus_no and u3.status_sus_no = 'Active'\r\n"
					+ "	left join T_Domain_Value t on t.domainid='VEHICLEISSUETYPE' and t.codevalue=d.type_of_issue \r\n"
					+ "	left join tb_tms_ro_main m on m.ro_no=d.ro_no \r\n" + " where " + qry + " order by d.ro_no";

			stmt = conn.prepareStatement(sql1);
			int j = 1;
			if (status != "") {
				stmt.setString(j, status);
				j += 1;
			}
			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}
			if (!depot_sus_no.equals("")) {
				stmt.setString(j, depot_sus_no);
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
			if (!type_of_issue.equals("")) {
				stmt.setString(j, type_of_issue);
				j += 1;
			}
			if (!type_of_ro.equals("")) {
				if (type_of_ro.equals("1")) {
					type_of_ro = "Single RO";
				} else if (type_of_ro.equals("3")) {
					type_of_ro = "Loan RO";
				} else if (type_of_ro.equals("4")) {
					type_of_ro = "NRU RO";
				} else if (type_of_ro.equals("5")) {
					type_of_ro = "Provisional RO";
				}

				stmt.setString(j, type_of_ro);
			}
			System.out.println("Query1--" + stmt);
			ResultSet rs1 = stmt.executeQuery();
			String checkbox = "";
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("ro_no"));// 0
				list.add(rs1.getString("unit_name"));// 1
				list.add(rs1.getString("command_unit_name"));// 2
				list.add(rs1.getString("mct"));// 3
				list.add(rs1.getString("std_nomclature"));// 4
				list.add(rs1.getString("quantity"));// 5
				list.add(rs1.getString("type_of_issue"));// 6
				list.add(rs1.getString("date_of_submission"));// 7
				list.add(rs1.getString("inliuemct"));// 8
				list.add(rs1.getString("id"));// 9
				list.add(rs1.getString("depot_unit_name"));// 10
				list.add(rs1.getString("type_of_vehicle"));// 11
				list.add(rs1.getString("valid_upto"));// 12
				String viewAction = "onclick=\"viewRoDetails(" + rs1.getString("id") + ")\"";
				String viewBtn = "<i class='action_icons action_view' " + viewAction + " title='View RO Data'></i>";
				list.add(viewBtn);// 13

				if (roleAccess.equals("MISO")
						&& (rs1.getString("status").equals("0") || rs1.getString("status").equals("2"))) {
					checkbox = "<input type='checkbox' name='checkbox_name1[]' class='nrCheckBox' id='NRIN "
							+ rs1.getString("id") + "' value =" + rs1.getString("id") + " onclick='setid("
							+ rs1.getString("id") + ") onclick='findselected();' />";
					list.add(checkbox);// 14

					list.add("");
				}
				if (roleAccess.equals("MISO") && rs1.getString("status").equals("1")) {
					list.add("");
					String cancelAction = "onclick=\"  if (confirm('Are You Sure you want to Cancel RO ?') ){cancelRoDetails("
							+ rs1.getString("id") + ",'" + rs1.getString("ro_no") + "','"
							+ rs1.getString("type_of_vehicle") + "')}else{ return false;}\"";
					String cancelBtn = "<i class='action_icons action_cancel' " + cancelAction
							+ " title='Cancel RO'></i>";
					list.add(cancelBtn);// 15
				}

				

				String currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				String validUptoStr = rs1.getString("valid_upto").trim(); 

				System.out.println("validUptoStr--" + validUptoStr); 

				// Define the correct date format for parsing
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

				try {
				    // Parse the validUpto string to a LocalDate
				    LocalDate validUptoDate = LocalDate.parse(validUptoStr, formatter);
				    LocalDate sevenDaysBefore = validUptoDate.minusDays(7);
				    LocalDate currentDateLocal = LocalDate.parse(currentdate, formatter); // Convert currentdate to LocalDate

				    // Format the new dates back to strings
				    String sevenDaysBeforeStr = sevenDaysBefore.format(formatter);
				    String validUptoStrr = validUptoDate.format(formatter);

				    // Output the result
				    System.out.println("sevenDaysBeforeStr--" + sevenDaysBeforeStr);
				    System.out.println("validUptoPlusOneStr--" + validUptoStrr);
				    System.out.println("currentdate--" + currentdate);

				    // Check if current date is within the seven-day period before validUptoDate
				    if ("1".equals(rs1.getString("status")) && "B".equals(rs1.getString("type_of_vehicle")) &&
				            (currentDateLocal.isEqual(sevenDaysBefore) || currentDateLocal.isEqual(validUptoDate) || 
				             (currentDateLocal.isAfter(sevenDaysBefore) && currentDateLocal.isBefore(validUptoDate)))) {
				        String editAction = "onclick=\"editRoDetails(" + rs1.getString("id") + ")\"";
				        String editBtn = "<i class='action_icons action_update' " + editAction + " title='Edit RO Data'></i>";
				        list.add(editBtn); // 16
				    } else {
				        list.add(""); // No edit button
				    }
				} catch (Exception e) {
				    System.out.println("Error parsing date: " + e.getMessage());
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

	// get UE by sus_no and mct 4 digit

	public String getUEFromMCT_SUSNO(String mct, String sus_no) {
		String list1 = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "SELECT \r\n" + "	sum(a.auth_amt) AS total\r\n" + "   FROM ( SELECT b_1.sus_no,\r\n"
					+ "            a_1.we_pe_no,\r\n" + "            a_1.mct_no,\r\n" + "            a_1.auth_amt,\r\n"
					+ "            ' '::text AS condition,\r\n" + "            'BASEAUTH'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_miso_wepe_transport_det a_1\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n"
					+ "		 	a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND \r\n"
					+ "		 	a_1.status::text = '1'::text\r\n"
					+ "		 	where a_1.mct_no =SUBSTRING(?,1,4) and b_1.sus_no =?\r\n" + "        UNION\r\n"
					+ "         SELECT a_1.sus_no,\r\n" + "            a_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            sum(b_1.amt_inc_dec) AS sum,\r\n"
					+ "            ''::text AS condition,\r\n" + "            'MOD'::text AS typeofauth\r\n"
					+ "			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n"
					+ "			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text \r\n"
					+ "		 		AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n"
					+ "          WHERE a_1.status::text = '1'::text and b_1.mct_no = SUBSTRING(?,1,4) and a_1.sus_no =?\r\n"
					+ "          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + "        UNION\r\n"
					+ "         SELECT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.in_lieu_mct,\r\n" + "            b_1.actual_inlieu_auth,\r\n"
					+ "            b_1.condition,\r\n" + "            'INLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.in_lieu_mct = SUBSTRING(?,1,4) and a_1.sus_no =? \r\n" + "        UNION\r\n"
					+ "         SELECT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            b_1.amt_inc_dec,\r\n"
					+ "            b_1.condition,\r\n" + "            'INCDEC'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.mct_no = SUBSTRING(?,1,4) and a_1.sus_no =?\r\n" + "        UNION\r\n"
					+ "         SELECT DISTINCT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n"
					+ "            ' '::text AS condition,\r\n"
					+ "            'REDUCEDUEINLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "         where b_1.mct_no = SUBSTRING(?,1,4) and a_1.sus_no =?	 \r\n"
					+ "		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + "		) a\r\n"
					+ "     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n"
					+ "  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + "  ORDER BY a.we_pe_no";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, String.valueOf(mct));
			stmt.setString(2, sus_no);
			stmt.setString(3, String.valueOf(mct));
			stmt.setString(4, sus_no);
			stmt.setString(5, String.valueOf(mct));
			stmt.setString(6, sus_no);
			stmt.setString(7, String.valueOf(mct));
			stmt.setString(8, sus_no);
			stmt.setString(9, String.valueOf(mct));
			stmt.setString(10, sus_no);

			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1 = rs1.getString("total");
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
		return list1;
	}

	public List<Map<String, Object>> getRODetails(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select 	distinct d.ro_no,\r\n" + "		u1.unit_name,\r\n"
					+ "		coalesce(cmd.cmd_name,'') as command_name,\r\n"
					+ "		coalesce(u3.unit_name,'') as depot_name,\r\n"
					+ "		coalesce(nrs.code_value,'') as nrs,\r\n"
					+ "		coalesce(t.label,'') as type_of_issue,\r\n"
					+ "		upper(d.issue_stock) as issue_stock,\r\n" + "		d.type_of_vehicle,\r\n"
					+ "		TO_CHAR(d.date_of_submission,'dd-mm-yyyy') as  issue_date,\r\n"
					+ "		ltrim(TO_CHAR( m.valid_upto,'dd-mm-yyyy'),'0') as valid_upto, \r\n"
					+ "		cast (m.extended_on as date) as extended_on, \r\n"
					+ "		ltrim(TO_CHAR( m.approve_date,'dd-mm-yyyy'),'0') as approve_date,\r\n"
					+ "		coalesce(d.other_issue_type,'NA') as other_issue_type,\r\n"
					+ "		coalesce(d.loan_auth,'NA') as loan_auth,\r\n"
					+ "		coalesce(d.loan_duration,'0') as loan_duration,\r\n" + "		d.mct,\r\n"
					+ "		d.std_nomclature,\r\n" + "		d.quantity,\r\n"
					+ "		coalesce(d.inliuemct,'0') as inliuemct,\r\n"
					+ "		coalesce(inluemct.std_nomclature,'NA') as std_nomclature_inlieu,\r\n"
					+ "		upper(d.accounting) as accounting,\r\n"
					+ "		upper(d.issue_precedence) as issue_precedence,\r\n" + "		d.remarks,\r\n"
					+ "		d.status \r\n"
					/* + "d.modify_by \r\n" */ // commented because of null value
					+ "	from TB_TMS_RO_VEHICLE_DTLS d\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = d.sus_no and u1.status_sus_no = 'Active'\r\n"
					+ "	inner join orbat_view_cmd cmd on cmd.cmd_code = Substr(u1.form_code_control,1,1)\r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u3 on u3.sus_no = d.depot_sus_no and u3.status_sus_no = 'Active'\r\n"
					+ "	left join T_Domain_Value t on t.domainid='VEHICLEISSUETYPE' and t.codevalue=d.type_of_issue\r\n"
					+ "	left join tb_miso_orbat_code nrs on u1.nrs_code = nrs.code and nrs.code_type='Location'\r\n"
					+ "	left join tb_tms_mct_master inluemct on inluemct.mct=cast(d.inliuemct as numeric)\r\n"
					+ "	left join tb_tms_ro_main m on m.ro_no=d.ro_no\r\n" + "	where d.id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Query-->" + stmt);
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

	public List<Map<String, Object>> getCancelRO(int id, String ro_no, String type_veh, String username, Date cur_date,
			String remarks) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q1 = "", q2 = "", q3 = "", q4 = "", q5 = "", q6 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt1, stmt2, stmt3, stmt4, stmt5, stmt6;
			if (type_veh.equals("B")) {
				q1 = " delete from tb_tms_mvcr_parta_dtl where rio_no in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ?) ";

				q2 = " delete from tb_tms_drr_dir_main where drr_dir_ser_no in \r\n"
						+ "(select drr_dir_ser_no from tb_tms_drr_dir_dtl where issue_against_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? )) ";

				q3 = " delete from tb_tms_drr_dir_dtl where issue_against_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? ) ";

				q4 = " update tb_tms_rio_vehicle_dtls set status='3' where ro_no= ? ";

				q5 = " update tb_tms_ro_main set status='3',deleted_by='" + username + "',deleted_date='" + cur_date
						+ "' where ro_no=? ";

				q6 = " update tb_tms_ro_vehicle_dtls set status='3',remarks=? where ro_no=? ";
			}
			if (type_veh.equals("A")) {
				q1 = " delete from tb_tms_census_retrn where ba_no in (select ba_no from tb_tms_census_drr_dir_dtl where issue_agnst_rio \r\n"
						+ "in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? ))";

				q2 = " delete from tb_tms_census_drr_dir_main where drr_dir_ser_no in \r\n"
						+ "(select drr_dir_ser_no from tb_tms_census_drr_dir_dtl where issue_agnst_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? )) ";

				q3 = " delete from tb_tms_census_drr_dir_dtl where issue_agnst_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? ) ";

				q4 = " update tb_tms_rio_vehicle_dtls set status='3' where ro_no= ? ";

				q5 = " update tb_tms_ro_main set status='3',deleted_by='" + username + "',deleted_date='" + cur_date
						+ "' where ro_no=? ";

				q6 = " update tb_tms_ro_vehicle_dtls set status='3',remarks=? where ro_no=? ";
			}

			if (type_veh.equals("C")) {
				q1 = " delete from tb_tms_emar_report where em_no in (select em_no from tb_tms_emar_drr_dir_dtl where issue_agnst_rio \r\n"
						+ "in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? ))";

				q2 = " delete from tb_tms_emar_drr_dir_main where drr_dir_ser_no in \r\n"
						+ "(select drr_dir_ser_no from tb_tms_emar_drr_dir_dtl where issue_agnst_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? )) ";

				q3 = " delete from tb_tms_emar_drr_dir_dtl where issue_agnst_rio in (select rio_no from tb_tms_rio_vehicle_dtls where ro_no = ? ) ";

				q4 = " update tb_tms_rio_vehicle_dtls set status='3' where ro_no= ? ";

				q5 = " update tb_tms_ro_main set status='3',deleted_by='" + username + "',deleted_date='" + cur_date
						+ "' where ro_no=? ";

				q6 = " update tb_tms_ro_vehicle_dtls set status='3',remarks=? where ro_no=? ";
			}

			stmt1 = conn.prepareStatement(q1);
			stmt2 = conn.prepareStatement(q2);
			stmt3 = conn.prepareStatement(q3);
			stmt4 = conn.prepareStatement(q4);
			stmt5 = conn.prepareStatement(q5);
			stmt6 = conn.prepareStatement(q6);

			stmt1.setString(1, ro_no);
			stmt2.setString(1, ro_no);
			stmt3.setString(1, ro_no);
			stmt4.setString(1, ro_no);
			stmt5.setString(1, ro_no);

			stmt6.setString(1, remarks);
			stmt6.setString(2, ro_no);

			stmt1.executeUpdate();
			stmt2.executeUpdate();
			stmt3.executeUpdate();
			stmt4.executeUpdate();
			stmt5.executeUpdate();
			stmt6.executeUpdate();

			stmt1.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			stmt5.close();
			stmt6.close();

			conn.close();
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("msg", "RO Cancelled");
			list.add(columns);
		} catch (SQLException e) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("msg", "RO not Cancelled");
			list.add(columns);
		} catch (Exception e) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("msg", "RO not Cancelled");
			list.add(columns);
		}
		return list;
	}

	/* NITIN V4 16/11/2022 */
	public ArrayList<ArrayList<String>> SearchgetAttributeFromROMainAndVehDtl_excel(String status, String issue_date,
			String to_date, String type_of_issue, String type_of_ro, String sus_no, String depot_sus_no,
			HttpSession session) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			String qry = "";

			if (status != "") {
				qry += "  d.status =  ?";
			}
			if (!sus_no.equals("")) {
				qry += " and d.sus_no = ?";
			}
			if (!depot_sus_no.equals("")) {
				qry += " and d.depot_sus_no = ?";
			}
			if (!issue_date.equals("") && !to_date.equals("")) {
				qry += " and d.date_of_submission between cast(? as date)" + " and " + "cast(? as date)";
			}
			if (!issue_date.equals("") && to_date.equals("")) {

				Date to_date1 = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				to_date = dateFormat.format(to_date1);
				qry += " and d.date_of_submission between  cast(? as date) and  cast(? as date) ";
			}
			if (!type_of_issue.equals("")) {
				qry += "  and d.type_of_issue = ?";
			}
			if (!type_of_ro.equals("")) {
				qry += "  and d.type_of_ro = ?";
			}

			// changed for RIO details display only under formation
			String roleFormationNo = session.getAttribute("roleFormationNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();

			String fcode = "";
			if (roleAccess.equals("Formation")) {
				if (roleSubAccess.equals("Command")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Corps")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0))
							+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Division")) {
					roleFormationNo = String.valueOf(roleFormationNo.charAt(0))
							+ String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))
							+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4))
							+ String.valueOf(roleFormationNo.charAt(5));
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
				if (roleSubAccess.equals("Brigade")) {
					fcode = " and u1.form_code_control like '" + roleFormationNo + "%'";
				}
			}

			sql1 = "select distinct d.ro_no,u1.unit_name,cmd.short_form as command_unit_name,\r\n"
					+ "	d.mct,d.std_nomclature,d.quantity,t.label as type_of_issue ,\r\n"
					+ "	ltrim(TO_CHAR( d.date_of_submission,'dd-mm-yyyy'),'0') as  date_of_submission, \r\n"
					+ "	d.inliuemct,d.id,u3.unit_name as depot_unit_name,d.status \r\n"
					+ " from TB_TMS_RO_VEHICLE_DTLS d \r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = d.sus_no and u1.status_sus_no = 'Active' "
					+ fcode + " \r\n"
					+ " inner join orbat_view_cmd cmd on cmd.cmd_code = Substr(u1.form_code_control,1,1) \r\n"
					+ "	inner join tb_miso_orbat_unt_dtl u3 on u3.sus_no = d.depot_sus_no and u3.status_sus_no = 'Active'\r\n"
					+ "	left join T_Domain_Value t on t.domainid='VEHICLEISSUETYPE' and t.codevalue=d.type_of_issue \r\n"
					+ " where " + qry + " order by d.ro_no";

			stmt = conn.prepareStatement(sql1);
			int j = 1;
			if (status != "") {
				stmt.setString(j, status);
				j += 1;
			}
			if (!sus_no.equals("")) {
				stmt.setString(j, sus_no);
				j += 1;
			}
			if (!depot_sus_no.equals("")) {
				stmt.setString(j, depot_sus_no);
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
			if (!type_of_issue.equals("")) {
				stmt.setString(j, type_of_issue);
				j += 1;
			}
			if (!type_of_ro.equals("")) {
				if (type_of_ro.equals("1")) {
					type_of_ro = "Single RO";
				} else if (type_of_ro.equals("3")) {
					type_of_ro = "Loan RO";
				} else if (type_of_ro.equals("4")) {
					type_of_ro = "NRU RO";
				} else if (type_of_ro.equals("5")) {
					type_of_ro = "Provisional RO";
				}

				stmt.setString(j, type_of_ro);
			}

			ResultSet rs1 = stmt.executeQuery();
			String checkbox = "";
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("ro_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("command_unit_name"));
				list.add(rs1.getString("depot_unit_name"));
				list.add(rs1.getString("std_nomclature"));
				list.add(rs1.getString("quantity"));
				list.add(rs1.getString("type_of_issue"));
				list.add(rs1.getString("date_of_submission"));
				list.add(rs1.getString("inliuemct"));
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

	// Added by Mitesh (14-10-2024)
	public List<String> getsdReliefFromSus(String sus_no) {
		LocalDate currentDate = LocalDate.now();
		LocalDate gretterdate = currentDate.plus(60, ChronoUnit.DAYS);
		LocalDate lowerdate = currentDate.minus(60, ChronoUnit.DAYS);
		String bigdate = gretterdate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String smalldate = lowerdate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		List<String> list1 = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			 String sql1 = "SELECT CASE " +
                     "WHEN EXISTS (" +
                     "    SELECT 1 " +
                     "    FROM tb_miso_orbat_relief_prgm c " +
                     "    INNER JOIN tb_miso_orbat_unt_dtl u ON u.sus_no = c.sus_no " +
                     "    WHERE u.status_sus_no = 'Active' " +
                     "    AND u.sus_no = ? " +
                     "    AND cast( ? AS date) < cast(c.nmb_date AS date) " +
                     "    AND cast(c.nmb_date AS date) < cast( ? AS date) " +
                     ") THEN 'YES' " +
                     "ELSE 'NO' " +
                     "END AS sd_relief";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no); // Set the dynamic parameter
			stmt.setString(2, smalldate);
			stmt.setString(3, bigdate);
			System.err.println("getsdRelief--" + stmt);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("sd_relief")); // Get the sd_relief column value
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list1;
	}

	// Added by mitesh 17-10
	public List<Map<String, Object>> updateRomain(String ro_no, String extended_date) {
		
	    List<Map<String, Object>> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt1 = null;
	    PreparedStatement stmt2 = null;
	    String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

	    try {
	        conn = dataSource.getConnection();
	        conn.setAutoCommit(false); // Use transaction management

	        String sql1 = "UPDATE tb_tms_ro_vehicle_dtls SET valid_upto = cast(? as date) WHERE ro_no = ?";
	        stmt1 = conn.prepareStatement(sql1);
	        stmt1.setString(1, extended_date);
	        stmt1.setString(2, ro_no);
	        stmt1.executeUpdate();

	        String sql2 = "UPDATE tb_tms_ro_main SET extended_on = cast(? as date), valid_upto = cast(? as date) WHERE ro_no = ?";
	        stmt2 = conn.prepareStatement(sql2);
	        stmt2.setString(1, date);
	        stmt2.setString(2, extended_date);
	        stmt2.setString(3, ro_no);
	        stmt2.executeUpdate();

	        conn.commit(); // Commit the transaction

	        Map<String, Object> columns = new LinkedHashMap<>();
	        columns.put("msg", "RO Extended successfully");
	        list.add(columns);
	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); 
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace(); // 
	            }
	        }
	        Map<String, Object> columns = new LinkedHashMap<>();
	        columns.put("msg", "RO not Extended: " + e.getMessage());
	        list.add(columns);
	        e.printStackTrace(); 
	    } catch (Exception e) {
	        Map<String, Object> columns = new LinkedHashMap<>();
	        columns.put("msg", "RO not Extended: " + e.getMessage());
	        list.add(columns);
	        e.printStackTrace(); 
	    } finally {
	       
	        try {
	            if (stmt1 != null) {
	                stmt1.close();
	            }
	            if (stmt2 != null) {
	                stmt2.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }
	    return list;
	}

}