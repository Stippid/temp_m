package com.controller.psg.Master;

import java.text.ParseException;
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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Examination_PassedDAO;
import com.models.psg.Master.TB_EXAMINATION_PASSED;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Examination_Passed_masterController {

	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private Examination_PassedDAO examDao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Examination_PassedUrl", method = RequestMethod.GET)
	 public ModelAndView Examination_PassedUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Examination_PassedUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		List<ArrayList<String>> list = examDao.search_ExaminationPassed("","","active");
		Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("Examination_PassedTiles");
	 }

	
	 @RequestMapping(value = "/Examination_passedAction",method=RequestMethod.POST)
		public ModelAndView Examination_passedAction(@ModelAttribute("Examination_passedCmd") TB_EXAMINATION_PASSED ta, @RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Examination_PassedUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				int id = ta.getId() > 0 ? ta.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				String examination_passed = request.getParameter("examination_passed").trim();
				String qualification_type = request.getParameter("qualification_type");
				
				 if (examination_passed.equals("") || examination_passed.equals("null") || examination_passed.equals(null)) {
						model.put("msg", "Please Enter Examination_passed");
						return new ModelAndView("redirect:Examination_PassedUrl");
					}
				
				 if (qualification_type.equals("0")) {
						model.put("msg", "Please Select Type Of Qualification Type");
						return new ModelAndView("redirect:Examination_PassedUrl");
					}
			
				if (ta.getStatus() == "inactive" || ta.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Examination_PassedUrl");
					}
				
				
				try{
					Query q0 = sessionHQL.createQuery("select count(id) from TB_EXAMINATION_PASSED where examination_passed=:examination_passed and status=:status and qualification_type=:qualification_type and id!=:id");
					q0.setParameter("examination_passed", ta.getExamination_passed()); 
					q0.setParameter("status", ta.getStatus());
					q0.setParameter("qualification_type",ta.getQualification_type());  
					q0.setParameter("id", id);  				
					
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						ta.setCreated_by(username);
						ta.setCreated_date(date);
						ta.setExamination_passed(examination_passed);
						ta.setQualification_type(Integer.parseInt(qualification_type));
						if (c == 0) {
							sessionHQL.save(ta);
							sessionHQL.flush();
							sessionHQL.clear();
							model.put("msg", "Data Saved Successfully.");

					}else {
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
				return new ModelAndView("redirect:Examination_PassedUrl");

			
	 }
	 
	 
	 @RequestMapping(value = "/admin/getSearch_Examination_passed", method = RequestMethod.POST)
		public ModelAndView getSearch_Examination_passed(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "examination_passed1", required = false) String examination_passed,
				@RequestParam(value = "qualification_type1", required = false) String qualification_type,
				@RequestParam(value = "status1", required = false) String status ){
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Examination_PassedUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
	    	List<ArrayList<String>> list = examDao.search_ExaminationPassed(qualification_type,examination_passed,status);
	    		Mmap.put("examination_passed1", examination_passed);
		        Mmap.put("qualification_type1", qualification_type);
				Mmap.put("status1", status);
				Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("list", list);
			return new ModelAndView("Examination_PassedTiles","Examination_passedCmd",new TB_EXAMINATION_PASSED());
		}
	 
	 
	
	 @RequestMapping(value = "/delete_Examination_passed", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Examination_passed(@ModelAttribute("id1") int id, BindingResult result,
				HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Examination_PassedUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 List<String> liststr = new ArrayList<String>();

			String username = session.getAttribute("username").toString();

			try {
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionHQL.beginTransaction();

				String hqlUpdate = "update TB_EXAMINATION_PASSED set modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where id=:id";

				int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
						.setString("modified_by", username).setDate("modified_date", new Date()).setInteger("id", id)
						.executeUpdate();
				tx.commit();
				sessionHQL.close();

				if (app > 0) {
					liststr.add("Delete Successfully.");
				} else {
					liststr.add("Delete UNSuccessfully.");
				}
				model.put("msg", liststr.get(0));

			} catch (Exception e) {
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				model.put("msg", liststr.get(0));
			}
			return new ModelAndView("redirect:Examination_PassedUrl");
		}
	 
	  @RequestMapping(value = "/Edit_Examination_passed",method=RequestMethod.POST)
			public ModelAndView Edit_Examination_passed(@ModelAttribute("id2") String updateid,ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
					HttpSession sessionEdit) {
				
		  			TB_EXAMINATION_PASSED authDetails = examDao.getEXAMINATIONPASSEDByid(Integer.parseInt(updateid));
					Mmap.put("Edit_Examination_PassedCMD", authDetails);
					Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					Mmap.put("msg", msg);
				
				return new ModelAndView("Edit_Examination_Passed_Tiles");
			}
	  
	  @RequestMapping(value = "/Edit_Examination_passedAction", method = RequestMethod.POST)
		public ModelAndView Edit_Examination_passedAction(@ModelAttribute("Edit_Examination_PassedCMD") TB_EXAMINATION_PASSED ex,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			
			String examination_passed = request.getParameter("examination_passed").trim();
			String status = request.getParameter("status");
			String qualification_type = request.getParameter("qualification_type");
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        

	        if (examination_passed.equals("") || examination_passed.equals("null") || examination_passed.equals(null)) {
	        	TB_EXAMINATION_PASSED authDetails = examDao.getEXAMINATIONPASSEDByid((id));
	        	model.put("Edit_Examination_PassedCMD", authDetails);
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Please Enter Examination Passed");
				return new ModelAndView("Edit_Examination_Passed_Tiles");
			}
	        if (qualification_type.equals("0")) {
	        	TB_EXAMINATION_PASSED authDetails = examDao.getEXAMINATIONPASSEDByid((id));
	        	model.put("Edit_Examination_PassedCMD", authDetails);
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Please Select Qualification");
				return new ModelAndView("Edit_Examination_Passed_Tiles");
			}
	
		/* if (ex.getStatus() == "inactive" || ex.getStatus().equals("inactive")) {
			 TB_EXAMINATION_PASSED authDetails = examDao.getEXAMINATIONPASSEDByid((id));
	        	model.put("Edit_Examination_PassedCMD", authDetails);
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Examination_Passed_Tiles");
	
			}*/
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_EXAMINATION_PASSED where examination_passed=:examination_passed and qualification_type=:qualification_type and status=:status and id !=:id");
						q0.setParameter("examination_passed", ex.getExamination_passed()); 
						q0.setParameter("qualification_type", ex.getQualification_type()); 
						q0.setParameter("status", status); 
						q0.setParameter("id", ex.getId()); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_EXAMINATION_PASSED set examination_passed=:examination_passed,qualification_type=:qualification_type,modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
					    	  Query query = session1.createQuery(hql)
					    				.setInteger("qualification_type", ex.getQualification_type())
					    			  	.setString("examination_passed", ex.getExamination_passed())
					    			  	.setString("status",status)
										.setString("modified_by", username)
										.setDate("modified_date", new Date())
										.setInteger("id", ex.getId());
					                    msg = query.executeUpdate() > 0 ? "1" :"0";
					                    tx.commit(); 
					                    
					                    if(msg == "1") {
					                    	 model.put("msg", "Data Updated Successfully.");
					                    }
					                    else {
					                    	model.put("msg", "Data Not Updated.");
					                    }
						}
						else {
							model.put("msg", "Data already Exist.");
						}
					
				  }catch(RuntimeException e){
		              try{
		                      tx.rollback();
		                      model.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		                      model.put("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Examination_PassedUrl");
		}
}
