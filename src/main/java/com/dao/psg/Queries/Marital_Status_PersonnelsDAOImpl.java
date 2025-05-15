package com.dao.psg.Queries;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Marital_Status_PersonnelsDAOImpl implements Marital_Status_PersonnelsDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> getMarital_Status_PersonnelsReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = " select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.offr,app.jco,app.or1,app.total,app.form_code_control,app.marital_status,app.date_of_birth,app.date_of_birth1\r\n" + 
					"		from (select distinct \r\n" + 
					"				fv.unit_name as cmd_unit,\r\n" + 
					"				fvm.unit_name as corp_unit,\r\n" + 
					"				div.unit_name as div_unit,\r\n" + 
					"				bde.unit_name as bde_unit,\r\n" + 
					"				orb.unit_name,\r\n" + 
					"				hld.unit_sus_no,\r\n" + 
					"				orb.form_code_control,\r\n" + 
					"				hld.marital_status,\r\n" + 
					"				TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"				TO_CHAR(hld.date_of_birth1,'DD-MON-YYYY') as date_of_birth1,\r\n" + 
					"				count(hld.marital_status) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
					"				count(hld.marital_status) FILTER (where hld.typ='JCO') as jco,\r\n" + 
					"				count(hld.marital_status) FILTER (where hld.typ='OR') as or1,\r\n" + 
					"				count(hld.marital_status) as total\r\n" + 
					"				from (\r\n" + 
					"						select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"							 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"					 		 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"				\r\n" + 
					"						union all\r\n" + 
					"							select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"							inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"							where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
					"						union all\r\n" + 
					"							select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
					"							inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
					"							where o.status in ('1','5') and o.category  = 'OR' " + 
					"				) hld \r\n" + 
					"				inner join tb_psg_mstr_marital_status m on m.marital_id = hld.marital_status \r\n" + 
					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
					"				\r\n" + 
					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"                group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.marital_status,hld.date_of_birth,hld.date_of_birth1 ) app  " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
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

