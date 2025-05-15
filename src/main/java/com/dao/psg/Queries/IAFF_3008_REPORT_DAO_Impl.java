package com.dao.psg.Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class IAFF_3008_REPORT_DAO_Impl implements IAFF_3008_REPORT_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> iaff3008_Report_Serving(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

		

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!rank.equals("")) {
				qry += " and upper(np.rank) = ? ";
			}
			q = "select * from (select distinct appointment,rank,name,personal_no,cda_acc_no,parent_arm,regiment,med_cat,\r\n"
					+ "TO_CHAR(date_of_tos,'DD-MON-YYYY') as dt_of_tos,\r\n"
					+ "TO_CHAR(date_of_birth,'DD-MON-YYYY') as dt_of_birth,\r\n"
					+ "TO_CHAR(date_of_seniority,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ "TO_CHAR(date_of_commission ,'DD-MON-YYYY') as dt_of_commission,\r\n"
					+ "TO_CHAR(date_of_appointment ,'DD-MON-YYYY') as dt_of_appointment,\r\n"
					+ "month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no,date_of_seniority as dt_sen from \r\n"
					+ "dblink('myconn','select distinct s.appointment,s.rank,s.name,s.personal_no,s.cda_acc_no,\r\n" + 
					"s.parent_arm,s.regiment,s.med_cat,s.date_of_tos,s.date_of_birth,s.date_of_seniority,s.date_of_commission,\r\n" + 
					"s.date_of_appointment,s.month,s.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no\r\n" + 
					" from  tb_psg_iaff_3008_serving s\r\n" + 
					"inner join tb_psg_iaff_3008_main v on s.version = v.version and v.month = s.month and  \r\n" + 
					"v.year = s.year and v.sus_no = s.sus_no \r\n" + 
					"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
					"v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ') \r\n"
					+ "AS t(appointment character varying,rank character varying,name character varying,personal_no character varying,\r\n"
					+ "	 cda_acc_no character varying, parent_arm character varying, regiment character varying,med_cat character varying,date_of_tos timestamp without time zone,\r\n"
					+ "	 date_of_birth timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
					+ "	 date_of_commission timestamp without time zone,date_of_appointment timestamp without time zone,\r\n"
					+ "    month character varying,year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying)) np\r\n"
					+ "	 left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank = np.rank " + qry
					+ " ORDER BY ro.id,dt_sen";

			stmt = c.prepareStatement(q);
			System.err.println("query for iaff3008_Report_Serving: \n" + stmt.toString());
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!rank.equals("")) {
					stmt.setString(j, rank.toUpperCase());
					// j += 1;
				}
			}
			
		
			rs = stmt.executeQuery();
			System.err.println("query for iaff3008_Report_Serving: \n" + stmt.toString());
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("appointment"));// 1
				list_Search.add(rs.getString("rank"));// 2
				list_Search.add(rs.getString("name"));// 3
				list_Search.add(rs.getString("personal_no"));// 4
				list_Search.add(rs.getString("cda_acc_no"));// 5
