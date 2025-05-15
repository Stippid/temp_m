package com.controller.psg.Transaction;

import java.math.BigInteger;
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
import com.controller.ExportFile.PDF_battleCasualty;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.BattleCADAO;
import com.models.psg.Transaction.TB_PSG_DECLARED_BA_CA;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})
public class BattleCasualtyController {
	PsgDashboardController das = new PsgDashboardController();
	
	
	Psg_CommonController p_comm = new Psg_CommonController();
	
	ValidationController valid = new ValidationController();
	
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	BattleCADAO bcd;
	
	@RequestMapping(value = "/admin/BattleCasulaty_url", method = RequestMethod.GET)
	 public ModelAndView BattleCasulaty_url(ModelMap Mmap, HttpSession session,
			 @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("BattleCasulaty_url", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 Mmap.put("msg", msg);
		return new ModelAndView("BattleCasulaty_Tiles");
	 }
	
	@RequestMapping(value = "/admin/battle_casualtyAction", method = RequestMethod.POST)
	public @ResponseBody String battle_casualtyAction(ModelMap Mmap, HttpSession session, HttpServletRequest request) throws ParseException
			 {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
		String personnel_no = request.getParameter("personnel_no").trim();
		String authority = request.getParameter("authority").trim();
		String date_of_authority = request.getParameter("date_of_authority").trim();
		String date_of_casualty = request.getParameter("casualty").trim();
		String status_by_mp = request.getParameter("status_by_mp").trim();
    	if(personnel_no == null || personnel_no.equals("")){ 
			return "Please Select Personel  No.";
		}
    	Date authority_date = null;
    	Date casualty_date = null;
		String Sen_id = request.getParameter("Sen_id");
		/*int status_by_mp = 0;
		if (Integer.parseInt(request.getParameter("status_by_mp")) != 0) {
			status_by_mp = Integer.parseInt(request.getParameter("status_by_mp"));
		}*/
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger (request.getParameter("comm_id"));
		}
		
		if (personnel_no.equals("")) {
			return "Please Enter Personnel No";
		}
		if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
		    return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		}		    	  
		if (personnel_no.length() < 7 || personnel_no.length() > 9) {
		  return "Personal No Should Contain Maximum 9 Character";
		}
		if (authority.equals("")) {
			return "Please Enter Authority";
		} 
		if (!valid.isValidAuth(authority)) {
	 		return valid.isValidAuthMSG + " Authority ";	
		}
 		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
 			return " Authority " + valid.isValidLengthMSG;	
		}
		if (date_of_casualty == null || date_of_casualty.equals("null") || date_of_casualty.equals("DD/MM/YYYY") || date_of_casualty.equals("")) {
			return "Please Select Date of Casualty";
		}
		if (!valid.isValidDate(date_of_casualty)) {
 			return valid.isValidDateMSG + " of Casualty";	
		}
		if (!date_of_casualty.equals("") && !date_of_casualty.equals("DD/MM/YYYY")) {
			casualty_date = format.parse(date_of_casualty);
		}		
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY") || date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!valid.isValidDate(date_of_authority)) {
 			return valid.isValidDateMSG + " of Authority";	
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			authority_date = format.parse(date_of_authority);
		}
		
		if (Integer.parseInt(Sen_id) == 0) {
		String hqlUpdate_s2="select count(id) from TB_PSG_DECLARED_BA_CA where comm_id=:comm_id and status in (0)";
		
		Query query_s2 = sessionhql.createQuery(hqlUpdate_s2).setBigInteger("comm_id", comm_id)
				.setMaxResults(1);
		Long c22 = (Long)  query_s2.uniqueResult();
		if(c22!=null && c22>0) {
		return " Data Already Exist. ";
		}
		}
		String msg = "";
		try {
		
			if (Integer.parseInt(Sen_id) == 0) {
				TB_PSG_DECLARED_BA_CA cs = new TB_PSG_DECLARED_BA_CA();
				cs.setComm_id(comm_id);
				cs.setStatus_by_mp(status_by_mp);
				cs.setAuthority(authority);
				cs.setDate_of_authority(authority_date);
				cs.setDate_of_casualty(casualty_date);
				cs.setCreated_by(username);
				cs.setCreated_date(new Date());
				cs.setStatus(0);

				int id = (int) sessionhql.save(cs);
				msg = String.valueOf(id);
				
			} else {

				String hql = "update TB_PSG_DECLARED_BA_CA set authority=:authority,date_of_authority=:date_of_authority,date_of_casualty=:date_of_casualty,status_by_mp=:status_by_mp,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  id=:id";
				Query query = sessionhql.createQuery(hql).setString("authority", authority).setDate("date_of_authority",authority_date)
						.setDate("date_of_casualty", casualty_date)
						.setString("status_by_mp", status_by_mp)
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
	
	@RequestMapping(value = "/admin/Search_BattleCAUrl" , method = RequestMethod.GET)
	 public ModelAndView Search_BattleCAUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		 Mmap.put("msg", msg);
		 Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();

		String roleAccess = session.getAttribute("roleAccess").toString();

			if (roleAccess.equals("Unit")) {
				Mmap.put("sus_no", roleSusNo);
				Mmap.put("unit_name",m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}

		Mmap.put("list",bcd.Search_BattleCA("", "0", "", "", "","","", roleSusNo, roleType));
		Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 return new ModelAndView("Search_BattleCATiles");
	 }
	
	@RequestMapping(value = "/admin/GetSearch_BattleCA", method = RequestMethod.POST)
	public ModelAndView GetSearch_BattleCA(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "personnel_no1", required = false) String personnel_no,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "rank1", required = false) String rank,
	   		@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "cr_by1", required = false) String cr_by,
	        @RequestParam(value = "cr_date1", required = false) String cr_date
			) {
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

			String roleType = session.getAttribute("roleType").toString();
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			String roleAccess = session.getAttribute("roleAccess").toString();
			
			unit_name = unit_name.replace("&#40;", "(");
			unit_name = unit_name.replace("&#41;", ")");
			
			
			if(unit_sus_no!="") {
		    	  if (!valid.SusNoLength(unit_sus_no)) {
						Mmap.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_BattleCAUrl");
					}
		    	  
		    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
						return new ModelAndView("redirect:Search_BattleCAUrl");
					}
		      }
			 
			 if(unit_name!="") {
				  if (!valid.isUnit(unit_name)) {
					  Mmap.put("msg", " Unit Name " + valid.isUnitMSG);
						return new ModelAndView("redirect:Search_BattleCAUrl");
					}
		    	  
//		    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//						Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//						return new ModelAndView("redirect:Search_BattleCAUrl");
//					}
		      }
			 
			   if(personnel_no!="") {
				   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
						return new ModelAndView("redirect:Search_BattleCAUrl");
					}
				  
				      
				      if (personnel_no.length() < 7 || personnel_no.length() > 9) {
							Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
							return new ModelAndView("redirect:Search_BattleCAUrl");
						}
			     } 


			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name", m.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, session) .get(0));


			}
		ArrayList<ArrayList<String>> list = bcd.Search_BattleCA(personnel_no, status,unit_sus_no, unit_name, rank, cr_by,cr_date, roleSusNo, roleType);
				
			Mmap.put("list", list);
			Mmap.put("size", list.size());
			Mmap.put("personnel_no1", personnel_no);
			Mmap.put("status1", status);
			Mmap.put("rank1", rank);
			Mmap.put("unit_name1", unit_name);
			Mmap.put("unit_sus_no1", unit_sus_no);
			Mmap.put("getTypeofRankList", p_comm.getTypeofRankList());
			Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
			Mmap.put("cr_date1", cr_date);
			Mmap.put("cr_by1", cr_by);
		return new ModelAndView("Search_BattleCATiles");
	}
	
	
	@RequestMapping(value = "/Approve_BattleCA" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_BattleCA(@ModelAttribute("idA") BigInteger id, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg) {
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", sessionA.getAttribute("roleid").toString());
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
				
			String hqlUpdate = "update TB_PSG_DECLARED_BA_CA set status=:status,modified_by=:modified_by,modified_date=:modified_date  where comm_id=:id and status='0'";
			
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1)
					
					.setString("modified_by", username)
					.setDate("modified_date", new Date())
					.setBigInteger("id", id).executeUpdate();					
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
		return new ModelAndView("redirect:Search_BattleCAUrl");
	}
	
	@RequestMapping(value = "/Reject_BatlleCA" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_BatlleCA(@ModelAttribute("idR") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "rej_remarksR", required = false) String reject_remarks,Authentication authentication) {
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", sessionA.getAttribute("roleid").toString());
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
								 
			String hqlUpdate = "update TB_PSG_DECLARED_BA_CA set status=:status,reject_remarks=:reject_remarks,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3)
					.setString("reject_remarks", reject_remarks)
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
		return new ModelAndView("redirect:Search_BattleCAUrl");
	}
	
	@RequestMapping(value = "/Edit_Search_BattleCA",method = RequestMethod.POST)
	public ModelAndView Edit_Search_BattleCA(@ModelAttribute("id2") String updateid,
			@ModelAttribute("personnel_no_e") String personnel_no,
			@ModelAttribute("personnel_no5") String personnel_no5, 
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", sessionEdit.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		TB_PSG_DECLARED_BA_CA authDetails = bcd.getSearch_BattleCAByid(Integer.parseInt(updateid));
		
		Mmap.put("msg", msg);
		Mmap.put("Edit_battleCMD", authDetails);
		
		Mmap.put("personnel_no5", personnel_no);
		return new ModelAndView("Edit_BattleCATiles");
	}
	
	@RequestMapping(value = "/Delete_BattleCA" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_BattleCA(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		Boolean val = roledao.ScreenRedirect("Search_BattleCAUrl", sessionA.getAttribute("roleid").toString());
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
			 
			String hqlUpdate = "delete from TB_PSG_DECLARED_BA_CA where id=:id";
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
		return new ModelAndView("redirect:Search_BattleCAUrl");
	}
	
	@RequestMapping(value = "/Download_BattleCA",method = RequestMethod.POST)
	public ModelAndView Download_BattleCA(
			@ModelAttribute("personnel_no_d") String personnel_no,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		
		
		
	
		ArrayList<ArrayList<String>> pdfprint = bcd.pdf_bettal(personnel_no);
		
		String username =  sessionEdit.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="";
		Date date = new Date();
		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
		return new ModelAndView(new PDF_battleCasualty(Heading,pdfprint,foot,username));
	}

}
