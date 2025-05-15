package com.dao.psg.Civilian;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Civilian.TB_CIVILIAN_DETAILS;
import com.persistance.util.HibernateUtil;

public class Civilian_dtlDAOImpl implements Civilian_dtlDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public ArrayList<ArrayList<String>> Search_Civilian_non_regular(String roleSusNo,String roleType,String unit_sus_no,String unit_name,String first_name,int status,String personnel_no, String cr_by,String cr_date,HttpSession session) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";	
		String q1="";
		String q2="";
		String q3="";
		String q4="";
		String q5="";
		String q6="";
		String q7="";
		try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(!roleSusNo.equals("")) {
				q1+=" and c.sus_no=?";
			}
			
			if(!unit_sus_no.equals("")) {
				q1 += "  and orb.sus_no = ?";
			}
			if(!first_name.equals("")) {
				q2+=" and ( upper(c.first_name)  like ? or upper(middle_name) like ? or upper(last_name) like ? ) ";
			}
			if(status!= -1 ) {
				q3+=" and status=?";
			}
			if(!personnel_no.equals("")) {
				q5 += "  and c.employee_no = ?";
			}
			if (!cr_by.equals("")) {
				q6 += "  and cast(l1.userid as character varying)= ? ";
			}

			if (!cr_date.equals("")) {
				q7 += " and cast(c.created_date as date) = cast(? as date)";
			}
			if(status != 1)	
			{
				q="select c.id,c.employee_no,c.sus_no as unit_sus_no,orb.unit_name,c.first_name ||' '|| middle_name ||' '|| last_name as name,ltrim(TO_CHAR(c.dob,'DD-MON-YYYY'),'0') as dob,c.civ_type,c.cancel_status,c.reject_remarks,c.employee_no from tb_psg_civilian_dtl c\n" + 
						" 						inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\n" + 
						"left join logininformation l1 on l1.username =c.created_by\r\n" + 
						"						where civilian_status='NR' "+q1 +q2 +q3 +q4 +q5 + q6 +q7 +"order by c.id desc" ;
				
			}
			
			if(status == 1)	
			{
				q="select c.id,c.employee_no,c.sus_no as unit_sus_no,orb.unit_name,c.first_name ||' '|| middle_name ||' '|| last_name as name,ltrim(TO_CHAR(c.dob,'DD-MON-YYYY'),'0') as dob,c.civ_type,c.cancel_status,c.reject_remarks,c.employee_no,(select count(*) from tb_psg_civilian_dtl where status in(0,3) and main_id = c.main_id) app from tb_psg_civilian_dtl c\n" + 
						" 						inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\n" + 
						"						left join logininformation l1 on l1.username =c.created_by\r\n" + 
						"						where civilian_status='NR' "+q1 +q2 +q3 +q4 +q5 + q6 + q7 +"order by c.id desc" ;
		
			
			}
			
			  stmt=conn.prepareStatement(q);
			  int i=1;
			  
			  if(!roleSusNo.equals("")) {
				  stmt.setString(i,roleSusNo);
				  i+=1;
				  }
			  
			  
			  if(!unit_sus_no.equals("")) {
			  stmt.setString(i,unit_sus_no);
			  i+=1;
			  }
			  if(!first_name.equals("")) {
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  }
			  
			  if(status!= -1 ) {
					stmt.setInt(i,status);
					  i+=1;
				}
			  if(!personnel_no.equals("")) {
				  stmt.setString(i,personnel_no);
				  i+=1;
				  }
			  if (!cr_by.equals("")) {
					stmt.setString(i, cr_by);
					i += 1;
				}
				if (!cr_date.equals("")) {
						stmt.setString(i, cr_date);
						i += 1;
					}
		      ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("id"));
		    	  list.add(rs.getString("employee_no"));
		    	  list.add(rs.getString("name"));
					
		    	  if(rs.getString("dob")!=null) {
						list.add(rs.getString("dob"));
						}
						else
							list.add("");	
		    	  list.add(rs.getString("unit_sus_no"));
		    	  list.add(rs.getString("reject_remarks"));
				
					String f1 = "";
					String f2 = "";
					String f3 = "";
					String f4 = "";
					String f5 = "";
					String f6 = "";
					String f7 = "";
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 0) {
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Details?') ){deleteData("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						
						f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 1) {
						if(rs.getString("app").equals("0"))
						{	
							String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){AppeditData("
									+ rs.getInt("id")+")}else{ return false;}\"";
							f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						}
						
						
						String View = "onclick=\"  {approveviewciviliannonregular("
								+ rs.getInt("id")+")}\"";
		               f3 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='Approve Data'></i>";
					}
					
                 if (roleType.equals("ALL") || roleType.equals("DEO") && status == 3) {
						
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						
					}
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 0 || status == 3)) {
						String View = "onclick=\" {viewData("
								+ rs.getInt("id")+")}\"";
		               f3 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='Approve Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 4) {
						if(rs.getInt("cancel_status") == -1) {
							String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelCivilianData("
									+ rs.getInt("id") + ")}else{ return false;}\"";
							 f5="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'>"
							 		+ "<i class='fa fa-times'></i></a>";
							 
							 String View = "onclick=\"  {approveviewciviliannonregular("
										+ rs.getInt("id")+")}\"";
				               f3 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='View Data'></i>";
						}
						else {
							String View = "onclick=\"  {approveviewciviliannonregular("
									+ rs.getInt("id")+")}\"";
			               f3 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='View Data'></i>";
						}
					}
					
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 1 || status == 3 || status == 4)) {
						
						String View = "onclick=\"  {approveviewciviliannonregular("
								+ rs.getInt("id")+")}\"";
		               f3 = "<i class='fa fa-eye'  " + View + " style='margin-left: 10px;' title='View Data'></i>";
					}
					
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 4)) {
						if(rs.getInt("cancel_status") == 0) {
						String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve Cancel Details ?') ){AppCancelData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f6 = "<i class='action_icons action_approve'  " + Approve + " style='margin-left: 10px;' title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are You Sure You Want to Reject Cancel Details ?') ){RejCancelData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f7 = "<i class='action_icons action_reject'  " + Reject + " style='margin-left: 10px;' title='Reject Data'></i>";
						}
					}
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 0)) {
						String view = "onclick=\"  {viewData("
								+ rs.getInt("id")+")}\"";
						f3 = "<i class='action_icons action_approve'  " + view + " style='margin-left: 10px;' title='Approve Data'></i>";
					}
					
					list.add(f1);
					list.add(f2);
					list.add(f3);
					list.add(f4);
					list.add(f5);
					list.add(f6);
					list.add(f7);
					
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
	public ArrayList<ArrayList<String>> Search_Civilian_regular(String roleSusNo,String roleType,String unit_sus_no,String unit_name,String first_name,int status,String personnel_no, String cr_by,String cr_date,HttpSession session) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String q1="";
		String q2="";
		String q3="";
		String q4="";
		String q5="";
		String q6="";
		String q7="";
		try{	  
			if(!roleSusNo.equals("")) {
				q1+=" and c.sus_no=?";
			}
			
			if(!unit_sus_no.equals("")) {
				q1 += "  and orb.sus_no = ?";
			}
			if(!first_name.equals("")) {
				q2+=" and ( upper(c.first_name) like ? or upper(middle_name) like ? or upper(last_name) like ? ) ";
			}
			
			if(status!= -1 ) {
				q3+=" and status=?";
			}
			
			if(!personnel_no.equals("")) {
				q5+=" and ( upper(c.employee_no) like ? ) ";
			}
			if (!cr_by.equals("")) {
				q6 += "  and cast(l1.userid as character varying)= ? ";
			}
			if (!cr_date.equals("")) {
				q7 += " and cast(c.created_date as date) = cast(? as date)";
			}
			//kajal----
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(status != 1)	
				{
					q="select c.id,c.employee_no,c.sus_no as unit_sus_no,orb.unit_name,c.first_name ||' '|| middle_name ||' '|| last_name as name,ltrim(TO_CHAR(c.dob,'DD-MON-YYYY'),'0') as dob,c.civ_type,c.cancel_status,c.reject_remarks,c.employee_no from tb_psg_civilian_dtl c\n" + 
							" 						inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\n" + 
							"						left join logininformation l1 on l1.username =c.created_by\r\n" + 
							"						where civilian_status='R'" +q1+q2+q3+q4+q5+q6+q7+" order by c.id desc";
				}
				
				if(status == 1)	
				{
					q="select c.id,c.employee_no,c.sus_no as unit_sus_no,orb.unit_name,c.first_name ||' '|| middle_name ||' '|| last_name as name,ltrim(TO_CHAR(c.dob,'DD-MON-YYYY'),'0') as dob,c.civ_type,c.cancel_status,c.reject_remarks,c.employee_no,(select count(*) from tb_psg_civilian_dtl where status in(0,3) and main_id = c.main_id) app from tb_psg_civilian_dtl c\n" + 
							" 						inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.sus_no and orb.status_sus_no='Active'\n" + 
							"						left join logininformation l1 on l1.username =c.created_by\r\n" + 
							"						where civilian_status='R' "+q1 +q2 +q3 +q4 +q5 +q6+q7+"order by c.id desc" ;
			
				
				}
				
				
			  stmt=conn.prepareStatement(q);
			
			  int i=1;
			  
			  if(!roleSusNo.equals("")) {
				  stmt.setString(i,roleSusNo);
				  i+=1;
				  }
			  
			  if(!unit_sus_no.equals("")) {
			  stmt.setString(i,unit_sus_no);
			  i+=1;
			  }
			  if(!first_name.equals("")) {
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
				  i+=1;
				  }
			 
			  if(status!= -1 ) {
					stmt.setInt(i,status);
					  i+=1;
				}
			  
			  if(!personnel_no.equals("")) {
				  stmt.setString(i,personnel_no);
				  i+=1;
				  }
			  if (!cr_by.equals("")) {
					stmt.setString(i, cr_by);
					i += 1;
				}
				if (!cr_date.equals("")) {
						stmt.setString(i, cr_date);
						i += 1;
					}
				
				
				System.err.println("s/a regular :\n"+stmt);
			  ResultSet rs = stmt.executeQuery();  
		   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("id"));
		    	  list.add(rs.getString("employee_no"));
		    	  list.add(rs.getString("name"));
					
		    	  if(rs.getString("dob")!=null) {
						list.add(rs.getString("dob"));
						}
						else
							list.add("");		
		    	  list.add(rs.getString("unit_sus_no"));
		    	  list.add(rs.getString("reject_remarks"));

					String f1 = "";
					String f2 = "";
					String f3 = "";
					String f4 = "";
					String f5 = "";
					String f6 = "";
					String f7 = "";
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 0) {
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Details?') ){deleteData("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 1) {
						if(rs.getString("app").equals("0"))
						{
							String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){AppeditData("
									+ rs.getInt("id")+")}else{ return false;}\"";
							f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						}
						String view1 = "onclick=\"  {AppViewData("
								+ rs.getInt("id")+")}\"";
						f4 = "<i class='fa fa-eye'  " + view1 + " style='margin-left: 10px;' title='View Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 4) {
						if(rs.getInt("cancel_status") == -1) {
							String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelCivilianData("
									+ rs.getInt("id") + ")}else{ return false;}\"";
							 f5="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'>"
							 		+ "<i class='fa fa-times'></i></a>";
							 
							 String view1 = "onclick=\"  {AppViewData("
										+ rs.getInt("id")+")}\"";
								f4 = "<i class='fa fa-eye'  " + view1 + " style='margin-left: 10px;' title='View Data'></i>";
						}
						else {
							String view1 = "onclick=\"  {AppViewData("
									+ rs.getInt("id")+")}\"";
							f4 = "<i class='fa fa-eye'  " + view1 + " style='margin-left: 10px;' title='View Data'></i>";
						}
					}
					
					if (roleType.equals("ALL") || roleType.equals("DEO") && status == 3) {
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 0)) {
						String view = "onclick=\"  {viewData("
								+ rs.getInt("id")+")}\"";
						f3 = "<i class='action_icons action_approve'  " + view + " style='margin-left: 10px;' title='Approve Data'></i>";
					}
					
					if (roleType.equals("ALL") ||roleType.equals("APP") && (status == 1 || status == 3 || status == 4)) {
						String view1 = "onclick=\"  {AppViewData("
								+ rs.getInt("id")+")}\"";
						f4 = "<i class='fa fa-eye'  " + view1 + " style='margin-left: 10px;' title='View Data'></i>";
					}
					
					if (roleType.equals("ALL") || roleType.equals("APP") && (status == 4)) {
						if(rs.getInt("cancel_status") == 0) {
						String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve Cancel Details ?') ){AppCancelData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f6 = "<i class='action_icons action_approve'  " + Approve + " style='margin-left: 10px;' title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are You Sure You Want to Reject Cancel Details ?') ){RejCancelData("
								+ rs.getInt("id")+")}else{ return false;}\"";
						f7 = "<i class='action_icons action_reject'  " + Reject + " style='margin-left: 10px;' title='Reject Data'></i>";
						}
					 
					}
					if (roleType.equals("ALL") || roleType.equals("APP") && status == 0) {
						String view = "onclick=\"  {viewData("
								+ rs.getInt("id")+")}\"";
						f3 = "<i class='action_icons action_approve'  " + view + " style='margin-left: 10px;' title='Approve Data'></i>";
					}
					list.add(f1);
					list.add(f2);
					list.add(f3);
					list.add(f5);
					list.add(f4);
					list.add(f6);
					list.add(f7);
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
	public ArrayList<ArrayList<String>> Search_Civilian() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				q="select first_name ||' '|| middle_name ||' '|| last_name as name,dob,civ_type as name from tb_psg_civilian_dtl" ;

		      ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("id"));
		    	  list.add(rs.getString("name"));
					
					list.add(rs.getString("dob"));					
					list.add(rs.getString("civ_type"));

					String f2 = "";						

					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("id")+ ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
					
					list.add(f2);
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
	public TB_CIVILIAN_DETAILS getTB_CIVILIAN_DETAILSByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	TB_CIVILIAN_DETAILS updateid = (TB_CIVILIAN_DETAILS) sessionHQL.get(TB_CIVILIAN_DETAILS.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	@Override
	public ArrayList<ArrayList<String>> Report_Civilian_regular(String unit_sus_no, String first_name) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";		
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				if(unit_sus_no !=null  && !unit_sus_no.equals("")) {
					qry += "  and cd.sus_no = ?";
				}
				if(first_name !=null  && !first_name.equals("")) {
					qry +=" and  upper(cd.first_name)  like ?  ";
				}

				q="select  t.pay_head,count(cd.id) filter(where cd.gender='6' and cd.civ_type='1' ) as indmale,\n" + 
						"count(cd.id) filter(where cd.gender='7' and cd.civ_type='1' ) as indfemale,\n" + 
						"count(cd.id) filter(where cd.gender='6' and cd.civ_type='2' ) as nonindmale,\n" + 
						"count(cd.id) filter(where cd.gender='7' and cd.civ_type='2' ) as nonindfemale\n" + 
						"from (select * from tb_psg_civilian_dtl cd where cd.civilian_status='NR' "+qry+") cd  right join \n" + 
						"(select * from tb_psg_mstr_pay_head t ) t on cast(cd.pay_level as text)=cast(t.id as text) \n" + 
						"group by t.pay_head,t.id order by t.id";
				 stmt=conn.prepareStatement(q);
				 
		      int i = 1;
		      if(unit_sus_no !=null  && !unit_sus_no.equals("")) {
				  stmt.setString(i,unit_sus_no);
				  i+=1;
				  }
				  if(first_name !=null  && !first_name.equals("")) {
					  stmt.setString(i,"%"+first_name.toUpperCase()+"%");
					  i+=1;
					  }
				  
			ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(rs.getString("pay_head"));
		    	  list.add(rs.getString("indmale"));
					list.add(rs.getString("indfemale"));					
					list.add(rs.getString("nonindmale"));
					list.add(rs.getString("nonindfemale"));

					
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
	
	//===================================view non regular========================================//
	
	public List<Map<String, Object>> getApproveDataForviewnonregular(int id)
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
				qry="select tc.service_other,tc.id,tc.civilian_status,tc.main_id,tc.cancel_status,tc.authority,TO_CHAR(tc.dt_of_authority ,'DD-MON-YYYY') as dt_of_authority,tc.sus_no,tc.first_name,tc.middle_name,tc.last_name,TO_CHAR(dob ,'DD-MON-YYYY') as dob ,\n" + 
						"g.gender_name as gender,tc.gender_other,td.label as classification_services ,\n" + 
						"td8.label as civ_group ,cat.category as category_belongs ,\n" + 
						"td2.label as service_status ,td3.label as classification_trade ,\n" + 
						"td4.label as civ_type ,td7.label as whether_ex_serviceman ,dis.disability as whether_person_disability,\n" + 
						"crpm.description as post_initialy_appointed ,\n" + 
						"TO_CHAR(joining_date_gov_service ,'DD-MON-YYYY') as joining_date_gov_service ,TO_CHAR(date_of_tos ,'DD-MON-YYYY') as date_of_tos ,\n" + 
						"crp.description as designation ,TO_CHAR(designation_date ,'DD-MON-YYYY') as designation_date , \n" + 
						"ph.pay_head as pay_level ,\n" + 
						"tc.father_name , \n" + 
						"tc.mother_name ,c.name as country_original ,\n" + 
						"s.state_name as state_original ,d.district_name as district_original ,\n" + 
						"ct.city_name as tehsil_origin ,cn.name as country_present ,\n" + 
						"st.state_name as state_present ,ds.district_name as district_present ,\n" + 
						"t.city_name as tehsil_present ,n.nationality_name as nationality ,\n" + 
						"r.religion_name as religion ,m.mothertounge as mother_tongue ,cc.cadre,uc.unit_name,tc.original_country_other,tc.original_state_other,tc.original_district_other,tc.original_tehshil_other,\n" + 
						"tc.present_country_other,tc.present_state_other,tc.present_district_other,tc.present_tehshil_other,tc.classification_trade_other,tc.religion_other,\n" + 
						"tc.mother_tongue_other,tc.nationality_other,\n" + 
						"PGP_SYM_DECRYPT(tc.aadhar_card ::bytea,current_setting('miso.version'))  as \n" + 
						"aadhar_card ,PGP_SYM_DECRYPT(tc.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td5.causes_name as non_effective ,TO_CHAR(date_non_effective ,'DD-MON-YYYY') as date_non_effective,tc.employee_no,tc.pay_level_other\n" + 
						"from tb_psg_civilian_dtl tc \n" + 
						"left join tb_psg_mstr_gender g ON g.id::text = tc.gender::text\n" + 
						"left join t_domain_value td ON td.codevalue::text = tc.classification_services::text and td.domainid='CLASSIFICATION_OF_SERVICES'\n" + 
						"left join tb_psg_mstr_category cat on cat.id = tc.category_belongs\n" + 
						"left join t_domain_value td2 ON td2.codevalue::text = tc.service_status::text and td2.domainid='SERVICE_STATUS' \n" + 
						"left join t_domain_value td3 ON td3.codevalue::text = tc.classification_trade::text and td3.domainid='CLASIFICATION_OF_TRADES' \n" + 
						"left join t_domain_value td4 ON td4.codevalue::text = tc.civ_type::text and td4.domainid='CIVILIAN_TYPE'\n" + 
						"left join t_domain_value td7 ON td7.codevalue::text = tc.whether_ex_serviceman::text and td7.domainid='EX_SERVICEMAN' \n" + 
						"left join tb_psg_mstr_disability dis on cast(dis.id as character varying)  = tc.whether_person_disability\n" + 
						"left join t_domain_value td8 ON td8.codevalue::text = tc.civ_group::text and td8.domainid='CIV_R_GROUP' \n" + 
						"left join tb_psg_mstr_pay_head ph on ph.id = tc.pay_level\n" + 
						"left join cue_tb_psg_rank_app_master crpm ON crpm.id::text = tc.post_initialy_appointed::text \n" + 
						"left join cue_tb_psg_rank_app_master crp ON crp.id::text = tc.designation::text \n" + 
						"left join tb_psg_mstr_country c ON c.id::text = tc.country_original::text  \n" + 
						"left join tb_psg_mstr_state s ON s.state_id::text = tc.state_original::text  \n" + 
						"left join tb_psg_mstr_district d ON d.district_id::text = tc.district_original::text  \n" + 
						"left join tb_psg_mstr_city ct ON ct.city_id::text = tc.tehsil_origin::text \n" + 
						"left join tb_psg_mstr_country cn ON cn.id::text = tc.country_present::text  \n" + 
						"left join tb_psg_mstr_state st ON st.state_id::text = tc.state_present::text  \n" + 
						"left join tb_psg_mstr_district ds ON ds.district_id::text = tc.district_present::text \n" + 
						"left join tb_psg_mstr_city t ON t.city_id::text = tc.tehsil_present::text \n" + 
						"left join tb_psg_mstr_nationality n ON n.nationality_id::text = tc.nationality::text  \n" + 
						"left join tb_psg_mstr_religion r ON r.religion_id::text = tc.religion::text \n" + 
						"left join tb_psg_mothertounge m ON m.id::text = tc.mother_tongue::text \n" + 
						"left join tb_psg_mstr_cadre_civilian cc ON cc.id::text = tc.cadre::text \n" + 
						"left join tb_miso_orbat_unt_dtl uc ON uc.sus_no::text = tc.sus_no::text \n" + 
						"left join tb_psg_mstr_cause_of_non_effective_civilian td5 ON td5.id::text = tc.non_effective::text "
//						+ "and td5.type_of_regular_or_nonregular='REGULAR' "
						+ "WHERE tc.id=? order by tc.id";
			
		
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,id);
			
		
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
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
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
		//===================================view regular========================================//
		
	public List<Map<String, Object>> getAllAppDataForviewregular(int id)
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
				qry="select tc.service_other,tc.id,PGP_SYM_DECRYPT(tc.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,tc.civilian_status,tc.main_id,tc.cancel_status,tc.authority,TO_CHAR(tc.dt_of_authority ,'DD-MON-YYYY') as dt_of_authority,tc.sus_no,tc.first_name,tc.middle_name,tc.last_name,TO_CHAR(dob ,'DD-MON-YYYY') as dob ,\n" + 
						"g.gender_name as gender,tc.gender_other,td.label as classification_services ,\n" + 
						"td8.label as civ_group ,cat.category as category_belongs ,\n" + 
						"td2.label as service_status ,td3.label as classification_trade ,\n" + 
						"td4.label as civ_type ,td7.ex_servicemen as whether_ex_serviceman ,dis.disability as whether_person_disability,\n" + 
						"crpm.description as post_initialy_appointed ,\n" + 
						"TO_CHAR(joining_date_gov_service ,'DD-MON-YYYY') as joining_date_gov_service ,TO_CHAR(date_of_tos ,'DD-MON-YYYY') as date_of_tos ,\n" + 
						"crp.description as designation ,TO_CHAR(designation_date ,'DD-MON-YYYY') as designation_date , \n" + 
						"p.pay_level as pay_level ,\n" + 
						"tc.father_name , \n" + 
						"tc.mother_name ,upper(c.name) as country_original ,\n" + 
						"upper(s.state_name) as state_original ,upper(d.district_name) as district_original ,\n" + 
						"upper(ct.city_name) as tehsil_origin ,cn.name as country_present ,\n" + 
						"upper(st.state_name) as state_present ,upper(ds.district_name) as district_present ,\n" + 
						"upper(t.city_name) as tehsil_present ,n.nationality_name as nationality ,\n" + 
						"r.religion_name as religion ,m.mothertounge as mother_tongue ,cc.cadre,uc.unit_name,tc.original_country_other,tc.original_state_other,tc.original_district_other,tc.original_tehshil_other,\n" + 
						"tc.present_country_other,tc.present_state_other,tc.present_district_other,tc.present_tehshil_other,tc.classification_trade_other,tc.religion_other,q.examination_passed as qualification,\n" + 
						"tc.mother_tongue_other,tc.nationality_other,\n" + 
						"PGP_SYM_DECRYPT(tc.aadhar_card ::bytea,current_setting('miso.version'))  as \n" + 
						"aadhar_card ,PGP_SYM_DECRYPT(tc.pan_no ::bytea,current_setting('miso.version'))  as pan_no,td5.causes_name as non_effective ,TO_CHAR(date_non_effective ,'DD-MON-YYYY') as date_non_effective,tc.employee_no\n" + 
						"from tb_psg_civilian_dtl tc \n" + 
						"left join tb_psg_mstr_gender g ON g.id::text = tc.gender::text\n" + 
						"left join t_domain_value td ON td.codevalue::text = tc.classification_services::text and td.domainid='CLASSIFICATION_OF_SERVICES'\n" + 
						"left join tb_psg_mstr_category cat on cat.id = tc.category_belongs\n" + 
						"left join t_domain_value td2 ON td2.codevalue::text = tc.service_status::text and td2.domainid='SERVICE_STATUS' \n" + 
						"left join t_domain_value td3 ON td3.codevalue::text = tc.classification_trade::text and td3.domainid='CLASIFICATION_OF_TRADES' \n" + 
						"left join t_domain_value td4 ON td4.codevalue::text = tc.civ_type::text and td4.domainid='CIVILIAN_TYPE'\n" + 
						"left join tb_psg_mstr_exservicemen td7 ON td7.id::text = tc.whether_ex_serviceman::text  \n" + 
						"left join tb_psg_mstr_disability dis on cast(dis.id as character varying) = tc.whether_person_disability\n" + 
						"left join t_domain_value td8 ON td8.codevalue::text = tc.civ_group::text and td8.domainid='CIV_R_GROUP' \n" + 
						"left join cue_tb_psg_rank_app_master crpm ON crpm.id::text = tc.post_initialy_appointed::text \n" + 
						"left join cue_tb_psg_rank_app_master crp ON crp.id::text = tc.designation::text \n" + 
						"left join tb_psg_mstr_pay_level p ON p.id = tc.pay_level\n" + 
						"left join tb_psg_mstr_country c ON c.id::text = tc.country_original::text  \n" + 
						"left join tb_psg_mstr_state s ON s.state_id::text = tc.state_original::text  \n" + 
						"left join tb_psg_mstr_district d ON d.district_id::text = tc.district_original::text  \n" + 
						"left join tb_psg_mstr_city ct ON ct.city_id::text = tc.tehsil_origin::text \n" + 
						"left join tb_psg_mstr_country cn ON cn.id::text = tc.country_present::text  \n" + 
						"left join tb_psg_mstr_state st ON st.state_id::text = tc.state_present::text  \n" + 
						"left join tb_psg_mstr_district ds ON ds.district_id::text = tc.district_present::text \n" + 
						"left join tb_psg_mstr_city t ON t.city_id::text = tc.tehsil_present::text \n" + 
						"left join tb_psg_mstr_nationality n ON n.nationality_id::text = tc.nationality::text  \n" + 
						"left join tb_psg_mstr_religion r ON r.religion_id::text = tc.religion::text \n" + 
						"left join tb_psg_mothertounge m ON m.id::text = tc.mother_tongue::text \n" + 
						"left join tb_psg_mstr_cadre_civilian cc ON cc.id::text = tc.cadre::text \n" + 
						"left join tb_miso_orbat_unt_dtl uc ON uc.sus_no::text = tc.sus_no::text \n" + 
						"left join tb_psg_mstr_examination_passed q ON q.id::text = tc.qualification::text \n" + 
						"left join tb_psg_mstr_cause_of_non_effective_civilian td5 ON td5.id::text = tc.non_effective::text and td5.type_of_regular_or_nonregular='REGULAR'WHERE tc.id=? order by tc.id" ;
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,id);	
		    ResultSet rs = null ;
		    try {
		    	
		    	System.err.println("print regular civ: \n\n" +stmt);
				rs = stmt.executeQuery();      
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
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
	
		
		
public String getEmpNoAlreadyExits(String employee_no) {
			
			Connection conn = null;
			String msg = "false";
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				sql = " select case when count(id) > 0 then false else true end from tb_psg_civilian_dtl where employee_no=? ";

				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setString(1, employee_no);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					msg = rs.getString("case");
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
			return msg;
		}


		public ArrayList<List<String>> GetEmployeeNoData(String employee_no){
			ArrayList<List<String>> list = new ArrayList<List<String>>();
			Connection conn = null;
			String q="";
			String qry="";		
			try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;

					q="select c.id,c.dob,concat(c.first_name ,' ',  c.middle_name ,' ', c.last_name)  as name,c.sus_no,r.description as rank,c.joining_date_gov_service \n" + 
							"					FROM tb_psg_civilian_dtl_main c\n" + 
							"					left join cue_tb_psg_rank_app_master r on r.id = c.designation\n" + 
							"					where upper(c.employee_no)=? and\n" + 
							"					(c.status='1') order by c.employee_no ";
					 stmt=conn.prepareStatement(q);
					 
					  stmt.setString(1, employee_no);
					  
				ResultSet rs = stmt.executeQuery();   	      
			      while (rs.next()) {
			    	  List<String> alist = new ArrayList<String>();
			    	  alist.add(rs.getString("id"));
			    	  alist.add(rs.getString("dob"));
			    	  alist.add(rs.getString("name"));
			    	  alist.add(rs.getString("sus_no"));					
			    	  alist.add(rs.getString("rank"));
			    	  alist.add(rs.getString("joining_date_gov_service"));

			    	  list.add(alist);
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
		public Boolean getEmpNoAlreadyExist(String emp_no) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			String queryP = "select count(id) from TB_CIVILIAN_DETAILS where UPPER(employee_no) =UPPER(:employee_no) and status <> 2";		
			

			Query q = session.createQuery(queryP);
			q.setParameter("employee_no", emp_no);		

			
			Long c = (Long) q.uniqueResult();
		
			tx.commit();
			session.close();
			if (c == 0 ) {
				return true;
			} else {
				return false;
			}
		}
		
		
		
		public String getRegularIdentityImagePath(String id) {
			String whr="";
			Connection conn = null;
			try {	
				
				if(id.equals("")) {
					id = "0";
				}
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select identity_image,id from tb_psg_civilian_dtl where id=? ";	
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
}
