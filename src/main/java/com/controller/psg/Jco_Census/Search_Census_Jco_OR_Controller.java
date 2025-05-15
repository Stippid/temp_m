package com.controller.psg.Jco_Census;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Census.Search_JCOsDao;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_SIBLINGS;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_FAMILY_MARRIED_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_SPOUSE_QUALIFICATION_JCO;
import com.persistance.util.HibernateUtil;


	
@Controller
@RequestMapping(value = {"admin","/","user"})

public class Search_Census_Jco_OR_Controller {

	  ValidationController valid = new ValidationController();
	  
	  Census_Jco_OR_Controller jco_or = new Census_Jco_OR_Controller();
	@Autowired
	Search_JCOsDao jco;
	Psg_CommonController p_comm = new Psg_CommonController();
	
      AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	
	//CommanController all = new CommanController();
	//CommanController com = new CommanController();
	Census_Jco_OR_Controller cj = new Census_Jco_OR_Controller();
	psg_jco_CommonController jcom = new psg_jco_CommonController();
	ValidationController validation = new ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private Search_JCOsDao SJDAO;

	@Autowired
	private Report_3008DAO report_3008DAO;

	@Autowired
	private Search_PostOutDao pod;

	PsgDashboardController das = new PsgDashboardController();
   
	@RequestMapping(value = "/admin/search_jco_url" , method = RequestMethod.GET)
	 public ModelAndView search_JCOs_orUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 String roleid = session.getAttribute("roleid").toString();
		    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
			if (val == false) {
			return new ModelAndView("AccessTiles");
		    }

