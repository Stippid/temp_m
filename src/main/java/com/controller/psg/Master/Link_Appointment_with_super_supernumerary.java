package com.controller.psg.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.Link_Appointment_with_super_supernumeraryDAO;
import com.models.psg.Transaction.TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Link_Appointment_with_super_supernumerary {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	@Autowired
	private Link_Appointment_with_super_supernumeraryDAO link_appointment_dao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	
	 @RequestMapping(value = "/admin/appointment", method = RequestMethod.GET)
	 public ModelAndView appointment(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("appointment", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		  	Mmap.put("msg", msg);
		 	ArrayList<ArrayList<String>> list = link_appointment_dao.search_appointment("","active");
		 	Mmap.put("list", list);
		 	Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 	
		 return new ModelAndView("AppointmentTiles");
	 }
	 
	 
	 // ..........save...............
	 
	@RequestMapping(value = "/AppointmentAction",method=RequestMethod.POST)
		public ModelAndView AppointmentAction(@ModelAttribute("AppointmentCMD") TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY api,
	HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appointment", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg1 = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		 int id = api.getId() > 0 ? api.getId() : 0; 
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				 String appointment = request.getParameter("appointment").trim();
				 
				if (appointment.equals("")|| appointment.equals("null")
							|| appointment.equals(null)) {
						model.put("msg", "Please Enter appointment");
						return new ModelAndView("redirect:appointment");
					}
				if (api.getStatus() == "inactive" || api.getStatus().equals("inactive")) {
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:appointment");
				}
				 
				try{
					
					
					model.put("getTypeofAppointementList", mcommon.getTypeofAppointementList());
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY where appointment=:appointment and id!=:id");
					 	
					q0.setParameter("appointment", api.getAppointment());  			
					q0.setParameter("id", id);
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						api.setCreated_by(username);
						api.setCreated_date(date);
						api.setAppointment(appointment);
						if (c == 0) {
							sessionHQL.save(api);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					else {
						
						api.setApproved_by(username);
						api.setApproved_date(date);
						if (c == 0) {
							String msg = link_appointment_dao.updateAppointment(api);
							model.put("msg", msg);
						} else {
							model.put("msg", "Data already Exist.");
						}
					}
					tx.commit();
				}catch(RuntimeException e){
					try{
						tx.rollback();
						model.put("msg", "roll back transaction");
					}catch(RuntimeException rbe){
						model.put("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					  sessionHQL.close();
					}
				}	
					// sessionHQL.close();
				return new ModelAndView("redirect:appointment");
			}
	 
	 //...........search...................
    @RequestMapping(value = "/admin/getSearch_Appointment_Master", method = RequestMethod.POST)
	public ModelAndView getSearch_Appointment_Master(ModelMap Mmap,HttpSession session,	HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "appointment1", required = false) String appointment1,
			@RequestParam(value = "status1", required = false) String status1)
{
    	
    	String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appointment", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

    	ArrayList<ArrayList<String>> list = link_appointment_dao.search_appointment(appointment1,status1);
	
			Mmap.put("appointment1", appointment1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			Mmap.put("list", list);
			
		return new ModelAndView("AppointmentTiles","AppointmentCMD",new TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY());
	}

    //.......................delete..............................//
    @RequestMapping(value = "/delete_Appointment", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Appointment(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
    	String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("appointment", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

    	
    	List<String> liststr = new ArrayList<String>();
		
		String username = session.getAttribute("username").toString();
		
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			 String hqlUpdate = "update TB_LINK_APPOINMENT_WITH_SUPER_SUPERNUMERAY set approved_by=:approved_by,approved_date=:approved_date,status=:status"
									+ " where id=:id";
			
			 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
					.setString("approved_by", username)
					.setDate("approved_date", new Date()).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:appointment");
	}
}
