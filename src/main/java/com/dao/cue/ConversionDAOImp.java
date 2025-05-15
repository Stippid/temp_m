package com.dao.cue;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.controller.cue.cueContoller;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class ConversionDAOImp implements ConversionDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<String> getSusNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sus_no from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm  where level_in_hierarchy='Unit') and status_sus_no='Active' order by sus_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getUnitsNameList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no in(select sus_no from Tbl_CodesForm where level_in_hierarchy='Unit') and status_sus_no='Active' order by unit_name");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getWeNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe='WE' order by we_pe_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getPeNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe='PE' order by we_pe_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getFeNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe='FE' order by we_pe_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getGoiNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct we_pe_no from CUE_TB_MISO_WEPECONDITIONS where we_pe='GOI' order by we_pe_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getWetNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet='WET' order by wet_pet_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getPetNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet='PET' order by wet_pet_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getFetNoList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct wet_pet_no from CUE_TB_MISO_MMS_WET_PET_MAST_DET where wet_pet='FET' order by wet_pet_no");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public String DeleteWETToWEUrlAdd(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_WEPECONDITIONS  set wet_pet_no = '',wet_link_status='' where id = :id";
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

	public String DeleteWETToWE(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_WEPECONDITIONS set wet_pet_no ='',wet_link_status='' where id = :id";
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

	public List<Map<String, Object>> getSearchWetToWe(String we_pe, String we_pe_no, String wet_link_status,
			String roleType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!wet_link_status.equals("all")) {
				qry += " where type='3' ";
			}
			if (we_pe != "") {
				qry += " and we_pe = ?";
			}
			if (we_pe_no != "") {
				qry += " and we_pe_no = ?";
			}

			q = " select we_pe_no,table_title,wet_pet_no,wet_link_status,id from cue_tb_miso_wepeconditions " + qry
					+ " order by we_pe_no ";
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (we_pe != "") {
				stmt.setString(j, we_pe);
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

				String f = "";
				String status = rs.getObject(4).toString();
				if (status.equals("1")) {
					String approveButton = "<i class='action_icons action_approve' title='Approved Data'></i>";
					f = approveButton;
				} else {
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					f = deleteButton;
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

	
	public List<Map<String, Object>> getSearchWetToWe1(String we_pe, String we_pe_no, String wet_link_status,
			String roleType,String roleAccess,String roleSusNo) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (wet_link_status != "") {
					if (!wet_link_status.equals("all")) {
						qry += " and wet_link_status = ?";
					}
				}
				if (we_pe != "") {
					qry += " and we_pe = ?";
				}
				if (we_pe_no != "") {
					qry += " and we_pe_no = ?";
				}
				
				if(roleAccess.equals("Line_dte")) {

					qry += " and sponsor_dire = ?";

				}

				q = " select we_pe_no,table_title,wet_pet_no,wet_link_status,id,\r\n"
						+ "created_by as cr_by,created_on as cr_on,approved_rejected_by as app_by,date_of_apprv_rejc as app_on,\r\n"
						+ "	modified_by as modi_by,modified_on as modi_on  from cue_tb_miso_wepeconditions where type='3' "
						+ qry + " order by we_pe_no ";
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (wet_link_status != "") {
					if (!wet_link_status.equals("all")) {
						stmt.setString(j, wet_link_status);
						j += 1;
					}
				}

				if (we_pe != "") {
					stmt.setString(j, we_pe);
					j += 1;
				}

				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
					j += 1;
				}

				
				if(roleAccess.equals("Line_dte")) {
					stmt.setString(j, roleSusNo);

				}
				
				System.out.println(" search we to wet------------------" + stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String rejectButton = "<i class='action_icons action_reject' " + Reject
							+ " title='Reject Data' ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					String f = "";
					if (wet_link_status.equals("0")) {
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

					} else if (wet_link_status.equals("1")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += rejectButton;
							f += LogButton1;
						}
						if (roleType.equals("DEO")) {
							f += appButton1;
							f += LogButton1;
						}

					} else if (wet_link_status.equals("2")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += appButton;
						}
						if (roleType.equals("DEO") || roleType.equals("ALL")) {

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
	}
}
