package com.controller.psg.Civilian;





import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Jco_Update_Census.Birth_UpdateController_JCO;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Update_Census.Update_Census_Details_JCO_DAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.persistance.util.HibernateUtil;





@Controller

@RequestMapping(value = {"admin","/","user"})





public class Update_Civilian_Controller_Reg {

	

	Psg_CommonController p_comm = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	psg_jco_CommonController jcom =new psg_jco_CommonController();

	PsgDashboardController das = new PsgDashboardController();


	

	Birth_UpdateController_JCO birth = new Birth_UpdateController_JCO();

	
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;

	@Autowired

	Search_UpdateComm_Dao UCO;

	@Autowired

	private RoleBaseMenuDAO roledao;

	

	@Autowired

	Search_UpdateComm_Dao scldao;

	@Autowired

	Update_Census_Details_JCO_DAO UCD;

	ValidationController valid = new ValidationController();

  	


  	
  	


  	

 	 @RequestMapping(value = "/admin/Update_Civilian_Details_REG", method = RequestMethod.GET)

 	 public ModelAndView Update_Civilian_Details_REG(ModelMap Mmap, HttpSession session,

 			 @RequestParam(value = "msg", required = false) String msg,

 			@RequestParam(value = "personnel_no_edit_civ", required = false) String emp_no,



			@RequestParam(value = "status", required = false) String status,



			@ModelAttribute("emp_no6") String emp_no6, 



			@ModelAttribute("status6") String status6, 



			@ModelAttribute("civ_id6") String civ_id6, 



			@ModelAttribute("unit_name6") String unit_name6, 



			@ModelAttribute("unit_sus_no6") String unit_sus_no6,HttpServletRequest request) {

 		 

 		 

 		 

 		 String  roleid = session.getAttribute("roleid").toString();

//		 Boolean val = roledao.ScreenRedirect("Update_Census_Details_JCO", roleid);	
//
//		 	if(val == false) {
//
//				return new ModelAndView("AccessTiles");
//
//			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

 		 

 		 

			Mmap.put("msg", msg);

			Mmap.put("getCourseNameList", p_comm.getCourseNameList());

			Mmap.put("getGenderList", p_comm.getGenderList());

			Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());

			Mmap.put("getRegtList", p_comm.getRegtList(""));

			Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());

			Mmap.put("getParentArmList", p_comm.getParentArmList());

			Mmap.put("getArmyType", p_comm.getArmyType());

			Mmap.put("getMedCountryName", p_comm.getMedCountryName("", session));

			Mmap.put("getMedStateName", p_comm.getMedStateName("", session));

			Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", session));

			Mmap.put("getNationalityList", p_comm.getNationalityList());

			Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());

			Mmap.put("getBloodList", p_comm.getBloodList());

			Mmap.put("getHeight", p_comm.getHeight());

			Mmap.put("getClass_enrollList", jcom.getClass_enrollList());

			Mmap.put("getRankjcoList", jcom.getRankjcoList());

			Mmap.put("getExservicemenList", p_comm.getExservicemenList());

			Mmap.put("getProfession", p_comm.getProfession());

			Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());

			

//			Mmap.put("getRank_intakeList", jcom.getRank_intakeList());

			Mmap.put("getclass_domicileList", jcom.getclass_domicileList());

			

			Mmap.put("msg", msg);



			Date date = new Date();



			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);



			Mmap.put("date", date1);

			

			Mmap.put("personnel_no_edit_civ", emp_no);



			Mmap.put("emp_no6", emp_no6);



			Mmap.put("status6", status6);



			Mmap.put("civ_id6", civ_id6);
	



			Mmap.put("unit_name6", unit_name6);



			Mmap.put("unit_sus_no6", unit_sus_no6);



 		 

 		 return new ModelAndView("Update_CivRegular_DataTiles");

 	 }

 	 
 	
 	
}

