package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.sql.DataSource;

public class  Qfd_DAOImpl implements  Qfd_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	public ArrayList<ArrayList<String>> search_Qfd(String type_veh, 
//			//String prf_code, 
//			String mct_main,
//			String line_dte_sus,int kms,int vintag,int py,String type_force,String checkVal,String cmd,String line_dte_sus_main) {
//
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//		Connection conn = null;
//		String q = "";
//		
//		String whr = " where m.sus_no =? ";
//
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//			if (type_veh.equals("0")) {
//				if(kms >0) { whr += " and round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? "; }
//				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
//				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }
//			
//	if(!checkVal.equals("")) {
//					
//					whr += "and d.mct in (" + checkVal + ")  ";
//					
//				}
//				
//				if(!cmd.equals("")) {
//					//whr += " and  in ( "+cmd+") ";
//				String temp = "";
//				String[] datacmd = cmd.split(Pattern.quote(","));
//
//				for (int i = 0; i < datacmd.length; i++) {
//
//					if (i == 0) {
//
//						temp = temp + "?";
//					} else {
//
//						temp = temp + ",?";
//					}
//				}
//
//				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
//				}
//				if(!mct_main.equals("0") && !mct_main.equals("")) { whr += " and m.mct_main_id = ? "; }
//			
//				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
//				
//				
////				if(!type_of_intervention.equals("0") && !type_of_intervention.equals("")) { whr += " and m.mct_main_id = ? "; }
//
//				
//				q=" SELECT  distinct \r\n"
//						+ "c.short_form as comd,\r\n"
//						+ "corps.coprs_name as corps,\r\n"
//						+ "div.div_name as div,\r\n"
//						+ "bde.bde_name as bde,\r\n"
//						+ "u.unit_name, u1.line_dte_name as line_dte,\r\n"
//						+ "        part.sus_no,\r\n"
//						+ "        m.mct_nomen,\r\n"
//						+ "    part.ba_no,\r\n"
//						+ "round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) as kms_run ,"
//						+ "        cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" as vintage ,part.vehcl_classfctn as classification"
//						+ ",\r\n"
//						+ "	 LEAST(\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh1_due_date\r\n"
//						+ ",\r\n"
//						+ "\r\n"
//						+ " LEAST(\r\n"
//						+ "     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh2_due_date\r\n"
//						+ ",\r\n"
//						+ " LEAST(\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.vehcl_kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh3_due_date,cin.oh1Date,cin.oh2Date,cin.oh3Date\r\n"
//						+ "	 FROM tb_tms_census_retrn part\r\n"
//						+ " JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n"
//						+ " JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'A'::text\r\n"
//						+ " join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n"
//						+ " left JOIN \r\n"
//						+ "        (\r\n"
//						+ "        SELECT \r\n"
//						+ "            mct_main_id,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n"
//						+ "			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n"
//						+ "      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n"
//						+ "        FROM \r\n"
//						+ "            tb_tms_mct_main_oh_mr\r\n"
//						+ "        GROUP BY \r\n"
//						+ "            mct_main_id\r\n"
//						+ "        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n"
//						+ "left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n"
//						+ "left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n"
//						+ "left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n"
//						+ "left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n"
//						+ "left join \r\n"
//						+ "(SELECT ba_no,status,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '2' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh1Date,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '4' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh2Date,         \r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '6' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh3Date\r\n"
//						+ "from  tb_tms_cin where type_of_veh='A' and status!='0'  GROUP BY ba_no,status) AS cin  on  cin.ba_no =part.ba_no\r\n"
//						+ "inner  join (\r\n"
//						+ "	select part2.ba_no,\r\n"
//						+ "   CASE\r\n"
//						+ "        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
//						+ "        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
//						+ "    END AS extracted_year\r\n"
//						+ "	 from tb_tms_census_retrn part2\r\n"
//						+ ") as ba_registraion on ba_registraion.ba_no = part.ba_no   "
//					
//			+ whr+"  and part.vehcl_kms_run!=0 and part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0' and type_of_veh='A')"
//				+ " ORDER BY 9,10 ";
//			}
//			if (type_veh.equals("1")) {
//				if(kms >0) { whr += " and round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? ";}
//				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
//				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }
//
//	if(!checkVal.equals("")) {
//					
//					whr += "and d.mct in (" + checkVal + ")  ";
//					
//				}
//				if(!cmd.equals("")) {
//					//whr += " and  in ( "+cmd+") ";
//				
//				
//				String temp = "";
//				String[] datacmd = cmd.split(Pattern.quote(","));
//
//				for (int i = 0; i < datacmd.length; i++) {
//
//					if (i == 0) {
//
//						temp = temp + "?";
//					} else {
//
//						temp = temp + ",?";
//					}
//				}
//
//				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
//				}
//				if(!mct_main.equals("0") && !mct_main.equals("")){ whr += " and m.mct_main_id = ? "; }
//				
//				
//				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
//				
//				q = "SELECT  distinct \r\n"
//						+ "c.short_form as comd,\r\n"
//						+ "corps.coprs_name as corps,\r\n"
//						+ "div.div_name as div,\r\n"
//						+ "bde.bde_name as bde,\r\n"
//						+ "        u.unit_name,    u1.line_dte_name as line_dte,\r\n"
//						+ "        part.sus_no,\r\n"
//						+ "        m.mct_nomen,\r\n"
//						+ "    part.ba_no,\r\n"
//						+ "round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) as kms_run ,"
//						+ "        cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" as vintage ,"
//				
//						+ "part.classification as classification, '' AS approve_date\r\n"
//						+ ",LEAST(\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh1_due_date\r\n"
//						+ ",\r\n"
//						+ " LEAST(\r\n"
//						+ "     CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh2_due_date\r\n"
//						+ ",\r\n"
//						+ " LEAST(\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n"
//						+ "    CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.kms_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh3_due_date,cin.oh1Date,cin.oh2Date,cin.oh3Date\r\n"
//						+ "	 FROM tb_tms_mvcr_parta_dtl part\r\n"
//						+ " JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.ba_no::text\r\n"
//						+ " JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'B'::text\r\n"
//						+ " join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n"
//						+ " left JOIN \r\n"
//						+ "        (\r\n"
//						+ "        SELECT \r\n"
//						+ "            mct_main_id,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n"
//						+ "			 MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n"
//						+ "      MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n"
//						+ "        FROM \r\n"
//						+ "            tb_tms_mct_main_oh_mr\r\n"
//						+ "        GROUP BY \r\n"
//						+ "            mct_main_id\r\n"
//						+ "        ) AS oh_cal  ON oh_cal.mct_main_id = m.mct_main_id \r\n"
//						+ "left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n"
//						+ "left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n"
//						+ "left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n"
//						+ "left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code\r\n"
//						+ "left join \r\n"
//						+ "(SELECT ba_no,status,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '2' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh1Date,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '4' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh2Date,         \r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '6' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh3Date\r\n"
//						+ "from  tb_tms_cin where type_of_veh='B' and status!='0'  GROUP BY ba_no,status) AS cin  on  cin.ba_no =part.ba_no\r\n"
//						+ "inner  join (\r\n"
//						+ "select part2.ba_no,\r\n"
//						+ "   CASE\r\n"
//						+ "        WHEN SUBSTRING(part2.ba_no, 1, 2) >= '00' AND SUBSTRING(part2.ba_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
//						+ "        WHEN SUBSTRING(part2.ba_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.ba_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.ba_no, 1, 2)\r\n"
//						+ "    END AS extracted_year\r\n"
//						+ "	 from tb_tms_mvcr_parta_dtl part2\r\n"
//						+ ") as ba_registraion on ba_registraion.ba_no = part.ba_no  \r\n"
//						+ whr+		 " and part.kms_run!=0 and part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0' and type_of_veh='B') "
//						+ " ORDER BY part.ba_no,9,10 ";
//			}
//			if (type_veh.equals("2")) {
//				if(kms >0) {whr += " and round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? ";}
//				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
//				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }
//
//				if(!checkVal.equals("")) {
//					
//					whr += "and d.mct in (" + checkVal + ")  ";
//					
//				}
//				if(!cmd.equals("")) {
//					//whr += " and  in ( "+cmd+") ";
//				
//				
//				String temp = "";
//				String[] datacmd = cmd.split(Pattern.quote(","));
//
//				for (int i = 0; i < datacmd.length; i++) {
//
//					if (i == 0) {
//
//						temp = temp + "?";
//					} else {
//
//						temp = temp + ",?";
//					}
//				}
//
//				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
//				}
//				if(!mct_main.equals("0") && !mct_main.equals("")) { whr += " and m.mct_main_id = ? "; }
//				
//				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
//				
//				q = " SELECT distinct  c.short_form as comd,\r\n"
//						+ "        corps.coprs_name as corps,\r\n"
//						+ "        div.div_name as div,\r\n"
//						+ "        bde.bde_name as bde,\r\n"
//						+ "        u.unit_name,u1.line_dte_name as line_dte,\r\n"
//						+ "        part.sus_no,\r\n"
//						+ "        m.mct_nomen,\r\n"
//						+ "    part.em_no as ba_no,\r\n"
//						+ "round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) as kms_run ,"
//						+ "        cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" as vintage ,"
//						
//						+ "part.classification as classification\r\n"
//						+ ",oh_cal.km1,oh_cal.vintage1,oh_cal.km2,oh_cal.vintage2,oh_cal.km3,oh_cal.vintage3\r\n"
//						+ ",LEAST(\r\n"
//						+ "        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage1 AS INTEGER)-1,\r\n"
//						+ "        CAST( ba_registraion.extracted_year AS INTEGER) + ROUND(CAST(oh_cal.km1 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh1_due_date\r\n"
//						+ ",\r\n"
//						+ "  LEAST(\r\n"
//						+ "          CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage2 AS INTEGER)-1,\r\n"
//						+ "        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km2 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh2_due_date\r\n"
//						+ ",\r\n"
//						+ "  LEAST(\r\n"
//						+ "        CAST( ba_registraion.extracted_year AS INTEGER) + CAST(oh_cal.vintage3 AS INTEGER)-1,\r\n"
//						+ "        CAST( ba_registraion.extracted_year AS INTEGER)+ ROUND(CAST(oh_cal.km3 AS INTEGER) / (CAST(part.current_km_run AS INTEGER) / (cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+0 )))\r\n"
//						+ ") AS oh3_due_date,cin.oh1Date,cin.oh2Date,cin.oh3Date\r\n"
//						+ "FROM tb_tms_emar_report part\r\n"
//						+ " JOIN tb_tms_banum_dirctry d ON d.ba_no::text = part.em_no::text\r\n"
//						+ " JOIN tb_tms_mct_main_master m ON m.mct_main_id = substr(d.mct::character varying::text, 1, 4) AND m.type_of_veh::text = 'C'::text\r\n"
//						+ " join tb_miso_orbat_unt_dtl u on part.sus_no = u.sus_no and u.status_sus_no = 'Active' \r\n"
//						+ " inner   join (\r\n"
//						+ "    select part2.em_no,\r\n"
//						+ "      CASE\r\n"
//						+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= '00' AND SUBSTRING(part2.em_no, 1, 2) <= to_char(now(),'yy') THEN '20' || SUBSTRING(part2.em_no, 1, 2)\r\n"
//						+ "                WHEN SUBSTRING(part2.em_no, 1, 2) >= to_char(now(),'yy') AND SUBSTRING(part2.em_no, 1, 2) <= '99' THEN '19' || SUBSTRING(part2.em_no, 1, 2)\r\n"
//						+ "        END AS extracted_year\r\n"
//						+ "     from tb_tms_emar_report part2\r\n"
//						+ ") as ba_registraion on ba_registraion.em_no = part.em_no   \r\n"
//						+ "   left JOIN  (SELECT  mct_main_id,MAX(CASE WHEN oh_mr = '2' THEN km END) AS km1, MAX(CASE WHEN oh_mr = '2' THEN CAST(vintage AS INTEGER) END) AS vintage1,\r\n"
//						+ "			   \r\n"
//						+ "             MAX(CASE WHEN oh_mr = '4' THEN km END) AS km2, MAX(CASE WHEN oh_mr = '4' THEN CAST(vintage AS INTEGER) END) AS vintage2,\r\n"
//						+ "            MAX(CASE WHEN oh_mr = '6' THEN km END) AS km3,MAX(CASE WHEN oh_mr = '6' THEN CAST(vintage AS INTEGER) END) AS vintage3\r\n"
//						+ "                FROM \r\n"
//						+ "                        tb_tms_mct_main_oh_mr\r\n"
//						+ "                GROUP BY \r\n"
//						+ "                        mct_main_id\r\n"
//						+ "                ) AS oh_cal   ON oh_cal.mct_main_id = m.mct_main_id \r\n"
//						+ " left join \r\n"
//						+ "(SELECT ba_no,status,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '2' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh1Date,\r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '4' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh2Date,         \r\n"
//						+ "MAX(CASE WHEN type_of_intervention = '6' THEN ltrim(TO_CHAR(oh_date,'DD-Mon-YYYY'),'0') END) AS oh3Date\r\n"
//						+ "from  tb_tms_cin where type_of_veh='C' and status!='0'  GROUP BY ba_no,status) AS cin  on  cin.ba_no =part.em_no\r\n"
//						+ " \r\n"
//						+ "left join tb_miso_orbat_line_dte u1 on  u.arm_code = u1.arm_code  \r\n"
//						+ "left join orbat_view_cmd c on substr(u.form_code_control,1,1) = c.cmd_code\r\n"
//						+ "left join orbat_view_corps corps on substr(u.form_code_control,2,2) = corps.corps_code\r\n"
//						+ "left join orbat_view_div div on substr(u.form_code_control,4,3) = div.div_code\r\n"
//						+ "left join orbat_view_bde bde on substr(u.form_code_control,7,4) = bde.bde_code"
//						+ whr+" and part.current_km_run!=0 and part.em_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.em_no and status='0' and type_of_veh='C')  "
//						+ " ORDER BY 9,10 ";
//			}
//			stmt = conn.prepareStatement(q);
//			int j = 1;
//			stmt.setString(j, line_dte_sus);
//			//j = j+1;
//			//stmt.setString(j, prf_code);
//			if(kms >0) {
//				j = j+1;
//				stmt.setInt(j, kms);
//			}
//			if(vintag >0) {
//				j = j+1;
//				stmt.setInt(j, vintag);
//			}
//			if(!type_force.equals("")) {
//				j = j+1;
//				stmt.setString(j, type_force);
//			}
//			
//			
//			if (!cmd.equals("")) {
//				String[] datacmd = cmd.split(Pattern.quote(","));
//			
//				for (int i = 0; i < datacmd.length; i++) {
//					j += 1;
//					stmt.setString(j, datacmd[i]);
//					
//				}
//			}
//			if(!mct_main.equals("0") && !mct_main.equals("")){ 
//				j = j+1;
//				stmt.setString(j, mct_main);
//			}
//			if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { 	
//				j = j+1;
//			stmt.setString(j, line_dte_sus_main); 
//			}
//			
//			
//		
//		
//		
//		
//		
//	System.out.println("stmt=== "+stmt);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				ArrayList<String> list1 = new ArrayList<String>();
//				list1.add(rs.getString("comd"));//0
//				list1.add(rs.getString("corps"));//1
//				list1.add(rs.getString("div"));//2
//				list1.add(rs.getString("bde"));//3
//				list1.add(rs.getString("unit_name"));//4
//				list1.add(rs.getString("sus_no"));//5
//				list1.add(rs.getString("mct_nomen"));//6 //
//				list1.add(rs.getString("ba_no"));//7
//				list1.add(rs.getString("kms_run"));//8
//				list1.add(rs.getString("vintage"));//9
//				list1.add(rs.getString("classification"));//10s
//				list1.add(rs.getString("line_dte"));//11s
//				list1.add(rs.getString("oh1_due_date"));//12
//				list1.add(rs.getString("oh2_due_date"));//13
//				list1.add(rs.getString("oh3_due_date"));//14
//				list1.add(rs.getString("oh1Date"));//17
//				list1.add(rs.getString("oh2Date"));//18
//				list1.add(rs.getString("oh3Date"));//19
//			
//
//				alist.add(list1);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return alist;
//	}
	
	
	
	
	
	
	public ArrayList<ArrayList<String>> search_Qfd(String type_veh, 
			//String prf_code, 
			String mct_main,
			String line_dte_sus,int kms,int vintag,int py,String type_force,String checkVal,String cmd,String line_dte_sus_main) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		String whr = " where m.sus_no = ?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			if (type_veh.equals("0")) {
				if(kms >0) { whr += " and round(part.vehcl_kms_run+((part.vehcl_kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? "; }
				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }
			
				if(!checkVal.equals("")) {
					String[] mct_main_array = checkVal.split(",");
					if(mct_main_array.length>0) {
						whr +=" and ( ";
					}
					for(int i=0;i<mct_main_array.length;i++) {
						if(i==0) {
							whr +=" m.mct_main_id = ? ";	
						}else {
							whr +=" or m.mct_main_id = ? ";
						}
					}
					if(mct_main_array.length>0) {
						whr +=" ) ";
					}
				}
				
				if(!cmd.equals("")) {
					//whr += " and  in ( "+cmd+") ";
				String temp = "";
				String[] datacmd = cmd.split(Pattern.quote(","));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
				}
				if(!mct_main.equals("0") && !mct_main.equals("")) { whr += " and m.mct_main_id = ? "; }
			
				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
				
				
