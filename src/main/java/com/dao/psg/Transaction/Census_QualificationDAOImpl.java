package com.dao.psg.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
public class Census_QualificationDAOImpl implements Census_QualificationDAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public List<Map<String, String>> getQualificationData(int cen_id,int status) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		String q="";
		String qry="";
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			 q = "select qua.id,qua.authority,qua.date_of_authority,qua.type,dom.label,qua.examination_pass,ep.examination_passed as exp_name,qua.degree,deg.degree as d_name,qua.specialization,spe.specialization as spce_name,qua.passing_year,\n" + 
		        		"qua.div_class,qua.institute,qua.subject,qua.exam_other,qua.degree_other,qua.specialization_other,qua.class_other,qua.reject_remarks from tb_psg_census_qualification qua\n" + 
		        		"inner join tb_psg_mstr_examination_passed ep on ep.id=qua.examination_pass\n" + 
		        		"inner join tb_psg_mstr_degree deg on deg.id=qua.degree\n" + 
		        		"left join tb_psg_mstr_specialization spe on spe.id=qua.specialization\n" + 
		        		"inner join t_domain_value dom on dom.codevalue=qua.type and dom.domainid='QUALIFICATION_TYPE'"
		        		+ "where qua.cen_id=? and qua.status=? order by qua.id";   
		        	
			
		    stmt=conn.prepareStatement(q);   	
		    stmt.setInt(1, cen_id);
		    stmt.setInt(2, status);
		    ResultSet rs = stmt.executeQuery();    
		   
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> columns = new LinkedHashMap<String, String>();
		 	    for (int i = 1; i <= columnCount; i++) {
		 	    	columns.put(metaData.getColumnLabel(i), rs.getString(i));
		 	    }
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
				 }catch (SQLException e){
				 }
			 }
		 }
		 return list;
		}
}
