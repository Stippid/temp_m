package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class  Track_status_DAOImpl implements  Track_status_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



public ArrayList<ArrayList<String>> a_search_track_status(HttpSession session,String mode,String py){
		
		String whr = "where cin_id > 0 ";
		String orderby = "";
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		String join =" left join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop";
		if(roleAccess.equals("CIN")) {
			join=" inner join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop ";
			//whr += " and type_of_veh ='B' ";
			orderby = " order by c.cin_id ";
		}
		if(roleAccess.equals("Unit")) {
			whr += " and c.base_workshop ='"+roleSusNo+"' ";
		}
		if(roleAccess.equals("Line_dte")) {
			whr += " and c.sus_no ='"+roleSusNo+"' ";
		}
		if(!py.equals("")) {
			whr += " and c.py ='"+py+"' ";
		}
		String workshop=" wrk.unit_name as wkp,";

		if(mode.equals("1")) {
			workshop="c.base_workshop as wkp , ";
		}
		
		
		if(!mode.equals("")) {
			whr += " and c.mode ='"+mode+"' ";
		}
		
		
		
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q=	" select distinct c.cin_id,c.ba_no,c.type_of_veh,c.kms,c.vintage,to_char(c.date,'dd-mm-yyyy') as date,u.unit_name,to_char(c.dt_arrival,'dd-mm-yyyy') as dt_arrival,to_char(c.dt_arrival,'yyyy-mm-dd') as dt_arrival1,oh_status,\r\n"
					+ "  to_char(c.oh_date,'yyyy-mm-dd') as oh_date,c.status,c.remark,e.cmd_name,\r\n"
					+ "  corps.coprs_name as corps, div.div_name as div,bde.bde_name as bde, \r\n"
					+ "  u.unit_name, b.sus_no  as sus ,"
			+workshop
				
					+ "c.qr_status,	COALESCE(c.mode, '0') as mode "
					+ ",c.type_of_intervention  from tb_tms_cin c\r\n"
					+ "   JOIN tb_tms_census_retrn b ON c.ba_no::text = b.ba_no::text\r\n"
					+ join
					+ "  left join tb_miso_orbat_unt_dtl u on b.sus_no = u.sus_no and u.status_sus_no = 'Active'  and c.type_of_veh ='A'\r\n"
					+ "  left join orbat_view_cmd e on substr(u.form_code_control,1,1) = e.cmd_code \r\n"
					+ "  left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n"
					+ "  left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code "
					+whr+ "  and c.status='0'    and c.type_of_veh = 'A' \n" +
					orderby;
			stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			 int i = 0;
			while(rs.next()){
				ArrayList<String> list1 = new ArrayList<String>();
				i++;
			   
			    list1.add(rs.getString("cin_id")); //0
				list1.add(rs.getString("ba_no")); //1
				list1.add(rs.getString("type_of_veh")); //2
				list1.add(rs.getString("kms")); //3
				list1.add(rs.getString("vintage")); //4
				list1.add(rs.getString("date")); //5
				list1.add(rs.getString("unit_name")); //6
				String dt_arrival = rs.getString("dt_arrival");
				list1.add(dt_arrival); //7
				list1.add(rs.getString("cmd_name"));//8
				list1.add(rs.getString("corps"));//9
				list1.add(rs.getString("div"));//10
				list1.add(rs.getString("bde"));//11
				list1.add(rs.getString("sus"));//12
				list1.add(rs.getString("unit_name"));//13
				list1.add(rs.getString("wkp"));//14
				list1.add(rs.getString("qr_status"));//15
	
				String oh_status = rs.getString("oh_status");
				String oh_date = rs.getString("oh_date");
				String remark_p = rs.getString("remark");
				
				String typeOfIntervention = rs.getString("type_of_intervention");
				if (typeOfIntervention != null && !typeOfIntervention.isEmpty()) {
					list1.add(getValue_of_oh(rs.getString("type_of_intervention")));//16
				} else {
					list1.add(rs.getString("type_of_intervention"));//16
				}
			
				
				
				
				if(rs.getString("mode").equals("2")){
				if(!rs.getString("status").equals("2")){
					if(roleAccess.equals("Unit")||roleAccess.equals("CIN")){
						String dt_arrival_input = "";
						if(dt_arrival == null){
							dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
						}else {
							dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
						}
						list1.add(dt_arrival_input); //17
						String oh_status_input = "";
						
						if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
							oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
								"	<option value=\"No\">No</option>\r\n" + 
								"	<option value=\"Yes\">Yes</option>\r\n" + 
								"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
								"</select>";
						}else{
							oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
						}
						list1.add(oh_status_input); //18
						
						
						
						String oh_date_input = "";
						if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
						}else {
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
						}
						list1.add(oh_date_input); //19
						
						
						if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
							String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
							list1.add(remarks);//20
							String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
						list1.add(cin_lbl); //21
				 //19
						}
						else {
							
							list1.add(""); //20
							
							String submit_input = "";
							submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
							list1.add(submit_input); //21
						}
						list1.add(rs.getString("mode")); //22
						
					}
					if(roleAccess.equals("MISO")){
						list1.add(dt_arrival); //17
						list1.add(rs.getString("oh_status")); //18
						list1.add(rs.getString("oh_date")); //19
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
						list1.add(remarks); //20
						list1.add(""); //21
						list1.add(rs.getString("mode")); //22
						
					}
					if(roleAccess.equals("Line_dte")){
						list1.add(dt_arrival); //17
						list1.add(rs.getString("oh_status")); //18
						list1.add(rs.getString("oh_date")); //19
						String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelData("
								+ rs.getString("cin_id") + ","+i+")}else{ return false;}\"";
						String Cancel_t="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";
						
						String remarks = "<textarea  disabled id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
						
						  list1.add(remarks); //20
						
						
						  list1.add(Cancel_t); //21
						  list1.add(rs.getString("mode")); //22
					}
				}else {
					list1.add(dt_arrival); //17
					list1.add(rs.getString("oh_status")); //18
					list1.add(rs.getString("oh_date")); //19
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
					list1.add(remarks); //20
					
					list1.add(""); //21
					list1.add(rs.getString("mode")); //22
				}
				
				
				
				
				}
				else {

					if(!rs.getString("status").equals("2")){
						if(roleAccess.equals("Line_dte")){
							String dt_arrival_input = "";
							if(dt_arrival == null){
								dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
							}else {
								dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
							}
							list1.add(dt_arrival_input); //17
							String oh_status_input = "";
							
							if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
								oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
									"	<option value=\"No\">No</option>\r\n" + 
									"	<option value=\"Yes\">Yes</option>\r\n" + 
									"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
									"</select>";
							}else{
								oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
							}
							list1.add(oh_status_input); //18
							
							
							
							String oh_date_input = "";
							if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
								oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
							}else {
								oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
							}
							list1.add(oh_date_input); //19
							
							
							if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
								String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
								list1.add(remarks);//20
								String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
							list1.add(cin_lbl); //21
							
					 //19
							}
							else {
								
								list1.add(""); //20
								
								String submit_input = "";
								submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
								list1.add(submit_input); //21
								
							}
								
							
						}
						
						else {
							list1.add(dt_arrival); //17
							list1.add(rs.getString("oh_status")); //18
							list1.add(rs.getString("oh_date")); //19
							String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" disabled></textarea>";
							list1.add(remarks); //20
							
							list1.add(""); //21
							list1.add(rs.getString("mode")); //22
							
							
							
							
						}
						
	
					}else {
						list1.add(dt_arrival); //17
						list1.add(rs.getString("oh_status")); //18
						list1.add(rs.getString("oh_date")); //19
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
						list1.add(remarks); //20
						
						list1.add(""); //21
						list1.add(rs.getString("mode")); //22
					}

				}
				
				alist.add(list1);
				
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
	
	

	



