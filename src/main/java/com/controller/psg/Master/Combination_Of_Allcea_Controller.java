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
import com.dao.psg.Master.Combination_Of_AllceaDAO;
import com.models.psg.Master.TB_PSG_MSTR_COMBINATION_OF_ALLCEA;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Combination_Of_Allcea_Controller {

	Psg_CommonController pcommon = new Psg_CommonController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Combination_Of_AllceaDAO COAdao;
	
	@RequestMapping(value = "/admin/Combination_of_allceaUrl", method = RequestMethod.GET)
	 public ModelAndView Combination_of_allceaUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		
		 ArrayList<ArrayList<String>> list = COAdao.search_Combination_of_allcea("0","0","active");
		 Mmap.put("list", list);
		 Mmap.put("msg", msg);
		 Mmap.put("getField_areaList", pcommon.getField_areaList());
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 
		 return new ModelAndView("Combination_of_allceaTiles");
	 }
	
	 @RequestMapping(value = "/Combination_of_allceaAction",method=RequestMethod.POST)
		public ModelAndView Combination_of_allceaAction(@ModelAttribute("Combination_of_allceaCMD") TB_PSG_MSTR_COMBINATION_OF_ALLCEA com,HttpServletRequest request,ModelMap model,
				HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
			

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
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
				
				 if (com.getFd1() == 0) {
			        	model.put("msg", "Please Select FD 1");
						return new ModelAndView("redirect:Combination_of_allceaUrl");
					}
				 if (com.getFd2() == 0) {
			        	model.put("msg", "Please Select FD 2");
						return new ModelAndView("redirect:Combination_of_allceaUrl");
					}
				 

				 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Combination_of_allceaUrl");
					}
				 
				try{
					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_COMBINATION_OF_ALLCEA where fd1=:fd1 and fd2=:fd2 and "
							+ "status=:status and id !=:id");
					q0.setParameter("fd1", com.getFd1());  
					q0.setParameter("fd2", com.getFd2());
					q0.setParameter("status", com.getStatus());
					q0.setParameter("id", id);  
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						com.setCreated_by(username);
						com.setCreated_dt(date);
						
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
				return new ModelAndView("redirect:Combination_of_allceaUrl");
			}
	 
	 @RequestMapping(value = "/admin/getSearch_Combination_Master", method = RequestMethod.POST)
		public ModelAndView getSearch_Combination_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "fd12", required = false) String fd1,
				@RequestParam(value = "fd22", required = false) String fd2,@ModelAttribute("status2") String status)  {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 		ArrayList<ArrayList<String>> list = COAdao.search_Combination_of_allcea(fd1,fd2,status);
				Mmap.put("fd12", fd1);
				Mmap.put("fd22", fd2);
				Mmap.put("status2", status);
				Mmap.put("getField_areaList", pcommon.getField_areaList());
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			    Mmap.put("list", list);
			    
			   return new ModelAndView("Combination_of_allceaTiles","Combination_of_allceaCMD",new TB_PSG_MSTR_COMBINATION_OF_ALLCEA());
			   
		}
	 
	 @RequestMapping(value = "/Edit_Combination_of_Allcea",method=RequestMethod.POST)
		public ModelAndView Edit_Combination_of_Allcea(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {

		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
		 TB_PSG_MSTR_COMBINATION_OF_ALLCEA FR = COAdao.getCombinationByid(Integer.parseInt(updateid));
				Mmap.put("Edit_CombinationCMD", FR);	
				Mmap.put("getField_areaList", pcommon.getField_areaList());
				Mmap.put("msg", msg);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
			return new ModelAndView("Edit_Combination_of_AllceaTiles");
		}
	 
	 @RequestMapping(value = "/Edit_Combination_Action", method = RequestMethod.POST)
		public ModelAndView Edit_Combination_Action(@ModelAttribute("Edit_FieldAreaCMD") TB_PSG_MSTR_COMBINATION_OF_ALLCEA rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String fd1 = request.getParameter("fd1");
			String fd2 = request.getParameter("fd2");
			String status = request.getParameter("status");
			
			 if (rs.getFd1() == 0) {
				 TB_PSG_MSTR_COMBINATION_OF_ALLCEA authDetails = COAdao.getCombinationByid(id);
		        	model.put("Edit_CombinationCMD", authDetails);
		        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Select FD 1");
					return new ModelAndView("Edit_Combination_of_AllceaTiles");
				}
			 if (rs.getFd2() == 0) {
				 TB_PSG_MSTR_COMBINATION_OF_ALLCEA authDetails = COAdao.getCombinationByid(id);
		        	model.put("Edit_CombinationCMD", authDetails);
		        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
		        	model.put("msg", "Please Select FD 2");
					return new ModelAndView("Edit_Combination_of_AllceaTiles");
				}	
		    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
		    		TB_PSG_MSTR_COMBINATION_OF_ALLCEA authDetails = COAdao.getCombinationByid(id);
		        	model.put("Edit_Eye_ColourCMD", authDetails);
		        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("Edit_Combination_of_AllceaTiles");
				}*/
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
				 try {
					
					 Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_COMBINATION_OF_ALLCEA where fd1=:fd1 and fd2=:fd2 and status=:status and id !=:id");
						q0.setParameter("fd1", Integer.parseInt(fd1));  
						q0.setParameter("fd2", Integer.parseInt(fd2));
						q0.setParameter("status", status);
						q0.setParameter("id", id); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_PSG_MSTR_COMBINATION_OF_ALLCEA set fd1=:fd1,fd2=:fd2,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setInteger("fd1",Integer.parseInt(fd1))
					    			  	.setInteger("fd2",Integer.parseInt(fd2))
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
			return new ModelAndView("redirect:Combination_of_allceaUrl");
		}

		
		@RequestMapping(value = "/delete_Combination_of_Allcea", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_Combination_of_Allcea(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			

			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Combination_of_allceaUrl", roleid);	
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_COMBINATION_OF_ALLCEA set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
										+ " where id=:id";
				 
				//String hqlUpdate = "delete from TB_RELIGION where religion_id=:religion_id";
				
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
			return new ModelAndView("redirect:Combination_of_allceaUrl");
		}
}
