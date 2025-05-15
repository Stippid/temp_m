package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.psg.Master.TB_CITY;
import com.persistance.util.HibernateUtil;

public class City_DAOImpl implements City_DAO {
		
		private DataSource dataSource;

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		public ArrayList<ArrayList<String>> search_city_name(int country_id,int state_id,int district_id,String city_name, String status)
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if(country_id != 0) {
					qry += "  and c.country_id  = ? ";
				}	
				
				if (state_id != 0) {
					qry += "and c.state_id = ?";

				}
				
				if (district_id != 0) {
					qry += "and c.district_id = ?";

				}
				
				if( !city_name.equals("")) {
					qry += "  and upper(c.city_name) like  ?";
				}
				if (!status.equals("0") ) {
					qry += " and upper(c.status) like ?";
					//flag = "N";
				}

				
				q="select distinct c.city_id,upper(c.city_name) as city_name,b.name,e.state_name,upper(f.district_name) as district_name,m.label as status from tb_psg_mstr_city c\n"
						+ "													left join tb_psg_mstr_country b on b.id = c.country_id\n"
						+ "													left join tb_psg_mstr_state e on e.state_id = c.state_id\n"
						+ "												left join tb_psg_mstr_district f on f.district_id = c.district_id\n"
						+ "						left join t_domain_value m on m.codevalue=cast(c.status as text) and m.domainid='STATUS_MSTR' where c.city_id!=0 "+ qry + "order by c.city_id desc";
				
					stmt=conn.prepareStatement(q);

				stmt = conn.prepareStatement(q);
				int j =1;
				if(country_id != 0) {
					stmt.setInt(j, country_id);
					j += 1;	
				}
				if(state_id != 0) {
					stmt.setInt(j, state_id);
					j += 1;	
				}
				if(district_id != 0) {
					stmt.setInt(j, district_id);
					j += 1;	
				}
				if( !city_name.equals("")) {
					stmt.setString(j, city_name.toUpperCase() + '%');
					j += 1;
				}
				if (!status.equals("0") ) {
					stmt.setString(j, status.toUpperCase());
					j++;
				}

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("name"));
					list.add(rs.getString("state_name"));
					list.add(rs.getString("district_name"));
					list.add(rs.getString("city_name"));
					

					String f = "";
					String f1 = "";
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Tehsil?') ){editData("+ rs.getString("city_id") + ")}else{ return false;}\"";
					f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Tehsil?') ){deleteData(" + rs.getString("city_id") + ")}else{ return false;}\"";
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
		
		
		public TB_CITY getTB_CITYByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	TB_CITY updateid = (TB_CITY) sessionHQL.get(TB_CITY.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
		public ArrayList<ArrayList<String>> search_cityReport()
		{
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				q="select distinct c.city_id,c.city_name,b.name,e.state_name,f.district_name from tb_psg_mstr_city c\n"
						+ "													left join tb_psg_mstr_country b on b.id = c.country_id\n"
						+ "													left join tb_psg_mstr_state e on e.state_id = c.state_id\n"
						+ "												left join tb_psg_mstr_district f on f.district_id = c.district_id\n"
						+ "						where c.city_id!=0 order by c.city_id desc";
				
					stmt=conn.prepareStatement(q);

				stmt = conn.prepareStatement(q);

				ResultSet rs = stmt.executeQuery();
				int i = 1;
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					String id = String.valueOf(i++);
					list.add(id);
					list.add(rs.getString("name"));
					list.add(rs.getString("state_name"));
					list.add(rs.getString("district_name"));
					list.add(rs.getString("city_name"));
					
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