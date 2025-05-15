package com.controller.psg.update_3008;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Master.Psg_CommanDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.models.psg.Transaction.TB_CENSUS_MEDICAL_CATEGORY;
import com.models.psg.Transaction.TB_MEDICAL_CATEGORY_HISTORY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_Medical_3008 {
	@Autowired
	Psg_CommanDAO psg_dao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	ValidationController valid = new ValidationController();
	Psg_CommonController mcommon = new Psg_CommonController();

	@Autowired
	CensusAprovedDAO censusAprovedDAO; 

	@RequestMapping(value = "/admin/medical_category_3008_Action", method = RequestMethod.POST)
	public @ResponseBody String medical_category_3008_Action(ModelMap Mmap, HttpSession session,
			HttpServletRequest request) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		Boolean validData = false;

		int sShape_count = Integer.parseInt(request.getParameter("sShape_count").toString()); // total count
		int sShapeOld_count = Integer.parseInt(request.getParameter("sShapeOld_count").toString());// oldcount

		int hShape_count = Integer.parseInt(request.getParameter("hShape_count").toString()); // total count
		int hShapeOld_count = Integer.parseInt(request.getParameter("hShapeOld_count").toString());// oldcount

		int aShape_count = Integer.parseInt(request.getParameter("aShape_count").toString()); // total count
		int aShapeOld_count = Integer.parseInt(request.getParameter("aShapeOld_count").toString());// oldcount

		int pShape_count = Integer.parseInt(request.getParameter("pShape_count").toString()); // total count
		int pShapeOld_count = Integer.parseInt(request.getParameter("pShapeOld_count").toString());// oldcount

		int eShape_count = Integer.parseInt(request.getParameter("eShape_count").toString()); // total count
		int eShapeOld_count = Integer.parseInt(request.getParameter("eShapeOld_count").toString());// oldcount

		int cCope_count = Integer.parseInt(request.getParameter("cCope_count").toString()); // total count
		int cCopeOld_count = Integer.parseInt(request.getParameter("cCopeOld_count").toString());// oldcount

		int oCope_count = Integer.parseInt(request.getParameter("oCope_count").toString()); // total count
		int oCopeOld_count = Integer.parseInt(request.getParameter("oCopeOld_count").toString());// oldcount

		int pCope_count = Integer.parseInt(request.getParameter("pCope_count").toString()); // total count
		int pCopeOld_count = Integer.parseInt(request.getParameter("pCopeOld_count").toString());// oldcount

		int eCope_count = Integer.parseInt(request.getParameter("eCope_count").toString()); // total count
		int eCopeOld_count = Integer.parseInt(request.getParameter("eCopeOld_count").toString());// oldcount
		int census_id = Integer.parseInt(request.getParameter("census_id"));
		String authority = request.getParameter("madical_authority");
		String date_of_authority = request.getParameter("madical_date_of_authority");
		String mad_classification = request.getParameter("mad_classification_count");
		String p_id = request.getParameter("census_id");
		BigInteger comm_id = new BigInteger(request.getParameter("comm_id"));
		String checkbox_1bx = request.getParameter("check_1bx");
		String _1BX_from_date = request.getParameter("1bx_from_date");
		String _1BX_to_date = request.getParameter("1bx_to_date");
		String _1BX_diagnosis = request.getParameter("1bx_diagnosis_code");
		Date comm_date = format.parse(request.getParameter("comm_date"));
		String msg = "";
		int diffrence;

		Session session3 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx3 = session3.beginTransaction();
		if (authority == null || authority.equals("")) {
			tx3.rollback();
			msg = "Please Enter The Authority ";
			return msg;
		}
		if (!valid.isValidAuth(authority)) {
			msg = valid.isValidAuthMSG + " Authority";
			return msg;
		}
		if (!valid.isvalidLength(authority, valid.authorityMax, valid.authorityMin)) {
			msg = "Authority " + valid.isValidLengthMSG;
			return msg;
		}
		if (date_of_authority == null || date_of_authority.equals("") || date_of_authority.equals("DD/MM/YYYY")) {
			tx3.rollback();
			msg = "Please Enter  Date of Authority ";
			return msg;
		}
		if (!valid.isValidDate(date_of_authority)) {
			msg = valid.isValidDateMSG + " of Authority";
			return msg;
		}
		if (mcommon.CompareDate(format.parse(date_of_authority), comm_date) == 0) {
			return "Authority Date should be Greater than Commission Date";
		}

		if (mad_classification == null || mad_classification.equals("0")) {
			tx3.rollback();
			msg = "Please Select The Medical Classification ";
			return msg;
		}

		try {

			Query qc = session3.createQuery(
					"select count(id) from TB_CENSUS_MEDICAL_CATEGORY where date_of_authority=:date_of_authority and comm_id=:comm_id and cancel_status!=1 and status in (1,2)");
			qc.setParameter("comm_id", comm_id).setTimestamp("date_of_authority", format.parse(date_of_authority));
			long c = (Long) qc.uniqueResult();

			if (c > 0) {
				tx3.rollback();
				msg = "Data With Same Authority Date Already Exists!!";
				return msg;
			}
			String hql12 = "update TB_MEDICAL_CATEGORY_HISTORY set status=:status where  comm_id=:comm_id and status=1";

			Query query12 = session3.createQuery(hql12).setInteger("status", 2).setBigInteger("comm_id", comm_id);

			msg = query12.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";

			String hql11 = "update TB_CENSUS_MEDICAL_CATEGORY set status=:status where comm_id=:comm_id and (status != '0' and status != '-1') ";

			Query query11 = session3.createQuery(hql11).setInteger("status", 2).setBigInteger("comm_id", comm_id);

			msg = query11.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";

			if (checkbox_1bx != null && checkbox_1bx.equals("1BX")) {

				if ((_1BX_from_date == null || _1BX_from_date.equals("") || _1BX_from_date.equals("DD/MM/YYYY"))
						|| (_1BX_to_date == null || _1BX_to_date.equals("") || _1BX_to_date.equals("DD/MM/YYYY"))) {
					tx3.rollback();
					msg = "Please Enter From Date And To Date For Shape 1BX";
					return msg;
				}
				if (_1BX_from_date != null || _1BX_from_date.trim().equals("") || _1BX_from_date.equals("DD/MM/YYYY")) {
					if (!valid.isValidDate(_1BX_from_date)) {
						msg = valid.isValidDateMSG + " of From ";

						return msg;
					}
				}
				if (_1BX_to_date != null || _1BX_to_date.trim().equals("") || _1BX_to_date.equals("DD/MM/YYYY")) {
					if (!valid.isValidDate(_1BX_to_date)) {
						msg = valid.isValidDateMSG + " of To ";

						return msg;
					}
				}
				if (format.parse(_1BX_to_date).compareTo(format.parse(_1BX_from_date)) < 0) {
					msg = "To Date Should Be Greater Than Or Equal To From Date For Shape 1BX";
					return msg;
				}

				if (_1BX_diagnosis.equals("") || _1BX_diagnosis == null) {
					tx3.rollback();
					msg = "Please  Enter The Diagnosis For Shape 1BX";
					return msg;
				}
				if (!_1BX_diagnosis.equals("")) {
					String[] arrOfStr = _1BX_diagnosis.split("-", 2);
					_1BX_diagnosis = arrOfStr[0];
				}
				String hqlUpdate = "delete from TB_CENSUS_MEDICAL_CATEGORY where cen_id=:id and status=0";
				int app = session3.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(p_id)).executeUpdate();

				String li[] = { "S", "H", "A", "P", "E", "C_C", "C_O", "C_P", "C_E" };
				String li_id[] = { "sShape_ch_id1", "hShape_ch_id1", "aShape_ch_id1", "pShape_ch_id1", "eShape_ch_id1",
						"cCope_ch_id1", "oCope_ch_id1", "pCope_ch_id1", "eCope_ch_id1" };
				for (int i = 0; i < li.length; i++) {
					String ch_id = request.getParameter(li_id[i]);
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat;
//					if(Integer.parseInt(ch_id) == 0) {
					Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();
//					}
//					else {
//						 Mad_cat =(TB_CENSUS_MEDICAL_CATEGORY)session3.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(ch_id));
//					}
					Mad_cat.setShape(li[i]);
					if (i == 5 || i == 8) {
						Mad_cat.setOther("");
					}
					if (i < 5) {
						Mad_cat.setShape_status(1);
						Mad_cat.setShape_value("1");
					} else
						Mad_cat.setShape_value("0");

					if ((_1BX_from_date != null && !_1BX_from_date.equals(""))
							&& (_1BX_to_date != null && !_1BX_to_date.equals(""))) {
						Mad_cat.setFrom_date_1bx(format.parse(_1BX_from_date));
						Mad_cat.setTo_date_1bx(format.parse(_1BX_to_date));
					}
					Mad_cat.setCen_id(Integer.parseInt(p_id));
					Mad_cat.setComm_id(comm_id);
					Mad_cat.setDiagnosis_1bx(_1BX_diagnosis);
					Mad_cat.setClasification("NIL");
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setStatus(1);
//					if(Integer.parseInt(ch_id) == 0) {
					Mad_cat.setCreated_by(username);
					Mad_cat.setCreated_on(date);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					session3.save(Mad_cat);
//					}
//					else {
//						Mad_cat.setModify_by(username);
//						Mad_cat.setModify_on(date);
//						session3.update(Mad_cat);
//					}

				}

				session3.flush();
				session3.clear();
			}

			else {
				////////////////////////////// S SHAPE ////////////////////////////
				for (int i = 1; i <= sShapeOld_count; i++) {

					String shape_status = request.getParameter("s_status" + i);
					String shape_value = request.getParameter("sShape_value" + i);
					String from_date = request.getParameter("s_from_date" + i);
					String to_date = request.getParameter("s_to_date" + i);
					String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

					String shape_ch = request.getParameter("sShape_ch_id" + i);

					if (!diagnosis_1.equals("")) {
						String[] arrOfStr = diagnosis_1.split("-", 2);
						diagnosis_1 = arrOfStr[0];
					}

					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Status " + i + " Row";
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Value " + i + " Row";
						return msg;
					}
					// 26-01-1994
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The From Date " + i + " Row";
							return msg;
						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + "  of The S-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The To Date " + i + " Row";
							return msg;
						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The S-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
							return msg;
						}
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);