public ArrayList<ArrayList<String>> b_search_track_status(HttpSession session,String mode,String py){
	
	String whr = "where cin_id > 0 ";
	String orderby = "";
	String roleAccess = session.getAttribute("roleAccess").toString();
	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String username = session.getAttribute("username").toString();
	
	String join =" left join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop";
	if(roleAccess.equals("CIN")) {
		join=" inner join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop ";
		//whr += " and type_of_veh ='B' ";
		orderby = " order by c.cin_id ";
	}
	if(roleAccess.equals("Unit")) {
		whr += " and c.base_workshop ='"+roleSusNo+"' ";
	}
	if(roleAccess.equals("Line_dte")) {
		whr += " and c.sus_no ='"+roleSusNo+"' ";
	}
	if(!py.equals("")) {
		whr += " and c.py ='"+py+"' ";
	}
	String workshop=" wrk.unit_name as wkp,";

	if(mode.equals("1")) {
		workshop="c.base_workshop as wkp,  ";
	}
	if(!mode.equals("")) {
		whr += " and c.mode ='"+mode+"' ";
	}
	
	
	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q=	" select distinct c.cin_id,c.ba_no,c.type_of_veh,c.kms,c.vintage,to_char(c.date,'dd-mm-yyyy') as date,u.unit_name,to_char(c.dt_arrival,'dd-mm-yyyy') as dt_arrival,to_char(c.dt_arrival,'yyyy-mm-dd') as dt_arrival1,oh_status,\r\n"
				+ "  to_char(c.oh_date,'yyyy-mm-dd') as oh_date,c.status,c.remark,e.cmd_name,\r\n"
				+ "  corps.coprs_name as corps, div.div_name as div,bde.bde_name as bde, \r\n"
				+ "  u.unit_name, b.sus_no  as sus ,"
				+workshop

				+ "c.qr_status,	COALESCE(c.mode, '0') as mode  from tb_tms_cin c\r\n"
				+ "   JOIN tb_tms_mvcr_parta_dtl b ON c.ba_no::text = b.ba_no::text\r\n"
				+ join
				+ "  left join tb_miso_orbat_unt_dtl u on b.sus_no = u.sus_no and u.status_sus_no = 'Active'  and c.type_of_veh ='B'\r\n"
				+ "  left join orbat_view_cmd e on substr(u.form_code_control,1,1) = e.cmd_code \r\n"
				+ "  left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n"
				+ "  left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code "
				+whr+ "  and c.status='0'   and c.type_of_veh = 'B' \n" +
				orderby;
		stmt = conn.prepareStatement(q);

		ResultSet rs = stmt.executeQuery();
		 int i = 0;
		while(rs.next()){
			ArrayList<String> list1 = new ArrayList<String>();
			i++;
		   
		    list1.add(rs.getString("cin_id")); //0
			list1.add(rs.getString("ba_no")); //1
			list1.add(rs.getString("type_of_veh")); //2
			list1.add(rs.getString("kms")); //3
			list1.add(rs.getString("vintage")); //4
			list1.add(rs.getString("date")); //5
			list1.add(rs.getString("unit_name")); //6
			String dt_arrival = rs.getString("dt_arrival");
			list1.add(dt_arrival); //7
			list1.add(rs.getString("cmd_name"));//8
			list1.add(rs.getString("corps"));//9
			list1.add(rs.getString("div"));//10
			list1.add(rs.getString("bde"));//11
			list1.add(rs.getString("sus"));//12
			list1.add(rs.getString("unit_name"));//13
			list1.add(rs.getString("wkp"));//14
			list1.add(rs.getString("qr_status"));//15
			
			String oh_status = rs.getString("oh_status");
			String oh_date = rs.getString("oh_date");
			String remark_p = rs.getString("remark");
			
			if(rs.getString("mode").equals("2")){
			if(!rs.getString("status").equals("2")){
				if(roleAccess.equals("Unit")||roleAccess.equals("CIN")){
					String dt_arrival_input = "";
					if(dt_arrival == null){
						dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
					}else {
						dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
					}
					list1.add(dt_arrival_input); //16
					String oh_status_input = "";
					
					if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
						oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
							"	<option value=\"No\">No</option>\r\n" + 
							"	<option value=\"Yes\">Yes</option>\r\n" + 
							"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
							"</select>";
					}else{
						oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
					}
					list1.add(oh_status_input); //17
					
					
					
					String oh_date_input = "";
					if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
						oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
					}else {
						oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
					}
					list1.add(oh_date_input); //18
					
					
					if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
						list1.add(remarks);//19
						String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
					list1.add(cin_lbl); //20
			 //19
					}
					else {
						
						list1.add(""); //19
						
						String submit_input = "";
						submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
						list1.add(submit_input); //20
					}
					list1.add(rs.getString("mode")); //21
					
				}
				if(roleAccess.equals("MISO")){
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
					list1.add(remarks); //19
					list1.add(""); //20
					list1.add(rs.getString("mode")); //21
					
				}
				if(roleAccess.equals("Line_dte")){
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelData("
							+ rs.getString("cin_id") + ","+i+")}else{ return false;}\"";
					String Cancel_t="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";
					
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
					
					  list1.add(remarks); //19
					
					
					  list1.add(Cancel_t); //20
					  list1.add(rs.getString("mode")); //21
				}
			}else {
				list1.add(dt_arrival); //16
				list1.add(rs.getString("oh_status")); //17
				list1.add(rs.getString("oh_date")); //18
				String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
				list1.add(remarks); //19
				
				list1.add(""); //20
				list1.add(rs.getString("mode")); //21
			}
			
			
			
			
			}
			else {

				if(!rs.getString("status").equals("2")){
					if(roleAccess.equals("Line_dte")){
						String dt_arrival_input = "";
						if(dt_arrival == null){
							dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
						}else {
							dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
						}
						list1.add(dt_arrival_input); //16
						String oh_status_input = "";
						
						if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
							oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
								"	<option value=\"No\">No</option>\r\n" + 
								"	<option value=\"Yes\">Yes</option>\r\n" + 
								"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
								"</select>";
						}else{
							oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
						}
						list1.add(oh_status_input); //17
						
						
						
						String oh_date_input = "";
						if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
						}else {
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
						}
						list1.add(oh_date_input); //18
						
						
						if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
							String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
							list1.add(remarks);//19
							String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
						list1.add(cin_lbl); //20
						
				 //19
						}
						else {
							
							list1.add(""); //19
							
							String submit_input = "";
							submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
							list1.add(submit_input); //20
							
						}
							
						
					}
					
					else {
						list1.add(dt_arrival); //16
						list1.add(rs.getString("oh_status")); //17
						list1.add(rs.getString("oh_date")); //18
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
						list1.add(remarks); //19
						
						list1.add(""); //20
						list1.add(rs.getString("mode")); //21
						
						
						
						
					}
					

				}else {
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
					list1.add(remarks); //19
					
					list1.add(""); //20
					list1.add(rs.getString("mode")); //21
				}
				
			}
			alist.add(list1);
			
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


