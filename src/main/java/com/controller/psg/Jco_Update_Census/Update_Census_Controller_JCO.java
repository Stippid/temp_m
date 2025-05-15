package com.controller.psg.Jco_Update_Census;





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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.JCO_Update_Census.Update_Census_Details_JCO_DAO;
import com.dao.psg.Transaction.Search_UpdateComm_Dao;
import com.persistance.util.HibernateUtil;





@Controller

@RequestMapping(value = {"admin","/","user"})





public class Update_Census_Controller_JCO {

	

	Psg_CommonController p_comm = new Psg_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	psg_jco_CommonController jcom =new psg_jco_CommonController();

	PsgDashboardController das = new PsgDashboardController();


	

	Birth_UpdateController_JCO birth = new Birth_UpdateController_JCO();

	

	@Autowired

	Search_UpdateComm_Dao UCO;

	@Autowired

	private RoleBaseMenuDAO roledao;

	

	@Autowired

	Search_UpdateComm_Dao scldao;

	@Autowired

	Update_Census_Details_JCO_DAO UCD;

	ValidationController valid = new ValidationController();

  	 @RequestMapping(value = "/admin/Update_Census_Details_JCO", method = RequestMethod.GET)

  	 public ModelAndView Update_Census_Details_JCO(ModelMap Mmap, HttpSession session,

  			 @RequestParam(value = "msg", required = false) String msg,

  			@RequestParam(value = "personnel_no_edit_JCO", required = false) String army_no,



			@RequestParam(value = "status", required = false) String status,



			@ModelAttribute("army_no6") String army_no6, 



			@ModelAttribute("status6") String status6, 



			@ModelAttribute("rank6") String rank6, 



			@ModelAttribute("jco_id6") String jco_id6, 



			@ModelAttribute("unit_name6") String unit_name6, 



			@ModelAttribute("unit_sus_no6") String unit_sus_no6,HttpServletRequest request) {

  		 

  		 

  		 

  		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Update_Census_Details_JCO", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

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

			

			Mmap.put("getRank_intakeList", jcom.getRank_intakeList());

			Mmap.put("getclass_domicileList", jcom.getclass_domicileList());

			

			Mmap.put("msg", msg);



			Date date = new Date();



			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);



			Mmap.put("date", date1);

			

			Mmap.put("personnel_no_edit_JCO", army_no);



			Mmap.put("army_no6", army_no6);



			Mmap.put("status6", status6);



			Mmap.put("rank6", rank6);



			Mmap.put("jco_id6", jco_id6);



			Mmap.put("unit_name6", unit_name6);



			Mmap.put("unit_sus_no6", unit_sus_no6);



  		 

