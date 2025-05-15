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
import com.dao.psg.Master.Mother_TongueDAO;
import com.models.psg.Master.TB_MOTHERTOUNGE;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/","user"})
public class Mother_tongueController {
			
	@Autowired
	private Mother_TongueDAO mtdao;
//	@Autowired
//	private RoleBaseMenuDAO roledao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	 @RequestMapping(value = "/admin/Mother_tongueUrl", method = RequestMethod.GET)
	 public ModelAndView Mother_tongueUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

		 
		 Mmap.put("list", mtdao.search_M_T_dtl("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("Mother_tongueTiles");
	 }
			 

			 @RequestMapping(value = "/M_TAction", method = RequestMethod.POST)
				public ModelAndView M_TAction(@ModelAttribute("CourseCMD") TB_MOTHERTOUNGE com, BindingResult result,
						HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
				 
				 
				 String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					int Id = com.getId() > 0 ? com.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					String mothertounge = request.getParameter("mothertounge").trim();
					String status = request.getParameter("status");
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					
					 if (mothertounge.equals("") || mothertounge.equals("null")|| mothertounge.equals(null)) {
						 model.put("msg", "Please Enter Mother Tounge.");
						 return new ModelAndView("redirect:Mother_tongueUrl");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Mother_tongueUrl");
						}
					try{
						
						Query q0 = sessionHQL.createQuery("select count(Id) from TB_MOTHERTOUNGE where mothertounge=:mothertounge and status=:status and Id !=:Id");
						q0.setParameter("mothertounge", com.getMothertounge());  
						q0.setParameter("status", com.getStatus());
						q0.setParameter("Id", Id);  
						Long c = (Long) q0.uniqueResult();

						if (Id == 0) {
							com.setCreated_by(username);
							com.setCreated_dt(date);
							com.setMothertounge(mothertounge);
							com.setStatus(status);
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
					return new ModelAndView("redirect:Mother_tongueUrl");
				}
	
				@RequestMapping(value = "/admin/getSearchM_T", method = RequestMethod.POST)
				public ModelAndView getSearchM_T(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "mothertounge1", required = false) String mothertounge1,
						@RequestParam(value = "status1", required = false) String status1){
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					Mmap.put("mothertounge1", mothertounge1);
					Mmap.put("status1", status1);
					Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
					ArrayList<ArrayList<String>> list = mtdao.search_M_T_dtl(mothertounge1, status1);
					Mmap.put("list", list);
					return new ModelAndView("Mother_tongueTiles","M_TCMD",new TB_MOTHERTOUNGE());
				}
		

				@RequestMapping(value = "/Edit_M_T",method=RequestMethod.POST)
				public ModelAndView Edit_M_T(@ModelAttribute("id2") String updateid,ModelMap Mmap,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
						HttpSession sessionEdit,HttpServletRequest request) {
					String roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}
					if (request.getHeader("Referer") == null) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					TB_MOTHERTOUNGE authDetails = mtdao.getmtByid(Integer.parseInt(updateid));
						 Mmap.put("Edit_M_TCMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", msg);
					return new ModelAndView("Edit_M_TTiles");
				}

				@RequestMapping(value = "/Edit_M_T_Action", method = RequestMethod.POST)
				public ModelAndView Edit_M_T_Action(@ModelAttribute("Edit_M_TCMD") TB_MOTHERTOUNGE rs,
						HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
						
						@SuppressWarnings("unused")
						String roleid = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
						if (val == false) {
							return new ModelAndView("AccessTiles");
						}
						if (request.getHeader("Referer") == null) {
							msg = "";
							return new ModelAndView("redirect:bodyParameterNotAllow");
						}
					String username = session.getAttribute("username").toString();
					int id = Integer.parseInt(request.getParameter("id"));
					
					Session session1 = HibernateUtil.getSessionFactory().openSession();
			        Transaction tx = session1.beginTransaction();
			        
			       String mothertounge = request.getParameter("mothertounge").trim();

					if (mothertounge.equals("") || mothertounge.equals("null") || mothertounge.equals(null)) {
						TB_MOTHERTOUNGE authDetails = mtdao.getmtByid(id);
						 Mmap.put("Edit_M_TCMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", "Please Enter Mother Tongue");
						return new ModelAndView("Edit_M_TTiles");
					}	
					/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
						TB_MOTHERTOUNGE authDetails = mtdao.getmtByid(id);
						 Mmap.put("Edit_M_TCMD", authDetails);
						 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
						 Mmap.put("msg", "Only Select Active Status");
						return new ModelAndView("Edit_M_TTiles");
					}*/
					
			        try {
						
			        	Query q0 = session1.createQuery("select count(id) from TB_MOTHERTOUNGE where mothertounge=:mothertounge  and id !=:id");
						q0.setParameter("mothertounge", mothertounge);  
						q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
							
							if(c==0) {
								 String hql = "update TB_MOTHERTOUNGE set mothertounge=:mothertounge,status=:status"
											+ " where id=:id";
						                                   
						    	  Query query = session1.createQuery(hql)
						    			  	.setString("mothertounge",rs.getMothertounge())
						    			  	.setString("status", rs.getStatus())
											//.setString("modified_by", username).setDate("modified_date", new Date())
											.setInteger("id",id);
						                    msg = query.executeUpdate() > 0 ? "1" :"0";
						                    tx.commit(); 
						                    
						                    if(msg == "1") {
						                    	Mmap.put("msg", "Data Updated Successfully.");
						                    }
						                    else {
						                    	Mmap.put("msg", "Data Not Updated.");
						                    }
							}
							else {
								Mmap.put("msg", "Data already Exist.");
							}
						
					  }catch(RuntimeException e){
				              try{
				                      tx.rollback();
				                      Mmap.put("msg", "roll back transaction");
				              }catch(RuntimeException rbe){
				            	  Mmap.put("msg", "Couldn�t roll back transaction " + rbe);
				              }
				              throw e;
				             
						}finally{
							if(session1!=null){
								session1.close();
							}
						}
					return new ModelAndView("redirect:Mother_tongueUrl");
				}

				@RequestMapping(value = "/deletemtURL", method = RequestMethod.POST)
				public @ResponseBody ModelAndView deletemtURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
						HttpSession sessionA, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
					
					String roleid = session.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("Mother_tongueUrl", roleid);
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
						 
						 String hqlUpdate = "update TB_MOTHERTOUNGE set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
					return new ModelAndView("redirect:Mother_tongueUrl");
				}
}
