package com.dao.mnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

public class FHO_SHO_PreventiveDAOImpl implements FHO_SHO_PreventiveDAO {

	
	
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<ArrayList<String>> getmalariyadeatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select unit_name,bt,mt,fl,fi,id from tb_med_ch_malaria where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String fl = "";
			String mt = "";
			String bt = "";
			String fi = "";
			String unit_name = "";
			String mala_total="";
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_malaria_id" + i + "' name='c_malaria_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
		unit_name = "<input type='hidden' id = 'mal_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'mal_unit_name" + i + "' name='unit_name" + i + "' maxlength='40' onkeypress='malUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		bt = "<input type='text' id = 'bt" + i + "' name='bt" + i + "' maxlength='40' value = '" + rs.getString("bt") + "' class='form-control-sm form-control' onchange='myFunctionMAl("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);' >";
		mt = "<input type='text' id = 'mt" + i + "' name='mt" + i + "' maxlength='40' value = '" + rs.getString("mt") + "' class='form-control-sm form-control' onchange='myFunctionMAl("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		fl = "<input type='text' id = 'fl" + i + "' name='fl" + i + "' maxlength='40' value = '" + rs.getString("fl") + "' class='form-control-sm form-control' onchange='myFunctionMAl("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		fi = "<input type='text' id = 'fi" + i + "' name='fi" + i + "' maxlength='40' value = '" + rs.getString("fi") + "' class='form-control-sm form-control' onchange='myFunctionMAl("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		
		int tot = rs.getInt("bt")+rs.getInt("mt")+rs.getInt("fl")+rs.getInt("fi");
		
		mala_total = "<input type='text' id = 'mala_total" + i + "' name='mala_total" + i + "' value = '" + tot + "' maxlength='40' class='form-control-sm form-control' readonly='readonly'  autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);' >";

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='malariaformopen("+i+");' id='malaria_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(unit_name);//0
				list.add(bt); // 1
				list.add(mt);// 2
				list.add(fl); // 3
				list.add(fi);// 4
				list.add(mala_total);// 5
				list.add(hdid); // 6

