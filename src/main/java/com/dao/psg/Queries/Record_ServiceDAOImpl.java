package com.dao.psg.Queries;

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

public class Record_ServiceDAOImpl implements Record_ServiceDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	
	public List<Map<String, Object>>getRecord_ServiceReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name," + 
					"app.unit_sus_no,app.personnel_no,app.cadet_no,app.form_code_control," + 
					"app.id,app.comm_id,app.comm_status," + 
					"app.rank,app.name\r\n" + 
					"from(select distinct \r\n" + 
					"fv.unit_name as cmd_unit,\r\n" + 
					"fvm.unit_name as corp_unit,\r\n" + 
					"div.unit_name as div_unit,\r\n" + 
					"bde.unit_name as bde_unit,\r\n" + 
					"u.unit_name,\r\n" + 
					"u.form_code_control,\r\n" + 
					"c.unit_sus_no,\r\n" + 
					"cl.id ,\r\n" + 
					"c.cadet_no,\r\n" + 
					"c.personnel_no,\r\n" + 
					"r.description as rank,\r\n" + 
					"c.name,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
					"arm.arm_desc as parent_arm,\r\n" + 
					"cl.status,\r\n" + 
					"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date,c.status as comm_status\r\n" + 
					"FROM tb_psg_census_detail_p cl \r\n" + 
					"inner join tb_psg_trans_proposed_comm_letter c on cl.comm_id =c.id  \r\n" + 
					"inner join cue_tb_psg_rank_app_master r on r.id = c.rank and r.status_active = 'Active' \r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.parent_arm\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.sus_no =c.unit_sus_no and u.status_sus_no='Active'\r\n" + 
					"left join tb_psg_mstr_course mc on mc.id=c.id \r\n" + 
					"left join all_fmn_view fv  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					"left join all_fmn_view div  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"left join all_fmn_view bde  on u.sus_no = c.unit_sus_no  and u.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'			\r\n" + 
					"and cl.id !=  0 and cl.status = '1' and c.status in ('1','5')) app " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no);
			System.err.println("query for service record: \n" + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String f = "";
				String View1 = "onclick=\"  {View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"','"+rs.getString("unit_sus_no")+"','"+rs.getInt("comm_status")+"')}\"";
                f = "<i class='fa fa-eye'" + View1 + " title='View Data'></i>";
               
			    columns.put("action", f);
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

public long getRecord_ServiceTotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(app1.*) from (select app.cmd_unit,app.corp_unit," +
					"app.div_unit,app.bde_unit,app.unit_name,app.unit_sus_no," +
					"app.personnel_no,app.cadet_no,app.form_code_control,app.rank,app.name\r\n" + 
					"from(select distinct \r\n" + 
					"fv.unit_name as cmd_unit,\r\n" + 
					"fvm.unit_name as corp_unit,\r\n" + 
					"div.unit_name as div_unit,\r\n" + 
					"bde.unit_name as bde_unit,\r\n" + 
					"u.unit_name,\r\n" + 
					"u.form_code_control,\r\n" + 
					"c.unit_sus_no,\r\n" + 
					"cl.id ,\r\n" + 
					"c.cadet_no,\r\n" + 
					"c.personnel_no,\r\n" + 
					"r.description as rank,\r\n" + 
					"c.name,\r\n" + 
					"ltrim(TO_CHAR(c.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
					"arm.arm_desc as parent_arm,\r\n" + 
					"cl.status,\r\n" + 
					"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date,c.status as comm_status\r\n" + 
					"FROM tb_psg_census_detail_p cl \r\n" + 
					"inner join tb_psg_trans_proposed_comm_letter c on cl.comm_id =c.id  \r\n" + 
					"inner join cue_tb_psg_rank_app_master r on r.id = c.rank and r.status_active = 'Active' \r\n" + 
					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.parent_arm\r\n" + 
					"inner join tb_miso_orbat_unt_dtl u on u.sus_no =c.unit_sus_no and u.status_sus_no='Active'\r\n" + 
					"left join tb_psg_mstr_course mc on mc.id=c.id \r\n" + 
					"left join all_fmn_view fv  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					"left join all_fmn_view fvm  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					"left join all_fmn_view div  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					"left join all_fmn_view bde  on u.sus_no = c.unit_sus_no  and u.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'			\r\n" + 
					"and cl.id !=  0 and cl.status = '1' and c.status in ('1','5')) app " + SearchValue +") app1" ;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,personnel_no);
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

public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no) {
		String SearchValue ="";
		if(!Search.equals("")) {
		Search = Search.toLowerCase();
			SearchValue =" where ( ";
			SearchValue +="lower(app.unit_sus_no) like ? or lower(app.unit_name) like ?  or lower(app.personnel_no) like ? or"
					+ " lower(app.cmd_unit) like ? or lower(app.corp_unit) like ?  or lower(app.div_unit) like ? "
					+ " or lower(app.bde_unit) like ? or lower(app.cadet_no) like ? "
					+ "or lower(app.name) like ? or lower(app.rank) like ?)";
		}
		
		
		if(!sus_no.equals("")){
			if (SearchValue.contains("where")) {
				SearchValue +=" and lower(app.unit_sus_no) like ?";
		} else {
			SearchValue += " where lower(app.unit_sus_no) like ? ";
		}
			
	}
	if(!unit_name.equals("0") && !unit_name.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(app.unit_name) like ? ";
		} else {
			SearchValue += " where lower(app.unit_name) like ? ";
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
	if(!personnel_no.equals("")){
		if (SearchValue.contains("where")) {
			SearchValue +=" and lower(app.personnel_no) like ? ";
		} else {
			SearchValue += " where lower(app.personnel_no) like ? ";
		}
	}
return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String personnel_no) {
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
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
		}	
		
		if(!sus_no.equals("")) {
			flag += 1;
			stmt.setString(flag, sus_no.toLowerCase()+"%");
		}
		if(!unit_name.equals("")) {
			flag += 1;
			stmt.setString(flag, unit_name.toLowerCase()+"%");
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
		if(!personnel_no.equals("")) {
			flag += 1;
			stmt.setString(flag, personnel_no.toLowerCase()+"%");
		}
						
		}catch (Exception e) {}
		return stmt;
	}

