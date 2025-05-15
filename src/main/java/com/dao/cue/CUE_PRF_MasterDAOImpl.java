package com.dao.cue;

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

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.models.Tb_Miso_Orbat_Code;
import com.persistance.util.HibernateUtilNA;

public class CUE_PRF_MasterDAOImpl implements CUE_PRF_MasterDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String setApprovedprfMst(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PRF_Mst c set c.status = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}

	public String setRejectprfMst(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PRF_Mst c set c.status = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Rejected Successfully";
		} else {
			return "Rejected not Successfully";
		}
	}

	public String setDeleteprfMst(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from CUE_TB_MISO_PRF_Mst where id = :id";
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

	public CUE_TB_MISO_PRF_Mst getCUE_TB_MISO_PRF_MstByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_PRF_Mst where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_PRF_Mst list = (CUE_TB_MISO_PRF_Mst) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String setUpdateprfMst(CUE_TB_MISO_PRF_Mst ac, String username, String modifydate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update CUE_TB_MISO_PRF_Mst  set prf_group=:prf_group ,coss_section =:coss_section,nodal_dte=:nodal_dte,modified_by=:modified_by,modified_on=:modified_on,status='0' where id=:id";
		Query query = session.createQuery(hql).setString("prf_group", ac.getPrf_group())
				.setString("coss_section", ac.getCoss_section()).setString("modified_by", username)
				.setString("modified_on", modifydate).setString("nodal_dte", ac.getNodal_dte())
				.setInteger("id", ac.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public @ResponseBody List<Map<String, Object>> getAttributeFromCategory2(String prf_group, String coss_section,
			String nodal_dte, String status, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (!status.equals("all")) {
					qry += " and (status = '0' or status = '2' or status = '1')  ";
				}

				if (prf_group != "") {
					qry += " and prf_group like ?";
				}

				if (!coss_section.equals("-")) {
					if (!coss_section.equals("0") && coss_section != "") {
						qry += " and coss_section = ?";
					}
				}
				if (nodal_dte != "" && !nodal_dte.equals("0")) {
					qry += " and nodal_dte = ?";
				}
				q = " select distinct p.status,p.prf_group,p.coss_section,t.label,p.id from cue_tb_miso_prf_group_mst p, t_domain_value t where p.nodal_dte=t.codevalue and t.domainid='SPONSERDTE' "
						+ qry + " order by p.prf_group ";

				stmt = conn.prepareStatement(q);

				int j = 1;
				if (prf_group != "") {
					stmt.setString(j, prf_group + "%");
					j += 1;
				}
				if (!coss_section.equals("0") && coss_section != "") {
					stmt.setString(j, coss_section);
					j += 1;
				}

				if (nodal_dte != "" && !nodal_dte.equals("0")) {
					stmt.setString(j, nodal_dte);
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
					if (rs.getObject(1).equals("1")) {

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

	public @ResponseBody List<Map<String, Object>> getAttributeFromCategory21(String prf_group, String coss_section,
			String nodal_dte, String status, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						qry += " and status = ?";
					}
				}
				if (prf_group != "") {
					qry += " and prf_group like ?";

				}

				if (!coss_section.equals("-")) {
					if (!coss_section.equals("0") && coss_section != "") {
						qry += " and coss_section = ?";

					}
				}
				if (nodal_dte != "" && !nodal_dte.equals("0")) {
					qry += " and nodal_dte = ?";

				}

				q = " select distinct p.status,p.prf_group,p.coss_section,t.label,p.id,\r\n"
						+ "p.created_by as cr_by,p.created_on as cr_on,\r\n"
						+ "	p.modified_by as modi_by,p.modified_on as modi_on  from cue_tb_miso_prf_group_mst p, t_domain_value t where p.nodal_dte=t.codevalue and t.domainid='SPONSERDTE' "
						+ qry + " order by p.prf_group ";

				stmt = conn.prepareStatement(q);

				int j = 1;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}

				}
				if (prf_group != "") {
					stmt.setString(j, prf_group + "%");
					j += 1;
				}

				if (!coss_section.equals("-")) {
					if (!coss_section.equals("0") && coss_section != "") {
						stmt.setString(j, coss_section);
						j += 1;

					}
				}
				if (nodal_dte != "" && !nodal_dte.equals("0")) {
					stmt.setString(j, nodal_dte);
				}

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
					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String rejectButton = "<i  class='action_icons action_reject' " + Reject + " ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(5) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
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

					} else if (status.equals("1")) {
						if (roleType.equals("APP") || roleType.equals("ALL")) {
							f += rejectButton;
							f += LogButton1;
						}
						if (roleType.equals("DEO")) {
							f += appButton1;
							f += LogButton1;
						}

					} else if (status.equals("2")) {
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