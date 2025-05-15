package com.dao.mnh;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.mnh.Scrutiny_Search_Model;
import com.models.mnh.Tb_Med_BedOccupancy;
import com.persistance.util.HibernateUtil;

public class MsAccessDataUploadDAOImpl implements MsAccessDataUploadDAO {
	
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/*public int getCheckIfExits(String and_no,String sus_no) {
		Connection conn = null;
        int str1=0;
        try {
        	conn = dataSource.getConnection();
            String sq1="";                            
            sq1 = "SELECT count(*) cnt from tb_med_patient_details WHERE and_no= ? and medical_unit=?";
            PreparedStatement stmt = conn.prepareStatement(sq1);
            stmt.setString(1, and_no);
            stmt.setString(2, sus_no);
            ResultSet rs = stmt.executeQuery();   
            while(rs.next()){
            	str1=Integer.parseInt(rs.getString("cnt"));
            }  
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException e){
    	   e.printStackTrace();
       	}
        return str1;
    }*/
	
	 public List<Scrutiny_Search_Model> getCheckIfExits(String and_no,String sus_no) {
		 
		
    	Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = sessionVersion.beginTransaction();
		Query q1 = sessionVersion.createQuery("from Scrutiny_Search_Model where and_no=:and_no and medical_unit=:sus_no");
		
		q1.setParameter("and_no", and_no);
		q1.setParameter("sus_no",sus_no);
		@SuppressWarnings("unchecked")
		List<Scrutiny_Search_Model> tb_med_patient =  (List<Scrutiny_Search_Model>) q1.list();
	
		tx1.commit();
		sessionVersion.close();
		return tb_med_patient;
	}
	 
	public List<Tb_Med_BedOccupancy> getCheckIfExitsBedOccup(String sus_no,Date BO_DATE,String CATEGORY,String WARD){
		Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = sessionVersion.beginTransaction();
		Query q1 = sessionVersion.createQuery("from Tb_Med_BedOccupancy where sus_no=:sus_no and bo_date=:bo_date and category=:category and ward=:ward");
		q1.setParameter("sus_no", sus_no);
		q1.setParameter("bo_date", BO_DATE);
		q1.setParameter("category", CATEGORY);
		q1.setParameter("ward", WARD);
		
		@SuppressWarnings("unchecked")
		List<Tb_Med_BedOccupancy> tb_med_patient = (List<Tb_Med_BedOccupancy>) q1.list();
		tx1.commit();
		sessionVersion.close();
		return tb_med_patient;
	}
}