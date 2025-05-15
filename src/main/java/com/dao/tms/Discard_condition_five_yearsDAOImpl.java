package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class  Discard_condition_five_yearsDAOImpl implements  Discard_condition_five_yearsDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}




public ArrayList<ArrayList<String>> search_veh_name(String type_veh,
		String month,String ddlYears,String checkVal) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		String whr = "  ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (type_veh.equals("0")) {
			
				if(!checkVal.equals("")) { whr += " and b.mct_main_id::integer in ( "+checkVal+") ";
				}
				
				q = "SELECT\r\n"
						+ "    b.mct_main_id,\r\n"
						+ "    SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer)) 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer+1			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+1)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer+1\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+1))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum2,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer+2			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+2)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer+2\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+2))\r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum3,\r\n"
						+ "SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer+3			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+3)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer+3\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+3))\r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum4	,\r\n"
						+ "SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer+4			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+4)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer+4\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+4))\r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum5,\r\n"
						+ "SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) = to_char(now(), 'yyyy'::text)::integer+5			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+5)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)-1) <= to_char(now(), 'yyyy'::text)::integer+5\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+5))\r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum6\r\n"
						+ "\r\n"
						+ "FROM tb_tms_census_retrn a\r\n"
						+ "inner JOIN tb_tms_banum_dirctry bd ON a.ba_no::text = bd.ba_no::text\r\n"
						+ "inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n"
						+ " left  join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id\r\n"
						+ "inner   join (\r\n"
						+ "select part2.ba_no,CASE\r\n"
						+ "WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
						+ "WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
						+ "END AS extracted_year\r\n"
						+ "from tb_tms_census_retrn part2\r\n"
						+ ") as ba_registraion on ba_registraion.ba_no = a.ba_no \r\n"
						+ "where a.vehcl_kms_run!=0 and a.status='1'\r\n"+whr
						+ "GROUP BY\r\n"
						+ "    b.mct_main_id;\r\n"
						+ "	 ";
					
			}
			if (type_veh.equals("1")) {
				
				if(!checkVal.equals("")) { whr += " and b.mct_main_id::integer in ( "+checkVal+") "; }
			
				q = " \r\n"
						+ "SELECT\r\n"
						+ "    b.mct_main_id,\r\n"
						+ "    SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer)) 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+1			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+1)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+1\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+1))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum2,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+2			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+2)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+2\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+2))\r\n"
						+ "			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum3,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+3			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+3)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+3\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+3))\r\n"
						+ "			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum4,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+4		  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+4)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+4\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+4))\r\n"
						+ "			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum5,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+5		  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+5)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+5\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.ba_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+5))\r\n"
						+ "			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum6\r\n"
						+ "	\r\n"
						+ "FROM tb_tms_mvcr_parta_dtl a\r\n"
						+ "inner JOIN tb_tms_banum_dirctry bd ON a.ba_no::text = bd.ba_no::text\r\n"
						+ "inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n"
						+ " left  join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id\r\n"
						+ "inner   join (\r\n"
						+ "select part2.ba_no,CASE\r\n"
						+ "WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
						+ "WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
						+ "END AS extracted_year\r\n"
						+ "from tb_tms_mvcr_parta_dtl part2\r\n"
						+ "				) as ba_registraion on ba_registraion.ba_no = a.ba_no \r\n"
						+ "where a.kms_run!=0 and a.status='1'\r\n"+ whr
						+ "  GROUP BY\r\n"
						+ "    b.mct_main_id";
		
			
			}
			if (type_veh.equals("2")) {
						if(!checkVal.equals("")) { whr += " and b.mct_main_id::integer in ( "+checkVal+") "; }
				
				q = "\r\n"
						+ "SELECT\r\n"
						+ "    b.mct_main_id,\r\n"
						+ "    SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer)) 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum,\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+1			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+1)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+1\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+1))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum2,\r\n"
						+ "\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+2			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+2)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+2\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+2))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum3\r\n"
						+ "\r\n"
						+ ",\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+3			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+3)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+3\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+3))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum4\r\n"
						+ "	,\r\n"
						+ "	\r\n"
						+ " SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+4			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+4)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+4\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+4))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum5,\r\n"
						+ "SUM(CASE\r\n"
						+ "           WHEN (((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) = to_char(now(), 'yyyy'::text)::integer+5			  \r\n"
						+ " 	AND (CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1)))\r\n"
						+ "		<= to_char(now(), 'yyyy'::text)::integer+5)			\r\n"
						+ "				)\r\n"
						+ "OR ((CAST(dis.vintage AS INTEGER) + CAST(ba_registraion.extracted_year AS INTEGER)) <= to_char(now(), 'yyyy'::text)::integer+5\r\n"
						+ "    AND (CAST(ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(dis.km AS INTEGER) /(a.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-\r\n"
						+ " cast(substr(a.em_no,1,2) as integer)) as varchar),2) as integer)+1))) ) = to_char(now(), 'yyyy'::text)::integer+5))\r\n"
						+ "				 \r\n"
						+ "				 			  \r\n"
						+ "THEN 1 ELSE 0 END) AS year_cal_sum6\r\n"
						+ "	\r\n"
						+ "FROM tb_tms_emar_report a\r\n"
						+ "inner JOIN tb_tms_banum_dirctry bd ON a.em_no::text = bd.ba_no::text\r\n"
						+ "inner JOIN tb_tms_mct_main_master b ON substr(bd.mct::character varying::text, 1, 4) = b.mct_main_id\r\n"
						+ " left  join tb_tms_mct_discard_condition dis on dis.mct_main_id = b.mct_main_id and dis.status='1'\r\n"
						+ "inner   join (\r\n"
						+ "select part2.em_no,CASE\r\n"
						+ "WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n"
						+ "WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n"
						+ "END AS extracted_year\r\n"
						+ "from tb_tms_emar_report part2\r\n"
						+ "				) as ba_registraion on ba_registraion.em_no = a.em_no \r\n"
						+ "where a.current_km_run!=0 and a.status='1'\r\n"+ whr
						+ "GROUP BY\r\n"
						+ "    b.mct_main_id";
						
						
			}
			
			
			stmt = conn.prepareStatement(q);
			System.err.println("DiscardQuery::" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				
				list1.add(rs.getString("mct_main_id"));
				list1.add(rs.getString("year_cal_sum"));
				list1.add(rs.getString("year_cal_sum2"));
				list1.add(rs.getString("year_cal_sum3"));
				list1.add(rs.getString("year_cal_sum4"));
				list1.add(rs.getString("year_cal_sum5"));
				list1.add(rs.getString("year_cal_sum6"));
				alist.add(list1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
		
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
