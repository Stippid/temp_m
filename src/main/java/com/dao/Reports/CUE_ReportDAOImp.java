package com.dao.Reports;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Miso_Orbat_Unt_Dtl;
import com.models.TB_TMS_MCT_MASTER;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class CUE_ReportDAOImp implements CUE_ReportDAO{

	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
    // PERSONNEL
	// 1.
	public ArrayList<ArrayList<String>> ue_persnl_appntrnkwiserprt(String from, String to, String sus) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	rank_desc,\r\n" + 
					"	rank_appt,\r\n" + 
					"	sum(sum) as total\r\n" + 
					"from olap_cue_app_rank_wise\r\n" + 
					"where sus_no = ? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6\r\n" + 
					"order by rank_desc";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("rank_desc"));
				list.add(rs1.getString("rank_appt"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 1. END
		 
	// 2. Data sheet

	public ArrayList<ArrayList<String>> ue_pers_data_officersreport(String from, String to, String rank_cat,String sus) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	category_of_persn,\r\n" + 
					"	rank_cat,\r\n" + 
					"	rank_appt,\r\n" + 
					"	rank,\r\n" + 
					"	base,\r\n" + 
					"	mod,\r\n" + 
					"	foot,\r\n" + 
					"	total_ue,\r\n" + 
					"	ct_part_i_ii,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	sus_no,\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	parent_arm,\r\n" + 
					"	user_arm\r\n" + 
					"from olap_cue_datasheet_for_offcr_jco_or_civ\r\n" + 
					"where rank_cat=? and sus_no=? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, rank_cat);
			stmt.setString(2, sus);
			stmt.setString(3, from);
			stmt.setString(4, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("category_of_persn"));
				list.add(rs1.getString("rank_cat"));
				list.add(rs1.getString("rank"));
				list.add(rs1.getString("rank_appt"));
				list.add(rs1.getString("base"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("foot"));
				list.add(rs1.getString("total_ue"));
				list.add(rs1.getString("ct_part_i_ii"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("parent_arm"));
				list.add(rs1.getString("user_arm"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 2. END

	// 3. Design and Training capecity

	public ArrayList<ArrayList<String>> design_training_capreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	training_cap\r\n" + 
					"from olap_cue_dsng_cap_trg_cetre\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("training_cap"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 3. END

	// 4. officers parent arm wise

	public ArrayList<ArrayList<String>> auth_str_alluntreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	count\r\n" + 
					"from olap_cue_arm_wise_strength_officers\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("count"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 4. END

	// 5.

	public ArrayList<ArrayList<String>> allontmnt_of_parntarmreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	count\r\n" + 
					"from olap_cue_arm_wise_strength_jco\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("count"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 5. END

	// 6.

	public ArrayList<ArrayList<String>> allontmnt_of_vancreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	count\r\n" + 
					"from olap_cue_arm_wise_strength_or\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("count"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 6. END

	// 7.

	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISEreport(String from, String to, String sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	sus_no,\r\n" + 
					"	cmd_name,\r\n" + 
					"	coprs_name,\r\n" + 
					"	div_name,\r\n" + 
					"	bde_name,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	user_arm,\r\n" + 
					"	unit_name,ct_part_i_ii,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	officer,\r\n" + 
					"	mns,\r\n" + 
					"	jco,\r\n" + 
					"	jco_or,\r\n" + 
					"	o_r,\r\n" + 
					"	ncsue,\r\n" + 
					"	civil_class_i_gaz,\r\n" + 
					"	civil_class_ii_gaz,\r\n" + 
					"	civil_class_ii_non_gaz,\r\n" + 
					"	civil_class_iii_non_gaz,\r\n" + 
					"	civil_class_iv_non_gaz,\r\n" + 
					"	civil_class_ii_non_gaz_ind,\r\n" + 
					"	civil_class_iii_non_gaz_ind,\r\n" + 
					"	civil_class_iv_non_gaz_ind\r\n" + 
					"from olap_cue_total_auth_rank_wise\r\n" + 
					"where sus_no=? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("cmd_name"));
				list.add(rs1.getString("coprs_name"));
				list.add(rs1.getString("div_name"));
				list.add(rs1.getString("bde_name"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("user_arm"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("ct_part_i_ii"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("officer"));
				list.add(rs1.getString("mns"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("jco_or"));
				list.add(rs1.getString("o_r"));
				list.add(rs1.getString("ncsue"));
				list.add(rs1.getString("civil_class_i_gaz"));
				list.add(rs1.getString("civil_class_ii_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz"));
				list.add(rs1.getString("civil_class_iii_non_gaz"));
				list.add(rs1.getString("civil_class_iv_non_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iv_non_gaz_ind"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 7. END

	// 8.

	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_REGUALR_ARMY_JCO_OR_RANKS_AND_PARENT_ARMreport(String from,
			String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	smaj,\r\n" + 
					"	sub,\r\n" + 
					"	sub_nbsub,\r\n" + 
					"	rns_jco,\r\n" + 
					"	jco_or,\r\n" + 
					"	nsub,\r\n" + 
					"	hav,\r\n" + 
					"	l_hav,\r\n" + 
					"	nk,\r\n" + 
					"	l_nk,\r\n" + 
					"	sep,\r\n" + 
					"	l_nk_sep,\r\n" + 
					"	rns_or,\r\n" + 
					"	sum(smaj+sub+sub_nbsub+rns_jco+jco_or+nsub) as total_1,\r\n" + 
					"	sum(hav+l_hav+nk+l_nk+sep+l_nk_sep+rns_or) as total_2\r\n" + 
					"from olap_cue_auth_estb_reg_army_jco_or_by_ranks\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14\r\n" + 
					"\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("smaj"));
				list.add(rs1.getString("sub"));
				list.add(rs1.getString("sub_nbsub"));
				list.add(rs1.getString("rns_jco"));
				list.add(rs1.getString("nsub"));
				list.add(rs1.getString("hav"));
				list.add(rs1.getString("l_hav"));
				list.add(rs1.getString("nk"));
				list.add(rs1.getString("l_nk"));
				list.add(rs1.getString("sep"));
				list.add(rs1.getString("l_nk_sep"));
				list.add(rs1.getString("rns_or"));
				list.add(rs1.getString("total_1"));
				list.add(rs1.getString("total_2"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 8. END

	// 9.

	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_OFFICERS_OF_CT_TYPE_REG_ERE_UNSPreport(String from,String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	arm_desc,\r\n" + 
					"	arm_code,\r\n" + 
					"	ctiere,\r\n" + 
					"	ctiregtl,\r\n" + 
					"	unsi,\r\n" + 
					"	ctiiere,\r\n" + 
					"	ctiiregtl,\r\n" + 
					"	unsii,\r\n" + 
					"	sum(ctiere+ctiregtl+unsi) as ctitotal,\r\n" + 
					"	sum(ctiiere+ctiiregtl+unsii) as ctiitotal,\r\n" + 
					"	sum(ctiere+ctiregtl+unsi+ctiiere+ctiiregtl+unsii) as grand_total\r\n" + 
					"from olap_cue_auth_estb_of_offcers_reg_ere_unsp\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("ctiregtl"));
				list.add(rs1.getString("ctiere"));
				list.add(rs1.getString("unsi"));
				list.add(rs1.getString("ctiiregtl"));
				list.add(rs1.getString("ctiiere"));
				list.add(rs1.getString("unsii"));
				list.add(rs1.getString("ctitotal"));
				list.add(rs1.getString("ctiitotal"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 9. END

	// 10.

	public ArrayList<ArrayList<String>> AUTH_ESTABLISHMENT_REG_ARMY_OFF_BY_RANKS_AND_PARENT_ARMreport(String from,String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	gen,\r\n" + 
					"	lt_gen,\r\n" + 
					"	maj_gen,\r\n" + 
					"	brig,\r\n" + 
					"	brig_colonel,\r\n" + 
					"	col,\r\n" + 
					"	col_ltcol,\r\n" + 
					"	lt_col,\r\n" + 
					"	col_ltcol_maj,\r\n" + 
					"	lt_col_maj_capt,\r\n" + 
					"	maj,\r\n" + 
					"	maj_capt,\r\n" + 
					"	capt,\r\n" + 
					"	lt,\r\n" + 
					"	rns,\r\n" + 
					"	sum(gen+lt_gen+maj_gen+brig+brig_colonel+col+col_ltcol+lt_col+col_ltcol_maj+lt_col_maj_capt+maj+maj_capt+capt+lt+rns) as total\r\n" + 
					"from olap_cue_auth_estb_reg_army_officer\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("gen"));
				list.add(rs1.getString("lt_gen"));
				list.add(rs1.getString("maj_gen"));
				list.add(rs1.getString("brig"));
				list.add(rs1.getString("brig_colonel"));
				list.add(rs1.getString("col"));
				list.add(rs1.getString("col_ltcol"));
				list.add(rs1.getString("lt_col"));
				list.add(rs1.getString("col_ltcol_maj"));
				list.add(rs1.getString("lt_col_maj_capt"));
				list.add(rs1.getString("maj"));
				list.add(rs1.getString("maj_capt"));
				list.add(rs1.getString("capt"));
				list.add(rs1.getString("lt"));
				list.add(rs1.getString("rns"));
				list.add(rs1.getString("total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 10. END

	// 11.

	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISE_REPORT1(String from, String to, String sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	user_arm,\r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	ct_part_i_ii,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	officer,\r\n" + 
					"	mns,\r\n" + 
					"	jco,\r\n" + 
					"	jco_or,\r\n" + 
					"	o_r,\r\n" + 
					"	ncsue,\r\n" + 
					"	civil_class_i_gaz,\r\n" + 
					"	civil_class_ii_gaz,\r\n" + 
					"	civil_class_ii_non_gaz,\r\n" + 
					"	civil_class_iii_non_gaz,\r\n" + 
					"	civil_class_iv_non_gaz,\r\n" + 
					"	civil_class_ii_non_gaz_ind,\r\n" + 
					"	civil_class_iii_non_gaz_ind,\r\n" + 
					"	civil_class_iv_non_gaz_ind\r\n" + 
					"from olap_cue_total_auth_rank_wise\r\n" + 
					"where sus_no = ? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("user_arm"));
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("ct_part_i_ii"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("officer"));
				list.add(rs1.getString("mns"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("jco_or"));
				list.add(rs1.getString("o_r"));
				list.add(rs1.getString("ncsue"));
				list.add(rs1.getString("civil_class_i_gaz"));
				list.add(rs1.getString("civil_class_ii_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz"));
				list.add(rs1.getString("civil_class_iii_non_gaz"));
				list.add(rs1.getString("civil_class_iv_non_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iv_non_gaz_ind"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 11. END

	// 12.

	public ArrayList<ArrayList<String>> TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISEreport(String from, String to,String sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	parent_arm_desc,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	cmd_name,\r\n" + 
					"	coprs_name,\r\n" + 
					"	div_name,\r\n" + 
					"	bde_name,\r\n" + 
					"	user_arm,\r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	ct_part_i_ii,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	officer,\r\n" + 
					"	jco,\r\n" + 
					"	jco_or,\r\n" + 
					"	o_r,\r\n" + 
					"	civil_class_i_gaz,\r\n" + 
					"	civil_class_ii_gaz,\r\n" + 
					"	civil_class_ii_non_gaz,\r\n" + 
					"	civil_class_iii_non_gaz,\r\n" + 
					"	civil_class_iv_non_gaz,\r\n" + 
					"	civil_class_ii_non_gaz_ind,\r\n" + 
					"	civil_class_iii_non_gaz_ind,\r\n" + 
					"	civil_class_iv_non_gaz_ind\r\n" + 
					"from olap_cue_total_auth_rank_wise_by_parent_arm\r\n" + 
					"where sus_no=? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("parent_arm_desc"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("cmd_name"));
				list.add(rs1.getString("coprs_name"));
				list.add(rs1.getString("div_name"));
				list.add(rs1.getString("bde_name"));
				list.add(rs1.getString("user_arm"));
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("ct_part_i_ii"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("officer"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("jco_or"));
				list.add(rs1.getString("o_r"));
				list.add(rs1.getString("civil_class_i_gaz"));
				list.add(rs1.getString("civil_class_ii_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz"));
				list.add(rs1.getString("civil_class_iii_non_gaz"));
				list.add(rs1.getString("civil_class_iv_non_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iv_non_gaz_ind"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 12. END

	// 13.

	public ArrayList<ArrayList<String>> AUTH_USER_ARM_WISEreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	user_arm_desc,\r\n" + 
					"	officer,\r\n" + 
					"	jco,\r\n" + 
					"	o_r,\r\n" + 
					"	sum(officer+jco+o_r) as total\r\n" + 
					"from olap_cue_actual_strength_by_user_arm\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("officer"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("o_r"));
				list.add(rs1.getString("total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 13. END

	// 14.

	public ArrayList<ArrayList<String>> AUTH_EST_REG_ARMY_PERS_CIVIL_FORMATIONreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	user_arm_desc,\r\n" + 
					"	non_med,\r\n" + 
					"	med,\r\n" + 
					"	mns,\r\n" + 
					"	jco,\r\n" + 
					"	o_r,\r\n" + 
					"	gaz,\r\n" + 
					"	non_gaz,\r\n" + 
					"	ncsue_cl_iv,\r\n" + 
					"	officers_total,\r\n" + 
					"	jco_or_total,\r\n" + 
					"	grand_total\r\n" + 
					"from olap_cue_auth_est_reg_army_personnel_civ_formation\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("non_med"));
				list.add(rs1.getString("med"));
				list.add(rs1.getString("mns"));
				list.add(rs1.getString("jco"));
				list.add(rs1.getString("o_r"));
				list.add(rs1.getString("gaz"));
				list.add(rs1.getString("non_gaz"));
				list.add(rs1.getString("ncsue_cl_iv"));
				list.add(rs1.getString("officers_total"));
				list.add(rs1.getString("jco_or_total"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 14. END

	// 15.

	public ArrayList<ArrayList<String>> AUTH_ESTB_JCO_OR(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	arm_desc,\r\n" + 
					"	reg_jco,\r\n" + 
					"	reg_or,\r\n" + 
					"	reg_total,\r\n" + 
					"	ere_jco,\r\n" + 
					"	ere_or,\r\n" + 
					"	ere_total,\r\n" + 
					"	unsp_jco,\r\n" + 
					"	unsp_or,\r\n" + 
					"	unsp_total,\r\n" + 
					"	sum(reg_jco+ere_jco+unsp_jco) as jco_total,\r\n" + 
					"	sum(reg_or+ere_or+unsp_or) as or_total,\r\n" + 
					"	sum(reg_jco+ere_jco+unsp_jco+reg_or+ere_or+unsp_or) as total\r\n" + 
					"from olap_cue_auth_est_reg_army_jco_or\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10\r\n" + 
					"";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("reg_jco"));
				list.add(rs1.getString("reg_or"));
				list.add(rs1.getString("reg_total"));
				list.add(rs1.getString("ere_jco"));
				list.add(rs1.getString("ere_or"));
				list.add(rs1.getString("ere_total"));
				list.add(rs1.getString("unsp_jco"));
				list.add(rs1.getString("unsp_or"));
				list.add(rs1.getString("unsp_total"));
				list.add(rs1.getString("jco_total"));
				list.add(rs1.getString("or_total"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 15. END

	// 16.

	public ArrayList<ArrayList<String>> AUTH_OF_OFF_UNIT_WISEreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	gen,\r\n" + 
					"	lt_gen,\r\n" + 
					"	maj_gen,\r\n" + 
					"	brig,\r\n" + 
					"	brig_colonel,\r\n" + 
					"	col,\r\n" + 
					"	col_ltcol,\r\n" + 
					"	col_ltcol_maj,\r\n" + 
					"	lt_col,\r\n" + 
					"	lt_col_maj,\r\n" + 
					"	lt_col_maj_capt,\r\n" + 
					"	maj,\r\n" + 
					"	maj_capt,\r\n" + 
					"	capt,\r\n" + 
					"	lt,\r\n" + 
					"	rns,\r\n" + 
					"	sum(gen+lt_gen+maj_gen+brig+brig_colonel+col+col_ltcol+col_ltcol_maj+lt_col+lt_col_maj+lt_col_maj_capt+maj+maj_capt+capt+lt+rns) as total\r\n" + 
					"from olap_cue_auth_of_officers_army_hq\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("gen"));
				list.add(rs1.getString("lt_gen"));
				list.add(rs1.getString("maj_gen"));
				list.add(rs1.getString("brig"));
				list.add(rs1.getString("brig_colonel"));
				list.add(rs1.getString("col"));
				list.add(rs1.getString("col_ltcol"));
				list.add(rs1.getString("col_ltcol_maj"));
				list.add(rs1.getString("lt_col"));
				list.add(rs1.getString("lt_col_maj"));
				list.add(rs1.getString("lt_col_maj_capt"));
				list.add(rs1.getString("maj"));
				list.add(rs1.getString("maj_capt"));
				list.add(rs1.getString("capt"));
				list.add(rs1.getString("lt"));
				list.add(rs1.getString("rns"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 16. END

	// 17.

	public ArrayList<ArrayList<String>> MAJ_MIN_UNIT_CMD_WISEreport(String from, String to, String sus_no) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	sus_no,\r\n" + 
					"	unit_name,\r\n" + 
					"	cmd_name,\r\n" + 
					"	unit_category,\r\n" + 
					"	maj_gen_and_above,\r\n" + 
					"	col_brig,\r\n" + 
					"	lt_col_and_below,\r\n" + 
					"	jco_or,\r\n" + 
					"	maj_gen_and_above_c,\r\n" + 
					"	col_brig_c,\r\n" + 
					"	lt_col_and_below_c,\r\n" + 
					"	jco_or_c\r\n" + 
					"from olap_cue_maj_min_unit_comd_wise\r\n" + 
					"where sus_no= ? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, sus_no);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("sus_no"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("cmd_name"));
				list.add(rs1.getString("unit_category"));
				list.add(rs1.getString("maj_gen_and_above"));
				list.add(rs1.getString("col_brig"));
				list.add(rs1.getString("lt_col_and_below"));
				list.add(rs1.getString("jco_or"));
				list.add(rs1.getString("maj_gen_and_above_c"));
				list.add(rs1.getString("col_brig_c"));
				list.add(rs1.getString("lt_col_and_below_c"));
				list.add(rs1.getString("jco_or_c"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 17. END

	// 18.

	public ArrayList<ArrayList<String>> MAJ_MIN_UNITSreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	user_arm_desc,\r\n" + 
					"	unit_category,\r\n" + 
					"	maj_gen_and_above,\r\n" + 
					"	col_brig,\r\n" + 
					"	lt_col_and_below,\r\n" + 
					"	jco_or,\r\n" + 
					"	maj_gen_and_above_c,\r\n" + 
					"	col_brig_c,\r\n" + 
					"	lt_col_and_below_c,\r\n" + 
					"	jco_or_c\r\n" + 
					"from olap_cue_maj_min_unit_arm_wise\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("unit_category"));
				list.add(rs1.getString("maj_gen_and_above"));
				list.add(rs1.getString("col_brig"));
				list.add(rs1.getString("lt_col_and_below"));
				list.add(rs1.getString("jco_or"));
				list.add(rs1.getString("maj_gen_and_above_c"));
				list.add(rs1.getString("col_brig_c"));
				list.add(rs1.getString("lt_col_and_below_c"));
				list.add(rs1.getString("jco_or_c"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 18. END


	// 20.

	public ArrayList<ArrayList<String>> TOTAL_AUTH_OF_CIVILIANS_IN_THE_ARMY_BY_USER_ARMSreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	user_arm,\r\n" + 
					"	user_arm_desc,\r\n" + 
					"	civil_class_i_gaz,\r\n" + 
					"	civil_class_ii_gaz,\r\n" + 
					"	ncsue,\r\n" + 
					"	civil_class_ii_non_gaz,\r\n" + 
					"	civil_class_iii_non_gaz,\r\n" + 
					"	civil_class_iv_non_gaz,\r\n" + 
					"	civil_class_ii_non_gaz_ind,\r\n" + 
					"	civil_class_iii_non_gaz_ind,\r\n" + 
					"	civil_class_iv_non_gaz_ind,\r\n" + 
					"	sum(civil_class_i_gaz+civil_class_ii_gaz+ncsue+civil_class_ii_non_gaz+civil_class_iii_non_gaz+civil_class_iv_non_gaz+civil_class_ii_non_gaz_ind+civil_class_iii_non_gaz_ind+civil_class_iv_non_gaz_ind) as total\r\n" + 
					"from olap_cue_total_auth_civ_by_user_arm\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("user_arm"));
				list.add(rs1.getString("user_arm_desc"));
				list.add(rs1.getString("civil_class_i_gaz"));
				list.add(rs1.getString("civil_class_ii_gaz"));
				list.add(rs1.getString("ncsue"));
				list.add(rs1.getString("civil_class_ii_non_gaz"));
				list.add(rs1.getString("civil_class_iii_non_gaz"));
				list.add(rs1.getString("civil_class_iv_non_gaz"));
				list.add(rs1.getString("civil_class_ii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iii_non_gaz_ind"));
				list.add(rs1.getString("civil_class_iv_non_gaz_ind"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 20. END
	// 21.

	public ArrayList<ArrayList<String>> ALLOTMENT_OF_VACANCIES_BY_PARENT_ARMreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	arm_desc,\r\n" + 
					"	auth_est,\r\n" + 
					"	indi_msm,\r\n" + 
					"	indi_ls_gcm,\r\n" + 
					"	rep_msm1,\r\n" + 
					"	rep_ls_gcm1\r\n" + 
					"from olap_cue_allot_vac_by_parent_arm\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("auth_est"));
				list.add(rs1.getString("indi_msm"));
				list.add(rs1.getString("indi_ls_gcm"));
				list.add(rs1.getString("rep_msm1"));
				list.add(rs1.getString("rep_ls_gcm1"));

			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 21. END
	
//TRANSPORT

	// 1. ALL INDIA AUTH B VEH

	public ArrayList<ArrayList<String>> all_india_auth_B_veh_rprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	gnl_spl,\r\n" + 
					"	group_name,\r\n" + 
					"	reserve,\r\n" + 
					"	cc,\r\n" + 
					"	sc,\r\n" + 
					"	wc,\r\n" + 
					"	ec,\r\n" + 
					"	nc,\r\n" + 
					"	atc,\r\n" + 
					"	anc,\r\n" + 
					"	swc,\r\n" + 
					"	mod,\r\n" + 
					"	sfc,\r\n" + 
					"	un,\r\n" + 
					"	sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un) as total,\r\n" + 
					"	case when reserve!=0 then cast(sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un)*(reserve/100) as integer) else 0 end as reserve_qty,\r\n" + 
					"	sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un)+(case when reserve!=0 then cast(sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un)*(reserve/100) as integer) else 0 end ) as grand_total\r\n" + 
					"from olap_cue_all_india_auth_b_veh\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14\r\n" + 
					"order by 1,2\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("gnl_spl"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("reserve"));
				list.add(rs1.getString("cc"));
				list.add(rs1.getString("sc"));
				list.add(rs1.getString("wc"));
				list.add(rs1.getString("ec"));
				list.add(rs1.getString("nc"));
				list.add(rs1.getString("atc"));
				list.add(rs1.getString("anc"));
				list.add(rs1.getString("swc"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("sfc"));
				list.add(rs1.getString("un"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("reserve_qty"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 1. END

	// 2. ALL INDIA AUTH A VEH

	public ArrayList<ArrayList<String>> all_india_auth_A_veh_rprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 ="select \r\n" + 
					"	gnl_spl,\r\n" + 
					"	group_name,\r\n" + 
					"	cc,\r\n" + 
					"	sc,\r\n" + 
					"	wc,\r\n" + 
					"	ec,\r\n" + 
					"	nc,\r\n" + 
					"	atc,\r\n" + 
					"	anc,\r\n" + 
					"	swc,\r\n" + 
					"	mod,\r\n" + 
					"	sfc,\r\n" + 
					"	un,\r\n" + 
					"	sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un) as total\r\n" + 
					"from olap_cue_all_india_auth_a_veh\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13\r\n" + 
					"order by 1\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("gnl_spl"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("cc"));
				list.add(rs1.getString("sc"));
				list.add(rs1.getString("wc"));
				list.add(rs1.getString("ec"));
				list.add(rs1.getString("nc"));
				list.add(rs1.getString("atc"));
				list.add(rs1.getString("anc"));
				list.add(rs1.getString("swc"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("sfc"));
				list.add(rs1.getString("un"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 2. END

	// 3. ALL INDIA FF AND NFF B VEH

	public ArrayList<ArrayList<String>> all_india_ff_nff_B_vehreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	gnl_spl,\r\n" + 
					"	group_name,\r\n" + 
					"	(case when reserve!=0 then reserve else 0 end) as reserve,\r\n" + 
					"	sum(ff) as ff,\r\n" + 
					"	sum(nff) as nff,\r\n" + 
					"	sum(total) as total,\r\n" + 
					"	sum(case when grand_total!=0 then grand_total else 0 end) as grand_total\r\n" + 
					"from olap_cue_all_india_auth_b_veh_ff_nff\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3\r\n" + 
					"order by 1\r\n";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("gnl_spl"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("ff"));
				list.add(rs1.getString("nff"));
				list.add(rs1.getString("reserve"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("grand_total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 3. END

	// 4. ALL INDIA FF NFF A VEH

	public ArrayList<ArrayList<String>> all_india_ff_nff_A_vehreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	gnl_spl,\r\n" + 
					"	group_name,\r\n" + 
					"	sum(case when ff!=0 then ff else 0 end) as ff,\r\n" + 
					"	sum(case when nff!=0 then nff else 0 end) as nff,\r\n" + 
					"	sum(case when total!=0 then total else 0 end) as total \r\n" + 
					"from olap_cue_all_india_auth_a_veh_ff_nff\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2\r\n" + 
					"order by 1\r\n";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("gnl_spl"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("ff"));
				list.add(rs1.getString("nff"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 4. END

	// 1. EST ALL INDIA UNIT ENT B VEH

	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENT_OF_B_VEH_RESERVErprt(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	prf_group,\r\n" + 
					"	mct,\r\n" + 
					"	std_nomclature,\r\n" + 
					"	sum(ue) as ue\r\n" + 
					"from olap_cue_all_india_unit_entl_b_veh_w_o_reserve\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3\r\n" + 
					"order by 1\r\n";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("prf_group"));
				list.add(rs1.getString("mct"));
				list.add(rs1.getString("std_nomclature"));
				list.add(rs1.getString("ue"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 1. EST END

	// 2. EST ALL INDIA UNIT ENTI B VEH W/O RESERVE FF NFF

	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENTI_OF_B_VEH_WITHreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	prf_group,\r\n" + 
					"	group_name,\r\n" + 
					"	mct,\r\n" + 
					"	sum(case when ff!=0 then ff else 0 end) as ff,\r\n" + 
					"	sum(case when nff!=0 then nff else 0 end) as nff,\r\n" + 
					"	sum(case when total!=0 then total else 0 end) as total,\r\n" + 
					"	sum(case when reserve!=0 then reserve else 0 end) as reserve,\r\n" + 
					"	sum(case when grand_total!=0 then grand_total else 0 end) as grand_total\r\n" + 
					"from olap_cue_all_india_unit_entl_b_veh_w_o_reserve_ff_nff\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)"
					+ "group by 1,2,3"
					+ "order by 1";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("prf_group"));
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("mct"));
				list.add(rs1.getString("ff"));
				list.add(rs1.getString("nff"));
				list.add(rs1.getString("total"));
				list.add(rs1.getString("reserve"));
				list.add(rs1.getString("grand_total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 2. EST END
	
	// 3. EST ALL INDIA UNIT EST OF B VEH INC DEC

	public ArrayList<ArrayList<String>> ALL_INDIA_UNIT_ENT_OF_B_VEH_INC_DEC(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	prf_group,\r\n" + 
					"	mct,\r\n" + 
					"	std_nomclature,\r\n" + 
					"	cur,\r\n" + 
					"	pre,\r\n" + 
					"	cur-pre as icr_decr\r\n" + 
					"from\r\n" + 
					"(select \r\n" + 
					"	prf_group,\r\n" + 
					"	mct,\r\n" + 
					"	std_nomclature,\r\n" + 
					"	sum(case when cast(substr(cast(insert_date as varchar),1,4) as integer)=cast(substr(cast(now() as varchar),1,4) as integer)-1 then ue else 0 end) as pre,\r\n" + 
					"	sum(case when cast(substr(cast(now() as varchar),1,4) as integer)=cast(substr(cast(insert_date as varchar),1,4) as integer) then ue else 0 end) as cur\r\n" + 
					"from olap_cue_all_india_unit_ent_b_veh_w_o_rsrv_incr_decr\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3"
					+ "order by 1 ) as foo"
					+ "\r\n";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("prf_group"));
				list.add(rs1.getString("mct"));
				list.add(rs1.getString("std_nomclature"));
				list.add(rs1.getString("cur"));
				list.add(rs1.getString("pre"));
				list.add(rs1.getString("icr_decr"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 3. EST END

	// 4. EST DATA VERIFICATION REPORT

	public ArrayList<ArrayList<String>> DATA_VERIFICATIONreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select\r\n" + 
					"	arm_code,\r\n" + 
					"	arm_desc,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	unit_type,\r\n" + 
					"	force_type,\r\n" + 
					"	modification,\r\n" + 
					"	prf_group,\r\n" + 
					"	mct_no,\r\n" + 
					"	std_nomclature,\r\n" + 
					"	cast(ue as integer),"
					+ "no_of_units,\r\n" + 
					"	cast(total_auth as integer) \r\n" + 
					"from olap_cue_data_verification_report\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)"
					+ "order by arm_code,force_type,prf_group,mct_no\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_code"));
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("unit_type"));
				list.add(rs1.getString("force_type"));
				list.add(rs1.getString("modification"));
				list.add(rs1.getString("prf_group"));
				list.add(rs1.getString("mct_no"));
				list.add(rs1.getString("std_nomclature"));
				list.add(rs1.getString("no_of_units"));
				list.add(rs1.getString("ue"));
				list.add(rs1.getString("total_auth"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 4. EST END

	// 5. EST ALL INDIA A VEH

	public ArrayList<ArrayList<String>> ALL_INDIA_AUTH_A_VEHreport(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	group_name,\r\n" + 
					"	(case when ff!=0 then ff else 0 end) as ff,\r\n" + 
					"	(case when nff!=0 then nff else 0 end) as nff,\r\n" + 
					"	(case when total!=0 then total else 0 end) as total\r\n" + 
					"from olap_cue_all_india_unit_entl_a_veh_w_o_reserve_ff_nff\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("ff"));
				list.add(rs1.getString("nff"));
				list.add(rs1.getString("total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 5. EST END

	// 6. EST ALL INDIA AUTH A VEH COMD

	public ArrayList<ArrayList<String>> ALL_INDIA_AUTH_A_VEHCOMD(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select\r\n" + 
					"	group_name,\r\n" + 
					"	cc,\r\n" + 
					"	sc,\r\n" + 
					"	wc,\r\n" + 
					"	ec,\r\n" + 
					"	nc,\r\n" + 
					"	atc,\r\n" + 
					"	anc,\r\n" + 
					"	swc,\r\n" + 
					"	mod,\r\n" + 
					"	sfc,\r\n" + 
					"	un,\r\n" + 
					"	sum(cc+sc+wc+ec+nc+atc+anc+swc+mod+sfc+un) as total\r\n" + 
					"from olap_cue_all_india_unit_entl_a_veh_w_o_reserve_comd_wise\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12\r\n" + 
					"";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("group_name"));
				list.add(rs1.getString("cc"));
				list.add(rs1.getString("sc"));
				list.add(rs1.getString("wc"));
				list.add(rs1.getString("ec"));
				list.add(rs1.getString("nc"));
				list.add(rs1.getString("atc"));
				list.add(rs1.getString("anc"));
				list.add(rs1.getString("swc"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("sfc"));
				list.add(rs1.getString("un"));
				list.add(rs1.getString("total"));

				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 6. EST END
			 
/*Weapon*/

	// 1. ITEM CARD

	public ArrayList<ArrayList<String>> item_card_report(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + "	arm_desc,\r\n" + "	unit_name,\r\n" + "	auth,\r\n" + "	description,\r\n"
					+ "	cast(scale as integer),\r\n" + "	cast(units as integer),\r\n"
					+ "	cast(total as integer)\r\n" + "from olap_cue_item_card\r\n"
					+ "where cast(insert_date as Date) between cast(? as Date) and cast(? as Date) order by arm_desc";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("auth"));
				list.add(rs1.getString("description"));
				list.add(rs1.getString("scale"));
				list.add(rs1.getString("units"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 1. END

	// 2. UNIT CARD

	public ArrayList<ArrayList<String>> unit_card_report(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	ces_cces as auth,\r\n" + 
					"	item_type as nomen,\r\n" + 
					"	ces_cces_no as description,\r\n" + 
					"	cast(auth_weapon as integer) as scale,\r\n" + 
					"	no_of_units\r\n" + 
					"from olap_cue_unit_card\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"order by item_type";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("auth"));
				list.add(rs1.getString("nomen"));
				list.add(rs1.getString("description"));
				list.add(rs1.getString("scale"));
				list.add(rs1.getString("no_of_units"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 2. END

			// 3. ALL INDIA UE REPORT PRO POLICY
				 
				 public ArrayList<ArrayList<String>> all_india_ue_report(String from,String to) {
						ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						try{	  
							conn = dataSource.getConnection();
							PreparedStatement stmt =null;
							String sql1="";	
								sql1= "select\r\n" + 
										"	prf_group_code,\r\n" + 
										"	item_code,\r\n" + 
										"	item_type,\r\n" + 
										"	coss_section,\r\n" + 
										"	ff,\r\n" + 
										"	nff,\r\n" + 
										"	ces,\r\n" + 
										"	cur,\r\n" + 
										"	pre,\r\n" + 
										"	cur-pre as diff\r\n" + 
										"from\r\n" + 
										"(select \r\n" + 
										"	prf_group_code,\r\n" + 
										"	item_code,\r\n" + 
										"	item_type,\r\n" + 
										"	coss_section,\r\n" + 
										"	ff,\r\n" + 
										"	nff,\r\n" + 
										"	ces,\r\n" + 
										"	sum(case when cast(substr(cast(insert_date as varchar),1,4) as integer)=cast(substr(cast(now() as varchar),1,4) as integer)-1 then ff+nff+ces else 0 end) as pre,\r\n" + 
										"	sum(case when cast(substr(cast(now() as varchar),1,4) as integer)=cast(substr(cast(insert_date as varchar),1,4) as integer) then ff+nff+ces else 0 end) as cur\r\n" + 
										"from olap_cue_pro_all_india_ue\r\n" + 
										"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
										"group by 1,2,3,4,5,6,7) as foo\r\n" + 
										"";
								stmt = conn.prepareStatement(sql1);
								stmt.setString(1, from);
								stmt.setString(2, to);
								ResultSet rs1 = stmt.executeQuery(); 
							    while(rs1.next()){
							    	  ArrayList<String> list = new ArrayList<String>();
							    	  list.add(rs1.getString("prf_group_code"));
							    	  list.add(rs1.getString("item_code"));
							    	  list.add(rs1.getString("item_type"));
							    	  list.add(rs1.getString("coss_section"));
							    	  list.add(rs1.getString("ff"));
							    	  list.add(rs1.getString("nff"));
							    	  list.add(rs1.getString("ces"));
							    	  list.add(rs1.getString("pre"));
							    	  list.add(rs1.getString("cur"));
							    	  list.add(rs1.getString("diff"));
							    	  alist.add(list);
						        }
							    rs1.close();

							stmt.close();
						}
						catch (SQLException e) {
							e.printStackTrace();
						} 
						return alist;
					}
				 
			// 3. END

	// 4. COMD WISE UE

	public ArrayList<ArrayList<String>> comd_wise_report(String from, String to, String comd_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			
			sql1 = "select\r\n" + 
					"	unit_name,\r\n" + 
					"	corps_name,\r\n" + 
					"	div_name,\r\n" + 
					"	bde_name,\r\n" + 
					"	item_seq_no,\r\n" + 
					"	nomen,\r\n" + 
					"	auth,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	base,\r\n" + 
					"	fn,\r\n" + 
					"	mod,\r\n" + 
					"	total\r\n" + 
					"from olap_cue_comd_ue\r\n" + 
					"where comd_name =? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)";
			
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, comd_name);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("corps_name"));
				list.add(rs1.getString("div_name"));
				list.add(rs1.getString("bde_name"));
				list.add(rs1.getString("item_seq_no"));
				list.add(rs1.getString("nomen"));
				list.add(rs1.getString("auth"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("base"));
				list.add(rs1.getString("fn"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 4. END

	// 5. COMD WISE UE REPORT

	public ArrayList<ArrayList<String>> comd_wise_ue_report(String from, String to) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	item_code,\r\n" + 
					"	nomen,\r\n" + 
					"	sc,\r\n" + 
					"	nc,\r\n" + 
					"	wc,\r\n" + 
					"	cc,\r\n" + 
					"	swc,ec,\r\n" + 
					"	anc,\r\n" + 
					"	un,\r\n" + 
					"	mod,\r\n" + 
					"	sfc,\r\n" + 
					"	atc,\r\n" + 
					"	ff_total,\r\n" + 
					"	nff_total,\r\n" + 
					"	trg_total,\r\n" + 
					"	sum(ff_total+nff_total+trg_total) as grand_total\r\n" + 
					"from olap_cue_prf_wise_comd_ue\r\n" + 
					"where cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16\r\n" + 
					"order by nomen";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, from);
			stmt.setString(2, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("item_code"));
				list.add(rs1.getString("nomen"));
				list.add(rs1.getString("sc"));
				list.add(rs1.getString("nc"));
				list.add(rs1.getString("wc"));
				list.add(rs1.getString("cc"));
				list.add(rs1.getString("swc"));
				list.add(rs1.getString("ec"));
				list.add(rs1.getString("anc"));
				list.add(rs1.getString("un"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("sfc"));
				list.add(rs1.getString("atc"));
				list.add(rs1.getString("ff_total"));
				list.add(rs1.getString("nff_total"));
				list.add(rs1.getString("trg_total"));
				list.add(rs1.getString("grand_total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 5. END

	// 6. ITEM CARD REPORT

	public ArrayList<ArrayList<String>> item_card_comd_report(String from, String to, String item_type) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select \r\n" + 
					"	arm_desc,\r\n" + 
					"	unit_name,\r\n" + 
					"	comd_name,\r\n" + 
					"	corps_name,\r\n" + 
					"	div_name,\r\n" + 
					"	bde_name,\r\n" + 
					"	auth,\r\n" + 
					"	we_pe_no,\r\n" + 
					"	total\r\n" + 
					"from olap_cue_item_card_comd_ue\r\n" + 
					"where item_type=? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n" + 
					"order by comd_name\r\n";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, item_type);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("arm_desc"));
				list.add(rs1.getString("unit_name"));
				list.add(rs1.getString("comd_name"));
				list.add(rs1.getString("corps_name"));
				list.add(rs1.getString("div_name"));
				list.add(rs1.getString("bde_name"));
				list.add(rs1.getString("auth"));
				list.add(rs1.getString("we_pe_no"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	// 6. END

	// 7. UNIT CARD COMD WISE REPORT
	public ArrayList<ArrayList<String>> unit_card_comd_report(String from, String to, String unit_name) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";

			sql1 = "select \r\n" + 
					"	comd_name,\r\n" + 
					"	corps_name,\r\n" + 
					"	div_name,\r\n" + 
					"	bde_name,\r\n" + 
					"	item_seq_no,\r\n" + 
					"	weap_nomen,\r\n" + 
					"	base,\r\n" + 
					"	mod,\r\n" + 
					"	fn,\r\n" + 
					"	total\r\n" + 
					"from olap_cue_unit_card_comd_ue\r\n" + 
					"where unit_name = ? and cast(insert_date as Date) between cast(? as Date) and cast(? as Date)\r\n";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, unit_name);
			stmt.setString(2, from);
			stmt.setString(3, to);
			ResultSet rs1 = stmt.executeQuery();
			while (rs1.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs1.getString("comd_name"));
				list.add(rs1.getString("corps_name"));
				list.add(rs1.getString("div_name"));
				list.add(rs1.getString("bde_name"));
				list.add(rs1.getString("item_seq_no"));
				list.add(rs1.getString("weap_nomen"));
				list.add(rs1.getString("base"));
				list.add(rs1.getString("mod"));
				list.add(rs1.getString("fn"));
				list.add(rs1.getString("total"));
				alist.add(list);
			}
			rs1.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}
	// 7. END
}
