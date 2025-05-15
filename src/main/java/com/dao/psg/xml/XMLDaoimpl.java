package com.dao.psg.xml;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.model.psg.xml.XML_FILE_UPLOAD;

public class XMLDaoimpl implements XMLDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public long getUploadedFiles_count(int status,String army, String name,String frm_dt1,String to_dt1,String Search, String orderColunm, String orderType,String present_p2_no,String selected_id,String unit_no,String unit_name, HttpSession sessionUserId) {
	
		
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
String SerachValue="";

if(!Search.equals(""))
{
	
	SerachValue+=" and ( upper(file_name) like ? or  upper(army_no) like ?  or upper(name) like ? or upper(uploaded_status) like ? or upper(error_msg) like ?    or upper(present_p2_no) like ?  or upper(sus_no) like ?) ";
	
	
}
if(army!="")
{
		SerachValue += " and upper(army_no) like ? ";
}
if(!name.equals(""))
{
		SerachValue += " and upper(name) like ? ";
}
if(!present_p2_no.equals(""))
{
		SerachValue += " and upper(present_p2_no) like  ? ";
}

if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY") && !to_dt1.equals("DD/MM/YYYY")){ 

		  SerachValue +=  " and cast(uploaded_on as date) >= cast(? as date) and cast(uploaded_on as date) <= cast(? as date)"; 

}

if(!unit_no.equals("")&&!unit_name.equals("") )
{
	SerachValue += " and sus_no like  ? ";
}

String selected="";
if(selected_id!="")
	{
	selected=" and id in ( "+selected_id+" )";
	}

			q = "select count(app.*) from(select  file_name,army_no,name,uploaded_status,sus_no,error_msg,ltrim(TO_CHAR(uploaded_on ,'DD-Mon-YYYY'),'0')  as uploaded_on,present_p2_no from xml_files_upload_new where status=?  "+SerachValue +selected+") app ";

			PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, status);
				
			int flag=1;
			
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}

			if (!army.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + army.toUpperCase() + "%");
			}

			if (!name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + name.toUpperCase() + "%");
			}
			if(!present_p2_no.equals(""))
			{
				flag += 1;
				stmt.setString(flag, "%" + present_p2_no.toUpperCase() + "%");
			}
			 if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY")  && !to_dt1.equals("DD/MM/YYYY")) { 
				  flag += 1; 	
				   stmt.setString(flag , frm_dt1);
				   flag += 1;	
	                stmt.setString(flag, to_dt1);
	                
			  }
				if(!unit_no.equals("")&&!unit_name.equals("") )
				{
					  flag += 1; 	
					   stmt.setString(flag ,unit_no.substring(0, unit_no.length() - 1)+"%");
				}
		System.out.println("--+----"+stmt);
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
	@Override
	public List<Map<String, Object>> getUploadedFiles(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, String name, String army,String frm_dt1,String to_dt1,int status ,String present_p2_no,String selected_id,String unit_no,String unit_name) {
		
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String SerachValue="";

if(!Search.equals(""))
{
	
	SerachValue+=" and ( upper(file_name) like ? or  upper(army_no) like ?  or upper(name) like ? or upper(uploaded_status) like ? or upper(error_msg) like ?    or upper(present_p2_no) like ?  or upper(sus_no) like ?) ";
	
	
}
if(army!="")
{
		SerachValue += " and upper(army_no) like ? ";
}
if(!name.equals(""))
{
		SerachValue += " and upper(name) like ? ";
}
if(!present_p2_no.equals(""))
{
		SerachValue += " and upper(present_p2_no) like ? ";
}



			
			  if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY") && !to_dt1.equals("DD/MM/YYYY")){ 
				 
					  SerachValue +=  " and cast(uploaded_on as date) >= cast(? as date) and cast(uploaded_on as date) <= cast(? as date)"; 
			
//				else { SerachValue += " where cast(uploaded_on as date) >= cast(? as date) and cast(uploaded_on as date) <= cast(? as date)"; 
//				}
			  
			  }
			  if(!unit_no.equals("")&&!unit_name.equals("") )
				{
					SerachValue += " and sus_no like ?  ";
					
				}
			String selected="";
			if(selected_id!="")
				{
				selected=" and id in ( "+selected_id+" )";
				}
			
			
			q = "select  id,file_name,army_no,name,uploaded_status,error_msg,ltrim(TO_CHAR(uploaded_on ,'DD-Mon-YYYY'),'0')  as uploaded_on  , sus_no,comm_id, status ,present_p2_no,ltrim(TO_CHAR(modified_date ,'DD-MM-YYYY'),'0')  as modified_date "
					+ "from xml_files_upload_new where status=?   "+SerachValue +selected
				+"     order by id " + " desc  limit " + pageL + " OFFSET " + startPage + "";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, status);
