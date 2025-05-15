package com.dao.avation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.models.TB_AVIATION_DRR_DTL;
import com.models.TB_AVIATION_TAILNO_DTL;
import com.persistance.util.HibernateUtil;

public class AVNDRRDAOImpl implements AVNDRRDAO {
	
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    
	@Override
    public ArrayList<List<String>> getsearch_drr_avn(String status, String from_date, String curr_date, String roleType) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
        Connection conn = null;

        if (dataSource == null) {
            System.out.println("DataSource is null. Please check the configuration.");
            return alist;  
        }

        try {
            conn = dataSource.getConnection();

            if (conn == null) {
                System.out.println("Connection could not be established.");
                return alist; 
            }

            PreparedStatement stmt = null;
            String sql = "SELECT DISTINCT d.unit_sus_no, d.drr_ser_no, d.classification, "
                    + "LTRIM(TO_CHAR(d.created_on, 'dd-mm-yyyy'), '') AS creation_date, "
                    + "(SELECT unit_name FROM tb_miso_orbat_unt_dtl WHERE sus_no = d.unit_sus_no AND status_sus_no = 'Active') AS unit_name "
                    + "FROM TB_AVIATION_DRR_DTL d ";  

            List<String> params = new ArrayList<>();

      
            if (status != null && !status.isEmpty()) {
                sql += " where d.status = ?";
                params.add(status);
            }

            if (from_date != null && !from_date.isEmpty()) {
                sql += " AND CAST(d.created_on AS DATE) >= CAST(? AS DATE) ";
                params.add(from_date);
            }

            if (curr_date != null && !curr_date.isEmpty()) {
                sql += " AND CAST(d.created_on AS DATE) <= CAST(? AS DATE)";
                params.add(curr_date);
            }

          
            if (curr_date == null || curr_date.isEmpty()) {
                curr_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }

            stmt = conn.prepareStatement(sql);

            
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {
                List<String> list = new ArrayList<>();

                
                String unitSusNo = rs.getString("unit_sus_no");
                list.add(unitSusNo != null ? unitSusNo : "");   // 0

                String drrSerNo = rs.getString("drr_ser_no");
                list.add(drrSerNo != null ? drrSerNo : "");    // 1

                String classification = rs.getString("classification");
                String typeOfIssue = (classification != null && classification.equals("3")) ? "Received from Unit" : "";
                list.add(typeOfIssue);                         // 2

                String creationDate = rs.getString("creation_date");
                list.add(creationDate != null ? creationDate : ""); // 3

                String unitName = rs.getString("unit_name");
                list.add(unitName != null ? unitName : "");     // 4

                
                String viewButton = "<i class='action_icons action_approve' "
                        + "onclick=\"View('" + (drrSerNo != null ? drrSerNo : "") + "','"
                        + (status != null ? status : "") + "','" + (from_date != null ? from_date : "") + "','"
                        + (status != null ? status : "") + "','"
                        + (unitSusNo != null ? unitSusNo : "") + "','" + (from_date != null ? from_date : "") + "','"
                        + (curr_date != null ? curr_date : "") + "','" + (unitName != null ? unitName : "") + "')\" title='View Data'></i>";

                String f = "";
                if ("0".equals(status)) {
                    if ("ALL".equals(roleType) || "APP".equals(roleType) || "DEO".equals(roleType)) {
                        f += viewButton;
                    }
                } else if ("1".equals(status)) {
                    f += viewButton;
                } else if ("2".equals(status)) {
                    if ("DEO".equals(roleType) || "ALL".equals(roleType)) {
                        f += viewButton;
                    }
                }
                list.add(f);  // 5
                alist.add(list);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return alist;
    }
	
	public  ArrayList<List<String>> getApprovedTail_NoFromDRRAVN(String viewSerNo,String viewStatus,String viewDate,String viewSus,String roleType)
  	{
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(viewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
    	Connection conn = null;
			try{	  
			   conn = dataSource.getConnection();
			   String sql= ""; 
			   sql="select distinct a.id,a.tail_no,a.agency_name,a.unit_sus_no,a.drr_ser_no,a.classification,a.status,b.unit_name\r\n" + 
			  		"	from TB_AVIATION_DRR_DTL a\r\n" + 
			  		"	left join tb_miso_orbat_unt_dtl b on a.unit_sus_no = b.sus_no and b.status_sus_no='Active' " + 
			  		"	where a.drr_ser_no= ?  and a.status= ? and CAST(created_on as date) >= CAST(? as date) and a.unit_sus_no = ?";
			   
			   PreparedStatement stmt=conn.prepareStatement(sql);
			   stmt.setString(1, viewSerNo);
			   stmt.setString(2, viewStatus);
			   stmt.setString(3,viewDate);
			   stmt.setString(4, viewSus);
			   
			   System.err.println("view--"+stmt);
			   ResultSet rs = stmt.executeQuery();
			
			   while(rs.next()){
				   	List<String> list = new  ArrayList<String>();
			   		list.add(rs.getString("id")); //0
			   		list.add(rs.getString("tail_no")); //1
			   		list.add(rs.getString("agency_name")); //2
			   		list.add(rs.getString("unit_sus_no")); //3
			   		list.add(rs.getString("drr_ser_no")); //4
			   		list.add(rs.getString("classification")); //5
			   		list.add(rs.getString("status")); //6
			   		list.add(rs.getString("unit_name")); //7
			   		
			   	 String editButton = "&nbsp;&nbsp;<i  onclick=Update('" + rs.getString("id") +"') title='View Data'>Edit</i>&nbsp;&nbsp;&nbsp;";
				 String Delete = "onclick=\"  if (confirm('Are You Sure you want to Delete this Record?') ){Delete1('"+ rs.getString("id") +"','"+rs.getString("drr_ser_no")+"','"+ rs.getString("tail_no") +"','" + rs.getString("unit_sus_no") +"')}else{ return false;}\"";
				 String deleteButton ="<i  "+Delete+" title='Delete Data'>Delete</i>";
			   	 String f="";
			   	
			   	if(rs.getString("status").equals("0")){
					if(roleType.equals("ALL")) {
						f += editButton;
						f += deleteButton;
					}
					if(roleType.equals("DEO")) {
						f += editButton;
						f += deleteButton;
						
					}
					if(roleType.equals("APP")) {
					
						f += deleteButton;
					}
				
			   	}
			   	else if(rs.getString("status").equals("1")){
					f += "Not Required";
				}
			   	else if(rs.getString("status").equals("2")){
					if(roleType.equals("DEO") || roleType.equals("ALL")) {
						f += deleteButton;
						
					}
				}	
						list.add(f);
						alist.add(list);
					}
    	      rs.close();
		      stmt.close();
		      conn.close();
		   }catch (SQLException e) {
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
	
	
	@SuppressWarnings("unchecked")
	public boolean ifExistDRRNo(String drr_ser_no) {
		String q = "from TB_AVIATION_DRR_DTL where drr_ser_no =:drr_ser_no";
   	    List<TB_AVIATION_DRR_DTL> created_by = null;
   	    Session session = HibernateUtil.getSessionFactory().openSession();
   	    Transaction tx = session.beginTransaction();
   	    try {
   	    	Query query = session.createQuery(q);
	   		query.setParameter("drr_ser_no", drr_ser_no);
	   		created_by = (List<TB_AVIATION_DRR_DTL>) query.list();
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

}
