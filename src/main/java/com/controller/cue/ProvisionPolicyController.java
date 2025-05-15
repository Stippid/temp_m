package com.controller.cue;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.cue.ProvisionPolicyDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_MMS_WET_PET_DET;
import com.models.CUE_TB_MISO_PROVISION_MASTER;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_DET;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES;
import com.models.CUE_TB_MISO_PROVISION_TRANSPORT_MASTER;
import com.models.CUE_TB_MISO_PROVISION_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_PROVISION_WEAPON_DET;
import com.models.CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES;
import com.models.CUE_TB_MISO_PROVISION_WET_EQUIP;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class ProvisionPolicyController {
	@Autowired
	private ProvisionPolicyDAO ppdao;  //=new Wet_Pet_Fet_SuperDAOImpl();
	
	@Autowired
	private RoleBaseMenuDAO roledao ;
	cueContoller M = new cueContoller();
	ValidationController validation = new ValidationController();
	@RequestMapping(value = "/provisionMstUrl", method = RequestMethod.GET)
	public ModelAndView provisionMstUrl(@ModelAttribute("getwe_pe_no") String getwe_pe_no ,ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("provisionMstUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getwe_pe_no", getwe_pe_no);
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTiles","provisionMstCmd",new CUE_TB_MISO_PROVISION_MASTER());
	}
	
	@RequestMapping(value = "/admin/ProvisionMstUrl1", method = RequestMethod.POST)
	public ModelAndView ProvisionMstUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "letter_no1", required = false) String letter_no,
			@RequestParam(value = "letter_date1", required = false) String letter_date,
			@RequestParam(value = "force_type1", required = false) String force_type,
			@RequestParam(value = "year_cal1", required = false) String year_cal,
			@RequestParam(value = "month_cal1", required = false) String month_cal,
			@RequestParam(value = "status1", required = false) String status){
			
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("status1", status);
		Mmap.put("letter_date1", letter_date);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("letter_no1", letter_no);
		Mmap.put("force_type1", force_type);
		Mmap.put("month_cal1", month_cal);
		Mmap.put("year_cal1", year_cal);
		
		List<Map<String, Object>> list = ppdao.AttributeReportProvisionMaster(we_pe_no,letter_no,force_type,status,year_cal,month_cal,roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTiles");
	}
	
	@RequestMapping(value = "/provisionMstAct", method = RequestMethod.POST)
	public ModelAndView provisionMstAct(@ModelAttribute("provisionMstCmd") CUE_TB_MISO_PROVISION_MASTER rs,HttpServletRequest request,ModelMap model,HttpSession session) {
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		 String letter_date = request.getParameter("letter_date");
		 String letter_no = request.getParameter("letter_no");
		 String month_cal = request.getParameter("month_cal");
		 String year_cal = request.getParameter("year_cal");
		 String we_pe_no = request.getParameter("we_pe_no");	
		 
		if(rs.getLetter_no() == "")
		{
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:provisionMstUrl");
		}	
		if(validation.checkWetPetLength(rs.getLetter_no())  == false){
	 		model.put("msg",validation.authLtrnoMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(rs.getLetter_date() == "")
		{
			model.put("msg", "Please Enter Auth Letter Date");
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkDateLength(rs.getLetter_date())  == false){
	 		model.put("msg",validation.dateMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(rs.getWe_pe_no() == "")
		{
			model.put("msg", "Please Enter  WE/PE No");
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(rs.getForce_type()== "")
		{
			model.put("msg", "Please Enter Select Force Type");
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWETypeLength(rs.getForce_type())  == false){
			model.put("msg",validation.forceTypeMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWETypeLength(rs.getNo_of_units())  == false){
	 		model.put("msg",validation.unitNoMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(rs.getNo_of_units() == "")
		{
			model.put("msg", "Please Enter No of Units");
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWepetabletittleLength(rs.getUnit_type())  == false){
	 		model.put("msg",validation.typeofUnitMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(rs.getWe_pe_no() == "")
		{
			model.put("msg", "Please Enter  WE/PE No");
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWepeLength(rs.getWe_pe_no())  == false){
	 		model.put("msg",validation.wepenoMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWETypeLength(rs.getWe_type())  == false){
	 		model.put("msg",validation.typeofDocMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWETypeLength(rs.getYear_cal())  == false){
	 		model.put("msg",validation.yearCalMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		if(validation.checkWetPetLength(rs.getWet_pet_no())  == false){
	 		model.put("msg",validation.wetpetnoMSG);
			return new ModelAndView("redirect:provisionMstUrl");
		}
		int pro_id = 0 ;
		Boolean b = checkprovitionpolicywepeForWeap(we_pe_no,month_cal,year_cal,session);
		if( b.equals(false))
		{
			rs.setCreated_by(username);
			rs.setCreated_on(creadtedate);
			
			rs.setLetter_no(request.getParameter("letter_no"));
			rs.setMonth_cal(request.getParameter("month_cal"));
			rs.setYear_cal(request.getParameter("year_cal"));
			rs.setWe_pe_no(request.getParameter("we_pe_no"));
			rs.setWet_pet_no(request.getParameter("wet_pet_no"));
			rs.setUnit_type(request.getParameter("unit_type"));
			rs.setForce_type(request.getParameter("force_type"));
			rs.setNo_of_units(request.getParameter("no_of_units"));
			rs.setStatus("0");
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			pro_id = (Integer) sessionHQL.save(rs);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();	
		
		////////////////////// Start MDFS
		if(request.getParameter("modHid") != null && request.getParameter("modHid") != ""){
			String[] modA = null;
			String[] modUnA = null;
			//String[] itemcode_tbl = null;
			//String[] amtincdec_tbl = null;
			
			modA = request.getParameter("modHid").toString().split("\\|:");
			modUnA = request.getParameter("modUnitsHid").toString().split("\\|:");
			//itemcode_tbl = request.getParameter("itemcode_tblHid").toString().split("\\|:");
			//amtincdec_tbl = request.getParameter("amtincdec_tblHid").toString().split("\\|:");
			
			for(int i = 0; i < modA.length; i++) {
				
				Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = sessionmdfs.beginTransaction();	
				Query qry = sessionmdfs.createSQLQuery("insert into CUE_TB_MISO_PROVISION_WEAPONS_MDFS (we_pe_no, item_code, "
						+ " amt_inc_dec,modification,no_of_units,provision_id,created_by,created_on,status)" 
				+ " select we_pe_no,item_seq_no,amt_inc_dec,'"+ modA[i]+"','"+modUnA[i]+"',"+pro_id+",'"+username+"','"+creadtedate+"','0' from CUE_TB_MISO_WEPE_WEAPONS_MDFS"
						+ " where upper(we_pe_no)=:we_pe_no and modification =:modification and status='1' ");
				qry.setParameter("modification", modA[i].toString());
				qry.setParameter("we_pe_no", we_pe_no.toUpperCase());
				qry.executeUpdate();
			    tx.commit();
			    sessionmdfs.close();
			}
		}
		//////////////////////end MDFS
		
		//////////////////////Start FOOTNOTE
		if(request.getParameter("footHid") != null && request.getParameter("footHid") != ""){
			String[] footMstidA = null;
			String[] footA = null;
			String[] footUnA = null;
			String[] footAmtA = null;
			String[] footItemA = null;
			String[] footScenarioA = null;
			String[] footLocForUnitCodeA = null;
			
			CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES profoot= new CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES();
			footMstidA = request.getParameter("footMstHid").toString().split("\\|:");
			footA = request.getParameter("footHid").toString().split("\\|:");
			footUnA = request.getParameter("footUnitsHid").toString().split("\\|:");
			footAmtA = request.getParameter("footAmtHid").toString().split("\\|:");
			footItemA = request.getParameter("footItemHid").toString().split("\\|:");
			footScenarioA = request.getParameter("footScenarioHid").toString().split("\\|:");
			footLocForUnitCodeA = request.getParameter("footlocForUnitHid").toString().split("\\|:");
			
			profoot.setProvision_id(pro_id);
			profoot.setWe_pe_no(request.getParameter("we_pe_no"));
			profoot.setCreated_by(username);
			profoot.setCreated_on(creadtedate);
			profoot.setStatus("0");
			
			for (int i = 0; i < footA.length; i++) {	
				profoot.setFoot_id(Integer.parseInt(footMstidA[i]));
				profoot.setCondition(footA[i]);
				profoot.setNo_of_units(footUnA[i]);
				Double amt= Double.parseDouble(footAmtA[i]);
				profoot.setAmt_inc_dec(amt);
				profoot.setItem_code(footItemA[i]);
				profoot.setScenario(footScenarioA[i]);
				profoot.setLoc_for_unit(footLocForUnitCodeA[i]);
				
				Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
				sessionfoot.beginTransaction();
				sessionfoot.save(profoot);
				sessionfoot.getTransaction().commit();
				sessionfoot.close();			
			}
		}
		//////////////////////end FOOTNOTE
		
		////////////////////// start Standard
		if(request.getParameter("stItemHid") != null && request.getParameter("stItemHid") != ""){
			String[] stItemA = null;
			String[] stAmtA = null;
			
			CUE_TB_MISO_PROVISION_WEAPON_DET prost= new CUE_TB_MISO_PROVISION_WEAPON_DET();
			CUE_TB_MISO_PROVISION_WET_EQUIP equip= new CUE_TB_MISO_PROVISION_WET_EQUIP();
			stItemA = request.getParameter("stItemHid").toString().split("\\|:");
			stAmtA = request.getParameter("stAmtHid").toString().split("\\|:");
			
			prost.setProvision_id(pro_id);
			prost.setWe_pe_no(request.getParameter("we_pe_no"));
			prost.setCreated_by(username);
			prost.setCreated_on(creadtedate);
			
			equip.setProvision_id(pro_id);
			equip.setCreated_by(username);
			equip.setCreated_on(creadtedate);
			
			String[] stItemHidWetPetNo = null;
			String[] stAmtHidWetPetNo = null;
			String stItemHidWetPetNo0 = request.getParameter("stItemHidWetPetNo");
			String stAmtHidWetPetNo0 = request.getParameter("stAmtHidWetPetNo");
			
			if(!stItemHidWetPetNo0.equals(""))
			{
				stItemHidWetPetNo = stItemHidWetPetNo0.toString().split("\\|");
				stAmtHidWetPetNo = stAmtHidWetPetNo0.toString().split("\\|"); 
				
				for (int p = 0; p < stItemHidWetPetNo.length; p++) {
					
					Double stAmtHidWetPetNo1=new Double(stAmtHidWetPetNo[p]);
					equip.setItem_seq_no(stItemHidWetPetNo[p]);
					equip.setAuth_weapon(stAmtHidWetPetNo1);
					
				if(request.getParameter("wet_pet_no") != null && request.getParameter("wet_pet_no") != "")
				{
					equip.setWet_pet_no(request.getParameter("wet_pet_no"));
					Session sessionwet = HibernateUtil.getSessionFactory().openSession();
					sessionwet.beginTransaction();
					sessionwet.save(equip);
					sessionwet.getTransaction().commit();
					sessionwet.close();
				}
			}
			}
			
			for (int i = 0; i < stItemA.length; i++) {	
				prost.setItem_code(stItemA[i]);
				double amtSt = new Double(stAmtA[i]);
				prost.setAuth_weapon(amtSt);
				Session sessionst = HibernateUtil.getSessionFactory().openSession();
				sessionst.beginTransaction();
				sessionst.save(prost);
				sessionst.getTransaction().commit();
				sessionst.close();
			}
			
			
		}
		
		model.put("msg", "Data saved Successfully");
		}
		else {
			model.put("msg", "Data Already exists!");
		}
		
		List<Map<String, Object>> list = ppdao.AttributeReportProvisionMaster(we_pe_no,letter_no,"","",year_cal,month_cal,"");
		model.put("letter_date1", letter_date);
		model.put("letter_no1", letter_no);
		model.put("month_cal1", month_cal);
		model.put("year_cal1", year_cal);
		model.put("we_pe_no1", we_pe_no);
		model.put("list", list);
		model.put("list.size()", list.size());
		model.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTiles");
	}
	
	public List<Object[]> getForceTypeList(){	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct codevalue,label from T_Domain_Value where domainid='FORCETYPE' order by label") ;
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getModByWeaponByWePeNo",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> getModByWeaponByWePeNo(String we_pe_no,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q =null;
		q = session.createQuery("select distinct modification from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no=:we_pe_no and modification is not null and modification !='' and status='1' order by modification") ;
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> list = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getStandardByWeaponByWePeNo",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_WEAPON_DET> getStandardByWeaponByWePeNo(String we_pe_no,HttpSession sessionUserId){
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q =null;
		q = session.createQuery("select distinct item_seq_no,auth_weapon from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no=:we_pe_no and item_seq_no is not null and item_seq_no !='' and status='1' order by item_seq_no") ;
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPON_DET> list = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getWet_petdetailByWetPetNo",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_MMS_WET_PET_DET> getWet_petdetailByWetPetNo(String wet_pet_no,HttpSession sessionUserId){	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q =null;
		q = session.createQuery("select distinct item_seq_no,authorization from CUE_TB_MISO_MMS_WET_PET_DET where wet_pet_no=:wet_pet_no and item_seq_no is not null and item_seq_no !='' order by item_seq_no") ;
		q.setParameter("wet_pet_no", wet_pet_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_MMS_WET_PET_DET> list = (List<CUE_TB_MISO_MMS_WET_PET_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	///////////////////////// search
	@RequestMapping(value = "/searchProvisionMstUrl", method = RequestMethod.GET)
	public ModelAndView searchProvisionMstUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request){
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchProvisionMstUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("searchProvisionMstTiles");
	}
			
	@RequestMapping(value = "/admin/searchProvisionMstUrl1", method = RequestMethod.POST)
	public ModelAndView searchProvisionMstUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "letter_no1", required = false) String letter_no,
			@RequestParam(value = "force_type1", required = false) String force_type,
			@RequestParam(value = "year_cal1", required = false) String year_cal,
			@RequestParam(value = "month_cal1", required = false) String month_cal,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("month_cal1", month_cal);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("letter_no1", letter_no);
			Mmap.put("force_type1", force_type);
			Mmap.put("year_cal1", year_cal);
			List<Map<String, Object>> list = ppdao.AttributeReportSearchProvisionMaster(status,year_cal,month_cal,we_pe_no,letter_no,force_type,roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			Mmap.put("getForceTypeList", getForceTypeList());
			return new ModelAndView("searchProvisionMstTiles");
	}
	
	/////////////////////////// Transport
	@RequestMapping(value = "/provisionMstTransUrl", method = RequestMethod.GET)
	public ModelAndView provisionMstTransUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("provisionMstTransUrl", roleid);	
		if(val == false){
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null){
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTransTiles","provisionMstTransCmd",new CUE_TB_MISO_PROVISION_TRANSPORT_MASTER());
	}	
	
	@RequestMapping(value = "/admin/provisionMstTransUrl1", method = RequestMethod.POST)
	public ModelAndView provisionMstTransUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "letter_no1", required = false) String letter_no,
			@RequestParam(value = "force_type1", required = false) String force_type,
			@RequestParam(value = "year_cal1", required = false) String year_cal,
			@RequestParam(value = "month_cal1", required = false) String month_cal,
			@RequestParam(value = "letter_date1", required = false) String letter_date,
			@RequestParam(value = "status1", required = false) String status){
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("status1", status);
			Mmap.put("year_cal1", year_cal);
			Mmap.put("letter_no1", letter_no);
			Mmap.put("force_type1", force_type);
			Mmap.put("month_cal1", month_cal);
			Mmap.put("letter_date1", letter_date);
			Mmap.put("force_type1", force_type);
			List<Map<String, Object>> list =ppdao.AttributeReportProvision(we_pe_no,letter_no,force_type,status,year_cal,month_cal,letter_date);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTransTiles");
	}
	
	@RequestMapping(value = "/admin/searchProvisionMstTransUrl1", method = RequestMethod.POST)
	public ModelAndView searchProvisionMstTransUrl1(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "letter_no1", required = false) String letter_no,
			@RequestParam(value = "force_type1", required = false) String force_type,
			@RequestParam(value = "year_cal1", required = false) String year_cal,
			@RequestParam(value = "month_cal1", required = false) String month_cal,
			@RequestParam(value = "status1", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			Mmap.put("month_cal1", month_cal);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("letter_no1", letter_no);
			Mmap.put("force_type1", force_type);
			Mmap.put("year_cal1", year_cal);
			List<Map<String, Object>> list = ppdao.AttributeReportSearchProvisionTrans(status,we_pe_no,letter_no,force_type,year_cal,month_cal,roleType);
			Mmap.put("list", list);
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("searchProvisionMstTransTiles");
	}
	
	@RequestMapping(value = "/provisionMstTransAct", method = RequestMethod.POST)
	public ModelAndView provisionMstTransAct(@ModelAttribute("provisionMstTransCmd") CUE_TB_MISO_PROVISION_TRANSPORT_MASTER rs,HttpServletRequest request, ModelMap model, HttpSession session) {
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String we_pe_no = request.getParameter("we_pe_no");
		String month_cal = request.getParameter("month_cal");
		String year_cal = request.getParameter("year_cal");
		String letter_no = request.getParameter("letter_no");
		String letter_date = request.getParameter("letter_date");

		if (rs.getLetter_no() == "") {
			model.put("msg", "Please Enter Auth Letter No");
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWetPetLength(rs.getLetter_no()) == false) {
			model.put("msg", validation.authLtrnoMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (rs.getLetter_date() == "") {
			model.put("msg", "Please Enter Auth Letter Date");
			return new ModelAndView("redirect:provisionMstTransUrl");
		}

		if (validation.checkDateLength(rs.getLetter_date()) == false) {
			model.put("msg", validation.dateMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (rs.getForce_type() == "") {
			model.put("msg", "Please Enter Select Force Type");
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWETypeLength(rs.getForce_type()) == false) {
			model.put("msg", validation.forceTypeMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (rs.getNo_of_units() == "") {
			model.put("msg", "Please Enter No of Units");
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWETypeLength(rs.getNo_of_units()) == false) {
			model.put("msg", validation.unitNoMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (we_pe_no == "") {
			model.put("msg", "Please Enter  WE/PE No");
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWepeLength(we_pe_no) == false) {
			model.put("msg", validation.wepenoMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWepetabletittleLength(rs.getUnit_type()) == false) {
			model.put("msg", validation.typeofUnitMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWETypeLength(rs.getWe_type()) == false) {
			model.put("msg", validation.typeofDocMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkWETypeLength(rs.getYear_cal()) == false) {
			model.put("msg", validation.yearCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}
		if (validation.checkMonthLength(rs.getMonth_cal()) == false) {
			model.put("msg", validation.monthCalMSG);
			return new ModelAndView("redirect:provisionMstTransUrl");
		}

		int pro_id = 0;
		Boolean b = checkprovitionpolicywepeForTrans(we_pe_no, month_cal, year_cal, session);
		if (b.equals(false)) {
			rs.setCreated_by(username);
			rs.setCreated_on(creadtedate);
			rs.setLetter_no(request.getParameter("letter_no"));
			rs.setMonth_cal(request.getParameter("month_cal"));
			rs.setYear_cal(request.getParameter("year_cal"));
			rs.setWe_pe_no(we_pe_no);
			rs.setWet_pet_no("");
			// rs.setWet_pet_no(request.getParameter("wet_pet_no"));
			rs.setUnit_type(request.getParameter("unit_type"));
			rs.setForce_type(request.getParameter("force_type"));
			rs.setNo_of_units(request.getParameter("no_of_units"));
			rs.setStatus("0");
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			pro_id = (Integer) sessionHQL.save(rs);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();

			////////////////////// Start MDFS
			if (request.getParameter("modHid") != null && request.getParameter("modHid") != "") {
				String[] modA = null;
				String[] modUnA = null;

				//String[] itemcode_tbl = null;
				//String[] amtincdec_tbl = null;

				modA = request.getParameter("modHid").toString().split("\\|:");
				modUnA = request.getParameter("modUnitsHid").toString().split("\\|:");
				//itemcode_tbl = request.getParameter("itemcode_tblHid").toString().split("\\|:");
				//amtincdec_tbl = request.getParameter("amtincdec_tblHid").toString().split("\\|:");
				
				for (int i = 0; i < modA.length; i++){
					Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
					Transaction tx1 = sessionmdfs.beginTransaction();
					/*Query qry = sessionmdfs.createSQLQuery("insert into CUE_TB_MISO_PROVISION_TRANSPORT_MDFS (we_pe_no, mct_no, "
									+ "amt_inc_dec,modification,no_of_units,provision_id,created_by,created_on,status)"
									+ "values" + "(:a1,:a2,:a3,:a4,:a5,:a6,:a7,:a8,:a9)");
					qry.setParameter("a1", request.getParameter("we_pe_no"));
					qry.setParameter("a2", itemcode_tbl[i]);
					qry.setParameter("a3", Integer.parseInt(amtincdec_tbl[i]));
					qry.setParameter("a4", modA[i]);
					qry.setParameter("a5", modUnA[i]);
					qry.setParameter("a6", pro_id);
					qry.setParameter("a7", username);
					qry.setParameter("a8", creadtedate);
					qry.setParameter("a9", "0");*/
					try {
						Query qry = sessionmdfs.createQuery("insert into CUE_TB_MISO_PROVISION_TRANSPORT_MDFS \r\n" + 
								"	(we_pe_no, mct_no,amt_inc_dec,modification,no_of_units,provision_id,created_by,created_on,status)\r\n" + 
								"select we_pe_no,mct_no,amt_inc_dec,'"+modA[i]+"','"+modUnA[i]+"',"+pro_id+",'"+username+"','"+creadtedate+"','0' from CUE_TB_MISO_WEPE_TRANSPORT_MDFS \r\n" + 
								"where upper(we_pe_no)=:we_pe_no and modification =:modification and status='1' ");
						
						qry.setParameter("modification", modA[i].toString());
						qry.setParameter("we_pe_no", we_pe_no.toUpperCase());
						qry.executeUpdate();
						tx1.commit();
					}catch(Exception e){
					
					}
					sessionmdfs.close();
				}
			}
			////////////////////// end MDFS

			////////////////////// Start FOOTNOTE
			if (request.getParameter("footHid") != null && request.getParameter("footHid") != "") {
				String[] footMstidA = null;
				String[] footA = null;
				String[] footUnA = null;
				String[] footAmtA = null;
				String[] footItemA = null;
				String[] footScenarioA = null;
				String[] footLocForUnitCodeA = null;

				CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES profoot = new CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES();

				footMstidA = request.getParameter("footMstHid").toString().split("\\|:");
				footA = request.getParameter("footHid").toString().split("\\|:");
				footUnA = request.getParameter("footUnitsHid").toString().split("\\|:");
				footAmtA = request.getParameter("footAmtHid").toString().split("\\|:");
				footItemA = request.getParameter("footItemHid").toString().split("\\|:");
				footScenarioA = request.getParameter("footScenarioHid").toString().split("\\|:");
				footLocForUnitCodeA = request.getParameter("footlocForUnitHid").toString().split("\\|:");

				profoot.setProvision_id(pro_id);
				profoot.setWe_pe_no(we_pe_no);
				profoot.setCreated_by(username);
				profoot.setCreated_on(creadtedate);
				profoot.setStatus("0");

				for (int i = 0; i < footA.length; i++) {
					profoot.setFoot_id(Integer.parseInt(footMstidA[i]));
					profoot.setCondition(footA[i]);
					profoot.setNo_of_units(footUnA[i]);
					Double amt = Double.parseDouble(footAmtA[i]);
					profoot.setAmt_inc_dec(amt);
					profoot.setMct_no(footItemA[i]);
					profoot.setScenario(footScenarioA[i]);
					profoot.setLoc_for_unit(footLocForUnitCodeA[i]);

					Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
					sessionfoot.beginTransaction();
					sessionfoot.save(profoot);
					sessionfoot.getTransaction().commit();
					sessionfoot.close();
				}
			}
			////////////////////// end FOOTNOTE

			////////////////////// start Standard
			if (request.getParameter("stItemHid") != null && request.getParameter("stItemHid") != "") {
				String[] stItemA = null;
				String[] stAmtA = null;
				CUE_TB_MISO_PROVISION_TRANSPORT_DET prost = new CUE_TB_MISO_PROVISION_TRANSPORT_DET();

				stItemA = request.getParameter("stItemHid").toString().split("\\|:");
				stAmtA = request.getParameter("stAmtHid").toString().split("\\|:");

				prost.setProvision_id(pro_id);
				prost.setWe_pe_no(we_pe_no);
				prost.setCreated_by(username);
				prost.setCreated_on(creadtedate);

				for (int i = 0; i < stItemA.length; i++) {
					prost.setMct_no(stItemA[i]);
					Double amtSt = Double.parseDouble(stAmtA[i]);
					prost.setAuth_amt(amtSt);
					Session sessionst = HibernateUtil.getSessionFactory().openSession();
					sessionst.beginTransaction();
					sessionst.save(prost);
					sessionst.getTransaction().commit();
					sessionst.close();
				}
			}
			////////////////////// end Standard
			model.put("msg", "Data saved Successfully");
		} else {
			model.put("msg", "Data Already exists!");
		}

		List<Map<String, Object>> list1 = ppdao.AttributeReportProvision("", letter_no, "", "", year_cal, month_cal,
				letter_date);
		model.put("list", list1);
		model.put("list.size()", list1.size());
		model.put("letter_no1", letter_no);
		model.put("month_cal1", month_cal);
		model.put("year_cal1", year_cal);
		model.put("letter_date1", letter_date);
		model.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("provisionMstTransTiles");
	}
	
	
	@RequestMapping(value = "/getModByTransByWePeNo",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> getModByTransByWePeNo(String we_pe_no,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q =null;
		q = session.createQuery("select distinct modification from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no and modification is not null and modification !='' and status='1' order by modification") ;
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@RequestMapping(value = "/getStandardByTransByWePeNo",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getStandardByTransByWePeNo(String we_pe_no,HttpSession sessionUserId) {	
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q =null;
		q = session.createQuery("select distinct mct_no,auth_amt from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no and mct_no is not null and mct_no !='' and status='1' order by mct_no") ;
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANSPORT_DET> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	/////////////////////// search Transport
	@RequestMapping(value = "/searchProvisionMstTransUrl", method = RequestMethod.GET)
	public ModelAndView searchProvisionMstTransUrl(ModelMap Mmap,HttpSession session,@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchProvisionMstTransUrl", roleid);	
		if(val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("searchProvisionMstTransTiles");
	}
	
	///////////////////////// end
	
	@RequestMapping(value = "/ApprovedProWepUrl",method=RequestMethod.POST)
	public ModelAndView ApprovedProWepUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "letter_no2", required = false) String letter_no,
		@RequestParam(value = "force_type2", required = false) String force_type,
		@RequestParam(value = "year_cal2", required = false) String year_cal,
		@RequestParam(value = "month_cal2", required = false) String month_cal,
		@RequestParam(value = "status2", required = false) String status){
		String roleType = session.getAttribute("roleType").toString();
		
		Mmap.put("msg", ppdao.setApprovedProwep(appid));	
		
		Mmap.put("month_cal1", month_cal);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("letter_no1", letter_no);
		Mmap.put("force_type1", force_type);
		Mmap.put("year_cal1", year_cal);
		
		List<Map<String, Object>> list = ppdao.AttributeReportSearchProvisionMaster(status,year_cal,month_cal,we_pe_no,letter_no,force_type,roleType);
		Mmap.put("list", list);
		
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("searchProvisionMstTiles");
	}
	
	@RequestMapping(value = "/rejectProWepUrl",method=RequestMethod.POST)
	public ModelAndView rejectProWepUrl(@ModelAttribute("rejectid") int rejectid,HttpSession session,ModelMap Mmap
			,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "letter_no3", required = false) String letter_no,
			@RequestParam(value = "force_type3", required = false) String force_type,
			@RequestParam(value = "year_cal3", required = false) String year_cal,
			@RequestParam(value = "month_cal3", required = false) String month_cal,
			@RequestParam(value = "status3", required = false) String status){
			String roleType = session.getAttribute("roleType").toString();
			
			Mmap.put("msg", ppdao.setRejectProwep(rejectid));	
			
			Mmap.put("month_cal1", month_cal);
			Mmap.put("status1", status);
			Mmap.put("we_pe_no1", we_pe_no);
			Mmap.put("letter_no1", letter_no);
			Mmap.put("force_type1", force_type);
			Mmap.put("year_cal1", year_cal);
			
			List<Map<String, Object>> list = ppdao.AttributeReportSearchProvisionMaster(status,year_cal,month_cal,we_pe_no,letter_no,force_type,roleType);
			Mmap.put("list", list);
			
			Mmap.put("list.size()", list.size());
			Mmap.put("roleType", roleType);
			Mmap.put("getForceTypeList", getForceTypeList());
			return new ModelAndView("searchProvisionMstTiles");
		
	}
	
	@RequestMapping(value = "/deleteProWepUrlAdd",method=RequestMethod.POST)
	public @ResponseBody List<String> deleteProWepUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(ppdao.setDeleteProwep(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/updateProWepUrl")
	public ModelAndView updateProWepUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("EditprovisionMstCmd", ppdao.getEditProWep(updateid));
		model.put("msg", msg);
		model.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("editprovisionMasterTile");
	}
	
	@RequestMapping(value = "/EditprovisionMstAct")
	public ModelAndView EditprovisionMstAct(@ModelAttribute("EditprovisionMstCmd") CUE_TB_MISO_PROVISION_MASTER rs,HttpServletRequest request,ModelMap model,HttpSession session,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
			if(rs.getLetter_no() == "")
			{
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter Auth Letter No");
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(validation.checkWetPetLength(rs.getLetter_no())  == false){
		 		model.put("msg",validation.authLtrnoMSG);
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(validation.checkDateLength(rs.getLetter_date())  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(rs.getForce_type()== "")
			{
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter Select Force Type");
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(validation.checkWETypeLength(rs.getForce_type())  == false){
				model.put("msg",validation.forceTypeMSG);
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(rs.getNo_of_units() == "")
			{
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter No of Units");
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(validation.checkWETypeLength(rs.getNo_of_units())  == false){
		 		model.put("msg",validation.unitNoMSG);
				return new ModelAndView("redirect:updateProWepUrl");
			}
			if(validation.checkWetPetLength(rs.getWet_pet_no())  == false){
		 		model.put("msg",validation.wetpetnoMSG);
				return new ModelAndView("redirect:updateProWepUrl");
			}
		
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		String username = session.getAttribute("username").toString();
		String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int pro_id = rs.getId();
		String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = "update CUE_TB_MISO_PROVISION_MASTER w set w.letter_no =:letter_no ,w.wet_pet_no =:wet_pet_no , w.unit_type =:unit_type , w.force_type =:force_type,w.no_of_units=:no_of_units,w.created_on=:creadtedate,w.modified_by=:modified_by,w.modified_on=:modified_on, w.status='0' where w.id =:id ";
		sessionHQL.createQuery( hqlUpdate ).setString( "letter_no", request.getParameter("letter_no") ).setString( "wet_pet_no", request.getParameter("wet_pet_no") ).setString( "unit_type", request.getParameter("unit_type") ).setString("creadtedate", creadtedate).setString("force_type", request.getParameter("force_type")).setString( "no_of_units", request.getParameter("no_of_units") ).setString("modified_by", username).setString("modified_on", modifydate).setInteger("id", pro_id).executeUpdate();
		tx.commit();
		sessionHQL.close();			
		
		//////////////////////Start MDFS
		Boolean id_mdfs_b = isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_WEAPONS_MDFS"); 
	
		if(id_mdfs_b.equals(false)) {
			
			String[] modA = null;
			String[] modUnA = null;
		
			modA = request.getParameter("modHid").toString().split("\\|:");
			modUnA = request.getParameter("modUnitsHid").toString().split("\\|:");
		
			for (int i = 0; i < modA.length; i++) {	
				
				Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
				Transaction tx1 = sessionmdfs.beginTransaction();	
				Query qry = sessionmdfs.createSQLQuery("insert into CUE_TB_MISO_PROVISION_WEAPONS_MDFS (we_pe_no, item_code, "
						+ "amt_inc_dec,modification,no_of_units,provision_id,modified_by,modified_on,status)" 
				+ " select we_pe_no,item_seq_no,amt_inc_dec,'"+ modA[i]+"','"+modUnA[i]+"',"+pro_id+",'"+username+"','"+creadtedate+"','0' from CUE_TB_MISO_WEPE_WEAPONS_MDFS"
						+ " where upper(we_pe_no)=:we_pe_no and modification =:modification and status='1' ");
				qry.setParameter("modification", modA[i].toString());
				qry.setParameter("we_pe_no", request.getParameter("we_pe_no").toUpperCase());
				qry.executeUpdate();
			    tx1.commit();
			    sessionmdfs.close();
			}
		}
		//////////////////////end MDFS
		
		//////////////////////Start FOOTNOTE		
		Boolean id_foot_b =  isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES");
		if(id_foot_b.equals(false))
		{
			if(request.getParameter("footHid") != null && request.getParameter("footHid") != ""){
			
			String[] footMstidA = null;
			String[] footA = null;
			String[] footUnA = null;
			String[] footAmtA = null;
			String[] footItemA = null;
			String[] footScenarioA = null;
			String[] footLocForUnitCodeA = null;
			
			CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES profoot= new CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES();
			
			footMstidA = request.getParameter("footMstHid").toString().split("\\|:");
			footA = request.getParameter("footHid").toString().split("\\|:");
			footUnA = request.getParameter("footUnitsHid").toString().split("\\|:");
			footAmtA = request.getParameter("footAmtHid").toString().split("\\|:");
			footItemA = request.getParameter("footItemHid").toString().split("\\|:");
			footScenarioA = request.getParameter("footScenarioHid").toString().split("\\|:");
			footLocForUnitCodeA = request.getParameter("footlocForUnitHid").toString().split("\\|:");
			
			profoot.setProvision_id(pro_id);
			profoot.setWe_pe_no(request.getParameter("we_pe_no"));
			profoot.setModified_by(username);
			profoot.setModified_on(creadtedate);
			profoot.setStatus("0");
			
			for (int i = 0; i < footA.length; i++) {	
				profoot.setFoot_id(Integer.parseInt(footMstidA[i]));
				profoot.setCondition(footA[i]);
				profoot.setNo_of_units(footUnA[i]);
				Double amt= Double.parseDouble(footAmtA[i]);
				profoot.setAmt_inc_dec(amt);
				profoot.setItem_code(footItemA[i]);
				profoot.setScenario(footScenarioA[i]);
				profoot.setLoc_for_unit(footLocForUnitCodeA[i]);
				
				Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
				sessionfoot.beginTransaction();
				sessionfoot.save(profoot);
				sessionfoot.getTransaction().commit();
				sessionfoot.close();			
			}
			}
		}
		//////////////////////end FOOTNOTE
		
		////////////////////// start Standard		
		Boolean id_std_b =  isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_WEAPON_DET");
		Boolean id_std_b_eqip = isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_WET_EQUIP");
		if(id_std_b.equals(false)) {
		
			if(request.getParameter("stItemHid") != null && request.getParameter("stItemHid") != ""){
				String[] stItemA = null;
				String[] stAmtA = null;
				CUE_TB_MISO_PROVISION_WEAPON_DET prost= new CUE_TB_MISO_PROVISION_WEAPON_DET();
			//	CUE_TB_MISO_PROVISION_WET_EQUIP equip= new CUE_TB_MISO_PROVISION_WET_EQUIP();
				stItemA = request.getParameter("stItemHid").toString().split("\\|:");
				stAmtA = request.getParameter("stAmtHid").toString().split("\\|:");
				prost.setProvision_id(pro_id);
				prost.setWe_pe_no(request.getParameter("we_pe_no"));
				prost.setModified_by(username);
				prost.setModified_on(creadtedate);
				
				
				if(id_std_b_eqip.equals(false)) {
					Session sessioneqpt = HibernateUtil.getSessionFactory().openSession();
					Transaction tx1 = sessioneqpt.beginTransaction(); 
					Query qry = sessioneqpt.createSQLQuery("insert into CUE_TB_MISO_PROVISION_WET_EQUIP (auth_weapon,created_by,created_on,item_seq_no,provision_id,wet_pet_no)" 
					+ " select authorization,'"+username+"','"+creadtedate+"',item_seq_no,"+pro_id+",wet_pet_no from CUE_TB_MISO_MMS_WET_PET_DET "
							+ " where upper(wet_pet_no)=:wet_pet_no ");
					qry.setParameter("wet_pet_no", request.getParameter("wet_pet_no").toUpperCase());
					sessioneqpt.save(prost);
					tx1.commit();
					sessioneqpt.close();
				}
				//equip.setProvision_id(pro_id);
				//equip.setCreated_by(username);
				//equip.setCreated_on(creadtedate);
				
				for (int i = 0; i < stItemA.length; i++) {
					prost.setItem_code(stItemA[i]);
					double amtSt = new Double(stAmtA[i]);
					prost.setAuth_weapon(amtSt);
					
					//double amtStwet = new Double(stAmtA[i]);
					//equip.setItem_seq_no(stItemA[i]);
					//equip.setAuth_weapon(amtStwet);
				
					Session sessionst = HibernateUtil.getSessionFactory().openSession();
					sessionst.beginTransaction();
					sessionst.save(prost);
					sessionst.getTransaction().commit();
					sessionst.close();
					
					/*if(request.getParameter("wet_pet_no") != null && request.getParameter("wet_pet_no") != "")
					{
						equip.setWet_pet_no(request.getParameter("wet_pet_no"));
						Session sessionwet = HibernateUtil.getSessionFactory().openSession();
						sessionwet.beginTransaction();
						sessionwet.save(equip);
						sessionwet.getTransaction().commit();
						sessionwet.close();
					}*/
				}
			}
		}
		//////////////////////end Standard	
		model.put("msg", "Updated Successfully");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("redirect:provisionMstUrl");
	}
	
	public Boolean isExistProWepmdfs(int id,String tbl) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql="delete FROM %s m where m.provision_id=:id ";
			Query query= session.createQuery(String.format(hql,tbl));
			query.setParameter("id", id);			
			query.executeUpdate();			
			tx.commit();
			session.close();
			return false;
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		} 
	}
	
	@RequestMapping(value = "/getmdfProWepCheck",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_PROVISION_WEAPONS_MDFS> getmdfProWepCheck(int id,String tbl,HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(String.format("from %s where provision_id=:id", tbl));
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_PROVISION_WEAPONS_MDFS> list = (List<CUE_TB_MISO_PROVISION_WEAPONS_MDFS>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	
	@RequestMapping(value = "/getFootProWepCheck",method=RequestMethod.POST)
	public @ResponseBody List<CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES> getFootProWepCheck(int id,String tbl,HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(String.format("from %s where provision_id=:id", tbl));
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES> list = (List<CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	
	///////////////////trans//////////////////////////////	
	@RequestMapping(value = "/ApprovedProTraUrl",method=RequestMethod.POST)
	public ModelAndView ApprovedProTraUrl(@ModelAttribute("appid") int appid,HttpSession session,ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
		@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
		@RequestParam(value = "letter_no2", required = false) String letter_no,
		@RequestParam(value = "force_type2", required = false) String force_type,
		@RequestParam(value = "year_cal2", required = false) String year_cal,
		@RequestParam(value = "month_cal2", required = false) String month_cal,
		@RequestParam(value = "status2", required = false) String status){
		String roleType = session.getAttribute("roleType").toString();
		Mmap.put("msg", ppdao.setApprovedProtra(appid));		
		Mmap.put("month_cal1", month_cal);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("letter_no1", letter_no);
		Mmap.put("force_type1", force_type);
		Mmap.put("year_cal1", year_cal);
	
		List<Map<String, Object>> list = ppdao.AttributeReportSearchProvisionTrans(status,we_pe_no,letter_no,force_type,year_cal,month_cal,roleType);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("searchProvisionMstTransTiles");
	}	
	
	@RequestMapping(value = "/rejectProTraUrl",method=RequestMethod.POST)
	public ModelAndView rejectProTraUrl(@ModelAttribute("rejectid") int rejectid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication){
		model.put("msg", ppdao.setRejectProtra(rejectid));	
		return new ModelAndView("redirect:searchProvisionMstTransUrl");
	}
	
	@RequestMapping(value = "/deleteProTraUrlAdd",method=RequestMethod.POST)
	public @ResponseBody List<String> deleteProTraUrlAdd(int deleteid) {			
		List<String> list2 = new ArrayList<String>();
		list2.add(ppdao.setDeleteProtra(deleteid));
		return list2;
	}
	
	@RequestMapping(value = "/updateProTraUrl")
	public ModelAndView updateProTraUrl(@ModelAttribute("updateid") int updateid,ModelMap model,@RequestParam(value = "msg", required = false) String msg,Authentication authentication,HttpSession sessionEdit){
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("EditprovisionMstTransCmd", ppdao.getEditProTrans(updateid));
		model.put("msg", msg);
		model.put("getForceTypeList", getForceTypeList());
		return new ModelAndView("editprovisionMstTransTiles");
	}
	@RequestMapping(value = "/editprovisionMstTransAct",method=RequestMethod.POST)
	public ModelAndView editprovisionMstTransAct(@ModelAttribute("EditprovisionMstTransCmd") CUE_TB_MISO_PROVISION_TRANSPORT_MASTER rs,HttpServletRequest request,ModelMap model,HttpSession session,HttpSession sessionEdit) {
		String roleType = sessionEdit.getAttribute("roleType").toString();
		if(!roleType.equals("ALL") & !roleType.equals("DEO")) {
			return new ModelAndView("AccessTiles");
		}
		try {
			if(rs.getLetter_no() == "")
			{
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter Auth Letter No");
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(validation.checkWetPetLength(rs.getLetter_no())  == false){
		 		model.put("msg",validation.authLtrnoMSG);
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(validation.checkDateLength(rs.getLetter_date())  == false){
		 		model.put("msg",validation.dateMSG);
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(rs.getUnit_type() == "") {
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter Type of Unit");
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(rs.getForce_type()== "")
			{
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter Select Force Type");
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(validation.checkWETypeLength(rs.getForce_type())  == false){
		 		model.put("msg",validation.forceTypeMSG);
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(rs.getNo_of_units() == ""){
				model.put("updateid",rs.getId());
				model.put("msg", "Please Enter No of Units");
				return new ModelAndView("redirect:updateProTraUrl");
			}
			if(validation.checkWETypeLength(rs.getNo_of_units())  == false){
		 		model.put("msg",validation.unitNoMSG);
				return new ModelAndView("redirect:provisionMstTransUrl");
			}
			
			String username = session.getAttribute("username").toString();
			String creadtedate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			int pro_id = rs.getId();
			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = "update CUE_TB_MISO_PROVISION_TRANSPORT_MASTER w set w.letter_no = :letter_no ,w.wet_pet_no = :wet_pet_no , w.unit_type =:unit_type , w.force_type =:force_type,w.no_of_units=:no_of_units,w.created_on=:creadtedate,w.modified_by=:modified_by,w.modified_on=:modified_on, w.status='0' where w.id =:id ";
			sessionHQL.createQuery( hqlUpdate ).setString( "letter_no", request.getParameter("letter_no") ).setString( "wet_pet_no", request.getParameter("wet_pet_no") ).setString( "unit_type", request.getParameter("unit_type") ).setString("creadtedate", creadtedate).setString("force_type", request.getParameter("force_type")).setString( "no_of_units", request.getParameter("no_of_units")).setString("modified_by", username).setString( "modified_on", modifydate).setInteger("id", pro_id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			////////////////////// Start MDFS
			Boolean id_mdfs_b = isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_TRANSPORT_MDFS"); 	
			if(id_mdfs_b.equals(false)) {
			if(request.getParameter("modHid") != null && request.getParameter("modHid") != ""){
				String[] modA = null;
				String[] modUnA = null;
				modA = request.getParameter("modHid").toString().split("\\|:");
				modUnA = request.getParameter("modUnitsHid").toString().split("\\|:");
				for (int i = 0; i < modA.length; i++) {	
					Session sessionmdfs = HibernateUtil.getSessionFactory().openSession();
					Transaction tx1 = sessionmdfs.beginTransaction();	
					Query qry = sessionmdfs.createQuery("insert into CUE_TB_MISO_PROVISION_TRANSPORT_MDFS \r\n" + 
							"	(we_pe_no, mct_no,amt_inc_dec,modification,no_of_units,provision_id,modified_by,modified_on,status)\r\n" + 
							"select we_pe_no,mct_no,amt_inc_dec,'"+modA[i]+"','"+modUnA[i]+"',"+pro_id+",'"+username+"','"+creadtedate+"','0' from CUE_TB_MISO_WEPE_TRANSPORT_MDFS \r\n" + 
							"where upper(we_pe_no)=:we_pe_no and modification =:modification and status='1' ");
					
					qry.setParameter("modification", modA[i].toString());
					qry.setParameter("we_pe_no", request.getParameter("we_pe_no").toUpperCase());
					qry.executeUpdate();
				    tx1.commit();
				    sessionmdfs.close();
				}
			}
		}
			//////////////////////end MDFS
			
			//////////////////////Start FOOTNOTE
		Boolean id_foot_b =  isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES");
		if(id_foot_b.equals(false))
		{
			if(request.getParameter("footHid") != null && request.getParameter("footHid") != ""){
				String[] footMstidA = null;
				String[] footA = null;
				String[] footUnA = null;
				String[] footAmtA = null;
				String[] footItemA = null;
				String[] footScenarioA = null;
				String[] footLocForUnitCodeA = null;
				
				CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES profoot= new CUE_TB_MISO_PROVISION_TRANSPORT_FOOTNOTES();
				
				footMstidA = request.getParameter("footMstHid").toString().split("\\|:");
				footA = request.getParameter("footHid").toString().split("\\|:");
				footUnA = request.getParameter("footUnitsHid").toString().split("\\|:");
				footAmtA = request.getParameter("footAmtHid").toString().split("\\|:");
				footItemA = request.getParameter("footItemHid").toString().split("\\|:");
				footScenarioA = request.getParameter("footScenarioHid").toString().split("\\|:");
				footLocForUnitCodeA = request.getParameter("footlocForUnitHid").toString().split("\\|:");
				
				profoot.setProvision_id(pro_id);
				profoot.setWe_pe_no(request.getParameter("we_pe_no"));
				profoot.setModified_by(username);
				profoot.setModified_on(creadtedate);
				profoot.setStatus("0");
				
				for (int i = 0; i < footA.length; i++) {	
					profoot.setFoot_id(Integer.parseInt(footMstidA[i]));
					profoot.setCondition(footA[i]);
					profoot.setNo_of_units(footUnA[i]);
					
					Double amt= Double.parseDouble(footAmtA[i]);
					profoot.setAmt_inc_dec(amt);
					profoot.setMct_no(footItemA[i]);				
					profoot.setScenario(footScenarioA[i]);
					profoot.setLoc_for_unit(footLocForUnitCodeA[i]);					
					Session sessionfoot = HibernateUtil.getSessionFactory().openSession();
					sessionfoot.beginTransaction();
					sessionfoot.save(profoot);
					sessionfoot.getTransaction().commit();
					sessionfoot.close();			
				}
			}
		}
			//////////////////////end FOOTNOTE
			
			////////////////////// start Standard
		Boolean id_std_b =  isExistProWepmdfs(pro_id,"CUE_TB_MISO_PROVISION_TRANSPORT_DET");
		if(id_std_b.equals(false))
		{
			if(request.getParameter("stItemHid") != null && request.getParameter("stItemHid") != ""){
				String[] stItemA = null;
				String[] stAmtA = null;
				
				CUE_TB_MISO_PROVISION_TRANSPORT_DET prost= new CUE_TB_MISO_PROVISION_TRANSPORT_DET();
				
				stItemA = request.getParameter("stItemHid").toString().split("\\|:");
				stAmtA = request.getParameter("stAmtHid").toString().split("\\|:");
				
				prost.setProvision_id(pro_id);
				prost.setWe_pe_no(request.getParameter("we_pe_no"));
				prost.setModified_by(username);
				prost.setModified_on(creadtedate);
				
				for (int i = 0; i < stItemA.length; i++) {
					prost.setMct_no(stItemA[i]);
					Double amtSt= Double.parseDouble(stAmtA[i]);
					prost.setAuth_amt(amtSt);
					Session sessionst = HibernateUtil.getSessionFactory().openSession();
					sessionst.beginTransaction();
					sessionst.save(prost);
					sessionst.getTransaction().commit();
					sessionst.close();
				}
			}
		}
			//////////////////////end Standard			
		model.put("msg", "Updated Successfully");
	}
	catch (Exception e) {}
	return new ModelAndView("redirect:provisionMstTransUrl");
}	
	
	@SuppressWarnings("unchecked")
	public Boolean checkprovitionpolicywepeForWeap(String we_pe_no,String month_cal,String year_cal,HttpSession sessionUserId) {
		String hql="";
		hql="select distinct we_pe_no from CUE_TB_MISO_PROVISION_MASTER where month_cal=:month_cal and year_cal=:year_cal and we_pe_no=:we_pe_no and (status_delete is  null or status_delete ='') order by we_pe_no";
		List<CUE_TB_MISO_PROVISION_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("month_cal", month_cal);
			query.setParameter("year_cal", year_cal);
			users = (List<CUE_TB_MISO_PROVISION_MASTER>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;	
	}
	
	@SuppressWarnings("unchecked")
	public Boolean checkprovitionpolicywepeForTrans(String we_pe_no,String month_cal,String year_cal,HttpSession sessionUserId) {
		String hql="";
		hql="select distinct we_pe_no from CUE_TB_MISO_PROVISION_TRANSPORT_MASTER where month_cal=:month_cal and year_cal=:year_cal and we_pe_no=:we_pe_no and (status_delete is  null or status_delete ='') order by we_pe_no";
		List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("month_cal", month_cal);
			query.setParameter("year_cal", year_cal);
			users = (List<CUE_TB_MISO_PROVISION_TRANSPORT_MASTER>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
		
			return null;
		} 
		if(users.size()>0)
		{
			return true;
		}
		return false;	
	}
	
	
}
