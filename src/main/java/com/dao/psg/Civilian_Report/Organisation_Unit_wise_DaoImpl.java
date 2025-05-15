package com.dao.psg.Civilian_Report;

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
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Organisation_Unit_wise_DaoImpl implements Organisation_Unit_wise_Dao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}	
		 
	 public List<Map<String, Object>> getOraganisation_Unit_Wise_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String parent_arm, String cadre,
			 HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, 
	         InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,parent_arm,cadre);
		String Cadre = "";
		if(cadre != null && !cadre.equals("") && !cadre.equals("0"))
		{
			Cadre += "and cast (cd.id as character varying) = cast (? as character varying) ";
		}

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select distinct app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.sus_no,app.unit_name,app.form_code_control,\r\n" + 
					"       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in,app.arm_code\r\n" + 
					"from (\r\n" + 
					"	select distinct a.arm_code,\r\n" + 
					"	--cd.id as cadre,\r\n" + 
					"   orb.sus_no,orb.unit_name,\r\n" + 
					"   fv.unit_name as cmd_unit,\r\n" + 
					"   fvm.unit_name as corp_unit,\r\n" + 
					"   div.unit_name as div_unit,\r\n" + 
					"   bde.unit_name as bde_unit,\r\n" + 
					"	orb.form_code_control,\r\n" + 
					"   count(*) filter (where c.civ_group = 'A' and c.classification_services = '1') as groupa_gaz,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '1') as groupb_gaz,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '2') as groupb_nongaz_nonin,\r\n" + 
					"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '2') as groupc_nongaz_nonin,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '1') as groupb_nongaz_in,\r\n" + 
					"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '1') as groupc_nongaz_in\r\n" + 
					"from tb_psg_civilian_dtl_main c \r\n" + 
					"inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no\r\n" + 
					"inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\r\n" + 
