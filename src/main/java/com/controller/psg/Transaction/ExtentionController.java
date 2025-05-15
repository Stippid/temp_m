package com.controller.psg.Transaction;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.HelpDeskController.helpController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.ExtentionDAO;
import com.dao.psg.update_census_data.Re_EmploymentDAO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.update_census_data.TB_REEMPLOYMENT;
import com.models.psg.update_census_data.TB_REEMP_EXTENTION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class ExtentionController {

	@Autowired
	private ExtentionDAO ex;
	helpController hp = new helpController();
	Psg_CommonController pcommon = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	PsgDashboardController das = new PsgDashboardController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/extensionurl", method = RequestMethod.GET)
	public ModelAndView extensionurl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
	
		String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("extensionurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		 String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		 Mmap.put("msg", msg);
		
			Mmap.put("roleSusNo", roleSusNo);
		
		
	Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		  return new ModelAndView("ExtentionTiles");
	 }
	
	/*--------------------- Save Extention----------------------------------*/
	@RequestMapping(value = "/admin/extention_Action", method = RequestMethod.POST)
	public @ResponseBody String extention_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		DateFormat format = new SimpleDateFormat("dd-MM-yyy", Locale.ENGLISH);	
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		int census_id = 0;
		BigInteger comm_id = BigInteger.ZERO;
		int r_id = 0;
		
		String extension_fr_dt = request.getParameter("from_dt");
		String extension_to_dt = request.getParameter("to_dt");
		String extension_auth_dt = request.getParameter("auth_dt");
		String ex_id = request.getParameter("ex_id");
		String auth_no= request.getParameter("auth_no");
		String personnel_no = request.getParameter("personnel_no");

		Date date = new Date();
		Date E_fr_dt = null;
		Date E_t_dt = null;
		Date E_auth_dt = null;
		Date release_dt = null;		
		String username = session.getAttribute("username").toString();
	
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if (personnel_no == null || personnel_no.equals("") || personnel_no.equals("null")) {
			return "Please Enter Personal No";
		}
		
		if (personnel_no != "") {
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
			}

			if (personnel_no.length() < 7 || personnel_no.length() > 9) {
				return "Personal No Should Contain Maximum 9 Character";
			}
		}
		
		 
		/*if (auth_no== null || request.getParameter("auth_no").trim().equals("")) {
			return "Please Enter Authority No";			
		}*/
		
		if(auth_no!="") {
			if (!valid.isValidAuth(auth_no)) {
		 		return valid.isValidAuthMSG + " Authority No";	
			}
	 		if (!valid.isvalidLength(auth_no,valid.nameMax,valid.nameMin)) {
	 			return "Authority No " + valid.isValidLengthMSG;	
			}	
		}
			
 		/*if (extension_auth_dt == null || extension_auth_dt.equals("null") || extension_auth_dt.equals("DD/MM/YYYY") || extension_auth_dt.equals("")) {
			return "Please Select Date of Authority";
		}*/
		/*if(E_auth_dt == null || E_auth_dt.equals("null")){ 
			return "Please Select  Date of Authority .";
		}*/
		
		if(extension_auth_dt!="") {
			if (!valid.isValidDate(extension_auth_dt)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			if (extension_auth_dt != "") {
				E_auth_dt = format1.parse(extension_auth_dt);
			}
		}
				
		if (extension_fr_dt == null || extension_fr_dt.equals("null") || extension_fr_dt.equals("DD/MM/YYYY") || extension_fr_dt.equals("")) {
			return "Please Select Date of Authority";
		}
		
		if(extension_fr_dt!="") {
			if (!valid.isValidDate(extension_fr_dt)) {
	 			return valid.isValidDateMSG + " of From";	
			}
			if (extension_fr_dt != "") {
				E_fr_dt = format1.parse(extension_fr_dt);
			}
		}
		if(extension_to_dt!="") {
		/*if (extension_to_dt == null || extension_to_dt.equals("null") || extension_to_dt.equals("DD/MM/YYYY") || extension_to_dt.equals("")) {
			return "Please Select Date of Authority";
		}*/
		if (!valid.isValidDate(extension_to_dt)) {
 			return valid.isValidDateMSG + " of To";	
		}
		if (extension_to_dt != "") {
			E_t_dt = format1.parse(extension_to_dt);
		}
		}
		if (pcommon.CompareDate(E_t_dt, E_fr_dt) == 0) {
			return "To Date should be Greater than From Date";
		}
		
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
	/*	if (Integer.parseInt(request.getParameter("comm_id")) != 0) {*/
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		
		if (Integer.parseInt(request.getParameter("r_id")) != 0) {
			r_id = Integer.parseInt(request.getParameter("r_id"));
		}
		
		String msg = "";
		 try { 
				if (Integer.parseInt(ex_id)== 0) {
					
					TB_REEMP_EXTENTION ext = new TB_REEMP_EXTENTION();
						 
						ext.setFrom_dt(E_fr_dt);
						ext.setTo_dt(E_t_dt);
						ext.setAuth_dt(E_auth_dt);
						ext.setStatus(0);
						ext.setAuth_no(auth_no);
						ext.setRe_emp_id(r_id);
						ext.setCreated_date(date);
						ext.setCreated_by(username);
						ext.setComm_id(comm_id);
						ext.setCensus_id(census_id);
						//ext.setRe_emp_select(3);
						int id = (int) sessionHQL.save(ext);
						msg = Integer.toString(id);
				
				}else {
					String hql = "update TB_REEMP_EXTENTION set "
							+ "from_dt=:E_fr_dt,to_dt=:E_t_dt,auth_dt=:E_auth_dt,auth_no=:auth_no,"
							+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  where id=:id";
		
					Query query = sessionHQL.createQuery(hql)
							 .setDate("E_fr_dt", E_fr_dt)
							 .setDate("E_t_dt", E_t_dt)
							 .setDate("E_auth_dt", E_auth_dt)
							 .setString("auth_no", auth_no)
							 .setString("modified_by", username)
							 .setTimestamp("modified_date", new Date())
							 .setInteger("status", 0) 
							// .setInteger("re_emp_select", 3) 
							 .setInteger("id", Integer.parseInt(ex_id));
			 
			          msg = query.executeUpdate() > 0 ? "update" : "0";
				}
				pcommon.update_miso_role_hdr_status(comm_id,0,username,"re_emp_status");
			tx.commit();
		
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		 
	return msg;
}



 
    
	@RequestMapping(value = "/getExtentionData", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> getExtentionData(int census_id,BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		List<Map<String, Object>> list  = ex.getExtn_data(census_id,comm_id);

			tx.commit();

			return list;


	}
    /*--------------------- Search Extention URL----------------------------------*/
    @RequestMapping(value = "/admin/search_extensionurl", method = RequestMethod.GET)
   	public ModelAndView search_extensionurl(ModelMap Mmap, HttpSession sessionA,
   			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
   			HttpSession sessionUserId) {

    	 String  roleid = sessionUserId.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} 		
   		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
   		String roleType = sessionA.getAttribute("roleType").toString();
   		String roleAccess = sessionA.getAttribute("roleAccess").toString();
  		
   		 if(roleAccess.equals("Unit")){
   				Mmap.put("sus_no",roleSusNo);
   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0)); 				 				
   			}
   		 
   		ArrayList<ArrayList<String>> list = ex.search_extention("","","","0","","",roleSusNo,roleType);
    		Mmap.put("list", list);
    		Mmap.put("roleSusNo", roleSusNo);
    		Mmap.put("msg", msg);
    		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
   		return new ModelAndView("SearchExtentionTiles");
   	}
       /*--------------------- Search Extention  ----------------------------------*/
   	@RequestMapping(value = "/search_extention" , method = RequestMethod.POST)
   	public ModelAndView search_extention(ModelMap Mmap, HttpSession sessionA, HttpServletRequest request,
   			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
   			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
   			@RequestParam(value = "status1", required = false) String status,
   		    @RequestParam(value = "unit_name1", required = false) String unit_name,  		       
   		    @RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
            @RequestParam(value = "cr_by1", required = false) String cr_by,
   			 @RequestParam(value = "cr_date1", required = false) String cr_date) {
   		
   		
   	 String  roleid = sessionUserId.getAttribute("roleid").toString();
	 Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);	
	 	if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		} 	
   		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");
 		
   		
   		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
   		String roleType = sessionA.getAttribute("roleType").toString();
   		String roleAccess = sessionA.getAttribute("roleAccess").toString();
   		
   		 if(roleAccess.equals("Unit")){
   				Mmap.put("sus_no",roleSusNo);
   				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,sessionA).get(0));				
   			}
   		 
   		if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:search_extensionurl");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:search_extensionurl");
				}
	     }
		 
		 if(personnel_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:search_extensionurl");
				}
			  
	    	  
			  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
					return new ModelAndView("redirect:search_extensionurl");
				}
	      }
		 
		 if(unit_name!="") {
			  if (!valid.isOnlyAlphabetNumeric(unit_name)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");
					return new ModelAndView("redirect:search_extensionurl");
				}
	    	  
//	    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//					return new ModelAndView("redirect:search_extensionurl");
//				}
	      }
		 
   		ArrayList<ArrayList<String>> list = ex.search_extention( unit_name,unit_sus_no,personnel_no, status,cr_by,cr_date,roleSusNo,roleType);
	    	Mmap.put("list", list);
	        Mmap.put("unit_name1", unit_name);         
	   		Mmap.put("unit_sus_no1", unit_sus_no);
	   		Mmap.put("personnel_no1", personnel_no);
	   		Mmap.put("status1", status);
    		Mmap.put("roleSusNo", roleSusNo);
    		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
    		Mmap.put("cr_date1", cr_date);
  	        Mmap.put("cr_by1", cr_by);
   		return new ModelAndView("SearchExtentionTiles");

   	}
    
	  /*--------------------- EDIT Extention URL----------------------------------*/
	 @RequestMapping(value = "/edit_extention")
		public ModelAndView edit_extention(@ModelAttribute("updateid") String updateid,
				@ModelAttribute("census_id_e") String census_id_e,
				@ModelAttribute("comm_id_e") String comm_id_e1,
				@ModelAttribute("personnel_no_e") String personnel_no_e,
				@ModelAttribute("personnel_no6") String personnel_no6,
				@ModelAttribute("status6") String status6, 
				@ModelAttribute("unit_sus_no6") String unit_sus_no, 
				@ModelAttribute("unit_name6") String unit_name, 
				ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		 
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				BigInteger comm_id_e = BigInteger.ZERO;
				if(!comm_id_e1.equals('0')) {
					comm_id_e = new BigInteger(comm_id_e1);
				}
				
			model.put("r_id", updateid);
			model.put("census_id",census_id_e);
			model.put("comm_id", comm_id_e);
			model.put("personnel_no", personnel_no_e);
			model.put("personnel_no6", personnel_no6);
			model.put("statusA", status6);
			model.put("unit_sus_no6", unit_sus_no);
			model.put("unit_name6", unit_name);
			return new ModelAndView("ExtentionTiles");
		}
	
	
	 @RequestMapping(value = "/admin/Edit_extention_Action", method = RequestMethod.POST)
		public ModelAndView  Edit_extention_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg1)
				throws ParseException {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg1 = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			} 	
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			int census_id = 0;
			//int comm_id = 0;
			
			String extension_fr_dt = request.getParameter("from_dt");
			String extension_to_dt = request.getParameter("to_dt");
			String r_id = request.getParameter("r_id");
			String personnel_no = request.getParameter("personnel_no");
			//String r_id = "0";
			
			 Date date = new Date();
			 Date E_fr_dt = null;
			 Date E_t_dt = null;
			 
			String username = session.getAttribute("username").toString();
		
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			if (extension_fr_dt != "") {
				E_fr_dt = format1.parse(extension_fr_dt);
			}
			
			if (extension_to_dt != "") {
				E_t_dt = format1.parse(extension_to_dt);
			}
			
			Mmap.put("updateid", r_id);
			
			if (personnel_no == null || personnel_no.equals("") || personnel_no.equals("null")) {
				Mmap.put("msg", "Please Enter Personal No");
				return new ModelAndView("redirect:edit_extention");
			}
			
			if (personnel_no != "") {
				if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:edit_extention");
				}

				if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
					return new ModelAndView("redirect:edit_extention");
				}
			}
			
			 
			/*if (auth_no== null || request.getParameter("auth_no").trim().equals("")) {
				return "Please Enter Authority No";			
			}*/
			
			/*if(auth_no!="") {
				if (!valid.isValidAuth(auth_no)) {
			 		return valid.isValidAuthMSG + " Authority No";	
				}
		 		if (!valid.isvalidLength(auth_no,valid.nameMax,valid.nameMin)) {
		 			return "Authority No " + valid.isValidLengthMSG;	
				}	
			}*/
				
	 		/*if (extension_auth_dt == null || extension_auth_dt.equals("null") || extension_auth_dt.equals("DD/MM/YYYY") || extension_auth_dt.equals("")) {
				return "Please Select Date of Authority";
			}*/
			/*if(E_auth_dt == null || E_auth_dt.equals("null")){ 
				return "Please Select  Date of Authority .";
			}*/
			
			/*if(extension_auth_dt!="") {
				if (!valid.isValidDate(extension_auth_dt)) {
		 			return valid.isValidDateMSG + " of Authority";	
				}
				if (extension_auth_dt != "") {
					E_auth_dt = format1.parse(extension_auth_dt);
				}
			}*/
					
			if (extension_fr_dt == null || extension_fr_dt.equals("null") || extension_fr_dt.equals("DD/MM/YYYY") || extension_fr_dt.equals("")) {
				Mmap.put("msg", "Please Select Date of Authority");
				return new ModelAndView("redirect:edit_extention");
			}
			
			if(extension_fr_dt!="") {
				if (!valid.isValidDate(extension_fr_dt)) {
		 			Mmap.put("msg",valid.isValidDateMSG + " of From");
					return new ModelAndView("redirect:edit_extention");
				}
				if (extension_fr_dt != "") {
					E_fr_dt = format1.parse(extension_fr_dt);
				}
			}
			if(extension_to_dt!="") {
			/*if (extension_to_dt == null || extension_to_dt.equals("null") || extension_to_dt.equals("DD/MM/YYYY") || extension_to_dt.equals("")) {
				return "Please Select Date of Authority";
			}*/
			if (!valid.isValidDate(extension_to_dt)) {
	 			Mmap.put("msg",valid.isValidDateMSG + " of To");
				return new ModelAndView("redirect:edit_extention");
			}
			if (extension_to_dt != "") {
				E_t_dt = format1.parse(extension_to_dt);
			}
			}
			if (pcommon.CompareDate(E_t_dt, E_fr_dt) == 0) {
				Mmap.put("msg","To Date should be Greater than From Date");
				return new ModelAndView("redirect:edit_extention");
			}
			
			if (Integer.parseInt(request.getParameter("census_id")) != 0) {
				census_id = Integer.parseInt(request.getParameter("census_id"));
			}
			BigInteger comm_id = BigInteger.ZERO;
			if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
				//comm_id = 	BigIntege.va r(request.getParameter("comm_id")) ;
				comm_id = new BigInteger(request.getParameter("comm_id"));
			}
			
			
			
			 
			String msg = "";
			 try { 
 						String hql = "update TB_REEMPLOYMENT set "
								+ "from_dt=:extension_fr_dt,to_dt=:extension_to_dt, "
								+ "modified_by=:modified_by,modified_date=:modified_date  where id=:id";
						 Query query = sessionHQL.createQuery(hql)
								.setDate("extension_fr_dt", E_fr_dt)
								.setDate("extension_to_dt", E_t_dt)
								.setString("modified_by", username)
								.setTimestamp("modified_date", new Date())
								.setInteger("id", Integer.parseInt(r_id));
			 	 	
				          msg = query.executeUpdate() > 0 ? "update" : "0";
				          Mmap.put("msg", "Data Updated Successfully");
				          pcommon.update_offr_status(census_id,username);
				
				tx.commit();
			
			} catch (RuntimeException e) {
				try {
					tx.rollback();
					msg = "0";
				} catch (RuntimeException rbe) {
					msg = "0";
				}

			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				
				//sessionHQL.close();
			 
			 return new ModelAndView("redirect:search_extensionurl");
	}

		
		@RequestMapping(value = "/Approve_Extnsn" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Approve_Extnsn(@ModelAttribute("id3") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
			 	@RequestParam(value = "modified_by", required = false) String modified_by,
				@RequestParam(value = "modified_date", required = false) String modified_date,
				@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			 	Authentication authentication) {
			
			
			 String  roleid = sessionA.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				} 	
				
				
			     List<String> liststr = new ArrayList<String>();
			     String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			     String username = sessionA.getAttribute("username").toString();
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				 String hqlUpdate1 = "update TB_REEMP_EXTENTION set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
							.setDate("modified_date", new Date())
							.setInteger("id", id).executeUpdate();
					TB_REEMP_EXTENTION tb_rem = (TB_REEMP_EXTENTION)sessionHQL.get(TB_REEMP_EXTENTION.class, id);   
				pcommon.update_miso_role_hdr_status(tb_rem.getComm_id(),1,username,"re_emp_status");
			 	tx.commit();
				sessionHQL.close();
				if (app > 0) {
					liststr.add("Approved Successfully.");
				} else {
					liststr.add("Approved Not Successfully.");
				}
				model.put("msg",liststr.get(0));
				model.put("personnel_no1", personnel_no);
				model.put("list", ex.search_extention("","","","","0","","",roleSusNo));
				
			return new ModelAndView("redirect:search_extensionurl");
		}
	    
	
		@RequestMapping(value = "/GetCensusDataExtnsn", method = RequestMethod.POST)

		public @ResponseBody List<TB_CENSUS_DETAIL_Parent> GetCensusDataExtnsn(BigInteger comm_id) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

