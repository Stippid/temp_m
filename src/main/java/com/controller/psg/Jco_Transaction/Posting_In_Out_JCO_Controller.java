package com.controller.psg.Jco_Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Census.Census_Controller;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.Transaction.Posting_Out_Controller;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Transaction.Posting_In_Out_JCO_DAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.UserLogin;
import com.models.psg.Jco_Transaction.TB_POSTING_IN_OUT_JCO;
import com.models.psg.Jco_Transaction.TB_PSG_MISO_ROLE_HDR_STATUS_JCO;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_MISO_ROLE_HDR_STATUS;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Posting_In_Out_JCO_Controller {

	Census_Controller obj_cen = new Census_Controller();
	@Autowired
	private Report_3008DAO report_3008DAO;
	@Autowired
	private Search_PostOutDao pod;
	@Autowired
	private Posting_In_Out_JCO_DAO PJD;
	helpController hp = new helpController();
	@Autowired
	Psg_CommonController commst = new Psg_CommonController();
	@Autowired
	Psg_CommonController p_comm = new Psg_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat c = new AllMethodsControllerOrbat();
	@Autowired
	Search_UpdatedJcoOr_DataDao UOD;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Psg_CommanDAO psg_com;
	psg_jco_CommonController jcom = new psg_jco_CommonController();
	///bisag v2 2508022(notification)
		NotificationController notification = new NotificationController();
		@Autowired
		Search_UpdateOffDataDao unitd ;
		Posting_Out_Controller u_id= new Posting_Out_Controller();
	/*--------------------------page open post IN and OUT-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_In_Out_JCOUrl", method = RequestMethod.GET)
	public ModelAndView Posting_In_Out_JCOUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("Posting_In_Out_JCOUrl", sessionUserId.getAttribute("roleid").toString());
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
		return new ModelAndView("Posting_In_Out_JCOTiles");
	}
	/*--------------------------Save Post OUT Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_Out_JcoAction", method = RequestMethod.POST)
	public @ResponseBody String Posting_Out_JcoAction(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
		int jco_id = 0;
		
		
		List<Map<String, Object>> list = UOD.GetJcoOrCensusDataApprove(jco_id);
		if (list.size() > 0)
		{		
			String Val1 = String.valueOf(list.get(0).get("update_jco_status")) ;			
			if (Integer.parseInt(Val1) != Integer.parseInt("1"))
			{
				return "Individual Record is still in Pending for Approval.Pl Notify the Oc/Co of Unit to Approve all Pending Records in Update Offcr Form.";
			}
		}	
		TB_POSTING_IN_OUT_JCO POJ = new TB_POSTING_IN_OUT_JCO();
		String to_sus_no = request.getParameter("to_sus_no");
		String out_auth = request.getParameter("out_auth");
		String personnel_no = request.getParameter("personnel_no");
		String out_auth_dt1 = request.getParameter("out_auth_dt");
		String unit_desc = request.getParameter("unit_description");
		String dt_of_sos1 = request.getParameter("dt_of_sos");
		String t_status_out = request.getParameter("t_status_out");
		int t_stus_out=0;
		int h_id = Integer.parseInt(request.getParameter("h_id"));
	
		
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Select Army  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ";
		}
		if (personnel_no.length() < 2 || personnel_no.length() > 12) {
			return "Army No Should Contain Maximum 12 Character";
		}
		if (out_auth.equals("") || out_auth == "" || out_auth == null) {
			return "Please Enter Auth No.";
		}		
		if (!valid.isValidAuth(out_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(out_auth,valid.authorityMax,valid.authorityMin)) {
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
		 	return valid.isOnlyAlphabetNumericMSG + " Unit Sus No";	
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
		if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
			jco_id = Integer.parseInt(request.getParameter("jco_id"));
		}

		 if (t_status_out != null && !t_status_out.trim().equals("")) {

				 t_stus_out = Integer.parseInt(t_status_out);

			}
		if (list.size() > 0)
		{
			if(sos!=null && list.get(0).get("date_appointment")!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("dt_of_sos");
				String preSos=list.get(0).get("date_appointment").toString().substring(0,10);
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
			POJ.setRank(Integer.parseInt(String.valueOf(list.get(0).get("rank_id"))));
			if (list.get(0).get("appoint_id") != null)
			{				
				POJ.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get("appoint_id"))));
				
			}
		}
		String rvalue = "";
		int id1 = h_id > 0 ? POJ.getId() : 0;
	try {
		Boolean v = PJD.getJcopernoAlreadyExits(jco_id);
		if (v == true)
		{
			rvalue = "Data already exists.";
			return rvalue;
		}
		if (v == false) {
			if (id1 == 0) {
				String roleAccess = session.getAttribute("roleAccess").toString();
				ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(to_sus_no);
				ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(to_sus_no);
				POJ.setTo_sus_no(to_sus_no);
				POJ.setOut_auth(out_auth);
				POJ.setOut_auth_dt(auth);
				POJ.setDt_of_sos(sos);
				POJ.setDt_of_tos(sos);
				POJ.setCreated_by(username);
				POJ.setCreated_date(new Date());
				POJ.setJco_id(jco_id);
				POJ.setFrom_sus_no(roleSusNo);
				POJ.setUnit_description(unit_desc);
				POJ.setStatus(0);
				POJ.setT_status(t_stus_out);
				if(orbatlist.size()>0) {
				POJ.setCmd_sus(orbatlist.get(0).get(1));
				POJ.setCorps_sus(orbatlist.get(0).get(2));
				POJ.setDiv_sus(orbatlist.get(0).get(3));
				POJ.setBde_sus(orbatlist.get(0).get(4));
				}
				if(Location_Trnlist.size()>0) {
				POJ.setLocation(Location_Trnlist.get(0).get(0));
				POJ.setTrn_type(Location_Trnlist.get(0).get(1));
				}
				sessionHQL.save(POJ);
				int id12 = (int) sessionHQL.save(POJ);
				tx.commit();
				rvalue = String.valueOf(POJ.getId());
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
	// End save post OUT
	
/*--------------------------Save Post IN Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/Posting_In_JcoAction", method = RequestMethod.POST)
	public @ResponseBody String Posting_In_JcoAction(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
		int jco_id = 0;
		
		
		
		int i_id = Integer.parseInt(request.getParameter("i_id"));
		Date tos = null;
		String dt_of_tos1 = request.getParameter("dt_of_tos");
		
		String in_auth = request.getParameter("in_auth");
		Date in_auth_dt1 = null;
		String in_auth_dt = request.getParameter("in_auth_dt");
		String t_status =request.getParameter("t_status");
        int t_stus = 0;   
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
			return "Please Select Army  No.";
		}			
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ";
		}
		if (personnel_no.length() < 2 || personnel_no.length() > 12) {
			return "Army No Should Contain Maximum 12 Character";
		}
		if (in_auth == null || in_auth.trim().equals("")) {
			return "Please Enter Auth No";
		}
		if (!valid.isValidAuth(in_auth)) {
			return valid.isValidAuthMSG + "Auth No";
		}
		if (!valid.isvalidLength(in_auth,valid.authorityMax,valid.authorityMin)) {
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
   			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   			 Date currentDate = new Date();
   			 Date selectedDate = dateFormat.parse(dt_of_tos1);
   		        if (selectedDate.after(currentDate)) {
   		            return "Future dates are not allowed";
   		    }		   
   		}
		if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
			jco_id = Integer.parseInt(request.getParameter("jco_id"));
		}
		if (t_status != null && !t_status.trim().equals("")) {

			t_stus = Integer.parseInt(t_status);
		}
		if (roleSusNo.equals("") || roleSusNo == "" || roleSusNo == null) {
			return "Please Enter To Unit SUS No";
		}
		TB_POSTING_IN_OUT_JCO po = new TB_POSTING_IN_OUT_JCO();
		List<Map<String, Object>> list = UOD.GetJcoOrCensusDataApprove(jco_id);
		if (list.size() > 0)
		{
			if(tos!=null && list.get(0).get("date_appointment")!=null) {
				String regex = "^0+(?!$)";
				String newTos=request.getParameter("dt_of_tos");
				String preTos=list.get(0).get("date_appointment").toString().substring(0,10);
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
			po.setRank(Integer.parseInt(String.valueOf(list.get(0).get("rank_id"))));
			if (list.get(0).get("appoint_id") != null && !list.get(0).get("appoint_id").equals("null"))
			{
				po.setApp_name(Integer.parseInt(String.valueOf(list.get(0).get("appoint_id"))));
			}
		}
		int id1 = i_id > 0 ? po.getId() : 0;
	try {
		Boolean v = PJD.getJcopernoAlreadyExits(jco_id);
			if (v == true)
			{
				rvalue = "Data already exists.";
				return rvalue;
			}
			if (v == false) {
				if (id1 == 0) {
					String roleAccess = session.getAttribute("roleAccess").toString();
					ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(roleSusNo);
					ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(roleSusNo);
					po.setCreated_by(username);
					po.setCreated_date(new Date());
				
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
					po.setJco_id(jco_id);
					po.setIn_auth_dt(in_auth_dt1);
					po.setDt_of_tos(tos);
					po.setStatus(0);
					po.setT_status(t_stus);
					po.setNotification_status(0);
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
	// End save post IN
	
/*---------------------------Search POST IN OUT Page Open url -----------------------------------------------------*/
	@RequestMapping(value = "/admin/Search_Posting_In_Out_JCOUrl", method = RequestMethod.GET)
	public ModelAndView Search_Posting_In_Out_JCOUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no4", required = false) String personnel_no4,
			@RequestParam(value = "rank4", required = false) String rank4,
			@RequestParam(value = "to_sus_no4", required = false) String to_sus_no4,
			@RequestParam(value = "from_sus_no4", required = false) String from_sus_no4,
			@RequestParam(value = "type4", required = false) String type4,
			@RequestParam(value = "status4", required = false) String status4,
			
			HttpServletRequest request, HttpSession sessionUserId) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", roleid);
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
		Mmap.put("roleType", roleType);
		Mmap.put("msg", msg);
		Mmap.put("personnel_no4", personnel_no4);
		Mmap.put("rank4", rank4);
		Mmap.put("to_sus_no4", to_sus_no4);
		Mmap.put("from_sus_no4", from_sus_no4);
		Mmap.put("type4", type4);
		Mmap.put("status4", status4);
		if (c.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).size() > 0) {
			Mmap.put("unit_name", c.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));
		}
		Mmap.put("list", PJD.Search_Postout_JCO(roleSusNo, "", "", "", roleType,"0","","",roleSusNo));
		Mmap.put("list1", PJD.Search_PostIn_JCO(roleSusNo, "", "", "", roleType,"0","","",roleSusNo));
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("getPostINOutstatusList", commst.getPostINOutstatusList());
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		//Mmap.put("getTypeofRankList", commst.getTypeofRankList());
		Mmap.put("getRankjcoList", jcom.getRankjcoList());
		return new ModelAndView("Search_Posting_In_Out_JCOTiles");
	}
	
