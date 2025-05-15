package com.dao.psg.Transaction;



import java.math.BigInteger;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.LinkedHashMap;

import java.util.List;

import java.util.Map;



import javax.sql.DataSource;



public class CensusAprovedDAOImpl implements CensusAprovedDAO{



	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;

    }

	

	

	public List<Map<String, Integer>> approveDetailOfcensusByid(int id) {

		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

		Connection conn = null;

		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			



			 q ="select (select count(id) from tb_psg_medical_category where status=0 and cen_id=p.id) as medCount,\r\n" + 
				 		"---(select count(id) from tb_psg_identity_card where status=0 and census_id=p.id) as idCount ,\r\n" + 
				 		"(select count(id) from tb_psg_census_address where status=0 and cen_id=p.id) as addCount ,\r\n" + 
				 		"(select count(id) from tb_psg_census_address where status=0 and spouse_country is not null and spouse_country!=0 and cen_id=p.id) as addSFCount ,\r\n" + 
				 		"(select count(id) from tb_psg_census_nok where status=0 and census_id=p.id) as nokCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_nok where status=0 and nok_relation=24 and  census_id=p.id) as nokrelationCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_cadet where status=0 and census_id=p.id) as preCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_ncc_exp where status=0 and cen_id=p.id) as nccCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_qualification where status=0 and cen_id=p.id) as qualiCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_language where status=0 and language !=0 and cen_id=p.id) as langCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_family_married where status=0 and marital_status=8 and cen_id=p.id) as marrCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_family_married where status=0 and marital_status=13 and cen_id=p.id) as seperateCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_family_married where status=0 and marital_status=9 and cen_id=p.id) as divcount,\r\n" + 
				 		"(select count(id) from tb_psg_census_family_married where status=0 and marital_status=11 and cen_id=p.id) as widowCount,\r\n" + 
				 		"(select count(id) from tb_psg_census_family_married where status=0 and marital_status=12 and cen_id=p.id) as widowerCount,\r\n" + 
				 		"p.marital_status,case when (p.father_name is not null  and p.mother_name is not null) then 1\r\n" + 
				 		"else 0\r\n" + 
				 		"end as parent_details from tb_psg_census_detail_p p where p.id=?";     

			

		    stmt=conn.prepareStatement(q);   	

		    stmt.setInt(1, id);

		    	

		    ResultSet rs = stmt.executeQuery();    

		    

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			     

			while (rs.next()) {

				Map<String, Integer> columns = new LinkedHashMap<String, Integer>();

		 	    for (int i = 1; i <= columnCount; i++) {

		 	    	columns.put(metaData.getColumnLabel(i), rs.getInt(i));

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
	
	
	@Override
	public String getcensusIdentityImagePath(String id) {
		String whr="";
		Connection conn = null;
		try {	
			
			if(id.equals("")) {
				id = "0";
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select identity_image,id from tb_psg_identity_card where id=? ";	
			stmt = conn.prepareStatement(query);
		
			stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("identity_image");             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
	public int getcensusid(BigInteger comm_id) {
	    Connection conn = null;
	    int censusId = 0; 
	    
	    try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT CASE WHEN id IS NULL THEN 0 ELSE id END AS census_id " +
	                     "FROM tb_psg_census_detail_p " +
	                     "WHERE comm_id = CAST(? AS BIGINT)";
	        
	        BigInteger id = BigInteger.ZERO;
	        if (comm_id != null) {
	            id = comm_id;
	        }
	        
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, String.valueOf(id));
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            censusId = rs.getInt("census_id");
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return censusId;
	}
	
	
	//////02012024
	
	public int checkdatapendingintable(BigInteger comm_id ,String columnNameToExclude) {

		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

		Connection conn = null;
		 int total = 0;
		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			



			 q ="SELECT\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_of_rank WHERE status=0 AND comm_id = " + comm_id + ") AS rank,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_name WHERE status=0 AND comm_id = " + comm_id + ") AS name,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_of_appointment WHERE status=0 AND comm_id = " + comm_id + ") AS appointment,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_identity_card WHERE status=0 AND comm_id = " + comm_id + ") AS identity_card,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_religion WHERE status=0 AND comm_id = " + comm_id + ") AS change_religion,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_family_married WHERE status=0 AND comm_id = " + comm_id + ") AS census_family_married,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_spouse_qualification WHERE status=0 AND comm_id = " + comm_id + ") AS census_spouse_qualification,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_children WHERE status=0 AND comm_id = " + comm_id + ") AS census_children,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_nok WHERE status=0 AND comm_id = " + comm_id + ") AS census_nok,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_address WHERE status=0 AND comm_id = " + comm_id + ") AS census_address,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_contact_cda_account_details WHERE status = 0) AS census_contact_cda_account_details,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_language WHERE status=0 AND comm_id = " + comm_id + ") AS census_language,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_qualification WHERE status=0 AND comm_id = " + comm_id + ") AS census_qualification,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_promo_exam WHERE status=0 AND comm_id = " + comm_id + ") AS census_promo_exam,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_army_course WHERE status=0 AND comm_id = " + comm_id + ") AS census_army_course,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_bpet WHERE status=0 AND comm_id = " + comm_id + ") AS census_bpet,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_firing_standard WHERE status=0 AND comm_id = " + comm_id + ") AS census_firing_standard,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_allergic_to_medicine WHERE status=0 AND comm_id = " + comm_id + ") AS allergic_to_medicine,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_medical_category WHERE status=0 AND comm_id = " + comm_id + ") AS medical_category,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_foreign_country WHERE status=0 AND comm_id = " + comm_id + ") AS census_foreign_country,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_awardsnmedal WHERE status=0 AND comm_id = " + comm_id + ") AS census_awardsnmedal,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_battle_physical_casuality WHERE status=0 AND comm_id = " + comm_id + ") AS census_battle_physical_casuality,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_discipline WHERE status=0 AND comm_id = " + comm_id + ") AS census_discipline,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_inter_arm_transfer WHERE status=0 AND comm_id = " + comm_id + ") AS inter_arm_transfer,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_of_comission WHERE status=0 AND comm_id = " + comm_id + ") AS change_of_comission,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_extension_of_comission WHERE status=0 AND comm_id = " + comm_id + ") AS extension_of_comission,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_census_secondment WHERE status=0 AND comm_id = " + comm_id + ") AS census_secondment,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_non_effective WHERE status=0 AND comm_id = " + comm_id + ") AS non_effective,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_deserter WHERE status=0 AND comm_id = " + comm_id + ") AS deserter,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_canteen_card_details1 WHERE status=0 AND comm_id = " + comm_id + ") AS canteen_card_details1";     

			

		    stmt=conn.prepareStatement(q); 
    
		

		    	

		    ResultSet rs = stmt.executeQuery();    

		    

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			     

			while (rs.next()) {

				Map<String, Integer> columns = new LinkedHashMap<String, Integer>();

		 	    for (int i = 1; i <= columnCount; i++) {

		 	    	String columnName = metaData.getColumnLabel(i);

		 	    	 if (!columnName.equalsIgnoreCase(columnNameToExclude)) {
		                    total += rs.getInt(i);
		                }

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

		 return total;

		}
	
	
	public int checkdatapendingintablecomm(BigInteger comm_id ,String columnNameToExclude) {

		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

		Connection conn = null;
		 int total = 0;
		String q="";

		String qry="";

		try{	  

			conn = dataSource.getConnection();					

			PreparedStatement stmt =null;

			



			 q ="SELECT\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_course WHERE status=0 AND comm_id = " + comm_id + ") AS comm_course,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_cadet WHERE status=0 AND comm_id = " + comm_id + ") AS comm_cadet,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_gender WHERE status=0 AND comm_id = " + comm_id + ") AS gender,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_commission WHERE status=0 AND comm_id = " + comm_id + ") AS comm_commission,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_birth WHERE status=0 AND comm_id = " + comm_id + ") AS comm_birth,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_inter_arm_transfer WHERE status=0 AND comm_id = " + comm_id + ") AS arm_transfer,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_update_comm_unit WHERE status=0 AND comm_id = " + comm_id + ") AS comm_unit,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_of_rank WHERE status=0 AND comm_id = " + comm_id + ") AS rank,\r\n"
			 		+ "  (SELECT COUNT(id) FROM tb_psg_change_of_comission WHERE status=0 AND comm_id = " + comm_id + ") AS commission";
			

		    stmt=conn.prepareStatement(q); 
    
		

		    	

		    ResultSet rs = stmt.executeQuery();    

		    

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();

			     

			while (rs.next()) {

				Map<String, Integer> columns = new LinkedHashMap<String, Integer>();

		 	    for (int i = 1; i <= columnCount; i++) {

		 	    	String columnName = metaData.getColumnLabel(i);

		 	    	 if (!columnName.equalsIgnoreCase(columnNameToExclude)) {
		                    total += rs.getInt(i);
		                }

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

		 return total;

		}
}

