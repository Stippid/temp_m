package com.dao.tms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtil;

public class Tms_Ad_HocDaoImpl implements Tms_Ad_HocDao{
	
	

	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	

	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	public List<Map<String, Object>> get_tms_catgory_List()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q ="select codevalue,label from t_domain_value where domainid='tms' order by id"; 

				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
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
	
	
	public List<String> getTableNameList() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//String sql1 = "SELECT table_name FROM information_schema.tables where table_catalog='miso_psg_olap' and table_schema='public'";
			String sql1 = "	 select table_schema as schema_name,\r\n" + 
					"       table_name as view_name\r\n" + 
					"from information_schema.views\r\n" + 
					"where table_schema not in ('information_schema', 'pg_catalog')\r\n" + 
					"and table_name  in ('tb_bano_dtls_a_veh','tms_dynamic_query','tb_bano_dtls_b_veh')\r\n" + 
					"order by schema_name,\r\n" + 
					"         view_name";
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("table_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}
	
	
	public ArrayList<ArrayList<String>> tms_get_categorywise_tabel_List() {
		
		
		 
		 ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";
		String qry1 = "";
		String sql1 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			//String[] arrOfStr = String.valueOf(t11).split(","); 
			StringBuilder select_list = new StringBuilder();

			/*for (int i = 0; i < arrOfStr.length; i++) {							
				if(i == arrOfStr.length-1)
				{
					select_list.append("?");
				}
				else
				{
					select_list.append("?").append(",");
				}	
				
				String[]  t1 = arrOfStr[i].split("-");
			  	//stmt.setString(j, t1[0] );
				
				System.err.println("nmkkkkkkkkkkkkkk11111111111111111-----------" + t1);
				
			}
			
			if(arrOfStr.length > 0){
				qry += " where p1.category in ( "+ select_list +" ) ";
			}*/
			
				//sql1 = "select codevalue,label from tb_olap_t_domain_value "+qry ;
				
				
			/*sql1 ="select concat(p1.table_name,'-', p2.split_part) as codevalue,p1.label from (	\r\n" + 
						"(select codevalue as table_name,label,id,category from t_domain_value where domainid='table_name') p1\r\n" + 
						"inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from t_domain_value where domainid='table_name'\r\n" + 
						") p2 on p1.table_name = p2.table_name) "+ qry +"order by p1.id asc";*/
			
			sql1 = "select concat(p1.table_name,'-', p2.split_part) as codevalue,p1.label from (	\r\n" + 
					"(select codevalue as table_name,label,id from t_domain_value where domainid='table_name') p1\r\n" + 
					"inner join \r\n" + 
					"(select split_part(label::TEXT, '-', 2),codevalue as table_name from t_domain_value where domainid='table_name'\r\n" + 
					") p2 on p1.table_name = p2.table_name)   order by p1.id asc";
				
			int j =1;
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, t11);
			
			//stmt.setString(1, t11);
			 /*if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
					  
					  String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }	*/
			 
		
			
		
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				
				
				ArrayList<String> list2 = new ArrayList<String>();
				
				list2.add(rs1.getString("codevalue"));
				list2.add(rs1.getString("label"));
				list1.add(list2);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
	
		return list1;
	}
	
	
	public List<String> tms_get_selected_field_List(String t2) {
		
		
		
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		String qry = "";
		String qry1 = "";
		String sql1 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			String[] arrOfStr = String.valueOf(t2).split(","); 
			StringBuilder select_list = new StringBuilder();

			for (int i = 0; i < arrOfStr.length; i++) {							
				if(i == arrOfStr.length-1)
				{
					select_list.append("?");
				}
				else
				{
					select_list.append("?").append(",");
				}	
				
				String[]  t1 = arrOfStr[i].split("-");
			  	//stmt.setString(j, t1[0] );
				
				
				
				if(t1[0].equals("tb_olap_no_of_pers_auth_rank_wise") || t1[0].equals("tb_olap_no_of_pers_held_rank_wise")) {
				
					qry1 += "and column_name not in('rank','sus_no')";
				}
			}
			
			if(arrOfStr.length > 0){
				qry += "in ( "+ select_list +" ) ";
			}
			
			
			
			//String sql1 = "SELECT column_name from INFORMATION_SCHEMA.COLUMNS \r\n" + qry;
			
			/*if(t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19,tb_olap_no_of_pers_held_rank_wise-a20") || t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19") || t2.equals("tb_olap_no_of_pers_held_rank_wise-a20")) {
				qry1 += "and column_name not in('rank','sus_no')";
			}*/
			
		/*	if(t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19,tb_olap_no_of_pers_held_rank_wise-a20") || t2.equals("tb_olap_no_of_pers_auth_rank_wise-a19") || t2.equals("tb_olap_no_of_pers_held_rank_wise-a20") ) {
				sql1 = "select concat(p2.split_part,'.', p1.column_name) as column_name from (	\r\n" + 
						"	(SELECT column_name,TABLE_NAME from INFORMATION_SCHEMA.COLUMNS \r\n" + 
						"WHERE table_catalog='miso_psg_olap' and TABLE_NAME "+qry+" "+qry1+" ) p1 inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from tb_olap_t_domain_value where domainid='table_name'\r\n" + 
						"and codevalue "+qry+" ) p2 on p1.table_name = p2.table_name)";
			}
			else {*/
				sql1 = "select concat(p2.split_part,'.', p1.column_name) as column_name from (	\r\n" + 
						"	(SELECT column_name,TABLE_NAME from INFORMATION_SCHEMA.COLUMNS \r\n" + 
						"WHERE table_catalog='miso_psg_v3' and TABLE_NAME "+qry+" ) p1 inner join \r\n" + 
						"(select split_part(label::TEXT, '-', 2),codevalue as table_name from t_domain_value where domainid='table_name'\r\n" + 
						"and codevalue "+qry+" ) p2 on p1.table_name = p2.table_name)";
			//}
			
			
			
			int j =1;
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, t2);
			
