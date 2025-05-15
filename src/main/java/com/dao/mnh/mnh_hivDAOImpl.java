package com.dao.mnh;

import java.math.BigDecimal;
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

import com.models.mnh.Tb_Med_HIV;
import com.persistance.util.HibernateUtil;

public class mnh_hivDAOImpl implements mnh_hivDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Long checkExitstinghivinput(String sus_no1,String type,String persnl_no,String id1) {
		
	     int id=0;
			
			if(!id1.equals("") && id1 != null)
			{
				id = Integer.parseInt(id1);
			}
			String hql="select count(id) from Tb_Med_HIV where sus_no=:d1 and type=:d2 and persnl_no=:d3 and id!=:id";
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q2= session.createQuery(hql);
			q2.setParameter("d1", sus_no1);
			q2.setParameter("d2", type);
			q2.setParameter("d3", persnl_no);
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
	public List<Map<String, Object>> search_hiv_Input(String sus1,String unit1, String psn_no, String frm_dt1, String to_dt1,String adhar1,String contact1,String relation1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";

			if (!sus1.equals("")) {
				qry += " Where r.sus_no = ?";
				flag = "N";
			}
			
			
			if (!psn_no.equals("") && !psn_no.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where persnl_no = ?";
					flag = "N";
				} else {
					qry += " and persnl_no = ?";
				}
			}

			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where to_char(r.created_on, 'YYYY-MM-DD') >= ? and to_char(r.created_on, 'YYYY-MM-DD') <= ?";
					flag = "N";
				} else {
					qry += " and to_char(r.created_on, 'YYYY-MM-DD') >= ? and to_char(r.created_on, 'YYYY-MM-DD') <= ?";
				}
			}
		
			if (!adhar1.equals("") && !adhar1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.adhar_card_no = ?";
					flag = "N";
				} else {
					qry += " and r.adhar_card_no = ?";
				}
			}
			if (!contact1.equals("") && !contact1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.contact_no = ?";
					flag = "N";
				} else {
					qry += " and r.contact_no = ?";
				}
			}
			if (!relation1.equals("-1") && !relation1.equals(null)) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where r.relation = ?";
					flag = "N";
				} else {
					qry += " and r.relation = ?";
				}
			}
		

	/*q = "select r.id,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active'),\r\n" + 
			"		r.sus_no,r.category,p.rank_code,r.persnl_no,r.adhar_card_no,r.contact_no,r.relation from tb_med_hiv r \r\n" + 
			"		left join tb_med_rankcode p on p.id = r.rank  " + qry;*/
	
	// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),\r\n" + 
					"		r.sus_no,r.category,p.rank_code,r.persnl_no,r.adhar_card_no,r.contact_no,r.relation from tb_med_hiv r \r\n" + 
					"		left join tb_med_rankcode p on p.id = r.rank  " + qry;
			stmt = conn.prepareStatement(q);
			
			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}			
			if (!psn_no.equals("") && !psn_no.equals(null)) {
				stmt.setString(j, psn_no);
				j++;
			}

			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				stmt.setString(j, frm_dt1);
				j++;
				stmt.setString(j, to_dt1);
				j++;
			}
			if(!adhar1.equals("")){
				stmt.setBigDecimal(j, BigDecimal.valueOf(Long.parseLong(adhar1))); 
		    	j++;
			} 
		
			if(!contact1.equals("")){
				stmt.setBigDecimal(j, BigDecimal.valueOf(Long.parseLong(contact1))); //or you can try below
		    	j++;
			} 
			
			if (!relation1.equals("-1")) {
				stmt.setString(j, relation1);
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
	 public Tb_Med_HIV gethivDetail(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_HIV updateid = (Tb_Med_HIV) sessionHQL.get(Tb_Med_HIV.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 public String update_hiv(Tb_Med_HIV obj,String username){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		 
			String hql = "update Tb_Med_HIV set service=:h1,persnl_name=:h2,persnl_no=:h3,category=:h4,rank=:h5,accession_no=:h6,sex=:h7,"
					+ "relation=:h8,total_service=:h10,marital_status=:h11,command1=:h12,unit=:h13,persnl_unit=:h14,"
					+ "reasons_elisa_screening=:h15,source_infection=:h16,other_details_elisa_screening=:h17,other_details_source_infection=:h18,"
					+ "possible_date_siwsw=:h19,possible_place_siwsw=:h20,name_confirmatory_test=:h21,"
					+ "report_date=:h23,report_received_on=:h24,lab_reference_no=:h25,remarks=:h26,adhar_card_no=:adhar_card_no,contact_no=:contact_no,modified_by=:m1,modified_on=:m2,type=:type where id=:id";

			Query query = sessionHQL.createQuery(hql).setString("h1", obj.getService()).setString("h2", obj.getPersnl_name())
					.setString("h3", obj.getPersnl_no()).setString("h4", obj.getCategory()).setInteger("h5",obj.getHivaid_rank().getId())
				.setString("h6", obj.getAccession_no()).setString("h7", obj.getSex()).setString("h8", obj.getRelation())
				.setInteger("h10", obj.getTotal_service()).setString("h11", obj.getMarital_status())
					.setString("h12", obj.getCommand1()).setString("h13", obj.getUnit()).setString("h14", obj.getPersnl_unit())
					.setString("h15", String.join(",", obj.getReasons_elisa_screening()))
					.setString("h16", String.join(",", obj.getSource_infection()))
					.setString("h17", obj.getOther_details_elisa_screening())
					.setString("h18", obj.getOther_details_source_infection()).setTimestamp("h19",obj.getPossible_date_siwsw())
					.setString("h20", obj.getPossible_place_siwsw()).setString("h21", obj.getName_confirmatory_test())
					.setTimestamp("h23", obj.getReport_date())
					.setTimestamp("h24",obj.getReport_received_on() ).setString("h25", obj.getLab_reference_no())
					.setString("h26", obj.getRemarks()).setString("m1", username).setTimestamp("m2", new Date())
					.setString("type",obj.getType() ).setBigInteger("adhar_card_no", obj.getAdhar_card_no()).setBigInteger("contact_no", obj.getContact_no()).setInteger("id", obj.getId());
			
			int val = query.executeUpdate();
			
			msg = val > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();
	 
		return msg;
	}
}
