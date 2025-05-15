package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_BANK;
import com.persistance.util.HibernateUtil;

public class BankDAOImpl implements BankDAO{


	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<ArrayList<String>> search_bnk_dtl(String bank_name,String bank_abb,String ifsc,String bank_address,String bank_phone)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if( !bank_name.equals("")) {
				qry += " and upper(bank_name) = ? ";
			}
			if( !bank_abb.equals("")) {
				qry += " and upper(bank_abb) = ? ";
			}
			if( !ifsc.equals("")) {
				qry += " and upper(ifsc) = ? ";
			}
			if( !bank_address.equals("")) {
				qry += " and upper(bank_address) = ? ";
			}
			if( !bank_phone.equals("")) {
				qry += " and cast(bank_phone as character varying) = ? ";
			}
				
			q="select distinct \r\n" + 
					"id,\r\n" +
					"bank_name,\r\n" + 
					"bank_abb,\r\n" + 
					"ifsc, \r\n" + 
					"bank_address,\r\n" + 
					"bank_phone\r\n" + 
					"FROM tb_psg_mstr_bank c\r\n" + 
					"where c.id is not null "+qry ;
				stmt=conn.prepareStatement(q);
				int j =1;
				 				
					
				if(!bank_name.equals("")) {
					stmt.setString(j, bank_name.toUpperCase());
					j += 1;
				}
					
				if(!bank_abb.equals("")) {
					stmt.setString(j, bank_abb.toUpperCase());
					j += 1;
				}
				if(!ifsc.equals("")) {
					stmt.setString(j, ifsc.toUpperCase());
					j += 1;
				}
				if(!bank_address.equals("")) {
					stmt.setString(j, bank_address.toUpperCase());
					j += 1;
				}
				if(!bank_phone.equals("")) {
					stmt.setString(j, bank_phone);
					j += 1;
				}
				
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("bank_name"));
					list.add(rs.getString("bank_abb"));
					list.add(rs.getString("ifsc"));
					list.add(rs.getString("bank_address"));
					list.add(rs.getString("bank_phone"));
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Bank Details?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Bank Details?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	public TB_BANK getbankByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_BANK updateid = (TB_BANK) sessionHQL.get(TB_BANK.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	
	/* public String updatebank_dtl(TB_BANK obj,String username,int id){
		 
	        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
	        String msg = "";
	        
	        try{
	    	  String hql = "update TB_BANK set bank_name=:bank_name,bank_abb=:bank_abb,ifsc=:ifsc,bank_address=:bank_address,bank_phone=:bank_phone,modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id";
	                                   
	    	  Query query = sessionHQL.createQuery(hql)
	    			  	.setString("bank_name",obj.getBank_name())
	    			  	.setString("bank_abb", obj.getBank_abb())
						.setString("ifsc", obj.getIfsc())
						.setString("bank_address",obj.getBank_address())
						.setInteger("bank_phone",obj.getBank_phone())
						.setString("modified_by", username).setDate("modified_date", new Date())
						.setInteger("id",id);
	                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	                    tx.commit(); 
	                  
	         }catch (Exception e) {
	               msg = "Data Not Updated.";
	                tx.rollback();
	        }
	        finally {
	              sessionHQL.close();
	        }
	        return msg;
	}*/
}