//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}

					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_1);

					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				// Logic for INSERT

				diffrence = sShape_count - sShapeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = sShapeOld_count + 1; i <= sShape_count; i++) {
						String shape_status = request.getParameter("s_status" + i);
						String shape_value = request.getParameter("sShape_value" + i);
						String from_date = request.getParameter("s_from_date" + i);
						String to_date = request.getParameter("s_to_date" + i);
						String diagnosis_1 = request.getParameter("_diagnosis_code1" + i);

						if (!diagnosis_1.equals("")) {
							String[] arrOfStr = diagnosis_1.split("-", 2);
							diagnosis_1 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Value " + i + " Row";
							return msg;
						}

//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + "  of The S-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The To Date " + i + " Row";
								return msg;
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The S-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
								return msg;
							}
						}

						Mad_cat.setShape("S");
						Mad_cat.setShape_status(Integer.parseInt(shape_status));
						Mad_cat.setShape_value(shape_value);

//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));
							Mad_cat.setTo_date(format.parse(to_date));
						} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}

						Mad_cat.setDiagnosis(diagnosis_1);

						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);
						session3.flush();
						session3.clear();
					}
				}

//////////////////////////////H SHAPE ////////////////////////////

				for (int i = 1; i <= hShapeOld_count; i++) {

					String shape_status = request.getParameter("h_status" + i);
					String shape_value = request.getParameter("hShape_value" + i);
					String from_date = request.getParameter("h_from_date" + i);
					String to_date = request.getParameter("h_to_date" + i);
					String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);
					String shape_ch = request.getParameter("hShape_ch_id" + i);

					if (!diagnosis_2.equals("")) {
						String[] arrOfStr = diagnosis_2.split("-", 2);
						diagnosis_2 = arrOfStr[0];
					}

					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Status " + i + " Row";
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Value " + i + " Row";
						return msg;
					}