public ArrayList<ArrayList<String>> Excel_Record_Service_Report()
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		
		q="select distinct \r\n" + 
				"fv.unit_name as cmd_unit,\r\n" + 
				"fvm.unit_name as corp_unit,\r\n" + 
				"div.unit_name as div_unit,\r\n" + 
				"bde.unit_name as bde_unit,\r\n" + 
				"u.unit_name,\r\n" + 
				"u.form_code_control,\r\n" + 
				"c.unit_sus_no,\r\n" + 
				"cl.id ,\r\n" + 
				"c.cadet_no,\r\n" + 
				"c.personnel_no,\r\n" + 
				"r.description as rank,\r\n" + 
				"c.name,\r\n" + 
				"ltrim(TO_CHAR(c.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
				"arm.arm_desc as parent_arm,\r\n" + 
				"cl.status,\r\n" + 
				"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date,c.status as comm_status\r\n" + 
				"FROM tb_psg_census_detail_p cl \r\n" + 
				"inner join tb_psg_trans_proposed_comm_letter c on cl.comm_id =c.id  \r\n" + 
				"inner join cue_tb_psg_rank_app_master r on r.id = c.rank and r.status_active = 'Active' \r\n" + 
				"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = c.parent_arm\r\n" + 
				"inner join tb_miso_orbat_unt_dtl u on u.sus_no =c.unit_sus_no and u.status_sus_no='Active'\r\n" + 
				"left join tb_psg_mstr_course mc on mc.id=c.id \r\n" + 
				"left join all_fmn_view fv  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
				"left join all_fmn_view fvm  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
				"left join all_fmn_view div  on u.sus_no = c.unit_sus_no  and SUBSTRING(u.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
				"left join all_fmn_view bde  on u.sus_no = c.unit_sus_no  and u.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'			\r\n" + 
				"and cl.id !=  0 and cl.status = '1' and c.status in ('1','5') " 				
				 + SearchValue ;
		
			stmt=conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("cmd_unit"));//1
				list.add(rs.getString("corp_unit"));//2
				list.add(rs.getString("div_unit"));//3
				list.add(rs.getString("bde_unit"));//4
				list.add(rs.getString("unit_name"));//5
//				list.add(rs.getString("unit_sus_no"));//6
				list.add(rs.getString("cadet_no"));//7
				list.add(rs.getString("personnel_no"));//8
				list.add(rs.getString("rank"));//9
				list.add(rs.getString("name"));//10
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
