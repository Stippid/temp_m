package com.controller.psg.Notification;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.psg.Transaction.NotificationDao;
import com.itextpdf.text.log.SysoCounter;
import com.models.psg.Transaction.TB_POSTING_IN_OUT;
import com.models.psg.update_census_data.TB_CHANGE_NAME;

import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Notification_Controller {
	@Autowired
	NotificationDao noti_Dao;
	
	@RequestMapping(value = "/TimeStampApp",method = RequestMethod.POST)
	public @ResponseBody List<String> TimeStampApp(HttpSession session) {
	
		String susNo =	 session.getAttribute("roleSusNo").toString();
		
		List<String> list = getTimeStampUser(susNo);
		
		return list;
	}
	
	public List<String> getTimeStampUser(String susno) {
		List<String> list = new ArrayList<String>();
	
			String timestamp ="";
	
			String alert ="";
			
	
			List<TB_POSTING_IN_OUT> approval = null;
			approval = getApprovalList(susno);
			
			for(int i=0;i<approval.size();i++){
				
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
					timestamp += " <span style='position: relative;border-radius: 2px;'> "+approval.get(i).getFrom_sus_no() +" : <b style='min-width: 20px'>"+formatter.format(approval.get(i).getCreated_date())+"</b></span><br>";	
			
					alert = "New Post In";
			
			}
			alert=String.valueOf(approval.size());
			if(!timestamp.equals("")) {
				list.add(timestamp);	
			}
			if(!alert.equals("")) {
				list.add(alert);	
			}
			
			
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TB_POSTING_IN_OUT> getApprovalList(String susNo){
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql ="FROM TB_POSTING_IN_OUT where to_sus_no=:to_sus_no and status='0'";
			Query q = session.createQuery(hql);
			q.setString("to_sus_no", susNo);
			
			List<TB_POSTING_IN_OUT> list = (List<TB_POSTING_IN_OUT>) q.list();
			tx.commit();
			
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@RequestMapping(value = "/RejectNotification",method = RequestMethod.POST)
	public @ResponseBody int RejectNotification(HttpSession session) {
	
		String susNo =	 session.getAttribute("roleSusNo").toString();
		
		int count = noti_Dao.getRejectNotification(susNo);
		return count;
	}
	@RequestMapping(value = "/countNotification",method = RequestMethod.POST)
	public @ResponseBody int countNotification(HttpSession session,ModelMap Mmap) {
	
		String susNo =	 session.getAttribute("roleSusNo").toString();
		
		int count = noti_Dao.getcountNotification(susNo);
		Mmap.put("count", count);
	
		return count;
	}
	
	@RequestMapping(value = "/personalNotification",method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> personalNotification(HttpSession session,ModelMap Mmap) {
	
		String susNo =	 session.getAttribute("roleSusNo").toString();
		
		//String count = noti_Dao.getpersonalNotification(susNo);
		ArrayList<ArrayList<String>> list = noti_Dao.getpersonalNotification(susNo);
		
		Mmap.put("list", list);
		Mmap.put("size", list.size());
	
		
		return list;
	}

	
	
	
	@RequestMapping(value = "/admin/Notificationaction", method = RequestMethod.POST)
	public @ResponseBody String Notificationaction(ModelMap Mmap, HttpSession session, HttpServletRequest request)
			throws ParseException {

		Session sessionhql = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionhql.beginTransaction();

		String susNo = session.getAttribute("roleSusNo").toString();
		String msg = "";
		try {
			String hql = " update TB_POSTING_IN_OUT set notification_status=:notification_status "
					+ " where  to_sus_no=:to_sus_no and notification_status=0 ";

			Query query = sessionhql.createQuery(hql)

					.setInteger("notification_status", 1).setString("to_sus_no", susNo);

			msg = query.executeUpdate() > 0 ? "" : "";

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
}
