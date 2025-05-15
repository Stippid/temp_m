package com.dao.psg.Transaction;

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

import javax.sql.DataSource;

import org.hibernate.Session;

import org.hibernate.Transaction;

import com.models.psg.Master.TB_BANK;

import com.models.psg.Transaction.TB_POSTING_IN_OUT;

import com.persistance.util.HibernateUtil;

public class Search_PostOutDaoImpl implements Search_PostOutDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

		this.dataSource = dataSource;

	}

	// postout search

	public ArrayList<ArrayList<String>> search_postout(String roleSusNo, String personnel_no, String rank,
			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {
				qry += "  and upper(b.personnel_no) like ? ";
			}
			if (!rank.equals("")) {
				qry += "  and r.id= ? ";
			}
			if (!to_sus_no.equals("")) {
				qry += "  and upper(p.to_sus_no) like ? ";
			}
			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}

			if (!status.equals("") && !status.equals("4")) {
				qry += "  and p.status=? ";
			}

			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {
					qry += " and ?=(select user_sus_no from logininformation where username=p.created_by ) ";
				}

				if (roleType.equals("APP")) {
					qry += " and p.cancel_status=0 ";
				}

				else

					qry += " and p.cancel_status in (-1,2) ";

				q = "select distinct p.id, b.personnel_no,b.name,r.description as rank,COALESCE(p.out_auth,'') as out_auth,\r\n"
						+

						"COALESCE(ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0'),'') as out_auth_dt,\r\n" +

						"p.to_sus_no as unit_sus_no,\r\n" +

						"ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,p.comm_id,p.reject_remarks \r\n" +

						"from tb_psg_posting_in_out p \r\n"
						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) NOT IN ('NR', 'NS')\r\n"
						+

						"inner join cue_tb_psg_rank_app_master r on r.id = b.rank\r\n"
						+ "inner join ( SELECT max(id) as id,comm_id\r\n" + "          FROM tb_psg_posting_in_out  where status != -1   \r\n"
						+ "     group by comm_id) as cin on cin.id=p.id\r\n" + "   and  cin.comm_id = p.comm_id "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where  (select count(*) from tb_psg_re_employment remp where remp.comm_id=p.comm_id and remp.date_of_tos=p.dt_of_tos)=0\r\n"
						+

						" and p.from_sus_no is not NULL and  substring(b.personnel_no, 1, 2) Not IN ('NR', 'NS') and p.from_sus_no like ? and p.status=1 "
						+ qry + " order by p.id desc ";

			}

			else {

				q = "select distinct p.id, b.personnel_no,\n" + "b.name,\n" + "r.description as rank,\n"
						+ "p.out_auth,\n"

						+ "ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\n"

						+ "p.to_sus_no as unit_sus_no,\n"

						+ "ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,p.comm_id,p.reject_remarks "

						+ "from tb_psg_posting_in_out p \n"

						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id  and substring(b.personnel_no, 1, 2) NOT IN ('NR', 'NS')\n"

						+ "inner join cue_tb_psg_rank_app_master r on r.id = b.rank  "

						+ "   left join logininformation l1 on l1.username =p.created_by \r\n"

						+ " where  substring(b.personnel_no, 1, 2) Not IN ('NR', 'NS') and p.from_sus_no like ?   "
						+ qry

						+ " order by p.id desc";

			}

			stmt = conn.prepareStatement(q);

			int j = 2;

			if (status.equals("4")) {

				stmt.setString(1, roleSusNo + "%");

			}

			else
				stmt.setString(1, roleSusNo + "%");


			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {

					stmt.setString(j, roleSusNo2);
					j += 1;

				}
			}
			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			if (!rank.equals("")) {

				stmt.setInt(j, Integer.parseInt(rank));

				j += 1;

			}

			if (!to_sus_no.equals("")) {

				stmt.setString(j, to_sus_no.toUpperCase() + "%");

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
			if (!status.equals("") && !status.equals("4")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			j++;

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_sos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('Reject' , "

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				else if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f1 = "<i class='action_icons action_reject' " + rejectData + " title='Reject Data'></i></a>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f = "<i class='action_icons action_reject' " + CancelData + " title='Cancel Data'></i></a>";

				}

				list.add(f);

				list.add(f1);

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

	// postin search

	public ArrayList<ArrayList<String>> search_postin(String roleSusNo, String personnel_no, String rank,

			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {

				qry += "  and upper(b.personnel_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (!to_sus_no.equals("")) {

				qry += "  and upper(p.from_sus_no) like ? ";

			}

			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}

			if (!status.equals("") && !status.equals("4")) {

				qry += "  and p.status=? ";

			}

			if (status.equals("4")) {

				if (!roleSusNo2.equals("")) {

					qry += " and ?=(select user_sus_no from logininformation where username=p.created_by ) ";

				}

				if (roleType.equals("APP")) {

					qry += " and p.cancel_status=0 ";

				}

				else

					qry += " and p.cancel_status in (-1,2) ";

				q = "select distinct  p.id, b.personnel_no,\r\n" +

						"b.name,\r\n" +

						"r.description as rank,\r\n" +

						"COALESCE(p.out_auth,'') as out_auth,\r\n" +

						"COALESCE(ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0'),'') as out_auth_dt,\r\n" +

						"p.to_sus_no as unit_sus_no,\r\n" +

						"ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,p.comm_id,p.reject_remarks from tb_psg_posting_in_out p \r\n"
						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) NOT IN ('NR', 'NS')  \r\n"
						+

						"inner join cue_tb_psg_rank_app_master r on r.id = b.rank \r\n"
						+ "inner join ( SELECT max(id) as id,comm_id\r\n" + "          FROM tb_psg_posting_in_out  where status != -1  \r\n"
						+ "     group by comm_id) as pin on pin.id=p.id\r\n" + "   and  pin.comm_id = p.comm_id "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where  (select count(*) from tb_psg_re_employment remp where remp.comm_id=p.comm_id and remp.date_of_tos=p.dt_of_tos)=0\r\n"
						+

						"\r\n" +

						"and p.from_sus_no is not NULL and  substring(b.personnel_no, 1, 2) Not IN ('NR', 'NS') and p.status=1 and p.to_sus_no like ? "
						+ qry + " order by p.id desc";

			}

			else {

				q = "select distinct  p.id, b.personnel_no,\r\n" + "b.name,\r\n" + "r.description as rank,\r\n"
						+ "p.out_auth,\r\n" + "ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n"
						+ "p.to_sus_no as unit_sus_no,\r\n"
						+ "ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,p.comm_id,p.reject_remarks from tb_psg_posting_in_out p \r\n"
						+ "left join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) NOT IN ('NR', 'NS') \r\n"
						+ "left join cue_tb_psg_rank_app_master r on r.id = b.rank "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where  substring(b.personnel_no, 1, 2) Not IN ('NR', 'NS') and p.to_sus_no like ?" + qry
						+ " order by p.id desc";

			}

			stmt = conn.prepareStatement(q);

			int j = 2;

			if (status.equals("4")) {
				stmt.setString(1, roleSusNo + "%");
//				j=2;
			} else {
				stmt.setString(1, roleSusNo + "%");
				// if (!qry.equals("")) {
			}

			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {
					stmt.setString(j, roleSusNo2);
					j += 1;
				}
			}
			if (!personnel_no.equals("")) {
				stmt.setString(j, personnel_no.toUpperCase() + "%");
				j += 1;
			}
			if (!rank.equals("")) {
				stmt.setInt(j, Integer.parseInt(rank));
				j += 1;
			}
			if (!to_sus_no.equals("")) {
				stmt.setString(j, to_sus_no.toUpperCase() + "%");
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
			if (!status.equals("") && !status.equals("4")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			j++;
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_tos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){InApproved("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('InReject', "

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

					/*
					 * String reject =
					 * "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('InReject', '"
					 * +rs.getString("id")+"'" + ",'" + rs.getString("comm_id") + "','" +
					 * rs.getString("unit_sus_no") + "' )}else{ return false;}\""; f1 =
					 * "<i class='action_icons action_reject' " + reject +
					 * " title='Reject Data'></i>";
					 */

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This In Posting Data ?') ){IneditData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				else if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f1 = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'REJECT'  " + rejectData
							+ "'><i class='fa fa-times'></i></a>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  " + CancelData
							+ "'><i class='fa fa-times'></i></a>";

				}

				list.add(f);

				list.add(f1);

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

	// postout search

	@Override

	public ArrayList<ArrayList<String>> search_jcopostout(String roleSusNo, String personnel_no, String rank,

			String to_sus_no, String roleType, String status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {

				qry += "  and upper(c.army_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (!to_sus_no.equals("")) {

				qry += "  and upper(p.to_sus_no) like ? ";

			}

			if (!status.equals("")) {

				qry += "  and p.status=? ";

			}

			q = "select distinct p.id, c.army_no,\r\n" +

					"c.full_name,\r\n" +

					"r.description as rank,\r\n" +

					"p.out_auth,\r\n" +

					"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" +

					"p.to_sus_no as unit_sus_no,\r\n" +

					"ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,p.census_id from tb_psg_posting_in_out_jco p \r\n"

					+

					"inner join  tb_psg_census_jco_or_p c on p.census_id=c.id \r\n" +

					"inner join cue_tb_psg_rank_app_master r on r.id = c.rank   "

					+ " where  p.from_sus_no= ?   " + qry

					+ " order by p.id desc";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, roleSusNo);

			int j = 2;

			// if (!qry.equals("")) {

			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			if (!rank.equals("")) {

				stmt.setInt(j, Integer.parseInt(rank));

				j += 1;

			}

			if (!to_sus_no.equals("")) {

				stmt.setString(j, to_sus_no.toUpperCase() + "%");

				j += 1;

			}

			if (!status.equals("")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			j++;

			// }

			ResultSet rs = stmt.executeQuery();

			System.err.println("postin----->" + stmt);
			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("army_no"));

				list.add(rs.getString("full_name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_sos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("

							+ rs.getString("id") + " ,'" + rs.getString("census_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){Reject("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				list.add(f);

				list.add(f1);

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

	// postin search

	@Override

	public ArrayList<ArrayList<String>> search_jcopostin(String roleSusNo, String personnel_no, String rank,

			String to_sus_no, String roleType, String status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {

				qry += "  and upper(b.army_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (!to_sus_no.equals("")) {

				qry += "  and upper(p.from_sus_no) like ? ";

			}

			if (!status.equals("")) {

				qry += " and  p.status=? ";

			}

			q = "select distinct  p.id, c.army_no,\r\n" +

					"c.full_name,\r\n" +

					"r.description as rank,\r\n" +

					"p.out_auth,\r\n" +

					"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" +

					"p.to_sus_no as unit_sus_no,\r\n" +

					"ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,p.census_id from tb_psg_posting_in_out_jco p \r\n"

					+

					"inner join  tb_psg_census_jco_or_p c on p.census_id=c.id \r\n" +

					"inner join cue_tb_psg_rank_app_master r on r.id = c.rank where   p.to_sus_no= ?  "

					+ qry + "  order by p.id desc";

			stmt = conn.prepareStatement(q);

			int j = 1;

			// if (!qry.equals("")) {

			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			if (!rank.equals("")) {

				stmt.setInt(j, Integer.parseInt(rank));

				j += 1;

			}

			if (!to_sus_no.equals("")) {

				stmt.setString(j, to_sus_no.toUpperCase() + "%");

				j += 1;

			}

			stmt.setString(j, roleSusNo);

			j++;

			if (!status.equals("")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			// }

			ResultSet rs = stmt.executeQuery();

			System.out.println("POSTIN---->" + stmt);

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("army_no"));

				list.add(rs.getString("full_name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_tos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){InApproved("

							+ rs.getString("id") + " ,'" + rs.getString("census_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){InReject("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This In Posting Data ?') ){IneditData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				list.add(f);

				list.add(f1);

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

	public List<Map<String, Object>> getPost_OutByid(int id, int cat) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (cat == 1) {

				q = "select p.id, b.personnel_no,\n" + "b.name,\n" + "r.description as rank,\n"
						+ "p.out_auth, p.comm_id\n"

						+ ",p.out_auth_dt,\n" + "p.to_sus_no as unit_sus_no,p.dt_of_tos,p.from_sus_no, \n"

						+ "m.unit_name,\n" + "p.dt_of_sos,p.unit_description,p.t_status,ta.label as t_status_lbl\n"

						+ "from tb_psg_posting_in_out p \n"

						+ "--inner join  tb_psg_census_detail_p c on p.census_id=c.id \n"

						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id \n"

						+ "inner join cue_tb_psg_rank_app_master r on r.id = b.rank \n"

						+ "inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\n"

						+ "inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active'  where p.id = ? ";

			}

			else if (cat == 2) {

				q = "select p.id, p.comm_id, c.army_no as personnel_no,c.full_name as name,r.description as rank,p.out_auth,\r\n"
						+

						"p.out_auth_dt,p.to_sus_no as unit_sus_no,p.from_sus_no,p.dt_of_tos,m.unit_name,p.dt_of_sos\r\n"

						+

						"from tb_psg_posting_in_out_jco p inner join  tb_psg_census_jco_or_p c on p.census_id=c.id\r\n"

						+

						"inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\n" +

						"inner join cue_tb_psg_rank_app_master r on r.id = c.rank \r\n" +

						"inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active' where p.id = ? ";

			}

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, id);

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

	public List<Map<String, Object>> getPost_InByid(int id, int cat) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (cat == 1) {

				q = "select p.id, b.personnel_no,b.name,r.description as rank, p.out_auth, p.comm_id,"

						+ "p.out_auth_dt,p.to_sus_no as unit_sus_no,m.unit_name, p.dt_of_tos,p.from_sus_no ,p.app_name,p.t_status,ta.label as t_status_lbl "

						+ " from tb_psg_posting_in_out p \n"

						+ "---inner join  tb_psg_census_detail_p c on p.census_id=c.id \n"

						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id \n"

						+ "inner join cue_tb_psg_rank_app_master r on r.id = b.rank \n"

						+ "inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\n"

						+ "inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active'  where p.id = ? ";

			}

			else if (cat == 2)

			{

				q = "select p.id, c.army_no as personnel_no,c.full_name as name,r.description as rank, p.out_auth, p.comm_id,\r\n"

						+

						"p.out_auth_dt,p.to_sus_no as unit_sus_no,m.unit_name, p.dt_of_tos,p.from_sus_no ,p.app_name,p.t_status \r\n"

						+

						"from tb_psg_posting_in_out_jco p \r\n" +

						" inner join  tb_psg_census_jco_or_p c on p.census_id=c.id \r\n" +

						"inner join cue_tb_psg_rank_app_master r on r.id = c.rank \r\n" +

						"inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active' where p.id = ? ";

			}

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, id);

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

	// get personnel no..(validation)

	public Boolean getpernoAlreadyExits(BigInteger comm_id) {

		Connection conn = null;

		Boolean msg = false;

		try {

			conn = dataSource.getConnection();

			String sql = "";

			PreparedStatement stmt = null;

			sql = "select case when count(p.id) > 0 then true else false end \n" + "from tb_psg_posting_in_out p\n"

					+ "---inner join tb_psg_census_detail_p c on p.census_id=c.id \n"

					+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id \n"

					+ "where p.status = '0' and b.id::text= ?   ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setString(1, comm_id.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				msg = rs.getBoolean("case");

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

		return msg;

	}

	public ArrayList<ArrayList<String>> getLocation_Trn(String to_sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			String qry = "";

			qry = "select distinct a.code_value as location,a.mod_desc as trn_type\r\n" +

					"from tb_miso_orbat_code a,\r\n" +

					"tb_miso_orbat_code b,\r\n" +

					"t_domain_value  c,\r\n" +

					"tb_miso_orbat_unt_dtl n \r\n" +

					"where a.code_type='Location' and b.code_type = 'Location' and n.status_sus_no in ('Active','Inactive') \r\n" +

					"and a.nrs_code = b.code and a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and c.domainid='TYPEOFLOCATION' and \r\n"
					+

					"a.nrs_code = n.nrs_code and n.sus_no = ? ";

			stmt = conn.prepareStatement(qry);

			stmt.setString(1, to_sus_no);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("location"));

				list.add(rs.getString("trn_type"));

				alist.add(list);

			}

			rs.close();

			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return alist;

	}

	public ArrayList<ArrayList<String>> getPosttenuredate(BigInteger comm_id)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "select to_char(dt_of_tos,'DD/MM/YYYY') as date from tb_psg_posting_in_out where comm_id::text=? and status='1' order by dt_of_tos desc limit 1";

			stmt = conn.prepareStatement(q);

			stmt.setString(1, comm_id.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("date"));

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

	public ArrayList<ArrayList<String>> GetCommDataApprove(BigInteger comm_id)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//// bisag 170723 v2 (without census postin postout record new changed from
			//// poonammam)
			/*
			 * q = "select  distinct\r\n" +
			 * 
			 * "c.id,\r\n" +
			 * 
			 * "cd.id as census_id,\r\n" +
			 * 
			 * "cd.marital_status as marital_status,\r\n" +
			 * 
			 * "c.name,\r\n" +
			 * 
			 * "cd.father_name,\r\n" +
			 * 
			 * "cd.father_dob,\r\n" +
			 * 
			 * "cd.mother_name,\r\n" +
			 * 
			 * "cd.mother_dob,\r\n" +
			 * 
			 * "ra.description as rank,\r\n" +
			 * 
			 * "r.description as appt,\r\n" +
			 * 
			 * "a.date_of_appointment as date_appointment,\r\n" +
			 * 
			 * "(select post.dt_of_tos  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n"
			 * +
			 * 
			 * "(select post.unit_description  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n"
			 * +
			 * 
			 * "c.unit_sus_no,\r\n" +
			 * 
			 * "i.id_card_no,\r\n" +
			 * 
			 * "re.religion_name,\r\n" +
			 * 
			 * "p.arm_desc as parent_arm,\r\n" +
			 * 
			 * "c.date_of_birth,\r\n" +
			 * 
			 * "c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" +
			 * 
			 * "fv.unit_name as command,c.regiment,cd.update_ofr_status,c.type_of_comm_granted\r\n"
			 * +
			 * 
			 * "from tb_psg_trans_proposed_comm_letter c \r\n" +
			 * 
			 * "inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n"
			 * +
			 * 
			 * "inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n"
			 * +
			 * 
			 * "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\r\n"
			 * +
			 * 
			 * "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n"
			 * +
			 * 
			 * "inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
			 * +
			 * 
			 * "left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n"
			 * +
			 * 
			 * "left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  "
			 * 
			 * + " and r.status_active = 'Active'\r\n" +
			 * 
			 * "left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" +
			 * 
			 * "left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n"
			 * +
			 * 
			 * "left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" +
			 * 
			 * "left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" +
			 * 
			 * "where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? "
			 * ;
			 * 
			 */

			q = "select  distinct  c.id,c.rank as rank_id,ra.description as rank,r.description as appt,c.name,\r\n"
					+ "a.date_of_appointment as date_appointment,\r\n"
					+ "(select post.dt_of_tos  from tb_psg_posting_in_out post where c.id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc limit 1) as dt_of_tos,\r\n"
					+ "(select post.unit_description  from tb_psg_posting_in_out post where c.id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n"
					+ "c.unit_sus_no,\r\n" + "i.id_card_no, \r\n" + "re.religion_name,\r\n"
					+ "p.arm_desc as parent_arm,\r\n" + "c.date_of_birth,\r\n"
					+ "c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n"
					+ "fv.unit_name as command,c.regiment,--cd.update_ofr_status,\r\n" + "c.type_of_comm_granted\r\n"
					+ "from tb_psg_trans_proposed_comm_letter c \r\n"
					+ "inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and UPPER(status_sus_no) IN ('INACTIVE','ACTIVE') \r\n"
					+ "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n"
					+ "inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1' \r\n"
					+ "left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  \r\n"
					+ " and r.status_active = 'Active' \r\n"
					+ "left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1  \r\n"
					+ "left join tb_psg_change_religion ret on ret.comm_id=c.id and ret.status=1\r\n"
					+ "left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n"
					+ "left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n"
					+ "where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, String.valueOf(comm_id));

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("id")); // 0

				list.add(rs.getString("name")); // 1

				list.add(rs.getString("rank_id"));// 2

				list.add(rs.getString("rank"));// 3

				list.add(rs.getString("appt"));// 4

				list.add(rs.getString("date_appointment"));// 5

				list.add(rs.getString("dt_of_tos"));// 6

				list.add(rs.getString("unit_sus_no"));// 7

				list.add(rs.getString("id_card_no"));// 8

				list.add(rs.getString("religion_name"));// 9

				list.add(rs.getString("parent_arm")); // 10

				list.add(rs.getString("date_of_birth"));// 11

				list.add(rs.getString("date_of_commission"));// 12

				list.add(rs.getString("rank_id"));// 13

				list.add(rs.getString("appoint_id"));// 14

				list.add(rs.getString("command"));// 15

				list.add(rs.getString("regiment"));// 16

				list.add(rs.getString("post_unit_desc"));// 17
				list.add("1");// 18

				list.add(rs.getString("type_of_comm_granted"));// 18

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

	public ArrayList<ArrayList<String>> search_postoutMns(String roleSusNo, String personnel_no, String rank,

			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {

				qry += "  and upper(b.personnel_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (!to_sus_no.equals("")) {

				qry += "  and upper(p.to_sus_no) like ? ";

			}
			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}

			if (!status.equals("") && !status.equals("4")) {

				qry += "  and p.status=? ";

			}

			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {

					qry += " and ?=(select user_sus_no from logininformation where username=p.created_by ) ";

				}

				if (roleType.equals("APP")) {

					qry += " and p.cancel_status=0 ";

				}

				else

					qry += " and p.cancel_status in (-1,2) ";

				q = "select distinct p.id, b.personnel_no,b.name,r.description as rank,COALESCE(p.out_auth,'') as out_auth,\r\n"
						+

						"COALESCE(ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0'),'') as out_auth_dt,\r\n" +

						"p.to_sus_no as unit_sus_no,\r\n" +

						"ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,p.comm_id,p.reject_remarks \r\n" +

						"from tb_psg_posting_in_out p \r\n"
						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) IN ('NR', 'NS')\r\n"
						+

						"inner join cue_tb_psg_rank_app_master r on r.id = b.rank\r\n"
						+ "inner join ( SELECT max(id) as id,comm_id\r\n" + "          FROM tb_psg_posting_in_out where status != -1   \r\n"
						+ "     group by comm_id) as cin on cin.id=p.id\r\n" + "   and  cin.comm_id = p.comm_id "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where  (select count(*) from tb_psg_re_employment remp where remp.comm_id=p.comm_id and remp.date_of_tos=p.dt_of_tos)=0\r\n"
						+

						"and  substring(b.personnel_no, 1, 2) IN ('NR', 'NS') and p.from_sus_no like ? and p.status=1  and p.from_sus_no is not NULL  "
						+ qry + " order by p.id desc ";

			}

			else {

				q = "select distinct p.id, b.personnel_no,\n" + "b.name,\n" + "r.description as rank,\n"
						+ "p.out_auth,\n"

						+ "ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\n"

						+ "p.to_sus_no as unit_sus_no,\n"

						+ "ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,p.comm_id,p.reject_remarks "

						+ "from tb_psg_posting_in_out p \n"

						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id  and substring(b.personnel_no, 1, 2) IN ('NR', 'NS')\n"

						+ "inner join cue_tb_psg_rank_app_master r on r.id = b.rank  "

						+ "   left join logininformation l1 on l1.username =p.created_by \r\n"

						+ " where  substring(b.personnel_no, 1, 2) IN ('NR', 'NS') and p.from_sus_no like ?   " + qry

						+ " order by p.id desc";

			}

			stmt = conn.prepareStatement(q);

			int j = 2;

			if (status.equals("4")) {

//				stmt.setString(1, roleSusNo2);

				stmt.setString(1, roleSusNo + "%");

//				j=3;

			}

			else

				stmt.setString(1, roleSusNo + "%");

			// if (!qry.equals("")) {

			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {

					stmt.setString(j, roleSusNo2);
					j += 1;

				}
			}
			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			if (!rank.equals("")) {

				stmt.setInt(j, Integer.parseInt(rank));

				j += 1;

			}

			if (!to_sus_no.equals("")) {

				stmt.setString(j, to_sus_no.toUpperCase() + "%");

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
			if (!status.equals("") && !status.equals("4")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			j++;

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_sos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('Reject' , "

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

					/*
					 * String reject =
					 * "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"
					 * +rs.getString("id")+"'" + ",'" + rs.getString("comm_id") + "','" +
					 * rs.getString("unit_sus_no") + "' )}else{ return false;}\""; f1 =
					 * "<i class='action_icons action_reject' " + reject +
					 * " title='Reject Data'></i>";
					 */

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ?') ){editData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				else if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f1 = "<i class='action_icons action_reject' " + rejectData + " title='Reject Data'></i></a>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f = "<i class='action_icons action_reject' " + CancelData + " title='Cancel Data'></i></a>";

				}

				list.add(f);

				list.add(f1);

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

	public ArrayList<ArrayList<String>> search_postinMns(String roleSusNo, String personnel_no, String rank,

			String to_sus_no, String cr_by, String cr_date, String roleType, String status, String roleSusNo2) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!personnel_no.equals("")) {

				qry += "  and upper(b.personnel_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (!to_sus_no.equals("")) {

				qry += "  and upper(p.from_sus_no) like ? ";

			}

			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}

			if (!status.equals("") && !status.equals("4")) {

				qry += "  and p.status=? ";

			}

			if (status.equals("4")) {

				if (!roleSusNo2.equals("")) {

					qry += " and ?=(select user_sus_no from logininformation where username=p.created_by ) ";

				}

				if (roleType.equals("APP")) {

					qry += " and p.cancel_status=0 ";

				}

				else

					qry += " and p.cancel_status in (-1,2) ";

				q = "select distinct  p.id, b.personnel_no,\r\n" +

						"b.name,\r\n" +

						"r.description as rank,\r\n" +

						"COALESCE(p.out_auth,'') as out_auth,\r\n" +

						"COALESCE(ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0'),'') as out_auth_dt,\r\n" +

						"p.to_sus_no as unit_sus_no,\r\n" +

						"ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,p.comm_id,p.reject_remarks from tb_psg_posting_in_out p \r\n"
						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) IN ('NR', 'NS')  \r\n"
						+

						"inner join cue_tb_psg_rank_app_master r on r.id = b.rank \r\n"
						+ "inner join ( SELECT max(id) as id,comm_id\r\n" + "          FROM tb_psg_posting_in_out  where status != -1   \r\n"
						+ "     group by comm_id) as pin on pin.id=p.id\r\n" + "   and  pin.comm_id = p.comm_id "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where  (select count(*) from tb_psg_re_employment remp where remp.comm_id=p.comm_id and remp.date_of_tos=p.dt_of_tos)=0\r\n"
						+

						"\r\n" +

						"and p.status=1  and p.from_sus_no is not NULL and substring(b.personnel_no, 1, 2) IN ('NR', 'NS') and p.to_sus_no like ? "
						+ qry + " order by p.id desc";

			}

			else {

				q = "select distinct  p.id, b.personnel_no,\r\n" + "b.name,\r\n" + "r.description as rank,\r\n"
						+ "p.out_auth,\r\n" + "ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n"
						+ "p.to_sus_no as unit_sus_no,\r\n"
						+ "ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,p.comm_id,p.reject_remarks from tb_psg_posting_in_out p \r\n"
						+ "left join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id and substring(b.personnel_no, 1, 2) IN ('NR', 'NS') \r\n"
						+ "left join cue_tb_psg_rank_app_master r on r.id = b.rank "
						+ "left join logininformation l1 on l1.username =p.created_by \r\n"
						+ "where substring(b.personnel_no, 1, 2) IN ('NR', 'NS') and p.to_sus_no like ?" + qry
						+ " order by p.id desc";

			}

			stmt = conn.prepareStatement(q);

			int j = 2;

			if (status.equals("4")) {
				stmt.setString(1, roleSusNo + "%");
//				j=2;
			} else {
				stmt.setString(1, roleSusNo + "%");
				// if (!qry.equals("")) {
			}

			if (status.equals("4")) {
				if (!roleSusNo2.equals("")) {
					stmt.setString(j, roleSusNo2);
					j += 1;
				}
			}
			if (!personnel_no.equals("")) {
				stmt.setString(j, personnel_no.toUpperCase() + "%");
				j += 1;
			}
			if (!rank.equals("")) {
				stmt.setInt(j, Integer.parseInt(rank));
				j += 1;
			}
			if (!to_sus_no.equals("")) {
				stmt.setString(j, to_sus_no.toUpperCase() + "%");
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
			if (!status.equals("") && !status.equals("4")) {

				stmt.setInt(j, Integer.parseInt(status));

				j += 1;

			}

			j++;
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("out_auth"));

				list.add(rs.getString("out_auth_dt"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dt_of_tos"));

				String f = "";

				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){InApproved("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('InReject', "

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' ,'"

							+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

					/*
					 * String reject =
					 * "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('InReject', '"
					 * +rs.getString("id")+"'" + ",'" + rs.getString("comm_id") + "','" +
					 * rs.getString("unit_sus_no") + "' )}else{ return false;}\""; f1 =
					 * "<i class='action_icons action_reject' " + reject +
					 * " title='Reject Data'></i>";
					 */

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This In Posting Data ?') ){IneditData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";

				}

				else if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "' )}else{ return false;}\"";

					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f1 = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'REJECT'  " + rejectData
							+ "'><i class='fa fa-times'></i></a>";

				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("comm_id") + "')}else{ return false;}\"";

					f = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  " + CancelData
							+ "'><i class='fa fa-times'></i></a>";

				}

				list.add(f);

				list.add(f1);

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

}
