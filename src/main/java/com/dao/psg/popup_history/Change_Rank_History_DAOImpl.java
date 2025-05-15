package com.dao.psg.popup_history;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.persistance.util.HibernateUtil;
import com.models.psg.update_census_data.TB_CHANGE_OF_RANK;


public class Change_Rank_History_DAOImpl implements Change_Rank_History_DAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	
	public ArrayList<ArrayList<String>> change_rank(BigInteger comm_id,int census_id)
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
			
					
			try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
							
					q="select r.authority,"
							+ "ltrim(TO_CHAR(r.date_of_authority  ,'DD-MON-YYYY'),'0') as date_of_authority,"
							+ "t.label as rank_type,"
							+ "ltrim(TO_CHAR(r.date_of_rank ,'DD-MON-YYYY'),'0')as date_of_rank,"
							+ "ra.description as rank,"
							+ "r.created_by,"
							+ "ltrim(TO_CHAR(r.created_date,'DD-MON-YYYY'),'0') as created_date ,r.modified_by,"
							+ "ltrim(TO_CHAR(r.modified_date,'DD-MON-YYYY'),'0') as modified_date "
							+ "from tb_psg_change_of_rank r"
							+ " inner join cue_tb_psg_rank_app_master ra on ra.id = r.rank and ra.status_active = 'Active'"
							+ " left join t_domain_value t on t.codevalue = cast(r.rank_type as char) and t.domainid='OFFR_RANK_TYPE'"
							+ " where ( r.status='1' or r.status='2' ) "
							+ "and  cast(r.comm_id as character varying)=? order by r.id";
				
				
				stmt=conn.prepareStatement(q);
				
				
				stmt.setString(1,String.valueOf(comm_id));
				
				System.err.println("query for rank history:  \n "+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("authority"));//1
					list.add(rs.getString("date_of_authority"));//2
					list.add(rs.getString("rank_type"));//3
					list.add(rs.getString("date_of_rank"));//4
					list.add(rs.getString("created_by"));//5
					list.add(rs.getString("created_date"));//6
					list.add(rs.getString("modified_by"));//7
					list.add(rs.getString("modified_date"));//8
					list.add(rs.getString("rank"));//9
					
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
					} catch (SQLException e) {}
				}
			}
			
			return alist;
      }
}	
