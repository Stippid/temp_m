package com.dao.cue;

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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.cue.ReportData;
import com.models.CUE_TB_MISO_CES;
import com.persistance.util.HibernateUtilNA;

public class ceoDAOImpl implements ceoDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public  List<Map<String, Object>>  getcceBySearch(String cce,String ces_cces_no,String roleType,String ccename)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
			String q1="";
			if(cce !="" && cce != null) {
				q1 += " and ces_cces=? ";
			}
			if(ces_cces_no !="" && ces_cces_no != null) {
				q1 += " and ces_cces_no=? ";
			}


			String	q =  "select c_d.id,c_d.ces_cces_no ,c_d.ces_cces,c_d.efctiv_date,c_d.expiry_date,c_d.remarks,c_d.ces_cces_name,c_d.item_seq_no,c_d.ces_namem,(m.item_type) as sub_item_seq,c_d.auth_proposed " +
					" from (select ces.auth_proposed,ces.id,ces.ces_cces_no ,ces.ces_cces,ces.efctiv_date,ces.expiry_date,ces.remarks,ces.ces_cces_name,ces.item_seq_no,(i_mas.item_type) as ces_namem from CUE_TB_MISO_CES ces,CUE_TB_MISO_MMS_ITEM_MSTR i_mas" +
					" where  ces.ces_cces_name =i_mas.item_code) c_d,CUE_TB_MISO_MMS_ITEM_MSTR as m where" +
					" c_d.item_seq_no = m.item_code  "+ q1;

			stmt=conn.prepareStatement(q);
			int j = 1;
			if(cce !="" && cce != null)
			{
				stmt.setString(j, cce);
				j += 1;
			}

			if(ces_cces_no !="" && ces_cces_no != null)
			{
				stmt.setString(j, ces_cces_no);
			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";

				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+")}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
				String f = "";

				f += deleteButton;
				f += updateButton;
				columns.put(metaData.getColumnLabel(1), f);
				list.add(columns);
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
		return list;
	}


	@Override
	public  List<Map<String, Object>>  getcceBySearch1(String ces_cces,String ces_cces_no,String roleType,String ccename)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement stmt =null;
			String q1="";
			if(ces_cces !="" && ces_cces != null) {
				q1 += " and ces_cces=? ";
			}
			if(ces_cces_no !="" && ces_cces_no != null) {
				q1 += " and ces_cces_no=? ";
			}


			String	q =  "select c_d.id,c_d.ces_cces_no ,c_d.ces_cces,c_d.efctiv_date,c_d.expiry_date,c_d.remarks,c_d.ces_cces_name,c_d.item_seq_no,c_d.ces_namem,(m.item_type) as sub_item_seq,c_d.auth_proposed " +
					" from (select ces.auth_proposed,ces.id,ces.ces_cces_no ,ces.ces_cces,ces.efctiv_date,ces.expiry_date,ces.remarks,ces.ces_cces_name,ces.item_seq_no,(i_mas.item_type) as ces_namem from CUE_TB_MISO_CES ces,CUE_TB_MISO_MMS_ITEM_MSTR i_mas" +
					" where  ces.ces_cces_name =i_mas.item_code) c_d,CUE_TB_MISO_MMS_ITEM_MSTR as m where" +
					" c_d.item_seq_no = m.item_code  "+ q1;

			stmt=conn.prepareStatement(q);

			int j = 1;
			if(ces_cces !="" && ces_cces != null)
			{
				stmt.setString(j, ces_cces);
				j += 1;
			}
			if(ces_cces_no !="" && ces_cces_no != null)
			{
				stmt.setString(j, ces_cces_no);
			}

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
		return list;
	}

	@Override
	public String setDeletecce(int appid){
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "DELETE FROM CUE_TB_MISO_CES WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id",appid);
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Deleted Successfully";
		}else {
			return "Deleted not Successfully";
		}
	}

	@Override
	public CUE_TB_MISO_CES getcceDetailsByid(int id)
	{
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_CES where id=:id");
		q.setParameter("id", id);
		CUE_TB_MISO_CES list = (CUE_TB_MISO_CES) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public String UpdatecesDetails(CUE_TB_MISO_CES ces,String username)
	{
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "UPDATE CUE_TB_MISO_CES SET item_seq_no=:item_seq_no, remarks =:remarks, auth_proposed=:auth_proposed,modified_by=:modified_by,modified_on=:modified_on, status='0'  WHERE id=:id";
		Query query = session.createQuery(hql).setString("item_seq_no", ces.getItem_seq_no()).setString("remarks",ces.getRemarks()).setDouble("auth_proposed",ces.getAuth_proposed()).setString("modified_by", username).setString("modified_on", modifydate).setInteger("id",ces.getId());
		int rowCount = query.executeUpdate();
		tx.commit();
		session.close();
		if(rowCount > 0) {
			return "Updated Successfully";
		}else {
			return "Updated not Successfully";
		}
	}


	public long getPersReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade,
			String rank,String rank_cat, String cont_comd, String cont_corps, String cont_div, String cont_bde, String sus_no, String we_pe_no,
			String eff_frm_date, String eff_to_date, String user_arm, String category_of_persn, String sponsor_dire, String training_capacity, 
			String unit_category, String radio1, String type_force, String parent_arm1,String weperadio) {
	 String SearchValue ="";
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			Connection conn = null;
			
		
			    
			    
			try {
				conn = dataSource.getConnection();
				
				 StringBuilder searchValue = new StringBuilder();
				 List<String> conditions = new ArrayList<>();
				 
				    if (!search.equals("")) {
						
				        conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
				        		+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
					            + " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
					            + " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
					            + " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
					            + " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
				    }
				    
				    if (!cont_comd.equals("")) {
				        conditions.add("substring(form_code_control,1,1) = ?");
				    }
				    if (!cont_corps.equals("0")) {
				        conditions.add("substring(form_code_control,1,3) = ?");
				    }
				    if (!cont_div.equals("0")) {
				        conditions.add("substring(form_code_control,1,6) = ?");
				    }
				    if (!cont_bde.equals("0")) {
				        conditions.add("form_code_control = ?");
				    }
				    if (!sus_no.equals("")) {
				        conditions.add("unit_sus = ?");
				    }
				    if (!appt_trade.equals("")) {
				        conditions.add("appt = ?");
				    }
				    if (!rank.equals("")) {
				        conditions.add("code = ?");
				    }
				    if (!we_pe_no.equals("")) {
				        conditions.add("we_pe_no = ?");
				    }
				    if (!user_arm.equals("0")) {
				        conditions.add("userarm_code = ?");
				    }
				    if (!category_of_persn.equals("")) {
				        if (category_of_persn.equals("1")) {
				            conditions.add("cat_pers = 'ERE'");
				        } else if (category_of_persn.equals("2")) {
				            conditions.add(" cat_pers = 'Regimental'"); 
				        }
				    }
				    if (!sponsor_dire.equals("0")) {
				        conditions.add("linedte_sus = ?");
				    }
				    if (!training_capacity.equals("")) {
				        conditions.add("training_capacity = ?");
				    }
				    if (!unit_category.equals("")) {
				        conditions.add("unit_category = ?");
				    }
				    if (!radio1.equals("")) {
				        conditions.add("ct_part_i_ii = ?");
				    }
				    
				    if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				        conditions.add("parentarm_code = ?");
				    }
				    if (!type_force.equals("")) {
				        conditions.add("type_of_force = ?");
				    }
				    if (!rank_cat.equals("") && !rank_cat.equals("--Select--")) {
				        conditions.add("rank_cat = ?");
				    }
				    if (!weperadio.equals("")) {
				        conditions.add("we_pe = ?");
				    }
//				    String convertedFromDate = "";
//					String convertedToDate = "";
//					java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
//			        java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
//				    if (!eff_frm_date.equals("")) {
//					    try {
//					        
//					        java.util.Date date = inputFormat.parse(eff_frm_date);
//					        convertedFromDate = outputFormat.format(date);
//					    } catch (Exception e) {
//					        e.printStackTrace();
//					    }
//					}
//					
//					if (!eff_to_date.equals("")) {
//					    try {
//					        java.util.Date date = inputFormat.parse(eff_to_date);
//					        convertedToDate = outputFormat.format(date);
//					    } catch (Exception e) {
//					        e.printStackTrace();
//					    }
//					}
                    
				    if (!conditions.isEmpty()) {
				        searchValue.append(" WHERE ");
				        searchValue.append(String.join(" AND ", conditions));
				    }
				    
				    
				  		q= "select count(app.*) from(SELECT * from ue_pers_for_zoho_new_report "+ searchValue.toString()+ ") app" ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				int flag = 0;
				if(!search.equals("")) {			
					
					String searchParam = search.toUpperCase() + "%";
					
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag, searchParam);
					flag += 1;
					stmt.setString(flag,searchParam);
					
				}
				if(!cont_comd.equals("")){ 
					flag += 1;
					stmt.setString(flag, cont_comd);
		  	  			}
	  			if(!cont_corps.equals("0")){ 
	  				flag += 1;
					stmt.setString(flag, cont_corps);
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_div); 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_bde);
		  	  			}
		  		if(!sus_no.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, sus_no);
		  	  			}
		  		if(!appt_trade.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, appt_trade);
		  	  			}
		  		if(!rank.equals("") && !rank.equals("--Select--")){ 
		  			flag += 1;
					stmt.setString(flag, rank);
		  	  			}
		  		if(!we_pe_no.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, we_pe_no);
		  	  			}
		  		if(!user_arm.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, user_arm);
		  	  			}
		  		
		  		if(!sponsor_dire.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, sponsor_dire);
		  	  			}
		  		if(!training_capacity.equals("")){ 
		  			flag += 1;
		  			stmt.setInt(flag, Integer.parseInt(training_capacity));
		  	  			}
		  		if(!unit_category.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, unit_category);
		  	  			}
		  		if(!radio1.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, radio1);
		  	  			}
		  		if(!parent_arm1.equals("") && !parent_arm1.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, parent_arm1);
		  	  			}
		  		if(!type_force.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, type_force);
		  	  			}
		  		if(!rank_cat.equals("") && !rank_cat.equals("--Select--")){ 
		  			flag += 1;
					stmt.setString(flag, rank_cat);
		  	  			}
		  		if(!weperadio.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, weperadio);
		  	  			}
