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
import com.dao.psg.Master.LanguageDao;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_LANGUAGE;
import com.persistance.util.HibernateUtil;

	@Controller
	@RequestMapping(value = {"admin","/","user"})
	public class LanguageController {
		
		Psg_CommonController pcommon = new Psg_CommonController();
		
		@Autowired
		private LanguageDao lan;
		@Autowired
		private RoleBaseMenuDAO roledao;

		
		// -------------------------------Language For page Open -------------------------------------
		@RequestMapping(value = "/admin/Language", method = RequestMethod.GET)
		 public ModelAndView Blood(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Language", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			
			 List<Map<String, Object>> list = lan.search_LanguageRepo("");
			 Mmap.put("list", list);
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			 
			 return new ModelAndView("LanguageTiles");
		 }
		
		// -------------------------------Language save -------------------------------------
		
		
		 @RequestMapping(value = "/LanguageAction",method=RequestMethod.POST)
			public ModelAndView LanguageAction(@ModelAttribute("LanguageCmd") TB_LANGUAGE com,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request,ModelMap model,HttpSession session) {
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Language", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				} 
					int id = com.getId() > 0 ? com.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					 if (com.getLanguage().equals("") || com.getLanguage().equals("null")
								|| com.getLanguage().equals(null)) {
							model.put("msg", "Please Enter Language");
							return new ModelAndView("redirect:Language");
						}
					try{
						
						Query q0 = sessionHQL.createQuery("select count(id) from TB_LANGUAGE where language=:language  and id !=:id");
						q0.setParameter("language", com.getLanguage());  
						
						q0.setParameter("id", id);  
						Long c = (Long) q0.uniqueResult();

						if (id == 0) {
							
							com.setCreated_by(username);
							com.setCreated_on(date);
							if (c == 0) {
								sessionHQL.save(com);
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
					return new ModelAndView("redirect:Language");
				}
		 
		// -------------------------SEARCH Language Report  -------------------------------------
			
			@RequestMapping(value = "/search_Language" , method = RequestMethod.POST)
			public ModelAndView search_Language(ModelMap model, HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("language1") String language1) {

				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Language", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
				List<Map<String, Object>> list = lan.search_LanguageRepo(language1);
				
				model.put("list", list);
				model.put("size", list.size());
				model.put("language1", language1);
			    model.put("list", list);
				return new ModelAndView("LanguageTiles");
			}

			// -------------------------Edit Language For page Open -------------------------------------
			@RequestMapping(value = "/edit_Language",method = RequestMethod.POST )
			public ModelAndView edit_Language(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
			    
				String roleid = sessionEdit.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Language", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				
				
				TB_LANGUAGE OpdDetails = lan.getLanguageByid(Integer.parseInt(updateid));
				model.put("EditLanguageCMD", OpdDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", msg);
		
				return new ModelAndView("EditLanguageTiles");
			}
	
			// -------------------------Edit Language Action -------------------------------------
			
			@SuppressWarnings("unused")
			@RequestMapping(value = "/EditLanguageAction", method = RequestMethod.POST)
			public ModelAndView EditLanguageAction(@ModelAttribute("EditLanguageCMD") TB_LANGUAGE b, HttpServletRequest request,
					BindingResult result,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

				 String  roleid = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Language", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				Mmap.put("updateid", b.getId());
				  
				 String username = session.getAttribute("username").toString();
				 String language1=request.getParameter("language_name");
				 
					 
					 if(language1.equals("") || language1.equals("null") || language1.equals(null)) {
					 Mmap.put("msg", "Please Enter Language");
						return new ModelAndView("redirect:edit_Language");
					}
			       		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			       		Transaction tx = sessionHQL.beginTransaction();
				try {
					Long op= lan.checkExitstingLanguage(language1,b.getId());
					
					b.setModify_by(username);
					b.setModify_on(new Date());
				    b.setLanguage(request.getParameter("language_name"));
						
						 if (op == 0) 
						   {
								
							 Mmap.put("msg", lan.Update_Language(b, username));
							} 
							if (op > 0) 
							{
								Mmap.put("msg", "Data already Exist.");
							}			
				
					 
				} catch (RuntimeException e) {
					try {
						
						Mmap.put("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				} finally {
					
				}
				return new ModelAndView("redirect:Language");
			}
			
			// -------------------------Delete Language  -------------------------------------
			@RequestMapping(value = "/language_delete" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView language_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				 String  roleid = sessionA.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Language", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
					}
				
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_LANGUAGE where id=:id";
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
				return new ModelAndView("redirect:Language");
			}
	
	
	
}