/*---------------------------Search Postout -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_Out_JCO", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_Out_JCO(ModelMap Mmap, HttpSession sessionA,
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
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
		    		list.add(list_error);
		    		return list;					
				}
			  	    	  
			  if (personnel_no.length() < 2 || personnel_no.length() > 12) {
					list_error.add("error");
		    		list_error.add("Army No Should Contain Maximum 12 Character");
		    		list.add(list_error);
		    		return list;
				}
	      }
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("cr_date_out", cr_date);
		Mmap.put("cr_by_out", cr_by);
			list = PJD.Search_Postout_JCO(from_sus_no, personnel_no, rank, to_sus_no, roleType,status,cr_by,cr_date,roleSusNo);
		return list;
	}
	//End search postout
	
/*---------------------------Search PostIN -----------------------------------------------------*/
	@RequestMapping(value = "/Search_Posting_In_JCO", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> Search_Posting_In_JCO(ModelMap Mmap, HttpSession sessionA,
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
		    		list_error.add(valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
		    		list.add(list_error);
		    		return list;					
				}
			  	    	  
			  if (personnel_no.length() < 2 || personnel_no.length() > 12) {
					list_error.add("error");
		    		list_error.add("Army No Should Contain Maximum 12 Character");
		    		list.add(list_error);
		    		return list;
				}
	      }
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 Mmap.put("cr_date_in", cr_date);
		Mmap.put("cr_by_in", cr_by);
			list = PJD.Search_PostIn_JCO(to_sus_no, personnel_no, rank, from_sus_no, roleType,status,cr_by,cr_date,roleSusNo);
		return list;
	}
	//End search postout
	
