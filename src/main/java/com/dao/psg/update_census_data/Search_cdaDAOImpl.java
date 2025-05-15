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


import com.models.psg.update_census_data.TB_CDA_ACC_NO;
import com.persistance.util.HibernateUtil;




public class Search_cdaDAOImpl implements Search_cdaDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> Search_CDA(String personnel_no,
			String status, String unit_sus_no, String unit_name,String rank, String cr_by,String cr_date,
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

			if (status.equals("0")) {
				qry += " cdn.status = '0'  "; // and cl.update_ofr_status =
													// '0'
			}
			else if (status.equals("1")) {
				qry += " cdn.status = '1' ";
			}
			else if (status.equals("3")) {
				qry += " cdn.status = '3' ";
			}
			else if (status.equals("4")) {
				qry += " cdn.status not in (0,3)  ";
				if(roleType.equals("DEO")){
					qry += "and  cdn.cancel_status in (-1,2)  ";
				}
				else if(roleType.equals("APP")) {
					qry += "and  cdn.cancel_status=0  ";
				}
			}
			
			if (!roleSusNo.equals("")) {
				qry += " and pro.unit_sus_no = ?";
			}
			if (!unit_sus_no.equals("")) {
				qry += "  and orb.sus_no = ?";
			}
			/*if (!unit_name.equals("")) {
				qry += "  and orb.unit_name = ?";
			}*/

			if(!personnel_no.equals("")) {
				qry += "  and upper(pro.personnel_no) like ? ";
			}

			if(!rank.equals("") ) {
			qry += "  and pro.rank = cast(? as integer) ";
			}

	       if (!cr_by.equals("")) {
			qry += "  and cast(l1.userid as character varying)= ? ";
			}
			if(!cr_date.equals("")) {
			qry +=" and cast(cdn.created_date as date) = cast(? as date)";
			}
				
			if (status.equals("4")) {
				q="select distinct \n" + 
						"cdn.comm_id,\n" + 
						"pro.personnel_no,\n" + 
						"cue.description,\n" + 
						"COALESCE((select cda_acc_no from tb_psg_cda_acc_num where comm_id=cdn.comm_id and status=1 order by id desc limit 1),'')\n" + 
						"	as  cda_acc_no,cdn.reject_remarks,cdn.cancel_status\n" + 
						"from tb_psg_cda_acc_num cdn\n" + 
						"inner join tb_psg_trans_proposed_comm_letter pro on cdn.comm_id=pro.id\n" + 
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pro.unit_sus_no and orb.status_sus_no='Active'\n" + 
						"inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=pro.rank and cue.status_active = 'Active'  \n" + 
						"left join logininformation l1 on l1.username =cdn.created_by\r\n" + 
						"where "+qry;	
				}
			else {
				q="select distinct \n" +
						"cdn.id,cdn.comm_id,\n" +
						"pro.personnel_no,\n" +
						"cue.description,\n" +
						"cdn.cda_acc_no,cdn.reject_remarks\n" +
						"from tb_psg_cda_acc_num cdn\n" +
						"inner join tb_psg_trans_proposed_comm_letter pro on cdn.comm_id=pro.id\n" +
						"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = pro.unit_sus_no and orb.status_sus_no='Active'\r\n" +
						"inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=pro.rank and cue.status_active = 'Active'  \n" +
						"left join logininformation l1 on l1.username =cdn.created_by\r\n" + 
						"where "+qry+ "order by cdn.id desc" ;
			}
			stmt=conn.prepareStatement(q);

		

			int j =1;
			{
			if (!roleSusNo.equals("")) {
				stmt.setString(j, roleSusNo);
				j += 1;
			}

			if (!unit_sus_no.equals("")) {
				stmt.setString(j, unit_sus_no);
				j += 1;
			}
			/*if (!unit_name.equals("")) {
				stmt.setString(j, unit_name);
				j += 1;
			}*/
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
	    	  	list.add(rs.getString("personnel_no"));
				list.add(rs.getString("description"));
				list.add(rs.getString("cda_acc_no"));

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

					String editData = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
							+ rs.getString("id") + ",'"+ rs.getString("comm_id") +"')}else{ return false;}\"";
					f4 = "<i class='action_icons action_update'  " + editData + " title='Edit Data'></i>";

				}
				if(roleType.equals("ALL") || roleType.equals("DEO") &&  status.equals("4"))
				{
					String View1 = "onclick=\"  {ViewCancelHistoryData('"+rs.getString("comm_id")+"','"+rs.getString("cancel_status")+"','-1')}\"";
			        f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";
				}
				if(roleType.equals("ALL") || roleType.equals("APP") &&  status.equals("4"))
				{
					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve Officer CDA Data?') ){ViewCancelHistoryData('"+rs.getString("comm_id")+"','0')}else{ return false;}\"";
				    f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";
				}

				list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				list.add(f4);
				list.add(rs.getString("reject_remarks"));
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
	



public ArrayList<ArrayList<String>> getcdaByid(String id){
	ArrayList<ArrayList<String>> blist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
		
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			//qry = "id !=  0";			
			
		
		
			
				q="select\n" + 
						"cdn.id,\n" + 
						"cdn.comm_id,"+
						"cdn.census_id,"+
						"pro.personnel_no,\n" + 
						"cue.description,\n" + 
						"cdn.cda_acc_no\n" + 
						"from tb_psg_cda_acc_num cdn\n" + 
						"inner join tb_psg_trans_proposed_comm_letter pro on cdn.comm_id=pro.id\n" + 
						"inner join cue_tb_psg_rank_app_master cue on cast(cue.id as integer)=pro.rank  and .status_active = 'Active'\n" + 
						"where cdn.id=?" ;
			
			stmt=conn.prepareStatement(q);
			
			
			
			int j =1;
		 

				stmt.setInt(1, Integer.parseInt(id));
				
		
	      ResultSet rs = stmt.executeQuery();   
	      
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("personnel_no"));
				list.add(rs.getString("cda_acc_no"));
				list.add(rs.getString("comm_id"));				
				list.add(rs.getString("census_id"));
				list.add(rs.getString("id"));
				
				
				
				
				
				
		
				 
				
				blist.add(list);
				
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
		return blist;
		
}


