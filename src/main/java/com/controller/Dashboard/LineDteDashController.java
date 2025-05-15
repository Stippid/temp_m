package com.controller.Dashboard;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.dao.Dashboard.CueDashboardDAO;
import com.dao.Dashboard.FormationDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class LineDteDashController {

	@Autowired
	CueDashboardDAO adminDash;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	FormationDashboardController formDashbord = new FormationDashboardController();
	
	@Autowired
	FormationDashboardDAO formDash;
	
	DateWithTimeStampController d = new DateWithTimeStampController();
	
	@RequestMapping(value = "/admin/line_dte_Dashboard", method = RequestMethod.GET)
	public ModelAndView line_dte_Dashboard(ModelMap Mmap, HttpSession session) {
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("line_dte_Dashboard", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(roleAccess.equals("Line_dte")){
			List<Map<String, Object>> getUpdationState =  getUpdationState(session);
			String  bvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getUpdationState.get(0).get("b_up").toString()) * 100) / (Float.parseFloat(getUpdationState.get(0).get("b_up").toString()) + Float.parseFloat(getUpdationState.get(0).get("b_yet").toString()))));
			String  avehProgressCount = String.format("%.2f" ,((Float.parseFloat(getUpdationState.get(0).get("a_up").toString()) * 100) / (Float.parseFloat(getUpdationState.get(0).get("a_up").toString()) + Float.parseFloat(getUpdationState.get(0).get("a_yet").toString()))));
			String  cvehProgressCount = String.format("%.2f" ,((Float.parseFloat(getUpdationState.get(0).get("c_up").toString()) * 100) / (Float.parseFloat(getUpdationState.get(0).get("c_up").toString()) + Float.parseFloat(getUpdationState.get(0).get("c_yet").toString()))));
			String  mcrProgressCount = String.format("%.2f" ,((Float.parseFloat(getUpdationState.get(0).get("d_up").toString()) * 100) / (Float.parseFloat(getUpdationState.get(0).get("d_up").toString()) + Float.parseFloat(getUpdationState.get(0).get("d_yet").toString()))));
			
			Mmap.put("getVehicleListInFRM", getUpdationState);
			Mmap.put("bvehProgressCount", bvehProgressCount);
			Mmap.put("avehProgressCount", avehProgressCount);
			Mmap.put("cvehProgressCount", cvehProgressCount);
			Mmap.put("mcrProgressCount", mcrProgressCount);
			Mmap.put("getWPNHoldingStateData", getHoldingStateData(session, "WE"));
			Mmap.put("getEQPTHoldingStateData", getHoldingStateData(session, "WET"));
			
			Mmap.put("maxDate", d.getFromDateByMonthCount(0));
			Mmap.put("futureDate", d.getFromDateByMonthCount(6));
			
		}else {
			return new ModelAndView("AccessTiles");
		}
		return new ModelAndView("line_dte_DashboardTiles");
	}
	
	public String getHoldingStateData(HttpSession sessionA, String type) {
	
		List<Map<String, Object>> list = formDash.getHoldingStateData(sessionA, type);
		String list1 = "";
		for (int i = 0; i < list.size(); i++) {
			list1 += ",{'colname' : '" + list.get(i).get("prf_grp_short") + "' ,'colcode' : "
					+ list.get(i).get("prf_code") + " , 'ue' : " + list.get(i).get("ue") + ", 'uh' : "
					+ list.get(i).get("uh") + ", 'ss' : " + list.get(i).get("ss") + ", 'ls' : "
					+ list.get(i).get("ls") + ", 'ac' : " + list.get(i).get("ac") + ", 'res' : " + list.get(i).get("res") + "}";
		}
		if (list1.length() > 0) {
			list1 = "[" + list1.substring(1, list1.length());
			list1 += "]";
		}else {
			list1 = "[]";
		}
		return list1;
	}
	
	public List<Map<String, Object>> getUpdationState(HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		String whr = "";
		if (roleAccess.equals("Line_dte")){
			whr =" and a.arm_code  in (select arm_code from tb_miso_orbat_line_dte where line_dte_sus = '"+roleSusNo+"' ) ";
		}else if(roleAccess.equals("MISO")){
			whr = "";
		}else {
			return null;
		}
		
		String qry = "SELECT A.UPDATED as a_up,AA.YET_TO_UPDATE as a_yet ,\r\n"
				+ "	B.UPDATED as b_up,BB.YET_TO_UPDATE as b_yet,"
				+ "C.UPDATED as c_up,CC.YET_TO_UPDATE as c_yet,"
				+ "D.UPDATED as d_up,DD.YET_TO_UPDATE as d_yet "
				+ " FROM  \r\n"
				+ "	(select  count(DISTINCT c.sus_no) as UPDATED from tb_tms_census_retrn_main c \r\n"
				+ "		JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = c.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text)) as A, \r\n"
				+ " \r\n"
				+ "	(select  count(DISTINCT c.sus_no) as YET_TO_UPDATE from tb_tms_census_retrn_main c \r\n"
				+ "		JOIN tb_tms_census_retrn cr ON c.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = c.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(c.approve_date, 'mm-yyyy'::text), '0'::text)) as AA,\r\n"
				+ " \r\n"
				+ "	(select  count(DISTINCT b.sus_no) as UPDATED from tb_tms_mvcr_parta_main b  \r\n"
				+ "		JOIN tb_tms_mvcr_parta_dtl c ON b.sus_no::text = c.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = b.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(b.approve_date, 'mm-yyyy'::text), '0'::text)) as B,\r\n"
				+ "  \r\n"
				+ "	(select  count(DISTINCT b.sus_no) as YET_TO_UPDATE from tb_tms_mvcr_parta_main b  \r\n"
				+ "		JOIN tb_tms_mvcr_parta_dtl c ON b.sus_no::text = c.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = b.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(b.approve_date, 'mm-yyyy'::text), '0'::text)) as BB,\r\n"
				+ "  \r\n"
				+ "	(select  count(DISTINCT e.sus_no) as UPDATED from tb_tms_emar_report_main e \r\n"
				+ "		JOIN tb_tms_emar_report cr ON e.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = e.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) = ltrim(to_char(e.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text)) as C,\r\n"
				+ "  \r\n"
				+ "	(select  count(DISTINCT e.sus_no) as YET_TO_UPDATE from tb_tms_emar_report_main e \r\n"
				+ "		JOIN tb_tms_emar_report cr ON e.sus_no::text = cr.sus_no::text \r\n"
				+ "		join tb_miso_orbat_unt_dtl a on a.sus_no = e.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where ltrim(to_char(now(), 'mm-yyyy'::text), '0'::text) != ltrim(to_char(e.approve_date::date::timestamp with time zone, 'mm-yyyy'::text), '0'::text)) as CC, \r\n "
				+ "  \r\n"
				+ "	(select count(distinct a.sus_no) as UPDATED from mms_tb_unit_upload_document c\r\n" 
				+ "		join tb_miso_orbat_unt_dtl a on c.sus_no = a.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where to_char(now(), 'mm-yyyy'::text) = to_char(c.created_on, 'mm-yyyy'::text)) as D,"
				+ "  \r\n"
				+ "	(select count(distinct a.sus_no) as YET_TO_UPDATE from mms_tb_unit_upload_document c\r\n" 
				+ "		join tb_miso_orbat_unt_dtl a on c.sus_no = a.sus_no AND a.status_sus_no = 'Active'  " + whr
				+ "		where to_char(now(), 'mm-yyyy'::text) != to_char(c.created_on, 'mm-yyyy'::text)) as DD"
				;
		
		List<Map<String, Object>> list = adminDash.getAllReportListJdbc(qry);
		return list;
	}
}
