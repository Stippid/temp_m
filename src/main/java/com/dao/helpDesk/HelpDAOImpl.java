package com.dao.helpDesk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

import com.models.Helpdesk.HD_TB_BISAG_FAQ;
import com.models.Helpdesk.HD_TB_BISAG_LOGIN_MARQUEE;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class HelpDAOImpl implements HelpDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> getNewfaqdetail1() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			Statement stmt = conn.createStatement();
			sql += "  select question,answer,section,id from hd_tb_miso_faq order by section ";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 1; i < columnCount + 1; i++) {
					String name = rs.getString(i);
					list.add(name);
				}
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

	public List<String> getsecList() {
		List<String> alist = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			Statement stmt = conn.createStatement();
			sql += "  select distinct section from hd_tb_miso_faq order by section ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				alist.add(name);
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

	public List<String> getqueList() {
		List<String> alist = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			Statement stmt = conn.createStatement();
			sql += "select question,answer,id from hd_tb_miso_faq order by section";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(1);
				alist.add(name);
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
	
	public List<Map<String, Object>> myTicketList(String ticket, String ticket_status, String from_date,String to_date, String help_topic, String userId, String roleid, String module, String label1){
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (ticket_status != "" && ticket_status != null) {
					qry += " and ticket_status =?";
				}
				if (!from_date.equals("")) {
					qry += " and cast(created_on as Date) >= cast(? as Date)";
				}
				if (!to_date.equals("")) {
					qry += " and cast(created_on as Date) <= cast(? as Date)";
				}

				if (ticket != "" && ticket != null) {
					qry += " and cast(ticket as character varying) =?";
				}

				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					qry += " and help_topic =?";
				}

				if (module != "0" && !module.equals("0")) {
					qry += " and cast(b.id as character varying) = ?";
				}

				if (!roleid.equals("1")) {
					qry += " and cast(userid as character varying)=?";
				}
				if (roleid.equals("1")) {
					if (label1.equals("My Tickets")) {
						qry += " and cast(userid as character varying)=?";
					}else {
						
					}
				}
			
				if (qry != "") {
					qry = " where " + qry.substring(4, qry.length());
				}
				
				q = "select distinct \r\n" + "	a.id,\r\n" + "	a.ticket,\r\n" + "	b.module_name,\r\n"
						+ "	a.issue_summary,\r\n"
						+ "	(case when a.ticket_status='0' then ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') \r\n"
						+ "		when a.ticket_status='1' then ltrim(TO_CHAR(a.assigned_dt,'dd-mm-yyyy'),'0') \r\n"
						+ "		when a.ticket_status='2' then ltrim(TO_CHAR(a.completed_dt,'dd-mm-yyyy'),'0') \r\n"
						+ "		else null\r\n" + "		end) as dt,\r\n" + "	t1.label as help_topic,\r\n"
						+ "	t.label as ticket_status,\r\n"
						+ "	(case when a.module='-1' then 'Others' else b.module_name end) as module_name \r\n"
						+ "from ticket_generate a \r\n"
						+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
						+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
						+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
						+ qry + " order by id DESC";
				
				
				int j = 1;
				stmt = conn.prepareStatement(q);

				if (ticket_status != "" && ticket_status != null) {
					stmt.setString(j, ticket_status);
					j += 1;
				}
				if (!from_date.equals("")) {
					stmt.setString(j, from_date);
					j += 1;
				}
				if (to_date != "") {
					stmt.setString(j, to_date);
					j += 1;
				}
				if (ticket != "" && ticket != null) {
					stmt.setString(j, ticket);
					j += 1;
				}
				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					stmt.setString(j, help_topic);
					j += 1;
				}
				if (module != "0" && !module.equals("0")) {
					stmt.setString(j, module);
					j += 1;
				}
				if (!roleid.equals("1")) {
					stmt.setString(j, userId);
					j += 1;
				}
				if (roleid.equals("1")) {
					if (label1.equals("My Tickets")) {
						stmt.setString(j, userId);
					}

				}

				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
System.out.println("creat tkt: " + stmt);
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}

					String editData = "onclick=\" editData(" + rs.getObject(1) + ",'" + label1 + "')\"";
					String updateButton = "<i class='fa fa-eye' " + editData
							+ " title='Edit Data' style='color:red'></i>";
					String f = "";
					f = updateButton;
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

	public List<Map<String, Object>> manageTicketList(String ticket, String ticket_status, String from_date,String to_date, String help_topic, String username, String roleid, String module, String label1,String moduleWhr,String typeWhr
			 ,String unit_name1,String cont_comd1,String role){
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				
				
				if (ticket_status != "" && ticket_status != null) {
					qry += " and ticket_status =?";
				}
				if (!from_date.equals("")) {
					qry += " and cast(created_on as Date) >= cast(? as Date)";
				}
				if (!to_date.equals("")) {
					qry += " and cast(created_on as Date) <= cast(? as Date)";
				}

				if (ticket != "" && ticket != null) {
					qry += " and cast(ticket as character varying) =?";
				}

				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					qry += " and help_topic =?";
				}

				if (module != "0" && !module.equals("0")) {
					qry += " and cast(b.id as character varying) = ?";
				}

				if (typeWhr.equals("APP") || typeWhr.equals("DEO")) {
					qry += " and assigned_to = ? ";
				}
				
				if (cont_comd1 != "" && cont_comd1 != null) {
					qry += " and SUBSTR(u.form_code_control,1,1) like ?";
				}
				if (unit_name1 != "" && unit_name1 != null) {
					qry += "  and upper(u.unit_name) like ?";
				}
				if(role.equals("sd_orbat_entitlement")) {
					qry ="where a.sd_vetting = ? "+ qry ;
				}
				
				if (qry != "" && !role.equals("sd_orbat_entitlement")) {
					qry = " where b.module_name in "+moduleWhr + qry;
				}
				
				q = "select distinct a.id,a.ticket,b.module_name,a.issue_summary,TO_CHAR(a.assigned_dt,'dd-mm-yyyy') as assigned_dt,TO_CHAR(a.completed_dt,'dd-mm-yyyy') as completed_dt,TO_CHAR(a.created_on,'dd-mm-yyyy') as created_on,\r\n"
						+ "	(case when a.ticket_status='0' then TO_CHAR(a.created_on,'dd-mm-yyyy') \r\n"
						+ "		when a.ticket_status='1' then TO_CHAR(a.assigned_dt,'dd-mm-yyyy') \r\n"
						+ "		when a.ticket_status='2' then TO_CHAR(a.completed_dt,'dd-mm-yyyy') \r\n"
						+ "		else null\r\n" 
						+ "		end) as dt,\r\n" 
						+ "	t1.label as help_topic,\r\n"
						+ "	t.label as ticket_status,\r\n"
						+ "	(case when a.module='-1' then 'Others' else b.module_name end) as module_name, \r\n"
						+ "	u.unit_name \r\n"
						+ "from ticket_generate a \r\n"
						+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
						+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
						+ "left join tb_miso_orbat_unt_dtl u on a.sus_no=u.sus_no and status_sus_no ='Active' \r\n"
						+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
						+ qry + " order by id DESC";
				
				
				int j = 1;
				stmt = conn.prepareStatement(q);

				if(role.equals("sd_orbat_entitlement")) {
					stmt.setString(j, username);
					j += 1;
				}
				
				if (ticket_status != "" && ticket_status != null) {
					stmt.setString(j, ticket_status);
					j += 1;
				}
				if (!from_date.equals("")) {
					stmt.setString(j, from_date);
					j += 1;
				}
				if (to_date != "") {
					stmt.setString(j, to_date);
					j += 1;
				}
				if (ticket != "" && ticket != null) {
					stmt.setString(j, ticket);
					j += 1;
				}
				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					stmt.setString(j, help_topic);
					j += 1;
				}
				if (module != "0" && !module.equals("0")) {
					stmt.setString(j, module);
					j += 1;
				}
				if (cont_comd1 != "" && cont_comd1 != null) {
					stmt.setString(j, cont_comd1+"%");
					j += 1;
				}
				
				if (unit_name1 != "" && unit_name1 != null) {
					stmt.setString(j, unit_name1.toUpperCase()+"%");
					j += 1;
				}
				
				if (typeWhr.equals("APP") || typeWhr.equals("DEO") || typeWhr.equals("orbat_app") || typeWhr.equals("orbat_deo")) {
					stmt.setString(j, username);
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();
				System.out.println("print----------" + stmt);
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						
					}

					String f = "";
					String TTButton = "";
					if(rs.getObject(7).equals("New") && typeWhr.equals("ALL")){
						String transferTicket = "onclick=\"transferData("+rs.getObject(1)+")\"";
						TTButton = "<i class='fa fa-exchange' "+transferTicket+" title='Transfer Ticket' style='color:red;font-size: 20px;'></i>";
					}
					
					String editData = "onclick=\" editData(" + rs.getObject(1) + ",'" + label1 + "')\"";
					String updateButton = "<i class='fa fa-eye' "+editData+" title='View Ticket' style='color:red;font-size: 20px;'></i>";
					
					f = TTButton+"   "+updateButton;
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

	//Mitesh(13-09)
	public String UpdateViewDetailsValue(HD_TB_BISAG_TICKET_GENERATE uh, String agent_name) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String ticket_status = uh.getTicket_status();
		int app2 = 0;
       if(!agent_name.equals("")) {
		if (ticket_status.equals("1")) {
			String hql1 = "update HD_TB_BISAG_TICKET_GENERATE c set c.assigned_to=:assigned_to,c.ticket_status=:ticket_status,assigned_dt='"+new Date()+"' where c.id=:id ";
			app2 = session.createQuery(hql1).setString("assigned_to", agent_name).setString("ticket_status", ticket_status).setInteger("id", uh.getId()).executeUpdate();
		}
		if (ticket_status.equals("2")) {
			String hql1 = "update HD_TB_BISAG_TICKET_GENERATE c set c.assigned_to=:assigned_to,c.ticket_status=:ticket_status,completed_dt='"+new Date()+"',miso_reply=:miso_reply where c.id=:id ";
			app2 = session.createQuery(hql1).setString("assigned_to", agent_name).setString("ticket_status", ticket_status).setString("miso_reply", uh.getMiso_reply()).setInteger("id", uh.getId()).executeUpdate();
		}
       }else {
    	   return "please assign to user";
       }
		tx.commit();
		session.close();
		if (app2 > 0) {
			return "Updated Successfully";
		} else {
			return "Updated Not Successfully";
		}
	}
	
	public String UpdateViewDetailsSD(HD_TB_BISAG_TICKET_GENERATE uh) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int app2 = 0;
		
			String hql1 = "update HD_TB_BISAG_TICKET_GENERATE c set sd_reply=:miso_reply,sd_reply_dt=:sd_reply_dt where c.id=:id ";
			app2 = session.createQuery(hql1).setString("miso_reply", uh.getSd_reply()).setDate("sd_reply_dt", new Date()).setInteger("id", uh.getId()).executeUpdate();
		
      
		tx.commit();
		session.close();
		if (app2 > 0) {
			return "Updated Successfully";
		} else {
			return "Updated Not Successfully";
		}
	}

	public HD_TB_BISAG_TICKET_GENERATE gethelpbyId(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from HD_TB_BISAG_TICKET_GENERATE where id=:id");
		q.setParameter("id", id);
		HD_TB_BISAG_TICKET_GENERATE list = (HD_TB_BISAG_TICKET_GENERATE) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getSupportRequestJdbc() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			qry += "select count(ticket_status)::int as total, ticket_status from ticket_generate where ticket_status!='' GROUP BY ticket_status ORDER BY 2";
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getActiveUserListJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getUserLoginCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getActiveUserCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getNewTicketCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getPendigTicketCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getCompletedTicketCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getRoleCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getUserCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getfeedbackrecCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public List<Map<String, Object>> getFeatureReqCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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

	public HD_TB_BISAG_FAQ geteditfaqbyId(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from HD_TB_BISAG_FAQ where id=:id");
		q.setParameter("id", id);
		HD_TB_BISAG_FAQ list = (HD_TB_BISAG_FAQ) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String UpdateFAQDetailsValue(HD_TB_BISAG_FAQ faqValues) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update HD_TB_BISAG_FAQ set question=:question,answer=:answer where id=:id";
		Query query = session.createQuery(hql).setString("question", faqValues.getQuestion())
				.setString("answer", faqValues.getAnswer()).setInteger("id", faqValues.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public List<Map<String, Object>> TicketSearchList(String section) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (section != "" && section != null) {
					qry += "section like ?";
				}
				q = "select id,answer,question from hd_tb_miso_faq where " + qry;
				stmt = conn.prepareStatement(q);
				if (section != "" && section != null) {
					stmt.setString(1, section + "%");
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
							+ rs.getObject(1) + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' " + Delete1
							+ " title='Delete Data'></i>";
					String f = "";
					f += updateButton;
					f += deleteButton;
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

	public List<Map<String, Object>> getHelpMngtListJdbc(String moduleid, String agent_name) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (agent_name != "" && agent_name != null){
					qry += " and a.agent_name like ?";
				}
				q = "select a.id,b.module_name,a.agent_name,a.moduleid \r\n" + 
						"from hd_tb_miso_user_info a\r\n" + 
						"inner join tb_ldap_module_master b on a.moduleid=b.id\r\n" + 
						"where cast(a.moduleid as character varying) = ? " + qry;

				int j = 1;
				stmt = conn.prepareStatement(q);
				
				stmt.setString(j, moduleid);
				j += 1;
				
				if (agent_name != "" && agent_name != null) {
					stmt.setString(j, agent_name + "%");
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("moduleid")+"','"+rs.getString("agent_name")+"')}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
					/*String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?')){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";*/
					String f = "";
					f += updateButton;
					//f += deleteButton;
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

	public Long checkExitstingUserManagement(int module, String agent_name) {
		String hql = "select count(*) from HD_TB_BISAG_USER_INFO where moduleid=:moduleid and LOWER(agent_name)=:agent_name ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setParameter("moduleid", module);
		q.setParameter("agent_name", agent_name.toLowerCase());
		Long users = (Long) q.uniqueResult();
		tx.commit();
		session.close();
		return users;
	}

	public List<Map<String, Object>> getSearchMercuryList1(String msg, String valid_upto) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (msg != "" && msg != null) {
					qry += " and msg = ?";
				}
				if (valid_upto != "" && valid_upto != null) {
					qry += " and valid_upto = ?";
				}
				q = "select msg, ltrim(TO_CHAR(valid_upto,'dd-mm-yyyy'),'0') as valid_upto,id from tb_login_mercuries WHERE valid_upto >= now() and valid_upto <= valid_upto and status='1' "
						+ qry;
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (msg != "" && msg != null) {
					stmt.setString(j, msg);
					j += 1;
				}
				if (valid_upto != "" && valid_upto != null) {
					stmt.setString(j, valid_upto);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getString("id") + ",'" + rs.getString("msg") + "','" + rs.getString("valid_upto")
							+ "')}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData
							+ " title='Edit Data'></i>";
					String f = "";
					f += updateButton;
					columns.put(metaData.getColumnLabel(3), f);
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

	@SuppressWarnings("unchecked")
	public Boolean getmsgExist(String msg, Date valid_upto) {
		String hql = "from HD_TB_BISAG_LOGIN_MARQUEE where msg=:msg and valid_upto=:valid_upto ";
		List<HD_TB_BISAG_LOGIN_MARQUEE> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("msg", msg);
			query.setParameter("valid_upto", valid_upto);
			users = (List<HD_TB_BISAG_LOGIN_MARQUEE>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<String> getLayoutlist() {
		String hql = "select t.msg from HD_TB_BISAG_LOGIN_MARQUEE t ";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<HD_TB_BISAG_TICKET_GENERATE> getmodule_help(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from HD_TB_BISAG_TICKET_GENERATE where id=:id");
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<HD_TB_BISAG_TICKET_GENERATE> list = (List<HD_TB_BISAG_TICKET_GENERATE>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> loggedin_report(List<String> userlist) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			String whr = "";
			for (int i = 0; i < userlist.size(); i++) {
				if (i == 0) {
					whr += " a.username = ? ";
				} else {
					whr += " or a.username = ? ";
				}
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select distinct a.userid,a.login_name,a.user_sus_no,c.unit_name\r\n"
					+ "        from logininformation a \r\n"
					+ "        left join tb_miso_orbat_unt_dtl c on a.user_sus_no=c.sus_no" + " where " + whr;
			stmt = conn.prepareStatement(q);
			for (int i = 1; i <= userlist.size(); i++) {
				stmt.setString(i, userlist.get(i - 1));
			}
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

	public String getSupportRequest() {
		List<Map<String, Object>> list = getSupportRequestJdbc();
		String list1 = "";
		for (int i = 0; i < list.size(); i++) {
			String doc_type = "";
			if (list.get(i).get("ticket_status").equals("0")) {
				doc_type = "New";
			}
			if (list.get(i).get("ticket_status").equals("1")) {
				doc_type = "In Progress";
			}
			if (list.get(i).get("ticket_status").equals("2")) {
				doc_type = "Completed";
			}
			if (list.get(i).get("ticket_status").equals("3")) {
				doc_type = "Feedback";
			}
			if (list.get(i).get("ticket_status").equals("4")) {
				doc_type = "Feature Request";
			}
			list1 += ",{'ticket_status' : '" + doc_type + "' ,total:\"" + list.get(i).get("total") + "\"}";
		}
		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		}else {
			list1 += "[]";
		}
		return list1;
	}

	public String getActiveUserList() {
		String qry = "select count(ltrim(TO_CHAR(createddate,'YYYY-MM-DD'),'0')) as total,ltrim(TO_CHAR(createddate,'YYYY-MM-DD'),'0') as date from userlogincountinfo where createddate is not null GROUP BY ltrim(TO_CHAR(createddate,'YYYY-MM-DD'),'0') ORDER BY 2 ";
		List<Map<String, Object>> list = getActiveUserListJdbc(qry);
		String list1 = "";
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).get("total").equals(0))
				list1 += ",{date: \"" + list.get(i).get("date").toString() + "\", total: \"" + list.get(i).get("total")
						+ "\"}";
		}
		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		}
		return list1;
	}

	public List<Map<String, Object>> getUserLoginCount() {
		String qry = "select count(userid)::int as total from userlogincountinfo where status='1' ";
		List<Map<String, Object>> list = getUserLoginCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getActiveUserCount() {
		String qry = "select count(distinct a.userid)::int as total from logininformation a inner join userlogincountinfo b on a.userid = b.userid and b.loginstatus='Active' left join tb_miso_orbat_unt_dtl c on a.user_sus_no=c.sus_no ";
		List<Map<String, Object>> list = getActiveUserCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getNewTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where ticket_status ='0' and extract(month from now()) = extract(month from created_on) "; // new
																																										// ticket
		List<Map<String, Object>> list = getNewTicketCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getPendigTicketCount() {
		/*
		 * String
		 * qry="select count(id)::int as total from ticket_generate where ticket_status ='1' and extract(month from now()) = extract(month from created_on) "
		 * ; //in progress tickets
		 */
		String qry = "select count(id)::int as total from ticket_generate where ticket_status ='1'"; // in progress
																										// tickets
		List<Map<String, Object>> list = getPendigTicketCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getCompletedTicketCount() {
		/*
		 * String
		 * qry="select count(id)::int as total from ticket_generate where ticket_status ='2' and extract(month from now()) = extract(month from created_on) "
		 * ; // completed tickets
		 */
		String qry = "select count(id)::int as total from ticket_generate where ticket_status ='2'"; // completed
																										// tickets
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getfeedbackrecCount() {
		String qry = "select count(id)::int as total from ticket_generate where help_topic ='1' and extract(month from now()) = extract(month from created_on) "; // feedback
																																									// received
		List<Map<String, Object>> list = getfeedbackrecCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getFeatureReqCount() {
		String qry = "select count(id)::int as total from ticket_generate where help_topic ='5' and extract(month from now()) = extract(month from created_on) "; // feature
																																									// request
																																									// received
		List<Map<String, Object>> list = getFeatureReqCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getRoleCount() {
		String qry = "select count(role_id)::int as total from roleinformation"; // role count
		List<Map<String, Object>> list = getRoleCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getUserCount() {
		String qry = "select count(l.userid)::int as total from logininformation l\r\n"
				+ " left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code\r\n"
				+ " left join userroleinformation u on l.userid = u.user_id "
				+ " join roleinformation r  on  r.role_id =  u.role_id  "
				+ " where l.enabled='1' and l.army_no is not null and l.army_no != '' "; // user count
		List<Map<String, Object>> list = getUserCountJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getUserReport() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String q = "select l.userid, l.username, l.login_name, r.role " + "from logininformation l "
					+ "left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code "
					+ "left join userroleinformation u on l.userid = u.user_id join roleinformation r  on  r.role_id =  u.role_id  "
					+ "where l.enabled='1'  order by l.userid ";
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				String f = "";
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

	public List<Map<String, Object>> ticketStatusNew() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select a.id," + "a.ticket," + "b.module_name," + "a.issue_summary,"
					+ "ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') as created_on,"
					+ "t1.label as help_topic,t.label as ticket_status,"
					+ "(case when a.module='-1' then 'Others' else b.module_name end) as module_name   \r\n"
					+ "from ticket_generate a \r\n"
					+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar)\r\n"
					+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
					+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
					+ "where  ticket_status ='0' and extract(month from now()) = extract(month from created_on) order by created_on DESC";
			stmt = conn.prepareStatement(q);
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

	public List<Map<String, Object>> ticketStatusInProgress() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select a.id," + "a.ticket," + "b.module_name,"
					+ "a.issue_summary,ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') as created_on,"
					+ "t1.label as help_topic," + "t.label as ticket_status,"
					+ "(case when a.module='-1' then 'Others' else b.module_name end) as module_name  \r\n"
					+ "from ticket_generate a \r\n"
					+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
					+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
					+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
					+ "where  ticket_status ='1' order by id DESC";
			stmt = conn.prepareStatement(q);
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

	public List<Map<String, Object>> ticketStatusCompleted() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select a.id," + "a.ticket," + "b.module_name," + "a.issue_summary,"
					+ "ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') as created_on," + "t1.label as help_topic,"
					+ "t.label as ticket_status,"
					+ "(case when a.module='-1' then 'Others' else b.module_name end) as module_name  \r\n"
					+ "from ticket_generate a \r\n"
					+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
					+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
					+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
					+ "where  ticket_status ='2' order by id DESC";
			stmt = conn.prepareStatement(q);
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

	public List<Map<String, Object>> helpTopicFeedback() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select a.id," + "a.ticket," + "b.module_name," + "a.issue_summary,"
					+ "ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') as created_on," + "t1.label as help_topic,"
					+ "t.label as ticket_status,"
					+ "(case when a.module='-1' then 'Others' else b.module_name end) as module_name  \r\n"
					+ "from ticket_generate a \r\n"
					+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
					+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
					+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
					+ "where  help_topic ='1' and extract(month from now()) = extract(month from created_on) order by created_on DESC";
			stmt = conn.prepareStatement(q);
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

	public List<Map<String, Object>> helpTopicFeatureRequest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select a.id," + "a.ticket," + "b.module_name," + "a.issue_summary,"
					+ "ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') as created_on," + "t1.label as help_topic,"
					+ "t.label as ticket_status,"
					+ "(case when a.module='-1' then 'Others' else b.module_name end) as module_name \r\n"
					+ "from ticket_generate a \r\n"
					+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
					+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
					+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
					+ "where  help_topic ='5' and extract(month from now()) = extract(month from created_on) order by created_on DESC";
			stmt = conn.prepareStatement(q);
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

	public HD_TB_BISAG_TICKET_GENERATE getUploadScreenShotByid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from HD_TB_BISAG_TICKET_GENERATE where id=:id");
		q.setParameter("id", id);
		HD_TB_BISAG_TICKET_GENERATE list = (HD_TB_BISAG_TICKET_GENERATE) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public List<String> getUserNameAutoComplateList(String agent_name,String whr){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select l.username \r\n" + 
					"from \r\n" + 
					"logininformation l\r\n" + 
					"inner join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"inner join roleinformation r on ur.role_id = r.role_id\r\n" + 
					"where  enabled = '1' and lower(l.username) like ? "+whr+"\r\n" + 
					"order by r.role";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, "%"+agent_name.toLowerCase()+"%");
			ResultSet rs = stmt.executeQuery();
			System.err.println("Serr stmt " + stmt);
			while (rs.next()){
				list.add( rs.getString("username"));
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
				} catch (SQLException e) {}
			}
		}
		return list;
	}
	
	public List<String> getUseridByUserName(String userName){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select l.userid \r\n" + 
					"from \r\n" + 
					"logininformation l\r\n" + 
					"inner join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"inner join roleinformation r on ur.role_id = r.role_id\r\n" + 
					"where lower(l.username) = ? \r\n" + 
					"order by r.role";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, userName.toLowerCase());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				list.add( rs.getString("userid"));
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
				} catch (SQLException e) {}
			}
		}
		return list;
	}
	
	//Start Whats New
	public List<Map<String, Object>> getWhatsNewList(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session) {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_WhatsNew(Search);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select heading,description,created_dt,id,TO_CHAR(created_dt,'dd-mm-yyyy') as doc_date from tb_miso_whats_new " + SearchValue + " ORDER BY " + orderColunm + " "
					+ orderType + " limit " + pageLength + " OFFSET " + startPage;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_WhatsNew(stmt, Search);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String upload = "onclick=\"downloadDoc('"+rs.getString("id")+"')\"";
	    		String f2 =  "<i  class='btn fa fa-download' "+upload+" title='Download Doc.'></i>";
	    		columns.put("action",f2);
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

	public long getWhatsNewCount(String Search) {
		String SearchValue = GenerateQueryWhereClause_WhatsNew(Search);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from tb_miso_whats_new  " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_WhatsNew(stmt, Search);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
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
		return (long) total;
	}

	public String GenerateQueryWhereClause_WhatsNew(String Search) {
		String SearchValue = "";
		if (!Search.equals("")) {
			SearchValue = " where ( ";
			SearchValue += " lower(heading) like ? or lower(description) like ? or TO_CHAR(created_dt,'dd-mm-yyyy') like ? )";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_WhatsNew(PreparedStatement stmt, String Search){
		int flag = 0;
		try {
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
		} catch (Exception e) {
		}
		return stmt;
	}
	//End Whats New
}
