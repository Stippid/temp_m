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

import com.models.mnh.Tb_Med_Death;
import com.models.psg.update_census_data.TB_CENSUS_BATT_PHY_CASUALITY;
import com.persistance.util.HibernateUtil;

public class mnh_input_mortalityDAOImpl implements mnh_input_mortalityDAO {
	private static final List<Map<String, Object>> List = null;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}		
	 public Long checkExitstingMortality(String sus_no,String persnl_no,int id) {
			String hql="select count(id) from Tb_Med_Death where sus_no=:d1 and persnl_no=:d2 and id!=:id ";
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q1= session.createQuery(hql);
			q1.setParameter("d1", sus_no);
			q1.setParameter("d2", persnl_no);
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
	
	 
	 
	 
	 
	 public List<Map<String, Object>> search_mortality_input(String sus1, String unit1, String psn_no, String cat1,String from_dt1,
				String to_dt1,String adhar1,String contact1) {
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
				if(!adhar1.equals("")){
					qry += " and r.adhar_card_no = ?";
				} 
				if(!contact1.equals("")){
					qry += " and r.contact_no= ?";
				} 
				if (!psn_no.equals("") && !psn_no.equals(null)) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where r.persnl_no = ?";
						flag = "N";
					} else {
						qry += " and r.persnl_no = ?";
					}
				}

				if (!cat1.equals("-1") && (!psn_no.equals("") && !psn_no.equals(null))) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where r.category = ?";
						flag = "N";
					} else {
						qry += " and r.category = ?";
					}
				}
				if (!from_dt1.equals("") && !from_dt1.equals(null)) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where to_char(sd.date_of_casualty, 'YYYY-MM-DD') >= ?";
						flag = "N";
					} else {
						qry += " and to_char(sd.date_of_casualty, 'YYYY-MM-DD') >= ?";
					}
				}

				if (!to_dt1.equals("") && !to_dt1.equals(null)) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where to_char(sd.date_of_casualty, 'YYYY-MM-DD') <= ?";
						flag = "N";
					} else {
						qry += " and to_char(sd.date_of_casualty, 'YYYY-MM-DD') <= ?";
					}
				}
		
				
				q = "select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
						+ "r.persnl_no,r.persnl_name,r.cause_of_death,concat(d.icd_code,'-',d.icd_description) as icd_code,r.adhar_card_no,r.contact_no"
						+ " from tb_med_death r left join tb_med_d_disease_cause d on  d.icd_code=r.icd_code " + qry+  " ORDER BY r.id DESC";
				
				
				
				
				
				
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (!sus1.equals("")) {
					stmt.setString(j, sus1);
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
				if (!psn_no.equals("") && !psn_no.equals(null)) {
					stmt.setString(j, psn_no);
					j++;
				}

				if (!cat1.equals("-1") && (!psn_no.equals("") && !psn_no.equals(null))) {
					stmt.setString(j, cat1);
					j++;
				}
				if (!from_dt1.equals("") && !from_dt1.equals(null)) {
					stmt.setString(j, from_dt1);
					j++;
				}

				if (!to_dt1.equals("") && !to_dt1.equals(null)) {
					stmt.setString(j, to_dt1);
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
	
	 
	 
/*	 public TB_CENSUS_BATT_PHY_CASUALITY getMorbalityByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	TB_CENSUS_BATT_PHY_CASUALITY updateid = (TB_CENSUS_BATT_PHY_CASUALITY) sessionHQL.get(TB_CENSUS_BATT_PHY_CASUALITY.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}*/
	 public Tb_Med_Death getMorbalityByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Death updateid = (Tb_Med_Death) sessionHQL.get(Tb_Med_Death.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	 
	 /*public List<Map<String, Object>> get_phy_battle_mortality_details_ds(Integer id2) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				q = "select bp.id,g.gender_name,c.personnel_no,c.name,bp.classification_of_casuality,bp.nature_of_casuality,bp.name_of_operation,bp.date_of_casuality,\r\n" + 
						"bp.cause_of_casuality,bp.diagnosis,bp.hospital_name,bp.sector,bp.nok_informed,bp.classification_of_casuality,bp.bde \r\n" + 
						"from tb_psg_census_battle_physical_casuality  bp \r\n" + 
						"inner join  tb_psg_trans_proposed_comm_letter c on bp.comm_id =c.id\r\n" + 
						"inner join  tb_psg_mstr_gender g on c.gender =g.id where bp.id=?";
				stmt = conn.prepareStatement(q);
				stmt.setInt(1, id2);

				ResultSet rs = stmt.executeQuery();
				System.err.println("saaaaaaaaaaaaaaa"+stmt);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					list.add(columns);
					System.err.println("saaalllllllll"+list);
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
		}*/

	 public String updatemortality(Tb_Med_Death obj,String username){
		 
		 
         Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = sessionHQL.beginTransaction();
        
        String msg = "";
      ///try{
    	  String hql = "update Tb_Med_Death set persnl_no=:a1,category=:a2,rank=:a3,persnl_name=:a4,persnl_unit=:a5,"
					+ "age_year=:a6,age_month=:a7,total_service_year=:a8,total_service_month=:a9,"
					+ "gender=:a10,relation=:a11,place_of_death=:a12,nature_of_death=:a13,date_of_death=:a14,"
					+ "operation=:a16,sector=:a17,location=:a18,bde_div_corps_comd=:a19,on_ib_ic=:a20,and_no=:a21,"
					+ "death_summary=:a22,nok_informed=:a23,name_of_nok=:a24,address_of_nok=:a25,remarks=:a26,"
					+ "cause_of_death=:a27,other_details=:a28,icd_code=:a29,icd_desc=:a30,adhar_card_no=:adhar_card_no,contact_no=:contact_no,modified_by=:m1,modified_on=:m2"
					+ " where id=:id";
             
                            
    	  Query query = sessionHQL.createQuery(hql)
    			  .setString("a1",obj.getPersnl_no())
    			  .setString("a2", obj.getCategory())
					.setInteger("a3", obj.getDeath_rank().getId())
					.setString("a4",obj.getPersnl_name())
					.setString("a5",obj.getPersnl_unit())
					.setInteger("a6",obj.getAge_year())
					.setInteger("a7", obj.getAge_month())  
					.setInteger("a8", obj.getTotal_service_year())
					.setInteger("a9",obj.getTotal_service_month())
					.setString("a10", obj.getGender())
					.setString("a11", obj.getRelation())
					.setString("a12", obj.getPlace_of_death())
					.setString("a13", obj.getNature_of_death())
					.setTimestamp("a14",obj.getDate_of_death())
					.setString("a16",obj.getOperation())
					.setString("a17", obj.getSector())
					.setString("a18", obj.getLocation())
					.setString("a19", obj.getBde_div_corps_comd())
					.setString("a20", obj.getOn_ib_ic())
					.setString("a21", obj.getAnd_no())
					.setString("a22", obj.getDeath_summary())
					.setString("a23", obj.getNok_informed())
					.setString("a24", obj.getName_of_nok())
					.setString("a25", obj.getAddress_of_nok())
					.setString("a26", obj.getRemarks())
					.setString("a27", obj.getCause_of_death())
					.setString("a28",obj.getOther_details())
					.setString("a29", obj.getIcd_code())
					.setString("a30", obj.getIcd_desc())
					.setBigInteger("adhar_card_no", obj.getAdhar_card_no()).setBigInteger("contact_no", obj.getContact_no())
					.setString("m1",username).setTimestamp("m2", new Date()).setInteger("id",obj.getId());
    	 
                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
                    tx.commit(); 
      
   
        return msg;
}
	 
	 
	 
	 
	 
	 
	 
	 
}