public ArrayList<ArrayList<String>> c_search_track_status(HttpSession session,String mode,String py){
	
	String whr = "where cin_id > 0 ";
	String orderby = "";
	String roleAccess = session.getAttribute("roleAccess").toString();
	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String username = session.getAttribute("username").toString();
	
	String join =" left join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop";
	if(roleAccess.equals("CIN")) {
		join=" inner join tb_tms_base_workshop_master wrk on wrk.sus_no=c.base_workshop ";
		//whr += " and type_of_veh ='B' ";
		orderby = " order by c.cin_id ";
	}
	if(roleAccess.equals("Unit")) {
		whr += " and c.base_workshop ='"+roleSusNo+"' ";
	}
	if(roleAccess.equals("Line_dte")) {
		whr += " and c.sus_no ='"+roleSusNo+"' ";
	}
	if(!py.equals("")) {
		whr += " and c.py ='"+py+"' ";
	}
	String workshop=" wrk.unit_name as wkp,";

	if(mode.equals("1")) {
		workshop="c.base_workshop as wkp, ";
	}
	if(!mode.equals("")) {
		whr += " and c.mode ='"+mode+"' ";
	}
	
	
	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q=	" select distinct c.cin_id,c.ba_no,c.type_of_veh,c.kms,c.vintage,to_char(c.date,'dd-mm-yyyy') as date,u.unit_name,to_char(c.dt_arrival,'dd-mm-yyyy') as dt_arrival,to_char(c.dt_arrival,'yyyy-mm-dd') as dt_arrival1,oh_status,\r\n"
				+ "  to_char(c.oh_date,'yyyy-mm-dd') as oh_date,c.status,c.remark,e.cmd_name,\r\n"
				+ "  corps.coprs_name as corps, div.div_name as div,bde.bde_name as bde, \r\n"
				+ "  u.unit_name, b.sus_no  as sus ,"
				+workshop
				+ "c.qr_status,	COALESCE(c.mode, '0') as mode  from tb_tms_cin c\r\n"
				+ "   JOIN tb_tms_emar_report b ON c.ba_no::text = b.em_no::text\r\n"
				+ join
				+ "  left join tb_miso_orbat_unt_dtl u on b.sus_no = u.sus_no and u.status_sus_no = 'Active'  and c.type_of_veh ='C'\r\n"
				+ "  left join orbat_view_cmd e on substr(u.form_code_control,1,1) = e.cmd_code \r\n"
				+ "  left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code \r\n"
				+ "  left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code "
				+whr+ "   and c.status='0'   and  c.type_of_veh = 'C' \n" +
				orderby;
		stmt = conn.prepareStatement(q);
		
		ResultSet rs = stmt.executeQuery();
		 int i = 0;
		while(rs.next()){
			ArrayList<String> list1 = new ArrayList<String>();
			i++;
		   
		    list1.add(rs.getString("cin_id")); //0
			list1.add(rs.getString("ba_no")); //1
			list1.add(rs.getString("type_of_veh")); //2
			list1.add(rs.getString("kms")); //3
			list1.add(rs.getString("vintage")); //4
			list1.add(rs.getString("date")); //5
			list1.add(rs.getString("unit_name")); //6
			String dt_arrival = rs.getString("dt_arrival");
			list1.add(dt_arrival); //7
			list1.add(rs.getString("cmd_name"));//8
			list1.add(rs.getString("corps"));//9
			list1.add(rs.getString("div"));//10
			list1.add(rs.getString("bde"));//11
			list1.add(rs.getString("sus"));//12
			list1.add(rs.getString("unit_name"));//13
			list1.add(rs.getString("wkp"));//14
			list1.add(rs.getString("qr_status"));//15
	
			String oh_status = rs.getString("oh_status");//16
			String oh_date = rs.getString("oh_date");//	17
			String remark_p = rs.getString("remark");//18
			
			if(rs.getString("mode").equals("2")){
			if(!rs.getString("status").equals("2")){
				if(roleAccess.equals("Unit")||roleAccess.equals("CIN")){
					String dt_arrival_input = "";
					if(dt_arrival == null){
						dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
					}else {
						dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
					}
					list1.add(dt_arrival_input); //16
					String oh_status_input = "";
					
					if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
						oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
							"	<option value=\"No\">No</option>\r\n" + 
							"	<option value=\"Yes\">Yes</option>\r\n" + 
							"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
							"</select>";
					}else{
						oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
					}
					list1.add(oh_status_input); //17
					
					
					
					String oh_date_input = "";
					if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
						oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
					}else {
						oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
					}
					list1.add(oh_date_input); //18
					
					
					if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
						list1.add(remarks);//19
						String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
					list1.add(cin_lbl); //20
			 //19
					}
					else {
						
						list1.add(""); //19
						
						String submit_input = "";
						submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
						list1.add(submit_input); //20
					}
					list1.add(rs.getString("mode")); //21
					
				}
				if(roleAccess.equals("MISO")){
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
					list1.add(remarks); //19
					list1.add(""); //20
					list1.add(rs.getString("mode")); //21
					
				}
				if(roleAccess.equals("Line_dte")){
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String CancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelData("
							+ rs.getString("cin_id") + ","+i+")}else{ return false;}\"";
					String Cancel_t="<i class='action_icons action_reject' "+CancelData+" title='Cancel Data'></i></a>";
					
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
					
					  list1.add(remarks); //19
					
					
					  list1.add(Cancel_t); //20
					  list1.add(rs.getString("mode")); //21
				}
			}else {
				list1.add(dt_arrival); //16
				list1.add(rs.getString("oh_status")); //17
				list1.add(rs.getString("oh_date")); //18
				String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
				list1.add(remarks); //19
				
				list1.add(""); //20
				list1.add(rs.getString("mode")); //21
			}
			
			
			
			
			}
			else {

				if(!rs.getString("status").equals("2")){
					if(roleAccess.equals("Line_dte")){
						String dt_arrival_input = "";
						if(dt_arrival == null){
							dt_arrival_input = "<input type=\"date\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\"  onchange=\"dt_arival_change("+i+");\" >";
						}else {
							dt_arrival_input = "<input type=\"text\" id=\"dt_arrival"+i+"\" name=\"dt_arrival"+i+"\" class=\"form-control\" value="+rs.getString("dt_arrival1")+" readonly=\"readonly\"     onchange=\"dt_arival_change("+i+");\"   >";
						}
						list1.add(dt_arrival_input); //16
						String oh_status_input = "";
						
						if(oh_status == null || oh_status.equals("No") || oh_status.equals("No") || oh_status.equals("Under_Progress")){
							oh_status_input = "<select name=\"oh_status"+i+"\" id=\"oh_status"+i+"\" class=\"form-control\" onchange=\"oh_dt_st("+i+");\">\r\n" + 
								"	<option value=\"No\">No</option>\r\n" + 
								"	<option value=\"Yes\">Yes</option>\r\n" + 
								"	<option value=\"Under_Progress\">Under Progress</option>\r\n" + 
								"</select>";
						}else{
							oh_status_input = "<input type=\"text\" id=\"oh_status"+i+"\" name=\"oh_status"+i+"\" class=\"form-control\" value="+oh_status+" readonly=\"readonly\">";
						}
						list1.add(oh_status_input); //17
						
						
						
						String oh_date_input = "";
						if(oh_date == null || oh_date.equals("") || oh_date.equals("null")){
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" readonly=\"readonly\">";
						}else {
							oh_date_input = "<input type=\"date\" id=\"oh_date"+i+"\" name=\"oh_date"+i+"\" class=\"form-control\" value="+rs.getString("oh_date")+" readonly=\"readonly\">";
						}
						list1.add(oh_date_input); //18
						
						
						if(oh_status == null || oh_status.equals("Yes") || oh_status.equals("Yes") && oh_date != null && dt_arrival != null){
							String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\" readonly></textarea>";
							list1.add(remarks);//19
							String cin_lbl = "<label class=\" form-control-label\" id=\"lab_l_inp"+i+"\"><strong style=\"color: Green;\"><u>OH Complesion</u></strong></label>";
						list1.add(cin_lbl); //20
						
				 //19
						}
						else {
							
							list1.add(""); //19
							
							String submit_input = "";
							submit_input = "<input type=\"button\" id=\"sub"+i+"\" value=\"Submit\" class=\"btn btn-primary btn-sm\" onclick=\"oh_dt_st_basesubmit("+i+","+rs.getString("cin_id")+","+rs.getString("qr_status")+");\" >";
							list1.add(submit_input); //20
							
						}
							
						
					}
					
					else {
						list1.add(dt_arrival); //16
						list1.add(rs.getString("oh_status")); //17
						list1.add(rs.getString("oh_date")); //18
						String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
						list1.add(remarks); //19
						
						list1.add(""); //20
						list1.add(rs.getString("mode")); //21
						
						
						
						
					}
					

				}else {
					list1.add(dt_arrival); //16
					list1.add(rs.getString("oh_status")); //17
					list1.add(rs.getString("oh_date")); //18
					String remarks = "<textarea  id = 'remarks" +i+ "' name='remarks" +i+ "' class=\"form-control\"></textarea>";
					list1.add(remarks); //19
					
					list1.add(""); //20
					list1.add(rs.getString("mode")); //21
				}
				
			}
			alist.add(list1);
			
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

private String getValue_of_oh(String typeOfIntervention) {	
String return_val="";
	    if (!typeOfIntervention.equals("")) {
	        switch (typeOfIntervention) {
	            case "1":
	            	return_val= "MR1";
	                break;
	            case "2":
	            	return_val= "OH1";
	                
	                break;
	            case "3":
	            	return_val= "MR2";
	               
	                break;
	            case "4":
	            	return_val= "OH2";		
	                break;
	            case "5":
	            	return_val= "MR3";
	                break;
	            case "6":
	            	return_val= "OH3";
	                break;
	            default:
	               
	                break;
	        }
	    }
	    return return_val;

}

}
