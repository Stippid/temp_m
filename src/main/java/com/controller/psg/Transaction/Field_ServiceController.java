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
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.update_census_data.Field_serviceDAO;
import com.models.psg.Transaction.TB_FIELD_SERVICE_CH;
import com.models.psg.Transaction.TB_FIELD_SERVICE_P;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Field_ServiceController {

	@Autowired
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
	
	@Autowired
	Field_serviceDAO fd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	
	PsgDashboardController das = new PsgDashboardController();
	
		
	
	@RequestMapping(value = "/admin/Field_Service_url", method = RequestMethod.GET)
	public ModelAndView Field_Service_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("Field_Service_url", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		
		//Mmap.put("list", fd.GetSearch_field_service_data(roleSusNo, "", "0", "","","0","","", session));
		Mmap.put("status1", "0");
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		
		return new ModelAndView("Field_ServicelTiles");
	}
	
	@RequestMapping(value = "/admin/Search_Field_Service_url", method = RequestMethod.GET)
	public ModelAndView Search_Field_Service_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Field_Service_url", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		
		if(roleAccess.equals("Unit")){
			Mmap.put("sus_no",roleSusNo);
			Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
		}
		
		Mmap.put("list", fd.GetSearch_field_service_data(roleSusNo, "", "0", "0","","0","","",0,"","",session));	
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("status1", "0");
		Mmap.put("msg", msg);
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());	
		 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
		 
		
	    
		return new ModelAndView("Search_Field_ServicelTiles");
	}
	 
	 @RequestMapping(value = "/admin/GetSearch_field_service_data", method = RequestMethod.POST)
		public ModelAndView GetSearch_field_service_data(ModelMap Mmap,HttpSession session,HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "personnel_no1", required = false) String personnel_no,
				@RequestParam(value = "status1", required = false) String statusA,
				@RequestParam(value = "fd_service2", required = false) String fd_service,
				@RequestParam(value = "unit_location2", required = false) String unit_location,
				@RequestParam(value = "operation2", required = false) String operation,
				@RequestParam(value = "unit_name2", required = false) String unit_name,
			    @RequestParam(value = "unit_sus_no2", required = false) String unit_sus_no,
				@RequestParam(value = "service_category1", required = false) String service_category1,
               @RequestParam(value = "cr_by1", required = false) String cr_by,
		       @RequestParam(value = "cr_date1", required = false) String cr_date){
		   
		 
		 String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Search_Field_Service_url", roleid);

			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		 String roleSusNo = session.getAttribute("roleSusNo").toString();
		 String roleAccess = session.getAttribute("roleAccess").toString();
			
			if(roleAccess.equals("Unit")){
				Mmap.put("sus_no",roleSusNo);
				Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
			}
			
			
			if(unit_sus_no!="") {
		    	  if (!valid.SusNoLength(unit_sus_no)) {
						Mmap.put("msg", valid.SusNoMSG);
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		    	  
		    	  if (!valid.isOnlyAlphabetNumericSpaceNot(unit_sus_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No");
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		     }
			 
			 if(personnel_no!="") {
				  if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ");
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
				  
		    	  
				  if (personnel_no.length() < 7 || personnel_no.length() > 9) {
						Mmap.put("msg", "Personal No Should Contain Maximum 9 Character");
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		      }
			 
			 if(unit_name!="") {
				  if (!valid.isOnlyAlphabetNumeric(unit_name)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Name ");
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		    	  
//		    	  if (!valid.isvalidLength(unit_name, valid.nameMax, valid.nameMin)) {
//						Mmap.put("msg", "Unit Name " + valid.isValidLengthMSG);
//						return new ModelAndView("redirect:Search_Field_Service_url");
//					}
		      }
			 
			 if(unit_location!="") {
				  if (!valid.isOnlyAlphabetNumeric(unit_location)) {
						Mmap.put("msg", valid.isOnlyAlphabetNumericMSG + " Unit Location ");
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		    	  
		    	  if (!valid.isvalidLength(unit_location, valid.nameMax, valid.nameMin)) {
						Mmap.put("msg", "Unit Location " + valid.isValidLengthMSG);
						return new ModelAndView("redirect:Search_Field_Service_url");
					}
		      }
			 
			ArrayList<ArrayList<String>> list = fd.GetSearch_field_service_data(roleSusNo,personnel_no,statusA,fd_service,unit_location,operation,unit_name,unit_sus_no,Integer.parseInt(service_category1),cr_by,cr_date,session);
				Mmap.put("list", list);
				Mmap.put("size", list.size());
			
				 Mmap.put("getRoleNameList_dash", das.getRoleNameList_dash());
				Mmap.put("service_category1", service_category1);
				Mmap.put("personnel_no1", personnel_no);
				Mmap.put("fd_service2", fd_service);
				Mmap.put("unit_location2", unit_location);
				Mmap.put("operation2", operation);
				Mmap.put("status1", statusA);
				Mmap.put("unit_name2", unit_name);
				Mmap.put("unit_sus_no2", unit_sus_no);
				Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
				Mmap.put("getOprationList", p_comm.getOprationList());
				Mmap.put("getFdService", p_comm.getFdService());
				Mmap.put("cr_date1",cr_date);
			    Mmap.put("cr_by1",cr_by);
					 
					 

				
				return new ModelAndView("Search_Field_ServicelTiles");
		}
	 
	 
	 @RequestMapping(value = "/admin/field_serviceAction", method = RequestMethod.POST)
		public @ResponseBody String field_serviceAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {

		 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String sus_no = session.getAttribute("roleSusNo").toString();
			if (sus_no.equals("") || sus_no == null) {
				sus_no = request.getParameter("unit_sus_no");
			}
			
			String service_category = request.getParameter("service_category");
			String personnel_no = request.getParameter("personnel_no");
			String fd_service = request.getParameter("fd_service");
			String from_date = request.getParameter("from_date");
			String to_date = request.getParameter("to_date");
			String duration = request.getParameter("duration");
			String place = request.getParameter("place");
			String unit_location = request.getParameter("unit_location");
			String operation = request.getParameter("operation");			
			String authority = request.getParameter("authority");
			String authority_date = request.getParameter("authority_date");
			String ch_id = request.getParameter("ch_id");
			String p_id= request.getParameter("p_id");

			int census_id = 0;			
			BigInteger comm_id = BigInteger.ZERO;
			
			String rvalue = "";			
			Date from_date_d = null;
			Date to_date_da = null;
			Date authority_date_d = null;
			
		
			
			if (sus_no == null || sus_no.equals("")) {
				return "Please Enter Unit SUS Number";
			}
			if (!valid.SusNoLength(sus_no)) {
		 		return valid.SusNoMSG ;	
			}
			if (!valid.isOnlyAlphabetNumericSpaceNot(sus_no)) {
				return valid.isOnlyAlphabetNumericSpaceNotMSG ;					
			}
			if (service_category.equals("0") || service_category == null) {
				return "Please Select Category";
			}			
			if (fd_service.equals("0") || fd_service == null) {
				return "Please Select Area";
			}
			if (authority == null || authority.equals("")) {
				return "Please Enter Authority ";
			}
			if (!valid.isValidAuth(authority)) {
		 		return valid.isValidAuthMSG + " Authority";	
			}
	 		if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority " + valid.isValidLengthMSG;	
			}
	 		if (authority_date == null || authority_date.equals("null") || authority_date.equals("DD/MM/YYYY") || authority_date.equals("")) {
				return "Please Select Date of Authority";
			}
	 		else if (!valid.isValidDate(authority_date)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			else {
				authority_date_d = format.parse(authority_date);
			}

			/*if (!authority_date.equals("") && !authority_date.equals("DD/MM/YYYY")) {
				String tarr[]=authority_date.split("/");
				authority_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
				authority_date_d = format.parse(authority_date);
			}
			else {
				return "Please Select Authority Date";
			}*/
									
			if (operation.equals("0") || operation == null) {
			    return "Please Select Operation";
			}
			if (!valid.isOnlyAlphabetNumeric(unit_location)) {
				return valid.isOnlyAlphabetNumericMSG + " Unit Location ";
			}	    	  
    	    if (!valid.isvalidLength(unit_location, valid.nameMax, valid.nameMin)) {
    		    return "Unit Location " + valid.isValidLengthMSG;
		   }	    	  
		   if (personnel_no.equals("") || personnel_no == null) {
			   return "Please Enter Personnel Number";
		   } 
		   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			    return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		   }		    	  
		   if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			  return "Personal No Should Contain Maximum 9 Character";
		   }
			
		   if (from_date == null || from_date.equals("null") || from_date.equals("DD/MM/YYYY") || from_date.equals("")) {
				return "Please Select From Date";
		   }
	 	   else if (!valid.isValidDate(from_date)) {
	 			return valid.isValidDateMSG + " of From";	
		   }
		   else {
				from_date_d = format.parse(from_date);
		   }		   
		   if (to_date == null || to_date.equals("null") || to_date.equals("DD/MM/YYYY") || to_date.equals("")) {
			   if (duration.equals("-1")) {
					return "To Date Should Be Greater Than From Date";
				}
		   }	   
	 	   else if (!valid.isValidDate(to_date)) {
	 			return valid.isValidDateMSG + " of To";	
		   }
		   else {
			   to_date_da = format.parse(to_date);
		   }
			/*if (!from_date.equals("") && from_date != null && !from_date.equals("DD/MM/YYYY")) {
				String farr[]=from_date.split("/");
				from_date=farr[2]+"-"+farr[1]+"-"+farr[0];
				from_date_d = format.parse(from_date);
			}
			else {
				return "Please Select From Date";
			}
			if (!to_date.equals("") && to_date != null && !to_date.equals("DD/MM/YYYY")) {
				String tarr[]=to_date.split("/");
				to_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
				to_date_da = format.parse(to_date);
				duration = String.valueOf(p_comm.calculate_age_Month(from_date_d, to_date_da));
				if (duration.equals("-1")) {
					return "To Date Should Be Greater Than From Date";
				}
			}*/
			
			if (!valid.isOnlyAlphabetNumeric(place)) {
				return valid.isOnlyAlphabetNumericMSG + "Place";	
			}
			if (!valid.isvalidLength(place, valid.nameMax, valid.nameMin)) {
	    		    return "Place" + valid.isValidLengthMSG;
			}
			
			if (request.getParameter("census_id") != "") {
				census_id = Integer.parseInt(request.getParameter("census_id"));
			}
			
			if (request.getParameter("comm_id") != "") {
				comm_id = new BigInteger(request.getParameter("comm_id"));
			}
			
			String tb_namep="";
			String tb_namech="";
			if(Integer.parseInt(service_category) == 1) {
				tb_namep = "TB_FIELD_SERVICE_P";
				tb_namech = "TB_FIELD_SERVICE_CH";
			}

			int fsc = 0;
			int fscc = 0;

				ArrayList<String> list = fd.getFieldCombination(fd_service);
				if (list.size()>0) {
					for(int i=0; i<list.size(); i++) {
						fscc += fd.getFieldServicesCount(personnel_no, list.get(i), from_date, to_date,Integer.parseInt(ch_id));
					}
					fsc = fd.getFieldServicesCount(personnel_no, fd_service, from_date, to_date,Integer.parseInt(ch_id));
				}
				else
					fsc = fd.getFieldServicesCount(personnel_no, "", from_date, to_date,Integer.parseInt(ch_id));
			
			Long c=0l;
			if(Integer.parseInt(p_id)==0) {
				Query qc = sessionhql.createQuery("select count(id) from TB_FIELD_SERVICE_P where fd_services=:fd_services and "
						+ "authority=:authority and operation=:operation and sus_id=:sus_id");
				qc.setParameter("fd_services", Integer.parseInt(fd_service)).setParameter("authority", authority)
				.setParameter("operation", Integer.parseInt(operation)).setParameter("sus_id", sus_no);  
				c = (Long) qc.uniqueResult();
			}
			
			if (c == 0) {
				
			if (fsc==0 && fscc ==0) {
				
					TB_FIELD_SERVICE_P fsp = new TB_FIELD_SERVICE_P();
					TB_FIELD_SERVICE_CH fsch = new TB_FIELD_SERVICE_CH();
					
		
					try {
						if (Integer.parseInt(ch_id) == 0) {
		
							if(Integer.parseInt(service_category) == 1) {
								Query q2 = sessionhql.createQuery("select count(id) from TB_FIELD_SERVICE_CH where p_id=:p_id and status=0");
								q2.setParameter("p_id", Integer.parseInt(p_id));  
								Long cl = (Long) q2.uniqueResult();
								int id=Integer.parseInt(p_id);
								
								if (cl==0) {
								fsp.setFd_services(Integer.parseInt(fd_service));
								fsp.setAuthority(authority);
								fsp.setOperation(Integer.parseInt(operation));
								fsp.setUnit_location(unit_location);
								fsp.setStatus(0);
								fsp.setSus_id(sus_no);
								fsp.setCreated_by(username);
								fsp.setCreated_date(date);
								fsp.setAuthority_date(authority_date_d);
								
								id = (int) sessionhql.save(fsp);
								}
								else {
									String hql = "update TB_FIELD_SERVICE_P set modified_by=:modify_by ,modified_date=:modify_on ,"
											+ "unit_location=:unit_location, "
											+ "authority=:authority,authority_date=:authority_date,  "
											+ "operation=:operation "
											+ "where  id=:id ";
									Query query = sessionhql.createQuery(hql).setInteger("id", id)
											.setString("modify_by", username)
											.setString("unit_location", unit_location)
											.setParameter("authority", authority).setParameter("authority_date", authority_date_d)
											.setParameter("operation", Integer.parseInt(operation))
											.setTimestamp("modify_on", date);
									rvalue = query.executeUpdate() > 0 ? "update" : "0";
									
								}
								//child save
								fsch.setP_id(id);
								fsch.setPersonnel_no(personnel_no);
								fsch.setDuration(duration);
								fsch.setPlace(place);
								fsch.setStatus(0);
								fsch.setCreated_by(username);
								fsch.setCreated_date(date);
								fsch.setFrom_date(from_date_d);
								fsch.setTo_date(to_date_da);
								fsch.setCensus_id(census_id);
								fsch.setComm_id(comm_id);
								
								int chid = (int) sessionhql.save(fsch);
								
								String xp = Integer.toString(id);
								String xch = Integer.toString(chid);
								rvalue = xch+","+xp;
							}
						
						} 
						else {
							//parent update
							String hql = "update "+tb_namep+" set modified_by=:modify_by ,modified_date=:modify_on, "
									+ "unit_location=:unit_location, "
									+ "authority=:authority,authority_date=:authority_date,  "
									+ "operation=:operation,"
									+ "sus_id=:sus_no,status=:status  "
									+ "where  id=:id";
							Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(p_id)).setString("modify_by", username)
									.setString("unit_location", unit_location)
									.setParameter("authority", authority).setParameter("authority_date", authority_date_d)
									.setParameter("operation", Integer.parseInt(operation))
									.setParameter("sus_no", sus_no).setParameter("status", 0)
									.setTimestamp("modify_on", date);
		
							rvalue = query.executeUpdate() > 0 ? "update" : "0";
							//child update
							String hql2 = "update "+tb_namech+" set modified_by=:modify_by ,modified_date=:modify_on ,personnel_no=:personnel_no,"
									+ "duration=:duration,place=:place,from_date=:from_date,to_date=:to_date,"
									+ "status=:status  "
									+ "where  id=:id";
							Query query2 = sessionhql.createQuery(hql2).setParameter("personnel_no", personnel_no)
									.setInteger("id", Integer.parseInt(ch_id)).setString("modify_by", username)
									.setString("duration", duration)
									.setParameter("place", place)
									.setParameter("from_date", from_date_d).setParameter("to_date", to_date_da)
									.setParameter("status", 0)
									.setTimestamp("modify_on", date);
		
							rvalue = query2.executeUpdate() > 0 ? "update" : "0";
		
						}
						p_comm.update_miso_role_hdr_status(comm_id,0,username,"field_ser_status");
						tx.commit();
					} catch (RuntimeException e) {
						e.printStackTrace();
						try {
							tx.rollback();
							rvalue = "0";
						} catch (RuntimeException rbe) {
							rvalue = "0";
						}
		
					} finally {
						if (sessionhql != null) {
							sessionhql.close();
						}
					}
				}else {
					return "Data With Similar Dates Already Exist For This Personnel number ";
				}			
			}
			
			else {
				return "Record of this Category, Area, Authority and for this Operation is already exist. Please Add/Edit This Record From Search FD Screen";
			}
			return rvalue;
		}
	 
	 @RequestMapping(value = "/admin/field_serviceApprUpdAction", method = RequestMethod.POST)
		public @ResponseBody String field_serviceApprUpdAction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {

		 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String sus_no = session.getAttribute("roleSusNo").toString();
			String service_category = request.getParameter("service_category");
			String personnel_no = request.getParameter("personnel_no");
			String fd_service = request.getParameter("fd_service");
			String from_date = request.getParameter("from_date");
			String to_date = request.getParameter("to_date");
			String duration = request.getParameter("duration");
			String place = request.getParameter("place");
			String unit_location = request.getParameter("unit_location");
			String operation = request.getParameter("operation");
			
			String authority = request.getParameter("authority");
			String authority_date = request.getParameter("authority_date");
			String ch_id = request.getParameter("ch_id");
			String p_id= request.getParameter("p_id");
			int ch_status= Integer.parseInt(request.getParameter("ch_status"));
			int census_id = 0;		
			BigInteger comm_id = BigInteger.ZERO;
			/*if (Integer.parseInt(request.getParameter("census_id")) != 0) {
				census_id = Integer.parseInt(request.getParameter("census_id"));
			}
			
			if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
				comm_id = Integer.parseInt(request.getParameter("comm_id"));
			}*/
			
			String rvalue = "";
			
			/*Date from_date_d = null;
			Date to_date_da = null;
			Date authority_date_d = null;
			
			if (!from_date.equals("") && from_date != null && !from_date.equals("DD/MM/YYYY")) {
				String farr[]=from_date.split("/");
				from_date=farr[2]+"-"+farr[1]+"-"+farr[0];
				from_date_d = format.parse(from_date);
			}
			else {
				return "Please Select From Date";
			}
			
			if (ch_status==1) {
				
				if (!to_date.equals("") && to_date != null && !to_date.equals("DD/MM/YYYY")) {
					String tarr[]=to_date.split("/");
					to_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
					to_date_da = format.parse(to_date);
					duration = String.valueOf(p_comm.calculate_age_Month(from_date_d, to_date_da));
					if (duration.equals("-1")) {
						return "To Date Should Be Greater Than From Date";
					}
				}
				else {
					return "Please Select To Date";
				}
			}
			else if (ch_status==0) {
				
				if (personnel_no.equals("") || personnel_no == null ) {
					return "Please Select From Personnel Number";
				}
			
				if (!to_date.equals("") && to_date != null && !to_date.equals("DD/MM/YYYY") ) {
					String tarr[]=to_date.split("/");
					to_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
					to_date_da = format.parse(to_date);
				}
			}*/
			
			
			Date from_date_d = null;
			Date to_date_da = null;
			//Date authority_date_d = null;
			
		
			
			
			
			/*else {
				authority_date_d = format.parse(authority_date);
			}*/

			/*if (!authority_date.equals("") && !authority_date.equals("DD/MM/YYYY")) {
				String tarr[]=authority_date.split("/");
				authority_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
				authority_date_d = format.parse(authority_date);
			}
			else {
				return "Please Select Authority Date";
			}*/
									
			
			    
    	    
    	    if (ch_status==0) {  
		   if (personnel_no.equals("") || personnel_no == null) {
			   return "Please Enter Personnel Number";
		   } 
		   if (!valid.isOnlyAlphabetNumericSpaceNot(personnel_no)) {
			    return valid.isOnlyAlphabetNumericSpaceNotMSG + " Personal No ";
		   }		    	  
		   if (personnel_no.length() < 7 || personnel_no.length() > 9) {
			  return "Personal No Should Contain Maximum 9 Character";
		   }
			
		   if (from_date == null || from_date.equals("null") || from_date.equals("DD/MM/YYYY") || from_date.equals("")) {
				return "Please Select From Date";
		   }
	 	   else if (!valid.isValidDate(from_date)) {
	 			return valid.isValidDateMSG + " of From";	
		   }
		   else {
				from_date_d = format.parse(from_date);
		   }
		   
    	    }
		   
		   if (ch_status==1) {
		   if (to_date == null || to_date.equals("null") || to_date.equals("DD/MM/YYYY") || to_date.equals("")) {
			   if (duration.equals("-1")) {
					return "To Date Should Be Greater Than From Date";
				}
		   }	   
	 	   else if (!valid.isValidDate(to_date)) {
	 			return valid.isValidDateMSG + " of To";	
		   }
		   else {
			   to_date_da = format.parse(to_date);
		   }
		   }
			/*if (!from_date.equals("") && from_date != null && !from_date.equals("DD/MM/YYYY")) {
				String farr[]=from_date.split("/");
				from_date=farr[2]+"-"+farr[1]+"-"+farr[0];
				from_date_d = format.parse(from_date);
			}
			else {
				return "Please Select From Date";
			}
			if (!to_date.equals("") && to_date != null && !to_date.equals("DD/MM/YYYY")) {
				String tarr[]=to_date.split("/");
				to_date=tarr[2]+"-"+tarr[1]+"-"+tarr[0];
				to_date_da = format.parse(to_date);
				duration = String.valueOf(p_comm.calculate_age_Month(from_date_d, to_date_da));
				if (duration.equals("-1")) {
					return "To Date Should Be Greater Than From Date";
				}
			}*/
			
			if (!valid.isOnlyAlphabetNumeric(place)) {
				return valid.isOnlyAlphabetNumericMSG + "Place";	
			}
			if (!valid.isvalidLength(place, valid.nameMax, valid.nameMin)) {
	    		    return "Place" + valid.isValidLengthMSG;
			}
			
			if (request.getParameter("census_id") != "") {
				census_id = Integer.parseInt(request.getParameter("census_id"));
			}
			
			if (request.getParameter("comm_id") != "") {
				comm_id = new BigInteger(request.getParameter("comm_id"));
			}
			
			String tb_namep="";
			String tb_namech="";
			if(Integer.parseInt(service_category) == 1) {
				tb_namep = "TB_FIELD_SERVICE_P";
				tb_namech = "TB_FIELD_SERVICE_CH";
			}
			int fsc = 0;
			int fscc = 0;
			
			//if(Integer.parseInt(ch_id)==0 || ch_status==1) {
				ArrayList<String> list = fd.getFieldCombination(fd_service);
				if (list.size()>0) {
					for(int i=0; i<list.size(); i++) {
						fscc += fd.getFieldServicesCount(personnel_no, list.get(i), from_date, to_date,Integer.parseInt(ch_id));
					}
					fsc = fd.getFieldServicesCount(personnel_no, fd_service, from_date, to_date,Integer.parseInt(ch_id));
				}
				else
					fsc = fd.getFieldServicesCount(personnel_no, "", from_date, to_date,Integer.parseInt(ch_id));
			//}
			
			if (fsc==0 && fscc ==0) {
			TB_FIELD_SERVICE_P fsp = new TB_FIELD_SERVICE_P();
			TB_FIELD_SERVICE_CH fsch = new TB_FIELD_SERVICE_CH();

			try {
				if (Integer.parseInt(ch_id) == 0) {

					if(Integer.parseInt(service_category) == 1) {
						
						String hql = "update "+tb_namep+" set modified_by=:modify_by ,modified_date=:modify_on, "
								+ "status=:status  where  id=:id";
						Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(p_id)).setString("modify_by", username)
								.setParameter("status", 0).setTimestamp("modify_on", date);

						query.executeUpdate();
						//child save
						fsch.setP_id(Integer.parseInt(p_id));
						fsch.setPersonnel_no(personnel_no);
						fsch.setDuration(duration);
						fsch.setPlace(place);
						fsch.setStatus(0);
						fsch.setCreated_by(username);
						fsch.setCreated_date(date);
						fsch.setFrom_date(from_date_d);
						fsch.setTo_date(to_date_da);
						fsch.setCensus_id(census_id);
						fsch.setComm_id(comm_id);
						
						int chid = (int) sessionhql.save(fsch);
						
						rvalue = Integer.toString(chid);
					}
					
				} 
				else {
					//parent update
					String hql = "update "+tb_namep+" set modified_by=:modify_by ,modified_date=:modify_on, "
							+ "unit_location=:unit_location, "
							+ "operation=:operation,"
							+ "status=:status  "
							+ "where  id=:id";
					Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(p_id)).setString("modify_by", username)
							.setString("unit_location", unit_location)
							.setParameter("operation", Integer.parseInt(operation))
							.setParameter("status", 0)
							.setTimestamp("modify_on", date);

					rvalue = query.executeUpdate() > 0 ? "update" : "0";
					//child update
					String hql2 = "update "+tb_namech+" set modified_by=:modify_by ,modified_date=:modify_on ,personnel_no=:personnel_no,"
							+ "duration=:duration,place=:place,from_date=:from_date,to_date=:to_date,"
							+ "status=:status  "
							+ "where  id=:id";
					Query query2 = sessionhql.createQuery(hql2).setParameter("personnel_no", personnel_no)
							.setInteger("id", Integer.parseInt(ch_id)).setString("modify_by", username)
							.setString("duration", duration)
							.setParameter("place", place)
							.setParameter("from_date", from_date_d).setParameter("to_date", to_date_da)
							.setParameter("status", 0)
							.setTimestamp("modify_on", date);

					rvalue = query2.executeUpdate() > 0 ? "update" : "0";

				}

				tx.commit();
			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
					tx.rollback();
					rvalue = "0";
				} catch (RuntimeException rbe) {
					rvalue = "0";
				}

			} finally {
				if (sessionhql != null) {
					sessionhql.close();
				}
			}
			}else {
				return "Data With Similar Dates Already Exist For This Personnel number ";
			}
			
			

			// sessionhql.close();

			return rvalue;
		}
	
	 @RequestMapping(value = "/admin/getFieldService", method = RequestMethod.POST)
		public @ResponseBody List<TB_FIELD_SERVICE_P> getFieldService(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			String sus_no = session.getAttribute("roleSusNo").toString();
			
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("q_id"));
			String hqlUpdate = " from TB_FIELD_SERVICE_P where id=:id  and (status=0 or status=3)";
			Query query = sessionHQL.createQuery(hqlUpdate).setParameter("id", id);
			List<TB_FIELD_SERVICE_P> list = (List<TB_FIELD_SERVICE_P>) query.list();
			tx.commit();
			sessionHQL.close();
		
			return list;
		}
		
	@RequestMapping(value = "/admin/getFieldServicech", method = RequestMethod.POST)
	public @ResponseBody List<TB_FIELD_SERVICE_CH> getFieldServicech(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		String sus_no = session.getAttribute("roleSusNo").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		String hqlUpdate = " from TB_FIELD_SERVICE_CH where p_id=:p_id and (status=0 or status=3)";
		Query query = sessionHQL.createQuery(hqlUpdate).setParameter("p_id", id);
		List<TB_FIELD_SERVICE_CH> list = (List<TB_FIELD_SERVICE_CH>) query.list();
		tx.commit();
		sessionHQL.close();
		
		return list;
	}
	
	 @RequestMapping(value = "/admin/getFieldServiceAppr", method = RequestMethod.POST)
		public @ResponseBody List<TB_FIELD_SERVICE_P> getFieldServiceAppr(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			String sus_no = session.getAttribute("roleSusNo").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("q_id"));
			String hqlUpdate = " from TB_FIELD_SERVICE_P where id=:id  ";
			Query query = sessionHQL.createQuery(hqlUpdate).setParameter("id", id);
			List<TB_FIELD_SERVICE_P> list = (List<TB_FIELD_SERVICE_P>) query.list();
			tx.commit();
			sessionHQL.close();
		
			return list;
		}
		
	@RequestMapping(value = "/admin/getFieldServiceApprCh", method = RequestMethod.POST)
	public @ResponseBody List<TB_FIELD_SERVICE_CH> getFieldServiceApprCh(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		String sus_no = session.getAttribute("roleSusNo").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("p_id"));
		String hqlUpdate = " from TB_FIELD_SERVICE_CH where p_id=:p_id  and cancel_status != 1";
		Query query = sessionHQL.createQuery(hqlUpdate).setParameter("p_id", id);
		List<TB_FIELD_SERVICE_CH> list = (List<TB_FIELD_SERVICE_CH>) query.list();
		tx.commit();
		sessionHQL.close();
	
		return list;
	}

	@RequestMapping(value = "/admin/fieldService_delete_action", method = RequestMethod.POST)
	public @ResponseBody String fieldService_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("field_service_ch_id"));
		int index = Integer.parseInt(request.getParameter("index"));
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(request.getParameter("cat")) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
		
		try {
			int app2 = 1;
			String hqlUpdate = "delete from "+tb_namech+" where id=:id ";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			if (index==1) { 
				String hqlUpdate2 = "delete from "+tb_namep+" where id=:id ";
				app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", p_id).executeUpdate();
			}
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app2 > 0) {
				msg = "1";
			} else {
				msg = "0";
			}
		} catch (Exception e) {

		}
		return msg;
	}
	
	
	
	@RequestMapping(value = "/Delete_Search_field_service_data" , method = RequestMethod.POST)
	public @ResponseBody ModelAndView Delete_Search_field_service_data(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Field_Service_url", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			 Transaction tx = sessionHQL.beginTransaction();
			 
			String hqlUpdate = "delete from TB_FIELD_SERVICE where id=:id";
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
		return new ModelAndView("Search_Field_ServicelTiles");
	}
	

	
	
	@RequestMapping(value = "/Edit_field_service_dataUrl", method = RequestMethod.POST)
	public ModelAndView Edit_field_service_dataUrl(@ModelAttribute("id2") String updateid,@ModelAttribute("fd_services2") String fd_services,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "fd_service3", required = false) String fd_service,
			@RequestParam(value = "cat1", required = false) String category,
		    Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		String roleid = sessionEdit.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Field_Service_url", roleid);

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
				Mmap.put("fd_service", fd_service);
				Mmap.put("p_id", updateid);
				Mmap.put("category", category);
				Mmap.put("status1", "0");
				Mmap.put("getOprationList", p_comm.getOprationList());
				Mmap.put("getFdService", p_comm.getFdService());
				Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
				Mmap.put("msg", msg);
				return new ModelAndView("Field_ServicelTiles");
	}
	
	@RequestMapping(value = "/Edit_Appr_field_service", method = RequestMethod.POST)
	public ModelAndView Edit_Appr_field_service(@ModelAttribute("id5") String updateid,@ModelAttribute("fd_services5") String fd_services,
			ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, 
			@RequestParam(value = "fd_service5", required = false) String fd_service,
			@RequestParam(value = "cat5", required = false) String category,
		    Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
		Mmap.put("fd_service", fd_service);
		Mmap.put("p_id", updateid);
		Mmap.put("category", category);
		Mmap.put("status1", "0");
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Field_Service_Edit_AppTiles");
	}
	
	
	@RequestMapping(value = "/ApproveSearch_field_service_dat", method = RequestMethod.POST)
	public ModelAndView ApproveSearch_field_service_dat(@ModelAttribute("id3") String id,
			@ModelAttribute("fd_servicesapp") String fd_services,
			@RequestParam(value = "cat3", required = false) String category,
			ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
			HttpSession sessionEdit) {	
			
		if(Integer.parseInt(category) == 1) {
			Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
			Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),""));
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
				
		Mmap.put("category", category);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("app_Field_ServicelTiles");
	}
	
	@RequestMapping(value = "/admin/Approve_service", method = RequestMethod.POST)
	public ModelAndView Approve_service( ModelMap model,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "id3", required = false) BigInteger id, 
			@RequestParam(value = "fd_servicesapp", required = false) String category
			) {
				String msg="";
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
	
		try {
			
			String hqlUpdatep = "update "+tb_namep+" set status=:status,modified_by=:modified_by,modified_date=:modified_date  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdatep).setInteger("status", 1)
					.setString("modified_by", username).setDate("modified_date", new Date()).setBigInteger("id", id)
					.executeUpdate();
			String hqlUpdatech = "update "+tb_namech+" set status=:status,modified_by=:modified_by,modified_date=:modified_date  where p_id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdatech).setInteger("status", 1)
					.setString("modified_by", username).setDate("modified_date", new Date()).setBigInteger("id", id)
					.executeUpdate();

			p_comm.update_miso_role_hdr_status(id,1,username,"field_ser_data");
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app2 > 0) {
				msg = "Approved Successfully.";
			} else {
				msg = "Unable To Approved.";
			}			
		} catch (Exception e) {
			msg = "Unable To Approved.";
	          tx.rollback();
		}
		model.put("msg", msg);
		return new ModelAndView("redirect:Search_Field_Service_url");

	}
	
	@RequestMapping(value = "/admin/Reject_FD_service", method = RequestMethod.POST)
	public ModelAndView Reject_FD_service( ModelMap model,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "id4", required = false) BigInteger id, 
			@RequestParam(value = "fd_servicesapp4", required = false) String category,
			@RequestParam(value = "rej_remarks4", required = false) String reject_remarks) {
				String msg="";
				
		
		String username = session.getAttribute("username").toString();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
	
		//try {
			
			String hqlUpdatep = "update "+tb_namep+" set status=:status,modified_by=:modified_by,modified_date=:modified_date,reject_remarks=:reject_remarks  where id=:id";
			int app = sessionHQL.createQuery(hqlUpdatep).setInteger("status", 3)
					.setString("reject_remarks", reject_remarks)
					.setString("modified_by", username).setDate("modified_date", new Date())
					.setBigInteger("id", id)
					.executeUpdate();
			
			String hqlUpdatech = "update "+tb_namech+" set status=:status,modified_by=:modified_by,modified_date=:modified_date where p_id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdatech).setInteger("status", 3)
					.setString("modified_by", username).setDate("modified_date", new Date()).setBigInteger("id", id)
					.executeUpdate();

			p_comm.update_miso_role_hdr_status(id,0,username,"field_ser_data");
			tx.commit();
			sessionHQL.close();
			if (app > 0 && app2 > 0) {
				msg = "Reject Successfully.";
			} else {
				msg = "Unable to Reject.";
			}			
//		} catch (Exception e) {
//			msg = "Unable To Approved.";
//	          tx.rollback();
//		}s
		model.put("msg", msg);
		return new ModelAndView("redirect:Search_Field_Service_url");

	}
	
	

