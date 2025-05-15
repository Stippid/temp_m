package com.controller.psg.Jco_Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Transaction.Marital_DiscordJCO_DAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_JCO;
import com.models.psg.Jco_Transaction.TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class MaritalDiscordControllerJCO {
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	psg_jco_CommonController jcoComm = new psg_jco_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	
	ValidationController valid = new ValidationController();
	
	@Autowired
	Search_UpdatedJcoOr_DataDao jcoDAO;
	@Autowired
	Marital_DiscordJCO_DAO MarDis;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	 @RequestMapping(value = "/admin/MaritialDiscJCO", method = RequestMethod.GET)
	 public ModelAndView MaritialDiscJCO(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
	     String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("MaritialDiscJCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		    if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 Mmap.put("msg", msg);
		 return new ModelAndView("Maritial_Discord_JCOTiles");
	 }

	 @RequestMapping(value = "/admin/Search_Marital_DiscordJCO", method = RequestMethod.GET)
	 public ModelAndView Search_Marital_DiscordJCO(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
			  if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
			  }
			if (roleAccess.equals("Unit")) {
				Mmap.put("sus_no", roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}
			Mmap.put("roleSusNo", roleSusNo);
		 Mmap.put("msg", msg);
		 Mmap.put("getTypeofRankList", jcoComm.getRankjcoList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());		 
		 return new ModelAndView("search_MaritalDiscord_JCOTiles");
	 }
	 
	 @RequestMapping(value = "/admin/getSearch_marital_discordJCO", method = RequestMethod.POST)
		public ModelAndView getSearch_marital_discordJCO(ModelMap Mmap,HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "personnel_no1", required = false) String personnel_no1,
				@RequestParam(value = "status1", required = false) String status1,
				@RequestParam(value = "unit_name1", required = false) String unit_name,
				@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
				 @RequestParam(value = "from_date1", required = false) String from_date,@RequestParam(value = "cr_by1", required = false) String cr_by,
				    @RequestParam(value = "cr_date1", required = false) String cr_date){
			   	 String roleType = session.getAttribute("roleType").toString();
				 String roleSusNo = session.getAttribute("roleSusNo").toString();
				 String roleAccess = session.getAttribute("roleAccess").toString();
				 unit_name = unit_name.replace("&#40;", "(");
				 unit_name = unit_name.replace("&#41;", ")");
				 
				 
				
			 if(personnel_no1!="") {
				   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no1)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
						return new ModelAndView("redirect:Search_Marital_DiscordJCO");
					}
        if (!valid.ArmyNoLength(personnel_no1)) {
                               Mmap.put("msg", valid.ArmyNoMSG);
                               return new ModelAndView("redirect:Search_Marital_DiscordJCO");
                       }
             
             if (personnel_no1.length() < 2 || personnel_no1.length() > 12) {
                               Mmap.put("msg", "Army No Should Contain Maximum 12 Character");
                               return new ModelAndView("redirect:Search_Marital_DiscordJCO");
                       }
        } 
				
				if (!from_date.equals("") || from_date !="") {
					
					if (!valid.isValidDate(from_date)) {
						Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations From Date ");
						return new ModelAndView("redirect:Search_Marital_DiscordJCO");
					}
				}
				
				
				if(unit_sus_no!="") {
			    	  if (!valid.SusNoLength(unit_sus_no)) {
							Mmap.put("msg", valid.SusNoMSG);
							return new ModelAndView("redirect:Search_Marital_DiscordJCO");
						}
			    	  
			    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
							Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
							return new ModelAndView("redirect:Search_Marital_DiscordJCO");
						}
			      }
				
				if(unit_name!="") {
					 if (!valid.isUnit(unit_name)) {
                   Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                   return new ModelAndView("redirect:Search_Marital_DiscordJCO");
           }
			    	  
//			    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//							Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//							return new ModelAndView("redirect:Search_Marital_DiscordJCO");
//						}
			      }
				 if (roleAccess.equals("Unit")) {
						Mmap.put("sus_no", roleSusNo);
						Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
					}
			Mmap.put("roleSusNo", roleSusNo);
	    	 String afom1[]=from_date.split("/");
				from_date = afom1[2]+"/"+afom1[1]+"/"+afom1[0];        
	    	ArrayList<ArrayList<String>> list = MarDis.Search_JCOMarital(personnel_no1,status1,roleType,from_date,cr_by,cr_date,roleAccess,roleSusNo);
		        Mmap.put("personnel_no1", personnel_no1);
		        Mmap.put("status1", status1);
		        if (!roleAccess.equals("Unit")) {
		        Mmap.put("unit_name", unit_name);
		        Mmap.put("unit_sus_no", unit_sus_no);
		        }
		        Mmap.put("list",list);
		        Mmap.put("getTypeofRankList", jcoComm.getRankjcoList());
		        Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		        Mmap.put("cr_date1", cr_date);
				Mmap.put("cr_by1", cr_by);
			return new ModelAndView("search_MaritalDiscord_JCOTiles");
		}

	 
	

	  
	  @RequestMapping(value = "/GetCensusDataApproveJCO", method = RequestMethod.POST)
		public @ResponseBody List<Map<String, Object>> GetCensusDataApproveJCO(int jco_id) {
			
		  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			List<Map<String, Object>> list = jcoDAO.GetJcoOrCensusDataApprove(jco_id);
			tx.commit();
			return list;
		}
	  @RequestMapping(value = "/Marital_discord_JCO", method = RequestMethod.POST)
	    public @ResponseBody String Marital_discord_JCO(String jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String count = null;
			try {
				Query q1 = sessionHQL.createQuery(
						"select count(p.id) from TB_PSG_MARITAL_DISCORD_JCO p\r\n" + 
						", TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO c  \r\n" + 
						"where p.jco_id=:id and p.id=c.marital_id and c.status='1'");
				q1.setParameter("id", Integer.parseInt(jco_id));
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();	
				if(list.size() > 0) {
					count = String.valueOf(list.get(0));
				}
				tx.commit();
			} catch (RuntimeException e) {		
				e.printStackTrace();
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return count;
		}
	  
	  public String getMaritalJCOAlreadyExistCount(String marital_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String count = null;
			try {
				Query q1 = sessionHQL.createQuery(
						"select count(marital_id) From TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where marital_id=:maritial_id and status='0' ");
				q1.setParameter("maritial_id", Integer.parseInt(marital_id));
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();	
				if(list.size() > 0) {
					count = String.valueOf(list.get(0));
				}
				tx.commit();
			} catch (RuntimeException e) {			
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return count;
		}
	 
	  @RequestMapping(value = "/Maritial_Discord_JCO_action", method = RequestMethod.POST)
		public ModelAndView Maritial_Discord_JCO_action(@ModelAttribute("Maritial_Discord_JCO_CMD") TB_PSG_MARITAL_DISCORD_JCO BAN,TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO m_child, BindingResult result,
				HttpServletRequest request, ModelMap Mmap, HttpSession session, @RequestParam(value = "msg", required = false) String msg) throws ParseException {
		  
		  String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("MaritialDiscJCO", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
			    if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 		Transaction tx = sessionHQL.beginTransaction();
	 		Date date = new Date();
	 		Date dt_comp = null;
	 		int jco_id = 0;
			
			String username = session.getAttribute("username").toString();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			String name_lady = request.getParameter("name_lady");
			String complaint = request.getParameter("complaint");
			String status_of_case = request.getParameter("status_of_case");
			String dt_of_comp = request.getParameter("dt_of_comp");
			
			String personnel_no = request.getParameter("personnel_no");
			
			
			if(personnel_no== null || personnel_no.equals("")){ 
	            Mmap.put("msg", "Please Enter Army No");
	            return new ModelAndView("redirect:MaritialDiscJCO");
	        } 
			if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
				Mmap.put("msg",valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No");
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			if (!valid.ArmyNoLength(personnel_no)) {
                Mmap.put("msg", valid.ArmyNoMSG );
                return new ModelAndView("redirect:MaritialDiscJCO");
			}
			 if (personnel_no.length() < 2 || personnel_no.length() > 12) {
				Mmap.put("msg", "Army No Should Contain Maximum 12 Character");
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
	
			if(name_lady== null || name_lady.equals("")){ 
	            Mmap.put("msg", "Please Enter Name of Complainant.");
	            return new ModelAndView("redirect:MaritialDiscJCO");
	        } 
			if (!valid.isOnlyAlphabet(name_lady)) {
				Mmap.put("msg", " Name of Complainant " + valid.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			
			if (!valid.isvalidLength(name_lady, valid.nameMax, valid.nameMin)) {
				Mmap.put("msg", "Name of Complainant " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			if(dt_of_comp == null || dt_of_comp.equals("") || dt_of_comp.equals("null") || dt_of_comp.equals("DD/MM/YYYY")){ 
	            Mmap.put("msg", "Please Enter Date of Complaint/Allegations.");
	            return new ModelAndView("redirect:MaritialDiscJCO");
	        } 
			else if (!valid.isValidDate(dt_of_comp)) {
				Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations");
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			else {
				dt_comp = format.parse(dt_of_comp);
			   }
			if(complaint == null || complaint.equals("")){ 
	            Mmap.put("msg", "Please Enter Complaint/Allegations.");
	            return new ModelAndView("redirect:MaritialDiscJCO");
	        } 
			if (!valid.isOnlyAlphabetNumeric(complaint)) {
				Mmap.put("msg", "Complaint/Allegations " + valid.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			
			
			if(status_of_case == null || status_of_case.equals("")){ 
	            Mmap.put("msg", "Please Enter Status of the Case.");
	            return new ModelAndView("redirect:MaritialDiscJCO");
	        } 
			
			if (!valid.isOnlyAlphabetNumeric(status_of_case)) {
				Mmap.put("msg", " Status of the Case " + valid.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:MaritialDiscJCO");
			}
			
			if (!valid.isvalidLength(status_of_case, valid.remarkMax, valid.remarkMin)) {
				Mmap.put("msg", "Status of the Case " + valid.isValidLengthMSG);
				return new ModelAndView("redirect:Maritial");
			}
			
			if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
				jco_id = Integer.parseInt(request.getParameter("jco_id"));
			}
			int id = Integer.parseInt(request.getParameter("Hid"));
			String val2 = getMaritalJCOAlreadyExistCount(request.getParameter("Hid"));
			try {
				if (id == 0) {
						BAN.setDt_of_complaint(dt_comp);
						BAN.setStatus(0);
				 		BAN.setCreated_by(username);
						BAN.setCreated_date(date);
				 		sessionHQL.save(BAN);
				 		m_child.setMarital_id(BAN.getId());
				 		m_child.setStatus(0);
				 		m_child.setStatus_of_case(request.getParameter("status_of_case"));
				 		m_child.setCreated_by(username);
				 		m_child.setCreated_date(date);
				 		sessionHQL.save(m_child);
				}else {
						BAN.setDt_of_complaint(dt_comp);
						if (Integer.parseInt(val2) > 0) {
							m_child.setId(Integer.parseInt(request.getParameter("Child_Hid")));
							m_child.setMarital_id(Integer.parseInt(request.getParameter("Hid")));
							m_child.setStatus_of_case(request.getParameter("status_of_case"));
							MarDis.GetUpdateMarital_DiscordJCOChild(m_child,username);
						}else {
							m_child.setMarital_id(Integer.parseInt(request.getParameter("Hid")));
						    m_child.setStatus(0);
					 		m_child.setStatus_of_case(request.getParameter("status_of_case"));
					 		m_child.setCreated_by(username);
					 		m_child.setCreated_date(date);
					 		sessionHQL.save(m_child);
						}
				 		BAN.setId(id);
				 		
				 		MarDis.GetUpdateMarital_DiscordJCOParent(BAN,username);//, update in PARENt
				 }
				jcoComm.update_miso_role_hdr_status_jco(jco_id,0,username,"marital_dis_status");
				tx.commit();
				Mmap.put("msg", "Data Saved Successfully.");
				}catch (RuntimeException e) {
				try {
					tx.rollback();
					Mmap.put("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					Mmap.put("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
			return new ModelAndView("redirect:MaritialDiscJCO");
		} 
	  
	  public @ResponseBody List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> get_StatusOfCase(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where marital_id=:maritial_id and status in ('0','3') ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("maritial_id", id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> list = (List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
	  
	  @RequestMapping(value = "/Edit_Marital_DiscordJCO")
		public ModelAndView Edit_Marital_DiscordJCO(@ModelAttribute("id2") String updateid,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
				HttpSession sessionEdit,HttpServletRequest request) {
		  
		  String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
			    if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
	    		 TB_PSG_MARITAL_DISCORD_JCO authDetails = MarDis.getmaritalByid(Integer.parseInt(updateid));
				 Mmap.put("Edit_Maritial_Discord_CMD", authDetails);
				 List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> Status_case = get_StatusOfCase(Integer.parseInt(updateid));
				 Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
				 Mmap.put("Child_Hid", Status_case.get(0).getId());
				 Mmap.put("msg", msg);
			return new ModelAndView("Edit_Maritial_Discord_JCOTiles");
		}
	  
	  @RequestMapping(value = "/admin/CheckJCOStatus", method = RequestMethod.POST)
		public @ResponseBody List<TB_PSG_MARITAL_DISCORD_JCO> CheckJCOStatus(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			String hqlUpdate = " from TB_PSG_MARITAL_DISCORD_JCO where  jco_id=:jco_id and status in (0,1) ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_MARITAL_DISCORD_JCO> list = (List<TB_PSG_MARITAL_DISCORD_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			
			return list;
		}
		@RequestMapping(value = "/admin/GetJCOStatusCase", method = RequestMethod.POST)
		public @ResponseBody List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> GetJCOStatusCase(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int maritial_id = Integer.parseInt(request.getParameter("id"));
			String hqlUpdate = " from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where marital_id=:maritial_id and status ='0' ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("maritial_id", maritial_id);
			@SuppressWarnings("unchecked")
			List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> list = (List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			
			return list;
		}
		
		 @RequestMapping(value = "/Edit_Marital_DiscordJCO_action", method = RequestMethod.POST)
			public ModelAndView Edit_Marital_DiscordJCO_action(@ModelAttribute("Edit_Marital_DiscordJCO_CMD") TB_PSG_MARITAL_DISCORD_JCO rs,TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO m_child,
					HttpServletRequest request,ModelMap Mmap, HttpSession session,@RequestParam(value = "msg", required = false) String msg) throws ParseException {
				
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
			    if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
			 Session session1 = HibernateUtil.getSessionFactory().openSession();
		        Transaction tx = session1.beginTransaction();
				String username = session.getAttribute("username").toString();	
				int id = Integer.parseInt(request.getParameter("id"));
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dt_comp = null;
				
				String name_lady = request.getParameter("name_lady");
				String complaint = request.getParameter("complaint");
				String status_of_case = request.getParameter("status_of_case");
				String dt_of_comp = request.getParameter("dt_of_comp");
				
				String personnel_no = request.getParameter("personnel_no");
				
				
				 Mmap.put("id2", id);
				if(personnel_no== null || personnel_no.equals("")){ 
		            Mmap.put("msg", "Please Enter Army No");
		            return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
		        } 
				if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg",valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No");
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				if (!valid.ArmyNoLength(personnel_no)) {
	                Mmap.put("msg", valid.ArmyNoMSG );
	                return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				 if (personnel_no.length() < 2 || personnel_no.length() > 12) {
					Mmap.put("msg", "Army No Should Contain Maximum 12 Character");
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
		
				if(name_lady== null || name_lady.equals("")){ 
		            Mmap.put("msg", "Please Enter Name of Complainant.");
		            return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
		        } 
				if (!valid.isOnlyAlphabet(name_lady)) {
					Mmap.put("msg", " Name of Complainant " + valid.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				
				if (!valid.isvalidLength(name_lady, valid.nameMax, valid.nameMin)) {
					Mmap.put("msg", "Name of Complainant " + valid.isValidLengthMSG);
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				if(dt_of_comp == null || dt_of_comp.equals("") || dt_of_comp.equals("null") || dt_of_comp.equals("DD/MM/YYYY")){ 
		            Mmap.put("msg", "Please Enter Date of Complaint/Allegations.");
		            return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
		        } 
				else if (!valid.isValidDate(dt_of_comp)) {
					Mmap.put("msg", valid.isValidDateMSG + " of Complaint/Allegations");
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				else {
					dt_comp = format.parse(dt_of_comp);
				   }
				if(complaint == null || complaint.equals("")){ 
		            Mmap.put("msg", "Please Enter Complaint/Allegations.");
		            return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
		        } 
				if (!valid.isOnlyAlphabetNumeric(complaint)) {
					Mmap.put("msg", "Complaint/Allegations " + valid.isOnlyAlphabetNumericMSG);
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				
				
				if(status_of_case == null || status_of_case.equals("")){ 
		            Mmap.put("msg", "Please Enter Status of the Case.");
		            return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
		        } 
				
				if (!valid.isOnlyAlphabetNumeric(status_of_case)) {
					Mmap.put("msg", " Status of the Case " + valid.isOnlyAlphabetNumericMSG);
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
				
				if (!valid.isvalidLength(status_of_case, valid.remarkMax, valid.remarkMin)) {
					Mmap.put("msg", "Status of the Case " + valid.isValidLengthMSG);
					return new ModelAndView("redirect:Edit_Marital_DiscordJCO");
				}
			try {		
						 	 rs.setId(id);	
						 	 rs.setDt_of_complaint(dt_comp);
							 String Parent = MarDis.GetUpdateMarital_DiscordJCOParent(rs,username);//,
							 if (Parent == "Data Updated Successfully.") {
								 m_child.setId(Integer.parseInt(request.getParameter("Child_Hid")));
								 m_child.setMarital_id(id);
								 m_child.setStatus_of_case(request.getParameter("status_of_case").trim());
								 Mmap.put("msg", MarDis.GetUpdateMarital_DiscordJCOChild(m_child,username));//,
							 }
					  }catch(RuntimeException e){
			              try{
			                      tx.rollback();
			                      Mmap.put("msg", "roll back transaction");
			              }catch(RuntimeException rbe){
			            	      Mmap.put("msg", "Couldn?t roll back transaction " + rbe);
			              }
			              throw e;
					}finally{
						if(session1!=null){
							session1.close();
						}
					}
				return new ModelAndView("redirect:Search_Marital_DiscordJCO");
			}
		 
		 @RequestMapping(value = "/Appove_Marital_DiscordJCO", method = RequestMethod.POST)
	 		public ModelAndView Appove_Marital_DiscordJCO(@ModelAttribute("id3") String updateid,ModelMap Mmap,
	 				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
	 				HttpSession sessionEdit,HttpServletRequest request) {
			 
			 String  roleid = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
			 	if(val == false) {
					return new ModelAndView("AccessTiles");
				}
			    if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
	 	    	TB_PSG_MARITAL_DISCORD_JCO authDetails = MarDis.getmaritalByid(Integer.parseInt(updateid));
	 				 Mmap.put("App_Maritial_Discord_CMD", authDetails);
	 				 List<TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO> Status_case = get_StatusOfCase(Integer.parseInt(updateid));
	 				 Mmap.put("Status_case", Status_case.get(0).getStatus_of_case());
	 				 Mmap.put("msg", msg);
	 			return new ModelAndView("Approve_Marital_DiscordJCO_tiles");
	 		}
		 
		 @RequestMapping(value = "/Approve_MaritalDisJCO", method = RequestMethod.POST)
			public @ResponseBody ModelAndView Approve_MaritalDisJCO(@ModelAttribute("id3") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
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
					 String username = session.getAttribute("username").toString();
					String hqlUpdate2 = "update from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set status='2',modified_by=:modified_by,modified_date=:modified_date"
							+ " where marital_id=:id and status='1' ";
					int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
							.setDate("modified_date", new Date()).executeUpdate();
					String hqlUpdate = "update from TB_PSG_MARITAL_DISCORD_JCO set status='1' where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					String hqlUpdate1 = "update from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set status='1',modified_by=:modified_by,modified_date=:modified_date"
							+ " where marital_id=:id and status='0' ";
					int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).setString("modified_by", username)
							.setDate("modified_date", new Date()).executeUpdate();
					TB_PSG_MARITAL_DISCORD_JCO com = (TB_PSG_MARITAL_DISCORD_JCO)sessionHQL.get(TB_PSG_MARITAL_DISCORD_JCO.class, id);
					jcoComm.update_miso_role_hdr_status_jco(com.getJco_id(),1,username,"marital_dis_status");
					tx.commit();
					sessionHQL.close();
					if (app > 0 && app1 > 0) {
						liststr.add("Approved Successfully.");
					} else {
						liststr.add("Not Approved.");
					}
					model.put("msg",liststr.get(0));
				} catch (Exception e) {
					liststr.add("CAN NOT APPROVED THIS DATA BECAUSE IT IS ALREADY APPROEVED.");
					model.put("msg",liststr.get(0));
				}
				return new ModelAndView("redirect:Search_Marital_DiscordJCO");
			}
		 
		 @RequestMapping(value = "/View_Appove_Marital_DiscordJCO", method = RequestMethod.POST)
			public ModelAndView View_Appove_Marital_DiscordJCO(@ModelAttribute("id5") String updateid,ModelMap Mmap,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication,
					HttpSession sessionEdit) {
			 		 TB_PSG_MARITAL_DISCORD_JCO authDetails = MarDis.getmaritalByid(Integer.parseInt(updateid));
					 Mmap.put("View_App_Maritial_Discord_CMD", authDetails);
					 List<ArrayList<String>> list = MarDis.Marital_DiscordJCO_History(updateid);
					 Mmap.put("list", list);
					 Mmap.put("msg", msg);
				return new ModelAndView("ViewApprove_Marital_DiscordJCO_tiles");
			}
		 
		 @RequestMapping(value = "/Reject_Marital_DiscordJCO", method = RequestMethod.POST)
			public @ResponseBody ModelAndView Reject_Marital_DiscordJCO(@ModelAttribute("id4") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model, HttpSession session,
					@RequestParam(value = "reject_remarks_md", required = false) String reject_remarks,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 
			 String  roleid = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
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
					 String username = session.getAttribute("username").toString();
					String hqlUpdate = "update from TB_PSG_MARITAL_DISCORD_JCO set status='3',modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setString("modified_by", username).setString("reject_remarks", reject_remarks)
							.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();
					String hqlUpdate1 = "update from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set status='3',modified_by=:modified_by,modified_date=:modified_date"
							+ " where marital_id=:id and status='0' ";
					int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).setString("modified_by", username)
							.setDate("modified_date", new Date()).executeUpdate();
					TB_PSG_MARITAL_DISCORD_JCO com = (TB_PSG_MARITAL_DISCORD_JCO)sessionHQL.get(TB_PSG_MARITAL_DISCORD_JCO.class, id);
					jcoComm.update_miso_role_hdr_status_jco(com.getJco_id(),0,username,"marital_dis_status");
					tx.commit();
					sessionHQL.close();
					if (app > 0 && app1 > 0) {
						liststr.add("Rejected Successfully.");
					} else {
						liststr.add("Not Rejected.");
					}
					model.put("msg",liststr.get(0));
				} catch (Exception e) {
					liststr.add("CAN NOT REJECT THIS DATA BECAUSE IT IS ALREADY REJECTED.");
					model.put("msg",liststr.get(0));
				}
				return new ModelAndView("redirect:Search_Marital_DiscordJCO");
			}
		 
		 @RequestMapping(value = "/Popup_Marital_DiscordJCOUrl", method = RequestMethod.POST)
		 public ModelAndView Popup_Marital_DiscordJCOUrl(ModelMap Mmap, HttpSession session,
				 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
				 @RequestParam(value = "m_jco_id", required = false) int m_jco_id)  {
				  List<ArrayList<String>> list = MarDis.Popup_Marital_DiscordJCO_History(m_jco_id);
				 Mmap.put("list", list);
			 Mmap.put("msg", msg);
			return new ModelAndView("Popup_Marital_DiscordJCO_tiles");
		 }
		 
		 
		 @RequestMapping(value = "/Appove_Marital_DiscordJCO_Cancel", method = RequestMethod.POST)
		 public ModelAndView Appove_Marital_DiscordJCO_Cancel(@RequestParam(value = "id_ac", required = false) String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession sessionEdit) {	
			 Mmap.put("listp", MarDis.getMarital_DiscordByidJCO(Integer.parseInt(id)));
		 		Mmap.put("listch", MarDis.getMarital_DiscordChildByidJCO(Integer.parseInt(id)));
		 	Mmap.put("msg", msg);
		 	return new ModelAndView("Appr_Marital_Discord_historyJCOTiles");
		 }
		 
		 @RequestMapping(value = "/View_Marital_DiscordJCO_history", method = RequestMethod.POST)
		 public ModelAndView View_Marital_DiscordJCO_history(@ModelAttribute("id7") String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession sessionEdit) {	
		 		Mmap.put("listp", MarDis.getMarital_DiscordByidJCO(Integer.parseInt(id)));
		 		Mmap.put("listch", MarDis.getMarital_DiscordChildByidJCO(Integer.parseInt(id)));
		 	Mmap.put("msg", msg);
		 	return new ModelAndView("View_Maritial_Discord_historyJCOTiles");
		 }
		 
		 @RequestMapping(value = "/Reject_Cancel_Maritial_Discord_JCO", method = RequestMethod.POST)
		 public ModelAndView Reject_Cancel_Maritial_Discord_JCO(@ModelAttribute("id_rj") String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession session) {	
		 	String username = session.getAttribute("username").toString();
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	try {
		 		String hql = "update TB_PSG_MARITAL_DISCORD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  id=:id";
		 		Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
		 		query.executeUpdate();
		 		///child
		 		String hql2 = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  marital_id=:id";
		 		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
		 		query2.executeUpdate();
		 		tx.commit();
		 		msg = "Data Rejected Successfully";
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 	}
		 	Mmap.put("msg", msg);
		 	return new ModelAndView("redirect:Search_Marital_DiscordJCO");
		 }
		 
		 @RequestMapping(value = "/Appr_Cancel_Maritial_Discord_JCO", method = RequestMethod.POST)
		 public ModelAndView Appr_Cancel_Maritial_Discord_JCO(@ModelAttribute("id7") String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession session) {	
		 	String username = session.getAttribute("username").toString();
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	try {
		 		Query q0 = sessionhql.createQuery("select count(id) from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where cancel_status in (-1,2) and marital_id =:p_id");
		 		q0.setParameter("p_id", Integer.parseInt(id));  
		 		Long c = (Long) q0.uniqueResult();
		 		if (c==0) {
		 			String hql = "update TB_PSG_MARITAL_DISCORD_JCO set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
			 				+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by where  id=:id";
			 		Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
			 				.setParameter("cancel_status", 1).setParameter("status", -1).setTimestamp("modified_date", new Date())
			 				.setString("cancel_by", username).setTimestamp("cancel_date", new Date());
			 		query.executeUpdate();
		 		}
		 		///child
		 		String hql2 = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
		 				+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by where  marital_id=:id";
		 		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 1).setParameter("status", -1).setTimestamp("modified_date", new Date())
		 				.setString("cancel_by", username).setTimestamp("cancel_date", new Date());
		 		query2.executeUpdate();
		 		tx.commit();
		 		msg = "Data Approved Successfully";
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 	}
		 	Mmap.put("msg", msg);
		 	return new ModelAndView("redirect:Search_Marital_DiscordJCO");
		 }
		 
		 @RequestMapping(value = "/delete_Marital_DiscordJCO", method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_Marital_DiscordJCO(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
			 
			 String  roleid = sessionA.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Search_Marital_DiscordJCO", roleid);	
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
					String hqlUpdate = "delete from TB_PSG_MARITAL_DISCORD_JCO where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
					String hqlUpdate1 = "delete from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where marital_id=:id";
					int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("id", id).executeUpdate();
					tx.commit();
					sessionHQL.close();
					if (app > 0 && app1>0) {
						liststr.add("Delete Successfully.");
					} else {
						liststr.add("Delete UNSuccessfully.");
					}
					model.put("msg",liststr.get(0));
				} catch (Exception e) {
					liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					model.put("msg",liststr.get(0));
				}
				return new ModelAndView("redirect:Search_Marital_DiscordJCO");
			}
		 
		 @RequestMapping(value = "/Cancel_Maritial_Discord_ChildFromView_JCO", method = RequestMethod.POST)
		 public ModelAndView Cancel_Maritial_Discord_ChildFromView_JCO(@RequestParam(value = "id_ch", required = false) String ch_id,
		 		@RequestParam(value = "id_p", required = false) String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession session) {	
		 	String redirr = "View_Maritial_Discord_historyJCOTiles";
		 	String username = session.getAttribute("username").toString();
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	try {
		 		Query q0 = sessionhql.createQuery("select count(id) from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where cancel_status in (-1,2) and marital_id =:p_id");
		 		q0.setParameter("p_id", Integer.parseInt(id));  
		 		Long c = (Long) q0.uniqueResult();
		 		Query q01 = sessionhql.createQuery("select count(id) from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where cancel_status in (-1,2) and status=1 and marital_id =:p_id");
		 		q01.setParameter("p_id", Integer.parseInt(id));  
		 		Long c1 = (Long) q01.uniqueResult();
		 		if (c1<=1) {
		 			redirr = "redirect:Search_Marital_DiscordJCO";
		 		}
		 		if (c<=1) {
		 			redirr = "redirect:Search_Marital_DiscordJCO";
		 			String hql = "update TB_PSG_MARITAL_DISCORD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 					+ "cancel_status=:cancel_status  where  id=:id";
		 			Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 					.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		 			query.executeUpdate();
		 		}
		 		///child
		 		String hql2 = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  id=:id";
		 		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(ch_id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		 		query2.executeUpdate();
		 		tx.commit();
		 		msg = "Data Cancelled Successfully";
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 	}
		 	Mmap.put("listp", MarDis.getMarital_DiscordByidJCO(Integer.parseInt(id)));
	 		Mmap.put("listch", MarDis.getMarital_DiscordChildByidJCO(Integer.parseInt(id)));
		 	Mmap.put("msg", msg);
		 	return new ModelAndView(redirr);
		 }
		 
		 
		 @RequestMapping(value = "/Cancel_Marital_DiscordfromView_JCO", method = RequestMethod.POST)
		 public ModelAndView Cancel_Marital_DiscordfromView_JCO(@ModelAttribute("id7") String id,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession session) {	
		 	String username = session.getAttribute("username").toString();
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	try {
		 		String hql = "update TB_PSG_MARITAL_DISCORD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  id=:id";
		 		Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		 		query.executeUpdate();
		 		///child
		 		String hql2 = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  marital_id=:id";
		 		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		 		query2.executeUpdate();
		 		tx.commit();
		 		msg = "Data Cancelled Successfully";
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 	}
		 	Mmap.put("msg", msg);
		 	return new ModelAndView("redirect:Search_Marital_DiscordJCO");
		 }
		 
		 @RequestMapping(value = "/RejectCh_Cancel_Maritial_DiscordJCO_dat", method = RequestMethod.POST)
		 public ModelAndView RejectCh_Cancel_Maritial_DiscordJCO_dat(@RequestParam(value = "id_rjCh", required = false) String ch_id,
		 		@RequestParam(value = "id_rjP", required = false) String id,
		 		@RequestParam(value = "cat_rjCh", required = false) String category,
		 		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		 		HttpSession session) {	
		 	String redirr = "Appr_Marital_Discord_historyJCOTiles";
		 	String username = session.getAttribute("username").toString();
		 	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		 	Transaction tx = sessionhql.beginTransaction();
		 	try {
		 		Query q0 = sessionhql.createQuery("select count(id) from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where cancel_status in (-1,2) and marital_id =:p_id");
		 		q0.setParameter("p_id", Integer.parseInt(id));  
		 		Long c = (Long) q0.uniqueResult();
		 		Query q01 = sessionhql.createQuery("select count(id) from TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO where cancel_status=0 and marital_id =:p_id");
		 		q01.setParameter("p_id", Integer.parseInt(id));  
		 		Long c1 = (Long) q01.uniqueResult();
		 		if (c1<=1) {
		 			redirr = "redirect:Search_Marital_DiscordJCO";
		 		}
		 		if (c==0 && c1<=1) {
		 			redirr = "Search_Field_ServicelTiles";
		 			String hql = "update TB_PSG_MARITAL_DISCORD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 					+ "cancel_status=:cancel_status  where  id=:id";
		 			Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
		 					.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
		 			query.executeUpdate();
		 		}
		 		///child
		 		String hql2 = "update TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO set modified_by=:modified_by ,modified_date=:modified_date, "
		 				+ "cancel_status=:cancel_status  where  id=:id";
		 		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(ch_id)).setString("modified_by", username)
		 				.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
		 		query2.executeUpdate();
		 		tx.commit();
		 		msg = "Data Rejected Successfully";
		 	}catch (Exception e) {
		 		// TODO: handle exception
		 		tx.rollback();
		 	}
		 	Mmap.put("listp", MarDis.getMarital_DiscordByidJCO(Integer.parseInt(id)));
	 		Mmap.put("listch", MarDis.getMarital_DiscordChildByidJCO(Integer.parseInt(id)));
		 	Mmap.put("msg", msg);
		 
		 	return new ModelAndView(redirr);
		 }
}