			stmt.setString(1, t2);
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
					  
					  String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }	
			 
			 if(arrOfStr.length > 0) {
				  for (int i = 0; i < arrOfStr.length; i++) {	
                   String[]  t1 = arrOfStr[i].split("-");
				  	stmt.setString(j, t1[0] );	
				  	j += 1;
				  }														 
			  }		
			
			 
			
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("column_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		
		return list1;
	}
	
	
	public List<Map<String, Object>> tms_Search_Pers_det(String create_query)
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q =  create_query;
				//q = "SELECT a1.medicine FROM tb_olap_allerg_details a1";
				//q = "SELECT * FROM information_schema.tables where table_schema='public';" 

			
				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
			

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					 ArrayList<String> get_adhoc =  new ArrayList<String>();
					 get_adhoc.add("");
					 get_adhoc.add("");
					columns.put("msg", get_adhoc);
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
	
	
	
	
	
	public ArrayList<ArrayList<String>> tms_getAdhocreport(String create_query) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
  
			 sql  = create_query;
		 stmt = conn.prepareStatement(sql);
		 
			 
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			String count = "1";
			int ct = Integer.parseInt(count);
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				count = ""+ct;
				ct++;
				list.add(count);
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

	

	/*public List<String> Arm_DescList() {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "SELECT arm_code,arm_desc FROM orbat_all_details_view";
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				list1.add(rs1.getString("arm_code"));
				list1.add(rs1.getString("arm_desc"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}*/
	
	
	public List<Map<String, Object>> Arm_DescList()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q =  "SELECT distinct arm_code,arm_desc FROM orbat_all_details_view where arm_code!='' and arm_desc!='' ";
				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					 ArrayList<String> get_adhoc =  new ArrayList<String>();
					 get_adhoc.add("");
					 get_adhoc.add("");
					columns.put("msg", get_adhoc);
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
	
	
	public List<Map<String, Object>> Type_of_ForceList()
	{	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q =  "SELECT distinct type_of_force FROM orbat_all_details_view";
				stmt = conn.prepareStatement(q);
				//stmt.setInt(1, civ_id);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
			

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					 ArrayList<String> get_adhoc =  new ArrayList<String>();
					 get_adhoc.add("");
					 get_adhoc.add("");
					columns.put("msg", get_adhoc);
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
	
	
/*	public List<String> Ad_Hoc_getaddsearchmctnoprfList(String prf_group,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select group_name from prf_master where cast(prf_code as integer)=:prf_code");
		q.setParameter("prf_code", prf_group);
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				String mct_no = String.valueOf(list.get(i));
				encCode = c.doFinal(mct_no.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}*/
	
	
/*	public List<String> Ad_Hoc_getaddsearchmctnoprfList(String prf_group,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		List<String> list1 = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//String sql1 = "SELECT table_name FROM information_schema.tables where table_catalog='miso_psg_olap' and table_schema='public'";
			String sql1 = "select group_name from prf_master where cast(prf_code as integer)=:prf_code";
			stmt = conn.prepareStatement(sql1);
			//stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				
				String enckey ="commonPwdEncKeys";
                Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
                String EncryptedPk = new String(Base64.encodeBase64(c.doFinal(rs1.getString("group_name").toString().getBytes())));
                list1.add(EncryptedPk);
				list1.add(rs1.getString("group_name"));
			}
			rs1.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		System.err.println("helllooo---" + list1);
		return list1;
	}*/
	
	
	public List<String> Ad_Hoc_getaddsearchmctnoprfList(String prf_group,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		List<String> list1 = new ArrayList<String>();
		
		//List<String> FinalList = new ArrayList<String>();
		Connection conn = null;
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "select group_name from prf_master where prf_code =?";
			stmt = conn.prepareStatement(sql1);
		    stmt.setString(1, prf_group);
			ResultSet rs1 = stmt.executeQuery();
			
			while (rs1.next()) {
				list1.add(rs1.getString("group_name"));
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				return list1;
			}
		}
		return list1;
	}
	
	
	
	public List<String> gettmsEncList(List<String> l, HttpSession s1) {
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher ci = null;
		try {
			ci = hex_asciiDao.EncryptionSHA256Algo(s1, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FList = new ArrayList<String>();
		for (int i = 0; i < l.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = ci.doFinal(l.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FList.add(base64EncodedEncryptedCode);
		}
		if (l.size() != 0) {
			FList.add(enckey + "0fsjyg==");
		}
		return FList;
	}
}
