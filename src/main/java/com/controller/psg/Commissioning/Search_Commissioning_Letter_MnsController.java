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
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_COMISSION;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Search_Commissioning_Letter_MnsController {

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
	

	@RequestMapping(value = "/admin/Search_Commissioning_MnsLetterUrl",method = RequestMethod.GET)
	 public ModelAndView Search_Commissioning_MnsLetterUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 Date date = new Date();
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			String yyyy = new SimpleDateFormat("yyyy").format(date);
			String to_date = "01/01/"+yyyy;
			
		Boolean val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		 String roleType = session.getAttribute("roleType").toString();

		 Mmap.put("date", date1);
		 Mmap.put("to_date",to_date );
		 Mmap.put("msg", msg);	
		 Mmap.put("getParentArmList", p_comm.getParentArmList());
		 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());

		 return new ModelAndView("Search_Commissioning_MnsLetterTiles");
	 }
	
	// pranay 29/10/24
	
	@RequestMapping(value = "/admin/GetSearch_Com_letter_mns", method = RequestMethod.POST)
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
			@RequestParam(value = "to_dt1", required = false) String to_dt1) throws ParseException{
	 
	 Boolean val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", session.getAttribute("roleid").toString());
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
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
    	  
    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
      }
	 
	 if(unit_posted_to!="") {
		  if (!valid.isUnit(unit_posted_to)) {
			  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
    	  
    	  if (!valid.isvalidLength(unit_posted_to, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
      }
	 
	   if(personnel_no!="") {
		   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
		  
		      if (personnel_no.length() < 7 || personnel_no.length() > 9) {
					Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
					return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
				}
	     } 
	   
	   if(name!="") {
	    	  if (!valid.isOnlyAlphabet(name)) {
					Mmap.put("msg", "Name " + valid.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
				}

				if (!valid.isvalidLength(name, valid.nameMax, valid.nameMin)) {
					Mmap.put("msg", "Name " + valid.isValidLengthMSG);
					return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
				}
	      }
	 //26-01-1994
		if (!date_of_commission.equals("") && date_of_commission !="" && !date_of_commission.equals("DD/MM/YYYY")) {
			
			if (!valid.isValidDate(date_of_commission)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Commission");
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
			}
		}
		
		if (!frm_dt1.equals("") && frm_dt1 !="" && !frm_dt1.equals("DD/MM/YYYY")) {
						
						if (!valid.isValidDate(frm_dt1)) {
							Mmap.put("msg", valid.isValidDateMSG + " From");
							return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
						}
					}
		
		if (!to_dt1.equals("") && to_dt1 !="" && !to_dt1.equals("DD/MM/YYYY")) {
			if (!valid.isValidDate(to_dt1)) {
				Mmap.put("msg", valid.isValidDateMSG + " To");
				return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
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
//		to_dt1 = d.toString();
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
		return new ModelAndView("Search_Commissioning_MnsLetterUrl","Search_Com_letterCMD",new TB_TRANS_PROPOSED_COMM_LETTER());
	}

	
		@RequestMapping(value = "/Delete_Commissioning_MnsLetter" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Commissioning_MnsLetter(@ModelAttribute("id1") BigInteger id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		 Boolean val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", sessionA.getAttribute("roleid").toString());
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
			 
//			String hql1 = "delete from TB_TRANS_PROPOSED_COMM_LETTER_HISTORY where comm_his_id=:comm_his_id";
//			int app1 = sessionHQL.createQuery(hql1).setInteger("comm_his_id", id).executeUpdate();
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
		return new ModelAndView("redirect:Search_Commissioning_MnsLetterUrl");
	}
		
		@RequestMapping(value = "/Edit_Commissioning_MnsLetterUrl")
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
				Boolean val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

			
					
			TB_TRANS_PROPOSED_COMM_LETTER authDetails = SLDAO.getSearch_com_letterByid(updateid);
			
			
			 
			 Mmap.put("Edit_Search_Com_letterCMD", authDetails);
			 Mmap.put("getCourseNameList", p_comm.getCourseNameListmns());          
			 Mmap.put("getGenderList", p_comm.getGenderList());
			 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionListmns());       			
			 /*Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());*/
			 Mmap.put("getTypeofRankList", p_comm.getrank_list("mns"));
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
			Mmap.put("IsMns", "True");
			return new ModelAndView("Edit_Commissioning_LetterTiles");
		}
		
		@RequestMapping(value = "/view_Commissioning_MnsLetterUrl",method = RequestMethod.POST)
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
			
			
			 Boolean val = roledao.ScreenRedirect("Search_Commissioning_MnsLetterUrl", sessionEdit.getAttribute("roleid").toString());
	         if(val == false) {
	                 return new ModelAndView("AccessTiles");
	         }

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			 Mmap.put("view_Search_Com_letterCMD", authDetails);
			 Mmap.put("getCourseNameList", p_comm.getCourseNameListmns());          
			 Mmap.put("getGenderList", p_comm.getGenderList());
			 Mmap.put("getTypeOfCommissionList", p_comm.getTypeOfCommissionListmns());       
			 Mmap.put("getRegtList", p_comm.getRegtList(""));
			 Mmap.put("getTypeofRankList", p_comm.getrank_list("mns"));
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

}
