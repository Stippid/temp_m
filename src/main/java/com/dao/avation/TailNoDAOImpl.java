package com.dao.avation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.TB_AVIATION_CHTL_TAILNO_DTL;
import com.models.TB_AVIATION_RPAS_TAILNO_DTL;
import com.models.TB_AVIATION_TAILNO_DTL;
import com.models.TB_MISO_MSTR_COUNTRY;
import com.models.T_Domain_Value;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

//Created By Mitesh  (20-11-2024)

public class TailNoDAOImpl implements TailNoDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@SuppressWarnings("unchecked")
	public boolean ifExistActNo(String tail_no) {
		String q = "from TB_AVIATION_TAILNO_DTL where tail_no =:tail_no";
   	    List<TB_AVIATION_TAILNO_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("tail_no", tail_no);
	   		created_by = (List<TB_AVIATION_TAILNO_DTL>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    e.printStackTrace();
   		    return (Boolean) null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }
	
	@SuppressWarnings("unchecked")
	public boolean ifExistRPASActNo(String tail_no) {
		String q = "from TB_AVIATION_RPAS_TAILNO_DTL where tail_no =:tail_no";
   	    List<TB_AVIATION_RPAS_TAILNO_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("tail_no", tail_no);
	   		created_by = (List<TB_AVIATION_RPAS_TAILNO_DTL>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    e.printStackTrace();
   		    return (Boolean) null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }
	
	@SuppressWarnings("unchecked")
	public boolean ifExistCHTLActNo(String tail_no) {
		String q = "from TB_AVIATION_CHTL_TAILNO_DTL where tail_no =:tail_no";
   	    List<TB_AVIATION_CHTL_TAILNO_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("tail_no", tail_no);
	   		created_by = (List<TB_AVIATION_CHTL_TAILNO_DTL>) query.list();
	   		tx.commit();
	   		session.close();
   	    }catch(Exception e) {
   	    	session.getTransaction().rollback();
   		    e.printStackTrace();
   		    return (Boolean) null; 
   	    }
   	    if(created_by.size() > 0) {
   	    	return true;
   	    }
   	    return false;
    }
	
	//ADDED BY MITESH 27-01-2025
		@RequestMapping(value = "/Getallcountry", method = RequestMethod.POST)
		public @ResponseBody List<TB_MISO_MSTR_COUNTRY> Getallcountry() {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();

			Query q = session.createQuery(
					"from TB_MISO_MSTR_COUNTRY ORDER BY country ASC");
			@SuppressWarnings("unchecked")
			List<TB_MISO_MSTR_COUNTRY> list = (List<TB_MISO_MSTR_COUNTRY>) q.list();
			
			tx.commit();
			session.close();
			return list;
		}
		
		public ArrayList<ArrayList<String>> getTailNoCurrentStatus(String tail_no, String roleType) {
		    Session session = HibernateUtilNA.getSessionFactory().openSession();
		    Transaction tx = null;
		    ArrayList<ArrayList<String>> resultList = new ArrayList<>();

		    try {
		        tx = session.beginTransaction();

		        // Fetch the classifications for the tail number
		        String classificationQuery = "select classifications from TB_AVIATION_TAILNO_DTL where tail_no = :tail_no";
		        Query classificationQ = session.createQuery(classificationQuery);
		        classificationQ.setParameter("tail_no", tail_no);
		        List<String> classifications = classificationQ.list();

		        // Handle case where no classifications are found
		        if (classifications == null || classifications.isEmpty()) {
		            ArrayList<String> errorRow = new ArrayList<>();
		            errorRow.add("No classifications found for the given Tail Number.");
		           /* resultList.add(errorRow);*/
		            return resultList;
		        }

		        // Prepare the query string based on classification
		        String queryStr = "";
		        if (classifications.contains("3")) {
		            queryStr = "select a.tail_no, a.std_nomclature, b.agency_name, a.classifications from tb_aviation_tail_no_dtl a " +
		                    "left join tb_aviation_drr_dtl b on b.tail_no = a.tail_no where a.tail_no = :tail_no order by b.id desc limit 1";
		        } else {
		            queryStr = "select a.tail_no, a.std_nomclature, a.sus_no, a.unit_name, a.classifications from tb_aviation_tail_no_dtl a " +
		                    "where a.tail_no = :tail_no";
		        }

		        // Execute the query to fetch results
		        Query query = session.createSQLQuery(queryStr);
		        query.setParameter("tail_no", tail_no);
		        List<Object[]> results = query.list();

		        // If no results are returned, add a "No data found" message
		        if (results.isEmpty()) {
		            ArrayList<String> noDataRow = new ArrayList<>();
		            noDataRow.add("No data found for the given Tail Number.");
		            resultList.add(noDataRow);
		        } else {
		            // Process the results
		            for (Object[] result : results) {
		                ArrayList<String> resultRow = new ArrayList<>();

		                // Ensure the result has enough columns
		                if (result.length >= 4) {
		                    resultRow.add(result[0] != null ? result[0].toString() : "");  // tail_no
		                    resultRow.add(result[1] != null ? result[1].toString() : "");  // std_nomclature

		                    // Add classification value (always at index 4 in the second query, if 5 columns are returned)
		                    if (result.length >= 5) {
		                        resultRow.add(result[4] != null ? result[4].toString() : "");  // classifications
		                    } else {
		                        resultRow.add("");  // Default empty string if classifications are missing
		                    }

		                    // Handle the classification check to display either agency_name or sus_no/unit_name
		                    if (classifications.contains("3")) {
		                    	resultRow.add("");
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // agency_name
		                    } else {
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // sus_no
		                        resultRow.add(result[3] != null ? result[3].toString() : "");  // unit_name
		                    }
		                }

		                // Construct Edit and Delete buttons based on the role and status
		                String f = "";
		                String editButton = "&nbsp;&nbsp;<i onclick=Update('" + result[0].toString() + "') title='View Data'>Edit</i>&nbsp;&nbsp;&nbsp;";
		              
		                    if ("ALL".equals(roleType) || "DEO".equals(roleType)) {
		                        f += editButton;
		                      
		                    }
		                
		                resultRow.add(f);
		                resultList.add(resultRow);
		            }
		        }

		        // Commit the transaction if no errors
		        tx.commit();

		    } catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();  // Rollback if there's an error
		        }
		        e.printStackTrace();
		        ArrayList<String> errorRow = new ArrayList<>();
		        errorRow.add("An error occurred while fetching data.");
		        resultList.add(errorRow);
		    } finally {
		        // Make sure to close the session
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }

		    return resultList;
		}
		
		public ArrayList<ArrayList<String>> getCHTLTailNoCurrentStatus(String tail_no, String roleType) {
		    Session session = HibernateUtilNA.getSessionFactory().openSession();
		    Transaction tx = null;
		    ArrayList<ArrayList<String>> resultList = new ArrayList<>();

		    try {
		        tx = session.beginTransaction();

		        // Fetch the classifications for the tail number
		        String classificationQuery = "select classifications from TB_AVIATION_CHTL_TAILNO_DTL where tail_no = :tail_no";
		        Query classificationQ = session.createQuery(classificationQuery);
		        classificationQ.setParameter("tail_no", tail_no);
		        List<String> classifications = classificationQ.list();

		        // Handle case where no classifications are found
		        if (classifications == null || classifications.isEmpty()) {
		            ArrayList<String> errorRow = new ArrayList<>();
		           /* errorRow.add("No classifications found for the given Tail Number.");
		            resultList.add(errorRow);*/
		            return resultList;
		        }

		        // Prepare the query string based on classification
		        String queryStr = "";
		        if (classifications.contains("3")) {
		            queryStr = "select a.tail_no, a.std_nomclature, b.agency_name, a.classifications from tb_aviation_chtl_tail_no_dtl a " +
		                    "left join tb_aviation_drr_dtl b on b.tail_no = a.tail_no where a.tail_no = :tail_no order by b.id desc limit 1";
		        } else {
		            queryStr = "select a.tail_no, a.std_nomclature, a.sus_no, a.unit_name, a.classifications from tb_aviation_chtl_tail_no_dtl a " +
		                    "where a.tail_no = :tail_no";
		        }

		        // Execute the query to fetch results
		        Query query = session.createSQLQuery(queryStr);
		        query.setParameter("tail_no", tail_no);
		        List<Object[]> results = query.list();

		        // If no results are returned, add a "No data found" message
		        if (results.isEmpty()) {
		            ArrayList<String> noDataRow = new ArrayList<>();
		            noDataRow.add("No data found for the given Tail Number.");
		            resultList.add(noDataRow);
		        } else {
		            // Process the results
		            for (Object[] result : results) {
		                ArrayList<String> resultRow = new ArrayList<>();

		                // Ensure the result has enough columns
		                if (result.length >= 4) {
		                    resultRow.add(result[0] != null ? result[0].toString() : "");  // tail_no
		                    resultRow.add(result[1] != null ? result[1].toString() : "");  // std_nomclature

		                    // Add classification value (always at index 4 in the second query, if 5 columns are returned)
		                    if (result.length >= 5) {
		                        resultRow.add(result[4] != null ? result[4].toString() : "");  // classifications
		                    } else {
		                        resultRow.add("");  // Default empty string if classifications are missing
		                    }

		                    // Handle the classification check to display either agency_name or sus_no/unit_name
		                    if (classifications.contains("3")) {
		                    	resultRow.add("");
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // agency_name
		                    } else {
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // sus_no
		                        resultRow.add(result[3] != null ? result[3].toString() : "");  // unit_name
		                    }
		                }

		                // Construct Edit and Delete buttons based on the role and status
		                String f = "";
		                String editButton = "&nbsp;&nbsp;<i onclick=Edit('" + result[0].toString() + "') title='View Data'>Edit</i>&nbsp;&nbsp;&nbsp;";
		              
		                    if ("ALL".equals(roleType) || "DEO".equals(roleType)) {
		                        f += editButton;
		                      
		                    }
		                
		                resultRow.add(f);
		                resultList.add(resultRow);
		            }
		        }

		        // Commit the transaction if no errors
		        tx.commit();

		    } catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();  // Rollback if there's an error
		        }
		        e.printStackTrace();
		        ArrayList<String> errorRow = new ArrayList<>();
		        errorRow.add("An error occurred while fetching data.");
		        resultList.add(errorRow);
		    } finally {
		        // Make sure to close the session
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }

		    return resultList;
		}
		
		public ArrayList<ArrayList<String>> getRPASTailNoCurrentStatus(String tail_no, String roleType) {
		    Session session = HibernateUtilNA.getSessionFactory().openSession();
		    Transaction tx = null;
		    ArrayList<ArrayList<String>> resultList = new ArrayList<>();

		    try {
		        tx = session.beginTransaction();

		        // Fetch the classifications for the tail number
		        String classificationQuery = "select classifications from TB_AVIATION_RPAS_TAILNO_DTL where tail_no = :tail_no";
		        Query classificationQ = session.createQuery(classificationQuery);
		        classificationQ.setParameter("tail_no", tail_no);
		        List<String> classifications = classificationQ.list();

		       
		        if (classifications == null || classifications.isEmpty()) {
		            ArrayList<String> errorRow = new ArrayList<>();
		            /*errorRow.add("No classifications found for the given Tail Number.");
		            resultList.add(errorRow);*/
		            return resultList;
		        }

		        // Prepare the query string based on classification
		        String queryStr = "";
		        if (classifications.contains("3")) {
		            queryStr = "select a.tail_no, a.std_nomclature, b.agency_name, a.classifications from tb_aviation_rpas_tail_no_dtl a " +
		                    "left join tb_aviation_drr_dtl b on b.tail_no = a.tail_no where a.tail_no = :tail_no order by b.id desc limit 1";
		        } else {
		            queryStr = "select a.tail_no, a.std_nomclature, a.sus_no, a.unit_name, a.classifications from tb_aviation_rpas_tail_no_dtl a " +
		                    "where a.tail_no = :tail_no";
		        }

		        // Execute the query to fetch results
		        Query query = session.createSQLQuery(queryStr);
		        query.setParameter("tail_no", tail_no);
		        List<Object[]> results = query.list();

		        // If no results are returned, add a "No data found" message
		        if (results.isEmpty()) {
		            ArrayList<String> noDataRow = new ArrayList<>();
		            noDataRow.add("No data found for the given Tail Number.");
		            resultList.add(noDataRow);
		        } else {
		            // Process the results
		            for (Object[] result : results) {
		                ArrayList<String> resultRow = new ArrayList<>();

		                // Ensure the result has enough columns
		                if (result.length >= 4) {
		                    resultRow.add(result[0] != null ? result[0].toString() : "");  // tail_no
		                    resultRow.add(result[1] != null ? result[1].toString() : "");  // std_nomclature

		                    // Add classification value (always at index 4 in the second query, if 5 columns are returned)
		                    if (result.length >= 5) {
		                    	resultRow.add("");
		                        resultRow.add(result[4] != null ? result[4].toString() : "");  // classifications
		                    } else {
		                        resultRow.add("");  // Default empty string if classifications are missing
		                    }

		                    // Handle the classification check to display either agency_name or sus_no/unit_name
		                    if (classifications.contains("3")) {
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // agency_name
		                    } else {
		                        resultRow.add(result[2] != null ? result[2].toString() : "");  // sus_no
		                        resultRow.add(result[3] != null ? result[3].toString() : "");  // unit_name
		                    }
		                }

		                // Construct Edit and Delete buttons based on the role and status
		                String f = "";
		                String editButton = "&nbsp;&nbsp;<i onclick=Modify('" + result[0].toString() + "') title='View Data'>Edit</i>&nbsp;&nbsp;&nbsp;";
		              
		                    if ("ALL".equals(roleType) || "DEO".equals(roleType)) {
		                        f += editButton;
		                      
		                    }
		                
		                resultRow.add(f);
		                resultList.add(resultRow);
		            }
		        }

		        // Commit the transaction if no errors
		        tx.commit();

		    } catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();  // Rollback if there's an error
		        }
		        e.printStackTrace();
		        ArrayList<String> errorRow = new ArrayList<>();
		        errorRow.add("An error occurred while fetching data.");
		        resultList.add(errorRow);
		    } finally {
		        // Make sure to close the session
		        if (session != null && session.isOpen()) {
		            session.close();
		        }
		    }

		    return resultList;
		}
}
