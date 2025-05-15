package com.controller.psg.Jco_Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.controller.ExportFile.PDF_Certify_BCPC;
import com.controller.ExportFile.PDF_battleCasualty;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Transaction.Certify_BCPC_DAO;
import com.models.psg.Jco_Transaction.TB_PSG_CERTIFY_BC_PC_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class Certify_BCPC_JCO_Controller {
	
	
	psg_jco_CommonController pjco = new psg_jco_CommonController();
	PsgDashboardController das = new PsgDashboardController();
	ValidationController valid = new ValidationController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	Certify_BCPC_DAO bcpc;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Certify_BC_PC_Url_JCO", method = RequestMethod.GET)
	 public ModelAndView Certify_BC_PC_Url_JCO(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		 String  roleid = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Certify_BC_PC_Url_JCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
		 Mmap.put("msg", msg);
		return new ModelAndView("Certify_BC_PC_Url_JCOTiles");
	 }
	
	@RequestMapping(value = "/admin/certify_BCPC_JCOAction", method = RequestMethod.POST)
	public @ResponseBody String certify_BCPC_JCOAction(ModelMap Mmap, HttpSession session, HttpServletRequest request) throws ParseException
			 {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		String personnel_no = request.getParameter("personnel_no");
		String authority = request.getParameter("authority").trim();
		String date_of_authority = request.getParameter("date_of_authority").trim();
		String date_of_casualty = request.getParameter("casualty").trim();
        
  
    	Date authority_date = null;
    	Date casualty_date = null;
		String Sen_id = request.getParameter("Sen_id");
		
		int jco_id = 0;
	
		if (personnel_no == null || personnel_no.equals("")) {
			return "Please Enter Army No";
		   }
			 if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					return valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No";
				}
			 if (personnel_no.length() < 2 || personnel_no.length() > 12) {
				 return "Army No Should Contain Maximum 12 Character";
			 }
		if (authority.equals("") || authority == null) {
			return "Please Enter Authority";
		} 
		if (!valid.isValidAuth(authority)) {
			return valid.isValidAuthMSG + " Authority";
		}	
		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return "Authority " + valid.isValidLengthMSG;	
		}
		
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {

			return "Please Select Date of Authority";
		}
		
		else if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		else {
			authority_date = format.parse(date_of_authority);
		}
		
		
		if (date_of_casualty == null || date_of_casualty.equals("null") || date_of_casualty.equals("DD/MM/YYYY") || date_of_casualty.equals("")) {

			return "Please Select Date of Casualty";
		}
		else if (!valid.isValidDate(date_of_casualty)) {
 			return valid.isValidDateMSG + " of Casualty";	
		}
		else {
			casualty_date = format.parse(date_of_casualty);
		}
		if (Integer.parseInt(request.getParameter("jco_id")) != 0) {
			jco_id = Integer.parseInt(request.getParameter("jco_id"));
		}

		
		
		if (Integer.parseInt(Sen_id) == 0) {
		String hqlUpdate_s2="select count(id) from TB_PSG_CERTIFY_BC_PC_JCO where jco_id=:jco_id and status in (0)";
		
		Query query_s2 = sessionhql.createQuery(hqlUpdate_s2).setInteger("jco_id", jco_id)
//				.setTimestamp("eff_date_of_seniority", format.parse(date_of_applicability))
				.setMaxResults(1);
		Long c22 = (Long)  query_s2.uniqueResult();
		if(c22!=null && c22>0) {
		return " Data Already Exist. ";
		}
		}
		String msg = "";
		try {
		
			if (Integer.parseInt(Sen_id) == 0) {
				TB_PSG_CERTIFY_BC_PC_JCO cs = new TB_PSG_CERTIFY_BC_PC_JCO();
				cs.setJco_id(jco_id);
				cs.setAuthority(authority);
				cs.setDate_of_authority(authority_date);
				cs.setDate_of_casualty(casualty_date);
				cs.setCreated_by(username);
				cs.setCreated_date(new Date());
				cs.setStatus(0);

				int id = (int) sessionhql.save(cs);
				msg = String.valueOf(id);
				
			} else {

				String hql = "update TB_PSG_CERTIFY_BC_PC_JCO set authority=:authority,date_of_authority=:date_of_authority,date_of_casualty=:date_of_casualty,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority",authority_date)
						.setDate("date_of_casualty", casualty_date)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 0).setInteger("id", Integer.parseInt(Sen_id));
				msg = query.executeUpdate() > 0 ? "update" : "0";

			}
			tx.commit();
	    } catch (RuntimeException e) {
	    	e.printStackTrace();
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionhql != null) {
				sessionhql.close();
			}
	    }

		return msg;
	}
	
	@RequestMapping(value = "/admin/Search_Certify_BC_PC_JCO" , method = RequestMethod.GET)
	 public ModelAndView Search_Certify_BC_PC_JCO(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionUserId) {

		Mmap.put("msg", msg);
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String  roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Certify_BC_PC_JCO", roleid);	
		String roleAccess = session.getAttribute("roleAccess").toString();
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

		Mmap.put("list",bcpc.Search_CertifyBCPC("", "0", "", "", "","","", roleSusNo, roleType));
		 Mmap.put("getTypeofRankList", pjco.getRankjcoList());
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 return new ModelAndView("Search_Certify_BCPC_Tiles");
	 }
	
	@RequestMapping(value = "/admin/GetSearch_Cartify_BCPC", method = RequestMethod.POST)
	public ModelAndView GetSearch_Cartify_BCPC(ModelMap Mmap,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
	   		@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
		    @RequestParam(value = "cr_date1", required = false) String cr_date) {

			String roleType = session.getAttribute("roleType").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
			unit_name = unit_name.replace("&#40;", "(");
			unit_name = unit_name.replace("&#41;", ")");
			if(unit_sus_no!="") {
	            if (!valid.SusNoLength(unit_sus_no)) {
	                              Mmap.put("msg", valid.SusNoMSG);
	                              return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	                      }
	            if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
					return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
				}
	           }
	    
	        if(unit_name!="") {
	        	 if (!valid.isUnit(unit_name)) {
                     Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
                     return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
             }

//	                      if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//	                              Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//	                              return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
//	                      }
	    }
	    
	        if(personnel_no!="") {
	            if (!valid.ArmyNoLength(personnel_no)) {
	                                   Mmap.put("msg", valid.ArmyNoMSG );
	                                   return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	                           }
	            if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
					Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Army No ");
					return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
				}
	                 if (personnel_no.length() < 2 || personnel_no.length() > 12) {
	                                   Mmap.put("msg", "Army No No Should Contain Maximum 12 Character");
	                                   return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	                           }
	        } 

			

			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session) .get(0));


			}
				ArrayList<ArrayList<String>> list = bcpc.Search_CertifyBCPC(personnel_no, status,unit_sus_no, unit_name, rank,cr_by,cr_date ,roleSusNo, roleType);
				
			Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("personnel_no1", personnel_no);
			Mmap.put("status1", status);
			Mmap.put("rank1", rank);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("unit_sus_no1", unit_sus_no);
			Mmap.put("getTypeofRankList", pjco.getRankjcoList());
			Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
			Mmap.put("cr_date1", cr_date);
			Mmap.put("cr_by1", cr_by);
		return new ModelAndView("Search_Certify_BCPC_Tiles");
	}
	
	
	@RequestMapping(value = "/Edit_Certify_BCPC",method = RequestMethod.POST)
	public ModelAndView Edit_Certify_BCPC(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no_e") String personnel_no,
			@ModelAttribute("personnel_no5") String personnel_no5, 
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		 String  roleid = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Certify_BC_PC_JCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		TB_PSG_CERTIFY_BC_PC_JCO authDetails = bcpc.getCertifyBCPC_Byid(Integer.parseInt(updateid));
		
		Mmap.put("msg", msg);
		Mmap.put("Edit_battleCMD", authDetails);
		
		Mmap.put("personnel_no5", personnel_no);
		return new ModelAndView("Edit_Certify_BCPC_Tiles");
	}
	
	
