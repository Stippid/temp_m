package com.controller.psg.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.controller.ExportFile.ActualStr_OFFR;
import com.controller.ExportFile.Identity_Details;
import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.SearchByIdentityDao;
import com.dao.psg.Transaction.CensusAprovedDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class SearchByIdentity_Controller {
	
	Psg_CommonController p_comm = new Psg_CommonController();
	@Autowired
	SearchByIdentityDao searchByIdDao;
	
	@Autowired

	CensusAprovedDAO censusAprovedDAO;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/searchByIdentityUrl", method = RequestMethod.GET)
	public ModelAndView searchByIdentityUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, 			
			HttpServletRequest request,HttpSession sessionUserId) {

			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("searchByIdentityUrl", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	
		Mmap.put("msg", msg);
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());		
		return new ModelAndView("search_by_IdentityTiles");
	}
	
	@RequestMapping(value = "/searchByIdentityUrl" , method = RequestMethod.POST)
	public ModelAndView searchByIdentityActionUrl(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchByIdentityUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String ser_cat=request.getParameter("service_category");
		String pr_no=request.getParameter("personnel_no");

		String id_no=request.getParameter("id_card_no");

		List<Map<String, Object>> list = searchByIdDao.search_identity(ser_cat,id_no,pr_no);
		model.put("ser_cat", ser_cat);
		model.put("pr_no", pr_no);
		model.put("id_no", id_no);
		model.put("size", list.size());
		model.put("getServiceCategoryList", p_comm.getServiceCategoryList());	
		model.put("list", list);
		return new ModelAndView("search_by_IdentityTiles");
	}
	
	
	 @RequestMapping(value = "/searchByIdentityPrint", method = RequestMethod.POST)
		public ModelAndView searchByIdentityPrint(ModelMap Mmap, HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
			
		 String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("searchByIdentityUrl", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
			String ser_cat=request.getParameter("service_categoryPrint");
			String pr_no=request.getParameter("personnel_noPrint");

			String id_no=request.getParameter("id_card_noPrint");
			String typeReport1=request.getParameter("typeReport1");

			List<Map<String, Object>> list = searchByIdDao.search_identity(ser_cat,id_no,pr_no);
			String filePath ="";
			if(ser_cat.equals("1"))
				if(list.size()>0)
					filePath= censusAprovedDAO.getcensusIdentityImagePath(list.get(0).get("id").toString());
			
			if(filePath==null || filePath.equals(""))
				filePath =  request.getRealPath("/")+"admin\\js\\img\\No_Image.jpg";
		
			if(list.size()>0) 
			list.get(0).put("path", filePath);
		
		
			if (list.size() == 0) {
				
				Mmap.put("msg", "Data Not Available.");
			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());
			
				if (typeReport1 != null && typeReport1.equals("pdfL")) {
					if (list.size() > 0) {
						List<String> TH = new ArrayList<String>();

					TH.add("No");
					TH.add("Rank");
					TH.add("Name");
					TH.add("Date of Birth");
					
					
					Date date = new Date();
				
						
						String Heading ="\n ACTUAL STR OF OFFRS BY USER ARMS/ FMN AND PARENT ARM/SERVICES \n  Reported On: "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						String username = session.getAttribute("username").toString();
						String foot = " Report Generated On "+ new SimpleDateFormat("yyyy-MM-dd").format(date);
						return new ModelAndView(new Identity_Details("L", TH, Heading, username,foot), "userList", list);
					}
				}
			}
			
			
			Mmap.put("ser_cat", ser_cat);
			Mmap.put("pr_no", pr_no);
			Mmap.put("id_no", id_no);
			Mmap.put("size", list.size());
			Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());	
			Mmap.put("list", list);
			return new ModelAndView("search_by_IdentityTiles");
		}
}
