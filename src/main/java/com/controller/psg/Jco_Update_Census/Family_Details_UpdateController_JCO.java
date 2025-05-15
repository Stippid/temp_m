package com.controller.psg.Jco_Update_Census;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Census.TB_CENSUS_JCO_OR_PARENT;
import com.models.psg.Jco_Update_Census.TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/","user"})

public class Family_Details_UpdateController_JCO {
	
	ValidationController valid = new ValidationController();
	psg_jco_CommonController p_comm = new psg_jco_CommonController();
	

	@RequestMapping(value = "/admin/Family_Details_Action_JCO", method = RequestMethod.POST)
	public @ResponseBody String Family_Details_Action_JCO(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
	
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String username = session.getAttribute("username").toString();
	
			Date Date_authority = null;
			Date father_dob_dt = null;
			Date mother_dob_dt = null;
			String authority = request.getParameter("fd_authority").trim();
			String date_of_authority = request.getParameter("fd_date_of_authority").trim();
			//String pan_no = request.getParameter("pan_no").trim();
	
			String fd_id = request.getParameter("fd_id").trim();
			String father_name = request.getParameter("father_name").trim();
			String father_dob = request.getParameter("father_dob").trim();
			String mother_dob = request.getParameter("mother_dob").trim();
			String father_place_birth = request.getParameter("father_place_birth").trim();
			String mother_place_birth = request.getParameter("mother_place_birth").trim();
			String isfatherInservice = request.getParameter("father_proff_radio");
			String ismotherInservice = request.getParameter("mother_proff_radio");
			String mother_personal_no = request.getParameter("mother_personal_no");
			String father_personal_no = request.getParameter("father_personal_no");
			String mother_other = request.getParameter("mother_other");
			String father_other = request.getParameter("father_other");
			String mother_service = request.getParameter("mother_service");
			String father_service = request.getParameter("father_service");
			String father_profession = request.getParameter("father_profession");
			String mother_profession = request.getParameter("mother_profession");
			String mother_name = request.getParameter("mother_name");
			String father_proffother = request.getParameter("father_proffother");
			String mother_proffother = request.getParameter("mother_proffother");
			
			String jco_id = request.getParameter("jco_id").trim();
			
			
			String msg = "";
			
			if (isfatherInservice.equals("yes")) {

				father_profession = "0";

				if (father_service == null || father_service.trim().equals("0")) {

					return "Please Select Father Service";

				}

				if (father_personal_no.trim().equals("")) {

					father_personal_no = null;

				}
         //260194
				if (father_service.equals("9")) {

					father_personal_no = null;

				} else {

					father_other = null;

				}

			} else {
					father_personal_no = null;
					father_other = null;
					father_service = null;
				}
				if (ismotherInservice.equals("yes")) {
					mother_profession = "0";
					if (mother_personal_no.equals("")) {
						mother_personal_no = null;
					}
					 //260194
					if (mother_service.equals("9")) {
						mother_personal_no = null;
					} else {
						mother_other = null;
					}
				} else {
					mother_personal_no = null;
					mother_other = null;
					mother_service = null;
				}
			
				 if(authority == null || authority.equals("null") || authority.equals("")) {
					 msg = "Please Enter Authority ";
					 return msg;
				 }
				 if (!valid.isValidAuth(authority)) {
						return valid.isValidAuthMSG + " Authority No";
					}	
					if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
			 			return "Authority No " + valid.isValidLengthMSG;	
					}
					if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
							|| date_of_authority.equals("")) {
						return "Please Select Date of Authority";
					}
					else if (!valid.isValidDate(date_of_authority)) {
			 			return valid.isValidDateMSG + " of Authority";	
					} else {
					Date_authority = format.parse(date_of_authority);
				}
				
				
				
				if (request.getParameter("father_name") == null || request.getParameter("father_name").trim().equals("")) {
					msg = "Please Select Father's Name";
					return msg;
				}
				if (valid.isOnlyAlphabet(request.getParameter("father_name")) == false) {
					return "Father's Name "+ valid.isOnlyAlphabetMSG;
				  
			     }
				if (!valid.isvalidLength(request.getParameter("father_name"),valid.nameMax,valid.nameMin)) {
					return "Father's Name " + valid.isValidLengthMSG;
				   
				}
				if (father_dob == null || father_dob.equals("null") || father_dob.equals("DD/MM/YYYY") || father_dob.equals("")) {
					msg = "Please Enter Father Date of Birth ";
					return msg;
				} 
				else  if (!valid.isValidDate(father_dob)){
					return valid.isValidDateMSG  + " of Father DOB";
				   
			     }
				else {
					father_dob_dt = format.parse(father_dob);
				}
				
				
				if (request.getParameter("father_place_birth") == null || request.getParameter("father_place_birth").equals("")) {
					msg = "Please Select Father's Place of Birth";
					return msg;
				}
				if (valid.isOnlyAlphabet(request.getParameter("father_place_birth")) == false) {
					return "Father's Place of Birth "+ valid.isOnlyAlphabetMSG;
				   
			     }
				if (!valid.isvalidLength(request.getParameter("father_place_birth"),valid.nameMax,valid.nameMin)) {
					return "Father's Place of Birth " + valid.isValidLengthMSG;
				 
				}
				if (isfatherInservice.equals("yes")) {	
					if (request.getParameter("father_service") == null || request.getParameter("father_service").equals("0")) {
						msg = "Please Select Father's Services";
						return msg;
					}
					if (!request.getParameter("father_service").equals("9")) {
					if (request.getParameter("father_personal_no") == null || request.getParameter("father_personal_no").equals("")) {
						msg = "Please Select Father's Personal No";
						return msg;
					}
					if (!valid.isOnlyAlphabetNumeric(request.getParameter("father_personal_no"))) {
						return "Father's Personal No " + valid.isOnlyAlphabetNumericMSG;
					   
					}
					if (!valid.isvalidLength(request.getParameter("father_personal_no"),valid.nameMax,valid.nameMin)) {
						return "Father's Personal No " + valid.isValidLengthMSG;
					   
					}
					
					}
					if (request.getParameter("father_service").equals("4")) {
					if (request.getParameter("father_other") == null || request.getParameter("father_other").equals("")) {
						msg = "Please Enter Father's Other";
						return msg;
					}
					}
				}
				if (isfatherInservice.equals("no")) {	
					if (request.getParameter("father_profession") == null || request.getParameter("father_profession").equals("0")) {
						msg = "Please Select Father's Profession";
						return msg;
					}
				}
				if (request.getParameter("mother_name") == null || request.getParameter("mother_name").trim().equals("")) {
					msg = "Please Select Mother's Name";
					return msg;
				}
				if (valid.isOnlyAlphabet(request.getParameter("mother_name")) == false) {
					return "Mother's Name "+ valid.isOnlyAlphabetMSG ;
				   
			     }
				if (!valid.isvalidLength(request.getParameter("mother_name"),valid.nameMax,valid.nameMin)) {
					return  "Mother's Name " + valid.isValidLengthMSG ;
				   
				}
				if (mother_dob == null || mother_dob.equals("null") || mother_dob.equals("DD/MM/YYYY") || mother_dob.equals("")) {
					msg = "Please Enter Mother Date of Birth ";
					return msg;
				} 
				else  if (!valid.isValidDate(mother_dob)){
					return  valid.isValidDateMSG  + " of Mother DOB";
				   
			     }
				else {
					mother_dob_dt = format.parse(mother_dob);
				}
				
				if (request.getParameter("mother_place_birth") == null || request.getParameter("mother_place_birth").equals("")) {
					msg = "Please Select Mother's Place of Birth";
					return msg;
				}
				if (valid.isOnlyAlphabet(request.getParameter("mother_place_birth")) == false) {
					return "Mother's Place of Birth "+ valid.isOnlyAlphabetMSG ;
				   
			     }
				if (!valid.isvalidLength(request.getParameter("mother_place_birth"),valid.nameMax,valid.nameMin)) {
					return "Mother's Place of Birth" + valid.isValidLengthMSG ;
				   
				}
				if (ismotherInservice.equals("yes")) {	
					if (request.getParameter("mother_service") == null || request.getParameter("mother_service").equals("0")) {
						msg = "Please Select Mother's Services";
						return msg;
					}
					
					if (!request.getParameter("mother_service").equals("9")) {
					if (request.getParameter("mother_personal_no") == null || request.getParameter("mother_personal_no").equals("")) {
						msg = "Please Enter Mother's Personal No";
						return msg;
					}
					if (!valid.isOnlyAlphabetNumeric(request.getParameter("mother_personal_no"))) {
						return "Mother's Personal No " + valid.isOnlyAlphabetNumericMSG;
					    
					}
					if (!valid.isvalidLength(request.getParameter("mother_personal_no"),valid.nameMax,valid.nameMin)) {
						return "Mother's Personal No" + valid.isValidLengthMSG;
					  
					}
					if (mother_personal_no.trim().equals("")) {

						mother_personal_no = null;

					}
				}
					if (request.getParameter("mother_service").equals("4")) {
						if (request.getParameter("mother_other") == null || request.getParameter("mother_other").equals("")) {
							return "Please Enter Mother's Other";
							
						}
					}
				}
				
				if (ismotherInservice.equals("no")) {	
					if (request.getParameter("mother_profession") == null || request.getParameter("mother_profession").equals("0")) {
						msg = "Please Select Mother's Profession";
						return msg;
					}
				}
		
		
	
		try {
			if (Integer.parseInt(fd_id) == 0) {

				TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO GN = new TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO();
				GN.setJco_id(Integer.parseInt(jco_id));
				GN.setAuthority(authority);
				GN.setDate_of_authority(Date_authority);
				GN.setFather_dob(father_dob_dt);
				GN.setFather_name(father_name);
				GN.setFather_other(father_other);
				GN.setFather_personal_no(father_personal_no);
				GN.setFather_place_birth(father_place_birth);
				GN.setFather_profession(Integer.parseInt(father_profession));
				GN.setFather_service(father_service);
				GN.setMother_dob(mother_dob_dt);
				GN.setMother_name(mother_name);
				GN.setMother_other(mother_other);
				GN.setMother_personal_no(mother_personal_no);
				GN.setMother_place_birth(mother_place_birth);
				GN.setMother_profession(Integer.parseInt(mother_profession));
				GN.setMother_service(mother_service);
				GN.setFather_proffother(father_proffother);
				GN.setMother_proffother(mother_proffother);
				GN.setCreated_by(username);
				GN.setCreated_date(new Date());
				GN.setStatus(0);

				int id = (int) sessionHQL.save(GN);
				msg = Integer.toString(id);
			} else {
			
				String hql = " update TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO set authority=:authority,date_of_authority=:date_of_authority,"
						+ "father_name=:father_name,father_dob=:father_dob,father_place_birth=:father_place_birth,father_service=:father_service,father_other=:father_other,"
						+ "father_personal_no=:father_personal_no,father_profession=:father_profession,mother_name=:mother_name,mother_dob=:mother_dob,"
						+ "mother_place_birth=:mother_place_birth,mother_service=:mother_service,mother_other=:mother_other,"
						+ "mother_personal_no=:mother_personal_no,mother_profession=:mother_profession,father_proffother=:father_proffother,mother_proffother=:mother_proffother,"
						+ "modified_by=:modified_by,modified_date=:modified_date,status=:status"
						+ " where  id=:id";
				Query query = sessionHQL.createQuery(hql).setString("authority", authority).setDate("date_of_authority", Date_authority)
						//.setString("pan_no", pan_no)
						.setString("father_name", father_name)
						.setTimestamp("father_dob", father_dob_dt)
						.setString("father_place_birth", father_place_birth)
						.setString("father_service", father_service)
						.setString("father_other", father_other)
						.setString("father_personal_no", father_personal_no)
						.setInteger("father_profession", Integer.parseInt(father_profession))
						.setString("mother_name", mother_name)
						.setTimestamp("mother_dob", mother_dob_dt)
						.setString("mother_place_birth", mother_place_birth)
						.setString("mother_service", mother_service)
						.setString("mother_other", mother_other)
						.setString("mother_personal_no", mother_personal_no)
						.setInteger("mother_profession", Integer.parseInt(mother_profession))
						.setString("father_proffother", father_proffother)
						.setString("mother_proffother", mother_proffother)
						.setString("modified_by", username)
						.setTimestamp("modified_date", new Date())
						.setInteger("status", 0)
						.setInteger("id",Integer.parseInt(request.getParameter("fd_id")));
									
				msg = query.executeUpdate() > 0 ? "update" : "0";
				Mmap.put("msg", "Data Updated Successfully");

			}
			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			
			}
			else
			{
				p_comm.update_JcoOr_Census_status(Integer.parseInt(jco_id), username);
			}
			
			tx.commit();
			}catch (RuntimeException e) {e.printStackTrace();
			try {
				tx.rollback();
				msg = "0";
			} catch (RuntimeException rbe) {
				msg = "0";
			}

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "/admin/get_Family_details1_JCO", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> get_Family_details1_JCO(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		int jco_id = Integer.parseInt(request.getParameter("jco_id"));

		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where jco_id=:jco_id and status='0'";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> list = (List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getupdate_census_FamilyData2", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> getupdate_census_FamilyData2(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where  status = '0' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> list = (List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	public String Update_Family_details_JCO(TB_CENSUS_JCO_OR_PARENT obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql = "update TB_CENSUS_JCO_OR_PARENT set father_name=:father_name,father_dob=:father_dob,father_place_birth=:father_place_birth,"
					+ "father_profession=:father_profession,father_service=:father_service,mother_name=:mother_name,mother_dob=:mother_dob,"
					+ "mother_place_birth=:mother_place_birth,mother_profession=:mother_profession,mother_service=:mother_service,father_other=:father_other,"
					+ "mother_other=:mother_other,father_proffother=:father_proffother,mother_proffother=:mother_proffother,"
					+ "modified_by=:modified_by,modified_date=:modified_date "
					+ " where id=:jco_id and status in('1','5')  ";
			Query query = sessionHQL.createQuery(hql).setParameter("father_name", obj.getFather_name()).setParameter("father_dob", obj.getFather_dob())
					.setParameter("father_place_birth", obj.getFather_place_birth()).setParameter("father_profession", obj.getFather_profession())
					.setParameter("father_service", obj.getFather_service()).setParameter("mother_name", obj.getMother_name()).setParameter("mother_dob", obj.getMother_dob())
					.setParameter("mother_place_birth", obj.getMother_place_birth()).setParameter("mother_profession", obj.getMother_profession())
					.setParameter("mother_service", obj.getMother_service()).setParameter("father_other", obj.getFather_other()).setParameter("mother_other",obj.getMother_other())
					.setParameter("father_proffother", obj.getFather_proffother()).setParameter("mother_proffother", obj.getMother_proffother())
					.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())
					.setInteger("jco_id", obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	public String Update_FamilyJCO(TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO obj, String username) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";
		try {
			String hql1 = "update TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO set status=:status where jco_id=:jco_id and status != '0' ";
			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("jco_id", obj.getJco_id());
			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			String hql = "update TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status "
					+ " where  jco_id=:jco_id and status = '0'";
			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("jco_id", obj.getJco_id());
			msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Approve Successfully.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
	//***************************** Start Reject***************************************//
	@RequestMapping(value = "/admin/getFamily_JCOReject", method = RequestMethod.POST)
	public @ResponseBody String getFamily_JCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();
		String reject_remarks = request.getParameter("reject_remarks");
		
		TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO BG = new TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO();
		
		BG.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
		BG.setId(Integer.parseInt(request.getParameter("fd_id")));
		BG.setReject_remarks(reject_remarks);
		String msg1 = Update_Family_Reject(BG, username);

		return msg1;

	}

	public String Update_Family_Reject(TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {

			String hql = "update TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
					+ " where jco_id=:jco_id and status = '0' and id=:id ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username).setString("reject_remarks", obj.getReject_remarks())
					.setTimestamp("modified_date", new Date()).setInteger("status", 3)
					.setInteger("jco_id", obj.getJco_id())
					.setInteger("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "Data Rejected Successfully." : "Data Not Rejected.";

			tx.commit();

		} catch (Exception e) {
			msg = "Data Not Rejected.";
			tx.rollback();
		} finally {
			sessionHQL.close();
		}
		return msg;

	}
	
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> getFamilyDataJCO2( int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where jco_id=:jco_id and status = '3' ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> list = (List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/getFamily_JCO3", method = RequestMethod.POST)
	public @ResponseBody List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> getFamily_JCO3(int jco_id) throws ParseException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where  status = '3' and jco_id=:jco_id ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> list = (List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	
	@RequestMapping(value = "/admin/Family_JCOdelete_action", method = RequestMethod.POST)
	public @ResponseBody String Family_JCOdelete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			String hqlUpdate = "delete from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();
			if (app > 0) {
				msg = "1";
			} else {
				msg = "0";
			}
		} catch (Exception e) {
		}
		return msg;
	}
	
	
	/*******************************************Approve view Start*********************************************************/
	public List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> View_Family_Details_DataCensus( int jco_id,Date modifiedDate) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate = " from TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO where  status = '1' and jco_id=:jco_id and modified_date=:modified_date ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id).setTimestamp("modified_date", modifiedDate);
		@SuppressWarnings("unchecked")
		List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO> list = (List<TB_PSG_UPDATE_CENSUS_FAMILY_DETAILS_JCO>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}
	/*******************************************Approve view End*********************************************************/
}