/*---------------------------Approve PostOut-------------------------------------*/
	@RequestMapping(value = "/Approve_PostOUT_JCO", method = RequestMethod.POST)
	public  ModelAndView Approve_PostOUT_JCO(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idapp1", required = false) String jco_id,
			@RequestParam(value = "unit_sus_noapp1", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_noapp1", required = false) String personnel_no,
			@RequestParam(value = "rankapp1", required = false) String rank,
			@RequestParam(value = "to_sus_noapp1", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp1", required = false) String from_sus_no,
			@RequestParam(value = "typeapp1", required = false) String type,
			@RequestParam(value = "statusapp1", required = false) String status,
			Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionA.getAttribute("roleid").toString());
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
		String reciver_sus_no="";
		
			hqlUpdate1 += "update TB_POSTING_IN_OUT_JCO ";

			hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
			
			TB_POSTING_IN_OUT_JCO post_out = (TB_POSTING_IN_OUT_JCO)sessionHQL.get(TB_POSTING_IN_OUT_JCO.class, id);
			
			String hqlUpdate2 = "update TB_CENSUS_JCO_OR_PARENT set unit_sus_no=:unit_sus_no ,date_of_tos=:date_of_tos where id=:id"; 
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("unit_sus_no", unit_sus_no).setDate("date_of_tos",  post_out.getDt_of_tos())
					.setInteger("id", Integer.parseInt(jco_id)).executeUpdate();
			List<Map<String, Object>> lpInout=PJD.getLastPostInOutJCO(Integer.parseInt(jco_id));
			if(lpInout.size()>1) {
				String rmsg=PJD.setTenureDateJCO(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}	
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
			List<Map<String, Object>> notit=UOD.getLastPostInnoti_jco(jco_id);
			
			String tos_date = notit.get(0).get("dt_of_tos").toString();
			String fromsus_no = notit.get(0).get("from_sus_no").toString();
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			List<Map<String, Object>> from_sus_unit=unitd.getunitdet(fromsus_no);
			String from_unit = from_sus_unit.get(0).get("unit_name").toString();
			List<Map<String, Object>> tosus_no_unit=unitd.getunitdet(tosus_no);
			String to_unit = tosus_no_unit.get(0).get("unit_name").toString();
			
			///bisag v2 250822  (notification)
			if(roleSusNo.equals(""))
			{
				 List<UserLogin> userlist = u_id.getPostIN_outuseridlist(tosus_no);
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
				   String per_no = notit.get(0).get("army_no").toString();
				   String rk = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("full_name").toString();
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+rk+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
              		}
				    List<UserLogin> userlistout = u_id.getPostIN_outuseridlist(fromsus_no);
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
					   String per_noout = notit.get(0).get("army_no").toString();
					   String rkout = notit.get(0).get("rank").toString();
					   String name = notit.get(0).get("full_name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", "+rkout+","+name+" has been posted out to " +to_unit +" on " +tos_date ;
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
			
			 List<UserLogin> userlist = u_id.getPostIN_outuseridlist(reciver_sus_no);
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
				   String per_no = notit.get(0).get("army_no").toString();
				   String rk = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("full_name").toString();
		           String title = "POST IN" ;
		           
                   String description = ""+per_no+", "+rk+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
				         Boolean d = notification.sendNotification(title, description,user_id, username);
                		}
			  }
			
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	/*---------------------------//Approve PostIn  ----------------------------------------------------*/
	@RequestMapping(value = "/Approve_PostIN_JCO", method = RequestMethod.POST)
	public  ModelAndView Approve_PostIN_JCO(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idapp2", required = false) String jco_id,
			@RequestParam(value = "unit_sus_noapp2", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_noapp2", required = false) String personnel_no,
			@RequestParam(value = "rankapp2", required = false) String rank,
			@RequestParam(value = "to_sus_noapp2", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noapp2", required = false) String from_sus_no,
			@RequestParam(value = "typeapp2", required = false) String type,
			@RequestParam(value = "statusapp2", required = false) String status,
			Authentication authentication) {
		
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionA.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String reciver_sus_no="";
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";

			hqlUpdate1 += "update TB_POSTING_IN_OUT_JCO";

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_date", new Date())
				.setInteger("id", id).executeUpdate();
		
		TB_POSTING_IN_OUT_JCO post_out = (TB_POSTING_IN_OUT_JCO)sessionHQL.get(TB_POSTING_IN_OUT_JCO.class, id);
		
			String hqlUpdate2 = "update TB_CENSUS_JCO_OR_PARENT set unit_sus_no=:unit_sus_no  ,date_of_tos=:date_of_tos where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("unit_sus_no", unit_sus_no).setDate("date_of_tos", post_out.getDt_of_tos())
					.setInteger("id", Integer.parseInt(jco_id)).executeUpdate();
			List<Map<String, Object>> lpInout=PJD.getLastPostInOutJCO(Integer.parseInt(jco_id));
			if(lpInout.size()>1) {
				String rmsg=PJD.setTenureDateJCO(Integer.parseInt(lpInout.get(1).get("id").toString()), id);
			}	
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
///bisag v2 250822  (notification)
			
			List<Map<String, Object>> notit = UOD.getLastPostInnoti_jco(jco_id);;
			
			String from_sus =notit.get(0).get("from_sus_no").toString();
		
			String tosus_no = notit.get(0).get("to_sus_no").toString();
			String date_tos =notit.get(0).get("dt_of_tos").toString();
			
			List<Map<String, Object>> from_sus_unit = unitd.getunitdet(from_sus);
			String from_unit = from_sus_unit.get(0).get("unit_name").toString();
			List<Map<String, Object>> tosus_no_unit = unitd.getunitdet(tosus_no);
			String to_unit = tosus_no_unit.get(0).get("unit_name").toString();
			
			if(roleSusNo.equals(""))
			{
				 List<UserLogin> userlist = u_id.getPostIN_outuseridlist(tosus_no);
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
				   String per_no = notit.get(0).get("army_no").toString();
				   String rk = notit.get(0).get("rank").toString();
				   String namein = notit.get(0).get("full_name").toString();
		           String title = "POST IN" ;
                 String description = ""+per_no+", "+rk+", "+namein+ " has been posted in from " +from_unit +" on " +date_tos ;
				    Boolean d = notification.sendNotification(title, description,user_id, username);
              		}
				    List<UserLogin> userlistout = u_id.getPostIN_outuseridlist(from_sus);
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
					   String per_noout = notit.get(0).get("army_no").toString();
					   String rkout = notit.get(0).get("rank").toString();
					   String name = notit.get(0).get("full_name").toString();
			           String titleOUT = "POST OUT" ;
	                 String descriptionOut = ""+per_noout+", " +rkout+", "+name+" has been posted out to " +to_unit +" on " +date_tos ;
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
			
			List<UserLogin> userlist = u_id.getPostIN_outuseridlist(reciver_sus_no);
					
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
						String per_no = notit.get(0).get("army_no").toString();
						String rk = notit.get(0).get("rank").toString();
						String name = notit.get(0).get("full_name").toString();
			          
			           String title = "POST OUT" ;
			           
			           String description = ""+per_no+", "+rk+", "+name+" has been posted out to " +to_unit+" on "+date_tos ;
			           
			     //UOD.SendNotification(title,description,user_id);
			       	Boolean d = notification.sendNotification(title, description,user_id, username);
			     		}
			}
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("personnel_no4", personnel_no);
		model.put("rank4", rank);
		model.put("to_sus_no4",to_sus_no);
		model.put("from_sus_no4",from_sus_no);
		model.put("type4", type);
		model.put("status4",status);
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}		
	
	/*------------------------------Approve Cancel History----------------------------------------------*/
	@RequestMapping(value = "/ApproveCancelHistory_PostINOUT_JCO", method = RequestMethod.POST)
	public  ModelAndView ApproveCancelHistory_PostINOUT_JCO(@ModelAttribute("idach") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idach", required = false) String jco_id,					
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
			tb_name += " TB_POSTING_IN_OUT_JCO ";

		if(!tb_name.equals("")) {
		hqlUpdate1 += "update "+tb_name+" set status=:status,cancel_status=:cancel_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";	
		Query query = sessionHQL.createQuery(hqlUpdate1);				
		int app=query.setString("modified_by", username)
		.setDate("modified_date", new Date()).setInteger("status", -1).setInteger("cancel_status", 1)
		.setInteger("id", id).executeUpdate();
		sessionHQL.flush();
		sessionHQL.clear();
		String hqlUpdate2="select id from "+tb_name+" where jco_id=:jco_id and status=1 order by id desc ";
		Query query2 = sessionHQL.createQuery(hqlUpdate2).setInteger("jco_id", Integer.parseInt(jco_id)).setMaxResults(1);
		Integer c = (Integer)  query2.uniqueResult();
		
		int app2=0;
		if(c!=null && c>0) {
			int chang_id=c.intValue();
			TB_POSTING_IN_OUT_JCO postin_out = (TB_POSTING_IN_OUT_JCO) sessionHQL.get(TB_POSTING_IN_OUT_JCO.class, chang_id);
				postin_out.setTenure_date(null);
				sessionHQL.update(postin_out);
				String hqlUpdate3 = "update TB_CENSUS_JCO_OR_PARENT set unit_sus_no=:unit_sus_no, date_of_tos=:date_of_tos where id=:id";
				app2 = sessionHQL.createQuery(hqlUpdate3)
						.setString("unit_sus_no", postin_out.getTo_sus_no())
						.setDate("date_of_tos", postin_out.getDt_of_tos())
						.setInteger("id", Integer.parseInt(jco_id)).executeUpdate();			
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	
/*-------------------------------Reject Cancel Postout-----------------------------------------------*/
	@RequestMapping(value = "/CancelRejectHistory_PostINOUT_JCO", method = RequestMethod.POST)
	public  ModelAndView CancelRejectHistory_PostINOUT_JCO(@ModelAttribute("idcrh") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idcrh", required = false) String jco_id,					
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
			hqlUpdate1 += "update TB_POSTING_IN_OUT_JCO ";
			
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	
/*------------------------------Delete PostIn-----------------------------------------*/
	@RequestMapping(value = "/Delete_PostIN_JCO", method = RequestMethod.POST)
	public @ResponseBody String Delete_PostIN_JCO(HttpServletRequest request,HttpSession sessionA, ModelMap model) {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		int id=Integer.parseInt(request.getParameter("id"));
			hqlUpdate1 += "DELETE FROM TB_POSTING_IN_OUT_JCO";
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
	@RequestMapping(value = "/Reject_PostOUT_JCO", method = RequestMethod.POST)
	public  ModelAndView Reject_PostOUT_JCO(@ModelAttribute("idoutr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idoutr", required = false) String jco_id,
			@RequestParam(value = "unit_sus_nooutr", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_nooutr", required = false) String personnel_no,
			@RequestParam(value = "rankoutr", required = false) String rank,
			@RequestParam(value = "to_sus_nooutr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_nooutr", required = false) String from_sus_no,
			@RequestParam(value = "typeoutr", required = false) String type,
			@RequestParam(value = "statusoutr", required = false) String status,
			@RequestParam(value = "rej_remarksoutjco", required = false) String reject_remarks,
			Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionA.getAttribute("roleid").toString());
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
			hqlUpdate1 += "update TB_POSTING_IN_OUT_JCO ";

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setString("reject_remarks", reject_remarks)
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	
/*-------------------------------Reject PostIn------------------------------------------------*/
	@RequestMapping(value = "/Reject_PostIN_JCO", method = RequestMethod.POST)
	public  ModelAndView Reject_PostIN_JCO(@ModelAttribute("idinr") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "jco_idinr", required = false) String jco_id,
			@RequestParam(value = "unit_sus_noinr", required = false) String unit_sus_no,
			@RequestParam(value = "modified_by", required = false) String modified_by,
			@RequestParam(value = "modified_date", required = false) String modified_date,
			@RequestParam(value = "personnel_noinr", required = false) String personnel_no,
			@RequestParam(value = "rankinr", required = false) String rank,
			@RequestParam(value = "to_sus_noinr", required = false) String to_sus_no,
			@RequestParam(value = "from_sus_noinr", required = false) String from_sus_no,
			@RequestParam(value = "typeinr", required = false) String type,
			@RequestParam(value = "statusinr", required = false) String status,
			@RequestParam(value = "rej_remarksinjco", required = false) String reject_remarks,
			Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionA.getAttribute("roleid").toString());
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
			hqlUpdate1 += "update TB_POSTING_IN_OUT_JCO";

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks where id=:id";
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setString("reject_remarks", reject_remarks)
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	@RequestMapping (value = "/admin/GETPostTenure_Date_Jco", method = RequestMethod.POST)
	 public @ResponseBody ArrayList<ArrayList<String>> GETPostTenure_Date_Jco(ModelMap Mmap, HttpSession session,
			 int jco_id,HttpServletRequest request) {
		ArrayList<ArrayList<String>> list = PJD.getPostTenureDateJCO(jco_id);
		return list;
	 }	
	@RequestMapping(value = "/CheckRole_Hdr_Status_Jco", method = RequestMethod.POST)
	public @ResponseBody  Map<String, String> CheckRole_Hdr_Status_Jco(String jco_id) {
		List<TB_PSG_MISO_ROLE_HDR_STATUS_JCO> list = null;
		Map<String, String> data = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session
					.createQuery("FROM TB_PSG_MISO_ROLE_HDR_STATUS_JCO where jco_id=:jco_id ");
			q.setInteger("jco_id", Integer.parseInt(jco_id));
			list = (List<TB_PSG_MISO_ROLE_HDR_STATUS_JCO>) q.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (list.size() > 0) {
			if(list.get(0).getField_ser_status()==0)
				data.put("error", "Field Service Entry In Pending State");
			else if(list.get(0).getMarital_dis_status()==0)
				data.put("error", "Marital discord Entry In Pending State");
			else if(list.get(0).getRe_call_status()==0)
				data.put("error", "Re Employment Entry In Pending State");
			else 
				data.put("pass", "1");
		} else {
			data.put("pass", "1");
		}
		return data;
	}
	
/*---------------------------Edit PostOut Page Open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_Out_JCOUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Post_Out_JCOUrl(@ModelAttribute("updateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "personnel_no6", required = false) String personnel_no6,
			@RequestParam(value = "rank6", required = false) String rank6,
			@RequestParam(value = "to_sus_no6", required = false) String to_sus_no6,
			@RequestParam(value = "from_sus_no6", required = false) String from_sus_no6,
			@RequestParam(value = "type6", required = false) String type6,
			@RequestParam(value = "status6", required = false) String status6,
			HttpSession sessionEdit, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionEdit.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = PJD.GetPost_OutByid(Integer.parseInt(updateid));
		model.put("list", list);
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("msg", msg);
		model.put("personnel_no6", personnel_no6);
		model.put("rank6", rank6);
		model.put("to_sus_no6", to_sus_no6);
		model.put("from_sus_no6", from_sus_no6);
		model.put("type6", type6);
		model.put("status6", status6);
		return new ModelAndView("Edit_Posting_Out_JCOTiles");
	}
	
/*---------------------------Edit PostOut Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_Posting_Out_JCOAction", method = RequestMethod.POST)
	public ModelAndView Edit_Posting_Out_JCOAction(HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			model.put("personnel_no4", request.getParameter("inpersonnel_no3v"));
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
			
			TB_POSTING_IN_OUT_JCO tb = new TB_POSTING_IN_OUT_JCO();
			String to_sus_no = request.getParameter("to_sus_no");
			String out_auth = request.getParameter("out_auth");
			String unit_desc = request.getParameter("unit_description_out");
			String dt_tos_pre = request.getParameter("dt_tos_pre");
			Date out_auth_dt1 = null;
			String out_auth_dt = request.getParameter("out_auth_dt");
			Date dt_of_sos1 = null;
			String dt_of_sos = request.getParameter("dt_of_sos");
			if(out_auth==null || out_auth.trim().equals("") ) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}			
			if (!valid.isValidAuth(out_auth)) {
				model.put("msg", "Please Valid Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			if (!valid.isvalidLength(out_auth, valid.authorityMax,valid.authorityMin)) {
				model.put("msg", "Please Enter Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}								
			if(out_auth_dt==null || out_auth_dt.trim().equals("") || out_auth_dt.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else if (!valid.isValidDate(out_auth_dt)) {
				model.put("msg", "Please Enter Date of Auth");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else {
				out_auth_dt1 = format.parse(out_auth_dt);
			}			
			if (to_sus_no.equals("") || to_sus_no == "" || to_sus_no == null) {
				model.put("msg", "Please Enter To Unit SUS No");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}		
			if (to_sus_no.equals(roleSusNo))
			{
				model.put("msg", "Please check To SUS NO,it can't be same as like FROM SUS NO");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}			
			if (to_sus_no != "") {
				if (!valid.SusNoLength(to_sus_no)) {
					model.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
				}
			}
			if (!valid.isOnlyAlphabetNumericSpaceNot(to_sus_no)) {
				model.put("msg",valid.isOnlyAlphabetNumericSpaceNotMSG + " To Sus No ");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");				
			}
			if(dt_of_sos==null || dt_of_sos.trim().equals("") || dt_of_sos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of SOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else if (!valid.isValidDate(dt_of_sos)) {
				model.put("msg", valid.isValidDateMSG + " of SOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else {
				dt_of_sos1 = format.parse(dt_of_sos);
			}
			if (dt_of_sos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_sos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			    }		   
			}
			if(unit_desc!=null) {
				unit_desc=unit_desc.trim();
				if (!valid.isOnlyAlphabet(unit_desc)) {
					model.put("msg", "Unit Description  " + valid.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
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
						return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
					}
				}
				else if(newSosY<preSosY){
					model.put("msg", "Invalid Date of SOS");
					return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
				}
			}
			if (to_sus_no.equals(roleSusNo))
			{
				model.put("msg", "Please check To SUS NO,it can't be same as like FROM SUS NO");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";

				hql += "update TB_POSTING_IN_OUT_JCO";
			
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	// End Edit Post Out Action
	
/*---------------------------Edit PostIn Page Open-----------------------------------------------------*/
	@RequestMapping(value = "/Edit_Post_In_JCOUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Post_In_JCOUrl(@ModelAttribute("inupdateid") String updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "personnel_no3", required = false) String personnel_no3,
			@RequestParam(value = "rank3", required = false) String rank3,
			@RequestParam(value = "to_sus_no3", required = false) String to_sus_no3,
			@RequestParam(value = "from_sus_no3", required = false) String from_sus_no3,
			@RequestParam(value = "type3", required = false) String type3,
			@RequestParam(value = "status3", required = false) String status3,
			HttpSession sessionEdit, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", sessionEdit.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<Map<String, Object>> list = PJD.GetPost_InByid(Integer.parseInt(updateid));
		model.put("list", list);
		model.put("getTypeofAppointementList", commst.getTypeofAppointementList());
		model.put("updateid", updateid);
		model.put("size", list.size());
		model.put("msg", msg);
		model.put("personnel_no3", personnel_no3);
		model.put("rank3", rank3);
		model.put("to_sus_no3", to_sus_no3);
		model.put("from_sus_no3", from_sus_no3);
		model.put("type3", type3);
		model.put("status3", status3);
		return new ModelAndView("Edit_Posting_In_JCOTiles");
	}
	
/*---------------------------Edit PostIn Action----------------------------------------------------*/
	@RequestMapping(value = "/admin/Edit_Posting_In_JCOAction", method = RequestMethod.POST)
	public ModelAndView Edit_Posting_In_JCOAction(HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg)
			throws ParseException {
		Boolean val = roledao.ScreenRedirect("Search_Posting_In_Out_JCOUrl", session.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		try {
			model.put("personnel_no4", request.getParameter("inpersonnel_no3v"));
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
			
			
			if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {
				model.put("msg", "Please Enter Date of TOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else if (!valid.isValidDate(dt_of_tos)) {
				model.put("msg", valid.isValidDateMSG + " of TOS");
				return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
			}
			else {
				dt_of_tos1 = format.parse(dt_of_tos);
			}
			if (dt_of_tos1 != null) {				
				 Date currentDate = new Date();
				 Date selectedDate = dt_of_tos1;
			        if (selectedDate.after(currentDate)) {
			        	model.put("msg","Future dates are not allowed");
			        	return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
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
						return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
					}
				}
				else if(newSosY<preSosY){
					model.put("msg", "Invalid Date of TOS");
					return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
				}
			}
			
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";
				hql += "update TB_POSTING_IN_OUT_JCO";
			
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
		return new ModelAndView("redirect:Search_Posting_In_Out_JCOUrl");
	}
	// End Edit PostIn Action
}