public TB_CDA_ACC_NO getSearch_CDAByid(int id) {
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	try {
		TB_CDA_ACC_NO updateid = (TB_CDA_ACC_NO) sessionHQL.get(TB_CDA_ACC_NO.class, id);
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

public ArrayList<ArrayList<String>> Popup_Change_of_CDA(BigInteger comm_id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		q="select  DISTINCT\n" + 
				"			s.id,\n" + 
				"			s.cda_acc_no,\n" + 
//				"			to_char(s.date_of_authority,'DD-MON-YYYY')as date_of_authority,\n" + 
//				"			to_char(s.date_of_seniority,'DD-MON-YYYY')as date_of_seniority,\n" + 
//				"			to_char(s.date_of_seniority,'DD-MON-YYYY') as old_seniority_date,\n" + 
				"			s.created_by,\n" + 
				"			to_char(s.created_date,'DD-MON-YYYY') as created_date,\n" + 
				"			s.modified_by,\n" + 
				"			to_char(s.modified_date,'DD-MON-YYYY') as modified_date\n" + 
				"FROM tb_psg_cda_acc_num s \n" + 
				"where s.status in (1,2) and s.comm_id::text =? order by s.id desc";
				
			stmt=conn.prepareStatement(q);
			
			stmt.setString(1,comm_id.toString());
			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("cda_acc_no"));
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

public List<Map<String, Object>> getHisChangeOfCDA(BigInteger comm_id,int status,HttpSession session) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	String accesslvl = session.getAttribute("roleAccess").toString();
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;		
		//String flag = "Y";
	
		if(status==-1)
			q="cdn.cancel_status in (-1,2)";
		else if(status==0) {
			q="cdn.cancel_status=0";
			if(!accesslvl.equals("MISO")) {
				q+="and (select user_sus_no from logininformation where username=cdn.created_by )!='' ";
			}
		}
			
		q = "select distinct \n" + 
				"cdn.id,(select user_sus_no from logininformation where username=cdn.created_by ) as unit_sus,cdn.census_id,cdn.comm_id,cdn.modified_by,ltrim(TO_CHAR(cdn.modified_date,'DD-MON-YYYY'),'0') as modified_date,cdn.status,cdn.cda_acc_no\n" + 
				"from tb_psg_cda_acc_num cdn\n" + 
				"where status in (1,2) and cdn.comm_id::text=? and  "+q+ " order by id desc";

		stmt = conn.prepareStatement(q);
		stmt.setString(1,comm_id.toString());

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
			String cancelData = "onclick=\"  if (confirm('Are you sure you want to Cancel Data?') ){CancelHisCDAData("
					+ rs.getObject(1) + ")}else{ return false;}\"";
			String cancelbtn="<a  class='btn btn-danger btn-sm' value='REMOVE' title = 'CANCEL'  "+cancelData+"'><i class='fa fa-times'></i></a>";
			String rejectData = "onclick=\"  if (confirm('Are you sure you want to Reject Data?') ){CancelHisCDAData("
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
public String approveHisCDA(BigInteger comm_id, List<Map<String, Object>> Lobj,String username,Date mod_date) {
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
			String hql_n = "update TB_CDA_ACC_NO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status, cancel_status=:cancel_status,cancel_date=:cancel_date,cancel_by=:cancel_by  "
					+ " where  id=:id";
			Query query_n = sessionhql.createQuery(hql_n).setInteger("status", -1).setInteger("cancel_status", 1).setTimestamp("cancel_date", mod_date).setString("cancel_by", username)
					.setInteger("id", id).setString("modified_by", username)
					.setTimestamp("modified_date", mod_date);
			msg = query_n.executeUpdate() > 0 ? "1" : "0";
			sessionhql.flush();
			sessionhql.clear();
		}
		
		if(app_id!=0) {
			String hqlUpdate="select id from TB_CDA_ACC_NO where comm_id=:comm_id and status in (1,2) order by id desc ";
			Query query = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setMaxResults(1);
			Integer c = (Integer)  query.uniqueResult();
		
			if(c!=null && c>0) {
				int chang_id=c.intValue();
				
				String hql_n = "update TB_CDA_ACC_NO set modified_date=:modified_date ,modified_by=:modified_by ,status=:status "
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




	
}
