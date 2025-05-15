package com.controller.psg.Jco_Update_JcoData;

import java.math.BigInteger;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Jco_COMMON.psg_jco_CommonController;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Jco_Update_JcoData.Search_UpdatedJcoOr_DataDao;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.models.psg.Jco_Update_JcoData.TB_CENSUS_CHILDREN_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Update_JcoOr_DataController {
	@Autowired
	Psg_CommanDAO psg_com;
	Psg_CommonController mcommon = new Psg_CommonController();
	psg_jco_CommonController pjc=new psg_jco_CommonController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	ValidationController valid = new ValidationController();
	@Autowired
	Search_UpdateOffDataDao UOD;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Search_UpdatedJcoOr_DataDao UJD;

	@RequestMapping(value = "/admin/update_jco_data_url", method = RequestMethod.GET)
	public ModelAndView update_jco_data_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			@RequestParam(value = "personnel_no_edit", required = false) String personnel_no,
			@RequestParam(value = "status", required = false) String status,
			@ModelAttribute("personnel_no6") String personnel_no6, @ModelAttribute("status6") String status6,
			@ModelAttribute("rank6") String rank6, @ModelAttribute("jco_id6") String jco_id6,
			@ModelAttribute("unit_name6") String unit_name6, @ModelAttribute("unit_sus_no6") String unit_sus_no6,
			@ModelAttribute("cr_by6") String cr_by6,
			@ModelAttribute("cr_date6") String cr_date6
			) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("update_jco_data_url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		if (status != null && !status.equals("")) {
			Mmap.put("status", status);
		} else {
			Mmap.put("status", 0);
		}
		// CHILDERN DATA
		Mmap.put("getRelationList", mcommon.getRelationList_JCO());
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getSeconded_To", mcommon.getSeconded_To());
		Mmap.put("getTypeofRankList", pjc.getRankjcoList());
		Mmap.put("getNon_EffectiveList", mcommon.getNon_EffectiveList());
		Mmap.put("getLanguageList", mcommon.getLanguageList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());
		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));
		Mmap.put("getParentArmList", mcommon.getParentArmList());
		Mmap.put("getRegtList", mcommon.getRegtList(""));
		Mmap.put("getArmyType", mcommon.getArmyType());
		Mmap.put("getForeign_country", psg_com.getforiegnCountry());
		Mmap.put("getForiegnCountryList", mcommon.getForeign_country());
		Mmap.put("getArmyCourseInstitute", mcommon.getArmyCourseInstitute("2"));
		Mmap.put("getTypeOfCommissionList", mcommon.getTypeOfCommissionList());
		Mmap.put("getSpecializationList", mcommon.getSpecializationList());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());
		Mmap.put("getDivclass", mcommon.getDivclass());
		Mmap.put("getSubject", mcommon.getSubject());
		Mmap.put("getExamination", mcommon.getExamination());
		Mmap.put("getCommandDetailsList", m.getCommandDetailsList());
		Mmap.put("getMedalList", mcommon.getMedalList());
		Mmap.put("getTypeofAppointementJCOList", pjc.getTypeofAppointementListJCO());
		Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());
		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getFiring_event_qe", mcommon.getFiring_event_qe());
		Mmap.put("getBPET_event_qe", mcommon.getBPET_event_qe());
		Mmap.put("getFiring_grade", mcommon.getFiring_grade());
		Mmap.put("getBPET_result", mcommon.getBPET_result());
		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());
		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());
		Mmap.put("getFamily_siblings", mcommon.getFamily_siblings());
		Mmap.put("getIndianLanguageList", mcommon.getIndianLanguageList());
		Mmap.put("getForiegnLangugeList", mcommon.getForiegnLangugeList());
		Mmap.put("getJcoOr_Non_EffectiveList", pjc.getJcoOr_Non_EffectiveList(""));
		Mmap.put("getPSG_CommandList", psg_com.getPSG_CommandList());
		Mmap.put("getPSG_CorpsList", psg_com.getPSG_CorpsList());
		Mmap.put("getPSG_DivList", psg_com.getPSG_DivList());
		Mmap.put("getPSG_BdeList", psg_com.getPSG_BdeList());
		Mmap.put("getPersonalType", mcommon.getPersonalType());
		Mmap.put("getPersonalRemainder", mcommon.getPersonalRemainder());
		Mmap.put("getOFFTypeofRankList", mcommon.getOFFTypeofRankList());
		Mmap.put("getChild_RelationshipList", mcommon.getChild_RelationshipList());
		Mmap.put("getChild_TypeList", mcommon.getChild_TypeList());
		Mmap.put("getShapeStatusList", mcommon.getShapeStatusList());
		Mmap.put("getcCopeList", mcommon.getCopeValueList("C"));
		Mmap.put("getoCopeList", mcommon.getCopeValueList("O"));
		Mmap.put("getpCopeList", mcommon.getCopeValueList("P"));
		Mmap.put("geteCopeList", mcommon.getCopeValueList("E"));
		Mmap.put("getesubCopeList", mcommon.getCopeValueList("E Sub Value"));
		Mmap.put("getOprationList", mcommon.getOprationList());
		Mmap.put("getDivclass", mcommon.getDivclass());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getExaminationTypeList", mcommon.getExaminationTypeList());
		Mmap.put("getCourseName", mcommon.getCourseName());
		Mmap.put("getFdService", mcommon.getFdService());
		Mmap.put("getCourseTypeList", mcommon.getCourseTypeList());
		Mmap.put("getExamList", pjc.getExamList_jco());
	
		Mmap.put("getdisciplinetypeentry", mcommon.getdisciplinetypeentry());
		Mmap.put("getDclredRcvrdList", mcommon.getDclredRcvrdList());
		Mmap.put("getCauseOfDeserter", mcommon.getCauseOfDeserter());
		Mmap.put("getARM_TYPE", mcommon.getARM_TYPE());
		Mmap.put("getExservicemenList", mcommon.getExservicemenList());
		Mmap.put("getCSDCategoryList", mcommon.getCSDCategoryList());
		Mmap.put("getMissingList", mcommon.getMissingList());
		Mmap.put("getCausesOfCasuality", mcommon.getCausesOfCasuality());
		Mmap.put("getDisc_Trialed", mcommon.getDisc_Trialed());
		Mmap.put("getArmyAct_Sec", mcommon.getArmyAct_Sec());
		Mmap.put("getSub_Clause", mcommon.getSub_Clause());
		Mmap.put("getTradeList", pjc.getTradeList());
		 Mmap.put("gettype_of_postingList", pjc.gettype_of_postingList());
		Mmap.put("getClassPayList", pjc.getClassPayList());
		Mmap.put("getPayGroupList", pjc.getPayGroupList());
		Mmap.put("getstatusList", mcommon.getstatusList());
		
		Mmap.put("msg", msg);
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		Mmap.put("date", date1);
		Mmap.put("personnel_no_edit", personnel_no);
		Mmap.put("personnel_no6", personnel_no6);
		Mmap.put("status6", status6);
		Mmap.put("rank6", rank6);
		Mmap.put("jco_id6", jco_id6);
		Mmap.put("unit_name6", unit_name6);
		Mmap.put("unit_sus_no6", unit_sus_no6);
		Mmap.put("cr_by6", cr_by6);
		Mmap.put("cr_date6", cr_date6);	
		return new ModelAndView("Update_JcoOR_DataTiles");
	}

	@RequestMapping(value = "/GetJcoOrCensusDataApprove", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> GetJcoOrCensusDataApprove(int jco_id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		List<Map<String, Object>> list = UJD.GetJcoOrCensusDataApprove(jco_id);
		tx.commit();
		return list;
	}
	@RequestMapping(value = "/GetServingStatusJCO", method = RequestMethod.POST)



	public @ResponseBody ArrayList<ArrayList<String>> GetServingStatusJCO(int jco_id) {



		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();



		Transaction tx = sessionHQL.beginTransaction();



		ArrayList<ArrayList<String>> list = UJD.GetServingStatusJCO(jco_id);

		

		tx.commit();

		return list;

	}
	

	// ***************************Children Details Start

		// **********************************//

		// Children Details GET

		@RequestMapping(value = "/admin/getm_children_detailsData_jco", method = RequestMethod.POST)

		public @ResponseBody List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData_jco(ModelMap Mmap, HttpSession session,

				HttpServletRequest request) throws ParseException {

			String msg = "";

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();


			int jco_id = Integer.parseInt(request.getParameter("jco_id"));



			String hqlUpdate = " from TB_CENSUS_CHILDREN_JCO where  status = '0' and jco_id=:jco_id order by id ";

			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);




			List<TB_CENSUS_CHILDREN_JCO> list = (List<TB_CENSUS_CHILDREN_JCO>) query.list();

			tx.commit();

			sessionHQL.close();

		

			return list;

		}



		/// Children Details save and update





	@RequestMapping(value = "/admin/m_children_details_action_jco", method = RequestMethod.POST)

		public @ResponseBody String m_children_details_action_jco(ModelMap Mmap, HttpSession session,

				HttpServletRequest request) throws ParseException {

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

			Session sessionhql = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionhql.beginTransaction();

			Date date = new Date();

			Date sib_date_birth = null;

			Date sib_adop_dt = null;

			String username = session.getAttribute("username").toString();

			String sib_name = request.getParameter("sib_name");

			String sib_date_of_birth = request.getParameter("sib_date_of_birth");

			String sib_type = request.getParameter("sib_type");

			String sib_relationship = request.getParameter("sib_relationship");



			String sib_adopted = request.getParameter("sib_adopted");

			String sib_adop_date = request.getParameter("sib_adop_date");

			//String aadhar_no = request.getParameter("aadhar_no");

			String pan_no = request.getParameter("pan_no");

			

	        String aadhar_no = "";

			

			String adhar = request.getParameter("aadhar_no");



			if (adhar != null && !adhar.equals("")) {

				aadhar_no = adhar;

			}



			String child_ex = request.getParameter("child_ex");

			int child_service = Integer.parseInt(request.getParameter("child_ser"));

			String child_personal_no = request.getParameter("child_pers_no");

			String other_child_ser = request.getParameter("other_child_ser");

			String child_ser_text = request.getParameter("child");

			String sib_ch_id = request.getParameter("sib_ch_id");

			String jco_id = request.getParameter("jco_id");


			String msg = "";

			

			

			if (sib_name.equals("") || sib_name == null || sib_name == "") {

				msg = "Please Enter Name";

				return msg;

			}
			if (!sib_name.trim().equals("") && sib_name != "" && sib_name != null) {
				if (!valid.isOnlyAlphabet(sib_name)) {
					msg =  "  Name " + valid.isOnlyAlphabetMSG;
					return msg;
				}
				
				if (!valid.isvalidLength(sib_name, valid.nameMax, valid.nameMin)) {
					msg =  " Name " + valid.isValidLengthMSG;
					return msg;
				}
			}
			

				if (sib_date_of_birth == null || sib_date_of_birth.equals("null") || sib_date_of_birth.equals("DD/MM/YYYY") || sib_date_of_birth.equals("")) {

				msg = "Please Select Date of Birth";

				return msg;

			}

				if (!sib_date_of_birth.trim().equals("") && !sib_date_of_birth.equals("DD/MM/YYYY")) {
					if (!valid.isValidDate(sib_date_of_birth)) {
						msg =  valid.isValidDateMSG + " of Birth ";
						return msg;
					} else {
						sib_date_birth = format.parse(sib_date_of_birth);
					}
				}

			if (sib_relationship.equals("0") || sib_relationship == null || sib_relationship == "0") {

				msg = "Please Select Relationship";

				return msg;

			}

			

			if(sib_adopted.equals("Yes") || sib_adopted == "Yes") {

					if (sib_adop_date == null || sib_adop_date.equals("null") || sib_adop_date.equals("DD/MM/YYYY") || sib_adop_date.equals("")) {

					msg = "Please Select Adoption Date";

					return msg;

				}	
					if (!sib_adop_date.equals("") && !sib_adop_date.equals("DD/MM/YYYY")) {
						if (!valid.isValidDate(sib_adop_date)) {
							msg =  valid.isValidDateMSG + " of Adoption  ";
							return msg;
						} else {
							sib_adop_dt= format.parse(sib_adop_date);
						}
						
					}

			}

			

			if (!adhar.equals("") && adhar != null && adhar != "" && adhar.length()<12) {

				msg = "Please Enter Aadhar Valid Card No";

				return msg;

			}
			if (adhar != "") {
				if (!valid.isValidAadhar(adhar)) {
					msg =  valid.isValidAadharMSG;
					return msg;
				}
			}
			if (pan_no != "") {

				if (!valid.isValidPan(pan_no)) {
					msg =  valid.isValidPanMSG;
					return msg;
				}

				if (!valid.PanNoLength(pan_no)) {
					msg =  valid.PanNoMSG;
					return msg;
				}
			}
			

			if(child_ex.equals("Yes")) {

				if(child_service == 0) {

					msg = "Please Select Child Service";

					return msg;

				}

				if(child_service == 1) {

					if(child_personal_no.equals("")) {

						msg = "Please Enter Child Personal No";

						return msg;

					}

				}
				if (!child_personal_no.trim().equals("")) {

					if (child_personal_no.trim().length() < 7 || child_personal_no.trim().length() > 9) {

						msg = "Please Enter Valid Child Personal No";

						return msg;

					}

				}
				if(child_ser_text.equals("OTHER")) {

					if(other_child_ser.equals("")) {

						msg = "Please Enter Other Child Service";

						return msg;

					}
					
					if (other_child_ser != null && !other_child_ser.trim().equals("")) {

						if (!valid.isOnlyAlphabet(other_child_ser)) {
							msg =  " Other Sibling Service " + valid.isOnlyAlphabetMSG;
							return msg;
						}	
						if (!valid.isvalidLength(other_child_ser, valid.nameMax, valid.nameMin)) {
							msg =  " Other Sibling Service " + valid.isValidLengthMSG;
							return msg;
						}
						
					}
					
				}

			}



			



		



			//try {

				// save

				if (Integer.parseInt(sib_ch_id) == 0) {

					TB_CENSUS_CHILDREN_JCO chil = new TB_CENSUS_CHILDREN_JCO();

					chil.setName(sib_name);

					chil.setDate_of_birth(sib_date_birth);

					chil.setType(sib_type);

					chil.setRelationship(Integer.parseInt(sib_relationship));

					chil.setAdoted(sib_adopted);

					if (!sib_adop_date.equals("") && !sib_adop_date.equals("DD/MM/YYYY")) {

						chil.setDate_of_adpoted(sib_adop_dt);

					}

					chil.setAadhar_no(aadhar_no);

					chil.setPan_no(pan_no);

					

					chil.setChild_service(child_service);

					chil.setChild_personal_no(child_personal_no);

					chil.setIf_child_ser(child_ex);

					chil.setOther_child_ser(other_child_ser);


					chil.setJco_id((Integer.parseInt(jco_id)));

					chil.setCreated_by(username);

					chil.setCreated_date(date);

					chil.setStatus(0);
					chil.setInitiated_from("u");

					int id = (int) sessionhql.save(chil);

					msg = Integer.toString(id);

				} else {

					/*--S---REJECT----*/

				/*	TB_CENSUS_DETAIL_Parent CC = new TB_CENSUS_DETAIL_Parent();

					CC.setUpdate_ofr_status(0);

					sessionhql.save(CC);*/

					/*---E--REJECT----*/

					String hql = "update TB_CENSUS_CHILDREN_JCO set status=:status,modified_by=:modified_by ,modified_date=:modified_date ,name=:name, date_of_birth=:date_of_birth,type=:type,adoted=:adoted,date_of_adpoted=:date_of_adpoted,aadhar_no=pgp_sym_encrypt(:aadhar_no,current_setting('miso.version')),pan_no=pgp_sym_encrypt(:pan_no,current_setting('miso.version')),"

							+ " relationship=:relationship,child_service=:child_ser,child_personal_no=:child_per_no,if_child_ser=:child_ex,other_child_ser=:other_child_ser where  id=:id";

					Query query = sessionhql.createQuery(hql).setInteger("status", 0)

							.setTimestamp("date_of_birth", sib_date_birth).setString("name", sib_name)

							.setString("type", sib_type).setInteger("relationship", Integer.parseInt(sib_relationship))

							.setString("adoted", request.getParameter("sib_adopted"))

							.setString("aadhar_no", aadhar_no).setString("pan_no", pan_no)

							.setInteger("id", Integer.parseInt(sib_ch_id)).setString("modified_by", username)

							.setTimestamp("modified_date", date).setInteger("child_ser",child_service).setString("child_per_no",child_personal_no)

							.setString("child_ex",child_ex).setString("other_child_ser",other_child_ser);

					

					

					if (!sib_adop_date.equals("") && !sib_adop_date.equals("DD/MM/YYYY") && sib_adop_date != null) {

						query.setTimestamp("date_of_adpoted", sib_adop_dt);

					} else {

						query.setTimestamp("date_of_adpoted", null);

					}



					msg = query.executeUpdate() > 0 ? "update" : "0";

				}

				

				String approoval_status = request.getParameter("app_status");

				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {

				

				}

				else

				{

					pjc.update_JcoOr_status(Integer.parseInt(jco_id), username);

				}

				

				tx.commit();

			/*} catch (RuntimeException e) {

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

			}*/

			 sessionhql.close();



			return msg;

		}



		// Children Details DELETE



		@RequestMapping(value = "/admin/m_children_details_delete_action_jco", method = RequestMethod.POST)

		public @ResponseBody String m_children_details_delete_action_jco(ModelMap Mmap, HttpSession session,

				HttpServletRequest request) throws ParseException {

			String msg = "";

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			int id = Integer.parseInt(request.getParameter("sib_ch_id"));

			try {

				String hqlUpdate = "delete from TB_CENSUS_CHILDREN_JCO where id=:id";

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

		// ***************************Children Details

		// End**********************************//



		/*--------------------- For Approval Children Details----------------------------------*/



		public @ResponseBody List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData1( int jco_id) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = " from TB_CENSUS_CHILDREN_JCO where  status='0' and jco_id=:jco_id order by id";

			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

			@SuppressWarnings("unchecked")

			List<TB_CENSUS_CHILDREN_JCO> list = (List<TB_CENSUS_CHILDREN_JCO>) query.list();

			tx.commit();

			sessionHQL.close();

			return list;

		}



		public String Update_Children_Details(TB_CENSUS_CHILDREN_JCO obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();



			String msg = "";

			String msg1 = "";

			try {

				/*

				 * String hql1 = "update TB_CENSUS_CHILDREN_JCO set status=:status  " +

				 * " where cen_id=:census_id and jco_id=:jco_id and status != '0' ";

				 * 

				 * Query query1 = sessionHQL.createQuery(hql1).setInteger("status",

				 * 2).setInteger("census_id", obj.getCen_id())

				 * .setInteger("comm_id",obj.getComm_id());

				 * 

				 * msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully."

				 * :"Data Not Updated.";

				 */



				String hql = "update TB_CENSUS_CHILDREN_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

						+ " where  jco_id=:jco_id and status = '0'";



				Query query = sessionHQL.createQuery(hql)



						.setString("modified_by", username).setTimestamp("modified_date", obj.getModified_date())

						.setInteger("status", 1)

						.setInteger("jco_id", obj.getJco_id());



				msg = query.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";



				tx.commit();

			} catch (Exception e) {

				msg = "Data Not Approve Successfully.";

				tx.rollback();

			} finally {

				sessionHQL.close();

			}

			return msg;

		}

		/*--------------------- For REJECT ----------------------------------*/



		@RequestMapping(value = "/admin/getchildren_Reject_jco", method = RequestMethod.POST)

		public @ResponseBody String getchildren_Reject_jco(ModelMap Mmap, HttpSession session, HttpServletRequest request,

				@RequestParam(value = "msg", required = false) String msg) throws ParseException {

			String reject_remarks = request.getParameter("reject_remarks");

			

			String username = session.getAttribute("username").toString();

			TB_CENSUS_CHILDREN_JCO chil = new TB_CENSUS_CHILDREN_JCO();


			chil.setJco_id(Integer.parseInt(request.getParameter("jco_id")));

			chil.setReject_remarks(reject_remarks);


			String msg1 = Children_Reject(chil, username);



			return msg1;



		}



		public String Children_Reject(TB_CENSUS_CHILDREN_JCO obj, String username) {




			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();



			String msg = "";

			String msg1 = "";

			try {

				



				String hql = "update TB_CENSUS_CHILDREN_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "

						+ " where  jco_id=:jco_id and status = '0' ";



				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

						.setTimestamp("modified_date", new Date()).setInteger("status", 3).setString("reject_remarks", obj.getReject_remarks())

						

						.setInteger("jco_id", obj.getJco_id());



				msg = query.executeUpdate() > 0 ? "1" : "0";



				tx.commit();



			} catch (Exception e) {

				msg = "Data Not Rejected.";

				tx.rollback();

			} finally {

				sessionHQL.close();

			}

			return msg;



		}



		public @ResponseBody List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData2( int jco_id) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate = " from TB_CENSUS_CHILDREN_JCO where  status='3' and jco_id=:jco_id order by id";

			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);

			@SuppressWarnings("unchecked")

			List<TB_CENSUS_CHILDREN_JCO> list = (List<TB_CENSUS_CHILDREN_JCO>) query.list();

			tx.commit();

			sessionHQL.close();

			return list;

		}



		@RequestMapping(value = "/admin/getm_children_detailsData3_jco", method = RequestMethod.POST)

		public @ResponseBody List<TB_CENSUS_CHILDREN_JCO> getm_children_detailsData3_jco(ModelMap Mmap, HttpSession session,

				HttpServletRequest request) throws ParseException {

			String msg = "";

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

			Transaction tx = sessionHQL.beginTransaction();


			int jco_id = Integer.parseInt(request.getParameter("jco_id"));



			String hqlUpdate = " from TB_CENSUS_CHILDREN_JCO where  status = '3' and jco_id=:jco_id order by id ";

			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);


			@SuppressWarnings("unchecked")

			List<TB_CENSUS_CHILDREN_JCO> list = (List<TB_CENSUS_CHILDREN_JCO>) query.list();

			tx.commit();

			sessionHQL.close();

		

			return list;

		}
		
}
