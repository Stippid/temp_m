package com.models.fpmis;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "fp.fp_tb_cda_detl20", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tb_cda_detl20_model {
	
	private int id;
	private int exp_id;
	private String sus_no;
	private String form_code_control;
	private int fwd_amt;
	private String fwd_ref;
	private String fwd_by;
	private Date fwd_date;
	private int bkd_amt;
	private String bkd_ref;
	private String bkd_by;
	private Date bkd_date;
	private String bkd_status;
	private String bkd_remarks;
	private String data_upd_by;
	private Date data_upd_date;
	private String tr_head;

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
	public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	public int getExp_id() {
		return exp_id;
	}
	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}
	public int getFwd_amt() {
		return fwd_amt;
	}
	public void setFwd_amt(int fwd_amt) {
		this.fwd_amt = fwd_amt;
	}
	public String getFwd_ref() {
		return fwd_ref;
	}
	public void setFwd_ref(String fwd_ref) {
		this.fwd_ref = fwd_ref;
	}
	public String getFwd_by() {
		return fwd_by;
	}
	public void setFwd_by(String fwd_by) {
		this.fwd_by = fwd_by;
	}
	public Date getFwd_date() {
		return fwd_date;
	}
	public void setFwd_date(Date fwd_date) {
		this.fwd_date = fwd_date;
	}
	public int getBkd_amt() {
		return bkd_amt;
	}
	public void setBkd_amt(int bkd_amt) {
		this.bkd_amt = bkd_amt;
	}
	public String getBkd_ref() {
		return bkd_ref;
	}
	public void setBkd_ref(String bkd_ref) {
		this.bkd_ref = bkd_ref;
	}
	public String getBkd_by() {
		return bkd_by;
	}
	public void setBkd_by(String bkd_by) {
		this.bkd_by = bkd_by;
	}
	public Date getBkd_date() {
		return bkd_date;
	}
	public void setBkd_date(Date bkd_date) {
		this.bkd_date = bkd_date;
	}
	public String getBkd_status() {
		return bkd_status;
	}
	public void setBkd_status(String bkd_status) {
		this.bkd_status = bkd_status;
	}
	public String getBkd_remarks() {
		return bkd_remarks;
	}
	public void setBkd_remarks(String bkd_remarks) {
		this.bkd_remarks = bkd_remarks;
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
	public String getTr_head() {
		return tr_head;
	}
	public void setTr_head(String tr_head) {
		this.tr_head = tr_head;
	}
	
}