//				list_Search.add(rs.getString("parent_arm"));// 6
				if(rs.getString("regiment")!= null ) { 
					list_Search.add(rs.getString("regiment"));//6
					
				}else {
					list_Search.add(rs.getString("parent_arm"));//6
				}
				list_Search.add(rs.getString("med_cat"));// 7
				list_Search.add(rs.getString("dt_of_tos"));// 8
				list_Search.add(rs.getString("dt_of_birth"));// 9
				list_Search.add(rs.getString("dt_of_commission"));// 10
				list_Search.add(rs.getString("dt_of_seniority"));// 11
				list_Search.add(rs.getString("dt_of_appointment"));// 12

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;

	}

	// --- Super Numarary

	public ArrayList<ArrayList<String>> iaff3008_Report_SuperNum(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

		

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!rank.equals("")) {
				qry += " and upper(np.rank) = ? ";
			}
			q = "select * from (select distinct appointment,rank,name,personal_no,cda_acc_no,parent_arm,regiment,med_cat,\r\n"
					+ "TO_CHAR(date_of_tos,'DD-MON-YYYY') as dt_of_tos,\r\n"
					+ "TO_CHAR(date_of_birth,'DD-MON-YYYY') as dt_of_birth,\r\n"
					+ "TO_CHAR(date_of_seniority,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ "TO_CHAR(date_of_commission ,'DD-MON-YYYY') as dt_of_commission,\r\n"
					+ "TO_CHAR(date_of_appointment ,'DD-MON-YYYY') as dt_of_appointment,\r\n"
					+ "month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no,date_of_seniority as dt_sen from \r\n"
					+ "dblink('myconn','select distinct sp.appointment,sp.rank,sp.name,sp.personal_no,sp.cda_acc_no,\r\n" + 
					"sp.parent_arm,sp.regiment,sp.med_cat,sp.date_of_tos,sp.date_of_birth,sp.date_of_seniority,sp.date_of_commission,\r\n" + 
					"sp.date_of_appointment,sp.month,sp.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no\r\n" + 
					" from  tb_psg_iaff_3008_supernumerary sp\r\n" + 
					"inner join tb_psg_iaff_3008_main v on sp.version = v.version and v.month = sp.month and  \r\n" + 
					"v.year = sp.year and v.sus_no = sp.sus_no \r\n" + 
					"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
					"v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ') \r\n"
					+ "AS t(appointment character varying,rank character varying,name character varying,personal_no character varying,\r\n"
					+ "	 cda_acc_no character varying, parent_arm character varying,regiment character varying,med_cat character varying,date_of_tos timestamp without time zone,\r\n"
					+ "	 date_of_birth timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
					+ "	 date_of_commission timestamp without time zone,date_of_appointment timestamp without time zone,\r\n"
					+ "      month character varying,year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying) ) np \r\n"
					+ "	 left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank = np.rank " + qry
					+ " ORDER BY ro.id,dt_sen";

			stmt = c.prepareStatement(q);
			System.err.println("query for iaff3008_Report_SuperNum: \n" + stmt);
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!rank.equals("")) {
					stmt.setString(j, rank.toUpperCase());
					// j += 1;
				}
			}
		 
			rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;

				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("appointment"));// 1
				list_Search.add(rs.getString("rank"));// 2
				list_Search.add(rs.getString("name"));// 3
				list_Search.add(rs.getString("personal_no"));// 4
				list_Search.add(rs.getString("cda_acc_no"));// 5