@RequestMapping(value = "/View_field_service_dat", method = RequestMethod.POST)
public ModelAndView View_field_service_dat(@ModelAttribute("id6") String id,
		@ModelAttribute("fd_servicesapp6") String fd_services,
		@RequestParam(value = "cat6", required = false) String category,
		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		HttpSession sessionEdit) {	
		
	if(Integer.parseInt(category) == 1) {
		Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
		Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),""));
	}
	else if(Integer.parseInt(category) == 2) {
		
	}
	
	Mmap.put("category", category);
	Mmap.put("getOprationList", p_comm.getOprationList());
	Mmap.put("getFdService", p_comm.getFdService());
	Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
	Mmap.put("msg", msg);
	return new ModelAndView("View_Field_ServicelTiles");
}

////VIEW AND CANCEL HISTORY //

//PARENT //
	@RequestMapping(value = "/View_field_service_history_dat", method = RequestMethod.POST)
	public ModelAndView View_field_service_history_dat(@ModelAttribute("id7") String id,
		@RequestParam(value = "cat7", required = false) String category,
		ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
		HttpSession sessionEdit) {	
		
	if(Integer.parseInt(category) == 1) {
		Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
		Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),"1"));
	}
	else if(Integer.parseInt(category) == 2) {
		
	}
	
	Mmap.put("category", category);
	Mmap.put("getOprationList", p_comm.getOprationList());
	Mmap.put("getFdService", p_comm.getFdService());
	Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
	Mmap.put("msg", msg);
	return new ModelAndView("View_Field_Service_HistoryTiles");
}

