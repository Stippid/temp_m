package com.dao.psg.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Transaction.TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY;
import com.persistance.util.HibernateUtil;

public class Link_Appointment_with_super_supernumeraryDAOImpl implements Link_Appointment_with_super_supernumeraryDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_appointment(String appointment,String status)	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String qry="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
	
//		if( appointment !=0) {
//			qry += " and upper(c.appointment) = ? ";
//		}
//		
		if( !appointment.equals("")) {
			qry += " where upper(appointment) = ? ";
		}
		q="select id, appointment from tb_psg_link_appoinment_with_super_supernumerary" + qry;
		
	
			
			stmt=conn.prepareStatement(q);
			int j =1;
			
			
//			if(appointment !=0) {
//				stmt.setInt(j, appointment);
//				j += 1;
//			}
			if(!appointment.equals("")) {
				stmt.setString(j, appointment.toUpperCase());
				j += 1;
			}
			
			ResultSet rs = stmt.executeQuery();      
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("appointment"));
				
				
				
				String f = "";
				String f1 = "";
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Appointment?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("appointment")  + "')}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Appointment?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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

	public String updateAppointment(TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY obj){
  		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
  		 Transaction tx = sessionHQL.beginTransaction();
  		
  		String msg = "";
  		try{
  			String hql = "update TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY set	appointment=:appointment, "
  					+ " approved_by=:approved_by,approved_date=:approved_date where id=:id";	
  			Query query = sessionHQL.createQuery(hql)
  					.setString("appointment",obj.getAppointment())
  					.setDate("approved_date",new Date())
  					.setString("approved_by",obj.getApproved_by())
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
// public TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY getappointmentByid(int id) {
//		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//	 	Transaction tx = sessionHQL.beginTransaction();
//	 	TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY updateid = (TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY) sessionHQL.get(TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY.class, id);
//		sessionHQL.getTransaction().commit();
//		sessionHQL.close();		
//		return updateid;
//	}



}
