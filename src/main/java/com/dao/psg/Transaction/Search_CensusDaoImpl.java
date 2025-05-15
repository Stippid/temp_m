package com.dao.psg.Transaction;

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

import org.hibernate.Session;

import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;

public class Search_CensusDaoImpl implements Search_CensusDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

		this.dataSource = dataSource;

	}

	public ArrayList<ArrayList<String>> Search_census(String roleSusNo, String personnel_no, String status, String rank,
			String unit_name, String unit_sus_no, HttpSession session)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			// qry = "id != 0";

			String roleType = session.getAttribute("roleType").toString();

			if (!roleSusNo.equals("")) {

				qry += " and tp.unit_sus_no = ?";

			}

			if (!unit_sus_no.equals("")) {

				qry += "  and orb.sus_no = ?";

			}

			if (!unit_name.equals("")) {

				qry += "  and orb.unit_name = ?";

			}

			if (!personnel_no.equals("")) {

				qry += "  and upper(tp.personnel_no) like ? ";

			}

			if (!rank.equals("")) {

				qry += "  and r.id= ? ";

			}

			if (status.equals("0")) {

				qry += " and cast(cl.status as character varying) = ? ";

			}

			if (status.equals("1")) {

				qry += " and cast(cl.status as character varying) = ? ";

			}

			if (status.equals("3")) {

				qry += " and cast(cl.status as character varying) = ? ";

			}

			 q = "select distinct \r\n" + 
						"cl.id,\r\n" + 
						"tp.cadet_no,\r\n" + 
						"tp.personnel_no,COALESCE(cl.reject_remarks,'') as reject_remarks,\r\n" + 
						"r.description as rank,\r\n" + 
						" CONCAT(cl.first_name, ' ', cl.middle_name, ' ', cl.last_name) AS name,\r\n" + 
						"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" + 
						"orb.unit_name,\r\n" + 
						"arm.arm_desc as parent_arm,cl.status,tp.id as comm_id\r\n" + 
						"FROM tb_psg_census_detail_p cl \r\n" + 
						"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id and tp.status !='4'\r\n" + 
						"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank\r\n" + 
						"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'\r\n" + 
						"left join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n" + 
						"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" + 
						"where cl.id !=  0" + qry + " order by cl.id desc";


			
			
			stmt = conn.prepareStatement(q);

			
			// stmt.setString(1,roleSuNo);

			int j = 1;

			if (!qry.equals(""))

			{

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

				if (!personnel_no.equals("")) {

					stmt.setString(j, personnel_no.toUpperCase() + "%");

					j += 1;

				}

				if (!rank.equals("")) {

					stmt.setInt(j, Integer.parseInt(rank));

					j += 1;

				}

				if (status.equals("0")) {

					stmt.setString(j, status);

					j += 1;

				}

				if (status.equals("1")) {

					stmt.setString(j, status);

					j += 1;

				}

				if (status.equals("3")) {

					stmt.setString(j, status);

					// j += 1;

				}

			}
			
			

			ResultSet rs = stmt.executeQuery();

		

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("cadet_no"));

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("name"));

				list.add(rs.getString("date_of_birth"));

				list.add(rs.getString("unit_name"));

				list.add(rs.getString("parent_arm"));

				list.add(rs.getString("status"));

				list.add(rs.getString("reject_remarks"));

				String f = "";

				String f1 = "";

				String f2 = "";
				String chekboxaction="";
				
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					 String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
						+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
				  
				     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
						+ "' value='" + rs.getObject(1) + "'   />";
				     chekboxaction+=Checkbox;

					String View = "onclick=\" View1Data('" + rs.getString("comm_id") + "','" + rs.getString("status") + "')\"";

					f1 = "<i " + View + " title='Approve Or Reject Data' style=' background: #31af91;border-radius: 24px;font-size: 15px; padding: 5px;'><i class='fa fa-check' style='color:white;'></i><i style='color:white;' class='fa fa-times'></i></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && (status.equals("1")))