@RequestMapping(value = "/Cancel_field_service_dat", method = RequestMethod.POST)
public ModelAndView Cancel_field_service_dat(@ModelAttribute("id7") String id,
	@RequestParam(value = "cat7", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession session) {	
	
	String tb_namep="";
	String tb_namech="";
	if(Integer.parseInt(category) == 1) {
		tb_namep = "TB_FIELD_SERVICE_P";
		tb_namech = "TB_FIELD_SERVICE_CH";
	}
	else if(Integer.parseInt(category) == 2) {
		
	}
	String username = session.getAttribute("username").toString();
	Session sessionhql = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionhql.beginTransaction();
	try {
		String hql = "update "+tb_namep+" set modified_by=:modified_by ,modified_date=:modified_date, "
				+ "cancel_status=:cancel_status  where  id=:id";
		Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
				.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		query.executeUpdate();
		
		///child
		String hql2 = "update "+tb_namech+" set modified_by=:modified_by ,modified_date=:modified_date, "
				+ "cancel_status=:cancel_status  where  p_id=:id";
		Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
				.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
		query2.executeUpdate();
		
		tx.commit();
		
		msg = "Data Cancelled Successfully";
	}catch (Exception e) {
		// TODO: handle exception
		tx.rollback();
	}
	
	Mmap.put("service_category1", category);
	Mmap.put("status1", 4);
	Mmap.put("getOprationList", p_comm.getOprationList());
	Mmap.put("getFdService", p_comm.getFdService());
	Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
	Mmap.put("msg", msg);
	return new ModelAndView("Search_Field_ServicelTiles");
}

@RequestMapping(value = "/Approve_field_service_history_dat", method = RequestMethod.POST)
public ModelAndView Approve_field_service_history_dat(@RequestParam(value = "id_ac", required = false) String id,
	@RequestParam(value = "cat_ac", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession sessionEdit) {	
	
		if(Integer.parseInt(category) == 1) {
			Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
			Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),"1"));
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
		
		Mmap.put("category", category);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Appr_Field_Service_HistoryTiles");
}

