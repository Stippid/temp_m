package com.dao.Jco_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.model.Jco_Master.TB_PSG_MSTR_GORKHA_DOMISILE_JCO;
import com.persistance.util.HibernateUtil;

public class Gorkha_Domisile_DaoImpl_JCO implements Gorkha_Domisile_DAO_JCO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Gorkha_Domisile(String domisile,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			if( !domisile.equals("")) {
				qry += " and upper(gd.domisile) like ? ";
			}
			
			if (!status.equals("0") ) {
				qry += " and gd.status = ?";
				
			}
			
			q="SELECT DISTINCT gd.id, UPPER(gd.domisile) as domisile, gd.status FROM tb_psg_mstr_gorkha_domisile_jco gd  " + 
					"where id !=0 "+qry+ "ORDER BY domisile" ;
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!domisile.equals("")) {
					stmt.setString(j, domisile.toUpperCase()+"%");
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("domisile"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Gorkha Domicile?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Gorkha Domicile?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
					if(status.equals("inactive"))
					{
						
						list.add("");
						list.add("");

					}
					else {
					list.add(f);
					list.add(f1);
					}
					
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
	public TB_PSG_MSTR_GORKHA_DOMISILE_JCO getGorkhaDomisileByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_MSTR_GORKHA_DOMISILE_JCO updateid = (TB_PSG_MSTR_GORKHA_DOMISILE_JCO) sessionHQL.get(TB_PSG_MSTR_GORKHA_DOMISILE_JCO.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public ArrayList<ArrayList<String>> Gorkha_Domisile_report()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				
				q="SELECT DISTINCT gd.id, UPPER(gd.domisile) as domisile, gd.status FROM tb_psg_mstr_gorkha_domisile_jco gd  " + 
						"where id !=0 ORDER BY domisile" ;
					stmt=conn.prepareStatement(q);
					
					ResultSet rs = stmt.executeQuery(); 
					int i = 1;
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("domisile"));
						list.add(rs.getString("status"));
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
}