//					if (roleType.equals("APP") && (status.equals("1") || status.equals("3")))
				{

			// VIEW 
					String View = "onclick=\"View1Data('" + rs.getString("comm_id") + "','" + rs.getString("status") + "') \"";

					f1 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("1"))

				{

					// VIEW

					String View = "onclick=\" View1Data('" + rs.getString("comm_id") + "','" + rs.getString("status") + "') \"";

					f1 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && ((status.equals("0")) || (status.equals("3")))){

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Details?') ){editData('"+ rs.getString("comm_id") + "')}else{ return false;}\"";

					f2 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				}

				// list.add(f);

				list.add(f1);

				list.add(f2);
				
				list.add(chekboxaction);
				
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

	public List<Map<String, Object>> View_censusByid(int id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "select p.id,cl.cadet_no,cl.personnel_no,cl.name, cl.gender,cl.date_of_birth,\r\n" +

					"PGP_SYM_DECRYPT(p.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number ,p.blood_group,p.border_area,p.country_birth,p.border_area,\r\n" +

					"p.district_birth,p.first_name,p.height,p.last_name,p.marital_status,p.last_name,\r\n" +

					"p.middle_name,p.nationality,p.place_birth,\r\n"
					+

					"p.religion,p.state_birth,p.status,p.father_name,p.father_dob,p.father_place_birth,p.father_profession,p.mother_name,\r\n"
					+

					"p.mother_dob,p.mother_profession,p.mother_place_birth,\r\n" +

					"ac.cen_id,p.org_state,p.org_district,ac.pre_state,ac.pre_district,ac.permanent_state,ac.permanent_district,\r\n"
					+

					"ac.permanent_village,ac.permanent_pin_code,ac.permanent_near_railway_station, \r\n" +

					"ac.permanent_tehsil,ac.nok_name, ac.relation,PGP_SYM_DECRYPT(ac.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no,ac.present_state,ac.present_district, \r\n"
					+

					"ac.present_village,ac.present_pin_code,ac.present_near_railway_station,p.org_state,p.org_district, \r\n"
					+

					"ac.nok_village,ac.nok_state,ac.nok_district,ac.nok_pin,ac.nok_near_railway_station,ac.nok_tehsil,ac.present_tehsil ,\r\n"
					+

					"fc.id,fc.country,fc.period,fc.from_dt,fc.to_dt,fc.purpose_visit,\r\n" +

					"cc.employee_name,cc.gazetted,cc.civil_service,cc.from_date,cc.to_date,cc.army_no,\r\n" +

					"cc.unit_reg,cc.designation,cc.pre_cadet_status,cc.total_service,\r\n" +

					"l.cen_id,l.f_exam_pass,l.f_lang_prof,l.f_mother_tougue,l.foreign_language,l.lang_std,l.language,\r\n"
					+

					"q.cen_id, q.div_class,q.examination_pass,q.institute,q.passing_year,q.subject,q.type,\r\n" +

					"sf.cen_id,sf.date_of_birth,sf.name,sf.relationship,\r\n" +

					"mf.cen_id,mf.adhar_no,mf.date_of_birth,mf.maiden_name,mf.marriage_date,\r\n" +

					"mf.nationality,mf.place_of_birth,\r\n" +

					"df.cen_id,df.divorce_date,df.divorced_spouse,df.marriage_date,ne.cen_id,ne.otu,ne.type_of_entry \r\n"
					+

					"from  tb_psg_census_detail_p p\r\n" +

					"inner join tb_psg_trans_proposed_comm_letter cl on p.comm_id = cl.id\r\n" +

					"left join tb_psg_census_address ac on ac.cen_id= cl.id \r\n" +

					"left join tb_psg_census_foreign_country fc on fc.cen_id=cl.id \r\n" +

					"left join tb_psg_census_cadet cc on cc.census_id=cl.id \r\n" +

					"left join tb_psg_census_language l   on l.cen_id=cl.id\r\n" +

					"left join tb_psg_census_qualification q on q.cen_id=cl.id \r\n" +

					"left join tb_psg_census_family_siblings sf  on sf.cen_id=cl.id \r\n" +

					"left join tb_psg_census_family_married mf  on mf.cen_id=cl.id \r\n" +

					"left join tb_psg_census_family_divorce df  on df.cen_id=cl.id\r\n" +

					"left join tb_psg_census_ncc_exp ne  on ne.cen_id=cl.id where p.id=? ";

			stmt = conn.prepareStatement(q);

			stmt.setInt(1, id);

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

	
	public String approve_censusData(String a,String user_sus,String status,String username) {
		String[] id_list = a.split(":");

		Connection conn = null;
		Integer out = 0;
		String q = "";
	

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
	
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Assets_Main assetupd =(Assets_Main)sessionHQL.get(Assets_Main.class, id);
					stmt = conn.prepareStatement("update tb_psg_census_detail_p set status=? where id=?");
					
					
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2, id);
					out = stmt.executeUpdate();
		
					
					}

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

		if (out > 0) {
			if(status.equals("1")) {
			return "Approved Successfully";
			}
			else if(status.equals("3")) {
				return "Rejected Successfully";
				}
			else
				return "UnSuccessfully";
		} else {
			if(status.equals("1")) {
				return "Approved not Successfully";
				}
			else if(status.equals("3")) {
				return "Rejected not Successfully";
				}
			else
			return "UnSuccessfully";
		}
	}
	
	/// bisag v2 230822  (converted to Datatable)


	public long GetSearch_censusCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_name,String personnel_no,
			String rank,String status, String cr_by,String cr_date,String roleType,Boolean IsMns) {
		
		// String roleType = sessionUserId.getAttribute("roleType").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
		String qry="";
		if(IsMns == true) {
			qry= "and substr(tp.personnel_no,1,2) in ('NR','NS')";
		}else {
			qry= "and substr(tp.personnel_no,1,2) Not in ('NR','NS')";
		}
		String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_sus_no, unit_name,personnel_no,
				 rank, status,cr_by,cr_date,roleType,roleSusNo,roleAccess); 
			int total = 0;
			
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
			
			  q=" select count(app.*) from( select distinct " + 
			  		" cl.id,  " +
			  		" tp.cadet_no," + 
			  		" tp.personnel_no,COALESCE(cl.reject_remarks,'') as reject_remarks," + 
			  		" r.description as rank," + 
			  		" tp.name," + 
			  		" ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth," + 
			  		" orb.unit_name," + 
			  		" arm.arm_desc as parent_arm,cl.status,tp.id as comm_id " + 
			  		" FROM tb_psg_census_detail_p cl " + 
			  		" inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id and tp.status !='4'" + 
			  		" inner join cue_tb_psg_rank_app_master r on r.id = tp.rank" + 
			  		" inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm " + 
			  		" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'" + 
			  		" left join logininformation l on tp.unit_sus_no = l.user_sus_no" + 
			  		" left join tb_psg_mstr_course mc on mc.id=tp.id "
			  		+ "left join logininformation l1 on l1.username =cl.created_by "  +
			  		" and cl.id !=  0 " +SearchValue+" "+qry+") app " ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name,personnel_no,
						 rank, status,cr_by,cr_date,roleType , roleSusNo,roleAccess);
			
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
						String rank,String status,String cr_by, String cr_date,String roleType,String roleSusNo,String roleAccess) {
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
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						
						
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
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
					if(!rank.equals("")) {
						flag += 1;
						stmt.setString(flag, rank);
					}
					

				if(!status.equals("")) {
						flag += 1;
						stmt.setString(flag, status);
					}
					

				  if(!cr_by.equals("")) {
						        
								flag += 1;
								stmt.setString(flag, cr_by);
							}
						  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
								flag += 1;
								stmt.setString(flag, cr_date);
							}
						  
						  
				if (!roleAccess.equals("MISO") && !roleAccess.equals("DGMS")) {
                      flag += 1;
					
					stmt.setString(flag, "%"+roleSusNo.toUpperCase()+"%");
				}	
