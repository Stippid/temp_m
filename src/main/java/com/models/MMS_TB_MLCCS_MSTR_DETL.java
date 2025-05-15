package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mms_tb_mlccs_mstr_detl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class MMS_TB_MLCCS_MSTR_DETL {
	
	
	
	private String req_tr_id;
	private String auth_lett_no;
	
	private String auth_date;
	private String cos_sec;
	private String prf_code;
	private String prf_group;
	private String cat_part_no;
	/*private String census_seq_no;*/
	private String census_no;
	private String nomen;
	private String brief_desc;
	private String au ;
	private String item_status;
	private String item_category;
	private String origin_country;
	private String manuf_agency;
	private String ahsp_agency;
	private String induc_year;
	private String nato_stk_no;
	private String def_cat_no_dcan;
	private String ces_no;
	private String upload_file_name;
	private String spl_remarks;
	private String remarks;
	private String data_cr_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date = new Date();
	
	private String data_upd_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_upd_date;
	private String data_app_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_app_date;
	private String op_status;
	private String class_category;
	private String dte_category;
	private String dte_eqpt_category;
	private String active_status;
	private String item_seq_no;
	private String item_code;
	private String digest_category;
	private String eqpt_priority;
	private String spl_dte;
	private int  roleid;
	
	private double cost;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReq_tr_id() {
		return req_tr_id;
	}

	public void setReq_tr_id(String req_tr_id) {
		this.req_tr_id = req_tr_id;
	}

	public String getAuth_lett_no() {
		return auth_lett_no;
	}

	public void setAuth_lett_no(String auth_lett_no) {
		this.auth_lett_no = auth_lett_no;
	}

	public String getAuth_date() {
		return auth_date;
	}

	public void setAuth_date(String auth_date) {
		this.auth_date = auth_date;
	}

	public String getCos_sec() {
		return cos_sec;
	}

	public void setCos_sec(String cos_sec) {
		this.cos_sec = cos_sec;
	}

	public String getPrf_code() {
		return prf_code;
	}

	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}

	public String getPrf_group() {
		return prf_group;
	}

	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}

	public String getCat_part_no() {
		return cat_part_no;
	}

	public void setCat_part_no(String cat_part_no) {
		this.cat_part_no = cat_part_no;
	}

	public String getCensus_no() {
		return census_no;
	}

	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}

	public String getNomen() {
		return nomen;
	}

	public void setNomen(String nomen) {
		this.nomen = nomen;
	}

	public String getBrief_desc() {
		return brief_desc;
	}

	public void setBrief_desc(String brief_desc) {
		this.brief_desc = brief_desc;
	}

	public String getAu() {
		return au;
	}

	public void setAu(String au) {
		this.au = au;
	}

	public String getItem_status() {
		return item_status;
	}

	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}

	public String getItem_category() {
		return item_category;
	}

	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	public String getOrigin_country() {
		return origin_country;
	}

	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}

	public String getManuf_agency() {
		return manuf_agency;
	}

	public void setManuf_agency(String manuf_agency) {
		this.manuf_agency = manuf_agency;
	}

	public String getAhsp_agency() {
		return ahsp_agency;
	}

	public void setAhsp_agency(String ahsp_agency) {
		this.ahsp_agency = ahsp_agency;
	}

	public String getInduc_year() {
		return induc_year;
	}

	public void setInduc_year(String induc_year) {
		this.induc_year = induc_year;
	}

	public String getNato_stk_no() {
		return nato_stk_no;
	}

	public void setNato_stk_no(String nato_stk_no) {
		this.nato_stk_no = nato_stk_no;
	}

	public String getDef_cat_no_dcan() {
		return def_cat_no_dcan;
	}

	public void setDef_cat_no_dcan(String def_cat_no_dcan) {
		this.def_cat_no_dcan = def_cat_no_dcan;
	}

	public String getCes_no() {
		return ces_no;
	}

	public void setCes_no(String ces_no) {
		this.ces_no = ces_no;
	}

	public String getUpload_file_name() {
		return upload_file_name;
	}

	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}

	public String getSpl_remarks() {
		return spl_remarks;
	}

	public void setSpl_remarks(String spl_remarks) {
		this.spl_remarks = spl_remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getData_cr_by() {
		return data_cr_by;
	}

	public void setData_cr_by(String data_cr_by) {
		this.data_cr_by = data_cr_by;
	}

	public Date getData_cr_date() {
		return data_cr_date;
	}

	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}

	public String getData_upd_by() {
		return data_upd_by;
	}

	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}

	public Date getData_upd_date() {
		return data_upd_date;
	}

	public void setData_upd_date(Date data_upd_date) {
		this.data_upd_date = data_upd_date;
	}

	public String getData_app_by() {
		return data_app_by;
	}

	public void setData_app_by(String data_app_by) {
		this.data_app_by = data_app_by;
	}

	public Date getData_app_date() {
		return data_app_date;
	}

	public void setData_app_date(Date data_app_date) {
		this.data_app_date = data_app_date;
	}

	public String getOp_status() {
		return op_status;
	}

	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}

	public String getClass_category() {
		return class_category;
	}

	public void setClass_category(String class_category) {
		this.class_category = class_category;
	}

	public String getDte_category() {
		return dte_category;
	}

	public void setDte_category(String dte_category) {
		this.dte_category = dte_category;
	}

	public String getDte_eqpt_category() {
		return dte_eqpt_category;
	}

	public void setDte_eqpt_category(String dte_eqpt_category) {
		this.dte_eqpt_category = dte_eqpt_category;
	}

	public String getActive_status() {
		return active_status;
	}

	public void setActive_status(String active_status) {
		this.active_status = active_status;
	}

	public String getItem_seq_no() {
		return item_seq_no;
	}

	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getDigest_category() {
		return digest_category;
	}

	public void setDigest_category(String digest_category) {
		this.digest_category = digest_category;
	}

	public String getEqpt_priority() {
		return eqpt_priority;
	}

	public void setEqpt_priority(String eqpt_priority) {
		this.eqpt_priority = eqpt_priority;
	}

	public String getSpl_dte() {
		return spl_dte;
	}

	public void setSpl_dte(String spl_dte) {
		this.spl_dte = spl_dte;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	
}
