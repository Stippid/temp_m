package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.persistance.util.HibernateUtil;

public class DailyReceiptIssuereportDAOimpl implements DailyReceiptIssuereportDAO{

	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public Boolean ifExistSusAndSer(String sus_no, String drr_dir_ser_no) {
		String q = "from TB_TMS_DRR_DIR_MAIN where sus_no =:sus_no and drr_dir_ser_no=:drr_dir_ser_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
    
    	Query query = session.createQuery(q);
   		query.setParameter("sus_no", sus_no).setParameter("drr_dir_ser_no", drr_dir_ser_no);
   	    List<TB_TMS_DRR_DIR_MAIN> list = (List<TB_TMS_DRR_DIR_MAIN>) query.list();
   		tx.commit();
   		session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}
	
	public Boolean ifExistSusAndSerAndBANo(String sus_no, String drr_dir_ser_no,String ba_no) {
		String q = "from TB_TMS_DRR_DIR_DTL where sus_no =:sus_no and drr_dir_ser_no=:drr_dir_ser_no and ba_no=:ba_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
    
    	Query query = session.createQuery(q);
   		query.setParameter("sus_no", sus_no).setParameter("drr_dir_ser_no", drr_dir_ser_no).setParameter("ba_no", ba_no);
   	    List<TB_TMS_DRR_DIR_DTL> list = (List<TB_TMS_DRR_DIR_DTL>) query.list();
   		tx.commit();
   		session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}

