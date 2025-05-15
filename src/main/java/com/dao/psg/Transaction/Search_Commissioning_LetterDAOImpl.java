package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.persistance.util.HibernateUtil;

public class Search_Commissioning_LetterDAOImpl implements Search_Commissioning_LetterDAO{
	
private static final String Date = null;
private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_comm_letter(String unit_sus_no,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,String date_of_commission,String frm_dt1,String to_dt1,String roleType)
	{

	 
	 
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			qry = "p.id !=  0";
			if( !unit_sus_no.equals("")) {
				qry += " and upper(unit_sus_no) like ? ";		
			}
			if(!parent_arm.equals("") && !parent_arm.equals("0")) {
				qry += "  and p.parent_arm= ? ";				
			}
			if(!personnel_no.equals("")) {
				qry += "  and upper(personnel_no) like ? ";
			}			
			if(!name.equals("")) {
				qry += "  and upper(name) like ? ";
			}			
			if(status.equals("0")) {
				qry += " and upper(p.status) like ?";
			}
			//260194
			if(status.equals("1")) {
				qry += " and upper(p.status) IN ('1','5') ";
			}
			if(status.equals("3")) {
				qry += " and upper(p.status) like ?";
			}            
			if(!type_of_comm_granted.equals("0")) {
				qry += " and cast(type_of_comm_granted as character varying) like ? ";				
			}
			//26-01-1994
			if(!date_of_commission.equals("") && !date_of_commission.equals("DD/MM/YYYY")) {
				qry += " and cast(date_of_commission as date) = cast(? as date)";	
				
			}
			 if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY") && !to_dt1.equals("DD/MM/YYYY")){
					 qry += " and cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)";
			  }
				 q="select distinct \r\n" + 
						"p.id,\r\n" + 
						"p.cadet_no,\r\n" + 
						"p.personnel_no, \r\n" + 
						"r.description as rank, \r\n" + 
						"p.name,\r\n" + 
						"b.gender_name as gender, \r\n" + 
						"c.comn_name as type_of_comm_granted,\r\n" + 
						"ltrim(TO_CHAR(date_of_commission ,'DD-Mon-YYYY'),'0') as date_of_commission,\r\n" + 
						"ltrim(TO_CHAR(p.date_of_birth ,'DD-Mon-YYYY'),'0') as date_of_birth,\r\n" + 
						" arm.arm_desc as parent_arm, \r\n" + 
						"orb.unit_name as unit_sus_no,p.reject_remarks\r\n" + 
						"FROM tb_psg_trans_proposed_comm_letter p\r\n" + 
						"inner join tb_psg_mstr_type_of_commission c on c.id = p.type_of_comm_granted  and c.status='active'\r\n" + 
						"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = p.parent_arm \r\n" + 
						"inner join cue_tb_psg_rank_app_master r on r.id = p.rank  and r.status_active='Active'\r\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active'\r\n" + 
						"inner join tb_psg_mstr_gender b on b.id = p.gender and b.status='active' "+
						 " where  " + qry+ " order by id desc" ;
						 
						 
				
				
				
				
		
				
			
