package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.models.mnh.Tb_Med_Daily_Disease_Surv_Details;
import com.models.mnh.Tb_Med_Strength;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class mnh_input_strengthDetailsDAOImpl implements mnh_input_strengthDetailsDAO{
	      private DataSource dataSource;
		  public void setDataSource(DataSource dataSource) {
		  this.dataSource = dataSource;
			}
		public Long checkstrengthExistlNo(String cmd,String qtr,int year,int id) {
					String hql="select count(id) from Tb_Med_Strength where  cmd=:d1 and qtr=:d2 and year=:d3 and id!=:id";
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction tx = session.beginTransaction();
					Query q1= session.createQuery(hql);
					q1.setParameter("d1", cmd);
					q1.setParameter("d2", qtr);
					q1.setParameter("d3", year);
					q1.setParameter("id", id);
					Long count_list1 =  (Long) q1.uniqueResult();	
				 
					tx.commit();
					session.close();
					if(count_list1 != null)
					{
						return count_list1;
					}
					else
					{
						return (long) 0;
					}
					
				}
	
			
        public Tb_Med_Strength updatestrength_detailsByid(int id) {
		  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Strength updateid = (Tb_Med_Strength) sessionHQL.get(Tb_Med_Strength.class, id);
		 
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
			 
	      public String updatestrength(Tb_Med_Strength obj,String username){
             Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
             Transaction tx = sessionHQL.beginTransaction();
            String msg = "";
               try{
                    String hql = "update Tb_Med_Strength set cmd=:cmd,qtr=:qtr,year=:year,off_male=:off_male,off_female=:off_female,cadet=:cadet,lady_cadet=:lady_cadet,mns=:mns,"
                    + "mns_cadet=:mns_cadet,jco=:jco,ort=:ort,dsc_jco=:dsc_jco,dsc_or=:dsc_or,rect=:rect,total=:total,high_offr=:high_offr,"
                    + "high_jco=:high_jco,high_or=:high_or,modified_by=:modified_by,modified_on=:modified_on where id=:id";
    
                 Query query = sessionHQL.createQuery(hql).setString("cmd",obj.getCmd()).setString("qtr", obj.getQtr()).setInteger("year", obj.getYear())
                    .setInteger("off_male", obj.getOff_male()).setInteger("off_female", obj.getOff_female()).setInteger("cadet", obj.getCadet())
                    .setInteger("lady_cadet", obj.getLady_cadet()).setInteger("mns", obj.getMns()).setInteger("mns_cadet", obj.getMns_cadet())
                    .setInteger("jco", obj.getJco()).setInteger("ort", obj.getOrt()).setInteger("dsc_jco", obj.getDsc_jco())
                    .setInteger("dsc_or", obj.getDsc_or()).setInteger("rect", obj.getRect()).setInteger("total", obj.getTotal())
                    .setInteger("high_offr", obj.getHigh_offr()).setInteger("high_jco", obj.getHigh_jco()).setInteger("high_or", obj.getHigh_or())
                    .setString("modified_by",username).setTimestamp("modified_on", new Date()).setInteger("id",obj.getId());
                        
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
	  
	      
	      
	      
	      
		public List<Map<String, Object>> search_strength_details_input(String cmd1, String qtr1, String yr1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";

			if (!cmd1.equals("-1")) {
				qry += " Where cmd = ?";
				flag = "N";
			}
			if (!qtr1.equals("-1")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where qtr = ?";
					flag = "N";
				} else {
					qry += " and qtr = ?";
				}
			}
			if (!yr1.equals("") && !yr1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where year = ?";
					flag = "N";
				} else {
					qry += " and year = ?";
				}
			}
          q = "select distinct a.id,(select sys_code_desc from tb_med_system_code where sys_code_value = qtr)"
     		  + " as qtr,a.year,c.unit_name as hq_name from tb_med_strength a "
			  + "left join nrv_hq c on c.form_code_control = a.cmd" + qry;
				
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!cmd1.equals("-1")) {
				stmt.setString(j, cmd1);
				j += 1;
			}
			if (!qtr1.equals("-1")) {
				stmt.setString(j, qtr1);
				j += 1;
			}
			if (!yr1.equals("") && !yr1.equals(null)) {
				stmt.setInt(j, Integer.parseInt(yr1));
				j += 1;
			}
			
		ResultSet rs = stmt.executeQuery();
	
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		 while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
					+ rs.getObject(1) + ")}else{ return false;}\"";
			String updateButton = "<i class='action_icons action_update' " + editData + " title='Edit Data'></i>";
			String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("
					+ rs.getObject(1) + ")}else{ return false;}\"";
			String deleteButton = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			String f = "";
			f += updateButton;
			f += deleteButton;
			columns.put(metaData.getColumnLabel(1), f);
			list.add(columns);
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
	return list;
}
		
	
		
		public ArrayList<ArrayList<String>> GetAlloffDetails(String ef){
		

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select count(c.personnel_no) as total_off,\r\n" + 
						"COUNT(c.*) filter(where c.gender = '6') AS off_Male,\r\n" + 
						"COUNT(c.*) filter(where c.gender = '7') AS off_Female\r\n" + 
						"from\r\n" + 
						"tb_psg_trans_proposed_comm_letter c "
						+ "inner join tb_miso_orbat_unt_dtl m on m.sus_no =unit_sus_no and m.status_sus_no ='Active'\r\n" + 
						"where m.form_code_control =  ? \r\n" + 
						
						"";
  
				stmt = conn.prepareStatement(q);
				stmt.setString(1, ef);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {

					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("off_Male")); // 0
					alist.add(rs.getString("off_Female")); // 1
					alist.add(rs.getString("total_off")); 

					list.add(alist);

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
			return list;
		}
		
		public ArrayList<ArrayList<String>> GetAlljcoDetails_j(String ef){
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select count(c.army_no) as total_jco from tb_psg_census_jco_or_p c \r\n" + 
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no =c.unit_sus_no and c.category='JCO' and status_sus_no ='Active'\r\n" + 
						"where m.form_code_control = ?  ";
						
						

				stmt = conn.prepareStatement(q);
				stmt.setString(1, ef);
				ResultSet rs = stmt.executeQuery();
			
				while (rs.next()) {

					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("total_jco")); // 0
				

					list.add(alist);

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
			return list;
		}
		
		
		
		public ArrayList<ArrayList<String>> GetAll_or_Details_o(String ef){
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;


				q = "select count(c.army_no) as total_or from tb_psg_census_jco_or_p c \r\n" + 
						"inner join tb_miso_orbat_unt_dtl m on m.sus_no =c.unit_sus_no and c.category='OR' and status_sus_no ='Active' \r\n" + 
						"where m.form_code_control = ?  ";
						

				stmt = conn.prepareStatement(q);
				stmt.setString(1, ef);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {

					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("total_or")); // 0
			

					list.add(alist);

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
			return list;
		}
		
}
