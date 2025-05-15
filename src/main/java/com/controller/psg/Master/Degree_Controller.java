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
import com.dao.psg.Master.DegreeDao;
import com.models.psg.Master.TB_DEGREE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Degree_Controller {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	private DegreeDao dgr;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	// -------------------------------Degree For page Open -------------------------------------
	 @RequestMapping(value = "/admin/Degreeurl", method = RequestMethod.GET)
	 public ModelAndView Degreeurl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		 ArrayList<ArrayList<String>> list = dgr.search_Degree("","active");
		 
			Mmap.put("list", list);
			Mmap.put("msg", msg);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());

		 return new ModelAndView("DegreeTiles");


	 }
	 
	// -------------------------------Degree save -------------------------------------
	 @RequestMapping(value = "/DegreeAction",method=RequestMethod.POST)
		
			public ModelAndView DegreeAction(@ModelAttribute("DegreeCMD") TB_DEGREE rm,HttpServletRequest request,
					 @RequestParam(value = "msg", required = false) String msg,ModelMap model,HttpSession session) {
		   String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

						 
					int id = rm.getId() > 0 ? rm.getId() : 0;
					
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					 Transaction	tx = sessionHQL.beginTransaction();
					 
					String degree = request.getParameter("degree").trim();
					
					 if (degree.equals("") ||degree.equals("null")|| degree.equals(null)) {
						 model.put("msg", "Please Enter Degree");
						 return new ModelAndView("redirect:Degreeurl");
						}
					
					if (rm.getStatus() == "inactive" || rm.getStatus().equals("inactive")) {
						 model.put("msg", "Only Select Active Status.");
						 return new ModelAndView("redirect:Degreeurl");
						}
					
					try{

						Query q0 = sessionHQL.createQuery("select count(id) from TB_DEGREE where degree=:degree  and status=:status and id !=:id");
						q0.setParameter("degree", rm.getDegree()); 
						 
						q0.setParameter("status",rm.getStatus()); 
						q0.setParameter("id", id);  
						
						
						Long c = (Long) q0.uniqueResult();
						if (id == 0) {
							rm.setCreated_by(username);
							rm.setCreated_on(date);
							rm.setDegree(degree);
							if (c == 0) {
								sessionHQL.save(rm);
								sessionHQL.flush();
								sessionHQL.clear();
								model.put("msg", "Data Saved Successfully.");
								
							}else {
								model.put("msg", "Data already Exist.");
							}
							tx.commit();
							}
						}
						
					
						catch(RuntimeException e){
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
					return new ModelAndView("redirect:Degreeurl");
				}	
	 
	 
	//.........................................Search..................................//
	 
		 @RequestMapping(value = "/Degree_Search" , method = RequestMethod.POST)
			public ModelAndView Degree_Search(ModelMap Mmap, HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "degree1", required = false) String degree1,
					@RequestParam(value = "status1", required = false) String status1)

				{
			 String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				ArrayList<ArrayList<String>> list = dgr.search_Degree(degree1,status1);

				Mmap.put("list", list);
				Mmap.put("size", list.size());
				Mmap.put("degree1", degree1);
				Mmap.put("status1", status1);
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				
				return new ModelAndView("DegreeTiles");
		 }
		 
		 
	 //.................................................Edit..............................//
	 
	 @RequestMapping(value = "/edit_degree",method=RequestMethod.POST)
		public ModelAndView edit_degree(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
			
		 String roleid = sessionEdit.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 TB_DEGREE degreeDetails = dgr.getdegreeByid(Integer.parseInt(updateid));
				Mmap.put("Edit_DegreeCMD", degreeDetails);
				
				Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
				Mmap.put("msg", msg);
			 
			return new ModelAndView("EditDegreeTiles");
		}
		
		
		@RequestMapping(value = "/edit_degree_Action", method = RequestMethod.POST)
		public ModelAndView edit_degree_Action(@ModelAttribute("Edit_DegreeCMD") TB_DEGREE ds,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			
			String username = session.getAttribute("username").toString();
			
			int id = Integer.parseInt(request.getParameter("id"));
			String degree = request.getParameter("degree").trim();
			String status = request.getParameter("status");
			
			 
				if (degree.equals("") || degree.equals("null") || degree.equals(null)) {
					TB_DEGREE degreeDetails = dgr.getdegreeByid((id));
					model.put("Edit_DegreeCMD", degreeDetails);
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Please Enter Degree");
					return new ModelAndView("EditDegreeTiles");
				}
				/*if (ds.getStatus() == "inactive" || ds.getStatus().equals("inactive")) {
					TB_DEGREE degreeDetails = dgr.getdegreeByid((id));
					model.put("Edit_DegreeCMD", degreeDetails);
					model.put("getStatusMasterList", mcommon.getStatusMasterList());
					model.put("msg", "Only Select Active Status.");
					return new ModelAndView("EditDegreeTiles");
				}*/
			
	
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
					 
					 try {
						 Query q0 = session1.createQuery("select count(id) from TB_DEGREE where degree=:degree and status=:status and id !=:id");
							
						 q0.setParameter("degree", degree); 
							
							q0.setParameter("status", status); 
							q0.setParameter("id", id); 
							Long c = (Long) q0.uniqueResult();
						
							if(c==0) {
					 
					 
					 
							 String hql = "update TB_DEGREE set degree=:degree, status=:status,modified_by=:modified_by,modified_on=:modified_on"
										+ " where id=:id";
					    	  Query query = session1.createQuery(hql)
					    			   .setInteger("id",id)
					    			  	.setString("degree",degree)
					    			  	.setString("status",status)
										.setString("modified_by", username)
										.setDate("modified_on", new Date());
										
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
					
				 }catch(RuntimeException e ){
		              try{
		                      tx.rollback();
		                      model.put("msg", "roll back transaction");
		              }catch(RuntimeException rbe){
		                      model.put("msg", "Couldn�t roll back transaction " + rbe);
		              }
		              throw e ;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Degreeurl");
		}

 
	 
	 //......................................delete..............................//

		@RequestMapping(value = "/delete_degree", method = RequestMethod.POST)
		public @ResponseBody ModelAndView delete_degree(@ModelAttribute("id1") int id, BindingResult result,
				HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Degreeurl", roleid);
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

				String hqlUpdate = "update TB_DEGREE set modified_by=:modified_by,modified_on=:modified_on,status=:status"
						+ " where id=:id";

				int app = sessionHQL.createQuery(hqlUpdate).setString("status", "inactive")
						.setString("modified_by", username).setDate("modified_on", new Date()).setInteger("id", id)
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
			return new ModelAndView("redirect:Degreeurl");
		}

}


