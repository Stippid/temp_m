package com.dao.Jco_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Jco_Master.TB_MSTR_PROMOTIONAL_EXAM_JCO;
import com.persistance.util.HibernateUtil;

public class Promotional_Exam_DAOImpl_JCO implements Promotional_Exam_DAO_JCO{
	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_PromoExamJCO(String promoExam,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !promoExam.equals("")) {
				qry += " and upper(promo_exam) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct id,promo_exam,status from tb_psg_mstr_promotional_exam_jco c \r\n" + 
					"where c.id is not null "+qry+" order by promo_exam" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				if(!promoExam.equals("")) {
					stmt.setString(j, "%"+promoExam.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("promo_exam"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Promotional Exam Detail?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Promotional Exam Detail?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					// bisag 091222 v2 (inactive editable)
					/*if(status.equals("inactive"))
					{
						
						list.add("");
						list.add("");

					}
					else {*/
					list.add(f);
					list.add(f1);
					//}
					
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
	public TB_MSTR_PROMOTIONAL_EXAM_JCO getmtByidJCO(int id)
	{ 
		Session sessionHQL =  HibernateUtil.getSessionFactory().openSession(); 
		Transaction tx = sessionHQL.beginTransaction(); 
		TB_MSTR_PROMOTIONAL_EXAM_JCO updateid = (TB_MSTR_PROMOTIONAL_EXAM_JCO)sessionHQL.get(TB_MSTR_PROMOTIONAL_EXAM_JCO.class, id); 
		sessionHQL.getTransaction().commit();
			  sessionHQL.close(); return updateid; 
			  }
	
	
	public ArrayList<ArrayList<String>> PromoExamReportJCO()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			q="select distinct id,promo_exam,status from tb_psg_mstr_promotional_exam_jco c \r\n" + 
					"where c.id is not null order by promo_exam" ;
				stmt=conn.prepareStatement(q);
				int i =1;
					
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("promo_exam"));
					list.add(rs.getString("status"));
					
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

}
