package com.controller.tms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
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

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Search_Ba_AllocDAO;
import com.dao.tms.Search_baDAO;
import com.dao.tms.tmsConversionRequestGstoSplVehDAO;
import com.models.TB_TMS_BANUM_DIRCTRY;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.Tms_Banum_Req_Child;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Common_Search_BA_No_AllocController {
	@Autowired
	private Search_Ba_AllocDAO pdao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Search_Ba_AllocDAO searchDao;

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/search_ba_alloUrl", method = RequestMethod.GET)
	public ModelAndView search_prf_mstUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_ba_allotile");
	}

	@RequestMapping(value = "/admin/search_ba_no_allocation", method = RequestMethod.POST)
	public ModelAndView search_ba_no_allocation(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "type_of_req1", required = false) String type_of_req,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "date1", required = false) String date,
			@RequestParam(value = "veh_cat1", required = false) String veh_cat,
			@RequestParam(value = "ba_no_type1", required = false) String ba_no_type,
			@RequestParam(value = "vehicle_clas_code1", required = false) String vehicle_clas_code) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}
		
		String roleType = session.getAttribute("roleType").toString();
		if (status != "") {
			Mmap.put("status1", status);
		}
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:search_ba_alloUrl");
			}
			Mmap.put("sus_no1", sus_no);
		}
		if (type_of_req != "") {
			if (validation.type_of_reqLength(type_of_req) == false) {
				Mmap.put("msg", validation.type_of_reqMSG);
				return new ModelAndView("redirect:search_ba_alloUrl");
			}
			Mmap.put("type_of_req1", type_of_req);
		}
		if (date != "") {
			Mmap.put("date1", date);
		}
		if (veh_cat != "") {
			Mmap.put("veh_cat1", veh_cat);
		}
		if (ba_no_type != "") {
			Mmap.put("ba_no_type1", ba_no_type);
		}
		if (vehicle_clas_code != "") {
			Mmap.put("vehicle_clas_code1", vehicle_clas_code);
		}
		ArrayList<List<String>> list = pdao.getSearchBA_Alloc(status, sus_no, type_of_req, date, veh_cat, ba_no_type,roleType,roleAccess,vehicle_clas_code);
		Mmap.put("list", list);
		
		if(status.equals("6")) {
			ArrayList<ArrayList<String>> printlist = pdao.getSearchBA_AllocPrint(status, sus_no, type_of_req, date, veh_cat,ba_no_type,roleAccess,vehicle_clas_code);
			Mmap.put("printlist", printlist);
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_ba_allotile");
	}

	@RequestMapping(value = "/VettedBA_NOUrl")
	public ModelAndView VettedBA_NOUrl(@ModelAttribute("Vettedid") int Vettedid,
			@ModelAttribute("p_Vettedid") int p_Vettedid, HttpSession session, ModelMap model,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("Tms_Banum_Req_ChildCmd", pdao.getTms_Banum_Req_Childid(Vettedid));
		model.put("Tms_Banum_Req_PrntCmd", pdao.getTms_Banum_Req_Prntid(p_Vettedid));
		model.put("Tms_Banum_Req_engin_chasisCmd", pdao.getTms_Banum_Req_engin_chasisid(p_Vettedid));
		model.put("msg", msg);
		return new ModelAndView("banum_dirctryVetTile");
	}

	@RequestMapping(value = "/viewBA_NOUrl", method = RequestMethod.POST)
	public ModelAndView updatePersonal_EntitalUrl(@ModelAttribute("viewid") int viewid, HttpSession session,
			@ModelAttribute("p_viewid") int p_viewid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("Tms_Banum_Req_ChildCmd", pdao.getTms_Banum_Req_Childid(viewid));
		model.put("Tms_Banum_Req_PrntCmd", pdao.getTms_Banum_Req_Prntid(p_viewid));
		model.put("Tms_Banum_Req_engin_chasisCmd", pdao.getTms_Banum_Req_engin_chasisid(p_viewid));
		String roleAccess = session.getAttribute("roleAccess").toString();
		model.put("roleAccess", roleAccess);
		
		return new ModelAndView("banum_dirctryViewTile");
	}

	@RequestMapping(value = "/ViewImageTMS", method = RequestMethod.GET)
	public void ViewImageTMS(@ModelAttribute("kmlFileId4") int id, @ModelAttribute("fildname") String fildname,
			ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session )
			throws IOException {
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		final int BUFFER_SIZE = 4096;
		String filePath = searchDao.getImagePath(id, fildname);
		model.put("filePath", filePath);
		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null || filePath.isEmpty() || filePath == "" || filePath == "null") {
				String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {
				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			String fullPath = request.getRealPath("/") + "admin\\js\\img\\No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}
	}

	@RequestMapping(value = "/admin/vetted_BA_noUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedItemArmUrl(@ModelAttribute("Tms_Banum_Req_ChildCmd") Tms_Banum_Req_Child tb_child,
			ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		int parent_req_id = tb_child.getParent_req_id();
		String Vehicle_clas_code = tb_child.getVehicle_clas_code();
		String child_req_id = request.getParameter("id_child");
		if (!request.getParameter("mct_number").equals("0000000000") & request.getParameter("mct_number") != null) {
			tb_child.setMct_number(new BigInteger(request.getParameter("mct_number")));
		} else {
			tb_child.setMct_number(new BigInteger("0"));
		}

		if (request.getParameter("ba_no_type") == null) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Select Army/Non-Army.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("veh_cat").equals("0") || request.getParameter("veh_cat").equals("")
				|| request.getParameter("veh_cat").equals("null") || request.getParameter("veh_cat").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Type of Vehicle.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("requesting_agency").equals("")
				|| request.getParameter("requesting_agency").equals("null")
				|| request.getParameter("requesting_agency").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Requesting Agency.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("dte_of_reqst").equals("")
				|| request.getParameter("dte_of_reqst").equals("null")
				|| request.getParameter("dte_of_reqst").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Select Date Of Request.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("sus_no").equals("") || request.getParameter("sus_no").equals("null")
				|| request.getParameter("sus_no").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if ((request.getParameter("ba_no_type").toString().equals("0"))
				&& (request.getParameter("mct_number").toString().equals("0")
						|| request.getParameter("mct_number").toString().equals("")
						|| request.getParameter("mct_number").toString().equals("null")
						|| request.getParameter("mct_number").toString().equals(null)
						|| request.getParameter("mct_number") == null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter MCT Number.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if ((request.getParameter("ba_no_type").toString().equals("0"))
				&& validation.mctLength(request.getParameter("mct_number")) == false) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", request.getParameter("id_child"));
			model.put("msg", validation.mctMSG);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("vehicle_clas_code").equals("")
				|| request.getParameter("vehicle_clas_code").equals("null")
				|| request.getParameter("vehicle_clas_code").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Vehicle Class Code.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (Vehicle_clas_code.equals("") || Vehicle_clas_code.equals("null") || Vehicle_clas_code.equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Class of Vehicle.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("year_of_entry").equals("")
				|| request.getParameter("year_of_entry").equals("null")
				|| request.getParameter("year_of_entry").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Yr of Entry into Service.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("tonnage"))) == false) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", validation.tonnageMSG);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("towing_capcty"))) == false) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", validation.towing_capacityMSG);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("lift_capcty"))) == false) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", validation.lift_capacityMSG);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (validation.checkUnit_nameLength(request.getParameter("sponsoring_dte")) == false) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", validation.sponsoring_dteMSG);
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("auth_letter_no").equals("")
				|| request.getParameter("auth_letter_no").equals("null")
				|| request.getParameter("auth_letter_no").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Auth letter No.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("initiating_auth").equals("")
				|| request.getParameter("initiating_auth").equals("null")
				|| request.getParameter("initiating_auth").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Initial Auth.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("engine_no1").equals("") || request.getParameter("engine_no1").equals("null")
				|| request.getParameter("engine_no1").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Engine.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("chasis_no1").equals("") || request.getParameter("chasis_no1").equals("null")
				|| request.getParameter("chasis_no1").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Chasis No.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else if (request.getParameter("quantity").equals("0") || request.getParameter("quantity").equals("")
				|| request.getParameter("quantity").equals("null") || request.getParameter("quantity").equals(null)) {
			model.put("p_Vettedid", parent_req_id);
			model.put("Vettedid", child_req_id);
			model.put("msg", "Please Enter Quantity.");
			return new ModelAndView("redirect:VettedBA_NOUrl");
		} else {
			try {
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				tb_child.setModify_by(username);
				tb_child.setModify_date(date);
				model.put("msg", pdao.updateReqChild(tb_child));
				return new ModelAndView("redirect:search_ba_alloUrl");
			} catch (Exception e) {
				return null;
			}
		}
	}

	@RequestMapping(value = "/admin/deleteBA_NoUrl", method = RequestMethod.POST)
	public ModelAndView deleteArmUrl(@ModelAttribute("deleteid") int deleteid, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", pdao.setDeleteprfMst(deleteid));
		return new ModelAndView("redirect:search_ba_alloUrl");
	}

	@RequestMapping(value = "/admin/Approvedba_noUrl", method = RequestMethod.POST)
	public ModelAndView Approvedba_noUrl(@ModelAttribute("appid") int appid, @ModelAttribute("p_appid") int p_appid,
			HttpSession sessionA, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!roleType.equals("APP") & !roleType.equals("ALL")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("Tms_Banum_Req_ChildCmd", pdao.getTms_Banum_Req_Childid(appid));
		
		model.put("Tms_Banum_Req_PrntCmd", pdao.getTms_Banum_Req_Prntid(p_appid));
		model.put("Tms_Banum_Req_engin_chasisCmd", pdao.getTms_Banum_Req_engin_chasisid(p_appid));
		model.put("msg", msg);
		return new ModelAndView("banum_dir_ApproveTile");
	}

	@RequestMapping(value = "/ba_no_allo_mstAction", method = RequestMethod.POST)
	public ModelAndView ba_no_allo_mstAction(@ModelAttribute("ba_no_alloCMD") TB_TMS_BANUM_DIRCTRY im,
			HttpServletRequest request, ModelMap model, HttpSession session){

		String ba_no_type = request.getParameter("ba_no_type_hide");
		
		String msg1 = "";
		String vhe_qut = request.getParameter("quantity");
		int prnt_id = Integer.parseInt(request.getParameter("id"));
		int id_child = Integer.parseInt(request.getParameter("id_child"));
		Date date = new Date();

		if ((request.getParameter("ba_no_type").toString().equals("0"))) {
			if (request.getParameter("mct_number") != null
					& validation.mctLength(request.getParameter("mct_number")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.mctMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}
		}

		if (validation.sus_noLength(request.getParameter("sus_no")) == false) {
			model.put("p_appid", request.getParameter("id"));
			model.put("appid", request.getParameter("id_child"));
			model.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:Approvedba_noUrl");
		}
		int prf_group = 0;//@nk
		String mct_number_s="00000000";
		if ((request.getParameter("ba_no_type").toString().equals("0"))) {		
			mct_number_s = request.getParameter("mct_number").toString();
			 		
			List<TB_TMS_MCT_MAIN_MASTER> mctMainList = pdao.getTms_Banum_Req_engin_chasisid(String.valueOf(mct_number_s.charAt(0)) + String.valueOf(mct_number_s.charAt(1)) +  String.valueOf(mct_number_s.charAt(2)) +  String.valueOf(mct_number_s.charAt(3)));
			if(mctMainList.size() > 0) {
				prf_group = Integer.parseInt(mctMainList.get(0).getPrf_code());
			}
		} 
		
		
		for (int i = 1; i <= Integer.parseInt(vhe_qut); i++) {
			
			String username = session.getAttribute("username").toString();
			String ba_no = request.getParameter("ba_no" + i + "");
			int id = Integer.parseInt(request.getParameter("id" + i + ""));
			String engine_no = request.getParameter("engine_no" + i + "");
			String chasis_no = request.getParameter("chasis_no" + i + "");
			BigInteger mct_number = new BigInteger(mct_number_s);

			if (validation.ba_noLength(request.getParameter("ba_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.ba_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}
			if (validation.initiating_authTMSLength(request.getParameter("engine_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.engine_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}

			if (validation.initiating_authTMSLength(request.getParameter("chasis_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.chasis_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}
			
			//kajal
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			TB_TMS_BANUM_DIRCTRY lang = new TB_TMS_BANUM_DIRCTRY();
			Query q0 = sessionHQL.createQuery("select count(id) from TB_TMS_BANUM_DIRCTRY where ba_no=:ba_no ");
			
			q0.setString("ba_no", ba_no);  
				
			Long c = (Long) q0.uniqueResult();
			

			if (c > 0)
			{
				model.put("msg", "Ba No. already exist, pl go back to BA NO REQUEST:SEARCH Screen.");
				return new ModelAndView("redirect:search_ba_alloUrl");
			}
			
		}
		
		
		
		for (int i = 1; i <= Integer.parseInt(vhe_qut); i++) {
			String username = session.getAttribute("username").toString();
			String ba_no = request.getParameter("ba_no" + i + "");
			int id = Integer.parseInt(request.getParameter("id" + i + ""));
			String engine_no = request.getParameter("engine_no" + i + "");
			String chasis_no = request.getParameter("chasis_no" + i + "");
			BigInteger mct_number = new BigInteger(mct_number_s);

			if (validation.ba_noLength(request.getParameter("ba_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.ba_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}
			if (validation.initiating_authTMSLength(request.getParameter("engine_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.engine_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}

			if (validation.initiating_authTMSLength(request.getParameter("chasis_no" + i + "")) == false) {
				model.put("p_appid", request.getParameter("id"));
				model.put("appid", request.getParameter("id_child"));
				model.put("msg", validation.chasis_noMSG);
				return new ModelAndView("redirect:Approvedba_noUrl");
			}
			
			im.setPrf_group(prf_group);
			im.setBa_no(ba_no);
			im.setEngine_no(engine_no);
			im.setChasis_no(chasis_no);
			if (ba_no_type.equals("1")) {
				im.setStatus("NonArmy");
			} else {
				im.setStatus("Active");
			}
			im.setCreation_by(username);
			im.setMct(mct_number);
			im.setCreation_date(date);
			im.setVersion_no(1);
			im.setParent_req_id(prnt_id);
			im.setVeh_cat(request.getParameter("veh_cat"));
			im.setApproved_by(username);
			im.setApprove_date(date);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			try {
				sessionHQL.save(im);
				sessionHQL.getTransaction().commit();
				msg1 = "Approved Successfully.";
			} catch (Exception e) {
				msg1 = "Not Approved Successfully.";
			} finally {
				sessionHQL.close();
			}

			if (im.getVeh_cat().equals("B")) {
				pdao.insertDIR_DRR_DTL_Main_B_veh(im, username, ba_no);
			}
			if (im.getVeh_cat().equals("A")) {
				pdao.insertCencusDIR_DRR_DTL_main_A_veh(im, username, ba_no);
			}
			if (im.getVeh_cat().equals("C")) {
				pdao.insertEMAR_DRR_DIR_DTL_main_C_veh(im, username, ba_no);
			}

			pdao.getTms_Banum_approve_upadte(id, prnt_id, date, ba_no, id_child, username);
			model.put("msg", msg1);
		}
		return new ModelAndView("redirect:search_ba_alloUrl");
	}


	@RequestMapping(value = "/getba_nocodeUrl", method = RequestMethod.POST)
	public @ResponseBody List<String> getba_nocodeUrl(String veh_code, int veh_qty) {
		String list = "";
		String baNo = "";
		int serialNo = 0;
		List<String> listItemCode = new ArrayList<String>();
		for (int i = 0; i < veh_qty; i++) {
			if (i == 0) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				Query q = session.createQuery("SELECT max(SUBSTRING(ba_no, 4,6)) FROM TB_TMS_BANUM_DIRCTRY where "
						+ " SUBSTR(ba_no,4,1) IN ('1','2','3','4','5','6','7','8','9','0') and "
						+ " SUBSTR(ba_no,5,1) IN ('1','2','3','4','5','6','7','8','9','0') and "
						+ " SUBSTR(ba_no,6,1) IN ('1','2','3','4','5','6','7','8','9','0') and "
						+ " SUBSTR(ba_no,7,1) IN ('1','2','3','4','5','6','7','8','9','0') and "
						+ " SUBSTR(ba_no,8,1) IN ('1','2','3','4','5','6','7','8','9','0') and "
						+ " SUBSTR(ba_no,9,1) IN ('1','2','3','4','5','6','7','8','9','0') and ba_no like :veh_code ");
				q.setParameter("veh_code", "__" + veh_code + "%");
				list = (String) q.list().get(0);
				tx.commit();
				session.close();
				if (list != null) {
					serialNo = Integer.parseInt(list) + 1;
				} else {
					serialNo = 1;
				}
				baNo = baNoGeneration(serialNo, veh_code);
				listItemCode.add(baNo);
			} else {
				list = baNo;
				String code = String.valueOf(list.charAt(3)) + String.valueOf(list.charAt(4))
						+ String.valueOf(list.charAt(5)) + String.valueOf(list.charAt(6))
						+ String.valueOf(list.charAt(7)) + String.valueOf(list.charAt(8));
				serialNo = Integer.parseInt(code) + 1;
				baNo = baNoGeneration(serialNo, veh_code);
				listItemCode.add(baNo);
			}
		}
		return listItemCode;
	}

	public String baNoGeneration(int serialNo, String veh_code) {
		String serial = String.format("%06d", serialNo);
		int sum = 0;
		int i = 0;
		for (int iq = 7; iq > 1; iq--) {
			String multi = String.valueOf(serial.charAt(i));
			int ans = Integer.parseInt(multi) * iq;
			sum += ans;
			i++;
		}
		int mod = sum % 11;
		int mini = 11 - mod;
		if (mod == 0) {
			mini = 0;
		}
		
		String last_cht = "";
		if (mini == 0)
			last_cht = "A";
		if (mini == 1)
			last_cht = "E";
		if (mini == 2)
			last_cht = "H";
		if (mini == 3)
			last_cht = "K";
		if (mini == 4)
			last_cht = "L";
		if (mini == 5)
			last_cht = "M";
		if (mini == 6)
			last_cht = "N";
		if (mini == 7)
			last_cht = "P";
		if (mini == 8)
			last_cht = "W";
		if (mini == 9)
			last_cht = "X";
		if (mini >= 10)
			last_cht = "Y";
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String formattedDate = df.format(Calendar.getInstance().getTime());
		String baNo = formattedDate + veh_code + serial + last_cht;
		return baNo;
	}

	public String baNoConvert(int serialNo, String veh_code, String year) {
		String serial = String.format("%06d", serialNo);
		int sum = 0;
		int i = 0;
		for (int iq = 7; iq > 1; iq--) {
			String multi = String.valueOf(serial.charAt(i));
			int ans = Integer.parseInt(multi) * iq;
			sum += ans;
			i++;
		}
		int mod = sum % 11;
		int mini = 11 - mod;
		String last_cht = "";
		if (mini == 0)
			last_cht = "A";
		if (mini == 1)
			last_cht = "E";
		if (mini == 2)
			last_cht = "H";
		if (mini == 3)
			last_cht = "K";
		if (mini == 4)
			last_cht = "L";
		if (mini == 5)
			last_cht = "M";
		if (mini == 6)
			last_cht = "N";
		if (mini == 7)
			last_cht = "P";
		if (mini == 8)
			last_cht = "W";
		if (mini == 9)
			last_cht = "X";
		if (mini >= 10)
			last_cht = "Y";
		String baNo = year + veh_code + serial + last_cht;
		return baNo;
	}

	@RequestMapping(value = "/admin/Allotment_of_BA_Number", method = RequestMethod.GET)
	public ModelAndView Animal_Equines(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Allotment_of_BA_Number", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Allotment_of_BA_NumberTile");
	}

	@RequestMapping(value = "/admin/getAttributeDataSearchallotmentofbanum", method = RequestMethod.POST)
	public ModelAndView getAttributeDataSearchallotmentofbanum(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "mct1", required = false) String mct,
			@RequestParam(value = "f_date", required = false) String f_date,
			@RequestParam(value = "t_date", required = false) String t_date) {
		
		if(mct.equals("")) {
			Mmap.put("msg", "Please Select MCT No.");
			return new ModelAndView("redirect:Allotment_of_BA_Number");
		}
		if (validation.mctLength(mct) == false) {
			Mmap.put("msg", validation.mctMSG);
			return new ModelAndView("redirect:Allotment_of_BA_Number");
		}
		if(f_date.equals("")) {
			Mmap.put("msg", "Please Select From Date.");
			return new ModelAndView("redirect:Allotment_of_BA_Number");
		}
		if(t_date.equals("")) {
			Mmap.put("msg", "Please Select To Date.");
			return new ModelAndView("redirect:Allotment_of_BA_Number");
		}
		
		ArrayList<List<String>> list = pdao.getAttributeDataSearchallotmentofbanum1(mct,f_date,t_date);
		Mmap.put("mct", mct);
		Mmap.put("f_date", f_date);
		Mmap.put("t_date", t_date);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Allotment_of_BA_NumberTile");
	}
	
	

/*   NITIN V4 16/11/2022  */
@RequestMapping(value = "/Delete_En_Details", method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_En_Details(int id,int p_id,int qty, HttpServletRequest request, HttpSession sessionA,
			Authentication authentication) {

		List<String> liststr = new ArrayList<String>();
		String roleType = sessionA.getAttribute("roleType").toString();
		
		/*if (!roleType.equals("APP") & !roleType.equals("DEO")) {
			liststr.add("You do not have permission to access This Operation.");
			return liststr;
		}*/
		int app = 0;
		try {
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from Tms_Banum_Req_Child where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			//tx.commit();
			
			qty = qty-1;
			String hql = "update Tms_Banum_Req_Child set quantity=:quantity where parent_req_id=:parent_req_id";
			Query query = session.createQuery(hql).setInteger("quantity", qty).
				setInteger("parent_req_id",p_id);
			app = query.executeUpdate();
			tx.commit();
			
			session.close();

			if (app > 0) {
				liststr.add("Deleted Successfully.");
			} else {
				liststr.add("Data not Delete.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}

	}

/// bisag 230523 v2 (new anhancement reject flow)


@RequestMapping(value = "/admin/update_BA_noUrl", method = RequestMethod.POST)
public ModelAndView UpdateItemArmUrl(@ModelAttribute("Tms_Banum_Req_ChildCmd") Tms_Banum_Req_Child tb_child,
		ModelMap model, HttpServletRequest request, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
	int parent_req_id = tb_child.getParent_req_id();
	String Vehicle_clas_code = tb_child.getVehicle_clas_code();
	String child_req_id = request.getParameter("id_child");
	if (!request.getParameter("mct_number").equals("0000000000") & request.getParameter("mct_number") != null) {
		tb_child.setMct_number(new BigInteger(request.getParameter("mct_number")));
	} else {
		tb_child.setMct_number(new BigInteger("0"));
	}

	if (request.getParameter("ba_no_type") == null) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Select Army/Non-Army.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("veh_cat").equals("0") || request.getParameter("veh_cat").equals("")
			|| request.getParameter("veh_cat").equals("null") || request.getParameter("veh_cat").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Type of Vehicle.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("requesting_agency").equals("")
			|| request.getParameter("requesting_agency").equals("null")
			|| request.getParameter("requesting_agency").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Requesting Agency.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("dte_of_reqst").equals("")
			|| request.getParameter("dte_of_reqst").equals("null")
			|| request.getParameter("dte_of_reqst").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Select Date Of Request.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("sus_no").equals("") || request.getParameter("sus_no").equals("null")
			|| request.getParameter("sus_no").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if ((request.getParameter("ba_no_type").toString().equals("0"))
			&& (request.getParameter("mct_number").toString().equals("0")
					|| request.getParameter("mct_number").toString().equals("")
					|| request.getParameter("mct_number").toString().equals("null")
					|| request.getParameter("mct_number").toString().equals(null)
					|| request.getParameter("mct_number") == null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter MCT Number.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if ((request.getParameter("ba_no_type").toString().equals("0"))
			&& validation.mctLength(request.getParameter("mct_number")) == false) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", request.getParameter("id_child"));
		model.put("msg", validation.mctMSG);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("vehicle_clas_code").equals("")
			|| request.getParameter("vehicle_clas_code").equals("null")
			|| request.getParameter("vehicle_clas_code").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Vehicle Class Code.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (Vehicle_clas_code.equals("") || Vehicle_clas_code.equals("null") || Vehicle_clas_code.equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Class of Vehicle.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("year_of_entry").equals("")
			|| request.getParameter("year_of_entry").equals("null")
			|| request.getParameter("year_of_entry").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Yr of Entry into Service.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("tonnage"))) == false) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", validation.tonnageMSG);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("towing_capcty"))) == false) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", validation.towing_capacityMSG);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("lift_capcty"))) == false) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", validation.lift_capacityMSG);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (validation.checkUnit_nameLength(request.getParameter("sponsoring_dte")) == false) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", validation.sponsoring_dteMSG);
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("auth_letter_no").equals("")
			|| request.getParameter("auth_letter_no").equals("null")
			|| request.getParameter("auth_letter_no").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Auth letter No.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("initiating_auth").equals("")
			|| request.getParameter("initiating_auth").equals("null")
			|| request.getParameter("initiating_auth").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Initial Auth.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("engine_no1").equals("") || request.getParameter("engine_no1").equals("null")
			|| request.getParameter("engine_no1").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Engine.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("chasis_no1").equals("") || request.getParameter("chasis_no1").equals("null")
			|| request.getParameter("chasis_no1").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Chasis No.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else if (request.getParameter("quantity").equals("0") || request.getParameter("quantity").equals("")
			|| request.getParameter("quantity").equals("null") || request.getParameter("quantity").equals(null)) {
		model.put("p_Updateid", parent_req_id);
		model.put("Updateid", child_req_id);
		model.put("msg", "Please Enter Quantity.");
		return new ModelAndView("redirect:UpdateBA_NOUrl");
	} else {
		try {
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			tb_child.setModify_by(username);
			tb_child.setModify_date(date);
			model.put("msg", pdao.editReqChild(tb_child));
			return new ModelAndView("redirect:search_ba_alloUrl");
		} catch (Exception e) {
			return null;
		}
	}
}



@RequestMapping(value = "/UpdateBA_NOUrl")
public ModelAndView UpdateBA_NOUrl(@ModelAttribute("Updateid") int Updateid,
		@ModelAttribute("p_Updateid") int p_Updateid, HttpSession session, ModelMap model,HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
	}
	model.put("Tms_Banum_Req_ChildCmd", pdao.getTms_Banum_Req_Childid(Updateid));
	model.put("Tms_Banum_Req_PrntCmd", pdao.getTms_Banum_Req_Prntid(p_Updateid));
	model.put("Tms_Banum_Req_engin_chasisCmd", pdao.getTms_Banum_Req_engin_chasisid(p_Updateid));
	model.put("msg", msg);
	return new ModelAndView("banum_dirctryUpdTile");
}



@RequestMapping(value = "/RejectedBA_NOUrl")
public ModelAndView RejectedBA_NOUrl(@ModelAttribute("Rejectedid") int Rejectedid,
		@ModelAttribute("p_Rejectedid") int p_Rejectedid, HttpSession session, ModelMap model,HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("search_ba_alloUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
	}
	model.put("Tms_Banum_Req_ChildCmd", pdao.getTms_Banum_Req_Childid(Rejectedid));
	model.put("Tms_Banum_Req_PrntCmd", pdao.getTms_Banum_Req_Prntid(p_Rejectedid));
	model.put("Tms_Banum_Req_engin_chasisCmd", pdao.getTms_Banum_Req_engin_chasisid(p_Rejectedid));
	model.put("msg", msg);
	return new ModelAndView("banum_dirctryRejTile");
}









@RequestMapping(value = "/admin/rejected_BA_noUrl", method = RequestMethod.POST)
public ModelAndView RejectedItemArmUrl(@ModelAttribute("Tms_Banum_Req_ChildCmd") Tms_Banum_Req_Child tb_child,
		ModelMap model, HttpServletRequest request, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
	int parent_req_id = tb_child.getParent_req_id();
	String Vehicle_clas_code = tb_child.getVehicle_clas_code();
	String child_req_id = request.getParameter("id_child");
	String remarks=request.getParameter("rej_remarks4");
	if (!request.getParameter("mct_number").equals("0000000000") & request.getParameter("mct_number") != null) {
		tb_child.setMct_number(new BigInteger(request.getParameter("mct_number")));
	} else {
		tb_child.setMct_number(new BigInteger("0"));
	}

	if (request.getParameter("ba_no_type") == null) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Select Army/Non-Army.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("veh_cat").equals("0") || request.getParameter("veh_cat").equals("")
			|| request.getParameter("veh_cat").equals("null") || request.getParameter("veh_cat").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Type of Vehicle.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("requesting_agency").equals("")
			|| request.getParameter("requesting_agency").equals("null")
			|| request.getParameter("requesting_agency").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Requesting Agency.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("dte_of_reqst").equals("")
			|| request.getParameter("dte_of_reqst").equals("null")
			|| request.getParameter("dte_of_reqst").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Select Date Of Request.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("sus_no").equals("") || request.getParameter("sus_no").equals("null")
			|| request.getParameter("sus_no").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if ((request.getParameter("ba_no_type").toString().equals("0"))
			&& (request.getParameter("mct_number").toString().equals("0")
					|| request.getParameter("mct_number").toString().equals("")
					|| request.getParameter("mct_number").toString().equals("null")
					|| request.getParameter("mct_number").toString().equals(null)
					|| request.getParameter("mct_number") == null)) {
		
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter MCT Number.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if ((request.getParameter("ba_no_type").toString().equals("0"))
			&& validation.mctLength(request.getParameter("mct_number")) == false) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", request.getParameter("id_child"));
		model.put("msg", validation.mctMSG);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("vehicle_clas_code").equals("")
			|| request.getParameter("vehicle_clas_code").equals("null")
			|| request.getParameter("vehicle_clas_code").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Vehicle Class Code.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (Vehicle_clas_code.equals("") || Vehicle_clas_code.equals("null") || Vehicle_clas_code.equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Class of Vehicle.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("year_of_entry").equals("")
			|| request.getParameter("year_of_entry").equals("null")
			|| request.getParameter("year_of_entry").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Yr of Entry into Service.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("tonnage"))) == false) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", validation.tonnageMSG);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("towing_capcty"))) == false) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", validation.towing_capacityMSG);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (validation.axle_wtsLength(String.valueOf(request.getParameter("lift_capcty"))) == false) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", validation.lift_capacityMSG);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (validation.checkUnit_nameLength(request.getParameter("sponsoring_dte")) == false) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", validation.sponsoring_dteMSG);
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("auth_letter_no").equals("")
			|| request.getParameter("auth_letter_no").equals("null")
			|| request.getParameter("auth_letter_no").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Auth letter No.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("initiating_auth").equals("")
			|| request.getParameter("initiating_auth").equals("null")
			|| request.getParameter("initiating_auth").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Initial Auth.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("engine_no1").equals("") || request.getParameter("engine_no1").equals("null")
			|| request.getParameter("engine_no1").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Engine.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("chasis_no1").equals("") || request.getParameter("chasis_no1").equals("null")
			|| request.getParameter("chasis_no1").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Chasis No.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} else if (request.getParameter("quantity").equals("0") || request.getParameter("quantity").equals("")
			|| request.getParameter("quantity").equals("null") || request.getParameter("quantity").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Quantity.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
	} 
			else if (request.getParameter("rej_remarks4").equals("") ||request.getParameter("rej_remarks4").equals("0")
		|| request.getParameter("rej_remarks4").equals("null") || request.getParameter("rej_remarks4").equals(null)) {
		model.put("p_Rejectedid", parent_req_id);
		model.put("Rejectedid", child_req_id);
		model.put("msg", "Please Enter Remarks.");
		return new ModelAndView("redirect:RejectedBA_NOUrl");
		} else {
		try {
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			tb_child.setModify_by(username);
			tb_child.setRemarks(remarks);
			tb_child.setModify_date(date);
			model.put("msg", pdao.rejectReqChild(tb_child));
			return new ModelAndView("redirect:search_ba_alloUrl");
		} catch (Exception e) {
			return null;
		}
	}
}






}