//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The From Date " + i + " Row";
							return msg;
						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + "  of The H-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The To Date " + i + " Row";
							return msg;
						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The H-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
							return msg;
						}
					}
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);

//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_2);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				// Logic for INSERT

				diffrence = hShape_count - hShapeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = hShapeOld_count + 1; i <= hShape_count; i++) {
						String shape_status = request.getParameter("h_status" + i);
						String shape_value = request.getParameter("hShape_value" + i);
						String from_date = request.getParameter("h_from_date" + i);
						String to_date = request.getParameter("h_to_date" + i);
						String diagnosis_2 = request.getParameter("_diagnosis_code2" + i);
						if (!diagnosis_2.equals("")) {
							String[] arrOfStr = diagnosis_2.split("-", 2);
							diagnosis_2 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Value " + i + " Row";
							return msg;
						}

//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + "  of The H-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The To Date " + i + " Row";
								return msg;
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The H-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
								return msg;
							}
						}

						Mad_cat.setShape("H");
						Mad_cat.setShape_status(Integer.parseInt(shape_status));
						Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));
							Mad_cat.setTo_date(format.parse(to_date));
						} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}
						Mad_cat.setDiagnosis(diagnosis_2);

						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//		//////////////////////////////A SHAPE ////////////////////////////

				for (int i = 1; i <= aShapeOld_count; i++) {

					String shape_status = request.getParameter("a_status" + i);
					String shape_value = request.getParameter("aShape_value" + i);
					String from_date = request.getParameter("a_from_date" + i);
					String to_date = request.getParameter("a_to_date" + i);
					String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);
					String shape_ch = request.getParameter("aShape_ch_id" + i);

					if (!diagnosis_3.equals("")) {
						String[] arrOfStr = diagnosis_3.split("-", 2);
						diagnosis_3 = arrOfStr[0];
					}

					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Status " + i + " Row";
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Value " + i + " Row";
						return msg;
					}

