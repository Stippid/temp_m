
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
import org.hibernate.Transaction;

import com.controller.cue.cueContoller;
import com.models.cue_wepe;
import com.models.cue_wet_pet;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_wetpet_validuptoDAOImpl implements Cue_wetpet_validuptoDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> getAttributeFromCategorylist1(String wet_pet, String wet_pet_no, String sponsor_dire,
			String arm, String doc_type, String status, String roleType, String from_date, String to_date,
			String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			String qry1 = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (status.equals("pending")) {
					qry1 += " where c.color_indicator = 'green'";
				}else if (status.equals("resolve")) {
					qry1 += " where c.color_indicator = 'red'";
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

				q = "select c.* from (select distinct c.wet_pet,c.wet_pet_no,c.doc_type,c.superseeded_wet_pet,l.line_dte_name as sponsor_dire,c.doc_type,o.arm_desc,c.status,c.id,c.valid_from,c.valid_till,c.unit_type,c.softdelete,\r\n"
						+ "c.created_by as cr_by,c.created_on as cr_on,c.approved_rejected_by as app_by,c.approved_rejected_on as app_on,\r\n"
						+ "	c.modified_by as modi_by,c.modified_on as modi_on , " + "CASE \n"
						+ "        WHEN TO_DATE(c.valid_till, 'DD-MM-YYYY') < CURRENT_DATE THEN \n"
						+ "            CONCAT(\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(YEAR FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(YEAR FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))), ' years ')\n"
						+ "                    ELSE '' \n" + "                END,\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(MONTH FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(MONTH FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))), ' months ')\n"
						+ "                    ELSE '' \n" + "                END,\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(DAY FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(DAY FROM age(CURRENT_DATE, TO_DATE(c.valid_till, 'DD-MM-YYYY'))), ' days ')\n"
						+ "                    ELSE '' \n" + "                END\n" + "            )\n"
						+ "        ELSE \n" + "            CONCAT(\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(YEAR FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(YEAR FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)), ' years ')\n"
						+ "                    ELSE '' \n" + "                END,\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(MONTH FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(MONTH FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)), ' months ')\n"
						+ "                    ELSE '' \n" + "                END,\n" + "                CASE \n"
						+ "                    WHEN EXTRACT(DAY FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \n"
						+ "                        CONCAT(EXTRACT(DAY FROM age(TO_DATE(c.valid_till, 'DD-MM-YYYY'), CURRENT_DATE)), ' days')\n"
						+ "                    ELSE '' \n" + "                END\n" + "            )\n"
						+ "    END AS valid,\n" + "    CASE \n"
						+ "        WHEN TO_DATE(c.valid_till, 'DD-MM-YYYY') < CURRENT_DATE THEN 'red' \n"
						+ "        ELSE 'green' \n" + "    END AS color_indicator "
						+ " from cue_tb_miso_mms_wet_mast_upload c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte l  where c.arm=o.arm_code "
						+ " and l.line_dte_sus = c.sponsor_dire" + qry + " order by c.wet_pet,c.wet_pet_no) c "+qry1+"";

				stmt = conn.prepareStatement(q);
				int j = 1;

//				if (status != "" && status != null && status != "null") {
//					if (!status.equals("all")) {
//						stmt.setString(j, status);
//						j += 1;
//					}
//				}
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
				if (from_date != "" && to_date != "") {
					stmt.setString(j, from_date);
					j += 1;
					stmt.setString(j, to_date);
					j += 1;
				}
				System.out.println("WET PET Valid Upto "+  stmt+" -=--========-");
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						
						String colorIndicator = rs.getString("color_indicator");

						// Format the valid column with color
						String validValue = rs.getString("valid");
						String coloredValidValue = "<span style='color: " + colorIndicator + "'>" + validValue
								+ "</span>";
						columns.put("valid", coloredValidValue);

					
					}

					String Delete12 = "onclick=\"  if (confirm('Please Download') ){getDownloadImagewetpet("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String deleteButton2 = "<a " + Delete12 + ">Download</a>";

//					String Approved = "onclick=\"  if (confirm('Are you sure you want to approve?') ){Approved("
//							+ rs.getObject(9) + ")}else{ return false;}\"";
//					String appButton = "<i class='action_icons action_approve' " + Approved
//							+ " title='Approve Data'></i>";
//
					String Reject = "onclick=\"  if (confirm('Are you sure you want to Raise Observation?') ){Reject("
							+ rs.getObject(9) + ")}else{ return false;}\"";
					String rejectButton = "<i class='action_icons action_reject' " + Reject
							+ " title='Reject Data'></i>";
//
//					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){Delete1("
//							+ rs.getObject(9) + ")}else{ return false;}\"";
//					String deleteButton = "<i class='action_icons action_delete' " + Delete1
//							+ " title='Delete Data'></i>";
//
//					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update("
//							+ rs.getObject(9) + ")}else{ return false;}\"";
//					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
//
//					String appButton1 = "<i class='action_icons action_approved'  title='Approve Data'></i>";
//					String LogButton = cueContoller.Get_button_info(metaData, rs);
//					String LogButton1 = cueContoller.Get_button_info1(metaData, rs);
					String f = "";
//					if (status.equals("0")) {
//						if (roleType.equals("ALL")) {
//							f += appButton;
//							f += rejectButton;
//							f += deleteButton;
//							f += updateButton;
//							f += LogButton;
//						}
//						if (roleType.equals("APP")) {
//							f += appButton;
//							f += rejectButton;
//							f += LogButton;
//						}
//						if (roleType.equals("DEO")) {
//							f += deleteButton;
//							f += updateButton;
//							f += LogButton;
//						}
//
//						if (roleType.equals("VIEW")) {
//							f += appButton;
//							f += rejectButton;
//							f += deleteButton;
//							f += updateButton;
//							f += LogButton;
//						}

//					} else if (status.equals("1")) {
//						if (roleType.equals("APP") || roleType.equals("ALL")) {
//							f += rejectButton;
//							f += LogButton1;
//						}
//						if (roleType.equals("DEO")) {
//							f += appButton1;
//							f += LogButton1;
//						}

//					} else if (status.equals("2")) {
//						if (roleType.equals("APP") || roleType.equals("ALL")) {
//							f += appButton;
//						}
//						if (roleType.equals("DEO") || roleType.equals("ALL")) {
//
//							f += deleteButton;
//							f += updateButton;
//						}
//
//					}
					f += rejectButton;
					columns.put(metaData.getColumnLabel(13), deleteButton2);
//					columns.put(metaData.getColumnLabel(9), f);
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

	public List<cue_wet_pet> getWET_PET_FET_listid(int id) {
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

}
