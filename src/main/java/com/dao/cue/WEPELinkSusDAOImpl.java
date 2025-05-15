package com.dao.cue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.cue.cueContoller;
import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_WEPE_link_sus_pershistory;
import com.models.CUE_TB_WEPE_link_sus_perstransweapon;
import com.models.CUE_TB_WEPE_link_sus_transhistory;
import com.models.CUE_TB_WEPE_link_sus_weaphistory;
import com.models.UserLogin;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class WEPELinkSusDAOImpl implements WEPELinkSusDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	Posting_Out_Controller post = new Posting_Out_Controller();
	NotificationController notification = new NotificationController();
	
	public List<Map<String, Object>> getwepepersModiDetailsReport(String we_pe_no, String tbl) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";

			sql = String.format(
					"select distinct modification from %s where we_pe_no=? and modification is not null and status='1' order by modification",
					tbl);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, we_pe_no);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				rows.add(columns);
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
		return rows;
	}

	public List<Map<String, Object>> getwepepersModiDetailsFootnotesReport(String we_pe_no) {
		{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (we_pe_no != "") {
					qry += " and tbl.we_pe_no=?";

				}
				q += "SELECT tbl.we_pe_no,appt, tbl.rank,tbl.condition,tbl.label AS rank_cat,tbl.amt_inc_dec, t_domain_value.label AS person_cat,tbl.id FROM (select tbl_appt.*,cue_tb_psg_rank_app_master.description as rank from  "
						+ "( SELECT DISTINCT cue_tb_miso_wepe_pers_footnotes.we_pe_no,cue_tb_psg_rank_app_master.description,cue_tb_miso_wepe_pers_footnotes.rank as rank_code,cue_tb_psg_rank_app_master.description as appt,t_domain_value.label,cue_tb_miso_wepe_pers_footnotes.condition,"
						+ " cue_tb_miso_wepe_pers_footnotes.amt_inc_dec,cue_tb_miso_wepe_pers_footnotes.category_of_personnel,cue_tb_miso_wepe_pers_footnotes.id FROM cue_tb_miso_wepe_pers_footnotes inner join  t_domain_value on t_domain_value.codevalue::text = cue_tb_miso_wepe_pers_footnotes.rank_cat::text and t_domain_value.domainid::text = 'RANKCATEGORY'::text and cue_tb_miso_wepe_pers_footnotes.status='1' "
						+ " inner join cue_tb_psg_rank_app_master on cue_tb_psg_rank_app_master.code=cue_tb_miso_wepe_pers_footnotes.appt_trade )tbl_appt inner join  cue_tb_psg_rank_app_master on cue_tb_psg_rank_app_master.code=tbl_appt.rank_code) tbl,t_domain_value  "
						+ "WHERE   tbl.category_of_personnel::text = t_domain_value.codevalue::text and t_domain_value.domainid::text = 'PERSON_CAT'::text "
						+ qry + " order by tbl.we_pe_no ";

				stmt = conn.prepareStatement(q);
				int j = 1;
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
					rows.add(columns);
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
			return rows;
		}
	}

	public List<Map<String, Object>> getwepetransModiDetailsFootnotesReport(String we_pe_no) {
	{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";
				if (we_pe_no != "") {
					qry += " cue_tb_miso_wepe_trans_footnotes.we_pe_no=?";
				}
				q += "select tb_tms_mct_main_master.mct_nomen as std_nomclature,we_pe_no,mct_no,in_lieu_mct,condition,amt_inc_dec,actual_inlieu_auth as inlieu_amt,cue_tb_miso_wepe_trans_footnotes.id,type_of_footnote  from cue_tb_miso_wepe_trans_footnotes "
						+ "inner join tb_tms_mct_main_master on tb_tms_mct_main_master.mct_main_id::text=cue_tb_miso_wepe_trans_footnotes.mct_no "
						+ " where   " + qry + " and cue_tb_miso_wepe_trans_footnotes.status='1'";
				stmt = conn.prepareStatement(q);
				int j = 1;

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
					rows.add(columns);
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
			return rows;
		}
	}

	public List<Map<String, Object>> getwepeweaponModiDetailsFootnotesReport(String we_pe_no) {
		{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (we_pe_no != "") {
					qry += " f.we_pe_no=?";

				}
				q += " select item_type,prf_group,f.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,f.amt_inc_dec,f.condition,f.we_pe_no,f.id from cue_tb_miso_wepe_weapon_footnotes f  "
						+ " left outer join tb_miso_orbat_code l on f.location=l.code and l.status_record='1'  left outer join tb_miso_orbat_unt_dtl b on f.formation=b.sus_no and b.status_sus_no='Active'  "
						+ " left outer join tb_miso_orbat_unt_dtl c on f.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ " inner join cue_tb_miso_mms_item_mstr on cue_tb_miso_mms_item_mstr.item_code=f.item_seq_no and f.status='1' where "
						+ qry + " ";

				stmt = conn.prepareStatement(q);
				int j = 1;

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
					rows.add(columns);
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
			return rows;
		}
	}

	@SuppressWarnings("unchecked")
	public Boolean isSus_noExist(String sus_no) {
		String hql = "FROM CUE_TB_WEPE_link_sus_perstransweapon U where U.sus_no=:sus_no ";
		List<CUE_TB_WEPE_link_sus_perstransweapon> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("sus_no", sus_no);
			users = (List<CUE_TB_WEPE_link_sus_perstransweapon>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	public List<Map<String, Object>> getViewmoreModipersDetailsReport(String we_pe_no, String mod) {
		{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (we_pe_no != "") {
					qry += " and tbl.we_pe_no =?";
				}
				if (mod != "") {
					qry += " and modification =?";
				}

				q += " SELECT  tbl.we_pe_no,  appt, tbl.rank, tbl.label AS rank_cat,tbl.amt_inc_dec ,scenario,loc_for_unit,modification,tbl.id,t_domain_value.label AS person_cat  FROM (select concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,tbl_appt.*,cue_tb_psg_rank_app_master.description as rank from  "
						+ " (SELECT DISTINCT  cue_tb_miso_wepe_pers_mdfs.we_pe_no,location,formation,scenario_unit,scenario,modification,cue_tb_miso_wepe_pers_mdfs.id, cue_tb_miso_wepe_pers_mdfs.appt_trade,  cue_tb_miso_wepe_pers_mdfs.rank as rank_code,cue_tb_psg_rank_app_master.description as appt, t_domain_value.label,cue_tb_miso_wepe_pers_mdfs.amt_inc_dec,"
						+ " cue_tb_miso_wepe_pers_mdfs.cat_per FROM cue_tb_miso_wepe_pers_mdfs inner join  t_domain_value on t_domain_value.codevalue::text = cue_tb_miso_wepe_pers_mdfs.rank_cat::text and  t_domain_value.domainid::text = 'RANKCATEGORY'::text and cue_tb_miso_wepe_pers_mdfs.status='1'  inner join cue_tb_psg_rank_app_master "
						+ " on cue_tb_psg_rank_app_master.code=cue_tb_miso_wepe_pers_mdfs.appt_trade )tbl_appt inner join cue_tb_psg_rank_app_master on cue_tb_psg_rank_app_master.code=tbl_appt.rank_code"
						+ " left outer join tb_miso_orbat_code l on location=l.code and l.status_record='1' left outer join tb_miso_orbat_unt_dtl b on formation=b.sus_no and b.status_sus_no='Active'  "
						+ " left outer join tb_miso_orbat_unt_dtl c on scenario_unit=c.sus_no and c.status_sus_no='Active'  "
						+ " ) tbl,t_domain_value"
						+ " WHERE  tbl.cat_per::text = t_domain_value.codevalue::text and  t_domain_value.domainid::text = 'PERSON_CAT'::text "
						+ qry + " order by tbl.we_pe_no";

				stmt = conn.prepareStatement(q);
				int j = 1;

				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j++;
				}
				if (mod != "") {
					stmt.setString(j, mod);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					rows.add(columns);
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
			return rows;
		}
	}

	public List<Map<String, Object>> getViewmoreModiTransDetailsReport(String we_pe_no, String mod) {
		{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (we_pe_no != "") {
					qry += " m.we_pe_no =?";
				}
				if (mod != "") {
					qry += " and m.modification =?";
				}

				q += "select tb_tms_mct_main_master.mct_nomen as std_nomclature,m.we_pe_no,m.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,m.modification,m.amt_inc_dec,m.mct_no  from cue_tb_miso_wepe_transport_mdfs m "
						+ "  left outer join tb_miso_orbat_code l on m.location=l.code and l.status_record='1'  left outer join tb_miso_orbat_unt_dtl b on m.formation=b.sus_no and b.status_sus_no='Active'  "
						+ "left outer join tb_miso_orbat_unt_dtl c on m.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ " inner join tb_tms_mct_main_master on tb_tms_mct_main_master.mct_main_id::text=m.mct_no where "
						+ qry + " ";

				stmt = conn.prepareStatement(q);
				int j = 1;

				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j++;
				}
				if (mod != "") {
					stmt.setString(j, mod);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					rows.add(columns);
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
			return rows;
		}
	}

	public List<Map<String, Object>> getViewmoreModiWepDetailsReport(String we_pe_no, String mod) {
		{
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";

				if (we_pe_no != "") {
					qry += " m.we_pe_no =?";
				}
				if (mod != "") {
					qry += " and m.modification =?";
				}

				q += "select item_type,prf_group,m.scenario,concat(b.unit_name,c.unit_name,l.code_value) as loc_for_unit,m.we_pe_no,m.amt_inc_dec,m.modification,m.id from cue_tb_miso_wepe_weapons_mdfs m "
						+ "left outer join tb_miso_orbat_code l on m.location=l.code and l.status_record='1'  left outer join tb_miso_orbat_unt_dtl b on m.formation=b.sus_no and b.status_sus_no='Active'  "
						+ "left outer join tb_miso_orbat_unt_dtl c on m.scenario_unit=c.sus_no and c.status_sus_no='Active' "
						+ "inner join cue_tb_miso_mms_item_mstr on cue_tb_miso_mms_item_mstr.item_code=m.item_seq_no where "
						+ qry + " ";

				stmt = conn.prepareStatement(q);
				int j = 1;

				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j++;
				}
				if (mod != "") {
					stmt.setString(j, mod);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					rows.add(columns);
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
			return rows;
		}
	}
	
	public List<Map<String, Object>> getFootNotesPersDetails(String sus_no,String wepe_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select f.we_pe_no,rm.description,tf.rank,rm.description as appt,tf.amt_inc_dec,tf.condition\r\n" + 
					"from cue_tb_wepe_link_sus_pers_footnotes f\r\n" + 
					"inner join cue_tb_miso_wepe_pers_footnotes tf on tf.id = f.foot_fk\r\n" + 
					"inner join cue_tb_psg_rank_app_master rm on rm.code=tf.appt_trade\r\n" + 
					"where f.we_pe_no=? and f.sus_no=? and tf.status='1'";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, wepe_no);
			stmt.setString(2, sus_no);
		
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

	public List<Map<String, Object>> getSearchlinkWEPEPersReportDetail(String we_pe_no, String status,
			String unit_sus_no, String roleType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;

		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!status.equals("all")) {
				qry += " and (l.status_pers = '0' or l.status_pers = '2' or l.status_pers = '1')  ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and l.sus_no = ?";
			}
			if (we_pe_no != "") {
				qry += " and l.wepe_pers_no = ?";
			}
			q += "select l.id,l.sus_no,udtl.unit_name,l.wepe_pers_no, mdfs.mod as modification,l.status_pers,count(tf.*) as footnotes_count from cue_tb_wepe_link_sus_perstransweapon l "
					+ " left join ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_pers_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  "
					+ " left join cue_tb_wepe_link_sus_pers_footnotes tf on l.sus_no=tf.sus_no and l.wepe_pers_no = tf.we_pe_no "
					+ " where  (l.status_pers = '0' or l.status_pers = '2' or l.status_pers = '1') " 
					+  qry
					+ " group by 1,2,3,4,5,6 "
					+ " order by l.status_pers,l.sus_no ";
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!unit_sus_no.equals("")) {
				stmt.setString(j, unit_sus_no);
				j += 1;
			}
			if (we_pe_no != "") {
				stmt.setString(j, we_pe_no);
			}
			System.out.println("ss: "+stmt);
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
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ",'" + rs.getString("wepe_pers_no") + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				String f = "";

				if (rs.getObject(6).equals("1")) {
					f += appButton;
				} else {
					f += deleteButton;
					f += updateButton;
				}

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
		System.out.println("list" + list);
		return list;
	}

	public List<Map<String, Object>> getSearchlinkWEPEPersReportDetail1(String status_pers, String wepe_pers_no,
			String unit_sus_no, String unit_name, String roleType, String roleAccess, String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String sql = "";

		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (status_pers != "" && status_pers != null && status_pers != "null") {
				if (!status_pers.equals("all")) {
					qry += " and l.status_pers = ?";
				}
			}
			if (wepe_pers_no != "" && wepe_pers_no != "null" && wepe_pers_no != null) {
				qry += " and l.wepe_pers_no = ?";
			}
			if (unit_sus_no != "") {
				qry += " and l.sus_no = ?";
			}
			if (qry != "") {
				qry = " where " + qry.substring(4, qry.length());
			}
			
			if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
				sql +=" select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_pers_no, mdfs.mod as modifi,\r\n"
						+ "	l.status_pers  ,\r\n"
						+ "l.created_by as cr_by,l.created_on as cr_on,\r\n"
						+ "l.modified_by as modi_by,l.modified_on as modi_on\r\n"
						+ "	from cue_tb_wepe_link_sus_perstransweapon l \r\n"
						+ "	left join  ( select sus_no, string_agg(modification,',') as mod \r\n"
						+ "	from cue_tb_wepe_link_sus_pers_mdfs group by sus_no) mdfs \r\n"
						+ "	on mdfs.sus_no=l.sus_no  \r\n"
						+ "	left join  tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active' \r\n"
						+ "	left join tb_miso_orbat_line_dte l1 on l1.arm_code=udtl.arm_code  " + ""
						 + qry + "  and l1.line_dte_sus =? order by l.status_pers,l.sus_no\r\n" ;
						
				
			}else
			{

			sql += " select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_pers_no, mdfs.mod as modifi,l.status_pers  ,\r\n"
					+ "l.created_by as cr_by,l.created_on as cr_on,\r\n"
					+ "l.modified_by as modi_by,l.modified_on as modi_on from cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_pers_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join "
					+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
					+ qry + " order by l.status_pers,l.sus_no ";
			}
			
			stmt = conn.prepareStatement(sql);
			int j = 1;
			
			if (status_pers != "" && status_pers != null && status_pers != "null") {
				if (!status_pers.equals("all")) {
					stmt.setString(j, status_pers);
					j += 1;
				}
			}

			if (wepe_pers_no != "" && wepe_pers_no != "null" && wepe_pers_no != null) {
				stmt.setString(j, wepe_pers_no);
				j += 1;
			}
			if (unit_sus_no != "") {
				stmt.setString(j, unit_sus_no);
				j += 1;
			}
			if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
				stmt.setString(j, roleSusNo);
				