//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The From Date " + i + " Row";
							return msg;
						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The A-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The To Date " + i + " Row";
							return msg;
						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The A-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
							return msg;
						}
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("")) || (to_date != null && !to_date.equals(""))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_3);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				// Logic for INSERT

				diffrence = aShape_count - aShapeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = aShapeOld_count + 1; i <= aShape_count; i++) {
						String shape_status = request.getParameter("a_status" + i);
						String shape_value = request.getParameter("aShape_value" + i);
						String from_date = request.getParameter("a_from_date" + i);
						String to_date = request.getParameter("a_to_date" + i);
						String diagnosis_3 = request.getParameter("_diagnosis_code3" + i);

						if (!diagnosis_3.equals("")) {
							String[] arrOfStr = diagnosis_3.split("-", 2);
							diagnosis_3 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Value " + i + " Row";
							return msg;
						}

//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The A-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The To Date " + i + " Row";
								return msg;
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The A-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
								return msg;
							}
						}

						Mad_cat.setShape("A");
						Mad_cat.setShape_status(Integer.parseInt(shape_status));
						Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));
							Mad_cat.setTo_date(format.parse(to_date));
						} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}
						Mad_cat.setDiagnosis(diagnosis_3);

						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setStatus(1);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//		//////////////////////////////P SHAPE ////////////////////////////

				for (int i = 1; i <= pShapeOld_count; i++) {

					String shape_status = request.getParameter("p_status" + i);
					String shape_value = request.getParameter("pShape_value" + i);
					String from_date = request.getParameter("p_from_date" + i);
					String to_date = request.getParameter("p_to_date" + i);
					String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);
					String shape_ch = request.getParameter("pShape_ch_id" + i);

					if (!diagnosis_4.equals("")) {
						String[] arrOfStr = diagnosis_4.split("-", 2);
						diagnosis_4 = arrOfStr[0];
					}

					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Status " + i + " Row";
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Value " + i + " Row";
						return msg;
					}

