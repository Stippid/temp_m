package com.controller.avation;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.HelpDeskController.helpController;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Notification.notificatioDAO;
import com.dao.avation.DACRDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_AVIATION_CHTL_DAILY_BASIS;
import com.models.TB_AVIATION_CHTL_DAILY_BASIS_HISTORY;
import com.models.TB_AVIATION_DAILY_BASIS;
import com.models.TB_AVIATION_DAILY_BASIS_HISTORY;
import com.models.TB_AVIATION_RPAS_DAILY_BASIS;
import com.models.TB_AVIATION_RPAS_DAILY_BASIS_HISTORY;
import com.models.TB_AVIATION_UPLOAD_DOC_DACR;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_UPLOAD_DOC_MCR;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class DacrController {
	
	@Autowired
	DACRDAO dcrdao;

	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();

	ValidationController validation = new ValidationController();

	String dacr_sus_no12 = "";
	String dacr_unit_name12 = "";
	
	 @Autowired
	 private notificatioDAO     notif;
	    helpController helpcntl=new helpController();
     NotificationController notification = new NotificationController();
	
	
	@RequestMapping(value = "/admin/dacrSetSession", method = RequestMethod.POST)
	public ModelAndView search_rising_dis_profileSetSession(ModelMap model, HttpServletRequest request,
			HttpSession sessionUserId, @RequestParam(value = "sus_no1", required = false) String sus_no1,
			@RequestParam(value = "unit_name1", required = false) String unit_name1) {
		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mvcr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (!sus_no1.equals("0") && !sus_no1.equals("")) {
			dacr_sus_no12 = sus_no1;
		}
		if (!unit_name1.equals("0") && !unit_name1.equals("")) {
			unit_name1 = unit_name1.replace("&#40;", "(");
			unit_name1 = unit_name1.replace("&#41;", ")");
			dacr_unit_name12 = unit_name1;
		}
		return new ModelAndView("redirect:dacr");
	}
	
	@RequestMapping(value = "/admin/dacr", method = RequestMethod.GET)
	public ModelAndView search_unit_profile(ModelMap Mmap, HttpSession sessionA,
	        @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		/*String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("dacr", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}*/
	    
	    
	    String roleAccess = sessionA.getAttribute("roleAccess").toString();
	    String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	    String roleType = sessionA.getAttribute("roleType").toString();

	    
	    Mmap.put("roleAccess", roleAccess);

	    if (roleAccess.equals("Unit")) {
	        
	        Mmap.put("sus_no", roleSusNo);
	        Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

	        String qry = ""; 
	        String qry1 = ""; 
	        ArrayList<List<String>> list = new ArrayList<>();
	        ArrayList<List<String>> list1 = new ArrayList<>();

	        try {
	            
	            list = dcrdao.UpdategetDACRReportListPending(qry, roleSusNo, roleType, roleAccess);
	            list1 = dcrdao.UpdategetDACRReportList(qry1, roleSusNo, roleType, roleAccess);

	            
	            System.out.println("RoleAccess: " + roleAccess);
	            System.out.println("RoleSusNo: " + roleSusNo);
	            System.out.println("RoleType: " + roleType);
	            System.out.println("Pending List Size: " + list.size());
	            System.out.println("Completed List Size: " + list1.size());

	            
	            if (list.isEmpty() && list1.isEmpty()) {
	                Mmap.put("msg", "Data Not Available.");
	            }

	            
	            Mmap.put("list", list);
	            Mmap.put("list1", list1);

	        } catch (Exception e) {
	           
	            System.err.println("Error fetching DACR reports: " + e.getMessage());
	            Mmap.put("msg", "An error occurred while fetching data. Please try again.");
	        }

	    } else {
	       
	        String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
	                + "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
	                + "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
	                + "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
	                + "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
	                + "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
	                + "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
	                + "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

	        
	        Mmap.put("Js_Css", Js_Css);
	        Mmap.put("dacr_sus_no1", dacr_sus_no12); 
	        Mmap.put("dacr_unit_name1", dacr_unit_name12); 
	    }

	    
	    Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
	    Mmap.put("msg", msg);

	    
	    return new ModelAndView("dacr_ReportTiles");
	}

	
	@RequestMapping(value = "/admin/UpdategetDACRReportList", method = RequestMethod.POST)
	public ModelAndView UpdategetDACRReportList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Boolean val = roledao.ScreenRedirect("dacr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String qry = "";
		String qry1 = "";
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
		if (!sus_no.equals("") & sus_no.length() == 8) {
			if (roleAccess.equals("Unit")) {
				sus_no = roleSusNo;
			} else {
				String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
						+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
						+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
						+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
						+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
						+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

				Mmap.put("Js_Css", Js_Css);
			}

			Mmap.put("sus_no", sus_no);
			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
		
			}
			
			ArrayList<List<String>> list = dcrdao.UpdategetDACRReportListPending(qry, sus_no, roleType,roleAccess);
			ArrayList<List<String>> list1 = dcrdao.UpdategetDACRReportList(qry1, sus_no, roleType,roleAccess);
			if (list.size() == 0 && list1.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
				
			} else {
				Mmap.put("list", list);
				Mmap.put("list1", list1);
			}
		} else if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Select SUS No.");
		} else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:dacr");
		}
		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:dacr");
		}
		return new ModelAndView("dacr_ReportTiles");
	}
 
	@RequestMapping(value = "/admin/getDACRReportList", method = RequestMethod.POST)
	public ModelAndView getDACRReportList(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name) {
		String roleType = sessionA.getAttribute("roleType").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (!sus_no.equals("")) {

			Mmap.put("sus_no", sus_no);
		}
		if (!unit_name.equals("")) {

			Mmap.put("unit_name", unit_name);
		}
		ArrayList<List<String>> list = dcrdao.getDACRReportListPending(sus_no, roleType);
		Mmap.put("list", list);
		return new ModelAndView("dacr_ReportTiles");
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/updateDacr", method = RequestMethod.POST)
	public @ResponseBody String updateDacr(@RequestParam(value = "msg", required = false) String msg,
	                                       @RequestParam String sus_no,
	                                       @RequestParam String acc_no,
	                                       @RequestParam String status,
	                                       @RequestParam(required = false) String falfHrsDay,
	                                       @RequestParam(required = false) String falfHrsNight,
	                                       @RequestParam(required = false) String gRun,
	                                       @RequestParam(required = false) String afHrs,
	                                       @RequestParam(required = false) String engHrsLeft,
	                                       @RequestParam(required = false) String engHrsRigth,
	                                       @RequestParam(required = false) String hrsLeft,
	                                       @RequestParam(required = false) String nextInsp,
	                                       @RequestParam(required = false) String hrsMonthly,
	                                       @RequestParam(required = false) String hrsQtrly,
	                                       @RequestParam(required = false) String hrsHalfYear,
	                                       @RequestParam(required = false) String hrsQtrlyFlow,
	                                       @RequestParam(required = false) String remarks,
	                                       @RequestParam(required = false) String balHrs,
	                                       @RequestParam(required = false) String goiDate,
	                                       @RequestParam(required = false) String pdcDate,
	                                       @RequestParam(required = false) String asonDate,
	                                       @RequestParam(required = false) String loc,
	                                       HttpSession sessionA, ModelMap Mmap) {

	   
	    System.out.println("Updating DACR with parameters: ");
	    System.out.println("sus_no: " + sus_no);
	    System.out.println("acc_no: " + acc_no);
	    System.out.println("goiDate (raw): " + goiDate);
	    System.out.println("pdcDate (raw): " + pdcDate);
	    
	    
	    if (loc == null || loc.isEmpty()) {
	        return "Invalid or missing Location! Please select locations.";
	    }
	    
	   
	    if (goiDate == null || goiDate.isEmpty() || !isValidDate(goiDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Dt last flown! Expected format: dd-MM-yyyy.";
	    }
	   /* if (pdcDate == null || pdcDate.isEmpty() || !isValidDate(pdcDate, "dd-MM-yyyy")) {
	        return "Invalid or missing PDC Date! Expected format: dd-MM-yyyy.";
	    }*/
	    if (asonDate == null || asonDate.isEmpty() || !isValidDate(asonDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Ason Date! Expected format: dd-MM-yyyy.";
	    }

	    
	    if (!isValidTimeFormat(falfHrsDay)) {
	        return "Invalid Day format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(falfHrsNight)) {
	        return "Invalid Night format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(gRun)) {
	        return "Invalid G Run format! Expected format: HH:mm.";
	    }
	    if (!isValidHourFormat(afHrs)) {
	        return "Invalid AF Hours format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(engHrsLeft)) {
	        return "Invalid Engine Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(engHrsRigth)) {
	        return "Invalid Engine Hours Right format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(hrsLeft)) {
	        return "Invalid Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }

	    Date formattedDate = null;
	    if (goiDate != null && !goiDate.isEmpty()) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            formattedDate = inputFormat.parse(goiDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    Date formattedDate1 = null;
	    if (pdcDate != null && !pdcDate.isEmpty()) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            formattedDate1 = inputFormat.parse(pdcDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    Date formattedDate2 = null;
	    if (asonDate != null && !asonDate.isEmpty()) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            formattedDate2 = inputFormat.parse(asonDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }

	    String username = (String) sessionA.getAttribute("username");
	    String roleAccess = (String) sessionA.getAttribute("roleAccess");
	    String roleSusNo = (String) sessionA.getAttribute("roleSusNo");

	    if ("Unit".equals(roleAccess)) {
	        sus_no = roleSusNo;
	    }

	    Session session = null;
	    Transaction tx = null;
	    Date dt = new Date();
	    Date asonDate1 = null;
	    String timeZone = "Asia/Kolkata"; 

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();
	        
	      
	            
	            asonDate1 = parseDateWithTimeZone(asonDate, "dd-MM-yyyy", timeZone);
	            System.err.println(asonDate1);
	            if (asonDate1 == null) {
	                return "DACR not updated due to invalid date format.";
	            }
	        

	        
	        String hqlUpdatePrevious = "UPDATE TB_AVIATION_DAILY_BASIS SET status1 = '0' WHERE acc_no = :acc_no AND sus_no = :sus_no";
	        Query updatePrevious = session.createQuery(hqlUpdatePrevious);
	        updatePrevious.setParameter("acc_no", acc_no);
	        updatePrevious.setParameter("sus_no", sus_no);
	        updatePrevious.executeUpdate();

	       
	        Query checkQuery = session.createQuery("from TB_AVIATION_DAILY_BASIS where acc_no = :acc_no and sus_no = :sus_no");
	        checkQuery.setParameter("acc_no", acc_no);
	        checkQuery.setParameter("sus_no", sus_no);
	     
	        if (checkQuery.list().isEmpty()) {
	            return "Data does not exist to update"; 
	        }
	        
	       
	        Query checkQuery1 = session.createQuery("from TB_AVIATION_DAILY_BASIS_HISTORY where acc_no = :acc_no and sus_no = :sus_no and ason_dt = :asonDate");
	        checkQuery1.setParameter("acc_no", acc_no);
	        checkQuery1.setParameter("sus_no", sus_no);
	        checkQuery1.setParameter("asonDate", asonDate1);
	        
	        if (!checkQuery1.list().isEmpty()) {
	            return "Data already updated for " +asonDate+ " date."; // Updated response for missing data
	        }

	        
	        String hql = "UPDATE TB_AVIATION_DAILY_BASIS SET ason_dt = :ason_dt, loc_code = :loc_code, date_of_pdc = :date_of_pdc, date_goi_letter = :date_goi_letter, modified_date = :modified_date, "
	                + "modified_by = :modified_by, status = :status, falf_hrs_day = :falf_hrs_day, falf_hrs_night = :falf_hrs_night, "
	                + "g_run = :g_run, af_hrs = :af_hrs, eng_hrs_left = :eng_hrs_left, eng_hrs_rigth = :eng_hrs_rigth, "
	                + "hrs_left = :hrs_left, next_insp = :next_insp, hrs_monthly = :hrs_monthly, hrs_qtrly = :hrs_qtrly, "
	                + "hrs_half_year = :hrs_half_year, hrs_qtrly_flow = :hrs_qtrly_flow, remarks = :remarks, bal_hrs = :bal_hrs "
	                + "WHERE acc_no = :acc_no AND sus_no = :sus_no";

	        Query updateQuery = session.createQuery(hql);
	        updateQuery.setParameter("modified_by", username);
	        updateQuery.setParameter("modified_date", new Date());
	        updateQuery.setParameter("status", status);
	        updateQuery.setParameter("falf_hrs_day", falfHrsDay);
	        updateQuery.setParameter("falf_hrs_night", falfHrsNight);
	        updateQuery.setParameter("g_run", gRun);
	        updateQuery.setParameter("af_hrs", afHrs);
	        updateQuery.setParameter("eng_hrs_left", engHrsLeft);
	        updateQuery.setParameter("eng_hrs_rigth", engHrsRigth);
	        updateQuery.setParameter("hrs_left", hrsLeft);
	        updateQuery.setParameter("next_insp", nextInsp);
	        updateQuery.setParameter("hrs_monthly", hrsMonthly);
	        updateQuery.setParameter("hrs_qtrly", hrsQtrly);
	        updateQuery.setParameter("hrs_half_year", hrsHalfYear);
	        updateQuery.setParameter("hrs_qtrly_flow", hrsQtrlyFlow);
	        updateQuery.setParameter("remarks", remarks);
	        updateQuery.setParameter("bal_hrs", balHrs);
	        updateQuery.setParameter("date_goi_letter", formattedDate);
	        updateQuery.setParameter("acc_no", acc_no);
	        updateQuery.setParameter("sus_no", sus_no);
	        updateQuery.setParameter("date_of_pdc", formattedDate1);
	        updateQuery.setParameter("loc_code", loc);
	        updateQuery.setParameter("ason_dt", formattedDate2);

	        int result = updateQuery.executeUpdate();
	        System.out.println("Rows affected: " + result);
	        
	        
	       
	        
	        @SuppressWarnings("unchecked")
	        List<TB_AVIATION_DAILY_BASIS> ListDacr = (List<TB_AVIATION_DAILY_BASIS>) checkQuery.list();
	        for (TB_AVIATION_DAILY_BASIS dacrDtl : ListDacr) {
	            TB_AVIATION_DAILY_BASIS_HISTORY dacr_dtl_htr = new TB_AVIATION_DAILY_BASIS_HISTORY();
	            
	            dacr_dtl_htr.setDacr_id(dacrDtl.getId());
	            dacr_dtl_htr.setAcc_no(acc_no);
	            dacr_dtl_htr.setAf_hrs(afHrs);
	            dacr_dtl_htr.setAuthority(dacrDtl.getAuthority());
	            dacr_dtl_htr.setBal_hrs(dacrDtl.getBal_hrs());
	            dacr_dtl_htr.setCreated_by(username);
	            dacr_dtl_htr.setCreated_date(dt);
	            dacr_dtl_htr.setDate_goi_letter(formattedDate);
	            dacr_dtl_htr.setDate_of_authority(dacrDtl.getDate_of_authority());
	            dacr_dtl_htr.setEng_hrs_left(engHrsLeft);
	            dacr_dtl_htr.setEng_hrs_rigth(engHrsRigth);
	            dacr_dtl_htr.setFalf_hrs_day(falfHrsDay);
	            dacr_dtl_htr.setFalf_hrs_night(falfHrsNight);
	            dacr_dtl_htr.setG_run(gRun);
	            dacr_dtl_htr.setHrs_half_year(hrsHalfYear);
	            dacr_dtl_htr.setHrs_left(hrsLeft);
	            dacr_dtl_htr.setHrs_monthly(hrsMonthly);
	            dacr_dtl_htr.setHrs_qtrly(hrsQtrly);
	            dacr_dtl_htr.setHrs_qtrly_flow(hrsQtrlyFlow);
	            dacr_dtl_htr.setNext_insp(nextInsp);
	            dacr_dtl_htr.setRemarks(remarks);
	            dacr_dtl_htr.setStatus(status);
	            dacr_dtl_htr.setStatus1("0");
	            dacr_dtl_htr.setSus_no(sus_no);
	            dacr_dtl_htr.setLh_ser_no(dacrDtl.getLh_ser_no());
	            dacr_dtl_htr.setRh_ser_no(dacrDtl.getRh_ser_no());
	            dacr_dtl_htr.setDate_of_pdc(formattedDate1);
	            dacr_dtl_htr.setLoc_code(loc);
	            dacr_dtl_htr.setAson_dt(formattedDate2);
	            dacr_dtl_htr.setAircraft_state(dacrDtl.getAircraft_state());
	            

	            session.save(dacr_dtl_htr);  
	        }
	        
	        
	        String hql1 = "UPDATE tb_aviation_tail_no_dtl SET lh_eng_hrs = :eng_hrs_left, rh_eng_hrs = :eng_hrs_rigth WHERE tail_no = :tail_no AND sus_no = :sus_no";
	        Query updateQuery1 = session.createSQLQuery(hql1);
	        updateQuery1.setParameter("eng_hrs_left", engHrsLeft);
	        updateQuery1.setParameter("eng_hrs_rigth", engHrsRigth);
	        updateQuery1.setParameter("tail_no", acc_no);
	        updateQuery1.setParameter("sus_no", sus_no);
	        updateQuery1.executeUpdate();
	        
	        
	        session.flush();

	        tx.commit();

	        return (result > 0) ? "Data Updated Successfully." : "No changes made.";
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); 
	        }
	        e.printStackTrace(); 
	        return "Error updating data: " + e.getMessage();
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	}

		           
		            private boolean isValidDate(String date, String format) {
		                try {
		                    SimpleDateFormat sdf = new SimpleDateFormat(format);
		                    sdf.setLenient(false);
		                    sdf.parse(date);
		                    return true;
		                } catch (Exception e) {
		                    return false;
		                }
		            }

		           
		            private boolean isValidTimeFormat(String time) {
		                if (time == null || time.isEmpty()) return true; 
		                String regex = "^([01][0-9]|2[0-3]):([0-5][0-9])$";
		                Pattern pattern = Pattern.compile(regex);
		                Matcher matcher = pattern.matcher(time);
		                return matcher.matches();
		            }
		            
		        
		            private boolean isValidHourFormat(String time) {
		                if (time == null || time.isEmpty()) return true; 
		                
		                
		                String regex = "^\\d+:[0-5][0-9]$";
		                Pattern pattern = Pattern.compile(regex);
		                Matcher matcher = pattern.matcher(time);
		                return matcher.matches();
		            }
	
	// New Adde By Mitesh 03-12-2024
	@RequestMapping(value = "/admin/dacrCHTL", method = RequestMethod.GET)
	public ModelAndView search_unit_profileCHTL(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		/*String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("dacr", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("roleAccess", roleAccess);
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			String qry = "";
			ArrayList<List<String>> list = dcrdao.UpdategetDACRReportchtlListPending(qry, roleSusNo, roleType,roleAccess);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
			} else {
				Mmap.put("list", list);	
			}
		} else {
			String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
					+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
					+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
					+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
					+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

			Mmap.put("Js_Css", Js_Css);
			Mmap.put("dacr_sus_no1", dacr_sus_no12);
			Mmap.put("dacr_unit_name1", dacr_unit_name12);
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
		Mmap.put("msg", msg);
		return new ModelAndView("dacr_ReportCHTLTiles");
	}
	
	
	@RequestMapping(value = "/admin/UpdategetDACRReportCHTLList", method = RequestMethod.POST)
	public ModelAndView UpdategetDACRReportCHTLList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Boolean val = roledao.ScreenRedirect("dacr", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
		String qry = "";
		if (!sus_no.equals("") & sus_no.length() == 8) {
			if (roleAccess.equals("Unit")) {
				sus_no = roleSusNo;
			} else {
				String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
						+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
						+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
						+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
						+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
						+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

				Mmap.put("Js_Css", Js_Css);
			}

			Mmap.put("sus_no", sus_no);
			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
			}

			ArrayList<List<String>> list = dcrdao.UpdategetDACRReportchtlListPending(qry, sus_no, roleType,roleAccess);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
			} else {
				Mmap.put("list", list);
			}
		} else if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Select SUS No.");
		} else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:dacr");
		}
		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:dacr");
		}
		return new ModelAndView("dacr_ReportCHTLTiles");
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/updatechtlDacr", method = RequestMethod.POST)
	public @ResponseBody String updatechtlDacr(@RequestParam(value = "msg", required = false) String msg,
										   @RequestParam String sus_no,
	                                       @RequestParam String acc_no,
	                                       @RequestParam String status,
	                                       @RequestParam(required = false) String falfHrsDay,
	                                       @RequestParam(required = false) String falfHrsNight,
	                                       @RequestParam(required = false) String gRun,
	                                       @RequestParam(required = false) String afHrs,
	                                       @RequestParam(required = false) String engHrs,
	                                       @RequestParam(required = false) String hrsLeft,
	                                       @RequestParam(required = false) String daysLeft,
	                                       @RequestParam(required = false) String nextInsp,
	                                       @RequestParam(required = false) String hrsMonthly,
	                                       @RequestParam(required = false) String hrsQtrly,
	                                       @RequestParam(required = false) String hrsHalfYear,
	                                       @RequestParam(required = false) String hrsQtrlyFlow,
	                                       @RequestParam(required = false) String remarks,
	                                       @RequestParam(required = false) String balHrs,
	                                       @RequestParam(required = false) String goiDate,
	                                       @RequestParam(required = false) String pdcDate,
	                                       @RequestParam(required = false) String asonDate,
	                                       @RequestParam(required = false) String loc,
	                                       HttpSession sessionA, ModelMap Mmap) {

	   
	    System.out.println("Updating DACR with parameters: ");
	    System.out.println("sus_no: " + sus_no);
	    System.out.println("acc_no: " + acc_no);
	    System.out.println("goiDate (raw): " + goiDate);
	    
	
	    if (goiDate == null || goiDate.isEmpty() || !isValidDate(goiDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Dt last flown! Expected format: dd-MM-yyyy.";
	    }
	   /* if (pdcDate == null || pdcDate.isEmpty() || !isValidDate(pdcDate, "dd-MM-yyyy")) {
	        return "Invalid or missing PDC Date! Expected format: dd-MM-yyyy.";
	    }*/
	    if (asonDate == null || asonDate.isEmpty() || !isValidDate(asonDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Ason Date! Expected format: dd-MM-yyyy.";
	    }

	    
	    if (!isValidTimeFormat(falfHrsDay)) {
	        return "Invalid Day format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(falfHrsNight)) {
	        return "Invalid Night format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(gRun)) {
	        return "Invalid G Run format! Expected format: HH:mm.";
	    }
	    if (!isValidHourFormat(afHrs)) {
	        return "Invalid AF Hours format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(engHrs)) {
	        return "Invalid Engine Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(hrsLeft)) {
	        return "Invalid Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }

	    
	    Date formattedDate = null;  // Initialize formattedDate as Date
	    if (goiDate != null && !goiDate.isEmpty()) {
	        try {
	           
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	           
	            formattedDate = inputFormat.parse(goiDate);
	            System.out.println("Formatted Date: " + formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    
	    Date formattedDate1 = null;
	    if (pdcDate != null && !pdcDate.isEmpty()) {
	        try {
	            
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            
	            formattedDate1 = inputFormat.parse(pdcDate);
	            System.out.println("Formatted Date: " + formattedDate1);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    
	    Date formattedDate2 = null;
	    if (asonDate != null && !asonDate.isEmpty()) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            formattedDate2 = inputFormat.parse(asonDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }

	    String username = (String) sessionA.getAttribute("username");
	    String roleAccess = (String) sessionA.getAttribute("roleAccess");
	    String roleSusNo = (String) sessionA.getAttribute("roleSusNo");

	    if ("Unit".equals(roleAccess)) {
	        sus_no = roleSusNo;
	    }

	    Session session = null;
	    Transaction tx = null;
	    Date dt = new Date();
	    Date asonDate1 = null;
	    String timeZone = "Asia/Kolkata"; 

	    try {
	    	
	    	
            asonDate1 = parseDateWithTimeZone(asonDate, "dd-MM-yyyy", timeZone);
            System.err.println(asonDate1);
            if (asonDate1 == null) {
                return "DACR not updated due to invalid date format.";
            }
        
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();
	        
	    
	        String hqlUpdatePrevious = "UPDATE TB_AVIATION_CHTL_DAILY_BASIS SET status1 = '0' WHERE acc_no = :acc_no AND sus_no = :sus_no";
	        Query updatePrevious = session.createQuery(hqlUpdatePrevious);
	        updatePrevious.setParameter("acc_no", acc_no);
	        updatePrevious.setParameter("sus_no", sus_no);
	        updatePrevious.executeUpdate();

	    
	        Query checkQuery = session.createQuery("from TB_AVIATION_CHTL_DAILY_BASIS where acc_no = :acc_no and sus_no = :sus_no");
	        checkQuery.setParameter("acc_no", acc_no);
	        checkQuery.setParameter("sus_no", sus_no);

	        if (checkQuery.list().isEmpty()) {
	            return "Data already exists"; 
	        }
	        
	        
	        Query checkQuery1 = session.createQuery("from TB_AVIATION_CHTL_DAILY_BASIS_HISTORY where acc_no = :acc_no and sus_no = :sus_no and ason_date = :asonDate");
	        checkQuery1.setParameter("acc_no", acc_no);
	        checkQuery1.setParameter("sus_no", sus_no);
	        checkQuery1.setParameter("asonDate", asonDate1);
	        
	        if (!checkQuery1.list().isEmpty()) {
	            return "Data already updated for " +asonDate+ " date."; 
	        }


	        
	        String hql = "UPDATE TB_AVIATION_CHTL_DAILY_BASIS SET ason_date = :ason_date,loc_code = :loc_code, date_of_pdc = :date_of_pdc, date_goi_letter = :date_goi_letter, modified_date = :modified_date, "
	                + "modified_by = :modified_by, status = :status, falf_hrs_day = :falf_hrs_day, falf_hrs_night = :falf_hrs_night, "
	                + "g_run = :g_run, af_hrs = :af_hrs, eng_hrs = :eng_hrs, days_left = :days_left , "
	                + "hrs_left = :hrs_left, next_insp = :next_insp, hrs_monthly = :hrs_monthly, hrs_qtrly = :hrs_qtrly, "
	                + "hrs_half_year = :hrs_half_year, hrs_qtrly_flow = :hrs_qtrly_flow, remarks = :remarks, bal_hrs = :bal_hrs "
	                + "WHERE acc_no = :acc_no AND sus_no = :sus_no";

	        Query updateQuery = session.createQuery(hql);
	        updateQuery.setParameter("modified_by", username);
	        updateQuery.setParameter("modified_date", new Date());
	        updateQuery.setParameter("status", status);
	        updateQuery.setParameter("falf_hrs_day", falfHrsDay);
	        updateQuery.setParameter("falf_hrs_night", falfHrsNight);
	        updateQuery.setParameter("g_run", gRun);
	        updateQuery.setParameter("af_hrs", afHrs);
	        updateQuery.setParameter("eng_hrs", engHrs);
	        updateQuery.setParameter("hrs_left", hrsLeft);
	        updateQuery.setParameter("days_left", daysLeft);
	        updateQuery.setParameter("next_insp", nextInsp);
	        updateQuery.setParameter("hrs_monthly", hrsMonthly);
	        updateQuery.setParameter("hrs_qtrly", hrsQtrly);
	        updateQuery.setParameter("hrs_half_year", hrsHalfYear);
	        updateQuery.setParameter("hrs_qtrly_flow", hrsQtrlyFlow);
	        updateQuery.setParameter("remarks", remarks);
	        updateQuery.setParameter("bal_hrs", balHrs);
	        updateQuery.setParameter("date_goi_letter", formattedDate); 
	        updateQuery.setParameter("acc_no", acc_no);
	        updateQuery.setParameter("sus_no", sus_no);
	        updateQuery.setParameter("date_of_pdc", formattedDate1);
	        updateQuery.setParameter("ason_date", formattedDate2);
	        updateQuery.setParameter("loc_code", loc); 
	        int result = updateQuery.executeUpdate();
	        System.out.println("Rows affected: " + result);
	        
	        
	        
	        
	        
	        @SuppressWarnings("unchecked")
	        List<TB_AVIATION_CHTL_DAILY_BASIS> ListDacr = (List<TB_AVIATION_CHTL_DAILY_BASIS>) checkQuery.list();
	        for (TB_AVIATION_CHTL_DAILY_BASIS dacrDtl : ListDacr) {
	        	TB_AVIATION_CHTL_DAILY_BASIS_HISTORY dacr_dtl_htr = new TB_AVIATION_CHTL_DAILY_BASIS_HISTORY();
	            
	            dacr_dtl_htr.setDacr_id(dacrDtl.getId());
	            dacr_dtl_htr.setAcc_no(acc_no);
	            dacr_dtl_htr.setAf_hrs(afHrs);
	            dacr_dtl_htr.setAuthority(dacrDtl.getAuthority());
	            dacr_dtl_htr.setBal_hrs(dacrDtl.getBal_hrs());
	            dacr_dtl_htr.setCreated_by(username);
	            dacr_dtl_htr.setCreated_date(dt);
	            dacr_dtl_htr.setDate_goi_letter(formattedDate);
	            dacr_dtl_htr.setDate_of_authority(dacrDtl.getDate_of_authority());
	            dacr_dtl_htr.setEng_hrs(engHrs);
	            dacr_dtl_htr.setFalf_hrs_day(falfHrsDay);
	            dacr_dtl_htr.setFalf_hrs_night(falfHrsNight);
	            dacr_dtl_htr.setG_run(gRun);
	            dacr_dtl_htr.setHrs_half_year(hrsHalfYear);
	            dacr_dtl_htr.setHrs_left(hrsLeft);
	            dacr_dtl_htr.setHrs_monthly(hrsMonthly);
	            dacr_dtl_htr.setHrs_qtrly(hrsQtrly);
	            dacr_dtl_htr.setHrs_qtrly_flow(hrsQtrlyFlow);
	            dacr_dtl_htr.setNext_insp(nextInsp);
	            dacr_dtl_htr.setRemarks(remarks);
	            dacr_dtl_htr.setStatus(status);
	            dacr_dtl_htr.setStatus1("0");
	            dacr_dtl_htr.setSus_no(sus_no);
	            dacr_dtl_htr.setEng_ser_no(dacrDtl.getEng_ser_no());
	            dacr_dtl_htr.setDate_of_pdc(formattedDate1);
	            dacr_dtl_htr.setLoc_code(loc);
	            dacr_dtl_htr.setAson_date(formattedDate2);
	            dacr_dtl_htr.setDays_left(daysLeft);

	            session.save(dacr_dtl_htr);  
	        }
	        
	        String hql1 = "UPDATE tb_aviation_chtl_tail_no_dtl SET eng_hrs = :eng_hrs WHERE tail_no = :tail_no AND sus_no = :sus_no";
        Query updateQuery1 = session.createSQLQuery(hql1);
        updateQuery1.setParameter("eng_hrs", engHrs);
        updateQuery1.setParameter("tail_no", acc_no);
        updateQuery1.setParameter("sus_no", sus_no);
        updateQuery1.executeUpdate();
	       
	        session.flush();
	        tx.commit();

	        return (result > 0) ? "Data Updated Successfully." : "No changes made.";
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); 
	        }
	        e.printStackTrace(); 
	        return "Error updating data: " + e.getMessage();
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	}
	
	// New Adde By Mitesh 12-12-2024
	@RequestMapping(value = "/admin/dacrRPAS", method = RequestMethod.GET)
	public ModelAndView search_unit_profileRPAS(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		/*String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("dacrRPAS", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}*/
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		Mmap.put("roleAccess", roleAccess);
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0));

			String qry = "";
			ArrayList<List<String>> list = dcrdao.UpdategetDACRReportrpasListPending(qry, roleSusNo, roleType,roleAccess);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
			} else {
				Mmap.put("list", list);	
			}
		} else {
			String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
					+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
					+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
					+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
					+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
					+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
					+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

			Mmap.put("Js_Css", Js_Css);
			Mmap.put("dacr_sus_no1", dacr_sus_no12);
			Mmap.put("dacr_unit_name1", dacr_unit_name12);
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
		Mmap.put("msg", msg);
		return new ModelAndView("dacr_ReportRPASTiles");
	}
	
	@RequestMapping(value = "/admin/UpdategetDACRReportRPASList", method = RequestMethod.POST)
	public ModelAndView UpdategetDACRReportRPASList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no2", required = false) String sus_no,
			@RequestParam(value = "unit_name2", required = false) String unit_name) {
		String roleid = session.getAttribute("roleid").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		Boolean val = roledao.ScreenRedirect("dacrRPAS", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicAVN());
		String qry = "";
		if (!sus_no.equals("") & sus_no.length() == 8) {
			if (roleAccess.equals("Unit")) {
				sus_no = roleSusNo;
			} else {
				String Js_Css = "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/aes.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/lib/pbkdf2.js\"></script>\r\n"
						+ "<script type=\"text/javascript\" src=\"js/AES_ENC_DEC/AesUtil.js\"></script>\r\n"
						+ "<link rel=\"stylesheet\" href=\"js/miso/autoComplate/autoComplate.css\">\r\n"
						+ "<script src=\"js/miso/miso_js/jquery-2.2.3.min.js\"></script> "
						+ "<link href=\"js/Calender/jquery-ui.css\" rel=\"Stylesheet\"></link>\r\n"
						+ "<script src=\"js/Calender/jquery-ui.js\" type=\"text/javascript\"></script>\r\n"
						+ "<script src=\"js/miso/commonJS/commonmethod.js\" type=\"text/javascript\"></script>\r\n";

				Mmap.put("Js_Css", Js_Css);
			}

			Mmap.put("sus_no", sus_no);
			if (all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).size() > 0) {
				Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(sus_no, session).get(0));
			}

			ArrayList<List<String>> list = dcrdao.UpdategetDACRReportrpasListPending(qry, sus_no, roleType,roleAccess);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
				Mmap.put("list", list);
			} else {
				Mmap.put("list", list);
			}
		} else if (sus_no.equals("") || sus_no.equals(null) || sus_no == "" || sus_no == null) {
			Mmap.put("msg", "Please Select SUS No.");
		} else if (validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:dacrRPAS");
		}
		if (validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:dacrRPAS");
		}
		return new ModelAndView("dacr_ReportRPASTiles");
	}

	//chnages by mitesh (26-12-2024)
	@SuppressWarnings("null")
	@RequestMapping(value = "/updaterpasDacr", method = RequestMethod.POST)
	public @ResponseBody String updaterpasDacr(@RequestParam(value = "msg", required = false) String msg,
										   @RequestParam String sus_no,
	                                       @RequestParam String acc_no,
	                                       @RequestParam String status,
	                                       @RequestParam(required = false) String falfHrsDay,
	                                       @RequestParam(required = false) String falfHrsNight,
	                                       @RequestParam(required = false) String gRun,
	                                       @RequestParam(required = false) String afHrs,
	                                       @RequestParam(required = false) String engHrs,
	                                       @RequestParam(required = false) String hrsLeft,
	                                       @RequestParam(required = false) String daysLeft,
	                                       @RequestParam(required = false) String nextInsp,
	                                       @RequestParam(required = false) String hrsMonthly,
	                                       @RequestParam(required = false) String hrsQtrly,
	                                       @RequestParam(required = false) String hrsHalfYear,
	                                       @RequestParam(required = false) String hrsQtrlyFlow,
	                                       @RequestParam(required = false) String remarks,
	                                       @RequestParam(required = false) String balHrs,
	                                       @RequestParam(required = false) String goiDate,
	                                       @RequestParam(required = false) String pdcDate,
	                                       @RequestParam(required = false) String asonDate,
	                                       @RequestParam(required = false) String loc,
	                                       HttpSession sessionA, ModelMap Mmap) {

	   
	    System.out.println("Updating DACR with parameters: ");
	    System.out.println("sus_no: " + sus_no);
	    System.out.println("acc_no: " + acc_no);
	    System.out.println("goiDate (raw): " + goiDate);
	    
	    
	    if (goiDate == null || goiDate.isEmpty() || !isValidDate(goiDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Dt last flown! Expected format: dd-MM-yyyy.";
	    }
	   /* if (pdcDate == null || pdcDate.isEmpty() || !isValidDate(pdcDate, "dd-MM-yyyy")) {
	        return "Invalid or missing PDC Date! Expected format: dd-MM-yyyy.";
	    }*/
	    if (asonDate == null || asonDate.isEmpty() || !isValidDate(asonDate, "dd-MM-yyyy")) {
	        return "Invalid or missing Ason Date! Expected format: dd-MM-yyyy.";
	    }

	   
	    if (!isValidTimeFormat(falfHrsDay)) {
	        return "Invalid Day format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(falfHrsNight)) {
	        return "Invalid Night format! Expected format: HH:mm.";
	    }
	    if (!isValidTimeFormat(gRun)) {
	        return "Invalid G Run format! Expected format: HH:mm.";
	    }
	    if (!isValidHourFormat(afHrs)) {
	        return "Invalid AF Hours format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(engHrs)) {
	        return "Invalid Engine Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }
	    if (!isValidHourFormat(hrsLeft)) {
	        return "Invalid Hours Left format! Expected format: hours:minutes (e.g., 100:30).";
	    }

	    
	    
	    Date formattedDate = null; 
	    if (goiDate != null && !goiDate.isEmpty()) {
	        try {
	           
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            
	            formattedDate = inputFormat.parse(goiDate);
	            System.out.println("Formatted Date: " + formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    
	    Date formattedDate1 = null;
	    if (pdcDate != null && !pdcDate.isEmpty()) {
	        try {
	            
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            
	            formattedDate1 = inputFormat.parse(pdcDate);
	            System.out.println("Formatted Date: " + formattedDate1);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }
	    
	    
	    Date formattedDate2 = null;
	    if (asonDate != null && !asonDate.isEmpty()) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	            formattedDate2 = inputFormat.parse(asonDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Invalid date format! Expected format: dd-MM-yyyy.";
	        }
	    }


	    String username = (String) sessionA.getAttribute("username");
	    String roleAccess = (String) sessionA.getAttribute("roleAccess");
	    String roleSusNo = (String) sessionA.getAttribute("roleSusNo");

	    if ("Unit".equals(roleAccess)) {
	        sus_no = roleSusNo;
	    }

	    Session session = null;
	    Transaction tx = null;
	    Date dt = new Date();
	    Date asonDate1 = null;
	    String timeZone = "Asia/Kolkata"; 

	    try {
	    	
	    	
            asonDate1 = parseDateWithTimeZone(asonDate, "dd-MM-yyyy", timeZone);
            System.err.println(asonDate1);
            if (asonDate1 == null) {
                return "DACR not updated due to invalid date format.";
            }
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();

	        
	    
	        Query checkQuery = session.createQuery("from TB_AVIATION_RPAS_DAILY_BASIS where acc_no = :acc_no and sus_no = :sus_no");
	        checkQuery.setParameter("acc_no", acc_no);
	        checkQuery.setParameter("sus_no", sus_no);

	        if (checkQuery.list().isEmpty()) {
	            return "Data already exists"; 
	        }
	        
	        
	        
	      
	        Query checkQuery1 = session.createQuery("from TB_AVIATION_RPAS_DAILY_BASIS_HISTORY where acc_no = :acc_no and sus_no = :sus_no and ason_date = :asonDate");
	        checkQuery1.setParameter("acc_no", acc_no);
	        checkQuery1.setParameter("sus_no", sus_no);
	        checkQuery1.setParameter("asonDate", asonDate1);
	        
	        if (!checkQuery1.list().isEmpty()) {
	            return "Data already updated for " +asonDate+ " date."; 
	        }
	        

	       
	        String hql = "UPDATE TB_AVIATION_RPAS_DAILY_BASIS SET status1 = :status1, ason_date = :ason_date, loc_code = :loc_code, date_of_pdc = :date_of_pdc, date_goi_letter = :date_goi_letter, modified_date = :modified_date, "
	                + "modified_by = :modified_by, status = :status, falf_hrs_day = :falf_hrs_day, falf_hrs_night = :falf_hrs_night, "
	                + "g_run = :g_run, af_hrs = :af_hrs, eng_hrs = :eng_hrs, days_left = :days_left , "
	                + "hrs_left = :hrs_left, next_insp = :next_insp, hrs_monthly = :hrs_monthly, hrs_qtrly = :hrs_qtrly, "
	                + "hrs_half_year = :hrs_half_year, hrs_qtrly_flow = :hrs_qtrly_flow, remarks = :remarks, bal_hrs = :bal_hrs "
	                + "WHERE acc_no = :acc_no AND sus_no = :sus_no";

	        Query updateQuery = session.createQuery(hql);
	        updateQuery.setParameter("modified_by", username);
	        updateQuery.setParameter("modified_date", new Date());
	        updateQuery.setParameter("status", status);
	        updateQuery.setParameter("falf_hrs_day", falfHrsDay);
	        updateQuery.setParameter("falf_hrs_night", falfHrsNight);
	        updateQuery.setParameter("g_run", gRun);
	        updateQuery.setParameter("af_hrs", afHrs);
	        updateQuery.setParameter("eng_hrs", engHrs);
	        updateQuery.setParameter("hrs_left", hrsLeft);
	        updateQuery.setParameter("days_left", daysLeft);
	        updateQuery.setParameter("next_insp", nextInsp);
	        updateQuery.setParameter("hrs_monthly", hrsMonthly);
	        updateQuery.setParameter("hrs_qtrly", hrsQtrly);
	        updateQuery.setParameter("hrs_half_year", hrsHalfYear);
	        updateQuery.setParameter("hrs_qtrly_flow", hrsQtrlyFlow);
	        updateQuery.setParameter("remarks", remarks);
	        updateQuery.setParameter("bal_hrs", balHrs);
	        updateQuery.setParameter("date_goi_letter", formattedDate); // Pass the Date object directly
	        updateQuery.setParameter("acc_no", acc_no);
	        updateQuery.setParameter("sus_no", sus_no);
	        updateQuery.setParameter("date_of_pdc", formattedDate1);
	        updateQuery.setParameter("loc_code", loc); 
	        updateQuery.setParameter("ason_date", formattedDate2);
	        updateQuery.setParameter("status1", "0");
	      
	        int result = updateQuery.executeUpdate();
	        System.out.println("Rows affected: " + result);
	        
	        
	      
	        
	        @SuppressWarnings("unchecked")
	        List<TB_AVIATION_RPAS_DAILY_BASIS> ListDacr = (List<TB_AVIATION_RPAS_DAILY_BASIS>) checkQuery.list();
  	        for (TB_AVIATION_RPAS_DAILY_BASIS dacrDtl : ListDacr) {     	
  	        	TB_AVIATION_RPAS_DAILY_BASIS_HISTORY dacr_dtl_htr = new TB_AVIATION_RPAS_DAILY_BASIS_HISTORY();
  	            
  	            dacr_dtl_htr.setDacr_id(dacrDtl.getId());
  	            dacr_dtl_htr.setAcc_no(acc_no);
  	            dacr_dtl_htr.setAf_hrs(afHrs);
  	            dacr_dtl_htr.setAuthority(dacrDtl.getAuthority());
  	            dacr_dtl_htr.setBal_hrs(dacrDtl.getBal_hrs());
  	            dacr_dtl_htr.setCreated_by(username);
  	            dacr_dtl_htr.setCreated_date(dt);
  	            dacr_dtl_htr.setDate_goi_letter(formattedDate);
  	            dacr_dtl_htr.setDate_of_authority(dacrDtl.getDate_of_authority());
  	            dacr_dtl_htr.setFalf_hrs_day(falfHrsDay);
  	            dacr_dtl_htr.setFalf_hrs_night(falfHrsNight);
  	            dacr_dtl_htr.setG_run(gRun);
  	            dacr_dtl_htr.setHrs_half_year(hrsHalfYear);
  	            dacr_dtl_htr.setHrs_left(hrsLeft);
  	            dacr_dtl_htr.setHrs_monthly(hrsMonthly);
  	            dacr_dtl_htr.setHrs_qtrly(hrsQtrly);
  	            dacr_dtl_htr.setHrs_qtrly_flow(hrsQtrlyFlow);
  	            dacr_dtl_htr.setNext_insp(nextInsp);
  	            dacr_dtl_htr.setRemarks(remarks);
  	            dacr_dtl_htr.setStatus(status);
  	            dacr_dtl_htr.setStatus1("0");
  	            dacr_dtl_htr.setSus_no(sus_no);
  	            dacr_dtl_htr.setEng_hrs(engHrs);
  	            dacr_dtl_htr.setEng_ser_no(dacrDtl.getEng_ser_no());
  	            dacr_dtl_htr.setDays_left(daysLeft);
  	            dacr_dtl_htr.setAson_date(formattedDate2);
  	            dacr_dtl_htr.setDate_of_pdc(formattedDate1);
  	            dacr_dtl_htr.setLoc_code(loc);
  	         
  	            
  	            session.save(dacr_dtl_htr);  
  	        }
  	        
  	      String hql1 = "UPDATE tb_aviation_rpas_tail_no_dtl set eng_hrs= :eng_hr WHERE tail_no = :tail_no AND sus_no = :sus_no";
	        Query updateQuery1 = session.createSQLQuery(hql1);
	        updateQuery1.setParameter("eng_hr", engHrs);
	        updateQuery1.setParameter("tail_no", acc_no);
	        updateQuery1.setParameter("sus_no", sus_no);
	        updateQuery1.executeUpdate();
  	        
	        session.flush();
	        tx.commit();

	        return (result > 0) ? "Data Updated Successfully." : "No changes made.";
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); 
	        }
	        e.printStackTrace(); 
	        return "Error updating data: " + e.getMessage();
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	}

	
    private Date parseDateWithTimeZone(String dateString, String format, String timeZone) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone)); 
            return dateFormat.parse(dateString); 
        } catch (ParseException e) {
            e.printStackTrace();
            return null; 
        }
    }
    
// Upload Document Dacr by Mitesh (24-01-2025) 
@RequestMapping(value = "/admin/uploadDocDACR")
public @ResponseBody List<String> uploadDocDACR(@RequestParam(value = "uploadDacr", required = false) MultipartFile uploadDacr,HttpServletRequest request,ModelMap model,HttpSession session ,String sus) {

List<String> list = new ArrayList<>();
final long fileSizeLimit =   Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
if (uploadDacr.getSize() > fileSizeLimit) {
list.add("File size should be less then 2 MB");
return list;
}

String uploadDacrExt = FilenameUtils.getExtension(uploadDacr.getOriginalFilename());
if(!uploadDacrExt.equals("zip") & !uploadDacrExt.equals("rar")) {
list.add("Only *.zip or *.rar file extensions allowed");
return list;
}

String username = session.getAttribute("username").toString();
String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

String issue_summary = request.getParameter("issue_summary");
String description_help = request.getParameter("description");
String report_obsn = request.getParameter("report_obsn");

System.err.println("issue_summary--"+issue_summary);
System.err.println("description_help--"+description_help);
System.err.println("report_obsn--"+report_obsn);
System.err.println("uploadDacr--"+sus);
TB_AVIATION_UPLOAD_DOC_DACR upload = new TB_AVIATION_UPLOAD_DOC_DACR();

int userid = Integer.parseInt(session.getAttribute("userId").toString());
String fname = "";
if (!uploadDacr.isEmpty()) {
DateWithTimeStampController timestamp = new DateWithTimeStampController();

try { 
byte[] bytes = uploadDacr.getBytes();

CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
if(fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
String avnFilePath = session.getAttribute("avnFilePath").toString();
File dir = new File(avnFilePath);
if (!dir.exists()) {
dir.mkdirs();
}

String filename = uploadDacr.getOriginalFilename();
String extension="";
int i = filename.lastIndexOf('.');
if (i >= 0) {
extension = filename.substring(i+1);
}
fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_AVNDOC."+extension;
File serverFile = new File(fname);                             
BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
stream.write(bytes);                               
stream.close();
upload.setDocument(fname);
upload.setCreatedby(username);
upload.setCreateddatetime(date);
upload.setSus_no(sus);
upload.setStatus("0");

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
session1.save(upload);
String s=create_ticket_avn(upload,session, issue_summary, description_help, report_obsn);
session1.getTransaction().commit();
session1.close();
list.add("Document Uploaded Successfully");
}else {
list.add("Invalid File Format.");
}
}
catch (Exception e) {
	System.err.println(e);
list.add("an Error ocurre file saving.");
}
} 
return list;
} 

private String create_ticket_avn(TB_AVIATION_UPLOAD_DOC_DACR upload ,HttpSession session,String issue_summary,String description_help,String report_obsn) {
String msg="";
String user_id = "";
String module_name = "AVIATION";
String sub_module = "ALH/LCH/APACHE";
String screenname = "DACR";
List<TB_LDAP_MODULE_MASTER>   module=helpcntl.getModuleNameHelpDeskList(session);
List<TB_LDAP_SUBMODULE_MASTER>   submodule=helpcntl.getSubModuleList(session);
List<TB_LDAP_SCREEN_MASTER> screen=helpcntl. getScreenList(session);
HD_TB_BISAG_TICKET_GENERATE tickect=new HD_TB_BISAG_TICKET_GENERATE();
for(TB_LDAP_MODULE_MASTER a:module)
{
if(a.getModule_name().equals("AVIATION"))
{
tickect.setModule(a.getId()); 
}
}
for(TB_LDAP_SUBMODULE_MASTER b:submodule)
{

if(b.getSubmodule_name().equals("ALH/LCH/APACHE"))
{
tickect.setSub_module(b.getId());
}

}
for(TB_LDAP_SCREEN_MASTER c:screen)
{
if(c.getScreen_name().equals("DACR"))
{
tickect.setScreen_name(c.getId());
} 
}
int userId = Integer.parseInt(session.getAttribute("userId").toString());
tickect.setCreated_by(upload.getCreatedby());
tickect.setCreated_on(new Date());
// tickect.setDescription();
tickect.setHelp_topic(report_obsn);
tickect.setIssue_summary(issue_summary);
tickect.setDescription(description_help);
tickect.setUserid(userId);
tickect.setTicket_status("0");
int ticket = helpcntl.getMaxticket();
tickect.setTicket(ticket);
tickect.setscreen_shot(upload.getDocument());
String roleAccess = session.getAttribute("roleAccess").toString();
String roleSusNo = session.getAttribute("roleSusNo").toString();
String username = session.getAttribute("username").toString();
if (roleAccess.equals("Unit")) {
tickect.setSus_no(roleSusNo);
} 
tickect.setCreated_on(new Date());
String unit_name=notif.getUnitNameFromUserId(userId);
String description="Please Check the Ticket in Manage Ticket Screen";
Integer module_id = tickect.getModule();
List<String> list = notif.getUserIdForNotif(module_id);
for(int i=0;i<list.size();i++) {
user_id+=list.get(i);
if(i<list.size()-1)
user_id+=",";
}

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
int N_id=(int)session1.save(tickect);

if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
Boolean d = notification.sendNotification_tms("TICKET GENERATED for " +   module_name +" : " + sub_module + " : " + screenname + " by " + unit_name,description,user_id, username,N_id);
}
//session1.save(tic_gen_noti);
session1.getTransaction().commit();
session1.close();
return msg;
}

@RequestMapping(value = "/getDocumentDACR",method = RequestMethod.POST)
public @ResponseBody List<TB_AVIATION_UPLOAD_DOC_DACR> getDocumentDACR(String sus_no,HttpSession sessionA) {
	
	String roleAccess = sessionA.getAttribute("roleAccess").toString();
	String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	if(roleAccess.equals("Unit")){
		sus_no = roleSusNo;
	}
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("from TB_AVIATION_UPLOAD_DOC_DACR where sus_no =:sus_no and status='0'");
	q.setParameter("sus_no", sus_no);
	@SuppressWarnings("unchecked")
	List<TB_AVIATION_UPLOAD_DOC_DACR> list = (List<TB_AVIATION_UPLOAD_DOC_DACR>) q.list();
	tx.commit();
	session.close();
	return list;
}
@RequestMapping(value = "/admin/getDownloadImageDACR_Report",method = RequestMethod.POST)
public ModelAndView getDownloadImageDACR_Report(@ModelAttribute("id1") int id1,ModelMap model ,HttpSession session,
		HttpServletRequest request,HttpServletResponse response) throws IOException{
	String EXTERNAL_FILE_PATH = "";
	List<TB_AVIATION_UPLOAD_DOC_DACR> a=getDocumentDACRImg(id1,session);

	EXTERNAL_FILE_PATH = a.get(0).getDocument();
	File file = null;
    file = new File(EXTERNAL_FILE_PATH);
    try{
    	if(!file.exists()){
    		//model.put("sus_no1", sus_no1);
    		return new ModelAndView("redirect:UpdategetDACRReportList?msg=Sorry. The file you are looking for does not exist");
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
    	
    	//Update status  
    	String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionUpdate.beginTransaction();
		String hqlUpdate = "update TB_AVIATION_UPLOAD_DOC_DACR u set u.status=:status,downloadby=:downloadby,downloadon=:downloadon where u.id =:id";
		sessionUpdate.createQuery( hqlUpdate ).setString( "status", "1").setInteger( "id",id1).setString( "downloadby", username).setString( "downloadon", date).executeUpdate();
		tx.commit();
		sessionUpdate.close();
		
    } catch (FileNotFoundException e) {
		e.printStackTrace();
    }
    return new ModelAndView("redirect:UpdategetDACRReportList?msg=Download Successfully.");
}
public List<TB_AVIATION_UPLOAD_DOC_DACR> getDocumentDACRImg(int id1,HttpSession sessionA) {
	String roleAccess = sessionA.getAttribute("roleAccess").toString();
	String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q =  null;
	if(roleAccess.equals("Unit")){
		q = session.createQuery("from TB_AVIATION_UPLOAD_DOC_DACR where id=:id and sus_no=:sus_no");
		q.setParameter("id", id1);
		q.setParameter("sus_no", roleSusNo);
	}else {
		q = session.createQuery("from TB_AVIATION_UPLOAD_DOC_DACR where id=:id ");
		q.setParameter("id", id1);
	}
	@SuppressWarnings("unchecked")
	List<TB_AVIATION_UPLOAD_DOC_DACR> list = (List<TB_AVIATION_UPLOAD_DOC_DACR>) q.list();
	tx.commit();
	session.close();
	return list;
}

@RequestMapping(value = "/admin/uploadDocDACRchtl")
public @ResponseBody List<String> uploadDocDACRchtl(@RequestParam(value = "uploadDacr", required = false) MultipartFile uploadDacr,HttpServletRequest request,ModelMap model,HttpSession session ,String sus) {

List<String> list = new ArrayList<>();
final long fileSizeLimit =   Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
if (uploadDacr.getSize() > fileSizeLimit) {
list.add("File size should be less then 2 MB");
return list;
}

String uploadDacrExt = FilenameUtils.getExtension(uploadDacr.getOriginalFilename());
if(!uploadDacrExt.equals("zip") & !uploadDacrExt.equals("rar")) {
list.add("Only *.zip or *.rar file extensions allowed");
return list;
}

String username = session.getAttribute("username").toString();
String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

String issue_summary = request.getParameter("issue_summary");
String description_help = request.getParameter("description");
String report_obsn = request.getParameter("report_obsn");

System.err.println("issue_summary--"+issue_summary);
System.err.println("description_help--"+description_help);
System.err.println("report_obsn--"+report_obsn);
System.err.println("uploadDacr--"+sus);
TB_AVIATION_UPLOAD_DOC_DACR upload = new TB_AVIATION_UPLOAD_DOC_DACR();

int userid = Integer.parseInt(session.getAttribute("userId").toString());
String fname = "";
if (!uploadDacr.isEmpty()) {
DateWithTimeStampController timestamp = new DateWithTimeStampController();

try { 
byte[] bytes = uploadDacr.getBytes();

CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
if(fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
String avnFilePath = session.getAttribute("avnFilePath").toString();
File dir = new File(avnFilePath);
if (!dir.exists()) {
dir.mkdirs();
}

String filename = uploadDacr.getOriginalFilename();
String extension="";
int i = filename.lastIndexOf('.');
if (i >= 0) {
extension = filename.substring(i+1);
}
fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_AVNDOC."+extension;
File serverFile = new File(fname);                             
BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
stream.write(bytes);                               
stream.close();
upload.setDocument(fname);
upload.setCreatedby(username);
upload.setCreateddatetime(date);
upload.setSus_no(sus);
upload.setStatus("0");

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
session1.save(upload);
String s=create_ticket_avnchtl(upload,session, issue_summary, description_help, report_obsn);
session1.getTransaction().commit();
session1.close();
list.add("Document Uploaded Successfully");
}else {
list.add("Invalid File Format.");
}
}
catch (Exception e) {
	System.err.println(e);
list.add("an Error ocurre file saving.");
}
} 
return list;
} 

private String create_ticket_avnchtl(TB_AVIATION_UPLOAD_DOC_DACR upload ,HttpSession session,String issue_summary,String description_help,String report_obsn) {
String msg="";
String user_id = "";
String module_name = "AVIATION";
String sub_module = "CHT/CHK/CTL";
String screenname = "DACR FOR CH";
List<TB_LDAP_MODULE_MASTER>   module=helpcntl.getModuleNameHelpDeskList(session);
List<TB_LDAP_SUBMODULE_MASTER>   submodule=helpcntl.getSubModuleList(session);
List<TB_LDAP_SCREEN_MASTER> screen=helpcntl. getScreenList(session);
HD_TB_BISAG_TICKET_GENERATE tickect=new HD_TB_BISAG_TICKET_GENERATE();
for(TB_LDAP_MODULE_MASTER a:module)
{
if(a.getModule_name().equals("AVIATION"))
{
tickect.setModule(a.getId()); 
}
}
for(TB_LDAP_SUBMODULE_MASTER b:submodule)
{

if(b.getSubmodule_name().equals("CHT/CHK/CTL"))
{
tickect.setSub_module(b.getId());
}

}
for(TB_LDAP_SCREEN_MASTER c:screen)
{
if(c.getScreen_name().equals("DACR FOR CH"))
{
tickect.setScreen_name(c.getId());
} 
}
int userId = Integer.parseInt(session.getAttribute("userId").toString());
tickect.setCreated_by(upload.getCreatedby());
tickect.setCreated_on(new Date());
// tickect.setDescription();
tickect.setHelp_topic(report_obsn);
tickect.setIssue_summary(issue_summary);
tickect.setDescription(description_help);
tickect.setUserid(userId);
tickect.setTicket_status("0");
int ticket = helpcntl.getMaxticket();
tickect.setTicket(ticket);
tickect.setscreen_shot(upload.getDocument());
String roleAccess = session.getAttribute("roleAccess").toString();
String roleSusNo = session.getAttribute("roleSusNo").toString();
String username = session.getAttribute("username").toString();
if (roleAccess.equals("Unit")) {
tickect.setSus_no(roleSusNo);
} 
tickect.setCreated_on(new Date());
String unit_name=notif.getUnitNameFromUserId(userId);
String description="Please Check the Ticket in Manage Ticket Screen";
Integer module_id = tickect.getModule();
List<String> list = notif.getUserIdForNotif(module_id);
for(int i=0;i<list.size();i++) {
user_id+=list.get(i);
if(i<list.size()-1)
user_id+=",";
}

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
int N_id=(int)session1.save(tickect);

if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
Boolean d = notification.sendNotification_tms("TICKET GENERATED for " +   module_name +" : " + sub_module + " : " + screenname + " by " + unit_name,description,user_id, username,N_id);
}
//session1.save(tic_gen_noti);
session1.getTransaction().commit();
session1.close();
return msg;
}

@RequestMapping(value = "/admin/uploadDocDACRRPAS")
public @ResponseBody List<String> uploadDocDACRRPAS(@RequestParam(value = "uploadDacr", required = false) MultipartFile uploadDacr,HttpServletRequest request,ModelMap model,HttpSession session ,String sus) {

List<String> list = new ArrayList<>();
final long fileSizeLimit =   Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
if (uploadDacr.getSize() > fileSizeLimit) {
list.add("File size should be less then 2 MB");
return list;
}

String uploadDacrExt = FilenameUtils.getExtension(uploadDacr.getOriginalFilename());
if(!uploadDacrExt.equals("zip") & !uploadDacrExt.equals("rar")) {
list.add("Only *.zip or *.rar file extensions allowed");
return list;
}

String username = session.getAttribute("username").toString();
String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

String issue_summary = request.getParameter("issue_summary");
String description_help = request.getParameter("description");
String report_obsn = request.getParameter("report_obsn");

System.err.println("issue_summary--"+issue_summary);
System.err.println("description_help--"+description_help);
System.err.println("report_obsn--"+report_obsn);
System.err.println("uploadDacr--"+sus);
TB_AVIATION_UPLOAD_DOC_DACR upload = new TB_AVIATION_UPLOAD_DOC_DACR();

int userid = Integer.parseInt(session.getAttribute("userId").toString());
String fname = "";
if (!uploadDacr.isEmpty()) {
DateWithTimeStampController timestamp = new DateWithTimeStampController();

try { 
byte[] bytes = uploadDacr.getBytes();

CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
if(fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
String avnFilePath = session.getAttribute("avnFilePath").toString();
File dir = new File(avnFilePath);
if (!dir.exists()) {
dir.mkdirs();
}

String filename = uploadDacr.getOriginalFilename();
String extension="";
int i = filename.lastIndexOf('.');
if (i >= 0) {
extension = filename.substring(i+1);
}
fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_AVNDOC."+extension;
File serverFile = new File(fname);                             
BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
stream.write(bytes);                               
stream.close();
upload.setDocument(fname);
upload.setCreatedby(username);
upload.setCreateddatetime(date);
upload.setSus_no(sus);
upload.setStatus("0");

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
session1.save(upload);
String s=create_ticket_avnRPAS(upload,session, issue_summary, description_help, report_obsn);
session1.getTransaction().commit();
session1.close();
list.add("Document Uploaded Successfully");
}else {
list.add("Invalid File Format.");
}
}
catch (Exception e) {
	System.err.println(e);
list.add("an Error ocurre file saving.");
}
} 
return list;
} 

private String create_ticket_avnRPAS(TB_AVIATION_UPLOAD_DOC_DACR upload ,HttpSession session,String issue_summary,String description_help,String report_obsn) {
String msg="";
String user_id = "";
String module_name = "AVIATION";
String sub_module = "RPAS";
String screenname = "DACR FOR RPAS";
List<TB_LDAP_MODULE_MASTER>   module=helpcntl.getModuleNameHelpDeskList(session);
List<TB_LDAP_SUBMODULE_MASTER>   submodule=helpcntl.getSubModuleList(session);
List<TB_LDAP_SCREEN_MASTER> screen=helpcntl. getScreenList(session);
HD_TB_BISAG_TICKET_GENERATE tickect=new HD_TB_BISAG_TICKET_GENERATE();
for(TB_LDAP_MODULE_MASTER a:module)
{
if(a.getModule_name().equals("AVIATION"))
{
tickect.setModule(a.getId()); 
}
}
for(TB_LDAP_SUBMODULE_MASTER b:submodule)
{

if(b.getSubmodule_name().equals("RPAS"))
{
tickect.setSub_module(b.getId());
}

}
for(TB_LDAP_SCREEN_MASTER c:screen)
{
if(c.getScreen_name().equals("DACR FOR RPAS"))
{
tickect.setScreen_name(c.getId());
} 
}
int userId = Integer.parseInt(session.getAttribute("userId").toString());
tickect.setCreated_by(upload.getCreatedby());
tickect.setCreated_on(new Date());
// tickect.setDescription();
tickect.setHelp_topic(report_obsn);
tickect.setIssue_summary(issue_summary);
tickect.setDescription(description_help);
tickect.setUserid(userId);
tickect.setTicket_status("0");
int ticket = helpcntl.getMaxticket();
tickect.setTicket(ticket);
tickect.setscreen_shot(upload.getDocument());
String roleAccess = session.getAttribute("roleAccess").toString();
String roleSusNo = session.getAttribute("roleSusNo").toString();
String username = session.getAttribute("username").toString();
if (roleAccess.equals("Unit")) {
tickect.setSus_no(roleSusNo);
} 
tickect.setCreated_on(new Date());
String unit_name=notif.getUnitNameFromUserId(userId);
String description="Please Check the Ticket in Manage Ticket Screen";
Integer module_id = tickect.getModule();
List<String> list = notif.getUserIdForNotif(module_id);
for(int i=0;i<list.size();i++) {
user_id+=list.get(i);
if(i<list.size()-1)
user_id+=",";
}

Session session1 = HibernateUtil.getSessionFactory().openSession();
session1.beginTransaction();
int N_id=(int)session1.save(tickect);

if (user_id!="" && !user_id.equals(null) && !user_id.equals("")) {
Boolean d = notification.sendNotification_tms("TICKET GENERATED for " +   module_name +" : " + sub_module + " : " + screenname + " by " + unit_name,description,user_id, username,N_id);
}
//session1.save(tic_gen_noti);
session1.getTransaction().commit();
session1.close();
return msg;
}



}