//				stmt.setString(j, roleArmCode);
//				j += 1;
			}
			ResultSet rs = stmt.executeQuery();
System.out.println("cue: "+ stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' " + Approved + " title='Approve Data'></i>";

				String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";

				String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4) + "');$('#rrr"
						+ rs.getObject(1) + "').attr('data-target','#rejectModal')}else{ return false;}\"";
				String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' " + Reject
						+ " title='Reject Data' data-toggle='modal'  ></i>";

				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				String DelinkApprve = "onclick=\"  if (confirm('Are you sure you want to Approve Delink?') ){DelinkApprve("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkApprve = "<i class='action_icons action_approve' " + DelinkApprve
						+ " title='Approve Delink'></i>";

				String DelinkReject = "onclick=\"  if (confirm('Are you sure you want to Reject Delink?') ){DelinkReject("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkReject = "<i class='action_icons action_reject' " + DelinkReject
						+ " title='Reject Delink'></i>";
				String LogButton = cueContoller.Get_button_info(metaData,rs);
                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
				String f = "";

				if (status_pers.equals("0")) {
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
					if (roleType.equals("DEO")) {
						f += deleteButton;
						f += updateButton;
						f += LogButton;
						}

				} else if (status_pers.equals("1")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += rejectButton;
						f += LogButton1;
					}
					if (roleType.equals("DEO")) {
						f += appButton1;
						f += LogButton1;
						}

				} else if (status_pers.equals("2")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += appButton;
					}
					if (roleType.equals("DEO") || roleType.equals("ALL")) {

						f += deleteButton;
						f += updateButton;
					}
				} else if (status_pers.equals("3")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += delinkApprve;
						f += delinkReject;
					}
				}
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

	public List<Map<String, Object>> getSearchlinkWEPETransReportDetail(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql += " select l.id,l.sus_no,udtl.unit_name,l.wepe_trans_no, mdfs.mod,l.status_trans from cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_trans_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join "
					+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
					+ qry + " order by l.status_trans,l.sus_no ";

			PreparedStatement stmt = conn.prepareStatement(sql);
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

	public List<Map<String, Object>> getSearchlinkWEPEWepReportDetail(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql += " select l.id,l.sus_no,udtl.unit_name,l.wepe_weapon_no, mdfs.mod,l.status_weap from cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_weapon_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join "
					+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
					+ qry + " order by l.status_weap,l.sus_no ";

			PreparedStatement stmt = conn.prepareStatement(sql);
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

	public String setApprovedWepeLink(int appid, String sus, String wepe, String username, String creadtedate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdatem = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers = :status where c.id = :id";
		int appm = session.createQuery(hqlUpdatem).setString("status", "1").setInteger("id", appid).executeUpdate();

		

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query qry = session1.createQuery(
		"select version_no FROM CUE_TB_WEPE_link_sus_pershistory WHERE sus_no =:sus order by version_no desc");
		qry.setParameter("sus", sus);
		qry.setMaxResults(1);
		
		Integer versionNo = (Integer) qry.uniqueResult();
		int version;
		if (versionNo != null) {
		    version = versionNo + 1;
		} else {
		    // Handle the case where no records are found
		    version = 1; // Or any default value you prefer
		}
		Query qry1 = session1.createQuery(
				"select MAX(sus_version) FROM Miso_Orbat_Unt_Dtl WHERE sus_no =:sus and status_sus_no='Active' ");
				qry1.setParameter("sus", sus);
				Integer version_orbat = (Integer) qry1.uniqueResult();
				
		
				CUE_TB_WEPE_link_sus_pershistory his=new CUE_TB_WEPE_link_sus_pershistory();
		
		
		his.setSus_no(sus);
		his.setWe_pe_no(wepe);
		his.setVersion_no(version);
		his.setOrbat_version(version_orbat);
		his.setCreated_on(creadtedate);
		his.setCreated_by(username);
		
		session1.save(his);
		tx1.commit();
		session1.close();
		
		
		
		String hqlUpdate1 = "update CUE_TB_wepe_link_sus_pers_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_pers_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();
		
		List<UserLogin> userlist = post.getPostIN_outuseridlist(sus);	
		
		//UserLogin  us_id= getnotuserid(notit.getSponsor_dire());
      
		
         String user_id = "";
      		for (int i = 0; i < userlist.size(); i++) {
      			if(i==0) {
      				user_id += 	String.valueOf(userlist.get(i).getUserId());
      			}
      			
      			else {
      				user_id += ","+String.valueOf(userlist.get(i).getUserId());;
      			}
      					
				}
		
      		if (user_id!="") {
      		  					
		     String title = "Link Sus No" ;
             String description = "Unit Auth for pers has been updated. For details, Please view standard entitlement for pers under modification entitlement module." ;
		     Boolean d = notification.sendNotification(title, description,user_id,"");
      		}
      		

		tx.commit();
		session.close();

		if (appm > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Personnel Approved Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Personnel Approved not Successfully";
		}
	}

	public String setRejectWepeLink(int appid, String sus, String wepe) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).executeUpdate();

		String hqlUpdate1 = "update CUE_TB_wepe_link_sus_pers_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_pers_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no  and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		tx.commit();
		session.close();

		if (app > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Personnel Rejected Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Personnel Rejected not Successfully";
		}
	}

	public String setDeleteWepeLink(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_wepe_link_sus_pers_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_wepe_link_sus_pers_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();
		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_pers_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_pers_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();
		// ---------------------------------- main link table
		// -------------------------------------------------------------

		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_pers_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers='',c.wepe_pers_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();
		return "Deleted Sucessfully";
	}

	public String setDelinkapprvWepeLink(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_wepe_link_sus_pers_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_wepe_link_sus_pers_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();
		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_pers_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_pers_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();
		// ---------------------------------- main link table
		// -------------------------------------------------------------

		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_pers_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers='',c.wepe_pers_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();
		return "Delinked Sucessfully";

	}

	public String setDelinkrejectWepeLink(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_pers = '0' where c.id = :id";
		int rowCount1 = session.createQuery(hqlUpdate1).setInteger("id", appid).executeUpdate();
		String hqlUpdate3 = "update CUE_TB_wepe_link_sus_pers_mdfs c set c.status ='0' where c.id = :id";
		int rowCountmdfs = session.createQuery(hqlUpdate3).setInteger("id", appid).executeUpdate();
		String hqlUpdate4 = "update CUE_TB_WEPE_link_sus_pers_footnotes c set c.status ='0' where c.id = :id";
		int rowCountfoot = session.createQuery(hqlUpdate4).setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();

		return "Rejected delink Sucessfully";
	}

	public CUE_TB_WEPE_link_sus_perstransweapon getCUE_TB_MISO_WEPE_PERS_MDFSidLink(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_WEPE_link_sus_perstransweapon where id=:id");
		q.setParameter("id", id);
		CUE_TB_WEPE_link_sus_perstransweapon list = (CUE_TB_WEPE_link_sus_perstransweapon) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String setApprovedWepeLinkTrans(int appid, String sus, String wepe, String username, String creadtedate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdatem = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans = :status where c.id = :id";
		int appm = session.createQuery(hqlUpdatem).setString("status", "1").setInteger("id", appid).executeUpdate();
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query qry = session1.createQuery(
		"select version_no FROM CUE_TB_WEPE_link_sus_transhistory WHERE sus_no =:sus order by version_no desc");
		qry.setParameter("sus", sus);
		qry.setMaxResults(1);
		
		Integer versionNo = (Integer) qry.uniqueResult();
		int version;
		if (versionNo != null) {
		    version = versionNo + 1;
		} else {
		    // Handle the case where no records are found
		    version = 1; // Or any default value you prefer
		}
		Query qry1 = session1.createQuery(
				"select max(sus_version) FROM Miso_Orbat_Unt_Dtl WHERE sus_no =:sus and status_sus_no='Active' ");
				qry1.setParameter("sus", sus);
				Integer version_orbat = (Integer) qry1.uniqueResult();
				
		
		CUE_TB_WEPE_link_sus_transhistory his=new CUE_TB_WEPE_link_sus_transhistory();
		
		
		his.setSus_no(sus);
		his.setWe_pe_no(wepe);
		his.setVersion_no(version);
		his.setOrbat_version(version_orbat);
		his.setCreated_on(creadtedate);
		his.setCreated_by(username);
		
		session1.save(his);
		tx1.commit();
		session1.close();
		
		
		
		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_trans_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_trans_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();
		
List<UserLogin> userlist = post.getPostIN_outuseridlist(sus);	
		
		//UserLogin  us_id= getnotuserid(notit.getSponsor_dire());
      
		
         String user_id = "";
      		for (int i = 0; i < userlist.size(); i++) {
      			if(i==0) {
      				user_id += 	String.valueOf(userlist.get(i).getUserId());
      			}
      			
      			else {
      				user_id += ","+String.valueOf(userlist.get(i).getUserId());;
      			}
      					
				}
		
      		if (user_id!="") {
      		  					
		     String title = "Link Sus No" ;
//             String description = "Your Authorisation Is Updated Please View UE In Standard Entitlement For Transport" ;
		     String description = "Unit Auth for trans has been updated. For details, Please view standard entitlement for trans under modification entitlement module." ;
		     
		     Boolean d = notification.sendNotification(title, description,user_id,"");
      		}
      		

		tx.commit();
		session.close();

		if (appm > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Transport Approved Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Transport Approved not Successfully";
		}
	}

	public String setRejectWepeLinkTrans(int appid, String sus, String wepe) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).executeUpdate();

		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_trans_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_trans_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		tx.commit();
		session.close();

		if (app > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Transport Rejected Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Transport Rejected not Successfully";
		}
	}

	public String setDeleteWepeLinkTrans(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_WEPE_link_sus_trans_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_WEPE_link_sus_trans_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();

		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_trans_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_trans_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();

		// ---------------------------------- main link table
		// -------------------------------------------------------------
		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_trans_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans='',c.wepe_trans_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();

		return "Deleted Successfully";
	}

	public String setDelinkapprvWepeLinkTrans(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_WEPE_link_sus_trans_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_WEPE_link_sus_trans_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();

		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_trans_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_trans_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();

		// ---------------------------------- main link table
		// -------------------------------------------------------------
		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_trans_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans='',c.wepe_trans_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();
		return "Delinked Sucessfully";
	}

	public String setDelinkrejectWepeLinkTrans(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_trans = '0' where c.id = :id";
		int rowCount1 = session.createQuery(hqlUpdate1).setInteger("id", appid).executeUpdate();
		String hqlUpdate3 = "update CUE_TB_WEPE_link_sus_trans_mdfs c set c.status ='0' where c.id = :id";
		int rowCountmdfs = session.createQuery(hqlUpdate3).setInteger("id", appid).executeUpdate();
		String hqlUpdate4 = "update CUE_TB_WEPE_link_sus_trans_footnotes c set c.status ='0' where c.id = :id";
		int rowCountfoot = session.createQuery(hqlUpdate4).setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();

		return "Rejected delink sucessfully";
	}

	public String setApprovedWepeLinkWep(int appid, String sus, String wepe, String username, String creadtedate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdatem = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap = :status where c.id = :id";
		int appm = session.createQuery(hqlUpdatem).setString("status", "1").setInteger("id", appid).executeUpdate();

		
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query qry = session1.createQuery(
		"select version_no FROM CUE_TB_WEPE_link_sus_weaphistory WHERE sus_no =:sus order by version_no desc");
		qry.setParameter("sus", sus);
		qry.setMaxResults(1);
		
		Integer versionNo = (Integer) qry.uniqueResult();
		int version;
		if (versionNo != null) {
		    version = versionNo + 1;
		} else {
		    // Handle the case where no records are found
		    version = 1; // Or any default value you prefer
		}
		Query qry1 = session1.createQuery(
				"select max(sus_version) FROM Miso_Orbat_Unt_Dtl WHERE sus_no =:sus and status_sus_no='Active' ");
				qry1.setParameter("sus", sus);
				Integer version_orbat = (Integer) qry1.uniqueResult();
				
		
		CUE_TB_WEPE_link_sus_weaphistory his=new CUE_TB_WEPE_link_sus_weaphistory();
		
		
		his.setSus_no(sus);
		his.setWe_pe_no(wepe);
		his.setVersion_no(version);
		his.setOrbat_version(version_orbat);
		his.setCreated_on(creadtedate);
		his.setCreated_by(username);
		
		session1.save(his);
		tx1.commit();
		session1.close();
		
		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_weapon_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_weapon_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "1").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		
		List<UserLogin> userlist = post.getPostIN_outuseridlist(sus);	
		
		//UserLogin  us_id= getnotuserid(notit.getSponsor_dire());
      
		
         String user_id = "";
      		for (int i = 0; i < userlist.size(); i++) {
      			if(i==0) {
      				user_id += 	String.valueOf(userlist.get(i).getUserId());
      			}
      			
      			else {
      				user_id += ","+String.valueOf(userlist.get(i).getUserId());;
      			}
      					
				}
		
      		if (user_id!="") {
      		  					
		     String title = "Link Sus No" ;
             String description = "Unit Auth for wpn/eqpt has been updated. For details, Please view standard entitlement for wpn under modification entitlement module." ;
		     Boolean d = notification.sendNotification(title, description,user_id,"");
      		}
		
		tx.commit();
		session.close();

		if (appm > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Weapon Approved Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Weapon Approved not Successfully";
		}
	}
	private cue_wepe getlastlinkno(int appid) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		cue_wepe id = (cue_wepe) sessionHQL

				.get(cue_wepe.class, appid);

		sessionHQL.getTransaction().commit();

		sessionHQL.close();

		return id;

	}
	public String setRejectWepeLinkWep(int appid, String sus, String wepe) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).executeUpdate();

		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_weapon_mdfs c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app1 = session.createQuery(hqlUpdate1).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		String hqlUpdate2 = "update CUE_TB_WEPE_link_sus_weapon_footnotes c set c.status = :status where c.sus_no = :sus and c.we_pe_no = :wepe_no and c.sus_no is not null and c.we_pe_no is not null";
		int app2 = session.createQuery(hqlUpdate2).setString("status", "2").setString("sus", sus)
				.setString("wepe_no", wepe).executeUpdate();

		tx.commit();
		session.close();

		if (app > 0 || app1 > 0 || app2 > 0) {
			return "Link SUS No. with WE/PE No. Weapon Rejected Successfully";
		} else {
			return "Link SUS No. with WE/PE No. Weapon Rejected not Successfully";
		}
	}

	public String setDeleteWepeLinkWeap(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_WEPE_link_sus_weapon_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_WEPE_link_sus_weapon_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();
		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_weapon_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_weapon_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();
		// ---------------------------------- main link table
		// -------------------------------------------------------------

		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_weapon_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap='',c.wepe_weapon_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();
		return "Deleted Successfully";
	}

	public String setDelinkapprvWepeLinkWeap(int appid, String sus, String wepe) {

		// ------------------------ Modification
		// ----------------------------------------------------------------------------

		String hqlmdfs = "select distinct id from CUE_TB_WEPE_link_sus_weapon_mdfs where sus_no=:sus and we_pe_no=:wepe ";
		Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
		Transaction txmdfs = sessionmdfs.beginTransaction();
		Query querymdfs = sessionmdfs.createQuery(hqlmdfs);
		querymdfs.setParameter("sus", sus);
		querymdfs.setParameter("wepe", wepe);
		List<Integer> lmdfs = new ArrayList<Integer>();
		lmdfs = (List<Integer>) querymdfs.list();
		Object[] objectmdfs = lmdfs.toArray();

		if (objectmdfs.length != 0) {
			for (Object obj : objectmdfs) {
				String hql2 = "delete from CUE_TB_WEPE_link_sus_weapon_mdfs where sus_no=:sus and id=:id";
				Session session2 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx2 = session2.beginTransaction();
				Query que2 = session2.createQuery(hql2);
				que2.setParameter("id", obj);
				que2.setParameter("sus", sus);
				int rowmdfs = que2.executeUpdate();
				tx2.commit();
				session2.close();
			}
		}
		txmdfs.commit();
		sessionmdfs.close();
		// ------------------------ Footnote
		// ----------------------------------------------------------------------------

		String hqlfnote = "select id from CUE_TB_WEPE_link_sus_weapon_footnotes where sus_no=:sus and we_pe_no=:wepe";
		Session sessionfnote = HibernateUtil.getSessionFactory().openSession();
		Transaction txfnote = sessionfnote.beginTransaction();
		Query queryfnote = sessionfnote.createQuery(hqlfnote);
		queryfnote.setParameter("sus", sus);
		queryfnote.setParameter("wepe", wepe);
		List<Integer> lfnote = new ArrayList<Integer>();
		lfnote = (List<Integer>) queryfnote.list();
		Object[] objectfnote = lfnote.toArray();

		if (objectfnote.length != 0) {
			for (Object obj : objectfnote) {
				String hql3 = "delete from CUE_TB_WEPE_link_sus_weapon_footnotes where sus_no=:sus and id=:id";
				Session session3 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx3 = session3.beginTransaction();
				Query que3 = session3.createQuery(hql3);
				que3.setParameter("id", obj);
				que3.setParameter("sus", sus);
				int rowfnote = que3.executeUpdate();
				tx3.commit();
				session3.close();
			}
		}
		txfnote.commit();
		sessionfnote.close();
		// ---------------------------------- main link table
		// -------------------------------------------------------------

		String hqlmain = "select id from CUE_TB_WEPE_link_sus_perstransweapon where sus_no=:sus and wepe_weapon_no=:wepe";
		Session sessionmain = HibernateUtil.getSessionFactory().openSession();
		Transaction txmain = sessionmain.beginTransaction();
		Query querymain = sessionmain.createQuery(hqlmain);
		querymain.setParameter("sus", sus);
		querymain.setParameter("wepe", wepe);
		List<Integer> lmain = new ArrayList<Integer>();
		lmain = (List<Integer>) querymain.list();
		Object[] objectmain = lmain.toArray();

		if (objectmain.length != 0) {
			for (Object obj : objectmain) {
				String hql4 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap='',c.wepe_weapon_no='' where c.sus_no=:sus and c.id=:id";
				Session session4 = HibernateUtil.getSessionFactory().openSession();
				Transaction tx4 = session4.beginTransaction();
				Query que4 = session4.createQuery(hql4);
				que4.setParameter("id", obj);
				que4.setParameter("sus", sus);
				int rowmain = que4.executeUpdate();
				tx4.commit();
				session4.close();

			}
		}
		txmain.commit();
		sessionmain.close();

		return "Delinked Sucessfully";
	}

	public String setDelinkrejectWepeLinkWeap(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate1 = "update CUE_TB_WEPE_link_sus_perstransweapon c set c.status_weap = '0' where c.id = :id";
		int rowCount1 = session.createQuery(hqlUpdate1).setInteger("id", appid).executeUpdate();
		String hqlUpdate3 = "update CUE_TB_WEPE_link_sus_weapon_mdfs c set c.status ='0' where c.id = :id";
		int rowCountmdfs = session.createQuery(hqlUpdate3).setInteger("id", appid).executeUpdate();
		String hqlUpdate4 = "update CUE_TB_WEPE_link_sus_weapon_footnotes c set c.status ='0' where c.id = :id";
		int rowCountfoot = session.createQuery(hqlUpdate4).setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		return "Rejected delink sucessfully";
	}

	public List<String> getLink_sus_permdf(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_wepe_link_sus_pers_mdfs where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getLink_sus_perfoot(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_WEPE_link_sus_pers_footnotes where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getLink_sus_transmdf(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_WEPE_link_sus_trans_mdfs where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getLink_sus_transfoot(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_WEPE_link_sus_trans_footnotes where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getLink_sus_wepmdf(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_WEPE_link_sus_weapon_mdfs where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getLink_sus_wepfoot(String sus_no, String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery(" from CUE_TB_WEPE_link_sus_weapon_footnotes where sus_no=:sus_no and we_pe_no=:we_pe_no");
		q.setParameter("sus_no", sus_no);
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	///////// zalak//////////
	public List<Map<String, Object>> getSearchlinkWEPETransReportDetail2(String wepe_trans_no, String unit_sus_no_trans,
			String status_trans, String roleType,String roleAccess,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String qry = "";
			if (!status_trans.equals("all")) {
				qry += " and (l.status_trans = '0' or l.status_trans = '2' or l.status_trans = '1')  ";
			}

			if (wepe_trans_no != "") {
				qry += " and l.wepe_trans_no = ?";

			}
			if (unit_sus_no_trans != "") {
				qry += " and l.sus_no = ?";

			}
			if (qry != "")
				qry = " where " + qry.substring(4, qry.length());

			sql += " select l.id,l.sus_no,udtl.unit_name,l.wepe_trans_no, mdfs.mod as modifi,l.status_trans,count(tf.*) as footnotes_count from cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " (select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_trans_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active' " 
					+ " left join cue_tb_wepe_link_sus_trans_footnotes tf on l.sus_no=tf.sus_no and l.wepe_trans_no = tf.we_pe_no "
					+ 	qry + " group by 1,2,3,4,5,6\r\n" 
					+ "order by l.status_trans,l.sus_no ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int j = 1;
			if (wepe_trans_no != "") {
				stmt.setString(j, wepe_trans_no);
				j += 1;
			}
			if (unit_sus_no_trans != "") {
				stmt.setString(j, unit_sus_no_trans);
			}
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String appButton = "<i class='action_icons action_approved' title='Update Data'></i>";

				String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData(" + rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String editdata = "<i class='action_icons action_update' " + editData + " title='Update Data'></i>";

				String deleteData = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData(" + rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4) + "')}else{ return false;}\"";
				String deletedata = "<i class='action_icons action_delete' " + deleteData + " title='Delete Data'></i>";

				String f = "";
				if (rs.getObject(6).equals("1")) {
					f += appButton;
				} else {
					f += editdata;
					f += deletedata;
				}
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
				} catch (SQLException e) {	}
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> getFootNotesTransDetails(String sus_no,String wepe_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select t.mct_nomen as std_nomclature,tf.we_pe_no,tf.mct_no,tf.amt_inc_dec,tf.condition \r\n" + 
					"from cue_tb_miso_wepe_trans_footnotes tf\r\n" + 
					"	inner join cue_tb_wepe_link_sus_trans_footnotes lf on tf.id = lf.fk_trans_foot\r\n" + 
					"	inner join tb_tms_mct_main_master t on t.mct_main_id::text=tf.mct_no \r\n" + 
					"	where tf.we_pe_no=? and lf.sus_no=? and\r\n" + 
					"	tf.status='1'";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, wepe_no);
			stmt.setString(2, sus_no);
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

	public List<Map<String, Object>> getSearchlinkWEPETransReportDetail1(String status_trans, String wepe_trans_no,String unit_sus_no_trans, String roleType,String roleAccess,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			String qry = "";

			if (status_trans != "" && status_trans != null && status_trans != "null") {
				if (!status_trans.equals("all")) {
					qry += " and l.status_trans = ?";
				}
			}
			if (wepe_trans_no != "") {
				qry += " and l.wepe_trans_no = ?";
			}
			if (unit_sus_no_trans != "") {
				qry += " and l.sus_no = ?";
			}
			if (qry != "") {
				qry = " where " + qry.substring(4, qry.length());
			}
			if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
				sql +=" select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_trans_no, mdfs.mod as modifi,l.status_trans ,\r\n"
						+ "l.created_by_trans as cr_by,l.created_on_trans as cr_on,\r\n"
						+ "l.modified_by_trans as modi_by,l.modified_on_trans as modi_on \r\n"
						+ "	from  cue_tb_wepe_link_sus_perstransweapon l\r\n"
						+ "	left join  ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_trans_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no \r\n"
						+ "	left join  tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'   \r\n"
						+ "	left join tb_miso_orbat_line_dte l1 on l1.arm_code=udtl.arm_code\r\n"
						+qry+ "	and l1.line_dte_sus =? " + 
						" order by l.status_trans,l.sus_no\r\n" ;
						
				
			}else
			{

			sql += " select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_trans_no, mdfs.mod as modifi,l.status_trans ,\r\n"
					+ "l.created_by_trans as cr_by,l.created_on_trans as cr_on,\r\n"
					+ "l.modified_by_trans as modi_by,l.modified_on_trans as modi_on from  cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_trans_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join "
					+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
					+ qry + " order by l.status_trans,l.sus_no ";

			}
			stmt = conn.prepareStatement(sql);
			int j = 1;
			
			if (status_trans != "" && status_trans != null && status_trans != "null") {
				if (!status_trans.equals("all")) {
					stmt.setString(j, status_trans);
					j += 1;
				}
			}
			
			if (wepe_trans_no != "") {
				stmt.setString(j, wepe_trans_no);
				j += 1;
			}
			if (unit_sus_no_trans != "") {
				stmt.setString(j, unit_sus_no_trans);
				j += 1;
			}
			if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
				stmt.setString(j, roleSusNo);
				
//				stmt.setString(j, roleArmCode);
//				j += 1;
			}
			System.out.println("trans:" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String ApprovedTrans = "onclick=\"  if (confirm('Are you sure you want to approve?') ){ApprovedTrans("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' " + ApprovedTrans
						+ " title='Approve Data'></i>";

				String RejectTrans = "onclick=\"  if (confirm('Are you sure you want to reject?') ){RejectTrans("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4) + "');$('#rrr"
						+ rs.getObject(1) + "').attr('data-target','#rejectModal')}else{ return false;}\"";
				String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
						+ RejectTrans + " title='Reject Data' data-toggle='modal'  ></i>";

				String DeleteTrans = "onclick=\"  if (confirm('Are you sure you want to delete?') ){DeleteTrans("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + DeleteTrans
						+ " title='Delete Data'></i>";

				String UpdateTrans = "onclick=\"  if (confirm('Are you sure you want to update?') ){UpdateTrans("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + UpdateTrans
						+ " title='Edit Data'></i>";

				String DelinkApprve = "onclick=\"  if (confirm('Are you sure you want to Approve Delink?') ){DelinkApprve("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkApprve = "<i class='action_icons action_approve' " + DelinkApprve
						+ " title='Approve Delink'></i>";

				String DelinkReject = "onclick=\"  if (confirm('Are you sure you want to Reject Delink?') ){DelinkReject("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkReject = "<i class='action_icons action_reject' " + DelinkReject
						+ " title='Reject Delink'></i>";
				String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
				
				String LogButton = cueContoller.Get_button_info(metaData,rs);
                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
				String f = "";
				if (status_trans.equals("0")) {
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
					if (roleType.equals("DEO")) {
						f += deleteButton;
						f += updateButton;
						f += LogButton;
					}

				} else if (status_trans.equals("1")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += rejectButton;
						f += LogButton1;
					}
					if (roleType.equals("DEO")) {
						f += appButton1;
						f += LogButton1;
					}

				} else if (status_trans.equals("2")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += appButton;
					}
					if (roleType.equals("DEO") || roleType.equals("ALL")) {

						f += deleteButton;
						f += updateButton;
					}
				} else if (status_trans.equals("3")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += delinkApprve;
						f += delinkReject;
					}
				}
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

	public List<Map<String, Object>> getSearchlinkWEPEWepReportDetail2(String wepe_weapon_no, String unit_sus_no_wep,
			String status_weap, String roleType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String qry = "";
			if (!status_weap.equals("all")) {
				qry += " and (l.status_weap = '0' or l.status_weap = '2' or l.status_weap = '1')  ";
			}
			if (wepe_weapon_no != "") {
				qry += " and l.wepe_weapon_no = ?";
			}
			if (unit_sus_no_wep != "") {
				qry += " and l.sus_no = ?";
			}
			if (qry != "")
				qry = " where " + qry.substring(4, qry.length());

			sql += " select l.id,l.sus_no,udtl.unit_name,l.wepe_weapon_no, mdfs.mod as modifi,l.status_weap from cue_tb_wepe_link_sus_perstransweapon l"
					+ " left join "
					+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_weapon_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
					+ " left join "
					+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
					+ qry + " order by l.status_weap,l.sus_no ";

			stmt = conn.prepareStatement(sql);
			int j = 1;
			if (wepe_weapon_no != "") {
				stmt.setString(j, wepe_weapon_no);
				j += 1;

			}
			if (unit_sus_no_wep != "") {
				stmt.setString(j, unit_sus_no_wep);

			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String appButton = "<i class='action_icons action_approved' title='Approve Data'></i>";

				String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String editdata = "<i class='action_icons action_update' " + editData + " title='Approve Data'></i>";

				String deleteData = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String deletedata = "<i class='action_icons action_delete' " + deleteData
						+ " title='Delete Data' ></i>";

				String f = "";
				if (rs.getObject(6).equals("1")) {
					f += appButton;
				} else {
					f += editdata;
					f += deletedata;
				}
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

	public List<Map<String, Object>> getSearchlinkWEPEWepReportDetail1(String status, String wepe_wep_no,
			String unit_sus_no_wep, String roleType,String roleAccess,String roleArmCode,String roleSusNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			String qry = "";
			String qry1 ="";
			if (status != "" && status != null && status != "null") {
				if (!status.equals("all")) {
					qry += " and l.status_weap = ?";
				}
			}
			if (wepe_wep_no != "") {
				qry += " and l.wepe_weapon_no = ?";
			}
			if (unit_sus_no_wep != "") {
				qry += " and l.sus_no = ?";
			}
			if (qry != "") {
				qry = " where " + qry.substring(4, qry.length());
			}

			
if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
	sql +=" select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_weapon_no, \r\n"
			+ " mdfs.mod as modifi,\r\n"
			+ "	 l.status_weap,\r\n"
			+ "l.created_by_wepon as cr_by,l.created_on_wepon as cr_on,\r\n"
			+ "	l.modified_by_weap as modi_by,l.modified_on_weap as modi_on  \r\n"
			+ " from cue_tb_wepe_link_sus_perstransweapon l \r\n"
			+ "left join  ( select sus_no, string_agg(modification,',') as mod\r\n"
			+ "			from cue_tb_wepe_link_sus_weapon_mdfs group by sus_no) mdfs \r\n"
			+ "			on mdfs.sus_no=l.sus_no  \r\n"
			+ "			 left join  tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no \r\n"
			+ "			left join tb_miso_orbat_line_dte l1 on l1.arm_code=udtl.arm_code  " + ""
			 + qry + " and l1.line_dte_sus =? order by l.status_weap,l.sus_no\r\n" ;
			
	
}else
{
	sql += " select distinct l.id,l.sus_no,udtl.unit_name,l.wepe_weapon_no, mdfs.mod as modifi,l.status_weap, l.created_by_wepon as cr_by,l.created_on_wepon as cr_on,\r\n"
			+ "	l.modified_by_weap as modi_by,l.modified_on_weap as modi_on from cue_tb_wepe_link_sus_perstransweapon l"
			+ " left join "
			+ " ( select sus_no, string_agg(modification,',') as mod from cue_tb_wepe_link_sus_weapon_mdfs group by sus_no) mdfs on mdfs.sus_no=l.sus_no "
			+ " left join "
			+ " tb_miso_orbat_unt_dtl udtl on l.sus_no=udtl.sus_no and udtl.status_sus_no='Active'  " + " "
			+ qry + " order by l.status_weap,l.sus_no ";
}
			
			
			stmt = conn.prepareStatement(sql);

			int j = 1;
			
			if (status != "" && status != null && status != "null") {
				if (!status.equals("all")) {
					stmt.setString(j, status);
					j += 1;
				}
			}
			if (wepe_wep_no != "") {
				stmt.setString(j, wepe_wep_no);
				j += 1;
			}
			if (unit_sus_no_wep != "") {
				stmt.setString(j, unit_sus_no_wep);
				j += 1;
			}
			if (roleAccess.equals("Line_dte")|| roleAccess == ("Line_dte") ) {
				stmt.setString(j, roleSusNo);
				
//				stmt.setString(j, roleArmCode);
//				j += 1;
			}
			System.out.println(stmt+"====");
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String LogButton = cueContoller.Get_button_info(metaData,rs);
                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
				String ApprovedWep = "onclick=\"  if (confirm('Are you sure you want to approve?') ){ApprovedWep("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' " + ApprovedWep
						+ " title='Approve Data'></i>";

				String RejectWep = "onclick=\"  if (confirm('Are you sure you want to reject?') ){RejectWep("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4) + "');$('#rrr"
						+ rs.getObject(1) + "').attr('data-target','#rejectModal')}else{ return false;}\"";
				String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
						+ RejectWep + " title='Reject Data' data-toggle='modal'  ></i>";

				String DeleteWep = "onclick=\"  if (confirm('Are you sure you want to delete?') ){DeleteWep("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + DeleteWep
						+ " title='Delete Data'></i>";

				String UpdateWep = "onclick=\"  if (confirm('Are you sure you want to update?') ){UpdateWep("
						+ rs.getObject(1) + ",'" + rs.getObject(4) + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + UpdateWep + " title='Edit Data'></i>";

				String DelinkApprve = "onclick=\"  if (confirm('Are you sure you want to Approve Delink?') ){DelinkApprve("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkApprve = "<i class='action_icons action_approve' " + DelinkApprve
						+ " title='Approve Delink'></i>";

				String DelinkReject = "onclick=\"  if (confirm('Are you sure you want to Reject Delink?') ){DelinkReject("
						+ rs.getObject(1) + ",'" + rs.getObject(2) + "','" + rs.getObject(4)
						+ "')}else{ return false;}\"";
				String delinkReject = "<i class='action_icons action_reject' " + DelinkReject
						+ " title='Reject Delink'></i>";

				String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";

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
					if (roleType.equals("DEO")) {
						f += deleteButton;
						f += updateButton;
						f += LogButton;
						
					}

              if(roleAccess.equals("Line_dte") && roleType.equals("VIEW") ) {
  							f += appButton;
  							f += rejectButton;
  							f += deleteButton;
  							f += updateButton;
  							f += LogButton;
  						}

				} else if (status.equals("1")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += rejectButton;
					}
					if (roleType.equals("DEO")) {
						f += appButton1;
					}

				} else if (status.equals("2")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += appButton;
					}
					if (roleType.equals("DEO") || roleType.equals("ALL")) {

						f += deleteButton;
						f += updateButton;
					}
				} else if (status.equals("3")) {
					if (roleType.equals("APP") || roleType.equals("ALL")) {
						f += delinkApprve;
						f += delinkReject;
					}
				}

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

	public List<Map<String, Object>> getOrbatDetails(String unit_sus_no) {
		if (unit_sus_no == "") {
			return null;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String qry = "";

			if (unit_sus_no != "") {
				qry += " a.sus_no = ?";
			}

			String query = "select a.sus_no,a.unit_name,\r\n"
					+ "	e.code_value AS location,d.arm_desc,ab.cmd_name,\r\n"
					+ "	ac.coprs_name,ad.div_name,ae.bde_name\r\n" + "from tb_miso_orbat_unt_dtl a\r\n"
					+ "LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
					+ "LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
					+ "LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4)  = ae.bde_code::text\r\n"
					+ "where " + qry + " and status_sus_no ='Active'";

			PreparedStatement stmt = conn.prepareStatement(query);

			if (unit_sus_no != "") {
				stmt.setString(1, unit_sus_no);
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
	public List<Map<String, Object>> getSearchlinkWEPEWepfootnoteReportDetail(String unit_sus_no_wep) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "";
			String qry = "";
			if (unit_sus_no_wep != "") {
				qry += " and l.sus_no = ?";
			} else {
				return null;
			}
			sql = " select mf.item_seq_no,im.item_type, md.auth_weapon,mf.amt_inc_dec, mf.condition from cue_tb_wepe_link_sus_perstransweapon l \r\n"
					+ "inner join cue_tb_wepe_link_sus_weapon_footnotes lf on l.sus_no=lf.sus_no and lf.we_pe_no = l.wepe_weapon_no\r\n"
					+ "inner join cue_tb_miso_wepe_weapon_footnotes mf on mf.id=lf.fk_weapon_foot and mf.we_pe_no=lf.we_pe_no\r\n"
					+ "inner join cue_tb_miso_wepe_weapon_det md on md.we_pe_no = mf.we_pe_no and mf.item_seq_no=md.item_seq_no\r\n"
					+ "inner join cue_tb_miso_mms_item_mstr im on im.item_code = mf.item_seq_no\r\n"
					+ "where (l.status_weap = '0' or l.status_weap = '1' or l.status_weap = '2') " + qry
					+ " order by l.sus_no,mf.item_seq_no";
			stmt = conn.prepareStatement(sql);
			int j = 1;
			if (unit_sus_no_wep != "") {
				stmt.setString(j, unit_sus_no_wep);
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
	
	public List<Map<String, Object>> getFootNotesWeaponDetails(String sus_no,String wepe_no){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select t.item_type as std_nomclature,tf.we_pe_no,t.item_code,tf.amt_inc_dec,tf.condition \r\n"
					+ "from cue_tb_miso_wepe_weapon_footnotes tf\r\n"
					+ "inner join cue_tb_wepe_link_sus_weapon_footnotes lf on tf.id = lf.fk_weapon_foot\r\n"
					+ "inner join CUE_TB_MISO_MMS_ITEM_MSTR t on t.item_code::text=tf.item_seq_no \r\n"
					+ "where tf.we_pe_no=? and lf.sus_no=? and\r\n"
					+ "tf.status='1'";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, wepe_no);
			stmt.setString(2, sus_no);
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
	public List<String> check_locationWeap(String wepe_wep_no,String sus_no) {
		if(!wepe_wep_no.equals(""))
		{
			wepe_wep_no = wepe_wep_no.replace("&#40;", "(");
			wepe_wep_no = wepe_wep_no.replace("&#41;", ")");
		}
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select   CASE \r\n"
					+ "        WHEN app.case OR app.unit OR app.formation THEN TRUE\r\n"
					+ "        ELSE FALSE\r\n"
					+ "    END AS result from (\r\n"
					+ "SELECT \r\n"
					+ "	case when  M.SCENARIO ='Location' then 	CONCAT(B.UNIT_NAME,\r\n"
					+ "		C.UNIT_NAME,\r\n"
					+ "		L.CODE_VALUE) in(select \r\n"
					+ "	e.code_value AS location\r\n"
					+ "from tb_miso_orbat_unt_dtl a\r\n"
					+ "LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
					+ "LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
					+ "where  a.sus_no = ? and status_sus_no ='Active') end,\r\n"
					+ "case when  M.SCENARIO ='Unit' \r\n"
					+ "then CONCAT(B.UNIT_NAME,C.UNIT_NAME,L.CODE_VALUE) in(select \r\n"
					+ "	a.unit_name AS location\r\n"
					+ "from tb_miso_orbat_unt_dtl a\r\n"
					+ "where  a.sus_no = ? and status_sus_no ='Active') end as unit,\r\n"
					+ "CASE \r\n"
					+ "        WHEN M.SCENARIO = 'Formation' THEN\r\n"
					+ "            CASE \r\n"
					+ "                WHEN EXISTS (\r\n"
					+ "                    SELECT 1\r\n"
					+ "                    FROM tb_miso_orbat_unt_dtl a\r\n"
					+ "                    LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
					+ "                    LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
					+ "                    LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ "                    WHERE a.sus_no = ?  AND status_sus_no ='Active'                \r\n"
					+ "					AND CONCAT(B.UNIT_NAME, C.UNIT_NAME, L.CODE_VALUE) IN (ab.cmd_name, ac.coprs_name, ad.div_name, ae.bde_name)\r\n"
					+ "                ) THEN true\r\n"
					+ "                ELSE false\r\n"
					+ "            END\r\n"
					+ "    END AS formation \r\n"
					+ "		\r\n"
					+ "FROM CUE_TB_MISO_WEPE_WEAPONS_MDFS M \r\n"
					+ "left outer join tb_miso_orbat_code l on m.location=l.code and l.status_record='1' \r\n"
					+ "left outer join tb_miso_orbat_unt_dtl b on m.formation=b.sus_no and b.status_sus_no='Active' \r\n"
					+ "left outer join tb_miso_orbat_unt_dtl c on m.scenario_unit=c.sus_no and c.status_sus_no='Active' \r\n"
					+ "INNER JOIN CUE_TB_MISO_MMS_ITEM_MSTR ON CUE_TB_MISO_MMS_ITEM_MSTR.ITEM_CODE = M.ITEM_SEQ_NO\r\n"
					+ "WHERE M.WE_PE_NO = ?  and m.status='1')  app\r\n"
					+ "";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, wepe_wep_no);
		
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
	
			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
				list.add( rs.getString("result"));
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
	public List<String> check_locationWeapTrans(String wepe_wep_no,String sus_no)
	{
		if(!wepe_wep_no.equals(""))
		{
			wepe_wep_no = wepe_wep_no.replace("&#40;", "(");
			wepe_wep_no = wepe_wep_no.replace("&#41;", ")");
		}
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select   CASE \r\n"
					+ "        WHEN app.case OR app.unit OR app.formation THEN TRUE\r\n"
					+ "        ELSE FALSE\r\n"
					+ "    END AS result from (\r\n"
					+ "SELECT \r\n"
					+ "	case when  M.SCENARIO ='Location' then CONCAT(B.UNIT_NAME,C.UNIT_NAME,L.CODE_VALUE) in(select \r\n"
					+ "	e.code_value AS location\r\n"
					+ "from tb_miso_orbat_unt_dtl a\r\n"
					+ "LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
					+ "LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
					+ "where  a.sus_no = ?  and status_sus_no ='Active') end,\r\n"
					+ "case when  M.SCENARIO ='Unit' \r\n"
					+ "then CONCAT(B.UNIT_NAME,C.UNIT_NAME,L.CODE_VALUE) in(select \r\n"
					+ "	a.unit_name AS location\r\n"
					+ "from tb_miso_orbat_unt_dtl a\r\n"
					+ "where  a.sus_no = ? and status_sus_no ='Active') end as unit,\r\n"
					+ "CASE \r\n"
					+ "        WHEN M.SCENARIO = 'Formation' THEN\r\n"
					+ "            CASE \r\n"
					+ "                WHEN EXISTS (\r\n"
					+ "                    SELECT 1\r\n"
					+ "                    FROM tb_miso_orbat_unt_dtl a\r\n"
					+ "                    LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
					+ "                    LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
					+ "                    LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
					+ "                    LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
					+ "                    WHERE a.sus_no = ? AND status_sus_no ='Active'                \r\n"
					+ "					AND CONCAT(B.UNIT_NAME, C.UNIT_NAME, L.CODE_VALUE) IN (ab.cmd_name, ac.coprs_name, ad.div_name, ae.bde_name)\r\n"
					+ "                ) THEN true\r\n"
					+ "                ELSE false\r\n"
					+ "            END\r\n"
					+ "    END AS formation\r\n"
					+ "FROM CUE_TB_MISO_WEPE_TRANSPORT_MDFS M	\r\n"
					+ "LEFT OUTER JOIN TB_MISO_ORBAT_CODE L ON M.LOCATION = L.CODE\r\n"
					+ "AND L.STATUS_RECORD = '1'\r\n"
					+ "LEFT OUTER JOIN TB_MISO_ORBAT_UNT_DTL B ON M.FORMATION = B.SUS_NO\r\n"
					+ "AND B.STATUS_SUS_NO = 'Active'\r\n"
					+ "LEFT OUTER JOIN TB_MISO_ORBAT_UNT_DTL C ON M.SCENARIO_UNIT = C.SUS_NO\r\n"
					+ "AND C.STATUS_SUS_NO = 'Active'\r\n"
					+ "INNER JOIN TB_TMS_MCT_MAIN_MASTER ON TB_TMS_MCT_MAIN_MASTER.MCT_MAIN_ID::text = M.MCT_NO\r\n"
					+ "WHERE M.WE_PE_NO = ? and   m.status='1')app\r\n"
					+ "\r\n"
					+ "";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);
			stmt.setString(3, sus_no);
			stmt.setString(4, wepe_wep_no);
		
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
				list.add( rs.getString("result"));
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
	public List<String> check_locationWeappers(String wepe_trans_no, String unit_sus_no) {			
		if(!wepe_trans_no.equals(""))
		{
			wepe_trans_no = wepe_trans_no.replace("&#40;", "(");
			wepe_trans_no = wepe_trans_no.replace("&#41;", ")");
		}
			List<String> list = new ArrayList<String>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				q = " select   CASE \r\n"
						+ "        WHEN app.case OR app.unit OR app.formation THEN TRUE\r\n"
						+ "        ELSE FALSE\r\n"
						+ "    END AS result from ( SELECT case when  TBL_APPT.SCENARIO ='Location' then CONCAT(B.UNIT_NAME,C.UNIT_NAME,L.CODE_VALUE) in(select \r\n"
						+ "	e.code_value AS location\r\n"
						+ "from tb_miso_orbat_unt_dtl a\r\n"
						+ "LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
						+ "LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
						+ "where  a.sus_no = ? and status_sus_no ='Active') end,\r\n"
						+ "						case when  TBL_APPT.SCENARIO ='Unit' \r\n"
						+ "then CONCAT(B.UNIT_NAME,C.UNIT_NAME,L.CODE_VALUE) in(select \r\n"
						+ "	a.unit_name AS location\r\n"
						+ "from tb_miso_orbat_unt_dtl a\r\n"
						+ "where  a.sus_no = ? and status_sus_no ='Active') end as unit,\r\n"
						+ "CASE \r\n"
						+ "        WHEN TBL_APPT.SCENARIO = 'Formation' THEN\r\n"
						+ "            CASE \r\n"
						+ "                WHEN EXISTS (\r\n"
						+ "                    SELECT 1\r\n"
						+ "                    FROM tb_miso_orbat_unt_dtl a\r\n"
						+ "                    LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
						+ "                    LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n"
						+ "                    LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
						+ "                    LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
						+ "                    LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
						+ "                    LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
						+ "                    WHERE a.sus_no = ? AND status_sus_no ='Active'                \r\n"
						+ "					AND CONCAT(B.UNIT_NAME, C.UNIT_NAME, L.CODE_VALUE) IN (ab.cmd_name, ac.coprs_name, ad.div_name, ae.bde_name)\r\n"
						+ "                ) THEN true\r\n"
						+ "                ELSE false\r\n"
						+ "            END\r\n"
						+ "    END AS formation\r\n"
						+ "						\r\n"
						+ "		\r\n"
						+ "		FROM\r\n"
						+ "			(SELECT DISTINCT CUE_TB_MISO_WEPE_PERS_MDFS.WE_PE_NO,\r\n"
						+ "					LOCATION,\r\n"
						+ "					FORMATION,\r\n"
						+ "					SCENARIO_UNIT,\r\n"
						+ "					SCENARIO,\r\n"
						+ "					MODIFICATION,\r\n"
						+ "					CUE_TB_MISO_WEPE_PERS_MDFS.ID,\r\n"
						+ "					CUE_TB_MISO_WEPE_PERS_MDFS.APPT_TRADE,\r\n"
						+ "					CUE_TB_MISO_WEPE_PERS_MDFS.RANK AS RANK_CODE,\r\n"
						+ "					CUE_TB_PSG_RANK_APP_MASTER.DESCRIPTION AS APPT,\r\n"
						+ "					T_DOMAIN_VALUE.LABEL,\r\n"
						+ "					CUE_TB_MISO_WEPE_PERS_MDFS.AMT_INC_DEC,\r\n"
						+ "					CUE_TB_MISO_WEPE_PERS_MDFS.CAT_PER\r\n"
						+ "				FROM CUE_TB_MISO_WEPE_PERS_MDFS\r\n"
						+ "				INNER JOIN T_DOMAIN_VALUE ON T_DOMAIN_VALUE.CODEVALUE::text = CUE_TB_MISO_WEPE_PERS_MDFS.RANK_CAT::text\r\n"
						+ "				AND T_DOMAIN_VALUE.DOMAINID::text = 'RANKCATEGORY'::text\r\n"
						+ "				AND CUE_TB_MISO_WEPE_PERS_MDFS.STATUS = '1'\r\n"
						+ "				INNER JOIN CUE_TB_PSG_RANK_APP_MASTER ON CUE_TB_PSG_RANK_APP_MASTER.CODE = CUE_TB_MISO_WEPE_PERS_MDFS.APPT_TRADE)TBL_APPT\r\n"
						+ "		INNER JOIN CUE_TB_PSG_RANK_APP_MASTER ON CUE_TB_PSG_RANK_APP_MASTER.CODE = TBL_APPT.RANK_CODE\r\n"
						+ "		LEFT OUTER JOIN TB_MISO_ORBAT_CODE L ON LOCATION = L.CODE\r\n"
						+ "		AND L.STATUS_RECORD = '1'\r\n"
						+ "		LEFT OUTER JOIN TB_MISO_ORBAT_UNT_DTL B ON FORMATION = B.SUS_NO\r\n"
						+ "		AND B.STATUS_SUS_NO = 'Active'\r\n"
						+ "		LEFT OUTER JOIN TB_MISO_ORBAT_UNT_DTL C ON SCENARIO_UNIT = C.SUS_NO\r\n"
						+ "		AND C.STATUS_SUS_NO = 'Active'  \r\n"
						+ "		where TBL_APPT.WE_PE_NO = ?) app";
			
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setString(1, unit_sus_no);
				stmt.setString(2, unit_sus_no);
				stmt.setString(3, unit_sus_no);
				stmt.setString(4, wepe_trans_no);
			
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
			
				while (rs.next()) {
//					Map<String, Object> columns = new LinkedHashMap<String, Object>();
//					for (int i = 1; i <= columnCount; i++) {
//						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//					}
					list.add( rs.getString("result"));
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
