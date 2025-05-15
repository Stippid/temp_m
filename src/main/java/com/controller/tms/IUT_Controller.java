package com.controller.tms;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.IUTDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.TB_TMS_IUT;
import com.models.TB_TMS_RELIEF_PROG_HISTORY;
import com.models.TB_TMS_RELIEF_PROG_HISTORY_A;
import com.models.TB_TMS_RELIEF_PROG_HISTORY_C;
import com.models.Tbl_CodesForm;
import com.models.assets.Assets_Main;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class IUT_Controller {

	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	private IUTDAO iutDAO;

	ValidationController validation = new ValidationController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();

	@RequestMapping(value = "/order_iut", method = RequestMethod.GET)
	public ModelAndView order_iut(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("order_iut", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("order_iutTiles");
	}

	@RequestMapping(value = "/getMCtMain_Id", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMCtMain_Id(HttpSession sessionA,
			@RequestParam String type_of_veh,@RequestParam String sus_no) {
		
		if (!type_of_veh.equals("")) {
			
			return iutDAO.getMCtMain_Id(type_of_veh, sessionA,sus_no);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/getIUTBANoList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getIUTBANoList(String source_sus_no, String type_of_veh,
			String mct_main, HttpSession sessionA) {
		List<Map<String, Object>> list = iutDAO.getIUTBANoList(source_sus_no, type_of_veh, mct_main,
				sessionA);
		return list;
	}


	@RequestMapping(value = "/IutAction",method=RequestMethod.POST)
	public ModelAndView IutAction(@ModelAttribute("IutActionCMD") TB_TMS_IUT rs,
	/*@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1 ,
	@RequestParam(value = "u_file_hid", required = false) String u_file_hid,*/
			HttpServletRequest request,ModelMap model,HttpSession session
			) throws IOException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("order_iut", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
	
		
		String username = session.getAttribute("username").toString();
		String source_sus_no = request.getParameter("source_sus_no");
		String source_unit_name = request.getParameter("source_unit_name");
		String target_sus_no = request.getParameter("target_sus_no");
		String target_unit_name = request.getParameter("target_unit_name");
		String authority_no = request.getParameter("authority_no");
		String ba_no = request.getParameter("app");
		String type_veh = request.getParameter("type_veh");
		String mct_main = request.getParameter("mct_main");
	
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String extension = "";
		String fname = "";
		
		
		/*String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
		if (!upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {
        model.put("msg", "Only *.pdf file extensions allowed");
		return new ModelAndView("redirect:order_iut");
			}
	
		
			if(u_file_hid.equals(""))
			{
			if (!doc_upload1.isEmpty()) {
				
	
				// code modify by pratiksha on 23/05/2023
				//try {
					
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = doc_upload1.getBytes();
					String tmsFilePath = session.getAttribute("tmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = doc_upload1.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userid + "_TMSDOC." + extension;

					

					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					 rs.setUpload_voucher(fname.replace("\\","/"));
			}
			} */
		
		
		
		
		
		if (source_sus_no.equals("") || source_sus_no == "" || source_sus_no == null || source_sus_no.equals(null)
				|| source_sus_no.equals("null")) {
			model.put("msg", "Please Enter the Source SUS No.");
			return new ModelAndView("redirect:order_iut");
		} else if (source_sus_no != "" & source_sus_no != null & !source_sus_no.equals("") & !source_sus_no.equals(null)
				& validation.sus_noLength(source_sus_no) == false) {
			model.put("msg", validation.source_sus_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (target_sus_no.equals("") || target_sus_no == "" || target_sus_no == null
				|| target_sus_no.equals(null) || target_sus_no.equals("null")) {
			model.put("msg", "Please Enter the Target SUS No.");
			return new ModelAndView("redirect:order_iut");
		} else if (target_sus_no != "" & target_sus_no != null & !target_sus_no.equals("") & !target_sus_no.equals(null)
				& validation.sus_noLength(target_sus_no) == false) {
			model.put("msg", validation.target_sus_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (authority_no.equals("") || authority_no == "" || authority_no == null || authority_no.equals(null)
				|| authority_no.equals("null")) {
			model.put("msg", "Please Enter the Authority No.");
			return new ModelAndView("redirect:order_iut");
		} else if (authority_no != "" & authority_no != null & !authority_no.equals("") & !authority_no.equals(null)
				& validation.authority_noLength(authority_no) == false) {
			model.put("msg", validation.authority_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (target_unit_name.equals("") || target_unit_name == "" || target_unit_name == null
				|| target_unit_name.equals(null) || target_unit_name.equals("null")) {
			model.put("msg", "Please Enter the Target Unit Name.");
			return new ModelAndView("redirect:order_iut");
		} else if (target_unit_name != "" & target_unit_name != null & !target_unit_name.equals("")
				& !target_unit_name.equals(null) & validation.checkUnit_nameLength(target_unit_name) == false) {
			model.put("msg", validation.target_unit_nameMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (source_unit_name.equals("") || source_unit_name == "" || source_unit_name == null
				|| source_unit_name.equals(null) || source_unit_name.equals("null")) {
			model.put("msg", "Please Enter the Source Unit Name.");
			return new ModelAndView("redirect:order_iut");
		} else if (source_unit_name != "" & source_unit_name != null & !source_unit_name.equals("")
				& !source_unit_name.equals(null) & validation.checkUnit_nameLength(source_unit_name) == false) {
			model.put("msg", validation.source_unit_nameMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (type_veh.equals("0") || type_veh == "0" || type_veh == null || type_veh.equals(null)
				|| type_veh.equals("null")) {
			model.put("msg", "Please Select VEH CAT");
			return new ModelAndView("redirect:order_iut");
		}else if (mct_main.equals("0") || mct_main == "0" || mct_main == null || mct_main.equals(null)
				|| mct_main.equals("null")) {
			model.put("msg", "Please Select MCT Main");
			return new ModelAndView("redirect:order_iut");
		}else if (ba_no.equals("") || ba_no == "" || ba_no == null || ba_no.equals(null) || ba_no.equals("null")) {
			model.put("msg", "Please Select the Ba No.");
			return new ModelAndView("redirect:order_iut");
		} else {
			Session sessionHQL = null;
			Transaction tx = null;
			try {
				
				
				
				sessionHQL = HibernateUtil.getSessionFactory().openSession();
				tx = sessionHQL.beginTransaction();
				
				
				rs.setIut_authority_no(authority_no);
				rs.setBa_no(ba_no);
				rs.setCreated_by(username);
				rs.setCreated_on(new Date());
				rs.setMain_id(Integer.parseInt(mct_main));
				rs.setSource_sus_no(source_sus_no);
				rs.setStatus(0);
				rs.setTarget_sus_no(target_sus_no);
				rs.setVehical_type(type_veh);
				rs.setUserid(userid);
				sessionHQL.save(rs);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				model.put("msg", "Ordered Successfully.");
			} catch (Exception e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldnot roll back transaction " + rbe.getMessage());
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:order_iut");
		}
	}	 
	
	@RequestMapping(value = "/IutAction_c",method=RequestMethod.POST)
	public ModelAndView IutAction_c(@ModelAttribute("IutActionCMD_c") TB_TMS_IUT rs,
	/*@RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1 ,
	@RequestParam(value = "u_file_hid", required = false) String u_file_hid,*/
			HttpServletRequest request,ModelMap model,HttpSession session
			) throws IOException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("order_iut_c_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
	
		
		String username = session.getAttribute("username").toString();
		String source_sus_no = request.getParameter("source_sus_no");
		String source_unit_name = request.getParameter("source_unit_name");
		String target_sus_no = request.getParameter("target_sus_no");
		String target_unit_name = request.getParameter("target_unit_name");
		String authority_no = request.getParameter("authority_no");
		String ba_no = request.getParameter("app");
		String type_veh = request.getParameter("type_veh");
		String mct_main = request.getParameter("mct_main");
	
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String extension = "";
		String fname = "";
		
		
		/*String upload_imgEXt = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
		if (!upload_imgEXt.toUpperCase().equals("pdf".toUpperCase())) {
        model.put("msg", "Only *.pdf file extensions allowed");
		return new ModelAndView("redirect:order_iut");
			}
	
		
			if(u_file_hid.equals(""))
			{
			if (!doc_upload1.isEmpty()) {
				
	
				// code modify by pratiksha on 23/05/2023
				//try {
					
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = doc_upload1.getBytes();
					String tmsFilePath = session.getAttribute("tmsFilePath").toString();
					File dir = new File(tmsFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = doc_upload1.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userid + "_TMSDOC." + extension;

					

					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					 rs.setUpload_voucher(fname.replace("\\","/"));
			}
			} */
		
		
		
		
		
		if (source_sus_no.equals("") || source_sus_no == "" || source_sus_no == null || source_sus_no.equals(null)
				|| source_sus_no.equals("null")) {
			model.put("msg", "Please Enter the Source SUS No.");
			return new ModelAndView("redirect:order_iut");
		} else if (source_sus_no != "" & source_sus_no != null & !source_sus_no.equals("") & !source_sus_no.equals(null)
				& validation.sus_noLength(source_sus_no) == false) {
			model.put("msg", validation.source_sus_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (target_sus_no.equals("") || target_sus_no == "" || target_sus_no == null
				|| target_sus_no.equals(null) || target_sus_no.equals("null")) {
			model.put("msg", "Please Enter the Target SUS No.");
			return new ModelAndView("redirect:order_iut");
		} else if (target_sus_no != "" & target_sus_no != null & !target_sus_no.equals("") & !target_sus_no.equals(null)
				& validation.sus_noLength(target_sus_no) == false) {
			model.put("msg", validation.target_sus_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (authority_no.equals("") || authority_no == "" || authority_no == null || authority_no.equals(null)
				|| authority_no.equals("null")) {
			model.put("msg", "Please Enter the Authority No.");
			return new ModelAndView("redirect:order_iut");
		} else if (authority_no != "" & authority_no != null & !authority_no.equals("") & !authority_no.equals(null)
				& validation.authority_noLength(authority_no) == false) {
			model.put("msg", validation.authority_noMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (target_unit_name.equals("") || target_unit_name == "" || target_unit_name == null
				|| target_unit_name.equals(null) || target_unit_name.equals("null")) {
			model.put("msg", "Please Enter the Target Unit Name.");
			return new ModelAndView("redirect:order_iut");
		} else if (target_unit_name != "" & target_unit_name != null & !target_unit_name.equals("")
				& !target_unit_name.equals(null) & validation.checkUnit_nameLength(target_unit_name) == false) {
			model.put("msg", validation.target_unit_nameMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (source_unit_name.equals("") || source_unit_name == "" || source_unit_name == null
				|| source_unit_name.equals(null) || source_unit_name.equals("null")) {
			model.put("msg", "Please Enter the Source Unit Name.");
			return new ModelAndView("redirect:order_iut");
		} else if (source_unit_name != "" & source_unit_name != null & !source_unit_name.equals("")
				& !source_unit_name.equals(null) & validation.checkUnit_nameLength(source_unit_name) == false) {
			model.put("msg", validation.source_unit_nameMSG);
			return new ModelAndView("redirect:order_iut");
		} else if (type_veh.equals("0") || type_veh == "0" || type_veh == null || type_veh.equals(null)
				|| type_veh.equals("null")) {
			model.put("msg", "Please Select VEH CAT");
			return new ModelAndView("redirect:order_iut");
		}else if (mct_main.equals("0") || mct_main == "0" || mct_main == null || mct_main.equals(null)
				|| mct_main.equals("null")) {
			model.put("msg", "Please Select MCT Main");
			return new ModelAndView("redirect:order_iut");
		}else if (ba_no.equals("") || ba_no == "" || ba_no == null || ba_no.equals(null) || ba_no.equals("null")) {
			model.put("msg", "Please Select the Ba No.");
			return new ModelAndView("redirect:order_iut");
		} else {
			Session sessionHQL = null;
			Transaction tx = null;
			try {
				
				
				
				sessionHQL = HibernateUtil.getSessionFactory().openSession();
				tx = sessionHQL.beginTransaction();
				
				
				rs.setIut_authority_no(authority_no);
				rs.setBa_no(ba_no);
				rs.setCreated_by(username);
				rs.setCreated_on(new Date());
				rs.setMain_id(Integer.parseInt(mct_main));
				rs.setSource_sus_no(source_sus_no);
				rs.setStatus(0);
				rs.setTarget_sus_no(target_sus_no);
				rs.setVehical_type(type_veh);
				rs.setUserid(userid);
				sessionHQL.save(rs);
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
				model.put("msg", "Ordered Successfully.");
			} catch (Exception e) {
				try {
					tx.rollback();
					model.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					model.put("msg", "Couldnot roll back transaction " + rbe.getMessage());
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:order_iut_c_veh");
		}
	}	 
	
	@RequestMapping(value = "/admin/track_iut_status", method = RequestMethod.GET)
	public ModelAndView track_iot_status(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status", roleid);
		String username = session.getAttribute("username").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 System.out.println("roleSusNo:- " + roleSusNo);
		 if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}
		//String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
	
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String formation_code = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
				Mmap.put("getCorpsList",corps);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectcorps",select);
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
			
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",formation_code,"","","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Corps")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
			}
		}
		 if(roleAccess.equals("Unit")){
			 //String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
				Mmap.put("unit_sus_no",roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				List<String> formation =mcommon.getformationfromSus_NOList(roleSusNo);
				roleFormationNo = formation.get(0);
				
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
				Mmap.put("getBdeList",bde);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,bde_code,"","",sessionA);			
				Mmap.put("list_serv", list_serv);*/
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
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
//		System.out.println(username+"username"+sus_no_ser+" sus_no_ser "+count_cmd1+" count_cmd1");
		
		ArrayList<ArrayList<String>> list = iutDAO.trackiutstatus_a_b(username,"","","","","","","",roleSubAccess,roleSusNo,roleAccess);
		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("getLineDteList", getLineDteList());
		
		return new ModelAndView("trackiutstatusTiles");
	}

	@RequestMapping(value = "/admin/getSearch_track_status_a_b", method = RequestMethod.POST)
	public ModelAndView getSearch_track_status_a_b(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "cont_comd1", required = false) String cont_comd1,
			@RequestParam(value = "cont_corps1", required = false) String cont_corps1,
			@RequestParam(value = "cont_div1", required = false) String cont_div1,
			@RequestParam(value = "cont_bde1", required = false) String cont_bde1,
			@RequestParam(value = "line_dte1", required = false) String line_dte1) {
		String cont_comd12 = cont_comd1;
		String cont_corps12 = cont_corps1;
		String cont_div12 = cont_div1;
		String	cont_bde12= cont_bde1;
	
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		  if(roleAccess.equals("Formation")){
              if(roleSubAccess.equals("Command")){
                      String formation_code = String.valueOf(roleFormationNo.charAt(0));
                      cont_comd12 = formation_code;
                      Mmap.put("cont_comd1",cont_comd12);
                      
                      List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);
                      Mmap.put("getCommandList",comd);
                      List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
                      Mmap.put("getCorpsList",corps);
                      
                      String select="<option value='0'>--Select--</option>";
                      Mmap.put("selectcorps",select);
                      Mmap.put("selectDiv",select);
                      Mmap.put("selectBde",select);
                      Mmap.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Corps")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=getFormationList("Command",command);
                      Mmap.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      cont_corps12 = cor;
                      Mmap.put("cont_corps1",cont_corps12);
                      
                      List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
                      Mmap.put("getCorpsList",corps);
                      
                      List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
                      Mmap.put("getDivList",Bn);
                      
                      String select="<option value='0'>--Select--</option>";
                      Mmap.put("selectDiv",select);
                      Mmap.put("selectBde",select);
                      Mmap.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Division")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=getFormationList("Command",command);
                      Mmap.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
                      Mmap.put("getCorpsList",corps);
                      
                      String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                      cont_div12 = div;
                      Mmap.put("cont_div1",cont_div12);
                      
                      List<Tbl_CodesForm> Bn=getFormationList("Division",div);
                      Mmap.put("getDivList",Bn);
                      
                      List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
                      Mmap.put("getBdeList",bde);
                      
                      String select="<option value='0'>--Select--</option>";
                      Mmap.put("selectBde",select);
                      
                      Mmap.put("selectLine_dte",select);
              }if(roleSubAccess.equals("Brigade")){
                      String command = String.valueOf(roleFormationNo.charAt(0));
                      List<Tbl_CodesForm> comd=getFormationList("Command",command);
                      Mmap.put("getCommandList",comd);
                      
                      String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                      List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
                      Mmap.put("getCorpsList",corps);
                      
                      String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                      List<Tbl_CodesForm> Bn=getFormationList("Division",div);
                      Mmap.put("getDivList",Bn);
                      
                      cont_bde12 = roleFormationNo;
                      Mmap.put("cont_bde1",cont_bde12);
                      
                      String bde_code = roleFormationNo;
                      List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
                      Mmap.put("getBdeList",bde);
                      
                      String select="<option value='0'>--Select--</option>";
                      Mmap.put("selectLine_dte",select);
              }
      }else if(roleAccess.equals("MISO") || roleAccess.equals("HeadQuarter")) {
              List<Tbl_CodesForm> comd=m.getCommandDetailsList();
              Mmap.put("getCommandList",comd);
              String selectComd ="<option value=''>--Select--</option>";
              String select="<option value='0'>--Select--</option>";
              Mmap.put("selectcomd", selectComd);
              Mmap.put("selectcorps",select);
              Mmap.put("selectDiv",select);
              Mmap.put("selectBde",select);
              Mmap.put("selectLine_dte",select);
              Mmap.put("getLine_DteList",roledao.getLine_DteList(""));
      }else if(roleAccess.equals("Line_dte")){
              List<Tbl_CodesForm> comd=m.getCommandDetailsList();
              Mmap.put("getCommandList",comd);
              String selectComd ="<option value=''>--Select--</option>";
              String select="<option value='0'>--Select--</option>";
              Mmap.put("selectcomd", selectComd);
              Mmap.put("selectcorps",select);
              Mmap.put("selectDiv",select);
              Mmap.put("selectBde",select);
              Mmap.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
      } else if(roleAccess.equals("Unit")){
    	  Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			 List<Tbl_CodesForm> comd=m.getCommandDetailsList();
			 Mmap.put("getCommandList",comd);
			 
		}
      
      
      else {
              return new ModelAndView("AccessTiles");
      }
      
		  	String formCode ="";
		  		if(!cont_bde12.equals("0") && !cont_bde12.equals("")){
		  				formCode = cont_bde12;
		  			}else {
		  					if(!cont_div12.equals("0") && !cont_div12.equals("")){
		  							formCode = cont_div12;
		  						}else {
		  								if(!cont_corps12.equals("0") && !cont_corps12.equals("")){
		  									formCode = cont_corps12;
		  									}else {
		  										if(!cont_comd12.equals("-1") && !cont_comd12.equals("")){
		  											formCode = cont_comd12;
		  											}
		  										}
		  								}
		  					}
		  		String username = session.getAttribute("username").toString();
	   Mmap.put("cont_comd1",cont_comd12);
       Mmap.put("cont_corps1",cont_corps12);
       Mmap.put("cont_div1",cont_div12);
       Mmap.put("cont_bde1",cont_bde12);

		Mmap.put("list",
				iutDAO.trackiutstatus_a_b(username, unit_sus_no, unit_name, cont_comd1, cont_corps1, cont_div1, cont_bde1,line_dte1, roleSubAccess,roleSusNo,roleAccess));
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("unit_name1", unit_name);
//		Mmap.put("cont_comd", cont_comd);
//		Mmap.put("cont_corps", cont_corps);
//		Mmap.put("cont_div", cont_div);
		Mmap.put("line_dte1", line_dte1);
		Mmap.put("getLineDteList", getLineDteList());
		return new ModelAndView("trackiutstatusTiles");
	}
	
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Miso_Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Miso_Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
		}

	@RequestMapping(value = "/admin/reject_iut_url", method = RequestMethod.POST)
	public ModelAndView reject_iut_url(@ModelAttribute("id1") int id, HttpSession sessionA, HttpServletRequest request,ModelMap Mmap,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "update TB_TMS_IUT set status=:status" + " where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setInteger("id", id).executeUpdate();
		if(app > 0) {
			msg = "Data Reject Successfully";
		}
		tx.commit();
		sessionHQL.close();
		Mmap.put("msg", msg);
		return new ModelAndView("redirect:track_iut_status");
	}


@RequestMapping(value = "/admin/approve_iut_url", method = RequestMethod.POST)
	public ModelAndView approve_iut_url(@ModelAttribute("id2") int id, HttpSession sessionA, HttpServletRequest request,ModelMap Mmap,
			ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "type_veh2", required = false) String type_veh, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_TMS_IUT Details = iutDAO.getsusnoByid(id);
		String sus_no = Details.getTarget_sus_no();
		String ba_no = Details.getBa_no();
		String veh_type = Details.getVehical_type();
		String source_sus_no =Details.getSource_sus_no();
		String values[] = ba_no.split(",");
		String ba_exist = null;
		int cif=0;
		int app=0;
		for (int j = 0; j < values.length; j++) {
		if(veh_type.equals("A")) {
			Query q = sessionHQL.createQuery("select count(ba_no) from TB_TMS_CENSUS_RETURN where sus_no=:sus and ba_no=:ba_no and status='1'");
			q.setString("sus",source_sus_no).setString("ba_no",values[j]);
			 cif = ((Number)q.uniqueResult()).intValue();
		}
		else	if(veh_type.equals("B")) {
			Query q = sessionHQL.createQuery("select count(ba_no) from TB_TMS_MVCR_PARTA_DTL where sus_no=:sus and ba_no=:ba_no and status='1'");
			q.setString("sus",source_sus_no).setString("ba_no",values[j]);
			 cif = ((Number)q.uniqueResult()).intValue();
		}
		else	if(veh_type.equals("C")) {
			Query q = sessionHQL.createQuery("select count(em_no) from TB_TMS_EMAR_REPORT where sus_no=:sus and em_no=:ba_no and status='1'");
			q.setString("sus",source_sus_no).setString("ba_no",values[j]);
			 cif = ((Number)q.uniqueResult()).intValue();
		}
		if (cif == 0) {
            if (ba_exist == null || ba_exist.isEmpty()
                    || ba_exist.equalsIgnoreCase("null")) {
            	ba_exist = values[j];
            } else {
            	ba_exist += "," + values[j];
                }
            }
		}
		
		if (ba_exist == null ) {
		String hqlUpdate;
		hqlUpdate = "update TB_TMS_IUT set status=:status" + " where id=:id";
		 app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("id", id).executeUpdate();
		for (int i = 0; i < values.length; i++) {
			String ba = values[i];
			if (type_veh.equals("A")) {
				hqlUpdate = "update TB_TMS_CENSUS_RETURN set sus_no=:sus_no where ba_no=:ba_no";
				app = sessionHQL.createQuery(hqlUpdate).setString("sus_no", sus_no).setString("ba_no", ba).executeUpdate();
				if(app > 0) {
					Query qce = null;
					qce = sessionHQL.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.vehcl_classfctn,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_CENSUS_RETURN e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.ba_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :ba " );
					qce.setParameter("ba", ba);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list();
					sessionHQL.flush();
					sessionHQL.clear();
					
					TB_TMS_RELIEF_PROG_HISTORY_A rc = new TB_TMS_RELIEF_PROG_HISTORY_A();
					rc.setFrom_unit_sus_no(Details.getSource_sus_no());
					rc.setTo_unit_sus_no(Details.getTarget_sus_no());
					rc.setApprove_date(new Date());
					rc.setDepot_name(Details.getTarget_sus_no());
					rc.setBa_no(ba);
					rc.setAuth(Details.getIut_authority_no());
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					sessionHQL.save(rc);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			}
			else if (type_veh.equals("B")) {
				hqlUpdate = "update TB_TMS_MVCR_PARTA_DTL set sus_no=:sus_no where ba_no=:ba_no";
				app = sessionHQL.createQuery(hqlUpdate).setString("sus_no", sus_no).setString("ba_no", ba).executeUpdate();
				if(app > 0) {
					Query qce = null;
					qce = sessionHQL.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.classification,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_MVCR_PARTA_DTL e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.ba_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :ba " );
					qce.setParameter("ba", ba);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list(); 
					sessionHQL.flush();
					sessionHQL.clear();
					
					TB_TMS_RELIEF_PROG_HISTORY rc = new TB_TMS_RELIEF_PROG_HISTORY();
					rc.setFrom_unit_sus_no(Details.getSource_sus_no());
					rc.setTo_unit_sus_no(Details.getTarget_sus_no());
					rc.setApprove_date(new Date());
					rc.setDepot_name(Details.getTarget_sus_no());
					rc.setBa_no(ba);
					rc.setAuth(Details.getIut_authority_no());
					rc.setCreated_by(username);
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					sessionHQL.save(rc);
					sessionHQL.flush();
					sessionHQL.clear();
				}
			} else if (type_veh.equals("C")) {
				hqlUpdate = "update TB_TMS_EMAR_REPORT set sus_no=:sus_no where em_no=:em_no";
				app = sessionHQL.createQuery(hqlUpdate).setString("sus_no", sus_no).setString("em_no", ba).executeUpdate();
				if(app > 0) {
					Query qce = null;
					qce = sessionHQL.createQuery("select b.ba_no,b.mct,b.engine_no,b.chasis_no,e.classification,e.sus_no,n.unit_name,m.std_nomclature  from TB_TMS_BANUM_DIRCTRY b,TB_TMS_EMAR_REPORT e,Miso_Orbat_Unt_Dtl n,TB_TMS_MCT_MASTER m  where b.ba_no=e.em_no and n.sus_no =e.sus_no and b.mct = m.mct and  b.ba_no = :em " );
					qce.setParameter("em", ba);
					@SuppressWarnings("unchecked")
					List<Object> result = (List<Object>) qce.list();
					sessionHQL.flush();
					sessionHQL.clear();
					TB_TMS_RELIEF_PROG_HISTORY_C rc = new TB_TMS_RELIEF_PROG_HISTORY_C();
					rc.setFrom_unit_sus_no(Details.getSource_sus_no());
					rc.setTo_unit_sus_no(Details.getTarget_sus_no());
					rc.setApprove_date(new Date());
					rc.setDepot_name(Details.getTarget_sus_no());
					rc.setBa_no(ba);
					rc.setAuth(Details.getIut_authority_no());
					Iterator itr = result.iterator();
					while(itr.hasNext()){
						Object[] obj = (Object[]) itr.next();
						rc.setClassification(String.valueOf(obj[4]));
						rc.setEngine_no(String.valueOf(obj[2]));
						rc.setChasis_no(String.valueOf(obj[3]));
						BigInteger gh = new BigInteger(String.valueOf( obj[1]));
						rc.setMct(gh);
						rc.setStd_nomclature(String.valueOf(obj[7]));
					}
					sessionHQL.save(rc);
				}
			}
//		}
		
		
		}
		if(app > 0) {
			msg = "Data Approve Successfully";
		}
		}
		
		else	if (ba_exist != null && !ba_exist.isEmpty()
                && !ba_exist.equalsIgnoreCase("null")) {
            msg=    "Reject these data. " +ba_exist + ": These BA numbers already transferred via partial swap screen.";
        }
		
 		tx.commit();
 		sessionHQL.close();
		Mmap.put("msg", msg);
		if (type_veh.equals("C")) {
			return new ModelAndView("redirect:track_iut_status_c_veh");
		}
  		return new ModelAndView("redirect:track_iut_status");
	}
	public List<Object[]> getLineDteList(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct line_dte_sus,line_dte_name from Tb_Miso_Orbat_Line_Dte where line_dte_sus is not null and line_dte_sus !=''  order by line_dte_name ");
//		q.setParameter("domainid", "SPONSERDTE");
		@SuppressWarnings("unchecked")
		List<Object[]>  list = (List<Object[]> ) q.list();
		tx.commit();
		session.close();
		return list;
	}
}