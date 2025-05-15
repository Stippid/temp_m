package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_EXAMINATION_PASSED;
import com.persistance.util.HibernateUtil;

public class Examination_PassedDAOImpl implements Examination_PassedDAO  {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@Override
	public List<ArrayList<String>> search_ExaminationPassed(String qualification_type, String examination_passed,
			String status) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( !examination_passed.equals("")) {
				qry += " where upper(c.examination_passed) like ? ";
			}
			
			if(!qualification_type.equals("0")) {
				qry += " and cast(qualification_type as character varying) like ? ";				
			}
			
			if (!status.equals("0") ) {
				qry += " and c.status = ?";
				
			}
			 q="select c.id,upper(c.examination_passed) as examination_passed,c.status,upper(m.label) as label\r\n" + 
			 		"from tb_psg_mstr_examination_passed c\r\n" + 
			 		"inner join t_domain_value m on m.codevalue=cast(c.qualification_type as text) \r\n" + 
			 		" and m.domainid='QUALIFICATION_TYPE'\r\n" + 
			 		""+qry;
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				
				if(!examination_passed.equals("")) {
					stmt.setString(j, examination_passed.toUpperCase()+"%");
					j += 1;
				}
			
				if(!qualification_type.equals("0")) {				
					stmt.setString(j, qualification_type.toUpperCase()+"%");
					j += 1;	
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("examination_passed"));
					list.add(rs.getString("label"));
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Examination Passed?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Examination Passed?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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

	public TB_EXAMINATION_PASSED getEXAMINATIONPASSEDByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
	 	TB_EXAMINATION_PASSED updateid = (TB_EXAMINATION_PASSED) sessionHQL.get(TB_EXAMINATION_PASSED.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}

	public ArrayList<ArrayList<String>> search_ExaminationPassedReport() {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			 q="select c.id,c.examination_passed,m.label\r\n" + 
			 		"from tb_psg_mstr_examination_passed c\r\n" + 
			 		"inner join t_domain_value m on m.codevalue=cast(c.qualification_type as text) \r\n" + 
			 		" and m.domainid='QUALIFICATION_TYPE'\r\n" + 
			 		"";
			
				stmt=conn.prepareStatement(q);
				int i = 1;
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("examination_passed"));
					list.add(rs.getString("label"));
					
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
