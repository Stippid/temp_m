package com.dao.fpmis;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class FP_MIS_TransactionDAOImpl implements FP_MIS_TransactionDAO {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private FP_MIS_MasterDAO fp1_Dao;
	
	public @ResponseBody String getUnitType(String enc, HttpSession s1, String nParaValue) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		String rolesus = s1.getAttribute("roleSusNo").toString();
		String rolefmn = s1.getAttribute("roleFormationNo").toString();
		String nRet = "";
		try {			
			if (rolesus.length()>0) {
				nParaValue = nParaValue.toUpperCase();
				if (nParaValue.equalsIgnoreCase("SUS_")) {
					nParaValue = nParaValue + rolesus;
				}
				String[] nPara = nParaValue.split("_");
				Connection conn = null;
				conn = dataSource.getConnection();
				String sql1 = "";
				sql1 = " select hq_type from fp.fp_tv_orbat_dtl ";
				if (nPara[0].equalsIgnoreCase("FMN")) {
					sql1 = sql1 + " where form_code_control='" + nPara[1] + "' and hq_type::integer<5";
				} else if (nPara[0].equalsIgnoreCase("SUS")) {
					sql1 = sql1 + " where sus_no='" + nPara[1] + "'";
				} else if (nPara[0].equalsIgnoreCase("UNIT")) {
					sql1 = sql1 + " where unit_name like '%" + nPara[1] + "%' and hq_type::integer=5";
				}
				System.out.println("-"+nParaValue);
				PreparedStatement stmt = conn.prepareStatement(sql1);
				ResultSet rs = stmt.executeQuery();
	
				if (!rs.isBeforeFirst()) {
					nRet = "";
				} else {
					while (rs.next()) {
						nRet = rs.getString("hq_type");
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			} else {
				nRet = "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRet;
	}
	
	
	public @ResponseBody ArrayList<ArrayList<String>> getUnitPrefDetl(String enc, HttpSession s1, String nParaValue) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		String username = s1.getAttribute("username").toString();
		String rolesus = s1.getAttribute("roleSusNo").toString();
		String rolefmn = s1.getAttribute("roleFormationNo").toString();
		String nRet = "";
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {			
			if (rolesus.length()>0) {
				nParaValue = nParaValue.toUpperCase();
				String[] nPara = nParaValue.split("_");
				Connection conn = null;
				conn = dataSource.getConnection();
				String sql1 = "";
				System.out.println("-"+nParaValue);
				sql1 = " select id,psus_no,sus_no,chl_head_code,usr_id from fp.fp_tb_pref_head ";
				if (nPara[0].equalsIgnoreCase("SUS")) {
					sql1 = sql1 + " where sus_no='" + nPara[1] + "'";
				} else if (nPara[0].equalsIgnoreCase("PSUS")) {
					sql1 = sql1 + " where psus_no='" + nPara[1] + "'";
				} else if (nPara[0].equalsIgnoreCase("HEAD")) {
					sql1 = sql1 + " where chl_head_code='" + nPara[1] + "'";
				}
				if (!(nPara[2]==null || nPara[2].equalsIgnoreCase(null))) {
					sql1 = sql1 + " and chl_head_code='" + nPara[2] + "'";
				}
				sql1 = sql1 + " and usr_id<>'" + username + "'";
				System.out.println("--"+sql1);
				PreparedStatement stmt = conn.prepareStatement(sql1);
				ResultSet rs = stmt.executeQuery();
	
				if (!rs.isBeforeFirst()) {
					nRet = "";
				} else {
					while (rs.next()) {
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString("id"));
						list.add(rs.getString("psus_no"));
						list.add(rs.getString("sus_no"));
						list.add(rs.getString("chl_head_code"));
						list.add(rs.getString("usr_id"));
						if (enc.equalsIgnoreCase("")) {
							li.add(list);
						} else {
							if (list.size() != 0) {
								List<String> FList = fp1_Dao.getMNHEncList(list, s1);
								li.add((ArrayList<String>) FList);
							} else {
								li.add(list);
							}
						}
					}
				}
				rs.close();
				stmt.close();
				conn.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getunitlist(String enc, HttpSession s1, String nParaValue) {
		System.out.println("getunitlist-para-"+nParaValue);
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			String tofmnCnd = "";
			if (nPara[2].length() <= 0 || nPara[1].length() <= 0) {
				tofmnCnd = "";
			} else {
				if (nPara[1].equals("0")) {
					tofmnCnd = "";
				}
				if (nPara[1].equals("1")) {
					tofmnCnd = " substring(form_code_control,1,1)='" + nPara[2].substring(0, 1) + "'";
				}
				if (nPara[1].equals("2")) {
					tofmnCnd = " substring(form_code_control,1,3)='" + nPara[2].substring(0, 3) + "'";
				}
				if (nPara[1].equals("3")) {
					tofmnCnd = " substring(form_code_control,1,6)='" + nPara[2].substring(0, 6) + "'";
				}
				if (nPara[1].equals("4")) {
					tofmnCnd = " substring(form_code_control,1,10)='" + nPara[2].substring(0, 10) + "'";
				}
				if (nPara[1].equals("5")) {
					tofmnCnd = " sus_no='" + nPara[3] + "'";
				}
			}

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nPara[1].equals("-1")) {
				sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl where  hq_type::integer<=1";
				sql1 = sql1 + " order by hq_type,form_code_control,sus_no,unit_name";
			} else {
				sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from (";
				if (nPara[1].equals("-1")) {
					sql1 = sql1
							+ " select '0'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl where hq_type::integer<=0";
					sql1 = sql1 + " UNION ALL ";
				}
				sql1 = sql1
						+ " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl ";
				if (nPara[0].equalsIgnoreCase("HQ") & (!nPara[1].equals("5"))) {
					if (nPara[1].equals("0")) {
						sql1 = sql1
								+ " where sus_no in (SELECT distinct chl_sus_no FROM fp.fp_tb_pref_unit WHERE SUS_NO='"
								+ nPara[3] + "')";
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where (hq_type::integer<5 and hq_type::integer<="
								+ (Integer.parseInt(nPara[1]) + 1) +")";
						}
					}
				} else if (nPara[0].equalsIgnoreCase("UNIT")) {
					if (!nPara[1].equals("5")) {
						sql1 = sql1 + " where hq_type::integer<=5 ";
					}
				} else {
					if (nPara[0].equalsIgnoreCase("SUS")) {
						sql1 = sql1 + "";
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where unit_name like '%XNX%'";
						}
					}
				}
				if (tofmnCnd.length() > 0) {
					if (nPara[1].equals("5")) {
						sql1 = sql1 + " where  " + tofmnCnd;
					} else {
						if (nPara[1].equals("0")) {

						} else {
							if (nPara[0].equalsIgnoreCase("HQ") || nPara[0].equalsIgnoreCase("UNIT")) {
								sql1 = sql1 + " and " + tofmnCnd;
							} else {
								sql1 = sql1 + " where " + tofmnCnd;
							}
						}
					}
				}
				sql1 = sql1 + " ) b order by ty,hq_type,form_code_control,sus_no,unit_name";
			}
			System.out.println("getunitlist--"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<ArrayList<String>> dli = new ArrayList<ArrayList<String>>();
				//narendra
				dli = getunitlist("", s1, "SUS_5_XXXXXXXXXX_" + nPara[3]);
				String nP = dli.get(0).get(3) + "_" + dli.get(0).get(0) + "_" + dli.get(0).get(1);
				list.add(dli.get(0).get(0));
				list.add(dli.get(0).get(1));
				list.add(dli.get(0).get(2));
				list.add(dli.get(0).get(3));
				list.add(dli.get(0).get(4));
				li.add(list);				
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("hq_type"));
					list.add(rs.getString("hq_child"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}				
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public @ResponseBody ArrayList<ArrayList<String>> getUnitHirarlist(String enc, HttpSession s1, String nParaValue) {
		System.out.println("getUnitHirarlist-para-"+nParaValue);
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			try {
				if (nPara[4].equalsIgnoreCase("Y")) {
					
				}				
			} catch (Exception e) {
				nPara[4]="N";
			}			
			
			String tofmnCnd = "";
			if (nPara[1].length() <= 0 || nPara[2].length() <= 0) {
				tofmnCnd = "";
			} else {
				if (nPara[4].equalsIgnoreCase("Y")) {
					if (!nPara[1].equals("5")) {
						tofmnCnd = " sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='" + nPara[3] + "')";
					} else {
						tofmnCnd = " sus_no='" + nPara[3] + "'";
					}
				} else {				
					if (nPara[1].equals("0")) {
						tofmnCnd = " sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='" + nPara[3] + "')";
					}
					if (nPara[1].equals("1")) {
						tofmnCnd = " substring(form_code_control,1,1)='" + nPara[2].substring(0, 1) + "'";
					}
					if (nPara[1].equals("2")) {
						tofmnCnd = " substring(form_code_control,1,3)='" + nPara[2].substring(0, 3) + "'";
					}
					if (nPara[1].equals("3")) {
						tofmnCnd = " substring(form_code_control,1,6)='" + nPara[2].substring(0, 6) + "'";
					}
					if (nPara[1].equals("4")) {
						tofmnCnd = " substring(form_code_control,1,10)='" + nPara[2].substring(0, 10) + "'";
					}
					if (nPara[1].equals("5")) {
						tofmnCnd = " sus_no='" + nPara[3] + "'";
					}
				}
			}

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nPara[1].equals("-1")) {
				sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl";
				sql1 = sql1 + " order by hq_type,form_code_control,sus_no,unit_name";
			} else {
				sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from (";
				if (nPara[1].equals("-1")) {
					sql1 = sql1
							+ " select '0'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl where hq_type::integer<=0";
					sql1 = sql1 + " UNION ALL ";
				}
				sql1 = sql1
						+ " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl ";
				if (nPara[0].equalsIgnoreCase("HQ") & (!nPara[1].equals("5"))) {
					if (nPara[1].equals("0")) {
						sql1 = sql1
								+ " where sus_no in (SELECT distinct chl_sus_no FROM fp.fp_tb_pref_unit WHERE SUS_NO='"
								+ nPara[3] + "')";
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where (hq_type::integer<5 and hq_type::integer<="
								+ (Integer.parseInt(nPara[1]) + 1) +")";
						}
					}
				} else if (nPara[0].equalsIgnoreCase("UNIT")) {
					if (!nPara[1].equals("5")) {
						sql1 = sql1 + " where hq_type::integer<=5 ";
					}
				} else {
					if (nPara[0].equalsIgnoreCase("SUS")) {
						if (nPara[1].equals("0")) {
							//sql1 = sql1 + " where  " + tofmnCnd;
							sql1 = sql1 + "";
						} else {
							sql1 = sql1 + "";
						}
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where unit_name like '%XNX%'";
						}
					}
				}
				if (tofmnCnd.length() > 0) {
					if (nPara[1].equals("5")) {
						sql1 = sql1 + " where  " + tofmnCnd;
					} else {
						if (nPara[1].equals("0")) {
							if (nPara[0].equalsIgnoreCase("HQ") || nPara[0].equalsIgnoreCase("UNIT")) {
								sql1 = sql1 + " and " + tofmnCnd;
							} else {
								sql1 = sql1 + " where " + tofmnCnd;
							}
						} else {
							if (nPara[0].equalsIgnoreCase("HQ") || nPara[0].equalsIgnoreCase("UNIT")) {
								sql1 = sql1 + " and " + tofmnCnd;
							} else {
								sql1 = sql1 + " where " + tofmnCnd;
							}
						}
					}
				}
				sql1 = sql1 + " ) b order by ty,form_code_control,hq_type,sus_no,unit_name";
			}
			System.out.println("getUnitHirarlist--"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<ArrayList<String>> dli = new ArrayList<ArrayList<String>>();
				dli = getunitlist("", s1, "SUS_5_XXXXXXXXXX_" + nPara[3]);
				String nP = dli.get(0).get(3) + "_" + dli.get(0).get(0) + "_" + dli.get(0).get(1);
				list.add(dli.get(0).get(0));
				list.add(dli.get(0).get(1));
				list.add(dli.get(0).get(2));
				list.add(dli.get(0).get(3));
				li.add(list);				
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("hq_type"));
					list.add(rs.getString("hq_child"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}				
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	
	public @ResponseBody ArrayList<ArrayList<String>> getHirarwithFplist(String enc, HttpSession s1, String nParaValue) {
		System.out.println("getHirarwithFplist-para-"+nParaValue);
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> li1 = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			String tofmnCnd = "";
			li1=this.getunitlist("", s1, "SUS_5_XXXXXXXXXX_" + nPara[3]);
			String ischld=li1.get(0).get(4);			
			System.out.println("getHirarwithFplist-ischld-"+ischld);
			if (nPara[2].length() <= 0 || nPara[1].length() <= 0) {
				tofmnCnd = "";
			} else {
				if (ischld.equalsIgnoreCase("Y") ) {
					if (!nPara[1].equals("5")) {
						tofmnCnd = " sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='" + nPara[3] + "')";
					} else {
						tofmnCnd = " sus_no='" + nPara[3] + "'";
					} 
				} else {				
					if (nPara[1].equals("0")) {
						tofmnCnd = " sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='" + nPara[3] + "')";
					}
					if (nPara[1].equals("1")) {
						tofmnCnd = " substring(form_code_control,1,1)='" + nPara[2].substring(0, 1) + "'";
					}
					if (nPara[1].equals("2")) {
						tofmnCnd = " substring(form_code_control,1,3)='" + nPara[2].substring(0, 3) + "'";
					}
					if (nPara[1].equals("3")) {
						tofmnCnd = " substring(form_code_control,1,6)='" + nPara[2].substring(0, 6) + "'";
					}
					if (nPara[1].equals("4")) {
						tofmnCnd = " substring(form_code_control,1,10)='" + nPara[2].substring(0, 10) + "'";
					}
					if (nPara[1].equals("5")) {
						tofmnCnd = " sus_no='" + nPara[3] + "'";
					}
				}
			}

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			/*if (nPara[1].equals("-1")) {
				sql1 = " select ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl";
				sql1 = sql1 + " order by hq_type,form_code_control,sus_no,unit_name";
			} else {
				sql1 = " select ty,hq_type,form_code_control,sus_no,unit_name,hq_child from (";
				if (nPara[1].equals("-1")) {
					sql1 = sql1
							+ " select '0'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl where hq_type::integer<=0";
					sql1 = sql1 + " UNION ALL ";
				}
				sql1 = sql1
						+ " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl ";
				if (nPara[0].equalsIgnoreCase("HQ") & (!nPara[1].equals("5"))) {
					if (nPara[1].equals("0")) {
						sql1 = sql1
								+ " where sus_no in (SELECT distinct chl_sus_no FROM fp.fp_tb_pref_unit WHERE SUS_NO='"
								+ nPara[3] + "')";
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where (hq_type::integer<5 and hq_type::integer<="
								+ (Integer.parseInt(nPara[1]) + 1) +")";
						}
					}
				} else if (nPara[0].equalsIgnoreCase("UNIT")) {
					if (!nPara[1].equals("5")) {
						sql1 = sql1 + " where hq_type::integer<=5 ";
					}
				} else {
					if (nPara[0].equalsIgnoreCase("SUS")) {
						if (nPara[1].equals("0")) {
							//sql1 = sql1 + " where  " + tofmnCnd;
							sql1 = sql1 + "";
						} else {
							sql1 = sql1 + "";
						}
					} else {
						if (!nPara[1].equals("5")) {
							sql1 = sql1 + " where unit_name like '%XNX%'";
						}
					}
				}
				if (tofmnCnd.length() > 0) {
					if (nPara[1].equals("5")) {
						sql1 = sql1 + " where  " + tofmnCnd;
					} else {
						if (nPara[1].equals("0")) {
							if (nPara[0].equalsIgnoreCase("HQ") || nPara[0].equalsIgnoreCase("UNIT")) {
								sql1 = sql1 + " and " + tofmnCnd;
							} else {
								sql1 = sql1 + " where " + tofmnCnd;
							}
						} else {
							if (nPara[0].equalsIgnoreCase("HQ") || nPara[0].equalsIgnoreCase("UNIT")) {
								sql1 = sql1 + " and " + tofmnCnd;
							} else {
								sql1 = sql1 + " where " + tofmnCnd;
							}
						}
					}
				}
				sql1 = sql1 + " ) b order by ty,form_code_control,hq_type,sus_no,unit_name";
			}*/
			
			sql1 = sql1 + " select distinct ty,hq_type,form_code_control,sus_no,unit_name,hq_child from (";
			
			if (ischld.equalsIgnoreCase("N") ) {
				sql1 = sql1 + " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl";
				//sql1 = sql1 + " where  substring(form_code_control,1,3)='311'";
				if (!nPara[3].equalsIgnoreCase("A000001")) {
					sql1 = sql1 + " where  " + tofmnCnd;
				}
				sql1 = sql1 + " union all ";
			}
			
			sql1 = sql1 + " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl  where  ";
			sql1 = sql1 + " sus_no in (select distinct sus_no from fp.fp_tb_pref_head where psus_no='" + nPara[3] + "')";
			if (nPara[0].equalsIgnoreCase("HQ")) {
				sql1 = sql1 + " ) b where hq_type::integer<=4";	
			} else if (nPara[0].equalsIgnoreCase("UNIT")) {
				sql1 = sql1 + " ) b where hq_type::integer=5";
			} else {
				sql1 = sql1 + " ) b ";
			}
			sql1 = sql1 + " order by ty,form_code_control,hq_type,sus_no,unit_name";
			
			
			System.out.println("getHirarwithFplist--"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<ArrayList<String>> dli = new ArrayList<ArrayList<String>>();
				dli = getunitlist("", s1, "SUS_5_XXXXXXXXXX_" + nPara[3]);
				String nP = dli.get(0).get(3) + "_" + dli.get(0).get(0) + "_" + dli.get(0).get(1);
				list.add(dli.get(0).get(0));
				list.add(dli.get(0).get(1));
				list.add(dli.get(0).get(2));
				list.add(dli.get(0).get(3));
				li.add(list);				
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("hq_type"));
					list.add(rs.getString("hq_child"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}				
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getunitBuglist(String enc, HttpSession s1, String nParaValue) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			String tofmnCnd = "";
			System.out.println("getunitBuglist-para-"+nParaValue);
			if (nPara[2].length() <= 0 || nPara[1].length() <= 0) {
				tofmnCnd = "";
			} else {
				if (nPara[1].equals("0")) {
					tofmnCnd = "";
				}
				if (nPara[1].equals("1")) {
					tofmnCnd = " substring(form_code_control,1,1)='" + nPara[2].substring(0, 1) + "'";
				}
				if (nPara[1].equals("2")) {
					tofmnCnd = " substring(form_code_control,1,3)='" + nPara[2].substring(0, 3) + "'";
				}
				if (nPara[1].equals("3")) {
					tofmnCnd = " substring(form_code_control,1,6)='" + nPara[2].substring(0, 6) + "'";
				}
				if (nPara[1].equals("4")) {
					tofmnCnd = " substring(form_code_control,1,10)='" + nPara[2].substring(0, 10) + "'";
				}
				if (nPara[1].equals("5")) {
					tofmnCnd = " sus_no='" + nPara[1] + "'";
				}
			}

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nPara[1].equals("-1")) {
				sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl where  sus_no in (select chl_sus_no from fp.fp_tb_pref_unit ";
				sql1 = sql1 + " where sus_no='" + nPara[3] + "')";
				sql1 = sql1 + " order by hq_type,form_code_control,sus_no,unit_name";
			} else {
				if (!nPara[1].equals("5")) {
					sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from (";
					sql1 = sql1
							+ " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl";
					sql1 = sql1 + " where sus_no in (select chl_sus_no from fp.fp_tb_pref_unit where sus_no='"
							+ nPara[3] + "')";
					sql1 = sql1 + " ) b order by hq_type,form_code_control,sus_no,unit_name";
				} else {
					sql1 = " select hq_type,form_code_control,sus_no,unit_name,hq_child from (";
					sql1 = sql1
							+ " select '1'::text as ty,hq_type,form_code_control,sus_no,unit_name,hq_child from fp.fp_tv_orbat_dtl";
					sql1 = sql1 + " where sus_no='" + nPara[3] + "' ";
					sql1 = sql1 + " ) b order by hq_type,form_code_control,sus_no,unit_name";
				}
			}
			System.out.println("getunitBuglist-para-"+nParaValue+"-Sql-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			int ncnt=0;
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("form_code_control"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("hq_type"));
				list.add(rs.getString("hq_child"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
				ncnt++;
			}
			if (ncnt==0) {				
				ArrayList<ArrayList<String>> dli = new ArrayList<ArrayList<String>>();
				dli = getunitlist("", s1, "SUS_5_XXXXXXXXXX_" + nPara[3]);
				String nP = dli.get(0).get(3) + "_" + dli.get(0).get(0) + "_" + dli.get(0).get(1);
				System.out.println("==="+nP);
				ArrayList<String> list = new ArrayList<String>();
				list.add(dli.get(0).get(0));
				list.add(dli.get(0).get(1));
				list.add(dli.get(0).get(2));
				list.add("5");
				li.add(list);				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getHeadlist(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nParaValue.equals("-1")) {
				sql1 = "select '-1' as trType,p.tr_head,q.head_desc from (";
				sql1 = sql1
						+ " select distinct a.major_head,'' as minor_head,'' as head_code,concat(a.major_head) as tr_head from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " ) p left join fp.fp_tb_head_mstr q on p.tr_head=q.tr_head";
				sql1 = sql1 + " where q.head_desc is not null";
			} else {
				String[] nPara = nParaValue.split(":");
				sql1 = "select p.trType,p.tr_head,upper(q.head_desc) as head_desc from (";
				sql1 = sql1
						+ " select distinct '1' as trType,a.major_head,'' as minor_head,'' as sub_head,'' as head_code,concat(a.major_head) as tr_head from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " where a.tr_level='1'";
				sql1 = sql1 + " union all ";
				sql1 = sql1
						+ " select distinct '2' as trType,a.major_head,a.minor_head as minor_head,'' as sub_head,'' as head_code,concat(a.major_head,':',a.minor_head) as tr_head from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " where a.tr_level='2'";
				sql1 = sql1 + " union all";
				sql1 = sql1
						+ " select distinct '3' as trType,a.major_head,a.minor_head as minor_head,a.sub_head as sub_head,'' as head_code,concat(a.major_head,':',a.minor_head,':',a.sub_head) as tr_head from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " where a.tr_level='3'";
				sql1 = sql1 + " union all";
				sql1 = sql1
						+ " select distinct '4' as trType,a.major_head,a.minor_head as minor_head,a.sub_head,a.head_code as head_code,concat(a.major_head,':',a.minor_head,':',a.sub_head,':',a.head_code) as tr_head from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " where a.tr_level='4'";
				sql1 = sql1 + " ) p left join fp.fp_tb_head_mstr q on p.tr_head=q.tr_head";
				sql1 = sql1 + " where q.head_desc is not null";
				String hCnd = "";
				if (nPara.length > 0) {
					for (int q = 0; q < nPara.length; q++) {
						if (q > 0) {
							hCnd = hCnd + " and ";
						}
						hCnd = hCnd + " split_part (p.tr_head, ':'," + (q + 1) + ")='" + nPara[q] + "' ";
					}
				}
				if (hCnd.length() > 0) {
					sql1 = sql1 + " and " + hCnd;
				}
				sql1 = sql1 + " order by p.tr_head";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("tr_head"));
					list.add(rs.getString("head_desc"));
					list.add(rs.getString("trType"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getHeadBuglist(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select b.tr_level,a.sus_no,a.chl_head_code,b.tr_head,b.head_desc,a.usr_id from fp.fp_tb_pref_head a";
			sql1 = sql1 + " left join fp.fp_tb_head_mstr b on a.chl_head_code=b.tr_head";
			sql1 = sql1 + " where sus_no='" + nParaValue + "'";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("tr_head"));
					list.add(rs.getString("head_desc"));
					list.add(rs.getString("tr_level"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> FindUnitList(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select form_code_control,sus_no,unit_name,hq_type from fp.fp_tv_orbat_dtl";
			if (nPara[0].equalsIgnoreCase("HQ")) {
				if (nPara[1].length() <= 0) {
					sql1 = sql1 + " where hq_type::integer<5";
				} else {
					sql1 = sql1 + " where hq_type::integer<5 and form_code_control='" + nPara[2] + "'";
				}

			} else if (nPara[0].equalsIgnoreCase("UNIT")) {
				if (nPara[1].length() <= 0) {
					sql1 = sql1 + " where hq_type::integer<=5";
				} else {
					sql1 = sql1 + " where sus_no='" + nPara[3] + "'";
				}
			} else {
				sql1 = sql1 + " where unit_name like '%XNX%'";
			}
			sql1 = sql1 + " order by form_code_control,sus_no,unit_name";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("form_code_control"));
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					list.add(rs.getString("hq_type"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> FindFinYear(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = nParaValue.toUpperCase();
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			
			//sql1 = " select domainid,codevalue,label,label_s,disp_order from fp.fp_domain_values where domainid='FINYEAR'"; 
			//sql1 += " and codevalue in (select fin_year from fp.fp_tb_adm_control where est_type in ('CFY','PVN') and status='1') order by disp_order";
					
			sql1 = " select a.domainid,a.codevalue,a.label,(case when b.est_type='CFY' then concat(a.label_s,' ( Cur FY )') when b.est_type='PVN' then concat(a.label_s,' ( Provn FY )') else a.label_s end) as label_s,";
			sql1 += " a.disp_order,b.est_type from fp.fp_domain_values a";
			
			if (nParaValue.equalsIgnoreCase("CFY")) {
				sql1 += " inner join ";
			} else {
				sql1 += " left join ";
			}
			sql1 += " (select fin_year,est_type from fp.fp_tb_adm_control where est_type in ('CFY','PVN') and status='1') b on a.codevalue=b.fin_year and b.est_type in ('CFY','PVN')";
			sql1 += " where domainid='FINYEAR' order by a.disp_order";
			
			if (nParaValue.equalsIgnoreCase("D")) {
				sql1 = sql1 +" Desc";
			}
			System.out.println("FindFinYear-sql1-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("codevalue"));
					list.add(rs.getString("label"));
					list.add(rs.getString("label_s"));					
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody String fp_fund_tfr_confirm(String m1_trhead, String m1_frunt, String m1_blamt, String m1_tramt,
			String m1_tount, String m1_tohead, String m1_trtype, String m1_tryear, String m1_trrem, String m1_pid,
			String usrid, String n1_trAltType,String n1_expType,String expId) {
		String nRet;
		System.out.println("--"+n1_expType);
		try {
			String nTrFile = "fp.fp_tb_trans_detl" + m1_tryear.substring(2, 4);

			Connection conn = null;
			conn = dataSource.getConnection();
			if (n1_trAltType.equalsIgnoreCase("RT")) {
				String sql0 = "";
				sql0 = "insert into " + nTrFile;
				sql0 = sql0
						+ " (tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,tr_sub_type,tr_exp_type) values ";
				sql0 = sql0
						+ " (?,localtimestamp,?,?,0,?::double precision,?,?,?,?,localtimestamp,?,localtimestamp,?,?,?, ?,?);";

				PreparedStatement stmt0 = conn.prepareStatement(sql0);

				String[] tount0 = m1_frunt.split("_");
				String[] frunt0 = m1_tount.split("_");
				String[] tohed0 = m1_tohead.split("_");

				if (tohed0[1].substring(tohed0[1].length() - 1, tohed0[1].length()).equals(":")) {
					tohed0[1] = tohed0[1].substring(0, tohed0[1].length() - 1);
				}

				stmt0.setString(1, m1_trhead);
				stmt0.setString(2, frunt0[1]);
				stmt0.setString(3, frunt0[2]);
				stmt0.setLong(4, (Long.parseLong(m1_tramt) * -1));
				stmt0.setString(5, tount0[0]);
				stmt0.setString(6, tount0[1]);
				stmt0.setString(7, tohed0[1]);
				stmt0.setString(8, m1_trtype);
				stmt0.setString(9, usrid);
				stmt0.setString(10, usrid);
				stmt0.setString(11, m1_trrem);
				stmt0.setInt(12, Integer.parseInt(m1_pid));
				stmt0.setString(13, "RS");
				stmt0.setString(14, n1_expType);
				stmt0.executeUpdate();
				nRet = "1";
				stmt0.close();
			}
			String sql1 = "";
			sql1 = "insert into " + nTrFile;
			sql1 = sql1
					+ " (tr_head,period,fr_fmn_code,fr_sus_no,recd_amt,exp_amt,to_fmn_code,to_sus_no,tr_head_to,tr_type,data_cr_date,data_cr_by,data_upd_date,data_upd_by,to_remarks,pid,tr_sub_type,tr_exp_type) values ";
			if (m1_trtype.equalsIgnoreCase("FND")) {
				sql1 = sql1
						+ " (?,localtimestamp,?,?,?::double precision,0,?,?,?,?,localtimestamp,?,localtimestamp,?,?,?,?,?);";
			} else {
				sql1 = sql1
						+ " (?,localtimestamp,?,?,0,?::double precision,?,?,?,?,localtimestamp,?,localtimestamp,?,?,?,?,?);";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);

			String[] frunt = m1_frunt.split("_");
			String[] tount = m1_tount.split("_");
			String[] tohed = m1_tohead.split("_");

			if (tohed[1].substring(tohed[1].length() - 1, tohed[1].length()).equals(":")) {
				tohed[1] = tohed[1].substring(0, tohed[1].length() - 1);
			}

			stmt.setString(1, m1_trhead);
			if (m1_frunt.contains("MOD") || m1_frunt.length() <= 0) {
				stmt.setString(2, "");
				stmt.setString(3, "");
			} else {
				stmt.setString(2, frunt[0]);
				stmt.setString(3, frunt[1]);
			}
			stmt.setString(4, m1_tramt);
			stmt.setString(5, tount[1]);
			stmt.setString(6, tount[2]);
			stmt.setString(7, tohed[1]);
			stmt.setString(8, m1_trtype);
			stmt.setString(9, usrid);
			stmt.setString(10, usrid);
			stmt.setString(11, m1_trrem);
			if (m1_pid.length() <= 0) {
				stmt.setInt(12, 1);
			} else {
				stmt.setInt(12, Integer.parseInt(m1_pid));
			}
			if (m1_frunt.contains("MOD") || m1_frunt.length() <= 0) {
				stmt.setString(13, "FN");
				stmt.setString(14, n1_expType);
			} else {
				stmt.setString(13, n1_trAltType);
				stmt.setString(14, n1_expType);
			}			
			System.out.println("Insert Qry-"+stmt.toString());
			stmt.executeUpdate();
			nRet = "1";
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			nRet = "0";
		}
		return nRet;
	}

	
	public @ResponseBody String fp_fund_tfr_confirm_id(String m1_trhead, String m1_tramt,
			String m1_trtype, String m1_tryear) {
		String nRet="";
		String sql1;
		try {
			String nTrFile = "fp.fp_tb_trans_detl" + m1_tryear.substring(2, 4);

			Connection conn = null;
			conn = dataSource.getConnection();
			sql1 = " select id from "+nTrFile +" where tr_head='"+m1_trhead+"' and tr_type='"+m1_trtype+"' and exp_amt="+m1_tramt;
			System.out.println("fp_fund_tfr_confirm_id-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nRet=rs.getString("id");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRet;
	}

	
	public @ResponseBody ArrayList<ArrayList<String>> getHeadPreflist(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {

			String selsus = nParaValue;
			String usrnm=s1.getAttribute("username").toString();
			String rolefmnNo = s1.getAttribute("roleFormationNo").toString();
			String roleSusNo = s1.getAttribute("roleSusNo").toString();
			String nUnty = getUnitType("", s1, "SUS_" + selsus);

			nParaValue = nUnty;
			ArrayList<ArrayList<String>> nUntDet = FindUnitList("", s1, "UNIT_" + nUnty + "_XXXXXXXXXX_" + selsus);
			String fmnNo = nUntDet.get(0).get(0);
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";                      

			if (nUnty.equals("-1")) {
				sql1 = " select distinct a.tr_head,a.head_desc,b.sus_no,b.chl_head_code from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " left join (select sus_no,chl_head_code,usr_id from fp.fp_tb_pref_head where sus_no='"+ selsus + "'";
				sql1 = sql1 + " ) b on a.tr_head=b.chl_head_code where a.tr_level='4'";
			} else {
				sql1 = " select distinct a.tr_head,a.head_desc,b.sus_no,b.chl_head_code from fp.fp_tb_head_mstr a";
				sql1 = sql1 + " left join (select sus_no,chl_head_code,usr_id from fp.fp_tb_pref_head where sus_no='"+ selsus + "'";
				sql1 = sql1 + " ) b on a.tr_head=b.chl_head_code where a.tr_level='4'";
				sql1 = sql1 + " and a.tr_head in (select distinct chl_head_code from fp.fp_tb_pref_head where sus_no='"
						+ roleSusNo + "')";
			}			
			
			sql1 = " select distinct a.tr_head,a.head_desc,b.sus_no,b.chl_head_code,b.psus_no from fp.fp_tb_head_mstr a ";
			sql1 = sql1 + " left join (select sus_no,chl_head_code,usr_id,psus_no from fp.fp_tb_pref_head ";
			sql1 = sql1 + " where sus_no='"+ roleSusNo + "' or psus_no='"+ roleSusNo + "') b on a.tr_head=b.chl_head_code ";
			sql1 = sql1 + " where a.tr_level='4' ";
			sql1 = sql1 + "  order by tr_head,head_desc";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			System.out.println("getHeadPreflist-"+sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("tr_head"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("chl_head_code"));
				list.add(rs.getString("psus_no"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getHeadAssignPreflist(String enc, HttpSession s1,
			String nParaValue, String userid2) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			nParaValue = "-1";
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nParaValue.equals("-1")) {
				sql1 = "select a.chl_head_code as head_code,p.head_desc as head_desc from fp.fp_tb_pref_head a,fp.fp_tb_head_mstr p";
				sql1 += "where (a.psus_no='"+userid2+"' or a.sus_no='"+userid2+"') and p.tr_head=a.chl_head_code";
			} else {
				//sql1 = "select a.chl_head_code as head_code,p.head_desc as head_desc from fp.fp_tb_pref_head a,fp.fp_tb_head_mstr p where a.usr_id ='"
						//+ userid2 + "' and p.tr_head=a.chl_head_code";
				sql1 = "select a.chl_head_code as head_code,p.head_desc as head_desc from fp.fp_tb_pref_head a,fp.fp_tb_head_mstr p";
				sql1 += "where (a.psus_no='"+userid2+"' or a.sus_no='"+userid2+"') and p.tr_head=a.chl_head_code";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			System.out.println("getHeadAssignPreflist-"+sql1);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("head_code"));
				list.add(rs.getString("head_desc"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getUnitPrefList(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			String selsus = nParaValue;

			String rolefmnNo = s1.getAttribute("roleFormationNo").toString();
			String roleSusNo = s1.getAttribute("roleSusNo").toString();
			String nUnty = getUnitType("", s1, "SUS_" + selsus);

			nParaValue = nUnty;
			ArrayList<ArrayList<String>> nUntDet = FindUnitList("", s1, "UNIT_" + nUnty + "_XXXXXXXXXX_" + selsus);
			String fmnNo = nUntDet.get(0).get(0);

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nParaValue.equals("-1")) {
				sql1 = "select a.sus_no,a.unit_name,b.sus_no as s_sus_no,b.chl_sus_no from fp.fp_tv_orbat_dtl a";
				sql1 = sql1
						+ " left join (select distinct sus_no,chl_sus_no,usr_id from fp.fp_tb_pref_unit where sus_no='"
						+ selsus + "') b on a.sus_no=b.chl_sus_no";

			} else {
				if (nParaValue.equals("5")) {
					sql1 = "select a.sus_no,a.unit_name,b.sus_no as s_sus_no,b.chl_sus_no from fp.fp_tv_orbat_dtl a";
					sql1 = sql1
							+ " left join (select distinct sus_no,chl_sus_no,usr_id from fp.fp_tb_pref_unit where sus_no='"
							+ selsus + "') b on a.sus_no=b.chl_sus_no";
					sql1 = sql1 + " where sus_no='" + selsus + "' and status_sus_no='Active' and hq_type::integer=5";
				} else {
					sql1 = "select a.sus_no,a.unit_name,b.sus_no as s_sus_no,b.chl_sus_no from fp.fp_tv_orbat_dtl a";
					sql1 = sql1
							+ " left join (select distinct sus_no,chl_sus_no,usr_id from fp.fp_tb_pref_unit where sus_no='"
							//+ selsus + "') b on a.sus_no=b.chl_sus_no where status_sus_no='Active' ";
							+ selsus + "') b on a.sus_no=b.chl_sus_no where status_sus_no='Active' ";
					/*if (nParaValue.equals("0")) {
						sql1 = sql1 + " status_sus_no='Active' and hq_type::integer >=0";
					}
					if (nParaValue.equals("1")) {
						sql1 = sql1 + " substring(form_code_control,1,1)='" + fmnNo.substring(0, 1)
								+ "' and status_sus_no='Active' and hq_type::integer >=1";
					}
					if (nParaValue.equals("2")) {
						sql1 = sql1 + " substring(form_code_control,1,3)='" + fmnNo.substring(0, 3)
								+ "' and status_sus_no='Active' and hq_type::integer >=2";
					}
					if (nParaValue.equals("3")) {
						sql1 = sql1 + " substring(form_code_control,1,6)='" + fmnNo.substring(0, 6)
								+ "' and status_sus_no='Active' and hq_type::integer >=3";
					}
					if (nParaValue.equals("4")) {
						sql1 = sql1 + " substring(form_code_control,1,10)='" + fmnNo
								+ "' and status_sus_no='Active' and hq_type::integer >=4";
					}*/
					sql1 = sql1 + " and arm_code<>'4900'"; 
					sql1 = sql1 + " order by hq_type,form_code_control,sus_no";
				}
			}
			//System.out.println("getUnitPrefList-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				list.add(rs.getString("s_sus_no"));
				list.add(rs.getString("chl_sus_no"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getUnitAssignPreflist(String enc, HttpSession s1,
			String nParaValue, String nParaValue1, String userid2) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			String selsus = nParaValue;
			String nUnty = getUnitType("", s1, "SUS_" + selsus);

			nParaValue = nUnty;
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			if (nParaValue.equals("-1")) {
				sql1 = "select p.unit_name as unit_name,a.chl_sus_no as sus_no from fp.fp_tb_pref_unit a,fp.fp_tv_orbat_dtl p where a.usr_id ='"
						+ userid2 + "' and a.sus_no='" + selsus + "' and p.sus_no=a.chl_sus_no";
			} else {
				sql1 = "select p.unit_name as unit_name,a.chl_sus_no as sus_no from fp.fp_tb_pref_unit a,fp.fp_tv_orbat_dtl p where a.usr_id ='"
						+ userid2 + "' and a.sus_no='" + selsus + "'and p.sus_no=a.chl_sus_no";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("unit_name"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody String FindDomainList(String enc, HttpSession s1, String nParaValue) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		String roleid = s1.getAttribute("roleid").toString();
		String nRet = "";
		try {
			nParaValue = nParaValue.toUpperCase();
			String[] nPara = nParaValue.split("_");
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select hq_type from fp.fp_tv_orbat_dtl ";
			if (nPara[0].equalsIgnoreCase("FMN")) {
				sql1 = sql1 + " where form_code_control='" + nPara[1] + "' and hq_type::integer<5";
			} else if (nPara[0].equalsIgnoreCase("SUS")) {
				sql1 = sql1 + " where sus_no='" + nPara[1] + "' and hq_type::integer=5";
			} else if (nPara[0].equalsIgnoreCase("UNIT")) {
				sql1 = sql1 + " where unit_name like '%" + nPara[1] + "%' and hq_type::integer=5";
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				nRet = "";
			} else {
				while (rs.next()) {
					nRet = rs.getString("hq_type");
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRet;
	}

	public List<String> getAllFormationList(String a1, String a2) {
		//System.out.println("getAllFormationList-"+a1+","+a2);
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		String a3 = a2.substring(0, 1);

		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			if (a3.equals("X")) {
				if (a2.equals("X000000000") && a1.equalsIgnoreCase("5")) {
					sq1 = "SELECT sus_no,unit_name,form_code_control FROM fp.fp_tv_orbat_dtl where hq_type::integer<=4 order by hq_type,form_code_control,unit_name";
				} else {
					sq1 = "SELECT sus_no,unit_name,form_code_control FROM fp.fp_tv_orbat_dtl where hq_type=? order by hq_type,form_code_control,unit_name";
				}				
			} else {
				sq1 = "SELECT sus_no,unit_name,form_code_control FROM fp.fp_tv_orbat_dtl where hq_type=? and substring(form_code_control,1,1)='"
						+ a3 + "' order by hq_type,form_code_control,unit_name";
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);
			if (a3.equals("X")) {
				if (a2.equals("X000000000") && a1.equalsIgnoreCase("5")) {
					
				} else {
					stmt.setString(1, a1);		
				}
			} else {
				stmt.setString(1, a1);
			}
			
			//System.out.println("getAllFormationList-"+stmt.toString());			
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("unit_name");
				str1 = str1 + ":" + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getAllFormationListAuto(String a1) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "SELECT sus_no,unit_name,form_code_control FROM fp.fp_tv_orbat_dtl where sus_no=? order by hq_type,form_code_control,unit_name";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, a1);
			//System.out.println("getAllFormationListAuto-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = rs.getString("unit_name");
				str1 = str1 + ":" + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getSusIncrList(String a1,String a2) {
		//System.out.println("getSusIncrList-"+a1+","+a2);
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String chkdg="ABCD";
			String flag;
			String finalsus;
			String datap="";
			String aa=a2.substring(0, 1);
			if (chkdg.contains(aa)) {
				if (a2.indexOf("XNX")>0) {
					sq1 = "SELECT coalesce(max(substring(sus_no,2,6))::numeric+1,1) as totsus FROM fp.fp_tb_norbat_dtl WHERE substring(SUS_NO,1,2)='"+aa+"0'";
					flag=aa;
				} else {
					sq1 = "SELECT  count(*)+1 as totsus FROM fp.fp_tb_norbat_dtl WHERE sus_no like '"+a2.substring(0,7)+"%'";
					flag="A";
				}				
			} else {
				sq1 = "SELECT count(*)+1 as totsus FROM fp.fp_tv_orbat_dtl WHERE sus_no like '%"+a2.substring(1,7)+"%'";
				flag="N";
			}
			PreparedStatement stmt = conn.prepareStatement(sq1);
			//System.out.println("getSusIncrList-"+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			if (rs.next()) {
				str1 = rs.getString("totsus");
			} else {
				str1 = "1";
			}
			if (str1.equals(null) || str1.equals("") || str1.length() <= 0) {
				str1 = "1";
			}
			int data=Integer.parseInt(str1);
			if(data>=100000){
				datap="";
			}else if(data>=10000 && data<100000){
				datap="0";
			}else if(data>=10000 && data<10000){
				datap="00";
			}else if(data>=100 && data<1000){
				datap="000";
			}else if(data>=10 && data<100){
				datap="0000";
			}else if(data<10){
				datap="00000";
			} 
			
			char str1a = (char) ((char) data+64);
			if (flag.equals("A")) {								
				finalsus=a2.substring(0,7)+str1a;
			} else if (flag.equals("N")) {
				finalsus=str1a+a2.substring(1,8);
			} else {
				finalsus=aa+datap+str1;
			}
			if (Integer.parseInt(str1)>25) {
				finalsus="E";
			} else {
				Boolean nchk=true;
				while(nchk) {		
					str1=(Integer.parseInt(str1)+1)+"";	
					String sq1d="select * from fp.fp_tb_norbat_dtl where sus_no='"+finalsus+"'";
					PreparedStatement stmtd = conn.prepareStatement(sq1d);
					ResultSet rsd = stmtd.executeQuery();
					if (rsd.next()) {					
						data=Integer.parseInt(str1);
						if(data>=100000){
							datap="";
						}else if(data>=10000 && data<100000){
							datap="0";
						}else if(data>=10000 && data<10000){
							datap="00";
						}else if(data>=100 && data<1000){
							datap="000";
						}else if(data>=10 && data<100){
							datap="0000";
						}else if(data<10){
							datap="00000";
						} 
						str1a = (char) ((char) data+64);
						if (flag.equals("A")) {								
							finalsus=a2.substring(0,7)+str1a;
						} else if (flag.equals("N")) {
							finalsus=str1a+a2.substring(1,8);
						} else {
							finalsus=aa+datap+str1;
						}
					} else {
						nchk=false;
					}
					rsd.close();
					stmtd.close();
					System.out.println("data-str1-"+str1);
					if (Integer.parseInt(str1)>25) {
						finalsus="E";
						break;
					}
				}
			}
			
			l.add(finalsus);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public ArrayList<ArrayList<String>> getSearchPrj(String fp_finYr, String est_type) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ac.id,est_type,dv.label est_label,to_char(date_from,'dd-mm-yyyy') as date_from,to_char(date_to,'dd-mm-yyyy') as date_to,fin_year,status from fp.fp_tb_adm_control ac join\r\n"
					+ "fp.fp_domain_values dv on ac.est_type = dv.codevalue";

			stmt = conn.prepareStatement(q);
			ResultSet rs = stmt.executeQuery();

			int idd = 0;
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				idd = rs.getInt("id");
				list.add(rs.getString("est_label"));
				list.add(rs.getString("date_from"));
				list.add(rs.getString("date_to"));
				list.add(rs.getString("fin_year"));
				list.add(rs.getString("status"));
				list.add(rs.getString("est_type"));
				String f = "";
				String Update = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData(" + idd + ",'"
						+ rs.getString("est_label") + "','" + rs.getString("date_from") + "','"
						+ rs.getString("date_to") + "','" + rs.getString("fin_year") + "','" + rs.getString("status")
						+ "')}else{ return false;}\"";
				f = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";

				list.add(f);
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

	public String nGetAdmFinYear(String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;

		String str1 = "";
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select est_type,date_from,date_to,fin_year,status from fp.fp_tb_adm_control where est_type='" + nPara
					+ "' and status='1'";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				str1 = rs.getString("fin_year");
			} else {
				str1 = "XXXX";
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str1;
	}

	public @ResponseBody ArrayList<ArrayList<String>> FindProjList(String enc, HttpSession s1, String rolesus) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String m1_tryear = nGetAdmFinYear("CPY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		try {
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select a.tr_head,b.head_desc,a.proj_amt,a.remarks from fp.fp_tb_proj_detl" + fileYr
					+ " a inner join fp.fp_tb_head_mstr b on b.tr_head=a.tr_head where a.sus_no='" + rolesus + "'";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("tr_head"));
					list.add(rs.getString("head_desc"));
					list.add(rs.getString("proj_amt"));
					list.add(rs.getString("remarks"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getProjectionList(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String m1_tryear = nGetAdmFinYear("CPY");
		String fileYr = m1_tryear.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(m1_tryear.substring(2, 4)) - 1) + "";

		try {
			String selsus = nParaValue;
			String rolefmnNo = s1.getAttribute("roleFormationNo").toString();
			String roleSusNo = s1.getAttribute("roleSusNo").toString();
			String nUnty = getUnitType("", s1, "SUS_" + selsus);

			nParaValue = nUnty;
			ArrayList<ArrayList<String>> nUntDet = FindUnitList("", s1, "UNIT_" + nUnty + "_XXXXXXXXXX_" + selsus);
			String fmnNo = nUntDet.get(0).get(0);

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";

			if (nUnty.equals("-1")) {
				sql1 = "select a.tr_head,b.head_desc,a.proj_amt,a.remarks from fp.fp_tb_proj_detl" + fileYr
						+ " a inner join fp.fp_tb_head_mstr b on b.tr_head=a.tr_head where a.sus_no='" + selsus + "'";
			} else {
				sql1 = "select a.tr_head,b.head_desc,a.proj_amt,a.remarks from fp.fp_tb_proj_detl" + fileYr
						+ " a inner join fp.fp_tb_head_mstr b on b.tr_head=a.tr_head where a.sus_no='" + selsus + "'";
			}
			sql1 = sql1 + "  order by a.tr_head,b.head_desc";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("tr_head"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("proj_amt"));
				list.add(rs.getString("remarks"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> FindFundList(String enc, HttpSession s1, String rolesus,
			String finYr) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String fileYr = finYr.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(finYr.substring(2, 4)) + 1) + "";
		try {
			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";
			sql1 = " select a.tr_head_to,b.head_desc,a.recd_amt,to_char(a.period::date,'dd-mm-yyyy') as period,to_remarks from fp.fp_tb_trans_detl"
					+ fileYr + " a inner join fp.fp_tb_head_mstr b on b.tr_head=a.tr_head_to where a.to_sus_no like '"
					+ rolesus + "%' and a.tr_type='FND' order by a.period::date desc,tr_head_to";
			System.out.println("FindFundList-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("tr_head_to"));
					list.add(rs.getString("head_desc"));
					list.add(rs.getString("recd_amt"));
					list.add(rs.getString("period"));
					list.add(rs.getString("to_remarks"));
					if (enc.equalsIgnoreCase("")) {
						li.add(list);
					} else {
						if (list.size() != 0) {
							List<String> FList = fp1_Dao.getMNHEncList(list, s1);
							li.add((ArrayList<String>) FList);
						} else {
							li.add(list);
						}
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody ArrayList<ArrayList<String>> getFundYrList(String enc, HttpSession s1, String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		String fileYr = nParaValue.substring(2, 4);
		String fileYrPrv = (Integer.parseInt(nParaValue.substring(2, 4)) + 1) + "";
		try {
			String rolefmnNo = s1.getAttribute("roleFormationNo").toString();
			String roleSusNo = s1.getAttribute("roleSusNo").toString();

			Connection conn = null;
			conn = dataSource.getConnection();
			String sql1 = "";

			sql1 = " select a.tr_head_to,b.head_desc,a.recd_amt,a.period::Date,a.to_remarks from fp.fp_tb_trans_detl" + fileYr
					+ " a inner join fp.fp_tb_head_mstr b on b.tr_head=a.tr_head_to where a.to_sus_no='" + roleSusNo
					+ "'";
			System.out.println("getFundYrList-"+sql1);
			PreparedStatement stmt = conn.prepareStatement(sql1);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("tr_head_to"));
				list.add(rs.getString("head_desc"));
				list.add(rs.getString("recd_amt"));
				list.add(rs.getString("period"));
				list.add(rs.getString("to_remarks"));
				if (enc.equalsIgnoreCase("")) {
					li.add(list);
				} else {
					if (list.size() != 0) {
						List<String> FList = fp1_Dao.getMNHEncList(list, s1);
						li.add((ArrayList<String>) FList);
					} else {
						li.add(list);
					}
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public @ResponseBody boolean checkHeadBuglist(String sus_no, String head) {
		boolean isExists = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "";
			conn = dataSource.getConnection();
			sql = "select a.sus_no from fp.fp_tb_pref_head a where a.sus_no=? and a.chl_head_code=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, head);

			ResultSet rs = stmt.executeQuery();
			isExists = rs.next();

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			isExists = false;
		}

		return isExists;
	}

	public @ResponseBody boolean checkBudgetHead(String sus_no, String head) {
		boolean isExists = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = dataSource.getConnection();
			String sql = "select a.sus_no from fp.fp_tb_pref_head a where a.sus_no=? and a.chl_head_code=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, head);

			ResultSet rs = stmt.executeQuery();
			isExists = rs.next();

			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			isExists = false;
		} catch (Exception e) {
			isExists = false;
		}
		return isExists;
	}

	public @ResponseBody boolean checkBudgetHolder(String sus_no, String chl_sus_no) {
		boolean isExists = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = dataSource.getConnection();
			String sql = "select a.sus_no from fp.fp_tb_pref_unit a where a.sus_no=? and a.chl_sus_no=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, chl_sus_no);

			ResultSet rs = stmt.executeQuery();
			isExists = rs.next();

			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			isExists = false;
		}
		catch (Exception e) {
			isExists = false;
		}
		return isExists;
	}
	
	public @ResponseBody boolean checkBudgetHolderWithHead(String sus_no, String chl_sus_no,String head) {
		boolean isExists = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = dataSource.getConnection();
			//String sql = "select p.sus_no from fp.fp_tb_pref_unit p join fp.fp_tb_pref_head h on p.sus_no = h.sus_no where p.sus_no = ? and p.chl_sus_no=? and h.chl_head_code=?";
			String sql = "select sus_no from fp.fp_tb_pref_head  where psus_no = ? and sus_no=? and chl_head_code=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setString(2, chl_sus_no);
			stmt.setString(3, head);
			ResultSet rs = stmt.executeQuery();
			isExists = rs.next();

			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			isExists = false;
		}
		catch (Exception e) {
			isExists = false;
		}
		return isExists;
	}
	
	public ArrayList<ArrayList<String>> getAllBudgetHolderWithHead_old(String sus_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {

			conn = dataSource.getConnection();
			String sql = "select distinct p.sus_no,p.chl_sus_no,h.chl_head_code from fp.fp_tb_pref_unit p join fp.fp_tb_pref_head h on p.chl_sus_no = h.sus_no where p.sus_no = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("sus_no"));
				list.add(rs.getString("chl_sus_no"));
				list.add(rs.getString("chl_head_code"));
				li.add(list);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			li = null;
		} catch (Exception e) {
			li = null;
		}
		return li;
	}
	
	public ArrayList<String> getAllBudgetHolderWithHead(String sus_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ArrayList<String> li = new ArrayList<String>();
		try {

			conn = dataSource.getConnection();
			String sql = "select distinct sus_no||'-'||chl_head_code sus_hd_code from fp.fp_tb_pref_head  where psus_no=? order by 1";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				li.add(rs.getString("sus_hd_code"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			li = null;
		} catch (Exception e) {
			li = null;
		}
		return li;
	}
	
	public ArrayList<ArrayList<String>> getAllBranchList() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		try {
			conn = dataSource.getConnection();
			String sql = "select label as name,codevalue as code from fp.fp_domain_values where domainid='ESTBRANCH' order by 1";
			stmt = conn.prepareStatement(sql);
			System.out.println("Query "+stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("er");
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("code"));
				list.add(rs.getString("name"));
				li.add(list);             
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("er "+e);
			li = null;
		} catch (Exception e) {
			System.out.println("er "+e);
			li = null;
		}
		System.out.println("len "+li.size());
		return li;
	}
	
	
	public void refeshMViews(String nPara) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			//String sql = "call nRefPrefHead()";
			String sql = "update fp.fp_tb_pref_head set usr_id=usr_id where id=(select max(id) from  fp.fp_tb_pref_head)";
			stmt = conn.prepareStatement(sql);
			System.out.println("Query "+stmt.toString() + " Executed." );
			stmt.executeUpdate();			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("er "+e);
		} catch (Exception e) {
			System.out.println("er "+e);
		}
		return;
	}
	
	

	public String nGetUnitNodal(String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		String str1 = "";
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select name_nodal_officer from logininformation where username='" + nPara + "'";
			PreparedStatement stmt = conn.prepareStatement(sq1);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				str1 = rs.getString("name_nodal_officer");
			} else {
				str1 = "";
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str1;
	}
	
	public List<Map<String, Object>> getExpFundsData(String chead,String csus, HttpSession session) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q="";
		String qry="";
		String cfinyr=this.nGetAdmFinYear("CFY").substring(2, 4);
		try{	  
			conn = dataSource.getConnection();					
			PreparedStatement stmt =null;
			q =" select a.fr_sus_no,b.unit_name as fr_sus_name,a.tr_head_to,c.head_desc ,a.fndamt,a.fnd_date,b.hq_type from fp.fp_tv_fund_detl"+cfinyr+" a "; 
			q +=" left join fp.fp_tv_orbat_dtl b on a.fr_sus_no=b.sus_no"; 
			q +=" left join fp.fp_tv_head_dtl c on a.tr_head_to=c.tr_head"; 
			q +=" where a.fndamt<>0 and a.to_sus_no='"+csus+"' and tr_head_to='"+chead+"'"; 
			q +=" order by a.tr_head_to,b.hq_type::integer,a.fr_sus_no";
		    stmt=conn.prepareStatement(q);   	
		    //stmt.setString(1, csus);
		    //stmt.setString(2, chead);
		    System.out.println("getExpFundsData-"+stmt.toString());	
		    ResultSet rs = stmt.executeQuery();      
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