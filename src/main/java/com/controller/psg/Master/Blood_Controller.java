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
import com.dao.psg.Master.BloodDao;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.psg.Master.TB_BLOOD_GROUP;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Blood_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();
	@Autowired
	private BloodDao bld;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	// -------------------------------Blood For page Open -------------------------------------
	@RequestMapping(value = "/admin/Blood", method = RequestMethod.GET)
	 public ModelAndView Blood(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Blood", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		
		List<Map<String, Object>> list = bld.search_BloodRepo("","active");
		Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("BloodTiles");
	 }
	
	// -------------------------------Blood save -------------------------------------
	
	
	 @RequestMapping(value = "/BloodAction",method=RequestMethod.POST)
		public ModelAndView BloodAction(@ModelAttribute("BloodCmd") TB_BLOOD_GROUP com,HttpServletRequest request,ModelMap model,HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Blood", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
				int id = com.getId() > 0 ? com.getId() : 0;
				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String blood_desc = request.getParameter("blood_desc").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
					
				 if (blood_desc.equals("") || blood_desc.equals("null")
							|| blood_desc.equals(null)) {
						model.put("msg", "Please Enter Blood Description");
						return new ModelAndView("redirect:Blood");
					}
				 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
						
						model.put("msg", "Only Select Active Status.");
			
						return new ModelAndView("redirect:Blood");
			
					}
				 
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_BLOOD_GROUP where blood_desc=:blood_desc and status=:status and id !=:id");
					q0.setParameter("blood_desc", com.getBlood_desc());  
					q0.setParameter("status", com.getStatus());
					
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						
						com.setCreated_by(username);
						com.setCreated_on(date);
						com.setBlood_desc(blood_desc);
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
				return new ModelAndView("redirect:Blood");
			}
	 

		// -------------------------SEARCH Blood Report  -------------------------------------
	
		@RequestMapping(value = "/search_Blood" , method = RequestMethod.POST)
		public ModelAndView search_Blood(ModelMap model, HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("blood_desc1") String blood_desc1,
				@ModelAttribute("status1") String status) {

			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Blood", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			List<Map<String, Object>> list = bld.search_BloodRepo(blood_desc1,status);
			model.put("list", list);
			model.put("size", list.size());
			model.put("blood_desc1", blood_desc1);
			model.put("status1", status);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			return new ModelAndView("BloodTiles");
		}

		// -------------------------Edit Blood For page Open -------------------------------------
		@RequestMapping(value = "/edit_Blood",method = RequestMethod.POST )
		public ModelAndView edit_Blood(@ModelAttribute("updateid") String updateid, ModelMap model, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession sessionEdit) {
			String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Blood", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			TB_BLOOD_GROUP OpdDetails = bld.getBloodByid(Integer.parseInt(updateid));
			model.put("EditBloodCMD", OpdDetails);
			model.put("getStatusMasterList", pcommon.getStatusMasterList());
			model.put("msg", msg);
	
			return new ModelAndView("EditBloodTiles");
		}
	
		// -------------------------Edit Blood Action -------------------------------------
		
		@RequestMapping(value = "/EditBloodAction", method = RequestMethod.POST)
		public ModelAndView EditBloodAction(@ModelAttribute("EditBloodCMD") TB_BLOOD_GROUP b, 
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Blood", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		//	Mmap.put("updateid", b.getId());
			int id = Integer.parseInt(request.getParameter("id"));
			 String username = session.getAttribute("username").toString();
			 String blood_desc1=request.getParameter("blood_desc").trim();
			 String status = request.getParameter("status");
				
				Session session1 = HibernateUtil.getSessionFactory().openSession();
	       		Transaction tx = session1.beginTransaction();
			 
						if (blood_desc1.equals("") || blood_desc1.equals("null") || blood_desc1.equals(null)) {
							TB_BLOOD_GROUP OpdDetails = bld.getBloodByid((id));
							model.put("EditBloodCMD", OpdDetails);
							model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Please Enter Blood Description");
				
							return new ModelAndView("EditBloodTiles");
						}
						/*if (b.getStatus() == "inactive" || b.getStatus().equals("inactive")) {
							TB_BLOOD_GROUP OpdDetails = bld.getBloodByid((id));
							model.put("EditBloodCMD", OpdDetails);
							model.put("getStatusMasterList", pcommon.getStatusMasterList());
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("EditBloodTiles");
				
						}*/
		       		
			try {
				 Query q0 = session1.createQuery("select count(id) from TB_BLOOD_GROUP where blood_desc=:blood_desc and status=:status and id !=:id");
					q0.setParameter("blood_desc", blood_desc1); 
					q0.setParameter("status", status); 
					q0.setParameter("id", id); 
					Long c = (Long) q0.uniqueResult();
				
					if(c==0) {
						 String hql = "update TB_BLOOD_GROUP set blood_desc=:blood_desc,modify_by=:modify_by,modify_on=:modify_on,status=:status"
									+ " where id=:id";
				                                   
				    	  Query query = session1.createQuery(hql)
				    			  	.setString("blood_desc",blood_desc1)
				    				.setString("status",status)
									.setString("modify_by", username)
									.setDate("modify_on", new Date())
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
			} finally {
				
			}
			return new ModelAndView("redirect:Blood");
		}
		
		// -------------------------Delete Blood  -------------------------------------
/*		@RequestMapping(value = "/blood_delete" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView blood_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_BLOOD_GROUP where id=:id";
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
			return new ModelAndView("redirect:Blood");
		}*/
		@RequestMapping(value = "/blood_delete", method = RequestMethod.POST)
		public @ResponseBody ModelAndView blood_delete(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Blood", roleid);
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
				 
				 String hqlUpdate = "update TB_BLOOD_GROUP set modify_by=:modify_by,modify_on=:modify_on,status=:status"
										+ " where id=:id";
				
				 int app = sessionHQL.createQuery(hqlUpdate).setString("status","inactive")
						.setString("modify_by", username)
						.setDate("modify_on", new Date()).setInteger("id", id).executeUpdate();
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
			return new ModelAndView("redirect:Blood");
		}
}
