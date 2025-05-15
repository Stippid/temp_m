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
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.controller.notification.NotificationController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.models.UserLogin;
import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_MasterDAOImpl implements Cue_MasterDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	NotificationController notification = new NotificationController();
	Posting_Out_Controller post = new Posting_Out_Controller();

	public String setApprovedDocument(int appid, String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//		String hqlUpdate = "update cue_wet_pet c set c.status = :status where c.id = :id";
		String hqlUpdate = "update cue_wet_pet c set c.status = :status, approved_rejected_by=:approved_rejected_by, approved_rejected_on=:date where c.id = :id";
		int app = session.createQuery(hqlUpdate).setString("status", "1").setInteger("id", appid).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();		
		tx.commit();
		session.close();
		cue_wet_pet notit = getlastwetpetno(appid);
		List<UserLogin> userlist = post.getPostIN_outuseridlist(notit.getSponsor_dire());
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
		String wet_pet_no = notit.getWet_pet_no();
		String title = "ETRC Uploaded New WET/PET";
		String description = "A new WET/PET is uploaded by ETRC and WET/PET is " + wet_pet_no
				+ ". Pl add data as per revised WE/PE";
		Boolean d = notification.sendNotification(title, description, user_id, notit.getCreated_by());
		}
		if (app > 0) {
			return "Approved Successfully";
		} else {
			return "Approved not Successfully";
		}
	}
	private cue_wet_pet getlastwetpetno(int appid) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		cue_wet_pet id = (cue_wet_pet) sessionHQL

				.get(cue_wet_pet.class, appid);

		sessionHQL.getTransaction().commit();

		sessionHQL.close();

		return id;

	}
	public String setRejectDocument(int appid, String username) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

