package com.controller.psg.update_offr_data;

import java.math.BigInteger;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.models.psg.Transaction.TB_CENSUS_DETAIL_Parent;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_MRG;
import com.models.psg.Transaction.TB_CENSUS_SPOUSE_QUALIFICATION;
import com.models.psg.Transaction.TB_PSG_MARRIED_3008MNS_DTL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Marraige_Controller {
	@Autowired
	private RoleBaseMenuDAO roledao;
	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired

	Psg_CommanDAO psg_com;
	ValidationController valid = new ValidationController();

	@RequestMapping(value = "/admin/censusMarraige", method = RequestMethod.GET)
	public ModelAndView Institute(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		Boolean val = roledao.ScreenRedirect("censusMarraige", session.getAttribute("roleid").toString());
        if(val == false) {
                return new ModelAndView("AccessTiles");
        }

		if(request.getHeader("Referer") == null ) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("getReligionList", mcommon.getReligionList());
		Mmap.put("getMarital_statusList", mcommon.getMarital_statusList());
		Mmap.put("getBloodList", mcommon.getBloodList());
		Mmap.put("getRelationList", mcommon.getRelationList());
		Mmap.put("getPreCadetStatusList", mcommon.getPreCadetStatusList());
		Mmap.put("getNCCTypeList", mcommon.getNCCTypeList());
		Mmap.put("getLanguageSTDList", mcommon.getLanguageSTDList());
		Mmap.put("getLanguagePROOFList", mcommon.getLanguagePROOFList());
		Mmap.put("getMedDistrictName", mcommon.getMedDistrictName("", session));
		Mmap.put("getMedStateName", mcommon.getMedStateName("", session));
		Mmap.put("getMedCountryName", mcommon.getMedCountryName("", session));
		Mmap.put("getNationalityList", mcommon.getNationalityList());
		Mmap.put("getMedCatList", mcommon.getMedCatList());
		Mmap.put("getQualificationTypeList", mcommon.getQualificationTypeList());
		Mmap.put("getInstituteNameList", mcommon.getInstituteNameList());
		Mmap.put("getLanguageList", mcommon.getLanguageList());
		Mmap.put("getMothertoungeList", mcommon.getMothertoungeList());
		Mmap.put("getHair_ColourList", mcommon.getHair_ColourList());
		Mmap.put("getEye_ColourList", mcommon.getEye_ColourList());
		Mmap.put("getMarital_eventList", mcommon.getMarital_eventList());
		Mmap.put("msg", msg);

		return new ModelAndView("censusmarraigeTiles");

	}

	@RequestMapping(value = "/admin/update_family_marriage_action", method = RequestMethod.POST)

	public @ResponseBody String update_family_marriage_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		int final_mId = 0;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();

		Date marriage_date_birth = null;

		Date marriage_dt = null;

		Date divorce_dt = null;

		Date marriage_date_authority_com = null;

		String ser_radio = null;

		String spouse_service = "0";

		String spouseSer_other = null;

		String spouse_personal_no = null;

		String username = session.getAttribute("username").toString();

		String maiden_name = request.getParameter("maiden_name");

		String marriage_date_of_birth = request.getParameter("marriage_date_of_birth");

		Date comm_date = format.parse(request.getParameter("comm_date"));

		// bisag v2 03072023(Observation)
		String spouse_profession = request.getParameter("spouse_profession");

		if (!marriage_date_of_birth.equals("") && !marriage_date_of_birth.equals("DD/MM/YYYY")) {

			marriage_date_birth = format.parse(marriage_date_of_birth);

		}

		String marriage_place_of_birth = request.getParameter("marriage_place_of_birth");

		String marriage_nationality = request.getParameter("marriage_nationality");

		String Nationality_other = null;

		String marriage_date = request.getParameter("marriage_date");

		if (!marriage_date.equals("") && !marriage_date.equals("DD/MM/YYYY")) {

			marriage_dt = format.parse(marriage_date);

		}

		String marr_ch_id = request.getParameter("marr_ch_id");

		String p_id = request.getParameter("p_id");

		String divorce_date = request.getParameter("divorce_date");

		if (!divorce_date.equals("") && !divorce_date.equals("DD/MM/YYYY")) {

			divorce_dt = format.parse(divorce_date);

		}

		String marriage_authority = request.getParameter("marriage_authority");

		String marriage_date_of_authority = request.getParameter("marriage_date_of_authority");

		String marriage_type_of_event = request.getParameter("marital_event");

		String marital_status = request.getParameter("marital_status");

		String pan_card = request.getParameter("pan_card");

		BigInteger comm_id = BigInteger.ZERO;

		comm_id = new BigInteger(request.getParameter("comm_id"));

		String curr_marital_status = request.getParameter("curr_marital_status");

		String separated_from_dt = request.getParameter("separated_from_dt");

		String separated_to_dt = request.getParameter("separated_to_dt");

		String msg = "";

		String adhar_number = "";

		String adhar = request.getParameter("marriage_adhar_no");
		String rank = "";
		String armservice = "";

		if (request.getParameter("rank") != null || !request.getParameter("rank").equals("")) {
			rank = request.getParameter("rank");
		}

		if (request.getParameter("armservice") != null || !request.getParameter("armservice").equals("")) {
			armservice = request.getParameter("armservice");
		}

		if (adhar != null && !adhar.equals("")) {
			adhar_number = adhar;
		}

		if (marriage_type_of_event == null || marriage_type_of_event.equals("0")) {
			return "Please Select Marital Event";
		}
		if (marriage_authority == null || marriage_authority.equals("") || marriage_authority == "") {
			return "Please Enter Authority";
		}
		if (!valid.isvalidLength(marriage_authority, valid.authorityMax, valid.authorityMin)) {
			return " Authority " + valid.isValidLengthMSG;
		}
		if (!valid.isValidAuth(marriage_authority)) {
			return valid.isValidAuthMSG + " Authority";
		}

		if (marriage_date_of_authority == null || marriage_date_of_authority.equals("null")
				|| marriage_date_of_authority.equals("DD/MM/YYYY") || marriage_date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		} else if (!valid.isValidDate(request.getParameter("marriage_date_of_authority"))) {
			return valid.isValidDateMSG + " of Authority";
		} else {
			marriage_date_authority_com = format.parse(marriage_date_of_authority);
		}

		if (mcommon.CompareDate(marriage_date_authority_com, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}

		if (marriage_type_of_event.equals("1") || marriage_type_of_event.equals("3")) {
			if (curr_marital_status.equals("13")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter Date of Separation";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " Date of Separation should not be before Marriage Date";
					}
				}
				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")
						|| separated_to_dt.equals("")) {
					separated_to_dt = null;
				} else {

					if (marriage_date != "" & separated_to_dt != ""
							|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {
						if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {
							return " Separation To Date should not be before Date of Separation";
						}
					}
				}
			} else {

				if (maiden_name == null || maiden_name.equals("")) {
					return "Please Enter Spouse Name";
				}
				if (!valid.isOnlyAlphabet(maiden_name)) {
					return " Spouse Name " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(maiden_name, valid.nameMax, valid.nameMin)) {
					return " Spouse Name " + valid.isValidLengthMSG;
				}
				if (marriage_date == null || marriage_date.equals("null") || marriage_date.equals("DD/MM/YYYY")
						|| marriage_date.equals("")) {
					return "Please Enter Date of Marriage";
				}
				if (marriage_place_of_birth == null || marriage_place_of_birth.equals("")) {
					return "Please Enter Spouse Place of Birth";
				}
				if (!valid.isOnlyAlphabet(marriage_place_of_birth)) {
					return "  Spouse Place of Birth " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(marriage_place_of_birth, valid.nameMax, valid.nameMin)) {
					return " Spouse Place of Birth " + valid.isValidLengthMSG;
				}
				if (marriage_nationality == null || marriage_nationality.equals("0")) {
					return "Please Select Nationality";
				}

				if (request.getParameter("marriage_nationality_other") != null
						&& !request.getParameter("marriage_nationality_other").equals("")) {
					Nationality_other = request.getParameter("marriage_nationality_other");
				}

				if (request.getParameter("marriage_nationality_other") != null
						&& !request.getParameter("marriage_nationality_other").trim().equals("")) {
					if (!valid.isOnlyAlphabet(request.getParameter("marriage_nationality_other"))) {
						return "Spouse Nationality Other " + valid.isOnlyAlphabetMSG;
					}
					if (!valid.isvalidLength(request.getParameter("marriage_nationality_other"), valid.nameMax,
							valid.nameMin)) {
						return "Spouse Nationality Other " + valid.isValidLengthMSG;
					}
				}
				if (marriage_date_of_birth == null || marriage_date_of_birth.equals("null")
						|| marriage_date_of_birth.equals("DD/MM/YYYY") || marriage_date_of_birth.equals("")) {
					return "Please Enter Spouse Date of Birth";
				}
				if (adhar_number == null || adhar_number.equals("")) {
					return "Please Enter Spouse Aadhaar Card No";
				}
				if (adhar != "" || adhar != null) {
					if (!valid.isValidAadhar(adhar_number)) {
						return valid.isValidAadharMSG;
					}
				}

				if (request.getParameter("ser_radio") == null || request.getParameter("ser_radio").equals("")) {
					return "Please Check Spouse In Service";
				} else {
					ser_radio = request.getParameter("ser_radio");
				}
				if (request.getParameter("ser_radio").equals("Yes")) {
					if (request.getParameter("spouse_service") == null
							|| request.getParameter("spouse_service").equals("")) {
						return "Please Select Spouse In Service";
					} else {
						spouse_service = request.getParameter("spouse_service");
					}

					if (request.getParameter("spouse_service").equals("4")) {
						if (request.getParameter("spouseSer_other") == null
								|| request.getParameter("spouseSer_other").equals("")) {
							return "Please Enter Other Service";
						}
						if (request.getParameter("spouseSer_other") != null
								|| !request.getParameter("spouseSer_other").equals("")) {

							if (!valid.isOnlyAlphabet(request.getParameter("spouseSer_other"))) {
								return "Spouse Nationality Other " + valid.isOnlyAlphabetMSG;
							}
							if (!valid.isvalidLength(request.getParameter("spouseSer_other"), valid.nameMax,
									valid.nameMin)) {
								return "Spouse Nationality Other " + valid.isValidLengthMSG;
							} else {
								spouseSer_other = request.getParameter("spouseSer_other");
							}

						}
					} else {

						if (request.getParameter("spouse_personal_no") != null
								&& !request.getParameter("spouse_personal_no").equals("")) {
							spouse_personal_no = request.getParameter("spouse_personal_no");
						}
					}
				}
			}
		}

		if (marriage_type_of_event.equals("2") || marriage_type_of_event.equals("5")
				|| marriage_type_of_event.equals("6")) {
			String d_ww_dt = "";
			if (marriage_type_of_event.equals("2")) {
				d_ww_dt = "Divorce";
			} else {
				d_ww_dt = "Death";
			}
			if (divorce_date == null || divorce_date.equals("null") || divorce_date.equals("DD/MM/YYYY")
					|| divorce_date.equals("")) {
				return "Please Enter The " + d_ww_dt + " Date";
			}
			if (marriage_date != "" & divorce_date != ""
					|| marriage_date != "DD/MM/YYY" & divorce_date != "DD/MM/YYY") {
				if (mcommon.CompareDate(format.parse(divorce_date), format.parse(marriage_date)) == 0) {
					return d_ww_dt + " Date should not be before Marriage Date";
				}
			}
		} else {
			divorce_dt = null;
		}
		if (marriage_type_of_event.equals("4")) {
			if (curr_marital_status.equals("8")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter From Date";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " From Date should not be before Marriage Date";
					}
				}
			}
			if (curr_marital_status.equals("13")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter From Date";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " From Date should not be before Marriage Date";
					}
				}
				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")
						|| separated_to_dt.equals("")) {
					return "Please Enter To Date";
				}
				if (marriage_date != "" & separated_to_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {
						return " To Date should not be before From Date";
					}
				}
			}
		}
		try {
			if (Integer.parseInt(marr_ch_id) == 0) {
				TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();
				fam_marr.setMaiden_name(maiden_name);

				// if (marriage_type_of_event.equals("1") || marriage_type_of_event.equals("3"))
				// {
				fam_marr.setDate_of_birth(marriage_date_birth);
				fam_marr.setMarriage_date(marriage_dt);
				fam_marr.setPlace_of_birth(marriage_place_of_birth);
				fam_marr.setNationality(Integer.parseInt(marriage_nationality));
				fam_marr.setAdhar_number(adhar_number);
				fam_marr.setAuthority(marriage_authority);
				fam_marr.setDate_of_authority(marriage_date_authority_com);
				fam_marr.setPan_card(pan_card);
				fam_marr.setIf_spouse_ser(ser_radio);
				fam_marr.setSpouse_service(Integer.parseInt(spouse_service));
				fam_marr.setSpouse_arm_service(Integer.parseInt(armservice));
				fam_marr.setSpouse_rank(Integer.parseInt(rank));
				fam_marr.setOther_spouse_ser(spouseSer_other);
				fam_marr.setSpouse_personal_no(spouse_personal_no);
				fam_marr.setOther_nationality(Nationality_other);
				fam_marr.setSpouse_profession(Integer.parseInt(spouse_profession));
				// }

				fam_marr.setCen_id(Integer.parseInt(p_id));
				fam_marr.setCreated_by(username);
				fam_marr.setCreated_date(date);
				fam_marr.setComm_id(comm_id);
				fam_marr.setType_of_event(Integer.parseInt(marriage_type_of_event));
				fam_marr.setMarital_status(Integer.parseInt(marital_status));
				fam_marr.setDivorce_date(divorce_dt);

				fam_marr.setStatus(0);
				final_mId = (int) sessionhql.save(fam_marr);
			} else {

				TB_CENSUS_FAMILY_MRG oOBJ = (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class,

						Integer.parseInt(marr_ch_id));

				if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)

						&& (oOBJ.getType_of_event() == 1 || oOBJ.getType_of_event() == 3)

						&& !curr_marital_status.equals("13")) {

					oOBJ.setDate_of_birth(marriage_date_birth);
					oOBJ.setMaiden_name(maiden_name);
					oOBJ.setMarriage_date(marriage_dt);
					oOBJ.setPlace_of_birth(marriage_place_of_birth);
					oOBJ.setNationality(Integer.parseInt(marriage_nationality));
					oOBJ.setAdhar_number(adhar_number);
					oOBJ.setAuthority(marriage_authority);
					oOBJ.setDate_of_authority(marriage_date_authority_com);
					oOBJ.setPan_card(pan_card);
					oOBJ.setIf_spouse_ser(ser_radio);
					oOBJ.setSpouse_service(Integer.parseInt(spouse_service));
					oOBJ.setSpouse_arm_service(Integer.parseInt(armservice));
					oOBJ.setSpouse_rank(Integer.parseInt(rank));
					oOBJ.setOther_spouse_ser(spouseSer_other);
					oOBJ.setSpouse_personal_no(spouse_personal_no);
					oOBJ.setOther_nationality(Nationality_other);
					oOBJ.setSpouse_profession(Integer.parseInt(spouse_profession));
					oOBJ.setModified_by(username);
					oOBJ.setModified_date(date);
					oOBJ.setStatus(0);

					sessionhql.update(oOBJ);

					msg = "update";

				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)

						&& oOBJ.getType_of_event() == Integer.parseInt(marriage_type_of_event)) {

					oOBJ.setModified_by(username);
					oOBJ.setModified_date(date);
					oOBJ.setAuthority(marriage_authority);
					oOBJ.setDate_of_authority(marriage_date_authority_com);
					if (oOBJ.getType_of_event() == 2 || oOBJ.getType_of_event() == 5 || oOBJ.getType_of_event() == 6) {
						oOBJ.setDivorce_date(divorce_dt);
					}

					else if (oOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {
						oOBJ.setDivorce_date(null);
						oOBJ.setSeparated_form_dt(format.parse(separated_from_dt));
						oOBJ.setSeparated_to_dt(null);
					}

					else if (oOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {
						oOBJ.setDivorce_date(null);

						if (separated_to_dt != null)
							oOBJ.setSeparated_to_dt(format.parse(separated_to_dt));
						else
							oOBJ.setSeparated_to_dt(null);
					}

					oOBJ.setStatus(0);

					sessionhql.update(oOBJ);

					msg = "update";

				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)
						&& oOBJ.getType_of_event() != Integer.parseInt(marriage_type_of_event)) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";
					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();
					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();

					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));

					if (nOBJ.getType_of_event() == 2 || nOBJ.getType_of_event() == 5 || nOBJ.getType_of_event() == 6) {

						nOBJ.setDivorce_date(divorce_dt);

					} else if (nOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {

						nOBJ.setDivorce_date(null);

						nOBJ.setSeparated_form_dt(format.parse(separated_from_dt));

						nOBJ.setSeparated_to_dt(null);

					} else if (nOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {

						nOBJ.setDivorce_date(null);

						if (separated_to_dt != null)

							nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));

						else

							nOBJ.setSeparated_to_dt(null);

					}

					nOBJ.setStatus(0);

					final_mId = (int) sessionhql.save(nOBJ);

				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 4

						&& Integer.parseInt(curr_marital_status) == 8) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";

					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();

					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();

					nOBJ = setMarriageData(nOBJ, oOBJ);

					nOBJ.setAuthority(marriage_authority);

					nOBJ.setDate_of_authority(marriage_date_authority_com);

					nOBJ.setCreated_by(username);

					nOBJ.setCreated_date(date);

					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));

					nOBJ.setMarital_status(Integer.parseInt(marital_status));

					nOBJ.setDivorce_date(null);

					nOBJ.setSeparated_to_dt(null);

					nOBJ.setSeparated_form_dt(format.parse(separated_from_dt));

					nOBJ.setStatus(0);

					final_mId = (int) sessionhql.save(nOBJ);

				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 1

						&& Integer.parseInt(curr_marital_status) == 13) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";

					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();

					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();
					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setSeparated_form_dt(oOBJ.getSeparated_form_dt());
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					nOBJ.setDivorce_date(null);

					if (separated_to_dt != null)
						nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));
					else
						nOBJ.setSeparated_to_dt(null);
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);

				} else if (oOBJ.getStatus() == 1 && (Integer.parseInt(marriage_type_of_event) == 5
						|| Integer.parseInt(marriage_type_of_event) == 6
						|| Integer.parseInt(marriage_type_of_event) == 2)) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";

					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();

					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();

					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					nOBJ.setDivorce_date(divorce_dt);
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);
				}
			}

			String approoval_status = request.getParameter("app_status");
			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
			} else {
				mcommon.update_offr_status(Integer.parseInt(p_id), username);
			}

			boolean flag = true;
			if (final_mId != 0) {
				msg = String.valueOf(final_mId);
			} else if (final_mId == 0 && !msg.equals("update")) {
				tx.rollback();
				flag = false;
			}

			if (flag)
				tx.commit();

		} catch (RuntimeException e) {
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

	@RequestMapping(value = "/admin/update_family_marriage_delete_action", method = RequestMethod.POST)
	public @ResponseBody String update_family_marriage_delete_action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("marr_ch_id"));

		try {

			String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where id=:id";

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

	@RequestMapping(value = "/admin/update_getfamily_marriageData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		int event = Integer.parseInt(request.getParameter("marital_event"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		List<TB_CENSUS_FAMILY_MRG> list = null;

		boolean flag = true;

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id";

		if (event != 0) {

			String hqlUpdate1 = "from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id and status=0 and type_of_event=:type_of_event";

			Query query = sessionHQL.createQuery(hqlUpdate1).setInteger("census_id", id).setBigInteger("comm_id", comm_id)

					.setInteger("type_of_event", event);

			list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

			if (list.size() > 0) {

				flag = false;

			} else {

				hqlUpdate += " and status=1 and (marital_status=8 OR marital_status=13)";

			}

		} else {

			hqlUpdate += " and status=0 ";

		}

		if (flag) {

			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

			list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

		}

		tx.commit();

		sessionHQL.close();

		return list;

	}

	/*--------------------- For Approval ----------------------------------*/
	@RequestMapping(value = "/admin/update_getfamily_marriageData1", method = RequestMethod.POST)
	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData1(int id, BigInteger comm_id, int event) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id";

		hqlUpdate += " and status=0 ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);
		@SuppressWarnings("unchecked")
		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();
		tx.commit();
		sessionHQL.close();
		return list;

	}

	public String Update_Marital_Status(TB_CENSUS_FAMILY_MRG obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String msg = "";

		try {

			String hql3 = "update TB_CENSUS_FAMILY_MRG set modified_by=:modified_by,modified_date=:modified_date,separated_to_dt=:separated_to_dt "
					+ " where cen_id=:census_id and comm_id=:comm_id and status = '1' and marital_status=13  ";

			Query query3 = sessionHQL.createQuery(hql3).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date())
					.setTimestamp("separated_to_dt", obj.getSeparated_to_dt()).setInteger("census_id", obj.getCen_id())
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query3.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql1 = "update TB_CENSUS_FAMILY_MRG set status=:status where cen_id=:census_id and comm_id=:comm_id and "
					+ " (status != '0' and status != '-1') ";

			Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2).setInteger("census_id", obj.getCen_id())
					.setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CENSUS_FAMILY_MRG set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0' ";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public String Update_Census_Marital(TB_CENSUS_DETAIL_Parent obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		try {

			String hql = "update TB_CENSUS_DETAIL_Parent set marital_status=:marital_status"

					+ " where id=:census_id and comm_id=:comm_id and status = '1' ";

			Query query = sessionHQL.createQuery(hql).setInteger("census_id", obj.getId())

					.setBigInteger("comm_id", obj.getComm_id()).setInteger("marital_status", obj.getMarital_status());

			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";

			tx.commit();

		} catch (Exception e) {

			msg = "Data Not Updated.";

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}

	/*--------------------- For REJECT ----------------------------------*/

	@RequestMapping(value = "/admin/getMaritalReject", method = RequestMethod.POST)

	public @ResponseBody String getMaritalReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,

			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String username = session.getAttribute("username").toString();

		TB_CENSUS_FAMILY_MRG MrgS = new TB_CENSUS_FAMILY_MRG();

		MrgS.setCen_id(Integer.parseInt(request.getParameter("p_id")));

		MrgS.setComm_id(new BigInteger(request.getParameter("comm_id")));

		MrgS.setId(Integer.parseInt(request.getParameter("marr_ch_id")));

		MrgS.setReject_remarks(request.getParameter("reject_remarks"));

		String msg1 = Marital_Status_Reject(MrgS, username);

		return msg1;

	}

	public String Marital_Status_Reject(TB_CENSUS_FAMILY_MRG obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "0";

		int msg1 = 0;

		int msg2 = 0;

		try {

			// String hql1 = "update TB_CENSUS_DETAIL_Parent set

			// modified_by=:modified_by,modified_date=:modified_date,"

			// + "update_ofr_status=:update_ofr_status where id=:census_id and

			// comm_id=:comm_id and status = '1' and update_ofr_status = '0' ";

			//

			// Query query1 = sessionHQL.createQuery(hql1).setString("modified_by",

			// username).setTimestamp("modified_date", new Date())

			// .setInteger("update_ofr_status", 3).setInteger("census_id",

			// obj.getCen_id()).setInteger("comm_id",obj.getComm_id());

			//

			// msg1 = query1.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not

			// Updated.";

			String hql = "update TB_CENSUS_FAMILY_MRG set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks    "

					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0' and id=:id";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", new Date()).setInteger("status", 3)

					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id())

					.setString("reject_remarks", obj.getReject_remarks()).setInteger("id", obj.getId());

			msg1 = query.executeUpdate() > 0 ? 1 : 0;

			String hql2 = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query2 = sessionHQL.createQuery(hql2).setString("modified_by", username)

					.setTimestamp("modified_date", new Date()).setInteger("status", 3)

					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

			msg2 = query2.executeUpdate() > 0 ? 1 : 0;

			if (msg1 > 0 || msg2 > 0) {
				msg = "1";
			}

			tx.commit();

		} catch (Exception e) {

			msg = "Data Not Rejected.";

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData2(int id, BigInteger comm_id, int event) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id";

		//

		//

		//

		// if(event==2) {

		//

		// hqlUpdate+=" and status=1 and (marital_status=8 OR marital_status=13) ";

		//

		// }

		//

		// else if(event==4) {

		//

		// hqlUpdate+=" and status=1 and (marital_status=8) ";

		//

		// }

		//

		// else {

		hqlUpdate += " and status='3' ";

		// }

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/update_getfamily_marriageData3", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> update_getfamily_marriageData3(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("p_id"));

		int event = Integer.parseInt(request.getParameter("marital_event"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:census_id  and comm_id=:comm_id";

		// if(event==2) {

		//

		// hqlUpdate+=" and status=1 and (marital_status=8 OR marital_status=13) ";

		//

		// }

		//

		// else if(event==4) {

		//

		// hqlUpdate+=" and status=1 and (marital_status=8) ";

		//

		// }

		//

		// else {

		hqlUpdate += " and status='3' ";

		// }

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("census_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	///// Spouse

	@RequestMapping(value = "/admin/getSpouseQualificationData3", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION> getSpouseQualificationData3(ModelMap Mmap,

			HttpSession session, HttpServletRequest request) throws ParseException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		int id = Integer.parseInt(request.getParameter("q_id"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where cen_id=:cen_id and status='3' and comm_id=:comm_id ";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION> update_census_Spouse(int id, BigInteger comm_id) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where cen_id=:cen_id and comm_id=:comm_id and status='0'";

		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("cen_id", id).setBigInteger("comm_id", comm_id);

		@SuppressWarnings("unchecked")

		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	public String Update_SposeQuali_Details(TB_CENSUS_SPOUSE_QUALIFICATION obj, String username) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";

		// String msg1 = "";

		try {

			String hql1 = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where cen_id=:census_id and comm_id=:comm_id and status = '1'";

			Query query1 = sessionHQL.createQuery(hql1).setString("modified_by", username)

					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 2)

					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

			msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

			String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_by=:modified_by,modified_date=:modified_date,status=:status  "

					+ " where cen_id=:census_id and comm_id=:comm_id and status = '0'";

			Query query = sessionHQL.createQuery(hql).setString("modified_by", username)

					.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)

					.setInteger("census_id", obj.getCen_id()).setBigInteger("comm_id", obj.getComm_id());

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

	public TB_CENSUS_FAMILY_MRG setMarriageData(TB_CENSUS_FAMILY_MRG newOBJ, TB_CENSUS_FAMILY_MRG oldOBJ) {

		newOBJ.setDate_of_birth(oldOBJ.getDate_of_birth());

		newOBJ.setMaiden_name(oldOBJ.getMaiden_name());

		newOBJ.setMarriage_date(oldOBJ.getMarriage_date());

		newOBJ.setPlace_of_birth(oldOBJ.getPlace_of_birth());

		newOBJ.setNationality(oldOBJ.getNationality());

		newOBJ.setAdhar_number(oldOBJ.getAdhar_number());

		newOBJ.setPan_card(oldOBJ.getPan_card());

		newOBJ.setIf_spouse_ser(oldOBJ.getIf_spouse_ser());

		newOBJ.setSpouse_service(oldOBJ.getSpouse_service());

		newOBJ.setOther_spouse_ser(oldOBJ.getOther_spouse_ser());

		newOBJ.setSpouse_personal_no(oldOBJ.getSpouse_personal_no());

		newOBJ.setOther_nationality(oldOBJ.getOther_nationality());

		newOBJ.setCen_id(oldOBJ.getCen_id());

		newOBJ.setComm_id(oldOBJ.getComm_id());

		newOBJ.setStatus(0);

		return newOBJ;

	}

	@RequestMapping(value = "/admin/updated_spouse_qualification_action", method = RequestMethod.POST)

	public @ResponseBody String spouse_qualification_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		int currentYear = date.getYear() + 1900;
		String username = session.getAttribute("username").toString();
		String type = request.getParameter("type");
		String examination_pass = request.getParameter("examination_pass");
		String passing_year = request.getParameter("passing_year");
		String div_class = request.getParameter("div_class");
		String subject = request.getParameter("subject");
		String institute = request.getParameter("institute");
		String degree = request.getParameter("degree");
		String specialization = request.getParameter("specialization");
		String exam_other_qua = request.getParameter("exam_other_qua");
		String class_other_qua = request.getParameter("class_other_qua");
		String degree_other = request.getParameter("degree_other");
		String spec_other = request.getParameter("spec_other");
		String qualification_ch_id = request.getParameter("qualification_ch_id");
		String q_id = request.getParameter("q_id");
		String com_id = request.getParameter("com_id");
		String dateofBirthyear = request.getParameter("dateofBirthyear");
		String spouse_id = request.getParameter("spouse_id");
		String rvalue = "";

		if (exam_other_qua.trim().equals("")) {
			exam_other_qua = null;
		}

		if (class_other_qua.trim().equals("")) {
			class_other_qua = null;
		}

		if (degree_other.trim().equals("")) {
			degree_other = null;
		}

		if (spec_other.trim().equals("")) {
			spec_other = null;
		}

		if (type == null || type.equals("0")) {
			return "Please Select Qualification Type";
		}

		if (examination_pass == null || examination_pass.equals("0")) {
			return "Please Select Examination Passed";
		}

		if (exam_other_qua != null && !exam_other_qua.trim().equals("")) {
			if (!valid.isOnlyAlphabet(exam_other_qua)) {
				return " Examination Passed Other" + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(exam_other_qua, valid.nameMax, valid.nameMin)) {
				return "Examination Passed Other " + valid.isValidLengthMSG;
			}
		}

		if (degree == null || degree.equals("0")) {
			return "Please Select Degree Acquried";
		}

		if (degree_other != null && !degree_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(degree_other)) {
				return " Degree Acquried Other" + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(degree_other, valid.nameMax, valid.nameMin)) {
				return "Degree Acquried Other " + valid.isValidLengthMSG;
			}
		}

		if (specialization == null || specialization.equals("0")) {
			return "Please Select Specialization";
		}

		if (spec_other != null && !spec_other.trim().equals("")) {
			if (!valid.isOnlyAlphabet(spec_other)) {
				return " Specialization Other" + valid.isOnlyAlphabetMSG;
			}
			if (!valid.isvalidLength(spec_other, valid.nameMax, valid.nameMin)) {
				return "Specialization Other " + valid.isValidLengthMSG;
			}
		}
		if (passing_year == null || passing_year.trim().equals("")) {
			return "Please Enter Year of passing";
		}

		try {

			if (spouse_id == null || spouse_id.equals("") || spouse_id.equals("0")) {

				String hql_ = "FROM TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=1 and marital_status=8";

				Query query_ = sessionhql.createQuery(hql_);

				query_.setInteger("comm_id", Integer.parseInt(com_id));

				List<TB_CENSUS_FAMILY_MRG> results = query_.list();

				if (results.size() > 0) {

					spouse_id = String.valueOf(results.get(0).getId());

					dateofBirthyear = String.valueOf(results.get(0).getDate_of_birth().getYear() + 1900);

				} else {

					return "Somthing Went Wrong";

				}

			}

		} catch (Exception e) {

			// TODO: handle exception

		}

		if (!passing_year.trim().equals("")) {

			if (Integer.parseInt(passing_year) <= Integer.parseInt(dateofBirthyear)

					|| Integer.parseInt(passing_year) > currentYear) {

				return "Please Enter The Valid Year of Passing";

			}

		}

		if (div_class == null || div_class.equals("0")) {

			return "Please Select The Div/Grade/PCT(%)";

		}

		if (class_other_qua != null && !class_other_qua.trim().equals("")) {

			if (!valid.isOnlyAlphabet(class_other_qua)) {

				return " Div/Grade/PCT(%) Other " + valid.isOnlyAlphabetMSG;

			}

			if (!valid.isvalidLength(class_other_qua, valid.nameMax, valid.nameMin)) {

				return "Div/Grade/PCT(%) Other " + valid.isValidLengthMSG;

			}

		}

		if (subject == null || subject.trim().equals("")) {

			return "Please Select The Subjects";

		}

		if (institute == null || institute.trim().equals("")) {

			return "Please Enter The Institute & place";

		}

		if (institute != null && !institute.trim().equals("")) {

			if (!valid.isOnlyAlphabet(institute)) {

				return " Institute & place " + valid.isOnlyAlphabetMSG;

			}

			if (!valid.isvalidLength(institute, valid.nameMax, valid.nameMin)) {

				return "Institute & place  " + valid.isValidLengthMSG;

			}

		}

		TB_CENSUS_SPOUSE_QUALIFICATION q = new TB_CENSUS_SPOUSE_QUALIFICATION();

		try {

			if (Integer.parseInt(qualification_ch_id) == 0) {

				q.setType(Integer.parseInt(type));

				if (examination_pass != null && !examination_pass.equals("0"))

					q.setExamination_pass(Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					q.setDegree(Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					q.setSpecialization(Integer.parseInt(specialization));

				q.setPassing_year(Integer.parseInt(passing_year));

				q.setDiv_class(div_class);

				q.setSubject(subject);

				q.setInstitute(institute);

				q.setCen_id(Integer.parseInt(q_id));

				q.setComm_id(new BigInteger(com_id));

				q.setSpouse_id(Integer.parseInt(spouse_id));

				q.setCreated_by(username);

				q.setCreated_date(date);

				q.setExam_other(exam_other_qua);

				q.setClass_other(class_other_qua);

				q.setDegree_other(degree_other);

				q.setSpecialization_other(spec_other);

				int id = (int) sessionhql.save(q);

				rvalue = Integer.toString(id);

			} else {

				String hql = "update TB_CENSUS_SPOUSE_QUALIFICATION set modified_by=:modify_by ,modified_date=:modify_on ,type=:type,"

						+ " passing_year=:passing_year,div_class=:div_class,subject=:subject,institute=:institute";

				hql += ",examination_pass=:examination_pass,exam_other=:exam_other,class_other=:class_other ,specialization_other=:specialization_other ,degree_other=:degree_other ";

				hql += ",specialization=:specialization,degree=:degree,status=0 ";

				hql += " where  id=:id";

				Query query = sessionhql.createQuery(hql).setInteger("type", Integer.parseInt(type))

						.setInteger("passing_year", Integer.parseInt(passing_year)).setString("div_class", div_class)

						.setString("subject", subject).setString("institute", institute)

						.setInteger("id", Integer.parseInt(qualification_ch_id)).setString("modify_by", username)

						.setTimestamp("modify_on", date).setString("exam_other", exam_other_qua)

						.setString("class_other", class_other_qua).setString("specialization_other", spec_other)

						.setString("degree_other", degree_other);

				if (examination_pass != null && !examination_pass.equals("0"))

					query.setInteger("examination_pass", Integer.parseInt(examination_pass));

				if (degree != null && !degree.equals("0"))

					query.setInteger("degree", Integer.parseInt(degree));

				if (specialization != null && !specialization.equals("0"))

					query.setInteger("specialization", Integer.parseInt(specialization));

				rvalue = query.executeUpdate() > 0 ? "Data Updated Successfully" : "Data Not Updated";

			}

			String approoval_status = request.getParameter("app_status");

			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {

			} else {

				mcommon.update_offr_status(Integer.parseInt(q_id), username);

			}

			tx.commit();

		} catch (RuntimeException e) {

			try {

				tx.rollback();

				rvalue = "Data Not Updated";

			} catch (RuntimeException rbe) {

				rvalue = "Data Not Updated";

			}

		} finally {

			if (sessionhql != null) {

				sessionhql.close();

			}

		}

		return rvalue;

	}

	@RequestMapping(value = "/admin/getUpdatedSpouseQualificationData", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_SPOUSE_QUALIFICATION> getUpdatedSpouseQualificationData(BigInteger comm_id,
			int app_status) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		String hqlUpdate = " from TB_CENSUS_SPOUSE_QUALIFICATION where comm_id=:comm_id and status=:status";

		Query query = sessionHQL.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id).setInteger("status", app_status);

		List<TB_CENSUS_SPOUSE_QUALIFICATION> list = (List<TB_CENSUS_SPOUSE_QUALIFICATION>) query.list();

		tx.commit();

		sessionHQL.close();

		return list;

	}

	@RequestMapping(value = "/admin/updated_family_marriage_delete_action", method = RequestMethod.POST)

	public @ResponseBody String updated_family_marriage_delete_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

//		int id = Integer.parseInt(request.getParameter("comm_id"));
		BigInteger id = new BigInteger(request.getParameter("comm_id"));

		String quali_delete = request.getParameter("qali_status");

		try {

			String hqlUpdate = "delete from TB_CENSUS_SPOUSE_QUALIFICATION where status in (3,0) and comm_id=:id";

			int app = sessionHQL.createQuery(hqlUpdate).setBigInteger("id",id).executeUpdate();

			int papp = 0;
			if (quali_delete == null || quali_delete.equals("")) {
				hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where status in (3,0) and comm_id=:id";
				papp = sessionHQL.createQuery(hqlUpdate).setBigInteger("id",id).executeUpdate();
			}

			tx.commit();

			if (papp > 0 || app > 0) {

				msg = "1";

			} else {

				msg = "0";

			}

		} catch (Exception e) {

			tx.rollback();

		} finally {

			sessionHQL.close();

		}

		return msg;

	}
	
	@RequestMapping(value = "/admin/census_mrg_detl", method = RequestMethod.POST)

	public @ResponseBody int census_mrg_detl(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		int id = Integer.parseInt(request.getParameter("p_id"));

		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		Session ses1 = HibernateUtil.getSessionFactory().openSession();
		Transaction t2 = ses1.beginTransaction();
		Query q = ses1.createQuery(
				"select distinct marital_status from TB_CENSUS_DETAIL_Parent where id=:id and comm_id=:comm_id");
		q.setParameter("id", id);
		q.setParameter("comm_id", comm_id);

		int marital_status = (int) q.uniqueResult();

		t2.commit();

		ses1.close();

		return marital_status;

	}
	
	@RequestMapping(value = "/admin/check_mrg_detl", method = RequestMethod.POST)
	public @ResponseBody int check_mrg_detl(ModelMap Mmap, HttpSession session,
	        HttpServletRequest request) throws ParseException {
	    int mrg_dtl = 0;
	    int id = Integer.parseInt(request.getParameter("p_id"));
	    BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	    Session ses1 = HibernateUtil.getSessionFactory().openSession();
	    Transaction t2 = ses1.beginTransaction();
	    Query q = ses1.createQuery("select count(id) from TB_CENSUS_FAMILY_MRG where cen_id=:id and comm_id=:comm_id and status !=:status");
	    q.setParameter("id", id);
	    q.setParameter("comm_id", comm_id);
	    q.setParameter("status", 0);
	   
	    Long result = (Long) q.uniqueResult(); 
	    mrg_dtl = result.intValue();
	    t2.commit();
	    ses1.close();

	    return mrg_dtl;
	}
	
	
	/*
	 * @RequestMapping(value = "/admin/getUpdatedmrgdtl", method =
	 * RequestMethod.POST) public @ResponseBody List<TB_CENSUS_FAMILY_MRG>
	 * getUpdatedmrgdtl(BigInteger comm_id,int id, int app_status) {
	 * 
	 * Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction tx = sessionHQL.beginTransaction();
	 * 
	 * String hqlUpdate =
	 * " from TB_CENSUS_FAMILY_MRG where cen_id=:id and comm_id=:comm_id and status=:status"
	 * ;
	 * 
	 * Query query = sessionHQL.createQuery(hqlUpdate).setParameter("id",
	 * id).setBigInteger("comm_id", comm_id).setInteger("status", app_status);
	 * 
	 * List<TB_CENSUS_FAMILY_MRG> list = (List<TB_CENSUS_FAMILY_MRG>) query.list();
	 * 
	 * tx.commit();
	 * 
	 * sessionHQL.close();
	 * 
	 * return list;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/admin/getUpdatedmrgdtl", method = RequestMethod.POST)

	public @ResponseBody List<TB_CENSUS_FAMILY_MRG> getUpdatedmrgdtl(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		String msg = "";

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();

		Integer id =Integer.parseInt(request.getParameter("p_id"));

		int app_status = Integer.parseInt(request.getParameter("app_status"));

		BigInteger comm_id =new BigInteger(request.getParameter("comm_id"));

		List<TB_CENSUS_FAMILY_MRG> list = null;
	

		 String hqlUpdate = " from TB_CENSUS_FAMILY_MRG where cen_id=:id and comm_id=:comm_id and status=:status";

		 Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id).setBigInteger("comm_id", comm_id).setInteger("status", app_status);
			list = (List<TB_CENSUS_FAMILY_MRG>) query.list();
		tx.commit();

		sessionHQL.close();

		return list;

	}
	
	@RequestMapping(value = "/admin/marriage_withoutcensus_3008mns_Action", method = RequestMethod.POST)
	public @ResponseBody String marriage_withoutcensus_3008mns_Action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		Date date = new Date();
		Date dt_authority = null;

		String username = session.getAttribute("username").toString();
		String authority = request.getParameter("marriage_authority1");
		String date_of_authority = request.getParameter("marriage_dt_of_autority");
		String marital_status = request.getParameter("marital_status1");
		Date marital_status_dt = null;
		String marital_status_date = request.getParameter("marriage_dte");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String msg = "";

		if (authority == null || authority.equals("")) {
			return "Please Enter Authority ";
		}
		if (!valid.isValidAuth(authority)) {
			return valid.isValidAuthMSG + " Authority";
		}
		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			return "Authority " + valid.isValidLengthMSG;
		}
		if (date_of_authority == null || date_of_authority.equals("null") || date_of_authority.equals("DD/MM/YYYY")
				|| date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		}
		if (!valid.isValidDate(date_of_authority)) {
			return valid.isValidDateMSG + " of Authority";
		}
		if (!date_of_authority.equals("") && !date_of_authority.equals("DD/MM/YYYY")) {
			dt_authority = format.parse(date_of_authority);
		}

		if (marital_status_date == null || marital_status_date.equals("null")
				|| marital_status_date.equals("DD/MM/YYYY") || marital_status_date.equals("")) {
			return "Please Select Date";
		}
		if (!marital_status_date.equals("") && !marital_status_date.equals("DD/MM/YYYY")) {
			marital_status_dt = format.parse(marital_status_date);
		}

		try {
			
			
		    String hql = "update TB_PSG_MARRIED_3008MNS_DTL set status=:status where  comm_id=:comm_id ";
           Query query = sessionhql.createQuery(hql).setParameter("status", 2).setParameter("comm_id", comm_id);
           query.executeUpdate();

			TB_PSG_MARRIED_3008MNS_DTL n = new TB_PSG_MARRIED_3008MNS_DTL();
			n.setCreated_by(username);
			n.setCreated_date(date);
			n.setAuthority(authority);
			n.setDate_of_authority(dt_authority);
			n.setComm_id(comm_id);
			n.setStatus(1);
			n.setApproved_by(username);
			n.setApproved_date(date);
			n.setMarital_status_date(marital_status_dt);
			n.setMarital_status(marital_status);

			int id = (int) sessionhql.save(n);
			msg = Integer.toString(id);
			
			tx.commit();
		} catch (RuntimeException e) {
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
	
	@RequestMapping(value = "/admin/check_3008mns_fill", method = RequestMethod.POST)
	public @ResponseBody int check_3008mns_fill(ModelMap Mmap, HttpSession session,
	        HttpServletRequest request) throws ParseException {
	    int marital_status = 0;
	    BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
	    Session ses1 = HibernateUtil.getSessionFactory().openSession();
	    Transaction t2 = ses1.beginTransaction();
	    
	    try {
	        Query q = ses1.createQuery("select marital_status from TB_PSG_MARRIED_3008MNS_DTL where comm_id=:comm_id and status =:status");
	        q.setParameter("comm_id", comm_id);
	        q.setParameter("status", 1);

	        Object result = q.uniqueResult();
	        
	        if (result != null) {	          
	            if (result instanceof Integer) {
	                marital_status = (Integer) result;
	            } else if (result instanceof String) {
	                marital_status = Integer.parseInt((String) result);
	            } else {	               
	                marital_status = 0;
	            }
	        }
	        
	        t2.commit();
	    } catch (Exception e) {	       
	        t2.rollback();
	        e.printStackTrace();
	    } finally {
	        ses1.close();
	    }

	    return marital_status;
	}
	
	
	
	
	@RequestMapping(value = "/admin/save_approve_family_marriage_action", method = RequestMethod.POST)

	public @ResponseBody String save_approve_family_marriage_action(ModelMap Mmap, HttpSession session,

			HttpServletRequest request) throws ParseException {

		int final_mId = 0;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		Date date = new Date();
		Date marriage_date_birth = null;
		Date marriage_dt = null;
		Date divorce_dt = null;
		Date marriage_date_authority_com = null;
		String ser_radio = null;
		String spouse_service = "0";
		String spouseSer_other = null;
		String spouse_personal_no = null;
		String username = session.getAttribute("username").toString();
		String maiden_name = request.getParameter("maiden_name");
		String marriage_date_of_birth = request.getParameter("marriage_date_of_birth");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		// bisag v2 03072023(Observation)
		String spouse_profession = request.getParameter("spouse_profession");
		if (!marriage_date_of_birth.equals("") && !marriage_date_of_birth.equals("DD/MM/YYYY")) {
			marriage_date_birth = format.parse(marriage_date_of_birth);
		}
		String marriage_place_of_birth = request.getParameter("marriage_place_of_birth");
		String marriage_nationality = request.getParameter("marriage_nationality");
		String Nationality_other = null;
		String marriage_date = request.getParameter("marriage_date");
		if (!marriage_date.equals("") && !marriage_date.equals("DD/MM/YYYY")) {
			marriage_dt = format.parse(marriage_date);
		}
		String marr_ch_id = request.getParameter("marr_ch_id");
		String p_id = request.getParameter("p_id");
		String divorce_date = request.getParameter("divorce_date");
		if (!divorce_date.equals("") && !divorce_date.equals("DD/MM/YYYY")) {
			divorce_dt = format.parse(divorce_date);
		}

		String marriage_authority = request.getParameter("marriage_authority");
		String marriage_date_of_authority = request.getParameter("marriage_date_of_authority");
		String marriage_type_of_event = request.getParameter("marital_event");
		String marital_status = request.getParameter("marital_status");
		String pan_card = request.getParameter("pan_card");
		BigInteger comm_id = BigInteger.ZERO;
		comm_id = new BigInteger(request.getParameter("comm_id"));
		String curr_marital_status = request.getParameter("curr_marital_status");
		String separated_from_dt = request.getParameter("separated_from_dt");
		String separated_to_dt = request.getParameter("separated_to_dt");
		String msg = "";
		String adhar_number = "";
		String adhar = request.getParameter("marriage_adhar_no");
		String rank = "";
		String armservice = "";

		if (request.getParameter("rank") != null || !request.getParameter("rank").equals("")) {
			rank = request.getParameter("rank");
		}
		if (request.getParameter("armservice") != null || !request.getParameter("armservice").equals("")) {
			armservice = request.getParameter("armservice");
		}
		if (adhar != null && !adhar.equals("")) {
			adhar_number = adhar;
		}
		if (marriage_type_of_event == null || marriage_type_of_event.equals("0")) {
			return "Please Select Marital Event";
		}
		if (marriage_authority == null || marriage_authority.equals("") || marriage_authority == "") {
			return "Please Enter Authority";
		}
		if (!valid.isvalidLength(marriage_authority, valid.authorityMax, valid.authorityMin)) {
			return " Authority " + valid.isValidLengthMSG;
		}
		if (!valid.isValidAuth(marriage_authority)) {
			return valid.isValidAuthMSG + " Authority";
			}
		if (marriage_date_of_authority == null || marriage_date_of_authority.equals("null")
				|| marriage_date_of_authority.equals("DD/MM/YYYY") || marriage_date_of_authority.equals("")) {
			return "Please Select Date of Authority";
		} else if (!valid.isValidDate(request.getParameter("marriage_date_of_authority"))) {
			return valid.isValidDateMSG + " of Authority";
		} else {
			marriage_date_authority_com = format.parse(marriage_date_of_authority);
		}
		if (mcommon.CompareDate(marriage_date_authority_com, comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}
		if (marriage_type_of_event.equals("1") || marriage_type_of_event.equals("3")) {
			if (curr_marital_status.equals("13")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter Date of Separation";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " Date of Separation should not be before Marriage Date";
					}
				}
				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")
						|| separated_to_dt.equals("")) {
					separated_to_dt = null;
				} else {

					if (marriage_date != "" & separated_to_dt != ""
							|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {
						if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {
							return " Separation To Date should not be before Date of Separation";
						}
					}
				}
			} else {

				if (maiden_name == null || maiden_name.equals("")) {
					return "Please Enter Spouse Name";
				}
				if (!valid.isOnlyAlphabet(maiden_name)) {
					return " Spouse Name " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(maiden_name, valid.nameMax, valid.nameMin)) {
					return " Spouse Name " + valid.isValidLengthMSG;
				}
				if (marriage_date == null || marriage_date.equals("null") || marriage_date.equals("DD/MM/YYYY")
						|| marriage_date.equals("")) {
					return "Please Enter Date of Marriage";
				}
				if (marriage_place_of_birth == null || marriage_place_of_birth.equals("")) {
					return "Please Enter Spouse Place of Birth";
				}
				if (!valid.isOnlyAlphabet(marriage_place_of_birth)) {
					return "  Spouse Place of Birth " + valid.isOnlyAlphabetMSG;
				}
				if (!valid.isvalidLength(marriage_place_of_birth, valid.nameMax, valid.nameMin)) {
					return " Spouse Place of Birth " + valid.isValidLengthMSG;
				}
				if (marriage_nationality == null || marriage_nationality.equals("0")) {
					return "Please Select Nationality";
				}

				if (request.getParameter("marriage_nationality_other") != null
						&& !request.getParameter("marriage_nationality_other").equals("")) {
					Nationality_other = request.getParameter("marriage_nationality_other");
				}

				if (request.getParameter("marriage_nationality_other") != null
						&& !request.getParameter("marriage_nationality_other").trim().equals("")) {
					if (!valid.isOnlyAlphabet(request.getParameter("marriage_nationality_other"))) {
						return "Spouse Nationality Other " + valid.isOnlyAlphabetMSG;
					}
					if (!valid.isvalidLength(request.getParameter("marriage_nationality_other"), valid.nameMax,
							valid.nameMin)) {
						return "Spouse Nationality Other " + valid.isValidLengthMSG;
					}
				}
				if (marriage_date_of_birth == null || marriage_date_of_birth.equals("null")
						|| marriage_date_of_birth.equals("DD/MM/YYYY") || marriage_date_of_birth.equals("")) {
					return "Please Enter Spouse Date of Birth";
				}
				if (adhar_number == null || adhar_number.equals("")) {
					return "Please Enter Spouse Aadhaar Card No";
				}
				if (adhar != "" || adhar != null) {
					if (!valid.isValidAadhar(adhar_number)) {
						return valid.isValidAadharMSG;
					}
				}

				if (request.getParameter("ser_radio") == null || request.getParameter("ser_radio").equals("")) {
					return "Please Check Spouse In Service";
				} else {
					ser_radio = request.getParameter("ser_radio");
				}
				if (request.getParameter("ser_radio").equals("Yes")) {
					if (request.getParameter("spouse_service") == null
							|| request.getParameter("spouse_service").equals("")) {
						return "Please Select Spouse In Service";
					} else {
						spouse_service = request.getParameter("spouse_service");
					}

					if (request.getParameter("spouse_service").equals("4")) {
						if (request.getParameter("spouseSer_other") == null
								|| request.getParameter("spouseSer_other").equals("")) {
							return "Please Enter Other Service";
						}
						if (request.getParameter("spouseSer_other") != null
								|| !request.getParameter("spouseSer_other").equals("")) {

							if (!valid.isOnlyAlphabet(request.getParameter("spouseSer_other"))) {
								return "Spouse Nationality Other " + valid.isOnlyAlphabetMSG;
							}
							if (!valid.isvalidLength(request.getParameter("spouseSer_other"), valid.nameMax,
									valid.nameMin)) {
								return "Spouse Nationality Other " + valid.isValidLengthMSG;
							} else {
								spouseSer_other = request.getParameter("spouseSer_other");
							}

						}
					} else {

						if (request.getParameter("spouse_personal_no") != null
								&& !request.getParameter("spouse_personal_no").equals("")) {
							spouse_personal_no = request.getParameter("spouse_personal_no");
						}
					}
				}
			}
		}

		if (marriage_type_of_event.equals("2") || marriage_type_of_event.equals("5")
				|| marriage_type_of_event.equals("6")) {
			String d_ww_dt = "";
			if (marriage_type_of_event.equals("2")) {
				d_ww_dt = "Divorce";
			} else {
				d_ww_dt = "Death";
			}
			if (divorce_date == null || divorce_date.equals("null") || divorce_date.equals("DD/MM/YYYY")
					|| divorce_date.equals("")) {
				return "Please Enter The " + d_ww_dt + " Date";
			}
			if (marriage_date != "" & divorce_date != ""
					|| marriage_date != "DD/MM/YYY" & divorce_date != "DD/MM/YYY") {
				if (mcommon.CompareDate(format.parse(divorce_date), format.parse(marriage_date)) == 0) {
					return d_ww_dt + " Date should not be before Marriage Date";
				}
			}
		} else {
			divorce_dt = null;
		}
		if (marriage_type_of_event.equals("4")) {
			if (curr_marital_status.equals("8")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter From Date";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " From Date should not be before Marriage Date";
					}
				}
			}
			if (curr_marital_status.equals("13")) {
				if (separated_from_dt == null || separated_from_dt.equals("null")
						|| separated_from_dt.equals("DD/MM/YYYY") || separated_from_dt.equals("")) {
					return "Please Enter From Date";
				}
				if (marriage_date != "" & separated_from_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_from_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_from_dt), format.parse(marriage_date)) == 0) {
						return " From Date should not be before Marriage Date";
					}
				}
				if (separated_to_dt == null || separated_to_dt.equals("null") || separated_to_dt.equals("DD/MM/YYYY")
						|| separated_to_dt.equals("")) {
					return "Please Enter To Date";
				}
				if (marriage_date != "" & separated_to_dt != ""
						|| marriage_date != "DD/MM/YYY" & separated_to_dt != "DD/MM/YYY") {
					if (mcommon.CompareDate(format.parse(separated_to_dt), format.parse(separated_from_dt)) == 0) {
						return " To Date should not be before From Date";
					}
				}
			}
		}
		try {
			if (Integer.parseInt(marr_ch_id) == 0) {
				TB_CENSUS_FAMILY_MRG fam_marr = new TB_CENSUS_FAMILY_MRG();
				fam_marr.setMaiden_name(maiden_name);
				fam_marr.setDate_of_birth(marriage_date_birth);
				fam_marr.setMarriage_date(marriage_dt);
				fam_marr.setPlace_of_birth(marriage_place_of_birth);
				fam_marr.setNationality(Integer.parseInt(marriage_nationality));
				fam_marr.setAdhar_number(adhar_number);
				fam_marr.setAuthority(marriage_authority);
				fam_marr.setDate_of_authority(marriage_date_authority_com);
				fam_marr.setPan_card(pan_card);
				fam_marr.setIf_spouse_ser(ser_radio);
				fam_marr.setSpouse_service(Integer.parseInt(spouse_service));
				fam_marr.setSpouse_arm_service(Integer.parseInt(armservice));
				fam_marr.setSpouse_rank(Integer.parseInt(rank));
				fam_marr.setOther_spouse_ser(spouseSer_other);
				fam_marr.setSpouse_personal_no(spouse_personal_no);
				fam_marr.setOther_nationality(Nationality_other);
				fam_marr.setSpouse_profession(Integer.parseInt(spouse_profession));
				fam_marr.setCen_id(Integer.parseInt(p_id));
				fam_marr.setCreated_by(username);
				fam_marr.setCreated_date(date);
				fam_marr.setComm_id(comm_id);
				fam_marr.setType_of_event(Integer.parseInt(marriage_type_of_event));
				fam_marr.setMarital_status(Integer.parseInt(marital_status));
				fam_marr.setDivorce_date(divorce_dt);

				fam_marr.setStatus(0);
				final_mId = (int) sessionhql.save(fam_marr);
			} else {
				TB_CENSUS_FAMILY_MRG oOBJ = (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class,Integer.parseInt(marr_ch_id));
				if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)
						&& (oOBJ.getType_of_event() == 1 || oOBJ.getType_of_event() == 3)
						&& !curr_marital_status.equals("13")) {
					oOBJ.setDate_of_birth(marriage_date_birth);
					oOBJ.setMaiden_name(maiden_name);
					oOBJ.setMarriage_date(marriage_dt);
					oOBJ.setPlace_of_birth(marriage_place_of_birth);
					oOBJ.setNationality(Integer.parseInt(marriage_nationality));
					oOBJ.setAdhar_number(adhar_number);
					oOBJ.setAuthority(marriage_authority);
					oOBJ.setDate_of_authority(marriage_date_authority_com);
					oOBJ.setPan_card(pan_card);
					oOBJ.setIf_spouse_ser(ser_radio);
					oOBJ.setSpouse_service(Integer.parseInt(spouse_service));
					oOBJ.setSpouse_arm_service(Integer.parseInt(armservice));
					oOBJ.setSpouse_rank(Integer.parseInt(rank));
					oOBJ.setOther_spouse_ser(spouseSer_other);
					oOBJ.setSpouse_personal_no(spouse_personal_no);
					oOBJ.setOther_nationality(Nationality_other);
					oOBJ.setSpouse_profession(Integer.parseInt(spouse_profession));
					oOBJ.setModified_by(username);
					oOBJ.setModified_date(date);
					oOBJ.setStatus(0);
					sessionhql.update(oOBJ);
					final_mId=Integer.parseInt(marr_ch_id);
					msg = "update";

				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)
						&& oOBJ.getType_of_event() == Integer.parseInt(marriage_type_of_event)) {
					oOBJ.setModified_by(username);
					oOBJ.setModified_date(date);
					oOBJ.setAuthority(marriage_authority);
					oOBJ.setDate_of_authority(marriage_date_authority_com);
					if (oOBJ.getType_of_event() == 2 || oOBJ.getType_of_event() == 5 || oOBJ.getType_of_event() == 6) {
						oOBJ.setDivorce_date(divorce_dt);
					}

					else if (oOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {
						oOBJ.setDivorce_date(null);
						oOBJ.setSeparated_form_dt(format.parse(separated_from_dt));
						oOBJ.setSeparated_to_dt(null);
					}

					else if (oOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {
						oOBJ.setDivorce_date(null);
						if (separated_to_dt != null)
							oOBJ.setSeparated_to_dt(format.parse(separated_to_dt));
						else
							oOBJ.setSeparated_to_dt(null);
						}
					oOBJ.setStatus(0);
					sessionhql.update(oOBJ);
					final_mId=Integer.parseInt(marr_ch_id);
					msg = "update";

				} else if ((oOBJ.getStatus() == 0 || oOBJ.getStatus() == 3)
						&& oOBJ.getType_of_event() != Integer.parseInt(marriage_type_of_event)) {
					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";
					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();
					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();
					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					if (nOBJ.getType_of_event() == 2 || nOBJ.getType_of_event() == 5 || nOBJ.getType_of_event() == 6) {
						nOBJ.setDivorce_date(divorce_dt);
					} else if (nOBJ.getType_of_event() == 4 && curr_marital_status.equals("8")) {
						nOBJ.setDivorce_date(null);
						nOBJ.setSeparated_form_dt(format.parse(separated_from_dt));
						nOBJ.setSeparated_to_dt(null);

					} else if (nOBJ.getType_of_event() == 1 && curr_marital_status.equals("13")) {
						nOBJ.setDivorce_date(null);
						if (separated_to_dt != null)
							nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));
						else
							nOBJ.setSeparated_to_dt(null);
					}
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);
				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 4
						&& Integer.parseInt(curr_marital_status) == 8) {
					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";
					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id()).executeUpdate();
					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();
					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					nOBJ.setDivorce_date(null);
					nOBJ.setSeparated_to_dt(null);
					nOBJ.setSeparated_form_dt(format.parse(separated_from_dt));
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);

				} else if (oOBJ.getStatus() == 1 && Integer.parseInt(marriage_type_of_event) == 1

						&& Integer.parseInt(curr_marital_status) == 13) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";

					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();

					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();
					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setSeparated_form_dt(oOBJ.getSeparated_form_dt());
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					nOBJ.setDivorce_date(null);

					if (separated_to_dt != null)
						nOBJ.setSeparated_to_dt(format.parse(separated_to_dt));
					else
						nOBJ.setSeparated_to_dt(null);
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);

				} else if (oOBJ.getStatus() == 1 && (Integer.parseInt(marriage_type_of_event) == 5
						|| Integer.parseInt(marriage_type_of_event) == 6
						|| Integer.parseInt(marriage_type_of_event) == 2)) {

					String hqlUpdate = "delete from TB_CENSUS_FAMILY_MRG where comm_id=:comm_id and status=0";
					int app = sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id())
							.executeUpdate();

					TB_CENSUS_FAMILY_MRG nOBJ = new TB_CENSUS_FAMILY_MRG();
					nOBJ = setMarriageData(nOBJ, oOBJ);
					nOBJ.setAuthority(marriage_authority);
					nOBJ.setDate_of_authority(marriage_date_authority_com);
					nOBJ.setCreated_by(username);
					nOBJ.setCreated_date(date);
					nOBJ.setType_of_event(Integer.parseInt(marriage_type_of_event));
					nOBJ.setMarital_status(Integer.parseInt(marital_status));
					nOBJ.setDivorce_date(divorce_dt);
					nOBJ.setStatus(0);
					final_mId = (int) sessionhql.save(nOBJ);
				}
			}



			boolean flag = true;
			if (final_mId != 0) {
				msg = String.valueOf(final_mId);
			} else if (final_mId == 0 && !msg.equals("update")) {
				tx.rollback();
				flag = false;
			}
			
			 
			if (flag) {      
				tx.commit();
				TB_CENSUS_FAMILY_MRG oOBJ = (TB_CENSUS_FAMILY_MRG) sessionhql.get(TB_CENSUS_FAMILY_MRG.class,final_mId);
				String hqlUpdate = "update  TB_CENSUS_DETAIL_Parent set marital_status=:marital_status where comm_id=:comm_id ";
				sessionhql.createQuery(hqlUpdate).setBigInteger("comm_id", oOBJ.getComm_id()).setInteger("marital_status",Integer.parseInt(marital_status))
						.executeUpdate();
				msg =Update_Marital_Status(oOBJ,username);
				
			}
		} catch (RuntimeException e) {
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
	
		

}