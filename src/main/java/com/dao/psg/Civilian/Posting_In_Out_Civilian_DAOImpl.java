package com.dao.psg.Civilian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Civilian.TB_POSTING_IN_OUT_CIVILIAN;
import com.persistance.util.HibernateUtil;

public class Posting_In_Out_Civilian_DAOImpl implements Posting_In_Out_Civilian_DAO{
private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> GetCivilianOrCensusDataApprove(int civ_id) throws SQLException {
				
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			// bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam)
			
			
			q = "select  distinct 	\r\n" + 
					"c.id,  \r\n" + 
					"(c.first_name || ' ' ||  c.middle_name || ' ' || c.last_name) as name,\r\n" + 
					"c.father_name,\r\n" + 
					"c.mother_name, \r\n" + 
					"c.dob,\r\n" + 
					"(select post.dt_of_tos  from tb_psg_posting_in_out_civilian post where c.id = post.civ_id  and post.to_sus_no = c.sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n" + 
					"(select post.unit_description  from tb_psg_posting_in_out_civilian post where c.id = post.civ_id  and post.to_sus_no = c.sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc, \r\n" + 
					"c.sus_no,\r\n" + 
					"c.employee_no,\r\n" + 
					"ra.description as rank,\r\n" + 
					"c.religion, \r\n" + 
					"t.trade as trade,\r\n" + 
					"c.pay_level as pay_level,\r\n" +
					"c.joining_date_gov_service, \r\n" + 
					"c.status \r\n" + 
					"from tb_psg_civilian_dtl_main c\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no \r\n" + 
					"left join cue_tb_psg_rank_app_master ra on  ra.id = c.designation\r\n" + 
					"left join tb_psg_mstr_trade_jco t on t.id = c.classification_trade\r\n" + 
					"where (c.status='1') and UPPER(status_sus_no) in ('INACTIVE','ACTIVE') and c.id = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, civ_id);
			
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
	
	public Boolean getCivilianPernoAlreadyExits(int civ_id) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select case when count(p.id) > 0 then true else false end from tb_psg_posting_in_out_civilian p \r\n" + 
					"inner join tb_psg_civilian_dtl_main c on p.civ_id=c.id where p.status = '0' and c.id = ? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, civ_id);
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
	
	public ArrayList<ArrayList<String>> getPostTenureDateCivilian(int civ_id){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
			try{			
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;		
					q="select to_char(dt_of_tos,'DD/MM/YYYY') as date from tb_psg_posting_in_out_civilian where civ_id=? and status='1' order by dt_of_tos desc limit 1" ;				
				stmt=conn.prepareStatement(q);
				stmt.setInt(1, civ_id);
				ResultSet rs = stmt.executeQuery();   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("date"));
		    	  alist.add(list);
			     }	
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
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
	
	 /*************PostOut Search************/
	
	public ArrayList<ArrayList<String>>Search_Postout_Civilian(String roleSusNo, String employee_no, String rank,
			String to_sus_no, String roleType, String status, String cr_by,String cr_date,String roleSusNo2) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!employee_no.equals("")) {
				qry += "  and upper(c.employee_no) like ? ";
			}
			if (!rank.equals("")) {
				qry += "  and r.id= ? ";
			}
			if (!to_sus_no.equals("")) {
				qry += "  and upper(p.to_sus_no) like ? ";
			}
			if (!status.equals("") && !status.equals("4")) {
				qry += "  and p.status=? ";
			}
			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}
			if (status.equals("4")) {
				if (roleType.equals("APP")) {
					qry += " and p.cancel_status=0 ";
				}
			else
				qry += " and p.cancel_status in (-1,2) ";
				
				q = "select distinct p.id, c.employee_no,\r\n" + 
						"first_name || ' ' || middle_name || ' ' || last_name as name,\r\n" + 
						"coalesce(r.description,'Not Fill') as rank,\r\n" + 
						"p.out_auth,\r\n" + 
						"TO_CHAR(p.out_auth_dt,'DD-MON-YYYY') as out_auth_dt,\r\n" + 
						"p.to_sus_no as sus_no,\r\n" + 
						"TO_CHAR(p.dt_of_sos,'DD-MON-YYYY') as dt_of_sos,\r\n" + 
						"p.civ_id, p.dt_of_tos as  dt_of_tosT, \r\n" + 
						"p.reject_remarks,p.from_sus_no as from_sus_no\r\n" + 
						"from tb_psg_posting_in_out_civilian p \r\n" + 
						"inner join  tb_psg_civilian_dtl_main c on p.civ_id = c.id \r\n" + 
						"left join cue_tb_psg_rank_app_master r on r.id = c.designation \r\n" +
						"left join logininformation l1 on l1.username =p.created_by\r\n" + 
						"where p.id = (select id from tb_psg_posting_in_out_civilian  where civ_id=p.civ_id  order by id desc limit 1)\r\n" + 
						"and ? =(select user_sus_no from logininformation where username=p.created_by )\r\n" + 
						"and  p.from_sus_no like ? and p.status=1 " + qry + " order by p.id desc ";
			}
			else {
				q = "select distinct p.id, c.employee_no,\r\n" + 
						"first_name || ' ' || middle_name || ' ' || last_name as name,\r\n" + 
						"coalesce(r.description,'Not Fill') as rank,\r\n" + 
						"p.out_auth,\r\n" + 
						"TO_CHAR(p.out_auth_dt,'DD-MON-YYYY') as out_auth_dt,\r\n" + 
						"p.to_sus_no as sus_no,\r\n" + 
						"TO_CHAR(p.dt_of_sos,'DD-MON-YYYY') as dt_of_sos,\r\n" + 
						"p.civ_id, p.dt_of_tos as  dt_of_tosT, \r\n" + 
						"p.reject_remarks,p.from_sus_no as from_sus_no\r\n" + 
						"from tb_psg_posting_in_out_civilian p \r\n" + 
						"inner join  tb_psg_civilian_dtl_main c on p.civ_id = c.id \r\n" + 
						"left join cue_tb_psg_rank_app_master r on r.id = c.designation \r\n" + 
						"left join logininformation l1 on l1.username =p.created_by\r\n" + 
						"where p.from_sus_no like ? " + qry +
						"order by p.id desc";
			}				
			stmt = conn.prepareStatement(q);
			int j = 2;
			if (status.equals("4")) {
				stmt.setString(1, roleSusNo2);
				stmt.setString(2, roleSusNo + "%");
				j = 3;
			}
			else
			stmt.setString(1, roleSusNo + "%");
			if (!employee_no.equals("")) {
				stmt.setString(j, employee_no.toUpperCase() + "%");
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
			if (!status.equals("") && !status.equals("4")) {
				stmt.setInt(j, Integer.parseInt(status));
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
			j++;
			
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("employee_no")); //1
				list.add(rs.getString("name")); //2
				list.add(rs.getString("rank")); //3
				list.add(rs.getString("out_auth")); //4
				list.add(rs.getString("out_auth_dt")); //5
				list.add(rs.getString("sus_no")); //6
				list.add(rs.getString("dt_of_sos")); //7
				list.add(rs.getString("reject_remarks"));//8 
				

				String f = "";
				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("
							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"
							+ rs.getString("sus_no") + "' ,'" + rs.getString("dt_of_tosT").substring(0,10) + "' )}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";
					
					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('Reject', "+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"+ rs.getString("sus_no") + "')}else{ return false;}\"";		
							
					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";
				}
				else if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0") || status.equals("3")))
				{
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update ?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String delete = "onclick=\"  if (confirm('Are You Sure you want to Delete ?') ){deletePostData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f += "<i class='action_icons action_delete' " + delete + " title='Delete Data' ></i>";
				}
				else if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){ApprovedCancelHisData("
							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"
							+ rs.getString("sus_no") + "' )}else{ return false;}\"";
					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";
					
					
					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data ?') ){addRemarkModel('RejectCancelHisData', "+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "')}else{ return false;}\"";
					
					f1 = "<i class='action_icons action_reject' " + rejectData + " title='Reject Data'></i></a>";
				}
				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))
				{
					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data ?') ){RejectCancelHisData("
							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "')}else{ return false;}\"";
					f = "<i class='action_icons action_reject' " + CancelData + " title='Cancel Data'></i></a>";
				}
				list.add(f);//9
				list.add(f1);//10
				list.add(rs.getString("from_sus_no"));//11 
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
	
	/*************PostIN Search************/

	public ArrayList<ArrayList<String>>Search_PostIn_Civilian(String roleSusNo, String employee_no, String rank,
			String to_sus_no, String roleType, String status,String cr_by,String cr_date, String roleSusNo2) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!employee_no.equals("")) {
				qry += "  and upper(c.employee_no) like ? ";
			}
			if (!rank.equals("")) {
				qry += "  and r.id= ? ";
			}
			if (!to_sus_no.equals("")) {
				qry += "  and upper(p.from_sus_no) like ? ";
			}
			if (!status.equals("") && !status.equals("4")) {
				qry += "  and p.status=? ";
			}
			if (!cr_by.equals("")) {
				qry += "  and cast(l1.userid as character varying)= ? ";
			}

		if(!cr_date.equals("")) {
					qry +=" and cast(p.created_date as date) = cast(? as date)";
				}
			if (status.equals("4")) {
				if (roleType.equals("APP")) {
					qry += " and p.cancel_status=0 ";
				}
				else
				qry += " and p.cancel_status in (-1,2) ";

				q = "select distinct p.id, \r\n" + 
						"c.employee_no,\r\n" + 
						"first_name || ' ' || middle_name || ' ' || last_name as name,\r\n" + 
						"coalesce(r.description,'Not Fill') as rank,\r\n" + 
						"p.out_auth,\r\n" + 
						"TO_CHAR(p.out_auth_dt,'DD-MON-YYYY') as out_auth_dt,\r\n" + 
						"p.to_sus_no as sus_no,\r\n" + 
						"TO_CHAR(p.dt_of_tos,'DD-MON-YYYY') as dt_of_tos, p.dt_of_tos as dt_of_tosT, \r\n" + 
						"p.civ_id,\r\n" + 
						"p.reject_remarks,p.from_sus_no as from_sus_no\r\n" + 
						"from tb_psg_posting_in_out_civilian p \r\n" + 
						"inner join  tb_psg_civilian_dtl_main c on p.civ_id = c.id \r\n" + 
						"left join cue_tb_psg_rank_app_master r on r.id = c.designation \r\n" +
						"left join logininformation l1 on l1.username =p.created_by\r\n" + 
						"where p.id = (select id from tb_psg_posting_in_out_civilian  where civ_id=p.civ_id  order by id desc limit 1) \r\n" + 
						"and ? =(select user_sus_no from logininformation where username=p.created_by )\r\n" + 
						"and p.status=1 and p.to_sus_no like ? " + qry + " order by p.id desc";
				}

			else {
				q = "select distinct p.id, \r\n" + 
						"c.employee_no,\r\n" + 
						"first_name || ' ' || middle_name || ' ' || last_name as name,\r\n" + 
						"coalesce(r.description,'Not Fill') as rank,\r\n" + 
						"p.out_auth,\r\n" + 
						"TO_CHAR(p.out_auth_dt,'DD-MON-YYYY') as out_auth_dt,\r\n" + 
						"p.to_sus_no as sus_no,\r\n" + 
						"TO_CHAR(p.dt_of_tos,'DD-MON-YYYY') as dt_of_tos, p.dt_of_tos as dt_of_tosT, \r\n" + 
						"p.civ_id,\r\n" + 
						"p.reject_remarks,p.from_sus_no as from_sus_no\r\n" + 
						"from tb_psg_posting_in_out_civilian p \r\n" + 
						"inner join  tb_psg_civilian_dtl_main c on p.civ_id = c.id \r\n" + 
						"left join cue_tb_psg_rank_app_master r on r.id = c.designation \r\n" + 
						"left join logininformation l1 on l1.username =p.created_by\r\n" + 
						"where p.to_sus_no like ? and c.civilian_status='R' " + qry + 
						"order by p.id desc";

				}
			stmt = conn.prepareStatement(q);
			int j = 2;
			if (status.equals("4")) {
				stmt.setString(1, roleSusNo2);
				stmt.setString(2, roleSusNo + "%");
				j = 3;
			}
			else
			stmt.setString(1, roleSusNo + "%");
			if (!employee_no.equals("")) {
				stmt.setString(j, employee_no.toUpperCase() + "%");
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
			if (!status.equals("") && !status.equals("4")) {
				stmt.setInt(j, Integer.parseInt(status));
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
			j++;
			

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("employee_no"));//1
				list.add(rs.getString("name"));//2
				list.add(rs.getString("rank"));//3
				list.add(rs.getString("out_auth"));//4
				list.add(rs.getString("out_auth_dt"));//5
				list.add(rs.getString("sus_no"));//6
				list.add(rs.getString("dt_of_tos"));//7
				list.add(rs.getString("reject_remarks"));//8
				
		

				String f = "";
				String f1 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){InApproved("
							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"
							+ rs.getString("sus_no") + "' ,'"+ rs.getString("dt_of_tosT").substring(0,10) + "' )}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

//					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){InReject("
//							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"
//							+ rs.getString("sus_no") + "' )}else{ return false;}\"";
					
					
					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') )"
							+ "{addRemarkModel('InReject', "+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' ,'"+ rs.getString("sus_no") + "')}else{ return false;}\"";		
					
					
					
					
					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";
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
							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "' )}else{ return false;}\"";
					f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

							+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "')}else{ return false;}\"";
					f1 = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'REJECT'  " + rejectData
							+ "'><i class='fa fa-times'></i></a>";
				}

				else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))
				{
					if (rs.getString("from_sus_no")!=null) {
						String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("
								+ rs.getString("id") + " ,'" + rs.getString("civ_id") + "')}else{ return false;}\"";
						f = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  " + CancelData
								+ "'><i class='fa fa-times'></i></a>";
					}
				}
				list.add(f);
				list.add(f1);
				list.add(rs.getString("from_sus_no"));//11
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
	
/*	public  String setTenureDate_Civilian(int id,int cur_id) {
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String msg = "0";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);	
		try {
			TB_POSTING_IN_OUT_CIVILIAN pin = (TB_POSTING_IN_OUT_CIVILIAN)sessionhql.get(TB_POSTING_IN_OUT_CIVILIAN.class, cur_id);
		     Date sos=pin.getDt_of_sos();
			 Calendar lastDate = Calendar.getInstance();
			 lastDate.setTime(sos);
			 lastDate.add(Calendar.MONTH, -1);
			 String tdate=lastDate.getActualMaximum(Calendar.DATE)+"/"+(lastDate.get(Calendar.MONTH) + 1)+"/"+ lastDate.get(Calendar.YEAR);
		      
		String hql_n = "update TB_POSTING_IN_OUT_CIVILIAN set tenure_date=:t_date where  id=:id";
		Query query_n = sessionhql.createQuery(hql_n).setTimestamp("t_date", format.parse(tdate) )
				.setInteger("id", id);
		msg = query_n.executeUpdate() > 0 ? "1" : "0";
		tx.commit();
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return "0";
		}
		return msg;
	}*/
	
	public List<Map<String, Object>> getLastPostInOutCivilian(int civ_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
							 
			q = "select * from tb_psg_posting_in_out_civilian where civ_id=? and status not in ('-1','3')  order by id desc limit 2 ";


			stmt = conn.prepareStatement(q);
			stmt.setInt(1,civ_id);
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
	
	public List<Map<String, Object>>GetPost_OutByid_Civilian(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
				q = "select p.id, \r\n" + 
						"c.employee_no,\r\n" + 
						"c.full_name as name,\r\n" + 
						"r.description as rank,\r\n" + 
						"p.out_auth, \r\n" + 
						"p.civ_id,\r\n" + 
						"p.out_auth_dt,\r\n" + 
						"p.to_sus_no as unit_sus_no,\r\n" + 
						"p.dt_of_tos,\r\n" + 
						"p.from_sus_no, \r\n" + 
						"m.unit_name,\r\n" + 
						"p.dt_of_sos,\r\n" + 
						"p.unit_description\r\n" + 
						" from tb_psg_posting_in_out_civilian p \r\n" + 
						"  inner join  tb_psg_civilian_dtl_main c on p.civ_id=c.id  \r\n"
						+ " inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no \r\n" + 
						" left join cue_tb_psg_rank_app_master r on r.id = c.designation \r\n" + 
						"  where UPPER(m.status_sus_no) in ('INACTIVE','ACTIVE') and  p.id = ? ";
					
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
	public List<Map<String, Object>>GetPost_InByid_Civilian(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			// bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam)
				q = "select p.id, \r\n" + 
						"c.employee_no,\r\n" + 
						"c.full_name as name,\r\n" + 
						"r.description as rank, \r\n" + 
						"p.out_auth, \r\n" + 
						"p.civ_id, \r\n" + 
						"p.out_auth_dt,\r\n" + 
						"p.to_sus_no as unit_sus_no,\r\n" + 
						"m.unit_name, \r\n" + 
						"p.dt_of_tos,\r\n" + 
						"p.from_sus_no,\r\n" + 
						"p.unit_description\r\n" + 
						"from tb_psg_posting_in_out_civilian p \r\n" + 
						"inner join  tb_psg_civilian_dtl_main c on p.civ_id=c.id \r\n" + 
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no "
					  + "left join cue_tb_psg_rank_app_master r on r.id = c.designation where  UPPER(status_sus_no) in ('INACTIVE','ACTIVE')  and p.id = ? ";

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
	@Override
	public List<Map<String, Object>> getLastPostInnoti_civ(int civ_id) {


		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;


			q = " select a.from_sus_no,a.to_sus_no,TO_CHAR(a.dt_of_tos,'DD-MON-YYYY') AS dt_of_tos,b.employee_no,b.full_name from tb_psg_posting_in_out_civilian a inner join  tb_psg_civilian_dtl_main b on  a.civ_id=b.id where cast(a.civ_id as character varying)=?  "
					+ " order by a.id desc limit 1";



			stmt = conn.prepareStatement(q);

			stmt.setString(1,String.valueOf(civ_id));

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

	
	public  String setTenureDate_Civilian(int id,int cur_id) {
        Connection conn = null;                
        String q = "";                
        
        Session sessionhql = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionhql.beginTransaction();
        String msg = "0";
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

        

        try {
    conn = dataSource.getConnection();                        
                PreparedStatement stmt = null;

                TB_POSTING_IN_OUT_CIVILIAN pin = (TB_POSTING_IN_OUT_CIVILIAN)sessionhql.get(TB_POSTING_IN_OUT_CIVILIAN.class, cur_id);

             Date sos=pin.getDt_of_sos();
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");                 
                   String tdate = dateFormat.format(sos);

                // Calendar lastDate = Calendar.getInstance();
                 //lastDate.setTime(sos);
                // lastDate.add(Calendar.MONTH, -1);
                // String tdate=lastDate.getActualMaximum(Calendar.DATE)+"/"+(lastDate.get(Calendar.MONTH) + 1)+"/"+ lastDate.get(Calendar.YEAR);

         q = "SELECT (date_trunc('month', ?::date) - interval '1 day')::date"; 
                stmt = conn.prepareStatement(q);
        
                stmt.setString(1,tdate);
                ResultSet rs = stmt.executeQuery();   
                Date tempdate = null;
    if (rs.next()) {
        tempdate = rs.getDate(1);
    }
    
        String hql_n = "update TB_POSTING_IN_OUT_CIVILIAN set  tenure_date=:t_date "

                        + " where  id=:id";

        Query query_n = sessionhql.createQuery(hql_n).setTimestamp("t_date", tempdate )

                        .setInteger("id", id);

        msg = query_n.executeUpdate() > 0 ? "1" : "0";

        tx.commit();

        }catch (Exception e) {

                // TODO: handle exception

                tx.rollback();

                return "0";

        }

        return msg;

}

}
