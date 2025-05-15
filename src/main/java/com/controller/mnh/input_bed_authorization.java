package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.mstr_bed_authoDAO;
import com.models.mnh.TB_Med_Authorisation_All;
import com.models.mnh.Tb_Med_Death;
import com.models.mnh.Tb_Med_Eir;
import com.models.mnh.Tb_Med_History_Stay;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 

public class input_bed_authorization {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	MNH_CommonController mcommon = new MNH_CommonController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	MNH_ValidationController validation = new MNH_ValidationController();
	@Autowired
	private mstr_bed_authoDAO bAuth;
	
	@RequestMapping(value = "/admin/bed_authorization", method = RequestMethod.GET)
	public ModelAndView bed_authorization(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {

		String username = (String) session.getAttribute("username");
		int userid = (Integer) session.getAttribute("userId");		

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("bed_authorization", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
	 	
		Mmap.put("msg", msg);		
		Mmap.put("getMedSystemCodeQuarter", mcommon.getMedSystemCodeQua("QUARTER", "", session));
		Mmap.put("service_type", mcommon.getMedSystemCodeQua("Service_Type", "", session));
		return new ModelAndView("mnh_input_bed_authorizationTiles");
	}
	
	@RequestMapping(value = "/bed_authorizationACT", method = RequestMethod.POST)
	public ModelAndView bed_authorizationACT(@ModelAttribute("bed_authorizationCMD") TB_Med_Authorisation_All rs,
			HttpServletRequest request,ModelMap model,HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("bed_authorization", roleid);
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		
		if(roleAccess.equals("Unit")){
			model.put("sus_no",roleSusNo);
			model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		int id = rs.getId() > 0 ? rs.getId() : 0;	

		String username = session.getAttribute("username").toString();
		String sus_no1=request.getParameter("sus_no");
		String unit_name1 = request.getParameter("unit_name1");
		String command = request.getParameter("command");
		
		String off_auth = request.getParameter("off_auth1");
		Integer off_auth1 = 0;
		if (off_auth != "" && !off_auth.equals("")) {
			off_auth1 = Integer.parseInt(off_auth);
		}
		String jco_or_auth = request.getParameter("jco_or_auth1");
		Integer jco_or_auth1 = 0;
		if (jco_or_auth != "" && !jco_or_auth.equals("")) {
			jco_or_auth1 = Integer.parseInt(jco_or_auth);
		}
		String off_fam_auth = request.getParameter("off_fam_auth1");
		Integer off_fam_auth1 = 0;
		if (off_fam_auth != "" && !off_fam_auth.equals("")) {
			off_fam_auth1 = Integer.parseInt(off_fam_auth);
		}
		String jco_or_fam_auth = request.getParameter("jco_or_fam_auth1");
		Integer jco_or_fam_auth1 = 0;
		if (jco_or_fam_auth != "" && !jco_or_fam_auth.equals("")) {
			jco_or_fam_auth1 = Integer.parseInt(jco_or_fam_auth);
		}
		String others = request.getParameter("others1");
		Integer others1 = 0;
		if (others != "" && !others.equals("")) {
			others1 = Integer.parseInt(others);
		}
		String total_all = request.getParameter("total_all1");
		Integer total_all1 = 0;
		if (total_all != "" && !total_all.equals("")) {
			total_all1 = Integer.parseInt(total_all);
		}
		String laid_out = request.getParameter("laid_out1");
		Integer laid_out1 = 0;
		if (laid_out != "" && !laid_out.equals("")) {
			laid_out1 = Integer.parseInt(laid_out);
		}
		 if( sus_no1 == null || sus_no1.equals("")){ 
             model.put("msg", "Please Enter the SUS No");
             return new ModelAndView("redirect:bed_authorization");
              } 
		 if(validation.sus_noLength(sus_no1) == false){
	        model.put("msg",validation.sus_noMSG);
	        return new ModelAndView("redirect:bed_authorization");
	            }
	       if( command == null || command.equals("")){ 
			model.put("msg", "Command Name Should not be Null");
			return new ModelAndView("redirect:bed_authorization");
		}
		if(Integer.parseInt(total_all) == 0) {	
				model.put("msg", "Total Bed Auth Should not be Null or Zero");
				return new ModelAndView("redirect:bed_authorization");
			}
       Integer t1 = (off_auth1 + jco_or_auth1 + off_fam_auth1 + jco_or_fam_auth1) ;
		
		if((t1) > Integer.parseInt(total_all)) {	
			model.put("msg", "Total Bed Auth Value should be less than or equal to the total of Officer,JCO/OR,Officer Family and JCO/OR Family Auth.");
			return new ModelAndView("redirect:bed_authorization");
		}
		if(Integer.parseInt(laid_out) < 0) {	
			model.put("msg", "Bed Laid Out should be greater than 0");
			return new ModelAndView("redirect:bed_authorization");
		}
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
           try {
			
			Long op= bAuth.checkExitstingbauth(sus_no1,rs.getId());
				rs.setCreated_on(new Date());
				rs.setCreated_by(username);
				rs.setOff_auth(off_auth1);
				rs.setJco_or_auth(jco_or_auth1);
				rs.setOff_fam_auth(off_fam_auth1);
				rs.setJco_or_fam_auth(jco_or_fam_auth1);
				rs.setOthers(others1);
				rs.setTotal_all(total_all1);
				rs.setLaid_out(laid_out1);
				
				if (op == 0) {
					session1.save(rs);
						tx.commit();
						model.put("msg", "Data Saved Successfully.");
					} 
					if (op > 0) 
						
					{
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
		return new ModelAndView("redirect:bed_authorization");

	}
	
	@RequestMapping(value = "/getSearchbauthMaster",method=RequestMethod.POST)
	public ModelAndView getSearchbauthMaster(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
		@RequestParam(value = "sus1", required = false) String sus1,HttpServletRequest request,
		@RequestParam(value = "unit1", required = false) String unit1,
		@RequestParam(value = "cmd1", required = false) String cmd1,
		@RequestParam(value = "off1", required = false) String off1,
		@RequestParam(value = "jco1", required = false) String jco1,
		@RequestParam(value = "ofam1", required = false) String ofam1,
		@RequestParam(value = "jcofam1", required = false) String jcofam1,
		@RequestParam(value = "laidout1", required = false) String laidout1,
		@RequestParam(value = "others1", required = false) String others1,
		@RequestParam(value = "total_all1", required = false) String total_all1){
		
		Boolean val = roledao.ScreenRedirect("bed_authorization", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE","",session));
		Mmap.put("getMedSystemCode_CATEGORY", mcommon.getMedSystemCode("CATEGORY","",session));
	 	Mmap.put("sus1", sus1);
	 	Mmap.put("unit1", unit1);
	 	Mmap.put("cmd1", cmd1);
	 	Mmap.put("off1", off1);
	 	Mmap.put("jco1", jco1);
	 	Mmap.put("ofam1", ofam1);
	 	Mmap.put("jcofam1", jcofam1);
	 	Mmap.put("total_all1", total_all1);
	 	Mmap.put("laidout1", laidout1);
	 	Mmap.put("others1", others1);
	 	Mmap.put("total_all1", total_all1);
	 	
	 	
	 	List<Map<String, Object>> list =bAuth.getSearchbauthoMaster(sus1,total_all1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("mnh_input_bed_authorizationTiles");
	}
	
	@RequestMapping(value = "/deletebAuthoMasterURL", method = RequestMethod.POST)
	public @ResponseBody ModelAndView deletebAuthoMasterURL(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		Boolean val = roledao.ScreenRedirect("bed_authorization", sessionA.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from tb_med_authorisation_all where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UnSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:bed_authorization");
	}
	
	@RequestMapping(value = "/edit_mst_bed_autho")
	public ModelAndView edit_mst_bed_autho(@ModelAttribute("id2") String updateid,ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {
		
		
		String  roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("bed_authorization", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Mmap.put("date", date1);
			
			TB_Med_Authorisation_All authDetails = bAuth.getbedauthByid(Integer.parseInt(updateid));
			Mmap.put("Edit_bed_authorizationCMD", authDetails);
			
			Mmap.put("msg", msg);
			
		return new ModelAndView("mnh_edit_bed_authorizationTiles");
	}	
	
	@RequestMapping(value = "/Edit_bed_authorizationACT", method = RequestMethod.POST)
	public ModelAndView edit_mortality_detailsAction(@ModelAttribute("Edit_bed_authorizationCMD") TB_Med_Authorisation_All rs,
			HttpServletRequest request,ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		Boolean val = roledao.ScreenRedirect("bed_authorization", session.getAttribute("roleid").toString());
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		 String username = session.getAttribute("username").toString();
		 String sus_no1=request.getParameter("sus_no");
		 String command=request.getParameter("command");
		 String off_auth = request.getParameter("off_auth1");
		 Date date = new Date();
		 
			Integer off_auth1 = 0;
			if (off_auth != "" && !off_auth.equals("")) {
				off_auth1 = Integer.parseInt(off_auth);
			}
			String jco_or_auth = request.getParameter("jco_or_auth1");
			Integer jco_or_auth1 = 0;
			if (jco_or_auth != "" && !jco_or_auth.equals("")) {
				jco_or_auth1 = Integer.parseInt(jco_or_auth);
			}
			String off_fam_auth = request.getParameter("off_fam_auth1");
			Integer off_fam_auth1 = 0;
			if (off_fam_auth != "" && !off_fam_auth.equals("")) {
				off_fam_auth1 = Integer.parseInt(off_fam_auth);
			}
			String jco_or_fam_auth = request.getParameter("jco_or_fam_auth1");
			Integer jco_or_fam_auth1 = 0;
			if (jco_or_fam_auth != "" && !jco_or_fam_auth.equals("")) {
				jco_or_fam_auth1 = Integer.parseInt(jco_or_fam_auth);
			}
			String others = request.getParameter("others1");
			Integer others1 = 0;
			if (others != "" && !others.equals("")) {
				others1 = Integer.parseInt(others);
			}
			String total_all = request.getParameter("total_all1");
			Integer total_all1 = 0;
			if (total_all != "" && !total_all.equals("")) {
				total_all1 = Integer.parseInt(total_all);
			}
			String laid_out = request.getParameter("laid_out1");
			Integer laid_out1 = 0;
			if (laid_out != "" && !laid_out.equals("")) {
				laid_out1 = Integer.parseInt(laid_out);
			}
			if( sus_no1 == null || sus_no1.equals("")){ 
	             model.put("msg", "Please Enter the SUS No");
	             return new ModelAndView("redirect:edit_mst_bed_autho");
	              } 
			 if(validation.sus_noLength(sus_no1) == false){
		        model.put("msg",validation.sus_noMSG);
		        return new ModelAndView("redirect:edit_mst_bed_autho");
		            }
		       if( command == null || command.equals("")){ 
				model.put("msg", "Command Name Should not be Null");
				return new ModelAndView("redirect:edit_mst_bed_autho");
		       }
		       if(Integer.parseInt(total_all) == 0) {	
					model.put("msg", "Total Bed Auth Should not be Null or Zero");
					return new ModelAndView("redirect:edit_mst_bed_autho");
				}
		       Integer t1 = (off_auth1 + jco_or_auth1 + off_fam_auth1 + jco_or_fam_auth1) ;
				
				if((t1) > Integer.parseInt(total_all)) {	
					model.put("msg", "Total Bed Auth Value should be less than or equal to the total of Officer,JCO/OR,Officer Family and JCO/OR Family Auth.");
					return new ModelAndView("redirect:edit_mst_bed_autho");
				}
		       if(Integer.parseInt(laid_out) < 0) {	
					model.put("msg", "Bed Laid Out should be greater than 0");
					return new ModelAndView("redirect:edit_mst_bed_autho");
				}
		     
		int id = Integer.parseInt(request.getParameter("id"));
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session1.beginTransaction();
			 try {
				 Long op= bAuth.checkExitstingbauth(sus_no1,rs.getId());
				 
				  rs.setOff_auth(off_auth1);
					rs.setJco_or_auth(jco_or_auth1);
					rs.setOff_fam_auth(off_fam_auth1);
					rs.setJco_or_fam_auth(jco_or_fam_auth1);
					rs.setOthers(others1);
					rs.setTotal_all(total_all1);
					rs.setLaid_out(laid_out1);
					rs.setModified_by(username);
					rs.setModified_on(date);
				 if (op == 0) {
					  model.put("msg", bAuth.updatebedauth(rs,username,id));
				 }
				 if (op > 0) 
	 					
	 				{
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
	             
			}
		return new ModelAndView("redirect:bed_authorization");
	}
}
