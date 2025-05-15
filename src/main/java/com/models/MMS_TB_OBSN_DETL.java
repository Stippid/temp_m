package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mms_tb_obsn_detl", uniqueConstraints = {@UniqueConstraint(columnNames = "tr_id")})
public class MMS_TB_OBSN_DETL {

	private int tr_id;
	private String  mth; 
	private String  yr; 
	private String deo; 
	private String sus_no; 
	private String  cert_opt1; 
	private String cert_opt2; 
	private String obsn1; 
	private String  obsn2; 
	private String  obsn3; 
	private String  obsn4; 
	private String  obsn5; 
	private String  obsn1_res;
	private String  obsn2_res; 
	private String  obsn3_res; 
	private String obsn4_res; 
	private String obsn5_res; 
	private String data_upd_by;
	private String  data_upd_date; 
	private String data_chk_by; 
	private String data_chk_date; 
	private String data_cr_by; 
	private String data_cr_date; 
	private String  latest;
	private String census_no;
	private String  census_seq_no;
	private String  type_of_hldg; 
	private String  type_of_eqpt; 
	private String material_no;
	private String obsn1_act;
	private String obsn2_act;
	private String obsn3_act;
	private String obsn4_act;
	private String obsn5_act;
	private String obsn_status;
	
	private String unit_upload_document;
	private String unit_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tr_id", unique = true, nullable = false)
	public int getTr_id() {
		return tr_id;
	}
	public void setTr_id(int tr_id) {
		this.tr_id = tr_id;
	}

	
	public String getMth() {
		return mth;
	}
	public void setMth(String mth) {
		this.mth = mth;
	}
	public String getYr() {
		return yr;
	}
	public void setYr(String yr) {
		this.yr = yr;
	}
	public String getDeo() {
		return deo;
	}
	public void setDeo(String deo) {
		this.deo = deo;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getCert_opt1() {
		return cert_opt1;
	}
	public void setCert_opt1(String cert_opt1) {
		this.cert_opt1 = cert_opt1;
	}
	public String getCert_opt2() {
		return cert_opt2;
	}
	public void setCert_opt2(String cert_opt2) {
		this.cert_opt2 = cert_opt2;
	}
	public String getObsn1() {
		return obsn1;
	}
	public void setObsn1(String obsn1) {
		this.obsn1 = obsn1;
	}
	public String getObsn2() {
		return obsn2;
	}
	public void setObsn2(String obsn2) {
		this.obsn2 = obsn2;
	}
	public String getObsn3() {
		return obsn3;
	}
	public void setObsn3(String obsn3) {
		this.obsn3 = obsn3;
	}
	public String getObsn4() {
		return obsn4;
	}
	public void setObsn4(String obsn4) {
		this.obsn4 = obsn4;
	}
	public String getObsn5() {
		return obsn5;
	}
	public void setObsn5(String obsn5) {
		this.obsn5 = obsn5;
	}
	public String getObsn1_res() {
		return obsn1_res;
	}
	public void setObsn1_res(String obsn1_res) {
		this.obsn1_res = obsn1_res;
	}
	public String getObsn2_res() {
		return obsn2_res;
	}
	public void setObsn2_res(String obsn2_res) {
		this.obsn2_res = obsn2_res;
	}
	public String getObsn3_res() {
		return obsn3_res;
	}
	public void setObsn3_res(String obsn3_res) {
		this.obsn3_res = obsn3_res;
	}
	public String getObsn4_res() {
		return obsn4_res;
	}
	public void setObsn4_res(String obsn4_res) {
		this.obsn4_res = obsn4_res;
	}
	public String getObsn5_res() {
		return obsn5_res;
	}
	public void setObsn5_res(String obsn5_res) {
		this.obsn5_res = obsn5_res;
	}
	public String getData_upd_by() {
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}
	public String getData_upd_date() {
		return data_upd_date;
	}
	public void setData_upd_date(String data_upd_date) {
		this.data_upd_date = data_upd_date;
	}
	public String getData_chk_by() {
		return data_chk_by;
	}
	public void setData_chk_by(String data_chk_by) {
		this.data_chk_by = data_chk_by;
	}
	public String getData_chk_date() {
		return data_chk_date;
	}
	public void setData_chk_date(String data_chk_date) {
		this.data_chk_date = data_chk_date;
	}
	public String getData_cr_by() {
		return data_cr_by;
	}
	public void setData_cr_by(String data_cr_by) {
		this.data_cr_by = data_cr_by;
	}
	public String getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(String data_cr_date) {
		this.data_cr_date = data_cr_date;
	}
	public String getLatest() {
		return latest;
	}
	public void setLatest(String latest) {
		this.latest = latest;
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
	public String getType_of_hldg() {
		return type_of_hldg;
	}
	public void setType_of_hldg(String type_of_hldg) {
		this.type_of_hldg = type_of_hldg;
	}
	public String getType_of_eqpt() {
		return type_of_eqpt;
	}
	public void setType_of_eqpt(String type_of_eqpt) {
		this.type_of_eqpt = type_of_eqpt;
	}
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getObsn1_act() {
		return obsn1_act;
	}
	public void setObsn1_act(String obsn1_act) {
		this.obsn1_act = obsn1_act;
	}
	public String getObsn2_act() {
		return obsn2_act;
	}
	public void setObsn2_act(String obsn2_act) {
		this.obsn2_act = obsn2_act;
	}
	public String getObsn3_act() {
		return obsn3_act;
	}
	public void setObsn3_act(String obsn3_act) {
		this.obsn3_act = obsn3_act;
	}
	public String getObsn4_act() {
		return obsn4_act;
	}
	public void setObsn4_act(String obsn4_act) {
		this.obsn4_act = obsn4_act;
	}
	public String getObsn5_act() {
		return obsn5_act;
	}
	public void setObsn5_act(String obsn5_act) {
		this.obsn5_act = obsn5_act;
	}
	public String getObsn_status() {
		return obsn_status;
	}
	public void setObsn_status(String obsn_status) {
		this.obsn_status = obsn_status;
	}
	public String getUnit_upload_document() {
		return unit_upload_document;
	}
	public void setUnit_upload_document(String unit_upload_document) {
		this.unit_upload_document = unit_upload_document;
	}
	public String getUnit_remarks() {
		return unit_remarks;
	}
	public void setUnit_remarks(String unit_remarks) {
		this.unit_remarks = unit_remarks;
	}
	
	
	
	  
}
