package com.dao.psg.Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class IAFF_3009_REPORT_DAO_Impl implements IAFF_3009_REPORT_DAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> iaff3009_Report_auth_str_jco_or(String month, String year, String comd_sus,
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
	//c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap?user=postgres&password=postgres");
		

			c.setAutoCommit(false);
			q = "SELECT dblink_connect('myconn','miso_psg_oltp1')";
			stmt = c.prepareStatement(q);
			rs = stmt.executeQuery();

		

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
				
			}
//			if (!month.equals("0")) {
//				qry += " where auth.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and auth.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and auth.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
			
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
				qry += " and upper(md.rank) = ? ";
			}
			
//			q="select distinct auth.arm_services,sum(auth.offrs) as offrs , sum(auth.mns_offrs) as mns_offrs,sum(auth.jcos) as jcos,sum(auth.ors) as ors,auth.remarks\r\n"
//					+ "					                      from  tb_psg_auth_str_jco_or_3009 as auth										  \r\n"
//					+ "										    inner join  tb_psg_iaff_3009_main_details as md \r\n"
//					+ "										    on auth.sus_no = md.sus_no and auth.year = md.year and auth.version=md.version and auth.month =md.month and  md.status != 3 \r\n"  +qry
//					+ "GROUP BY auth.arm_services,auth.remarks";
													   

			q = "select  distinct arm_services,sum(offrs) as offrs , sum(mns_offrs) as mns_offrs,sum(jcos) as jcos,sum(ors) as ors,remarks from (select distinct arm_services,offrs,mns_offrs,jcos,ors,remarks,\r\n"
					+ "					month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "					dblink('myconn',' select distinct auth.arm_services,auth.offrs,\r\n"
					+ "					  	                auth.mns_offrs,auth.jcos,\r\n"
					+ "				      	auth.ors,auth.remarks,auth.month,auth.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no from tb_psg_iaff_3009_main as v \r\n"
					+ "						inner join tb_psg_auth_str_jco_or_3009 as auth \r\n"
					+ "						on v.sus_no = auth.sus_no and v.year = auth.year and v.version=auth.version and v.month = auth.month\r\n"
					+ "						inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "						on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "						where  v.status != 3  ') \r\n"
					+ "					AS t(arm_services character varying,offrs integer,mns_offrs integer,jcos integer,\r\n"
					+ "						 ors integer, remarks character varying, month character varying,year character varying, \r\n"
					+ "						 cmd_sus character varying,corp_sus character varying,\r\n"
					+ "	 div_sus character varying,bde_sus character varying,sus_no character varying)) np \r\n" + qry
					+ "GROUP BY arm_services,remarks";

			stmt = c.prepareStatement(q);
			System.err.println("query for auth str: \n" + stmt);
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
				list_Search.add(rs.getString("arm_services"));// 1
				list_Search.add(rs.getString("offrs"));// 2
				list_Search.add(rs.getString("mns_offrs"));// 3
				list_Search.add(rs.getString("jcos"));// 4
				list_Search.add(rs.getString("ors"));// 5//
				list_Search.add(rs.getString("remarks"));// 6
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;
	}

	public ArrayList<ArrayList<String>> iaff3009_Report_auth_civ(String month, String year, String comd_sus,
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
//			if (!month.equals("0")) {
//				qry += " where auth.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and auth.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and auth.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
			
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


//			q="select distinct sum(auth.gp_a_gazetted) as gp_a_gazetted , sum(auth.gp_b_gazetted) as gp_b_gazetted ,sum(auth.gp_b_non_gazetted) as gp_b_non_gazetted , sum(auth.gp_c_non_gazetted) as gp_c_non_gazetted,auth.remarks\r\n"
//					+ "					                      from  tb_psg_auth_civ_3009 as auth										  \r\n"
//					+ "										    inner join  tb_psg_iaff_3009_main_details as md \r\n"
//					+ "										    on auth.sus_no = md.sus_no and auth.year = md.year and auth.version=md.version and auth.month =md.month and md.status != 3\r\n" +qry
//					+ "                                          GROUP BY auth.remarks";


			q = "select  sum(gp_a_gazetted) AS gp_a_gazetted,\r\n"
					+ "       sum(gp_b_gazetted) AS gp_b_gazetted,\r\n"
					+ "       sum(gp_b_non_gazetted) AS gp_b_non_gazetted,\r\n"
					+ "       sum(gp_c_non_gazetted) AS gp_c_non_gazetted,\r\n"
					+ "       remarks from (select distinct gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
					+ "					month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "					dblink('myconn',' select distinct auth.gp_a_gazetted, auth.gp_b_gazetted,auth.gp_b_non_gazetted, auth.gp_c_non_gazetted,auth.remarks,\r\n"
					+ "						   auth.month,auth.year,\r\n"
					+ "					 md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no \r\n"
					+ "					 from tb_psg_iaff_3009_main as v \r\n"
					+ "					 inner join tb_psg_auth_civ_3009 as auth \r\n"
					+ "					 on v.sus_no = auth.sus_no and v.year = auth.year and v.version=auth.version and v.month = auth.month\r\n"
					+ "					inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "					on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "					where  v.status != 3 ') \r\n"
					+ "					AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted integer,\r\n"
					+ "						 gp_c_non_gazetted integer, remarks character varying, month character varying,year character varying,						 \r\n"
					+ "						 cmd_sus character varying,corp_sus character varying,\r\n"
					+ "						 div_sus character varying,bde_sus character varying,sus_no character varying)) np \r\n"
					+ qry
					+ "                                          GROUP BY remarks";

			stmt = c.prepareStatement(q);
			System.err.println("query for auth civ: \n" + stmt);
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

				list_Search.add(rs.getString("gp_a_gazetted"));// 1
				list_Search.add(rs.getString("gp_b_gazetted"));// 2
				list_Search.add(rs.getString("gp_b_non_gazetted"));// 3
				list_Search.add(rs.getString("gp_b_non_gazetted"));// 4
				list_Search.add(rs.getString("remarks"));// 5//
				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;
	}

	public ArrayList<ArrayList<String>> iaff3009_Report_posted_offrs_jco_or(String month, String year, String comd_sus,
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
//			if (!month.equals("0")) {
//				qry += " where post.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and post.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and post.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
			
			
//
//			q = " SELECT\r\n"
//					+ "    post.arms_services,\r\n"
//					+ "    SUM(post.brig_and_above_offrs) AS brig_and_above_offrs,\r\n"
//					+ "    SUM(post.col_offrs) AS col_offrs,\r\n"
//					+ "    SUM(post.col_ts_offrs) AS col_ts_offrs,\r\n"
//					+ "    SUM(post.lt_col_offrs) AS lt_col_offrs,\r\n"
//					+ "    SUM(post.maj_offrs) AS maj_offrs,\r\n"
//					+ "    SUM(post.capt_lt_offrs) AS capt_lt_offrs,\r\n"
//					+ "    SUM(post.brig_and_above_mns_offrs) AS brig_and_above_mns_offrs,\r\n"
//					+ "    SUM(post.col_mns_offrs) AS col_mns_offrs,\r\n"
//					+ "    SUM(post.lt_col_mns_offrs) AS lt_col_mns_offrs,\r\n"
//					+ "    SUM(post.maj_mns_offrs) AS maj_mns_offrs,\r\n"
//					+ "    SUM(post.capt_lt_mns_offrs) AS capt_lt_mns_offrs,\r\n"
//					+ "    SUM(post.sub_major_jco) AS sub_major_jco,\r\n"
//					+ "    SUM(post.sub_jco) AS sub_jco,\r\n"
//					+ "    SUM(post.nb_sub_jco) AS nb_sub_jco,\r\n"
//					+ "    SUM(post.warrant_officer_jco) AS warrant_officer_jco,\r\n"
//					+ "    SUM(post.hav_or) AS hav_or,\r\n"
//					+ "    SUM(post.nk_or) AS nk_or,\r\n"
//					+ "    SUM(post.lnk_sep_or) AS lnk_sep_or,\r\n"
//					+ "    SUM(post.rects_or) AS rects_or\r\n"
//					+ "FROM\r\n"
//					+ "    tb_psg_posted_offrs_jco_or_3009 AS post\r\n"
//					+ "INNER JOIN\r\n"
//					+ "    tb_psg_iaff_3009_main_details AS md ON post.sus_no = md.sus_no\r\n"
//					+ "        AND post.year = md.year\r\n"
//					+ "        AND post.version = md.version\r\n"
//					+ "        AND post.month = md.month\r\n"
//					+ "        and  md.status != 3\r\n" +qry
//		            + "	GROUP BY  post.arms_services ";
			
			
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

			q = " select distinct        arms_services,\r\n"
					+ "										     SUM(brig_and_above_offrs) AS brig_and_above_offrs,\r\n"
					+ "										     SUM(col_offrs) AS col_offrs,\r\n"
					+ "										     SUM(col_ts_offrs) AS col_ts_offrs,\r\n"
					+ "										     SUM(lt_col_offrs) AS lt_col_offrs,\r\n"
					+ "										     SUM(maj_offrs) AS maj_offrs,\r\n"
					+ "										     SUM(capt_lt_offrs) AS capt_lt_offrs,\r\n"
					+ "										     SUM(brig_and_above_mns_offrs) AS brig_and_above_mns_offrs,\r\n"
					+ "										     SUM(col_mns_offrs) AS col_mns_offrs,\r\n"
					+ "										     SUM(lt_col_s_mns_offrs) AS lt_col_s_mns_offrs,\r\n"
					+ "												   SUM(lt_col_ts_mns_offrs) AS lt_col_ts_mns_offrs,\r\n"
					+ "										     SUM(maj_mns_offrs) AS maj_mns_offrs,\r\n"
					+ "										     SUM(capt_lt_mns_offrs) AS capt_lt_mns_offrs,\r\n"
					+ "										     SUM(sub_major_jco) AS sub_major_jco,\r\n"
					+ "										     SUM(sub_jco) AS sub_jco,\r\n"
					+ "										     SUM(nb_sub_jco) AS nb_sub_jco,\r\n"
					+ "										     SUM(warrant_officer_jco) AS warrant_officer_jco,\r\n"
					+ "										     SUM(hav_or) AS hav_or,\r\n"
					+ "										     SUM(nk_or) AS nk_or,\r\n"
					+ "										     SUM(lnk_sep_or) AS lnk_sep_or,\r\n"
					+ "										     SUM(rects_or) AS rects_or from (select distinct arms_services,brig_and_above_offrs,col_offrs,col_ts_offrs,lt_col_offrs,maj_offrs,capt_lt_offrs,brig_and_above_mns_offrs,\r\n"
					+ "								   col_mns_offrs,lt_col_s_mns_offrs,lt_col_ts_mns_offrs,maj_mns_offrs,capt_lt_mns_offrs,sub_major_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,nk_or,\r\n"
					+ "								   lnk_sep_or,rects_or,	month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "								   dblink('myconn','select distinct post.arms_services,post.brig_and_above_offrs,post.col_offrs,post.col_ts_offrs,post.lt_col_offrs,\r\n"
					+ "											    post.maj_offrs,post.capt_lt_offrs,post.brig_and_above_mns_offrs,\r\n"
					+ "												post.col_mns_offrs,post.lt_col_s_mns_offrs,lt_col_ts_mns_offrs,post.maj_mns_offrs,\r\n"
					+ "												post.capt_lt_mns_offrs,post.sub_major_jco,post.sub_jco,\r\n"
					+ "												post.nb_sub_jco,post.warrant_officer_jco,post.hav_or,post.nk_or,post.lnk_sep_or,post.rects_or,post.month,post.year, md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no\r\n"
					+ "										        from tb_psg_iaff_3009_main as v \r\n"
					+ "										       inner join tb_psg_posted_offrs_jco_or_3009 as post\r\n"
					+ "										    on v.sus_no = post.sus_no and v.year = post.year and v.version=post.version and v.month = post.month\r\n"
					+ "										    inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "										    on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "										    where  v.status != 3') \r\n"
					+ "										AS t(arms_services character varying,brig_and_above_offrs integer,col_offrs integer,col_ts_offrs integer,lt_col_offrs integer,\r\n"
					+ "											 maj_offrs integer,capt_lt_offrs integer,brig_and_above_mns_offrs integer,col_mns_offrs integer,lt_col_s_mns_offrs integer,lt_col_ts_mns_offrs integer,\r\n"
					+ "											 maj_mns_offrs integer,capt_lt_mns_offrs integer,sub_major_jco integer,sub_jco integer,nb_sub_jco integer,warrant_officer_jco integer,\r\n"
					+ "											 hav_or integer,nk_or integer, lnk_sep_or integer,rects_or integer, month character varying,year character varying,	 \r\n"
					+ "											 cmd_sus character varying,corp_sus character varying,\r\n"
					+ "											 div_sus character varying,bde_sus character varying,sus_no character varying)) np"
					+ qry
					 + "	GROUP BY arms_services ";

			stmt = c.prepareStatement(q);
			System.err.println("query for posted off jco or: \n" + stmt);
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
				list_Search.add(rs.getString("arms_services")); // 0
				list_Search.add(rs.getString("brig_and_above_offrs")); // 1
				list_Search.add(rs.getString("col_offrs")); // 2
				list_Search.add(rs.getString("col_ts_offrs")); // 3
				list_Search.add(rs.getString("lt_col_offrs")); // 4
				list_Search.add(rs.getString("maj_offrs")); // 5
				list_Search.add(rs.getString("capt_lt_offrs")); // 6 (not 7)
				list_Search.add(rs.getString("brig_and_above_mns_offrs")); // 7
				list_Search.add(rs.getString("col_mns_offrs")); // 8
				list_Search.add(rs.getString("lt_col_s_mns_offrs")); // 9
				list_Search.add(rs.getString("lt_col_ts_mns_offrs")); // 10
				list_Search.add(rs.getString("maj_mns_offrs")); // 11
				list_Search.add(rs.getString("capt_lt_mns_offrs")); // 12
				list_Search.add(rs.getString("sub_major_jco")); // 13
				list_Search.add(rs.getString("sub_jco")); // 14
				list_Search.add(rs.getString("nb_sub_jco")); // 15
				list_Search.add(rs.getString("warrant_officer_jco")); // 16
				list_Search.add(rs.getString("hav_or")); // 17
				list_Search.add(rs.getString("nk_or")); // 18
				list_Search.add(rs.getString("lnk_sep_or")); // 19
				list_Search.add(rs.getString("rects_or")); // 20

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;

	}

	public ArrayList<ArrayList<String>> iaff3009_Report_posted_civ(String month, String year, String comd_sus,
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
//			if (!month.equals("0")) {
//				qry += " where auth.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and auth.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and auth.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
//			q="SELECT SUM(auth.gp_a_gazetted) AS gp_a_gazetted, SUM(auth.gp_b_gazetted) AS gp_b_gazetted,\r\n"
//					+ "    SUM(auth.gp_b_non_gazetted) AS gp_b_non_gazetted,  SUM(auth.gp_c_non_gazetted) AS gp_c_non_gazetted, auth.remarks\r\n"
//					+ "FROM tb_psg_posted_civ_3009 AS auth\r\n"
//					+ "INNER JOIN tb_psg_iaff_3009_main_details AS md ON auth.sus_no = md.sus_no\r\n"
//					+ "        AND auth.year = md.year  AND auth.version = md.version  AND auth.month = md.month and md.status != 3\r\n" + qry
//					+ "GROUP BY auth.remarks";
			
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
			
			q = "select SUM(gp_a_gazetted) AS gp_a_gazetted, SUM(gp_b_gazetted) AS gp_b_gazetted, SUM(gp_b_non_gazetted) AS gp_b_non_gazetted,  SUM(gp_c_non_gazetted) AS gp_c_non_gazetted, remarks from (select distinct gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
					+ "					month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "					dblink('myconn',' select distinct auth.gp_a_gazetted, auth.gp_b_gazetted,auth.gp_b_non_gazetted, auth.gp_c_non_gazetted,auth.remarks,\r\n"
					+ "						   auth.month,auth.year,\r\n"
					+ "					 md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no \r\n"
					+ "					 from tb_psg_iaff_3009_main as v \r\n"
					+ "					 inner join tb_psg_posted_civ_3009 as auth \r\n"
					+ "					 on v.sus_no = auth.sus_no and v.year = auth.year and v.version=auth.version and v.month = auth.month\r\n"
					+ "					inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "					on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "					where  v.status != 3 ') \r\n"
					+ "					AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted integer,\r\n"
					+ "						 gp_c_non_gazetted integer, remarks character varying, month character varying,year character varying,						 \r\n"
					+ "						 cmd_sus character varying,corp_sus character varying,\r\n"
					+ "						 div_sus character varying,bde_sus character varying,sus_no character varying)) np \r\n"
					+ qry
					+ "GROUP BY remarks";

					
		
			stmt = c.prepareStatement(q);
			System.err.println("query for posted civ: \n" + stmt);
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
				// list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("gp_a_gazetted"));// 0
				list_Search.add(rs.getString("gp_b_gazetted"));// 1
				list_Search.add(rs.getString("gp_b_non_gazetted"));// 2
				list_Search.add(rs.getString("gp_c_non_gazetted"));// 3
				list_Search.add(rs.getString("remarks"));// 4

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
	
		return alist;

	}

	public ArrayList<ArrayList<String>> iaff3009_Report_rank_and_trade_wise_jco_or(String month, String year,
			String comd_sus, String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank) {

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
//			if (!month.equals("0")) {
//				qry += " where rt.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and rt.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and md.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
//			q="	SELECT  rt.arms_services, rt.trade, SUM(rt.sub_maj_jco) AS sub_maj_jco,  SUM(rt.sub_jco) AS sub_jco,SUM(rt.nb_sub_jco) AS nb_sub_jco,\r\n"
//					+ "    SUM(rt.warrant_officer_jco) AS warrant_officer_jco,SUM(rt.hav_or) AS hav_or, SUM(rt.nk_or) AS nk_or,\r\n"
//					+ "    SUM(rt.lnk_sep_or) AS lnk_sep_or, SUM(rt.rects_or) AS rects_or, SUM(rt.total) AS total\r\n"
//					+ "FROM  tb_psg_rank_and_trade_wise_jco_or_3009 AS rt\r\n"
//					+ "INNER JOIN tb_psg_iaff_3009_main_details AS md ON rt.sus_no = md.sus_no\r\n"
//					+ "        AND rt.year = md.year AND rt.version = md.version AND rt.month = md.month and  md.status != 3\r\n" + qry
//					+ "GROUP BY rt.arms_services, rt.trade";
					
			
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
			
			q = "select arms_services,trade, SUM(sub_maj_jco) AS sub_maj_jco,  SUM(sub_jco) AS sub_jco,SUM(nb_sub_jco) AS nb_sub_jco,\r\n"
					+ "							   SUM(warrant_officer_jco) AS warrant_officer_jco,SUM(hav_or) AS hav_or, SUM(nk_or) AS nk_or,\r\n"
					+ "						  SUM(lnk_sep_or) AS lnk_sep_or, SUM(rects_or) AS rects_or, SUM(total) AS total from (select distinct arms_services,trade,sub_maj_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,\r\n"
					+ "			   nk_or,lnk_sep_or,rects_or,total,month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "					dblink('myconn','	select distinct rt.arms_services,rt.trade,rt.sub_maj_jco,rt.sub_jco,\r\n"
					+ "					  rt.nb_sub_jco,rt.warrant_officer_jco,rt.hav_or,\r\n"
					+ "					 rt.nk_or,rt.lnk_sep_or,rt.rects_or,rt.total,rt.month,rt.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no  from  tb_psg_iaff_3009_main as v \r\n"
					+ "					 inner join tb_psg_rank_and_trade_wise_jco_or_3009  as rt\r\n"
					+ "					  on v.sus_no = rt.sus_no and v.year = rt.year and v.version=rt.version and v.month = rt.month\r\n"
					+ "					    inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "					    on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "					    where  v.status != 3') \r\n"
					+ "					AS t(arms_services character varying ,trade character varying,sub_maj_jco integer,sub_jco integer,nb_sub_jco integer,\r\n"
					+ "						 warrant_officer_jco integer,hav_or integer,\r\n"
					+ "			           nk_or integer,lnk_sep_or integer,rects_or integer,total integer, month character varying,year character varying,						 \r\n"
					+ "						 cmd_sus character varying,corp_sus character varying,\r\n"
					+ "						 div_sus character varying,bde_sus character varying,sus_no character varying)) np\r\n"
					+ qry
					+ "GROUP BY arms_services,trade";

			stmt = c.prepareStatement(q);
			System.err.println("query for rank_and_trade_wise_jco_or: \n" + stmt);
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

				// list_Search.add(String.valueOf(i));// 0
				list_Search.add(rs.getString("arms_services"));// 0
				list_Search.add(rs.getString("trade"));// 1
				list_Search.add(rs.getString("sub_maj_jco"));// 2
				list_Search.add(rs.getString("sub_jco"));// 3
				list_Search.add(rs.getString("nb_sub_jco"));// 4
				list_Search.add(rs.getString("warrant_officer_jco"));// 5
				list_Search.add(rs.getString("hav_or"));// 6
				list_Search.add(rs.getString("nk_or"));// 7
				list_Search.add(rs.getString("lnk_sep_or"));// 8
				list_Search.add(rs.getString("rects_or"));// 9
				list_Search.add(rs.getString("total"));// 10

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
		
		return alist;

	}

	public ArrayList<ArrayList<String>> iaff3009_Report_religious_denomination(String month, String year,
			String comd_sus, String corp_sus, String div_sus, String bde_sus, String unit_sus_no, String rank)

	{
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
//			if (!month.equals("0")) {
//				qry += " where rd.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and rd.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and rd.sus_no = ?  ";
//			}
//			if (!rank.equals("")) {
//				qry += " and upper(md.rank) = ? ";
//			}
			
//			q="SELECT rd.arms_services, rd.religion, SUM(rd.jcos_posted_str_incl_ere_pers) AS jcos_posted_str_incl_ere_pers,\r\n"
//					+ "    SUM(rd.or_posted_str_incl_ere_pers) AS or_posted_str_incl_ere_pers, SUM(rd.held_religious_teacher) AS held_religious_teacher,\r\n"
//					+ "    SUM(rd.auth_religious_teacher) AS auth_religious_teacher\r\n"
//					+ "FROM tb_psg_religious_denomination_3009 AS rd\r\n"
//					+ "INNER JOIN tb_psg_iaff_3009_main_details AS md ON rd.sus_no = md.sus_no\r\n"
//					+ "        AND rd.year = md.year AND rd.version = md.version AND rd.month = md.month and  md.status != 3\r\n" + qry
//					+ "GROUP BY rd.arms_services, rd.religion";
				
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
					

			q = "select arms_services, religion, SUM(jcos_posted_str_incl_ere_pers) AS jcos_posted_str_incl_ere_pers,\r\n"
					+ "    SUM(or_posted_str_incl_ere_pers) AS or_posted_str_incl_ere_pers, SUM(held_religious_teacher) AS held_religious_teacher,\r\n"
					+ "    SUM(auth_religious_teacher) AS auth_religious_teacher from (select distinct arms_services,religion,jcos_posted_str_incl_ere_pers,or_posted_str_incl_ere_pers,held_religious_teacher,\r\n"
					+ "			   auth_religious_teacher,month,year,cmd_sus,corp_sus,div_sus,bde_sus,sus_no from \r\n"
					+ "					dblink('myconn','select distinct rd.arms_services,rd.religion,rd.jcos_posted_str_incl_ere_pers,rd.or_posted_str_incl_ere_pers,\r\n"
					+ "				     	rd.held_religious_teacher,rd.auth_religious_teacher,rd.month,rd.year,md.cmd_sus,md.corp_sus,md.div_sus,md.bde_sus,md.sus_no  \r\n"
					+ "					    from  tb_psg_iaff_3009_main as v \r\n"
					+ "					    inner join tb_psg_religious_denomination_3009 as rd \r\n"
					+ "					    on v.sus_no = rd.sus_no and v.year = rd.year and v.version=rd.version and v.month = rd.month\r\n"
					+ "					    inner join  tb_psg_iaff_3009_main_details as md \r\n"
					+ "					    on v.sus_no = md.sus_no and v.year = md.year and v.version=md.version and v.month =md.month\r\n"
					+ "					    where  v.status != 3') \r\n"
					+ "					AS t(arms_services character varying ,religion character varying,jcos_posted_str_incl_ere_pers integer,\r\n"
					+ "						 or_posted_str_incl_ere_pers integer,held_religious_teacher integer,auth_religious_teacher integer,\r\n"
					+ "						 month character varying,year character varying, cmd_sus character varying,corp_sus character varying,\r\n"
					+ "						 div_sus character varying,bde_sus character varying,sus_no character varying)) np\r\n"
					+ qry
					+ "GROUP BY arms_services,religion";

			stmt = c.prepareStatement(q);
			System.err.println("query for religious_denomination: \n" + stmt);
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

				// list.add(String.valueOf(i));
				list_Search.add(rs.getString("arms_services"));// 0
				list_Search.add(rs.getString("religion"));// 1
				list_Search.add(rs.getString("jcos_posted_str_incl_ere_pers"));// 2
				list_Search.add(rs.getString("or_posted_str_incl_ere_pers"));// 3
				list_Search.add(rs.getString("held_religious_teacher"));// 4
				list_Search.add(rs.getString("auth_religious_teacher"));// 5

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

	public ArrayList<ArrayList<String>> iaff3009_Report_MainDetails(String month, String year, String version, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!month.equals("0")) {
				qry += " where m.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and m.year = ? ";
			}
			if (!version.equals("")) {
				qry += " and m.version = ? ";
			}

			if (!unit_sus_no.equals("")) {
				qry += " and m.sus_no = ?  ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and m.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and m.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and m.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and m.bde_sus = ? ";
			}

			q = "select distinct present_return_no,TO_CHAR(m.present_return_dt,'DD-MON-YYYY') as present_return_dt,m.we_pe_no,m.auth,m.held,\r\n"
					+ "						   TO_CHAR(TO_TIMESTAMP(month,'MM'),'MON') as month,m.year,m.cmd_sus,m.corp_sus,m.div_sus,m.bde_sus,m.sus_no,ud.unit_name\r\n"
					+ "					from tb_psg_iaff_3009_main_details m"
					+ "                left join tb_miso_orbat_unt_dtl ud on ud.sus_no = m.sus_no and ud.status_sus_no='Active'  \r\n" + qry;

			stmt = conn.prepareStatement(q);
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
				if (version != "") {
					stmt.setString(j, version);
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

			}

			int i = 0;
			
			//System.err.println(" box deatsils: \n" +stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list_Search = new ArrayList<String>();
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
	
	
	public ArrayList<ArrayList<String>> iaff3009_Report_getremarks(String month, String year, String version, String comd_sus,
			String corp_sus, String div_sus, String bde_sus, String unit_sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (!month.equals("0")) {
				qry += " where m.month = ? ";
			}
			if (!year.equals("")) {
				qry += " and m.year = ? ";
			}
			if (!version.equals("")) {
				qry += " and m.version = ? ";
			}

			if (!unit_sus_no.equals("")) {
				qry += " and m.sus_no = ?  ";
			}
			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
				qry += " and m.cmd_sus = ? ";
			}
			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
				qry += " and m.corp_sus = ? ";
			}
			if (!div_sus.equals("0") && !div_sus.equals("")) {
				qry += " and m.div_sus = ? ";
			}
			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
				qry += " and m.bde_sus = ? ";
			}

			q = "select distinct distributionlist,remarks\r\n"
					+ "					from tb_psg_iaff_3009_main_details m"
					+ "  \r\n" + qry;

			stmt = conn.prepareStatement(q);
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
				if (version != "") {
					stmt.setString(j, version);
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

			}

			int i = 0;
			
			System.err.println("remarks \n"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list_Search = new ArrayList<String>();
			
				list_Search.add(rs.getString("distributionlist"));// 0
				list_Search.add(rs.getString("remarks"));// 1

				alist.add(list_Search);
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


	public ArrayList<ArrayList<String>> iaff3009_Report_summary(String month, String year,
			String comd_sus, String corp_sus, String div_sus, String bde_sus, String unit_sus_no)

	{
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
//			if (!month.equals("0")) {
//				qry += " where ds.month = ? ";
//			}
//			if (!year.equals("")) {
//				qry += " and ds.year = ? ";
//			}
//			if (!comd_sus.equals("0") && !comd_sus.equals("")) {
//				qry += " and md.cmd_sus = ? ";
//			}
//			if (!corp_sus.equals("0") && !corp_sus.equals("")) {
//				qry += " and md.corp_sus = ? ";
//			}
//			if (!div_sus.equals("0") && !div_sus.equals("")) {
//				qry += " and md.div_sus = ? ";
//			}
//			if (!bde_sus.equals("0") && !bde_sus.equals("")) {
//				qry += " and md.bde_sus = ? ";
//			}
//			if (!unit_sus_no.equals("")) {
//				qry += " and ds.sus_no = ?  ";
//			}
			
			
//			q="select distinct  sum(CAST (ds.offrs_auth AS integer)) as offrs_auth,\r\n"
//					+ "                   sum(CAST ( ds.offrs_posted AS integer)) as offrs_posted\r\n"
//					+ "				   ,sum(CAST ( ds.offrs_sur AS integer)) as offrs_sur,\r\n"
//					+ "				   sum(CAST ( ds.offrs_defi AS integer)) as offrs_defi,sum(CAST ( ds.jco_auth AS integer)) as jco_auth,\r\n"
//					+ "				   sum(cast(ds.jco_posted as integer)) as jco_posted,sum(cast(ds.jco_sur as integer)) as jco_sur,\r\n"
//					+ "				   sum(cast(ds.jco_defi as integer)) as jco_defi, sum(cast(ds.or_auth as integer)) as or_auth,\r\n"
//					+ "				   sum(cast(ds.or_posted as integer)) as or_posted,sum(cast(ds.or_sur as integer)) as or_sur,\r\n"
//					+ "				  sum(cast(ds.or_defi as integer)) as or_defi,sum(cast(ds.civ_auth as integer)) as civ_auth,\r\n"
//					+ "				  sum(cast(ds.civ_posted as integer)) as civ_posted,\r\n"
//					+ "				  sum(cast(ds.civ_sur as integer)) as civ_sur,sum(cast(ds.civ_defi as integer)) as civ_defi\r\n"
//					+ "						from  tb_psg_summary_3009 as ds  \r\n"
//					+ "							  inner join  tb_psg_iaff_3009_main_details as md \r\n"
//					+ "								 on ds.sus_no = md.sus_no and ds.year = md.year and ds.version=md.version and ds.month =md.month and md.status !=3 \r\n" +qry;
//					

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
			
			q="SELECT distinct  sum(CAST (offrs_auth AS integer)) as offrs_auth,\r\n"
					+ "					                    sum(CAST ( offrs_posted AS integer)) as offrs_posted\r\n"
					+ "					 				   ,sum(CAST ( offrs_sur AS integer)) as offrs_sur,\r\n"
					+ "					 				   sum(CAST ( offrs_defi AS integer)) as offrs_defi,sum(CAST ( jco_auth AS integer)) as jco_auth,\r\n"
					+ "					 				   sum(cast(jco_posted as integer)) as jco_posted,sum(cast(jco_sur as integer)) as jco_sur,\r\n"
					+ "					 				   sum(cast(jco_defi as integer)) as jco_defi, sum(cast(or_auth as integer)) as or_auth,\r\n"
					+ "					 				   sum(cast(or_posted as integer)) as or_posted,sum(cast(or_sur as integer)) as or_sur,\r\n"
					+ "					 				  sum(cast(or_defi as integer)) as or_defi,sum(cast(civ_auth as integer)) as civ_auth,\r\n"
					+ "					 				  sum(cast(civ_posted as integer)) as civ_posted,\r\n"
					+ "					 				  sum(cast(civ_sur as integer)) as civ_sur,sum(cast(civ_defi as integer)) as civ_defi\r\n"
					+ "  FROM (\r\n"
					+ "        SELECT DISTINCT offrs_auth,\r\n"
					+ "               offrs_posted,\r\n"
					+ "               offrs_sur,\r\n"
					+ "               offrs_defi,\r\n"
					+ "               jco_auth,\r\n"
					+ "               jco_posted,\r\n"
					+ "               jco_sur,\r\n"
					+ "               jco_defi,\r\n"
					+ "               or_auth,\r\n"
					+ "               or_posted,\r\n"
					+ "               or_sur,\r\n"
					+ "               or_defi,\r\n"
					+ "               civ_auth,\r\n"
					+ "               civ_posted,\r\n"
					+ "               civ_sur,\r\n"
					+ "               civ_defi,\r\n"
					+ "               MONTH,\r\n"
					+ "               YEAR,\r\n"
					+ "               sus_no,\r\n"
					+ "               cmd_sus,\r\n"
					+ "               corp_sus,\r\n"
					+ "               div_sus,\r\n"
					+ "               bde_sus\r\n"
			
					+ "          FROM dblink('myconn','SELECT DISTINCT (CAST (ds.offrs_auth AS integer)) AS offrs_auth,\r\n"
					+ "     (CAST (ds.offrs_posted AS integer)) AS offrs_posted ,\r\n"
					+ "     (CAST (ds.offrs_sur AS integer)) AS offrs_sur,\r\n"
					+ "     (CAST (ds.offrs_defi AS integer)) AS offrs_defi,\r\n"
					+ "     (CAST (ds.jco_auth AS integer)) AS jco_auth,\r\n"
					+ "     (cast(ds.jco_posted AS integer)) AS jco_posted,\r\n"
					+ "     (cast(ds.jco_sur AS integer)) AS jco_sur,\r\n"
					+ "     (cast(ds.jco_defi AS integer)) AS jco_defi,\r\n"
					+ "     (cast(ds.or_auth AS integer)) AS or_auth,\r\n"
					+ "     (cast(ds.or_posted AS integer)) AS or_posted,\r\n"
					+ "     (cast(ds.or_sur AS integer)) AS or_sur,\r\n"
					+ "     (cast(ds.or_defi AS integer)) AS or_defi,\r\n"
					+ "     (cast(ds.civ_auth AS integer)) AS civ_auth,\r\n"
					+ "     (cast(ds.civ_posted AS integer)) AS civ_posted,\r\n"
					+ "     (cast(ds.civ_sur AS integer)) AS civ_sur,\r\n"
					+ "     (cast(ds.civ_defi AS integer)) AS civ_defi,\r\n"
					+ "       ds.month,\r\n"
					+ "       ds.year,\r\n"
					+ "       ds.sus_no,\r\n"
					+ "       md.cmd_sus,\r\n"
					+ "       md.corp_sus,\r\n"
					+ "       md.div_sus,\r\n"
					+ "       md.bde_sus\r\n"
					+ "      \r\n"
					+ "  FROM tb_psg_iaff_3009_main AS v\r\n"
					+ " INNER JOIN tb_psg_summary_3009 AS ds\r\n"
					+ "    ON v.sus_no = ds.sus_no\r\n"
					+ "   AND v.year = ds.year\r\n"
					+ "   AND v.version = ds.version\r\n"
					+ "   AND v.month = ds.month\r\n"
					+ " INNER JOIN tb_psg_iaff_3009_main_details AS md\r\n"
					+ "    ON v.sus_no = md.sus_no\r\n"
					+ "   AND v.year = md.year\r\n"
					+ "   AND v.version = md.version\r\n"
					+ "   AND v.month = md.month\r\n"
					+ " WHERE v.status != 3\r\n"
					+ "     ') AS t(offrs_auth integer,offrs_posted integer,offrs_sur integer,\r\n"
					+ "             offrs_defi integer, jco_auth integer,jco_posted integer,\r\n"
					+ "             jco_sur integer,jco_defi integer, or_auth integer,\r\n"
					+ "             or_posted integer, or_sur integer, or_defi integer ,\r\n"
					+ "             civ_auth integer,civ_posted integer, civ_sur integer,\r\n"
					+ "             civ_defi integer, MONTH CHARACTER varying,YEAR CHARACTER varying,sus_no CHARACTER varying,\r\n"
					+ "             cmd_sus CHARACTER varying,corp_sus CHARACTER varying,\r\n"
					+ "             div_sus CHARACTER varying,bde_sus CHARACTER varying)\r\n"
					+ "       ) np" +qry;


			stmt = c.prepareStatement(q);
			System.err.println("query for Report_summary: \n" + stmt);
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
				
			}

			rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 0;
			while (rs.next()) {
				ArrayList<String> list_Search = new ArrayList<String>();
				i++;

				// list.add(String.valueOf(i));
				list_Search.add(rs.getString("offrs_auth"));// 0
				list_Search.add(rs.getString("offrs_posted"));// 1
				list_Search.add(rs.getString("offrs_sur"));// 2
				list_Search.add(rs.getString("offrs_defi"));// 3
				list_Search.add(rs.getString("jco_auth"));// 4
				list_Search.add(rs.getString("jco_posted"));// 5
				list_Search.add(rs.getString("jco_sur"));// 6
				list_Search.add(rs.getString("jco_defi"));// 7
				list_Search.add(rs.getString("or_auth"));// 8
				list_Search.add(rs.getString("or_posted"));// 9
				list_Search.add(rs.getString("or_sur"));// 10
				list_Search.add(rs.getString("or_defi"));// 11
				list_Search.add(rs.getString("civ_auth"));// 12
				list_Search.add(rs.getString("civ_posted"));// 13
				list_Search.add(rs.getString("civ_sur"));// 14
				list_Search.add(rs.getString("civ_defi"));// 15

				alist.add(list_Search);
			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
	
		return alist;

	}
	
	
	public ArrayList<ArrayList<String>> get3009Report(String month, String year, String comd_sus,
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
          			+ "         FROM tb_psg_iaff_3009_main a \r\n"
          			+ "         WHERE a.sus_no = b.sus_no \r\n"
          			+ "           AND a.month =?\r\n"
          			+ "           AND a.year = ?\r\n"
          			+ "        ),\r\n"
          			+ "        -1\r\n"
          			+ "    ) AS status,\r\n"
          			+ " COALESCE((select a.version from tb_psg_iaff_3009_main a where a.sus_no = b.sus_no 	AND a.month=? AND a.year=?),'0') as version\r\n"
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
			
                     
			System.err.println("3009 report: \n\n"+stmt);
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

}