//		  		if(!eff_frm_date.equals("")){ 
//                    flag += 1;
//                    stmt.setString(flag, convertedFromDate);
//                }
//                if(!eff_to_date.equals("")){ 
//                    flag += 1;
//                    stmt.setString(flag, convertedToDate);
//                }
                
				ResultSet rs = stmt.executeQuery();
				System.out.println("dddddddddddddDD: " + stmt);
				while (rs.next()) {  
					total = rs.getInt(1);
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
			return (long) total;
		}
	

	public List<Map<String, Object>> getPersReportcountTable(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat,
			String cont_comd, String cont_corps, String cont_div, String cont_bde, String sus_no, String we_pe_no,
			String eff_frm_date, String eff_to_date, String user_arm, String category_of_persn, String sponsor_dire,
			String training_capacity, String unit_category, String radio1, String type_force, String parent_arm1,String weperadio)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		String qry="";
  		try{	  
  			
  			String pageL = "";
  	        
  	        if(pageLength == -1){
  				pageL = "ALL";
  			}else {
  				pageL = String.valueOf(pageLength);
  			}
  			conn = dataSource.getConnection();	
  			
  			 StringBuilder searchValue = new StringBuilder();
			 List<String> conditions = new ArrayList<>();
			 
			// Add search condition if present
			    if (!search.equals("")) {
			        conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
				        		+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
				            + " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
				            + " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
				            + " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
				            + " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
			    }
			    
			    if (!cont_comd.equals("")) {
			        conditions.add("substring(form_code_control,1,1) = ?");
			    }
			    if (!cont_corps.equals("0")) {
			        conditions.add("substring(form_code_control,1,3) = ?");
			    }
			    if (!cont_div.equals("0")) {
			        conditions.add("substring(form_code_control,1,6) = ?");
			    }
			    if (!cont_bde.equals("0")) {
			        conditions.add("form_code_control = ?");
			    }
			    if (!sus_no.equals("")) {
			        conditions.add("unit_sus = ?");
			    }
			    if (!appt_trade.equals("")) {
			        conditions.add("appt = ?");
			    }
			    if (!rank.equals("")) {
			        conditions.add("code = ?");
			    }
			    if (!we_pe_no.equals("")) {
			        conditions.add("we_pe_no = ?");
			    }
			    if (!user_arm.equals("0")) {
			        conditions.add("userarm_code = ?");
			    }
			    if (!category_of_persn.equals("")) {
			        if (category_of_persn.equals("1")) {
			            conditions.add("cat_pers = 'ERE'");
			        } else if (category_of_persn.equals("2")) {
			            conditions.add(" cat_pers = 'Regimental'");  
			        }
			    }
			    if (!sponsor_dire.equals("0")) {
			        conditions.add("linedte_sus = ?");
			    }
			    if (!training_capacity.equals("")) {
			        conditions.add("training_capacity = ?");
			    }
			    if (!unit_category.equals("")) {
			        conditions.add("unit_category = ?");
			    }
			    if (!radio1.equals("")) {
			        conditions.add("ct_part_i_ii = ?");
			    }
			    if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
			        conditions.add("parentarm_code = ?");
			    }
			    if (!type_force.equals("")) {
			        conditions.add("type_of_force = ?");
			    }
			    if (!rank_cat.equals("") && !rank_cat.equals("--Select--")) {
			        conditions.add("rank_cat = ?");
			    }
			    if (!weperadio.equals("")) {
			        conditions.add("we_pe = ?");
			    }
//			    if (!eff_frm_date.equals("")) {
//                    conditions.add("TO_DATE(eff_frm_date, 'DD-MM-YYYY') >= TO_DATE(?, 'DD-MM-YYYY')");
//                }
//                if (!eff_to_date.equals("")) {
//                    conditions.add("TO_DATE(eff_to_date, 'DD-MM-YYYY') <= TO_DATE(?, 'DD-MM-YYYY')");
//                }

			    if (!conditions.isEmpty()) {
			        searchValue.append(" WHERE ");
			        searchValue.append(String.join(" AND ", conditions));
			    }
			    
  			 qry="SELECT  * from ue_pers_for_zoho_new_report " +searchValue.toString() +" limit " + pageL;  
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
	  	
  			int flag = 0;
			if(!search.equals("")) {			
				String searchParam = search.toUpperCase() + "%";
				
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
			}
			
			if(!cont_comd.equals("")){ 
				flag += 1;
				stmt.setString(flag, cont_comd);
	  	  			}
  			if(!cont_corps.equals("0")){ 
  				flag += 1;
				stmt.setString(flag, cont_corps);
	  	  			}
	  		if(!cont_div.equals("0")){ 
	  			flag += 1;
				stmt.setString(flag, cont_div); 
	  	  			}
	  		if(!cont_bde.equals("0")){ 
	  			flag += 1;
				stmt.setString(flag, cont_bde);
	  	  			}
	  		if(!sus_no.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, sus_no);
	  	  			}
	  		if(!appt_trade.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, appt_trade);
	  	  			}
	  		if(!rank.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, rank);
	  	  			}
	  		if(!we_pe_no.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, we_pe_no);
	  	  			}
	  		if(!user_arm.equals("0")){ 
	  			flag += 1;
				stmt.setString(flag, user_arm);
	  	  			}
	  		
	  		if(!sponsor_dire.equals("0")){ 
	  			flag += 1;
				stmt.setString(flag, sponsor_dire);
	  	  			}
	  		if(!training_capacity.equals("")){ 
	  			flag += 1;
	  			stmt.setInt(flag, Integer.parseInt(training_capacity));
	  	  			}
	  		if(!unit_category.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, unit_category);
	  	  			}
	  		if(!radio1.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, radio1);
	  	  			}
	  		
	  		if(!parent_arm1.equals("") && !parent_arm1.equals("0")){ 
	  			flag += 1;
				stmt.setString(flag, parent_arm1);
	  	  			}
	  		if(!type_force.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, type_force);
	  	  			}
	  		if(!rank_cat.equals("") && !rank_cat.equals("--Select--")){ 
	  			flag += 1;
				stmt.setString(flag, rank_cat);
	  	  			}
	  		if(!weperadio.equals("")){ 
	  			flag += 1;
				stmt.setString(flag, weperadio);
	  	  			}
//	  		if(!eff_frm_date.equals("")){ 
//                flag += 1;
//                stmt.setString(flag, eff_frm_date);
//            }
//            if(!eff_to_date.equals("")){ 
//                flag += 1;
//                stmt.setString(flag, eff_to_date);
//            }
	  		
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
  		return list;
  	}


	public ArrayList<ArrayList<String>> getPersExcelReportTable(String appt_trade, String rank, String cont_comd,String cont_corps, 
			String cont_div, String cont_bde, String sus_no, String we_pe_no, String eff_frm_date, String eff_to_date, String user_arm, 
			String category_of_persn, String sponsor_dire, String training_capacity, String unit_category, String ct_part_i_ii,
			String type_force, String parent_arm,String weperadio,String rank_cat ) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{	  

  			conn = dataSource.getConnection();	
  			
  			 StringBuilder searchValue = new StringBuilder();
			 List<String> conditions = new ArrayList<>();
			 
	
			    
			    if (!cont_comd.equals("")) {
			        conditions.add("substring(form_code_control,1,1) = ?");
			    }
			    if (!cont_corps.equals("0")) {
			        conditions.add("substring(form_code_control,1,3) = ?");
			    }
			    if (!cont_div.equals("0")) {
			        conditions.add("substring(form_code_control,1,6) = ?");
			    }
			    if (!cont_bde.equals("0")) {
			        conditions.add("form_code_control = ?");
			    }
			    if (!sus_no.equals("")) {
			        conditions.add("unit_sus = ?");
			    }
			    if (!appt_trade.equals("")) {
			        conditions.add("appt = ?");
			    }
			    if (!rank.equals("")) {
			        conditions.add("code = ?");
			    }
			    if (!we_pe_no.equals("")) {
			        conditions.add("we_pe_no = ?");
			    }
			    if (!user_arm.equals("0")) {
			        conditions.add("wearm = ?");
			    }
			    if (!category_of_persn.equals("")) {
			        if (category_of_persn.equals("1")) {
			            conditions.add("cat_pers = 'ERE'");
			        } else if (category_of_persn.equals("2")) {
			            conditions.add(" cat_pers = 'Regimental'");  
			        }
			    }
			    if (!sponsor_dire.equals("0")) {
			        conditions.add("linedte_sus = ?");
			    }
			    if (!training_capacity.equals("")) {
			        conditions.add("training_capacity = ?");
			    }
			    if (!unit_category.equals("")) {
			        conditions.add("unit_category = ?");
			    }
			    if (!ct_part_i_ii.equals("")) {
			        conditions.add("ct_part_i_ii = ?");
			    }
			    
			    if (!parent_arm.equals("0") && !parent_arm.equals("")) {
			        conditions.add(" parentarm_code  = ?");
			    }
			    
			    if (!rank_cat.equals("") && !rank_cat.equals("--Select--")) {
			        conditions.add("rank_cat = ?");
			    }
			    
			    
			    if (!weperadio.equals("")) {
			        conditions.add("we_pe = ?");
			    }
			    
			    if (!type_force.equals("")) {
			        conditions.add("type_of_force = ?");
			    }
			    
//			    if (!eff_frm_date.equals("")) {
//                    conditions.add("TO_DATE(eff_frm_date, 'DD-MM-YYYY') >= TO_DATE(?, 'DD-MM-YYYY')");
//                }
//                if (!eff_to_date.equals("")) {
//                    conditions.add("TO_DATE(eff_to_date, 'DD-MM-YYYY') <= TO_DATE(?, 'DD-MM-YYYY')");
//                }

			    if (!conditions.isEmpty()) {
			        searchValue.append(" WHERE ");
			        searchValue.append(String.join(" AND ", conditions));
			    }
			    
  			 qry="SELECT  * from ue_pers_for_zoho_new_report " +searchValue.toString();  
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
	  	
  			int flag = 0;
			
			
				if (!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd);
				}
				if (!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps);
				}
				if (!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div);
				}
				if (!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde);
				}
				if (!sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, sus_no);
				}
				if (!appt_trade.equals("")) {
					flag += 1;
					stmt.setString(flag, appt_trade);
				}
				if (!rank.equals("")) {
					flag += 1;
					stmt.setString(flag, rank);
				}
				if (!we_pe_no.equals("")) {
					flag += 1;
					stmt.setString(flag, we_pe_no);
				}
				if (!user_arm.equals("0")) {
					flag += 1;
					stmt.setString(flag, user_arm);
				}
	
				if (!sponsor_dire.equals("0")) {
					flag += 1;
					stmt.setString(flag, sponsor_dire);
				}
				if (!training_capacity.equals("")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(training_capacity));
				}
				if (!unit_category.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_category);
				}
				if (!ct_part_i_ii.equals("")) {
					flag += 1;
					stmt.setString(flag, ct_part_i_ii);
				}
				if (!parent_arm.equals("0") && !parent_arm.equals("")) {
					flag += 1;
					stmt.setString(flag, parent_arm);
				}
				
				if(!rank_cat.equals("") && !rank_cat.equals("--Select--")){ 
		  			flag += 1;
					stmt.setString(flag, rank_cat);
		  	  	}
				
				if(!weperadio.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, weperadio);
		  	  	}
				
				if(!type_force.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, type_force);
		  	  			}
