package com.dao.psg.Jco_Transaction;

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

import com.models.psg.Jco_Transaction.TB_POSTING_IN_OUT_JCO;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.persistance.util.HibernateUtil;

public class Posting_In_Out_JCO_DAOImpl implements Posting_In_Out_JCO_DAO{
private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	 /*************PostOut Search************/
		public ArrayList<ArrayList<String>>Search_Postout_JCO(String roleSusNo, String personnel_no, String rank,
				String to_sus_no, String roleType, String status, String cr_by,String cr_date, String roleSusNo2) {
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
					q = "select distinct p.id, c.army_no as personnel_no,\r\n" + 
							"c.full_name as name,\r\n" + 
							"r.rank as rank,\r\n" + 
							"p.out_auth,p.reject_remarks,\r\n" + 
							"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" + 
							"p.to_sus_no as unit_sus_no,\r\n" + 
							"ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,\r\n" + 
							"p.jco_id \r\n" + 
							"from tb_psg_posting_in_out_jco p \r\n" + 
							"inner join  tb_psg_census_jco_or_p c on p.jco_id = c.id \r\n" + 
							"inner join tb_psg_mstr_rank_jco r on r.id = c.rank\r\n" + 
							"left join logininformation l1 on l1.username =p.created_by\r\n" + 
							"where p.id = (select id from tb_psg_posting_in_out_jco  where jco_id=p.jco_id  and status != -1 order by id desc limit 1)\r\n" + 
							"and (select count(*) from tb_psg_re_call_jco recl where recl.jco_id=p.jco_id and recl.date_of_tos=p.dt_of_tos)=0\r\n" + 
							//"and ? =(select user_sus_no from logininformation where username=p.created_by )\r\n" + 
							"and  p.from_sus_no like ? and p.status=1 and p.from_sus_no is not NULL  " + qry + " order by p.id desc ";
				}
				else {
					q = "select distinct p.id, c.army_no as personnel_no,\r\n" + 
							"c.full_name as name,\r\n" + 
							"r.rank as rank,\r\n" + 
							"p.out_auth,p.reject_remarks,\r\n" + 
							"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" + 
							"p.to_sus_no as unit_sus_no,\r\n" + 
							"ltrim(TO_CHAR(p.dt_of_sos,'DD-MON-YYYY'),'0') as dt_of_sos,\r\n" + 
							"p.jco_id \r\n" + 
							"from tb_psg_posting_in_out_jco p \r\n" + 
							"left join logininformation l1 on l1.username =p.created_by\r\n" + 
							"inner join  tb_psg_census_jco_or_p c on p.jco_id = c.id \r\n" + 
							"inner join tb_psg_mstr_rank_jco r on r.id = c.rank where p.from_sus_no like ? " + qry +
							"order by p.id desc";
				}				
				stmt = conn.prepareStatement(q);
				int j = 2;
				if (status.equals("4")) {
					/*stmt.setString(1, roleSusNo2);
					stmt.setString(2, roleSusNo + "%");
					j = 3;*/
					
					
					stmt.setString(1, roleSusNo + "%");
					j = 2;
				}
				else
				stmt.setString(1, roleSusNo + "%");
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
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' ,'"
								+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";
						f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";
						
