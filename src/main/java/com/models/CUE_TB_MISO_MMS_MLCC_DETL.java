/*package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "cue_tb_miso_mms_mlcc_detl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_MMS_MLCC_DETL {
	
	private String  cos_sec_no;
	public String getCos_sec_no() {
		return cos_sec_no;
	}
	public void setCos_sec_no(String cos_sec_no) {
		this.cos_sec_no = cos_sec_no;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getCensus_no() {
		return census_no;
	}
	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}
	public String getCensus_seq_no() {
		return census_seq_no;
	}
	public void setCensus_seq_no(String census_seq_no) {
		this.census_seq_no = census_seq_no;
	}
	public Integer getAcc_units() {
		return acc_units;
	}
	public void setAcc_units(Integer acc_units) {
		this.acc_units = acc_units;
	}
	public Integer getItem_status() {
		return item_status;
	}
	public void setItem_status(Integer item_status) {
		this.item_status = item_status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAuth_letr_no() {
		return auth_letr_no;
	}
	public void setAuth_letr_no(String auth_letr_no) {
		this.auth_letr_no = auth_letr_no;
	}
	public String getAuth_letr_dt() {
		return auth_letr_dt;
	}
	public void setAuth_letr_dt(String auth_letr_dt) {
		this.auth_letr_dt = auth_letr_dt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVersion_no() {
		return version_no;
	}
	public void setVersion_no(String version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getCat_part_no() {
		return cat_part_no;
	}
	public void setCat_part_no(String cat_part_no) {
		this.cat_part_no = cat_part_no;
	}
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public Integer getCategory_code() {
		return category_code;
	}
	public void setCategory_code(Integer category_code) {
		this.category_code = category_code;
	}
	public Integer getClass_a_eqpt() {
		return class_a_eqpt;
	}
	public void setClass_a_eqpt(Integer class_a_eqpt) {
		this.class_a_eqpt = class_a_eqpt;
	}
	public Integer getAso_digest() {
		return aso_digest;
	}
	public void setAso_digest(Integer aso_digest) {
		this.aso_digest = aso_digest;
	}
	public String getPrf_group_ue() {
		return prf_group_ue;
	}
	public void setPrf_group_ue(String prf_group_ue) {
		this.prf_group_ue = prf_group_ue;
	}
	public String getBrief_desc() {
		return brief_desc;
	}
	public void setBrief_desc(String brief_desc) {
		this.brief_desc = brief_desc;
	}
	public String getRepl_census() {
		return repl_census;
	}
	public void setRepl_census(String repl_census) {
		this.repl_census = repl_census;
	}
	public String getRepl_nomen() {
		return repl_nomen;
	}
	public void setRepl_nomen(String repl_nomen) {
		this.repl_nomen = repl_nomen;
	}
	public String getYear_of_induc() {
		return year_of_induc;
	}
	public void setYear_of_induc(String year_of_induc) {
		this.year_of_induc = year_of_induc;
	}
	public String getCntry_of_origin() {
		return cntry_of_origin;
	}
	public void setCntry_of_origin(String cntry_of_origin) {
		this.cntry_of_origin = cntry_of_origin;
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
	public String getNsn() {
		return nsn;
	}
	public void setNsn(String nsn) {
		this.nsn = nsn;
	}
	public String getDcan() {
		return dcan;
	}
	public void setDcan(String dcan) {
		this.dcan = dcan;
	}
	public String getContr_store() {
		return contr_store;
	}
	public void setContr_store(String contr_store) {
		this.contr_store = contr_store;
	}
	private String  creation_by;
	private String  creation_date;
	private String  item_seq_no;
	private String  item_name;
	private String  census_no;
	private String  census_seq_no;
	private Integer  acc_units;
	private Integer  item_status;
	private Integer  status;
	private String  auth_letr_no;
	private String  auth_letr_dt;
	private String  remarks;
	private String  version_no;
	private String  softdelete;
	private String  cat_part_no;
	private String  approve_by;
	private String  approve_date;
	private String  update_by;
	private String  update_date;
	private Integer  category_code;
	private Integer  class_a_eqpt;
	private Integer  aso_digest;
	private String  prf_group_ue;
	private String  brief_desc;
	private String  repl_census;
	private String  repl_nomen;
	private String  year_of_induc;
	private String  cntry_of_origin;
	private String  manuf_agency;
	private String  ahsp_agency;
	private String  nsn;
	private String  dcan;
	private String  contr_store;
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
}
*/