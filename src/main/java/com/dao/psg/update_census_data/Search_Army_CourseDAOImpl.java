package com.dao.psg.update_census_data;

import java.math.BigInteger;
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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.models.psg.update_census_data.TB_CENSUS_ARMY_COURSE;
import com.persistance.util.HibernateUtil;

public class Search_Army_CourseDAOImpl implements Search_Army_CourseDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> Search_ArmyCourseList(String personnel_no, String status, String unit_sus_no,
			String unit_name, String rank, String cr_by, String cr_date, String roleSusNo, String roleType) {

		System.err.println("--------------------" + roleType);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (status.equals("0")) {
				qry += " cdn.status = '0'  "; // and cl.update_ofr_status =
												// '0'
			} else if (status.equals("1")) {
				qry += " cdn.status = '1' ";
			} else if (status.equals("3")) {
				qry += " cdn.status = '3' ";
			} else if (status.equals("4")) {
				qry += "  cdn.status in (1,2) ";
				if (roleType.equals("DEO")) {
				qry += "and  cdn.cancel_status in (-1,2)  ";
				}  if (roleType.equals("APP")) {
					qry += "and  cdn.cancel_status=0  ";
				}
			}

			if (!roleSusNo.equals("")) {
				qry += " and pro.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			

			if (!personnel_no.equals("")) {
				qry += "  and upper(pro.personnel_no) like ? ";
			}

			if (!rank.equals("")) {
				qry += "  and pro.rank = cast(? as integer) ";
			}

			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}
			if (!cr_date.equals("")) {
				qry += " and cast(cdn.created_on as date) = cast(? as date)";
			}

			if (status.equals("4")) {
				q = "SELECT\n" + 
						"    MAX(cdn.id) AS id,\n" + 
						"    MAX(cdn.cancel_status) AS cancel_status,\n" + 
						"    MAX(cdn.comm_id) AS comm_id,\n" + 
						"    MAX(cdn.reject_remarks) AS reject_remarks,\n" + 
						"    STRING_AGG(DISTINCT cdn.course_name, ', ') AS course_names,\n" + 
						"    STRING_AGG(DISTINCT I.course_institute, ', ') AS course_institutes,\n" + 
						"    pro.personnel_no,\n" + 
						"    MAX(cdn.authority) AS authority, MAX(LTRIM(TO_CHAR(cdn.date_of_authority, 'DD-MON-YYYY'), '0')) AS date_of_authority\n" + 
						"FROM\n" + 
						"    tb_psg_census_army_course cdn\n" + 
						"    INNER JOIN tb_psg_trans_proposed_comm_letter pro ON cdn.comm_id = pro.id\n" + 
						"    INNER JOIN tb_psg_mstr_course_institute I ON cdn.course_institute = I.id\n" + 
						"    INNER JOIN tb_psg_mstr_div_grade DG ON cdn.div_grade::int = DG.id\n" + 
						"    INNER JOIN t_domain_value CT ON cdn.course_type = CT.codevalue AND CT.domainid = 'COURSE_TYPE'\n" + 
						"    INNER JOIN cue_tb_psg_rank_app_master cue ON CAST(cue.id AS INTEGER) = pro.rank AND cue.status_active = 'Active'\n" + 
						"    INNER JOIN tb_miso_orbat_unt_dtl Un ON Un.sus_no = pro.unit_sus_no AND Un.status_sus_no = 'Active'\n" + 
						"    LEFT JOIN logininformation l1 ON l1.username = cdn.created_by\n" + 
						"WHERE\n" + 
						qry +
						"GROUP BY\n" + 
						"    pro.personnel_no\n" + 
						"ORDER BY\n" + 
						"    MAX(cdn.id) DESC";
			} else {
				q = "SELECT\n" + 
						"    MAX(cdn.id) AS id,\n" + 
						"    MAX(cdn.comm_id) AS comm_id,\n" + 
						"    MAX(cdn.reject_remarks) AS reject_remarks,\n" + 
						"    STRING_AGG(DISTINCT cdn.course_name, ', ' ORDER BY cdn.course_name) AS course_names,\n" + 
						"    STRING_AGG(DISTINCT I.course_institute, ', ' ORDER BY I.course_institute) AS course_institutes,\n" + 
						"    pro.personnel_no,\n" + 
						"    MAX(cdn.authority) AS authority,\n" + 
						"    MAX(LTRIM(TO_CHAR(cdn.date_of_authority, 'DD-MON-YYYY'), '0')) AS date_of_authority\n" + 
						"FROM\n" + 
						"    tb_psg_census_army_course cdn\n" + 
						"    INNER JOIN tb_psg_trans_proposed_comm_letter pro ON cdn.comm_id = pro.id\n" + 
						"    INNER JOIN tb_psg_mstr_course_institute I ON cdn.course_institute = I.id\n" + 
						"    INNER JOIN tb_psg_mstr_div_grade DG ON cdn.div_grade::int = DG.id\n" + 
						"    INNER JOIN t_domain_value CT ON cdn.course_type = CT.codevalue AND CT.domainid = 'COURSE_TYPE'\n" + 
						"    INNER JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no = pro.unit_sus_no\n" + 
						"    INNER JOIN cue_tb_psg_rank_app_master cue ON CAST(cue.id AS INTEGER) = pro.rank and cue.status_active = 'Active'\n" + 
						"	INNER JOIN tb_miso_orbat_unt_dtl Un ON Un.sus_no = pro.unit_sus_no and Un.status_sus_no='Active'\n" + 
						"    LEFT JOIN logininformation l1 ON l1.username = cdn.created_by\n" + 
						"WHERE\n" + 
						qry+
						"GROUP BY\n" + 
						"    pro.personnel_no\n" + 
						"ORDER BY\n" + 
						"    MAX(cdn.id) DESC";
			}
			stmt = conn.prepareStatement(q);

			int j = 1;
			{
				if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				
				if (!personnel_no.equals("")) {
					stmt.setString(j, personnel_no.toUpperCase() + "%");
					j += 1;
				}
				if (!rank.equals("")) {
					stmt.setString(j, rank);
					j += 1;
				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
					stmt.setString(j, cr_date);
					j += 1;

				}
			}

			System.err.println("S/A army: \n"+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("personnel_no"));
				list.add(rs.getString("course_names"));
				list.add(rs.getString("course_institutes"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				
				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				if ((roleType.equals("ALL") || roleType.equals("DEO")) && status.equals("0") || status.equals("3")) {
					String editData = "onclick=\"  if (confirm('Are You Sure You Want to Update this Data ?')){editData("
							+ rs.getString("id") + ",'" + rs.getString("personnel_no") + "')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + editData + " title='Edit Data'></i>";

				}
				if ((roleType.equals("ALL") || roleType.equals("APP")) && status.equals("0")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved('"
							+ rs.getString("id") + "','" + rs.getString("personnel_no") + "','"
							+ rs.getString("comm_id") + "')}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

				/*	String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"
							+ rs.getString("comm_id") + "')}else{ return false;}\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";*/
				}
				if ((roleType.equals("ALL") || roleType.equals("DEO")) && status.equals("4")) {

					String View1 = "onclick=\"  {ViewHistoryInactiveData_FN('"+rs.getString("comm_id")+"','" + rs.getString("personnel_no") + "')}\"";
					f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4"))

				{
					String View1 = "onclick=\"  {ViewCancelHistoryData('"+rs.getString("comm_id")+"','" + rs.getString("personnel_no") + "')}\"";
						f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";

					String View2 = "onclick=\" {ViewData('" + rs.getString("comm_id") + "','"+ rs.getString("personnel_no") + "')}\"";

					f2 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

			//}

				}
				if ((roleType.equals("ALL") || roleType.equals("DEO") || roleType.equals("APP")) && status.equals("1")) {
				String View = "onclick=\" {ViewData('"+rs.getString("comm_id")+"','" + rs.getString("personnel_no") + "')} \"";
				f1 = "<i class='fa fa-eye' "+View+" title='View Data' ></i>";
				}
				
				
				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(rs.getString("reject_remarks"));
				alist.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
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

	public List<Map<String, Object>> getHisChangeOfArmyCourse(BigInteger comm_id, int status, HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String accesslvl = session.getAttribute("roleAccess").toString();
		System.err.println("----------------" + accesslvl);
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			// String flag = "Y";

			if (status == -1)
				q = "A.cancel_status in (-1,2)";
			else if (status == 0) {
				q = "A.cancel_status=0";
				if (!accesslvl.equals("MISO")) {
					q += "(select user_sus_no from logininformation where username=A.created_by )!='' ";
				}
			}

			q = "SELECT\n"
					+ " A.id, A.AUTHORITY, (select user_sus_no from logininformation where username=A.created_by ) as unit_sus,\n"
					+ "	A.COURSE_ABBREVIATION,\n"
					+ "	LTRIM(TO_CHAR(A.DATE_OF_AUTHORITY, 'DD-MON-YYYY'), '0') AS DATE_OF_AUTHORITY,\n"
					+ "	A.COURSE_NAME,\n"
					+ "	CASE\n"
					+ "		WHEN INS.COURSE_INSTITUTE = 'OTHERS' THEN A.COURSE_INSTITUTE_OTHER\n"
					+ "		ELSE INS.COURSE_INSTITUTE\n"
					+ "	END AS COURSE_INSTITUTE,\n"
					+ "	CASE\n"
					+ "		WHEN D.DIV = 'OTHERS' THEN A.AR_COURSE_DIV_OT\n"
					+ "		ELSE D.DIV\n"
					+ "	END AS DIV_GRADE,\n"
					+ "	CASE\n"
					+ "		WHEN T.LABEL = 'OTHERS' THEN A.COURSE_TYPE_OT\n"
					+ "		ELSE T.LABEL\n"
					+ "	END AS COURSE_TYPE,\n"
					+ "	LTRIM(TO_CHAR(A.START_DATE, 'DD-MON-YYYY'), '0') AS START_DATE,\n"
					+ "	LTRIM(TO_CHAR(A.DATE_OF_COMPLETION, 'DD-MON-YYYY'), '0') AS DATE_OF_COMPLETION,\n"
					+ "	A.CREATED_BY,\n"
					+ "	LTRIM(TO_CHAR(A.CREATED_ON, 'DD-MON-YYYY'), '0') AS CREATED_ON,\n"
					+ "	A.MODIFY_BY,\n"
					+ "	LTRIM(TO_CHAR(A.MODIFY_ON, 'DD-MON-YYYY'), '0') AS MODIFY_ON\n"
					+ "FROM\n"
					+ "	TB_PSG_CENSUS_ARMY_COURSE A\n"
					+ "	INNER JOIN TB_PSG_MSTR_DIV_GRADE D ON D.ID::TEXT = A.DIV_GRADE\n"
					+ "	INNER JOIN T_DOMAIN_VALUE T ON T.CODEVALUE = A.COURSE_TYPE\n"
					+ "	AND T.DOMAINID = 'COURSE_TYPE'\n"
					+ "	INNER JOIN TB_PSG_MSTR_COURSE_INSTITUTE INS ON INS.ID = A.COURSE_INSTITUTE\n"
					+ "	AND INS.STATUS = 'active'\n"
					+ "WHERE\n"
					+ "	A.STATUS IN (1, 2)\n"
					+ "	AND CAST(A.COMM_ID AS CHARACTER VARYING) = ?\n"
					+ "	AND \n"
					+ q
					+ " ORDER BY\n"
					+ "	A.ID";
			
//			q = "SELECT \n"
//					+ "    MIN(A.id) AS id,\n"
//					+ "    MIN(A.AUTHORITY) AS AUTHORITY,\n"
//					+ "    MIN((select user_sus_no from logininformation where username=A.created_by )) as unit_sus,\n"
//					+ "    pro.PERSONNEL_NO,\n"
//					+ "    STRING_AGG(DISTINCT A.COURSE_NAME, ', ') AS COURSE_NAME,\n"
//					+ "    STRING_AGG(DISTINCT A.COURSE_ABBREVIATION, ', ') AS COURSE_ABBREVIATION,\n"
//					+ "    STRING_AGG(DISTINCT \n"
//					+ "        CASE\n"
//					+ "            WHEN INS.COURSE_INSTITUTE = 'OTHERS' THEN A.COURSE_INSTITUTE_OTHER\n"
//					+ "            ELSE INS.COURSE_INSTITUTE\n"
//					+ "        END, ', ') AS COURSE_INSTITUTE,\n"
//					+ "    STRING_AGG(DISTINCT \n"
//					+ "        CASE\n"
//					+ "            WHEN T.LABEL = 'OTHERS' THEN A.COURSE_TYPE_OT\n"
//					+ "            ELSE T.LABEL\n"
//					+ "        END, ', ') AS COURSE_TYPE,\n"
//					+ "    STRING_AGG(DISTINCT LTRIM(TO_CHAR(A.START_DATE, 'DD-MON-YYYY'), '0'), ', ') AS START_DATE,\n"
//					+ "    STRING_AGG(DISTINCT LTRIM(TO_CHAR(A.DATE_OF_COMPLETION, 'DD-MON-YYYY'), '0'), ', ') AS DATE_OF_COMPLETION,\n"
//					+ "    MIN(A.CREATED_BY) AS CREATED_BY,\n"
//					+ "    MIN(LTRIM(TO_CHAR(A.CREATED_ON, 'DD-MON-YYYY'), '0')) AS CREATED_ON,\n"
//					+ "    MIN(A.MODIFY_BY) AS MODIFY_BY,\n"
//					+ "    MIN(LTRIM(TO_CHAR(A.MODIFY_ON, 'DD-MON-YYYY'), '0')) AS MODIFY_ON,\n"
//					+ "    MIN(CASE\n"
//					+ "        WHEN D.DIV = 'OTHERS' THEN A.AR_COURSE_DIV_OT\n"
//					+ "        ELSE D.DIV\n"
//					+ "    END) AS DIV_GRADE,\n"
//					+ "    MIN(LTRIM(TO_CHAR(A.DATE_OF_AUTHORITY, 'DD-MON-YYYY'), '0')) AS DATE_OF_AUTHORITY\n"
//					+ "FROM \n"
//					+ "    TB_PSG_CENSUS_ARMY_COURSE A\n"
//					+ "    INNER JOIN tb_psg_trans_proposed_comm_letter pro ON A.comm_id = pro.id\n"
//					+ "    INNER JOIN TB_PSG_MSTR_DIV_GRADE D ON D.ID::TEXT = A.DIV_GRADE\n"
//					+ "    INNER JOIN T_DOMAIN_VALUE T ON T.CODEVALUE = A.COURSE_TYPE\n"
//					+ "    AND T.DOMAINID = 'COURSE_TYPE'\n"
//					+ "    INNER JOIN TB_PSG_MSTR_COURSE_INSTITUTE INS ON INS.ID = A.COURSE_INSTITUTE\n"
//					+ "    AND INS.STATUS = 'active'\n"
//					+ "WHERE \n"
//					+ "    A.STATUS IN (1, 2)\n"
//					+ "    AND CAST(A.COMM_ID AS CHARACTER VARYING) = ?\n"
//					+ "    AND \n"
//					+ q
//					+ "GROUP BY \n"
//					+ "    pro.PERSONNEL_NO\n"
//					+ "ORDER BY \n"
//					+ "    pro.PERSONNEL_NO;";

			stmt = conn.prepareStatement(q);
			System.err.println("-----------------------" + stmt);
			stmt.setString(1, comm_id.toString());

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if (rs.getObject(i) == null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisCDAData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  " + cancelData
						+ "'><i class='fa fa-times'></i></a>";
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisCDAData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String rejectbtn = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'Reject'  " + rejectData
						+ "'><i class='fa fa-times'></i></a>";
				if (status == -1) {
					if ((rs.getString("unit_sus") == null || rs.getString("unit_sus").equals(""))
							&& !accesslvl.equals("MISO"))
						columns.put("action", "");
					else
						columns.put("action", cancelbtn);
				}
				if (status == 0) {
					columns.put("action", rejectbtn);
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
	
	public TB_CENSUS_ARMY_COURSE getseracharmyid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_CENSUS_ARMY_COURSE updateid = (TB_CENSUS_ARMY_COURSE) sessionHQL.get(TB_CENSUS_ARMY_COURSE.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	
	public ArrayList<ArrayList<String>> Popup_Change_of_armycourse(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			q= " SELECT DISTINCT\n" + 
					"					   cdn.id, \n" + 
					"					   STRING_AGG(DISTINCT cdn.course_name, ', ') AS course_names, \n" + 
					"					    STRING_AGG(DISTINCT I.course_institute, ', ') AS course_institutes, \n" + 
					"					    \n" + 
					"					    LTRIM(TO_CHAR(cdn.start_date, 'DD-MON-YYYY'), '0') AS start_date,\n" + 
					"						LTRIM(TO_CHAR(cdn.date_of_completion, 'DD-MON-YYYY'), '0') AS completion_date\n" + 
					"					FROM\n" + 
					"					    tb_psg_census_army_course cdn \n" + 
					"					    INNER JOIN tb_psg_trans_proposed_comm_letter pro ON cdn.comm_id = pro.id \n" + 
					"					   INNER JOIN tb_psg_mstr_course_institute I ON cdn.course_institute = I.id \n" + 
					"					    INNER JOIN tb_psg_mstr_div_grade DG ON cdn.div_grade::int = DG.id\n" + 
					"					    INNER JOIN t_domain_value CT ON cdn.course_type = CT.codevalue AND CT.domainid = 'COURSE_TYPE' \n" + 
					"					    INNER JOIN cue_tb_psg_rank_app_master cue ON CAST(cue.id AS INTEGER) = pro.rank AND cue.status_active = 'Active'\n" + 
					"					    INNER JOIN tb_miso_orbat_unt_dtl Un ON Un.sus_no = pro.unit_sus_no AND Un.status_sus_no = 'Active' \n" + 
					"						where cdn.status in (1,2) and cdn.comm_id::text =? \n" + 
					"								  GROUP BY\n" + 
					"					 cdn.id\n" + 
					"					ORDER BY \n" + 
					"					   cdn.id DESC";
			
					
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,comm_id.toString());
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("course_names"));
					list.add(rs.getString("course_institutes"));//1
					list.add(rs.getString("start_date"));//2
					list.add(rs.getString("completion_date"));//3
					
					
					
					alist.add(list);
	 	        }
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
				//throw new RuntimeException(e);
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

}
