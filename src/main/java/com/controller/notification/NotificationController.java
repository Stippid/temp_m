package com.controller.notification;




import java.beans.Statement;

import java.io.IOException;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.Emoluments.emolumentDAO;
import com.dao.Notification.notificatioDAO;
import com.models.TB_MISO_CLICKCOUNT;
import com.models.notification.TB_NOTIFICATION;
import com.models.psg.Transaction.TB_CENSUS_CADET;
import com.models.psg.Transaction.TB_CENSUS_FAMILY_SIBLINGS;
import com.persistance.util.HibernateUtil;

import javassist.expr.NewArray;


import com.controller.DateWithTimestamp.DateWithTimeStampController;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class NotificationController {

	
	@Autowired
	private notificatioDAO notifi;
	
	DateWithTimeStampController timestamp = new DateWithTimeStampController();

	private DataSource dataSource;
	
	@SuppressWarnings("unchecked")
	public Boolean sendNotification(String title, String description,String user_id,String created_by) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		
		
		try {
			Query qry = session.createSQLQuery("insert into tb_notification "
					+ "(title ,description ,receiver_id , created_by,created_date)" 
					+ "values"
					+ "(:title,:description,:user_id,:created_by,:created_date)");
					qry.setParameter("title", title);
					qry.setParameter("description", description);
					qry.setParameter("user_id", user_id);
					qry.setParameter("created_by", created_by);
					qry.setParameter("created_date", new Date());
					qry.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	

	@RequestMapping(value = "/getnotificationList", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getnotificationList(HttpServletRequest request, HttpSession sessionA
			) throws HibernateException, ParseException {
		
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		
		return notifi.getnotificationList(userid);

	}	
	
	@RequestMapping(value = "/getnotificationDetails", method = RequestMethod.POST)
	public @ResponseBody List<TB_NOTIFICATION> getnotificationDetails(HttpServletRequest request, HttpSession sessionA) {
		
		String userid = sessionA.getAttribute("userId").toString();
		int id=0;
		id = Integer.parseInt(request.getParameter("id"));
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = " from TB_NOTIFICATION where id=:id  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id);

		List<TB_NOTIFICATION> list = (List<TB_NOTIFICATION>) query.list();
		
		//////////////update view///////////
		
		String seenFlag=request.getParameter("seen");
		String seen_by = list.get(0).getSeen_by();
		if (!seenFlag.equals("")) {
			if (seen_by==null || seen_by.equals("")) {
				seen_by=userid;
			}
			else {
				seen_by=seen_by+","+userid;
			}
			
			int updateid = sessionHQL.createQuery("update TB_NOTIFICATION set seen_by=:seen_by where id=:id").setString("seen_by",seen_by).setInteger("id", id).executeUpdate();
		}
		
		

		tx.commit();

		sessionHQL.close();

		return list;
		

	}	
	
	@RequestMapping(value = "/removeNotification", method = RequestMethod.POST)
	public @ResponseBody String removeNotification(HttpServletRequest request, HttpSession sessionA) {
		
		String userid = sessionA.getAttribute("userId").toString();
		int id=0;
		id = Integer.parseInt(request.getParameter("id"));
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		
		String hqlUpdate = " from TB_NOTIFICATION where id=:id  ";
		Query query = sessionHQL.createQuery(hqlUpdate).setInteger("id", id);

		List<TB_NOTIFICATION> list = (List<TB_NOTIFICATION>) query.list();
		
		//////////////Remove notification///////////
		
		String Receiver = list.get(0).getReceiver_id();
		
		Receiver = Receiver.replace(userid+",", "");
		Receiver = Receiver.replace(","+userid, "");
		Receiver = Receiver.replace(userid, "");

			int updateid = sessionHQL.createQuery("update TB_NOTIFICATION set receiver_id=:receiver_id where id=:id").setString("receiver_id",Receiver).setInteger("id", id).executeUpdate();
		
	

		tx.commit();

		sessionHQL.close();

		return String.valueOf(updateid);
		

	}
	public Boolean sendNotification_tms(String title, String description,String user_id,String created_by,int common_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		
		
		try {
			Query qry = session.createSQLQuery("insert into tb_notification "
					+ "(title ,description ,receiver_id , created_by,created_date,common_id)" 
					+ "values"
					+ "(:title,:description,:user_id,:created_by,:created_date,:common_id)");
					qry.setParameter("title", title);
					qry.setParameter("description", description);
					qry.setParameter("user_id", user_id);
					qry.setParameter("created_by", created_by);
					qry.setParameter("created_date", new Date());
					qry.setParameter("common_id", common_id);
					
					qry.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@RequestMapping(value = "/getnotificationList_new", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getnotificationList_new(HttpServletRequest request, HttpSession sessionA
			) throws HibernateException, ParseException {
		
		int userid = Integer.parseInt(sessionA.getAttribute("userId").toString());
		
		return notifi.getnotificationList_new(userid);

	}	
	

	@RequestMapping(value = "/getClickCount", method = RequestMethod.POST)

	public @ResponseBody boolean getClickCount(int screen_id,HttpServletRequest request, HttpSession sessionA,ModelMap model) throws SQLException { 

		boolean flag;

		flag=false;

		String userid = sessionA.getAttribute("userId").toString();

		int user_id=0;

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		try {

		user_id = Integer.parseInt(userid);

		System.err.println("user_id is "+user_id);

		System.err.println("screen_id is "+screen_id);
		request.getSession().setAttribute("screen_id_analytics", screen_id);
		TB_MISO_CLICKCOUNT click = new TB_MISO_CLICKCOUNT();

		click.setScreen_id(screen_id);

		click.setUser_id(user_id);

		//Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		sessionHQL.beginTransaction();

		sessionHQL.save(click);

		sessionHQL.getTransaction().commit();

		flag=true;

		return flag;

		} catch (JDBCException e) {

			System.err.println("exception is "+e);

			e.printStackTrace();

			return false;



		}  catch (Exception eq) {

			System.err.println("exception is "+eq);

			eq.printStackTrace();

			return false;

		

		}



	}

	
	
	
}