			stmt=conn.prepareStatement(q);
			if(!qry.equals(""))     
			{  int j =1;
				if( !unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no.toUpperCase()+"%");
					j += 1;						
				}				
				if( !parent_arm.equals("") &&  !parent_arm.equals("0")) {
					stmt.setString(j, parent_arm);
					j += 1;	
				} 
				if( !personnel_no.equals("")) {
					stmt.setString(j, personnel_no.toUpperCase()+"%");
					j += 1;	
				} 
				if(!name.equals("")) {
					stmt.setString(j, name.toUpperCase()+"%");
					j += 1;	
				}			 
				if(status.equals("0")) {				
					stmt.setString(j, status.toUpperCase()+"%");
					j += 1;	
				}
				//260194
//				if(status.equals("1")) {				
//					stmt.setString(j, status.toUpperCase()+"%");
//					j += 1;	
//				}
				if(status.equals("3")) {				
					stmt.setString(j, status.toUpperCase()+"%");
					j += 1;	
				}
				if(!type_of_comm_granted.equals("0")) {				
					stmt.setString(j, type_of_comm_granted.toUpperCase()+"%");
					j += 1;	
				}
				//26-01-1994
				if(!date_of_commission.equals("") && !date_of_commission.equals("DD/MM/YYYY")) {
					stmt.setString(j,date_of_commission);
					j += 1;	
				}
			if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY") && !to_dt1.equals("DD/MM/YYYY")){
	                stmt.setString(j, frm_dt1);
	                j += 1;	
	                stmt.setString(j, to_dt1);
	                j += 1;	
	        }
			}
			
	      ResultSet rs = stmt.executeQuery();   
	      
	     
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      int i = 0;
	      
	     
	      while (rs.next()) {
	    	  i++;
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("cadet_no"));
	    	  list.add(rs.getString("personnel_no"));
	    	  list.add(rs.getString("rank"));
	    	  list.add(rs.getString("name"));
	    	  list.add(rs.getString("gender"));
	    	  list.add(rs.getString("type_of_comm_granted"));
			  list.add(rs.getString("date_of_commission"));
			  list.add(rs.getString("date_of_birth"));
			  list.add(rs.getString("parent_arm"));
	    	  list.add(rs.getString("unit_sus_no"));
	    	 // list.add(rs.getString("reject_remarks"));
	    	  
				
				
			  
	  	
	  	//columns.put("chekboxaction", chekboxaction);
			String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";
				String f5 = "";
				String chekboxaction="";
				
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					
				
			    	  String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
						+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
				  
				     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
						+ "' value='" + rs.getObject(1) + "'   />";
				     chekboxaction+=Checkbox;
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("id")+ ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
					
					/*String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){Reject("+ rs.getString("id") + ","+i+")}else{ return false;}\"";*/
				
					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";
					
					f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
					
					String View = "onclick=\"  {viewData("+rs.getString("id")+ ")}\"";
				     f4 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
				}
				
				
				if(roleType.equals("ALL") || roleType.equals("APP") && (status.equals("1") || status.equals("3")))
				{
					String View = "onclick=\"  {viewData("+rs.getString("id")+ ")}\"";
				     f4 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
				     //VIEW
				     String Relegate = "onclick=\"  if (confirm('Are You Sure You Want to Relegate This Letter?') ){relegateData("+rs.getString("id")+ ")}else{ return false;}\"";
				     f5 = "<i class='fa fa-refresh' " + Relegate + " title='Relegate Data'></i>";
				}
				
				if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("1")))
				{
					String View = "onclick=\"  {viewData("+rs.getString("id")+ ")}\"";
				     f4 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
				     //VIEW
				
				}
				
				if(roleType.equals("ALL") || roleType.equals("DEO") && ((status.equals("0")) ||  (status.equals("3"))))
				{
					
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Letter?') ){deleteData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				}
				

			  
				
			
		    // chekboxaction+=CheckboxId;
			    
				list.add(f);
				list.add(f1);	
				list.add(f2);
				list.add(f3);
				list.add(f4);
				list.add(f5);
				list.add(rs.getString("reject_remarks"));
				list.add(chekboxaction);
				
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

	public TB_TRANS_PROPOSED_COMM_LETTER getSearch_com_letterByid(BigInteger id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_TRANS_PROPOSED_COMM_LETTER updateid = (TB_TRANS_PROPOSED_COMM_LETTER) sessionHQL.get(TB_TRANS_PROPOSED_COMM_LETTER.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	//,Date appoint_dt
	public String GetUpdateSearch_Comm_Letter(TB_TRANS_PROPOSED_COMM_LETTER obj,String username){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
       
       String msg = "";
     try{
    	 String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set authority=:authority,date_of_authority=:date_of_authority,"
					+ "cadet_no=:cadet_no,batch_no=:batch_no,"
					+ "course=:course,name=:name,gender=:gender,type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,"
					+ "date_of_seniority=:date_of_seniority,rank=:rank,date_of_rank=:date_of_rank,"
					+ "date_of_birth=:date_of_birth,parent_arm=:parent_arm,unit_sus_no=:unit_sus_no,regiment=:regiment,"
					+ "status=:status,date_of_tos=:date_of_tos,"
					+ "parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,"
					+ "modified_by=:modified_by,modified_date=:modified_date,tnai_no=:tnai_no where id=:id";
			
			
 		Query query = sessionHQL.createQuery(hql).setString("authority",obj.getAuthority()).setTimestamp("date_of_authority", obj.getDate_of_authority())
					.setString("cadet_no", obj.getCadet_no())
					.setString("batch_no", obj.getBatch_no())
					.setInteger("course",obj.getCourse())
					.setString("name",obj.getName())
					.setInteger("gender", obj.getGender())
					.setInteger("type_of_comm_granted", obj.getType_of_comm_granted())
					.setTimestamp("date_of_commission", obj.getDate_of_commission())
					.setTimestamp("date_of_seniority", obj.getDate_of_seniority())
					.setInteger("rank", obj.getRank())
					.setTimestamp("date_of_rank", obj.getDate_of_rank())
					.setTimestamp("date_of_birth", obj.getDate_of_birth())
					.setString("parent_arm", obj.getParent_arm())
					.setString("unit_sus_no", obj.getUnit_sus_no())
					.setString("regiment", obj.getRegiment())
					.setInteger("status", obj.getStatus())
					.setTimestamp("date_of_tos", obj.getDate_of_tos())
					.setString("parent_unit", obj.getParent_unit())
					.setString("parent_sus_no", obj.getParent_sus_no())
				   .setString("modified_by",username).setTimestamp("modified_date", new Date()).setString("tnai_no", obj.getTnai_no()).setBigInteger("id",obj.getId());
		  	
		
               msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
           
               tx.commit();
              
               
               TB_TRANS_PROPOSED_COMM_LETTER_HISTORY prx = new TB_TRANS_PROPOSED_COMM_LETTER_HISTORY();
               Session session1 = HibernateUtil.getSessionFactory().openSession();
               Transaction tx1 = session1.beginTransaction();
               String hql1 = "update TB_TRANS_PROPOSED_COMM_LETTER_HISTORY set authority=:authority,date_of_authority=:date_of_authority,personnel_no=:personnel_no,"
   					+ "cadet_no=:cadet_no,batch_no=:batch_no,"
   					+ "course=:course,name=:name,gender=:gender,type_of_comm_granted=:type_of_comm_granted,date_of_commission=:date_of_commission,"
   					+ "date_of_seniority=:date_of_seniority,rank=:rank,date_of_rank=:date_of_rank,"
   					+ "date_of_birth=:date_of_birth,parent_arm=:parent_arm,unit_sus_no=:unit_sus_no,regiment=:regiment,"
   					+ "status=:status,date_of_tos=:date_of_tos,"
   				    +"parent_sus_no=:parent_sus_no,parent_unit=:parent_unit,"
   					+ "modified_by=:modified_by,modified_date=:modified_date,tnai_no=:tnai_no where comm_his_id=:comm_his_id";
   			
   			
       		
			Query query1 = session1.createQuery(hql1).setString("authority",obj.getAuthority()).setTimestamp("date_of_authority", obj.getDate_of_authority())
   					.setString("personnel_no",obj.getPersonnel_no())
   					.setString("cadet_no", obj.getCadet_no())
   					.setString("batch_no", obj.getBatch_no())
   					.setInteger("course",obj.getCourse())
   					.setString("name",obj.getName())
   					.setInteger("gender", obj.getGender())
   					.setInteger("type_of_comm_granted", obj.getType_of_comm_granted())
   					.setTimestamp("date_of_commission", obj.getDate_of_commission())
   					.setTimestamp("date_of_seniority", obj.getDate_of_seniority())
   					.setInteger("rank", obj.getRank())
   					.setTimestamp("date_of_rank", obj.getDate_of_rank())
   					.setTimestamp("date_of_birth", obj.getDate_of_birth())
   					.setString("parent_arm", obj.getParent_arm())
   					.setString("unit_sus_no", obj.getUnit_sus_no())
   					.setString("regiment", obj.getRegiment())
   					.setInteger("status", obj.getStatus())
   					.setTimestamp("date_of_tos", obj.getDate_of_tos())
   				    .setString("parent_sus_no", obj.getParent_sus_no())
   				    .setString("parent_unit", obj.getParent_unit())
                    .setString("modified_by",username).setTimestamp("modified_date", new Date()).setString("tnai_no", obj.getTnai_no()).setBigInteger("comm_his_id",obj.getId());
                              
       			msg = query.executeUpdate() > 0 ? "update" : "0";
               
       			tx1.commit();
                session1.close();

                }
       catch (Exception e) {
               msg = "Data Not Updated.";
               tx.rollback();
       }
       finally {
               sessionHQL.close();
       }
       return msg;
}

	
	               
	public Boolean getPersonnelNoAlreadyExist(String personnel_no,BigInteger c_id) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "SELECT case when sum(count_pers.count) > 0 then false else true end FROM   \r\n" + 
					"(\r\n" + 
					"select count(id) from tb_psg_change_of_comission where upper(new_personal_no)=? and status =0  and cast(id as character varying) != ?\r\n" + 
					"union all  \r\n" + 
					"select count(id) from tb_psg_trans_proposed_comm_letter where personnel_no=?   \r\n" + 
					") count_pers";

			BigInteger id =BigInteger.ZERO;
			if(c_id != null && !c_id.equals(""))
			{
				id = c_id;
			}
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, personnel_no);
			stmt.setString(2, String.valueOf(id));
			stmt.setString(3, personnel_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				msg = rs.getBoolean("case");
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
	
	/// bisag 170822 v1 (converted to Datatable)
	public long GetSearch_Com_letterCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId ,String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,
			String date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType,Boolean IsMns) {
			
			String SearchValue = GenerateQueryWhereClause_SQL( Search, unit_sus_no, unit_posted_to,parent_arm, personnel_no, name, status, type_of_comm_granted, date_of_commission, frm_dt1, to_dt1,cr_by,cr_date,roleType); 
				int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				String qry="";
//				   if(roleAccess.equals("DGMS")) {
//			        	qry="and substr(p.personnel_no,1,2) in ('NR','NS') ";    // only nr and ns prefix personnel when roleaccess is dgms
//			        }
			     if(IsMns==true) {
			        	qry="and substr(p.personnel_no,1,2) in ('NR','NS') ";
			        }else if (IsMns==false) {
			        	qry="and substr(p.personnel_no,1,2) Not in ('NR','NS') ";
			        }
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					
				
				  q="select count(app.*) from(select distinct " + 
				  		"p.id," + 
				  		"p.cadet_no," + 
				  		"p.personnel_no, " + 
				  		"r.description as rank, " + 
				  		"p.name," + 
				  		"b.gender_name as gender, " + 
				  		"c.comn_name as type_of_comm_granted," + 
				  		"ltrim(TO_CHAR(date_of_commission ,'DD-Mon-YYYY'),'0') as date_of_commission," + 
				  	    "ltrim(TO_CHAR(p.date_of_birth ,'DD-Mon-YYYY'),'0') as date_of_birth, " + 
				  		" arm.arm_desc as parent_arm," + 
				  		"orb.unit_name as unit_sus_no,p.reject_remarks " + 
				  		"FROM tb_psg_trans_proposed_comm_letter p " + 
				  		"inner join tb_psg_mstr_type_of_commission c on c.id = p.type_of_comm_granted  and c.status='active' "+qry+" " + 
				  		"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = p.parent_arm " + 
				  		"inner join cue_tb_psg_rank_app_master r on r.id = p.rank  and r.status_active='Active' " + 
				  		"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active' " + 
				  		"inner join tb_psg_mstr_gender b on b.id = p.gender and b.status='active'" +
				  	    "left join logininformation l1 on l1.username = p.created_by " +SearchValue+") app " ;
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_posted_to,parent_arm, personnel_no, name,status, type_of_comm_granted,date_of_commission,frm_dt1, to_dt1,cr_by,cr_date,roleType);
				
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
			  stmt,String Search,String unit_sus_no,String unit_posted_to,String parent_arm,String
			  personnel_no,String name,String status,String type_of_comm_granted,String
			  date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType) {
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
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}

			if(!unit_sus_no.equals("")) {
				flag += 1;
				
				stmt.setString(flag, "%"+unit_sus_no.toUpperCase()+"%");
				
			}
			
			
			if(!unit_posted_to.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+unit_posted_to.toUpperCase()+"%");
			}
			
			if(!parent_arm.equals("0")) {
				
				flag += 1;
				stmt.setString(flag, "%"+parent_arm+"%");
			}
			if(!personnel_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+personnel_no.toUpperCase()+"%");
			}
			if(!name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+name.toUpperCase()+"%");
			}

			
			
        if(!type_of_comm_granted.equals("")) {
        
				flag += 1;
				stmt.setString(flag, type_of_comm_granted);
			} 
			if(!date_of_commission.equals("") && !date_of_commission.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_commission);
			}
			
			  if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY")  && !to_dt1.equals("DD/MM/YYYY")) { 
				  flag += 1; 	
				   stmt.setString(flag , frm_dt1);
				   flag += 1;	
	                stmt.setString(flag, to_dt1);
	                
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
		System.out.println(stmt+"====");
		return stmt;
		
	}

	 public String GenerateQueryWhereClause_SQL(String Search,String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,String date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType) {
			String SearchValue ="";
			if(!Search.equals("")) { // for Input Filter
				SearchValue =" where  ";
				SearchValue +="( upper(p.cadet_no) like ? or upper(personnel_no) like ? or upper(r.description) like ? "
						+ " or upper(p.name) like ? or upper(b.gender_name) like ? or upper(c.comn_name) like ? "
						+ "or upper(orb.unit_name) like ? or upper(arm.arm_desc)  like ?  "
						+ "  ) ";
			}
			
		
			
			
			
			if( !unit_sus_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(unit_sus_no) like ? ";	
				}
				else {
					SearchValue += " where upper(unit_sus_no) like ?";
				}
			}
			if( !unit_posted_to.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(orb.unit_name) like ? ";	
				}
				else {
					SearchValue += " where upper(orb.unit_name) like ?";
				}
			}
			if(!parent_arm.equals("") && !parent_arm.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += "  and p.parent_arm like ? ";
				}
				else {
					SearchValue += " where  p.parent_arm like ?";
				}
								
			}
			if(!personnel_no.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += "  and upper(personnel_no) like ? ";
				}
				else {
					SearchValue += "  where upper(personnel_no) like ? ";
				}
				
			}			
			if(!name.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += "  and upper(p.name) like ? ";
				}
				else {
					SearchValue += "  where upper(p.name) like ? ";
				}
				
			}		
			if(status.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(p.status) IN ('0') ";
				}
				else {
					SearchValue += " where upper(p.status) IN ('0') ";
				}
				
			}
			//260194
			if(status.equals("1")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(p.status) IN ('1','5') ";
				}
				else {
					SearchValue += " where upper(p.status) IN ('1','5') ";
				}
			
			}
			
		
			if(status.equals("3")) {
			
				if (SearchValue.contains("where")) {
					SearchValue += " and upper(p.status) IN ('3') ";
				}
				else {
					SearchValue += " where upper(p.status) IN ('3') ";
				}
				
			}            
			if(!type_of_comm_granted.equals("")) {
				
				if (SearchValue.contains("where")) {
				
					SearchValue += " and cast(p.type_of_comm_granted as character varying) = ? ";				
				}
				else {
				
					SearchValue += " where cast(p.type_of_comm_granted as character varying) = ? ";				
				}
				
			}
			//26-01-1994
			if(!date_of_commission.equals("") && !date_of_commission.equals("DD/MM/YYYY")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(date_of_commission as date) = cast(? as date)";	
				}
				else {
					SearchValue += " where cast(date_of_commission as date) = cast(? as date)";	
				}
				
				
			}
		
		  if(!frm_dt1.equals("") && !to_dt1.equals("") && !frm_dt1.equals("DD/MM/YYYY") && !to_dt1.equals("DD/MM/YYYY")){ 
			  if (SearchValue.contains("where")) {
		  SearchValue +=  " and cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"; 
		  } 
			else { SearchValue += " where cast(p.created_date as date) >= cast(? as date) and cast(p.created_date as date) <= cast(? as date)"; 
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
					SearchValue += " and cast(p.created_date as date) = cast(? as date)";	
				}
				else {
					SearchValue += " where cast(p.created_date as date) = cast(? as date)";	
				}
				
				
			}
			
			
			return SearchValue;
		}
	 
	 
	 
	 public List<Map<String, Object>> GetSearch_Com_letterdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId,
			 String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,String name,String status,String type_of_comm_granted,String date_of_commission,String frm_dt1,String to_dt1,String cr_by,String cr_date,String roleType,Boolean IsMns ) 
		{
			

			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			
	    	String SearchValue = GenerateQueryWhereClause_SQL(Search, unit_sus_no, unit_posted_to, parent_arm, personnel_no, name, status, type_of_comm_granted, date_of_commission, frm_dt1, to_dt1, cr_by, cr_date, roleType);
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";
        String qry="";
//        if(roleAccess.equals("DGMS")) {
//        	qry="and substr(p.personnel_no,1,2) in ('NR','NS') ";    // only nr and ns prefix personnel when roleaccess is dgms
//        }
        if(IsMns==true) {
        	qry="and substr(p.personnel_no,1,2) in ('NR','NS') ";
        }else if (IsMns==false) {
        	qry="and substr(p.personnel_no,1,2) Not in ('NR','NS') ";
        }
		
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
						" p.id," + 
						" p.cadet_no, " + 
						"p.personnel_no, " + 
					   " r.description as rank, " + 
						" p.name, " + 
						"b.gender_name as gender, " + 
					  "c.comn_name as type_of_comm_granted," + 
						" ltrim(TO_CHAR(date_of_commission ,'DD-Mon-YYYY'),'0') as date_of_commission," + 
						" ltrim(TO_CHAR(p.date_of_birth ,'DD-Mon-YYYY'),'0') as date_of_birth," + 
						" arm.arm_desc as parent_arm, " + 
						" orb.unit_name as unit_sus_no,p.reject_remarks " + 
					  " FROM tb_psg_trans_proposed_comm_letter p " + 
						" inner join tb_psg_mstr_type_of_commission c on c.id = p.type_of_comm_granted  and c.status='active' "+qry+" " + 
					   " inner join tb_miso_orbat_arm_code  arm on arm.arm_code = p.parent_arm " + 
						" inner join cue_tb_psg_rank_app_master r on r.id = p.rank  and r.status_active='Active'   " + 
						" inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = p.unit_sus_no and status_sus_no='Active' " + 
						" inner join tb_psg_mstr_gender b on b.id = p.gender and b.status='active' left join logininformation l1 on l1.username = p.created_by "
						+ ""+SearchValue + "order by id "  +
						" desc  limit " +pageL+" OFFSET "+startPage+"";
				
			
			
				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,unit_sus_no, unit_posted_to,parent_arm, personnel_no, name, status, type_of_comm_granted, date_of_commission, frm_dt1, to_dt1,cr_by,cr_date,roleType);
				
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
				String chekboxaction="";
				
					
				
			    	  String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
						+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
				  
				     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
						+ "' value='" + rs.getObject(1) + "'   />";
				     chekboxaction+=Checkbox;
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("id")+ ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
					
					/*String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){Reject("+ rs.getString("id") + ","+i+")}else{ return false;}\"";*/
				
					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";
					
					f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
					
					String View = "onclick=\"  {viewData("+rs.getString("id")+ ")}\"";
				     f4 = "<i class='fa fa-eye'  " + View + " title='View Data'></i>";
				
				
				
				   //VIEW
				     String Relegate = "onclick=\"  if (confirm('Are You Sure You Want to Delete All Data?') ){relegateData("+rs.getString("id")+ ")}else{ return false;}\"";
				     f5 = "<i class='fa fa-refresh' " + Relegate + " title='Delete All Data'></i>";
				
				
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Letter?') ){deleteData("
							+ rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

				


				if (roleType.equals("ALL") || roleType.equals("DEO")) {
					if (status.equals("0")) {
						f7 += f;
						f7 += f1;
						
					}
					if (status.equals("1")) {
						f7 += f4;
						
						
					}
					if (status.equals("3")) {

						f7 += f;
						f7 += f1;
					}
				}
				if (roleType.equals("ALL") || roleType.equals("APP")) {
					if (status.equals("1")) {
						f7 += f4;
						f7 += f5;
						
					}
					if (status.equals("3")) {
						f7 += f4;
						f7 += f5;
					}
					if (status.equals("0")) {
						
						f7 += f2;
						f7 += f3;
						f7 += f4;
						
						
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
	 
	 public Boolean getPersonnelNoAlreadyExistinpostinout(String personnel_no) {
			Connection conn = null;
			Boolean msg = false;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
				sql = "	select case when count(p.id)>=1 then true else false  end from tb_psg_trans_proposed_comm_letter c \r\n"
						+ "				       inner join tb_psg_posting_in_out p \r\n"
						+ "				       on p.comm_id =c.id\r\n"
						+ "				       where upper(personnel_no)=?";
				
			

				BigInteger id =BigInteger.ZERO;			
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.setString(1, personnel_no);		
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					msg = rs.getBoolean("case");
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
}
