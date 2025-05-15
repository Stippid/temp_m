package com.dao.psg.Transaction;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_BATTALION;
import com.models.psg.Master.TB_MARITAL_STATUS;
import com.models.psg.Transaction.TB_FIELD_SERVICE_P;
import com.models.psg.Transaction.TB_MARITIAL_DISCORD;
import com.models.psg.Transaction.TB_Maritial_Discord_Status_Child;
import com.persistance.util.HibernateUtil;

public class Maritial_DiscordDAOImpl implements Maritial_DiscordDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	/*Mmap.put("list", tm_dao.search_maritial_table("1", "", "1", "",
	"", roleAccess, roleSusNo, ""));*/
	
	///Bisag 060922(new enhancement)
	
	public ArrayList<ArrayList<String>> search_maritial_table(String service_category1,String personnel_no,
			String roleType,String roleAccess,String roleSusNo)
	{
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			if (service_category1 != "" || service_category1 != null || service_category1 != "0") {
				qry += " and p.service_category = cast(? as integer) ";
			}
			if(personnel_no !="") {
				qry += " and upper(p.personnel_no) = ? ";
			}
			
			
			if (roleAccess.equals("Unit")) {
				qry += " and upper(c.unit_sus_no) = ? ";
			}
			
			
			q="select p.id,m.id,p.personnel_no,p.name_lady,m.status_of_case,ltrim(TO_CHAR(p.dt_of_comp ,'DD-MON-YYYY'),'0') as dt_of_comp,"
					+ "p.complaint,ltrim(TO_CHAR(m.dt_of_status ,'DD-MON-YYYY'),'0') as dt_of_status,m.status from tb_psg_maritial_discord p "
					+ " inner join tb_psg_maritial_discord_status_child m on m.maritial_id = p.id "
					+ "inner join tb_psg_trans_proposed_comm_letter c on c.id =p.comm_id  where p.id !=0 " +qry +  ""
							+ "and m.status in (1,2) and m.close_status = 2 " + 
							"and p.status = 1 and p.close_status = 2  order by m.id asc";
			
				stmt=conn.prepareStatement(q);
				
				
				int j =1;
		
				if(service_category1 != "" || service_category1 != null || service_category1 != "0") {
					stmt.setString(j, service_category1);
					j += 1;
				}
				if(personnel_no !="") {
					stmt.setString(j, personnel_no);
					j += 1;
				}
				
				if (roleAccess.equals("Unit")) {
					stmt.setString(j, roleSusNo);
					j += 1;	
				}
				
				
				ResultSet rs = stmt.executeQuery();  
			
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("personnel_no"));//0
					list.add(rs.getString("name_lady"));//1
					list.add(rs.getString("status_of_case"));//2
					list.add(rs.getString("dt_of_comp"));//3
					list.add(rs.getString("complaint"));//4
					list.add(rs.getString("dt_of_status"));//5
					
					String f = "";
					String f1 = "";
					String f2 = "";
					String f3 = "";
					
				
					
						
					
				       if (rs.getString("status").equals("1")) {
							
						
				        
				        String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Marital Discord?') ){editData("+ rs.getInt("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					
						
						String Approve = "onclick=\" {approveData(" + rs.getInt("id") + ")}\"";
						f2 = "<i class='action_icons action_approve' " + Approve + " title='Approve Data'></i>";
						
						String Close = "onclick=\"  if (confirm('Are You Sure you want to Close This Marital Discord ?') ){Close_Data("+ rs.getString("id") + ")}else{ return false;}\"";
						f3 = "<i class='action_icons action_reject' " + Close + " title='Close Data'></i>";
						
						String View1 = "onclick=\"  {AppViewData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				        
				       }
					
					list.add(f);//6
					list.add(f1);//7
					//list.add(f2);//8
					list.add(f3);//9
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
	
	
	
	
	public ArrayList<ArrayList<String>> search_maritial(String service_category1,String personnel_no,
			String status,String roleType,String from_date,String roleAccess,String roleSusNo, String rank,String close_status1,String cr_by,String cr_date)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
			if (service_category1 != "" || service_category1 != null || service_category1 != "0") {
				qry += " and p.service_category = cast(? as integer) ";
			}
			if(personnel_no !="") {
				qry += " and upper(p.personnel_no) = ? ";
			}
			if(!status.equals("") && !status.equals("4") && !status.equals("5")) {
				
			    qry += " and  m.status = cast(? as integer) and p.cancel_status in (-1,0,2) ";
			}
			if(roleType.equals("DEO") && status.equals("4")) {
				
				qry += " and cast(m.status as character varying) = ? and m.cancel_status in (-1,2) and cast(m.close_status as character varying) != '1'  and cast(p.close_status as character varying) != '1'";
			} 
			if(roleType.equals("APP") && status.equals("4")) {
				
				qry += " and cast(m.status as character varying) = ? and (p.cancel_status = 0 or m.cancel_status = 0) and cast(m.close_status as character varying) != '1'  and cast(p.close_status as character varying) != '1'";
			} 
			
			if (roleAccess.equals("Unit")) {
				qry += " and upper(c.unit_sus_no) = ? ";
			}
			if(!rank.equals("") && !rank.equals("0")) {
			    qry += " and c.rank = ? ";
			}
			
			if( status.equals("5") && !close_status1.equals("") && !close_status1.equals("4") ) {
	
			    qry += " and  cast(m.close_status as character varying) = ?  and cast(p.close_status as character varying) = ? and m.status = 1  ";
			}
			if( status.equals("5") && close_status1.equals("4")) {
				
			    qry += " and  cast(m.close_status as character varying) = '1'  and cast(p.close_status as character varying) = '1' and m.status = 1";
			}

	    
	
	
	
	if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

	
	
	if(!cr_date.equals("")) {
				qry +=" and cast(p.created_date as date) = cast(? as date)";
			}
	
	
	
	
		
				q="select p.id,p.personnel_no,p.name_lady,m.status_of_case, p.reject_remarks from tb_psg_maritial_discord p "
						+ " inner join tb_psg_maritial_discord_status_child m on m.maritial_id = p.id "
						+ "inner join tb_psg_trans_proposed_comm_letter c on c.id =p.comm_id " +
						" left join logininformation l1 on l1.username = p.created_by   where p.id !=0 "  +qry +  "order by p.id desc";
			
			
				stmt=conn.prepareStatement(q);
				
				int j =1;
				
				if(service_category1 != "" || service_category1 != null || service_category1 != "0") {
					stmt.setString(j, service_category1);
					j += 1;
				}
				if(personnel_no !="") {
					stmt.setString(j, personnel_no);
					j += 1;
				}
				
				if(!status.equals("") && !status.equals("4") &&!status.equals("5")) {
					
						
						stmt.setString(j, status);
						j += 1;
					
				}
				
				if(status.equals("4")) {				
					stmt.setString(j, "1");
					j += 1;	
				}
			
				if (roleAccess.equals("Unit")) {
					stmt.setString(j, roleSusNo);
					j += 1;	
				}
				if(!rank.equals("") && !rank.equals("0")) {
					stmt.setInt(j, Integer.parseInt(rank));
					j += 1;	
				}
				if(status.equals("5") && !close_status1.equals("")&& !close_status1.equals("4")) {
					stmt.setString(j, close_status1);
					j += 1;	
					stmt.setString(j, close_status1);
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

				ResultSet rs = stmt.executeQuery();  
				
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("personnel_no"));
					list.add(rs.getString("name_lady"));
					list.add(rs.getString("status_of_case"));
					list.add(rs.getString("reject_remarks"));
					
					String f = "";
					String f1 = "";
					String f2 = "";
					String f3 = "";
					
					if(roleType.equals("ALL") || roleType.equals("DEO") && ((status.equals("0")) ||  (status.equals("3"))))
					{
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Marital Discord?') ){editData("+ rs.getInt("id") + ")}else{ return false;}\"";
						f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String status_count = "0";
						if(search_maritial_count(rs.getString("personnel_no")).size() > 0)
						{
							status_count = search_maritial_count(rs.getString("personnel_no")).get(0).get(0);
						}
						if(status_count.equals("0"))
						{
							String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Marital Discord?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
							f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
						}
					}
					if(roleType.equals("ALL") || roleType.equals("DEO") && status.equals("1"))
					{
						String View1 = "onclick=\"  {AppViewData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					}
					if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
					{
						
						String Approve = "onclick=\" {approveData(" + rs.getInt("id") + ")}\"";
						f2 = "<i class='action_icons action_approve' " + Approve + " title='Approve Data'></i>";
						
						String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject This Marital Discord ?') ){Reject("+ rs.getString("id") + ")}else{ return false;}\"";
						f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
					}
					if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("1"))
					{
						String View1 = "onclick=\"  {AppViewData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					}
					if(roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
					{
						String View1 = "onclick=\"  {ViewHistoryData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					}
					if( roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("4"))
					{
						String View1 = "onclick=\"  {ApprHistoryData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					}
					
					if(roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("5") &&  close_status1.equals("0"))
					{
						String Approve = "onclick=\" {approvecloseData(" + rs.getInt("id") + ")}\"";
						f2 = "<i class='action_icons action_approve' " + Approve + " title='Approve Data'></i>";
						
					}
					
					
					if( roleType.equals("ALL") ||  status.equals("5") &&  (close_status1.equals("1") ||  close_status1.equals("4")))
					{
						String View1 = "onclick=\"  {AppViewData("+ rs.getString("id") + ")}\"";
				        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
					}
					
		
					list.add(f);
					list.add(f1);
					list.add(f2);
					list.add(f3);
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
	
	public ArrayList<ArrayList<String>> Popup_Marital_Discord_History(BigInteger comm_id, int census_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="SELECT distinct ch.id, ch.status_of_case,  "
					+ "ch.created_by,ltrim(TO_CHAR(ch.created_date ,'DD-MON-YYYY'),'0') as created_date, "
					+ "ch.modified_by,ltrim(TO_CHAR(ch.modified_date ,'DD-MON-YYYY'),'0') as modified_date, \n"  
					+ "p.name_lady,ltrim(TO_CHAR(p.dt_of_comp ,'DD-MON-YYYY'),'0') as dt_of_comp ,p.complaint\n" 
					+ "FROM tb_psg_maritial_discord_status_child ch "
					+ "inner join tb_psg_maritial_discord p on p.id = ch.maritial_id "
					+ "WHERE ch.status in (1,2) AND p.comm_id::text = ? AND p.census_id = ? order by ch.id desc";
					
				
				stmt=conn.prepareStatement(q);
				
				stmt.setString(1,comm_id.toString());
				stmt.setInt(2,census_id);
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("status_of_case"));//0
					list.add(rs.getString("created_by"));//1
					list.add(rs.getString("created_date"));//2
					list.add(rs.getString("modified_by"));//3
					list.add(rs.getString("modified_date"));//4
					list.add(rs.getString("name_lady"));//5
					list.add(rs.getString("dt_of_comp"));//6
					list.add(rs.getString("complaint"));//7
					
					
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

	public TB_MARITIAL_DISCORD getmaritialByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_MARITIAL_DISCORD updateid = (TB_MARITIAL_DISCORD) sessionHQL.get(TB_MARITIAL_DISCORD.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	
	public String GetUpdateMarital_DiscordParent(TB_MARITIAL_DISCORD obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
	  String msg = "";
	  try{
			 String hql = "update TB_MARITIAL_DISCORD set personnel_no=:personnel_no,name_lady=:name_lady,status='0',dt_of_comp=:dt_of_comp,"
			 		+ " complaint=:complaint,modified_by=:modified_by,modified_date=:modified_date where id=:id";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    				.setString("personnel_no",obj.getPersonnel_no())
	    			  	.setString("name_lady",obj.getName_lady())
	    			  	.setTimestamp("dt_of_comp", obj.getDt_of_comp())
	    			  	.setString("complaint", obj.getComplaint())
						.setString("modified_by", username)
						.setDate("modified_date", new Date())
					  .setInteger("id",obj.getId());
	    	  
	          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	        
	          tx.commit();
	          
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
	public String GetUpdateMarital_DiscordChild(TB_Maritial_Discord_Status_Child obj,String username){
		
	
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
	  String msg = "";
	  try{
	  
			 String hql = "update TB_Maritial_Discord_Status_Child set status_of_case=:status_of_case,status='0',"
			 		+ " modified_by=:modified_by,modified_date=:modified_date,dt_of_status=:dt_of_status where maritial_id=:maritial_id and id=:id ";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    			  	.setString("status_of_case",obj.getStatus_of_case())
						.setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setInteger("maritial_id",obj.getMaritial_id()).setTimestamp("dt_of_status",obj.getDt_of_status())
						.setInteger("id", obj.getId());
	    	  
	    	  
	          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	          
	      
	          tx.commit();
	          
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
	
	public ArrayList<ArrayList<String>> Marital_Discord_History(String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			
			q="SELECT distinct ch.id, ch.status_of_case ,ltrim(TO_CHAR(ch.dt_of_status ,'DD-MON-YYYY'),'0') as status_date,  "
					+ "ch.created_by,ltrim(TO_CHAR(ch.created_date ,'DD-MON-YYYY'),'0') as created_date, "
					+ "ch.modified_by,ltrim(TO_CHAR(ch.modified_date ,'DD-MON-YYYY'),'0') as modified_date, \n" 
					+ "p.name_lady,ltrim(TO_CHAR(p.dt_of_comp ,'DD-MON-YYYY'),'0') as dt_of_comp ,p.complaint\n"  
					+ "FROM tb_psg_maritial_discord_status_child ch "
					+ "inner join tb_psg_maritial_discord p on p.id = ch.maritial_id and p.status = '1' "
					+ "WHERE ch.status in (1,2) AND ch.maritial_id = ? order by ch.id desc";
					
				
				stmt=conn.prepareStatement(q);
				
				stmt.setInt(1,Integer.parseInt(id));
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("status_of_case"));//0
					list.add(rs.getString("created_by"));//1
					list.add(rs.getString("created_date"));//2
					list.add(rs.getString("modified_by"));//3
					list.add(rs.getString("modified_date"));//4
					list.add(rs.getString("name_lady"));//5
					list.add(rs.getString("dt_of_comp"));//6
					list.add(rs.getString("complaint"));//7
					list.add(rs.getString("status_date"));//7
					
					
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
	
	public List<TB_MARITIAL_DISCORD> getMaritial_DiscordByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String updateid = " from TB_MARITIAL_DISCORD where id=:id";
			Query query = sessionHQL.createQuery(updateid).setParameter("id", id);
			List<TB_MARITIAL_DISCORD> list = (List<TB_MARITIAL_DISCORD>) query.list();
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
	
	public List<TB_Maritial_Discord_Status_Child> getMaritial_DiscordChByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String updateid = " from TB_Maritial_Discord_Status_Child where maritial_id=:maritial_id and status=1";
			Query query = sessionHQL.createQuery(updateid).setParameter("maritial_id", id);
			List<TB_Maritial_Discord_Status_Child> list = (List<TB_Maritial_Discord_Status_Child>) query.list();
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
	
	public ArrayList<ArrayList<String>> search_maritial_count(String personnel_no)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
		
		
			if(personnel_no !="") {
				qry += " and upper(p.personnel_no) = ? ";
			}
			
			
			
			q="select COUNT(m.*) as count_id  from tb_psg_maritial_discord p \n" + 
					"inner join tb_psg_maritial_discord_status_child m on m.maritial_id = p.id \n" + 
					"inner join tb_psg_trans_proposed_comm_letter c \n" + 
					"on c.id =p.comm_id  where p.id !=0  and p.service_category = cast('1' as integer)    \n" + 
					"and   m.status = cast('1' as integer) and\n" + 
					"p.cancel_status in (-1,0,2) " +qry +  "";
					
				
				stmt=conn.prepareStatement(q);
             int j =1;
				
				
				if(personnel_no !="") {
					stmt.setString(j, personnel_no);
					j += 1;
				}
				
				
			
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("count_id"));//0
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
}
