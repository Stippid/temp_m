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
import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.models.cue_wepe;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Cue_wepe_validuptoDAOImpl implements Cue_wepe_validuptoDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	public List<Map<String, Object>> getvaliduptowepefegsl(String we_pe, String we_pe_no, String doc_type, String arm,
//			String sponsor_dire, String status, String roleType, String from_date, String to_date, String roleAccess) {
//		{
//			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//			Connection conn = null;
//			try {
//				conn = dataSource.getConnection();
//				PreparedStatement stmt = null;
//				String q = "";
//				String qry = "";
//
//				if (status != "" && status != null && status != "null") {
//					if (!status.equals("all")) {
//						qry += " and c.status =?";
//					}
//				}
//				if (we_pe != "") {
//					qry += " and c.we_pe =?";
//				}
//				if (we_pe_no != "") {
//					qry += " and c.we_pe_no =?";
//				}
//				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
//					qry += " and c.sponsor_dire =?";
//				}
//				if (arm != "" && !arm.equals("0")) {
//					qry += " and c.arm =?";
//				}
//				if (doc_type != "") {
//					qry += " and c.doc_type =?";
//				}
//				if (from_date != "" && to_date != "") {
//					qry += " and c.created_on is not null and cast(concat(substr(c.created_on,7,4),'-',substr(c.created_on,4,2),'-',substr(c.created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
//				}
//				q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,sd.line_dte_name,c.doc_type,o.arm_desc,\r\n"
//						+ "c.doc_type,c.eff_frm_date,c.eff_to_date,c.status,c.reject_letter_no,c.reject_remarks,c.doc_path,c.table_title,"
//						+ "c.created_by as cr_by,c.created_on as cr_on,c.approved_rejected_by as app_by,c.date_of_apprv_rejc as app_on,"
//						+ "c.modified_by as modi_by,c.modified_on as modi_on\r\n"
//						+ "from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o,tb_miso_orbat_line_dte sd  where c.arm=o.arm_code and line_dte_sus=sponsor_dire \r\n"
//						+ " and c.we_pe_no not in (select distinct suprcdd_we_pe_no from cue_tb_miso_wepe_upload_condition where suprcdd_we_pe_no is not null \r\n"
//						+ "											  or trim(suprcdd_we_pe_no) ='') \r\n" + qry
//						+ "order by c.we_pe,c.we_pe_no";
////                q = "select distinct c.id,c.we_pe,c.we_pe_no,c.table_title,c.suprcdd_we_pe_no,c.sponsor_dire,c.doc_type,o.arm_desc,c.doc_type,c.eff_frm_date,c.eff_to_date,c.status,c.reject_letter_no,c.reject_remarks,c.doc_path,c.table_title"
////                        + " from cue_tb_miso_wepe_upload_condition c,tb_miso_orbat_arm_code o where c.arm=o.arm_code   "
////                        + qry + " order by c.we_pe,c.we_pe_no ";
//				stmt = conn.prepareStatement(q);
//				int j = 1;
//				if (status != "" && status != null && status != "null") {
//					if (!status.equals("all")) {
//						stmt.setString(j, status);
//						j += 1;
//					}
//				}
//				if (we_pe != "") {
//					stmt.setString(j, we_pe);
//					j += 1;
//				}
//				if (we_pe_no != "") {
//					stmt.setString(j, we_pe_no);
//					j += 1;
//				}
//				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
//					stmt.setString(j, sponsor_dire);
//					j += 1;
//				}
//				if (arm != "" && !arm.equals("0")) {
//					stmt.setString(j, arm);
//					j += 1;
//				}
//				if (doc_type != "") {
//					stmt.setString(j, doc_type);
//					j += 1;
//				}
//				if (from_date != "" && to_date != "") {
//					stmt.setString(j, from_date);
//					j += 1;
//					stmt.setString(j, to_date);
//					j += 1;
//				}
//
//				ResultSet rs = stmt.executeQuery();
//				ResultSetMetaData metaData = rs.getMetaData();
//
//				int columnCount = metaData.getColumnCount();
//				while (rs.next()) {
////                	String a="";
//					Map<String, Object> columns = new LinkedHashMap<String, Object>();
//
//					for (int i = 1; i <= columnCount; i++) {
//						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
////
////                        if(metaData.getColumnLabel(i).equals("cr_by")) {
////							a+="Uploaded By :" +rs.getObject(i);
////						}
////						if(metaData.getColumnLabel(i).equals("cr_on")) {
////							a+= "\nUploaded On :" +rs.getObject(i);
////						}
//
//					}
//					String LogButton = cueContoller.Get_button_info(metaData, rs);
//					String LogButton1 = cueContoller.Get_button_info1(metaData, rs);
//
////                    		"<i class='fa fa-question-circle' " + " title=' " + a + " '></i>";
//					String Approved = "onclick=\"   if (confirm('Are you sure you want to approve?') ){Approved("
//							+ rs.getObject(1) + ")}else{ return false;}\"";
//					String appButton = "<i class='action_icons action_approve' " + Approved
//							+ " title='Approve Data'></i>";
//
//					String Reject = "onclick=\"   if (confirm('Are you sure you want to reject?') ){Reject("
//							+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
//							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
//					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
//							+ Reject + " title='Reject Data' data-toggle='modal'   ></i>";
//
//					String Delete1 = "onclick=\"   if (confirm('Are you sure you want to delete?') ){Delete1("
//							+ rs.getObject(1) + ")}else{ return false;}\"";
//					String deleteButton = "<i class='action_icons action_delete' " + Delete1
//							+ " title='Delete Data'></i>";
//
//					String Update = "onclick=\"   if (confirm('Are you sure you want to update?') ){Update("
//							+ rs.getObject(1) + ")}else{ return false;}\"";
//					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
//
//					String appButton1 = "<i class='action_icons action_approved'   title='Approve Data'></i>";
////                    if (rs.getString("doc_path") != null) {
//					String Delete12 = "onclick=\"   if (confirm('Please Download') ){getDownloadImagelist("
//							+ rs.getString("id") + ")}else{ return false;}\"";
//					String deleteButton2 = "<i " + Delete12 + ">Download</i>";
//
////					columns.put(metaData.getColumnLabel(columnCount - 1), deleteButton2);
//					columns.put(metaData.getColumnLabel(15), deleteButton2);
////                    }
//
//					String f = "";
//
//					if (status.equals("0")) {
//						if (roleType.equals("ALL")) {
//							f += appButton;
//							f += rejectButton;
//							f += deleteButton;
//							f += updateButton;
//							f += LogButton;
//
//						}
//						if (roleType.equals("APP")) {
//							f += appButton;
//							f += rejectButton;
//							f += LogButton;
//						}
//						if (roleType.equals("DEO")// || roleAccess.equals("Line_dte")
//						) {
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
//						}
//
//					} else if (status.equals("1")) {
//						if (roleType.equals("APP") || roleType.equals("ALL")) {
//							f += rejectButton;
//							f += LogButton1;
//						}
//						if (roleType.equals("DEO")) {
//							f += appButton1;
//							f += LogButton1;
//						}
//
//					} else if (status.equals("2")) {
//						if (roleType.equals("APP") || roleType.equals("ALL")) {
//							f += appButton;
//						}
//						if (roleType.equals("DEO") || roleType.equals("ALL")// || roleAccess.equals("Line_dte")
//						) {
//
//							f += deleteButton;
//							f += updateButton;
//						}
//
//					}
//
//					columns.put(metaData.getColumnLabel(1), f);
//
//					list.add(columns);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//					}
//				}
//			}
//			return list;
//		}
//	}

	public List<Map<String, Object>> getvaliduptowepefegsl(String we_pe, String we_pe_no, String doc_type, String arm,
			String sponsor_dire, String status, String roleType, String from_date, String to_date, String roleAccess) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String q = "";
				String qry = "";
				String qry1 = "";

				if (status.equals("pending")) {
					qry1 += " where c.color_indicator = 'green'";
				}else if (status.equals("resolve")) {
					qry1 += " where c.color_indicator = 'red'";
				}
				if (we_pe != "") {
					qry += " and c.we_pe =?";
				}
				if (we_pe_no != "") {
					qry += " and c.we_pe_no =?";
				}
				if (sponsor_dire != "" && !sponsor_dire.equals("0")) {
					qry += " and c.sponsor_dire =?";
				}
				if (arm != "" && !arm.equals("0")) {
					qry += " and c.arm =?";
				}
				if (doc_type != "") {
					qry += " and c.doc_type =?";
				}
				if (from_date != "" && to_date != "") {
					qry += " and c.created_on is not null and cast(concat(substr(c.created_on,7,4),'-',substr(c.created_on,4,2),'-',substr(c.created_on,1,2)) as Date) between cast(? as Date) and cast(? as Date) ";
				}


				q = "select c.* from (\r\n"
						+ "SELECT DISTINCT \r\n"
						+ "    c.id, \r\n"
						+ "    c.we_pe, \r\n"
						+ "    c.we_pe_no, \r\n"
						+ "    c.table_title, \r\n"
						+ "    c.suprcdd_we_pe_no, \r\n"
						+ "    sd.line_dte_name, \r\n"
						+ "    c.doc_type, \r\n"
						+ "    o.arm_desc, \r\n"
						+ "    c.eff_frm_date, \r\n"
						+ "    c.eff_to_date, \r\n"
						+ "    c.status, \r\n"
						+ "    c.reject_letter_no, \r\n"
						+ "    c.reject_remarks, \r\n"
						+ "    c.doc_path, \r\n"
						+ "    c.table_title, \r\n"
						+ "    c.created_by AS cr_by, \r\n"
						+ "    c.created_on AS cr_on, \r\n"
						+ "    c.approved_rejected_by AS app_by, \r\n"
						+ "    c.date_of_apprv_rejc AS app_on, \r\n"
						+ "    c.modified_by AS modi_by, \r\n"
						+ "    c.modified_on AS modi_on, \r\n"
						+ "    CASE \r\n"
						+ "        WHEN TO_DATE(c.eff_to_date, 'DD-MM-YYYY') < CURRENT_DATE THEN \r\n"
						+ "            CONCAT(\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(YEAR FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(YEAR FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))), ' years ')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END,\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(MONTH FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(MONTH FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))), ' months ')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END,\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(DAY FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(DAY FROM age(CURRENT_DATE, TO_DATE(c.eff_to_date, 'DD-MM-YYYY'))), ' days')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END\r\n"
						+ "            )\r\n"
						+ "        ELSE \r\n"
						+ "            CONCAT(\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(YEAR FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(YEAR FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)), ' years ')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END,\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(MONTH FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(MONTH FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)), ' months ')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END,\r\n"
						+ "                CASE \r\n"
						+ "                    WHEN EXTRACT(DAY FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)) > 0 THEN \r\n"
						+ "                        CONCAT(EXTRACT(DAY FROM age(TO_DATE(c.eff_to_date, 'DD-MM-YYYY'), CURRENT_DATE)), ' days')\r\n"
						+ "                    ELSE '' \r\n"
						+ "                END\r\n"
						+ "            )\r\n"
						+ "    END AS valid,\r\n"
						+ "    CASE \r\n"
						+ "        WHEN TO_DATE(c.eff_to_date, 'DD-MM-YYYY') < CURRENT_DATE THEN 'red' \r\n"
						+ "        ELSE 'green' \r\n"
						+ "    END AS color_indicator\r\n"
						+ "FROM \r\n"
						+ "    cue_tb_miso_wepe_upload_condition c\r\n"
						+ "JOIN \r\n"
						+ "    tb_miso_orbat_arm_code o ON c.arm = o.arm_code\r\n"
						+ "JOIN \r\n"
						+ "    tb_miso_orbat_line_dte sd ON line_dte_sus = sponsor_dire\r\n"
						+ "WHERE \r\n"
						+ "    c.we_pe_no NOT IN (\r\n"
						+ "        SELECT DISTINCT suprcdd_we_pe_no \r\n"
						+ "        FROM cue_tb_miso_wepe_upload_condition \r\n"
						+ "        WHERE suprcdd_we_pe_no IS NOT NULL OR TRIM(suprcdd_we_pe_no) = ''\r\n"
						+ "    ) \r\n"
						+ "    AND c.status = '1'   "+qry+" \r\n"				
					           
						+ " \r\n"
						+ " ORDER BY \r\n"
						+ "    c.we_pe, c.we_pe_no) c  "+qry1+" ";
				
				
				
				
				stmt = conn.prepareStatement(q);
				int j = 1;
			
				if (we_pe != "") {
					stmt.setString(j, we_pe);
					j += 1;
				}
				if (we_pe_no != "") {
					stmt.setString(j, we_pe_no);
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
				System.out.println("2011" + stmt);

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
//                     String a="";
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//
//                                     if(metaData.getColumnLabel(i).equals("cr_by")) {
//a+="Uploaded By :" +rs.getObject(i);
//}
//if(metaData.getColumnLabel(i).equals("cr_on")) {
//a+= "\nUploaded On :" +rs.getObject(i);
//}

						String colorIndicator = rs.getString("color_indicator");

						// Format the valid column with color
						String validValue = rs.getString("valid");
						String coloredValidValue = "<span style='color: " + colorIndicator + "'>" + validValue
								+ "</span>";
						columns.put("valid", coloredValidValue);

					}

					String LogButton = cueContoller.Get_button_info(metaData, rs);
					String LogButton1 = cueContoller.Get_button_info1(metaData, rs);

//                             "<i class='fa fa-question-circle' " + " title=' " + a + " '></i>";
					String Approved = "onclick=\"     if (confirm('Are you sure you want to approve?') ){Approved("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String appButton = "<i class='action_icons action_approve' " + Approved
							+ " title='Approve Data'></i>";

					String Reject = "onclick=\"     if (confirm('Are you sure you want to reject?') ){Reject("
							+ rs.getObject(1) + ");$('#rrr" + rs.getObject(1)
							+ "').attr('data-target','#rejectModal')}else{ return false;}\"";
					String rejectButton = "<i id='rrr" + rs.getObject(1) + "' class='action_icons action_reject' "
							+ Reject + " title='Reject Data' data-toggle='modal'     ></i>";

					String Delete1 = "onclick=\"     if (confirm('Are you sure you want to delete?') ){Delete1("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";

					String Update = "onclick=\"     if (confirm('Are you sure you want to update?') ){Update("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

					String appButton1 = "<i class='action_icons action_approved'     title='Approve Data'></i>";
                            if (rs.getString("doc_path") != null) {
					String Delete12 = "onclick=\"     if (confirm('Please Download') ){getDownloadImagelist("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String deleteButton2 = "<i " + Delete12 + ">Download</i>";

					// columns.put(metaData.getColumnLabel(columnCount - 1), deleteButton2);
					columns.put(metaData.getColumnLabel(14), deleteButton2);
                            }

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
						if (roleType.equals("DEO")// || roleAccess.equals("Line_dte")
						) {
							f += deleteButton;
							f += updateButton;
							f += LogButton;
						}

						if (roleType.equals("VIEW")) {
							f += appButton;
							f += rejectButton;
							f += deleteButton;
							f += updateButton;
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
						if (roleType.equals("DEO") || roleType.equals("ALL")// || roleAccess.equals("Line_dte")
						) {

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

	public List<cue_wepe> getWe_Pe_DownloadByid(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from cue_wepe where id=:id");
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<cue_wepe> list = (List<cue_wepe>) q.list();
		tx.commit();
		session.close();
		return list;
	}

}
