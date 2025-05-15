package com.controller.psg.Jco_Update_JcoData;

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
import com.controller.psg.Master.Psg_CommonController;
import com.controller.validation.ValidationController;
import com.models.psg.Jco_Update_JcoData.TB_ATTACHMENT_DETAILS_JCO;
import com.models.psg.Jco_Update_JcoData.TB_CHANGE_IN_DATE_OF_SENIORITY_JCO;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Attachment_Details_JCOController {

	
private Psg_CommonController com = new Psg_CommonController();
	
	private psg_jco_CommonController pjc=new psg_jco_CommonController();
	
	ValidationController valid = new ValidationController();
	
	// save and update change name
		@RequestMapping(value = "/admin/attachment_details_JcoOr_action", method = RequestMethod.POST)
		public @ResponseBody String attachment_details_JcoOr_action(ModelMap Mmap, HttpSession session, HttpServletRequest request)
				throws ParseException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Session sessionhql = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionhql.beginTransaction();
			Date date = new Date();
			Date att_authority = null;
			Date att_of_move = null;
			
			Date from = null;
			Date to = null;

			String username = session.getAttribute("username").toString();
			String authority = request.getParameter("att_authority");
			Date enroll_date = format.parse(request.getParameter("enroll_date"));
			Date tos_date = format.parse(request.getParameter("tos_date"));
			
			String att_date_authority = request.getParameter("att_date_authority");
			
			
			String att_movement_number = request.getParameter("att_movement_number");
			String att_date_of_move = request.getParameter("att_date_of_move");      
			
			String att_sus_no = request.getParameter("att_sus_no");
			
			String att_unit_name = request.getParameter("att_unit_name");
			
			
			att_unit_name = att_unit_name.replace("&#40;", "(");

			att_unit_name = att_unit_name.replace("&#41;", ")");
			
			
			String att_place = request.getParameter("att_place");
			
			String att_from = request.getParameter("att_from");
			
			String att_to = request.getParameter("att_to");
			
            String att_duration = request.getParameter("att_duration");
			
			String att_reasons = request.getParameter("att_reasons");
			
		
			
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			int att_id = Integer.parseInt(request.getParameter("att_id"));
			String msg = "";

			
			if (authority == null || authority.equals("")) {
				return "Please Enter Authority";
			}
			if (!valid.isValidAuth(authority)) {
				return valid.isValidAuthMSG + " Authority ";
			}	
			if (!valid.isvalidLength(authority,valid.authorityMax,valid.authorityMin)) {
	 			return "Authority " + valid.isValidLengthMSG;	
			}
			if (att_date_authority == null || att_date_authority.equals("null") || att_date_authority.equals("DD/MM/YYYY") || att_date_authority.equals("")) {
				return "Please Select Date of Authority";
			}
			else if (!valid.isValidDate(att_date_authority)) {
	 			return valid.isValidDateMSG + " of Authority";	
			}
			else {
				att_authority = format.parse(att_date_authority);
			}
			
			if (com.CompareDate(att_authority,enroll_date) == 0) {
				return "Authority Date should be Greater than Enrollment Date";
			}
					
			if (att_movement_number == null || att_movement_number.equals("")) {
				return "Please Enter Movement Order Number";
			}
			
			if (!valid.isvalidLength(att_movement_number,valid.authorityMax,valid.authorityMin)) {
	 			return "Movement Order Number " + valid.isValidLengthMSG;	
			}
			 if (!valid.isOnlyAlphabetNumericSpaceNot(att_movement_number)) {
					return valid.isOnlyAlphabetNumericSpaceNotMSG + "Movement Order Number";
				}
			if (att_date_of_move == null || att_date_of_move.equals("null") || att_date_of_move.equals("DD/MM/YYYY") || att_date_of_move.equals("")) {
				return "Please Enter Date of Movement Order";
			}
			else if (!valid.isValidDate(att_date_of_move)) {
	 			return valid.isValidDateMSG + " of Movement Order";	
			}
			else {
				att_of_move = format.parse(att_date_of_move);
			}
			
			if (com.CompareDate(att_of_move,tos_date) == 0) {
				return "Date of Movement Order should Be Greater Than Date of TOS";
			}
			
			if (att_sus_no == null || att_sus_no.equals("") || att_sus_no.equals("null")) {
				return "Please Enter Attached to (Unit SUS No)";
			}
			 if (!valid.SusNoLength(att_sus_no)) {
					return valid.SusNoMSG;
				}
	  	  
	  	  if (!valid.isOnlyAlphabetNumericSpaceNot(att_sus_no)) {
					return valid.isOnlyAlphabetNumericSpaceNotMSG + "Unit SUS No";
				}
			
			if (att_unit_name == null || att_unit_name.equals("") || att_unit_name.equals("null")) {
				return "Please Enter Unit Name";
			}
			if (!valid.isUnit(att_unit_name)) {
                return " Unit Name " + valid.isUnitMSG;
             }

            if (!valid.isvalidLength(att_unit_name, valid.nameMax, valid.nameMin)) {
                return " Unit Name " + valid.isValidLengthMSG;
           
             }
			if (att_place == null || att_place.equals("") || att_place.equals("null")) {
				return "Please Enter  Place";
			}
			if (!valid.isOnlyAlphabet(att_place)) {
		 		return  " Place "+ valid.isOnlyAlphabetMSG;	
			}
			if (!valid.isvalidLength(att_place,valid.nameMax,valid.nameMin)) {
		 		return "Place " + valid.isValidLengthMSG;	
			}
			if (att_from == null || att_from.equals("null") || att_from.equals("DD/MM/YYYY") || att_from.equals("")) {
				return "Please Enter From Date";
			}
			else if (!valid.isValidDate(att_from)) {
	 			return valid.isValidDateMSG + " of From";	
			}
			else {
				from = format.parse(att_from);
			}
			
			if (com.CompareDate(from,tos_date) == 0) {
				return "From Date should Be Greater Than Date of TOS";
			}
			
			if (att_to == null || att_to.equals("null") || att_to.equals("DD/MM/YYYY") || att_to.equals("")) {
				return "Please Enter To Date";
			}
			else if (!valid.isValidDate(att_to)) {
	 			return valid.isValidDateMSG + " of To";	
			}
			else {
				to = format.parse(att_to);
			}
			
			if (com.CompareDate(to,tos_date) == 0) {
				return "To Date should Be Greater Than Date of TOS";
			}
			
			if (att_duration == null || att_duration.equals("") || att_duration.equals("null")) {
				return "Please Enter Duration";
			}
			/*if (!att_duration.equals(com.calculate_duration(format.parse(att_from), format.parse(att_to)))) {
				return "Please Enter valid From And To Date1";
			}*/
			if (att_reasons == null || att_reasons.equals("") || att_reasons.equals("null")) {
				return "Please Enter Reasons";
			}
			
			try {

				if (att_id == 0) {
					TB_ATTACHMENT_DETAILS_JCO n = new TB_ATTACHMENT_DETAILS_JCO();
					n.setAtt_authority(authority);
					n.setAtt_date_authority(att_authority);
					n.setAtt_date_of_move(att_of_move);
					n.setAtt_duration(att_duration);
					n.setAtt_from(from);
					n.setAtt_movement_number(att_movement_number);
					n.setAtt_place(att_place);
					n.setAtt_reasons(att_reasons);
					n.setAtt_sus_no(att_sus_no);
					n.setAtt_to(to);
					n.setInitiated_from("u");
					n.setJco_id(jco_id);
					n.setStatus(0);
					n.setCreated_by(username);
					n.setCreated_date(date);

					int id = (int) sessionhql.save(n);
					msg = Integer.toString(id);
				} else {
			
					String hql = " update TB_ATTACHMENT_DETAILS_JCO set att_authority=:att_authority ,att_date_authority=:att_date_authority"
							+ " ,initiated_from=:initiated_from,att_movement_number=:att_movement_number,att_date_of_move=:att_date_of_move,"
							+ "att_sus_no=:att_sus_no,att_place=:att_place,att_from=:att_from,att_to=:att_to,att_reasons=:att_reasons,att_duration=:att_duration,"
							+ "status=:status,modified_by=:modified_by,modified_date=:modified_date"
							+ " where  id=:id";
					Query query = sessionhql.createQuery(hql)

							.setString("att_authority", authority)
							.setTimestamp("att_date_authority", att_authority)
							.setTimestamp("att_date_of_move", att_of_move)
							.setString("att_movement_number", att_movement_number)
							.setString("att_sus_no", att_sus_no)
							.setString("att_place", att_place)
							.setTimestamp("att_from", from)
							.setTimestamp("att_to", to)
							.setString("att_duration", att_duration)
							.setString("att_reasons", att_reasons)
							.setInteger("status", 0).setString("modified_by", username).setString("initiated_from","u")
							.setTimestamp("modified_date", new Date()).setInteger("id", att_id);

					msg = query.executeUpdate() > 0 ? "update" : "0";
					Mmap.put("msg", "Data Updated Successfully");
				}
				String approoval_status = request.getParameter("app_status");
				if (approoval_status != null && !approoval_status.equals("") && approoval_status.equals("3")) {
					
				}
				else
				{
					pjc.update_JcoOr_status(jco_id,username);
				}
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
		
		
		@RequestMapping(value = "/admin/attachment_JCOGetData", method = RequestMethod.POST)
		public @ResponseBody List<TB_ATTACHMENT_DETAILS_JCO> attachment_JCOGetData(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int jco_id = Integer.parseInt(request.getParameter("jco_id"));
			String hqlUpdate = " from TB_ATTACHMENT_DETAILS_JCO where jco_id=:jco_id and status='0' order by id desc ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_ATTACHMENT_DETAILS_JCO> list = (List<TB_ATTACHMENT_DETAILS_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		public @ResponseBody List<TB_ATTACHMENT_DETAILS_JCO> Attachment_GetData1( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_ATTACHMENT_DETAILS_JCO where  status='0' and jco_id=:jco_id ";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_ATTACHMENT_DETAILS_JCO> list = (List<TB_ATTACHMENT_DETAILS_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		/*--------------------- For REJECT ----------------------------------*/

		@RequestMapping(value = "/admin/getAttachmentJCOReject", method = RequestMethod.POST)
		public @ResponseBody String getAttachmentJCOReject(ModelMap Mmap, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			String reject_remarks = request.getParameter("reject_remarks");

			
			String username = session.getAttribute("username").toString();
			TB_ATTACHMENT_DETAILS_JCO t = new TB_ATTACHMENT_DETAILS_JCO();
			t.setJco_id(Integer.parseInt(request.getParameter("jco_id")));
			t.setId(Integer.parseInt(request.getParameter("att_id")));
			t.setReject_remarks(reject_remarks);

			String msg1 = Update_Attachment_JCOReject(t, username);
			return msg1;

		}

		public String Update_Attachment_JCOReject(TB_ATTACHMENT_DETAILS_JCO obj, String username) {

			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			
			try {

				String hql = "update TB_ATTACHMENT_DETAILS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status,reject_remarks=:reject_remarks  "
						+ " where jco_id=:jco_id and status = '0' and id=:id ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", new Date()).setInteger("status", 3)
						.setInteger("jco_id", obj.getJco_id()).setString("reject_remarks", obj.getReject_remarks())
						.setInteger("id", obj.getId());

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
		
		
		public String Update_AttachmentData(TB_ATTACHMENT_DETAILS_JCO obj, String username) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			try {
				String hql1 = "update TB_ATTACHMENT_DETAILS_JCO set status=:status where  jco_id=:jco_id and (status != '0' and status != '-1') ";

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 2)
						.setInteger("jco_id", obj.getJco_id());

				msg = query1.executeUpdate() > 0 ? "Data Approve Successfully." : "Data Not Approve Successfully.";

				String hql = "update TB_ATTACHMENT_DETAILS_JCO set modified_by=:modified_by,modified_date=:modified_date,status=:status  "
						+ " where  jco_id=:jco_id and status = '0' ";

				Query query = sessionHQL.createQuery(hql).setString("modified_by", username)
						.setTimestamp("modified_date", obj.getModified_date()).setInteger("status", 1)
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
		
		
		public @ResponseBody List<TB_ATTACHMENT_DETAILS_JCO> attachment_getDataJCO2( int jco_id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			String hqlUpdate = " from TB_ATTACHMENT_DETAILS_JCO where  jco_id=:jco_id and status = '3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", jco_id);
			@SuppressWarnings("unchecked")
			List<TB_ATTACHMENT_DETAILS_JCO> list = (List<TB_ATTACHMENT_DETAILS_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		
		@RequestMapping(value = "/admin/attachment_JCOGetData3", method = RequestMethod.POST)
		public @ResponseBody List<TB_ATTACHMENT_DETAILS_JCO> attachment_JCOGetData3(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("jco_id"));
			
			String hqlUpdate = " from TB_ATTACHMENT_DETAILS_JCO where jco_id=:jco_id and status='3'";
			Query query = sessionHQL.createQuery(hqlUpdate).setInteger("jco_id", id);
			@SuppressWarnings("unchecked")
			List<TB_ATTACHMENT_DETAILS_JCO> list = (List<TB_ATTACHMENT_DETAILS_JCO>) query.list();
			tx.commit();
			sessionHQL.close();
			return list;
		}
		
		@RequestMapping(value = "/admin/Change_Attachment_JCOdelete_action", method = RequestMethod.POST)
		public @ResponseBody String Change_Attachment_JCOdelete_action(ModelMap Mmap, HttpSession session,
				HttpServletRequest request) throws ParseException {
			String msg = "";
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				String hqlUpdate = "delete from TB_ATTACHMENT_DETAILS_JCO where id=:id";
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
		
}
