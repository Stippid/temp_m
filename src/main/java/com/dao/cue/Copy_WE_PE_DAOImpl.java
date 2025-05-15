package com.dao.cue;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.CUE_TB_MISO_WEPECONDITIONS;
import com.models.CUE_TB_MISO_WEPE_PERS_DET;
import com.models.CUE_TB_MISO_WEPE_PERS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_PERS_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_DET;
import com.models.CUE_TB_MISO_WEPE_TRANSPORT_MDFS;
import com.models.CUE_TB_MISO_WEPE_TRANS_FOOTNOTES;
import com.models.CUE_TB_MISO_WEPE_WEAPONS_MDFS;
import com.models.CUE_TB_MISO_WEPE_WEAPON_DET;
import com.models.CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES;
import com.persistance.util.HibernateUtil;

public class Copy_WE_PE_DAOImpl implements CopyWE_PE_DAO{

	public List<CUE_TB_MISO_WEPECONDITIONS> getWEPENODetailsList(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPECONDITIONS where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPECONDITIONS> list = (List<CUE_TB_MISO_WEPECONDITIONS>) q.list();		
		tx.commit();
		session.close();
		return list;
	}
	
	public List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> getWEPENODetailsListFromTransFootnote(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANS_FOOTNOTES where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES> list = (List<CUE_TB_MISO_WEPE_TRANS_FOOTNOTES>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> getWEPENODetailsListFromTransMDFS(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_MDFS where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_MDFS>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_TRANSPORT_DET> getWEPENODetailsListFromTransDet(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_TRANSPORT_DET where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_TRANSPORT_DET> list = (List<CUE_TB_MISO_WEPE_TRANSPORT_DET>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_PERS_DET> getWEPENODetailsListFromPersDet(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_DET where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_DET> list = (List<CUE_TB_MISO_WEPE_PERS_DET>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> getWEPENODetailsListFromPersFootnote(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_FOOTNOTES where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES> list = (List<CUE_TB_MISO_WEPE_PERS_FOOTNOTES>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_PERS_MDFS> getWEPENODetailsListFromPersMDFS(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_PERS_MDFS where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_PERS_MDFS> list = (List<CUE_TB_MISO_WEPE_PERS_MDFS>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_WEAPON_DET> getWEPENODetailsListFromWeapDet(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPON_DET where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPON_DET> list = (List<CUE_TB_MISO_WEPE_WEAPON_DET>) q.list();		
		tx.commit();
		session.close();
		return list;
	}

	public List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> getWEPENODetailsListFromWeapFootnote(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES> list = (List<CUE_TB_MISO_WEPE_WEAPON_FOOTNOTES>) q.list();		
		tx.commit();
		session.close();
		return list;
	}
	
	public List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> getWEPENODetailsListFromWeapMDFS(String we_pe_no) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from CUE_TB_MISO_WEPE_WEAPONS_MDFS where we_pe_no=:we_pe_no");
		q.setParameter("we_pe_no", we_pe_no);
		@SuppressWarnings("unchecked")
		List<CUE_TB_MISO_WEPE_WEAPONS_MDFS> list = (List<CUE_TB_MISO_WEPE_WEAPONS_MDFS>) q.list();		
		tx.commit();
		session.close();
		return list;
	}
		
	
}
