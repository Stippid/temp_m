package com.controller.tms;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.ExportFile.Excel_To_Export_1_Table_Report_data_update;
import com.controller.notification.NotificationController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.ReportsDAO;
import com.dao.tms.Capture_rel_orderDAO;
import com.dao.tms.Capture_rel_orderDAOImpl;
import com.dao.tms.DailyReceiptIssuereportDAO;
import com.dao.tms.DailyReceiptIssuereportDAOimpl;
import com.dao.tms.RioDAO;
import com.dao.tms.RioDAOImpl;
import com.models.TB_TMS_CENSUS_DRR_DIR_DTL;
import com.models.TB_TMS_CENSUS_DRR_DIR_MAIN;
import com.models.TB_TMS_CENSUS_RETURN;
import com.models.TB_TMS_CENSUS_RETURN_MAIN;
import com.models.TB_TMS_DRR_DIR_DTL;
import com.models.TB_TMS_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_DRR_DIR_DTL;
import com.models.TB_TMS_EMAR_DRR_DIR_MAIN;
import com.models.TB_TMS_EMAR_REPORT;
import com.models.TB_TMS_EMAR_REPORT_MAIN;
import com.models.TB_TMS_MCT_MASTER;
import com.models.TB_TMS_MVCR_PARTA_DTL;
import com.models.TB_TMS_MVCR_PARTA_MAIN;
import com.models.TB_TMS_RIO_VEHICLE_DTLS;
import com.models.TB_TMS_RO_VEHICLE_DTLS;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class B_VehSearchRIOController {
	@Autowired
	RioDAO rioDAO = new RioDAOImpl();
	@Autowired
	Capture_rel_orderDAO captureDAO;
	@Autowired
	DailyReceiptIssuereportDAO dDao;
	
	AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();

	ValidationController validation = new ValidationController();
	B_VehSearchMVCRController s = new B_VehSearchMVCRController();
	Psg_CommonController comm = new Psg_CommonController();
	///bisag v2 2508022(notification)
	NotificationController notification = new NotificationController();
	
	@Autowired
	RoleBaseMenuDAO roledao;
	
	@Autowired
	ReportsDAO  UOD;

	AllMethodsControllerOrbat tmsSusUnitName = new AllMethodsControllerOrbat();
	B_VehTmsDailyReceiptIssueReportController tmsstdnomen = new B_VehTmsDailyReceiptIssueReportController();

	@RequestMapping(value = "/relissueorder")
	public ModelAndView relissueorder(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg", msg);
		return new ModelAndView("rioTiles");
	}

	@RequestMapping(value = "/getRIOData", method = RequestMethod.POST)
	public ModelAndView getRIOData(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "depot_sus_no1", required = false) String depot_sus_no,
			@RequestParam(value = "depot_name1", required = false) String depot_name,
			@RequestParam(value = "from_date1", required = false) String fdate,
			@RequestParam(value = "to_date1", required = false) String tdate,
			@RequestParam(value = "status1", required = false) String status) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if (!sus_no.equals("") && sus_no.length() == 8) {
			Mmap.put("sus_no", sus_no);
			Mmap.put("unit_name", allOrbat.getActiveUnitNameFromSusNo_Without_Enc(sus_no, sessionA).get(0));
		} else if (sus_no != "" & sus_no != null & !sus_no.equals("") & !sus_no.equals(null)
				& validation.sus_noLength(sus_no) == false) {
			Mmap.put("msg", validation.sus_noMSG);
			return new ModelAndView("redirect:relissueorder");
		}
		if (unit_name.length() > 100 & validation.checkUnit_nameLength(unit_name) == false) {
			Mmap.put("msg", validation.unit_nameMSG);
			return new ModelAndView("redirect:relissueorder");
		}
		
		Mmap.put("depot_sus_no", depot_sus_no);
		Mmap.put("depot_name", depot_name);

		if (!fdate.equals("")) {
			Mmap.put("fdate", fdate);
		}
		if (!tdate.equals("")) {
			Mmap.put("tdate", tdate);
		}
		if (!status.equals("")) {
			Mmap.put("status", status);
		}
		ArrayList<ArrayList<String>> list = rioDAO.getRIOReport(sus_no, fdate, tdate, status, roleType,depot_sus_no,sessionA);
		Mmap.put("list", list);

		return new ModelAndView("rioTiles");
	}

	@RequestMapping(value = "/ViewRelIssueOrderUrl", method = RequestMethod.POST)
	public ModelAndView ViewRelIssueOrderUrl(@Valid @Validated @ModelAttribute("editId") int id,
			HttpSession sessionUserId, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		TB_TMS_RIO_VEHICLE_DTLS list = rioDAO.viewRio(id);
	
	
		model.put("tms_viewrio_masterCmd", list);
		model.put("rio_id",list.getId() );
		//-------------------------------24-08-2020-priti bcz of list null, page is not working---------------------------------//
		if (list != null) {
			
			List<String> nrs = captureDAO.getNRSFromSus(list.getSus_no());
			if(nrs.size() > 0) {
				model.put("nrs", nrs.get(0));
			}
	

			List<String> Unit_nameList = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getSus_no(), sessionUserId);
			model.put("unit_name", Unit_nameList.get(0));
	
			List<String> CommandNameList = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getCommand_sus_no(), sessionUserId);
			if (CommandNameList.size() != 0) {
				model.put("command_sus_no", CommandNameList.get(0));
			}
	
			List<String> DepotList = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getDepot_sus_no(), sessionUserId);
			model.put("depot_sus_no", DepotList.get(0));
			model.put("d_sus_no", rioDAO.viewRio(id).getDepot_sus_no());
			
			List<String> mct_NomanList = tmsstdnomen.getStdNomenListWithoutEncrypt(rioDAO.viewRio(id).getMct(),sessionUserId);
			List<String> list4 = null;
			if (!rioDAO.viewRio(id).getInliuemct().equals(BigInteger.ZERO)) {
				list4 = tmsstdnomen.getStdNomenListWithoutEncrypt(new BigInteger(rioDAO.viewRio(id).getInliuemct().toString()), sessionUserId);
			}
			
			if (mct_NomanList.size() > 0) {
				model.put("mct_nomen", mct_NomanList.get(0));
			}
			
			if (generateRIONo(list.getMct(), rioDAO.viewRio(id).getDepot_sus_no()).size() != 0) {
				model.put("rio_no", generateRIONo(list.getMct(), rioDAO.viewRio(id).getDepot_sus_no()).get(0));
			} else {
				model.put("msg", "RIO No not Generated Properly. (Incorrect MCT No or Depot SUS No)");
			}
	
			if (list4 != null && list4.size() != 0 && !list4.equals(null) && !list4.equals("")) {
				model.put("inlieu_mct_nomen", list4.get(0));
			}
			
			List<TB_TMS_MVCR_PARTA_MAIN> approveDate = rioDAO.getLastUpdateDateUnit(list.getSus_no());
			if (approveDate.size() > 0) {
				String date = new SimpleDateFormat("dd-MM-yyyy").format(approveDate.get(0));
				model.put("last_update_unit_date", date);
			}
			
			model.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
			
		}
		return new ModelAndView("viewrioTiles");
	}





	@RequestMapping(value = "/ViewrioAction", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView ViewrioAction(@ModelAttribute("tms_viewrio_masterCmd") TB_TMS_RO_VEHICLE_DTLS ro,
            HttpServletRequest request, ModelMap model, HttpSession sessionA,
            @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
        String username = sessionA.getAttribute("username").toString();
        String year = new SimpleDateFormat("yyyy").format(new Date());
        int id = Integer.parseInt(request.getParameter("id"));
        String rio_no = request.getParameter("rio_no");
        String sus_no = request.getParameter("sus_no");
        String depot_sus_no = request.getParameter("depot_sus_no1");
        String type_of_issue = request.getParameter("type_of_issue");
        String inliuemctRIO = request.getParameter("inliuemct");
        String accounting = request.getParameter("accounting");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantityhd = Integer.parseInt(request.getParameter("quantityhd"));

        String type_of_vehicle = request.getParameter("type_of_vehicle");

        int remquantity = 0;
        if (quantityhd >= quantity) {
            remquantity = (quantityhd - quantity);
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date valid_up_to = null;
        try {
            valid_up_to = format.parse(request.getParameter("valid_up_to1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String roleAccess = sessionA.getAttribute("roleAccess").toString();
        String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
        if (roleAccess.equals("Unit")) {
            sus_no = roleSusNo;
        }

        String Totalba_no = "";
        for (int i = 1; i <= quantity; i++) {
            if (Totalba_no.equals("")) {
                Totalba_no = request.getParameter("ba_no" + i);
            } else {
                Totalba_no += ", " + request.getParameter("ba_no" + i);
            }
        }

        Session sessionHQL = null;
        Transaction tx = null;
        try {
            sessionHQL = HibernateUtil.getSessionFactory().openSession();
            tx = sessionHQL.beginTransaction();

            String hql1 = "update TB_TMS_RIO_VEHICLE_DTLS set rio_no=:rio_no,ba_no=:ba_no,approved_date=:approved_date,valid_upto=:valid_upto,status=:status,quantity=:quantity,remaining_quantity=:remaining_quantity where id=:id";
            Query query1 = sessionHQL.createQuery(hql1);
            query1.setParameter("rio_no", rio_no);
            query1.setParameter("status", "1");
            query1.setParameter("quantity", quantity);
            query1.setParameter("ba_no", Totalba_no);
            if (quantityhd >= quantity) {
                query1.setParameter("remaining_quantity", remquantity);
            } else {
                int remaining_quantity = Integer.parseInt(request.getParameter("remaining_quantity"));
                query1.setParameter("remaining_quantity", remaining_quantity);
            }
            query1.setDate("valid_upto", valid_up_to);
            query1.setDate("approved_date", new Date());
            query1.setInteger("id", id);
            query1.executeUpdate();
            sessionHQL.flush();
            sessionHQL.clear();

            TB_TMS_RIO_VEHICLE_DTLS riovehdtl = new TB_TMS_RIO_VEHICLE_DTLS();
            TB_TMS_RIO_VEHICLE_DTLS listRio = captureDAO.getRIOVehAllDtls(id);
            if (quantityhd > quantity) {
                riovehdtl.setAccounting(listRio.getAccounting());
                riovehdtl.setClass_vehicle(listRio.getClass_vehicle());
                riovehdtl.setCommand_sus_no(listRio.getCommand_sus_no());
                riovehdtl.setDepot_sus_no(listRio.getDepot_sus_no());
                riovehdtl.setType_of_vehicle(listRio.getType_of_vehicle());

                String inliuemct = listRio.getInliuemct();
                if (inliuemct.equals("")) {
                    riovehdtl.setInliuemct("0");
                } else {
                    riovehdtl.setInliuemct(listRio.getInliuemct());
                }
                riovehdtl.setIssue_precedence(listRio.getIssue_precedence());
                riovehdtl.setIssue_stock(listRio.getIssue_stock());
                riovehdtl.setLoan_auth(listRio.getLoan_auth());
                riovehdtl.setLoan_duration(listRio.getLoan_duration());
                riovehdtl.setMct(listRio.getMct());
                riovehdtl.setOther_issue_type(listRio.getOther_issue_type());
                riovehdtl.setQuantity(remquantity);
                riovehdtl.setRemarks(listRio.getRemarks());
                riovehdtl.setRio_no("00");
                riovehdtl.setVersion_no(listRio.getVersion_no());
                riovehdtl.setType_of_release(listRio.getType_of_release());
                riovehdtl.setType_of_issue(listRio.getType_of_issue());
                riovehdtl.setSus_no(listRio.getSus_no());
                riovehdtl.setStatus("0");
                riovehdtl.setRo_no(listRio.getRo_no());
                riovehdtl.setIssue_date(listRio.getIssue_date());
                riovehdtl.setCreation_date(listRio.getCreation_date());
                riovehdtl.setQuantity_status("Remaining Quantity");
                riovehdtl.setValid_upto(listRio.getValid_upto());
                sessionHQL.save(riovehdtl);
                sessionHQL.flush();
                sessionHQL.clear();
            }

            if (type_of_vehicle.equalsIgnoreCase("B")) {
                TB_TMS_DRR_DIR_MAIN main = new TB_TMS_DRR_DIR_MAIN();
                main.setCreation_by(username);
                main.setCreation_date(new Date());
                main.setDt_of_retrn(new Date());
                main.setDt_of_submsn(new Date());
                main.setStatus("0");
                main.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                main.setSus_no(depot_sus_no);
                main.setVersion_no(0);
                main.setType_of_stock("3");
                main.setStatus("1");
                main.setApproved_by(username);
                main.setApprove_date(new Date());
                int DRRmain = (int) sessionHQL.save(main);
                sessionHQL.flush();
                sessionHQL.clear();

                if (DRRmain > 0) {
                    TB_TMS_DRR_DIR_DTL dtl = new TB_TMS_DRR_DIR_DTL();
                    for (int i = 1; i <= quantity; i++) {
                        String ba_no = request.getParameter("ba_no" + i);
                        dtl.setCreation_by(username);
                        dtl.setCreation_date(new Date());
                        dtl.setStatus("0");
                        dtl.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                        dtl.setSus_no(depot_sus_no);
                        dtl.setTyp_of_retrn("1");
                        dtl.setType_of_issue("0");
                        dtl.setType_of_stock("3");
                        dtl.setIssue_against_rio(rio_no);
                        dtl.setUnit_sus_no(sus_no);
                        dtl.setBa_no(ba_no);
                        dtl.setKms_run(0);
                        dtl.setClassification("1");
                        dtl.setAuthority(listRio.getRo_no());
                        dtl.setApproved_by(username);
                        dtl.setApprove_date(new Date());
                        dtl.setVersion_no(1);
                        dtl.setIssuetype(type_of_issue);
                        sessionHQL.save(dtl);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }

                Query q1 = sessionHQL.createQuery("select count(id) from TB_TMS_MVCR_PARTA_MAIN where sus_no=:sus_no");
                q1.setParameter("sus_no", sus_no);
                Long count2 = (Long) q1.uniqueResult();
                sessionHQL.flush();
                sessionHQL.clear();

                if (count2 == 0) {
                    TB_TMS_MVCR_PARTA_MAIN PartAmain = new TB_TMS_MVCR_PARTA_MAIN();
                    PartAmain.setCreation_by(username);
                    PartAmain.setCreation_date(new Date());
                    PartAmain.setDate_of_mvcr(new Date());
                    PartAmain.setStatus("1");
                    PartAmain.setDt_of_submsn(new Date());
                    PartAmain.setSus_no(sus_no);
                    PartAmain.setVersion_no(0);
                    PartAmain.setStatus("1");
                    PartAmain.setApproved_by(username);
                    PartAmain.setApprove_date(new Date());
                    sessionHQL.save(PartAmain);
                    sessionHQL.flush();
                    sessionHQL.clear();
                }
                for (int i = 1; i <= quantity; i++) {
                    String ba_no = request.getParameter("ba_no" + i);
                    if (dDao.ifExistMVCRBaNo(ba_no) == false) {
                        TB_TMS_MVCR_PARTA_DTL para_dtl = new TB_TMS_MVCR_PARTA_DTL();
                        para_dtl.setSus_no(sus_no);
                        para_dtl.setDate_of_mvcr(new Date());
                        para_dtl.setIssue_type("F");
                        para_dtl.setOther_issue_type(type_of_issue);
                        para_dtl.setBa_no(ba_no);
                        para_dtl.setClassification("1");
                        para_dtl.setKms_run(0);
                        para_dtl.setAuthrty_letter_no(listRio.getRo_no());
                        para_dtl.setRemarks("MISO/TMS: Please Collect the Vehicle From Depot");
                        para_dtl.setCreation_by(username);
                        para_dtl.setCreation_date(new Date());
                        para_dtl.setStatus("1");
                        para_dtl.setInlieu_mct(new BigInteger(inliuemctRIO));
                        para_dtl.setRio_no(rio_no);
                        para_dtl.setApproved_by(username);
                        para_dtl.setApprove_date(new Date());
                        para_dtl.setVersion_no(1);
                        para_dtl.setAccounting(accounting);
                        sessionHQL.save(para_dtl);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }
            }else if (type_of_vehicle.equalsIgnoreCase("A")) {
                TB_TMS_CENSUS_DRR_DIR_MAIN main = new TB_TMS_CENSUS_DRR_DIR_MAIN();
                main.setCreation_by(username);
                main.setCreation_date(new Date());
                main.setDt_of_retrn(new Date());
                main.setDt_of_submsn(new Date());
                main.setStatus("0");
                main.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                main.setSus_no(depot_sus_no);
                main.setVersion_no(0);
                main.setType_of_stock("3");
                main.setStatus("1");
                main.setApproved_by(username);
                main.setApprove_date(new Date());
                int DRRmain = (int) sessionHQL.save(main);
                sessionHQL.flush();
                sessionHQL.clear();

                if (DRRmain > 0) {
                    TB_TMS_CENSUS_DRR_DIR_DTL dtl = new TB_TMS_CENSUS_DRR_DIR_DTL();
                    for (int i = 1; i <= quantity; i++) {
                        String ba_no = request.getParameter("ba_no" + i);
                        dtl.setCreation_by(username);
                        dtl.setCreation_date(new Date());
                        dtl.setStatus("0");
                        dtl.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                        dtl.setSus_no(depot_sus_no);
                        dtl.setIssue_condition("2");
                        dtl.setType_of_stock("3");
                        dtl.setIssue_agnst_rio(rio_no);
                        dtl.setUnit_sus_no(sus_no);
                        dtl.setBa_no(ba_no);
                        dtl.setKms_run(0);
                        dtl.setClassification("1");
                        dtl.setAuthority(listRio.getRo_no());
                        dtl.setApproved_by(username);
                        dtl.setApproved_date(new Date());
                        dtl.setIssue_type(type_of_issue);
                        dtl.setCreation_by(username);
                        dtl.setCreation_date(new Date());
                        dtl.setOther_agency("");
                         
                        sessionHQL.save(dtl);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }

                Query q1 = sessionHQL.createQuery("select count(id) from TB_TMS_CENSUS_RETURN_MAIN where sus_no=:sus_no");
                q1.setParameter("sus_no", sus_no);
                Long count2 = (Long) q1.uniqueResult();
                sessionHQL.flush();
                sessionHQL.clear();

                if (count2 == 0) {
                    TB_TMS_CENSUS_RETURN_MAIN PartAmain = new TB_TMS_CENSUS_RETURN_MAIN();
                    PartAmain.setCreation_by(username);
                    PartAmain.setCreation_date(new Date());
                    PartAmain.setDate_of_cens_retrn(new Date());
                    PartAmain.setStatus("1");
                    PartAmain.setDt_of_submsn(new Date());
                    PartAmain.setSus_no(sus_no);
                    PartAmain.setVersion_no(1);
                    PartAmain.setStatus("1");
                    PartAmain.setApproved_by(username);
                    PartAmain.setApprove_date(new Date());
                    sessionHQL.save(PartAmain);
                    sessionHQL.flush();
                    sessionHQL.clear();
                }
                for (int i = 1; i <= quantity; i++) {
                    String ba_no = request.getParameter("ba_no" + i);
                    if (dDao.ifExistA_VEHBaNo(ba_no) == false) {
                        TB_TMS_CENSUS_RETURN census_rtn = new TB_TMS_CENSUS_RETURN();
                        census_rtn.setSus_no(sus_no);
                        census_rtn.setDate_of_cens_retrn(new Date());
                        census_rtn.setDt_of_submsn(new Date());
                        census_rtn.setIssued_type("F");
                        census_rtn.setBa_no(ba_no);
                        census_rtn.setVehcl_classfctn("1");
                        census_rtn.setVehcl_kms_run(0);
                        census_rtn.setTrack_kms(0);
                        census_rtn.setUnit_remarks("MISO/TMS: Please Collect the Vehicle From Depot");
                        census_rtn.setMiso_remarks("MISO/TMS: Please Collect the Vehicle From Depot");
                        census_rtn.setCreation_by(username);
                        census_rtn.setCreation_date(new Date());
                        census_rtn.setStatus("1");
                        census_rtn.setApproved_by(username);
                        census_rtn.setApprove_date(new Date());
                        census_rtn.setVersion_no(1);
                        census_rtn.setEngine_type("");
                        census_rtn.setEngine_kms_run("0");
                        census_rtn.setEngine_hrs_run("0");
                        census_rtn.setAux_engine_run("");
                        census_rtn.setAux_engn_clasfctn("");
                        census_rtn.setAux_engn_hrs_run(0);
                        census_rtn.setMain_gun_type("");
                        census_rtn.setMain_gun_efc("");
                        census_rtn.setMain_gun_qr("");
                        census_rtn.setSec_gun_type("");
                        census_rtn.setMain_radio_set_nomcltr("");
                        census_rtn.setMain_radio_set("");
                        census_rtn.setMain_radio_set_condn("");
                        census_rtn.setVeh_km_run_period("");
                        census_rtn.setAux_type("");
                        census_rtn.setMain_radio_set_uh("");
                        sessionHQL.save(census_rtn);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }
            }else if (type_of_vehicle.equalsIgnoreCase("C")) {
                TB_TMS_EMAR_DRR_DIR_MAIN main = new TB_TMS_EMAR_DRR_DIR_MAIN();
                main.setCreation_by(username);
                main.setCreation_date(new Date());
                main.setDt_of_retrn(new Date());
                main.setDt_of_submsn(new Date());
                main.setStatus("0");
                main.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                main.setSus_no(depot_sus_no);
                main.setType_of_stock("3");
                main.setStatus("1");
                main.setApproved_by(username);
                main.setApprove_date(new Date());
                int DRRmain = (int) sessionHQL.save(main);
                sessionHQL.flush();
                sessionHQL.clear();

                if (DRRmain > 0) {
                    TB_TMS_EMAR_DRR_DIR_DTL dtl = new TB_TMS_EMAR_DRR_DIR_DTL();
                    for (int i = 1; i <= quantity; i++) {
                        String ba_no = request.getParameter("ba_no" + i);
                        dtl.setCreation_by(username);
                        dtl.setCreation_date(new Date());
                        dtl.setStatus("0");
                        dtl.setDrr_dir_ser_no(year + "-DIR-" + rio_no);
                        dtl.setSus_no(depot_sus_no);
                        dtl.setIssue_condition("2");
                        dtl.setType_of_stock("3");
                        dtl.setIssue_agnst_rio(rio_no);
                        dtl.setUnit_sus_no(sus_no);
                        dtl.setEm_no(ba_no);
                        dtl.setKms_run(new BigInteger("0"));
                        dtl.setClassification("1");
                        dtl.setAuthority(listRio.getRo_no());
                        dtl.setApproved_by(username);
                        dtl.setApproved_date(new Date());
                        dtl.setIssue_type(type_of_issue);
                        dtl.setCreation_by(username);
                        dtl.setCreation_date(new Date());
                        dtl.setOther_agency("");
                         
                        sessionHQL.save(dtl);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }

                Query q1 = sessionHQL.createQuery("select count(id) from TB_TMS_EMAR_REPORT_MAIN where sus_no=:sus_no");
                q1.setParameter("sus_no", sus_no);
                Long count2 = (Long) q1.uniqueResult();
                sessionHQL.flush();
                sessionHQL.clear();

                if (count2 == 0) {
                    TB_TMS_EMAR_REPORT_MAIN emarmain = new TB_TMS_EMAR_REPORT_MAIN();
                    emarmain.setCreation_by(username);
                    emarmain.setCreation_date(new Date());
                    emarmain.setDate_of_mvcr(new Date());
                    emarmain.setDt_of_submsn(new Date());
                    emarmain.setStatus("1");
                    emarmain.setDt_of_submsn(new Date());
                    emarmain.setSus_no(sus_no);
                    emarmain.setVersion_no(1);
                    emarmain.setStatus("1");
                    emarmain.setApproved_by(username);
                    emarmain.setApprove_date(new Date());
                    sessionHQL.save(emarmain);
                    sessionHQL.flush();
                    sessionHQL.clear();
                }
                for (int i = 1; i <= quantity; i++) {
                    String ba_no = request.getParameter("ba_no" + i);
                    if (dDao.ifExistC_VEHBaNo(ba_no) == false) {
                        TB_TMS_EMAR_REPORT emr_rtn = new TB_TMS_EMAR_REPORT();
                        emr_rtn.setSus_no(sus_no);
                        emr_rtn.setEm_no(ba_no);
                        emr_rtn.setClassification("1");
                        emr_rtn.setCurrent_km_run(new BigInteger("0"));
                        emr_rtn.setPrev_km_run(new BigInteger("0"));
                        emr_rtn.setTotal_km_run(new BigInteger("0"));
                        emr_rtn.setAprv_rejec_remarks("MISO/TMS: Please Collect the Vehicle From Depot");
                        emr_rtn.setCreation_by(username);
                        emr_rtn.setCreation_date(new Date());
                        emr_rtn.setStatus("1");
                        emr_rtn.setApproved_by(username);
                        emr_rtn.setApprove_date(new Date());
                        emr_rtn.setVersion_no(1);
                        
                        sessionHQL.save(emr_rtn);
                        sessionHQL.flush();
                        sessionHQL.clear();
                    }
                }
            }
            tx.commit();
            model.put("msg", "Release Issuue Order Generated Successfully.");
        	List<Map<String, Object>> notit=UOD.getformationorbat(sus_no);
	    	String cmd_sus_no = "";
	    	String corps_sus_no  = ""; 
	    	String div_sus_no = "";
	    	String bde_sus_no = "";
	    	String unit_names = "" ;
	    	String type_of_letter ="" ;  
	    	if(notit.get(0).get("cmd")!=null) {
	    		cmd_sus_no = notit.get(0).get("cmd").toString();			
	    	}
	    	if(notit.get(0).get("corps")!=null) {
	    		corps_sus_no = notit.get(0).get("corps").toString();			
	    	}
	    	if(notit.get(0).get("div")!=null) {
	    		div_sus_no = notit.get(0).get("div").toString();			
	    	}
	    	if(notit.get(0).get("bde")!=null) {
	    		bde_sus_no = notit.get(0).get("bde").toString();			
	    	}
			
	    	if(notit.get(0).get("unit_name")!=null) {
	    		unit_names = notit.get(0).get("unit_name").toString();			
	    	}
	    	if (sus_no != null  || !sus_no.trim().equals("")) {
	    	 List<UserLogin> userlist = comm.getUseridlist(sus_no);
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
				   //String per_no = notit.get(0).get("personnel_no").toString();
		            String title = "Release Issue Order Generated" ;
	                String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued" ;
				    Boolean d = notification.sendNotification_tms(title, description,user_id, username,id);
	         		}
	    	}
				    if (cmd_sus_no != null  && !cmd_sus_no.trim().equals("") ) {
				 if( cmd_sus_no != sus_no && !cmd_sus_no.trim().equals(sus_no)) {
					 
				
				    	List<UserLogin> userlist = comm.getUseridlist(cmd_sus_no);
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
					   //String per_no = notit.get(0).get("personnel_no").toString();
			            String title = "Release Issue Order Generated" ;
		                String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued" ;
					    Boolean d = notification.sendNotification_tms(title, description,user_id, username,id);
		         		}
				    }
				    }
				    if (div_sus_no != null  && !div_sus_no.trim().equals("")  ) { 
				    	 if( div_sus_no != sus_no && !div_sus_no.trim().equals(sus_no)) {
				    List<UserLogin> userlist = comm.getUseridlist(div_sus_no);
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
						   //String per_no = notit.get(0).get("personnel_no").toString();
				            String title = "Release Issue Order Generated" ;
			                String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued" ;
						    Boolean d = notification.sendNotification_tms(title, description,user_id, username,id);
			         		}
				    }
				    }
				    if (bde_sus_no != null  && !bde_sus_no.trim().equals("")) {
				    	 if( bde_sus_no != sus_no && !bde_sus_no.trim().equals(sus_no)) {
						    List<UserLogin> userlist = comm.getUseridlist(bde_sus_no);
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
							   //String per_no = notit.get(0).get("personnel_no").toString();
					            String title = "Release Issue Order Generated" ;
				                String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued" ;
							    Boolean d = notification.sendNotification_tms(title, description,user_id, username,id);
				         		}
	    	
				    }
				    }
				    if (corps_sus_no != null  && !corps_sus_no.trim().equals("") ) {
				    	if( corps_sus_no != sus_no && !corps_sus_no.trim().equals(sus_no)) {
            List<UserLogin> userlist = comm.getUseridlist(corps_sus_no);
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
			   //String per_no = notit.get(0).get("personnel_no").toString();
	            String title = "Release Issue Order Generated" ;
                String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued" ;
			    Boolean d = notification.sendNotification_tms(title, description,user_id, username,id);
         		}
				    }
				    
				    }
				 //   for line
				    if (sus_no != null     && !sus_no.trim().equals("")) {
                        List<String> userlist = dDao.getUseridBysusforlinedte(sus_no);
                                                        String user_id = "";
                                            for (int i = 0; i < userlist.size(); i++) {
                                            if(i==0) {
                                            user_id += userlist.get(i);
                                            }
                                            
                                            else {
                                            user_id += ","+userlist.get(i);
                                            }
                                            
             }
                                            if ((user_id!="")) {
                                            String title = "Release Issue Order Generated" ;
                                            String description = ""+quantity+" "+type_of_vehicle+" type of Vehical wise RIO issued";
                        Boolean d = notification.sendNotification_tms(title, description,user_id, username,0);
                                            }
                        }

        } catch (RuntimeException e) {
            try {
                tx.rollback();
                model.put("msg", "roll back transaction");
            } catch (RuntimeException rbe) {
                model.put("msg", "Couldn�t roll back transaction " + rbe);
            }
            throw e;
        } finally {
            if (sessionHQL != null) {
                sessionHQL.close();
            }
        }
        return new ModelAndView("redirect:relissueorder");
    }
	






	
	@RequestMapping(value = "/printRIOUrl", method = RequestMethod.POST)
	public ModelAndView printRIOUrl(@Valid @Validated @ModelAttribute("pId") int id, ModelMap model,
			HttpSession sessionUserId, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		
		TB_TMS_RIO_VEHICLE_DTLS list = rioDAO.viewRio(id);
		
		model.put("tms_viewrio_masterCmd", list);
		List<String> unit_name = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getSus_no(),sessionUserId);
		List<String> command_unit_name = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getCommand_sus_no(), sessionUserId);
		List<String> depot_unit_name = tmsSusUnitName.getActiveUnitNameFromSusNo_Without_Enc(list.getDepot_sus_no(),sessionUserId);
		List<String> mct_noman = tmsstdnomen.getStdNomenListWithoutEncrypt(list.getMct(), sessionUserId);
		List<String> inliue_mct_noman = null;
		if (!rioDAO.viewRio(id).getInliuemct().equals(BigInteger.ZERO)) {
			inliue_mct_noman = tmsstdnomen.getStdNomenListWithoutEncrypt(new BigInteger(list.getInliuemct().toString()), sessionUserId);
		}
		model.put("unit_name", unit_name.get(0));
		model.put("command_sus_no", command_unit_name.get(0));
		model.put("depot_sus_no", depot_unit_name.get(0));
		model.put("mct_nomen", mct_noman.get(0));
		List<String> nrslist = captureDAO.getNRSFromSus(list.getSus_no());
		if(nrslist.size() > 0) {
			model.put("nrs", nrslist.get(0));
		}else {
			model.put("nrs", "");
		}
		if (inliue_mct_noman != null && inliue_mct_noman.size() != 0 && !inliue_mct_noman.equals(null) && !inliue_mct_noman.equals("")) {
			model.put("inlieu_mct_nomen", inliue_mct_noman.get(0));
		}
		model.put("getMvcrpartCissuetypeList", s.getMvcrpartCissuetypeList());
		
		return new ModelAndView("printrioTiles");
	}

	@RequestMapping(value = "/admin/UpdateValidUptoUrl", method = RequestMethod.POST)
	public ModelAndView UpdateValidUptoUrl(@ModelAttribute("upId") int upId,
			@ModelAttribute("ValidUptoVal") String ValidUptoVal, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		model.put("tms_rio_masterCmd", rioDAO.UpdateValidUpto(upId, ValidUptoVal));
		return new ModelAndView("rioTiles");
	}

	@RequestMapping(value = "/getFreeStockBANoList")
	public @ResponseBody List<String> getFreeStockBANoList(BigInteger mct, String d_sus_no,String type_of_vehicle) {		
		if(mct == null || d_sus_no.equals("")) {
			return null;
		}else {
			return rioDAO.getFreeStockBANoList(d_sus_no,mct,type_of_vehicle);
		}
	}

	
	@RequestMapping(value = "/getFreeStockBANo_and_ro_rio_pending_qty")
	public @ResponseBody int getFreeStockBANo_and_ro_rio_pending_qty(BigInteger mct, String d_sus_no,String type_of_vehicle) {		
		if(mct == null || d_sus_no.equals("")) {
			return 0;
		}else {
			return rioDAO.getFreeStockBANo_and_ro_rio_pending_qty(d_sus_no,mct,type_of_vehicle);
		}
	}
	
	@RequestMapping(value = "/generateRIONo")
	public @ResponseBody List<String> generateRIONo(BigInteger mct, String d_sus_no) {

		String RioNo = "";
		List<String> rioList = new ArrayList<String>();

		Session sessionHQL = null;
		Transaction tx = null;
		try {
			sessionHQL = HibernateUtil.getSessionFactory().openSession();
			tx = sessionHQL.beginTransaction();

			// Start Just the year, with 2 digits
			DateFormat df = new SimpleDateFormat("yy");
			String YearlastTwoDigit = df.format(Calendar.getInstance().getTime());
			RioNo += YearlastTwoDigit;
			// end Just the year, with 2 digits

			// Start Get Third Digit
			Query q = sessionHQL.createQuery(" from TB_TMS_MCT_MASTER where status = 'ACTIVE' and mct=:mct ");
			q.setParameter("mct", mct);
			@SuppressWarnings("unchecked")
			List<TB_TMS_MCT_MASTER> list = (List<TB_TMS_MCT_MASTER>) q.list();
			sessionHQL.flush();
			sessionHQL.clear();

			if (list.get(0).getPurpose_of_vehicle().equals("0")) {
				RioNo += "G";
			}
			if (list.get(0).getPurpose_of_vehicle().equals("1")) {
				RioNo += "S";
			}
			// End Get third Digit
			// Start Get Fourth Digit

			Query qDepot = sessionHQL.createQuery("select depot_code from TB_TMS_MCT_NODAL_DIR_MASTER where sus_no=:d_sus_no and type_of_agncy=:type_of_agncy ");
			qDepot.setParameter("type_of_agncy", "Depot");
			qDepot.setParameter("d_sus_no", d_sus_no);
			@SuppressWarnings("unchecked")
			List<String> listDepot = (List<String>) qDepot.list();
			sessionHQL.flush();
			sessionHQL.clear();
			RioNo += listDepot.get(0);
			// End Get Fourth Digit
			
			// Start Get five
			RioNo += "-";
			
			// Start Get six,seven,eight,nine digit
			String maxRio = "";
			Query qMax = sessionHQL.createQuery(
					"SELECT max(SUBSTRING(rio_no,6,4)) FROM TB_TMS_RIO_VEHICLE_DTLS where rio_no like :YearlastTwoDigit and SUBSTRING(rio_no,4,1) = :Depo");
			qMax.setParameter("Depo", listDepot.get(0));
			qMax.setParameter("YearlastTwoDigit", YearlastTwoDigit + "%");
			maxRio = (String) qMax.list().get(0);
			sessionHQL.flush();
			sessionHQL.clear();
			if (maxRio == null) {
				RioNo += "0001";
			} else {
				String serial = maxRio;
				int serialNo = Integer.parseInt(serial) + 1;
				RioNo += String.format("%04d", serialNo);
			}
			// End Get six,seven,eight,nine digit

			// Start get ten Digit
			RioNo += list.get(0).getVeh_class_code();

			if (RioNo.length() == 10) {
				rioList.add(RioNo);
			}
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return rioList;
	}
	
	
	
	/*   NITIN V4 16/11/2022  */
	 @RequestMapping(value = "/Excel_data_rio", method = RequestMethod.POST)
		public ModelAndView Excel_data_rio(HttpServletRequest request,
				ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("status_ex") String status,
				@ModelAttribute("sus_no_ex") String sus_no,
				@ModelAttribute("unit_name_ex") String unit_name,
				@ModelAttribute("depot_sus_no_ex") String depot_sus_no,
				@ModelAttribute("depot_name_ex") String depot_name,
				@ModelAttribute("from_date_ex") String fdate,
				@ModelAttribute("to_date_ex") String tdate,
				@RequestParam(value = "msg", required = false) String msg) {

		    String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("relissueorder", roleid);
			String roleType = session.getAttribute("roleType").toString();
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
		  ArrayList<ArrayList<String>> Excel =rioDAO.getRIOReport_excel(sus_no, fdate, tdate, status, roleType,depot_sus_no,session);
		  
		 List<String> TH = new ArrayList<String>();
			
			TH.add("RIO NO");
			TH.add("RO NO");
			TH.add("Unit name ");
			TH.add("Command  Name");
			TH.add("Nomenclature");
			TH.add("MCT");
			TH.add("Qty");
			TH.add("Qty Status");
			
			
			TH.add("Depot Name");
			TH.add("Type of Issue");
			TH.add("Date of Submission");
			TH.add("Date of Validity");
			TH.add("In Lieu Nomenclature");
			TH.add("UE");
			TH.add("UH");
			TH.add("PRF UE");
			TH.add("PRF UH");
			
			
			String Heading = "\nData Updated Report";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new Excel_To_Export_1_Table_Report_data_update("L", TH, username,Excel), "userList", Excel );
		}
	 
	 /*   NITIN V4 16/11/2022  */
	 
	 
	 @RequestMapping(value = "/getquantitysumpending")
		public @ResponseBody Long getquantitysumpending( String rio_id,String mct,String sus_no) {
			Long result = null;
			   Session sessionHQL = null;
		        Transaction tx = null;
		        try {
		            sessionHQL = HibernateUtil.getSessionFactory().openSession();
		            tx = sessionHQL.beginTransaction();

		            String hql1 = "SELECT COALESCE(SUM(quantity),0) AS qty from TB_TMS_RIO_VEHICLE_DTLS WHERE STATUS='0' and id < :id  and mct= :mct and depot_sus_no=:dep_sus"; // Changed '<' to '<'
		            Query query1 = sessionHQL.createQuery(hql1);
		            query1.setParameter("id", Integer.parseInt(rio_id));
		            query1.setParameter("mct", new BigInteger(mct));
		            query1.setParameter("dep_sus", sus_no);
		            // Changed setString to setParameter

		            @SuppressWarnings("unchecked")
		            List<Long> list = query1.list(); // Changed the type to BigDecimal

		            if (!list.isEmpty()) {
		                result =list.get(0); // Convert BigDecimal to int
		            }
		            
		        } catch (RuntimeException e) {
		            try {
		                tx.rollback();		              
		            } catch (RuntimeException rbe) {		                
		            }
		            throw e;
		        } finally {
		            if (sessionHQL != null) {
		                sessionHQL.close();
		            }
		        }
			return result;
		}

	 

}
