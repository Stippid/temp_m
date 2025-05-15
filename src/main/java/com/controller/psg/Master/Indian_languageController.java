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
import com.dao.psg.Master.Indian_languageDAO;
import com.models.psg.Master.TB_INDIAN_LANGUAGE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Indian_languageController {

	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private Indian_languageDAO ind_landao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/Indian_languageUrl", method = RequestMethod.GET)
	 public ModelAndView Hair_Colour(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		 Mmap.put("msg", msg);
		 Mmap.put("list", ind_landao.search_indian_language("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("Indian_language_Tiles","Indian_languageCMD",new TB_INDIAN_LANGUAGE());
		
	 }
	 
 /******************************Save For Indian language***********************************/
	 
	 @RequestMapping(value = "/Indian_languageAction",method=RequestMethod.POST)
		public ModelAndView Hair_ColorAction(@ModelAttribute("Indian_languageCMD") TB_INDIAN_LANGUAGE IL,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
					
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
				int id = IL.getId() > 0 ? IL.getId() : 0;				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				
				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				Transaction	tx = sessionHQL.beginTransaction();
				 
				String indian_language =request.getParameter("ind_language").trim();
				
				 if (indian_language.equals("") || indian_language.equals("null")|| indian_language.equals(null)) {
					 model.put("msg", "Please Enter Language Name");
					 return new ModelAndView("redirect:Indian_languageUrl");
					}
				 if (IL.getStatus() == "inactive" || IL.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Indian_languageUrl");
					}
			
				try{					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_INDIAN_LANGUAGE where ind_language=:ind_language and status=:status and id !=:id");
					q0.setParameter("ind_language", IL.getInd_language());  					
					q0.setParameter("id", id); 
					q0.setParameter("status", IL.getStatus());
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						IL.setCreated_by(username);
						IL.setCreated_date(date);
						IL.setInd_language(indian_language);
						if (c == 0) {
							sessionHQL.save(IL);
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
				return new ModelAndView("redirect:Indian_languageUrl");
			}
	 
	 /******************************Search For Indian language***********************************/
		
		@RequestMapping(value = "/admin/GetSearch_Indian_lan", method = RequestMethod.POST)
		public ModelAndView GetSearch_Indian_lan(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "ind_language1", required = false) String ind_language,
				@RequestParam(value="status1",required = false ) String status){
			
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
				ArrayList<ArrayList<String>> list = ind_landao.search_indian_language(ind_language,status);
					Mmap.put("list", list);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					
			return new ModelAndView("Indian_language_Tiles","Indian_languageCMD",new TB_INDIAN_LANGUAGE());
		}

	 /******************************Delete For Indian language***********************************/
	    
		@RequestMapping(value = "/Delete_Indian_language", method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Indian_language(@ModelAttribute("id1") int id, BindingResult result,
				HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
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

				String hqlUpdate = "update TB_INDIAN_LANGUAGE set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
			return new ModelAndView("redirect:Indian_languageUrl");
		}
		
/******************************Update For Indian language***********************************/ 

		
		@RequestMapping(value = "/Edit_Indian_lanUrl",method=RequestMethod.POST)
		public ModelAndView Edit_Indian_lanUrl(@ModelAttribute("id2") int updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}	
				
			TB_INDIAN_LANGUAGE authDetails = ind_landao.getindianlanByid(updateid);
			Mmap.put("Edit_Indian_lanCMD", authDetails);
			Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			
			Mmap.put("msg", msg);
			return new ModelAndView("EditIndian_language_Tiles");
		}
		
		
		
		@RequestMapping(value = "/Edit_Indian_lanAction", method = RequestMethod.POST)
		public ModelAndView Edit_Indian_lanAction(@ModelAttribute("Edit_Indian_lanCMD") TB_INDIAN_LANGUAGE lan,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Indian_languageUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			String ind_language = request.getParameter("ind_language").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        if (ind_language.equals("") || ind_language.equals("null")|| ind_language.equals(null)) {
	        	TB_INDIAN_LANGUAGE authDetails = ind_landao.getindianlanByid(id);
	        	model.put("Edit_Indian_lanCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Language Name");
				return new ModelAndView("EditIndian_language_Tiles");
			}	
	    	/*if (lan.getStatus() == "inactive" || lan.getStatus().equals("inactive")) {
	    		TB_INDIAN_LANGUAGE authDetails = ind_landao.getindianlanByid(id);
	        	model.put("Edit_Indian_lanCMD", authDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("EditIndian_language_Tiles");
			}*/
	        		
			 	try {
			 		
					 Query q0 = session1.createQuery("select count(id) from TB_INDIAN_LANGUAGE where ind_language=:ind_language and status=:status  and id !=:id");
						q0.setParameter("ind_language", ind_language);  
						q0.setParameter("id", id);
						q0.setParameter("status", status); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_INDIAN_LANGUAGE set ind_language=:ind_language,status=:status,modified_by=:modified_by,modified_date=:modified_date"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("ind_language",ind_language)
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
			return new ModelAndView("redirect:Indian_languageUrl");
		}

	
}
