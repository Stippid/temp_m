package com.controller.avation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.avation.ActSlotDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.TB_AVIATION_ACT_MAIN_MASTER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Common_Act_MainController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private ActSlotDAO act_dao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	Common_Act_slotController_avation M = new Common_Act_slotController_avation();
	
	ValidationController validation = new ValidationController();
	
	@RequestMapping(value="/actmain")
	public ModelAndView actmain(ModelMap model,HttpServletRequest request,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg){	
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
//		String  roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("actmain", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		model.put("getAviationGroupNamelist", M.getAviationGroupNamelist(session));
		model.put("getLine_DteList",roledao.getLine_DteListsus());
		model.put("msg",msg);
		return new ModelAndView("actmainTiles","aviation_main_masterCmd", new TB_AVIATION_ACT_MAIN_MASTER());
	}
	
	//MITESH (28-11-24)
	@RequestMapping(value="/actmainRPAS")
	public ModelAndView actmainRPAS(ModelMap model,HttpServletRequest request,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg){	
		
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		
//		String  roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("actmain", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		model.put("getAviationGroupRPASNamelist", M.getAviationGroupRPASNamelist(session));
		model.put("getLine_DteList",roledao.getLine_DteListsus());
		model.put("msg",msg);
		return new ModelAndView("actmainRPASTiles","aviation_main_masterCmd", new TB_AVIATION_ACT_MAIN_MASTER());
	}
	
	@RequestMapping(value = "/actMainAction", method = RequestMethod.POST)
	public ModelAndView actMainAction(@ModelAttribute("aviation_main_masterCmd") TB_AVIATION_ACT_MAIN_MASTER actmain,HttpServletRequest request,ModelMap model,HttpSession session) {	
//		String  roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("actmain", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}	
		String sus_no = request.getParameter("sus_no").toString().trim();
		String username = session.getAttribute("username").toString().trim();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
		String act = request.getParameter("act_main_id").trim();
		String act_nomen = request.getParameter("act_nomen").trim();
		String abc_code=request.getParameter("abc_code").trim(); 	
		String type_of_aircraft = request.getParameter("type_of_aircraft").trim();
		
		actmain.setCreated_by(username);
		actmain.setCreated_on(date);
		if(request.getParameter("act_slot").equals("0"))
		{
			model.put("msg", "Please Select ABC Nomenclature.");
			return new ModelAndView("redirect:actmain");
		}
		else if(actmain.getAct_nomen().equals(""))
		{
			model.put("msg", "Please Enter ACT Nomenclature.");
			return new ModelAndView("redirect:actmain");
		}
		else if(validation.act_nomenLength(actmain.getAct_nomen()) == false){
	 		model.put("msg",validation.act_nomenMSG);
			return new ModelAndView("redirect:actmain");
		}
		else if(actmain.getAct_main_id().equals(""))
		{
			model.put("msg", "Please Enter ACT Main.");
			return new ModelAndView("redirect:actmain");
		}
		else if(validation.act_main_idLength(actmain.getAct_main_id()) == false){
	 		model.put("msg",validation.act_main_idMSG);
			return new ModelAndView("redirect:actmain");
		}
		else
		{
			Session session1=HibernateUtil.getSessionFactory().openSession();
			try
			{
				String main_id = actmain.getAct_main_id();
				String slot = request.getParameter("act_slot").toString().trim();
				String[] array = slot.split(",");
				int from =Integer.parseInt(array[0]);
				int to = Integer.parseInt(array[1]);
				if(from > Integer.parseInt(main_id) || to < Integer.parseInt(main_id))
				{
					model.put("msg", "Please enter ACT Main Id within " + from +" - " + to + ".");
					return new ModelAndView("redirect:actmain");
				}
				if(act_dao.checkIfExistMainIDAviation(actmain.getAct_main_id()) != false) {
					
					model.put("msg", "ACT Main Id Already exists");
					return new ModelAndView("redirect:actmain");
				}
				session1.beginTransaction();
				actmain.setSus_no(sus_no);
				actmain.setAbc_code(abc_code);
				actmain.setAct_nomen(act_nomen);
				actmain.setType_of_aircraft(type_of_aircraft);
				session1.save(actmain);
				session1.getTransaction().commit();
				model.put("msg", "ACT Successfully Created.");
				return new ModelAndView("redirect:actmain");
			}
			catch(Exception e)
			{
				session1.getTransaction().rollback();
				return null;
			}
			finally
			{
				session1.close();
			}
		}
	}
	
	@RequestMapping(value = "/actMainActionRPAS", method = RequestMethod.POST)
	public ModelAndView actMainActionRPAS(@ModelAttribute("aviation_main_masterCmd") TB_AVIATION_ACT_MAIN_MASTER actmain,HttpServletRequest request,ModelMap model,HttpSession session) {	
//		String  roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("actmainRPAS", roleid);	
//		if(val == false) {
//			return new ModelAndView("AccessTiles");
//		}	
		String sus_no = request.getParameter("sus_no").toString().trim();
		String username = session.getAttribute("username").toString().trim();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); 
		String act = request.getParameter("act_main_id").trim();
		String act_nomen = request.getParameter("act_nomen").trim();
		String abc_code=request.getParameter("abc_code").trim(); 	
		String type_of_aircraft = request.getParameter("type_of_aircraft").trim();
		
		actmain.setCreated_by(username);
		actmain.setCreated_on(date);
		if(request.getParameter("act_slot").equals("0"))
		{
			model.put("msg", "Please Select ABC Nomenclature.");
			return new ModelAndView("redirect:actmainRPAS");
		}
		else if(actmain.getAct_nomen().equals(""))
		{
			model.put("msg", "Please Enter ACT Nomenclature.");
			return new ModelAndView("redirect:actmainRPAS");
		}
		else if(validation.act_nomenLength(actmain.getAct_nomen()) == false){
	 		model.put("msg",validation.act_nomenMSG);
			return new ModelAndView("redirect:actmainRPAS");
		}
		else if(actmain.getAct_main_id().equals(""))
		{
			model.put("msg", "Please Enter ACT Main.");
			return new ModelAndView("redirect:actmainRPAS");
		}
		else if(validation.act_main_idLength(actmain.getAct_main_id()) == false){
	 		model.put("msg",validation.act_main_idMSG);
			return new ModelAndView("redirect:actmainRPAS");
		}
		else
		{
			Session session1=HibernateUtil.getSessionFactory().openSession();
			try
			{
				String main_id = actmain.getAct_main_id();
				String slot = request.getParameter("act_slot").toString().trim();
				String[] array = slot.split(",");
				int from =Integer.parseInt(array[0]);
				int to = Integer.parseInt(array[1]);
				if(from > Integer.parseInt(main_id) || to < Integer.parseInt(main_id))
				{
					model.put("msg", "Please enter ACT Main Id within " + from +" - " + to + ".");
					return new ModelAndView("redirect:actmainRPAS");
				}
				if(act_dao.checkIfExistMainIDAviation(actmain.getAct_main_id()) != false) {
					
					model.put("msg", "ACT Main Id Already exists");
					return new ModelAndView("redirect:actmainRPAS");
				}
				session1.beginTransaction();
				actmain.setSus_no(sus_no);
				actmain.setAbc_code(abc_code);
				actmain.setAct_nomen(act_nomen);
				actmain.setType_of_aircraft(type_of_aircraft);
				session1.save(actmain);
				session1.getTransaction().commit();
				model.put("msg", "ACT Successfully Created.");
				return new ModelAndView("redirect:actmainRPAS");
			}
			catch(Exception e)
			{
				session1.getTransaction().rollback();
				return null;
			}
			finally
			{
				session1.close();
			}
		}
	}
	
	@RequestMapping(value = "/getmaxACTMainID", method = RequestMethod.POST)
	public @ResponseBody String getmaxACTMainID(String actSlotId,HttpSession sessionUserId) {			
		String[] list = actSlotId.split(",");
		String from = list[0];
		String to = list[1];
		return act_dao.getmaxACTMainID(from,to);
	}
	
	@RequestMapping(value = "/getSearch_actdtl" , method = RequestMethod.POST)
	public ModelAndView getSearch_actdtl(ModelMap model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "act_slot1", required = false) String act_slot,
			@RequestParam(value = "abc_code1", required = false) String abc_code,
			@RequestParam(value = "sus_no1", required = false) String sus_no,
			@RequestParam(value = "type_of_aircraft1", required = false) String type_of_aircraft){
	
		ArrayList<ArrayList<String>> list = act_dao.search_act_main(act_slot, abc_code, sus_no,type_of_aircraft);
		model.put("list", list);
		model.put("size", list.size());
		model.put("act_slot1", act_slot);
		model.put("abc_code1", abc_code);
		model.put("sus_no1", sus_no);
		model.put("type_of_aircraft1", type_of_aircraft);
		model.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.put("year", new SimpleDateFormat("yyyy").format(new Date()));
		model.put("msg", msg);
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String select="<option value='0'>-- Select --</option>";
		if(roleAccess.equals("Line_dte")) {
			model.put("getLine_DteList",roledao.getLine_DteList(roleSusNo));
		}else {
			model.put("selectLine_dte", select);
			model.put("getLine_DteList",roledao.getLine_DteList(""));
		}
		
		model.put("getAviationGroupNamelist", M.getAviationGroupNamelist(session));
		model.put("getLine_DteList",roledao.getLine_DteListsus());
		
		return new ModelAndView("actmainTiles");
	}
	
}