public long getMarital_Status_PersonnelsTotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(app1.*) from\r\n" + 
 					"			(select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.offr,app.jco,app.or1,app.total,app.form_code_control,app.marital_status,app.date_of_birth,app.date_of_birth1\r\n" + 
 					"			from (select distinct \r\n" + 
 					"				fv.unit_name as cmd_unit,\r\n" + 
 					"				fvm.unit_name as corp_unit,\r\n" + 
 					"				div.unit_name as div_unit,\r\n" + 
 					"				bde.unit_name as bde_unit,\r\n" + 
 					"				orb.unit_name,\r\n" + 
 					"				hld.unit_sus_no,\r\n" + 
 					"				orb.form_code_control,\r\n" + 
 					"				hld.marital_status,\r\n" + 
 					"				TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
 					"				TO_CHAR(hld.date_of_birth1,'DD-MM-YYYY') as date_of_birth1,\r\n" +
 					"				count(hld.marital_status) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
 					"				count(hld.marital_status) FILTER (where hld.typ='JCO') as jco,\r\n" + 
 					"				count(hld.marital_status) FILTER (where hld.typ='OR') as or1,\r\n" + 
 					"				count(hld.marital_status) as total\r\n" + 
 					"				from (\r\n" + 
					"						select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"							 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"					 		 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"				\r\n" + 
					"						union all\r\n" + 
					"							select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"							inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"							where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
					"						union all\r\n" + 
					"							select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
					"							inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
					"							where o.status in ('1','5') and o.category  = 'OR' " + 
					"				) hld \r\n" + 
 					"				inner join tb_psg_mstr_marital_status m on m.marital_id = hld.marital_status \r\n" + 
 					"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
 					"				\r\n" + 
 					"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
 					"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
 					"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
 					"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
 					"                group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.marital_status,hld.date_of_birth,hld.date_of_birth1 ) app " +SearchValue +") app1";
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
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


  	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ? or"
 					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
 					+ " or lower(app.bde_unit) like ? or cast(app.total as character varying) = ? "
 					+ "or cast(app.offr as character varying) = ? or cast(app.jco as character varying) = ? "
 					+ "or cast(app.or1 as character varying) = ? )";
 		}
  		
  		
  		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
  				SearchValue +=" and lower(app.unit_sus_no) = ?";
			} else {
				SearchValue += " where lower(app.unit_sus_no) = ? ";
			}
  			
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
    	} 
    	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and app.form_code_control = ? ";
			} else {
				SearchValue += " where app.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and app.form_code_control like ? ";
    			} else {
    				SearchValue += " where app.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and app.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where app.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }
    	if(!marital_status.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.marital_status as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.marital_status as character varying) = ? ";
			}
    	}
    	
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(app.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(app.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			}
		}
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
    		}	
			
			if(!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toLowerCase());
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toLowerCase());
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")){
				flag += 1;
				stmt.setString(flag, cont_bde);
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			flag += 1;
 					stmt.setString(flag, cont_div+"%");
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_corps+"%");
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_comd+"%");
				    	}
			    	}
			    }
		    }
			if(!marital_status.equals("0")) {
				flag += 1;
				stmt.setString(flag, marital_status);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
			}
 			
 		}catch (Exception e) {}
 		return stmt;
 	}
  


	public List<Map<String, Object>> getMarital_Status_PersonnelsReportDataListDetails_pers(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select \r\n" + 
					"f.unit_name,\r\n" + 
					"hld.unit_sus_no,\r\n" + 
					"hld.army_no,\r\n" + 
					"hld.description,\r\n" + 
					"hld.name,\r\n" + 
					"hld.marital_status,\r\n" + 
					"t.marital_name,\r\n" + 
					"TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"TO_CHAR(hld.date_of_birth1,'DD-MM-YYYY') as date_of_birth1\r\n" + 
					"	from (\r\n" + 
					"				select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"					 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"					 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"\r\n" + 
					"				union all\r\n" + 
					"					select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"					inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"					where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
					"				union all\r\n" + 
					"					select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
					"					inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
					"					where o.status in ('1','5') and o.category  = 'OR' \r\n" + 
					"		) hld \r\n" + 
					"		inner join tb_psg_mstr_marital_status t on cast(t.marital_id as integer)= hld.marital_status \r\n" + 
					"		inner join tb_miso_orbat_unt_dtl f on f.sus_no = hld.unit_sus_no and f.status_sus_no='Active' " +SearchValue + " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
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

public long getMarital_Status_PersonnelsTotalCountDtl_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
		String SearchValue = GenerateQueryWhereClause_SQL_pers(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app.*) from\r\n" + 
					"	(select \r\n" + 
					"f.unit_name,\r\n" + 
					"hld.unit_sus_no,\r\n" + 
					"hld.army_no,\r\n" + 
					"hld.description,\r\n" + 
					"hld.name,\r\n" + 
					"hld.marital_status,\r\n" + 
					"t.marital_name,\r\n" + 
					"TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
					"TO_CHAR(hld.date_of_birth1,'DD-MM-YYYY') as date_of_birth1\r\n" + 
					"	from (\r\n" + 
					"				select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
					"					 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
					"					 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
					"\r\n" + 
					"				union all\r\n" + 
					"					select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
					"					inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
					"					where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
					"				union all\r\n" + 
					"					select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
					"					inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
					"					where o.status in ('1','5') and o.category  = 'OR' \r\n" + 
					"		) hld \r\n" + 
					"		inner join tb_psg_mstr_marital_status t on cast(t.marital_id as integer)= hld.marital_status \r\n" + 
					"		inner join tb_miso_orbat_unt_dtl f on f.sus_no = hld.unit_sus_no and f.status_sus_no='Active' " + 
					"" + SearchValue +
					") app  " ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_pers(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,marital_status,dt_from,dt_to);
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


	public String GenerateQueryWhereClause_SQL_pers(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
		String SearchValue ="";
		if(!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +=" lower(hld.unit_sus_no) like ? or lower(f.unit_name) like ?  or lower(hld.army_no) like ? "
					+ "or lower(hld.description) like ? or lower(hld.name) like ? or lower(t.marital_name) like ? "
					+ "or lower((TO_CHAR(cast(hld.date_of_birth  as  date),'DD-MON-YYYY'))) like ? "
					+ ")";
			
		}
		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(hld.unit_sus_no) = ? ";
			} else {
				SearchValue += " where lower(hld.unit_sus_no) = ? ";
			}
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(f.unit_name) = ? ";
			} else {
				SearchValue += " where lower(f.unit_name) = ? ";
			}
    	} 
    	
    	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and f.form_code_control = ? ";
			} else {
				SearchValue += " where f.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and f.form_code_control like ? ";
    			} else {
    				SearchValue += " where f.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and f.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where f.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and f.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where f.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }
    	
    	if(!marital_status.equals("0")){
    		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.marital_status = cast(? as integer) ";
			} else {
				SearchValue += " where hld.marital_status = cast(? as integer) ";
			}
    	}
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(hld.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(hld.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			}
		}
		
 return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL_pers(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to) {
		int flag = 0;
		try {
  		if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
  		}	
		
		if(!sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, sus_no.toLowerCase());
		}
		if(!unit_name.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_name.toLowerCase());
		}
		if(!cont_bde.equals("0") && !cont_bde.equals("")){
			flag += 1;
			stmt.setString(flag, cont_bde);
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			flag += 1;
				stmt.setString(flag, cont_div+"%");
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			flag += 1;
 					stmt.setString(flag, cont_corps+"%");
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_comd+"%");
			    	}
		    	}
		    }
	    }
		if(!marital_status.equals("0")) {
			flag += 1;
			stmt.setString(flag, marital_status);
		}
		if(!dt_from.equals("") && !dt_to.equals("")){
			flag+=1;
			stmt.setString(flag, dt_from);
			flag+=1;
			stmt.setString(flag, dt_to);
		}
			
		}catch (Exception e) {}
		return stmt;
	}