//				if(!roleSusNo.equals("")) {
//					flag += 1;
//					
//					stmt.setString(flag, "%"+roleSusNo.toUpperCase()+"%");
//					
//				}
				
				
				}catch (Exception e) {}
			
				return stmt;
				
			}
			
			
			
			 
					

			 public String GenerateQueryWhereClause_SQL(String Search,String unit_sus_no,String unit_name,String personnel_no,
						String rank,String status,String cr_by,String cr_date,String roleType,String roleSusNo,String roleAccess) {
					String SearchValue ="";
					if(!Search.equals("")) { // for Input Filter
					
						
						SearchValue =" where  ";
						SearchValue +="( upper(tp.cadet_no) like ? or upper(tp.personnel_no) like ? or upper(r.description) like ? "
								+ " or upper(tp.name) like ? or upper(ltrim(TO_CHAR(tp.date_of_birth,'DD-MON-YYYY'),'0')) like ? or upper(orb.unit_name) like ? "
								+ " or upper(arm.arm_desc)  like ?  "
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
							SearchValue += "  and upper(tp.personnel_no) like ? ";
						}
						else {
							SearchValue += "  where upper(tp.personnel_no) like ? ";
						}
						
					}			
					if(!rank.equals("")) {
						if (SearchValue.contains("where")) {
							
							SearchValue += " and cast(r.id  as character varying) = ? ";
							//SearchValue += "  and r.id = ? ";
						}
						else {
							SearchValue += "  where  cast(r.id  as character varying) = ? ";
						}
						
					}			
					if(!status.equals("")) {
						if (SearchValue.contains("where")) {
							SearchValue += " and cast(cl.status as character varying) = ? ";
						}
						else {
							SearchValue += " where  cast(cl.status as character varying) = ? ";
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
					//260194
					 if(!roleAccess.equals("MISO") && !roleAccess.equals("DGMS")) {
							if (SearchValue.contains("where")) {
								SearchValue += " and upper(tp.unit_sus_no) like ? ";	
							}
							else {
								SearchValue += " where upper(tp.unit_sus_no) like ?";
							}
					 }
				
				
				
					
					return SearchValue;
				}
			 
			 
			 
			 public List<Map<String, Object>> GetSearch_censusdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
					 String unit_sus_no,String unit_name,String personnel_no,
						String rank,String status, String cr_by,String cr_date,String roleType,Boolean IsMns) 
				{
					
				
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
					String qry="";
					if(IsMns == true) {
						qry= "and substr(tp.personnel_no,1,2) in ('NR','NS')";
					}else {
						qry= "and substr(tp.personnel_no,1,2) Not in ('NR','NS')";
					}
			    	String SearchValue = GenerateQueryWhereClause_SQL(Search, unit_sus_no, unit_name,personnel_no, rank, status,cr_by,cr_date , roleType, roleSusNo,roleAccess);
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
						
					
						q="select distinct " + 
								"cl.id, " + 
								"tp.cadet_no, " + 
								"tp.personnel_no,COALESCE(cl.reject_remarks,'') as reject_remarks, " + 
								"r.description as rank, " + 
								"       case when cl.status = '0' then tp.name\r\n"
								+ "            else concat(cl.first_name, ' ', cl.middle_name, ' ', cl.last_name)\r\n"
								+ "             end as name," + 
								"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth, " + 
								"orb.unit_name, " + 
								"arm.arm_desc as parent_arm,cl.status,tp.id as comm_id " + 
								"FROM tb_psg_census_detail_p cl " + 
								"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id and tp.status !='4' " + 
								"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank " + 
								"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm " + 
								"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active' " + 
								"left join logininformation l on tp.unit_sus_no = l.user_sus_no " + 
								" left join tb_psg_mstr_course mc on mc.id=tp.id "
								+  " left join logininformation l1 on l1.username =cl.created_by "
								+ ""+SearchValue + "  "+qry+" and  cl.id !=  0 order by cl.id "   + 
								" desc  limit " +pageL+" OFFSET "+startPage+" ";
						
					
					
						stmt=conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_name, personnel_no, rank, status,cr_by,cr_date,roleType, roleSusNo,roleAccess);
						
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
						
						
						
						String View = "onclick=\" View1Data('" + rs.getString("comm_id") + "','" + rs.getString("status") + "')\"";

						f1 = "<i " + View + " title='Approve Or Reject Data' style=' background: #31af91;border-radius: 24px;font-size: 15px; padding: 5px;'><i class='fa fa-check' style='color:white;'></i><i style='color:white;' class='fa fa-times'></i></i>";
						
							
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Details?') ){editData('"+ rs.getString("comm_id") + "')}else{ return false;}\"";

						f2 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						

						
						String View1 = "onclick=\"View1Data('" + rs.getString("comm_id") + "','" + rs.getString("status") + "') \"";
						f3 = "<i class='fa fa-eye'  " + View1 + " title='View Data'> </i>&nbsp;";

					String ModificationAfterApproval = "onclick=\"  if (confirm('Are You Sure You Want to Modify This Details?') ){modificationAfterApprovalData('"+ rs.getString("comm_id") + "')}else{ return false;}\"";
						f3 += "<i class='action_icons action_update'  " + ModificationAfterApproval + " title='Modify Data'></i>";

						if (roleType.equals("ALL") || roleType.equals("DEO")) {
							if (status.equals("0")) {
								f7 += f2;
								
							}
							if (status.equals("1")) {
								f7 += f3;
								
								
							}
							if (status.equals("3")) {

								f7 += f2;
							}
						}
						if (roleType.equals("ALL") || roleType.equals("APP")) {
							if (status.equals("1")) {
								f7 += f3;
								
							}
							
							if (status.equals("0")) {
								
								f7 += f1;
								
								
							}

						}
						columns.put("action", f7); // 5
					  
						
					
				  
						//columns.put("action",action);
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