  		 return new ModelAndView("Update_Census_DetailsTiles");

  	 }

  	 

  	 

  	@RequestMapping(value = "/admin/Search_Update_Census_Url_JCO", method = RequestMethod.GET)

 	public ModelAndView Search_Update_Census_Url_JCO(ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg,

 			HttpServletRequest request) {

  		

  		

 		String roleType = session.getAttribute("roleType").toString();

 		String roleid = session.getAttribute("roleid").toString();

 		Boolean val = roledao.ScreenRedirect("Search_Update_Census_Url_JCO", roleid);

 		String roleAccess = session.getAttribute("roleAccess").toString();

 		String roleSusNo = session.getAttribute("roleSusNo").toString();

 		

 		

 		 	if(val == false) {

 				return new ModelAndView("AccessTiles");

 			}

 			if(request.getHeader("Referer") == null ) {

 				msg = "";

 			}

 			

 			if (roleAccess.equals("Unit")) {

 				Mmap.put("sus_no", roleSusNo);

 				Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

 			}

 			

 		Mmap.put("msg", msg);

 		Mmap.put("getRankjcoList", jcom.getRankjcoList());
 		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

 		/// bisag v2 010922  (converted to Datatable)
 		//Mmap.put("list", UCD.AppSearch_UpdateCensusJCOData("", "0", "", "", "", roleSusNo, roleType, "0"));

 		return new ModelAndView("Search_S_C_L_Tiles_JCO");

 	}



  	@RequestMapping(value = "/admin/GetS_C_LData_JCO", method = RequestMethod.POST)

	public ModelAndView GetS_C_LData_JCO(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg,

			@RequestParam(value = "army_no1", required = false) String army_no,

			@RequestParam(value = "status1", required = false) String status,

			@RequestParam(value = "rank1", required = false) String rank,

			@RequestParam(value = "unit_name1", required = false) String unit_name,

			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,

			@RequestParam(value = "icStatus1", required = false) String icStatus) {



		unit_name = unit_name.replace("&#40;", "(");

		unit_name = unit_name.replace("&#41;", ")");

		String roleType = session.getAttribute("roleType").toString();

		String roleSusNo = session.getAttribute("roleSusNo").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();

		if(unit_sus_no!="") {
            if (!valid.SusNoLength(unit_sus_no)) {
                              Mmap.put("msg", valid.SusNoMSG);
                              return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
                      }
            if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
				}
           }
    
        if(unit_name!="") {
        	 if (!valid.isUnit(unit_name)) {
                 Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                 return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
          }
//                      if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//                              Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//                              return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
//                      }
    }
    
        if(army_no!="") {
            if (!valid.ArmyNoLength(army_no)) {
                                   Mmap.put("msg", valid.ArmyNoMSG );
                                   return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
                           }
            if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
					return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
				}
                 if (army_no.length() < 2 || army_no.length() > 12) {
                                   Mmap.put("msg", "Army No No Should Contain Maximum 12 Character");
                                   return new ModelAndView("redirect:Search_Update_Census_Url_JCO");
                           }
        } 

		if (roleAccess.equals("Unit")) {

			Mmap.put("sus_no", roleSusNo);

			Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session).get(0));

		}

 		
		/// bisag v2 010922  (converted to Datatable)
// 	      ArrayList<ArrayList<String>> list = UCD.AppSearch_UpdateCensusJCOData(army_no, status, rank, unit_sus_no, unit_name, roleSusNo, roleType, icStatus);

 	    		  

// 	        Mmap.put("list", list);
//
// 			Mmap.put("size", list.size());

 			Mmap.put("army_no1", army_no);

 			Mmap.put("rank1", rank);

 			Mmap.put("status1", status);

 			Mmap.put("unit_name1", unit_name);

 			Mmap.put("unit_sus_no1", unit_sus_no);

 			Mmap.put("icStatus1", icStatus);

 			Mmap.put("getRankjcoList", jcom.getRankjcoList());
 			 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
 			
 		return new ModelAndView("Search_S_C_L_Tiles_JCO");

 	}

	

	@RequestMapping(value = "/GetJcoUpdateCensusDataApprove", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> GetJcoUpdateCensusDataApprove(int jco_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		List<Map<String, Object>> list = UCD.GetJcoOrUpdateCensusDataApprove(jco_id);

		tx.commit();

		return list;

	}
	
	/// bisag v2 010922  (converted to Datatable)
		@RequestMapping(value = "/Getcensus_detail_data_count", method = RequestMethod.POST)
			public @ResponseBody long Getcensus_detail_data_count(String Search,String orderColunm,String orderType,HttpSession sessionUserId
					,String msg,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String cr_by,String cr_date) throws SQLException {
				
				 String roleType = sessionUserId.getAttribute("roleType").toString();
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
				
				return UCD.Getcensus_detail_data_count_jco(Search, orderColunm, orderType, sessionUserId, unit_sus_no, unit_name,personnel_no,
						 rank, status,icstatus,roleType, cr_by, cr_date);
			}
			

			
			@RequestMapping(value = "/Getcensus_detail_data", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>> Getcensus_detail_data(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
					,String unit_sus_no,String unit_name,String personnel_no,
					String rank,String status,String icstatus,String cr_by,String cr_date) throws SQLException {
				 String roleType = sessionUserId.getAttribute("roleType").toString();
				
				return UCD.Getcensus_detail_data_jco(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_sus_no, unit_name,personnel_no,
						 rank, status,icstatus,roleType, cr_by, cr_date);
			}

}

