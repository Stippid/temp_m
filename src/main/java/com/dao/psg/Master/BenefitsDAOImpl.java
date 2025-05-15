package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_PSG_BENEFITS_MASTER;
import com.models.psg.Master.TB_STATE;
import com.persistance.util.HibernateUtil;

public class BenefitsDAOImpl implements BenefitsDAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public ArrayList<ArrayList<String>> search_Benefits_name(int agency_id,String benefits_name,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if(agency_id != 0) {
				qry += "  and s.agency_id = ? ";
			}	
			if( !benefits_name.equals("")) {
				qry += " and upper(s.benefits_name) = Upper(?) ";
			}
			if (!status.equals("0") ) {
				qry += " and s.status = ?";
				//flag = "N";
			}
			
			q="select distinct s.id,s.benefits_name,s.agency_id,c.agency_name,m.label as status " + 
			        "from tb_psg_benefits_mstr s \r\n" + 
					" left join tb_psg_agency_mstr c on s.agency_id = c.id  \r\n" + 
					" left join t_domain_value m on m.codevalue=cast(s.status as text) and m.domainid='STATUS_MSTR'\r\n" + 
					" where s.id!=0 "+ qry +" order by s.id asc";
			
			
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(agency_id != 0) {
					stmt.setInt(j, agency_id);
					j += 1;	
				}
				if(!benefits_name.equals("")) {
					stmt.setString(j, benefits_name.toUpperCase());
					j += 1;
				}
				
				if (!status.equals("0") ) {
					stmt.setString(j, status);
					j++;
				}
				
				ResultSet rs = stmt.executeQuery();      
				
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("agency_name"));
					list.add(rs.getString("benefits_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This State?') ){editData("+ rs.getString("id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This State?') ){deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
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
	
	
	 public TB_PSG_BENEFITS_MASTER getBenefitsByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_PSG_BENEFITS_MASTER updateid = (TB_PSG_BENEFITS_MASTER) sessionHQL.get(TB_PSG_BENEFITS_MASTER.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	

}
