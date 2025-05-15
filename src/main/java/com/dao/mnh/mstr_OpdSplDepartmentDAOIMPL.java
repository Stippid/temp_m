package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.models.mnh.Tb_Med_Opd_Sp_Department;
import com.models.mnh.Tb_Med_Opd_Sp_Procedure_master;
import com.models.mnh.Tb_Med_Opd_Sp_Subprocedure;
import com.persistance.util.HibernateUtil;

public class mstr_OpdSplDepartmentDAOIMPL implements mstr_OpdSplDepartmentDAO {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public List<Map<String, Object>> getOPDSpDepartListJdbc(String dept_name, String status){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			String flag = "Y";
			
			if(!dept_name.equals("") && !dept_name.equals(null)){
				qry += " Where  Upper(dept_name) like ?";
				flag = "N";
			} 
			if(!status.equals("") && !status.equals(null)){
				if(flag.equalsIgnoreCase("Y")) {
					qry += " Where status = ?";
					flag = "N";
				}else {
					qry += " and status = ?";
				}	
			} 
				
			q = "select distinct id,dept_name,status from tb_med_opd_sp_department " +qry+" order by dept_name";
			stmt=conn.prepareStatement(q);    
			int j=1;  
			if(!dept_name.equals("") && !dept_name.equals(null)){
			 	stmt.setString(j, '%'+dept_name.toUpperCase()+'%');
			 	j+=1;
			} 
			if(!status.equals("") && !status.equals(null)){
			 	stmt.setString(j, status);
			} 
			ResultSet rs = stmt.executeQuery();      
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for(int i = 1; i <= columnCount; i++){
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	          
		 	    String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("dept_name")+"','"+rs.getString("status")+"')}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
		 	  	String f = "";
				f += updateButton;
				f += deleteButton;
	 	  		columns.put(metaData.getColumnLabel(1), f);
		 	    list.add(columns);
		 	}
			rs.close();
			stmt.close();
			conn.close();
	    }catch (SQLException e){
	    	e.printStackTrace();
	    }finally{
	    	if(conn != null){
	    		try{
	    			conn.close();
				}catch(SQLException e){
				}
			}
		}
		return list;
	}
	public String updatedept(Tb_Med_Opd_Sp_Department obj,String username){
		 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = sessionHQL.beginTransaction();
		
		String msg = "";
		try{
			String hql = "update Tb_Med_Opd_Sp_Department set dept_name=:dept_name,status=:status,"
	   				+ "modified_by=:m1,modified_on=:m2 where id=:id";	
	   		Query query = sessionHQL.createQuery(hql).setString("dept_name",obj.getDept_name()).setString("status",obj.getStatus())
	   				.setString("m1", username).setTimestamp("m2", new Date()).setInteger("id",obj.getId());
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	}
	
	
	public Long checkExitstingSpProcedureMaster(Integer old_dept,String old_proc,int id) {
		 
		String hql="select count(id) from Tb_Med_Opd_Sp_Procedure_master where dept_id=:d1 and proc_name=:d2 and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q4= session.createQuery(hql);
		q4.setParameter("d1", old_dept);
		q4.setParameter("d2", old_proc);
		q4.setParameter("id", id);
		Long count_list =  (Long) q4.uniqueResult();	
		tx.commit();
		session.close();
		if(count_list != null)
		{
			return count_list;
		}
		else
		{
			return (long) 0;
		}
		
	}
	
    public String update_opd_sp_proc_name(Tb_Med_Opd_Sp_Procedure_master obj){
      	 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
      	 Transaction tx = sessionHQL.beginTransaction();
      	String msg = "";
      	try{
      		String hql = "update Tb_Med_Opd_Sp_Procedure_master set dept_id=:dept_id,proc_name=:proc_name,status=:status,modified_by=:modified_by,modified_on=:modified_on where id=:id";		
      		Query query = sessionHQL.createQuery(hql).setInteger("dept_id",obj.getDepartment().getId()).setString("proc_name",obj.getProc_name()).setString("status",obj.getStatus())
      				.setString("modified_by",obj.getModified_by())
      				.setTimestamp("modified_on",obj.getModified_on()).setInteger("id", obj.getId());
      		msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
      		tx.commit();
      	}
      	catch (Exception e) {
      		msg = "Data Not Updated.";
      		tx.rollback();
      	}
      	finally {
      		sessionHQL.close();
      	}
      	return msg;
      	}
    
    
	public List<Map<String, Object>> getspprocedureListJdbc(Integer dept_id1,String proc_name1,String stat){
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			
			if(!dept_id1.equals(-1)){
		    	qry += " and a.dept_id = ?";
			} 
			if(!proc_name1.equals("") && !proc_name1.equals(null)){
			 	qry += " and (Upper(a.proc_name) like ?)";
			} 
			if(!stat.equals("") && !stat.equals(null)){
			 	qry += " and a.status = ?";
			} 
			
			q = "select a.id,b.dept_name,a.proc_name,a.status,a.dept_id from tb_med_opd_sp_procedure a, tb_med_opd_sp_department b where a.dept_id =b.id  " +qry+" order by b.dept_name,a.proc_name";
			stmt=conn.prepareStatement(q);
			int j=1;  
			if(!dept_id1.equals(-1)){
				stmt.setInt(j, dept_id1);
			 	j++;
			} 
			if(!proc_name1.equals("") && !proc_name1.equals(null)){
			 	stmt.setString(j, '%'+proc_name1.toUpperCase()+'%');
			 	j+=1;
			} 
			 	
			if(!stat.equals("") && !stat.equals(null)){
				stmt.setString(j, stat);	
			} 
				 
			ResultSet rs = stmt.executeQuery();          
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for(int i = 1; i <= columnCount; i++){
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	    String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("dept_id")+"','"+rs.getString("proc_name")+"')}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
		 	  	String f = "";
				f += updateButton;
				f += deleteButton;
	 	  		columns.put(metaData.getColumnLabel(1), f);
		 	    list.add(columns);
		 	}
			rs.close();
			stmt.close();
			conn.close();
	    }catch (SQLException e){
	    	e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
				}
			}
		}
		return list;
	}
	
	
	public Long checkExitstingSpsubProcedureMaster(Integer old_dept,String old_sub,int id) {
		
		String hql="select count(id) from Tb_Med_Opd_Sp_Subprocedure where dept_id=:d1 and  subproc_name=:d3 and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q4= session.createQuery(hql);
		q4.setParameter("d1", old_dept);
	    q4.setParameter("d3", old_sub);
		q4.setParameter("id", id);
		Long count_list =  (Long) q4.uniqueResult();	
		tx.commit();
		session.close();
		if(count_list != null)
		{
			return count_list;
		}
		else
		{
			return (long) 0;
		}
		
	}
	
	   public String update_opd_sp_subproc_name(Tb_Med_Opd_Sp_Subprocedure obj){
	      	 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	      	 Transaction tx = sessionHQL.beginTransaction();
	      	String msg = "";
	      	try{
	      		String hql = "update Tb_Med_Opd_Sp_Subprocedure set dept_id=:dept_id,proc_id=:proc_id,subproc_name=:subproc_name,status=:status,modified_by=:modified_by,modified_on=:modified_on where id=:id";		
	      		Query query = sessionHQL.createQuery(hql).setInteger("dept_id",obj.getDepartment_subproc().getId()).setInteger("proc_id",obj.getProc_id()).setString("subproc_name", obj.getSubproc_name()).setString("status",obj.getStatus())
	      				.setString("modified_by",obj.getModified_by())
	      				.setTimestamp("modified_on",obj.getModified_on()).setInteger("id", obj.getId());
	      		msg = query.executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
	      		tx.commit();
	      	}
	      	catch (Exception e) {
	      		msg = "Data Not Updated.";
	      		tx.rollback();
	      	}
	      	finally {
	      		sessionHQL.close();
	      	}
	      	return msg;
	   }
    
    public List<Map<String, Object>> getspsubprocedureListJdbc(Integer dept_id1,Integer proc_id1,String subproc_name1,String stat) {
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			String flag="Y";
			
			if(!dept_id1.equals(-1)){
				qry += " where a.dept_id = ?";
				flag="N";
			} 
			if(!proc_id1.equals(-1)){
				if(flag.equalsIgnoreCase("Y")) {
					qry += " where a.proc_id = ?";
					flag = "N";
				}else {
					qry += " and a.proc_id = ?";
				}
			} 
			if(!subproc_name1.equals("") && !subproc_name1.equals(null)){ 
			 	if(flag.equalsIgnoreCase("Y")) {
			 		qry += " where   Upper(a.subproc_name) like ?";
			 		flag = "N";
				}else {
					qry += " and  Upper(a.subproc_name) like ?";
				}
			} 
			if(!stat.equals("") && !stat.equals(null)){ 	
				if(flag.equalsIgnoreCase("Y")) {
					qry += " where a.status = ?";
					flag = "N";
				}else {
					qry += " and a.status = ?";
				}
			} 
			
			q = "select distinct a.id,a.dept_id,c.dept_name,a.proc_id,b.proc_name,a.subproc_name,a.status from tb_med_opd_sp_subprocedure a "
					+ "left join tb_med_opd_sp_procedure b on b.id = a.proc_id "
					+ "left join tb_med_opd_sp_department c on c.id = a.dept_id " +qry + "order by c.dept_name,b.proc_name,a.subproc_name";  
			stmt=conn.prepareStatement(q);
			
			int j=1;
			if(!dept_id1.equals(-1)){
				stmt.setInt(j, dept_id1);
			 	j++;
			} 
			 		
			if(!proc_id1.equals(-1)){
				stmt.setInt(j, proc_id1);
			 	j++;
			}  
			 	
			if(!subproc_name1.equals("") && !subproc_name1.equals(null)){ 
			 	
			 	stmt.setString(j, '%'+subproc_name1.toUpperCase()+'%');
			 	j+=1;
			} 
			 	
			if(!stat.equals("") && !stat.equals(null)){ 	
			 	stmt.setString(j, stat);
			} 
				 
			ResultSet rs = stmt.executeQuery();         
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
		 	    for(int i = 1; i <= columnCount; i++){
		 	    	columns.put(metaData.getColumnLabel(i), rs.getObject(i));
		 	    }
		 	          
		 	    String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("+rs.getObject(1)+",'"+rs.getString("dept_id")+"','"+rs.getString("proc_id")+"','"+rs.getString("subproc_name")+"')}else{ return false;}\"";
				String updateButton ="<i class='action_icons action_update' "+editData+" title='Edit Data'></i>";
				String Delete1 = "onclick=\"  if (confirm('Are you sure you want to delete?') ){deleteData("+rs.getObject(1)+")}else{ return false;}\"";
				String deleteButton ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
		 	  	String f = "";
				f += updateButton;
				f += deleteButton;
	 	  		columns.put(metaData.getColumnLabel(1), f);
		 	    list.add(columns);
		 	}
			rs.close();
			stmt.close();
			conn.close();
	   }catch (SQLException e){
		   e.printStackTrace();
	   }finally{
		   if(conn != null){
			   try{
				   conn.close();
			   }catch(SQLException e){
			   }
		   }
	   }
	   return list;
	}
	
	
	

}