						String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){addRemarkModel('Reject' , "

							+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' ,'"

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
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' ,'"
								+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";
						f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";
						
						String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";
						f1 = "<i class='action_icons action_reject' " + rejectData + " title='Reject Data'></i></a>";
					}
					else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))
					{
						String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";
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
		

		/*************PostIN Search************/

		public ArrayList<ArrayList<String>>Search_PostIn_JCO(String roleSusNo, String personnel_no, String rank,
				String to_sus_no, String roleType, String status,String cr_by,String cr_date, String roleSusNo2) {
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
					qry += "  and upper(p.from_sus_no) like ? ";
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

					q = "select distinct p.id, \r\n" + 
							"c.army_no as personnel_no,\r\n" + 
							"c.full_name as name,\r\n" + 
							"r.rank as rank,\r\n" + 
							"p.out_auth,\r\n" + 
							"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" + 
							"p.to_sus_no as unit_sus_no,\r\n" + 
							"ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
							"p.jco_id \r\n" + 
							"from tb_psg_posting_in_out_jco p \r\n" + 
							"inner join  tb_psg_census_jco_or_p c on p.jco_id = c.id \r\n" + 
							"inner join tb_psg_mstr_rank_jco r on r.id = c.rank\r\n" + 
							"left join logininformation l1 on l1.username =p.created_by\r\n" + 
							"where p.id = (select id from tb_psg_posting_in_out_jco  where jco_id=p.jco_id and status != -1  order by id desc limit 1) \r\n" + 
							"and (select count(*) from tb_psg_re_call_jco recl where recl.jco_id=p.jco_id and recl.date_of_tos=p.dt_of_tos)=0\r\n" + 
							//"and ? =(select user_sus_no from logininformation where username=p.created_by )\r\n" + 
							"and p.status=1 and p.from_sus_no is not NULL and p.to_sus_no like ? " + qry + " order by p.id desc";
					}

				else {
					q = "select distinct p.id, \r\n" + 
							"c.army_no as personnel_no,\r\n" + 
							"c.full_name as name,\r\n" + 
							"r.rank as rank,\r\n" + 
							"p.out_auth,\r\n" + 
							"ltrim(TO_CHAR(p.out_auth_dt,'DD-MON-YYYY'),'0') as out_auth_dt,\r\n" + 
							"p.to_sus_no as unit_sus_no,\r\n" + 
							"ltrim(TO_CHAR(p.dt_of_tos,'DD-MON-YYYY'),'0') as dt_of_tos,\r\n" + 
							"p.jco_id \r\n" + 
							"from tb_psg_posting_in_out_jco p \r\n" + 
							"inner join  tb_psg_census_jco_or_p c on p.jco_id = c.id \r\n" + 
							"inner join tb_psg_mstr_rank_jco r on r.id = c.rank\r\n" + 
							"left join logininformation l1 on l1.username =p.created_by\r\n" + 
							"where  p.to_sus_no like ? " + qry + 
							"order by p.id desc";

					}
				stmt = conn.prepareStatement(q);
				int j = 2;
				if (status.equals("4")) {
					/*stmt.setString(1, roleSusNo2);
					stmt.setString(2, roleSusNo + "%");
					j = 3;*/
					
					stmt.setString(1, roleSusNo + "%");
					j = 2;
				}
				else
				stmt.setString(1, roleSusNo + "%");
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
				System.err.println("query search post in jco \n"+stmt);
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
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' ,'"
								+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";
						f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

						String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){InReject("
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' ,'"
								+ rs.getString("unit_sus_no") + "' )}else{ return false;}\"";
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
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "' )}else{ return false;}\"";
						f = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

						String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){RejectCancelHisData("

								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";
						f1 = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'REJECT'  " + rejectData
								+ "'><i class='fa fa-times'></i></a>";
					}

					else if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))
					{
						String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){RejectCancelHisData("
								+ rs.getString("id") + " ,'" + rs.getString("jco_id") + "')}else{ return false;}\"";
						f = "<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  " + CancelData
								+ "'><i class='fa fa-times'></i></a>";
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

	public List<Map<String, Object>> getLastPostInOutJCO(int jco_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
							 
			q = "select * from tb_psg_posting_in_out_jco where jco_id=? and status not in ('-1','3')   order by id desc limit 2 ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1,jco_id);
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
	
	 public  String setTenureDateJCO(int id,int cur_id) {
	        Connection conn = null;                
	        String q = "";                
	        
	        Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionhql.beginTransaction();
	        String msg = "0";
	        DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

	        

	             try {
	              conn = dataSource.getConnection();                        
	                PreparedStatement stmt = null;

	                TB_POSTING_IN_OUT_JCO pin = (TB_POSTING_IN_OUT_JCO)sessionhql.get(TB_POSTING_IN_OUT_JCO.class, cur_id);

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
	    
	        String hql_n = "update TB_POSTING_IN_OUT_JCO set  tenure_date=:t_date "

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
		
	
	public Boolean getJcopernoAlreadyExits(int jco_id) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select case when count(p.id) > 0 then true else false end from tb_psg_posting_in_out_jco p\r\n" + 
					"inner join tb_psg_census_jco_or_p c on p.jco_id=c.id where p.status = '0' and c.id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, jco_id);
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
	public ArrayList<ArrayList<String>> getPostTenureDateJCO(int jco_id){
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
		try{			
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;		
				q="select to_char(dt_of_tos,'DD/MM/YYYY') as date from tb_psg_posting_in_out_jco where jco_id=? and status='1' order by dt_of_tos desc limit 1" ;				
			stmt=conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
		
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
	
	public List<Map<String, Object>>GetPost_OutByid(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
		
				q = "select p.id, \r\n" + 
						"c.army_no as personnel_no,\r\n" + 
						"c.full_name as name,\r\n" + 
						"r.rank,\r\n" + 
						"p.out_auth, \r\n" + 
						"p.jco_id,\r\n" + 
						"p.out_auth_dt,\r\n" + 
						"p.to_sus_no as unit_sus_no,\r\n" + 
						"p.dt_of_tos,\r\n" + 
						"p.from_sus_no, \r\n" + 
						"m.unit_name,\r\n" + 
						"p.dt_of_sos,\r\n" + 
						"p.unit_description,p.t_status,ta.label as t_status_lbl,c.arm_service\r\n" + 
						"from tb_psg_posting_in_out_jco p \r\n" + 
						"inner join  tb_psg_census_jco_or_p c on p.jco_id=c.id \r\n" + 
						"inner join tb_psg_mstr_rank_jco r on r.id = c.rank \r\n" + 
						"inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\n"+
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active' where p.id = ? ";
					
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
	
	
	public List<Map<String, Object>>GetPost_InByid(int id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

				q = "select p.id, \r\n" + 
						"c.army_no as personnel_no,\r\n" + 
						"c.full_name as name,\r\n" + 
						//"r.description as rank, \r\n" + 
						"r.rank as rank, \r\n" + 
						"p.out_auth, \r\n" + 
						"p.jco_id,\r\n" + 
						"p.out_auth_dt,\r\n" + 
						"p.to_sus_no as unit_sus_no,\r\n" + 
						"m.unit_name, \r\n" + 
						"p.dt_of_tos,\r\n" + 
						"p.from_sus_no,\r\n" + 
						"p.unit_description,p.t_status,ta.label as t_status_lbl,c.arm_service \r\n" + 
						"from tb_psg_posting_in_out_jco p \r\n" + 
						"inner join  tb_psg_census_jco_or_p c on p.jco_id = c.id \r\n" + 
						"inner join tb_psg_mstr_rank_jco r on r.id = c.rank \r\n" + 
						"inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\r\n"+
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active' where p.id = ? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("edit data \n"+stmt);
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
}