public ArrayList<ArrayList<String>> pdf_getMarital_Status_PersonnelsReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
  				SearchValue +=" and lower(app.unit_sus_no) = ?";
			} else {
				SearchValue += " where lower(app.unit_sus_no) = ? ";
			}
  			
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
    	} 
    	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and app.form_code_control = ? ";
			} else {
				SearchValue += " where app.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and app.form_code_control like ? ";
    			} else {
    				SearchValue += " where app.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and app.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where app.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }
    	if(!marital_status.equals("0")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and cast(app.marital_status as character varying)  = ? ";
			} else {
				SearchValue += " where cast(app.marital_status as character varying) = ? ";
			}
    	}
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(app.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(app.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			}
		}
		
		q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no,app.offr,app.jco,app.or1,app.total,app.form_code_control,app.marital_status,app.date_of_birth,app.date_of_birth1\r\n" + 
				"		from (select distinct \r\n" + 
				"				fv.unit_name as cmd_unit,\r\n" + 
				"				fvm.unit_name as corp_unit,\r\n" + 
				"				div.unit_name as div_unit,\r\n" + 
				"				bde.unit_name as bde_unit,\r\n" + 
				"				orb.unit_name,\r\n" + 
				"				hld.unit_sus_no,\r\n" + 
				"				orb.form_code_control,\r\n" + 
				"				hld.marital_status,\r\n" + 
				"				TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
				"				TO_CHAR(hld.date_of_birth1,'DD-MM-YYYY') as date_of_birth1,\r\n" + 
				"				count(hld.marital_status) FILTER (where hld.typ='OFFR') as offr,\r\n" + 
				"				count(hld.marital_status) FILTER (where hld.typ='JCO') as jco,\r\n" + 
				"				count(hld.marital_status) FILTER (where hld.typ='OR') as or1,\r\n" + 
				"				count(hld.marital_status) as total\r\n" + 
				"				from (\r\n" + 
				"						select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"							 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"					 		 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
				"				\r\n" + 
				"						union all\r\n" + 
				"							select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
				"							inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
				"							where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
				"						union all\r\n" + 
				"							select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
				"							inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
				"							where o.status in ('1','5') and o.category  = 'OR' " + 
				"				) hld \r\n" + 
				"				inner join tb_psg_mstr_marital_status m on m.marital_id = hld.marital_status \r\n" + 
				"				inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = hld.unit_sus_no and orb.status_sus_no='Active'\r\n" + 
				"		\r\n" + 
				"				left join all_fmn_view fv  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
				"				left join all_fmn_view fvm  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"				left join all_fmn_view div  on orb.sus_no = hld.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"				left join all_fmn_view bde  on orb.sus_no = hld.unit_sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
				"                group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.unit_sus_no,orb.form_code_control,hld.marital_status,hld.date_of_birth,hld.date_of_birth1 ) app " + SearchValue ;
			stmt=conn.prepareStatement(q);
			int flag =0;
			if(!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toLowerCase());
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toLowerCase());
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")){
				flag += 1;
				stmt.setString(flag, cont_bde);
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			flag += 1;
					stmt.setString(flag, cont_div+"%");
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_corps+"%");
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_comd+"%");
				    	}
			    	}
			    }
		    }
			if(!marital_status.equals("0")) {
				flag += 1;
				stmt.setString(flag, marital_status);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
			}
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("cmd_unit"));//0
				list.add(rs.getString("corp_unit"));//1
				list.add(rs.getString("div_unit"));//2
				list.add(rs.getString("bde_unit"));//3
				list.add(rs.getString("unit_name"));//4
				list.add(rs.getString("unit_sus_no"));//5
				list.add(rs.getString("offr"));//6
				list.add(rs.getString("jco"));//7
				list.add(rs.getString("or1"));//8
				list.add(rs.getString("total"));//9
							
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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
	
