package com.controller.psg.Civilian;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Civilian.Civilian_dtlDAO;
import com.dao.psg.Civilian.Posting_In_Out_Civilian_DAO;
import com.dao.psg.Civilian_Report.Arm_Service_Wise_RegularEst_Dao;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.UserLogin;
import com.models.psg.Civilian.TB_POSTING_IN_OUT_CIVILIAN;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Posting_In_Out_Civilian_Controller {

//	Census_Controller obj_cen = new Census_Controller();	
	
	@Autowired
	private Arm_Service_Wise_RegularEst_Dao regdao; 
	@Autowired
	private Civilian_dtlDAO civdao; 
	NotificationController notification = new NotificationController();
//	@Autowired
//	private Report_3008DAO report_3008DAO;
//	@Autowired
//	private Search_PostOutDao pod;
//	@Autowired
//	private Posting_In_Out_JCO_DAO PJD;
	@Autowired
	private Posting_In_Out_Civilian_DAO PCD;
	AllMethodsControllerOrbat hp = new AllMethodsControllerOrbat();
//	@Autowired
	Psg_CommonController commst = new Psg_CommonController();
	PsgDashboardController das = new PsgDashboardController();
//	@Autowired
	//CommanController com = new CommanController();
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
//	@Autowired
//	Search_UpdatedJcoOr_DataDao UOD;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Psg_CommanDAO psg_com;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	Search_UpdateOffDataDao UOD;
	
	/*--------------------------Page Open Post In Out-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_In_Out_CivilianUrl", method = RequestMethod.GET)
	public ModelAndView Posting_In_Out_CivilianUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("Posting_In_Out_CivilianUrl", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getstatusList", commst.getstatusList());
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("Posting_In_Out_CivilianTiles");
	}
	
	/*--------------------------Save Post OUT Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_Out_CivilianAction", method = RequestMethod.POST)
	public @ResponseBody String Posting_Out_CivilianAction(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request) throws ParseException,SQLException {
		
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		if (roleSusNo == null || roleSusNo.equals("")) {
			roleSusNo = request.getParameter("from_sus_no");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Date date = new Date();
		Date auth = null;
		Date sos = null;
		String username = session.getAttribute("username").toString();
		int civ_id = 0;
		if (Integer.parseInt(request.getParameter("civ_id")) != 0) {
			civ_id = Integer.parseInt(request.getParameter("civ_id"));
		}
		List<Map<String, Object>> list = PCD.GetCivilianOrCensusDataApprove(civ_id);
		TB_POSTING_IN_OUT_CIVILIAN POC = new TB_POSTING_IN_OUT_CIVILIAN();
		String to_sus_no = request.getParameter("to_sus_no");
		String out_auth = request.getParameter("out_auth");
		String employee_no = request.getParameter("employee_no");
		String out_auth_dt1 = request.getParameter("out_auth_dt");
		String unit_desc = request.getParameter("unit_description");
		
		int h_id = Integer.parseInt(request.getParameter("h_id"));
		
		String dt_of_sos1 = request.getParameter("dt_of_sos");
		
		if (out_auth.equals("") || out_auth == "" || out_auth == null) {
			return "Please Enter Auth No.";
		}
		
		if (!valid.isValidAuth(out_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}

		if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}
	
		if (!valid.isValidDate(out_auth_dt1)) {
			return valid.isValidDateMSG + " of Auth";
		}
		
		if (out_auth_dt1 == null || out_auth_dt1.equals("null") || out_auth_dt1.equals("DD/MM/YYYY") || out_auth_dt1.equals("")) {
			return "Please Select Auth Date";
		} else {
			auth = format.parse(out_auth_dt1);
		}
		
		if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
			return "Please Enter Unit SUS No";
		}
		
		if (to_sus_no != "") {
			if (!valid.SusNoLength(to_sus_no)) {
				return valid.SusNoMSG;
			}
		}
		
		if (!valid.isValidDate(dt_of_sos1)) {
			return valid.isValidDateMSG + " of SOS";
		}
		
		if (dt_of_sos1 == null || dt_of_sos1.equals("null") || dt_of_sos1.equals("DD/MM/YYYY") || dt_of_sos1.equals("")) {
			return "Please Select SOS Date";
		} else {
			sos = format.parse(dt_of_sos1);
		}
		if (dt_of_sos1 != null) {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 Date currentDate = new Date();
			 Date selectedDate = dateFormat.parse(dt_of_sos1);
		        if (selectedDate.after(currentDate)) {
		            return "Future dates are not allowed";
		    }		   
		}
		if(unit_desc!=null) {
			unit_desc=unit_desc.trim();
			if (!valid.isOnlyAlphabet(unit_desc)) {
				return "Unit Description  " + valid.isOnlyAlphabetMSG;
			}
		}
		String rvalue = "";
		int id1 = h_id > 0 ? POC.getId() : 0;
	try {
		Boolean v = PCD.getCivilianPernoAlreadyExits(civ_id);
		if (v == true)
		{
			rvalue = "Data already exists.";
			return rvalue;
		}
		if (v == false) {
			if (id1 == 0) {
				String roleAccess = session.getAttribute("roleAccess").toString();
				ArrayList<ArrayList<String>> orbatlist = regdao.getCivcommand(to_sus_no);
				ArrayList<ArrayList<String>> Location_Trnlist = regdao.getLocation_Trn(to_sus_no);
				POC.setTo_sus_no(to_sus_no);
				POC.setOut_auth(out_auth);
				POC.setOut_auth_dt(auth);
				POC.setDt_of_sos(sos);
				POC.setDt_of_tos(sos);
				POC.setCreated_by(username);
				POC.setCreated_date(new Date());
				POC.setCiv_id(civ_id);
				POC.setFrom_sus_no(roleSusNo);
				POC.setUnit_description(unit_desc);
				POC.setStatus(0);
				if(orbatlist.size()>0) {
				POC.setCmd_sus(orbatlist.get(0).get(1));
				POC.setCorps_sus(orbatlist.get(0).get(2));
				POC.setDiv_sus(orbatlist.get(0).get(3));
				POC.setBde_sus(orbatlist.get(0).get(4));
				}
				if(Location_Trnlist.size()>0) {
				POC.setLocation(Location_Trnlist.get(0).get(0));
				POC.setTrn_type(Location_Trnlist.get(0).get(1));
				}
				sessionHQL.save(POC);
				int id12 = (int) sessionHQL.save(POC);
				tx.commit();
				rvalue = String.valueOf(POC.getId());
				}
			}		
		} catch (RuntimeException e) {
		 try {
			 tx.rollback();
			 rvalue = "0";
		 } catch (RuntimeException rbe) {
			 rvalue = "0";
		 	}
		 } finally {
			 if (sessionHQL != null) {
				 sessionHQL.close();
			 }
		 }
		return rvalue;
	}
	// End Save Post OUT
	
/*--------------------------Save Post IN Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_In_CivilianAction", method = RequestMethod.POST)
	public @ResponseBody String Posting_In_CivilianAction(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request) throws ParseException {
		Date date = new Date();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		if (roleSusNo == null || roleSusNo.equals("")) {
			roleSusNo = request.getParameter("to_sus_no");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();
		String employee_no = request.getParameter("employee_no");
		String from_sus_no = request.getParameter("from_sus_no");
		String rvalue = "";
		if (roleSusNo.equals(from_sus_no))
		{
			rvalue = "Please check from SUS NO,it can't be same as like to SUS NO";
			return rvalue;
		}
		
		
		int civ_id = 0;
		if (Integer.parseInt(request.getParameter("civ_id")) != 0) {
			civ_id = Integer.parseInt(request.getParameter("civ_id"));
		}
		int i_id = Integer.parseInt(request.getParameter("i_id"));
		Date tos = null;
		String dt_of_tos1 = request.getParameter("dt_of_tos");
		String in_auth = request.getParameter("in_auth");
		Date in_auth_dt1 = null;
		String in_auth_dt = request.getParameter("in_auth_dt");
		
		TB_POSTING_IN_OUT_CIVILIAN PN = new TB_POSTING_IN_OUT_CIVILIAN();
		
		if (from_sus_no != "") {
			if (!valid.SusNoLength(from_sus_no)) {
				return  valid.SusNoMSG;
			}
		}

		if (in_auth == null || in_auth.trim().equals("")) {
			return "Please Enter Auth No";
		}

		if (!valid.isValidAuth(in_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}

		if (roleSusNo.equals("") || roleSusNo == "" || roleSusNo == null) {
			return "Please Enter To Unit SUS No";
		}
		if (!valid.isvalidLength(in_auth, valid.nameMax, valid.nameMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}
	
		if (!valid.isValidDate(in_auth_dt)) {
			return valid.isValidDateMSG + " of Auth";
		}
		
		if (in_auth_dt == null || in_auth_dt.equals("null") || in_auth_dt.equals("DD/MM/YYYY") || in_auth_dt.equals("")) {
			return "Please Select Auth Date";
		} else {
			in_auth_dt1 = format.parse(in_auth_dt);
		}
		
		if (!valid.isValidDate(dt_of_tos1)) {
			return valid.isValidDateMSG + " of TOS";
		}
		
		if (dt_of_tos1 == null || dt_of_tos1.equals("null") || dt_of_tos1.equals("DD/MM/YYYY") || dt_of_tos1.equals("")) {
			return "Please Select TOS Date";
		} else {
			tos = format.parse(dt_of_tos1);
		}
		if (dt_of_tos1 != null) {
  			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  			 Date currentDate = new Date();
  			 Date selectedDate = dateFormat.parse(dt_of_tos1);
  		        if (selectedDate.after(currentDate)) {
  		            return "Future dates are not allowed";
  		    }		   
  		}
		int id1 = i_id > 0 ? PN.getId() : 0;
	try {
		Boolean v = PCD.getCivilianPernoAlreadyExits(civ_id);
			if (v == true)
			{
				rvalue = "Data already exists.";
				return rvalue;
			}
			if (v == false) {
				if (id1 == 0) {
					String roleAccess = session.getAttribute("roleAccess").toString();
					ArrayList<ArrayList<String>> orbatlist = regdao.getCivcommand(roleSusNo);
					ArrayList<ArrayList<String>> Location_Trnlist = regdao.getLocation_Trn(roleSusNo);
					PN.setCreated_by(username);
					PN.setCreated_date(new Date());
				
					if(orbatlist.size()>0) {
					PN.setCmd_sus(orbatlist.get(0).get(1));
					PN.setCorps_sus(orbatlist.get(0).get(2));
					PN.setDiv_sus(orbatlist.get(0).get(3));
					PN.setBde_sus(orbatlist.get(0).get(4));
					}
					if(Location_Trnlist.size()>0) {
					PN.setLocation(Location_Trnlist.get(0).get(0));
					PN.setTrn_type(Location_Trnlist.get(0).get(1));
					}
					PN.setFrom_sus_no(from_sus_no);
					PN.setTo_sus_no(roleSusNo);
					PN.setDt_of_sos(tos);
					PN.setIn_auth(in_auth);
					PN.setCiv_id(civ_id);
					PN.setIn_auth_dt(in_auth_dt1);
					PN.setDt_of_tos(tos);
					PN.setStatus(0);
					PN.setNotification_status(0);
					sessionHQL.save(PN);
					int id12 = (int) sessionHQL.save(PN);
					tx.commit();
					rvalue = String.valueOf(PN.getId());
				}
			}
		} catch (RuntimeException e) {
			try {
			tx.rollback();
				rvalue = "0";
			} catch (RuntimeException rbe) {
				rvalue = "0";
			}
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return rvalue;
	}
	// End Save Post IN
	
	@RequestMapping(value = "/getEmployee_NoListApproved_Civilian", method = RequestMethod.POST)
	public @ResponseBody List<String> getEmployee_NoListApproved_Civilian(String employee_no, HttpSession sessionUserId,HttpServletRequest request) {

	        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = sessionHQL.beginTransaction();
	       //try{ 	        
	        String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	        String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	        String roleType = sessionUserId.getAttribute("roleType").toString();
	        Query q= null;
	        String rsus=request.getParameter("roleSus");
	       if( rsus!=null && !rsus.equals("")){
	        	roleSusNo=rsus;
	       }
	        
	        if(roleSusNo!=null && !roleSusNo.equals("")){
	              
	                 q = sessionHQL.createQuery("select distinct p.employee_no from TB_CIVILIAN_MAIN p where sus_no=:roleSusNo and (p.status=1)\r\n" + 
	                 								"and civilian_status = 'R' and upper(p.employee_no) like :employee_no "
	                 								+ "and (select count(id) from TB_CIVILIAN_DETAILS c where c.main_id=p.id and status in (0,3))=0  order by p.employee_no");            		 
	                 q.setParameter("roleSusNo", roleSusNo);  
	        }
	        else
	        {                 
	        	  q = sessionHQL.createQuery("select distinct p.employee_no from TB_CIVILIAN_MAIN p where (p.status=1) and p.civilian_status = 'R' \r\n" + 
	        			  						 "and upper(p.employee_no) like :employee_no "
	        			  						 + "and (select count(id) from TB_CIVILIAN_DETAILS c where c.main_id=p.id and status in (0,3))=0  order by p.employee_no");                               
	        }
	       
	        q.setParameter("employee_no", employee_no.toUpperCase()+"%");
	        @SuppressWarnings("unchecked")        
	        List<String> list = (List<String>) q.list();
	        tx.commit();
            String enckey = hex_asciiDao.getAlphaNumericString();
            Cipher c = null;
            try {
                    c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
                            | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                    e.printStackTrace();
            }
            List<String> FinalList = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                byte[] encCode = null;
                try {
                        encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
                } catch (IllegalBlockSizeException | BadPaddingException e) {
                        e.printStackTrace();
                }
                String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
                FinalList.add(base64EncodedEncryptedCode);
            }
            FinalList.add(enckey + "4bsjyg==");
            return FinalList;	                
	}
	
	@RequestMapping(value = "/GetEmployeeNoData", method = RequestMethod.POST)
	public @ResponseBody ArrayList<List<String>> GetEmployeeNoData(String employee_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		ArrayList<List<String>> list=null;

			 list = civdao.GetEmployeeNoData(employee_no);
		

			return list;
	}
	
	@RequestMapping(value = "/CheckEmployeeNoData", method = RequestMethod.POST)
	public @ResponseBody List<String> CheckEmployeeNoData(String employee_no) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select c.id,c.status FROM TB_CIVILIAN_MAIN c where upper(employee_no) = :employee_no  order by employee_no");
			q1.setParameter("employee_no", employee_no.toUpperCase());
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			tx.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@RequestMapping (value = "/admin/GETPostTenure_Date_Civilian", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GETPostTenure_Date_Civilian(ModelMap Mmap, HttpSession session,
			 int civ_id,HttpServletRequest request){
		ArrayList<ArrayList<String>> list = PCD.getPostTenureDateCivilian(civ_id);
		return list;
	 }
	
	@RequestMapping(value = "/GetCivilianOrCensusDataApprove", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetCivilianOrCensusDataApprove(int civ_id) throws SQLException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<Map<String, Object>> list = PCD.GetCivilianOrCensusDataApprove(civ_id);
		tx.commit();
		return list;
	}
	
	/*---------------------------Search POST IN OUT Page Open url -----------------------------------------------------*/
	@RequestMapping(value = "/admin/Search_Posting_In_Out_CivilianUrl", method = RequestMethod.GET)
	public ModelAndView Search_Posting_In_Out_CivilianUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "employee_no4", required = false) String employee_no4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "to_sus_no4", required = false) String to_sus_no4,
			@RequestParam(value = "from_sus_no4", required = false) String from_sus_no4,
			@RequestParam(value = "type4", required = false) String type4,
			@RequestParam(value = "status4", required = false) String status4,
			HttpServletRequest request, HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleAccess", roleAccess);
		Mmap.put("msg", msg);
		Mmap.put("employee_no4", employee_no4);
		Mmap.put("rank4", rank4);
		Mmap.put("to_sus_no4", to_sus_no4);
		Mmap.put("from_sus_no4", from_sus_no4);
		Mmap.put("type4", type4);
		Mmap.put("status4", status4);
		if (hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", hp.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("list", PCD.Search_Postout_Civilian( "","", "", "", roleType,"0","","",roleSusNo));
		Mmap.put("list1", PCD.Search_PostIn_Civilian("", "", "", "", roleType,"0","","",roleSusNo));
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getDesignationList", commst.getDesignationList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		return new ModelAndView("Search_Posting_In_Out_CivilianTiles");
	}
	
