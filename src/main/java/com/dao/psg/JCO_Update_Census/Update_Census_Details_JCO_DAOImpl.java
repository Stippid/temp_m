package com.dao.psg.JCO_Update_Census;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Update_Census_Details_JCO_DAOImpl implements Update_Census_Details_JCO_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

		this.dataSource = dataSource;

	}

	public ArrayList<ArrayList<String>> AppSearch_UpdateCensusJCOData(String army_no, String status, String rank,

			String unit_sus_no, String unit_name, String roleSusNo, String roleType, String icStatus) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

		

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			if (!roleSusNo.equals("")) {

				qry += " and cl.unit_sus_no = ?";

			}

			if (!unit_sus_no.equals("")) {

				qry += "  and orb.sus_no = ?";

			}

			if (!unit_name.equals("")) {

				qry += "  and orb.unit_name = ?";

			}

			if (!army_no.equals("")) {

				qry += "  and upper(cl.army_no) like ? ";

			}

			if (status.equals("0")) {

				qry += " and cl.status in ('1','5') and cl.update_census_status = '0' ";

			}

			if (status.equals("1")) {

				qry += " and cl.status in ('1','4','5') and cl.update_census_status = '1' ";

			}

			if (status.equals("3")) {

				qry += " and cl.status in ('1','5') and cl.update_census_status = '3' ";

			}

			if (status.equals("4")) {

				qry += " and cl.status != '0' ";

				if (icStatus.equals("1"))

					qry += "and cl.status='4' ";

				else

					qry += "and cl.status!='4' ";

			}

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

				qry += " and r.id = ?";

			}

			q = "select distinct cl.id ,\r\n"
					+ "					cl.Army_no,cl.update_jco_cancel_status,r.rank as rank,cl.full_name,\r\n"
					+ "					ltrim(TO_CHAR(cl.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n"
					+ "					arm.arm_desc as parent_arm,cl.status,\r\n"
					+ "					cl.marital_status,cl.modified_date  \r\n"
					+ "					FROM tb_psg_census_jco_or_p cl \r\n"
					+ "					join tb_psg_mstr_rank_jco r on r.id = cl.rank and r.status = 'active'\r\n"
					+ "					 join tb_miso_orbat_arm_code  arm on arm.arm_code = cl.arm_service\r\n"
					+ "					join tb_miso_orbat_unt_dtl orb on orb.sus_no = cl.unit_sus_no and status_sus_no='Active'\n"
					+ qry +

					"					 order by cl.modified_date desc";


			stmt = conn.prepareStatement(q);


			if (!qry.equals("")) {

				int j = 1;

				if (!roleSusNo.equals("")) {

					stmt.setString(j, roleSusNo);

					j += 1;

				}

				if (!unit_sus_no.equals("")) {

					stmt.setString(j, unit_sus_no);

					j += 1;

				}

				if (!unit_name.equals("")) {

					stmt.setString(j, unit_name);

					j += 1;

				}

				if (!army_no.equals("")) {

					stmt.setString(j, army_no.toUpperCase() + "%");

					j += 1;

				}

				if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

					stmt.setInt(j, Integer.parseInt(rank));

					j += 1;

				}

			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("army_no"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("full_name"));

				list.add(rs.getString("date_of_birth"));

				list.add(rs.getString("parent_arm"));

				String f = "";

				String f1 = "";

				String f2 = "";

				String f3 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0")) {

					String Approve = "onclick=\" {ViewData("
							+ rs.getString("id") + ")}\"";

					f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3")) {
				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4")) {
					String View1 = "onclick=\" {ViewHistoryData("
							+ rs.getString("id") + ",'" + rs.getString("army_no")
							+ "')}\"";
					f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";
					if (icStatus.equals("1")) {
						String View2 = "onclick=\" {ViewHistoryInactiveData_FN("
								+ rs.getString("id") + ",'"

								+ rs.getString("army_no")

								+ "')}\"";

						f1 = "<i class='fa fa-list' " + View2 + " title='View Data'></i>";

					}

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					if (rs.getString("update_jco_cancel_status").equals("1")) {

						String Approve = "onclick=\"  {ViewCancelHistoryData("

								+ rs.getString("id") + ",'"

								+ rs.getString("army_no")

								+ "')}\"";

						f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";

					}

					String View2 = "onclick=\"{ViewHistoryInactiveData_FN("

							+ rs.getString("id") + ",'" + rs.getString("army_no")

							+ "')}\"";

					f1 = "<i class='fa fa-list'   " + View2 + " title='View Data'></i>";

				}

				if (status.equals("1")) {

					String Approve = "onclick=\" AppViewData(" + rs.getString("id") + ")\"";

					f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3"))) {

					String Reject = "onclick=\" {Reject("

							+ rs.getString("id") + ")}\"";

					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0"))) {

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("

							+ rs.getString("id") + ")}else{ return false;}\"";

					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				}

				list.add(f); // 8
				list.add(f1); // 9
				list.add(f2); // 10
				list.add(f3); // 11
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

	public List<Map<String, Object>> GetJcoOrUpdateCensusDataApprove(int jco_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "select  distinct 	\r\n" +

					"c.id, \r\n" +

					"c.full_name,\r\n" +

					"c.father_name,\r\n" +

					"c.father_dob,\r\n" +

					"c.mother_name,\r\n" +

					"c.mother_dob,\r\n" +

					"ra.rank as rank, \r\n" +

					"aj.appointment as appointment,c.record_office_sus,c.record_office,\r\n" +

					"a.date_of_appointment as date_appointment, \r\n" +

					"(select post.dt_of_tos  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n"
					+

					"(select post.unit_description  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n"
					+

					"c.unit_sus_no,\r\n" +

					"p.arm_desc as parent_arm,\r\n" +

					"c.date_of_birth, \r\n" +

					"c.enroll_dt,ra.id as rank_id ,aj.id as appoint_id,\r\n" +

					"fv.unit_name as command,c.regiment,c.update_census_status,t.trade as trade,cl.class_pay as class_pay,gp.pay_group as pay_group,c.date_of_seniority as date_of_seniority\r\n"
					+

					",to_char(c.date_of_rank, 'DD/MM/YYYY') as date_of_rank,to_char((select post.dt_of_tos  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1), 'DD/MM/YYYY') as dt_of_tos2 from tb_psg_census_jco_or_p c\r\n" +

					"inner join tb_psg_mstr_rank_jco ra on  ra.id = c.rank \r\n" +

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\r\n"
					+

					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" +

					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n"
					+

					"left join tb_psg_change_of_appointment_jco a on c.id = a.jco_id and  a.status ='1'\r\n" +

					"left join tb_psg_mstr_appt_jco aj on aj.id=a.appointment     \r\n" +

					"left join tb_miso_orbat_arm_code p on p.arm_code = c.arm_service\r\n" +

					"left join tb_psg_mstr_trade_jco t on t.id = c.trade\r\n" +

					"left join tb_psg_mstr_class_pay_jco cl on cl.id = c.class_pay\r\n" +

					"left join tb_psg_mstr_pay_group_jco gp on gp.id = c.pay_group\r\n" +

					"where (c.status='1' or c.status='5') and  c.id=? ";

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, jco_id);

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
	
	/// bisag v2 010922  (converted to Datatable)


		public long Getcensus_detail_data_count_jco(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
				String rank,String status,String icstatus,String roleType,String cr_by,String cr_date) {
			
			// String roleType = sessionUserId.getAttribute("roleType").toString();
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
			String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_sus_no, unit_name,personnel_no,
					 rank, status,icstatus,roleType,roleSusNo,cr_by,cr_date); 
				int total = 0;
				
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					
								
				  q=" select count(app.*) from( select distinct cl.id , " + 
				  		"cl.Army_no,cl.update_jco_cancel_status,r.rank as rank,cl.full_name, " + 
				  		"ltrim(TO_CHAR(cl.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth, " + 
				  		"arm.arm_desc as parent_arm,cl.status, " + 
				  		"cl.marital_status,cl.modified_date   " + 
				  		"FROM tb_psg_census_jco_or_p cl  " + 
				  		"join tb_psg_mstr_rank_jco r on r.id = cl.rank and r.status = 'active' " + 
				  		" join tb_miso_orbat_arm_code  arm on arm.arm_code = cl.arm_service " + 
				  		"join tb_miso_orbat_unt_dtl orb on orb.sus_no = cl.unit_sus_no and status_sus_no='Active'"
				  		+ "left join logininformation l1 on l1.username = cl.created_by "  
				  		+SearchValue+ "  order by cl.modified_date desc) app " ;
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name,personnel_no, 
							 rank, status, icstatus,roleType , roleSusNo,cr_by, cr_date);
				
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
		
		
		
		
		public PreparedStatement setQueryWhereClause_SQL(PreparedStatement
				  stmt,String Search,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String roleType,String roleSusNo,String cr_by,String cr_date) {
			int flag = 0;
			
			try {
				if(!Search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					

					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, Search.toUpperCase()+"%");
					
					
					flag += 1;
					stmt.setString(flag, "%"+Search.toUpperCase()+"%");
					
					
				
				}
	   
				if(!unit_sus_no.equals("")) {
					flag += 1;
				
					stmt.setString(flag, unit_sus_no.toUpperCase());
				}
				if(!unit_name.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_name.toUpperCase());
				}
				
				
				if(!personnel_no.equals("")) {
					flag += 1;
					stmt.setString(flag, "%"+personnel_no.toUpperCase()+"%");
				}
				if(!rank.equals("0")) {
					flag += 1;
					//stmt.setString(flag, rank);
					stmt.setInt(flag, Integer.parseInt(rank));
				}
		         if(!roleSusNo.equals("")) {
					flag += 1;
					stmt.setString(flag, roleSusNo.toUpperCase());
					
				}
		         if(!cr_by.equals("")) {
				        
						flag += 1;
						stmt.setString(flag, cr_by);
					}
				  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, cr_date);
					}
			
			}catch (Exception e) {}
		    return stmt;
			
		}
		

			
		
		
				

		 public String GenerateQueryWhereClause_SQL(String Search,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String roleType,String roleSusNo,String cr_by,String cr_date) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" where  ";
					SearchValue +="(  upper(cl.army_no) like ? or upper(r.rank) like ? "
							+ " or upper(cl.full_name) like ? or upper(ltrim(TO_CHAR(cl.date_of_birth,'DD-MON-YYYY'),'0')) like ?  "
							+ " or upper(arm.arm_desc)  like ? "
							+ "  ) ";
				}
			
				
			
				
				if( !unit_sus_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.sus_no  = ?";	
					}
					else {
						SearchValue += " where  orb.sus_no = ?";
					}
				}
				if( !unit_name.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  orb.unit_name = ?  ";	
					}
					else {
						SearchValue += " where  orb.unit_name = ? ";
					}
				}
				
				if(!personnel_no.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += "  and upper(cl.army_no) like  ? ";
					}
					else {
						SearchValue += "  where upper(cl.army_no) like ? ";
					}
					
				}			
				if(!rank.equals("0")) {
					if (SearchValue.contains("where")) {
						
						SearchValue += " and r.id = ? ";
						//SearchValue += "  and r.id = ? ";
					}
					else {
						SearchValue += "  where  r.id = ? ";
					}
					
				}			
				if(status.equals("0")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and cl.status in ('1','5') and cl.update_census_status = '0' ";
					}
					else {
						SearchValue += " where cl.status in ('1','5') and cl.update_census_status = '0' ";
					}
					
				}
				if(status.equals("1")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and cl.status in ('1','4','5') and cl.update_census_status = '1' ";
					}
					else {
						SearchValue += " where  cl.status in ('1','4','5') and cl.update_census_status = '1' ";
					}
					
				}
				if(status.equals("3")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  cl.status in ('1','5') and cl.update_census_status = '3' ";
					}
					else {
						SearchValue += " where  cl.status in ('1','5') and cl.update_census_status = '3' ";
					}
					
				}
				if(status.equals("4")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and  cl.status != '0' ";
						if (icstatus.equals("1"))
						SearchValue += " and  cl.status = '4' ";
						else
							SearchValue += "and cl.status!='4' ";
						
					}
					else {
						SearchValue += " where  and cl.status != '0' ";
						if (icstatus.equals("1"))
							SearchValue += " where  cl.status = '4' ";
							else
								SearchValue += "where cl.status!='4' ";
							
						}
						
					}
					
				
				  
				if( !roleSusNo.equals("")) {
					if (SearchValue.contains("where")) {
						SearchValue += " and cl.unit_sus_no = ? ";	
					}
					else {
						SearchValue += " where cl.unit_sus_no = ? ";
					}
				}
				 if(!cr_by.equals("")) {
						
						if (SearchValue.contains("where")) {
						
							SearchValue += " and cast(l1.userid as character varying) = ? ";				
						}
						else {
						
							SearchValue += " where cast(l1.userid as character varying) = ? ";				
						}
						
					}
				  
				  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
						if (SearchValue.contains("where")) {
							SearchValue += " and cast(cl.created_date as date) = cast(? as date)";	
						}
						else {
							SearchValue += " where cast(cl.created_date as date) = cast(? as date)";	
						}
						
						
					}
				
				return SearchValue;
			}
		 
		 
		 
		 public List<Map<String, Object>> Getcensus_detail_data_jco(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
				 String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String roleType,String cr_by,String cr_date) 
			{
				
			
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				
		    	String SearchValue = GenerateQueryWhereClause_SQL(Search, unit_sus_no, unit_name,personnel_no, rank, status, icstatus, roleType, roleSusNo, cr_by, cr_date);
		    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
			Connection conn = null;
			String q="";


			try{	
				String pageL = "";
		        
		        if(pageLength == -1){
					pageL = "ALL";
				}else {
					pageL = String.valueOf(pageLength);
				}
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					
				
				
				
					
					
					  q=" select distinct cl.id , " + 
					  		"cl.Army_no,cl.update_jco_cancel_status,r.rank as rank,cl.full_name, " + 
					  		"ltrim(TO_CHAR(cl.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth, " + 
					  		" arm.arm_desc as parent_arm,cl.status, " + 
					  		" cl.marital_status,cl.modified_date   " + 
					  		" FROM tb_psg_census_jco_or_p cl " + 
					  		" join tb_psg_mstr_rank_jco r on r.id = cl.rank and r.status = 'active'" + 
					  		" join tb_miso_orbat_arm_code  arm on arm.arm_code = cl.arm_service" + 
					  		" join tb_miso_orbat_unt_dtl orb on orb.sus_no = cl.unit_sus_no and status_sus_no='Active'"
					  		+ "left join logininformation l1 on l1.username = cl.created_by " + 
					  		"" +SearchValue+ "  order by cl.modified_date desc limit " +pageL+" OFFSET "+startPage+" ";
					
				
					stmt=conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name, personnel_no, rank, status, icstatus,roleType, roleSusNo, cr_by, cr_date);
					
			      ResultSet rs = stmt.executeQuery();   
			     
			      ResultSetMetaData metaData = rs.getMetaData();
			      int columnCount = metaData.getColumnCount();
			      
			  	while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String action = "";
					String f = "";
					String f1 = "";
					String f2 = "";
					String f3 = "";
					String f4 = "";
					String f5 = "";
					String f7 = "";
					String fk = "";
					String fv = "";
					
					
						String Approve = "onclick=\" {ViewData("
								+ rs.getString("id") + ")}\"";

						f4 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
					     String View1 = "onclick=\" {ViewHistoryData("
								+ rs.getString("id") + ",'" + rs.getString("army_no")
								+ "')}\"";
						f5 = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";
						String View2 = "onclick=\" {ViewHistoryInactiveData_FN("
									+ rs.getString("id") + ",'"

									+ rs.getString("army_no")

									+ "')}\"";

							f1 = "<i class='fa fa-list' " + View2 + " title='View Data'></i>";

						String Approve1 = "onclick=\"  {ViewCancelHistoryData("

									+ rs.getString("id") + ",'"

									+ rs.getString("army_no")

									+ "')}\"";

							fv = "<i class='fa fa-eye'  " + Approve1 + " title='Approve Data'></i>";
	                   String Approve2 = "onclick=\" AppViewData(" + rs.getString("id") + ")\"";

						fk = "<i class='fa fa-eye'  " + Approve2 + " title='View Data'></i>";

					String Reject = "onclick=\" {Reject("

								+ rs.getString("id") + ")}\"";

						f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
	                  String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("
	             + rs.getString("id") + ")}else{ return false;}\"";

						f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					
					
					
					if (roleType.equals("ALL") || roleType.equals("DEO")) {
						if (status.equals("0")) {
							f7 += f3;
						}
	                    if (status.equals("3")) {
							f7 += f2;
						}
	                    if (status.equals("4")) {
							f7 += f5;
							if (icstatus.equals("1")) {
								f7 += f1;
							}
								
						}
					}
					if (roleType.equals("ALL") || roleType.equals("APP")) {
						if (status.equals("4")) {
							if (rs.getString("update_jco_cancel_status").equals("1")) {
								f7 += fv;
							}
							    f7 += f1;
						    }
						if (status.equals("0")) {
							f7 += f4;
						}
					}
					
					if (status.equals("1")) {
						f7 += fk;
					}
					
					
					columns.put("action", f7); // 5
				  list.add(columns);
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
				return list;
			}

}
