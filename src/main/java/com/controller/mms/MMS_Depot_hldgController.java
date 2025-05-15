package com.controller.mms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.MMS_Depot_hldgDAO;
import com.dao.mms.Mms_Common_DAO;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class MMS_Depot_hldgController {
	
	@Autowired
	private MMS_Depot_hldgDAO m3DAO;
	
	@Autowired
	private Mms_Common_DAO mmsCommonDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	ValidationController validation = new ValidationController();
	//DEPOT HLDG MODULE (1)-> (DEPOT MCR SCREEN START)
	@RequestMapping(value = "/mms_depot_mcr", method = RequestMethod.GET)
	public ModelAndView mms_depot_mcr(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String username=(String) session.getAttribute("username");
		int roleid = (Integer)session.getAttribute("roleid");
		int userid = (Integer)session.getAttribute("userId");
		String roleType = (String) session.getAttribute("roleType");
		String accsLvl = (String) session.getAttribute("roleAccess");
		String accssubLvl=(String) session.getAttribute("roleAccess");
		
		Boolean val = roledao.ScreenRedirect("mms_depot_mcr", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		accssubLvl = l1.get(0).get(1);
		accsLvl = l1.get(0).get(7);
		roleType = l1.get(0).get(8);
		Mmap.put("r_1", l1);
		Mmap.put("msg", msg);
		return new ModelAndView("mms_depot_mcrTiles");
	}
	//DEPOT HLDG MODULE (1)-> (DEPOT MCR SCREEN END)
	
	//(1)-> (DEPOT MCR SCREEN METHODS START)
	@RequestMapping(value = "/mms_print_depot_mcr", method = RequestMethod.POST)
	public ModelAndView mms_print_depot_mcr(@ModelAttribute("printDepotId") String printDepotId,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession session){
		
		Boolean val = roledao.ScreenRedirect("mms_depot_mcr", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("m_1", m3DAO.depotMcrList(printDepotId));
		model.put("m_2", printDepotId);
		
		List<String> a1 = mmsCommonDAO.getMMSHirarNameBySUS("BRIGADE", printDepotId);
		String[] pa1 = a1.get(0).split(":");
		List<String> a2 = mmsCommonDAO.getMMSHirarNameBySUS("DIVISION", printDepotId);
		String[] pa2 = a2.get(0).split(":");
		List<String> a3 = mmsCommonDAO.getMMSHirarNameBySUS("CORPS", printDepotId);
		String[] pa3 = a3.get(0).split(":");
		List<String> a4 = mmsCommonDAO.getMMSHirarNameBySUS("COMMAND", printDepotId);
		String[] pa4 = a4.get(0).split(":");
		
		String hqv = "";
		if(pa1[1].length() > 0) {
			hqv = hqv + pa1[1];
		}
		if(pa2[1].length() > 0) {
			if (hqv.length() >0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa2[1];
		}
			
		if(a3.size() > 0 && (!a3.equals(null))) {
			if (hqv.length() >0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa3[1];
		}
		if(pa4[1].length() > 0) {
			if (hqv.length() >0) {
				hqv = hqv + " / ";
			}
			hqv = hqv + pa4[1];
		}
		
	    model.put("m_5",hqv);
		model.put("m_3", mmsCommonDAO.getMMSHirarNameBySUS("HQ", printDepotId));
		model.put("m_dt",mmsCommonDAO.getMMSMaxDt(printDepotId));
		
		return new ModelAndView("mms_print_depot_mcrTiles");
	}
	
	@RequestMapping(value = "/admin/depotMcrList", method = RequestMethod.POST)
	public ModelAndView depotMcrList(@ModelAttribute("sus_no2") String sus_no2,String unit_name2,ModelMap model,HttpSession session){
		int userid = (Integer)session.getAttribute("userId");
		Boolean val = roledao.ScreenRedirect("mms_depot_mcr", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		if(sus_no2.equals("")){
			 model.put("msg", "Please Select the To SUS.");
			 return new ModelAndView("mms_depot_mcr"); 
		 }
		 if(sus_no2 != "") {
				if(validation.sus_noLength(sus_no2) == false){
					model.put("msg",validation.sus_noMSG);
					return new ModelAndView("redirect:mms_depot_mcr");
				}
				
			}
			
		 if(unit_name2.equals("")){
			 model.put("msg", "Please Select the To Unit.");
			 return new ModelAndView("mms_depot_mcr"); 
		 }
		 if(unit_name2 != "") {
				if(validation.checkUnit_nameLength(unit_name2) == false){
					model.put("msg",validation.unit_nameMSG);
					return new ModelAndView("redirect:mms_depot_mcr");
				}
				
			}
		
		if(roleAccess.equals("Unit")){
			sus_no2 = roleSusNo;
		}
		ArrayList<ArrayList<String>> l1 = mmsCommonDAO.getMMSRData(session);
		model.put("r_1", l1);
		model.put("m_1", m3DAO.depotMcrList(sus_no2));
		model.put("m_2", sus_no2);
		model.put("m_3", unit_name2);
		return new ModelAndView("mms_depot_mcrTiles");	
	}
	//(1)-> (DEPOT MCR SCREEN METHODS END)
}