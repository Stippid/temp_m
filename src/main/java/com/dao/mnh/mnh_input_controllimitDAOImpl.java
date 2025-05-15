package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class mnh_input_controllimitDAOImpl implements mnh_input_controllimitDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> contol_limit_report(String command1, String category1, String disease_type1,
			String disease_principal1, String disease_subtype1, String disease_mmr1,String block_description1) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q2 = "";
		String q1 = "";
		String q3 = "";
		String q4 = "";
		String q5 = "";
		String q6 = "";
		String q7 = "";
		String qry1="";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if((disease_mmr1.equals("EFFECTS OF ALTITUDE") || disease_mmr1.equals("EFFECTS OF HEAT")
	            	|| disease_mmr1.equals("EFFECTS OF COLD")  || disease_mmr1.equals("INJURIES NEA")))
				{
	        		qry1 = " inner join  tb_med_d_disease_cause c on (case when (l.icd_cause_code1d is null or l.icd_cause_code1d = '') then "
	        				+ " l.icd_cause_code1a else l.icd_cause_code1d end) = c.icd_code::text";
				}
	            else   {
	            	qry1 = " inner join  tb_med_d_disease_cause c on (case when (l.diagnosis_code1d is null or l.diagnosis_code1d = '') then "
	        				+ " l.diagnosis_code1a else l.diagnosis_code1d end) = c.icd_code::text ";
	            }

			
			
			if (!category1.equals("-1")) {
				q2 += " where m3.category=?";
			}
			if (!command1.equals("ALL")) {
				q1 += " and m3.command like substr(?,1,1)";
			}
			
			if (!disease_principal1.equals("-1")) {
				q3 += " and m3.disease_principal=?";
			}
			if (!disease_type1.equals("-1")) {
				q4 += " and m3.disease_type=?";
			}
			if (!disease_subtype1.equals("-1")) {
				q5 += " and m3.disease_subtype=?";
			}
			if (!disease_mmr1.equals("-1")) {
				q6 += " and m3.disease_mmr=?";
			}
			if (!block_description1.equals("-1")) {
				q7 += " and m3.block_description=?";
			}
			
			 	if (category1.equals("JCO")) {
				q = " SELECT\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.january) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
						"        END AS january,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.february) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
						"        END AS february,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.march) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.march) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
						"        END AS march,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.april) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.april) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
						"        END AS april,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.may) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.may) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
						"        END AS may,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.june) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.june) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
						"        END AS june,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.july) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.july) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
						"        END AS july,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.august) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.august) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
						"        END AS august,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.september) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.september) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
						"        END AS september,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.october) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.october) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
						"        END AS october,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.november) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.november) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
						"        END AS november,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.december) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.december) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
						"        END AS december,\n" + 
						"    m3.year\n" + 
						"   FROM ( SELECT m2.year,\n" + 
						"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) AS rate_jco,\n" + 
						"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q1'::text) AS qtr_jco1,\n" + 
						"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q2'::text) AS qtr_jco2,\n" + 
						"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q3'::text) AS qtr_jco3,\n" + 
						"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q4'::text) AS qtr_jco4,\n" + 
						"            sum(m2.january) AS january,\n" + 
						"            sum(m2.february) AS february,\n" + 
						"            sum(m2.march) AS march,\n" + 
						"            sum(m2.april) AS april,\n" + 
						"            sum(m2.may) AS may,\n" + 
						"            sum(m2.june) AS june,\n" + 
						"            sum(m2.july) AS july,\n" + 
						"            sum(m2.august) AS august,\n" + 
						"            sum(m2.september) AS september,\n" + 
						"            sum(m2.october) AS october,\n" + 
						"            sum(m2.november) AS november,\n" + 
						"            sum(m2.december) AS december,\n" + 
						"            m2.command,\n" + 
						"            m2.category,\n" + 
						"            m2.diagnosis_code1a,\n" + 
						"            m2.diagnosis_code1d,\n" + 
						"			 m2.disease_principal,\n" + 
						"			 m2.disease_mmr,\n" + 
						"			 m2.disease_type,\n" + 
						"			 m2.disease_subtype,\n" + 
						"			 m2.block_description\n" + 
						"           FROM ( SELECT DISTINCT sum(m1.january) AS january,\n" + 
						"                    sum(m1.february) AS february,\n" + 
						"                    sum(m1.march) AS march,\n" + 
						"                    sum(m1.april) AS april,\n" + 
						"                    sum(m1.may) AS may,\n" + 
						"                    sum(m1.june) AS june,\n" + 
						"                    sum(m1.july) AS july,\n" + 
						"                    sum(m1.august) AS august,\n" + 
						"                    sum(m1.september) AS september,\n" + 
						"                    sum(m1.october) AS october,\n" + 
						"                    sum(m1.november) AS november,\n" + 
						"                    sum(m1.december) AS december,\n" + 
						"                    m1.jco,\n" + 
						"                    m1.ort,\n" + 
						"                    m1.dsc_jco,\n" + 
						"                    m1.dsc_or,\n" + 
						"                    m1.rect,\n" + 
						"                    m1.qtr,\n" + 
						"                    m1.command,\n" + 
						"                    m1.category,\n" + 
						"                    m1.diagnosis_code1a,\n" + 
						"                    m1.diagnosis_code1d,\n" + 
						"                    m1.year,\n" + 
						"					 m1.disease_principal,\n" + 
						"					m1.disease_mmr,\n" + 
						"					m1.disease_type,\n" + 
						"					m1.disease_subtype,\n" + 
						"					m1.block_description\n" + 
						"                   FROM (select * from med_control_limit_view_temp l "+ qry1 +") m1\n" + 
						"                  GROUP BY m1.jco, m1.ort, m1.dsc_jco, m1.dsc_or,m1.disease_principal,m1.disease_mmr,m1.disease_type,m1.disease_subtype,\n" + 
						"		 m1.block_description ,m1.rect, m1.qtr, m1.year, m1.command, m1.category, m1.diagnosis_code1a, m1.diagnosis_code1d) m2\n" + 
						"          GROUP BY m2.year, m2.command, m2.category, m2.diagnosis_code1a,m2.disease_principal,m2.disease_mmr,m2.disease_type,m2.disease_subtype,\n" + 
						"		 m2.block_description, m2.diagnosis_code1d) m3 " + q2 +" "+ q1  + q3 +" "+ q4 + q5 +" "+ q6 + q7 +" \n" + 
						"		  \n" + 
						"  GROUP BY m3.qtr_jco1, m3.qtr_jco2, m3.qtr_jco3, m3.qtr_jco4, m3.year\n" + 
						"  ORDER BY m3.year";
			stmt = conn.prepareStatement(q);
			int j=1;
			
			if (!category1.equals("-1")) {
				  stmt.setString(j, category1);
				  j+=1;
				}
				if (!command1.equals("ALL")) {
				  stmt.setString(j, command1);
				  j+=1;
				}
				
				if (!disease_principal1.equals("-1")) {
					  stmt.setString(j, disease_principal1);
					  j+=1;
				}
				if (!disease_type1.equals("-1")) {
					 stmt.setString(j, disease_type1);
					 j+=1;
				}
				if (!disease_subtype1.equals("-1")) {
					 stmt.setString(j, disease_subtype1);
					 j+=1;
				}
			 
				if (!disease_mmr1.equals("-1")) {
					 stmt.setString(j, disease_mmr1);
					 j+=1;
				}
				if (!block_description1.equals("-1")) {
					 stmt.setString(j, block_description1);
					 j+=1;
				}
			}
			else {
				q=" SELECT\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.january) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS january,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.february) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS february,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.march) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.march) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS march,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.april) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.april) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS april,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.may) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.may) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS may,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.june) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.june) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS june,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.july) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.july) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS july,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.august) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.august) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS august,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.september) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.september) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS september,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.october) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.october) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS october,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.november) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.november) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS november,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.december) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.december) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS december,\n" + 
						"    m3.year\n" + 
						"   FROM (SELECT year,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q1' ) as qtr_off1,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q2' ) as qtr_off2,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q3' ) as qtr_off3,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q4' ) as qtr_off4,\n" + 
						"\n" + 
						"sum(january) as january,\n" + 
						"sum(february) as february,  \n" + 
						"sum(march) as march, \n" + 
						"sum(april) as april, \n" + 
						"sum(may) as may, \n" + 
						"sum(june) as june, \n" + 
						"sum(july) as july,  \n" + 
						"sum(august) as august, \n" + 
						"sum(september) as september,\n" + 
						"sum(october) as october, \n" + 
						"sum(november) as november, \n" + 
						"sum(december) as december,\n" + 
						"	m2.command,\n" + 
						"	m2.category,\n" + 
						"	m2.diagnosis_code1a,\n" + 
						"	m2.diagnosis_code1d,\n" + 
						"	m2.disease_principal,\n" + 
						"	m2.disease_mmr,\n" + 
						"	m2.disease_type,\n" + 
						"	m2.disease_subtype,\n" + 
						"	m2.block_description\n" + 
						"from\n" + 
						"(select \n" + 
						"distinct sum(january) as january,\n" + 
						"sum(february) as february,  \n" + 
						"sum(march) as march, \n" + 
						"sum(april) as april, \n" + 
						"sum(may) as may, \n" + 
						"sum(june) as june, \n" + 
						"sum(july) as july,  \n" + 
						"sum(august) as august, \n" + 
						"sum(september) as september,\n" + 
						"sum(october) as october, \n" + 
						"sum(november) as november, \n" + 
						"sum(december) as december,\n" + 
						"m1.off_male,m1.off_female,m1.cadet,m1.lady_cadet,m1.mns,m1.mns_cadet,\n" + 
						"qtr,\n" + 
						"year,m1.command,\n" + 
						"	m1.category,\n" + 
						"	m1.diagnosis_code1a,\n" + 
						"	m1.diagnosis_code1d,\n" + 
						"	m1.disease_principal,\n" + 
						"	m1.disease_mmr,\n" + 
						"	m1.disease_type,\n" + 
						"	m1.disease_subtype,\n" + 
						"	m1.block_description\n" + 
						"from (select * from med_control_limit_view_temp l "+ qry1 +") m1\n" + 
						"group by m1.off_male,m1.off_female,m1.cadet,m1.lady_cadet,m1.mns,m1.mns_cadet,\n" + 
						"qtr,year,m1.command,m1.category,m1.diagnosis_code1a,m1.diagnosis_code1d,m1.disease_principal,m1.disease_mmr,m1.disease_type,\n" + 
						" m1.disease_subtype,m1.block_description) m2\n" + 
						"group by year,m2.command,m2.category,m2.diagnosis_code1a,m2.diagnosis_code1d,m2.disease_principal,m2.disease_mmr,m2.disease_type,m2.disease_subtype,\n" + 
						"		 m2.block_description) m3 " + q2 +" "+ q1  + q3 +" "+ q4 + q5 +" "+ q6 + q7 +"  \n" + 
						"		 \n" + 
						"  GROUP BY m3.qtr_off1, m3.qtr_off2, m3.qtr_off3, m3.qtr_off4, m3.year\n" + 
						"  ORDER BY m3.year";
				
				stmt = conn.prepareStatement(q);
				int j=1;
				if (!category1.equals("-1")) {
					  stmt.setString(j, category1);
					  j+=1;
					}
					if (!command1.equals("ALL")) {
					  stmt.setString(j, command1);
					  j+=1;
					}
					
					if (!disease_principal1.equals("-1")) {
						  stmt.setString(j, disease_principal1);
						  j+=1;
					}
					if (!disease_type1.equals("-1")) {
						 stmt.setString(j, disease_type1);
						 j+=1;
					}
					if (!disease_subtype1.equals("-1")) {
						 stmt.setString(j, disease_subtype1);
						 j+=1;
					}
					if (!disease_mmr1.equals("-1")) {
						 stmt.setString(j, disease_mmr1);
						 j+=1;
					}
					if (!block_description1.equals("-1")) {
						 stmt.setString(j, block_description1);
						 j+=1;
					}
			}
			 

			ResultSet rs = stmt.executeQuery();
			 
			while (rs.next()) {
				 
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("year"));
				alist.add(rs.getString("january"));
				alist.add(rs.getString("february"));
				alist.add(rs.getString("march"));
				alist.add(rs.getString("april"));
				alist.add(rs.getString("may"));
				alist.add(rs.getString("june"));
				alist.add(rs.getString("july"));
				alist.add(rs.getString("august"));
				alist.add(rs.getString("september"));
				alist.add(rs.getString("october"));
				alist.add(rs.getString("november"));
				alist.add(rs.getString("december"));
				list.add(alist);
				

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
				}
			}
		}

	 

		return list;

	}

	public ArrayList<ArrayList<String>> contol_limit_report_cal(String command1, String category1, String disease_type1,
			String disease_principal1, String disease_subtype1, String disease_mmr1,String block_description1) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q2 = "";
		String q1 = "";
		String q3 = "";
		String q4 = "";
		String q5 = "";
		String q6 = "";
		String q7 = "";
		String qry1 = "";
		
		
		String qry = "";
	 
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if((disease_mmr1.equals("EFFECTS OF ALTITUDE") || disease_mmr1.equals("EFFECTS OF HEAT")
	            	|| disease_mmr1.equals("EFFECTS OF COLD")  || disease_mmr1.equals("INJURIES NEA")))
				{
	        		qry1 = " inner join  tb_med_d_disease_cause c on (case when (l.icd_cause_code1d is null or l.icd_cause_code1d = '') then "
	        				+ " l.icd_cause_code1a else l.icd_cause_code1d end) = c.icd_code::text";
				}
	            else   {
	            	qry1 = " inner join  tb_med_d_disease_cause c on (case when (l.diagnosis_code1d is null or l.diagnosis_code1d = '') then "
	        				+ " l.diagnosis_code1a else l.diagnosis_code1d end) = c.icd_code::text ";
	            }
			
			
			if (!category1.equals("-1")) {
				q2 += " where m3.category=?";
			}
			if (!command1.equals("ALL")) {
				q1 += " and m3.command like substr(?,1,1)";
			}
			
			if (!disease_principal1.equals("-1")) {
				q3 += " and m3.disease_principal=?";
			}
			if (!disease_type1.equals("-1")) {
				q4 += " and m3.disease_type=?";
			}
			if (!disease_subtype1.equals("-1")) {
				q5 += " and m3.disease_subtype=?";
			}
			if (!disease_mmr1.equals("-1")) {
				q6 += " and m3.disease_mmr=?";
			}
			if (!block_description1.equals("-1")) {
				q7 += " and m3.block_description=?";
			}
		
			if (category1.equals("JCO")) {
			q = "select max(january)-min(january) as january,max(february)-min(february) as february,max(march)-min(march) as march,\n" + 
					"														max(april)-min(april) as april,max(may)-min(may) as may,max(june)-min(june) as june,\n" + 
					"														max(july)-min(july) as july,max(august)-min(august) as august,\n" + 
					"														max(september)-min(september) as september,max(october)-min(october) as october,\n" + 
					"													max(november)-min(november) as november,max(december) as december from ( SELECT\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.january) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
					"        END AS january,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.february) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
					"        END AS february,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco1::integer = 0 OR m3.qtr_jco1 IS NULL THEN round(sum(m3.march) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.march) * 1000::numeric / m3.qtr_jco1::numeric, 2)\n" + 
					"        END AS march,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.april) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.april) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
					"        END AS april,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.may) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.may) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
					"        END AS may,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco2::integer = 0 OR m3.qtr_jco2 IS NULL THEN round(sum(m3.june) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.june) * 1000::numeric / m3.qtr_jco2::numeric, 2)\n" + 
					"        END AS june,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.july) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.july) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
					"        END AS july,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.august) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.august) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
					"        END AS august,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco3::integer = 0 OR m3.qtr_jco3 IS NULL THEN round(sum(m3.september) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.september) * 1000::numeric / m3.qtr_jco3::numeric, 2)\n" + 
					"        END AS september,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.october) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.october) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
					"        END AS october,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.november) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.november) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
					"        END AS november,\n" + 
					"        CASE\n" + 
					"            WHEN m3.qtr_jco4::integer = 0 OR m3.qtr_jco4 IS NULL THEN round(sum(m3.december) * 1000::numeric / 1::numeric, 2)\n" + 
					"            ELSE round(sum(m3.december) * 1000::numeric / m3.qtr_jco4::numeric, 2)\n" + 
					"        END AS december,\n" + 
					"    m3.year\n" + 
					"   FROM ( SELECT m2.year,\n" + 
					"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) AS rate_jco,\n" + 
					"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q1'::text) AS qtr_jco1,\n" + 
					"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q2'::text) AS qtr_jco2,\n" + 
					"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q3'::text) AS qtr_jco3,\n" + 
					"            sum(m2.jco + m2.ort + m2.dsc_jco + m2.dsc_or + m2.rect) FILTER (WHERE m2.qtr::text = 'q4'::text) AS qtr_jco4,\n" + 
					"            sum(m2.january) AS january,\n" + 
					"            sum(m2.february) AS february,\n" + 
					"            sum(m2.march) AS march,\n" + 
					"            sum(m2.april) AS april,\n" + 
					"            sum(m2.may) AS may,\n" + 
					"            sum(m2.june) AS june,\n" + 
					"            sum(m2.july) AS july,\n" + 
					"            sum(m2.august) AS august,\n" + 
					"            sum(m2.september) AS september,\n" + 
					"            sum(m2.october) AS october,\n" + 
					"            sum(m2.november) AS november,\n" + 
					"            sum(m2.december) AS december,\n" + 
					"            m2.command,\n" + 
					"            m2.category,\n" + 
					"            m2.diagnosis_code1a,\n" + 
					"            m2.diagnosis_code1d,\n" + 
					"			 m2.disease_principal,\n" + 
					"			 m2.disease_mmr,\n" + 
					"			 m2.disease_type,\n" + 
					"			 m2.disease_subtype,\n" + 
					"			 m2.block_description\n" + 
					"           FROM ( SELECT DISTINCT sum(m1.january) AS january,\n" + 
					"                    sum(m1.february) AS february,\n" + 
					"                    sum(m1.march) AS march,\n" + 
					"                    sum(m1.april) AS april,\n" + 
					"                    sum(m1.may) AS may,\n" + 
					"                    sum(m1.june) AS june,\n" + 
					"                    sum(m1.july) AS july,\n" + 
					"                    sum(m1.august) AS august,\n" + 
					"                    sum(m1.september) AS september,\n" + 
					"                    sum(m1.october) AS october,\n" + 
					"                    sum(m1.november) AS november,\n" + 
					"                    sum(m1.december) AS december,\n" + 
					"                    m1.jco,\n" + 
					"                    m1.ort,\n" + 
					"                    m1.dsc_jco,\n" + 
					"                    m1.dsc_or,\n" + 
					"                    m1.rect,\n" + 
					"                    m1.qtr,\n" + 
					"                    m1.command,\n" + 
					"                    m1.category,\n" + 
					"                    m1.diagnosis_code1a,\n" + 
					"                    m1.diagnosis_code1d,\n" + 
					"                    m1.year,\n" + 
					"					 m1.disease_principal,\n" + 
					"					m1.disease_mmr,\n" + 
					"					m1.disease_type,\n" + 
					"					m1.disease_subtype,\n" + 
					"					m1.block_description\n" + 
					"                   FROM (select * from med_control_limit_view_temp l "+ qry1 + ") m1\n" + 
					"                  GROUP BY m1.jco, m1.ort, m1.dsc_jco, m1.dsc_or,m1.disease_principal,m1.disease_mmr,m1.disease_type,m1.disease_subtype,\n" + 
					"		 m1.block_description ,m1.rect, m1.qtr, m1.year, m1.command, m1.category, m1.diagnosis_code1a, m1.diagnosis_code1d) m2\n" + 
					"          GROUP BY m2.year, m2.command, m2.category, m2.diagnosis_code1a,m2.disease_principal,m2.disease_mmr,m2.disease_type,m2.disease_subtype,\n" + 
					"		 m2.block_description, m2.diagnosis_code1d) m3 " + q2 +" "+ q1  + q3 +" "+ q4 + q5 +" "+ q6 + q7 + "\n" + 
					"		  \n" + 
					"  GROUP BY m3.qtr_jco1, m3.qtr_jco2, m3.qtr_jco3, m3.qtr_jco4, m3.year\n" + 
					"  ORDER BY m3.year)l";

			stmt = conn.prepareStatement(q);
			int j=1;
			if (!category1.equals("-1")) {
				  stmt.setString(j, category1);
				  j+=1;
				}
				if (!command1.equals("ALL")) {
				  stmt.setString(j, command1);
				  j+=1;
				}
				
				if (!disease_principal1.equals("-1")) {
					  stmt.setString(j, disease_principal1);
					  j+=1;
				}
				if (!disease_type1.equals("-1")) {
					 stmt.setString(j, disease_type1);
					 j+=1;
				}
				if (!disease_subtype1.equals("-1")) {
					 stmt.setString(j, disease_subtype1);
					 j+=1;
				}
				if (!disease_mmr1.equals("-1")) {
					 stmt.setString(j, disease_mmr1);
					 j+=1;
				}
				if (!block_description1.equals("-1")) {
					 stmt.setString(j, block_description1);
					 j+=1;
				}
				
			}
			else {
				q="select max(january)-min(january) as january,max(february)-min(february) as february,max(march)-min(march) as march,\n" + 
						"														max(april)-min(april) as april,max(may)-min(may) as may,max(june)-min(june) as june,\n" + 
						"														max(july)-min(july) as july,max(august)-min(august) as august,\n" + 
						"														max(september)-min(september) as september,max(october)-min(october) as october,\n" + 
						"													max(november)-min(november) as november,max(december) as december from ( SELECT\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.january) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS january,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.february) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.january) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS february,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off1::integer = 0 OR m3.qtr_off1 IS NULL THEN round(sum(m3.march) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.march) * 1000::numeric / m3.qtr_off1::numeric, 2)\n" + 
						"        END AS march,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.april) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.april) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS april,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.may) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.may) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS may,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off2::integer = 0 OR m3.qtr_off2 IS NULL THEN round(sum(m3.june) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.june) * 1000::numeric / m3.qtr_off2::numeric, 2)\n" + 
						"        END AS june,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.july) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.july) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS july,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.august) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.august) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS august,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off3::integer = 0 OR m3.qtr_off3 IS NULL THEN round(sum(m3.september) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.september) * 1000::numeric / m3.qtr_off3::numeric, 2)\n" + 
						"        END AS september,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.october) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.october) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS october,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.november) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.november) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS november,\n" + 
						"        CASE\n" + 
						"            WHEN m3.qtr_off4::integer = 0 OR m3.qtr_off4 IS NULL THEN round(sum(m3.december) * 1000::numeric / 1::numeric, 2)\n" + 
						"            ELSE round(sum(m3.december) * 1000::numeric / m3.qtr_off4::numeric, 2)\n" + 
						"        END AS december,\n" + 
						"    m3.year\n" + 
						"   FROM (SELECT year,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q1' ) as qtr_off1,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q2' ) as qtr_off2,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q3' ) as qtr_off3,\n" + 
						"sum(m2.off_male+m2.off_female+m2.cadet+m2.lady_cadet+m2.mns+m2.mns_cadet) filter (where qtr = 'q4' ) as qtr_off4,\n" + 
						"\n" + 
						"sum(january) as january,\n" + 
						"sum(february) as february,  \n" + 
						"sum(march) as march, \n" + 
						"sum(april) as april, \n" + 
						"sum(may) as may, \n" + 
						"sum(june) as june, \n" + 
						"sum(july) as july,  \n" + 
						"sum(august) as august, \n" + 
						"sum(september) as september,\n" + 
						"sum(october) as october, \n" + 
						"sum(november) as november, \n" + 
						"sum(december) as december,\n" + 
						"	m2.command,\n" + 
						"	m2.category,\n" + 
						"	m2.diagnosis_code1a,\n" + 
						"	m2.diagnosis_code1d,\n" + 
						"	m2.disease_principal,\n" + 
						"	m2.disease_mmr,\n" + 
						"	m2.disease_type,\n" + 
						"	m2.disease_subtype,\n" + 
						"	m2.block_description\n" + 
						"from\n" + 
						"(select \n" + 
						"distinct sum(january) as january,\n" + 
						"sum(february) as february,  \n" + 
						"sum(march) as march, \n" + 
						"sum(april) as april, \n" + 
						"sum(may) as may, \n" + 
						"sum(june) as june, \n" + 
						"sum(july) as july,  \n" + 
						"sum(august) as august, \n" + 
						"sum(september) as september,\n" + 
						"sum(october) as october, \n" + 
						"sum(november) as november, \n" + 
						"sum(december) as december,\n" + 
						"m1.off_male,m1.off_female,m1.cadet,m1.lady_cadet,m1.mns,m1.mns_cadet,\n" + 
						"qtr,\n" + 
						"year,m1.command,\n" + 
						"	m1.category,\n" + 
						"	m1.diagnosis_code1a,\n" + 
						"	m1.diagnosis_code1d,\n" + 
						"	m1.disease_principal,\n" + 
						"	m1.disease_mmr,\n" + 
						"	m1.disease_type,\n" + 
						"	m1.disease_subtype,\n" + 
						"	m1.block_description\n" + 
						"from (select * from med_control_limit_view_temp l "+ qry1 +") m1\n" + 
						"group by m1.off_male,m1.off_female,m1.cadet,m1.lady_cadet,m1.mns,m1.mns_cadet,\n" + 
						"qtr,year,m1.command,m1.category,m1.diagnosis_code1a,m1.diagnosis_code1d,m1.disease_principal,m1.disease_mmr,m1.disease_type,\n" + 
						" m1.disease_subtype,m1.block_description) m2\n" + 
						"group by year,m2.command,m2.category,m2.diagnosis_code1a,m2.diagnosis_code1d,m2.disease_principal,m2.disease_mmr,m2.disease_type,m2.disease_subtype,\n" + 
						"		 m2.block_description) m3 " + q2 +" " +q1  + q3 +" "+ q4 + q5 +" "+ q6 + q7 + "\n" + 
						"		 \n" + 
						"  GROUP BY m3.qtr_off1, m3.qtr_off2, m3.qtr_off3, m3.qtr_off4, m3.year\n" + 
						"  ORDER BY m3.year)l";
				
				stmt = conn.prepareStatement(q);
				int j=1;
				if (!category1.equals("-1")) {
					  stmt.setString(j, category1);
					  j+=1;
					}
					if (!command1.equals("ALL")) {
					  stmt.setString(j, command1);
					  j+=1;
					}
					
					if (!disease_principal1.equals("-1")) {
						  stmt.setString(j, disease_principal1);
						  j+=1;
					}
					if (!disease_type1.equals("-1")) {
						 stmt.setString(j, disease_type1);
						 j+=1;
					}
					if (!disease_subtype1.equals("-1")) {
						 stmt.setString(j, disease_subtype1);
						 j+=1;
					}
					if (!disease_mmr1.equals("-1")) {
						 stmt.setString(j, disease_mmr1);
						 j+=1;
					}
					if (!block_description1.equals("-1")) {
						 stmt.setString(j, block_description1);
						 j+=1;
					}	
					}
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				int sum = (rs.getInt("january") + rs.getInt("february") + rs.getInt("march") + rs.getInt("april")
						+ rs.getInt("may") + rs.getInt("june") + rs.getInt("july") + rs.getInt("august")
						+ rs.getInt("september") + rs.getInt("october") + rs.getInt("november") + rs.getInt("december"))/12;
				alist.add(rs.getString("january"));
				alist.add(rs.getString("february"));
				alist.add(rs.getString("march"));
				alist.add(rs.getString("april"));
				alist.add(rs.getString("may"));
				alist.add(rs.getString("june"));
				alist.add(rs.getString("july"));
				alist.add(rs.getString("august"));
				alist.add(rs.getString("september"));
				alist.add(rs.getString("october"));
				alist.add(rs.getString("november"));
				alist.add(rs.getString("december"));
				alist.add(String.valueOf(sum));
				 
				list.add(alist);

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

}
