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
import com.dao.psg.Master.SecondedToDAO;
import com.models.psg.Master.TB_PSG_MSTR_SECONDED_TO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class SecondedToController {
	
	Psg_CommonController mcommon = new Psg_CommonController();
	
	@Autowired
	SecondedToDAO sTo;
	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/SecondedToUrl", method = RequestMethod.GET)
	 public ModelAndView SecondedToUrl(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		 
		 Mmap.put("list", sTo.search_secondedTo("","active"));
		 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
		 Mmap.put("msg", msg);
		 return new ModelAndView("SecondedToTiles");
	 }
	
	@RequestMapping(value = "/SecondedTOAction", method = RequestMethod.POST)
	public ModelAndView SecondedTOAction(@ModelAttribute("SecondedTOCMD") TB_PSG_MSTR_SECONDED_TO com, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
	 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int Id = com.getId() > 0 ? com.getId() : 0;
		
		String username = session.getAttribute("username").toString();
		String secondedto = request.getParameter("secondedto").trim();
		String status = request.getParameter("status");
		
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		 if (secondedto.equals("") || secondedto.equals("null")|| secondedto.equals(null)) {
			 model.put("msg", "Please Enter Seconded To.");
			 return new ModelAndView("redirect:SecondedToUrl");
			}
		 if (com.getStatus() == "inactive" || com.getStatus().equals("inactive")) {
				model.put("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:SecondedToUrl");
			}
		 
		try{
			
			Query q0 = sessionHQL.createQuery("select count(id) from TB_PSG_MSTR_SECONDED_TO where seconded_to=:secondedto");
			q0.setParameter("secondedto", secondedto);  
			Long c = (Long) q0.uniqueResult();

			if (Id == 0) {
				com.setCreated_by(username);
				com.setCreated_dt(new Date());
				com.setSeconded_to(secondedto);
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
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
		return new ModelAndView("redirect:SecondedToUrl");
	}
	@RequestMapping(value = "/admin/getsecondedToSearch", method = RequestMethod.POST)
	public ModelAndView getsecondedToSearch(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "secondedto1", required = false) String secondedto1,
			@RequestParam(value = "status1", required = false) String status1){
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
			Mmap.put("secondedto1", secondedto1);
			Mmap.put("status1", status1);
			Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			ArrayList<ArrayList<String>> list = sTo.search_secondedTo(secondedto1, status1);
		    Mmap.put("list", list);
		return new ModelAndView("SecondedToTiles","SecondedTOCMD",new TB_PSG_MSTR_SECONDED_TO());
	}
	
	@RequestMapping(value = "/Edit_SecondedTo",method=RequestMethod.POST)
	public ModelAndView Edit_SecondedTo(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit,HttpServletRequest request) {
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		TB_PSG_MSTR_SECONDED_TO authDetails = sTo.getmtByid(Integer.parseInt(updateid));
			 Mmap.put("Edit_STOCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", msg);
		return new ModelAndView("Edit_SecondedToTiles");
	}

	@RequestMapping(value = "/Edit_SecondedToAction", method = RequestMethod.POST)
	public ModelAndView Edit_SecondedToAction(@ModelAttribute("Edit_SecondedToCMD") TB_PSG_MSTR_SECONDED_TO rs,
			HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
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
        
       String secondedto = request.getParameter("secondedto").trim();

		if (secondedto.equals("") || secondedto.equals("null") || secondedto.equals(null)) {
			TB_PSG_MSTR_SECONDED_TO authDetails = sTo.getmtByid(id);
			 Mmap.put("Edit_STOCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Please Enter Type Of Entry");
			return new ModelAndView("Edit_SecondedToTiles");
		}	
		/*if (rs.getStatus() == "inactive" || rs.getStatus().equals("inactive")) {
			TB_PSG_MSTR_SECONDED_TO authDetails = sTo.getmtByid(id);
			 Mmap.put("Edit_STOCMD", authDetails);
			 Mmap.put("getStatusMasterList", mcommon.getStatusMasterList());
			 Mmap.put("msg", "Only Select Active Status");
			return new ModelAndView("Edit_SecondedToTiles");
		}*/
		
        try {
			
        	Query q0 = session1.createQuery("select count(id) from TB_PSG_MSTR_SECONDED_TO where seconded_to=:secondedto  and id !=:id");
			q0.setParameter("secondedto", secondedto);  
			q0.setParameter("id", id); 
				Long c = (Long) q0.uniqueResult();
				
				if(c==0) {
					 String hql = "update TB_PSG_MSTR_SECONDED_TO set seconded_to=:secondedto,status=:status,modified_by=:modified_by,modified_dt=:modified_date"
								+ " where id=:id";
			                                   
			    	  Query query = session1.createQuery(hql)
			    			  	.setString("secondedto",secondedto)
			    			  	.setString("status", rs.getStatus())
								.setString("modified_by", username).setDate("modified_date", new Date())
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
	            	  Mmap.put("msg", "Couldn't roll back transaction " + rbe);
	              }
	              throw e;
	             
			}finally{
				if(session1!=null){
					session1.close();
				}
			}
		return new ModelAndView("redirect:SecondedToUrl");
	}
	
	 
	 @RequestMapping(value = "/deleteSTOURL", method = RequestMethod.POST)
		public @ResponseBody ModelAndView deleteSTOURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request, HttpSession session,
				HttpSession sessionA, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("SecondedToUrl", roleid);
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
				 
				 String hqlUpdate = "update TB_PSG_MSTR_SECONDED_TO set modified_by=:modified_by,modified_dt=:modified_dt,status=:status"
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
			return new ModelAndView("redirect:SecondedToUrl");
		}

}