//					"left join tb_psg_mstr_cadre_civilian cd on cd.id = c.cadre \r\n" + 
					"left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"where c.civilian_status = 'R' and c.status = '1'  \r\n" + Cadre +
					"group by a.arm_code,orb.sus_no,orb.unit_name,fv.unit_name,fvm.unit_name,div.unit_name,bde.unit_name,orb.form_code_control) app" +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,parent_arm,cadre);
			System.err.println("query held unitwise \n " + stmt);
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
	
	public long getOraganisation_Unit_Wise_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,
			String unit_name,String sus_no,String parent_arm, String cadre) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,parent_arm,cadre);
		int total = 0;
		String q = null;
		String Cadre = "";
		if(cadre != null && !cadre.equals("") && !cadre.equals("0"))
		{
			Cadre += "and cast (cd.id as character varying) = cast (? as character varying) ";
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from\r\n" + 
					"	(select distinct app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.sus_no,app.unit_name,app.form_code_control,\r\n" + 
					"       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in,app.arm_code\r\n" + 
					"from (\r\n" + 
					"	select distinct a.arm_code,\r\n" + 
					"	--cd.id as cadre,\r\n" + 
					"   orb.sus_no,orb.unit_name,\r\n" + 
					"   fv.unit_name as cmd_unit,\r\n" + 
					"   fvm.unit_name as corp_unit,\r\n" + 
					"   div.unit_name as div_unit,\r\n" + 
					"   bde.unit_name as bde_unit,\r\n" + 
					"	orb.form_code_control,\r\n" + 
					"   count(*) filter (where c.civ_group = 'A' and c.classification_services = '1') as groupa_gaz,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '1') as groupb_gaz,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '2') as groupb_nongaz_nonin,\r\n" + 
					"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '2') as groupc_nongaz_nonin,\r\n" + 
					"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '1') as groupb_nongaz_in,\r\n" + 
					"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '1') as groupc_nongaz_in\r\n" + 
					"from tb_psg_civilian_dtl_main c \r\n" + 
					"inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no\r\n" + 
					"inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\r\n" + 
//					"left join tb_psg_mstr_cadre_civilian cd on cd.id = c.cadre \r\n" + 
					"left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"where c.civilian_status = 'R' and c.status = '1' \r\n" + Cadre +
					"	group by a.arm_code,orb.sus_no,orb.unit_name,fv.unit_name,fvm.unit_name,div.unit_name,bde.unit_name,orb.form_code_control) app" +SearchValue +") app1";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,parent_arm,cadre);
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
	
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,
			String sus_no,String parent_arm, String cadre) {
 		String SearchValue ="";
 		
 		
 		
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
 			SearchValue +="lower(app.sus_no) like ? or lower(app.unit_name) like ? "
 					+ "or cast(app.groupa_gaz as character varying) = ? or cast(app.groupb_gaz as character varying) = ? "
 					+ "or cast(app.groupb_nongaz_nonin as character varying) = ? or cast(app.groupc_nongaz_nonin as character varying) = ? "
 					+ "or cast(app.groupb_nongaz_in as character varying) = ? or cast(app.groupc_nongaz_in as character varying) = ? )";
 		}
  		
  		
  		if(!sus_no.equals("")){
  			if (SearchValue.contains("where")) {
  				SearchValue +=" and lower(app.sus_no) = ?";
			} else {
				SearchValue += " where lower(app.sus_no) = ? ";
			}
  			
    	}
    	if(!unit_name.equals("0") && !unit_name.equals("")){
    		if (SearchValue.contains("where")) {
    			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
    	} 
    	if(!parent_arm.equals("0") && !parent_arm.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and app.arm_code = ? ";
			} else {
				SearchValue += " where app.arm_code = ? ";
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
    	
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,
		  String cont_bde,String unit_name,String sus_no,String parent_arm, String cadre) {
 		int flag = 0;
 		try {
 			
 			
 			
 			if(cadre != null && !cadre.equals("") && !cadre.equals("0"))
 			{
 				flag += 1;
 				stmt.setString(flag, cadre);
 			}
 			
    		if(!Search.equals("")) {
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
			if(!parent_arm.equals("0") && !parent_arm.equals("")){
				flag += 1;
				stmt.setString(flag, parent_arm);
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
 			
 		}catch (Exception e) {}
 		return stmt;
 	}
  
  

public ArrayList<ArrayList<String>> pdf_Oraganisation_Unit_Wise_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,
		String unit_name,String sus_no,String parent_arm, String cadre)
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
      	  if(!parent_arm.equals("0") && !parent_arm.equals("")){
    			if (SearchValue.contains("where")) {
    				SearchValue +=" and app.arm_code = ? ";
    			} else {
    				SearchValue += " where app.arm_code = ? ";
    			}
        	}
        	if(!cadre.equals("0") && !cadre.equals("")){
    			if (SearchValue.contains("where")) {
    				SearchValue +=" and app.cadre = ? ";
    			} else {
    				SearchValue += " where app.cadre = ? ";
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
    	
		q="select distinct app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.sus_no,app.unit_name,app.form_code_control,\r\n" + 
				"       app.groupa_gaz,app.groupb_gaz,app.groupb_nongaz_nonin,app.groupc_nongaz_nonin,app.groupb_nongaz_in,app.groupc_nongaz_in,app.arm_code\r\n" + 
				"from (\r\n" + 
				"	select distinct a.arm_code,\r\n" + 
				"	--cd.id as cadre,\r\n" + 
				"   orb.sus_no,orb.unit_name,\r\n" + 
				"   fv.unit_name as cmd_unit,\r\n" + 
				"   fvm.unit_name as corp_unit,\r\n" + 
				"   div.unit_name as div_unit,\r\n" + 
				"   bde.unit_name as bde_unit,\r\n" + 
				"	orb.form_code_control,\r\n" + 
				"   count(*) filter (where c.civ_group = 'A' and c.classification_services = '1') as groupa_gaz,\r\n" + 
				"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '1') as groupb_gaz,\r\n" + 
				"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '2') as groupb_nongaz_nonin,\r\n" + 
				"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '2') as groupc_nongaz_nonin,\r\n" + 
				"   count(*) filter (where c.civ_group = 'B' and c.classification_services = '2' and c.civ_type = '1') as groupb_nongaz_in,\r\n" + 
				"   count(*) filter (where c.civ_group = 'C' and c.classification_services = '2' and c.civ_type = '1') as groupc_nongaz_in\r\n" + 
				"from tb_psg_civilian_dtl_main c \r\n" + 
				"inner join cue_psg_auth_held ah on c.sus_no = ah.unit_sus_no\r\n" + 
				"inner join tb_miso_orbat_arm_code a on a.arm_code = ah.arm_code\r\n" + 
				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\r\n" + 
//				"left join tb_psg_mstr_cadre_civilian cd on cd.id = c.cadre \r\n" + 
				"left join all_fmn_view fv  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
				"left join all_fmn_view fvm  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
				"left join all_fmn_view div  on orb.sus_no = c.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
				"left join all_fmn_view bde  on orb.sus_no = c.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
				"where c.civilian_status = 'R' and c.status = '1' \r\n" + 
				"	group by a.arm_code,orb.sus_no,orb.unit_name,fv.unit_name,fvm.unit_name,div.unit_name,bde.unit_name,orb.form_code_control) app" + SearchValue ;
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
			if(!parent_arm.equals("0") && !parent_arm.equals("")){
				flag += 1;
				stmt.setString(flag, parent_arm);
	    	}
	    	if(!cadre.equals("0") && !cadre.equals("")){
	    		flag += 1;
				stmt.setString(flag, cadre);
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
		
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				///list.add(String.valueOf(i++)); //0
				list.add(rs.getString("sus_no"));//0
				list.add(rs.getString("unit_name"));//1
				list.add(rs.getString("groupa_gaz"));//2
				list.add(rs.getString("groupb_gaz"));//3
				list.add(rs.getString("groupb_nongaz_nonin"));//4
				list.add(rs.getString("groupc_nongaz_nonin"));//5
				list.add(rs.getString("groupb_nongaz_in"));//6
				list.add(rs.getString("groupc_nongaz_in"));//7
				list.add("");//8
							
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
