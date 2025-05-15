package com.dao.psg.miso_olap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

public class JDBCMISO_OLAPDAOImpl implements JDBCMISO_OLAPDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void iaff3008_MainDetails_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
//	        	.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_iaff_3008_main_details (sus_no,auth,held,present_return_no,\r\n"
							+ "		  present_return_dt,we_pe_no,cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version) \r\n"
							+ "   SELECT sus_no,auth,held,present_return_no,present_return_dt,we_pe_no,\r\n"
							+ "		  cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version\r\n"
							+ "    FROM dblink('myconn','SELECT distinct md.sus_no,md.auth,md.held,md.present_return_no,md.present_return_dt,\r\n"
							+ "	md.we_pe_no,md.cmd_sus,md.cmd_unit,md.corp_sus,md.corp_unit,md.div_sus,md.div_unit,md.bde_sus,md.bde_unit,\r\n"
							+ "	md.observation,md.created_date,md.created_by,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version FROM tb_psg_iaff_3008_main_details md \r\n"
							+ "	inner join tb_psg_iaff_3008_main v on md.version = v.version and v.month = md.month and \r\n"
							+ "	v.year = md.year and v.sus_no = md.sus_no  where v.status != 3 ') \r\n"
							+ "	AS t(sus_no character varying,auth character varying,held character varying,\r\n"
							+ "		 present_return_no character varying,present_return_dt timestamp without time zone,\r\n"
							+ "	we_pe_no character varying,cmd_sus character varying,cmd_unit character varying,\r\n"
							+ "	corp_sus character varying,corp_unit character varying,div_sus character varying,div_unit character varying,\r\n"
							+ "	bde_sus character varying,bde_unit character varying,observation character varying,	 \r\n"
							+ "	created_date timestamp without time zone,created_by character varying,\r\n"
							+ "	month character varying,year character varying,version character varying)");

			int a = pstmt.executeUpdate();

			PreparedStatement pstmt1 = c.prepareStatement(
					"insert into tb_olap_csd_details (name,date_of_birth,type_of_card,card_no,category,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT name,date_of_birth,type_of_card,card_no,category,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select b.name,b.date_of_birth,a.type_of_card,a.card_no,c.label as category,b.personnel_no as personal_no,\r\n"
							+ "cast(date_part(''month'', (SELECT current_timestamp)) as character varying) as month,cast(date_part(''year'', (SELECT current_timestamp)) as character varying) as year,\r\n"
							+ "b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_canteen_card_details1 a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id \r\n"
							+ " inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''  \r\n"
							+ "left join t_domain_value c on cast(c.codevalue as int) = a.relation and c.domainid=''CSD_CARD''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  a.status not in (''0'',''3'',''-1'') and b.status not in (''0'',''3'')') \r\n"
							+ "AS t(name character varying,date_of_birth timestamp without time zone,type_of_card character varying,\r\n"
							+ "card_no character varying,category character varying,personal_no character varying,month character varying,year character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control  character varying,ct_part_i_ii  character varying,type_of_location  character varying)\r\n"
							+ "");

			int b = pstmt1.executeUpdate();

			//// ALLERGY DETAILS

			PreparedStatement pstmt2 = c.prepareStatement(
					"insert into tb_olap_allerg_details (medicine,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT medicine,personal_no,month,year ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location \r\n"
							+ "FROM dblink('myconn','select a.medicine,b.personnel_no,date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location \r\n"
							+ "from tb_psg_allergic_to_medicine a  \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''    \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')   \r\n"
							+ "AS t(medicine character varying,personal_no character varying,month character varying,year character varying,unit_sus_no  character varying\r\n"
							+ ",unit_name  character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)   ");

			int cc = pstmt2.executeUpdate();

