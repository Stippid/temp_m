package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class C_VehicleDAOImpl implements C_VehicleDAO {
	private DataSource dataSource;
	int count = 0;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<List<String>> getFMVCRPartBDataList(String sus_no) {
		ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		String q = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			q = "select distinct mct.mct,mct.mct_nomen,\r\n" 
					+ "	mct.ue,sum(we) as we,sum(opwks) as opwks,\r\n"
					+ "	sum(ascfp) as ascfp,sum(other) as other,\r\n" 
					+ "	sum(we+opwks+ascfp+other) as total_uh \r\n"
					+ "from (select \r\n" 
					+ "		distinct substr(d.mct::varchar,1,4) as mct,\r\n"
					+ "		coalesce((SELECT \r\n" 
					+ "	sum(a.auth_amt) AS total\r\n"
					+ "   	FROM ( SELECT b_1.sus_no,\r\n" 
					+ "            a_1.we_pe_no,\r\n"
					+ "            a_1.mct_no,\r\n" 
					+ "            a_1.auth_amt,\r\n"
					+ "            ' '::text AS condition,\r\n" 
					+ "            'BASEAUTH'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_miso_wepe_transport_det a_1\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n"
					+ "		 	a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND \r\n"
					+ "		 	a_1.status::text = '1'::text\r\n"
					+ "		 	where a_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and b_1.sus_no =e.sus_no\r\n"
					+ "        UNION\r\n" 
					+ "         SELECT a_1.sus_no,\r\n" 
					+ "            a_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" 
					+ "            sum(b_1.amt_inc_dec) AS sum,\r\n"
					+ "            ''::text AS condition,\r\n" 
					+ "            'MOD'::text AS typeofauth\r\n"
					+ "			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n"
					+ "			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text \r\n"
					+ "		 		AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n"
					+ "          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n"
					+ "          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + "        UNION\r\n"
					+ "         SELECT a_1.sus_no,\r\n" 
					+ "            b_1.we_pe_no,\r\n"
					+ "            b_1.in_lieu_mct,\r\n" 
					+ "            b_1.actual_inlieu_auth,\r\n"
					+ "            b_1.condition,\r\n" 
					+ "            'INLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.in_lieu_mct =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n"
					+ "        UNION\r\n" 
					+ "         SELECT a_1.sus_no,\r\n" 
					+ "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" 
					+ "            b_1.amt_inc_dec,\r\n"
					+ "            b_1.condition,\r\n" 
					+ "            'INCDEC'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no\r\n"
					+ "        UNION\r\n" 
					+ "         SELECT DISTINCT a_1.sus_no,\r\n" 
					+ "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" 
					+ "            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n"
					+ "            ' '::text AS condition,\r\n"
					+ "            'REDUCEDUEINLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "         where b_1.mct_no =SUBSTRING(cast(d.mct as character varying), 1, 4) and a_1.sus_no =e.sus_no	 \r\n"
					+ "		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + "		) a\r\n"
					+ "     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n"
					+ "  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" 
					+ "  ORDER BY a.we_pe_no ),0) as ue,\r\n"
					+ "	  \r\n"
					+ "		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as we,\r\n"
					+ "		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as opwks,\r\n"
					+ "		(select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as ascfp,\r\n"
					+ "		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as other,\r\n"
					+ "		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as Total_UH\r\n"
					+ "		,m.mct_nomen\r\n" + "from tb_tms_emar_report e   \r\n"
					+ "inner join tb_tms_banum_dirctry d on  d.ba_no = e.em_no \r\n"
					+ "inner join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n"
					+ "where  e.sus_no = ? and e.proc_from != '' ) as mct \r\n"
					+ "group by  mct.mct,mct.ue,mct.mct_nomen";

			stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<String> list = new ArrayList();
				list.add(rs.getString("mct"));
				list.add(rs.getString("mct_nomen"));
				list.add(rs.getString("ue"));
				list.add(rs.getString("we"));
				list.add(rs.getString("opwks"));
				list.add(rs.getString("ascfp"));
				list.add(rs.getString("other"));
				list.add(rs.getString("total_uh"));
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