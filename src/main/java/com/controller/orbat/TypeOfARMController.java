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
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.Orbat_MasterDAO;
import com.models.Tb_Miso_Orbat_Code;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class TypeOfARMController {

		@Autowired
		Orbat_MasterDAO masterDAO;
		@Autowired
		private RoleBaseMenuDAO roledao;
		HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
		AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
		
		ValidationController valid = new ValidationController();
		

		@RequestMapping(value = "/admin/type_arm", method = RequestMethod.GET)
		public ModelAndView Type_arm(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
			Mmap.put("msg", msg);
			return new ModelAndView("type_armTiles","type_armCMD", new Tb_Miso_Orbat_Code());
		}

		@RequestMapping(value = "/admin/type_arm1", method = RequestMethod.POST)
		public ModelAndView Type_arm1(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg
				,@RequestParam(value = "parnt_code_id1", required = false) String parnt_code_id) {
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			Mmap.put("parnt_code_id", parnt_code_id);
			Mmap.put("code", masterDAO.getmaxprantCode(parnt_code_id).get(0));
			Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
			Mmap.put("msg", msg);
			return new ModelAndView("type_armTiles","type_armCMD", new Tb_Miso_Orbat_Code());
		}
		
		
		
		@RequestMapping(value = "/type_armAction",method = RequestMethod.POST)
		public ModelAndView type_armAction(@ModelAttribute("parentsArmCMD") Tb_Miso_Orbat_Code p,HttpServletRequest request,ModelMap model,HttpSession sessionA) {
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = sessionA.getAttribute("username").toString();
			int code_length = p.getCode().length();
			String parent_arm_code= request.getParameter("parent_arm");
			String code_value = request.getParameter("code_value");
			String code;
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			try {
				if (parent_arm_code.equals("")) {
					model.put("msg", "Please Select parent Arm");
					return new ModelAndView("redirect:type_arm");
				}
				if (p.getCode_value().equals("")) {
					model.put("msg", "Please Enter Code value");
					return new ModelAndView("redirect:type_arm");
				}
				if(valid.checkTypesOfArmNameLength(p.getCode_value())  == false){
					model.put("msg",valid.TypesOf_arm_NameMSG);
					return new ModelAndView("redirect:type_arm");
				}
				if (p.getCode() == "") {
					model.put("msg", "Please Enter Code");
					return new ModelAndView("redirect:type_arm");
				}
				if(code_length == 1 )
				{
					 code=parent_arm_code+"0"+p.getCode();
				}
				else {
					code=parent_arm_code+p.getCode();
				}
				
				if(valid.checkTypesOfArmCodeLength(code) == false) {
					model.put("msg",valid.TypesOf_arm_codeMSG);
					return new ModelAndView("redirect:type_arm");
				}
				String type_of_arm = "Type of Arm";
				if(masterDAO.ifExistCodeValue(code_value,type_of_arm) != false) {
					model.put("msg", "Code Value '"+code_value+"' Already Exists");
					return new ModelAndView("redirect:type_arm");
				}
				if(masterDAO.ifExistCode(code) != false) {
					model.put("msg", "Code Already Exists");
					return new ModelAndView("redirect:type_arm");
				}
				
				p.setCreated_by(username);
				p.setCreated_on(new Date());
				p.setCode(code);
				p.setCode_type("Type of Arm");
				p.setStatus_record("0");
				
				sessionHQL.beginTransaction();
				sessionHQL.save(p);
				sessionHQL.getTransaction().commit();
				
				model.put("msg", "Data Saved Successfully");
			}catch (Exception e) {
				model.put("msg", "Data not Saved");
				sessionHQL.getTransaction().rollback();
			}finally {
				sessionHQL.close();
			}
			return new ModelAndView("redirect:type_arm");
		}

		public @ResponseBody List<String> getmaxprantCode(String p_code) {			
			return masterDAO.getmaxprantCode(p_code);
		}
		
		public Boolean gettype_arm_code_valueList(@RequestParam String code_value) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query query= session.createQuery("from Tb_Miso_Orbat_Code where code_type='Type of Arm' and code_value=:code_value ");
			query.setParameter("code_value", code_value);
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> users = (List<Tb_Miso_Orbat_Code>) query.list();
			tx.commit();
			session.close();
			if(users.size()>0)
			{
				return true;
			}else {
				return false;
			}
		
		}
		
		@RequestMapping(value = "/admin/search_type_arm", method = RequestMethod.GET)
		public ModelAndView Search_type_arm(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
			Mmap.put("msg", msg);
			return new ModelAndView("search_type_armTiles");
		}
		
		@RequestMapping(value = "/admin/search_type_arm1", method = RequestMethod.POST)
		public ModelAndView Search_type_arm1(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "code_value1", required = false) String code_value,
				@RequestParam(value = "code1", required = false) String code,
				@RequestParam(value = "status_record1", required = false) String status_record,
				@RequestParam(value = "parent_arm1", required = false) String parent_arm) {
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			if(valid.checkTypesOfArmNameLength(code_value)  == false){
				Mmap.put("msg",valid.TypesOf_arm_NameMSG);
				return new ModelAndView("redirect:search_type_arm");
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			Session sessionTypeArm= HibernateUtilNA.getSessionFactory().openSession();
			sessionTypeArm.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = sessionTypeArm.beginTransaction();
			Query q = null;
			String qry=" code_type=:code_type ";
			
			if(!parent_arm.equals("0")) {
				qry +=  " and code like :parent_arm ";
				Mmap.put("parent_arm1", parent_arm);
			}
			else {
				qry +="";
				Mmap.put("parent_arm1", "0");
			}
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
			qry += " order by code,code_value ";
			
			q = sessionTypeArm.createQuery("from Tb_Miso_Orbat_Code where "+ qry);
			q.setString("code_type","Type of Arm");
			q.setString("status_record",status_record);
			if(!parent_arm.equals("0")) {
				q.setString("parent_arm", parent_arm.toUpperCase()+"%");
			}
			if(code_value != ""){
				q.setString("code_value", "%"+code_value.toUpperCase()+"%");
			}
			if(code != ""){
				q.setString("code", code.toUpperCase() +"%");
			}
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			sessionTypeArm.close();
			
			for(int i=0;i<list.size();i++) {
				String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this Type of Arm ?') ){Approved("+list.get(i).getId()+")}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' "+Approved+" title='Approve Data'></i>";
				
				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Type of Arm ?') ){Reject("+list.get(i).getId()+")}else{ return false;}\"";
				String rejectButton ="<i class='action_icons action_reject' "+Reject+" title='Reject Data'></i>";
				
				String Delete1 = "onclick=\"  if (confirm('Are You Sure you want to Delete this Type of Arm ?') ){Delete1("+list.get(i).getId()+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
				
				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update this Type of Arm ?') ){Update("+list.get(i).getId()+")}else{ return false;}\"";
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
			
			Mmap.put("getPrantArmList", all.getPrantArmList(sessionA));
			
			if(list.size() == 0) {
				Mmap.put("msg", "Data Not Available");
			}else {
				Mmap.put("msg", msg);
				Mmap.put("list", list);
			}
			return new ModelAndView("search_type_armTiles");
		}
		
		@RequestMapping(value = "/admin/ApprovedTypeOfArmUrl",method = RequestMethod.POST)
		public ModelAndView ApprovedArmUrl(@ModelAttribute("appid") int appid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = sessionA.getAttribute("username").toString();
			model.put("msg", masterDAO.setApprovedTypeOfARM(appid,username));	
			return new ModelAndView("redirect:search_type_arm");
		}
		
		@RequestMapping(value = "/admin/rejectTypeOfArmUrl",method = RequestMethod.POST)
		public ModelAndView rejectArmUrl(@ModelAttribute("rejectid") int rejectid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
			
			model.put("msg", masterDAO.setRejectTypeOfARM(rejectid));	
			return new ModelAndView("redirect:search_type_arm");
		}
		
		@RequestMapping(value = "/admin/deleteTypeOfArmUrl",method = RequestMethod.POST)
		public ModelAndView deleteArmUrl(@ModelAttribute("deleteid") int deleteid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			model.put("msg", masterDAO.setDeleteTypeOfARM(deleteid));	
			return new ModelAndView("redirect:search_type_arm");
		}
		
		@RequestMapping(value = "/admin/updateTypeOfArmUrl",method = RequestMethod.POST)
		public ModelAndView updateTypeOfArmUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			Tb_Miso_Orbat_Code codeList =  masterDAO.getTb_Miso_Orbat_CodeByid(updateid);
			model.put("Edittype_armCMD",codeList);
			model.put("code", codeList.getCode());
			model.put("code_value", codeList.getCode_value());
			
			String parnt_code_id = String.valueOf(codeList.getCode().charAt(0)) + String.valueOf(codeList.getCode().charAt(1));
			model.put("parnt_code_id", parnt_code_id);
			
			String code1 = String.valueOf(codeList.getCode().charAt(2)) + String.valueOf(codeList.getCode().charAt(3));
			model.put("code1", code1);
			
			model.put("msg",msg);
			return new ModelAndView("Edittype_armTiles");
		}
		
		@RequestMapping(value = "/Edittype_armAction",method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView Tb_Miso_Orbat_Code(@ModelAttribute("Edittype_armCMD") Tb_Miso_Orbat_Code updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,HttpSession sessionA) {
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_type_arm", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = sessionA.getAttribute("username").toString();
			int code_length = updateid.getCode().length();
			String parent_arm_code= request.getParameter("parent_arm");
			String code_value = request.getParameter("code_value");
			String code;
			
			if (updateid.getCode_value().equals("")) {
				model.put("msg", "Please Enter Code value");
				return new ModelAndView("redirect:search_type_arm");
			}
			if(valid.checkTypesOfArmNameLength(updateid.getCode_value())  == false){
				model.put("msg",valid.TypesOf_arm_NameMSG);
				return new ModelAndView("redirect:search_type_arm");
			}
			if (updateid.getCode() == "") {
				model.put("msg", "Please Enter Code");
				return new ModelAndView("redirect:search_type_arm");
			}
			if(code_length == 1 )
			{
				 code=parent_arm_code+"0"+updateid.getCode();
			}
			else {
				code=parent_arm_code+updateid.getCode();
			}
			if(gettype_arm_code_valueList(code_value) != false) {
				model.put("msg", "Type of Arm Already Exists");
				return new ModelAndView("redirect:search_type_arm");
			}
		
			updateid.setModified_by(username);
			updateid.setModified_on(new Date());
			updateid.setCode(code);
			updateid.setCode_type("Type of Arm");
		
			model.put("msg", masterDAO.setUpdateTypeOfARM(updateid));
			return new ModelAndView("redirect:search_type_arm");
		}
}