int flag=1;
if(!Search.isEmpty()||!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}

			if (!army.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + army.toUpperCase() + "%");
			}

			if (!name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + name.toUpperCase() + "%");
			}
			
			if(!present_p2_no.equals(""))
			{
				flag += 1;
				stmt.setString(flag, "%" + present_p2_no.toUpperCase() + "%");
			}
			
			
			 if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY")  && !to_dt1.equals("DD/MM/YYYY")) { 
				  flag += 1; 	
				   stmt.setString(flag , frm_dt1);
				   flag += 1;	
	                stmt.setString(flag, to_dt1);
	                
			  }
				if(!unit_no.equals("")&&!unit_name.equals("") )
				{
					  flag += 1; 	
					  stmt.setString(flag ,unit_no.substring(0, unit_no.length() - 1)+"%");
				}
			
			
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			while (rs.next()) {
				

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				  BigInteger commId = rs.getBigDecimal("comm_id").toBigInteger();
					 String personnel_no = rs.getString("army_no");
					 String modified_date=rs.getString("modified_date");
					 String id_upload=rs.getString("id");
					 String army_no=rs.getString("army_no");
				   if ("0".equals(rs.getString("status"))) {
//					   BigInteger commId = rs.getBigDecimal("comm_id").toBigInteger();
//					 String personnel_no=rs.getString("army_no");
//					    String action = "<i class='action_icons action_approve' onclick='if (confirm(\"Are You Sure you want to Update/Approve?\")) { ViewData(" + commId +",'"+personnel_no+ "'); }' title='Update/Approve Data'></i>";

					// String action ="<i class='action_icons action_update' onclick='if (confirm(\"Are You Sure you want to Update?\")) { editData(" + commId + ",\"" + personnel_no + "\"); }' title='Update Data'></i>";
//					 action+=  "<i class='action_icons action_approve' onclick='if (confirm(\"Are You Sure you want to Approve?\")) { ViewData(" + commId + ",\"" + personnel_no + "\"); }' title='Approve Data'></i>";
					   String action ="<i class='action_icons action_update' onclick='edit_new_Data(" + commId + ",\"" + id_upload + "\"); ' title='View Data'></i>"; 
					   columns.put("action", action);
				    }
				   
				   if ("1".equals(rs.getString("status"))) {
						 String action ="<i class='fa fa-eye' onclick=' AppViewData("+ commId +       ",\""+modified_date+"\",\""+army_no +"\" ) ' title='View Data'></i>";
						 
						    columns.put("action", action);
					   
					   
				   }
				   
				   
				   

				    for (int i = 1; i <= columnCount; i++) {
				        columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				    }

				    list.add(columns);
//					if((rs.getObject("status").equals("0") ))
//					{
////						columns.put("action", '<i class="action_icons action_approve" onclick="  if (confirm('Are You Sure you want to Approve ?') )
////								{InApproved( '0418035M' )}else{ return false;}" title="Approve Data"></i>');
//						columns.put("action", "<i class='action_icons action_update ' onclick='if (confirm(\'Are You Sure you want to Update/Approve?\')) { InApproved(\'"+rs.getObject("comm_id")+"\'); } else { return false; }' title='Update/Approve Data'></i>");
//
//					}
//			
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));			
//				}
//
//			
//				list.add(columns);
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

	public ArrayList<ArrayList<String>> getShapeData_xml(String shape, BigInteger comm_id, int status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select authority,date_of_authority,clasification,id,shape_status,shape_value,from_date,to_date,diagnosis,shape_sub_value,other,from_date_1bx,to_date_1bx,diagnosis_1bx,\r\n"
					+ "(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis) as des_1,(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis_1bx) as bxdes_1,reject_remarks \r\n"
					+ "from tb_psg_medical_category where shape=?  and comm_id=? and status=? order by id";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, shape);
			stmt.setObject(2, comm_id);
			stmt.setInt(3, status);
		
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("shape_status")); // 0
				list.add(rs.getString("shape_value")); // 1
				list.add(rs.getString("from_date")); // 2
				list.add(rs.getString("to_date")); // 3
				if (rs.getString("des_1") != null)
					list.add(rs.getString("diagnosis") + "-" + rs.getString("des_1")); // 4
				else
					list.add("");

				list.add(rs.getString("id")); // 10 -> 5
				list.add(rs.getString("authority")); // 11 -> 6
				list.add(rs.getString("date_of_authority")); // 12 -> 7
				list.add(rs.getString("clasification")); // 13 -> 8
				list.add(rs.getString("shape_sub_value")); // 9
				list.add(rs.getString("other")); // 10
				list.add(rs.getString("from_date_1bx")); // 11
				list.add(rs.getString("to_date_1bx")); // 12

				if (rs.getString("bxdes_1") != null)
					list.add(rs.getString("diagnosis_1bx") + "-" + rs.getString("bxdes_1")); // 13
				else
					list.add("");
				list.add(rs.getString("reject_remarks")); // 14
				alist.add(list);
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
	@Override
	public List<Map<String, String>> update_award_medal_history_xml(BigInteger comm_id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			
			q = "select am.id,td.award_cat as category_8 ,mm.medal_name,am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\n" + 
					"ltrim(TO_CHAR(am.date_of_award  ,'DD-MON-YYYY'),'0') AS date_of_award,"
					+ "am.created_by,\n"
	        		+ "ltrim(TO_CHAR(am.created_on  ,'DD-MON-YYYY'),'0') AS created_date,\n"
					+ "am.authority,ltrim(TO_CHAR(am.date_of_authority  ,'DD-MON-YYYY'),'0') AS date_of_authority,"
					+ "am.modify_by,\n"
	        		+ "ltrim(TO_CHAR(am.modify_on  ,'DD-MON-YYYY'),'0') AS modify_on\n"
					+ ",am.census_id,am.status from tb_psg_census_awardsnmedal am\n" + 
					"inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n" + 
					"inner join orbat_view_corps co on co.sus_no=am.corps_area\n" + 
					"inner join orbat_view_div di on di.sus_no=am.div_subarea\n" + 
					"inner join orbat_view_bde bd on bd.sus_no=am.bde\n" + 
					"inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n" + 
					"inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n" + 
					 "where  cast(comm_id as character varying)=? and am.status in (1,2) order by am.id";   
			
//	        q = "select am.id,td.award_cat as category_8 ,mm.medal_name,\n"
//	        		+ "am.unit,cm.cmd_name,co.coprs_name,di.div_name,bd.bde_name,\n"
//	        		+ "am.date_of_award,am.authority,\n"
//	        		+ "ltrim(TO_CHAR(am.date_of_authority  ,'DD-MON-YYYY'),'0') AS date_of_authority,\n"
//	        		+ "am.created_by,\n"
//	        		+ "ltrim(TO_CHAR(am.created_on  ,'DD-MON-YYYY'),'0') AS created_date,\n"
//	        		+ "am.modify_by,\n"
//	        		+ "ltrim(TO_CHAR(am.modify_on  ,'DD-MON-YYYY'),'0') AS modify_on\n"
//	        		+ "from tb_psg_census_awardsnmedal am\n"
//	        		+ "inner join  orbat_view_cmd cm on cm.sus_no=am.command  \n"
//	        		+ "inner join orbat_view_corps co on co.sus_no=am.corps_area\n"
//	        		+ "inner join orbat_view_div di on di.sus_no=am.div_subarea\n"
//	        		+ "inner join orbat_view_bde bd on bd.sus_no=am.bde\n"
//	        		+ "inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\n"
//	        		+ "inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer)\n"
//	        		+ "where am.census_id=? and comm_id=? and am.status in (1,2)";   
	        
		    stmt=conn.prepareStatement(q);   	
		    stmt.setString(1, String.valueOf(comm_id));
//		    stmt.setInt(1, census_id);
		    ResultSet rs = stmt.executeQuery();    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getString(i));
		 	    }
		 	    list.add(columns);
		 	  
		 	 }
			 rs.close();
			 stmt.close();
			 conn.close();
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}

	
	@Override
	public ArrayList<ArrayList<String>> change_nok_history_dtl_xml(BigInteger comm_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
		
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
			
							
					//q="select * from tb_psg_census_nok where (status='1' or status='2') and comm_id='281' and census_id = '397' ";
				q="SELECT DISTINCT nok.id, nok.authority, ltrim(TO_CHAR(nok.date_authority  ,'DD-MON-YYYY'),'0') as date_authority, nok.created_by, \n"
						+ " ltrim(TO_CHAR(nok.created_date,'DD-MON-YYYY'),'0') AS created_date, \n"
						+ "	nok.modified_by, \n"
						+ "ltrim(TO_CHAR(nok.modified_date,'DD-MON-YYYY'),'0') as modified_date, \n"
						+ "PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no, nok.nok_name, nok.nok_near_railway_station, nok.nok_pin, nok.nok_rural_urban_semi, \n"
						+ " nok.nok_village, \n"
						+ "case when c.name = 'OTHERS' then nok.ctry_other\n"
						+ "else c.name end as Country,\n"
						+ "case when s.state_name = 'OTHERS' then nok.st_other\n"
						+ "else s.state_name end as State,\n"
						+ "case when d.district_name = 'OTHERS' then nok.dist_other\n"
						+ "else  d.district_name  end as District,\n"
						+ "case when city.city_name = 'OTHERS' then nok.tehsil_other\n"
						+ "else  city.city_name  end as Tehsil,\n"
						+ "case when r.relation_name = 'OTHERS' then nok.relation_other\n"
						+ "else  r.relation_name  end as Relation\n"
						+ " \n"
						+ "FROM public.tb_psg_census_nok nok\n"
						+ "INNER JOIN tb_psg_mstr_country c ON nok.nok_country = c.id\n"
						+ "INNER JOIN tb_psg_mstr_state s ON nok.nok_state = s.state_id\n"
						+ "INNER JOIN tb_psg_mstr_district d ON nok.nok_district = d.district_id\n"
						+ "INNER JOIN tb_psg_mstr_city city ON cast(nok.nok_tehsil  as integer)= city.city_id \n"
						+ "INNER JOIN tb_psg_mstr_relation r ON nok.nok_relation= r.id\n"
						+ "WHERE (nok.status='1' or nok.status='2')\n"
						+ "\n"
						+ "and cast(comm_id as character varying)=?   order by nok.id \n";
				
			
					
				
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
//				stmt.setInt(2,census_id);
				
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//0
					list.add(rs.getString("date_authority"));//1
					list.add(rs.getString("nok_name"));//2
					list.add(rs.getString("nok_mobile_no"));//3
					list.add(rs.getString("Relation"));//4
					list.add(rs.getString("nok_village"));//5
					list.add(rs.getString("State"));//6
					list.add(rs.getString("District"));//7
					list.add(rs.getString("Tehsil"));//8
					list.add(rs.getString("nok_pin"));//9
					list.add(rs.getString("Country"));//10
					list.add(rs.getString("nok_near_railway_station"));//11
					list.add(rs.getString("nok_rural_urban_semi"));//12
					list.add(rs.getString("created_by"));//13
					list.add(rs.getString("created_date"));//14
					list.add(rs.getString("modified_by"));//15
					list.add(rs.getString("modified_date"));//16

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
					} catch (SQLException e) {}
				}
			}
			
		return alist;
	}
	

	public ArrayList<ArrayList<String>> update_child_dtl_XML(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			q=" select ch.id,ch.type,ch.name,PGP_SYM_DECRYPT(ch.aadhar_no ::bytea,current_setting('miso.version'))  as aadhar_no,ch.adoted,\n" + 
					" ltrim(TO_CHAR(ch.date_of_adpoted,'dd-MON-yyyy'),'0')  as date_of_adpoted,\n" + 
					" ltrim(TO_CHAR(ch.date_of_birth,'dd-MON-yyyy'),'0') as date_of_birth,ch.modified_by,\n" + 
					" ltrim(TO_CHAR(ch.modified_date,'dd-MON-yyyy'),'0') as modified_date,ch.created_by,\n" + 
					"  ltrim(TO_CHAR(ch.created_date,'dd-MON-yyyy'),'0') as created_date,\n" + 
					" PGP_SYM_DECRYPT(ch.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td.label as relationship \n" + 
					" from tb_psg_census_children ch\n" + 
					"				inner join  t_domain_value td on td.codevalue=cast(ch.relationship as char) and td.domainid='CHILD_RELATIONSHIP'\n" + 
					"				where  cast(ch.comm_id as character varying) = ? and ch.status in (1,2) order by ch.id";
			
				
				stmt=conn.prepareStatement(q);
	
//				stmt.setInt(1,census_id);			
				stmt.setString(1,String.valueOf(comm_id));
				ResultSet rs = stmt.executeQuery();    
			
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));
					list.add(rs.getString("date_of_birth"));
					list.add(rs.getString("relationship"));
					list.add(rs.getString("adoted"));
					list.add(rs.getString("date_of_adpoted"));
					list.add(rs.getString("aadhar_no"));
					list.add(rs.getString("pan_no"));
					list.add(rs.getString("created_by"));
					list.add(rs.getString("created_date"));
					list.add(rs.getString("modified_by"));
					list.add(rs.getString("modified_date"));
					
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
	@Override
	public ArrayList<ArrayList<String>> change_Contact_xml(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
//			q="SELECT id, census_id, created_by, to_char(created_date,'DD/MON/YYYY') as created_date, PGP_SYM_DECRYPT(gmail ::bytea,current_setting('miso.version'))  as gmail, PGP_SYM_DECRYPT(mobile_no ::bytea,current_setting('miso.version'))  as mobile_no, modified_by, to_char(modified_date,'DD/MON/YYYY') as modified_date, PGP_SYM_DECRYPT(nic_mail ::bytea,current_setting('miso.version'))  as nic_mail, status, comm_id \n" +
//					"FROM public.tb_psg_census_contact_cda_account_details\n" +
//					" WHERE status in (1,2)  AND cast(comm_id as character varying)=? AND census_id = ? order by id";
			
			q="SELECT id, created_by, to_char(created_date,'DD/MON/YYYY') as created_date, PGP_SYM_DECRYPT(gmail ::bytea,current_setting('miso.version'))  as gmail,  CASE"
					+" WHEN PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version')) = '0' THEN '' "
					+ "        ELSE PGP_SYM_DECRYPT(mobile_no::bytea, current_setting('miso.version'))"
					+ "  END as mobile_no , modified_by, to_char(modified_date,'DD/MON/YYYY') as modified_date, PGP_SYM_DECRYPT(nic_mail ::bytea,current_setting('miso.version'))  as nic_mail, status, comm_id \n" +
					"FROM public.tb_psg_census_contact_cda_account_details\n" +
					" WHERE status in (1,2)  AND cast(comm_id as character varying)=?  order by id";
			
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
//				stmt.setInt(2,census_id);
				
				
				ResultSet rs = stmt.executeQuery(); 

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					//list.add(rs.getString("id"));//0
					list.add(rs.getString("gmail"));//1
					list.add(rs.getString("nic_mail"));//2
					list.add(rs.getString("mobile_no"));//3
					
					list.add(rs.getString("created_by"));//5
					list.add(rs.getString("created_date"));//6
					list.add(rs.getString("modified_by"));//7
					list.add(rs.getString("modified_date"));//8
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
	public ArrayList<ArrayList<String>> GetServingStatus_xml(BigInteger comm_id)

	{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		

	Connection conn = null;

	String q="";

	

	try{	  

		

			conn = dataSource.getConnection();			 

			PreparedStatement stmt=null;

			

				q="select c.id,c.personnel_no,"

						+ " case "+

						" when c.id in (select id from tb_psg_trans_proposed_comm_letter where status='5') then 'DESERTER' " + 

						" when c.id in (select id from tb_psg_trans_proposed_comm_letter where status='4') then 'NON EFFECTIVE' " +

						" when c.id in (select comm_id from tb_psg_re_employment where re_emp_select ='2' and status=1) then 'RE-EMPLOYED' \r\n" + 

						" when c.id in (select comm_id from tb_psg_re_employment where re_emp_select ='1' and status=1) then 'RE-CALL FROM RESERVE' \r\n" + 						

						" else 'SERVING' END\r\n" + 

						" from \r\n" + 

						" tb_psg_trans_proposed_comm_letter c \r\n" + 

						" where  cast(c.id  as character varying)=?" ;


			stmt=conn.prepareStatement(q);

			stmt.setString(1,String.valueOf(comm_id));

	      ResultSet rs = stmt.executeQuery();   

	    

	      ResultSetMetaData metaData = rs.getMetaData();

	      int columnCount = metaData.getColumnCount();

	      

	      while (rs.next()) {

	    	  ArrayList<String> list = new ArrayList<String>();

	    	  	list.add(rs.getString("id"));

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("case"));				

				

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
	public ArrayList<ArrayList<String>> update_non_effective_status_details_xml(BigInteger comm_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{  	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			  
			 q="SELECT ne.id, ne.non_ef_authority,ltrim(TO_CHAR(ne.date_of_authority_non_ef,'DD-MON-YYYY'),'0') as date_of_authority,td.causes_name as cause_of_non_effective, ltrim(TO_CHAR(ne.date_of_non_effective,'DD-MON-YYYY'),'0') as date_of_non_effective, ne.modified_by,ltrim(TO_CHAR(ne.modified_date,'DD-MON-YYYY'),'0') as modified_date,ne.created_by,ltrim(TO_CHAR(ne.created_date,'DD-MON-YYYY'),'0') as created_date  FROM tb_psg_non_effective ne \r\n" + 
			    		"inner join tb_psg_mstr_cause_of_non_effective td on ne.cause_of_non_effective=td.id::text " + 
			    		 "where   ne.status in (1,2) and cast(ne.comm_id as character varying)=?  order by ne.id";
			   
			stmt=conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));

			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("non_ef_authority"));//1
				list.add(rs.getString("date_of_authority"));//2
				list.add(rs.getString("cause_of_non_effective"));//3
				list.add(rs.getString("date_of_non_effective"));//4
				list.add(rs.getString("created_by"));//5
				list.add(rs.getString("created_date"));//6
				list.add(rs.getString("modified_by"));//7
				list.add(rs.getString("modified_date"));//8
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
	}


	public ArrayList<ArrayList<String>> GetCensusDataApprove_xml(BigInteger comm_id)

	{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		

	Connection conn = null;

	String q="";

	

	try{	  

		

			conn = dataSource.getConnection();			 

			PreparedStatement stmt=null;

			//Updated on 23-06-2022 for non-effective data

				

			

			q = "select  distinct\r\n" + 

					"c.id,\r\n" + 

					"cd.id as census_id,\r\n" + 

					"cd.marital_status as marital_status,\r\n" + 

					"c.name,\r\n" + 

					"cd.father_name,\r\n" +

					"cd.father_dob,\r\n" +

					"cd.mother_name,\r\n" +

					"cd.mother_dob,\r\n" +

					"ra.description as rank,\r\n" + 

					"r.description as appt,\r\n" + 

					"a.date_of_appointment as date_appointment,\r\n" + 

					"(select post.dt_of_tos  from tb_psg_posting_in_out post where c.id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n" + 

					"(select post.unit_description  from tb_psg_posting_in_out post where c.id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n" + 

					"c.unit_sus_no,\r\n" + 

					"i.id_card_no,\r\n" + 

					"re.religion_name,\r\n" + 

					"p.arm_desc as parent_arm,\r\n" + 

					"c.date_of_birth,\r\n" + 

					"c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" + 

					"fv.unit_name as command,c.regiment,cd.update_ofr_status,c.type_of_comm_granted\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c \r\n" + 

					"left join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\r\n" + 

					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 

					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n" + 

					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  "
		
					+ " and r.status_active = 'Active'\r\n" + 

					"left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" + 

					"left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n" + 

					"left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" + 

					"left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? ";



				

			stmt=conn.prepareStatement(q);

			

			stmt.setString(1,String.valueOf(comm_id));

			

	      ResultSet rs = stmt.executeQuery();   

	     

	      ResultSetMetaData metaData = rs.getMetaData();

	      int columnCount = metaData.getColumnCount();

	      

	      while (rs.next()) {

	    	  ArrayList<String> list = new ArrayList<String>();

	    	  	list.add(rs.getString("id"));

				list.add(rs.getString("census_id"));

				list.add(rs.getString("name"));				

				list.add(rs.getString("rank"));

				list.add(rs.getString("appt"));

				list.add(rs.getString("date_appointment"));

				list.add(rs.getString("dt_of_tos"));

				list.add(rs.getString("unit_sus_no"));

				

				list.add(rs.getString("id_card_no"));

				list.add(rs.getString("religion_name"));

				list.add(rs.getString("parent_arm"));

				list.add(rs.getString("date_of_birth"));//11

				list.add(rs.getString("date_of_commission"));

				list.add(rs.getString("marital_status"));//13

				list.add(rs.getString("rank_id"));//14

				list.add(rs.getString("appoint_id"));//15

				list.add(rs.getString("command"));//16

				list.add(rs.getString("regiment"));//17

				list.add(rs.getString("update_ofr_status"));//18

				list.add(rs.getString("father_name"));//19

				list.add(rs.getString("father_dob"));//20

				list.add(rs.getString("mother_name"));//21

				list.add(rs.getString("mother_dob"));//22

				list.add(rs.getString("post_unit_desc"));//23         
				
				list.add(rs.getString("type_of_comm_granted"));//24

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
	@Override
	public List<Map<String, Object>> getPost_in_data(BigInteger comm_id,int cat,int status) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (cat == 1) {

				q = "select p.id, b.personnel_no,b.name,r.description as rank, p.out_auth, p.comm_id,\r\n"
						+ "p.out_auth_dt,p.to_sus_no as unit_sus_no,m.unit_name, p.dt_of_tos,p.from_sus_no ,p.app_name,p.t_status,ta.label as t_status_lbl \r\n"
						+ " from tb_psg_posting_in_out p \r\n"
						+ "inner join tb_psg_trans_proposed_comm_letter b on p.comm_id=b.id \r\n"
						+ "inner join cue_tb_psg_rank_app_master r on r.id = b.rank \r\n"
						+ "inner join T_Domain_Value ta on ta.codevalue::int=p.t_status and domainid='TASTATUS'\r\n"
						+ "inner join tb_miso_orbat_unt_dtl m on m.sus_no = p.to_sus_no and m.status_sus_no='Active'  where p.comm_id = ? and p.status="+status;

			}

			else if (cat == 2)

			{

			

			}

			stmt = conn.prepareStatement(q);

			 stmt.setObject(1, comm_id);

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
	@Override
	public long getcasualitycount(int Status, String Search, String orderColunm, String orderType, String comm_id_casualty,
			String upload_id_casualty, HttpSession sessionUserId) {
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
String SerachValue="";
if(!Search.equals(""))
{
	
	SerachValue+=" and ( upper(casseqno) like ? or  upper(description) like ?  or upper(fmdate) like ? "
			+ " or upper(rmk1) like ? or upper(remark) like ? ) ";

}


			q = "select count(app.*) from(select  *  from xml_casualty_details where  xml_file_upload_id=?  "+SerachValue+") app ";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			//	stmt.setObject(1, new BigInteger(comm_id_casualty));
			stmt.setObject(1, Integer.parseInt(upload_id_casualty));
				
				
			int flag=1;
			
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

		
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
	@Override
	public List<Map<String, Object>> getcasualitydata(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, HttpSession sessionUserId, int status, String comm_id_casualty,
			String upload_id_casualty) {

		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String SerachValue="";

if(!Search.equals(""))
{
	
	SerachValue+=" and ( upper(casseqno) like ? or  upper(description) like ?  or upper(fmdate) like ? "
			+ " or upper(rmk1) like ? or upper(remark) like ? ) ";

}

			
			q = "select  *  from xml_casualty_details where  xml_file_upload_id=?  "+SerachValue;

			stmt = conn.prepareStatement(q);
			//stmt.setObject(1, new BigInteger(comm_id_casualty));
			stmt.setObject(1, Integer.parseInt(upload_id_casualty));

int flag=1;
if(!Search.isEmpty()||!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}

	
			

			
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			while (rs.next()) {
				
				 String casualty_no=rs.getString("casualty_no");
				 String xml_file_upload_id=rs.getString("xml_file_upload_id");
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				    for (int i = 1; i <= columnCount; i++) {
				        columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				        
				    }
				    String action ="<i class='action_icons action_delete' onclick=' delete_casualty("+ casualty_no +","+xml_file_upload_id+" ) '  style='width:10px; height:10px;'title='Delete Data'></i>";
					 
				    columns.put("action", action);

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
	@Override
	public String insertCasualty( NodeList casualtyNodes, int status_xml, String personnel_no,XML_FILE_UPLOAD xmlFile) {
		 String savedId="";
		
		String insertSql = "INSERT INTO xml_files_upload_new "
				+ "(file_name, army_no, error_msg, name, uploaded_status, uploaded_on, status, comm_id, present_p2_no,"
				+ " unit_name, sus_no, cda_account_no, census_id, present_p2_date,rank) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		 Connection conn=null;
		try{
			  conn = dataSource.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
		    stmt.setString(1, xmlFile.getFileName());
		    stmt.setString(2, xmlFile.getArmyNo());
		    stmt.setString(3, xmlFile.getErrorMsg());
		    stmt.setString(4, xmlFile.getName());
		    //stmt.setBlob(5, new ByteArrayInputStream(xmlFile.getFileData()));
		    stmt.setString(5, xmlFile.getUploadedStatus());
		    stmt.setTimestamp(6, xmlFile.getUploadedOn());
		    stmt.setInt(7, xmlFile.getStatus());
		    stmt.setObject(8, xmlFile.getComm_id());
		    stmt.setString(9, xmlFile.getPresent_p2_no());
		    stmt.setString(10, xmlFile.getUnit_name());
		    stmt.setString(11, xmlFile.getSus_no());
		    stmt.setString(12, xmlFile.getCda_account_no());
		    stmt.setInt(13, xmlFile.getCensus_id());
		    if (xmlFile.getPresent_p2_date() != null) {
		        stmt.setTimestamp(14, new Timestamp(xmlFile.getPresent_p2_date().getTime()));
		    } else {
		        stmt.setNull(14, Types.TIMESTAMP);
		    }
		    stmt.setString(15, xmlFile.getRank());

		    int rowsAffected = stmt.executeUpdate();
		    if (rowsAffected == 1) {
		        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                 savedId = String.valueOf(generatedKeys.getLong(1));		              
		            }
		        }
		    } 
		    
		    if(savedId!="")
		    {
		    			
		       for (int i = 0; i < casualtyNodes.getLength(); i++) {
	               Node casualtyNode = casualtyNodes.item(i);
	               if (casualtyNode.getNodeType() == Node.ELEMENT_NODE) {
	                   Map<String, String> dataMap = new HashMap<>();
	                   NodeList childNodes = casualtyNode.getChildNodes();
	                   StringBuilder sql = new StringBuilder("INSERT INTO XML_CASUALTY_DETAILS ( xml_file_upload_id,status,personnel_no,casualty_no,");
	                   StringBuilder sql2 = new StringBuilder("");
	                   sql2.append(savedId).append(",").append(status_xml).append(",'").append(personnel_no).append("',").append(i+1).append(",");
	                   for (int j = 0; j < childNodes.getLength(); j++) {
	                       Node childNode = childNodes.item(j);
	                       if (childNode.getNodeType() == Node.ELEMENT_NODE) {
	                           String tagName = childNode.getNodeName();
	                           String tagValue = childNode.getTextContent();
	                           tagValue = tagValue.replaceAll("'", " ");
	                           dataMap.put(tagName, tagValue);
	                           sql.append(tagName).append(", ");
	                           sql2.append("'").append(tagValue).append("', ");
	                           String alterQuery = "ALTER TABLE XML_CASUALTY_DETAILS ADD COLUMN " + tagName + " VARCHAR ";
	                           try {
	                   			stmt = conn.prepareStatement(alterQuery);
	                        	   stmt.executeUpdate();
	                           } catch (SQLException e) {	                                         
	                               continue;
	                           }                                                  
	                       }
	                   }                                     
	                   sql.delete(sql.length() - 2, sql.length()); 
	                   sql.append(") VALUES (");
	                   sql2.delete(sql2.length() - 2, sql2.length()); 
	                   sql2.append(")");
	                   System.out.println("-------"+sql.toString()+sql2.toString());
	               	stmt = conn.prepareStatement(sql.toString()+sql2.toString());
	                stmt.executeUpdate();   	                
	               }
	               }
		       stmt.close();
	           conn.close();
	           return savedId;
		    }  
		} catch (Exception e) {		 
		    e.printStackTrace();
		}
		 finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return savedId;
	}
	@Override
	public List<String> getcolumname() {

		List<String> list = new ArrayList<String>();
		list.add("Ser No");
		Connection conn = null;
		String q = "";

		try {
		
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String SerachValue="";
		
			q = "select  *  from xml_casualty_details  limit 1";

			stmt = conn.prepareStatement(q);
			
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			while (rs.next()) {			
				    for (int i = 1; i <= columnCount; i++) {
				      
				        if(metaData.getColumnLabel(i).equals("status")||metaData.getColumnLabel(i).equals("id")||metaData.getColumnLabel(i).equals("xml_file_upload_id"))
				        {
				        	continue;
				        }
				        list.add(metaData.getColumnLabel(i).toUpperCase());
				    }

				   
			}
			list.add("Action");
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
//	@Override
//	public String deletexml(String xml_file_upload_id, String casualty_no) {
//		String msg="";
//		
//		
//		try {
//			String hql = "delete from XML_CASUALTY_DETAILS" 
//					+ " where  xml_file_upload_id=:xml_file_upload_id and  casualty_no=:casualty_no";						       					
//		.setInteger("xml_file_upload_id",Integer.parseInt(xml_file_upload_id))
//					.setInteger("casualty_no",Integer.parseInt(casualty_no));						       					
//			msg = query.executeUpdate() > 0 ? "Delete Successfully" : " Delete  Unsuccessfull";
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		
//		return null;
//	}

}
