package com.controller.mnh;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.validation.MNH_ValidationController;

import com.dao.login.RoleBaseMenuDAO;
import com.dao.mnh.InvalidmentDAO;
import com.models.mnh.Tb_Med_Imb;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class input_InvalidmentController {
	MNH_ValidationController validation = new MNH_ValidationController();
	@Autowired
	private RoleBaseMenuDAO roledao; 
	
	@Autowired
	private InvalidmentDAO invalidDao; 
	AllMethodsControllerOrbat all = new AllMethodsControllerOrbat();
		
	
	MNH_CommonController mcommon = new MNH_CommonController();
	
	// INPUTS Invalidment 
		@RequestMapping(value = "/admin/mnh_input_invalidment", method = RequestMethod.GET)
		public ModelAndView mnh_input_invalidment(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
			 
			Date date = new Date();
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

			Boolean val = roledao.ScreenRedirect("mnh_input_invalidment", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
			Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
			Mmap.put("getMedSystemCode_SERVICE", mcommon.getMedSystemCode("SERVICE", "", session));
			Mmap.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", session));
			Mmap.put("ml_5", mcommon.getTradeList("", session));
			Mmap.put("ml_6", mcommon.getMedrelationList("", session));
			Mmap.put("getMedSystemCode_SEX", mcommon.getMedSystemCode("SEX", "", session));
			Mmap.put("date", date1);
			Mmap.put("msg", msg);		

			return new ModelAndView("mnh_input_IMBTiles");
		}
		
		
		/*----------Save Invalidment-----------*/
		
		
		@RequestMapping(value = "/Invalid_IMB_InputAction", method = RequestMethod.POST)
		public ModelAndView Invalid_IMB_InputAction(@ModelAttribute("Invalid_IMB_InputCMD") Tb_Med_Imb rs,
					BindingResult bindingResult, HttpServletRequest request, ModelMap Mmap, HttpSession session ,
					@RequestParam(value = "msg", required = false) String msg) throws ParseException  {

			Boolean val = roledao.ScreenRedirect("mnh_input_invalidment", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
			
			
		int id = rs.getId() > 0 ? rs.getId() : 0;			

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date_of_birth_i = null;
				Date date_a1 = null;
				Date date_a2 = null;
				Date birth = null;
				
				String username = session.getAttribute("username").toString();
				String sus_no = request.getParameter("sus_no");
				String name = request.getParameter("name");
				String service = request.getParameter("service");
				String service_years = request.getParameter("service_years1");
				Integer service_years1 = 0;
				 if (service_years != "" && !service_years.equals("")) {
					 service_years1 = Integer.parseInt(service_years);
					}
				 BigInteger adhar_card_no =BigInteger.ZERO;
		         if(!request.getParameter("adhar_card_no") .equals("")) {
		        	 adhar_card_no =new BigInteger(request.getParameter("adhar_card_no"));
				 }
		         BigInteger contact_no =BigInteger.ZERO;            
		         if(!request.getParameter("contact_no1") .equals("")){
		         	contact_no =new BigInteger(request.getParameter("contact_no1"));
				     }
		         String persnl_no1 = request.getParameter("persnl_no1");
				String category = request.getParameter("category");
				String persnl_no2 = request.getParameter("persnl_no2");
				String persnl_no3 = request.getParameter("persnl_no3");
				String persnl_no = null;
				if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
					persnl_no = persnl_no2 + persnl_no3;
				} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
						&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
					persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
				} else if (service.equalsIgnoreCase("OTHERS")) {
					persnl_no = null;
				}
				
				String icd_cd[] = request.getParameter("icd_code").split("-");
				String icd_cau[] = request.getParameter("icd_cause").split("-");
			
				if (request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")) {
					Mmap.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				
				if (persnl_no2.equals("") && !service.equals("OTHERS")) {
					Mmap.put("msg", "Please Enter the Personnel No");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				if (persnl_no3.equals("-1") && !service.equals("OTHERS")) {
					Mmap.put("msg", "Please Enter the Personnel No");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				 if(validation.persnl_noLength(request.getParameter("persnl_no2")) == false){
			        	Mmap.put("msg",validation.persnl_no2MSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				
				if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
					category = "";
				}
				if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
					Mmap.put("msg", "Please Select the Category");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				/*if (rs.getInvalid_rank().getId() == -1) {
					Mmap.put("msg", "Please Select the rank");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}*/
				if (request.getParameter("sex") == null || request.getParameter("sex").equals("-1")) {
					Mmap.put("msg", "Please Select the Gender");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				if (request.getParameter("relation") == null || request.getParameter("relation").equals("-1")) {
					Mmap.put("msg", "Please Select the Relation");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				if (request.getParameter("date_of_birth1") == null || request.getParameter("date_of_birth1").equals("")) {
					Mmap.put("msg", "Please Select the Date of  Birth");
					return new ModelAndView("redirect:mnh_input_invalidment");
				} else {
		
					date_of_birth_i = formatter1.parse(request.getParameter("date_of_birth1"));
				}
			
					SimpleDateFormat YY = new SimpleDateFormat("yyyy");
					SimpleDateFormat MM = new SimpleDateFormat("MM");
					SimpleDateFormat dd = new SimpleDateFormat("dd");
					int year = Integer.parseInt(YY.format(date_of_birth_i));
					int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
					int Dd = Integer.parseInt(dd.format(date_of_birth_i));
               
				LocalDate today = LocalDate.now(); // Today's date
				LocalDate birthday = LocalDate.of(year, MM1, Dd);// Birth date
				Period p = Period.between(birthday, today);
				Integer age_year = 0;
				
				if (request.getParameter("age_year") != "" && !request.getParameter("age_year").equals("")) {
					age_year = p.getYears();
				}
		
				if (request.getParameter("date_of_birth") != "" && request.getParameter("age_year") != "" && age_year < 17) {
					Mmap.put("msg", "Personnel Age Year Not Allowed Below 17");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				
		
				if (service_years1 > age_year) {
					Mmap.put("msg", "Service Year Should Not More Than Age Year");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				
				 if(validation.DiseasemmrLength(rs.getPersnl_unit()) == false){
			        	Mmap.put("msg",validation.persnl_unitMSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				 if(validation.DiseaseFamilyLength(rs.getFmn()) == false){
			        	Mmap.put("msg",validation.FmnMSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				  if(validation.MessageLength(rs.getDiagnosis_remarks()) == false){
			        	Mmap.put("msg",validation.diagnosis_remarksMSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				
				if (request.getParameter("icd_code") == null || request.getParameter("icd_code").equals("")) {
					Mmap.put("msg", "Please Enter the ICD Code");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				
				 if(validation.IcdDescriptionLength(request.getParameter("icd_code")) == false){
			        	Mmap.put("msg",validation.Icd_codeMSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				 if(validation.IcdDescriptionLength(rs.getIcd_cause()) == false){
			        	Mmap.put("msg",validation.icd_causeMSG);
		               return new ModelAndView("redirect:mnh_input_invalidment");
		               }
				
				if (request.getParameter("date_place_origine") == null
						|| request.getParameter("date_place_origine").equals("")) {
					Mmap.put("msg", "Please Select the Date of  Origin");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				if (request.getParameter("date_imb") == null || request.getParameter("date_imb").equals("")) {
					Mmap.put("msg", "Please Select the Date of Imb");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				if (request.getParameter("percent") == null || request.getParameter("percent").equals("")) {
					Mmap.put("msg", "Please Enter the Percent");
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
				 if(validation.sus_noLength(request.getParameter("sus_no")) == false){
					 Mmap.put("msg",validation.sus_noMSG);
		                return new ModelAndView("redirect:mnh_input_invalidment");
		             }
				
				if(validation.DiseaseFamilyLength(rs.getAttributable_aggravated()) == false){
		        	Mmap.put("msg",validation.attributable_aggravatedMSG);
	               return new ModelAndView("redirect:mnh_input_invalidment");
	               }
					date_a1 = formatter.parse(request.getParameter("date_place_origine"));
					date_a2 = formatter.parse(request.getParameter("date_imb"));
					birth = formatter.parse(request.getParameter("date_of_birth1"));
					
					Session session1 = HibernateUtilNA.getSessionFactory().openSession();
					Transaction tx = session1.beginTransaction();
					try {
						Long invalidExist_cond = invalidDao.checkExitstingInvalidment(sus_no, persnl_no, id);
						if (invalidExist_cond != (long) 0) {
							Mmap.put("msg", "sus no  already exists.");
							return new ModelAndView("redirect:mnh_input_invalidment");
						}
			
						rs.setCreated_on(new Date());
						rs.setCreated_by(username);
						rs.setSus_no(sus_no);
						rs.setPersnl_no(persnl_no);
						rs.setName(name);
						rs.setAge(age_year);
						rs.setService_years(service_years1);
						rs.setDate_place_origine(date_a1);
						rs.setDate_imb(date_a2);
						rs.setDate_of_birth(birth);
						rs.setContact_no(contact_no);
						rs.setAdhar_card_no(adhar_card_no);
						rs.setIcd_code(icd_cd[0]);
						rs.setIcd_cause(icd_cau[0]);
						rs.setSex(request.getParameter("sex"));
						 
						session1.save(rs);
						tx.commit();
						Mmap.put("msg", "Data saved Successfully ");
					}
					catch (RuntimeException e) {
						try {
							tx.rollback();
							Mmap.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
						}
						throw e;
					} finally {
			
						if (session1 != null) {
							session1.close();
						}
					}
					return new ModelAndView("redirect:mnh_input_invalidment");
				}
			
			
		/*----------SEARCH PageOpen-----------*/
			@RequestMapping(value = "/admin/mnh_input_search_invalidment", method = RequestMethod.GET)
			public ModelAndView mnh_input_search_invalidment(ModelMap Mmap, HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg) {				
				
				int userid = (Integer) session.getAttribute("userId");			

				Boolean val = roledao.ScreenRedirect("mnh_input_search_invalidment", session.getAttribute("roleid").toString());
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}				

				if(request.getHeader("Referer") == null ) {
					msg = "";
					return new ModelAndView("redirect:bodyParameterNotAllow");
				}
				
				String roleAccess = session.getAttribute("roleAccess").toString();
				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleType = session.getAttribute("roleType").toString();
				
				if(roleAccess.equals("Unit")){
					Mmap.put("sus_no",roleSusNo);
					Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
				}
				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
				 Mmap.put("msg", msg);
					Mmap.put("date", date1);
					Mmap.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
					Mmap.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
					
					
					return new ModelAndView("mnh_input_IMB_searchTiles");
			}
		
			/*----------SEARCH -----------*/
		@RequestMapping(value = "/search_invalid_IMB_Input", method = RequestMethod.POST)
		public ModelAndView search_invalid_IMB_Input(ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, @ModelAttribute("sus1") String sus1,
				@ModelAttribute("unit1") String unit1, @ModelAttribute("psn_no") String psn_no,HttpServletRequest request,
				@ModelAttribute("dt_origin1") String dt_origin1, @ModelAttribute("dt_imb1") String dt_imb1,String adhar1,String contact1) {

			Boolean val = roledao.ScreenRedirect("mnh_input_search_invalidment", session.getAttribute("roleid").toString());
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}				

			if(request.getHeader("Referer") == null ) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}
				
				if (sus1 == null || sus1.equals("")) {
					model.put("msg", "Please Enter the SUS No");
					return new ModelAndView("redirect:mnh_input_search_invalidment");
				}
				if (validation.DiseasemmrLength(unit1) == false) {
					model.put("msg", validation.hospital_nameMSG);
					return new ModelAndView("redirect:mnh_input_search_invalidment");
				}
				if (validation.sus_noLength(sus1) == false) {
					model.put("msg", validation.sus_noMSG);
					return new ModelAndView("redirect:mnh_input_search_invalidment");
				}
				if (validation.adhar_noLength(adhar1) == false) {
					 model.put("msg", validation.adharnoMSG);
						return new ModelAndView("redirect:mnh_input_search_invalidment");
					}
				 String roleAccess = session.getAttribute("roleAccess").toString();
					String roleSusNo = session.getAttribute("roleSusNo").toString();
				
					String f_sus_no=sus1;
					if(roleAccess.equals("Unit")){
						model.put("sus_no",roleSusNo);
						model.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
						f_sus_no = roleSusNo;
						
					}
				model.put("sus1", f_sus_no);
				model.put("unit1", unit1);
				model.put("psn_no", psn_no);
				model.put("dt_origin1", dt_origin1);
				model.put("dt_imb1", dt_imb1);
				model.put("adhar1", adhar1);
				model.put("contact1", contact1);
				List<Map<String, Object>> list = invalidDao.search_invalidment_input(sus1, psn_no, dt_origin1, dt_imb1,adhar1,contact1);
				model.put("list", list);
				model.put("size", list.size());
				model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", session));
				model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", session));
				
				
					model.put("list", list);
				
				return new ModelAndView("mnh_input_IMB_searchTiles");
			}
			
			
				/*----------Edit PageOpen-----------*/
		
				@RequestMapping(value = "/edit_invalidment_input")
				public ModelAndView edit_invalidment_input(@ModelAttribute("id2") String updateid, ModelMap model,
						@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
						HttpSession sessionEdit) {
					
					String  roleid = sessionEdit.getAttribute("roleid").toString();
					Boolean val = roledao.ScreenRedirect("mnh_input_search_invalidment", roleid);	
					if(val == false) {
						return new ModelAndView("AccessTiles");
					}
					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					
					Date date = new Date();
					String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
					model.put("date", date1);
			
					Tb_Med_Imb invDetails = invalidDao.updateinvalid_Byid(Integer.parseInt(updateid));
					model.put("Edit_Invalid_IMB_InputCMD", invDetails);
					model.put("date", date1);
					model.put("msg", msg);
			
					model.put("getMedSystemCodePERSNL_PRE", mcommon.getMedSystemCode("PERSNL_PRE", "", sessionEdit));
					model.put("getMedSystemCodePERSNL_SUF", mcommon.getMedSystemCode("PERSNL_SUF", "", sessionEdit));
					model.put("getMedSystemCodeSERVICE", mcommon.getMedSystemCode("SERVICE", "", sessionEdit));
					model.put("getMedSystemCodeCATEGORY", mcommon.getMedSystemCode("CATEGORY", "", sessionEdit));
					model.put("ml_5", mcommon.getTradeList("", sessionEdit));
					model.put("ml_6", mcommon.getMedrelationList("", sessionEdit));
					model.put("getMedSystemCode_SEX", mcommon.getMedSystemCode("SEX", "", sessionEdit));
			
					return new ModelAndView("edit_InvalidmentTiles");
				}
			
				/*----------Edit -----------*/
				@RequestMapping(value = "/Edit_Invalid_IMB_InputAction", method = RequestMethod.POST)
				public ModelAndView Edit_Invalid_IMB_InputAction(@ModelAttribute("Edit_Invalid_IMB_InputCMD") Tb_Med_Imb lm,@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, ModelMap Mmap, HttpSession session) throws ParseException {
					Boolean val = roledao.ScreenRedirect("mnh_input_search_invalidment", session.getAttribute("roleid").toString());
					if (val == false) {
						return new ModelAndView("AccessTiles");
					}				

					if(request.getHeader("Referer") == null ) {
						msg = "";
						return new ModelAndView("redirect:bodyParameterNotAllow");
					}
					 int id = lm.getId() > 0 ? lm.getId() : 0;			
						Mmap.put("id2", lm.getId());
						  String roleAccess = session.getAttribute("roleAccess").toString();
							String roleSusNo = session.getAttribute("roleSusNo").toString();
						
							String sus_no1=request.getParameter("sus_no");
							if(roleAccess.equals("Unit")){
								Mmap.put("sus_no",roleSusNo);
								Mmap.put("unit_name",all.getActiveUnitNameFromSusNo_Without_Enc(roleSusNo,session).get(0));
								sus_no1 = roleSusNo;
								
							}
							 BigInteger contact_no =BigInteger.ZERO;            
					            if(!request.getParameter("contact_no1") .equals("")) 
					           {
					            	contact_no =new BigInteger(request.getParameter("contact_no1"));
							     }
					        	BigInteger adhar_card_no = BigInteger.ZERO;
								if (!request.getParameter("adhar_card_no").equals("")) {
									adhar_card_no = new BigInteger(request.getParameter("adhar_card_no"));
								}
					Date date = new Date();
					String username = session.getAttribute("username").toString();
					
					Date date_of_birth_i = null;
					Date date_imb_i = null;
					Date date_place_origine_i = null;
					
					DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");			
					DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			        
					
					
					String service = request.getParameter("service");
					String name = request.getParameter("name");
					String persnl_no1 = request.getParameter("persnl_no1");
					String persnl_no2 = request.getParameter("persnl_no2");
					String persnl_no3 = request.getParameter("persnl_no3");
					String persnl_no = null;
					if (persnl_no1.equalsIgnoreCase("-1") && !service.equalsIgnoreCase("OTHERS")) {
						persnl_no = persnl_no2 + persnl_no3;
					} else if (!persnl_no1.equalsIgnoreCase("-1") && !persnl_no2.equalsIgnoreCase("")
							&& !persnl_no3.equalsIgnoreCase("-1") && service.equalsIgnoreCase("ARMY")) {
						persnl_no = persnl_no1 + persnl_no2 + persnl_no3;
					} else if (service.equalsIgnoreCase("OTHERS")) {
						persnl_no = null;
					}
					
					String category = request.getParameter("category");
					
					
					String icd_cd[] = request.getParameter("icd_code").split("-");
					String icd_cau[] = request.getParameter("icd_cause").split("-");
					String date_of_birth = request.getParameter("date_of_birth1");
					String age_year1 = request.getParameter("age_year");
					String date_place_ori = request.getParameter("date_place_origine1");
					String date_imb = request.getParameter("date_imb1");
					String sex = request.getParameter("sex");	  
					String relation = request.getParameter("relation");  
					  
					String service_years = request.getParameter("service_years1");
					Integer service_years1 = 0;
					 if (service_years != "" && !service_years.equals("")) {
						 service_years1 = Integer.parseInt(service_years);
						}
					 
					 String percent = request.getParameter("percent1");
						Integer percent1 = 0;
						 if (percent != "" && !percent.equals("")) {
							 percent1 = Integer.parseInt(percent);
							}
						 
					 
					if (!date_of_birth.equals("")) {
						date_of_birth_i = formatter2.parse(date_of_birth);
					}
					if (!date_place_ori.equals("")) {
						date_place_origine_i = formatter2.parse(date_place_ori);
					}
					if (!date_imb.equals("")) {
						date_imb_i = formatter2.parse(date_imb);
					}
					
					SimpleDateFormat YY = new SimpleDateFormat("yyyy");
					SimpleDateFormat MM = new SimpleDateFormat("MM");
					SimpleDateFormat dd = new SimpleDateFormat("dd");
					int year = Integer.parseInt(YY.format(date_of_birth_i));
					int MM1 = Integer.parseInt(MM.format(date_of_birth_i));
					int Dd = Integer.parseInt(dd.format(date_of_birth_i));
			    
					LocalDate today = LocalDate.now(); // Today's date
					LocalDate birthday1 = LocalDate.of(year, MM1, Dd);// Birth date
					Period p = Period.between(birthday1, today);
					Integer age_year = 0;
					if (age_year1 != "" && !age_year1.equals("")) {
						age_year = p.getYears();
					}
			
					
					if (request.getParameter("sus_no") == null || request.getParameter("sus_no").equals("")) {
						Mmap.put("msg", "Please Enter the SUS No");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if(validation.sus_noLength(request.getParameter("sus_no")) == false){
						 Mmap.put("msg",validation.sus_noMSG);
			                return new ModelAndView("redirect:edit_invalidment_input");
			             }
					if(validation.DiseasemmrLength(lm.getName()) == false){
			        	Mmap.put("msg",validation.NameMSG);
		                return new ModelAndView("redirect:edit_invalidment_input");
		                }
					
					 if (persnl_no2.equals("") && !service.equals("OTHERS")) {
						 Mmap.put("msg", "Please Enter the Personnel No");
							return new ModelAndView("redirect:edit_invalidment_input");
						}
						if (persnl_no3.equals("-1") && !service.equals("OTHERS")) {
							Mmap.put("msg", "Please Enter the Personnel No");
							return new ModelAndView("redirect:edit_invalidment_input");
						}
						 if(validation.persnl_noLength(request.getParameter("persnl_no2")) == false){
							 Mmap.put("msg",validation.persnl_no2MSG);
				               return new ModelAndView("redirect:edit_invalidment_input");
				               }
					if (category.equals("-1") && service.equalsIgnoreCase("OTHERS")) {
						category = "";
					}	
					if(validation.persnl_noLength(request.getParameter("persnl_no2")) == false){
			        	Mmap.put("msg",validation.persnl_no2MSG);
		               return new ModelAndView("redirect:edit_invalidment_input");
		               }
					
					if (category.equals("-1") && !service.equalsIgnoreCase("OTHERS")) {
						Mmap.put("msg", "Please Select the Category");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
			
					/*if (lm.getInvalid_rank().getId() == -1) {
						Mmap.put("msg", "Please Select the rank");
						return new ModelAndView("redirect:edit_invalidment_input");
					}*/
					if (sex.equals("-1") || sex.equals("")) {
						Mmap.put("msg", "Please Select the Gender");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if (relation.equals("-1") && !relation.equals("")) {
						Mmap.put("msg", "Please Enter the Relation");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if (request.getParameter("date_of_birth1") == null || request.getParameter("date_of_birth1").equals("")) {
						Mmap.put("msg", "Please Select the Date of  Birth");
						return new ModelAndView("redirect:edit_invalidment_input");
					} 
					if (date_of_birth != "" && age_year1 != "" && age_year < 17) {
						Mmap.put("msg", "Personnel Age Year Not Allowed Below 17");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if (service_years1 > age_year) {
						Mmap.put("msg", "Service Year Should Not More Than Age Year");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if(validation.DiseasemmrLength(lm.getPersnl_unit()) == false){
			        	Mmap.put("msg",validation.persnl_unitMSG);
		               return new ModelAndView("redirect:edit_invalidment_input");
		               }
					 if(validation.DiseaseFamilyLength(lm.getFmn()) == false){
				        	Mmap.put("msg",validation.FmnMSG);
			               return new ModelAndView("redirect:edit_invalidment_input");
			               }
					if (request.getParameter("icd_code") == null || request.getParameter("icd_code").equals("")) {
						Mmap.put("msg", "Please Enter the ICD Code");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if(validation.IcdDescriptionLength(lm.getIcd_code()) == false){
			        	Mmap.put("msg",validation.Icd_codeMSG);
		               return new ModelAndView("redirect:edit_invalidment_input");
		               }
					if(validation.IcdDescriptionLength(lm.getIcd_cause()) == false){
			        	Mmap.put("msg",validation.icd_causeMSG);
		               return new ModelAndView("redirect:edit_invalidment_input");
		               }
					if (request.getParameter("date_place_origine1") == null
							|| request.getParameter("date_place_origine1").equals("")) {
						Mmap.put("msg", "Please Select the Date of  Origign");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					if (request.getParameter("date_imb1") == null || request.getParameter("date_imb1").equals("")) {
						Mmap.put("msg", "Please Select the Date of Imb");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					
					if (request.getParameter("percent1") == null || request.getParameter("percent1").equals("")) {
						Mmap.put("msg", "Please Enter the Percent");
						return new ModelAndView("redirect:edit_invalidment_input");
					}
					 if(validation.MessageLength(lm.getDiagnosis_remarks()) == false){
				        	Mmap.put("msg",validation.diagnosis_remarksMSG);
			               return new ModelAndView("redirect:edit_invalidment_input");
			               }
					 if(validation.DiseaseFamilyLength(lm.getAttributable_aggravated()) == false){
				        	Mmap.put("msg",validation.attributable_aggravatedMSG);
			               return new ModelAndView("redirect:edit_invalidment_input");
			               }
					
					try {
			
						Long d = invalidDao.checkExitstingInvalidment(sus_no1, persnl_no, lm.getId());
			
						lm.setModified_by(username);
						lm.setModified_on(date);
						lm.setAge(age_year);
						lm.setService_years(service_years1);
						lm.setPersnl_no(persnl_no);
						lm.setDate_imb(date_imb_i);
						lm.setDate_of_birth(date_of_birth_i);
						lm.setContact_no(contact_no);
						lm.setAdhar_card_no(adhar_card_no);
						lm.setSex(sex);
						lm.setName(name);
						lm.setDate_place_origine(date_place_origine_i);
						lm.setPercent(percent1);
						lm.setIcd_code(icd_cd[0]);
						lm.setIcd_cause(icd_cau[0]);
						if (d == 0) {
			
							Mmap.put("msg", invalidDao.updateinvlidment(lm, username));
 						}
						if (d > 0) {
							Mmap.put("msg", "Data already Exist32656.");
						}
			
					} catch (RuntimeException e) {
						try {
			
							Mmap.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							Mmap.put("msg", "Couldn’t roll back transaction " + rbe);
						}
						throw e;
					} finally {
			
					}
					return new ModelAndView("redirect:mnh_input_search_invalidment");
				}
			
			// update  End //
		
			
			
			 //Delete//
			
			
			@RequestMapping(value = "/delete_invalidment_input" , method = RequestMethod.POST)
			public @ResponseBody ModelAndView delete_invalidment_input(@ModelAttribute("id1") int id,BindingResult result, HttpServletRequest request,
					HttpSession sessionA, ModelMap model,
					@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
				Boolean val = roledao.ScreenRedirect("mnh_input_search_invalidment", sessionA.getAttribute("roleid").toString());
				if (val == false) {
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
					 
					String hqlUpdate = "delete from Tb_Med_Imb where id=:id";
					
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
				return new ModelAndView("mnh_input_IMB_searchTiles");
			}
			
		
}
