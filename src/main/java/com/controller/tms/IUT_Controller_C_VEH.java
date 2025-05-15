package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.IUTDAO;
import com.models.TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class IUT_Controller_C_VEH {
	
	@Autowired
	RoleBaseMenuDAO roledao;

	@Autowired
	private IUTDAO iutDAO;

	ValidationController validation = new ValidationController();
	
	IUT_Controller iut = new IUT_Controller();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@RequestMapping(value = "/order_iut_c_veh", method = RequestMethod.GET)
	public ModelAndView order_iut_c_veh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("order_iut_c_veh", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("order_iut_c_veh_Tiles");
	}
	
	
	@RequestMapping(value = "/admin/track_iut_status_c_veh", method = RequestMethod.GET)
	public ModelAndView track_iut_status_c_veh(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("track_iut_status_c_veh", roleid);
		String username = session.getAttribute("username").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		
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
				List<Tbl_CodesForm> comd= iut.getFormationList("Command",formation_code);	
				Mmap.put("getCommandList",comd);
				List<Tbl_CodesForm> corps=iut.getFormationList("Corps",formation_code);
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
				List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				List<Tbl_CodesForm> Bn=iut.getFormationList("Division",cor);
				Mmap.put("getDivList",Bn);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectDiv",select);
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,"","","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Division")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=iut.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				List<Tbl_CodesForm> bde=iut.getFormationList("Brigade",div);
				Mmap.put("getBdeList",bde);
				
				String select="<option value='0'>--Select--</option>";
				Mmap.put("selectBde",select);
				/*ArrayList<ArrayList<String>> list_serv = held.GetdaoSearch_Report_jco_holding1111("","",command,cor,div,"","","",sessionA);		
				Mmap.put("list_serv", list_serv);*/
			}
			if(roleSubAccess.equals("Brigade")) {
				String command = String.valueOf(roleFormationNo.charAt(0));
				List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=iut.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = iut.getFormationList("Brigade",bde_code);
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
				List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
				Mmap.put("getCommandList",comd);
				
				String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
				List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
				Mmap.put("getCorpsList",corps);
				
				String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				List<Tbl_CodesForm> Bn=iut.getFormationList("Division",div);
				Mmap.put("getDivList",Bn);
				
				String bde_code = roleFormationNo;
				List<Tbl_CodesForm> bde = iut.getFormationList("Brigade",bde_code);
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
		ArrayList<ArrayList<String>> list = iutDAO.trackiutstatus(username,"","","","","","","", roleSubAccess, roleSusNo, roleAccess);
		Mmap.put("list", list);
		Mmap.put("date", date1);
		Mmap.put("msg", msg);
		Mmap.put("getLineDteList", iut.getLineDteList());
		
		return new ModelAndView("trackiutstatus_c_veh_Tiles");
	}
	@RequestMapping(value = "/admin/getSearch_track_status_C", method = RequestMethod.POST)
	public ModelAndView getSearch_track_status_C(ModelMap Mmap, HttpSession session, HttpServletRequest request,
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
		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();
		String roleid = session.getAttribute("roleid").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		Boolean val = roledao.ScreenRedirect("track_iut_status_c_veh", roleid);
		String username = session.getAttribute("username").toString();
		
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		 if(roleAccess.equals("Formation")){
             if(roleSubAccess.equals("Command")){
                     String formation_code = String.valueOf(roleFormationNo.charAt(0));
                     cont_comd12 = formation_code;
                     Mmap.put("cont_comd1",cont_comd12);
                     
                     List<Tbl_CodesForm> comd= iut.getFormationList("Command",formation_code);
                     Mmap.put("getCommandList",comd);
                     List<Tbl_CodesForm> corps=iut.getFormationList("Corps",formation_code);
                     Mmap.put("getCorpsList",corps);
                     
                     String select="<option value='0'>--Select--</option>";
                     Mmap.put("selectcorps",select);
                     Mmap.put("selectDiv",select);
                     Mmap.put("selectBde",select);
                     Mmap.put("selectLine_dte",select);
             }if(roleSubAccess.equals("Corps")){
                     String command = String.valueOf(roleFormationNo.charAt(0));
                     List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
                     Mmap.put("getCommandList",comd);
                     
                     String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                     cont_corps12 = cor;
                     Mmap.put("cont_corps1",cont_corps12);
                     
                     List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
                     Mmap.put("getCorpsList",corps);
                     
                     List<Tbl_CodesForm> Bn=iut.getFormationList("Division",cor);
                     Mmap.put("getDivList",Bn);
                     
                     String select="<option value='0'>--Select--</option>";
                     Mmap.put("selectDiv",select);
                     Mmap.put("selectBde",select);
                     Mmap.put("selectLine_dte",select);
             }if(roleSubAccess.equals("Division")){
                     String command = String.valueOf(roleFormationNo.charAt(0));
                     List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
                     Mmap.put("getCommandList",comd);
                     
                     String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                     List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
                     Mmap.put("getCorpsList",corps);
                     
                     String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                     cont_div12 = div;
                     Mmap.put("cont_div1",cont_div12);
                     
                     List<Tbl_CodesForm> Bn=iut.getFormationList("Division",div);
                     Mmap.put("getDivList",Bn);
                     
                     List<Tbl_CodesForm> bde=iut.getFormationList("Brigade",div);
                     Mmap.put("getBdeList",bde);
                     
                     String select="<option value='0'>--Select--</option>";
                     Mmap.put("selectBde",select);
                     
                     Mmap.put("selectLine_dte",select);
             }if(roleSubAccess.equals("Brigade")){
                     String command = String.valueOf(roleFormationNo.charAt(0));
                     List<Tbl_CodesForm> comd=iut.getFormationList("Command",command);
                     Mmap.put("getCommandList",comd);
                     
                     String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
                     List<Tbl_CodesForm> corps=iut.getFormationList("Corps",cor);
                     Mmap.put("getCorpsList",corps);
                     
                     String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
                     List<Tbl_CodesForm> Bn=iut.getFormationList("Division",div);
                     Mmap.put("getDivList",Bn);
                     
                     cont_bde12 = roleFormationNo;
                     Mmap.put("cont_bde1",cont_bde12);
                     
                     String bde_code = roleFormationNo;
                     List<Tbl_CodesForm> bde = iut.getFormationList("Brigade",bde_code);
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
		  
	   Mmap.put("cont_comd1",cont_comd12);
      Mmap.put("cont_corps1",cont_corps12);
      Mmap.put("cont_div1",cont_div12);
      Mmap.put("cont_bde1",cont_bde12);
		Mmap.put("list",
				iutDAO.trackiutstatus(username,unit_sus_no,unit_name,cont_comd1,cont_corps1,cont_div1,cont_bde1,line_dte1, roleSubAccess, roleSusNo, roleAccess));
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("line_dte1", line_dte1);
		Mmap.put("getLineDteList", iut.getLineDteList());
	
		return new ModelAndView("trackiutstatus_c_veh_Tiles");
	}
	
	
	@RequestMapping(value = "/UploadFile_Voucher2")
	public ModelAndView UploadFile_Voucher2(
   @ModelAttribute("ba_nop") String updateid,
   @ModelAttribute("target_unit_sus") String target_unit_sus_no,@ModelAttribute("type_of_veh") String type_of_veh,
   ModelMap Mmap,
    @RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpServletRequest request, HttpSession sessionEdit) {
		int userid = Integer.parseInt(sessionEdit.getAttribute("userId").toString());
	
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
	
		Mmap.put("type_of_veh", type_of_veh);
 		Mmap.put("msg", msg);
		Mmap.put("ba_nop", updateid);
		Mmap.put("target_unit_sus_no", target_unit_sus_no);
		return new ModelAndView("UploadFileTilesC");
	}
	@RequestMapping(value = "admin/IutUploadFile2",method=RequestMethod.POST)
	public ModelAndView IutUploadFile2(@ModelAttribute("UploadFileCMD") TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER rs,
	        @RequestParam(value = "doc_upload1", required = false) MultipartFile doc_upload1,
	        @RequestParam(value = "doc_upload2", required = false) MultipartFile doc_upload2,
	        @RequestParam(value = "u_file_hidden1", required = false) String u_file_hidden1,
	        @RequestParam(value = "u_file_hidden2", required = false) String u_file_hidden2,
	        @RequestParam(value = "update_document", required = false) String update_document,
	        String msg,
	        HttpServletRequest request, ModelMap model, HttpSession session
			) throws IOException {
		String roleid = session.getAttribute("roleid").toString();
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction txn = sessionhql.beginTransaction();
		String extension = "";
		String fname = "";
		String extension1 = "";
		String fname1 = "";
		
		String ba_no = request.getParameter("ba_no");
		String target_sus_no = request.getParameter("target_sus_no");
		
		String upload_imgEXt1 = FilenameUtils.getExtension(doc_upload1.getOriginalFilename());
		String upload_imgEXt2 = FilenameUtils.getExtension(doc_upload2.getOriginalFilename());
		

	
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
	
		Query q0=sessionhql.createQuery("select count(id) from TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER where ba_no=:ba_no and target_sus_no=:target_sus_no");
		
		q0.setString("ba_no", ba_no);
		
		q0.setString("target_sus_no", target_sus_no);
		
		Long c = (Long) q0.uniqueResult();
		
	
		
			if(u_file_hidden1.equals(""))	
			{
				
			if (!doc_upload1.isEmpty()) {
				if (!upload_imgEXt1.toUpperCase().equals("pdf".toUpperCase())) {
			        model.put("msg", "Only *.pdf file extensions allowed");
					return new ModelAndView("redirect:UploadFileC");
						}
				
			
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
					 rs.setUpload_voucher1(fname.replace("\\","/"));
			}
			}
		
			if(u_file_hidden2.equals(""))	
			{
			
			if (!doc_upload2.isEmpty()) {
				
				if (!upload_imgEXt2.toUpperCase().equals("pdf".toUpperCase())) {
			        model.put("msg", "Only *.pdf file extensions allowed");
					return new ModelAndView("redirect:UploadFileC");
						}
				DateWithTimeStampController timestamp2 = new DateWithTimeStampController();
				
				byte[] bytes = doc_upload2.getBytes();
				String tmsFilePath1 = session.getAttribute("tmsFilePath").toString();
				
				File dir = new File(tmsFilePath1);
				if (!dir.exists())
					dir.mkdirs();
				String filename = doc_upload2.getOriginalFilename();
				int i = filename.lastIndexOf('.');
				if (i >= 0) {
					extension = filename.substring(i + 1);
				}
				fname1 = dir.getAbsolutePath() + File.separator + timestamp2.currentDateWithTimeStampString() + "_2_"
						+ userid + "_TMSDOC." + extension;


				File serverFile = new File(fname1);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					 rs.setUpload_voucher2(fname1.replace("\\","/"));
			}
			}
			 Session sessionHQL = null;
				Transaction tx = null;
				if(c==0)
				{
				try {
					sessionHQL = HibernateUtil.getSessionFactory().openSession();
					tx = sessionHQL.beginTransaction();
					rs.setBa_no(ba_no);
					rs.setTarget_sus_no(target_sus_no);
					sessionHQL.save(rs);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					model.put("msg", "Document Uploaded Successfully.");
				} 
				catch (Exception e) {
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
				}
				else {
					
					  if (update_document != null && !update_document.isEmpty()) {
					    
					        try {
					            sessionHQL = HibernateUtil.getSessionFactory().openSession();
					            tx = sessionHQL.beginTransaction();

					            if (update_document.equals("update_document1")) {
					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
					            	int id = list.get(0).getId();
					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					                if (existingRecord != null) {
					                   
					                    existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
					               
					                    sessionHQL.update(existingRecord);
					            } 
					                }else if (update_document.equals("update_document2")) {
					                	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
						            	int id = list.get(0).getId();
					                	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					                	 if (existingRecord != null) {
					                         
					                         existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
					                       
					                         sessionHQL.update(existingRecord);
					                     }
					            } else if (update_document.equals("update_documents")) {
					            	List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_no, target_sus_no, session);
					            	int id = list.get(0).getId();

					            	TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER existingRecord = (TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER) sessionHQL.get(TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER.class, id);
					            	 if (existingRecord != null) {
					                    
					                     existingRecord.setUpload_voucher1(rs.getUpload_voucher1());
					                     existingRecord.setUpload_voucher2(rs.getUpload_voucher2());
					                     sessionHQL.update(existingRecord);
					                 }
					            }

					            sessionHQL.flush();
					            sessionHQL.clear();
					            tx.commit();
					            model.put("msg", "Document/s Updated Successfully.");
					        } catch (Exception e) {
					            // Handle exceptions
					        } finally {
					            if (sessionHQL != null) {
					                sessionHQL.close();
					            }
					        }
					    }

				}
			return new ModelAndView("redirect:track_iut_status_c_veh");
	}

	@RequestMapping(value = "/admin/getDownloadVoucher2", method = RequestMethod.POST)
	public ModelAndView getDownloadVoucher2(@ModelAttribute("ba_num") String ba_num,
			@ModelAttribute("target_unit_sus_no") String target_unit_sus_no,
			@ModelAttribute("file_name") String file_name1,@ModelAttribute("type_of_veh") String type_of_veh,
			ModelMap model, HttpSession session,String msg,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		String EXTERNAL_FILE_PATH = "";
		
	    List<TB_TMS_IUT_TRACK_STATUS_UPLOAD_VOUCHER> list = iutDAO.getIutDocument(ba_num,target_unit_sus_no ,session);
	    
	    if(list.size() ==0) {
	    	return new ModelAndView("redirect:UploadFile_Voucher2?msg=Sorry. The file you are looking for does not exist");
	    }
	    
	    else {
	
		if(file_name1.equals("doc_upload1")) {
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no, session).get(0).getUpload_voucher1();
			
		}
	
		if(file_name1.equals("doc_upload2")) {	
			EXTERNAL_FILE_PATH =iutDAO.getIutDocument(ba_num,target_unit_sus_no,session).get(0).getUpload_voucher2();
			
		}
		if(EXTERNAL_FILE_PATH == null)
		{
			return new ModelAndView("redirect:UploadFile_Voucher2?msg=Sorry. The file you are looking for does not exist");
		}
		File file = null;
		file = new File(EXTERNAL_FILE_PATH);
		try {
			if (file == null || !file.exists() ) {
				return new ModelAndView("redirect:UploadFile_Voucher2?msg=Sorry. The file you are looking for does not exist");
			}
		} catch (Exception exception) {
		}
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());
		InputStream inputStream = null;
		try {
		    	inputStream = new BufferedInputStream(new FileInputStream(file));
		    	FileCopyUtils.copy(inputStream, response.getOutputStream());
		    	
		    } catch (FileNotFoundException e) {
			
			}
	    }
		return new ModelAndView("redirect:UploadFile_Voucher2?msg=Download Successfully."); 
	    }
}
