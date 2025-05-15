package com.dao.psg.Master;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.sql.SQLException;

import java.util.ArrayList;



import javax.sql.DataSource;



import org.hibernate.Session;

import org.hibernate.Transaction;



import com.models.psg.Master.TB_QUALIFICATION;

import com.persistance.util.HibernateUtil;



public class QualificationDaoImpl implements QualificationDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;

    }

	public ArrayList<ArrayList<String>> search_qualification (String qualification_type,String examination_passed,String degree, String status)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

	

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

				conn = dataSource.getConnection();			 

				PreparedStatement stmt=null;

				

				if( !qualification_type.equals("0")) {

					qry += " and q.qualification_type= ?";

				}

				

			

				

				if( !examination_passed.equals("0")) {

					qry += " and q.examination_passed= ?";

				}

				

				if (!degree.equals("0") ) {

					qry += " and q.degree= ?";

					//flag = "N";

				}

				if (!status.equals("0") ) {

					qry += " and upper(q.status) like ?";

					//flag = "N";

				}

				

			

					q="select q.id, upper(td.label) as label,upper(ep.examination_passed) as examination_passed,upper(d.degree) as degree,q.status from tb_qualification q\r\n" + 

							"inner join tb_psg_mstr_degree d on d.id=q.degree\r\n" + 

							"inner join tb_psg_mstr_examination_passed ep on ep.id=q.examination_passed\r\n" + 

							"inner join t_domain_value td on td.codevalue=q.qualification_type and td.domainid='QUALIFICATION_TYPE'\r\n" + 

							"where q.id!=0 " + qry ;

				

				

				

				

				stmt=conn.prepareStatement(q);

				

				if(!qry.equals(""))     

				{  int j =1;

					if( !qualification_type.equals("0")) {

						stmt.setString(j, qualification_type);

						j += 1;	

					}

					if( !examination_passed.equals("0")) {

						stmt.setInt(j, Integer.parseInt(examination_passed));

						j += 1;	

					}

					if( !degree.equals("0")) {

						stmt.setInt(j, Integer.parseInt(degree));

						j += 1;	

					}

					if (!status.equals("0") ) {

						stmt.setString(j, status.toUpperCase()+"%");

						j++;

					}

				}

		      ResultSet rs = stmt.executeQuery();    



		      while (rs.next()) {

		    	  ArrayList<String> list = new ArrayList<String>();

					list.add(rs.getString("label"));	

					list.add(rs.getString("examination_passed"));	

					list.add(rs.getString("degree"));	

					list.add(rs.getString("status"));	

				

					

					String f = "";

					String f1 = "";

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Qualification ?') ){editData("+ rs.getInt("id") + ")}else{ return false;}\"";

					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Qualification ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";

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

	public TB_QUALIFICATION getqualificationByid(int id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			TB_QUALIFICATION updateid = (TB_QUALIFICATION) sessionHQL.get(TB_QUALIFICATION.class, id);

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

	public ArrayList<ArrayList<String>> search_qualificationReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  

				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
					q="select q.id, td.label,ep.examination_passed,d.degree from tb_qualification q\r\n" + 
							"inner join tb_psg_mstr_degree d on d.id=q.degree\r\n" + 
							"inner join tb_psg_mstr_examination_passed ep on ep.id=q.examination_passed\r\n" + 
							"inner join t_domain_value td on td.codevalue=q.qualification_type and td.domainid='QUALIFICATION_TYPE'\r\n" + 
							"where q.id!=0 " ;

				stmt=conn.prepareStatement(q);
		      ResultSet rs = stmt.executeQuery();    
		      
		      int i = 1;
		      while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  	String id = String.valueOf(i++);
		    	  	list.add(id);
					list.add(rs.getString("label"));	
					list.add(rs.getString("examination_passed"));	
					list.add(rs.getString("degree"));	

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

