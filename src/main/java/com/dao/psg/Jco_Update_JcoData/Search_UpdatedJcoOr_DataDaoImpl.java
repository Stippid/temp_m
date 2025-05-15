package com.dao.psg.Jco_Update_JcoData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.persistance.util.HibernateUtil;

public class Search_UpdatedJcoOr_DataDaoImpl implements Search_UpdatedJcoOr_DataDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> GetJcoOrCensusDataApprove(int jco_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select  distinct 	\n" + "c.id, \n" + "c.marital_status as marital_status,\n" + "c.full_name,\n"
					+ "c.father_name,\n" + "c.father_dob,\n" + "c.mother_name,\n" + "c.mother_dob,\n"
					+ "ra.rank as rank, \n" + "aj.appointment as appointment,c.record_office_sus,c.record_office,c.date_of_attestation,\n"
					+ "a.date_of_appointment as date_appointment, \n"
					+ "(select post.dt_of_tos  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc limit 1) as dt_of_tos,\n"
					+ "(select post.unit_description  from tb_psg_posting_in_out_jco post where c.id = post.jco_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\n"
					+ "c.unit_sus_no,\n" + "i.id_card_no,\n" 
					+ "case when upper(re.religion_name)='OTHERS' OR   upper(re.religion_name)='OTHERS' then ret.other\n" + 
					"     else re.religion_name\n" + 
					"	 end as religion_name,"
					+ "p.arm_desc as parent_arm,\n"
					+ "c.date_of_birth, \n" + "c.enroll_dt,ra.id as rank_id ,aj.id as appoint_id,\n"
					+ "fv.unit_name as command,c.regiment,c.update_jco_status,"
					+ "t.trade as trade,cl.class_pay as class_pay,gp.pay_group as pay_group,c.date_of_seniority as date_of_seniority\n" + "from tb_psg_census_jco_or_p c\n"
					+ "inner join tb_psg_mstr_rank_jco ra on  ra.id = c.rank \n"
					+ "inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and UPPER(status_sus_no) in ('INACTIVE','ACTIVE')\n"
					+ "inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\n"
					+ "inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \n"
					+ "left join tb_psg_change_of_appointment_jco a on c.id = a.jco_id and  a.status ='1'\n"
					+ "left join tb_psg_mstr_appt_jco aj on aj.id=a.appointment \n"
					+ "left join tb_psg_identity_card_jco i on i.jco_id = c.id and i.status=1  \n"
					+ "left join tb_psg_change_religion_jco ret on ret.jco_id=c.id and ret.status=1\n"
					+ "left join tb_psg_mstr_religion re on re.religion_id = ret.religion    \n"
					+ "left join tb_miso_orbat_arm_code p on p.arm_code = c.arm_service\n"
					+ "left join tb_psg_mstr_trade_jco t on t.id = c.trade\n"
					+ "left join tb_psg_mstr_class_pay_jco cl on cl.id = c.class_pay\n"
					+ "left join tb_psg_mstr_pay_group_jco gp on gp.id = c.pay_group\n"
					+ "where (c.status='1' or c.status='5') and  c.id=? ";

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

	public ArrayList<ArrayList<String>> GetServingStatusJCO(int jco_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select c.id,c.army_no, case  \r\n"
					+ " when c.id in (select id from tb_psg_census_jco_or_p where status='5') then 'DESERTER' "
					+ " when c.id in (select id from tb_psg_census_jco_or_p where status='4') then 'NON EFFECTIVE' "
					+ " when c.id in (select jco_id from tb_psg_re_call_jco where re_emp_select ='1' and status=1) then 'RE-CALL FROM RESERVE' \r\n"
					
					+ " else 'SERVING' END\r\n" + "from \r\n" + "tb_psg_census_jco_or_p c \r\n" + "where c.id=?";



			
			stmt = conn.prepareStatement(q);

			stmt.setInt(1, jco_id);

			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));
				list.add(rs.getString("army_no"));
				list.add(rs.getString("case"));

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
	public List<Map<String, String>> getQualificationJCOData(int jco_id, int status) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select qua.id,qua.authority,qua.date_of_authority,qua.type,dom.label,qua.examination_pass,ep.examination_passed as exp_name,qua.degree,deg.degree as d_name,qua.specialization,spe.specialization as spce_name,qua.passing_year,\n"
					+ "qua.div_class,qua.institute,qua.subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other from tb_psg_census_qualification_jco qua\n"
					+ "inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n"
					+ "inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n"
					+ "inner join tb_psg_mstr_specialization spe on spe.id=qua.specialization\n"
					+ "inner join t_domain_value dom on dom.codevalue=qua.type and dom.domainid='QUALIFICATION_TYPE'"
					+ "where qua.jco_id=? and qua.status=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, jco_id);
			stmt.setInt(2, status);
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

	public ArrayList<ArrayList<String>> AppSearch_UpdateJCOData(String personnel_no, String status, String rank,
			String unit_sus_no, String unit_name,String cr_by,String cr_date, String roleSusNo, String roleType, String icStatus) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String qorc="";
		try {
		
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
//			String co_status = "cl.status in ('1','5')";

			if (!roleSusNo.equals("")) {
				qry += " and cl.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}

			if (!personnel_no.equals("")) {
				qry += "  and upper(cl.army_no) like ? ";
			}

			if (status.equals("0")) {
				qry += " and cl.status in ('1','5') and cl.update_jco_status = '0' ";
				
			}
			if (status.equals("1")) {
				qry += " and cl.status in ('1','4','5') and cl.update_jco_status = '1' ";
				
			}
			if (status.equals("3")) {
				qry += " and cl.status in ('1','5') and cl.update_jco_status = '3' ";
			}
			
			if (status.equals("4")) {
				qorc+=",update_jco_cancel_status desc";
				qry += " and cl.status != '0' ";
//			if(roleType.equals("APP")) {
//				qry+=" and cl.update_jco_cancel_status=1 ";
//			}
				if (icStatus.equals("1"))
					qry+= "and cl.status='4' ";
				else
					qry+= "and cl.status!='4' ";
			}

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {
//qry += "  and r.id = ? ";
				qry += " and r.id = ?";
			}
			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

	
	
	if(!cr_date.equals("")) {
				qry +=" and cast(cl.created_date as date) = cast(? as date)";
			}
	

			q = "select distinct cl.id ,\n" + 
					"					cl.Army_no,cl.update_jco_cancel_status,r.rank as rank,cl.full_name,\n" + 
					"					ltrim(TO_CHAR(cl.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\n" + 
					"					arm.arm_desc as parent_arm,cl.status,\n" + 
					"					cl.marital_status,cl.modified_date  \n" + 
					"					FROM tb_psg_census_jco_or_p cl \n" + 
					"					left join logininformation l1 on l1.username =cl.created_by\r\n" +
					"					join tb_psg_mstr_rank_jco r on r.id = cl.rank and r.status = 'active'\n" + 
					"					 join tb_miso_orbat_arm_code  arm on arm.arm_code = cl.arm_service\n" + 
					"					 join tb_miso_orbat_unt_dtl orb on orb.sus_no = cl.unit_sus_no and status_sus_no='Active'\n" + 
					"					 left join logininformation l on cl.unit_sus_no = l.user_sus_no where cl.id !=  0 \n" +  qry +
					"					 order by cl.modified_date desc"+qorc;

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
				if (!personnel_no.equals("")) {
					stmt.setString(j, personnel_no.toUpperCase() + "%");
					j += 1;
				}
//if( !rank.equals("0")) {
				if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {
					stmt.setInt(j, Integer.parseInt(rank));
					j += 1;
				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
						stmt.setString(j, cr_date);
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
					String Approve = "onclick=\" ViewData("
							+ rs.getString("id") + ")\"";
					f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

				}
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3")) {

				}
				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4")) {

					/*String View1 = "onclick=\"  if (confirm('Are You Sure You Want to View This Data?') ){ViewHistoryData("
							+ rs.getString("id") + ",'" + rs.getString("army_no")
							+ "')}else{ return false;}\"";
					f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";*/
					
					String View1 = "onclick=\" ViewHistoryData("
							+ rs.getString("id") + ")\"";
					f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";

					if (icStatus.equals("1")) {

						/*String View2 = "onclick=\"  if (confirm('Are You Sure You Want to View This Data?') ){ViewHistoryInactiveData_FN("
								+ rs.getString("id") + ",'"
								+ rs.getString("army_no") 
								+ "')}else{ return false;}\"";
						f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";*/
						
						String View2 = "onclick=\"  ViewHistoryInactiveData_FN("
								+ rs.getString("id") + ")\"";
						f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";

					}

				}
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4")) {

					if (rs.getString("update_jco_cancel_status").equals("1")) {
						/*String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve JCO/OR Data?') ){ViewCancelHistoryData("
								+ rs.getString("id") + ",'"
								+ rs.getString("army_no") 
								+ "')}else{ return false;}\"";
						f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";*/
						
						String Approve = "onclick=\"  ViewCancelHistoryData("
								+ rs.getString("id") + ")\"";
						f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";
					}

				/*	String View2 = "onclick=\"  if (confirm('Are You Sure You Want to View This Data?') ){ViewHistoryInactiveData_FN("
							+ rs.getString("id") + ",'" + rs.getString("army_no")
							+ "')}else{ return false;}\"";
					f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";*/

					String View2 = "onclick=\" ViewHistoryInactiveData_FN("
							+ rs.getString("id") + ")\"";
					f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

				}

				if (status.equals("1")) {

					String Approve = "onclick=\" AppViewData(" + rs.getString("id") + ")\"";
					f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";
				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3"))) {
					String Reject = "onclick=\" Reject("
							+ rs.getString("id") + ")\"";
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0"))) {
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					
				}
				if (roleType.equals("ALL") && (status.equals("0"))) {
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Approve = "onclick=\" ViewData("
							+ rs.getString("id") + ")\"";
					f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
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
	
	public ArrayList<ArrayList<String>> getShapeData(String shape,int p_id,int status)
    {
            ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
            Connection conn = null;
            String q="";
    
            try{          
                    conn = dataSource.getConnection();                         
                    PreparedStatement stmt=null;
                    
                    
                    
                    q="select authority,date_of_authority,clasification,id,shape_status,shape_value,from_date,to_date,diagnosis,shape_sub_value,other,from_date_1bx,to_date_1bx,diagnosis_1bx,\r\n" + 
                                    "(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis) as des_1,(select icd_description from tb_med_d_disease_cause where icd_code=diagnosis_1bx) as bxdes_1 \r\n" + 
                                    "from tb_psg_medical_category_jco where shape=?  and jco_id=? and status=? order by id" ;
                            stmt=conn.prepareStatement(q);
                            stmt.setString(1, shape);
                            stmt.setInt(2, p_id);
                            stmt.setInt(3, status);
                    
                            ResultSet rs = stmt.executeQuery();      
                            while (rs.next()) {
                                    ArrayList<String> list = new ArrayList<String>();
                                    list.add(rs.getString("shape_status")); //0
                                    list.add(rs.getString("shape_value")); //1
                                    list.add(rs.getString("from_date"));        //2
                                    list.add(rs.getString("to_date"));        //3
                                    if(rs.getString("des_1")!=null)
                                            list.add(rs.getString("diagnosis")+"-"+rs.getString("des_1"));        //4
                                    else
                                            list.add("");

                                    list.add(rs.getString("id")); //10 -> 5
                                    list.add(rs.getString("authority")); //11 -> 6
                                    list.add(rs.getString("date_of_authority")); //12 -> 7
                                    list.add(rs.getString("clasification")); //13 -> 8
                                    list.add(rs.getString("shape_sub_value")); //9
                                    list.add(rs.getString("other")); //10
                                    list.add(rs.getString("from_date_1bx")); //11
                                    list.add(rs.getString("to_date_1bx")); //12
                                    
                                    if(rs.getString("bxdes_1")!=null)
                                            list.add(rs.getString("diagnosis_1bx")+"-"+rs.getString("bxdes_1"));        //13
                                    else
                                            list.add("");
                                    
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
	public ArrayList<ArrayList<String>> getSelfMotFatName(String jco_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(!jco_id.equals("")) {
					qry += " and  c.id= ? ";
				}			
					q="select  distinct\n" + 
							"c.id,\n" + 
							"c.full_name,\n" + 
							"c.date_of_birth,\n" + 
							"c.mother_name,\n" + 
							"c.mother_dob,\n" + 
							"c.father_name,c.father_dob\n" + 
							"from tb_psg_census_jco_or_p c \n" + 
							"where (c.status='1' or c.status='5') " + qry+ "  " ;
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if(!jco_id.equals("")) {
						stmt.setInt(j, Integer.parseInt(jco_id));
						j += 1;	
					} 
				}
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("id"));//0
					list.add(rs.getString("full_name"));//1
					list.add(rs.getString("date_of_birth"));//2								
					list.add(rs.getString("mother_name"));//3
					list.add(rs.getString("mother_dob"));//4
					list.add(rs.getString("father_name"));//5
					list.add(rs.getString("father_dob"));//6
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
	
	@Override
	public ArrayList<ArrayList<String>> getSpouseName(String jco_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(!jco_id.equals("")) {
					qry += " and  c.id= ? ";
				}			
					q="select  distinct\r\n" + 
							"fm.id,fm.maiden_name,fm.date_of_birth\r\n" + 
							"from tb_psg_census_jco_or_p c \r\n" + 
							"inner join tb_psg_census_family_married_jco fm on c.marital_status = fm.marital_status and fm.marital_status='8' and fm.status=1\r\n" + 
							"where (c.status='1' or c.status='5') " + qry+ " order by id desc limit 1 " ;
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if(!jco_id.equals("")) {
						stmt.setInt(j, Integer.parseInt(jco_id));
						j += 1;	
					} 
				}
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("id"));//0
					list.add(rs.getString("maiden_name"));//1
					list.add(rs.getString("date_of_birth"));//2								
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
	public ArrayList<ArrayList<String>> getChildName(String jco_id,String rela) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(!jco_id.equals("")) {
					qry += " and  c.jco_id= ? ";
				}
				if(!rela.equals("")) {
					qry += " and  c.relationship= ? ";
				}
//					q="select  distinct\r\n" + 
//							"cm.id,\r\n" + 
//							"cm.name,\r\n" + 
//							"cm.date_of_birth\r\n" + 
//							"from tb_psg_census_jco_or_p c \r\n" + 
//							"inner join tb_psg_census_family_married_jco fm on c.marital_status = fm.marital_status and fm.marital_status!='10' and fm.status=1 and fm.jco_id=c.id\r\n" + 
//							"inner join tb_psg_census_children_jco cm on cm.jco_id = fm.jco_id and cm.status='1'\r\n" + 
//							"where (c.status='1' or c.status='5') " + qry+ " " ;
				
				q="select  distinct\r\n" + 
						"c.id,\r\n" + 
						"c.name,\r\n" + 
						"c.date_of_birth\r\n" + 
						"from tb_psg_census_children_jco c \r\n" + 
						"where c.status='1' " + qry+ " " ;
				stmt=conn.prepareStatement(q);
				if(!qry.equals(""))     
				{  int j =1;
					if(!jco_id.equals("")) {
						stmt.setInt(j, Integer.parseInt(jco_id));
						j += 1;	
					} 
					if(!rela.equals("")) {
						if(rela.equals("5"))
							stmt.setInt(j, 2);
						else
							stmt.setInt(j, 1);
						j += 1;	
					} 
				}
		      ResultSet rs = stmt.executeQuery();   
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	list.add(rs.getString("id"));//0
					list.add(rs.getString("name"));//1
					list.add(rs.getString("date_of_birth"));//2								
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
	
	public ArrayList<ArrayList<String>> getChilddob(String id,String jco_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
			
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
					q="select  distinct\r\n" + 
							"cm.date_of_birth\r\n" + 
							"from tb_psg_census_jco_or_p c \r\n" + 
							"inner join tb_psg_census_children_jco cm on cm.jco_id = c.id and cm.status='1'\r\n" + 
							"where cm.name = ? and c.id=?";
				stmt=conn.prepareStatement(q);
				  int j =1;
						stmt.setString(j, id);
						j += 1;	
						stmt.setInt(j, Integer.parseInt(jco_id));
		      ResultSet rs = stmt.executeQuery();   
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("date_of_birth"));//0								
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
	@Override
	public String getJcocensusIdentityImagePath(String id) {
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select identity_image,id from tb_psg_identity_card_jco where id=? ";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("identity_image");             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
	public List<Map<String, Object>> getLastPostInnoti_jco(String jco_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		q = "select  a.from_sus_no,a.to_sus_no,TO_CHAR(a.dt_of_tos,'DD-MON-YYYY') AS dt_of_tos,b.army_no,b.full_name,rk.rank as rank \n"
				+ " from tb_psg_posting_in_out_jco a inner join  tb_psg_census_jco_or_p b on  a.jco_id=b.id\n"
				+ " inner join tb_psg_mstr_rank_jco rk on rk.id=b.rank\n"
				+ "  where cast(a.jco_id as character varying)=? order by a.id desc limit 1";





		stmt = conn.prepareStatement(q);

		stmt.setString(1,String.valueOf(jco_id));

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
}
