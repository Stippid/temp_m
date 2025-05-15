package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_PURPOSEOF_VISIT;
import com.persistance.util.HibernateUtil;

public class Purposeof_VisitDAOImpl implements Purposeof_VisitDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_visitPurposedtl(String visitPurpose,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !visitPurpose.equals("")) {
				qry += " and upper(visit_purpose) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct id,visit_purpose,status from tb_psg_mstr_purposeof_visit c \r\n" + 
					"where c.id is not null "+qry+" order by visit_purpose" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				if(!visitPurpose.equals("")) {
					stmt.setString(j, "%"+visitPurpose.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("visit_purpose"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Type of Entry Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Type of Entry Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		/*
					if(status.equals("inactive"))
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
	public TB_PSG_MSTR_PURPOSEOF_VISIT getmtByid(int id) { Session sessionHQL =
			  HibernateUtil.getSessionFactory().openSession(); Transaction tx =
			  sessionHQL.beginTransaction(); TB_PSG_MSTR_PURPOSEOF_VISIT updateid = (TB_PSG_MSTR_PURPOSEOF_VISIT)
			  sessionHQL.get(TB_PSG_MSTR_PURPOSEOF_VISIT.class, id); sessionHQL.getTransaction().commit();
			  sessionHQL.close(); return updateid; }
	
	
	public ArrayList<ArrayList<String>> search_visitPurposeReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				
			q="select distinct id,visit_purpose,status from tb_psg_mstr_purposeof_visit c \r\n" + 
					"where c.id is not null  order by visit_purpose" ;
				stmt=conn.prepareStatement(q);
				int i =1;
					
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("visit_purpose"));
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
