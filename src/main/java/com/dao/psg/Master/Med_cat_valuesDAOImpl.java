package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Master.TB_MED_CAT_VALUES;
import com.persistance.util.HibernateUtil;

public class Med_cat_valuesDAOImpl implements Med_cat_valuesDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> search_med_cat_values (String shape_status, String values,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				if( !shape_status.equals("0")) {
					qry += "and mm.shape_status = ?";
				}
				if(!values.equals("")) {
					qry += " and upper(values) like ?";
				}
				if (!status.equals("0") ) {
					qry += " and upper(mm.status) like ?";
				}
				
			q="	select mm.id, \n" + 
					"					t.label as shape_status,\n" + 
					"					m.label as status,\n" + 
					"					upper(values) as values from tb_mstr_med_cat_values mm \n" + 
					"					left join t_domain_value t on t.codevalue =cast(mm.shape_status as text) and t.domainid='SHAPE_STATUS' \n" + 
					"					left join t_domain_value m on m.codevalue=cast(mm.status as text) and m.domainid='STATUS_MSTR'\n" + 
					"					where mm.id!=0 "+ qry +" order by mm.id desc";
				
				stmt=conn.prepareStatement(q);
				
				int j = 1;
			
				
				if (!shape_status.equals("0")) {
					stmt.setString(j, shape_status);
					j += 1;
				}
					
					if(!values.equals("")) {
						stmt.setString(j, values.toUpperCase() + "%");
						j += 1;
					}
					
					if (!status.equals("0") ) {
						stmt.setString(j, status.toUpperCase());
						j++;
					}
					
		      ResultSet rs = stmt.executeQuery();      
		      
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("shape_status"));	
					list.add(rs.getString("values"));
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Mad Cat Details?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("shape_status") + "','" + rs.getString("values") + "')}else{ return false;}\"";
					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Mad Cat Details?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
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
		
	public TB_MED_CAT_VALUES get_med_cat_valuesByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_MED_CAT_VALUES updateid = (TB_MED_CAT_VALUES) sessionHQL.get(TB_MED_CAT_VALUES.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
	public ArrayList<ArrayList<String>> search_med_cat_valuesReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	
		Connection conn = null;
		String q="";
		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
			q="	select mm.id, \n" + 
					"					t.label as shape_status,\n" + 
					"					values from tb_mstr_med_cat_values mm \n" + 
					"					left join t_domain_value t on t.codevalue =cast(mm.shape_status as text) and t.domainid='SHAPE_STATUS' \n" + 
					"					where mm.id!=0 order by mm.id desc";
				
				stmt=conn.prepareStatement(q);
				
		      ResultSet rs = stmt.executeQuery();      
		      int i = 1;
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	String id = String.valueOf(i++);
		    	  	list.add(id);
					list.add(rs.getString("shape_status"));	
					list.add(rs.getString("values"));
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