//		String hqlUpdate = "update cue_wet_pet c set c.status=:status where c.id = :id";
		String hqlUpdate = "update cue_wet_pet c set c.status=:status, approved_rejected_by=:approved_rejected_by, approved_rejected_on=:date where c.id = :id";

		int app = session.createQuery(hqlUpdate).setString("status", "2").setInteger("id", appid).setString("approved_rejected_by", username).setString("date", Date).executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			return "Rejected Successfully";
		} else {
			return "Rejected not Successfully";
		}
	}

	public String setDeleteDocument(int appid) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from cue_wet_pet where id = :id";
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

	public cue_wet_pet getcue_wet_petByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where id=:id");
		q.setParameter("id", id);
		cue_wet_pet list = (cue_wet_pet) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateDocAttValue(cue_wet_pet DocAttValues, String username, String modifydate) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update cue_wet_pet  set superseeded_wet_pet=:superseeded_wet_pet,valid_from=:valid_from,valid_till=:valid_till,unit_type=:unit_type,sponsor_dire=:sponsor_dire,arm=:arm,doc_type=:doc_type,doc_path=:doc_path,modified_by=:modified_by,modified_on=:modified_on,status ='0' where id=:id";
		Query query = session.createQuery(hql).setString("unit_type", DocAttValues.getUnit_type())
				.setString("valid_till", DocAttValues.getValid_till())
				.setString("valid_from", DocAttValues.getValid_from())
				.setString("superseeded_wet_pet", DocAttValues.getSuperseeded_wet_pet())
				.setString("sponsor_dire", DocAttValues.getSponsor_dire())
				.setString("doc_type", DocAttValues.getDoc_type()).setString("arm", DocAttValues.getArm())
				.setString("doc_path", DocAttValues.getDoc_path()).setInteger("id", DocAttValues.getId())
				.setString("modified_by", username).setString("modified_on", modifydate);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Update Successfully";
		} else {
			return "Update not Successfully";
		}
	}

	public List<cue_wet_pet> getWET_PET_FET_DOCid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wet_pet where id=:id");
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<cue_wet_pet> list = (List<cue_wet_pet>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getAttributeFromCategory1(String wet_pet, String wet_pet_no, String sponsor_dire,
			String arm, String doc_type, String status, String roleType, String from_date, String to_date,String roleAccess) {
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
						qry += " and c.status =?";
					}
				}
				if (wet_pet != "") {
					qry += " and c.wet_pet = ?";
				}
				if (wet_pet_no != "") {
					qry += " and c.wet_pet_no = ?";
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					qry += " and c.sponsor_dire = ?";
				}
				if (arm != "" && !arm.equals("0")) {
					qry += " and c.arm =?";
				}
				if (doc_type != "") {
					qry += " and c.doc_type = ?";
				}
				if (from_date != "" && to_date != "") {
					qry += " and c.created_on is not null and cast(concat(substr(c.created_on,7,4),'-',substr(c.created_on,4,2),'-',substr(c.created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
				}

				q = "select distinct c.wet_pet,c.wet_pet_no,c.doc_type,c.superseeded_wet_pet,l.line_dte_name as sponsor_dire,c.doc_type,o.arm_desc,c.status,c.id,c.valid_from,c.valid_till,c.unit_type,c.softdelete,\r\n"
						+ "c.created_by as cr_by,c.created_on as cr_on,c.approved_rejected_by as app_by,c.approved_rejected_on as app_on,\r\n"
						+ "	c.modified_by as modi_by,c.modified_on as modi_on  from cue_tb_miso_mms_wet_mast_upload c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte l  where c.arm=o.arm_code "
						+" and l.line_dte_sus = c.sponsor_dire"  + qry + " order by c.wet_pet,c.wet_pet_no ";

				stmt = conn.prepareStatement(q);
				int j = 1;

				if (status != "" && status != null && status != "null") {
					if (!status.equals("all")) {
						stmt.setString(j, status);
						j += 1;
					}
				}
				if (wet_pet != "") {
					stmt.setString(j, wet_pet);
					j += 1;
				}
				if (wet_pet_no != "") {
					stmt.setString(j, wet_pet_no);
					j += 1;
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					stmt.setString(j, sponsor_dire);
					j += 1;
				}
				if (arm != "" && !arm.equals("0")) {
					stmt.setString(j, arm);
					j += 1;
				}
				if (doc_type != "") {
					stmt.setString(j, doc_type);
					j += 1;
				}
				if(from_date != "" && to_date != ""){
					stmt.setString(j, from_date);
					j += 1;
					stmt.setString(j, to_date);
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete12 = "onclick=\"  if (confirm('Please Download') ){getDownloadImagewetpet("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String deleteButton2 = "<a " + Delete12 + ">Download</a>";

					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"  if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String rejectButton = "<i class='action_icons action_reject' " + Reject
							+ " title='Reject Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(9) + ")}else{ return false;}\"";
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
						if (roleType.equals("DEO")) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}
						
						if (roleType.equals("VIEW")) {
							f += appButton;
							f += rejectButton;
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
					columns.put(metaData.getColumnLabel(13), deleteButton2);
					columns.put(metaData.getColumnLabel(9), f);
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

	public List<Map<String, Object>> upload_WET_PET1(String status, String wet_pet, String wet_pet_no,
			String sponsor_dire, String arm, String doc_type, String roleType,String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (!status.equals("all")) {
					qry += "and (c.status = '0' or c.status = '2' or c.status = '1')  ";
				}
				if (wet_pet != "") {
					qry += " and c.wet_pet = ?";
				}
				if (wet_pet_no != "") {
					qry += " and c.wet_pet_no = ?";
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					qry += " and c.sponsor_dire = ?";
				}
				if (arm != "" && !arm.equals("0")) {
					qry += " and c.arm = ?";
				}

//				if (doc_type != "") {
//					if (!doc_type.equals("Confidential") && !doc_type.equals("Restricted")
//							&& !doc_type.equals("Secret")) {
//						qry += " and c.doc_type = ?";
//					}
//				}
				if (doc_type != "") {
						qry += " and c.doc_type = ?";
				}
				////jainisha
				
				/*q = "select distinct c.id,c.wet_pet,c.wet_pet_no,c.unit_type,c.superseeded_wet_pet,c.sponsor_dire,o.arm_desc,c.valid_from,c.valid_till,c.unit_type,c.doc_type,c.doc_path,c.status from cue_tb_miso_mms_wet_mast_upload c,tb_miso_orbat_arm_code o where c.arm=o.arm_code "
						+ qry + " order by c.wet_pet,c.wet_pet_no ";*/
				q = "select distinct c.id,c.wet_pet,c.wet_pet_no,c.unit_type,c.superseeded_wet_pet,c.sponsor_dire,sd.line_dte_name,o.arm_desc,c.valid_from,c.valid_till,c.unit_type,c.doc_type,c.doc_path,c.status from cue_tb_miso_mms_wet_mast_upload c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte sd  where c.arm=o.arm_code \r\n"
						+ " and sd.line_dte_sus=c.sponsor_dire	"+ qry + " order by c.wet_pet,c.wet_pet_no";


				stmt = conn.prepareStatement(q);
				int j = 1;
				if (wet_pet != "") {
					stmt.setString(j, wet_pet);
					j += 1;
				}
				if (wet_pet_no != "") {
					stmt.setString(j, wet_pet_no);
					j += 1;
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					stmt.setString(j, sponsor_dire);
					j += 1;
				}
				if (arm != "" && !arm.equals("0")) {
					stmt.setString(j, arm);
					j += 1;
				}

//				if (doc_type != "") {
//					if (!doc_type.equals("Confidential") && !doc_type.equals("Restricted")
//							&& !doc_type.equals("Secret")) {
//						stmt.setString(j, doc_type);
//					}
//				}
				
				if (doc_type != "") {
						stmt.setString(j, doc_type);
				}
				ResultSet rs = stmt.executeQuery();
				System.out.println("wet?pet?fet" + stmt);
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String Delete12 = "onclick=\"  if (confirm('Please Download') ){getDownloadImageWetPetUpload("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String deleteButton2 = "<a href='#' " + Delete12 + ">Download</a>";

					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
					String f = "";
					if (rs.getObject(14).equals("1")) {
//						f += appButton1;
						
//	 	  				if(roleType.equals("DEO") || roleAccess.equals("Line_dte"))
		 	  				if(roleType.equals("ALL")) {
								f += appButton1;
								f += deleteButton;
								f += updateButton;
								
							}
							if(roleType.equals("APP")) {
								f += appButton1;
					
								f += deleteButton;
								//f += updateButton;
								}
							if(roleType.equals("DEO") || roleAccess.equals("Line_dte")) {
								f += appButton1;
								}
							if( roleType.equals("VIEW") && roleAccess.equals("MISO") ) {
	  							f += appButton1;
	  							
	  							}	

					} else {
						f += deleteButton;
						f += updateButton;
					}
					System.out.println("rs.getObject(13)"+rs.getObject(13));
					columns.put(metaData.getColumnLabel(columnCount - 1), deleteButton2);
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

}
