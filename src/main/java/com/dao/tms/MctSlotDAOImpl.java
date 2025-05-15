package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_SLOT_MASTER;

import com.persistance.util.HibernateUtil;

public class MctSlotDAOImpl implements MctSlotDAO {
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
 

	public ArrayList<ArrayList<String>> search_mct_main(String mct_slot,String prf_code,String type_of_veh,String sus_no){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !sus_no.equals("")) {
				qry += " and upper(c.sus_no) like ? ";
			}
			/*if (!status1.equals("0") ) {
				qry += " and upper(status) = ?";
				
			}*/
			
			q="select distinct  c.id, c.mct_nomen,c.prf_code,c.type_of_veh,c.sus_no,"
					+ "concat(a.code_no_from, ',', a.code_no_to) as prf,c.mct_main_id\n"
					+ "from tb_tms_mct_main_master c inner join tb_tms_mct_slot_master a on a.prf_code =c.prf_code "
					+ "\n"
					+ "where c.id !=0" +qry;

		
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!sus_no.equals("")) {
					stmt.setString(j, "%"+sus_no.toUpperCase()+"%");
					j += 1;
				}
				/*if (!status1.equals("0") ) {
					stmt.setString(j, status1.toUpperCase());
					j++;
				}*/
			
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("mct_nomen"));
					list.add(rs.getString("prf_code"));
					list.add(rs.getString("type_of_veh"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("prf"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This MCT?') ){editData('"+ rs.getString("id") +"','"+rs.getString("mct_main_id")+"','"+rs.getString("sus_no")+"'"
							+ ",'"+rs.getString("prf_code")+"','"+rs.getString("type_of_veh")+"','"+rs.getString("prf")+"','"+rs.getString("mct_nomen")+"')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This MCT?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
					 
					list.add(f);
					list.add(f1);
					
					
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
    
    
    public String getmaxMCTMainID(String from,String to){
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			
	 		String query = null;
	 	    PreparedStatement stmt=null;
			query ="select min(ser) as min1 from generate_series(?,?) ser left join(select mct_main_id,(right(mct_main_id,4):: integer )as rightval from tb_tms_mct_main_master ) as seq on ser = seq.rightval where rightval is null";	
			stmt=conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(from) );
			stmt.setInt(2, Integer.parseInt(to));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("min1");    
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
    
    public String getmaxPRFCodeID(){
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
	 		String query = null;
			query ="select min(ser) as min1 from generate_series(1,999) ser left join(select prf_code,(right(prf_code,3):: integer )as rightval from tb_tms_mct_slot_master ) as seq on ser = seq.rightval where rightval is null";	
			ResultSet rs = stmt.executeQuery(query);
			
 	        while(rs.next()){
 	           whr=rs.getString("min1");             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
    
 
	@SuppressWarnings("unchecked")
	public Boolean checkIfExistMainID(String mainId) {
		String hql="from TB_TMS_MCT_MAIN_MASTER where mct_main_id=:mct_main_id";
		List<TB_TMS_MCT_MAIN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("mct_main_id",mainId);
			users = (List<TB_TMS_MCT_MAIN_MASTER>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean checkIfExistMCTRange(int code_from,int code_to) {
		String hql="from TB_TMS_MCT_SLOT_MASTER where (:code_from between code_no_from and code_no_to) or (:code_to between code_no_from and code_no_to)";
		List<TB_TMS_MCT_MAIN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("code_from", code_from);
			query.setParameter("code_to", code_to);
			users = (List<TB_TMS_MCT_MAIN_MASTER>) query.list();
			
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;
	
	}
	
	
	
	public String update_mct_slot(TB_TMS_MCT_MAIN_MASTER obj){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update TB_TMS_MCT_MAIN_MASTER set mct_nomen=:mct_nomen1,prf_code=:prf_code1,type_of_veh=:type_of_veh1,sus_no=:sus_no1,modified_by=:modified_by,modified_date=:modified_date where id=:id";		
			Query query = sessionHQL.createQuery(hql).setString("mct_nomen1",obj.getMct_nomen())
					
					.setString("prf_code1",obj.getPrf_code())
					.setString("type_of_veh1",obj.getType_of_veh())
					.setString("sus_no1",obj.getSus_no()).setInteger("id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
	
	
}