/*---------------------------Search Postout -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_Out_Civilian", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_Out_Civilian(ModelMap Mmap, HttpSession sessionA,
			HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "employee_no", required = false) String employee_no,
			@RequestParam(value = "rank", required = false) String rank,
			@RequestParam(value = "to_sus_no", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_no", required = false) String from_sus_no,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "cr_by", required = false) String cr_by,
		    @RequestParam(value = "cr_date", required = false) String cr_date) {
		
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> list_error = new ArrayList<String>();
		 if(to_sus_no!="") {
	    	  if (!valid.SusNoLength(to_sus_no)) {
	    		  list_error.add("error");
	    		  list_error.add(valid.SusNoMSG);
	    		  list.add(list_error);
				  return list;
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(to_sus_no)) {
	    		  list_error.add("error");
	    		  list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
	    		  list.add(list_error);
					return list;
				}
	      }
		 
		 if(employee_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(employee_no)) {
					list_error.add("error");
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
		    		list.add(list_error);
		    		return list;					
				}
			  	
			  if (!valid.isvalidLength(employee_no, valid.nameMax, valid.nameMin)) {
				  	list_error.add("error");
		    		list_error.add("Employee No " +valid.isValidLengthMSG);
		    		list.add(list_error);
		    		return list;				
				}		  
	      }
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("cr_date", cr_date);
		Mmap.put("cr_by", cr_by);
			list = PCD.Search_Postout_Civilian(from_sus_no, employee_no, rank, to_sus_no, roleType,status,cr_by,cr_date,roleSusNo);
		return list;
	}
	//End search postout
	
/*---------------------------Search PostIN -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_In_Civilian", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_In_Civilian(ModelMap Mmap, HttpSession sessionA,
			HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "employee_no", required = false) String employee_no,
			@RequestParam(value = "rank", required = false) String rank,
			@RequestParam(value = "to_sus_no", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_no", required = false) String from_sus_no,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "cr_by", required = false) String cr_by,
		    @RequestParam(value = "cr_date", required = false) String cr_date
			) {

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> list_error = new ArrayList<String>();
		 if(from_sus_no!="") {
	    	  if (!valid.SusNoLength(from_sus_no)) {
	    		  list_error.add("error");
	    		  list_error.add(valid.SusNoMSG);
	    		  list.add(list_error);
				  return list;
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(from_sus_no)) {
	    		  list_error.add("error");
	    		  list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
	    		  list.add(list_error);
					return list;
				}
	      }
		 
		 if(employee_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(employee_no)) {
					list_error.add("error");
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
		    		list.add(list_error);
		    		return list;					
				}
			  	
			  if (!valid.isvalidLength(employee_no, valid.nameMax, valid.nameMin)) {
				  	list_error.add("error");
		    		list_error.add("Employee No " +valid.isValidLengthMSG);
		    		list.add(list_error);
		    		return list;				
				}		  
	      }
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("cr_date", cr_date);
		Mmap.put("cr_by", cr_by);
			list = PCD.Search_PostIn_Civilian(to_sus_no, employee_no, rank, from_sus_no, roleType,status,cr_by,cr_date,roleSusNo);
		return list;
	}
	//End search postout
	
/*---------------------------Approve PostOut-------------------------------------*/
/*	@RequestMapping(value = "/Approve_PostOUT_Civilian", method = RequestMethod.POST)
	public  ModelAndView Approve_PostOUT_Civilian(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idapp1", required = false) String civ_id,
			@RequestParam(value = "sus_noapp1", required = false) String sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "employee_noapp1", required = false) String employee_no,
			@RequestParam(value = "rankapp1", required = false) String rank,
			@RequestParam(value = "to_sus_noapp1", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp1", required = false) String from_sus_no,
			@RequestParam(value = "typeapp1", required = false) String type,
			@RequestParam(value = "statusapp1", required = false) String status,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";

			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN ";

			hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
			String hqlUpdate2 = "update TB_CIVILIAN_MAIN set sus_no=:sus_no where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no)
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();
			String hqlUpdate3 = "update TB_CIVILIAN_DETAILS set sus_no=:sus_no where status='1' and id=:id ";
			int app3 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no)
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();
			List<Map<String, Object>> lpInout=PCD.getLastPostInOutCivilian(Integer.parseInt(civ_id));
			if(lpInout.size()>1) {
				String rmsg=PCD.setTenureDate_Civilian(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}	
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}*/
	@RequestMapping(value = "/Approve_PostOUT_Civilian", method = RequestMethod.POST)
	public  ModelAndView Approve_PostOUT_Civilian(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idapp1", required = false) String civ_id,
			@RequestParam(value = "sus_noapp1", required = false) String sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "employee_noapp1", required = false) String employee_no,
			@RequestParam(value = "rankapp1", required = false) String rank,
			@RequestParam(value = "to_sus_noapp1", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp1", required = false) String from_sus_no,
			@RequestParam(value = "typeapp1", required = false) String type,
			@RequestParam(value = "dt_tos1", required = false) String dt_tos1,
			@RequestParam(value = "statusapp1", required = false) String status,
			Authentication authentication) {
		
		//chauhan
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		try {
			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN ";

			hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
			String hqlUpdate2 = "update TB_CIVILIAN_MAIN set sus_no=:sus_no, date_of_tos=:date_of_tos where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no).setTimestamp("date_of_tos", format.parse(dt_tos1))
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();
			//use of wrong column id for update
			/*String hqlUpdate3 = "update TB_CIVILIAN_DETAILS set sus_no=:sus_no where status='1' and id=:id ";
			int app3 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no)
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();*/
			
			Query q0 = sessionHQL.createQuery("SELECT id FROM TB_CIVILIAN_DETAILS WHERE MAIN_ID=:id and status=1 ORDER BY ID DESC").setMaxResults(1);
			q0.setParameter("id", Integer.parseInt(civ_id));   
			List<String> list = (List<String>) q0.list();
			
			if(list.size() > 0)
			{
				int app3 =0;
				String hqlUpdate3 = "UPDATE TB_CIVILIAN_DETAILS main\r\n" + 
						"SET sus_no=:sus_no, date_of_tos=:date_of_tos WHERE  id =:id ";
				 app3 = sessionHQL.createQuery(hqlUpdate3)
						.setString("sus_no", sus_no).setTimestamp("date_of_tos", format.parse(dt_tos1))
						.setInteger("id", Integer.parseInt(String.valueOf(list.get(0)) )).executeUpdate();
				
				
			}
			List<Map<String, Object>> lpInout=PCD.getLastPostInOutCivilian(Integer.parseInt(civ_id));
			if(lpInout.size()>1) {
				String rmsg=PCD.setTenureDate_Civilian(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}	
			tx.commit();
			sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");	
			List<Map<String, Object>> notit=PCD.getLastPostInnoti_civ(Integer.parseInt(civ_id));
			String from_sus =notit.get(0).get("from_sus_no").toString();
			String date_tos =notit.get(0).get("dt_of_tos").toString();
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			
			List<Map<String, Object>> from_sus_unit = UOD.getunitdet(from_sus);
			
			String from_unit = from_sus_unit.get(0).get("unit_name").toString();
			List<Map<String, Object>> tosus_no_unit=UOD.getunitdet(tosus_no);
			String to_unit = tosus_no_unit.get(0).get("unit_name").toString();
			
			///bisag v2 250822  (notification)
			if(roleSusNo.equals(""))
			{
				 List<UserLogin> userlist = getPostIN_outuseridlist(tosus_no);
                 String user_id = "";
              		for (int i = 0; i < userlist.size(); i++) {
              			if(i==0) {
              				user_id += 	userlist.get(i).getUserId();
              			}
              			
              			else {
              				user_id += ","+userlist.get(i).getUserId();
              			}
              					
						}
              		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
				   String per_no = notit.get(0).get("employee_no").toString();
				   String namein = notit.get(0).get("full_name").toString();
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+namein+" has been Posted in from " +from_unit +" on " +date_tos ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
              		}
				    List<UserLogin> userlistout = getPostIN_outuseridlist(from_sus);
	                 String user_idout = "";
	              		for (int i = 0; i < userlistout.size(); i++) {
	              			if(i==0) {
	              				user_idout += 	userlistout.get(i).getUserId();
	              			}
	              			
	              			else {
	              				user_idout += ","+userlistout.get(i).getUserId();
	              			}
	              					
							}
	              		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
					   String per_noout = notit.get(0).get("employee_no").toString();
					   String nameout = notit.get(0).get("full_name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", "+nameout+" has been posted out to " +to_unit +" on " +date_tos ;
					    Boolean dout = notification.sendNotification(titleOUT, descriptionOut,user_idout, username);    
	              		}
				
			}
			else {
			String reciver_sus_no="";
			
			
			if(roleSusNo.equals(sus_no))
			{
			reciver_sus_no=from_sus_no;
				
			}
			if(roleSusNo.equals(from_sus_no))
			{
				reciver_sus_no=sus_no;
			}
			
			
			
			
			
			List<UserLogin> userlist = getPostIN_outuseridlist(reciver_sus_no);
					
						 String user_id = "";
			     		for (int i = 0; i < userlist.size(); i++) {
			     			if(i==0) {
			     				user_id += 	userlist.get(i).getUserId();
			     			}
			     			
			     			else {
			     				user_id += ","+userlist.get(i).getUserId();
			     			}
			     					
							}
			     		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
						String per_no = notit.get(0).get("employee_no").toString();
						 String namein = notit.get(0).get("full_name").toString();
			          
			           String title = "POST OUT" ;
			           
			           String description = ""+per_no+", "+namein+" has been posted in from " +from_unit+" on "+date_tos ;
			           
			     //UOD.SendNotification(title,description,user_id);
			       	Boolean d = notification.sendNotification(title, description,user_id, username);
			
			     		}
			
			
			}
			
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);

		} catch (Exception e) {
			
		} finally {
			if (sessionHQL != null) {
				
			}
		}
		
		return new ModelAndView("Search_Posting_In_Out_CivilianTiles");
	}

	
	
	/*---------------------------//Approve PostIn  ----------------------------------------------------*/
	@RequestMapping(value = "/Approve_PostIN_Civilian", method = RequestMethod.POST)
	public  ModelAndView Approve_PostIN_Civilian(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idapp2", required = false) String civ_id,
			@RequestParam(value = "sus_noapp2", required = false) String sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "employee_noapp2", required = false) String employee_no,
			@RequestParam(value = "rankapp2", required = false) String rank,
			@RequestParam(value = "to_sus_noapp2", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp2", required = false) String from_sus_no,
			@RequestParam(value = "typeapp2", required = false) String type,
			@RequestParam(value = "dt_tos2", required = false) String dt_tos2,
			@RequestParam(value = "statusapp2", required = false) String status,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		try {
			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN";

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();

			String hqlUpdate2 = "update TB_CIVILIAN_MAIN set sus_no=:sus_no, date_of_tos=:date_of_tos where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no).setTimestamp("date_of_tos", format.parse(dt_tos2))
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();
			/*String hqlUpdate3 = "update TB_CIVILIAN_DETAILS set sus_no=:sus_no where status='1' and id=:id ";
			int app3 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", sus_no)
					.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();*/
			
			Query q0 = sessionHQL.createQuery("SELECT id FROM TB_CIVILIAN_DETAILS WHERE MAIN_ID=:id and status=1 ORDER BY ID DESC").setMaxResults(1);
			q0.setParameter("id", Integer.parseInt(civ_id));   
			List<String> list = (List<String>) q0.list();
			int app3 =0;
			if(list.size() > 0)
			{
				String hqlUpdate3 = "UPDATE TB_CIVILIAN_DETAILS main\r\n" + 
						"SET sus_no=:sus_no, date_of_tos=:date_of_tos WHERE  id =:id ";
				 app3 = sessionHQL.createQuery(hqlUpdate3)
						.setString("sus_no", sus_no).setTimestamp("date_of_tos", format.parse(dt_tos2))
						.setInteger("id", Integer.parseInt(String.valueOf(list.get(0)) )).executeUpdate();
				
				
			}
			
			List<Map<String, Object>> lpInout=regdao.getLastPostInOutCIVILIAN(Integer.parseInt(civ_id));
			if(lpInout.size()>1) {
				String rmsg=PCD.setTenureDate_Civilian(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}	
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
			List<Map<String, Object>> notit=PCD.getLastPostInnoti_civ(Integer.parseInt(civ_id));
			String from_sus =notit.get(0).get("from_sus_no").toString();
			String date_tos =notit.get(0).get("dt_of_tos").toString();
			String reciver_sus_no="";
			
			
			String tos_date = notit.get(0).get("dt_of_tos").toString();
			
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			List<Map<String, Object>> from_sus_unit = UOD.getunitdet(from_sus);
			String from_unit = from_sus_unit.get(0).get("unit_name").toString();
			List<Map<String, Object>> tosus_no_unit=UOD.getunitdet(tosus_no);
			String to_unit = tosus_no_unit.get(0).get("unit_name").toString();
			
			///bisag v2 250822  (notification)
			if(roleSusNo.equals(""))
			{
				 List<UserLogin> userlist = getPostIN_outuseridlist(tosus_no);
                 String user_id = "";
              		for (int i = 0; i < userlist.size(); i++) {
              			if(i==0) {
              				user_id += 	userlist.get(i).getUserId();
              			}
              			
              			else {
              				user_id += ","+userlist.get(i).getUserId();
              			}
              					
						}
              		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
				   String per_no = notit.get(0).get("employee_no").toString();
				   String namein = notit.get(0).get("full_name").toString();
				   
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
              		}
				    List<UserLogin> userlistout = getPostIN_outuseridlist(from_sus);
	                 String user_idout = "";
	              		for (int i = 0; i < userlistout.size(); i++) {
	              			if(i==0) {
	              				user_idout += 	userlistout.get(i).getUserId();
	              			}
	              			
	              			else {
	              				user_idout += ","+userlistout.get(i).getUserId();
	              			}
	              					
							}
	              		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
					   String per_noout = notit.get(0).get("employee_no").toString();
					   String nameout = notit.get(0).get("full_name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", "+nameout+" has been posted out to " +to_unit +" on " +tos_date ;
					    Boolean dout = notification.sendNotification(titleOUT, descriptionOut,user_idout, username);    
				    
	              		}
			}
			else {
			
			
			if(roleSusNo.equals(to_sus_no))
			{
			reciver_sus_no=from_sus;
				
			}
			if(roleSusNo.equals(from_sus))
			{
				reciver_sus_no=to_sus_no;
			}
			
			
			
			
			
			List<UserLogin> userlist = getPostIN_outuseridlist(reciver_sus_no);
					
						 String user_id = "";
			     		for (int i = 0; i < userlist.size(); i++) {
			     			if(i==0) {
			     				user_id += 	userlist.get(i).getUserId();
			     			}
			     			
			     			else {
			     				user_id += ","+userlist.get(i).getUserId();
			     			}
			     					
							}
			     		if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
						String per_no = notit.get(0).get("employee_no").toString();
						String name = notit.get(0).get("full_name").toString();
			          
			           String title = "POST IN" ;
			           
			           String description = ""+per_no+", "+name+" has been posted out to " +to_unit+" on "+date_tos ;
			           
			     //UOD.SendNotification(title,description,user_id);
			       	Boolean d = notification.sendNotification(title, description,user_id, username);
			
			     		}
			}
		} else {
			liststr.add("Approved Not Successfully.");
		}
		}catch (Exception e) {
			tx.rollback();
			liststr.add("Data Not Approved.");
		}finally {
			
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("Search_Posting_In_Out_CivilianTiles");
	}
	
	/*------------------------------Approve Cancel History----------------------------------------------*/
	@RequestMapping(value = "/ApproveCancelHistory_PostINOUT_Civilian", method = RequestMethod.POST)
	public  ModelAndView ApproveCancelHistory_PostINOUT_Civilian(@ModelAttribute("idach") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idach", required = false) String civ_id,					
			@RequestParam(value = "employee_noach", required = false) String employee_no,
			@RequestParam(value = "rankach", required = false) String rank,
			@RequestParam(value = "to_sus_noach", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noach", required = false) String from_sus_no,
			@RequestParam(value = "typeach", required = false) String type,
			@RequestParam(value = "statusach", required = false) String status,
			Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String tb_name="";
		String hqlUpdate1 = "";
		try {
			tb_name += " TB_POSTING_IN_OUT_CIVILIAN ";

		if(!tb_name.equals("")) {
		hqlUpdate1 += "update "+tb_name+" set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";	
		Query query = sessionHQL.createQuery(hqlUpdate1);				
		int app=query.setString("modified_by", username)
		.setDate("modified_date", new Date()).setInteger("status", -1).setInteger("cancel_status", 1)
		.setInteger("id", id).executeUpdate();
		sessionHQL.flush();
		sessionHQL.clear();
		String hqlUpdate2="select id from "+tb_name+" where civ_id=:civ_id and status=1 order by id desc ";
		Query query2 = sessionHQL.createQuery(hqlUpdate2).setInteger("civ_id", Integer.parseInt(civ_id)).setMaxResults(1);
		Integer c = (Integer)  query2.uniqueResult();
		int app2=0;
		if(c!=null && c>0) {
			int chang_id=c.intValue();
			TB_POSTING_IN_OUT_CIVILIAN postin_out = (TB_POSTING_IN_OUT_CIVILIAN) sessionHQL.get(TB_POSTING_IN_OUT_CIVILIAN.class, chang_id);
				postin_out.setTenure_date(null);
				sessionHQL.update(postin_out);
				String hqlUpdate3 = "update TB_CIVILIAN_MAIN set sus_no=:sus_no, date_of_tos=:date_of_tos where id=:id";
				app2 = sessionHQL.createQuery(hqlUpdate3)
						.setString("sus_no", postin_out.getTo_sus_no()).setTimestamp("date_of_tos", postin_out.getDt_of_tos())
						.setInteger("id", Integer.parseInt(civ_id)).executeUpdate();	
				
				
				Query q0 = sessionHQL.createQuery("SELECT id FROM TB_CIVILIAN_DETAILS WHERE MAIN_ID=:id and status=1 ORDER BY ID DESC").setMaxResults(1);
				q0.setParameter("id", Integer.parseInt(civ_id));   
				List<String> list = (List<String>) q0.list();
				int app3 =0;
				if(list.size() > 0)
				{
					String hqlUpdate4 = "UPDATE TB_CIVILIAN_DETAILS main\r\n" + 
							"SET sus_no=:sus_no, date_of_tos=:date_of_tos WHERE  id =:id ";
					 app3 = sessionHQL.createQuery(hqlUpdate4)
							.setString("sus_no", postin_out.getTo_sus_no()).setTimestamp("date_of_tos", postin_out.getDt_of_tos())
							.setInteger("id", Integer.parseInt(String.valueOf(list.get(0)) )).executeUpdate();
					
					
				}
				
				//KAJAL CHAUHAN 1111
		}
		if (app > 0 && app2 > 0) {
			tx.commit();
				liststr.add("Approved Successfully.");
		} else {
				tx.rollback();
				liststr.add("Approved Not Successfully.");
			}
		}
		else {
			liststr.add("Data Not Approved.");
			}
		}catch (Exception e) {
			tx.rollback();
			liststr.add("Data Not Approved.");
		}finally {
			sessionHQL.close();
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	
/*-------------------------------Reject Cancel Postout-----------------------------------------------*/
	@RequestMapping(value = "/CancelRejectHistory_PostINOUT_Civilian", method = RequestMethod.POST)
	public  ModelAndView CancelRejectHistory_PostINOUT_Civilian(@ModelAttribute("idcrh") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idcrh", required = false) String civ_id,					
			@RequestParam(value = "employee_nocrh", required = false) String employee_no,
			@RequestParam(value = "rankcrh", required = false) String rank,
			@RequestParam(value = "to_sus_nocrh", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_nocrh", required = false) String from_sus_no,
			@RequestParam(value = "typecrh", required = false) String type,
			@RequestParam(value = "statuscrh", required = false) String status,
			@RequestParam(value = "reject_remarkscrh", required = false) String reject_remarks,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN ";
			
		hqlUpdate1 += " set cancel_status=:cancel_status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date,cancel_by=:cancel_by,cancel_date=:cancel_date  where id=:id";
	try {
		Query query = sessionHQL.createQuery(hqlUpdate1);
		if(roleType.equals("DEO"))
				query.setInteger("cancel_status", 0).setString("reject_remarks", reject_remarks).setString("cancel_by", username)
				.setDate("cancel_date", new Date());
		else
			query.setInteger("cancel_status", 2).setString("cancel_by", null)
			.setDate("cancel_date", null);
	int app=query.setString("modified_by", username)
		.setDate("modified_date", new Date())
		.setInteger("id", id).executeUpdate();
		tx.commit();
		if (app > 0) {
			if(roleType.equals("DEO"))
				liststr.add("Cancelled Successfully.");
			else
				liststr.add("Rejected Successfully.");
		} else {
			tx.rollback();
			if(roleType.equals("DEO"))
				liststr.add("Cancelled Not Successfully.");
			else
				liststr.add("Rejected Not Successfully.");
		}
	}catch (Exception e) {
		tx.rollback();
		if(roleType.equals("DEO"))
			liststr.add("Cancelled Not Successfully.");
		else
			liststr.add("Rejected Not Successfully.");
	}finally {
		sessionHQL.close();
	}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	
/*------------------------------Delete PostIn-----------------------------------------*/
	@RequestMapping(value = "/Delete_PostIN_Civilian", method = RequestMethod.POST)
	public @ResponseBody String Delete_PostIN_Civilian(HttpServletRequest request,HttpSession sessionA, ModelMap model) {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		int id=Integer.parseInt(request.getParameter("id"));
			hqlUpdate1 += "DELETE FROM TB_POSTING_IN_OUT_CIVILIAN";
			hqlUpdate1 += "   where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1)
				.setInteger("id", id).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
		msg="Deleted Successfully.";
		} else {
			msg="Deleted Not Successfully.";
		}
		return msg;
	}
	
/*---------------------------Reject PostOut---------------------------------------------*/
	@RequestMapping(value = "/Reject_PostOUT_Civilian", method = RequestMethod.POST)
	public  ModelAndView Reject_PostOUT_Civilian(@ModelAttribute("idoutr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idoutr", required = false) String civ_id,
			@RequestParam(value = "sus_nooutr", required = false) String sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "employee_nooutr", required = false) String employee_no,
			@RequestParam(value = "rankoutr", required = false) String rank,
			@RequestParam(value = "to_sus_nooutr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_nooutr", required = false) String from_sus_no,
			@RequestParam(value = "typeoutr", required = false) String type,
			@RequestParam(value = "statusoutr", required = false) String status,
			@RequestParam(value = "rej_remarkoutr", required = false) String reject_remarks,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN ";

		hqlUpdate1 += " set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Rejected Successfully.");
		} else {
			liststr.add("Data Not Rejected");
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	
/*-------------------------------Reject PostIn------------------------------------------------*/
	@RequestMapping(value = "/Reject_PostIN_Civilian", method = RequestMethod.POST)
	public  ModelAndView Reject_PostIN_Civilian(@ModelAttribute("idinr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "civ_idinr", required = false) String civ_id,
			@RequestParam(value = "sus_noinr", required = false) String sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "employee_noinr", required = false) String employee_no,
			@RequestParam(value = "rankinr", required = false) String rank,
			@RequestParam(value = "to_sus_noinr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noinr", required = false) String from_sus_no,
			@RequestParam(value = "typeinr", required = false) String type,
			@RequestParam(value = "statusinr", required = false) String status,
			@RequestParam(value = "rej_remarkinr", required = false) String reject_remarks,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
			hqlUpdate1 += "update TB_POSTING_IN_OUT_CIVILIAN";

		hqlUpdate1 += " set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Data Rejected Successfully.");
		} else {
			liststr.add("Data Not Rejected");
		}
		model.put("msg", liststr.get(0));
		model.put("employee_no4", employee_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	
/*---------------------------Edit PostOut Page Open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_Out_CivilianUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Post_Out_CivilianUrl(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "employee_no6", required = false) String employee_no6,
			@RequestParam(value = "rank6", required = false) String rank6,
			@RequestParam(value = "to_sus_no6", required = false) String to_sus_no6,
			@RequestParam(value = "from_sus_no6", required = false) String from_sus_no6,
			@RequestParam(value = "type6", required = false) String type6,
			@RequestParam(value = "status6", required = false) String status6,
			HttpSession sessionEdit, HttpServletRequest request) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = PCD.GetPost_OutByid_Civilian(Integer.parseInt(updateid));
		model.put("list", list);
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("msg", msg);
		model.put("employee_no6", employee_no6);
		model.put("rank6", rank6);
		model.put("to_sus_no6", to_sus_no6);
		model.put("from_sus_no6", from_sus_no6);
		model.put("type6", type6);
		model.put("status6", status6);
		return new ModelAndView("Edit_Post_Out_CivilianTiles");
	}
	
/*---------------------------Edit PostOut Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_Posting_Out_CivilianAction", method = RequestMethod.POST)
	public ModelAndView Edit_Posting_Out_CivilianAction(@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			model.put("employee_no4", request.getParameter("inemployee_no3v"));
			model.put("rank4", request.getParameter("inrank3v"));
			model.put("to_sus_no4", request.getParameter("into_sus_no3v"));
			model.put("from_sus_no4", request.getParameter("infrom_sus_no_out3v"));
			model.put("type4", request.getParameter("intype3v"));
			model.put("status4", request.getParameter("instatus3v"));
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String to_sus_no = request.getParameter("to_sus_no");
			String out_auth = request.getParameter("out_auth");
			String unit_desc = request.getParameter("unit_description_out");
			Date out_auth_dt1 = null;
			String out_auth_dt = request.getParameter("out_auth_dt");
			Date dt_of_sos1 = null;
			String dt_of_sos = request.getParameter("dt_of_sos");
			String dt_tos_pre = request.getParameter("dt_tos_pre");
			int hh_id = Integer.parseInt(request.getParameter("id"));
			
			TB_POSTING_IN_OUT_CIVILIAN tb = new TB_POSTING_IN_OUT_CIVILIAN();
			
			
			if(out_auth==null || out_auth.trim().equals("") ) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if (!valid.isValidAuth(out_auth)) {
				model.put("msg", "Please Valid Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}

			if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
		
			if (!valid.isValidDate(out_auth_dt)) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if(out_auth_dt==null || out_auth_dt.trim().equals("") || out_auth_dt.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			else {
				out_auth_dt1 = format.parse(out_auth_dt);
			}
			
			if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
				model.put("msg", "Please Enter To Unit SUS No");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if (to_sus_no.equals(roleSusNo))
			{
				model.put("msg", "Please check To SUS NO,it can't be same as like FROM SUS NO");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if (to_sus_no != "") {
				if (!valid.SusNoLength(to_sus_no)) {
					model.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
				}
			}
			
			if (!valid.isValidDate(dt_of_sos)) {
				model.put("msg", valid.isValidDateMSG + " of SOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if(dt_of_sos==null || dt_of_sos.trim().equals("") || dt_of_sos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of SOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			else {
				dt_of_sos1 = format.parse(dt_of_sos);
			}
			if (dt_of_sos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_sos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			    }
			}
			if(unit_desc!=null) {
				unit_desc=unit_desc.trim();
				if (!valid.isOnlyAlphabet(unit_desc)) {
					model.put("msg", "Unit Description  " + valid.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
				}
			}
		
			String rvalue = "";
			String hql = "";

				hql += "update TB_POSTING_IN_OUT_CIVILIAN";
			
			hql += " set out_auth=:out_auth,out_auth_dt=:out_auth_dt,unit_description=:unit_description,to_sus_no=:to_sus_no, dt_of_sos=:dt_of_sos,dt_of_tos=:dt_of_tos,status=0 where id=:h_id";
			Query query = sessionHQL.createQuery(hql)
					.setParameter("out_auth", out_auth)
					.setParameter("out_auth_dt", out_auth_dt1)
					.setParameter("to_sus_no", to_sus_no)
					.setParameter("unit_description", unit_desc)
					.setParameter("dt_of_sos", dt_of_sos1)
					.setParameter("dt_of_tos", dt_of_sos1)
					.setParameter("h_id", hh_id);
			rvalue = query.executeUpdate() > 0 ? "update" : "0";
			model.put("msg", "Data Updated Successfully");
			tx.commit();
			sessionHQL.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	// End Edit Post Out Action
	
	/*---------------------------Edit PostIn Page Open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_In_CivilianUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Post_In_CivilianUrl(@ModelAttribute("inupdateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "employee_no3", required = false) String employee_no3,
			@RequestParam(value = "rank3", required = false) String rank3,
			@RequestParam(value = "to_sus_no3", required = false) String to_sus_no3,
			@RequestParam(value = "from_sus_no3", required = false) String from_sus_no3,
			@RequestParam(value = "type3", required = false) String type3,
			@RequestParam(value = "status3", required = false) String status3,
			HttpSession sessionEdit, HttpServletRequest request) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = PCD.GetPost_InByid_Civilian(Integer.parseInt(updateid));
		model.put("list", list);
		model.put("getTypeofAppointementList", commst.getTypeofAppointementList());
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("msg", msg);
		model.put("employee_no3", employee_no3);
		model.put("rank3", rank3);
		model.put("to_sus_no3", to_sus_no3);
		model.put("from_sus_no3", from_sus_no3);
		model.put("type3", type3);
		model.put("status3", status3);
		return new ModelAndView("Edit_Posting_In_CivilianTiles");
	}
	
 /*---------------------------Edit PostIn Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_Posting_In_CivilianAction", method = RequestMethod.POST)
	public ModelAndView Edit_Posting_In_CivilianAction(HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg, ModelMap model, HttpSession session)
			throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_CivilianUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			model.put("employee_no4", request.getParameter("inemployee_no3v"));
			model.put("rank4", request.getParameter("inrank3v"));
			model.put("to_sus_no4", request.getParameter("into_sus_no3v"));
			model.put("from_sus_no4", request.getParameter("infrom_sus_no_out3v"));
			model.put("type4", request.getParameter("intype3v"));
			model.put("status4", request.getParameter("instatus3v"));
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String from_sus_no = request.getParameter("from_sus_no");
			
			Date dt_of_tos1 = null;
			String dt_of_tos = request.getParameter("dt_of_tos");
			String dt_tos_pre = request.getParameter("dt_tos_pre");
			
			if (!valid.isValidDate(dt_of_tos)) {
				model.put("msg", valid.isValidDateMSG + " of TOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			
			if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of TOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			}
			if (dt_of_tos != "") {
				dt_of_tos1 = format.parse(dt_of_tos);
			}
			if (dt_of_tos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_tos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
			    }		   
			}

			
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";
				hql += "update TB_POSTING_IN_OUT_CIVILIAN";
			
			hql += "  set dt_of_tos=:dt_of_tos,dt_of_sos=:dt_of_sos,status=0 where id=:h_id";
			Query query = sessionHQL.createQuery(hql)
					.setParameter("dt_of_tos", dt_of_tos1).setParameter("dt_of_sos", dt_of_tos1).setParameter("h_id", hh_id);
			rvalue = query.executeUpdate() > 0 ? "update" : "0";
			model.put("msg", "Data Updated Successfully");
			tx.commit();
			sessionHQL.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("redirect:Search_Posting_In_Out_CivilianUrl");
	}
	// End Edit PostIn Action
	 public List<UserLogin> getPostIN_outuseridlist(String to_sus_no) {

	    	
         Session session1 = HibernateUtil.getSessionFactory().openSession();

         Transaction tx1 = session1.beginTransaction();

         Query q1 = session1.createQuery(
                         " from UserLogin where user_sus_no=:user_sus_no");
         q1.setString("user_sus_no", to_sus_no);
         @SuppressWarnings("unchecked")

         List<UserLogin> list = (List<UserLogin>) q1.list();

         tx1.commit();

         session1.close();

         return list;

 }
	 
	 //psg p1 v2
	 

@RequestMapping(value = "/getEmpNoCivilianList", method = RequestMethod.POST)
		public @ResponseBody List<String> getEmpNoCivilianList(String employee_no, HttpSession sessionUserId) {

		        Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = sessionHQL.beginTransaction();		
		                
		       Query q = sessionHQL.createQuery("select distinct p.employee_no from TB_CIVILIAN_MAIN p where (p.status=1) and p.civilian_status = 'R' \r\n" + 
		        			  						 "and upper(p.employee_no) like :employee_no "
		        			  						 + "and (select count(id) from TB_CIVILIAN_DETAILS c where c.main_id=p.id and status in (0,3))=0  order by p.employee_no");                               
		       
		        q.setParameter("employee_no", "%"+employee_no.toUpperCase()+"%");
		        @SuppressWarnings("unchecked")        
		        List<String> list = (List<String>) q.list();
		        tx.commit();
	            String enckey = hex_asciiDao.getAlphaNumericString();
	            Cipher c = null;
	            try {
	                    c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
	            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
	                            | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
	                    e.printStackTrace();
	            }
	            List<String> FinalList = new ArrayList<String>();
	            for (int i = 0; i < list.size(); i++) {
	                byte[] encCode = null;
	                try {
	                        encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
	                } catch (IllegalBlockSizeException | BadPaddingException e) {
	                        e.printStackTrace();
	                }
	                String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
	                FinalList.add(base64EncodedEncryptedCode);
	            }
	            FinalList.add(enckey + "4bsjyg==");
	            return FinalList;	                
		}
}
