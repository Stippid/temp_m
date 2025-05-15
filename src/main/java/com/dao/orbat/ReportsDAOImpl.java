package com.dao.orbat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

public class ReportsDAOImpl implements ReportsDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Map<String, Object>> getsus_noorbat(int id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "    SELECT  ul.sus_no as unit,ul.unit_name,c.sus_no as cmd,cc.sus_no as corps ,cd.sus_no as div,cb.sus_no as bde from tb_miso_orbat_unt_dtl as  ul \r\n" + 
					"      LEFT JOIN orbat_view_cmd c ON substr(ul.form_code_control::text, 1, 1) = c.cmd_code::text\r\n" + 
					"     LEFT JOIN orbat_view_corps cc ON substr(ul.form_code_control::text, 2, 2) = cc.corps_code::text\r\n" + 
					"     LEFT JOIN orbat_view_div cd ON substr(ul.form_code_control::text, 4, 3) = cd.div_code::text\r\n" + 
					"     LEFT JOIN orbat_view_bde cb ON substr(ul.form_code_control::text, 7, 4) = cb.bde_code::text\r\n" + 
					"	 where  ul.id = ? " ;


			stmt = conn.prepareStatement(q);

			stmt.setInt(1,id);

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
	
	public List<Map<String, Object>> getformationorbat(String sus_no) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "    SELECT distinct ul.sus_no as unit,ul.unit_name,c.sus_no as cmd,cc.sus_no as corps ,cd.sus_no as div,cb.sus_no as bde from tb_miso_orbat_unt_dtl as  ul \r\n" + 
					"      LEFT JOIN orbat_view_cmd c ON substr(ul.form_code_control::text, 1, 1) = c.cmd_code::text\r\n" + 
					"     LEFT JOIN orbat_view_corps cc ON substr(ul.form_code_control::text, 2, 2) = cc.corps_code::text\r\n" + 
					"     LEFT JOIN orbat_view_div cd ON substr(ul.form_code_control::text, 4, 3) = cd.div_code::text\r\n" + 
					"     LEFT JOIN orbat_view_bde cb ON substr(ul.form_code_control::text, 7, 4) = cb.bde_code::text\r\n" + 
					"	 where  ul.sus_no = ? " ;


			stmt = conn.prepareStatement(q);

			stmt.setString(1,sus_no);

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
	
	
	
	
	public List<Map<String, Object>> gettyp_ltr(int id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "  select type_of_letter from tb_miso_orbat_shdul_detl  " + 
					"	 where  letter_id = ? " ;


			stmt = conn.prepareStatement(q);

			stmt.setInt(1,id);

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
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias(DatatablesCriterias criterias ,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n" + 
				"    where a.status_sus_no='Active'";
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"    where a.status_sus_no='Active'";
		String FilteredCountQry =  "SELECT \r\n" + 
				"     count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"    where a.status_sus_no='Active'";
		
		
		String qry = "";
		
		if(!sus_no121.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name121.equals("0") && !unit_name121.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
		    		qry +=" and a.form_code_control like ?";
		    	}else {
		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
    		qry +=" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!location121.equals("") & !location121.equals("0")){
			qry +=" and a.code = ? ";
		}
		
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias(criterias,mainQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias(DatatablesCriterias criterias , String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
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
    		
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	private Long getTotalCountActiveDetails(String qry) {
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
	private Long getFilteredCountActiveDetails(DatatablesCriterias criterias ,String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121){
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long FilteredCount = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
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
	////////////////////////////////////////////////////////////////

	public DataSet<Map<String, Object>> findFormationwiseReportWithDatatablesCriterias(DatatablesCriterias criterias , String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12) {
		String mainQry = "SELECT a.id,\r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs,\r\n" + 
				"    b.approved_rejected_on,\r\n" + 
				"    a.status_sus_no,\r\n" + 
				"    x.label AS type_of_force,\r\n" + 
				"    a.ct_part_i_ii,\r\n" + 
				"    a.sus_version,\r\n" + 
				"    a.comm_depart_date,\r\n" + 
				"    a.compltn_arrv_date,\r\n" + 
				"    a.creation_on,\r\n" + 
				"    c.label AS action,\r\n" + 
				"    a.arm_code,\r\n" + 
				"    d.arm_desc,\r\n" + 
				"    a.form_code_control\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue\r\n" + 
				"     LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = a.type_force::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text";
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue ";
		String FilteredCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue ";
		
		
		//Date to_date1 = Calendar.getInstance().getTime();  			 
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
       // String to_date = dateFormat.format(to_date1);
        
       // Date currentDate = null;
       // Date from = null;
      //  Date to = null;
		
		
		String qry="";
		if(status_sus_no12.equals("All")){
    		qry +=" where a.status_sus_no in ('Pending','Active','Inactive','INVALID') ";
    	}else {
    		qry +=" where a.status_sus_no = ? ";
    	}
    	if(!sus_no12.equals("0") && !sus_no12.equals("")){
    		qry +=" and upper(a.sus_no) like ? ";
    	}
    	if(!unit_name12.equals("0") && !unit_name12.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div12.equals("0") && !cont_div12.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		    		qry +=" and a.form_code_control like ? ";
		    	}else {
		    		if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
    	if(!action12.equals("")){
    		qry +=" and c.label = ? ";
    	}
    	if(!approved_rejected_on12.equals("") && approved_rejected_on22.equals("")){
    		qry +=" and b.approved_rejected_on between cast( ? as Date) and cast( ? as Date) ";
    	}
    	if(!approved_rejected_on12.equals("") && !approved_rejected_on22.equals("")){
    		qry +=" and b.approved_rejected_on between cast( ? as Date)  and cast( ? as Date) ";
    	}
		
		List<Map<String, Object>> metadata = findAllDetailsCriteriasformation(criterias,mainQry+qry, status_sus_no12,sus_no12,unit_name12,cont_bde12,cont_div12,cont_corps12,action12,approved_rejected_on12,approved_rejected_on22,cont_comd12);
		Long count = getTotalCountFormation(TotalCountQry);
		Long countFiltered = getFilteredCountFormation(criterias,FilteredCountQry+qry,status_sus_no12,sus_no12,unit_name12,cont_bde12,cont_div12,cont_corps12,action12,approved_rejected_on12,approved_rejected_on22,cont_comd12);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findAllDetailsCriteriasformation(DatatablesCriterias criterias , String qry, String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12) {
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
    		
    		int count=1;
    		if(!status_sus_no12.equals("All")){
    			stmt.setString(count++, status_sus_no12);
    		}
    		if(!sus_no12.equals("0") && !sus_no12.equals("")){
    			stmt.setString(count++, sus_no12.toUpperCase()+"%");
        	}
        	if(!unit_name12.equals("0") && !unit_name12.equals("")){
        		stmt.setString(count++, "%"+unit_name12.toUpperCase()+"%");
        	} 
        	if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
        		stmt.setString(count++, cont_bde12);
        	}else {
        		if(!cont_div12.equals("0") && !cont_div12.equals("")){
        			stmt.setString(count++, cont_div12+"%");
        		}else {
    	    		if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
    	    			stmt.setString(count++, cont_corps12+"%");
    	    		}else {
    		    		if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
    		    			stmt.setString(count++, cont_comd12+"%");
    		    		}
    		    	}
    		    }
    	    }
        	if(!action12.equals("")){
        		stmt.setString(count++, action12);
        	}
        	if(!approved_rejected_on12.equals("") && approved_rejected_on22.equals("")){
        		Date date = new Date();
        		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		String currentDate = df.format(date);
        		
        		stmt.setString(count++, approved_rejected_on12);
        		stmt.setString(count++,currentDate);
        	}
        	if(!approved_rejected_on12.equals("") && !approved_rejected_on22.equals("")){
        		stmt.setString(count++,approved_rejected_on12);
        		stmt.setString(count++, approved_rejected_on22);
        	}
    		
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("approved_rejected_on", rs1.getString("approved_rejected_on"));
    			columns.put("creation_on", rs1.getString("creation_on"));
    			columns.put("status_sus_no", rs1.getString("status_sus_no"));
    			columns.put("type_of_force", rs1.getString("type_of_force"));
    			columns.put("ct_part_i_ii", rs1.getString("ct_part_i_ii"));
    			columns.put("sus_version", rs1.getString("sus_version"));
    			columns.put("comm_depart_date", rs1.getString("comm_depart_date"));
    			columns.put("action", rs1.getString("action"));
    			columns.put("arm_code", rs1.getString("arm_code"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	private Long getTotalCountFormation(String qry) {
		Long count = (long) 0;
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	    	conn = dataSource.getConnection();
	    	pstmt = conn.prepareStatement(qry);
	    	rs = pstmt.executeQuery();
	    	if (rs.next()) {
	    		count = rs.getLong(1);
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		rs.close();
	    		pstmt.close();
	    		conn.close();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	    return count;
	}
	private Long getFilteredCountFormation(DatatablesCriterias criterias ,String qry, String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long totalFiltered = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		int count=1;
    		if(!status_sus_no12.equals("All")){
    			stmt.setString(count++, status_sus_no12);
    		}
    		if(!sus_no12.equals("0") && !sus_no12.equals("")){
    			stmt.setString(count++, sus_no12.toUpperCase()+"%");
        	}
        	if(!unit_name12.equals("0") && !unit_name12.equals("")){
        		stmt.setString(count++, "%"+unit_name12.toUpperCase()+"%");
        	} 
        	if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
        		stmt.setString(count++, cont_bde12);
        	}else {
        		if(!cont_div12.equals("0") && !cont_div12.equals("")){
        			stmt.setString(count++, cont_div12+"%");
        		}else {
    	    		if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
    	    			stmt.setString(count++, cont_corps12+"%");
    	    		}else {
    		    		if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
    		    			stmt.setString(count++, cont_comd12+"%");
    		    		}
    		    	}
    		    }
    	    }
        	if(!action12.equals("")){
        		stmt.setString(count++, action12);
        	}
        	if(!approved_rejected_on12.equals("") && approved_rejected_on22.equals("")){
        		Date date = new Date();
        		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		String currentDate = df.format(date);
        		
        		stmt.setString(count++, approved_rejected_on12);
        		stmt.setString(count++,currentDate);
        	}
        	if(!approved_rejected_on12.equals("") && !approved_rejected_on22.equals("")){
        		stmt.setString(count++,approved_rejected_on12);
        		stmt.setString(count++, approved_rejected_on22);
        	}
    		
			ResultSet rs = stmt.executeQuery();   
			if (rs.next()) {
				totalFiltered = rs.getLong(1);
	    	}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return totalFiltered;
	}
	
	//Get Formation
	public List<Map<String, Object>> getAllFormationList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from all_fmn_view order by form_code_control";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs1 = stmt.executeQuery();
			ResultSetMetaData metaData = rs1.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs1.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs1.getObject(i));
				}
				list.add(columns);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> getKLP_MATRIXList(String formation,String station) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT o.unit_name,concat(o.cmd_name,'/',o.coprs_name,'/',o.div_name,'/',o.bde_name) as imdt,COALESCE(w.wepe_pers_no,'') as wepe_pers_no,\r\n" + 
					"COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='0'), 0::numeric) as officer,\r\n" + 
					"	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='1'), 0::numeric) as jco,\r\n" + 
					" 	COALESCE(sum(ue.base_auth+ue.mod_auth+ue.foot_auth) FILTER (where ue.rank_cat='2' or ue.rank_cat='3'), 0::numeric) as or\r\n" + 
					"FROM public.orbat_all_details_view o\r\n" + 
					"left join cue_tb_wepe_link_sus_perstransweapon w on o.sus_no=w.sus_no\r\n" + 
					"left join sus_pers_stdauth ue on ue.sus_no=o.sus_no\r\n" + 
					"where o.status_sus_no='Active' and o.form_code_control like ? \r\n" + 
					"group by 1,2,3,o.form_code_control\r\n" + 
					"order by o.form_code_control ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, formation+"%");
			ResultSet rs1 = stmt.executeQuery();
			ResultSetMetaData metaData = rs1.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs1.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs1.getObject(i));
				}
				list.add(columns);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public String getlinedte_susno(String sus_no) {
	    Connection conn = null;
	    String q = "";
	    String result = null; // Variable to store the result
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement stmt = null;

	        q = "SELECT rl.line_dte_sus AS sus_no " +
	                "FROM tb_miso_orbat_unt_dtl AS ou " +
	                "INNER JOIN tb_miso_orbat_line_dte AS rl ON ou.arm_code = rl.arm_code " +
	                "WHERE ou.sus_no = ? AND ou.status_sus_no = 'Active'";

	        stmt = conn.prepareStatement(q);
	        stmt.setString(1, sus_no);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            result = rs.getString("sus_no");
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return result; 
	}
	
	//28.10.2024
	

	/*
	 * cii formation report
	 */
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_cii(DatatablesCriterias criterias,String sus_no1211,String unit_name1211,String level1_1211,String level2_1211,String level3_1211,String level4_1211,String level5_1211,String level6_1211,String level7_1211,String level8_1211,String level9_1211,String levell0_1211,String line_dte_sus1211,String location1211) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.level1 AS level1,\r\n" + 
				"    ab.level2 AS level2,\r\n" + 
				"    ab.level3 AS level3,\r\n" + 
				"    ab.level4 AS level4,\r\n" +
				"    ab.level5 AS level5,\r\n" +
				"    ab.level6 AS level6,\r\n" +
				"    ab.level7 AS level7,\r\n" +
				"    ab.level8 AS level8,\r\n" +
				"    ab.level9 AS level9,\r\n" +
				"    ab.level10 AS level10,\r\n" +
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs\r\n" + 
				"   FROM tb_miso_orbat_cii_unt_dtl a\r\n" + 
			//	"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.loc_code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_mast_fmn ab ON a.fmn_code::text = ab.fmn_code::text  AND ab.status_record='1'"
				+ "	  INNER JOIN tb_miso_orbat_unt_dtl ac ON a.sus_no::text = ac.sus_no::text AND a.status_sus_no='Active' AND ac.status_sus_no='Active'	 \r\n" + 
			//	"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
			//	"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
			//	"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n" + 
				"    where a.status_sus_no='Active'";
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_cii_unt_dtl a\r\n" + 
				"     INNER JOIN tb_miso_orbat_unt_dtl b ON a.sus_no = b.sus_no and b.status_sus_no='Active'\r\n" + 
				"    where a.status_sus_no='Active'";
		String FilteredCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_cii_unt_dtl a\r\n" + 
				"     INNER JOIN tb_miso_orbat_unt_dtl b ON a.sus_no = b.sus_no and b.status_sus_no='Active'\r\n" + 
				"     LEFT JOIN tb_miso_orbat_mast_fmn ab ON a.fmn_code::text = ab.fmn_code::text  AND ab.status_record='1'" +
				"    where a.status_sus_no='Active'";
		
		
		String qry = "";
		
		if(!sus_no1211.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name1211.equals("0") && !unit_name1211.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	}
    	
    	if(!level1_1211.equals("0") && !level1_1211.equals("")){
			qry +=" and upper(ab.level1) like ? ";
    	}
    	
    	if(!level2_1211.equals("0") && !level2_1211.equals("")){
			qry +=" and upper(ab.level2) like ? ";
    	}
    	
    	if(!level3_1211.equals("0") && !level3_1211.equals("")){
			qry +=" and upper(ab.level3) like ? ";
    	}
    	
    	if(!level4_1211.equals("0") && !level4_1211.equals("")){
			qry +=" and upper(ab.level4) like ? ";
    	}
    	
    	if(!level5_1211.equals("0") && !level5_1211.equals("")){
			qry +=" and upper(ab.level5) like ? ";
    	}
    	
    	if(!level6_1211.equals("0") && !level6_1211.equals("")){
			qry +=" and upper(ab.level6) like ? ";
    	}
    	
    	
    	if(!level7_1211.equals("0") && !level7_1211.equals("")){
			qry +=" and upper(ab.level7) like ? ";
    	}
    	
    	if(!level8_1211.equals("0") && !level8_1211.equals("")){
			qry +=" and upper(ab.level8) like ? ";
    	}
    	
    	
    	if(!level9_1211.equals("0") && !level9_1211.equals("")){
			qry +=" and upper(ab.level9) like ? ";
    	}
    	
    	if(!levell0_1211.equals("0") && !levell0_1211.equals("")){
			qry +=" and upper(ab.level10) like ? ";
    	}
    	
    	
		/*
		 * if(!cont_bde1211.equals("0") && !cont_bde1211.equals("")){ qry
		 * +=" and a.form_code_control = ? "; }else { if(!cont_div1211.equals("0") &&
		 * !cont_div1211.equals("")){ qry +=" and a.form_code_control like ? "; }else {
		 * if(!cont_corps1211.equals("0") && !cont_corps1211.equals("")){ qry
		 * +=" and a.form_code_control like ?"; }else { if(!cont_comd1211.equals("-1")
		 * && !cont_comd1211.equals("")){ qry +=" and a.form_code_control like ? "; } }
		 * } }
		 */
		if(!line_dte_sus1211.equals("") & !line_dte_sus1211.equals("0")){
    		qry +=" and a.arm_code  = ? ";
    	}
		
		if(!location1211.equals("") & !location1211.equals("0")){
			qry +=" and a.loc_code = ? ";
		}
		System.err.println("qry value " + qry);
		
		System.err.println("qry main value " + mainQry);
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias_cii(criterias,mainQry+qry,sus_no1211,unit_name1211,level1_1211,level2_1211,level3_1211,level4_1211,level5_1211,level6_1211,level7_1211,level8_1211,level9_1211,levell0_1211,line_dte_sus1211,location1211);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		System.out.println("count of the  " + count);
		Long countFiltered = getFilteredCountActiveDetails_cii(criterias,FilteredCountQry+qry,sus_no1211,unit_name1211,level1_1211,level2_1211,level3_1211,level4_1211,level5_1211,level6_1211,level7_1211,level8_1211,level9_1211,levell0_1211,line_dte_sus1211,location1211);
		System.out.println("filetered count of the  " + countFiltered);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias_cii(DatatablesCriterias criterias , String qry,String sus_no1211,String unit_name1211,String level1_1211,String level2_1211,String level3_1211,String level4_1211,String level5_1211,String level6_1211,String level7_1211,String level8_1211,String level9_1211,String levell0_1211,String line_dte_sus1211,String location1211) {
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
    		
    		int count=1;
    		if( !sus_no1211.equals("")){
        		stmt.setString(count++, sus_no1211.toUpperCase()+"%");
        	}
        	if(!unit_name1211.equals("0") && !unit_name1211.equals("")){
    			stmt.setString(count++, "%"+unit_name1211.toUpperCase()+"%");
        	}
        	
        	if(!level1_1211.equals("0") && !level1_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!level2_1211.equals("0") && !level2_1211.equals("")){
    			stmt.setString(count++, "%"+level2_1211.toUpperCase()+"%");
        	}
        	
        	if(!level3_1211.equals("0") && !level3_1211.equals("")){
    			stmt.setString(count++, "%"+level3_1211.toUpperCase()+"%");
        	}
        	
        	if(!level4_1211.equals("0") && !level4_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!level5_1211.equals("0") && !level5_1211.equals("")){
    			stmt.setString(count++, "%"+level5_1211.toUpperCase()+"%");
        	}
        	
        	if(!level6_1211.equals("0") && !level6_1211.equals("")){
    			stmt.setString(count++, "%"+level6_1211.toUpperCase()+"%");
        	}
        	
        	
        	if(!level7_1211.equals("0") && !level7_1211.equals("")){
    			stmt.setString(count++, "%"+level7_1211.toUpperCase()+"%");
        	}
        	
        	
        	if(!level8_1211.equals("0") && !level8_1211.equals("")){
    			stmt.setString(count++, "%"+level8_1211.toUpperCase()+"%");
        	}
        	
        	if(!level9_1211.equals("0") && !level9_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!levell0_1211.equals("0") && !levell0_1211.equals("")){
    			stmt.setString(count++, "%"+levell0_1211.toUpperCase()+"%");
        	}
        	
        	
        	
        	if(!line_dte_sus1211.equals("") & !line_dte_sus1211.equals("0")){
        		stmt.setString(count++, line_dte_sus1211);
        	}
        	if(!location1211.equals("") & !location1211.equals("0")){
        		stmt.setString(count++, location1211);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		System.err.println("query findAllActiveOrbatDetailsCriterias_cii " + stmt);
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("level1", rs1.getString("level1"));
    			columns.put("level2", rs1.getString("level2"));
    			columns.put("level3", rs1.getString("level3"));
    			columns.put("level4", rs1.getString("level4"));
    			columns.put("level5", rs1.getString("level5"));
    			columns.put("level6", rs1.getString("level6"));
    			columns.put("level7", rs1.getString("level7"));
    			columns.put("level8", rs1.getString("level8"));
    			columns.put("level9", rs1.getString("level9"));
    			columns.put("level10", rs1.getString("level10"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	
	private Long getFilteredCountActiveDetails_cii(DatatablesCriterias criterias , String qry,String sus_no1211,String unit_name1211,String level1_1211,String level2_1211,String level3_1211,String level4_1211,String level5_1211,String level6_1211,String level7_1211,String level8_1211,String level9_1211,String levell0_1211,String line_dte_sus1211,String location1211){
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long FilteredCount = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		int count=1;
    		if( !sus_no1211.equals("")){
        		stmt.setString(count++, sus_no1211.toUpperCase()+"%");
        	}
        	if(!unit_name1211.equals("0") && !unit_name1211.equals("")){
    			stmt.setString(count++, "%"+unit_name1211.toUpperCase()+"%");
        	}
        	
        	if(!level1_1211.equals("0") && !level1_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!level2_1211.equals("0") && !level2_1211.equals("")){
    			stmt.setString(count++, "%"+level2_1211.toUpperCase()+"%");
        	}
        	
        	if(!level3_1211.equals("0") && !level3_1211.equals("")){
    			stmt.setString(count++, "%"+level3_1211.toUpperCase()+"%");
        	}
        	
        	if(!level4_1211.equals("0") && !level4_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!level5_1211.equals("0") && !level5_1211.equals("")){
    			stmt.setString(count++, "%"+level5_1211.toUpperCase()+"%");
        	}
        	
        	if(!level6_1211.equals("0") && !level6_1211.equals("")){
    			stmt.setString(count++, "%"+level6_1211.toUpperCase()+"%");
        	}
        	
        	
        	if(!level7_1211.equals("0") && !level7_1211.equals("")){
    			stmt.setString(count++, "%"+level7_1211.toUpperCase()+"%");
        	}
        	
        	
        	if(!level8_1211.equals("0") && !level8_1211.equals("")){
    			stmt.setString(count++, "%"+level8_1211.toUpperCase()+"%");
        	}
        	
        	if(!level9_1211.equals("0") && !level9_1211.equals("")){
    			stmt.setString(count++, "%"+level1_1211.toUpperCase()+"%");
        	}
        	
        	if(!levell0_1211.equals("0") && !levell0_1211.equals("")){
    			stmt.setString(count++, "%"+levell0_1211.toUpperCase()+"%");
        	}
        	
        	
        	
        	if(!line_dte_sus1211.equals("") & !line_dte_sus1211.equals("0")){
        		stmt.setString(count++, line_dte_sus1211);
        	}
        	if(!location1211.equals("") & !location1211.equals("0")){
        		stmt.setString(count++, location1211);
        	}
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
	
/*start orbat publication report*/
	
	/*ORBAT PUBLICATION REPORT LOC AND STATE*/
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub(DatatablesCriterias criterias ,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs," +
			    "  a.ct_part_i_ii As ctpart,\r\n" + 
				"	l1.arms as arm,\r\n" + 
				"	l1.arm_description as arm_desc,\r\n" + 
				"	l1.line_dte_name as line_dte\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text \r\n" +
				"	  LEFT JOIN tb_miso_orbat_arm_code ar1 on ar1.arm_code=a.arm_code\r\n" + 
				"	 LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code	\r\n" + 
				"    where a.status_sus_no='Active' AND  l1.arms  NOT IN ('IAF','IN') " ;	
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' AND  l1.arms  NOT IN ('IAF','IN')";
		String FilteredCountQry =  "SELECT \r\n" + 
				"     count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' AND  l1.arms  NOT IN ('IAF','IN')";
		
		
		String qry = "";
		String qry_order = "";
		if(!sus_no121.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name121.equals("0") && !unit_name121.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
		    		qry +=" and a.form_code_control like ?";
		    	}else {
		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
    		qry +=" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!location121.equals("") & !location121.equals("0")){
			qry +=" and a.code = ? ";
		}
		
		qry_order = "ORDER BY a.form_code_control,a.sus_no,location ";
		
		System.err.println("QUERY FOR Q " + qry);
		
		System.err.println("QUERY FOR main Q " + mainQry);
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias_Pub(criterias,mainQry+qry+qry_order,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias_Pub(DatatablesCriterias criterias , String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
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
    		
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("ctpart", rs1.getString("ctpart"));
    			columns.put("arm", rs1.getString("arm"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			columns.put("line_dte", rs1.getString("line_dte"));
    			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	
	/*start of orbat loc and state CTPARTII*/
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTII(DatatablesCriterias criterias ,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs," +
			    "  a.ct_part_i_ii As ctpart,\r\n" + 
				"	l1.arms as arm,\r\n" + 
				"	l1.arm_description as arm_desc,\r\n" + 
				"	l1.line_dte_name as line_dte\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text \r\n" +
				"	  LEFT JOIN tb_miso_orbat_arm_code ar1 on ar1.arm_code=a.arm_code\r\n" + 
				"	 LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code	\r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartII' AND  l1.arms  NOT IN ('IAF','IN') " ;
				
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartII' AND  l1.arms  NOT IN ('IAF','IN') ";
		String FilteredCountQry =  "SELECT \r\n" + 
				"     count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" +
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartII' AND  l1.arms  NOT IN ('IAF','IN')";
		
		
		String qry = "";
		String qry_order = "";
		
		if(!sus_no121.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name121.equals("0") && !unit_name121.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
		    		qry +=" and a.form_code_control like ?";
		    	}else {
		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
    		qry +=" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!location121.equals("") & !location121.equals("0")){
			qry +=" and a.code = ? ";
		}
		
		qry_order = "ORDER BY a.form_code_control,a.sus_no,location ";
		
		System.err.println("QUERY FOR Q " + qry);
		
		System.err.println("QUERY FOR main Q " + mainQry);
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias_Pub_CTPARTII(criterias,mainQry+qry+qry_order,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias_Pub_CTPARTII(DatatablesCriterias criterias , String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
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
    		
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("ctpart", rs1.getString("ctpart"));
    			columns.put("arm", rs1.getString("arm"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			columns.put("line_dte", rs1.getString("line_dte"));
    			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	
	
	/*start of orbat PUBLICATION REPORT PART IA*/
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_A(DatatablesCriterias criterias ,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs," +
			    "  a.ct_part_i_ii As ctpart,\r\n" + 
				"	l1.arms as arm,\r\n" + 
				"	l1.arm_description as arm_desc,\r\n" + 
				"	l1.line_dte_name as line_dte\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text \r\n" +
				"	  LEFT JOIN tb_miso_orbat_arm_code ar1 on ar1.arm_code=a.arm_code\r\n" + 
				"	 LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code	\r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI' 	\r\n" +
				"     and l1.arms  in ('AAD','ARMD','ARTY','AVN','ENGR','HQ','INF','MECH INF') AND  l1.arms  NOT IN ('IAF','IN') " ;
				
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"    FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"    JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI'" +
				"    and l1.arms  in ('AAD','ARMD','ARTY','AVN','ENGR','HQ','INF','MECH INF') AND  l1.arms  NOT IN ('IAF','IN')";
		String FilteredCountQry =  "SELECT \r\n" + 
				"     count(*) as total \r\n" + 
				"     FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
			    "     LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"     where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI' 	\r\n" +
				"     and l1.arms  in ('AAD','ARMD','ARTY','AVN','ENGR','HQ','INF','MECH INF') AND  l1.arms  NOT IN ('IAF','IN')";
		
		
		String qry = "";
		String qry_order = "";
		
		if(!sus_no121.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name121.equals("0") && !unit_name121.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
		    		qry +=" and a.form_code_control like ?";
		    	}else {
		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
    		qry +=" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!location121.equals("") & !location121.equals("0")){
			qry +=" and a.code = ? ";
		}
		
		qry_order = "ORDER BY a.form_code_control,a.sus_no,location ";
		
		System.err.println("QUERY FOR Q " + qry);
		
		System.err.println("QUERY FOR main Q " + mainQry);
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias_Pub_CTPART_A(criterias,mainQry+qry+qry_order,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias_Pub_CTPART_A(DatatablesCriterias criterias , String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
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
    		
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("ctpart", rs1.getString("ctpart"));
    			columns.put("arm", rs1.getString("arm"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			columns.put("line_dte", rs1.getString("line_dte"));
    			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	
	
	/*start of orbat PUBLICATION REPORT PART IB*/
	public DataSet<Map<String, Object>> findActiveOrbatDetailsWithDatatablesCriterias_Pub_CTPARTI_B(DatatablesCriterias criterias ,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
		String mainQry = "SELECT \r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"    ab.cmd_name,\r\n" + 
				"    ac.coprs_name,\r\n" + 
				"    ad.div_name,\r\n" + 
				"    ae.bde_name,\r\n" + 
				"    e.code_value AS location,\r\n" + 
				"    nr.code_value AS nrs," +
			    "  a.ct_part_i_ii As ctpart,\r\n" + 
				"	l1.arms as arm,\r\n" + 
				"	l1.arm_description as arm_desc,\r\n" + 
				"	l1.line_dte_name as line_dte\r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
				"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
				"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
				"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
				"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text \r\n" +
				"	  LEFT JOIN tb_miso_orbat_arm_code ar1 on ar1.arm_code=a.arm_code\r\n" + 
				"	 LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code	\r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI' 	\r\n" +
				"     and l1.arms  in ('AMC','AOC','APS','ASC','EME','GL AND RASO','MC','PNR','RVC','SIG','TC') AND \r\n " + 
				"    l1.arms  NOT IN ('IAF','IN')  ";
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"    FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"    JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"    LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
				"    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI' \r\n" +
				"     and l1.arms  in ('AMC','AOC','APS','ASC','EME','GL AND RASO','MC','PNR','RVC','SIG','TC') AND \r\n " + 
				"    l1.arms  NOT IN ('IAF','IN') ";
		String FilteredCountQry =  "SELECT \r\n" + 
				"     count(*) as total \r\n" + 
				"     FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
			    "     LEFT JOIN tb_miso_orbat_line_dte l1 on l1.arm_code= a.arm_code \r\n" + 
			    "    where a.status_sus_no='Active' and a.ct_part_i_ii = 'CTPartI' \r\n" +
				"     and l1.arms  in ('AMC','AOC','APS','ASC','EME','GL AND RASO','MC','PNR','RVC','SIG','TC') AND \r\n " + 
				"    l1.arms  NOT IN ('IAF','IN') ";
		
		
		String qry = "";
		String qry_order = "";
		
		
		if(!sus_no121.equals("")){
    		qry +=" and upper(a.sus_no) like ?";
    	}
    	if(!unit_name121.equals("0") && !unit_name121.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
    		qry +=" and a.form_code_control = ? ";
    	}else {
    		if(!cont_div121.equals("0") && !cont_div121.equals("")){
	    		qry +=" and a.form_code_control like ? ";
	    	}else {
	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
		    		qry +=" and a.form_code_control like ?";
		    	}else {
		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
		    			qry +=" and a.form_code_control like ? ";
			    	}
		    	}
		    }
	    }
		if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
    		qry +=" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = ? ) ";
    	}
		
		if(!location121.equals("") & !location121.equals("0")){
			qry +=" and a.code = ? ";
		}
		
		qry_order = "ORDER BY a.form_code_control,a.sus_no,location ";
		
		System.err.println("QUERY FOR Q " + qry);
		
		System.err.println("QUERY FOR main Q " + mainQry);
    	List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias_Pub_CTPART_B(criterias,mainQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(criterias,FilteredCountQry+qry,sus_no121,unit_name121,cont_bde121,cont_div121,cont_corps121,cont_comd121,line_dte_sus121,location121);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	
	
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias_Pub_CTPART_B(DatatablesCriterias criterias , String qry,String sus_no121,String unit_name121,String cont_bde121,String cont_div121,String cont_corps121,String cont_comd121,String line_dte_sus121,String location121) {
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
    		
    		int count=1;
    		if( !sus_no121.equals("")){
        		stmt.setString(count++, sus_no121.toUpperCase()+"%");
        	}
        	if(!unit_name121.equals("0") && !unit_name121.equals("")){
    			stmt.setString(count++, "%"+unit_name121.toUpperCase()+"%");
        	} 
        	if(!cont_bde121.equals("0") && !cont_bde121.equals("")){
        		stmt.setString(count++, cont_bde121);
        	}else {
        		if(!cont_div121.equals("0") && !cont_div121.equals("")){
    	    		stmt.setString(count++, cont_div121+"%");
    	    	}else {
    	    		if(!cont_corps121.equals("0") && !cont_corps121.equals("")){
    	    			stmt.setString(count++, cont_corps121+"%");
    		    	}else {
    		    		if(!cont_comd121.equals("-1") && !cont_comd121.equals("")){
    		    			stmt.setString(count++, cont_comd121+"%");
    			    	}
    		    	}
    		    }
    	    }
        	if(!line_dte_sus121.equals("") & !line_dte_sus121.equals("0")){
        		stmt.setString(count++, line_dte_sus121);
        	}
        	if(!location121.equals("") & !location121.equals("0")){
        		stmt.setString(count++, location121);
        	}
    		ResultSet rs1 = stmt.executeQuery();     
    		
    	
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("ctpart", rs1.getString("ctpart"));
    			columns.put("arm", rs1.getString("arm"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			columns.put("line_dte", rs1.getString("line_dte"));
    			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}


	//AUTHORITY VIEW REPORT DAOIMPL
	
	public DataSet<Map<String, Object>> findAuthorityReportWithDatatablesCriterias(DatatablesCriterias criterias , String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12,String letter_no) {
		String mainQry = "SELECT a.id,\r\n" + 
				"    a.sus_no,\r\n" + 
				"    a.unit_name,\r\n" + 
				"	a.letter_no,\r\n" + 
				"    b.approved_rejected_on,\r\n" + 
				"    a.status_sus_no,\r\n" + 
				"    a.sus_version,\r\n" + 
				"    c.label AS action\r\n" + 
				"     FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue";
		String TotalCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue ";
		String FilteredCountQry = "SELECT \r\n" + 
				"    count(*) as total \r\n" + 
				"   FROM tb_miso_orbat_unt_dtl a\r\n" + 
				"     JOIN tb_miso_orbat_shdul_detl b ON a.id = b.letter_id" +
				"     JOIN t_domain_value c ON c.domainid = 'SCHEDULETYPE'::text AND b.type_of_letter::text = c.codevalue ";
		
		
		//Date to_date1 = Calendar.getInstance().getTime();  			 
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
       // String to_date = dateFormat.format(to_date1);
        
       // Date currentDate = null;
       // Date from = null;
      //  Date to = null;
		
		
		String qry="";
		if(status_sus_no12.equals("All")){
    		qry +=" where a.status_sus_no in ('Pending','Active','Inactive','INVALID') ";
    	}else {
    		qry +=" where a.status_sus_no = ? ";
    	}
    	if(!sus_no12.equals("0") && !sus_no12.equals("")){
    		qry +=" and upper(a.sus_no) like ? ";
    	}
    	if(!unit_name12.equals("0") && !unit_name12.equals("")){
			qry +=" and upper(a.unit_name) like ? ";
    	} 
    	
    	if(!letter_no.trim().equals("") && !letter_no.equals("")){
			qry +=" and a.letter_no  ilike ? ";
    	} 
    	
    	
    	
    	if(!action12.equals("")){
    		qry +=" and c.label = ? ";
    	}
    	
		
		List<Map<String, Object>> metadata = findAllDetailsCriteriasformation_Authority(criterias,mainQry+qry, status_sus_no12,sus_no12,unit_name12,cont_bde12,cont_div12,cont_corps12,action12,approved_rejected_on12,approved_rejected_on22,cont_comd12,letter_no);
		Long count = getTotalCountFormation(TotalCountQry);
		Long countFiltered = getFilteredCountFormation_Authority_View(criterias,FilteredCountQry+qry,status_sus_no12,sus_no12,unit_name12,cont_bde12,cont_div12,cont_corps12,action12,approved_rejected_on12,approved_rejected_on22,cont_comd12,letter_no);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findAllDetailsCriteriasformation_Authority(DatatablesCriterias criterias , String qry, String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12,String letter_no) {
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
    		
    		int count=1;
    		if(!status_sus_no12.equals("All")){
    			stmt.setString(count++, status_sus_no12);
    		}
    		if(!sus_no12.equals("0") && !sus_no12.equals("")){
    			stmt.setString(count++, sus_no12.toUpperCase()+"%");
        	}
        	if(!unit_name12.equals("0") && !unit_name12.equals("")){
        		stmt.setString(count++, "%"+unit_name12.toUpperCase()+"%");
        	} 
        	
        	if(!letter_no.equals("0") && !letter_no.equals("")){
        		stmt.setString(count++, "%"+letter_no.toUpperCase()+"%");
        	} 
        	
        	if(!action12.equals("")){
        		stmt.setString(count++, action12);
        	}
        	
    		
			ResultSet rs1 = stmt.executeQuery(); 
			System.err.println("qUERY FOR FMN WISE " + stmt);
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("letter_no", rs1.getString("letter_no"));
    			columns.put("approved_rejected_on", rs1.getString("approved_rejected_on"));
    			columns.put("status_sus_no", rs1.getString("status_sus_no"));
    			columns.put("sus_version", rs1.getString("sus_version"));
    			columns.put("action", rs1.getString("action"));  			
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	
	private Long getFilteredCountFormation_Authority_View(DatatablesCriterias criterias ,String qry, String status_sus_no12,String sus_no12,String unit_name12,String cont_bde12,String cont_div12,String cont_corps12,String action12,String approved_rejected_on12,String approved_rejected_on22,String cont_comd12,String letter_no) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long totalFiltered = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		int count=1;
    		if(!status_sus_no12.equals("All")){
    			stmt.setString(count++, status_sus_no12);
    		}
    		if(!sus_no12.equals("0") && !sus_no12.equals("")){
    			stmt.setString(count++, sus_no12.toUpperCase()+"%");
        	}
        	if(!unit_name12.equals("0") && !unit_name12.equals("")){
        		stmt.setString(count++, "%"+unit_name12.toUpperCase()+"%");
        	} 
        	
        	if(!unit_name12.equals("0") && !unit_name12.equals("")){
        		stmt.setString(count++, "%"+unit_name12.toUpperCase()+"%");
        	} 
        	
        	if(!action12.equals("")){
        		stmt.setString(count++, action12);
        	}
        	
    		
			ResultSet rs = stmt.executeQuery();   
			if (rs.next()) {
				totalFiltered = rs.getLong(1);
	    	}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return totalFiltered;
	}
	
}