//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The From Date " + i + " Row";
							return msg;
						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The P-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The To Date " + i + " Row";
							return msg;
						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The P-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
							return msg;
						}
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);
//		if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_4);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = pShape_count - pShapeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = pShapeOld_count + 1; i <= pShape_count; i++) {
						String shape_status = request.getParameter("p_status" + i);
						String shape_value = request.getParameter("pShape_value" + i);
						String from_date = request.getParameter("p_from_date" + i);
						String to_date = request.getParameter("p_to_date" + i);
						String diagnosis_4 = request.getParameter("_diagnosis_code4" + i);

						if (!diagnosis_4.equals("")) {
							String[] arrOfStr = diagnosis_4.split("-", 2);
							diagnosis_4 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Value " + i + " Row";
							return msg;
						}

//					if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The P-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The To Date " + i + " Row";
								return msg;
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The P-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
								return msg;
							}
						}

						Mad_cat.setShape("P");
						Mad_cat.setShape_status(Integer.parseInt(shape_status));
						Mad_cat.setShape_value(shape_value);
//				if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));
							Mad_cat.setTo_date(format.parse(to_date));
						} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}
						Mad_cat.setDiagnosis(diagnosis_4);

						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);
						session3.flush();
						session3.clear();
					}
				}

//			//////////////////////////////E SHAPE ////////////////////////////

				for (int i = 1; i <= eShapeOld_count; i++) {

					String shape_status = request.getParameter("e_status" + i);
					String shape_value = request.getParameter("eShape_value" + i);
					String from_date = request.getParameter("e_from_date" + i);
					String to_date = request.getParameter("e_to_date" + i);
					String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);
					String shape_ch = request.getParameter("eShape_ch_id" + i);

					if (!diagnosis_5.equals("")) {
						String[] arrOfStr = diagnosis_5.split("-", 2);
						diagnosis_5 = arrOfStr[0];
					}

					if (shape_status == null || shape_status.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Status " + i + " Row";
						return msg;
					}
					if (shape_value == null || shape_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Shape Value " + i + " Row";
						return msg;
					}

//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The From Date " + i + " Row";
							return msg;
						}
						if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(from_date)) {
								msg = valid.isValidDateMSG + " of The E-Shape From ";

								return msg;
							}
						}

						if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
							tx3.rollback();
							msg = "Please Enter The To Date " + i + " Row";
							return msg;
						}
						if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							if (!valid.isValidDate(to_date)) {
								msg = valid.isValidDateMSG + " of The E-Shape To ";

								return msg;
							}
						}
						if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
							msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
							return msg;
						}
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(shape_ch));
					Mad_cat.setShape_status(Integer.parseInt(shape_status));
					Mad_cat.setShape_value(shape_value);
