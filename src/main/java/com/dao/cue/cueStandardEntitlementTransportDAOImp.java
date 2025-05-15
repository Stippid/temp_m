package com.dao.cue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.cue.cueContoller;
import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class cueStandardEntitlementTransportDAOImp implements cueStandardEntitlementTransportDAO {
	cueContoller M = new cueContoller();

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	NotificationController notification = new NotificationController();
	Posting_Out_Controller post = new Posting_Out_Controller();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> getMctNoList(HttpSession sessionUserId, String mct_main_id)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		{
			List<String> list = new ArrayList<String>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select distinct mct_main_id from tb_tms_mct_main_master where upper(mct_main_id) like ? order by mct_main_id  limit 10";
				stmt = conn.prepareStatement(q);
				stmt.setString(1, mct_main_id.toUpperCase() + "%");

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("mct_main_id");
					list.add(id);
				}
				rs.close();
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
			return M.getAutoCommonList(sessionUserId, list);
		}
	}

	public List<Map<String, Object>> getStdNomenclatureList(HttpSession sessionUserId, String mct_nomen)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		{
			List<String> list = new ArrayList<String>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select distinct mct_nomen from tb_tms_mct_main_master where upper(mct_nomen) like ? order by mct_nomen limit 10";
				stmt = conn.prepareStatement(q);
				stmt.setString(1, mct_nomen.toUpperCase() + "%");

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("mct_nomen");
					list.add(id);
				}
				rs.close();
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
			return M.getAutoCommonList(sessionUserId, list);
		}
	}

	////// ------- report
	public List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getReportList() {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_DET");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANSPORT_DET> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q.list();
		tx.commit();
		return list;
	}



	public String setDeletetrans(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_WEPE_TRANSPORT_DET where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", appid);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}

	public CUE_TB_MISO_WEPE_TRANSPORT_DET getCUE_Trans_Authorizarion_id(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_WEPE_TRANSPORT_DET list = (CUE_TB_MISO_WEPE_TRANSPORT_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateTransMasterValue(CUE_TB_MISO_WEPE_TRANSPORT_DET ItemMasterValue, String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_WEPE_TRANSPORT_DET  set mct_no=:mct_no ,we_pe_no =:we_pe_no , auth_amt =:auth_amt , remarks =:remarks,modified_by=:modified_by,modified_on=:modified_on, status ='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
		Query query = session.createQuery(hql).setString("mct_no", ItemMasterValue.getMct_no())
				.setString("we_pe_no", ItemMasterValue.getWe_pe_no())
				.setInteger("auth_amt", ItemMasterValue.getAuth_amt())
				.setString("remarks", ItemMasterValue.getRemarks()).setString("modified_by", username)
				.setString("modified_on", modifydate).setString("vettedby_dte1", "")
				.setString("vettedby_dte2", "").setInteger("id", ItemMasterValue.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public int deleteAlreadyExistInFootModImp(int id, String we_pe_no) {
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session2.beginTransaction();
		Query qmct_no = session2.createQuery("select distinct mct_no from CUE_TB_MISO_WEPE_TRANSPORT_DET where id=:id");
		qmct_no.setParameter("id", id);
		@SuppressWarnings("unchecked")
		String mct_no = (String) qmct_no.uniqueResult();
		Query q = session2
				.createQuery("from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no and mct_no=:mct_no");
		q.setParameter("we_pe_no", we_pe_no);
		q.setParameter("mct_no", mct_no);
		@SuppressWarnings("rawtypes")
		List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> list = (List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES>) q.list();
		tx3.commit();
		session2.close();
		if (list.size() > 0 && list.isEmpty() == false) {
			return 1;
		} else {
			return 0;
		}
	}

	public int deleteAlreadyExistInModImp(int id, String we_pe_no) {
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session2.beginTransaction();
		Query qmct_no = session2.createQuery("select distinct mct_no from CUE_TB_MISO_WEPE_TRANSPORT_DET where id=:id");
		qmct_no.setParameter("id", id);
		@SuppressWarnings("unchecked")
		String mct_no = (String) qmct_no.uniqueResult();
		Query q = session2
				.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no and mct_no=:mct_no");
		q.setParameter("we_pe_no", we_pe_no);
		q.setParameter("mct_no", mct_no);
		@SuppressWarnings("rawtypes")
		List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q.list();
		tx3.commit();
		session2.close();

		if (list.size() > 0 && list.isEmpty() == false) {
			return 1;
		} else {
			return 0;
		}
	}

	

	public List<Map<String, Object>> getAttributeFromTransEntitlementDtl1(String mct_no, String we_pe_no, String status,
			String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (!status.equals("all")) {
					qry += " and (d.status = '0' or d.status = '2' or d.status = '1')  ";
				}
				if (mct_no != "") {
					qry += " and d.mct_no = ?";

				}
				if (we_pe_no != "") {
					qry += " and d.we_pe_no = ?";

				}
				if (qry != "")
					qry = " where " + qry.substring(4, qry.length());

				q = "  select distinct d.mct_no,d.we_pe_no,d.auth_amt,d.remarks,d.id,d.status,d.remarks,m.mct_nomen as std_nomclature,d.reject_letter_no,d.reject_remarks from cue_tb_miso_wepe_transport_det d left outer join  tb_tms_mct_main_master m on d.mct_no = cast(m.mct_main_id as text) "
						+ qry + " order by  d.status,d.we_pe_no,d.mct_no  ";

				stmt = conn.prepareStatement(q);
				int j = 1;
				if (mct_no != "") {
					stmt.setString(j, mct_no);
					j += 1;
				}
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String appButton = "<i class='action_icons action_approved'  title='Approve Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String f = "";
					if (rs.getObject(6).equals("1")) {
						f += appButton;
					} else {
						f += deleteButton;
						f += updateButton;
					}

					columns.put(metaData.getColumnLabel(5), f);

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
	}

	////////////////////////////////////////

	public List<Map<String, Object>> getViewStdlnkPersonfotnoteEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (sus_no != "") {
					qry += " and l.sus_no = ?";
				}

				q = "	select  a.we_pe_no,rk1.description as app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,dv1.label as cat_per,"
						+ "rk2.description as rank,dv2.label as rank_cat, ba.auth_amt::int as base_amt,a.condition,a.rank_cat::int as catrank  "
						+ "from cue_tb_miso_wepe_pers_footnotes a "
						+ " inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text "
						+ " inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text  "
						+ " inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text "
						+ " inner join t_domain_value dv1   on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.category_of_personnel "
						+ " inner join t_domain_value dv2  on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat"
						+ " JOIN cue_tb_wepe_link_sus_pers_footnotes l ON a.id = l.foot_fk"
						+ " left join cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code "
						+ " and ba.category_of_persn = category_of_personnel  and ba.rank = a.rank and ba.rank_cat = a.rank_cat "
						+ " left join cue_tb_wepe_link_sus_perstransweapon link on link.sus_no=l.sus_no   and l.we_pe_no=link.wepe_pers_no "
						+ " and ba.we_pe_no =a.we_pe_no  where  a.status = '1' and l.status='1' and link.status_pers='1' "
						+ qry + "  order by catrank ";
				stmt = conn.prepareStatement(q);

				if (sus_no != "") {
					stmt.setString(1, sus_no);
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
	}

	public List<Map<String, Object>> getViewStdlnkPersonModEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry11 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (sus_no != "") {
					qry11 += " and l.sus_no = ?";
				}

				q = " select  a.modification,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec::int,a.rank_cat::int as catrank,dv1.label as cat_per,rk2.description as rank,dv2.label "
						+ " as rank_cat, ba.auth_amt::int as base_amt from CUE_TB_MISO_WEPE_PERS_MDFS a  "
						+ " inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text "
						+ " inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text "
						+ " inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  "
						+ " inner join t_domain_value dv1              on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.cat_per  "
						+ " inner join t_domain_value dv2              on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat 	"
						+ " left join CUE_TB_wepe_link_sus_pers_mdfs l on  a.we_pe_no= l.we_pe_no and "
						+ " a.modification=l.modification"
						+ " left join   cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code "
						+ " left join cue_tb_wepe_link_sus_perstransweapon link on link.sus_no=l.sus_no   and l.we_pe_no=link.wepe_pers_no  "
						+ " and ba.category_of_persn = cat_per and ba.rank = a.rank and ba.rank_cat = a.rank_cat "
						+ " and ba.we_pe_no =a.we_pe_no  where  a.status = '1' and l.status='1' and link.status_pers='1' "
						+ qry11 + "  order by catrank ";
				stmt = conn.prepareStatement(q);
				if (sus_no != "") {
					stmt.setString(1, sus_no);
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

	}

	public List<Map<String, Object>> getViewStdlnkPersonEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry10 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (sus_no != "") {
					qry10 += " and tbl.sus_no = ?";
				}

				q = "SELECT  tbl.we_pe_no,tbl.arm_code,tbl.app_trd_code,tbl.cat_id,tbl.rank_cat,tbl.rank_code,tbl.auth_amt, "
						+ "tbl.des as appt_trade,tbl_rank.rankdes as rank,tbl.arm,tbl.pers_cat FROM "
						+ "( SELECT tbl_appt.sus_no,tbl_appt.we_pe_no, tbl_appt.app_trd_code, tbl_appt.rank_cat,tbl_appt.rank_code,tbl_appt.arm_code,tbl_appt.auth_amt, "
						+ "  tbl_appt.cat_id, tbl_appt.des,tbl_appt.arm,tbl_appt.pers_cat FROM "
						+ "( SELECT DISTINCT link_sus.sus_no,link_sus.wepe_pers_no AS we_pe_no,pers_det.app_trd_code, "
						+ " pers_det.rank_cat, pers_det.rank AS rank_code,pers_det.arm_code, pers_det.auth_amt, pers_det.category_of_persn AS cat_id,psg_app.description as des, "
						+ " tb_miso_orbat_arm_code.arm_desc as arm, t_domain_value.label as pers_cat FROM cue_tb_wepe_link_sus_perstransweapon  link_sus"
						+ " JOIN cue_tb_miso_wepe_pers_det pers_det ON pers_det.we_pe_no::text = link_sus.wepe_pers_no "
						+ " join cue_tb_psg_rank_app_master psg_app on psg_app.code=pers_det.app_trd_code "
						+ " join tb_miso_orbat_arm_code on tb_miso_orbat_arm_code.arm_code::text = pers_det.arm_code "
						+ "  join t_domain_value  on pers_det.category_of_persn::text = t_domain_value.codevalue::text "
						+ "   WHERE link_sus.status_pers = '1'::text AND pers_det.status::text = '1'::text  and t_domain_value.domainid::text = 'PERSON_CAT'::text ) tbl_appt)tbl  "
						+ "  join (SELECT DISTINCT sus_pers.sus_no, sus_pers.wepe_pers_no AS we_pe_no, "
						+ "  pers.app_trd_code,pers.rank_cat,pers.rank AS rank_code,pers.arm_code,pers.auth_amt, pers.category_of_persn AS cat_id, "
						+ " psg_rank.description as rankdes FROM cue_tb_wepe_link_sus_perstransweapon  sus_pers  "
						+ "  JOIN cue_tb_miso_wepe_pers_det pers ON pers.we_pe_no::text = sus_pers.wepe_pers_no "
						+ "  join cue_tb_psg_rank_app_master psg_rank on psg_rank.code=pers.rank"
						+ "  join t_domain_value td  on pers.category_of_persn::text = td.codevalue::text "
						+ "   WHERE sus_pers.status_pers = '1'::text AND pers.status::text = '1'::text and td.domainid::text = 'PERSON_CAT'::text) tbl_rank "
						+ "  on tbl.we_pe_no=tbl_rank.we_pe_no and tbl.rank_cat=tbl_rank.rank_cat and tbl.app_trd_code=tbl_rank.app_trd_code and tbl.arm_code= tbl_rank.arm_code and tbl.sus_no=tbl_rank.sus_no and tbl.auth_amt != 0 "
						+ qry10 + "  ";
				stmt = conn.prepareStatement(q);

				if (sus_no != "") {
					stmt.setString(1, sus_no);
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

	}

	public List<Map<String, Object>> getViewTransEntitlementDtl(String sus_no) {
	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select m.mct_nomen,a.mct_no,a.base::int,a.mod::int as modification,\r\n" + 
					"	a.gennotes::int,a.inlieu::int,a.reducedueinlieu::int,a.total::int  from \r\n" + 
					"(SELECT a.mct_no,\r\n" + 
					"    sum(\r\n" + 
					"        CASE\r\n" + 
					"            WHEN a.typeofauth = 'BASEAUTH'::text THEN a.auth_amt\r\n" + 
					"            ELSE 0::bigint\r\n" + 
					"        END) AS base,\r\n" + 
					"    sum(\r\n" + 
					"        CASE\r\n" + 
					"            WHEN a.typeofauth = 'MOD'::text THEN a.auth_amt\r\n" + 
					"            ELSE 0::bigint\r\n" + 
					"        END) AS mod,\r\n" + 
					"    sum(\r\n" + 
					"        CASE\r\n" + 
					"            WHEN a.typeofauth = 'INCDEC'::text THEN a.auth_amt\r\n" + 
					"            ELSE 0::bigint\r\n" + 
					"        END) AS gennotes,\r\n" + 
					"    sum(\r\n" + 
					"        CASE\r\n" + 
					"            WHEN a.typeofauth = 'INLIEU'::text THEN a.auth_amt\r\n" + 
					"            ELSE 0::bigint\r\n" + 
					"        END) AS inlieu,\r\n" + 
					"    sum(\r\n" + 
					"        CASE\r\n" + 
					"            WHEN a.typeofauth = 'REDUCEDUEINLIEU'::text THEN a.auth_amt\r\n" + 
					"            ELSE 0::bigint\r\n" + 
					"        END) AS reducedueinlieu,\r\n" + 
					"    sum(a.auth_amt) AS total\r\n" + 
					"   FROM ( SELECT a_1.mct_no,\r\n" + 
					"            a_1.auth_amt,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'BASEAUTH'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_miso_wepe_transport_det a_1\r\n" + 
					"           inner JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n" + 
					"		 		a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND a_1.status::text = '1'::text \r\n" + 
					"		 	and b_1.sus_no =?\r\n" + 
					"        UNION\r\n" + 
					"         SELECT b_1.mct_no,\r\n" + 
					"            sum(b_1.amt_inc_dec) AS sum,\r\n" + 
					"            ''::text AS condition,\r\n" + 
					"            'MOD'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n" + 
					"           Inner JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n" + 
					"          WHERE a_1.status::text = '1'::text and a_1.sus_no =?\r\n" + 
					"          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + 
					"        UNION\r\n" + 
					"         SELECT b_1.in_lieu_mct,\r\n" + 
					"            b_1.actual_inlieu_auth,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 where a_1.sus_no =?\r\n" + 
					"        UNION\r\n" + 
					"         SELECT b_1.mct_no,\r\n" + 
					"            b_1.amt_inc_dec,\r\n" + 
					"            b_1.condition,\r\n" + 
					"            'INCDEC'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where a_1.sus_no =?\r\n" + 
					"        UNION\r\n" + 
					"         SELECT b_1.mct_no,\r\n" + 
					"            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n" + 
					"            ' '::text AS condition,\r\n" + 
					"            'REDUCEDUEINLIEU'::text AS typeofauth\r\n" + 
					"           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n" + 
					"             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n" + 
					"             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n" + 
					"		 	where a_1.sus_no =?\r\n" + 
					"          GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no) a\r\n" + 
					"     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n" + 
					"  GROUP BY \r\n" + 
					"  a.mct_no\r\n" + 
					" ) as a\r\n" + 
					" inner join tb_tms_mct_main_master m on a.mct_no=m.mct_main_id \r\n" + 
					"	order by a.mct_no";

			
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, sus_no);
			stmt.setString(5, sus_no);

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
				} catch (SQLException e) {}
			}
		}
		return list;
	}

	public List<Map<String, Object>> getViewStdlnkTransEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (sus_no != "") {
					qry += " and spw.sus_no =?";
				}

				q = "	Select spw.sus_no,m.mct_nomen,d.we_pe_no,d.auth_amt,d.mct_no,d.id from CUE_TB_MISO_WEPE_TRANSPORT_DET d,TB_TMS_MCT_MAIN_MASTER m,CUE_TB_WEPE_link_sus_perstransweapon spw where (d.mct_no = cast(m.mct_main_id as text)) and d.status = '1' and (spw.wepe_trans_no = d.we_pe_no) and spw.status_trans= '1' and d.auth_amt !=0 "
						+ qry + " order by d.mct_no  ";
				stmt = conn.prepareStatement(q);
				if (sus_no != "") {
					stmt.setString(1, sus_no);
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

	}

	public List<Map<String, Object>> getViewStdlnkTransModEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry2 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (sus_no != "") {
					qry2 += " and l.sus_no = ?";
				}

				q = " select l.sus_no,c.mct_no,c.we_pe_no,c.modification,c.amt_inc_dec,d.auth_amt,m.mct_nomen from CUE_TB_MISO_WEPE_TRANSPORT_MDFS c ,CUE_TB_WEPE_link_sus_trans_mdfs l,CUE_TB_MISO_WEPE_TRANSPORT_DET d,TB_TMS_MCT_MAIN_MASTER m,cue_tb_wepe_link_sus_perstransweapon link where c.we_pe_no = l.we_pe_no and c.we_pe_no = d.we_pe_no and d.mct_no = c.mct_no  and (c.mct_no = cast(m.mct_main_id as text)) and c.status = '1' and l.status='1' and l.modification = c.modification and link.sus_no=l.sus_no and link.status_trans='1' "
						+ qry2
						+ "  group by l.sus_no,c.mct_no,c.we_pe_no,c.modification,c.amt_inc_dec,m.mct_nomen,d.auth_amt order by c.mct_no  ";
				
				
				stmt = conn.prepareStatement(q);
				if (sus_no != "") {
					stmt.setString(1, sus_no);
				}
				System.out.println("vvvvvv:" + stmt);
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

	}

	public List<Map<String, Object>> getViewStdlnkTransfotnoteEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry3 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (sus_no != "") {
					qry3 += " l.sus_no = ?";
				}

				q = " select tbl_main.sus_no,COALESCE(tbl_main.mct_no) as mct_no,tbl_main.we_pe_no,tbl_main.amt_inc_dec,tbl_main.auth_amt,\r\n "
						+ " tbl_main.condition,tbl_main.mct_nomen FROM  (select l.sus_no,c.mct_no,c.we_pe_no,( c.amt_inc_dec  )\r\n "
						+ " AS amt_inc_dec,d.auth_amt as auth_amt,\r\n"
						+ " c.condition,m.mct_nomen ,c.in_lieu_mct from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES c ,CUE_TB_WEPE_link_sus_trans_footnotes l, CUE_TB_MISO_WEPE_TRANSPORT_DET d, \r\n "
						+ " TB_TMS_MCT_MAIN_MASTER m,cue_tb_wepe_link_sus_perstransweapon link where c.we_pe_no = l.we_pe_no and c.we_pe_no = d.we_pe_no and d.mct_no = c.mct_no  and (c.mct_no = cast(m.mct_main_id as varchar))   \r\n "
						+ " and c.status = '1' and l.status='1' and l.fk_trans_foot=c.id and " + qry3
						+ " and c.type_of_footnote='1' and link.sus_no=l.sus_no and link.status_trans='1'  group by l.sus_no,c.mct_no,c.we_pe_no,c.amt_inc_dec,d.auth_amt,\r\n "
						+ " c.condition,m.mct_nomen,c.actual_inlieu_auth,c.type_of_footnote,c.in_lieu_mct order by c.mct_no) \r\n "
						+ " tbl_main order by mct_no ";

				stmt = conn.prepareStatement(q);

				if (sus_no != "") {
					stmt.setString(1, sus_no);
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

	}

	////////////// Inlieu/////////////////

	public List<Map<String, Object>> getViewStdlnkTransInlieuEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry3 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (sus_no != "") {
					qry3 += " where a.sus_no = ?";
				}

				q = " select a.sus_no,b.we_pe_no,b.mct_no,d.mct_nomen as mct_name1,c.auth_amt,b.in_lieu_mct,e.mct_nomen,b.actual_inlieu_auth,b.condition,'INLIEU' as typeofauth from cue_tb_wepe_link_sus_trans_footnotes a\r\n "
						+ " join cue_tb_miso_wepe_trans_footnotes b on a.fk_trans_foot=b.id and b.type_of_footnote='0' \r\n "
						+ " join cue_tb_miso_wepe_transport_det c on b.mct_no=c.mct_no and b.we_pe_no=c.we_pe_no\r\n "
						+ " join tb_tms_mct_main_master d on b.mct_no=d.mct_main_id\r\n "
						+ " join tb_tms_mct_main_master e on b.in_lieu_mct=e.mct_main_id\r\n "
						+ " join cue_tb_wepe_link_sus_perstransweapon link on link.sus_no=a.sus_no and link.status_trans='1' "
						+ qry3 + " order by mct_no ";

				stmt = conn.prepareStatement(q);
				if (sus_no != "") {
					stmt.setString(1, sus_no);
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

	}

	//////////////////////////

	public List<Map<String, Object>> getViewUnitDtlPersReport(String sus_no, String we_pe_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry8 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q="select  t.label as rank_category,s.rank_cat::int ,\r\n" + 
						"	  sum(case when t1.label ='ERE'  then (s.base_auth+s.mod_auth+s.foot_auth) else 0 end)::int as ere, \r\n" + 
						"	  sum(case when t1.label ='Regimental'  then (s.base_auth+s.mod_auth+s.foot_auth) else 0 end)::int as regimental,\r\n" + 
						"	  (sum(case when t1.label ='ERE'  then (s.base_auth+s.mod_auth+s.foot_auth) else 0 end)  + sum(case when t1.label ='Regimental'  then (s.base_auth+s.mod_auth+s.foot_auth) else 0 end)  )::int as total \r\n" + 
						"from	 \r\n" + 
						"(SELECT \r\n" + 
						"    tbl_pers.cat_id,\r\n" + 
						"    tbl_pers.rank_cat,\r\n" + 
						"    tbl_pers.app_trd_code,\r\n" + 
						"    COALESCE(tbl_pers.auth_amt, 0::numeric::double precision) AS base_auth,\r\n" + 
						"    COALESCE(tbl_mod.amt_inc_dec, 0::numeric::double precision) AS mod_auth,\r\n" + 
						"    COALESCE(tbl_foot.amt_inc_dec, 0::numeric) AS foot_auth--,\r\n" + 
						"   FROM (SELECT DISTINCT ptw.sus_no,\r\n" + 
						"			ptw.wepe_pers_no AS we_pe_no,\r\n" + 
						"			pers_det.app_trd_code,\r\n" + 
						"			pers_det.rank_cat,\r\n" + 
						"			pers_det.rank AS rank_code,\r\n" + 
						"			pers_det.arm_code,\r\n" + 
						"			pers_det.auth_amt,\r\n" + 
						"			pers_det.category_of_persn AS cat_id\r\n" + 
						"		   FROM cue_tb_wepe_link_sus_perstransweapon ptw\r\n" + 
						"			 JOIN cue_tb_miso_wepe_pers_det pers_det ON pers_det.we_pe_no = ptw.wepe_pers_no\r\n" + 
						"		  WHERE ptw.status_pers = '1' AND pers_det.status = '1' \r\n" + 
						"		 and ptw.sus_no = ? ) tbl_pers\r\n" + 
						"     LEFT JOIN ( SELECT link_s_p_mdfs.we_pe_no,\r\n" + 
						"            string_agg(link_s_p_mdfs.modification, ',') AS modification,\r\n" + 
						"            wepe_pers_mdfs.rank,\r\n" + 
						"            wepe_pers_mdfs.arm_code,\r\n" + 
						"            wepe_pers_mdfs.rank_cat,\r\n" + 
						"            wepe_pers_mdfs.cat_per,\r\n" + 
						"            wepe_pers_mdfs.appt_trade,\r\n" + 
						"            sum(wepe_pers_mdfs.amt_inc_dec) AS amt_inc_dec,\r\n" + 
						"            link_s_p_mdfs.sus_no\r\n" + 
						"           FROM cue_tb_miso_wepe_pers_mdfs wepe_pers_mdfs\r\n" + 
						"           inner join cue_tb_wepe_link_sus_pers_mdfs link_s_p_mdfs on link_s_p_mdfs.we_pe_no = wepe_pers_mdfs.we_pe_no AND link_s_p_mdfs.status = '1'\r\n" + 
						"          WHERE link_s_p_mdfs.modification = wepe_pers_mdfs.modification and link_s_p_mdfs.sus_no = ? \r\n" + 
						"          GROUP BY link_s_p_mdfs.we_pe_no, wepe_pers_mdfs.rank, wepe_pers_mdfs.arm_code, wepe_pers_mdfs.rank_cat, wepe_pers_mdfs.cat_per, wepe_pers_mdfs.appt_trade, link_s_p_mdfs.sus_no\r\n" + 
						"          ORDER BY link_s_p_mdfs.we_pe_no) tbl_mod ON tbl_pers.we_pe_no = tbl_mod.we_pe_no AND tbl_pers.sus_no = tbl_mod.sus_no AND tbl_pers.app_trd_code = tbl_mod.appt_trade AND tbl_pers.rank_cat = tbl_mod.rank_cat AND tbl_pers.cat_id = tbl_mod.cat_per AND tbl_pers.rank_code = tbl_mod.rank AND tbl_pers.arm_code = tbl_mod.arm_code\r\n" + 
						"     LEFT JOIN ( SELECT cue_tb_miso_wepe_pers_footnotes.we_pe_no,\r\n" + 
						"            cue_tb_miso_wepe_pers_footnotes.arm_code,\r\n" + 
						"            cue_tb_miso_wepe_pers_footnotes.rank,\r\n" + 
						"            cue_tb_miso_wepe_pers_footnotes.rank_cat,\r\n" + 
						"            cue_tb_miso_wepe_pers_footnotes.category_of_personnel,\r\n" + 
						"            cue_tb_miso_wepe_pers_footnotes.appt_trade,\r\n" + 
						"            cue_tb_wepe_link_sus_pers_footnotes.sus_no,\r\n" + 
						"            sum(cue_tb_miso_wepe_pers_footnotes.amt_inc_dec) AS amt_inc_dec\r\n" + 
						"           FROM cue_tb_miso_wepe_pers_footnotes\r\n" + 
						"           Inner JOIN cue_tb_wepe_link_sus_pers_footnotes ON cue_tb_miso_wepe_pers_footnotes.id = cue_tb_wepe_link_sus_pers_footnotes.foot_fk\r\n" + 
						"          WHERE cue_tb_wepe_link_sus_pers_footnotes.status = '1' and cue_tb_wepe_link_sus_pers_footnotes.sus_no = ?\r\n" + 
						"          GROUP BY cue_tb_miso_wepe_pers_footnotes.we_pe_no, cue_tb_miso_wepe_pers_footnotes.rank, cue_tb_miso_wepe_pers_footnotes.arm_code, cue_tb_miso_wepe_pers_footnotes.rank_cat, cue_tb_miso_wepe_pers_footnotes.category_of_personnel, cue_tb_miso_wepe_pers_footnotes.appt_trade, cue_tb_wepe_link_sus_pers_footnotes.sus_no) tbl_foot ON tbl_pers.we_pe_no = tbl_foot.we_pe_no AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.app_trd_code = tbl_foot.appt_trade AND tbl_pers.rank_cat = tbl_foot.rank_cat AND tbl_pers.cat_id = tbl_foot.category_of_personnel AND tbl_pers.rank_code = tbl_foot.rank AND tbl_pers.arm_code = tbl_foot.arm_code\r\n" + 
						") as s\r\n" + 
						"inner join t_domain_value t on t.domainid = 'RANKCATEGORY' and s.rank_cat=t.codevalue\r\n" + 
						"	inner join t_domain_value t1 on  s.cat_id = t1.codevalue and t1.domainid = 'PERSON_CAT' \r\n" + 
						" group by t.label,s.rank_cat  order by s.rank_cat\r\n";
				stmt = conn.prepareStatement(q);
				
				
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
				stmt.setString(3, sus_no);
				
				/*if (we_pe_no != "") {
					stmt.setString(j, we_pe_no.replace("&#40;", "(").replace("&#41;", ")"));
				}*/
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
	}

	public List<Map<String, Object>> getViewWeaponEntitlementDtl(String sus_no) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry4 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				/*if (sus_no != "") {
					qry4 += " having sus_no = ?";
				}
				q = " select distinct pers_id,we_wet_no,s.item_seq_no,item_type,base_auth,mod_auth,foot_auth,(base_auth + foot_auth + mod_auth ) as total,\r\n "
						+ " modification,type_we_wet,ces_cces_no,ces_cces from sus_weapon_wepe_with_wetpet s \r\n "
						+ " JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code::text = s.item_seq_no\r\n "
						+ " group by base_auth,mod_auth,foot_auth,we_wet_no,s.item_seq_no,item_type,\r\n "
						+ " sus_no,modification,type_we_wet,ces_cces,ces_cces_no,pers_id " + qry4
						+ " order by s.item_seq_no ";*/
				
				/*q = "select distinct pers_id,we_wet_no,s.item_seq_no,item_type,base_auth,mod_auth,foot_auth,\r\n" + 
						"	(base_auth + foot_auth + mod_auth ) as total,\r\n" + 
						"	modification,type_we_wet,ces_cces_no,ces_cces \r\n" + 
						"from (SELECT tbl_main.prf_group_code,\r\n" + 
						"    tbl_main.pers_id,\r\n" + 
						"    --tbl_main.sus_no,\r\n" + 
						"    tbl_main.we_wet_no,\r\n" + 
						"    tbl_main.item_seq_no,\r\n" + 
						"    tbl_main.base_auth,\r\n" + 
						"    tbl_main.mod_auth,\r\n" + 
						"    tbl_main.foot_auth,\r\n" + 
						"    tbl_main.type_we_wet,\r\n" + 
						"    array_to_string(array_agg(DISTINCT c.ces_cces), ' | ') AS ces_cces,\r\n" + 
						"    array_to_string(array_agg(DISTINCT c.ces_cces_no), ' | ') AS ces_cces_no,\r\n" + 
						"    tbl_main.modification\r\n" + 
						"   FROM ( SELECT tbl_we_pe.prf_group_code,\r\n" + 
						"            tbl_we_pe.pers_id,\r\n" + 
						"           -- tbl_we_pe.sus_no,\r\n" + 
						"            tbl_we_pe.we_wet_no,\r\n" + 
						"            tbl_we_pe.item_seq_no,\r\n" + 
						"            tbl_we_pe.base_auth,\r\n" + 
						"            tbl_we_pe.mod_auth,\r\n" + 
						"            tbl_we_pe.foot_auth,\r\n" + 
						"            tbl_we_pe.type_we_wet,\r\n" + 
						"            tbl_we_pe.modification\r\n" + 
						"           FROM ( SELECT tbl_pers.prf_group_code,\r\n" + 
						"                    tbl_pers.pers_id,\r\n" + 
						"                    tbl_pers.sus_no,\r\n" + 
						"                    tbl_pers.we_pe_no AS we_wet_no,\r\n" + 
						"                    tbl_pers.item_seq_no,\r\n" + 
						"                    COALESCE(tbl_pers.base_auth, 0::numeric::double precision) AS base_auth,\r\n" + 
						"                    COALESCE(tbl_mod.mod_auth, 0::numeric::double precision) AS mod_auth,\r\n" + 
						"                    COALESCE(tbl_foot.foot_auth, 0::numeric::double precision) AS foot_auth,\r\n" + 
						"                    tbl_pers.we_pe AS type_we_wet,\r\n" + 
						"                    tbl_mod.modification\r\n" + 
						"                   FROM ( SELECT cue_tb_miso_mms_item_mstr.prf_group_code,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.we_pe_no,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.item_seq_no,\r\n" + 
						"                                    cue_tb_miso_mms_item_mstr.id as pers_id,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.auth_weapon AS base_auth,\r\n" + 
						"                                    cue_tb_miso_wepeconditions.we_pe,\r\n" + 
						"						 			ptw.sus_no\r\n" + 
						"                                   FROM cue_tb_miso_wepe_weapon_det\r\n" + 
						"                                     JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = cue_tb_miso_wepe_weapon_det.item_seq_no\r\n" + 
						"                                     JOIN cue_tb_miso_wepeconditions ON cue_tb_miso_wepeconditions.we_pe_no = cue_tb_miso_wepe_weapon_det.we_pe_no\r\n" + 
						"								 	 JOIN cue_tb_wepe_link_sus_perstransweapon ptw ON ptw.wepe_weapon_no = cue_tb_miso_wepe_weapon_det.we_pe_no \r\n" + 
						"								 			AND ptw.status_weap = '1' and ptw.sus_no=?\r\n" + 
						"                                  WHERE cue_tb_miso_wepe_weapon_det.status = '1'\r\n" + 
						"                            ) tbl_pers\r\n" + 
						"                     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_weapon_mdfs.sus_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapons_mdfs.we_pe_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapons_mdfs.item_seq_no,\r\n" + 
						"                            sum(cue_tb_miso_wepe_weapons_mdfs.amt_inc_dec) AS mod_auth,\r\n" + 
						"                            string_agg(cue_tb_wepe_link_sus_weapon_mdfs.modification, ',') AS modification\r\n" + 
						"                           FROM cue_tb_miso_wepe_weapons_mdfs\r\n" + 
						"                             JOIN cue_tb_wepe_link_sus_weapon_mdfs ON cue_tb_wepe_link_sus_weapon_mdfs.modification = cue_tb_miso_wepe_weapons_mdfs.modification AND cue_tb_wepe_link_sus_weapon_mdfs.we_pe_no = cue_tb_miso_wepe_weapons_mdfs.we_pe_no\r\n" + 
						"                          WHERE cue_tb_wepe_link_sus_weapon_mdfs.status = '1' and  cue_tb_wepe_link_sus_weapon_mdfs.sus_no =?\r\n" + 
						"                          GROUP BY cue_tb_wepe_link_sus_weapon_mdfs.sus_no, cue_tb_miso_wepe_weapons_mdfs.we_pe_no, cue_tb_miso_wepe_weapons_mdfs.item_seq_no) tbl_mod ON tbl_pers.we_pe_no = tbl_mod.we_pe_no AND tbl_pers.sus_no = tbl_mod.sus_no AND tbl_pers.item_seq_no = tbl_mod.item_seq_no\r\n" + 
						"                     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_weapon_footnotes.sus_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapon_footnotes.we_pe_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapon_footnotes.item_seq_no,\r\n" + 
						"                            sum(cue_tb_miso_wepe_weapon_footnotes.amt_inc_dec) AS foot_auth\r\n" + 
						"                           FROM cue_tb_miso_wepe_weapon_footnotes\r\n" + 
						"                             JOIN cue_tb_wepe_link_sus_weapon_footnotes ON cue_tb_wepe_link_sus_weapon_footnotes.fk_weapon_foot = cue_tb_miso_wepe_weapon_footnotes.id\r\n" + 
						"                          WHERE cue_tb_wepe_link_sus_weapon_footnotes.status = '1' and cue_tb_wepe_link_sus_weapon_footnotes.sus_no =?\r\n" + 
						"                          GROUP BY cue_tb_wepe_link_sus_weapon_footnotes.sus_no, cue_tb_miso_wepe_weapon_footnotes.we_pe_no, cue_tb_miso_wepe_weapon_footnotes.item_seq_no) tbl_foot ON tbl_pers.we_pe_no = tbl_foot.we_pe_no AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.item_seq_no = tbl_foot.item_seq_no) tbl_we_pe\r\n" + 
						"        UNION\r\n" + 
						"         SELECT tbl_tmp.prf_group_code,\r\n" + 
						"            tbl_tmp.id,\r\n" + 
						"            --tbl_tmp.sus_no,\r\n" + 
						"            tbl_tmp.wet_pet_no AS we_wet_no,\r\n" + 
						"            tbl_tmp.item_seq_no,\r\n" + 
						"            COALESCE(tbl_tmp.base_auth, 0::numeric) AS base_auth,\r\n" + 
						"            COALESCE(0::numeric, 0::numeric) AS mod_auth,\r\n" + 
						"            COALESCE(0::numeric, 0::numeric) AS foot_auth,\r\n" + 
						"            tbl_tmp.wet_type AS type_we_wet,\r\n" + 
						"            ' ' AS modificaiton\r\n" + 
						"           FROM ( SELECT cue_tb_miso_mms_item_mstr.prf_group_code,\r\n" + 
						"                    cue_tb_miso_mms_item_mstr.id,\r\n" + 
						"                    cue_tb_miso_wepeconditions.we_pe_no,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.wet_type,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.item_seq_no,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.\"authorization\" AS base_auth,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.wet_pet_no,\r\n" + 
						"                    cue_tb_wepe_link_sus_perstransweapon.sus_no\r\n" + 
						"                   FROM cue_tb_miso_mms_wet_pet_det\r\n" + 
						"                     JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = cue_tb_miso_mms_wet_pet_det.item_seq_no\r\n" + 
						"                     JOIN cue_tb_miso_wepeconditions ON cue_tb_miso_wepeconditions.wet_pet_no = cue_tb_miso_mms_wet_pet_det.wet_pet_no\r\n" + 
						"                     JOIN cue_tb_wepe_link_sus_perstransweapon ON cue_tb_wepe_link_sus_perstransweapon.wepe_weapon_no = cue_tb_miso_wepeconditions.we_pe_no\r\n" + 
						"				 	where cue_tb_wepe_link_sus_perstransweapon.sus_no =?\r\n" + 
						"				) tbl_tmp\r\n" + 
						"		) tbl_main\r\n" + 
						"     	LEFT JOIN cue_tb_miso_ces c ON c.ces_cces_name = tbl_main.item_seq_no\r\n" + 
						"  GROUP BY tbl_main.prf_group_code,tbl_main.we_wet_no, tbl_main.item_seq_no, tbl_main.base_auth, tbl_main.mod_auth, tbl_main.foot_auth, tbl_main.type_we_wet, tbl_main.pers_id, tbl_main.modification\r\n" + 
						")as s \r\n" + 
						"JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = s.item_seq_no\r\n" + 
						"group by base_auth,mod_auth,foot_auth,we_wet_no,s.item_seq_no,item_type,\r\n" + 
						"modification,type_we_wet,ces_cces,ces_cces_no,pers_id \r\n" + 
						"order by s.item_seq_no";*/
				
				q = "select distinct pgm.prf_group,pers_id,we_wet_no,s.item_seq_no,item_type,base_auth,mod_auth,foot_auth,\r\n" + 
						"	(base_auth + foot_auth + mod_auth ) as total,\r\n" + 
						"	modification,type_we_wet,ces_cces_no,ces_cces \r\n" + 
						"from (SELECT tbl_main.prf_group_code,\r\n" + 
						"    tbl_main.pers_id,\r\n" + 
						"    --tbl_main.sus_no,\r\n" + 
						"    tbl_main.we_wet_no,\r\n" + 
						"    tbl_main.item_seq_no,\r\n" + 
						"    tbl_main.base_auth,\r\n" + 
						"    tbl_main.mod_auth,\r\n" + 
						"    tbl_main.foot_auth,\r\n" + 
						"    tbl_main.type_we_wet,\r\n" + 
						"    array_to_string(array_agg(DISTINCT c.ces_cces), ' | ') AS ces_cces,\r\n" + 
						"    array_to_string(array_agg(DISTINCT c.ces_cces_no), ' | ') AS ces_cces_no,\r\n" + 
						"    tbl_main.modification\r\n" + 
						"   FROM ( SELECT tbl_we_pe.prf_group_code,\r\n" + 
						"            tbl_we_pe.pers_id,\r\n" + 
						"           -- tbl_we_pe.sus_no,\r\n" + 
						"            tbl_we_pe.we_wet_no,\r\n" + 
						"            tbl_we_pe.item_seq_no,\r\n" + 
						"            tbl_we_pe.base_auth,\r\n" + 
						"            tbl_we_pe.mod_auth,\r\n" + 
						"            tbl_we_pe.foot_auth,\r\n" + 
						"            tbl_we_pe.type_we_wet,\r\n" + 
						"            tbl_we_pe.modification\r\n" + 
						"           FROM ( SELECT tbl_pers.prf_group_code,\r\n" + 
						"                    tbl_pers.pers_id,\r\n" + 
						"                    tbl_pers.sus_no,\r\n" + 
						"                    tbl_pers.we_pe_no AS we_wet_no,\r\n" + 
						"                    tbl_pers.item_seq_no,\r\n" + 
						"                    COALESCE(tbl_pers.base_auth, 0::numeric::double precision) AS base_auth,\r\n" + 
						"                    COALESCE(tbl_mod.mod_auth, 0::numeric::double precision) AS mod_auth,\r\n" + 
						"                    COALESCE(tbl_foot.foot_auth, 0::numeric::double precision) AS foot_auth,\r\n" + 
						"                    tbl_pers.we_pe AS type_we_wet,\r\n" + 
						"                    tbl_mod.modification\r\n" + 
						"                   FROM ( SELECT cue_tb_miso_mms_item_mstr.prf_group_code,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.we_pe_no,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.item_seq_no,\r\n" + 
						"                                    cue_tb_miso_mms_item_mstr.id as pers_id,\r\n" + 
						"                                    cue_tb_miso_wepe_weapon_det.auth_weapon AS base_auth,\r\n" + 
						"                                    cue_tb_miso_wepeconditions.we_pe,\r\n" + 
						"						 			ptw.sus_no\r\n" + 
						"                                   FROM cue_tb_miso_wepe_weapon_det\r\n" + 
						"                                     JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = cue_tb_miso_wepe_weapon_det.item_seq_no\r\n" + 
						"                                     JOIN cue_tb_miso_wepeconditions ON cue_tb_miso_wepeconditions.we_pe_no = cue_tb_miso_wepe_weapon_det.we_pe_no\r\n" + 
						"								 	 JOIN cue_tb_wepe_link_sus_perstransweapon ptw ON ptw.wepe_weapon_no = cue_tb_miso_wepe_weapon_det.we_pe_no \r\n" + 
						"								 			AND ptw.status_weap = '1' and ptw.sus_no=?\r\n" + 
						"                                  WHERE cue_tb_miso_wepe_weapon_det.status = '1'\r\n" + 
						"                            ) tbl_pers\r\n" + 
						"                     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_weapon_mdfs.sus_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapons_mdfs.we_pe_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapons_mdfs.item_seq_no,\r\n" + 
						"                            sum(cue_tb_miso_wepe_weapons_mdfs.amt_inc_dec) AS mod_auth,\r\n" + 
						"                            string_agg(cue_tb_wepe_link_sus_weapon_mdfs.modification, ',') AS modification\r\n" + 
						"                           FROM cue_tb_miso_wepe_weapons_mdfs\r\n" + 
						"                             JOIN cue_tb_wepe_link_sus_weapon_mdfs ON cue_tb_wepe_link_sus_weapon_mdfs.modification = cue_tb_miso_wepe_weapons_mdfs.modification AND cue_tb_wepe_link_sus_weapon_mdfs.we_pe_no = cue_tb_miso_wepe_weapons_mdfs.we_pe_no\r\n" + 
						"                          WHERE cue_tb_wepe_link_sus_weapon_mdfs.status = '1' and  cue_tb_wepe_link_sus_weapon_mdfs.sus_no =?\r\n" + 
						"                          GROUP BY cue_tb_wepe_link_sus_weapon_mdfs.sus_no, cue_tb_miso_wepe_weapons_mdfs.we_pe_no, cue_tb_miso_wepe_weapons_mdfs.item_seq_no) tbl_mod ON tbl_pers.we_pe_no = tbl_mod.we_pe_no AND tbl_pers.sus_no = tbl_mod.sus_no AND tbl_pers.item_seq_no = tbl_mod.item_seq_no\r\n" + 
						"                     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_weapon_footnotes.sus_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapon_footnotes.we_pe_no,\r\n" + 
						"                            cue_tb_miso_wepe_weapon_footnotes.item_seq_no,\r\n" + 
						"                            sum(cue_tb_miso_wepe_weapon_footnotes.amt_inc_dec) AS foot_auth\r\n" + 
						"                           FROM cue_tb_miso_wepe_weapon_footnotes\r\n" + 
						"                             JOIN cue_tb_wepe_link_sus_weapon_footnotes ON cue_tb_wepe_link_sus_weapon_footnotes.fk_weapon_foot = cue_tb_miso_wepe_weapon_footnotes.id\r\n" + 
						"                          WHERE cue_tb_wepe_link_sus_weapon_footnotes.status = '1' and cue_tb_wepe_link_sus_weapon_footnotes.sus_no =?\r\n" + 
						"                          GROUP BY cue_tb_wepe_link_sus_weapon_footnotes.sus_no, cue_tb_miso_wepe_weapon_footnotes.we_pe_no, cue_tb_miso_wepe_weapon_footnotes.item_seq_no) tbl_foot ON tbl_pers.we_pe_no = tbl_foot.we_pe_no AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.item_seq_no = tbl_foot.item_seq_no) tbl_we_pe\r\n" + 
						"        UNION\r\n" + 
						"         SELECT tbl_tmp.prf_group_code,\r\n" + 
						"            tbl_tmp.id,\r\n" + 
						"            --tbl_tmp.sus_no,\r\n" + 
						"            tbl_tmp.wet_pet_no AS we_wet_no,\r\n" + 
						"            tbl_tmp.item_seq_no,\r\n" + 
						"            COALESCE(tbl_tmp.base_auth, 0::numeric) AS base_auth,\r\n" + 
						"            COALESCE(0::numeric, 0::numeric) AS mod_auth,\r\n" + 
						"            COALESCE(0::numeric, 0::numeric) AS foot_auth,\r\n" + 
						"            tbl_tmp.wet_type AS type_we_wet,\r\n" + 
						"            ' ' AS modificaiton\r\n" + 
						"           FROM ( SELECT cue_tb_miso_mms_item_mstr.prf_group_code,\r\n" + 
						"                    cue_tb_miso_mms_item_mstr.id,\r\n" + 
						"                    cue_tb_miso_wepeconditions.we_pe_no,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.wet_type,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.item_seq_no,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.\"authorization\" AS base_auth,\r\n" + 
						"                    cue_tb_miso_mms_wet_pet_det.wet_pet_no,\r\n" + 
						"                    cue_tb_wepe_link_sus_perstransweapon.sus_no\r\n" + 
						"                   FROM cue_tb_miso_mms_wet_pet_det\r\n" + 
						"                     JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = cue_tb_miso_mms_wet_pet_det.item_seq_no\r\n" + 
						"                     JOIN cue_tb_miso_wepeconditions ON cue_tb_miso_wepeconditions.wet_pet_no = cue_tb_miso_mms_wet_pet_det.wet_pet_no\r\n" + 
						"                     JOIN cue_tb_wepe_link_sus_perstransweapon ON cue_tb_wepe_link_sus_perstransweapon.wepe_weapon_no = cue_tb_miso_wepeconditions.we_pe_no\r\n" + 
						"				 	where cue_tb_wepe_link_sus_perstransweapon.sus_no =?\r\n" + 
						"				) tbl_tmp\r\n" + 
						"		) tbl_main\r\n" + 
						"     	LEFT JOIN cue_tb_miso_ces c ON c.ces_cces_name = tbl_main.item_seq_no\r\n" + 
						"  GROUP BY tbl_main.prf_group_code,tbl_main.we_wet_no, tbl_main.item_seq_no, tbl_main.base_auth, tbl_main.mod_auth, tbl_main.foot_auth, tbl_main.type_we_wet, tbl_main.pers_id, tbl_main.modification\r\n" + 
						")as s \r\n" + 
						"JOIN cue_tb_miso_mms_item_mstr ON cue_tb_miso_mms_item_mstr.item_code = s.item_seq_no\r\n" +
						"join cue_tb_miso_prf_group_mst pgm on cue_tb_miso_mms_item_mstr.prf_group_code=pgm.prf_group_code\r\n"+
						"group by base_auth,mod_auth,foot_auth,we_wet_no,s.item_seq_no,item_type,\r\n" + 
						"modification,type_we_wet,ces_cces,ces_cces_no,pers_id,pgm.prf_group \r\n" + 
						"order by s.item_seq_no,pgm.prf_group";

				stmt = conn.prepareStatement(q);
				stmt.setString(1, sus_no);
				stmt.setString(2, sus_no);
				stmt.setString(3, sus_no);
				stmt.setString(4, sus_no);
				
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					if (!rs.getString("ces_cces_no").equals("")) {

						String Reject = "onclick=\"  if (confirm('Do you want to view CES details?') ){Reject12("
								+ rs.getString("pers_id") + "," + rs.getString("total") + ");$('#aaa"
								+ rs.getObject("pers_id")
								+ "').attr('data-target','#rejectModal_ces')}else{ return false;}\"";
						String deleteButton2 = "<a id='aaa" + rs.getObject("pers_id") + "' href='#' " + Reject
								+ " data-toggle='modal' style='color: blue; text-decoration: underline;'>"
								+ rs.getString("ces_cces_no") + "</a>";

						columns.put(metaData.getColumnLabel(columnCount - 1), deleteButton2);
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
	}

	public ArrayList<ArrayList<String>> ces_cess_popup(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql += " select a.auth_proposed,a.ces_cces_no,a.item_seq_no,b.item_type as mainnomen , c.item_type as subnomen,a.ces_cces from cue_tb_miso_ces a "
					+ "left join cue_tb_miso_mms_item_mstr b on a.ces_cces_name=b.item_code  "
					+ "left join cue_tb_miso_mms_item_mstr c on a.item_seq_no=c.item_code  where b.id= ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
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

	///////// dwnld//////////////////

	public @ResponseBody List<String> getcue_wepeByid_trasnsent(String we_pe_no) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry = "";

		qry = " select distinct a.doc_path from cue_wepe a, CUE_TB_MISO_WEPECONDITIONS b  where b.we_pe_no=:we_pe_no and "
				+ "  a.doc_path in (select distinct d.doc_path from cue_wepe d,CUE_TB_MISO_WEPECONDITIONS c where "
				+ " c.uploaded_wepe=d.we_pe_no and c.we_pe_no=:we_pe_no )  ";

		Query q = session.createQuery(qry);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public @ResponseBody List<String> get_amdt_to_doc(String we_pe_no) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry = "";

		qry = " select distinct a.doc_path from UploadAamendmentToDocument a, CUE_TB_MISO_WEPECONDITIONS b  where b.we_pe_no=:we_pe_no and "
				+ "  a.doc_path in (select distinct d.doc_path from UploadAamendmentToDocument d,CUE_TB_MISO_WEPECONDITIONS c where "
				+ " c.uploaded_wepe=d.we_pe_no and c.we_pe_no=:we_pe_no )  ";

		Query q = session.createQuery(qry);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public @ResponseBody List<String> get_wet_pet_doc(String wet_pet_no) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry = "";

		qry = "select distinct a.doc_path from UploadWetPetAamendmentToDocument a, CUE_TB_MISO_MMS_WET_PET_MAST_DET b  where b.wet_pet_no=:wet_pet_no and "
				+ "  a.doc_path in (select distinct d.doc_path from UploadWetPetAamendmentToDocument d,CUE_TB_MISO_MMS_WET_PET_MAST_DET c where "
				+ " c.uploaded_wetpet=d.wet_pet_no and c.wet_pet_no=:wet_pet_no )    ";

		Query q = session.createQuery(qry);
		q.setParameter("wet_pet_no", wet_pet_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public @ResponseBody List<String> get_wet_pet_amd_doc(String wet_pet_no) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String qry = "";

		qry = "select distinct a.doc_path from cue_wet_pet a, CUE_TB_MISO_MMS_WET_PET_MAST_DET b  where b.wet_pet_no=:wet_pet_no and "
				+ "  a.doc_path in (select distinct d.doc_path from cue_wet_pet d,CUE_TB_MISO_MMS_WET_PET_MAST_DET c where "
				+ " c.uploaded_wetpet=d.wet_pet_no and c.wet_pet_no=:wet_pet_no)     ";

		Query q = session.createQuery(qry);
		q.setParameter("wet_pet_no", wet_pet_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getViewStdlnkWeaponEntitlementDtl(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String qry5 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (sus_no != "") {
				qry5 += " and spw.sus_no = ?";
			}

			q = "select spw.sus_no,d.we_pe_no,m.item_type,d.item_seq_no,d.id,d.auth_weapon AS base_auth from cue_tb_miso_wepe_weapon_det d,cue_tb_miso_mms_item_mstr m,cue_tb_wepe_link_sus_perstransweapon spw where m.item_code = d.item_seq_no and d.status = '1' and  spw.wepe_weapon_no = d.we_pe_no and spw.status_weap = '1' and d.auth_weapon !=0 "
					+ qry5 + " order by d.item_seq_no";

			stmt = conn.prepareStatement(q);
			if (sus_no != "") {
				stmt.setString(1, sus_no);
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

	public List<Map<String, Object>> getViewStdlnkWeaponModEntitlementDtl(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String qry6 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (sus_no != "") {
				qry6 += " and l.sus_no = ?";
			}

			q = " select c.we_pe_no,c.modification,c.amt_inc_dec,c.item_seq_no,d.auth_weapon,m.item_type from CUE_TB_MISO_WEPE_WEAPONS_MDFS c \r\n "
					+ " ,CUE_TB_WEPE_link_sus_weapon_mdfs l, CUE_TB_MISO_WEPE_WEAPON_DET d,CUE_TB_MISO_MMS_ITEM_MSTR m ,cue_tb_wepe_link_sus_perstransweapon link\r\n "
					+ "  where m.item_code = d.item_seq_no\r\n "
					+ " and c.we_pe_no = l.we_pe_no and (c.item_seq_no = d.item_seq_no and c.we_pe_no = d.we_pe_no)  and  c.status = '1' and l.status='1' and link.status_weap='1' and\r\n "
					+ " l.modification = c.modification and link.sus_no=l.sus_no " + qry6 + "\r\n "
					+ " group by c.we_pe_no,c.modification,c.amt_inc_dec,c.item_seq_no,d.auth_weapon,m.item_type order by c.item_seq_no ";

			stmt = conn.prepareStatement(q);
			if (sus_no != "") {
				stmt.setString(1, sus_no);
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

	public List<Map<String, Object>> getViewStdlnkWeaponfotnoteEntitlementDtl(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String qry7 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (sus_no != "") {
				qry7 += " and l.sus_no = ?";
			}

			q = " select c.we_pe_no,c.condition,c.amt_inc_dec,d.auth_weapon,c.item_seq_no,m.item_type from cue_tb_miso_wepe_weapon_footnotes c ,cue_tb_wepe_link_sus_weapon_footnotes l, cue_tb_miso_wepe_weapon_det d,cue_tb_miso_mms_item_mstr m ,cue_tb_wepe_link_sus_perstransweapon link where m.item_code = d.item_seq_no and c.we_pe_no = l.we_pe_no and (c.item_seq_no = d.item_seq_no and c.we_pe_no = d.we_pe_no)  and  c.status = '1' and l.status='1' and link.status_weap='1'  and l.fk_weapon_foot=c.id and link.sus_no=l.sus_no "
					+ qry7
					+ " group by c.we_pe_no,c.condition,c.amt_inc_dec,d.auth_weapon,c.item_seq_no,m.item_type order by c.item_seq_no \r\n";
			stmt = conn.prepareStatement(q);
			if (sus_no != "") {
				stmt.setString(1, sus_no);
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

	public List<Map<String, Object>> getViewPersonEntitlementDtl(String sus_no, String we_pe_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		//String qry9 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			q="select	a.we_pe_no,t1.label AS person_category,	t.label as rank_category,\r\n" + 
					"		c.description as appointment,	c1.description AS rank,	a.base_auth::int,\r\n" + 
					"		a.mod_auth::int,a.foot_auth::int,arm.arm_desc,\r\n" + 
					"		(a.base_auth + a.mod_auth + a.foot_auth )::int as total,\r\n" + 
					"		a.modification from \r\n" + 
					"(SELECT DISTINCT tbl_pers.sus_no,\r\n" + 
					"    tbl_pers.we_pe_no,\r\n" + 
					"    tbl_pers.cat_id,\r\n" + 
					"    tbl_pers.rank_cat,\r\n" + 
					"    tbl_pers.app_trd_code,\r\n" + 
					"    tbl_pers.rank_code,\r\n" + 
					"    COALESCE(tbl_pers.auth_amt, 0::numeric::double precision) AS base_auth,\r\n" + 
					"    COALESCE(tbl_mod.amt_inc_dec, 0::numeric::double precision) AS mod_auth,\r\n" + 
					"    COALESCE(tbl_foot.amt_inc_dec, 0::numeric) AS foot_auth,\r\n" + 
					"    tbl_pers.arm_code AS arm,\r\n" + 
					"    tbl_mod.modification\r\n" + 
					"   FROM ( SELECT DISTINCT \r\n" + 
					"			ptw.sus_no,\r\n" + 
					"            ptw.wepe_pers_no AS we_pe_no,\r\n" + 
					"            pers_det.app_trd_code,\r\n" + 
					"			pers_det.rank_cat,\r\n" + 
					"			pers_det.rank AS rank_code,\r\n" + 
					"			pers_det.arm_code,\r\n" + 
					"			pers_det.auth_amt,\r\n" + 
					"			pers_det.category_of_persn AS cat_id\r\n" + 
					"		   FROM cue_tb_wepe_link_sus_perstransweapon ptw\r\n" + 
					"		   Inner JOIN cue_tb_miso_wepe_pers_det pers_det ON pers_det.we_pe_no = ptw.wepe_pers_no and pers_det.status = '1'\r\n" + 
					"		  WHERE ptw.status_pers = '1' AND ptw.sus_no= ? \r\n" + 
					" 		) tbl_pers\r\n" + 
					"     	LEFT JOIN ( SELECT cue_tb_wepe_link_sus_pers_mdfs.we_pe_no,\r\n" + 
					"            string_agg(cue_tb_wepe_link_sus_pers_mdfs.modification, ',') AS modification,\r\n" + 
					"            cue_tb_miso_wepe_pers_mdfs.rank,\r\n" + 
					"            cue_tb_miso_wepe_pers_mdfs.arm_code,\r\n" + 
					"            cue_tb_miso_wepe_pers_mdfs.rank_cat,\r\n" + 
					"            cue_tb_miso_wepe_pers_mdfs.cat_per,\r\n" + 
					"            cue_tb_miso_wepe_pers_mdfs.appt_trade,\r\n" + 
					"            sum(cue_tb_miso_wepe_pers_mdfs.amt_inc_dec) AS amt_inc_dec,\r\n" + 
					"            cue_tb_wepe_link_sus_pers_mdfs.sus_no\r\n" + 
					"           FROM cue_tb_miso_wepe_pers_mdfs,\r\n" + 
					"            cue_tb_wepe_link_sus_pers_mdfs\r\n" + 
					"          WHERE cue_tb_wepe_link_sus_pers_mdfs.we_pe_no = cue_tb_miso_wepe_pers_mdfs.we_pe_no AND cue_tb_wepe_link_sus_pers_mdfs.status = '1' AND cue_tb_wepe_link_sus_pers_mdfs.modification = cue_tb_miso_wepe_pers_mdfs.modification\r\n" + 
					"          GROUP BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no, cue_tb_miso_wepe_pers_mdfs.rank, cue_tb_miso_wepe_pers_mdfs.arm_code, cue_tb_miso_wepe_pers_mdfs.rank_cat, cue_tb_miso_wepe_pers_mdfs.cat_per, cue_tb_miso_wepe_pers_mdfs.appt_trade, cue_tb_wepe_link_sus_pers_mdfs.sus_no\r\n" + 
					"          ORDER BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no) tbl_mod ON tbl_pers.we_pe_no = tbl_mod.we_pe_no AND tbl_pers.sus_no = tbl_mod.sus_no AND tbl_pers.app_trd_code = tbl_mod.appt_trade AND tbl_pers.rank_cat = tbl_mod.rank_cat AND tbl_pers.cat_id = tbl_mod.cat_per AND tbl_pers.rank_code = tbl_mod.rank AND tbl_pers.arm_code = tbl_mod.arm_code\r\n" + 
					"     LEFT JOIN ( SELECT cue_tb_miso_wepe_pers_footnotes.we_pe_no,\r\n" + 
					"            cue_tb_miso_wepe_pers_footnotes.arm_code,\r\n" + 
					"            cue_tb_miso_wepe_pers_footnotes.rank,\r\n" + 
					"            cue_tb_miso_wepe_pers_footnotes.rank_cat,\r\n" + 
					"            cue_tb_miso_wepe_pers_footnotes.category_of_personnel,\r\n" + 
					"            cue_tb_miso_wepe_pers_footnotes.appt_trade,\r\n" + 
					"            cue_tb_wepe_link_sus_pers_footnotes.sus_no,\r\n" + 
					"            sum(cue_tb_miso_wepe_pers_footnotes.amt_inc_dec) AS amt_inc_dec\r\n" + 
					"           FROM cue_tb_miso_wepe_pers_footnotes\r\n" + 
					"             JOIN cue_tb_wepe_link_sus_pers_footnotes ON cue_tb_miso_wepe_pers_footnotes.id = cue_tb_wepe_link_sus_pers_footnotes.foot_fk\r\n" + 
					"          WHERE cue_tb_wepe_link_sus_pers_footnotes.status = '1'\r\n" + 
					"          GROUP BY cue_tb_miso_wepe_pers_footnotes.we_pe_no, cue_tb_miso_wepe_pers_footnotes.rank, cue_tb_miso_wepe_pers_footnotes.arm_code, cue_tb_miso_wepe_pers_footnotes.rank_cat, cue_tb_miso_wepe_pers_footnotes.category_of_personnel, cue_tb_miso_wepe_pers_footnotes.appt_trade, cue_tb_wepe_link_sus_pers_footnotes.sus_no) tbl_foot ON tbl_pers.we_pe_no = tbl_foot.we_pe_no AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.app_trd_code = tbl_foot.appt_trade AND tbl_pers.rank_cat = tbl_foot.rank_cat AND tbl_pers.cat_id = tbl_foot.category_of_personnel AND tbl_pers.rank_code = tbl_foot.rank AND tbl_pers.arm_code = tbl_foot.arm_code\r\n" + 
					" ) as a\r\n" + 
					" 	inner join  t_domain_value t on t.domainid = 'RANKCATEGORY' and a.rank_cat=t.codevalue\r\n" + 
					"	inner join t_domain_value t1 on  a.cat_id::text = t1.codevalue and t1.domainid = 'PERSON_CAT'::text \r\n" + 
					"	inner join cue_tb_psg_rank_app_master c on c.code::text = a.app_trd_code\r\n" + 
					"	inner join tb_miso_orbat_arm_code arm  on arm.arm_code::text = a.arm::text\r\n" + 
					"	inner join cue_tb_psg_rank_app_master c1 ON c1.code::text = a.rank_code::text  \r\n" + 
					"	inner join cue_tb_wepe_link_sus_perstransweapon link on a.sus_no::text = link.sus_no and a.we_pe_no::text = link.wepe_pers_no\r\n" + 
					"	group by a.base_auth,a.mod_auth,a.foot_auth,a.we_pe_no,t.label,c.description,arm.arm_desc,t1.label\r\n" + 
					"		,c1.description,a.rank_cat,a.base_auth::int,a.mod_auth::int,a.foot_auth::int,modification\r\n" + 
					"order by rank_cat";
			
			stmt = conn.prepareStatement(q);
			int j = 1;
			//if (sus_no != "") {
				stmt.setString(j, sus_no);
			
			//}

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

	public List<Map<String, Object>> getViewTransEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (we_pe_no != "") {
					qry += " where we_pe_no = ?";
				}
				if (status != "") {
					qry += " and d.status = ?";
				}

				q = "select distinct d.mct_no,d.we_pe_no,d.auth_amt,d.remarks,d.id,d.status,d.remarks,m.mct_nomen as std_nomclature,d.reject_letter_no,d.reject_remarks,d.vettedby_dte1,d.vettedby_dte2,"
						+ "(case when trim(d.vettedby_dte1) <> '' then concat(d.vettedby_dte1,' on ',d.vettedby_dte2)"  
						+ " else 'Not Vetted' end) as vetted "
						+ " from cue_tb_miso_wepe_transport_det d left outer join  tb_tms_mct_main_master m on d.mct_no = cast(m.mct_main_id as text) "
						+ qry + " order by  d.status,d.we_pe_no,d.mct_no ";

				stmt = conn.prepareStatement(q);

				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String f = "";
					if (rs.getObject(6).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(6).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(6).equals("2") && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectT("
							+ rs.getObject(5) + ", '" + rs.getObject(2) + "', '" + rs.getObject(1) + "', '" + rs.getObject(8) + "', '" + rs.getObject(3) + "','base');$('#rrr" + rs.getObject(5)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(5) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
					if (status != "") {
						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
						f += rejectButton;
						}
					}
					columns.put(metaData.getColumnLabel(6), f);
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

	}

	public List<Map<String, Object>> getViewStdlnkTransModEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry2 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (we_pe_no != "") {
					qry2 += " where a.we_pe_no = ?";
				}
				if (status != "") {
					qry2 += " and a.status = ?";
				}
				q = "select distinct a.we_pe_no,a.modification,a.mct_no,a.amt_inc_dec,a.remarks,a.id,m.mct_nomen as std_nomclature,a.reject_letter_no ,a.reject_remarks,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,a.status,d.auth_amt,a.vettedby_dte1,a.vettedby_dte2,"
						+ "(case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2)"  
						+ "	else 'Not Vetted' end) as vetted"
						+ " from cue_tb_miso_wepe_transport_mdfs a left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1'"
						+ " left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ " inner join  tb_tms_mct_main_master m on a.mct_no = cast(m.mct_main_id as text) LEFT JOIN cue_tb_miso_wepe_transport_det d ON a.we_pe_no=d.we_pe_no and a.mct_no=d.mct_no "
						+ qry2 + " order by a.status,a.we_pe_no,a.mct_no";
				stmt = conn.prepareStatement(q);
				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(12).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(12).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(12).equals("2") && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectT("
							+ rs.getObject(6) + ", '" + rs.getObject(1) + "', '" + rs.getObject(3) + "', '" + rs.getObject(7) + "', '" + rs.getObject(13) + "','mod','" + rs.getObject(4) + "','" + rs.getObject(2) + "');$('#rrr" + rs.getObject(6)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(6) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					if (status != "") {

						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
						}
					
					columns.put(metaData.getColumnLabel(12), f);
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

	}

	public List<Map<String, Object>> getViewStdlnkTransfotnoteEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry3 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (we_pe_no != "") {
					qry3 += " where a.we_pe_no = ?";
				}
				if (status != "") {
					qry3 += " and a.status = ?";
				}

				q = "select distinct a.we_pe_no,a.mct_no,a.amt_inc_dec,a.remarks,a.id,a.condition,m.mct_nomen as std_nomclature,a.reject_letter_no ,a.reject_remarks,"
						+ "a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,a.status,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code,d.auth_amt,a.vettedby_dte1,a.vettedby_dte2,"
						+ "(case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2)"  
						+ " else 'Not Vetted' end) as vetted "
						+ " from cue_tb_miso_wepe_trans_footnotes a left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' "
						+ " left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ " inner join  tb_tms_mct_main_master m on a.mct_no = cast(m.mct_main_id as text) LEFT JOIN cue_tb_miso_wepe_transport_det d ON a.we_pe_no=d.we_pe_no and a.mct_no=d.mct_no "
						+ qry3 + " order by a.status,	a.we_pe_no,a.mct_no ";

				stmt = conn.prepareStatement(q);

				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(12).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(12).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(12).equals("2") && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectT("
							+ rs.getObject(5) + ", '" + rs.getObject(1) + "', '" + rs.getObject(2) + "', '" + rs.getObject(7) + "', '" + rs.getObject(14) + "','incdec', '" + rs.getObject(3) + "', '" + rs.getObject(6) + "');$('#rrr" + rs.getObject(5)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(5) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
					if (status != "") {

						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
						}
					columns.put(metaData.getColumnLabel(12), f);
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

	}

	public List<Map<String, Object>> getViewWeaponEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry4 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (we_pe_no != "") {
					qry4 += "d.we_pe_no = ?";
				}
				if (status != "") {
					qry4 += " and d.status = ?";
				}

				q = "select d.we_pe_no,d.auth_weapon,m.item_type,d.item_seq_no,d.status,d.id,d.reject_letter_no,d.reject_remarks,d.vettedby_dte1,d.vettedby_dte2,"
						+ " (case when trim(d.vettedby_dte1) <> '' then concat(d.vettedby_dte1,' on ',d.vettedby_dte2)"
						+ " else 'Not Vetted' end) as vetted from cue_tb_miso_wepe_weapon_det d inner join cue_tb_miso_mms_item_mstr m "
						+ " on d.item_seq_no=m.item_code where " + qry4 + " order by d.status,d.item_seq_no";

				stmt = conn.prepareStatement(q);
				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}

				ResultSet rs = stmt.executeQuery();
				System.err.println("list 4--->  "+ stmt);
				ResultSetMetaData metaData = rs.getMetaData(); 

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(5).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(5).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(5).equals("2") && status == "") {
						f += "Reject";
					}
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectW("
							+ rs.getObject(6) + ", '" + rs.getObject(1) + "', '" + rs.getObject(2) + "', '" + rs.getObject(3) + "', '" + rs.getObject(4) + "','base');$('#rrr" + rs.getObject(6)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(6) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
					if (status != "") {
						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
					}
					columns.put(metaData.getColumnLabel(5), f);
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
	}

	public List<Map<String, Object>> getViewStdlnkWeaponModEntitlementDtladdMore(String we_pe_no, String status, String roleType,String roleAccess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String qry6 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (we_pe_no != "") {
				qry6 += " where a.we_pe_no = ?";
			}
			if (status != "") {
				qry6 += " and a.status = ?";
			}
			q = "select  a.we_pe_no,a.item_seq_no,m.item_type,n.auth_weapon as baseauth,a.amt_inc_dec,a.modification,a.scenario,concat(b.unit_name,c.unit_name,l.code_value)  as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,a.vettedby_dte1,a.vettedby_dte2,"
					+ " (case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2)"  
					+" else 'Not Vetted' end) as vetted from cue_tb_miso_wepe_weapons_mdfs a "
					+ " left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
					+ " inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "
					+ "  " + qry6 + " order by a.status,a.item_seq_no";

			stmt = conn.prepareStatement(q);
			if (we_pe_no != "") {
				stmt.setString(1, we_pe_no);
			}
			if (status != "") {
				stmt.setString(2, status);
			}
			System.out.println("ssss: " + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				if (rs.getObject(12).equals("0") && status == "") {
					f += "Pending";
				}
				if (rs.getObject(12).equals("1") && status == "") {
					f += "Approved";
				}
				if (rs.getObject(12).equals("2") && status == "") {
					f += "Reject";
				}
				
				String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectW("
						+ rs.getObject(11) + ", '" + rs.getObject(1) + "', '" + rs.getObject(4) + "', '" + rs.getObject(3) + "', '" + rs.getObject(2) + "','mod', '" + rs.getObject(5) + "' , '" + rs.getObject(6) + "');$('#rrr" + rs.getObject(6)
						+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
				
				String rejectButton = "<i id='rrr" + rs.getObject(6) + "' class='action_icons action_reject' " + Reject
						+ " title='Reject Data' data-toggle='modal'  ></i>";
				
				if (status != "") {
					if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
						f += rejectButton;
						}
				}
				
				columns.put(metaData.getColumnLabel(12), f);
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

	public List<Map<String, Object>> getViewStdlnkWeaponfotnoteEntitlementDtladdMore(String we_pe_no, String status, String roleType,String roleAccess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		String q = "";
		String qry7 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (we_pe_no != "") {
				qry7 += " where a.we_pe_no = ?";
			}
			if (status != "") {
				qry7 += " and  a.status = ?";
			}
			q = "select a.we_pe_no,a.item_seq_no,m.item_type,n.auth_weapon as baseauth,a.amt_inc_dec,a.condition,a.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit ,a.reject_letter_no,a.reject_remarks,a.id,a.status,a.remarks,concat(a.formation,a.scenario_unit,a.location) as loc_for_unit_code,a.vettedby_dte1,a.vettedby_dte2,"
					+ "(case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2) \r\n" + 
					" else 'Not Vetted' end) as vetted from cue_tb_miso_wepe_weapon_footnotes a "
					+ " left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
					+ " inner join cue_tb_miso_mms_item_mstr m on a.item_seq_no=m.item_code left outer join cue_tb_miso_wepe_weapon_det n on a.we_pe_no=n.we_pe_no and a.item_seq_no=n.item_seq_no "
					+ " " + qry7 + " order by a.status,a.item_seq_no";

			stmt = conn.prepareStatement(q);
			if (we_pe_no != "") {
				stmt.setString(1, we_pe_no);
			}
			if (status != "") {
				stmt.setString(2, status);
			}

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				if (rs.getObject(12).equals("0") && status == "") {
					f += "Pending";
				}
				if (rs.getObject(12).equals("1") && status == "") {
					f += "Approved";
				}
				if (rs.getObject(12).equals("2") && status == "") {
					f += "Reject";
				}

				String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectW("
						+ rs.getObject(11) + ", '" + rs.getObject(1) + "', '" + rs.getObject(4) + "', '" + rs.getObject(3) + "', '" + rs.getObject(2) + "','incdec', '" + rs.getObject(5) + "' , '" + rs.getObject(6) + "');$('#rrr" + rs.getObject(11)
						+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
				
				String rejectButton = "<i id='rrr" + rs.getObject(11) + "' class='action_icons action_reject' " + Reject
						+ " title='Reject Data' data-toggle='modal'  ></i>";
				
				if (status != "") {
					if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
						f += rejectButton;
						}
				}
				
				
				columns.put(metaData.getColumnLabel(12), f);
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

	public List<Map<String, Object>> getViewPersonEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry10 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (we_pe_no != "") {
					qry10 += " and tbl.we_pe_no = ?";
				}
				if (status != "") {
					qry10 += " and tbl.status = ?";
				}

				q = "SELECT tbl.we_pe_no,monew.arm_desc AS arm_code,tbl.appt_trade,tbl.description as rank,tbl.label AS rank_cat,tbl.auth_amt,tdnew.label AS person_cat, tbl.status,tbl.id,tbl.reject_letter_no, tbl.reject_remarks,tbl.vettedby_dte1,tbl.vettedby_dte2, "
						+ "(case when trim(tbl.vettedby_dte1) <> '' then concat(tbl.vettedby_dte1,' on ',tbl.vettedby_dte2)"  
						+ " else 'Not Vetted' end) as vetted "
						+ " FROM ( SELECT DISTINCT  mst.status,mst.id,mst.arm_code,mst.we_pe_no, mst.app_trd_code,app.description as appt_trade,mst.rank,rk.description,td.label,mst.rank_cat,mst.auth_amt, mst.category_of_persn,mst.reject_letter_no, mst.reject_remarks,td.codevalue,mst.vettedby_dte1,mst.vettedby_dte2 "
						+ " FROM cue_tb_miso_wepe_pers_det mst, t_domain_value td , tb_miso_orbat_arm_code mo,cue_tb_psg_rank_app_master rk, cue_tb_psg_rank_app_master app WHERE td.codevalue::text = mst.rank_cat::text and td.domainid::text = 'RANKCATEGORY'::text "
						+ " and mo.arm_code::text = mst.arm_code and UPPER(rk.level_in_hierarchy) ='RANK' and rk.code=mst.rank and upper(app.level_in_hierarchy) ='APPOINTMENT' and app.code=mst.app_trd_code) tbl,t_domain_value tdnew,tb_miso_orbat_arm_code monew WHERE tbl.category_of_persn::text = tdnew.codevalue::text and tdnew.domainid::text = 'PERSON_CAT'::text "
						+ " and tbl.arm_code::text = monew.arm_code " + qry10
						+ " order by we_pe_no,tbl.codevalue,tdnew.label,tbl.description,tbl.appt_trade,status ";
				;

				stmt = conn.prepareStatement(q);

				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}
				System.out.println("ddddd: " + stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(8).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(8).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(8).equals("2") && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectP("
							+ rs.getObject(9) + ", '" + rs.getObject(1) + "', '" + rs.getObject(2) + "', '" + rs.getObject(4) + "', '" + rs.getObject(5) + "', '" + rs.getObject(6) + "','base');$('#rrr" + rs.getObject(9)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(9) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					if (status != "") {
						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
					}
					columns.put(metaData.getColumnLabel(8), f);
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


	public List<Map<String, Object>> getViewStdlnkPersonModEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry11 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (we_pe_no != "") {
					qry11 += " where a.we_pe_no = ?";
				}
				if (status != "") {
					qry11 += " and a.status = ?";
				}

				q = " select  a.we_pe_no,a.modification,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat,a.scenario, ba.auth_amt as base_amt, a.id, a.status,   "
						+ "	concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no,a.vettedby_dte1,a.vettedby_dte2, "
						+ "(case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2)"  
						+ " else 'Not Vetted' end) as vetted "
						+ "	from CUE_TB_MISO_WEPE_PERS_MDFS a "
						+ "	left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1'  "
						+ "	left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active' "
						+ "	left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ "	inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text "
						+ "	inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text "
						+ "	inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  "
						+ "	inner join t_domain_value dv1              on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.cat_per  "
						+ "	inner join t_domain_value dv2              on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat  "
						+ "	left join   cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = cat_per and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no "
						+ qry11 + " order by a.we_pe_no,a.status  ";
				stmt = conn.prepareStatement(q);

				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(12).equals("0")  && status == "") {
						f += "Pending";
					}
					if (rs.getObject(12).equals("1")  && status == "") {
						f += "Approved";
					}
					if (rs.getObject(12).equals("2")  && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectP("
							+ rs.getObject(11) + ", '" + rs.getObject(1) + "', '" + rs.getObject(4) + "', '" + rs.getObject(7) + "', '" + rs.getObject(8) + "', '" + rs.getObject(10) + "','mod', '" + rs.getObject(5) + "', '" + rs.getObject(2) + "');$('#rrr" + rs.getObject(11)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(11) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
					if (status != "") {
						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
					}
					columns.put(metaData.getColumnLabel(12), f);
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

	}

	public List<Map<String, Object>> getViewStdlnkPersonfotnoteEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (we_pe_no != "" && !we_pe_no.equals("")) {
					qry += " where a.we_pe_no = ?";
				}
				if (status != "") {
					qry += " and a.status = ?";
				}

				q = " select  a.we_pe_no,rk1.description app_trade,monew.arm_desc as parent_arm,a.amt_inc_dec,dv1.label as cat_per,rk2.description as rank,dv2.label as rank_cat, "
						+ " a.scenario, ba.auth_amt as base_amt, a.id, concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit  ,a.reject_remarks, a.reject_letter_no,a.condition,a.status,a.vettedby_dte1,a.vettedby_dte2,"
						+ "(case when trim(a.vettedby_dte1) <> '' then concat(a.vettedby_dte1,' on ',a.vettedby_dte2)"  
						+ "else 'Not Vetted' end) as vetted   from cue_tb_miso_wepe_pers_footnotes a "
						+ " left outer join tb_miso_orbat_code l on a.location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on a.formation=b.sus_no and b.status_sus_no='Active'  left outer join tb_miso_orbat_unt_dtl c on a.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ " inner join tb_miso_orbat_arm_code monew on  a.arm_code::text = monew.arm_code::text  inner join cue_tb_psg_rank_app_master rk1 on a.appt_trade::text = rk1.code::text  "
						+ " inner join cue_tb_psg_rank_app_master rk2 on a.rank::text = rk2.code::text  inner join t_domain_value dv1   on dv1.domainid='PERSON_CAT' and dv1.codevalue=a.category_of_personnel "
						+ " inner join t_domain_value dv2  on dv2.domainid='RANKCATEGORY'  and dv2.codevalue=a.rank_cat "
						+ " left join cue_tb_miso_wepe_pers_det ba   on ba.app_trd_code=a.appt_trade and ba.arm_code =a.arm_code and ba.category_of_persn = category_of_personnel "
						+ " and ba.rank = a.rank and ba.rank_cat = a.rank_cat and ba.we_pe_no =a.we_pe_no " + qry
						+ "  order by a.we_pe_no, a.status ";

				stmt = conn.prepareStatement(q);

				if (we_pe_no != "" && !we_pe_no.equals("")) {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String f = "";
					if (rs.getObject(15).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(15).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(15).equals("2") && status == "") {
						f += "Reject";
					}
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectP("
							+ rs.getObject(10) + ", '" + rs.getObject(1) + "', '" + rs.getObject(3) + "', '" + rs.getObject(6) + "', '" + rs.getObject(7) + "', '" + rs.getObject(9) + "','incdec', '" + rs.getObject(4) + "', '" + rs.getObject(14) + "');$('#rrr" + rs.getObject(10)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(10) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
					if (status != "") {
						if (roleType.equals("DEO") && roleAccess.equals("Line_dte")){
							f += rejectButton;
							}
					}
					columns.put(metaData.getColumnLabel(15), f);
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
	}

	public List<Map<String, Object>> getViewStdlnkTransInlieuEntitlementDtladdMore(String we_pe_no,String status, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry3 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (we_pe_no != "") {
					qry3 += " where b.we_pe_no = ?";
				}
				if (status != "") {
					qry3 += " and a.status = ?";
				}
				q = "select a.id,a.sus_no,b.we_pe_no,b.mct_no,d.mct_nomen as mct_name1,c.auth_amt,b.in_lieu_mct,e.mct_nomen,b.actual_inlieu_auth,b.condition,a.status,'INLIEU' as typeofauth from cue_tb_wepe_link_sus_trans_footnotes a"
						+ " join cue_tb_miso_wepe_trans_footnotes b on a.fk_trans_foot=b.id and b.type_of_footnote='0' "
						+ " join cue_tb_miso_wepe_transport_det c on b.mct_no=c.mct_no and b.we_pe_no=c.we_pe_no"
						+ " join tb_tms_mct_main_master d on b.mct_no=d.mct_main_id"
						+ " join tb_tms_mct_main_master e on b.in_lieu_mct=e.mct_main_id" + " " + qry3
						+ " order by mct_no";

				stmt = conn.prepareStatement(q);
				if (we_pe_no != "") {
					stmt.setString(1, we_pe_no);
				}
				if (status != "") {
					stmt.setString(2, status);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String f = "";
					if (rs.getObject(10).equals("0") && status == "") {
						f += "Pending";
					}
					if (rs.getObject(10).equals("1") && status == "") {
						f += "Approved";
					}
					if (rs.getObject(10).equals("2") && status == "") {
						f += "Reject";
					}
					
					String Reject = "onclick=\" if (confirm('Are you sure to raise observation on data?') ){RejectT("
							+ rs.getObject(1) + ", '" + rs.getObject(3) + "', '" + rs.getObject(2) + "', '" + rs.getObject(3) + "', '" + rs.getObject(4) + "','base');$('#rrr" + rs.getObject(1)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					
					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' " + Reject
							+ " title='Reject Data' data-toggle='modal'  ></i>";
					
//					if (status != "") {
//						f += rejectButton;
//					}
					columns.put(metaData.getColumnLabel(10), f);

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

	}

	public List<Map<String, Object>> getViewUnitDtlTransReport(String roleSusNo, String roleAccess) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (roleSusNo != "") {
				q += " and  a.sus_no =?";
			}

			q = "select distinct a.ct_part_i_ii,c.code_value as code from tb_miso_orbat_unt_dtl a, tb_miso_orbat_codesform b,tb_miso_orbat_code c where a.code = c.code and c.code_type = 'Location' and a.sus_no=b.sus_no and a.status_sus_no = 'Active' "
					+ q;

			stmt = conn.prepareStatement(q);
			int j = 1;
			if (roleSusNo != "") {
				stmt.setString(j, roleSusNo);
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
	
	@Override
	
	public String setApprovedtrans(int appid,String username, String we_pe_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String date_of_apprv_rejc = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_WEPE_TRANSPORT_DET c set c.status = :status,c.aprv_rejc_by=:aprv_rejc_by,c.date_of_apprv_rejc=:date_of_apprv_rejc where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setString("aprv_rejc_by", username).setString("date_of_apprv_rejc", date_of_apprv_rejc).setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		

		CUE_TB_WEPE_link_sus_perstransweapon notit = getSusnotrans(we_pe_no);
		if(notit.getSus_no()!="") {
		List<UserLogin> userlist = post.getPostIN_outuseridlist(notit.getSus_no());
		
		String user_id = "";
		for (int i = 0; i < userlist.size(); i++) {
			if (i == 0) {
				user_id += String.valueOf(userlist.get(i).getUserId());
			}

			else {
				user_id += "," + String.valueOf(userlist.get(i).getUserId());
				;
			}

		}
		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
//		String we_pe_no1 = notit.getCreated_by_wepon();
		String title = "UE is updated";
		String description = "New UE is updated on WE/PE No " + we_pe_no;
		Boolean d = notification.sendNotification(title, description, user_id, notit.getCreated_by());
		}
		}
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}

	@Override
public CUE_TB_WEPE_link_sus_perstransweapon getSusnotrans(String we_pe_no) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		CUE_TB_WEPE_link_sus_perstransweapon list1 = new CUE_TB_WEPE_link_sus_perstransweapon();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_WEPE_link_sus_perstransweapon where wepe_trans_no=:we_pe_no and status_trans='1'");
		q.setParameter("we_pe_no",we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_WEPE_link_sus_perstransweapon> list = (List<CUE_TB_WEPE_link_sus_perstransweapon>) q.list();	
		session.getTransaction().commit();
		session.close();			
		if(!list.isEmpty())
		{	
		return list.get(0);	
		}
		return list1;
	}







	@Override
	public List<Map<String, Object>> getAttributeFromTransEntitlementDtl(String we_pe_no, String mct_no, String we_pe,
			String status, String roleType, String roleAccess, String roleSusNo) {
			
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		if (status != "" && status != null && status != "null") {
		if (!status.equals("all")) {
		qry += " and d.status = ?";
		}
		}
		if (mct_no != "") {
		qry += " and d.mct_no = ?";
		}
		if (we_pe_no != "") {
		qry += " and d.we_pe_no = ?";
		}

		if (qry != "") {
		qry = " where " + qry.substring(4, qry.length());
		}
		if(roleAccess.equals("Line_dte")) {
			if (qry != "") {
				qry += " and c.sponsor_dire = ?";
			}
			else {
				qry +=" where c.sponsor_dire = ?" ;
			}

		}

			
			
			q = "  select distinct d.mct_no,d.we_pe_no,d.auth_amt,d.remarks,d.id,d.status,d.remarks,m.mct_nomen as std_nomclature,"
					+ "d.reject_letter_no,d.reject_remarks from cue_tb_miso_wepe_transport_det d inner join cue_tb_miso_wepeconditions c on d.we_pe_no=c.we_pe_no"
					+ " left outer join  tb_tms_mct_main_master m on d.mct_no = cast(m.mct_main_id as text) "
					+ qry + " order by  d.status,d.we_pe_no,d.mct_no  ";
			stmt = conn.prepareStatement(q);
			
			int j = 1;
			if (status != "" && status != null && status != "null") {
			if (!status.equals("all")) {
			stmt.setString(j, status);
			j += 1;
			}
			}
			if (mct_no != "") {
			stmt.setString(j, mct_no);
			j += 1;
			}
			if (we_pe_no != "") {
			stmt.setString(j, we_pe_no);
			j += 1;
			}
			if(roleAccess.equals("Line_dte")) {
			stmt.setString(j, roleSusNo);
			}
			
			
			System.out.println("searech  std tpt auth +-------" + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
			columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			String Approved = "onclick=\"   if (confirm('Are you sure you want to approve?') ){Approved("
			+ rs.getObject(5) + ")}else{ return false;}\"";
			String appButton = "<i class='action_icons action_approve' " + Approved
			+ " title='Approve Data'></i>";

			String Reject = "onclick=\"   if (confirm('Are you sure you want to reject?') ){Reject("
			+ rs.getObject(5) + ");$('#rrr" + rs.getObject(5)
			+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
			String rejectButton = "<i id='rrr" + rs.getObject(5) + "' class='action_icons action_reject' "
			+ Reject + " title='Reject Data' data-toggle='modal'   ></i>";

			String Delete1 = "onclick=\"   if (confirm('Are you sure you want to delete?') ){Delete1("
			+ rs.getObject(5) + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1
			+ " title='Delete Data'></i>";

			String Update = "onclick=\"   if (confirm('Are you sure you want to update?') ){Update("
			+ rs.getObject(5) + ")}else{ return false;}\"";
			String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
			String LogButton = cueContoller.Get_button_info(metaData,rs);
			                              String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
			String f = "";
			if (status.equals("0")) {
			if (roleType.equals("ALL")) {
			f += appButton;
			f += rejectButton;
			f += deleteButton;
			f += updateButton;
			f += LogButton;
			}
			if (roleType.equals("APP")) {
			f += appButton;
			f += rejectButton;
			f += LogButton;
			}
			if (roleType.equals("DEO") && roleAccess.equals("Line_dte")) {
			f += deleteButton;
			f += updateButton;
			f += LogButton;
			}
			if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
			  f += appButton;
			  f += rejectButton;
			  f += LogButton;
			  }
//			  if(   roleAccess.equals("Line_dte") ) {
//			  f += deleteButton;
//			  f += updateButton;
//			  f += LogButton;
//			  }

			} else if (status.equals("1")) {
			if (roleType.equals("APP") || roleType.equals("ALL")) {
			f += rejectButton;
			f += LogButton1;
			}
			if (roleType.equals("DEO")) {
			f += "Approved";
			f += LogButton1;
			}

			} else if (status.equals("2")) {
			if (roleType.equals("APP") || roleType.equals("ALL")) {
			f += appButton;
			}
			if (roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")) {

			f += deleteButton;
			f += updateButton;
			}
			}
			columns.put(metaData.getColumnLabel(5), f);
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
	public List<Map<String, Object>> getViewWeaponCESEntitlementDtl(List<Map<String, Object>> list4) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, String> columns2 = new LinkedHashMap<String, String>();
		String pers_id="";
	for(int i=0;i<list4.size();i++)
	{		
		if(!list4.get(i).get("ces_cces_no").equals(""))
		{
			columns2.put(String.valueOf(list4.get(i).get("pers_id")), String.valueOf(list4.get(i).get("total")));
			if(pers_id.isEmpty())
			{
				pers_id += String.valueOf(list4.get(i).get("pers_id"));

			}
			else {
				pers_id += ","+String.valueOf(list4.get(i).get("pers_id"));

			}
		}
	}		
	if(pers_id!="")
	{
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		

		q = "select b.id ,cast(a.auth_proposed as text),a.ces_cces_no,a.item_seq_no,b.item_type as mainnomen , c.item_type as subnomen,a.ces_cces from cue_tb_miso_ces a \r\n"
				+ "				left join cue_tb_miso_mms_item_mstr b on a.ces_cces_name=b.item_code  \r\n"
				+ "				left join cue_tb_miso_mms_item_mstr c on a.item_seq_no=c.item_code  where b.id in ("+pers_id +" ) order by ces_cces_no ";
				

		stmt = conn.prepareStatement(q);
		int j = 1;
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();
		while (rs.next()) {			
		 int id = rs.getInt(1);
			    String authProposed = rs.getString("auth_proposed");

			    Map<String, Object> columns = new LinkedHashMap<String, Object>();
			    for (int i = 1; i <= columnCount; i++) {
			        columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			        
			    }

			    // Check for null before performing multiplication
			    String columns2Value = columns2.get(String.valueOf(id));
			    if (columns2Value != null) {
			        double total = Double.parseDouble(columns2Value) * Double.parseDouble(authProposed);
			        columns.put("total", total);
			       
			    } else {
			       
			    	 columns.put("total", 0);;
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
	
	}
		return list;
	}



public List<Map<String, Object>> getPrfCodeList(HttpSession sessionUserId, String prf_code)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct prf_code from tb_tms_mct_main_master where upper(prf_code) like ? order by prf_code  limit 10";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, prf_code.toUpperCase() + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("prf_code");
				list.add(id);
			}
			rs.close();
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
		return M.getAutoCommonList(sessionUserId, list);
	}
}

public List<Map<String, Object>> getPrfGpNameList(HttpSession sessionUserId, String prf_gp_name)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct group_name from tb_tms_mct_slot_master where upper(group_name) like ? order by group_name  limit 10";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, prf_gp_name.toUpperCase() + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("group_name");
				list.add(id);
			}
			rs.close();
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
		return M.getAutoCommonList(sessionUserId, list);
	}
}
public List<Map<String, Object>> getMctNoListPrf(HttpSession sessionUserId, String mct_main_id, String prf_code)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct mct_main_id from tb_tms_mct_main_master where upper(mct_main_id) like ?  and prf_code = ? order by mct_main_id  limit 10";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, mct_main_id.toUpperCase() + "%");
			stmt.setString(2, prf_code);
System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("mct_main_id");
				list.add(id);
			}
			rs.close();
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
		return M.getAutoCommonList(sessionUserId, list);
	}
}

public List<Map<String, Object>> getMctNCListPrf(HttpSession sessionUserId, String mct_nomen,String prf_code)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select distinct mct_nomen from tb_tms_mct_main_master where upper(mct_nomen) like ? and prf_code = ? order by mct_nomen limit 10";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, mct_nomen.toUpperCase() + "%");
			stmt.setString(2, prf_code);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("mct_nomen");
				list.add(id);
			}
			rs.close();
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
		return M.getAutoCommonList(sessionUserId, list);
	}
}

}

