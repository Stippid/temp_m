package com.dao.psg.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.models.psg.Master.TB_INSTITUTE;
import com.persistance.util.HibernateUtil;


public class InstituteDAOimpl implements InstituteDAO {
private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

	public ArrayList<ArrayList<String>> search_Institute (String institute_name,String status)

	{

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;

		String q="";
		String qry="";

		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;

				if( !institute_name.equals("")) {

					qry += " and upper(il.institute_name) like ?";

				}
				
				if (!status.equals("0") ) {
					qry += " and il.status = ?";
				}

					 q="select  il.id ,il.institute_name,il.institute_type,string_agg( distinct cast( ins.id as text),',') as ch_id, string_agg(distinct bn.battalion_name,',') as bn_name,\r\n" + 
					 		"string_agg(distinct co.company_name,',') as co_name,string_agg(pl.platoon_name,',') as pl_name, \r\n" + 
					 		"			string_agg(distinct cast(bn.id as text),',') as bn_id	,string_agg(distinct cast(bn.id as text) || '_' ||  cast(co.id as text),',') as co_id,\r\n" + 
					 		"			string_agg('pla_' ||cast(pl.id as text),',') as pl_id,m.label as status\r\n" + 
					 		"			from tb_psg_mstr_institute_list il \r\n" + 
					 		"					 		left join tb_psg_mstr_institute ins on il.id=ins.institute_id \r\n" + 
					 		"					 		left join tb_psg_mstr_battalion bn on bn.id=ins.bn_id and bn.status='active'\r\n" + 
					 		"					 		left join tb_psg_mstr_company co on co.id=ins.company_id and co.status='active' \r\n" + 
					 		"					 		left join tb_psg_mstr_platoon pl on pl.id=ins.platoon_id \r\n" + 
					 		"							left join t_domain_value m on m.codevalue=cast(il.status as text) and m.domainid='STATUS_MSTR' \r\n" + 
					 		"					 		where il.id!=0 "+ qry +"group by il.id,il.institute_name,m.label order by il.id desc"; 


				stmt=conn.prepareStatement(q);

				if(!qry.equals(""))     

				{  int j =1;

					if( !institute_name.equals("")) {

						stmt.setString(j, institute_name.toUpperCase()+"%");

						j += 1;	
					}	
					
					if (!status.equals("0") ) {
						stmt.setString(j, status);
						j++;
					}

				}

		      ResultSet rs = stmt.executeQuery();      

      while (rs.next()) {

		    	  ArrayList<String> list = new ArrayList<String>();		    	    


					list.add(rs.getString("institute_name"));

					if(rs.getString("bn_name")!=null)
						list.add(rs.getString("bn_name"));
					else
						list.add("--");
					if(rs.getString("co_name")!=null)

						list.add(rs.getString("co_name"));

					else

						list.add("--");

					if(rs.getString("pl_name")!=null)

						list.add(rs.getString("pl_name"));

					else

						list.add("--");

					String bat_id="";
					String com_id="";
					String pla_id="";

					if(rs.getString("bn_id")!=null)
						bat_id=rs.getString("bn_id");

					if(rs.getString("co_id")!=null)	
						com_id=rs.getString("co_id");

					if(rs.getString("pl_id")!=null)

						pla_id=rs.getString("pl_id");

					String f = "";
					String f1 = "";

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Institute Detail ?') ){editData("+ rs.getInt("id") + ",'" + rs.getString("institute_name") + "','" +bat_id+ "','" +com_id+ "','" +pla_id+ "','" +rs.getString("institute_type") + "')}else{ return false;}\"";

					 f = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Institute Detail ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
					 f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					 if(status.equals("inactive"))
						{
							
							list.add("");
							list.add("");

						}
						else {
						list.add(f);
						list.add(f1);
						}
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

	public TB_INSTITUTE getInstituteByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			TB_INSTITUTE updateid = (TB_INSTITUTE) sessionHQL.get(TB_INSTITUTE.class, id);

			tx.commit();

			return updateid;
		} catch (RuntimeException e) {

			return null;

		} finally {
			if (sessionHQL != null) {

				sessionHQL.close();
			}
		}	
	}
	
	public ArrayList<ArrayList<String>> search_InstituteReport()
	{
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";

		try{	  
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;

					 q="select  il.id ,il.institute_name,il.institute_type,string_agg( distinct cast( ins.id as text),',') as ch_id, string_agg(distinct bn.battalion_name,',') as bn_name,\r\n" + 
					 		"string_agg(distinct co.company_name,',') as co_name,string_agg(pl.platoon_name,',') as pl_name, \r\n" + 
					 		"			string_agg(distinct cast(bn.id as text),',') as bn_id	,string_agg(distinct cast(bn.id as text) || '_' ||  cast(co.id as text),',') as co_id,\r\n" + 
					 		"			string_agg('pla_' ||cast(pl.id as text),',') as pl_id,m.label as status\r\n" + 
					 		"			from tb_psg_mstr_institute_list il \r\n" + 
					 		"					 		left join tb_psg_mstr_institute ins on il.id=ins.institute_id \r\n" + 
					 		"					 		left join tb_psg_mstr_battalion bn on bn.id=ins.bn_id and bn.status='active'\r\n" + 
					 		"					 		left join tb_psg_mstr_company co on co.id=ins.company_id and co.status='active' \r\n" + 
					 		"					 		left join tb_psg_mstr_platoon pl on pl.id=ins.platoon_id \r\n" + 
					 		"							left join t_domain_value m on m.codevalue=cast(il.status as text) and m.domainid='STATUS_MSTR' \r\n" + 
					 		"					 		where il.id!=0 group by il.id,il.institute_name,m.label order by il.id desc"; 


				stmt=conn.prepareStatement(q);

		      ResultSet rs = stmt.executeQuery();      
int i = 1;
      while (rs.next()) {

		    	  ArrayList<String> list = new ArrayList<String>();		    	    
		    	  String id = String.valueOf(i++);
		    	  list.add(id);
					list.add(rs.getString("institute_name"));

					if(rs.getString("bn_name")!=null)
						list.add(rs.getString("bn_name"));
					else
						list.add("--");
					if(rs.getString("co_name")!=null)
						list.add(rs.getString("co_name"));
					else
						list.add("--");
//					if(rs.getString("pl_name")!=null)
//						list.add(rs.getString("pl_name"));
//					else
//						list.add("--");

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







