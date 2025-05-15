package com.controller.login;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.models.login.TB_MISO_USER_REGISTRATION;
import com.persistance.util.HibernateUtil;

@Controller
public class User_RegistrationController {
	
	DateWithTimeStampController timestamp = new DateWithTimeStampController();
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/user_registrationUrl", method = RequestMethod.GET)
	public ModelAndView user_registrationUrl(@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession session, ModelMap Mmap) {
		Mmap.put("msg", msg);
		return new ModelAndView("User_RegTile");
	}

	@RequestMapping(value = "/user_registration_action", method = RequestMethod.POST)
	public @ResponseBody String user_registration_action(@RequestParam(value = "doc", required = false) MultipartFile document,ModelMap Mmap, HttpServletRequest request, HttpSession session) {
		String msg = "";
		String extension = "";
		String fname = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			String user_name = request.getParameter("user_name").trim();
			String unit_name = request.getParameter("unit_name").trim();
			String sus_no = request.getParameter("sus_no").trim();
			String army_no = request.getParameter("army_no").trim();
			String rank = request.getParameter("rank").trim();
			String mobile_no = request.getParameter("mobile_no").trim();
			String appointment = request.getParameter("appointment").trim();
			
			if (user_name.trim().equals("")){
				return "Please Enter User Name";
			}
			else if (unit_name.trim().equals("")) {
				return "Please Enter Unit Name";
			}
			else if (sus_no.trim().equals("")){
				return "Please Enter SUS No";
			}
			else if(all.getSusNoActiveValidation(sus_no.trim()).size() == 0) {
				return "Please Enter Active SUS No";
			}
			else if (army_no.trim().equals("")){
				return "Please Enter Army No";
			}
			else if (rank.trim().equals("")){
				return "Please Enter Rank";
			}else if(mobile_no.trim() == "") {
				return "Please Enter Mobile No";
			}else if (document.isEmpty()){
				return "Please Upload Document ";
			}else if (appointment.trim().equals("") || appointment.trim().equals("null") || appointment.trim().equals(null)){
				return "Please Enter Appointment";
			}else {
				if (!document.isEmpty()) {
					byte[] bytes = document.getBytes();
					String mnhFilePath = "/srv"+ File.separator + "user_regitraion";
					File dir = new File(mnhFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = document.getOriginalFilename();
					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_REGDOC." + extension;
					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				}
				TB_MISO_USER_REGISTRATION aon = new TB_MISO_USER_REGISTRATION();
				aon.setUser_name(user_name);
				aon.setUnit_name(unit_name);
				aon.setSus_no(sus_no);
				aon.setArmy_no(army_no);
				aon.setRank(rank);
				aon.setMobile_no(mobile_no);
				aon.setDoc(fname);
				aon.setAppointment(appointment);
				aon.setCreated_on(new Date());
				int id = (int) sessionHQL.save(aon);
				tx.commit();
				msg = String.valueOf(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "No Transction Made";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
}
