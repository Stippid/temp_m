package com.dao.orbat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Create_Unit_moveDAOImpl implements Create_Unit_moveDAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<String> getAllBodyDetailsList(String sus_no) {
    	ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		try{
    		conn = dataSource.getConnection();
    		String sql= "select r.auth_let_no, r.id, " +
	    				" (select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name," +
	    				" r.imdt_fmn," +
	    				" r.indn_de_indn_pd," +
	    				" r.arm_name," +
	    				" r.mode_of_tpt," +
	    				" r.nmb_date," +
	    				" r.loc," +
	    				" n.code_value as nrs," +
	    				" t.label as typ_of_stn," +
	    				" l.mod_desc as typ_of_terrain," +
	    				" r.mov_of_adv_party_dt," +
	    				" r.rplc_by_unit_sus_no," +
	    				" (select unit_name from tb_miso_orbat_unt_dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active')  as rplc_by_unit_name," +
	    				" (case when r.relief_yes_no='1' then 'Yes' else 'No' end) as relief_yes_no,"+
	    				" r.remarks," +
	    				" r.type_of_cl"+
	    				" from tb_miso_orbat_relief_prgm r" +
    					" inner join tb_miso_orbat_code l on l.code_type='Location' and r.loc = l.code" +
    					" left join tb_miso_orbat_code n on n.code_type='Location' and l.nrs_code = n.code" +
    					" left join t_domain_value t on t.domainid='TYPEOFLOCATION' and l.type_loc = t.codevalue" + 
						" where r.sd_status = '1' and r.unit_status is null and r.sus_no= ? limit 1";
    		 
    		// (case when r.relief_yes_no='1' then 'Yes' else 'No' end) as 
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {	    			
    			list.add(rs1.getString("id"));
    			list.add(rs1.getString("unit_name"));
    			list.add(rs1.getString("imdt_fmn"));
    			list.add(rs1.getString("indn_de_indn_pd"));
    			list.add(rs1.getString("arm_name"));
    			list.add(rs1.getString("mode_of_tpt"));
    			list.add(rs1.getString("nmb_date"));
    			list.add(rs1.getString("loc"));
    			list.add(rs1.getString("nrs"));
    			list.add(rs1.getString("typ_of_stn"));
    			list.add(rs1.getString("typ_of_terrain"));
    			list.add(rs1.getString("mov_of_adv_party_dt"));
    			list.add(rs1.getString("rplc_by_unit_sus_no"));
    			list.add(rs1.getString("rplc_by_unit_name"));
    			list.add(rs1.getString("auth_let_no"));
    			list.add(rs1.getString("relief_yes_no"));
    			list.add(rs1.getString("remarks"));
    			list.add(rs1.getString("type_of_cl"));   
    			
    			//alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return list;
    }
	
	public ArrayList<ArrayList<String>> getInstrList(String nPara){
    	ArrayList<String> list2 = new ArrayList<String>();		
    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	String eqptInst1="3(a) GUNS COMMAN VEHS COMMA EQPT AND CONT STORES LESS PERS ARMS TO BE HANDED / TAKEN OVER BY RELIEVING REGT IN ACCORDANCE WITH DGD / CD 2&3 LETTER NO 76476/HANDING TAKING/05/GS/WE 2&3 OF 17(17) SEP 2019. COMMAN LETTER NO 76476/HANDING TAKING/05/GS/CD-2&3 DT 17 (17) DEC 2020 AND EVEN LETTER NO DT 25 (25) MAY 2021 ADDESD COMDS ONLY(.)";
    	String eqptInst2="3(b) MORS/VEHS/EQPT AND CONT STORES LESS PERS ARMS TO BE CARRIED BY RELIEVING REGT IN ACCORDANCE WITH THIS HQ LETTER NO 76476/HANDING TAKING/05/GS/WE 2&3 OF 17 SEP 19 ADDSD COMDS ONLY(.)";
    	String eqptInst3="3(c) VEHS OBLIQUE EQPT AND CONT STORES LESS PERS ARMS TO BE  HANDED/TAKEN OVER BY RELIEVING REGT IN ACCORDANCE WITH THIS HQ LETTER NO 76476/HNADING TAKING/05/GS/WE/ 2&3 OF 17 SEP 19 ADDSD COMDS ONLY (.)";
    	String vehInst1="#MOV BY RAIL. 4(a) MODE OF TPT (.) RAIL (.) REF THIS HQ LETTER NO 20053/ADG MOV/POLICY/RAIL/P-1 DT 12 (12) MAR 2012 (.) UNITS OT SUBMIT ROLLING STK DEMAND DIRECT TO INARMY GS MOV (RAIL) THIS HQ  IN ACCORDANCE WITH SAO 7/S/84 AND LETTER NO 24740/ SD 4(b) DT 02 (02) AUG 1984 AS AMENDED ADDSD TO COMDS ONLY (.) FORMAT ALSO ATT AT APPX B TO AO 14/2016/GS (.)";
    	String vehInst2="#MOV BY RD. 4(e) MODE OF TPT (.) Mov by Rd(.) REQMT OF CHT FOR RD MOV BE FWD TO CONCERNED COMD HQ (.) RD MOVE TO BE COORD BY CONCENRED COMD HQ IN CONSULTATION WITH GS MOV (BUDGET) THIS HQ (.) INSTR UNITS NOT RPT NOT TO CORRES WITH THIS HQ COMMA SD DTE DIRECTLY FOR RD / RAIL MOV (.)";
    	if (nPara.equalsIgnoreCase("E") || nPara.equalsIgnoreCase("A")) {
    		list2.add("3a");
    		list2.add(eqptInst1);
    		li.add(0,list2);
    		list2.add("3b");
    		list2.add(eqptInst2);
    		li.add(1,list2);
    		list2.add("3c");
    		list2.add(eqptInst3);
    		li.add(2,list2);
    	}
    	if (nPara.equalsIgnoreCase("V") || nPara.equalsIgnoreCase("A")) {
    		list2.add("4a");
    		list2.add(vehInst1);
    		li.add(list2);
    		list2.add("4e");
    		list2.add(vehInst2);
    		li.add(list2);
    	}
    
		return li;
    }

	public ArrayList<ArrayList<String>> getSearchNReliefReportList(String auth_letter,String status){
    	ArrayList<String> list = new ArrayList<String>();
    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{
    		conn = dataSource.getConnection();    		
    		String qq;/*="select distinct r.id, r.auth_let_no, r.sus_no,o1.unit_name as unit_name, o1.form_code_control as imdt_fmn, o4.unit_name as imdt_fmn_name,";
	    	qq += " o1.arm_code as arm_name, r.mode_of_tpt, r.nmb_date, r.indn_de_indn_pd, r.loc as loc, o2.code_value as nrs,";
	    	qq += " r.typ_of_stn, r.typ_of_terrain, r.mov_of_adv_party_dt, r.rplc_by_unit_sus_no, o3.unit_name as rplc_by_unit_name,";
	    	qq += " r1.imdt_fmn as rplc_by_unit_fmn,o5.unit_name as rplc_fmn_name,r1.loc as rplc_loc,r1.code_value as rplc_nrs,";
	    	qq += " r.sd_status,r.period_from,r.period_to,o6.arm_desc,oall.cmd_name,oall.coprs_name,oall.div_name,oall.bde_name from tb_miso_orbat_relief_prgm r";
	    	qq += " left join tb_miso_orbat_unt_dtl o1 on o1.sus_no = r.sus_no and o1.status_sus_no = 'Active'"; 
	    	qq += " left join tb_miso_orbat_code o2 on o2.code=r.loc and o2.code_type='Location'";
	    	qq += " left join tb_miso_orbat_unt_dtl o3 on o3.sus_no = r.rplc_by_unit_sus_no and o3.status_sus_no = 'Active'"; 
	    	qq += " left join (select r1.sus_no,r1.imdt_fmn,r1.loc,l1.code_value from tb_miso_orbat_relief_prgm r1"; 
	    	qq += " left join tb_miso_orbat_unt_dtl o1 on r1.imdt_fmn=o1.form_code_control"; 
	    	qq += " left join tb_miso_orbat_code l1 on l1.code=r1.loc where l1.code_type='Location' ";
	    	qq += " and o1.sus_no in (select sus_no from tb_miso_orbat_codesform ";
	    	qq += " where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade'))) r1 on r.rplc_by_unit_sus_no=r1.sus_no";
	    	qq += " left join nrv_hq o4 on o4.form_code_control = o1.form_code_control";
	    	qq += " left join nrv_hq o5 on o5.form_code_control = o3.form_code_control";
	    	qq += " left join tb_miso_orbat_arm_code o6 on o6.arm_code = concat(left(o1.arm_code,2),'00')";
	    	qq += " left join orbat_all_details_view oall on oall.sus_no=r1.sus_no and oall.status_sus_no='Active'";
	    	qq += " where r.auth_let_no='"+auth_letter+"' and r.sd_status='1'";*/
	    	
	    	
	    	/*qq="select distinct r.id, r.auth_let_no, r.sus_no,o1.unit_name as unit_name, o1.form_code_control as imdt_fmn,";
	    	qq += " o4.unit_name as imdt_fmn_name, o1.arm_code as arm_name, r.mode_of_tpt, r.nmb_date,"; 
	    	qq += " r.indn_de_indn_pd, r1.loc as loc, o2.code_value as nrs, r.typ_of_stn, r.typ_of_terrain,";
	    	qq += " r.mov_of_adv_party_dt, r.rplc_by_unit_sus_no, o3.unit_name as rplc_by_unit_name,"; 
	    	qq += " r1.imdt_fmn as rplc_by_unit_fmn,o5.unit_name as rplc_fmn_name,r.loc as rplc_loc,";
	    	qq += " o7.code_value as rplc_nrs, r.sd_status,r.period_from,r.period_to,o6.arm_desc,oall.cmd_name,";
	    	qq += " oall.coprs_name,oall.div_name,oall.bde_name from tb_miso_orbat_relief_prgm r"; 
	    	qq += " left join tb_miso_orbat_unt_dtl o1 on o1.sus_no = r.sus_no and o1.status_sus_no = 'Active'"; 
	    	qq += " left join tb_miso_orbat_unt_dtl o3 on o3.sus_no = r.rplc_by_unit_sus_no and o3.status_sus_no = 'Active'"; 
	    	qq += " left join (select r1.sus_no,r1.imdt_fmn,r1.loc,l1.code_value from tb_miso_orbat_relief_prgm r1"; 
	    	qq += " inner join tb_miso_orbat_code l1 on l1.code=r1.loc where l1.code_type='Location' and r1.auth_let_no='"+auth_letter+"' and r1.sd_status='1') r1"; 
	    	qq += " on r.rplc_by_unit_sus_no=r1.sus_no"; 
	    	qq += " left join nrv_hq o4 on o4.form_code_control = o1.form_code_control"; 
	    	qq += " left join nrv_hq o5 on o5.form_code_control = o3.form_code_control";
	    	qq += " left join tb_miso_orbat_code o2 on o2.code=r1.loc and o2.code_type='Location'"; 
	    	qq += " left join tb_miso_orbat_code o7 on o7.code=r.loc and o7.code_type='Location'"; 
	    	qq += " left join tb_miso_orbat_arm_code o6 on o6.arm_code = concat(left(o1.arm_code,2),'00')"; 
	    	qq += " left join orbat_all_details_view oall on oall.sus_no=r1.sus_no and oall.status_sus_no='Active'"; 
	    	qq += " where r.auth_let_no='"+auth_letter+"' and r.sd_status='1'";     */
    		   
    		
    		/*qq="SELECT distinct a.id, a.auth_let_no,a.sus_no,    cur.unit_name, cur.form_code_control as imdt_fmn, "
    		+ " o4.unit_name as imdt_fmn_name,	ac1.arm_code as arm_name, COALESCE(upper(a.mode_of_tpt),' ') as mode_of_tpt,a.nmb_date::date AS nmb_date, a.indn_de_indn_pd, frm_l.code as loc, frm_l.code_value AS nrs, "   
    		+"a.typ_of_stn, a.typ_of_terrain,a.mov_of_adv_party_dt,a.rplc_by_unit_sus_no,cur1.unit_name as rplc_by_unit_name, a.imdt_fmn as rplc_by_unit_fmn, "
    		+" o5.unit_name as rplc_fmn_name,to_l.code AS rplc_loc,to_l.code_value AS rplc_nrs,a.sd_status, a.period_from,a.period_to,ac1.arm_desc, "
    		+" ab.cmd_name AS cmd_name,    ac.coprs_name AS coprs_name,"    
    		+" ad.div_name AS div_name,    ae.bde_name AS bde_name "    
    	    +" FROM tb_miso_orbat_relief_prgm a "
    	    +" JOIN tb_miso_orbat_unt_dtl cur ON  a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text "
    		+" left JOIN tb_miso_orbat_unt_dtl cur1 ON a.rplc_by_unit_sus_no::text = cur1.sus_no::text AND upper(cur1.status_sus_no::text) = 'ACTIVE'::text "
    		+" left join nrv_hq o4 on o4.form_code_control = cur.form_code_control "
    		+" left join nrv_hq o5 on o5.form_code_control = a.imdt_fmn "
    	    +" LEFT JOIN orbat_view_cmd cur_ab ON substr(cur.form_code_control::text, 1, 1) = cur_ab.cmd_code::text "
    	    +" LEFT JOIN orbat_view_corps cur_ac ON substr(cur.form_code_control::text, 2, 2) = cur_ac.corps_code::text "
    	    +" LEFT JOIN orbat_view_div cur_ad ON substr(cur.form_code_control::text, 4, 3) = cur_ad.div_code::text "
    	    +" LEFT JOIN orbat_view_bde cur_ae ON substr(cur.form_code_control::text, 7, 4) = cur_ae.bde_code::text "
    	    +" LEFT JOIN tb_miso_orbat_code frm_l ON cur.code::text = frm_l.code::text AND frm_l.code_type::text = 'Location'::text "
    	    +" LEFT JOIN tb_miso_orbat_code to_l ON a.loc::text = to_l.code::text AND to_l.code_type::text = 'Location'::text "
    	    +" LEFT JOIN orbat_view_cmd ab ON substr(a.imdt_fmn::text, 1, 1) = ab.cmd_code::text "
    	    +" LEFT JOIN orbat_view_corps ac ON substr(a.imdt_fmn::text, 2, 2) = ac.corps_code::text "
    	    +" LEFT JOIN orbat_view_div ad ON substr(a.imdt_fmn::text, 4, 3) = ad.div_code::text"
    	    +" LEFT JOIN orbat_view_bde ae ON substr(a.imdt_fmn::text, 7, 4) = ae.bde_code::text "
    		+" LEFT JOIN tb_miso_orbat_arm_code ac1 on ac1.arm_code = left(cur.arm_code,2)||'00' "	 
    	    +" WHERE a.auth_let_no='"+auth_letter+"' and a.sd_status='1' ORDER BY a.id";  */
	    	
    		
    		
    		qq="SELECT distinct a.id, a.auth_let_no,a.sus_no, cur.unit_name, fcl.imdt_fmn as imdt_fmn,  o4.unit_name as imdt_fmn_name,	ac1.arm_code as arm_name, \r\n" + 
    				"COALESCE(upper(a.mode_of_tpt),' ') as mode_of_tpt,a.nmb_date::date AS nmb_date, a.indn_de_indn_pd, \r\n" + 
    				"frm_l.code as loc, frm_l.code_value AS nrs, a.typ_of_stn, a.typ_of_terrain,a.mov_of_adv_party_dt,\r\n" + 
    				"a.rplc_by_unit_sus_no,cur1.unit_name as rplc_by_unit_name, a.imdt_fmn as rplc_by_unit_fmn,  o5.unit_name as rplc_fmn_name,\r\n" + 
    				"to_l.code AS rplc_loc,to_l.code_value AS rplc_nrs,a.sd_status, a.period_from,a.period_to,ac1.arm_desc,  \r\n" + 
    				"ab.cmd_name AS cmd_name,    ac.coprs_name AS coprs_name, ad.div_name AS div_name,    ae.bde_name AS bde_name  \r\n" + 
    				"FROM tb_miso_orbat_relief_prgm a  \r\n" + 
    				"	  JOIN tb_miso_orbat_unt_dtl cur ON  a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text  \r\n" + 
    				"left JOIN tb_miso_orbat_unt_dtl cur1 ON a.rplc_by_unit_sus_no::text = cur1.sus_no::text AND upper(cur1.status_sus_no::text) = 'ACTIVE'::text  \r\n" + 
    				"left join nrv_hq o5 on o5.form_code_control = a.imdt_fmn  \r\n" + 
    				"LEFT JOIN tb_miso_orbat_code to_l ON a.loc::text = to_l.code::text AND to_l.code_type::text = 'Location'::text  \r\n" + 
    				"LEFT JOIN orbat_view_cmd ab ON substr(a.imdt_fmn::text, 1, 1) = ab.cmd_code::text  \r\n" + 
    				"LEFT JOIN orbat_view_corps ac ON substr(a.imdt_fmn::text, 2, 2) = ac.corps_code::text  \r\n" + 
    				"LEFT JOIN orbat_view_div ad ON substr(a.imdt_fmn::text, 4, 3) = ad.div_code::text \r\n" + 
    				"LEFT JOIN orbat_view_bde ae ON substr(a.imdt_fmn::text, 7, 4) = ae.bde_code::text  \r\n" + 
    				"left join (select a.rplc_by_unit_sus_no, a.type_of_cl as tocl,a.loc,a.imdt_fmn,a.arm_name from tb_miso_orbat_relief_prgm a	\r\n" + 
    				"				where a.sd_status='1' and   a.auth_let_no='"+auth_letter+"'  ) fcl \r\n" + 
    				"				on a.sus_no=fcl.rplc_by_unit_sus_no     \r\n" + 
    				"left join nrv_hq o4 on o4.form_code_control = fcl.imdt_fmn \r\n" + 
    				"LEFT JOIN tb_miso_orbat_arm_code ac1 on ac1.arm_code = left(fcl.arm_name,2)||'00'\r\n" + 
    				"LEFT JOIN tb_miso_orbat_code frm_l ON fcl.loc::text = frm_l.code::text AND frm_l.code_type::text = 'Location'::text  \r\n" + 
    				"WHERE a.auth_let_no='"+auth_letter+"' and a.sd_status='1' ORDER BY a.id";
    		
	    	
			PreparedStatement stmt=conn.prepareStatement(qq);
			/*stmt.setString(1, sus_no);*/
			ResultSet nrs = stmt.executeQuery();     
			if (!nrs.isBeforeFirst()) {
				list.add("NIL");
				li.add(list);
			} else {
				while (nrs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(nrs.getString("id"));
					list2.add(nrs.getString("auth_let_no"));
					list2.add(nrs.getString("sus_no"));
					list2.add(nrs.getString("unit_name"));
					list2.add(nrs.getString("imdt_fmn"));					
					list2.add(nrs.getString("arm_name"));
					list2.add(nrs.getString("mode_of_tpt"));
					list2.add(nrs.getString("nmb_date"));
					list2.add(nrs.getString("indn_de_indn_pd"));
					list2.add(nrs.getString("loc"));
					list2.add(nrs.getString("nrs")); //10
					list2.add(nrs.getString("typ_of_stn"));
					list2.add(nrs.getString("typ_of_terrain"));
					list2.add(nrs.getString("mov_of_adv_party_dt"));
					list2.add(nrs.getString("rplc_by_unit_sus_no"));
					list2.add(nrs.getString("rplc_by_unit_name"));
					list2.add(nrs.getString("rplc_by_unit_fmn"));
					list2.add(nrs.getString("rplc_loc"));
					list2.add(nrs.getString("rplc_nrs"));
					list2.add(nrs.getString("sd_status")); 
					list2.add(nrs.getString("imdt_fmn_name")); //20
					list2.add(nrs.getString("rplc_fmn_name"));	
					list2.add(nrs.getString("period_from"));
					list2.add(nrs.getString("period_to"));
					list2.add(nrs.getString("arm_desc"));
					list2.add(nrs.getString("cmd_name"));
					list2.add(nrs.getString("coprs_name"));
					list2.add(nrs.getString("div_name"));
					list2.add(nrs.getString("bde_name"));
					li.add(list2);
				}
			}
		
    		nrs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return li;
    }
	
	
	public ArrayList<ArrayList<String>> getSDreliefreport(String auth_letter){
    	ArrayList<String> list = new ArrayList<String>();
    	ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{
    		conn = dataSource.getConnection();    		
    		/*String qq="select distinct a.auth_let_no,to_char(to_date(a.date1,'YYYY-MM-DD'),'DD-Mon-YYYY') as date1,a.sus_no,e.unit_name,d.unit_name as from_fmn_name,lc.code_value as from_loc, \r\n" + 
    				"  c.unit_name as to_fmn_name,  lc1.code_value as to_loc, a.rplc_by_unit_sus_no,f.unit_name as replunit,\r\n" + 
    				" ac.arm_desc,COALESCE(upper(a.mode_of_tpt),' ') as mode_of_tpt,"
    				+ " (case when a.relief_yes_no = '0' or a.relief_yes_no = null then to_char(to_date(a.nmb_date,'YYYY-MM-DD'),'DD-Mon-YYYY') \r\n" + 
    				"							else 'On Relief' end) as nmb_date,"
    				+ " a.indn_de_indn_pd,to_char(to_date(a.mov_of_adv_party_dt,'YYYY-MM-DD'),'DD-Mon-YYYY') as mov_of_adv_party_dt,a.period_from,a.period_to, \r\n" +
    				" a.remarks, a.type_of_cl,b.type_of_cl as two_of_cl, a.id, a.ser_no"+
    				" from tb_miso_orbat_relief_prgm a \r\n" + 
    				" left join tb_miso_orbat_relief_prgm b on a.rplc_by_unit_sus_no=b.sus_no and b.auth_let_no=a.auth_let_no \r\n" + 
    				" left join tb_miso_orbat_code lc on b.loc=lc.code and lc.code_type='Location' \r\n"+
    				" left join tb_miso_orbat_code lc1 on a.loc=lc1.code and lc1.code_type='Location' \r\n "+
    				" LEFT JOIN (select distinct a.sus_no,a.unit_name,a.form_code_control from tb_miso_orbat_unt_dtl a  \r\n" + 
    				" join tb_miso_orbat_codesform b on upper(b.level_in_hierarchy::text) = any (ARRAY['COMMAND'::text, 'CORPS'::text, 'DIVISION'::text, 'BRIGADE'::text])\r\n" + 
    				" and a.sus_no=b.sus_no where a.status_sus_no in ('Active','Inactive') ) c on c.form_code_control= a.imdt_fmn\r\n" + 
    				" LEFT JOIN (select distinct a.sus_no,a.unit_name,a.form_code_control from tb_miso_orbat_unt_dtl a  \r\n" + 
    				" join tb_miso_orbat_codesform b on upper(b.level_in_hierarchy::text) = any (ARRAY['COMMAND'::text, 'CORPS'::text, 'DIVISION'::text, 'BRIGADE'::text])\r\n" + 
    				"  and a.sus_no=b.sus_no where a.status_sus_no in ('Active','Inactive') ) d on d.form_code_control= b.imdt_fmn \r\n" + 
    				" left join tb_miso_orbat_unt_dtl e on e.sus_no=a.sus_no and e.status_sus_no in ('Active','Inactive')\r\n" +
    				" left join tb_miso_orbat_arm_code ac on substring(a.arm_name,1,2)||'00'=ac.arm_code \r\n"+
    				" left join tb_miso_orbat_unt_dtl f on f.sus_no=a.rplc_by_unit_sus_no and f.status_sus_no in ('Active','Inactive') "
    				+ " where a.sd_status='1' and "+auth_letter+" order by a.id";   */
    		
    		
    		String qq ="SELECT a.auth_let_no, to_char(to_date(a.date1,'YYYY-MM-DD'),'DD-Mon-YYYY') as date1,a.sus_no,  cur.unit_name,ff.unit_name as from_fmn_name,  \r\n" + 
    				"    frm_l.code_value AS from_loc,\r\n" + 
    				"	 tf.unit_name as to_fmn_name,to_l.code_value AS to_loc,\r\n" + 
    				"	a.rplc_by_unit_sus_no,cur1.unit_name as replunit,ac1.arm_desc,COALESCE(upper(a.mode_of_tpt),' ') as mode_of_tpt,\r\n" + 
    				"	(case when a.relief_yes_no = '0' or a.relief_yes_no = null then to_char(to_date(a.nmb_date,'YYYY-MM-DD'),'DD-Mon-YYYY')  \r\n" + 
    				"      else 'On Relief' end) as nmb_date,\r\n" + 
    				"	a.indn_de_indn_pd,to_char(to_date(a.mov_of_adv_party_dt,'YYYY-MM-DD'),'DD-Mon-YYYY') as mov_of_adv_party_dt,  \r\n" + 
    				"    a.remarks, fcl.tocl as fromcl,a.type_of_cl as tocl, a.id, a.period_from,a.period_to,	a.ser_no\r\n" + 
    				"    FROM tb_miso_orbat_relief_prgm a\r\n" + 
    				"    JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text AND upper(cur.status_sus_no::text) = 'ACTIVE'::text\r\n" + 
    				"	 JOIN tb_miso_orbat_unt_dtl cur1 ON a.rplc_by_unit_sus_no::text = cur1.sus_no::text AND upper(cur1.status_sus_no::text) = 'ACTIVE'::text\r\n" + 
    				"	 left join tb_miso_orbat_relief_prgm b on a.rplc_by_unit_sus_no=b.sus_no and b.auth_let_no=a.auth_let_no\r\n" +  
    				"	 left join nrv_hq tf on a.imdt_fmn=tf.form_code_control\r\n" + 
    				"    LEFT JOIN tb_miso_orbat_code to_l ON a.loc::text = to_l.code::text AND to_l.code_type::text = 'Location'::text\r\n" + 
    				"	 LEFT JOIN tb_miso_orbat_arm_code ac1 on ac1.arm_code = left(cur.arm_code,2)||'00'\r\n" + 
    				"	 LEFT JOIN tb_miso_orbat_arm_code ac2 on ac2.arm_code = cur.arm_code\r\n" +
    				" 	 left join (select a.rplc_by_unit_sus_no, a.type_of_cl as tocl,a.loc,a.imdt_fmn from tb_miso_orbat_relief_prgm a	where a.sd_status='1' and  "+auth_letter+") fcl on a.sus_no=fcl.rplc_by_unit_sus_no " +
    				"    LEFT JOIN tb_miso_orbat_code frm_l ON fcl.loc::text = frm_l.code::text AND frm_l.code_type::text = 'Location'::text\r\n" +
    				"	 left join nrv_hq ff on ff.form_code_control=fcl.imdt_fmn\r\n" +
    				"    WHERE a.sd_status='1' and "+auth_letter+" ORDER BY to_number(a.ser_no,'99'),a.id " ;    				

    		
    		
    		
			PreparedStatement stmt=conn.prepareStatement(qq);
			/*stmt.setString(1, sus_no);*/
			ResultSet rs = stmt.executeQuery();     
			if (!rs.isBeforeFirst()) {	
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list2 = new ArrayList<String>();
					//list2.add(nrs.getString("id"));
					list2.add(rs.getString("auth_let_no"));
					list2.add(rs.getString("date1"));
					list2.add(rs.getString("sus_no"));
					list2.add(rs.getString("unit_name"));					
					list2.add(rs.getString("from_fmn_name"));
					list2.add(rs.getString("from_loc"));
					list2.add(rs.getString("to_fmn_name"));
					list2.add(rs.getString("to_loc")); 
					list2.add(rs.getString("rplc_by_unit_sus_no"));
					list2.add(rs.getString("replunit"));
					list2.add(rs.getString("arm_desc"));
					list2.add(rs.getString("mode_of_tpt").toUpperCase());
					list2.add(rs.getString("nmb_date"));
					list2.add(rs.getString("indn_de_indn_pd"));
					list2.add(rs.getString("mov_of_adv_party_dt"));
					list2.add(rs.getString("period_from"));
					list2.add(rs.getString("period_to"));
					list2.add(rs.getString("remarks"));
					list2.add(rs.getString("fromcl"));
					list2.add(rs.getString("tocl"));
					list2.add(rs.getString("ser_no"));
					li.add(list2);
				}
			}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return li;
    }
	

}
	
	 


