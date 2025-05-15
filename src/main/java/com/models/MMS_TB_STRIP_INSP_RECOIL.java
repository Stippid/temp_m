package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mms_tb_strip_insp_recoil", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_STRIP_INSP_RECOIL {
	private int id;
	private String eqpt_regn_no;
	private String recoil_sys_regd_no;
	private Date due_dt;
	private Date done_dt;
	private String periodicity;
	private String created_by;
	private Date created_on;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}
	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}
	public String getRecoil_sys_regd_no() {
		return recoil_sys_regd_no;
	}
	public void setRecoil_sys_regd_no(String recoil_sys_regd_no) {
		this.recoil_sys_regd_no = recoil_sys_regd_no;
	}
	public Date getDue_dt() {
		return due_dt;
	}
	public void setDue_dt(Date due_dt) {
		this.due_dt = due_dt;
	}
	public Date getDone_dt() {
		return done_dt;
	}
	public void setDone_dt(Date done_dt) {
		this.done_dt = done_dt;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
}