public ArrayList<ArrayList<String>> pdf_getMarital_Status_PersonnelsReportDataList_pers(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String marital_status,String dt_from,String dt_to)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(hld.unit_sus_no) = ? ";
			} else {
				SearchValue += " where lower(hld.unit_sus_no) = ? ";
			}
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(f.unit_name) = ? ";
			} else {
				SearchValue += " where lower(f.unit_name) = ? ";
			}
    	} 
    	
    	
    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and f.form_code_control = ? ";
			} else {
				SearchValue += " where f.form_code_control = ? ";
			}
    	}else {
    		if(!cont_div.equals("0") && !cont_div.equals("")){
    			if (SearchValue.contains("where")) {
        			SearchValue +=" and f.form_code_control like ? ";
    			} else {
    				SearchValue += " where f.form_code_control like ? ";
    			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and f.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where f.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and f.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where f.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }
    	
    	if(!marital_status.equals("0")){
    		if (SearchValue.contains("where")) {
				SearchValue +=" and hld.marital_status = cast(? as integer) ";
			} else {
				SearchValue += " where hld.marital_status = cast(? as integer) ";
			}
    	}
    	if(!dt_from.equals("") && !dt_to.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue +="and cast(hld.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			} else {
				SearchValue +="where cast(hld.date_of_birth1 as date) between cast(? as date) and cast(? as date)";
			}
		}
			
		q="select \r\n" + 
				"f.unit_name,\r\n" + 
				"hld.unit_sus_no,\r\n" + 
				"hld.army_no,\r\n" + 
				"hld.description,\r\n" + 
				"hld.name,\r\n" + 
				"hld.marital_status,\r\n" + 
				"t.marital_name,\r\n" + 
				"TO_CHAR(hld.date_of_birth,'DD-MON-YYYY') as date_of_birth,\r\n" + 
				"TO_CHAR(hld.date_of_birth1,'DD-MM-YYYY') as date_of_birth1\r\n" + 
				"	from (\r\n" + 
				"				select c.name,c.personnel_no as army_no,d.description,cd.marital_status,c.date_of_birth,c.date_of_birth as date_of_birth1,c.unit_sus_no,'OFFR' AS typ from tb_psg_trans_proposed_comm_letter c \r\n" + 
				"					 inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and c.status in ('1','5') \r\n" + 
				"					 inner join cue_tb_psg_rank_app_master d on  d.id = c.rank and upper(d.level_in_hierarchy) = 'RANK'  \r\n" + 
				"\r\n" + 
				"				union all\r\n" + 
				"					select j.full_name as name,j.army_no,r.rank as description,j.marital_status,j.date_of_birth,j.date_of_birth as date_of_birth1,j.unit_sus_no,'JCO' AS typ from tb_psg_census_jco_or_p j \r\n" + 
				"					inner join tb_psg_mstr_rank_jco r on r.id = j.rank\r\n" + 
				"					where  j.status in ('1','5') and j.category  = 'JCO' \r\n" + 
				"				union all\r\n" + 
				"					select o.full_name as name,o.army_no,r.rank as description,o.marital_status,o.date_of_birth,o.date_of_birth as date_of_birth1,o.unit_sus_no,'OR' AS typ from tb_psg_census_jco_or_p o \r\n" + 
				"					inner join tb_psg_mstr_rank_jco r on r.id = o.rank\r\n" + 
				"					where o.status in ('1','5') and o.category  = 'OR' \r\n" + 
				"		) hld \r\n" + 
				"		inner join tb_psg_mstr_marital_status t on cast(t.marital_id as integer)= hld.marital_status \r\n" + 
				"		inner join tb_miso_orbat_unt_dtl f on f.sus_no = hld.unit_sus_no and f.status_sus_no='Active' " +SearchValue ;
			stmt=conn.prepareStatement(q);
			int flag =0;
			if(!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toLowerCase());
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toLowerCase());
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")){
				flag += 1;
				stmt.setString(flag, cont_bde);
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			flag += 1;
					stmt.setString(flag, cont_div+"%");
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_corps+"%");
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_comd+"%");
				    	}
			    	}
			    }
		    }
			if(!marital_status.equals("0")) {
				flag += 1;
				stmt.setString(flag, marital_status);
			}
			if(!dt_from.equals("") && !dt_to.equals("")){
				flag+=1;
				stmt.setString(flag, dt_from);
				flag+=1;
				stmt.setString(flag, dt_to);
			}
			
			ResultSet rs = stmt.executeQuery(); 
			int i =1;    
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("unit_name"));//0
				list.add(rs.getString("unit_sus_no"));//1
				list.add(rs.getString("army_no"));//2
				list.add(rs.getString("description"));//3
				list.add(rs.getString("name"));//4
				list.add(rs.getString("marital_name"));//5
				list.add(rs.getString("date_of_birth"));//6
				alist.add(list);
 	        }
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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
