package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_COPECODE;
import com.persistance.util.HibernateUtil;

public class CopeCodeDAOImpl implements CopeCodeDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_Copecode(String copdecode,String status,String value,String description)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !copdecode.equals("0")) {
				qry += " and upper(cope_code) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
			if( !value.equals("")) {
				qry += " and upper(value) like ? ";
			}
			if( !description.equals("")) {
				qry += " and upper(description) like ? ";
			}
				
			q="select distinct id,upper(cope_code) as cope_code,upper(value) as value,upper(description) as description from tb_psg_mstr_copecode c \r\n" + 
					"where c.id is not null "+qry+" order by id" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				if(!copdecode.equals("0")) {
					stmt.setString(j, "%"+copdecode.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				if(!value.equals("")) {
					stmt.setString(j, "%"+value.toUpperCase()+"%");
					j += 1;
				}
				if(!description.equals("")) {
					stmt.setString(j, "%"+description.toUpperCase()+"%");
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("cope_code"));
					list.add(rs.getString("value"));
					list.add(rs.getString("description"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Cope Code Detail?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Cope Code Detail?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	@SuppressWarnings("unused")
	public TB_PSG_MSTR_COPECODE getmtByid(int id) { Session sessionHQL =
			  HibernateUtil.getSessionFactory().openSession(); Transaction tx =
			  sessionHQL.beginTransaction(); TB_PSG_MSTR_COPECODE updateid = (TB_PSG_MSTR_COPECODE)
			  sessionHQL.get(TB_PSG_MSTR_COPECODE.class, id); sessionHQL.getTransaction().commit();
			  sessionHQL.close(); return updateid; }
	
	
	public ArrayList<ArrayList<String>> CopecodeReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				
			q="select distinct id,cope_code,value,description,status from tb_psg_mstr_copecode c \r\n" + 
					"where c.id is not null  order by id" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(j++);
					list.add(id);
					list.add(rs.getString("cope_code"));
					list.add(rs.getString("value"));
					list.add(rs.getString("description"));
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
