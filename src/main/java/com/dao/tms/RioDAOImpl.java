package com.dao.tms;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_VEHICLE_DTLS;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class RioDAOImpl implements RioDAO{

	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<TB_TMS_RO_VEHICLE_DTLS> getReportList1() {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("select  r.ro_no,r.sus_no,r.mct,r.quantity,r.depot_sus_no,r.type_of_release , r.date_of_submission,r.date_of_submission as validity,r.inliuemct,(select  total from cue_transport_ue_final where sus_no= r.sus_no and SUBSTRING(mct_no,1,4) = SUBSTRING(cast(r.mct as character varying), 1, 4) ) as ue,(select count(mct) from TB_TMS_BANUM_DIRCTRY where ba_no in(select ba_no from TB_TMS_MVCR_PARTA_DTL where sus_no = r.sus_no) and status='Active' and SUBSTRING(cast(mct as character varying),1,4) = SUBSTRING(cast(r.mct as character varying) ,1,4)) as UHfrom TB_TMS_RO_VEHICLE_DTLS r where r.date_of_submission = '2019-02-16' and r.sus_no='0101002B'");
    	@SuppressWarnings("unchecked")
    	List<TB_TMS_RO_VEHICLE_DTLS> list = (List<TB_TMS_RO_VEHICLE_DTLS>) q.list();
    	tx.commit();
    	return list;
	}
	
	public TB_TMS_RIO_VEHICLE_DTLS viewRio(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction(); 
		Query q = session.createQuery("from TB_TMS_RIO_VEHICLE_DTLS where id=:id");
		q.setParameter("id", id);
		TB_TMS_RIO_VEHICLE_DTLS list = (TB_TMS_RIO_VEHICLE_DTLS) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public List<TB_TMS_MVCR_PARTA_MAIN> getLastUpdateDateUnit(String sus_no) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("select approve_date from TB_TMS_MVCR_PARTA_MAIN where sus_no=:sus_no");
    	q.setParameter("sus_no", sus_no);
    	@SuppressWarnings("unchecked")
    	List<TB_TMS_MVCR_PARTA_MAIN> list = (List<TB_TMS_MVCR_PARTA_MAIN>) q.list();
    	tx.commit();
    	return list;
	}
	
	public String UpdateValidUpto(int upId,String ValidUptoVal) {
		int id = upId;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
        Date valid_upto = null;
        try {
        	valid_upto = formatter1.parse(ValidUptoVal);
		}  catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
        Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update TB_TMS_RIO_VEHICLE_DTLS c set c.valid_upto = :valid_upto where c.id =" + id;
		int app = session.createQuery( hqlUpdate ).setDate( "valid_upto", valid_upto ).executeUpdate();
		tx.commit();
		session.close();
		if(app > 0) {
			return "Data Update Successfully";
		}else {
			return "Data Update not Successfully";
		}
	}
	
	public ArrayList<ArrayList<String>> getRIOReport(String sus_no,String fdate ,String tdate ,String status,String roleType,String depot_sus_no,HttpSession session){
		 ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >();  
		 String qry = "";
		 String today= "";
		 if(!sus_no.equals("")) {
			 qry += " where r.sus_no = ? ";
		 }
		 if(!fdate.equals("") & tdate.equals("")) {
			 if(qry.contains("where")) {
				 qry += " and r.issue_date between cast(? as date) and  cast(? as date) ";
			 }else {
				 qry += " where r.issue_date between cast(? as date) and  cast(? as date) ";
			 }
		 }
		 if(!tdate.equals("") & fdate.equals("")) {
			 if(qry.contains("where")) {
				 qry += " and cast(r.issue_date as date) >= cast(? as date) ";
			 }else {
				 qry += " where cast(r.issue_date as date) >= cast(? as date) ";
			
			 }
		 }
		 if(!fdate.equals("") & !tdate.equals("")) { 
			 if(qry.contains("where")) {
				qry += " and r.issue_date between cast(? as date) and  cast(? as date) ";
			 }else {
				qry += " where r.issue_date between cast(? as date) and  cast(? as date) ";
			 }
		 }

		 if(!status.equals("")){
			 if(qry.contains("where")) {
				 qry += " and r.status = ?";
			 }else {
				 qry += " where r.status = ?";
			 } 
		 }
		 
		 if(!depot_sus_no.equals("")){
			 if(qry.contains("where")) {
				 qry += " and r.depot_sus_no = ?";
			 }else {
				 qry += " where r.depot_sus_no = ?";
			 } 
		 }
		
		// changed for RIO details display only under formation
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		
		String fcode=""; 
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Brigade")) {
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
		}
		 
		 Connection conn = null;
		 try {	
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
	 			
	 		String query = "select  r.id,r.type_of_vehicle,\r\n" + 
	 				"	r.rio_no as RIO,\r\n" + 
	 				"	r.ro_no,\r\n" + 
	 				"	u1.unit_name,\r\n" + 
	 				"	cmd.cmd_name as command,\r\n" + 
	 				"	m.std_nomclature as mctNomen,\r\n" + 
	 				"	r.mct as mct,\r\n" + 
	 				"	r.quantity,\r\n" + 
	 				"	r.quantity_status,\r\n" + 
	 				"	d1.unit_name as Depot,\r\n" + 
	 				"	(select label from T_Domain_Value where domainid='VEHICLEISSUETYPE' and codevalue=r.type_of_issue limit 1) as type_of_issue ,	\r\n" + 
	 				"	ltrim(TO_CHAR( r.issue_date,'dd-mm-yyyy'),'0') as  issue_date,\r\n" + 
	 				"	ltrim(TO_CHAR( r.valid_upto,'dd-mm-yyyy'),'0') as   validity,\r\n" + 
	 				"	(select std_nomclature from  tb_tms_mct_master where mct = cast(r.inliuemct as numeric) and (r.inliuemct is not null or r.inliuemct != '')  limit 1 ) as inliuemct,\r\n" + 
	 				"	(select total from cue_transport_ue_final where sus_no= r.sus_no and SUBSTRING(mct_no,1,4) = SUBSTRING(cast(r.mct as character varying), 1, 4) limit 1 ) as UE,\r\n" + 
	 				"	(select count(mct) from tb_tms_banum_dirctry where ba_no in(select ba_no from tb_tms_mvcr_parta_dtl where sus_no = r.sus_no) and status='Active' and SUBSTRING(cast(mct as character varying),1,4) = SUBSTRING(cast(r.mct as character varying) ,1,4) limit 1) as UH,\r\n" + 
	 				"	(select total from cue_transport_ue_final v ,tb_tms_mct_main_master m \r\n" + 
	 				"where 	\r\n" + 
	 				"	v.sus_no = r.sus_no \r\n" + 
	 				"	and m.prf_code = (select prf_code from tb_tms_mct_main_master where mct_main_id = left(cast(r.mct as character varying), 4)) \r\n" + 
	 				"	and left(cast(m.mct_main_id as character varying), 4) = left(cast(v.mct_no as character varying), 4) limit 1) as PRF_UE,\r\n" + 
	 				"	(select count(mct) from tb_tms_banum_dirctry where ba_no in(select ba_no from tb_tms_mvcr_parta_dtl where sus_no = r.sus_no ) and status='Active' and SUBSTRING(cast(mct as character varying),1,4) = SUBSTRING(cast(r.mct as character varying) ,1,4) limit 1) as PRFUH\r\n" + 
	 				"from tb_tms_rio_vehicle_dtls r \r\n" + 
	 				" inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = r.sus_no and u1.status_sus_no = 'Active' "+fcode+" \r\n" +
	 				"   left join orbat_view_cmd cmd on cmd.cmd_code = Substr(u1.form_code_control,1,1) " +
	 				"left join tb_tms_mct_master m on m.mct = r.mct\r\n" + 
	 				"left join tb_miso_orbat_unt_dtl d1 on d1.sus_no = r.depot_sus_no and d1.status_sus_no = 'Active'" +
	 				"  "+qry+" order by r.id";
	 		
	 			PreparedStatement stmtt=conn.prepareStatement(query);
				int j =1;
				if(sus_no != "") {	
					stmtt.setString(j, sus_no);
					j += 1;
				}		
				if(!fdate.equals("") && tdate.equals("")) {
					stmtt.setString(j, fdate);
					j += 1;
					Date to_date1 = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					today = dateFormat.format(to_date1);
					stmtt.setString(j, today);
					j += 1;
				}
				if(!tdate.equals("") && fdate.equals("")) {
					stmtt.setString(j, tdate);
					j += 1;
				}
				if(!fdate.equals("") && !tdate.equals("")) { 
					stmtt.setString(j, fdate);
					j += 1;
					stmtt.setString(j, tdate);
					j += 1;
				}
				if(!status.equals("")) {
					stmtt.setString(j, status);
					j += 1;
				}
				if(!depot_sus_no.equals("")) {
					stmtt.setString(j, depot_sus_no);
					j += 1;
				}
			 	ResultSet rs = stmtt.executeQuery();
			
				while(rs.next()){
					ArrayList<String> list1 = new ArrayList<String>(); 
		        	
					list1.add(rs.getString("id"));//0
		        	list1.add(rs.getString("RIO"));//1
		        	list1.add(rs.getString("ro_no"));//2
		        	list1.add(rs.getString("unit_name"));//3
		        	list1.add(rs.getString("command"));//4
		        	list1.add(rs.getString("mctNomen"));//5
		        	list1.add(rs.getString("mct"));//6
		        	list1.add(rs.getString("quantity"));//7
		        	list1.add(rs.getString("quantity_status"));//8
		        	list1.add(rs.getString("Depot"));//9
		        	list1.add(rs.getString("type_of_issue"));//10
		        	list1.add(rs.getString("issue_date"));//11
		        	list1.add(rs.getString("validity"));//12
		        	list1.add(rs.getString("inliuemct"));//13
		        	list1.add(rs.getString("UE"));//14
		        	list1.add(rs.getString("UH"));//15
		        	list1.add(rs.getString("PRF_UE"));//16
		        	list1.add(rs.getString("PRFUH"));//17
		        	list1.add(rs.getString("type_of_vehicle"));//18
		        	String f="";
		        	Date date= null;
		        	Date validateDate = null;
		        	String validate = rs.getString("validity");
		        	String issue_date =rs.getString("issue_date");
		        	
		        	if(roleStaff_lvl.equals("MGO")) {
			        	if(validate != "null"  && validate != null && validate != "" && !validate.equals("") && !validate.equals(null)  )
			  	        {
		  	        		date = new Date();
		  					try {
		  						validateDate= new SimpleDateFormat("dd-MM-yyyy").parse(rs.getString("validity")) ;
		  					} catch (ParseException e) {
		  						e.printStackTrace();
		  					}	
		  					if(date.before(validateDate) && (issue_date != null))
		  					{
		  						String update = "onclick=\"  if (confirm('Are You Sure you want to Update Date of Validity ?') ){update('"+rs.getString("id")+"')}else{ return false;}\"";
		  						String Amend = "onclick=\"  if (confirm('Are You Sure you want to Update Date of Validity ?') ){Amend('"+rs.getString("id")+"')}else{ return false;}\"";
		  				    	f +="<a hreF='#' '"+Amend+"' class='btn btn-default btn-xs' title='Update Data' id='amend'"+rs.getString("id")+"' >Extend/Amend</a> &nbsp; &nbsp;"
		  				    			+ "<a hreF='#' '"+update+"' class='btn btn-default btn-xs' title='Update Data' id='update'"+rs.getString("id")+"' style='display:none'>Update</a>"; 
		  				    }
			  	        }
		        	}
		        	if(roleAccess.equals("MISO") && (status == "0" || status.equals("0"))){
		        		if(roleType.equals("ALL") || roleType.equals("APP")) {
		        			String View = "onclick=\"view('"+rs.getString("id")+"')\"";
		  	    			f ="<i class='action_icons action_view' "+View+" title='View Data'></i>";
		        		}
		  			}
		  	      	if(status == "1" || status.equals("1")){
		  	      		if(roleType.equals("ALL") || roleType.equals("APP") || roleType.equals("DEO")) {
		  	      			String View = "onclick=\"print('"+rs.getString("id")+"')\"";
		  	      			f ="<i class='action_icons action_view' "+View+" title='View Data'></i>";
		  	      		}
		  			}
		  	      	list1.add(f);//18
		  	        aList.add(list1); 
				}
		  	    rs.close();
		  	    stmt.close();
				conn.close();
			} catch (SQLException e) {
	   			e.printStackTrace();
	   	} 
		return aList;
	}

	public TB_TMS_RIO_VEHICLE_DTLS UpdateRIO(TB_TMS_RIO_VEHICLE_DTLS rio) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		session.update(rio);
		session.getTransaction().commit();
		session.close();
		return rio;	
	}
	
	
	

