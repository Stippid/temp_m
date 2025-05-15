package com.dao.psg.update_census_data;





import java.math.BigInteger;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Date;

import java.util.LinkedHashMap;



import javax.sql.DataSource;



import org.hibernate.Query;

import org.hibernate.Session;

import org.hibernate.Transaction;



import com.models.psg.Master.TB_BLOOD_GROUP;

import com.models.psg.Transaction.TB_POSTING_IN_OUT;

import com.models.psg.update_census_data.TB_CHANGE_NAME;

import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;

import com.persistance.util.HibernateUtil;



import java.util.List;

import java.util.Locale;

import java.util.Map;



public class Search_UpdateOffDataDaoImpl implements Search_UpdateOffDataDao{



	

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;

    }

	




//------------- pranay 26.04
	
	public ArrayList<ArrayList<String>> Search_UpdateOffData(String personnel_no, String status, String rank,
			String unit_sus_no,

			String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		String qorc = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String co_status = "tp.status='1'";

			if (!roleSusNo.equals("")) {
				qry += " and tp.unit_sus_no = ?";
			}

			if (!unit_sus_no.equals("")) {

				qry += "  and orb.sus_no = ?";

			}

			if (!unit_name.equals("")) {

				qry += "  and orb.unit_name = ?";

			}

			if (!personnel_no.equals("")) {

				qry += "  and upper(tp.personnel_no) like ? ";

			}

			if (status.equals("0")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '0' ";

				co_status = "tp.status in ('1','5')";

			}

			if (status.equals("1")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '1' ";

				co_status = "tp.status in ('1','4','5')";

			}

			if (status.equals("3")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '3' ";

			}

			if (status.equals("4")) {

				qorc += ",update_ofr_cancel_status desc";

				qry += " and cl.status != '0' ";

				// if(roleType.equals("APP")) {

				// qry+=" and cl.update_ofr_cancel_status=1 ";

				// }

				if (icStatus.equals("1"))

					co_status = " tp.status='4' ";

				else

					co_status = " tp.status!='4' ";

			}

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

				// qry += " and r.id = ? ";

				qry += " and cast(r.id  as character varying) = ?";

			}

			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(cl.created_date as date) = cast(? as date)";
			}

			q = "select distinct \r\n" +

					"cl.id ,\r\n" +

					"tp.cadet_no,\r\n" +

					"tp.personnel_no,cl.update_ofr_cancel_status,\r\n" +

					"r.description as rank,\r\n" +

					"    CASE \r\n" + "    WHEN cl.first_name IS NULL THEN tp.name\r\n"
					+ "    ELSE cl.first_name || ' ' || cl.middle_name || ' ' || cl.last_name \r\n" + "END AS name," +

					"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" +

					"arm.arm_desc as parent_arm,\r\n" +

					"cl.status,\r\n" +

					"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date  \r\n" +

					"FROM tb_psg_census_detail_p cl \r\n" +

					"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id and  LEFT(personnel_no, 2) NOT IN ('NR', 'NS') and " + co_status + " \r\n" +

					"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank and r.status_active = 'Active'\r\n" +

					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" +

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'\r\n"
					+

					"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n"
					+ "left join logininformation l1 on l1.username =cl.created_by\r\n" + "where cl.id !=  0 " + qry

					+ " order by cl.modified_date desc" + qorc;

			stmt = conn.prepareStatement(q);

			if (!qry.equals(""))

			{

				int j = 1;

				if (!roleSusNo.equals("")) {

					stmt.setString(j, roleSusNo);

					j += 1;

				}

				if (!unit_sus_no.equals("")) {

					stmt.setString(j, unit_sus_no);

					j += 1;

				}

				if (!unit_name.equals("")) {

					stmt.setString(j, unit_name);

					j += 1;

				}

				if (!personnel_no.equals("")) {

					stmt.setString(j, personnel_no.toUpperCase() + "%");

					j += 1;

				}

				// if( !rank.equals("0")) {

				if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

					stmt.setString(j, rank);

					j += 1;

				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
					stmt.setString(j, cr_date);
					j += 1;

				}

			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("cadet_no"));

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("name"));

				list.add(rs.getString("date_of_birth"));

				list.add(rs.getString("parent_arm"));

				// list.add(rs.getString("rank"));

				// list.add(rs.getString("course_name"));

				String f = "";

				String f1 = "";

				String f2 = "";

				String f3 = "";
				String f4 = "";

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approve = "onclick=\"  {ViewData('" + rs.getString("comm_id") + "')}\"";

					f4 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3"))

				{

					// String View1 = "onclick=\" if (confirm('Are You Sure You Want to View This
					// Letter?') ){View1Data("+ rs.getString("id") +
					// ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

					// f1 = "<i class='fa fa-eye' " + View1 + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					if (roleType.equals("ALL")
							|| rs.getString("update_ofr_cancel_status").equals("1") && icStatus.equals("1")) {

					}

					else {

						String View1 = "onclick=\"  {ViewHistoryData(" + rs.getString("comm_id") + ")}\"";

						f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";

					}

					if (roleType.equals("ALL") || icStatus.equals("1")) {

						String View2 = "onclick=\" {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

						f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";

					}

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4"))

				{

					if (rs.getString("update_ofr_cancel_status").equals("1")) {

						String Approve = "onclick=\"  {ViewCancelHistoryData(" + rs.getString("comm_id") + ")}\"";

						f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";

					}

					// if(icStatus.equals("1")) {

					String View2 = "onclick=\"  {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

					f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

					// }

				}

				if (roleType.equals("ALL") || status.equals("1"))

				{

					String Approve = "onclick=\" AppViewData(" + rs.getString("comm_id") + ")\"";

					f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3")))

				{

					String Reject = "onclick=\" {Reject(" + rs.getString("comm_id") + ")}\"";

					f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("

							+ rs.getString("comm_id") + ")}else{ return false;}\"";

					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				}

				list.add(f); // 8

				list.add(f1); // 9

				list.add(f2); // 10

				list.add(f3); // 11

				list.add(f4); // 12

				alist.add(list);

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

		return alist;

	}


	public List<Map<String, Object>> View_UpadteOfficerDataByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			

			if(!comm_id.equals("")) {

				qry += " and cdp.comm_id=? ";

			}

	        q = "select pcl.id,pcl.personnel_no,pcl.name,r.description as rank,cr.date_of_rank,pcl.regiment,tc.comn_name,pcl.date_of_commission,pcl.date_of_seniority,app.appointment,app.date_of_appointment,\r\n" + 

	        		"m.unit_name,po.dt_of_tos,pcl.date_of_birth,gen.gender_name,rel.religion_name,nati.nationality_name,mol.mothertounge,\r\n" + 

	        		"PGP_SYM_DECRYPT(cdp.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,cdp.blood_group,hgt.centimeter as height,idc.id_card_no,idc.issue_dt,mari.marital_name,PGP_SYM_DECRYPT(cdaco.gmail ::bytea,current_setting('miso.version'))  as gmail,PGP_SYM_DECRYPT(cdaco.mobile_no ::bytea,current_setting('miso.version'))  as mobile_no,\r\n" + 

	        		"(select shape_value from tb_psg_medical_category where cdp.comm_id=comm_id and status=1 and shape='S') as s_shape,\r\n" + 

	        		"(select shape_value from tb_psg_medical_category where cdp.comm_id=comm_id and status=1 and shape='H') as h_shape,\r\n" + 

	        		"(select shape_value from tb_psg_medical_category where cdp.comm_id=comm_id and status=1 and shape='A') as a_shape,\r\n" + 

	        		"(select shape_value from tb_psg_medical_category where cdp.comm_id=comm_id and status=1 and shape='P') as p_shape,\r\n" + 

	        		"(select shape_value from tb_psg_medical_category where cdp.comm_id=comm_id and status=1 and shape='E') as e_shape,pcl.date_of_commission as medical\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 

	        		"inner join cue_tb_psg_rank_app_master r on r.id = pcl.rank and r.status_active = 'Active'\r\n" + 

	        		"inner join tb_psg_change_of_rank cr on cr.comm_id= pcl.id\r\n" + 

	        		"inner join tb_psg_mstr_type_of_commission tc on tc.id = pcl.type_of_comm_granted and tc.status = 'active' \r\n" + 

	        		"left join tb_psg_change_of_appointment app on app.comm_id = pcl.id \r\n" + 

	        		"inner join tb_miso_orbat_unt_dtl m on m.sus_no = pcl.unit_sus_no and m.status_sus_no='Active'\r\n" + 

	        		"left join tb_psg_posting_in_out po on po.census_id=pcl.id\r\n" + 

	        		"inner  join tb_psg_mstr_religion rel on rel.religion_id=cdp.religion and rel.status = 'active' \r\n" + 

	        		"inner join tb_psg_mstr_gender gen on gen.id=pcl.gender\r\n" + 

	        		"inner join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality and nati.status='active'\r\n" + 

	        		"inner join tb_psg_mothertounge mol on mol.id=cdp.mother_tongue and mol.status='active'\r\n" + 

	        		"inner join tb_psg_mstr_height hgt on hgt.height_id = cdp.height and hgt.status='active'\r\n" + 

	        		"inner join tb_psg_mstr_marital_status mari on mari.marital_id = cdp.marital_status and mari.status='active' \r\n" + 

	        		"left join tb_psg_census_contact_cda_account_details cdaco on cdaco.comm_id=pcl.id\r\n" + 

	        		"inner join tb_psg_identity_card idc on idc.comm_id = pcl.id\r\n" + 

	        		"where  cdp.status=1 " +qry ;

	      

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

		    if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> Spouce_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and ms.comm_id=? ";

			}

	        q="select ms.maiden_name,ms.marriage_date,ms.date_of_birth,ms.place_of_birth,PGP_SYM_DECRYPT(ms.adhar_number ::bytea,current_setting('miso.version'))  as adhar_number,ms.nationality,nati.nationality_name    \r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 

	        		"inner join tb_psg_mstr_nationality nati on nati.nationality_id=cdp.nationality and nati.status='active' \r\n" + 

	        		"inner join tb_psg_census_family_married ms on ms.comm_id =pcl.id where ms.status= 1" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> AWARD_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and awm.comm_id=? ";

			}

	        q="select awm.category_8,awm.date_of_award,awm.unit   \r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 

	        		"inner join tb_psg_census_awardsnmedal awm on awm.comm_id=pcl.id where awm.status= 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> AQ_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and cq.comm_id=? ";

			}

	        q="select cq.examination_pass,cq.passing_year,cq.div_class,cq.subject\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 

	        		"inner join tb_psg_census_qualification cq on cq.comm_id = pcl.id where cq.status= 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	

	public List<Map<String, Object>> Lan_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and cl.comm_id=? ";

			}

	        q="select cl.id,il.ind_language,ls.name,fl.foreign_language_name,cl.f_lang_prof\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_detail_p cdp on cdp.comm_id= pcl.id \r\n" + 

	        		"inner join tb_psg_census_language cl on cl.comm_id=pcl.id\r\n" + 

	        		"inner join tb_psg_mstr_indian_language il on il.id =  cl.language and il.status = 'active' \r\n" + 

	        		"inner join tb_psg_lang_std ls on ls.id = cl.lang_std and ls.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_foreign_language fl on fl.id= cl.foreign_language and fl.status = 'active' where cl.status= 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> F_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and fc.comm_id=? ";

			}

	        q="select fc.id,fc.purpose_visit,fc.from_dt,fc.to_dt,fc.period\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_foreign_country fc on fc.comm_id= pcl.id where fc.status= 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> B_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and bp.comm_id=? ";

			}

	        q="select bp.bpet_qe,bp.bpet_result,bp.year\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_bpet bp on bp.comm_id= pcl.id where bp.status = 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	

	public List<Map<String, Object>> FS_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and fs.comm_id=? ";

			}

	        q="select fs.firing_event_qe,fs.firing_grade,fs.year\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_firing_standard fs on fs.comm_id= pcl.id where fs.status = 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> BA_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and pc.comm_id=? ";

			}

	        q="select tdc.label as classification_of_casuality,pc.nature_of_casuality,pc.name_of_operation,\r\n" + 

	        		"pc.date_of_casuality,pc.cause_of_casuality ,pc.exact_place\r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_battle_physical_casuality pc on pc.comm_id= pcl.id "

	        		+ "inner join t_domain_value tdc on tdc.codevalue=pc.classification_of_casuality  and tdc.domainid='CLASSIFICATION_OF_CASUALITY'\r\n" 

	        		+ " where pc.status = 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> SC_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and sc.comm_id=? ";

			}

	        q="select sc.seconded_to,sc.secondment_effect \r\n" + 

	        		"from tb_psg_trans_proposed_comm_letter pcl \r\n" + 

	        		"inner join tb_psg_census_secondment sc on sc.comm_id= pcl.id where sc.status = 1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> ORM_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " and c.comm_id=? ";

			}

	        q="select  c.org_country,c.org_state,c.org_district,c.org_tehsil from tb_psg_census_detail_p c\r\n" + 

	        		"inner join tb_psg_trans_proposed_comm_letter pcl on pcl.id=c.comm_id where c.status=1 " +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> PM_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " where add.comm_id=? ";

			}

	        q="select co.name as country ,st.state_name,di.district_name,th.city_name,add.permanent_village,add.permanent_pin_code,"

	        		+ "add.permanent_near_railway_station,add.permanent_border_area from tb_psg_census_address add\r\n" +	        	

	        		"inner join tb_psg_trans_proposed_comm_letter pcl on pcl.id=add.comm_id\r\n" + 

	        		"inner join tb_psg_mstr_country co on co.id=add.permanent_country and co.status = 'active' \r\n" + 

	        		"inner join tb_psg_mstr_state st on st.state_id = add.permanent_state and st.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_district di on di.district_id = add.permanent_district and di.status='active' \r\n" + 

	        		"inner join tb_psg_mstr_city th on cast(th.city_id as character varying)=cast( add.permanent_tehsil as character varying) where add.status = 1 and th.status = 'active'" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> PS_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " where add.comm_id=? ";

			}

	        q="select co.name as country ,st.state_name,di.district_name,th.city_name,add.permanent_village,\r\n" + 

	        		"add.present_pin_code,add.present_near_railway_station,add.permanent_border_area\r\n" + 

	        		" from tb_psg_census_address add\r\n" + 

	        		"inner join tb_psg_trans_proposed_comm_letter pcl on pcl.id=add.comm_id\r\n" + 

	        		"inner join tb_psg_mstr_country co on co.id=add.present_country and co.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_state st on st.state_id = add.present_state and st.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_district di on di.district_id = add.present_district and di.status='active' \r\n" + 

	        		"inner join tb_psg_mstr_city th on th.city_id = add.present_tehsil and th.status = 'active'\r\n" + 

	        		" where add.status = 1" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> NOK_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " where nok.comm_id=? ";

			}

	        q="select nok.nok_name,rel.relation_name from tb_psg_census_nok nok\r\n" + 

	        		"inner join tb_psg_trans_proposed_comm_letter  pcl on pcl.id = nok.comm_id\r\n" + 

	        		"inner join tb_psg_mstr_relation rel on rel.id=nok.nok_relation and rel.status='active'\r\n" + 	        		

	        		" where nok.status = 1" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> NA_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " where nok.comm_id=? ";

			}

	        q="select co.name as country ,st.state_name,di.district_name,th.city_name,nok.nok_village,nok.nok_pin,nok.nok_near_railway_station,PGP_SYM_DECRYPT(nok.nok_mobile_no ::bytea,current_setting('miso.version'))  as nok_mobile_no\r\n" + 

	        		"from tb_psg_census_nok nok\r\n" + 

	        		"inner join tb_psg_trans_proposed_comm_letter  pcl on pcl.id = nok.comm_id\r\n" + 

	        		"inner join tb_psg_mstr_country co on co.id=nok.nok_country and co.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_state st on st.state_id = nok.nok_state and st.status = 'active'\r\n" + 

	        		"inner join tb_psg_mstr_district di on di.district_id = nok.nok_district and di.status='active'\r\n" + 

	        		"inner join tb_psg_mstr_city th on cast(th.city_id as character varying)=cast( nok.nok_tehsil as character varying) and th.status = 'active'\r\n" + 	        		        		

	        		" where nok.status = 1" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	public List<Map<String, Object>> FM_View_UpadteByid(int id,String comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			if(!comm_id.equals("")) {

				qry += " where ce.comm_id=? ";

			}

	        q="select ce.father_name,ce.father_dob,ce.mother_name,ce.mother_dob,fs.date_of_birth,fs.name\r\n" + 

	        		"from tb_psg_census_detail_p ce\r\n" + 

	        		"inner  join tb_psg_trans_proposed_comm_letter  pcl on pcl.id = ce.comm_id\r\n" + 

	        		"left join tb_psg_census_family_siblings fs on fs.comm_id=pcl.id\r\n" +       			        		        		

	        		" where ce.status = 1" +qry ;

	        		

		    stmt=conn.prepareStatement(q);   	

		    int j =1;

			

			

			if( !comm_id.equals("")) {

				stmt.setString(j, comm_id);

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

	

	

	public ArrayList<ArrayList<String>> GetCensusDataApprove(BigInteger comm_id)

	{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		

	Connection conn = null;

	String q="";

	

	try{	  

		

			conn = dataSource.getConnection();			 

			PreparedStatement stmt=null;

			//Updated on 23-06-2022 for non-effective data

				

			

			/*q = "select  distinct\r\n" + 

					"c.id,\r\n" + 

					"cd.id as census_id,\r\n" + 

					"cd.marital_status as marital_status,\r\n" + 

					"c.name,\r\n" + 

					"cd.father_name,\r\n" +

					"cd.father_dob,\r\n" +

					"cd.mother_name,\r\n" +

					"cd.mother_dob,\r\n" +

					"ra.description as rank,\r\n" + 

					"r.description as appt,\r\n" + 

					"a.date_of_appointment as date_appointment,\r\n" + 

					"(select post.dt_of_tos  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n" + 

					"(select post.unit_description  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n" + 

					"c.unit_sus_no,\r\n" + 

					"i.id_card_no,\r\n" + 

					"re.religion_name,\r\n" + 

					"p.arm_desc as parent_arm,\r\n" + 

					"c.date_of_birth,\r\n" + 

					"c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" + 

					"fv.unit_name as command,c.regiment,cd.update_ofr_status,c.type_of_comm_granted\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c \r\n" + 

					"inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\r\n" + 

					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" + 

					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n" + 

					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  "
		
					+ " and r.status_active = 'Active'\r\n" + 

					"left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" + 

					"left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n" + 

					"left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" + 

					"left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? ";*/
//change by manav
			q = "select  distinct\r\n" +

					"c.id,\r\n" +

					"cd.id as census_id,\r\n" +

					"cd.marital_status as marital_status,\r\n" +

					"CASE \r\n"
					+ "    WHEN cd.first_name IS NULL THEN c.name\r\n"
					+ "    ELSE CONCAT(cd.first_name, ' ', cd.middle_name, ' ', cd.last_name)\r\n"
					+ "END AS name,\r\n" +

					"cd.father_name,\r\n" +

					"cd.father_dob,\r\n" +

					"cd.mother_name,\r\n" +

					"cd.mother_dob,\r\n" +

					"ra.description as rank,\r\n" +

					"r.description as appt,\r\n" +

					"a.date_of_appointment as date_appointment,\r\n" +

					"(select post.dt_of_tos  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n"
					+

					"(select post.unit_description  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n"
					+

					"c.unit_sus_no,\r\n" +

					"i.id_card_no,\r\n" +

					"re.religion_name,\r\n" +

					"p.arm_desc as parent_arm,\r\n" +

					"c.date_of_birth,\r\n" +

					"c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" +

					"fv.unit_name as command,c.regiment,cd.update_ofr_status,c.type_of_comm_granted\r\n" +

					"from tb_psg_trans_proposed_comm_letter c \r\n" +

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n"
					+

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no='Active'\r\n"
					+

					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" +

					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+ "left join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n"
					+ "left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n" +

					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  "

					+ " and r.status_active = 'Active'\r\n" +

					"left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" +

					"left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n" +

					"left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" +

					"left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" +

					"where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? ";	


				

			stmt=conn.prepareStatement(q);

			

			stmt.setString(1,String.valueOf(comm_id));

			
			System.out.println("record data : \n"+ stmt);
	      ResultSet rs = stmt.executeQuery();   

	     

	      ResultSetMetaData metaData = rs.getMetaData();

	      int columnCount = metaData.getColumnCount();

	      

	      while (rs.next()) {

	    	  ArrayList<String> list = new ArrayList<String>();

	    	  	list.add(rs.getString("id"));

				list.add(rs.getString("census_id"));

				list.add(rs.getString("name"));				

				list.add(rs.getString("rank"));

				list.add(rs.getString("appt"));

				list.add(rs.getString("date_appointment"));

				list.add(rs.getString("dt_of_tos"));

				list.add(rs.getString("unit_sus_no"));

				

				list.add(rs.getString("id_card_no"));

				list.add(rs.getString("religion_name"));

				list.add(rs.getString("parent_arm"));

				list.add(rs.getString("date_of_birth"));//11

				list.add(rs.getString("date_of_commission"));

				list.add(rs.getString("marital_status"));//13

				list.add(rs.getString("rank_id"));//14

				list.add(rs.getString("appoint_id"));//15

				list.add(rs.getString("command"));//16

				list.add(rs.getString("regiment"));//17

				list.add(rs.getString("update_ofr_status"));//18

				list.add(rs.getString("father_name"));//19

				list.add(rs.getString("father_dob"));//20

				list.add(rs.getString("mother_name"));//21

				list.add(rs.getString("mother_dob"));//22

				list.add(rs.getString("post_unit_desc"));//23         
				
				list.add(rs.getString("type_of_comm_granted"));//24

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

	public List<Map<String, Object>> getLastPostInOut(BigInteger comm_id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			q = "select * from tb_psg_posting_in_out where cast(comm_id as character varying)=? and status not in ('-1','3')  order by id desc limit 2";




			stmt = conn.prepareStatement(q);

			stmt.setString(1,String.valueOf(comm_id));

			

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



public List<Map<String, Object>> getLastPostInnoti(BigInteger comm_id) {

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	Connection conn = null;

	String q = "";

	String qry = "";

	try {

		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		q = "	 select a.from_sus_no,a.to_sus_no,TO_CHAR(a.dt_of_tos,'DD-MON-YYYY') AS dt_of_tos,rk.description as rank ,\r\n" + 
				"	  b.personnel_no,b.name from tb_psg_posting_in_out a inner join  tb_psg_trans_proposed_comm_letter b \r\n" + 
				"	  on  a.comm_id=b.id\r\n" + 
				"	  inner join cue_tb_psg_rank_app_master rk ON rk.id = b.rank where cast(a.comm_id as character varying)=?   order by a.id desc limit 1";



		stmt = conn.prepareStatement(q);

		stmt.setString(1,String.valueOf(comm_id));

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



	
	public List<Map<String, Object>> getPostInOut1(int comm_id) {
	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	
		Connection conn = null;
	
		String q = "";
	
		String qry = "";
	
		
	
		try {
	
			conn = dataSource.getConnection();
	
			PreparedStatement stmt = null;
	
		
	
			//String flag = "Y";
	
			
	
		
	
			 
	
		q = "select * from tb_psg_posting_in_out where comm_id=?  order by id desc";
	
	
	
			stmt = conn.prepareStatement(q);
	
			stmt.setInt(1,comm_id);
	
		
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
	

	public   String setTenureDate(int id,int cur_id) {
        Connection conn = null;                               
        String q = "";                               
      
        Session sessionhql = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionhql.beginTransaction();
        String msg = "0";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
			conn = dataSource.getConnection();                                               
            PreparedStatement stmt = null;

            TB_POSTING_IN_OUT pin = (TB_POSTING_IN_OUT)sessionhql.get(TB_POSTING_IN_OUT.class, cur_id);

             Date sos=pin.getDt_of_sos();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");                                 
                  String tdate = dateFormat.format(sos);
                 
            // Calendar lastDate = Calendar.getInstance();
              //lastDate.setTime(sos);
            // lastDate.add(Calendar.MONTH, -1);
            // String tdate=lastDate.getActualMaximum(Calendar.DATE)+"/"+(lastDate.get(Calendar.MONTH) + 1)+"/"+ lastDate.get(Calendar.YEAR);
////bisag 180823 v2(by defult get date from server)
      q = "SELECT (date_trunc('month', ?::date) - interval '1 day')::date"; 
   
                    stmt = conn.prepareStatement(q);
    
                    stmt.setString(1,tdate);
                   
                    ResultSet rs = stmt.executeQuery();     
                    Date tempdate = null;
			 if (rs.next()) {
			         tempdate = rs.getDate(1);
			 }

	         String hql_n = "update TB_POSTING_IN_OUT set   tenure_date=:t_date "
	
	                                         + " where   id=:id";
	
	         Query query_n = sessionhql.createQuery(hql_n).setTimestamp("t_date", tempdate )
	
	                                         .setInteger("id", id);
	    
	         msg = query_n.executeUpdate() > 0 ? "1" : "0";
	
	         tx.commit();
	
	         }catch (Exception e) {
	
	                         // TODO: handle exception
	e.printStackTrace();
	                         tx.rollback();
	
	                         return "0";
	
	         }
	
       return msg;

}
	
	
	public  String posrting_setTenureDate(int id,int cur_id) {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();

		String msg = "0";

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		
		try {

			

			TB_POSTING_IN_OUT pin = (TB_POSTING_IN_OUT)sessionhql.get(TB_POSTING_IN_OUT.class, cur_id);

		     Date sos=pin.getDt_of_sos();

			 Calendar lastDate = Calendar.getInstance();

			 lastDate.setTime(sos);

			 lastDate.add(Calendar.MONTH, -1);

			 String tdate=lastDate.getActualMaximum(Calendar.DATE)+"/"+(lastDate.get(Calendar.MONTH) + 1)+"/"+ lastDate.get(Calendar.YEAR);

		      

		String hql_n = "update TB_POSTING_IN_OUT set  tenure_date=:t_date "

				+ " where  id=:id";

		Query query_n = sessionhql.createQuery(hql_n).setTimestamp("t_date", format.parse(tdate) )

				.setInteger("id", id);

		msg = query_n.executeUpdate() > 0 ? "1" : "0";

		tx.commit();

		}catch (Exception e) {

			// TODO: handle exception

			tx.rollback();

			return "0";

		}

		return msg;

	}


	public ArrayList<ArrayList<String>> GetServingStatus(BigInteger comm_id)

	{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		

	Connection conn = null;

	String q="";

	

	try{	  

		

			conn = dataSource.getConnection();			 

			PreparedStatement stmt=null;

			

				q="select c.id,c.personnel_no,"

						+ " case "+

						" when c.id in (select id from tb_psg_trans_proposed_comm_letter where status='5') then 'DESERTER' " + 

						" when c.id in (select id from tb_psg_trans_proposed_comm_letter where status='4') then 'NON EFFECTIVE' " +

						" when c.id in (select comm_id from tb_psg_re_employment where re_emp_select ='2' and status=1) then 'RE-EMPLOYED' \r\n" + 

						" when c.id in (select comm_id from tb_psg_re_employment where re_emp_select ='1' and status=1) then 'RE-CALL FROM RESERVE' \r\n" + 						

						" else 'SERVING' END\r\n" + 

						" from \r\n" + 

						" tb_psg_trans_proposed_comm_letter c \r\n" + 

						" inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and  cast(c.id  as character varying)=?" ;



			

				

			

			

			stmt=conn.prepareStatement(q);

			

			stmt.setString(1,String.valueOf(comm_id));

			

	      ResultSet rs = stmt.executeQuery();   

	    

	      ResultSetMetaData metaData = rs.getMetaData();

	      int columnCount = metaData.getColumnCount();

	      

	      while (rs.next()) {

	    	  ArrayList<String> list = new ArrayList<String>();

	    	  	list.add(rs.getString("id"));

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("case"));				

				

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

	

	public ArrayList<ArrayList<String>> getNon_effective_date(int comm_id,String hd_rank)

	{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		

	Connection conn = null;

	String q="";

	

	try{	  

		

			conn = dataSource.getConnection();			 

			PreparedStatement stmt=null;

			

			q = "select * from (select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY') AS DOB,\r\n" + 

					"r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN r.description = 'COAS' AND TO_CHAR(c.date_of_birth,'DD')= '01' THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '743 month')::date),LAST_DAY((a.date_of_appointment::timestamp  + interval '36 month')::date))\r\n" + 

					"WHEN r.description = 'COAS' AND TO_CHAR(c.date_of_birth,'DD') != '01' THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '744 month')),LAST_DAY((a.date_of_appointment::timestamp  + interval '36 month')::date))\r\n" + 

					"WHEN r.description = 'DG AFMS' AND TO_CHAR(c.date_of_birth,'DD')= '01' THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '743 month')::date),LAST_DAY((a.date_of_appointment::timestamp  + interval '36 month')::date))\r\n" + 

					"WHEN r.description = 'DG AFMS' AND TO_CHAR(c.date_of_birth,'DD')!= '01' THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '744 month')),LAST_DAY((a.date_of_appointment::timestamp  + interval '36 month')::date))\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA' and ra.code IN ('R000','R001') and r.description IN ('COAS','DG AFMS')\r\n" + 

					"\r\n" + 

					"UNION \r\n" + 

					"\r\n" + 

					"select c.id as id, c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code NOT IN ('R005','R111','R004','R003','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '623 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code NOT IN ('R005','R111','R004','R003','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '624 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code  IN ('R005','R111','R004') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '647 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code IN ('R005','R111','R004')THEN LAST_DAY((c.date_of_birth::timestamp  + interval '648 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '671 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '672 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '719 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '720 month')::date)\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active' \r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA' \r\n" + 

					"and SUBSTR(c.personnel_no,1,2)!='SC'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2)!='SL'\r\n" + 

					"and c.parent_arm IN ('0100','0200','0300','0400','0500','0600','0700','0800','0900')\r\n" + 

					"\r\n" + 

					"UNION\r\n" + 

					"\r\n" + 

					"select c.id as id, c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code NOT IN ('R005','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '647 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code NOT IN ('R005','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '648 month')::date) ---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '671 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '672 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '719 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '720 month')::date) END AS RETIRED_DATE\r\n" + 

					"FROM tb_psg_trans_proposed_comm_letter c \r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active' \r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2) != 'SC'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2) != 'SL'\r\n" + 

					"and c.parent_arm IN ('1000','1100','1200','1500')\r\n" + 

					"			  \r\n" + 

					"UNION\r\n" + 

					"\r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '719 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '720 month')::date)\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"left join tb_psg_census_secondment s on  c.id = s.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active' \r\n" + 

					"inner join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"where SUBSTR(c.personnel_no,1,2)!='SC'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2)!='SL'\r\n" + 

					"and (c.parent_arm = '1300' OR t.label = 'SURVEY OF INDIA')\r\n" + 

					"			   \r\n" + 

					"UNION\r\n" + 

					"\r\n" + 

					"select c.id as id, c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"			   r.description as appt, CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R001'  THEN LAST_DAY((c.date_of_birth::timestamp  + interval '719 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '720 month')::date)--END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code != 'R001'  THEN LAST_DAY((c.date_of_birth::timestamp  + interval '707 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code != 'R001' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '708 month')::date) END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"left join tb_psg_census_secondment s on  c.id = s.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2)!='SC'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2)!='SL'\r\n" + 

					"and t.label != 'SURVEY OF INDIA'			   \r\n" + 

					"\r\n" + 

					"UNION\r\n" + 

					"			   \r\n" + 

					"select c.id as id, c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code NOT IN ('R111','R004','R003','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '671 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code NOT IN ('R111','R004','R003','R002','R001','R000','R013')THEN LAST_DAY((c.date_of_birth::timestamp  + interval '672 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code  IN ('R111','R004') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code  IN ('R111','R004')THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '707 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '708 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '719 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R002' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '720 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R001' AND extract(year FROM now())- extract(year FROM (c.date_of_birth)) >=60 THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '731 month')::date),LAST_DAY((a.date_of_appointment::timestamp  + interval '24 month')::date))\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R001' AND extract(year FROM now())- extract(year FROM (c.date_of_birth)) >=60 THEN LEAST(LAST_DAY((c.date_of_birth::timestamp  + interval '732 month')::date),LAST_DAY((a.date_of_appointment::timestamp  + interval '24 month')::date))\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active' \r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"--left join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"where SUBSTR(c.personnel_no,1,2) NOT IN ('97','98')\r\n" + 

					"and c.parent_arm IN ('2000','2100','2200') and com.comn_name != 'TA'	\r\n" + 

					"			   \r\n" + 

					"UNION \r\n" + 

					"			   \r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"			   r.description as appt,CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code IN('R111','R004') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '683 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code IN('R111','R004') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '684 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code IN('R115','R005','R006','R007','R008') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '671 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code IN('R115','R005','R006','R007','R008') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '672 month')::date)END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"--left join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"where SUBSTR(c.personnel_no,1,2) NOT IN ('97','98')\r\n" + 

					"and c.parent_arm IN ('2000','2100','2200') and com.comn_name != 'TA'			   \r\n" + 

					"\r\n" + 

					" UNION\r\n" + 

					"			   \r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"			   r.description as appt,CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND r.code NOT IN ('R003','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '683 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND r.code NOT IN ('R003','R002','R001','R000','R013')THEN LAST_DAY((c.date_of_birth::timestamp  + interval '684 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND r.code = '60' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND r.code = '60' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND r.code = '70' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '707 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND r.code = '70' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '708 month')::date)\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"--left join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"where c.parent_arm IN  ('2300','1900','1700','1600','1400') and com.comn_name != 'TA'\r\n" + 

					" \r\n" + 

					"UNION\r\n" + 

					"		\r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"			   r.description as appt,CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '683 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '684 month')::date)\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"left join tb_psg_census_secondment s on  c.id = s.comm_id\r\n" + 

					"left join t_domain_value t on t.codevalue=cast(s.seconded_to as char)\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK'  and ra.status_active = 'Active' \r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA'\r\n" + 

					"and SUBSTR(c.personnel_no,1,2)='SC'\r\n" + 

					"and s.seconded_to IS NULL\r\n" + 

					"\r\n" + 

					"UNION \r\n" + 

					"\r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB,\r\n" + 

					"			   r.description as appt,CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code NOT IN ('R003','R002','R001','R000','R013') THEN LAST_DAY((c.date_of_birth::timestamp  + interval '683 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code NOT IN ('R003','R002','R001','R000','R013')THEN LAST_DAY((c.date_of_birth::timestamp  + interval '684 month')::date)---END AS RETIRED_DATE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '695 month')::date)\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' AND ra.code = 'R003' THEN LAST_DAY((c.date_of_birth::timestamp  + interval '696 month')::date)\r\n" + 

					"\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active' \r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA' and SUBSTR(c.personnel_no,1,2)= 'SL'\r\n" + 

					"\r\n" + 

					"UNION\r\n" + 

					"\r\n" + 

					"select c.id as id,c.personnel_no,c.name,ra.description as rank,p.arm_desc as parent_arm,c.unit_sus_no,TO_CHAR(c.date_of_birth,'DD-MM-YYYY')AS DOB, r.description as appt,\r\n" + 

					"CASE\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') = '01'  THEN ((c.date_of_birth::timestamp  + interval '695 month')::date)-1\r\n" + 

					"WHEN TO_CHAR(c.date_of_birth,'DD') != '01' THEN ((c.date_of_birth::timestamp  + interval '696 month')::date)-1\r\n" + 

					"END AS RETIRED_DATE\r\n" + 

					"from tb_psg_trans_proposed_comm_letter c\r\n" + 

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id\r\n" + 

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n" + 

					"inner join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" + 

					"inner join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'   and r.status='1' and r.status_active = 'Active'\r\n" + 

					"inner join tb_psg_mstr_type_of_commission com on  com.id = c.type_of_comm_granted and com.status = 'active'\r\n" + 

					"where com.comn_name != 'TA'\r\n" + 

					") a" + 

					" where id=? and rank=?"; 

					

					 

				
				

			

		

			stmt=conn.prepareStatement(q);

			

			stmt.setInt(1, comm_id);

			stmt.setString(2, hd_rank);

			

	      ResultSet rs = stmt.executeQuery();   

	   

	      ResultSetMetaData metaData = rs.getMetaData();

	      int columnCount = metaData.getColumnCount();

	      

	      while (rs.next()) {

	    	  ArrayList<String> list = new ArrayList<String>();

	    	  	list.add(rs.getString("personnel_no"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));				

				list.add(rs.getString("parent_arm"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("dob"));

				list.add(rs.getString("appt"));

				list.add(rs.getString("retired_date"));

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

	

	public ArrayList<ArrayList<String>> AppSearch_UpdateOffData(String personnel_no, String status, String rank,
			String unit_sus_no,

			String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		String qry = "";

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			String co_status = "tp.status in ('1','5')";

			if (!roleSusNo.equals("")) {

				qry += " and tp.unit_sus_no = ?";

			}

			if (!unit_sus_no.equals("")) {

				qry += "  and orb.sus_no = ?";

			}

			if (!unit_name.equals("")) {

				qry += "  and orb.unit_name = ?";

			}

			if (!personnel_no.equals("")) {

				qry += "  and upper(tp.personnel_no) like ? ";

			}

			if (status.equals("0")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '0' ";

				co_status = "tp.status in ('1','5')";

			}

			if (status.equals("1")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '1' ";

				co_status = "tp.status in ('1','4','5')";

			}

			if (status.equals("3")) {

				qry += " and cl.status = '1' and cl.update_ofr_status = '3' ";

			}

			if (status.equals("4")) {

				qry += " and cl.status != '0' ";

//	if(roleType.equals("APP")) {

//		qry+=" and cl.update_ofr_cancel_status=1 ";

//	}

				if (icStatus.equals("1"))

					co_status = " tp.status='4' ";

				else

					co_status = " tp.status!='4' ";

			}

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

				// qry += " and r.id = ? ";

				qry += " and cast(r.id  as character varying) = ?";

			}

			if (!cr_by.equals("")) {

				qry += "  and cast(l1.userid as character varying)= ? ";

			}

			if (!cr_date.equals("")) {
				qry += " and cast(cl.created_date as date) = cast(? as date)";
			}

			q = "select distinct \r\n" +

					"cl.id ,\r\n" +

					"tp.cadet_no,\r\n" +

					"tp.personnel_no,cl.update_ofr_cancel_status,\r\n" +

					"r.description as rank,\r\n" +

					"    CASE \r\n" + "    WHEN cl.first_name IS NULL THEN tp.name\r\n"
					+ "    ELSE cl.first_name || ' ' || cl.middle_name || ' ' || cl.last_name \r\n" + "END AS name," +

					"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" +

					"arm.arm_desc as parent_arm,\r\n" +

					"cl.status,\r\n" +

					"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date  \r\n" +

					"FROM tb_psg_census_detail_p cl \r\n" +

					"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id  and LEFT(personnel_no, 2) NOT IN ('NR', 'NS')and " + co_status + " \r\n" +

					"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank and r.status_active = 'Active'\r\n" +

					"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" +

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'\r\n"
					+

					"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" +

					"left join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n"
					+ "left join logininformation l1 on l1.username =cl.created_by\r\n" +

					"where cl.id !=  0 " + qry

					+ " order by cl.modified_date desc";

			stmt = conn.prepareStatement(q);

			if (!qry.equals(""))

			{

				int j = 1;

				if (!roleSusNo.equals("")) {

					stmt.setString(j, roleSusNo);

					j += 1;

				}

				if (!unit_sus_no.equals("")) {

					stmt.setString(j, unit_sus_no);

					j += 1;

				}

				if (!unit_name.equals("")) {

					stmt.setString(j, unit_name);

					j += 1;

				}

				if (!personnel_no.equals("")) {

					stmt.setString(j, personnel_no.toUpperCase() + "%");

					j += 1;

				}

				// if( !rank.equals("0")) {

				if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

					stmt.setString(j, rank);

					j += 1;

				}
				if (!cr_by.equals("")) {
					stmt.setString(j, cr_by);
					j += 1;

				}
				if (!cr_date.equals("")) {
					stmt.setString(j, cr_date);
					j += 1;

				}
			}

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("cadet_no"));

				list.add(rs.getString("personnel_no"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("name"));

				list.add(rs.getString("date_of_birth"));

				list.add(rs.getString("parent_arm"));

				// list.add(rs.getString("rank"));

				// list.add(rs.getString("course_name"));

				String f = "";

				String f1 = "";

				String f2 = "";

				String f3 = "";
				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

				{

					String Approve = "onclick=\" {ViewData('" + rs.getString("comm_id") + "')}\"";

					f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

//String View1 = "onclick=\"  if (confirm('Are You Sure You Want to View This Letter?') ){View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

//f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3"))

				{

//String View1 = "onclick=\"  if (confirm('Are You Sure You Want to View This Letter?') ){View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

//f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

				{

					String View1 = "onclick=\"  {ViewHistoryData(" + rs.getString("comm_id") + ")}\"";

					f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";

					if (icStatus.equals("1")) {

						String View2 = "onclick=\"  {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

						f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";

					}

				}

				if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4"))

				{

					if (rs.getString("update_ofr_cancel_status").equals("1")) {

						String Approve = "onclick=\" {ViewCancelHistoryData(" + rs.getString("comm_id") + ")}\"";

						f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";

					}

//if(icStatus.equals("1")) {

					String View2 = "onclick=\" {ViewHistoryInactiveData_FN('" + rs.getString("comm_id") + "')}\"";

					f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

//}

				}

				if (roleType.equals("ALL") || status.equals("1"))

				{

					String Approve = "onclick=\" AppViewData(" + rs.getString("comm_id") + ")\"";

					f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3")))

				{

					String Reject = "onclick=\"  {Reject(" + rs.getString("comm_id") + ")}\"";

					f2 = "<i class='action_icons action_update' " + Reject + " title='Edit Data'></i>";

				}

				if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0")))

				{

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("

							+ rs.getString("comm_id") + ")}else{ return false;}\"";

					f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

				}

				list.add(f); // 8

				list.add(f1); // 9

				list.add(f2); // 10

				list.add(f3); // 11

				alist.add(list);

			}

			rs.close();

			stmt.close();

			conn.close();

		} catch (SQLException e) {

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





public ArrayList<ArrayList<String>> GetCensusDataApprove_postin_out(BigInteger comm_id)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q = "";

		try {

			

			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			// Updated on 23-06-2022 for non-effective data

			q = "select  distinct\r\n" +

					"c.id,\r\n" +

					"cd.id as census_id,\r\n" +

					"cd.marital_status as marital_status,\r\n" +

					"c.name,\r\n" +

					"cd.father_name,\r\n" +

					"cd.father_dob,\r\n" +

					"cd.mother_name,\r\n" +

					"cd.mother_dob,\r\n" +

					"ra.description as rank,\r\n" +

					"r.description as appt,\r\n" +

					"a.date_of_appointment as date_appointment,\r\n" +

					"(select post.dt_of_tos  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id limit 1) as dt_of_tos,\r\n"
					+

					"(select post.unit_description  from tb_psg_posting_in_out post where cd.comm_id = post.comm_id  and post.to_sus_no = c.unit_sus_no and post.status='1' order by post.id desc  limit 1) as post_unit_desc,\r\n"
					+

					"c.unit_sus_no,\r\n" +

					"i.id_card_no,\r\n" +

					"re.religion_name,\r\n" +

					"p.arm_desc as parent_arm,\r\n" +

					"c.date_of_birth,\r\n" +

					"c.date_of_commission,ra.id as rank_id ,r.id as appoint_id,\r\n" +

					"fv.unit_name as command,c.regiment,cd.update_ofr_status,c.type_of_comm_granted\r\n" +

					"from tb_psg_trans_proposed_comm_letter c \r\n" +

					"inner join tb_psg_census_detail_p cd on c.id = cd.comm_id and cd.status ='1' \r\n" +

					"inner join cue_tb_psg_rank_app_master ra on  ra.id = c.rank and upper(ra.level_in_hierarchy) = 'RANK' and ra.status_active = 'Active'\r\n"
					+

					"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = c.unit_sus_no and status_sus_no in ('Active','Inactive')\r\n"
					+

					"inner join tb_miso_orbat_code l on orb.code = l.code and orb.code_type = l.code_type\r\n" +

					"inner join all_fmn_view fv  on orb.sus_no = c.unit_sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n"
					+

					"left join tb_psg_change_of_appointment a on c.id = a.comm_id and  a.status ='1'\r\n" +

					"left join cue_tb_psg_rank_app_master r on  r.id = a.appointment and upper(r.level_in_hierarchy) = 'APPOINTMENT'  "

					+ " and r.status_active = 'Active'\r\n" +

					"left join tb_psg_identity_card i on i.comm_id = c.id and i.status=1 \r\n" +

					"left join tb_psg_change_religion ret on ret.comm_id=cd.comm_id and ret.status=1\r\n" +

					"left join tb_psg_mstr_religion re on re.religion_id = ret.religion   \r\n" +

					"left join tb_miso_orbat_arm_code p on p.arm_code = c.parent_arm\r\n" +

					"where (c.status='1' or c.status='4' or c.status='5') and  cast(c.id as character varying)=? ";

		

			stmt = conn.prepareStatement(q);

			stmt.setString(1, String.valueOf(comm_id));

			ResultSet rs = stmt.executeQuery();

			

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("id"));

				list.add(rs.getString("census_id"));

				list.add(rs.getString("name"));

				list.add(rs.getString("rank"));

				list.add(rs.getString("appt"));

				list.add(rs.getString("date_appointment"));

				list.add(rs.getString("dt_of_tos"));

				list.add(rs.getString("unit_sus_no"));

				list.add(rs.getString("id_card_no"));

				list.add(rs.getString("religion_name"));

				list.add(rs.getString("parent_arm"));

				list.add(rs.getString("date_of_birth"));// 11

				list.add(rs.getString("date_of_commission"));

				list.add(rs.getString("marital_status"));// 13

				list.add(rs.getString("rank_id"));// 14

				list.add(rs.getString("appoint_id"));// 15

				list.add(rs.getString("command"));// 16

				list.add(rs.getString("regiment"));// 17

				list.add(rs.getString("update_ofr_status"));// 18

				list.add(rs.getString("father_name"));// 19

				list.add(rs.getString("father_dob"));// 20

				list.add(rs.getString("mother_name"));// 21

				list.add(rs.getString("mother_dob"));// 22

				list.add(rs.getString("post_unit_desc"));// 23

				list.add(rs.getString("type_of_comm_granted"));// 24

				alist.add(list);

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

		return alist;

	}


	
public List<Map<String, Object>> getunitdet(String from_sus) {

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	Connection conn = null;

	String q = "";

	String qry = "";

	try {

		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		q = "select * from  tb_miso_orbat_unt_dtl where sus_no=? and status_sus_no='Active' ";

		stmt = conn.prepareStatement(q);

		stmt.setString(1, from_sus);
		

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
public String setTenureDate_3008(int id, Date tos) {
	Connection conn = null;
	String q = "";

	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	String msg = "0";
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		

		Date sos = tos;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String tdate = dateFormat.format(sos);
		
		// Calendar lastDate = Calendar.getInstance();
		// lastDate.setTime(sos);
		// lastDate.add(Calendar.MONTH, -1);
		// String
		// tdate=lastDate.getActualMaximum(Calendar.DATE)+"/"+(lastDate.get(Calendar.MONTH)
		// + 1)+"/"+ lastDate.get(Calendar.YEAR);
		//// bisag 180823 v2(by defult get date from server)
		q = "SELECT (date_trunc('month', ?::date) - interval '1 day')::date";

		stmt = conn.prepareStatement(q);

		stmt.setString(1, tdate);

		ResultSet rs = stmt.executeQuery();
		Date tempdate = null;
		if (rs.next()) {
			tempdate = rs.getDate(1);
		}

		String hql_n = "update TB_POSTING_IN_OUT set   tenure_date=:t_date "

				+ " where   id=:id";

		Query query_n = sessionhql.createQuery(hql_n).setTimestamp("t_date", tempdate)

				.setInteger("id", id);
		
		msg = query_n.executeUpdate() > 0 ? "1" : "0";

		tx.commit();

	} catch (Exception e) {

		// TODO: handle exception
		e.printStackTrace();
		tx.rollback();

		return "0";

	}

	return msg;

}
public ArrayList<ArrayList<String>> GetpostinoutDataForTos(BigInteger comm_id)

{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

	Connection conn = null;

	String q = "";

	try {
		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		q = "SELECT p.id,\r\n" + "       b.personnel_no,\r\n" + "       b.name,\r\n"
				+ "       r.description AS rank,\r\n" + "       p.out_auth,\r\n" + "       p.comm_id,\r\n"
				+ "       p.out_auth_dt,\r\n" + "       p.to_sus_no AS unit_sus_no,\r\n" + "       m.unit_name,\r\n"
				+ "       p.dt_of_tos,\r\n" + "       p.from_sus_no,\r\n" + "       p.app_name,\r\n"
				+ "       p.t_status,\r\n" + "       ta.label AS t_status_lbl\r\n"
				+ "  FROM tb_psg_posting_in_out p\r\n" + " INNER JOIN tb_psg_trans_proposed_comm_letter b\r\n"
				+ "    ON p.comm_id = b.id\r\n" + " INNER JOIN cue_tb_psg_rank_app_master r\r\n"
				+ "    ON r.id = b.rank\r\n" + " INNER JOIN t_domain_value ta\r\n"
				+ "    ON ta.codevalue::int = p.t_status\r\n" + "   AND domainid = 'TASTATUS'\r\n"
				+ " INNER JOIN tb_miso_orbat_unt_dtl m\r\n" + "    ON m.sus_no = p.to_sus_no\r\n"
				+ "   AND m.status_sus_no = 'Active'\r\n" + " WHERE cast(p.comm_id as character varying)=?  and p.status=1 order by p.id desc limit 1";

		stmt = conn.prepareStatement(q);

		stmt.setString(1, String.valueOf(comm_id));

		ResultSet rs = stmt.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("id"));// 0

			list.add(rs.getString("personnel_no"));// 1

			list.add(rs.getString("name"));// 2

			list.add(rs.getString("rank"));// 3

			list.add(rs.getString("out_auth"));// 4

			list.add(rs.getString("unit_sus_no"));// 5

			list.add(rs.getString("unit_name"));// 6

			list.add(rs.getString("dt_of_tos"));// 7

			list.add(rs.getString("from_sus_no"));// 8

			list.add(rs.getString("app_name"));// 9

			list.add(rs.getString("t_status"));// 10

			list.add(rs.getString("t_status_lbl"));// 11

			alist.add(list);

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

	return alist;

}



public ArrayList<ArrayList<String>> GetpostinoutPrevioustDateofTos(BigInteger comm_id)
{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

	Connection conn = null;

	String q = "";

	try {
		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		q = "SELECT dt_of_tos,id\r\n"
				+ "  FROM tb_psg_posting_in_out\r\n"
				+ " WHERE id IN (\r\n"
				+ "        SELECT id\r\n"
				+ "          FROM tb_psg_posting_in_out\r\n"
				+ "         WHERE cast(comm_id as character varying)=? \r\n"
				+ "           AND status = 1\r\n"
				+ "         ORDER BY id DESC\r\n"
				+ "         LIMIT 2\r\n"
				+ "       )\r\n"
				+ " ORDER BY id ASC\r\n"
				+ " LIMIT 1;";

		stmt = conn.prepareStatement(q);

		stmt.setString(1, String.valueOf(comm_id));

		ResultSet rs = stmt.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("id"));// 0

			list.add(rs.getString("dt_of_tos"));// 1				

			alist.add(list);

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

	return alist;

}


public ArrayList<ArrayList<String>> Search_UpdateMnsOffData(String personnel_no, String status, String rank,
		String unit_sus_no,

		String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus) {

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = "";
	String qorc = "";

	try {

		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String co_status = "tp.status='1'";

		if (!roleSusNo.equals("")) {
			qry += " and tp.unit_sus_no = ?";
		}

		if (!unit_sus_no.equals("")) {

			qry += "  and orb.sus_no = ?";

		}

		if (!unit_name.equals("")) {

			qry += "  and orb.unit_name = ?";

		}

		if (!personnel_no.equals("")) {

			qry += "  and upper(tp.personnel_no) like ? ";

		}

		if (status.equals("0")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '0' ";

			co_status = "tp.status in ('1','5')";

		}

		if (status.equals("1")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '1' ";

			co_status = "tp.status in ('1','4','5')";

		}

		if (status.equals("3")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '3' ";

		}

		if (status.equals("4")) {

			qorc += ",update_ofr_cancel_status desc";

			qry += " and cl.status != '0' ";

			// if(roleType.equals("APP")) {

			// qry+=" and cl.update_ofr_cancel_status=1 ";

			// }

			if (icStatus.equals("1"))

				co_status = " tp.status='4' ";

			else

				co_status = " tp.status!='4' ";

		}

		if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

			// qry += " and r.id = ? ";

			qry += " and cast(r.id  as character varying) = ?";

		}

		if (!cr_by.equals("")) {

			qry += "  and cast(l1.userid as character varying)= ? ";

		}

		if (!cr_date.equals("")) {
			qry += " and cast(cl.created_date as date) = cast(? as date)";
		}

		q = "select distinct \r\n" +

				"cl.id ,\r\n" +

				"tp.cadet_no,\r\n" +

				"tp.personnel_no,cl.update_ofr_cancel_status,\r\n" +

				"r.description as rank,\r\n" +

				"    CASE \r\n" + "    WHEN cl.first_name IS NULL THEN tp.name\r\n"
				+ "    ELSE cl.first_name || ' ' || cl.middle_name || ' ' || cl.last_name \r\n" + "END AS name," +

				"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" +

				"arm.arm_desc as parent_arm,\r\n" +

				"cl.status,\r\n" +

				"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date  \r\n" +

				"FROM tb_psg_census_detail_p cl \r\n" +

				"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id and LEFT(tp.personnel_no, 2) IN ('NR', 'NS') and " + co_status + " \r\n" +

				"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank and r.status_active = 'Active'\r\n" +

				"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" +

				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'\r\n"
				+

				"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n"
				+ "left join logininformation l1 on l1.username =cl.created_by\r\n" + "where cl.id !=  0 " + qry

				+ " order by cl.modified_date desc" + qorc;

		stmt = conn.prepareStatement(q);

		if (!qry.equals(""))

		{

			int j = 1;

			if (!roleSusNo.equals("")) {

				stmt.setString(j, roleSusNo);

				j += 1;

			}

			if (!unit_sus_no.equals("")) {

				stmt.setString(j, unit_sus_no);

				j += 1;

			}

			if (!unit_name.equals("")) {

				stmt.setString(j, unit_name);

				j += 1;

			}

			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			// if( !rank.equals("0")) {

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

				stmt.setString(j, rank);

				j += 1;

			}
			if (!cr_by.equals("")) {
				stmt.setString(j, cr_by);
				j += 1;

			}
			if (!cr_date.equals("")) {
				stmt.setString(j, cr_date);
				j += 1;

			}

		}

		ResultSet rs = stmt.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("cadet_no"));

			list.add(rs.getString("personnel_no"));

			list.add(rs.getString("rank"));

			list.add(rs.getString("name"));

			list.add(rs.getString("date_of_birth"));

			list.add(rs.getString("parent_arm"));

			// list.add(rs.getString("rank"));

			// list.add(rs.getString("course_name"));

			String f = "";

			String f1 = "";

			String f2 = "";

			String f3 = "";
			String f4 = "";

			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

			{

				String Approve = "onclick=\"  {ViewData('" + rs.getString("comm_id") + "')}\"";

				f4 = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3"))

			{

				// String View1 = "onclick=\" if (confirm('Are You Sure You Want to View This
				// Letter?') ){View1Data("+ rs.getString("id") +
				// ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

				// f1 = "<i class='fa fa-eye' " + View1 + " title='View Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

			{

				if (roleType.equals("ALL")
						|| rs.getString("update_ofr_cancel_status").equals("1") && icStatus.equals("1")) {

				}

				else {

					String View1 = "onclick=\"  {ViewHistoryData(" + rs.getString("comm_id") + ")}\"";

					f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";

				}

				if (roleType.equals("ALL") || icStatus.equals("1")) {

					String View2 = "onclick=\" {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

					f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";

				}

			}

			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4"))

			{

				if (rs.getString("update_ofr_cancel_status").equals("1")) {

					String Approve = "onclick=\"  {ViewCancelHistoryData(" + rs.getString("comm_id") + ")}\"";

					f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";

				}

				// if(icStatus.equals("1")) {

				String View2 = "onclick=\"  {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

				f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

				// }

			}

			if (roleType.equals("ALL") || status.equals("1"))

			{

				String Approve = "onclick=\" AppViewData(" + rs.getString("comm_id") + ")\"";

				f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3")))

			{

				String Reject = "onclick=\" {Reject(" + rs.getString("comm_id") + ")}\"";

				f2 = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0")))

			{

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("

						+ rs.getString("comm_id") + ")}else{ return false;}\"";

				f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

			}

			list.add(f); // 8

			list.add(f1); // 9

			list.add(f2); // 10

			list.add(f3); // 11

			list.add(f4); // 12

			alist.add(list);

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

	return alist;

}


public ArrayList<ArrayList<String>> AppSearch_UpdateMnsOffData(String personnel_no, String status, String rank,
		String unit_sus_no,

		String unit_name, String cr_by, String cr_date, String roleSusNo, String roleType, String icStatus)

{

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

	Connection conn = null;

	String q = "";

	String qry = "";

	try {

		conn = dataSource.getConnection();

		PreparedStatement stmt = null;

		String co_status = "tp.status in ('1','5')";

		if (!roleSusNo.equals("")) {

			qry += " and tp.unit_sus_no = ?";

		}

		if (!unit_sus_no.equals("")) {

			qry += "  and orb.sus_no = ?";

		}

		if (!unit_name.equals("")) {

			qry += "  and orb.unit_name = ?";

		}

		if (!personnel_no.equals("")) {

			qry += "  and upper(tp.personnel_no) like ? ";

		}

		if (status.equals("0")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '0' ";

			co_status = "tp.status in ('1','5')";

		}

		if (status.equals("1")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '1' ";

			co_status = "tp.status in ('1','4','5')";

		}

		if (status.equals("3")) {

			qry += " and cl.status = '1' and cl.update_ofr_status = '3' ";

		}

		if (status.equals("4")) {

			qry += " and cl.status != '0' ";

//if(roleType.equals("APP")) {

//	qry+=" and cl.update_ofr_cancel_status=1 ";

//}

			if (icStatus.equals("1"))

				co_status = " tp.status='4' ";

			else

				co_status = " tp.status!='4' ";

		}

		if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

			// qry += " and r.id = ? ";

			qry += " and cast(r.id  as character varying) = ?";

		}

		if (!cr_by.equals("")) {

			qry += "  and cast(l1.userid as character varying)= ? ";

		}

		if (!cr_date.equals("")) {
			qry += " and cast(cl.created_date as date) = cast(? as date)";
		}

		q = "select distinct \r\n" +

				"cl.id ,\r\n" +

				"tp.cadet_no,\r\n" +

				"tp.personnel_no,cl.update_ofr_cancel_status,\r\n" +

				"r.description as rank,\r\n" +

				"    CASE \r\n" + "    WHEN cl.first_name IS NULL THEN tp.name\r\n"
				+ "    ELSE cl.first_name || ' ' || cl.middle_name || ' ' || cl.last_name \r\n" + "END AS name," +

				"ltrim(TO_CHAR(tp.date_of_birth  ,'DD-MON-YYYY'),'0') as date_of_birth,\r\n" +

				"arm.arm_desc as parent_arm,\r\n" +

				"cl.status,\r\n" +

				"mc.course_name,cl.comm_id,cl.marital_status,cl.modified_date  \r\n" +

				"FROM tb_psg_census_detail_p cl \r\n" +

				"inner join tb_psg_trans_proposed_comm_letter tp on cl.comm_id =tp.id  and LEFT(personnel_no, 2) IN ('NR', 'NS')and " + co_status + " \r\n" +

				"inner join cue_tb_psg_rank_app_master r on r.id = tp.rank and r.status_active = 'Active'\r\n" +

				"inner join tb_miso_orbat_arm_code  arm on arm.arm_code = tp.parent_arm\r\n" +

				"inner join tb_miso_orbat_unt_dtl orb on orb.sus_no = tp.unit_sus_no and status_sus_no='Active'\r\n"
				+

				"left join tb_psg_mstr_course mc on mc.id=tp.id \r\n" +

				"left join logininformation l on tp.unit_sus_no = l.user_sus_no\r\n"
				+ "left join logininformation l1 on l1.username =cl.created_by\r\n" +

				"where cl.id !=  0 " + qry

				+ " order by cl.modified_date desc";

		stmt = conn.prepareStatement(q);

		if (!qry.equals(""))

		{

			int j = 1;

			if (!roleSusNo.equals("")) {

				stmt.setString(j, roleSusNo);

				j += 1;

			}

			if (!unit_sus_no.equals("")) {

				stmt.setString(j, unit_sus_no);

				j += 1;

			}

			if (!unit_name.equals("")) {

				stmt.setString(j, unit_name);

				j += 1;

			}

			if (!personnel_no.equals("")) {

				stmt.setString(j, personnel_no.toUpperCase() + "%");

				j += 1;

			}

			// if( !rank.equals("0")) {

			if (!rank.equals("0") && rank != null && rank != "null" && rank != "") {

				stmt.setString(j, rank);

				j += 1;

			}
			if (!cr_by.equals("")) {
				stmt.setString(j, cr_by);
				j += 1;

			}
			if (!cr_date.equals("")) {
				stmt.setString(j, cr_date);
				j += 1;

			}
		}

		ResultSet rs = stmt.executeQuery();

		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("cadet_no"));

			list.add(rs.getString("personnel_no"));

			list.add(rs.getString("rank"));

			list.add(rs.getString("name"));

			list.add(rs.getString("date_of_birth"));

			list.add(rs.getString("parent_arm"));

			// list.add(rs.getString("rank"));

			// list.add(rs.getString("course_name"));

			String f = "";

			String f1 = "";

			String f2 = "";

			String f3 = "";
			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("0"))

			{

				String Approve = "onclick=\" {ViewData('" + rs.getString("comm_id") + "')}\"";

				f = "<i class='action_icons action_approve'  " + Approve + " title='Approve Data'></i>";

//String View1 = "onclick=\"  if (confirm('Are You Sure You Want to View This Letter?') ){View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

//f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("3"))

			{

//String View1 = "onclick=\"  if (confirm('Are You Sure You Want to View This Letter?') ){View1Data("+ rs.getString("id") + ",'"+rs.getString("comm_id")+"')}else{ return false;}\"";

//f1 = "<i class='fa fa-eye'  " + View1 + " title='View Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && status.equals("4"))

			{

				String View1 = "onclick=\"  {ViewHistoryData(" + rs.getString("comm_id") + ")}\"";

				f = "<i class='fa fa-eye'  " + View1 + " title='Cancel Data'></i>";

				if (icStatus.equals("1")) {

					String View2 = "onclick=\"  {ViewHistoryInactiveData_FN(" + rs.getString("comm_id") + ")}\"";

					f1 = "<i class='fa fa-list' " + View2 + " title='View History'></i>";

				}

			}

			if (roleType.equals("ALL") || roleType.equals("APP") && status.equals("4"))

			{

				if (rs.getString("update_ofr_cancel_status").equals("1")) {

					String Approve = "onclick=\" {ViewCancelHistoryData(" + rs.getString("comm_id") + ")}\"";

					f = "<i class='fa fa-eye'  " + Approve + " title='Approve Data'></i>";

				}

//if(icStatus.equals("1")) {

				String View2 = "onclick=\" {ViewHistoryInactiveData_FN('" + rs.getString("comm_id") + "')}\"";

				f1 = "<i class='fa fa-list'   " + View2 + " title='View History'></i>";

//}

			}

			if (roleType.equals("ALL") || status.equals("1"))

			{

				String Approve = "onclick=\" AppViewData(" + rs.getString("comm_id") + ")\"";

				f = "<i class='fa fa-eye'  " + Approve + " title='View Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("3")))

			{

				String Reject = "onclick=\"  {Reject(" + rs.getString("comm_id") + ")}\"";

				f2 = "<i class='action_icons action_update' " + Reject + " title='Edit Data'></i>";

			}

			if (roleType.equals("ALL") || roleType.equals("DEO") && (status.equals("0")))

			{

				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Letter?') ){editData("

						+ rs.getString("comm_id") + ")}else{ return false;}\"";

				f3 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

			}

			list.add(f); // 8

			list.add(f1); // 9

			list.add(f2); // 10

			list.add(f3); // 11

			alist.add(list);

		}

		rs.close();

		stmt.close();

		conn.close();

	} catch (SQLException e) {

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



