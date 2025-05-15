package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class Type_Of_Stock_ADAOImpl implements Type_Of_Stock_ADAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
    public  ArrayList<List<String>> getSearchStockA(String sus_no, String mct, String type_of_stock, String roleType) {
        ArrayList<List<String>> alist = new ArrayList<List<String>>();
   		Connection conn = null;
		 String q=null;
		 try{	  
		 conn = dataSource.getConnection();			 
			/*
			 * q =
			 * "select distinct b.ba_no as ba_no,b.engine_no as engine ,b.chasis_no as chasis,d.type_of_stock as type_of_stock,d.id as id,c.remarks as Remarks \r\n"
			 * + "from TB_TMS_CENSUS_DRR_DIR_DTL d \r\n" +
			 * "inner join TB_TMS_BANUM_DIRCTRY b on  b.ba_no=d.ba_no and cast(b.mct as character varying) = ? where d.sus_no=? and d.type_of_stock=? and d.ba_no not in (select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no=? and  type_of_stock in ('3','4','5'))\r\n"
			 * + "inner join Tms_Banum_Req_Child c on d.ba_no=c.ba_no";
			 */
			// query shivani 17-16-2023
			  q="select distinct b.ba_no as ba_no,b.engine_no as engine ,b.chasis_no as chasis,d.type_of_stock as type_of_stock,d.id as id ,ch.remarks\r\n"
			  		+ "from TB_TMS_CENSUS_DRR_DIR_DTL d \r\n"
			  		+ "inner join TB_TMS_BANUM_DIRCTRY b on  b.ba_no=d.ba_no and cast(b.mct as character varying) = ? \r\n"
			  		+ "inner join tb_tms_banum_req_child ch on ch.ba_no = b.ba_no\r\n"
			  		+ "where d.sus_no= ? and d.type_of_stock= ? \r\n"
			  		+ "and d.ba_no not in (select ba_no from TB_TMS_CENSUS_DRR_DIR_DTL where sus_no= ? and  type_of_stock in ('3','4','5'))\r\n";
			 
		   PreparedStatement stmt=conn.prepareStatement(q);
		   
		   stmt.setString(1,mct);
	       stmt.setString(2,sus_no);
	       stmt.setString(3,type_of_stock);
	       stmt.setString(4,sus_no);
	    
		   ResultSet rs = stmt.executeQuery();
		   
		   while(rs.next()){
	           
			   ArrayList<String> list = new ArrayList<String>();
			    list.add(rs.getString("ba_no"));
			    list.add(rs.getString("engine"));
			    list.add(rs.getString("chasis"));
			    list.add(rs.getString("type_of_stock"));
			    list.add(rs.getString("id"));
			    
			    
			    String type_of_stock1 = "<select id='type_of_stock"+rs.getString("id")+"'    name='type_of_stock"+rs.getString("id")+"'> "+     
			    		"<option value='0'>Free Stock</option>"+
						"<option value='1'>WWR</option>"+
						"<option value='2'>Issue Reserve</option>"+
						"</select>";
			    
				String Update = "onclick=\"  if (confirm('Are You Sure you want to Update  ?') ){update('"+rs.getString("id")+"')}else{ return false;}\"";
				
				String appButton = "<i class='action_icons action_approve' "+Update+" title='Approve Data'></i>";
			
				
				list.add(type_of_stock1); //5
				
			
				list.add(rs.getString("remarks"));//6
				 String f = "";
				
				 if(roleType.equals("ALL")) {
						f += appButton;
					}
					if(roleType.equals("APP")) {
						f += appButton;
					}
					if(roleType.equals("DEO")) {
						f += appButton;
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
    
}
