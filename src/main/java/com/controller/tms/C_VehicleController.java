package com.controller.tms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.tms.C_VehicleDAO;
import com.dao.tms.tmsCVehicleDAO;
import com.dao.tms.tmsCVehicleDAOImp;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.TB_TMS_EMAR_HISTORY_TABLE;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.UPLOAD_DOCUMENT_EMAR;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class C_VehicleController {

	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	ValidationController validation = new ValidationController();

	@Autowired
	tmsCVehicleDAO cVeh = new tmsCVehicleDAOImp();

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private notificatioDAO     notif;
    helpController helpcntl=new helpController();
    
    NotificationController notification = new NotificationController();
    
	@RequestMapping(value = "/admin/cvehReportActivityForm", method = RequestMethod.GET)
	public ModelAndView cvehReportActivityForm(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cvehReportActivityForm", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("cvehReportActivityTile");
	}

	@RequestMapping(value = "/admin/ReportSearchCVehicleActivityReport", method = RequestMethod.POST)
	public ModelAndView ReportSearchCVehicleActivityReport(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "status1", required = false) String status) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cvehReportActivityForm", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:cvehReportActivityForm");
			}

			List<Map<String, Object>> list = cVeh.getReportSearchConvertREQ(sus_no,status);
			Mmap.put("sus_no", sus_no);
			Mmap.put("status", status);
			Mmap.put("list", list);
			String main_status = "";
			for (Map<String, Object> map : list) {
				main_status = (String) map.get("main_status");
				break;
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			Mmap.put("roleType", roleType);
			Mmap.put("main_status", main_status);
		} else {
			Mmap.put("msg", "Please select SUS No.");
		}
		return new ModelAndView("cvehReportActivityTile");
	}

	@RequestMapping(value = "/admin/UpdateReportSearchCVehicleActivityReport", method = RequestMethod.POST)
	public ModelAndView UpdateReportSearchCVehicleActivityReport(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		String Type_Veh = "C";
		Mmap.put("getMotherDepoList11", getMotherDepoList(Type_Veh, sessionA));
		String role = sessionA.getAttribute("roleType").toString();
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:unit_details");
			}
			
			ArrayList<ArrayList<String>> list = cVeh.UpdateReportSearchConvertREQ(sus_no, role,roleAccess);
			if (list.size() == 0) {
				Mmap.put("sus_no", sus_no);
				Mmap.put("list", list);
			} else {
				Mmap.put("sus_no", sus_no);
				Mmap.put("list", list);
				String con = "";
				for (int i = 0; i < list.size(); i++) {
					String ProcFrom = list.get(i).get(28);
					if (ProcFrom == null || ProcFrom.equals("") || ProcFrom.equals(null)) {
						con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('WE') ;\n";
					} else {
						con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('" + list.get(i).get(28) + "') ;\n";
					}
				}
				String unsv = "";
				for (int i = 0; i < list.size(); i++) {
					String unsv1 = list.get(i).get(29);
					if (unsv1 == null || unsv1.equals("") || unsv1.equals(null)) {
						unsv += "$('select#unsv" + list.get(i).get(6) + "').val('No') ;\n";
					} else {
						unsv += "$('select#unsv" + list.get(i).get(6) + "').val('" + list.get(i).get(29) + "') ;\n";
					}
				}
				String classi = "";
				for (int i = 0; i < list.size(); i++) {
					classi += "$('select#classification" + list.get(i).get(6) + "').val(" + list.get(i).get(9) + ") \n";
					if (list.get(i).get(9) == null) {
						classi += "$('select#classification" + list.get(i).get(6) + "').val('1') \n";
					}
				}
				int TOTAL = Integer.parseInt(list.get(list.size() - 1).get(43));
				int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(44));
				int NotUpdated = TOTAL - UPDATE;
				
				String main_status1 = list.get(0).get(43);
				Mmap.put("main_status", main_status1);
				Mmap.put("roleType", role);
				Mmap.put("con", con);
				Mmap.put("unsv", unsv);
				Mmap.put("classi", classi);
				Mmap.put("TOTAL", TOTAL);
				Mmap.put("UPDATE", UPDATE);
				Mmap.put("NotUpdated", NotUpdated);
			}
		} else {
			Mmap.put("msg", "Please Select SUS No.");
		}
		return new ModelAndView("unit_detailsTile");
	}

	@RequestMapping(value = "/admin/ApproveEARreport", method = RequestMethod.POST)
	public ModelAndView ApproveEARreport(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "appsus_no", required = false) String sus_no) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("cvehReportActivityForm", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		String Type_Veh = "C";
		Mmap.put("getMotherDepoList11", getMotherDepoList(Type_Veh, sessionA));
		String role = sessionA.getAttribute("roleType").toString();
		if (!sus_no.equals("") & sus_no.length() == 8) {
			
			
			
			String Save_history=Save_History(sus_no,sessionA);

			String username = sessionA.getAttribute("username").toString();

			Session session3 = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx3 = session3.beginTransaction();
			String hql1 = "update TB_TMS_EMAR_REPORT_MAIN  set approve_date=:approve_date,approved_by=:approved_by,status=:status  where sus_no=:sus_no ";
			Query updatedEntities2 = session3.createQuery(hql1);
			updatedEntities2.setParameter("sus_no", sus_no);
			updatedEntities2.setParameter("approve_date", new Date());
			updatedEntities2.setParameter("approved_by", username);
			updatedEntities2.setParameter("status", "1");
			updatedEntities2.executeUpdate();
			tx3.commit();
			// UPDATE ON TB_TMS_EMAR_REPORT
			Transaction tx4 = session3.beginTransaction();
			String hql2 = "update TB_TMS_EMAR_REPORT  set approve_date=:approve_date,approved_by=:approved_by,status=:status  where sus_no=:sus_no";
			Query updatedEntities = session3.createQuery(hql2);
			updatedEntities.setParameter("approve_date", new Date());
			updatedEntities.setParameter("approved_by", username);
			updatedEntities.setParameter("sus_no", sus_no);
			updatedEntities.setParameter("status", "1");
			updatedEntities.executeUpdate();

			tx4.commit();
			session3.close();

		} else {
			Mmap.put("msg", "Please Select SUS No.");
		}
		return new ModelAndView("cvehReportActivityTile");
	}
	
	
	