	public Boolean ifExistMVCRBaNo(String ba_no) {
		String q = "from TB_TMS_MVCR_PARTA_DTL where ba_no=:ba_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
	   	query.setParameter("ba_no", ba_no);
	   	@SuppressWarnings("unchecked")
		List<TB_TMS_MVCR_PARTA_DTL> list = (List<TB_TMS_MVCR_PARTA_DTL>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}

	public Boolean ifExistSusAndSerDtl(String a, String ba_no) {
		String q = "from TB_TMS_DRR_DIR_DTL where typ_of_retrn =:typ_of_retrn and type_of_issue=:type_of_issue and ba_no=:ba_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
   	    String aa = null;
   	    String bb = null;
   	    
   	    if(a.equals("1")) {
   	    	aa = "0";
   	    	bb = "1";	
	    }else if(a.equals("2")) {
	    	aa = "1";
   	    	bb = "0";
	    }else if(a.equals("3")) {
	    	aa = "0";
   	    	bb = "0";
	    }else if(a.equals("4")) {
	    	aa = "1";
   	    	bb = "1";
	    }
	   	query.setParameter("typ_of_retrn", aa).setParameter("type_of_issue", bb).setParameter("ba_no", ba_no);
	   	List<TB_TMS_DRR_DIR_DTL> list = (List<TB_TMS_DRR_DIR_DTL>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}
	/*NITIN V4 15/11/2022*/
	public ArrayList<List<String>> getsearch_drr_b_Veh( String status, String sus_no, String from_date,String curr_date, String roleType,String ba_no)	
  	{
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
			try{	  
			   conn = dataSource.getConnection();
			   PreparedStatement stmt=null;
			   String sql= ""; 
			
				String qry1="";
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
				if(!status.equals("")) {
					qry1 += " status = ?";
					
				}
				
				if(!sus_no.equals("")) {
					qry1 += " and sus_no = ?";
					
				}
				
				if(!from_date.equals("")) {
					qry1 += " and cast(creation_date as date) BETWEEN cast(? as date)";
					
				} 
				
				if(!curr_date.equals("")) {
					qry1 += " and cast(? as date) ";
				}else {
					qry1 += " and cast(? as date) ";
				}
				
				if(!ba_no.equals("")) {
					qry1 += " and ba_no = ?";
					
				}
			  
				sql = "select distinct d.sus_no, "
						+ "d.drr_dir_ser_no,"
						+ "d.typ_of_retrn,"
						+ "d.type_of_issue,"
						+ "ltrim(TO_CHAR( d.creation_date,'dd-mm-yyyy'),'0') as  creation_date,"
						+ "d.status,"
						+ "(select unit_name from tb_miso_orbat_unt_dtl where sus_no = d.sus_no and status_sus_no = 'Active') "
						+ "from "
						+ "TB_TMS_DRR_DIR_DTL d where "+ qry1 + "";
				
				stmt=conn.prepareStatement(sql);
				int j =1;
				if(!status.equals("")) {
					stmt.setString(j, status);
					j += 1;
				}
				if(!sus_no.equals("")) {
					stmt.setString(j, sus_no);
					j += 1;
				}
				if(!from_date.equals("")) {
					stmt.setString(j,  from_date );
					j += 1;
				} 
				if(!curr_date.equals("")) {
					stmt.setString(j, curr_date);
					j += 1;
				}else {
					stmt.setString(j, date);
					j += 1;
				}
				
				if(!ba_no.equals("")) {
					stmt.setString(j, ba_no);
				}
			
			   ResultSet rs = stmt.executeQuery();
			
			   while(rs.next()){
				  List<String> list = new  ArrayList<String>();
		    	  list.add(rs.getString("sus_no"));
		    	  list.add(rs.getString("drr_dir_ser_no"));
		    	  String type_of_issue = "";
		    	  if(rs.getString("typ_of_retrn").equals("0") && rs.getString("type_of_issue").equals("1") )
		    	  {
		    		  type_of_issue = "Free Stock";
		    	  }
		    	  else if(rs.getString("typ_of_retrn").equals("1") && rs.getString("type_of_issue").equals("0") )
		    	  {
		    		  type_of_issue ="Issued To Unit";
		    	  }
		    	  else if(rs.getString("typ_of_retrn").equals("0") && rs.getString("type_of_issue").equals("0"))
		    	  {
		    		  type_of_issue = "Receive From Unit";
		    	  }
		    	  else if(rs.getString("typ_of_retrn").equals("1") && rs.getString("type_of_issue").equals("1") )
		    	  {
		    		  type_of_issue = "Auction";
		    	  }
		    	  else
		    	  {
		    		  
		    	  }
		    	  list.add(type_of_issue);
		    	  list.add(rs.getString("creation_date"));
		    	  list.add(rs.getString("status"));
		    	  list.add(rs.getString("unit_name"));
				  String View = "onclick=\"  View('"+rs.getString("drr_dir_ser_no")+"','"+rs.getString("status")+"','"+rs.getString("creation_date")+"','"+status+"','"+sus_no+"','"+from_date+"','"+curr_date+"')\"";
			   	  String viewButton = "<i class='action_icons action_approve' "+View+" title='View Data'></i>";
				
						String f = "";
						if(status.equals("0")){
							if(roleType.equals("ALL")) {
								f += viewButton;
								
							}
							if(roleType.equals("APP")) {
								f += viewButton;
								
							}
							if(roleType.equals("DEO")) {
								f += viewButton;
							}
						}else if(status.equals("1")){
							f += viewButton;
						}else if(status.equals("2")){
							if(roleType.equals("DEO") || roleType.equals("ALL")) {
								f += viewButton;
							}
						}
						list.add(f);
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




	
	
	public  ArrayList<List<String>> getPendingBA_NoFromDRR(String viewSerNo,String viewStatus,String viewDate)
  	{
			    	
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
			try{	  
			   conn = dataSource.getConnection();
			   String sql= ""; 
			   sql="select distinct a.id,a.ba_no,a.sus_no,a.unit_sus_no,a.drr_dir_ser_no,a.typ_of_retrn,a.type_of_issue,a.status  ,b.unit_name\r\n" + 
			  		"	from TB_TMS_DRR_DIR_DTL a\r\n" + 
			  		"	left join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active' " + 
			  		"	where a.drr_dir_ser_no= ? and a.status= ? and ltrim(TO_CHAR(a.creation_date,'dd-mm-yyyy'),'0') = ? ";
			   
			   PreparedStatement stmt=conn.prepareStatement(sql);
			   stmt.setString(1, viewSerNo);
			   stmt.setString(2, viewStatus);
			   stmt.setString(3, viewDate);
			
			   ResultSet rs = stmt.executeQuery();
			
			   while(rs.next()){
				   	List<String> list = new  ArrayList<String>();
			   		list.add(rs.getString("id")); //0
			   		list.add(rs.getString("ba_no")); //1
			   		list.add(rs.getString("sus_no")); //2
			   		list.add(rs.getString("unit_sus_no")); //3
			   		list.add(rs.getString("drr_dir_ser_no")); //4
			   		list.add(rs.getString("typ_of_retrn")); //5
			   		list.add(rs.getString("type_of_issue")); //6
			   		list.add(rs.getString("status")); //7
			   		list.add(rs.getString("unit_name")); //8
			   		
			   			String f = "";
						list.add(f);
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
	public Boolean ifExistA_VEHBaNo(String ba_no) {
        String q = "from TB_TMS_CENSUS_RETURN where ba_no=:ba_no";
                 Session session = HibernateUtil.getSessionFactory().openSession();
                 Transaction tx = session.beginTransaction();
                 Query query = session.createQuery(q);
             query.setParameter("ba_no", ba_no);
             @SuppressWarnings("unchecked")
        List<TB_TMS_CENSUS_RETURN> list = (List<TB_TMS_CENSUS_RETURN>) query.list();
             tx.commit();
             session.close();
                 if(list.size() > 0) {
                     return true;
                 }else {
                     return false;
                 }
    }
    
    public Boolean ifExistC_VEHBaNo(String em_no) {
        String q = "from TB_TMS_EMAR_REPORT where em_no=:em_no";
                 Session session = HibernateUtil.getSessionFactory().openSession();
                 Transaction tx = session.beginTransaction();
                 Query query = session.createQuery(q);
             query.setParameter("em_no", em_no);
             @SuppressWarnings("unchecked")
        List<TB_TMS_EMAR_REPORT> list = (List<TB_TMS_EMAR_REPORT>) query.list();
             tx.commit();
             session.close();
                 if(list.size() > 0) {
                     return true;
                 }else {
                     return false;
                 }
    }
    
    public List<String> getUseridBysusfordepo(String sus_no){
    	List<String> list = new ArrayList<String>();
    	Connection conn = null;
    	String q = "";
    	try {
    	conn = dataSource.getConnection();
    	q = "select l.userid \r\n" + 
    	"from \r\n" + 
    	"logininformation l\r\n" + 
    	"inner join userroleinformation ur on l.userid = ur.user_id\r\n" + 
    	"inner join roleinformation r on ur.role_id = r.role_id\r\n" + 
    	"where l.user_sus_no = ?     and   r.role_type='APP'   \r\n" + 
    	"order by r.role";

    	PreparedStatement stmt = conn.prepareStatement(q);
    	stmt.setString(1, sus_no);
    	ResultSet rs = stmt.executeQuery();
    	while (rs.next()){
    	list.add( rs.getString("userid"));
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
    	} catch (SQLException e) {}
    	}
    	}
    	return list;
    	}
    
    public List<String> getUseridBysusforlinedte(String sus_no){
    	List<String> list = new ArrayList<String>();
    	Connection conn = null;
    	String q = "";
    	try {
    	conn = dataSource.getConnection();
    	q = "  select distinct l.userid,a.sus_no,b.line_dte_sus from tb_miso_orbat_unt_dtl a inner join tb_miso_orbat_line_dte b on a.arm_code=b.arm_code\r\n"
    			+ "						 inner join logininformation l on l.user_sus_no =b.line_dte_sus\r\n"
    			+ "						 where a.sus_no = ? and  b.line_dte_sus != ''";

    	PreparedStatement stmt = conn.prepareStatement(q);
    	stmt.setString(1, sus_no);
    	ResultSet rs = stmt.executeQuery();
    	while (rs.next()){
    	list.add( rs.getString("userid"));
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
    	} catch (SQLException e) {}
    	}
    	}
    	return list;
    	}
    
    
   
}