//			try {

			Query q1 = sessionHQL

					.createQuery("FROM TB_CENSUS_DETAIL_Parent c where comm_id = :comm_id and status in (-1,1)");

			q1.setParameter("comm_id", comm_id);

			@SuppressWarnings("unchecked")

			List<TB_CENSUS_DETAIL_Parent> list = (List<TB_CENSUS_DETAIL_Parent>) q1.list();

			

			tx.commit();

			return list;
	 

		}
	
		@RequestMapping(value = "/Reject_Extnsn" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Reject_Extnsn(@ModelAttribute("idr") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,
				Authentication authentication) {
			
			 String  roleid = sessionA.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("search_extensionurl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				} 	
			
			
			     List<String> liststr = new ArrayList<String>();
		
			     String username = sessionA.getAttribute("username").toString();
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				
				String hqlUpdate = "update TB_REEMP_EXTENTION set status=:status,reject_remarks=:reject_remarks where id=:id";
			
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)
						.setString("reject_remarks", reject_remarks)
						.setInteger("id", id).executeUpdate();
				
				TB_REEMP_EXTENTION tb_rem = (TB_REEMP_EXTENTION)sessionHQL.get(TB_REEMP_EXTENTION.class, id);   
				pcommon.update_miso_role_hdr_status(tb_rem.getComm_id(),0,username,"re_emp_status");
				tx.commit();
				sessionHQL.close();
				

				if (app > 0) {
					liststr.add("Rejected Successfully.");
				} else {
					liststr.add("Rejected Not Successfully.");
				}
				model.put("msg",liststr.get(0));

				
			return new ModelAndView("redirect:search_extensionurl");
		}
		
		
		
		 @RequestMapping(value = "/admin/ViewCancelHistoryExtension", method = RequestMethod.POST)
		 public ModelAndView ViewCancelHistoryExtension(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,
				 @RequestParam(value = "can_comm_id", required = false) String comm_id1,
				 @RequestParam(value = "can_pers_no", required = false) String personal_no,
				 @RequestParam(value = "can_status", required = false) String status,
				 @RequestParam(value = "can_remp_id", required = false) String remp_id,
				 HttpServletRequest request) {
			/* String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Bank", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}*/
			 
			 BigInteger comm_id = BigInteger.ZERO;
				if(!comm_id1.equals("")) {
					comm_id = new BigInteger(comm_id1);
				}
				String roleType = session.getAttribute("roleType").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
		
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				 Mmap.put("msg", msg);
				 Mmap.put("comm_id", comm_id);
				 Mmap.put("personal_no", personal_no);
				 Mmap.put("remp_id", remp_id);
				 Mmap.put("status", status);
				 Mmap.put("roleSusNo", roleSusNo);
				 Mmap.put("roleType", roleType);
				 Mmap.put("roleAccess", roleAccess);
				 return new ModelAndView("Extension_History_Tiles");
		 }
		 @RequestMapping(value = "/getExtensionHistoryData" , method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> getExtensionHistoryData(HttpServletRequest request,
					HttpSession sessionA,
					 Authentication authentication) {
			 BigInteger comm_id= new BigInteger(request.getParameter("comm_id"));
			 int status=Integer.parseInt(request.getParameter("status"));
			 int remp_id=Integer.parseInt(request.getParameter("remp_id"));
		
			 return ex.getHisOfExtension(remp_id,comm_id,status,sessionA);
			}
		 @RequestMapping(value = "/admin/CancelHisExtensionData", method = RequestMethod.POST)
		 public @ResponseBody String CancelHisExtensionData(ModelMap Mmap, HttpSession session,
		 		HttpServletRequest request) throws ParseException {
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	String msg = "0";
		 	String username = session.getAttribute("username").toString();	
		 	int id=Integer.parseInt(request.getParameter("id").toString());
		 	int status=Integer.parseInt(request.getParameter("set_status").toString());
		 	try {
		 	String hql_n = "update TB_REEMP_EXTENTION set modified_date=:modified_date ,modified_by=:modified_by , cancel_status=:status "
		 			+ " where  id=:id";
		 	Query query_n = sessionhql.createQuery(hql_n).setInteger("status", status)
		 			.setInteger("id", id).setString("modified_by", username)
		 			.setTimestamp("modified_date", new Date());
		 	msg = query_n.executeUpdate() > 0 ? "1" : "0";
		 	tx.commit();
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 		return "0";
		 	}
		 	return msg;
		 }
		 @RequestMapping(value = "/ApproveExtensionHistoryData", method = RequestMethod.POST)
		 public @ResponseBody String ApproveExtensionHistoryData( 
		 		HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
		 	String username = session.getAttribute("username").toString();
		 	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		 	String comm_id=request.getParameter("comm_id");
		 	 int remp_id=Integer.parseInt(request.getParameter("remp_id"));
		 	 List<Map<String, Object>> ChangeOfExtension = ex.getHisOfExtension(remp_id,new BigInteger(comm_id),0,session);
		 		Date modified_date=new Date();
		 		String msg="";
		 	try{
		       if (ChangeOfExtension.size() > 0) {
		 		msg=ex.approveHisExtension(remp_id,new BigInteger(comm_id), ChangeOfExtension, username, modified_date);	
		 	 }
		 	}catch (RuntimeException e) { 
		 			msg= "Couldn?t roll back transaction " + e;
		 	} finally {
		 	}
		 	return msg;
		 }
		 
}
