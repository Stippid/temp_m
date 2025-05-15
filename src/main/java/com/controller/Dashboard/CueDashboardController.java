package com.controller.Dashboard;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.cue.cueContoller;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.CUE_TB_MISO_MMS_WET_PET_MAST_DET;
import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.T_Domain_Value;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CueDashboardController {
	@Autowired
	CueDashboardDAO adminDash;

	public String pageType = "";
	public String pageTypeU = "";

	@Autowired
	private RoleBaseMenuDAO roledao;
	cueContoller M = new cueContoller();
	Psg_CommonController mcommon = new Psg_CommonController();
	psg_jco_CommonController jcom = new psg_jco_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	PsgDashboardController das = new PsgDashboardController();


	/////////////////////////////////// CUE- ORBAT Dashboard use
	@RequestMapping(value = "/admin/cueOrbatDashboard", method = RequestMethod.GET)
	public ModelAndView cueOrbatDashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		 String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("cueOrbatDashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		Mmap.put("roleSubAccess111", roleAccess);
		
		Mmap.put("msg", msg);
		Mmap.put("getDocTypeWiseCountWe", getDocTypeWiseCountWe()); 
		Mmap.put("getDocTypeWiseCountPe", getDocTypeWiseCountPe()); 
		Mmap.put("getDocTypeWiseCountFe", getDocTypeWiseCountFe()); 
		Mmap.put("getDocTypeWiseCountGsl", getDocTypeWiseCountGsl()); 
		Mmap.put("getDocTypeWiseCountWET", getDocTypeWiseCountWET());
		Mmap.put("getDocTypeWiseCountPET", getDocTypeWiseCountPET());
		Mmap.put("getDocTypeWiseCountFET", getDocTypeWiseCountFET());
		Mmap.put("getUEByRankCat", getUEByRankCat());
//		Mmap.put("getCueDocTypeWiseModule", getCueDocTypeWiseModule());
		Mmap.put("getActiveUnits", getActiveUnits());
		Mmap.put("getReliefPgme", getReliefPgme());
		Mmap.put("roleAccess", roleAccess(session));
		Mmap.put("getArmNameList", M.getArmNameList());
		return new ModelAndView("cueOrbatDashboardTiles");
	}

	private Object roleAccess(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getDocTypeWiseCountWe() {
		String qry = "select coun.we_pe,count(coun.we_pe) as noofwes from (select distinct d.we_pe,a.wepe_pers_no,a.sus_no,utl.unit_name,c.line_dte_name as sponsor_dire,\r\n"
				+ "a.wet_pet_no\r\n"
				+ "from (\r\n"
				+ "SELECT a.SUS_NO, a.wepe_pers_no,a.wepe_weapon_no,a.status_pers,b.wet_pet_no\r\n"
				+ "from  cue_tb_wepe_link_sus_perstransweapon a\r\n"
				+ "left join CUE_TB_MISO_WEPECONDITIONS b on a.wepe_weapon_no=b.we_pe_no and b.type='3'\r\n"
				+ ") a\r\n"
				+ "join tb_miso_orbat_unt_dtl utl on a.sus_no=utl.sus_no and utl.status_sus_no='Active'\r\n"
				+ "join CUE_TB_MISO_WEPECONDITIONS d on a.wepe_pers_no=d.we_pe_no and d.type ='1'\r\n"
				+ "left join tb_miso_orbat_line_dte c on d.sponsor_dire=c.line_dte_sus \r\n"
				+ "where a.status_pers='1' ORDER BY wepe_pers_no) coun group by 1"; 
		System.out.println("wewewewewe");
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getDocTypeWiseCountPe() {
		String qry = "SELECT  distinct d.we_pe, count(d.we_pe) as noofwes\r\n"
				+ "FROM  CUE_TB_MISO_WEPECONDITIONS d 	  					\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "  or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ " or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (\r\n"
				+ "SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1'  and d.we_pe='PE'  AND d.type IN ('1', '2', '3') \r\n"
				+ "GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getDocTypeWiseCountFe() {
		String qry = "SELECT  distinct d.we_pe, count(d.we_pe) as noofwes\r\n"
				+ "FROM  CUE_TB_MISO_WEPECONDITIONS d 	  					\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "  or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ " or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (\r\n"
				+ "SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1' and d.we_pe='FE'   AND d.type IN ('1', '2', '3') \r\n"
				+ "GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getDocTypeWiseCountGsl() {
		String qry = "SELECT  distinct d.we_pe, count(d.we_pe) as noofwes\r\n"
				+ "FROM  CUE_TB_MISO_WEPECONDITIONS d 	  					\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "  or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ " or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (\r\n"
				+ "SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1' and d.we_pe='GSL'   AND d.type IN ('1', '2', '3') \r\n"
				+ "GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public String getUEByRankCat() {
		String qry = "select app.rank_category,app.total from (SELECT \r\n"
				+ "    CASE \r\n"
				+ "        WHEN t.label IN ('Civilian Non-Gazzetted Non-Industrial CL-II', 'Civil Class I Gazetted', 'Civil Class II Gazetted',\r\n"
				+ "                        'Civilian Non-Gazzetted Non-Industrial CL-III', 'Civilian Non-Gazzetted Non-Industrial CL-IV',\r\n"
				+ "                        'Civilian Non-Gazzetted Industrial CL-II', 'Civilian Non-Gazzetted Industrial CL-III',\r\n"
				+ "                        'Civilian Non-Gazzetted Industrial CL-IV') THEN 'Civilian'\r\n"
				+ "        ELSE t.label  \r\n"
				+ "    END as rank_category,\r\n"
				+ "    SUM(s.base_auth + s.mod_auth + s.foot_auth)::int as total\r\n"
				+ "FROM  \r\n"
				+ "    sus_pers_stdauth s  \r\n"
				+ "LEFT JOIN \r\n"
				+ "    t_domain_value t ON t.domainid = 'RANKCATEGORY' AND s.rank_cat = t.codevalue\r\n"
				+ "JOIN \r\n"
				+ "    tb_miso_orbat_unt_dtl utl ON s.sus_no = utl.sus_no  AND utl.status_sus_no = 'Active' \r\n"
				+ "GROUP BY   \r\n"
				+ "    rank_category )app\r\n"
				+ "order by \r\n"
				+ "case  WHEN app.rank_category = 'OFFICER' THEN 1\r\n"
				+ "        WHEN app.rank_category = 'JCO' THEN 2\r\n"
				+ "        WHEN app.rank_category = 'OR' THEN 3\r\n"
				+ "        WHEN app.rank_category = 'JCO/OR' THEN 4\r\n"
				+ "		WHEN app.rank_category = 'Civilian' THEN 5\r\n"
				+ "end\r\n"
				+ "";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		String list1 = "";
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).get("total").equals(0)) {
				list1 += ",{rank_category: \"" + list.get(i).get("rank_category").toString() + "\", total: \""
						+ list.get(i).get("total") + "\"}";
			}
		}
		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		}
		return list1;
	}
	
	@RequestMapping(value = "/getCueDocTypeWiseModule", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getCueDocTypeWiseModule(@RequestParam Date fromDate,
			@RequestParam Date toDate) {
		List<Map<String, Object>> list = adminDash.getCueDocTypeWiseModule(fromDate, toDate);
		
		return list;
	
	}

//	public String getCueDocTypeWiseModule() {
//		
//		String qry = "SELECT DISTINCT tbl.we_pe,COALESCE(tbl.noofwes, 0::int) AS noofwes,tblp.we_pe AS we_pe_pers,COALESCE(tblp.noofwes, 0::int) AS noofwes_pers,tblt.we_pe AS we_pe_trans,COALESCE(tblt.noofwes, 0::int) AS noofwes_trans,tblw.we_pe AS we_pe_weap,COALESCE(tblw.noofwes, 0::int) AS noofwes_weap FROM \r\n"
//				+ "(SELECT DISTINCT\r\n" + "we_pe,count(we_pe) as noofwes\r\n"
//				+ "FROM cue_tb_miso_wepe_upload_condition\r\n"
//				+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_miso_wepe_upload_condition WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null\r\n"
//				+ "GROUP BY 1 ORDER BY 1) tbl\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
//				+ "we_pe,count(we_pe) as noofwes FROM cue_tb_miso_wepeconditions\r\n"
//				+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_miso_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='1'\r\n"
//				+ "GROUP BY 1 ORDER BY 1) tblp ON tbl.we_pe=tblp.we_pe\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
//				+ "we_pe,count(we_pe) as noofwes FROM cue_tb_miso_wepeconditions\r\n"
//				+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_miso_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='2'\r\n"
//				+ "GROUP BY 1 ORDER BY 1) tblt ON tbl.we_pe=tblt.we_pe\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
//				+ "we_pe,count(we_pe) as noofwes FROM cue_tb_miso_wepeconditions\r\n"
//				+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_miso_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='3'\r\n"
//				+ "GROUP BY 1 ORDER BY 1) tblw ON tbl.we_pe=tblw.we_pe\r\n" + "ORDER BY 1 DESC";
//
//		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
//
//		String list1 = "";
//		for (int i = 0; i < list.size(); i++) {
//			list1 += ",{'we_pe' : '" + list.get(i).get("we_pe") + "' ,'noofwes' : " + list.get(i).get("noofwes")
//					+ " , 'noofwes_pers' : " + list.get(i).get("noofwes_pers") + ", 'noofwes_trans' : "
//					+ list.get(i).get("noofwes_trans") + ", 'noofwes_weap' : " + list.get(i).get("noofwes_weap") + "}";
//		}
//		if (list1.length() > 0) {
//			list1 = "[" + list1.substring(1, list1.length());
//			list1 += "]";
//		}
//		return list1;
//	}

	@RequestMapping(value = "/getCueTypeYearWiseUE", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCueTypeYearWiseUE(int fromYear, int toYear, String arm) {
		List<Map<String, Object>> list = adminDash.getCueTypeYearWiseUE(fromYear, toYear, arm);
		return list;
	}

	@RequestMapping(value = "/getCueTypeMonthWiseUE", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCueTypeMonthWiseUE(int year, String doc_type, String arm1) {
		List<Map<String, Object>> list = adminDash.getCueTypeMonthWiseUE(year,doc_type,arm1);
		return list;
	}
	

	public List<Map<String, Object>> getActiveUnits() {
		String qry = "select count(sus_no) as total from tb_miso_orbat_unt_dtl where status_sus_no ='Active'";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	public List<Map<String, Object>> getReliefPgme() {
		String qry = "select count (app.*) as total from(select distinct a.sus_no, a.unit_name, a.frm_cmd_name, a.frm_coprs_name, a.frm_div_name,a.frm_bde_name,a.to_cmd_name,a.to_coprs_name,\r\n"
					+ "a.to_div_name,a.to_bde_name,to_char(a.nmb_date,'dd-mm-yyyy') as nmb_date,cur.ct_part_i_ii,d.arm_desc, x.label AS type_of_force from view_relief_report a \r\n"
					+ "JOIN tb_miso_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text\r\n"
					+ "	 LEFT JOIN tb_miso_orbat_arm_code d ON cur.arm_code::text = d.arm_code::text\r\n"
					+ "	 LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = cur.type_force::text\r\n"
					+ " WHERE a.sd_status='1'  order by nmb_date ) app";

		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}

	@RequestMapping(value = "/getCommandWiseActionCluster", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCommandWiseActionCluster(@RequestParam Date fromDate,
			@RequestParam Date toDate) {
		List<Map<String, Object>> list = adminDash.getCommandWiseActionCluster(fromDate, toDate);
		return list;
	}

	@RequestMapping(value = "/getCommandWiseUnitMov", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCommandWiseUnitMov(@RequestParam Date fromDate,
			@RequestParam Date toDate) {
		List<Map<String, Object>> list = adminDash.getCommandWiseUnitMov(fromDate, toDate);
		return list;
	}
	////////////////////////////////////

	///////////////////////////////
	@RequestMapping(value = "/admin/DocTypeReport", method = RequestMethod.GET)
	public ModelAndView DocTypeReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
	
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		
		
		Mmap.put("pageType", pageType1);
		Mmap.put("getArmNameList", M.getArmNameList());
//		Mmap.put("getRankList", getRankList(cat_id));
		
		
		pageType = pageType1;
		Mmap.put("msg", msg);
		System.out.println(pageType+"pagetype");
	/*	if (pdf != null && pdf.equals("Y")) {
			List<String> TH = new ArrayList<String>();
			TH.add("DOC TYPE");
			TH.add("WE/PE/FE/GSL NO.");
			TH.add("TABLE TITLE");
			TH.add("SPONSER DIRE");
			TH.add("TYPE");
			
			ArrayList<List<String>> aList = new ArrayList<List<String>>();
			aList.add(TH);
			return new ModelAndView(new DownloadPdf("L",TH), "userList", aList);
		} 
		*/
		Mmap.put("pageType", pageType1);
		return new ModelAndView("docTypeCueReportTiles");
	}

	@RequestMapping(value = "/admin/getDocTypeDetaisReport", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<CUE_TB_MISO_WEPECONDITIONS> getDocTypeDetaisReport(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {

		if(pageType.equals("we") || pageType.equals("pe") || pageType.equals("fe") || pageType.equals("gsl")) {
			String qry = "SELECT d FROM CUE_TB_MISO_WEPECONDITIONS d where  d.we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM CUE_TB_MISO_WEPECONDITIONS WHERE suprcdd_we_pe_no is not null) and d.we_pe_no is not null and d.status='1' and (type='1' or type='2' or type='3') and d.we_pe='"+pageType.toUpperCase()+"'";
			String countQry = "SELECT COUNT(d) FROM CUE_TB_MISO_WEPECONDITIONS d where  d.we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM CUE_TB_MISO_WEPECONDITIONS WHERE suprcdd_we_pe_no is not null) and d.we_pe_no is not null and d.status='1' and (type='1' or type='2' or type='3') and d.we_pe='"+pageType.toUpperCase()+"'";
			
			DataSet<CUE_TB_MISO_WEPECONDITIONS> dataSet = adminDash.DatatablesCriteriasCommonReportWEPECond(criterias, qry,countQry);
			return DatatablesResponse.build(dataSet, criterias);
		}else {
			return null;
		}
	}
	
	@RequestMapping(value = "/admin/getDocTypeDetaisReportWPF", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<CUE_TB_MISO_MMS_WET_PET_MAST_DET> getDocTypeDetaisReportWPF(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {

		if(pageType.equals("wet") || pageType.equals("pet") || pageType.equals("fet")) {
			String qry = "SELECT d FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET d where  d.wet_pet_no not in (SELECT DISTINCT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet is not null) and d.wet_pet_no is not null and d.status='1' and d.wet_pet='"+pageType.toUpperCase()+"'";
			String countQry = "SELECT COUNT(d) FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET d where  d.wet_pet_no not in (SELECT DISTINCT superseeded_wet_pet FROM CUE_TB_MISO_MMS_WET_PET_MAST_DET WHERE superseeded_wet_pet is not null) and d.wet_pet_no is not null and d.status='1' and d.wet_pet='"+pageType.toUpperCase()+"'";
			
			DataSet<CUE_TB_MISO_MMS_WET_PET_MAST_DET> dataSet = adminDash.DatatablesCriteriasCommonReportWETPETCond(criterias, qry,countQry);
			return DatatablesResponse.build(dataSet, criterias);
		}else {
			return null;
		}
	}

	@RequestMapping(value = "/admin/UnitDetaisReport", method = RequestMethod.GET)
	public ModelAndView UnitDetaisReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		pageTypeU = pageType1;
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("msg", msg);
		return new ModelAndView("unitDetaisrptTiles");
	}

	@RequestMapping(value = "/admin/getUnitsCommonDetaisReport", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUnitsCommonDetaisReport(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		DataSet<Map<String, Object>> dataSet = adminDash.DatatablesCriteriasCommonReportUnit(criterias, pageTypeU);
		return DatatablesResponse.build(dataSet, criterias);
	}
	
	
	@RequestMapping(value = "/admin/DocTypeReportWetPetFet", method = RequestMethod.GET)
	public ModelAndView DocTypeReportWetPetFet(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		pageType = pageType1;
		Mmap.put("msg", msg);
		Mmap.put("pageType", pageType1);
		Mmap.put("getTypeofRankcategoryListdash", getTypeofRankcategoryListdash());
		Mmap.put("getTypeofAppointementList", mcommon.getTypeofAppointementList());
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		if(pageType.equals("tpt")) {
		return new ModelAndView("Cue_DocTypeDetailsReportTptTiles");
		}
		else if(pageType.equals("wpn")) {
			return new ModelAndView("Cue_DocTypeDetailsReportWpnEqptTiles");
			}
		else if(pageType.equals("pers")) {
			return new ModelAndView("Cue_DocTypeDetailsReportPersTiles");
			}
		else if(pageType.equals("WE")) {
			
			return new ModelAndView("Cue_DocTypeDetailsOFwepeandwetpetTiles" );
			
			}
		else if(pageType.equals("WET")) {
			return new ModelAndView("Cue_DocTypeDetailsOFwepeandwetpetTiles");
			
			}
		else {
			return null;
		}
			
	}
	@RequestMapping(value = "/getDocTypeDetaisReportTptount", method = RequestMethod.POST)
	public @ResponseBody long getDocTypeDetaisReportTptcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String std_nomclature, String arm,
			String cont_comd, String cont_corps,String cont_div, String cont_bde) throws SQLException {
		
		return adminDash.DatatablesReporTptcount(Search, orderColunm, orderType, sessionUserId, std_nomclature,arm,
				 cont_comd,  cont_corps, cont_div,  cont_bde);
		}
	
	@RequestMapping(value = "/getDocTypeDetaisReportTpt", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getDocTypeDetaisReportTpt(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId, String std_nomclature, String arm,
			String cont_comd, String cont_corps,String cont_div, String cont_bde)
			throws SQLException {
		return adminDash.DatatablesReporTpt( startPage, pageLength, Search, orderColunm, orderType,sessionUserId, std_nomclature,arm,
				 cont_comd,  cont_corps, cont_div,  cont_bde);
	}
	
	@RequestMapping(value = "/getDocTypeDetaisReportWpncount", method = RequestMethod.POST)
	public @ResponseBody long getDocTypeDetaisReportWpncount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
			String cont_div, String cont_bde,String arm) throws SQLException {
		return adminDash.DatatablesReporWpncount(Search, orderColunm, orderType, sessionUserId, nomenClature, cont_comd, cont_corps, 
				 cont_div,  cont_bde, arm);
		}
	
	@RequestMapping(value = "/getDocTypeDetaisReportWpn", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getDocTypeDetaisReportWpn(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
			String cont_div, String cont_bde,String arm)
			throws SQLException {
		return adminDash.DatatablesReporWpn( startPage, pageLength, Search, orderColunm, orderType,sessionUserId, nomenClature, cont_comd, cont_corps, 
				 cont_div, cont_bde, arm);
	}
	
	@RequestMapping(value = "/getDocTypeDetaisReportPerscount", method = RequestMethod.POST)
	public @ResponseBody long getDocTypeDetaisReportPerscount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, 
			String appt_trade, String rank, String rank_cat,String cont_comd, String cont_corps, String cont_div, String cont_bde,
			String radio1) throws SQLException {
		return adminDash.DatatablesReporPerscount(Search, orderColunm, orderType, sessionUserId, appt_trade, rank, rank_cat,cont_comd,cont_corps,cont_div,cont_bde, radio1);
		}
	
	@RequestMapping(value = "/getDocTypeDetaisReportPers", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getDocTypeDetaisReportPers(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId, 
			 String appt_trade, String rank, String rank_cat,String cont_comd, String cont_corps, String cont_div, String cont_bde,String radio1)
			throws SQLException {
		return adminDash.DatatablesReporPers( startPage, pageLength, Search, orderColunm, orderType,sessionUserId, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
	}
	
	@RequestMapping(value = "/getitemtypedash", method = RequestMethod.POST)
	public @ResponseBody List<String> getitemtypedash(HttpSession sessionUserId,String selected) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct item_type from CUE_TB_MISO_MMS_ITEM_MSTR where  status='1' and  status_active='Active' and prf_group=:selected  order by item_type") ;
		q.setParameter("selected", selected);
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return list1;
	}
	
	@RequestMapping(value = "/getTypeofRankcategoryListdash", method = RequestMethod.POST)
	public List<T_Domain_Value> getTypeofRankcategoryListdash() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from T_Domain_Value where domainid=:domainid and codevalue not in('2','13')");
		q.setParameter("domainid", "RANKCATEGORY");
		@SuppressWarnings("unchecked")
		List<T_Domain_Value>  list = (List<T_Domain_Value> ) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getPrfTpt", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPrfTpt(HttpSession sessionUserId,String prf_group) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct group_name from TB_TMS_MCT_SLOT_MASTER where upper(group_name) like:group_name order by group_name").setMaxResults(10);
		q.setParameter("group_name",prf_group.toUpperCase()+"%");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return M.getAutoCommonList(sessionUserId,list1);
		
	}
	
	@RequestMapping(value = "/getitemtypedashTpt", method = RequestMethod.POST)
	public @ResponseBody List<String> getitemtypedashTpt(HttpSession sessionUserId,String selected) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct mct_nomen from TB_TMS_MCT_MAIN_MASTER where prf_code = (select prf_code from TB_TMS_MCT_SLOT_MASTER where group_name=:selected  ) order by mct_nomen") ;
		q.setParameter("selected", selected);
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q.list();
		tx.commit();
		session.close();
		return list1;
	}
	
	@RequestMapping(value = "/admin/unitMoveReport", method = RequestMethod.POST)
	public ModelAndView unitMoveReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		//	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);	
			
			
			String from_date = request.getParameter("frm_dt_a");
			String to_Date = request.getParameter("to_dt_a");
			
			String reportId = request.getParameter("report_id");
	
		pageType = pageType1;
		Mmap.put("msg", msg);
		Mmap.put("pageType", pageType1);
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		List<Tbl_CodesForm> comd=m.getCommandDetailsList();
		Mmap.put("getCommandList1",comd);
		String selectComd ="<option value=''>--Select--</option>";
		Mmap.put("selectcomd1", selectComd);
		Mmap.put("fromDate",from_date);
		Mmap.put("toDate",to_Date);
		Mmap.put("reportId",reportId);
		return new ModelAndView("unitMoveReportTiles");
	
	}
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}
	
	@RequestMapping(value = "/getDocTypeDetaisWEPEcount", method = RequestMethod.POST)
	public @ResponseBody long getDocTypeDetaisWEPEcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String cr_by, String crDate) throws SQLException {
		
		return adminDash.getDocTypeDetaisWEPEcount(Search, orderColunm, orderType, sessionUserId, cr_by, crDate);
		}
	
	@RequestMapping(value = "/getDocTypeDetaisWEPElist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getDocTypeDetaisWEPElist(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId,String cr_by, String crDate)
			throws SQLException {
	
		return adminDash.getDocTypeDetaisWEPElist( startPage, pageLength, Search, orderColunm, orderType,sessionUserId, cr_by, crDate);
	}
	
	@RequestMapping(value = "/getDocTypeDetaisWETPETcount", method = RequestMethod.POST)
	public @ResponseBody long getDocTypeDetaisWETPETcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String cr_by, String crDate) throws SQLException {
		
		return adminDash.getDocTypeDetaisWETPETcount(Search, orderColunm, orderType, sessionUserId,cr_by, crDate);
		}
	
	@RequestMapping(value = "/getDocTypeDetaisWETPETlist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>>  getDocTypeDetaisWETPETlist(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId,String cr_by, String crDate)
			throws SQLException {
		return adminDash.getDocTypeDetaisWETPETlist( startPage, pageLength, Search, orderColunm, orderType,sessionUserId, cr_by, crDate);
	}
	
	
	
	@RequestMapping(value = "/admin/cmdWiseUnitDtl", method = RequestMethod.POST)
	public ModelAndView cmdWiseUnitDtl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		
		 
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		
	
			String from_date = request.getParameter("frm_dt_cmd");
			String to_Date = request.getParameter("to_dt_cmd");
			String cmd = request.getParameter("cmd");
			String Ydata = request.getParameter("Ydata");
		pageType = pageType1;
		Mmap.put("msg", msg);
		Mmap.put("pageType", pageType1);
		
	
		List<Tbl_CodesForm> comd=m.getCommandDetailsList();
		Mmap.put("getCommandList1",comd);
		Mmap.put("getArmNameList", M.getArmNameList());
		String selectComd ="<option value=''>--Select--</option>";
		Mmap.put("selectcomd1", selectComd);
		Mmap.put("fromDate",from_date);
		Mmap.put("toDate",to_Date);
		Mmap.put("cmd",cmd);
		Mmap.put("Ydata",Ydata);
		return new ModelAndView("cmdWiseUnitDtlsTiles");
	
	}
	
	
	@RequestMapping(value = "/unit_wise_dtls_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> unit_wise_dtls_table(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String fromDate,String toDate, String cmd, String Ydata,
			String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name) throws SQLException {
		
		return adminDash.unit_wise_dtls_table(startPage, pageLength, Search, orderColunm, orderType,sessionUserId, fromDate, toDate,cmd,Ydata,
				 cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name);
	}
	
	@RequestMapping(value = "/unit_wise_dtls_tablecount", method = RequestMethod.POST)
	public @ResponseBody long unit_wise_dtls_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String fromDate,String toDate, String cmd, String Ydata,
			String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name) throws SQLException {
		
		return adminDash.unit_wise_dtls_tablecount(Search, orderColunm, orderType, sessionUserId, fromDate, toDate,cmd,Ydata,
				cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name);
	}
	
	@RequestMapping(value = "/admin/DocTypeReportWPF", method = RequestMethod.GET)
	public ModelAndView DocTypeReportWPF(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		pageType = pageType1;
		Mmap.put("msg", msg);
		

		Mmap.put("pageType", pageType1);
		return new ModelAndView("docTypeCueReportWPFTiles");
	}
	
	
	@RequestMapping(value = "/admin/rankCategoryWiseReport", method = RequestMethod.POST)
	public ModelAndView rankCategoryWiseReport(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type", required = false) String pageType1,
			@RequestParam(value = "pdf", required = false) String pdf,
			HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		 String roleFormationNo = session.getAttribute("roleFormationNo").toString();
	
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
	
		if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
			List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			Mmap.put("getCommandList",comd);
			String selectComd ="<option value=''>--Select--</option>";
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectcomd", selectComd);
			Mmap.put("selectcorps",select);
			Mmap.put("selectDiv",select);
			Mmap.put("selectBde",select);
		}
		
			String cat_id = request.getParameter("cat_id");
		pageType = pageType1;
		Mmap.put("msg", msg);
		Mmap.put("pageType", pageType1);
		Mmap.put("getArmNameList", M.getArmNameList());
		Mmap.put("getRankList", getRankList(cat_id));
		
	

		Mmap.put("cat_id",cat_id);
		return new ModelAndView("RankCategoryWiseUETiles");
	
	}
	
	@RequestMapping(value = "/Rank_cat_wise_dtl_table", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Rank_cat_wise_dtl_table(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name,String rank) throws SQLException {
		
		return adminDash.Rank_cat_wise_dtl_table(startPage, pageLength, Search, orderColunm, orderType,sessionUserId, cat_id,
				 cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name, rank);
	}
	
	@RequestMapping(value = "/Rank_cat_wise_dtl_tablecount", method = RequestMethod.POST)
	public @ResponseBody long Rank_cat_wise_dtl_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name,String rank) throws SQLException {
		
		return adminDash.Rank_cat_wise_dtl_tablecount(Search, orderColunm, orderType, sessionUserId, cat_id,
				cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name, rank);
	}
	
	public List<Map<String, Object>> getDocTypeWiseCountWET() {
		String qry = "select distinct wpf.wet_pet ,count(wpf.wet_pet) as noofwes\r\n"
				+ "from cue_tb_miso_mms_wet_pet_mast_det wpf\r\n"
				+ "	inner join  CUE_TB_MISO_WEPECONDITIONS d  on d.wet_pet_no=wpf.wet_pet_no\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w \r\n"
				+ "ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "	FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1' and wpf.wet_pet='WET'   AND d.type IN ('1', '2', '3') GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getDocTypeWiseCountPET() {
		String qry = "select distinct wpf.wet_pet ,count(wpf.wet_pet) as noofwes\r\n"
				+ "from cue_tb_miso_mms_wet_pet_mast_det wpf\r\n"
				+ "	inner join  CUE_TB_MISO_WEPECONDITIONS d  on d.wet_pet_no=wpf.wet_pet_no\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w \r\n"
				+ "ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "	FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1' and wpf.wet_pet='PET'  AND d.type IN ('1', '2', '3') GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getDocTypeWiseCountFET() {
		String qry = "select distinct wpf.wet_pet ,count(wpf.wet_pet) as noofwes\r\n"
				+ "from cue_tb_miso_mms_wet_pet_mast_det wpf\r\n"
				+ "	inner join  CUE_TB_MISO_WEPECONDITIONS d  on d.wet_pet_no=wpf.wet_pet_no\r\n"
				+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w \r\n"
				+ "ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
				+ "or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
				+ "left join tb_miso_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
				+ "WHERE d.we_pe_no NOT IN (SELECT DISTINCT suprcdd_we_pe_no \r\n"
				+ "	FROM CUE_TB_MISO_WEPECONDITIONS \r\n"
				+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
				+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1' and wpf.wet_pet='FET'   AND d.type IN ('1', '2', '3') GROUP BY 1 ORDER BY 1 DESC";
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
	
	public List<Object[]>  getRankList(String cat_id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		String qry="";
		if(!cat_id.equals("")) {
			if(cat_id.equals("Civilian")) {
				qry+= "and t.codevalue in ('4','5','6','7','8','9','10','11')";
			}else {
			qry+= "and t.label =:rank";
			}
		}
		q = session.createQuery("select distinct  rank.code,rank.description from CUE_TB_PSG_RANK_APP_MASTER rank\r\n"
				+ "	, T_Domain_Value t where rank.parent_code=t.codevalue  and t.domainid='RANKCATEGORY'\r\n"
				+ "	and status ='1' and code is not null and upper(level_in_hierarchy) = :level_in_hierarchy\r\n"
				+ qry+	" order by rank.description ");
		q.setParameter("level_in_hierarchy", "RANK");
		if(!cat_id.equals("Civilian")) {
		q.setParameter("rank", cat_id);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/get_DocTypeDetais_Report", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_DocTypeDetais_Report(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name, String radio1) throws SQLException {
		
		return adminDash.get_DocTypeDetais_Report(pageType,startPage, pageLength, Search, orderColunm, orderType,sessionUserId,
				 cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name,radio1);
	}
	
	@RequestMapping(value = "/get_DocTypeDetais_Report_tablecount", method = RequestMethod.POST)
	public @ResponseBody long get_DocTypeDetais_Report_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name, String radio1) throws SQLException {
		
		return adminDash.get_DocTypeDetais_Report_tablecount(pageType,Search, orderColunm, orderType, sessionUserId, 
				cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name,radio1);
	}
	
	@RequestMapping(value = "/getCueTypeYearWiseUEwet", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCueTypeYearWiseUEwet(int fromYear, int toYear, String arm) {
		List<Map<String, Object>> list = adminDash.getCueTypeYearWiseUEwet(fromYear, toYear, arm);
		return list;
	}
	
	@RequestMapping(value = "/getCueTypeMonthWiseUEwet", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getCueTypeMonthWiseUEwet(int year, String doc_type, String armwet) {
		List<Map<String, Object>> list = adminDash.getCueTypeMonthWiseUEwet(year,doc_type,armwet);
		return list;
	}
	
	@RequestMapping(value = "/get_Active_Unit_DocTypeDetais_Report", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_Active_Unit_DocTypeDetais_Report(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name, String location, String radio1) throws SQLException {
		return adminDash.get_Active_Unit_DocTypeDetais_Report(pageType,startPage, pageLength, Search, orderColunm, orderType,sessionUserId, cat_id,
				 cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name,  location,radio1);
	}
	
	@RequestMapping(value = "/get_Active_Unit_DocTypeDetais_Report_tablecount", method = RequestMethod.POST)
	public @ResponseBody long get_Active_Unit_DocTypeDetais_Report_tablecount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name, String location, String radio1) throws SQLException {
		
		return adminDash.get_Active_Unit_DocTypeDetais_Report_tablecount(pageType,Search, orderColunm, orderType, sessionUserId, cat_id,
				cont_comd,  cont_corps, cont_div, cont_bde, arm, unit_sus_no, unit_name,  location,radio1);
	}
	
	@RequestMapping(value = "/get_Dtl_doc_type_month_wise", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_Dtl_doc_type_month_wise(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String arm_a, String doc_type, String year) throws SQLException {
		return adminDash.get_Dtl_doc_type_month_wise(pageType,startPage, pageLength, Search, orderColunm, orderType,sessionUserId,
				arm_a,doc_type,year);
	}
	
	@RequestMapping(value = "/get_Dtl_doc_type_month_wiseCountsum", method = RequestMethod.POST)
	public @ResponseBody long get_Dtl_doc_type_month_wiseCountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String arm_a, String doc_type, String year) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		return adminDash.get_Dtl_doc_type_month_wiseCountsum(Search, orderColunm, orderType, sessionUserId, arm_a,  doc_type, year);
	}

	
	@RequestMapping(value = "/get_Dtl_doc_type_month_wise_WET_PET", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> get_Dtl_doc_type_month_wise_WET_PET(int startPage,String Search,int pageLength,String orderColunm,String orderType,HttpSession sessionUserId
			,String arm_wet, String doc_wet, String year_wet) throws SQLException {
		
		return adminDash.get_Dtl_doc_type_month_wise_WET_PET(pageType,startPage, pageLength, Search, orderColunm, orderType,sessionUserId,
				arm_wet,doc_wet,year_wet);
	}
	@RequestMapping(value = "/get_Dtl_doc_type_month_wise_WET_PETCountsum", method = RequestMethod.POST)
	public @ResponseBody long get_Dtl_doc_type_month_wise_WET_PETCountsum(String Search,String orderColunm,String orderType,HttpSession sessionUserId
			,String msg,String arm_wet, String doc_wet, String year_wet) throws SQLException {
		 String roleType = sessionUserId.getAttribute("roleType").toString();
		return adminDash.get_Dtl_doc_type_month_wise_WET_PETCountsum(Search, orderColunm, orderType, sessionUserId, arm_wet,  doc_wet,  year_wet);
	}
}