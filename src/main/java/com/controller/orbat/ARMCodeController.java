package com.controller.orbat;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.FlushMode;
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

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.Orbat_MasterDAO;
import com.dao.orbat.Orbat_MasterDAOImpl;
import com.models.Tb_Miso_Orabt_Arm_Code;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ARMCodeController {

	Orbat_MasterDAO masterDAO = new Orbat_MasterDAOImpl();
	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController validation = new ValidationController();
	
	// CREATE ARM Code
	@RequestMapping(value = "/admin/arm_code", method = RequestMethod.GET)
	public ModelAndView Arm_code(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("arm_code", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("arm_codeTiles");
	}
	// CREATE ARM Code ACTION	
	@RequestMapping(value = "/createArmAction", method = RequestMethod.POST)
	public ModelAndView createArmAction(@ModelAttribute("createArmCMD") Tb_Miso_Orabt_Arm_Code ac,HttpServletRequest request,BindingResult bindingResult,ModelMap model,HttpSession session) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("arm_code", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String username = session.getAttribute("username").toString();
		if(bindingResult.hasErrors()) {
			model.put("msg", "Please Enter Proper Data");
			return new ModelAndView("arm_codeTiles");
		}else {
			if(ac.getArm_code().equals("")){
				model.put("msg","Please Enter Arm Code");
				return new ModelAndView("redirect:arm_code");
			}
			String arm_code = String.format("%04d", Integer.parseInt(ac.getArm_code()));
			if(validation.checkArmCodeLength(arm_code)  == false){
		 		model.put("msg",validation.arm_codeMSG);
				return new ModelAndView("redirect:arm_code");
			}
			
			if(ac.getArm_desc().equals("")){
		 		model.put("msg","Please Enter Arm Name");
				return new ModelAndView("redirect:arm_code");
			}
			if(validation.checkArmDescLength(ac.getArm_desc()) == false){
		 		model.put("msg",validation.arm_descMSG);
				return new ModelAndView("redirect:arm_code");
			}
			
			if(masterDAO.ifExistArmCode_name(ac.getArm_desc(),ac.getArm_code(),0) != false) {
				model.put("msg", "Arm Name or Arm Code Already Exists");
				return new ModelAndView("redirect:arm_code");
			}
			
			
			Session sessionHQL = null;
	    	Transaction tx = null;
	    	try{
	    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    		tx = sessionHQL.beginTransaction();
				ac.setArm_code(arm_code);
				ac.setCreated_by(username);
				ac.setCreated_on(new Date());
				ac.setStatus("0");
				ac.setVersion_no(1);
				ac.setArm_type_flag(0);
				
				sessionHQL.save(ac);
				sessionHQL.getTransaction().commit();
				model.put("msg", "Data Saved Successfully");
				return new ModelAndView("redirect:arm_code");
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
	   }
	}

	// SEARCH ARM Code 
	@RequestMapping(value = "/admin/search_arm_code", method = RequestMethod.GET)
	public ModelAndView Search_arm_code(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_arm_code", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_arm_codeTiles");
	}
	
	// SEARCH ARM Code
	@RequestMapping(value = "/admin/search_arm_code1", method = RequestMethod.POST)
	public ModelAndView Search_arm_code1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "arm_desc1", required = false) String arm_desc,
			@RequestParam(value = "arm_code1", required = false) String arm_code,
			@RequestParam(value = "status1", required = false) String status , ModelMap model) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_arm_code", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(validation.checkArmDescLength(arm_desc) == false){
			model.put("msg",validation.arm_descMSG);
			return new ModelAndView("redirect:search_arm_code");
		}
		
		String roleType = session.getAttribute("roleType").toString();
		
		Session sessionARM= HibernateUtil.getSessionFactory().openSession();
		sessionARM.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = sessionARM.beginTransaction();
		Query q = null;
		String qry="";
		if(arm_desc != "") {
			qry += " Upper(arm_desc) LIKE :arm_desc";
			Mmap.put("arm_desc1", arm_desc);
		}
		if(arm_code != "") {
			if(qry == "") {
				qry += " Upper(arm_code) LIKE :arm_code";
				
			}else {
				qry += " and Upper(arm_code) LIKE :arm_code";
			}
			Mmap.put("arm_code1", arm_code);
		}
		if(status != "") {
			if(qry == "") {
				qry += " status =:status";
			}else {
				qry += " and status =:status";
			}
			Mmap.put("status1", status);
		}
		
		if(qry == "") {
			q = sessionARM.createQuery(" from Tb_Miso_Orabt_Arm_Code");
		}else {
			q = sessionARM.createQuery(" from Tb_Miso_Orabt_Arm_Code where "+ qry + " Order by arm_code");
		}
		
		
		if(arm_desc != "") {
			q.setParameter("arm_desc", "%"+ arm_desc.toUpperCase()+"%");
		}
		if(arm_code != "") {
			q.setParameter("arm_code",  arm_code.toUpperCase()+"%");
		}
		if(status != "") {
			q.setParameter("status", status);
		}
		
		
		
		@SuppressWarnings("unchecked")
		List<Tb_Miso_Orabt_Arm_Code> list = (List<Tb_Miso_Orabt_Arm_Code>) q.list();
		tx.commit();
		sessionARM.close();
		
		for(int i=0;i<list.size();i++) {
			String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this Arm ?') ){Approved("+list.get(i).getId()+")}else{ return false;}\"";
			String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
			
			String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Arm ?') ){Reject("+list.get(i).getId()+")}else{ return false;}\"";
			String rejectButton ="<i class='action_icons action_reject' "+Reject+" title='Reject Data'></i>";
			
			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Arm ?') ){Delete1("+list.get(i).getId()+")}else{ return false;}\"";
			String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
			
			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Arm ?') ){Update("+list.get(i).getId()+")}else{ return false;}\"";
			String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
			
			String f = "";
			if(status.equals("0")){
				if(roleType.equals("ALL")) {
					f += appButton;
					f += rejectButton;
					f += deleteButton;
					f += updateButton;
				}
				if(roleType.equals("APP")) {
					f += appButton;
					f += rejectButton;
				}
				if(roleType.equals("DEO")) {
					f += deleteButton;
					f += updateButton;
				}
			}else if(status.equals("1")){
				f += "Approved";
			}else if(status.equals("2")){
				if(roleType.equals("DEO") || roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}
			}
			list.get(i).setStatus(f);
		}
		if(list.size() == 0) {
			Mmap.put("msg", "Data Not Available");
		}else {
			Mmap.put("list", list);
		}
		return new ModelAndView("search_arm_codeTiles");
	}
	
	// APPROVE ARM Code
	@RequestMapping(value = "/admin/ApprovedArmUrl",method = RequestMethod.POST)
	public ModelAndView ApprovedArmUrl(@ModelAttribute("appid") int appid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", masterDAO.setApprovedARM(appid));	
		return new ModelAndView("redirect:search_arm_code");
	}
	
	// REJECT ARM Code
	@RequestMapping(value = "/admin/rejectArmUrl",method = RequestMethod.POST)
	public ModelAndView rejectArmUrl(@ModelAttribute("rejectid") int rejectid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", masterDAO.setRejectARM(rejectid));	
		return new ModelAndView("redirect:search_arm_code");
	}
		
	// DELETE ARM Code
	@RequestMapping(value = "/admin/deleteArmUrl",method = RequestMethod.POST)
	public ModelAndView deleteArmUrl(@ModelAttribute("deleteid") int deleteid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", masterDAO.setDeleteARM(deleteid));	
		return new ModelAndView("redirect:search_arm_code");
	}
	
	// EDIT ARM Code
	@RequestMapping(value = "/admin/updateArmUrl")
	public ModelAndView Editarm_code(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA,HttpServletRequest request){
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		model.put("EditArmCMD", masterDAO.getTb_Miso_Orabt_Arm_CodeByid(updateid));
		model.put("msg", msg);	
		return new ModelAndView("Editarm_codeTiles");
	}
		
	// UPDATE ARM Code
	@RequestMapping(value = "/admin/updateArmAction",method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Tb_Miso_Orabt_Arm_Code(@ModelAttribute("EditArmCMD") Tb_Miso_Orabt_Arm_Code arm,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		String username = sessionA.getAttribute("username").toString();
		if(arm.getArm_code().equals("")){
			model.put("msg","Please Enter Arm Code");
			model.put("updateid",arm.getId());
			return new ModelAndView("redirect:updateArmUrl");
		}
		String arm_code = String.format("%04d", Integer.parseInt(arm.getArm_code()));
		if(validation.checkArmCodeLength(arm_code)  == false){
			model.put("msg",validation.arm_codeMSG);
	 		model.put("updateid",arm.getId());
			return new ModelAndView("redirect:updateArmUrl");
		}
		if(arm.getArm_desc().equals("")){
	 		model.put("msg","Please Enter Arm Name");
	 		model.put("updateid",arm.getId());
			return new ModelAndView("redirect:updateArmUrl");
		}
		if(validation.checkArmDescLength(arm.getArm_desc()) == false){
			model.put("msg",validation.arm_descMSG);
	 		model.put("updateid",arm.getId());
			return new ModelAndView("redirect:updateArmUrl");
		}
		if(masterDAO.ifExistArmCode_name(arm.getArm_desc(),arm_code,arm.getId()) != false) {
			model.put("updateid",arm.getId());
			model.put("msg", "Arm Name Already Exists");
			return new ModelAndView("redirect:updateArmUrl");
		}
		model.put("msg", masterDAO.Updatearmcode(arm,username));
		return new ModelAndView("redirect:search_arm_code");
	}
}