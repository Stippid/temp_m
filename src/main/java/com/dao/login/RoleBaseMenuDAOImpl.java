package com.dao.login;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_ROLEMASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.UserLogin;
import com.models.login.Tb_Miso_HTover;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Service
@Repository
public class RoleBaseMenuDAOImpl implements RoleBaseMenuDAO {

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int VisitorCounter() {
		int count = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "SELECT nextval('login_visitor_count')";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	
	public String getTodaysLogin() {
		String todayslogin="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "select count(*) as count from userlogincountinfo where to_char(createddate,'dd-mm-yyyy')=to_char(now(), 'dd-mm-yyyy')";
		
		
			PreparedStatement stmt = conn.prepareStatement(qry);
		

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				todayslogin = rs.getString(1);
			}
		
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todayslogin;
	}
	

	// Start Menu Query
	public ArrayList<ArrayList<String>> getModulelist(String roleid) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct b.module_name as module_name,a.moduleid  as moduleid from TB_LDAP_ROLEMASTER a, TB_LDAP_MODULE_MASTER b where a.roleid = ? and b.id > 0 and b.id = a.module.id  and  a.module.id != 0 ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("module_name"));
				list.add(rs.getString("moduleid"));

				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public ArrayList<ArrayList<String>> getSubModulelist(String moduleid, String roleid) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct b.submodule_name as submodule_name ,a.submoduleid  as submoduleid from TB_LDAP_ROLEMASTER a, TB_LDAP_SUBMODULE_MASTER b where a.roleid= ? and b.module_id = ? and  a.submoduleid = b.id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleid);
			stmt.setString(2, moduleid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("submodule_name"));
				list.add(rs.getString("submoduleid"));

				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}
