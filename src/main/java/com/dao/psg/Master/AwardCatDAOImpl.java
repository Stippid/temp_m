package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_MSTR_AWARD_CAT;
import com.persistance.util.HibernateUtil;

public class AwardCatDAOImpl implements AwardCatDAO{
	
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_AwardCat(String awardCat,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !awardCat.equals("")) {
				qry += " and upper(award_cat) like ? ";
			}
			if( !status.equals("0")) {
				qry += " and upper(status) = ? ";
			}
				
			q="select distinct id,upper(award_cat) as award_cat,status from tb_psg_mstr_award_category c \r\n" + 
					"where c.id is not null "+qry+" order by award_cat" ;
				stmt=conn.prepareStatement(q);
				int j =1;
					
				if(!awardCat.equals("")) {
					stmt.setString(j, "%"+awardCat.toUpperCase()+"%");
					j += 1;
				}
				if(!status.equals("0")) {
					stmt.setString(j, status.toUpperCase());
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("award_cat"));
					list.add(rs.getString("status"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Detail?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Detail?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_PSG_MSTR_AWARD_CAT getmtByid(int id) { Session sessionHQL =
			  HibernateUtil.getSessionFactory().openSession(); Transaction tx =
			  sessionHQL.beginTransaction(); TB_PSG_MSTR_AWARD_CAT updateid = (TB_PSG_MSTR_AWARD_CAT)
			  sessionHQL.get(TB_PSG_MSTR_AWARD_CAT.class, id); sessionHQL.getTransaction().commit();
			  sessionHQL.close(); return updateid; }
	
	public ArrayList<ArrayList<String>> AwardCatReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
				
			q="select distinct id,award_cat,status from tb_psg_mstr_award_category c \r\n" + 
					"where c.id is not null order by award_cat" ;
				stmt=conn.prepareStatement(q);
				int i =1;
					
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("award_cat"));
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
