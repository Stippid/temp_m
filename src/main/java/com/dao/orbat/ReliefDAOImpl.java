package com.dao.orbat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Miso_Orbat_Relief_Prgm;
import com.models.Miso_Orbat_Shdul_Detl;
import com.models.Miso_Orbat_Unt_Dtl;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class ReliefDAOImpl implements ReliefDAO{
	public List<Miso_Orbat_Relief_Prgm> getReliefReportList(String period_from,String period_to,String status,String sus_no) {
		String qry= "where sd_status =:status ";
		if(!period_from.equals("") & period_to.equals("")) {
			qry += " and period_from=:period_from";
		}
		if(period_from.equals("") & !period_to.equals("")) {
			qry += " and period_to=:period_to";
		}
		if(!period_from.equals("") & !period_to.equals("")) {
			qry += " and period_from=:period_from and period_to =:period_to";
		}
		if(!sus_no.equals("")) {
			qry += " and sus_no=:sus_no ";
		}
	 	Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("select auth_let_no,to_char(to_date(date1,'YYYY-MM-DD'),'DD-Mon-YYYY') as date1,period_from,period_to,sd_status,count(*),ser_no from Miso_Orbat_Relief_Prgm " + qry +" group by auth_let_no,date1,period_from,period_to,sd_status,ser_no");
    	q.setParameter("status", status);
    	if(!period_from.equals("") & period_to.equals("")) {
			q.setParameter("period_from", period_from);
			
		}
    	if(period_from.equals("") & !period_to.equals("")) {
    		q.setParameter("period_to", period_to);
    		
		}
		if(!period_from.equals("") & !period_to.equals("")) {
			q.setParameter("period_from", period_from);
			q.setParameter("period_to", period_to);
			
		}
		if(!sus_no.equals("")) {
			q.setParameter("sus_no", sus_no);
			
		}
    	@SuppressWarnings("unchecked")
    	List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
    	
    	
    	tx.commit();
    	return list;
    }
	public Miso_Orbat_Relief_Prgm getLatLon(int id){
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("from Miso_Orbat_Relief_Prgm where id=:id");
    	q.setParameter("id", id);
    	Miso_Orbat_Relief_Prgm list = (Miso_Orbat_Relief_Prgm) q.list().get(0);
    	tx.commit();
    	return list;
	}	
	 
	 public String deleteReliefPro(int entryId){
			Session session = HibernateUtilNA.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hql = "delete from Miso_Orbat_Relief_Prgm where id = :id";
		    Query query = session.createQuery(hql);
		    query.setInteger("id",entryId);
		    int rowCount = query.executeUpdate();
			tx.commit();
			session.close();
			if(rowCount > 0) {
				return "Data Deleted Successfully";
			}else {
				return "Data not Deleted";
			}
			
		}
	
		@Override
		public List<String> getSearchReliefReportList(String auth_letter,String ser_no,String status) {
			Session session= HibernateUtilNA.getSessionFactory().openSession();
	    	session.setFlushMode(FlushMode.ALWAYS);
	    	Transaction tx = session.beginTransaction();	
	    	Query q = session.createQuery("select id,"
	    			+ " r.auth_let_no,"
	    			+ " r.sus_no,"
	    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name,"
	    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade')) and status_sus_no = 'Active' and  form_code_control= r.imdt_fmn) as imdt_fmn,"
	    			+ " (select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code=r.arm_name) as arm_name,"
	    			+ " r.mode_of_tpt,"
	    			+ " to_char(to_date(r.nmb_date,'YYYY-MM-DD'),'DD-Mon-YYYY') as nmb_date,"
	    			+ " r.indn_de_indn_pd,"
	    			+ " (select code_value from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location') as loc,"
	    			+ " (select code_value from Tb_Miso_Orbat_Code where code_type='Location' and code = (select nrs_code from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location')) as nrs,"
	    			+ " r.typ_of_stn,"
	    			+ " r.typ_of_terrain,"
	    			+ " to_char(to_date(r.mov_of_adv_party_dt,'YYYY-MM-DD'),'DD-Mon-YYYY') as mov_of_adv_party_dt,"
	    			+ " r.rplc_by_unit_sus_no,"
	    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active') as rplc_by_unit_name, "
	    			+ " r.relief_yes_no, "
	    			+ " r.type_of_cl, "   
	    			+ " r.sd_status, r.ser_no "
	    			+ " from Miso_Orbat_Relief_Prgm r  where r.auth_let_no=:auth_let_no and r.ser_no=:serno and r.sd_status=:status order by id asc");
	    	
	    	
	    	q.setParameter("auth_let_no",auth_letter);  
	    	q.setParameter("serno",ser_no);
	    	q.setParameter("status",status);
	    	@SuppressWarnings("unchecked")
	    	List<String> list = (List<String>) q.list();
	    	tx.commit();
	    	return list;
		}
		
	 
	 
	public Miso_Orbat_Relief_Prgm UpdateDataEntry(Miso_Orbat_Relief_Prgm rp) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		session.update(rp);
		session.getTransaction().commit();
		session.close();
		return rp;	
	}

	
	
	@Override
	public List<Miso_Orbat_Relief_Prgm> getSearchReliefReportList(String auth_letter,String status) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("select id,"
    			+ " r.auth_let_no,"
    			+ " r.sus_no,"
    			//+ " unit_name,"
    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name,"
    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade')) and status_sus_no = 'Active' and  form_code_control= r.imdt_fmn) as imdt_fmn,"
    			+ " (select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code=r.arm_name) as arm_name,"
    			+ " r.mode_of_tpt,"
    			+ " r.nmb_date,"
    			+ " r.indn_de_indn_pd,"
    			+ " (select code_value from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location') as loc,"
    			+ " (select code_value from Tb_Miso_Orbat_Code where code_type='Location' and code = (select nrs_code from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location')) as nrs,"
    			+ " r.typ_of_stn,"
    			+ " r.typ_of_terrain,"
    			+ " r.mov_of_adv_party_dt,"
    			+ " r.rplc_by_unit_sus_no,"
    			+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active') as rplc_by_unit_name, "
    			+ " r.sd_status, r.ser_no "
    			+ " from Miso_Orbat_Relief_Prgm r  where r.auth_let_no=:auth_let_no and r.sd_status=:status");
    	q.setParameter("auth_let_no",auth_letter);
    	q.setParameter("status",status); 
    	@SuppressWarnings("unchecked")
    	List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
    	tx.commit();
    	return list;
	}
	
	@Override
	public List<Miso_Orbat_Relief_Prgm> getsearchMainBodyReportList(String sus_no,String status,String unit_name) {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();
    	Query q = null;
    	if(!sus_no.equals("") ) {
    		q = session.createQuery("select r.id,"
    				+ "	r.auth_let_no,"
    				+ "	r.sus_no,"
    				+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no = 'Active') as unit_name,"
    				+ "	(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade')) and status_sus_no = 'Active' and  form_code_control=r.imdt_fmn) as imdt_fmn,"
    				//+ "	(select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code=r.arm_name) as arm_name,"
    				+ "	r.mode_of_tpt,"
    				+ "	r.nmb_date,"
    				+ "	r.indn_de_indn_pd,"
    				+ "	(select code_value from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location' ) as loc,"
    				+ "	(select code_value from Tb_Miso_Orbat_Code where code_type='Location' and code = (select nrs_code from Tb_Miso_Orbat_Code where code=r.loc and code_type='Location')) as nrs , "
    				//+ "	r.typ_of_stn,"
    				//+ "	r.typ_of_terrain,"
    				+ "	r.mov_of_adv_party_dt,"
    				//+ "	r.rplc_by_unit_sus_no,"
    				+ "	 (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active') as rplc_by_unit_name, "
    				+ "	r.dep_date, "
    				+ "	r.arr_date, "
    				+ "	r.unit_status "
    				+ "	from Miso_Orbat_Relief_Prgm  r "
    				+ "	where "
    				+ " r.sus_no like :sus_no and r.unit_status=:status");
    		q.setParameter("sus_no",sus_no+"%");
        	q.setParameter("status",status);
        	//q.setParameter("unit_name", "%"+unit_name.toUpperCase()+"%");
    	}/*else {
    		q = session.createQuery("select id,auth_let_no,sus_no,unit_name,(select unit_name from Miso_Orbat_Unt_Dtl where sus_no in (select sus_no from Tbl_CodesForm where level_in_hierarchy in ('Command', 'Corps', 'Division', 'Brigade')) and status_sus_no = 'Active' and  form_code_control=imdt_fmn) as imdt_fmn,(select arm_desc from Tb_Miso_Orabt_Arm_Code where arm_code=arm_name) as arm_name,mode_of_tpt,nmb_date,indn_de_indn_pd,(select code_value from Tb_Miso_Orbat_Code where code=loc and code_type='Location' and nrs_code=nrs_code) as loc,nrs,typ_of_stn,typ_of_terrain,mov_of_adv_party_dt,rplc_by_unit_sus_no, (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.rplc_by_unit_sus_no and status_sus_no = 'Active') as rplc_by_unit_name,unit_status from Miso_Orbat_Relief_Prgm  where unit_status=:status");
    		q.setParameter("status",status);
    	}*/ 
    	@SuppressWarnings("unchecked")
    	List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
    	tx.commit();
    	return list;
	} 
	
	@Override 
	public String approvedSdReliefDetails(String auth_letter,String status,String ser_no,String username,String date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		int id = session.createQuery("UPDATE Miso_Orbat_Relief_Prgm set sd_status='1',approved_by_sd=:approved_by_sd, approved_on_sd=:approved_on_sd  where ser_no=:ser_no and auth_let_no=:auth_let_no and sd_status=:status").setParameter("ser_no",ser_no).setParameter("auth_let_no",auth_letter).setParameter("approved_by_sd",username).setParameter("approved_on_sd",date).setParameter("status",status).executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		if(id > 0) {
			return "Data Approved Sucessfully";
		}else {
			return "Data not Approved";
		}
	}
	
	@Override
	public String rejectedSdReliefDetails(String auth_letter, String ser_no, String status, String username,String date) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction();
		int id = session.createQuery("UPDATE Miso_Orbat_Relief_Prgm set sd_status='2',rejected_by_sd=:rejected_by_sd, rejected_on_sd=:rejected_on_sd  where ser_no=:ser_no and auth_let_no=:auth_let_no and sd_status=:status").setParameter("ser_no",ser_no).setParameter("auth_let_no",auth_letter).setParameter("rejected_by_sd",username).setParameter("rejected_on_sd",date).setParameter("status",status).executeUpdate();
		session.getTransaction().commit();
		session.close();
		if(id > 0) {
			return "Data Rejected Sucessfully";
		}else {
			return "Data not Rejected";
		}
	}
	
	@Override
	public String approvedUnitReliefDetails(int id,String status,String username,String date,String sus_no) {
		Session sessionHQL = null;
    	Transaction tx = null;
    	try{
    		sessionHQL = HibernateUtil.getSessionFactory().openSession();
    		tx = sessionHQL.beginTransaction();
		
			Query q = sessionHQL.createQuery("select count(sus_no) from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no=:status_sus_no ");
			q.setParameter("sus_no", sus_no);
			q.setParameter("status_sus_no","Pending");
			Long count = (Long) q.list().get(0);
			sessionHQL.flush();
			sessionHQL.clear();
			
			if(count == 0) {
				Query q1_unit = sessionHQL.createQuery("from Miso_Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no=:status_sus_no ");
				q1_unit.setParameter("sus_no", sus_no);
				q1_unit.setParameter("status_sus_no","Active");
				@SuppressWarnings("unchecked")
				List<Miso_Orbat_Unt_Dtl> unt_dtl = (List<Miso_Orbat_Unt_Dtl>) q1_unit.list();
				sessionHQL.flush();
				sessionHQL.clear();
				
				Miso_Orbat_Relief_Prgm relief = (Miso_Orbat_Relief_Prgm) sessionHQL.get(Miso_Orbat_Relief_Prgm.class, id);
				sessionHQL.flush();
				sessionHQL.clear();
				
				Miso_Orbat_Unt_Dtl u = new Miso_Orbat_Unt_Dtl();
				u.setSus_no(relief.getSus_no());
				u.setForm_code_admin(relief.getImdt_fmn());
				u.setForm_code_control(relief.getImdt_fmn());
				u.setForm_code_operation(relief.getImdt_fmn());
				u.setLetter_no(relief.getUnit_auth_letter_no()+" dt "+ relief.getUnit_auth_letter_date());
				u.setUnit_name(unt_dtl.get(0).getUnit_name());
				u.setStatus_sus_no("Pending");
				u.setAddress(unt_dtl.get(0).getAddress());
				u.setCode(relief.getLoc());
				u.setCode_type(unt_dtl.get(0).getCode_type());
				u.setModification(relief.getTyp_of_stn());
				u.setType_force(unt_dtl.get(0).getType_force());
				u.setCt_part_i_ii(unt_dtl.get(0).getCt_part_i_ii());
				u.setEntity(unt_dtl.get(0).getEntity());
				u.setSus_no_for_comb_disint(relief.getSus_no());
				// Sus Version No
				
				Query q1_ver = sessionHQL.createQuery("select count(sus_no) from Miso_Orbat_Unt_Dtl where sus_no=:target_sus_no");
				q1_ver.setParameter("target_sus_no", sus_no);
				Long list1 = (Long) q1_ver.list().get(0);
				int ver;
				sessionHQL.flush();
				sessionHQL.clear();
				
				if(list1 != null) {
					 ver = (int) (list1 + 1);
				}else {
					ver = (int) 1;
				}
				u.setSus_version(ver);
				u.setType_of_location(relief.getTyp_of_terrain());
				u.setArm_code(unt_dtl.get(0).getArm_code());
				u.setUnit_army_hq(unt_dtl.get(0).getUnit_army_hq());
				u.setCreation_by(username);
				u.setCreation_on(new Date());
				u.setIs_unit_pending("Y");
				u.setNrs_code(relief.getNrs_code());
				try {
					u.setComm_depart_date(new SimpleDateFormat("yyyy-MM-dd").parse(relief.getDep_date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					u.setCompltn_arrv_date(new SimpleDateFormat("yyyy-MM-dd").parse(relief.getArr_date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				int uid = (Integer) sessionHQL.save(u);
				sessionHQL.flush();
				sessionHQL.clear();
				
				int shdule = 0;
				
				if(uid > 0) {
					Miso_Orbat_Shdul_Detl shdul = new Miso_Orbat_Shdul_Detl();
					shdul.setType_of_letter("9");
					shdul.setLetter_no(relief.getUnit_auth_letter_no()+" dt "+ relief.getUnit_auth_letter_date());
					shdul.setStatus("0");
					shdul.setCreated_by(username);
					try {
						shdul.setCreated_on(new SimpleDateFormat("yyyy-MM-dd").parse(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					shdul.setGoi_letter_no(relief.getAuth_let_no());
					try {
						shdul.setDate_goi_letter(new SimpleDateFormat("yyyy-MM-dd").parse(relief.getDate1()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					shdul.setUpload_goi_letter(relief.getUpload_document());
					try {
						shdul.setSanction_date(new SimpleDateFormat("yyyy-MM-dd").parse(relief.getUnit_auth_letter_date()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					shdul.setUpload_auth_letter(relief.getArr_dep_report());
					shdul.setLetter_id(uid);
					
					shdule = (int) sessionHQL.save(shdul);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				if(shdule > 0) {
					sessionHQL.createQuery("UPDATE Miso_Orbat_Relief_Prgm set unit_status='1' ,approved_by_unit=:approved_by_unit, approved_on_unit=:approved_on_unit  where id=:id and unit_status=:status").setParameter("id",id).setParameter("approved_by_unit",username).setParameter("approved_on_unit",date).setParameter("status",status).executeUpdate();
					sessionHQL.flush();
					sessionHQL.clear();
				}
				tx.commit();
				return "Data Approved Sucessfully";
			}else {
				return "Data not Approved Please Contact MISO";
			}
		}catch(RuntimeException e){
    		tx.rollback();
    		return "Data not Approved Please Contact MISO";
    	}finally{
    		if(sessionHQL!=null){
    			sessionHQL.close();
    		}
    	}	
	}
	
	@Override
	public ArrayList<List<String>> getAmendmentReportList() {
		Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	//Query q = session.createQuery("from Miso_Orbat_Relief_Prgm where sd_status='1' and unit_status is null order by sus_no");
    	
    	Query q = session.createQuery("select id,"
				+ "	r.sus_no,"
				+ " (select unit_name from Miso_Orbat_Unt_Dtl where sus_no = r.sus_no and status_sus_no in('Active')) as unit_name,"
				+ "	r.auth_let_no,"
				+ "	r.date1,"
				+ "	r.period_from,"
				+ "	r.period_to "
				+ "	from Miso_Orbat_Relief_Prgm  r "
				+ "	where  r.sd_status='1' and r.unit_status is null "
				+ " and r.sus_no in (select sus_no from Miso_Orbat_Unt_Dtl where status_sus_no='Active')" 
				+ "  order by sus_no");

		
    	@SuppressWarnings("unchecked")
    	List<Object[]>  list = (List<Object[]> ) q.list();
    	//List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
    	tx.commit();
    	ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
    	for(Object[] listObject: list){
    		List<String> List = new ArrayList<String>();
    		
    		int id = (Integer)listObject[0];
    		String sus_no = (String)listObject[1];
    		String unit_name = (String)listObject[2];
    		String auth_let_no = (String)listObject[3];
    		String date1 = (String)listObject[4];
    		String period_from = (String)listObject[5];
    		String period_to = (String)listObject[6];
    		
    		List.add(String.valueOf(id));
    		List.add(sus_no);
    		List.add(unit_name);
    		List.add(auth_let_no);
    		List.add(date1);
    		List.add(period_from);
    		List.add(period_to);
    		FinalList.add(List);
	   	}
    	return FinalList;
	}

	@Override
	public Miso_Orbat_Relief_Prgm editamend(int id) {
		Session session = HibernateUtilNA.getSessionFactory().openSession();
		session.beginTransaction(); 
		Query q = session.createQuery("from Miso_Orbat_Relief_Prgm where id=:id");
		q.setParameter("id", id);
		Miso_Orbat_Relief_Prgm list = (Miso_Orbat_Relief_Prgm) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public Miso_Orbat_Relief_Prgm UpdateAmendment(Miso_Orbat_Relief_Prgm rp) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 
		session.update(rp);
		session.getTransaction().commit();
		session.close();
		return rp;
	}
	
	public List<Miso_Orbat_Relief_Prgm> getRelief_ProgByid(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction tx = session.beginTransaction();
       Query q = session.createQuery("from Miso_Orbat_Relief_Prgm where id=:id");
       q.setParameter("id", id);
       @SuppressWarnings("unchecked")
       List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
       tx.commit();
       session.close();
       return list;        
    }
		
	public List<Miso_Orbat_Relief_Prgm> getReliefNReportList(String period_from,String period_to,String status,String arm){
		String qry= "where sd_status =:status ";
		if(!period_from.equals("") & period_to.equals("")) {
			qry += " and period_from=:period_from";
		}
		if(period_from.equals("") & !period_to.equals("")) {
			qry += " and period_to=:period_to";
		}
		if(!period_from.equals("") & !period_to.equals("")) {
			qry += " and period_from=:period_from and period_to =:period_to";
		}
		if(!arm.equals("")) {
			qry += " and arm_name like '"+arm+"%'";
		}
	 	Session session= HibernateUtilNA.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.ALWAYS);
    	Transaction tx = session.beginTransaction();	
    	Query q = session.createQuery("select auth_let_no,date1,period_from,period_to,sd_status,count(*) from Miso_Orbat_Relief_Prgm " 
    			+ qry +" and coalesce(unit_status,'0')<>'1' group by auth_let_no,date1,period_from,period_to,sd_status");
    	q.setParameter("status", status);
    	if(!period_from.equals("") & period_to.equals("")) {
			q.setParameter("period_from", period_from);
		}
    	if(period_from.equals("") & !period_to.equals("")) {
    		q.setParameter("period_to", period_to);
		}
		if(!period_from.equals("") & !period_to.equals("")) {
			q.setParameter("period_from", period_from);
			q.setParameter("period_to", period_to);
		}
		
    	@SuppressWarnings("unchecked")
    	List<Miso_Orbat_Relief_Prgm> list = (List<Miso_Orbat_Relief_Prgm>) q.list();
    	
    	tx.commit();
    	return list;
    }
	@Override
	public String approvedSdReliefDetails(String auth_letter, String status, String username, String date) {
		// TODO Auto-generated method stub
		return null;
	}	
}