//			if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
					if (!shape_status.equals("1")) {
						Mad_cat.setFrom_date(format.parse(from_date));
						Mad_cat.setTo_date(format.parse(to_date));
					} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						Mad_cat.setFrom_date(format.parse(from_date));
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setDiagnosis(diagnosis_5);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = eShape_count - eShapeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = eShapeOld_count + 1; i <= eShape_count; i++) {
						String shape_status = request.getParameter("e_status" + i);
						String shape_value = request.getParameter("eShape_value" + i);
						String from_date = request.getParameter("e_from_date" + i);
						String to_date = request.getParameter("e_to_date" + i);
						String diagnosis_5 = request.getParameter("_diagnosis_code5" + i);
						if (!diagnosis_5.equals("")) {
							String[] arrOfStr = diagnosis_5.split("-", 2);
							diagnosis_5 = arrOfStr[0];
						}

						if (shape_status == null || shape_status.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Status " + i + " Row";
							return msg;
						}
						if (shape_value == null || shape_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Shape Value " + i + " Row";
							return msg;
						}

//						if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							if (from_date == null || from_date.equals("") || from_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The From Date " + i + " Row";
								return msg;
							}
							if (from_date != null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(from_date)) {
									msg = valid.isValidDateMSG + " of The E-Shape From ";

									return msg;
								}
							}

							if (to_date == null || to_date.equals("") || to_date.equals("DD/MM/YYYY")) {
								tx3.rollback();
								msg = "Please Enter The To Date " + i + " Row";
								return msg;
							}
							if (to_date != null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
								if (!valid.isValidDate(to_date)) {
									msg = valid.isValidDateMSG + " of The E-Shape To ";

									return msg;
								}
							}
							if (format.parse(to_date).compareTo(format.parse(from_date)) < 0) {
								msg = "To Date Should Be Greater Than Or Equal To From Date In Row " + i + "";
								return msg;
							}
						}

						Mad_cat.setShape("E");
						Mad_cat.setShape_status(Integer.parseInt(shape_status));
						Mad_cat.setShape_value(shape_value);
