package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class Search_UpdateComm_DaoImpl implements Search_UpdateComm_Dao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> Search_S_C_LData(String personnel_no, String status, String rank,
			String unit_sus_no, String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType,String roleAccess,Boolean IsMns) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String co_status = " and (tp.status='1' or tp.status = '5')";
			qry = co_status + " and tp.update_comm_status = ?";

			if (!roleSusNo.equals("") && !roleAccess.equals("DGMS")) {
				qry += " and tp.unit_sus_no = ?";
			}
			if(IsMns == true) {
				qry += " and substr(tp.personnel_no,1,2) in ('NR','NS')";
			}else {
				qry += " and substr(tp.personnel_no,1,2) Not in ('NR','NS')";
			}
			
		
			if (!unit_sus_no.equals("") ) {
				qry += "  and orb.sus_no = ?";
			}
			if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}

			if (!personnel_no.equals("")) {
				qry += "  and upper(tp.personnel_no) like ? ";
			}

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {
				qry += " and cast(r.id  as character varying) = ?";
			}

			
			 if(!cr_by.equals("")) {
				qry += " and cast(l1.userid as character varying) = ? ";				
					
				}
			  
			  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
					
				  qry += " and cast(tp.created_date as date) = cast(? as date)";	
					
					
					
				}
			
			q = "select distinct \r\n" + 
					"tp.id ,\r\n" + 
					"tp.cadet_no,\r\n" + 
					"tp.personnel_no,\r\n" + 
					"r.description as rank,\r\n" + 
					"tp.name,\r\n" + 
					"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
					"arm.arm_desc as parent_arm,\r\n" + 
					"tp.status,\r\n" + 
					"mc.course_name,tp.modified_date  \r\n" + 
					"FROM tb_psg_trans_proposed_comm_letter tp \r\n" + 
					"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank and r.status_active = 'Active'\r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and UPPER(status_sus_no) IN ('INACTIVE','ACTIVE') \r\n" + 
					"left join tb_psg_mstr_course_comm mc on mc.id=tp.course \r\n" + 
					"left join logininformation l1 on l1.username = tp.created_by " + "where tp.id !=  0  " + qry
					+ " order by tp.modified_date desc";

			stmt = conn.prepareStatement(q);

			if (!qry.equals("")) {
				int j = 1;
				if (!status.equals("")) {
					stmt.setInt(j, Integer.parseInt(status));
					j += 1;
				}

				if (!roleSusNo.equals("") && !roleAccess.equals("DGMS")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}

				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!unit_name.equals("")) {
					stmt.setString(j, unit_name);
					j += 1;
				}
				if (!personnel_no.equals("")) {
					stmt.setString(j, personnel_no.toUpperCase() + "%");
					j += 1;
				}
				if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {
					stmt.setString(j, rank);
					j += 1;
				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;
				}
				 if(!cr_date.equals("")  &&  !cr_date.equals("DD/MM/YYYY")) {
					stmt.setString(j, cr_date);
					j += 1;
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("cadet_no"));// 1
				list.add(rs.getString("personnel_no"));// 2
				list.add(rs.getString("rank"));// 3
				list.add(rs.getString("name"));// 4
				list.add(rs.getString("date_of_birth"));// 5
				list.add(rs.getString("parent_arm"));// 6
				list.add(rs.getString("course_name"));// 7

				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")) {
					
					String Approve = "onclick=\"ViewData(" + rs.getString("id") + ") \"";
					f4 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
				
				}
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3")) {
					String View1 = "onclick=\" AppViewData(" + rs.getString("id") + ")";
							
				}

				if (roleType.equals("ALL") || status.equals("1")) {

					String Approve = "onclick=\" AppViewData(" + rs.getString("id") + ")\"";
					f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";
				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3"))) {
					String Reject = "onclick=\" Reject(" + rs.getString("id") + ") \"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0"))) {
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				}

				list.add(f); // 8
				list.add(f1); // 9
				list.add(f2); // 10
				list.add(f3); // 11
				list.add(f4); // 11
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

	public ArrayList<ArrayList<String>> GetCommissionDataApprove(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct\r\n" + "c.id,\r\n" + "c.name,\r\n" + "ra.description as rank,\r\n"
					+ "r.description as appt,\r\n" + "a.date_of_appointment as date_appointment,\r\n"
					+ "c.date_of_tos,\r\n" + "c.unit_sus_no,\r\n" + "p.arm_desc as parent_arm,\r\n"
					+ "c.date_of_birth,\r\n" + "c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n"
					+ "fv.unit_name as command,c.regiment\r\n" + "from tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and UPPER(status_sus_no) IN ('INACTIVE','ACTIVE') \r\n"
					+ "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n"
					+ "inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n"
					+ "left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'    and r.status_active = 'Active'\r\n"
					+ "left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n"
					+ "where (c.status='1' or c.status='5') and  cast(c.id as character varying)=?  ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));

			ResultSet rs = stmt.executeQuery();
		
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("name")); // 1
				list.add(rs.getString("rank"));// 2
				list.add(rs.getString("appt"));// 3
				list.add(rs.getString("date_appointment"));// 4
				list.add(rs.getString("date_of_tos"));// 5
				list.add(rs.getString("unit_sus_no"));// 6
				list.add(rs.getString("parent_arm"));// 7
				list.add(rs.getString("date_of_birth"));// 8
				list.add(rs.getString("date_of_commission"));// 9
				list.add(rs.getString("rank_id"));// 10
				list.add(rs.getString("appoint_id"));// 11
				list.add(rs.getString("command"));// 12
				list.add(rs.getString("regiment"));// 13

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

	public ArrayList<ArrayList<String>> gettenuredate(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select p.id,to_char(p.tenure_date,'DD/MM/YYYY') as tenure_date from tb_psg_posting_in_out p where cast(comm_id as character varying)=? "
					+ "and from_sus_no is null order by p.id";

			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("tenure_date"));
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
}
