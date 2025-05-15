package com.dao.avation;

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

import com.models.TB_AVIATION_ACT_MAIN_MASTER;
import com.persistance.util.HibernateUtil;

public class ActSlotDAOImpl implements ActSlotDAO  {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	

    public ArrayList<ArrayList<String>> search_act_main(String act_slot,String abc_code,String sus_no,String type_of_aircraft){
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
			
			q="select distinct  c.id, c.act_nomen,c.abc_code,c.sus_no,c.type_of_aircraft,"
					+ "concat(a.code_no_from, ',', a.code_no_to) as abc,c.act_main_id\n"
					+ "from tb_aviation_act_main_master c inner join tb_aviation_act_slot_master a on a.abc_code =c.abc_code "
					+ "\n"
					+ "where c.id !=0" +qry;

		
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!sus_no.equals("")) {
					stmt.setString(j, "%"+sus_no.toUpperCase()+"%");
					j += 1;
				}
				ResultSet rs = stmt.executeQuery();   
				System.out.println("1"+stmt);
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("act_nomen"));//0
					list.add(rs.getString("abc_code"));//1
					list.add(rs.getString("type_of_aircraft"));//2
					list.add(rs.getString("sus_no"));//3
					list.add(rs.getString("abc"));//4
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This ACT?') ){editData('"+ rs.getString("id") +"','"+rs.getString("act_main_id")+"','"+rs.getString("sus_no")+"'"
							+ ",'"+rs.getString("abc_code")+"','"+rs.getString("type_of_aircraft")+"','"+rs.getString("abc")+"','"+rs.getString("act_nomen")+"')}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This ACT?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
					 
					list.add(f);
					list.add(f1);
					
					
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
    
    public String getmaxACTMainID(String from,String to){
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			
	 		String query = null;
	 	    PreparedStatement stmt=null;
			query ="select min(ser) as min1 from generate_series(?,?) ser left join(select act_main_id,(right(act_main_id,4):: integer )as rightval from tb_aviation_act_main_master ) as seq on ser = seq.rightval where rightval is null";	
			stmt=conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(from) );
			stmt.setInt(2, Integer.parseInt(to));
			ResultSet rs = stmt.executeQuery();
			System.out.println("2"+stmt);
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
	public Boolean checkIfExistMainIDAviation(String mainId) {
		String hql="from TB_AVIATION_ACT_MAIN_MASTER where act_main_id=:act_main_id";
		List<TB_AVIATION_ACT_MAIN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("act_main_id",mainId);
			users = (List<TB_AVIATION_ACT_MAIN_MASTER>) query.list();
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
    
    public String getmaxABCCodeID(){
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			Statement stmt = (Statement)conn.createStatement();
	 		String query = null;
			query ="select min(ser) as min1 from generate_series(1,999) ser left join(select abc_code,(right(abc_code,3):: integer )as rightval from tb_aviation_act_slot_master) as seq on ser = seq.rightval where rightval is null";	
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
	public Boolean checkIfExistACTRange(int code_from,int code_to) {
		String hql="from TB_AVIATION_ACT_SLOT_MASTER where (:code_from between code_no_from and code_no_to) or (:code_to between code_no_from and code_no_to)";
		List<TB_AVIATION_ACT_MAIN_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("code_from", code_from);
			query.setParameter("code_to", code_to);
			users = (List<TB_AVIATION_ACT_MAIN_MASTER>) query.list();
			
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
}