//	  		if(!eff_frm_date.equals("")){ 
//                flag += 1;
//                stmt.setString(flag, eff_frm_date);
//            }
//            if(!eff_to_date.equals("")){ 
//                flag += 1;
//                stmt.setString(flag, eff_to_date);
//            }
	  		

				ResultSet rs = stmt.executeQuery();  
				System.err.println("query excel " + stmt);
				int i =1;  
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(String.valueOf(i++)); //0
					list.add(rs.getString("unit_name"));//0
					list.add(rs.getString("unit_sus"));//1
					list.add(rs.getString("we_pe_no"));//2
					list.add(rs.getString("table_title"));//3
					list.add(rs.getString("eff_frm_date"));//4
					list.add(rs.getString("eff_to_date"));//5
					list.add(rs.getString("arm_desc")); //6
					list.add(rs.getString("training_capacity"));//7
					list.add(rs.getString("comd_name"));//8
					list.add(rs.getString("corps_name"));//9	
					list.add(rs.getString("div_name"));//10
					list.add(rs.getString("bde_name"));//11	
					list.add(rs.getString("ct_part_i_ii"));//12	
					list.add(rs.getString("parentarm"));//13
					list.add(rs.getString("linedte_name"));//14	
					list.add(rs.getString("type_of_force"));//15
					list.add(rs.getString("cat_pers"));//16
					list.add(rs.getString("rank_cat"));//17
					list.add(rs.getString("appt"));//18
					list.add(rs.getString("rank"));//19
					list.add(rs.getString("unit_category"));//20
					list.add(rs.getString("base_auth"));//21
					list.add(rs.getString("mod_auth"));//22
					list.add(rs.getString("foot_auth"));//23	
					list.add(rs.getString("total"));//24
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

	//ARMY AIR DEFENCE REPORT

