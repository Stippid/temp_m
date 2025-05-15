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
@Table(name = "mms_tb_comd_ro_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_COMD_RO_MSTR {
	

	
	private int id;
	private String data_app_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_app_date ;
	private String data_cr_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date;
	private String data_upd_by; 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_upd_date; 
	private String loan_from_date ;
	private String loan_upto_date;
	private String op_status;
	private String prf_code;
	private String rel_depot_sus;
	private String remarks;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date rio_date;
	private String ro_agency;
	
	private String ro_date;
	private String ro_no;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date comd_ro_date;
	private String comd_ro_no;
	private int ro_qty;
	private String ro_type;
	private String ro_type_n;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ro_valid_upto;
	private int role_id;
	private String spl_remarks;
	 private String from_comd_sus;
	 private String to_sus_no;
	 private String type_of_eqpt;
	 private String type_of_hldg;
	 private String upload_file_name;
	 
	 private String ro_for;
	 private String type_of_issue;
	 private String census_no;
	 private String material_no;
	 private String ro_ue;
	 private String ro_uh;
	 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getData_cr_by() {
		return data_cr_by;
	}
	public void setData_cr_by(String data_cr_by) {
		this.data_cr_by = data_cr_by;
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
	public String getLoan_from_date() {
		return loan_from_date;
	}
	public void setLoan_from_date(String loan_from_date) {
		this.loan_from_date = loan_from_date;
	}
	public String getLoan_upto_date() {
		return loan_upto_date;
	}
	public void setLoan_upto_date(String loan_upto_date) {
		this.loan_upto_date = loan_upto_date;
	}
	public String getOp_status() {
		return op_status;
	}
	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public String getRel_depot_sus() {
		return rel_depot_sus;
	}
	public void setRel_depot_sus(String rel_depot_sus) {
		this.rel_depot_sus = rel_depot_sus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getRio_date() {
		return rio_date;
	}
	public void setRio_date(Date rio_date) {
		this.rio_date = rio_date;
	}
	public String getRo_agency() {
		return ro_agency;
	}
	public void setRo_agency(String ro_agency) {
		this.ro_agency = ro_agency;
	}

	public String getRo_date() {
		return ro_date;
	}
	public void setRo_date(String ro_date) {
		this.ro_date = ro_date;
	}
	public String getRo_no() {
		return ro_no;
	}
	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public void setComd_ro_no(String comd_ro_no) {
		this.comd_ro_no = comd_ro_no;
	}
	public int getRo_qty() {
		return ro_qty;
	}
	public void setRo_qty(int ro_qty) {
		this.ro_qty = ro_qty;
	}
	public String getRo_type() {
		return ro_type;
	}
	public void setRo_type(String ro_type) {
		this.ro_type = ro_type;
	}
	public String getRo_type_n() {
		return ro_type_n;
	}
	public void setRo_type_n(String ro_type_n) {
		this.ro_type_n = ro_type_n;
	}
	public Date getRo_valid_upto() {
		return ro_valid_upto;
	}
	public void setRo_valid_upto(Date ro_valid_upto) {
		this.ro_valid_upto = ro_valid_upto;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getSpl_remarks() {
		return spl_remarks;
	}
	public void setSpl_remarks(String spl_remarks) {
		this.spl_remarks = spl_remarks;
	}
	public String getFrom_comd_sus() {
		return from_comd_sus;
	}
	public void setFrom_comd_sus(String from_comd_sus) {
		this.from_comd_sus = from_comd_sus;
	}
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}
	public String getType_of_eqpt() {
		return type_of_eqpt;
	}
	public void setType_of_eqpt(String type_of_eqpt) {
		this.type_of_eqpt = type_of_eqpt;
	}
	public String getType_of_hldg() {
		return type_of_hldg;
	}
	public void setType_of_hldg(String type_of_hldg) {
		this.type_of_hldg = type_of_hldg;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public String getRo_for() {
		return ro_for;
	}
	public void setRo_for(String ro_for) {
		this.ro_for = ro_for;
	}
	public String getType_of_issue() {
		return type_of_issue;
	}
	public void setType_of_issue(String type_of_issue) {
		this.type_of_issue = type_of_issue;
	}
	public String getCensus_no() {
		return census_no;
	}
	public void setCensus_no(String census_no) {
		this.census_no = census_no;
	}
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getRo_ue() {
		return ro_ue;
	}
	public void setRo_ue(String ro_ue) {
		this.ro_ue = ro_ue;
	}
	public String getRo_uh() {
		return ro_uh;
	}
	public void setRo_uh(String ro_uh) {
		this.ro_uh = ro_uh;
	}

	public Date getComd_ro_date() {
		return comd_ro_date;
	}
	public void setComd_ro_date(Date comd_ro_date) {
		this.comd_ro_date = comd_ro_date;
	}
	public String getComd_ro_no() {
		return comd_ro_no;
	}
	public Date getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}
	
	
	
	 
	 
	 

}
