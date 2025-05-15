package com.controller.cue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.ValidationController;
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.cue.cueStandardEntitlementTransportDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.Miso_Orbat_Unt_Dtl;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class cueStandardEntitlementForTransport {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	private WepePersMdfsDAO wepepersmdfs;	
	@Autowired
	private cueStandardEntitlementTransportDAO cueTransport ;	
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	
	@Autowired
	private Cue_wepe_conditionDAO vetting;
	
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	cueContoller M = new cueContoller();
	ValidationController validation = new ValidationController();
	@RequestMapping(value = "/standardEntitlementForTransport", method = RequestMethod.GET)
	public ModelAndView standardEntitlementForTransport(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("standardEntitlementForTransport", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("standardEntitlementForTransportTiles");
	}
	
	
	@RequestMapping(value = "/admin/standardentitlement1", method = RequestMethod.POST)
	public ModelAndView standardentitlement1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe1", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("we_pe1", we_pe);
			Mmap.put("status1", status);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("we_pe_no1", we_pe_no);
		
			 List<Map<String, Object>> list= cueTransport.getAttributeFromTransEntitlementDtl1(mct_no,we_pe_no,status,roleType);
		
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
		    Mmap.put("roleType", roleType);		
		return new ModelAndView("standardEntitlementForTransportTiles");
	}
	
	
	//////---- report
	@RequestMapping(value = "/transportAuthorizationUnderDTLAction", method = RequestMethod.POST)
	public ModelAndView addItemEntryForm(@ModelAttribute("transportAuthorizationUnderDTLCMD") CUE_TB_MISO_WEPE_TRANSPORT_DET rs,
			HttpServletRequest request,ModelMap model,HttpSession session) {
			
		String we_pe_no="";
		we_pe_no= request.getParameter("we_pe_no");	
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());		
		int roleid = (Integer)session.getAttribute("roleid");		
		String we_pe = request.getParameter("we_pe");		
		Session session0 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = session0.beginTransaction();
			
		try
		{
				if(we_pe == ""  || we_pe==null || we_pe=="null" || we_pe.equals(null) )
				{
					model.put("msg", "Please Select Document");
					return new ModelAndView("redirect:standardEntitlementForTransport");
				}
				 if(rs.getWe_pe_no().equals(""))
					{
						model.put("msg", "Please Enter WE/PE No");
						return new ModelAndView("redirect:standardEntitlementForTransport");
					}
				 if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
				 		model.put("msg",validation.wepenoMSG);
						return new ModelAndView("redirect:standardEntitlementForTransport");
					}
				 if(rs.getMct_no().equals(""))
					{
						model.put("msg", "Please Enter MCT No");
						return new ModelAndView("redirect:standardEntitlementForTransport");
					}
					if(validation.checkMctLength(rs.getMct_no())  == false){
				 		model.put("msg",validation.mctnoMSG);
						return new ModelAndView("redirect:standardEntitlementForTransport");
					}
				 if(rs.getAuth_amt()==0)
					{
						model.put("msg", "Please Enter Authorization Amount");
						return new ModelAndView("redirect:standardEntitlementForTransport");
					} 
					String auth_amt =  Double.toString(rs.getAuth_amt());
	                if(validation.checkAuth_amtLength(auth_amt)  == false){
	                        model.put("msg",validation.auth_amtMSG);
	                        return new ModelAndView("redirect:standardEntitlementForTransport");
	                }
	                if(validation.checkRemarksLength(rs.getRemarks())  == false){
	    		 		model.put("msg",validation.remarksMSG);
	    				return new ModelAndView("redirect:standardEntitlementForTransport");
	    			}
				rs.setRoleid(roleid);	
				String mct = request.getParameter("mct_no");				
				
				 rs.setWe_pe_no(request.getParameter("we_pe_no"));
				rs.setCreated_by(username);
				rs.setCreated_on(creadtedate);
				//rs.setModified_by(username);
				//rs.setModified_on(modifieddate);
				rs.setMct_no(request.getParameter("mct_no"));
				
				rs.setRemarks(request.getParameter("remarks"));
				rs.setEntity("UNIT");
				rs.setStatus("0");
				
				int did = (Integer) session0.save(rs);
				session0.getTransaction().commit();
				session0.close();									
				model.put("msg", "Data saved Successfully");		
			}	
			catch (Exception e) {
				session0.getTransaction().rollback();
				//return null;
			}
			finally{
				
			}
			 List<Map<String, Object>> list= cueTransport.getAttributeFromTransEntitlementDtl1("",we_pe_no,"","");
				model.put("we_pe_no1", we_pe_no);
				model.put("we_pe1", we_pe);
				model.put("list", list);
				model.put("list.size()", list.size());						
		return new ModelAndView("standardEntitlementForTransportTiles");
	}
	
