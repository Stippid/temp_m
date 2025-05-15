package com.controller.tms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_TMS_MCT_REG_AGENCY_MASTER;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Common_Mct_Requesting_AgencyController {
	
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value="/mctreqagency")
	public ModelAndView mctreqagency(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg ){	
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctreqagency", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		return new ModelAndView("mctreagencyTiles","tms_request_agency_masterCmd", new TB_TMS_MCT_REG_AGENCY_MASTER());
	}

	@RequestMapping(value = "/mctagencyAction", method = RequestMethod.POST)
	public ModelAndView mctAction(@Valid @ModelAttribute("tms_request_agency_masterCmd") TB_TMS_MCT_REG_AGENCY_MASTER reqagnc,HttpServletRequest request,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctreqagency", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
		if(request.getParameter("req_agency").toString().equals("") || request.getParameter("req_agency").toString().equals("null") || request.getParameter("req_agency").toString().equals(null) )
		{
			model.put("msg", "Please Enter MCT Request Agency.");
			return new ModelAndView("redirect:mctreqagency");
		}
		else if(validation.checkUnit_nameLength(request.getParameter("req_agency")) == false){
	 		model.put("msg",validation.sponsoring_dteMSG);
			return new ModelAndView("redirect:mctreqagency");
	}
		else if(request.getParameter("sus_no").toString().equals("") || request.getParameter("sus_no").toString().equals("null") || request.getParameter("sus_no").toString().equals(null) )
		{
			model.put("msg", "Please Enter SUS No.");
			return new ModelAndView("redirect:mctreqagency");
		}
		 else if(validation.sus_noLength(request.getParameter("sus_no")) == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mctreqagency");
		}
		else
		{
			reqagnc.setCreated_by(username);
			reqagnc.setCreated_on(date);
			if(ifRequestAgency_SusNoExist(reqagnc.getSus_no()).equals(true)) {
				model.put("msg", "SUS No Already Exist.");
				return new ModelAndView("redirect:mctreqagency");
			}
			Session session1=HibernateUtil.getSessionFactory().openSession();
			try
			{
				session1.beginTransaction();
				session1.save(reqagnc);
				session1.getTransaction().commit();
				model.put("msg", "Depot/Unit/Est Successfully Added.");
				return new ModelAndView("redirect:mctreqagency");
			}
			catch(Exception e)
			{
				session1.getTransaction().rollback();
				model.put("msg", "SUS No Already Exist.");
				return new ModelAndView("redirect:mctreqagency");
			}
			finally
			{
				session1.close();
			}
		}
	}
	public Boolean ifRequestAgency_SusNoExist(String sus_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String q = "from TB_TMS_MCT_REG_AGENCY_MASTER where sus_no =:sus_no ";
    	Query query = session.createQuery(q);
    	query.setParameter("sus_no", sus_no);
   		@SuppressWarnings("unchecked")
		List<TB_TMS_MCT_REG_AGENCY_MASTER> list = (List<TB_TMS_MCT_REG_AGENCY_MASTER>) query.list();
   		tx.commit();
   		if(list.size() > 0) {
	    	return true;
	    }else {
	    	return false;
	    }
    }
}