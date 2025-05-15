package com.controller.tms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.MVCRDAO;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.T_Domain_Value;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class B_VehSearchMVCRController {
	@Autowired
	private MVCRDAO addmctDAO; 
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value = "/admin/search_mvcr", method = RequestMethod.GET)
	public ModelAndView search_mvcr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		if(roleAccess.equals("Unit")){
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",allOrbat.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
		}
		if(request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_mvcrTile");
	}
	
	@RequestMapping(value = "/admin/SearchAttributeReportMvcr", method = RequestMethod.POST)
	public ModelAndView SearchAttributeReportMvcr(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_noSer", required = false) String sus_no,
			@RequestParam(value = "statusSer", required = false) String status,
			@RequestParam(value = "unit_nameSer", required = false) String unit_name)
	{
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(status != "") {
			Mmap.put("status", status);
		}
		if(!sus_no.equals("") & sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			
			if(allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).size()>0)
			{
				Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			}

			ArrayList<List<String>> list =	addmctDAO.getSearchAttributeReportMvcr(status,sus_no,roleType);	
			if(list.size() == 0)
			{
				Mmap.put("list", list);
			}
			else
			{
				Mmap.put("roleType", roleType);
				Mmap.put("list", list);
				Mmap.put("unit_name12", list.get(0).get(1));
			}
		}	
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
	    } 
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:search_mvcr");
		}	
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:search_mvcr");
		}
		return new ModelAndView("search_mvcrTile");
	}

	@RequestMapping(value = "/admin/search_unit_holding_details", method = RequestMethod.GET)
	public ModelAndView search_unit_holding_details(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_unit_holding_details", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_unit_holding_detailsTile");
	}
	
	@RequestMapping(value = "/admin/search_unit_holding_detailsList", method = RequestMethod.POST)
	public ModelAndView search_unit_holding_detailsList(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name)
	{	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_unit_holding_details", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		if(!sus_no.equals("") & sus_no.length() == 8) {
			ArrayList<ArrayList<String>> list = addmctDAO.search_unit_holding_detailsList(sus_no);
		 	if(list.size() == 0)
			{
		 		Mmap.put("list", list);
			}
			else
			{
				int sumUE = 0;
				int againUE = 0;
				int overUE = 0;
				int loan = 0;
				int sector = 0;
				int acsfp = 0;
				int freash_release = 0;
				int gift = 0;
				int totalUH = 0;
				for(int i=0;i<list.size();i++) {
					String sumUE1 = list.get(i).get(2);
					if(sumUE1 == null) {
						sumUE1 = "0";
					}
					sumUE = sumUE + Integer.parseInt(sumUE1);
					againUE = againUE + Integer.parseInt( list.get(i).get(3));
					overUE = overUE + Integer.parseInt(list.get(i).get(4));
					loan = loan + Integer.parseInt(list.get(i).get(5));
					sector = sector + Integer.parseInt(list.get(i).get(6));
					acsfp = acsfp + Integer.parseInt(list.get(i).get(7));
					freash_release = freash_release + Integer.parseInt(list.get(i).get(8));
					gift = gift + Integer.parseInt(list.get(i).get(9));
					totalUH = totalUH + Integer.parseInt(list.get(i).get(10));
				}
				Mmap.put("sumUE",sumUE);
				Mmap.put("againUE",againUE);
				Mmap.put("overUE",overUE);
				Mmap.put("loan",loan);
				Mmap.put("sector",sector);
				Mmap.put("acsfp",acsfp);
				Mmap.put("freash_release",freash_release);
				Mmap.put("gift",gift);
				Mmap.put("totalUH",totalUH);
				Mmap.put("list", list);
				Mmap.put("sus_no", sus_no);
				Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
			}
		}
		else if(sus_no.equals("") || sus_no.equals(null)|| sus_no == "" || sus_no == null){
			Mmap.put("msg", "Please Select SUS No.");
	    } 
		else if(validation.sus_noLength(sus_no) == false){
			Mmap.put("msg",validation.sus_noMSG);
			return new ModelAndView("redirect:search_unit_holding_details");
		}
		
		if(validation.checkUnit_nameLength(unit_name) == false){
			Mmap.put("msg",validation.unit_nameMSG);
			return new ModelAndView("redirect:search_unit_holding_details");
		}
	 	return new ModelAndView("search_unit_holding_detailsTile");
	}
	
	@RequestMapping(value = "/generate_baNo_presentCost", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> generate_baNo_presentCost(String sus_no,String mct,HttpSession sessionA){
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		return addmctDAO.generate_baNo_presentCost(sus_no, mct);
	}
	
	@RequestMapping(value = "/admin/ViewMVCRUrl",method = RequestMethod.POST)
	public ModelAndView ViewMVCRUrl(@ModelAttribute("sus_noV") String sus_no,String unit_nameV,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA,Authentication authentication){
	
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		if(!sus_no.equals("")) {
			model.put("sus_no", sus_no);
		}
		if(!unit_nameV.equals("")) {
			model.put("unit_name", unit_nameV);
		}
		ArrayList<List<String>> getMVCRReportList = addmctDAO.getMVCRReportListForApproval(sus_no, roleType);
		model.put("getMVCRReportList", getMVCRReportList);
		return new ModelAndView("view_search_mvcrTile");
	}
	
	@RequestMapping(value = "/admin/ApprovedmvcrUrl",method = RequestMethod.POST)
	public ModelAndView ApprovedmvcrUrl(@ModelAttribute("sus_no2") String sus_no,@ModelAttribute("unit_name1") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
        if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		String  username = sessionA.getAttribute("username").toString();
		model.put("msg", addmctDAO.setApprovedmvcr(sus_no,username));	
		return new ModelAndView("further_mvcr_detailsTile");
	}
	

	@RequestMapping(value = "/admin/rejectmvcrUrl",method = RequestMethod.POST)
	public ModelAndView rejectmvcrUrl(@ModelAttribute("sus_no3") String sus_no,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("APP") & !roleType.equals("ALL")) {
                return new ModelAndView("AccessTiles");
        }
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("msg", addmctDAO.setRejectmvcr(sus_no));	
		return new ModelAndView("redirect:search_mvcr");
	}
	
	@RequestMapping(value = "/admin/MVCR_partA",method = RequestMethod.POST)
	public ModelAndView MVCR_partA(@ModelAttribute("sus_no1") String sus_no,ModelMap model,@ModelAttribute("unit_name1") String unit_name,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("partAMvcrList", addmctDAO.getMVCR_PartA_List(sus_no));
		//model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
		
		List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			
			/*if(formation.get(0).get("bde_name").equals("-")) {
				if(formation.get(0).get("div_name").equals("-")) {
					if(formation.get(0).get("coprs_name").equals("-")) {
						model.put("fmn",formation.get(0).get("cmd_name"));
					}else {
						model.put("fmn",formation.get(0).get("coprs_name"));
					}
				}else {
					model.put("fmn",formation.get(0).get("div_name"));
				}
			}else {
				model.put("fmn",formation.get(0).get("bde_name"));
			}*/
		}
		
		List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			model.put("app", app);
		}
		if(modi != null) {
			model.put("modi", modi);
		}
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
		}
		model.put("sus_no", sus_no);
		return new ModelAndView("MVCR_partATile");
	}
	
	//////////////part B/////////
	@RequestMapping(value = "/admin/MVCR_partB",method = RequestMethod.POST)
	public ModelAndView MVCR_partB(@ModelAttribute("sus_nob") String sus_no,@ModelAttribute("unit_nameb") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("sus_no", sus_no);
		model.put("unit_name", unit_name);
		return new ModelAndView("MVCR_partBTile");
	}
	

	/*   NITIN V4 18/11/2022  */
	@RequestMapping(value = "/admin/Details_UE_UH",method = RequestMethod.POST)
		public ModelAndView Detalis_UE_UH(@ModelAttribute("sus_no1") String sus_no,ModelMap model,@ModelAttribute("unit_name1") String unit_name,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
			String app = null;
			if(dateList.get(0).getApprove_date() != null) {
				app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
			}
			String modi =null;
			if(dateList.get(0).getModify_date() != null) {
				modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
			}
			if(app != null) {
				model.put("app", app);
			}
			if(modi != null) {
				model.put("modi", modi);
			}
			ArrayList<List<String>> partBMvcrDetails_UE_UHList = addmctDAO.getMVCR_PartB_DETAILS_UE_UH_List(sus_no);
			model.put("partBMvcrDetails_UE_UHList", partBMvcrDetails_UE_UHList);
			
			List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
			if(formation.size() > 0) {
				model.put("unit_name",formation.get(0).get("unit_name"));
				model.put("modification",formation.get(0).get("mod"));
				model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
				model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			}
			
			List<String> wepe = addmctDAO.getwepeno(sus_no);
			if(wepe.size() != 0) {
				model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
			}
			model.put("sus_no", sus_no);
			if(partBMvcrDetails_UE_UHList.size() > 0){
		 		int sumUE = 0;
				int againUE = 0;
				int overUE = 0;
				int loan = 0;
				int sector = 0;
				int acsfp = 0;
				int pbd = 0;
				int fresh_release = 0;
				int gift = 0;
				int totalUH = 0;
				int defi = 0;
				int surplus = 0;
				int op = 0;
				int trg = 0;
				int wwr = 0;
				int opwks = 0;
				int other = 0;
				for(int i=0;i<partBMvcrDetails_UE_UHList.size();i++) {
					String sumUE1 = partBMvcrDetails_UE_UHList.get(i).get(6);
					if(sumUE1 == null) {
						sumUE1 = "0";
					}
					sumUE = sumUE + Integer.parseInt(sumUE1);
					againUE = againUE + Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(7)) + Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(8));
					overUE = overUE + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(9)) + Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(10));
					loan = loan + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(11)) + Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(12));
					sector = sector + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(13)) +Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(14));
					acsfp = acsfp + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(15))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(16));
					pbd = pbd + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(17))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(18));
					fresh_release = fresh_release + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(19));
					gift = gift + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(20)) + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(21));
					totalUH = totalUH + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(24));
					defi = defi + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(25));
					surplus = surplus + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(26));
					op = op + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(27))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(28));
					trg = trg + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(29))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(30));
					wwr = wwr + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(31))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(32));
					opwks = opwks + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(33))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(34));
					other = other + Integer.parseInt(partBMvcrDetails_UE_UHList.get(i).get(35))+Integer.parseInt( partBMvcrDetails_UE_UHList.get(i).get(36));
				}
				model.put("sumUE",sumUE);
				model.put("againUE",againUE);
				model.put("overUE",overUE);
				model.put("loan",loan);
				model.put("sector",sector);
				model.put("acsfp",acsfp);
				model.put("pbd",pbd);
				model.put("fresh_release",fresh_release);
				model.put("gift",gift);
				model.put("totalUH",totalUH);
				model.put("defi",defi);
				model.put("surplus",surplus);
				model.put("op",op);
				model.put("trg",trg);
				model.put("wwr",wwr);
				model.put("opwks",opwks);
				model.put("other",other);
			}
			return new ModelAndView("MVCR_partBDetailsUETile");
		}


	
	@RequestMapping(value = "/admin/MCTwise_UE_UH",method = RequestMethod.POST)
	public ModelAndView MCTwise_UE_UH(@ModelAttribute("sus_no1") String sus_no,ModelMap model,@ModelAttribute("unit_name1") String unit_name,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		ArrayList<ArrayList<String>> partBMvcrMCTwise_UE_UHList = addmctDAO.getAttributeFromMCTwiseMvcr(sus_no);
		model.put("partBMvcrMCTwise_UE_UHList", partBMvcrMCTwise_UE_UHList);
		List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
		}
		
		List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			model.put("app", app);
		}
		if(modi != null) {
			model.put("modi", modi);
		}
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
		}
		model.put("sus_no", sus_no);
		
		int sumUE = 0;
		int totalUH = 0;
		for(int i=0;i<partBMvcrMCTwise_UE_UHList.size();i++) {
			String sumUE1 = partBMvcrMCTwise_UE_UHList.get(i).get(2);
			if(sumUE1 == null) {
				sumUE1 = "0";
			}
			sumUE = sumUE + Integer.parseInt(sumUE1);
			totalUH = totalUH + Integer.parseInt(partBMvcrMCTwise_UE_UHList.get(i).get(3));
		}
		model.put("sumUE",sumUE);
		model.put("totalUH",totalUH);
		return new ModelAndView("MCTwise_UE_UHTile");
	}
	
	@RequestMapping(value = "/admin/prfwise_UH",method = RequestMethod.POST)
	public ModelAndView prfwise_UH(@ModelAttribute("sus_no1") String sus_no,ModelMap model,@ModelAttribute("unit_name1") String unit_name,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		ArrayList<ArrayList<String>> partBMvcrPRFwise_UE_UHList = addmctDAO.getAttributeprfwiseMvcr(sus_no);
		model.put("partBMvcrPRFwise_UE_UHList", partBMvcrPRFwise_UE_UHList);
		//model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
		List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			/*if(formation.get(0).get("bde_name").equals("-")) {
				if(formation.get(0).get("div_name").equals("-")) {
					if(formation.get(0).get("coprs_name").equals("-")) {
						model.put("fmn",formation.get(0).get("cmd_name"));
					}else {
						model.put("fmn",formation.get(0).get("coprs_name"));
					}
				}else {
					model.put("fmn",formation.get(0).get("div_name"));
				}
			}else {
				model.put("fmn",formation.get(0).get("bde_name"));
			}*/
		}
		
		List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			model.put("app", app);
		}
		if(modi != null) {
			model.put("modi", modi);
		}
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
		}
		model.put("sus_no", sus_no);
		int sumUE = 0;
		int totalUH = 0;
		for(int i=0;i<partBMvcrPRFwise_UE_UHList.size();i++) {
			String sumUE1 = partBMvcrPRFwise_UE_UHList.get(i).get(2);
			if(sumUE1 == null) {
				sumUE1 = "0";
			}
			sumUE = sumUE + Integer.parseInt(sumUE1);
			totalUH = totalUH + Integer.parseInt(partBMvcrPRFwise_UE_UHList.get(i).get(3));
		}
		model.put("sumUE",sumUE);
		model.put("totalUH",totalUH);
		return new ModelAndView("prfwise_UHTile");
	}
	
	@RequestMapping(value = "/admin/MVCR_partC",method = RequestMethod.POST)
	public ModelAndView MVCR_partC(@ModelAttribute("sus_noc") String sus_no,@ModelAttribute("unit_namec") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}	
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		model.put("sus_no", sus_no);
		//model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
		List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			/*if(formation.get(0).get("bde_name").equals("-")) {
				if(formation.get(0).get("div_name").equals("-")) {
					if(formation.get(0).get("coprs_name").equals("-")) {
						model.put("fmn",formation.get(0).get("cmd_name"));
					}else {
						model.put("fmn",formation.get(0).get("coprs_name"));
					}
				}else {
					model.put("fmn",formation.get(0).get("div_name"));
				}
			}else {
				model.put("fmn",formation.get(0).get("bde_name"));
			}*/
		}
		
		List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			model.put("app", app);
		}
		if(modi != null) {
			model.put("modi", modi);
		}
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
		}
		model.put("getMvcrpartCissuetypeList",getMvcrpartCissuetypeList());
		return new ModelAndView("MVCR_partCTile");
	}		
	
	@RequestMapping(value = "/admin/MVCR_E_Asset",method = RequestMethod.POST)
	public ModelAndView MVCR_E_Asset(@ModelAttribute("sus_noe") String sus_no,@ModelAttribute("unit_namee") String unit_name,ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_mvcr", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		ArrayList<ArrayList<String>> partBMvcrE_Asset_UHList = addmctDAO.getAttributeFromMVCR_E_Asset(sus_no);
		model.put("partBMvcrE_Asset_UHList", partBMvcrE_Asset_UHList);
		//model.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no,sessionA).get(0));
		List<Map<String, Object>> formation = addmctDAO.getFormationDetailsFromSusNo(sus_no);
		if(formation.size() > 0) {
			model.put("unit_name",formation.get(0).get("unit_name"));
			model.put("modification",formation.get(0).get("mod"));
			model.put("fmn",formation.get(0).get("cmd_name")+"/"+formation.get(0).get("coprs_name")+"/"+formation.get(0).get("div_name")+"/"+formation.get(0).get("bde_name"));
			model.put("loc_nrs",formation.get(0).get("loc")+"("+formation.get(0).get("nrs")+")");
			/*if(formation.get(0).get("bde_name").equals("-")) {
				if(formation.get(0).get("div_name").equals("-")) {
					if(formation.get(0).get("coprs_name").equals("-")) {
						model.put("fmn",formation.get(0).get("cmd_name"));
					}else {
						model.put("fmn",formation.get(0).get("coprs_name"));
					}
				}else {
					model.put("fmn",formation.get(0).get("div_name"));
				}
			}else {
				model.put("fmn",formation.get(0).get("bde_name"));
			}*/
		}
		
		List<TB_TMS_MVCR_PARTA_MAIN> dateList = addmctDAO.getApproveDate(sus_no);
		String app = null;
		if(dateList.get(0).getApprove_date() != null) {
			app =  new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getApprove_date());
		}
		String modi =null;
		if(dateList.get(0).getModify_date() != null) {
			modi = new SimpleDateFormat("dd-MM-yyyy").format(dateList.get(0).getModify_date());
		}
		if(app != null) {
			model.put("app", app);
		}
		if(modi != null) {
			model.put("modi", modi);
		}
		List<String> wepe = addmctDAO.getwepeno(sus_no);
		if(wepe.size() != 0) {
			model.put("wep", addmctDAO.getwepeno(sus_no).get(0));
		}
		model.put("sus_no", sus_no);
		int totalUH = 0;
		double PRECOST = 0.0;
		double TOTAL = 0;
		for(int i=0;i<partBMvcrE_Asset_UHList.size();i++) {
			String PRECOST1 = partBMvcrE_Asset_UHList.get(i).get(2);
			if(PRECOST1 == null) {
				PRECOST1 = "0";
			}
			PRECOST = PRECOST + Double.parseDouble(PRECOST1);
			totalUH = totalUH + Integer.parseInt(partBMvcrE_Asset_UHList.get(i).get(3));
		}
		model.put("PRECOST",PRECOST);
		model.put("totalUH",totalUH);
		TOTAL = PRECOST * totalUH;
		model.put("TOTAL",TOTAL);
		return new ModelAndView("MVCR_E_AssetTile");
	}
	
	@RequestMapping(value = "/getAttributeFromMVCR_E_Asset")
	public @ResponseBody ArrayList<ArrayList<String>> getAttributeFromMVCR_E_Asset(String sus_no,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		return addmctDAO.getAttributeFromMVCR_E_Asset(sus_no);
	}
	
	@RequestMapping(value = "/getMvcrpartCList", method = RequestMethod.POST)
	public @ResponseBody List<String> getMvcrpartCList(String sus_no,String issue_type,HttpSession sessionA) {	
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
	 	return addmctDAO.getMvcrpartCList(sus_no,issue_type);
	}
	
	////-----------------------priti----25-08-2020 In Observation they want other option and also want to remove acsf,sector store.----------------//
	@RequestMapping(value = "/getMvcrpartCissuetypeList")
	public @ResponseBody List<T_Domain_Value> getMvcrpartCissuetypeList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid='VEHICLEISSUETYPE' and codevalue not in ('2','7','8','6')");
		@SuppressWarnings("unchecked")
		List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/generate_ba_no", method = RequestMethod.POST)
	public @ResponseBody List<String> getBA_noFromIssueType(String sus_no,String mct,String issue_type,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		return addmctDAO.getBA_noFromIssueType(sus_no,mct,issue_type);
	}
}
