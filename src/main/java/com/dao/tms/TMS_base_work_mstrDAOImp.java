package com.dao.tms;

import java.math.BigInteger;
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

import com.models.TB_TMS_BASE_WORKSHOP_MASTER;
import com.models.TB_TMS_REPAIR_AGENCY_MASTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.persistance.util.HibernateUtil;

public class TMS_base_work_mstrDAOImp implements TMS_base_work_mstrDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> base_workshop_search_REPORT(String sus_no,
			String unit_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			qry = "id !=  0";
			if( !sus_no.equals("")) {
				qry += " and upper(sus_no) like ? ";		
			}
			
			if( !unit_name.equals("")) {
				qry += " and upper(unit_name) like ? ";		
			}
			sql1 = "select id,sus_no,unit_name from tb_tms_base_workshop_master where  " + qry+ "";

			stmt = conn.prepareStatement(sql1);
			if(!qry.equals(""))     
			{  int j =1;
				if( !sus_no.equals("")) {
					stmt.setString(j, sus_no.toUpperCase()+"%");
					j += 1;						
				}
				if( !unit_name.equals("")) {
					stmt.setString(j, unit_name.toUpperCase()+"%");
					j += 1;						
				}
			}
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				String f = "";
				String f1 = "";
				
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update?') ){editData('"+ rs1.getString("id") +"')}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				
				
				
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete?') ){deleteData("
						+ rs1.getString("id") + ")}else{ return false;}\"";
			  f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				
				
				list.add(f);
				list.add(f1);
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}	
	
	public ArrayList<ArrayList<String>> repair_agency_search_REPORT(String sermct_main_nomen,
			String re_agency) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			qry = "a.id !=  0";
			if( !sermct_main_nomen.equals("")) {
				qry += " and mct  like ? ";		
			}
			
			if( !re_agency.equals("")) {
				qry += " and repair like ? ";		
			}
			sql1 = "select a.id,a.mct,a.repair,b.mct_nomen from tb_tms_repair_agency_master a inner join tb_tms_mct_main_master b on a.mct=b.mct_main_id where  " + qry+ "";

			stmt = conn.prepareStatement(sql1);
			if(!qry.equals(""))     
			{  int j =1;
				if( !sermct_main_nomen.equals("")) {
					stmt.setString(j, sermct_main_nomen);
					j += 1;						
				}
				if( !re_agency.equals("")) {
					stmt.setString(j, re_agency);
					j += 1;						
				}
			}
			System.out.println("==="+stmt);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("mct"));
				list.add(rs1.getString("repair"));
				list.add(rs1.getString("mct_nomen"));
				String f = "";
				String f1 = "";
				
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update?') ){editData('"+ rs1.getString("id") +"')}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				
				
				
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete?') ){deleteData("
						+ rs1.getString("id") + ")}else{ return false;}\"";
			  f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				
				
				list.add(f);
				list.add(f1);
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	
	public TB_TMS_BASE_WORKSHOP_MASTER getbase_workshopByid(Integer id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_TMS_BASE_WORKSHOP_MASTER updateid = (TB_TMS_BASE_WORKSHOP_MASTER) sessionHQL.get(TB_TMS_BASE_WORKSHOP_MASTER.class, id);
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
public TB_TMS_REPAIR_AGENCY_MASTER getrepairByid(Integer id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_TMS_REPAIR_AGENCY_MASTER updateid = (TB_TMS_REPAIR_AGENCY_MASTER) sessionHQL.get(TB_TMS_REPAIR_AGENCY_MASTER.class, id);
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
	
	public String GetUpdatebase_workshop(TB_TMS_BASE_WORKSHOP_MASTER obj,String username){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
       
       String msg = "";
     try{
    		String hql = "update TB_TMS_BASE_WORKSHOP_MASTER set sus_no=:sus_no,unit_name=:unit_name,"
					+ "modified_by=:modified_by,modified_on=:modified_on where id=:id";
			
			
    		Query query = sessionHQL.createQuery(hql).setString("sus_no",obj.getSus_no()).setString("unit_name",obj.getUnit_name())
				.setString("modified_by",username).setTimestamp("modified_on", new Date()).setInteger("id",obj.getId());
			
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

	
	public String GetUpdaterepairshop(TB_TMS_REPAIR_AGENCY_MASTER obj,String username){
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
       
       String msg = "";
     try{
    		String hql = "update TB_TMS_REPAIR_AGENCY_MASTER set mct=:mct,repair=:repair,"
					+ "modified_by=:modified_by,modified_on=:modified_on where id=:id";
			
			
    		Query query = sessionHQL.createQuery(hql).setString("mct",obj.getMct()).setString("repair",obj.getRepair())
				.setString("modified_by",username).setTimestamp("modified_on", new Date()).setInteger("id",obj.getId());
			
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
	public Boolean getsus_noExist(String sus_no,Integer c_id) {
		Connection conn = null;
		Boolean msg = false;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "SELECT case when sum(count_pers.count) > 0 then false else true end FROM   \r\n" + 
					"(\r\n" + 
					"select count(id) from tb_tms_base_workshop_master where upper(sus_no)=? and status =0  and cast(id as character varying) != ?\r\n" + 
					") count_pers";

			Integer id =Integer.MIN_VALUE;
			if(c_id != null && !c_id.equals(""))
			{
				id = c_id;
			}
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, sus_no);
			stmt.setString(2, String.valueOf(id));
			
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
