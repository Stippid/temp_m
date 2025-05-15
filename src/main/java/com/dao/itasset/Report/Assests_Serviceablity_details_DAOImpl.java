package com.dao.itasset.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Assests_Serviceablity_details_DAOImpl implements Assests_Serviceablity_details_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	//1234 KAJAL
	@Override
	public ArrayList<ArrayList<String>> Search_Assets_Serviceablity_Details(String service_state,String asset_type,String unservice_state,HttpSession session) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q1 = "";
		String ususno=session.getAttribute("roleSusNo").toString();


		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (!ususno.equals("")) {
				q1 += "and  am.sus_no=? ";

			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";

			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(am.asset_type as character varying) = ? ";

			}


			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";

			}

			q1 += " and am.status= ? ";

			q = "select distinct am.id,am.asset_type,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "case when count(ch_am.*) >0 then ch_am.warranty else am.warranty end as warranty,\r\n"
					+ "case when count(ch_am.*) >0 then td.label else td1.label end  as service_state,\r\n"
					+ "case when count(ch_am.*) >0 then td2.label else td3.label end  as unservice_state\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_assets_child ch_am on am.id = ch_am.p_id and ch_am.status=0\r\n"
					+ "left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n"
					+ "left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n"
					+ "left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n"
					+ "left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n"
					+ "left join  tb_mstr_os om on  om.id=am.operating_system\r\n"
					+ "left join  tb_mstr_office ofm on  ofm.id=am.office\r\n"
					+ "left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"
					+ "left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n"
					+ "left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n"
					+ "left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "where    am.id!=0  " + q1 + "\r\n"
					+ "group by  am.id,ma.assets_name,am.asset_type,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "ch_am.warranty,ch_am.service_state,td.label,td1.label,td2.label,td3.label";

			stmt = conn.prepareStatement(q);

			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);

				j++;
			}
			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);

				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);

				j++;
			}

			if (service_state.equals("4") || unservice_state.equals("BER")) {
				stmt.setInt(j, 4);
				j++;
			} else {
				stmt.setInt(j, 1);
				j++;
			}


			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("model_number"));// 1
				list.add(rs.getString("machine_number"));// 2
				list.add(rs.getString("mac_address"));// 3
				list.add(rs.getString("ip_address"));// 4
				list.add(rs.getString("processor_type"));// 5
				list.add(rs.getString("ram"));// 6
				list.add(rs.getString("hdd"));// 7
				list.add(rs.getString("os"));// 8
				list.add(rs.getString("office"));// 9
				list.add(rs.getString("os_firmware"));// 10
				list.add(rs.getString("dply_env"));// 11
				list.add(rs.getString("id"));// 12

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
	public ArrayList<ArrayList<String>> pdf_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q1 = "";
		String ususno=session.getAttribute("roleSusNo").toString();
		try {
			if (!ususno.equals("")) {
				q1 += "and  am.sus_no=? ";

			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";

			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(am.asset_type as character varying) = ? ";

			}


			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";

			}


			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//			String ususno=session.getAttribute("roleSusNo").toString();
			q = "select distinct am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "case when count(ch_am.*) >0 then ch_am.warranty else am.warranty end as warranty,\r\n"
					+ "case when count(ch_am.*) >0 then td.label else td1.label end  as service_state,\r\n"
					+ "case when count(ch_am.*) >0 then td2.label else td3.label end  as unservice_state\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_assets_child ch_am on am.id = ch_am.p_id and ch_am.status=0\r\n"
					+ "left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n"
					+ "left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n"
					+ "left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n"
					+ "left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n"
					+ "left join  tb_mstr_os om on  om.id=am.operating_system\r\n"
					+ "left join  tb_mstr_office ofm on  ofm.id=am.office\r\n"
					+ "					left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"
					+ "					left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n"
					+ "					left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "					left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n"
					+ "					left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "					left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "					where  am.id!=0 and am.status= '1'  \r\n"+ q1
					+ "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "ch_am.warranty,ch_am.service_state,td.label,td1.label,td2.label,td3.label";

			stmt = conn.prepareStatement(q);
			//			stmt.setString(1, ususno);
			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);

				j++;
			}
			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);

				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);

				j++;
			}

			//			if (service_state.equals("4") || unservice_state.equals("BER")) {
			//				stmt.setInt(j, 4);
			//				j++;
			//			} else {
			//				stmt.setInt(j, 1);
			//				j++;
			//			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("model_number"));// 1
				list.add(rs.getString("machine_number"));// 2
				list.add(rs.getString("mac_address"));// 3
				list.add(rs.getString("ip_address"));// 4
				list.add(rs.getString("processor_type"));// 5
				list.add(rs.getString("ram"));// 6
				list.add(rs.getString("hdd"));// 7
				list.add(rs.getString("os"));// 8
				list.add(rs.getString("office"));// 9
				list.add(rs.getString("os_firmware"));// 10
				list.add(rs.getString("dply_env"));// 11
				list.add(rs.getString("id"));// 12
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
	public ArrayList<ArrayList<String>> Search_Assets_Peripherals_Details(String service_state,String asset_type,
			String unservice_state,HttpSession session) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String ususno=session.getAttribute("roleSusNo").toString();
		String q = "";
		String q1 = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;


			if (!ususno.equals("")) {
				q1 += "and  ap.sus_no = ? ";
			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";
			}

			if (!asset_type.equals("0") ) {
				q1 += "and  cast(ap.assets_type as character varying) = ? ";
			}

			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";
			}

			q1 += " and ap.status= ? ";

			q = "select ap.id,ap.assets_type,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n"
					+ "ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw \r\n"
					+ "left join t_domain_value td on  td.codevalue:: character varying = ap.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "left join t_domain_value td2 on  td2.codevalue:: character varying = ap.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' where  ap.id!=0  "
					+ q1 + "";

			stmt = conn.prepareStatement(q);

			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);
				j++;
			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);
				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);
				j++;
			}

			if (service_state.equals("4") || unservice_state.equals("BER")) {
				stmt.setInt(j, 4);
				j++;
			} else {
				stmt.setInt(j, 1);
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("type_of_hw"));// 1
				list.add(rs.getString("year_of_proc"));// 2
				list.add(rs.getString("year_of_manufacturing"));// 3
				list.add(rs.getString("b_cost"));// 4
				list.add(rs.getString("make_name"));// 5
				list.add(rs.getString("machine_no"));// 6
				list.add(rs.getString("model_no"));// 7
				list.add(rs.getString("remarks"));// 8
				list.add(rs.getString("id"));// 9

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
	public ArrayList<ArrayList<String>> pdf_Peripherals_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String ususno=session.getAttribute("roleSusNo").toString();
		String q = "";
		String q1 = "";

		try {

			if (!ususno.equals("")) {
				q1 += "and  ap.sus_no=? ";

			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";

			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(ap.assets_type as character varying) = ? ";

			}


			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";

			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ap.id,ap.assets_type,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n"
					+ "ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw \r\n"
					+ "left join t_domain_value td on  td.codevalue:: character varying = ap.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "left join t_domain_value td2 on  td2.codevalue:: character varying = ap.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' where ap.id!=0  and ap.status= '1' \r\n "+q1;

			stmt = conn.prepareStatement(q);
			//			stmt.setString(1, ususno);


			int j = 1;
			if (!ususno.equals("")) {
				stmt.setString(j, ususno);

				j++;
			}
			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);

				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);

				j++;
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("type_of_hw"));// 1
				list.add(rs.getString("year_of_proc"));// 2
				list.add(rs.getString("year_of_manufacturing"));// 3
				list.add(rs.getString("b_cost"));// 4
				list.add(rs.getString("make_name"));// 5
				list.add(rs.getString("machine_no"));// 6
				list.add(rs.getString("model_no"));// 7
				list.add(rs.getString("remarks"));// 8
				list.add(rs.getString("id"));// 9

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
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q1 = "";
		String ususno=session.getAttribute("roleSusNo").toString();
		try {
			if (!ususno.equals("")) {
				q1 += "and  am.sus_no=? ";

			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";

			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(am.asset_type as character varying) = ? ";

			}


			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";

			}


			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			//			String ususno=session.getAttribute("roleSusNo").toString();
			q = "select distinct am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "case when count(ch_am.*) >0 then ch_am.warranty else am.warranty end as warranty,\r\n"
					+ "case when count(ch_am.*) >0 then td.label else td1.label end  as service_state,\r\n"
					+ "case when count(ch_am.*) >0 then td2.label else td3.label end  as unservice_state\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_assets_child ch_am on am.id = ch_am.p_id and ch_am.status=0\r\n"
					+ "left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n"
					+ "left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n"
					+ "left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n"
					+ "left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n"
					+ "left join  tb_mstr_os om on  om.id=am.operating_system\r\n"
					+ "left join  tb_mstr_office ofm on  ofm.id=am.office\r\n"
					+ "					left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"
					+ "					left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n"
					+ "					left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "					left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n"
					+ "					left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "					left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n"
					+ "					where  am.id!=0 and am.status= '1'  \r\n"+ q1
					+ "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n"
					+ "ch_am.warranty,ch_am.service_state,td.label,td1.label,td2.label,td3.label";

			stmt = conn.prepareStatement(q);
			//			stmt.setString(1, ususno);
			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);

				j++;
			}
			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);

				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);

				j++;
			}

			//			if (service_state.equals("4") || unservice_state.equals("BER")) {
			//				stmt.setInt(j, 4);
			//				j++;
			//			} else {
			//				stmt.setInt(j, 1);
			//				j++;
			//			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("model_number"));// 1
				list.add(rs.getString("machine_number"));// 2
				list.add(rs.getString("mac_address"));// 3
				list.add(rs.getString("ip_address"));// 4
				list.add(rs.getString("processor_type"));// 5
				list.add(rs.getString("ram"));// 6
				list.add(rs.getString("hdd"));// 7
				list.add(rs.getString("os"));// 8
				list.add(rs.getString("office"));// 9
				list.add(rs.getString("os_firmware"));// 10
				list.add(rs.getString("dply_env"));// 11
				//				list.add(rs.getString("id"));// 12
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
	public ArrayList<ArrayList<String>> excel_Peripheral_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String ususno=session.getAttribute("roleSusNo").toString();
		String q = "";
		String q1 = "";

		try {

			if (!ususno.equals("")) {
				q1 += "and  ap.sus_no=? ";

			}

			if (!service_state.equals("0") && !service_state.equals("4")) {
				q1 += "and  td.label = ? ";

			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(ap.assets_type as character varying) = ? ";

			}


			if (!unservice_state.equals("0")) {
				q1 += "and td2.label = ? ";

			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ap.id,ap.assets_type,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n"
					+ "ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw \r\n"
					+ "left join t_domain_value td on  td.codevalue:: character varying = ap.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n"
					+ "left join t_domain_value td2 on  td2.codevalue:: character varying = ap.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' where ap.id!=0  and ap.status= '1' \r\n "+q1;

			stmt = conn.prepareStatement(q);
			//			stmt.setString(1, ususno);


			int j = 1;
			if (!ususno.equals("")) {
				stmt.setString(j, ususno);

				j++;
			}
			if (!service_state.equals("0") && !service_state.equals("4")) {
				stmt.setString(j, service_state);

				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			if (!unservice_state.equals("0")) {
				stmt.setString(j, unservice_state);

				j++;
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("type_of_hw"));// 1
				list.add(rs.getString("year_of_proc"));// 2
				list.add(rs.getString("year_of_manufacturing"));// 3
				list.add(rs.getString("b_cost"));// 4
				list.add(rs.getString("make_name"));// 5
				list.add(rs.getString("machine_no"));// 6
				list.add(rs.getString("model_no"));// 7
				list.add(rs.getString("remarks"));// 8
				//				list.add(rs.getString("id"));// 9

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



	// AUDIT REPORT

	//	@Override
	//	public ArrayList<ArrayList<String>> Audit_Report(String asset_type,HttpSession session) {
	//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	//		Connection conn = null;
	//		String q = "";
	//		String q1 = "";
	//		String ususno=session.getAttribute("roleSusNo").toString();
	//
	//		try {
	//			conn = dataSource.getConnection();
	//			PreparedStatement stmt = null;
	//
	//			if (!ususno.equals("")) {
	//				q1 += "and am.sus_no=? ";
	//			}
	//
	//			if (!asset_type.equals("0")) {
	//				q1 += "and cast(am.asset_type as character varying) = ? ";
	//			}
	//
	//			q = "select ma.assets_name,mm.make_name,tmm.model_name,am.ip_address,am.mac_address,am.domain_username,om.os,\r\n"
	//					+ "EXTRACT(YEAR FROM am.proc_date) as proc_year,am.cd_drive,am.last_cyber_audit_date,am.upgrade_repair_date,\r\n"
	//					+ "am.comp_upgrade_repair,am.last_format_done,am.details_of_auth,am.reason_for_formatting,am.machine_number\r\n"
	//					+ "from tb_asset_main am \r\n"
	//					+ "left join tb_mstr_assets ma on  ma.id = am.asset_type and ma.category=1\r\n"
	//					+ "left join tb_mstr_make mm on mm.id = am.make_name\r\n"
	//					+ "left join tb_mstr_model tmm on tmm.id = am.model_name\r\n"
	//					+ "left join tb_mstr_os om on om.id = am.operating_system\r\n"
	//					+ "where am.id != 0 and am.status = '1' " + q1;
	//
	//			stmt = conn.prepareStatement(q);
	//
	//			int j = 1;
	//
	//			if (!ususno.equals("")) {
	//				stmt.setString(j, ususno);
	//				j++;
	//			}
	//
	//			if (!asset_type.equals("0")) {
	//				stmt.setString(j, asset_type);
	//				j++;
	//			}
	//
	//			ResultSet rs = stmt.executeQuery();
	//			System.out.println("Quer --> " + stmt);
	//			while (rs.next()) {
	//				ArrayList<String> list = new ArrayList<String>();
	//
	//				list.add(rs.getString("assets_name"));// 0
	//				list.add(rs.getString("make_name"));// 1
	//				list.add(rs.getString("model_name"));// 2
	//				list.add(rs.getString("ip_address"));// 3
	//				list.add(rs.getString("mac_address"));// 4
	//				list.add(rs.getString("domain_username"));// 5
	//				list.add(rs.getString("os"));// 6
	//				list.add(rs.getString("proc_year"));// 7
	//				list.add(rs.getString("cd_drive"));// 8
	//				list.add(rs.getString("last_cyber_audit_date"));// 9
	//				list.add(rs.getString("upgrade_repair_date"));// 10
	//				list.add(rs.getString("comp_upgrade_repair"));// 11
	//				list.add(rs.getString("last_format_done"));// 12
	//				list.add(rs.getString("details_of_auth"));// 13
	//				list.add(rs.getString("reason_for_formatting"));// 14
	//				list.add(rs.getString("machine_number"));// 15
	//				alist.add(list);
	//			}
	//			rs.close();
	//			stmt.close();
	//			conn.close();
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			if (conn != null) {
	//				try {
	//					conn.close();
	//				} catch (SQLException e) {
	//				}
	//			}
	//		}
	//		return alist;
	//	}


	@Override
	public List<Map<String, Object>> getAuditReportData(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String asset_type, HttpSession sessionUserId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";

		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess =  sessionUserId.getAttribute("roleAccess").toString();
		
		try{
			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt=null;
			String SearchValue = "";
			String qry="";

			if(!asset_type.equals("0")) {
				SearchValue = " and CAST(am.asset_type as character varying) like ? ";
			}
			
			if(!roleSusNo.equals("") && roleAccess.equals("Unit") ) {
				qry = " and am.sus_no=? ";
			}
			
		
			if(!search.equals("")) {
				SearchValue +=" and (upper(ma.assets_name) like ? OR upper(mm.make_name) like ? OR upper(tmm.model_name) like ?"
						+ " OR upper(am.ip_address) like ? OR upper(am.domain_username) like ? OR upper(om.os) like ?"
						+ " OR upper(TO_CHAR(am.proc_date, 'YYYY-MM-DD')) like ? OR upper(am.cd_drive) like ? "
						+ " OR upper(TO_CHAR(am.last_cyber_audit_date, 'YYYY-MM-DD')) like ? OR upper(TO_CHAR(am.upgrade_repair_date, 'YYYY-MM-DD')) like ? "
						+ " OR upper(am.comp_upgrade_repair) like ? OR upper(TO_CHAR(am.last_format_done, 'YYYY-MM-DD')) like ?"
						+ " OR upper(am.details_of_auth) like ? OR upper(am.reason_for_formatting) like ? OR upper(am.machine_number) like ?)";
			}

			q = "select ma.assets_name,mm.make_name,tmm.model_name,am.ip_address,am.mac_address,am.domain_username,om.os,\r\n"
					+ "EXTRACT(YEAR FROM am.proc_date) as proc_year,am.cd_drive,to_char(am.last_cyber_audit_date,'dd-mm-yyyy') as last_cyber_audit_date ,to_char(am.upgrade_repair_date,'dd-mm-yyyy') as upgrade_repair_date ,\r\n"
					+ "am.comp_upgrade_repair,to_char(am.last_format_done,'dd-mm-yyyy') as last_format_done,am.details_of_auth,am.reason_for_formatting,am.machine_number\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_mstr_assets ma on  ma.id = am.asset_type and ma.category=1\r\n"
					+ "left join tb_mstr_make mm on mm.id = am.make_name\r\n"
					+ "left join tb_mstr_model tmm on tmm.id = am.model_name\r\n"
					+ "left join tb_mstr_os om on om.id = am.operating_system\r\n"
					+ "where am.id != 0 and am.status = '1' "
					+ qry + SearchValue
					+" ORDER BY "+ orderColunm +" "+ orderType +" limit " +pageL+" OFFSET "+startPage ;
			stmt=conn.prepareStatement(q);

			int flag = 0;
			
			if(!roleSusNo.equals("") && roleAccess.equals("Unit")) {
				flag += 1;
				stmt.setString(flag, roleSusNo.toUpperCase());
			}

			if(!asset_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, asset_type.toUpperCase()+"%");
			}
			
			

			if(!search.equals("")) {
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("Audit Data --> " + stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				list.add(columns);
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
		return list;
	}


	@Override
	public long getAuditReportCount(String search,String orderColunm,String orderType,String asset_type, HttpSession sessionUserId) {
		int total = 0;
		String q = null;
		Connection conn = null;
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess =  sessionUserId.getAttribute("roleAccess").toString();
		try {
			conn = dataSource.getConnection();

			String SearchValue = "";
			String qry="";
			if(!asset_type.equals("0")) {
				SearchValue = " and CAST(am.asset_type as character varying) like ? ";
			}
			
			if(!roleSusNo.equals("") && roleAccess.equals("Unit") ) {
				qry = " and am.sus_no=? ";
			}

			if(!search.equals("")) {
				SearchValue +=" and (upper(ma.assets_name) like ? OR upper(mm.make_name) like ? OR upper(tmm.model_name) like ?"
						+ " OR upper(am.ip_address) like ? OR upper(am.domain_username) like ? OR upper(om.os) like ?"
						+ " OR upper(TO_CHAR(am.proc_date, 'YYYY-MM-DD')) like ? OR upper(am.cd_drive) like ? "
						+ " OR upper(TO_CHAR(am.last_cyber_audit_date, 'YYYY-MM-DD')) like ? OR upper(TO_CHAR(am.upgrade_repair_date, 'YYYY-MM-DD')) like ? "
						+ " OR upper(am.comp_upgrade_repair) like ? OR upper(TO_CHAR(am.last_format_done, 'YYYY-MM-DD')) like ?"
						+ " OR upper(am.details_of_auth) like ? OR upper(am.reason_for_formatting) like ? OR upper(am.machine_number) like ?)";
			}

			q="select count(app.*) from(select ma.assets_name,mm.make_name,tmm.model_name,am.ip_address,am.mac_address,am.domain_username,om.os,\r\n"
					+ " EXTRACT(YEAR FROM am.proc_date) as proc_year,am.cd_drive,am.last_cyber_audit_date,am.upgrade_repair_date,\r\n"
					+ "	am.comp_upgrade_repair,am.last_format_done,am.details_of_auth,am.reason_for_formatting,am.machine_number\r\n"
					+ "	from tb_asset_main am \r\n"
					+ "	left join tb_mstr_assets ma on  ma.id = am.asset_type and ma.category=1\r\n"
					+ "	left join tb_mstr_make mm on mm.id = am.make_name\r\n"
					+ "	left join tb_mstr_model tmm on tmm.id = am.model_name\r\n"
					+ "	left join tb_mstr_os om on om.id = am.operating_system\r\n"
					+ "	where am.id != 0 and am.status = '1'  \r\n "
					+ qry + SearchValue 
					+" ) app" ;
			PreparedStatement stmt = conn.prepareStatement(q);

			int flag = 0;
			
			if(!roleSusNo.equals("") && roleAccess.equals("Unit")) {
				flag += 1;
				stmt.setString(flag, roleSusNo.toUpperCase());
			}
			
			if(!asset_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, asset_type.toUpperCase()+"%");
			}
			
			

			if(!search.equals("")) {
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
			}

			ResultSet rs = stmt.executeQuery();
			System.out.println("Audit Count --> " + stmt);

			while (rs.next()) {
				total = rs.getInt(1);
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
		return total;
	}


	@Override
	public ArrayList<ArrayList<String>> pdf_AuditReport(HttpSession session, String asset_type) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q1 = "";
		String ususno=session.getAttribute("roleSusNo").toString();
		try {
			if (!ususno.equals("")) {
				q1 += "and  am.sus_no=? ";
			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(am.asset_type as character varying) = ? ";
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select ma.assets_name,mm.make_name,tmm.model_name,am.ip_address,am.mac_address,am.domain_username,om.os,\r\n"
					+ "EXTRACT(YEAR FROM am.proc_date) as proc_year,am.cd_drive,am.last_cyber_audit_date,am.upgrade_repair_date,\r\n"
					+ "am.comp_upgrade_repair,am.last_format_done,am.details_of_auth,am.reason_for_formatting,am.machine_number\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_mstr_assets ma on  ma.id = am.asset_type and ma.category=1\r\n"
					+ "left join tb_mstr_make mm on mm.id = am.make_name\r\n"
					+ "left join tb_mstr_model tmm on tmm.id = am.model_name\r\n"
					+ "left join tb_mstr_os om on om.id = am.operating_system\r\n"
					+ "where am.id != 0 and am.status = '1' " + q1;

			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);
				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				String Brack = "";
				Brack = rs.getString("assets_name").replace("&#40;", "(").replace("&#41;", ")");
				list.add(Brack);
				list.add(rs.getString("machine_number"));
				list.add(rs.getString("make_name"));
				list.add(rs.getString("model_name"));
				list.add(rs.getString("ip_address"));
				list.add(rs.getString("mac_address"));
				list.add(rs.getString("domain_username"));
				list.add(rs.getString("os"));
				list.add(rs.getString("proc_year"));
				list.add(rs.getString("cd_drive"));
				list.add(rs.getString("last_cyber_audit_date"));
				list.add(rs.getString("upgrade_repair_date"));
				list.add(rs.getString("comp_upgrade_repair"));
				list.add(rs.getString("last_format_done"));
				list.add(rs.getString("details_of_auth"));
				list.add(rs.getString("reason_for_formatting"));

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
	public ArrayList<ArrayList<String>> excel_AuditReport(HttpSession session, String asset_type) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String q1 = "";
		String ususno=session.getAttribute("roleSusNo").toString();
		try {
			if (!ususno.equals("")) {
				q1 += "and  am.sus_no=? ";
			}

			if (!asset_type.equals("0")) {
				q1 += "and  cast(am.asset_type as character varying) = ? ";
			}

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select ma.assets_name,mm.make_name,tmm.model_name,am.ip_address,am.mac_address,am.domain_username,om.os,\r\n"
					+ "EXTRACT(YEAR FROM am.proc_date) as proc_year,am.cd_drive,am.last_cyber_audit_date,am.upgrade_repair_date,\r\n"
					+ "am.comp_upgrade_repair,am.last_format_done,am.details_of_auth,am.reason_for_formatting,am.machine_number\r\n"
					+ "from tb_asset_main am \r\n"
					+ "left join tb_mstr_assets ma on  ma.id = am.asset_type and ma.category=1\r\n"
					+ "left join tb_mstr_make mm on mm.id = am.make_name\r\n"
					+ "left join tb_mstr_model tmm on tmm.id = am.model_name\r\n"
					+ "left join tb_mstr_os om on om.id = am.operating_system\r\n"
					+ "where am.id != 0 and am.status = '1' " + q1;

			stmt = conn.prepareStatement(q);
			int j = 1;

			if (!ususno.equals("")) {
				stmt.setString(j, ususno);
				j++;
			}

			if (!asset_type.equals("0")) {
				stmt.setString(j, asset_type);
				j++;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				String Brack = "";
				Brack = rs.getString("assets_name").replace("&#40;", "(").replace("&#41;", ")");
				list.add(Brack);
				list.add(rs.getString("machine_number"));
				list.add(rs.getString("make_name"));
				list.add(rs.getString("model_name"));
				list.add(rs.getString("ip_address"));
				list.add(rs.getString("mac_address"));
				list.add(rs.getString("domain_username"));
				list.add(rs.getString("os"));
				list.add(rs.getString("proc_year"));
				list.add(rs.getString("cd_drive"));
				list.add(rs.getString("last_cyber_audit_date"));
				list.add(rs.getString("upgrade_repair_date"));
				list.add(rs.getString("comp_upgrade_repair"));
				list.add(rs.getString("last_format_done"));
				list.add(rs.getString("details_of_auth"));
				list.add(rs.getString("reason_for_formatting"));
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

}
