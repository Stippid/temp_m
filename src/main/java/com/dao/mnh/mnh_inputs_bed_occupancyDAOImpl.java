package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.mnh.Tb_Med_Bed_Ex_Servicemen;
import com.models.mnh.Tb_Med_Bed_Serving;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class mnh_inputs_bed_occupancyDAOImpl implements mnh_inputs_bed_occupancyDAO{

	private DataSource dataSource;
	private Object type;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Map<String, Object>> search_bed_occupancy_Input(String sus1, String type1, String qtr1, String yr1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";
			String tbl_name = null;

			if (type1.equals("1")) {
				tbl_name = "tb_med_bed_serving";
			}
			if (type1.equals("2")) {
				tbl_name = "tb_med_bed_ex_servicemen";
			}

			if (!sus1.equals("")) {
				qry += " Where r.sus_no = ?";
				flag = "N";
			}

			if (!qtr1.equals("-1")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.qtr = ?";
					flag = "N";
				} else {
					qry += " and r.qtr = ?";
				}
			}

			if (!yr1.equals("")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.year = ?";
					flag = "N";
				} else {
					qry += " and r.year = ?";
				}
			}

			/*q = "select r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active'),"
					+ "r.ofcr_tot,r.jco_tot,r.ofcr_fmly_tot,r.jco_fmly_tot from " + tbl_name + " r" + qry;*/
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
					+ "r.ofcr_tot,r.jco_tot,r.ofcr_fmly_tot,r.jco_fmly_tot from " + tbl_name + " r" + qry;
			
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}

			if (!qtr1.equals("-1")) {
				stmt.setString(j, qtr1);
				j++;
			}

			if (!yr1.equals("")) {
				stmt.setInt(j, Integer.parseInt(yr1));
				j++;
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
	
	 
	 public Tb_Med_Bed_Ex_Servicemen getexservDetail(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	Tb_Med_Bed_Ex_Servicemen updateid = (Tb_Med_Bed_Ex_Servicemen) sessionHQL.get(Tb_Med_Bed_Ex_Servicemen.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	public Tb_Med_Bed_Serving getservDetail(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	Tb_Med_Bed_Serving updateid = (Tb_Med_Bed_Serving) sessionHQL.get(Tb_Med_Bed_Serving.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	
	public String updatebedoccupancyserv(Tb_Med_Bed_Serving obj,String username){		
        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
        
        String msg = "";		
     try{
		String hql = "update Tb_Med_Bed_Serving set qtr=:qtr,year=:year,ofcr_max=:a1,ofcr_tot=:a2,ofcr_avg=:a3,jco_max=:a4,jco_tot=:a5,jco_avg=:a6,"
				+ "ofcr_fmly_max=:a7,ofcr_fmly_tot=:a8,ofcr_fmly_avg=:a9,jco_fmly_max=:a10,jco_fmly_tot=:a11,jco_fmly_avg=:a12,remarks=:a13,"
				+ "modified_by=:m1,modified_on=:m2 where id=:id";

		Query query = sessionHQL.createQuery(hql).setString("qtr", obj.getQtr())
				.setInteger("year", obj.getYear()).setInteger("a1", obj.getOfcr_max()).setInteger("a2", obj.getOfcr_tot())
				.setDouble("a3", obj.getOfcr_avg()).setInteger("a4", obj.getJco_max()).setInteger("a5", obj.getJco_tot())
				.setDouble("a6", obj.getJco_avg()).setInteger("a7", obj.getOfcr_fmly_max()).setInteger("a8", obj.getOfcr_fmly_tot())
				.setDouble("a9", obj.getOfcr_fmly_avg()).setInteger("a10", obj.getJco_fmly_max()).setInteger("a11", obj.getJco_fmly_tot())
				.setDouble("a12", obj.getJco_fmly_avg()).setString("a13", obj.getRemarks()).setString("m1", username)
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
	
	public String updatebedoccupancyxserv(Tb_Med_Bed_Ex_Servicemen obj1,String username){		
       
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
        
        String msg = "";
        
     try{
            
		String hql = "update Tb_Med_Bed_Ex_Servicemen set qtr=:qtr,year=:year,ofcr_max=:a1,ofcr_tot=:a2,ofcr_avg=:a3,jco_max=:a4,jco_tot=:a5,jco_avg=:a6,"
				+ "ofcr_fmly_max=:a7,ofcr_fmly_tot=:a8,ofcr_fmly_avg=:a9,jco_fmly_max=:a10,jco_fmly_tot=:a11,jco_fmly_avg=:a12,remarks=:a13,"
				+ "need_exms_adm=:e1,need_exms_bed_days=:e2,no_of_fmly_ref_adm=:e3,modified_by=:m1,modified_on=:m2 where id=:id";
		
		
		
		Query query = sessionHQL.createQuery(hql).setString("qtr", obj1.getQtr())
				.setInteger("year", obj1.getYear()).setInteger("a1", obj1.getOfcr_max()).setInteger("a2", obj1.getOfcr_tot())
				.setDouble("a3", obj1.getOfcr_avg()).setInteger("a4", obj1.getJco_max()).setInteger("a5", obj1.getJco_tot())
				.setDouble("a6", obj1.getJco_avg()).setInteger("a7", obj1.getOfcr_fmly_max()).setInteger("a8", obj1.getOfcr_fmly_tot())
				.setDouble("a9", obj1.getOfcr_fmly_avg()).setInteger("a10", obj1.getJco_fmly_max()).setInteger("a11", obj1.getJco_fmly_tot())
				.setDouble("a12", obj1.getJco_fmly_avg()).setString("a13", obj1.getRemarks()).setInteger("e1", obj1.getNeed_exms_adm())
				.setInteger("e2", obj1.getNeed_exms_bed_days()).setInteger("e3",obj1.getNo_of_fmly_ref_adm()).setString("m1", username)
				.setTimestamp("m2", new Date()).setInteger("id", obj1.getId());

	
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
	
		
	public String delete_bed_occupancy_Input(int deleteid, String type) {
		String tbl_name = null;
		if (type.equals("1")) {
			tbl_name = "Tb_Med_Bed_Serving";
		}
		if (type.equals("2")) {
			tbl_name = "Tb_Med_Bed_Ex_Servicemen";
		}

		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "delete from " + tbl_name + " where id=:id";
		int rowCount = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}
	
	
	 public Long checkExitstingbedocc(String sus_no1,String quater,int id,int yr) {
	      
			
			String hql="select count(id) from Tb_Med_Bed_Serving where sus_no=:d1 and qtr=:d2 and year=:d3 and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q2= session.createQuery(hql);
			q2.setParameter("d1", sus_no1);
			q2.setParameter("d2", quater);
			q2.setParameter("d3", yr);
			q2.setParameter("id", id);

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
	 
	 public Long checkExitstingbedocexserv(String sus_no1,String quater,int id,int yr) {
	     
			String hql="select count(id) from Tb_Med_Bed_Ex_Servicemen where sus_no=:d1 and qtr=:d2 and year=:d3 and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q2= session.createQuery(hql);
			q2.setParameter("d1", sus_no1);
			q2.setParameter("d2", quater);
			q2.setParameter("d3", yr);
			q2.setParameter("id", id);

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
	 
	
	 public Boolean getquaterAlreadyExist(String qtr,String sus,String c_id,String type,int yr) {
			Connection conn = null;
			Boolean msg = false;
			try {
				conn = dataSource.getConnection();
				String sql = "";
				PreparedStatement stmt = null;
			
				//String tbl_name = null;
				if (type.equals("1")) {
					
					/*tbl_name = "tb_med_bed_serving";*/
					sql = "SELECT case when sum(count_pers.count) > 0 then false else true end FROM   \r\n" + 
							"(select count(id) from tb_med_bed_serving where qtr=? and sus_no=? and year=?  and id != ?) count_pers";

				}
				if (type.equals("2")) {
					
					sql = "SELECT case when sum(count_pers.count) > 0 then false else true end FROM   \r\n" + 
							"(select count(id) from tb_med_bed_ex_servicemen where qtr=? and sus_no=? and year=?  and id != ?) count_pers";
				}
				

				int id =0;
				if(c_id != null && !c_id.equals(""))
				{
					id = Integer.parseInt(c_id);
				}
				
				stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				if (type.equals("1")) {
				stmt.setString(1, qtr);
				stmt.setString(2, sus);
				stmt.setInt(3, yr);
				stmt.setInt(4, id);
				}
				if (type.equals("2")) {
					stmt.setString(1, qtr);
					stmt.setString(2, sus);
					stmt.setInt(3, yr);
					stmt.setInt(4, id);
					}
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					msg = rs.getBoolean("case");
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
				return msg;
		}
			
		
}