@RequestMapping(value = "/Appr_Cancel_field_service_dat", method = RequestMethod.POST)
public ModelAndView Appr_Cancel_field_service_dat(@ModelAttribute("id7") String id,
	@RequestParam(value = "cat7", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession session) {	
			
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery("select count(id) from "+tb_namech+" where cancel_status in (-1,2) and p_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));  
			Long c = (Long) q0.uniqueResult();
			if (c==0) {
				String hql = "update "+tb_namep+" set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
						+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
						.setParameter("cancel_status", 1).setParameter("status", -1).setTimestamp("modified_date", new Date())
						.setString("cancel_by", username).setTimestamp("cancel_date", new Date());
				query.executeUpdate();
			}
			
			
			///child
			String hql2 = "update "+tb_namech+" set modified_by=:modified_by ,modified_date=:modified_date, status=:status, "
					+ "cancel_status=:cancel_status , cancel_date=:cancel_date , cancel_by=:cancel_by where cancel_status=0 and p_id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
					.setParameter("cancel_status", 1).setParameter("status", -1).setTimestamp("modified_date", new Date())
					.setString("cancel_by", username).setTimestamp("cancel_date", new Date());;
			query2.executeUpdate();
			
			tx.commit();
			
			msg = "Data Approved Successfully";
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		
		Mmap.put("service_category1", category);
		Mmap.put("status1", 4);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Search_Field_ServicelTiles");
	}

@RequestMapping(value = "/Reject_Cancel_field_service_dat", method = RequestMethod.POST)
public ModelAndView Reject_Cancel_field_service_dat(@ModelAttribute("id_rj") String id,
	@RequestParam(value = "cat_rj", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession session) {	
			
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			String hql = "update "+tb_namep+" set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  id=:id";
			Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
					.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
			query.executeUpdate();
			
			///child
			String hql2 = "update "+tb_namech+" set modified_by=:modified_by ,modified_date=:modified_date, "
					+ "cancel_status=:cancel_status  where  p_id=:id";
			Query query2 = sessionhql.createQuery(hql2).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
					.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
			query2.executeUpdate();
			
			tx.commit();
			
			msg = "Data Rejected Successfully";
		}catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		}
		
		Mmap.put("service_category1", category);
		Mmap.put("status1", 4);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		return new ModelAndView("Search_Field_ServicelTiles");
}

////////CHILD /////////////

@RequestMapping(value = "/Cancel_field_service_Child", method = RequestMethod.POST)
public ModelAndView Cancel_field_service_Child(@RequestParam(value = "id_ch", required = false) String ch_id,
	@RequestParam(value = "id_p", required = false) String id,
	@RequestParam(value = "cat_ch", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession session) {	
	
		String redirr = "redirect:View_field_service_history_dat";
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery("select count(id) from "+tb_namech+" where cancel_status in (-1,2) and p_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));  
			Long c = (Long) q0.uniqueResult();
			Query q01 = sessionhql.createQuery("select count(id) from "+tb_namech+" where cancel_status in (-1,2) and status=1 and p_id =:p_id");
			q01.setParameter("p_id", Integer.parseInt(id));  
			Long c1 = (Long) q01.uniqueResult();
			if (c1<=1) {
				redirr = "Search_Field_ServicelTiles";
			}
			
			if (c<=1) {
				redirr = "Search_Field_ServicelTiles";
				String hql = "update "+tb_namep+" set modified_by=:modified_by ,modified_date=:modified_date, "
						+ "cancel_status=:cancel_status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
						.setParameter("cancel_status", 0).setTimestamp("modified_date", new Date());
				query.executeUpdate();
			}
			
			
			///child
			String hql2 = "update "+tb_namech+" set modified_by=:modified_by ,modified_date=:modified_date, "
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
		
		Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
		Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),"1"));
		
		Mmap.put("category", category);
		Mmap.put("service_category1", category);
		Mmap.put("status1", 4);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		Mmap.put("id7", id);
		Mmap.put("cat7", category);
		
		return new ModelAndView(redirr);
}

