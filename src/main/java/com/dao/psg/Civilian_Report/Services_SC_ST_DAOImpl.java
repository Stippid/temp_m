package com.dao.psg.Civilian_Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class Services_SC_ST_DAOImpl implements Services_SC_ST_DAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
	}
	 
	 
	 
	 
	 public ArrayList<ArrayList<String>> Search_Services_SC_ST(){	 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
			Connection conn = null;
			String q="";		
				try{	  
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;		
					q="	  select distinct\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' )) as per_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_a_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_a_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' )) as per_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_b_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '3'  )) as per_b_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2')) as per_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_c_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_c_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' )) as per_total_t,	  \r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_total_sc,	   \r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_total_st,	   \r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' )) as temp_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_a_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_a_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' )) as temp_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_b_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_b_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' )) as temp_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '2')) as temp_c_sc,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '3' )) as temp_c_st,\r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1')) as temp_total_t,	  \r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_total_sc,	   \r\n" + 
							"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_total_st,	 \r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' )) as grand_total,\r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' and hld.category_belongs = '2' )) as grand_total_sc,	   \r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' and hld.category_belongs = '3' )) as grand_total_st,	 \r\n" + 
							"	\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status != '4')) as per_a_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_a_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_a_st1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status != '4')) as per_b_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_b_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4' )) as per_b_st1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status != '4')) as per_c_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_c_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_c_st1,	\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and status != '4')) as per_total_t1,	  \r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_total_sc1,	   \r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_total_st1,	\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status != '4')) as temp_a_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_a_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_a_st1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status != '4')) as temp_b_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_b_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_b_st1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status != '4')) as temp_c_total1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_c_sc1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_c_st1,\r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and status != '4')) as temp_total_t1,	  \r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_total_sc1,	   \r\n" + 
							"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_total_st1,\r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and status != '4')) as grand_total1,\r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and hld.category_belongs = '2' and status != '4')) as grand_total_sc1,	   \r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and hld.category_belongs = '3' and status != '4')) as grand_total_st1	 ,  \r\n" + 
							"	   \r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status = '4')) as n_per_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status = '4')) as n_per_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status = '4')) as n_per_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.service_status = '2' and status = '4')) as n_per_total_t,	  	   \r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status = '4')) as n_temp_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status = '4')) as n_temp_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status = '4')) as n_temp_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.service_status = '1' and status = '4')) as n_temp_total_t,	   \r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='N_PRV_YR'  and status = '4')) as n_grand_total,\r\n" + 
							"	   \r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status = '4')) as nc_per_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status = '4')) as nc_per_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status = '4')) as nc_per_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.service_status = '2' and status = '4')) as nc_per_total_t,	  	   \r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status = '4')) as nc_temp_a_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status = '4')) as nc_temp_b_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status = '4')) as nc_temp_c_total,\r\n" + 
							"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.service_status = '1' and status = '4')) as nc_temp_total_t,	   \r\n" + 
							"	   count(hld.service_status) filter(where (hld.typ='N_CRN_YR'  and status = '4')) as nc_grand_total\r\n" + 
							"	   \r\n" + 
							"	   \r\n" + 
							"	 from ((\r\n" + 
							"		 select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'PRV_YR' AS typ  \r\n" + 
							"			from tb_psg_civilian_dtl_main c where c.status in ('1','4') \r\n" + 
							"			and c.id not in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
							"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day' and j.status in ('4')\r\n" + 
							"			 and j.date_non_effective is not null ) and \r\n" + 
							"			 to_date(to_char(c.joining_date_gov_service::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day'\r\n" + 
							"		)		union all\r\n" + 
							"		(			 \r\n" + 
							"select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'CRN_YR' AS typ  \r\n" + 
							"			from tb_psg_civilian_dtl_main c where c.status in ('1','4') \r\n" + 
							"			and c.id NOT in (select j.id from  tb_psg_civilian_dtl_main j where\r\n" + 
							"						    to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= now()::date and j.status in ('4')\r\n" + 
							"			   and j.date_non_effective is not null )\r\n" + 
							"		)	\r\n" + 
							"		   union all\r\n" + 
							"		   (select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'N_PRV_YR' AS typ  \r\n" + 
							"			from tb_psg_civilian_dtl_main c where  \r\n" + 
							"		 c.id  in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
							"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') >= date_trunc('year', now())::DATE - interval '1 year' and\r\n" + 
							"						     to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day' and j.status in ('4')\r\n" + 
							"			 and j.date_non_effective is not null ) and \r\n" + 
							"			 to_date(to_char(c.joining_date_gov_service::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day'\r\n" + 
							"			  )\r\n" + 
							"			 UNION ALL\r\n" + 
							" 			( select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'N_CRN_YR' AS typ  \r\n" + 
							"			from tb_psg_civilian_dtl_main c where \r\n" + 
							"			 c.id  in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
							"			 to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') >= date_trunc('year', current_date) and				 \r\n" + 
							"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', CURRENT_DATE)::DATE + interval '12 month - 1 day' and j.status in ('4')\r\n" + 
							"			 and j.date_non_effective is not null )  \r\n" + 
							"))hld " ;
						
					  stmt=conn.prepareStatement(q);
					  System.err.println("query for agewise.....: \n" + stmt);
				      ResultSet rs = stmt.executeQuery();   
				      ResultSetMetaData metaData = rs.getMetaData();
				      
				      
				     
				      int columnCount = metaData.getColumnCount();
				      while (rs.next()) {
				    	  ArrayList<String> list = new ArrayList<String>();
				    	  list.add(rs.getString("per_a_total"));//0
				    	  list.add(rs.getString("per_a_sc"));//1
				    	  list.add(rs.getString("per_a_st"));//2
				    	  list.add(rs.getString("per_b_total"));//3
				    	  list.add(rs.getString("per_b_sc"));//4
				    	  list.add(rs.getString("per_b_st"));//5
						  list.add(rs.getString("per_c_total"));//6
						  list.add(rs.getString("per_c_sc"));//7
						  list.add(rs.getString("per_c_st"));//8
				    	  list.add(rs.getString("per_total_t"));//9	    	  
				    	  list.add(rs.getString("per_total_sc"));//10
				    	  list.add(rs.getString("per_total_st"));//11
				    	  list.add(rs.getString("temp_a_total"));//12
				    	  list.add(rs.getString("temp_a_sc"));//13
				    	  list.add(rs.getString("temp_a_st"));//14
				    	  list.add(rs.getString("temp_b_total"));//15
						  list.add(rs.getString("temp_b_sc"));//16
						  list.add(rs.getString("temp_b_st"));//17
						  list.add(rs.getString("temp_c_total"));//18
				    	  list.add(rs.getString("temp_c_sc"));//19
				    	  list.add(rs.getString("temp_c_st"));//20
				    	  list.add(rs.getString("temp_total_t"));//21		    	  
				    	  list.add(rs.getString("temp_total_sc"));//22
				    	  list.add(rs.getString("temp_total_st"));//23
				    	  list.add(rs.getString("grand_total"));//24
				    	  list.add(rs.getString("grand_total_sc"));//25
						  list.add(rs.getString("grand_total_st"));//26
						  list.add(rs.getString("per_a_total1"));//27
						  list.add(rs.getString("per_a_sc1"));//28
				    	  list.add(rs.getString("per_a_st1"));//29
				    	  list.add(rs.getString("per_b_total1"));//30
				    	  list.add(rs.getString("per_b_sc1"));//31
				    	  list.add(rs.getString("per_b_st1"));//32
				    	  list.add(rs.getString("per_c_total1"));//33
				    	  list.add(rs.getString("per_c_sc1"));//34
				    	  list.add(rs.getString("per_c_st1"));//35
						  list.add(rs.getString("per_total_t1"));//36
						  list.add(rs.getString("per_total_sc1"));//37
						  list.add(rs.getString("per_total_st1"));//38
				    	  list.add(rs.getString("temp_a_total1"));//39
				    	  list.add(rs.getString("temp_a_sc1"));//40
				    	  list.add(rs.getString("temp_a_st1"));//41
				    	  list.add(rs.getString("temp_b_total1"));//42
				    	  list.add(rs.getString("temp_b_sc1"));//43
				    	  list.add(rs.getString("temp_b_st1"));//44
				    	  list.add(rs.getString("temp_c_total1"));//45
						  list.add(rs.getString("temp_c_sc1"));//46
						  list.add(rs.getString("temp_c_st1"));//47
						  list.add(rs.getString("temp_total_t1"));//48
				    	  list.add(rs.getString("temp_total_sc1"));//49
				    	  list.add(rs.getString("temp_total_st1"));//50
				    	  list.add(rs.getString("grand_total1"));//51
				    	  list.add(rs.getString("grand_total_sc1"));//52
				    	  list.add(rs.getString("grand_total_st1"));//53	
				    	  
				    	  
				    	  list.add(rs.getString("n_per_a_total"));//54
				    	  list.add(rs.getString("n_per_b_total"));//55
				    	  list.add(rs.getString("n_per_c_total"));//56
				    	  
				          list.add(rs.getString("n_per_total_t"));//57
						  
				    	  
				          list.add(rs.getString("n_temp_a_total"));//58
				    	  list.add(rs.getString("n_temp_b_total"));//59
				    	  list.add(rs.getString("n_temp_c_total"));//60
				    	  
				    	  list.add(rs.getString("n_temp_total_t"));//61
				    	  list.add(rs.getString("n_grand_total"));//62
				    	  
				    	  list.add(rs.getString("nc_per_a_total"));//63
				    	  list.add(rs.getString("nc_per_b_total"));//64
				    	  list.add(rs.getString("nc_per_c_total"));//65
				    	  
				          list.add(rs.getString("nc_per_total_t"));//66
						  
				    	  
				          list.add(rs.getString("nc_temp_a_total"));//67
				    	  list.add(rs.getString("nc_temp_b_total"));//68
				    	  list.add(rs.getString("nc_temp_c_total"));//69
				    	  
				    	  list.add(rs.getString("nc_temp_total_t"));//70
				    	  list.add(rs.getString("nc_grand_total"));//71
					      alist.add(list);					
			 	        }
				      rs.close();
				      stmt.close();
				      conn.close();
				   }catch (SQLException e) {
						//throw new RuntimeException(e);
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
						  }
						}
					}
					return alist;
				}
	 
	 

	 public ArrayList<ArrayList<String>> PDF_Services_SC_ST(){	 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();		
			Connection conn = null;
			String q="";
			String qry="";
				
				try{	  
						conn = dataSource.getConnection();			 
						PreparedStatement stmt=null;
						
						q="	  select distinct\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' )) as per_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_a_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_a_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' )) as per_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_b_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '3'  )) as per_b_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2')) as per_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_c_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_c_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' )) as per_total_t,	  \r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' and hld.category_belongs = '2' )) as per_total_sc,	   \r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '2' and hld.category_belongs = '3' )) as per_total_st,	   \r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' )) as temp_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_a_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_a_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' )) as temp_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_b_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_b_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' )) as temp_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '2')) as temp_c_sc,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '3' )) as temp_c_st,\r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1')) as temp_total_t,	  \r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1' and hld.category_belongs = '2' )) as temp_total_sc,	   \r\n" + 
								"	   count(*) filter(where (hld.typ='PRV_YR' and hld.service_status = '1' and hld.category_belongs = '3')) as temp_total_st,	 \r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' )) as grand_total,\r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' and hld.category_belongs = '2' )) as grand_total_sc,	   \r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='PRV_YR' and hld.category_belongs = '3' )) as grand_total_st,	 \r\n" + 
								"	\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status != '4')) as per_a_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_a_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_a_st1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status != '4')) as per_b_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_b_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4' )) as per_b_st1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status != '4')) as per_c_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_c_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_c_st1,	\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and status != '4')) as per_total_t1,	  \r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and hld.category_belongs = '2' and status != '4')) as per_total_sc1,	   \r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '2' and hld.category_belongs = '3' and status != '4')) as per_total_st1,	\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status != '4')) as temp_a_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_a_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_a_st1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status != '4')) as temp_b_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_b_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_b_st1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status != '4')) as temp_c_total1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_c_sc1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_c_st1,\r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and status != '4')) as temp_total_t1,	  \r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and hld.category_belongs = '2' and status != '4')) as temp_total_sc1,	   \r\n" + 
								"	   count(*) filter(where (hld.typ='CRN_YR' and hld.service_status = '1' and hld.category_belongs = '3' and status != '4')) as temp_total_st1,\r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and status != '4')) as grand_total1,\r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and hld.category_belongs = '2' and status != '4')) as grand_total_sc1,	   \r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='CRN_YR' and hld.category_belongs = '3' and status != '4')) as grand_total_st1	 ,  \r\n" + 
								"	   \r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status = '4')) as n_per_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status = '4')) as n_per_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status = '4')) as n_per_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.service_status = '2' and status = '4')) as n_per_total_t,	  	   \r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status = '4')) as n_temp_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status = '4')) as n_temp_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status = '4')) as n_temp_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_PRV_YR' and hld.service_status = '1' and status = '4')) as n_temp_total_t,	   \r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='N_PRV_YR'  and status = '4')) as n_grand_total,\r\n" + 
								"	   \r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'A' and hld.service_status = '2' and status = '4')) as nc_per_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'B' and hld.service_status = '2' and status = '4')) as nc_per_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'C' and hld.service_status = '2' and status = '4')) as nc_per_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.service_status = '2' and status = '4')) as nc_per_total_t,	  	   \r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'A' and hld.service_status = '1' and status = '4')) as nc_temp_a_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'B' and hld.service_status = '1' and status = '4')) as nc_temp_b_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.civ_group = 'C' and hld.service_status = '1' and status = '4')) as nc_temp_c_total,\r\n" + 
								"	   count(*) filter(where (hld.typ='N_CRN_YR' and hld.service_status = '1' and status = '4')) as nc_temp_total_t,	   \r\n" + 
								"	   count(hld.service_status) filter(where (hld.typ='N_CRN_YR'  and status = '4')) as nc_grand_total\r\n" + 
								"	   \r\n" + 
								"	   \r\n" + 
								"	 from ((\r\n" + 
								"		 select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'PRV_YR' AS typ  \r\n" + 
								"			from tb_psg_civilian_dtl_main c where c.status in ('1','4') \r\n" + 
								"			and c.id not in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
								"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day' and j.status in ('4')\r\n" + 
								"			 and j.date_non_effective is not null ) and \r\n" + 
								"			 to_date(to_char(c.joining_date_gov_service::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day'\r\n" + 
								"		)		union all\r\n" + 
								"		(			 \r\n" + 
								"select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'CRN_YR' AS typ  \r\n" + 
								"			from tb_psg_civilian_dtl_main c where c.status in ('1','4') \r\n" + 
								"			and c.id NOT in (select j.id from  tb_psg_civilian_dtl_main j where\r\n" + 
								"						    to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= now()::date and j.status in ('4')\r\n" + 
								"			   and j.date_non_effective is not null )\r\n" + 
								"		)	\r\n" + 
								"		   union all\r\n" + 
								"		   (select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'N_PRV_YR' AS typ  \r\n" + 
								"			from tb_psg_civilian_dtl_main c where  \r\n" + 
								"		 c.id  in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
								"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') >= date_trunc('year', now())::DATE - interval '1 year' and\r\n" + 
								"						     to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day' and j.status in ('4')\r\n" + 
								"			 and j.date_non_effective is not null ) and \r\n" + 
								"			 to_date(to_char(c.joining_date_gov_service::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', now())::DATE - interval '1 day'\r\n" + 
								"			  )\r\n" + 
								"			 UNION ALL\r\n" + 
								" 			( select c.civ_group,c.category_belongs,c.service_status,to_date(to_char(c.date_non_effective::date,'Mon YYYY'),'Mon YYYY'),c.status,'N_CRN_YR' AS typ  \r\n" + 
								"			from tb_psg_civilian_dtl_main c where \r\n" + 
								"			 c.id  in (select j.id from  tb_psg_civilian_dtl_main j where \r\n" + 
								"			 to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') >= date_trunc('year', current_date) and				 \r\n" + 
								"		   to_date(to_char(j.date_non_effective::date,'Mon YYYY'),'Mon YYYY') <= date_trunc('year', CURRENT_DATE)::DATE + interval '12 month - 1 day' and j.status in ('4')\r\n" + 
								"			 and j.date_non_effective is not null )  \r\n" + 
								"))hld " ;
						
					  stmt=conn.prepareStatement(q);
				      ResultSet rs = stmt.executeQuery();   
				      ResultSetMetaData metaData = rs.getMetaData();
				      int columnCount = metaData.getColumnCount();
				      while (rs.next()) {
				    	  ArrayList<String> list = new ArrayList<String>();
				    	  
				    	  list.add(rs.getString("per_a_total"));//0
				    	  list.add(rs.getString("per_a_sc"));//1
				    	  list.add(rs.getString("per_a_st"));//2
				    	  
				    	  list.add(rs.getString("n_per_a_total"));//54
				    	  
				    	  list.add(rs.getString("per_a_total1"));//27
						  list.add(rs.getString("per_a_sc1"));//28
				    	  list.add(rs.getString("per_a_st1"));//29
				    	  
				    	  list.add(rs.getString("nc_per_a_total"));//63
				    	  
				    	  
				    	  
				    	  
				    	  list.add(rs.getString("per_b_total"));//3
				    	  list.add(rs.getString("per_b_sc"));//4
				    	  list.add(rs.getString("per_b_st"));//5
				    	  
				    	  list.add(rs.getString("n_per_b_total"));//55
				    	  
				    	  list.add(rs.getString("per_b_total1"));//30
				    	  list.add(rs.getString("per_b_sc1"));//31
				    	  list.add(rs.getString("per_b_st1"));//32
				    	  
				    	  list.add(rs.getString("nc_per_b_total"));//64
				    	  
				    	  
				    	  
				    	  
				    	  
						  list.add(rs.getString("per_c_total"));//6
						  list.add(rs.getString("per_c_sc"));//7
						  list.add(rs.getString("per_c_st"));//8
						  
						  list.add(rs.getString("n_per_c_total"));//55
						  
						  list.add(rs.getString("per_c_total1"));//33
				    	  list.add(rs.getString("per_c_sc1"));//34
				    	  list.add(rs.getString("per_c_st1"));//35
				    	  
				    	  list.add(rs.getString("nc_per_c_total"));//65
				    	  
				    	  
				    	  
				    	  
						  
				    	  list.add(rs.getString("per_total_t"));//9	 
				    	  list.add(rs.getString("per_total_sc"));//10
				    	  list.add(rs.getString("per_total_st"));//11
				    	  
				    	  
				    	  list.add(rs.getString("n_per_total_t"));//57
				    	  
				    	  
				    	  list.add(rs.getString("per_total_t1"));//36
						  list.add(rs.getString("per_total_sc1"));//37
						  list.add(rs.getString("per_total_st1"));//38
						  
						   list.add(rs.getString("nc_per_total_t"));//66
						   
						   
						   
				    	  
				    	  
				    	  
				    	  list.add(rs.getString("temp_a_total"));//12
				    	  list.add(rs.getString("temp_a_sc"));//13
				    	  list.add(rs.getString("temp_a_st"));//14
				    	  
				    	   list.add(rs.getString("n_temp_a_total"));//58
				    	   
				    	   list.add(rs.getString("temp_a_total1"));//39
					    	  list.add(rs.getString("temp_a_sc1"));//40
					    	  list.add(rs.getString("temp_a_st1"));//41
					    	  
					          list.add(rs.getString("nc_temp_a_total"));//67
					          
					          
					          
					          
				    	  
				    	  list.add(rs.getString("temp_b_total"));//15
						  list.add(rs.getString("temp_b_sc"));//16
						  list.add(rs.getString("temp_b_st"));//17
						  
						  list.add(rs.getString("n_temp_b_total"));//59
						  
						  list.add(rs.getString("temp_b_total1"));//42
				    	  list.add(rs.getString("temp_b_sc1"));//43
				    	  list.add(rs.getString("temp_b_st1"));//44
				    	  
				    	  
				    	  list.add(rs.getString("nc_temp_b_total"));//68
				    	  
				    	  
				    	  
						  
						  list.add(rs.getString("temp_c_total"));//18
				    	  list.add(rs.getString("temp_c_sc"));//19
				    	  list.add(rs.getString("temp_c_st"));//20
				    	  
				     	  list.add(rs.getString("n_temp_c_total"));//60
				     	  
				     	 list.add(rs.getString("temp_c_total1"));//45
						  list.add(rs.getString("temp_c_sc1"));//46
						  list.add(rs.getString("temp_c_st1"));//47
						  
						  list.add(rs.getString("nc_temp_c_total"));//69
						  
						  
						  
				    	  
				    	  list.add(rs.getString("temp_total_t"));//21		    	  
				    	  list.add(rs.getString("temp_total_sc"));//22
				    	  list.add(rs.getString("temp_total_st"));//23
				    	  
				    	  list.add(rs.getString("n_temp_total_t"));//61
				    	  
				    	  list.add(rs.getString("temp_total_t1"));//48
				    	  list.add(rs.getString("temp_total_sc1"));//49
				    	  list.add(rs.getString("temp_total_st1"));//50
				    	  
				    	  list.add(rs.getString("nc_temp_total_t"));//70
				    	  
				    	  
				    	  
				    	  
				    	  list.add(rs.getString("grand_total"));//24
				    	  list.add(rs.getString("grand_total_sc"));//25
						  list.add(rs.getString("grand_total_st"));//26
						  
						  
						  list.add(rs.getString("n_grand_total"));//62
						  
				    	  
				    	  list.add(rs.getString("grand_total1"));//51
				    	  list.add(rs.getString("grand_total_sc1"));//52
				    	  list.add(rs.getString("grand_total_st1"));//53	
				    	  
				    	  
				    	  list.add(rs.getString("nc_grand_total"));//71
				    
					      alist.add(list);	
							
			 	        }
				      rs.close();
				      stmt.close();
				      conn.close();
				   }catch (SQLException e) {
						//throw new RuntimeException(e);
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
						  }
						}
					}
					return alist;
				}	 
}
