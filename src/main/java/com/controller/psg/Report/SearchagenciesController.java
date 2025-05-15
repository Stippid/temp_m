package com.controller.psg.Report;

import java.sql.SQLException;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Queries.Blood_Group_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.search_agencies_formationDao;
import com.models.Tbl_CodesForm;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class SearchagenciesController {
	
	Blood_Group_Controller bloodGP = new Blood_Group_Controller();
	Psg_CommonController mcommon = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	
	@Autowired
	search_agencies_formationDao cd;
	

	ValidationController valid = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	
	@RequestMapping(value = "/admin/SearchagenciesFormationOfficer_url", method = RequestMethod.GET)
	public ModelAndView SearchagenciesFormationOfficer_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId)throws SQLException {
		
			String roleid = sessionUserId.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchagenciesFormationOfficer_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		
		String sus_no = sessionA.getAttribute("roleSusNo").toString();
		String  roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String  username = sessionUserId.getAttribute("username").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= bloodGP.getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=bloodGP.getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=bloodGP.getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
			}
		}
		 if(roleAccess.equals("Unit")){
			 String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));
				
				List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
				roleFormationNo = formation.get(0);
				
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=bloodGP.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=bloodGP.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=bloodGP.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = bloodGP.getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				
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
		Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
		Mmap.put("getAgencyList", mcommon.getAgencyList());
		Mmap.put("msg", msg);
		
		

		
		return new ModelAndView("Search_agencies_officer_tile");
	
	}

	 @RequestMapping(value = "/admin/Searchagenciesbattle", method = RequestMethod.POST)
		public ModelAndView Searchagenciesbattle(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			    @RequestParam(value = "rank1", required = false) String rank,
			    @RequestParam(value = "dt_of_casuality1", required = false) String dt_of_casuality,
			    @RequestParam(value = "scase1", required = false) String scase,
			    @RequestParam(value = "agency_id1", required = false) String agency_id,
			    @RequestParam(value = "type_of_benefits_id1", required = false) String type_of_benefits_id,HttpServletRequest request) {
		 
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SearchagenciesFormationOfficer_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

				String roleType = session.getAttribute("roleType").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				
				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session) .get(0));


				}
					
				Mmap.put("personnel_no1", personnel_no);
				Mmap.put("rank1", rank);
				Mmap.put("dt_of_casuality1", dt_of_casuality);
				Mmap.put("scase1", scase);
				Mmap.put("agency_id1", agency_id);
				Mmap.put("type_of_benefits_id1", type_of_benefits_id);
				
			Mmap.put("getTypeofRankList", mcommon.getTypeofRankList());
			    
			return new ModelAndView("Search_agencies_officer_tile");
		}
	 
	 
	 
	
	 
	 //////////////////////////////////	
		@RequestMapping(value = "/getagenciesdatasearchdetail", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getagenciesdatasearchdetail(int startPage,int pageLength,String Search,String orderColunm,String orderType,String personnel_no,String rank,HttpSession sessionUserId,
				String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id) throws SQLException {
			
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
			String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
			
			if(roleAccess.equals("Emoluments")) {
				if(roleSubAccess.equals("Command")) {
					String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
					cont_comd = fcode_comd;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_comd+cont_bde.substring(1, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_comd+cont_div.substring(1, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
				    		}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_comd;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Corps")) {
					String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
					cont_corps = fcode_corps;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_corps+cont_bde.substring(3, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_corps+cont_div.substring(3, 6);
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_corps;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_corps;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Division")) {
					String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					cont_div = fcode_div;
					
					if(!cont_bde.equals("0") && !cont_bde.equals("")){
						cont_bde = fcode_div+cont_bde.substring(6, 10);
			    	}else {
			    		if(!cont_div.equals("0") && !cont_div.equals("")){
			    			cont_div = fcode_div;
				    	}else {
				    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
				    			cont_corps = fcode_div;
					    	}else {
					    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
					    			cont_comd = fcode_div;
						    	}
					    	}
					    }
				    }
				}
				if(roleSubAccess.equals("Brigade")) {
					cont_bde = roleFormationNo;
				}
			}
			
			
			return cd.getallSearchagencies(startPage, pageLength, Search, orderColunm, orderType, personnel_no,rank,
					 sessionUserId,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,dt_of_casuality,scase,agency_id,type_of_benefits_id);
		}
	 
		@RequestMapping(value = "/getagenciesCount", method = RequestMethod.POST)
		public @ResponseBody long getagenciesCount(String Search,String orderColunm,String orderType,String personnel_no,String rank,String b_code,HttpSession sessionUserId,
				String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String dt_of_casuality,String scase,String agency_id,String type_of_benefits_id) throws SQLException {
			
		
			return cd.getSearchagenciesCount(Search, orderColunm, orderType,personnel_no,rank,
					 sessionUserId,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,dt_of_casuality,scase,agency_id,type_of_benefits_id);
		}
	 /////////////////////
	/*	@RequestMapping(value = "/view_Emoluments_History_formationUrl", method = RequestMethod.POST)
		public ModelAndView view_Emoluments_History_formationUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
				@RequestParam(value = "comm_id_a", required = false) int comm_id) {
			List<ArrayList<String>> list = cd.View_Emoluments_History_formation(comm_id);
			Mmap.put("list", list);
			Mmap.put("msg", msg);
			return new ModelAndView("View_Emoluments_History_formation_Tiles");
		}*/
}
