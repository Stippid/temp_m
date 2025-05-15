package com.dao.psg.update_census_data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Transaction.TB_FIELD_SERVICE_CH;
import com.models.psg.Transaction.TB_FIELD_SERVICE_P;
import com.persistance.util.HibernateUtil;
public class Field_serviceDAOImpl implements Field_serviceDAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public ArrayList<ArrayList<String>> GetSearch_field_service_data(String roleSusNo,String personnel_no,String statusA,String fd_service,String unit_location,String operation,String unit_name,String unit_sus_no,int service_category, String cr_by,String cr_date, HttpSession session)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	String qryin="";
	String tb_name="tb_psg_field_service_p";
	String tb_namech="tb_psg_field_service_ch";
	String service_categ="OFFICER";
	
	try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			String roleType = session.getAttribute("roleType").toString();
			String sus_no = session.getAttribute("roleSusNo").toString();
			if (!sus_no.equals("") || sus_no == null) {
				qryin="inner join logininformation l on s.sus_id = l.user_sus_no \r\n";
			}
			if(!roleSusNo.equals("")) {
				qry+=" and s.sus_id = ?";
			}
			if(!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			if(!personnel_no.equals("")) {
				qry += "  and upper(ch.personnel_no) like ? ";
			}
			if(statusA.equals("0")) {
				qry += " and cast(s.status as character varying) = ? and s.cancel_status in (-1,0,2)";
			}
			if(statusA.equals("1")) {
				qry += " and cast(ch.status as character varying) = ? and s.cancel_status in (-1,0,2)";
			}
			if(statusA.equals("3")) {
				qry += " and cast(s.status as character varying) = ? and s.cancel_status in (-1,0,2)";
			}  
			if(roleType.equals("DEO") && statusA.equals("4")) {
				qry += " and cast(ch.status as character varying) = ? and ch.cancel_status in (-1,2)";
			} 
			if(roleType.equals("APP") && statusA.equals("4")) {
				qry += " and cast(ch.status as character varying) = ? and (s.cancel_status = 0 or ch.cancel_status = 0)";
			} 
			if(!fd_service.equals("0")) {
				qry += "  and cast(s.fd_services as character varying) = ?";
			}
			if(!unit_location.equals("")) {
				qry += "  and upper(s.unit_location) like ? ";
			}
			if(!operation.equals("0")) {
				qry += "  and cast(s.operation as character varying) = ?";
			}
			
			
			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}
     	if(!cr_date.equals("")) {
				qry +=" and cast(s.created_date as date) = cast(? as date)";
			}
	
				q="select distinct  s.id , s.authority,s.authority_date,s.sus_id as unit_sus_no,orb.unit_name,d.field_area,\r\n" + 
						"						 s.unit_location,o.operation_name,s.fd_services,s.status,s.reject_remarks from "+tb_name+" s \r\n" + 
						"						 inner join "+tb_namech+" ch  \r\n" + 
						"						 on ch.p_id = s.id \r\n"+
						"						 inner join tb_psg_mstr_field_area d  \r\n" + 
						"						 on d.id= s.fd_services \r\n" + 
						"						 inner join tb_psg_mstr_operation o on o.id =s.operation \r\n" + 
						"						 inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = s.sus_id and status_sus_no='Active'\r\n" + 
						"left join logininformation l1 on l1.username = s.created_by\r\n" + 
												qryin + 
						"						 where s.id >= 0 " + qry + " order by s.id desc" ;
				
				

			stmt=conn.prepareStatement(q);
			
			if(!qry.equals(""))     
			{  
				int j =1;
				if (!roleSusNo.equals("")) {
					stmt.setString(j, roleSusNo);
					j += 1;
				}
				if(!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;	
				} 
				if( !personnel_no.equals("")) {
					stmt.setString(j, personnel_no.toUpperCase()+"%");
					j += 1;	
				} 
				if(statusA.equals("0")) {				
					stmt.setString(j, statusA);
					j += 1;	
				}
				if(statusA.equals("1")) {				
					stmt.setString(j, statusA);
					j += 1;	
				}
				if(statusA.equals("3")) {				
					stmt.setString(j, statusA);
					j += 1;	
				}
				if(statusA.equals("4")) {				
					stmt.setString(j, "1");
					j += 1;	
				}
				if( !fd_service.equals("0")) {
					stmt.setString(j, fd_service);
					j += 1;	
				}
				if( !unit_location.equals("")) {
					stmt.setString(j, unit_location.toUpperCase()+"%");
					j += 1;	
				}
				if( !operation.equals("0")) {
					stmt.setString(j, operation);
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
	    	  	list.add(service_categ);//0
	    	  	list.add(rs.getString("field_area"));//1
	    	  	list.add(rs.getString("authority"));//2
	    	  	list.add(rs.getString("authority_date"));//3
	    	  	list.add(rs.getString("operation_name"));//4
	    	  	list.add(rs.getString("unit_location"));//5
	    	  	list.add(rs.getString("unit_name"));//6
	    	  	list.add(rs.getString("reject_remarks"));//6
				String status = rs.getString("status");
				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0") && statusA.equals("0") )
				{
					//String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("id")+ ",'"+ rs.getString("fd_services") +"','"+rs.getString("census_id")+ "'}else{ return false;}\"";
					String Approved = "onclick=\" Approved("+rs.getString("id")+" ,'"+rs.getString("fd_services")+"' )\"";
					f3 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";  
				}
				if(roleType.equals("ALL") || roleType.equals("DEO") && (statusA.equals("0") ||statusA.equals("3")) )
				{
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Data?') ){editData("
							+ rs.getString("id") + ",'"+ rs.getString("fd_services") +"')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("DEO") && (statusA.equals("1") ) )
				{
					String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Data ?') ){editApprData("+rs.getString("id")+" ,'"+rs.getString("fd_services")+"' )}else{ return false;}\"";
					f2 = "<i class='action_icons action_update' "+Update+" title='Update Data' ></i>";
					String View = "onclick=\" ViewData("+rs.getString("id")+" ,'"+rs.getString("fd_services")+"' ) \"";
					f1 = "<i class='fa fa-eye' "+View+" title='View Data' ></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("DEO") &&  statusA.equals("4"))
				{
					String View1 = "onclick=\" ViewHistoryData("+ rs.getString("id") + ") \"";
			        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("APP") &&  statusA.equals("4"))
				{
					String View1 = "onclick=\" ApprHistoryData("+ rs.getString("id") + ") \"";
			        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("APP") && (statusA.equals("1") ) )
				{
					String View = "onclick=\" ViewData("+rs.getString("id")+" ,'"+rs.getString("fd_services")+"' ) \"";
					f1 = "<i class='fa fa-eye' "+View+" title='View Data' ></i>";
				}
	
				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(rs.getString("fd_services"));
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
	public List<TB_FIELD_SERVICE_P> getfield_servicerByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String updateid = " from TB_FIELD_SERVICE_P where id=:id";
			Query query = sessionHQL.createQuery(updateid).setParameter("id", id);
			List<TB_FIELD_SERVICE_P> list = (List<TB_FIELD_SERVICE_P>) query.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	public List<TB_FIELD_SERVICE_CH> getfield_servicerChByid(int id, String status) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String q = "";
		if(!status.equals("")) {
			q = " and status=:status ";
		}
		try {
			String updateid = " from TB_FIELD_SERVICE_CH where p_id=:p_id "+q;
			Query query = sessionHQL.createQuery(updateid).setParameter("p_id", id);
			if(!status.equals("")) {
				query.setParameter("status", Integer.parseInt(status));
			}
			List<TB_FIELD_SERVICE_CH> list = (List<TB_FIELD_SERVICE_CH>) query.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	public ArrayList<String> getFieldCombination(String fd_service){
	Connection conn = null;
	ArrayList<String> list = new ArrayList<String>();
	String q="";
	try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				q="select fd1,fd2 from tb_psg_mstr_combination_of_allcea where fd1=? or fd2=?" ;
			stmt=conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(fd_service));
			stmt.setInt(2, Integer.parseInt(fd_service));
	      ResultSet rs = stmt.executeQuery();   
	      while (rs.next()) {
	    	  if (rs.getString("fd1").equals(fd_service)) {
	    		  list.add(rs.getString("fd2"));
	    	  }
	    	  else
	    		  list.add(rs.getString("fd1"));
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
		return list;
	}
	public int getFieldServicesCount(String personnel_no, String fd_service, String from_date, String to_date, int ch_id){
		Connection conn = null;
		String q="";
		String qry="";
		String qry1="";
		String qry2="";
		String qryid="";
		int count =0;
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if(ch_id!=0){
					qryid += "  and ch.id != ? ";
				}
				if(!fd_service.equals("")) {
					qry += "  and p.fd_services=?";
				}
				if(!to_date.equals("")) {
					qry1 += "  or ?::timestamp between from_date and to_date ";
					qry2 += " or ( from_date  between ?::timestamp and ?::timestamp or to_date between ?::timestamp and ?::timestamp ) ";
				}
					q="select count(*) as count\r\n" + 
							"from tb_psg_field_service_p p inner join tb_psg_field_service_ch ch on ch.p_id=p.id\r\n" + 
							"where ch.personnel_no =? and (( ?::timestamp  between from_date and to_date "+qry1+") "+qry2+" ) "+qry+qryid ;
				stmt=conn.prepareStatement(q);
				stmt.setString(1, personnel_no);
				stmt.setString(2,  from_date);
				int i=3;
				if(!to_date.equals("")) {
					stmt.setString(i,  to_date);
					i++;
					stmt.setString(i,  from_date);
					i++;
					stmt.setString(i,  to_date);
					i++;
					stmt.setString(i,  from_date);
					i++;
					stmt.setString(i,  to_date);
					i++;
				}
				if(!fd_service.equals("")) {
					stmt.setInt(i, Integer.parseInt(fd_service));
					i++;
				}
				if(ch_id!=0){
					stmt.setInt(i,  ch_id);
					i++;
				}
		      ResultSet rs = stmt.executeQuery();   
		      rs.next();
		      count = rs.getInt("count");
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
			return count;
		}
}
