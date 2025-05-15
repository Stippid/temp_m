package com.controller.cue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.link_we_wetDAO;
import com.dao.cue.link_we_wetDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.cue_wepe;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class search_we_link_wetController {	
	
	@Autowired
	private Cue_wepe_conditionDAO wepedolink;
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	ValidationController validation = new ValidationController();
	
	link_we_wetDAO WElinkWET = new link_we_wetDAOImpl();
			
	@RequestMapping(value = "/admin/search_we_link_wet", method = RequestMethod.GET)
	public ModelAndView search_we_link_wet(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	 	String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_we_link_wet", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_we_link_wetTiles");
	} 	 
	 

	 	@RequestMapping(value = "/admin/search_we_link_wet1", method = RequestMethod.POST)
		public ModelAndView search_we_link_wet1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "we_pe02", required = false) String we_pe,
				@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
				@RequestParam(value = "wet_pet_no1", required = false) String wet_pet_no,
				@RequestParam(value = "wet_link_status1", required = false) String wet_link_status){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleid = session.getAttribute("roleid").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				
				Mmap.put("wet_link_status1", wet_link_status);
				Mmap.put("we_pe02", we_pe);
				Mmap.put("we_pe_no1", we_pe_no);
				Mmap.put("wet_pet_no1", wet_pet_no);
				 List<Map<String, Object>> list =wepedolink.getWEPElinkwithWETPET1(we_pe,we_pe_no,wet_link_status,roleType,roleAccess,roleSusNo);
			Mmap.put("list", list);
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("search_we_link_wetTiles");
		}
		
	@RequestMapping(value = "/admin/Approved_WE_linkwith_WET_Url", method=RequestMethod.POST)
	public ModelAndView Approved_WE_linkwith_WET_Url(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe03", required = false) String we_pe,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "wet_link_status2", required = false) String wet_link_status){
		String roleType = session.getAttribute("roleType").toString();
		String username = session.getAttribute("username").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		Mmap.put("msg", WElinkWET.setApprovedWELinkwithWET(appid, username));
		Mmap.put("wet_link_status1", wet_link_status);
		Mmap.put("we_pe02", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		
		 List<Map<String, Object>> list =wepedolink.getWEPElinkwithWETPET1(we_pe,we_pe_no, wet_link_status,roleType,roleAccess,roleSusNo);
		 Mmap.put("list", list);
		 Mmap.put("roleType", roleType);
		 Mmap.put("list.size()", list.size());
	return new ModelAndView("search_we_link_wetTiles");
	}
	
	@RequestMapping(value = "/admin/reject_WE_linkwith_WET_Url", method=RequestMethod.POST)
	public ModelAndView reject_WE_linkwith_WET_Url(@ModelAttribute("rejectid") int rejectid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe04", required = false) String we_pe,
		@RequestParam(value = "we_pe_no4", required = false) String we_pe_no,
		@RequestParam(value = "wet_link_status4", required = false) String wet_link_status){
		Mmap.put("msg", WElinkWET.setRejectWELinkwithWET(rejectid));	
		Mmap.put("wet_link_status1", wet_link_status);
		Mmap.put("we_pe02", we_pe);
		Mmap.put("we_pe_no1", we_pe_no);
		
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		 List<Map<String, Object>> list =wepedolink.getWEPElinkwithWETPET1(we_pe,we_pe_no, wet_link_status,roleType,roleAccess,roleSusNo);
		 Mmap.put("list", list);
		 Mmap.put("roleType", roleType);
		 Mmap.put("list.size()", list.size());
		return new ModelAndView("search_we_link_wetTiles");
	}
	
	@RequestMapping(value = "/delete_WE_linkwith_WET_UrlAdd",method = RequestMethod.POST)
	public @ResponseBody List<String> delete_WE_linkwith_WET_UrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(WElinkWET.setDeleteWELinkwithWET(deleteid));
		return list2;
	}

	@RequestMapping(value = "/update_WE_linkwith_WET_Url")
	public ModelAndView update_WE_linkwith_WET_Url(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("EditWE_link", WElinkWET.getWELinkwithWETByid(updateid));	
		model.put("msg", msg);
		return new ModelAndView("Editlink_we_wetTiles");
	}
	
	@RequestMapping(value = "/EditWE_link_with_WET_Action", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView cue_wepe(@ModelAttribute("EditWE_link") cue_wepe updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
			
			String wet_pet = request.getParameter("wet_pet");			
			if(wet_pet.equals("") || wet_pet == "null" || wet_pet.equals(null) || wet_pet.isEmpty())
			{
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Select WET/PET/FET Document");
				return new ModelAndView("redirect:update_WE_linkwith_WET_Url");
			}	
			String wet_pet_no = request.getParameter("wet_pet_no");			
			if(wet_pet_no.equals("") || wet_pet_no == "null" || wet_pet_no.equals(null) || wet_pet_no.isEmpty())
			{
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter WET/PET/FET Document No");
				return new ModelAndView("redirect:update_WE_linkwith_WET_Url");
			}	
			
			if(validation.checkWetPetLength(wet_pet_no)  == false)
			{
				model.put("msg",validation.wetpetnoMSG);
				return new ModelAndView("redirect:update_WE_linkwith_WET_Url");
			}

			String unit_visible = request.getParameter("unit_visible");			
			if(unit_visible.equals("") || unit_visible == "null" || unit_visible.equals(null) || unit_visible.isEmpty())
			{
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Select Visible to Unit or not?");
				return new ModelAndView("redirect:update_WE_linkwith_WET_Url");
			}	
			
			if(validation.checkParent_codeLength(unit_visible)  == false)
			{
				model.put("msg",validation.visibMSG);
				return new ModelAndView("redirect:update_WE_linkwith_WET_Url");					
			}
			String username = session.getAttribute("username").toString();
			WElinkWET.UpdateWELinkwithWET(updateid,username);		
			model.put("msg", "Updated Successfully");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ModelAndView("redirect:link_we_wet");
	}

}
