	package com.controller.Jco_Master;

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

import com.controller.psg.Master.Psg_CommonController;
import com.dao.Jco_Master.Type_of_postDao;
import com.dao.login.RoleBaseMenuDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.model.Jco_Master.TB_Type_Of_Post;
import com.persistance.util.HibernateUtil;

	@Controller
	@RequestMapping(value = {"admin","/","user"})
	public class Type_of_postingController {
		
		Psg_CommonController pcommon = new Psg_CommonController();
		
		@Autowired
		private Type_of_postDao lan;
		@Autowired
		private RoleBaseMenuDAO roledao;

		
		// -------------------------------Language For page Open -------------------------------------
		@RequestMapping(value = "/admin/type_of_postURl", method = RequestMethod.GET)
		 public ModelAndView type_of_postURl(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Language", roleid);	
				if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			
			 List<Map<String, Object>> list = lan.search_type_of_postRepo("","active");
			 Mmap.put("list", list);
			 Mmap.put("msg", msg);
			 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			 
			 return new ModelAndView("Type_Of_PostingTiles");
		 }
		
		// -------------------------------Language save -------------------------------------
		
		
		 @RequestMapping(value = "/type_of_postAction",method=RequestMethod.POST)
			public ModelAndView type_of_postAction(@ModelAttribute("type_of_postCmd") TB_Type_Of_Post com,HttpServletRequest request,ModelMap model,HttpSession session) {
				
						 
					int id = com.getId() > 0 ? com.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					 String type_of_post1=request.getParameter("type_of_post").trim();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					 if (type_of_post1.equals("") || type_of_post1.equals("null") || type_of_post1.equals(null)) {
							model.put("msg", "Please Enter Type of Posting");
							return new ModelAndView("redirect:type_of_postURl");
							
						}
					 if (type_of_post1.length() >50 ) {
						 model.put("msg", "Type of Posting Length Should Be Less Than 50 Characters");
						 return new ModelAndView("redirect:Language");
					 }
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status");
							return new ModelAndView("redirect:type_of_postURl");
						}
					 
					try{
						
						Query q0 = sessionHQL.createQuery("select count(id) from TB_Type_Of_Post where type_of_post=:type_of_post  and id !=:id");
						q0.setParameter("type_of_post", com.getType_of_post());  
						
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
					return new ModelAndView("redirect:type_of_postURl");
				}
		 
		// -------------------------SEARCH Language Report  -------------------------------------
			
			@RequestMapping(value = "/search_type_of_post" , method = RequestMethod.POST)
			public ModelAndView search_type_of_post(ModelMap model, HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("type_of_post1") String type_of_post1,
					@RequestParam(value = "status1", required = false) String status1) {

				List<Map<String, Object>> list = lan.search_type_of_postRepo(type_of_post1,status1);
				
				model.put("list", list);
				model.put("size", list.size());
				model.put("ltype_of_post1", type_of_post1);
				model.put("status1", status1);
			    model.put("list", list);
			    model.put("getStatusMasterList", pcommon.getStatusMasterList());
				return new ModelAndView("Type_Of_PostingTiles");
			}

			// -------------------------Edit Language For page Open -------------------------------------
			@RequestMapping(value = "/edit_type_of_post",method = RequestMethod.POST )
			public ModelAndView edit_type_of_post(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
			    
				TB_Type_Of_Post OpdDetails = lan.gettype_of_postByid(Integer.parseInt(updateid));
				model.put("EditType_Of_PostingCMD", OpdDetails);
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", msg);
		
				return new ModelAndView("EditType_Of_PostingTiles");
			}
	
			// -------------------------Edit Language Action -------------------------------------
			
		@SuppressWarnings("unused")
			@RequestMapping(value = "/EditType_Of_PostingAction", method = RequestMethod.POST)
			public ModelAndView EditType_Of_PostingAction(@ModelAttribute("EditType_Of_PostingCMD") TB_Type_Of_Post b, HttpServletRequest request,
					BindingResult result,ModelMap Mmap, HttpSession session) throws ParseException {

				Mmap.put("updateid", b.getId());
				  
				 String username = session.getAttribute("username").toString();
				 int id = Integer.parseInt(request.getParameter("id"));
				 String type_of_post1=request.getParameter("type_of_post").trim();
				 if (type_of_post1.equals("") || type_of_post1.equals("null") || type_of_post1.equals(null)) {
					 TB_Type_Of_Post OpdDetails = lan.gettype_of_postByid(id);
					 Mmap.put("EditType_Of_PostingCMD", OpdDetails);
					 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					 Mmap.put("msg", "Please Enter Type of Posting");
					 return new ModelAndView("EditType_Of_PostingTiles");
					// return new ModelAndView("redirect:edit_Language");
					}
				 if (type_of_post1.length() >50 ) {
					 TB_Type_Of_Post OpdDetails = lan.gettype_of_postByid(id);
					 Mmap.put("EditType_Of_PostingCMD", OpdDetails);
					 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					 Mmap.put("msg", "Type of Posting Length Should Be Less Than 50 Characters");
					 return new ModelAndView("EditType_Of_PostingTiles");
				 }
				/* if (b.getStatus() == "inactive" || b.getStatus().equals("inactive")) {
					 TB_Type_Of_Post OpdDetails = lan.gettype_of_postByid(id);
					 Mmap.put("EditLanguageCMD", OpdDetails);
					 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
					 Mmap.put("msg", "Only Select Active Status");
					 return new ModelAndView("EditType_Of_PostingTiles");
				 }*/
				 
			       		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			       		Transaction tx = sessionHQL.beginTransaction();
				//try {
					Long op= lan.checkExitstingtype_of_post(type_of_post1,b.getId());
					
					b.setModify_by(username);
					b.setModify_on(new Date());
				    b.setType_of_post(type_of_post1);
						
						 if (op == 0) 
						   {
								
							 Mmap.put("msg", lan.Update_type_of_post(b, username));
							} 
							if (op > 0) 
							{
								Mmap.put("msg", "Data already Exist.");
							}			
				
					 
				/*} catch (RuntimeException e) {
					try {
						
						Mmap.put("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				} finally {
					
				}*/
				return new ModelAndView("redirect:type_of_postURl");
			}
			
			// -------------------------Delete Language  -------------------------------------
			@RequestMapping(value = "/type_of_post_delete" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView type_of_post_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				List<String> liststr = new ArrayList<String>();
				try {
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction tx = sessionHQL.beginTransaction();
					 
					String hqlUpdate = "delete from TB_Type_Of_Post where id=:id";
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
				return new ModelAndView("redirect:type_of_postURl");
			}
	
	
	
}
