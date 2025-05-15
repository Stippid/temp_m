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

public class fp_tb_proj_detl_model {
	
	private int id;
	private String sus_no;
	private String form_code_control;
	private String est_type;
	private String tr_head;
	private float proj_amt;
	private float amount;
	private String remarks;
	private String data_upd_by;
	private Date data_upd_date;
	
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
	public String getEst_type() {
		return est_type;
	}
	public void setEst_type(String est_type) {
		this.est_type = est_type;
	}
	public String getTr_head() {
		return tr_head;
	}
	public void setTr_head(String tr_head) {
		this.tr_head = tr_head;
	}
	
	public float getProj_amt() {
		return proj_amt;
	}
	public void setProj_amt(float proj_amt) {
		this.proj_amt = proj_amt;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	

}