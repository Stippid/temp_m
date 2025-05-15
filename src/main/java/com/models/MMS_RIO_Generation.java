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
@Table(name = "mms_tb_rio_mstr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class MMS_RIO_Generation {
	
	private int id;
	private String rio_no;
	private String ro_agency;
	private String type_of_issue;
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ro_date;*/
	private String ro_date;
	private String ro_type;
	private String ro_type_n;
	private String to_sus_no;
	private String to_comd_sus;
	private String prf_code;
	private String type_of_hldg;
	private String type_of_eqpt;
	private int ro_qty;
	private String rel_depot_sus;
	private String upload_file_name;
	private String spl_remarks;
	private String remarks;
	private String data_cr_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date;
	/*@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date;*/
	private String data_upd_by; 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_upd_date; 
	private String data_app_by ;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_app_date ;
	private String op_status;
	private String loan_from_date ;
	private String loan_upto_date;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ro_valid_upto;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date rio_valid_upto;
	private String ro_no;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date rio_date;
	private int role_id;
	private String ro_for;
	private String census_no;	
	private String ro_ue;
	private String ro_uh;
    private int ro_id;
    private String type_of_stk;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRo_agency() {
		return ro_agency;
	}
	public void setRo_agency(String ro_agency) {
		this.ro_agency = ro_agency;
	}
	/*public String getRo_date() {
		return ro_date;
	}
	public void setRo_date(String ro_date) {
		this.ro_date = ro_date;
	}*/
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
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}
	public String getTo_comd_sus() {
		return to_comd_sus;
	}
	public void setTo_comd_sus(String to_comd_sus) {
		this.to_comd_sus = to_comd_sus;
	}
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
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
	public int getRo_qty() {
		return ro_qty;
	}
	public void setRo_qty(int ro_qty) {
		this.ro_qty = ro_qty;
	}
	public String getRel_depot_sus() {
		return rel_depot_sus;
	}
	public void setRel_depot_sus(String rel_depot_sus) {
		this.rel_depot_sus = rel_depot_sus;
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
	/*public Date getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}*/
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
	public Date getRo_valid_upto() {
		return ro_valid_upto;
	}
	public void setRo_valid_upto(Date ro_valid_upto) {
		this.ro_valid_upto = ro_valid_upto;
	}
	public String getRo_no() {
		return ro_no;
	}
	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}
	public Date getRio_date() {
		return rio_date;
	}
	public void setRio_date(Date rio_date) {
		this.rio_date = rio_date;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	/*public Date getRo_date() {
		return ro_date;
	}
	public void setRo_date(Date ro_date) {
		this.ro_date = ro_date;
	}*/
	public String getRo_date() {
		return ro_date;
	}
	public void setRo_date(String ro_date) {
		this.ro_date = ro_date;
	}
	public Date getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}
	public String getRio_no() {
		return rio_no;
	}
	public void setRio_no(String rio_no) {
		this.rio_no = rio_no;
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
	public String getRo_for() {
		return ro_for;
	}
	public void setRo_for(String ro_for) {
		this.ro_for = ro_for;
	}
	public Date getRio_valid_upto() {
		return rio_valid_upto;
	}
	public void setRio_valid_upto(Date rio_valid_upto) {
		this.rio_valid_upto = rio_valid_upto;
	}
	public int getRo_id() {
		return ro_id;
	}
	public void setRo_id(int ro_id) {
		this.ro_id = ro_id;
	}
	public String getType_of_stk() {
		return type_of_stk;
	}
	public void setType_of_stk(String type_of_stk) {
		this.type_of_stk = type_of_stk;
	}

}
