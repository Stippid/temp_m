package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_CIVILIAN_DESIGNATION;
import com.persistance.util.HibernateUtil;

public class Civilian_DesignationDAOImpl implements Civilian_DesignationDAO{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public ArrayList<ArrayList<String>> search_Civilian_designation(String designation,String status,String civilian_trade,String classification_services,String c_group)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			
			if( !designation.equals("")) {
				qry += " and upper(c.designation) like ? ";
			}
			
			if( !classification_services.equals("")) {
				qry += " and c.classification_services = ? ";
			}
			if( !c_group.equals("")) {
				qry += " and c.c_group = ? ";
			}
			if (!civilian_trade.equals("0") ) {
				qry += " and t.civilian_trade = ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
			}
			
			q="select  c.id,\n" + 
					"		c.classification_services,\n" + 
					"		c.designation,\n" + 
					"		c.c_group,\n" + 
					"		c.status,\n" + 
					"		t.civilian_trade\n" + 
					"from tb_psg_mstr_civilian_designation c\n" + 
					"inner join tb_psg_mstr_civilian_trade t on c.civilian_trade = t.id\n" + 
					"where c.id !=0 "+qry ;
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(!designation.equals("")) {
					stmt.setString(j, designation.toUpperCase()+"%");
					j += 1;
				}
				if(!classification_services.equals("")) {
					stmt.setString(j, classification_services);
					j += 1;
				}
				if(!c_group.equals("")) {
					stmt.setString(j, c_group);
					j += 1;
				}
				if (!civilian_trade.equals("0") ) {
					stmt.setInt(j,Integer.parseInt(civilian_trade));
					j++;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
			
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("designation"));
					list.add(rs.getString("classification_services"));
					list.add(rs.getString("c_group"));
					list.add(rs.getString("civilian_trade"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Civilian Designation?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Civilian Designation?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	public TB_PSG_MSTR_CIVILIAN_DESIGNATION getCivilianDesByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_PSG_MSTR_CIVILIAN_DESIGNATION updateid = (TB_PSG_MSTR_CIVILIAN_DESIGNATION) sessionHQL.get(TB_PSG_MSTR_CIVILIAN_DESIGNATION.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	
	public ArrayList<ArrayList<String>> Civilian_DesignationReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				
			q="select  c.id,\n" + 
					"		c.classification_services,\n" + 
					"		c.designation,\n" + 
					"		c.c_group,\n" + 
					"		c.status,\n" + 
					"		t.civilian_trade\n" + 
					"from tb_psg_mstr_civilian_designation c\n" + 
					"inner join tb_psg_mstr_civilian_trade t on c.civilian_trade = t.id\n" + 
					"where c.id is not null order by id" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(j++);
					list.add(id);
					list.add(rs.getString("classification_services"));
					list.add(rs.getString("designation"));
					list.add(rs.getString("c_group"));
					list.add(rs.getString("status"));
					list.add(rs.getString("civilian_trade"));
					
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
