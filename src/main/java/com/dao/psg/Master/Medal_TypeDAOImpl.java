package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_MEDAL_TYPE;
import com.persistance.util.HibernateUtil;

public class Medal_TypeDAOImpl implements Medal_TypeDAO{

private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> search_relation (String medal_type,String medal_name,String medal_abberivation,String status)
	{		
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
			Connection conn = null;
			String q="";
			String qry="";
			try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
					if( !medal_type.equals("0")) {
						qry += " and mt.medal_type = ?";
					}
					if(!medal_name.equals("")) {
						qry += "  and upper(mt.medal_name) like ?";
						
					}

					
					if(!medal_abberivation.equals("")) {
						qry += "  and upper(mt.medal_abberivation) like ?";
					}
					if (!status.equals("0") ) {
						qry += " and upper(mt.status) like ?";
						
					}

						q="select mt.id,upper(k.award_cat) as award_cat,upper(mt.medal_name) as medal_name,upper(mt.medal_abberivation) as medal_abberivation,m.label as status\r\n" + 
								"from tb_psg_mstr_medal mt \r\n" + 
								"inner join tb_psg_mstr_award_category k on k.id = mt.medal_type::int\r\n" + 
								"inner join t_domain_value m on m.codevalue=cast(mt.status as text) and m.domainid='STATUS_MSTR' \r\n" + 
								"where mt.id !=0  "+ qry +" order by mt.id desc	";

					stmt=conn.prepareStatement(q);
					  int j =1;
					  
						if( !medal_type.equals("0")) {
							stmt.setString(j, medal_type);
							j += 1;	
						}
						if( !medal_name.equals("")) {
							stmt.setString(j, medal_name.toUpperCase()+"%");
							j += 1;	
						} 
						if( !medal_abberivation.equals("")) {
							stmt.setString(j, medal_abberivation.toUpperCase()+"%");
							j += 1;	
						} 
						
						if (!status.equals("0") ) {
							stmt.setString(j, status.toUpperCase());
							j++;
						}
				
			      ResultSet rs = stmt.executeQuery();      
			      
			      while (rs.next()) {
			    	  ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("award_cat"));
						list.add(rs.getString("medal_name"));
						list.add(rs.getString("medal_abberivation"));
						
						String f = "";
						String f1 = "";
						String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Medal Details ?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("award_cat") + "','" + rs.getString("medal_name") + "','" + rs.getString("medal_abberivation") + "')}else{ return false;}\"";
						 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
						String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Medal Details ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
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
	
	public TB_MEDAL_TYPE getRelationByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			TB_MEDAL_TYPE updateid = (TB_MEDAL_TYPE) sessionHQL.get(TB_MEDAL_TYPE.class, id);
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
	
	public ArrayList<ArrayList<String>> search_MedalTypeReport()
	{		
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
			Connection conn = null;
			String q="";
			try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;

						q="select mt.id,t.label as medal_type,mt.medal_name,mt.medal_abberivation\r\n" + 
								"																from tb_psg_mstr_medal mt \r\n" + 
								"																left join t_domain_value t on mt.medal_type =t.codevalue and t.domainid='MEDAL_CATEGORY' \r\n" + 
								"																where mt.id !=0  order by mt.id desc	";

					stmt=conn.prepareStatement(q);
					
				
			      ResultSet rs = stmt.executeQuery();      
			      int i = 1;
			      while (rs.next()) {
			    	  ArrayList<String> list = new ArrayList<String>();
			    	  String id = String.valueOf(i++);
						list.add(id);
						list.add(rs.getString("medal_type"));
						list.add(rs.getString("medal_name"));
						list.add(rs.getString("medal_abberivation"));
						
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