public int getFreeStockBANo_and_ro_rio_pending_qty(String d_sus_no, BigInteger mct, String type_of_vehicle) {
           Connection conn = null;
           int count = 0;
           try {     
                   conn = dataSource.getConnection();
                   String tableName;
                   String tableName2;
                   String condition;
                   String bano;

                   if (type_of_vehicle.equals("A")) {
                           tableName = "TB_TMS_CENSUS_DRR_DIR_DTL";
                           tableName2 = " SELECT ba_no FROM  tb_tms_census_retrn";
                           bano="dir.ba_no"; 
                           condition="dir_dtl.ba_no  from   tb_tms_census_drr_dir_dtl  ";
                   } else if (type_of_vehicle.equals("B")) {
                           tableName = "TB_TMS_DRR_DIR_DTL";
                           tableName2 = " SELECT ba_no FROM  tb_tms_mvcr_parta_dtl ";
                           condition="dir_dtl.ba_no  from  tb_tms_drr_dir_dtl ";
                           bano="dir.ba_no";
                   } else if (type_of_vehicle.equals("C")) {
                           tableName = "TB_TMS_EMAR_DRR_DIR_DTL";
                           tableName2 = "SELECT em_no FROM  tb_tms_emar_report ";
                           bano="dir.em_no";
                           condition=" dir_dtl.em_no  from tb_tms_emar_drr_dir_dtl";
                   } else {
                           tableName = "";
                           tableName2 = "";
                           bano ="";
                           condition="";
                   }
               
                   
                   
                   String query = null;

                   query = "SELECT COALESCE(q1.qty-(q2.qty+q3.qty),0) AS freestock FROM\r\n" + 
                                   "(SELECT COUNT(b.ba_no) AS qty FROM\r\n" + 
                                   "(SELECT DISTINCT d.ba_no AS ba_no\r\n" + 
                                   "FROM TB_TMS_BANUM_DIRCTRY d\r\n" + 
                                   "INNER JOIN tb_tms_banum_req_child c ON d.ba_no=c.ba_no\r\n" + 
                                   "INNER JOIN tb_tms_banum_req_prnt prnt ON c.parent_req_id = prnt.parent_req_id AND ba_no_type ='0'\r\n" + 
                                   "INNER JOIN "+tableName+" dir ON d.ba_no = "+bano+" AND d.sus_no = ? AND dir.type_of_stock not in ('2','1') \r\n" + 
                                   "WHERE d.mct = ?::numeric AND d.status = 'Active') \r\n" + 
                                   "AS b WHERE b.ba_no NOT IN ("+tableName2+") and  b.ba_no NOT IN ("
                                   		+ "select distinct "+condition+" dir_dtl where dir_dtl.sus_no = ?  and dir_dtl.status='1' and dir_dtl.classification in ('5','6','7') ) )  q1,\r\n" + 
                                   "(SELECT COALESCE(SUM(quantity),0) AS qty\r\n" + 
                                   "FROM tb_tms_ro_vehicle_dtls\r\n" + 
                                   "WHERE mct = ?::numeric AND depot_sus_no = ? AND status IN ('0')) q2,\r\n" + 
                                   "(SELECT COALESCE(SUM(rio.quantity),0) AS qty FROM tb_tms_rio_vehicle_dtls rio\r\n" + 
                                   "INNER JOIN tb_tms_ro_main ro ON rio.ro_no = ro.ro_no\r\n" + 
                                   "WHERE rio.mct = ?::numeric AND rio.depot_sus_no = ? AND rio.status IN ('0')) q3";                                   

                   PreparedStatement stmtt = conn.prepareStatement(query);
                   stmtt.setString(1, d_sus_no);
                   stmtt.setBigDecimal(2, new BigDecimal(mct));
                   stmtt.setString(3, d_sus_no);
                   stmtt.setBigDecimal(4, new BigDecimal(mct));
                   stmtt.setString(5, d_sus_no);
                   stmtt.setBigDecimal(6, new BigDecimal(mct));
                   stmtt.setString(7, d_sus_no);
                  
                   System.err.println("Freee--"+stmtt);

                   ResultSet rs = stmtt.executeQuery();
                
                   while (rs.next()) {
                           count = rs.getInt("freestock");
                   }
                   rs.close();
                   conn.close();
           } catch (SQLException e) {
                  
           } 
           return count;
    }
	
	
	
	