@RequestMapping(value = "/RejectCh_Cancel_field_service_dat", method = RequestMethod.POST)
public ModelAndView RejectCh_Cancel_field_service_dat(@RequestParam(value = "id_rjCh", required = false) String ch_id,
	@RequestParam(value = "id_rjP", required = false) String id,
	@RequestParam(value = "cat_rjCh", required = false) String category,
	ModelMap Mmap,@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
	HttpSession session) {	
	
		String redirr = "redirect:Approve_field_service_history_dat";
		String tb_namep="";
		String tb_namech="";
		if(Integer.parseInt(category) == 1) {
			tb_namep = "TB_FIELD_SERVICE_P";
			tb_namech = "TB_FIELD_SERVICE_CH";
		}
		else if(Integer.parseInt(category) == 2) {
			
		}
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		try {
			Query q0 = sessionhql.createQuery("select count(id) from "+tb_namech+" where cancel_status in (-1,2) and p_id =:p_id");
			q0.setParameter("p_id", Integer.parseInt(id));  
			Long c = (Long) q0.uniqueResult();
			
			Query q01 = sessionhql.createQuery("select count(id) from "+tb_namech+" where cancel_status=0 and p_id =:p_id");
			q01.setParameter("p_id", Integer.parseInt(id));  
			Long c1 = (Long) q01.uniqueResult();
			if (c1<=1) {
				redirr = "Search_Field_ServicelTiles";
			}
			if ( c1<=1) {
				redirr = "Search_Field_ServicelTiles";
				String hql = "update "+tb_namep+" set modified_by=:modified_by ,modified_date=:modified_date, "
						+ "cancel_status=:cancel_status  where  id=:id";
				Query query = sessionhql.createQuery(hql).setInteger("id", Integer.parseInt(id)).setString("modified_by", username)
						.setParameter("cancel_status", 2).setTimestamp("modified_date", new Date());
				query.executeUpdate();
			}
			
			
			///child
			String hql2 = "update "+tb_namech+" set modified_by=:modified_by ,modified_date=:modified_date, "
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
		
		Mmap.put("listp", fd.getfield_servicerByid(Integer.parseInt(id)));
		Mmap.put("listch", fd.getfield_servicerChByid(Integer.parseInt(id),"1"));		
		Mmap.put("category", category);
		Mmap.put("service_category1", category);
		Mmap.put("status1", 4);
		Mmap.put("getOprationList", p_comm.getOprationList());
		Mmap.put("getFdService", p_comm.getFdService());
		Mmap.put("getServiceCategoryList", p_comm.getServiceCategoryList());
		Mmap.put("msg", msg);
		Mmap.put("id_ac", id);
		Mmap.put("cat_ac", category);		
		return new ModelAndView(redirr);
	}
////VIEW AND CANCEL HISTORY ENDS//
	
}
