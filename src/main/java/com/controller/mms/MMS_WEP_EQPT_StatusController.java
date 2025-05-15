package com.controller.mms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_ReportsDAO;
import com.dao.tms.vehicleDetailsDAO;
import com.models.CUE_TB_MISO_MMS_ITEM_MSTR;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MMS_WEP_EQPT_StatusController {
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@Autowired
	MMS_ReportsDAO mms_report;
	
	@RequestMapping(value = "/admin/wep_eqpt_status", method = RequestMethod.GET)
	public ModelAndView wep_eqpt_status(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(!roleAccess.equals("HeadQuarter") & !roleAccess.equals("MISO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getItemGroupList", getItemGroupList());
		
		if(roleAccess.equals("Line_dte")) {
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		return new ModelAndView("wep_eqpt_statusTile");
	}
	
	@RequestMapping(value = "/admin/wep_eqpt_status_details", method = RequestMethod.POST)
	public ModelAndView wep_eqpt_status_details(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "cmd1", required = false) String cmd,
			@RequestParam(value = "type_wep_eqpt1", required = false) String type_wep_eqpt,
			@RequestParam(value = "prf_code1", required = false) String prf_code,
			@RequestParam(value = "line_dte_sus1", required = false) String line_dte_sus)
	{	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		if(!roleAccess.equals("HeadQuarter") & !roleAccess.equals("MISO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		
		if(roleAccess.equals("Line_dte")) {
			line_dte_sus = roleSusNo;
			Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		Mmap.put("getCommandList", all.getCommandDetailsList());
		Mmap.put("getItemGroupList", getItemGroupList());
		Mmap.put("cmd", cmd);
		Mmap.put("type_wep_eqpt", type_wep_eqpt);
		Mmap.put("prf_code", prf_code);
		Mmap.put("line_dte_sus1", line_dte_sus);
		
		if(!type_wep_eqpt.equals("")) {
			ArrayList<ArrayList<String>> list = mms_report.wep_eqpt_status_details(cmd,type_wep_eqpt,prf_code,line_dte_sus);
			if(list.size() == 0)
			{
		 		Mmap.put("list", list);
			}
			else
			{
				double sumUE = 0;
				long uh = 0;
				long loan = 0;
				long sector = 0;
				long acsfp = 0;
				long wwr_unit = 0;
				long wwr_depot = 0;
				long total_uh = 0;
				for(int i=0;i<list.size();i++) {
					String sumUE1 = list.get(i).get(6);
					if(sumUE1 == null) {
						sumUE1 = "0";
					}
					sumUE = sumUE + Double.parseDouble(sumUE1);
					uh = uh + Long.parseLong(list.get(i).get(7));
					loan = loan +  Long.parseLong(list.get(i).get(8));
					sector = sector +  Long.parseLong(list.get(i).get(9));
					acsfp = acsfp +  Long.parseLong(list.get(i).get(10));
					wwr_unit = wwr_unit +  Long.parseLong(list.get(i).get(11));
					wwr_depot = wwr_depot +  Long.parseLong(list.get(i).get(12));
					total_uh = total_uh +  Long.parseLong(list.get(i).get(13));
				}
				Mmap.put("sumUE",sumUE);
				Mmap.put("uh",uh);
				Mmap.put("loan",loan);
				Mmap.put("sector",sector);
				Mmap.put("acsfp",acsfp);
				Mmap.put("wwr_unit",wwr_unit);
				Mmap.put("wwr_depot",wwr_depot);
				Mmap.put("total_uh",total_uh);
				Mmap.put("list", list);
			}
		}
	 	return new ModelAndView("wep_eqpt_statusTile");
	}
	
	public List<CUE_TB_MISO_MMS_ITEM_MSTR> getItemGroupList() {
		Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionComnd.beginTransaction();
		Query q = sessionComnd.createQuery("select codevalue,label from T_Domain_Value where domainid = 'ITEMGROUP' order by label");
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_ITEM_MSTR> list = (List<CUE_TB_MISO_MMS_ITEM_MSTR>) q.list();
		tx.commit();
		sessionComnd.close();
		return list;
	}
	
	@RequestMapping(value = "/getPRFListbyItemGroup", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getPRFListbyItemGroup(HttpSession sessionA,
			@RequestParam String type) {
		if(type.equals("")) {
			return null;
		}else {
			List<Map<String, Object>> list = mms_report.getPRFListbyItemGroup(type,sessionA);
			return list;
		}
	}
	
	/// 
	
	@RequestMapping(value = "/wep_eqpt_statusList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> wep_eqpt_statusList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String cmd,String type_wep_eqpt,String prf_code,String line_dte_sus,String sus_no_list,HttpSession session){
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status", session.getAttribute("roleid").toString());
		
		if (val == false) {
			return null;
		}else {
			
			if(type_wep_eqpt.equals("")) {
				return null;
			}else if(prf_code.equals("") || prf_code.equals("0")){
				return null;
			}else{
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Line_dte")) {
					line_dte_sus = roleSusNo;
				}
			
				return mms_report.wep_eqpt_statusList(cmd,type_wep_eqpt,prf_code,line_dte_sus,sus_no_list);
			}
		}
	}
	
	@RequestMapping(value = "/wep_eqpt_statusCount", method = RequestMethod.POST)
	public @ResponseBody long wep_eqpt_statusCount(String Search,String cmd,String type_wep_eqpt,String prf_code,String line_dte_sus,String sus_no_list,HttpSession session){
		Boolean val = roledao.ScreenRedirect("wep_eqpt_status", session.getAttribute("roleid").toString());
		
		if (val == false) {
			return 0;
		}else {
			
			if(type_wep_eqpt.equals("")) {
				return 0;
			}else if(prf_code.equals("") || prf_code.equals("0")){
				return 0;
			}else{
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				if(roleAccess.equals("Line_dte")){
					line_dte_sus = roleSusNo;
				}
				return mms_report.wep_eqpt_statusList(cmd,type_wep_eqpt,prf_code,line_dte_sus,sus_no_list).size();
			}
		}
	}
}