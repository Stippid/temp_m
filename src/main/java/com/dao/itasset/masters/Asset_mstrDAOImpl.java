package com.dao.itasset.masters;
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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtilNA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.http.HttpSession;


public class Asset_mstrDAOImpl implements Asset_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListAsset_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType, String assets_name,String category,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL( Search, category,assets_name);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,assets_name,\n"
					+"CASE\n"
					+ " WHEN category=1 THEN 'COMPUTING'\n"
					+ " WHEN category=2 THEN 'PERIPHERAL'\n"
					+ " ELSE 'Other'\n"
					+ " END AS category\n"
					+ " from tb_mstr_assets "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,  Search, category,assets_name);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
                      String enckey ="commonPwdEncKeys";
	                    Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);
	                    String EncryptedPk = new String(Base64.encodeBase64( c.doFinal(rs.getString("id").toString().getBytes())));
                      String Delete = "onclick=\"  if (confirm('Are you sure you want to Delete?') ){deleteData('"+EncryptedPk+"')}else{ return false;}\""; 
                      String deleteButton = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>"; 
                      String Update = "onclick=\"  if (confirm('Are you sure you want to Update?') ){editData('"+EncryptedPk+"')}else{ return false;}\""; 
                      String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>"; 
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

public long getReportListAsset_mstrTotalCount(String Search, String assets_name,String category) {
 		String SearchValue = GenerateQueryWhereClause_SQL( Search, category,assets_name);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) from tb_mstr_assets  "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,  Search, category,assets_name);
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


  	
   public String Deleteasset_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtilNA.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MSTR_ASSETS  where cast(id as string) = :deleteid";
          Query q = session.createQuery(hql).setString("deleteid",DcryptedPk);
      	int rowCount = q.executeUpdate();
      	tx.commit();
          session.close();
	    if(rowCount > 0) {
 			return "Deleted Successfully";
  		}else {
  		return "Deleted not Successfully";
   	}
  	}
   public String GenerateQueryWhereClause_SQL(String Search,String category, String assets_name) {
		String SearchValue ="";
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" CASE\n" + 
					"WHEN category=1 THEN 'COMPUTING' " + 
					"WHEN category=2 THEN 'PERIPHERAL' " + 
					"ELSE 'Other'\n" + 
					"END like ? or upper(assets_name) like ? )";
		}
		
	
		if (!category.equals("0")) {
			category = category.toUpperCase();
		if (SearchValue.contains("where")) {
			SearchValue += " and cast(category as character varying) like ? ) ";
			
		} else {
				SearchValue += " where cast(category as character varying) like ?  ";
			}
		}
	

	
		if (!assets_name.equals("")) {
			assets_name = assets_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(assets_name ) like ? ";
			} else {
				SearchValue += " where upper(assets_name ) like ? ";
			}
		}
		
		return SearchValue;
	}
		
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String category, String assets_name) {
		int flag = 0;
		try {
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
			if(!category.equals("0")) {
			flag += 1;
			stmt.setString(flag, category);
		}
			
		if(!assets_name.equals("")) {
				flag += 1;
				stmt.setString(flag, assets_name.toUpperCase()+"%");
		}
		}catch (Exception e) {}
		return stmt;
	}
	
   /* report excel */
   public ArrayList<ArrayList<String>> Report_DataTableMakeList(String assets_name,String category){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(assets_name,category);
	
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			 
			        q="select distinct id,assets_name, CASE WHEN category=1 THEN 'COMPUTING'  WHEN category=2 THEN 'PERIPHERAL'  ELSE 'Other' END AS category from tb_mstr_assets"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,assets_name,category);
		
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("category"));
				alist.add(rs.getString("assets_name"));
				
				list.add(alist);
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
   
   public String GenerateQueryWhereClause_SQL1( String assets_name,String category) {
		String SearchValue ="";
 		
			//SearchValue +=" UPPER(assets_name) like ? OR (CASE WHEN category=1 THEN 'COMPUTING' WHEN category=2 THEN 'PERIPHERAL' ELSE 'Other' END) LIKE ?  )";
		
 		
 		if (!assets_name.equals("")) {
 			assets_name = assets_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and UPPER(assets_name) like ? ";
			} else {
				SearchValue += " where UPPER(assets_name) like ? ";
			}
		}
		if (!category.equals("0")) {
			category = category.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(category as character varying) like ? ";
			} else {
				SearchValue += " where cast(category as character varying) like ? ";
			}
		}
  return SearchValue;
}


 public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String assets_name,String category) {
		int flag = 0;
		try {
   		
				if (!assets_name.equals("")) {
					flag += 1;
					stmt.setString(flag, assets_name.toUpperCase() + "%");
				}
				if (!category.equals("0")) {
					flag += 1;
					stmt.setString(flag, category + "%");
				}
			
		}catch (Exception e) {}
		return stmt;
	}

}
