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
import com.models.Tb_Miso_Orbat_Code;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ParentARMController {

	Orbat_MasterDAO masterDAO = new Orbat_MasterDAOImpl();	
	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController validation = new ValidationController();
	
	//private AllMethodsControllerOrbat allOrbat = new AllMethodsControllerOrbat();
	
	@RequestMapping(value = "/admin/create_parent_arm", method = RequestMethod.GET)
	public ModelAndView Create_parent_arm(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("create_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		
		/*msg = allOrbat.EncMSGValidation(msg,sessionA);
		if(msg.equals("Invalid")) {
			sessionA.invalidate();
			return new ModelAndView("redirect:/login");
		}else {
			Mmap.put("msg", msg);
		}*/
		return new ModelAndView("create_parent_armTiles","parentsArmCMD", new Tb_Miso_Orbat_Code());
	}
	
	@RequestMapping(value = "/parentsArmAction",method = RequestMethod.POST)
	public ModelAndView parentsArmAction(@ModelAttribute("parentsArmCMD") Tb_Miso_Orbat_Code p,HttpServletRequest request,ModelMap model,HttpSession sessionA) {
		
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("create_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = sessionA.getAttribute("username").toString();
		String code_value = request.getParameter("code_value");
		if (p.getCode_value().equals("")) {
			model.put("msg", "Please Enter Code value");
			return new ModelAndView("redirect:create_parent_arm");
		}
		if(validation.checkParentArmNameLength(code_value)  == false){
			model.put("msg",validation.parent_arm_NameMSG);
			return new ModelAndView("redirect:create_parent_arm");
		}
		if (p.getCode().equals("")) {
			model.put("msg",  "Please Enter Code");
			return new ModelAndView("redirect:create_parent_arm");
		}

		if(validation.checkParentArmCodeLength(p.getCode())  == false){
	 		model.put("msg", validation.parent_arm_codeMSG);
	 		return new ModelAndView("redirect:create_parent_arm");
		}
		String parent_arm = "Parent Arm";
		if(masterDAO.ifExistCodeValue(code_value,parent_arm) != false) {
			model.put("msg","Data Already exists");
			return new ModelAndView("redirect:create_parent_arm");
		}
		String code = request.getParameter("code");
		if(masterDAO.ifExistCode(code) != false) {
			model.put("msg", "Data Already exists");
			return new ModelAndView("redirect:create_parent_arm");
		}
		
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
    		
    		p.setCreated_by(username);
			p.setCreated_on(new Date());
			p.setCode_type("Parent Arm");
			p.setStatus_record("0");
			p.setVersion_on(1);
			sessionHQL.save(p);
			tx.commit();
    		model.put("msg", "Data Saved Successfully");
    	}catch(RuntimeException e){
    		try{
    			tx.rollback();
    			model.put("msg", "roll back transaction");
    		}catch(RuntimeException rbe){
    			model.put("msg", "Couldn’t roll back transaction" + rbe);
    		}
    		throw e;
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}
		return new ModelAndView("redirect:create_parent_arm");
	}
	
	@RequestMapping(value = "/admin/search_parent_arm", method = RequestMethod.GET)
	public ModelAndView Search_parent_arm(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_parent_armTiles");
	}
	
	@RequestMapping(value = "/admin/search_parent_arm1", method = RequestMethod.POST)
	public ModelAndView Search_parent_arm1(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "code_value1", required = false) String code_value,
			@RequestParam(value = "code1", required = false) String code,
			@RequestParam(value = "status_record1", required = false) String status_record) {
			String roleType = sessionA.getAttribute("roleType").toString();
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(validation.checkParentArmNameLength(code_value)  == false){
				Mmap.put("msg",validation.parent_arm_NameMSG);
				return new ModelAndView("redirect:search_parent_arm");
			}
		
			Session sessionPA= HibernateUtilNA.getSessionFactory().openSession();
			sessionPA.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionPA.beginTransaction();
			Query q = null;
			String qry=" code_type=:code_type ";
			
			if(qry == "") {
				q = sessionPA.createQuery("from Tb_Miso_Orbat_Code");
			}else {
				if(code_value != "") {
					qry += " and upper(code_value) like :code_value ";
					Mmap.put("code_value1", code_value);
				}
				if(code != "") {
					qry += " and upper(code) like :code ";
					Mmap.put("code1", code);
				}
				if(status_record != "") {
					qry += " and status_record=:status_record ";
					Mmap.put("status_record1", status_record);
				}
				q = sessionPA.createQuery("from Tb_Miso_Orbat_Code where "+qry+" order by code,code_value");
				q.setString("code_type","Parent Arm");
				q.setString("status_record",status_record);
				if(code_value != ""){
					q.setString("code_value", "%"+code_value.toUpperCase()+"%");
				}
				if(code != ""){
					q.setString("code", code.toUpperCase() +"%");
				}
				
			}
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			sessionPA.close();
			
		for(int i=0;i<list.size();i++) {
			String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this Parent Arm ?') ){Approved("+list.get(i).getId()+")}else{ return false;}\"";
			String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
			
			String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Parent Arm ?') ){Reject("+list.get(i).getId()+")}else{ return false;}\"";
			String rejectButton ="<i class='action_icons action_reject' "+Reject+" title='Reject Data'></i>";
			
			String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Parent Arm ?') ){Delete1("+list.get(i).getId()+")}else{ return false;}\"";
			String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
			
			String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Parent Arm ?') ){Update("+list.get(i).getId()+")}else{ return false;}\"";
			String updateButton ="<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
			
			String f = "";
			if(status_record.equals("0")){
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
			}else if(status_record.equals("1")){
				f += "Approved";
			}else if(status_record.equals("2")){
				if(roleType.equals("DEO") || roleType.equals("ALL")) {
					f += deleteButton;
					f += updateButton;
				}
			}
			list.get(i).setStatus_record(f);
		}
		
		if(list.size() == 0) {
			Mmap.put("msg", "Data Not Available");
		}else {
			Mmap.put("list", list);
		}
		return new ModelAndView("search_parent_armTiles");
	}
	
	@RequestMapping(value = "/admin/ApprovedPArmUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedArmUrl(@ModelAttribute("appid") int appid,ModelMap model,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		String username = sessionA.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", masterDAO.setApprovedPARM(appid,username));	
		return new ModelAndView("search_parent_armTiles");
	}
	
	@RequestMapping(value = "/admin/rejectPArmUrl", method = RequestMethod.POST)
	public ModelAndView rejectArmUrl(@ModelAttribute("rejectid") int rejectid,ModelMap model,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
	//	String username = sessionA.getAttribute("username").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("APP")) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", masterDAO.setRejectPARM(rejectid));
		return new ModelAndView("search_parent_armTiles");
	}
	
	@RequestMapping(value = "/admin/deletePArmUrl", method = RequestMethod.POST)
	public ModelAndView deleteArmUrl(@ModelAttribute("deleteid") int deleteid,ModelMap model,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}

		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		
		model.put("msg", masterDAO.setDeletePARM(deleteid));
		return new ModelAndView("search_parent_armTiles");
	}

	@RequestMapping(value = "/admin/updatePArmUrl", method = RequestMethod.POST)
	public ModelAndView updatePArmUrl(@ModelAttribute("updateid") int updateid,ModelMap model,Authentication authentication,HttpSession sessionA){
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}			
		
		model.put("parentsArmEditCMD", masterDAO.getTb_Miso_Orbat_CodeByid(updateid));	
		return new ModelAndView("create_parent_armEditTiles");
	}
		
	@RequestMapping(value = "/parentsArmEditAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Tb_Miso_Orbat_Code(@ModelAttribute("parentsArmEditCMD") Tb_Miso_Orbat_Code updateid,HttpServletRequest request,ModelMap model,HttpSession sessionA) {
		String  roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_parent_arm", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		String roleType = sessionA.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}	
		
		if (updateid.getCode_value().equals("")) {
			model.put("msg", "Please Enter Code value");
			return new ModelAndView("redirect:search_parent_arm");
		}
		if(validation.checkParentArmNameLength(updateid.getCode_value())  == false){
			model.put("msg",validation.parent_arm_NameMSG);
			return new ModelAndView("redirect:search_parent_arm");
		}
		if (updateid.getCode() == "") {
			model.put("msg", "Please Enter Code");
			return new ModelAndView("redirect:search_parent_arm");
		}
		if (updateid.getCode().length() != 2) {
			model.put("msg", "Please Enter 2 Digit Code");
			return new ModelAndView("redirect:search_parent_arm");
		}
		if(validation.checkParentArmCodeLength(updateid.getCode())  == false){
			model.put("msg",validation.parent_arm_codeMSG);
	 		return new ModelAndView("redirect:search_parent_arm");
		}
		masterDAO.UpdateParentARM(updateid);		
		model.put("msg", "Data Updated Successfully");
		return new ModelAndView("redirect:search_parent_arm");
	} 
}