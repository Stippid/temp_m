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

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;
import com.persistance.util.HibernateUtilNA;

public class Wet_Pet_Fet_SuperDAOImpl implements Wet_Pet_Fet_SuperDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> getWetPetSuperReportDetailUrl1(String wet_pet, String wet_pet_no, String unit_type,
			String arm, String sponsor_dire, String uploaded_wetpet, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (wet_pet != "" && wet_pet != null)
					qry = " and e.wet_pet=?";
				if (wet_pet_no != "" && wet_pet_no != null)
					qry += " and e.wet_pet_no=?";
				if (unit_type != "" && unit_type != null)
					qry += " and e.unit_type=? ";
				if (!arm.equals("0") && arm != "")
					qry += " and e.arm=?";
				if (!sponsor_dire.equals("0") && sponsor_dire != "")
					qry += " and e.sponsor_dire=? ";

				if (uploaded_wetpet != "" && uploaded_wetpet != null)
					qry += " and e.uploaded_wetpet=?";
///jainisha
			/*	q = "SELECT e.id,e.status,e.wet_pet,e.wet_pet_no,e.uploaded_wetpet,e.superseeded_wet_pet,e.unit_type,e.valid_from,e.valid_till,e.doc_type,a.arm_desc,e.sponsor_dire,e.reject_letter_no,e.reject_remarks FROM cue_tb_miso_mms_wet_pet_mast_det e ,"
						+ " tb_miso_orbat_arm_code a  WHERE (e.arm=a.arm_code) " + qry + " order by e.id desc";*/
				q = "SELECT distinct e.id,e.status,e.wet_pet,e.wet_pet_no,e.uploaded_wetpet,e.superseeded_wet_pet,e.unit_type,e.valid_from,e.valid_till,e.doc_type,a.arm_desc,e.sponsor_dire,sd.line_dte_name,e.reject_letter_no,e.reject_remarks FROM cue_tb_miso_mms_wet_pet_mast_det e ,\r\n"
						+ "tb_miso_orbat_arm_code a, tb_miso_orbat_line_dte sd   WHERE (e.arm=a.arm_code)  and sd.line_dte_sus=e.sponsor_dire " + qry + " order by e.id desc";


				stmt = conn.prepareStatement(q);
				int j = 1;
				if (wet_pet != "" && wet_pet != null) {
					stmt.setString(j, wet_pet);
					j += 1;
				}
				if (wet_pet_no != "" && wet_pet_no != null) {
					stmt.setString(j, wet_pet_no);
					j += 1;
				}
				if (unit_type != "" && unit_type != null) {
					stmt.setString(j, unit_type);
					j += 1;
				}
				if (!arm.equals("0") && arm != "") {
					stmt.setString(j, arm);
					j += 1;
				}
				if (!sponsor_dire.equals("0") && sponsor_dire != "") {
					stmt.setString(j, sponsor_dire);
					j += 1;
				}
				if (uploaded_wetpet != "" && uploaded_wetpet != null) {
					stmt.setString(j, uploaded_wetpet);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String f = "";
					if (rs.getObject(2).equals("1")) {
						f += appButton1;
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
			return list;
		}
	}

	public List<Map<String, Object>> getWetPetSuperReportDetailUrl(String status, String wet_pet, String wet_pet_no,
			String unit_type, String arm, String sponsor_dire, String uploaded_wetpet, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();

				String q1 = "";

				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						q1 += " and e.status = ?";
					}
				}
				if (wet_pet_no != "" && wet_pet_no != null) {
					q1 += " and e.wet_pet_no=? ";
				}
				if (unit_type != "" && unit_type != null) {
					q1 += " and e.unit_type=? ";
				}
				if (!arm.equals("0") && arm != "" && arm != null) {
					q1 += " and e.arm=?";
				}
				if (!sponsor_dire.equals("0") && sponsor_dire != "") {
					q1 += " and e.sponsor_dire=?";
				}

				if (wet_pet != "" && wet_pet != null) {
					q1 += " and e.wet_pet=?";
				}
 /*191223 v2 bisag (get line _dte name)*/
			/*	q = "SELECT e.id,e.status,e.wet_pet,e.wet_pet_no,e.uploaded_wetpet,e.superseeded_wet_pet,e.unit_type,e.valid_from,e.valid_till,e.doc_type,a.arm_desc,e.sponsor_dire,e.reject_letter_no,e.reject_remarks FROM cue_tb_miso_mms_wet_pet_mast_det e ,"
						+ " tb_miso_orbat_arm_code a  WHERE (e.arm=a.arm_code) " + q1 + " order by e.status,e.id desc";*/

				q="SELECT distinct e.id,e.status,e.wet_pet,e.wet_pet_no,e.uploaded_wetpet,e.superseeded_wet_pet,e.unit_type,e.valid_from,e.valid_till,e.doc_type,a.arm_desc,e.sponsor_dire,sd.line_dte_name,e.reject_letter_no,e.reject_remarks "
						+ ",e.created_by as cr_by,e.created_on as cr_on,e.approved_rejected_by as app_by,e.approved_rejected_on as app_on,\r\n"
						+ "	e.modified_by as modi_by,e.modified_on as modi_on "
						+ "FROM cue_tb_miso_mms_wet_pet_mast_det e , \r\n"
						+ "tb_miso_orbat_arm_code a ,tb_miso_orbat_line_dte sd  WHERE (e.arm=a.arm_code) and sd.line_dte_sus=e.sponsor_dire " + q1 + " order by e.status,e.id desc";

				PreparedStatement stmt = conn.prepareStatement(q);
				int j = 1;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}
				}
				if (wet_pet_no != "" && wet_pet_no != null) {
					stmt.setString(j, wet_pet_no);
					j += 1;
				}
				if (unit_type != "" && unit_type != null) {
					stmt.setString(j, unit_type);
					j += 1;
				}
				if (!arm.equals("0") && arm != "" && arm != null) {
					stmt.setString(j, arm);
					j += 1;
				}
				if (!sponsor_dire.equals("0") && sponsor_dire != "") {
					stmt.setString(j, sponsor_dire);
					j += 1;
				}

				if (wet_pet != "" && wet_pet != null) {
					stmt.setString(j, wet_pet);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(1) +",'"+ rs.getObject(6)+ "','"+ rs.getObject(2)+ "')}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
							+ Reject + " title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String LogButton = cueContoller.Get_button_info(metaData,rs);
	                  String LogButton1 = cueContoller.Get_button_info1(metaData,rs);
					String f = "";

					if (status.equals("0")) {
						/*if (roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;

						}*/
						if (roleType.equals("APP")) {
							f += appButton;
							f += rejectButton;
							f += LogButton;
						}
						if (roleType.equals("DEO") || roleAccess.equals("Line_dte")|| roleType.equals("ALL")) {
							f += appButton;
							f += rejectButton;
							f += updateButton;
							f += deleteButton;
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
						if (roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")) {
							f += updateButton;
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
	}

	public List<Map<String, Object>> getWetPetEquipmentReportDetail(String wet_pet_no, String item_seq_no,
			String authorization, String status, String roleType, String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String q1 = "";
			try {
				conn = dataSource.getConnection();

				PreparedStatement stmt = null;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						q1 += " and e.status =?";
					}

				}
				if (wet_pet_no != "" && wet_pet_no != null)
					q1 += " and e.wet_pet_no=? ";

				q = " SELECT e.id,e.wet_pet_no,i.item_type,e.authorization,e.remarks,e.item_seq_no,e.status "
						+ ",e.created_by as cr_by,e.created_on as cr_on,e.modified_by as modi_by,e.modified_on as modi_on "
						+ "FROM cue_tb_miso_mms_wet_pet_det e ,"
						+ "cue_tb_miso_mms_item_mstr i  WHERE (e.item_seq_no=i.item_code) " + q1
						+ " order by e.item_seq_no,i.item_type";

				stmt = conn.prepareStatement(q);
				int j = 1;
				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}
				}
				if (wet_pet_no != "" && wet_pet_no != null) {
					stmt.setString(j, wet_pet_no);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
							+ Reject + " title='Reject Data' data-toggle='modal'  ></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					
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
//							f += appButton;
//							f += rejectButton;
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
						if (roleType.equals("DEO") || roleType.equals("ALL") || roleAccess.equals("Line_dte")) {

							f += deleteButton;
							f += updateButton;
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
	}

	public List<Map<String, Object>> getWetPetEquipmentReportDetail1(String wet_pet_no, String item_seq_no,
			String authorization, String roleType) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String q1 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (wet_pet_no != "" && wet_pet_no != null)
					q1 += " and e.wet_pet_no=? ";

				q = " SELECT e.id,e.wet_pet_no,i.item_type,e.authorization,e.remarks,e.item_seq_no,e.reject_remarks,e.reject_letter_no FROM cue_tb_miso_mms_wet_pet_det e ,cue_tb_miso_mms_item_mstr i  WHERE (e.item_seq_no=i.item_code) "
						+ q1 + " order by e.item_seq_no,i.item_type";

				stmt = conn.prepareStatement(q);
				if (wet_pet_no != "" && wet_pet_no != null) {
					stmt.setString(1, wet_pet_no);
				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String f = "";

					f += deleteButton;
					f += updateButton;
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
	}

	public String setDeleteWetPetSuper(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE id = :id";
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

	public CUE_TB_MISO_MMS_WET_PET_MAST_DET getWetPetSuperDetailsByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_MMS_WET_PET_MAST_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_MMS_WET_PET_MAST_DET list = (CUE_TB_MISO_MMS_WET_PET_MAST_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateWetPetSuperDetails(CUE_TB_MISO_MMS_WET_PET_MAST_DET wetSuper, String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE CUE_TB_MISO_MMS_WET_PET_MAST_DET SET superseeded_wet_pet=:superseeded_wet_pet, unit_type =:unit_type, valid_from=:valid_from,valid_till=:valid_till,uploaded_wetpet=:uploaded_wetpet,doc_type=:doc_type,arm=:arm,sponsor_dire=:sponsor_dire, modified_by=:modified_by,modified_on=:modified_on,status ='0' WHERE id=:id";
		Query query = session.createQuery(hql).setString("superseeded_wet_pet", wetSuper.getSuperseeded_wet_pet())
				.setString("unit_type", wetSuper.getUnit_type()).setString("valid_from", wetSuper.getValid_from())
				.setString("valid_till", wetSuper.getValid_till())
				.setString("uploaded_wetpet", wetSuper.getUploaded_wetpet())
				.setString("doc_type", wetSuper.getDoc_type()).setString("arm", wetSuper.getArm())
				.setString("sponsor_dire", wetSuper.getSponsor_dire()).setString("modified_by", username)
				.setString("modified_on", modifydate).setInteger("id", wetSuper.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public String setDeleteWetPetAmendments(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM CUE_TB_MISO_MMS_WET_PET_DET WHERE id = :id";
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

	public CUE_TB_MISO_MMS_WET_PET_DET getWetPetAmendmentsDetailsByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_MMS_WET_PET_DET where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_MMS_WET_PET_DET list = (CUE_TB_MISO_MMS_WET_PET_DET) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateWetPetAmendmentsDetails(CUE_TB_MISO_MMS_WET_PET_DET wetSuper, String username) {
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE CUE_TB_MISO_MMS_WET_PET_DET SET item_seq_no=:item_seq_no, authorization =:authorization, remarks=:remarks,modified_by=:modified_by,modified_on=:modified_on, wet_pet_no=:wet_pet_no WHERE id=:id";
		Query query = session.createQuery(hql).setString("item_seq_no", wetSuper.getItem_seq_no())
				.setDouble("authorization", wetSuper.getAuthorization()).setString("remarks", wetSuper.getRemarks())
				.setString("wet_pet_no", wetSuper.getWet_pet_no()).setString("modified_by", username)
				.setString("modified_on", modifydate).setInteger("id", wetSuper.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public List<String> getWetPetAmendmentsDetailsByEditId(int editid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery(
				"SELECT e.id,t.wet_pet,e.wet_pet_no,t.unit_type,t.uploaded_wetpet,i.item_type,i.item_code,e.authorization,e.remarks FROM CUE_TB_MISO_MMS_WET_PET_DET e , CUE_TB_MISO_MMS_WET_PET_MAST_DET t, CUE_TB_MISO_MMS_ITEM_MSTR i  WHERE e.wet_pet_no=t.wet_pet_no and e.item_seq_no=i.item_code and e.id=:editid");
		q.setInteger("editid", editid);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	//////////////////////////////

	public String COPYWetPetFetDetail(String wet_pet_no_copy, String wet_pet_no, String unit_type, int roleid) {
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
			String sql = null;

			sql = "INSERT INTO cue_tb_miso_mms_wet_pet_mast_det( approved_rejected_by, approved_rejected_on, created_by, created_on,equip_table_id, modified_by, modified_on, softdelete,superseeded_wet_pet, valid_from, valid_till,version_no,vettedby_dte1,vettedby_dte2,wet_pet,wet_pet_status,doc_type,arm,sponsor_dire,uploaded_wetpet,unit_type,wet_pet_no,roleid)"
					+ "SELECT approved_rejected_by, approved_rejected_on, created_by, created_on,equip_table_id, modified_by, modified_on, softdelete,superseeded_wet_pet, valid_from, valid_till,version_no,vettedby_dte1,vettedby_dte2,wet_pet,wet_pet_status,doc_type,arm,sponsor_dire,uploaded_wetpet,"
					+ "?,?,? FROM cue_tb_miso_mms_wet_pet_mast_det WHERE wet_pet_no=?  ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, unit_type);
			stmt.setString(2, wet_pet_no);
			stmt.setInt(3, roleid);
			stmt.setString(4, wet_pet_no_copy);
			stmt.executeUpdate();
			stmt.close();

			String sql1 = "INSERT INTO cue_tb_miso_mms_wet_pet_det( acc_unit, catpart_no, cec_cces, ces_cces_no,cos_sec, created_by, created_on, item_equip_name,item_seq_no, modified_by, modified_on,periodicity,softdelete,vettedby_dte1,vettedby_dte2,wet_type,remarks,version_no,catpart_id,equip_table_id,\"authorization\",wet_pet_no,roleid,status) "
					+ "SELECT acc_unit, catpart_no, cec_cces, ces_cces_no,cos_sec, created_by, created_on, item_equip_name,item_seq_no, modified_by, modified_on,periodicity,softdelete,vettedby_dte1,vettedby_dte2,wet_type,remarks,version_no,catpart_id,equip_table_id,\"authorization\","
					+ "?,?,status FROM cue_tb_miso_mms_wet_pet_det WHERE wet_pet_no=?";

			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, wet_pet_no);
			stmt1.setInt(2, roleid);
			stmt1.setString(3, wet_pet_no_copy);
			stmt1.executeUpdate();
			stmt1.close();

			conn.close();
			return "Copy Successfully";

		} catch (SQLException e) {

			e.printStackTrace();
			return "Copy not Successfully";
		}
	}

	public String setApproveduploadwetpet(int appid, int roleid, String username, String roleType) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String hqlUpdate = "update CUE_TB_MISO_MMS_WET_PET_MAST_DET c set c.status = :status, approved_rejected_by=:approved_rejected_by, approved_rejected_on=:date where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setInteger("id", appid).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}
	
	
	public String updatecapdatawetpet(String wet_pet_no,String superno,int roleid,String username,String roleType, String pstatus) {
		String ms="";
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		Query c=session.createQuery("select count(*) from CUE_TB_MISO_MMS_WET_PET_DET where wet_pet_no =:wet_pet_no and status='0'");
		c.setParameter("wet_pet_no", wet_pet_no);
		Long count = (Long)c.uniqueResult();
		if(count==0) {
		Query q= session.createQuery(
				"from CUE_TB_MISO_MMS_WET_PET_DET c where c.wet_pet_no=:superno");
		q.setString("superno", superno);
		@SuppressWarnings("unchecked")
	
		List<CUE_TB_MISO_MMS_WET_PET_DET> list = (List<CUE_TB_MISO_MMS_WET_PET_DET>) q.list();
		if(!list.isEmpty()) {
			CUE_TB_MISO_MMS_WET_PET_DET rs = new CUE_TB_MISO_MMS_WET_PET_DET();
			rs.setAcc_unit(list.get(0).getAcc_unit());
			rs.setCatpart_id(list.get(0).getCatpart_id());
			rs.setCatpart_no(list.get(0).getCatpart_no());
			rs.setCec_cces(list.get(0).getCec_cces());
			rs.setCes_cces_no(list.get(0).getCes_cces_no());
			rs.setCos_sec(list.get(0).getCos_sec());
			rs.setEquip_table_id(list.get(0).getEquip_table_id());
			rs.setItem_equip_name(list.get(0).getItem_equip_name());
			rs.setItem_seq_no(list.get(0).getItem_seq_no());
//			rs.setModified_by(list.get(0).getModified_by());
//			rs.setModified_on(list.get(0).getModified_on());
			rs.setPeriodicity(list.get(0).getPeriodicity());
			rs.setSoftdelete(list.get(0).getSoftdelete());
			rs.setVettedby_dte1(list.get(0).getVettedby_dte1());
			rs.setVettedby_dte2(list.get(0).getVettedby_dte2());
			rs.setWet_type(list.get(0).getWet_type());
			rs.setRemarks(list.get(0).getRemarks());
			rs.setVersion_no(list.get(0).getVersion_no());
			rs.setAuthorization(list.get(0).getAuthorization());
//			rs.setReject_remarks(list.get(0).getReject_remarks());
//			rs.setReject_letter_no(list.get(0).getReject_letter_no());
//			rs.setReject_date(list.get(0).getReject_date());
			rs.setRoleid(roleid);
			rs.setStatus("0");
			rs.setWet_pet_no(wet_pet_no);
			rs.setCreated_by(username);
			rs.setCreated_on(Date);
			int  a = (int)session.save(rs);
			if(a!=0) {
				ms="Data Saved";
			}else {
				ms="Data not Saved";
			}
					
		}
		}
		
		tx.commit();
		session.close();

			return ms;
	}

	public String setApprovedWet_pet_amendmentdetails(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_MMS_WET_PET_DET c set c.status = :status where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setInteger("id", appid).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}

}
