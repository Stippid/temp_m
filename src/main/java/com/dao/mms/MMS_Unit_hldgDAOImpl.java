package com.dao.mms;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.MMS_TB_REGN_MSTR_DETL;
import com.persistance.util.HibernateUtil;

public class MMS_Unit_hldgDAOImpl implements MMS_Unit_hldgDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getRegdFileName(String nPara) {
		Connection conn = null;
		String fileName = "";
		try {
			conn = dataSource.getConnection();

			PreparedStatement stmt = null;

			String sql1 = "";

			sql1 = "select versionno  as filecode from mms_domain_values where codevalue=?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, nPara);

			ResultSet rs = stmt.executeQuery();
			String fn = "";

			if (rs.next()) {
				fn = rs.getString("filecode");
			}

			rs.close();
			stmt.close();
			conn.close();

			if (fn.equalsIgnoreCase("1")) {
				fileName = "mms_tb_regn_mstr_detl";
			}

			if (fn.equalsIgnoreCase("2")) {
				fileName = "mms_tb_regn_oth_mstr";
			}

			if (fn.equalsIgnoreCase("3")) {
				fileName = "mms_tb_depot_regn_mstr_detl";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	//changes by mitesh(19-02-2025)
	@Override
	public ArrayList<ArrayList<String>> UnitMcrList(String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			/*
			 * nrSql =
			 * "select distinct f.prf_group,i.item_type as item_nomen,m.nomen,z.* from (";
			 * nrSql = nrSql +
			 * " select (case when p.sus_no is null then q.sus_no else p.sus_no end) as sus_no,"
			 * ; nrSql = nrSql +
			 * " (case when p.prf_code is null then q.prf_code else p.prf_code end) as prf_code,"
			 * ; nrSql = nrSql +
			 * " (case when p.item_code is null then q.item_code else p.item_code end) as Item_code,"
			 * ; nrSql = nrSql +
			 * " (case when p.type_of_eqpt is null then q.type_of_eqpt else p.type_of_eqpt end) as type_of_eqpt,"
			 * ; nrSql = nrSql +
			 * " p.census_no,coalesce(q.totue,0) as totue,coalesce(p.uh,0) as totuh,coalesce(p.ss,0) as totss,coalesce(p.ls,0) as totls,coalesce(p.ac,0) as totac,coalesce(p.un,0) as totun,coalesce(p.uwwr,0) as totuwwr,"
			 * ; nrSql = nrSql +
			 * " coalesce(p.trss,0) as tottrss,coalesce(p.etsr,0) as totetsr,coalesce(p.othr,0) as totothr,p.upd_date from ("
			 * ; nrSql = nrSql +
			 * " select p.sus_no,m.prf_code,m.item_code,p.census_no,m.nomen,p.type_of_eqpt,p.uh,p.ss,p.ls,p.ac,p.un,p.uwwr,p.trss,p.etsr,p.othr,p.upd_date from ("
			 * ; nrSql = nrSql + " select a.sus_no,a.census_no,a.type_of_eqpt,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'A0' then a.tothol else 0 end) as uh,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'A5' then a.tothol else 0 end) as ss,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'A14' then a.tothol else 0 end) as ls,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'A16' then a.tothol else 0 end) as ac,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'A17' then a.tothol else 0 end) as un,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'R0' then a.tothol else 0 end) as uwwr,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'R4' then a.tothol else 0 end) as trss,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'R3' then a.tothol else 0 end) as etsr,"; nrSql =
			 * nrSql +
			 * " (case a.type_of_hldg when 'R5' then a.tothol else 0 end) as othr,upd_date";
			 * nrSql = nrSql +
			 * " from mms_tv_regn_mstr a where a.sus_no=?) p left join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no) p"
			 * ; nrSql = nrSql + " full outer join"; nrSql = nrSql +
			 * " (select p1.sus_no,p1.prf_code,p1.item_code,'' as census_no,'' as nomen,p1.we_pe as type_of_eqpt,sum(p1.totue) as totue from"
			 * ; nrSql = nrSql +
			 * " (select sus_no,prf_group_code as prf_code,item_code,(case when upper(type)='CES' then '2' else '1' end) as we_pe,total_ue_qty as totue"
			 * ; nrSql = nrSql +
			 * " from mms_ue_mview  where sus_no= ?) p1 group by p1.sus_no,p1.prf_code,p1.item_code,p1.we_pe"
			 * ; nrSql = nrSql +
			 * " ) q  on p.sus_no=q.sus_no and p.prf_code=q.prf_code and p.item_code=q.item_code"
			 * ; nrSql = nrSql +
			 * " where not (q.totue=0 and p.uh=0 and p.ss = 0 and p.ls =0 and p.ac=0 and p.un=0 and p.uwwr=0 and p.trss=0 and p.etsr=0 and p.othr=0)) z"
			 * ; nrSql = nrSql +
			 * " left join cue_tb_miso_prf_group_mst f on z.prf_code=f.prf_group_code";
			 * nrSql = nrSql +
			 * " left join mms_tb_mlccs_mstr_detl m on z.census_no=m.census_no"; nrSql =
			 * nrSql + " left join cue_tb_miso_mms_item_mstr i on z.item_code=i.item_code";
			 * nrSql = nrSql + " order by z.sus_no,f.prf_group,i.item_type,m.nomen"; nrSql =
			 * nrSql + " ( select a.sus_no,a.census_no,a.type_of_eqpt,"; nrSql = nrSql +
			 * " (case a.type_of_hldg when 'A0' then a.tothol else 0 end) as uh,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'A5' then a.tothol else 0 end) as ss,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'A14' then a.tothol else 0 end) as ls,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'A16' then a.tothol else 0 end) as ac,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'A17' then a.tothol else 0 end) as un,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'R0' then a.tothol else 0 end) as uwwr,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'R4' then a.tothol else 0 end) as trss,"; nrSql =
			 * nrSql + " (case a.type_of_hldg when 'R3' then a.tothol else 0 end) as etsr,";
			 * nrSql = nrSql +
			 * " (case a.type_of_hldg when 'R5' then a.tothol else 0 end) as othr,upd_date";
			 * nrSql = nrSql + " from mms_tv_regn_mstr a where a.sus_no=? ) p";
			 */

			/*
			 * nrSql =
			 * "select distinct f.prf_group,i.item_type as item_nomen,m.nomen,z.* from (";
			 * nrSql = nrSql +
			 * " select (case when p.sus_no is null then q.sus_no else p.sus_no end) as sus_no,"
			 * ; nrSql = nrSql +
			 * " (case when p.prf_code is null then q.prf_code else p.prf_code end) as prf_code,"
			 * ; nrSql = nrSql +
			 * " (case when p.item_code is null then q.item_code else p.item_code end) as Item_code,"
			 * ; nrSql = nrSql +
			 * " (case when p.type_of_eqpt is null then q.type_of_eqpt else p.type_of_eqpt end) as type_of_eqpt,"
			 * ; nrSql = nrSql +
			 * " p.census_no,coalesce(q.totue,0) as totue,coalesce(p.uh,0) as totuh,coalesce(p.ss,0) as totss,coalesce(p.ls,0) as totls,coalesce(p.ac,0) as totac,coalesce(p.un,0) as totun,coalesce(p.uwwr,0) as totuwwr,"
			 * ; nrSql = nrSql +
			 * " coalesce(p.trss,0) as tottrss,coalesce(p.etsr,0) as totetsr,coalesce(p.othr,0) as totothr,p.upd_date from ("
			 * ; nrSql = nrSql +
			 * " select p.sus_no,m.prf_code,m.item_code,p.census_no,m.nomen,p.type_of_eqpt,p.uh,p.ss,p.ls,p.ac,p.un,p.uwwr,p.trss,p.etsr,p.othr,p.upd_date from "
			 * ;
			 *
			 * //UPDATE 18-11-2022 DUBLICATION OF CENSUS NO nrSql = nrSql +
			 * "(select b.sus_no,b.census_no,b.type_of_eqpt,sum(b.uh) as uh,\r\n" +
			 * "sum(b.ss) as ss,\r\n" + "sum(b.ls) as ls,\r\n" + "sum(b.ac) as ac,\r\n" +
			 * "sum(b.un) as un,\r\n" + "sum(b.uwwr) as uwwr,\r\n" +
			 * "sum(b.trss) as trss,\r\n" + "sum(b.etsr) as etsr,\r\n" +
			 * "sum(b.othr) as othr,\r\n" + "max(b.data_app_date) as upd_date\r\n" +
			 * " from \r\n" + " (" + "select a.census_no,\r\n" + "	a.sus_no,\r\n" +
			 * "	a.type_of_eqpt,\r\n" +
			 * "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n" +
			 * "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n" +
			 * "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n" +
			 * "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n" +
			 * "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n" +
			 * "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n" +
			 * "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n" +
			 * "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n" +
			 * "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr,\r\n" +
			 * "	data_app_date\r\n" + "	from mms_tb_regn_mstr_detl a \r\n" +
			 * "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
			 * + "	where a.sus_no = ? and a.op_status='1'\r\n" + "union All\r\n" +
			 * "select a.census_no,\r\n" + "	a.sus_no,\r\n" + "	a.type_of_eqpt,\r\n" +
			 * "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n" +
			 * "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n" +
			 * "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n" +
			 * "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n" +
			 * "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n" +
			 * "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n" +
			 * "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n" +
			 * "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n" +
			 * "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr,\r\n" +
			 * "	data_app_date\r\n" + "	from mms_tb_depot_regn_mstr_detl a \r\n" +
			 * "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
			 * + "	where a.sus_no = ? and a.op_status='1'\r\n" + "union All\r\n" +
			 * "select a.census_no,\r\n" + "	a.to_sus_no,\r\n" + "	a.type_of_eqpt,\r\n"
			 * + "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n" +
			 * "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n" +
			 * "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n" +
			 * "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n" +
			 * "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n" +
			 * "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n" +
			 * "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n" +
			 * "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n" +
			 * "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr,\r\n" +
			 * "	data_app_date\r\n" + "	from mms_tb_regn_oth_mstr a \r\n" +
			 * "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
			 * + "	where a.to_sus_no = ? and a.op_status='1'" +
			 * ") as b group by 1,2,3 ) p";
			 *
			 *
			 *
			 *
			 *
			 *
			 * nrSql = nrSql +
			 * " left join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no) p"; nrSql =
			 * nrSql + " full outer join"; nrSql = nrSql +
			 * " (select p1.sus_no,p1.prf_code,p1.item_code,'' as census_no,'' as nomen,p1.we_pe as type_of_eqpt,sum(p1.totue) as totue from"
			 * ; nrSql = nrSql +
			 * " (select sus_no,prf_group_code as prf_code,item_code,(case when upper(type)='CES' then '2' else '1' end) as we_pe,total_ue_qty as totue"
			 * ; nrSql = nrSql +
			 * " from mms_ue_mview  where sus_no= ?) p1 group by p1.sus_no,p1.prf_code,p1.item_code,p1.we_pe"
			 * ; nrSql = nrSql +
			 * " ) q  on p.sus_no=q.sus_no and p.prf_code=q.prf_code and p.item_code=q.item_code"
			 * ; nrSql = nrSql +
			 * " where not (q.totue=0 and p.uh=0 and p.ss = 0 and p.ls =0 and p.ac=0 and p.un=0 and p.uwwr=0 and p.trss=0 and p.etsr=0 and p.othr=0)) z"
			 * ; nrSql = nrSql +
			 * " left join cue_tb_miso_prf_group_mst f on z.prf_code=f.prf_group_code";
			 * nrSql = nrSql +
			 * " left join mms_tb_mlccs_mstr_detl m on z.census_no=m.census_no"; nrSql =
			 * nrSql + " left join cue_tb_miso_mms_item_mstr i on z.item_code=i.item_code";
			 * nrSql = nrSql + " order by z.sus_no,f.prf_group,i.item_type,m.nomen";
			 */

			nrSql = "select distinct f.prf_group,i.item_type as item_nomen,m.nomen,z.* from (";
			nrSql = nrSql + " select (case when p.sus_no is null then q.sus_no else p.sus_no end) as sus_no,";
			nrSql = nrSql + " (case when p.prf_code is null then q.prf_code else p.prf_code end) as prf_code,";
			nrSql = nrSql + " (case when p.item_code is null then q.item_code else p.item_code end) as Item_code,";
			nrSql = nrSql
					+ " (case when p.type_of_eqpt is null then q.type_of_eqpt else p.type_of_eqpt end) as type_of_eqpt,";
			nrSql = nrSql
					+ " p.census_no,coalesce(p.uh,0) as totuh1,coalesce(p.uh,0) as totuh,coalesce(p.ss,0) as totss,coalesce(p.ls,0) as totls,coalesce(p.ac,0) as totac,coalesce(p.un,0) as totun,coalesce(p.uwwr,0) as totuwwr,";
			nrSql = nrSql
					+ " coalesce(p.trss,0) as tottrss,coalesce(p.etsr,0) as totetsr,coalesce(p.othr,0) as totothr from (";
			nrSql = nrSql
					+ " select p.sus_no,m.prf_code,m.item_code,p.census_no,m.nomen,p.type_of_eqpt,p.uh,p.ss,p.ls,p.ac,p.un,p.uwwr,p.trss,p.etsr,p.othr from ";

			nrSql = nrSql + "(select b.sus_no,b.census_no,b.type_of_eqpt,sum(b.uh) as uh,\r\n" + "sum(b.ss) as ss,\r\n"
					+ "sum(b.ls) as ls,\r\n" + "sum(b.ac) as ac,\r\n" + "sum(b.un) as un,\r\n"
					+ "sum(b.uwwr) as uwwr,\r\n" + "sum(b.trss) as trss,\r\n" + "sum(b.etsr) as etsr,\r\n"
					+ "sum(b.othr) as othr \r\n" + " from \r\n" + " (" + "select a.census_no,\r\n" + "	a.sus_no,\r\n"
					+ "	a.type_of_eqpt,\r\n" + "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n"
					+ "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n"
					+ "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n"
					+ "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n"
					+ "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n"
					+ "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n"
					+ "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n"
					+ "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n"
					+ "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr\r\n"
					+ "	from mms_tb_regn_mstr_detl a \r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "	where a.sus_no = ? and a.op_status='1'\r\n" + "union All\r\n" + "select a.census_no,\r\n"
					+ "	a.sus_no,\r\n" + "	a.type_of_eqpt,\r\n"
					+ "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n"
					+ "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n"
					+ "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n"
					+ "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n"
					+ "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n"
					+ "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n"
					+ "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n"
					+ "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n"
					+ "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr\r\n"
					+ "	from mms_tb_depot_regn_mstr_detl a \r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "	where a.sus_no = ? and a.op_status='1'\r\n" + "union All\r\n" + "select a.census_no,\r\n"
					+ "	a.to_sus_no,\r\n" + "	a.type_of_eqpt,\r\n"
					+ "	(case when a.type_of_hldg='A0' then 1 else 0 end) as uh ,\r\n"
					+ "	(case a.type_of_hldg when 'A5' then 1 else 0 end) as ss, \r\n"
					+ "	(case a.type_of_hldg when 'A14' then 1 else 0 end) as ls, \r\n"
					+ "	(case a.type_of_hldg when 'A16' then 1 else 0 end) as ac, \r\n"
					+ "	(case a.type_of_hldg when 'A17' then 1 else 0 end) as un, \r\n"
					+ "	(case a.type_of_hldg when 'R0' then 1 else 0 end) as uwwr, \r\n"
					+ "	(case a.type_of_hldg when 'R4' then 1 else 0 end) as trss, \r\n"
					+ "	(case a.type_of_hldg when 'R3' then 1 else 0 end) as etsr, \r\n"
					+ "	(case a.type_of_hldg when 'R5' then 1 else 0 end) as othr\r\n"
					+ "	from mms_tb_regn_oth_mstr a \r\n"
					+ "	LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "	where a.to_sus_no = ? and a.op_status='1'" + ") as b group by 1,2,3 ) p";

			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on p.census_no=m.census_no) p";
			nrSql = nrSql + " full outer join";
			nrSql = nrSql
					+ " (select p1.sus_no,p1.prf_code,p1.item_code,'' as census_no,'' as nomen,p1.we_pe as type_of_eqpt,sum(p1.totue) as totue from";
			nrSql = nrSql
					+ " (select sus_no,prf_group_code as prf_code,item_code,(case when upper(type)='CES' then '2' else '1' end) as we_pe,total_ue_qty as totue";
			nrSql = nrSql
					+ " from mms_ue_mview  where sus_no= ?) p1 group by p1.sus_no,p1.prf_code,p1.item_code,p1.we_pe";
			nrSql = nrSql + " ) q  on p.sus_no=q.sus_no and p.prf_code=q.prf_code and p.item_code=q.item_code";
			nrSql = nrSql
					+ " where not (q.totue=0 and p.uh=0 and p.ss = 0 and p.ls =0 and p.ac=0 and p.un=0 and p.uwwr=0 and p.trss=0 and p.etsr=0 and p.othr=0)) z";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst f on z.prf_code=f.prf_group_code";
			nrSql = nrSql + " left join mms_tb_mlccs_mstr_detl m on z.census_no=m.census_no";
			nrSql = nrSql + " left join cue_tb_miso_mms_item_mstr i on z.item_code=i.item_code";
			nrSql = nrSql + " order by z.sus_no,f.prf_group,i.item_type,m.nomen";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, nParaValue);
			stmt.setString(2, nParaValue);
			stmt.setString(3, nParaValue);
			stmt.setString(4, nParaValue);
			System.err.println("mcr-1--"+stmt);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sus_no")); // 0
					list.add(rs.getString("prf_group")); // 1
					list.add(rs.getString("totuh1")); // 2
					list.add(rs.getString("item_code")); // 3
					list.add(rs.getString("census_no")); // 4
					list.add(rs.getString("nomen")); // 5
					list.add(rs.getString("totuh")); // 6
					list.add(rs.getString("totss")); // 7
					list.add(rs.getString("totls")); // 8
					list.add(rs.getString("totac")); // 9
					list.add(rs.getString("totun")); // 10
					list.add(rs.getString("totuwwr")); // 11
					list.add(rs.getString("tottrss")); // 12
					list.add(rs.getString("totetsr")); // 13
					list.add(rs.getString("totothr")); // 14
					list.add(""); // 15
					// list.add(rs.getString("upd_date"));
					li.add(list);
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

	@Override
	public List<Map<String, Object>> getTrasctionUnitWiseList(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select coalesce(e.prf_group,'&nbsp;') as prf_group,d.census_no,d.nomen,\r\n"
					+ "	d.type_of_hldg_n, d.actv,\r\n" + "	TO_CHAR(d.tr_date, 'dd-mm-yyyy') as tr_date\r\n"
					+ " from ( \r\n"
					+ " select a.cur_prf_code as prf_code,a.cur_census_no as census_no,a.nomen,a.type_of_hldg_n, \r\n"
					+ " concat('&nbsp;<b>',a.regn_count,'</b>x Qty ',a.tran_type_n,' from ',a.unit_name) as actv,a.tr_date\r\n"
					+ " from mms_tv_regn_tr_log a \r\n" + " where a.cur_sus_no=? and a.cur_unit_status ='0'\r\n"
					+ " union all \r\n"
					+ " select a.cur_prf_code as prf_code,a.cur_census_no as census_no,a.nomen,a.type_of_hldg_n, \r\n"
					+ " concat('&nbsp;<b>',a.regn_count,'</b>x Qty ',a.tran_type_n,' to ',a.cur_unit) as actv,a.tr_date\r\n"
					+ " from mms_tv_regn_tr_log a \r\n" + " where a.from_sus_no=? and a.from_unit_status ='0' ) d \r\n"
					+ " left join cue_tb_miso_prf_group_mst e on d.prf_code=e.prf_group_code \r\n"
					+ " order by d.tr_date desc";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			stmt.setString(2, sus_no);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				// miso v4 (11-11-2022)
				for (int i = 1; i <= columnCount; i++) {
					if (rs.getObject(i) == null) {

						columns.put(metaData.getColumnLabel(i), "");
					} else {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
					}

				}

				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			return list;
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
	public String veryfyTransaction_MCR(String sus_no) {
		String msg = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String qry = "update mms_tb_regn_tr_log a set cur_unit_status = '1' where cur_sus_no = ? and cur_unit_status='0' ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, sus_no);
			stmt.executeUpdate();

			qry = "update mms_tb_regn_tr_log a set from_unit_status = '1' where from_sus_no = ? and from_unit_status='0' ";
			stmt = conn.prepareStatement(qry);
			stmt.setString(1, sus_no);
			stmt.executeUpdate();
			msg = "1";
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			msg = "0";

		}
		return msg;
	}

	// sana 17-11-2022
	@Override
	public String updateMisoReply(String miso_reply, int miso_reply_id, String username) {
		String msg = "";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String qry = "update mms_tb_unit_upload_document a set miso_reply = ? ,completion_by=?, dt_completion=localtimestamp where id= ? ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, miso_reply);

			stmt.setString(2, username);
			// stmt.setDate(3,dt_completion);
			stmt.setInt(3, miso_reply_id);
			stmt.executeUpdate();
			msg = "MISO Reply Successfully Submitted";
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			msg = "MISO Reply not Submitted";
		}
		return msg;
	}

	@Override
	public List<Map<String, Object>> getUnitLastUpdatedOn(String sus_no) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "SELECT to_char(created_on,'DD-MM-YYYY') as update_date,created_by FROM public.mms_tb_unit_upload_document where sus_no = ? order by id desc limit 1";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i).toString());
				}
				list.add(columns);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			return list;
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

	// sana 18-11-2022
	@Override
	public ArrayList<ArrayList<String>> unitCorrectCertilist(String susno, String mth, String yr) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			nrSql = "select d.sus_no,d.from_sus_no,d.prf_code,e.prf_group,d.census_no,d.nomen,d.type_of_hldg,d.type_of_hldg_n,";
			nrSql = nrSql + " d.actv,d.tr_id,d.mth,d.yr,d.tr_date,o.obsn1,o.tr_id as o_id \r\n from ( \r\n";
			nrSql = nrSql
					+ " select a.cur_sus_no as sus_no,a.from_sus_no,a.cur_prf_code as prf_code,a.cur_census_no as census_no,a.nomen,a.cur_hldg_ty as type_of_hldg,a.type_of_hldg_n, \r\n";
			nrSql = nrSql
					+ " concat('&nbsp;<b>',a.regn_count,'</b>x Qty ',a.tran_type_n,' from ',a.unit_name) as actv,a.tr_id,a.mth,a.yr,a.tr_date \r\n";
			nrSql = nrSql + " from mms_tv_regn_tr_log a \r\n";
			nrSql = nrSql + " where  a.yr=?  and  a.mth=?  and  a.cur_sus_no=? \r\n";
			nrSql = nrSql + " union all \r\n";
			nrSql = nrSql
					+ " select a.cur_sus_no as sus_no,a.from_sus_no,a.cur_prf_code as prf_code,a.cur_census_no as census_no,a.nomen,a.cur_hldg_ty as type_of_hldg,a.type_of_hldg_n, \r\n";
			nrSql = nrSql
					+ " concat('&nbsp;<b>',a.regn_count,'</b>x Qty ',a.tran_type_n,' to ',a.cur_unit) as actv,a.tr_id,a.mth,a.yr,a.tr_date \r\n";
			nrSql = nrSql + " from mms_tv_regn_tr_log a \r\n";
			nrSql = nrSql + " where  a.yr=?  and  a.mth=?  and  a.from_sus_no=?) d \r\n";
			nrSql = nrSql + " left join cue_tb_miso_prf_group_mst e on d.prf_code=e.prf_group_code \r\n";
			nrSql = nrSql
					+ " left join mms_tb_obsn_detl o on d.from_sus_no = o.sus_no and d.mth = o.mth::double precision and d.yr = o.yr::double precision and \r\n";
			nrSql = nrSql + " d.census_no = o.census_no and d.type_of_hldg = o.type_of_hldg \r\n";
			nrSql = nrSql + "  order by d.tr_date desc,d.tr_id desc \r\n";

			PreparedStatement stmt = conn.prepareStatement(nrSql);

			stmt.setDouble(1, Double.parseDouble(yr));
			stmt.setDouble(2, Double.parseDouble(mth));
			stmt.setString(3, susno);
			stmt.setDouble(4, Double.parseDouble(yr));
			stmt.setDouble(5, Double.parseDouble(mth));
			stmt.setString(6, susno);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("prf_group"));
				list.add(rs.getString("census_no"));
				list.add(rs.getString("nomen"));
				list.add(rs.getString("type_of_hldg_n"));
				list.add(rs.getString("actv"));
				list.add(rs.getString("tr_id"));
				list.add(rs.getString("mth"));
				list.add(rs.getString("yr"));
				list.add(rs.getString("type_of_hldg"));
				list.add(rs.getString("obsn1"));
				list.add(rs.getString("o_id"));
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

	// sana 18-11-2022
	@Override
	public ArrayList<ArrayList<String>> getUnitUploadedDocDetails(String sus_no, String month, String yr,
			String roleAccess) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "SELECT id,remark,TO_CHAR(created_on, 'dd-mm-yyyy') as created_on,coalesce(miso_reply,'') as miso_reply,TO_CHAR(dt_completion, 'dd-mm-yyyy') as dt_completion,completion_by \r\n"
					+ "FROM public.mms_tb_unit_upload_document \r\n" + "where sus_no = ? and \r\n"
					+ "cast(TO_CHAR(created_on, 'mm') as float) =? and\r\n"
					+ "cast(TO_CHAR(created_on, 'yyyy') as float) =?\r\n" + "and doc is not null\r\n" + "order by id";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt.setString(1, sus_no);
			stmt.setDouble(2, Double.parseDouble(month));
			stmt.setDouble(3, Double.parseDouble(yr));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("<a href='#' onclick='getDownloadUnitDoc(" + rs.getString("id")
				+ ")' class='btn btn-primary btn-sm'><i class='fa fa-download' aria-hidden='true'></i></a>");
				list.add(rs.getString("remark"));
				list.add(rs.getString("created_on"));
				list.add(rs.getString("dt_completion"));
				list.add(rs.getString("completion_by"));
				String miso_reply = rs.getString("miso_reply").toString();
				if (roleAccess.equalsIgnoreCase("MISO") & miso_reply.equals("")) {
					String input = "<textarea id='miso_reply" + rs.getString("id") + "' name='miso_reply"
							+ rs.getString("id")
							+ "' maxlength='250' placeholder='MISO Reply' class='form-control-sm form-control' rows='1' style='width:80%;display: inline;'>"
							+ "</textarea>&ensp;<button id='replyBtn' class='btn btn-success btn-sm' onclick='miso_reply("
							+ rs.getString("id") + ");' style='border-radius: 5px;vertical-align: top;'>Reply</button>";
					list.add(input);
				} else {
					list.add(rs.getString("miso_reply"));
				}
				li.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			return li;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return li;
	}

	@Override
	public List<String> getEqptData(String r1, String r2) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select depres_dur_year,barrel1_detl,service_status,barrel2_detl,barrel3_detl,barrel4_detl,spl_remarks,id "
					+ "from mms_tb_regn_mstr_detl where regn_seq_no=? and eqpt_regn_no=?";

			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, r1);
			stmt.setString(2, r2);
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("depres_dur_year");
				str1 = str1 + ":" + rs.getString("barrel1_detl");
				str1 = str1 + ":" + rs.getString("service_status");
				str1 = str1 + ":" + rs.getString("barrel2_detl");
				str1 = str1 + ":" + rs.getString("barrel3_detl");
				str1 = str1 + ":" + rs.getString("barrel4_detl");
				str1 = str1 + ":" + rs.getString("spl_remarks");
				str1 = str1 + ":" + rs.getString("id");
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

	@Override
	public Boolean ifExistEqptRegNo(String e, HttpSession s1) {
		String q = "from MMS_TB_REGN_MSTR_DETL where eqpt_regn_no=:eqpt_regn_no";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(q);
		query.setParameter("eqpt_regn_no", e);
		@SuppressWarnings("unchecked")
		List<MMS_TB_REGN_MSTR_DETL> list = query.list();
		tx.commit();
		session.close();

		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	///bisag 260523 v2 (query change by prasan sir (convert into ::integer))

	@Override
	public String RegdTransLog(String regnSeqno, String regnno, String nFSUSNo, String nFPRF, String nFCensus,
			String nFMNo, String nFHldType, String nFEqptType, String nOldval, String nTSUSNo, String nTMNo,
			String nTHldType, String nTEqptType, String nNewV, String nAuthNo, String nAuthDate, String nAuthType,
			String nOStatus, String nPara, String fname) {

		String[] tmpRegn = regnno.split(":");

		try {
			Connection conn = null;
			conn = dataSource.getConnection();
			String nrSql = "";

			nrSql = "insert into mms_tb_regn_tr_log(regn_seq_no, eqpt_regn_no,from_sus_no,from_prf_code,from_census_no,";
			nrSql = nrSql
					+ " from_material_no,from_hldg_ty,from_eqpt_ty,old_value,cur_sus_no,cur_prf_code,cur_census_no,";
			nrSql = nrSql
					+ " cur_material_no,cur_hldg_ty,cur_eqpt_ty,new_value,tr_date,tran_type,auth_type,auth_no,auth_date,";
			nrSql = nrSql + " regn_count,op_status,upload_file_name,from_unit_status,cur_unit_status) ";
			nrSql = nrSql + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,localtimestamp,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, regnSeqno);
			stmt.setString(2, regnno);
			stmt.setString(3, nFSUSNo);
			stmt.setString(4, nFPRF);
			stmt.setString(5, nFCensus);
			stmt.setString(6, nFMNo);
			stmt.setString(7, nFHldType);
			stmt.setString(8, nFEqptType);
			stmt.setString(9, nOldval);
			stmt.setString(10, nTSUSNo);
			stmt.setString(11, nFPRF);
			stmt.setString(12, nFCensus);
			stmt.setString(13, nTMNo);
			stmt.setString(14, nTHldType);
			stmt.setString(15, nTEqptType);
			stmt.setString(16, nNewV);
			stmt.setString(17, nPara);
			stmt.setString(18, nAuthType);
			stmt.setString(19, nAuthNo);
			stmt.setString(20, nAuthDate);
			stmt.setInt(21, tmpRegn.length);
			stmt.setString(22, nOStatus);
			stmt.setString(23, fname);
			stmt.setString(24, "0");
			stmt.setString(25, "0");

			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public List<String> mms_list_regn_no(String sus_no, String census_no, String type_of_hldg, String regn_seq_no) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		String qry = "";
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			String asus = "";

			if (type_of_hldg.equals("A0")) {
				asus = " a.sus_no";
			} else if (type_of_hldg.equals("A5")) {
				asus = " a.to_sus_no";
			} else if (type_of_hldg.equals("A14") || type_of_hldg.equals("A16")) {
				asus = " a.to_sus_no";
			} else {
				asus = " a.sus_no";
			}

			if (sus_no != "" && sus_no != null) {
				qry += "and " + asus + "=?";
			}

			if (regn_seq_no != "" && regn_seq_no != null) {
				qry += " and a.eqpt_regn_no LIKE ?";
			} else {
				if (census_no != "" && census_no != null) {
					qry += " and a.census_no=?";
				}
				if (!type_of_hldg.equalsIgnoreCase("ALL")) {
					qry += " and a.type_of_hldg=?";
				}
			}
			if (qry == "") {
				/*
				 * sql1 = "select "+asus+
				 * " as sus_no,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n," +
				 * "a.type_of_eqpt,t2.label as type_of_eqpt_n,a.regn_seq_no,a.eqpt_regn_no,t1.codevalue as type_of_hldg_n_code "
				 * + "from "+ getRegdFileName(type_of_hldg)+" a,"+ " mms_tb_mlccs_mstr_detl m,"+
				 * " mms_domain_values t1,"+ " mms_domain_values t2"+
				 * " where a.census_no=m.census_no"+
				 * " and (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')"+
				 * " and (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')";
				 */
				sql1 = "select " + asus
						+ " as sus_no,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,\r\n"
						+ "		a.type_of_eqpt,t2.label as type_of_eqpt_n,a.regn_seq_no,a.eqpt_regn_no,t1.codevalue as type_of_hldg_n_code,t3.label as au\r\n"
						+ "from " + getRegdFileName(type_of_hldg) + " a "
						+ "	inner join mms_tb_mlccs_mstr_detl m  on a.census_no=m.census_no\r\n"
						+ "	left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
						+ "	left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'\r\n"
						+ "	left join mms_domain_values t3 on m.au=t3.codevalue and t3.domainid='ACCOUNTINGUNITS'";
			} else {
				/*
				 * sql1 = "select "+asus+
				 * " as sus_no,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,a.type_of_eqpt,t2.label as type_of_eqpt_n,"
				 * + "a.regn_seq_no,a.eqpt_regn_no,t1.codevalue as type_of_hldg_n_code " +
				 * "from "+ getRegdFileName(type_of_hldg)+" a,"+ " mms_tb_mlccs_mstr_detl m,"+
				 * " mms_domain_values t1,"+ " mms_domain_values t2"+
				 * " where a.census_no=m.census_no"+
				 * " and (a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING')"+
				 * " and (a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT')"+qry;
				 */

				sql1 = "select " + asus
						+ " as sus_no,a.census_no,m.nomen,a.type_of_hldg,t1.label as type_of_hldg_n,\r\n"
						+ "		a.type_of_eqpt,t2.label as type_of_eqpt_n,a.regn_seq_no,a.eqpt_regn_no,t1.codevalue as type_of_hldg_n_code,t3.label as au\r\n"
						+ "from " + getRegdFileName(type_of_hldg) + " a "
						+ "	inner join mms_tb_mlccs_mstr_detl m  on a.census_no=m.census_no\r\n"
						+ "	left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
						+ "	left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'\r\n"
						+ "	left join mms_domain_values t3 on m.au=t3.codevalue and t3.domainid='ACCOUNTINGUNITS'"
						+ " where a.census_no != '' " + qry;
			}

			PreparedStatement stmt = conn.prepareStatement(sql1);
			if (qry != "") {
				int i = 0;
				if (sus_no != "" && sus_no != null) {
					i++;
					stmt.setString(i, sus_no);
				}
				if (regn_seq_no != "" && regn_seq_no != null) {
					i++;
					stmt.setString(i, "%" + regn_seq_no.toUpperCase() + "%");
				} else {
					if (census_no != "" && census_no != null) {
						i++;
						stmt.setString(i, census_no);
					}
					if (!type_of_hldg.equalsIgnoreCase("ALL")) {
						i++;
						stmt.setString(i, type_of_hldg);
					}
				}
			}
			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("census_no");
				str1 = str1 + ":" + rs.getString("nomen");
				str1 = str1 + ":" + rs.getString("type_of_hldg_n");
				str1 = str1 + ":" + rs.getString("type_of_eqpt_n");
				str1 = str1 + ":" + rs.getString("eqpt_regn_no");
				str1 = str1 + ":" + rs.getString("regn_seq_no");
				str1 = str1 + ":" + rs.getString("type_of_hldg_n_code");
				str1 = str1 + ":" + rs.getString("au");
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

	@Override
	public String mms_update_regnno(String regn_no, String regn_seq_no, String nFileName) {
		Connection conn = null;
		int rs = 0;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			nrSql = "update " + nFileName + " set eqpt_regn_no=? where regn_seq_no=?";
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, regn_no);
			stmt.setString(2, regn_seq_no);
			rs = stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs > 0) {
			return "updated Successfully";
		} else {
			return "update not Successfully";
		}
	}

	@Override
	public String RegdTransUpdate(String regnSeqno, String regnno, String nFSUSNo, String nTSUSNo, String nTHldType,
			String nTEqptType, String nOStatus, String nTfrStatus, String nUpBy, String nPara, String nFileName) {
		String[] tmpRegn = regnSeqno.split(":");
		try {
			Connection conn = null;
			conn = dataSource.getConnection();

			String f_sus_no = "";

			if (nFileName.equals("mms_tb_regn_mstr_detl") || nFileName.equals("mms_tb_depot_regn_mstr_detl")) {
				f_sus_no = "sus_no";
			} else if (nFileName.equals("mms_tb_regn_oth_mstr")) {
				f_sus_no = "iv_sus_no";
			}

			String nrSql = "";
			nrSql = "update " + nFileName + " ";
			nrSql += "set " + f_sus_no + "=?,";
			nrSql += "type_of_hldg=?,";
			nrSql += "type_of_eqpt=?,";
			nrSql += "from_sus_no=?,";
			nrSql += "to_sus_no=?,";
			nrSql += "op_status=?,";
			nrSql += "tfr_status=?,";
			nrSql += "from_tr_date=localtimestamp,";
			nrSql += "to_tr_date=localtimestamp,";
			nrSql += "data_upd_by=?,";
			nrSql += "data_upd_date=localtimestamp ";
			nrSql += "where regn_seq_no=?";

			for (int i = 0; i < tmpRegn.length; i++) {
				PreparedStatement stmt = conn.prepareStatement(nrSql);
				String nCnd = tmpRegn[i];
				stmt.setString(1, nTSUSNo);
				stmt.setString(2, nTHldType);
				stmt.setString(3, nTEqptType);
				stmt.setString(4, nFSUSNo);
				stmt.setString(5, nTSUSNo);
				stmt.setString(6, nOStatus);
				stmt.setString(7, nTfrStatus);
				stmt.setString(8, nUpBy);
				stmt.setString(9, nCnd);
				stmt.executeUpdate();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<ArrayList<String>> mms_ue_uh_summ(String nSUSNo, String nPara) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String Sql = "";
			Sql = "select \r\n" + "	pr.prf_group,\r\n" + "	i.item_type as item_nomen,\r\n"
					+ "	t1.label as type_of_hldg_n,\r\n" + "	t2.label as type_of_eqpt_n,\r\n"
					+ "	cast(r.totue as varchar) as totue,\r\n" + "	r.totuh,\r\n"
					+ "	TO_CHAR(r.upd_date, 'dd-mm-yyyy') as upd_date \r\n" + // r.upd_date
					"from (\r\n" + "	select p.prf_code,\r\n" + "			p.item_code,\r\n"
					+ "			'A0' as type_of_hldg,\r\n" + "			p.type_of_eqpt,\r\n"
					+ "			sum(ueqty) as totue,\r\n" + "			sum(uhqty) as totuh,\r\n"
					+ "			max(upd_date) as upd_date \r\n" + "	from (select 	b.prf_code,\r\n"
					+ "					b.item_code,\r\n" + "					a.census_no,\r\n"
					+ "					a.type_of_eqpt,\r\n" + "					0 as ueqty,\r\n"
					+ "					a.tothol as uhqty,\r\n" + "					a.data_app_date as upd_date  \r\n"
					+ "		  from (\r\n" + "				select a.census_no,\r\n"
					+ "				a.type_of_eqpt,\r\n" + "				1  as tothol ,\r\n"
					+ "				data_app_date\r\n" + "				from mms_tb_regn_mstr_detl a \r\n"
					+ "				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "				where a.sus_no = ? and a.op_status='1' and a.type_of_hldg='A0'\r\n"
					+ "			union All\r\n" + "			select a.census_no,\r\n"
					+ "				a.type_of_eqpt,\r\n" + "				1 as tothol ,\r\n"
					+ "				data_app_date\r\n" + "				from mms_tb_depot_regn_mstr_detl a \r\n"
					+ "				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "				where a.sus_no = ? and a.op_status='1' and a.type_of_hldg='A0'\r\n"
					+ "			union All\r\n" + "			select a.census_no,\r\n"
					+ "				a.type_of_eqpt,\r\n" + "				1 as uh ,\r\n"
					+ "				data_app_date\r\n" + "				from mms_tb_regn_oth_mstr a \r\n"
					+ "				LEFT JOIN tb_miso_orbat_unt_dtl u ON a.to_sus_no::text = u.sus_no::text AND u.status_sus_no::text = 'Active'::text\r\n"
					+ "				where a.to_sus_no = ? and a.op_status='1' and  a.type_of_hldg='A0'\r\n"
					+ "		  ) as a\r\n"
					+ "				inner join mms_tb_mlccs_mstr_detl b on a.census_no=b.census_no  \r\n"
					+ "		union all   \r\n" + "			select 	prf_group_code as prf_code,\r\n"
					+ "					item_code,\r\n" + "					'' as census_no,\r\n"
					+ "					(case when upper(type)='CES' then '2' else '1' end) as type_of_eqpt,\r\n"
					+ "					total_ue_qty as ueqty,\r\n" + "					0 as uhqty,\r\n"
					+ "					null as upd_date \r\n" + // cast('01/01/2019' as date) as upd_date
					"			from mms_ue_mview  \r\n" + "		where sus_no=?) p  \r\n"
					+ "	group by p.prf_code,p.item_code,p.type_of_eqpt\r\n" + "	) r  \r\n"
					+ "	left join cue_tb_miso_mms_item_mstr i on r.item_code=i.item_code  \r\n"
					+ "	left join cue_tb_miso_prf_group_mst pr on r.prf_code=pr.prf_group_code  \r\n"
					+ "	left join mms_domain_values t1 on r.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'  \r\n"
					+ "	left join mms_domain_values t2 on r.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'  \r\n"
					+ "	order by pr.prf_group,r.item_code,r.type_of_hldg,r.type_of_eqpt";

			PreparedStatement stmt = conn.prepareStatement(Sql);
			stmt.setString(1, nSUSNo);
			stmt.setString(2, nSUSNo);
			stmt.setString(3, nSUSNo);
			stmt.setString(4, nSUSNo);
			System.out.println("==a=a=a===" + stmt);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("prf_group")); // 0
					list.add(rs.getString("item_nomen")); // 1
					list.add(rs.getString("type_of_hldg_n")); // 2
					list.add(rs.getString("type_of_eqpt_n")); // 3
					float tue = Float.parseFloat(rs.getString("totue"));
					float tuh = Float.parseFloat(rs.getString("totuh"));
					float tdefi = tue - tuh;
					list.add(rs.getString("totue")); // 4
					list.add(rs.getString("totuh")); // 5
					if (tdefi <= 0) {
						list.add(String.valueOf(Math.abs(tdefi))); // 6 surplus
						list.add("0"); // 7
					} else {
						list.add("0"); // 6
						list.add(String.valueOf(Math.abs(tdefi))); // 7 defi
					}
					list.add(rs.getString("upd_date")); // 8
					float total = tue - tuh;
					if (total <= 0) {
						list.add(String.valueOf(Math.abs(total))); // 9 surplus
					} else {
						list.add(String.valueOf(-(total))); // 9 deficiency
					}
					li.add(list);
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

	@Override
	public ArrayList<ArrayList<String>> UnitccReglist(int id) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String sql = "";
		try {
			conn = dataSource.getConnection();
			sql = "select eqpt_regn_no from mms_tb_regn_tr_log where tr_id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("eqpt_regn_no"));
					li.add(list);
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

	//
	@Override
	public ArrayList<ArrayList<String>> UnitRegnMcrList(String nParaValue) {
		// List<String> nFlName = getRegnFileName();
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String Sql = "select \r\n" + "	a.sus_no,\r\n" + "	f.prf_group,\r\n" + "	a.census_no,\r\n"
					+ "	m.nomen,\r\n" + "	t1.label as type_of_hldg_n,\r\n" + "	t2.label as type_of_eqpt_n,\r\n"
					+ "	a.tothol,\r\n" + "	case when t1.versionno = '1'\r\n"
					+ "		then (select string_agg(eqpt_regn_no,',') \r\n"
					+ "				from mms_tb_regn_mstr_detl \r\n"
					+ "				where eqpt_regn_no not like 'NTMP%' and to_sus_no=a.sus_no and census_no= a.census_no and type_of_hldg=a.type_of_hldg) \r\n"
					+ "		when t1.versionno = '2'\r\n" + "		then (select string_agg(eqpt_regn_no,',') \r\n"
					+ "				from mms_tb_regn_oth_mstr \r\n"
					+ "				where eqpt_regn_no not like 'NTMP%' and to_sus_no=a.sus_no and census_no= a.census_no and type_of_hldg=a.type_of_hldg) 	\r\n"
					+ "		when t1.versionno = '3'\r\n" + "		then (select string_agg(eqpt_regn_no,',') \r\n"
					+ "				from mms_tb_depot_regn_mstr_detl \r\n"
					+ "				where eqpt_regn_no not like 'NTMP%' and to_sus_no=a.sus_no and census_no= a.census_no and type_of_hldg=a.type_of_hldg) 		\r\n"
					+ "		end	as reg\r\n" + "	from \r\n" + "	(select \r\n" + "	 a.sus_no,\r\n"
					+ "	 a.census_no,\r\n" + "	 a.type_of_hldg,\r\n" + "	 a.type_of_eqpt,\r\n" + "	 a.tothol\r\n"
					+ "	 from mms_tv_regn_mstr a \r\n" + "		where a.sus_no = ? \r\n" + "	) a	 \r\n"
					+ "	 inner join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no \r\n"
					+ "	 inner join cue_tb_miso_prf_group_mst f on m.prf_code=f.prf_group_code \r\n"
					+ "	 inner join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING' \r\n"
					+ "	 inner join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT' \r\n"
					+ "	 order by f.prf_group,m.nomen";

			PreparedStatement stmt = conn.prepareStatement(Sql);
			stmt.setString(1, nParaValue);
			ResultSet rs = stmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("prf_group"));
					list.add("0");
					list.add(rs.getString("census_no"));
					list.add(rs.getString("nomen"));
					list.add(rs.getString("type_of_hldg_n"));
					list.add(rs.getString("type_of_eqpt_n"));
					list.add(rs.getString("tothol"));
					list.add(rs.getString("reg"));
					li.add(list);
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

	public String getRegnList(String nSus_no, String nCensus_no, String nType_of_type, String tableName) {
		Connection conn = null;
		String nrStr = "";
		try {
			conn = dataSource.getConnection();
			String nrSql = "";

			// String nFlName ="mms_tb_regn_mstr_detl";
			nrSql = "select eqpt_regn_no from " + tableName
					+ " where eqpt_regn_no not like 'NTMP%' and to_sus_no=? and census_no=? and type_of_hldg=? order by eqpt_regn_no";
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, nSus_no);
			stmt.setString(2, nCensus_no);
			stmt.setString(3, nType_of_type);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (nrStr.length() > 0) {
					nrStr = nrStr + ", ";
				}
				nrStr = nrStr + rs.getString("eqpt_regn_no");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrStr;
	}

	public List<String> getRegnFileName() {
		Connection conn = null;
		String fileName = "";
		List<String> list = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select codevalue,versionno  as filecode from mms_domain_values where domainid='TYPEOFHOLDING' --and codevalue=?";
			stmt = conn.prepareStatement(sql1);
			// stmt.setString(1, nPara);
			ResultSet rs = stmt.executeQuery();
			String fn = "";

			if (rs.next()) {
				fn = rs.getString("filecode");
				list.add(rs.getString("codevalue"));
				list.add(rs.getString("filecode"));
				if (fn.equalsIgnoreCase("1")) {
					fileName = "mms_tb_regn_mstr_detl";
				}
				if (fn.equalsIgnoreCase("2")) {
					fileName = "mms_tb_regn_oth_mstr";
				}
				if (fn.equalsIgnoreCase("3")) {
					fileName = "mms_tb_depot_regn_mstr_detl";
				}
				list.add(fileName);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> checkIfExistUnitHoldingeqpt_regn_no(String eqpt_regn_no) {
		Connection conn = null;
		String fileName = "";
		List<String> list = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			String nrSql = "";

			// String nFlName ="mms_tb_regn_mstr_detl";
			nrSql = "select sum (ifexist) as exist_count from \r\n"
					+ "(select COUNT(*) as ifexist from mms_tb_regn_mstr_detl where regn_seq_no = ? \r\n" + "UNION\r\n"
					+ "select COUNT(*) as ifexist from mms_tb_regn_oth_mstr where regn_seq_no = ?\r\n" + "UNION\r\n"
					+ "select COUNT(*) as ifexist from mms_tb_depot_regn_mstr_detl where regn_seq_no = ?\r\n" + ") n";
			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, eqpt_regn_no);
			stmt.setString(2, eqpt_regn_no);
			stmt.setString(3, eqpt_regn_no);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getString("exist_count"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String RegnNoGeneration1_UNIT(String prf_code, String eqpt_regn_no) {
		String Regn_seq_no = "";
		// -------------------------New Query for max number
		// ---------------------------------------
		String list1 = new String();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";

			sql1 = "select max(prfseq) as count from (select max(substr(regn_seq_no,9,16)::integer) as prfseq from mms_tb_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8)\r\n"
					+ "UNION\r\n"
					+ "select max(substr(regn_seq_no,9,16)::integer) as prfseq from mms_tb_regn_oth_mstr where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n"
					+ "UNION\r\n"
					+ "select max(substr(regn_seq_no,9,16)::integer) as prfseq from mms_tb_depot_regn_mstr_detl where regn_seq_no is not null  group by substr(regn_seq_no,0,8) \r\n"
					+ ") AS tab";
			PreparedStatement stmt = conn.prepareStatement(sql1);

			ResultSet rs1 = stmt.executeQuery();

			while (rs1.next()) {
				list1 = rs1.getString("count");
			}
			rs1.close();
			stmt.close();
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

		String serial = "";
		if (list1.equals(null)) {
			serial = "00000000";
		} else {
			String list2 = list1;
			serial = list2;
		}

		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%08d", serialNo);
		Regn_seq_no = prf_code + "N" + serial;
		return Regn_seq_no;
	}

	@Override
	public String getsus_no_list(String sus_no) {
		Connection conn = null;
		String susno = "";

		try {
			conn = dataSource.getConnection();
			String sql1 = "";

			sql1 = "SELECT DISTINCT coalesce(cb.sus_no, cd.sus_no, cc.sus_no, c.sus_no) AS sus_no\r\n"
					+ "  FROM tb_miso_orbat_unt_dtl AS ul\r\n" + "  LEFT JOIN orbat_view_cmd c\r\n"
					+ "    ON substr(ul.form_code_control::text, 1, 1) = c.cmd_code::text\r\n"
					+ "  LEFT JOIN orbat_view_corps cc\r\n"
					+ "    ON substr(ul.form_code_control::text, 2, 2) = cc.corps_code::text\r\n"
					+ "  LEFT JOIN orbat_view_div cd\r\n"
					+ "    ON substr(ul.form_code_control::text, 4, 3) = cd.div_code::text\r\n"
					+ "  LEFT JOIN orbat_view_bde cb\r\n"
					+ "    ON substr(ul.form_code_control::text, 7, 4) = cb.bde_code::text\r\n"
					+ " WHERE ul.sus_no = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);

			ResultSet rs1 = stmt.executeQuery();

			while (rs1.next()) {
				susno = rs1.getString("sus_no");
			}

			rs1.close();
			stmt.close();
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

		return susno;
	}

	@Override
	public ArrayList<ArrayList<String>> trackiutstatus_mms(String sus_no, String role_type, String roleAccess,
			Integer id) {

		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String qry = "";
		if (roleAccess.equals("Unit")) {
			qry = "a.to_sus_no";
		} else {
			qry = "a.nxt_level_in_hierarchy_sus_no";
		}

		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String sql = "";

			if (role_type.equals("DEO")) {
				sql = "SELECT distinct on (a.id) a.id,\r\n" + "       a.username,\r\n"
						+ "       b.unit_name AS source_unit,\r\n" + "       c.unit_name AS target_unit,\r\n"
						+ "        REPLACE( a.eqpt_regn_no, ':', ',') as  regn_seq_no,\r\n"
						+ "       case when a.status = 0 then 'pending' end as pstatus,a.status,\r\n"
						+ "       c.form_code_control\r\n" + "  FROM mms_tb_child_regn_mstr_detl a\r\n"
						+ "  LEFT JOIN tb_miso_orbat_unt_dtl b\r\n" + "    ON b.sus_no = a.from_sus_no\r\n"
						+ "  LEFT JOIN tb_miso_orbat_unt_dtl c\r\n" + "    ON c.sus_no = a.to_sus_no\r\n"
						+ "    where a.from_sus_no = ? and a.status = 0 and ( ? = 0 or A.id = ? )";
			} else {
				sql = "SELECT distinct on (a.id) a.id,\r\n" + "       a.username,\r\n"
						+ "       b.unit_name AS source_unit,\r\n" + "       c.unit_name AS target_unit,\r\n"
						+ "        REPLACE( a.eqpt_regn_no, ':', ',') as  regn_seq_no,\r\n"
						+ "       case when a.status = 0 then 'pending' end as pstatus,a.status,\r\n"
						+ "       c.form_code_control\r\n" + "  FROM mms_tb_child_regn_mstr_detl a\r\n"
						+ "  LEFT JOIN tb_miso_orbat_unt_dtl b\r\n" + "    ON b.sus_no = a.from_sus_no\r\n"
						+ "  LEFT JOIN tb_miso_orbat_unt_dtl c\r\n" + "    ON c.sus_no = a.to_sus_no\r\n" + "    where "
						+ qry + " = ? and a.status = 0  and ( ? = 0 or A.id = ? )";

			}

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sus_no);
			stmt.setInt(2, id);
			stmt.setInt(3, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("username"));
				list.add(rs.getString("source_unit"));
				list.add(rs.getString("target_unit"));
				list.add(rs.getString("regn_seq_no"));
				list.add(rs.getString("pstatus"));
				// list.add(rs.getString("form_code_control"));

				String f = "";

				String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve this Conversion ?') ){approve_iut("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String appButton = "<i class='action_icons action_approve' " + Approved + " title='Approve Data'></i>";

				String update1 = "onclick=\"  if (confirm('Are You Sure you want to Update Data?') ){update_iut("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String updateButton = "<i class='action_icons action_update'  " + update1 + " title='Update Data'></i>";

				String Reject = "onclick=\"  if (confirm('Are You Sure you want to Reject?') ){reject_iut("
						+ rs.getString("id") + ")}else{ return false;}\"";
				String rejectButton = "<i class='action_icons action_reject'  " + Reject + " title='Reject Data'></i>";

				if (role_type.equals("DEO")) {

					f += updateButton;
					// f += deleteButton;
				} else {
					f += appButton;
					f += rejectButton;
				}

				list.add(f);
				// list.add(rs.getString("target_sus_no"));
				aList.add(list);

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
		return aList;
	}

}