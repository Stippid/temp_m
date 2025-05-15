package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_COMPANY;

import com.persistance.util.HibernateUtil;

public class CompanyDAOImpl implements CompanyDAO{

	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	public ArrayList<ArrayList<String>> search_Company (String company_name, int bat_id, String status) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!company_name.equals("")) {
				qry += " and upper (c.company_name) like  ?";
			}
			
			if (!status.equals("0") ) {
				qry += " and upper(c.status) like ?";
			}
			
			if( bat_id!=0) {
				qry += " and c.bat_id = ? ";
			}

			if (company_name.equals("") && bat_id==0 && status.equals("0")  ) {
			 
				q = " SELECT c.id,c.company_name,b.battalion_name,m.label as status\n" + 
						"					FROM  tb_psg_mstr_company c \n" + 
						"					inner join tb_psg_mstr_battalion b on b.id = c.bat_id and b.status='active'\n" + 
						"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR' where c.id!=0";
			}
		
			else
			{
			q = " SELECT c.id,c.company_name,b.battalion_name,m.label as status\n" + 
					"					FROM  tb_psg_mstr_company c \n" + 
					"					inner join tb_psg_mstr_battalion b on b.id = c.bat_id and b.status='active'\n" + 
					"					left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR' where c.id!=0"  + qry;
			}
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!company_name.equals("")) {
				stmt.setString(j, company_name.toUpperCase()+"%");
				j++;
			}
			if (!status.equals("0") ) {
				stmt.setString(j, status.toUpperCase()+"%");
				j++;
			}
			if( bat_id!=0) {
				stmt.setInt(j, bat_id);
				j += 1;
			}
 
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				 ArrayList<String> list = new ArrayList<String>();
				 list.add(rs.getString("battalion_name"));
					list.add(rs.getString("company_name"));
					
					
				String editData = "onclick=\"  if (confirm('Are you sure you want to update This Company?') ){editData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete This Company?') ){deleteData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
				
				String f = "";
				f += updateButton;
				f += deleteButton;
				 if(status.equals("inactive"))
					{
						
						list.add("");
					//	list.add("");

					}
					else {
					list.add(f);
					//list.add(f1);
					}
				//list.add(f);
				alist.add(list);
				
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
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
public TB_COMPANY getCompanyByid(int id) {
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	TB_COMPANY updateid = (TB_COMPANY) sessionHQL.get(TB_COMPANY.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	
public Long checkExitstingCompany(String company_name1,int id1, String status1) {
	String hql="select count(id) from TB_COMPANY where company_name1=:co1 ,status1=:s1 and  id!=:id";
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q2= session.createQuery(hql);
	q2.setParameter("co1", company_name1);
	q2.setParameter("s1", status1);
	
	q2.setParameter("id", id1);
	Long count_list2 =  (Long) q2.uniqueResult();	
	tx.commit();
	session.close();
	if(count_list2 != null)
	{
		return count_list2;
	}
	else
	{
		return (long) 0;
	}
	
}
	
	
public String Update_Company(TB_COMPANY obj,String username){
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = sessionHQL.beginTransaction();
  
  String msg = "";
  try{
  	String hql = "update TB_COMPANY set company_name=:company_name,bat_id=:bat_id,modify_by=:m1,modify_date=:m2 where id=:id";

		Query query = sessionHQL.createQuery(hql).setString("company_name", obj.getCompany_name())
				.setInteger("bat_id",  obj.getBat_id())
				.setString("m1", username)
				.setTimestamp("m2", new Date()).setInteger("id", obj.getId());
		
          msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
          
          tx.commit();
  }
  catch (Exception e) {
          msg = "Data Not Updated.";
          tx.rollback();
  }
  finally {
          sessionHQL.close();
  }
  return msg;

}
	
public ArrayList<ArrayList<String>> search_CompanyReport() {

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		 
			q = " SELECT c.id,c.company_name,b.battalion_name\n" + 
					"					FROM  tb_psg_mstr_company c \n" + 
					"					inner join tb_psg_mstr_battalion b on b.id = c.bat_id and b.status='active'\n" + 
					"					where c.id!=0";
	
		stmt = conn.prepareStatement(q);
		ResultSet rs = stmt.executeQuery();
		int i = 1;
		while (rs.next()) {
			 ArrayList<String> list = new ArrayList<String>();
			 String id = String.valueOf(i++);
			 list.add(id);
			 list.add(rs.getString("battalion_name"));
			 list.add(rs.getString("company_name"));
				
			alist.add(list);
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
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