/*	@RequestMapping(value = "/Download_Certify_BCPC",method = RequestMethod.POST)
	public ModelAndView Download_Certify_BCPC(
			@ModelAttribute("army_no_d") String army_no,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
	
		
		ArrayList<ArrayList<String>> pdfprint = bcpc.pdf_Certify_BCPC(army_no);
		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
		return new ModelAndView(new PDF_Certify_BCPC(Heading,pdfprint,foot,username));
	}*/
	
	
	@RequestMapping(value = "/Download_Certify_BCPC",method = RequestMethod.POST)
	public ModelAndView Download_Certify_BCPC(
			@ModelAttribute("army_no_d") String army_no,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		
		
		
	
		ArrayList<ArrayList<String>> pdfprint = bcpc.pdf_Certify_BCPC(army_no);
		
		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
		return new ModelAndView(new PDF_Certify_BCPC(Heading,pdfprint,foot,username));
	}
	
	
	@RequestMapping(value = "/Approve_Certify_BCPC" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Certify_BCPC(@ModelAttribute("idA") int id, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg) {
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Certify_BC_PC_JCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		     List<String> liststr = new ArrayList<String>();
		     String username = sessionA.getAttribute("username").toString();
		     try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();					
				
			String hqlUpdate = "update TB_PSG_CERTIFY_BC_PC_JCO set status=:status,modified_by=:modified_by,modified_date=:modified_date  where jco_id=:id and status='0'";
			
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1)
					.setString("modified_by", username)
					.setDate("modified_date", new Date())
					.setInteger("id", id).executeUpdate();	
			
			if (app > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
			model.put("msg",liststr.get(0));	
			
			tx.commit();
			sessionHQL.close();
		     } catch (Exception e) {
		    	 e.printStackTrace();
					// TODO: handle exception
				}
		return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	}
	
	@RequestMapping(value = "/Reject_Certify_BCPC" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Certify_BCPC(@ModelAttribute("idR") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "cer_rej_remarks", required = false) String reject_remarks,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Certify_BC_PC_JCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		List<String> liststr = new ArrayList<String>();

			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			
			 String username =  sessionA.getAttribute("username").toString();
								 
			String hqlUpdate = "update TB_PSG_CERTIFY_BC_PC_JCO set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("reject_remarks", reject_remarks)
					
					.setString("modified_by", username)
					.setDate("modified_date", new Date())
					.setInteger("id", id).executeUpdate();
			
			if (app > 0) {
				liststr.add("Reject Successfully.");
			} else {
				liststr.add("Reject UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));
			tx.commit();
			sessionHQL.close();
		return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	}
	
	@RequestMapping(value = "/Delete_Certify_BCPC" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Certify_BCPC(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		List<String> liststr = new ArrayList<String>();
		
		 String  roleid = sessionA.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Certify_BC_PC_JCO", roleid);	
		 	if(val == false) {
				return new ModelAndView("AccessTiles");
			}
		 	if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from TB_PSG_CERTIFY_BC_PC_JCO where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg",liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg",liststr.get(0));
		}
		return new ModelAndView("redirect:Search_Certify_BC_PC_JCO");
	}
	

}
