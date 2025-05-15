package com.dao.tms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class AnimalEquinesDAOImpl implements AnimalEquinesDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/// bisag 170822 V1 (Query Optimized)
	public ArrayList<ArrayList<String>> Equinesreportlist(String sus_no, String fdate, String tdate) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String qry1 = "";
			String sql = "";

		

			if (fdate != "" && tdate != "") {

				qry1 += " and cast(A.created_date as date) between cast(? as date) and cast(? as date) + 1 ";
			}

			if (fdate != "" && tdate == "") {

				qry1 += " and cast(A.created_date as date) >= cast(? as date)";
			}

			if (fdate == "" && tdate != "") {

				qry1 += " and cast(A.created_date as date) <= cast(? as date)";
			}

			sql = "select distinct type_of_animal,UE,UH,\r\n" + 
					"(cast(UH as Integer) - cast(UE as Integer)) as sur,\r\n" + 
					"(cast(UE as Integer) - cast(UH as Integer)) as defi,\r\n" + 
					"(cast(UH as Integer) + cast(UE as Integer)) as total \r\n" + 
					"FROM ( select m.type_of_animal,case when (b.ue_of_equins='0' OR B.ue_of_equins is null) then '0' else b.ue_of_equins END as \r\n" + 
					" UE,( select CAST(count(a.id)  as character varying) as UH  from \r\n" + 
					" tb_tms_animal_details_tbl a 			\r\n" + 
					" left join tb_tms_type_of_animal_master m on m.id=a.type_equines\r\n" + 
					" where a.anml_type = 'Army Equines' and a.sus_no = ? " +qry1 +" ) from tb_tms_animals_ue_master b \r\n" + 
					" left join tb_tms_type_of_animal_master m on m.id=b.type_equines\r\n" + 
					" where b.anml_type = 'Army Equines' and b.sus_no = ? ) D where UH !='0' OR UE != '0'";
			
			stmt = conn.prepareStatement(sql);

			
			
			
			stmt.setString(1, sus_no);
			//stmt.setString(2, sus_no);
			//stmt.setString(3, sus_no);

			int j = 2;

			if (fdate != "" && tdate != "") {
				stmt.setString(j, fdate);
				j += 1;
				stmt.setString(j, tdate);
				j += 1;
			}

			if (fdate != "" && tdate == "") {

				
				stmt.setString(j, fdate);
				j += 1;
			}

			if (fdate == "" && tdate != "") {
				stmt.setString(j, tdate);
				j += 1;

			}
			
			
			if (sus_no != "") {

				stmt.setString(j, sus_no);
				j += 1;
			}

			

			ResultSet rs = stmt.executeQuery();
			
			
		

			while (rs.next()) {

				int a1 = Integer.parseInt(rs.getString("UE"));
				int a2 = Integer.parseInt(rs.getString("UH"));
				int dif = a2 - a1;

				if (dif < 0) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("type_of_animal"));
					list.add(rs.getString("UE"));
					list.add(rs.getString("UH"));
					list.add(rs.getString("sur").valueOf(0));
					list.add(rs.getString("defi"));
					list.add(rs.getString("total"));
					aList.add(list);
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("type_of_animal"));
					list.add(rs.getString("UE"));
					list.add(rs.getString("UH"));
					list.add(rs.getString("sur"));
					list.add(rs.getString("defi").valueOf(0));
					list.add(rs.getString("total"));
					aList.add(list);
				}
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

	/// bisag 170822 V1 (Query Optimized)
	public ArrayList<ArrayList<String>> Dogreportlist(String sus_no, String fdate, String tdate) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";
			String qry1 = "";
			String sql = "";

			
			if (fdate != "" && tdate != "") {

				qry1 += " and cast(A.created_date as date) between cast(? as date) and cast(? as date) + 1 ";
			}

			if (fdate != "" && tdate == "") {

				qry1 += " and cast(A.created_date as date) >= cast(? as date)";
			}

			if (fdate == "" && tdate != "") {

				qry1 += " and cast(A.created_date as date) <= cast(? as date)";
			}

		
			
			sql = "select distinct type_splztn,        UE,        UH,         (cast(UH as Integer) - cast(UE as Integer)) as sur,\r\n" + 
					"(cast(UE as Integer) - cast(UH as Integer)) as defi,         (cast(UH as Integer) + cast(UE as Integer)) as total \r\n" + 
					"FROM ( select m.type_splztn,case when (b.ue_of_dogs='0' OR B.ue_of_dogs is null) then '0' else b.ue_of_dogs END as \r\n" + 
					" UE,( select CAST(count(a.id)  as character varying) as UH  from \r\n" + 
					" tb_tms_animal_details_tbl a 			\r\n" + 
					" left join tb_tms_specialization_master m on m.id=a.specialization\r\n" + 
					" where a.anml_type = 'Army Dog' and a.sus_no = ? " +qry1 +" ) from tb_tms_animals_ue_master b \r\n" + 
					" left join tb_tms_specialization_master m on m.id=b.specialization\r\n" + 
					" where b.anml_type = 'Army Dog' and b.sus_no = ? ) D where UH !='0' OR UE != '0'";
			
			stmt = conn.prepareStatement(sql);
			/*stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);*/
			stmt.setString(1, sus_no);
			
		

			int j=2;

		
			
			if (fdate != "" && tdate != "") {
				stmt.setString(j, fdate);
				j += 1;
				stmt.setString(j, tdate);
				j += 1;
			}
			if (fdate != "" && tdate == "") {
				stmt.setString(j, fdate);
				j += 1;
			}
			if (fdate == "" && tdate != "") {
				stmt.setString(j, tdate);
				j += 1;
			}
			if (sus_no != "") {
				stmt.setString(j, sus_no);
				j += 1;
			}
			
			ResultSet rs = stmt.executeQuery();
			
		

			while (rs.next()) {

				int a1 = Integer.parseInt(rs.getString("UE"));
				int a2 = Integer.parseInt(rs.getString("UH"));
				int dif = a2 - a1;

				if (dif < 0) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("type_splztn"));
					list.add(rs.getString("UE"));
					list.add(rs.getString("UH"));
					list.add(rs.getString("sur").valueOf(0));
					list.add(rs.getString("defi"));
					list.add(rs.getString("total"));
					aList.add(list);
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("type_splztn"));
					list.add(rs.getString("UE"));
					list.add(rs.getString("UH"));
					list.add(rs.getString("sur"));
					list.add(rs.getString("defi").valueOf(0));
					list.add(rs.getString("total"));
					aList.add(list);
				}
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
}