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

import com.models.mnh.Tb_Med_Imb;
import com.persistance.util.HibernateUtil;

public class InvalidmentDAOIMPL implements InvalidmentDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

	 public Long checkExitstingInvalidment(String sus_no,String persnl_no,int id) {
			String hql="select count(id) from Tb_Med_Imb where  sus_no=:d1 and persnl_no=:d2 and id!=:id";
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
	 
	 public List<Map<String, Object>> search_invalidment_input(String sus1, String psn_no, String dt_origin1,
				String dt_imb1,String adhar1,String contact1) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				String flag = "Y";

				if (!sus1.equals("")) {
					qry += " Where sus_no = ?";
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
						qry += " Where persnl_no = ?";
						flag = "N";
					} else {
						qry += " and persnl_no = ?";
					}
				}

				if (!dt_origin1.equals("") && !dt_origin1.equals(null)) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where to_char(date_place_origine, 'YYYY-MM-DD') >= ?";
						flag = "N";
					} else {
						qry += " and to_char(date_place_origine, 'YYYY-MM-DD') >= ?";
					}
				}

				if (!dt_imb1.equals("") && !dt_imb1.equals(null)) {
					if (flag.equalsIgnoreCase("Y")) {
						qry += " Where to_char(date_imb, 'YYYY-MM-DD') <= ?";
						flag = "N";
					} else {
						qry += " and to_char(date_imb, 'YYYY-MM-DD') <= ?";
					}
				}

				/*q = "select  b.id,a.sus_no,b.unit_name,a.persnl_no,a.diagnosis_remarks,a.icd_code,to_char(a.date_imb, 'DD/MM/YYYY') as date_imb," + 
						"a.date_place_origine from tb_med_imb a  left join tb_miso_orbat_unt_dtl b on b.sus_no = a.sus_no" + qry;
				*/
				
				// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
				
				q = "select distinct r.id,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
						+ "r.persnl_no,r.diagnosis_remarks, to_char(r.date_imb, 'DD/MM/YYYY') as date_imb ,r.date_place_origine,r.date_place_origine,concat(d.icd_code,'-',d.icd_description) as icd_code1,r.icd_code,r.icd_cause,r.adhar_card_no,r.contact_no "
						+ "from tb_med_imb r left join tb_med_d_disease_cause d on  d.icd_code=r.icd_code " + qry +  "ORDER BY r.id DESC";
				
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

				if (!dt_origin1.equals("") && !dt_origin1.equals(null)) {
					stmt.setString(j, dt_origin1);
					j++;
				}

				if (!dt_imb1.equals("") && !dt_imb1.equals(null)) {
					stmt.setString(j, dt_imb1);
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

	 
	 public Tb_Med_Imb updateinvalid_Byid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	@SuppressWarnings("unused")
			Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Imb updateid = (Tb_Med_Imb) sessionHQL.get(Tb_Med_Imb.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	
	
	 public String updateinvlidment(Tb_Med_Imb imb,String username){
		 
		 
		 
			                 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			                 Transaction tx = sessionHQL.beginTransaction();
			                
			                String msg = "";
			                try{
			                	String hql = "update Tb_Med_Imb set name=:a1,persnl_no=:a2,category=:a3,rank=:a4,persnl_unit=:a5,age=:a6,service_years=:a7,fmn=:a8,"
			        					+ "trade=:a9,diagnosis_remarks=:a10,icd_code=:a11,icd_cause=:a12,date_place_origine=:a13,date_imb=:a14,percent=:a15,"
			        					+ "attributable_aggravated=:a16,date_of_birth=:a17,adhar_card_no=:adhar_card_no,contact_no=:contact_no,modified_by=:m1,modified_on=:m2 where id=:id";
			                	
			                	Query query = sessionHQL.createQuery(hql).setString("a1", imb.getName())
			        					.setString("a2", imb.getPersnl_no()).setString("a3", imb.getCategory())
			        					.setInteger("a4", imb.getInvalid_rank().getId()).setString("a5", imb.getPersnl_unit())
			        					.setInteger("a6", imb.getAge())
			        					.setInteger("a7", imb.getService_years())
			        					.setString("a8", imb.getFmn()).setString("a9", imb.getTrade())
			        					.setString("a10", imb.getDiagnosis_remarks())
			        					.setString("a11", imb.getIcd_code())
			        					.setString("a12", imb.getIcd_cause())
			        					.setDate("a13", imb.getDate_place_origine())
			        					.setDate("a14", imb.getDate_imb()).setInteger("a15", imb.getPercent())
			        					.setString("a16", imb.getAttributable_aggravated())
			        					.setDate("a17", imb.getDate_of_birth()).setBigInteger("adhar_card_no", imb.getAdhar_card_no()).setBigInteger("contact_no", imb.getContact_no())
			        					.setString("m1",username).setTimestamp("m2", new Date()).setInteger("id",imb.getId());
			                	
			                	
			                        msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			                        tx.commit();
			                }			                
			               
			               
			                catch (Exception e) {
			                        msg = "Data Not Updated123.";
			                        tx.rollback();
			                }
			                finally {
			                        sessionHQL.close();
			                }
			                return msg;
			        }
	 
	 
	
	 

}