///***********************************SEARCH CODE **************************************************************************************/////		
		
	@RequestMapping(value = "/admin/searchstandardentitlement1", method = RequestMethod.POST)
	public ModelAndView searchstandardentitlement1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "mct_no1", required = false) String mct_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "std_nomclature1", required = false) String std_nomclature,
			@RequestParam(value = "table_title1", required = false) String table_title,
			@RequestParam(value = "we_pe1", required = false) String we_pe){
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("table_title1", table_title);
			Mmap.put("std_nomclature1", std_nomclature);
			Mmap.put("status1", status);
			Mmap.put("we_pe1", we_pe);
			 List<Map<String, Object>> list= cueTransport.getAttributeFromTransEntitlementDtl(we_pe_no,mct_no,we_pe,status,roleType,roleAccess,roleSusNo);
			
			Mmap.put("list", list);
					
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());				
		return new ModelAndView("search_standard_entitlementTile");
	}
		
		@RequestMapping(value = "/admin/searchstandardentitlement", method = RequestMethod.GET)
		public ModelAndView search_item_master(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("searchstandardentitlement", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("msg", msg);
			return new ModelAndView("search_standard_entitlementTile");
		}
		
		@RequestMapping(value = "/admin/ApprovedTransEntitlementDtl", method = RequestMethod.POST)
		public ModelAndView ApprovedTransEntitlementDtl(@ModelAttribute("appid") int appid,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
				@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
				@RequestParam(value = "mct_no2", required = false) String mct_no,
				@RequestParam(value = "std_nomclature2", required = false) String std_nomclature,
				@RequestParam(value = "table_title2", required = false) String table_title,
				@RequestParam(value = "status2", required = false) String status,
				@RequestParam(value = "we_pe2", required = false) String we_pe){
			String username = session.getAttribute("username").toString();
			
			String mst = cueTransport.setApprovedtrans(appid,username,we_pe_no);
			if(mst.equals("Approved Successfully")) {
				vetting.updateVettingDtl( we_pe_no, "2");
			}
			Mmap.put("msg", mst);	
			String roleType = session.getAttribute("roleType").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("mct_no1", mct_no);
			Mmap.put("status1", status);
			Mmap.put("we_pe1", we_pe);
			Mmap.put("table_title1", table_title);
			Mmap.put("std_nomclature1", std_nomclature);
			 List<Map<String, Object>> list= cueTransport.getAttributeFromTransEntitlementDtl(we_pe_no,mct_no,we_pe,status,roleType,roleAccess,roleSusNo);
			 
			Mmap.put("list", list);				
			Mmap.put("roleType", roleType);
			Mmap.put("list.size()", list.size());
			return new ModelAndView("search_standard_entitlementTile");
		}
		
		@RequestMapping(value = "/deleterejectTransEntitlementDtlAdd", method = RequestMethod.POST)
		public @ResponseBody List<String> deleterejectTransEntitlementDtlAdd(int deleteid) {			
			List<String> list2 = new ArrayList<String>();
			list2.add(cueTransport.setDeletetrans(deleteid));
			return list2;
		}

		@RequestMapping(value = "/admin/updatedeleterejectTransEntitlementDtl")
		public ModelAndView updatedeleterejectTransEntitlementDtl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
			model.put("edititem_masterCmd", cueTransport.getCUE_Trans_Authorizarion_id(updateid));	
			model.put("msg", msg);
			return new ModelAndView("editstandardentitlementTile");
		}
		
		@RequestMapping(value = "/edittrans_AuthAction", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView edittrans_AuthAction(@ModelAttribute("edititem_masterCmd") CUE_TB_MISO_WEPE_TRANSPORT_DET updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession session,HttpSession sessionEdit) {
			String roleType = sessionEdit.getAttribute("roleType").toString();
			String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
				return new ModelAndView("AccessTiles");
			}
			try {
				
				String auth_amt1 = request.getParameter("auth_amt1");
				if(auth_amt1.equals("")) {
					auth_amt1 = "0";
				}
				Integer auth_amt =  Integer.parseInt(auth_amt1);
				 if(auth_amt.equals("") || auth_amt.equals("0") || auth_amt==0 )
				{
					model.put("updateid",updateid.getId() );
					model.put("msg", "Please Enter Authorization Amount");
					return new ModelAndView("redirect:updatedeleterejectTransEntitlementDtl");
					
				}
			 String auth_amt2 =  Double.toString(updateid.getAuth_amt());
                if(validation.checkAuth_amtLength(auth_amt2)  == false){
                        model.put("msg",validation.auth_amtMSG);
                        return new ModelAndView("redirect:updatedeleterejectTransEntitlementDtl");
                }
                if(validation.checkRemarksLength(updateid.getRemarks())  == false){
    		 		model.put("msg","Please Enter Valid Length Of Remarks");
    				return new ModelAndView("redirect:updatedeleterejectTransEntitlementDtl");
    			}
				updateid.setAuth_amt(Integer.parseInt(auth_amt1));
				String username = session.getAttribute("username").toString();
				cueTransport.UpdateTransMasterValue(updateid, username);		
				model.put("msg", "Updated Successfully");
				
			}	
			catch (Exception e) {
				
			}
				return new ModelAndView("redirect:standardEntitlementForTransport");
		} 
		
	
		@RequestMapping(value = "/viewstandardEntitlementForTransport", method = RequestMethod.GET)
		public ModelAndView viewstandardEntitlementForTransport(String subModule2,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String  roleid = session.getAttribute("roleid").toString();
			Mmap.put("wepetype", subModule2);
			Mmap.put("roleid", roleid);
			Mmap.put("msg", msg);
			return new ModelAndView("viewstandardEntitlementForTransportTiles");
		}
		
		
		@RequestMapping(value = "/viewstandardEntitlementForTransport_Pers", method = RequestMethod.GET)
		public ModelAndView viewstandardEntitlementForTransport_Pers(String subModule2,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String  roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("viewstandardEntitlementForTransport_Pers", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("roleAccess", roleAccess);
			
			if(roleAccess.equals("Unit")) {
				Mmap.put("sus_no01", roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}else {
				String jsCSS = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script> \r\n" + 
						"\r\n" + 
						"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
						"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
						"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>";
				Mmap.put("jsCSS", jsCSS);
			}
			
			Mmap.put("msg", msg);
			Mmap.put("roleid", roleid); 
			Mmap.put("wepe", "1");  // for PERSONNEL value is 1
			return new ModelAndView("viewstandardEntitlementForTransportTiles");
		}
		
		@RequestMapping(value = "/viewstandardEntitlementForTransport_Trans", method = RequestMethod.GET)
		public ModelAndView viewstandardEntitlementForTransport_Trans(String subModule2,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String  roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("viewstandardEntitlementForTransport_Trans", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			Mmap.put("roleAccess", roleAccess);
			
			if(roleAccess.equals("Unit")) {
				Mmap.put("sus_no01", roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}else {
				String jsCSS = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script> \r\n" + 
						"\r\n" + 
						"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
						"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
						"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>";
				Mmap.put("jsCSS", jsCSS);				
			}
			
			Mmap.put("msg", msg);
			Mmap.put("roleid", roleid);
			Mmap.put("wepe", "2"); // for TRANSPORT value is 2
			return new ModelAndView("viewstandardEntitlementForTransportTiles");
		}
			
		@RequestMapping(value = "/viewstandardEntitlementForTransport_Weap", method = RequestMethod.GET)
		public ModelAndView viewstandardEntitlementForTransport_Weap(String subModule2,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			String  roleAccess =  session.getAttribute("roleAccess").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("viewstandardEntitlementForTransport_Weap", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			Mmap.put("roleAccess", roleAccess);
			
			if(roleAccess.equals("Unit")) {
				Mmap.put("sus_no01", roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				
				List<Map<String, Object>> list = cueTransport.getViewUnitDtlTransReport(roleSusNo,roleAccess);
				if(!list.isEmpty()) {
					Mmap.put("ct_part_i_ii",list.get(0).get("ct_part_i_ii"));
					Mmap.put("code_value",list.get(0).get("code"));
				}
			}
			else {
				String jsCSS = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
						"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script> \r\n" + 
						"\r\n" + 
						"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
						"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
						"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>";
				Mmap.put("jsCSS", jsCSS);
			}
			
			Mmap.put("msg", msg);
			Mmap.put("roleid", roleid);
			Mmap.put("wepe", "3"); // for WEAPON value is 3
			return new ModelAndView("viewstandardEntitlementForTransportTiles");
		}

				
		@RequestMapping(value = "/getUnitsNameListActive",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getUnitsNameListActive(HttpSession session1,HttpSession sessionUserId,String unit_name) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {	
			String  roleAccess =  session1.getAttribute("roleAccess").toString();
			String roleSubAccess = session1.getAttribute("roleSubAccess").toString();
			String  rolecode= ""; 
			
			if(roleAccess.equals("Formation")){	
				rolecode =  session1.getAttribute("roleFormationNo").toString();
			}
			else if(roleAccess.equals("Line_dte")) {	
				rolecode =  session1.getAttribute("roleArmCode").toString();
			}
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(roleAccess.equals("MISO")) {
				q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no = 'Active'").setMaxResults(20);
				q.setParameter("unit_name", "%"+unit_name.toUpperCase().toString()+"%");
			}
			else if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					rolecode = String.valueOf(rolecode.charAt(0));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", rolecode+"%");
				}
				if(roleSubAccess.equals("Corps")) {
					rolecode = String.valueOf(rolecode.charAt(0)) + String.valueOf(rolecode.charAt(1)) + String.valueOf(rolecode.charAt(2));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", rolecode+"%");
				}
				if(roleSubAccess.equals("Division")) {
					rolecode = String.valueOf(rolecode.charAt(0)) + String.valueOf(rolecode.charAt(1)) + String.valueOf(rolecode.charAt(2)) + String.valueOf(rolecode.charAt(3)) + String.valueOf(rolecode.charAt(4)) + String.valueOf(rolecode.charAt(5));
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", rolecode+"%");
				}
				if(roleSubAccess.equals("Brigade")) {
					q = session.createQuery("select unit_name from Miso_Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo ").setMaxResults(10);
					q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
					q.setParameter("roleFormationNo", rolecode+"%");
				}
			}
			else if(roleAccess.equals("Line_dte")) {
				rolecode =  session1.getAttribute("roleArmCode").toString();
				 q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where  status_sus_no='Active' and arm_code=:rolecode and upper(unit_name) like:unit_name  order by unit_name").setMaxResults(10);
				 q.setParameter("rolecode", rolecode);
				 q.setParameter("unit_name", unit_name.toUpperCase()+"%");
				
			}
			else {
				q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where status_sus_no='Active' and upper(unit_name) like:unit_name  order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase()+"%");
			}
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session.close();
			return M.getAutoCommonList(sessionUserId,list1);
		}
		
		@RequestMapping(value = "/getSusNoDTLList",method = RequestMethod.POST)
		public @ResponseBody List<String> getSusNoDTLList(String sus_no,HttpSession sessionUserId) {
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no ='Active' order by unit_name");
			q.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	
		@RequestMapping(value = "/getSusNoListActive",method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> getSusNoListActive(HttpSession session,HttpSession sessionUserId,String sus_no) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
			String  roleAccess = "";
			String roleSusNo ="";
			if(session.getAttribute("roleAccess").toString() != "" && session.getAttribute("roleAccess").toString() != null){
				  roleAccess =  session.getAttribute("roleAccess").toString();
			}
			else 
				roleAccess = "";
			
			String roleSubAccess = session.getAttribute("roleSubAccess").toString();
			String  rolecode= ""; 
			if(roleAccess.equals("Formation")){	
				rolecode =  session.getAttribute("roleFormationNo").toString();
				if(roleSubAccess.equals("Command")) {
					rolecode = String.valueOf(rolecode.charAt(0));
				}
				if(roleSubAccess.equals("Corps")) {
					rolecode = String.valueOf(rolecode.charAt(0)) + String.valueOf(rolecode.charAt(1)) + String.valueOf(rolecode.charAt(2));
				}
				if(roleSubAccess.equals("Division")) {
					rolecode = String.valueOf(rolecode.charAt(0)) + String.valueOf(rolecode.charAt(1)) + String.valueOf(rolecode.charAt(2)) + String.valueOf(rolecode.charAt(3)) + String.valueOf(rolecode.charAt(4)) + String.valueOf(rolecode.charAt(5));
				}
			}
			else if(roleAccess.equals("Line_dte")) {	
				 roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			}
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			Query q = null;
			if(roleAccess.equals("Formation")) {
				 q = session1.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where  status_sus_no='Active' and form_code_control like :rolecode and upper(sus_no) like:sus_no order by sus_no").setMaxResults(10);
				 q.setParameter("rolecode", roleSusNo);
				 q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			else if(roleAccess.equals("Line_dte")) {
				 q = session1.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where  status_sus_no='Active' and arm_code in (select arm_code from Tb_Miso_Orbat_Line_Dte where line_dte_sus =:roleSusNo ) and upper(sus_no) like:sus_no order by sus_no").setMaxResults(10);
				 q.setParameter("rolecode", rolecode);
				 q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			else {
				q = session1.createQuery("select distinct sus_no from Miso_Orbat_Unt_Dtl where  status_sus_no='Active' and upper(sus_no) like:sus_no order by sus_no").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
			}
			@SuppressWarnings("unchecked")
			List<String> list1 = (List<String>) q.list();
			tx.commit();
			session1.close();
			return M.getAutoCommonList(sessionUserId,list1);
		}
		


		
		@RequestMapping(value = "/getUnitDetailsListActive", method=RequestMethod.POST)
		public @ResponseBody List<Miso_Orbat_Unt_Dtl> getUnitDetailsListActive(String unitName,HttpSession sessionUserId) {
			
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				unitName = all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionUserId).get(0);
			}
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select sus_no from Miso_Orbat_Unt_Dtl where unit_name=:unitName and status_sus_no='Active'");
			q.setParameter("unitName", unitName);
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			return list;
		}

		@RequestMapping(value = "/getViewUnitDtlTransReport", method=RequestMethod.POST)
		public @ResponseBody List<Miso_Orbat_Unt_Dtl> getViewUnitDtlTransReport(String sus_no,HttpSession sessionUserId) {
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
			if(roleAccess.equals("Unit")){
				sus_no = roleSusNo;
			}
			
			Session session= HibernateUtilNA.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = null;
			if(sus_no != "")
			{				
				q = session.createQuery("select distinct a.ct_part_i_ii,c.code_value as code from Miso_Orbat_Unt_Dtl a, Tbl_CodesForm b,Tb_Miso_Orbat_Code c where a.code = c.code and c.code_type = 'Location' and a.sus_no=b.sus_no and a.status_sus_no = 'Active' and  a.sus_no =:sus_no");
				q.setParameter("sus_no", sus_no);
			}
			else {
				q = session.createQuery("select distinct a.ct_part_i_ii,c.code_value as code from Miso_Orbat_Unt_Dtl a, Tbl_CodesForm b,Tb_Miso_Orbat_Code c where a.code = c.code and c.code_type = 'Location' and a.sus_no=b.sus_no and a.status_sus_no = 'Active'");
			}
			@SuppressWarnings("unchecked")
			List<Miso_Orbat_Unt_Dtl> list = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/getViewTransEntitlementDtl1", method = RequestMethod.POST)
		public ModelAndView getViewTransEntitlementDtl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "sus_no01", required = false) String sus_no,
				@RequestParam(value = "type_of_cue01", required = false) String type_of_cue,
				@RequestParam(value = "we_pe_no01", required = false) String we_pe_no){
				String roleType = session.getAttribute("roleType").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				
				
				Mmap.put("type_of_cue01", type_of_cue);
				Mmap.put("we_pe_no01", we_pe_no);
				Mmap.put("roleAccess", roleAccess);
				if(roleAccess.equals("Unit")) {
					Mmap.put("sus_no01", roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					List<Map<String, Object>> list = cueTransport.getViewUnitDtlTransReport(roleSusNo,roleAccess);
					if(!list.isEmpty()) {
						Mmap.put("ct_part_i_ii",list.get(0).get("ct_part_i_ii"));
						Mmap.put("code_value",list.get(0).get("code"));
					}
					sus_no = roleSusNo;
				}else {
					Mmap.put("sus_no01", sus_no);
					
					String jsCSS = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n" + 
							"<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script> \r\n" + 
							"<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n" + 
							"<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n" + 
							"<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>";
					Mmap.put("jsCSS", jsCSS);
				}
				
				
				if(type_of_cue.equals("1")) {
					List<Map<String, Object>> list1=null;
					List<Map<String, Object>> list2=null;
					List<Map<String, Object>> list3=null;
					List<Map<String, Object>> list13=null;
					List<Map<String, Object>> list=null;
					
					list1= cueTransport.getViewStdlnkTransEntitlementDtl(sus_no);
					list2= cueTransport.getViewStdlnkTransModEntitlementDtl(sus_no);
					list3= cueTransport.getViewStdlnkTransfotnoteEntitlementDtl(sus_no);
					list13= cueTransport.getViewStdlnkTransInlieuEntitlementDtl(sus_no);
					list= cueTransport.getViewTransEntitlementDtl(sus_no);
					
					int base =0;
					int base1 = 0;
					int modification = 0 ;
					int gennotes = 0;
					int inlieu = 0;
					int reducedueinlieu = 0;
					int total = 0;
					int modification1 = 0 ;
					int gennotes1 = 0;
					int inlieu1 = 0;
					int reducedueinlieu1 = 0;
					int total1 = 0;
						
					for(int i=0;i<list.size();i++) {
						if(list.get(i).get("base") == null) {
							base1 = 0;
						}
						else {
							base1 = (Integer) list.get(i).get("base");
						}
						if(list.get(i).get("modification") == null) {
							modification1 =0;
						}
						else {
							modification1 = (Integer) list.get(i).get("modification");
						}
						if(list.get(i).get("gennotes") == null) {
							gennotes1 = 0;
						}
						else {
							gennotes1 = (Integer) list.get(i).get("gennotes");
						}
						if(list.get(i).get("inlieu") == null) {
							inlieu1 = 0;
						}
						else {
							inlieu1 = (Integer) list.get(i).get("inlieu");
						}
						if(list.get(i).get("reducedueinlieu") == null) {
							reducedueinlieu1 = 0;
						}
						else {
							reducedueinlieu1 = (Integer) list.get(i).get("reducedueinlieu");
						}
							
						if(list.get(i).get("total") == null) {
							total1 = 0;
						}
						else {
							total1 = (Integer) list.get(i).get("total");
						}
						base += base1;
						modification+=modification1;
						gennotes += gennotes1;
						inlieu += inlieu1;
						reducedueinlieu += reducedueinlieu1;
						total += total1;
					}
					
					Mmap.put("base",base);
					Mmap.put("modification",modification);
					Mmap.put("gennotes",gennotes);
					Mmap.put("inlieu",inlieu);
					Mmap.put("reducedueinlieu",reducedueinlieu);
					Mmap.put("total",total);
					
					Mmap.put("list", list);
					Mmap.put("list1", list1);
					Mmap.put("list2", list2);
					Mmap.put("list3", list3);
					Mmap.put("list13", list13);
				}
				
				if(type_of_cue.equals("2")) {
					List<Map<String, Object>> list4= cueTransport.getViewWeaponEntitlementDtl(sus_no);
					
					List<Map<String, Object>>list_ces= cueTransport.getViewWeaponCESEntitlementDtl(list4);
					List<Map<String, Object>> list5= cueTransport.getViewStdlnkWeaponEntitlementDtl(sus_no);
					List<Map<String, Object>> list6= cueTransport.getViewStdlnkWeaponModEntitlementDtl(sus_no);
					List<Map<String, Object>> list7= cueTransport.getViewStdlnkWeaponfotnoteEntitlementDtl(sus_no);
					 
					double base_authW =0;
					double mod_authW = 0;
					double foot_authW = 0 ;
					double totalW =0;
					double base_authW1 =0;
					double mod_authW1 = 0;
					double foot_authW1 = 0 ;
					double totalW1 =0;
					
					for(int i=0;i<list4.size();i++) {
						
						if(list4.get(i).get("base_auth") == null) {
							base_authW1 = 0;
						}
						else {
							base_authW1 = (Double) list4.get(i).get("base_auth");
						}
						if(list4.get(i).get("mod_auth") == null) {
							mod_authW1 = 0;
						}
						else {
							mod_authW1 = (Double) list4.get(i).get("mod_auth");
						}
						if(list4.get(i).get("foot_auth") == null) {
							foot_authW1 = 0;
						}
						else {
							foot_authW1 = (Double) list4.get(i).get("foot_auth");
						}
						if(list4.get(i).get("total") == null) {
							totalW1 = 0;
						}
						else {
							totalW1 = (Double) list4.get(i).get("total");
						}
						
						base_authW += base_authW1;
						mod_authW+=mod_authW1;
						foot_authW += foot_authW1;
						totalW += totalW1;
					}
						
					Mmap.put("base_authW",base_authW);
					Mmap.put("mod_authW",mod_authW);
					Mmap.put("foot_authW",foot_authW);
					Mmap.put("totalW",totalW);
					
					Mmap.put("list4", list4);
					Mmap.put("list5", list5);
					Mmap.put("list6", list6);
					Mmap.put("list7", list7);
					Mmap.put("list_ces", list_ces);
					
				}
				
				
				if(type_of_cue.equals("3")) {
					List<Map<String, Object>> list8= cueTransport.getViewUnitDtlPersReport(sus_no, we_pe_no);
					List<Map<String, Object>> list9= cueTransport.getViewPersonEntitlementDtl(sus_no, we_pe_no);
					List<Map<String, Object>> list10= cueTransport.getViewStdlnkPersonEntitlementDtl(sus_no);
					List<Map<String, Object>> list11= cueTransport.getViewStdlnkPersonModEntitlementDtl(sus_no);
					List<Map<String, Object>> list12= cueTransport.getViewStdlnkPersonfotnoteEntitlementDtl(sus_no);
					
					int ere =0;
					int regimental = 0;
					int totalP = 0 ;
					int ere1 =0;
					int regimental1 = 0;
					int totalP1 = 0 ;
					for(int i=0;i<list8.size();i++) {
						
						if(list8.get(i).get("ere") == null) {
							ere1 = 0;
						}
						else {
							ere1 = (Integer) list8.get(i).get("ere");
						}
						
						if(list8.get(i).get("regimental") == null) {
							regimental1 =0;
						}
						else {
							regimental1 =(Integer) list8.get(i).get("regimental");
						}
						
						if(list8.get(i).get("total") == null) {
							totalP1 = 0;
						}
						else {
							totalP1 = (Integer) list8.get(i).get("total");
						}
						
						ere += ere1;
						regimental += regimental1;
						totalP += totalP1;
					}
					
					int base_auth =0;
					int mod_auth = 0;
					int foot_auth = 0 ;
					int totalSP = 0;
					int base_auth1 =0;
					int mod_auth1 = 0;
					int foot_auth1 = 0 ;
					int totalSP1 = 0 ;
						
					for(int i=0;i<list9.size();i++) {
						
						if(list9.get(i).get("base_auth") == null) {
							base_auth1 = 0;
						}
						else {
							base_auth1 = (Integer) list9.get(i).get("base_auth");
						}
						if(list9.get(i).get("mod_auth") == null) {
							mod_auth1 = 0;
						}
						else {
							mod_auth1 = (Integer) list9.get(i).get("mod_auth");
						}
						if(list9.get(i).get("foot_auth") == null) {
							foot_auth1 = 0;
						}
						else {
							foot_auth1 = (Integer) list9.get(i).get("foot_auth");
						}
						if(list9.get(i).get("total") == null) {
							totalSP1 = 0;
						}
						else {
							totalSP1 = (Integer) list9.get(i).get("total");
						}
						
						base_auth += base_auth1;
						mod_auth += mod_auth1;
						foot_auth += foot_auth1;
						totalSP +=totalSP1;
					}
					Mmap.put("base_auth",base_auth);
					Mmap.put("mod_auth",mod_auth);
					Mmap.put("foot_auth",foot_auth);
					Mmap.put("totalSP",totalSP);
					
					Mmap.put("ere",ere);
					Mmap.put("regimental",regimental);
					Mmap.put("totalP",totalP);
					
					Mmap.put("list8", list8);
					Mmap.put("list9", list9);
					Mmap.put("list10", list10);
					Mmap.put("list11", list11);
					Mmap.put("list12", list12);
				}
				
				Mmap.put("roleType", roleType);
			return new ModelAndView("viewstandardEntitlementForTransportTiles");
		}

				
		@RequestMapping(value = "/deleteAlreadyExistInFootModCond", method = RequestMethod.POST)
		public @ResponseBody List<Object> deleteAlreadyExistInFootModCond(int id,String we_pe_no) {
			int countf =	cueTransport.deleteAlreadyExistInFootModImp(id, we_pe_no);
			int countm =	cueTransport.deleteAlreadyExistInModImp(id, we_pe_no);
			List<Object> lstObj = new ArrayList<Object>();
			if(countm == 0 && countf == 0)
			{
				lstObj.add(0);
			}
			else
			{
				lstObj.add(1);
			}					
				return lstObj;	
		}	

		@RequestMapping(value = "/updaterejectTransStndrdaction", method = RequestMethod.POST)	 
		public @ResponseBody List<String> updaterejectTransStndrdaction(String remarks,String letter_no,int id) {
			List<String> list2  =wepepersmdfs.updaterejectactionqrywepers("CUE_TB_MISO_WEPE_TRANSPORT_DET",remarks,id,letter_no);
			return list2;
		}
				
				
		 @RequestMapping(value = "/admin/getDownloadImageTrans_old", method = RequestMethod.POST)
		    public ModelAndView getDownloadImageTrans_old(@ModelAttribute("we_pe_no1") String we_pe_no,@ModelAttribute("pageUrl") String pageUrl
		    		,@ModelAttribute("contraint") String contraint
		    		,@ModelAttribute("sus_no2") String sus_no
		    		,@ModelAttribute("type_of_cue1") String type_of_cue
		    		,ModelMap model ,HttpServletRequest request,HttpSession session
		    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException{
		    	String url=pageUrl;    	    	
		    	String EXTERNAL_FILE_PATH = "";	     
		    	
		    	List<String> a = new ArrayList<String>();
	    		 a = cueTransport.getcue_wepeByid_trasnsent(we_pe_no);
	    		if(a.size()!=0  )
	    		{
		    		//EXTERNAL_FILE_PATH = cueTransport.getcue_wepeByid_trasnsent(we_pe_no).get(0).toString();
	    			EXTERNAL_FILE_PATH = cueTransport.getcue_wepeByid_trasnsent(we_pe_no).get(0).toString();
	    			
		    	File file = null;
		        file = new File(EXTERNAL_FILE_PATH);    
		        try{
		            if(!file.exists()){ 
			           	model.put("msg", "Sorry.The file you are looking for does not exist!");	
			           	getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
			            return new ModelAndView(url);
		            }
		       }
		       catch(Exception exception){
		       }
		       String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		       if(mimeType==null){
		    	   mimeType = "application/octet-stream";
		       }
		       response.setContentType(mimeType);
		       response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
		       response.setContentLength((int)file.length());
		       InputStream inputStream = null;
		       try {
		                inputStream = new BufferedInputStream(new FileInputStream(file));
		                FileCopyUtils.copy(inputStream, response.getOutputStream());
		                model.put("msg", "Download Successfully");	
		            return new ModelAndView(url); 
		        } catch (FileNotFoundException e) {
		          
		        }
	    	}
	    	else {
	    		model.put("msg", "Sorry.The file you are looking for does not exist!");
	    	}
	    		 getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
			return new ModelAndView(url);    
	    }
				 
	/* @RequestMapping(value = "/admin/getDownloadImageTransAmdt", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageTransAmdt(@ModelAttribute("we_pe_no2") String we_pe_no
	    		,@ModelAttribute("pageUrl2") String pageUrl,@ModelAttribute("contraint2") String contraint
	    		,@ModelAttribute("sus_no3") String sus_no
	    		,@ModelAttribute("type_of_cue3") String type_of_cue
	    		,ModelMap model ,HttpServletRequest request,HttpSession session
	    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException{
	    	String url=pageUrl;    	    	
	    	String EXTERNAL_FILE_PATH = "";	     
	    	List<String> a = new ArrayList<String>();
	    		 a = cueTransport.get_amdt_to_doc(we_pe_no);
	    		if(a.size()!=0  )
	    		{
	    			EXTERNAL_FILE_PATH = cueTransport.get_amdt_to_doc(we_pe_no).get(0).toString();
	    		
	    	
	    	File file = null;
	        file = new File(EXTERNAL_FILE_PATH);    
	        try{
	            if(!file.exists()){
		           	model.put("msg", "Sorry.The file you are looking for does not exist!");	
		            getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
	            	return new ModelAndView(url);
	            }
	       }
	        catch(Exception exception){
	                
	        }
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	                mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	        response.setContentLength((int)file.length());
	        InputStream inputStream = null;
	        try {
	                inputStream = new BufferedInputStream(new FileInputStream(file));
	                FileCopyUtils.copy(inputStream, response.getOutputStream());
	                model.put("msg", "Download Successfully");	
	            return new ModelAndView(url);
	        } catch (FileNotFoundException e) {
	                    
	        	
	        }
	    		}
	    		
	        else {
    			model.put("msg", "Sorry.The file you are looking for does not exist!");
    		}
	    	getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
	        return new ModelAndView(url);
	    }*/
		 
		 @RequestMapping(value = "/admin/getDownloadImageTransAmdt", method = RequestMethod.POST)
		    public ModelAndView getDownloadImageTransAmdt(@ModelAttribute("we_pe_no2") String we_pe_no
		    		,@ModelAttribute("pageUrl2") String pageUrl,@ModelAttribute("contraint2") String contraint
		    		,@ModelAttribute("sus_no3") String sus_no
		    		,@ModelAttribute("type_of_cue3") String type_of_cue
		    		,ModelMap model ,HttpServletRequest request,HttpSession session
		    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException, DocumentException{
			 String username = session.getAttribute("username").toString();
				String ip = session.getAttribute("ip").toString();	
			 
			 String url=pageUrl;    	    	
		    	String EXTERNAL_FILE_PATH = "";	     
		    	List<String> a = new ArrayList<String>();
		    		 a = cueTransport.get_amdt_to_doc(we_pe_no);
		    		if(a.size()!=0  )
		    		{
		    			EXTERNAL_FILE_PATH = cueTransport.get_amdt_to_doc(we_pe_no).get(0).toString();
		    		
		    	
		    	File file = null;
		        file = new File(EXTERNAL_FILE_PATH);    
		        try{
		            if(!file.exists()){
			           	model.put("msg", "Sorry.The file you are looking for does not exist!");	
			            getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
		            	return new ModelAndView(url);
		            }
		       }
		        catch(Exception exception){
		                
		        }
		        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	            if (mimeType != null && mimeType.equals("application/pdf")) {
	                //Process for PDF
	                addWatermarkToPdf(file, response, model,request,username,ip);
	                model.put("msg", "Download Successfully");
	            } else {
	                //Process other file types
	                downloadOtherFiles(file,response,model,url);
	                model.put("msg", "Download Successfully");

	            }
		    		}
		    		
		        else {
	    			model.put("msg", "Sorry.The file you are looking for does not exist!");
	    		}
		    	getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
		        return new ModelAndView(url);
		 }
	 
	/* @RequestMapping(value = "/admin/getDownloadImageWetPetDoc", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageWetPetDoc(@ModelAttribute("wet_pet_no1") String wet_pet_no
	    		,@ModelAttribute("pageUrl3") String pageUrl,@ModelAttribute("contraint3") String contraint,ModelMap model 
	    		,@ModelAttribute("sus_no4") String sus_no
	    		,@ModelAttribute("type_of_cue4") String type_of_cue
	    		,HttpServletRequest request,HttpSession session
	    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException{
	    	String url=pageUrl;    	    	
	    	String EXTERNAL_FILE_PATH = "";	     
	    	
	    	List<String> a = new ArrayList<String>();
    		 a = cueTransport.get_wet_pet_doc(wet_pet_no);
    		if(a.size()!=0  )
    		{
	    		EXTERNAL_FILE_PATH = cueTransport.get_wet_pet_doc(wet_pet_no).get(0).toString();
		   
	    	
	    	File file = null;
	        file = new File(EXTERNAL_FILE_PATH);    
	        try{
	            if(!file.exists()){
		           	model.put("msg", "Sorry.The file you are looking for does not exist!");	
		           	model.put("wet_pet_no01", wet_pet_no);
		            getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
	            	return new ModelAndView(url);
	            }
	       }
	        catch(Exception exception){
	                
	        }
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	                mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	        response.setContentLength((int)file.length());
	        InputStream inputStream = null;
	        try {
	                inputStream = new BufferedInputStream(new FileInputStream(file));
	                FileCopyUtils.copy(inputStream, response.getOutputStream());
	                model.put("msg", "Download Successfully");	
	            return new ModelAndView(url);
	        } catch (FileNotFoundException e) {
	                    
	        
	        }
    		}
    		
	        else {
    			model.put("msg", "Sorry.The file you are looking for does not exist!");
    		}
    		
    		getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
    		model.put("wet_pet_no01", wet_pet_no);
	        return new ModelAndView(url);
	    }*/
	 
	 @RequestMapping(value = "/admin/getDownloadImageWetPetDoc", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageWetPetDoc(@ModelAttribute("wet_pet_no1") String wet_pet_no
	    		,@ModelAttribute("pageUrl3") String pageUrl,@ModelAttribute("contraint3") String contraint,ModelMap model 
	    		,@ModelAttribute("sus_no4") String sus_no
	    		,@ModelAttribute("type_of_cue4") String type_of_cue
	    		,HttpServletRequest request,HttpSession session
	    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException, DocumentException{
	    	String url=pageUrl;    	    	
	    	String EXTERNAL_FILE_PATH = "";	     
	    	
	    	String username = session.getAttribute("username").toString();
			String ip = session.getAttribute("ip").toString();
			
	    	List<String> a = new ArrayList<String>();
 		 a = cueTransport.get_wet_pet_doc(wet_pet_no);
 		if(a.size()!=0  )
 		{
	    		EXTERNAL_FILE_PATH = cueTransport.get_wet_pet_doc(wet_pet_no).get(0).toString();
		   
	    	
	    	File file = null;
	        file = new File(EXTERNAL_FILE_PATH);    
	        try{
	            if(!file.exists()){
		           	model.put("msg", "Sorry.The file you are looking for does not exist!");	
		           	model.put("wet_pet_no01", wet_pet_no);
		            getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
	            	return new ModelAndView(url);
	            }
	       }
	        catch(Exception exception){
	                
	        }
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
         if (mimeType != null && mimeType.equals("application/pdf")) {
             //Process for PDF
             addWatermarkToPdf(file, response, model,request,username,ip);
             model.put("msg", "Download Successfully");
         } else {
             //Process other file types
             downloadOtherFiles(file,response,model,url);
             model.put("msg", "Download Successfully");

         }
 		}
 		
	        else {
 			model.put("msg", "Sorry.The file you are looking for does not exist!");
 		}
 		
 		getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
 		model.put("wet_pet_no01", wet_pet_no);
	        return new ModelAndView(url);
	    }
	 
	 @RequestMapping(value = "/admin/getDownloadImageAmdtWetPetDoc", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageAmdtWetPetDoc(@ModelAttribute("wet_pet_no2") String wet_pet_no
	    		,@ModelAttribute("pageUrl4") String pageUrl,@ModelAttribute("contraint4") String contraint,ModelMap model 
	    		,@ModelAttribute("sus_no5") String sus_no
	    		,@ModelAttribute("type_of_cue5") String type_of_cue
	    		,HttpServletRequest request,HttpSession session
	    		,HttpServletResponse response,@RequestParam(value = "msg", required = false) String msg) throws IOException, DocumentException{
	    	String url=pageUrl;    	    	
	    	String EXTERNAL_FILE_PATH = "";	     
	    	String username = session.getAttribute("username").toString();
			String ip = session.getAttribute("ip").toString();
			
	    	List<String> a = new ArrayList<String>();
 		 a = cueTransport.get_wet_pet_amd_doc(wet_pet_no);
 		if(a.size()!=0  )
 		{
	    		EXTERNAL_FILE_PATH = cueTransport.get_wet_pet_amd_doc(wet_pet_no).get(0).toString();
		   
	    	
	    	File file = null;
	        file = new File(EXTERNAL_FILE_PATH);    
	        try{
	            if(!file.exists()){
	           	 model.put("msg", "Sorry.The file you are looking for does not exist!");
	           	model.put("wet_pet_no01", wet_pet_no);
	           	getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
	            		return new ModelAndView(url);
	               }
	       }
	        catch(Exception exception){
	                
	        }
	        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
         if (mimeType != null && mimeType.equals("application/pdf")) {
             //Process for PDF
             addWatermarkToPdf(file, response, model,request,username,ip);
             model.put("msg", "Download Successfully");
         } else {
             //Process other file types
             downloadOtherFiles(file,response,model,url);
             model.put("msg", "Download Successfully");

         }
 		}
 		
	        else {
 			model.put("msg", "Sorry.The file you are looking for does not exist!");
 		}
 		getViewTransEntitlementDtl1(model,session,msg,sus_no,type_of_cue,"");
 		model.put("wet_pet_no01", wet_pet_no);
	        return new ModelAndView(url);    
	       // return new ModelAndView("redirect:update_WE_PE_Url?msg=Download Successfully");
	    }
	 
	
	 @RequestMapping(value = "/ces_cess_popup", method=RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> ces_cess_popup(int id) {
		 if(id != 0)
		 {
			return cueTransport.ces_cess_popup(id);
		 }
		 else {
			 return null ;
		 }
		}
	 
	 @RequestMapping(value = "/getTableTitlewepe", method=RequestMethod.POST)
		public @ResponseBody List<String> getTableTitlewepe(String we_pe_no) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct a.table_title from cue_wepe a, CUE_TB_MISO_WEPECONDITIONS b  where b.we_pe_no=:we_pe_no and  " + 
					" a.table_title in (select distinct d.table_title from cue_wepe d,CUE_TB_MISO_WEPECONDITIONS c where "  + 
					"  c.uploaded_wepe=d.we_pe_no and c.we_pe_no=:we_pe_no ) ");
			q.setParameter("we_pe_no", we_pe_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
		
			tx.commit();
			session.close();
			return list;
		}
	 
	 @RequestMapping(value = "/getTableTitlewepeamdt", method=RequestMethod.POST)
		public @ResponseBody List<String> getTableTitlewepeamdt(String we_pe_no) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct a.amdt_title_we_pe from UploadAamendmentToDocument a, CUE_TB_MISO_WEPECONDITIONS b  where b.we_pe_no=:we_pe_no and  " + 
					" a.amdt_title_we_pe in (select distinct d.amdt_title_we_pe from UploadAamendmentToDocument d,CUE_TB_MISO_WEPECONDITIONS c where "  + 
					"  c.uploaded_wepe=d.we_pe_no and c.we_pe_no=:we_pe_no ) ");
			q.setParameter("we_pe_no", we_pe_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	 
	 @RequestMapping(value = "/getTableTitlewtpet", method=RequestMethod.POST)
		public @ResponseBody List<String> getTableTitlewtpet(String wet_pet_no,HttpSession sessionUserId) {
		 int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());	
		 Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct a.amdt_title_wet_pet from UploadWetPetAamendmentToDocument a, CUE_TB_MISO_MMS_WET_PET_MAST_DET b  where b.wet_pet_no=:wet_pet_no and  " + 
					"  a.amdt_title_wet_pet in (select distinct d.amdt_title_wet_pet from UploadWetPetAamendmentToDocument d,CUE_TB_MISO_MMS_WET_PET_MAST_DET c where  " + 
					" c.uploaded_wetpet=d.wet_pet_no and c.wet_pet_no=:wet_pet_no )   ");
			
			q.setParameter("wet_pet_no", wet_pet_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	 
	 @RequestMapping(value = "/getTableTitlewtpetamdt", method=RequestMethod.POST)
		public @ResponseBody List<String> getTableTitlewtpetamdt(String wet_pet_no,HttpSession sessionUserId) {
		 int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());		
		 Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select distinct a.unit_type from cue_wet_pet a, CUE_TB_MISO_MMS_WET_PET_MAST_DET b  where b.wet_pet_no=:wet_pet_no  and " + 
					" a.unit_type in (select distinct d.unit_type from cue_wet_pet d,CUE_TB_MISO_MMS_WET_PET_MAST_DET c where " + 
					" c.uploaded_wetpet=d.wet_pet_no and c.wet_pet_no=:wet_pet_no  )    ");
			
			q.setParameter("wet_pet_no", wet_pet_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
		
			tx.commit();
			session.close();
			return list;
		}
	 
	 @RequestMapping(value = "/admin/getDownloadImageTrans", method = RequestMethod.POST)
	    public ModelAndView getDownloadImageTrans(
	            @ModelAttribute("we_pe_no1") String we_pe_no,
	            @ModelAttribute("pageUrl") String pageUrl,
	            @ModelAttribute("contraint") String contraint,
	            @ModelAttribute("sus_no2") String sus_no,
	            @ModelAttribute("type_of_cue1") String type_of_cue,
	            ModelMap model,
	            HttpServletRequest request,
	            HttpSession session,
	            HttpServletResponse response,
	            @RequestParam(value = "msg", required = false) String msg) throws IOException, DocumentException {
			String username = session.getAttribute("username").toString();
			String ip = session.getAttribute("ip").toString();
	        String url = pageUrl;
	        String EXTERNAL_FILE_PATH = "";

	        List<String> filePaths = new ArrayList<String>();
	        filePaths = cueTransport.getcue_wepeByid_trasnsent(we_pe_no);

	        if (filePaths.size() != 0) {
	            EXTERNAL_FILE_PATH = filePaths.get(0);
	            File file = new File(EXTERNAL_FILE_PATH);

	            if (!file.exists()) {
	                model.put("msg", "Sorry.The file you are looking for does not exist!");
	                //getViewTransEntitlementDtl1(model, session, msg, sus_no, type_of_cue, "");  // Removed method call
	                return new ModelAndView(url, model);
	            }
	             // Check if it's a PDF file
	            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	            if (mimeType != null && mimeType.equals("application/pdf")) {
	                //Process for PDF
	                addWatermarkToPdf(file, response, model,request,username,ip);
	                model.put("msg", "Download Successfully");
	            } else {
	                //Process other file types
	                downloadOtherFiles(file,response,model,url);
	                model.put("msg", "Download Successfully");

	            }
	         }
	        else {
	            model.put("msg", "Sorry.The file you are looking for does not exist!");
	        }
	          //getViewTransEntitlementDtl1(model, session, msg, sus_no, type_of_cue, "");
	        return new ModelAndView(url, model);
	    }
	 
	 private void addWatermarkToPdf(File file, HttpServletResponse response, ModelMap model, HttpServletRequest request,String username, String ip) throws IOException, DocumentException {
		    PdfReader reader = null;
		    PdfStamper stamper = null;
		    try {
		        reader = new PdfReader(new FileInputStream(file));
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		        stamper = new PdfStamper(reader, response.getOutputStream()); // Moved stamper initialization here
		        int numberOfPages = reader.getNumberOfPages();
		        // Changed font here by using FontFactory to retrieve the font
		        Font FONT = FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD, BaseColor.GRAY);
		        PdfGState gs1 = new PdfGState();
		        gs1.setFillOpacity(0.4f);
		        
		          String watermarkText = username+" " + ip;

		        for (int i = 1; i <= numberOfPages; i++) {
		            PdfContentByte content = stamper.getOverContent(i);
		            content.setGState(gs1);
		            content.beginText();
		            content.setColorFill(BaseColor.GRAY);
		            content.setFontAndSize(FONT.getBaseFont(), FONT.getSize());
		            float x = reader.getPageSize(i).getWidth() / 2;
		            float y = reader.getPageSize(i).getHeight() / 2;
		            content.showTextAligned(Element.ALIGN_CENTER, watermarkText, x, y, 45);
		            content.endText();
		        }
		    } catch (Exception e) {
		        model.put("msg", "Error adding watermark: " + e.getMessage());
		         // Add log statement so you can see the exception if it's happening.
		         e.printStackTrace();
		    }finally {
		        if(stamper!=null){
		          stamper.close();
		        }
		        if(reader!=null){
		            reader.close();
		        }
		     }
		}
	 
	    private void downloadOtherFiles(File file, HttpServletResponse response,ModelMap model,String url) throws IOException {
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	            mimeType = "application/octet-stream";
	        }
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
	        response.setContentLength((int)file.length());
	        InputStream inputStream = null;
	        try {
	            inputStream = new BufferedInputStream(new FileInputStream(file));
	            FileCopyUtils.copy(inputStream, response.getOutputStream());
	            model.put("msg", "Download Successfully");
	        } catch (FileNotFoundException e) {
	            model.put("msg", "Error:File not found"+ e.getMessage());
	        }finally {
	            if (inputStream!=null){
	                inputStream.close();
	            }
	        }
	    }
				
				
}
