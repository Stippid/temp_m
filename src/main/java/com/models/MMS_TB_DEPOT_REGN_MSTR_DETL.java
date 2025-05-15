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
@Table(name = "mms_tb_depot_regn_mstr_detl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_DEPOT_REGN_MSTR_DETL {

	
	  private int id;	  
	  private String sus_no; 
	  private int census_seq_no;
	  private String  census_no;
	  private String type_of_hldg;
	  private String type_of_eqpt;
	  private String eqpt_regn_no;
	  private String regn_seq_no;
	  private String from_sus_no;
	  private String from_form_code;
	  private Date from_tr_date;
	  private String to_sus_no;
	  private String to_form_code;
	  private Date to_tr_date;
	  private String barrel1_detl;
	  private String barrel2_detl;
	  private String barrel3_detl;
	  private String barrel4_detl;
	  private String eqpt_ser_no;
	  private String eqpt_batch_no;
	  private String eqpt_part_no;
	  private String service_status;
	  private String spl_remarks; 
	  private String remarks;
	  private String data_cr_by;
	  private Date data_cr_date;
	  private String data_upd_by;
	  private Date data_upd_date;
	  private String  data_app_by;
	  private Date data_app_date;
	  private String op_status;
	  private String tfr_status;
	  private String rv_no;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date rv_date;
	  private String prf_code;
	  private int depres_dur_year;
	  
	  
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public int getCensus_seq_no() {
		return census_seq_no;
	}
	public void setCensus_seq_no(int census_seq_no) {
		this.census_seq_no = census_seq_no;
	}
	public String getCensus_no() {
		return census_no;
	}
	public void setCensus_no(String census_no) {
		this.census_no = census_no;
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
	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}
	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}
	public String getRegn_seq_no() {
		return regn_seq_no;
	}
	public void setRegn_seq_no(String regn_seq_no) {
		this.regn_seq_no = regn_seq_no;
	}
	public String getFrom_sus_no() {
		return from_sus_no;
	}
	public void setFrom_sus_no(String from_sus_no) {
		this.from_sus_no = from_sus_no;
	}
	public String getFrom_form_code() {
		return from_form_code;
	}
	public void setFrom_form_code(String from_form_code) {
		this.from_form_code = from_form_code;
	}
	public Date getFrom_tr_date() {
		return from_tr_date;
	}
	public void setFrom_tr_date(Date from_tr_date) {
		this.from_tr_date = from_tr_date;
	}
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}
	public String getTo_form_code() {
		return to_form_code;
	}
	public void setTo_form_code(String to_form_code) {
		this.to_form_code = to_form_code;
	}
	public Date getTo_tr_date() {
		return to_tr_date;
	}
	public void setTo_tr_date(Date to_tr_date) {
		this.to_tr_date = to_tr_date;
	}
	public String getBarrel1_detl() {
		return barrel1_detl;
	}
	public void setBarrel1_detl(String barrel1_detl) {
		this.barrel1_detl = barrel1_detl;
	}
	public String getBarrel2_detl() {
		return barrel2_detl;
	}
	public void setBarrel2_detl(String barrel2_detl) {
		this.barrel2_detl = barrel2_detl;
	}
	public String getBarrel3_detl() {
		return barrel3_detl;
	}
	public void setBarrel3_detl(String barrel3_detl) {
		this.barrel3_detl = barrel3_detl;
	}
	public String getBarrel4_detl() {
		return barrel4_detl;
	}
	public void setBarrel4_detl(String barrel4_detl) {
		this.barrel4_detl = barrel4_detl;
	}
	public String getEqpt_ser_no() {
		return eqpt_ser_no;
	}
	public void setEqpt_ser_no(String eqpt_ser_no) {
		this.eqpt_ser_no = eqpt_ser_no;
	}
	public String getEqpt_batch_no() {
		return eqpt_batch_no;
	}
	public void setEqpt_batch_no(String eqpt_batch_no) {
		this.eqpt_batch_no = eqpt_batch_no;
	}
	public String getEqpt_part_no() {
		return eqpt_part_no;
	}
	public void setEqpt_part_no(String eqpt_part_no) {
		this.eqpt_part_no = eqpt_part_no;
	}
	public String getService_status() {
		return service_status;
	}
	public void setService_status(String service_status) {
		this.service_status = service_status;
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
	public String getTfr_status() {
		return tfr_status;
	}
	public void setTfr_status(String tfr_status) {
		this.tfr_status = tfr_status;
	}
	public String getRv_no() {
		return rv_no;
	}
	public void setRv_no(String rv_no) {
		this.rv_no = rv_no;
	}
	public Date getRv_date() {
		return rv_date;
	}
	public void setRv_date(Date rv_date) {
		this.rv_date = rv_date;
	}
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public int getDepres_dur_year() {
		return depres_dur_year;
	}
	public void setDepres_dur_year(int depres_dur_year) {
		this.depres_dur_year = depres_dur_year;
	}
	
}
