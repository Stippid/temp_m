package com.dao.avation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.TB_AVIATION_MAKE_MASTER;
import com.models.TB_TMS_MAKE_MASTER;
import com.persistance.util.HibernateUtil;

public class MakeMasterAviationDAOImp implements MakeMasterAviationDAO {

	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public String getmaxMakeNo(String actSlotId){
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
	 		String query = null;
			query="select min(ser) from generate_series(1,999) ser left join(select make_no,(right(make_no,3):: integer )as rightval from tb_aviation_make_master where act_slot_id= ? ) as seq on ser = seq.rightval where rightval is null";	
			PreparedStatement stmt=conn.prepareStatement(query);
			stmt.setString(1,actSlotId);
			ResultSet rs = stmt.executeQuery();
     	        while(rs.next()){
     	           whr=rs.getString("min");     
			
     	}
		}catch (SQLException e) {
     			e.printStackTrace();
     	}	
        return whr;
	}


	public Boolean ifExistMakeNo(String act_slot_id, String make_no) {
		 String q = "from TB_AVIATION_MAKE_MASTER where act_slot_id =:act_slot_id and make_no =:make_no";
    	 List<TB_AVIATION_MAKE_MASTER> created_by = null;
    	 Session session = HibernateUtil.getSessionFactory().openSession();
    	 Transaction tx = session.beginTransaction();
    	 try {
    		 Query query = session.createQuery(q);
    		 query.setParameter("act_slot_id", act_slot_id).setParameter("make_no", make_no);
    		 created_by = (List<TB_AVIATION_MAKE_MASTER>) query.list();
    		 tx.commit();
    		 session.close();
    	 }catch(Exception e) {
    		 session.getTransaction().rollback();
    		 e.printStackTrace();
    		 return null; 
    	 }
    	 if(created_by.size() > 0) {
    		 return true;
    	 }
    	 return false;
	}
}