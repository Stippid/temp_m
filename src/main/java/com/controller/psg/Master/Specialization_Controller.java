package com.controller.psg.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.SpecializationDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_SPECIALIZATION;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Specialization_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	private SpecializationDAO SPE;	
	
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/SpecializationUrl", method = RequestMethod.GET)
	 public ModelAndView SpecializationUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 		
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

		 
			
			List<Map<String, Object>> list = SPE.search_Specialization("","active");
			Mmap.put("list", list);
		 
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		return new ModelAndView("SpecializationTiles");
	 }
	 

	 @RequestMapping(value = "/Specialization_Action",method=RequestMethod.POST)
		public ModelAndView Specialization_Action(@ModelAttribute("Specialization_CMD") TB_SPECIALIZATION sa,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request,ModelMap model,HttpSession session) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			int id = sa.getId() > 0 ? sa.getId() : 0;
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction	tx = sessionHQL.beginTransaction();
				Date date = new Date();
				String username = session.getAttribute("username").toString();
					
				String specialization = request.getParameter("specialization").trim();
					
					if (specialization.equals("") || specialization.equals("null")|| specialization.equals(null)) {
						 model.put("msg", "Please Enter Specialization Name");
						 return new ModelAndView("redirect:SpecializationUrl");
						}
					if (sa.getStatus() == "inactive" || sa.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:SpecializationUrl");
					}
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_SPECIALIZATION where specialization=:specialization and status=:status  and id !=:id");
					q0.setParameter("specialization", sa.getSpecialization());
					q0.setParameter("status", sa.getStatus());
					
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					
					if (id == 0) {
					sa.setCreated_by(username);
					sa.setCreated_date(date);
					sa.setSpecialization(specialization);
						if (c == 0) {
							sessionHQL.save(sa);
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
						model.put("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:SpecializationUrl");
			}
	 
	 @RequestMapping(value = "/search_Specialization" , method = RequestMethod.POST)
		public ModelAndView search_Specialization(ModelMap model, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, 
				@RequestParam(value = "specialization1", required = false) String specialization,
				@RequestParam(value = "status1", required = false) String status) {
		

			List<Map<String, Object>> list = SPE.search_Specialization(specialization,status);
			model.put("list", list);
			model.put("size", list.size());
			model.put("specialization1", specialization);
			model.put("status1", status);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			
			
			//return new ModelAndView("SpecializationTiles");
			return new ModelAndView("SpecializationTiles","specialization_CMD",new TB_SPECIALIZATION());
		}
	 
	 
		
	 @RequestMapping(value = "/Specialization_delete", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Specialization_delete(@ModelAttribute("id1") int id, BindingResult result,
				HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);
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

				String hqlUpdate = "update TB_SPECIALIZATION set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
			return new ModelAndView("redirect:SpecializationUrl");
		}
		
		@RequestMapping(value = "/edit_Specialization",method = RequestMethod.POST )
		public ModelAndView edit_Specialization(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
		    
			TB_SPECIALIZATION  SpeDetails = SPE.getSpecializationByid(Integer.parseInt(updateid));
			model.put("EditSpecializationCMD", SpeDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", msg);
	
			return new ModelAndView("EditSpecializationTiles");
		}
	 

		@RequestMapping(value = "/EditSpecializationAction", method = RequestMethod.POST)
		public ModelAndView EditSpecializationAction(@ModelAttribute("EditSpecializationCMD") TB_SPECIALIZATION SA,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SpecializationUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			String specialization = request.getParameter("specialization").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
			
	        if (specialization.equals("") || specialization.equals("null")|| specialization.equals(null)) {
	        	TB_SPECIALIZATION  SpeDetails = SPE.getSpecializationByid((id));
				model.put("EditSpecializationCMD", SpeDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Specialization Name");
				return new ModelAndView("EditSpecializationTiles");
			}	
	      /*  if (SA.getStatus() == "inactive" || SA.getStatus().equals("inactive")) {
	        	TB_SPECIALIZATION  SpeDetails = SPE.getSpecializationByid((id));
				model.put("EditSpecializationCMD", SpeDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("EditSpecializationTiles");
			}*/
			 	try {
					 Query q0 = session1.createQuery("select count(id) from TB_SPECIALIZATION where specialization=:specialization and status=:status and id !=:id");
						q0.setParameter("specialization", specialization); 
						q0.setParameter("status", status); 
						q0.setParameter("id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_SPECIALIZATION set specialization=:specialization,modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("specialization",specialization)
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
		                      model.put("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:SpecializationUrl");
		}

}