//				list_Search.add(rs.getString("parent_arm"));// 6
				if(rs.getString("regiment")!= null ) {
					list_Search.add(rs.getString("regiment"));//6
					
				}else {
					list_Search.add(rs.getString("parent_arm"));//6
				}
				list_Search.add(rs.getString("med_cat"));// 7
				list_Search.add(rs.getString("dt_of_tos"));// 8
				list_Search.add(rs.getString("dt_of_birth"));// 9
				list_Search.add(rs.getString("dt_of_commission"));// 10
				list_Search.add(rs.getString("dt_of_seniority"));// 11
				list_Search.add(rs.getString("dt_of_appointment"));// 12

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
				return alist;

	}

	// --- Re Employ

	public ArrayList<ArrayList<String>> iaff3008_Report_Re_Empl(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!rank.equals("")) {
				qry += " and upper(np.rank) = ? ";
			}
			q = "select * from (select distinct appointment,rank,name,personal_no,cda_acc_no,parent_arm,regiment,med_cat,\r\n"
					+ "TO_CHAR(date_of_tos,'DD-MON-YYYY') as dt_of_tos,\r\n"
					+ "TO_CHAR(date_of_birth,'DD-MON-YYYY') as dt_of_birth,\r\n"
					+ "TO_CHAR(date_of_seniority,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ "TO_CHAR(date_of_commission ,'DD-MON-YYYY') as dt_of_commission,\r\n"
					+ "TO_CHAR(date_of_appointment ,'DD-MON-YYYY') as dt_of_appointment,\r\n"
					+ "month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no ,date_of_seniority as dt_sen from \r\n"
					+ "dblink('myconn','select distinct re.appointment,re.rank,re.name,re.personal_no,re.cda_acc_no,\r\n" + 
					"re.parent_arm,re.regiment,re.med_cat,re.date_of_tos,re.date_of_birth,re.date_of_seniority,re.date_of_commission,\r\n" + 
					"re.date_of_appointment,re.month,re.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no\r\n" + 
					" from  tb_psg_iaff_3008_re_employeement re\r\n" + 
					"inner join tb_psg_iaff_3008_main v on re.version = v.version and v.month = re.month and  \r\n" + 
					"v.year = re.year and v.sus_no = re.sus_no \r\n" + 
					"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
					"v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ') \r\n"
					+ "AS t(appointment character varying,rank character varying,name character varying,personal_no character varying,\r\n"
					+ "	 cda_acc_no character varying, parent_arm character varying,regiment character varying,med_cat character varying,date_of_tos timestamp without time zone,\r\n"
					+ "	 date_of_birth timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
					+ "	 date_of_commission timestamp without time zone,date_of_appointment timestamp without time zone,\r\n"
					+ "      month character varying,year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying)) np " 
					+ "	 left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank = np.rank " + qry
					+ " ORDER BY ro.id,dt_sen";

			stmt = c.prepareStatement(q);
			System.err.println("query for iaff3008_Report_Re_Empl: \n" + stmt);
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!rank.equals("")) {
					stmt.setString(j, rank.toUpperCase());
					/// j += 1;
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("appointment"));// 1
				list_Search.add(rs.getString("rank"));// 2
				list_Search.add(rs.getString("name"));// 3
				list_Search.add(rs.getString("personal_no"));// 4
				list_Search.add(rs.getString("cda_acc_no"));// 5