//	@Override
//	public List<Map<String, Object>> armyAirDefenceReportTable(int startPage, int pageLength, String search, String orderColunm,
//			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
//			String user_arm, String category_of_persn,String parent_arm1) {
//
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Connection conn = null;
//		String qry="";
//		try{
//
//			String pageL = "";
//
//			if(pageLength == -1){
//				pageL = "ALL";
//			}else {
//				pageL = String.valueOf(pageLength);
//			}
//
//			conn = dataSource.getConnection();
//
//			StringBuilder searchValue = new StringBuilder();
//			List<String> conditions = new ArrayList<>();
//
//			if (!search.equals("")) {
//				conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
//						+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
//						+ " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
//						+ " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
//						+ " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
//						+ " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
//			}
//
//			if (!sus_no.equals("")) {
//				conditions.add("unit_sus = ?");
//			}
//			if (!appt_trade.equals("")) {
//				conditions.add("appt = ?");
//			}
//			if (!rank.equals("")) {
//				conditions.add("code = ?");
//			}
//			if (!we_pe_no.equals("")) {
//				conditions.add("we_pe_no = ?");
//			}
//			if (!user_arm.equals("0")) {
//				conditions.add("userarm_code = ?");
//			}
//			if (!category_of_persn.equals("")) {
//				if (category_of_persn.equals("1")) {
//					conditions.add("cat_pers = 'ERE'");
//				} else if (category_of_persn.equals("2")) {
//					conditions.add(" cat_pers = 'Regimental'");
//				}
//			}
//
//			if (!conditions.isEmpty()) {
//				searchValue.append(" WHERE ");
//				searchValue.append(String.join(" AND ", conditions));
//			}
//
//			qry="SELECT table_title as tt,\r\n"
//					+ "parentarm as arm,\r\n"
//					+ "count (parentarm) as pc,\r\n"
//					+ "count (unit_sus) as tou,\r\n"
//					+ "count (rankcatnew) FILTER (where rankcatnew ='OFFICER') as offr,\r\n"
//					+ "count (rankcatnew) FILTER (where rankcatnew ='JCO') as jco,\r\n"
//					+ "count (rankcatnew) FILTER (where rankcatnew ='OR') as or,\r\n"
//					+ "count (rankcatnew) FILTER (where rankcatnew in ('OFFICER','JCO','OR' )) as total\r\n"
//					+ "FROM ue_pers_for_zoho_new_report\r\n"
//					+  searchValue.toString() + "GROUP BY table_title,parentarm ORDER BY table_title limit "+pageL ;
//
//			PreparedStatement stmt=conn.prepareStatement(qry);
//
//			int flag = 0;
//			if(!search.equals("")) {
//				String searchParam = search.toUpperCase() + "%";
//
//				flag += 1;
//				stmt.setString(flag, searchParam);
//				flag += 1;
//				stmt.setString(flag, searchParam);
//				flag += 1;
//				stmt.setString(flag,searchParam);
//				flag += 1;
//				stmt.setString(flag,searchParam);
//				flag += 1;
//				stmt.setString(flag, searchParam);
//				flag += 1;
//				stmt.setString(flag, searchParam);
//				flag += 1;
//				stmt.setString(flag,searchParam);
//				flag += 1;
//				stmt.setString(flag,searchParam);
//				flag += 1;
//				stmt.setString(flag, searchParam);
//			}
//
//			if (!sus_no.equals("")) {
//				flag += 1;
//				stmt.setString(flag, sus_no);
//			}
//			if (!appt_trade.equals("")) {
//				flag += 1;
//				stmt.setString(flag, appt_trade);
//			}
//			if (!rank.equals("")) {
//				flag += 1;
//				stmt.setString(flag, rank);
//			}
//			if (!we_pe_no.equals("")) {
//				flag += 1;
//				stmt.setString(flag, we_pe_no);
//			}
//			if (!user_arm.equals("0")) {
//				flag += 1;
//				stmt.setString(flag, user_arm);
//			}
//
//			ResultSet rs = stmt.executeQuery();
//			ResultSetMetaData metaData = rs.getMetaData();
//			System.out.println("Query AAD Data --> " + stmt);
//			int columnCount = metaData.getColumnCount();
//			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				list.add(columns);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return list;
//	}
//

	
	public ArrayList<ReportData> armyAirDefenceReportTable(HttpSession session) {
		ArrayList<ReportData> reportDataList = new ArrayList<>();
		Map<String, ReportData> reportDataMap = new LinkedHashMap<>(); // Use LinkedHashMap to preserve unit order
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT table_title as tt,\r\n" + " parentarm as arm,\r\n" + " count (parentarm) as pc,\r\n"
					+ " count (unit_sus) as tou,\r\n"
					+ " count (rankcatnew) FILTER (where rankcatnew ='OFFICER') as offr,\r\n"
					+ " count (rankcatnew) FILTER (where rankcatnew ='JCO') as jco,\r\n"
					+ " count (rankcatnew) FILTER (where rankcatnew ='OR') as orc,\r\n"
					+ " count (rankcatnew) FILTER (where rankcatnew in ('OFFICER','JCO','OR' )) as total\r\n"
					+ " FROM ue_pers_for_zoho_new_report\r\n" + " GROUP BY table_title,parentarm ORDER BY table_title ";

			stmt = conn.prepareStatement(q);
			int i = 0;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				String unitType = rs.getString("tt");
				String armName = rs.getString("arm");
				List<String> personnelCounts = new ArrayList<>();
				personnelCounts.add(rs.getString("offr"));
				personnelCounts.add(rs.getString("jco"));
				personnelCounts.add(rs.getString("orc"));
				personnelCounts.add(rs.getString("total"));

				if (!reportDataMap.containsKey(unitType)) {
					reportDataMap.put(unitType, new ReportData(unitType));
				}
				ReportData reportData = reportDataMap.get(unitType);
				reportData.addArmData(armName, personnelCounts);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		reportDataList.addAll(reportDataMap.values());
		return reportDataList;
	}
	@Override
	public List<Map<String, Object>> rankWiseStateStrReportCountTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(arm) like ? )");
			}

			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT  parentarm as arm,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R000','R200') THEN total ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R000','R200') THEN total ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R001','R201') THEN total ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R001','R201') THEN total ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen2,\r\n"
					+ " \r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R004','R204') THEN total ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R004','R204') THEN total ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R114') THEN total ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R114') THEN total ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R128') THEN total ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R128') THEN total ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R007','R207','R307') THEN total ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R007','R207','R307') THEN total ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R008','R208') THEN total ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R008','R208') THEN total ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and code not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN total ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code!='null' THEN total ELSE 0 END) as total_offr1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code!='null' THEN total ELSE 0 END) as total_offr2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='JCO'  THEN total ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OR'  THEN total ELSE 0 END) as or,\r\n"
					+ "SUM (CASE WHEN appt ='FOOTNOTES'  THEN total ELSE 0 END) as footnotes,\r\n"
					+ "SUM (CASE WHEN appt ='REINFORCEMENT'  THEN total ELSE 0 END) as flr,\r\n"
					+ "SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300')   \r\n"
					+  searchValue.toString() +  "GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0 \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "select arm_desc,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R002','R202','26736') THEN auth_amt ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R002','R202','26736') THEN auth_amt ELSE 0 END) as majgen2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R005','26304','25831','R205','R116','R117','26704') THEN auth_amt ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R005','26304','25831','R205','R116','R117','26704') THEN auth_amt ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R007','R207','R307') THEN auth_amt ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R007','R207','R307') THEN auth_amt ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and rank not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN auth_amt ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='1'  THEN auth_amt ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rank_cat ='3'  THEN auth_amt ELSE 0 END) as or,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "SUM (CASE WHEN rank_cat in ('1','0','3' )  THEN auth_amt ELSE 0 END) as auth_amt\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+  searchValue.toString() +  "GROUP BY  arm_desc  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}

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
		return list;
	}

	@Override
	public long rankWiseStateStrReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {

		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(arm) like ? )");
			}


			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" And ");
				searchValue.append(String.join(" AND ", conditions));
			}


			q= "select count(app.*) from(SELECT  parentarm as arm,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R000','R200') THEN total ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R000','R200') THEN total ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R001','R201') THEN total ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R001','R201') THEN total ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen2,\r\n"
					+ " \r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R004','R204') THEN total ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R004','R204') THEN total ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R114') THEN total ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R114') THEN total ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R128') THEN total ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R128') THEN total ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R007') THEN total ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R007') THEN total ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R008','R208') THEN total ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R008','R208') THEN total ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and code not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN total ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code!='null' THEN total ELSE 0 END) as total_offr1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code!='null' THEN total ELSE 0 END) as total_offr2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='JCO'  THEN total ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OR'  THEN total ELSE 0 END) as or,\r\n"
					+ "SUM (CASE WHEN appt ='FOOTNOTES'  THEN total ELSE 0 END) as footnotes,\r\n"
					+ "SUM (CASE WHEN appt ='REINFORCEMENT'  THEN total ELSE 0 END) as flr,\r\n"
					+ "SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300')   \r\n"
					+  searchValue.toString() +  "GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0 \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "select arm_desc,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R002','R202','26736') THEN auth_amt ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R002','R202','26736') THEN auth_amt ELSE 0 END) as majgen2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R005','26304','25831','R205','R116','R117','26704') THEN auth_amt ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R005','26304','25831','R205','R116','R117','26704') THEN auth_amt ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R007') THEN auth_amt ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R007') THEN auth_amt ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and rank not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN auth_amt ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='1'  THEN auth_amt ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rank_cat ='3'  THEN auth_amt ELSE 0 END) as or,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "SUM (CASE WHEN rank_cat in ('1','0','3' )  THEN auth_amt ELSE 0 END) as auth_amt\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+  searchValue.toString() +  "GROUP BY  arm_desc  ) app" ;

			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";
				flag += 1;
				stmt.setString(flag, searchParam);
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public ArrayList<ArrayList<String>> rankWiseStateStrReportTableExcel(String appt_trade, String rank, String sus_no,
			String we_pe_no, String user_arm, String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		String SearchValue="";
		try{

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" And ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT  parentarm as arm,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R000','R200') THEN total ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R000','R200') THEN total ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R001','R201') THEN total ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R001','R201') THEN total ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen2,\r\n"
					
					/*+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R002','R202') THEN auth_amt ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R002','R202') THEN auth_amt ELSE 0 END) as majgen2,\r\n"
					+ "\r\n"*/
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R003','R110','R203') THEN total ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R004','R204') THEN total ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R004','R204') THEN total ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R005','26304','25831','R205','R116','R117','26704') THEN total ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R114') THEN total ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R114') THEN total ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R006','R128','R129','R306') THEN total ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R128') THEN total ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R128') THEN total ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R007','R207','R307') THEN total ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R007','R207','R307') THEN total ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R008','R208') THEN total ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R008','R208') THEN total ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and code not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN total ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code!='null' THEN total ELSE 0 END) as total_offr1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code!='null' THEN total ELSE 0 END) as total_offr2,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='JCO'  THEN total ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OR'  THEN total ELSE 0 END) as or,\r\n"
					+ "SUM (CASE WHEN appt ='FOOTNOTES'  THEN total ELSE 0 END) as footnotes,\r\n"
					+ "SUM (CASE WHEN appt ='REINFORCEMENT'  THEN total ELSE 0 END) as flr,\r\n"
					+ "SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300')   \r\n"
					+  searchValue.toString() +  "GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0 \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "select arm_desc,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R000','R200') THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer3,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R001','R201') THEN auth_amt ELSE 0 END) as officer4,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen1,\r\n"
					+ "SUM (CASE WHEN rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' and code IN ('R002','R202','26736') THEN total ELSE 0 END) as majgen2,\r\n"
					
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R003','R110','R203') THEN auth_amt ELSE 0 END) as brig2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R004','R204') THEN auth_amt ELSE 0 END) as col2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R005') THEN auth_amt ELSE 0 END) as ltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R005') THEN auth_amt ELSE 0 END) as ltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R114') THEN auth_amt ELSE 0 END) as colltcol2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R006','R128','R129','R306') THEN auth_amt ELSE 0 END) as maj2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R128') THEN auth_amt ELSE 0 END) as majcapt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R007','R207','R307') THEN auth_amt ELSE 0 END) as capt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R007','R207','R307') THEN auth_amt ELSE 0 END) as capt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank IN ('R008','R208') THEN auth_amt ELSE 0 END) as lt2,\r\n"
					+ "\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and rank not IN (\r\n"
					+ "'R099')\r\n"
					+ "and ct_part_i_ii!='Others' THEN auth_amt ELSE 0 END) as unsp,\r\n"
					+ " SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartI' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr1,\r\n"
					+ "SUM (CASE WHEN rank_cat ='0' and ct_part_i_ii='CTPartII' and rank!='null' THEN auth_amt ELSE 0 END) as auth_amt_offr2,\r\n"
					+ "SUM (CASE WHEN rank_cat ='1'  THEN auth_amt ELSE 0 END) as jco,\r\n"
					+ "SUM (CASE WHEN rank_cat ='3'  THEN auth_amt ELSE 0 END) as or,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "SUM (CASE WHEN rank_cat in ('1','0','3' )  THEN auth_amt ELSE 0 END) as auth_amt\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+  searchValue.toString() +  "GROUP BY  arm_desc ";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm"));//0
				list.add(rs.getString("officer1"));//1
				list.add(rs.getString("officer2"));//2
				list.add(rs.getString("officer3"));//3
				list.add(rs.getString("officer4"));//4
				list.add(rs.getString("unsp"));//5
				list.add(rs.getString("jco")); //6
				list.add(rs.getString("or"));//7
				list.add(rs.getString("flr"));//8
				list.add(rs.getString("footnotes"));//8
				list.add(rs.getString("total"));//9
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


	@Override
	public List<Map<String, Object>> armWiseStateStrReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(arm) like ? )");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT parentarm as arm,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO'and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as orT,\r\n"
					+ "sum(CASE WHEN  appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "sum(CASE WHEN  appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "sum(CASE WHEN  rankcatnew in ('JCO','OFFICER','OR' ) THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n"
					+ searchValue.toString()+ "GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0  \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "SELECT arm_desc as arm,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1'and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as orT,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "sum(CASE WHEN  rank_cat in ('0','1','3' ) THEN auth_amt ELSE 0 END) as total\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+ searchValue.toString()+ "GROUP BY  arm_desc  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}

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
		return list;
	}

	@Override
	public long armWiseStateStrReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {

		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(arm) like ? )");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}


			q= "select count(app.*) from(SELECT parentarm as arm,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO'and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as orT,\r\n"
					+ "sum(CASE WHEN  appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "sum(CASE WHEN  appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "sum(CASE WHEN  rankcatnew in ('JCO','OFFICER','OR' ) THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n"
					+  searchValue.toString()+  "GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0 \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "SELECT arm_desc as arm,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1'and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as orT,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "sum(CASE WHEN  rank_cat in ('0','1','3' ) THEN auth_amt ELSE 0 END) as total\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+ searchValue.toString()+ "GROUP BY  arm_desc ) app" ;

			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";
				flag += 1;
				stmt.setString(flag, searchParam);
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public ArrayList<ArrayList<String>> armWiseStateStrReportTableExcel(String category_of_persn, String parent_arm ){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry= "SELECT parentarm as arm,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OFFICER' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO'and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='JCO' and ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartI' THEN total ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and ct_part_i_ii='CTPartII' THEN total ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rankcatnew ='OR' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN total ELSE 0 END) as orT,\r\n"
					+ "sum(CASE WHEN  appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "sum(CASE WHEN  appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "sum(CASE WHEN  rankcatnew in ('JCO','OFFICER','OR' ) THEN total ELSE 0 END) as total\r\n"
					+ "FROM   ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n"
					+  searchValue.toString()+"GROUP BY  parentarm HAVING SUM (CASE WHEN rankcatnew in ('JCO','OFFICER','OR' )  THEN total ELSE 0 END)  > 0 \r\n"
					+ "\r\n"
					+ "UNION ALL \r\n"
					+ "\r\n"
					+ "SELECT arm_desc as arm,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as officer1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as officer2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='0' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as officerT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1'and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as jco1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as jco2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='1' and ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as jcoT,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartI' THEN auth_amt ELSE 0 END) as or1,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and ct_part_i_ii='CTPartII' THEN auth_amt ELSE 0 END) as or2,\r\n"
					+ "sum(CASE WHEN  rank_cat ='3' and  ct_part_i_ii in ('CTPartI','CTPartII') THEN auth_amt ELSE 0 END) as orT,\r\n"
					+ "'0' as footnotes,\r\n"
					+ "'0' as flr,\r\n"
					+ "sum(CASE WHEN  rank_cat in ('0','1','3' ) THEN auth_amt ELSE 0 END) as total\r\n"
					+ "from cue_gs_pool where status ='1'\r\n"
					+ searchValue.toString()+ "GROUP BY  arm_desc  ";

			PreparedStatement stmt=conn.prepareStatement(qry);
			int flag = 0;

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}

			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm"));//0
				list.add(rs.getString("officer1"));//1
				list.add(rs.getString("officer2"));//2
				list.add(rs.getString("officerT"));//3
				list.add(rs.getString("jco1"));//4
				list.add(rs.getString("jco2"));//5
				list.add(rs.getString("jcoT")); //6
				list.add(rs.getString("or1"));//7
				list.add(rs.getString("or2"));//8
				list.add(rs.getString("orT"));//9
				list.add(rs.getString("footnotes"));//10
				list.add(rs.getString("flr"));//11
				list.add(rs.getString("total"));//12

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



	@Override
	public List<Map<String, Object>> estStrReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
						+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
						+ " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
						+ " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
						+ " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
						+ " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
			}

			if (!sus_no.equals("")) {
				conditions.add("unit_sus = ?");
			}
			if (!appt_trade.equals("")) {
				conditions.add("appt = ?");
			}
			if (!rank.equals("")) {
				conditions.add("code = ?");
			}
			if (!we_pe_no.equals("")) {
				conditions.add("we_pe_no = ?");
			}
			if (!user_arm.equals("0")) {
				conditions.add("userarm_code = ?");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="WITH part_counts AS (\r\n"
					+ "  SELECT \r\n"
					+ "    ct_part_i_ii,\r\n"
					+ "    arm_desc,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('GEN','GEN/EQUIVALENT') THEN total ELSE 0 END) as gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('LT GEN','LT GEN/EQUIVALENT') THEN total ELSE 0 END) as lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('MAJ GEN','MAJ GEN/EQUIVALENT') THEN total ELSE 0 END) as maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT') THEN total ELSE 0 END) as brig_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL','COL [TS]','COL/EQUIVALENT') THEN total ELSE 0 END) as col_ltcol,\r\n"
					+ "	 SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN total ELSE 0 END) as col_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank NOT IN ('GEN','GEN/EQUIVALENT', 'LT GEN','LT GEN/EQUIVALENT', 'MAJ GEN','MAJ GEN/EQUIVALENT', 'BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT', 'COL','COL [TS]','COL/EQUIVALENT','COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN base_auth ELSE 0 END) as other_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) as jco_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) as or_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "    SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "    SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "    SUM(CASE WHEN rankcatnew IN ('OFFICER','JCO', 'OR') THEN total ELSE 0 END) AS total_count\r\n"
					+ "  FROM ue_pers_for_zoho_new_report WHERE parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n" +  searchValue.toString()
					+ "  GROUP BY ct_part_i_ii, arm_desc\r\n"
					+ ")\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part I' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS total_count\r\n"
					+ "\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part II' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS total_count\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "    SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "    SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "WHERE ct_part_i_ii IN ('CTPartI', 'CTPartII')\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Auth of Select RK' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "	'0',\r\n"
					+ "     SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) as offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Distr of UNSP RKS' AS part,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "	SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total(CT I & CT II)(Less AMC/ADC)' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'AMC/ADC' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);

			}


			if(!sus_no.equals("")){
				flag += 1;
				stmt.setString(flag, sus_no);
			}
			if(!appt_trade.equals("")){
				flag += 1;
				stmt.setString(flag, appt_trade);
			}
			if(!rank.equals("")){
				flag += 1;
				stmt.setString(flag, rank);
			}
			if(!we_pe_no.equals("")){
				flag += 1;
				stmt.setString(flag, we_pe_no);
			}
			if(!user_arm.equals("0")){
				flag += 1;
				stmt.setString(flag, user_arm);
			}


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
		return list;
	}

	@Override
	public long estStrReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {

		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
						+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
						+ " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
						+ " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
						+ " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
						+ " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
			}


			if (!sus_no.equals("")) {
				conditions.add("unit_sus = ?");
			}
			if (!appt_trade.equals("")) {
				conditions.add("appt = ?");
			}
			if (!rank.equals("")) {
				conditions.add("code = ?");
			}
			if (!we_pe_no.equals("")) {
				conditions.add("we_pe_no = ?");
			}
			if (!user_arm.equals("0")) {
				conditions.add("userarm_code = ?");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}


			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}


			q= "select count(app.*) from(WITH part_counts AS (\r\n"
					+ "  SELECT \r\n"
					+ "    ct_part_i_ii,\r\n"
					+ "    arm_desc,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('GEN','GEN/EQUIVALENT') THEN total ELSE 0 END) as gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('LT GEN','LT GEN/EQUIVALENT') THEN total ELSE 0 END) as lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('MAJ GEN','MAJ GEN/EQUIVALENT') THEN total ELSE 0 END) as maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT') THEN total ELSE 0 END) as brig_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL','COL [TS]','COL/EQUIVALENT') THEN total ELSE 0 END) as col_ltcol,\r\n"
					+ "	 SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN total ELSE 0 END) as col_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank NOT IN ('GEN','GEN/EQUIVALENT', 'LT GEN','LT GEN/EQUIVALENT', 'MAJ GEN','MAJ GEN/EQUIVALENT', 'BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT', 'COL','COL [TS]','COL/EQUIVALENT','COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN base_auth ELSE 0 END) as other_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) as jco_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) as or_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "    SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "    SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "    SUM(CASE WHEN rankcatnew IN ('OFFICER','JCO', 'OR') THEN total ELSE 0 END) AS total_count\r\n"
					+ "  FROM ue_pers_for_zoho_new_report  WHERE parentarm_code NOT IN ('3400', '4800','3700','3300')\r\n" +  searchValue.toString()
					+ "  GROUP BY ct_part_i_ii, arm_desc\r\n"
					+ ")\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part I' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS total_count\r\n"
					+ "\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part II' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS total_count\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "    SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "    SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "WHERE ct_part_i_ii IN ('CTPartI', 'CTPartII')\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Auth of Select RK' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "	'0',\r\n"
					+ "     SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) as offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Distr of UNSP RKS' AS part,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "	SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total(CT I & CT II)(Less AMC/ADC)' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'AMC/ADC' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts    ) app" ;

			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);


			}

			if(!sus_no.equals("")){
				flag += 1;
				stmt.setString(flag, sus_no);
			}
			if(!appt_trade.equals("")){
				flag += 1;
				stmt.setString(flag, appt_trade);
			}
			if(!rank.equals("")){
				flag += 1;
				stmt.setString(flag, rank);
			}
			if(!we_pe_no.equals("")){
				flag += 1;
				stmt.setString(flag, we_pe_no);
			}
			if(!user_arm.equals("0")){
				flag += 1;
				stmt.setString(flag, user_arm);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public ArrayList<ArrayList<String>> sdFormatOneReportTableExcel(String category_of_persn, String parent_arm ){
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry= "WITH part_counts AS (\r\n"
					+ "  SELECT \r\n"
					+ "    ct_part_i_ii,\r\n"
					+ "    arm_desc,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('GEN','GEN/EQUIVALENT') THEN total ELSE 0 END) as gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('LT GEN','LT GEN/EQUIVALENT') THEN total ELSE 0 END) as lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('MAJ GEN','MAJ GEN/EQUIVALENT') THEN total ELSE 0 END) as maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT') THEN total ELSE 0 END) as brig_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL','COL [TS]','COL/EQUIVALENT') THEN total ELSE 0 END) as col_ltcol,\r\n"
					+ "	 SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank IN ('COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN total ELSE 0 END) as col_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' AND rank NOT IN ('GEN','GEN/EQUIVALENT', 'LT GEN','LT GEN/EQUIVALENT', 'MAJ GEN','MAJ GEN/EQUIVALENT', 'BRIG', 'BRIG/COLONEL','BRIG/EQUIVALENT', 'COL','COL [TS]','COL/EQUIVALENT','COL/LT COL','COL/LT COL/MAJ','COL/LT COL/MAJ/CAPT') THEN base_auth ELSE 0 END) as other_officers,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) as jco_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) as or_count,\r\n"
					+ "    SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "    SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) as footnotes,\r\n"
					+ "    SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) as flr,\r\n"
					+ "    SUM(CASE WHEN rankcatnew IN ('OFFICER','JCO', 'OR') THEN total ELSE 0 END) AS total_count\r\n"
					+ "  FROM ue_pers_for_zoho_new_report SummarySanctionedStrReport\r\n" +  searchValue.toString()
					+ "  GROUP BY ct_part_i_ii, arm_desc\r\n"
					+ ")\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part I' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartI') AS total_count\r\n"
					+ "\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'CT Part II' AS part,\r\n"
					+ "    (SELECT SUM(gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS gen_officers,\r\n"
					+ "    (SELECT SUM(lt_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS lt_gen_officers,\r\n"
					+ "    (SELECT SUM(maj_gen_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS maj_gen_officers,\r\n"
					+ "    (SELECT SUM(brig_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS brig_officers,\r\n"
					+ "    (SELECT SUM(col_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_officers,\r\n"
					+ "	(SELECT SUM(col_ltcol) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS col_ltcol,\r\n"
					+ "    (SELECT SUM(other_officers) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS other_officers,\r\n"
					+ "    (SELECT SUM(offr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS offr,\r\n"
					+ "    (SELECT SUM(jco_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS jco_count,\r\n"
					+ "    (SELECT SUM(or_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS or_count,\r\n"
					+ "    (SELECT SUM(flr) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS flr,\r\n"
					+ "    (SELECT SUM(footnotes) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS footnotes,\r\n"
					+ "    (SELECT SUM(total_count) FROM part_counts WHERE ct_part_i_ii = 'CTPartII') AS total_count\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "    SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "    SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "WHERE ct_part_i_ii IN ('CTPartI', 'CTPartII')\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Auth of Select RK' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "	'0',\r\n"
					+ "     SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) as offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(gen_officers + lt_gen_officers + maj_gen_officers + brig_officers + col_officers + col_ltcol) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Distr of UNSP RKS' AS part,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	'0',\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    '0',\r\n"
					+ "    '0',\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "	SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total(CT I & CT II)(Less AMC/ADC)' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'AMC/ADC' AS part,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN gen_officers ELSE 0 END) AS gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN lt_gen_officers ELSE 0 END) AS lt_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN maj_gen_officers ELSE 0 END) AS maj_gen_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN brig_officers ELSE 0 END) AS brig_officers,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_officers ELSE 0 END) AS col_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN col_ltcol ELSE 0 END) AS col_ltcol,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN other_officers ELSE 0 END) AS other_officers,\r\n"
					+ "	SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN offr ELSE 0 END) AS offr,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN jco_count ELSE 0 END) AS jco_count,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN or_count ELSE 0 END) AS or_count,\r\n"
					+ "	SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN flr ELSE 0 END) AS flr,\r\n"
					+ "    SUM(CASE WHEN arm_desc NOT IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN footnotes ELSE 0 END) AS footnotes,\r\n"
					+ "    SUM(CASE WHEN arm_desc IN ('ARMY DENTAL CORPS','ARMY MEDICAL CORPS') THEN total_count ELSE 0 END) AS total_count\r\n"
					+ "FROM part_counts\r\n"
					+ "UNION ALL\r\n"
					+ "SELECT \r\n"
					+ "    'Total' AS part,\r\n"
					+ "    SUM(gen_officers) AS gen_officers,\r\n"
					+ "    SUM(lt_gen_officers) AS lt_gen_officers,\r\n"
					+ "    SUM(maj_gen_officers) AS maj_gen_officers,\r\n"
					+ "    SUM(brig_officers) AS brig_officers,\r\n"
					+ "    SUM(col_officers) AS col_officers,\r\n"
					+ "	SUM(col_ltcol) AS col_ltcol,\r\n"
					+ "    SUM(other_officers) AS other_officers,\r\n"
					+ "	SUM(offr) AS offr,\r\n"
					+ "    SUM(jco_count) AS jco_count,\r\n"
					+ "    SUM(or_count) AS or_count,\r\n"
					+ "	SUM(flr) AS flr,\r\n"
					+ "    SUM(footnotes) AS footnotes,\r\n"
					+ "    SUM(total_count) AS total_count\r\n"
					+ "FROM part_counts   ";

			PreparedStatement stmt=conn.prepareStatement(qry);
			int flag = 0;

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}

			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("part"));//0
				list.add(rs.getString("gen_officers"));//1
				list.add(rs.getString("lt_gen_officers"));//2
				list.add(rs.getString("maj_gen_officers"));//3
				list.add(rs.getString("brig_officers"));//4
				list.add(rs.getString("col_officers"));//5
				list.add(rs.getString("other_officers")); //6
				list.add(rs.getString("offr"));//7
				list.add(rs.getString("jco_count"));//8
				list.add(rs.getString("or_count"));//9
				list.add(rs.getString("flr"));//10
				list.add(rs.getString("footnotes"));//11
				list.add(rs.getString("total_count"));//12

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
	@Override
	public List<Map<String, Object>> armWiseStrCTiReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(parentarm) like ? )");
			}

			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR' or  appt ='REINFORCEMENT' THEN total ELSE 0 END) AS or,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartI' and parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n"  +  searchValue.toString()
					+ "GROUP BY parentarm "
					+ " HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0 limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";
				flag += 1;
				stmt.setString(flag, searchParam);
			}

			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}

			ResultSet rs = stmt.executeQuery();
			System.err.println("armWiseStrCTiReportTable  " + stmt);
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
		return list;
	}

	@Override
	public long armWiseStrCTiReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;


		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(parentarm) like ? )");
			}


			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}


			q= "select count(app.*) from(SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR' or  appt ='REINFORCEMENT'  THEN total ELSE 0 END) AS or,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartI' and parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n" +  searchValue.toString()
					+ "GROUP BY parentarm "
					+ " HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0) app" ;

			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";
				flag += 1;
				stmt.setString(flag, searchParam);
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}

	@Override
	public ArrayList<ArrayList<String>> armWiseStrCTiReportTableExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR' or  appt ='REINFORCEMENT' THEN total ELSE 0 END) AS or,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartI' and parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n"  +  searchValue.toString()
					+ "GROUP BY parentarm \r\n"
					+ "HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
				flag += 1;
				stmt.setString(flag, parent_arm);
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("parentarm"));//0
				list.add(rs.getString("offr"));//1
				list.add(rs.getString("jco")); //6
				list.add(rs.getString("or"));//7
				list.add(rs.getString("flr"));//8
				list.add(rs.getString("fn"));//8
				list.add(rs.getString("totalstr"));//9
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
	@Override
	public List<Map<String, Object>> armWiseStrCTiiReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR'  or  appt ='REINFORCEMENT'  THEN total ELSE 0 END) AS or,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartII' and parentarm_code NOT IN ('3400', '4800','3700','3300')\r\n" +  searchValue.toString()
					+ "GROUP BY parentarm "
					+ "HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0 limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}



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
		return list;
	}

	@Override
	public long armWiseStrCTiiReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				conditions.add("parentarm_code = ?");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR'  or  appt ='REINFORCEMENT'  THEN total ELSE 0 END) AS or,\r\n"
					+ "SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartII' and parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n" +  searchValue.toString()
					+ "GROUP BY parentarm \r\n"
					+ "HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0 ) app";

			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}
			if (!parent_arm1.equals("") && !parent_arm1.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
				flag += 1;
				stmt.setString(flag, parent_arm1);
			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> armWiseStrCTiiReportTableExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT parentarm,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OFFICER'  THEN total ELSE 0 END) AS offr,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'JCO'  THEN total ELSE 0 END) AS jco,\r\n"
					+ " SUM(CASE WHEN rank_cat = 'OR'  or  appt ='REINFORCEMENT'  THEN total ELSE 0 END) AS or,\r\n"
					+ "SUM(CASE WHEN appt ='REINFORCEMENT' or rank_cat ='OR' THEN total ELSE 0 END) AS inclflr,\r\n"
					+ " SUM(CASE WHEN appt ='FOOTNOTES' THEN total ELSE 0 END) AS fn,\r\n"
					+ " SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) AS totalstr,\r\n"
					+ " SUM(CASE WHEN appt ='REINFORCEMENT' THEN total ELSE 0 END) AS flr\r\n"
					+ "FROM ue_pers_for_zoho_new_report\r\n"
					+ " where ct_part_i_ii = 'CTPartII' and parentarm_code NOT IN ('3400', '4800','3700','3300') \r\n" +  searchValue.toString()
					+ "GROUP BY parentarm \r\n"
					+ "HAVING SUM(case when rank_cat in ('OFFICER','JCO','OR')   then total else 0 end) > 0";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
				flag += 1;
				stmt.setString(flag, parent_arm);
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("parentarm"));//0
				list.add(rs.getString("offr"));//1
				list.add(rs.getString("jco")); //6
				list.add(rs.getString("or"));//7
				list.add(rs.getString("flr"));//8
				list.add(rs.getString("fn"));//8
				list.add(rs.getString("totalstr"));//9
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

	@Override
	public List<Map<String, Object>> dteWiseStrReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String appt_trade, String rank,String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn,String parent_arm1) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
						+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
						+ " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
						+ " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
						+ " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
						+ " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
			}

			if (!sus_no.equals("")) {
				conditions.add("unit_sus = ?");
			}
			if (!appt_trade.equals("")) {
				conditions.add("appt = ?");
			}
			if (!rank.equals("")) {
				conditions.add("code = ?");
			}
			if (!we_pe_no.equals("")) {
				conditions.add("we_pe_no = ?");
			}
			if (!user_arm.equals("0")) {
				conditions.add("userarm_code = ?");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT \r\n"
					+ "SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) AS GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS LT_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS MAJ_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS BRIG,\r\n"
					+ "SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS COL,\r\n"
					+ "SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS LT_COL,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS MAJ,\r\n"
					+ "SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS CAPT,\r\n"
					+ "SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS LT,\r\n"
					+ "SUM(CASE WHEN rank = 'FD MARSHAL' THEN total ELSE 0 END) AS FD_MARSHAL,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ " SUM(total)Total FROM ue_pers_for_zoho_new_report  \r\n" +  searchValue.toString()
					+ "	limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);

			}


			if(!sus_no.equals("")){
				flag += 1;
				stmt.setString(flag, sus_no);
			}
			if(!appt_trade.equals("")){
				flag += 1;
				stmt.setString(flag, appt_trade);
			}
			if(!rank.equals("")){
				flag += 1;
				stmt.setString(flag, rank);
			}
			if(!we_pe_no.equals("")){
				flag += 1;
				stmt.setString(flag, we_pe_no);
			}
			if(!user_arm.equals("0")){
				flag += 1;
				stmt.setString(flag, user_arm);
			}


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
		return list;
	}

	@Override
	public long dteWiseStrReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String appt_trade, String rank, String rank_cat, String sus_no, String we_pe_no,
			String user_arm, String category_of_persn, String parent_arm1) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? or upper(unit_name) like ? or upper(we_pe_no) like ? or upper(table_title) like ?"
						+ "or upper(comd_name) like ? or upper(corps_name) like ? or upper(div_name) like ? or upper(bde_name) like ?"
						+ " or upper(arm_desc) like ? or cast(training_capacity as text) like ? or upper(ct_part_i_ii) like ?"
						+ " or upper(parentarm) like ? or upper(linedte_name) like ?  or upper(type_of_force) like ?  or upper(cat_pers) like ?  or upper(rank_cat) like ? "
						+ " or upper(appt) like ?  or upper(rank) like ?  or upper(unit_category) like ?  or cast(base_auth as text) like ?  or cast(mod_auth as text) like ?"
						+ " or cast(foot_auth as text) like ? or cast(total as text) like ?)");
			}


			if (!sus_no.equals("")) {
				conditions.add("unit_sus = ?");
			}
			if (!appt_trade.equals("")) {
				conditions.add("appt = ?");
			}
			if (!rank.equals("")) {
				conditions.add("code = ?");
			}
			if (!we_pe_no.equals("")) {
				conditions.add("we_pe_no = ?");
			}
			if (!user_arm.equals("0")) {
				conditions.add("userarm_code = ?");
			}
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}


			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from( SELECT \r\n"
					+ "SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) as GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS LT_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS MAJ_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS BRIG,\r\n"
					+ "SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS COL,\r\n"
					+ "SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS LT_COL,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS MAJ,\r\n"
					+ "SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS CAPT,\r\n"
					+ "SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS LT,\r\n"
					+ "SUM(CASE WHEN rank = 'FD MARSHAL' THEN total ELSE 0 END) AS FD_MARSHAL,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ "	SUM(total)Total FROM ue_pers_for_zoho_new_report  " +  searchValue.toString() +") app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag, searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);
				flag += 1;
				stmt.setString(flag,searchParam);

				flag += 1;
				stmt.setString(flag, searchParam);


			}

			if(!sus_no.equals("")){
				flag += 1;
				stmt.setString(flag, sus_no);
			}
			if(!appt_trade.equals("")){
				flag += 1;
				stmt.setString(flag, appt_trade);
			}
			if(!rank.equals("")){
				flag += 1;
				stmt.setString(flag, rank);
			}
			if(!we_pe_no.equals("")){
				flag += 1;
				stmt.setString(flag, we_pe_no);
			}
			if(!user_arm.equals("0")){
				flag += 1;
				stmt.setString(flag, user_arm);
			}
			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> detWiseStrReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT \r\n"
					+ "SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) AS GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS LT_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS MAJ_GEN,\r\n"
					+ "SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS BRIG,\r\n"
					+ "SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS COL,\r\n"
					+ "SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS LT_COL,\r\n"
					+ "SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS MAJ,\r\n"
					+ "SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS CAPT,\r\n"
					+ "SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS LT,\r\n"
					+ "SUM(CASE WHEN rank = 'FD MARSHAL' THEN total ELSE 0 END) AS FD_MARSHAL,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ "	SUM(total)Total FROM ue_pers_for_zoho_new_report  "  +  searchValue.toString() ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add("");//0
				list.add(rs.getString("GEN"));//0
				list.add(rs.getString("LT_GEN"));//1
				list.add(rs.getString("MAJ_GEN")); //2
				list.add(rs.getString("BRIG"));//3
				list.add(rs.getString("COL"));//4
				list.add(rs.getString("LT_COL"));//5
				list.add(rs.getString("MAJ"));//6
				list.add(rs.getString("CAPT"));//7
				list.add(rs.getString("LT"));//8
				list.add(rs.getString("MAJ_GEN")); //9
				list.add(rs.getString("FD_MARSHAL"));//10
				list.add(rs.getString("jco"));//11
				list.add(rs.getString("or"));//12
				list.add(rs.getString("non_civ_gaz"));//13
				list.add(rs.getString("Total"));//14
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

	@Override
	public List<Map<String, Object>> dtlsOfAllUnitsReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}



			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="select  arm_desc,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'JCO' THEN total ELSE 0 END) as jco,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OR'  THEN total ELSE 0 END) as or,\r\n"
					+ "count(distinct unit_name) as unit_name from ue_pers_for_zoho_new_report \r\n" +  searchValue.toString()
					+ " group by  arm_desc	limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long dtlsOfAllUnitsReportcount(String search, String orderColunm, String orderType, HttpSession sessionUserId) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(select  arm_desc,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'JCO' THEN total ELSE 0 END) as jco,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OR'  THEN total ELSE 0 END) as or,\r\n"
					+ "count(distinct unit_name) as unit_name from ue_pers_for_zoho_new_report "+  searchValue.toString()
					+ "group by  arm_desc ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> dtlsOfAllUnitsReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="select  arm_desc,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OFFICER' THEN total ELSE 0 END) as offr,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'JCO' THEN total ELSE 0 END) as jco,\r\n"
					+ "sum(CASE WHEN  rankcatnew = 'OR'  THEN total ELSE 0 END) as or,,\r\n"
					+ "count(distinct unit_name) as unit_name from ue_pers_for_zoho_new_report "  +  searchValue.toString()+" group by  arm_desc ";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("arm_desc"));//0
				list.add(rs.getString("offr"));//1
				list.add(rs.getString("jco"));//1
				list.add(rs.getString("or"));//1
				list.add(rs.getString("unit_name"));//1
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

	@Override
	public List<Map<String, Object>> designCapReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}



			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="select  DISTINCT unit_name,training_capacity from ue_pers_for_zoho_new_report where training_capacity is not null and training_capacity != 0 \r\n" +  searchValue.toString()
			+ "	limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long designCapReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from( select  DISTINCT unit_name,training_capacity from ue_pers_for_zoho_new_report where training_capacity is not null and training_capacity != 0" +  searchValue.toString() +") app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> designCapReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="select DISTINCT unit_name,training_capacity from ue_pers_for_zoho_new_report where training_capacity is not null and training_capacity != 0 "  +  searchValue.toString() ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));//0
				list.add(rs.getString("training_capacity"));//1
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
	@Override
	public List<Map<String, Object>> authStrOfManpowerReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}



			if (!conditions.isEmpty()) {
				searchValue.append(" AND ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT unit_name,\r\n"
					+ "					SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS officers,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "					SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "  			 		 'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ "  			 		SUM(total)Total FROM ue_pers_for_zoho_new_report where userarm = 'CAT-A' \r\n" +  searchValue.toString()
					+ "	group by unit_name limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfManpowerReportcount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId) {



		String SearchValue ="";
		int total = 0;
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String q = null;
		Connection conn = null;




		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from( SELECT unit_name,\r\n"
					+ "					SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS officers,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "					SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "  			 		 'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ "  			 		SUM(total)Total FROM ue_pers_for_zoho_new_report where userarm = 'CAT-A' " +  searchValue.toString() +" group by unit_name) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfManpowerReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT unit_name,\r\n"
					+ "					SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS officers,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat = 'OR'  THEN total ELSE 0 END) AS or,\r\n"
					+ "					SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' ) THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "  			 		SUM(CASE WHEN rank_cat in( 'Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
					+ "  			 		 'Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV') THEN total ELSE 0 END) AS non_civ_gaz,\r\n"
					+ "  			 		SUM(total)Total FROM ue_pers_for_zoho_new_report where userarm = 'CAT-A' "  +  searchValue.toString()
					+ "						group by unit_name";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("unit_name"));//0
				list.add(rs.getString("officers"));//1
				list.add(rs.getString("jco"));//1
				list.add(rs.getString("or"));//1
				list.add(rs.getString("civ_gaz"));//1
				list.add(rs.getString("non_civ_gaz"));//1
				list.add(rs.getString("total"));//1
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

	@Override
	public List<Map<String, Object>> authStrOfClrkInIAReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT unit_name,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS combat_ants,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "			SUM(total) AS total  FROM ue_pers_for_zoho_new_report \r\n" +  searchValue.toString()
					+ "	group by unit_name limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfClrkInIAReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from( SELECT unit_name,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS combat_ants,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "			SUM(total) AS total  FROM ue_pers_for_zoho_new_report " +  searchValue.toString() +" group by unit_name) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfClrkInIAReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT unit_name,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS combat_ants,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "			SUM(total) AS total  FROM ue_pers_for_zoho_new_report  "  +  searchValue.toString()
					+ "						group by unit_name";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("unit_name"));//1
				list.add(rs.getString("combat_ants"));//1
				list.add(rs.getString("civ"));//2
				list.add(rs.getString("total"));//3
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

	@Override
	public List<Map<String, Object>> authStrOfManpwerInIAReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT arm_desc,parentarm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS total,\r\n"
					+ "			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "			SUM(CASE WHEN rank_cat =  'NCsUE' THEN total ELSE 0 END) AS ncsu \r\n,"
					+ "			SUM(total) AS gtotal  FROM ue_pers_for_zoho_new_report \r\n" +  searchValue.toString()
					+ "	 group by arm_desc,parentarm order by arm_desc limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfManpwerInIAReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT arm_desc,parentarm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS total,\r\n"
					+ "			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "			SUM(CASE WHEN rank_cat =  'NCsUE' THEN total ELSE 0 END) AS ncsu,\r\n"		
					+ "			SUM(total) AS gtotal  FROM ue_pers_for_zoho_new_report " +  searchValue.toString() +"  group by arm_desc,parentarm order by arm_desc) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfManpwerInIAReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT arm_desc,parentarm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') THEN total ELSE 0 END) AS total,\r\n"
					+ "			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "			SUM(CASE WHEN rank_cat =  'NCsUE' THEN total ELSE 0 END) AS ncsu,\r\n"
					+ "			SUM(total) AS gtotal  FROM ue_pers_for_zoho_new_report"  +  searchValue.toString()
					+ "			group by arm_desc,parentarm order by arm_desc";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("parentarm"));//1
				list.add(rs.getString("officer"));//2
				list.add(rs.getString("jco"));//3
				list.add(rs.getString("or"));//1
				list.add(rs.getString("total"));//2
				list.add(rs.getString("civ_gaz"));//3
				list.add(rs.getString("civ_ng_ni"));//1
				list.add(rs.getString("civ_ng_indu"));//2
				list.add("");//2
				list.add(rs.getString("gtotal"));//3
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
	
	@Override
	public List<Map<String, Object>> formationWiseStrInIAReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct comd_name,corps_name,div_name,bde_name,unit_name,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "					 			SUM(total) AS total  FROM ue_pers_for_zoho_new_report  \r\n" +  searchValue.toString()
					+ "	 							group by comd_name,corps_name,div_name,bde_name,unit_name \r\n"
					+ "						  		order by comd_name,corps_name,div_name,bde_name limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long formationWiseStrInIAReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT distinct comd_name,corps_name,div_name,bde_name,unit_name,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "					 			SUM(total) AS total  FROM ue_pers_for_zoho_new_report  " +  searchValue.toString() 
					+ "  							group by comd_name,corps_name,div_name,bde_name,unit_name \r\n"
					+ "						  		order by comd_name,corps_name,div_name,bde_name) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> formationWiseStrInIAReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct comd_name,corps_name,div_name,bde_name,unit_name,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "					 			SUM(total) AS total  FROM ue_pers_for_zoho_new_report "  +  searchValue.toString()
					+ "								group by comd_name,corps_name,div_name,bde_name,unit_name \r\n"
					+ "						  		order by comd_name,corps_name,div_name,bde_name";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("comd_name"));//1
				list.add(rs.getString("corps_name"));//1
				list.add(rs.getString("div_name"));//3
				list.add(rs.getString("bde_name"));//1
				list.add(rs.getString("unit_name"));//2
				list.add(rs.getString("officer"));//2
				list.add(rs.getString("jco"));//3
				list.add(rs.getString("or"));//1
				list.add(rs.getString("civ"));//2
				list.add(rs.getString("total"));//2
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
	
	@Override
	public List<Map<String, Object>> authStrOfOffrByRkReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct parentarm,\r\n"
					+ "								SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) AS gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS lt_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS maj_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS brig,\r\n"
					+ "								SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL/COL' THEN total ELSE 0 END) AS lt_col_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS lt_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS maj,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS capt,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS lt,\r\n"
					+ "					 	SUM(CASE WHEN rank_cat = 'OFFICER' and rank in ('GEN','LT GEN','MAJ GEN','BRIG','COL','LT COL/COL','LT COL','MAJ','CAPT','LT') THEN total ELSE 0 END) AS total\r\n"
					+ "								FROM ue_pers_for_zoho_new_report \r\n" +  searchValue.toString() + " group by parentarm  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfOffrByRkReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT distinct parentarm,\r\n"
					+ "								SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) AS gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS lt_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS maj_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS brig,\r\n"
					+ "								SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL/COL' THEN total ELSE 0 END) AS lt_col_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS lt_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS maj,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS capt,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS lt,\r\n"
					+ "					 	SUM(CASE WHEN rank_cat = 'OFFICER' and rank in ('GEN','LT GEN','MAJ GEN','BRIG','COL','LT COL/COL','LT COL','MAJ','CAPT','LT') THEN total ELSE 0 END) AS total\r\n"
					+ "								FROM ue_pers_for_zoho_new_report " +  searchValue.toString()+" group by parentarm ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfOffrByRkReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct parentarm,\r\n"
					+ "								SUM(CASE WHEN rank = 'GEN' THEN total ELSE 0 END) AS gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT GEN' THEN total ELSE 0 END) AS lt_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ GEN' THEN total ELSE 0 END) AS maj_gen,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'BRIG' THEN total ELSE 0 END) AS brig,\r\n"
					+ "								SUM(CASE WHEN rank = 'COL' THEN total ELSE 0 END) AS col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL/COL' THEN total ELSE 0 END) AS lt_col_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT COL' THEN total ELSE 0 END) AS lt_col,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'MAJ' THEN total ELSE 0 END) AS maj,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'CAPT' THEN total ELSE 0 END) AS capt,\r\n"
					+ "					 			SUM(CASE WHEN rank = 'LT' THEN total ELSE 0 END) AS lt,\r\n"
					+ "					 	SUM(CASE WHEN rank_cat = 'OFFICER' and rank in ('GEN','LT GEN','MAJ GEN','BRIG','COL','LT COL/COL','LT COL','MAJ','CAPT','LT') THEN total ELSE 0 END) AS total\r\n"
					+ "								FROM ue_pers_for_zoho_new_report "  +  searchValue.toString() +" group by parentarm ";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("parentarm"));//1
				list.add(rs.getString("gen"));//1
				list.add(rs.getString("lt_gen"));//3
				list.add(rs.getString("maj_gen"));//1
				list.add(rs.getString("brig"));//2
				list.add(rs.getString("col"));//2
				list.add(rs.getString("lt_col_col"));//3
				list.add(rs.getString("lt_col"));//1
				list.add(rs.getString("maj"));//2
				list.add(rs.getString("capt"));//2
				list.add(rs.getString("lt"));//2
				list.add(rs.getString("total"));//2
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

	@Override
	public List<Map<String, Object>> authStrInIAReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,\r\n"
					+ "								count(unit_name) as no_of_unit,cat_pers,parentarm,\r\n"
					+ "								SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS offr,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "								SUM(total) AS total FROM ue_pers_for_zoho_new_report\r\n" +  searchValue.toString() 
					+ " group by arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,cat_pers,parentarm order by arm_desc   limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrInIAReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(	SELECT distinct arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,\r\n"
					+ "								count(unit_name) as no_of_unit,cat_pers,parentarm,\r\n"
					+ "								SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS offr,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "								SUM(total) AS total FROM ue_pers_for_zoho_new_report " +  searchValue.toString()
					+" group by arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,cat_pers,parentarm order by arm_desc  ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrInIAReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,\r\n"
					+ "								count(unit_name) as no_of_unit,cat_pers,parentarm,\r\n"
					+ "								SUM(CASE WHEN rank_cat = 'OFFICER' THEN total ELSE 0 END) AS offr,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat = 'CIV' THEN total ELSE 0 END) AS civ,\r\n"
					+ "								SUM(total) AS total FROM ue_pers_for_zoho_new_report"  +  searchValue.toString()
					+" group by arm_desc,we_pe_no,table_title,eff_frm_date,eff_to_date,cat_pers,parentarm order by arm_desc  ";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("arm_desc"));//1
				list.add(rs.getString("we_pe_no"));//1
				list.add(rs.getString("table_title"));//3
				list.add(rs.getString("eff_frm_date"));//1
				list.add(rs.getString("eff_to_date"));//2
				list.add(rs.getString("no_of_unit"));//1
				list.add(rs.getString("cat_pers"));//2
				list.add(rs.getString("parentarm"));//2
				list.add(rs.getString("offr"));//3
				list.add(rs.getString("jco"));//1
				list.add(rs.getString("or"));//2
				list.add(rs.getString("civ"));//2
				list.add(rs.getString("total"));//2
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

	@Override
	public List<Map<String, Object>> majMinUnitsReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct parentarm,unit_name,cat_pers,parentarm,unit_category,rank,appt FROM ue_pers_for_zoho_new_report \r\n" +  searchValue.toString() 
					+ "order by parentarm  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long majMinUnitsReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT distinct parentarm,unit_name,cat_pers,parentarm,unit_category,rank,appt FROM ue_pers_for_zoho_new_report  " +  searchValue.toString()
					+" order by parentarm ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> majMinUnitsReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct parentarm,unit_name,cat_pers,parentarm,unit_category,rank,appt FROM ue_pers_for_zoho_new_report "  +  searchValue.toString()
					+"  order by parentarm\r\n";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("parentarm"));//1
				list.add(rs.getString("unit_name"));//1
				list.add(rs.getString("unit_category"));//3
				list.add(rs.getString("rank"));//1
				list.add(rs.getString("appt"));//2
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
	
	@Override
	public List<Map<String, Object>> authStrOfTradeReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct appt,parentarm,we_pe_no, SUM(total) AS total FROM ue_pers_for_zoho_new_report  \r\n" +  searchValue.toString() 
					+ "group by appt,parentarm,we_pe_no order by appt  limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfTradeReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT distinct appt,parentarm,we_pe_no, SUM(total) AS total FROM ue_pers_for_zoho_new_report  " +  searchValue.toString()
					+" group by appt,parentarm,we_pe_no order by appt ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfTradeReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct appt,parentarm,we_pe_no, SUM(total) AS total FROM ue_pers_for_zoho_new_report  "  +  searchValue.toString()
					+"  group by appt,parentarm,we_pe_no order by appt\r\n";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("appt"));//1
				list.add(rs.getString("parentarm"));//1
				list.add(rs.getString("we_pe_no"));//3
				list.add(rs.getString("total"));//1
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
	
	@Override
	public List<Map<String, Object>> authStrinIADtlReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="	SELECT distinct unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,ct_part_i_ii,\r\n"
					+ "								SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "					 			SUM(total) AS total,training_capacity   FROM ue_pers_for_zoho_new_report \r\n" +  searchValue.toString() 
					+ "group by unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,training_capacity,ct_part_i_ii limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrinIADtlReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT distinct unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,ct_part_i_ii,\r\n"
					+ "								SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "					 			SUM(total) AS total,training_capacity   FROM ue_pers_for_zoho_new_report " +  searchValue.toString()
					+" group by unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,training_capacity,ct_part_i_ii ) app";
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("dddddddddddddDD: " + stmt);
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrinIADtlReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT distinct unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,ct_part_i_ii,\r\n"
					+ "								SUM(CASE WHEN rankcatnew = 'OFFICER' THEN total ELSE 0 END) AS officer,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'JCO' THEN total ELSE 0 END) AS jco,\r\n"
					+ "					 			SUM(CASE WHEN rankcatnew = 'OR' THEN total ELSE 0 END) AS or,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in( 'Civil Class I Gazetted','Civil Class II Gazetted' )  THEN total ELSE 0 END) AS civ_gaz,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Non-Industrial CL-II','Civilian Non-Gazzetted Non-Industrial CL-III','Civilian Non-Gazzetted Non-Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_ni,\r\n"
					+ "					 			SUM(CASE WHEN rank_cat in('Civilian Non-Gazzetted Industrial CL-II','Civilian Non-Gazzetted Industrial CL-III','Civilian Non-Gazzetted Industrial CL-IV')  THEN total ELSE 0 END) AS civ_ng_indu,\r\n"
					+ "					 			SUM(total) AS total,training_capacity   FROM ue_pers_for_zoho_new_report"  +  searchValue.toString()
					+"  group by unit_sus,unit_name,arm_desc, comd_name,corps_name,div_name,bde_name,we_pe_no,unit_category,type_of_force,training_capacity,ct_part_i_ii\r\n";

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("unit_sus"));//0
				list.add(rs.getString("unit_name"));//1
				list.add(rs.getString("arm_desc"));//2
				list.add(rs.getString("comd_name"));//3
				list.add(rs.getString("corps_name"));//4
				list.add(rs.getString("div_name"));//5
				list.add(rs.getString("bde_name")); //6
				list.add(rs.getString("we_pe_no"));//7
				list.add(rs.getString("ct_part_i_ii"));//8
				list.add(rs.getString("unit_category"));//9	
				list.add(rs.getString("type_of_force"));//10
				list.add(rs.getString("officer"));//11	
				list.add(rs.getString("jco"));//12	
				list.add(rs.getString("or"));//13
				list.add(rs.getString("civ_gaz"));//14	
				list.add(rs.getString("civ_ng_ni"));//15
				list.add(rs.getString("civ_ng_indu"));//16
				list.add("");//17
				list.add(rs.getString("total"));//18
				list.add(rs.getString("training_capacity"));//19
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
	
	@Override
	public List<Map<String, Object>> authStrNonRegReportTable(int startPage, int pageLength, String search, String orderColunm,
			String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT 'Assam Rif' as arm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3400' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3400' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3400' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('4','5') and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('6','7','8','9','10','11') and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3400' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "union all \r\n"
					+ "select 'DSC' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3300' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3300' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3300' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('4','5') and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('6','7','8','9','10','11') and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3300' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "			  union all \r\n"
					+ "select 'TA' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3700' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3700' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3700' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('4','5') and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank_cat_code in ('6','7','8','9','10','11') and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3700' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n" +  searchValue.toString()  +" limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);
			System.out.println("dddddddddddddDD: " + stmt);
			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrNonRegReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT 'Assam Rif' as arm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3400' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3400' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3400' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3400' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "union all \r\n"
					+ "select 'DSC' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3300' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3300' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3300' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3300' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "			  union all \r\n"
					+ "select 'TA' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3700' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3700' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3700' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3700' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  " +  searchValue.toString()+ " ) app" ;
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrNonRegReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT 'Assam Rif' as arm,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3400' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3400' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3400' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3400' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3400' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "union all \r\n"
					+ "select 'DSC' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3300' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3300' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3300' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3300' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3300' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report  \r\n"
					+ "			  union all \r\n"
					+ "select 'TA' as arm,\r\n"
					+ "SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='3700' THEN total ELSE 0 END) AS officer,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'JCO' and parentarm_code ='3700' THEN total ELSE 0 END) AS jco,\r\n"
					+ "			SUM(CASE WHEN rankcatnew = 'OR' and parentarm_code ='3700' THEN total ELSE 0 END) AS or,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-I' and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzi,\r\n"
					+ "			SUM(CASE WHEN rank = 'CIV GAZETTED OFFR CL-II' and parentarm_code ='3700' THEN total ELSE 0 END) AS civgzii,\r\n"
					+ "			SUM(CASE WHEN rankcatnew in ('OFFICER','JCO','OR') and parentarm_code ='3700' THEN total ELSE 0 END) AS total\r\n"
					+ "			  FROM ue_pers_for_zoho_new_report "  +  searchValue.toString() ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("arm"));//0
				list.add(rs.getString("officer"));//11	
				list.add(rs.getString("jco"));//12	
				list.add(rs.getString("or"));//13
				list.add(rs.getString("total"));//18
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
	
	
	@Override
	public List<Map<String, Object>> authStrOfMnsOffrReportTable(int startPage, int pageLength, String search, String orderColunm, String orderType, HttpSession sessionUserId) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry="";
		try{

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			// Add search condition if present
			if (!search.equals("")) {
				conditions.add("( upper(unit_sus) like ? )");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT 'MILITARY NURSING SERVICE [MNS]' as arm,\r\n"
					+ "	SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='4800' THEN total ELSE 0 END) AS officer\r\n"
					+ "	FROM ue_pers_for_zoho_new_report  \r\n" +  searchValue.toString()  +" limit "+pageL ;

			PreparedStatement stmt=conn.prepareStatement(qry);
			System.out.println("dddddddddddddDD: " + stmt);
			int flag = 0;
			if(!search.equals("")) {
				String searchParam = search.toUpperCase() + "%";

				flag += 1;
				stmt.setString(flag, searchParam);

			}

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
		return list;
	}

	@Override
	public long authStrOfMnsOffrReportcount(String search, String orderColunm, String orderType,HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();

			if (!search.equals("")) {

				conditions.add("( upper(unit_sus) like ? )");
			}

			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}
			q="select count(app.*) from(SELECT 'MILITARY NURSING SERVICE [MNS]' as arm,\r\n"
					+ "	SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='4800' THEN total ELSE 0 END) AS officer\r\n"
					+ "	FROM ue_pers_for_zoho_new_report   " +  searchValue.toString()+ " ) app" ;
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {

				String searchParam = search.toUpperCase() + "%";


				flag += 1;
				stmt.setString(flag, searchParam);

			}

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}
	@Override
	public ArrayList<ArrayList<String>> authStrOfMnsOffrReportExcel( String category_of_persn, String parent_arm)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry="";
		try{
			conn = dataSource.getConnection();

			StringBuilder searchValue = new StringBuilder();
			List<String> conditions = new ArrayList<>();
			if (!category_of_persn.equals("")) {
				if (category_of_persn.equals("1")) {
					conditions.add("cat_pers = 'ERE'");
				} else if (category_of_persn.equals("2")) {
					conditions.add(" cat_pers = 'Regimental'");
				}
			}

			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				conditions.add("parentarm_code = ?");
			}
			if (!conditions.isEmpty()) {
				searchValue.append(" WHERE ");
				searchValue.append(String.join(" AND ", conditions));
			}

			qry="SELECT 'MILITARY NURSING SERVICE [MNS]' as arm,\r\n"
					+ "	SUM(CASE WHEN rankcatnew = 'OFFICER' and parentarm_code ='4800' THEN total ELSE 0 END) AS officer\r\n"
					+ "	FROM ue_pers_for_zoho_new_report "  +  searchValue.toString() ;

			PreparedStatement stmt=conn.prepareStatement(qry);

			int flag = 0;
			System.out.println("dddd: "+ stmt);
			if (!parent_arm.equals("") && !parent_arm.equals("0")) {
				flag += 1;
				stmt.setString(flag, parent_arm);
			}
			ResultSet rs = stmt.executeQuery();
			int i =1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("arm"));//0
				list.add(rs.getString("officer"));//11	
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
}
