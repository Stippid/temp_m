package com.controller.mnh;



import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.MNH_Common_DAO;
import com.dao.mnh.MNH_DataStatusDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class MNH_DataStatusController {
	
	
	@Autowired
	RoleBaseMenuDAO roledao;
	
	@Autowired
	MNH_Common_DAO mnh1_Dao;
	
	@Autowired
	MNH_DataStatusDAO mnh5_Dao;
	
	MNH_CommonController mcommon = new MNH_CommonController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	MNH_ValidationController validation = new MNH_ValidationController();
	// DATA STATUS MODULE (1)-> (HOSPITAL DATA STATUS SCREEN START)
	@RequestMapping(value = "/admin/mnh_hospital_status", method = RequestMethod.GET)
	public ModelAndView mnh_hospital_status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_hospital_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getCommandList", all.getCommandDetailsList());
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		//Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_hospital_statusTiles");
	}
	// DATA STATUS MODULE (1)-> (HOSPITAL DATA STATUS SCREEN END)

	// DATA STATUS MODULE (2)-> (APPROVAL UNIT STATUS SCREEN START)
	@RequestMapping(value = "/admin/mnh_unit_approval", method = RequestMethod.GET)
	public ModelAndView mnh_unit_approval(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_unit_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_unit_approvalTiles");
	}
	// DATA STATUS MODULE (2)-> (APPROVAL UNIT STATUS SCREEN END)

	// DATA STATUS MODULE (3)-> (APPROVAL MISO STATUS SCREEN START)
	@RequestMapping(value = "/admin/mnh_miso_approval", method = RequestMethod.GET)
	public ModelAndView mnh_miso_approval(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_miso_approval", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("ml_2", mcommon.getMNHUserList("", session));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_miso_approvalTiles");
	}
	// DATA STATUS MODULE (3)-> (APPROVAL MISO STATUS SCREEN END)

	
	// (1)-> (HOSPITAL DATA STATUS SCREEN METHODS START)
	@RequestMapping(value = "/search_hospital_datastatus", method = RequestMethod.POST)
	public ModelAndView search_hospital_datastatus(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@ModelAttribute("sus1") String sus1, String unit1, String cmd1, String yr1) {
		Boolean val = roledao.ScreenRedirect("mnh_hospital_status", session.getAttribute("roleid").toString());
		
		String role = session.getAttribute("role").toString();
		 String userId = session.getAttribute("userId").toString();
	
		//int deo_user_id = Integer.parseInt(request.getParameter("user_id"));	
		
		
		
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	
		if (validation.checkYearLength(yr1) == false) {
			model.put("msg", validation.yearMSG);
			return new ModelAndView("redirect:mnh_hospital_status");
		}
		List<Map<String, Object>> list = mnh5_Dao.search_hospital_datastatus(sus1, cmd1, yr1,role,userId);
		model.put("list", list);
		model.put("size", list.size());
		model.put("sus1", sus1);
		model.put("unit1", unit1);
		model.put("cmd1", cmd1);
		model.put("yr1", yr1);
		model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		model.put("getCommandList", all.getCommandDetailsList());
		return new ModelAndView("mnh_hospital_statusTiles");
	}
	// (1)-> (HOSPITAL DATA STATUS SCREEN METHODS END)

	// (2),(3)-> (APPROVAL UNIT & MISO STATUS SCREEN METHODS START)
	@RequestMapping(value = "/search_approval_datastatus", method = RequestMethod.POST)
	public ModelAndView search_approval_datastatus(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
			String unit1, String cmd1, String user1, String mth_yr1, String para1, String frm_dt1,String to_dt1,HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("mnh_hospital_status", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if (para1.equalsIgnoreCase("UNIT")) {
			if (sus1 != "") {
				
				if( sus1 == null || sus1.equals("")){ 
					model.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_unit_approval");
				}
				if (validation.sus_noLength(sus1) == false) {
					model.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_unit_approval");
				}
			/*	 if( cmd1 == null || cmd1.equals("")){ 
						model.put("msg", "Command Name Should not be Null");
						return new ModelAndView("redirect:mnh_unit_approval");
					}*/
				
			}
		}
			
		List<Map<String, Object>> list = mnh5_Dao.search_approval_datastatus(cmd1, user1, mth_yr1, para1, sus1,frm_dt1,to_dt1);
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
		   sum=sum + (int) (long) list.get(i).get("app");
		}
		model.put("total",sum);
		int sum1=0;
		for (int i = 0; i < list.size(); i++) {
		   sum1=sum1 + (int) (long) list.get(i).get("reject");
		}
		model.put("total1",sum1);
		int sum2=0;
		for (int i = 0; i < list.size(); i++) {
		   sum2=sum2 + (int) (long) list.get(i).get("total_bal");
		}
		model.put("total2",sum2);
		int sum3=0;
		for (int i = 0; i < list.size(); i++) {
		   sum3=sum3 + (int) (long) list.get(i).get("still");
		}
		model.put("total3",sum3);
		int sum4=0;
		for (int i = 0; i < list.size(); i++) {
		   sum4=sum4 + (int) (long) list.get(i).get("total");
		}
		model.put("total4",sum4);
		model.put("list", list);
		model.put("size", list.size());
		model.put("sus1", sus1);
		model.put("unit1", unit1);
		model.put("cmd1", cmd1);
		model.put("user1", user1);
		model.put("mth_yr1", mth_yr1);
		model.put("frm_dt1", frm_dt1);
		model.put("to_dt1", to_dt1);
		
		if (para1.equalsIgnoreCase("UNIT")) {
			model.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
			return new ModelAndView("mnh_unit_approvalTiles");
		}
		if (para1.equalsIgnoreCase("MISO")) {
			model.put("ml_2", mcommon.getMNHUserList("", session));
			return new ModelAndView("mnh_miso_approvalTiles");
		}
		return null;
	}
	// (2),(3)-> (APPROVAL UNIT & MISO STATUS SCREEN METHODS END)
	@RequestMapping(value = "/admin/mnh_datastatusSearch", method = RequestMethod.GET)
	public ModelAndView mnh_datastatusSearch(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_datastatusSearch", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		Mmap.put("ml_1", mnh1_Dao.getMNHDistinctHirarName("COMMAND", "ALL", "COMMAND"));
		Mmap.put("msg", msg);
		return new ModelAndView("mnh_datastatusSearchTiles");
	}
		
	@RequestMapping(value = "/search_Datastatus", method = RequestMethod.POST)
	public ModelAndView search_Datastatus(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@ModelAttribute("sus1") String sus1, String unit1,String frm_dt1, String to_dt1) {
	
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mnh_datastatusSearch", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		
		List<Map<String, Object>> list = mnh5_Dao.search_datastatus(sus1,frm_dt1, to_dt1);
		
		
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
		    sum=sum + (int) (long) list.get(i).get("count");
		}
		model.put("total",sum);
		model.put("list", list);
		model.put("size", list.size());
		model.put("sus1", sus1);
		model.put("unit1", unit1);
		model.put("frm_dt1", frm_dt1);
		model.put("to_dt1", to_dt1);
		
		return new ModelAndView("mnh_datastatusSearchTiles");
	}
	
}
