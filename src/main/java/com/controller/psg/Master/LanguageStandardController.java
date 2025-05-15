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
import com.dao.psg.Master.LanguageStandardDAO;
import com.models.psg.Master.TB_LANG_STD;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class LanguageStandardController {
	
	Psg_CommonController pComm = new Psg_CommonController();
	
	@Autowired
	LanguageStandardDAO landStdDao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/LanguageStandard", method = RequestMethod.GET)
	 public ModelAndView LanguageStandard(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 ArrayList<ArrayList<String>> list = landStdDao.Search_LangSTD_dtl("", "active");
		 Mmap.put("list", list);
		 Mmap.put("getStatusMasterList", pComm.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("LanguageStandardTiles");
	 }
	
	
	 @RequestMapping(value = "/LanguageSTDAction",method=RequestMethod.POST)
		public ModelAndView LanguageSTDAction(@ModelAttribute("LanguageSTDCmd") TB_LANG_STD ba,HttpServletRequest request,
				ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
					 
				int id = ba.getId() > 0 ? ba.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String language = request.getParameter("language").trim();
				String status = request.getParameter("status");
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				
				
				 
				 if (language.equals("") || language.equals("null")|| language.equals(null)) {
					 model.put("msg", "Please Enter Language Standard");
					 return new ModelAndView("redirect:LanguageStandard");
					}
				 if (ba.getStatus() == "inactive" || ba.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:LanguageStandard");
					}
				 
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_LANG_STD where name=:name  and status=:status  and id !=:id");
					q0.setParameter("name", language);
					q0.setParameter("status", status);
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						ba.setName(language);
						ba.setStatus(status);
						ba.setCreated_by(username);
						ba.setCreated_dt(date);
						if (c == 0) {
							sessionHQL.save(ba);
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
						model.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:LanguageStandard");
			}
	 
	 @RequestMapping(value = "/admin/search_LanguageSTD", method = RequestMethod.POST)
		public ModelAndView search_LanguageSTD(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "language1", required = false) String language,
             @RequestParam(value = "status1", required = false) String status )

		{
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 ArrayList<ArrayList<String>> list = landStdDao.Search_LangSTD_dtl(language,status);
				Mmap.put("language1", language);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pComm.getStatusMasterList());
			
				Mmap.put("list", list);
			return new ModelAndView("LanguageStandardTiles","LanguageSTDCmd",new TB_LANG_STD());
		}
	 
	 @RequestMapping(value = "/edit_LanguageSTD",method=RequestMethod.POST)
		public ModelAndView edit_LanguageSTD(@ModelAttribute("updateid") String updateid,ModelMap Mmap,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			    TB_LANG_STD genDetails = landStdDao.getLangStdByid(Integer.parseInt(updateid));
			    Mmap.put("Edit_LanStdCMD", genDetails);
			    Mmap.put("getStatusMasterList", pComm.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_LangStdTiles");
		}
	 
	 @RequestMapping(value = "/Edit_LangStdAction", method = RequestMethod.POST)
		public ModelAndView Edit_LangStdAction(@ModelAttribute("Edit_LangStdCMD") TB_LANG_STD rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			 String language = request.getParameter("language").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
	        
		
	        if (language.equals("") || language.equals("null") || language.equals(null)) {
	        	TB_LANG_STD genDetails = landStdDao.getLangStdByid(id);
	        	model.put("Edit_LanStdCMD", genDetails);
	        	model.put("getStatusMasterList", pComm.getStatusMasterList());
	        	model.put("msg", "Please Enter Language Standard");
				return new ModelAndView("Edit_LangStdTiles");
			}	
			if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
				TB_LANG_STD genDetails = landStdDao.getLangStdByid(id);
				model.put("Edit_LanStdCMD", genDetails);
				model.put("getStatusMasterList", pComm.getStatusMasterList());
				model.put("msg", "Please Select Status");
				return new ModelAndView("Edit_LangStdTiles");
			}
			
			
				 try {
						
					 Query q0 = session1.createQuery("select count(id) from TB_LANG_STD where name=:name and  status=:status and id !=:id");
						q0.setParameter("name", language);  
						q0.setParameter("status", status);
						q0.setParameter("id", id);
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_LANG_STD set name=:name,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("name",language)
						    			  	.setString("status",status)
											.setString("modified_by", username).setDate("modified_date", new Date())
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
		                      model.put("msg", "Couldn't roll back transaction " + rbe);
		              }
		              throw e;
		             
		            }finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:LanguageStandard");
		}

	 
	 
	 @RequestMapping(value = "/languageSTD_delete", method = RequestMethod.POST)
		public @ResponseBody ModelAndView languageSTD_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("LanguageStandard", roleid);
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
				 
				 String hqlUpdate = "update TB_LANG_STD set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modified_by", username)
						.setDate("modified_dt", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:LanguageStandard");
		}
}