			if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
	
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 
		 if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}
		 Mmap.put("msg", msg);	
		 Mmap.put("getTypeofRankJcoList", p_comm.getTypeofRankJcoList());
		 Mmap.put("getRankjcoList", jcom.getRankjcoList());
		 /// bisag v2 260822  (converted to Datatable)
		 //Mmap.put("list", jco.Search_JCOs(roleSusNo,"","0","","","",session));
		 Mmap.put("status1", "0");
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 return new ModelAndView("search_JCOs_ORTiles");
	 }

	 @RequestMapping(value = "/admin/getSearch_JCOs_OR", method = RequestMethod.POST)
		public ModelAndView getSearch_JCOs_OR(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "army_no1", required = false) String army_no,
				@RequestParam(value = "status1", required = false) String status,
				@RequestParam(value = "rank1", required = false) String rank,
				@RequestParam(value = "unit_name1", required = false) String unit_name,
			    @RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no
			    ,	@RequestParam(value = "cr_by1", required = false) String cr_by,
			    @RequestParam(value = "cr_date1", required = false) String cr_date){

		 	unit_name = unit_name.replace("&#40;", "(");
			unit_name = unit_name.replace("&#41;", ")");

			 String roleid = session.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				  if(unit_sus_no!="") {
                  if (!valid.SusNoLength(unit_sus_no)) {
                                    Mmap.put("msg", valid.SusNoMSG);
                                    return new ModelAndView("redirect:search_jco_url");
                            }
            	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
  					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
  					return new ModelAndView("redirect:search_jco_url");
  				}
          }
          
          if(unit_name!="") {
        	  if (!valid.isUnit(unit_name)) {
                  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                  return new ModelAndView("redirect:search_jco_url");
                }

//                            if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//                                    Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//                                    return new ModelAndView("redirect:search_jco_url");
//                            }
          }
          
             if(army_no!="") {
             if (!valid.ArmyNoLength(army_no)) {
                                    Mmap.put("msg", valid.ArmyNoMSG);
                                    return new ModelAndView("redirect:search_jco_url");
                            }
             if (!valid.isOnlyAlphabetNumericSpaceNot(army_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
					return new ModelAndView("redirect:search_jco_url");
				}
			  
                  if (army_no.length() < 2 || army_no.length() > 12) {
                                    Mmap.put("msg", "Army No Should Contain Maximum 12 Character");
                                    return new ModelAndView("redirect:search_jco_url");
                            }
             } 

				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					
					
				}
				
				/// bisag v2 260822  (converted to Datatable)
				//ArrayList<ArrayList<String>> list = jco.Search_JCOs(roleSusNo,army_no,status,rank,unit_name,unit_sus_no ,session);
				//Mmap.put("list", list);
				//Mmap.put("size", list.size());					
				Mmap.put("army_no1", army_no);
				Mmap.put("rank1", rank);
				Mmap.put("status1", status);
				Mmap.put("unit_name1", unit_name);
				Mmap.put("unit_sus_no1", unit_sus_no);
				Mmap.put("getRankjcoList", jcom.getRankjcoList());

				   Mmap.put("cr_date1", cr_date);
				Mmap.put("cr_by1", cr_by);
				 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
				return new ModelAndView("search_JCOs_ORTiles");
			
		}
	 
	   @RequestMapping(value = "/view_JCOs_Url" , method = RequestMethod.POST)
		public ModelAndView view_JCOs_Url(@ModelAttribute("idv") String updateid, 
				@RequestParam(value = "army_no6", required = false) String army_no,
				@RequestParam(value = "status6", required = false) String status,
				@RequestParam(value = "rank6", required = false) String rank,
				@RequestParam(value = "unit_name6", required = false) String unit_name,
			    @RequestParam(value = "unit_sus_no6", required = false) String unit_sus_no,

				
				ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit)  {
		       String roleid = sessionEdit.getAttribute("roleid").toString();
		        Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
					if (val == false) {
					return new ModelAndView("AccessTiles");
				    }
			
					if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
					}
		  ArrayList<ArrayList<String>> list= jco.View_GetAllJCO_OR_Details(Integer.parseInt(updateid));
		 
					model.put("list", list);
					model.put("size", list.size());
					model.put("army_no6", army_no);
					model.put("rank6", rank);
					model.put("status6", status);
					model.put("unit_name6", unit_name);
					model.put("unit_sus_no6", unit_sus_no);
					
					// medical
					model.put("getShapeStatusList", p_comm.getShapeStatusList());
					model.put("getcCopeList", p_comm.getCopeValueList("C"));
					model.put("getoCopeList", p_comm.getCopeValueList("O"));
					model.put("getpCopeList", p_comm.getCopeValueList("P"));
					model.put("geteCopeList", p_comm.getCopeValueList("E"));
					model.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));
					//end medical

					
					model.put("msg", msg);
		return new ModelAndView("View_JCOs_ORTiles");
	 }
	 
	 
		@RequestMapping(value = "/admin/Delete_JCOs_Url" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_JCOs_Url(@ModelAttribute("id3") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			 String roleid = sessionA.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			List<String> liststr = new ArrayList<String>();
			
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_CENSUS_JCO_OR_PARENT where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				
				String hqlUpdate1 = "delete from TB_CENSUS_JCO_OR_SIBLINGS where jco_id=:id";
				int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).executeUpdate();
				
				String hqlUpdate2 = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:id";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).executeUpdate();
			
				String hqlUpdate3 = "delete from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:id";
				int app3 = sessionHQL.createQuery(hqlUpdate3).setInteger("id", id).executeUpdate();
				
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
			return new ModelAndView("redirect:search_jco_url");
		}

		@RequestMapping(value = "/Relegate_Jco_Url" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Relegate_Jco_Url(@ModelAttribute("idr") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 String roleid = sessionA.getAttribute("roleid").toString();
			    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
				if (val == false) {
				return new ModelAndView("AccessTiles");
			    }

				if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_CENSUS_JCO_OR_PARENT where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();
				sessionHQL.close();
				
				
				if (app > 0  ) {
					liststr.add("Relegate Successfully.");
				} else {
					liststr.add("Relegate UNSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg",liststr.get(0));
			}
			return new ModelAndView("redirect:search_jco_url");
		}
	        //-------------------------------------UPDATE
		 
		 @RequestMapping(value = "/Edit_JCOs_lanUrl", method = RequestMethod.POST)
			public ModelAndView Edit_JCOs_lanUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "army_no9", required = false) String army_no,
					@RequestParam(value = "status9", required = false) String status,
					@RequestParam(value = "rank9", required = false) String rank,
					@RequestParam(value = "unit_name9", required = false) String unit_name,
				    @RequestParam(value = "unit_sus_no9", required = false) String unit_sus_no,
					Authentication authentication,HttpServletRequest request,
					HttpSession sessionEdit,HttpSession sessionUserId) {	
			 
				TB_CENSUS_JCO_OR_PARENT authDetails = jco.getJCOsByid(Integer.parseInt(updateid));
			    TB_CENSUS_JCO_OR_SIBLINGS authSibDetails = jco.getJCOSiblingByid(Integer.parseInt(updateid));
		
				 String roleid = sessionEdit.getAttribute("roleid").toString();
				 
				    Boolean val = roledao.ScreenRedirect("search_jco_url", roleid);
					if (val == false) {
					return new ModelAndView("AccessTiles");
				    }

					if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
					}
				   
					String roleSusNo = sessionEdit.getAttribute("roleSusNo").toString();
					String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
					if (roleAccess.equals("Unit")) {
						Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionEdit).get(0));
					}
				Mmap.put("Edit_JCOsCMD", authDetails);
				Mmap.put("Edit_JCOSiblingCMD", authSibDetails);
				
				Mmap.put("roleSusNo", roleSusNo);
				Mmap.put("msg", msg);
				Mmap.put("getArmyType", p_comm.getArmyType());
				Mmap.put("getReligionList", p_comm.getReligionList());
				Mmap.put("getMarital_statusList", p_comm.getMarital_statusList());
				Mmap.put("getBloodList", p_comm.getBloodList());
				Mmap.put("getRelationList", p_comm.getRelationList_JCO());
				Mmap.put("getMedDistrictName", p_comm.getMedDistrictName("", sessionEdit));
				Mmap.put("getMedStateName", p_comm.getMedStateName("", sessionEdit));
				Mmap.put("getMedCountryName", p_comm.getMedCountryName("", sessionEdit));
				Mmap.put("getNationalityList", p_comm.getNationalityList());
				Mmap.put("getMedCatList", p_comm.getMedCatList());
				Mmap.put("getParentArmList", p_comm.getParentArmList());
				Mmap.put("getMothertoungeList", p_comm.getMothertoungeList());
				Mmap.put("getHeight", p_comm.getHeight());
				Mmap.put("getFamily_siblings", p_comm.getFamily_siblings());
				Mmap.put("getMedCityName", p_comm.getMedCityName("", sessionEdit));
				Mmap.put("getProfession", p_comm.getProfession());
				Mmap.put("getGenderList", p_comm.getGenderList());
				Mmap.put("getTypeofRankJcoList", p_comm.getTypeofRankJcoList());
				Mmap.put("getTypeofTradeList", p_comm.getTypeofTradeList());
				Mmap.put("getExservicemenList", p_comm.getExservicemenList());
				Mmap.put("getClass_payList", jcom.getClass_payList());
				Mmap.put("getPaygroupList", jcom.getPaygroupList());
				Mmap.put("getCategory_jCOList", jcom.getCategory_jCOList());
				Mmap.put("getClass_enrollList", jcom.getClass_enrollList());
				Mmap.put("getRankjcoList", jcom.getRankjcoList());
				Mmap.put("getTradeJcoList", jcom.getTradeJcoList());
				Mmap.put("roleSusNo", roleSusNo);	
				Mmap.put("getQualificationTypeList", p_comm.getQualificationTypeList());
				Mmap.put("getLanguageList", p_comm.getLanguageList());
				Mmap.put("getSpecializationList", p_comm.getSpecializationList());
				Mmap.put("getDivclass", p_comm.getDivclass());
				Mmap.put("getSubject", p_comm.getSubject());
				Mmap.put("getCommInstitute", p_comm.getInstitute("1"));
				Mmap.put("getTrainingInstitute", p_comm.getInstitute("2"));
				Mmap.put("getRank_intakeList", jcom.getRank_intakeList());
				Mmap.put("getclass_domicileList", jcom.getclass_domicileList());
				// medical
				Mmap.put("getShapeStatusList", p_comm.getShapeStatusList());
				Mmap.put("getcCopeList", p_comm.getCopeValueList("C"));
				Mmap.put("getoCopeList", p_comm.getCopeValueList("O"));
				Mmap.put("getpCopeList", p_comm.getCopeValueList("P"));
				Mmap.put("geteCopeList", p_comm.getCopeValueList("E"));
				Mmap.put("getesubCopeList", p_comm.getCopeValueList("E Sub Value"));
				//end medical
				if (roleAccess.equals("Unit")) {
					Mmap.put("unit_name", all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionUserId).get(0));
				}
				Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
				return new ModelAndView("Edit_JCOs_ORTiles");
			}
		 @RequestMapping(value = "/admin/family_marriage_delete_action_jco", method = RequestMethod.POST)
			public @ResponseBody String family_marriage_delete_action_jco(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("marr_ch_id"));
				
				try {
					String hqlUpdate = "delete from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where spouse_id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					hqlUpdate = "delete from TB_CENSUS_FAMILY_MARRIED_JCO where id=:id";
					int papp = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					
					tx.commit();
					if (papp > 0) {
						msg = "1";
					} else {
						msg = "0";
					}
				} catch (Exception e) {
					tx.rollback();
				} finally {
					sessionHQL.close();
				}
				return msg;
			}
			
			@RequestMapping(value = "/admin/family_sibling_delete_action_jco", method = RequestMethod.POST)
			public @ResponseBody String family_sibling_delete_action_jco(ModelMap Mmap, HttpSession session,
					HttpServletRequest request) throws ParseException {
				String msg = "";
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();
				int id = Integer.parseInt(request.getParameter("sib_ch_id"));
				try {
					String hqlUpdate = "delete from TB_CENSUS_JCO_OR_SIBLINGS where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					tx.commit();
					sessionHQL.close();
					if (app > 0) {
						msg = "1";
					} else {
						msg = "0";
					}
				} catch (Exception e) {
				}
				return msg;
			}
			@RequestMapping(value = "/admin/getjco_siblingData", method = RequestMethod.POST)

			public @ResponseBody List<TB_CENSUS_JCO_OR_SIBLINGS> getjco_siblingData(ModelMap Mmap, HttpSession session,

					HttpServletRequest request) throws ParseException {

				

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				Transaction tx = sessionHQL.beginTransaction();

				int id = Integer.parseInt(request.getParameter("p_id"));

				String hqlUpdate = " from TB_CENSUS_JCO_OR_SIBLINGS where jco_id=:jco_id order by id";

				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
				List<TB_CENSUS_JCO_OR_SIBLINGS> list = (List<TB_CENSUS_JCO_OR_SIBLINGS>) query.list();

				tx.commit();

				sessionHQL.close();

				return list;

			}
			@RequestMapping(value = "/admin/getfamily_marriageData_jco", method = RequestMethod.POST)

			public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> getfamily_marriageData_jco(ModelMap Mmap, HttpSession session,

					HttpServletRequest request) throws ParseException {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				Transaction tx = sessionHQL.beginTransaction();

				int id = Integer.parseInt(request.getParameter("p_id1"));
				String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id  and divorce_date is null order by id";

				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
				List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();

				tx.commit();

				sessionHQL.close();

				return list;

			}
			@RequestMapping(value = "/admin/getfamily_divorceData_jco", method = RequestMethod.POST)

			public @ResponseBody List<TB_CENSUS_FAMILY_MARRIED_JCO> getfamily_divorceData_jco(ModelMap Mmap, HttpSession session,

					HttpServletRequest request) throws ParseException {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				Transaction tx = sessionHQL.beginTransaction();

				int id = Integer.parseInt(request.getParameter("p_id1"));
				String hqlUpdate = " from TB_CENSUS_FAMILY_MARRIED_JCO where jco_id=:jco_id and divorce_date is not null order by id";

				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
				List<TB_CENSUS_FAMILY_MARRIED_JCO> list = (List<TB_CENSUS_FAMILY_MARRIED_JCO>) query.list();

				tx.commit();

				sessionHQL.close();

				return list;

			}
			@RequestMapping(value = "/admin/getspouse_qualiData_jco", method = RequestMethod.POST)

			public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> getspouse_qualiData_jco(ModelMap Mmap, HttpSession session,

					HttpServletRequest request) throws ParseException {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				Transaction tx = sessionHQL.beginTransaction();

				int id = Integer.parseInt(request.getParameter("p_id"));
                           
				String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:jco_id order by id";

				Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
				List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION_JCO>) query.list();
				
				tx.commit();

				sessionHQL.close();

				return list;

			}

			@RequestMapping(value = "/Edit_JCOsAction", method = RequestMethod.POST)
			public ModelAndView Edit_JCOsAction(@ModelAttribute("Edit_JCOsCMD") TB_CENSUS_JCO_OR_PARENT jc,
					HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				
				String username = session.getAttribute("username").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
		        int id = Integer.parseInt(request.getParameter("jco_id"));
		        String jco_id=request.getParameter("jco_id");
		    	

				Date sib_dt = null;
		        Date tos_dt = null;
				Date birth_dt = null;
				Date atts_dt = null;
				Date dt_ser = null;
				Date f_dt = null;
				Date m_dt = null;
				Date en_dt = null;
				Date dt_rank = null;

				String adhar_numbers = "";
				String adhar_number = "";
				String date_attestation = request.getParameter("date_of_attestation");
				String birth_date = request.getParameter("date_of_birth");
				
				String enrl_date = request.getParameter("enroll_dt");
				String tos_date = request.getParameter("date_of_tos");
				String date_of_rank = request.getParameter("date_of_rank");

				String date_ser = request.getParameter("date_of_seniority");
				
				String class_pay = request.getParameter("class_pay");
				String pay_group = request.getParameter("pay_group");
				String unit_name = request.getParameter("unit_name");
				String first_name = request.getParameter("first_name");
				String middle_name = request.getParameter("middle_name");
				String last_name = request.getParameter("last_name");
				String merge_full = first_name + " " + middle_name + " " + last_name;
				
				String religion_hid = request.getParameter("religion_hid");
				String mother_tongue_hid = request.getParameter("mother_tongue_hid");
				String gender_hid = request.getParameter("gender_hid");
				String gender_other = request.getParameter("gender_other");
				
				
				String rank = request.getParameter("rank");
				
				String reli = request.getParameter("religion");
				String blood = request.getParameter("blood_group");
				String domicile = request.getParameter("domicile");
				
				String mt = request.getParameter("mother_tongue");
				String trade = request.getParameter("trade");
				
				String arm_ser = request.getParameter("arm_service");
				String regiment = request.getParameter("regiment");
				String unit_sus_no = request.getParameter("unit_sus_no");
				String roleAccess = session.getAttribute("roleAccess").toString();
				if (!roleAccess.equals("Unit")) {
					if(unit_sus_no == null || unit_sus_no.equals("null") || unit_sus_no.equals("")) {
						model.put("msg", "Please Enter Unit Sus No.");
						model.put("id2", id);
						return new ModelAndView("redirect:search_jco_url");
					} 
				 if (!validation.isOnlyAlphabetNumeric(unit_sus_no)) {
					 model.put("msg", validation.isOnlyAlphabetNumericMSG + "Unit Sus No");
						model.put("id2", id);
						return new ModelAndView("redirect:search_jco_url");
		            }
				 if (!validation.SusNoLength(unit_sus_no)) {
					 model.put("msg", validation.SusNoMSG);
						model.put("id2", id);
						return new ModelAndView("redirect:search_jco_url");
		            }
				else {
						roleSusNo = unit_sus_no;
					}
				
				if(unit_name == null || unit_name.equals("null") || unit_name.equals("")) {
					model.put("msg", "Please Enter Unit Name.");
					model.put("id2", id);
					return new ModelAndView("redirect:search_jco_url");
				} 
				}
				
				String country_other = request.getParameter("country_other");
				String state_other = request.getParameter("state_other");
				String district_other = request.getParameter("district_other");
				
				String religion_other = request.getParameter("religion_other");
				String mother_tongue_other = request.getParameter("mother_tongue_other");
				
				
				
				String army_no1 = request.getParameter("army_no1");
				String army_no2 = request.getParameter("army_no2");
				String army_no3 = request.getParameter("army_no3");
				
		

				if (request.getParameter("category") == null || request.getParameter("category").trim().equals("0")) {
					model.put("msg", "Please Select Category");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (request.getParameter("category").equals("JCO")) {
					if (army_no1 == null || army_no1.trim().equals("0")) {
						model.put("msg", " Please Select Army No");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
					if (army_no2 == null || army_no2.trim().equals("")) {
						model.put("msg", " Please Enter Army No");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
				} else {
					if (army_no2 == null || army_no2.trim().equals("")) {
						model.put("msg", " Please Enter Army No");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
				}

		         if (validation.isOnlyNumer(army_no2) == true) {
					model.put("msg", validation.isOnlyNumerMSG  + " Army No");
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				if (army_no2.length() < 1 || army_no2.length() > 9) {
					model.put("msg", "Please Enter Valid Army No");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (first_name == null || first_name.trim().equals("")) {
					model.put("msg", "Please Enter First Name");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (validation.isOnlyAlphabet(first_name) == false) {
					model.put("msg", "First Name "+ validation.isOnlyAlphabetMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				if (!validation.isvalidLength(first_name,validation.nameMax,validation.nameMin)) {
					model.put("msg", "First Name " + validation.isValidLengthMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
				}

				if (request.getParameter("gender") == null || request.getParameter("gender").trim().equals("0")) {
					model.put("msg", "Please Select Gender");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (gender_hid != null && gender_hid.equals("OTHER")) {
					if (gender_other == null || gender_other.trim().equals("")) {
					
						model.put("msg", "Please Enter Other Gender");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
					if (validation.isOnlyAlphabet(gender_other) == false) {
						model.put("msg", "Other Gender "+ validation.isOnlyAlphabetMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
				     }
					if (!validation.isvalidLength(gender_other,validation.nameMax,validation.nameMin)) {
						model.put("msg", "Other Gender " + validation.isValidLengthMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
					}
					}
				if (request.getParameter("rank") == null || request.getParameter("rank").trim().equals("0")) {
					model.put("msg", "Please Select Rank");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (request.getParameter("rank").equals("17")) {
					if (request.getParameter("rank_intake") == null || request.getParameter("rank_intake").trim().equals("0")) {
						model.put("msg", "Please Select Rank Intake");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
				}
				if (birth_date == null || birth_date.trim().equals("") || birth_date.equals("DD/MM/YYYY")) { 
					
					model.put("msg", "Please Select Date of Birth");
					model.put("id2", id);
					tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
					else  if (!validation.isValidDate(birth_date)){
						
							model.put("msg", validation.isValidDateMSG  + " of Birth");
							model.put("id2", id);
							 tx.rollback();
						    return new ModelAndView("redirect:search_jco_url");
					     }
				else {
					 
					birth_dt = format.parse(birth_date);
				}
				
			
			
				if (reli == null || reli.equals("0")) {
					model.put("msg", "Please Select Religion");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (religion_hid != null && religion_hid.equals("OTHERS")) {
					if (religion_other == null || religion_other.trim().equals("")) {
					
						model.put("msg", "Please Enter Other Religion");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
					if (validation.isOnlyAlphabet(religion_other) == false) {
						model.put("msg", "Other Religion "+ validation.isOnlyAlphabetMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
				     }
					if (!validation.isvalidLength(religion_other,validation.nameMax,validation.nameMin)) {
						model.put("msg", "Other Religion " + validation.isValidLengthMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
					}
					}
				if (mt == null || mt.equals("0")) {
					model.put("msg", "Please Select Mother Tongue");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (mother_tongue_hid != null && mother_tongue_hid.equals("OTHERS")) {
					if (mother_tongue_other == null || mother_tongue_other.trim().equals("")) {
					
						model.put("msg", "Please Enter Other Mother Tongue");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
					if (validation.isOnlyAlphabet(mother_tongue_other) == false) {
						model.put("msg", "Other Mother Tongue "+ validation.isOnlyAlphabetMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
				     }
					if (!validation.isvalidLength(mother_tongue_other,validation.nameMax,validation.nameMin)) {
						model.put("msg", "Other Mother Tongue " + validation.isValidLengthMSG);
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
					}
					}
				
				if (blood == null || blood.equals("0")) {
					model.put("msg", "Please Select Blood Group");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
		
				if (class_pay == null || class_pay.equals("0")) {
					model.put("msg", "Please Select Class(Pay)");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (pay_group == null || pay_group.equals("0")) {
					model.put("msg", "Please Select Pay Group");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (request.getParameter("record_office_sus") == null || request.getParameter("record_office_sus").trim().equals("")) {
					model.put("msg", "Please Enter Record Office(SUS No)");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
		
				 if (!validation.isOnlyAlphabetNumeric(request.getParameter("record_office_sus"))) {
					 model.put("msg", validation.isOnlyAlphabetNumericMSG + " Record Office Sus");
		             model.put("id2", id);
		             tx.rollback();
				     return new ModelAndView("redirect:search_jco_url");
		            }
				 if (!validation.SusNoLength(request.getParameter("record_office_sus"))) {
					 model.put("msg", validation.SusNoMSG);
		             model.put("id2", id);
		             tx.rollback();
				     return new ModelAndView("redirect:search_jco_url");
		            }
				 
					if (request.getParameter("record_office") == null || request.getParameter("record_office").trim().equals("")) {
						model.put("msg", "Please Enter Record Office");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
					
				if (enrl_date == null || enrl_date.trim().equals("") || enrl_date.equals("DD/MM/YYYY")) {
					model.put("msg", "Please Select Date of Enrollment");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				} 
				else  if (!validation.isValidDate(enrl_date)){
					model.put("msg", validation.isValidDateMSG  + " of Enrollment");
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				else {
					en_dt = format.parse(enrl_date);
				}
				//260194
	
				if (!request.getParameter("rank").equals("17")) {
					if (date_attestation == null || date_attestation.trim().equals("") || date_attestation.equals("DD/MM/YYYY")) {
						model.put("msg", "Please Select Date of Attestation");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					} 
				}
				
				if (!date_attestation.trim().equals(null) || !date_attestation.trim().equals("") || !date_attestation.equals("DD/MM/YYYY")) { 
				
					atts_dt = format.parse(date_attestation);
				}
				
				 if (!validation.isValidDate(date_attestation)){
						model.put("msg", validation.isValidDateMSG  + " of Attestation");
						model.put("id2", id);
						  tx.rollback();
					    return new ModelAndView("redirect:search_jco_url");
				     }
					
				
				if (p_comm.calculate_age(birth_dt, en_dt) < 16) {
					model.put("msg", "Please enter age above 16");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (date_ser == null || date_ser.trim().equals("") || date_ser.equals("DD/MM/YYYY")) {
					model.put("msg", "Please Select Date of Seniority");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				} 
				else  if (!validation.isValidDate(date_ser)){
					model.put("msg", validation.isValidDateMSG  + " of Seniority");
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				else {
					dt_ser = format.parse(date_ser);
				}
				if (p_comm.calculate_age(birth_dt, dt_ser) < 16) {
					model.put("msg", "Please enter age above 16");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (trade == null || trade.equals("0")) {
					model.put("msg", " Please Enter Trade");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (tos_date == null || tos_date.trim().equals("") || tos_date.equals("DD/MM/YYYY")) {
					model.put("msg", "Please Select Date of TOS");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				} 
				else  if (!validation.isValidDate(tos_date)){
					model.put("msg", validation.isValidDateMSG  + " of Tos");
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				else {
					tos_dt = format.parse(tos_date);
				}
				if (arm_ser == null || arm_ser.equals("0")) {
					model.put("msg", "Please Select Arm/Services");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				
				if(arm_ser.equals("0700") || arm_ser.equals("0800")) {
					if(regiment==null || regiment.trim().equals("") || regiment.equals("0")) {
						model.put("msg", "Please Select Regiment");
						model.put("id2", id);
						  tx.rollback();
						return new ModelAndView("redirect:search_jco_url");
					}
				}

		   
				if (request.getParameter("father_name") == null || request.getParameter("father_name").trim().equals("")) {
					model.put("msg", "Please Select Father's Name");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (validation.isOnlyAlphabet(request.getParameter("father_name")) == false) {
					model.put("msg","Father's Name "+ validation.isOnlyAlphabetMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				if (!validation.isvalidLength(request.getParameter("father_name"),validation.nameMax,validation.nameMin)) {
					model.put("msg", "Father's Name " + validation.isValidLengthMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
				}
	
				if (request.getParameter("mother_name") == null || request.getParameter("mother_name").trim().equals("")) {
					model.put("msg", "Please Select Mother's Name");
					model.put("id2", id);
					  tx.rollback();
					return new ModelAndView("redirect:search_jco_url");
				}
				if (validation.isOnlyAlphabet(request.getParameter("mother_name")) == false) {
					model.put("msg","Mother's Name "+ validation.isOnlyAlphabetMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
			     }
				if (!validation.isvalidLength(request.getParameter("mother_name"),validation.nameMax,validation.nameMin)) {
					model.put("msg", "Mother's Name " + validation.isValidLengthMSG);
					model.put("id2", id);
					  tx.rollback();
				    return new ModelAndView("redirect:search_jco_url");
				}
			
				
				
				if (date_of_rank == null || date_of_rank.trim().equals("") || date_of_rank.equals("DD/MM/YYYY")) {
					model.put("msg", "Please Select Date of Rank");
					return new ModelAndView("redirect:search_jco_url");
				} 
				else  if (!validation.isValidDate(date_of_rank)){
					model.put("msg", validation.isValidDateMSG  + " of Rank");
				    return new ModelAndView("redirect:search_jco_url");
			     }
				else {
					
					if(format.parse(date_of_rank).before(en_dt)) {
						model.put("msg", "Please Select Date of Rank Greater than Enrollment Date");
					    return new ModelAndView("redirect:search_jco_url");
					}else {
					dt_rank = format.parse(date_of_rank);
					}
				}
				
				String medicalStatus=jco_or.medical_categoryAction_jco(model,session,request,jco_id);
				if (!medicalStatus.equals("1")) {					
					model.put("msg", medicalStatus);
					    return new ModelAndView("redirect:search_jco_url");
				}
				
				
				
				 	try {
				 		String armsuffix = cj.generate_army_no(army_no2);
						if(request.getParameter("category").equals("OR"))
						{
							army_no1="";
						}
						String army_no = army_no1 + army_no2 + armsuffix;
					
						Boolean d = SJDAO.getArmyNoAlreadyExist(army_no,String.valueOf(id),null);
						
				 		 if (d = true) 
						   {  					
				 			Query q0 = session1.createQuery("select count(id) from TB_CENSUS_JCO_OR_PARENT where army_no=:army_no and id !=:id");
							q0.setParameter("army_no", army_no);  
							q0.setParameter("id", id); 
							
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_CENSUS_JCO_OR_PARENT set army_no=:army_no,full_name=:full_name,modified_by=:modified_by,modified_date=:modified_date,first_name=:first_name,middle_name=:middle_name,"
								 		+ " last_name=:last_name,date_of_birth=:date_of_birth,date_of_seniority=:date_of_seniority,father_dob=:father_dob,mother_dob=:mother_dob,place_of_birth=:place_of_birth,district_of_birth=:district_of_birth,"
											+ "state_of_birth=:state_of_birth,country_of_birth=:country_of_birth,nationality=:nationality,religion=:religion,blood_group=:blood_group,height=:height,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),marital_status=:marital_status,"
											+ "mother_tongue=:mother_tongue,arm_service=:arm_service,regiment=:regiment,unit_sus_no=:unit_sus,father_name=:father_name,father_place_birth=:father_place_birth,father_profession=:father_profession,"
											+ "mother_name=:mother_name,mother_place_birth=:mother_place_birth,mother_profession=:mother_profession,rank=:rank,gender=:gender,trade=:trade,pan_no=pgp_sym_encrypt(:pancard_number,current_setting('miso.version'))\r\n" + 
											",enroll_dt=:enroll_dt,"
											+ "father_service=:father_service,father_other=:father_other,father_personal_no=:father_personal_no,mother_service=:mother_service,mother_other=:mother_other,mother_personal_no=:mother_personal_no,"
											+" status=:status,class_pay=:class_pay,pay_group=:pay_group,country_other=:country_other,state_other=:state_other,district_other=:district_other,nationality_other=:nationality_other,"
											+"mother_tongue_other=:mother_tongue_other,religion_other=:religion_other,gender_other=:gender_other,father_proffother=:father_proffother,mother_proffother=:mother_proffother,domicile=:domicile,class_enroll=:class_enroll,"
											+"date_of_attestation=:date_of_attestation,record_office_sus=:record_office_sus,record_office=:record_office,date_of_tos=:date_of_tos,date_of_rank=:date_of_rank,rank_intake=:rank_intake where id=:id";
						                                
		
						    	  Query query = session1.createQuery(hql)
						    			  .setString("army_no", army_no)
						    			  .setString("full_name", merge_full)
						    			  .setString("first_name", jc.getFirst_name())
						    			  .setString("middle_name",jc.getMiddle_name())					    					  
						    			  .setString("last_name",jc.getLast_name())
						    			  .setString("place_of_birth", jc.getPlace_of_birth())				   		  						   		  		  
						    			  .setInteger("district_of_birth",jc.getDistrict_of_birth())	
						    			  .setInteger("state_of_birth",jc.getState_of_birth())
						    			  .setInteger("country_of_birth",jc.getCountry_of_birth())
						    			  .setInteger("nationality",jc.getNationality())
						    			  .setInteger("religion",jc.getReligion())
						    			  .setInteger("blood_group",jc.getBlood_group())
						    			  .setInteger("height",jc.getHeight())
						    			  .setInteger("rank",jc.getRank())
						    			  .setInteger("gender",jc.getGender())
						    			  .setString("aadhar_no", adhar_number)
						    			  .setString("pancard_number", jc.getPan_no())
						    			  .setInteger("marital_status",jc.getMarital_status())
						    			  .setInteger("mother_tongue",jc.getMother_tongue())  
						    			  	.setString("arm_service",jc.getArm_service())
						    			  	.setString("regiment",jc.getRegiment())
						    			  	.setString("unit_sus",jc.getUnit_sus_no())
						    			  	//.setString("unit_name",jc.getUnit_posted_to())
						    			  	.setString("father_name",jc.getFather_name())
						    			  	.setString("father_place_birth",jc.getFather_place_birth())
						    			  	.setInteger("father_profession",0)
						    			  	.setString("father_other", "")
						    			  	.setString("father_personal_no", jc.getFather_personal_no())
						    			  	.setString("father_service", jc.getFather_service())
						    			  	.setString("mother_name",jc.getMother_name())
						    			  	.setString("mother_place_birth",jc.getMother_place_birth())
						    			  	.setInteger("mother_profession",0)
						    			  	.setString("mother_other", "")
						    			  	.setString("mother_personal_no", jc.getMother_personal_no())
						    			  	.setString("mother_service", jc.getMother_service())
						    			    .setInteger("trade",jc.getTrade())
						
											.setString("modified_by", username)
											.setTimestamp("modified_date", new Date())
											.setTimestamp("date_of_birth", birth_dt)
											.setTimestamp("date_of_seniority", dt_ser)
											.setTimestamp("father_dob", f_dt)
											.setTimestamp("mother_dob", m_dt)
											.setTimestamp("enroll_dt", en_dt)
											.setInteger("status", jc.getStatus())
											.setInteger("class_enroll", jc.getClass_enroll())
											.setInteger("class_pay", jc.getClass_pay())
											.setInteger("pay_group", jc.getPay_group())
											.setInteger("domicile", jc.getDomicile())
											.setString("country_other", jc.getCountry_other())
											.setString("state_other", jc.getState_other())
											.setString("district_other", jc.getDistrict_other())
											.setString("nationality_other", jc.getNationality_other())
											.setString("mother_tongue_other",jc.getMother_tongue_other())
											.setString("religion_other", jc.getReligion_other())
											.setString("gender_other", gender_other)
											.setString("father_proffother", jc.getFather_proffother())
											.setString("mother_proffother", jc.getMother_proffother())
											
											.setTimestamp("date_of_attestation", atts_dt)
											.setString("record_office_sus", jc.getRecord_office_sus())
											.setString("record_office", jc.getRecord_office())
											.setTimestamp("date_of_tos", jc.getDate_of_tos())
											.setTimestamp("date_of_rank", dt_rank)
											.setString("rank_intake",jc.getRank_intake())
											.setInteger("id",id);	    	  
						    	  
						                    msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
						                    
						                    
						                    
						                    
						                    
						                    ////////////////////////qualification
						                    
						                    String spouse_quali_radio = request.getParameter("spouse_quali_radio");
						                    
						                    if(spouse_quali_radio.equals("yes"))
						                    	
						                    {
						                   int id1 = Integer.parseInt(request.getParameter("marr_ch_id1"));
						               
						                	String type = request.getParameter("spouse_quali_type");
						    				String examination_pass = request.getParameter("spouse_quali");
						    				String passing_year = request.getParameter("spouse_yrOfPass");
						    				String div_class = request.getParameter("spouse_div_class");
						    				String subject = request.getParameter("subject_sp");
						    				String institute = request.getParameter("spouse_institute_place");
						    				String degree = request.getParameter("quali_degree_spouse");
						    				String specialization = request.getParameter("spouse_specialization");
						    				String exam_other_qua = request.getParameter("exam_otherSpouse");
						    				String class_other_qua = request.getParameter("class_otherSpouse");
						    				String degree_other = request.getParameter("quali_deg_otherSpouse");
						    				String spec_other = request.getParameter("quali_spec_otherSpouse");						            		
						            		
						    				if (type == null || type.equals("0")) {
						    					model.put("msg", "Please Select The Qualification Type");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (examination_pass == null || examination_pass.equals("0")) {
						    					model.put("msg", "Please Select The Examination Passed");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (degree == null || degree.equals("0")) {
						    					model.put("msg", "Please Select The Degree Acquried");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (specialization == null || specialization.equals("0")) {
						    					model.put("msg", "Please Select The Specialization");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (passing_year == null || passing_year.trim().equals("")) {
						    					model.put("msg", "Please Enter The Year of passing");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (div_class == null || div_class.equals("0")) {
						    					model.put("msg", "Please Select The Div/Grade/PCT(%)");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				
						    				if (institute == null || institute.trim().equals("")) {
						    					model.put("msg", "Please Enter The Institute & place");
						    					model.put("id2", id);
						    					  tx.rollback();
						    					return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    				}
						    				if (validation.isOnlyAlphabet(institute) == false) {
						    					model.put("msg", validation.isOnlyAlphabetMSG);
						    					model.put("id2", id);
						    					  tx.rollback();
						    				    return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						    	             }
						    				 if (!validation.isvalidLength(institute,validation.nameMax,validation.nameMin)) {
						 						model.put("msg", "Institute" + validation.isValidLengthMSG);
						 						model.put("id2", id);
						 						  tx.rollback();
						 					    return new ModelAndView("redirect:Edit_JCOs_lanUrl");
						 					}
						    				Query q01= session1.createQuery("select count(id) from TB_CENSUS_SPOUSE_QUALIFICATION_JCO where jco_id=:id");
											
											q01.setParameter("id", id); 
											
											Long c1 = (Long) q01.uniqueResult();
											
											if(c1==0) {
												TB_CENSUS_SPOUSE_QUALIFICATION_JCO sq = new TB_CENSUS_SPOUSE_QUALIFICATION_JCO();					        					        			
						        				
						        				sq.setType(Integer.parseInt(type));
						        				sq.setExamination_pass(Integer.parseInt(examination_pass));
						        				sq.setPassing_year(Integer.parseInt(passing_year));
						        				sq.setDiv_class(div_class);
						        				sq.setSubject(subject);
						        				sq.setInstitute(institute);
						        				sq.setDegree(Integer.parseInt(degree));
						        				sq.setSpecialization(Integer.parseInt(specialization));
						        				sq.setExam_other(exam_other_qua);
						        				sq.setClass_other(class_other_qua);
						        				sq.setDegree_other(degree_other);
						        				sq.setSpecialization_other(spec_other);
						        				sq.setCreated_by(username);
						        				sq.setCreated_date(new Date());
						        				sq.setJco_id(id);
						        				sq.setSpouse_id(id1);
						        				session1.save(sq);
						        				session1.flush();
						        				session1.clear();      				
						        					
											}else {
											
						                    String hqlQuli = "update TB_CENSUS_SPOUSE_QUALIFICATION_JCO set modified_by=:modify_by ,modified_date=:modify_on ,type=:type,"
						    						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute";
						                    hqlQuli += ",examination_pass=:examination_pass,exam_other=:exam_other,class_other=:class_other ,specialization_other=:specialization_other ,degree_other=:degree_other ";
						                    hqlQuli += ",specialization=:specialization,degree=:degree,status=0 ";
						                    hqlQuli += " where  jco_id=:jco_id";
						    				Query queryQuli = session1.createQuery(hqlQuli).setInteger("type", Integer.parseInt(type))
						    						
						    						.setString("div_class", div_class)
						    						.setString("subject", subject)
						    						.setString("institute", institute)
						    						.setString("modify_by", username)
						    						.setTimestamp("modify_on", new Date())
						    						.setString("exam_other", exam_other_qua)
						    						.setString("class_other", class_other_qua)
						    						.setString("specialization_other", spec_other)
						    						.setString("degree_other", degree_other)
						    						.setInteger("jco_id", id);
						    				if (examination_pass != null && !examination_pass.equals("0"))
						    					queryQuli.setInteger("examination_pass", Integer.parseInt(examination_pass));
						    				if (degree != null && !degree.equals("0"))
						    					queryQuli.setInteger("degree", Integer.parseInt(degree));
						    				if (specialization != null && !specialization.equals("0"))
						    					queryQuli.setInteger("specialization", Integer.parseInt(specialization));
						    				if (passing_year != null && !passing_year.equals(""))
						    					queryQuli.setInteger("passing_year", Integer.parseInt(passing_year));
						    				msg = queryQuli.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";
						                    }
						                    }
						    				
						    				//////////////////////siblings
						               String adhar_numbers_sib = "";             

				 	}
					}
				 		model.put("msg", msg);
				 	}
				 	catch(RuntimeException e){
						  e.printStackTrace();
			              try{
			                      tx.rollback();
			                      model.put("msg", "roll back transaction");
			              }catch(RuntimeException rbe){
			                      model.put("msg", "Couldn t roll back transaction " + rbe);
			              }
			              throw e;
			             
					}finally{
						if(session1!=null){
							session1.close();
						}
					}
				 		 
				 		
				return new ModelAndView("redirect:search_jco_url");
			}
			
			
			/// bisag v2 260822  (converted to Datatable)



			@RequestMapping(value = "/GetSearch_censusCount_jco", method = RequestMethod.POST)
				public @ResponseBody long GetSearch_censusCount_jco(String Search,String orderColunm,String orderType,HttpSession sessionUserId
						,String msg,String unit_sus_no,String unit_name,String personnel_no,
						String rank,String status, String cr_by,String cr_date) throws SQLException {
					
					 String roleType = sessionUserId.getAttribute("roleType").toString();
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					
					return jco.GetSearch_censusCount_jco(Search, orderColunm, orderType, sessionUserId, unit_sus_no, unit_name,personnel_no,
							 rank, status,cr_by,cr_date,roleType);
				}
				

				
				@RequestMapping(value = "/GetSearch_censusdata_jco", method = RequestMethod.POST)
				public @ResponseBody List<Map<String, Object>> GetSearch_censusdata_jco(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
						,String unit_sus_no,String unit_name,String personnel_no,
						String rank,String status, String cr_by,String cr_date) throws SQLException {
					 String roleType = sessionUserId.getAttribute("roleType").toString();
					
					return jco.GetSearch_censusdata_jco(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_sus_no, unit_name,personnel_no,
							 rank, status,cr_by,cr_date,roleType);
				}


				 		 



}
