package com.controller.psg.update_3008;
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
public class Posting_Out_Controller_3008 {
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
	@RequestMapping(value = "/admin/Posting_OutUrl_3008", method = RequestMethod.GET)
	public ModelAndView Posting_OutUrl_3008(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("Posting_OutUrl_3008", sessionUserId.getAttribute("roleid").toString());
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
		return new ModelAndView("Posting_Out_3009_Tiles", "post_outCMD", new TB_POSTING_IN_OUT());
	}
	
	

	/*--------------------------Save Post OUT Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_out_action_3008", method = RequestMethod.POST)
	public @ResponseBody String posting_out_action_3008(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
		String reciver_sus_no="";
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
		
		 
		
		List<String> liststr = new ArrayList<String>();
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
				po.setStatus(1);
				po.setModified_by(username);
				po.setModified_date(new Date());
				sessionHQL.save(po);
				int id = (int) sessionHQL.save(po);				
				
				
			String hqlUpdate2 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
				int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("unit_sus_no", to_sus_no)
					.setBigInteger("id",comm_id).executeUpdate();
					List<Map<String, Object>> lpInout=UOD.getLastPostInOut(comm_id);
					
						String rmsg=UOD.setTenureDate_3008(Integer.parseInt(lpInout.get(0).get("id").toString()), sos);						
					
		
						
				tx.commit();
				rvalue = String.valueOf(po.getId());
				
				if (id > 0) {
					liststr.add("Approved Successfully.");
					List<Map<String, Object>> notit=UOD.getLastPostInnoti(comm_id);
					
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
			                 String descriptionOut = ""+per_noout+", "+rk+", " +name+" has been posted out to " +to_unit +" on " +tos_date ;
							    Boolean dout = notification.sendNotification(titleOUT, descriptionOut,user_idout, username);    
			              		}
						
					}
					else {
					if(roleSusNo.equals(to_sus_no))
					{
					reciver_sus_no=fromsus_no;
						
					}
					if(roleSusNo.equals(fromsus_no))
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
						   String namein = notit.get(0).get("name").toString();
				           String title = "POST IN" ;
				           
		                   String description = ""+per_no+","+rk+", "+namein+" has been posted in from " +from_unit +" on " +tos_date ;
						         Boolean d = notification.sendNotification(title, description,user_id, username);
		                		}
					  }
					} else {
					liststr.add("Approved Not Successfully.");
				}
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
//	/*--------------------------Save Post IN Action-----------------------------------------------------*/
	@RequestMapping(value = "/admin/posting_in_action_3008", method = RequestMethod.POST)
    public @ResponseBody String posting_in_action_3008(ModelMap Mmap, HttpSession session, HttpSession sessionUserId,
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
            List<String> liststr = new ArrayList<String>();
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
                                    po.setStatus(1);
                                    po.setModified_by(username);
                                    po.setModified_date(new Date());                                   
                                   
                                    po.setT_status(t_stus);
                                    //po.setNotification_status(0);
                                    sessionHQL.save(po);
                                    int id12 = (int) sessionHQL.save(po);                                    
                                	String reciver_sus_no="";
                                    String hqlUpdate2 = "update TB_TRANS_PROPOSED_COMM_LETTER set unit_sus_no=:unit_sus_no where id=:id";
                    				int app2 = sessionHQL.createQuery(hqlUpdate2)
                    					.setString("unit_sus_no", roleSusNo)
                    					.setBigInteger("id",comm_id).executeUpdate();
                    					List<Map<String, Object>> lpInout=UOD.getLastPostInOut(comm_id);                    					
                    				
                    						String rmsg=UOD.setTenureDate_3008(Integer.parseInt(lpInout.get(0).get("id").toString()), tos);
                    						
                    						
                    	
                    						
                    						
                    			   tx.commit();                                    
                    			   if (id12 > 0) {
                    					liststr.add("Approved Successfully.");
                    				
                    					///bisag v2 250822  (notification)
                    					
                    					List<Map<String, Object>> notit=UOD.getLastPostInnoti(comm_id);
                    					
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
                    							   String rkout = notit.get(0).get("rank").toString();
                    							   String nameout = notit.get(0).get("name").toString();
                    					           String titleOUT = "POST OUT" ;
                    			                 String descriptionOut = ""+per_noout+", "+rkout+", "+nameout+" has been posted out to " +to_unit +" on " +date_tos ;
                    							    Boolean dout = notification.sendNotification(titleOUT, descriptionOut,user_idout, username);    
                    			              		}
                    						
                    					}
                    					else {
                    					
                    					if(roleSusNo.equals(tosus_no))
                    					{
                    						
                    						reciver_sus_no=from_sus;
                    						
                    					}
                    					if(roleSusNo.equals(from_sus))
                    					{
                    						
                    						reciver_sus_no=tosus_no;
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
                    					           
                    					           String description = ""+per_no+","+rk+","+name+" has been posted out to " +to_unit+" on "+date_tos ;
                    					           
                    					     //UOD.SendNotification(title,description,user_id);
                    					       	Boolean d = notification.sendNotification(title, description,user_id, username);
                    					     		}
                    					}
                    				} else {
                    					liststr.add("Approved Not Successfully.");
                    				}
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

	  
	  @RequestMapping(value = "/getunitsusno", method = RequestMethod.POST)
		public @ResponseBody List<String> getunitsusno(String personal_no, HttpSession sessionUserId) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery(
					"select distinct o.unit_name,c.unit_sus_no from TB_TRANS_PROPOSED_COMM_LETTER c, Miso_Orbat_Unt_Dtl o where personnel_no =:personal_no and c.unit_sus_no =o.sus_no");	
			q.setParameter("personal_no", personal_no);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
		
			return list;
		}
}
