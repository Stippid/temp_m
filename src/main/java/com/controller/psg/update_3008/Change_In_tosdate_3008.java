package com.controller.psg.update_3008;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.Dashboard.PsgDashboardController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.psg.Master.Psg_CommonController;
import com.controller.psg.update_offr_data.Inter_Arm_Tranfer_Controller;
import com.controller.validation.ValidationController;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.psg.Transaction.CensusAprovedDAO;
import com.dao.psg.update_census_data.Search_UpdateOffDataDao;
import com.dao.psg.update_census_data.SeniorityDAO;
import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.models.psg.update_census_data.TB_INTER_ARM_TRANSFER;
import com.models.psg.update_census_data.TB_PSG_CHANGE_OF_SENIORITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Change_In_tosdate_3008 {
	@Autowired
	SeniorityDAO SD;
	@Autowired
	Search_UpdateOffDataDao UOD;
	Psg_CommonController p_comm = new Psg_CommonController();
	ValidationController valid = new ValidationController();
	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();
	PsgDashboardController das = new PsgDashboardController();
	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/admin/update_tos_date_Action_3008", method = RequestMethod.POST)
	public @ResponseBody String update_tos_date_Action_3008(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String username = session.getAttribute("username").toString();
		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();
		
	
		
		Date dt_of_tos1 = null;
		String dt_of_tos = request.getParameter("date_of_tos");
		String dt_tos_pre = request.getParameter("dt_tos_pre");
		int preid = Integer.parseInt(request.getParameter("preid"));

	
		
		BigInteger comm_id = BigInteger.ZERO;
		if (new BigInteger(request.getParameter("comm_id")) != new BigInteger("0")) {
		//if (Integer.parseInt(request.getParameter("comm_id")) != 0) {
			comm_id = new BigInteger(request.getParameter("comm_id"));
		}
		if(dt_of_tos==null || dt_of_tos.trim().equals("") || dt_of_tos.trim().equals("DD/MM/YYYY")) {		
			return "Please Enter Date of TOS";
		}

		 if (!valid.isValidDate(dt_of_tos)) {
		 		return valid.isValidDateMSG + " of TOS";	
		}
		if (dt_of_tos != "") {
			dt_of_tos1 = format.parse(dt_of_tos);
		}
		if (dt_of_tos1 != null) {				
			 Date currentDate = new Date();
			 Date selectedDate = dt_of_tos1;
		        if (selectedDate.after(currentDate)) {		        	
		        	return "Future dates are not allowed";
		    }		   
		}
				
		Long c;
		Query q0 = sessionhql.createQuery(
				"select count(id) from TB_POSTING_IN_OUT where comm_id=:comm_id and status=1")				
				.setParameter("comm_id", comm_id);
		c = (Long) q0.uniqueResult();
		if (c > 1) {
			if(dt_of_tos!=null && dt_tos_pre!=null) {
				String regex = "^0+(?!$)";
				String newSos=request.getParameter("date_of_tos");
				String preSos=dt_tos_pre;
				String newSosArr[]=newSos.split("/");
				String preSosArr[]=preSos.split("/");
				int newSosM=Integer.parseInt(newSosArr[1].replaceAll(regex, ""));
				int newSosY=Integer.parseInt(newSosArr[2]);
				int preSosM=Integer.parseInt(preSosArr[1].replaceAll(regex, ""));
				int preSosY=Integer.parseInt(preSosArr[2]);
				if(newSosY==preSosY){
					if(newSosM<=preSosM){					
						return "Invalid Date of TOS";			}
				}
				else if(newSosY<preSosY){				
					return "Invalid Date of TOS";
				}
			}
			
		}
		
		Query que = sessionhql.createQuery(
				"select count(id) from TB_POSTING_IN_OUT where comm_id=:comm_id and status=0")				
				.setParameter("comm_id", comm_id);
		Long count = (Long) que.uniqueResult();
		if (count >= 1) {
			return "The TOS date cannot be changed because this user's data is pending in the Search/Approve Posting screen.";
		}		
		
		String msg = "";
		try {
			
			int hh_id = Integer.parseInt(request.getParameter("id"));
			String rvalue = "";
			String hql = "";			
			
			hql += " update TB_POSTING_IN_OUT set  dt_of_tos=:dt_of_tos,dt_of_sos=:dt_of_sos where id=:h_id";
			Query query = sessionhql.createQuery(hql)
					.setParameter("dt_of_tos", dt_of_tos1).setParameter("dt_of_sos", dt_of_tos1).setParameter("h_id", hh_id);
			msg = query.executeUpdate() > 0 ? "update" : "0";	                    					
			
			if (c > 1) {
			String rmsg=UOD.setTenureDate_3008(preid, dt_of_tos1);
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


}
