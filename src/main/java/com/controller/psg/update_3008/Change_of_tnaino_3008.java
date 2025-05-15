package com.controller.psg.update_3008;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.update_census_data.SeniorityDAO;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_TNAI_NO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_of_tnaino_3008 {

	Psg_CommonController com = new Psg_CommonController();
	ValidationController validation = new ValidationController();

	@Autowired

	CensusAprovedDAO censusAprovedDAO;
	
	
	@Autowired
	SeniorityDAO SD;


	// save and update change tnaino
			@RequestMapping(value = "/admin/change_of_3008_tnai_action", method = RequestMethod.POST)
			public @ResponseBody String change_of_3008_tnai_action(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Session sessionhql = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionhql.beginTransaction();
				Date date = new Date();
				Date dt_authority = null;

				String username = session.getAttribute("username").toString();
				String authority = request.getParameter("tnai_authority");
				Date comm_date = format.parse(request.getParameter("comm_date"));
				Date change_in_name_dt = null;

				String change_in_tnaino_date = request.getParameter("change_in_tnaino_date");

				String date_of_authority = request.getParameter("tnai_date_of_authority");

				String tnaino = request.getParameter("tnaino");
				BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
				//int census_id = Integer.parseInt(request.getParameter("census_id"));
				int census_id=0;
				String censusIdParameter = request.getParameter("census_id");
				if (censusIdParameter != null && !censusIdParameter.isEmpty()) {
				    census_id = Integer.parseInt(censusIdParameter);		    
				}
				
				String msg = "";

				if (authority == null || authority.equals("")) {
					return "Please Enter Authority ";
				}
				if (!validation.isValidAuth(authority)) {
					return validation.isValidAuthMSG + " Authority";
				}
				if (!validation.isvalidLength(authority, validation.authorityMax, validation.authorityMin)) {
					return "Authority " + validation.isValidLengthMSG;
				}
				if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
						|| date_of_authority.equals("")) {
					return "Please Select Date of Authority";
				}
				if (!validation.isValidDate(date_of_authority)) {
					return validation.isValidDateMSG + " of Authority";
				}
				if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
					dt_authority = format.parse(date_of_authority);
				}
				if (com.CompareDate(dt_authority, comm_date) == 0) {
					return "Authority Date should be Greater than Commission Date";
				}
				if (tnaino == null || tnaino.equals("")) {
					return "Please Enter TNAI No";
				}
			
				
				if (change_in_tnaino_date == null || change_in_tnaino_date.equals("null")
						|| change_in_tnaino_date.equals("DD/MM/YYYY") || change_in_tnaino_date.equals("")) {
					return "Please Select Date";
				}
				if (!change_in_tnaino_date.equals("") && !change_in_tnaino_date.equals("DD/MM/YYYY")) {
					change_in_name_dt = format.parse(change_in_tnaino_date);
				}

				try {
					String hql1 = "update TB_CHANGE_TNAI_NO set status=:status where  comm_id=:comm_id and (status != '0' and status != '-1') ";

					Query query1 = sessionhql.createQuery(hql1).setInteger("status", 2).setBigInteger("comm_id", comm_id);

					msg = query1.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";

				
						TB_CHANGE_TNAI_NO n = new TB_CHANGE_TNAI_NO();
						n.setCreated_by(username);
						n.setCreated_date(date);
						n.setAuthority(authority);
						n.setTnai_no(tnaino);
						n.setDate_of_authority(dt_authority);
						n.setComm_id(comm_id);
						n.setCensus_id(census_id);
						n.setStatus(1);
						n.setCreated_by(username);
						n.setCreated_date(date);
						n.setChange_in_name_date(change_in_name_dt);
						n.setModified_by(username);
						n.setModified_date(date);
						n.setApproved_by(username);
						n.setApproved_date(date);
						
					
						int id = (int) sessionhql.save(n);
						msg = Integer.toString(id);
				
					
					String hql = "update TB_TRANS_PROPOSED_COMM_LETTER set modified_by=:modified_by,modified_date=:modified_date,tnai_no=:tnai_no "
							+ " where id=:comm_id ";

					Query query = sessionhql.createQuery(hql).setString("modified_by", username)
							.setTimestamp("modified_date", new Date()).setBigInteger("comm_id", comm_id)
							.setString("tnai_no", tnaino);
					msg = query.executeUpdate() > 0 ? "update" : "Data Not Approved Successfully";
					
				
					tx.commit();
				} catch (RuntimeException e) {
					try {
						tx.rollback();
						msg = "0";
					} catch (RuntimeException rbe) {
						msg = "0";
					}
				} finally {
					if (sessionhql != null) {
						sessionhql.close();
					}
				}

				return msg;
			}
	

	
	@RequestMapping(value = "/Popup_TnainohistoryUrl_3008", method = RequestMethod.POST)
	public ModelAndView Popup_TnainohistoryUrl_3008(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "comm_id12", required = false) BigInteger comm_id) {
		List<ArrayList<String>> list = SD.Popup_Change_of_tnaino(comm_id);
		Mmap.put("list", list);
		Mmap.put("msg", msg);
		return new ModelAndView("Popup_Tnaino_tiles");
		
	}

}