//	@RequestMapping(value = "/admin/ApproveEARreport", method = RequestMethod.POST)
//	public ModelAndView ApproveEARreport(ModelMap Mmap, HttpSession sessionA,
//			@RequestParam(value = "msg", required = false) String msg,
//			@RequestParam(value = "appsus_no", required = false) String sus_no) throws ParseException {
//		String roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("cvehReportActivityForm", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		String roleAccess = sessionA.getAttribute("roleAccess").toString();
//		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
//		if (roleAccess.equals("Unit")) {
//			sus_no = roleSusNo;
//		}
//		String Type_Veh = "C";
//		Mmap.put("getMotherDepoList11", getMotherDepoList(Type_Veh, sessionA));
//		String role = sessionA.getAttribute("roleType").toString();
//		if (!sus_no.equals("") & sus_no.length() == 8) {
//			String Save_history=Save_History(sus_no,sessionA);
//			String username = sessionA.getAttribute("username").toString();
//
//			Session session3 = HibernateUtilNA.getSessionFactory().openSession();
//			Transaction tx3 = session3.beginTransaction();
//			String hql1 = "update TB_TMS_EMAR_REPORT_MAIN  set approve_date=:approve_date,approved_by=:approved_by,status=:status  where sus_no=:sus_no ";
//			Query updatedEntities2 = session3.createQuery(hql1);
//			updatedEntities2.setParameter("sus_no", sus_no);
//			updatedEntities2.setParameter("approve_date", new Date());
//			updatedEntities2.setParameter("approved_by", username);
//			updatedEntities2.setParameter("status", "1");
//			updatedEntities2.executeUpdate();
//			if(Save_history.equals("Data saved successfully."))
//			{
//				
//				tx3.commit();
//				
//			}
//			else {
//				tx3.rollback();
//				
//			}
//		
//			// UPDATE ON TB_TMS_EMAR_REPORT
//			Transaction tx4 = session3.beginTransaction();
//			String hql2 = "update TB_TMS_EMAR_REPORT  set approve_date=:approve_date,approved_by=:approved_by,status=:status  where sus_no=:sus_no";
//			Query updatedEntities = session3.createQuery(hql2);
//			updatedEntities.setParameter("approve_date", new Date());
//			updatedEntities.setParameter("approved_by", username);
//			updatedEntities.setParameter("sus_no", sus_no);
//			updatedEntities.setParameter("status", "1");
//			updatedEntities.executeUpdate();
//
//if(Save_history.equals("Data saved successfully."))
//{
//	
//	tx4.commit();
//	session3.close();
//}
//else {
//	tx4.rollback();
//	session3.close();
//}
//			
//			
//		
//		} else {
//			Mmap.put("msg", "Please Select SUS No.");
//		}
//		return new ModelAndView("cvehReportActivityTile");
//	}
	
	public String Save_History(String sus_no, HttpSession sessionA) {
	    String msg = "";

	    String username = sessionA.getAttribute("username").toString();
	    List<Map<String, Object>> list = cVeh.getReportSearchConvertREQ(sus_no, "0");
	    Session sessionMvcr = HibernateUtil.getSessionFactory().openSession();
	    Transaction txMvcr = sessionMvcr.beginTransaction();

	    try {
	        for (Map<String, Object> map : list) {
	        	TB_TMS_EMAR_HISTORY_TABLE fmvcr = new TB_TMS_EMAR_HISTORY_TABLE();
	            fmvcr.setEr_id((int) map.get("id"));
	            fmvcr.setSus_no((String) map.get("sus_no"));
	            fmvcr.setEm_no((String) map.get("em_no"));
	            fmvcr.setProc_from((String) map.get("proc_from"));
	            fmvcr.setClassification((String) map.get("classification"));
//	            fmvcr.setServiceable((String) map.get("seviceable"));
	            fmvcr.setReasons((String) map.get("reasons"));
	            fmvcr.setCurrent_km_run(getBigIntegerValue(map.get("current_km_run")));
	            fmvcr.setPrev_km_run(getBigIntegerValue(map.get("prev_km_run")));
	            fmvcr.setTotal_km_run(getBigIntegerValue(map.get("total_km_run")));
	            fmvcr.setStatus((String) map.get("status"));
	            fmvcr.setApproved_by((String) map.get("approved_by"));
	            fmvcr.setApprove_date(parseDate(map.get("approve_date")));
	            fmvcr.setCreation_date(new Date());
	            fmvcr.setModify_by((String) map.get("modify_by"));
	            fmvcr.setModify_date(parseDate(map.get("modify_date")));
	            fmvcr.setCreation_by(username);
	            fmvcr.setServiceable((String) map.get("seviceable"));
	            fmvcr.setBalh_for((String) map.get("balh_for"));
	            fmvcr.setPers((String) map.get("pers"));
	            fmvcr.setRispares((String) map.get("rispares"));
	            if( map.get("status").equals("0")){
	            	fmvcr.setData_updated(1);
	            };
	            sessionMvcr.save(fmvcr);
	        }
	        txMvcr.commit();
	        msg = "Data saved successfully.";
	    } catch (Exception e) {
	        if (txMvcr != null) {
	            txMvcr.rollback();
	        }
	        msg = "Failed to save data: " + e.getMessage();
	    } finally {
	        if (sessionMvcr != null) {
	            sessionMvcr.close();
	        }
	    }

	    return msg;
	}
	private BigInteger getBigIntegerValue(Object value) {
	    if (value instanceof BigDecimal) {
	        return ((BigDecimal) value).toBigInteger();
	    } else if (value instanceof BigInteger) {
	        return (BigInteger) value;
	    } else {
	        return null;
	    }
	}

	private Date parseDate(Object value) throws ParseException {
	    if (value instanceof String) {
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	        return format.parse((String) value);
	    } else if (value instanceof Date) {
	        return (Date) value;
	    } else {
	        return null;
	    }
	}

	

	//changes by Mitesh (10-02-24)
		@RequestMapping(value = "/admin/UpdateEARreport", method = RequestMethod.POST)
		public ModelAndView UpdateEARreport(@ModelAttribute("updateid") int updateid, @ModelAttribute("catid") String catid,
				@ModelAttribute("ProcFrom") String ProcFrom, @ModelAttribute("current") String current,
				@ModelAttribute("Balh_for") String Balh_for, @ModelAttribute("oh_state") String oh_state,
				@ModelAttribute("date_of_oh") String date_of_oh, @ModelAttribute("Rispares") String Rispares,
				@ModelAttribute("pers") String pers, @ModelAttribute("unsv") String unsv,
				@ModelAttribute("prev") String prev, @ModelAttribute("Depo") String Depo,
				@ModelAttribute("em_no") String em_no, @ModelAttribute("ser_reason") String remarks,
				@ModelAttribute("DRR") String DRR, @ModelAttribute("sus_no2") String sus_no,
				@ModelAttribute("classi") String classi, @ModelAttribute("spareDemand1") String spareDemand, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionA) {
			
		

			String role = sessionA.getAttribute("roleType").toString();
			String roleType = sessionA.getAttribute("roleType").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			if (roleAccess.equals("Unit")) {
				sus_no = roleSusNo;
			}

			Session sessionMvcr = HibernateUtil.getSessionFactory().openSession();
			Transaction txMvcr = sessionMvcr.beginTransaction();
			Query qMvcr = sessionMvcr.createQuery("from TB_TMS_EMAR_REPORT where em_no =:em_no and sus_no=:sus_no");
			qMvcr.setParameter("em_no", em_no);
			qMvcr.setParameter("sus_no", sus_no);
			@SuppressWarnings("unchecked")
			List<TB_TMS_EMAR_REPORT> ListMvcrC = (List<TB_TMS_EMAR_REPORT>) qMvcr.list();
			txMvcr.commit();
			int version = ListMvcrC.get(0).getVersion();	
			int updateversion = version+1;
			sessionMvcr.close();
			
			
			if (ListMvcrC.size() == 0) {
				model.put("msg", "Invalid Data Input.");
			}

			String username = sessionA.getAttribute("username").toString();
			int row = 0;
			Pattern pattern = Pattern.compile(".*[^0-9].*");
			if (!pattern.matcher(current).matches() == false) {
				model.put("msg", "Please Enter Numeric Value for Hrs Run in Current.");
			} 
		else {
				/*if (oh_state.equals("Yes")) {
					if (unsv == "No" || unsv.equals("No")) {
						model.put("msg", "Please Update UnServicable.");
					} else {
							Session sessionGet2 = HibernateUtilNA.getSessionFactory().openSession();
							Transaction tx2 = sessionGet2.beginTransaction();
							Query q = sessionGet2.createQuery("delete from TB_TMS_EMAR_REPORT where em_no in(select em_no from TB_TMS_EMAR_DRR_DIR_DTL where unit_sus_no=:sus_no and em_no=:em_no )");
							q.setParameter("sus_no", sus_no).setParameter("em_no", em_no);
							q.executeUpdate();
							tx2.commit();
							sessionGet2.close();
							
							Session sessionemar_maindt = HibernateUtil.getSessionFactory().openSession();
							Transaction txemar_main = sessionemar_maindt.beginTransaction();
							Query q_emar_main = sessionemar_maindt.createQuery("select id from TB_TMS_EMAR_REPORT where sus_no=:sus_no and status = '1'");
							q_emar_main.setParameter("sus_no", sus_no);
							@SuppressWarnings("unchecked")
							List<TB_TMS_EMAR_REPORT> getlistexistsus_no = (List<TB_TMS_EMAR_REPORT>) q_emar_main.list();
							txemar_main.commit();
							sessionemar_maindt.close();
							
							if (getlistexistsus_no.size() == 0) {
								Session sessionGetdt = HibernateUtilNA.getSessionFactory().openSession();
								Transaction tx31 = sessionGetdt.beginTransaction();
								Query qdt = sessionGetdt.createQuery("delete from TB_TMS_EMAR_REPORT_MAIN where sus_no =:sus_no");
								qdt.setParameter("sus_no", sus_no);
								qdt.executeUpdate();
								tx31.commit();
								sessionGetdt.close();
							}
							
							Session session1 = HibernateUtilNA.getSessionFactory().openSession();
							Transaction tx = session1.beginTransaction();
							String hqlUpdate = "update TB_TMS_EMAR_DRR_DIR_DTL c set c.status = :status, c.approved_by = :approved_by, c.approved_date = :approved_date,c.issue_condition = :issue_condition,c.remarks = :remarks,sus_no = :sus_no,c.classification = :classification where c.unit_sus_no = :unit_sus_no  and c.em_no = :em_no";
							int app = session1.createQuery(hqlUpdate).setString("status", "1")
									.setString("approved_by", username).setTimestamp("approved_date", new Date())
									.setString("sus_no", Depo).setString("unit_sus_no", sus_no).setString("em_no", em_no)
									.setString("issue_condition", "3").setString("remarks", remarks)
									.setString("classification", classi).executeUpdate();
							tx.commit();
							session1.close();
							if (app > 0) {
								model.put("msg", "Overhaul Approved Successfully!");
							} else {
								model.put("msg", "Overhaul not Approved!");
							}
							String year = new SimpleDateFormat("yyyy").format(new Date());
							String drr_dir_ser_no = year + "-DRR-" + em_no;
							Date date = new Date();
							String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
							Session sessionemar_drrdt = HibernateUtil.getSessionFactory().openSession();
							Transaction txemar_drr = sessionemar_drrdt.beginTransaction();
							Query q_emar_drr = sessionemar_drrdt.createQuery("select drr_dir_ser_no from TB_TMS_EMAR_DRR_DIR_DTL where drr_dir_ser_no like :drr_dir_ser_no order by SUBSTRING(drr_dir_ser_no,20,1) DESC")
									.setMaxResults(1);
							q_emar_drr.setParameter("drr_dir_ser_no", drr_dir_ser_no + "%");
							@SuppressWarnings("unchecked")
							List<String> getlistexistdrrSerNo = (List<String>) q_emar_drr.list();
							txemar_drr.commit();
							sessionemar_drrdt.close();
							if (getlistexistdrrSerNo.size() == 0) {
								drr_dir_ser_no = drr_dir_ser_no + "1";
							} else {
								int lstIndexdrrSerNo = Integer
										.parseInt(String.valueOf(getlistexistdrrSerNo.get(0).charAt(19)));

								drr_dir_ser_no = drr_dir_ser_no + (lstIndexdrrSerNo + 1);
							}
							TB_TMS_EMAR_DRR_DIR_DTL emar = new TB_TMS_EMAR_DRR_DIR_DTL();

							BigInteger t = BigInteger.valueOf(0);
							emar.setApprove_date(new Date());
							emar.setApproved_by(username);
							emar.setCreation_by(username);
							emar.setCreation_date(new Date());
							emar.setStatus("1");
							emar.setDrr_dir_ser_no(drr_dir_ser_no);
							emar.setSus_no(Depo);
							emar.setUnit_sus_no(sus_no);
							emar.setIssue_condition("3");
							emar.setEm_no(em_no);
							emar.setKms_run(new BigInteger(Balh_for));
							emar.setClassification(classi);
							emar.setRemarks(remarks);
							emar.setAuthority(date1);
							emar.setOther_agency("");
							Session sessionDtl = HibernateUtil.getSessionFactory().openSession();
							sessionDtl.beginTransaction();
							sessionDtl.save(emar);
							sessionDtl.getTransaction().commit();
							sessionDtl.close();
					}
				} else { */
					Date date_of_oh1 = null;
					if (!date_of_oh.equals(null) && !date_of_oh.equals("") && date_of_oh != "" && date_of_oh != null) {
						DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						try {
							date_of_oh1 = formatter1.parse(date_of_oh);
						} catch (java.text.ParseException e) {
							e.printStackTrace();
						}
					}
					
					ArrayList<ArrayList<String>> list1 = cVeh.UpdateReportSearchConvertREQ(sus_no, role,roleAccess);
				
					
					   Session ses = HibernateUtil.getSessionFactory().openSession();
					    Transaction tx = ses.beginTransaction();
				    Query qry = ses.createQuery("select count(*) from TB_TMS_EMAR_REPORT "
				    	    + "where em_no = :em_no and sus_no = :sus_no "		    	   
				    	    + "and (proc_from = :proc_from or proc_from is null)"
				    	    + "and (date_of_oh = :date_of_oh or date_of_oh is null)"
				    	    + "and (classification = :classification or classification is null)"		    	
				    	    + "and (current_km_run = :current_km_run or current_km_run is null)"
				    	    + "and (Balh_for = :Balh_for or Balh_for is null)"
				    	    + "and (date_of_oh = :date_of_oh or date_of_oh is null) "
			    	        + "and (rispares = :rispares or rispares is null)"
				    	    + "and (pers = :pers or pers is null)"
				    	    + "and (seviceable = :seviceable or seviceable is null)"	
				    	    + "and (ser_reason = :reason or ser_reason is null)"
				    	    
			);

				    BigInteger currentAsBigInteger = new BigInteger(current);
				    qry.setParameter("em_no", em_no);
				    qry.setParameter("sus_no", sus_no);
				    qry.setParameter("proc_from", ProcFrom);
				    qry.setParameter("classification", classi); 
				    qry.setParameter("current_km_run", currentAsBigInteger); 
				    qry.setParameter("Balh_for", Balh_for);
				    qry.setParameter("rispares",Rispares);
				    qry.setParameter("pers",pers);
				    qry.setParameter("seviceable", oh_state);
				    qry.setParameter("date_of_oh", date_of_oh1);
				    qry.setParameter("reason", remarks);

				    	@SuppressWarnings("unchecked")
				    	Long count = (Long) qry.uniqueResult();
				    	
				    	if (count > 0) {
				    		model.put("msg", "Data already exists.");
				    		tx.commit();
							ses.close();	
					     model.put("sus_no", sus_no);
					     model.put("list", list1);
					     
						return new ModelAndView("unit_detailsTile");
				    	}   
				    
				
					BigInteger current1 = new BigInteger(current);
					Session session3 = HibernateUtilNA.getSessionFactory().openSession();
					Transaction tx3 = session3.beginTransaction();
					String hql1 = "update TB_TMS_EMAR_REPORT_MAIN  set approve_date=:approve_date,approved_by=:approved_by ,status=:status where sus_no=:sus_no and status !='0'";
					Query updatedEntities2 = session3.createQuery(hql1);
					updatedEntities2.setParameter("sus_no", sus_no);
					updatedEntities2.setParameter("approve_date", new Date());
					updatedEntities2.setParameter("approved_by", username);
					updatedEntities2.setParameter("status", "0");
					row = updatedEntities2.executeUpdate();
					tx3.commit();
					// UPDATE ON TB_TMS_EMAR_REPORT
					Transaction tx4 = session3.beginTransaction();
					String hql2 = "update TB_TMS_EMAR_REPORT  set oh_state=:oh_state,ser_reason=:reason,proc_from=:proc_from,current_km_run =:current_km_run, Balh_for=:Balh_for,Rispares=:Rispares,pers=:pers,seviceable=:seviceable,date_of_oh=:date_of_oh,prev_km_run=:prev_km_run,modify_by=:modify_by,modify_date=:modify_date,classification=:classification,total_km_run=:total_km_run,spare_demand=:spare_demand,approve_date=:approve_date,approved_by=:approved_by,status=:status,version=:version  where id=:id";
					Query updatedEntities = session3.createQuery(hql2);
					updatedEntities.setParameter("id", updateid);
					updatedEntities.setParameter("proc_from", ProcFrom);
					updatedEntities.setParameter("current_km_run", current1);
					updatedEntities.setParameter("Balh_for", Balh_for);
					updatedEntities.setParameter("Rispares", Rispares);
					updatedEntities.setParameter("pers", pers);
					updatedEntities.setParameter("date_of_oh", date_of_oh1);
					updatedEntities.setParameter("modify_by", username);
					updatedEntities.setParameter("modify_date", new Date());
					updatedEntities.setParameter("approve_date", new Date());
					updatedEntities.setParameter("approved_by", username);
					updatedEntities.setParameter("classification", classi);
					updatedEntities.setParameter("prev_km_run", BigInteger.ZERO);				
					if (Balh_for.equals("")) {
						updatedEntities.setParameter("total_km_run", Balh_for);
					} else {
						updatedEntities.setParameter("total_km_run", new BigInteger(Balh_for));
					}
					updatedEntities.setParameter("seviceable", oh_state);
					updatedEntities.setParameter("oh_state", oh_state);
					updatedEntities.setParameter("spare_demand", spareDemand);
					updatedEntities.setParameter("status", "0");
					updatedEntities.setParameter("version", updateversion);
					updatedEntities.setParameter("reason", remarks);
					row = updatedEntities.executeUpdate();
					tx4.commit();
					session3.close();
				//}
			}
			ArrayList<ArrayList<String>> list = cVeh.UpdateReportSearchConvertREQ(sus_no, role,roleAccess);
			model.put("sus_no", sus_no);
			model.put("list", list);
			String con = "";
			for (int i = 0; i < list.size(); i++) {
				String ProcFrom1 = list.get(i).get(28);
				if (ProcFrom1 == null || ProcFrom1.equals("") || ProcFrom1.equals(null)) {
					con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('WE') ;\n";
				} else {
					con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('" + list.get(i).get(28) + "') ;\n";
				}
			}
			String unsv1 = "";
			for (int i = 0; i < list.size(); i++) {
				String unsv12 = list.get(i).get(29);
				if (unsv12 == null || unsv12.equals("") || unsv12.equals(null)) {
					unsv1 += "$('select#unsv" + list.get(i).get(6) + "').val('No') ;\n";
				} else {
					unsv1 += "$('select#unsv" + list.get(i).get(6) + "').val('" + list.get(i).get(29) + "') ;\n";
				}
			}
			String classi1 = "";
			for (int i = 0; i < list.size(); i++) {
				classi1 += "$('select#classification" + list.get(i).get(6) + "').val(" + list.get(i).get(9) + ") \n";
				if (list.get(i).get(9) == null) {
					classi1 += "$('select#classification" + list.get(i).get(6) + "').val('1') \n";
				}
			}
			
			/*String oh_stateJS = "";
			for (int i = 0; i < list.size(); i++) {
				
				oh_stateJS += "$('select#state" + list.get(i).get(6) + "').val(" + list.get(i).get(9) + ") \n";
				if (list.get(i).get(9) == null) {
					oh_stateJS += "$('select#state" + list.get(i).get(6) + "').val('1') \n";
				}
			}*/
			
			//String Type_Veh = "C";
			//model.put("getMotherDepoList11", getMotherDepoList(Type_Veh, sessionA));
			model.put("con", con);
			model.put("unsv", unsv1);
			model.put("classi", classi1);
			//model.put("oh_stateJS", oh_stateJS);
			return new ModelAndView("unit_detailsTile");
		}

	@RequestMapping(value = "/admin/unit_details", method = RequestMethod.GET)
	public ModelAndView Unit_details(ModelMap Mmap, HttpSession sessionA, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("unit_details", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String Type_Veh = "C";
		Mmap.put("getMotherDepoList11", getMotherDepoList(Type_Veh, sessionA));
		if (roleAccess.equals("Unit")) {
			Mmap.put("sus_no", roleSusNo);
			Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));
			ArrayList<ArrayList<String>> list = cVeh.UpdateReportSearchConvertREQ(roleSusNo, roleType,roleAccess);
			Mmap.put("list", list);
			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available.");
			} else {
				String con = "";
				for (int i = 0; i < list.size(); i++) {
					String ProcFrom = list.get(i).get(28);
					if (ProcFrom == null || ProcFrom.equals("") || ProcFrom.equals(null)) {
						con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('WE') ;\n";
					} else {
						con += "$('select#ProcFrom" + list.get(i).get(6) + "').val('" + list.get(i).get(28) + "') ;\n";
					}
				}
				String unsv = "";
				for (int i = 0; i < list.size(); i++) {
					String unsv1 = list.get(i).get(29);
					if (unsv1 == null || unsv1.equals("") || unsv1.equals(null)) {
						unsv += "$('select#unsv" + list.get(i).get(6) + "').val('No') ;\n";
					} else {
						unsv += "$('select#unsv" + list.get(i).get(6) + "').val('" + list.get(i).get(29) + "') ;\n";
					}
				}
				String classi = "";
				for (int i = 0; i < list.size(); i++) {
					classi += "$('select#classification" + list.get(i).get(6) + "').val(" + list.get(i).get(9) + ") \n";
					if (list.get(i).get(9) == null) {
						classi += "$('select#classification" + list.get(i).get(6) + "').val('1') \n";
					}
				}
				int TOTAL = Integer.parseInt(list.get(list.size() - 1).get(43));
				int UPDATE = Integer.parseInt(list.get(list.size() - 1).get(44));
				int NotUpdated = TOTAL - UPDATE;
				
				Mmap.put("con", con);
				Mmap.put("unsv", unsv);
				Mmap.put("classi", classi);
				Mmap.put("TOTAL", TOTAL);
				Mmap.put("UPDATE", UPDATE);
				Mmap.put("NotUpdated", NotUpdated);

			}
		}
		Mmap.put("GetHelpTopic", helpcntl.GetHelpTopicT());
		Mmap.put("msg", msg);
		return new ModelAndView("unit_detailsTile");
	}

	@Autowired
	C_VehicleDAO cVehDAO;

	@RequestMapping(value = "Assembly", method = RequestMethod.GET)
	public ModelAndView Assembly(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("AssemblyTile");
	}

	@RequestMapping(value = "Cat_Part", method = RequestMethod.GET)
	public ModelAndView Cat_Part(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("Cat_PartTile");
	}

	@RequestMapping(value = "FMVCR_PartB", method = RequestMethod.GET)
	public ModelAndView FMVCR_PartB(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("FMVCR_PartBTile");
	}

	@RequestMapping(value = "/admin/getFMVCRPartBDataList", method = RequestMethod.POST)
	public ModelAndView getFMVCRPartBDataList(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("FMVCR_PartB", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		if (sus_no != "") {
			if (validation.sus_noLength(sus_no) == false) {
				Mmap.put("msg", validation.sus_noMSG);
				return new ModelAndView("redirect:FMVCR_PartB");
			}

			ArrayList<List<String>> list = cVehDAO.getFMVCRPartBDataList(sus_no);
			Mmap.put("sus_no", sus_no);
			Mmap.put("list", list);

			int sumUE = 0;
			int we = 0;
			int opwks = 0;
			int ascfp = 0;
			int other = 0;
			int totalUH = 0;
			for (int i = 0; i < list.size(); i++) {
				String sumUE1 = (String) list.get(i).get(2);
				if (sumUE1 == null) {
					sumUE1 = "0";
				}
				sumUE = sumUE + Integer.parseInt(sumUE1);
				we = we + Integer.parseInt((String) list.get(i).get(3));
				opwks = opwks + Integer.parseInt((String) list.get(i).get(4));
				ascfp = ascfp + Integer.parseInt((String) list.get(i).get(5));
				other = other + Integer.parseInt((String) list.get(i).get(6));
				totalUH = totalUH + Integer.parseInt((String) list.get(i).get(7));
			}
			Mmap.put("sumUE", sumUE);
			Mmap.put("we", we);
			Mmap.put("opwks", opwks);
			Mmap.put("ascfp", ascfp);
			Mmap.put("other", other);
			Mmap.put("totalUH", totalUH);
			Date date = new Date();
			String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
			Mmap.put("date", date1);
		} else {
			Mmap.put("msg", "Please Select SUS No.");
		}
		return new ModelAndView("FMVCR_PartBTile");
	}

	// *********************************Edit c- vehicle
	// ***************************************************//

	@RequestMapping(value = "/edit_c_veh_activity_report", method = RequestMethod.GET)
	public ModelAndView edit_c_veh_activity_report(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("edit_c_veh_activity_reportTiles", "edit_c_veh_activity_reportCMD",
				new TB_TMS_MVCR_PARTA_DTL());
	}

	// ****************************************End Edit c- vehicle
	// *********************************************//

	public @ResponseBody List<String> getMotherDepoList(String type_of_veh, HttpSession sessionUserId) {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
				"select distinct  m.sus_no,u.unit_name from TB_TMS_MCT_NODAL_DIR_MASTER m, Miso_Orbat_Unt_Dtl u where m.type_of_veh = 'C' and m.sus_no=u.sus_no and u.status_sus_no='Active'");
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list1;
	}
	
	// emar upload doc 
	
	
	@RequestMapping(value = "/admin/uploadDocEMAR", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadDocEMAR(@RequestParam(value = "uploadMvcr", required = false) MultipartFile uploadMvcr, HttpServletRequest request,ModelMap model, HttpSession session, String sus) {
	List<String> list = new ArrayList<>();
	final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 2 MB
	if (uploadMvcr.getSize() > fileSizeLimit) {
	list.add("File size should be less then 2 MB");
	return list;
	}
	String uploadMvcrExt = FilenameUtils.getExtension(uploadMvcr.getOriginalFilename()).toLowerCase();
	if (!uploadMvcrExt.equals("zip") & !uploadMvcrExt.equals("rar")) {
	list.add("Only *.zip or *.rar file extensions allowed");
	return list;
	}
	String username = session.getAttribute("username").toString();
	String issue_summary = request.getParameter("issue_summary");
	String description_help = request.getParameter("description");
	String report_obsn = request.getParameter("report_obsn");
	
	String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	UPLOAD_DOCUMENT_EMAR upload = new UPLOAD_DOCUMENT_EMAR();

	int userid = Integer.parseInt(session.getAttribute("userId").toString());
	String fname = "";
	DateWithTimeStampController timestamp = new DateWithTimeStampController();
	// code modify by Paresh on 05/05/2020
	if (!uploadMvcr.isEmpty()) {
	try {
	byte[] bytes = uploadMvcr.getBytes();
	CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
	if(fileValidation.check_RAR_File(bytes) || fileValidation.check_ZIP_File(bytes)) {
	String tmsFilePath = session.getAttribute("tmsFilePath").toString();
	File dir = new File(tmsFilePath);
	if (!dir.exists()) {
	dir.mkdirs();
	}
	String filename = uploadMvcr.getOriginalFilename();
	String extension = "";
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
	upload.setDocument(fname);

	upload.setCreatedby(username);
	upload.setCreateddatetime(date);
	upload.setSus_no(sus);
	upload.setStatus("0");
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	session1.beginTransaction();
	session1.save(upload);
	session1.getTransaction().commit();
	session1.close();
	String s=create_ticket_tms_c(upload,session, issue_summary, description_help, report_obsn);
	list.add("Document Uploaded Successfully");
	}else {
	list.add("Invalid File Format.");
	}
	} catch (Exception e) {
		System.out.println("errorrrrrr: " + e);
	list.add("an Error ocurre file saving.");
	}
	}
	return list;
	}
	
	private String create_ticket_tms_c(UPLOAD_DOCUMENT_EMAR upload, HttpSession session, String issue_summary,
			String description_help, String report_obsn) {
		String msg = "";
		String user_id = "";
		String module_name = "TRANSPORT";
		String sub_module = "C VEHICLE";
		String screenname = "UNIT DETAIL";
		List<TB_LDAP_MODULE_MASTER> module = helpcntl.getModuleNameHelpDeskList(session);
		List<TB_LDAP_SUBMODULE_MASTER> submodule = helpcntl.getSubModuleList(session);
		List<TB_LDAP_SCREEN_MASTER> screen = helpcntl.getScreenList(session);
		HD_TB_BISAG_TICKET_GENERATE tickect = new HD_TB_BISAG_TICKET_GENERATE();
		for (TB_LDAP_MODULE_MASTER a : module) {
			if (a.getModule_name().equals("TRANSPORT")) {
				tickect.setModule(a.getId());

			}
		}
		for (TB_LDAP_SUBMODULE_MASTER b : submodule) {

			if (b.getSubmodule_name().equals("C VEHICLE")) {
				tickect.setSub_module(b.getId());
			}

		}
		for (TB_LDAP_SCREEN_MASTER c : screen) {
			if (c.getScreen_name().equals("UNIT DETAIL")) {
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
		// String description= module_name +" : " + sub_module + " : " + screen + "\n" +
		// " (i) "+N.getIssue_summary() + "\n" + " (ii) "+N.getDescription();
		String unit_name = notif.getUnitNameFromUserId(userId);
		String description = "Please Check the Ticket in Manage Ticket Screen";
		Integer module_id = tickect.getModule();
		List<String> list = notif.getUserIdForNotif(module_id);
		for (int i = 0; i < list.size(); i++) {
			user_id += list.get(i);
			if (i < list.size() - 1)
				user_id += ",";
		}

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		int N_id = (int) session1.save(tickect);

		if (user_id != "" && !user_id.equals(null) && !user_id.equals("")) {
			Boolean d = notification.sendNotification_tms("TICKET GENERATED for " + module_name + " : " + sub_module
					+ " : " + screenname + " by " + unit_name, description, user_id, username, N_id);
		}
		// session1.save(tic_gen_noti);
		session1.getTransaction().commit();
		session1.close();
		return msg;
	}
	@RequestMapping(value = "/getDocumentEMAR",method = RequestMethod.POST)
	public @ResponseBody List<UPLOAD_DOCUMENT_EMAR> getDocumentEMAR(String sus_no,HttpSession sessionA) {
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if(roleAccess.equals("Unit")){
			sus_no = roleSusNo;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from UPLOAD_DOCUMENT_EMAR where sus_no =:sus_no and status='0'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<UPLOAD_DOCUMENT_EMAR> list = (List<UPLOAD_DOCUMENT_EMAR>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	 @RequestMapping(value = "/admin/getDownloadImageEMAR_Report",method = RequestMethod.POST)
		public ModelAndView getDownloadImageEMAR_Report(@ModelAttribute("id1") int id1,ModelMap model ,HttpSession session,
				HttpServletRequest request,HttpServletResponse response) throws IOException{
			String EXTERNAL_FILE_PATH = "";
			List<UPLOAD_DOCUMENT_EMAR> a=getDocumentEMARImg(id1,session);

			EXTERNAL_FILE_PATH = a.get(0).getDocument();
			File file = null;
		    file = new File(EXTERNAL_FILE_PATH);
		    try{
		    	if(!file.exists()){
		    		//model.put("sus_no1", sus_no1);
		    		return new ModelAndView("redirect:mcr?msg=Sorry. The file you are looking for does not exist");
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
		    	
		    	//Update status UploadDocumentMVCR Table 
		    	String username = session.getAttribute("username").toString();
				String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		    	Session sessionUpdate = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionUpdate.beginTransaction();
				String hqlUpdate = "update UPLOAD_DOCUMENT_EMAR u set u.status=:status,downloadby=:downloadby,downloadon=:downloadon where u.id =:id";
				sessionUpdate.createQuery( hqlUpdate ).setString( "status", "1").setInteger( "id",id1).setString( "downloadby", username).setString( "downloadon", date).executeUpdate();
				tx.commit();
				sessionUpdate.close();
				
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
		    }
		    return new ModelAndView("redirect:unit_details?msg=Download Successfully.");
		}
	 
	 public List<UPLOAD_DOCUMENT_EMAR> getDocumentEMARImg(int id1,HttpSession sessionA) {
			String roleAccess = sessionA.getAttribute("roleAccess").toString();
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q =  null;
			if(roleAccess.equals("Unit")){
				q = session.createQuery("from UPLOAD_DOCUMENT_EMAR where id=:id and sus_no=:sus_no");
				q.setParameter("id", id1);
				q.setParameter("sus_no", roleSusNo);
			}else {
				q = session.createQuery("from UPLOAD_DOCUMENT_EMAR where id=:id ");
				q.setParameter("id", id1);
			}
			@SuppressWarnings("unchecked")
			List<UPLOAD_DOCUMENT_EMAR> list = (List<UPLOAD_DOCUMENT_EMAR>) q.list();
			tx.commit();
			session.close();
			return list;
		}
	 
	 
	 

	 @RequestMapping(value = "/getExcelFMVCR", method = RequestMethod.POST)
		public ModelAndView getExcelFMVCR(HttpServletRequest request, ModelMap model, HttpSession session,
				String typeReport1, @RequestParam(value = "sus_noex", required = false) String sus_no,
				@RequestParam(value = "unit_nameex", required = false) String unit_name,
				@RequestParam(value = "msg", required = false) String msg) {
		 	
		 	String roleAccess = session.getAttribute("roleAccess").toString();
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("unit_details", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			ArrayList<List<String>> Excel = cVeh.getFMVCRList(sus_no, roleAccess);

			List<String> TH = new ArrayList<String>();
			
			TH.add("EM No");
			TH.add("Proc From");
			TH.add("Classification");
			TH.add("Serviceable");
			TH.add("Reasons");
			TH.add("Spare Demand");
			TH.add("Current Hrs");
			TH.add("Previous Hrs");
			TH.add("Total Hrs");
			TH.add("OH State");
			TH.add("Date Of OH");
			TH.add("Proc");
			TH.add("Old BA No");	
			TH.add("Engine No");
			TH.add("Chassis No");
			TH.add("MCT No");
			TH.add("Std Nomenclature");
			TH.add("Date Of Induction");
			TH.add("Unit Name");
			
			TH.add("OH 1 Due");
			TH.add("OH 2 Due");
			TH.add("OH 3 Due");
			
			
			TH.add("Type of Engine (OE/ OH)");
			TH.add("Engine Failure at");
			TH.add("Hydraullic Sys Type");
			TH.add("Hydraullic Sys Serviceability Status");
			
			TH.add("Work Attachement Serviceability (S/UNSV)");
			TH.add("Main Clutch Serviceability (S/USNV)");
			TH.add("Under Carriage Assy Serviceability (S/UNSV)");
			TH.add("Final Drive Serviceability (S/UNSV)");
			TH.add("Electrical Sys Serviceability (S/UNSV)");
			TH.add("Steering & Brake Sys Serviceability (S/UNSV)");


			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Export_C_Vehicle_Excel("L", TH, username, Excel), "userList", Excel);
		}
	 
	 
	 @RequestMapping(value = "/exportFMVCRExcelAction", method = RequestMethod.POST)
		public ModelAndView exportFMVCRExcelAction(
				@RequestParam(value = "file_browser", required = false) MultipartFile file_browser,
				HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		 	
		 	String numberOfColumns = request.getParameter("numberOfColumns");
		 	String ColumnNameExists = request.getParameter("ColumnNameExists");
		 	LocalDateTime now = LocalDateTime.now();
         Timestamp timestamp = Timestamp.valueOf(now);
		 	
		 	if (numberOfColumns.equals("32") && ColumnNameExists.equals("true")) {
		 	
			String table_name = request.getParameter("table_name");
			int countrow = Integer.parseInt(request.getParameter("countrow"));
			String username = session.getAttribute("username").toString();
			String sus_no = request.getParameter("sus_no3");
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			ArrayList<List<String>> Excel = cVeh.getFullFMVCRList(sus_no, roleAccess);
			
			SimpleDateFormat formatter_dash = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			
				for (int i = 0; i < countrow; i++) {
					
					String em_no = request.getParameter("EM No_" + i);
					String month = new SimpleDateFormat("MM-yyyy").format(new Date());
					if(cVeh.checkDataExists(em_no,month,roleSusNo,table_name) > 0) {
						
						String type_of_engine = request.getParameter("Type of Engine (OE/ OH)_" + i);
						if(type_of_engine == "" || type_of_engine == null || type_of_engine == "undefined" || type_of_engine.trim().equals("undefined")) {
							type_of_engine = null;
						}
						
						String engineFailureAtStr = request.getParameter("Engine Failure at_" + i);
						int engineFailureAt = 0;

						try {
						    if (engineFailureAtStr != null && !engineFailureAtStr.isEmpty()) {
						        engineFailureAt = Integer.parseInt(engineFailureAtStr); 
						        if (engineFailureAt < 0) {
						        	model.put("msg", "Engine Failure At Cannot Be Negative");
						        	return new ModelAndView("redirect:unit_details");
						        }
						    } 
						} catch (NumberFormatException e) {
							model.put("msg", "Engine Failure At Can Only Be Numeric");
							return new ModelAndView("redirect:unit_details");
						}
						
						String engine_failure_at = request.getParameter("Engine Failure at_" + i);
						if(engine_failure_at == "" || engine_failure_at == null) {
							engine_failure_at = "0";
						}
						
						String hydraullic_sys_type = request.getParameter("Hydraullic Sys Type_" + i);
						if(hydraullic_sys_type == "" || hydraullic_sys_type == null || hydraullic_sys_type == "undefined" || hydraullic_sys_type.trim().equals("undefined")) {
							hydraullic_sys_type = null;
						}
						
						String hydraullic_sys_ser_status = request.getParameter("Hydraullic Sys Serviceability Status_" + i);
						if(hydraullic_sys_ser_status == "" || hydraullic_sys_ser_status == null || hydraullic_sys_ser_status == "undefined" || hydraullic_sys_ser_status.trim().equals("undefined")) {
							hydraullic_sys_ser_status = null;
						}
						
						String work_att_ser = request.getParameter("Work Attachement Serviceability (S/ UNSV)_" + i);
						if(work_att_ser == "" || work_att_ser == null || work_att_ser == "undefined" || work_att_ser.trim().equals("undefined")) {
							work_att_ser = null;
						}
						
						String main_clutch_ser = request.getParameter("Main Clutch Serviceability (S/ UNSV)_" + i);
						if(main_clutch_ser == "" || main_clutch_ser == null || main_clutch_ser == "undefined" || main_clutch_ser.trim().equals("undefined")) {
							main_clutch_ser = null;
						}
						
						String under_carriage_assy_ser = request.getParameter("Under Carriage Assy Serviceability (S/ UNSV)_" + i);
						if(under_carriage_assy_ser == "" || under_carriage_assy_ser == null || under_carriage_assy_ser == "undefined" || under_carriage_assy_ser.trim().equals("undefined")) {
							under_carriage_assy_ser = null;
						}
						
						String final_drive_ser = request.getParameter("Final Drive Serviceability (S/ UNSV)_" + i);
						if(final_drive_ser == "" || final_drive_ser == null || final_drive_ser == "undefined" || final_drive_ser.trim().equals("undefined")) {
							final_drive_ser = null;
						}
						
						String electrical_sys_ser = request.getParameter("Electrical Sys Serviceability (S/ UNSV)_" + i);
						if(electrical_sys_ser == "" || electrical_sys_ser == null || electrical_sys_ser == "undefined" || electrical_sys_ser.trim().equals("undefined")) {
							electrical_sys_ser = null;
						}
						
						String steer_brake_sys_ser = request.getParameter("Steering & Brake Sys Serviceability (S/ UNSV)_" + i);
						if(steer_brake_sys_ser == "" || steer_brake_sys_ser == null || steer_brake_sys_ser == "undefined" || steer_brake_sys_ser.trim().equals("undefined")) {
							steer_brake_sys_ser = null;
						}
						
						String proc_from = request.getParameter("Proc From_" + i);
						if(proc_from == "" || proc_from == null || proc_from == "undefined" || proc_from.trim().equals("undefined")) {
							proc_from = null;
						}
						
						String classification = request.getParameter("Classification_" + i);
						if(classification == "" || classification == null || classification == "undefined" || classification.trim().equals("undefined")) {
							classification = null;
						}
						
						String serviceable = request.getParameter("Serviceable_" + i);
						if(serviceable == "" || serviceable == null || serviceable == "undefined" || serviceable.trim().equals("undefined")) {
							serviceable = null;
						}
						
						String reasons = request.getParameter("Reasons_" + i);
						if(reasons == "" || reasons == null || reasons == "undefined" || reasons.trim().equals("undefined")) {
							reasons = null;
						}
						
						String spare_demand = request.getParameter("Spare Demand_" + i);
						if(spare_demand == "" || spare_demand == null || spare_demand == "undefined" || spare_demand.trim().equals("undefined")) {
							spare_demand = null;
						}
						
						String current_km_run = request.getParameter("Current Hrs_" + i);
						String prev_km_run = request.getParameter("Previous Hrs_" + i);
						String total_km_run = request.getParameter("Total Hrs_" + i);
						String oh_state = request.getParameter("OH State_" + i);
						if(oh_state == "" || oh_state == null || oh_state == "undefined" || oh_state.trim().equals("undefined")) {
							oh_state = null;
						}
						
						Date date_of_oh = null;
						String date_of_oh1 = request.getParameter("Date Of OH_" + i);
						if (date_of_oh1 != null && !date_of_oh1.trim().isEmpty() && date_of_oh1!="undefined" && !date_of_oh1.trim().equals("undefined")) {
							date_of_oh = formatter_dash.parse(date_of_oh1);
						} else {
							date_of_oh = null; 
						}
						
						
						Session session1 = HibernateUtil.getSessionFactory().openSession();
						Transaction tx = session1.beginTransaction();
						Query qry = session1.createQuery("update TB_TMS_EMAR_REPORT set \r\n" + 
								"em_no=:a1,type_of_engine=:a2,engine_failure_at=:a3,hydraullic_sys_type=:a4,hydraullic_sys_ser_status=:a5,work_att_ser=:a6,main_clutch_ser=:a7,\r\n" + 
								"under_carriage_assy_ser=:a8,final_drive_ser=:a9,electrical_sys_ser=:a10,steer_brake_sys_ser=:a11,proc_from=:a12,classification=:a13,serviceable=:a14,\r\n" +
								"reasons=:a15,spare_demand=:a16,current_km_run=:a17,prev_km_run=:a18,total_km_run=:a19,oh_state=:a20,date_of_oh=:a21 where em_no=:a1");
						
						qry.setParameter("a1", em_no);
						qry.setParameter("a2", type_of_engine);
						qry.setParameter("a3", Integer.parseInt(engine_failure_at));
						qry.setParameter("a4", hydraullic_sys_type);
						qry.setParameter("a5", hydraullic_sys_ser_status);
						qry.setParameter("a6", work_att_ser);
						qry.setParameter("a7", main_clutch_ser);
						qry.setParameter("a8", under_carriage_assy_ser);
						qry.setParameter("a9", final_drive_ser);
						qry.setParameter("a10", electrical_sys_ser);
						qry.setParameter("a11", steer_brake_sys_ser);
						
						qry.setParameter("a12", proc_from);
						qry.setParameter("a13", classification);
						qry.setParameter("a14", serviceable);
						qry.setParameter("a15", reasons);
						qry.setParameter("a16", spare_demand);
						qry.setParameter("a17", BigInteger.valueOf(Long.parseLong(current_km_run)));
						qry.setParameter("a18", BigInteger.valueOf(Long.parseLong(prev_km_run)));
						qry.setParameter("a19", BigInteger.valueOf(Long.parseLong(total_km_run)));
						qry.setParameter("a20", oh_state);
						qry.setParameter("a21", date_of_oh);
//						qry.setParameter("a22", roleSusNo);
						
						
						//SAVE
						
						String id = Excel.get(0).get(0);
						
						TB_TMS_EMAR_HISTORY_TABLE emarh = new TB_TMS_EMAR_HISTORY_TABLE();
						emarh.setEm_no(em_no);
						emarh.setEr_id(Integer.parseInt(id));
						emarh.setSus_no(sus_no);
						emarh.setType_of_engine(type_of_engine);
						emarh.setEngine_failure_at(Integer.parseInt(engine_failure_at));
						emarh.setHydraullic_sys_type(hydraullic_sys_type);
						emarh.setHydraullic_sys_ser_status(hydraullic_sys_ser_status);
						emarh.setWork_att_ser(work_att_ser);
						emarh.setMain_clutch_ser(main_clutch_ser);
						emarh.setUnder_carriage_assy_ser(under_carriage_assy_ser);
						emarh.setFinal_drive_ser(final_drive_ser);
						emarh.setElectrical_sys_ser(electrical_sys_ser);
						emarh.setSteer_brake_sys_ser(steer_brake_sys_ser);
						emarh.setProc_from(proc_from);
						emarh.setClassification(classification);
						emarh.setServiceable(serviceable);
						emarh.setReasons(reasons);
						emarh.setSpare_demand(spare_demand);
						
						BigInteger bigIntCurrentKmRun = new BigInteger(current_km_run);
						emarh.setCurrent_km_run(bigIntCurrentKmRun);
						
						BigInteger bigIntPrevKmRun = new BigInteger(prev_km_run);
						emarh.setPrev_km_run(bigIntPrevKmRun);
						
						BigInteger bigIntTotalKmRun = new BigInteger(total_km_run);
						emarh.setTotal_km_run(bigIntTotalKmRun);
						
						emarh.setOh_state(oh_state);
						emarh.setDate_of_oh(date_of_oh);
						emarh.setSave_date(timestamp);
						session1.save(emarh);
						
						qry.executeUpdate();
						tx.commit();
						session1.close();
						
						
					}
				}

				model.put("msg", "Your Data Saved Successfully");
				return new ModelAndView("redirect:unit_details");
			}
			else {
				model.put("msg", "Please Enter Valid Excel File For C Vehicle");
				return new ModelAndView("redirect:unit_details");
			}
		}



}