//				list_Search.add(rs.getString("parent_arm"));// 6
				if(rs.getString("regiment")!= null ) {
					list_Search.add(rs.getString("regiment"));//6
				}else {
					list_Search.add(rs.getString("parent_arm"));//6
				}
				list_Search.add(rs.getString("med_cat"));// 7
				list_Search.add(rs.getString("dt_of_tos"));// 8
				list_Search.add(rs.getString("dt_of_birth"));// 9
				list_Search.add(rs.getString("dt_of_commission"));// 10
				list_Search.add(rs.getString("dt_of_seniority"));// 11
				list_Search.add(rs.getString("dt_of_appointment"));// 12
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;

	}

	// -----Deserter
	public ArrayList<ArrayList<String>> iaff3008_Report_Deserter(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
			if (!month.equals("0")) {
				qry += " where np.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and np.year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and np.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and np.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and np.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and np.bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and np.sus_no = ?  ";
			}
			if (!rank.equals("")) {
				qry += " and upper(np.rank) = ? ";
			}

			q = "select * from (select distinct appointment,rank,name,personal_no,cda_acc_no,parent_arm,regiment,med_cat,\r\n"
					+ "TO_CHAR(date_of_tos,'DD-MON-YYYY') as dt_of_tos,\r\n"
					+ "TO_CHAR(date_of_birth,'DD-MON-YYYY') as dt_of_birth,\r\n"
					+ "TO_CHAR(date_of_seniority,'DD-MON-YYYY') as dt_of_seniority,\r\n"
					+ "TO_CHAR(date_of_commission ,'DD-MON-YYYY') as dt_of_commission,\r\n"
					+ "TO_CHAR(date_of_appointment ,'DD-MON-YYYY') as dt_of_appointment,\r\n"
					+ "month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no,date_of_seniority as dt_sen from \r\n"
					+ "dblink('myconn','select distinct ds.appointment,ds.rank,ds.name,ds.personal_no,ds.cda_acc_no,\r\n" + 
					"ds.parent_arm,ds.regiment,ds.med_cat,ds.date_of_tos,ds.date_of_birth,ds.date_of_seniority,ds.date_of_commission,\r\n" + 
					"ds.date_of_appointment,ds.month,ds.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no\r\n" + 
					" from  tb_psg_iaff_3008_deserter ds\r\n" + 
					"inner join tb_psg_iaff_3008_main v on ds.version = v.version and v.month = ds.month and  \r\n" + 
					"v.year = ds.year and v.sus_no = ds.sus_no \r\n" + 
					"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
					"v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ') \r\n"
					+ "AS t(appointment character varying,rank character varying,name character varying,personal_no character varying,\r\n"
					+ "	 cda_acc_no character varying, parent_arm character varying,regiment character varying,med_cat character varying,date_of_tos timestamp without time zone,\r\n"
					+ "	 date_of_birth timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
					+ "	 date_of_commission timestamp without time zone,date_of_appointment timestamp without time zone,\r\n"
					+ "    month character varying,year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying)) np " 
					+ "	 left join tb_psg_iaff_3008_rank_wise_data ro on ro.rank = np.rank " + qry
					+ " ORDER BY ro.id,dt_sen";

			stmt = c.prepareStatement(q);
			System.err.println("query for iaff3008_Report_Deserter: \n" + stmt);
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
				if (!rank.equals("")) {
					stmt.setString(j, rank.toUpperCase());
					/// j += 1;
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("appointment"));// 1
				list_Search.add(rs.getString("rank"));// 2
				list_Search.add(rs.getString("name"));// 3
				list_Search.add(rs.getString("personal_no"));// 4
				list_Search.add(rs.getString("cda_acc_no"));// 5
//				list_Search.add(rs.getString("parent_arm"));// 6
				if(rs.getString("regiment")!= null ) {
					list_Search.add(rs.getString("regiment"));//6
					
				}else {
					list_Search.add(rs.getString("parent_arm"));//6
				}
				list_Search.add(rs.getString("med_cat"));// 7
				list_Search.add(rs.getString("dt_of_tos"));// 8
				list_Search.add(rs.getString("dt_of_birth"));// 9
				list_Search.add(rs.getString("dt_of_commission"));// 10
				list_Search.add(rs.getString("dt_of_seniority"));// 11
				list_Search.add(rs.getString("dt_of_appointment"));// 12
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
	
		return alist;

	}

	// ---------------------MAIN DETAILS

	public ArrayList<ArrayList<String>> iaff3008_Report_MainDetails(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String q = "";
		String qry = "";
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres",
					"postgres");
			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

			

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
			if (!month.equals("0")) {
				qry += " where month = ? ";
			}
			if (!year.equals("")) {
				qry += " and year = ? ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and bde_sus = ? ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and sus_no = ?  ";
			}

			q = "select distinct present_return_no,\r\n"
					+ "TO_CHAR(present_return_dt,'DD-MON-YYYY') as present_return_dt,\r\n" + "we_pe_no,auth,held,\r\n"
					+ "TO_CHAR(TO_TIMESTAMP(month,'MM'),'MON') as month ,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no,unit_name from \r\n"
					+ "dblink('myconn','select distinct m.present_return_no,m.present_return_dt,m.we_pe_no,m.auth,m.held,\r\n" + 
					"	   month,year,m.cmd_sus,m.corp_sus,m.div_sus,m.bde_sus,m.sus_no, ud.unit_name\r\n" + 
					"from tb_psg_iaff_3008_main_details m\r\n" + 
					"inner join tb_miso_orbat_unt_dtl ud on ud.sus_no = m.sus_no and ud.status_sus_no=''Active'' ') \r\n"
					+ "AS t(present_return_no character varying,present_return_dt timestamp without time zone,\r\n"
					+ "we_pe_no character varying,auth  character varying,held character varying,\r\n"
					+ "month character varying,year character varying,cmd_sus character varying,corp_sus character varying,\r\n"
					+ "div_sus character varying,bde_sus character varying,sus_no character varying, unit_name character varying)  " + qry;

			stmt = c.prepareStatement(q);
			System.err.println("query for iaff3008_Report_MainDetails: \n" + stmt);
			if (!qry.equals("")) {

				int j = 1;
				if (!month.equals("0")) {
					stmt.setString(j, month);
					j += 1;
				}
				if (year != "") {
					stmt.setString(j, year);
					j += 1;
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					// j += 1;
				}

			}
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;
				list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("present_return_no"));// 1
				list_Search.add(rs.getString("present_return_dt"));// 2
				list_Search.add(rs.getString("we_pe_no"));// 3
				list_Search.add(rs.getString("auth"));// 4
				list_Search.add(rs.getString("held"));// 5
				list_Search.add(rs.getString("month"));// 6
				list_Search.add(rs.getString("year"));// 7
				list_Search.add(rs.getString("sus_no"));// 8
				list_Search.add(rs.getString("unit_name"));// 9
				
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;

	}

	/////////////////////// pratiksha

	public ArrayList<ArrayList<String>> iaff_Report_3008_Auth_Held(String month,
			  String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			  ,String unit_sus_no,String rank)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";	
			try{	
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if (!month.equals("0")) {
					qry += " where v.month = ? ";
				}
				if (!year.equals("")) {
					qry += " and v.year = ? ";
				}
				
				if (!unit_sus_no.equals("")) {
					qry += " and v.sus_no = ?  ";
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					qry += " and md.cmd_sus = ? ";
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					qry += " and md.corp_sus = ? ";
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					qry += " and md.div_sus = ? ";
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					qry += " and md.bde_sus = ? ";
				}
				
				
				if (!rank.equals("")) {
					qry += " and upper(ds.rank) = ? ";
				}
				
				
				
				
				q="select distinct ds.rank,ds.base_auth,ds.mod_auth,ds.foot_auth,ds.total_auth,ds.total_held,\r\n" + 
						"ds.month,ds.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no,b.id \r\n" + 
						" from  tb_psg_iaff_3008_auth ds\r\n" + 
						"inner join tb_psg_iaff_3008_main v on ds.version = v.version and v.month = ds.month and  \r\n" + 
						"v.year = ds.year and v.sus_no = ds.sus_no \r\n" + 
						"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
						"v.year = md.year and v.sus_no = md.sus_no and v.status != 3 \r\n" + 
						"inner join \r\n" + 
						"tb_psg_iaff_3008_rank_wise_data b on ds.rank=b.rank" + qry +
							 " order by b.id  ";
						
				
				
			  stmt=conn.prepareStatement(q);
			  
			  if (!qry.equals("")) {

					int j = 1;
					if (!month.equals("0")) {
						stmt.setString(j, month);
						j += 1;
					}
					if (year != "") {
						stmt.setString(j, year);
						j += 1;
					}
					
					if (!unit_sus_no.equals("")) {
						stmt.setString(j, unit_sus_no);
						 j += 1;
					}
					if (!comd_sus.equals("0") && !comd_sus.equals("")) {
						stmt.setString(j, comd_sus);
						j += 1;
					}
					if (!corp_sus.equals("0") && !corp_sus.equals("")) {
						stmt.setString(j, corp_sus);
						j += 1;
					}
					if (!div_sus.equals("0") && !div_sus.equals("")) {
						stmt.setString(j, div_sus);
						j += 1;
					}
					if (!bde_sus.equals("0") && !bde_sus.equals("")) {
						stmt.setString(j, bde_sus);
						j += 1;
					}
					if (!rank.equals("")) {
						stmt.setString(j, rank);
						// j += 1;
					}

				}
			  
			  int i = 0;
		      ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  i++;
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i));
		    	  list.add(rs.getString("rank"));
					list.add(rs.getString("base_auth"));					
					list.add(rs.getString("mod_auth"));
					list.add(rs.getString("foot_auth"));
					list.add(rs.getString("total_auth"));	
					list.add(rs.getString("total_held"));	
					
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
	//bisag v2 290822 (split auth and held)
	public ArrayList<ArrayList<String>> iaff_Report_3008_Auth(String month,
			  String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			  ,String unit_sus_no,String rank)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";	
			try{	
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if (!month.equals("0")) {
					qry += " where v.month = ? ";
				}
				if (!year.equals("")) {
					qry += " and v.year = ? ";
				}
				
				if (!unit_sus_no.equals("")) {
					qry += " and v.sus_no = ?  ";
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					qry += " and md.cmd_sus = ? ";
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					qry += " and md.corp_sus = ? ";
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					qry += " and md.div_sus = ? ";
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					qry += " and md.bde_sus = ? ";
				}
				
				
				if (!rank.equals("")) {
					qry += " and upper(ds.rank) = ? ";
				}
				
				
				
				
				q="select distinct ds.rank,ds.base_auth,ds.mod_auth,ds.foot_auth,ds.total_auth,ds.total_held,\r\n" + 
						"ds.month,ds.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no,b.id \r\n" + 
						" from  tb_psg_iaff_3008_auth ds\r\n" + 
						"inner join tb_psg_iaff_3008_main v on ds.version = v.version and v.month = ds.month and  \r\n" + 
						"v.year = ds.year and v.sus_no = ds.sus_no \r\n" + 
						"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
						"v.year = md.year and v.sus_no = md.sus_no and v.status != 3 \r\n" + 
						"left join \r\n" + 
						"tb_psg_iaff_3008_rank_wise_data b on ds.rank=b.rank" + qry +
							 " order by b.id  ";
						
				
				
			  stmt=conn.prepareStatement(q);
			  System.err.println("query for iaff_Report_3008_Auth: \n" + stmt);
			  if (!qry.equals("")) {

					int j = 1;
					if (!month.equals("0")) {
						stmt.setString(j, month);
						j += 1;
					}
					if (year != "") {
						stmt.setString(j, year);
						j += 1;
					}
					
					if (!unit_sus_no.equals("")) {
						stmt.setString(j, unit_sus_no);
						 j += 1;
					}
					if (!comd_sus.equals("0") && !comd_sus.equals("")) {
						stmt.setString(j, comd_sus);
						j += 1;
					}
					if (!corp_sus.equals("0") && !corp_sus.equals("")) {
						stmt.setString(j, corp_sus);
						j += 1;
					}
					if (!div_sus.equals("0") && !div_sus.equals("")) {
						stmt.setString(j, div_sus);
						j += 1;
					}
					if (!bde_sus.equals("0") && !bde_sus.equals("")) {
						stmt.setString(j, bde_sus);
						j += 1;
					}
					if (!rank.equals("")) {
						stmt.setString(j, rank);
						// j += 1;
					}

				}
			  
		
			  
			  int i = 0;
		      ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  i++;
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i));
		    	  list.add(rs.getString("rank"));
					list.add(rs.getString("base_auth"));					
					list.add(rs.getString("mod_auth"));
					list.add(rs.getString("foot_auth"));
					list.add(rs.getString("total_auth"));	