//					if (!shape_status.equals("1") || (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) || (to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY"))) {
						if (!shape_status.equals("1")) {
							Mad_cat.setFrom_date(format.parse(from_date));
							Mad_cat.setTo_date(format.parse(to_date));
						} else if (from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
							Mad_cat.setFrom_date(format.parse(from_date));
						}
						Mad_cat.setDiagnosis(diagnosis_5);

						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//				//////////////////////////////C COPE //////////////////////////////

				for (int i = 1; i <= cCopeOld_count; i++) {
					String cope_ch = request.getParameter("cCope_ch_id" + i);
					String cope_value = request.getParameter("c_cvalue" + i);
					String cope_other = request.getParameter("c_cother" + i);

					if (cope_value.equals("2 [c]") && cope_other.equals("")) {
						tx3.rollback();
						msg = "Please Enter Other " + i + " Row";
						return msg;
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					if (cope_value.equals("2 [c]")) {
						Mad_cat.setOther(cope_other);
					} else {
						Mad_cat.setOther("");
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setShape_value(cope_value);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = cCope_count - cCopeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = cCopeOld_count + 1; i <= cCope_count; i++) {
						String cope_value = request.getParameter("c_cvalue" + i);
						String cope_other = request.getParameter("c_cother" + i);

						if (cope_value.equals("2 [c]") && cope_other.equals("")) {
							tx3.rollback();
							msg = "Please Enter Other " + i + " Row";
							return msg;
						}

						if (cope_value.equals("2 [c]")) {
							Mad_cat.setOther(cope_other);
						} else {
							Mad_cat.setOther(null);
						}
						Mad_cat.setShape("C_C");
						Mad_cat.setShape_value(cope_value);
						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setStatus(1);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//				//////////////////////////////O COPE //////////////////////////////

				for (int i = 1; i <= oCopeOld_count; i++) {
					String cope_ch = request.getParameter("oCope_ch_id" + i);
					String cope_value = request.getParameter("c_ovalue" + i);

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setShape_value(cope_value);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = oCope_count - oCopeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = oCopeOld_count + 1; i <= oCope_count; i++) {
						String cope_value = request.getParameter("c_ovalue" + i);

						Mad_cat.setShape("C_O");
						Mad_cat.setShape_value(cope_value);
						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//				//////////////////////////////P COPE //////////////////////////////

				for (int i = 1; i <= pCopeOld_count; i++) {
					String cope_ch = request.getParameter("pCope_ch_id" + i);
					String cope_value = request.getParameter("c_pvalue" + i);

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setShape_value(cope_value);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = pCope_count - pCopeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = pCopeOld_count + 1; i <= pCope_count; i++) {
						String cope_value = request.getParameter("c_pvalue" + i);

						Mad_cat.setShape("C_P");
						Mad_cat.setShape_value(cope_value);
						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						Mad_cat.setStatus(1);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}

//				//////////////////////////////E COPE //////////////////////////////

				for (int i = 1; i <= eCopeOld_count; i++) {
					String cope_ch = request.getParameter("eCope_ch_id" + i);
					String cope_value = request.getParameter("c_evalue" + i);
					String cope_sub_value = request.getParameter("c_esubvalue" + i);
					String cope_other = request.getParameter("c_esubvalueother" + i);

					if (cope_value.equals("1") && cope_sub_value.equals("0")) {
						tx3.rollback();
						msg = "Please Select The Cope Sub Value " + i + " Row";
						return msg;
					}

					if (cope_sub_value.equals("k") && cope_other.equals("")) {
						tx3.rollback();
						msg = "Please Enter Other " + i + " Row";
						return msg;
					}

					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = (TB_CENSUS_MEDICAL_CATEGORY) session3
							.get(TB_CENSUS_MEDICAL_CATEGORY.class, Integer.parseInt(cope_ch));

					if (cope_value.equals("1")) {
						Mad_cat.setShape_sub_value(cope_sub_value);
					} else {
						Mad_cat.setShape_sub_value(null);
					}
					if (cope_sub_value.equals("k")) {
						Mad_cat.setOther(cope_other);
					} else {
						Mad_cat.setOther(null);
					}
					Mad_cat.setFrom_date_1bx(null);
					Mad_cat.setTo_date_1bx(null);
					Mad_cat.setDiagnosis_1bx(null);
					Mad_cat.setShape_value(cope_value);
					Mad_cat.setAuthority(authority);
					Mad_cat.setDate_of_authority(format.parse(date_of_authority));
					Mad_cat.setClasification(mad_classification);
					Mad_cat.setModify_by(username);
					Mad_cat.setModify_on(date);
					Mad_cat.setStatus(1);
					session3.update(Mad_cat);
					session3.flush();
					session3.clear();

				}

				diffrence = eCope_count - eCopeOld_count;

				if (diffrence != 0) {
					TB_CENSUS_MEDICAL_CATEGORY Mad_cat = new TB_CENSUS_MEDICAL_CATEGORY();

					for (int i = eCopeOld_count + 1; i <= eCope_count; i++) {
						String cope_value = request.getParameter("c_evalue" + i);
						String cope_sub_value = request.getParameter("c_esubvalue" + i);
						String cope_other = request.getParameter("c_esubvalueother" + i);

						if (cope_value.equals("1") && cope_sub_value.equals("0")) {
							tx3.rollback();
							msg = "Please Select The Cope Sub Value " + i + " Row";
							return msg;
						}

						if (cope_sub_value.equals("k") && cope_other.equals("")) {
							tx3.rollback();
							msg = "Please Enter Other " + i + " Row";
							return msg;
						}

						if (cope_value.equals("1")) {
							Mad_cat.setShape_sub_value(cope_sub_value);
						} else {
							Mad_cat.setShape_sub_value("0");
						}

						if (cope_sub_value.equals("k")) {
							Mad_cat.setOther(cope_other);
						} else {
							Mad_cat.setOther("");
						}
						Mad_cat.setShape("C_E");
						Mad_cat.setShape_value(cope_value);
						Mad_cat.setAuthority(authority);
						Mad_cat.setDate_of_authority(format.parse(date_of_authority));
						Mad_cat.setClasification(mad_classification);
						Mad_cat.setCen_id(Integer.parseInt(p_id));
						Mad_cat.setComm_id(comm_id);
						Mad_cat.setCreated_by(username);
						Mad_cat.setCreated_on(date);
						Mad_cat.setStatus(1);
						Mad_cat.setModify_by(username);
						Mad_cat.setModify_on(date);
						session3.save(Mad_cat);

						session3.flush();
						session3.clear();
					}
				}
			}

//			String approoval_status = request.getParameter("app_status");
//			if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
//				
//			}
//			else
//			{
//				mcommon.update_offr_status(Integer.parseInt(p_id),username);
//			}

			String sShape = "S ";

			String hShape = "H ";

			String aShape = "A ";

			String pShape = "P ";

			String eShape = "E ";

			String cCope = "C ";

			String oCope = "O ";

			String pCope = "P ";

			String eCope = "E ";

			int lmc = 0;
			String hqlUpdate = " from TB_CENSUS_MEDICAL_CATEGORY where  comm_id=:comm_id and status='1'";
			Query query = session3.createQuery(hqlUpdate).setBigInteger("comm_id", comm_id);
			@SuppressWarnings("unchecked")
			List<TB_CENSUS_MEDICAL_CATEGORY> MedDetails = (List<TB_CENSUS_MEDICAL_CATEGORY>) query.list();
			if (MedDetails.size() > 0) {
				Date med_auth_date = MedDetails.get(0).getDate_of_authority();

				for (int i = 0; i < MedDetails.size(); i++) {

					int newLmc = MedDetails.get(i).getShape_status();

					if (newLmc > lmc) {

						lmc = newLmc;

					}

					String shape = MedDetails.get(i).getShape();

					if (shape.equals("S")) {

						if (!sShape.equals("S "))

							sShape += ",";

						sShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("H")) {

						if (!hShape.equals("H "))

							hShape += ",";

						hShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("A")) {

						if (!aShape.equals("A "))

							aShape += ",";

						aShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("P")) {

						if (!pShape.equals("P "))

							pShape += ",";

						pShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("E")) {

						if (!eShape.equals("E "))

							eShape += ",";

						eShape += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_C")) {

						if (!cCope.equals("C "))

							cCope += ",";

						cCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_O")) {

						if (!oCope.equals("O "))

							oCope += ",";

						oCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_P")) {

						if (!pCope.equals("P "))

							pCope += ",";

						pCope += MedDetails.get(i).getShape_value();

					} else if (shape.equals("C_E")) {

						if (!eCope.equals("E "))

							eCope += ",";

						eCope += MedDetails.get(i).getShape_value();

					}

				}

				String Fshape = sShape + ";" + hShape + ";" + aShape + ";" + pShape + ";" + eShape;

				String Fcope = cCope + ";" + oCope + ";" + pCope + ";" + eCope;

				TB_MEDICAL_CATEGORY_HISTORY Mobj = new TB_MEDICAL_CATEGORY_HISTORY();

				Mobj.setComm_id(comm_id);

				Mobj.setShape(Fshape);

				Mobj.setCope(Fcope);

				Mobj.setStatus(1);

				Mobj.setDate_of_authority(med_auth_date);

				if (lmc == 1) {

					Mobj.setMed_classification_lmc("FIT");

				} else if (lmc == 2) {

					Mobj.setMed_classification_lmc("PERMANENT");

				} else if (lmc == 3) {

					Mobj.setMed_classification_lmc("TEMPORARY");

				}
				session3.save(Mobj);

			}
			// END LOGIC OF INSERT

			if (census_id > 0) {
				int count = censusAprovedDAO.checkdatapendingintable(comm_id, "medical_category");
			      if (count == 0) {

					String 	hql = "update TB_CENSUS_DETAIL_Parent set update_ofr_status=:update_ofr_status"
							+ " where comm_id=:comm_id ";
					Query query1 = session3.createQuery(hql).setBigInteger("comm_id", comm_id)
							.setInteger("update_ofr_status", 1);
					msg = query1.executeUpdate() > 0 ? "Data Approved Successfully" : "Data Not Approved Successfully";
				}

			}

			tx3.commit();

			msg = "1";

		} catch (RuntimeException e) {
			try {
				tx3.rollback();

				msg = "Data not Updated";
			} catch (RuntimeException rbe) {
				msg = "Data not Updated";

			}

		} finally {
			if (session3 != null) {
				session3.close();
			}
		}

		return msg;
	}

}
