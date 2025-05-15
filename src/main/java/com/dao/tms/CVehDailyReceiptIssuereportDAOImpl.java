package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_REPORT;
import com.persistance.util.HibernateUtil;

public class CVehDailyReceiptIssuereportDAOImpl implements CVehDailyReceiptIssuereportDAO{

	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public Boolean ifExistSusAndSer(String sus_no, String drr_dir_ser_no) {
		String q = "from TB_TMS_EMAR_DRR_DIR_MAIN where sus_no =:sus_no and drr_dir_ser_no=:drr_dir_ser_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
    
    	Query query = session.createQuery(q);
   		query.setParameter("sus_no", sus_no).setParameter("drr_dir_ser_no", drr_dir_ser_no);
   	    @SuppressWarnings("unchecked")
		List<TB_TMS_EMAR_DRR_DIR_MAIN> list = (List<TB_TMS_EMAR_DRR_DIR_MAIN>) query.list();
   		tx.commit();
   		session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}

	public Boolean ifExistSusAndSerDtl(String a, String em_no) {
		String q = "from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition =:a and em_no=:em_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
	   	query.setParameter("a", a).setParameter("em_no", em_no);
	   	@SuppressWarnings("unchecked")
		List<TB_TMS_EMAR_DRR_DIR_DTL> list = (List<TB_TMS_EMAR_DRR_DIR_DTL>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}

	public Boolean ifExistEmarEMNo(String em_no) {
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
	public Boolean ifExistSusAndSerAndEMNo(String sus_no, String drr_dir_ser_no, String em_no,String iss_cond) {
		String q = "from TB_TMS_EMAR_DRR_DIR_DTL where sus_no =:sus_no and drr_dir_ser_no=:drr_dir_ser_no and em_no=:em_no and issue_condition = :issue_condition";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
    	Query query = session.createQuery(q);
   		query.setParameter("sus_no", sus_no).setParameter("drr_dir_ser_no", drr_dir_ser_no).setParameter("em_no", em_no).setParameter("issue_condition", iss_cond);
   	    @SuppressWarnings("unchecked")
		List<TB_TMS_EMAR_DRR_DIR_DTL> list = (List<TB_TMS_EMAR_DRR_DIR_DTL>) query.list();
   		tx.commit();
   		session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}
	
	public  ArrayList<List<String>> getsearch_drr_c_Veh(String status,String sus_no,String from_date,String curr_date,String roleType)
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
		   if(status.equals("3")) {
			   sql = "select distinct d.sus_no, d.drr_dir_ser_no,d.issue_condition,ltrim(TO_CHAR(d.creation_date ,'dd-mm-yyyy'),'0') as creation_date,d.status,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = d.sus_no and status_sus_no = 'Active') from TB_TMS_EMAR_DRR_DIR_DTL d";
			}else {
				sql = "select distinct d.sus_no, d.drr_dir_ser_no,d.issue_condition,ltrim(TO_CHAR(d.creation_date ,'dd-mm-yyyy'),'0') as creation_date ,d.status,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = d.sus_no and status_sus_no = 'Active') from TB_TMS_EMAR_DRR_DIR_DTL d where "+ qry1 + " ";
			}
		   
		   stmt=conn.prepareStatement(sql);
		   if(!status.equals("3"))
		   {
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
				}else {
					stmt.setString(j, date);
				}
		   }
		
		   ResultSet rs = stmt.executeQuery();
		   while(rs.next()){
			   List<String> list = new  ArrayList<String>();
			   list.add(rs.getString("sus_no"));
			   list.add(rs.getString("drr_dir_ser_no"));
			   String type_of_issue = "";
			   if(rs.getString("issue_condition").equals("1") )
			   {
				   type_of_issue = "Free Stock";
			   }
			   else if(rs.getString("issue_condition").equals("2") )
			   {
				   type_of_issue ="Issued To Unit";
			   }
			   else if(rs.getString("issue_condition").equals("3") )
			   {
				   type_of_issue = "Received From Unit";
			   }
			   else if(rs.getString("issue_condition").equals("4") )
			   {
				   type_of_issue = "Auctioned ";
			   }
			   list.add(type_of_issue);
			   list.add(rs.getString("creation_date"));
			   list.add(rs.getString("status"));
			   list.add(rs.getString("unit_name"));
			   
			   String View = "onclick=\" View('"+rs.getString("drr_dir_ser_no")+"','"+rs.getString("status")+"','"+from_date+"','"+status+"','"+rs.getString("sus_no")+"','"+from_date+"','"+curr_date+"')\"";
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
	public List<String> getCEmarVehSusBaNoDtlListCase2AgaiIssue(String sus_no)
  	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try{	 
				 
  				conn = dataSource.getConnection();
  			    String sql="";
  			 
  			  sql = "select distinct d.id,d.em_no from tb_tms_emar_drr_dir_dtl d where d.issue_condition ='3' and status !='0' and d.em_no not in (select em_no from tb_tms_emar_report) and \r\n" + 
  			  		" d.em_no not in (select e.em_no from tb_tms_emar_drr_dir_dtl e where e.classification > cast('4' as varchar)) and  d.em_no not in(select em_no\r\n" + 
  			  		"  from tb_tms_emar_drr_dir_dtl where sus_no=? and issue_condition='4' and (status='0' or status = '1')) and  \r\n" + 
  			  		"d.em_no not in(select em_no from tb_tms_emar_drr_dir_dtl where sus_no=? and issue_condition='2' and status='0' ) and sus_no =? and d.id in\r\n" + 
  			  		"(select id from(select em_no,max(id) as id from (select distinct d.id,d.em_no,d.sus_no from tb_tms_emar_drr_dir_dtl d where d.issue_condition ='3' and status !='0' and d.em_no not in (select em_no from tb_tms_emar_report) and \r\n" + 
  			  		"d.em_no not in (select e.em_no from tb_tms_emar_drr_dir_dtl e where e.classification > cast('4' as varchar)) and  d.em_no not in(select em_no\r\n" + 
  			  		" from tb_tms_emar_drr_dir_dtl where sus_no=d.sus_no and issue_condition='4' and (status='0' or status = '1')))a group by 1)ca)";
  			   
  				PreparedStatement stmt=conn.prepareStatement(sql);
  				stmt.setString(1,sus_no);
  				stmt.setString(2,sus_no);
  				stmt.setString(3,sus_no);
  				ResultSet asm = stmt.executeQuery();			
  			    
			while(asm.next()){
				String em_no = asm.getString("em_no");
	    	    list.add(em_no);  	              
			}
			asm.close();
			stmt.close();
			conn.close();
		}catch (SQLException e) {
		   e.printStackTrace();
		} 
		return list;
  	}  
	
	public Boolean ifExistSusAndSerDtNIssueType(String a, String ba_no,String issue_type) {
		String q = "from TB_TMS_EMAR_DRR_DIR_DTL where issue_condition =:a and em_no=:ba_no and issue_type = :issue_type ";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
	   	query.setParameter("a", a).setParameter("ba_no", ba_no);
	   	query.setParameter("a", a).setParameter("issue_type", issue_type);
	   	List<TB_TMS_EMAR_DRR_DIR_DTL> list = (List<TB_TMS_EMAR_DRR_DIR_DTL>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}
	
	public  ArrayList<List<String>> getApprovedBA_NoFromDRRCVehicle(String viewSerNo,String viewStatus,String viewDate,String viewSus,String roleType)
  	{
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(viewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
			try{	  
			   conn = dataSource.getConnection();
			   String sql= ""; 
			   sql="select distinct a.id,a.em_no,a.sus_no,a.unit_sus_no,a.drr_dir_ser_no,a.issue_condition,a.status  ,b.unit_name\r\n" + 
			  		"	from TB_TMS_EMAR_DRR_DIR_DTL a\r\n" + 
			  		"	left join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active' " + 
			  		"	where a.drr_dir_ser_no= ?  and a.status= ? and CAST(creation_date as date) >= CAST(? as date) and a.sus_no = ?";
			   
			   PreparedStatement stmt=conn.prepareStatement(sql);
			   stmt.setString(1, viewSerNo);
			   stmt.setString(2, viewStatus);
			   stmt.setString(3,viewDate);
			   stmt.setString(4, viewSus);
			   ResultSet rs = stmt.executeQuery();
			
			   while(rs.next()){
				   	List<String> list = new  ArrayList<String>();
			   		list.add(rs.getString("id")); //0
			   		list.add(rs.getString("em_no")); //1
			   		list.add(rs.getString("sus_no")); //2
			   		list.add(rs.getString("unit_sus_no")); //3
			   		list.add(rs.getString("drr_dir_ser_no")); //4
			   		list.add(rs.getString("issue_condition")); //6
			   		list.add(rs.getString("status")); //7
			   		list.add(rs.getString("unit_name")); //8
			   		
			   	String editButton = "&nbsp;&nbsp;<i  onclick=Update('" + rs.getString("id") +"') title='View Data'>Edit</i>&nbsp;&nbsp;&nbsp;";
				String Delete = "onclick=\"  if (confirm('Are You Sure you want to Delete this Record?') ){Delete1('"+ rs.getString("id") +"','"+rs.getString("drr_dir_ser_no")+"','"+ rs.getString("issue_condition") +"','"+ rs.getString("em_no") +"','" + rs.getString("sus_no") +"')}else{ return false;}\"";
				String deleteButton ="<i  "+Delete+" title='Delete Data'>Delete</i>";
			   	String f="";
			   	
			   	if(rs.getString("status").equals("0")){
					if(roleType.equals("ALL")) {
						f += editButton;
						f += deleteButton;
					}
					if(roleType.equals("DEO")) {
						f += editButton;
						f += deleteButton;
					}
					if(roleType.equals("APP")) {
						f += deleteButton;
					}
				}
			   	else if(rs.getString("status").equals("1")){
					f += "Not Required";
				}
			   	else if(rs.getString("status").equals("2")){
					if(roleType.equals("DEO") || roleType.equals("ALL")) {
						f += deleteButton;
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
	
	
	public List<Map<String, String>> get_C_vech_daily(String sus_no_aprove, String ser_no_approve) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            String sql = "select a.unit_sus_no,c.std_nomclature,CAST(count(*) AS INT) as counts from tb_tms_emar_drr_dir_dtl a "
                                        + " inner join tb_tms_banum_dirctry b on   a.em_no=b.ba_no "
                                        + " inner join tb_tms_mct_master c on b.mct=c.mct "
                                        + " where a.drr_dir_ser_no=?   and   a.sus_no=? group by c.std_nomclature ,a.unit_sus_no ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ser_no_approve);
            stmt.setString(2, sus_no_aprove);
            
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
            return list;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException e) {    }
            }
        }
        return list;
    }
}