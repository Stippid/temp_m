package com.dao.helpDesk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

public class DashboardAccessLogDAOImpl implements DashboardAccessLogDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	
	public DataSet<Map<String, Object>> findDashboardAccessLogListWithDatatablesCriterias(DatatablesCriterias criterias) {
		
		String whr = "";
		String filter = criterias.getSearch().toString().toLowerCase();
		if(!filter.equals("")) {
			whr = " and ( lower(b.username) like ? or lower(c.unit_name) like ? or lower(b.army_no) like ? or lower(a.dashboard_role) like ? ) ";
		}
		
		
		LocalDate now = LocalDate.now(); // current date
		LocalDate earlier = now.minusMonths(1); // one month previous data
		
		String mainQry = " select b.username,c.unit_name,b.army_no,max(a.created_on) as created_on,a.dashboard_role\r\n" + 
				"	from tb_miso_ocsp_verify_users a\r\n" + 
				"	inner join logininformation b on a.userid = b.userid\r\n" + 
				"	left join all_fmn_view c on b.user_formation_no=c.form_code_control\r\n" + 
				"	where a.created_on > '"+earlier.toString()+"'  \r\n" + whr +
				"	group by a.userid,b.username,b.army_no,a.dashboard_role,c.unit_name " ;
		String TotalCountQry ="select count(distinct(a.userid)) from tb_miso_ocsp_verify_users a\r\n" + 
				"	inner join logininformation b on a.userid = b.userid\r\n" + 
				//"	left join all_fmn_view c on b.user_formation_no=c.form_code_control\r\n" + 
				" where a.created_on > '"+earlier.toString()+"'" ;
				//"	group by a.userid,b.username,b.army_no,a.dashboard_role,c.unit_name " ;
		String FilteredCountQry =  "select count(distinct(a.userid)) from tb_miso_ocsp_verify_users a\r\n" + 
				"inner join logininformation b on a.userid = b.userid\r\n" +
				//"	left join all_fmn_view c on b.user_formation_no=c.form_code_control\r\n" +
				" where a.created_on > '"+earlier.toString()+"' ";
				//"	group by a.userid,b.username,b.army_no,a.dashboard_role,c.unit_name " ;
	
		List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias(criterias,mainQry,filter);
		Long count = getTotalCountActiveDetails(TotalCountQry,filter);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry,filter);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias(DatatablesCriterias criterias , String qry,String filter) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
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
		queryBuilder.append(" limit "+criterias.getDisplaySize()+" OFFSET "+ criterias.getDisplayStart()+"");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		if(!filter.equals("")) {
    			for(int i=1;i<=4;i++) {
    				stmt.setString(i, filter+"%");
    			}
    		}
    		ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("username", rs1.getString("username"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("army_no", rs1.getString("army_no"));
    			columns.put("created_on", rs1.getString("created_on"));
    			columns.put("dashboard_role", rs1.getString("dashboard_role"));
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	private Long getTotalCountActiveDetails(String qry,String filter) {
		Connection conn = null;
		Long count = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(qry);
    		ResultSet rs = stmt.executeQuery();   
			if (rs.next()) {
				count = rs.getLong(1);
	    	}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return count;
	}
	private Long getFilteredCountActiveDetails(DatatablesCriterias criterias ,String qry,String filter) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long FilteredCount = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		ResultSet rs = stmt.executeQuery();     
			if (rs.next()) {
				FilteredCount = rs.getLong(1);
	    	}
			rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return FilteredCount;
	}
}
