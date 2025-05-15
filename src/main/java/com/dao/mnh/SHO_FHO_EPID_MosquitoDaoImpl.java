package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.models.mnh.Tb_Med_Eir;
import com.models.mnh.Tb_Med_History_Stay;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;



public class SHO_FHO_EPID_MosquitoDaoImpl implements SHO_FHO_EPID_MosquitoDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	
	public List<Map<String, Object>> search_mosqfecoair_ShoInput(String sus1,String unit1, String dis2,String service1,String frm_dt1, String to_dt1) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String flag = "Y";

			if (!sus1.equals("")) {
				qry += "  r.sus_no = ?";
				flag = "N";
			}
			if (!dis2.equals("")) {
				qry += " and r.disease = ?";
				flag = "N";
			}
			if (!service1.equals("-1")) {
				qry += " and r.service = ?";
				flag = "N";
			}
			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				if (flag.equalsIgnoreCase("Y")) {
					qry += " Where to_char(r.created_on, 'YYYY-MM-DD') >= ? and to_char(r.created_on, 'YYYY-MM-DD') <= ?";
					flag = "N";
				} else {
					qry += " and to_char(r.created_on, 'YYYY-MM-DD') >= ? and to_char(r.created_on, 'YYYY-MM-DD') <= ?";
				}
			}
			
		/*	q = "select r.id,d.rank_desc,z.disease_name,(select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no ='Active'),"
				      + " r.sus_no,r.service,r.category,r.persnl_no,r.disease,r.personal_name  from tb_med_eir r,tb_med_rankcode d , tb_med_daily_surv_disease_mstr z "
				      + " Where r.disease = z.code and r.rank = d.id and  " + qry;*/
			
			// Added view  orbat_all_details_view_mnh instead of tb_miso_orbat_unt_dtl
			q = "select r.id,d.rank_desc,z.disease_name,(select unit_name from orbat_all_details_view_mnh where sus_no = r.sus_no and status_sus_no ='Active'),"
				      + " r.sus_no,r.service,r.category,r.persnl_no,r.disease,r.personal_name  from tb_med_eir r,tb_med_rankcode d , tb_med_daily_surv_disease_mstr z "
				      + " Where r.disease = z.id and r.rank = d.id and  " + qry;
			stmt = conn.prepareStatement(q);
			int j = 1;
			if (!sus1.equals("")) {
				stmt.setString(j, sus1);
				j++;
			}
			if (!dis2.equals("")) {
				stmt.setInt(j, Integer.parseInt(dis2));
				j++;
			}
			if (!service1.equals("-1")) {
				stmt.setString(j, service1);
				j++;
			}
			if (!frm_dt1.equals("") && !to_dt1.equals("")) {
				stmt.setString(j, frm_dt1);
				j++;
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
				
				String Print1 = "onclick=\"  if (confirm('Are you sure you want to print?') ){printData("
						+ rs.getObject(1) + ")}else{ return false;}\"";
				String printButton = "<i class= 'fa fa-print' " + Print1 + " title='Print Data'></i>";
				
				
				String f = "";
				f += updateButton;
				f += deleteButton;
				f += printButton;
				
				
				
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
	
	 public Tb_Med_Eir getmosquitoDetail(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_Eir updateid = (Tb_Med_Eir) sessionHQL.get(Tb_Med_Eir.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}

	public Tb_Med_History_Stay getmosquitoDetail2(int id) {
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionHQL.beginTransaction();
		 	Tb_Med_History_Stay updateid = (Tb_Med_History_Stay) sessionHQL.get(Tb_Med_History_Stay.class, id);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();		
			return updateid;
		}
	//update  mosquito in travel tbl
	 public String update_mosquito_travel(Tb_Med_History_Stay obj,String username,String id){
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		//try{
			String hql = "update Tb_Med_History_Stay  set history_time=:h_time,history_loc=:h_loc,history_date=:h_datee_dt,disease=:h3,modified_by=:m1,modified_on=:m2 where id=:id and eir_id=:eir_id";		
			Query query = sessionHQL.createQuery(hql).setString("h_time", obj.getHistory_time()).setString("h_loc", obj.getHistory_loc()).setDate("h_datee_dt", obj.getHistory_date())
					.setString("h3", obj.getDisease()).
					setString("m1", username).setTimestamp("m2", new Date())
					.setInteger("id", Integer.parseInt(id))
					.setInteger("eir_id", obj.getEir_id());
			
			int val = query.executeUpdate();
			
			
			msg = val > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();			
		
			/*}
		catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}*/
		return msg;
	}
	 

		//update  mosquito in tb_med_eir tbl
	
	
	
		
	 public String update_mosquito(Tb_Med_Eir obj,String username){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		try{
			String hql = "update Tb_Med_Eir  set disease=:h1,case_no=:h2,datee=:h41,service=:h3,relationship=:h4,persnl_no=:h5,category=:h6,rank=:h7," + 
					" personnel_unit=:h8,present_address=:h10,contact_no=:h11,type_of_symptom=:h12,history=:h13,prob_source_insp=:h14," + 
					" en_loc1=:h15,en_loc2=:h16,en_loc3=:h17,final_epid_diag=:h18," + 
					" final_epid_diagnosis=:h19,action_for_patient=:h20,action_by_sho=:h21,repo_type=:h44,breeding_found=:h45,"
					+ "follow_up_down=:h46,history_Of_past=:h47,other_action_by_sho=:h43, " + 
					" recommendation_unit=:h23,recommendation_indl=:h42,date_onset_symp=:h24,date_repo_med_centre=:h25,"+					
					" adhar_card_no =:h28, age=:age,personal_name=:h29,date_of_birth=:date_of_birth,modified_by=:m1,modified_on=:m2 where id=:id";		
			Query query = sessionHQL.createQuery(hql).setInteger("h1", obj.getDisease()).setString("h2", obj.getCase_no()).setDate("h41", obj.getDatee())
					.setString("h3", obj.getService()).setString("h4", obj.getRelationship()).setString("h5",obj.getPersnl_no())
					.setString("h6", obj.getCategory()).setInteger("h7", obj.getEir_rank().getId()).setString("h8", obj.getPersonnel_unit())
					.setString("h10", obj.getPresent_address()).setBigInteger("h11", obj.getContact_no())
					.setString("h12", obj.getType_of_symptom()).setString("h13", obj.getHistory()).setString("h14", obj.getProb_source_insp())
					.setString("h15", obj.getEn_loc1()).setString("h16", obj.getEn_loc2())
					.setString("h17", obj.getEn_loc3())
					.setString("h18", obj.getFinal_epid_diag()).setString("h19",obj.getFinal_epid_diagnosis())
					.setString("h20", obj.getAction_for_patient()).setString("h21", String.join(",", obj.getAction_by_sho()))
					.setString("h44", obj.getRepo_type())
					.setInteger("h45", obj.getBreeding_found())
					.setInteger("h46", obj.getFollow_up_down())
					.setInteger("h47", obj.getHistory_Of_past())
					.setString("h43", obj.getOther_action_by_sho())
					.setString("h23", obj.getRecommendation_unit())
					.setString("h42", obj.getRecommendation_indl())
					.setTimestamp("h24",obj.getDate_onset_symp()).setTimestamp("h25", obj.getDate_repo_med_centre())
					.setBigInteger("h28", obj.getAdhar_card_no())
					.setString("h29", obj.getPersonal_name())
					.setString("age", obj.getAge())
					.setTimestamp("date_of_birth", obj.getDate_of_birth()).
					setString("m1", username).setTimestamp("m2", new Date())
					.setInteger("id", obj.getId());
			
			int val = query.executeUpdate();
			
			
			msg = val > 0 ? "Data Updated Successfully." :"Data Not Updated.";
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
	 
	//update  feco in tb_med_eir tbl
	 public String update_feco(Tb_Med_Eir obj,String username){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		//try{
		String hql = "update Tb_Med_Eir  set disease=:h1,case_no=:h2,datee=:h41,service=:h3,relationship=:h4,persnl_no=:h5,category=:h6,rank=:h7," + 
				" personnel_unit=:h8,present_address=:h10,contact_no=:h11,type_of_symptom=:h12,history=:h13,prob_source_insp=:h14," + 
				" en_loc1=:h15,en_loc2=:h16,en_loc3=:h17,final_epid_diag=:h18,recommendation_indl=:h42," + 
				" final_epid_diagnosis=:h19,action_for_patient=:h20,action_by_sho=:h21,repo_type=:h44,recommendation_unit=:h23,source_of_water=:h51,date_onset_symp=:h24,date_repo_med_centre=:h25,"+					
				" adhar_card_no =:h28, age=:age,personal_name=:h29,date_of_birth=:date_of_birth,modified_by=:m1,modified_on=:m2 where id=:id";		
		Query query = sessionHQL.createQuery(hql).setInteger("h1", obj.getDisease()).setString("h2", obj.getCase_no()).setDate("h41", obj.getDatee())
				.setString("h3", obj.getService()).setString("h4", obj.getRelationship()).setString("h5",obj.getPersnl_no())
				.setString("h6", obj.getCategory()).setInteger("h7", obj.getEir_rank().getId()).setString("h8", obj.getPersonnel_unit())
				.setString("h10", obj.getPresent_address()).setBigInteger("h11", obj.getContact_no())
				.setString("h12", obj.getType_of_symptom()).setString("h13", obj.getHistory()).setString("h14", obj.getProb_source_insp())
				.setString("h15", obj.getEn_loc1()).setString("h16", obj.getEn_loc2())
				.setString("h17", obj.getEn_loc3())
				.setString("h18", obj.getFinal_epid_diag())
				.setString("h42", obj.getRecommendation_indl()).setString("h19",obj.getFinal_epid_diagnosis())
				.setString("h20", obj.getAction_for_patient()).setString("h21", obj.getAction_by_sho())
				.setString("h44", obj.getRepo_type())
				.setString("h23", obj.getRecommendation_unit())
				.setString("h51", obj.getSource_of_water())
				.setTimestamp("h24",obj.getDate_onset_symp()).setTimestamp("h25", obj.getDate_repo_med_centre())
				.setBigInteger("h28", obj.getAdhar_card_no())
				.setString("h29", obj.getPersonal_name())
				.setString("age", obj.getAge())
				.setTimestamp("date_of_birth", obj.getDate_of_birth()).
				setString("m1", username).setTimestamp("m2", new Date())
				.setInteger("id", obj.getId());
	
			int val = query.executeUpdate();
			
			
			msg = val > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();			
		
		//}
		/*catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}*/
		return msg;
	}
	 


	 
	
	 
	
	

public String update_mosquitoAir(Tb_Med_History_Stay obj2,String username){
		 
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
	
		 
		String msg = "";
		try{
		
			
			String hql = "update Tb_Med_History_Stay set ip_date=:ip_date,loc1=:loc1,loc2=:loc2,loc3=:loc3,loc4=:loc4, "
					+ "loc5=:loc5,time1=:time1,time2=:time2,time3=:time3,time4=:time4,time5=:time5,"
					+ "date_air=:date_air,time_air=:time_air," 
					+ "close_contact_air1=:close_contact_air1,date_exp_air1=:date_exp_air1,remarks_air=:remarks_air,"
					+ "close_contact_air2=:close_contact_air2,date_exp_air2=:date_exp_air2,period_segration=:period_segration,"
					+"date_hospitalization=:date_hospitalization,period_infectivity=:period_infectivity,act_patient_room=:act_patient_room,"
					+ "act_segration_room=:act_segration_room,date_certi_infec_free=:date_certi_infec_free,"
					+ "modified_by =:modified_by,modified_on =:modified_on where id =:id";
			
			Query query = sessionHQL.createQuery(hql)
					.setString("modified_by", username).setTimestamp("modified_on", new Date())
					.setInteger("id", obj2.getId());
			
			int val = query.executeUpdate();
			
			msg = val > 0 ? "Data Updated Successfully." :"Data Not Updated.";
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
	 

	public List<Map<String, Object>> get_mosq_print(Integer id3){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
		
			q=" select e.case_no,s.disease_name,e.persnl_no,r.rank_desc,e.personal_name,e.personnel_unit ,e.present_address,\r\n" + 
					"round(cast(e.contact_no as decimal)*100/100) as contact_no,e.date_onset_symp,e.date_repo_med_centre,e.history ,upper (e.repo_type) as repo_type, \r\n" + 
					"c.sys_code_desc as final_epid_diagnosis,c1.sys_code_desc as action_for_patient\r\n" + 
					"\r\n" + 
					"from  tb_med_eir e \r\n" + 
					"left join tb_med_daily_surv_disease_mstr s on s.code = e.disease  \r\n" + 
					"left join tb_med_rankcode r on r.id = e.rank\r\n" + 
					" left join Tb_Med_System_Code c1 on c1.sys_code_value = e.final_epid_diagnosis  and c1.sys_code='ACTION_UNDERTAKEN'\r\n" + 
					" left join Tb_Med_System_Code c on c.sys_code_value = e.final_epid_diagnosis   and c.sys_code='FINAL_EPID_DIAGNOSIS' " 
					+ " where e.id=?  ";
			stmt=conn.prepareStatement(q);   
		    stmt.setInt(1, id3);
		    	
		    ResultSet rs = stmt.executeQuery();     
		  
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			     
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	    list.add(columns);
		 	 }
			      
			 rs.close();
			 stmt.close();
			 conn.close();
	     }catch (SQLException e){
	    	 e.printStackTrace();
		 }finally{
			 if(conn != null){
				 try{
					 conn.close();
				 }catch (SQLException e){
				 }
			}
		}
		return list;
	}
}