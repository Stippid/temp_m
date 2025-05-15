package com.dao.login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.models.UserAttempts;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Repository
public class UserAttemptsDAOImpl implements UserAttemptsDAO {
	
	private static final int MAX_ATTEMPTS = 5;
	
	private UserLoginDAO login= new UserLoginDAOImpl();
	
	//@Override
	public void updateFailAttempts(String userName) {
		int userId = getUserId(userName);
		UserAttempts attempts = getUserAttempts(userId);
		if (attempts == null) {
				// if no record, insert a new attempt
				UserAttempts newAttempt = new UserAttempts(userId,0,new Date());
				Session session= HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				session.save(newAttempt);
				tx.commit();
				session.close();
		} else {
			if (attempts.getAttempts() + 1 >= MAX_ATTEMPTS) {
				// locked user
				UserLogin user = login.findUser(userName);
				user.setEnabled(0);
				login.update(user);
				// throw exception
				throw new LockedException("User Account is locked!");
			}
			// update attempts count, +1
			attempts.setAttempts(attempts.getAttempts()+1);
			attempts.setLastModified(new Date());
			
			Session session= HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.update(attempts);
			tx.commit();
			session.close();
		}
	}

	//@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false)
	public UserAttempts getUserAttempts(int userId) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String curr_date = desiredFormat.format(new Date());
		String before30Minutes = desiredFormat.format(new Date(System.currentTimeMillis()-30*60*1000));
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM UserAttempts U where U.userId=:userId and TO_CHAR(lastModified, 'dd-mm-yyyy HH24:MI:SS') between :before30Minutes and :curr_date ");
		q.setParameter("userId",userId);
		q.setParameter("before30Minutes",before30Minutes);
		q.setParameter("curr_date",curr_date);
		List<UserAttempts> list = (List<UserAttempts>) q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			UserAttempts myAtttempt= (UserAttempts) list.get(0);
			return myAtttempt;
		}
		else
		{
			return null;
		}
	}

	public UserAttempts getUserAttempts(String userName) {
		int userId = getUserId(userName);
		return getUserAttempts(userId);
	}
	
	//@Override
	@Transactional(readOnly=false)
	public void resetFailAttempts(String userName) {
		int userId = getUserId(userName);
		UserAttempts attempts = getUserAttempts(userId);
		if (attempts != null) {
			attempts.setAttempts(0);
			attempts.setLastModified(new Date());
			Session session= HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.update(attempts);
			tx.commit();
			session.close();
		}
	}

	public int getUserId(String userName){
		UserLogin loginDetails = login.findUser(userName);
		if(loginDetails !=null)
		{
			return loginDetails.getUserId();
		}
		else
		{
			return 0;
		}
	}
}