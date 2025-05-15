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
import com.dao.psg.Master.Discipline_TrialedDAO;
import com.models.psg.Master.TB_PSG_MSTR_DISC_TRIALED;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/","user"})
public class Discipline_TrialedController {

	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	Discipline_TrialedDAO Didao;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/admin/Discipline_Trialed_Url", method = RequestMethod.GET)
	 public ModelAndView Discipline_Trialed_Url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		 ArrayList<ArrayList<String>> list = Didao.search_Disc_Trialed("", "active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("Discipline_TrialedTiles");
	 }
	 
	 
	 @RequestMapping(value = "/Disc_trialedAction",method=RequestMethod.POST)
		public ModelAndView Disc_trialedAction(@ModelAttribute("disc_trialedActionCMD") TB_PSG_MSTR_DISC_TRIALED com,
				 @RequestParam(value = "msg", required = false) String msg,	HttpServletRequest request,ModelMap model,HttpSession session) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
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
				
				
				
				 String disc_trialed = request.getParameter("disc_trialed").trim();
				 
				 
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();
				 
				
				try{
					
					 if (disc_trialed.equals("") || disc_trialed.equals("null") || disc_trialed.equals(null)) {
							model.put("msg", "Please Enter Discipline Trialed");
							return new ModelAndView("redirect:Discipline_Trialed_Url");
						}
					 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
							model.put("msg", "Only Select Active Status.");
							return new ModelAndView("redirect:Discipline_Trialed_Url");
						}
					
					
					Query q0 = sessionHQL.createQuery("select count(Id) from TB_PSG_MSTR_DISC_TRIALED where disc_trialed=:disc_trialed and status=:status and id !=:id");
					q0.setParameter("disc_trialed", com.getDisc_trialed());  
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						com.setDisc_trialed(disc_trialed);
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
					e.printStackTrace();
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
				return new ModelAndView("redirect:Discipline_Trialed_Url");
			}
	 
	 
	 @RequestMapping(value = "/admin/Search_Discipline_trialed", method = RequestMethod.POST)
		public ModelAndView Search_Discipline_trialed(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "disc_trialed1", required = false) String disc_trialed1 ,@ModelAttribute("status1") String status)  {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 		ArrayList<ArrayList<String>> list = Didao.search_Disc_Trialed(disc_trialed1, status);
				Mmap.put("disc_trialed1", disc_trialed1);
				Mmap.put("status1", status);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			    Mmap.put("list", list);
			   return new ModelAndView("Discipline_TrialedTiles");
			   
		}
	
				
	 @RequestMapping(value = "/Edit_Discipline_Trialed",method=RequestMethod.POST)
		public ModelAndView Edit_Discipline_Trialed(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {
			
			String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 TB_PSG_MSTR_DISC_TRIALED DT = Didao.getDisc_TrByid(Integer.parseInt(updateid));
				Mmap.put("Edit_Disc_TrialedCMD", DT);	
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			return new ModelAndView("Edit_Discipline_TrialedTiles");
		}
		
	 
		@RequestMapping(value = "/Edit_Disc_Trialed_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Disc_Trialed_Action(@ModelAttribute("Edit_Disc_TrialedCMD") TB_PSG_MSTR_DISC_TRIALED rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String disc_trialed = request.getParameter("disc_trialed").trim();
			String status = request.getParameter("status");
			
			

			if (disc_trialed.equals("") || disc_trialed.equals("null")|| disc_trialed.equals(null)) {
				TB_PSG_MSTR_DISC_TRIALED DT = Didao.getDisc_TrByid(id);
				model.put("Edit_Disc_TrialedCMD", DT);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Discipline Trialed");
				return new ModelAndView("Edit_Discipline_TrialedTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_PSG_MSTR_DISC_TRIALED DT = Didao.getDisc_TrByid(id);
				model.put("Edit_Disc_TrialedCMD", DT);	
				model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Discipline_TrialedTiles");
			}*/
	    	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_DISC_TRIALED where disc_trialed=:disc_trialed and status=:status and id !=:id");
						q0.setParameter("disc_trialed", disc_trialed);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status);
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_DISC_TRIALED set disc_trialed=:disc_trialed,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("disc_trialed",disc_trialed)
					    			  	.setString("status",status)
										.setString("modified_by", username)
										.setDate("modified_dt", new Date())
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
			return new ModelAndView("redirect:Discipline_Trialed_Url");
		}

		
		@RequestMapping(value = "/delete_Discipline_Trialed", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Discipline_Trialed(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Discipline_Trialed_Url", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_DISC_TRIALED set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:Discipline_Trialed_Url");
		}
}
