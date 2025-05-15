package com.dao.tms;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.TB_PSG_UPLOAD;
import com.models.TB_TMS_IUT;
import com.models.TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER;
import com.models.TB_TMS_MCT_MASTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class IUTDAOImp implements IUTDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public List<Map<String, Object>> getMCtMain_Id(String type_of_veh,HttpSession session,String sus_no)
  	{		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="",whr="";
  			if(type_of_veh.equals("A")){
  				whr =" inner join tb_tms_census_retrn c on b.ba_no=c.ba_no and c.sus_no=? ";
  			}
  			if(type_of_veh.equals("B")) {
  				whr =" inner join tb_tms_mvcr_parta_dtl c on b.ba_no=c.ba_no and c.sus_no=? ";
  			}
  			if(type_of_veh.equals("C")) {
  				whr =" inner join tb_tms_emar_report c on b.ba_no=c.em_no and c.sus_no=? ";
  			}
  			
			qry ="select distinct s.mct_main_id,s.mct_nomen from tb_tms_mct_main_master s "
					+ " inner join tb_tms_banum_dirctry b on substr(cast(mct as varchar),1,4)=s.mct_main_id "
					+ whr
					+ "where s.type_of_veh = ?" ; 
		
 			PreparedStatement stmt=conn.prepareStatement(qry); 	
 			
 			int flag =1;
 			if(type_of_veh.equals("A") || type_of_veh.equals("B") || type_of_veh.equals("C")){
 				stmt.setString(flag, sus_no);
 				flag = flag+1;
 			}
 			stmt.setString(flag, type_of_veh);
 			
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
	
	
	public List<Map<String, Object>> getIUTBANoList(String source_sus_no,String type_of_veh,String mct_main,HttpSession session)
  	{		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
		
  		try{	  
  			conn = dataSource.getConnection();	
  			String qry="";
  			
  			
  			if(type_of_veh.equals("A")) {
  			
			qry ="select a.ba_no from tb_tms_census_retrn a \r\n" + 
					"left join tb_tms_banum_dirctry c on c.ba_no = a.ba_no\r\n" + 
					"where a.sus_no = ? and SUBSTRING(cast(c.mct as character varying), 1,4) = ?\r\n" + 
					"ORDER BY SUBSTRING(a.ba_no, 1,2) ,SUBSTRING(a.ba_no, 3,1) " ; 
			
  			}
  			
  			else if(type_of_veh.equals("B")) {
  				
  				qry ="select a.ba_no from tb_tms_mvcr_parta_dtl a \r\n" + 
  						"left join tb_tms_banum_dirctry c on c.ba_no = a.ba_no\r\n" + 
  						"where a.sus_no = ? and SUBSTRING(cast(c.mct as character varying), 1,4) = ?\r\n" + 
  						"ORDER BY SUBSTRING(a.ba_no, 1,2) ,SUBSTRING(a.ba_no, 3,1) " ; 
  	  			}
  			else if(type_of_veh.equals("C")) {
  				
  				qry ="select a.em_no as ba_no from tb_tms_emar_report a \r\n" + 
  						"left join tb_tms_banum_dirctry c on c.ba_no = a.em_no\r\n" + 
  						"where a.sus_no = ? and SUBSTRING(cast(c.mct as character varying), 1,4) = ?\r\n" + 
  						"ORDER BY SUBSTRING(a.em_no, 1,2) ,SUBSTRING(a.em_no, 3,1) " ; 
  			}
			
			
  			
 			PreparedStatement stmt=conn.prepareStatement(qry); 
 			stmt.setString(1, source_sus_no);
 			stmt.setString(2, mct_main);
 			
 			System.err.println("query:" + stmt);
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
	
	
	public ArrayList<ArrayList<String>> trackiutstatus(String username, String unit_sus_no, String unit_name,
			String cont_comd, String cont_corps, String cont_div, String cont_bde,String line_dte1,String roleSubAccess, String roleSusNo, String roleAccess) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry = "";
			String sql = "";
			if( !cont_comd.equals("")) {
				qry += " and( substring(b.form_code_control,1,1) like ? or  substring(c.form_code_control,1,1) like ?  ) ";	
		}
		if( !cont_corps.equals("0") &&  !cont_corps.equals("")) {
				qry += " and( substring(b.form_code_control,1,3) like ? or  substring(c.form_code_control,1,3) like ? )";	
		}
		if( !cont_div.equals("0") && !cont_div.equals("") ) {
				qry += " and( substring(b.form_code_control,1,6) like ? or  substring(c.form_code_control,1,6) like ?  )";	
		}
		if( !cont_bde.equals("0") && !cont_bde.equals("")) {
				qry += " and( b.form_code_control like ? or  c.form_code_control like ? )";	
		}
		
		if( !unit_name.equals("")) {
				qry += " and(b.unit_name like ? or c.unit_name like ? )";	
		}
		if( !line_dte1.equals("0") && !line_dte1.equals("")) {
			qry += "and ld.line_dte_sus  like ? ";	
		}
		 if(roleSubAccess.equals("Command")){
			 
				qry += " and ( substring(b.form_code_control,1,1) = (select SUBSTR(form_code_control,1,1) from tb_miso_orbat_unt_dtl \r\n"
						+ "where sus_no in(select sus_no from \r\n"
						+ "tb_miso_orbat_codesform where level_in_hierarchy='Command') and sus_no = ? and status_sus_no='Active') \r\n"
						+ "	or  substring(c.form_code_control,1,1) = (select SUBSTR(form_code_control,1,1) from tb_miso_orbat_unt_dtl \r\n"
						+ "where sus_no in(select sus_no from \r\n"
						+ "tb_miso_orbat_codesform where level_in_hierarchy='Command') and sus_no = ? and status_sus_no='Active') )";	
		}
			 
			 if(roleAccess.equals("Unit")){
				 
			qry += " and(b.sus_no like ? or c.sus_no like ? )";	
			}
			 
		
			sql = "select distinct a.id,a.created_by,a.iut_authority_no,a.vehical_type,a.main_id,b.unit_name,c.unit_name as unit,a.ba_no,case when a.status='0' then 'Pending' END as status,a.created_by from tb_tms_iut a\r\n"
					+ "left join tb_miso_orbat_unt_dtl b on b.sus_no = a.source_sus_no and UPPER(b.status_sus_no) IN ('INACTIVE','ACTIVE') \r\n"
					+ "left join tb_miso_orbat_unt_dtl c on c.sus_no = a.target_sus_no and UPPER(c.status_sus_no) IN ('INACTIVE','ACTIVE')\r\n"
					+ "left join tb_miso_orbat_line_dte ld on ld.line_dte_sus=b.sus_no\r\n"
					 + "where a.status = '0' and a.vehical_type ='C'"+qry+" ";
			stmt = conn.prepareStatement(sql);

			int flag = 0;
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
			if(!cont_corps.equals("0") &&  !cont_corps.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
				
			}
			if(!cont_corps.equals("0") &&  !cont_corps.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
				
			}
			if(!cont_div.equals("0") && !cont_div.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
				
			}
			if(!cont_div.equals("0") && !cont_div.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
				
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
				
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
				
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());
				
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());
				
			}
			if( !line_dte1.equals("0") && !line_dte1.equals("")) {
				flag += 1;
				stmt.setString(flag, line_dte1.toUpperCase());
			}
			 if(roleSubAccess.equals("Command")){
					flag += 1;
					stmt.setString(flag, roleSusNo.toUpperCase());
				}
				 if(roleSubAccess.equals("Command")){
						flag += 1;
						stmt.setString(flag, roleSusNo.toUpperCase());
					}
				 
				 if(roleAccess.equals("Unit")){
					 	flag += 1;
						stmt.setString(flag, roleSusNo.toUpperCase());
						}
				 if(roleAccess.equals("Unit")){
					 	flag += 1;
						stmt.setString(flag, roleSusNo.toUpperCase());
						}
			ResultSet rs = stmt.executeQuery();
			System.err.println("TrackStatus Query->" +stmt);
			while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));//0
					list.add(rs.getString("iut_authority_no"));//1
					list.add(rs.getString("vehical_type"));//2
					list.add(rs.getString("main_id"));//3
					list.add(rs.getString("unit_name"));//4
					list.add(rs.getString("unit"));//5
					list.add(rs.getString("ba_no"));//6
					list.add(rs.getString("status"));//7
					list.add(rs.getString("created_by"));//8
					//list.add(rs.getString("userid"));
					String f = "";

					
					String update1 = "onclick=\"  if (confirm('Are You Sure you want to Approve?') ){approve_iut("
							+ rs.getString("id") + ",'" + rs.getString("vehical_type") + "')}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_approve'  " + update1 + " title='Approve Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Reject?') ){reject_iut("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_reject'  " + Delete1 + " title='Reject Data'></i>";

					if(username.equals(rs.getString("created_by"))) {
						
						f += updateButton;
						f += deleteButton;
					} 

					list.add(f);
					
					aList.add(list);

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
		return aList;
	}
	public ArrayList<ArrayList<String>> trackiutstatus_a_b(String username, String unit_sus_no, String unit_name,
			String cont_comd, String cont_corps, String cont_div, String cont_bde, String line_dte1,String roleSubAccess, String roleSusNo, String roleAccess) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String qry="";
			String sql = "";
			if( !cont_comd.equals("")) {
					qry += " and( substring(b.form_code_control,1,1) like ? or  substring(c.form_code_control,1,1) like ?  ) ";	
			}
			if( !cont_corps.equals("0") &&  !cont_corps.equals("")) {
					qry += " and( substring(b.form_code_control,1,3) like ? or  substring(c.form_code_control,1,3) like ? )";	
			}
			if( !cont_div.equals("0") && !cont_div.equals("") ) {
					qry += " and( substring(b.form_code_control,1,6) like ? or  substring(c.form_code_control,1,6) like ?  )";	
			}
			if( !cont_bde.equals("0") && !cont_bde.equals("")) {
					qry += " and( b.form_code_control like ? or  c.form_code_control like ? )";	
			}
			
			if( !unit_name.equals("")) {
					qry += " and(b.unit_name like ? or c.unit_name like ? )";	
			}
			if( !line_dte1.equals("0") && !line_dte1.equals("")) {
				qry += "and ld.line_dte_sus  like ? ";	
			}
			 if(roleSubAccess.equals("Command")){
				 
				qry += " and ( substring(b.form_code_control,1,1) = (select SUBSTR(form_code_control,1,1) from tb_miso_orbat_unt_dtl \r\n"
						+ "where sus_no in(select sus_no from \r\n"
						+ "tb_miso_orbat_codesform where level_in_hierarchy='Command') and sus_no = ? and status_sus_no='Active') \r\n"
						+ "	or  substring(c.form_code_control,1,1) = (select SUBSTR(form_code_control,1,1) from tb_miso_orbat_unt_dtl \r\n"
						+ "where sus_no in(select sus_no from \r\n"
						+ "tb_miso_orbat_codesform where level_in_hierarchy='Command') and sus_no = ? and status_sus_no='Active') )";	
		}
			 
			 if(roleAccess.equals("Unit")){
				 
			qry += " and(b.sus_no like ? or c.sus_no like ? )";	
			}
			 
			sql = "select distinct a.id,a.created_by,a.iut_authority_no,a.vehical_type,a.main_id,b.unit_name,c.unit_name as unit,a.ba_no,a.target_sus_no,case when a.status='0' then 'Pending' END as status,a.created_by from tb_tms_iut a\r\n"
					+ "left join tb_miso_orbat_unt_dtl b on b.sus_no = a.source_sus_no and UPPER(b.status_sus_no) IN ('INACTIVE','ACTIVE') \r\n"
					+ "left join tb_miso_orbat_unt_dtl c on c.sus_no = a.target_sus_no and UPPER(c.status_sus_no) IN ('INACTIVE','ACTIVE') \r\n"
					+ "left join tb_miso_orbat_line_dte ld on ld.line_dte_sus=b.sus_no\r\n"
					+ "where a.status = '0' and a.vehical_type != 'C' "+qry+"";
			stmt = conn.prepareStatement(sql);
			int flag = 0;
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
			if(!cont_comd.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_comd.toUpperCase()+"%");
				
			}
			if(!cont_corps.equals("0") &&  !cont_corps.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
				
			}
			if(!cont_corps.equals("0") &&  !cont_corps.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_corps.toUpperCase()+"%");
				
			}
			if(!cont_div.equals("0") && !cont_div.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
				
			}
			if(!cont_div.equals("0") && !cont_div.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_div.toUpperCase()+"%");
				
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
				
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")) {
				flag += 1;
				stmt.setString(flag, cont_bde.toUpperCase()+"%");
				
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());
				
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toUpperCase());
				
			}
			if( !line_dte1.equals("0") && !line_dte1.equals("")) {
				flag += 1;
				stmt.setString(flag, line_dte1.toUpperCase());
			}
			 if(roleSubAccess.equals("Command")){
				flag += 1;
				stmt.setString(flag, roleSusNo.toUpperCase());
			}
			 if(roleSubAccess.equals("Command")){
					flag += 1;
					stmt.setString(flag, roleSusNo.toUpperCase());
				}
			 
			 if(roleAccess.equals("Unit")){
				 	flag += 1;
					stmt.setString(flag, roleSusNo.toUpperCase());
					}
			 if(roleAccess.equals("Unit")){
				 	flag += 1;
					stmt.setString(flag, roleSusNo.toUpperCase());
					}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));
					list.add(rs.getString("iut_authority_no"));
					list.add(rs.getString("vehical_type"));
					list.add(rs.getString("main_id"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("unit"));
					list.add(rs.getString("ba_no"));
					list.add(rs.getString("status"));
					list.add(rs.getString("created_by"));
					
					
					String f = "";

					
					String update1 = "onclick=\"  if (confirm('Are You Sure you want to Approve?') ){approve_iut("
							+ rs.getString("id") + ",'" + rs.getString("vehical_type") + "')}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_approve'  " + update1 + " title='Approve Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Reject?') ){reject_iut("
							+ rs.getString("id") + ")}else{ return false;}\"";
					String deleteButton = "<i class='action_icons action_reject'  " + Delete1 + " title='Reject Data'></i>";

					if(username.equals(rs.getString("created_by"))) {
						
						f += updateButton;
						f += deleteButton;
					}

					list.add(f);
					list.add(rs.getString("target_sus_no"));
					aList.add(list);

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
		return aList;
	}
	public TB_TMS_IUT getsusnoByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 	Transaction tx = sessionHQL.beginTransaction();
	 	TB_TMS_IUT updateid = (TB_TMS_IUT) sessionHQL.get(TB_TMS_IUT.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();		
		return updateid;
	}
	
	public List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> getIutDocument(String ba_num,String target_sus_no ,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
		if (roleAccess.equals("Unit")) {
			q = session.createQuery("from TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER where ba_no=:ba_num and target_sus_no=:target_sus_no order by id desc");
			q.setParameter("ba_num", ba_num);
			q.setParameter("target_sus_no", target_sus_no);
		} else {
			q = session.createQuery("from TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER where ba_no=:ba_num and target_sus_no=:target_sus_no order by id desc");
			q.setParameter("ba_num", ba_num);
			q.setParameter("target_sus_no", target_sus_no);
		}

		@SuppressWarnings("unchecked")
		List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = (List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER>) q.list();
		

		tx.commit();
		session.close();
		if(list != null)
			return list;
		else
			return null;
	}



}