// ARMY COURSE 

			PreparedStatement pstmt3 = c.prepareStatement(
					"insert into tb_olap_army_course_details (authority,date_of_authority,course_name,div_grade,course_type,start_date,\r\n"
							+ "date_of_completion,course_abbreviation,course_institute,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ ") \r\n"
							+ "SELECT authority,date_of_authority,course_name,div_grade,course_type,start_date,\r\n"
							+ "date_of_completion,course_abbreviation,course_institute,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.course_name,c.div as div_grade,\r\n"
							+ "(case  when a.course_type_ot = null or  a.course_type_ot is null then  d.label \r\n"
							+ "else a.course_type_ot end ) \r\n" + "as course_type,\r\n"
							+ "a.start_date,a.date_of_completion,a.course_abbreviation,e.course_institute,b.personnel_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_army_course a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1'' \r\n"
							+ "inner join tb_psg_mstr_div_grade c on cast(c.id as character varying)= a.div_grade\r\n"
							+ "inner join t_domain_value d on d.codevalue = a.course_type and d.domainid=''COURSE_TYPE''\r\n"
							+ "inner join tb_psg_mstr_course_institute e on e.id=a.course_institute\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')') \r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,\r\n"
							+ "course_name character varying,div_grade character varying,course_type character varying, \r\n"
							+ "start_date timestamp without time zone,date_of_completion timestamp without time zone,\r\n"
							+ "course_abbreviation character varying, course_institute character varying,personal_no character varying,\r\n"
							+ "month character varying,year character varying,unit_sus_no character varying,unit_name character varying,\r\n"
							+ "	 form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)  ");

			int d = pstmt3.executeUpdate();

			// Award And Medal
			PreparedStatement pstmt4 = c.prepareStatement(
					"insert into tb_olap_awardsnmedal_details (category,date_of_award ,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,personal_no,\r\n"
							+ "month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ ")  \r\n"
							+ "SELECT category,date_of_award ,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,personal_no,month,year,\r\n"
							+ "unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n" + "\r\n"
							+ "FROM dblink('myconn',' select ma.award_cat as category,a.date_of_award ,a.unit,ov.bde_name as bde,od.div_name as div_subarea,oc.coprs_name as corps_area,  \r\n"
							+ "cv.cmd_name as command,a.authority,a.date_of_authority,m.medal_name as award_medal ,b.personnel_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_awardsnmedal a \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1'' \r\n"
							+ "left join tb_psg_mstr_award_category ma on cast(ma.id as character varying) = a.category_8\r\n"
							+ "left join orbat_view_bde ov on ov.sus_no = a.bde\r\n"
							+ "left join orbat_view_div od on od.sus_no = a.div_subarea\r\n"
							+ "left join orbat_view_corps oc on oc.sus_no = a.corps_area\r\n"
							+ "left join orbat_view_cmd cv on cv.sus_no = a.command\r\n"
							+ "left join tb_psg_mstr_medal m on cast(m.id as character varying)  = a.select_desc \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ " where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(category character varying,date_of_award timestamp without time zone,unit character varying,bde character varying,\r\n"
							+ "div_subarea character varying,corps_area character varying,command character varying,authority character varying,date_of_authority timestamp without time zone,\r\n"
							+ "award_medal character varying,personal_no character varying,month character varying,year character varying,unit_sus_no character varying,\r\n"
							+ "unit_name character varying, form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int e = pstmt4.executeUpdate();

			// BPET
			PreparedStatement pstmt5 = c.prepareStatement(
					"insert into tb_olap_bpet_details (bpet_qtr,bpet_result ,bpet_year ,conducted_at_unit,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT bpet_qtr,bpet_result ,bpet_year,conducted_at_unit,personal_no,month,year ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location  \r\n"
							+ "FROM dblink('myconn','select   bq.bpet_qt as bpet_qtr,a.bpet_result ,a.year as bpet_year, \r\n"
							+ " u.unit_name as conducted_at_unit,b.personnel_no as personal_no, \r\n"
							+ " date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ " date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location \r\n"
							+ "from tb_psg_census_bpet a \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''  \r\n"
							+ "left join tb_psg_mstr_bpet_qt bq on cast(bq.id as character varying) = a.bpet_qe  \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.unit_sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(bpet_qtr character varying,bpet_result character varying,bpet_year character varying,conducted_at_unit character varying, \r\n"
							+ "personal_no character varying,month character varying, year character varying, unit_sus_no character varying,unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying) ");

			int f = pstmt5.executeUpdate();

			// CHILDREN DETAILS
			PreparedStatement pstmt6 = c.prepareStatement(
					"insert into tb_olap_children_details (date_of_birth,relationship,specially_abled_child,adoted,pan_no,date_of_adpoted,name,\r\n"
							+ "child_service,service_exservice,child_personal_no,aadhar_no,personal_no,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location,age)  \r\n"
							+ "SELECT date_of_birth,relationship,specially_abled_child,adoted,pan_no,date_of_adpoted,name, \r\n"
							+ "child_service,service_exservice,child_personal_no,aadhar_no,personal_no ,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location,age\r\n"
							+ "FROM dblink('myconn',' select   a.date_of_birth, c.label as relationship,a.type as specially_abled_child, a.adoted as adopted_child,\r\n"
							+ "a.pan_no,a.date_of_adpoted,a.name,\r\n"
							+ "e.ex_servicemen as child_service,(case  when a.other_child_ser = null or  a.other_child_ser = '''' or a.other_child_ser is null then  a.if_child_ser\r\n"
							+ "                                 else a.other_child_ser end ) as service_exservice,  a.child_personal_no,aadhar_no,   b.personnel_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location,\r\n"
							+ "			date_part(''year'',age(now(),a.date_of_birth) ) as age\r\n"
							+ "from tb_psg_census_children a \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1'' \r\n"
							+ "left join t_domain_value c on c.codevalue = cast(a.relationship as character varying) and c.domainid=''CHILD_RELATIONSHIP''\r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = child_service and e.status=''active'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in  (''0'',''3'',''4'',''-1'')')\r\n"
							+ "AS t(date_of_birth timestamp without time zone,relationship character varying,specially_abled_child character varying,adoted character varying, \r\n"
							+ "pan_no character varying,date_of_adpoted timestamp without time zone,name character varying, \r\n"
							+ "child_service character varying,service_exservice character varying,child_personal_no character varying,aadhar_no character varying,\r\n"
							+ "personal_no character varying,year character varying,month character varying,unit_sus_no character varying,unit_name character varying,form_code_control  character varying,\r\n"
							+ "ct_part_i_ii character varying,type_of_location character varying,age character varying)\r\n"
							+ "");

			int g = pstmt6.executeUpdate();

			// FIRING STANDARD
			PreparedStatement pstmt7 = c.prepareStatement(
					"insert into tb_olap_firing_standard_details (firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,personal_no,month,year,unit_sus_no,unit_name,\r\n"
							+ "form_code_control,ct_part_i_ii,type_of_location)     \r\n"
							+ " SELECT firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,personal_no ,month,year ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ " FROM dblink('myconn','select    q.bpet_qt as firing_event_qtr,f.firing_result as firing_grade,a.year,u.unit_name as conducted_at_unit , \r\n"
							+ " b.personnel_no as personal_no, \r\n"
							+ " date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ " date_part(''year'', (SELECT current_timestamp)) as year , b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ " from tb_psg_census_firing_standard a \r\n"
							+ " inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id \r\n"
							+ " inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''  \r\n"
							+ " left join tb_psg_mstr_bpet_qt q on cast(q.id as character varying) = a.firing_event_qe  and q.status=''active'' \r\n"
							+ " left join tb_psg_mstr_firing_result f on cast(q.id as character varying) = a.firing_grade  and q.status=''active'' \r\n"
							+ " left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.firing_unit_sus_no and u.status_sus_no  = ''Active'' \r\n"
							+ " where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ " AS t(firing_event_qtr character varying,firing_grade character varying,firing_year character varying,conducted_at_unit character varying, \r\n"
							+ "  personal_no character varying, month character varying, year character varying , unit_sus_no character varying,unit_name character varying,form_code_control character varying,  \r\n"
							+ "ct_part_i_ii character varying,type_of_location character varying)\r\n" + "\r\n" + "  ");

			int h = pstmt7.executeUpdate();

			// VISIT TO FOREIGN COUNTRY
			PreparedStatement pstmt8 = c.prepareStatement(
					"insert into tb_olap_foreign_country (country,period,date_from,date_to,purpose_visit,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT country,period,date_from,date_to,purpose_visit,personal_no ,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select    q.name as country,a.period,a.from_dt as date_from,a.to_dt as date_to,\r\n"
							+ "p.visit_purpose as purpose_visit, b.personnel_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "\r\n" + "From tb_psg_census_foreign_country a  \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id \r\n"
							+ "inner join tb_psg_census_detail_p cp on cp.comm_id = b.id and cp.status=''1''\r\n"
							+ "left join tb_psg_foreign_country q on q.id = a.country\r\n"
							+ "left join tb_psg_mstr_purposeof_visit p on p.id = a.purpose_visit and p.status=''active'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(country character varying,period character varying,date_from timestamp without time zone,\r\n"
							+ "date_to timestamp without time zone,purpose_visit character varying,personal_no character varying ,month character varying, \r\n"
							+ "year character varying,unit_sus_no character varying,unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ "");

			int i = pstmt8.executeUpdate();

			// MEDICAL DETAILS
			PreparedStatement pstmt9 = c.prepareStatement(
					"insert into tb_olap_medical_details (authority,date_of_authority,shape,shape_status,shape_value,date_from,date_to,diagnosis,\r\n"
							+ "diagnosis_cause,diagnosis_code,clasification,shape_sub_value,diagnosis_1bx,date_from_1bx,date_to_1bx,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ ") \r\n"
							+ "SELECT authority,date_of_authority,shape,shape_status,shape_value,date_from,date_to,diagnosis,\r\n"
							+ "diagnosis_cause,diagnosis_code,clasification,shape_sub_value,diagnosis_1bx,date_from_1bx,date_to_1bx,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn',' select a.authority,a.date_of_authority,a.shape,a.shape_status,(case  when a.other = null or a.other is null then  a.shape_value\r\n"
							+ "else a.other end ) as shape_value,a.from_date as date_from,a.to_date as date_to,a.diagnosis, \r\n"
							+ "a.diagnosis_cause,a.diagnosis_code,a.clasification,a.shape_sub_value,a.diagnosis_1bx,a.from_date_1bx as date_from_1bx,a.to_date_1bx as date_to_1bx,\r\n"
							+ "b.personnel_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_medical_category a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(authority character varying ,date_of_authority timestamp without time zone,shape character varying,shape_status character varying, \r\n"
							+ "shape_value character varying,date_from timestamp without time zone,date_to timestamp without time zone,diagnosis character varying,\r\n"
							+ "diagnosis_cause character varying,diagnosis_code character varying,clasification character varying,shape_sub_value character varying,\r\n"
							+ "diagnosis_1bx character varying,date_from_1bx timestamp without time zone,date_to_1bx timestamp without time zone,\r\n"
							+ "personal_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int j = pstmt9.executeUpdate();

			// PROMOTIONAL EXAM DETAILS
			PreparedStatement pstmt10 = c.prepareStatement(
					"insert into tb_olap_promo_exam_details (authority,date_of_authority,exam ,date_of_passing,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT authority,date_of_authority,exam ,date_of_passing,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn',' select   a.authority,a.date_of_authority, (case  when a.exam_other = null or  a.exam_other = '''' or a.exam_other is null then  e.promo_exam\r\n"
							+ "                                 else a.exam_other end ) as exam ,a.date_of_passing,b.personnel_no as personal_no ,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_promo_exam a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1'' \r\n"
							+ "left join tb_psg_mstr_promotional_exam e on cast(e.id as character varying)=a.exam and e.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,exam character varying,date_of_passing  character varying,\r\n"
							+ "personal_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ "");

			int k = pstmt10.executeUpdate();

			// SPOUSE DETAILS
			PreparedStatement pstmt11 = c.prepareStatement(
					"insert into tb_olap_spouse_details (date_of_birth, name, date_of_marriage,nationality,date_of_divorce,marital_event,marital_status,place_of_birth,\r\n"
							+ " pan_card,spouse_service,spouse_personal_no, service_exservice,date_from_separated,date_to_separated,aadhar_number,personal_no,month,year,unit_sus_no,\r\n"
							+ "unit_name,form_code_control,ct_part_i_ii,type_of_location,age)  \r\n"
							+ "SELECT date_of_birth, name, date_of_marriage,nationality,date_of_divorce,marital_event,marital_status,place_of_birth,pan_card,spouse_service,spouse_personal_no,  \r\n"
							+ "service_exservice,date_from_separated,date_to_separated,aadhar_number,personal_no,month,year , unit_sus_no ,unit_name ,form_code_control,ct_part_i_ii,\r\n"
							+ "type_of_location,age\r\n"
							+ "FROM dblink('myconn','  select  a.date_of_birth,a.maiden_name as name,a.marriage_date as date_of_marriage,\r\n"
							+ "(case  when a.other_nationality = null  or a.other_nationality is null then n.nationality_name\r\n"
							+ "else a.other_nationality end ) as nationality,\r\n"
							+ "a.divorce_date as date_of_divorce, d.label as marital_event,m.marital_name as marital_status,a.place_of_birth,a.pan_card,e.ex_servicemen as spouse_service,\r\n"
							+ "a.spouse_personal_no,\r\n"
							+ "(case  when a.other_spouse_ser = null  or a.other_spouse_ser is null then a.if_spouse_ser\r\n"
							+ "else a.other_spouse_ser end ) as service_exservice,\r\n"
							+ "a.separated_form_dt as date_from_separated,a.separated_to_dt as date_to_separated,\r\n"
							+ "a.adhar_number as aadhar_number, b.personnel_no as personal_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year  ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location,\r\n"
							+ "date_part(''year'',age(now(),a.date_of_birth) ) as age\r\n"
							+ "from tb_psg_census_family_married a   \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id   \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''  \r\n"
							+ "left join tb_psg_mstr_nationality n on n.nationality_id = a.nationality \r\n"
							+ "left join t_domain_value d on d.codevalue = cast(a.type_of_event as character varying) and  domainid=''MARITAL_EVENT'' \r\n"
							+ "left join tb_psg_mstr_marital_status m on m.marital_id = a.marital_status  \r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = a.spouse_service and e.status=''active''  \r\n"
							+ " left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(date_of_birth timestamp without time zone, name character varying , date_of_marriage timestamp without time zone,nationality character varying, \r\n"
							+ "date_of_divorce timestamp without time zone, \r\n"
							+ "marital_event character varying,marital_status character varying,place_of_birth character varying,pan_card character varying,  \r\n"
							+ "spouse_service character varying, \r\n"
							+ "spouse_personal_no character varying,  service_exservice character varying,date_from_separated timestamp without time zone, \r\n"
							+ "date_to_separated timestamp without time zone, \r\n"
							+ "aadhar_number character varying,personal_no character varying,month character varying, year character varying , unit_sus_no character varying, \r\n"
							+ "unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying,age character varying)  \r\n"
							+ "\r\n" + " ");

			int l = pstmt11.executeUpdate();

			// Personal details
			// personal details
			PreparedStatement pstmt12 = c.prepareStatement(
					"  insert into tb_olap_personal_details (personal_no,cadet_no,course_no,course_comm,pers_name,gender,type_of_commission_granted,date_of_commission,date_of_seniority, \r\n"
							+ "rank,date_of_rank,date_of_birth,unit_sus_no,unit_posted_to,date_of_tos,parent_arm_service,regiment,authority,date_of_authority,\r\n"
							+ "country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,mother_tongue,religion,type_of_entry,aadhar_no,pan_no,commissioning_institute,\r\n"
							+ "traning_institute,maritial_status,blood_group,height,id_card_no,date_of_issuing,issuing_authority, visible_identification_marks,hair_colour,eye_colour,\r\n"
							+ "original_country,original_state,original_district,original_tehsil,\r\n"
							+ "presently_domicile_country,presently_domicile_state,presently_domicile_district,presently_domicile_tehsil,\r\n"
							+ "permanent_country,permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin,permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban,permanent_border_area, \r\n"
							+ "present_country,present_state,present_district,present_tehsil,present_village_town_city, \r\n"
							+ "present_pin ,present_nearest_railway_station,present_rural_urban_semi_urban,\r\n"
							+ "nok_name,nok_relation,nok_country,nok_state,nok_district,nok_tehsil, \r\n"
							+ "nok_village_town_city ,nok_pin, nok_nearest_railway_station,nok_rural_urban_semi_urban,\r\n"
							+ "nok_mobile_no,father_name,date_of_birth_father,father_aadhar_no,father_pan_no,father_place_of_birth,\r\n"
							+ "father_service,father_personal_no,mother_name,date_of_mother,mother_aadhar_no,mother_pan_no,\r\n"
							+ "mother_place_of_birth,mother_service,mother_personal_no,  pre_cadet_status,pre_cadet_designation, \r\n"
							+ "pre_cadet_name_of_employer,pre_cadet_competency,pre_cadet_gazetted_non_gazetted,pre_cadet_pensionable_civil_service,\r\n"
							+ "pre_cadet_service_no,pre_cadet_unit_regiment,date_pre_cadet_from,date_pre_cadet_to,pre_cadet_total_service_in_rank, pre_cadet_ncc_experience,\r\n"
							+ "rank_type,appointment,date_of_appointment,gmail,nic_mail ,mobile_no,date_of_casulaty, time_of_casuality ,onduty,\r\n"
							+ "batnpc_country,batnpc_state,batnpc_district,batnpc_tehsil,batnpc_village_town_city,\r\n"
							+ "batnpc_pin,batnpc_exact_place_area_post,batnpc_name_of_operation,batnpc_sector,batnpc_filed_area,batnpc_whether_on,\r\n"
							+ "batnpc_bde,batnpc_div,batnpc_corp,batnpc_command,batnpc_hospital_name,batnpc_hospital_location,\r\n"
							+ "batnpc_cause_of_casualty,batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ,batnpc_nok_informed,\r\n"
							+ "date_of_informing_batnpc,batnpc_time_of_informing,batnpc_method_of_informing,batnpc_category_of_casualty,\r\n"
							+ "army_act_sec,sub_clause,trialed_by,punishment_awarded ,discipline_type_of_entry,date_of_discipline_award , \r\n"
							+ "discipline_unit_name,date_from_which_change_in_seniority_is_effective,date_from_extension ,date_to_extension, \r\n"
							+ "seconded_to ,date_secondment_with_effect_from,cause_of_non_effective,date_of_non_effective,\r\n"
							+ "non_eff_country,non_eff_state, non_eff_district,non_eff_tehsil,\r\n"
							+ "non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ "non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ "deserter_arms_type,deserter_weapon ,date_of_desertion,cause_of_desertion,month,year,serv_type,unit_name,age,form_code_control,year_of_service,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT personal_no,cadet_no,course_no,course_comm,pers_name,gender,type_of_commission_granted,date_of_commission,date_of_seniority,  \r\n"
							+ "rank,date_of_rank,date_of_birth,\r\n"
							+ "unit_sus_no,unit_posted_to,date_of_tos,parent_arm_service,regiment,authority,date_of_authority,\r\n"
							+ "country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,mother_tongue,religion,type_of_entry,aadhar_no,pan_no,commissioning_institute,\r\n"
							+ "traning_institute,maritial_status,blood_group,height,id_card_no,date_of_issuing,issuing_authority, visible_identification_marks,hair_colour,eye_colour,\r\n"
							+ "original_country,original_state,original_district,original_tehsil,\r\n"
							+ "presently_domicile_country,presently_domicile_state,presently_domicile_district,presently_domicile_tehsil,\r\n"
							+ "permanent_country,permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin,permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban,permanent_border_area,\r\n"
							+ "present_country,present_state,present_district,present_tehsil,present_village_town_city,\r\n"
							+ "present_pin ,present_nearest_railway_station,present_rural_urban_semi_urban,\r\n"
							+ "nok_name,nok_relation,nok_country,nok_state,nok_district,nok_tehsil,\r\n"
							+ "nok_village_town_city ,nok_pin, nok_nearest_railway_station,nok_rural_urban_semi_urban,\r\n"
							+ "nok_mobile_no,father_name,date_of_birth_father,father_aadhar_no,father_pan_no,father_place_of_birth,\r\n"
							+ "father_service,father_personal_no,mother_name,date_of_mother,mother_aadhar_no,mother_pan_no,\r\n"
							+ "mother_place_of_birth,mother_service,mother_personal_no,  pre_cadet_status,pre_cadet_designation,\r\n"
							+ "pre_cadet_name_of_employer,pre_cadet_competency,pre_cadet_gazetted_non_gazetted,pre_cadet_pensionable_civil_service,\r\n"
							+ "pre_cadet_service_no,pre_cadet_unit_regiment,date_pre_cadet_from,date_pre_cadet_to,pre_cadet_total_service_in_rank, pre_cadet_ncc_experience,\r\n"
							+ "rank_type,appointment,date_of_appointment,gmail,nic_mail ,mobile_no,date_of_casulaty, time_of_casuality ,onduty,\r\n"
							+ "batnpc_country,batnpc_state,batnpc_district,batnpc_tehsil,batnpc_village_town_city,\r\n"
							+ "batnpc_pin,batnpc_exact_place_area_post,batnpc_name_of_operation,batnpc_sector,batnpc_filed_area,batnpc_whether_on,\r\n"
							+ "batnpc_bde,batnpc_div,batnpc_corp,batnpc_command,batnpc_hospital_name,batnpc_hospital_location,\r\n"
							+ "batnpc_cause_of_casualty,batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ,batnpc_nok_informed,\r\n"
							+ "date_of_informing_batnpc,batnpc_time_of_informing,batnpc_method_of_informing,batnpc_category_of_casualty,\r\n"
							+ "army_act_sec,sub_clause,trialed_by,punishment_awarded ,discipline_type_of_entry,date_of_discipline_award ,\r\n"
							+ "discipline_unit_name,date_from_which_change_in_seniority_is_effective,date_from_extension ,date_to_extension,\r\n"
							+ "seconded_to ,date_secondment_with_effect_from,cause_of_non_effective,date_of_non_effective,\r\n"
							+ "non_eff_country,non_eff_state, non_eff_district,non_eff_tehsil,\r\n"
							+ "non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ "non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ "deserter_arms_type,deserter_weapon ,date_of_desertion,cause_of_desertion,month,year,serv_type,unit_name,age,form_code_control,year_of_service,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select distinct cl.personnel_no,\r\n"
							+ "cl.cadet_no,cl.batch_no as course_no,cc.course_name as course_comm,cl.name as pers_name,g.gender_name as gender,\r\n"
							+ "cg.comn_name as type_of_commission_granted,cl.date_of_commission,cl.date_of_seniority,ran.description as rank,cl.date_of_rank,cl.date_of_birth,\r\n"
							+ "cl.unit_sus_no,jj.unit_name as unit_posted_to,cl.date_of_tos,arm.arm_desc as parent_arm_service,armm.arm_desc,cl.authority,cl.date_of_authority,\r\n"
							+ "ct.name as country_of_birth,st.state_name as state_of_birth,dt.district_name as district_of_birth,p.place_birth as place_of_birth,nati.nationality_name as nationality,\r\n"
							+ "mol.mothertounge as mother_tongue,rel.religion_name as religion,tne.label as type_of_entry,p.adhar_number as aadhar_no,p.pancard_number as pan_no,ins.institute_name as Commissioning_institute\r\n"
							+ ",ins2.institute_name as traning_institute,\r\n"
							+ "b.battalion_name as comm_bn,com.company_name  as comm_coy,\r\n"
							+ "b1.battalion_name as trn_bn,com1.company_name  as trn_coy,\r\n"
							+ "ms.marital_name as maritial_status, bg.blood_desc as blood_group,h.centimeter as height ,idc.id_card_no  ,idc.issue_dt as date_of_issuing, jjj.unit_name as issuing_authority  , idc.id_marks as visible_identification_marks\r\n"
							+ ",hc.hair_cl_name as hair_colour ,ec.eye_cl_name as eye_colour,cto.name as original_country,sto.state_name as original_state,dto.district_name as original_district,tto.city_name as original_tehsil,\r\n"
							+ "ctop.name as presently_domicile_country,stop.state_name as presently_domicile_state,dtop.district_name as presently_domicile_district,ttop.city_name as presently_domicile_tehsil,\r\n"
							+ "ctopp.name as permanent_country,stopp.state_name as permanent_state, dtopp.district_name as permanent_district,ttopp.city_name as permanent_tehsil,\r\n"
							+ "ca.permanent_village as permanent_village_town_city,ca.permanent_pin_code as permanent_pin,ca.permanent_near_railway_station as permanent_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as permanent_rural_urban_semi_urban,ca.permanent_border_area,pctop.name as present_country,pstop.state_name as present_state,pdtop.district_name  as present_district,pttop.city_name as present_tehsil,ca.present_village as present_village_town_city,\r\n"
							+ "ca.present_pin_code as present_pin ,ca.present_near_railway_station as present_nearest_railway_station,ca.present_rural_urban_semi as present_rural_urban_semi_urban\r\n"
							+ ",nok.nok_name,rela.relation_name as nok_relation,coun.name as nok_country,sta.state_name as nok_state,dis.district_name as nok_district,nteh.city_name as nok_tehsil,\r\n"
							+ "nok.nok_village as nok_village_town_city ,nok.nok_pin,nok.nok_near_railway_station as nok_nearest_railway_station,nok.nok_rural_urban_semi as nok_rural_urban_semi_urban,\r\n"
							+ "nok.nok_mobile_no,p.father_name,p.father_dob as date_of_birth_father,p.father_adhar_number as father_aadhar_no,p.father_pancard_number as father_pan_no,p.father_place_birth father_place_of_birth\r\n"
							+ ",exser.ex_servicemen as father_service,p.father_personal_no,p.mother_name,p.mother_dob as date_of_mother,p.mother_adhar_number as mother_aadhar_no,p.mother_pancard_number as mother_pan_no,\r\n"
							+ "p.mother_place_birth as mother_place_of_birth,mexser.ex_servicemen as mother_service,p.mother_personal_no,mcre.description as pre_cadet_status,\r\n"
							+ "cadet.designation as pre_cadet_designation,\r\n"
							+ "cadet.employee_name as pre_cadet_name_of_employer,spe.specialization as pre_cadet_competency,cadet.gazetted as pre_cadet_gazetted_non_gazetted,\r\n"
							+ "cadet.civil_service as pre_cadet_pensionable_civil_service,cadet.army_no as pre_cadet_service_no,cadet.unit_reg as pre_cadet_unit_regiment,\r\n"
							+ "cadet.from_date as date_pre_cadet_from,cadet.to_date as date_pre_cadet_to,\r\n"
							+ "cadet.total_service as pre_cadet_total_service_in_rank\r\n"
							+ ",ncc.otu as pre_cadet_ncc_experience ,td.label as rank_type,appn.description as appointment,ap.date_of_appointment,con.gmail,con.nic_mail,con.mobile_no ,\r\n"
							+ "bat.date_of_casuality as date_of_casulaty,bat.time_of_casuality ,bat.onduty,bcon.name as batnpc_country,bts.state_name as batnpc_state,\r\n"
							+ "bpd.district_name as batnpc_district,\r\n"
							+ "bpcc.city_name as batnpc_tehsil,bat.village as batnpc_village_town_city,bat.pin as batnpc_pin,bat.exact_place as batnpc_exact_place_area_post,\r\n"
							+ "bat.name_of_operation as batnpc_name_of_operation,bat.sector as batnpc_sector,bat.field_services as batnpc_filed_area,bat.whether_on as batnpc_whether_on\r\n"
							+ ",bd.bde_name as batnpc_bde,di.div_name as batnpc_div,co.coprs_name as batnpc_corp,cm.cmd_name as batnpc_command,bat.hospital_name as batnpc_hospital_name,\r\n"
							+ "bat.hospital_location as batnpc_hospital_location\r\n"
							+ ",bat.cause_of_casuality as batnpc_cause_of_casualty,bat.circumstances as batnpc_circumstances,bat.diagnosis as batnpc_diagnosis,\r\n"
							+ "bat.aid_to_civ as batnpc_aid_to_civ,bat.nok_informed as batnpc_nok_informed,\r\n"
							+ "bat.date_of_informing as date_of_informing_batnpc,bat.time_of_informing as batnpc_time_of_informing,bat.methodofinforming as batnpc_method_of_informing,\r\n"
							+ "bat.cause_of_casuality as batnpc_category_of_casualty,\r\n"
							+ "arse.army_act_sec as army_act_sec,subc.sub_clause,ditri.disc_trialed as trialed_by,disci.description as punishment_awarded,dictd.label as discipline_type_of_entry,disci.award_dt as date_of_discipline_award ,\r\n"
							+ "disor.unit_name as discipline_unit_name,chc.eff_date_of_seniority as date_from_which_change_in_seniority_is_effective,ext.fromdt as date_from_extension ,ext.todt as date_to_extension,\r\n"
							+ "sem.seconded_to ,sec.secondment_effect as date_secondment_with_effect_from,cau.causes_name as cause_of_non_effective,non.date_of_non_effective,\r\n"
							+ "ctopp.name as non_eff_country,stopp.state_name as non_eff_state, dtopp.district_name as non_eff_district,ttopp.city_name as non_eff_tehsil,\r\n"
							+ "ca.permanent_village as non_eff_village_town_city,ca.permanent_pin_code as non_eff_pin,ca.permanent_near_railway_station as non_eff_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as non_eff_rural_urban_semi_urban,ca.permanent_border_area as non_eff_border_area\r\n"
							+ ",detd.label as deserter_arms_type,dest.arm_type_weapon as deserter_weapon ,dest.dt_desertion as date_of_desertion,cdes.label as cause_of_desertion,\r\n"
							+ "			date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "			date_part(''year'', (SELECT current_timestamp)) as year,u.unit_name,\r\n"
							+ "			case  \r\n"
							+ "when cl.id in (select id from tb_psg_trans_proposed_comm_letter where status=''5'') then ''DESERTER''  \r\n"
							+ "when cl.id in (select id from tb_psg_trans_proposed_comm_letter where status=''4'') then ''NON EFFECTIVE'' \r\n"
							+ "when cl.id in (select comm_id from tb_psg_re_employment where re_emp_select =''2'' and status=1) then ''RE-EMPLOYMENT'' \r\n"
							+ "when cl.id in (select comm_id from tb_psg_re_employment where re_emp_select =''1'' and status=1) then ''RE-CALL FROM RESERVE'' \r\n"
							+ "else ''SERVING'' END as serv_type ,date_part(''year'',age(now(),cl.date_of_birth) ) as age,u.form_code_control,\r\n"
							+ "date_part(''year'',age(now(),cl.date_of_commission) ) as year_of_serving,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_trans_proposed_comm_letter cl\r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = cl.id and p.status=''1''\r\n"
							+ "inner join tb_psg_mstr_course_comm cc on cc.id=cl.course and cc.status=''active''\r\n"
							+ "inner join tb_psg_mstr_gender g on g.id = cl.gender and g.status=''active'' \r\n"
							+ "inner join tb_psg_mstr_type_of_commission cg on cg.id = cl.type_of_comm_granted  and cg.status=''active''\r\n"
							+ "inner join cue_tb_psg_rank_app_master ran on ran.id = cl.rank and\r\n"
							+ "upper(ran.level_in_hierarchy) = ''RANK'' and ran.parent_code =''0'' and  ran.code not in\r\n"
							+ "(''R009'',''R099'',''R400'',''R110'',''R203'',''R207'',''R307'',''R1001'',''R1002'',''TQ284'',''210'',''R200'',''R115'',''R205'',''R116'', \r\n"
							+ "''R117'',''R201'',''R208'',''R202'',''R128'',''R129'',''R306'',''R204'',''R114'') and ran.status_active = ''Active''\r\n"
							+ "inner join tb_miso_orbat_unt_dtl jj on jj.sus_no=cl.unit_sus_no and jj.status_sus_no=''Active''\r\n"
							+ "inner join tb_miso_orbat_arm_code  arm on arm.arm_code = cl.parent_arm   and SUBSTR(arm.arm_code,3,4)= ''00''\r\n"
							+ "left join tb_miso_orbat_arm_code  armm on armm.arm_code = cl.regiment and SUBSTR(armm.arm_code,1,2) IN (''07'',''08'') and armm.arm_code not in (''0700'',''0800'')\r\n"
							+ "inner join tb_psg_mstr_country ct on ct.id = p.country_birth and ct.status =''active''\r\n"
							+ "inner join tb_psg_mstr_state st on st.state_id = p.state_birth and st.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district dt on dt.district_id = p.district_birth and dt.status=''active''\r\n"
							+ "inner join tb_psg_mstr_nationality nati on nati.nationality_id=p.nationality and nati.status=''active''\r\n"
							+ "inner join tb_psg_mothertounge mol on mol.id=p.mother_tongue and mol.status=''active''\r\n"
							+ "inner join tb_psg_mstr_religion rel on rel.religion_id=p.religion and rel.status=''active''\r\n"
							+ "inner join tb_psg_ncc_type_entry tne on tne.id=p.ncc_type and tne.status=''active''\r\n"
							+ "inner join tb_psg_mstr_institute_list ins on ins.id =p.com_institute and ins.institute_type=''1''  and ins.status=''active''\r\n"
							+ "left join tb_psg_mstr_institute_list ins2 on ins2.id =p.training_institute and ins2.institute_type=''2'' and ins2.status=''active''\r\n"
							+ "left join tb_psg_mstr_battalion b on  b.id =p.com_bn_id and b.status=''active''\r\n"
							+ "left join tb_psg_mstr_company com on  com.id=p.com_company_id and com.status=''active''\r\n"
							+ "inner join tb_psg_census_language l on l.comm_id = cl.id and l.status=''1''\r\n"
							+ "inner join tb_psg_mstr_indian_language il on il.id =l.language  and il.status=''active'' \r\n"
							+ "left join tb_psg_mstr_institute i1 on i1.institute_id= ins2.id  and i1.status=''active''\r\n"
							+ "left join tb_psg_mstr_battalion b1 on b1.id=i1.bn_id  and b1.status=''active''\r\n"
							+ "left join tb_psg_mstr_company com1 on com1.id =i1.company_id  and com1.status=''active''\r\n"
							+ "inner join tb_psg_mstr_marital_status ms on ms.marital_id=p.marital_status and ms.status=''active''\r\n"
							+ "inner join tb_psg_mstr_blood bg on bg.id=p.blood_group and bg.status=''active''\r\n"
							+ "inner join tb_psg_mstr_height h on h.height_id=p.height and h.status=''active''\r\n"
							+ "inner join tb_psg_identity_card idc on idc.comm_id=cl.id  and idc.status=''1''\r\n"
							+ "inner join tb_miso_orbat_unt_dtl jjj on jjj.sus_no=idc.issue_authority and jjj.status_sus_no=''Active''\r\n"
							+ "inner join tb_psg_mstr_hair_colour hc on hc.id=idc.hair_colour  and hc.status=''active''\r\n"
							+ "inner join tb_psg_mstr_eye_colour ec on ec.id=idc.eye_colour and ec.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country cto on cto.id = p.org_country and cto.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state sto on sto.state_id = p.org_state and sto.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district dto on dto.district_id = p.org_district and dto.status=''active''\r\n"
							+ "inner join tb_psg_mstr_city tto on tto.city_id = p.org_tehsil and tto.status=''active''\r\n"
							+ "inner join tb_psg_census_address ca on ca.comm_id = cl.id and  ca.status=''1''\r\n"
							+ "inner join tb_psg_mstr_country ctop on ctop.id = ca.pre_country and ctop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state stop on stop.state_id = ca.pre_state and stop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district dtop on dtop.district_id = ca.pre_district and dtop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_city ttop on ttop.city_id = ca.pre_tesil and ttop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country ctopp on ctopp.id = ca.permanent_country and ctopp.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state stopp on stopp.state_id = ca.permanent_state and stopp.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district dtopp on dtopp.district_id = ca.permanent_district and dtopp.status=''active''\r\n"
							+ "inner join tb_psg_mstr_city ttopp on ttopp.city_id = ca.permanent_tehsil and ttopp.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country pctop on pctop.id = ca.present_country and pctop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state pstop on pstop.state_id = ca.present_state and pstop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district pdtop on pdtop.district_id = ca.pre_district and pdtop.status=''active''\r\n"
							+ "inner join tb_psg_mstr_city pttop on pttop.city_id = ca.present_tehsil and pttop.status=''active''\r\n"
							+ "inner join tb_psg_census_nok nok on nok.comm_id = cl.id and nok.status=''1''\r\n"
							+ "inner join tb_psg_mstr_relation rela on rela.id = nok.nok_relation and rela.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country coun on coun.id = nok.nok_country and coun.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state sta on sta.state_id = nok.nok_state and coun.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district dis on dis.district_id = nok.nok_district and dis.status=''active''\r\n"
							+ "inner join tb_psg_mstr_city nteh on nteh.city_id = nok.nok_tehsil and nteh.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen exser on cast(exser.id as character varying)  = p.father_service and exser.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen mexser on cast(mexser.id as character varying)  = p.mother_service  and mexser.status=''active''\r\n"
							+ "inner join tb_psg_census_cadet cadet on cadet.comm_id  = cl.id  and cadet.status=''1''\r\n"
							+ "inner join tb_psg_mstr_pre_cadet_status mcre on mcre.id  = cadet.status  and mcre.status=''active''\r\n"
							+ "left join tb_psg_mstr_specialization spe on spe.id  = cadet.competency  and spe.status=''active''\r\n"
							+ "inner join tb_psg_census_ncc_exp ncc on ncc.comm_id  = cl.id  and ncc.status=''1''\r\n"
							+ "inner join tb_psg_change_of_rank rk on rk.comm_id = cl.id and rk.status=''1''\r\n"
							+ "left join t_domain_value td on td.codevalue=cast(rk.rank_type  as char) and td.domainid=''OFFR_RANK_TYPE'' \r\n"
							+ "join cue_tb_psg_rank_app_master cue on cue.id=rk.rank  and upper(cue.level_in_hierarchy) = ''RANK'' and cue.parent_code =''0'' and \r\n"
							+ "cue.code not in (''R009'',''R099'',''R400'',''R110'',''R203'',''R207'',''R307'',''R1001'',''R1002'',''TQ284'',''210'', \r\n"
							+ "''R200'',''R115'',''R205'',''R116'',''R117'',''R201'',''R208'',''R202'',''R128'',''R129'',''R306'',''R204'',''R114'') and cue.status_active = ''Active''\r\n"
							+ "left join tb_psg_change_of_appointment ap on ap.comm_id=cl.id and ap.status=''1''\r\n"
							+ "left join cue_tb_psg_rank_app_master appn on appn.id=ap.appointment \r\n"
							+ "and upper(appn.level_in_hierarchy) = ''APPOINTMENT'' and appn.parent_code =''0'' and appn.status_active = ''Active'' \r\n"
							+ "left join tb_psg_census_contact_cda_account_details con on con.comm_id=cl.id and con.status=''1''\r\n"
							+ "left join tb_psg_census_battle_physical_casuality bat on bat.comm_id=cl.id  and bat.status=''1'' \r\n"
							+ "left join tb_psg_mstr_country bcon on cast(bcon.id as character varying) = bat.country and bcon.status=''active'' \r\n"
							+ "left join tb_psg_mstr_state bts on cast(bts.state_id as character varying) = bat.state and bts.status=''active''\r\n"
							+ "left join tb_psg_mstr_district bpd on cast(bpd.district_id as character varying) = bat.district and bpd.status=''active'' \r\n"
							+ "left join tb_psg_mstr_city bpcc on cast(bpcc.city_id as character varying) = bat.tehsil and bpcc.status=''active''\r\n"
							+ "left join orbat_view_bde bd on bd.sus_no=bat.bde\r\n"
							+ "left join orbat_view_div di on di.sus_no=bat.div_subarea\r\n"
							+ "left join orbat_view_corps co on co.sus_no=bat.corps_area \r\n"
							+ "left join  orbat_view_cmd cm on cm.sus_no=bat.command  \r\n"
							+ "left join tb_psg_census_discipline disci on disci.comm_id=cl.id and disci.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_mstr_army_act_sec arse on arse.id=disci.army_act_sec and arse.status=''active''\r\n"
							+ "left join tb_psg_mstr_sub_clause subc on subc.id=disci.sub_clause and subc.status=''active'' \r\n"
							+ "left join tb_psg_mstr_disc_trialed ditri on ditri.id=disci.trialed_by and ditri.status=''active''\r\n"
							+ "left join t_domain_value dictd on dictd.codevalue=cast(disci.type_of_entry as character varying) and dictd.domainid=''DISCIPLINE'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl disor on disor.sus_no=disci.unit_name and disor.status_sus_no=''Active''\r\n"
							+ "left join tb_psg_change_of_comission chc on chc.comm_id=cl.id and chc.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_extension_of_comission ext on ext.comm_id=cl.id and ext.status=''1''\r\n"
							+ "left join tb_psg_census_secondment sec on sec.comm_id=cl.id and sec.status=''1''\r\n"
							+ "left join tb_psg_mstr_seconded_to sem on sem.id=sec.seconded_to  and sem.status=''active''\r\n"
							+ "left join tb_psg_non_effective non on non.comm_id=cl.id and non.status=''1''\r\n"
							+ "left join tb_psg_mstr_cause_of_non_effective cau on cau.id=cast(non.cause_of_non_effective as integer)  and cau.status=''active''\r\n"
							+ "left join tb_psg_deserter dest on dest.comm_id=cl.id and dest.status=''1''\r\n"
							+ "left join t_domain_value detd on detd.codevalue=dest.arm_type and detd.domainid=''ARM_TYPE''\r\n"
							+ "left join t_domain_value cdes on cdes.codevalue=dest.desertion_cause and cdes.domainid=''CAUSE_OF_DESRTION''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on cl.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where cl.status not in (''0'',''3'',''-1'',''4'')') \r\n"
							+ "AS t(personal_no character varying,cadet_no character varying,course_no character varying,course_comm character varying,pers_name character varying,gender character varying,\r\n"
							+ "type_of_commission_granted character varying,date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,rank character varying,\r\n"
							+ "date_of_rank timestamp without time zone,date_of_birth timestamp without time zone,unit_sus_no character varying,unit_posted_to character varying,date_of_tos timestamp without time zone, parent_arm_service character varying,regiment character varying,\r\n"
							+ "authority character varying,date_of_authority timestamp without time zone,\r\n"
							+ "country_of_birth character varying,state_of_birth character varying,district_of_birth character varying,place_of_birth character varying,nationality character varying,\r\n"
							+ "mother_tongue character varying,religion character varying,type_of_entry character varying,aadhar_no character varying,pan_no character varying, commissioning_institute character varying,\r\n"
							+ "traning_institute character varying,\r\n"
							+ "comm_bn character varying, comm_coy character varying,\r\n"
							+ "trg_bn character varying,  trg_coy character varying,\r\n"
							+ "maritial_status character varying,blood_group character varying,height character varying,id_card_no character varying,date_of_issuing timestamp without time zone,issuing_authority character varying,\r\n"
							+ "visible_identification_marks character varying,hair_colour character varying,eye_colour character varying,\r\n"
							+ "original_country character varying,original_state character varying,original_district character varying,original_tehsil character varying,\r\n"
							+ "presently_domicile_country character varying,presently_domicile_state character varying,presently_domicile_district character varying,presently_domicile_tehsil character varying,\r\n"
							+ "permanent_country character varying,permanent_state character varying, permanent_district character varying, permanent_tehsil character varying,\r\n"
							+ "permanent_village_town_city character varying,permanent_pin character varying,permanent_nearest_railway_station character varying,\r\n"
							+ "permanent_rural_urban_semi_urban character varying,permanent_border_area character varying,\r\n"
							+ "present_country character varying,present_state character varying,present_district character varying,present_tehsil character varying,present_village_town_city character varying,\r\n"
							+ "present_pin character varying,present_nearest_railway_station character varying,present_rural_urban_semi_urban character varying,\r\n"
							+ "nok_name character varying,nok_relation character varying,nok_country character varying,nok_state character varying,nok_district character varying,nok_tehsil character varying,\r\n"
							+ "nok_village_town_city character varying,nok_pin character varying, nok_nearest_railway_station character varying,nok_rural_urban_semi_urban character varying,\r\n"
							+ "nok_mobile_no character varying,father_name character varying,date_of_birth_father timestamp without time zone,father_aadhar_no character varying,father_pan_no character varying,\r\n"
							+ "father_place_of_birth character varying,\r\n"
							+ "father_service character varying,father_personal_no character varying,mother_name character varying,date_of_mother timestamp without time zone,mother_aadhar_no character varying,\r\n"
							+ "mother_pan_no character varying,mother_place_of_birth character varying,mother_service character varying,mother_personal_no character varying, pre_cadet_status character varying,\r\n"
							+ "pre_cadet_designation character varying,\r\n"
							+ "pre_cadet_name_of_employer character varying,pre_cadet_competency character varying,pre_cadet_gazetted_non_gazetted character varying,pre_cadet_pensionable_civil_service character varying,\r\n"
							+ "pre_cadet_service_no character varying,pre_cadet_unit_regiment character varying,date_pre_cadet_from timestamp without time zone,date_pre_cadet_to timestamp without time zone,\r\n"
							+ "pre_cadet_total_service_in_rank character varying, pre_cadet_ncc_experience character varying ,  rank_type character varying,appointment character varying ,date_of_appointment timestamp without time zone\r\n"
							+ ",gmail character varying,nic_mail character varying ,mobile_no character varying,date_of_casulaty timestamp without time zone,\r\n"
							+ "time_of_casuality character varying,onduty character varying,\r\n"
							+ "batnpc_country character varying,batnpc_state character varying,batnpc_district character varying,\r\n"
							+ "batnpc_tehsil character varying,batnpc_village_town_city character varying,\r\n"
							+ "batnpc_pin character varying,batnpc_exact_place_area_post character varying,batnpc_name_of_operation character varying,batnpc_sector character varying,\r\n"
							+ "batnpc_filed_area character varying,batnpc_whether_on character varying,\r\n"
							+ "batnpc_bde character varying,batnpc_div character varying,batnpc_corp character varying,batnpc_command character varying,\r\n"
							+ "batnpc_hospital_name character varying,batnpc_hospital_location character varying,\r\n"
							+ "batnpc_cause_of_casualty character varying,batnpc_circumstances character varying,batnpc_diagnosis character varying,batnpc_aid_to_civ character varying,\r\n"
							+ "batnpc_nok_informed character varying, date_of_informing_batnpc timestamp without time zone,batnpc_time_of_informing character varying,\r\n"
							+ "batnpc_method_of_informing character varying,batnpc_category_of_casualty character varying,\r\n"
							+ "army_act_sec character varying,sub_clause character varying,trialed_by character varying,punishment_awarded character varying,discipline_type_of_entry character varying,\r\n"
							+ "date_of_discipline_award timestamp without time zone, discipline_unit_name character varying,\r\n"
							+ "date_from_which_change_in_seniority_is_effective timestamp without time zone,date_from_extension timestamp without time zone,date_to_extension timestamp without time zone,\r\n"
							+ "seconded_to character varying,date_secondment_with_effect_from timestamp without time zone,cause_of_non_effective character varying ,date_of_non_effective timestamp without time zone, \r\n"
							+ "non_eff_country character varying,non_eff_state character varying, non_eff_district character varying,non_eff_tehsil character varying,\r\n"
							+ "non_eff_village_town_city character varying, non_eff_pin character varying, non_eff_nearest_railway_station character varying,\r\n"
							+ "non_eff_rural_urban_semi_urban character varying, non_eff_border_area character varying,\r\n"
							+ "deserter_arms_type character varying,deserter_weapon character varying,date_of_desertion timestamp without time zone,cause_of_desertion	character varying\r\n"
							+ ",month character varying, year character varying,serv_type character varying,\r\n"
							+ "	 unit_name character varying,age character varying, form_code_control character varying,year_of_service  character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int o = pstmt12.executeUpdate();

			// Spuse QUALIFICATION details
			PreparedStatement pstmt15 = c.prepareStatement(
					"insert into tb_olap_spouse_qualification_details ( qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
							+ " examination_pass , specialization , degree,  personal_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT  qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
							+ " examination_pass , specialization , degree,  personal_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select  t.label as qualification_type,a.passing_year,(case  when a.class_other = null or a.class_other is null then g.div\r\n"
							+ "else a.class_other end ) as div_class, \r\n"
							+ "array_to_string(ARRAY(select sub.description  \r\n"
							+ "from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification where id=a.id), '','')) qsub \r\n"
							+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject, \r\n"
							+ "a.institute,a.authority,a.date_of_authority,d.examination_passed as examination_pass , \r\n"
							+ "(case  when a.specialization_other = null  or a.specialization_other is null then  s.specialization\r\n"
							+ "else a.specialization_other end ) as specialization ,md.degree, b.personnel_no as personal_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_spouse_qualification a  \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id    \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''  \r\n"
							+ "left join t_domain_value t on t.codevalue=cast(a.type as character varying)  and domainid=''QUALIFICATION_TYPE'' \r\n"
							+ "left join tb_psg_mstr_div_grade g on cast(g.id as character varying)  = a.div_class and g.status=''active''  \r\n"
							+ "left join tb_psg_census_subject cs on cast(cs.id as character varying) = a.subject   \r\n"
							+ "left join tb_psg_mstr_examination_passed d on d.id = a.examination_pass and d.status=''active'' \r\n"
							+ "left join tb_psg_mstr_specialization s on s.id = a.specialization and s.status=''active'' \r\n"
							+ "left join tb_psg_mstr_degree md on md.id = a.degree and md.status=''active'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(qualification_type character varying, passing_year character varying,  div_class character varying, subject character varying , \r\n"
							+ "institute character varying, authority character varying, date_of_authority timestamp without time zone,  examination_pass character varying , \r\n"
							+ "specialization character varying, degree character varying, personal_no character varying,  \r\n"
							+ "month character varying,year character varying, unit_sus_no character varying, unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ "\r\n" + "");

			int P = pstmt15.executeUpdate();

			PreparedStatement pstmt16 = c.prepareStatement(
					"insert into tb_olap_formation (sus_no,unit_name,cmd_name,corps_name,div_name,bde_name,location,nrs,approved_rejected_on,status_sus_no,type_of_force,ct_part_i_ii,\r\n"
							+ "comm_depart_date,compltn_arrv_date,creation_on,arm_code,arm_desc,form_code_control,sus_version,action)\r\n"
							+ "SELECT sus_no,unit_name,cmd_name,corps_name,div_name,bde_name,location,nrs,approved_rejected_on,status_sus_no,type_of_force,ct_part_i_ii,\r\n"
							+ "comm_depart_date,compltn_arrv_date,creation_on,arm_code,arm_desc,form_code_control,sus_version,action\r\n"
							+ "FROM dblink('myconn','SELECT \r\n" + "    a.sus_no,\r\n" + "    a.unit_name,\r\n"
							+ "    ab.cmd_name,\r\n" + "    ac.coprs_name,\r\n" + "    ad.div_name,\r\n"
							+ "    ae.bde_name,\r\n" + "    e.code_value AS location,\r\n"
							+ "    nr.code_value AS nrs,\r\n" + "    b.approved_rejected_on,\r\n"
							+ "    a.status_sus_no,\r\n" + "    x.label AS type_of_force,\r\n"
							+ "    a.ct_part_i_ii,\r\n" + "    a.comm_depart_date,\r\n" + "    a.compltn_arrv_date,\r\n"
							+ "    a.creation_on,\r\n" + "    a.arm_code,\r\n" + "    d.arm_desc,\r\n"
							+ "    a.form_code_control,\r\n" + "	a.sus_version,\r\n" + "    c.label AS action\r\n"
							+ "   FROM tb_miso_orbat_unt_dtl a\r\n"
							+ "     JOIN tb_miso_orbat_shdul_detl b ON a.id::numeric = b.letter_id\r\n"
							+ "     JOIN t_domain_value c ON c.domainid::text = ''SCHEDULETYPE''::text AND b.type_of_letter::text = c.codevalue::text\r\n"
							+ "     LEFT JOIN tb_miso_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n"
							+ "     LEFT JOIN tb_miso_orbat_code e ON a.code::text = e.code::text AND e.code_type::text = ''Location''::text\r\n"
							+ "     LEFT JOIN tb_miso_orbat_code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = ''Location''::text\r\n"
							+ "     LEFT JOIN t_domain_value x ON x.domainid::text = ''TYPEOFFORCE''::text AND x.codevalue::text = a.type_force::text\r\n"
							+ "     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
							+ "     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
							+ "     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
							+ "     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text') \r\n"
							+ "AS t(sus_no character varying,unit_name character varying,cmd_name character varying,corps_name character varying,\r\n"
							+ "	div_name character varying,bde_name character varying,location character varying,nrs character varying,approved_rejected_on timestamp without time zone,status_sus_no character varying,type_of_force character varying,ct_part_i_ii character varying,\r\n"
							+ "comm_depart_date timestamp without time zone,compltn_arrv_date timestamp without time zone,creation_on timestamp without time zone,arm_code character varying,arm_desc character varying,form_code_control character varying,sus_version character varying,\r\n"
							+ "	 action character varying)");

			int q = pstmt16.executeUpdate();

			/////// MEDICAL CATEGORY HISTORY
			PreparedStatement pstmt17 = c.prepareStatement(
					"insert into tb_olap_medical_categoryhistory  (shape,date_of_authority,status,cope,med_classification_lmc,month,year,personal_no,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ ")  \r\n"
							+ "SELECT shape,date_of_authority,status,cope,med_classification_lmc,month,year,personal_no ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "\r\n"
							+ "FROM dblink('myconn','select  a.shape,a.date_of_authority,a.status,a.cope,a.med_classification_lmc, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year , \r\n"
							+ "b.personnel_no as personal_no,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_medical_categoryhistory a  \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id    \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id  and p.status=''1'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''			\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(shape character varying,date_of_authority timestamp without time zone,status character varying,cope character varying, \r\n"
							+ " med_classification_lmc character varying,month character varying,year character varying,personal_no character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying) \r\n"
							+ "");

			int r = pstmt17.executeUpdate();

			// FIELD SERVICE

			PreparedStatement pstmt18 = c.prepareStatement(
					"insert into tb_olap_field_service (personal_no,field_area,authority,authority_date,operation_name,unit_location,  \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT personal_no,field_area,authority,authority_date,operation_name,unit_location, \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location  \r\n"
							+ "FROM dblink('myconn','select d.personnel_no as personnel_no ,c.field_area as field_area ,a.authority as authority,  \r\n"
							+ "a.authority_date as authority_date,  \r\n"
							+ "e.operation_name as operation_name,a.unit_location as unit_location, \r\n"
							+ "a.sus_id as sus_no,d.from_date as from_date,d.to_date as to_date,d.duration as duration,d.place as place, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_field_service_p a \r\n"
							+ "inner join tb_psg_mstr_field_area c on c.id = a.fd_services and c.status =''active'' \r\n"
							+ "inner join tb_psg_field_service_ch d on d.p_id = a.id  \r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=d.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.id = d.census_id and p.status=1 \r\n"
							+ "inner join tb_psg_mstr_operation e on e.id = a.operation and e.status=''active'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(personal_no character varying,field_area character varying,authority character varying,authority_date timestamp without time zone, \r\n"
							+ "operation_name character varying,unit_location character varying,sus_no character varying,from_date timestamp without time zone, \r\n"
							+ "to_date timestamp without time zone,duration character varying,place character varying,month character varying,year character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying) \r\n"
							+ "  ");

			int s = pstmt18.executeUpdate();

			PreparedStatement pstmt19 = c.prepareStatement(
					"insert into tb_olap_cue_psg_auth_held  (cmd_unit,corp_unit,div_unit,bde_unit, unit_name,unit_sus_no,\r\n"
							+ "arm_code,off_total_auth,jco_total_auth,or_total_auth,civ_total_auth,offr,jco,orr,civ)  \r\n"
							+ "SELECT cmd_unit,corp_unit,div_unit,bde_unit, unit_name,unit_sus_no,\r\n"
							+ "arm_code,off_total_auth,jco_total_auth,or_total_auth,civ_total_auth,offr,jco,orr,civ\r\n"
							+ "FROM dblink('myconn','SELECT fv.unit_name AS cmd_unit,\r\n"
							+ "    fvm.unit_name AS corp_unit,\r\n" + "    div.unit_name AS div_unit,\r\n"
							+ "    bde.unit_name AS bde_unit,\r\n" + "    orb.unit_name,\r\n"
							+ "    hld.unit_sus_no,\r\n" + "    orb.arm_code,\r\n"
							+ "    COALESCE(sum(n.base_auth + n.mod_auth + n.foot_auth::double precision) FILTER (WHERE r.parent_code::text = ''0''::text), ''0''::double precision) AS off_total_auth,\r\n"
							+ "    COALESCE(sum(n.base_auth + n.mod_auth + n.foot_auth::double precision) FILTER (WHERE r.parent_code::text = ''1,2''::text), ''0''::double precision) AS jco_total_auth,\r\n"
							+ "    COALESCE(sum(n.base_auth + n.mod_auth + n.foot_auth::double precision) FILTER (WHERE r.parent_code::text = ''3''::text), ''0''::double precision) AS or_total_auth,\r\n"
							+ "    COALESCE(sum(n.base_auth + n.mod_auth + n.foot_auth::double precision) FILTER (WHERE r.parent_code::text = ''4,5,6,7,8,9,10,11''::text), ''0''::double precision) AS civ_total_auth,\r\n"
							+ "    sum(hld.total_held) FILTER (WHERE hld.typ = ''OFFR''::text) AS offr,\r\n"
							+ "    sum(hld.total_held) FILTER (WHERE hld.typ = ''JCO''::text) AS jco,\r\n"
							+ "    sum(hld.total_held) FILTER (WHERE hld.typ = ''OR''::text) AS \"or\",\r\n"
							+ "    sum(hld.total_held) FILTER (WHERE hld.typ = ''CIV''::text) AS civ\r\n"
							+ "   FROM ( SELECT c.unit_sus_no,\r\n" + "            count(c.*) AS total_held,\r\n"
							+ "            ''OFFR''::text AS typ\r\n"
							+ "           FROM tb_psg_trans_proposed_comm_letter c\r\n"
							+ "          WHERE c.status::text <> ALL (ARRAY[''0''::character varying::text, ''3''::character varying::text, ''4''::character varying::text])\r\n"
							+ "          GROUP BY c.unit_sus_no\r\n" + "        UNION\r\n"
							+ "         SELECT j.unit_sus_no,\r\n" + "            count(j.*) AS total_held,\r\n"
							+ "            ''JCO''::text AS typ\r\n" + "           FROM tb_psg_census_jco_or_p j\r\n"
							+ "          WHERE (j.status <> ALL (ARRAY[0, 3, 4])) AND j.category::text = ''JCO''::text\r\n"
							+ "          GROUP BY j.unit_sus_no\r\n" + "        UNION\r\n"
							+ "         SELECT o.unit_sus_no,\r\n" + "            count(o.*) AS total_held,\r\n"
							+ "            ''OR''::text AS typ\r\n" + "           FROM tb_psg_census_jco_or_p o\r\n"
							+ "          WHERE (o.status <> ALL (ARRAY[0, 3, 4])) AND o.category::text = ''OR''::text\r\n"
							+ "          GROUP BY o.unit_sus_no\r\n" + "        UNION\r\n"
							+ "         SELECT cv.sus_no,\r\n" + "            count(cv.*) AS total_held,\r\n"
							+ "            ''CIV''::text AS typ\r\n" + "           FROM tb_psg_civilian_dtl cv\r\n"
							+ "          WHERE cv.status <> ALL (ARRAY[0, 3, 4])\r\n"
							+ "          GROUP BY cv.sus_no) hld\r\n"
							+ "     LEFT JOIN tb_miso_orbat_unt_dtl orb ON orb.sus_no::text = hld.unit_sus_no::text AND orb.status_sus_no::text = ''Active''::text\r\n"
							+ "     LEFT JOIN sus_pers_stdauth n ON n.sus_no = hld.unit_sus_no::text\r\n"
							+ "     LEFT JOIN cue_tb_psg_rank_app_master r ON n.rank_code::text = r.code::text\r\n"
							+ "     LEFT JOIN all_fmn_view fv ON orb.sus_no::text = hld.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 1) = \"substring\"(fv.form_code_control::text, 1, 1) AND fv.level_in_hierarchy::text = ''Command''::text\r\n"
							+ "     LEFT JOIN all_fmn_view fvm ON orb.sus_no::text = hld.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 3) = \"substring\"(fvm.form_code_control::text, 1, 3) AND fvm.level_in_hierarchy::text = ''Corps''::text\r\n"
							+ "     LEFT JOIN all_fmn_view div ON orb.sus_no::text = hld.unit_sus_no::text AND \"substring\"(orb.form_code_control::text, 1, 6) = \"substring\"(div.form_code_control::text, 1, 6) AND div.level_in_hierarchy::text = ''Division''::text\r\n"
							+ "     LEFT JOIN all_fmn_view bde ON orb.sus_no::text = hld.unit_sus_no::text AND orb.form_code_control::text = bde.form_code_control::text AND bde.level_in_hierarchy::text = ''Brigade''::text\r\n"
							+ "  GROUP BY fv.unit_name, fvm.unit_name, div.unit_name, bde.unit_name, orb.unit_name, hld.unit_sus_no, r.description, orb.arm_code')  \r\n"
							+ "AS t(cmd_unit character varying,corp_unit character varying,div_unit character varying,bde_unit character varying, unit_name character varying,\r\n"
							+ "         unit_sus_no character varying,arm_code character varying,off_total_auth character varying,jco_total_auth character varying,or_total_auth character varying,\r\n"
							+ "         civ_total_auth character varying,offr character varying,jco character varying,orr character varying,civ character varying)");

			int t = pstmt19.executeUpdate();

			PreparedStatement pstmt20 = c.prepareStatement(
					"insert into tb_olap_miso_orbat_unt_dtl (address,arm_code,code,code_type,comm_depart_date,compltn_arrv_date,condition,\r\n"
							+ "														convert_status,ct_part_i_ii,district,entity,\r\n"
							+ "entity_contd,form_code_admin,form_code_control,form_code_operation,is_unit_pending,letter_id,letter_no,level_c,level_d,modification,\r\n"
							+ "nrs_code,old_unit_status,parent_sus_no,remarks,softdelete,state,status_sus_no,sus_no,sus_no_for_comb_disint,sus_version,\r\n"
							+ "type_force,type_of_location,unit_army_hq,unit_cat,unit_name,version_no) \r\n"
							+ "	         		SELECT address,arm_code,code,code_type,comm_depart_date,compltn_arrv_date,condition,convert_status,ct_part_i_ii,district,entity,\r\n"
							+ "entity_contd,form_code_admin,form_code_control,form_code_operation,is_unit_pending,letter_id,letter_no,level_c,level_d,modification,\r\n"
							+ "nrs_code,old_unit_status,parent_sus_no,remarks,softdelete,state,status_sus_no,sus_no,sus_no_for_comb_disint,sus_version,\r\n"
							+ "type_force,type_of_location,unit_army_hq,unit_cat,unit_name,version_no\r\n"
							+ "	         		FROM dblink('myconn','select address,arm_code,code,code_type,comm_depart_date,compltn_arrv_date,condition,convert_status,ct_part_i_ii,district,entity,\r\n"
							+ "entity_contd,form_code_admin,form_code_control,form_code_operation,is_unit_pending,letter_id,letter_no,level_c,level_d,modification,\r\n"
							+ "nrs_code,old_unit_status,parent_sus_no,remarks,softdelete,state,status_sus_no,sus_no,sus_no_for_comb_disint,sus_version,\r\n"
							+ "type_force,type_of_location,unit_army_hq,unit_cat,unit_name,version_no\r\n"
							+ "from tb_miso_orbat_unt_dtl') \r\n"
							+ "	         		AS t(address character varying,arm_code character varying,code character varying,code_type character varying,comm_depart_date timestamp without time zone,\r\n"
							+ "						compltn_arrv_date timestamp without time zone,condition character varying,\r\n"
							+ "						convert_status character varying,ct_part_i_ii character varying,district character varying,entity character varying,\r\n"
							+ "						entity_contd character varying,form_code_admin character varying,form_code_control character varying,form_code_operation character varying,\r\n"
							+ "						is_unit_pending character varying,letter_id character varying,letter_no character varying,level_c character varying,\r\n"
							+ "						level_d character varying,modification character varying,nrs_code character varying,old_unit_status character varying,\r\n"
							+ "						parent_sus_no character varying,remarks character varying,softdelete character varying,state character varying,\r\n"
							+ "						status_sus_no character varying,sus_no character varying,sus_no_for_comb_disint character varying,sus_version character varying,type_force character varying,\r\n"
							+ "						type_of_location character varying,unit_army_hq character varying,unit_cat character varying,unit_name character varying,version_no character varying)");

			int u = pstmt20.executeUpdate();

			PreparedStatement pstmt21 = c
					.prepareStatement("insert into tb_olap_no_of_pers_auth_rank_wise (auth,rank,unit_sus_no) \r\n"
							+ "SELECT auth,rank,unit_sus_no\r\n" + "FROM dblink('myconn','select \r\n"
							+ "COALESCE(sum(base_auth + mod_auth + foot_auth),''0'') as total_auth,\r\n"
							+ "r.description,n.sus_no\r\n" + "from (\r\n" + " SELECT DISTINCT tbl_pers.sus_no,\r\n"
							+ "    tbl_pers.we_pe_no,\r\n" + "    tbl_pers.cat_id,\r\n" + "    tbl_pers.rank_cat,\r\n"
							+ "    tbl_pers.app_trd_code,\r\n" + "    tbl_pers.rank_code,\r\n"
							+ "    COALESCE(tbl_pers.auth_amt, 0::numeric::double precision) AS base_auth,\r\n"
							+ "    COALESCE(tbl_mod.amt_inc_dec, 0::numeric::double precision) AS mod_auth,\r\n"
							+ "    COALESCE(tbl_foot.amt_inc_dec, 0::numeric) AS foot_auth,\r\n"
							+ "    tbl_pers.arm_code AS arm,\r\n" + "    tbl_mod.modification\r\n"
							+ "   FROM ( SELECT tbl.sus_no,\r\n" + "            tbl.we_pe_no,\r\n"
							+ "            tbl.app_trd_code,\r\n" + "            tbl.cat_id,\r\n"
							+ "            tbl.rank_cat,\r\n" + "            tbl.rank_code,\r\n"
							+ "            tbl.auth_amt,\r\n" + "            tbl.arm_code\r\n"
							+ "           FROM ( SELECT tbl_appt.sus_no,\r\n"
							+ "                    tbl_appt.we_pe_no,\r\n"
							+ "                    tbl_appt.app_trd_code,\r\n"
							+ "                    tbl_appt.rank_cat,\r\n"
							+ "                    tbl_appt.rank_code,\r\n"
							+ "                    tbl_appt.arm_code,\r\n"
							+ "                    tbl_appt.auth_amt,\r\n" + "                    tbl_appt.cat_id\r\n"
							+ "                   FROM ( SELECT DISTINCT cue_tb_wepe_link_sus_perstransweapon.sus_no,\r\n"
							+ "                            cue_tb_wepe_link_sus_perstransweapon.wepe_pers_no AS we_pe_no,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.app_trd_code,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.rank_cat,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.rank AS rank_code,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.arm_code,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.auth_amt,\r\n"
							+ "                            cue_tb_miso_wepe_pers_det.category_of_persn AS cat_id\r\n"
							+ "                           FROM cue_tb_wepe_link_sus_perstransweapon\r\n"
							+ "                             JOIN cue_tb_miso_wepe_pers_det ON cue_tb_miso_wepe_pers_det.we_pe_no::text = cue_tb_wepe_link_sus_perstransweapon.wepe_pers_no::text\r\n"
							+ "                          WHERE cue_tb_wepe_link_sus_perstransweapon.status_pers::text = ''1''::text AND cue_tb_miso_wepe_pers_det.status::text = ''1''::text) tbl_appt) tbl) tbl_pers\r\n"
							+ "     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_pers_mdfs.we_pe_no,\r\n"
							+ "            string_agg(cue_tb_wepe_link_sus_pers_mdfs.modification::text, '',''::text) AS modification,\r\n"
							+ "            cue_tb_miso_wepe_pers_mdfs.rank,\r\n"
							+ "            cue_tb_miso_wepe_pers_mdfs.arm_code,\r\n"
							+ "            cue_tb_miso_wepe_pers_mdfs.rank_cat,\r\n"
							+ "            cue_tb_miso_wepe_pers_mdfs.cat_per,\r\n"
							+ "            cue_tb_miso_wepe_pers_mdfs.appt_trade,\r\n"
							+ "            sum(cue_tb_miso_wepe_pers_mdfs.amt_inc_dec) AS amt_inc_dec,\r\n"
							+ "            cue_tb_wepe_link_sus_pers_mdfs.sus_no\r\n"
							+ "           FROM cue_tb_miso_wepe_pers_mdfs,\r\n"
							+ "            cue_tb_wepe_link_sus_pers_mdfs\r\n"
							+ "          WHERE cue_tb_wepe_link_sus_pers_mdfs.we_pe_no::text = cue_tb_miso_wepe_pers_mdfs.we_pe_no::text AND cue_tb_wepe_link_sus_pers_mdfs.status::text = ''1''::text AND cue_tb_wepe_link_sus_pers_mdfs.modification::text = cue_tb_miso_wepe_pers_mdfs.modification::text\r\n"
							+ "          GROUP BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no, cue_tb_miso_wepe_pers_mdfs.rank, cue_tb_miso_wepe_pers_mdfs.arm_code, cue_tb_miso_wepe_pers_mdfs.rank_cat, cue_tb_miso_wepe_pers_mdfs.cat_per, cue_tb_miso_wepe_pers_mdfs.appt_trade, cue_tb_wepe_link_sus_pers_mdfs.sus_no\r\n"
							+ "          ORDER BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no) tbl_mod ON tbl_pers.we_pe_no::text = tbl_mod.we_pe_no::text AND tbl_pers.sus_no = tbl_mod.sus_no::text AND tbl_pers.app_trd_code::text = tbl_mod.appt_trade::text AND tbl_pers.rank_cat::text = tbl_mod.rank_cat::text AND tbl_pers.cat_id::text = tbl_mod.cat_per::text AND tbl_pers.rank_code::text = tbl_mod.rank::text AND tbl_pers.arm_code::text = tbl_mod.arm_code::text\r\n"
							+ "     LEFT JOIN ( SELECT cue_tb_miso_wepe_pers_footnotes.we_pe_no,\r\n"
							+ "            cue_tb_miso_wepe_pers_footnotes.arm_code,\r\n"
							+ "            cue_tb_miso_wepe_pers_footnotes.rank,\r\n"
							+ "            cue_tb_miso_wepe_pers_footnotes.rank_cat,\r\n"
							+ "            cue_tb_miso_wepe_pers_footnotes.category_of_personnel,\r\n"
							+ "            cue_tb_miso_wepe_pers_footnotes.appt_trade,\r\n"
							+ "            cue_tb_wepe_link_sus_pers_footnotes.sus_no,\r\n"
							+ "            sum(cue_tb_miso_wepe_pers_footnotes.amt_inc_dec) AS amt_inc_dec\r\n"
							+ "           FROM cue_tb_miso_wepe_pers_footnotes\r\n"
							+ "             JOIN cue_tb_wepe_link_sus_pers_footnotes ON cue_tb_miso_wepe_pers_footnotes.id = cue_tb_wepe_link_sus_pers_footnotes.foot_fk\r\n"
							+ "          WHERE cue_tb_wepe_link_sus_pers_footnotes.status::text = ''1''::text\r\n"
							+ "          GROUP BY cue_tb_miso_wepe_pers_footnotes.we_pe_no, cue_tb_miso_wepe_pers_footnotes.rank, cue_tb_miso_wepe_pers_footnotes.arm_code, cue_tb_miso_wepe_pers_footnotes.rank_cat, cue_tb_miso_wepe_pers_footnotes.category_of_personnel, cue_tb_miso_wepe_pers_footnotes.appt_trade, cue_tb_wepe_link_sus_pers_footnotes.sus_no) tbl_foot ON tbl_pers.we_pe_no::text = tbl_foot.we_pe_no::text AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.app_trd_code::text = tbl_foot.appt_trade::text AND tbl_pers.rank_cat::text = tbl_foot.rank_cat::text AND tbl_pers.cat_id::text = tbl_foot.category_of_personnel::text AND tbl_pers.rank_code::text = tbl_foot.rank::text AND tbl_pers.arm_code::text = tbl_foot.arm_code::text\r\n"
							+ "\r\n" + ") n\r\n"
							+ "inner join cue_tb_psg_rank_app_master r on r.code  = n.rank_code and upper(r.level_in_hierarchy) = upper(''Rank'')\r\n"
							+ "group by \r\n" + "r.description,\r\n" + "n.sus_no')\r\n"
							+ "AS t(auth character varying,rank character varying,unit_sus_no character varying)");

			int v = pstmt21.executeUpdate();

			PreparedStatement pstmt22 = c
					.prepareStatement("insert into tb_olap_no_of_pers_held_rank_wise (held,rank,unit_sus_no) \r\n"
							+ "SELECT held,rank,unit_sus_no\r\n"
							+ "FROM dblink('myconn','select count(c.personnel_no) as held,b.description as rank,c.unit_sus_no as sus_no\r\n"
							+ " from\r\n" + "tb_psg_trans_proposed_comm_letter c \r\n"
							+ "inner join cue_tb_psg_rank_app_master b on b.id=c.rank and upper(b.level_in_hierarchy) = ''RANK''\r\n"
							+ "group by b.description,c.unit_sus_no ')\r\n"
							+ "AS t(held character varying,rank character varying,unit_sus_no character varying)");

			int w = pstmt22.executeUpdate();

			/*
			 * ///// allergy details jco PreparedStatement pstmt23=c.
			 * prepareStatement("insert into tb_olap_allerg_details_jco (medicine,army_no,month,year) \r\n"
			 * +
			 * "													         SELECT medicine,army_no,month,year\r\n"
			 * +
			 * "													        FROM dblink('myconn','select a.medicine,b.army_no,date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
			 * +
			 * "													        date_part(''year'', (SELECT current_timestamp)) as year\r\n"
			 * +
			 * "													        from tb_psg_allergic_to_medicine_jco a\r\n"
			 * +
			 * "													        inner join tb_psg_census_jco_or_p b on b.id=a.jco_id \r\n"
			 * +
			 * "													        where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''-1'')') \r\n"
			 * +
			 * "													        AS t(medicine character varying,army_no character varying,\r\n"
			 * +
			 * "													        month character varying,year character varying)"
			 * );
			 * 
			 * int x=pstmt23.executeUpdate();
			 */

			/*
			 * ///// promo exam details jco PreparedStatement pstmt24=c.
			 * prepareStatement("insert into tb_olap_promo_exam_details_jco (authority,date_of_authority,exam,date_of_passing,army_no,month,year) \r\n"
			 * +
			 * " SELECT authority,date_of_authority,exam,date_of_passing,army_no,month,year\r\n"
			 * +
			 * "FROM dblink('myconn','select a.authority,a.date_of_authority,a.exam,a.date_of_passing,b.army_no as army_no,\r\n"
			 * +
			 * "cast(date_part(''month'', (SELECT current_timestamp)) as character varying) as month, \r\n"
			 * +
			 * "cast(date_part(''year'', (SELECT current_timestamp)) as character varying) as year\r\n"
			 * + "from tb_psg_census_promo_exam_jco a\r\n" +
			 * "inner join tb_psg_census_jco_or_p b on b.id = a.jco_id \r\n" +
			 * "where  a.status not in (''0'',''3'',''-1'') and b.status not in (''0'',''3'',''-1'')') \r\n"
			 * +
			 * "AS t(authority character varying,date_of_authority timestamp without time zone,exam character varying,\r\n"
			 * +
			 * "date_of_passing character varying,army_no character varying,month character varying,year character varying)"
			 * );
			 * 
			 * int y=pstmt24.executeUpdate();
			 */

			/// held officers
			PreparedStatement pstmt25 = c.prepareStatement("insert into tb_olap_held_officer (held,month,year)\r\n"
					+ "																	SELECT held,month,year\r\n"
					+ "																	FROM dblink('myconn','select count(*) as held,date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
					+ "																		         	                                                date_part(''year'', (SELECT current_timestamp)) as year\r\n"
					+ "																													         		 from\r\n"
					+ "																													         		tb_psg_trans_proposed_comm_letter c \r\n"
					+ "																																	inner join tb_psg_census_detail_p p on p.comm_id = c.id and p.status = ''1''\r\n"
					+ "																																	where c.status not in (''0'',''-1'',''3'',''4'')')\r\n"
					+ "																	AS t(held character varying,month character varying,year character varying)");

			int z = pstmt25.executeUpdate();

			/// auth officers
			/*
			 * PreparedStatement
			 * pstmt26=c.prepareStatement("insert into tb_olap_auth_officer (auth)\r\n" +
			 * "SELECT auth\r\n" + "FROM dblink('myconn','select\r\n" +
			 * "COALESCE(sum(base_auth + mod_auth + foot_auth),''0'') as auth\r\n" +
			 * "from (\\r\\n\" + \r\n" +
			 * "									         		\" SELECT DISTINCT tbl_pers.sus_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.cat_id,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.app_trd_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.rank_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    COALESCE(tbl_pers.auth_amt, 0::numeric::double precision) AS base_auth,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    COALESCE(tbl_mod.amt_inc_dec, 0::numeric::double precision) AS mod_auth,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    COALESCE(tbl_foot.amt_inc_dec, 0::numeric) AS foot_auth,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_pers.arm_code AS arm,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"    tbl_mod.modification\\r\\n\" + \r\n"
			 * +
			 * "									         		\"   FROM ( SELECT tbl.sus_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.app_trd_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.cat_id,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.rank_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.auth_amt,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            tbl.arm_code\\r\\n\" + \r\n"
			 * +
			 * "									         		\"           FROM ( SELECT tbl_appt.sus_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.app_trd_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.rank_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.arm_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.auth_amt,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                    tbl_appt.cat_id\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                   FROM ( SELECT DISTINCT cue_tb_wepe_link_sus_perstransweapon.sus_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_wepe_link_sus_perstransweapon.wepe_pers_no AS we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.app_trd_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.rank AS rank_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.arm_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.auth_amt,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                            cue_tb_miso_wepe_pers_det.category_of_persn AS cat_id\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                           FROM cue_tb_wepe_link_sus_perstransweapon\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                             JOIN cue_tb_miso_wepe_pers_det ON cue_tb_miso_wepe_pers_det.we_pe_no::text = cue_tb_wepe_link_sus_perstransweapon.wepe_pers_no::text\\r\\n\" + \r\n"
			 * +
			 * "									         		\"                          WHERE cue_tb_wepe_link_sus_perstransweapon.status_pers::text = ''1''::text AND cue_tb_miso_wepe_pers_det.status::text = ''1''::text) tbl_appt) tbl) tbl_pers\\r\\n\" + \r\n"
			 * +
			 * "									         		\"     LEFT JOIN ( SELECT cue_tb_wepe_link_sus_pers_mdfs.we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            string_agg(cue_tb_wepe_link_sus_pers_mdfs.modification::text, '',''::text) AS modification,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_mdfs.rank,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_mdfs.arm_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_mdfs.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_mdfs.cat_per,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_mdfs.appt_trade,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            sum(cue_tb_miso_wepe_pers_mdfs.amt_inc_dec) AS amt_inc_dec,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_wepe_link_sus_pers_mdfs.sus_no\\r\\n\" + \r\n"
			 * +
			 * "									         		\"           FROM cue_tb_miso_wepe_pers_mdfs,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_wepe_link_sus_pers_mdfs\\r\\n\" + \r\n"
			 * +
			 * "									         		\"          WHERE cue_tb_wepe_link_sus_pers_mdfs.we_pe_no::text = cue_tb_miso_wepe_pers_mdfs.we_pe_no::text AND cue_tb_wepe_link_sus_pers_mdfs.status::text = ''1''::text AND cue_tb_wepe_link_sus_pers_mdfs.modification::text = cue_tb_miso_wepe_pers_mdfs.modification::text\\r\\n\" + \r\n"
			 * +
			 * "									         		\"          GROUP BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no, cue_tb_miso_wepe_pers_mdfs.rank, cue_tb_miso_wepe_pers_mdfs.arm_code, cue_tb_miso_wepe_pers_mdfs.rank_cat, cue_tb_miso_wepe_pers_mdfs.cat_per, cue_tb_miso_wepe_pers_mdfs.appt_trade, cue_tb_wepe_link_sus_pers_mdfs.sus_no\\r\\n\" + \r\n"
			 * +
			 * "									         		\"          ORDER BY cue_tb_wepe_link_sus_pers_mdfs.we_pe_no) tbl_mod ON tbl_pers.we_pe_no::text = tbl_mod.we_pe_no::text AND tbl_pers.sus_no = tbl_mod.sus_no::text AND tbl_pers.app_trd_code::text = tbl_mod.appt_trade::text AND tbl_pers.rank_cat::text = tbl_mod.rank_cat::text AND tbl_pers.cat_id::text = tbl_mod.cat_per::text AND tbl_pers.rank_code::text = tbl_mod.rank::text AND tbl_pers.arm_code::text = tbl_mod.arm_code::text\\r\\n\" + \r\n"
			 * +
			 * "									         		\"     LEFT JOIN ( SELECT cue_tb_miso_wepe_pers_footnotes.we_pe_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_footnotes.arm_code,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_footnotes.rank,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_footnotes.rank_cat,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_footnotes.category_of_personnel,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_miso_wepe_pers_footnotes.appt_trade,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            cue_tb_wepe_link_sus_pers_footnotes.sus_no,\\r\\n\" + \r\n"
			 * +
			 * "									         		\"            sum(cue_tb_miso_wepe_pers_footnotes.amt_inc_dec) AS amt_inc_dec\\r\\n\" + \r\n"
			 * +
			 * "									         		\"           FROM cue_tb_miso_wepe_pers_footnotes\\r\\n\" + \r\n"
			 * +
			 * "									         		\"             JOIN cue_tb_wepe_link_sus_pers_footnotes ON cue_tb_miso_wepe_pers_footnotes.id = cue_tb_wepe_link_sus_pers_footnotes.foot_fk\\r\\n\" + \r\n"
			 * +
			 * "									         		\"          WHERE cue_tb_wepe_link_sus_pers_footnotes.status::text = ''1''::text\\r\\n\" + \r\n"
			 * +
			 * "									         		\"          GROUP BY cue_tb_miso_wepe_pers_footnotes.we_pe_no, cue_tb_miso_wepe_pers_footnotes.rank, cue_tb_miso_wepe_pers_footnotes.arm_code, cue_tb_miso_wepe_pers_footnotes.rank_cat, cue_tb_miso_wepe_pers_footnotes.category_of_personnel, cue_tb_miso_wepe_pers_footnotes.appt_trade, cue_tb_wepe_link_sus_pers_footnotes.sus_no) tbl_foot ON tbl_pers.we_pe_no::text = tbl_foot.we_pe_no::text AND tbl_pers.sus_no = tbl_foot.sus_no AND tbl_pers.app_trd_code::text = tbl_foot.appt_trade::text AND tbl_pers.rank_cat::text = tbl_foot.rank_cat::text AND tbl_pers.cat_id::text = tbl_foot.category_of_personnel::text AND tbl_pers.rank_code::text = tbl_foot.rank::text AND tbl_pers.arm_code::text = tbl_foot.arm_code::text\\r\\n\" + \r\n"
			 * + "									         		\"\\r\\n\" + \r\n" +
			 * "									         		\")')\r\n" +
			 * "AS t(auth character varying)");
			 * 
			 * int aa=pstmt26.executeUpdate();
			 */

			///// Language

			PreparedStatement pstmt60 = c.prepareStatement(
					"insert into tb_olap_language_details (foreign_language_proficiency, foreign_language,language_standard,language,authority,\r\n"
							+ "date_of_authority,foreign_language_exam_pass, personal_no ,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT foreign_language_proficiency, foreign_language,language_standard,language,authority,\r\n"
							+ "date_of_authority,foreign_language_exam_pass, personal_no ,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select e.name as foreign_language_proficiency,f.foreign_language_name as foreign_language,ls.name as language_standard, \r\n"
							+ "il.ind_language as language,a.authority,a.date_of_authority,a.f_exam_pass as foreign_language_exam_pass,\r\n"
							+ "b.personnel_no as personal_no ,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_language a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id\r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1'' \r\n"
							+ "inner join tb_psg_lang_proof e on  e.id= a.f_lang_prof and e.status=''active'' \r\n"
							+ "inner join tb_psg_mstr_foreign_language f on  f.id= a.foreign_language and f.status=''active''  \r\n"
							+ "inner join tb_psg_lang_std ls on  ls.id= a.lang_std and ls.status=''active''\r\n"
							+ "left join tb_psg_mstr_indian_language il on  il.id= a.language and il.status=''active''\r\n"
							+ " left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''    \r\n"
							+ " where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')') \r\n"
							+ "AS t(foreign_language_proficiency character varying, foreign_language character varying,language_standard character varying,language character varying,  \r\n"
							+ "authority character varying, date_of_authority timestamp without time zone,foreign_language_exam_pass character varying,  personal_no character varying\r\n"
							+ ",month character varying, year character varying, unit_sus_no  character varying, unit_name  character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)  \r\n"
							+ " ");

			int y1 = pstmt60.executeUpdate();

			///// Qualification

			PreparedStatement pstmt61 = c.prepareStatement(
					"insert into tb_olap_qualification_details (passing_year, qualification_type, authority, date_of_authority, institute, examination_pass, \r\n"
							+ "specialization , degree, div_grade_pct,  subject, personal_no,month,year, unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT passing_year, qualification_type, authority, date_of_authority, institute, examination_pass, \r\n"
							+ "specialization , degree, div_grade_pct,  subject, personal_no,month,year , unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.passing_year,e.label as qualification_type,a.authority,a.date_of_authority,\r\n"
							+ "a.institute,exa.examination_passed as examination_pass,\r\n"
							+ "(case  when a.specialization_other = null  or a.specialization_other is null then  sp.specialization\r\n"
							+ "else a.specialization_other end ) as specialization,td.degree,\r\n"
							+ "(case  when a.class_other = null or a.class_other is null then dc.div\r\n"
							+ "else a.class_other end ) as div_class,\r\n"
							+ "array_to_string(ARRAY(select sub.description \r\n"
							+ "from unnest(string_to_array((select subject from tb_psg_census_qualification where id=a.id), '','')) qsub\r\n"
							+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject,\r\n"
							+ "b.personnel_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_qualification a\r\n"
							+ "inner join tb_psg_trans_proposed_comm_letter b on b.id=a.comm_id  \r\n"
							+ "inner join tb_psg_census_detail_p p on p.comm_id = b.id and p.status=''1''\r\n"
							+ "left join t_domain_value e on  e.codevalue  = a.type  and  domainid=''QUALIFICATION_TYPE'' \r\n"
							+ "inner join tb_psg_mstr_examination_passed exa on exa.id=a.examination_pass  and exa.status=''active''\r\n"
							+ "inner join tb_psg_mstr_specialization sp on sp.id=a.specialization  and sp.status=''active''\r\n"
							+ "inner join  tb_psg_mstr_degree td on td.id =a.degree and td.status=''active'' \r\n"
							+ "inner join  tb_psg_mstr_div_grade dc on dc.id =a.div_class and dc.status=''active''\r\n"
							+ " left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')') \r\n"
							+ "AS t(passing_year integer, qualification_type character varying, authority character varying, date_of_authority timestamp without time zone, \r\n"
							+ "institute character varying, examination_pass character varying, \r\n"
							+ "specialization character varying, degree character varying, div_grade_pct character varying,  subject character varying,personal_no character varying, \r\n"
							+ "month character varying,year character varying , unit_sus_no character varying, unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying) ");

			int z1 = pstmt61.executeUpdate();

			// JCO/////////////////////
			///// allergy details jco

			PreparedStatement pstmt27 = c.prepareStatement(
					"insert into tb_olap_allerg_details_jco (medicine,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT medicine,army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.medicine,b.army_no,date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_allergic_to_medicine_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''JCO''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')') \r\n"
							+ "AS t(medicine character varying,army_no character varying,month character varying,year character varying,unit_sus_no\r\n"
							+ " character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying) ");

			int ab = pstmt27.executeUpdate();

			///// BPET details jco
			PreparedStatement pstmt28 = c.prepareStatement(
					"insert into tb_olap_bpet_details_jco(bpet_qtr,bpet_result ,bpet_year,conducted_at_unit,army_no,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT bpet_qtr,bpet_result,bpet_year,conducted_at_unit,army_no,year,month ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select   bq.bpet_qt as bpet_qtr,a.bpet_result ,a.year as bpet_year, \r\n"
							+ "u.unit_name as conducted_at_unit,b.army_no as army_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_bpet_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''JCO''\r\n"
							+ "left join tb_psg_mstr_bpet_qt bq on cast(bq.id as character varying) = a.bpet_qe \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.unit_sus_no  and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in   (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(bpet_qtr character varying,bpet_result character varying,bpet_year character varying,conducted_at_unit character varying,\r\n"
							+ "	 army_no character varying, year character varying,month character varying, unit_sus_no character varying,\r\n"
							+ "	 unit_name character varying,form_code_control  character varying,ct_part_i_ii  character varying,type_of_location  character varying )  \r\n"
							+ "");

			int ac = pstmt28.executeUpdate();

			///// Children details jco
			PreparedStatement pstmt29 = c.prepareStatement(
					"insert into tb_olap_census_children_jco (date_of_birth,relationship,type,adoted,pan_no,date_of_adpoted,name,\r\n"
							+ "child_service,if_child_ser,child_personal_no,aadhar_no,army_no,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location,age)  \r\n"
							+ "SELECT date_of_birth,relationship,type,adoted,pan_no,date_of_adpoted,name, \r\n"
							+ "child_service,if_child_ser,child_personal_no,aadhar_no,army_no ,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location,age\r\n"
							+ "FROM dblink('myconn','\r\n"
							+ "select   a.date_of_birth, c.label as relationship,a.type as specially_abled_child, a.adoted as adopted_child,\r\n"
							+ "a.pan_no,a.date_of_adpoted,a.name, \r\n" + "e.ex_servicemen as child_service,\r\n"
							+ " (case  when a.other_child_ser = null  or a.other_child_ser is null then  a.if_child_ser\r\n"
							+ "                                 else a.other_child_ser end ) as service_exservice,\r\n"
							+ "a.child_personal_no,a.aadhar_no, b.army_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location,\r\n"
							+ "                        date_part(''year'',age(now(),a.date_of_birth) ) as age\r\n"
							+ "from tb_psg_census_children_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''JCO''\r\n"
							+ "left join t_domain_value c on c.codevalue = cast(a.relationship as character varying) and c.domainid=''CHILD_RELATIONSHIP''\r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = child_service and e.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in  (''0'',''3'',''4'',''-1'') ')\r\n"
							+ "AS t(date_of_birth timestamp without time zone,relationship character varying,type character varying,adoted character varying, \r\n"
							+ "pan_no character varying,date_of_adpoted timestamp without time zone,name character varying, \r\n"
							+ "child_service character varying,if_child_ser character varying,child_personal_no character varying,aadhar_no character varying,\r\n"
							+ "army_no character varying,year character varying,month character varying,unit_sus_no character varying,\r\n"
							+ "unit_name character varying,form_code_control  character varying,ct_part_i_ii  character varying,type_of_location  character varying,age  character varying)\r\n"
							+ "");

			int ad = pstmt29.executeUpdate();

			///// Firing standard jco
			PreparedStatement pstmt30 = c.prepareStatement(
					"insert into tb_olap_census_firing_standard_jco (firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)     \r\n"
							+ " SELECT firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,army_no ,month,year ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ " FROM dblink('myconn','select    q.bpet_qt as firing_event_qtr,f.firing_result as firing_grade,a.year,u.unit_name as conducted_at_unit , \r\n"
							+ "b.army_no as personal_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,U.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_firing_standard_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''JCO''\r\n"
							+ "left join tb_psg_mstr_bpet_qt q on cast(q.id as character varying) = a.firing_event_qe  and q.status=''active'' \r\n"
							+ "left join tb_psg_mstr_firing_result f on cast(q.id as character varying) = a.firing_grade  and q.status=''active''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.firing_unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in  (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ " AS t(firing_event_qtr character varying,firing_grade character varying,firing_year character varying,conducted_at_unit character varying, \r\n"
							+ "  army_no character varying, month character varying, year character varying , unit_sus_no character varying,\r\n"
							+ "	  unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)  \r\n"
							+ " ");

			int ae = pstmt30.executeUpdate();

			// Prmotional Exam jco
			PreparedStatement pstmt31 = c.prepareStatement(
					"insert into tb_olap_promo_exam_details_jco (authority,date_of_authority,exam ,date_of_passing,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT authority,date_of_authority,exam ,date_of_passing,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select   a.authority,a.date_of_authority, \r\n"
							+ " (case  when a.exam_other = null or  a.exam_other is null then  e.promo_exam\r\n"
							+ "                                 else a.exam_other end ) as exam,\r\n"
							+ "a.date_of_passing,b.army_no as personal_no ,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name ,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_promo_exam_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''JCO''\r\n"
							+ "left join tb_psg_mstr_promotional_exam e on cast(e.id as character varying)=a.exam and e.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,exam character varying,date_of_passing  character varying,\r\n"
							+ "personal_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,\r\n"
							+ "	 form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ "	 ");

			int af = pstmt31.executeUpdate();

			// Spouse Details jco
			PreparedStatement pstmt32 = c.prepareStatement(
					" insert into tb_olap_spouse_details_jco (date_of_birth,maiden_name,marriage_date,nationality,divorce_date, authority,date_of_authority,\r\n"
							+ "type_of_event,marital_status,place_of_birth,pan_card,spouse_service,spouse_personal_no,if_spouse_ser,initiated_from,adhar_number,separated_from_dt\r\n"
							+ ",separated_to_dt,month,year ,unit_sus_no ,unit_name,form_code_control,army_no,ct_part_i_ii,type_of_location,age)\r\n"
							+ "\r\n"
							+ "SELECT date_of_birth,maiden_name,marriage_date,nationality,divorce_date, authority,date_of_authority,\r\n"
							+ "type_of_event,marital_status,place_of_birth,pan_card,spouse_service,spouse_personal_no,if_spouse_ser,initiated_from,adhar_number,separated_from_dt\r\n"
							+ ",separated_to_dt,month,year ,unit_sus_no ,unit_name,form_code_control,army_no,ct_part_i_ii,type_of_location,age\r\n"
							+ "\r\n"
							+ "FROM dblink('myconn','select  a.date_of_birth,a.maiden_name,a.marriage_date,\r\n"
							+ "(case  when a.other_nationality = null  or a.other_nationality is null then n.nationality_name\r\n"
							+ "else a.other_nationality end ) as nationality,\r\n"
							+ "a.divorce_date,a.authority,a.date_of_authority,\r\n"
							+ "d.label as marital_event,m.marital_name as marital_status,a.place_of_birth,a.pan_card,e.ex_servicemen as spouse_service,a.spouse_personal_no,\r\n"
							+ "(case  when a.other_spouse_ser = null  or a.other_spouse_ser is null then a.if_spouse_ser\r\n"
							+ "else a.other_spouse_ser end ) as service_exservice,\r\n" + "a.initiated_from \r\n"
							+ ",a.adhar_number,a.separated_from_dt,a.separated_to_dt, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,u.unit_name,b.unit_sus_no ,u.form_code_control,b.army_no,u.ct_part_i_ii,u.type_of_location\r\n"
							+ ",date_part(''year'',age(now(),a.date_of_birth) ) as age\r\n"
							+ "                        from tb_psg_census_family_married_jco a   \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id and  b.status=''1'' and  b.category =''JCO''  \r\n"
							+ "left join tb_psg_mstr_nationality n on n.nationality_id = a.nationality \r\n"
							+ "left join t_domain_value d on d.codevalue = cast(a.type_of_event as character varying) and  domainid=''MARITAL_EVENT'' \r\n"
							+ "left join tb_psg_mstr_marital_status m on m.marital_id = a.marital_status  \r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = a.spouse_service and e.status=''active''  \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') and n.nationality_name is not null')\r\n"
							+ "AS t(date_of_birth timestamp without time zone,maiden_name character varying,marriage_date timestamp without time zone,nationality character varying,divorce_date timestamp without time zone, authority character varying,date_of_authority timestamp without time zone,\r\n"
							+ "type_of_event character varying,marital_status character varying,place_of_birth character varying,pan_card character varying,spouse_service character varying,spouse_personal_no character varying,if_spouse_ser character varying,initiated_from character varying,adhar_number character varying,separated_from_dt timestamp without time zone\r\n"
							+ ",separated_to_dt timestamp without time zone,month character varying,year character varying,\r\n"
							+ " unit_sus_no character varying,unit_name character varying,form_code_control character varying,\r\n"
							+ "  army_no character varying,ct_part_i_ii character varying,type_of_location character varying,age character varying)");

			int ag = pstmt32.executeUpdate();

			// Award medal jco
			PreparedStatement pstmt33 = c.prepareStatement(
					"insert into tb_olap_awardsnmedal_details_jco (category,date_of_award,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,\r\n"
							+ "army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT category,date_of_award,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,\r\n"
							+ "army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select td.award_cat as category ,am.date_of_award,am.unit,bd.bde_name as bde,di.div_name as div_subarea,co.coprs_name as corps_area,cm.cmd_name as command,\r\n"
							+ "am.authority,am.date_of_authority,mm.medal_name as award_medal,jc.army_no,\r\n"
							+ "cast(date_part(''month'', (SELECT current_timestamp)) as character varying) as month, \r\n"
							+ "cast(date_part(''year'', (SELECT current_timestamp)) as character varying) as year,\r\n"
							+ "jc.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_awardsnmedal_jco am  \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id = am.jco_id  and jc.category = ''JCO'' \r\n"
							+ "inner join  orbat_view_cmd cm on cm.sus_no=am.command \r\n"
							+ "inner join orbat_view_corps co on co.sus_no=am.corps_area\r\n"
							+ "inner join orbat_view_div di on di.sus_no=am.div_subarea \r\n"
							+ "inner join orbat_view_bde bd on bd.sus_no=am.bde \r\n"
							+ "inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\r\n"
							+ "inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer) \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = jc.unit_sus_no and u.status_sus_no  =''Active''\r\n"
							+ "where  am.status not in (''0'',''3'',''-1'') and jc.status not in (''0'',''3'',''-1'',''4'')\r\n"
							+ "') \r\n"
							+ "AS t(category  character varying,date_of_award  timestamp without time zone,unit  character varying,bde character varying,div_subarea character varying,\r\n"
							+ "corps_area character varying,command character varying,authority character varying,\r\n"
							+ "date_of_authority timestamp without time zone,award_medal character varying,army_no character varying,\r\n"
							+ "month character varying,year character varying,unit_sus_no character varying,unit_name character varying ,\r\n"
							+ "	 form_code_control character varying, ct_part_i_ii character varying, type_of_location character varying)");

			int ai = pstmt33.executeUpdate();

			/// qualification jco
			PreparedStatement pstmt34 = c.prepareStatement("\r\n"
					+ "insert into tb_olap_qualification_details_jco (passing_year, type, authority, date_of_authority, institute, examination_pass, \r\n"
					+ "specialization , degree, div_class,  subject, army_no,month,year, unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
					+ "SELECT passing_year, type, authority, date_of_authority, institute, examination_pass, \r\n"
					+ "specialization , degree, div_class,  subject, army_no,month,year , unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
					+ "FROM dblink('myconn','select a.passing_year,e.label as type,a.authority,a.date_of_authority,a.institute,exa.examination_passed as examination_pass,\r\n"
					+ "(case  when a.specialization_other = null  or a.specialization_other is null then  sp.specialization\r\n"
					+ "else a.specialization_other end ) as specialization,td.degree,\r\n"
					+ "(case  when a.class_other = null or a.class_other is null then dc.div\r\n"
					+ "else a.class_other end ) as div_class,array_to_string(ARRAY(select sub.description \r\n"
					+ "from unnest(string_to_array((select subject from tb_psg_census_qualification_jco where id=a.id), '','')) qsub\r\n"
					+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject,\r\n"
					+ "p.army_no,\r\n" + "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
					+ "date_part(''year'', (SELECT current_timestamp)) as year ,p.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
					+ "from tb_psg_census_qualification_jco a\r\n"
					+ "inner join tb_psg_census_jco_or_p p on a.jco_id = p.id and p.status=''1'' and p.category =''JCO''\r\n"
					+ "left join t_domain_value e on  e.codevalue  = a.type  and  domainid=''QUALIFICATION_TYPE''\r\n"
					+ "inner join tb_psg_mstr_examination_passed exa on exa.id=a.examination_pass  and exa.status=''active''\r\n"
					+ "inner join tb_psg_mstr_specialization sp on sp.id=a.specialization  and sp.status=''active''\r\n"
					+ "inner join  tb_psg_mstr_degree td on td.id =a.degree and td.status=''active'' \r\n"
					+ "inner join  tb_psg_mstr_div_grade dc on dc.id =a.div_class and dc.status=''active''\r\n"
					+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = p.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
					+ "where  (a.status not in (''0'',''3'',''-1'')) and p.status not in (''0'',''3'',''4'',''-1'') ') \r\n"
					+ "AS t(passing_year integer, type character varying, authority character varying, date_of_authority timestamp without time zone, \r\n"
					+ "institute character varying, examination_pass character varying, \r\n"
					+ "specialization character varying, degree character varying, div_class character varying,  subject character varying,army_no character varying, \r\n"
					+ "month character varying,year character varying , unit_sus_no character varying, unit_name character varying,\r\n"
					+ "	 form_code_control  character varying,ct_part_i_ii  character varying,type_of_location  character varying) ");

			int aj = pstmt34.executeUpdate();

			/// Spouse qualification jco
			PreparedStatement pstmt35 = c.prepareStatement(
					"insert into tb_olap_spouse_qualification_jco (qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
							+ " examination_pass , specialization , degree,  army_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT  qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
							+ " examination_pass , specialization , degree,  army_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select t.label as qualification_type,a.passing_year,\r\n"
							+ "(case  when a.class_other = null or a.class_other is null then g.div\r\n"
							+ "else a.class_other end ) as div_class,\r\n"
							+ "array_to_string(ARRAY(select sub.description  \r\n"
							+ "from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification_jco where id=a.id), '','')) qsub \r\n"
							+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject, \r\n"
							+ "a.institute,a.authority,a.date_of_authority,d.examination_passed as examination_pass , \r\n"
							+ "(case  when a.specialization_other = null  or a.specialization_other is null then  s.specialization\r\n"
							+ "else a.specialization_other end ) as specialization,md.degree,p.army_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,p.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_spouse_qualification_jco a   \r\n"
							+ "inner join tb_psg_census_jco_or_p  p on p.id = a.jco_id and p.status=''1''  and p.category =''JCO''\r\n"
							+ "left join t_domain_value t on t.codevalue=cast(a.type as character varying)  and domainid=''QUALIFICATION_TYPE'' \r\n"
							+ "left join tb_psg_mstr_div_grade g on cast(g.id as character varying)  = a.div_class and g.status=''active''  \r\n"
							+ "left join tb_psg_census_subject cs on cast(cs.id as character varying) = a.subject   \r\n"
							+ "left join tb_psg_mstr_examination_passed d on d.id = a.examination_pass and d.status=''active'' \r\n"
							+ "left join tb_psg_mstr_specialization s on s.id = a.specialization and s.status=''active'' \r\n"
							+ "left join tb_psg_mstr_degree md on md.id = a.degree and md.status=''active'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = p.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and p.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(qualification_type character varying, passing_year character varying,  div_class character varying, subject character varying , \r\n"
							+ "institute character varying, authority character varying, date_of_authority timestamp without time zone,  examination_pass character varying , \r\n"
							+ "specialization character varying, degree character varying, army_no character varying,  \r\n"
							+ "month character varying,year character varying, unit_sus_no character varying, unit_name character varying,\r\n"
							+ "	 form_code_control character varying, ct_part_i_ii character varying, type_of_location character varying)\r\n"
							+ "	 ");

			int ak = pstmt35.executeUpdate();

			// Field Service jco
			PreparedStatement pstmt36 = c.prepareStatement(
					"insert into tb_olap_field_service_jco (army_no,field_area,authority,authority_date,operation_name,unit_location,  \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT army_no,field_area,authority,authority_date,operation_name,unit_location, \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select d.personnel_no as army_no ,c.field_area as field_area ,a.authority as authority,  \r\n"
							+ "a.authority_date as authority_date,  e.operation_name as operation_name,a.unit_location as unit_location, \r\n"
							+ "a.sus_id as sus_no,d.from_date as from_date,d.to_date as to_date,d.duration as duration,d.place as place, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,jc.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_field_service_p_jco a \r\n"
							+ "inner join tb_psg_mstr_field_area c on c.id = a.fd_services and c.status =''active'' \r\n"
							+ "inner join tb_psg_field_service_ch_jco d on d.p_id = a.id  \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id=d.jco_id and jc.category =''JCO''\r\n"
							+ "inner join tb_psg_mstr_operation e on e.id = a.operation and e.status=''active'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = jc.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and jc.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(army_no character varying,field_area character varying,authority character varying,authority_date timestamp without time zone, \r\n"
							+ "operation_name character varying,unit_location character varying,sus_no character varying,from_date timestamp without time zone, \r\n"
							+ "to_date timestamp without time zone,duration character varying,place character varying,month character varying,year character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying\r\n"
							+ "	 ,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying) ");

			int al = pstmt36.executeUpdate();

			// Medical category jco
			PreparedStatement pstmt37 = c.prepareStatement(
					"insert into tb_olap_medical_category_jco (authority,date_of_authority,shape,shape_status,shape_value,from_date,to_date,diagnosis,\r\n"
							+ "clasification,shape_sub_value,diagnosis_1bx,from_date_1bx,to_date_1bx,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT authority,date_of_authority,shape,shape_status,shape_value,from_date,to_date,diagnosis,clasification,shape_sub_value,diagnosis_1bx,\r\n"
							+ "from_date_1bx,to_date_1bx,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.shape,a.shape_status,\r\n"
							+ "(case  when a.other = null or a.other is null then  a.shape_value\r\n"
							+ "else a.other end ) as shape_value,			\r\n"
							+ "a.from_date as from_date,a.to_date as to_date,a.diagnosis,\r\n"
							+ "a.clasification,a.shape_sub_value,a.diagnosis_1bx,a.from_date_1bx,a.to_date_1bx,\r\n"
							+ "jc.army_no,\r\n" + "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,jc.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_medical_category_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id = a.jco_id  and jc.status = ''1'' and jc.category = ''JCO'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on jc.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and jc.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(authority character varying ,date_of_authority timestamp without time zone,shape character varying,shape_status character varying, \r\n"
							+ "shape_value character varying,from_date timestamp without time zone,to_date timestamp without time zone,diagnosis character varying,clasification character varying,\r\n"
							+ "shape_sub_value character varying,diagnosis_1bx character varying,from_date_1bx timestamp without time zone,to_date_1bx timestamp without time zone,\r\n"
							+ "army_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,\r\n"
							+ "	 form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int am = pstmt37.executeUpdate();

			// Language jco
			PreparedStatement pstmt38 = c.prepareStatement(
					"insert into tb_olap_language_jco (authority,date_of_authority,f_exam_pass,initiated_from ,f_lang_prof,foreign_language,lang_std,\r\n"
							+ "language,unit_name,unit_sus_no ,form_code_control,army_no,month,year,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT authority,date_of_authority,f_exam_pass,initiated_from ,f_lang_prof,foreign_language,lang_std,\r\n"
							+ "language,army_no,month,year,unit_name,unit_sus_no ,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.f_exam_pass,a.initiated_from ,e.name as f_lang_prof,f.foreign_language_name as foreign_language,ls.name as lang_std,\r\n"
							+ "il.ind_language as language,b.army_no,date_part(''month'', (SELECT current_timestamp)) as month,date_part(''year'', (SELECT current_timestamp)) as year,u.unit_name,b.unit_sus_no ,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_language_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id and b.status=''1''  and b.category= ''JCO''\r\n"
							+ "inner join tb_psg_lang_proof e on  e.id= a.f_lang_prof and e.status=''active'' \r\n"
							+ "inner join tb_psg_mstr_foreign_language f on  f.id= a.foreign_language and f.status=''active''\r\n"
							+ "inner join tb_psg_lang_std ls on  ls.id= a.lang_std and ls.status=''active''\r\n"
							+ "left join tb_psg_mstr_indian_language il on  il.id= a.language and il.status=''active''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')\r\n"
							+ "')\r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,f_exam_pass character varying,initiated_from character varying,f_lang_prof character varying,foreign_language character varying,lang_std character varying,\r\n"
							+ "language character varying,unit_name character varying,unit_sus_no character varying,form_code_control character varying,\r\n"
							+ "	 army_no character varying,month character varying,year character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ " ");

			int an = pstmt38.executeUpdate();

			/// Medical category history jco
			PreparedStatement pstmt39 = c.prepareStatement(
					"insert into tb_olap_medical_categoryhistory_jco  (shape,date_of_authority,cope,med_classification_lmc,month,year,army_no,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT shape,date_of_authority,cope,med_classification_lmc,month,year,army_no ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select  a.shape,a.date_of_authority,a.cope,a.med_classification_lmc, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year , \r\n"
							+ "b.army_no as army_no ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location \r\n"
							+ "from tb_psg_medical_categoryhistory_jco a  \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status =''1''  and b.category =''JCO''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(shape character varying,date_of_authority timestamp without time zone,cope character varying, \r\n"
							+ " med_classification_lmc character varying,month character varying,year character varying,army_no character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int ao = pstmt39.executeUpdate();

			/// Army Course jco
			PreparedStatement pstmt40 = c.prepareStatement(
					"insert into tb_olap_army_course_details_jco (authority,date_of_authority,course_name,div_grade,course_type,\r\n"
							+ "start_date,date_of_completion,course_abbreviation,course_institute,army_no,\r\n"
							+ "month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)\r\n"
							+ "\r\n" + "SELECT authority,date_of_authority,course_name,div_grade,course_type,\r\n"
							+ "start_date,date_of_completion,course_abbreviation,course_institute,army_no,month,year,\r\n"
							+ "unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.course_name,c.div as div_grade,\r\n"
							+ "(case  when a.course_type_ot = null or a.course_type_ot is null then  d.label else a.course_type_ot end ) as course_type,a.start_date,a.date_of_completion,\r\n"
							+ "a.course_abbreviation,e.course_institute,b.army_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_army_course_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status =''1'' and b.category =''JCO''\r\n"
							+ "inner join tb_psg_mstr_div_grade c on cast(c.id as character varying)= a.div_grade\r\n"
							+ "inner join t_domain_value d on d.codevalue = a.course_type and d.domainid=COURSE_TYPE\r\n"
							+ "inner join tb_psg_mstr_course_institute e on e.id=a.course_institute\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')')\r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,course_name character varying,div_grade character varying,course_type character varying,\r\n"
							+ "start_date timestamp without time zone,date_of_completion timestamp without time zone,course_abbreviation character varying,course_institute character varying,\r\n"
							+ "army_no character varying,month character varying,year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ " ");

			int ap = pstmt40.executeUpdate();

			/// Foreign Country jco
			PreparedStatement pstmt41 = c.prepareStatement(
					"insert into tb_olap_foreign_country_jco (country,period,date_from,date_to, purpose_visit,army_no ,month, year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT country,period,date_from,date_to, purpose_visit,army_no ,month, year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select    q.name as country,a.period,a.from_dt as date_from,a.to_dt as date_to, \r\n"
							+ "p.visit_purpose as purpose_visit, b.army_no ,date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,\r\n"
							+ " b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "From tb_psg_census_foreign_country_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.category =''JCO''\r\n"
							+ "left join tb_psg_foreign_country q on q.id = a.country \r\n"
							+ "left join tb_psg_mstr_purposeof_visit p on p.id = a.purpose_visit and p.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(country character varying,period character varying,date_from timestamp without time zone,date_to timestamp without time zone, purpose_visit character varying,army_no character varying ,\r\n"
							+ "	 month character varying, year character varying,unit_sus_no character varying,unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int aq = pstmt41.executeUpdate();

			/// Personal Details jco
			PreparedStatement pstmt42 = c.prepareStatement(
					"insert into tb_olap_personal_details_jco (army_no,first_name,full_name,middle_name,gender,date_of_seniority,rank,rank_intake,date_of_birth,unit_posted_to,\r\n"
							+ "                                                                                   date_of_tos,arm_desc,country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,\r\n"
							+ "mother_tongue,religion,aadhar_no, pan_no,maritial_status, blood_group,height ,id_card_no  ,date_of_issuing, issuing_authority  , \r\n"
							+ "visible_identification_marks,hair_colour , eye_colour,presently_domicile_country,presently_domicile_state,presently_domicile_district,\r\n"
							+ "presently_domicile_tehsil,permanent_country, permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin, permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban, present_country,present_state, present_district, present_tehsil, present_village_town_city,\r\n"
							+ "present_pin , present_nearest_railway_station, present_rural_urban_semi_urban,nok_name, nok_relation, nok_country, nok_state, nok_district,\r\n"
							+ "nok_tehsil,  nok_village_town_city ,nok_pin, nok_nearest_railway_station, nok_rural_urban_semi_urban, \r\n"
							+ "nok_mobile_no,father_name, date_of_birth_father,father_place_of_birth,father_service,father_personal_no,mother_name, date_of_mother,\r\n"
							+ " mother_place_of_birth,mother_service,mother_personal_no, \r\n"
							+ "gmail,nic_mail,mobile_no , date_of_casulaty,time_of_casuality ,onduty, batnpc_country, batnpc_state,batnpc_district,batnpc_tehsil, \r\n"
							+ " batnpc_village_town_city, batnpc_pin,batnpc_exact_place_area_post, batnpc_name_of_operation,batnpc_sector, batnpc_filed_area, batnpc_whether_on,\r\n"
							+ " batnpc_bde, batnpc_div, batnpc_corp, batnpc_command, batnpc_hospital_name, batnpc_hospital_location, batnpc_cause_of_casualty,\r\n"
							+ " batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ, batnpc_nok_informed, date_of_informing_batnpc,batnpc_time_of_informing,\r\n"
							+ "batnpc_method_of_informing, batnpc_category_of_casualty,army_act_sec,sub_clause, trialed_by, punishment_awarded, discipline_type_of_entry,\r\n"
							+ " date_of_discipline_award ,discipline_unit_name,cause_of_non_effective,date_of_non_effective,\r\n"
							+ " non_eff_country, non_eff_state, non_eff_district,non_eff_tehsil, non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ " non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ " month, year,unit_sus_no,unit_name, age,form_code_control,year_of_service,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT army_no,first_name,full_name,middle_name,gender,date_of_seniority,rank,rank_intake,date_of_birth,unit_posted_to,date_of_tos,arm_desc,\r\n"
							+ "country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,\r\n"
							+ "mother_tongue,religion,aadhar_no, pan_no,maritial_status, blood_group,height ,id_card_no  ,date_of_issuing, issuing_authority  , \r\n"
							+ " visible_identification_marks,hair_colour , eye_colour,presently_domicile_country,presently_domicile_state,presently_domicile_district,\r\n"
							+ "presently_domicile_tehsil,permanent_country, permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin, permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban, present_country,present_state, present_district, present_tehsil, present_village_town_city,\r\n"
							+ "present_pin , present_nearest_railway_station, present_rural_urban_semi_urban,nok_name, nok_relation, nok_country, nok_state, nok_district,\r\n"
							+ "nok_tehsil,  nok_village_town_city ,nok_pin, nok_nearest_railway_station, nok_rural_urban_semi_urban, \r\n"
							+ "nok_mobile_no,father_name, date_of_birth_father,father_place_of_birth,father_service,father_personal_no,mother_name, date_of_mother,\r\n"
							+ " mother_place_of_birth,mother_service,mother_personal_no, \r\n"
							+ "gmail,nic_mail,mobile_no , date_of_casulaty,time_of_casuality ,onduty, batnpc_country, batnpc_state,batnpc_district,batnpc_tehsil, \r\n"
							+ " batnpc_village_town_city, batnpc_pin,batnpc_exact_place_area_post, batnpc_name_of_operation,batnpc_sector, batnpc_filed_area, batnpc_whether_on,\r\n"
							+ " batnpc_bde, batnpc_div, batnpc_corp, batnpc_command, batnpc_hospital_name, batnpc_hospital_location, batnpc_cause_of_casualty,\r\n"
							+ " batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ, batnpc_nok_informed, date_of_informing_batnpc,batnpc_time_of_informing,\r\n"
							+ "batnpc_method_of_informing, batnpc_category_of_casualty,army_act_sec,sub_clause, trialed_by, punishment_awarded, discipline_type_of_entry,\r\n"
							+ " date_of_discipline_award ,discipline_unit_name,cause_of_non_effective,date_of_non_effective,\r\n"
							+ " non_eff_country, non_eff_state, non_eff_district,non_eff_tehsil, non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ " non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ " month, year,unit_sus_no,unit_name, age,form_code_control,year_of_service,ct_part_i_ii,type_of_location\r\n"
							+ "\r\n" + "FROM dblink('myconn','select distinct cl.army_no,\r\n"
							+ "cl.full_name ,cl.first_name,cl.middle_name,g.gender_name as gender,\r\n"
							+ "cl.date_of_seniority,ran.rank as rank,rani.label as rank_intake,cl.date_of_birth,\r\n"
							+ "cl.unit_sus_no,jj.unit_name as unit_posted_to,cl.date_of_tos,armm.arm_desc,\r\n"
							+ "ct.name as country_of_birth,st.state_name as state_of_birth,dt.district_name as district_of_birth,cl.place_of_birth as place_of_birth,\r\n"
							+ "nati.nationality_name as nationality,\r\n"
							+ "mol.mothertounge as mother_tongue,rel.religion_name as religion,cl.aadhar_no as aadhar_no,cl.pan_no as pan_no,\r\n"
							+ "ms.marital_name as maritial_status, bg.blood_desc as blood_group,h.centimeter as height ,idc.id_card_no  ,\r\n"
							+ "idc.issue_dt as date_of_issuing, jjj.unit_name as issuing_authority  , idc.id_marks as visible_identification_marks\r\n"
							+ ",hc.hair_cl_name as hair_colour ,ec.eye_cl_name as eye_colour,\r\n"
							+ "ctop.name as presently_domicile_country,stop.state_name as presently_domicile_state,dtop.district_name as presently_domicile_district,\r\n"
							+ "ttop.city_name as presently_domicile_tehsil,\r\n"
							+ "ctopp.name as permanent_country,stopp.state_name as permanent_state, dtopp.district_name as permanent_district,\r\n"
							+ "ttopp.city_name as permanent_tehsil,\r\n"
							+ "ca.permanent_village as permanent_village_town_city,ca.permanent_pin_code as permanent_pin,\r\n"
							+ "ca.permanent_near_railway_station as permanent_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as permanent_rural_urban_semi_urban,pctop.name as present_country,pstop.state_name as present_state,\r\n"
							+ "pdtop.district_name  as present_district,pttop.city_name as present_tehsil,ca.present_village as present_village_town_city,\r\n"
							+ "ca.present_pin_code as present_pin ,ca.present_near_railway_station as present_nearest_railway_station,\r\n"
							+ "ca.present_rural_urban_semi as present_rural_urban_semi_urban\r\n"
							+ ",nok.nok_name,rela.relation_name as nok_relation,coun.name as nok_country,sta.state_name as nok_state,dis.district_name as nok_district,\r\n"
							+ "nteh.city_name as nok_tehsil, \r\n"
							+ "nok.nok_village as nok_village_town_city ,nok.nok_pin,nok.nok_near_railway_station as nok_nearest_railway_station,nok.nok_rural_urban_semi as nok_rural_urban_semi_urban, \r\n"
							+ "nok.nok_mobile_no,cl.father_name,cl.father_dob as date_of_birth_father,cl.father_place_birth father_place_of_birth\r\n"
							+ ",exser.ex_servicemen as father_service,cl.father_personal_no,cl.mother_name,cl.mother_dob as date_of_mother,\r\n"
							+ "cl.mother_place_birth as mother_place_of_birth,mexser.ex_servicemen as mother_service,cl.mother_personal_no,\r\n"
							+ "con.gmail,con.nic_mail,con.mobile_no ,\r\n"
							+ "bat.date_of_casuality as date_of_casulaty,bat.time_of_casuality ,bat.onduty,bcon.name as batnpc_country,bts.state_name as batnpc_state,\r\n"
							+ "bpd.district_name as batnpc_district,\r\n"
							+ "bpcc.city_name as batnpc_tehsil,bat.village as batnpc_village_town_city,bat.pin as batnpc_pin,bat.exact_place as batnpc_exact_place_area_post,\r\n"
							+ "bat.name_of_operation as batnpc_name_of_operation,bat.sector as batnpc_sector,bat.field_services as batnpc_filed_area,\r\n"
							+ "bat.whether_on as batnpc_whether_on\r\n"
							+ ",bd.bde_name as batnpc_bde,di.div_name as batnpc_div,co.coprs_name as batnpc_corp,cm.cmd_name as batnpc_command,\r\n"
							+ "bat.hospital_name as batnpc_hospital_name,\r\n"
							+ "bat.hospital_location as batnpc_hospital_location\r\n"
							+ ",bat.cause_of_casuality as batnpc_cause_of_casualty,bat.circumstances as batnpc_circumstances,bat.diagnosis as batnpc_diagnosis,\r\n"
							+ "bat.aid_to_civ as batnpc_aid_to_civ,bat.nok_informed as batnpc_nok_informed,\r\n"
							+ "bat.date_of_informing as date_of_informing_batnpc,bat.time_of_informing as batnpc_time_of_informing,\r\n"
							+ "bat.methodofinforming as batnpc_method_of_informing,\r\n"
							+ "bat.cause_of_casuality as batnpc_category_of_casualty,\r\n"
							+ "arse.army_act_sec as army_act_sec,subc.sub_clause,ditri.disc_trialed as trialed_by,disci.description as punishment_awarded,dictd.label as discipline_type_of_entry,\r\n"
							+ "	disci.award_dt as date_of_discipline_award ,\r\n"
							+ "disor.unit_name as discipline_unit_name,\r\n"
							+ "cau.causes_name as cause_of_non_effective,non.date_of_non_effective,\r\n"
							+ "ctopp.name as non_eff_country,stopp.state_name as non_eff_state, dtopp.district_name as non_eff_district,ttopp.city_name as non_eff_tehsil,\r\n"
							+ "ca.permanent_village as non_eff_village_town_city,ca.permanent_pin_code as non_eff_pin,ca.permanent_near_railway_station as non_eff_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as non_eff_rural_urban_semi_urban,ca.permanent_border_area as non_eff_border_area,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,u.unit_name,\r\n"
							+ "date_part(''year'',age(now(),cl.date_of_birth) ) as age, u.form_code_control,\r\n"
							+ "date_part(''year'',age(now(),cl.enroll_dt) ) as year_of_service,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_jco_or_p cl \r\n"
							+ "left join tb_psg_mstr_gender g on g.id = cl.gender and g.status=''active''\r\n"
							+ "left join tb_psg_mstr_rank_jco ran on ran.id = cl.rank and ran.status = ''active'' and ran.category=''JCO''\r\n"
							+ "left join t_domain_value rani on rani.codevalue = cl.rank_intake and rani.domainid=''RECT_INTAKE''			\r\n"
							+ "left join tb_miso_orbat_unt_dtl jj on jj.sus_no=cl.unit_sus_no and jj.status_sus_no=''Active''\r\n"
							+ "left join tb_miso_orbat_arm_code  armm on armm.arm_code = cl.regiment and SUBSTR(armm.arm_code,1,2) IN (''07'',''08'') and armm.arm_code not in (''0700'',''0800'')\r\n"
							+ "left join tb_psg_mstr_country ct on ct.id = cl.country_of_birth and ct.status =''active''\r\n"
							+ "left join tb_psg_mstr_state st on st.state_id = cl.state_of_birth and st.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dt on dt.district_id = cl.district_of_birth and dt.status=''active''\r\n"
							+ "left join tb_psg_mstr_nationality nati on nati.nationality_id=cl.nationality and nati.status=''active''\r\n"
							+ "left join tb_psg_mothertounge mol on mol.id=cl.mother_tongue and mol.status=''active''\r\n"
							+ "left join tb_psg_mstr_religion rel on rel.religion_id=cl.religion and rel.status=''active''\r\n"
							+ "left join tb_psg_census_language_jco l on l.jco_id = cl.id and l.status=''1''\r\n"
							+ "left join tb_psg_mstr_indian_language il on il.id =l.language  and il.status=''active''\r\n"
							+ "left join tb_psg_mstr_marital_status ms on ms.marital_id=cl.marital_status and ms.status=''active''\r\n"
							+ "left join tb_psg_mstr_blood bg on bg.id=cl.blood_group and bg.status=''active''\r\n"
							+ "left join tb_psg_mstr_height h on h.height_id=cl.height and h.status=''active''\r\n"
							+ "left join tb_psg_identity_card_jco idc on idc.jco_id=cl.id  and idc.status=''1''\r\n"
							+ "left join tb_miso_orbat_unt_dtl jjj on jjj.sus_no=idc.issue_authority and jjj.status_sus_no=''Active''\r\n"
							+ "left join tb_psg_mstr_hair_colour hc on hc.id=idc.hair_colour  and hc.status=''active''\r\n"
							+ "left join tb_psg_mstr_eye_colour ec on ec.id=idc.eye_colour and ec.status=''active''\r\n"
							+ " left join tb_psg_census_address_jco ca on ca.jco_id = cl.id and  ca.status=''1''\r\n"
							+ "left join tb_psg_mstr_country ctop on ctop.id = ca.pre_country and ctop.status=''active''\r\n"
							+ "left join tb_psg_mstr_state stop on stop.state_id = ca.pre_state and stop.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dtop on dtop.district_id = ca.pre_district and dtop.status=''active''\r\n"
							+ "left join tb_psg_mstr_city ttop on ttop.city_id = ca.pre_tesil and ttop.status=''active''\r\n"
							+ "left join tb_psg_mstr_country ctopp on ctopp.id = ca.permanent_country and ctopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_state stopp on stopp.state_id = ca.permanent_state and stopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dtopp on dtopp.district_id = ca.permanent_district and dtopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_city ttopp on ttopp.city_id = ca.permanent_tehsil and ttopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_country pctop on pctop.id = ca.present_country and pctop.status=''active''\r\n"
							+ "left join tb_psg_mstr_state pstop on pstop.state_id = ca.present_state and pstop.status=''active''\r\n"
							+ "left join tb_psg_mstr_district pdtop on pdtop.district_id = ca.pre_district and pdtop.status=''active''\r\n"
							+ "left join tb_psg_mstr_city pttop on pttop.city_id = ca.present_tehsil and pttop.status=''active''\r\n"
							+ "left join tb_psg_census_nok_jco nok on nok.jco_id = cl.id and nok.status=''1''\r\n"
							+ "left join tb_psg_mstr_relation rela on rela.id = nok.nok_relation and rela.status=''active''\r\n"
							+ "left join tb_psg_mstr_country coun on coun.id = nok.nok_country and coun.status=''active''\r\n"
							+ "left join tb_psg_mstr_state sta on sta.state_id = nok.nok_state and coun.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dis on dis.district_id = nok.nok_district and dis.status=''active''\r\n"
							+ "left join tb_psg_mstr_city nteh on nteh.city_id = nok.nok_tehsil and nteh.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen exser on cast(exser.id as character varying)  = cl.father_service and exser.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen mexser on cast(mexser.id as character varying)  = cl.mother_service  and mexser.status=''active''\r\n"
							+ "left join tb_psg_census_cadet cadet on cadet.comm_id  = cl.id  and cadet.status=''1''\r\n"
							+ "left join tb_psg_mstr_pre_cadet_status mcre on mcre.id  = cadet.status  and mcre.status=''active''\r\n"
							+ "left join tb_psg_mstr_specialization spe on spe.id  = cadet.competency  and spe.status=''active''\r\n"
							+ "left join tb_psg_census_ncc_exp ncc on ncc.comm_id  = cl.id  and ncc.status=''1''\r\n"
							+ "left join tb_psg_change_of_rank_jco rk on rk.jco_id = cl.id and rk.status=''1''\r\n"
							+ "left join t_domain_value td on td.codevalue=cast(rk.rank_type  as char) and td.domainid=''OFFR_RANK_TYPE''\r\n"
							+ "left join tb_psg_mstr_rank_jco ran1 on ran1.id = rk.rank and ran1.status = ''active''	and ran1.category=''JCO''		\r\n"
							+ "left join tb_psg_change_of_appointment ap on ap.comm_id=cl.id and ap.status=''1''\r\n"
							+ "left join cue_tb_psg_rank_app_master appn on appn.id=ap.appointment \r\n"
							+ "and upper(appn.level_in_hierarchy) = ''APPOINTMENT'' and appn.parent_code =''0'' and appn.status_active = ''Active''\r\n"
							+ "left join tb_psg_census_contact_cda_account_details_jco con on con.jco_id=cl.id and con.status=''1''\r\n"
							+ "left join tb_psg_census_battle_physical_casuality_jco bat on bat.jco_id=cl.id  and bat.status=''1''\r\n"
							+ "left join tb_psg_mstr_country bcon on cast(bcon.id as character varying) = bat.country and bcon.status=''active''\r\n"
							+ "left join tb_psg_mstr_state bts on cast(bts.state_id as character varying) = bat.state and bts.status=''active''\r\n"
							+ "left join tb_psg_mstr_district bpd on cast(bpd.district_id as character varying) = bat.district and bpd.status=''active''\r\n"
							+ "left join tb_psg_mstr_city bpcc on cast(bpcc.city_id as character varying) = bat.tehsil and bpcc.status=''active''\r\n"
							+ "left join orbat_view_bde bd on bd.sus_no=bat.bde\r\n"
							+ "left join orbat_view_div di on di.sus_no=bat.div_subarea\r\n"
							+ "left join orbat_view_corps co on co.sus_no=bat.corps_area \r\n"
							+ "left join  orbat_view_cmd cm on cm.sus_no=bat.command  \r\n"
							+ "left join tb_psg_census_discipline_jco disci on disci.jco_id=cl.id and disci.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_mstr_army_act_sec arse on arse.id=disci.army_act_sec and arse.status=''active''\r\n"
							+ "left join tb_psg_mstr_sub_clause subc on subc.id=disci.sub_clause and subc.status=''active''\r\n"
							+ "left join tb_psg_mstr_disc_trialed ditri on ditri.id=disci.trialed_by and ditri.status=''active''\r\n"
							+ "left join t_domain_value dictd on dictd.codevalue=cast(disci.type_of_entry as character varying) and dictd.domainid=''DISCIPLINE''\r\n"
							+ "left join tb_miso_orbat_unt_dtl disor on disor.sus_no=disci.unit_name and disor.status_sus_no=''Active''\r\n"
							+ "left join tb_psg_change_of_comission chc on chc.comm_id=cl.id and chc.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_extension_of_comission ext on ext.comm_id=cl.id and ext.status=''1''\r\n"
							+ "left join tb_psg_census_secondment sec on sec.comm_id=cl.id and sec.status=''1''\r\n"
							+ "left join tb_psg_mstr_seconded_to sem on sem.id=sec.seconded_to  and sem.status=''active''\r\n"
							+ "left join tb_psg_non_effective_jco non on non.jco_id=cl.id and non.status=''1''\r\n"
							+ "left join tb_psg_mstr_cause_of_non_effective cau on cau.id=cast(non.cause_of_non_effective as integer)  and cau.status=''active''\r\n"
							+ "left join tb_psg_deserter_jco dest on dest.jco_id=cl.id and dest.status=''1''\r\n"
							+ "left join t_domain_value detd on detd.codevalue=dest.arm_type and detd.domainid=''ARM_TYPE''\r\n"
							+ "left join t_domain_value cdes on cdes.codevalue=dest.desertion_cause and cdes.domainid=''CAUSE_OF_DESRTION'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on cl.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (cl.status not in (''0'',''3'',''-1'',''4'') and cl.category=''JCO'')')\r\n"
							+ "AS t(army_no character varying,first_name character varying,full_name character varying,middle_name character varying,gender character varying,\r\n"
							+ "         date_of_seniority timestamp without time zone,rank character varying,rank_intake character varying,date_of_birth timestamp without time zone,\r\n"
							+ "unit_sus_no character varying,unit_posted_to character varying,date_of_tos timestamp without time zone,arm_desc character varying,\r\n"
							+ "         country_of_birth character varying,state_of_birth character varying,district_of_birth character varying,place_of_birth character varying,\r\n"
							+ "         nationality character varying,mother_tongue character varying,religion character varying,aadhar_no character varying, \r\n"
							+ "         pan_no character varying,maritial_status character varying, blood_group character varying,height character varying,id_card_no  character varying,\r\n"
							+ "         date_of_issuing timestamp without time zone, issuing_authority  character varying, \r\n"
							+ " visible_identification_marks character varying,hair_colour character varying, eye_colour character varying,\r\n"
							+ "         presently_domicile_country character varying,presently_domicile_state character varying,presently_domicile_district character varying,\r\n"
							+ "presently_domicile_tehsil character varying,permanent_country character varying, permanent_state character varying, permanent_district character varying,\r\n"
							+ "         permanent_tehsil character varying,permanent_village_town_city character varying,permanent_pin character varying,\r\n"
							+ "         permanent_nearest_railway_station character varying,permanent_rural_urban_semi_urban character varying, present_country character varying,\r\n"
							+ "         present_state character varying, present_district character varying, present_tehsil character varying, present_village_town_city character varying,\r\n"
							+ "present_pin character varying, present_nearest_railway_station character varying, present_rural_urban_semi_urban character varying,\r\n"
							+ "         nok_name character varying, \r\n"
							+ "         nok_relation character varying, nok_country character varying, nok_state character varying, nok_district character varying,\r\n"
							+ "nok_tehsil character varying,  nok_village_town_city character varying ,nok_pin character varying, nok_nearest_railway_station character varying, \r\n"
							+ "         nok_rural_urban_semi_urban character varying, nok_mobile_no character varying,father_name character varying,\r\n"
							+ "         date_of_birth_father character varying,\r\n"
							+ "         father_place_of_birth character varying,father_service character varying,father_personal_no character varying,mother_name character varying, \r\n"
							+ "         date_of_mother character varying, mother_place_of_birth character varying,mother_service character varying,mother_personal_no character varying, \r\n"
							+ "        gmail character varying,nic_mail character varying,mobile_no character varying , \r\n"
							+ "         date_of_casulaty character varying,time_of_casuality character varying,onduty character varying, batnpc_country character varying,\r\n"
							+ "         batnpc_state character varying,batnpc_district character varying,batnpc_tehsil character varying, \r\n"
							+ " batnpc_village_town_city character varying, batnpc_pin character varying,batnpc_exact_place_area_post character varying,\r\n"
							+ "         batnpc_name_of_operation character varying,batnpc_sector character varying, batnpc_filed_area character varying, batnpc_whether_on character varying,\r\n"
							+ " batnpc_bde character varying, batnpc_div character varying, batnpc_corp character varying, batnpc_command character varying,\r\n"
							+ "         batnpc_hospital_name character varying, batnpc_hospital_location character varying, batnpc_cause_of_casualty character varying,\r\n"
							+ " batnpc_circumstances character varying,batnpc_diagnosis character varying,batnpc_aid_to_civ character varying, batnpc_nok_informed character varying,\r\n"
							+ "         date_of_informing_batnpc character varying,batnpc_time_of_informing character varying,\r\n"
							+ "batnpc_method_of_informing character varying, batnpc_category_of_casualty character varying,army_act_sec character varying,\r\n"
							+ "         sub_clause character varying, trialed_by character varying, punishment_awarded character varying, discipline_type_of_entry character varying,\r\n"
							+ " date_of_discipline_award character varying ,discipline_unit_name character varying, \r\n"
							+ "cause_of_non_effective character varying,date_of_non_effective character varying, non_eff_country character varying, \r\n"
							+ "         non_eff_state character varying, non_eff_district character varying,\r\n"
							+ "         non_eff_tehsil character varying, non_eff_village_town_city character varying, non_eff_pin character varying, non_eff_nearest_railway_station character varying,\r\n"
							+ " non_eff_rural_urban_semi_urban character varying, non_eff_border_area character varying, \r\n"
							+ " month character varying, year character varying,unit_name character varying, age character varying,\r\n"
							+ "         form_code_control character varying,year_of_service character varying,ct_part_i_ii character varying,type_of_location character varying)\r\n"
							+ "");

			int ar = pstmt42.executeUpdate();

			///// OR //////

			///// // ALLERGY DETAILS OR
			PreparedStatement pstmt43 = c.prepareStatement(
					"insert into tb_olap_allerg_details_or (medicine,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT medicine,army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.medicine,b.army_no,date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_allergic_to_medicine_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''OR''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')') \r\n"
							+ "AS t(medicine character varying,army_no character varying,month character varying,year character varying,unit_sus_no\r\n"
							+ " character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)  ");

			int as = pstmt43.executeUpdate();

			// ARMY COURSE OR

			PreparedStatement pstmt44 = c.prepareStatement(
					"insert into tb_olap_army_course_details_or (authority ,date_of_authority, course_name ,div_grade,course_type ,\r\n"
							+ "start_date,date_of_completion ,course_abbreviation ,course_institute ,\r\n"
							+ "army_no ,month ,year ,unit_sus_no ,unit_name ,form_code_control ,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT authority ,date_of_authority, course_name ,div_grade,course_type ,\r\n"
							+ "start_date,date_of_completion ,course_abbreviation ,course_institute ,\r\n"
							+ "army_no ,month ,year ,unit_sus_no ,unit_name ,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.course_name,c.div as div_grade,\r\n"
							+ "(case  when a.course_type_ot = null or  a.course_type_ot is null then  d.label \r\n"
							+ "else a.course_type_ot end ) as course_type,\r\n"
							+ "a.start_date,a.date_of_completion,a.course_abbreviation,e.course_institute,b.army_no,\r\n"
							+ "b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year\r\n"
							+ "from tb_psg_census_army_course_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.category = ''OR''\r\n"
							+ "inner join tb_psg_mstr_div_grade c on cast(c.id as character varying)= a.div_grade\r\n"
							+ "inner join t_domain_value d on d.codevalue = a.course_type and d.domainid=COURSE_TYPE\r\n"
							+ "inner join tb_psg_mstr_course_institute e on e.id=a.course_institute\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')  ') \r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,course_name character varying,div_grade character varying,course_type character varying,\r\n"
							+ "start_date timestamp without time zone,date_of_completion timestamp without time zone,course_abbreviation character varying,course_institute character varying,\r\n"
							+ "army_no character varying,month character varying,year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying\r\n"
							+ ",ct_part_i_ii character varying, type_of_location  character varying)");

			int at = pstmt44.executeUpdate();

			// AWARD AND MEDAL OR

			PreparedStatement pstmt45 = c.prepareStatement(
					"insert into tb_olap_awardsnmedal_details_or (category,date_of_award,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,\r\n"
							+ "army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT category,date_of_award,unit,bde,div_subarea,corps_area,command,authority,date_of_authority,award_medal,\r\n"
							+ "army_no,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select td.award_cat as category ,am.date_of_award,am.unit,bd.bde_name as bde,di.div_name as div_subarea,co.coprs_name as corps_area,cm.cmd_name as command,\r\n"
							+ "am.authority,am.date_of_authority,mm.medal_name as award_medal,jc.army_no,\r\n"
							+ "cast(date_part(''month'', (SELECT current_timestamp)) as character varying) as month, \r\n"
							+ "cast(date_part(''year'', (SELECT current_timestamp)) as character varying) as year,\r\n"
							+ "jc.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_awardsnmedal_jco am  \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id = am.jco_id  and jc.category = ''OR'' \r\n"
							+ "inner join  orbat_view_cmd cm on cm.sus_no=am.command \r\n"
							+ "inner join orbat_view_corps co on co.sus_no=am.corps_area\r\n"
							+ "inner join orbat_view_div di on di.sus_no=am.div_subarea \r\n"
							+ "inner join orbat_view_bde bd on bd.sus_no=am.bde \r\n"
							+ "inner join tb_psg_mstr_award_category td on td.id::text =am.category_8\r\n"
							+ "inner join tb_psg_mstr_medal mm on mm.id=cast(am.select_desc as integer) \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = jc.unit_sus_no and u.status_sus_no  =''Active''\r\n"
							+ "where  am.status not in (''0'',''3'',''-1'') and jc.status not in (''0'',''3'',''-1'',''4'')\r\n"
							+ "') \r\n"
							+ "AS t(category  character varying,date_of_award  timestamp without time zone,unit  character varying,bde character varying,div_subarea character varying,\r\n"
							+ "corps_area character varying,command character varying,authority character varying,\r\n"
							+ "date_of_authority timestamp without time zone,award_medal character varying,army_no character varying,\r\n"
							+ "month character varying,year character varying,unit_sus_no character varying,unit_name character varying ,form_code_control character varying\r\n"
							+ ",ct_part_i_ii character varying,type_of_location  character varying)\r\n" + "");

			int au = pstmt45.executeUpdate();

			// BPET OR

			PreparedStatement pstmt46 = c.prepareStatement(
					"insert into tb_olap_bpet_details_or(bpet_qtr,bpet_result ,bpet_year,conducted_at_unit,army_no,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT bpet_qtr,bpet_result,bpet_year,conducted_at_unit,army_no,year,month ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select   bq.bpet_qt as bpet_qtr,a.bpet_result ,a.year as bpet_year, \r\n"
							+ "u.unit_name as conducted_at_unit,b.army_no as army_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_bpet_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''OR''\r\n"
							+ "left join tb_psg_mstr_bpet_qt bq on cast(bq.id as character varying) = a.bpet_qe \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.unit_sus_no  and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in   (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(bpet_qtr character varying,bpet_result character varying,bpet_year character varying,conducted_at_unit character varying,\r\n"
							+ "	 army_no character varying, year character varying,month character varying, unit_sus_no character varying,\r\n"
							+ "	 unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying ) ");

			int av = pstmt46.executeUpdate();

			// CHILDREN DETAILS OR

			PreparedStatement pstmt47 = c.prepareStatement(
					"insert into tb_olap_census_children_or (date_of_birth,relationship,type,adoted,pan_no,date_of_adpoted,name,\r\n"
							+ "child_service,if_child_ser,child_personal_no,aadhar_no,army_no,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT date_of_birth,relationship,type,adoted,pan_no,date_of_adpoted,name, \r\n"
							+ "child_service,if_child_ser,child_personal_no,aadhar_no,army_no ,year,month,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','\r\n"
							+ "select   a.date_of_birth, c.label as relationship,a.type as specially_abled_child, a.adoted as adopted_child,\r\n"
							+ "a.pan_no,a.date_of_adpoted,a.name, \r\n" + "e.ex_servicemen as child_service,\r\n"
							+ " (case  when a.other_child_ser = null  or a.other_child_ser is null then  a.if_child_ser\r\n"
							+ "                                 else a.other_child_ser end ) as service_exservice,\r\n"
							+ "a.child_personal_no,a.aadhar_no, b.army_no as personal_no,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_children_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''OR''\r\n"
							+ "left join t_domain_value c on c.codevalue = cast(a.relationship as character varying) and c.domainid=''CHILD_RELATIONSHIP''\r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = child_service and e.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in  (''0'',''3'',''4'',''-1'') ')\r\n"
							+ "AS t(date_of_birth timestamp without time zone,relationship character varying,type character varying,adoted character varying, \r\n"
							+ "pan_no character varying,date_of_adpoted timestamp without time zone,name character varying, \r\n"
							+ "child_service character varying,if_child_ser character varying,child_personal_no character varying,aadhar_no character varying,\r\n"
							+ "army_no character varying,year character varying,month character varying,unit_sus_no character varying,unit_name character varying,form_code_control  character varying\r\n"
							+ ",ct_part_i_ii  character varying,type_of_location  character varying)\r\n" + " ");

			int aw = pstmt47.executeUpdate();

			// FIRING STD OR

			PreparedStatement pstmt48 = c.prepareStatement(
					"insert into tb_olap_census_firing_standard_or (firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)     \r\n"
							+ " SELECT firing_event_qtr,firing_grade,firing_year,conducted_at_unit ,army_no ,month,year ,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ " FROM dblink('myconn','select    q.bpet_qt as firing_event_qtr,f.firing_result as firing_grade,a.year,u.unit_name as conducted_at_unit , \r\n"
							+ "b.army_no as personal_no, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_firing_standard_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''OR''\r\n"
							+ "left join tb_psg_mstr_bpet_qt q on cast(q.id as character varying) = a.firing_event_qe  and q.status=''active'' \r\n"
							+ "left join tb_psg_mstr_firing_result f on cast(q.id as character varying) = a.firing_grade  and q.status=''active''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = a.firing_unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in  (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(firing_event_qtr character varying,firing_grade character varying,firing_year character varying,conducted_at_unit character varying,army_no character varying, \r\n"
							+ "month character varying, year character varying , unit_sus_no character varying,unit_name character varying,form_code_control character varying\r\n"
							+ ",ct_part_i_ii  character varying ,type_of_location  character varying) ");

			int ax = pstmt48.executeUpdate();

			// VISIT TO FOREIGN COUNTRY OR
			PreparedStatement pstmt49 = c.prepareStatement("\r\n"
					+ "insert into tb_olap_foreign_country_or (country,period,date_from,date_to, purpose_visit,army_no ,month, year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
					+ "SELECT country,period,date_from,date_to, purpose_visit,army_no ,month, year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
					+ "FROM dblink('myconn','select    q.name as country,a.period,a.from_dt as date_from,a.to_dt as date_to, \r\n"
					+ "p.visit_purpose as purpose_visit, b.army_no ,date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
					+ "date_part(''year'', (SELECT current_timestamp)) as year,\r\n"
					+ "			b.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
					+ "From tb_psg_census_foreign_country_jco a \r\n"
					+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.category =''OR''\r\n"
					+ "left join tb_psg_foreign_country q on q.id = a.country \r\n"
					+ "left join tb_psg_mstr_purposeof_visit p on p.id = a.purpose_visit and p.status=''active''\r\n"
					+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
					+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
					+ "AS t(country character varying,period character varying,date_from timestamp without time zone,date_to timestamp without time zone, purpose_visit character varying,army_no character varying ,\r\n"
					+ "month character varying, year character varying,unit_sus_no character varying,unit_name character varying,form_code_control  character varying,ct_part_i_ii character varying,type_of_location character varying) ");

			int ay = pstmt49.executeUpdate();

			// MEDICAL DETAILS OR
			PreparedStatement pstmt50 = c.prepareStatement(
					"insert into tb_olap_medical_category_or (authority,date_of_authority,shape,shape_status,shape_value,from_date,to_date,diagnosis,\r\n"
							+ "clasification,shape_sub_value,diagnosis_1bx,from_date_1bx,to_date_1bx,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location) \r\n"
							+ "SELECT authority,date_of_authority,shape,shape_status,shape_value,from_date,to_date,diagnosis,clasification,shape_sub_value,diagnosis_1bx,\r\n"
							+ "from_date_1bx,to_date_1bx,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location  \r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.shape,a.shape_status,\r\n"
							+ "(case  when a.other = null or a.other is null then  a.shape_value\r\n"
							+ "else a.other end ) as shape_value,			\r\n"
							+ "a.from_date as from_date,a.to_date as to_date,a.diagnosis,\r\n"
							+ "a.clasification,a.shape_sub_value,a.diagnosis_1bx,a.from_date_1bx,a.to_date_1bx,\r\n"
							+ "jc.army_no,\r\n" + "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,jc.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location  \r\n"
							+ "from tb_psg_medical_category_jco a \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id = a.jco_id  and jc.status = ''1'' and jc.category = ''OR'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on jc.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and jc.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(authority character varying ,date_of_authority timestamp without time zone,shape character varying,shape_status character varying, \r\n"
							+ "shape_value character varying,from_date timestamp without time zone,to_date timestamp without time zone,diagnosis character varying,clasification character varying,\r\n"
							+ "shape_sub_value character varying,diagnosis_1bx character varying,from_date_1bx timestamp without time zone,to_date_1bx timestamp without time zone,\r\n"
							+ "army_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying\r\n"
							+ "	,ct_part_i_ii  character varying,type_of_location character varying)");

			int az = pstmt50.executeUpdate();

			// PROMOTINAL EXAM OR

			PreparedStatement pstmt51 = c.prepareStatement(
					"insert into tb_olap_promo_exam_details_or (authority,date_of_authority,exam ,date_of_passing,army_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT authority,date_of_authority,exam ,date_of_passing,personal_no,month,year,unit_sus_no,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select   a.authority,a.date_of_authority, \r\n"
							+ " (case  when a.exam_other = null or  a.exam_other is null then  e.promo_exam\r\n"
							+ "  else a.exam_other end ) as exam,\r\n"
							+ "a.date_of_passing,b.army_no as personal_no ,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,b.unit_sus_no,u.unit_name ,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_promo_exam_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status=''1'' and b.category =''OR''\r\n"
							+ "left join tb_psg_mstr_promotional_exam e on cast(e.id as character varying)=a.exam and e.status=''active''\r\n"
							+ "left join tb_miso_orbat_unt_dtl u on b.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,exam character varying,date_of_passing  character varying,\r\n"
							+ "personal_no character varying,month character varying, year character varying,unit_sus_no character varying,unit_name character varying,\r\n"
							+ "form_code_control character varying,ct_part_i_ii character varying,type_of_location  character varying) ");

			int aaa = pstmt51.executeUpdate();

			// SPOUSE DETAILS OR

			PreparedStatement pstmt52 = c.prepareStatement(
					"insert into tb_olap_spouse_details_or (date_of_birth,maiden_name,marriage_date,nationality,divorce_date, authority,date_of_authority,\r\n"
							+ "type_of_event,marital_status,place_of_birth,pan_card,spouse_service,spouse_personal_no,if_spouse_ser,initiated_from,adhar_number,separated_from_dt\r\n"
							+ ",separated_to_dt,month,year ,unit_sus_no ,unit_name,form_code_control,army_no,ct_part_i_ii,type_of_location)\r\n"
							+ "\r\n"
							+ "SELECT date_of_birth,maiden_name,marriage_date,nationality,divorce_date, authority,date_of_authority,\r\n"
							+ "type_of_event,marital_status,place_of_birth,pan_card,spouse_service,spouse_personal_no,if_spouse_ser,initiated_from,adhar_number,separated_from_dt\r\n"
							+ ",separated_to_dt,month,year ,unit_sus_no ,unit_name,form_code_control,army_no,ct_part_i_ii,type_of_location\r\n"
							+ "\r\n"
							+ "FROM dblink('myconn','select  a.date_of_birth,a.maiden_name,a.marriage_date,\r\n"
							+ "(case  when a.other_nationality = null  or a.other_nationality is null then n.nationality_name\r\n"
							+ "else a.other_nationality end ) as nationality,\r\n"
							+ "a.divorce_date,a.authority,a.date_of_authority,\r\n"
							+ "d.label as marital_event,m.marital_name as marital_status,a.place_of_birth,a.pan_card,e.ex_servicemen as spouse_service,a.spouse_personal_no,\r\n"
							+ "(case  when a.other_spouse_ser = null  or a.other_spouse_ser is null then a.if_spouse_ser\r\n"
							+ "else a.other_spouse_ser end ) as service_exservice,\r\n" + "a.initiated_from \r\n"
							+ ",a.adhar_number,a.separated_from_dt,a.separated_to_dt, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,u.unit_name,b.unit_sus_no ,u.form_code_control,b.army_no,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_family_married_jco a   \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id and  b.status=''1'' and  b.category =''OR''  \r\n"
							+ "left join tb_psg_mstr_nationality n on n.nationality_id = a.nationality \r\n"
							+ "left join t_domain_value d on d.codevalue = cast(a.type_of_event as character varying) and  domainid=''MARITAL_EVENT'' \r\n"
							+ "left join tb_psg_mstr_marital_status m on m.marital_id = a.marital_status  \r\n"
							+ "left join tb_psg_mstr_exservicemen e on e.id = a.spouse_service and e.status=''active''  \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') and n.nationality_name is not null')\r\n"
							+ "AS t(date_of_birth timestamp without time zone,maiden_name character varying,marriage_date timestamp without time zone,nationality character varying,divorce_date timestamp without time zone, authority character varying,date_of_authority timestamp without time zone,\r\n"
							+ "type_of_event character varying,marital_status character varying,place_of_birth character varying,pan_card character varying,spouse_service character varying,spouse_personal_no character varying,if_spouse_ser character varying,initiated_from character varying,adhar_number character varying,separated_from_dt timestamp without time zone\r\n"
							+ ",separated_to_dt timestamp without time zone,month character varying,year character varying,unit_sus_no character varying,unit_name character varying,form_code_control character varying,army_no character varying,ct_part_i_ii character varying,type_of_location  character varying) ");

			int aab = pstmt51.executeUpdate();

			// PERSONAL DETAILS OR

			PreparedStatement pstmt53 = c.prepareStatement(
					"insert into tb_olap_personal_details_or (army_no,first_name,full_name,middle_name,gender,date_of_seniority,rank,rank_intake,date_of_birth,unit_posted_to,\r\n"
							+ "                                                                                   date_of_tos,arm_desc,country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,\r\n"
							+ "mother_tongue,religion,aadhar_no, pan_no,maritial_status, blood_group,height ,id_card_no  ,date_of_issuing, issuing_authority  , \r\n"
							+ "visible_identification_marks,hair_colour , eye_colour,presently_domicile_country,presently_domicile_state,presently_domicile_district,\r\n"
							+ "presently_domicile_tehsil,permanent_country, permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin, permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban, present_country,present_state, present_district, present_tehsil, present_village_town_city,\r\n"
							+ "present_pin , present_nearest_railway_station, present_rural_urban_semi_urban,nok_name, nok_relation, nok_country, nok_state, nok_district,\r\n"
							+ "nok_tehsil,  nok_village_town_city ,nok_pin, nok_nearest_railway_station, nok_rural_urban_semi_urban, \r\n"
							+ "nok_mobile_no,father_name, date_of_birth_father,father_place_of_birth,father_service,father_personal_no,mother_name, date_of_mother,\r\n"
							+ " mother_place_of_birth,mother_service,mother_personal_no, \r\n"
							+ "gmail,nic_mail,mobile_no , date_of_casulaty,time_of_casuality ,onduty, batnpc_country, batnpc_state,batnpc_district,batnpc_tehsil, \r\n"
							+ " batnpc_village_town_city, batnpc_pin,batnpc_exact_place_area_post, batnpc_name_of_operation,batnpc_sector, batnpc_filed_area, batnpc_whether_on,\r\n"
							+ " batnpc_bde, batnpc_div, batnpc_corp, batnpc_command, batnpc_hospital_name, batnpc_hospital_location, batnpc_cause_of_casualty,\r\n"
							+ " batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ, batnpc_nok_informed, date_of_informing_batnpc,batnpc_time_of_informing,\r\n"
							+ "batnpc_method_of_informing, batnpc_category_of_casualty,army_act_sec,sub_clause, trialed_by, punishment_awarded, discipline_type_of_entry,\r\n"
							+ " date_of_discipline_award ,discipline_unit_name,cause_of_non_effective,date_of_non_effective,\r\n"
							+ " non_eff_country, non_eff_state, non_eff_district,non_eff_tehsil, non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ " non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ " month, year,unit_sus_no,unit_name, age,form_code_control,year_of_service,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT army_no,first_name,full_name,middle_name,gender,date_of_seniority,rank,rank_intake,date_of_birth,unit_posted_to,date_of_tos,arm_desc,\r\n"
							+ "country_of_birth,state_of_birth,district_of_birth,place_of_birth,nationality,\r\n"
							+ "mother_tongue,religion,aadhar_no, pan_no,maritial_status, blood_group,height ,id_card_no  ,date_of_issuing, issuing_authority  , \r\n"
							+ " visible_identification_marks,hair_colour , eye_colour,presently_domicile_country,presently_domicile_state,presently_domicile_district,\r\n"
							+ "presently_domicile_tehsil,permanent_country, permanent_state, permanent_district, permanent_tehsil,\r\n"
							+ "permanent_village_town_city,permanent_pin, permanent_nearest_railway_station,\r\n"
							+ "permanent_rural_urban_semi_urban, present_country,present_state, present_district, present_tehsil, present_village_town_city,\r\n"
							+ "present_pin , present_nearest_railway_station, present_rural_urban_semi_urban,nok_name, nok_relation, nok_country, nok_state, nok_district,\r\n"
							+ "nok_tehsil,  nok_village_town_city ,nok_pin, nok_nearest_railway_station, nok_rural_urban_semi_urban, \r\n"
							+ "nok_mobile_no,father_name, date_of_birth_father,father_place_of_birth,father_service,father_personal_no,mother_name, date_of_mother,\r\n"
							+ " mother_place_of_birth,mother_service,mother_personal_no, \r\n"
							+ "gmail,nic_mail,mobile_no , date_of_casulaty,time_of_casuality ,onduty, batnpc_country, batnpc_state,batnpc_district,batnpc_tehsil, \r\n"
							+ " batnpc_village_town_city, batnpc_pin,batnpc_exact_place_area_post, batnpc_name_of_operation,batnpc_sector, batnpc_filed_area, batnpc_whether_on,\r\n"
							+ " batnpc_bde, batnpc_div, batnpc_corp, batnpc_command, batnpc_hospital_name, batnpc_hospital_location, batnpc_cause_of_casualty,\r\n"
							+ " batnpc_circumstances,batnpc_diagnosis,batnpc_aid_to_civ, batnpc_nok_informed, date_of_informing_batnpc,batnpc_time_of_informing,\r\n"
							+ "batnpc_method_of_informing, batnpc_category_of_casualty,army_act_sec,sub_clause, trialed_by, punishment_awarded, discipline_type_of_entry,\r\n"
							+ " date_of_discipline_award ,discipline_unit_name,cause_of_non_effective,date_of_non_effective,\r\n"
							+ " non_eff_country, non_eff_state, non_eff_district,non_eff_tehsil, non_eff_village_town_city, non_eff_pin, non_eff_nearest_railway_station,\r\n"
							+ " non_eff_rural_urban_semi_urban, non_eff_border_area,\r\n"
							+ " month, year,unit_sus_no,unit_name, age,form_code_control,year_of_service,ct_part_i_ii,type_of_location\r\n"
							+ "\r\n" + "FROM dblink('myconn','select distinct cl.army_no,\r\n"
							+ "cl.full_name ,cl.first_name,cl.middle_name,g.gender_name as gender,\r\n"
							+ "cl.date_of_seniority,ran.rank as rank,rani.label as rank_intake,cl.date_of_birth,\r\n"
							+ "cl.unit_sus_no,jj.unit_name as unit_posted_to,cl.date_of_tos,armm.arm_desc,\r\n"
							+ "ct.name as country_of_birth,st.state_name as state_of_birth,dt.district_name as district_of_birth,cl.place_of_birth as place_of_birth,\r\n"
							+ "nati.nationality_name as nationality,\r\n"
							+ "mol.mothertounge as mother_tongue,rel.religion_name as religion,cl.aadhar_no as aadhar_no,cl.pan_no as pan_no,\r\n"
							+ "ms.marital_name as maritial_status, bg.blood_desc as blood_group,h.centimeter as height ,idc.id_card_no  ,\r\n"
							+ "idc.issue_dt as date_of_issuing, jjj.unit_name as issuing_authority  , idc.id_marks as visible_identification_marks\r\n"
							+ ",hc.hair_cl_name as hair_colour ,ec.eye_cl_name as eye_colour,\r\n"
							+ "ctop.name as presently_domicile_country,stop.state_name as presently_domicile_state,dtop.district_name as presently_domicile_district,\r\n"
							+ "ttop.city_name as presently_domicile_tehsil,\r\n"
							+ "ctopp.name as permanent_country,stopp.state_name as permanent_state, dtopp.district_name as permanent_district,\r\n"
							+ "ttopp.city_name as permanent_tehsil,\r\n"
							+ "ca.permanent_village as permanent_village_town_city,ca.permanent_pin_code as permanent_pin,\r\n"
							+ "ca.permanent_near_railway_station as permanent_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as permanent_rural_urban_semi_urban,pctop.name as present_country,pstop.state_name as present_state,\r\n"
							+ "pdtop.district_name  as present_district,pttop.city_name as present_tehsil,ca.present_village as present_village_town_city,\r\n"
							+ "ca.present_pin_code as present_pin ,ca.present_near_railway_station as present_nearest_railway_station,\r\n"
							+ "ca.present_rural_urban_semi as present_rural_urban_semi_urban\r\n"
							+ ",nok.nok_name,rela.relation_name as nok_relation,coun.name as nok_country,sta.state_name as nok_state,dis.district_name as nok_district,\r\n"
							+ "nteh.city_name as nok_tehsil, \r\n"
							+ "nok.nok_village as nok_village_town_city ,nok.nok_pin,nok.nok_near_railway_station as nok_nearest_railway_station,nok.nok_rural_urban_semi as nok_rural_urban_semi_urban, \r\n"
							+ "nok.nok_mobile_no,cl.father_name,cl.father_dob as date_of_birth_father,cl.father_place_birth father_place_of_birth\r\n"
							+ ",exser.ex_servicemen as father_service,cl.father_personal_no,cl.mother_name,cl.mother_dob as date_of_mother,\r\n"
							+ "cl.mother_place_birth as mother_place_of_birth,mexser.ex_servicemen as mother_service,cl.mother_personal_no,\r\n"
							+ "con.gmail,con.nic_mail,con.mobile_no ,\r\n"
							+ "bat.date_of_casuality as date_of_casulaty,bat.time_of_casuality ,bat.onduty,bcon.name as batnpc_country,bts.state_name as batnpc_state,\r\n"
							+ "bpd.district_name as batnpc_district,\r\n"
							+ "bpcc.city_name as batnpc_tehsil,bat.village as batnpc_village_town_city,bat.pin as batnpc_pin,bat.exact_place as batnpc_exact_place_area_post,\r\n"
							+ "bat.name_of_operation as batnpc_name_of_operation,bat.sector as batnpc_sector,bat.field_services as batnpc_filed_area,\r\n"
							+ "bat.whether_on as batnpc_whether_on\r\n"
							+ ",bd.bde_name as batnpc_bde,di.div_name as batnpc_div,co.coprs_name as batnpc_corp,cm.cmd_name as batnpc_command,\r\n"
							+ "bat.hospital_name as batnpc_hospital_name,\r\n"
							+ "bat.hospital_location as batnpc_hospital_location\r\n"
							+ ",bat.cause_of_casuality as batnpc_cause_of_casualty,bat.circumstances as batnpc_circumstances,bat.diagnosis as batnpc_diagnosis,\r\n"
							+ "bat.aid_to_civ as batnpc_aid_to_civ,bat.nok_informed as batnpc_nok_informed,\r\n"
							+ "bat.date_of_informing as date_of_informing_batnpc,bat.time_of_informing as batnpc_time_of_informing,\r\n"
							+ "bat.methodofinforming as batnpc_method_of_informing,\r\n"
							+ "bat.cause_of_casuality as batnpc_category_of_casualty,\r\n"
							+ "arse.army_act_sec as army_act_sec,subc.sub_clause,ditri.disc_trialed as trialed_by,disci.description as punishment_awarded,dictd.label as discipline_type_of_entry,\r\n"
							+ "	disci.award_dt as date_of_discipline_award ,\r\n"
							+ "disor.unit_name as discipline_unit_name,\r\n"
							+ "cau.causes_name as cause_of_non_effective,non.date_of_non_effective,\r\n"
							+ "ctopp.name as non_eff_country,stopp.state_name as non_eff_state, dtopp.district_name as non_eff_district,ttopp.city_name as non_eff_tehsil,\r\n"
							+ "ca.permanent_village as non_eff_village_town_city,ca.permanent_pin_code as non_eff_pin,ca.permanent_near_railway_station as non_eff_nearest_railway_station,\r\n"
							+ "ca.permanent_rural_urban_semi as non_eff_rural_urban_semi_urban,ca.permanent_border_area as non_eff_border_area,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year,u.unit_name,\r\n"
							+ "date_part(''year'',age(now(),cl.date_of_birth) ) as age, u.form_code_control,\r\n"
							+ "date_part(''year'',age(now(),cl.enroll_dt) ) as year_of_service,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_jco_or_p cl \r\n"
							+ "left join tb_psg_mstr_gender g on g.id = cl.gender and g.status=''active''\r\n"
							+ "left join tb_psg_mstr_rank_jco ran on ran.id = cl.rank and ran.status = ''active'' and ran.category=''OR''\r\n"
							+ "left join t_domain_value rani on rani.codevalue = cl.rank_intake and rani.domainid=''RECT_INTAKE''			\r\n"
							+ "left join tb_miso_orbat_unt_dtl jj on jj.sus_no=cl.unit_sus_no and jj.status_sus_no=''Active''\r\n"
							+ "left join tb_miso_orbat_arm_code  armm on armm.arm_code = cl.regiment and SUBSTR(armm.arm_code,1,2) IN (''07'',''08'') and armm.arm_code not in (''0700'',''0800'')\r\n"
							+ "left join tb_psg_mstr_country ct on ct.id = cl.country_of_birth and ct.status =''active''\r\n"
							+ "left join tb_psg_mstr_state st on st.state_id = cl.state_of_birth and st.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dt on dt.district_id = cl.district_of_birth and dt.status=''active''\r\n"
							+ "left join tb_psg_mstr_nationality nati on nati.nationality_id=cl.nationality and nati.status=''active''\r\n"
							+ "left join tb_psg_mothertounge mol on mol.id=cl.mother_tongue and mol.status=''active''\r\n"
							+ "left join tb_psg_mstr_religion rel on rel.religion_id=cl.religion and rel.status=''active''\r\n"
							+ "left join tb_psg_census_language_jco l on l.jco_id = cl.id and l.status=''1''\r\n"
							+ "left join tb_psg_mstr_indian_language il on il.id =l.language  and il.status=''active''\r\n"
							+ "left join tb_psg_mstr_marital_status ms on ms.marital_id=cl.marital_status and ms.status=''active''\r\n"
							+ "left join tb_psg_mstr_blood bg on bg.id=cl.blood_group and bg.status=''active''\r\n"
							+ "left join tb_psg_mstr_height h on h.height_id=cl.height and h.status=''active''\r\n"
							+ "left join tb_psg_identity_card_jco idc on idc.jco_id=cl.id  and idc.status=''1''\r\n"
							+ "left join tb_miso_orbat_unt_dtl jjj on jjj.sus_no=idc.issue_authority and jjj.status_sus_no=''Active''\r\n"
							+ "left join tb_psg_mstr_hair_colour hc on hc.id=idc.hair_colour  and hc.status=''active''\r\n"
							+ "left join tb_psg_mstr_eye_colour ec on ec.id=idc.eye_colour and ec.status=''active''\r\n"
							+ " left join tb_psg_census_address_jco ca on ca.jco_id = cl.id and  ca.status=''1''\r\n"
							+ "left join tb_psg_mstr_country ctop on ctop.id = ca.pre_country and ctop.status=''active''\r\n"
							+ "left join tb_psg_mstr_state stop on stop.state_id = ca.pre_state and stop.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dtop on dtop.district_id = ca.pre_district and dtop.status=''active''\r\n"
							+ "left join tb_psg_mstr_city ttop on ttop.city_id = ca.pre_tesil and ttop.status=''active''\r\n"
							+ "left join tb_psg_mstr_country ctopp on ctopp.id = ca.permanent_country and ctopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_state stopp on stopp.state_id = ca.permanent_state and stopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dtopp on dtopp.district_id = ca.permanent_district and dtopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_city ttopp on ttopp.city_id = ca.permanent_tehsil and ttopp.status=''active''\r\n"
							+ "left join tb_psg_mstr_country pctop on pctop.id = ca.present_country and pctop.status=''active''\r\n"
							+ "left join tb_psg_mstr_state pstop on pstop.state_id = ca.present_state and pstop.status=''active''\r\n"
							+ "left join tb_psg_mstr_district pdtop on pdtop.district_id = ca.pre_district and pdtop.status=''active''\r\n"
							+ "left join tb_psg_mstr_city pttop on pttop.city_id = ca.present_tehsil and pttop.status=''active''\r\n"
							+ "left join tb_psg_census_nok_jco nok on nok.jco_id = cl.id and nok.status=''1''\r\n"
							+ "left join tb_psg_mstr_relation rela on rela.id = nok.nok_relation and rela.status=''active''\r\n"
							+ "left join tb_psg_mstr_country coun on coun.id = nok.nok_country and coun.status=''active''\r\n"
							+ "left join tb_psg_mstr_state sta on sta.state_id = nok.nok_state and coun.status=''active''\r\n"
							+ "left join tb_psg_mstr_district dis on dis.district_id = nok.nok_district and dis.status=''active''\r\n"
							+ "left join tb_psg_mstr_city nteh on nteh.city_id = nok.nok_tehsil and nteh.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen exser on cast(exser.id as character varying)  = cl.father_service and exser.status=''active''\r\n"
							+ "left join tb_psg_mstr_exservicemen mexser on cast(mexser.id as character varying)  = cl.mother_service  and mexser.status=''active''\r\n"
							+ "left join tb_psg_census_cadet cadet on cadet.comm_id  = cl.id  and cadet.status=''1''\r\n"
							+ "left join tb_psg_mstr_pre_cadet_status mcre on mcre.id  = cadet.status  and mcre.status=''active''\r\n"
							+ "left join tb_psg_mstr_specialization spe on spe.id  = cadet.competency  and spe.status=''active''\r\n"
							+ "left join tb_psg_census_ncc_exp ncc on ncc.comm_id  = cl.id  and ncc.status=''1''\r\n"
							+ "left join tb_psg_change_of_rank_jco rk on rk.jco_id = cl.id and rk.status=''1''\r\n"
							+ "left join t_domain_value td on td.codevalue=cast(rk.rank_type  as char) and td.domainid=''OFFR_RANK_TYPE''\r\n"
							+ "left join tb_psg_mstr_rank_jco ran1 on ran1.id = rk.rank and ran1.status = ''active'' and ran.category=''OR''			\r\n"
							+ "left join tb_psg_change_of_appointment ap on ap.comm_id=cl.id and ap.status=''1''\r\n"
							+ "left join cue_tb_psg_rank_app_master appn on appn.id=ap.appointment \r\n"
							+ "and upper(appn.level_in_hierarchy) = ''APPOINTMENT'' and appn.parent_code =''0'' and appn.status_active = ''Active''\r\n"
							+ "left join tb_psg_census_contact_cda_account_details_jco con on con.jco_id=cl.id and con.status=''1''\r\n"
							+ "left join tb_psg_census_battle_physical_casuality_jco bat on bat.jco_id=cl.id  and bat.status=''1''\r\n"
							+ "left join tb_psg_mstr_country bcon on cast(bcon.id as character varying) = bat.country and bcon.status=''active''\r\n"
							+ "left join tb_psg_mstr_state bts on cast(bts.state_id as character varying) = bat.state and bts.status=''active''\r\n"
							+ "left join tb_psg_mstr_district bpd on cast(bpd.district_id as character varying) = bat.district and bpd.status=''active''\r\n"
							+ "left join tb_psg_mstr_city bpcc on cast(bpcc.city_id as character varying) = bat.tehsil and bpcc.status=''active''\r\n"
							+ "left join orbat_view_bde bd on bd.sus_no=bat.bde\r\n"
							+ "left join orbat_view_div di on di.sus_no=bat.div_subarea\r\n"
							+ "left join orbat_view_corps co on co.sus_no=bat.corps_area \r\n"
							+ "left join  orbat_view_cmd cm on cm.sus_no=bat.command  \r\n"
							+ "left join tb_psg_census_discipline_jco disci on disci.jco_id=cl.id and disci.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_mstr_army_act_sec arse on arse.id=disci.army_act_sec and arse.status=''active''\r\n"
							+ "left join tb_psg_mstr_sub_clause subc on subc.id=disci.sub_clause and subc.status=''active''\r\n"
							+ "left join tb_psg_mstr_disc_trialed ditri on ditri.id=disci.trialed_by and ditri.status=''active''\r\n"
							+ "left join t_domain_value dictd on dictd.codevalue=cast(disci.type_of_entry as character varying) and dictd.domainid=''DISCIPLINE''\r\n"
							+ "left join tb_miso_orbat_unt_dtl disor on disor.sus_no=disci.unit_name and disor.status_sus_no=''Active''\r\n"
							+ "left join tb_psg_change_of_comission chc on chc.comm_id=cl.id and chc.status=''1'' and cl.status=''1''\r\n"
							+ "left join tb_psg_extension_of_comission ext on ext.comm_id=cl.id and ext.status=''1''\r\n"
							+ "left join tb_psg_census_secondment sec on sec.comm_id=cl.id and sec.status=''1''\r\n"
							+ "left join tb_psg_mstr_seconded_to sem on sem.id=sec.seconded_to  and sem.status=''active''\r\n"
							+ "left join tb_psg_non_effective_jco non on non.jco_id=cl.id and non.status=''1''\r\n"
							+ "left join tb_psg_mstr_cause_of_non_effective cau on cau.id=cast(non.cause_of_non_effective as integer)  and cau.status=''active''\r\n"
							+ "left join tb_psg_deserter_jco dest on dest.jco_id=cl.id and dest.status=''1''\r\n"
							+ "left join t_domain_value detd on detd.codevalue=dest.arm_type and detd.domainid=''ARM_TYPE''\r\n"
							+ "left join t_domain_value cdes on cdes.codevalue=dest.desertion_cause and cdes.domainid=''CAUSE_OF_DESRTION'' \r\n"
							+ "left join tb_miso_orbat_unt_dtl u on cl.unit_sus_no = u.sus_no and u.status_sus_no=''Active''\r\n"
							+ "where  (cl.status not in (''0'',''3'',''-1'',''4'') and cl.category=''OR'')')\r\n"
							+ "AS t(army_no character varying,first_name character varying,full_name character varying,middle_name character varying,gender character varying,\r\n"
							+ "         date_of_seniority timestamp without time zone,rank character varying,rank_intake character varying,date_of_birth timestamp without time zone,\r\n"
							+ "unit_sus_no character varying,unit_posted_to character varying,date_of_tos timestamp without time zone,arm_desc character varying,\r\n"
							+ "         country_of_birth character varying,state_of_birth character varying,district_of_birth character varying,place_of_birth character varying,\r\n"
							+ "         nationality character varying,mother_tongue character varying,religion character varying,aadhar_no character varying, \r\n"
							+ "         pan_no character varying,maritial_status character varying, blood_group character varying,height character varying,id_card_no  character varying,\r\n"
							+ "         date_of_issuing timestamp without time zone, issuing_authority  character varying, \r\n"
							+ " visible_identification_marks character varying,hair_colour character varying, eye_colour character varying,\r\n"
							+ "         presently_domicile_country character varying,presently_domicile_state character varying,presently_domicile_district character varying,\r\n"
							+ "presently_domicile_tehsil character varying,permanent_country character varying, permanent_state character varying, permanent_district character varying,\r\n"
							+ "         permanent_tehsil character varying,permanent_village_town_city character varying,permanent_pin character varying,\r\n"
							+ "         permanent_nearest_railway_station character varying,permanent_rural_urban_semi_urban character varying, present_country character varying,\r\n"
							+ "         present_state character varying, present_district character varying, present_tehsil character varying, present_village_town_city character varying,\r\n"
							+ "present_pin character varying, present_nearest_railway_station character varying, present_rural_urban_semi_urban character varying,\r\n"
							+ "         nok_name character varying, \r\n"
							+ "         nok_relation character varying, nok_country character varying, nok_state character varying, nok_district character varying,\r\n"
							+ "nok_tehsil character varying,  nok_village_town_city character varying ,nok_pin character varying, nok_nearest_railway_station character varying, \r\n"
							+ "         nok_rural_urban_semi_urban character varying, nok_mobile_no character varying,father_name character varying,\r\n"
							+ "         date_of_birth_father character varying,\r\n"
							+ "         father_place_of_birth character varying,father_service character varying,father_personal_no character varying,mother_name character varying, \r\n"
							+ "         date_of_mother character varying, mother_place_of_birth character varying,mother_service character varying,mother_personal_no character varying, \r\n"
							+ "        gmail character varying,nic_mail character varying,mobile_no character varying , \r\n"
							+ "         date_of_casulaty character varying,time_of_casuality character varying,onduty character varying, batnpc_country character varying,\r\n"
							+ "         batnpc_state character varying,batnpc_district character varying,batnpc_tehsil character varying, \r\n"
							+ " batnpc_village_town_city character varying, batnpc_pin character varying,batnpc_exact_place_area_post character varying,\r\n"
							+ "         batnpc_name_of_operation character varying,batnpc_sector character varying, batnpc_filed_area character varying, batnpc_whether_on character varying,\r\n"
							+ " batnpc_bde character varying, batnpc_div character varying, batnpc_corp character varying, batnpc_command character varying,\r\n"
							+ "         batnpc_hospital_name character varying, batnpc_hospital_location character varying, batnpc_cause_of_casualty character varying,\r\n"
							+ " batnpc_circumstances character varying,batnpc_diagnosis character varying,batnpc_aid_to_civ character varying, batnpc_nok_informed character varying,\r\n"
							+ "         date_of_informing_batnpc character varying,batnpc_time_of_informing character varying,\r\n"
							+ "batnpc_method_of_informing character varying, batnpc_category_of_casualty character varying,army_act_sec character varying,\r\n"
							+ "         sub_clause character varying, trialed_by character varying, punishment_awarded character varying, discipline_type_of_entry character varying,\r\n"
							+ " date_of_discipline_award character varying ,discipline_unit_name character varying, \r\n"
							+ "cause_of_non_effective character varying,date_of_non_effective character varying, non_eff_country character varying, \r\n"
							+ "         non_eff_state character varying, non_eff_district character varying,\r\n"
							+ "         non_eff_tehsil character varying, non_eff_village_town_city character varying, non_eff_pin character varying, non_eff_nearest_railway_station character varying,\r\n"
							+ " non_eff_rural_urban_semi_urban character varying, non_eff_border_area character varying, \r\n"
							+ " month character varying, year character varying,unit_name character varying, age character varying,\r\n"
							+ "         form_code_control character varying,year_of_service character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int aac = pstmt53.executeUpdate();

			// LANGUAGE DETAILS OR
			PreparedStatement pstmt54 = c.prepareStatement(
					"insert into tb_olap_language_or (authority,date_of_authority,f_exam_pass,initiated_from ,f_lang_prof,foreign_language,lang_std,\r\n"
							+ "language,unit_name,unit_sus_no ,form_code_control,army_no,month,year,ct_part_i_ii,type_of_location)\r\n"
							+ "SELECT authority,date_of_authority,f_exam_pass,initiated_from ,f_lang_prof,foreign_language,lang_std,\r\n"
							+ "language,army_no,month,year,unit_name,unit_sus_no ,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.authority,a.date_of_authority,a.f_exam_pass,a.initiated_from ,e.name as f_lang_prof,f.foreign_language_name as foreign_language,ls.name as lang_std,\r\n"
							+ "il.ind_language as language,b.army_no,date_part(''month'', (SELECT current_timestamp)) as month,date_part(''year'', (SELECT current_timestamp)) as year,u.unit_name,\r\n"
							+ "b.unit_sus_no ,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_language_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id and b.status=''1''  and b.category= ''OR''\r\n"
							+ "inner join tb_psg_lang_proof e on  e.id= a.f_lang_prof and e.status=''active'' \r\n"
							+ "inner join tb_psg_mstr_foreign_language f on  f.id= a.foreign_language and f.status=''active''\r\n"
							+ "inner join tb_psg_lang_std ls on  ls.id= a.lang_std and ls.status=''active''\r\n"
							+ "left join tb_psg_mstr_indian_language il on  il.id= a.language and il.status=''active''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'')\r\n"
							+ "')\r\n"
							+ "AS t(authority character varying,date_of_authority timestamp without time zone,f_exam_pass character varying,initiated_from character varying,f_lang_prof character varying,foreign_language character varying,lang_std character varying,\r\n"
							+ "language character varying,unit_name character varying,unit_sus_no character varying,form_code_control character varying,army_no character varying,month character varying,\r\n"
							+ "year character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int aad = pstmt54.executeUpdate();

			// QUALIFICATION DETAILS OR

			PreparedStatement pstmt55 = c.prepareStatement(
					"insert into tb_olap_qualification_details_or (passing_year, type, authority, date_of_authority, institute, examination_pass, \r\n"
							+ "specialization , degree, div_class,  subject, army_no,month,year, unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
							+ "SELECT passing_year, type, authority, date_of_authority, institute, examination_pass, \r\n"
							+ "specialization , degree, div_class,  subject, army_no,month,year , unit_sus_no, unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select a.passing_year,e.label as type,a.authority,a.date_of_authority,a.institute,exa.examination_passed as examination_pass,\r\n"
							+ "(case  when a.specialization_other = null  or a.specialization_other is null then  sp.specialization\r\n"
							+ "else a.specialization_other end ) as specialization,td.degree,\r\n"
							+ "(case  when a.class_other = null or a.class_other is null then dc.div\r\n"
							+ "else a.class_other end ) as div_class,array_to_string(ARRAY(select sub.description \r\n"
							+ "from unnest(string_to_array((select subject from tb_psg_census_qualification_jco where id=a.id), '','')) qsub\r\n"
							+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject,\r\n"
							+ "p.army_no,\r\n" + "date_part(''month'', (SELECT current_timestamp)) as month,\r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,p.unit_sus_no,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_census_qualification_jco a\r\n"
							+ "inner join tb_psg_census_jco_or_p p on a.jco_id = p.id and p.status=''1'' and p.category =''OR''\r\n"
							+ "left join t_domain_value e on  e.codevalue  = a.type  and  domainid=''QUALIFICATION_TYPE''\r\n"
							+ "inner join tb_psg_mstr_examination_passed exa on exa.id=a.examination_pass  and exa.status=''active''\r\n"
							+ "inner join tb_psg_mstr_specialization sp on sp.id=a.specialization  and sp.status=''active''\r\n"
							+ "inner join  tb_psg_mstr_degree td on td.id =a.degree and td.status=''active'' \r\n"
							+ "inner join  tb_psg_mstr_div_grade dc on dc.id =a.div_class and dc.status=''active''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = p.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and p.status not in (''0'',''3'',''4'',''-1'') ') \r\n"
							+ "AS t(passing_year integer, type character varying, authority character varying, date_of_authority timestamp without time zone, \r\n"
							+ "institute character varying, examination_pass character varying, \r\n"
							+ "specialization character varying, degree character varying, div_class character varying,  subject character varying,army_no character varying, \r\n"
							+ "month character varying,year character varying , unit_sus_no character varying, unit_name character varying,form_code_control  character varying\r\n"
							+ "	,ct_part_i_ii character varying , type_of_location character varying)");

			int aae = pstmt55.executeUpdate();

			// SPOUSE QUALIFICATION DETAILS OR

			PreparedStatement pstmt56 = c.prepareStatement("\r\n"
					+ "insert into tb_olap_spouse_qualification_or (qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
					+ " examination_pass , specialization , degree,  army_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location)   \r\n"
					+ "SELECT  qualification_type, passing_year,  div_class, subject ,institute, authority, date_of_authority, \r\n"
					+ " examination_pass , specialization , degree,  army_no,month,year, unit_sus_no , unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
					+ "FROM dblink('myconn','select t.label as qualification_type,a.passing_year,\r\n"
					+ "(case  when a.class_other = null or a.class_other is null then g.div\r\n"
					+ "else a.class_other end ) as div_class,\r\n"
					+ "array_to_string(ARRAY(select sub.description  \r\n"
					+ "from unnest(string_to_array((select subject from tb_psg_census_spouse_qualification_jco where id=a.id), '','')) qsub \r\n"
					+ "inner join tb_psg_census_subject sub on sub.id=cast(qsub as integer)),'','') as subject, \r\n"
					+ "a.institute,a.authority,a.date_of_authority,d.examination_passed as examination_pass , \r\n"
					+ "(case  when a.specialization_other = null  or a.specialization_other is null then  s.specialization\r\n"
					+ "else a.specialization_other end ) as specialization,md.degree,p.army_no, \r\n"
					+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
					+ "date_part(''year'', (SELECT current_timestamp)) as year ,p.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
					+ "from tb_psg_census_spouse_qualification_jco a   \r\n"
					+ "inner join tb_psg_census_jco_or_p  p on p.id = a.jco_id and p.status=''1''  and p.category =''OR''\r\n"
					+ "left join t_domain_value t on t.codevalue=cast(a.type as character varying)  and domainid=''QUALIFICATION_TYPE'' \r\n"
					+ "left join tb_psg_mstr_div_grade g on cast(g.id as character varying)  = a.div_class and g.status=''active''  \r\n"
					+ "left join tb_psg_census_subject cs on cast(cs.id as character varying) = a.subject   \r\n"
					+ "left join tb_psg_mstr_examination_passed d on d.id = a.examination_pass and d.status=''active'' \r\n"
					+ "left join tb_psg_mstr_specialization s on s.id = a.specialization and s.status=''active'' \r\n"
					+ "left join tb_psg_mstr_degree md on md.id = a.degree and md.status=''active'' \r\n"
					+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = p.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
					+ "where  (a.status not in (''0'',''3'',''-1'')) and p.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
					+ "AS t(qualification_type character varying, passing_year character varying,  div_class character varying, subject character varying , \r\n"
					+ "institute character varying, authority character varying, date_of_authority timestamp without time zone,  examination_pass character varying , \r\n"
					+ "specialization character varying, degree character varying, army_no character varying,  \r\n"
					+ "month character varying,year character varying, unit_sus_no character varying, unit_name character varying,form_code_control character varying\r\n"
					+ ",ct_part_i_ii  character varying,type_of_location  character varying)");

			int aaf = pstmt56.executeUpdate();

			// MEDICAL CATEGORY HISTORY OR

			PreparedStatement pstmt57 = c.prepareStatement(
					" insert into tb_olap_medical_categoryhistory_or  (shape,date_of_authority,cope,med_classification_lmc,month,year,army_no,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT shape,date_of_authority,cope,med_classification_lmc,month,year,army_no ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location\r\n"
							+ "FROM dblink('myconn','select  a.shape,a.date_of_authority,a.cope,a.med_classification_lmc, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year , \r\n"
							+ "b.army_no as army_no ,b.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_medical_categoryhistory_jco a  \r\n"
							+ "inner join tb_psg_census_jco_or_p b on b.id=a.jco_id  and b.status =''1''  and b.category =''OR''\r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = b.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and b.status not in (''0'',''3'',''4'',''-1'') ')  \r\n"
							+ "AS t(shape character varying,date_of_authority timestamp without time zone,cope character varying, \r\n"
							+ " med_classification_lmc character varying,month character varying,year character varying,army_no character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii character varying,type_of_location character varying)");

			int aag = pstmt57.executeUpdate();

			// FIELD SERVICE OR

			PreparedStatement pstmt58 = c.prepareStatement(
					"insert into tb_olap_field_service_or (army_no,field_area,authority,authority_date,operation_name,unit_location,  \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location)  \r\n"
							+ "SELECT army_no,field_area,authority,authority_date,operation_name,unit_location, \r\n"
							+ "sus_no,from_date,to_date,duration,place,month,year ,unit_sus_no ,unit_name,form_code_control,ct_part_i_ii,type_of_location  \r\n"
							+ "FROM dblink('myconn','select d.personnel_no as army_no ,c.field_area as field_area ,a.authority as authority,  \r\n"
							+ "a.authority_date as authority_date,  e.operation_name as operation_name,a.unit_location as unit_location, \r\n"
							+ "a.sus_id as sus_no,d.from_date as from_date,d.to_date as to_date,d.duration as duration,d.place as place, \r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month,  \r\n"
							+ "date_part(''year'', (SELECT current_timestamp)) as year ,jc.unit_sus_no ,u.unit_name,u.form_code_control,u.ct_part_i_ii,u.type_of_location\r\n"
							+ "from tb_psg_field_service_p_jco a \r\n"
							+ "inner join tb_psg_mstr_field_area c on c.id = a.fd_services and c.status =''active'' \r\n"
							+ "inner join tb_psg_field_service_ch_jco d on d.p_id = a.id  \r\n"
							+ "inner join tb_psg_census_jco_or_p jc on jc.id=d.jco_id and jc.category =''OR''\r\n"
							+ "inner join tb_psg_mstr_operation e on e.id = a.operation and e.status=''active'' \r\n"
							+ "left join  tb_miso_orbat_unt_dtl u on u.sus_no = jc.unit_sus_no and u.status_sus_no  = ''Active''\r\n"
							+ "where  (a.status not in (''0'',''3'',''-1'')) and jc.status not in (''0'',''3'',''4'',''-1'')')  \r\n"
							+ "AS t(army_no character varying,field_area character varying,authority character varying,authority_date timestamp without time zone, \r\n"
							+ "operation_name character varying,unit_location character varying,sus_no character varying,from_date timestamp without time zone, \r\n"
							+ "to_date timestamp without time zone,duration character varying,place character varying,month character varying,year character varying\r\n"
							+ ",unit_sus_no character varying,unit_name character varying,form_code_control character varying,ct_part_i_ii  character varying,type_of_location character varying)  \r\n"
							+ " ");

			int aah = pstmt58.executeUpdate();

			// civilian regular
			PreparedStatement pstmt59 = c.prepareStatement(
					"insert into tb_olap_civilian_dtl (sus_no,first_name,middle_name,last_name,dob,gender,classification_services,civ_group,category_belongs,service_status,classification_trade,\r\n"
							+ "                         civ_type,whether_ex_serviceman,whether_person_disability,post_initialy_appointed,joining_date_gov_service,designation,designation_date,\r\n"
							+ "                                   pay_level,father_name,mother_name,state_original,district_original,tehsil_origin,state_present,district_present,tehsil_present,nationality,religion,\r\n"
							+ "                                   mother_tongue,aadhar_card,non_effective,date_non_effective,civilian_status,country_original,country_present,\r\n"
							+ "                                   employee_no,authority,dt_of_authority,pan_no,full_name,cadre,main_id,date_of_tos,month,year) \r\n"
							+ "	         		SELECT sus_no,first_name,middle_name,last_name,dob,gender,classification_services,civ_group,category_belongs,service_status,classification_trade,\r\n"
							+ "                         civ_type,whether_ex_serviceman,whether_person_disability,post_initialy_appointed,joining_date_gov_service,designation,designation_date,\r\n"
							+ "                                   pay_level,father_name,mother_name,state_original,district_original,tehsil_origin,state_present,district_present,tehsil_present,nationality,religion,\r\n"
							+ "                                   mother_tongue,aadhar_card,non_effective,date_non_effective,civilian_status,country_original,country_present,\r\n"
							+ "                                   employee_no,authority,dt_of_authority,pan_no,full_name,cadre,main_id,date_of_tos,month,year \r\n"
							+ "	         		FROM dblink('myconn','select  a.sus_no,a.first_name,a.middle_name,a.last_name,a.dob,b.gender_name as gender,c.label as classification_services,\r\n"
							+ "d.label as civ_group,e.category as category_belongs,f.label as service_status,\r\n"
							+ "(case  when a.classification_trade_other = null or  a.classification_trade_other = '''' or a.classification_trade_other is null then  g.label \r\n"
							+ "else a.classification_trade_other end ) as classification_trade,h.label as civ_type,\r\n"
							+ "(case  when a.service_other = null or  a.service_other = '''' or a.service_other is null then  COALESCE(i.ex_servicemen,'''')\r\n"
							+ "else a.service_other end ) as whether_ex_serviceman,\r\n"
							+ "COALESCE(j.disability,'''') as whether_person_disability,k.description as post_initialy_appointed,a.joining_date_gov_service,l.description as designation,\r\n"
							+ "a.designation_date,m.pay_level as pay_level,a.father_name,a.mother_name,\r\n"
							+ "(case  when a.original_state_other = null or  a.original_state_other = '''' or a.original_state_other is null then  COALESCE(n.state_name,'''')\r\n"
							+ "else a.original_state_other end ) as state_original,\r\n"
							+ "(case  when a.original_district_other = null or  a.original_district_other = '''' or a.original_district_other is null then  COALESCE(o.district_name,'''')\r\n"
							+ "else a.original_district_other end ) as district_original,\r\n"
							+ "(case  when a.original_tehshil_other = null or  a.original_tehshil_other = '''' or a.original_tehshil_other is null then  COALESCE(q.city_name,'''')\r\n"
							+ "else a.original_tehshil_other end ) as tehsil_origin,\r\n"
							+ "(case  when a.present_state_other = null or  a.present_state_other = '''' or a.present_state_other is null then  COALESCE(r.state_name,'''')\r\n"
							+ "else a.present_state_other end ) as state_present,\r\n"
							+ "(case  when a.present_district_other = null or  a.present_district_other = '''' or a.present_district_other is null then  COALESCE(s.district_name,'''')\r\n"
							+ "else a.present_district_other end ) as district_present,\r\n"
							+ "(case  when a.present_tehshil_other = null or  a.present_tehshil_other = '''' or a.present_tehshil_other is null then  COALESCE(u.city_name,'''')\r\n"
							+ "else a.present_tehshil_other end ) as tehsil_present,\r\n"
							+ "(case  when a.nationality_other = null or  a.nationality_other = '''' or a.nationality_other is null then  COALESCE(v.nationality_name,'''')\r\n"
							+ "else a.nationality_other end ) as nationality,\r\n"
							+ "(case  when a.religion_other = null or  a.religion_other = '''' or a.religion_other is null then  COALESCE(w.religion_name,'''')\r\n"
							+ "else a.religion_other end ) as religion,\r\n"
							+ "(case  when a.mother_tongue_other = null or  a.mother_tongue_other = '''' or a.mother_tongue_other is null then  COALESCE(x.mothertounge,'''')\r\n"
							+ "else a.mother_tongue_other end ) as mother_tongue,a.aadhar_card,y.causes_name as non_effective,a.date_non_effective as date_non_effective,''R'' as civilian_status,\r\n"
							+ "(case  when a.original_country_other = null or  a.original_country_other = '''' or a.original_country_other is null then  COALESCE(p.name,'''')\r\n"
							+ "else a.original_country_other end ) as country_original,\r\n"
							+ "(case  when a.present_country_other = null or  a.present_country_other = '''' or a.present_country_other is null then  COALESCE(t.name,'''')\r\n"
							+ "else a.present_country_other end ) as country_present,a.employee_no,a.authority,a.dt_of_authority,a.pan_no,a.full_name,z.cadre,a.main_id,a.date_of_tos,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, date_part(''year'', (SELECT current_timestamp)) as year\r\n"
							+ "from tb_psg_civilian_dtl a\r\n" + "inner join tb_psg_mstr_gender b on b.id=a.gender\r\n"
							+ "inner join t_domain_value c on c.codevalue = a.classification_services and c.domainid=''CLASSIFICATION_OF_SERVICES'' \r\n"
							+ "inner join t_domain_value d on d.codevalue = a.civ_group and d.domainid=''CIV_R_GROUP'' \r\n"
							+ "inner join tb_psg_mstr_category e on e.id = a.category_belongs  and e.status = ''active'' \r\n"
							+ "inner join t_domain_value f on f.codevalue = a.service_status and f.domainid=''SERVICE_STATUS'' \r\n"
							+ "inner join t_domain_value g on cast(g.codevalue as integer) = a.classification_trade and g.domainid=''CLASIFICATION_OF_TRADES'' \r\n"
							+ "inner join t_domain_value h on h.codevalue = a.civ_type and h.domainid=''CIVILIAN_TYPE'' \r\n"
							+ "left join tb_psg_mstr_exservicemen i on cast(i.id as character varying) = a.whether_ex_serviceman and i.status=''active''\r\n"
							+ "left join tb_psg_mstr_disability j on cast(j.id as character varying) = a.whether_person_disability and j.status=''active''\r\n"
							+ "inner join cue_tb_psg_rank_app_master k  on cast(k.id as character varying) = a.post_initialy_appointed and upper(k.level_in_hierarchy) = upper(''Appointment'') and k.parent_code in (''4'',''5'',''6'',''7'',''8'',''9'',''10'',''11'') and k.status_active = ''Active''\r\n"
							+ "inner join cue_tb_psg_rank_app_master l  on l.id = a.designation and upper(l.level_in_hierarchy) = upper(''Appointment'') and l.parent_code in (''4'',''5'',''6'',''7'',''8'',''9'',''10'',''11'') and l.status_active = ''Active''\r\n"
							+ "inner join tb_psg_mstr_pay_level m on m.id = a.pay_level and m.status = ''active''\r\n"
							+ "inner join tb_psg_mstr_state n on n.state_id = a.state_original and n.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district o on o.district_id = a.district_original and o.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country p on p.id = a.country_original and p.status=''active'' 														 		\r\n"
							+ "inner join tb_psg_mstr_city q on q.city_id = a.tehsil_origin and q.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state r on r.state_id = a.state_present and r.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district s on s.district_id = a.district_present and s.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country t on t.id = a.country_present and t.status=''active'' 														 		\r\n"
							+ "inner join tb_psg_mstr_city u on u.city_id = a.tehsil_present and u.status=''active''\r\n"
							+ "inner join tb_psg_mstr_nationality v on v.nationality_id = a.nationality and v.status=''active''\r\n"
							+ "inner join tb_psg_mstr_religion w on w.religion_id = a.religion and w.status=''active''\r\n"
							+ "inner join tb_psg_mothertounge x on x.id = a.mother_tongue and x.status=''active''\r\n"
							+ "inner join tb_psg_mstr_cause_of_non_effective_civilian y on y.id = a.non_effective and y.status=''active''\r\n"
							+ "inner join tb_psg_mstr_cadre_civilian z on z.id = a.cadre and z.status=''active''\r\n"
							+ "where a.civilian_status=''R'' and a.status not in (''0'',''3'',''-1'')')  \r\n"
							+ "	         		AS t(sus_no character varying,first_name character varying,middle_name character varying,last_name character varying,dob timestamp without time zone,gender character varying,classification_services character varying,civ_group character varying,category_belongs character varying,service_status character varying,classification_trade character varying,\r\n"
							+ "                         civ_type character varying,whether_ex_serviceman character varying,whether_person_disability character varying,post_initialy_appointed character varying,joining_date_gov_service timestamp without time zone,designation character varying,designation_date timestamp without time zone,\r\n"
							+ "                                   pay_level character varying,father_name character varying,mother_name character varying,state_original character varying,district_original character varying,tehsil_origin character varying,state_present character varying,district_present character varying,tehsil_present character varying,nationality character varying,religion character varying,\r\n"
							+ "                                   mother_tongue character varying,aadhar_card character varying,non_effective character varying,date_non_effective timestamp without time zone,civilian_status character varying,country_original character varying,country_present character varying,\r\n"
							+ "                                   employee_no character varying,authority character varying,dt_of_authority timestamp without time zone,pan_no character varying,full_name character varying,cadre character varying,main_id character varying,date_of_tos timestamp without time zone,month character varying,year character varying) ");

			int aai = pstmt59.executeUpdate();

			// civilian non-regular
			PreparedStatement pstmt62 = c.prepareStatement(
					"insert into tb_olap_civilian_dtl (sus_no,first_name,middle_name,last_name,dob,gender,category_belongs,civ_type,joining_date_gov_service,\r\n"
							+ "                                 pay_level,state_original,district_original,tehsil_origin,state_present,district_present,tehsil_present,nationality,\r\n"
							+ "                                religion,mother_tongue,aadhar_card,non_effective,date_non_effective,civilian_status,country_original,country_present,\r\n"
							+ "                                 employee_no,authority,dt_of_authority,pan_no,full_name,main_id,date_of_tos,month,year)\r\n"
							+ "SELECT sus_no,first_name,middle_name,last_name,dob,gender,category_belongs,civ_type,joining_date_gov_service,\r\n"
							+ "                                 pay_level,state_original,district_original,tehsil_origin,state_present,district_present,tehsil_present,nationality,\r\n"
							+ "                                religion,mother_tongue,aadhar_card,non_effective,date_non_effective,civilian_status,country_original,country_present,\r\n"
							+ "                                 employee_no,authority,dt_of_authority,pan_no,full_name,main_id,date_of_tos,month,year \r\n"
							+ "FROM dblink('myconn','select  a.sus_no,a.first_name,a.middle_name,a.last_name,a.dob,b.gender_name as gender,\r\n"
							+ "e.category as category_belongs,\r\n" + "h.label as civ_type,\r\n"
							+ "a.joining_date_gov_service,\r\n" + "m.pay_head as pay_level,\r\n"
							+ "(case  when a.original_state_other = null or  a.original_state_other = '''' or a.original_state_other is null then  COALESCE(n.state_name,'''')\r\n"
							+ "else a.original_state_other end ) as state_original,\r\n"
							+ "(case  when a.original_district_other = null or  a.original_district_other = '''' or a.original_district_other is null then  COALESCE(o.district_name,'''')\r\n"
							+ "else a.original_district_other end ) as district_original,\r\n"
							+ "(case  when a.original_tehshil_other = null or  a.original_tehshil_other = '''' or a.original_tehshil_other is null then  COALESCE(q.city_name,'''')\r\n"
							+ "else a.original_tehshil_other end ) as tehsil_origin,\r\n"
							+ "(case  when a.present_state_other = null or  a.present_state_other = '''' or a.present_state_other is null then  COALESCE(r.state_name,'''')\r\n"
							+ "else a.present_state_other end ) as state_present,\r\n"
							+ "(case  when a.present_district_other = null or  a.present_district_other = '''' or a.present_district_other is null then  COALESCE(s.district_name,'''')\r\n"
							+ "else a.present_district_other end ) as district_present,\r\n"
							+ "(case  when a.present_tehshil_other = null or  a.present_tehshil_other = '''' or a.present_tehshil_other is null then  COALESCE(u.city_name,'''')\r\n"
							+ "else a.present_tehshil_other end ) as tehsil_present,\r\n"
							+ "(case  when a.nationality_other = null or  a.nationality_other = '''' or a.nationality_other is null then  COALESCE(v.nationality_name,'''')\r\n"
							+ "else a.nationality_other end ) as nationality,\r\n"
							+ "(case  when a.religion_other = null or  a.religion_other = '''' or a.religion_other is null then  COALESCE(w.religion_name,'''')\r\n"
							+ "else a.religion_other end ) as religion,\r\n"
							+ "(case  when a.mother_tongue_other = null or  a.mother_tongue_other = '''' or a.mother_tongue_other is null then  COALESCE(x.mothertounge,'''')\r\n"
							+ "else a.mother_tongue_other end ) as mother_tongue,a.aadhar_card,y.causes_name as non_effective,a.date_non_effective as date_non_effective,''NR'' as civilian_status,\r\n"
							+ "(case  when a.original_country_other = null or  a.original_country_other = '''' or a.original_country_other is null then  COALESCE(p.name,'''')\r\n"
							+ "else a.original_country_other end ) as country_original,\r\n"
							+ "(case  when a.present_country_other = null or  a.present_country_other = '''' or a.present_country_other is null then  COALESCE(t.name,'''')\r\n"
							+ "else a.present_country_other end ) as country_present,a.employee_no,a.authority,a.dt_of_authority,a.pan_no,a.full_name,\r\n"
							+ "a.main_id,\r\n" + "a.date_of_tos,\r\n"
							+ "date_part(''month'', (SELECT current_timestamp)) as month, date_part(''year'', (SELECT current_timestamp)) as year\r\n"
							+ "from tb_psg_civilian_dtl a\r\n" + "inner join tb_psg_mstr_gender b on b.id=a.gender\r\n"
							+ "inner join tb_psg_mstr_category e on e.id = a.category_belongs  and e.status = ''active'' \r\n"
							+ "inner join t_domain_value h on h.codevalue = a.civ_type and h.domainid=''CIVILIAN_TYPE'' \r\n"
							+ "inner join tb_psg_mstr_pay_head m on m.id = a.pay_level and m.status = ''active''\r\n"
							+ "inner join tb_psg_mstr_state n on n.state_id = a.state_original and n.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district o on o.district_id = a.district_original and o.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country p on p.id = a.country_original and p.status=''active'' 														 		\r\n"
							+ "inner join tb_psg_mstr_city q on q.city_id = a.tehsil_origin and q.status=''active''\r\n"
							+ "inner join tb_psg_mstr_state r on r.state_id = a.state_present and r.status=''active''\r\n"
							+ "inner join tb_psg_mstr_district s on s.district_id = a.district_present and s.status=''active''\r\n"
							+ "inner join tb_psg_mstr_country t on t.id = a.country_present and t.status=''active'' 														 		\r\n"
							+ "inner join tb_psg_mstr_city u on u.city_id = a.tehsil_present and u.status=''active''\r\n"
							+ "inner join tb_psg_mstr_nationality v on v.nationality_id = a.nationality and v.status=''active''\r\n"
							+ "inner join tb_psg_mstr_religion w on w.religion_id = a.religion and w.status=''active''\r\n"
							+ "inner join tb_psg_mothertounge x on x.id = a.mother_tongue and x.status=''active''\r\n"
							+ "left join tb_psg_mstr_cause_of_non_effective_civilian y on y.id = a.non_effective and y.status=''active''\r\n"
							+ "where a.civilian_status=''NR'' and a.status not in (''0'',''3'',''-1'')')  \r\n"
							+ "	         		AS t(sus_no character varying,first_name character varying,middle_name character varying,last_name character varying,dob timestamp without time zone,gender character varying,category_belongs character varying,civ_type character varying,joining_date_gov_service timestamp without time zone,\r\n"
							+ "                                 pay_level character varying,state_original character varying,district_original character varying,tehsil_origin character varying,state_present character varying,district_present character varying,tehsil_present character varying,nationality character varying,\r\n"
							+ "                                religion character varying,mother_tongue character varying,aadhar_card character varying,non_effective character varying,date_non_effective timestamp without time zone,civilian_status character varying,country_original character varying,country_present character varying,\r\n"
							+ "                                 employee_no character varying,authority character varying,dt_of_authority timestamp without time zone,pan_no character varying,full_name character varying,main_id character varying,date_of_tos timestamp without time zone,month character varying,year character varying) ");

			int aaj = pstmt62.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public Boolean iaff3008_MainDetails_Delete_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("delete  from tb_psg_iaff_3008_main_details m where m.id in (\r\n"
							+ "	select id from tb_psg_iaff_3008_main_details p1 inner join\r\n"
							+ "  (select month,year,sus_no,created_date \r\n"
							+ "	FROM dblink('myconn','select dm.month,dm.year,dm.sus_no,dm.created_date from tb_psg_iaff_3008_main_details dm where dm.status = 1') \r\n"
							+ "	AS t(month character varying,year character varying,sus_no character varying,created_date timestamp without time zone)\r\n"
							+ "	where (to_char(created_date,'YYYY/MM/DD') >=  to_char(date_trunc('month', current_date - interval '1' month),'YYYY/MM/DD')\r\n"
							+ "	and to_char(created_date,'YYYY/MM/DD') <=  to_char((date_trunc('MONTH',\r\n"
							+ "	 (to_char(date_trunc('month', current_date - interval '1' month),'YYYY/MM/DD'))::date) + INTERVAL '1 MONTH - 1 day')::DATE,'YYYY/MM/DD')) \r\n"
							+ "   and EXISTS (select dm.month,dm.year,dm.sus_no from tb_psg_iaff_3008_main_details dm )) p2\r\n"
							+ "   on (p1.month =p2.month and p1.year =p2.year and p1.sus_no =p2.sus_no)) ");

			/// ResultSet rs1 =stmt.executeQuery();
			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {

			System.exit(0);
			return false;
		}

		return true;
	}

	public void iaff3008_Serving_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_serving (personal_no,date_of_tos,\r\n"
							+ "tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,\r\n"
							+ "regiment,date_of_birth,date_of_commission,date_of_seniority,unit_name,created_date,created_by,\r\n"
							+ "date_of_appointment,month,year,version,med_cat,name\r\n"
							+ "FROM dblink('myconn','SELECT distinct s.personal_no,s.date_of_tos,s.tenure_date,\r\n"
							+ "s.sus_no,s.rank,s.appointment,s.cda_acc_no,s.parent_arm,s.regiment,\r\n"
							+ "s.date_of_birth,s.date_of_commission,s.date_of_seniority,s.unit_name,s.created_date,s.created_by,\r\n"
							+ "s.date_of_appointment,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version,s.med_cat,s.name \r\n"
							+ "FROM tb_psg_iaff_3008_serving s \r\n"
							+ "inner join tb_psg_iaff_3008_main v on s.version = v.version and v.month = s.month and \r\n"
							+ "v.year = s.year and v.sus_no = s.sus_no \r\n"
							+ "inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone,\r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ "version character varying,med_cat character varying,name character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3008_SuperNumarary_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_supernumerary (personal_no,date_of_tos,\r\n"
							+ "tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm, \r\n"
							+ "regiment,date_of_birth,date_of_commission,date_of_seniority,unit_name,created_date,created_by,\r\n"
							+ "date_of_appointment,month,year,version,med_cat,name  \r\n"
							+ "FROM dblink('myconn','SELECT distinct sp.personal_no,\r\n"
							+ "sp.date_of_tos,sp.tenure_date,sp.sus_no,sp.rank,sp.appointment,sp.cda_acc_no,sp.parent_arm,sp.regiment,sp.date_of_birth,\r\n"
							+ "sp.date_of_commission,sp.date_of_seniority,sp.unit_name,sp.created_date,sp.created_by,sp.date_of_appointment,v.month,v.year,\r\n"
							+ "(CASE  when v.status=0 then (cast(v.version as int) - 1) else cast(v.version as int)  END) as version,sp.med_cat,sp.name FROM \r\n"
							+ "tb_psg_iaff_3008_supernumerary sp inner join tb_psg_iaff_3008_main v on sp.version = v.version and \r\n"
							+ "v.month = sp.month and v.year = sp.year and v.sus_no = sp.sus_no "
							+ "inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3  ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone,\r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ " version character varying,med_cat character varying,name character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3008_Re_EmoloyeeMent_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_re_employeement (personal_no,date_of_tos,\r\n"
							+ "tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,\r\n"
							+ "regiment,date_of_birth,date_of_commission,date_of_seniority,unit_name,created_date,created_by,date_of_appointment,\r\n"
							+ "month,year,version,med_cat,name \r\n"
							+ "FROM dblink('myconn','SELECT distinct re.personal_no,re.date_of_tos,re.tenure_date,re.sus_no,re.rank,\r\n"
							+ "re.appointment,re.cda_acc_no,re.parent_arm,re.regiment,re.date_of_birth,re.date_of_commission,re.date_of_seniority, \r\n"
							+ "re.unit_name,re.created_date,re.created_by,re.date_of_appointment,v.month,v.year,\r\n"
							+ "(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version,re.med_cat,re.name \r\n"
							+ "FROM tb_psg_iaff_3008_re_employeement re inner join tb_psg_iaff_3008_main v on re.version = v.version \r\n"
							+ "and v.month = re.month  and v.year = re.year and v.sus_no = re.sus_no "
							+ "inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3  ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone, \r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying, \r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ " version character varying,med_cat character varying,name character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3008_Deserter_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_deserter (personal_no,date_of_tos,tenure_date,\r\n"
							+ "sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,\r\n"
							+ "date_of_commission,date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name \r\n"
							+ "FROM dblink('myconn','SELECT distinct ds.personal_no,ds.date_of_tos,ds.tenure_date,ds.sus_no,ds.rank,ds.appointment, \r\n"
							+ "ds.cda_acc_no,ds.parent_arm,ds.regiment,ds.date_of_birth,ds.date_of_commission,ds.date_of_seniority,ds.unit_name,\r\n"
							+ "ds.created_date,ds.created_by,ds.date_of_appointment,v.month,v.year,\r\n"
							+ "(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version,ds.med_cat,ds.name \r\n"
							+ "FROM tb_psg_iaff_3008_deserter ds inner join tb_psg_iaff_3008_main v on ds.version = v.version \r\n"
							+ "and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no "
							+ "inner join tb_psg_iaff_3008_main_details md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3  ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone, \r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ " version character varying,med_cat character varying,name character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public void iaff3009_MainDetails_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_iaff_3009_main_details (sus_no,auth,held,present_return_no,\r\n"
							+ "		  present_return_dt,we_pe_no,cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version) \r\n"
							+ "   SELECT sus_no,auth,held,present_return_no,present_return_dt,we_pe_no,\r\n"
							+ "		  cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version\r\n"
							+ "    FROM dblink('myconn','SELECT distinct md.sus_no,md.auth,md.held,md.present_return_no,md.present_return_dt,\r\n"
							+ "	md.we_pe_no,md.cmd_sus,md.cmd_unit,md.corp_sus,md.corp_unit,md.div_sus,md.div_unit,md.bde_sus,md.bde_unit,\r\n"
							+ "	md.observation,md.created_date,md.created_by,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version FROM tb_psg_iaff_3009_main_details md \r\n"
							+ "	inner join tb_psg_iaff_3009_main v on md.version = v.version and v.month = md.month and \r\n"
							+ "	v.year = md.year and v.sus_no = md.sus_no  where v.status != 3 ') \r\n"
							+ "	AS t(sus_no character varying,auth character varying,held character varying,\r\n"
							+ "		 present_return_no character varying,present_return_dt timestamp without time zone,\r\n"
							+ "	we_pe_no character varying,cmd_sus character varying,cmd_unit character varying,\r\n"
							+ "	corp_sus character varying,corp_unit character varying,div_sus character varying,div_unit character varying,\r\n"
							+ "	bde_sus character varying,bde_unit character varying,observation character varying,	 \r\n"
							+ "	created_date timestamp without time zone,created_by character varying,\r\n"
							+ "	month character varying,year character varying,version character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_authoffrs_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_auth_str_jco_or_3009 (arm_services,offrs,mns_offrs,jcos,ors,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date) \r\n"
							+ "	         		SELECT arm_services,offrs,mns_offrs,jcos,ors,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date\r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.arm_services,ds.offrs,ds.mns_offrs,ds.jcos,ds.ors,ds.remarks,  \r\n"
							+ "	         		ds.sus_no,v.month,v.year,\r\n"
							+ "	         		(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		FROM tb_psg_auth_str_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ')\r\n"
							+ "	         		AS t(arm_services character varying,offrs integer,mns_offrs integer,\r\n"
							+ "					jcos integer,ors integer, remarks character varying,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_authciv_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_auth_civ_3009 (gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date) \r\n"
							+ "	         		SELECT gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date\r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.gp_a_gazetted,ds.gp_b_gazetted,ds.gp_b_non_gazetted,\r\n"
							+ "								ds.gp_c_non_gazetted,ds.remarks,  \r\n"
							+ "	         		ds.sus_no,v.month,v.year,\r\n"
							+ "	         		(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		FROM tb_psg_auth_civ_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		v.year = md.year and v.sus_no = md.sus_no where v.status != 3')\r\n"
							+ "	         		AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted integer,\r\n"
							+ "						 gp_c_non_gazetted integer,remarks character varying,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			// String month1 = pstmt.getParameter(1);

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_postedoffrs_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_posted_offrs_jco_or_3009 (arms_services,brig_and_above_offrs,col_offrs,col_ts_offrs,lt_col_offrs,maj_offrs,\r\n"
							+ "											 capt_lt_offrs,brig_and_above_mns_offrs,col_mns_offrs,lt_col_s_mns_offrs,lt_col_ts_mns_offrs,maj_mns_offrs,\r\n"
							+ "											 capt_lt_mns_offrs,sub_major_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,nk_or,\r\n"
							+ "											 lnk_sep_or,rects_or,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date)\r\n"
							+ "	         		SELECT arms_services,brig_and_above_offrs,col_offrs,col_ts_offrs,lt_col_offrs,maj_offrs,capt_lt_offrs,brig_and_above_mns_offrs,col_mns_offrs,lt_col_s_mns_offrs,lt_col_ts_mns_offrs,maj_mns_offrs,\r\n"
							+ "															 capt_lt_mns_offrs,sub_major_jco,sub_jco,nb_sub_jco,warrant_officer_jco,hav_or,nk_or,lnk_sep_or,rects_or,remarks,\r\n"
							+ "							       	                     	sus_no,month,year,version,approved_by,approved_date \r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.arms_services,ds.brig_and_above_offrs,ds.col_offrs,\r\n"
							+ "								             ds.col_ts_offrs,ds.lt_col_offrs,ds.maj_offrs,\r\n"
							+ "											 ds.capt_lt_offrs,ds.brig_and_above_mns_offrs,ds.col_mns_offrs,	ds.lt_col_s_mns_offrs,\r\n"
							+ "		ds.lt_col_ts_mns_offrs,\r\n"
							+ "								             ds.maj_mns_offrs,ds.capt_lt_mns_offrs,ds.sub_major_jco,ds.sub_jco,\r\n"
							+ "								             ds.nb_sub_jco,ds.warrant_officer_jco,ds.hav_or,ds.nk_or,ds.lnk_sep_or,rects_or,ds.remarks,  \r\n"
							+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		                         FROM tb_psg_posted_offrs_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ')\r\n"
							+ "	         		AS t( arms_services character varying,brig_and_above_offrs integer,col_offrs integer,col_ts_offrs integer,lt_col_offrs integer,maj_offrs integer,\r\n"
							+ "											 capt_lt_offrs integer,brig_and_above_mns_offrs integer,col_mns_offrs integer,lt_col_s_mns_offrs integer,lt_col_ts_mns_offrs integer,maj_mns_offrs integer,\r\n"
							+ "											 capt_lt_mns_offrs integer,sub_major_jco integer,sub_jco integer,nb_sub_jco integer,warrant_officer_jco integer,hav_or integer,nk_or integer,\r\n"
							+ "											 lnk_sep_or integer,rects_or integer,remarks character varying,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_postedciv_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_posted_civ_3009 (gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date) \r\n"
							+ "	         		SELECT gp_a_gazetted,gp_b_gazetted,gp_b_non_gazetted,gp_c_non_gazetted,remarks,\r\n"
							+ "	         		sus_no,month,year,version,approved_by,approved_date\r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.gp_a_gazetted,ds.gp_b_gazetted,ds.gp_b_non_gazetted,\r\n"
							+ "								ds.gp_c_non_gazetted,ds.remarks,  \r\n"
							+ "	         		ds.sus_no,v.month,v.year,\r\n"
							+ "	         		(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		FROM tb_psg_posted_civ_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		v.year = md.year and v.sus_no = md.sus_no where v.status != 3')\r\n"
							+ "	         		AS t(gp_a_gazetted integer,gp_b_gazetted integer,gp_b_non_gazetted integer,\r\n"
							+ "						 gp_c_non_gazetted integer,remarks character varying,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_rank_trad_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_rank_and_trade_wise_jco_or_3009 (arms_services,trade,sub_maj_jco,sub_jco,nb_sub_jco,warrant_officer_jco, hav_or,nk_or,lnk_sep_or,\r\n"
							+ "													rects_or,total,sus_no,month,year,version,approved_by,approved_date)\r\n"
							+ "	         		          SELECT arms_services,trade,sub_maj_jco,sub_jco,nb_sub_jco,warrant_officer_jco, hav_or,nk_or,lnk_sep_or,rects_or,total,												\r\n"
							+ "	         	                  	sus_no,month,year,version,approved_by,approved_date\r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.arms_services,ds.trade,ds.sub_maj_jco,ds.sub_jco,ds.nb_sub_jco,ds.warrant_officer_jco,\r\n"
							+ "								ds.hav_or,ds.nk_or,ds.lnk_sep_or,ds.rects_or,ds.total,\r\n"
							+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		                         FROM tb_psg_rank_and_trade_wise_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3')\r\n"
							+ "	         		AS t( arms_services character varying,trade character varying,sub_maj_jco integer,sub_jco integer,nb_sub_jco integer,\r\n"
							+ "						  warrant_officer_jco integer, hav_or integer,nk_or integer,lnk_sep_or integer,	rects_or integer,total integer,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_rel_denomination_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_religious_denomination_3009 (arms_services,religion,jcos_posted_str_incl_ere_pers,or_posted_str_incl_ere_pers,held_religious_teacher,auth_religious_teacher\r\n"
							+ "												,sus_no,month,year,version,approved_by,approved_date)\r\n"
							+ "	         		          SELECT arms_services,religion,jcos_posted_str_incl_ere_pers,or_posted_str_incl_ere_pers,held_religious_teacher,auth_religious_teacher,\r\n"
							+ "	         	                  	sus_no,month,year,version,approved_by,approved_date\r\n"
							+ "	         		FROM dblink('myconn','SELECT distinct ds.arms_services,ds.religion,ds.jcos_posted_str_incl_ere_pers,ds.or_posted_str_incl_ere_pers,\r\n"
							+ "								ds.held_religious_teacher,ds.auth_religious_teacher,\r\n"
							+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
							+ "	         		                         FROM tb_psg_religious_denomination_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
							+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
							+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
							+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3')\r\n"
							+ "	         		AS t( arms_services character varying,religion character varying,jcos_posted_str_incl_ere_pers integer,or_posted_str_incl_ere_pers integer,held_religious_teacher integer,\r\n"
							+ "						  auth_religious_teacher integer,sus_no character varying,month character varying,year character varying, \r\n"
							+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3009_summary_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_summary_3009 (\r\n"
					+ "offrs_auth,offrs_posted,offrs_sur,offrs_defi,offrs_authover_above,offrs_authno,\r\n"
					+ "offrs_mns_auth,offrs_mns_posted,offrs_mns_sur,offrs_mns_defi,offrs_mns_authover_above,offrs_mns_authno,\r\n"
					+ "jco_auth,jco_posted,jco_sur,jco_defi,jco_authover_above,jco_authno,\r\n"
					+ " or_auth,or_posted,or_sur,or_defi,or_authover_above,or_authno,\r\n"
					+ " civ_auth,civ_posted,civ_sur,civ_defi,civ_authover_above,civ_authno,\r\n"
					+ " sus_no, month,year,version,approved_by,approved_date)\r\n"
					+ "													 SELECT \r\n"
					+ "													 offrs_auth,offrs_posted,offrs_sur,offrs_defi,offrs_authover_above,offrs_authno,jco_authover_above,jco_authno,civ_authover_above,civ_authno,or_authover_above,or_authno,\r\n"
					+ "offrs_mns_auth,offrs_mns_posted,offrs_mns_sur,offrs_mns_defi,offrs_mns_authover_above,offrs_mns_authno,\r\n"
					+ "													 jco_auth,jco_posted,jco_sur,jco_defi,\r\n"
					+ "													 or_auth,or_posted,or_sur,or_defi,\r\n"
					+ "													 civ_auth,civ_posted,civ_sur,civ_defi,\r\n"
					+ "													 sus_no,month,year,version,approved_by,approved_date\r\n"
					+ "														FROM dblink('myconn',' SELECT distinct\r\n"
					+ "														ds.offrs_auth,\r\n"
					+ "	ds.offrs_posted,\r\n"
					+ "	ds.offrs_sur,\r\n"
					+ "	ds.offrs_defi,\r\n"
					+ "	ds.offrs_authover_above,\r\n"
					+ "	ds.offrs_authno,\r\n"
					+ "	ds.offrs_mns_auth,\r\n"
					+ "	ds.offrs_mns_posted,\r\n"
					+ "	ds.offrs_mns_sur,\r\n"
					+ "	ds.offrs_mns_defi,\r\n"
					+ "	ds.offrs_mns_authover_above,\r\n"
					+ "	ds.offrs_mns_authno,\r\n"
					+ "	ds.jco_auth,\r\n"
					+ "	ds.jco_posted,\r\n"
					+ "	ds.jco_sur,\r\n"
					+ "	ds.jco_defi,\r\n"
					+ "	ds.jco_authover_above,\r\n"
					+ "	ds.jco_authno,\r\n"
					+ "	ds.or_auth,\r\n"
					+ "	ds.or_posted,\r\n"
					+ "	ds.or_sur,\r\n"
					+ "	ds.or_defi,\r\n"
					+ "	ds.or_authover_above,\r\n"
					+ "	ds.or_authno,\r\n"
					+ "	ds.civ_auth,\r\n"
					+ "	ds.civ_posted,\r\n"
					+ "	ds.civ_sur,\r\n"
					+ "	ds.civ_defi,\r\n"
					+ "	ds.civ_authover_above,\r\n"
					+ "	ds.civ_authno,\r\n"
					+ "	ds.sus_no,\r\n"
					+ "	v.month,\r\n"
					+ "	v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
					+ "															         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
					+ "															         		                         FROM tb_psg_summary_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
					+ "															         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
					+ "															         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
					+ "															         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3')\r\n"
					+ "															         		AS t( offrs_auth character varying,offrs_posted character varying,offrs_sur character varying, offrs_defi character varying,offrs_authover_above character varying,offrs_authno character varying,\r\n"
					+ "																					 offrs_mns_auth character varying,offrs_mns_posted character varying,offrs_mns_sur character varying, offrs_mns_defi character varying,offrs_mns_authover_above character varying,offrs_mns_authno character varying,\r\n"
					+ "							                            jco_auth character varying,jco_posted character varying, jco_sur character varying, jco_defi character varying,jco_authover_above character varying,jco_authno character varying,\r\n"
					+ "																					or_auth character varying, or_posted character varying,or_sur character varying,or_defi character varying,or_authover_above character varying,or_authno character varying,\r\n"
					+ "																					civ_auth character varying,civ_posted character varying, civ_sur character varying,civ_defi character varying,civ_authover_above character varying,civ_authno character varying,\r\n"
					+ "																																		sus_no character varying,month character varying,year character varying, \r\n"
					+ "															         		      version character varying,approved_by character varying,approved_date timestamp without time zone)\r\n");
					

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	// ---------end civilian---------//
	// @Scheduled(cron = "00 44 11 01-31 * ?")
	public void iaff3009_authoffrs_olap_new() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));
			}
			String[] colName = { "offrs", "mns_offrs", "jcos", "ors" };
			for (int j = 0; j < colName.length; j++) {

				String a = "ds." + colName[j];
				String b = colName[j];

				PreparedStatement pstmt = c.prepareStatement("insert into tb_psg_auth_str_" + b + "_3009"
						+ "(arm_services," + b + ",remarks,\r\n"
						+ "	         		sus_no,month,year,version,approved_by,approved_date) \r\n"
						+ "	         		SELECT arm_services," + b + ",remarks,\r\n"
						+ "	         		sus_no,month,year,version,approved_by,approved_date\r\n"
						+ "	         		FROM dblink('myconn','SELECT distinct ds.arm_services," + a
						+ ",ds.remarks,  \r\n" + "	         		ds.sus_no,v.month,v.year,\r\n"
						+ "	         		(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
						+ "	         			else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
						+ "	         		FROM tb_psg_auth_str_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
						+ "	         		and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
						+ "	         		inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
						+ "	         		v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ')\r\n"
						+ "	         		AS t(arm_services character varying," + b + " integer,\r\n"
						+ "					 remarks character varying,sus_no character varying,month character varying,year character varying, \r\n"
						+ "	         		 version character varying,approved_by character varying,approved_date timestamp without time zone)");

				int i = pstmt.executeUpdate();

			}
			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

//		@Scheduled(cron = "00 24 14 01-31 * ?")
	public void iaff3009_postedoffrs_olap_new() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			String[] colName = { "brig_and_above_offrs", "col_offrs", "col_ts_offrs", "lt_col_offrs", "maj_offrs",
					"capt_lt_offrs", "brig_and_above_mns_offrs", "col_mns_offrs", "lt_col_mns_offrs", "maj_mns_offrs",
					"capt_lt_mns_offrs", "sub_major_jco", "sub_jco", "nb_sub_jco", "warrant_officer_jco", "hav_or",
					"nk_or", "lnk_sep_or", "rects_or" };
			for (int j = 0; j < colName.length; j++) {

				String a = "ds." + colName[j];
				String b = colName[j];

				PreparedStatement pstmt = c.prepareStatement("INSERT INTO TB_PSG_POSTED_" + b
						+ "_3009 (ARMS_SERVICES,\r\n" + "\r\n" + b + ",\r\n"
						+ "														REMARKS,\r\n"
						+ "														SUS_NO,\r\n"
						+ "														MONTH,\r\n"
						+ "														YEAR,\r\n"
						+ "														VERSION,\r\n"
						+ "														APPROVED_BY,\r\n"
						+ "														APPROVED_DATE)\r\n"
						+ "SELECT ARMS_SERVICES,\r\n" + b + ",\r\n" + "	REMARKS,\r\n" + "	SUS_NO,\r\n"
						+ "	MONTH,\r\n" + "	YEAR,\r\n" + "	VERSION,\r\n" + "	APPROVED_BY,\r\n"
						+ "	APPROVED_DATE\r\n" + "FROM DBLINK('myconn',\r\n" + "\r\n"
						+ "						'SELECT distinct ds.arms_services," + a + ",ds.remarks,  \r\n"
						+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
						+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
						+ "	         		                         FROM tb_psg_posted_offrs_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
						+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
						+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
						+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ') AS T(ARMS_SERVICES CHARACTER varying,"
						+ b
						+ " integer,REMARKS CHARACTER varying,SUS_NO CHARACTER varying,MONTH CHARACTER varying,YEAR CHARACTER varying, VERSION CHARACTER varying,APPROVED_BY CHARACTER varying,APPROVED_DATE timestamp WITHOUT TIME ZONE)");

//					guru
				int i = pstmt.executeUpdate();

			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

//		@Scheduled(cron = "00 20 17 01-31 * ?")
	public void iaff3009_rank_trad_olap_new() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}
			String[] colName = { "sub_maj_jco", "sub_jco", "nb_sub_jco", "warrant_officer_jco", "hav_or", "nk_or",
					"lnk_sep_or", "rects_or" };
			for (int j = 0; j < colName.length; j++) {

				String a = "ds." + colName[j];
				String b = colName[j];

				PreparedStatement pstmt = c.prepareStatement("INSERT INTO TB_PSG_RANK_AND_TRADE_WISE_" + b
						+ "_3009 (ARMS_SERVICES,\r\n" + "\r\n"
						+ "														TRADE,\r\n" + b + ",\r\n"

						+ "														SUS_NO,\r\n"
						+ "														MONTH,\r\n"
						+ "														YEAR,\r\n"
						+ "														VERSION,\r\n"
						+ "														APPROVED_BY,\r\n"
						+ "														APPROVED_DATE)\r\n"
						+ "SELECT ARMS_SERVICES,\r\n" + "	TRADE,\r\n" + b + ",\r\n" + "	SUS_NO,\r\n"
						+ "	MONTH,\r\n" + "	YEAR,\r\n" + "	VERSION,\r\n" + "	APPROVED_BY,\r\n"
						+ "	APPROVED_DATE\r\n" + "FROM DBLINK('myconn',\r\n" + "\r\n"
						+ "						'SELECT distinct ds.arms_services,ds.trade," + a + ",\r\n"
						+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
						+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
						+ "	         		                         FROM tb_psg_rank_and_trade_wise_jco_or_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
						+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
						+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
						+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3') AS T(ARMS_SERVICES CHARACTER varying,TRADE CHARACTER varying,"
						+ b
						+ " integer,SUS_NO CHARACTER varying,MONTH CHARACTER varying,YEAR CHARACTER varying, VERSION CHARACTER varying,APPROVED_BY CHARACTER varying,APPROVED_DATE timestamp WITHOUT TIME ZONE)");

				int i = pstmt.executeUpdate();

			}

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	// @Scheduled(cron = "00 18 11 01-31 * ?")
	public void iaff3009_rel_denomination_olap_new() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			String[] colName = { "jcos_posted_str_incl_ere_pers", "or_posted_str_incl_ere_pers" };
			for (int j = 0; j < colName.length; j++) {

				String a = "ds." + colName[j];
				String b = colName[j];

				PreparedStatement pstmt = c.prepareStatement("INSERT INTO tb_psg_religious_denom_" + b
						+ "_3009 (ARMS_SERVICES,\r\n" + "\r\n"
						+ "														RELIGION,\r\n" + b + ",\r\n"

						+ "														HELD_RELIGIOUS_TEACHER,\r\n"
						+ "														AUTH_RELIGIOUS_TEACHER ,\r\n"
						+ "														SUS_NO,\r\n"
						+ "														MONTH,\r\n"
						+ "														YEAR,\r\n"
						+ "														VERSION,\r\n"
						+ "														APPROVED_BY,\r\n"
						+ "														APPROVED_DATE)\r\n"
						+ "SELECT ARMS_SERVICES,\r\n" + "	RELIGION,\r\n" + b + ",\r\n"

						+ "	HELD_RELIGIOUS_TEACHER,\r\n" + "	AUTH_RELIGIOUS_TEACHER,\r\n" + "	SUS_NO,\r\n"
						+ "	MONTH,\r\n" + "	YEAR,\r\n" + "	VERSION,\r\n" + "	APPROVED_BY,\r\n"
						+ "	APPROVED_DATE\r\n" + "FROM DBLINK('myconn',\r\n" + "\r\n"
						+ "						'SELECT distinct ds.arms_services,ds.religion," + a + ",\r\n"
						+ "								ds.held_religious_teacher,ds.auth_religious_teacher,\r\n"
						+ "	         		                         ds.sus_no,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
						+ "	         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
						+ "	         		                         FROM tb_psg_religious_denomination_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
						+ "	         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
						+ "	         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
						+ "	         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3') AS T(ARMS_SERVICES CHARACTER varying,RELIGION CHARACTER varying,"
						+ b
						+ " integer,HELD_RELIGIOUS_TEACHER integer, AUTH_RELIGIOUS_TEACHER integer,SUS_NO CHARACTER varying,MONTH CHARACTER varying,YEAR CHARACTER varying, VERSION CHARACTER varying,APPROVED_BY CHARACTER varying,APPROVED_DATE timestamp WITHOUT TIME ZONE)");

				int i = pstmt.executeUpdate();

			}
			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	// @Scheduled(cron = "00 18 12 01-31 * ?")
	public void iaff3009_summary_olap_new() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			String[] colName = { "offrs_auth", "offrs_posted", "offrs_sur", "offrs_defi", "jco_auth", "jco_posted",
					"jco_sur", "jco_defi", "or_auth", "or_posted", "or_sur", "or_defi", "civ_auth", "civ_posted",
					"civ_sur", "civ_defi" };
			for (int j = 0; j < colName.length; j++) {

				String a = "ds." + colName[j];
				String b = colName[j];

				PreparedStatement pstmt = c.prepareStatement("INSERT INTO TB_PSG_SUMM_" + b + "_3009 (" + b + ",\r\n"

						+ "														SUS_NO,\r\n"
						+ "														MONTH,\r\n"
						+ "														YEAR,\r\n"
						+ "														VERSION,\r\n"
						+ "														APPROVED_BY,\r\n"
						+ "														APPROVED_DATE)\r\n" + "SELECT " + b
						+ ",\r\n" + "	SUS_NO,\r\n" + "	MONTH,\r\n" + "	YEAR,\r\n" + "	VERSION,\r\n"
						+ "	APPROVED_BY,\r\n" + "	APPROVED_DATE\r\n" + "FROM DBLINK('myconn',\r\n" + "\r\n"
						+ "						' SELECT distinct " + a + ",ds.sus_no,v.month,\r\n"
						+ "																 v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
						+ "								         			                     else cast(v.version as int)  END) as version,ds.approved_by,ds.approved_date\r\n"
						+ "								         		                         FROM tb_psg_summary_3009 ds inner join tb_psg_iaff_3009_main v on ds.version = v.version \r\n"
						+ "								         		                         and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no \r\n"
						+ "								         		                         inner join tb_psg_iaff_3009_main_details md on  v.month = md.month and  \r\n"
						+ "								         		                         v.year = md.year and v.sus_no = md.sus_no where v.status != 3') AS T("
						+ b
						+ " CHARACTER varying, SUS_NO CHARACTER varying,MONTH CHARACTER varying,YEAR CHARACTER varying, VERSION CHARACTER varying,APPROVED_BY CHARACTER varying,APPROVED_DATE timestamp WITHOUT TIME ZONE)");

				int i = pstmt.executeUpdate();

			}
			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

//	for 3008 mns pranay 13.06.24

	public void iaff3008_Serving_olap_mns() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_serving_mns (personal_no,date_of_tos,\r\n"
							+ "tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,\r\n"
							+ "regiment,date_of_birth,date_of_commission,date_of_seniority,unit_name,created_date,created_by,\r\n"
							+ "date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks\r\n"
							+ "FROM dblink('myconn','SELECT distinct s.personal_no,s.date_of_tos,s.tenure_date,\r\n"
							+ "s.sus_no,s.rank,s.appointment,s.cda_acc_no,s.parent_arm,s.regiment,\r\n"
							+ "s.date_of_birth,s.date_of_commission,s.date_of_seniority,s.unit_name,s.created_date,s.created_by,\r\n"
							+ "s.date_of_appointment,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version,s.med_cat,s.name,s.date_of_rank,s.tnai_no,s.marital_status,s.date_of_marriage,s.remarks \r\n"
							+ "FROM tb_psg_iaff_3008_serving_mns s \r\n"
							+ "inner join tb_psg_iaff_3008_main_mns v on s.version = v.version and v.month = s.month and \r\n"
							+ "v.year = s.year and v.sus_no = s.sus_no \r\n"
							+ "inner join tb_psg_iaff_3008_main_details_mns md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3 ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone,\r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ "version character varying,med_cat character varying,name character varying,date_of_rank TIMESTAMP WITHOUT TIME ZONE,\r\n" 
							+ "tnai_no character varying,\r\n" 
							+ " marital_status character varying,\r\n" 
							+ " date_of_marriage character varying,\r\n" 
							+ " remarks character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3008_SuperNumarary_olap_mns() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c
					.prepareStatement("insert into tb_psg_iaff_3008_supernumerary_mns (personal_no,date_of_tos,\r\n"
							+ "tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm, \r\n"
							+ "regiment,date_of_birth,date_of_commission,date_of_seniority,unit_name,created_date,created_by,\r\n"
							+ "date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks  \r\n"
							+ "FROM dblink('myconn','SELECT distinct sp.personal_no,\r\n"
							+ "sp.date_of_tos,sp.tenure_date,sp.sus_no,sp.rank,sp.appointment,sp.cda_acc_no,sp.parent_arm,sp.regiment,sp.date_of_birth,\r\n"
							+ "sp.date_of_commission,sp.date_of_seniority,sp.unit_name,sp.created_date,sp.created_by,sp.date_of_appointment,v.month,v.year,\r\n"
							+ "(CASE  when v.status=0 then (cast(v.version as int) - 1) else cast(v.version as int)  END) as version,sp.med_cat,sp.name,sp.date_of_rank,sp.tnai_no,sp.marital_status,sp.date_of_marriage,sp.remarks FROM \r\n"
							+ "tb_psg_iaff_3008_supernumerary_mns sp inner join tb_psg_iaff_3008_main_mns v on sp.version = v.version and \r\n"
							+ "v.month = sp.month and v.year = sp.year and v.sus_no = sp.sus_no "
							+ "inner join tb_psg_iaff_3008_main_details_mns md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3  ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone,\r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ " version character varying,med_cat character varying,name character varying,date_of_rank TIMESTAMP WITHOUT TIME ZONE, \r\n"  
							+ "tnai_no character varying, \r\n"  
							+ " marital_status character varying, \r\n"  
							+ " date_of_marriage character varying, \r\n" 
							+ " remarks character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}

	public void iaff3008_Deserter_olap_mns() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_iaff_3008_deserter_mns (personal_no,date_of_tos,tenure_date,\r\n"
							+ "sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,date_of_commission,\r\n"
							+ "date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks) \r\n"
							+ "SELECT personal_no,date_of_tos,tenure_date,sus_no,rank,appointment,cda_acc_no,parent_arm,regiment,date_of_birth,\r\n"
							+ "date_of_commission,date_of_seniority,unit_name,created_date,created_by,date_of_appointment,month,year,version,med_cat,name,date_of_rank,tnai_no,marital_status,date_of_marriage,remarks \r\n"
							+ "FROM dblink('myconn','SELECT distinct ds.personal_no,ds.date_of_tos,ds.tenure_date,ds.sus_no,ds.rank,ds.appointment, \r\n"
							+ "ds.cda_acc_no,ds.parent_arm,ds.regiment,ds.date_of_birth,ds.date_of_commission,ds.date_of_seniority,ds.unit_name,\r\n"
							+ "ds.created_date,ds.created_by,ds.date_of_appointment,v.month,v.year,\r\n"
							+ "(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version,ds.med_cat,ds.name,ds.date_of_rank,ds.tnai_no,ds.marital_status,ds.date_of_marriage,ds.remarks \r\n"
							+ "FROM tb_psg_iaff_3008_deserter_mns ds inner join tb_psg_iaff_3008_main_mns v on ds.version = v.version \r\n"
							+ "and v.month = ds.month and v.year = ds.year and v.sus_no = ds.sus_no "
							+ "inner join tb_psg_iaff_3008_main_details_mns md on  v.month = md.month and \r\n"
							+ "v.year = md.year and v.sus_no = md.sus_no where v.status != 3  ')\r\n"
							+ "AS t(personal_no character varying,date_of_tos timestamp without time zone,tenure_date timestamp without time zone, \r\n"
							+ "sus_no character varying,rank character varying,appointment character varying,cda_acc_no character varying,\r\n"
							+ "parent_arm character varying,regiment character varying,date_of_birth timestamp without time zone,\r\n"
							+ "date_of_commission timestamp without time zone,date_of_seniority timestamp without time zone,\r\n"
							+ "unit_name character varying,created_date timestamp without time zone,created_by character varying,\r\n"
							+ "date_of_appointment timestamp without time zone,month character varying,year character varying,\r\n"
							+ " version character varying,med_cat character varying,name character varying,date_of_rank TIMESTAMP WITHOUT TIME ZONE,\r\n"  
							+ "tnai_no character varying,\r\n" 
							+ " marital_status character varying,\r\n"
							+ " date_of_marriage character varying,\r\n" 
							+ " remarks character varying)");

			int i = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public void iaff3008_MainDetails_mns_olap() {
		Connection c = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/miso_psg_olap", "postgres", "postgres");
			c.setAutoCommit(false);
			PreparedStatement stmt = c.prepareStatement("SELECT dblink_connect('myconn','miso_psg_oltp1')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("dblink_connect"));

			}

			PreparedStatement pstmt = c.prepareStatement(
					"insert into tb_psg_iaff_3008_main_details_mns (sus_no,auth,held,present_return_no,\r\n"
							+ "		  present_return_dt,we_pe_no,cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version) \r\n"
							+ "   SELECT sus_no,auth,held,present_return_no,present_return_dt,we_pe_no,\r\n"
							+ "		  cmd_sus,cmd_unit,corp_sus,corp_unit,div_sus,div_unit,bde_sus,bde_unit,\r\n"
							+ "		  observation,created_date,created_by,month,year,version\r\n"
							+ "    FROM dblink('myconn','SELECT distinct md.sus_no,md.auth,md.held,md.present_return_no,md.present_return_dt,\r\n"
							+ "	md.we_pe_no,md.cmd_sus,md.cmd_unit,md.corp_sus,md.corp_unit,md.div_sus,md.div_unit,md.bde_sus,md.bde_unit,\r\n"
							+ "	md.observation,md.created_date,md.created_by,v.month,v.year,(CASE  when v.status=0 then (cast(v.version as int) - 1)\r\n"
							+ "	else cast(v.version as int)  END) as version FROM tb_psg_iaff_3008_main_details_mns md \r\n"
							+ "	inner join tb_psg_iaff_3008_main_mns v on md.version = v.version and v.month = md.month and \r\n"
							+ "	v.year = md.year and v.sus_no = md.sus_no  where v.status != 3 ') \r\n"
							+ "	AS t(sus_no character varying,auth character varying,held character varying,\r\n"
							+ "		 present_return_no character varying,present_return_dt timestamp without time zone,\r\n"
							+ "	we_pe_no character varying,cmd_sus character varying,cmd_unit character varying,\r\n"
							+ "	corp_sus character varying,corp_unit character varying,div_sus character varying,div_unit character varying,\r\n"
							+ "	bde_sus character varying,bde_unit character varying,observation character varying,	 \r\n"
							+ "	created_date timestamp without time zone,created_by character varying,\r\n"
							+ "	month character varying,year character varying,version character varying)");

			int a = pstmt.executeUpdate();

			c.commit();
			c.close();
		} catch (Exception e) {
			System.exit(0);
		}

	}
	
	

}