//					list.add(rs.getString("total_held"));	
					
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
	
	
	public ArrayList<ArrayList<String>> iaff_Report_3008_Held(String month,
			  String year,String comd_sus,String corp_sus,String div_sus,String bde_sus
			  ,String unit_sus_no,String rank)

	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
		Connection conn = null;
		String q="";
		String qry="";	
			try{	
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				if (!month.equals("0")) {
					qry += " where v.month = ? ";
				}
				if (!year.equals("")) {
					qry += " and v.year = ? ";
				}
				
				if (!unit_sus_no.equals("")) {
					qry += " and v.sus_no = ?  ";
				}
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					qry += " and md.cmd_sus = ? ";
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					qry += " and md.corp_sus = ? ";
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					qry += " and md.div_sus = ? ";
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					qry += " and md.bde_sus = ? ";
				}
				
				
				if (!rank.equals("")) {
					qry += " and upper(ds.rank) = ? ";
				}
				
				
				
				
				q="select distinct ds.rank,ds.base_auth,ds.mod_auth,ds.foot_auth,ds.total_auth,ds.total_held,\r\n" + 
						"ds.month,ds.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no,b.id \r\n" + 
						" from  tb_psg_iaff_3008_held ds\r\n" + 
						"inner join tb_psg_iaff_3008_main v on ds.version = v.version and v.month = ds.month and  \r\n" + 
						"v.year = ds.year and v.sus_no = ds.sus_no \r\n" + 
						"inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and\r\n" + 
						"v.year = md.year and v.sus_no = md.sus_no and v.status != 3 \r\n" + 
						"inner join \r\n" + 
						"tb_psg_iaff_3008_rank_wise_data b on ds.rank=b.rank" + qry +
							 " order by b.id  ";
						
				
				
			  stmt=conn.prepareStatement(q);
			  System.err.println("query for iaff_Report_3008_Held: \n" + stmt);
			  if (!qry.equals("")) {

					int j = 1;
					if (!month.equals("0")) {
						stmt.setString(j, month);
						j += 1;
					}
					if (year != "") {
						stmt.setString(j, year);
						j += 1;
					}
					
					if (!unit_sus_no.equals("")) {
						stmt.setString(j, unit_sus_no);
						 j += 1;
					}
					if (!comd_sus.equals("0") && !comd_sus.equals("")) {
						stmt.setString(j, comd_sus);
						j += 1;
					}
					if (!corp_sus.equals("0") && !corp_sus.equals("")) {
						stmt.setString(j, corp_sus);
						j += 1;
					}
					if (!div_sus.equals("0") && !div_sus.equals("")) {
						stmt.setString(j, div_sus);
						j += 1;
					}
					if (!bde_sus.equals("0") && !bde_sus.equals("")) {
						stmt.setString(j, bde_sus);
						j += 1;
					}
					if (!rank.equals("")) {
						stmt.setString(j, rank);
						// j += 1;
					}

				}
			  
			  int i = 0;
		      ResultSet rs = stmt.executeQuery();   	      
		      while (rs.next()) {
		    	  i++;
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  list.add(String.valueOf(i));
		    	  list.add(rs.getString("rank"));
//					list.add(rs.getString("base_auth"));					
//					list.add(rs.getString("mod_auth"));
//					list.add(rs.getString("foot_auth"));
//					list.add(rs.getString("total_auth"));	
					list.add(rs.getString("total_held"));	
					
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
	
	//bisag v2 290822 (split auth and held) end
	
	
	
	
	public ArrayList<ArrayList<String>> get3008Report_list(String month, String year, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no,String line_dte_sus) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		String q = "";

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;			

             String qry="";				
			
		
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and substr(b.form_code_control,1,1) =substr(?,1,1) ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and substr(b.form_code_control,2,2) = substr(?,2,2) ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and substr(b.form_code_control,4,3) =substr(?,4,3) ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and substr(b.form_code_control,7,4) =substr(?,7,4) ";
			}
			if (!unit_sus_no.equals("")) {
				qry += " and b.sus_no = ?  ";
			}
			if(!line_dte_sus.equals("") && !line_dte_sus.equals("0")) {
				qry += " and b.arm_code in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus =?)  ";
			}
			
			
          	q = " SELECT distinct \r\n"
          			+ "	b.sus_no,\r\n"
          			+ "	b.unit_name,\r\n"
          			+ "   COALESCE(\r\n"
          			+ "        (SELECT a.status \r\n"
          			+ "         FROM tb_psg_iaff_3008_main a \r\n"
          			+ "         WHERE a.sus_no = b.sus_no \r\n"
          			+ "           AND a.month =?\r\n"
          			+ "           AND a.year = ?\r\n"
          			+ "        ),\r\n"
          			+ "        -1\r\n"
          			+ "    ) AS status,\r\n"
          			+ " COALESCE((select a.version from tb_psg_iaff_3008_main a where a.sus_no = b.sus_no 	AND a.month=? AND a.year=?),'0') as version\r\n"
          			+ "	\r\n"
          			+ "FROM\r\n"
          			+ "	tb_miso_orbat_unt_dtl b\r\n"
          			+ "	inner join tb_miso_orbat_codesform c\r\n"
          			+ "	on c.sus_no = b.sus_no\r\n"
          		//	+ "	and level_in_hierarchy = 'Unit'\r\n"
          			+ "WHERE\r\n"
          			+ "	b.status_sus_no='Active' "+qry+" ";
			stmt = conn.prepareStatement(q);
			stmt.setString(1, month);
			stmt.setString(2, year);
			stmt.setString(3, month);
			stmt.setString(4, year);
		
			if (!qry.equals("")) {

				int j = 5;
				
				if (!comd_sus.equals("0") && !comd_sus.equals("")) {
					stmt.setString(j, comd_sus);
					j += 1;
				}
				if (!corp_sus.equals("0") && !corp_sus.equals("")) {
					stmt.setString(j, corp_sus);
					j += 1;
				}
				if (!div_sus.equals("0") && !div_sus.equals("")) {
					stmt.setString(j, div_sus);
					j += 1;
				}
				if (!bde_sus.equals("0") && !bde_sus.equals("")) {
					stmt.setString(j, bde_sus);
					j += 1;
				}
				if (!unit_sus_no.equals("")) {
					stmt.setString(j, unit_sus_no);
					j += 1;
				}
		
				if(!line_dte_sus.equals("") && !line_dte_sus.equals("0")) {
					stmt.setString(j, line_dte_sus);
					j += 1;
				}
				
			}
			
                     

			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); // 0
				list.add(rs.getString("unit_name"));// 1			
				list.add(month);// 2	
				list.add(year);// 3	
				list.add(rs.getString("version"));// 4
				
				int status=Integer.parseInt(rs.getString("status"));
				String f1 = "";
				String f4 = "";
				String value="";
				if(status== -1) {
					value="NOT YET UPDT";
				}else if(status == 0) {
					value="PENDING";
				}else if(status ==1) {
					
					 String View = "onclick=\"  if (confirm('Are You Sure You Want to View This ?') ){editData("
								+ month + ",'" + year + "' ,'" + rs.getString("version")
								+ "' ,'" + rs.getString("sus_no") + "','"+ status +"')}else{ return false;}\"";
						f4 = "<i class='fa fa-eye'  " + View + " title='Approve/View Data'></i>";

							
					 
					 String Printreport = "onclick=\"  if (confirm('Are You Sure You Want to Download This Report ?') ){print_report("
								+ month+ ",'" + year + "' ,'" + rs.getString("version")
								+ "' ,'" + rs.getString("sus_no") + "')}else{ return false;}\"";
						f1 = "<i class='fa fa-download'  " + Printreport + " title='Download Report'></i>";
						
						value = f4 + f1 ;
					
				}
				list.add(value);	
				alist.add(list);

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
		return alist;
	}
	
	public ArrayList<ArrayList<String>> getRemarks3008(String month, String year, String unitSusNo) {
	    ArrayList<ArrayList<String>> remarkLists = new ArrayList<>();

	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = dataSource.getConnection();
	        String query = "SELECT dtl.remarksforoffrstos AS remarks1, " +
	                "dtl.remarksforoffrssos AS remarks2, " +
	                "dtl.remarksforoffrspostout AS remarks3, " +
	                "dtl.remarksforoffrspostin AS remarks4, " +
	                "dtl.remarksforoffrsunderretire AS remarks5, " +
	                "dtl.remarks AS remarks6, " +
	                "dtl.remarksdistr AS remarks7 " +
	                "FROM tb_psg_iaff_3008_main_details dtl " +
	                "INNER JOIN tb_psg_iaff_3008_main main ON dtl.sus_no=main.sus_no " +
	                "AND dtl.month=main.month " +
	                "AND dtl.year=main.year " +
	                "AND dtl.version=main.version " +
	                "WHERE main.sus_no=? " +
	                "AND main.year=? " +
	                "AND main.month=? and main.status=1";

	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, unitSusNo);
	        stmt.setString(2, year);
	        stmt.setString(3, month);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            ArrayList<String> remarkList = new ArrayList<>();
	            remarkList.add(rs.getString("remarks1"));
	            remarkList.add(rs.getString("remarks2"));
	            remarkList.add(rs.getString("remarks3"));
	            remarkList.add(rs.getString("remarks4"));
	            remarkList.add(rs.getString("remarks5"));
	            remarkList.add(rs.getString("remarks6"));
	            remarkList.add(rs.getString("remarks7"));

	            remarkLists.add(remarkList);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	            }
	        }
	    }
	    return remarkLists;
	}
	
}
