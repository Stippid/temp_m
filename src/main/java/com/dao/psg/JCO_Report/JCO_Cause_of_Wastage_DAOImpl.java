package com.dao.psg.JCO_Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class JCO_Cause_of_Wastage_DAOImpl implements JCO_Cause_of_wastage_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	  public ArrayList<ArrayList<String>> getCause_of_wastage_jco()
	  {
		  
	  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	
	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  			  		
	  		q = "select  distinct\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Death') as Death,\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Medical Ground (Invalided Out)') as Medical_Ground,\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Voluntary Retirement') as Voluntary_Retirement,\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Commissioned as Officer') as Commissioned_as_Officer,\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Resigned') as Resigned,\r\n" + 
	  				"   count(*) filter (where d.causes_name = 'Death' or d.causes_name = 'Medical Ground (Invalided Out)' or d.causes_name = 'Voluntary Retirement' \r\n" + 
	  				"				     or d.causes_name = 'Commissioned as Officer' or d.causes_name = 'Resigned') as total\r\n" + 
	  				"   from tb_psg_non_effective_jco n \r\n" + 
	  				"   inner join tb_psg_census_jco_or_p p on p.id=n.jco_id and p.status in ('4') \r\n" + 
	  				"   inner join tb_psg_mstr_cause_of_non_effective_jco d on d.id::text=n.cause_of_non_effective and d.status = 'active' \r\n" + 
	  				"   where n.status = 1";
	  			stmt=conn.prepareStatement(q);
	  			System.err.println("query for wastage \n" + stmt);
	  			ResultSet rs = stmt.executeQuery();   
	  			int i =1;  
	  			while (rs.next()) {
	  				
	  				ArrayList<String> list = new ArrayList<String>();
	  				list.add(rs.getString("Death"));//0
	  				list.add(rs.getString("Medical_Ground"));//1
	  				list.add(rs.getString("Voluntary_Retirement"));//2
	  				list.add(rs.getString("Commissioned_as_Officer"));//3
	  				list.add(rs.getString("Resigned"));//4
	  				list.add(rs.getString("total"));//5
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
