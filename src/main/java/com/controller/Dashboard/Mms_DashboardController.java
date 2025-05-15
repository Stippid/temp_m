package com.controller.Dashboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.Dashboard.MmsDashboardDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_ADMIN_DAO;
import com.dao.mms.MMS_Adhoc_DAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.CUE_TB_MISO_PRF_Mst;
import com.models.MMS_TV_ADH_UNIT_STATUS;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Mms_DashboardController {
	
	@Autowired
	MmsDashboardDAO mmsDash;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private MMS_ADMIN_DAO m7DAO;
	
	@RequestMapping(value = "/admin/mmsDashboard", method = RequestMethod.GET)
	public ModelAndView mmsDashboard(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.CheckDashboard("mmsDashboard", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getmmscensusTotalList", mmsDash.getmmscensusTotalList());
		Mmap.put("getmmsprfTotalnoList", mmsDash.getmmsprfTotalnoList());
		Mmap.put("getmmsunitTotalnoList", mmsDash.getmmsunitTotalnoList());
		Mmap.put("getmmsdepotTotalList", mmsDash.getmmsdepotTotalList());
		Mmap.put("getmmssectorTotalList", mmsDash.getmmssectorTotalList());
		Mmap.put("getmmsloanTotalList", mmsDash.getmmsloanTotalList());
		Mmap.put("getmmsacsfpTotalList", mmsDash.getmmsacsfpTotalList());
		Mmap.put("getmmsengrTotalList", mmsDash.getmmsengrTotalList());
		
		Mmap.put("getdefaulterunittbl",  mmsDash.getdefaulterunittbl());
		Mmap.put("getunitwithmcrobsn",  mmsDash.getunitwithmcrobsn());
		Mmap.put("getmmsRoRioStatustbl",  mmsDash.getmmsRoRioStatustbl());
		Mmap.put("getcensusnostatustbl",  mmsDash.getcensusnostatustbl());
		Mmap.put("getclofwpneqpttbl",  mmsDash.getclofwpneqpttbl());
		Mmap.put("getmmsprfnamenoList",getmmsprfnamenoList());
		
		return new ModelAndView("mmsDashboardTiles");
	}
	
	public List<CUE_TB_MISO_PRF_Mst> getmmsprfnamenoList() {
		return mmsDash.getmmsprfnamenoList();
	}
	
	@RequestMapping(value = "/getchartdivPRFWiseList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String> > getchartdivPRFWiseList(String prf) {
		prf = prf.replace("&#39;","");
		return mmsDash.getchartdivPRFWiseList(prf);
	}
	
	@RequestMapping(value = "/admin/TotalUnitReport", method = RequestMethod.GET)
	public ModelAndView TotalUnitReport(ModelMap Mmap) {
		return new ModelAndView("TotalUnitReportTiles");
	}
	@RequestMapping(value = "/admin/getTotalUnitReport")
	public @ResponseBody DatatablesResponse<MMS_TV_ADH_UNIT_STATUS> getTotalUnitReport(@DatatablesParams DatatablesCriterias criterias,HttpSession session, HttpServletRequest request) {
		DataSet<MMS_TV_ADH_UNIT_STATUS> dataSet = mmsDash.DatatablesCriteriasadhunit(criterias);
		return DatatablesResponse.build(dataSet, criterias);
	}
	
	@RequestMapping(value = "/Ustatuslist_dash", method = RequestMethod.GET)
	public ModelAndView Ustatuslist_dash(ModelMap model){
		String deo="ALL";
		String stat="ALL";
		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH)+1;
		int y = cal.get(Calendar.YEAR);
		String mnth= y+"-"+String.format("%02d", m);
	
		ArrayList<ArrayList<String>> list = m7DAO.UnitMcrStatusList(deo,stat,mnth,"","","","","","");
		model.put("m_12", list);
		return new ModelAndView("mms_unit_status_dash_tiles");
	}
	
	@Autowired
    MMS_Adhoc_DAO adao;
	
	@RequestMapping(value = "/admin/wast_dash_reportlist", method = RequestMethod.POST)
	public ModelAndView wast_dash_reportlist(ModelMap Mmap,HttpSession session) {
		ArrayList<ArrayList<String>> list = adao.wastreportlist();
		Mmap.put("m_12", list);
		return new ModelAndView("mms_wast_report_dash_tiles");
	}
	
	
	
	@RequestMapping(value = "/getTotalUnitStatusReport", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getTotalUnitStatusReport(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId){
		Boolean val = roledao.CheckDashboard("mmsDashboard", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return null;
		}else {
			return mmsDash.getTotalUnitStatusReport(startPage,pageLength,Search,orderColunm,orderType);
		}
	}
	@RequestMapping(value = "/getTotalUnitStatusReportCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalUnitStatusReportCount(HttpSession sessionUserId,String Search){
		Boolean val = roledao.CheckDashboard("mmsDashboard", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return 0;
		}else {
			return mmsDash.getTotalUnitStatusReportCount(Search);
		}
	}
	
	
}