				list.add(add_more); //7
				
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
				}
			}
		}
		return alist;
	}
	
	
	public ArrayList<ArrayList<String>> getSTDdeatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select unit_name,fl,fi,id from tb_med_ch_std where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String fl = "";
		
			String fi = "";
			String unit_name = "";
			String mala_total="";
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_std_id" + i + "' name='c_std_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
		unit_name = "<input type='hidden' id = 'std_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'std_unit_name" + i + "' name='unit_name" + i + "' maxlength='40' onkeypress='stdUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		fl = "<input type='text' id = 's_fl" + i + "' name='s_fl" + i + "' maxlength='40' value = '" + rs.getString("fl") + "' class='form-control-sm form-control' onchange='myFunctionSTD("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		fi = "<input type='text' id = 's_fi" + i + "' name='s_fi" + i + "' maxlength='40' value = '" + rs.getString("fi") + "' class='form-control-sm form-control' onchange='myFunctionSTD("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		
		int tot = rs.getInt("fl")+rs.getInt("fi");
		
		mala_total = "<input type='text' id = 'std_total" + i + "' name='std_total" + i + "' value = '" + tot + "' maxlength='40' class='form-control-sm form-control' readonly='readonly'  autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);' >";

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='stdformopen("+i+");' id='std_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(unit_name);//0
			
				list.add(fl); // 3
				list.add(fi);// 4
				list.add(mala_total);// 5
				list.add(hdid); // 6

				list.add(add_more); //7
				
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
				}
			}
		}
		return alist;
	}
	public ArrayList<ArrayList<String>> get_Viral_Hepatitis_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select unit_name,fl,fi,id from tb_med_ch_viral_hepatitis where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String fl = "";
		
			String fi = "";
			String unit_name = "";
			String mala_total="";
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_viral_hepatitis_id" + i + "' name='c_viral_hepatitis_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
		unit_name = "<input type='hidden' id = 'viral_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'viral_unit_name" + i + "' name='unit_name" + i + "' maxlength='40' onkeypress='viralUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		fl = "<input type='text' id = 'h_fl" + i + "' name='h_fl" + i + "' maxlength='40' value = '" + rs.getString("fl") + "' class='form-control-sm form-control' onchange='myFunctionHEPA("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		fi = "<input type='text' id = 'h_fi" + i + "' name='h_fi" + i + "' maxlength='40' value = '" + rs.getString("fi") + "' class='form-control-sm form-control' onchange='myFunctionHEPA("+i+")'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		
		int tot = rs.getInt("fl")+rs.getInt("fi");
		
		mala_total = "<input type='text' id = 'hepa_total" + i + "' name='hepa_total" + i + "' value = '" + tot + "' maxlength='40' class='form-control-sm form-control' readonly='readonly'  autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);' >";

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='hepatitsformopen("+i+");' id='hepatits_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(unit_name);//0
			
				list.add(fl); // 3
				list.add(fi);// 4
				list.add(mala_total);// 5
				list.add(hdid); // 6

				list.add(add_more); //7
				
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
				}
			}
		}
		return alist;
	}
	
	public ArrayList<ArrayList<String>> get_h1n1_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select unit_name,no_of_cases,id from tb_med_ch_h1n1_influenza where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String no_of_cases="";
			String unit_name = "";
			
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_h1n1_id" + i + "' name='c_h1n1_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
		unit_name = "<input type='hidden' id = 'h1n1_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'h1n1_unit_name" + i + "' name='unit_name" + i + "' maxlength='40' onkeypress='h1n1UnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		no_of_cases = "<input type='text' id = 'no_cases" + i + "' name='no_cases" + i + "' maxlength='40' value = '" + rs.getString("no_of_cases") + "' class='form-control-sm form-control'   autocomplete='off' placeholder='0' onkeypress='return isNumber0_9Only(event);'>";
		
		
		

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='h1n1formopen("+i+");' id='h1n1_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(unit_name);//0
			
				list.add(no_of_cases); // 3
			
			
				list.add(hdid); // 6

				list.add(add_more); //7
				
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
				}
			}
		}
		return alist;
	}
	
	
	public ArrayList<ArrayList<String>> get_injuries_nea_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select unit_name,diagnosis,mode_of_injuries,id from tb_med_ch_injuries_nea where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String diagnosis="";
			String unit_name = "";
			String mode_of_injuries="";
			
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_nea_id" + i + "' name='c_nea_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
		unit_name = "<input type='hidden' id = 'inj_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'inj_unit_name" + i + "' name='unit_name" + i + "' maxlength='40'  onkeypress='injUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		diagnosis = "<input type='text' id = 'diagnosis" + i + "' name='diagnosis" + i + "' maxlength='40' value = '" + rs.getString("diagnosis") + "' class='form-control-sm form-control'   autocomplete='off' placeholder='0' >";
		mode_of_injuries="<select  id = 'mode_of_injury" + i + "' class='form-control-sm form-control' autocomplete='off' name='mode_of_injury" + i + "'> </select>";
		
		

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='injuriesformopen("+i+");' id='injuries_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(unit_name);//0
			
				list.add(diagnosis); // 3
				
				list.add(mode_of_injuries);
				
				list.add(hdid); // 6

				list.add(add_more); //7
				list.add(rs.getString("mode_of_injuries"));
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
				}
			}
		}
		return alist;
	}
	
	
	public ArrayList<ArrayList<String>> get_pulmonary_tuberculosis_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select personnel_no,category,rank,personnel_name,unit_name,id from tb_med_ch_pulmonary_tuberculosis where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String pers_name="";
			String unit_name = "";
			String category="";
			String rank="";
			String pers1="";
			String pers2="";
			String pers3="";
		
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_pul_tub_id" + i + "' name='c_pul_tub_id" + i + "' value = '" + rs.getString("id")
				+ "' size='1' style='width:100%;'>"; 
		
				String p_no=rs.getString("personnel_no");
				String p_no1=p_no.substring(0, 2);
				String p_no3=p_no.substring(p_no.length()-1);
				String p_no2=p_no.substring(2,p_no.length()-1);

				
		unit_name = "<input type='hidden' id = 'pul_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'pul_unit_name" + i + "' name='unit_name" + i + "' maxlength='40' onkeypress='pulUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
		pers1="<select  id = 'personnel_number1" + i + "' class='form-control-sm form-control' autocomplete='off' name='personnel_number1" + i + "' onchange='per_nochange("+i+")'> </select>";
		pers3="<select  id = 'personnel_number3" + i + "' class='form-control-sm form-control' autocomplete='off' name='personnel_number3" + i + "'> </select>";
		pers2 = "<input type='text' id = 'personnel_number2" + i + "' name='personnel_number2" + i + "' maxlength='40' value = '" +p_no2 + "' class='form-control-sm form-control'   autocomplete='off' placeholder='Enter No...'  maxlength='10' >";

		category="<select  id = 'category" + i + "' class='form-control-sm form-control' autocomplete='off' name='category" + i + "'> </select>";
		rank="<select  id = 'rank" + i + "' class='form-control-sm form-control' autocomplete='off' name='rank" + i + "'> </select>";

		pers_name = "<input type='text' id = 'personnel_name" + i + "' name='personnel_name" + i + "' maxlength='40' value = '" + rs.getString("personnel_name") + "' class='form-control-sm form-control'   autocomplete='off' placeholder='0' >";

		

				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='formopen("+i+");' id='id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(pers1);//0
			
				list.add(pers2); // 3
				list.add(pers3);
				list.add(category);
				list.add(rank);
				list.add(pers_name);
				list.add(unit_name);
				
				list.add(hdid); // 6

				list.add(add_more); //7
				list.add(p_no1);
				list.add(p_no3);
				list.add(rs.getString("category"));
				list.add(rs.getString("rank"));
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
				}
			}
		}
		return alist;
	}
	
	
	
	public ArrayList<ArrayList<String>> get_milkquality_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select sample_number,specific_gravity,fat_content,total_solids,id from Tb_Med_Ch_milk_quality where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String sample_number="";
			String specific_gravity = "";
			String fat_content="";
			String total_solids="";
			String hdid = "";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_milk_id" + i + "' name='c_milk_id" + i + "' value = '" + rs.getString("id")+"' size='1' style='width:100%;'>"; 
		
				sample_number = "<input type='text' id = 'milk_samplenum" + i + "' name='milk_samplenum" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("sample_number") + "'>";
				specific_gravity="<select id='milk_spec_gravity"+i+"' name='milk_spec_gravity"+i+"' class='form-control-sm form-control' ><option value=''>--Select--</option><option value='1'>Satisfactory</option><option value='2'>Non Satisfactory</option></select>";
				total_solids="<select id='milk_tot_solid"+i+"' name='milk_tot_solid"+i+"' class='form-control-sm form-control' ><option value=''>--Select--</option><option value='1'>Satisfactory</option><option value='2'>Non Satisfactory</option></select>";
				fat_content="<select id='milk_fat_con"+i+"' name='milk_fat_con"+i+"' class='form-control-sm form-control' ><option value=''>--Select--</option><option value='1'>Satisfactory</option><option value='2'>Non Satisfactory</option></select>";


				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='milkformopen("+i+");' id='milk_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(sample_number);//0
			
				list.add(specific_gravity); // 3
				list.add(fat_content);
				list.add(total_solids);
				list.add(hdid); // 6

				list.add(add_more); //7
				list.add(rs.getString("specific_gravity"));
				list.add(rs.getString("fat_content"));
				list.add(rs.getString("total_solids"));
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
				}
			}
		}
		return alist;
	}
	
	
	public ArrayList<ArrayList<String>> gethealthedu_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select topic,unit_name,personal_category,number_present,remarks,photographs,id from tb_med_ch_health_education where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String topic="";
			String unit_name = "";
			String personal_category="";
			String number_present="";
			String remarks="";
			String photographs="";
			String hdid = "";
			String view="";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_health_id" + i + "' name='c_health_id" + i + "' value = '" + rs.getString("id")+"' size='1' style='width:100%;'>"; 
		
				unit_name = "<input type='hidden' id = 'health_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'health_unit_name" + i + "' name='health_unit_name" + i + "' maxlength='40' onkeypress='healthUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
				personal_category="<select id='h_personnel_category"+i+"' name='h_personnel_category"+i+"' class='form-control-sm form-control' ></select>";
				topic = "<input type='text' id = 'health_topic" + i + "' name='health_topic" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("topic") + "'>";
				number_present = "<input type='text' id = 'h_number_present" + i + "' name='h_number_present" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("number_present") + "' onkeypress='return isNumber0_9Only(event);'>";
				photographs = "	<input type='file'   id='hea_img"+i+"' name='hea_img"+i+"' class='img' onchange='PreviewImage("+i+");'>	<img style='display: none;' src='healthEduConvertpath?healtheduid="+rs.getString("id")+"' id='healthimagepreview"+i+"' style='width: 100px; height: 100px;' />";
				remarks = "<input type='text' id = 'h_remarks" + i + "' name='h_remarks" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("remarks") + "'>";


				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				view="<i class='fa fa-eye' onclick='healthimage("+i+");'></i>";
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='healthformopen("+i+");' id='health_id_add"+i+"' value='ADD'></i>"	;	}
			
				
				
				list.add(topic); // 6
				list.add(unit_name); // 6
				list.add(personal_category); // 6
				list.add(number_present); // 6
				list.add(remarks); // 6
				list.add(photographs); // 6
				list.add(hdid); // 6
				list.add(add_more); //7
				list.add(rs.getString("personal_category"));		
				list.add(view);
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
				}
			}
		}
		return alist;
	}
	
	public ArrayList<ArrayList<String>> getworkshop_deatails(int id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			sql = "select topic,unit_name,target_group,number_present,remarks,photographs,id from tb_med_ch_workshops where p_id=? ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);

			
			String topic="";
			String unit_name = "";
			String target_group="";
			String number_present="";	
			String remarks="";
			String photographs="";
			String hdid = "";
			String view="";

			int i = 0;
			ResultSet rs = stmt.executeQuery();
			String add_more = "";
			while (rs.next()) {

				i++;
				ArrayList<String> list = new ArrayList<String>();
				
				hdid = "<input type='hidden' id = 'c_workshop_id" + i + "' name='c_workshop_id" + i + "' value = '" + rs.getString("id")+"' size='1' style='width:100%;'>"; 
		
				unit_name = "<input type='hidden' id = 'workshop_sus_no" + i + "'  maxlength='40' class='form-control-sm form-control' autocomplete='off' '><input type='text' id = 'workshop_unit_name" + i + "' name='unit_name" + i + "' maxlength='40'  onkeypress='workshopUnitAuto(this);' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("unit_name") + "'>";
				target_group="<select id='target_group"+i+"' name='target_group"+i+"' class='form-control-sm form-control' ></select>";
				topic = "<input type='text' id = 'topic" + i + "' name='topic" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("topic") + "'>";
				number_present = "<input type='text' id = 'number_present" + i + "' name='number_present" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("number_present") + "' onkeypress='return isNumber0_9Only(event);'>";
				photographs = "	<input type='file'   id='work_img"+i+"' name='work_img"+i+"' class='img' onchange='workPreviewImage("+i+");'><img style='display: none;' src='workshopConvertpath?workid="+rs.getString("id")+"' id='workimagepreview"+i+"' style='width: 100px; height: 100px;' />";
				remarks = "<input type='text' id = 'remarks" + i + "' name='remarks" + i + "' maxlength='40' class='form-control-sm form-control' autocomplete='off' value = '" + rs.getString("remarks") + "'>";

			
				Date date = new Date();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);

				view="<i class='fa fa-eye' onclick='workimage("+i+");'></i>";
				if (rs.isLast()) {

					add_more = "<i class='fa fa-plus' onclick='workshopformopen("+i+");' id='workshop_id_add"+i+"' value='ADD'></i>"	;	}
				
				
				list.add(topic); // 6
				list.add(unit_name); // 6
				list.add(target_group); // 6
				list.add(number_present); // 6
				list.add(remarks); // 6
				list.add(photographs); // 6
				list.add(hdid); // 6
				list.add(add_more); //7
				list.add(rs.getString("target_group"));		
				list.add(view);
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
				}
			}
		}
		return alist;
	}

	@Override
	public ArrayList<ArrayList<String>> search_preventive_deatails(String sus_no,String month,String year) {
		
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			String q1="",q2="";
			PreparedStatement stmt = null;
			
			if(!month.equals("") || month != "") {
				q1+="and month like ?";
			}
			if(!year.equals("") || year != "") {
				q2+="and year = ?";
			}
			sql = "select id,sho_name,month,year FROM tb_med_pr_preventive_details where sus_no=?\n" + 
					q1+" "+q2+
					"ORDER BY id DESC ;";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int j=1;
			stmt.setString(j, sus_no);
			j++;
			if(!month.equals("") || month != "") {
				stmt.setString(j, month+"%");
				j++;
			}
			if(!year.equals("") || year != "") {
				stmt.setInt(j, Integer.parseInt(year));
				j++;
			}
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("sho_name"));//0
			
				list.add(rs.getString("month")); // 1
				list.add(rs.getString("year")); //2
				String f="";
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Location ?') ){deleteData(" + rs.getInt("id") + ")}else{ return false;}\"";
				f ="<i class='action_icons action_delete' "+Delete1+" title='Delete Data'></i>";
				list.add(f); //3
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
				}
			}
		}
		return alist;
	}

	@Override
	public String gethealthImagePath(String id) {
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select photographs,id from tb_med_ch_health_education where id=? ";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("photographs");             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}

	@Override
	public String getworkImagePath(String id) {
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select photographs,id from tb_med_ch_workshops where id=? ";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("photographs");             	
 	        }
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
}
