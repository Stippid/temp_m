package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_DIV_GRADE;

import com.persistance.util.HibernateUtil;

public class RhDaoImpl implements RhDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Rh(String rh)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			/*if( !country_id.e ) {
				qry += " and country_id = ? ";
			}*/
			if( !rh.equals("")) {
				qry += " and rh = ? ";
			}
			q="select distinct id,rh from tb_psg_mstr_rh  " + 
					"where id  is not null "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				
				/*if(!religion_name.equals("")) {
					stmt.setString(j, religion_name.toUpperCase());
					j += 1;
				}*/
				if(!rh.equals("")) {
					stmt.setString(j, rh.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("rh"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This RH?') ){editData("+ rs.getString("Id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This RH?') ){deleteData(" + rs.getString("Id") + ")}else{ return false;}\"";
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
	 public TB_DIV_GRADE getrhByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_DIV_GRADE updateid = (TB_DIV_GRADE) sessionHQL.get(TB_DIV_GRADE.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
}
