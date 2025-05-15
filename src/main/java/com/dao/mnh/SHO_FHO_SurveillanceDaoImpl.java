package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.persistance.util.HibernateUtil;




public class SHO_FHO_SurveillanceDaoImpl implements SHO_FHO_SurveillanceDao {



	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

//	 CAPTURE SURV DETAILS	
	 public ArrayList<ArrayList<String>> search_Surve(String sus,String date_from,String date_to,String type) {

			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				String sql1 = "";
				String whr = null;
				
				if(sus != ""  && sus != null && sus !="null" ) {	
					if(!sus.equals("all")){
						whr = "m.sus_no =?";
					}
				}		
				if(date_from != "" && date_from !=null) {
					whr += " and cast(date_from as Date) = cast(? as Date)";
					}
				if(date_to !="" && date_to !=null) {
					whr += " and cast(date_to as Date) = cast(? as Date)";
					}
				if(type != "" && type !=null) {
					whr += " and m.type =?";
					}
				
				String sql1Count = "select  count(m.id) from  tb_med_surv_master s  left join tb_med_surveillance_data m on s.id = m.surv_master_id \r\n" + 
									" where "+whr;
				
				stmt=conn.prepareStatement(sql1Count);
				int j = 1;
				
				if(sus != ""  && sus != null && sus !="null" ) {
					stmt.setString(j, sus);
					j += 1;
					}
				if(date_from != "" && date_from !=null) {
					stmt.setString(j, date_from);
					j += 1;
					}
				if(date_to !="" && date_to !=null) {
					stmt.setString(j, date_to);
					j += 1;
					}
				if(type != "" && type !=null) {
					stmt.setString(j, type);
					}
				
				ResultSet rs1 = stmt.executeQuery();
				ResultSetMetaData metaData = rs1.getMetaData();
				int i =0;
				int count =0;
				while (rs1.next()) {
					count = rs1.getInt("count");
					}
				if(count == 0)
				{
					String sql1_data_ser = "select id,target_diseases,target_diseases_sub,investigation from tb_med_surv_master order by target_diseases ";
					
					stmt1=conn.prepareStatement(sql1_data_ser);
					ResultSet rs2 = stmt1.executeQuery();
					ResultSetMetaData metaData1 = rs2.getMetaData();
					while (rs2.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						
						list.add(rs2.getString("target_diseases"));//0
						list.add(rs2.getString("target_diseases_sub"));
						list.add(rs2.getString("investigation"));
					    
						String test_self ="<input type='text' class='input_control'  style='width:60px;' id = 'test_self" + i + "' name='test_self" + i + "' value='0' onkeypress='return isNumberKey(event, this);'>" ; 
						String test_wife ="<input type='text' class='input_control' style='width:60px;' id = 'test_wife" + i + "' name='test_wife" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String test_husband ="<input type='text' class='input_control'style='width:60px;' id = 'test_husband" + i + "' name='test_husband" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String test_child_less ="<input type='text' style='width:60px;' class='input_control' id = 'test_child_less" + i + "' name='test_child_less" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String test_child_greater ="<input type='text' class='input_control'style='width:60px;' id = 'test_child_greater" + i + "' name='test_child_greater" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String test_mother ="<input type='text' class='input_control'style='width:60px;' id = 'test_mother" + i + "' name='test_mother" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String test_father ="<input type='text' class='input_control' style='width:60px;'id = 'test_father" + i + "' name='test_father" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String test_sister ="<input type='text' class='input_control' style='width:60px;' id = 'test_sister" + i + "' name='test_sister" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String test_brother ="<input type='text' class='input_control' style='width:60px;'id = 'test_brother" + i + "' name='test_brother" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_self ="<input type='text' class='input_control' style='width:60px;'id = 'positive_self" + i + "' name='positive_self" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_wife ="<input type='text' class='input_control' style='width:60px;'id = 'positive_wife" + i + "' name='positive_wife" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_husband ="<input type='text' class='input_control' style='width:60px;' id = 'positive_husband" + i + "' name='positive_husband" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_child_less ="<input type='text' class='input_control' style='width:60px;' id = 'positive_child_less" + i + "' name='positive_child_less" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_child_greater ="<input type='text' class='input_control' style='width:60px;' id = 'positive_child_greater" + i + "' name='positive_child_greater" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_mother ="<input type='text' class='input_control' style='width:60px;' id = 'positive_mother" + i + "' name='positive_mother" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_father ="<input type='text' class='input_control' style='width:60px;' id = 'positive_father" + i + "' name='positive_father" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_sister ="<input type='text' class='input_control' style='width:60px;'id = 'positive_sister" + i + "' name='positive_sister" + i + "' value='0'  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_brother ="<input type='text' class='input_control' style='width:60px;'id = 'positive_brother" + i + "' name='positive_brother" + i + "' value='0' onkeypress='return isNumberKey(event, this);' >" ; 
						String hd_id ="<input type='hidden' class='hd_id' id = 'hd_id" + i + "' name='hd_id" + i + "' value=" +rs2.getInt("id")+ " >" ; 
						String action_id ="<input type='hidden' class='action' id = 'action" + i + "' name='action" + i + "' value='0' >" ; 

						list.add(test_self);
					    list.add(test_wife);
					    list.add(test_husband);
					    list.add(test_child_less);
					    list.add(test_child_greater);
					    list.add(test_mother);
					    list.add(test_father);
					    list.add(test_sister);
					    list.add(test_brother);
					    list.add(positive_self);
					    list.add(positive_wife);
					    list.add(positive_husband);
					    list.add(positive_child_less);
					    list.add(positive_child_greater);
					    list.add(positive_mother);
					    list.add(positive_father);
					    list.add(positive_sister);
					    list.add(positive_brother);
						list.add(hd_id);
						list.add(action_id);
						alist.add(list);
						}
					}
				if(count > 0)
				{
					String sql1_data_ser = "select s.id as sur_mast_id,m.id,m.test_self,m.test_wife,m.test_husband,m.test_child_less,m.test_child_greater,m.test_mother,m.test_father,m.test_sister,m.test_brother,m.positive_self,m.positive_wife,m.positive_husband,m.positive_child_less,m.positive_child_greater,m.positive_mother,m.positive_father,m.positive_sister,m.positive_brother,s.target_diseases,s.target_diseases_sub,s.investigation "
										 + "from  tb_med_surv_master s  left join tb_med_surveillance_data m on s.id = m.surv_master_id \r\n"
								         + "where "+whr+" order by s.target_diseases";
					
					
					stmt1=conn.prepareStatement(sql1_data_ser);
					
					int j1 = 1;
					
					if(sus != ""  && sus != null && sus !="null" ) {
						stmt1.setString(j1, sus);
						j1 += 1;
						}
					if(date_from != "" && date_from !=null) {
						stmt1.setString(j1, date_from);
						j1 += 1;
						}
					if(date_to !="" && date_to !=null) {
						stmt1.setString(j1, date_to);
						j1 += 1;
						}
					if(type != "" && type !=null) {
						stmt1.setString(j1, type);
						}
					
					ResultSet rs2 = stmt1.executeQuery();
					ResultSetMetaData metaData1 = rs2.getMetaData();
					
					while (rs2.next()) {
						i++;
						ArrayList<String> list = new ArrayList<String>();
						
						list.add(rs2.getString("target_diseases"));//0
						list.add(rs2.getString("target_diseases_sub"));
						list.add(rs2.getString("investigation"));
						
						String test_self ="<input type='text' class='input_control' style='width:35px;' id ='test_self" + i + "' name='test_self" + i + "' value=" +rs2.getInt("test_self")+ "  onkeypress='return isNumberKey(event, this);' >" ; 
						String test_wife ="<input type='text' class='input_control' style='width:35px;' id = 'test_wife" + i + "' name='test_wife" + i + "' value=" +rs2.getInt("test_wife")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_husband ="<input type='text' class='input_control' style='width:35px;' id = 'test_husband" + i + "' name='test_husband" + i + "' value=" +rs2.getInt("test_husband")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_child_less ="<input type='text' class='input_control'style='width:35px;' id = 'test_child_less" + i + "' name='test_child_less" + i + "' value=" +rs2.getInt("test_child_less")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_child_greater ="<input type='text' class='input_control' style='width:35px;' id = 'test_child_greater" + i + "' name='test_child_greater" + i + "' value=" +rs2.getInt("test_child_greater")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_mother ="<input type='text' class='input_control'  style='width:35px;' id = 'test_mother" + i + "' name='test_mother" + i + "' value=" +rs2.getInt("test_mother")+ "  onkeypress='return isNumberKey(event, this);'>" ; 
						String test_father ="<input type='text' class='input_control' style='width:35px;' id = 'test_father" + i + "' name='test_father" + i + "' value=" +rs2.getInt("test_father")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_sister ="<input type='text' class='input_control' style='width:35px;' id = 'test_sister" + i + "' name='test_sister" + i + "' value=" +rs2.getInt("test_sister")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String test_brother ="<input type='text' class='input_control' style='width:35px;' id = 'test_brother" + i + "' name='test_brother" + i + "' value=" +rs2.getInt("test_brother")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_self ="<input type='text' class='input_control' style='width:35px;' id = 'positive_self" + i + "' name='positive_self" + i + "' value=" +rs2.getInt("positive_self")+ "  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_wife ="<input type='text' class='input_control'  style='width:35px;' id = 'positive_wife" + i + "' name='positive_wife" + i + "' value=" +rs2.getInt("positive_wife")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_husband ="<input type='text' class='input_control' style='width:35px;' id = 'positive_husband" + i + "' name='positive_husband" + i + "' value=" +rs2.getInt("positive_husband")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_child_less ="<input type='text' class='input_control' style='width:35px;' id = 'positive_child_less" + i + "' name='positive_child_less" + i + "' value=" +rs2.getInt("positive_child_less")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_child_greater ="<input type='text' class='input_control'style='width:35px;'  id = 'positive_child_greater" + i + "' name='positive_child_greater" + i + "' value=" +rs2.getInt("positive_child_greater")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_mother ="<input type='text' class='input_control' style='width:35px;' id = 'positive_mother" + i + "' name='positive_mother" + i + "' value=" +rs2.getInt("positive_mother")+ "  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_father ="<input type='text' class='input_control' style='width:35px;' id = 'positive_father" + i + "' name='positive_father" + i + "' value=" +rs2.getInt("positive_father")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String positive_sister ="<input type='text' class='input_control'style='width:35px;' id = 'positive_sister" + i + "' name='positive_sister" + i + "' value=" +rs2.getInt("positive_sister")+ "  onkeypress='return isNumberKey(event, this);'>" ; 
						String positive_brother ="<input type='text' class='input_control' style='width:35px;' id = 'positive_brother" + i + "' name='positive_brother" + i + "' value=" +rs2.getInt("positive_brother")+ " onkeypress='return isNumberKey(event, this);' >" ; 
						String hd_id ="<input type='hidden' class='hd_id' id = 'hd_id" + i + "' name='hd_id" + i + "' value=" +rs2.getInt("sur_mast_id")+ " >" ; 
						String action_id ="<input type='hidden' class='action' id = 'action" + i + "' name='action" + i + "' value=" +rs2.getInt("id")+ " >" ; 

						
						list.add(test_self);//9
					    list.add(test_wife);
					    list.add(test_husband);
					    list.add(test_child_less);
					    list.add(test_child_greater);
					    list.add(test_mother);
					    list.add(test_father);
					    list.add(test_sister);
					    list.add(test_brother);
					    list.add(positive_self);
					    list.add(positive_wife);
					    list.add(positive_husband);
					    list.add(positive_child_less);
					    list.add(positive_child_greater);
					    list.add(positive_mother);
					    list.add(positive_father);
					    list.add(positive_sister);
					    list.add(positive_brother);
					    list.add(hd_id);
					    list.add(action_id);
					    alist.add(list);
					    }
					}
				rs1.close();
				stmt.close();
				conn.close();
				}
			catch (SQLException e){
				e.printStackTrace();
				}
			finally
			{
				if(conn != null)
				{
					try{
						conn.close();
						}catch (SQLException e){
						 }
			    }
			}
			return alist;
			}
				
			
	
}
