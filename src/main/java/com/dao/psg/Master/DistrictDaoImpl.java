
package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_DISTRICT;
import com.persistance.util.HibernateUtil;

public class DistrictDaoImpl implements DistrictDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_District_name(int country_id,int state_id,String district_name,String status)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			if( country_id!=0) {
				qry += " and d.country_id = ? ";
			}
			
			if( state_id!=0) {
				qry += " and d.state_id = ? ";
			}
			if( !district_name.equals("")) {
				qry += " and upper(d.district_name) like upper(?) ";
			}
			
			
			if (!status.equals("0") ) {
				qry += " and upper(d.status) like ?";
				//flag = "N";
			}
				
			q="select d.district_id, upper(d.district_name) as district_name,b.name,e.state_name,m.label as status \r\n"
					+ "from tb_psg_mstr_district d \r\n"
					+ "	left join tb_psg_mstr_country b on b.id = d.country_id  \r\n"
					+ "	left join tb_psg_mstr_state e on e.state_id = d.state_id\r\n"
					+ "	left join t_domain_value m on m.codevalue=cast(d.status as text) and m.domainid='STATUS_MSTR'\r\n"
					+ "	where district_id !=0"+qry ;
				
				stmt=conn.prepareStatement(q);
				int j =1;
				
				if(country_id !=0) {
					stmt.setInt(j, country_id);
					j += 1;
				}
				
				
				if( state_id!=0) {
					stmt.setInt(j, state_id);
					j += 1;
				}
				if(!district_name.equals("")) {
					stmt.setString(j, district_name.toUpperCase() + '%');
					j += 1;
				}
				

				if (!status.equals("0") ) {
					stmt.setString(j, status.toUpperCase());
					j+= 1;
				}
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));
					list.add(rs.getString("state_name"));
					list.add(rs.getString("district_name"));
					
					
					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This District?') ){editData("+ rs.getString("district_id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This District?') ){deleteData(" + rs.getString("district_id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
		
					/*if(status.equals("inactive"))
					{
						
						list.add("");
						list.add("");

					}
					else {*/
					list.add(f);
					list.add(f1);
					//}
					
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

	public TB_DISTRICT getDistrictByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		TB_DISTRICT updateid = (TB_DISTRICT) sessionHQL.get(TB_DISTRICT.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}
	public ArrayList<ArrayList<String>> search_DistrictReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
				
			q="select d.district_name,b.name,e.state_name  \r\n"
					+ "from tb_psg_mstr_district d \r\n"
					+ "	left join tb_psg_mstr_country b on b.id = d.country_id  \r\n"
					+ "	left join tb_psg_mstr_state e on e.state_id = d.state_id\r\n"
					+ "	where district_id !=0" ;
				
				stmt=conn.prepareStatement(q);
				ResultSet rs = stmt.executeQuery(); 
				int i = 1;
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("name"));
					list.add(rs.getString("state_name"));
					list.add(rs.getString("district_name"));
					
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

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		