package com.dao.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.MMS_TB_DEPOT_REGN_MSTR;
import com.models.MMS_TB_DEPOT_REGN_MSTR_DETL;
import com.persistance.util.HibernateUtil;

public class MMS_Depot_hldgDAOImpl implements MMS_Depot_hldgDAO{
	
    private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*public ArrayList<ArrayList<String>> depotMcrList(String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    String nrSql=""; 
		    nrSql = "select p.sus_no,max(f.prf_group) as prf_group,max(m.item_code) as item_code,p.census_no,max(m.nomen) as nomen,";
		    nrSql = nrSql + " sum(p.freestk) as totfreestk,";
		    nrSql = nrSql + " sum(p.rep_d) as totrepd, ";
		    nrSql = nrSql + " sum(p.rep_w) as totrepw,";
		    nrSql = nrSql + " sum(p.wwr) as totwwr,";
		    nrSql = nrSql + " sum(p.etsr) as totetsr,";
		    nrSql = nrSql + " sum(p.trss) as tottrss,";
		    nrSql = nrSql + " sum(p.othr) as totothr,";
		    nrSql = nrSql + " sum(p.blr) as totblr,";
		    nrSql = nrSql + " sum(p.ber) as totber";
		    nrSql = nrSql + " from (";  
		    nrSql = nrSql + " select a.sus_no,a.census_no,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'D4' then a.tothol else 0 end) as freestk,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'D51' then a.tothol else 0 end) as rep_d,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'D52' then a.tothol else 0 end) as rep_w,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'R3' then a.tothol else 0 end) as etsr,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'R0' then a.tothol else 0 end) as wwr,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'R4' then a.tothol else 0 end) as trss,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'R5' then a.tothol else 0 end) as othr,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'G0' then a.tothol else 0 end) as blr,";
		    nrSql = nrSql + " (case a.type_of_hldg when 'G1' then a.tothol else 0 end) as ber";
		    nrSql = nrSql + " from mms_tv_regn_mstr a ";
		    nrSql = nrSql + " where a.sus_no=?";		     
		    nrSql = nrSql + " ) p inner join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no";
		    nrSql = nrSql + " inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code group by p.sus_no,p.census_no";
		    nrSql = nrSql + " order by p.sus_no,max(f.prf_group_code),p.census_no";
		    
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		    stmt.setString(1, nParaValue);
		    ResultSet rs = stmt.executeQuery();   
			String nrStr="";
			
			if(!rs.isBeforeFirst()){	
				 ArrayList<String> list = new ArrayList<String>();
				 list.add("NIL");
				 li.add(list); 
			 }else {
				 while(rs.next()){
					 ArrayList<String> list = new ArrayList<String>();
					 list.add(rs.getString("sus_no"));
		             list.add(rs.getString("prf_group"));
		             list.add(rs.getString("census_no"));
		             list.add(rs.getString("nomen"));
		             list.add(rs.getString("totfreestk"));
		             list.add(rs.getString("totrepd"));
		             list.add(rs.getString("totrepw"));
		             list.add(rs.getString("totwwr"));
		             list.add(rs.getString("tottrss"));
		             list.add(rs.getString("totetsr"));
		             list.add(rs.getString("totothr"));
		             list.add(rs.getString("totblr"));
		             list.add(rs.getString("totber")); 
		             li.add(list);   	
		         }
			 }
			
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e) {
    	   e.printStackTrace();
       }	
       return li;
	}*/
	
	
	public ArrayList<ArrayList<String>> depotMcrList(String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
    	Connection conn = null;
		try{	  
			conn = dataSource.getConnection();
		    //String nrSql=""; 
		    //nrSql = "select p.sus_no,max(f.prf_group) as prf_group,max(m.item_code) as item_code,p.census_no,max(m.nomen) as nomen,";
		    //nrSql = nrSql + " sum(p.freestk) as totfreestk,";
		    //nrSql = nrSql + " sum(p.rep_d) as totrepd, ";
		    //nrSql = nrSql + " sum(p.rep_w) as totrepw,";
		    //nrSql = nrSql + " sum(p.wwr) as totwwr,";
		    //nrSql = nrSql + " sum(p.etsr) as totetsr,";
		    //nrSql = nrSql + " sum(p.trss) as tottrss,";
		    //nrSql = nrSql + " sum(p.othr) as totothr,";
		    //nrSql = nrSql + " sum(p.blr) as totblr,";
		    //nrSql = nrSql + " sum(p.ber) as totber";
		    //nrSql = nrSql + " from (";  
		    //nrSql = nrSql + " select a.sus_no,a.census_no,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'D4' then a.tothol else 0 end) as freestk,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'D51' then a.tothol else 0 end) as rep_d,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'D52' then a.tothol else 0 end) as rep_w,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'R3' then a.tothol else 0 end) as etsr,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'R0' then a.tothol else 0 end) as wwr,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'R4' then a.tothol else 0 end) as trss,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'R5' then a.tothol else 0 end) as othr,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'G0' then a.tothol else 0 end) as blr,";
		    //nrSql = nrSql + " (case a.type_of_hldg when 'G1' then a.tothol else 0 end) as ber";
		    //nrSql = nrSql + " from mms_tv_regn_mstr a ";
		    //nrSql = nrSql + " where a.sus_no=?";		     
		    //nrSql = nrSql + " ) p inner join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no";
		    //nrSql = nrSql + " inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code group by p.sus_no,p.census_no";
		    //nrSql = nrSql + " order by p.sus_no,max(f.prf_group_code),p.census_no";
		    
		    String nrSql = "";
		    nrSql = "select p.sus_no,max(f.prf_group) as prf_group,max(m.item_code) as item_code,p.census_no,max(m.nomen) as nomen,\r\n" + 
		    		"sum(p.freestk) as totfreestk,\r\n" + 
		    		"sum(p.rep_d) as totrepd, \r\n" + 
		    		"sum(p.rep_w) as totrepw,\r\n" + 
		    		"sum(p.wwr) as totwwr,\r\n" + 
		    		"sum(p.etsr) as totetsr,\r\n" + 
		    		"sum(p.trss) as tottrss,\r\n" + 
		    		"sum(p.othr) as totothr,\r\n" + 
		    		"sum(p.blr) as totblr,\r\n" + 
		    		"sum(p.ber) as totber\r\n" + 
		    		"from \r\n" + 
		    		"(select a.sus_no,a.census_no,\r\n" + 
		    		"(case a.type_of_hldg when 'D4' then a.tothol else 0 end) as freestk,\r\n" + 
		    		"(case a.type_of_hldg when 'D51' then a.tothol else 0 end) as rep_d,\r\n" + 
		    		"(case a.type_of_hldg when 'D52' then a.tothol else 0 end) as rep_w,\r\n" + 
		    		"(case a.type_of_hldg when 'R3' then a.tothol else 0 end) as etsr,\r\n" + 
		    		"(case a.type_of_hldg when 'R0' then a.tothol else 0 end) as wwr,\r\n" + 
		    		"(case a.type_of_hldg when 'R4' then a.tothol else 0 end) as trss,\r\n" + 
		    		"(case a.type_of_hldg when 'R5' then a.tothol else 0 end) as othr,\r\n" + 
		    		"(case a.type_of_hldg when 'G0' then a.tothol else 0 end) as blr,\r\n" + 
		    		"(case a.type_of_hldg when 'G1' then a.tothol else 0 end) as ber\r\n" + 
		    		"from (SELECT 	m.sus_no,\r\n" + 
		    		"m.census_no,\r\n" + 
		    		"m.type_of_hldg,\r\n" + 
		    		"count(m.regn_seq_no) AS tothol\r\n" + 
		    		"FROM mms_tb_regn_mstr_detl m  where m.sus_no=?\r\n" + 
		    		"GROUP BY m.sus_no, m.census_no, m.type_of_hldg, m.type_of_eqpt\r\n" + 
		    		"UNION ALL\r\n" + 
		    		"SELECT d.sus_no,\r\n" + 
		    		"d.census_no,\r\n" + 
		    		"d.type_of_hldg,\r\n" + 
		    		"count(d.regn_seq_no) AS tothol\r\n" + 
		    		"FROM mms_tb_depot_regn_mstr_detl d where d.sus_no=?\r\n" + 
		    		"GROUP BY d.sus_no, d.census_no, d.type_of_hldg, d.type_of_eqpt\r\n" + 
		    		"UNION ALL\r\n" + 
		    		"SELECT o.to_sus_no AS sus_no,\r\n" + 
		    		"o.census_no,\r\n" + 
		    		"o.type_of_hldg,\r\n" + 
		    		"count(o.regn_seq_no) AS tothol\r\n" + 
		    		"FROM mms_tb_regn_oth_mstr o where o.to_sus_no=?\r\n" + 
		    		"GROUP BY o.to_sus_no, o.census_no, o.type_of_hldg, o.type_of_eqpt) a ) p \r\n" + 
		    		"inner join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no\r\n" + 
		    		"inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n" + 
		    		"group by p.sus_no,p.census_no\r\n" + 
		    		"order by p.sus_no,max(f.prf_group_code),p.census_no";
		    PreparedStatement stmt = conn.prepareStatement(nrSql);
		    stmt.setString(1, nParaValue);
		    stmt.setString(2, nParaValue);
		    stmt.setString(3, nParaValue);
		    ResultSet rs = stmt.executeQuery();   
			String nrStr="";
			
			if(!rs.isBeforeFirst()){	
				 ArrayList<String> list = new ArrayList<String>();
				 list.add("NIL");
				 li.add(list); 
			 }else {
				 while(rs.next()){
					 ArrayList<String> list = new ArrayList<String>();
					 list.add(rs.getString("sus_no"));
		             list.add(rs.getString("prf_group"));
		             list.add(rs.getString("census_no"));
		             list.add(rs.getString("nomen"));
		             list.add(rs.getString("totfreestk"));
		             list.add(rs.getString("totrepd"));
		             list.add(rs.getString("totrepw"));
		             list.add(rs.getString("totwwr"));
		             list.add(rs.getString("tottrss"));
		             list.add(rs.getString("totetsr"));
		             list.add(rs.getString("totothr"));
		             list.add(rs.getString("totblr"));
		             list.add(rs.getString("totber")); 
		             li.add(list);   	
		         }
			 }
			
     		rs.close();
     		stmt.close();
     		conn.close();
       }catch(SQLException e) {
    	   e.printStackTrace();
       }	
       return li;
	}
	

	public Boolean ifExistDepotRIONo(String rio_no) {
		String q = "from MMS_TB_DEPOT_REGN_MSTR where rio_no=:rio_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
	   	query.setParameter("rio_no", rio_no);
	   	@SuppressWarnings("unchecked")
		List<MMS_TB_DEPOT_REGN_MSTR> list = (List<MMS_TB_DEPOT_REGN_MSTR>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}

	public Boolean ifExistDepotEqptRegNo(String eqpt_regn_no) {
		String q = "from MMS_TB_DEPOT_REGN_MSTR_DETL where eqpt_regn_no=:eqpt_regn_no";
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    Query query = session.createQuery(q);
	   	query.setParameter("eqpt_regn_no", eqpt_regn_no);
	   	@SuppressWarnings("unchecked")
		List<MMS_TB_DEPOT_REGN_MSTR_DETL> list = (List<MMS_TB_DEPOT_REGN_MSTR_DETL>) query.list();
	   	tx.commit();
	   	session.close();
   	    if(list.size() > 0) {
   	    	return true;
   	    }else {
   	    	return false;
   	    }
	}
}