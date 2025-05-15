package com.controller.tms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.ExportFile.ExcelUserListReportViewsc_st;
import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.Oh_Qfd_DAO;
import com.dao.tms.TMS_base_work_mstrDAO;
import com.dao.tms.Track_status_DAO;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_MCT_MAIN_MASTER;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_VEH_CAT_LINK;
import com.models.Tms_Banum_Req_Child;
import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Track_status_Controller {

	@Autowired
	private Oh_Qfd_DAO mari_dao;

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Track_status_DAO t_dao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private TMS_base_work_mstrDAO base_workdao;
	
	/*@RequestMapping(value = "/Track_status_url")
	public ModelAndView Track_status_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Track_status_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		ArrayList<ArrayList<String>> list = t_dao.a_search_track_status(session);
		model.put("list", list);
		model.put("size", list.size());
		model.put("roleAccess", roleAccess);
		model.put("base_work_shopList", mari_dao.base_work_shopList());
		model.put("msg", msg);
		return new ModelAndView("track_statusTiles");
	}
*/
	/*
	 * @RequestMapping(value = "/gettrackstatus_report" , method =
	 * RequestMethod.POST) public ModelAndView gettrackstatus_report(ModelMap model,
	 * HttpSession session,HttpServletRequest request,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg){
	 * 
	 * ArrayList<ArrayList<String>> list = t_dao.search_track_status();
	 * model.put("list", list); model.put("size", list.size());
	 * 
	 * 
	 * model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	 * model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
	 * model.put("msg", msg); String roleAccess =
	 * session.getAttribute("roleAccess").toString(); String roleSusNo =
	 * session.getAttribute("roleSusNo").toString();
	 * 
	 * 
	 * return new ModelAndView("track_statusTiles"); }
	 */

	/*
	 * @RequestMapping(value = "/Excel_track_status_report", method =
	 * RequestMethod.POST) public ModelAndView
	 * Excel_track_status_report(HttpServletRequest request,ModelMap
	 * model,HttpSession session,String typeReport1,
	 * 
	 * @RequestParam(value = "type_vehex", required = false) String type_veh,
	 * //@RequestParam(value = "prf_code1", required = false) String prf_code,
	 * 
	 * @RequestParam(value = "mct_mainex", required = false) String mct_main,
	 * 
	 * @RequestParam(value = "line_dte_susex", required = false) String
	 * line_dte_sus,
	 * 
	 * @RequestParam(value = "kmsex", required = false) int kms,
	 * 
	 * @RequestParam(value = "vintagex", required = false) int vintag,
	 * 
	 * @RequestParam(value = "pyex", required = false) int py,
	 * 
	 * @RequestParam(value = "type_forceex", required = false) String type_force,
	 * 
	 * @RequestParam(value = "msg", required = false) String msg) {
	 * 
	 * String roleid = session.getAttribute("roleid").toString(); Boolean val =
	 * roledao.ScreenRedirect("oh_qfd_url", roleid); if (val == false) { return new
	 * ModelAndView("AccessTiles"); } if (request.getHeader("Referer") == null) {
	 * msg = ""; return new ModelAndView("redirect:bodyParameterNotAllow"); }
	 * ArrayList<ArrayList<String>> Excel = t_dao.search_track_status();
	 * 
	 * List<String> TH = new ArrayList<String>();
	 * 
	 * 
	 * TH.add("Comd"); TH.add("Corps"); TH.add("Division"); TH.add("Brigade");
	 * TH.add("Unit Name"); TH.add("SUS No"); TH.add("Nomenclature");
	 * TH.add("BA No"); TH.add("Kms Run"); TH.add("Vintage");
	 * 
	 * 
	 * String username = session.getAttribute("username").toString(); return new
	 * ModelAndView(new Excel_To_Export_1_QFD("L", TH, username,Excel), "userList",
	 * Excel ); }
	 */
	@RequestMapping(value = "/Track_status_url")
	public ModelAndView Track_status_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Track_status_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
	
//	ArrayList<ArrayList<String>> list = t_dao.a_search_track_status(session,"","");
//		model.put("list", list);
//		model.put("size", list.size());
		model.put("roleAccess", roleAccess);
		model.put("base_work_shopList", mari_dao.base_work_shopList());
		model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
		model.put("msg", msg);
		return new ModelAndView("track_statusTiles");
	}
	 @RequestMapping(value = "/gettrackstatus_report_B" , method =
			 RequestMethod.POST) public ModelAndView gettrackstatus_report_B(ModelMap model,
			 HttpSession session,HttpServletRequest request,
			 
			 @RequestParam(value = "msg", required = false) String msg
			 ,	 @RequestParam(value = "mode1", required = false) String mode1
			 ,	 @RequestParam(value = "py1", required = false) String py1
					 ){
			 
				ArrayList<ArrayList<String>> list = t_dao.b_search_track_status(session,mode1,py1);
			 model.put("list", list); model.put("size", list.size());
			 
			
			 model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			 model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
			 model.put("msg", msg); 
			 model.put("mode1", mode1); 
			 model.put("py1", py1); 
			 
			 
			 String roleAccess =
			 session.getAttribute("roleAccess").toString(); String roleSusNo =
			 session.getAttribute("roleSusNo").toString();
			 model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
			 model.put("base_work_shopList", mari_dao.base_work_shopList());
			 return new ModelAndView("b_track_statusTiles"); }
	
	
	
	

 @RequestMapping(value = "/gettrackstatus_report" , method =
	 RequestMethod.POST) public ModelAndView gettrackstatus_report(ModelMap model,
	 HttpSession session,HttpServletRequest request,
	 
	 @RequestParam(value = "msg", required = false) String msg
	 ,	 @RequestParam(value = "mode1", required = false) String mode1
	 ,	 @RequestParam(value = "py1", required = false) String py1
			 ){
	 
		ArrayList<ArrayList<String>> list = t_dao.a_search_track_status(session,mode1,py1);
	 model.put("list", list); model.put("size", list.size());
	 
	
	 model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	 model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
	 model.put("msg", msg); 
	 model.put("mode1", mode1); 
	 model.put("py1", py1); 
	 model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
	 
	 model.put("base_work_shopList", mari_dao.base_work_shopList());
	 String roleAccess =
	 session.getAttribute("roleAccess").toString(); String roleSusNo =
	 session.getAttribute("roleSusNo").toString();
	 
	 
	 return new ModelAndView("track_statusTiles"); }
	

	@RequestMapping(value = "/admin/update_track_action", method = RequestMethod.POST)
	public @ResponseBody String update_track_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		String msg = "";
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		String username = session.getAttribute("username").toString();
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date oh_dt = null;
		Date arri_dt = null;

		String dt_arrival = request.getParameter("dt_arrival");
		String oh_date = request.getParameter("oh_date");
		String oh_status = request.getParameter("oh_status");
		String cin_no = request.getParameter("cin_no");
		String veh_type = request.getParameter("veh_type");
		String ba_no = request.getParameter("ba_no");
		if (oh_date != null && !oh_date.equals("") && !oh_date.equals("DD/MM/YYYY")) {
			oh_dt = format.parse(oh_date);
		}
		if (dt_arrival != null && !dt_arrival.equals("") && !dt_arrival.equals("DD/MM/YYYY")) {
			arri_dt = format.parse(dt_arrival);
		}
		try {
			String hqlHQL1 = "update TB_TMS_CIN  set oh_status=:oh_status,oh_date=:oh_date,dt_arrival=:dt_arrival where cin_id=:cin_id";
			Query updatedEntities = sessionhql.createQuery(hqlHQL1);
			updatedEntities.setParameter("oh_status", oh_status);
			updatedEntities.setParameter("oh_date", oh_dt);
			updatedEntities.setParameter("dt_arrival", arri_dt);
			updatedEntities.setParameter("cin_id", Integer.parseInt(cin_no));
			updatedEntities.executeUpdate();
			if(oh_status.equals("Yes")){
				String hqlUpdate ="";
				if(veh_type.equals("A")) {
					hqlUpdate = "update TB_TMS_CENSUS_RETURN p set vehcl_kms_run=0 where p.ba_no=:ba_no";
				}
				if(veh_type.equals("B")) {
					hqlUpdate = "update TB_TMS_MVCR_PARTA_DTL p set kms_run=0 where p.ba_no=:ba_no";
				}
				if(veh_type.equals("C")) {
					hqlUpdate = "update TB_TMS_EMAR_REPORT p set current_km_run=0 where p.em_no=:ba_no";
				}
				Query updatedKms = sessionhql.createQuery(hqlUpdate);
				updatedKms.setParameter("ba_no",ba_no);
				updatedKms.executeUpdate();	
			}
			tx.commit();
			if(oh_status.equals("Yes")) {
				msg = "OH Completed";
			}else {
				msg = "Data Updated";
			}
		} catch (RuntimeException e) {
			tx.rollback();
			msg = "An Error Occured";
		} finally {
			sessionhql.close();
		}
		return msg;
	}

	@RequestMapping(value = "/admin/cancle_cin_action", method = RequestMethod.POST)
	public @ResponseBody String cancle_cin_action(ModelMap Mmap, HttpSession session, HttpServletRequest request) {
		String msg = "";
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String cin_no = request.getParameter("cin_no");
		String remarks = request.getParameter("remarks");
		try {
			String hqlHQL1 = "update TB_TMS_CIN  set status=:status,remark=:remark where cin_id=:cin_id";
			Query updatedEntities = sessionhql.createQuery(hqlHQL1);
			updatedEntities.setParameter("status", "2");
			updatedEntities.setParameter("remark", remarks);
			updatedEntities.setParameter("cin_id", Integer.parseInt(cin_no));
			updatedEntities.executeUpdate();
			tx.commit();
			msg = "Data updated";
		} catch (RuntimeException e) {
			tx.rollback();
			msg = "Data Not Updated";
		} finally {
			sessionhql.close();
		}
		return msg;
	}

	@RequestMapping(value = "/B_Track_status_url")
	public ModelAndView B_Track_status_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Track_status_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		/*ArrayList<ArrayList<String>> list = t_dao.b_search_track_status(session);
		model.put("list", list);
		model.put("size", list.size());*/
		model.put("roleAccess", roleAccess);
		model.put("base_work_shopList", mari_dao.base_work_shopList());
		model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
		model.put("msg", msg);
		return new ModelAndView("b_track_statusTiles");
	}
	
	
	@RequestMapping(value = "/C_Track_status_url")
	public ModelAndView C_Track_status_url(ModelMap model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Track_status_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleAccess = session.getAttribute("roleAccess").toString();
		/*ArrayList<ArrayList<String>> list = t_dao.c_search_track_status(session);
		model.put("list", list);
		model.put("size", list.size());*/
		model.put("roleAccess", roleAccess);
		model.put("base_work_shopList", mari_dao.base_work_shopList());
		model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
		model.put("msg", msg);
		return new ModelAndView("c_track_statusTiles");
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/IUTDownloadDemo" , method = RequestMethod.GET)
	public void IUTDownloadDemo(@ModelAttribute("id") int id,ModelMap model, HttpServletRequest request,HttpServletResponse response,
			HttpSession sessionA, Authentication authentication) throws IOException, SQLException {
	
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		 Assets_Main u_file_path=(Assets_Main)sessionHQL.get(Assets_Main.class,id);
		 
		 String filePath = u_file_path.getU_file();


	final int BUFFER_SIZE = 4096;



	ServletContext context = request.getSession().getServletContext();
	try {
		if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
			@SuppressWarnings("deprecation")
			String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
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
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}
	} catch (Exception ex) {
		@SuppressWarnings("deprecation")
		String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		ServletOutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

}
	
	
	@RequestMapping(value = "/get_C_trackstatus_report" , method =
			 RequestMethod.POST) public ModelAndView get_C_trackstatus_report(ModelMap model,
			 HttpSession session,HttpServletRequest request,
			 
			 @RequestParam(value = "msg", required = false) String msg
			 ,	 @RequestParam(value = "mode1", required = false) String mode1
			 ,	 @RequestParam(value = "py1", required = false) String py1
					 ){
			 
				ArrayList<ArrayList<String>> list = t_dao.c_search_track_status(session, mode1, py1);
			 model.put("list", list); model.put("size", list.size());
			 
			 System.out.println(mode1+"mode1"+py1);
			 model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			 model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
			 model.put("msg", msg); 
			 model.put("mode1", mode1); 
			 model.put("py1", py1); 
			 model.put("repair_shopList", base_workdao.repair_agency_search_REPORT("", ""));
			 model.put("base_work_shopList", mari_dao.base_work_shopList());
			 String roleAccess =
			 session.getAttribute("roleAccess").toString(); String roleSusNo =
			 session.getAttribute("roleSusNo").toString();
			 
			 
			 return new ModelAndView("c_track_statusTiles"); }
			 
			 
	
}
