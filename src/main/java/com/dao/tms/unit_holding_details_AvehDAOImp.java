package com.dao.tms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class unit_holding_details_AvehDAOImp implements unit_holding_details_AvehDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<ArrayList<String>> search_unit_holding_detailsListt_Aveh(String sus_no) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";

			sql = "select \r\n" + "	distinct n.mct_nomen,\r\n"
					+ "	 SUBSTR(cast(m.mct as character varying),1,4) as mct_main_id,\r\n" + "	coalesce((SELECT \r\n"
					+ "	sum(a.auth_amt) AS total\r\n" + "   	FROM ( SELECT b_1.sus_no,\r\n"
					+ "            a_1.we_pe_no,\r\n" + "            a_1.mct_no,\r\n" + "            a_1.auth_amt,\r\n"
					+ "            ' '::text AS condition,\r\n" + "            'BASEAUTH'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_miso_wepe_transport_det a_1\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon b_1 ON \r\n"
					+ "		 	a_1.we_pe_no::text = b_1.wepe_trans_no::text AND b_1.status_trans::text = '1'::text AND \r\n"
					+ "		 	a_1.status::text = '1'::text\r\n"
					+ "		 	where a_1.mct_no =SUBSTRING(cast(m.mct as character varying), 1, 4) and b_1.sus_no =part.sus_no\r\n"
					+ "        UNION\r\n" + "         SELECT a_1.sus_no,\r\n" + "            a_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            sum(b_1.amt_inc_dec) AS sum,\r\n"
					+ "            ''::text AS condition,\r\n" + "            'MOD'::text AS typeofauth\r\n"
					+ "			FROM cue_tb_wepe_link_sus_trans_mdfs a_1\r\n"
					+ "			JOIN cue_tb_miso_wepe_transport_mdfs b_1 ON a_1.modification::text = b_1.modification::text \r\n"
					+ "		 		AND a_1.we_pe_no::text = b_1.we_pe_no::text AND b_1.status::text = '1'::text\r\n"
					+ "          WHERE a_1.status::text = '1'::text and b_1.mct_no =SUBSTRING(cast(m.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n"
					+ "          GROUP BY a_1.sus_no, a_1.we_pe_no, b_1.mct_no\r\n" + "        UNION\r\n"
					+ "         SELECT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.in_lieu_mct,\r\n" + "            b_1.actual_inlieu_auth,\r\n"
					+ "            b_1.condition,\r\n" + "            'INLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND b_1.status::text = '1'::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.in_lieu_mct =SUBSTRING(cast(m.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n"
					+ "        UNION\r\n" + "         SELECT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            b_1.amt_inc_dec,\r\n"
					+ "            b_1.condition,\r\n" + "            'INCDEC'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '1'::text AND b_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "		 	where b_1.mct_no =SUBSTRING(cast(m.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no\r\n"
					+ "        UNION\r\n" + "         SELECT DISTINCT a_1.sus_no,\r\n" + "            b_1.we_pe_no,\r\n"
					+ "            b_1.mct_no,\r\n" + "            sum(b_1.actual_inlieu_auth) * '-1'::integer,\r\n"
					+ "            ' '::text AS condition,\r\n"
					+ "            'REDUCEDUEINLIEU'::text AS typeofauth\r\n"
					+ "           FROM cue_tb_wepe_link_sus_trans_footnotes a_1\r\n"
					+ "             JOIN cue_tb_miso_wepe_trans_footnotes b_1 ON a_1.fk_trans_foot = b_1.id AND b_1.type_of_footnote::text = '0'::text\r\n"
					+ "             JOIN cue_tb_miso_wepe_transport_det c ON b_1.mct_no::text = c.mct_no::text AND b_1.we_pe_no::text = c.we_pe_no::text AND a_1.status::text = '1'::text\r\n"
					+ "             JOIN cue_tb_wepe_link_sus_perstransweapon link ON a_1.we_pe_no::text = link.wepe_trans_no::text AND link.status_trans::text = '1'::text\r\n"
					+ "         where b_1.mct_no =SUBSTRING(cast(m.mct as character varying), 1, 4) and a_1.sus_no =part.sus_no	 \r\n"
					+ "		 GROUP BY a_1.sus_no, b_1.we_pe_no, b_1.mct_no\r\n" + "		) a\r\n"
					+ "     JOIN tb_tms_mct_main_master b ON a.mct_no::text = b.mct_main_id::text\r\n"
					+ "  GROUP BY a.sus_no, a.we_pe_no, a.mct_no\r\n" + "  ORDER BY a.we_pe_no ),0) as ue,\r\n"
					+ "	(select count(p.ba_no) from tb_tms_census_retrn p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(m.mct  AS varchar),1,4)) and p.sus_no = part.sus_no) as total_uh \r\n"
					+ "from \r\n" + "tms_vehicle_status_a_b_c_with_ue_uh part \r\n"
					+ "inner join tb_tms_mct_master m on part.mct_main_id = SUBSTRING(cast(m.mct as character varying), 1, 4) and m.type_of_vehicle = 'A' \r\n"  
					+ "left join tb_tms_mct_main_master n on  n.mct_main_id = SUBSTRING(cast(m.mct as character varying), 1, 4) \r\n"
					+ "where part.sus_no= ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, sus_no);
			
			ResultSet rs = stmt.executeQuery();
			System.err.println(stmt);
			while (rs.next()) {
				ArrayList<String> list1 = new ArrayList<String>();
				list1.add(rs.getString("mct_nomen"));
				list1.add(rs.getString("mct_main_id"));
				list1.add(rs.getString("ue"));
				list1.add(rs.getString("total_uh"));
				list.add(list1);
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
		return list;
	}
}