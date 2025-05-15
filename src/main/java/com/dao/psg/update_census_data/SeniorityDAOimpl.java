package com.dao.psg.update_census_data;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;
public class SeniorityDAOimpl implements SeniorityDAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> getseniorityData(BigInteger comm_id)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	try{	
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
				q="select DISTINCT \n" + 
						"			s.id,\n" + 
						"			s.authority,\n" + 
						"			s.date_of_authority,\n" + 
						"			s.date_of_seniority,\n" + 
						"			to_char(s.date_of_seniority,'DD-MON-YYYY') as old_seniority_date,s.applicablity_date,s.reason \n" + 
						"FROM tb_psg_change_of_seniority s \n" + 
						"where s.comm_id::text =? and status=0 order by s.id desc " ;
			stmt=conn.prepareStatement(q);
		
			stmt.setString(1, String.valueOf(comm_id));
			ResultSet rs = stmt.executeQuery();   
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  list.add(rs.getString("id"));	
	    	  list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				list.add(rs.getString("date_of_seniority"));
				list.add(rs.getString("old_seniority_date"));
				list.add(rs.getString("applicablity_date"));
				list.add(rs.getString("reason"));
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
	public ArrayList<ArrayList<String>> getoldseniorityDate(BigInteger comm_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	
				conn = dataSource.getConnection();
				PreparedStatement stmt=null;
				q="select DISTINCT \n" + 
				"			ltrim(to_char(s.date_of_seniority,'DD-MON-YYYY'),'') as old_seniority_date\n" + 
				"FROM tb_psg_change_of_seniority s \n" + 
				"where s.comm_id::text =? and status=1  " ;
				stmt=conn.prepareStatement(q);
			
				stmt.setString(1, String.valueOf(comm_id));
				ResultSet rs = stmt.executeQuery();   
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("old_seniority_date"));
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
	public ArrayList<ArrayList<String>> Search_Seniority(String personnel_no,
			String status, String unit_sus_no, String unit_name,String rank,String cr_by,String cr_date,
			String roleSusNo,
			String roleType)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String qry="";
	try{	
		
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
			if (!status.equals("") && !status.equals("4")) {
				qry += " sd.status = cast(? as integer)  ";
			}
			else if (status.equals("4")) {
				qry += " sd.status not in (0,3)  ";
				if(roleType.equals("DEO")){
					qry += "and  sd.cancel_status in (-1,2)  ";
				}
				else if(roleType.equals("APP")) {
					qry += "and  sd.cancel_status=0  ";
				}
			}
			if (!roleSusNo.equals("")) {
				qry += " and pro.unit_sus_no = ?";
			}
			if(!personnel_no.equals("")) {
				qry += "  and upper(pro.personnel_no) like ? ";
			}
			if(!rank.equals("") ) {
			qry += "  and pro.rank = cast(? as integer) ";
			}
			if(!cr_by.equals("")) {
				qry += " and cast(l1.userid as character varying) = ? ";				
					
				}
			  
			  if(!cr_date.equals("") && !cr_date.equals("DD/MM/YYYY")) {
					
				  qry += " and cast(sd.created_date as date) = cast(? as date)";	
					
					
					
				}
			 if (status.equals("4")) {
				 q="select distinct\n" + 
				 		"			sd.comm_id,\n" + 
				 		"			pro.personnel_no,\n" + 
				 		"			cue.description,\n" + 
				 		"			COALESCE((select ltrim(TO_CHAR(date_of_seniority,'DD-MON-YYYY'),'0') from tb_psg_change_of_seniority where comm_id=sd.comm_id and status=1 order by id desc limit 1),'')\n" + 
				 		"			as date_of_seniority ,\n" + 
				 		"			COALESCE((select authority from tb_psg_change_of_seniority where comm_id=sd.comm_id and status=1 order by id desc limit 1),'')\n" + 
				 		"			as authority ,\n" + 
				 		"			COALESCE((select ltrim(TO_CHAR(date_of_authority,'DD-MON-YYYY'),'0') from tb_psg_change_of_seniority where comm_id=sd.comm_id and status=1 order by id desc limit 1),'')\n" + 
				 		"			as date_of_authority,sd.reject_remarks , COALESCE(TO_CHAR(sd.applicablity_date,'DD-MON-YYYY'),'') as  applicablity_date \n" + 
				 		"			from tb_psg_change_of_seniority sd\n" + 
				 		"			inner join tb_psg_trans_proposed_comm_letter pro on sd.comm_id=pro.id\n" + 
				 		"			inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pro.unit_sus_no and orb.status_sus_no='Active'\n" + 
				 		"			inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=pro.rank and cue.status_active = 'Active'"
				 	+"          left join logininformation l1 on l1.username = sd.created_by  \n" + 
				 		"			where  " + qry;
			 }
			 else {
			 q="select distinct\n" + 
						"			sd.id,sd.comm_id,\n" + 
						"			pro.personnel_no,\n" + 
						"			cue.description, \n" + 
						"			to_char(sd.date_of_seniority,'DD-MON-YYYY')as date_of_seniority,\n" + 
						"			sd.authority,\n" + 
						"			to_char(sd.date_of_authority,'DD-MON-YYYY')as date_of_authority,sd.reject_remarks,  COALESCE(TO_CHAR(sd.applicablity_date,'DD-MON-YYYY'),'') as  applicablity_date\n" + 
						"			from tb_psg_change_of_seniority sd\n" + 
						"			inner join tb_psg_trans_proposed_comm_letter pro on sd.comm_id=pro.id\n" + 
						"			inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pro.unit_sus_no and orb.status_sus_no='Active'\n" + 
						"			inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=pro.rank and cue.status_active = 'Active'"
						+"          left join logininformation l1 on l1.username = sd.created_by  \n" 
						+ " where "+qry+ "order by sd.id desc" ;
			 }
			stmt=conn.prepareStatement(q);
			
			
			if(!qry.equals(""))     
			{ 
					int j =1;
					if (!status.equals("") && !status.equals("4")) {
						stmt.setString(j, status);
						j += 1;
					}
					if (!roleSusNo.equals("")) {
						stmt.setString(j, roleSusNo);
						j += 1;
					}
					if (!personnel_no.equals("")) {
						stmt.setString(j, personnel_no.toUpperCase() + "%");
						j += 1;
					}
					if(!rank.equals("")) {
						stmt.setString(j, rank);
						j += 1;
					}
					if (!cr_by.equals("")) {
						stmt.setString(j, cr_by);
						j += 1;
					}
					 if(!cr_date.equals("")  &&  !cr_date.equals("DD/MM/YYYY")) {
						stmt.setString(j, cr_date);
						j += 1;
					}
			}
			
			 ResultSet rs = stmt.executeQuery();   
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("personnel_no"));
				list.add(rs.getString("description"));
				list.add(rs.getString("authority"));
				list.add(rs.getString("date_of_authority"));
				list.add(rs.getString("date_of_seniority"));
				String f = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				String f4 = "";
				if(roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))
				{
					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("comm_id")+ ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
					
					String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"')}else{ return false;}\"";					
					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

				}
				if(roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3") || (status.equals("0"))))
				{
					String editData = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData('"
							+ rs.getString("id") + "','"+rs.getString("comm_id")+"')}else{ return false;}\"";
					f4 = "<i class='action_icons action_update'  " + editData + " title='Edit Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
				{
					String View1 = "onclick=\"  {ViewCancelHistoryData('"+rs.getString("comm_id")+"','-1')}\"";
			        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("4"))
				{
					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve Officer Seniority Data?') ){ViewCancelHistoryData('"+rs.getString("comm_id")+"','0')}else{ return false;}\"";
				    f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
				}
				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(f4);
				list.add(rs.getString("reject_remarks"));
				list.add(rs.getString("applicablity_date"));
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
	public TB_PSG_CHANGE_OF_SENIORITY getSearch_SeniorityByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_PSG_CHANGE_OF_SENIORITY updateid = (TB_PSG_CHANGE_OF_SENIORITY) sessionHQL.get(TB_PSG_CHANGE_OF_SENIORITY.class, id);
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
	public ArrayList<ArrayList<String>> Popup_Change_of_seniority(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			q="select  DISTINCT\n" + 
					"			s.id,\n" + 
					"			s.authority,\n" + 
					"			to_char(s.date_of_authority,'DD-MON-YYYY')as date_of_authority,\n" + 
					"			to_char(s.date_of_seniority,'DD-MON-YYYY')as date_of_seniority,\n" + 
					"			to_char(s.date_of_seniority,'DD-MON-YYYY') as old_seniority_date,\n" + 
					"			s.reason,s.created_by,\n" + 
					"			to_char(s.created_date,'DD-MON-YYYY') as created_date,\n" + 
					"			s.modified_by,\n" + 
					"			to_char(s.modified_date,'DD-MON-YYYY') as modified_date\n" + 
					"FROM tb_psg_change_of_seniority s \n" + 
					"where s.status in (1,2) and s.comm_id::text =? order by s.id desc";
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));
					list.add(rs.getString("date_of_authority"));
					list.add(rs.getString("old_seniority_date"));
					list.add(rs.getString("date_of_seniority"));
					list.add(rs.getString("reason"));
					list.add(rs.getString("created_by"));
					list.add(rs.getString("created_date"));
					list.add(rs.getString("modified_by"));
					list.add(rs.getString("modified_date"));
					
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
	public List<Map<String, Object>> getHisChangeOfSeniority(BigInteger comm_id,int status,HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String accesslvl = session.getAttribute("roleAccess").toString();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;		
			
			if(status==-1)
				q="sd.cancel_status in (-1,2)";
			else if(status==0) {
				q="sd.cancel_status=0";
				if(!accesslvl.equals("MISO")) {
					q+="and (select user_sus_no from logininformation where username=sd.created_by )!='' ";
				}
			}
			q = "select distinct\n" + 
				"			sd.id,sd.comm_id,sd.status,(select user_sus_no from logininformation where username=sd.created_by ) as unit_sus,\n" + 
				"			sd.census_id,\n" + 
				"			to_char(sd.date_of_seniority,'DD-MON-YYYY')as date_of_seniority,\n" + 
				"			to_char(sd.applicablity_date,'DD-MON-YYYY')as eff_date_of_authority,\n" + 
				"			sd.authority,\n" + 
				"			to_char(sd.date_of_authority,'DD-MON-YYYY')as date_of_authority,\n" + 
				"			to_char(sd.modified_date,'DD-MON-YYYY')as modified_date,\n" + 
				"			sd.modified_by\n" + 
				"			from tb_psg_change_of_seniority sd\n" + 
				"			where  sd.status not in (0,3) and sd.comm_id::text=? \n" + 
				"			and (select count(*) from tb_psg_change_of_comission remp where remp.comm_id=sd.comm_id and remp.date_of_seniority=sd.date_of_seniority)=0\n" + 
				"			and "+q+" order by sd.id desc ";

			stmt = conn.prepareStatement(q);
			stmt.setString(1,String.valueOf(comm_id));
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					if(rs.getObject(i)==null)
						columns.put(metaData.getColumnLabel(i), "");
					else
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisSeniorityData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
				String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisSeniorityData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String rejectbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'Reject'  "+rejectData+"'><i class='fa fa-times'></i></a>";
				if(status==-1 ) {
					if((rs.getString("unit_sus")==null || rs.getString("unit_sus").equals("")) && !accesslvl.equals("MISO") )
						columns.put("action", "");
					else
					 columns.put("action", cancelbtn);
				}
				if(status==0) {
					columns.put("action", rejectbtn);
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
	@Override
	public String approveHisseniority(BigInteger comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
		// TODO Auto-generated method stub
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();	
		String msg = "0";
		int app_id=0;
		Transaction tx = sessionhql.beginTransaction();
		try {
			for(int i=0;i<Lobj.size();i++) {
				int id=Integer.parseInt(Lobj.get(i).get("id").toString());
				int status=Integer.parseInt(Lobj.get(i).get("status").toString());
				if(status==1) {
					app_id=id;
				}
				String hql_n = "update TB_PSG_CHANGE_OF_SENIORITY set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
						+ " where  id=:id";
				Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
						.setInteger("id", id).setString("modified_by", username)
						.setTimestamp("modified_date", mod_date);
				msg = query_n.executeUpdate() > 0 ? "1" : "0";
				sessionhql.flush();
				sessionhql.clear();
			}
		
			if(app_id!=0) {
				String hqlUpdate="select id from TB_PSG_CHANGE_OF_SENIORITY where comm_id=:comm_id and status in (1,2) order by id desc ";
				Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
				Integer c = (Integer)  query.uniqueResult();
			
				if(c!=null && c>0) {
					int chang_id=c.intValue();
			
					String hql_n = "update TB_PSG_CHANGE_OF_SENIORITY set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
							+ " where  id=:id";
					Query query_n = sessionhql.createQuery(hql_n).setInteger("status", 1)
							.setInteger("id", chang_id).setString("modified_by", username)
							.setTimestamp("modified_date", mod_date);
					msg = query_n.executeUpdate() > 0 ? "1" : "0";
					sessionhql.flush();
					sessionhql.clear();
				}
			}
			tx.commit();
			}catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				return "0";
			}
			return msg;
	}
	
	// pranay 29-10-24
	public ArrayList<ArrayList<String>> Popup_Change_of_tnaino(BigInteger comm_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			q="select  DISTINCT\n" + 
					"			s.id,\n" + 
					"			s.authority,\n" + 
					"			to_char(s.date_of_authority,'DD-MON-YYYY')as date_of_authority,\n" + 					
					"			s.tnai_no,s.created_by,\n" + 
					"			to_char(s.created_date,'DD-MON-YYYY') as created_date,\n" + 
					"			s.approved_by,\n" + 
					"			to_char(s.approved_date,'DD-MON-YYYY') as approved_date\n" + 
					"FROM tb_psg_change_tnai_no s \n" + 
					"where s.status in (1,2) and s.comm_id::text =? order by s.id desc";
				stmt=conn.prepareStatement(q);
				stmt.setString(1,String.valueOf(comm_id));
				System.err.println("\n HISTORY CHANGE TNAI \n\n" +stmt );
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));
					list.add(rs.getString("date_of_authority"));			
					list.add(rs.getString("tnai_no"));
					list.add(rs.getString("created_by"));
					list.add(rs.getString("created_date"));
					list.add(rs.getString("approved_by"));
					list.add(rs.getString("approved_date"));
					
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
