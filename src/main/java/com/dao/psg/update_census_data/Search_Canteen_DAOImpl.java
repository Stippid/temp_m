package com.dao.psg.update_census_data;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_BANK;
import com.models.psg.Transaction.TB_PSG_CANTEEN_CARD_DETAIL1;
import com.persistance.util.HibernateUtil;

public class Search_Canteen_DAOImpl implements Search_Canteen_DAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> Search_canteen(String card_no,String status,String service)
	{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	  
	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			//qry = "id !=  0";			
			if(!card_no.equals("")) {
				qry += "  and upper(cn.card_no) like ? ";
			}			
			
			if (!service.equals("")) {
				qry += "  and cn.service= cast(? as integer) ";
			}
			if (status.equals("0")) {
				qry += " and cast(cn.status as character varying) = ? ";
			}
			if (status.equals("1")) {
				qry += " and cast(cn.status as character varying) = ? ";
			}
			if (status.equals("3")) {
				qry += " and cast(cn.status as character varying) = ? ";
			}
			 		
				q="select distinct cn.id,cn.card_no,cn.name,\n" + 
						"To_char(cn.date_of_birth,'DD-MM-YYYY') as date_of_birth,cn.type_of_card, td.label as service, r.relation_name as relation\n" + 
						"		from tb_psg_canteen_card_details cn\n" + 
						"		left join t_domain_value td on domainid='EX_SERVICEMAN' and cast (td.codevalue as integer)= cn.service\n" + 
						"		left join tb_psg_mstr_relation r on r.id = cn.relation\n" + 
						"		where cn.id != 0 and r.status ='active'" + qry+ "  order by cn.id desc" ;
				
			
			stmt=conn.prepareStatement(q);
			//stmt.setString(1,roleSuNo);
			
			if(!qry.equals(""))     
			{  int j =1;
			
				/*
				 * if (roleSusNo != "") { stmt.setString(j, roleSusNo); j += 1; }
				 */						
				if(!card_no.equals("")) {
					stmt.setString(j, card_no.toUpperCase()+"%");
					j += 1;	
				} 

				if (!service.equals("")) {
					stmt.setString(j, service);
					j += 1;
				}
				if (status.equals("0")) {
					stmt.setString(j, status);
					j += 1;
				}
				if (status.equals("1")) {
					stmt.setString(j, status);
					j += 1;
				}
				if (status.equals("3")) {
					stmt.setString(j, status);
					j += 1;
				}
			}
			
		
	      ResultSet rs = stmt.executeQuery();   
	
	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  ArrayList<String> list = new ArrayList<String>();
	    	  	list.add(rs.getString("card_no"));
				list.add(rs.getString("name"));
				list.add(rs.getString("date_of_birth"));								
				list.add(rs.getString("type_of_card"));
				list.add(rs.getString("relation"));
				list.add(rs.getString("service"));
				
				String f = "";
				String f1 = "";				
				String f2 = "";
				String f3 = "";
				
				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Letter?') ){deleteData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
				String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this  Letter ?') ){Approved("+rs.getString("id")+ ")}else{ return false;}\"";
				f2 = "<i class='action_icons action_approve' "+Approved+" title='Approve Data' ></i>";
				
				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){Reject("
						+ rs.getString("id") + ")}else{ return false;}\"";
				f3 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";
			    list.add(f);
				list.add(f1);
				list.add(f2);
				list.add(f3);
				
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
	 public TB_PSG_CANTEEN_CARD_DETAIL1 getcanteen_detailsByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_CANTEEN_CARD_DETAIL1 updateid = (TB_PSG_CANTEEN_CARD_DETAIL1) sessionHQL.get(TB_PSG_CANTEEN_CARD_DETAIL1.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
}
