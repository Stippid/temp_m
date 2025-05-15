package com.controller.psg.Transaction;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import org.hibernate.HibernateException;
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
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Census.Census_Controller;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.UserLogin;
import com.models.psg.Master.TB_BANK;
import com.models.psg.Master.TB_MARITAL_STATUS;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_MISO_ROLE_HDR_STATUS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_CHANGE_OF_APPOINTMENT;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Posting_Out_Controller {
	Census_Controller obj_cen = new Census_Controller();
	@Autowired
	private Report_3008DAO report_3008DAO;
	@Autowired
	private Search_PostOutDao pod;
	
	helpController hp = new helpController();
	Psg_CommonController commst = new Psg_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	@Autowired
	Search_UpdateOffDataDao UOD;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Psg_CommanDAO psg_com;
	PsgDashboardController das = new PsgDashboardController();
	
	///bisag v2 2508022(notification)
	NotificationController notification = new NotificationController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	/*--------------------------Page Open Post IN and OUT-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_OutUrl", method = RequestMethod.GET)
	public ModelAndView Posting_OutUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("Posting_OutUrl", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("getTypeofAppointementList", commst.getTypeofAppointementList());
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getstatusList", commst.getstatusList());
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getUnitNameList_Active_or_Inactive", allt.getUnitNameList_Active_or_Inactive(roleSusNo, sessionUserId));
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("Posting_OutTiles", "post_outCMD", new TB_POSTING_IN_OUT());
	}
	
	
/*------------------------------page open post in and out withcensus---------------------------------------*/
	
	@RequestMapping(value = "/admin/Posting_OutUrl_withcensus", method = RequestMethod.GET)
	public ModelAndView Posting_OutUrl_withcensus(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		//String roleid = sessionUserId.getAttribute("roleid").toString();
		//Boolean val = roledao.ScreenRedirect("Posting_OutUrl_withcensus", roleid);
		//Boolean val = roledao.ScreenRedirect("Posting_OutUrl_withcensus", sessionUserId.getAttribute("roleid").toString());
		//if (val == false) {
		//	return new ModelAndView("AccessTiles");
		//}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("getTypeofAppointementList", commst.getTypeofAppointementList());
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getstatusList", commst.getstatusList());
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getUnitNameList_Active_or_Inactive", allt.getUnitNameList_Active_or_Inactive(roleSusNo, sessionUserId));
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		return new ModelAndView("Posting_OutTileswithcensus", "post_outCMD", new TB_POSTING_IN_OUT());
	}
	
	/*--------------------------Save Post OUT Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_out_action", method = RequestMethod.POST)
	public @ResponseBody String posting_out_action(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request) throws ParseException {
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
		BigInteger comm_id = BigInteger.ZERO;
		String t_status_out =request.getParameter("t_status_out");
		
		 int t_stus_out = 0;
		 
	
		
	
		

		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		ArrayList<ArrayList<String>> list = pod.GetCommDataApprove(comm_id);
		/*if (list.size() > 0)
		{
			if (Integer.parseInt(list.get(0).get(18)) != Integer.parseInt("1"))
			{
				return "Individual Record is still in Pending for Approval.Pl Notify the  Approval to Approve all Pending Records in Update Offcr Form.";
			}
		}*/
		
		///bisag 08052023 v1 (after commissioning directly postout)
		/*int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}*/
		TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
		String to_sus_no = request.getParameter("to_sus_no");
		String out_auth = request.getParameter("out_auth");
		String personnel_no = request.getParameter("personnel_no");
		String out_auth_dt1 = request.getParameter("out_auth_dt");
		String unit_desc = request.getParameter("unit_description");
		String dt_of_sos1 = request.getParameter("dt_of_sos");
		if(unit_desc!=null) {
			unit_desc=unit_desc.trim();
		}
		int h_id = Integer.parseInt(request.getParameter("h_id"));
		
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}
		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			return "Personal No Should Contain Maximum 9 Character";
		}
		if (out_auth.equals("") || out_auth == "" || out_auth == null) {
			return "Please Enter Auth No.";
		}		
		if (!valid.isValidAuth(out_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}				
		if (out_auth_dt1 == null || out_auth_dt1.equals("null") || out_auth_dt1.equals("DD/MM/YYYY") || out_auth_dt1.equals("")) {
			return "Please Select Auth Date";
		}
		else if (!valid.isValidDate(out_auth_dt1)) {
			return valid.isValidDateMSG + " of Auth";
		}
		else {
			auth = format.parse(out_auth_dt1);
		}		
		if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
			return "Please Enter Unit SUS No";
		}	
		
		if (!valid.isOnlyAlphabetNumeric(to_sus_no)) {
		 	return valid.isOnlyAlphabetNumericMSG + "Unit Sus No";	
		}
		if (to_sus_no != "") {
			if (!valid.SusNoLength(to_sus_no)) {
				return valid.SusNoMSG;
			}
		}				
		if (dt_of_sos1 == null || dt_of_sos1.equals("null") || dt_of_sos1.equals("DD/MM/YYYY") || dt_of_sos1.equals("")) {
			return "Please Select SOS Date";
		} 
		else if (!valid.isValidDate(dt_of_sos1)) {
			return valid.isValidDateMSG + " of SOS";
		}	
		else {
			sos = format.parse(dt_of_sos1);
		}
		if (dt_of_sos1 != null) {
			// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 Date currentDate = new Date();
			 Date selectedDate = format.parse(dt_of_sos1);
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
		 if (t_status_out != null && !t_status_out.trim().equals("")) {

				t_stus_out = Integer.parseInt(t_status_out);

			}
		if (list.size() > 0)
		{
			if(sos!=null && list.get(0).get(6)!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_sos");
				String preSos=list.get(0).get(6).toString().substring(0,10);
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("-");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[0]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){
						return "Invalid Date of SOS";
					}
				}
				else if(newSosY<preSosY){
					return "Invalid Date of SOS";
				}
			}
			po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(2))));
			if (list.get(0).get(14) != null)
			{
				po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(14))));
			}
		}
		String rvalue = "";
		int id1 = h_id > 0 ? po.getId() : 0;
		try {
		Boolean v = pod.getpernoAlreadyExits(comm_id);
		if (v == true)
		{
			rvalue = "Data already exists.";
			return rvalue;
		}
		if (v == false) {
			if (id1 == 0) {
				String roleAccess = session.getAttribute("roleAccess").toString();

				ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommandpost_inout(to_sus_no);
				ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(to_sus_no);
				po.setTo_sus_no(to_sus_no);
				po.setOut_auth(out_auth);
				po.setOut_auth_dt(auth);
				po.setDt_of_sos(sos);
				po.setDt_of_tos(sos);
				po.setCreated_by(username);
				po.setCreated_date(new Date());
				//po.setCensus_id(census_id);
				po.setComm_id(comm_id);
				po.setFrom_sus_no(roleSusNo);
				po.setUnit_description(unit_desc);
				po.setT_status(t_stus_out);
				po.setStatus(0);
				if(orbatlist.size()>0) {
				po.setCmd_sus(orbatlist.get(0).get(1));
				po.setCorps_sus(orbatlist.get(0).get(2));
				po.setDiv_sus(orbatlist.get(0).get(3));
				po.setBde_sus(orbatlist.get(0).get(4));
				}
				if(Location_Trnlist.size()>0) {
				po.setLocation(Location_Trnlist.get(0).get(0));
				po.setTrn_type(Location_Trnlist.get(0).get(1));
				}

				sessionHQL.save(po);
				int id12 = (int) sessionHQL.save(po);
				tx.commit();
				rvalue = String.valueOf(po.getId());
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
	// end save post OUT
	/*--------------------------Save Post IN Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_in_action", method = RequestMethod.POST)
    public @ResponseBody String posting_in_action(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
            String personnel_no = request.getParameter("personnel_no");
            String from_sus_no = request.getParameter("from_sus_no");
            String t_status =request.getParameter("t_status");
          
            String rvalue = "";
            if (roleSusNo.equals(from_sus_no))
            {
                    rvalue = "Please check from SUS NO,it can't be same as like to SUS NO";
                    return rvalue;
            }
            int i_id = Integer.parseInt(request.getParameter("i_id"));
            /*
             * int census_id = 0; if (Integer.parseInt(request.getParameter("census_id")) !=
             * 0) { census_id = Integer.parseInt(request.getParameter("census_id")); }
             */
            BigInteger comm_id = BigInteger.ZERO;
            if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
                    comm_id = new BigInteger(request.getParameter("comm_id"));
            }
            Date tos = null;
            String dt_of_tos1 = request.getParameter("dt_of_tos");
            if (dt_of_tos1 != "") {
                    tos = format.parse(dt_of_tos1);
            }
            String in_auth = request.getParameter("in_auth");
            Date in_auth_dt1 = null;
            String in_auth_dt = request.getParameter("in_auth_dt");
          
            if (in_auth_dt != null || !in_auth_dt.equals("DD/MM/YYYY")) {
                    in_auth_dt1 = format.parse(in_auth_dt);
            }
            TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
            
            if (from_sus_no.equals("") || from_sus_no == "" || from_sus_no == null) {
                    return "Please Enter From SUS No";
            }
            if (!valid.isOnlyAlphabetNumeric(from_sus_no)) {
                     return valid.isOnlyAlphabetNumericMSG + " From Sus No";        
            }
            if (from_sus_no != "") {
                    if (!valid.SusNoLength(from_sus_no)) {
                            return  valid.SusNoMSG;
                    }
            }
            if (personnel_no == null || personnel_no.equals("")) {
                    return "Please Select Personel  No.";
            }                        
            if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
                    return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
            }
            if (personnel_no.length() < 7 || personnel_no.length() > 9) {
                    return "Personal No Should Contain Maximum 9 Character";
            }
            if (in_auth == null || in_auth.trim().equals("")) {
                    return "Please Enter Auth No";
            }
            if (!valid.isValidAuth(in_auth)) {
                    return valid.isValidAuthMSG + "Auth No";
            }
            if (!valid.isvalidLength(in_auth, valid.nameMax, valid.nameMin)) {
                    return "Auth No " + valid.isValidLengthMSG;
            }                                
            if (in_auth_dt == null || in_auth_dt.equals("null") || in_auth_dt.equals("DD/MM/YYYY") || in_auth_dt.equals("")) {
                    return "Please Select Auth Date";
            } 
            else if (!valid.isValidDate(in_auth_dt)) {
                    return valid.isValidDateMSG + " of Auth";
            }
            else {
                    in_auth_dt1 = format.parse(in_auth_dt);
            }                                                
            if (dt_of_tos1 == null || dt_of_tos1.equals("null") || dt_of_tos1.equals("DD/MM/YYYY") || dt_of_tos1.equals("")) {
                    return "Please Select TOS Date";
            } 
            else if (!valid.isValidDate(dt_of_tos1)) {
                    return valid.isValidDateMSG + " of TOS";
            }
            else {
                    tos = format.parse(dt_of_tos1);
            }
            if (dt_of_tos1 != null) {
      			// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      			 Date currentDate = new Date();
      			 Date selectedDate = format.parse(dt_of_tos1);
      		        if (selectedDate.after(currentDate)) {
      		            return "Future dates are not allowed";
      		    }		   
      		}
            int t_stus = 0;
            
            if (t_status != null && !t_status.trim().equals("")) {

			t_stus = Integer.parseInt(t_status);

		}
        	if (roleSusNo.equals("") || roleSusNo == "" || roleSusNo == null) {
    			return "Please Enter To Unit SUS No";
    		}
		
            ArrayList<ArrayList<String>> list = pod.GetCommDataApprove(comm_id);
            if (list.size() > 0)
            {
                    if(tos!=null && list.get(0).get(6)!=null) {
                            String regex = "^0+(?!$)";
                            String newTos=request.getParameter("dt_of_tos");
                            String preTos=list.get(0).get(6).toString().substring(0,10);
                           
                            String newTosArr[]=newTos.split("/");
                            String preTosArr[]=preTos.split("-");
                            int newTosM=Integer.parseInt(newTosArr[1].replaceAll(regex, ""));
                            int newTosY=Integer.parseInt(newTosArr[2]);
                            int preTosM=Integer.parseInt(preTosArr[1].replaceAll(regex, ""));
                            int preTosY=Integer.parseInt(preTosArr[0]);
                            
                            if(newTosY==preTosY){
                                    if(newTosM<=preTosM){
                                            return "Invalid Date of TOS";
                                    }
                            }
                            else if(newTosY<preTosY){
                                    return "Invalid Date of TOS";
                            }
                    }
                    po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(2))));
                    if (list.get(0).get(14) != null && !list.get(0).get(14).equals("null"))
                    {
                            po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(14))));
                    }
            }
            int id1 = i_id > 0 ? po.getId() : 0;
            try {
                    Boolean v = pod.getpernoAlreadyExits(comm_id);
                    if (v == true)
                    {
                            rvalue = "Data already exists.";
                            return rvalue;
                    }
                    if (v == false) {
                            if (id1 == 0) {
                                    String roleAccess = session.getAttribute("roleAccess").toString();

                                    ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommandpost_inout(roleSusNo);
                                    ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
                                    po.setCreated_by(username);
                                    po.setCreated_date(new Date());
                                    //po.setCensus_id(census_id);
                                    po.setComm_id(comm_id);
                                    if(orbatlist.size()>0) {
                                    po.setCmd_sus(orbatlist.get(0).get(1));
                                    po.setCorps_sus(orbatlist.get(0).get(2));
                                    po.setDiv_sus(orbatlist.get(0).get(3));
                                    po.setBde_sus(orbatlist.get(0).get(4));
                                    }
                                    if(Location_Trnlist.size()>0) {
                                    po.setLocation(Location_Trnlist.get(0).get(0));
                                    po.setTrn_type(Location_Trnlist.get(0).get(1));
                                    }
                                    po.setFrom_sus_no(from_sus_no);
                                    po.setTo_sus_no(roleSusNo);
                                    po.setDt_of_sos(tos);
                                    po.setIn_auth(in_auth);
                                    po.setIn_auth_dt(in_auth_dt1);
                                    po.setDt_of_tos(tos);
                                    po.setStatus(0);
                                   
                                    po.setT_status(t_stus);
                                    //po.setNotification_status(0);
                                    sessionHQL.save(po);
                                    int id12 = (int) sessionHQL.save(po);
                                    tx.commit();
                                    rvalue = String.valueOf(po.getId());
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
	// end save post IN
	/*---------------------------Search POST IN OUT page open url -----------------------------------------------------*/
	@RequestMapping(value = "/admin/Search_Posting_OutUrl")//, method = RequestMethod.GET
	public ModelAndView Search_Posting_OutUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no4", required = false) String personnel_no4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "to_sus_no4", required = false) String to_sus_no4,
			@RequestParam(value = "from_sus_no4", required = false) String from_sus_no4,
			@RequestParam(value = "type4", required = false) String type4,
			@RequestParam(value = "status4", required = false) String status4,
			@RequestParam(value = "service_category4", required = false) String service_category4,
			HttpServletRequest request, HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
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
		Mmap.put("personnel_no4", personnel_no4);
		Mmap.put("rank4", rank4);
		Mmap.put("to_sus_no4", to_sus_no4);
		Mmap.put("from_sus_no4", from_sus_no4);
		Mmap.put("type4", type4);
		Mmap.put("status4", status4);
		Mmap.put("service_category4", service_category4);
		
		
		if (m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("list", pod.search_postout(roleSusNo, "", "", "", "", "", roleType,"0",roleSusNo));
	     Mmap.put("list1", pod.search_postin(roleSusNo, "", "", "", "", "", roleType,"0",roleSusNo));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getTypeofRankList", commst.getTypeofRankList());
		return new ModelAndView("Search_Posting_Out_Tiles", "search_post_outCMD", new TB_POSTING_IN_OUT());
	}
	/*---------------------------Search postout -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_Out", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_Out(ModelMap Mmap, HttpSession sessionA,
			HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no", required = false) String personnel_no,
			@RequestParam(value = "rank", required = false) String rank,
			@RequestParam(value = "to_sus_no", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_no", required = false) String from_sus_no,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "cr_by", required = false) String cr_by,
		    @RequestParam(value = "cr_date", required = false) String cr_date) {
		
		String cat = request.getParameter("service_category");
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
		 
		 if(personnel_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					list_error.add("error");
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
		    		list.add(list_error);
		    		return list;					
				}
			  	    	  
			  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					list_error.add("error");
		    		list_error.add("Personal No Should Contain Maximum 9 Character");
		    		list.add(list_error);
		    		return list;
				}
	      }
		 
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
			if (cat.equals("1"))
		 list = pod.search_postout(from_sus_no, personnel_no, rank, to_sus_no,cr_by,cr_date, roleType,status,roleSusNo);
		else if (cat.equals("2"))
			list = pod.search_jcopostout(from_sus_no, personnel_no, rank, to_sus_no, roleType,status);
		return list;
	}
	// end search postout
	/*---------------------------Search postIN -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_In", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_In(ModelMap Mmap, HttpSession sessionA,
			HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no", required = false) String personnel_no,
			@RequestParam(value = "rank", required = false) String rank,
			@RequestParam(value = "to_sus_no", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_no", required = false) String from_sus_no,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "cr_by", required = false) String cr_by,
		    @RequestParam(value = "cr_date", required = false) String cr_date) {
		
		String cat = request.getParameter("service_category");
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
		 
		 if(personnel_no!="") {
			  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					list_error.add("error");
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG);
		    		list.add(list_error);
		    		return list;					
				}
			  	    	  
			  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					list_error.add("error");
		    		list_error.add("Personal No Should Contain Maximum 9 Character");
		    		list.add(list_error);
		    		return list;
				}
	      }
		 	
		
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 if (cat.equals("1"))
				list = pod.search_postin(to_sus_no, personnel_no, rank, from_sus_no,cr_by,cr_date,roleType,status,roleSusNo);
			else if (cat.equals("2"))
				list = pod.search_jcopostout(from_sus_no, personnel_no, rank, to_sus_no, roleType,status);
		return list;
	}
	// end search postout
	/*---------------------------Edit postout Page open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_Out_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Post_Out_Url(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "personnel_no6", required = false) String personnel_no6,
			@RequestParam(value = "rank6", required = false) String rank6,
			@RequestParam(value = "to_sus_no6", required = false) String to_sus_no6,
			@RequestParam(value = "from_sus_no6", required = false) String from_sus_no6,
			@RequestParam(value = "type6", required = false) String type6,
			@RequestParam(value = "status6", required = false) String status6,
			@RequestParam(value = "service_category6", required = false) String service_category6,
			HttpSession sessionEdit, HttpServletRequest request) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String cat = request.getParameter("service_categoryout");
		List<Map<String, Object>> list = pod.getPost_OutByid(Integer.parseInt(updateid), Integer.parseInt(cat));
		model.put("list", list);
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("service_category", cat);
		model.put("msg", msg);
		model.put("personnel_no6", personnel_no6);
		model.put("rank6", rank6);
		model.put("to_sus_no6", to_sus_no6);
		model.put("from_sus_no6", from_sus_no6);
		model.put("type6", type6);
		model.put("status6", status6);
		model.put("service_category6", service_category6);
		return new ModelAndView("Edit_Posting_Out_Tiles");
	}
	/*---------------------------Edit postin Page open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_In_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Post_In_Url(@ModelAttribute("inupdateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "personnel_no3", required = false) String personnel_no3,
			@RequestParam(value = "rank3", required = false) String rank3,
			@RequestParam(value = "to_sus_no3", required = false) String to_sus_no3,
			@RequestParam(value = "from_sus_no3", required = false) String from_sus_no3,
			@RequestParam(value = "type3", required = false) String type3,
			@RequestParam(value = "status3", required = false) String status3,
			@RequestParam(value = "service_category3", required = false) String service_category3,
			HttpSession sessionEdit, HttpServletRequest request) {
		String cat = request.getParameter("service_categoryin");
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = pod.getPost_InByid(Integer.parseInt(updateid), Integer.parseInt(cat));
		model.put("list", list);
		model.put("getTypeofAppointementList", commst.getTypeofAppointementList());
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("service_category", cat);
		model.put("msg", msg);
		model.put("personnel_no3", personnel_no3);
		model.put("rank3", rank3);
		model.put("to_sus_no3", to_sus_no3);
		model.put("from_sus_no3", from_sus_no3);
		model.put("type3", type3);
		model.put("status3", status3);
		model.put("service_category3", service_category3);
		return new ModelAndView("Edit_Posting_In_Tiles");
	}
	/*---------------------------Edit PostOut Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_posting_out_Action", method = RequestMethod.POST)
	public ModelAndView Edit_posting_out_Action( @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			/*model.put("personnel_no4", request.getParameter("inpersonnel_no3v"));
			model.put("rank4", request.getParameter("inrank3v"));
			model.put("to_sus_no4", request.getParameter("into_sus_no3v"));
			model.put("from_sus_no4", request.getParameter("infrom_sus_no_out3v"));
			model.put("type4", request.getParameter("intype3v"));
			model.put("status4", request.getParameter("instatus3v"));
			model.put("service_category4", request.getParameter("inservice_category3v"));*/
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Date date = new Date();
			String username = session.getAttribute("username").toString();			
			String to_sus_no = request.getParameter("to_sus_no");
			String out_auth = request.getParameter("out_auth");
			String unit_desc = request.getParameter("unit_description_out");
			if(unit_desc!=null) {
				unit_desc=unit_desc.trim();
			}
			Date out_auth_dt1 = null;
			String out_auth_dt = request.getParameter("out_auth_dt");
			Date dt_of_sos1 = null;
			String dt_of_sos = request.getParameter("dt_of_sos");
			String dt_tos_pre = request.getParameter("dt_tos_pre");

			TB_POSTING_IN_OUT tb = new TB_POSTING_IN_OUT();
			
			
			if(out_auth==null || out_auth.trim().equals("") ) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}			
			if (!valid.isValidAuth(out_auth)) {
				model.put("msg", "Please Valid Auth");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}								
			if(out_auth_dt==null || out_auth_dt.trim().equals("") || out_auth_dt.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			else if (!valid.isValidDate(out_auth_dt)) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			else {
				out_auth_dt1 = format.parse(out_auth_dt);
			}			
			if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
				model.put("msg", "Please Enter To Unit SUS No");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}		
			if (to_sus_no.equals(roleSusNo))
			{
				model.put("msg", "Please check To SUS NO,it can't be same as like FROM SUS NO");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}			
			if (to_sus_no != "") {
				if (!valid.SusNoLength(to_sus_no)) {
					model.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_Posting_OutUrl");
				}
			}
			if (!valid.isOnlyAlphabetNumericSpaceNot(to_sus_no)) {
				model.put("msg",valid.isOnlyAlphabetNumericSpaceNotMSG + " To Sus No ");
				return new ModelAndView("redirect:Search_Posting_OutUrl");				
			}
			if(dt_of_sos==null || dt_of_sos.trim().equals("") || dt_of_sos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of SOS");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			else if (!valid.isValidDate(dt_of_sos)) {
				model.put("msg", valid.isValidDateMSG + " of SOS");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			else {
				dt_of_sos1 = format.parse(dt_of_sos);
			}
			if (dt_of_sos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_sos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_OutUrl");
			    }		   
			}
			if(unit_desc!=null) {
				unit_desc=unit_desc.trim();
				if (!valid.isOnlyAlphabet(unit_desc)) {
					model.put("msg", "Unit Description  " + valid.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_Posting_OutUrl");
				}
			}
			
			if(dt_of_sos!=null && dt_tos_pre!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_sos");
				String preSos=dt_tos_pre;
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("/");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[2]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){
						model.put("msg", "Invalid Date of SOS");
						return new ModelAndView("redirect:Search_Posting_OutUrl");
					}
				}
				else if(newSosY<preSosY){
					model.put("msg", "Invalid Date of SOS");
					return new ModelAndView("redirect:Search_Posting_OutUrl");
				}
			}
			if (to_sus_no.equals(roleSusNo))
			{
				model.put("msg", "Please check To SUS NO,it can't be same as like FROM SUS NO");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";
			int service_category = Integer.parseInt(request.getParameter("service_category"));
			if (service_category == 1) {
				hql += "update TB_POSTING_IN_OUT";
			}
			
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
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	// end edit action
	/*---------------------------Edit PostIn Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_posting_in_Action", method = RequestMethod.POST)
	public ModelAndView Edit_posting_in_Action(HttpServletRequest request, ModelMap model, HttpSession session)
			throws ParseException {
		try {
			/*model.put("personnel_no4", request.getParameter("inpersonnel_no3v"));
			model.put("rank4", request.getParameter("inrank3v"));
			model.put("to_sus_no4", request.getParameter("into_sus_no3v"));
			model.put("from_sus_no4", request.getParameter("infrom_sus_no_out3v"));
			model.put("type4", request.getParameter("intype3v"));
			model.put("status4", request.getParameter("instatus3v"));
			model.put("service_category4", request.getParameter("inservice_category3v"));*/
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String from_sus_no = request.getParameter("from_sus_no");
			Date dt_of_tos1 = null;
			String dt_of_tos = request.getParameter("dt_of_tos");
			String dt_tos_pre = request.getParameter("dt_tos_pre");
			
			if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of TOS");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			if (!valid.isValidDate(dt_of_tos)) {
				model.put("msg", valid.isValidDateMSG + " of TOS");
				return new ModelAndView("redirect:Search_Posting_OutUrl");
			}
			if (dt_of_tos != "") {
				dt_of_tos1 = format.parse(dt_of_tos);
			}
			if (dt_of_tos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_tos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_OutUrl");
			    }		   
			}
			if(dt_of_tos!=null && dt_tos_pre!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_tos");
				String preSos=dt_tos_pre;
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("/");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[2]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){
						model.put("msg", "Invalid Date of TOS");
						return new ModelAndView("redirect:Search_Posting_OutUrl");
					}
				}
				else if(newSosY<preSosY){
					model.put("msg", "Invalid Date of TOS");
					return new ModelAndView("redirect:Search_Posting_OutUrl");
				}
			}
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";
			int service_category = Integer.parseInt(request.getParameter("service_category"));
			if (service_category == 1) {
				hql += "update TB_POSTING_IN_OUT";
			}
			
			hql += "  set  dt_of_tos=:dt_of_tos,dt_of_sos=:dt_of_sos,status=0 where id=:h_id";
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
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	// end edit action
	/*---------------------------//Approve postout  ----------------------------------------------------*/
	@RequestMapping(value = "/Approve_PostOUT", method = RequestMethod.POST)
	public  ModelAndView Approve_PostOUT(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idapp1", required = false) String comm_id,
			@RequestParam(value = "unit_sus_noapp1", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "service_categoryAppout", required = false) String service_categoryAppout,
			@RequestParam(value = "personnel_noapp1", required = false) String personnel_no,
			@RequestParam(value = "rankapp1", required = false) String rank,
			@RequestParam(value = "to_sus_noapp1", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp1", required = false) String from_sus_no,
			@RequestParam(value = "typeapp1", required = false) String type,
			@RequestParam(value = "statusapp1", required = false) String status,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
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
		String reciver_sus_no="";
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		if (service_categoryAppout.equals("1")) {
			hqlUpdate1 += "update TB_POSTING_IN_OUT ";
		}

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		if (service_categoryAppout.equals("1")) {
			String hqlUpdate2 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("unit_sus_no", unit_sus_no)
					.setBigInteger("id", new BigInteger(comm_id)).executeUpdate();
			List<Map<String, Object>> lpInout=UOD.getLastPostInOut(new BigInteger(comm_id));
			
			if(lpInout.size()>1) {
				String rmsg=UOD.setTenureDate(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
				
			}
		}	
		
		/*// when postinout then APPOINTMENT null ------------pranay 01.06
		
		   TB_POSTING_IN_OUT pin = (TB_POSTING_IN_OUT)sessionHQL.get(TB_POSTING_IN_OUT.class, id);      
		
		TB_CHANGE_OF_APPOINTMENT appoint =  new TB_CHANGE_OF_APPOINTMENT();
		appoint.setCreated_by(username);
		appoint.setCreated_date(new Date());
		appoint.setStatus(1);
		appoint.setDate_of_appointment(pin.getDt_of_tos());
		appoint.setCancel_status(-1);
		appoint.setComm_id(new BigInteger(comm_id));
		appoint.setAppointment(27107);		
		sessionHQL.save(appoint);*/
		
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
			List<Map<String, Object>> notit=UOD.getLastPostInnoti(new BigInteger(comm_id));
			
			String tos_date = notit.get(0).get("dt_of_tos").toString();
			String fromsus_no = notit.get(0).get("from_sus_no").toString();
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			List<Map<String, Object>> from_sus_unit=UOD.getunitdet(fromsus_no);
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
				   String per_no = notit.get(0).get("personnel_no").toString();
				   String rkin = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("name").toString();
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+rkin+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
              		}
				    List<UserLogin> userlistout = getPostIN_outuseridlist(fromsus_no);
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
					   String per_noout = notit.get(0).get("personnel_no").toString();
					   String rk = notit.get(0).get("rank").toString();
					   String name = notit.get(0).get("name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", "+rk+", "+name+" has been posted out to " +to_unit +" on " +tos_date ;
					    Boolean dout = notification.sendNotification(titleOUT, descriptionOut,user_idout, username);    
	              		}
				
			}
			else {
			if(roleSusNo.equals(unit_sus_no))
			{
			reciver_sus_no=from_sus_no;
				
			}
			if(roleSusNo.equals(from_sus_no))
			{
				reciver_sus_no=unit_sus_no;
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
				   String per_no = notit.get(0).get("personnel_no").toString();
				   String rk = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("name").toString();
		           String title = "POST IN" ;
		           
                   String description = ""+per_no+", " +rk+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
				         Boolean d = notification.sendNotification(title, description,user_id, username);
                		}
			  }
			} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		/*model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppout);*/
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	/*---------------------------//Approve postin  ----------------------------------------------------*/
	@RequestMapping(value = "/Approve_PostIN", method = RequestMethod.POST)
	public  ModelAndView Approve_PostIN(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idapp2", required = false) String comm_id,
			@RequestParam(value = "unit_sus_noapp2", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_noapp2", required = false) String personnel_no,
			@RequestParam(value = "service_categoryAppin", required = false) String service_categoryAppin,
			@RequestParam(value = "rankapp2", required = false) String rank,
			@RequestParam(value = "to_sus_noapp2", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp2", required = false) String from_sus_no,
			@RequestParam(value = "typeapp2", required = false) String type,
			@RequestParam(value = "statusapp2", required = false) String status,
			Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		
		
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		if (service_categoryAppin.equals("1")) {
			hqlUpdate1 += "update TB_POSTING_IN_OUT";
		}
		String reciver_sus_no="";
		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		if (service_categoryAppin.equals("1")) {
			String hqlUpdate2 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("unit_sus_no", unit_sus_no)
					.setBigInteger("id", new BigInteger(comm_id)).executeUpdate();
			List<Map<String, Object>> lpInout=UOD.getLastPostInOut(new BigInteger(comm_id));
			
			if(lpInout.size()>1) {
				String rmsg=UOD.setTenureDate(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}
		}
		
		
		
		/*// when postin then APPOINTMENT null ---- pranay 01.06
		
		   TB_POSTING_IN_OUT pin = (TB_POSTING_IN_OUT)sessionHQL.get(TB_POSTING_IN_OUT.class, id);      
		
		TB_CHANGE_OF_APPOINTMENT appoint =  new TB_CHANGE_OF_APPOINTMENT();
		appoint.setCreated_by(username);
		appoint.setCreated_date(new Date());
		appoint.setStatus(1);
		appoint.setDate_of_appointment(pin.getDt_of_tos());
		appoint.setCancel_status(-1);
		appoint.setComm_id(new BigInteger(comm_id));
		appoint.setAppointment(27107);		
		sessionHQL.save(appoint);*/
		
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
		
			///bisag v2 250822  (notification)
			
			List<Map<String, Object>> notit=UOD.getLastPostInnoti(new BigInteger(comm_id));
			
			String from_sus =notit.get(0).get("from_sus_no").toString();
		
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			String date_tos =notit.get(0).get("dt_of_tos").toString();
			
			List<Map<String, Object>> from_sus_unit=UOD.getunitdet(from_sus);
			String from_unit = from_sus_unit.get(0).get("unit_name").toString();
			List<Map<String, Object>> tosus_no_unit=UOD.getunitdet(tosus_no);
			String to_unit = tosus_no_unit.get(0).get("unit_name").toString();
			
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
				   String per_no = notit.get(0).get("personnel_no").toString();
				   String rkin = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("name").toString();
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+rkin+", "+namein+" has been posted in from " +from_unit +" on " +date_tos ;
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
					   String per_noout = notit.get(0).get("personnel_no").toString();
					   String rk = notit.get(0).get("rank").toString();
					   String name = notit.get(0).get("name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", "+rk+", "+name+" has been posted out to " +to_unit +" on " +date_tos ;
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
						String per_no = notit.get(0).get("personnel_no").toString();
						 String rk = notit.get(0).get("rank").toString();
						   String name = notit.get(0).get("name").toString();
			          
			           String title = "POST OUT" ;
			           
			           String description = ""+per_no+", " +rk+ ", " +name+" has been posted out to " +to_unit+" on "+date_tos ;
			         
			     //UOD.SendNotification(title,description,user_id);
			       	Boolean d = notification.sendNotification(title, description,user_id, username);
			     		}
			}
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		/*model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppin);*/
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	/*---------------------------//Approve postout  ----------------------------------------------------*/
	@RequestMapping(value = "/Reject_PostOUT", method = RequestMethod.POST)
	public  ModelAndView Reject_PostOUT(@ModelAttribute("idoutr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idoutr", required = false) String comm_id,
			@RequestParam(value = "unit_sus_nooutr", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "service_categoryAppoutr", required = false) String service_categoryAppout,
			@RequestParam(value = "personnel_nooutr", required = false) String personnel_no,
			@RequestParam(value = "rankoutr", required = false) String rank,
			@RequestParam(value = "to_sus_nooutr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_nooutr", required = false) String from_sus_no,
			@RequestParam(value = "typeoutr", required = false) String type,
			@RequestParam(value = "statusoutr", required = false) String status,
			@RequestParam(value = "rej_remarksoutr", required = false) String reject_remarks,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_OutUrl", roleid);
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
		if (service_categoryAppout.equals("1")) {
			hqlUpdate1 += "update TB_POSTING_IN_OUT ";
		}

		hqlUpdate1 += " set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";

		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3)
				.setString("reject_remarks", reject_remarks)
				.setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Rejected Successfully.");
		} else {
			liststr.add("Rejected Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppout);
		return new ModelAndView("Search_Posting_Out_Tiles");
	}
	/*---------------------------//Approve postin  ----------------------------------------------------*/
	@RequestMapping(value = "/Reject_PostIN", method = RequestMethod.POST)
	public  ModelAndView Reject_PostIN(@ModelAttribute("idinr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idinr", required = false) String comm_id,
			@RequestParam(value = "unit_sus_noinr", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_noinr", required = false) String personnel_no,
			@RequestParam(value = "service_categoryAppinr", required = false) String service_categoryAppin,
			@RequestParam(value = "rankinr", required = false) String rank,
			@RequestParam(value = "to_sus_noinr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noinr", required = false) String from_sus_no,
			@RequestParam(value = "typeinr", required = false) String type,
			@RequestParam(value = "statusinr", required = false) String status,
			@RequestParam(value = "rej_remarksinr", required = false) String reject_remarks,
			Authentication authentication) {
		
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		if (service_categoryAppin.equals("1")) {
			hqlUpdate1 += "update TB_POSTING_IN_OUT";
		}

		hqlUpdate1 += " set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3)
				.setString("reject_remarks", reject_remarks)
				.setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Rejected Successfully.");
		} else {
			liststr.add("Rejected Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppin);
		return new ModelAndView("Search_Posting_Out_Tiles");
	}
	/*---------------------------//delete postin  ----------------------------------------------------*/
	@RequestMapping(value = "/Delete_PostIN", method = RequestMethod.POST)
	public @ResponseBody String Delete_PostIN(
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model
		) {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		String service_category=request.getParameter("service_category");
		int id=Integer.parseInt(request.getParameter("id"));
		if (service_category.equals("1")) {
			hqlUpdate1 += "DELETE FROM TB_POSTING_IN_OUT";
		}

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
	// check Exist PERSONNEL No onchange
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPersNoCheckAlreadyExits", method = RequestMethod.POST)
	public @ResponseBody List<String> getPersNoCheckAlreadyExits(String personnel_no) {
		List<String> list = new ArrayList<String>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<TB_TRANS_PROPOSED_COMM_LETTER> users = null;
		try {
			Query q = session
					.createQuery("FROM TB_TRANS_PROPOSED_COMM_LETTER where Lower(personnel_no)=:personnel_no ");
			q.setParameter("personnel_no", personnel_no.toLowerCase());
			users = (List<TB_TRANS_PROPOSED_COMM_LETTER>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			list.add("true");
		} else {
			list.add("false");
		}
		return list;
	}
	// check Exist To Sus No onchange
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToSusNoCheckAlreadyExits", method = RequestMethod.POST)
	public @ResponseBody List<String> getToSusNoCheckAlreadyExits(String to_sus_no) {
		List<String> list = new ArrayList<String>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Miso_Orbat_Unt_Dtl> users = null;
		try {
			Query q = session.createQuery("FROM Miso_Orbat_Unt_Dtl where (sus_no)=:sus_no and status_sus_no='Active' ");
			q.setParameter("sus_no", to_sus_no);
			users = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			list.add("true");
		} else {
			list.add("false");
		}
		return list;
	}
	// check Exist From Sus No onchange
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFromSusNoCheckAlreadyExits", method = RequestMethod.POST)
	public @ResponseBody List<String> getFromSusNoCheckAlreadyExits(String from_sus_no) {
		List<String> list = new ArrayList<String>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Miso_Orbat_Unt_Dtl> users = null;
		try {
			Query q = session
					.createQuery("FROM Miso_Orbat_Unt_Dtl where Lower(sus_no)=:sus_no and status_sus_no='Active' ");
			q.setParameter("sus_no", from_sus_no.toLowerCase());
			users = (List<Miso_Orbat_Unt_Dtl>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			list.add("true");
		} else {
			list.add("false");
		}
		return list;
	}
	
	@RequestMapping(value = "/checkrole_hdr_status", method = RequestMethod.POST)
	public @ResponseBody  Map<String, String> checkrole_hdr_status(String comm_id) {
		List<TB_PSG_MISO_ROLE_HDR_STATUS> list = null;
		Map<String, String> data = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session
					.createQuery("FROM TB_PSG_MISO_ROLE_HDR_STATUS where comm_id=:comm_id ");
			q.setBigInteger("comm_id", new BigInteger(comm_id));
			list = (List<TB_PSG_MISO_ROLE_HDR_STATUS>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		
		if (list.size() > 0 ) {
			if(list.get(0).getCda_status()==0)
				data.put("error", "CDA Account Entry IN Pending State");
			else if(list.get(0).getField_ser_status()==0)
				data.put("error", "Field Service Entry In Pending State");
			else if(list.get(0).getMarital_dis_status()==0)
				data.put("error", "Marital discord Entry In Pending State");
			else if(list.get(0).getRe_emp_status()==0)
				data.put("error", "Re Employment Entry In Pending State");
			else if(list.get(0).getSeniority_status()==0)
				data.put("error", "Seniority Entry In Pending State");
			else if(list.get(0).getArmy_course_status()==0)
				data.put("error", "Army Course Entry In Pending State");
			/*else if(list.get(0).getLanguage_status()==0)
				data.put("error", "Language Entry In Pending State");
			else if(list.get(0).getForeign_language_status()==0)
				data.put("error", "Foreign Language Entry In Pending State");*/
			else 
				data.put("pass", "1");
		} 
		else {
			data.put("pass", "1");
		}
		return data;
	}
	
	
	@RequestMapping (value = "/admin/GETPosttenure_date", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GETtenure_date(ModelMap Mmap, HttpSession session,
			 BigInteger comm_id,HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = pod.getPosttenuredate(comm_id);
		return list;
	 }
	///////////////////////////Cancel History//////////////
	/*---------------------------//Cancel Reject postout  ----------------------------------------------------*/
	@RequestMapping(value = "/CancelRejectHistory_PostINOUT", method = RequestMethod.POST)
	public  ModelAndView CancelRejectHistory_PostINOUT(@ModelAttribute("idcrh") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idcrh", required = false) String comm_id,					
			@RequestParam(value = "service_categoryAppcrh", required = false) String service_categoryAppout,
			@RequestParam(value = "personnel_nocrh", required = false) String personnel_no,
			@RequestParam(value = "rankcrh", required = false) String rank,
			@RequestParam(value = "to_sus_nocrh", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_nocrh", required = false) String from_sus_no,
			@RequestParam(value = "typecrh", required = false) String type,
			@RequestParam(value = "statuscrh", required = false) String status,
			Authentication authentication) {
		
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		if (service_categoryAppout.equals("1")) {
			hqlUpdate1 += "update TB_POSTING_IN_OUT ";
		}
			
		hqlUpdate1 += " set cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date,cancel_by=:cancel_by,cancel_date=:cancel_date  where id=:id";
	try {
		Query query = sessionHQL.createQuery(hqlUpdate1);
		if(roleType.equals("DEO"))
				query.setInteger("cancel_status", 0).setString("cancel_by", username)
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
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppout);
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	/*---------------------------//Approve Cancel History  ----------------------------------------------------*/
	@RequestMapping(value = "/ApproveCancelHistory_PostINOUT", method = RequestMethod.POST)
	public  ModelAndView ApproveCancelHistory_PostINOUT(@ModelAttribute("idach") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "comm_idach", required = false) String comm_id,					
			@RequestParam(value = "service_categoryAppach", required = false) String service_categoryAppout,
			@RequestParam(value = "personnel_noach", required = false) String personnel_no,
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
		String tb_name="";
		String hqlUpdate1 = "";
		try {
		if (service_categoryAppout.equals("1")) {
			tb_name += " TB_POSTING_IN_OUT ";
		}
		
		if(!tb_name.equals("")) {
		hqlUpdate1 += "update "+tb_name+" set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";	
		Query query = sessionHQL.createQuery(hqlUpdate1);				
		int app=query.setString("modified_by", username)
		.setDate("modified_date", new Date()).setInteger("status", -1).setInteger("cancel_status", 1)
		.setInteger("id", id).executeUpdate();
		sessionHQL.flush();
		sessionHQL.clear();
		String hqlUpdate2="select id from "+tb_name+" where comm_id=:comm_id and status=1 order by id desc ";
		Query query2 = sessionHQL.createQuery(hqlUpdate2).setBigInteger("comm_id", new BigInteger(comm_id)).setMaxResults(1);
		Integer c = (Integer)  query2.uniqueResult();
	
		int app2=0;
		if(c!=null && c>0) {
			int chang_id=c.intValue();
		
			if (service_categoryAppout.equals("1")) {
				TB_POSTING_IN_OUT postin_out = (TB_POSTING_IN_OUT) sessionHQL.get(TB_POSTING_IN_OUT.class, chang_id);
				postin_out.setTenure_date(null);
				sessionHQL.update(postin_out);
				String hqlUpdate3 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
				app2 = sessionHQL.createQuery(hqlUpdate3)
						.setString("unit_sus_no", postin_out.getTo_sus_no())
						.setBigInteger("id", new BigInteger(comm_id)).executeUpdate();
			}
			else if (service_categoryAppout.equals("2")) {
			}
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
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		model.put("service_category4", service_categoryAppout);
		return new ModelAndView("redirect:Search_Posting_OutUrl");
	}
	
	///bisag v2 250822  (notification)

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
	  
	  @RequestMapping(value = "/GetCommDataApprove", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> GetCommDataApprove(BigInteger comm_id) {
       Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessionHQL.beginTransaction();
          ArrayList<ArrayList<String>> list = pod.GetCommDataApprove(comm_id);
             tx.commit();
           return list;
}
	  
	  @RequestMapping(value = "/getpersonnel_noListApprovedcomm", method = RequestMethod.POST)
	    public @ResponseBody List<String> getpersonnel_noListApprovedcomm(String personel_no, HttpSession sessionUserId,HttpServletRequest request) {

	            Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = sessionHQL.beginTransaction();
	            List<String> FinalList = new ArrayList<String>();
	    try{
	            String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
	            String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
	            String roleType = sessionUserId.getAttribute("roleType").toString();
	            Query q= null;
	            String rsus=request.getParameter("roleSus");
	            if( rsus!=null && !rsus.equals("")){
	            	roleSusNo=rsus;
	            }
	            
	            if(roleSusNo!=null && !roleSusNo.equals("")) {
	                q = sessionHQL.createQuery(
	                    "select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p " +
	                    "where substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS') " +
	                    "and p.unit_sus_no = :roleSusNo " +
	                    "and (p.status = '1' or p.status = '5') " +
	                    "and upper(p.personnel_no) like :personnel_no " +
	                    "order by p.personnel_no");
	                
	                q.setParameter("roleSusNo", roleSusNo);
	            }
	            else {
	                q = sessionHQL.createQuery(
	                    "select distinct p.personnel_no from TB_TRANS_PROPOSED_COMM_LETTER p " +
	                    "where substring(p.personnel_no, 1, 2) Not IN ('NR', 'NS') " +
	                    "and (p.status = '1' or p.status = '5') " +
	                    "and upper(p.personnel_no) like :personnel_no " +
	                    "order by p.personnel_no");
	            }

	            q.setParameter("personnel_no", personel_no.toUpperCase() + "%");
	            
	            
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
	                    
	   
	  }catch (Exception e) {
			tx.rollback();
		
		}finally {
			sessionHQL.close();
		}
		return FinalList;
	  }

	  /////old
	  
	  

/*--------------------------Save Post IN Action withcensus-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_in_action_withcensus", method = RequestMethod.POST)
	public @ResponseBody String posting_in_action_withcensus(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
		String personnel_no = request.getParameter("personnel_no");
		String from_sus_no = request.getParameter("from_sus_no");

		String rvalue = "";
		if (roleSusNo.equals(from_sus_no))
		{
			rvalue = "Please check from SUS NO,it can't be same as like to SUS NO";
			return rvalue;
		}
		int i_id = Integer.parseInt(request.getParameter("i_id"));
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != BigInteger.ZERO) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		Date tos = null;
		String dt_of_tos1 = request.getParameter("dt_of_tos");
		if (dt_of_tos1 != "") {
			tos = format.parse(dt_of_tos1);
		}
		String in_auth = request.getParameter("in_auth");
		Date in_auth_dt1 = null;
		String in_auth_dt = request.getParameter("in_auth_dt");
		if (in_auth_dt != null) {
			in_auth_dt1 = format.parse(in_auth_dt);
		}
		TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
		
		if (from_sus_no.equals("") || from_sus_no == "" || from_sus_no == null) {
			return "Please Enter From SUS No";
		}
		if (!valid.isOnlyAlphabetNumeric(from_sus_no)) {
		 	return valid.isOnlyAlphabetNumericMSG + " From Sus No";	
		}
		if (from_sus_no != "") {
			if (!valid.SusNoLength(from_sus_no)) {
				return  valid.SusNoMSG;
			}
		}
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}
		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			return "Personal No Should Contain Maximum 9 Character";
		}
		if (in_auth == null || in_auth.trim().equals("")) {
			return "Please Enter Auth No";
		}
		if (!valid.isValidAuth(in_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(in_auth, valid.nameMax, valid.nameMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}				
		if (in_auth_dt == null || in_auth_dt.equals("null") || in_auth_dt.equals("DD/MM/YYYY") || in_auth_dt.equals("")) {
			return "Please Select Auth Date";
		} 
		else if (!valid.isValidDate(in_auth_dt)) {
			return valid.isValidDateMSG + " of Auth";
		}
		else {
			in_auth_dt1 = format.parse(in_auth_dt);
		}						
		if (dt_of_tos1 == null || dt_of_tos1.equals("null") || dt_of_tos1.equals("DD/MM/YYYY") || dt_of_tos1.equals("")) {
			return "Please Select TOS Date";
		} 
		else if (!valid.isValidDate(dt_of_tos1)) {
			return valid.isValidDateMSG + " of TOS";
		}
		else {
			tos = format.parse(dt_of_tos1);
		}
		
		if (dt_of_tos1 != null) {
  		//	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  			 Date currentDate = new Date();
  			 Date selectedDate = format.parse(dt_of_tos1);
  		        if (selectedDate.after(currentDate)) {
  		            return "Future dates are not allowed";
  		    }		   
  		}

		ArrayList<ArrayList<String>> list = UOD.GetCensusDataApprove(comm_id);
		if (list.size() > 0)
		{
			if(tos!=null && list.get(0).get(6)!=null) {
				String regex = "^0+(?!$)";
				String newTos=request.getParameter("dt_of_tos");
				String preTos=list.get(0).get(6).toString().substring(0,10);
				String newTosArr[]=newTos.split("/");
				String preTosArr[]=preTos.split("-");
				int newTosM=Integer.parseInt(newTosArr[1].replaceAll(regex, ""));
				int newTosY=Integer.parseInt(newTosArr[2]);
				int preTosM=Integer.parseInt(preTosArr[1].replaceAll(regex, ""));
				int preTosY=Integer.parseInt(preTosArr[0]);
				if(newTosY==preTosY){
					if(newTosM<=preTosM){
						return "Invalid Date of TOS";
					}
				}
				else if(newTosY<preTosY){
					return "Invalid Date of TOS";
				}
			}
			po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(14))));
			if (list.get(0).get(15) != null && !list.get(0).get(15).equals("null"))
			{
				po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(15))));
			}
		}
		int id1 = i_id > 0 ? po.getId() : 0;
		try {
			Boolean v = pod.getpernoAlreadyExits(comm_id);
			if (v == true)
			{
				rvalue = "Data already exists.";
				return rvalue;
			}
			if (v == false) {
				if (id1 == 0) {
					String roleAccess = session.getAttribute("roleAccess").toString();

					ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommandpost_inout(roleSusNo);
					ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
					po.setCreated_by(username);
					po.setCreated_date(new Date());
					po.setCensus_id(census_id);
					po.setComm_id(comm_id);
					if(orbatlist.size()>0) {
					po.setCmd_sus(orbatlist.get(0).get(1));
					po.setCorps_sus(orbatlist.get(0).get(2));
					po.setDiv_sus(orbatlist.get(0).get(3));
					po.setBde_sus(orbatlist.get(0).get(4));
					}
					if(Location_Trnlist.size()>0) {
					po.setLocation(Location_Trnlist.get(0).get(0));
					po.setTrn_type(Location_Trnlist.get(0).get(1));
					}
					po.setFrom_sus_no(from_sus_no);
					po.setTo_sus_no(roleSusNo);
					po.setDt_of_sos(tos);
					po.setIn_auth(in_auth);
					po.setIn_auth_dt(in_auth_dt1);
					po.setDt_of_tos(tos);
					po.setStatus(0);
					//po.setNotification_status(0);
					sessionHQL.save(po);
					int id12 = (int) sessionHQL.save(po);
					tx.commit();
					rvalue = String.valueOf(po.getId());
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
	// end save post IN withcensus
	
	/*--------------------------Save Post OUT Action withcensus-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_out_action_withcensus", method = RequestMethod.POST)
	public @ResponseBody String posting_out_action_withcensus(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
			HttpServletRequest request) throws ParseException {
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
		BigInteger comm_id = BigInteger.ZERO;
		
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		ArrayList<ArrayList<String>> list = UOD.GetCensusDataApprove(comm_id);
		if (list.size() > 0)
		{
			if (Integer.parseInt(list.get(0).get(18)) != Integer.parseInt("1"))
			{
				return "Individual Record is still in Pending for Approval.Pl Notify the  Approval to Approve all Pending Records in Update Offcr Form.";
			}
		}
		int census_id = 0;
		if (Integer.parseInt(request.getParameter("census_id")) != 0) {
			census_id = Integer.parseInt(request.getParameter("census_id"));
		}
		TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
		String to_sus_no = request.getParameter("to_sus_no");
		String out_auth = request.getParameter("out_auth");
		String personnel_no = request.getParameter("personnel_no");
		String out_auth_dt1 = request.getParameter("out_auth_dt");
		String unit_desc = request.getParameter("unit_description");
		String dt_of_sos1 = request.getParameter("dt_of_sos");
		if(unit_desc!=null) {
			unit_desc=unit_desc.trim();
		}
		int h_id = Integer.parseInt(request.getParameter("h_id"));
		
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Personel  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}
		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			return "Personal No Should Contain Maximum 9 Character";
		}
		if (out_auth.equals("") || out_auth == "" || out_auth == null) {
			return "Please Enter Auth No.";
		}		
		if (!valid.isValidAuth(out_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(out_auth, valid.nameMax, valid.nameMin)) {
			return "Auth No " + valid.isValidLengthMSG;
		}				
		if (out_auth_dt1 == null || out_auth_dt1.equals("null") || out_auth_dt1.equals("DD/MM/YYYY") || out_auth_dt1.equals("")) {
			return "Please Select Auth Date";
		}
		else if (!valid.isValidDate(out_auth_dt1)) {
			return valid.isValidDateMSG + " of Auth";
		}
		else {
			auth = format.parse(out_auth_dt1);
		}		
		if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
			return "Please Enter Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(to_sus_no)) {
		 	return valid.isOnlyAlphabetNumericMSG + "Unit Sus No";	
		}
		if (to_sus_no != "") {
			if (!valid.SusNoLength(to_sus_no)) {
				return valid.SusNoMSG;
			}
		}				
		if (dt_of_sos1 == null || dt_of_sos1.equals("null") || dt_of_sos1.equals("DD/MM/YYYY") || dt_of_sos1.equals("")) {
			return "Please Select SOS Date";
		} 
		else if (!valid.isValidDate(dt_of_sos1)) {
			return valid.isValidDateMSG + " of SOS";
		}	
		else {
			sos = format.parse(dt_of_sos1);
		}	
		
		if (dt_of_sos1 != null) {
			// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 Date currentDate = new Date();
			 Date selectedDate = format.parse(dt_of_sos1);
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

		if (list.size() > 0)
		{
			if(sos!=null && list.get(0).get(6)!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_sos");
				String preSos=list.get(0).get(6).toString().substring(0,10);
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("-");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[0]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){
						return "Invalid Date of SOS";
					}
				}
				else if(newSosY<preSosY){
					return "Invalid Date of SOS";
				}
			}
			po.setRank(Integer.parseInt(String.valueOf(list.get(0).get(14))));
			if (list.get(0).get(15) != null)
			{
				po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get(15))));
			}
		}
		String rvalue = "";
		int id1 = h_id > 0 ? po.getId() : 0;
		try {
		Boolean v = pod.getpernoAlreadyExits(comm_id);
		if (v == true)
		{
			rvalue = "Data already exists.";
			return rvalue;
		}
		if (v == false) {
			if (id1 == 0) {
				String roleAccess = session.getAttribute("roleAccess").toString();

				ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommandpost_inout(to_sus_no);
				ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(to_sus_no);
				po.setTo_sus_no(to_sus_no);
				po.setOut_auth(out_auth);
				po.setOut_auth_dt(auth);
				po.setDt_of_sos(sos);
				po.setDt_of_tos(sos);
				po.setCreated_by(username);
				po.setCreated_date(new Date());
				po.setCensus_id(census_id);
				po.setComm_id(comm_id);
				po.setFrom_sus_no(roleSusNo);
				po.setUnit_description(unit_desc);
				po.setStatus(0);
				if(orbatlist.size()>0) {
				po.setCmd_sus(orbatlist.get(0).get(1));
				po.setCorps_sus(orbatlist.get(0).get(2));
				po.setDiv_sus(orbatlist.get(0).get(3));
				po.setBde_sus(orbatlist.get(0).get(4));
				}
				if(Location_Trnlist.size()>0) {
				po.setLocation(Location_Trnlist.get(0).get(0));
				po.setTrn_type(Location_Trnlist.get(0).get(1));
				}

				sessionHQL.save(po);
				int id12 = (int) sessionHQL.save(po);
				tx.commit();
				rvalue = String.valueOf(po.getId());
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
	// end save post OUT withcensus

	@RequestMapping(value = "/CheckPersonnelNoDataIncensus", method = RequestMethod.POST)

	public @ResponseBody List<String> CheckPersonnelNoDataIncensus(String comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q1 = sessionHQL.createQuery(

					"select c.comm_id FROM TB_CENSUS_DETAIL_Parent c where c.comm_id = :comm_id");

			q1.setParameter("comm_id",new BigInteger(comm_id));

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
	 //date-19/08/2023
		//---This method verifies whether census data has been filled or if any updates to the census data have occurred. If the census data is either incomplete or has been modified, the method returns 0.--/
		@RequestMapping(value = "/check_prsonalno_in_update", method = RequestMethod.POST)
		public @ResponseBody int check_prsonalno_in_update(String comm_id) {
		    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = sessionHQL.beginTransaction();

		    try {
		        Query q1 = sessionHQL.createQuery(
		                "select c.update_ofr_status FROM TB_CENSUS_DETAIL_Parent c where c.comm_id = :comm_id");
		        q1.setParameter("comm_id", new BigInteger(comm_id));

		        Integer result = (Integer) q1.uniqueResult(); 

		        tx.commit();

		        return result != null ? result : 1; 

		    } catch (RuntimeException e) {
		        return 0; 
		    } finally {
		        if (sessionHQL != null) {
		            sessionHQL.close();
		        }
		    }
		}

		//date-10/10/2023
		//---This method verifies whether comm data has been filled or if any updates to the comm data have occurred. If the comm data is either incomplete or has been modified, the method returns 0.--/
		@RequestMapping(value = "/check_prsonalno_in_comm_update", method = RequestMethod.POST)
		public @ResponseBody int check_prsonalno_in_comm_update(String comm_id) {
		    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = sessionHQL.beginTransaction();

		    try {
		        Query q1 = sessionHQL.createQuery(
		                "select c.update_comm_status FROM TB_TRANS_PROPOSED_COMM_LETTER c where c.id = :comm_id");
		        q1.setParameter("comm_id", new BigInteger(comm_id));

		        Integer result = (Integer) q1.uniqueResult(); 

		        tx.commit();

		        return result != null ? result : 1; 

		    } catch (RuntimeException e) {
		        return 0; 
		    } finally {
		        if (sessionHQL != null) {
		            sessionHQL.close();
		        }
		    }
		}
}
