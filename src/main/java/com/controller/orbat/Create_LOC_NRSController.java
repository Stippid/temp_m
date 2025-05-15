package com.controller.orbat;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.orbat.Loc_and_NrsDAO;
import com.dao.orbat.Orbat_MasterDAO;
import com.dao.orbat.Orbat_MasterDAOImpl;
import com.models.T_Domain_Value;
import com.models.Tb_Miso_Orbat_Code;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class Create_LOC_NRSController {

		Orbat_MasterDAO masterDAO = new Orbat_MasterDAOImpl();	
		@Autowired
		Loc_and_NrsDAO locDAO;
		@Autowired
		private RoleBaseMenuDAO roledao;
		ValidationController validation = new ValidationController();
	
		@RequestMapping(value = "/admin/create_loc_nrs", method = RequestMethod.GET)
		public ModelAndView Create_loc_nrs(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("create_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			Mmap.put("getTerrainCode", getTerrainCode());
			Mmap.put("getprantList1", getprantList1());
			Mmap.put("getprantList2", getprantList2());
			Mmap.put("msg", msg);
			return new ModelAndView("create_loc_nrsTiles","createLocCMD", new Tb_Miso_Orbat_Code());
		}
		
		public List<T_Domain_Value> getprantList1() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid='TYPEOFLOCATION' order by codevalue");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<Tb_Miso_Orbat_Code> getprantList2() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Tb_Miso_Orbat_Code where code_type='Location' and status_record='1' order by code_value");
			@SuppressWarnings("unchecked")
			List<Tb_Miso_Orbat_Code> list = (List<Tb_Miso_Orbat_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getTerrainCode() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select label from T_Domain_Value where domainid='TERRAIN' order by codevalue");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/createLocAction",method = RequestMethod.POST)
		public ModelAndView createLocAction(@ModelAttribute("createLocCMD") Tb_Miso_Orbat_Code p1,HttpServletRequest request,ModelMap model,HttpSession session) {
			String  roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("create_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = session.getAttribute("username").toString();
			if (p1.getCode_value().equals("")) {
				model.put("msg", "Please Enter Code value");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			String code_value = request.getParameter("code_value").toUpperCase();
			if(validation.checkLocNameLength(code_value)  == false){
				model.put("msg",validation.Loc_NameMSG);
				return new ModelAndView("redirect:create_loc_nrs");
			}
			
			if (p1.getCode() == "") {
				model.put("msg", "Please Enter Code");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			String code = request.getParameter("code").toUpperCase();
			if(validation.LocCodeLength(code)  == false){
				model.put("msg",validation.loc_codeMSG);
				return new ModelAndView("redirect:create_loc_nrs");
			}
			String mod_desc = request.getParameter("mod_desc");
			String type_loc = request.getParameter("type_loc");
			String nrs = request.getParameter("nrs");
			if(mod_desc.equals("0"))
			{
				model.put("msg", "Please Select Modification");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			if(type_loc.equals("0"))
			{
				model.put("msg", "Please Select Type Of Location");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			if(validation.nrsLength(nrs)  == false){
				model.put("msg",validation.nrsMSG);
				return new ModelAndView("redirect:create_loc_nrs");
			}
			
			if(masterDAO.ifExistCodeValueLocation(code_value,0,code) != false ) {
				model.put("msg", "Location Name Already exists");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			if(masterDAO.ifExistCodeLocation(code) != false) {
				model.put("msg", "Location Code  Already exists");
				return new ModelAndView("redirect:create_loc_nrs");
			}
			String checkvalue= request.getParameter("checkvalue");
			if(checkvalue.equals("Yes")) {
				p1.setNrs_code(request.getParameter("nrs_codetxt").toUpperCase());				
			}
			else {
				p1.setNrs_code(request.getParameter("nrs_codesel").toUpperCase());	
			}	
			if(validation.nrs_codeLength(p1.getNrs_code())  == false){
				model.put("msg",validation.nrs_codeMSG);
				return new ModelAndView("redirect:create_loc_nrs");
			}
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			try {
				sessionHQL.beginTransaction();
				p1.setCode(code);
				p1.setCode_value(code_value);
				p1.setCreated_by(username);
				p1.setCreated_on(new Date());
				p1.setStatus_record("0");
				p1.setCode_type("Location");
				p1.setVersion_on(1);
				sessionHQL.save(p1);
				sessionHQL.getTransaction().commit();
				model.put("msg", "Location NRS Saved");
			}catch (Exception e) {
				sessionHQL.getTransaction().rollback();
				model.put("msg", "Location NRS not Saved");
				return new ModelAndView("redirect:create_loc_nrs");
			}finally {
				sessionHQL.close();
			}
			return new ModelAndView("redirect:create_loc_nrs");
		}
		
	
		
		
		@RequestMapping(value = "/admin/ApprovedLocArmUrl",method = RequestMethod.POST)
		public ModelAndView ApprovedLocArmUrl(@ModelAttribute("appid") int appid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
			
			String username = sessionA.getAttribute("username").toString();
			model.put("msg", masterDAO.setApprovedLOC_NRS(appid,username));	
			return new ModelAndView("redirect:search_loc_nrs");
		}
		
		@RequestMapping(value = "/admin/rejectLocArmUrl",method = RequestMethod.POST)
		public ModelAndView rejectLocArmUrl(@ModelAttribute("rejectid") int rejectid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("APP")) {
				return new ModelAndView("AccessTiles");
			}
			
			model.put("msg", masterDAO.setRejectLOC_NRS(rejectid));	
			return new ModelAndView("redirect:search_loc_nrs");
		}
		
		@RequestMapping(value = "/admin/deleteLocArmUrl",method = RequestMethod.POST)
		public ModelAndView deleteLocArmUrl(@ModelAttribute("deleteid") int deleteid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionA){
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			model.put("msg", masterDAO.setDeleteLOC_NRS(deleteid));	
			return new ModelAndView("redirect:search_loc_nrs");
		}
		

		@RequestMapping(value = "/admin/updateLocArmUrl",method = RequestMethod.POST)
		public ModelAndView updateLocArmUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "status_record0", required = false) String status_record,
				Authentication authentication,HttpSession sessionA){
			System.out.println(status_record+"status_record1======");
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			model.put("getTerrainCode", getTerrainCode());
			model.put("getprantList1", getprantList1());
			model.put("getprantList2", getprantList2());
			model.put("locnrsEditCMD", masterDAO.getTb_Miso_Orbat_CodeByid(updateid));
			model.put("status_record", status_record);
			model.put("msg", msg);
			return new ModelAndView("loc_nrsEditTiles");
		}
		
		
		
		@RequestMapping(value = "/admin/locnrsEditAction",method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView locnrsEditAction(@ModelAttribute("locnrsEditCMD") Tb_Miso_Orbat_Code updateid,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "status_record1", required = false) String status_record,
				HttpSession sessionA) {
			String roleType = sessionA.getAttribute("roleType").toString();
			if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
				return new ModelAndView("AccessTiles");
			}
			
			
			System.out.println("ststus:" + status_record);
			String username = sessionA.getAttribute("username").toString();
		//	String nrs_codesel= request.getParameter("nrs_codesel");
			if (updateid.getCode_value().equals("")) {
				model.put("msg", "Please Enter Code value");
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			if (updateid.getCode() == "") {
				model.put("msg", "Please Enter Code");
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			String code_value = request.getParameter("code_value").toUpperCase();
			if(validation.checkLocNameLength(code_value)  == false){
				model.put("msg",validation.Loc_NameMSG);
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			
			String code = request.getParameter("code").toUpperCase();
			if(masterDAO.ifExistCodeValueLocation(code_value,updateid.getId(),code) != false ) {
				model.put("msg", "Location Name Already exists");
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			
			
			if(validation.LocCodeLength(code)  == false){
				model.put("msg",validation.loc_codeMSG);
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			String checkvalue= request.getParameter("checkvalue");
			if(checkvalue.equals("Yes")) {
				updateid.setNrs_code(request.getParameter("nrs_codetxt").toUpperCase());				
			}
			else {
				updateid.setNrs_code(request.getParameter("nrs_codesel").toUpperCase());	
			}	
			if(validation.nrs_codeLength(updateid.getNrs_code())  == false){
				model.put("msg",validation.nrs_codeMSG);
				model.put("updateid", updateid.getId());
				return new ModelAndView("redirect:updateLocArmUrl");
			}
			
			masterDAO.UpdateLocAndNRS(updateid,username,status_record);		
			model.put("msg", "Data Updated Successfully");
			return new ModelAndView("redirect:search_loc_nrs");
		} 
		 
		
		
		@RequestMapping(value = "/admin/search_loc_nrs", method = RequestMethod.GET)
		public ModelAndView Search_loc_nrs(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) {
				msg = "";
			}
			
			Mmap.put("msg", msg);
			return new ModelAndView("search_loc_nrsTiles");
		}	
		
		@RequestMapping(value = "/admin/search_loc_nrs1", method = RequestMethod.POST)
		public ModelAndView Search_loc_nrs1(ModelMap Mmap,HttpSession sessionA,@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "code_value1", required = false) String code_value,
				@RequestParam(value = "code1", required = false) String code,
				@RequestParam(value = "status_record1", required = false) String status_record){
				
			String  roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("search_loc_nrs", roleid);	
			if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			if(validation.checkLocNameLength(code_value)  == false){
				Mmap.put("msg",validation.Loc_NameMSG);
				return new ModelAndView("redirect:search_loc_nrs");
			}
			
			String roleType = sessionA.getAttribute("roleType").toString();
			
			if(code_value != "") {
				Mmap.put("code_value1",code_value);
			}
			if(code != "") {
				Mmap.put("code1",code);
			}
			if(status_record != "") {
				Mmap.put("status_record1", status_record);
			}
			ArrayList<ArrayList<String>> list =  locDAO.getLoc_And_NrsList(code_value,code,status_record,roleType);
			if(list.size() == 0) {
				Mmap.put("msg", "Data Not Available");
			}else {
				Mmap.put("list", list);
			}
			return new ModelAndView("search_loc_nrsTiles");
		}	
}