//anu
	public String getAnalytics(int roleid,int userid)
	{
	Connection conn = null;
	try {
	
		//ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		//Connection conn = null;
		
	
			conn = dataSource.getConnection();
		
	
		String qry  ="select userid,roleid,screenid from tb_ldap_screen_analytics b where b.roleid=? and b.userid=? order by b.id";
		
		PreparedStatement stmt = conn.prepareStatement(qry);
		stmt.setInt(1, roleid);
		stmt.setInt(2, userid);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			return "yes";
		/*	ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("userid"));
			list.add(rs.getString("roleid"));
			list.add(rs.getString("screenid"));

			aList.add(list);*/
		}
					
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
	
	
		e.printStackTrace();
	}finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	return null;
}

	
	public ArrayList<ArrayList<String>> getScreenlist(String moduleid, String submoduleid, String roleid) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select distinct b.screen_name,b.screen_url , a.screenid from TB_LDAP_ROLEMASTER a , TB_LDAP_SCREEN_MASTER b where a.roleid=? and  a.module.id = ? and a.sub_module.id = ? and a.screen.id =  b.id ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, roleid);
			stmt.setString(2, moduleid);
			stmt.setString(3, submoduleid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("screen_name"));
				list.add(rs.getString("screen_url"));
				list.add(rs.getString("screenid"));

				aList.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	// End Menu Query

	@SuppressWarnings("unchecked")
	public Boolean getmoduleExist(String v) {
		String hql = "from TB_LDAP_MODULE_MASTER where module_name=:module_name ";
		List<TB_LDAP_MODULE_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("module_name", v);
			users = (List<TB_LDAP_MODULE_MASTER>) query.list();
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
	public Boolean getroleExist(String v) {
		String hql = "from Role where role=:role ";
		List<Role> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("role", v);
			users = (List<Role>) query.list();
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
	public Boolean getsubmoduleExist(String name, int m_id, int sm_id) {
		String hql = "";
		if (sm_id != 0) {
			hql = "from TB_LDAP_SUBMODULE_MASTER where (submodule_name=:submodule_name or module_id=:module_id) and id!=:id ";
		} else {
			hql = "from TB_LDAP_SUBMODULE_MASTER where submodule_name=:submodule_name  ";
		}
		List<TB_LDAP_SUBMODULE_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);

			query.setParameter("submodule_name", name);

			if (sm_id != 0) {
				query.setParameter("module_id", m_id);
				query.setParameter("id", sm_id);
			}
			users = (List<TB_LDAP_SUBMODULE_MASTER>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

		if (sm_id != 0) {
			if (users.size() > 1) {
				return true;
			}
			return false;
		} else {
			if (users.size() > 0) {
				return true;
			}
			return false;

		}

	}

	@SuppressWarnings("unchecked")
	public Boolean getscreenExist(String name, String url, int module_id, int sm_id, String sc_id) {
		String hql = "";
		if (sc_id.equals("") || sc_id == "" && url != null) {
			hql = "from TB_LDAP_SCREEN_MASTER where screen_name=:screen_name and screen_url=:screen_url and screen_module_id=:screen_module_id and screen_submodule_id=:screen_submodule_id ";
		} else {
			hql = "from TB_LDAP_SCREEN_MASTER where screen_name=:screen_name and screen_module_id=:screen_module_id and screen_submodule_id=:screen_submodule_id and id!=:id";
		}
		List<TB_LDAP_SCREEN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("screen_name", name);
			query.setParameter("screen_module_id", module_id);
			query.setParameter("screen_submodule_id", sm_id);

			if (sc_id != "" || !sc_id.equals("") && url == null) {
				query.setParameter("id", Integer.parseInt(sc_id));
			} else
				query.setParameter("screen_url", url);

			users = (List<TB_LDAP_SCREEN_MASTER>) query.list();
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
	public Boolean getscreenSubExist(String name, String url, String module_id, String screen_submodule_id) {
		String hql = "from TB_LDAP_SCREEN_MASTER where screen_name=:screen_name and screen_url=:screen_url and screen_module_id=:screen_module_id  and screen_submodule_id=:screen_submodule_id ";
		List<TB_LDAP_SCREEN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("screen_name", name);
			query.setParameter("screen_url", url);
			query.setParameter("screen_module_id", module_id);
			query.setParameter("screen_submodule_id", screen_submodule_id);
			users = (List<TB_LDAP_SCREEN_MASTER>) query.list();
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
	public Boolean getuserExist(String name) {
		String hql = "from UserLogin where userName=:userName ";
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("userName", name);
			users = (List<UserLogin>) query.list();
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
	public Boolean ScreenRedirect(String name, String roleid) {
		String hql = "from TB_LDAP_ROLEMASTER ro , TB_LDAP_SCREEN_MASTER sc where ro.screen.id = sc.id and sc.screen_url=:screen_url and ro.roleid=:roleid ";
		List<TB_LDAP_ROLEMASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("screen_url", name);
			query.setParameter("roleid", Integer.parseInt(roleid));
			
			users = (List<TB_LDAP_ROLEMASTER>) query.list();
		
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Boolean CheckDashboard(String name, String roleid) {
		String hql = "from Role r where r.role_url=:screen_url and r.roleId=:roleid ";
		List<TB_LDAP_ROLEMASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("screen_url", name);
			query.setParameter("roleid", Integer.parseInt(roleid));
			users = (List<TB_LDAP_ROLEMASTER>) query.list();
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
	public Boolean getlinkroleExist(int m_id, int sm_id, int s_id, int rid) {
		String hql = "from TB_LDAP_ROLEMASTER where module.id=:moduleid and sub_module.id=:submoduleid and screen.id=:screenid and roleid=:roleid ";
		List<TB_LDAP_SCREEN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("moduleid", m_id);
			query.setParameter("submoduleid", sm_id);
			query.setParameter("screenid", s_id);
			query.setParameter("roleid", rid);
			users = (List<TB_LDAP_SCREEN_MASTER>) query.list();
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

	///////////////// screen delete

	public void selectChild(String name, String url, String m_id, String sm_id) {
		String q1 = "select id FROM tb_ldap_screen_master  WHERE screen_name='" + name + "' and screen_url='" + url
				+ "' and screen_module_id='" + m_id + "' and screen_submodule_id='" + sm_id + "'";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(q1);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				deleteChild(rs.getInt("id"));
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

	}

	public void deleteChild(int sid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM TB_LDAP_SCREEN_MASTER where id=:id ";
		Query q = session.createQuery(hql).setInteger("id", sid);
		q.executeUpdate();
		String hql1 = "DELETE FROM TB_LDAP_ROLEMASTER where screen.id=:id ";
		Query q1 = session.createQuery(hql1).setInteger("id", sid);
		q1.executeUpdate();
		tx.commit();
		session.close();
	}

	/////////////// Module Report//////////
	public List<Map<String, Object>> ModuleSearchReport() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "Select id,module_name from tb_ldap_module_master where id > 0 order by id";
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put(metaData.getColumnLabel(2), rs.getObject(2));
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update('"
						+ rs.getObject(1) + "','" + rs.getObject(2) + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				columns.put(metaData.getColumnLabel(1), updateButton);

				list.add(columns);
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
		return list;
	}

	/////////// Sub Module Report //////////

	public List<Map<String, Object>> SubModuleSearchReport() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT sub.id, sub.submodule_name, sub.module_id,m.module_name FROM tb_ldap_submodule_master sub , tb_ldap_module_master m where  sub.module_id = m.id and m.id > 0";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				columns.put(metaData.getColumnLabel(2), rs.getObject(2));
				columns.put(metaData.getColumnLabel(4), rs.getObject(4));

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update('"
						+ rs.getObject(1) + "','" + rs.getObject(2) + "','" + rs.getObject(3)
						+ "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				columns.put(metaData.getColumnLabel(1), updateButton);

				list.add(columns);
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
		return list;
	}

	//////// Screen report /////////////////

	public List<Map<String, Object>> ScreenSearchReport(String m_id, String sm_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (m_id != "" && m_id != null && m_id != "null" && !m_id.equals("0")) {
				qry += "and screen_module_id = ? ";
			}
			if (sm_id != "" && sm_id != null && sm_id != "null" && sm_id != "0" && !sm_id.equals("0")) {
				qry += " and screen_submodule_id = ? ";
			}

			if (qry == "") {
				q = " select DISTINCT ON( s.screen_name)screen_name as screen_name,  s.id as id, s.screen_module_id as screen_module_id, s.screen_submodule_id as screen_submodule_id,m.module_name as module_name,sub.submodule_name as submodule_name,s.screen_url as screen_url from tb_ldap_screen_master s,tb_ldap_module_master m,tb_ldap_submodule_master sub where   cast(s.screen_module_id as int) = m.id and  cast(s.screen_submodule_id as int) = sub.id and m.id > 0";
			} else {
				q = "select DISTINCT ON( s.screen_name)screen_name as screen_name, s.id as id, s.screen_module_id as screen_module_id, s.screen_submodule_id as screen_submodule_id,m.module_name as module_name,sub.submodule_name as submodule_name ,s.screen_url as screen_url from tb_ldap_screen_master s,tb_ldap_module_master m,tb_ldap_submodule_master sub  where    cast(s.screen_module_id as int) = m.id and  cast(s.screen_submodule_id as int) = sub.id and m.id > 0"
						+ qry;
			}
			stmt = conn.prepareStatement(q);

			int j = 1;
			/*
			 * if(m_id != "" && m_id != null && m_id !="null" && !m_id.equals("0") ) {
			 * stmt.setString(j, m_id); j += 1; } if(sm_id != "" && sm_id !=null && sm_id
			 * !="null" && sm_id != "0" && !sm_id.equals("0") ) { stmt.setString(j, sm_id);
			 * j += 1; }
			 */

			if (m_id != "" && m_id != null && m_id != "null" && !m_id.equals("0")) {
				stmt.setInt(j, Integer.parseInt(m_id));
				j += 1;
			}
			if (sm_id != "" && sm_id != null && sm_id != "null" && sm_id != "0" && !sm_id.equals("0")) {
				stmt.setInt(j, Integer.parseInt(sm_id));
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

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){Update('"
						+ rs.getObject(2) + "','" + rs.getObject(1) + "','" + rs.getObject(7) + "','" + rs.getObject(3)
						+ "','" + rs.getObject(4) + "')}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				columns.put(metaData.getColumnLabel(2), updateButton);

				list.add(columns);
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
		return list;
	}

	public List<Map<String, Object>> RoleSearchReport() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "SELECT * FROM roleinformation ";

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
		return list;
	}

	@SuppressWarnings("unchecked")
	public Role getRoleNameListbyid(int role_id) {
		String hql = "FROM Role where roleId=:role_id order by id";
		List<Role> Role = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("role_id", role_id);
			Role = (List<Role>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (Role.size() > 0) {
			return Role.get(0);
		} else {
			return null;
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////

	public List<Map<String, Object>> SearchUserBbyRole1(String access_lvl, String subaccess_lvl, String user_name,
			String user_sus_no) {
		{

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if (access_lvl != "" && access_lvl != null && !access_lvl.equals("All")
						&& !access_lvl.equals("Username")) {
					qry += " and r.access_lvl = ?";
				}
				if (subaccess_lvl != "" && subaccess_lvl != null && !subaccess_lvl.equals("All")
						&& !subaccess_lvl.equals("Username")) {
					qry += " and r.sub_access_lvl  = ?";
				}
				if (user_sus_no != "" && user_sus_no != null && !user_sus_no.equals("All")
						&& !user_sus_no.equals("Username")) {
					if (access_lvl.equals("Line_dte")) {
						if (!user_sus_no.equals("0")) {
							qry += " and l.user_arm_code  = ?";
						}

					} else
						qry += " and l.user_sus_no  = ?";
				}

				if (access_lvl.equals("All")) {
					q = "select l.username, l.user_sus_no, l.user_formation_no, r. access_lvl,r.sub_access_lvl, r.role , l.user_arm_code, d.arm_desc from logininformation l "
							+ "left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
							+ " left join userroleinformation u on l.userid = u.user_id join roleinformation r  on  r.role_id =  u.role_id ";
				} else if (access_lvl.equals("Username")) {
					q = "select l.username, l.user_sus_no, l.user_formation_no, r. access_lvl,r.sub_access_lvl, r.role, l.user_arm_code, d.arm_desc from logininformation l "
							+ "left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
							+ " left join userroleinformation u on l.userid = u.user_id and l.username='" + user_name
							+ "' join roleinformation r  on  r.role_id =  u.role_id ";
				} else {
					q = "select l.username, l.user_sus_no, l.user_formation_no, r. access_lvl,r.sub_access_lvl, r.role, l.user_arm_code, d.arm_desc from logininformation l "
							+ "left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
							+ " left join userroleinformation u on l.userid = u.user_id join roleinformation r  on  r.role_id =  u.role_id "
							+ qry;
				}

				stmt = conn.prepareStatement(q);

				int j = 1;
				if (access_lvl != "" && access_lvl != null && !access_lvl.equals("All")
						&& !access_lvl.equals("Username")) {
					stmt.setString(j, access_lvl);
					j += 1;
				}
				if (subaccess_lvl != "" && subaccess_lvl != null && !subaccess_lvl.equals("All")
						&& !subaccess_lvl.equals("Username")) {
					stmt.setString(j, subaccess_lvl);
					j += 1;
				}
				if (user_sus_no != "" && user_sus_no != null && !user_sus_no.equals("All")
						&& !user_sus_no.equals("Username") && !user_sus_no.equals("0")) {
					stmt.setString(j, user_sus_no);
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
	}

	////////////////////////////////////////////////////////////////////////////////////

	public String getActiveData(String activeid) {
		Connection conn = null;
		Statement stmt;
		try {
			conn = dataSource.getConnection();
			stmt = (Statement) conn.createStatement();
			String sql = null;
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			sql = "update logininformation set enabled ='1',modified_on = '"+new Date()+"',ac_dc_date = '"+modifydate+"' where userId ='"+activeid+"' ";
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt1 = (Statement) conn.createStatement();
			String sql1 = "INSERT INTO temp.logininformation(userid,username,password,enabled,accountnonexpired,accountnonlocked,credentialsnonexpired,rights,name_nodal_officer ,address ,pincode,district,email,telephone,user_sus_no,user_arm_code,user_formation_no,login_name,created_on,modified_on,ac_dc_date) SELECT  * FROM public.logininformation WHERE public.logininformation.userid='"+activeid+"'  ";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			conn.close();
			return "User Actived Successfully";
		} catch (SQLException e) {
			return "User Activation Failed!!";
		}
	}

	public String getDeactiveData(String userid) {
		Connection conn = null;
		Statement stmt;
		try {
			conn = dataSource.getConnection();
			stmt = (Statement) conn.createStatement();
			String sql = null;
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			sql = "update logininformation set enabled ='0',modified_on = '" + new Date() + "',ac_dc_date = '"+ modifydate + "' where userId ='" + userid + "' ";
			stmt.executeUpdate(sql);
			stmt.close();
			Statement stmt1 = (Statement) conn.createStatement();
			String sql1 = "INSERT INTO temp.logininformation(userid,username,password,enabled,accountnonexpired,accountnonlocked,credentialsnonexpired,rights,name_nodal_officer ,address ,pincode,district,email,telephone,user_sus_no,user_arm_code,user_formation_no,login_name,created_on,modified_on,ac_dc_date) SELECT * FROM public.logininformation WHERE public.logininformation.userid='"+userid+"' ";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			conn.close();
			return "User Deactived Successfully";
		} catch (SQLException e) {
			return "User Deactivation Failed!!";
		}
	}

	// status of inactive user
	public List<Map<String, Object>> getReportStatusOfInactiveUserList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = null;
		try {
			conn = dataSource.getConnection();
			q = " SELECT t.* FROM ("
					+ "   select  DISTINCT l.userid,l.username , max(u.createddate)::date as date,( now()::date -max(u.createddate)::date ) as day1 from userlogincountinfo u inner join logininformation l on  u.userid=l.userid  group by 1,2  order by l.userid\r\n"
					+ ") t WHERE t.day1 > '30'";
			PreparedStatement stmt = conn.prepareStatement(q);
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

	///// user_mst update monika//////
	public UserLogin getUserLoginbyid(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from UserLogin where id=:id");
		q.setParameter("id", id);
		UserLogin list = (UserLogin) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public List<Map<String, Object>> getRole(int updateid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt = conn.prepareStatement(
					"SELECT l.userid,  l.username,  u_r.role_id,  r.role,  l.password,l.login_name,l.user_sus_no FROM  logininformation l, roleinformation r,  userroleinformation u_r WHERE  l.userid = u_r.user_id AND u_r.role_id = r.role_id and userid= ? ");
			stmt.setInt(1, updateid);
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

	/*public String UpdateUserMst(UserLogin updateid, int roll, String user_arm_code, String user_sus_no,
			String formation_code, String access_lve1, String sub_access_lve1,String name,String rank,String username) {

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if (access_lve1.equals("Formation")) {
			updateid.setUser_formation_no(formation_code);
		} else {
			updateid.setUser_formation_no("null");
		}
		if (sub_access_lve1.equals("Arm")) {
			updateid.setUser_Arm_code(user_arm_code);
		} else {
			updateid.setUser_Arm_code("0");
		}
		String hql = "update UserLogin set login_name=:login_name,password=:password,modified_on='" + new Date()
				+ "',user_sus_no=:user_sus_no,"
				+ "user_arm_code=:user_arm_code,user_formation_no=:user_formation_no,army_no=:army_no ,name =:name ,rank =:rank,modified_by=:modified_by  where userid =:userid";
		Query query = session.createQuery(hql).setString("user_sus_no", updateid.getUser_sus_no())
				.setString("password", updateid.getPassword()).setString("login_name", updateid.getLogin_name())
				.setString("user_arm_code", updateid.getUser_Arm_code())
				.setString("user_formation_no", updateid.getUser_formation_no())
				.setString("name",name).setString("modified_by",username).setInteger("rank",Integer.parseInt(rank))
				.setString("army_no", updateid.getArmy_no()).setInteger("userid", updateid.getUserId());
		query.executeUpdate();
		String hql1 = "update UserRole set role_id=:role_id,modified_on='" + new Date() + "' where user_id =:user_id";
		Query query1 = session.createQuery(hql1).setInteger("role_id", roll).setInteger("user_id",
				updateid.getUserId());
		int rowCount1 = query1.executeUpdate();
		tx.commit();
		session.close();
		String in = userinsertdata("update", updateid.getUserId(), roll);

		if (rowCount1 > 0 && in.equals("0")) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}*/
	
	public String UpdateUserMst(UserLogin updateid, int roll, String user_arm_code, String user_sus_no,
			String formation_code, String access_lve1, String sub_access_lve1,String name,String rank,String username) {
		
		String from_army_no = getUserLoginbyid(updateid.getUserId()).getArmy_no();
		int flag = 0;
		
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if (access_lve1.equals("Formation")) {
			updateid.setUser_formation_no(formation_code);
		} else {
			updateid.setUser_formation_no("null");
		}
		if (sub_access_lve1.equals("Arm")) {
			updateid.setUser_Arm_code(user_arm_code);
		} else {
			updateid.setUser_Arm_code("0");
		}
		String hql = "update UserLogin set login_name=:login_name,password=:password,modified_on='" + new Date()
				+ "',user_sus_no=:user_sus_no,"
				+ "user_arm_code=:user_arm_code,user_formation_no=:user_formation_no,army_no=:army_no ,name =:name ,rank =:rank,modified_by=:modified_by  where userid =:userid";
		Query query = session.createQuery(hql).setString("user_sus_no", updateid.getUser_sus_no())
				.setString("password", updateid.getPassword()).setString("login_name", updateid.getLogin_name())
				.setString("user_arm_code", updateid.getUser_Arm_code())
				.setString("user_formation_no", updateid.getUser_formation_no())
				.setString("name",name).setString("modified_by",username).setInteger("rank",Integer.parseInt(rank))
				.setString("army_no", updateid.getArmy_no()).setInteger("userid", updateid.getUserId());
		int rowupdated = query.executeUpdate();
		String hql1 = "update UserRole set role_id=:role_id,modified_on='" + new Date() + "' where user_id =:user_id";
		Query query1 = session.createQuery(hql1).setInteger("role_id", roll).setInteger("user_id",
				updateid.getUserId());
		int rowCount1 = query1.executeUpdate();
		
		if(rowCount1 >= 1 && rowupdated >= 1) {
			Date date = new Date();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Tb_Miso_HTover ht = new Tb_Miso_HTover();
			sessionHQL.beginTransaction();
			ht.setHt_type("Asigma");
			ht.setHt_reason("PermanentPosting");
			ht.setTo_army_no(updateid.getArmy_no());
			ht.setFrom_army_no(from_army_no);
			ht.setFrom_userid(updateid.getUserId());
			ht.setTo_userid(updateid.getUserId());
			ht.setFrom_username(getUserLoginbyid(updateid.getUserId()).getUserName());
			ht.setTo_username(getUserLoginbyid(updateid.getUserId()).getUserName());
			ht.setCreated_on(date);
			ht.setCreated_by(username);
			flag = (int) sessionHQL.save(ht);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
		}
		tx.commit();
		session.close();
		String in = userinsertdata("update", updateid.getUserId(), roll);

		if (rowCount1 > 0 && in.equals("0")) {
			return "Updated Successfully";
		} else {
			return "Updated not Successfully";
		}
	}

	public String userinsertdata(String type, int i, int roll) {
		Connection conn = null;
		Statement stmt;

		try {
			conn = dataSource.getConnection();
			stmt = (Statement) conn.createStatement();
			String sql = null;
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			if (type.equals("insert")) {
				sql = "INSERT INTO temp.logininformation(userid,username, password, enabled,accountnonexpired, accountnonlocked, credentialsnonexpired"
						+ ",  name_nodal_officer, address, pincode,  email, telephone,  user_sus_no, user_arm_code, user_formation_no, login_name, created_on,ac_dc_date )"
						+ " SELECT userid,username,password,enabled,accountnonexpired,accountnonlocked,credentialsnonexpired,name_nodal_officer,address,pincode, email, telephone,  user_sus_no, user_arm_code, user_formation_no,  login_name,'"
						+ new Date() + "','" + modifydate
						+ "'  FROM public.logininformation where public.logininformation.userid ='" + i + "'";
			} else if (type.equals("update")) {
				sql = "INSERT INTO temp.logininformation(userid,username, password, enabled,accountnonexpired, accountnonlocked, credentialsnonexpired"
						+ ",  name_nodal_officer, address, pincode,  email, telephone,  user_sus_no, user_arm_code, user_formation_no, login_name, created_on )"
						+ " SELECT userid,username,password,enabled,accountnonexpired,accountnonlocked,credentialsnonexpired,name_nodal_officer,address,pincode, email, telephone,  user_sus_no, user_arm_code, user_formation_no,  login_name,'"
						+ new Date() + "'  FROM public.logininformation where public.logininformation.userid ='" + i
						+ "'";
			}

			stmt.executeUpdate(sql);
			stmt.close();

			Statement stmt1 = (Statement) conn.createStatement();
			String sql1 = "INSERT INTO temp.userroleinformation(user_role_id,user_id, role_id, created_on ) "
					+ " SELECT user_role_id, " + i + ",'" + roll + "','" + new Date()
					+ "' FROM public.userroleinformation where public.userroleinformation.user_id ='" + i + "' ";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			conn.close();
			return "0";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "1";
	}

	public List<Map<String, Object>> getUserReport() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String q = "select l.userid,l.username, l.user_sus_no, l.user_formation_no,r.role_id,"
					+ "l.password,l.login_name, r.role, r. access_lvl,r.sub_access_lvl, r.role , l.user_arm_code, d.arm_desc from logininformation l "
					+ " left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
					+ " left join userroleinformation u on l.userid = u.user_id"
					+ " join roleinformation r  on  r.role_id =  u.role_id  where l.enabled='1'";

			/*
			 * String q =
			 * "SELECT l.userid,  l.username,  u_r.role_id,  r.role,  l.password,l.login_name,l.user_sus_no,l.user_arm_code,l.user_formation_no  FROM  logininformation l, roleinformation r,  userroleinformation u_r "
			 * + " WHERE  l.userid = u_r.user_id AND u_r.role_id = r.role_id";
			 */
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
		return list;
	}

	@SuppressWarnings("unchecked")
	public Boolean DashboardRedirect(String name, String roleid) {
		String hql = "from TB_LDAP_ROLEMASTER ro  where ro.role_url=:role_url and ro.role_id=:role_id ";
		List<TB_LDAP_ROLEMASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("role_url", name);
			query.setParameter("role_id", Integer.parseInt(roleid));
			users = (List<TB_LDAP_ROLEMASTER>) query.list();
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

	////////////
	public DataSet<Map<String, Object>> DatatablesCriteriasUserreport(DatatablesCriterias criterias, String qry,
			String roleSubAccess) {
		List<Map<String, Object>> metadata = findDepartmentCriteriasforma1(criterias, qry, roleSubAccess);
		Long countFiltered = getFilteredCountfo(criterias, qry);
		Long count = getTotalCountfo(qry);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}

	private List<Map<String, Object>> findDepartmentCriteriasforma1(DatatablesCriterias criterias, String qry,String roleSubAccess) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (qry.equals("")) {
				q = "select distinct l.userid,l.user_sus_no, l.username, l.login_name, r.role, l.army_no ,case when (select count(id) from tb_psg_trans_proposed_comm_letter where  personnel_no =l.army_no) >0 then  concat (rnk.description,' ',tpc.name)\r\n" + 
						"else concat (rnks.description,' ',l.name) end as name, d.arm_desc,case when l.modified_on is null then  ltrim(TO_CHAR(l.created_on,'DD-MON-YYYY'),'0')\r\n" + 
						"else  ltrim(TO_CHAR(l.modified_on,'DD-MON-YYYY'),'0') end as modified_date,case when l.modified_by is null then  l.created_by else  l.modified_by end as modified_by,udtl.unit_name, \r\n" + 
						"ltrim(TO_CHAR(h.created_on,'DD-MON-YYYY'),'0') as ht_date \r\n" +
						"from logininformation l \r\n" + 
						"left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code \r\n" + 
						"left join userroleinformation u on l.userid = u.user_id\r\n" + 
						"join roleinformation r  on  r.role_id =  u.role_id\r\n" + 
						"left join  tb_psg_trans_proposed_comm_letter tpc on tpc.personnel_no = l.army_no\r\n" + 
						"left join cue_tb_psg_rank_app_master rnk on  rnk.id = tpc.rank\r\n" + 
						"left join cue_tb_psg_rank_app_master rnks on rnks.id = l.rank "
						+"left join tb_miso_htover h on h.from_username=l.username"
						+ " left join tb_miso_orbat_unt_dtl udtl  on udtl.sus_no =l.user_sus_no and udtl.status_sus_no= 'Active' " 
						+ " where l.enabled='1'  order by l.userid limit "
						+ criterias.getDisplaySize() + " OFFSET " + criterias.getDisplayStart();
			} else {
//				q = "select l.userid,l.username, l.user_sus_no,"
//						+ " l.login_name, r.role, r.access_lvl,r.sub_access_lvl, d.arm_desc,l.army_no "
//						+ " from logininformation l "
//						+ " left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
//						+ " left join userroleinformation u on l.userid = u.user_id"
//						+ " join roleinformation r  on  r.role_id =  u.role_id  where l.enabled='1' " + qry
//						+ " order by l.userid limit " + criterias.getDisplaySize() + " OFFSET "
//						+ criterias.getDisplayStart();
				
				q = "select distinct l.userid, l.user_sus_no,l.username, l.login_name, r.role,l.army_no,case when (select count(id) from tb_psg_trans_proposed_comm_letter where  personnel_no =l.army_no) >0 then  concat (rnk.description,' ',tpc.name)\r\n" + 
						"else concat (rnks.description,' ',l.name) end as name, d.arm_desc,\r\n" + 
						"case when l.modified_on is null then  ltrim(TO_CHAR(l.created_on,'DD-MON-YYYY'),'0')\r\n" + 
						"else  ltrim(TO_CHAR(l.modified_on,'DD-MON-YYYY'),'0') end as modified_date,case when l.modified_by is null then  l.created_by else  l.modified_by end as modified_by, \r\n" + 
						"ltrim(TO_CHAR((select MAX(h.created_on) from tb_miso_htover h where h.from_username=l.username ),'DD-MON-YYYY'),'0') as ht_date,udtl.unit_name \r\n" +
						"from logininformation l \r\n" + 
						"left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code \r\n" + 
						"left join userroleinformation u on l.userid = u.user_id \r\n" + 
						"join roleinformation r  on  r.role_id =  u.role_id \r\n" + 
						"left join  tb_psg_trans_proposed_comm_letter tpc on tpc.personnel_no = l.army_no\r\n" + 
						"left join cue_tb_psg_rank_app_master rnk on  rnk.id = tpc.rank "
						+ "left join cue_tb_psg_rank_app_master rnks on rnks.id = l.rank  "
						+"left join tb_miso_htover h on h.from_username=l.username"
						+ " left join tb_miso_orbat_unt_dtl udtl  on udtl.sus_no =l.user_sus_no and udtl.status_sus_no= 'Active' "
						+ "where l.enabled='1' " + qry
						+ " order by l.userid limit " + criterias.getDisplaySize() + " OFFSET "
						+ criterias.getDisplayStart();
			}
			q += getFilterQueryfo(criterias, q);
			if (criterias.hasOneSortedColumn()) {
				List<String> orderParams = new ArrayList<String>();
				Iterator<String> itr2 = orderParams.iterator();
				while (itr2.hasNext()) {
					q += itr2.next();
					if (itr2.hasNext()) {
						q += " , ";
					}
				}
			}
			stmt = conn.prepareStatement(q);
			System.err.println("user list: \n"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int count = 0;
			while (rs.next()) {
				count += 1;
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("sr", criterias.getDisplayStart() + count);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				System.err.println("rs.getObject(1)  -- " + rs.getObject(1));
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
						+ rs.getObject(1) + " ,'"+rs.getObject(2)+"')}else{ return false;}\"";
				String updateButton = "<i   class='action_icons action_update' " + Update + " title='Edit Data'></i>";
				String f = "";
				
				String View = "onclick=\"  if (confirm('Are you sure you want to view History?') ){Pop_Up_History("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String viewButton = "<i   class='fa fa-eye' style='font-size:24px;color:red' " + View + " title='View Data'></i>";
				
				f += updateButton;
				f += viewButton;
				columns.put("Action", f);
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
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
	
	private Long getFilteredCountfo(DatatablesCriterias criterias, String qry) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select l.userid,l.username, l.user_sus_no, l.user_formation_no,r.role_id,"
					+ "l.password,l.login_name, r.role, r. access_lvl,r.sub_access_lvl, r.role , l.user_arm_code, d.arm_desc,l.army_no from logininformation l "
					+ " left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
					+ " left join userroleinformation u on l.userid = u.user_id"
					+ " join roleinformation r  on  r.role_id =  u.role_id  where l.enabled='1' " + qry
					+ " order by l.userid";
			q += getFilterQueryfo(criterias, q);
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
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return Long.parseLong(String.valueOf(list.size()));
	}

	private static StringBuilder getFilterQueryfo(DatatablesCriterias criterias, String queryBuilder2) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder2.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("userid")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" " + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(" + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
				}
			}
			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountfo(String qry) {
		int columnCount = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select COUNT(l.userid) as total from logininformation l "
					+ " left join tb_miso_orbat_arm_code d on l.user_arm_code= d.arm_code"
					+ " left join userroleinformation u on l.userid = u.user_id"
					+ " join roleinformation r  on  r.role_id =  u.role_id  where l.enabled='1' " + qry;
			
			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();
			//ResultSetMetaData metaData = rs.getMetaData();
			//columnCount = metaData.getColumnCount();
			while (rs.next()) {
				columnCount = Integer.parseInt(rs.getString("total"));
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
		return (long) columnCount;
	}

	/////// Active Deavtive report////////////

	public DataSet<Map<String, Object>> DatatablesCriteriasActiveUserreport(DatatablesCriterias criterias, String qry,
			String roleSubAccess, String Status) { //

		List<Map<String, Object>> metadata = findDepartmentCriteriasActive(criterias, qry, roleSubAccess, Status); //
		Long countFiltered = getFilteredCountActive(criterias, qry); //
		Long count = getTotalCountfoactive(qry); //
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}

	private List<Map<String, Object>> findDepartmentCriteriasActive(DatatablesCriterias criterias, String qry,
			String roleSubAccess, String Status) {
		StringBuilder queryBuilder = null;
		if (qry.equals("")) {
			queryBuilder = new StringBuilder("FROM UserLogin d ");
		} else {
			queryBuilder = new StringBuilder("FROM UserLogin d where " + qry);
		}
		queryBuilder.append(getFilterQueryfoactive(criterias, queryBuilder));
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		@SuppressWarnings("unchecked")
		List<UserLogin> list1 = (List<UserLogin>) q.list();
		tx.commit();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list1.size(); i++) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			columns.put("sr", criterias.getDisplayStart() + i + 1);
			columns.put("login_name", list1.get(i).getLogin_name());
			columns.put("userName", list1.get(i).getUserName());
			columns.put("ac_dc_date", list1.get(i).getAc_dc_date());
			String ActiveButton = "";
			String DeactiveButton = "";
			if (Status.equals("0")) {
				String Active = "onclick=\"  if (confirm('Are you sure you want to Active?') ){ActiveData(" + list1.get(i).getUserId() + ")}else{ return false;}\"";
				ActiveButton = "<i id='thAction1' style='color: blue; text-decoration: underline;font-weight:bold;' " + Active + " title='Active Data'>Active</i>";
			} else if (Status.equals("1")) {
				String Deactive = "onclick=\"  if (confirm('Are you sure you want to Deactive?') ){DeactiveData(" + list1.get(i).getUserId() + ")}else{ return false;}\"";
				DeactiveButton = "<i id='thAction1' style='color: blue; text-decoration: underline;font-weight:bold;' " + Deactive + " title='Active Data'>Deactive</i>";
			}
			String f = "";
			if (Status.equals("0")) {
				f += ActiveButton;
			} else if (Status.equals("1")) {
				f += DeactiveButton;
			}
			columns.put("Action", f);
			list.add(columns);
		}
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCountActive(DatatablesCriterias criterias, String qry) { //
		StringBuilder queryBuilder = null;
		if (qry.equals("")) {
			queryBuilder = new StringBuilder("SELECT d FROM UserLogin d ");
		} else {
			queryBuilder = new StringBuilder("SELECT d FROM UserLogin d where " + qry + " ");
		}
		queryBuilder.append(getFilterQueryfoactive(criterias, queryBuilder));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		List<UserLogin> list = (List<UserLogin>) q.list();
		tx.commit();
		session.close();
		return Long.parseLong(String.valueOf(list.size()));
	}
	private static StringBuilder getFilterQueryfoactive(DatatablesCriterias criterias, StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if (!queryBuilder1.toString().contains("where")) {
				queryBuilder.append(" WHERE ");
			} else {
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if (columnDef.getName().equalsIgnoreCase("ac_dc_date") || columnDef.getName().equalsIgnoreCase("sr")
							|| columnDef.getName().equalsIgnoreCase("Action")) {
						if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					} else {
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
				}
			}
			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
			queryBuilder.append(")");
		}
		return queryBuilder;
	}

	private Long getTotalCountfoactive(String qry) { //
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (qry.equals("")) {
			q = session.createQuery("SELECT COUNT(d) FROM UserLogin d ");
		} else {
			q = session.createQuery("SELECT COUNT(d) FROM UserLogin d where " + qry);
		}
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	public List<String> getLayoutlist() {
		List<String> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "SELECT t.msg from tb_login_mercuries t WHERE t.valid_upto >= now()";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("msg"));
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
	
	public List<Map<String, Object>> getLine_DteList(String line_dte_sus) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String whr="";
			if(!line_dte_sus.equals("")) {
				whr = " and line_dte_sus = ?";
			}
			q = "select distinct line_dte_sus,line_dte_name from tb_miso_orbat_line_dte where line_dte_sus is not null and line_dte_sus !='' "+whr+" order by line_dte_name  ";
			stmt = conn.prepareStatement(q);
			if(!line_dte_sus.equals("")) {
				stmt.setString(1, line_dte_sus);
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
	
	
	public List<Map<String, Object>> getLine_DteListit_ass(String line_dte_sus) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String whr="";
			if(!line_dte_sus.equals("")) {
				whr = " and line_dte_sus = ?";
			}
			q = "select distinct line_dte_sus,line_dte_name from tb_miso_orbat_line_dte where line_dte_sus is not null and line_dte_sus !='' "+whr+" order by line_dte_name  ";
			stmt = conn.prepareStatement(q);
			if(!line_dte_sus.equals("")) {
				stmt.setString(1, line_dte_sus);
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
	
	
	public List<String> nMgtSct(HttpSession n1) {
		List<String> list = new ArrayList<>();
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "SELECT t.msg from tb_login_mercuries t WHERE t.valid_upto >= now()";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("msg"));
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
		}*/
		String getval="0";
		String role=n1.getAttribute("role").toString();
		if (role.toUpperCase().indexOf("FP")>=0) {
			getval="1";	
		}
		if (role.toUpperCase().indexOf("COR")>=0) {
			getval="1";	
		}
		if (role.toUpperCase().indexOf("DV")>=0) {
			getval="1";	
		}
		list.add(getval);
		return list;
	}
	public List<Map<String, Object>> getLine_DteListsus() {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String whr="";
			
			q = "select distinct line_dte_sus,line_dte_name from tb_miso_orbat_line_dte where line_dte_sus != '' order by line_dte_name  ";
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
	public ArrayList<ArrayList<String>> GetUsernameandRank(String username1){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
			try{			
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;		
					q="select distinct case when (select count(id) from tb_psg_trans_proposed_comm_letter where  personnel_no =l.army_no) >0 then tpc.name\r\n" + 
							"else l.name end as name,\r\n" + 
							" case when (select count(id) from tb_psg_trans_proposed_comm_letter where  personnel_no =l.army_no) >0 then tpc.rank\r\n" + 
							"else l.rank end as rank \r\n" + 
							"from logininformation l \r\n" + 
							"left join  tb_psg_trans_proposed_comm_letter tpc on tpc.personnel_no = l.army_no\r\n" + 
							"left join cue_tb_psg_rank_app_master rnks on rnks.id = l.rank \r\n" + 
							"left join cue_tb_psg_rank_app_master rnk on  rnk.id = tpc.rank\r\n" + 
							"where l.username= ? " ;				
				stmt=conn.prepareStatement(q);
				stmt.setString(1, username1);
				//stmt.setString(2, army_no);
				
				ResultSet rs = stmt.executeQuery();   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("name"));
		    	  list.add(rs.getString("rank"));
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
	
	public List<Map<String, Object>> getLine_DteList11(String line_dte_sus) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String whr="";
			if(!line_dte_sus.equals("")) {
				whr = " and arm_code in (SELECT arm_code FROM tb_miso_orbat_unt_dtl where sus_no=?)";
			}
			q = "select distinct line_dte_sus,line_dte_name from tb_miso_orbat_line_dte  where line_dte_sus is not null and line_dte_sus !='' "+whr+"";
			stmt = conn.prepareStatement(q);
			
			if(!line_dte_sus.equals("")) {
				stmt.setString(1, line_dte_sus);
			}
			System.out.println("a====== " + stmt);
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
	
	public int dailyVisitorCounter() {
		int count = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "SELECT nextval('daily_visitor_count')";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	public int monthlyVisitorCounter() {
		int count = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql = "SELECT nextval('monthly_visitor_count')";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	public String getMonthlyLogin() {
		String montlyLogin="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "select count(*) c  from userlogincountinfo where extract(year from createddate)=extract(year from now()) and extract(month from createddate)=extract(month from now())";
		
		
			PreparedStatement stmt = conn.prepareStatement(qry);
		

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				montlyLogin = rs.getString(1);
			}
		
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return montlyLogin;
	}
	
	public String getYearlyLogin() {
		String yearlyLogin="";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "select count(*) c  from userlogincountinfo where extract(year from createddate)=extract(year from now())";
		
		
			PreparedStatement stmt = conn.prepareStatement(qry);
		

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				yearlyLogin = rs.getString(1);
			}
		
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yearlyLogin;
	}
	
	
	// leaderboard 
	
	public List<Map<String, Object>> get_comnd_wise_count_list(HttpSession sessionUserId)
	{	
    
   List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";


	try{     
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			q = "SELECT a.cmd,\r\n"
					+ "a.form_code_control,\r\n"
					+ "       a_veh_percentage,\r\n"
					+ "       b_veh_percentage,\r\n"
					+ "       c_veh_percentage,\r\n"
					+ "       wpn_eqpt_percentage,\r\n"
					+ "       it_asset,\r\n"
					+ "       avg_of3008,\r\n"
					+ "       avg_of3009,\r\n"
					+ "       round(avg((a.a_veh_percentage + a.b_veh_percentage + a.c_veh_percentage + a.wpn_eqpt_percentage  +a.avg_of3008 + a.avg_of3009) / 6), 2) AS over_all_percentage,\r\n"
					+ "       CASE WHEN a.cmd = 'HQ NORTHERN COMMAND'        THEN 'login_file/nc.png'\r\n"
					+ "            WHEN a.cmd = 'HQ EASTERN COMMAND'         THEN 'login_file/ec.png'\r\n"
					+ "            WHEN a.cmd = 'HQ WESTERN COMMAND'         THEN 'login_file/wc.png'\r\n"
					+ "            WHEN a.cmd = 'HQ CENTRAL COMMAND'         THEN 'login_file/cc.png'\r\n"
					+ "            WHEN a.cmd = 'HQ SOUTHERN COMMAND'        THEN 'login_file/sc.png'\r\n"
					+ "            WHEN a.cmd = 'HQ ARMY TRG COMMAND [ARTRAC]' THEN 'login_file/tc.png'\r\n"
					+ "            WHEN a.cmd = 'HQ SOUTH WESTERN COMMAND'   THEN 'login_file/swc.png'\r\n"
					+ "            ELSE NULL\r\n"
					+ "             END AS src_path\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT fv.unit_name AS cmd,\r\n"
					+ "               fv.form_code_control,\r\n"
					+ "               round(coalesce((sum(a.a_veh) * 100) / nullif(sum(a.a_veh_total), 0), 0), 0) AS a_veh_percentage,\r\n"
					+ "               round(coalesce((sum(a.b_veh) * 100) / nullif(sum(a.b_veh_total), 0), 0), 0) AS b_veh_percentage,\r\n"
					+ "               round(coalesce((sum(a.c_veh) * 100) / nullif(sum(a.c_veh_total), 0), 0), 0) AS c_veh_percentage,\r\n"
					+ "               round(coalesce((sum(a.wpn_eqpt) * 100) / nullif(sum(a.wpn_eqpt_total), 0), 0), 0) AS wpn_eqpt_percentage,\r\n"
					+ "               sum(a.peri) + sum(a.computing) AS it_asset,\r\n"
					+ "               round((sum(a.iaff3008) * 100) / nullif((SELECT count(*) FROM tb_miso_orbat_unt_dtl b WHERE substring(b.form_code_control, 1, 1) = substring(fv.form_code_control, 1, 1) AND b.status_sus_no = 'Active'), 0), 0) AS avg_of3008,\r\n"
					+ "               round((sum(a.iaff3009) * 100) / nullif((SELECT count(*) FROM tb_miso_orbat_unt_dtl b WHERE substring(b.form_code_control, 1, 1) = substring(fv.form_code_control, 1, 1) AND b.status_sus_no = 'Active'), 0), 0) AS avg_of3009\r\n"
					+ "          FROM (\r\n"
					+ "                SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       count(dt.ba_no) AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_mvcr_parta_dtl dt\r\n"
					+ "                 WHERE to_char(dt.modify_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       count(dt.ba_no) AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_mvcr_parta_dtl dt\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       count(dt.em_no) AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_emar_report dt\r\n"
					+ "                 WHERE to_char(dt.modify_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       count(dt.em_no) AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_emar_report dt\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       count(dt.ba_no) AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_census_retrn dt\r\n"
					+ "                 WHERE to_char(dt.dt_of_submsn, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       count(dt.ba_no) AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_tms_census_retrn dt\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       count(eqpt_regn_no) AS wpn_eqpt,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM mms_tb_regn_mstr_detl dt\r\n"
					+ "                 INNER JOIN mms_tb_mlccs_mstr_detl m\r\n"
					+ "                    ON dt.census_no = m.census_no\r\n"
					+ "                  LEFT JOIN cue_tb_miso_mms_item_mstr i\r\n"
					+ "                    ON m.item_code::text = i.item_code::text\r\n"
					+ "                 WHERE to_char(dt.data_upd_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn,\r\n"
					+ "                       count(eqpt_regn_no) AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM mms_tb_regn_mstr_detl dt\r\n"
					+ "                 INNER JOIN mms_tb_mlccs_mstr_detl m\r\n"
					+ "                    ON dt.census_no = m.census_no\r\n"
					+ "                  LEFT JOIN cue_tb_miso_mms_item_mstr i\r\n"
					+ "                    ON m.item_code::text = i.item_code::text\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       count(id) AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_psg_iaff_3009_main dt\r\n"
					+ "                 WHERE to_char(dt.modified_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       count(id) AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM tb_psg_iaff_3008_main dt\r\n"
					+ "                 WHERE to_char(dt.modified_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                   AND status = 1\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       count(id) AS peri,\r\n"
					+ "                       0 AS computing\r\n"
					+ "                  FROM it_asset_peripherals dt\r\n"
					+ "                 WHERE to_char(dt.created_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                   AND status = 1\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "             UNION ALL SELECT DISTINCT dt.sus_no,\r\n"
					+ "                       0 AS a_veh,\r\n"
					+ "                       0 AS a_veh_total,\r\n"
					+ "                       0 AS b_veh,\r\n"
					+ "                       0 AS b_veh_total,\r\n"
					+ "                       0 AS c_veh,\r\n"
					+ "                       0 AS c_veh_total,\r\n"
					+ "                       0 AS wpn,\r\n"
					+ "                       0 AS wpn_eqpt_total,\r\n"
					+ "                       0 AS iaff3008,\r\n"
					+ "                       0 AS iaff3009,\r\n"
					+ "                       0 AS peri,\r\n"
					+ "                       count(id) AS computing\r\n"
					+ "                  FROM it_asset_peripherals dt\r\n"
					+ "                 WHERE to_char(dt.created_date, 'MM-YYYY') = to_char(CURRENT_DATE, 'MM-YYYY')\r\n"
					+ "                   AND status = 1\r\n"
					+ "                 GROUP BY dt.sus_no\r\n"
					+ "               ) a\r\n"
					+ "         INNER JOIN tb_miso_orbat_unt_dtl orb\r\n"
					+ "            ON orb.sus_no = a.sus_no\r\n"
					+ "           AND orb.status_sus_no = 'Active'\r\n"
					+ "         INNER JOIN all_fmn_view fv\r\n"
					+ "            ON substring(orb.form_code_control, 1, 1) = substring(fv.form_code_control, 1, 1)\r\n"
					+ "           AND fv.level_in_hierarchy = 'Command'\r\n"
					+ "        \r\n"
					+ "         GROUP BY fv.unit_name,\r\n"
					+ "                  fv.form_code_control\r\n"
					+ "       )a\r\n"
					+ "       where substring(a.form_code_control, 1, 1) in ('5','2','3','4','1','6','8')\r\n"
					+ " GROUP BY a.cmd,\r\n"
					+ "          a.a_veh_percentage,\r\n"
					+ "          a.b_veh_percentage,\r\n"
					+ "          a.c_veh_percentage,\r\n"
					+ "          a.wpn_eqpt_percentage,\r\n"
					+ "          a.it_asset,\r\n"
					+ "          a.avg_of3008,\r\n"
					+ "          a.avg_of3009,\r\n"
					+ "          a.form_code_control\r\n"
					+ " ORDER BY over_all_percentage DESC";
			
		
		
			stmt=conn.prepareStatement(q);	
	      ResultSet rs = stmt.executeQuery();   
	      System.out.println("dash: " + stmt);
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
		return list;
	} 
	
	//raj 28.10.2024
	
	//RAJ 14.10.2024 VALUE ARMCODE FOR LINE DTE LIST 
		public List<Map<String, Object>> getLine_DteList_arm_code(String line_dte_sus) {
			String q = null;
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String whr="";
				if(!line_dte_sus.equals("")) {
					whr = " and line_dte_sus = ?";
				}
				q = "select distinct arms from tb_miso_orbat_line_dte where line_dte_sus is not null and line_dte_sus !='' and arm_code is not null and arm_code !='' "+whr+" order by line_dte_name  ";
				stmt = conn.prepareStatement(q);
				if(!line_dte_sus.equals("")) {
					stmt.setString(1, line_dte_sus);
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
	

		public ArrayList<ArrayList<String>> Popup_User_History(BigInteger user_id) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			
				q= " select log.username,ht.ht_reason,ht.ht_type,ht.from_army_no,ht.to_army_no,ht.created_by,ht.created_on \r\n" + 
						"from tb_miso_htover ht \r\n" + 
						"left join logininformation log ON log.username = ht.from_username " +
						"left join userroleinformation roleinf ON roleinf.user_id::text = ht.from_userid::text\r\n " + 
						"where ht.from_userid =? ORDER BY ht.id DESC";
				
						
					stmt=conn.prepareStatement(q);
					
					stmt.setString(1,user_id.toString());
					
					ResultSet rs = stmt.executeQuery();      
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("username"));//0
						list.add(rs.getString("ht_reason"));//1
						list.add(rs.getString("ht_type"));//2
						list.add(rs.getString("from_army_no"));//3
						list.add(rs.getString("to_army_no"));//4
						list.add(rs.getString("created_by"));//5
						list.add(rs.getString("created_on"));//6
						
						
						
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
}