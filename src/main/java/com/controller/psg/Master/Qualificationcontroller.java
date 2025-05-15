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


import com.dao.psg.Master.QualificationDao;
import com.models.psg.Master.TB_QUALIFICATION;

import com.persistance.util.HibernateUtil;

@Controller

@RequestMapping(value = {"admin","/" ,"user"}) 

public class Qualificationcontroller {

	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired

	private QualificationDao qc;

	@Autowired

	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/Qualification", method = RequestMethod.GET)

	 public ModelAndView Qualification(ModelMap Mmap, HttpSession session,

		@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		ArrayList<ArrayList<String>> list = qc.search_qualification("0","0","0","active");

		 Mmap.put("msg", msg);

		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());

		 Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());

		 Mmap.put("getDegreeList", pcommon.getDegreeList());

		 Mmap.put("list", list);

		 return new ModelAndView("qualificationTiles");

	 }

	 @RequestMapping(value = "/qualificationAction",method=RequestMethod.POST)

		public ModelAndView qualificationAction(@ModelAttribute("qualificationCMD") TB_QUALIFICATION qf,

				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		 
		 String  roleid = session.getAttribute("roleid").toString();

		 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

		 	if(val == false) {

				return new ModelAndView("AccessTiles");

			}

			if(request.getHeader("Referer") == null ) {

				msg = "";

			}

				int id = qf.getId() > 0 ? qf.getId() : 0;				

				Date date = new Date();

				String username = session.getAttribute("username").toString();

				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				 Transaction	tx = sessionHQL.beginTransaction();
				 
				 if (qf.getQualification_type().equals("0")  || qf.getQualification_type() == null || qf.getQualification_type().equals(null)) {
						model.put("msg", "Please Select Qualification Type ");
			
						return new ModelAndView("redirect:Qualification");
					}
				 if (qf.getStatus() == "inactive" || qf.getStatus().equals("inactive")) {
						
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Qualification");
			
					}
			
					if (qf.getExamination_passed() == 0 ) {
						model.put("msg", "Please Select Examination Passed");
						return new ModelAndView("redirect:Qualification");
					}
			
					if (qf.getDegree() == 0 ) {
						model.put("msg", "Please Select Degree");
						return new ModelAndView("redirect:Qualification");
					}
					

				try{					

					Query q0 = sessionHQL.createQuery("select count(id) from TB_QUALIFICATION where qualification_type=:qualification_type and examination_passed=:examination_passed and degree=:degree  and status=:status and id !=:id");

					q0.setString("qualification_type", qf.getQualification_type()); 

					q0.setString("status", qf.getStatus());

					q0.setInteger("examination_passed", qf.getExamination_passed());

					q0.setInteger("degree", qf.getDegree());

					q0.setInteger("id", id); 

					Long c = (Long) q0.uniqueResult();

					if (id == 0) {

						qf.setCreated_by(username);

						qf.setCreated_date(date);

						if (c == 0) {

							sessionHQL.save(qf);

							sessionHQL.flush();

							sessionHQL.clear();

							model.put("msg", "Data Saved Successfully.");

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

						model.put("msg", "Couldn?t roll back transaction " + rbe);

					}

					throw e;

				}finally{

					if(sessionHQL!=null){

					   sessionHQL.close();

					}

				}	

				return new ModelAndView("redirect:Qualification");

			}

	 /******************************Search For Qualification***********************************/

		@RequestMapping(value = "/admin/GetSearch_Qualification", method = RequestMethod.POST)

		public ModelAndView GetSearch_Qualification(ModelMap Mmap,HttpSession session,HttpServletRequest request,

				@RequestParam(value = "msg", required = false) String msg,

				@RequestParam(value = "hidqualification_type", required = false) String qualification_type,

				@RequestParam(value = "hidexamination_passed", required = false) String examination_passed,

				@RequestParam(value = "hiddegree", required = false) String degree,

				  @RequestParam(value = "status1", required = false) String status ){
			  String  roleid = session.getAttribute("roleid").toString();

			  Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

			 	if(val == false) {

					return new ModelAndView("AccessTiles");

				}

				if(request.getHeader("Referer") == null ) {

					msg = "";

				}

                   

				ArrayList<ArrayList<String>> list = qc.search_qualification(qualification_type,examination_passed,degree,status);

				Mmap.put("list", list);

				Mmap.put("status1", status);

				Mmap.put("qualification_type", qualification_type);

				Mmap.put("examination_passed", examination_passed);

				Mmap.put("degree", degree);

				

				 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());

				 Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());

				 Mmap.put("getDegreeList", pcommon.getDegreeList());

				 

			return new ModelAndView("qualificationTiles","qualificationCMD",new TB_QUALIFICATION());

		}

		 /******************************Delete For Qualification***********************************/

		/*@RequestMapping(value = "/Delete_Qualification" , method = RequestMethod.POST)

		public @ResponseBody ModelAndView Delete_Qualification(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,

				HttpSession sessionA, ModelMap model,

				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

			List<String> liststr = new ArrayList<String>();

			try {

				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				 Transaction tx = sessionHQL.beginTransaction();

				String hqlUpdate = "delete from TB_QUALIFICATION where id=:id";

				int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();

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

			return new ModelAndView("redirect:Qualification");

		}*/
		
		@RequestMapping(value = "/Delete_Qualification", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Qualification(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String  roleid = session.getAttribute("roleid").toString();

			 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

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
				 
				 String hqlUpdate = "update TB_QUALIFICATION set modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modified_by", username)
						.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Qualification");
		}

