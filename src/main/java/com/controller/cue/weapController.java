
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
import com.dao.cue.Cue_wepe_conditionDAO;
import com.dao.cue.WeaponAuthUnderModiDAO;
import com.dao.cue.WepePersMdfsDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.CUE_TB_MISO_PROVISION_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class weapController {

	@Autowired
	private WepePersMdfsDAO wepepersmdfs;
	@Autowired
	private WeaponAuthUnderModiDAO weponAuthmodiDAO;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Cue_wepe_conditionDAO vetting;	
	
	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/admin/Weap_Auth", method = RequestMethod.GET)
	public ModelAndView in_de_footnote(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Weap_Auth", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getLocation1", getLocation1());
//		Mmap.put("getFormation1", getFormation1());
		Mmap.put("getUnitUrl1", getUnitUrl1());
		return new ModelAndView("weap_authTiles", "weap_aut_modiCMD", new CUE_TB_MISO_PROVISION_WEAPONS_MDFS());
	}

	public List<String> getLocation1() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select distinct code,code_value from Tb_Miso_Orbat_Code where status_record='1'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;

	}
	
	public List<String> getUnitUrl1() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select a.sus_no, a.unit_name, b.formation_code, b.level_in_hierarchy from Miso_Orbat_Unt_Dtl a, Tbl_CodesForm b where a.sus_no=b.sus_no  \r\n"
				+ "	and upper(b.level_in_hierarchy) not in ('COMMAND','CORPS','DIVISION','BRIGADE') and UPPER(a.status_sus_no) = 'ACTIVE'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;

	}
	
	public List<String> getFormation1() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select   a.sus_no, a.unit_name, b.formation_code, b.level_in_hierarchy from Miso_Orbat_Unt_Dtl a,   Tbl_CodesForm b where a.sus_no=b.sus_no  \r\n"
				+ "and upper(b.level_in_hierarchy) in ('COMMAND','CORPS','DIVISION','BRIGADE') and UPPER(a.status_sus_no) = 'ACTIVE'");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;

	}

	@RequestMapping(value = "/admin/Weap_Auth1", method = RequestMethod.POST)
	public ModelAndView Weap_Auth1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "scenario1", required = false) String scenario,
			@RequestParam(value = "location1", required = false) String location,
			@RequestParam(value = "formation1", required = false) String formation,
			@RequestParam(value = "unit1", required = false) String unit,
			@RequestParam(value = "location1_hid", required = false) String location_code,
			@RequestParam(value = "formation1_hid", required = false) String formation_code,
			@RequestParam(value = "unit1_hid", required = false) String unit_code,
			@RequestParam(value = "modification1", required = false) String modification) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess =  session.getAttribute("roleAccess").toString();
		
		Mmap.put("we_pe1", we_pe);
		Mmap.put("scenario1", scenario);
		Mmap.put("location1", location);
		Mmap.put("formation1", formation);
		Mmap.put("unit1", unit);
		Mmap.put("location1_hid", location_code);
		Mmap.put("formation1_hid", formation_code);
		Mmap.put("unit1_hid", unit_code);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("modification1", modification);
		List<Map<String, Object>> list = weponAuthmodiDAO.AttributeReportSearchMdfs1(we_pe_no, item_seq_no,
				modification, status, roleType,roleAccess);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		Mmap.put("getLocation1", getLocation1());