//				if(!type_of_intervention.equals("0") && !type_of_intervention.equals("")) { whr += " and m.mct_main_id = ? "; }

				
				q="select distinct c.short_form as comd,\r\n"
						+ "       corps.coprs_name as corps,\r\n"
						+ "       div.div_name as div,\r\n"
						+ "       bde.bde_name as bde,\r\n"
						+ "       u.unit_name,\r\n"
						+ "       u1.line_dte_name as line_dte,\r\n"
						+ "       part.sus_no,\r\n"
						+ "       m.mct_nomen,\r\n"
						+"        f.std_nomclature,\r\n"
						+ "       part.ba_no,\r\n"
						+ "       round(part.vehcl_kms_run + ((part.vehcl_kms_run / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1)) * 0), 0) as kms_run,\r\n"
						+ "       cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0 as vintage,\r\n"
						+ "       part.vehcl_classfctn as classification,\r\n"
						+ "       '' as approve_date,\r\n"
						+ "       case when oh_cal.km1 is null or (oh_cal.km1 = 0 and oh_cal.vintage1 = 0) then null\r\n"
						+ "            when oh_cal.km1 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh1_due_date,\r\n"
						+ "       case when oh_cal.km2 is null or (oh_cal.km2 = 0 and oh_cal.vintage2 = 0) then null\r\n"
						+ "            when oh_cal.km2 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh2_due_date,\r\n"
						+ "       case when oh_cal.km3 is null or (oh_cal.km3 = 0 and oh_cal.vintage3 = 0) then null\r\n"
						+ "            when oh_cal.km3 =0 and coalesce(part.vehcl_kms_run, 0) = 0          then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh3_due_date,\r\n"
						+ "       case when oh_cal.mr_km1 is null or (oh_cal.mr_km1 = 0 and oh_cal.mr_vintage1 = 0) then null\r\n"
						+ "            when oh_cal.mr_km1 =0 and coalesce(part.vehcl_kms_run, 0) = 0                then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.mr_km1 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr1_due_date,\r\n"
						+ "       case when oh_cal.mr_km2 is null or (oh_cal.mr_km2 = 0 and oh_cal.mr_vintage2 = 0) then null\r\n"
						+ "            when oh_cal.mr_km2 =0 and coalesce(part.vehcl_kms_run, 0) = 0                then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.mr_km2 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr2_due_date,\r\n"
						+ "       case when oh_cal.mr_km3 is null or (oh_cal.mr_km3 = 0 and oh_cal.mr_vintage3 = 0) then null\r\n"
						+ "            when oh_cal.mr_km3 =0 and coalesce(part.vehcl_kms_run, 0) = 0                then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.mr_km3 as NUMERIC) /case when cast(part.vehcl_kms_run as INTEGER) = 0 then 1 else cast(part.vehcl_kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr3_due_date,\r\n"
						+ "       cin.oh1date,\r\n"
						+ "       cin.oh2date,\r\n"
						+ "       cin.oh3date,\r\n"
						+ "       cin.mr1date,\r\n"
						+ "       cin.mr2date,\r\n"
						+ "       cin.mr3date\r\n"
						+ "  from tb_tms_census_retrn part\r\n"
						+ "  join tb_tms_banum_dirctry d\r\n"
						+ "    on d.ba_no::text = part.ba_no::text\r\n"
						+ "  join tb_tms_mct_main_master m\r\n"
						+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
						+ "   and m.type_of_veh::text = 'A'::text\r\n"
						+"  join tb_tms_mct_master f\r\n" 
						+ "    on f.mct = d.mct\r\n"
						+ "  join tb_miso_orbat_unt_dtl u\r\n"
						+ "    on part.sus_no = u.sus_no\r\n"
						+ "   and u.status_sus_no = 'Active'\r\n"
						+ "  left join (\r\n"
						+ "        select mct_main_id,\r\n"
						+ "               max(case when oh_mr = '2' then km end) as km1,\r\n"
						+ "               max(case when oh_mr = '2' then cast(vintage as INTEGER) end) as vintage1,\r\n"
						+ "               max(case when oh_mr = '4' then km end)as km2,\r\n"
						+ "               max(case when oh_mr = '4' then cast(vintage as INTEGER) end) as vintage2,\r\n"
						+ "               max(case when oh_mr = '6' then km end) as km3,\r\n"
						+ "               max(case when oh_mr = '6' then cast(vintage as INTEGER) end) as vintage3,\r\n"
						+ "               max(case when oh_mr = '1' then km end) as mr_km1,\r\n"
						+ "               max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as mr_vintage1,\r\n"
						+ "               max(case when oh_mr = '3' then km end) as mr_km2,\r\n"
						+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as mr_vintage2,\r\n"
						+ "               max(case when oh_mr = '5' then km end) as mr_km3,\r\n"
						+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as mr_vintage3\r\n"
						+ "          from tb_tms_mct_main_oh_mr\r\n"
						+ "         group by mct_main_id\r\n"
						+ "       ) as oh_cal\r\n"
						+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
						+ " inner join tb_miso_orbat_line_dte u1\r\n"
						+ "    on m.sus_no=u1.line_dte_sus\r\n"
						+ "  left join orbat_view_cmd c\r\n"
						+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
						+ "  left join orbat_view_corps corps\r\n"
						+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
						+ "  left join orbat_view_div div\r\n"
						+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
						+ "  left join orbat_view_bde bde\r\n"
						+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
						+ "  left join (\r\n"
						+ "        select ba_no,\r\n"
						+ "               status,\r\n"
						+ "               max(case when type_of_intervention = '2' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh1date,\r\n"
						+ "               max(case when type_of_intervention = '4' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh2date,\r\n"
						+ "               max(case when type_of_intervention = '6' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh3date,\r\n"
						+ "               max(case when type_of_intervention = '1' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr1date,\r\n"
						+ "               max(case when type_of_intervention = '3' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr2date,\r\n"
						+ "               max(case when type_of_intervention = '5' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr3date\r\n"
						+ "          from tb_tms_cin\r\n"
						+ "         where type_of_veh = 'A'\r\n"
						+ "           and status != '0'\r\n"
						+ "         group by ba_no,\r\n"
						+ "                  status\r\n"
						+ "       ) as cin\r\n"
						+ "    on cin.ba_no = part.ba_no\r\n"
						+ " inner join (select part2.ba_no,\r\n"
						+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                     end as extracted_year\r\n"
						+ "          from tb_tms_census_retrn part2\r\n"
						+ "       ) as ba_registraion\r\n"
						+ "    on ba_registraion.ba_no = part.ba_no"
						+ "     "+whr+""
						+ "   and part.ba_no not in (\r\n"
						+ "        select ba_no\r\n"
						+ "          from tb_tms_cin cin\r\n"
						+ "         where cin.ba_no = part.ba_no\r\n"
						+ "           and status = '0'\r\n"
						+ "           and type_of_veh = 'A'\r\n"
						+ "       )\r\n"
					
					
						+ " order by part.ba_no,\r\n"
						+ "          9,\r\n"
						+ "          10;";
			}
			if (type_veh.equals("1")) {
				if(kms >0) { whr += " and round(part.kms_run+((part.kms_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? ";}
				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.ba_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }

				if(!checkVal.equals("")) {
					String[] mct_main_array = checkVal.split(",");
					if(mct_main_array.length>0) {
						whr +=" and ( ";
					}
					for(int i=0;i<mct_main_array.length;i++) {
						if(i==0) {
							whr +=" m.mct_main_id = ? ";	
						}else {
							whr +=" or m.mct_main_id = ? ";
						}
					}
					if(mct_main_array.length>0) {
						whr +=" ) ";
					}
				}
				if(!cmd.equals("")) {
					//whr += " and  in ( "+cmd+") ";
				
				
				String temp = "";
				String[] datacmd = cmd.split(Pattern.quote(","));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
				}
				if(!mct_main.equals("0") && !mct_main.equals("")){ whr += " and m.mct_main_id = ? "; }
				
				
				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
				
				q = "select distinct c.short_form as comd,\r\n"
						+ "       corps.coprs_name as corps,\r\n"
						+ "       div.div_name as div,\r\n"
						+ "       bde.bde_name as bde,\r\n"
						+ "       u.unit_name,\r\n"
						+ "       u1.line_dte_name as line_dte,\r\n"
						+ "       part.sus_no,\r\n"
						+ "       m.mct_nomen,\r\n"
						+"        f.std_nomclature,\r\n"
						+ "       part.ba_no,\r\n"
						+ "       round(part.kms_run + ((part.kms_run / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1)) * 0), 0) as kms_run,\r\n"
						+ "       cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0 as vintage,\r\n"
						+ "       part.classification as classification,\r\n"
						+ "       '' as approve_date,\r\n"
						+ "       case when oh_cal.km1 is null or (oh_cal.km1 = 0 and oh_cal.vintage1 = 0) then null\r\n"
						+ "            when oh_cal.km1 = 0 and coalesce(part.kms_run, 0) = 0               then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) / case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh1_due_date,\r\n"
						+ "       case when oh_cal.km2 is null or (oh_cal.km2 = 0 and oh_cal.vintage2 = 0) then null\r\n"
						+ "            when oh_cal.km2 =0 and coalesce(part.kms_run, 0) = 0                then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh2_due_date,\r\n"
						+ "       case when oh_cal.km3 is null or (oh_cal.km3 = 0 and oh_cal.vintage3 = 0) then null\r\n"
						+ "            when oh_cal.km3 =0 and coalesce(part.kms_run, 0) = 0                then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as oh3_due_date,\r\n"
						+ "       case when oh_cal.mr_km1 is null or (oh_cal.mr_km1 = 0 and oh_cal.mr_vintage1 = 0) then null\r\n"
						+ "            when oh_cal.mr_km1 =0 and coalesce(part.kms_run, 0) = 0                      then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage1 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage1 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km1 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr1_due_date,\r\n"
						+ "       case when oh_cal.mr_km2 is null or (oh_cal.mr_km2 = 0 and oh_cal.mr_vintage2 = 0) then null\r\n"
						+ "            when oh_cal.mr_km2 =0 and coalesce(part.kms_run, 0) = 0                   then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage2 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage2 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km2 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr2_due_date,\r\n"
						+ "       case when oh_cal.mr_km3 is null or (oh_cal.mr_km3 = 0 and oh_cal.mr_vintage3 = 0) then null\r\n"
						+ "            when oh_cal.mr_km3 =0 and coalesce(part.kms_run, 0) = 0                   then cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage3 as INTEGER) - 1\r\n"
						+ "            else least(cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage3 as INTEGER) - 1, cast(ba_registraion.extracted_year as INTEGER) + round(cast(oh_cal.km3 as NUMERIC) /case when cast(part.kms_run as INTEGER) = 0 then 1 else cast(part.kms_run as NUMERIC) / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.ba_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0) end))\r\n"
						+ "             end as mr3_due_date,\r\n"
						+ "       cin.oh1date,\r\n"
						+ "       cin.oh2date,\r\n"
						+ "       cin.oh3date,\r\n"
						+ "       cin.mr1date,\r\n"
						+ "       cin.mr2date,\r\n"
						+ "       cin.mr3date\r\n"
						+ "  from tb_tms_mvcr_parta_dtl part\r\n"
						+ "  join tb_tms_banum_dirctry d\r\n"
						+ "    on d.ba_no::text = part.ba_no::text\r\n"
						+ "  join tb_tms_mct_main_master m\r\n"
						+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
						+ "   and m.type_of_veh::text = 'B'::text\r\n"
						+"  join tb_tms_mct_master f\r\n" 
						+ "    on f.mct = d.mct\r\n"
						+ "  join tb_miso_orbat_unt_dtl u\r\n"
						+ "    on part.sus_no = u.sus_no\r\n"
						+ "   and u.status_sus_no = 'Active'\r\n"
						+ "  left join (\r\n"
						+ "        select mct_main_id,\r\n"
						+ "               max(case when oh_mr = '2' then km end) as km1,\r\n"
						+ "               max(case when oh_mr = '2' then cast(vintage as INTEGER) end) as vintage1,\r\n"
						+ "               max(case when oh_mr = '4' then km end)as km2,\r\n"
						+ "               max(case when oh_mr = '4' then cast(vintage as INTEGER) end) as vintage2,\r\n"
						+ "               max(case when oh_mr = '6' then km end) as km3,\r\n"
						+ "               max(case when oh_mr = '6' then cast(vintage as INTEGER) end) as vintage3,\r\n"
						+ "               max(case when oh_mr = '1' then km end) as mr_km1,\r\n"
						+ "               max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as mr_vintage1,\r\n"
						+ "               max(case when oh_mr = '3' then km end) as mr_km2,\r\n"
						+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as mr_vintage2,\r\n"
						+ "               max(case when oh_mr = '5' then km end) as mr_km3,\r\n"
						+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as mr_vintage3\r\n"
						+ "          from tb_tms_mct_main_oh_mr\r\n"
						+ "         group by mct_main_id\r\n"
						+ "       ) as oh_cal\r\n"
						+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
						+ " inner join tb_miso_orbat_line_dte u1\r\n"
						+ "    on m.sus_no=u1.line_dte_sus\r\n"
						+ "  left join orbat_view_cmd c\r\n"
						+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
						+ "  left join orbat_view_corps corps\r\n"
						+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
						+ "  left join orbat_view_div div\r\n"
						+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
						+ "  left join orbat_view_bde bde\r\n"
						+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
						+ "  left join (\r\n"
						+ "        select ba_no,\r\n"
						+ "               status,\r\n"
						+ "               max(case when type_of_intervention = '2' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh1date,\r\n"
						+ "               max(case when type_of_intervention = '4' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh2date,\r\n"
						+ "               max(case when type_of_intervention = '6' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh3date,\r\n"
						+ "               max(case when type_of_intervention = '1' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr1date,\r\n"
						+ "               max(case when type_of_intervention = '3' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr2date,\r\n"
						+ "               max(case when type_of_intervention = '5' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr3date\r\n"
						+ "          from tb_tms_cin\r\n"
						+ "         where type_of_veh = 'B'\r\n"
						+ "           and status != '0'\r\n"
						+ "         group by ba_no,\r\n"
						+ "                  status\r\n"
						+ "       ) as cin\r\n"
						+ "    on cin.ba_no = part.ba_no\r\n"
						+ " inner join (\r\n"
						+ "        select part2.ba_no,\r\n"
						+ "               case when substring(part2.ba_no, 1, 2) >= '00' and substring(part2.ba_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                    when substring(part2.ba_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.ba_no, 1, 2) <= '99' then '19' || substring(part2.ba_no, 1, 2)\r\n"
						+ "                     end as extracted_year\r\n"
						+ "          from tb_tms_mvcr_parta_dtl part2\r\n"
						+ "       ) as ba_registraion\r\n"
						+ "    on ba_registraion.ba_no = part.ba_no"
						+ whr+		"and part.issue_type!='4' and part.ba_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.ba_no and status='0' and type_of_veh='B') "
						+ " ORDER BY part.ba_no,9,10 ";
			}
			if (type_veh.equals("2")) {
				if(kms >0) {whr += " and round(part.current_km_run+((part.current_km_run/(cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1))*"+py+"),0) >= ? ";}
				if(vintag >0) { whr += " and cast(RIGHT(cast(((100+cast(to_char(now(),'yy') as integer))-cast(substr(part.em_no,1,2) as integer)) as varchar),2) as integer)+1+"+py+" >= ? ";}
				if(!type_force.equals("")) { whr += " and u.type_force = ? "; }

				if(!checkVal.equals("")) {
					String[] mct_main_array = checkVal.split(",");
					if(mct_main_array.length>0) {
						whr +=" and ( ";
					}
					for(int i=0;i<mct_main_array.length;i++) {
						if(i==0) {
							whr +=" m.mct_main_id = ? ";	
						}else {
							whr +=" or m.mct_main_id = ? ";
						}
					}
					if(mct_main_array.length>0) {
						whr +=" ) ";
					}
				}
				if(!cmd.equals("")) {
					//whr += " and  in ( "+cmd+") ";
				
				
				String temp = "";
				String[] datacmd = cmd.split(Pattern.quote(","));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {

						temp = temp + "?";
					} else {

						temp = temp + ",?";
					}
				}

				whr += "and SUBSTR(u.form_code_control,1,1) in (" + temp + ")  ";
				}
				if(!mct_main.equals("0") && !mct_main.equals("")) { whr += " and m.mct_main_id = ? "; }
				
				if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { whr += " and  u1.line_dte_sus = ? "; }
				
				q = "select distinct c.short_form as comd,\r\n"
						+ "       corps.coprs_name as corps,\r\n"
						+ "       div.div_name as div,\r\n"
						+ "       bde.bde_name as bde,\r\n"
						+ "       u.unit_name,\r\n"
						+ "       u1.line_dte_name as line_dte,\r\n"
						+ "       part.sus_no,\r\n"
						+ "       m.mct_nomen,\r\n"
						+"        f.std_nomclature,\r\n"
						+ "       part.em_no as ba_no,\r\n"
						+ "       round(part.current_km_run + ((part.current_km_run / (cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.em_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1)) * 0), 0) as kms_run,\r\n"
						+ "       cast(right(cast(((100 + cast(to_char(now(), 'yy') as INTEGER)) - cast(substr(part.em_no, 1, 2) as INTEGER)) as VARCHAR), 2) as INTEGER) + 1 + 0 as vintage,\r\n"
						+ "       part.classification as classification,\r\n"
						+ "       '' as approve_date,\r\n"
						+ "       case when oh_cal.km1 is null or (oh_cal.km1 = 0 and oh_cal.vintage1 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage1 as INTEGER) - 1\r\n"
						+ "             end as oh1_due_date,\r\n"
						+ "       case when oh_cal.km2 is null or (oh_cal.km2 = 0 and oh_cal.vintage2 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage2 as INTEGER) - 1\r\n"
						+ "             end as oh2_due_date,\r\n"
						+ "       case when oh_cal.km3 is null or (oh_cal.km3 = 0 and oh_cal.vintage3 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.vintage3 as INTEGER) - 1\r\n"
						+ "             end as oh3_due_date,\r\n"
						+ "       case when oh_cal.mr_km1 is null or (oh_cal.mr_km1 = 0 and oh_cal.mr_vintage1 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage1 as INTEGER) - 1\r\n"
						+ "             end as mr1_due_date,\r\n"
						+ "       case when oh_cal.mr_km2 is null or (oh_cal.mr_km2 = 0 and oh_cal.mr_vintage2 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage2 as INTEGER) - 1\r\n"
						+ "             end as mr2_due_date,\r\n"
						+ "       case when oh_cal.mr_km3 is null or (oh_cal.mr_km3 = 0 and oh_cal.mr_vintage3 = 0) then null\r\n"
						+ "            else cast(ba_registraion.extracted_year as INTEGER) + cast(oh_cal.mr_vintage3 as INTEGER) - 1\r\n"
						+ "             end as mr3_due_date,\r\n"
						+ "       cin.oh1date,\r\n"
						+ "       cin.oh2date,\r\n"
						+ "       cin.oh3date,\r\n"
						+ "       cin.mr1date,\r\n"
						+ "       cin.mr2date,\r\n"
						+ "       cin.mr3date\r\n"
						+ "  from tb_tms_emar_report part\r\n"
						+ "  join tb_tms_banum_dirctry d\r\n"
						+ "    on d.ba_no::text = part.em_no::text\r\n"
						+ "  join tb_tms_mct_main_master m\r\n"
						+ "    on m.mct_main_id = substr(d.mct::character varying::text, 1, 4)\r\n"
						+ "   and m.type_of_veh::text = 'C'::text\r\n"
						+"  join tb_tms_mct_master f\r\n" 
						+ "    on f.mct = d.mct\r\n"
						+ "  join tb_miso_orbat_unt_dtl u\r\n"
						+ "    on part.sus_no = u.sus_no\r\n"
						+ "   and u.status_sus_no = 'Active'\r\n"
						+ "  left join (\r\n"
						+ "        select mct_main_id,\r\n"
						+ "               max(case when oh_mr = '2' then km end) as km1,\r\n"
						+ "               max(case when oh_mr = '2' then cast(vintage as INTEGER) end) as vintage1,\r\n"
						+ "               max(case when oh_mr = '4' then km end)as km2,\r\n"
						+ "               max(case when oh_mr = '4' then cast(vintage as INTEGER) end) as vintage2,\r\n"
						+ "               max(case when oh_mr = '6' then km end) as km3,\r\n"
						+ "               max(case when oh_mr = '6' then cast(vintage as INTEGER) end) as vintage3,\r\n"
						+ "               max(case when oh_mr = '1' then km end) as mr_km1,\r\n"
						+ "               max(case when oh_mr = '1' then cast(vintage as INTEGER) end) as mr_vintage1,\r\n"
						+ "               max(case when oh_mr = '3' then km end) as mr_km2,\r\n"
						+ "               max(case when oh_mr = '3' then cast(vintage as INTEGER) end) as mr_vintage2,\r\n"
						+ "               max(case when oh_mr = '5' then km end) as mr_km3,\r\n"
						+ "               max(case when oh_mr = '5' then cast(vintage as INTEGER) end) as mr_vintage3\r\n"
						+ "          from tb_tms_mct_main_oh_mr\r\n"
						+ "         group by mct_main_id\r\n"
						+ "       ) as oh_cal\r\n"
						+ "    on oh_cal.mct_main_id = m.mct_main_id\r\n"
						+ " inner join tb_miso_orbat_line_dte u1\r\n"
						+ "    on m.sus_no=u1.line_dte_sus\r\n"
						+ "  left join orbat_view_cmd c\r\n"
						+ "    on substr(u.form_code_control, 1, 1) = c.cmd_code\r\n"
						+ "  left join orbat_view_corps corps\r\n"
						+ "    on substr(u.form_code_control, 2, 2) = corps.corps_code\r\n"
						+ "  left join orbat_view_div div\r\n"
						+ "    on substr(u.form_code_control, 4, 3) = div.div_code\r\n"
						+ "  left join orbat_view_bde bde\r\n"
						+ "    on substr(u.form_code_control, 7, 4) = bde.bde_code\r\n"
						+ "  left join (\r\n"
						+ "        select ba_no,\r\n"
						+ "               status,\r\n"
						+ "               max(case when type_of_intervention = '2' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh1date,\r\n"
						+ "               max(case when type_of_intervention = '4' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh2date,\r\n"
						+ "               max(case when type_of_intervention = '6' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as oh3date,\r\n"
						+ "               max(case when type_of_intervention = '1' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr1date,\r\n"
						+ "               max(case when type_of_intervention = '3' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr2date,\r\n"
						+ "               max(case when type_of_intervention = '5' then ltrim(to_char(oh_date, 'DD-Mon-YYYY'), '0') end) as mr3date\r\n"
						+ "          from tb_tms_cin\r\n"
						+ "         where type_of_veh = 'C'\r\n"
						+ "           and status != '0'\r\n"
						+ "         group by ba_no,\r\n"
						+ "                  status\r\n"
						+ "       ) as cin\r\n"
						+ "    on cin.ba_no = part.em_no\r\n"
						+ " inner join (\r\n"
						+ "        select part2.em_no,\r\n"
						+ "               case when substring(part2.em_no, 1, 2) >= '00' and substring(part2.em_no, 1, 2) <= to_char(now(), 'yy') then '20' || substring(part2.em_no, 1, 2)\r\n"
						+ "                    when substring(part2.em_no, 1, 2) >= to_char(now(), 'yy') and substring(part2.em_no, 1, 2) <= '99' then '19' || substring(part2.em_no, 1, 2)\r\n"
						+ "                     end as extracted_year\r\n"
						+ "          from tb_tms_emar_report part2\r\n"
						+ "       ) as ba_registraion\r\n"
						+ "    on ba_registraion.em_no = part.em_no"
						+ whr+"  and part.em_no not in (select ba_no from tb_tms_cin cin where  cin.ba_no =part.em_no and status='0' and type_of_veh='C')  "
						+ " ORDER BY 9,10 ";
			}
			stmt = conn.prepareStatement(q);
			int j = 1;
			stmt.setString(j, line_dte_sus);
			//j = j+1;
			//stmt.setString(j, prf_code);
			if(kms >0) {
				j = j+1;
				stmt.setInt(j, kms);
			}
			if(vintag >0) {
				j = j+1;
				stmt.setInt(j, vintag);
			}
			if(!type_force.equals("")) {
				j = j+1;
				stmt.setString(j, type_force);
			}
			
			
			if (!cmd.equals("")) {
				String[] datacmd = cmd.split(Pattern.quote(","));
			
				for (int i = 0; i < datacmd.length; i++) {
					j += 1;
					stmt.setString(j, datacmd[i]);
					
				}
			}
			
			if(!checkVal.equals("")){
				String[] mct_main_array = checkVal.split(",");
				for(int i=0;i<mct_main_array.length;i++) {
					j = j + 1;
					stmt.setString(j,mct_main_array[i]);
				}
			}			
		
			if(!line_dte_sus_main.equals("0") && !line_dte_sus_main.equals("")) { 	
				j = j+1;
			stmt.setString(j, line_dte_sus_main); 
			}			
	       System.err.println("stmt=== "+stmt);
	       
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("comd"));//0
				list1.add(rs.getString("corps"));//1
				list1.add(rs.getString("div"));//2
				list1.add(rs.getString("bde"));//3
				list1.add(rs.getString("unit_name"));//4
				list1.add(rs.getString("sus_no"));//5
			//	list1.add(rs.getString("mct_nomen"));//6 //
				list1.add(rs.getString("ba_no"));//7
				list1.add(rs.getString("kms_run"));//8
				list1.add(rs.getString("vintage"));//9
				list1.add(rs.getString("classification"));//10s
				list1.add(rs.getString("line_dte"));//11s
				list1.add(rs.getString("oh1_due_date"));//12
				list1.add(rs.getString("oh2_due_date"));//13
				list1.add(rs.getString("oh3_due_date"));//14
				list1.add(rs.getString("oh1Date"));//15
				list1.add(rs.getString("oh2Date"));//16
				list1.add(rs.getString("oh3Date"));//17
				list1.add(rs.getString("mr1_due_date"));//18
				list1.add(rs.getString("mr2_due_date"));//19
				list1.add(rs.getString("mr3_due_date"));//20
				list1.add(rs.getString("mr1date"));//21
				list1.add(rs.getString("mr2date"));//22
				list1.add(rs.getString("mr3date"));//23
				list1.add(rs.getString("std_nomclature"));//24 //
			

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
