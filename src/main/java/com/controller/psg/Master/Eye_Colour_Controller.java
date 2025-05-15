package com.controller.psg.Master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Eye_ColourDAO;
import com.models.psg.Master.TB_EYE_COLOUR;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Eye_Colour_Controller {
	
	Psg_CommonController pcommon = new Psg_CommonController();

	@Autowired
	private Eye_ColourDAO eyedao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/Eye_ColourUrl", method = RequestMethod.GET)
	 public ModelAndView Eye_ColourUrl(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 
		 Mmap.put("msg", msg);
		 Mmap.put("list", eyedao.GetSearch_Eye_Colour("","active"));
		 Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
		 return new ModelAndView("Eye_ColourTiles");
	 }

	 /******************************Save For Eye Colour***********************************/
	 
	 @RequestMapping(value = "/Eye_ColourAction",method=RequestMethod.POST)
		public ModelAndView Eye_ColourAction(@ModelAttribute("Eye_ColourCMD") TB_EYE_COLOUR EC,
				HttpServletRequest request,ModelMap model,HttpSession session, @RequestParam(value = "msg", required = false) String msg) {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 			 
				int id = EC.getId() > 0 ? EC.getId() : 0;				
				Date date = new Date();
				String username = session.getAttribute("username").toString();
				String eye_cl_name = request.getParameter("eye_cl_name").trim();
				
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction	tx = sessionHQL.beginTransaction();

				 if (eye_cl_name.equals("") || eye_cl_name.equals("null")|| eye_cl_name.equals(null)) {
					 model.put("msg", "Please Enter Colour Name");
					 return new ModelAndView("redirect:Eye_ColourUrl");
					}
				 if (EC.getStatus() == "inactive" || EC.getStatus().equals("inactive")) {
						model.put("msg", "Only Select Active Status.");
						return new ModelAndView("redirect:Eye_ColourUrl");
					}
				 				
				try{					
					Query q0 = sessionHQL.createQuery("select count(id) from TB_EYE_COLOUR where eye_cl_name=:eye_cl_name and status=:status  and id !=:id");
					q0.setParameter("eye_cl_name", EC.getEye_cl_name());  					
					q0.setParameter("id", id); 
					q0.setParameter("status", EC.getStatus());
					Long c = (Long) q0.uniqueResult();

					if (id == 0) {
						EC.setCreated_by(username);
						EC.setCreated_date(date);
						EC.setEye_cl_name(eye_cl_name);
						if (c == 0) {
							sessionHQL.save(EC);
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
						model.put("msg", "Couldn’t roll back transaction " + rbe);
					}
					throw e;
				}finally{
					if(sessionHQL!=null){
					   sessionHQL.close();
					}
				}	
				return new ModelAndView("redirect:Eye_ColourUrl");
			}

	
	
	/******************************Search For Eye Colour***********************************/
	
	@RequestMapping(value = "/admin/GetSearch_Eye_Colour", method = RequestMethod.POST)
	public ModelAndView GetSearch_Eye_Colour(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "eye_cl_name2", required = false) String eye_cl_name,
			@RequestParam(value = "status2", required = false) String status){
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
		 
			if(!eye_cl_name.equals("")) {
				Mmap.put("eye_cl_name2", eye_cl_name);
			}
			ArrayList<ArrayList<String>> list = eyedao.GetSearch_Eye_Colour(eye_cl_name,status);
				Mmap.put("list", list);
				Mmap.put("status2", status);
				Mmap.put("eye_cl_name2", eye_cl_name);
				Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
				
		return new ModelAndView("Eye_ColourTiles","Eye_ColourCMD",new TB_EYE_COLOUR());
	}

	
	 /******************************Delete For Eye Colour***********************************/
    
		/*@RequestMapping(value = "/Delete_Eye_Colour" , method = RequestMethod.POST)
		public @ResponseBody ModelAndView Delete_Eye_Colour(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			List<String> liststr = new ArrayList<String>();
			try {
				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 
				String hqlUpdate = "delete from TB_EYE_COLOUR where id=:id";
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
			return new ModelAndView("redirect:Eye_ColourUrl");
		}*/
	@RequestMapping(value = "/Delete_Eye_Colour", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Eye_Colour(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession session, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
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

			String hqlUpdate = "update TB_EYE_COLOUR set modified_by=:modified_by,modified_date=:modified_date,status=:status"
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
		return new ModelAndView("redirect:Eye_ColourUrl");
	}
		/******************************Update For Eye Colour***********************************/ 

		
		@RequestMapping(value = "/Edit_Eye_ColourUrl",method=RequestMethod.POST)
		public ModelAndView Edit_Eye_ColourUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) {	
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 		
			TB_EYE_COLOUR authDetails = eyedao.getEyeColourByid(Integer.parseInt(updateid));
			Mmap.put("Edit_Eye_ColourCMD", authDetails);
			Mmap.put("getStatusMasterList", pcommon.getStatusMasterList());
			Mmap.put("msg", msg);
			return new ModelAndView("Edit_Eye_ColourTiles");
		}
		
		
		
		@RequestMapping(value = "/Edit_Eye_ColourAction", method = RequestMethod.POST)
		public ModelAndView Edit_Eye_ColourAction(@ModelAttribute("Edit_Eye_ColourCMD") TB_EYE_COLOUR rs,
				HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Eye_ColourUrl", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) {
					msg = "";
				}
			 
			String username = session.getAttribute("username").toString();
			int id = Integer.parseInt(request.getParameter("id"));
			String eye_cl_name = request.getParameter("eye_cl_name").trim();
			String status = request.getParameter("status");
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
	        Transaction tx = session1.beginTransaction();
			
	        if (eye_cl_name.equals("") || eye_cl_name.equals("null")|| eye_cl_name.equals(null)) {
	        	TB_EYE_COLOUR authDetails = eyedao.getEyeColourByid((id));
	        	model.put("Edit_Eye_ColourCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
	        	model.put("msg", "Please Enter Colour Name");
				//model.put("id2", id);
				return new ModelAndView("Edit_Eye_ColourTiles");
			}	
	    	/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
	    		TB_EYE_COLOUR authDetails = eyedao.getEyeColourByid((id));
	        	model.put("Edit_Eye_ColourCMD", authDetails);
	        	model.put("getStatusMasterList", pcommon.getStatusMasterList());
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("Edit_Eye_ColourTiles");
			}*/
		 
			 	try {
					 Query q0 = session1.createQuery("select count(id) from TB_EYE_COLOUR where eye_cl_name=:eye_cl_name and status=:status and id !=:id");
						q0.setParameter("eye_cl_name", eye_cl_name);  
						q0.setParameter("id", id); 
						q0.setParameter("status", status); 
						Long c = (Long) q0.uniqueResult();
						
						if(c==0) {
							 String hql = "update TB_EYE_COLOUR set eye_cl_name=:eye_cl_name,modified_by=:modified_by,modified_date=:modified_date,status=:status"
										+ " where id=:id";
					                                   
					    	  Query query = session1.createQuery(hql)
					    			  	.setString("eye_cl_name",eye_cl_name)
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
		                      model.put("msg", "Couldn’t roll back transaction " + rbe);
		              }
		              throw e;
		             
				}finally{
					if(session1!=null){
						session1.close();
					}
				}
			return new ModelAndView("redirect:Eye_ColourUrl");
		}

}	
