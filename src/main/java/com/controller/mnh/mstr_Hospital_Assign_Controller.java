package com.controller.mnh;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.mnh.MNH_CommonController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.mstr_Search_Hospital_DAO;
import com.models.mnh.Tb_Med_Hosp_Assign;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class mstr_Hospital_Assign_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private mstr_Search_Hospital_DAO Search_Hospital_DAO;

	
	MNH_CommonController mcommon = new MNH_CommonController();
		
		@RequestMapping(value = "/admin/mnh_hospital_assign", method = RequestMethod.GET)
		public ModelAndView mnh_hospital_assign(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("mnh_hospital_assign", roleid);
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("ml_1",mcommon.getMNHUserList("", session));
			
			Mmap.put("msg", msg);
			return new ModelAndView("mnh_hos_assignTiles");
		}
	
		
		@RequestMapping(value = "/ms_hos_assignAction", method = RequestMethod.POST)
		public ModelAndView ms_hos_assignAction(@ModelAttribute("ms_hos_assignCMD") Tb_Med_Hosp_Assign rs,
				HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg1) {
			String  roleid = session.getAttribute("roleid").toString();
	
		
			 Boolean val = roledao.ScreenRedirect("mnh_hospital_assign", session.getAttribute("roleid").toString());
	         if(val == false) {
	           return new ModelAndView("AccessTiles");
	          }
	         if(request.getHeader("Referer") == null ) {
					msg1 = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			int id = rs.getId() > 0 ? rs.getId() : 0;
			
			String username = session.getAttribute("username").toString();					
			
		
			int deo_user_id = Integer.parseInt(request.getParameter("user_id"));	
			
			// Partial swap coding for swapping
			String sus_no_list = request.getParameter("app");			
			ArrayList<String> sus_no_list_ar = new ArrayList<String>();			
			String values[] = sus_no_list.split(",");
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		Transaction tx = sessionHQL.beginTransaction();
    		Query qce = null;
    		String msg="Hospital assigned Successfully";
    		 try{	 
			for(int i=0; i<values.length;i++)
			{
				String sus = values[i].trim();
				sus_no_list_ar.add(sus);
			
				qce = sessionHQL.createQuery("select id  from Tb_Med_Hosp_Assign b where b.sus_no =:sus_no and user_id =:deo_user_id" );
				qce.setParameter("sus_no", sus.trim());
				qce.setParameter("deo_user_id", deo_user_id);
				@SuppressWarnings("unchecked")
				List<Object> result = (List<Object>) qce.list(); 
				
				sessionHQL.flush();
				sessionHQL.clear();
				if(result.size() == 0) {
					//Not in Assignmenet table Save operation
					 rs.setUser_id(deo_user_id);
					 rs.setSus_no(sus.trim());
				  	 rs.setCreated_by(username);
					 rs.setCreated_on(new Date());	
					 rs.setRoleid(Integer.parseInt(roleid));
					 sessionHQL.save(rs);									   
					 sessionHQL.flush();				
					 sessionHQL.clear();
					//End
					// model.put("msg", "Hospital assigned Successfully");
					
				} 
				
			}
			
			//Swap right to left sus_no(need to delete from table assignment)
		
			qce = sessionHQL.createQuery("select sus_no  from Tb_Med_Hosp_Assign b where b.user_id =:deo_user_id" );
			qce.setParameter("deo_user_id", deo_user_id);
			@SuppressWarnings("unchecked")
			List<String> result = (List<String>) qce.list(); 
			sessionHQL.flush();
			sessionHQL.clear();				
			result.removeAll(sus_no_list_ar);		
			
			
			 for(int i =0; i<result.size();i++) {
			
				 qce = sessionHQL.createQuery("delete from Tb_Med_Hosp_Assign b where b.sus_no =:sus_no_list and user_id =:deo_user_id" );
				 qce.setParameter("deo_user_id", deo_user_id);
				 qce.setParameter("sus_no_list", result.get(i));
				int rowCount = qce.executeUpdate();
				if(rowCount == 0) {
				
					msg= "Hospital not assigned Successfully";
				}
				
				
			 }
			
			 if(msg.equals("Hospital not assigned Successfully"))
			 {
				 tx.rollback();
					
			 }
			 else
			 {
				 tx.commit();
			 }
    		 } catch (RuntimeException e) {
    				try {
    					tx.rollback();
    					model.put("msg", "roll back transaction");
    				} catch (RuntimeException rbe) {
    					model.put("msg", "Couldn’t roll back transaction " + rbe);
    				}
    				throw e;
    			} finally {
    				if (sessionHQL != null) {
    					sessionHQL.close();
    				}
    			}
			 model.put("msg", msg);			 
			 return new ModelAndView("redirect:mnh_hospital_assign");		
		}	
	
	}