//		Mmap.put("getFormation1", getFormation1());
		Mmap.put("getUnitUrl1", getUnitUrl1());
		return new ModelAndView("weap_authTiles");
	}

	@RequestMapping(value = "/admin/weap_aut_modiAction", method = RequestMethod.POST)
	public ModelAndView weap_aut_modiAction(@ModelAttribute("weap_aut_modiCMD") CUE_TB_MISO_WEPE_WEAPONS_MDFS rs,
			HttpServletRequest request, ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		int roleid = (Integer) session.getAttribute("roleid");
		String amt_inc_dec = request.getParameter("amt_inc_dec");
		String we_pe = request.getParameter("we_pe");
		String radio1 = request.getParameter("ct-radio1");
		String we_pe_no = request.getParameter("we_pe_no");
		String item_seq_no = request.getParameter("item_seq_no");
		String modification = request.getParameter("modification");
		String base_au = request.getParameter("base_autho_hidden");
		String location = request.getParameter("Location_a");
		String scenario3 = request.getParameter("scenario");
		String formation = request.getParameter("formation_name");
		String unit = request.getParameter("scenario_unit_name");
		String location_code = request.getParameter("location");
		String formation_code = request.getParameter("formation");
		String unit_code = request.getParameter("Unit_a");
		String remarks = request.getParameter("remarks");
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx0 = sessionHQL.beginTransaction();
		int a=0;
		try {

			if (we_pe == "" || we_pe == null || we_pe == "null" || we_pe.equals(null)) {
				model.put("msg", "Please Select Document");
				return new ModelAndView("redirect:Weap_Auth");
			}
			if (rs.getWe_pe_no() == "") {
				model.put("msg", "Please Enter WE/PE No");
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (validation.checkWepeLength(rs.getWe_pe_no()) == false) {
				model.put("msg", validation.wepenoMSG);
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (rs.getScenario() == "") {
				model.put("msg", "Please Select Scenario");
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (validation.checkScenarioLength(rs.getScenario()) == false) {
				model.put("msg", validation.senarioMSG);
				return new ModelAndView("redirect:Weap_Auth");
			}

			String scenario2 = rs.getScenario();
			if (scenario2.equals("Location")) {
				if (location.equals("") || location == "" || location == null
						|| location.equals(null) || location.isEmpty()) {
					model.put("msg", "Please Enter Location");
					return new ModelAndView("redirect:Weap_Auth");
				}

//				if (validation.checkFormationLength(location) == false) {
//					model.put("msg", validation.locMSG);
//					return new ModelAndView("redirect:Weap_Auth");
//				}
			}
			if (scenario2.equals("Formation")) {
				if (rs.getFormation().equals("") || rs.getFormation() == "" || rs.getFormation() == null
						|| rs.getFormation().equals(null) || rs.getFormation().isEmpty()) {
					model.put("msg", "Please Enter Formation");
					return new ModelAndView("redirect:Weap_Auth");

				}
				if (validation.checkFormationLength(rs.getFormation()) == false) {
					model.put("msg", validation.formMSG);
					return new ModelAndView("redirect:Weap_Auth");
				}
			}
			if (scenario2.equals("Unit")) {
				if (unit_code.equals("") || unit_code == "" || unit_code == null
						|| unit_code.equals(null) || unit_code.isEmpty()) {
					model.put("msg", "Please Enter Unit");
					return new ModelAndView("redirect:Weap_Auth");
				}
//				if (validation.checkFormationLength(rs.getScenario_unit()) == false) {
//					model.put("msg", validation.unitMSG);
//					return new ModelAndView("redirect:Weap_Auth");
//				}
			}
			if (rs.getModification().equals("")) {
				model.put("msg", "Please Enter Modification");
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (validation.checkModificationLength(rs.getModification()) == false) {
				model.put("msg", validation.modMSG);
				return new ModelAndView("redirect:Weap_Auth");
			}

			String item_type = request.getParameter("item_type");
			if (item_type == "") {
				model.put("msg", "Please Enter Nomenclature");
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (rs.getItem_seq_no().equals("")) {
				model.put("msg", "Please Enter Item Code");
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (validation.checkFormationLength(rs.getItem_seq_no()) == false) {
				model.put("msg", validation.itemcodeMSG);
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (amt_inc_dec == "" || amt_inc_dec == null || amt_inc_dec == "null" || amt_inc_dec.equals(null)) {
				model.put("msg", "Please Select Increment/Decrement");
				return new ModelAndView("redirect:Weap_Auth");
			}
			if (rs.getAmt_inc_dec() == 0.0) {
				model.put("msg", "Please Enter Amount of Increment/Decrement");
				return new ModelAndView("redirect:Weap_Auth");
			}

			int lenval = 0;
			if (request.getParameter("ct-radio1").equals("Increase"))
				lenval = 8;
			else if (request.getParameter("ct-radio1").equals("Decrease"))
				lenval = 9;

			String amt_inc_dec_valid = Double.toString(rs.getAmt_inc_dec());
			if (validation.checkAmt_inc_decLength(amt_inc_dec_valid, lenval) == false) {
				model.put("msg", validation.amt_inc_decMSG);
				return new ModelAndView("redirect:Weap_Auth");
			}

			if (validation.checkRemarksLength(rs.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:Weap_Auth");
			} 
			else {
				
				String amt;

				CUE_TB_MISO_WEPE_WEAPON_DET cue_weapon_det = new CUE_TB_MISO_WEPE_WEAPON_DET();
				Boolean det = isdetailWepe_weapdet_exits(we_pe_no, item_seq_no);
				if (det.equals(false)) {
				if (base_au.equals("") || base_au.equals("undefined") || base_au.equals("0")){
					cue_weapon_det.setWe_pe_no(we_pe_no);
					cue_weapon_det.setItem_seq_no(item_seq_no);
					double auth_amt = Double.valueOf(0);
					cue_weapon_det.setAuth_weapon(auth_amt);
					cue_weapon_det.setCreated_by(username);
					cue_weapon_det.setCreated_on(date);
					cue_weapon_det.setCreated_by(username);
					cue_weapon_det.setCreated_on(date);
					cue_weapon_det.setStatus("1");
					cue_weapon_det.setRoleid(roleid);
					Session sessionUD = HibernateUtil.getSessionFactory().openSession();
					sessionUD.beginTransaction();
					int uid = (Integer) sessionUD.save(cue_weapon_det);
					sessionUD.getTransaction().commit();
					sessionUD.close();
					model.put("msg", "Data saved Successfully");
				}
				}
				Boolean e = isdetailWepe_weapauth_exits(we_pe_no, item_seq_no, modification,location,formation,unit_code);
				if (e.equals(false)) {
				
				String scenario = rs.getScenario();
				
					if (scenario.equals("Location")) {
					
							
						String location_b []= location.split(",");
						for(String locationA:location_b)
//						for (int i =0; i<location_b.length; i++)
						{
							Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL1.beginTransaction();
							
							CUE_TB_MISO_WEPE_WEAPONS_MDFS rs1 = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
							
							rs1.setCreated_by(username);
							rs1.setCreated_on(date);
							rs1.setStatus("0");
							rs1.setLocation(locationA);
							rs1.setFormation(null);
							rs1.setScenario_unit(null);
							rs1.setScenario(scenario);	
							rs1.setModification(modification);
							rs1.setWe_pe_no(we_pe_no);
							rs1.setItem_seq_no(item_seq_no);
							rs1.setRoleid(roleid);
							rs1.setRemarks(remarks);
							
							if (radio1.equals("Decrease")) {
								amt = "-" + amt_inc_dec;
								double sum = Double.valueOf(Double.parseDouble(amt));
								rs1.setAmt_inc_dec(sum);
							}else {
								rs1.setAmt_inc_dec(Double.parseDouble(amt_inc_dec));
							}
							
							sessionHQL1.save(rs1);
							sessionHQL1.getTransaction().commit();
							sessionHQL1.close();
						}
						
					}
					
					
					else if (scenario.equals("Formation")) {
						Boolean e1 = isformationWepe_weapauth_exits(we_pe_no, item_seq_no, modification,formation);
						if (e1.equals(false)) {
						Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL1.beginTransaction();
							
						CUE_TB_MISO_WEPE_WEAPONS_MDFS rs2 = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
							
							rs2.setCreated_by(username);
							rs2.setCreated_on(date);
							rs2.setStatus("0");
							rs2.setFormation(formation_code);
							rs2.setLocation(null);
							rs2.setScenario_unit(null);
							rs2.setScenario(scenario);
							rs2.setModification(modification);
							rs2.setWe_pe_no(we_pe_no);
							rs2.setItem_seq_no(item_seq_no);
							rs2.setRoleid(roleid);
							rs2.setRemarks(remarks);
							if (radio1.equals("Decrease")) {
								amt = "-" + amt_inc_dec;
								double sum = Double.valueOf(Double.parseDouble(amt));
								rs2.setAmt_inc_dec(sum);
							}else {
								rs2.setAmt_inc_dec(Double.parseDouble(amt_inc_dec));
							}
						
						sessionHQL1.save(rs2);
						sessionHQL1.getTransaction().commit();
						sessionHQL1.close();
						}else {
							a++;
							model.put("msg", "Data Already Exits!");
						}
					} else if (scenario.equals("Unit")) {
						
						String unit_b []= unit_code.split(",");
						for (String unitA:unit_b) {
							Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
							sessionHQL1.beginTransaction();
							
							CUE_TB_MISO_WEPE_WEAPONS_MDFS rs3 = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
							rs3.setCreated_by(username);
							rs3.setCreated_on(date);
							rs3.setStatus("0");
							rs3.setFormation(null);
							rs3.setLocation(null);
							rs3.setScenario_unit(unitA);
							rs3.setScenario(scenario);
							rs3.setModification(modification);
							rs3.setWe_pe_no(we_pe_no);
							rs3.setItem_seq_no(item_seq_no);
							rs3.setRoleid(roleid);
							rs3.setRemarks(remarks);
							
							if (radio1.equals("Decrease")) {
								amt = "-" + amt_inc_dec;
								double sum = Double.valueOf(Double.parseDouble(amt));
								rs3.setAmt_inc_dec(sum);
							}else {
								rs3.setAmt_inc_dec(Double.parseDouble(amt_inc_dec));
							}
						sessionHQL1.save(rs3);
						sessionHQL1.getTransaction().commit();
						sessionHQL1.close();
						}
						
						
					} else if (scenario.equals("Others")) {
						Session sessionHQL1 = HibernateUtil.getSessionFactory().openSession();
						sessionHQL1.beginTransaction();
						
						CUE_TB_MISO_WEPE_WEAPONS_MDFS rs4 = new CUE_TB_MISO_WEPE_WEAPONS_MDFS();
						rs4.setCreated_by(username);
						rs4.setCreated_on(date);
						rs4.setStatus("0");
						rs4.setFormation(null);
						rs4.setLocation(null);
						rs4.setScenario_unit(null);
						rs4.setScenario("Others");
						rs4.setModification(modification);
						rs4.setWe_pe_no(we_pe_no);
						rs4.setItem_seq_no(item_seq_no);
						rs4.setRoleid(roleid);
						rs4.setRemarks(remarks);
						
						if (radio1.equals("Decrease")) {
							amt = "-" + amt_inc_dec;
							double sum = Double.valueOf(Double.parseDouble(amt));
							rs4.setAmt_inc_dec(sum);
						}else {
							rs4.setAmt_inc_dec(Double.parseDouble(amt_inc_dec));
						}
						sessionHQL1.save(rs4);
						sessionHQL1.getTransaction().commit();
						sessionHQL1.close();
					} else {
					
						rs.setScenario(null);
						rs.setFormation(null);
						rs.setLocation(null);
						rs.setScenario_unit(null);
					//	sessionHQL1.getTransaction().commit();
						//sessionHQL1.close();
					}
//					sessionHQL1.getTransaction().commit();
					if (a == 0) {
						model.put("msg", "Data saved Successfully");
					} else {
						model.put("msg", "Data Already Exits!");
					}

				} else {
					model.put("msg", "Data Already Exits!");
				}
			}
		} catch (Exception e) {
			sessionHQL.getTransaction().rollback();

		}
		
		List<Map<String, Object>> list = weponAuthmodiDAO.AttributeReportSearchMdfs1(we_pe_no, "", "", "", "","");
		model.put("list", list);
		model.put("we_pe_no1", we_pe_no);
		model.put("we_pe1", we_pe);
		model.put("scenario1", scenario3);
		model.put("location1", location);
		model.put("formation1", formation);
		model.put("unit1", unit);
		model.put("location1_hid", location_code);
		model.put("formation1_hid", formation_code);
		model.put("unit1_hid", unit_code);
		model.put("list.size()", list.size());
		model.put("getLocation1", getLocation1());
		model.put("getFormation1", getFormation1());
		model.put("getUnitUrl1", getUnitUrl1());
		return new ModelAndView("weap_authTiles");
	}

	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_weapauth_exits(String we_pe_no, String item_seq_no, String modification, String location,String formation,String unit_code) {
		String loc = "";
		String form = "";
		String unit = "";
		String location_b []= location.split(",");
		int i =0;
		for(String locationA:location_b) {
			if(i==0) {
				loc+= ":location"+i;
			}
			else {
				loc+= ",:location"+i;
			}
			i++;
		}
		String formation_b []= formation.split(",");
		int k =0;
		for(String formationA:formation_b) {
			if(k==0) {
				form+= ":formation"+k;
			}
			else {
				form+= ",:formation"+k;
			}
			k++;
		}
		
		String unit_b []= unit_code.split(",");
		int p =0;
		for(String unitA:unit_b) {
			if(p==0) {
				unit+= ":scenario_unit"+p;
			}
			else {
				unit+= ",:scenario_unit"+p;
			}
			p++;
		}
		String hql = "FROM CUE_TB_MISO_WEPE_WEAPONS_MDFS m where m.we_pe_no=:we_pe_no and m.item_seq_no=:item_seq_no and m.modification=:modification"
				+ " and (location in("+loc+" ) or  location is null) "
				+ " and (formation in("+form+" ) or  formation is null) "
				+ " and (scenario_unit in("+unit+" ) or  scenario_unit is null)";
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("item_seq_no", item_seq_no);
			query.setParameter("modification", modification);			
			String locationx []= location.split(",");
			int j =0;
			for(String locationA:locationx) {
				query.setParameter("location"+j, locationA);
				j++;
			}
			
			String formationx []= formation.split(",");
			int l =0;
			for(String formationA:formationx) {
				query.setParameter("formation"+l, formationA);
				l++;
			}
			
			String unitx []= unit_code.split(",");
			int n =0;
			for(String unitA:unitx) {
				query.setParameter("scenario_unit"+n, unitA);
				n++;
			}
		
			users = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isformationWepe_weapauth_exits(String we_pe_no, String item_seq_no, String modification,String formation) {
		
	
		String hql = "FROM CUE_TB_MISO_WEPE_WEAPONS_MDFS m where m.scenario=:scenario and m.we_pe_no=:we_pe_no and m.item_seq_no=:item_seq_no and m.modification=:modification";
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("item_seq_no", item_seq_no);
			query.setParameter("modification", modification);	
			query.setParameter("scenario", "Formation");	
			users = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean isdetailWepe_weapdet_exits(String we_pe_no, String item_seq_no) {
		
		String hql = "FROM CUE_TB_MISO_WEPE_WEAPON_DET m where m.we_pe_no=:we_pe_no and m.item_seq_no=:item_seq_no ";
		List<CUE_TB_MISO_WEPE_WEAPON_DET> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("we_pe_no", we_pe_no);
			query.setParameter("item_seq_no", item_seq_no);
			
			users = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/admin/search_Weap_Auth", method = RequestMethod.GET)
	public ModelAndView search_Weap_Auth(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_Weap_Auth", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("search_weap_authTiles");
	}

	@RequestMapping(value = "/ApprovedWeapnmodUrl", method = RequestMethod.POST)
	public ModelAndView ApprovedWeapnmodUrl(@ModelAttribute("appid") int appid, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			@RequestParam(value = "we_pe_no2", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no2", required = false) String item_seq_no,
			@RequestParam(value = "item_type2", required = false) String item_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "we_pe2", required = false) String we_pe,
			@RequestParam(value = "modification2", required = false) String modification) {
		String username = session.getAttribute("username").toString();
		String mst = weponAuthmodiDAO.setApprovedWeaponModi(appid,username);
		if(mst.equals("Approved Successfully")) {
			vetting.updateVettingDtl( we_pe_no, "3");
		}
		Mmap.put("msg", mst);
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("item_type1", item_type);
		Mmap.put("modification1", modification);

		List<Map<String, Object>> list = weponAuthmodiDAO.AttributeReportSearchMdfs(status, we_pe_no, item_seq_no,
				modification, roleType, roleAccess);

		Mmap.put("list", list);
		Mmap.put("roleType", roleType);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("search_weap_authTiles");
	}

	@RequestMapping(value = "/deleteWeapnmodUrlAdd", method = RequestMethod.POST)
	public @ResponseBody List<String> deleteWeapnmodUrlAdd(int deleteid) {
		List<String> list2 = new ArrayList<String>();
		list2.add(weponAuthmodiDAO.setDeleteWeaponModi(deleteid));
		return list2;
	}

	////////// EDIT ////////
	@RequestMapping(value = "/admin/updateWeapnmod1Url")
	public ModelAndView updateWeapnmod1Url(@ModelAttribute("updateid") int updateid, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
			HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		model.put("weap_aut_modiCMD", weponAuthmodiDAO.getCUE_TB_MISO_WEPE_WEAPONS_MDFSByid(updateid));
		model.put("msg", msg);
		return new ModelAndView("Edit_weap_authTiles");
	}

	@RequestMapping(value = "/admin/Editweap_aut_modiAction", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView in_deEditAction(@ModelAttribute("weap_aut_modiCMD") CUE_TB_MISO_WEPE_WEAPONS_MDFS updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession sessionEdit) {

		String roleType = sessionEdit.getAttribute("roleType").toString();
		String roleAccess = sessionEdit.getAttribute("roleAccess").toString();
		if (!roleType.equals("ALL") & !roleType.equals("DEO") & !roleAccess.equals("Line_dte")) {
			return new ModelAndView("AccessTiles");
		}
		try {

			String radio1 = request.getParameter("ct-radio1");
			String amt_inc_dec = request.getParameter("amt_inc_dec");
			if (updateid.getScenario().equals("") || updateid.getScenario() == null
					|| updateid.getScenario().equals(null) || updateid.getScenario().isEmpty()) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Scenario.");
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (validation.checkScenarioLength(updateid.getScenario()) == false) {
				model.put("msg", validation.senarioMSG);
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			String scenario2 = updateid.getScenario();
			if (scenario2 != "") {
				if (scenario2.equals("Location")) {
					if (updateid.getLocation().equals("") || updateid.getLocation() == null
							|| updateid.getLocation().equals(null) || updateid.getLocation().isEmpty()) {
						model.put("updateid", updateid.getId());
						model.put("msg", "Please Enter Location");
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}

					if (validation.checkFormationLength(updateid.getLocation()) == false) {
						model.put("msg", validation.locMSG);
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}

				}
				if (scenario2.equals("Formation")) {
					if (updateid.getFormation().equals("") || updateid.getFormation() == null
							|| updateid.getFormation().equals(null) || updateid.getFormation().isEmpty()) {
						model.put("updateid", updateid.getId());
						model.put("msg", "Please Enter Formation");
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}
					if (validation.checkFormationLength(updateid.getFormation()) == false) {
						model.put("msg", validation.formMSG);
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}
				}
				if (scenario2.equals("Unit")) {
					if (updateid.getScenario_unit().equals("") || updateid.getScenario_unit() == null
							|| updateid.getScenario_unit().equals(null) || updateid.getScenario_unit().isEmpty()) {
						model.put("updateid", updateid.getId());
						model.put("msg", "Please Enter Unit");
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}

					if (validation.checkFormationLength(updateid.getScenario_unit()) == false) {
						model.put("msg", validation.unitMSG);
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}
				}
			}
			if (updateid.getModification().equals("") || updateid.getModification() == null
					|| updateid.getModification().equals(null) || updateid.getModification().isEmpty()) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Modification.");
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (validation.checkModificationLength(updateid.getModification()) == false) {
				model.put("msg", validation.modMSG);
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (updateid.getItem_seq_no().equals("") || updateid.getItem_seq_no() == null
					|| updateid.getItem_seq_no().equals(null) || updateid.getItem_seq_no().isEmpty()) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Nomenclature / Item Code");
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (validation.checkFormationLength(updateid.getItem_seq_no()) == false) {
				model.put("msg", validation.itemcodeMSG);
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (radio1 == "" || radio1 == null || radio1 == "null" || radio1.equals(null)
					|| radio1.equals("undefined")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Select Increment/Decrement");
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			String base = request.getParameter("base_auth");
			float f_base = Float.parseFloat(base);
			float f_amt_inc_dec = Float.parseFloat(amt_inc_dec);
			if (amt_inc_dec == "0" || amt_inc_dec == null || amt_inc_dec == "null" || amt_inc_dec.equals("0")
					|| amt_inc_dec.equals("undefined")) {
				model.put("updateid", updateid.getId());
				model.put("msg", "Please Enter Amount of Increment / Decrement");
				return new ModelAndView("redirect:updateWeapnmod1Url");
			} else {

				if (radio1 == "Decrease" || radio1.equals("Decrease")) {
					if (f_amt_inc_dec > f_base) {
						model.put("updateid", updateid.getId());
						model.put("msg", "Please Check Amount of Inc/Dec");
						return new ModelAndView("redirect:updateWeapnmod1Url");
					}
				}
			}

			Double amt_inc_dec1 = 0.0;
			if (radio1.equals("Decrease")) {
				amt_inc_dec = "-" + amt_inc_dec;
				amt_inc_dec1 = Double.valueOf(Double.parseDouble(amt_inc_dec));
			} else {
				amt_inc_dec1 = Double.parseDouble(request.getParameter("amt_inc_dec"));
			}

			int lenval = 0;
			if (request.getParameter("ct-radio1").equals("Increase"))
				lenval = 8;
			else if (request.getParameter("ct-radio1").equals("Decrease"))
				lenval = 9;

			String amt_inc_dec_valid = Double.toString(updateid.getAmt_inc_dec());
			if (validation.checkAmt_inc_decLength(amt_inc_dec_valid, lenval) == false) {
				model.put("msg", validation.amt_inc_decMSG);
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			if (validation.checkRemarksLength(updateid.getRemarks()) == false) {
				model.put("msg", validation.remarksMSG);
				return new ModelAndView("redirect:updateWeapnmod1Url");
			}

			Session sessioncount = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = sessioncount.beginTransaction();
			Query q1 = sessioncount.createQuery("select count(*) from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no=:we_pe_no and item_seq_no=:item_seq_no and modification=:modification "
					+ " and (location=:location or  location is null)"
					+ " and (formation=:formation or  formation is null) "
					+ " and (scenario_unit=:scenario_unit or  scenario_unit is null)  and id !=:id");
			q1.setParameter("we_pe_no", updateid.getWe_pe_no());
			q1.setParameter("item_seq_no", updateid.getItem_seq_no());
			q1.setParameter("modification", updateid.getModification());
			q1.setParameter("location", updateid.getLocation());
			q1.setParameter("formation", updateid.getFormation());
			q1.setParameter("scenario_unit", updateid.getScenario_unit());
			q1.setParameter("id", updateid.getId());
			Long count2 = (Long) q1.uniqueResult();
			model.put("count", count2);
			transaction.commit();
			sessioncount.close();

			String mod = request.getParameter("modification");
			String location = request.getParameter("location");
			String formation = request.getParameter("formation");
			String scenario1 = request.getParameter("scenario");
			String scenario_unit = request.getParameter("scenario_unit");
			String remarks = request.getParameter("remarks");
			String item_seq_no = request.getParameter("item_seq_no");
			if (count2 == 0) {
				String scenario = request.getParameter("scenario");
				if (scenario.equals("Location")) {
					scenario1 = scenario;
					location = request.getParameter("location");
					formation = "";
					scenario_unit = "";
				} else if (scenario.equals("Formation")) {
					scenario1 = scenario;
					location = "";
					formation = request.getParameter("formation");
					scenario_unit = "";
				} else if (scenario.equals("Unit")) {
					scenario1 = scenario;
					location = "";
					formation = "";
					scenario_unit = request.getParameter("scenario_unit");
				} else if (scenario.equals("Others")) {
					scenario1 = scenario;
					location = "";
					formation = "";
					scenario_unit = "";
				} else {
					scenario1 = "";
					location = "";
					formation = "";
					scenario_unit = "";
				}

				Session session = HibernateUtilNA.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();

				String hql = "update CUE_TB_MISO_WEPE_WEAPONS_MDFS set scenario=:scenario,scenario_unit=:scenario_unit,modification=:modification,location=:location,formation=:formation,amt_inc_dec=:amt_inc_dec,remarks=:remarks,item_seq_no=:item_seq_no, status ='0',vettedby_dte1 = :vettedby_dte1, vettedby_dte2 = :vettedby_dte2 where id=:id";
				Query query = session.createQuery(hql).setString("location", location)
						.setString("scenario_unit", scenario_unit).setString("formation", formation)
						.setString("scenario", scenario1).setString("modification", mod)
						.setDouble("amt_inc_dec", amt_inc_dec1).setString("remarks", remarks)
						.setString("item_seq_no", item_seq_no).setString("vettedby_dte1", "")
			    		.setString("vettedby_dte2", "").setInteger("id", updateid.getId());
				int rowCount = query.executeUpdate();
				tx.commit();
				session.close();
				if (rowCount > 0) {
					model.put("msg", "Updated Successfully");
				} else {
					model.put("msg", "Updated not Successfully");
				}
			} else {
				model.put("msg", "Data already exist !");
				model.put("weap_aut_modiCMD", weponAuthmodiDAO.getCUE_TB_MISO_WEPE_WEAPONS_MDFSByid(updateid.getId()));
				return new ModelAndView("Edit_weap_authTiles");
			}
		} catch (Exception e) {

		} finally {

		}

		return new ModelAndView("redirect:Weap_Auth");
	}

	@RequestMapping(value = "/admin/search_weap_auth1", method = RequestMethod.POST)
	public ModelAndView search_weap_auth1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "we_pe_no1", required = false) String we_pe_no,
			@RequestParam(value = "item_seq_no1", required = false) String item_seq_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "item_type1", required = false) String item_type,
			@RequestParam(value = "we_pe1", required = false) String we_pe,
			@RequestParam(value = "modification1", required = false) String modification) {
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();

		Mmap.put("we_pe1", we_pe);
		Mmap.put("status1", status);
		Mmap.put("we_pe_no1", we_pe_no);
		Mmap.put("item_type1", item_type);
		Mmap.put("item_seq_no1", item_seq_no);
		Mmap.put("modification1", modification);

		List<Map<String, Object>> list = weponAuthmodiDAO.AttributeReportSearchMdfs(status, we_pe_no, item_seq_no,
				modification, roleType, roleAccess);

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		Mmap.put("roleType", roleType);
		return new ModelAndView("search_weap_authTiles");
	}

	@RequestMapping(value = "/updaterejectactionweapmod", method = RequestMethod.POST)
	public @ResponseBody List<String> updaterejectactionweapmod(HttpSession session,String remarks, String letter_no, int id) {
		 String username = session.getAttribute("username").toString();
		List<String> list2 = wepepersmdfs.updaterejectactionqrywepers2("cue_tb_miso_wepe_weapons_mdfs", remarks, id,
				letter_no,username);
		return list2;
	}

}