/*   NITIN V4 16/11/2022  */
	public ArrayList<ArrayList<String>> getRIOReport_excel(String sus_no,String fdate ,String tdate ,String status,String roleType,String depot_sus_no,HttpSession session){
		 ArrayList<ArrayList<String> > aList =  new ArrayList<ArrayList<String> >();  
		 String qry = "";
		 String today= "";
		 

		 
		 if(!sus_no.equals("")) {
			 qry += " where r.sus_no = ? ";
		 }
		 if(!fdate.equals("") & tdate.equals("")) {
			 if(qry.contains("where")) {
				 qry += " and r.issue_date between cast(? as date) and  cast(? as date) ";
			 }else {
				 qry += " where r.issue_date between cast(? as date) and  cast(? as date) ";
			 }
		 }
		 if(!tdate.equals("") & fdate.equals("")) {
			 if(qry.contains("where")) {
				 qry += " and cast(r.issue_date as date) >= cast(? as date) ";
			 }else {
				 qry += " where cast(r.issue_date as date) >= cast(? as date) ";
			
			 }
		 }
		 if(!fdate.equals("") & !tdate.equals("")) { 
			 if(qry.contains("where")) {
				qry += " and r.issue_date between cast(? as date) and  cast(? as date) ";
			 }else {
				qry += " where r.issue_date between cast(? as date) and  cast(? as date) ";
			 }
		 }

		 if(!status.equals("")){
			 if(qry.contains("where")) {
				 qry += " and r.status = ?";
			 }else {
				 qry += " where r.status = ?";
			 } 
		 }
		 
		 if(!depot_sus_no.equals("")){
			 if(qry.contains("where")) {
				 qry += " and r.depot_sus_no = ?";
			 }else {
				 qry += " where r.depot_sus_no = ?";
			 } 
		 }
		
		// changed for RIO details display only under formation
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleStaff_lvl = session.getAttribute("roleStaff_lvl").toString();
		
		String fcode=""; 
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
			if (roleSubAccess.equals("Brigade")) {
				fcode = " and u1.form_code_control like '"+roleFormationNo+"%'";
			}
		}
		 
		 Connection conn = null;
		 try {	
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
	 			
	 		String query = "select  r.id,\r\n" + 
	 				"	r.rio_no as RIO,\r\n" + 
	 				"	r.ro_no,\r\n" + 
	 				"	u1.unit_name,\r\n" + 
	 				"	cmd.cmd_name as command,\r\n" + 
	 				"	m.std_nomclature as mctNomen,\r\n" + 
	 				"	r.mct as mct,\r\n" + 
	 				"	r.quantity,\r\n" + 
	 				"	r.quantity_status,\r\n" + 
	 				"	d1.unit_name as Depot,\r\n" + 
	 				"	(select label from T_Domain_Value where domainid='VEHICLEISSUETYPE' and codevalue=r.type_of_issue limit 1) as type_of_issue ,	\r\n" + 
	 				"	ltrim(TO_CHAR( r.issue_date,'dd-mm-yyyy'),'0') as  issue_date,\r\n" + 
	 				"	ltrim(TO_CHAR( r.valid_upto,'dd-mm-yyyy'),'0') as   validity,\r\n" + 
	 				"	(select std_nomclature from  tb_tms_mct_master where mct = cast(r.inliuemct as numeric) and (r.inliuemct is not null or r.inliuemct != '')  limit 1 ) as inliuemct,\r\n" + 
	 				"	(select total from cue_transport_ue_final where sus_no= r.sus_no and SUBSTRING(mct_no,1,4) = SUBSTRING(cast(r.mct as character varying), 1, 4) limit 1 ) as UE,\r\n" + 
	 				"	(select count(mct) from tb_tms_banum_dirctry where ba_no in(select ba_no from tb_tms_mvcr_parta_dtl where sus_no = r.sus_no) and status='Active' and SUBSTRING(cast(mct as character varying),1,4) = SUBSTRING(cast(r.mct as character varying) ,1,4) limit 1) as UH,\r\n" + 
	 				"	(select total from cue_transport_ue_final v ,tb_tms_mct_main_master m \r\n" + 
	 				"where 	\r\n" + 
	 				"	v.sus_no = r.sus_no \r\n" + 
	 				"	and m.prf_code = (select prf_code from tb_tms_mct_main_master where mct_main_id = left(cast(r.mct as character varying), 4)) \r\n" + 
	 				"	and left(cast(m.mct_main_id as character varying), 4) = left(cast(v.mct_no as character varying), 4) limit 1) as PRF_UE,\r\n" + 
	 				"	(select count(mct) from tb_tms_banum_dirctry where ba_no in(select ba_no from tb_tms_mvcr_parta_dtl where sus_no = r.sus_no ) and status='Active' and SUBSTRING(cast(mct as character varying),1,4) = SUBSTRING(cast(r.mct as character varying) ,1,4) limit 1) as PRFUH\r\n" + 
	 				"from tb_tms_rio_vehicle_dtls r \r\n" + 
	 				" inner join tb_miso_orbat_unt_dtl u1 on u1.sus_no = r.sus_no and u1.status_sus_no = 'Active' "+fcode+" \r\n" +
	 				"   left join orbat_view_cmd cmd on cmd.cmd_code = Substr(u1.form_code_control,1,1) " +
	 				"left join tb_tms_mct_master m on m.mct = r.mct\r\n" + 
	 				"left join tb_miso_orbat_unt_dtl d1 on d1.sus_no = r.depot_sus_no and d1.status_sus_no = 'Active'" +
	 				"  "+qry+" order by r.id";
	 		
	 			PreparedStatement stmtt=conn.prepareStatement(query);
				int j =1;
				
				if(!sus_no.equals("") && sus_no != "") {	
				
					stmtt.setString(j, sus_no);
					j += 1;
				}		
				if(!fdate.equals("") && tdate.equals("")) {
				
					stmtt.setString(j, fdate);
					j += 1;
					Date to_date1 = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					today = dateFormat.format(to_date1);
					stmtt.setString(j, today);
					j += 1;
				}
				if(!tdate.equals("") && fdate.equals("")) {
					
					stmtt.setString(j, tdate);
					j += 1;
				}
				if(!fdate.equals("") && !tdate.equals("")) { 
					
					stmtt.setString(j, fdate);
					j += 1;
					stmtt.setString(j, tdate);
					j += 1;
				}
				if(status!="" || !status.equals("") ) {
					
					stmtt.setString(j, status);
					j += 1;
				}
				if(!depot_sus_no.equals("")) {
					
					stmtt.setString(j, depot_sus_no);
					j += 1;
				}
			 	ResultSet rs = stmtt.executeQuery();
				while(rs.next()){
					ArrayList<String> list1 = new ArrayList<String>(); 
		        	
					//list1.add(rs.getString("id"));//0
		        	list1.add(rs.getString("RIO"));//1
		        	list1.add(rs.getString("ro_no"));//2
		        	list1.add(rs.getString("unit_name"));//3
		        	list1.add(rs.getString("command"));//4
		        	list1.add(rs.getString("mctNomen"));//5
		        	list1.add(rs.getString("mct"));//6
		        	list1.add(rs.getString("quantity"));//7
		        	list1.add(rs.getString("quantity_status"));//8
		        	list1.add(rs.getString("Depot"));//9
		        	list1.add(rs.getString("type_of_issue"));//10
		        	list1.add(rs.getString("issue_date"));//11
		        	list1.add(rs.getString("validity"));//12
		        	list1.add(rs.getString("inliuemct"));//13
		        	list1.add(rs.getString("UE"));//14
		        	list1.add(rs.getString("UH"));//15
		        	list1.add(rs.getString("PRF_UE"));//16
		        	list1.add(rs.getString("PRFUH"));//17
		        	
		  	        aList.add(list1); 
				}
		  	    rs.close();
		  	    stmt.close();
				conn.close();
			} catch (SQLException e) {
	   			e.printStackTrace();
	   	} 
		return aList;
	}
	
	//Updated By Mitesh1107
	public List<String> getFreeStockBANoList(String d_sus_no,BigInteger mct,String type_of_vehicle){
		 Connection conn = null;
	 List<String> list = new ArrayList<String>();
		 try {	
			conn = dataSource.getConnection();
			String tableName;
			String query = null;
			 
				if (type_of_vehicle.equals("A")) {
				    tableName = "TB_TMS_CENSUS_DRR_DIR_DTL";
				    query = "select b.ba_no from\r\n" + 
			 				"(select distinct d.ba_no as ba_no ,d.id\r\n" + 
			 				"from TB_TMS_BANUM_DIRCTRY d\r\n" + 
			 				"inner join tb_tms_banum_req_child c on d.ba_no=c.ba_no\r\n" + 
			 				"inner join tb_tms_banum_req_prnt prnt on c.parent_req_id = prnt.parent_req_id and ba_no_type ='0'\r\n" + // also check only Army ba_no show in free stock
			 				"inner join "+tableName+" dir on d.ba_no = dir.ba_no and d.sus_no = ? and dir.type_of_stock not in ('2','1') \r\n" + 
			 				"where cast(d.mct as varchar)= ? and d.status = 'Active' order by d.id asc) as b where b.ba_no not in (select ba_no from tb_tms_census_retrn )\r\n"
			 				+ "and  b.ba_no NOT IN (select distinct dir_dtl.ba_no  from  tb_tms_census_drr_dir_dtl  dir_dtl \r\n"
			 				+ " where dir_dtl.sus_no = ?  and dir_dtl.status='1' and dir_dtl.classification in ('5','6','7'))\r\n"

			 				
			 				;
				} else if (type_of_vehicle.equals("B")) {
				    tableName = "TB_TMS_DRR_DIR_DTL";
				    query = "select b.ba_no from\r\n" + 
			 				"(select distinct d.ba_no as ba_no ,d.id\r\n" + 
			 				"from TB_TMS_BANUM_DIRCTRY d\r\n" + 
			 				"inner join tb_tms_banum_req_child c on d.ba_no=c.ba_no\r\n" + 
			 				"inner join tb_tms_banum_req_prnt prnt on c.parent_req_id = prnt.parent_req_id and ba_no_type ='0'\r\n" + // also check only Army ba_no show in free stock
			 				"inner join "+tableName+" dir on d.ba_no = dir.ba_no and d.sus_no = ?  and dir.type_of_stock not in ('2','1')\r\n" + 
			 				"where cast(d.mct as varchar)= ? and d.status = 'Active' order by d.id asc) as b where b.ba_no not in (select ba_no from TB_TMS_MVCR_PARTA_DTL )\r\n"
			 				+ "and  b.ba_no NOT IN (select distinct dir_dtl.ba_no  from  tb_tms_drr_dir_dtl  dir_dtl \r\n"
			 				+ " where dir_dtl.sus_no = ?  and dir_dtl.status='1' and dir_dtl.classification in ('5','6','7'))\r\n"
			 				;
			
				} else if (type_of_vehicle.equals("C")) {
				    tableName = "TB_TMS_EMAR_DRR_DIR_DTL";
				    query = "select b.ba_no from\r\n" + 
			 				"(select distinct d.ba_no as ba_no ,d.id \r\n" + 
			 				"from TB_TMS_BANUM_DIRCTRY d\r\n" + 
			 				"inner join tb_tms_banum_req_child c on d.ba_no=c.ba_no\r\n" + 
			 				"inner join tb_tms_banum_req_prnt prnt on c.parent_req_id = prnt.parent_req_id and ba_no_type ='0'\r\n" + // also check only Army ba_no show in free stock
			 				"inner join "+tableName+" dir on d.ba_no = dir.em_no and d.sus_no = ?  and dir.type_of_stock not in ('2','1') \r\n" + 
			 				"where cast(d.mct as varchar)= ? and d.status = 'Active' order by d.id asc) as b where b.ba_no not in (select em_no from tb_tms_emar_report )\r\n"
			 				+ "and  b.ba_no NOT IN (select distinct dir_dtl.em_no  from  tb_tms_emar_drr_dir_dtl  dir_dtl \r\n"
			 				+ " where dir_dtl.sus_no = ?  and dir_dtl.status='1' and dir_dtl.classification in ('5','6','7'))\r\n"

			 				
			 				;
				} else {
				   tableName = "";
				}
	 	
	 		
			
			PreparedStatement stmtt=conn.prepareStatement(query);
			stmtt.setString(1, d_sus_no);
			stmtt.setString(2, String.valueOf(mct));
			stmtt.setString(3, d_sus_no);

//			
			System.err.println("BA_No Issue--"+stmtt);

			ResultSet rs = stmtt.executeQuery();
	
		 	while(rs.next()){
				list.add(rs.getString("ba_no"));//0
	        }
	  	    rs.close();
			conn.close();
		} catch (SQLException e) {
  			
	   	} 
		return list;
	}
	
}