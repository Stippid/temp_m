package com.controller.tms;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
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
import com.dao.tms.TmsNodalDirDAO;
import com.dao.tms.TmsNodalDirDAOImp;
import com.models.TB_TMS_MCT_NODAL_DIR_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Common_Mct_Nodal_DirController {

	TmsNodalDirDAO nodalDao = new TmsNodalDirDAOImp();
	ValidationController validation = new ValidationController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value="/mctnodaldir")
	public ModelAndView mctnodaldir(ModelMap model,HttpServletRequest request,HttpSession session,@RequestParam(value = "msg", required = false) String msg ){	
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctnodaldir", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("msg",msg);
		return new ModelAndView("mctnodalTiles","tms_nodal_dir_masterCmd", new TB_TMS_MCT_NODAL_DIR_MASTER());
	}
	
	@RequestMapping(value = "/mctnodalAction", method = RequestMethod.POST)
	public ModelAndView mctnodalAction(@ModelAttribute("tms_nodal_dir_masterCmd") TB_TMS_MCT_NODAL_DIR_MASTER nodal, HttpServletRequest request,ModelMap model,HttpSession session)  {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("mctnodaldir", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getParameter("type_of_agncy").equals("0") || request.getParameter("type_of_agncy").equals(""))
		{
			model.put("msg", "Please Select the Type of Agency.");
			return new ModelAndView("redirect:mctnodaldir");
		}
		else if(request.getParameter("type_of_veh").equals("0") || request.getParameter("type_of_veh").equals("") || request.getParameter("type_of_veh").equals("null") || request.getParameter("type_of_veh").equals(null))
		{
			model.put("msg", "Please Select the Type of Vehicle.");
			return new ModelAndView("redirect:mctnodaldir");
		}
		else if( request.getParameter("sus_no").equals("") || request.getParameter("sus_no").equals("null") || request.getParameter("sus_no").equals(null))
		{
			model.put("msg", "Please Select Sus No.");
			return new ModelAndView("redirect:mctnodaldir");
		}
		 else if(validation.sus_noLength(request.getParameter("sus_no")) == false){
		 		model.put("msg",validation.sus_noMSG);
				return new ModelAndView("redirect:mctnodaldir");
		}
		
		else if(request.getParameter("type_of_agncy").toString().equals("Depot") && request.getParameter("depot_code").equals("") )
		{
			model.put("msg", "Please enter Depot Code.");
			return new ModelAndView("redirect:mctnodaldir");
		}
		 else if(validation.type_of_vehLength(request.getParameter("depot_code")) == false){
		 		model.put("msg",validation.depot_codeMSG);
				return new ModelAndView("redirect:mctnodaldir");
		}
		else
		{
			String username = session.getAttribute("username").toString();
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
			nodal.setCreated_by(username);
			nodal.setCreated_on(date);
			Session session1=HibernateUtil.getSessionFactory().openSession();
			if(nodalDao.ifExist(request.getParameter("type_of_agncy").toString(), request.getParameter("sus_no"), request.getParameter("depot_code"), request.getParameter("type_of_veh")).equals(false))
			{
				session1.beginTransaction();
				session1.save(nodal);
				session1.getTransaction().commit();
				session1.close();
				model.put("msg", "Nodal Dte/Depot Added Successfully.");
				return new ModelAndView("redirect:mctnodaldir");
			}
			else
			{
				model.put("msg", "Data already Exist.");
				return new ModelAndView("redirect:mctnodaldir");
			}
		}
	}
}