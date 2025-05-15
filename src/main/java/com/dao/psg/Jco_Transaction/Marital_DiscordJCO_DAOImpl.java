package com.dao.psg.Jco_Transaction;

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

import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_JCO;
import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO;
import com.models.psg.Transaction.TB_MARITIAL_DISCORD;
import com.models.psg.Transaction.TB_Maritial_Discord_Status_Child;
import com.persistance.util.HibernateUtil;

public class Marital_DiscordJCO_DAOImpl implements Marital_DiscordJCO_DAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public String GetUpdateMarital_DiscordJCOParent(TB_PSG_MARITAL_DISCORD_JCO obj,String username){
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
	  String msg = "";
	  try{
			 String hql = "update TB_PSG_MARITAL_DISCORD_JCO set personnel_no=:personnel_no,name_lady=:name_lady,status='0',dt_of_complaint=:dt_of_comp,"
			 		+ " complaint=:complaint,modified_by=:modified_by,modified_date=:modified_date where id=:id";
	                             
	    	  Query query = sessionHQL.createQuery(hql)
	    				.setString("personnel_no",obj.getPersonnel_no())
	    			  	.setString("name_lady",obj.getName_lady().trim())
	    			  	.setTimestamp("dt_of_comp", obj.getDt_of_complaint())
	    			  	.setString("complaint", obj.getComplaint().trim())
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
	
	public String GetUpdateMarital_DiscordJCOChild(TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO obj,String username) {
		
	   Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	   Transaction tx = sessionHQL.beginTransaction();
	  
	  String msg = "";
	  try{
	  
			 String hql = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set status_of_case=:status_of_case,status='0',"
			 		+ " modified_by=:modified_by,modified_date=:modified_date where marital_id=:maritial_id and id=:id ";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    			  	.setString("status_of_case",obj.getStatus_of_case().trim())
						.setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setInteger("maritial_id",obj.getMarital_id())
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
	
	public ArrayList<ArrayList<String>> search_marital_count(String personnel_no)
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
			
			q="select COUNT(m.*) as count_id  from tb_psg_marital_discord_jco p \n" + 
					"inner join tb_psg_marital_discord_status_child_jco m on m.marital_id = p.id \n" + 
					"inner join tb_psg_census_jco_or_p c \n" + 
					"on c.id =p.jco_id  where p.id !=0   \n" + 
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
	
	
	public ArrayList<ArrayList<String>> Search_JCOMarital(String personnel_no,
			String status,String roleType,String from_date,String cr_by,String cr_date,String roleAccess,String roleSusNo)
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
			if(!status.equals("") && !status.equals("4")) {
			    qry += " and p.status = cast(? as integer) and  m.status = cast(? as integer) and p.cancel_status in (-1,0,2) ";
			}
			if(roleType.equals("DEO") && status.equals("4")) {
				qry += " and cast(m.status as character varying) = ? and m.cancel_status in (-1,2)";
			} 
			if(roleType.equals("APP") && status.equals("4")) {
				qry += " and cast(m.status as character varying) = ? and (p.cancel_status = 0 or m.cancel_status = 0)";
			} 
			
			if (roleAccess.equals("Unit")) {
				qry += " and upper(c.unit_sus_no) = ? ";
			}
			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(p.created_date as date) = cast(? as date)";
			}
	
	
			
			q="select p.id,p.personnel_no,p.name_lady,m.status_of_case,p.reject_remarks from tb_psg_marital_discord_jco p "
					+ " inner join tb_psg_marital_discord_status_child_jco m on m.marital_id = p.id "
					+	"left join logininformation l1 on l1.username =p.created_by\r\n"  
					+ "inner join tb_psg_census_jco_or_p c on c.id =p.jco_id  where p.id !=0 " +qry +  "order by p.id desc";
				
				stmt=conn.prepareStatement(q);
				
				
				int j =1;
				
				if(personnel_no !="") {
					stmt.setString(j, personnel_no);
					j += 1;
				}
				
				if(!status.equals("") && !status.equals("4")) {
					
						stmt.setString(j, status);
						j += 1;
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
						if(search_marital_count(rs.getString("personnel_no")).size() > 0)
						{
							status_count = search_marital_count(rs.getString("personnel_no")).get(0).get(0);
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
						String Approve = "onclick=\"  {approveData(" + rs.getInt("id") + ")}\"";
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
					if(roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("4"))
					{
						String View1 = "onclick=\"  {ApprHistoryData("+ rs.getString("id") + ")}\"";
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
	
	@SuppressWarnings("unused")
	public TB_PSG_MARITAL_DISCORD_JCO getmaritalByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_PSG_MARITAL_DISCORD_JCO updateid = (TB_PSG_MARITAL_DISCORD_JCO) sessionHQL.get(TB_PSG_MARITAL_DISCORD_JCO.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	
	public ArrayList<ArrayList<String>> Marital_DiscordJCO_History(String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			
			q="SELECT distinct ch.id, ch.status_of_case,  "
					+ "ch.created_by,TO_CHAR(ch.created_date ,'DD-MON-YYYY') as created_date, "
					+ "ch.modified_by,TO_CHAR(ch.modified_date ,'DD-MON-YYYY') as modified_date \n"  
					+ "FROM tb_psg_marital_discord_status_child_jco ch "
					+ "inner join tb_psg_marital_discord_jco p on p.id = ch.marital_id and p.status = '1' "
					+ "WHERE ch.status in (1,2) AND ch.marital_id = ? order by ch.id desc";
				
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
	
	
	public ArrayList<ArrayList<String>> Popup_Marital_DiscordJCO_History(int jco_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
		
			
			q="SELECT distinct ch.id, ch.status_of_case,  "
					+ "ch.created_by,TO_CHAR(ch.created_date ,'DD-MON-YYYY') as created_date, "
					+ "ch.modified_by,TO_CHAR(ch.modified_date ,'DD-MON-YYYY') as modified_date \n"  
					+ "FROM tb_psg_marital_discord_status_child_jco ch "
					+ "inner join tb_psg_marital_discord_jco p on p.id = ch.marital_id "
					+ "WHERE ch.status in (1,2) AND p.jco_id = ? order by ch.id desc";
					
				
				stmt=conn.prepareStatement(q);
				
				stmt.setInt(1,jco_id);
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("status_of_case"));//0
					list.add(rs.getString("created_by"));//1
					list.add(rs.getString("created_date"));//2
					list.add(rs.getString("modified_by"));//3
					list.add(rs.getString("modified_date"));//4
					
					
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
	
	@SuppressWarnings("unchecked")
	public List<TB_PSG_MARITAL_DISCORD_JCO> getMarital_DiscordByidJCO(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String updateid = " from TB_PSG_MARITAL_DISCORD_JCO where id=:id";
			Query query = sessionHQL.createQuery(updateid).setParameter("id", id);
			List<TB_PSG_MARITAL_DISCORD_JCO> list = (List<TB_PSG_MARITAL_DISCORD_JCO>) query.list();
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
	
	@SuppressWarnings("unchecked")
	public List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> getMarital_DiscordChildByidJCO(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String updateid = " from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where marital_id=:maritial_id and status=1";
			Query query = sessionHQL.createQuery(updateid).setParameter("maritial_id", id);
			List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> list = (List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO>) query.list();
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

}
