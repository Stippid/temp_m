package com.dao.RBAC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.UserLogin;

public class UserMSTRDAOImpl implements UserMSTRDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//Get User List for Export CSV
	public List<UserLogin> getFMN_WISE_USER_ID_LIST(String formCode) {
	
		String q = null;
		List<UserLogin> alist = new ArrayList<UserLogin>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select 	l.userid,u.sus_no,\r\n" + 
					"  		u.unit_name,\r\n" + 
					"		l.username,\r\n" + 
					"		l.army_no,r.role\r\n" + 
					"from\r\n" + 
					"  tb_miso_orbat_unt_dtl u \r\n" + 
					"  left join logininformation l on u.sus_no = l.user_sus_no\r\n" + 
					"  left join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"  left join roleinformation r  on  r.role_id =  ur.role_id\r\n" + 
					"  where u.status_sus_no::text = 'Active'::text and u.form_code_control like ? \r\n" + 
					" order by l.username,u.sus_no,l.army_no ";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, formCode+"%");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				UserLogin u = new UserLogin();
				for (int i = 1; i <= columnCount; i++) {
					u.setUser_sus_no(rs.getString("sus_no"));
					u.setUnit_name(rs.getString("unit_name"));
					u.setUserName(rs.getString("username"));
					u.setArmy_no(rs.getString("army_no"));
					u.setRole(rs.getString("role"));
				}
				alist.add(u);
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
		return alist;
	}
	//Get User List for Export CSV
	
	public DataSet<Map<String, Object>> DatatablesCriteriasUserReportFormationWise(DatatablesCriterias criterias, String cont_comd1,String cont_corps1, String cont_div1, String cont_bde1) {
		List<Map<String, Object>> metadata = findDepartmentCriteriasformation(criterias,cont_comd1,cont_corps1,cont_div1,cont_bde1);
		Long countFiltered = getFilteredCountfo(criterias,cont_comd1,cont_corps1,cont_div1,cont_bde1);
		Long count = getTotalCountfo(cont_comd1, cont_corps1,cont_div1,cont_bde1);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findDepartmentCriteriasformation(DatatablesCriterias criterias, String cont_comd1,String cont_corps1, String cont_div1, String cont_bde1) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		
		String qry = "";
		
		
    	 if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
    		qry +=" and u.form_code_control = ? ";
    	}else {
    		if(!cont_div1.equals("0") && !cont_div1.equals("")){
	    		qry +=" and u.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
		    		qry +=" and u.form_code_control like ?";
		    	}else {
		    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
		    			qry +=" and u.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select 	l.userid,u.sus_no,\r\n" + 
					"  		u.unit_name,\r\n" + 
					"		l.username,\r\n" + 
					"		l.army_no,r.role\r\n" + 
					"from\r\n" + 
					"  tb_miso_orbat_unt_dtl u \r\n" + 
					"  left join logininformation l on u.sus_no = l.user_sus_no\r\n" + 
					"  left join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"  left join roleinformation r  on  r.role_id =  ur.role_id\r\n" + 
					"  where u.status_sus_no::text = 'Active'::text \r\n" + qry + 
					" order by (case when l.userid > 0 then u.unit_name end)  " + 							/*l.username,u.sus_no,l.army_no*/
					" limit " + criterias.getDisplaySize() + " OFFSET "	+ criterias.getDisplayStart();
			
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
			
			int count1=1;
    		/*if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	}*/ 
        	if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
        		stmt.setString(count1++, cont_bde1);
        	}else {
        		if(!cont_div1.equals("0") && !cont_div1.equals("")){
    	    		stmt.setString(count1++, cont_div1+"%");
    	    	}else {
    	    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
    	    			stmt.setString(count1++, cont_corps1+"%");
    		    	}else {
    		    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
    		    			stmt.setString(count1++, cont_comd1+"%");
    			    	}
    		    	}
    		    }
    	    }
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
				
				//String f = "";
				/*if(rs.getObject(1) != null) {
					String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+ rs.getObject(1) + ")}else{ return false;}\"";
					String updateButton = "<i   class='action_icons action_update' " + Update + " title='go to Edit Data'></i>";
					f = updateButton;
				}else {
					//<i class="fa fa-user-circle-o" aria-hidden="true"></i>
					String updateButton = "<a href='user_mstUrl' title='go to Create New User'><i class='action_icons action_create_user' ></i><a>";
					f = updateButton;
				}
				columns.put("Action", f);*/
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
	/*private Long getFilteredCountfo(DatatablesCriterias criterias, String formCode) {
		String q = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select 	l.userid,u.sus_no,\r\n" + 
					"  		u.unit_name,\r\n" + 
					"		l.username,\r\n" + 
					"		l.army_no,r.role\r\n" + 
					"from\r\n" + 
					"  tb_miso_orbat_unt_dtl u \r\n" + 
					"  left join logininformation l on u.sus_no = l.user_sus_no\r\n" + 
					"  left join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"  left join roleinformation r  on  r.role_id =  ur.role_id\r\n" + 
					"  where u.status_sus_no::text = 'Active'::text and u.form_code_control like ? \r\n" + 
					" order by l.username,u.sus_no,l.army_no ";
					
			q += getFilterQueryfo(criterias, q);
		
			stmt = conn.prepareStatement(q);
			stmt.setString(1, formCode+"%");
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
	}*/
	
	private Long getFilteredCountfo(DatatablesCriterias criterias, String cont_comd1,String cont_corps1, String cont_div1, String cont_bde1) {
		int columnCount = 0;
		String q = null;
		Connection conn = null;
		
		String qry = "";
		
		
		if (!cont_bde1.equals("0") && !cont_bde1.equals("")) {
			qry += " and u.form_code_control = ? ";
		} else {
			if (!cont_div1.equals("0") && !cont_div1.equals("")) {
				qry += " and u.form_code_control like ? ";
			} else {
				if (!cont_corps1.equals("0") && !cont_corps1.equals("")) {
					qry += " and u.form_code_control like ?";
				} else {
					if (!cont_comd1.equals("-1") && !cont_comd1.equals("")) {
						qry += " and u.form_code_control like ? ";
					}
				}
			}
		}
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select 	COUNT(*) as total "
					+ " from\r\n" + 
					"  tb_miso_orbat_unt_dtl u \r\n" + 
					"  left join logininformation l on u.sus_no = l.user_sus_no\r\n" + 
					"  left join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"  left join roleinformation r  on  r.role_id =  ur.role_id\r\n" + 
					"  where u.status_sus_no::text = 'Active'::text " + qry; 
		
			stmt = conn.prepareStatement(q);
			
			int count1=1;
    		/*if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	}*/ 
        	if(!cont_bde1.equals("0") && !cont_bde1.equals("")){
        		stmt.setString(count1++, cont_bde1);
        	}else {
        		if(!cont_div1.equals("0") && !cont_div1.equals("")){
    	    		stmt.setString(count1++, cont_div1+"%");
    	    	}else {
    	    		if(!cont_corps1.equals("0") && !cont_corps1.equals("")){
    	    			stmt.setString(count1++, cont_corps1+"%");
    		    	}else {
    		    		if(!cont_comd1.equals("-1") && !cont_comd1.equals("")){
    		    			stmt.setString(count1++, cont_comd1+"%");
    			    	}
    		    	}
    		    }
    	    }
			
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

	private Long getTotalCountfo(String cont_comd1,String cont_corps1, String cont_div1, String cont_bde1) {
		int columnCount = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select 	COUNT(*) as total "
					+ " from\r\n" + 
					"  tb_miso_orbat_unt_dtl u \r\n" + 
					"  left join logininformation l on u.sus_no = l.user_sus_no\r\n" + 
					"  left join userroleinformation ur on l.userid = ur.user_id\r\n" + 
					"  left join roleinformation r  on  r.role_id =  ur.role_id\r\n" + 
					"  where u.status_sus_no::text = 'Active'::text "; 
			
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
}