/******************************Update For Qualification***********************************/ 

		@RequestMapping(value = "/Edit_qualification",method=RequestMethod.POST)

		public ModelAndView Edit_qualification(@ModelAttribute("id2") String updateid, ModelMap Mmap,

				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,

				HttpSession sessionEdit) {	

			String  roleid = sessionEdit.getAttribute("roleid").toString();

			 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

			 	if(val == false) {

					return new ModelAndView("AccessTiles");

				}

				if(request.getHeader("Referer") == null ) {

					msg = "";

				}
					TB_QUALIFICATION authDetails = qc.getqualificationByid(Integer.parseInt(updateid));
					Mmap.put("editqualificationCMD", authDetails);
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					Mmap.put("getQualificationTypeList", pcommon.getQualificationTypeList());
					Mmap.put("getDegreeList", pcommon.getDegreeList());
					Mmap.put("msg", msg);

			return new ModelAndView("Edit_QualificationTiles");

		}

		@RequestMapping(value = "/editqualificationAction", method = RequestMethod.POST)

		public ModelAndView editqualificationAction(@ModelAttribute("editqualificationCMD") TB_QUALIFICATION rs,

				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String  roleid = session.getAttribute("roleid").toString();

			 Boolean val = roledao.ScreenRedirect("Qualification", roleid);	

			 	if(val == false) {

					return new ModelAndView("AccessTiles");

				}

				if(request.getHeader("Referer") == null ) {

					msg = "";

				}
			
			
			
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));

			String qualification_type = request.getParameter("qualification_type");

			String examination_passed = request.getParameter("examination_passed");

			String degree = request.getParameter("degree");

			String status = request.getParameter("status");

			Session session1 = HibernateUtil.getSessionFactory().openSession();

	        Transaction tx = session1.beginTransaction();
	        
	        if (rs.getQualification_type().equals("0") || rs.getQualification_type() == null || rs.getQualification_type().equals(null)) {
	        	TB_QUALIFICATION authDetails = qc.getqualificationByid((id));
	        	model.put("editqualificationCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getDegreeList", pcommon.getDegreeList());
				model.put("msg", "Please Select Qualification Type");
				//model.put("id2", id);
				return new ModelAndView("Edit_QualificationTiles");
			}
	       /* if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	        	TB_QUALIFICATION authDetails = qc.getqualificationByid((id));
	        	model.put("editqualificationCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getDegreeList", pcommon.getDegreeList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_QualificationTiles");
	
			}*/
	
		  if (rs.getExamination_passed() == 0 ) {
			  TB_QUALIFICATION authDetails = qc.getqualificationByid((id));
	        	model.put("editqualificationCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getDegreeList", pcommon.getDegreeList());
				model.put("msg", "Please Select Examination Passed");
//				model.put("id2", id);
				return new ModelAndView("Edit_QualificationTiles");
			}
		  if (rs.getDegree() == 0 ) {
			  TB_QUALIFICATION authDetails = qc.getqualificationByid((id));
	        	model.put("editqualificationCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("getQualificationTypeList", pcommon.getQualificationTypeList());
				model.put("getDegreeList", pcommon.getDegreeList());
				model.put("msg", "Please Select Degree");
//				model.put("id2", id);
				return new ModelAndView("Edit_QualificationTiles");
			}
	
	
		 

			 	try {

			 		Query q0 = session1.createQuery("select count(id) from TB_QUALIFICATION where qualification_type=:qualification_type and examination_passed=:examination_passed and degree=:degree  and status=:status and id !=:id");

					q0.setParameter("qualification_type", rs.getQualification_type()); 

					q0.setParameter("status", rs.getStatus());

					q0.setParameter("examination_passed", rs.getExamination_passed());

					q0.setParameter("degree", rs.getDegree());

					q0.setParameter("id", id); 

					Long c = (Long) q0.uniqueResult();

						if(c==0) {

							 String hql = "update TB_QUALIFICATION set qualification_type=:qualification_type,examination_passed=:examination_passed , degree=:degree,status=:status"

							 		+ ",modified_by=:modified_by ,modified_date=:modified_date"

										+ " where id=:id";

					    	  Query query = session1.createQuery(hql)

					    			  	.setString("qualification_type",qualification_type)

					    			  	.setInteger("examination_passed", Integer.parseInt(examination_passed))

					    			  	.setInteger("degree", Integer.parseInt(degree))

					    			  	.setString("status",status)

					    			  	.setString("modified_by", username)

										.setDate("modified_date", new Date())

										.setInteger("id",id);

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

		                      model.put("msg", "Couldn?t roll back transaction " + rbe);

		              }

		              throw e;

				}finally{

					if(session1!=null){

						session1.close();

					}

				}

			return new ModelAndView("redirect:Qualification");

		}

}

