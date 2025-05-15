package com.controller.psg.Commissioning;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Report.Report_3008DAO;
import com.dao.psg.Transaction.Search_Commissioning_LetterDAO;
import com.dao.psg.Transaction.Search_PostOutDao;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_BIRTH;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_CADET;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COMMISSION;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_COURSE;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_GENDER;
import com.models.psg.Transaction.TB_PSG_UPDATE_COMM_UNIT;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER_HISTORY;
import com.models.psg.update_census_data.TB_CHANGE_NAME;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;
import com.models.psg.update_census_data.TB_CHANGE_TNAI_NO;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_Commissioning_Letter_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Report_3008DAO report_3008DAO;
	
	@Autowired
	private Search_PostOutDao pod;
	
	@Autowired
	private Search_Commissioning_LetterDAO SLDAO;

	Psg_CommonController p_comm = new Psg_CommonController();
	
	PsgDashboardController das = new PsgDashboardController();
	
	ValidationController valid = new ValidationController();
	
	@RequestMapping(value = "/admin/Search_Commissioning_LetterUrl",method = RequestMethod.GET)
	 public ModelAndView Search_Commissioning_LetterUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			String yyyy = new SimpleDateFormat("yyyy").format(date);
			String to_date = "01/01/"+yyyy;
			
			String roleType = session.getAttribute("roleType").toString();
	 		String roleAccess = session.getAttribute("roleAccess").toString();
	 		System.err.println("Role Type is " + roleType + "  RoleAccess is " + roleAccess);	
		
		 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", session.getAttribute("roleid").toString());
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
 		 
		 Mmap.put("date", date1);
		 Mmap.put("to_date",to_date );
		 Mmap.put("msg", msg);	
		 Mmap.put("getParentArmList", p_comm.getParentArmList());
		 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

		 return new ModelAndView("Search_Commissioning_LetterTiles");
	 }

	 @RequestMapping(value = "/admin/GetSearch_Com_letter", method = RequestMethod.POST)
		public ModelAndView GetSearch_Com_letter(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
			    @RequestParam(value = "unit_posted_to2", required = false) String unit_posted_to,
				@RequestParam(value = "parent_arm1", required = false) String parent_arm,
				@RequestParam(value = "personnel_no1", required = false) String personnel_no,
				@RequestParam(value = "name1", required = false) String name,
				@RequestParam(value = "status1", required = false) String status,
				@RequestParam(value = "type_of_comm_granted2", required = false) String type_of_comm_granted,
				@RequestParam(value = "date_of_commission1", required = false) String date_of_commission,
				@RequestParam(value = "frm_dt1", required = false) String frm_dt1,
				@RequestParam(value = "to_dt1", required = false) String to_dt1,
				@RequestParam(value = "IsMns", required = false) String ismns) throws ParseException{
		 
		 Boolean IsMns = Boolean.parseBoolean(ismns);
		 String redirect = "Search_Commissioning_LetterUrl";
		 String Tiles ="Search_Commissioning_LetterTiles";
		 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", session.getAttribute("roleid").toString());
		 if(IsMns == true) {
			 val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", session.getAttribute("roleid").toString());
			 redirect = "Search_Commissioning_MnsLetterUrl";
			 Tiles ="Search_Commissioning_MnsLetterTiles";
		 }	 
		
         if(val == false) {
                 return new ModelAndView("AccessTiles");
         }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 unit_posted_to = unit_posted_to.replace("&#40;", "(");
		 unit_posted_to = unit_posted_to.replace("&#41;", ")");
		 
		 if(unit_sus_no!="") {
	    	  if (!valid.SusNoLength(unit_sus_no)) {
					Mmap.put("msg", valid.SusNoMSG);
					return new ModelAndView("redirect:"+redirect+"");
				}
	    	  
	    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:"+redirect+"");
				}
	      }
		 
		 if(unit_posted_to!="") {
			  if (!valid.isUnit(unit_posted_to)) {
				  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
					return new ModelAndView("redirect:"+redirect+"");
				}
	    	  
	    	  if (!valid.isvalidLength(unit_posted_to, valid.nameMax, valid.nameMin)) {
					Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
					return new ModelAndView("redirect:"+redirect+"");
				}
	      }
		 
		   if(personnel_no!="") {
			   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
					return new ModelAndView("redirect:"+redirect+"");
				}
			  
			      if (personnel_no.length() < 7 || personnel_no.length() > 9) {
						Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
						return new ModelAndView("redirect:"+redirect+"");
					}
		     } 
		   
		   if(name!="") {
		    	  if (!valid.isOnlyAlphabet(name)) {
						Mmap.put("msg", "Name " + valid.isOnlyAlphabetMSG);
						return new ModelAndView("redirect:"+redirect+"");
					}

					if (!valid.isvalidLength(name, valid.nameMax, valid.nameMin)) {
						Mmap.put("msg", "Name " + valid.isValidLengthMSG);
						return new ModelAndView("redirect:"+redirect+"");
					}
		      }
		 //26-01-1994
			if (!date_of_commission.equals("") && date_of_commission !="" && !date_of_commission.equals("DD/MM/YYYY")) {
				
				if (!valid.isValidDate(date_of_commission)) {
					Mmap.put("msg", valid.isValidDateMSG + " of Commission");
					return new ModelAndView("redirect:"+redirect+"");
				}
			}
			
			if (!frm_dt1.equals("") && frm_dt1 !="" && !frm_dt1.equals("DD/MM/YYYY")) {
							
							if (!valid.isValidDate(frm_dt1)) {
								Mmap.put("msg", valid.isValidDateMSG + " From");
								return new ModelAndView("redirect:"+redirect+"");
							}
						}
			
			if (!to_dt1.equals("") && to_dt1 !="" && !to_dt1.equals("DD/MM/YYYY")) {
				if (!valid.isValidDate(to_dt1)) {
					Mmap.put("msg", valid.isValidDateMSG + " To");
					return new ModelAndView("redirect:"+redirect+"");
				}
			}


		if (date_of_commission != null  && !date_of_commission.trim().trim().equals("") && !date_of_commission.equals("DD/MM/YYYY")) {
			 String afom[]=date_of_commission.split("/");
			 date_of_commission = afom[2]+"-"+afom[1]+"-"+afom[0];

			 }

		if (frm_dt1 != null && !frm_dt1.trim().equals("") && !frm_dt1.equals("DD/MM/YYYY")) {
			 String afom[]=frm_dt1.split("/");
			 frm_dt1 = afom[2]+"-"+afom[1]+"-"+afom[0];

			 }
		if(to_dt1 == null || to_dt1.trim().equals("") || to_dt1.equals("DD/MM/YYYY") ) {
			Date d = new Date();
//			to_dt1 = d.toString();
			//26-01-1994
			 to_dt1 = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		else {
			 String afom[]=to_dt1.split("/");
			 to_dt1 = afom[2]+"-"+afom[1]+"-"+afom[0];
		}
	
		 		String roleType = session.getAttribute("roleType").toString();
				ArrayList<ArrayList<String>> list = SLDAO.Search_comm_letter(unit_sus_no,parent_arm,personnel_no,name,status,type_of_comm_granted,date_of_commission,frm_dt1, to_dt1,roleType);
				Mmap.put("list", list);
				Mmap.put("size", list.size());
				Mmap.put("unit_sus_no2", unit_sus_no);
		        Mmap.put("unit_posted_to2", unit_posted_to);
				Mmap.put("parent_arm1", parent_arm);
				Mmap.put("personnel_no1", personnel_no);
				Mmap.put("name1", name);
				Mmap.put("status1", status);
				Mmap.put("type_of_comm_granted2", type_of_comm_granted);
				Mmap.put("date_of_commission1", date_of_commission);
				Mmap.put("frm_dt1", frm_dt1);
			 	Mmap.put("to_dt1", to_dt1);
				Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());
				Mmap.put("getParentArmList", p_comm.getParentArmList());
			return new ModelAndView(Tiles,"Search_Com_letterCMD",new TB_TRANS_PROPOSED_COMM_LETTER());
		}
	 
	 
	 /******************************Delete For Commissioning Letter***********************************/
	    
	/*	@RequestMapping(value = "/Delete_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Commissioning_Letter(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			try {
				
				 
				String hqlUpdate = "delete from TB_TRANS_PROPOSED_COMM_LETTER where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				
				sessionHQL.flush();
				sessionHQL.clear();
				 
//				String hql1 = "delete from TB_TRANS_PROPOSED_COMM_LETTER_HISTORY where comm_his_id=:comm_his_id";
//				int app1 = sessionHQL.createQuery(hql1).setInteger("comm_his_id", id).executeUpdate();
				tx.commit();
				
				

				if (app > 0  ) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				tx.rollback();
				model.put("msg",liststr.get(0));
			}
			finally {
				if (sessionHQL != null) {
					sessionHQL.close();

				}
			}
			return new ModelAndView("redirect:Search_Commissioning_LetterUrl");
		}*/
	 
	 

		@RequestMapping(value = "/Delete_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Commissioning_Letter(@ModelAttribute("id1") BigInteger id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			try {
				
				 
				String hqlUpdate = "delete from TB_TRANS_PROPOSED_COMM_LETTER where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setBigInteger("id", id).executeUpdate();
				
				sessionHQL.flush();
				sessionHQL.clear();
				 
//				String hql1 = "delete from TB_TRANS_PROPOSED_COMM_LETTER_HISTORY where comm_his_id=:comm_his_id";
//				int app1 = sessionHQL.createQuery(hql1).setInteger("comm_his_id", id).executeUpdate();
				tx.commit();
				
				

				if (app > 0  ) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				tx.rollback();
				model.put("msg",liststr.get(0));
			}
			finally {
				if (sessionHQL != null) {
					sessionHQL.close();

				}
			}
			return new ModelAndView("redirect:Search_Commissioning_LetterUrl");
		}
	 
		/******************************Relegate For Commissioning Letter***********************************/
	/*	@RequestMapping(value = "/Relegate_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Relegate_Commissioning_Letter(@ModelAttribute("idr") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_TRANS_PROPOSED_COMM_LETTER where id=:id";
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
			return new ModelAndView("redirect:Search_Commissioning_LetterUrl");
		}*/
		
		
		@RequestMapping(value = "/Relegate_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Relegate_Commissioning_Letter(@ModelAttribute("idr") BigInteger id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_TRANS_PROPOSED_COMM_LETTER where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setBigInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Search_Commissioning_LetterUrl");
		}

		
		
		/******************************Update For Commissioning Letter***********************************/ 

		
		@RequestMapping(value = "/Edit_Commissioning_LetterUrl")
		public ModelAndView Edit_Commissioning_LetterUrl(
				@ModelAttribute("id2") BigInteger updateid,
				@ModelAttribute("status6") String status, 
				@ModelAttribute("unit_sus_no6") String unit_sus_no, 
				@ModelAttribute("unit_posted_to6") String unit_posted_to, 
				@ModelAttribute("parent_arm6") String parent_arm, 
				@ModelAttribute("personnel_no6") String personnel_no, 
				@ModelAttribute("name6") String name, 
				@ModelAttribute("type_of_comm_granted6") String type_of_comm_granted, 
				@ModelAttribute("date_of_commission6") String date_of_commission, 
				@ModelAttribute("frm_dt6") String frm_dt, 
			
				
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
			
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

			
					
			TB_TRANS_PROPOSED_COMM_LETTER authDetails = SLDAO.getSearch_com_letterByid(updateid);
			
			
			 
			 Mmap.put("Edit_Search_Com_letterCMD", authDetails);
			 Mmap.put("getCourseNameList", p_comm.getCourseNameList());          
			 Mmap.put("getGenderList", p_comm.getGenderList());
			 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());       			
			 Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
			 Mmap.put("getPersonalType", p_comm.getPersonalType());
			 Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
			 Mmap.put("getParentArmList", p_comm.getParentArmList());
			Mmap.put("msg", msg);
			Mmap.put("status6", status);
			Mmap.put("unit_sus_no6", unit_sus_no);
			Mmap.put("unit_posted_to6", unit_posted_to);
			Mmap.put("parent_arm6", parent_arm);
			Mmap.put("personnel_no6", personnel_no);
			Mmap.put("name6", name);
			Mmap.put("type_of_comm_granted6", type_of_comm_granted);
			Mmap.put("date_of_commission6", date_of_commission);
			Mmap.put("frm_dt6", frm_dt);
			Mmap.put("IsMns", "False");
	
			return new ModelAndView("Edit_Commissioning_LetterTiles");
		}
		
		
		
		
		///////view start/////
		
		@RequestMapping(value = "/view_Commissioning_LetterUrl",method = RequestMethod.POST)
		public ModelAndView view_Commissioning_LetterUrl(
				@ModelAttribute("id5") BigInteger updateid,
				@ModelAttribute("status5") String status, 
				@ModelAttribute("unit_sus_no5") String unit_sus_no, 
				@ModelAttribute("unit_posted_to5") String unit_posted_to, 
				@ModelAttribute("parent_arm5") String parent_arm, 
				@ModelAttribute("personnel_no5") String personnel_no, 
				@ModelAttribute("name5") String name, 
				@ModelAttribute("type_of_comm_granted5") String type_of_comm_granted, 
				@ModelAttribute("date_of_commission5") String date_of_commission, 
				@ModelAttribute("frm_dt5") String frm_dt, 
				@ModelAttribute("to_dt5") String to_dt, 
				  	
				
				ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
						
			
			TB_TRANS_PROPOSED_COMM_LETTER authDetails = SLDAO.getSearch_com_letterByid(updateid);
			
			
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionEdit.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			 Mmap.put("view_Search_Com_letterCMD", authDetails);
			 Mmap.put("getCourseNameList", p_comm.getCourseNameList());          
			 Mmap.put("getGenderList", p_comm.getGenderList());
			 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());       
			 Mmap.put("getRegtList", p_comm.getRegtList(""));
			 Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
			 Mmap.put("getPersonalType", p_comm.getPersonalType());
			 Mmap.put("getPersonalRemainder", p_comm.getPersonalRemainder());
			 Mmap.put("getParentArmList", p_comm.getParentArmList());
			Mmap.put("msg", msg);
			Mmap.put("status5", status);
			Mmap.put("unit_sus_no5", unit_sus_no);
			Mmap.put("unit_posted_to5", unit_posted_to);
			Mmap.put("parent_arm5", parent_arm);
			Mmap.put("personnel_no5", personnel_no);
			Mmap.put("name5", name);
			Mmap.put("type_of_comm_granted5", type_of_comm_granted);
			Mmap.put("date_of_commission5", date_of_commission);
			Mmap.put("frm_dt5", frm_dt);
			Mmap.put("to_dt5", to_dt);
			 	
			
			return new ModelAndView("view_Commissioning_LetterTiles");
		}
		///////end view////
		
		@RequestMapping(value = "/Edit_Search_Com_letterAction", method = RequestMethod.POST)
		public ModelAndView Edit_Search_Com_letterAction(@ModelAttribute("Edit_Search_Com_letterCMD") TB_TRANS_PROPOSED_COMM_LETTER BAN,BindingResult result,
				HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", session.getAttribute("roleid").toString());
			String auth = request.getParameter("IsMns");
			String redirect ="Search_Commissioning_LetterUrl";
			if(auth.equals("True")) {
				val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", session.getAttribute("roleid").toString());
				redirect = "Search_Commissioning_MnsLetterUrl";
			}
			
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
	        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			String username = session.getAttribute("username").toString();	
			BigInteger id = new BigInteger(request.getParameter("id"));
			Date auth_dt = null;
			Date comm_dt = null;
			Date seniority_dt = null;
			Date rank_dt = null;
			Date birth_dt = null;
			Date Tos_dt = null;

	 		String authority_date = request.getParameter("date_of_authority");
	 		String commission_date = request.getParameter("date_of_commission");

			String parent_sus_no = request.getParameter("parent_sus_no");
			String parent_unit = request.getParameter("parent_unit");


			    
	 		String seniority_date = request.getParameter("date_of_seniority");    
	 		String tos_date = request.getParameter("date_of_tos"); 
	 		String rank_date = request.getParameter("date_of_rank"); 
	 		
	 		String regiment = request.getParameter("regiment");
			String parent_armLb = request.getParameter("parent_armLb");
	 		
	 		String birth_date = request.getParameter("date_of_birth");    
	 		String persnl_no1 = request.getParameter("persnl_no1");
            String persnl_no2 = request.getParameter("persnl_no2");
            String persnl_no3 = request.getParameter("persnl_no3");
            String tnai_no = request.getParameter("tnai_no");
         
            String persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
		
			Date date1=null;
			
			Mmap.put("id2",id);
			if(request.getParameter("authority") == null || request.getParameter("authority").trim().equals("")){ 
	            Mmap.put("msg", "Please Enter Authority");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			else {
				BAN.setAuthority(p_comm.StringValidationForName(request.getParameter("authority").toString()));
			}
			
			if (!valid.isValidAuth(request.getParameter("authority"))) {
				Mmap.put("msg", valid.isValidAuthMSG + "Authority");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}

			if (!valid.isvalidLength(request.getParameter("authority"), valid.authorityMax, valid.authorityMin)) {
				Mmap.put("msg", "Authority " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if (!valid.isValidDate(authority_date)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Authority");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if (authority_date==null ||  authority_date.equals("DD/MM/YYYY") || authority_date.trim().equals("")) { 
				Mmap.put("msg","Please Select Authority Date");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl"); } 
			     else { 
				auth_dt = format.parse(authority_date); 
			}
			if (request.getParameter("persnl_no1") == null || request.getParameter("persnl_no1").equals("-1")
					|| request.getParameter("persnl_no1").equals("0")) {
				Mmap.put("msg", "Please Select Personal No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			if (request.getParameter("persnl_no2") == null || request.getParameter("persnl_no2").trim().equals("")) {
				Mmap.put("msg", "Please Enter Personal No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if (valid.isOnlyNumer(request.getParameter("persnl_no2")) == true) {
				Mmap.put("msg", "Personal No" + valid.isOnlyNumerMSG);
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if (request.getParameter("persnl_no2").length() != 5) {
				Mmap.put("msg", "Please Enter Valid Personal No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			String persuffix = p_comm.getPersonnelNuSuffix(persnl_no2);

			if (!persnl_no3.equals(persuffix)) {
				Mmap.put("msg", "Please Enter Valid Personal No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}

			if (persnl_no.length() < 7 || persnl_no.length() > 9) {
				Mmap.put("msg", "Please Enter Valid Personal No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if (request.getParameter("cadet_no") == null || request.getParameter("cadet_no").trim().equals("")) {
				Mmap.put("msg", "Please Enter Cadet No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			else {
				BAN.setCadet_no(request.getParameter("cadet_no").trim());
			}
			
			if (!valid.validateSlash(request.getParameter("cadet_no"))) {
				Mmap.put("msg", valid.validateSlashMSG + "Cadet No");
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
		}
			
			 if (request.getParameter("persnl_no1").equals("NR")
						|| request.getParameter("persnl_no1").equals("NS")) {
				 if (!valid.validateSlash(request.getParameter("tnai_no"))) {
						Mmap.put("msg", valid.validateSlashMSG + "TNAI No");
						return new ModelAndView("redirect:Prop_Comm_letter_new");
					
				}
			 }
			
			if(request.getParameter("batch_no") == null || request.getParameter("batch_no").trim().equals("")){ 
	            Mmap.put("msg", "Please Enter Course No");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			
			if (valid.isOnlyNumer(request.getParameter("batch_no")) == true) {
				Mmap.put("msg", "Course No " + valid.isOnlyNumerMSG);
				return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}
			
			if(request.getParameter("course") == null || request.getParameter("course").equals("0")){ 
	            Mmap.put("msg", "Please Select Course");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        }
			if(request.getParameter("name") == null || request.getParameter("name").trim().equals("")){ 
	            Mmap.put("msg", "Please Enter Name");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			if(Pattern.matches("^[ A-Za-z]+$", request.getParameter("name").toString())) {
				
				BAN.setName(p_comm.StringValidationForName(request.getParameter("name").toString()));
			}
			
			 if (!valid.isvalidLength(request.getParameter("name"), valid.nameMax, valid.nameMin)) {
					Mmap.put("msg", "Name " + valid.isValidLengthMSG);
					return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
				}
			
			if(request.getParameter("gender") == null || request.getParameter("gender").equals("0")){ 
	            Mmap.put("msg", "Please Select Gender");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        }
			if(request.getParameter("type_of_comm_granted") == null || request.getParameter("type_of_comm_granted").equals("0")){ 
	            Mmap.put("msg", "Please Select Type of Commission Granted");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			if (commission_date==null || commission_date.equals("DD/MM/YYYY") || commission_date.trim().equals("")) {
	            Mmap.put("msg", "Please Enter Date of Commission");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			else {
				comm_dt = format.parse(commission_date);
			}
			String  dt_commission1 =commission_date.substring(6,10);
	 		String  dt_commission2 =commission_date.substring(3,5);
			
			if (seniority_date==null || seniority_date.equals("DD/MM/YYYY") || seniority_date.trim().equals("")) {
	            Mmap.put("msg", "Please Enter Date of Seniority");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			else {
				 seniority_dt = format.parse(seniority_date);
			}
			
		
		
			if(request.getParameter("rank") == null || request.getParameter("rank").equals("0")){ 
	            Mmap.put("msg", "Please Select Rank");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        }
			String dt_rank1 =rank_date.substring(6,10);
	 		String dt_rank2 =rank_date.substring(3,5);
			if (rank_date==null || rank_date.equals("DD/MM/YYYY") || rank_date.trim().equals("")) {
				  Mmap.put("msg", "Please Select Date of Rank");
				  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			} else {
				rank_dt = format.parse(rank_date);
		    }
			
			
			/*if ((Integer.parseInt(dt_commission1) != Integer.parseInt(dt_rank1)) || (Integer.parseInt(dt_commission2) != Integer.parseInt(dt_rank2))) {
				 Mmap.put("msg"," Date of Rank should be equals to Date of Commission ");
				 return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
			}*/
			
			
			
			if (birth_date==null || birth_date.equals("DD/MM/YYYY") || birth_date.trim().equals("")) {
		           Mmap.put("msg", "Please Enter Date of Birth");
		           return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
            }  else {
			 birth_dt = format.parse(birth_date);
			  String is=birth_date+"-01";
			 date1=format.parse(is);
		    }
			if (p_comm.calculate_age(birth_dt, comm_dt) < 16) {
				Mmap.put("msg", "Please enter age above 16");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
			
			if (p_comm.calculate_age(birth_dt, new Date()) < 16) {
				Mmap.put("msg", "Please enter age above 16");
				return new ModelAndView("redirect:Prop_Comm_letter_new");
			}
			if (birth_date != null && BAN.getDate_of_commission() != null){ 
				if (date1.compareTo(BAN.getDate_of_commission()) > 0) {
					  Mmap.put("msg", " Date of Commission  should not be before dt of Birth ");
					  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
				} 
			}
			
			if (birth_date != null && BAN.getDate_of_seniority() != null){ 
				if (date1.compareTo(BAN.getDate_of_seniority()) > 0) {
				  Mmap.put("msg", "  Date of Seniority  should not be before dt of Birth ");
				  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl"); 
			    }
			}
			if (birth_date != null && BAN.getDate_of_rank() != null){ 
				if (date1.compareTo(BAN.getDate_of_rank()) > 0) {
					  Mmap.put("msg", "Date of Rk  should not be before dt of Birth ");
					  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl"); 
				}
			}
			

		
			if(request.getParameter("unit_sus_no") == null || request.getParameter("unit_sus_no").trim().equals("")){ 
	            Mmap.put("msg", "Please Enter Unit Sus No");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			
			if(request.getParameter("unit_posted_to") == null || request.getParameter("unit_posted_to").trim().equals("")){ 
	            Mmap.put("msg", "Please Enter Unit Posted To");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        }
			if (tos_date==null || tos_date.equals("DD/MM/YYYY") || tos_date.trim().equals("")) {
	            Mmap.put("msg", "Please Enter Date of TOS.");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
			else {
				 Tos_dt = format.parse(tos_date);
			}
			
			if (birth_date != null && BAN.getDate_of_tos() != null){ 
				if (date1.compareTo(BAN.getDate_of_tos()) > 0) {
				  Mmap.put("msg", "  Date of Tos  should not be before dt of Birth ");
				  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl"); 
			    }
			 }
			if (format.parse(tos_date).compareTo(format.parse(commission_date)) < 0) {
				 Mmap.put("msg", "Date of TOS can be greater than Date of Commission or equal to.");
				  return new ModelAndView("redirect:Edit_Commissioning_LetterUrl"); 
			}
			if(request.getParameter("parent_arm") == null || request.getParameter("parent_arm").equals("0")){ 
	            Mmap.put("msg", "Please Enter Parent Arm/Service");
	            return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
	        } 
	
			
			if( request.getParameter("parent_arm").equals("0700") || request.getParameter("parent_arm").equals("0800")) {
				if(regiment==null || regiment.trim().equals("") || regiment.equals("0")) {
					Mmap.put("msg", "Please Select Regiment");
					return new ModelAndView("redirect:Edit_Commissioning_LetterUrl");
				}
			}
			else {
				regiment="0";
			}
			  
			
		try {		
			//Boolean d = SLDAO.getPersonnelNoAlreadyExist(persnl_no);
					 BAN.setModified_by(username);
					 BAN.setModified_date(new Date());
					 BAN.setPersonnel_no(persnl_no);
					 BAN.setRegiment(regiment);
					 BAN.setTnai_no(tnai_no);
					// BAN.setParent_sus_no(parent_sus_no);
					// BAN.setParent_unit(parent_unit);

					 
									
						 String msg1= SLDAO.GetUpdateSearch_Comm_Letter(BAN,username);
						 
						 if(msg1== "update") {
							 Mmap.put("msg", "Data Updated Successfully.");
						 }else {
							 Mmap.put("msg", "Data Not Updated.");
						 }
		  				
					
				  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      Mmap.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:"+redirect+"");
		}
		
		@RequestMapping(value = "/Approve_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Approve_Commissioning_Letter(@ModelAttribute("id3") BigInteger id1,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "status", required = false) String status,
				@RequestParam(value = "modified_by", required = false) String modified_by,
				@RequestParam(value = "modified_date", required = false) String modified_date,
				@RequestParam(value = "CheckVal3", required = false) String CheckVal3,
				@RequestParam(value = "isMns", required = false) String isMns,
				Authentication authentication) {
			
			String[] id_list = CheckVal3.split(":");
			 Boolean val=roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
			 String redirect = "Search_Commissioning_LetterTiles";
			if(isMns.equals("True")) {
				val= roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", sessionA.getAttribute("roleid").toString());
				redirect = "Search_Commissioning_MnsLetterTiles";
			}
			
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			     List<String> liststr = new ArrayList<String>();
			//try {
			     String username = sessionA.getAttribute("username").toString();
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				
				 int app = 0;
				 for (int ik = 0; ik < id_list.length; ik++) {
					 BigInteger id = new BigInteger((id_list[ik]));
					 
					
					 TB_TRANS_PROPOSED_COMM_LETTER BAN;
					 if(id1.intValue()!=0) {
					
						 BAN = (TB_TRANS_PROPOSED_COMM_LETTER)sessionHQL.get(TB_TRANS_PROPOSED_COMM_LETTER.class, id1); 
					 }
					 else {
						
				          BAN = (TB_TRANS_PROPOSED_COMM_LETTER)sessionHQL.get(TB_TRANS_PROPOSED_COMM_LETTER.class, id);
					 }
				  TB_CHANGE_NAME med = new TB_CHANGE_NAME();
				   med.setName(BAN.getName());
				   med.setComm_id(BAN.getId());
				  
				   med.setAuthority(BAN.getAuthority());
				   med.setDate_of_authority(BAN.getDate_of_authority());
				   med.setStatus(1);
				   med.setCreated_by(BAN.getCreated_by());
	               med.setCreated_date(BAN.getCreated_date());
	               med.setModified_by(username);
	               med.setModified_date(new Date());
				 
	               BigInteger i = BigInteger.ZERO;
                   i = BAN.getId();
                   
				   TB_CHANGE_OF_RANK rnk = new TB_CHANGE_OF_RANK();
			       rnk.setRank(BAN.getRank());
			       rnk.setAuthority(BAN.getAuthority());
			       rnk.setDate_of_authority(BAN.getDate_of_authority());
			       rnk.setComm_id(i);
			      
			       rnk.setDate_of_rank(BAN.getDate_of_rank());
			       rnk.setStatus(1);
			       rnk.setCreated_by(BAN.getCreated_by());
			       rnk.setCreated_date(BAN.getCreated_date());
			       rnk.setModified_by(username); 
			       rnk.setModified_date(new Date());
			       

                   
				   TB_PSG_CHANGE_OF_COMISSION coc= new TB_PSG_CHANGE_OF_COMISSION();
				   
				 
				   coc.setNew_personal_no(BAN.getPersonnel_no());
				   coc.setType_of_commission_granted(BAN.getType_of_comm_granted());
				   coc.setDate_of_seniority(BAN.getDate_of_seniority());
				   coc.setDate_of_permanent_commission(BAN.getDate_of_commission());
				   coc.setComm_id(i);
				   coc.setStatus(1);
				   coc.setAuthority(BAN.getAuthority());
				   coc.setDate_of_authority(BAN.getDate_of_authority());
				   coc.setCreated_by(BAN.getCreated_by());
				   coc.setCreated_date(BAN.getCreated_date());
				   coc.setModified_by(username);
				   coc.setModified_date(new Date());
				   
				   TB_INTER_ARM_TRANSFER arm = new TB_INTER_ARM_TRANSFER();
				   arm.setParent_arm_service(BAN.getParent_arm());
				   arm.setRegt(BAN.getRegiment());
				   arm.setWith_effect_from(BAN.getDate_of_commission());
				   arm.setComm_id(BAN.getId());
				   arm.setAuthority(BAN.getAuthority());
				   arm.setDate_of_authority(BAN.getDate_of_authority());
				   arm.setStatus(1);
				   arm.setCreated_by(BAN.getCreated_by());
				   arm.setCreated_date(BAN.getCreated_date());
				   arm.setModified_by(username);
				   arm.setModified_date(new Date());
				  
				   TB_PSG_CHANGE_OF_SENIORITY sen = new TB_PSG_CHANGE_OF_SENIORITY();
				   sen.setDate_of_seniority(BAN.getDate_of_seniority());
				   sen.setStatus(1);
				   sen.setAuthority(BAN.getAuthority());
				   sen.setDate_of_authority(BAN.getDate_of_authority());
				   sen.setCreated_by(BAN.getCreated_by());
				   sen.setCreated_date(BAN.getCreated_date());
				   sen.setModified_by(username);
				   sen.setModified_date(new Date());
				   sen.setComm_id(BAN.getId());
				   sen.setApplicablity_date(BAN.getDate_of_seniority());
				
				//sen.setComm_id(comm_id);		   
				   
				 TB_TRANS_PROPOSED_COMM_LETTER_HISTORY his= new TB_TRANS_PROPOSED_COMM_LETTER_HISTORY(); 
			       his.setAuthority(BAN.getAuthority());
				   his.setDate_of_authority(BAN.getDate_of_authority());
				   his.setName(BAN.getName());
				   his.setCadet_no(BAN.getCadet_no());
				   his.setBatch_no(BAN.getBatch_no());
				   his.setCourse(BAN.getCourse());
				   his.setGender(BAN.getGender());
				   his.setType_of_comm_granted(BAN.getType_of_comm_granted());
				   his.setUnit_sus_no(BAN.getUnit_sus_no());
				   his.setParent_arm(BAN.getParent_arm());
				   his.setRank(BAN.getRank());
				   his.setRegiment(BAN.getRegiment());
				   his.setDate_of_commission(BAN.getDate_of_commission());
				   his.setDate_of_seniority(BAN.getDate_of_seniority());
				   his.setDate_of_rank(BAN.getDate_of_rank());
				   his.setDate_of_birth(BAN.getDate_of_birth());
				   his.setPersonnel_no(BAN.getPersonnel_no());
				   his.setComm_his_id(BAN.getId());
				   his.setDate_of_tos(BAN.getDate_of_tos());
				   his.setStatus(1);
				   his.setCreated_by(BAN.getCreated_by());
				   his.setCreated_date(BAN.getCreated_date());
				   his.setModified_by(username);
				   his.setModified_date(new Date());
				   his.setTnai_no(BAN.getTnai_no());
				   
//	pranay 18-04			 
 TB_PSG_UPDATE_COMM_BIRTH dob  = new TB_PSG_UPDATE_COMM_BIRTH();
				   
				   dob.setDate_of_birth(BAN.getDate_of_birth());
				   dob.setAuthority(BAN.getAuthority());
				   dob.setDate_of_authority(BAN.getDate_of_authority());
				   dob.setComm_id(i);			       
			       dob.setStatus(1);
			       dob.setCreated_by(BAN.getCreated_by());
			       dob.setCreated_date(BAN.getCreated_date());
			       dob.setModified_by(username); 
			       dob.setModified_date(new Date());
			       
 TB_PSG_UPDATE_COMM_COMMISSION comm  = new TB_PSG_UPDATE_COMM_COMMISSION();
				   
			       comm.setType_of_comm_granted(BAN.getType_of_comm_granted());
			       comm.setDate_of_commission(BAN.getDate_of_commission());
				   comm.setAuthority(BAN.getAuthority());
				   comm.setDate_of_authority(BAN.getDate_of_authority());
				   comm.setComm_id(i);			       
				   comm.setStatus(1);
			       comm.setCreated_by(BAN.getCreated_by());
			       comm.setCreated_date(BAN.getCreated_date());
			       comm.setModified_by(username); 
			       comm.setModified_date(new Date());
			       
//			   	pranay 29-05	
TB_PSG_UPDATE_COMM_CADET cadet_no=new TB_PSG_UPDATE_COMM_CADET();			    
			       cadet_no.setCadet_no(BAN.getCadet_no());
			       cadet_no.setAuthority(BAN.getAuthority());
			       cadet_no.setDate_of_authority(BAN.getDate_of_authority());
			       cadet_no.setComm_id(i);			       
				   cadet_no.setStatus(1);
				   cadet_no.setCreated_by(BAN.getCreated_by());
				   cadet_no.setCreated_date(BAN.getCreated_date());
				   cadet_no.setModified_by(username); 
				   cadet_no.setModified_date(new Date());
				   
TB_PSG_UPDATE_COMM_GENDER gender=new TB_PSG_UPDATE_COMM_GENDER();			    
				   gender.setGender(BAN.getGender());
				   gender.setAuthority(BAN.getAuthority());
				   gender.setDate_of_authority(BAN.getDate_of_authority());
				   gender.setComm_id(i);			       
				   gender.setStatus(1);
				   gender.setCreated_by(BAN.getCreated_by());
				   gender.setCreated_date(BAN.getCreated_date());
				   gender.setModified_by(username); 
				   gender.setModified_date(new Date());
				   
				   
TB_PSG_UPDATE_COMM_COURSE course=new TB_PSG_UPDATE_COMM_COURSE();
				   course.setCourse(BAN.getCourse());
				   course.setBatch_no(BAN.getBatch_no());
				   course.setAuthority(BAN.getAuthority());
				   course.setDate_of_authority(BAN.getDate_of_authority());
				   course.setComm_id(i);			       
				   course.setStatus(1);
				   course.setCreated_by(BAN.getCreated_by());
				   course.setCreated_date(BAN.getCreated_date());
				   course.setModified_by(username); 
				   course.setModified_date(new Date());
				   
				   
				   Session ses1 = HibernateUtil.getSessionFactory().openSession();
					Transaction t2 = ses1.beginTransaction();		
					Query q = ses1.createQuery(
							"select distinct unit_name from Miso_Orbat_Unt_Dtl where sus_no=:sus_no  and status_sus_no='Active'");
					q.setParameter("sus_no", BAN.getUnit_sus_no());
					String Unit_name = (String) q.uniqueResult();
				   
				   TB_PSG_UPDATE_COMM_UNIT unit_upd = new TB_PSG_UPDATE_COMM_UNIT();
				   unit_upd.setDate_of_tos(BAN.getDate_of_tos());
				   unit_upd.setUnit_sus_no(BAN.getUnit_sus_no());
				   unit_upd.setUnit_post_to(Unit_name);				   
				   unit_upd.setAuthority(BAN.getAuthority());
				   unit_upd.setDate_of_authority(BAN.getDate_of_authority());
				   unit_upd.setComm_id(i);			       
				   unit_upd.setStatus(1);
				   unit_upd.setCreated_by(BAN.getCreated_by());
				   unit_upd.setCreated_date(BAN.getCreated_date());
				   unit_upd.setModified_by(username); 
				   unit_upd.setModified_date(new Date());
				  
					 
				   String prefix= BAN.getPersonnel_no();
				    String pnoprefix = prefix.substring(0, 2);
				    TB_CHANGE_TNAI_NO med1 = new TB_CHANGE_TNAI_NO();
				    
				    if(pnoprefix.equals("NR") || pnoprefix.equals("NS")) {				    	 
						   med1.setTnai_no(BAN.getTnai_no());
						   med1.setComm_id(BAN.getId());
						  
						   med1.setAuthority(BAN.getAuthority());
						   med1.setDate_of_authority(BAN.getDate_of_authority());
						   med1.setStatus(1);
						   med1.setCreated_by(BAN.getCreated_by());
						   med1.setCreated_date(BAN.getCreated_date());
			               med1.setModified_by(username);
			               med1.setModified_date(new Date());
				    }
				   
					 
				   
//SAVE FOR POSTING IN OUT CHANDANI
					TB_POSTING_IN_OUT po = new TB_POSTING_IN_OUT();
				
					ArrayList<ArrayList<String>> orbatlist = report_3008DAO.getcommand(BAN.getUnit_sus_no());
				    ArrayList<ArrayList<String>> Location_Trnlist = pod.getLocation_Trn(BAN.getUnit_sus_no());
				    
				   
				    
				    po.setTo_sus_no(BAN.getUnit_sus_no());
					po.setDt_of_sos(BAN.getDate_of_tos());
					po.setDt_of_tos(BAN.getDate_of_tos());
					po.setCreated_by(BAN.getCreated_by());
					
					po.setCreated_date(BAN.getCreated_date());
					po.setModified_by(username);
                    po.setModified_date(new Date());
					po.setComm_id(BAN.getId());
					po.setStatus(1);
					
					if(orbatlist.size() > 0 && Location_Trnlist.size() > 0 )
					{
						
						
						po.setCmd_sus(orbatlist.get(0).get(1)); 
						po.setCorps_sus(orbatlist.get(0).get(2));
						po.setDiv_sus(orbatlist.get(0).get(3));
						po.setBde_sus(orbatlist.get(0).get(4));
						po.setLocation(Location_Trnlist.get(0).get(0));
						po.setTrn_type(Location_Trnlist.get(0).get(1));
						po.setRank(BAN.getRank());
						
						
					String Count_Name = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_CHANGE_NAME") ;
					 if (Integer.parseInt(Count_Name) == 0) {
							sessionHQL.save(med);
					    
				     }	
					 String Count_Rank = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_CHANGE_OF_RANK") ;
					 if (Integer.parseInt(Count_Rank) == 0) {
						sessionHQL.save(rnk);
						
				     }	
					 String Count_comm = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_CHANGE_OF_COMISSION") ;
					 if (Integer.parseInt(Count_comm) == 0) {
						 sessionHQL.save(coc);
						   
				     }	
					 String Count_Arm= CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_INTER_ARM_TRANSFER") ;
					 if (Integer.parseInt(Count_Arm) == 0) {
						 sessionHQL.save(arm);
						 
				     }
					 String Count_sen= CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_CHANGE_OF_SENIORITY") ;
					 if (Integer.parseInt(Count_sen) == 0) {
					sessionHQL.save(sen);
						 
				     }
					 String Count_Post = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_POSTING_IN_OUT") ;
					
					 if(Integer.parseInt(Count_Post) == 0) {
								sessionHQL.save(po);
							
					 }
					 
					 String date_of_birth = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_BIRTH") ;
					 if (Integer.parseInt(date_of_birth) == 0) {
						sessionHQL.save(dob);
						
				     }
					 String commision = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_COMMISSION") ;
					 if (Integer.parseInt(commision) == 0) {
						sessionHQL.save(comm);
						
				     }
					 String cadet = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_CADET") ;
					 if (Integer.parseInt(cadet) == 0) {
						sessionHQL.save(cadet_no);
						
				     }	
					 String gen = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_GENDER") ;
					 if (Integer.parseInt(gen) == 0) {
						sessionHQL.save(gender);
						
				     }	
					 
					 String course_no = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_COURSE") ;
					 if (Integer.parseInt(course_no) == 0) {
						sessionHQL.save(course);
						
				     }
					 String update_unit = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_PSG_UPDATE_COMM_UNIT") ;
					 if (Integer.parseInt(update_unit) == 0) {
						sessionHQL.save(unit_upd);
						
				     }
					 
					 if(pnoprefix.equals("NR") || pnoprefix.equals("NS")) {	
						 String Count_tnaino = CountOfComm_idApprove(String.valueOf(BAN.getId()) , "TB_CHANGE_TNAI_NO") ;
						 if (Integer.parseInt(Count_tnaino) == 0) {
								sessionHQL.save(med1);
						    
					     }
						   }
					
				
					    sessionHQL.save(his);
					    sessionHQL.flush();
					    sessionHQL.clear(); 
					    
					}
					    
					    
					else
					{
						model.put("msg","Orbat Details are not Exist.");
						return new ModelAndView(redirect);
					}
						
					
					
					    
					if (id1.intValue()!=0) {
						
						String hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set status=:status,update_comm_status=:update_comm_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
						app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("update_comm_status", 1).setString("modified_by", username)
								.setDate("modified_date", new Date()).setBigInteger("id", id1).executeUpdate();
					} else {
						
						String hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set status=:status,update_comm_status=:update_comm_status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
						app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setInteger("update_comm_status", 1).setString("modified_by", username)
								.setDate("modified_date", new Date()).setBigInteger("id", id).executeUpdate();
					}
					
				
				 }
				
				tx.commit();
				sessionHQL.close();
				

				if (app > 0) {
					liststr.add("Approved Successfully.");
				} else {
					liststr.add("Approved Not Successfully.");
				}
				model.put("msg",liststr.get(0));
				String roleType = sessionA.getAttribute("roleType").toString();
				 
				//model.put("list",  SLDAO.Search_comm_letter("","","","","0","","","","",roleType));
				model.put("status1", status);	
				 Date date = new Date();
					String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
					String yyyy = new SimpleDateFormat("yyyy").format(date);
					String to_date = "01/01/"+yyyy;
					model.put("date", date1);
					model.put("to_date",to_date );
				model.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());
		
				
			return new ModelAndView(redirect);
		}
		
		
		
		public String CountOfComm_idApprove(String comm_id ,String TB_MODEL) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String count = null;
			try {
				Query q1 = sessionHQL.createQuery("select count(comm_id) from "+ TB_MODEL +" where comm_id=:comm_id");		
				
			
				q1.setParameter("comm_id", new BigInteger(comm_id));
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();			
				if(list.size() > 0) {
					count = String.valueOf(list.get(0));
				}
				tx.commit();
			} catch (RuntimeException e) {			
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return count;
		}
		
	/*	@RequestMapping(value = "/Reject_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Reject_Commissioning_Letter(@ModelAttribute("id4") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "rej_remarks4", required = false) String reject_remarks,Authentication authentication) {
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<String> liststr = new ArrayList<String>();
			//try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				
				 String username =  sessionA.getAttribute("username").toString();
				 
				 
				String hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setInteger("id", id).executeUpdate();
				
				String hql1 = "update TB_TRANS_PROPOSED_COMM_LETTER_HISTORY set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where comm_his_id=:comm_his_id";
				int app1 = sessionHQL.createQuery(hql1).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setInteger("comm_his_id", id).executeUpdate();
				
				if (app > 0) {
					liststr.add("Data Rejected Successfully.");
				} else {
					liststr.add("Data not Rejected");
				}
				
				String roleType = sessionA.getAttribute("roleType").toString();
				 
				
				model.put("msg",liststr.get(0));

			} catch (Exception e) {
				
				model.put("msg",liststr.get(0));
			}
				//model.put("list",  SLDAO.Search_comm_letter("","","","","0","","","","",roleType));
				model.put("status",0);
				
				tx.commit();
				sessionHQL.close();
				return new ModelAndView("Search_Commissioning_LetterTiles");
		}*/
		
		
		
		
		@RequestMapping(value = "/Reject_Commissioning_Letter" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Reject_Commissioning_Letter(@ModelAttribute("id4") BigInteger id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "rej_remarks4", required = false) String reject_remarks,
				@RequestParam(value = "isMns", required = false) String isMns,Authentication authentication) {
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_LetterUrl", sessionA.getAttribute("roleid").toString());
			 String redirect = "Search_Commissioning_LetterTiles";
			 if(isMns.equals("True")) {
					val= roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", sessionA.getAttribute("roleid").toString());
					redirect = "Search_Commissioning_MnsLetterTiles";
				}
			 
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<String> liststr = new ArrayList<String>();
			//try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				
				 String username =  sessionA.getAttribute("username").toString();
				 
				 
				String hqlUpdate = "update TB_TRANS_PROPOSED_COMM_LETTER set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
				int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setBigInteger("id", id).executeUpdate();
				
				/*String hql1 = "update TB_TRANS_PROPOSED_COMM_LETTER_HISTORY set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where comm_his_id=:comm_his_id";
				int app1 = sessionHQL.createQuery(hql1).setInteger("status", 3).setString("reject_remarks", reject_remarks).setString("modified_by", username)
						.setDate("modified_date", new Date())
						.setInteger("comm_his_id", id).executeUpdate();*/
				
				if (app > 0) {
					liststr.add("Data Rejected Successfully.");
				} else {
					liststr.add("Data not Rejected");
				}
				
				String roleType = sessionA.getAttribute("roleType").toString();
				 
				
				model.put("msg",liststr.get(0));

			/*} catch (Exception e) {
				
				model.put("msg",liststr.get(0));
			}*/
				//model.put("list",  SLDAO.Search_comm_letter("","","","","0","","","","",roleType));
				model.put("status",0);
				
				tx.commit();
				sessionHQL.close();
				return new ModelAndView(redirect);
		}
		
		@RequestMapping(value = "/GetSearch_Com_letterCount", method = RequestMethod.POST)
		public @ResponseBody long GetSearch_Com_letterCount(String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String msg,String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,
				String name,String status, String type_of_comm_granted, String date_of_commission, String frm_dt1,String to_dt1,String cr_by,String cr_date,String IsMns) throws SQLException {
			 String roleType = sessionUserId.getAttribute("roleType").toString();
			
			return SLDAO.GetSearch_Com_letterCount(Search, orderColunm, orderType, sessionUserId, unit_sus_no, unit_posted_to, parent_arm, personnel_no,
					 name, status,  type_of_comm_granted,  date_of_commission,  frm_dt1, to_dt1,cr_by,cr_date,roleType,Boolean.parseBoolean(IsMns));
		}
		

		
		@RequestMapping(value = "/GetSearch_Com_letterdata", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> GetSearch_Com_letterdata(int startPage,int pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId
				,String unit_sus_no,String unit_posted_to,String parent_arm,String personnel_no,
				String name,String status, String type_of_comm_granted, String date_of_commission, String frm_dt1,String to_dt1,String cr_by,String cr_date,String IsMns) throws SQLException {
			 String roleType = sessionUserId.getAttribute("roleType").toString();
			
			return SLDAO.GetSearch_Com_letterdata(startPage, pageLength, Search, orderColunm, orderType,sessionUserId,unit_sus_no, unit_posted_to, parent_arm, personnel_no,
					 name, status,  type_of_comm_granted,  date_of_commission,  frm_dt1, to_dt1, cr_by, cr_date,roleType,Boolean.parseBoolean(IsMns));
		}



}
