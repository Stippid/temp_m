package com.model.InspectionReports;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_insp_establishment", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_ESTABLISHMENT {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String reg_offrs_auth;
	private String reg_offrs_posted;
	private String reg_offrs_sur;
	private String reg_offrs_defi;
	private String reg_offrs_medcat;
	private String reg_jco_auth;
	private String reg_jco_posted;
	private String reg_jco_sur;
	private String reg_jco_defi;
	private String reg_jco_medcat;
	private String reg_or_auth;
	private String reg_or_posted;
	private String reg_or_sur;
	private String reg_or_defi;
	private String reg_or_medcat;
	private String reg_civ_auth;
	private String reg_civ_posted;
	private String reg_civ_sur;
	private String reg_civ_defi;
	private String reg_civ_medcat;
	private String reg_warrant_auth;
	private String reg_warrant_posted;
	private String reg_warrant_sur;
	private String reg_warrant_defi;
	private String reg_warrant_medcat;	
	private String atted_offrs_auth;
	private String atted_offrs_posted;
	private String atted_offrs_sur;
	private String atted_offrs_defi;
	private String atted_offrs_medcat;
	private String atted_jco_auth;
	private String atted_jco_posted;
	private String atted_jco_sur;
	private String atted_jco_defi;
	private String atted_jco_medcat;
	private String atted_or_auth;
	private String atted_or_posted;
	private String atted_or_sur;
	private String atted_or_defi;
	private String atted_or_medcat;
	private String atted_civ_auth;
	private String atted_civ_posted;
	private String atted_civ_sur;
	private String atted_civ_defi;
	private String atted_civ_medcat;
	private String atted_warrant_auth;
	private String atted_warrant_posted;
	private String atted_warrant_sur;
	private String atted_warrant_defi;
	private String atted_warrant_medcat;
	private String user_remarks;		
	private Integer status;
	private String year;	
	
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

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
	public String getReg_offrs_auth() {
		return reg_offrs_auth;
	}
	public void setReg_offrs_auth(String reg_offrs_auth) {
		this.reg_offrs_auth = reg_offrs_auth;
	}
	public String getReg_offrs_posted() {
		return reg_offrs_posted;
	}
	public void setReg_offrs_posted(String reg_offrs_posted) {
		this.reg_offrs_posted = reg_offrs_posted;
	}
	public String getReg_offrs_sur() {
		return reg_offrs_sur;
	}
	public void setReg_offrs_sur(String reg_offrs_sur) {
		this.reg_offrs_sur = reg_offrs_sur;
	}
	public String getReg_offrs_defi() {
		return reg_offrs_defi;
	}
	public void setReg_offrs_defi(String reg_offrs_defi) {
		this.reg_offrs_defi = reg_offrs_defi;
	}
	public String getReg_offrs_medcat() {
		return reg_offrs_medcat;
	}
	public void setReg_offrs_medcat(String reg_offrs_medcat) {
		this.reg_offrs_medcat = reg_offrs_medcat;
	}
	public String getReg_jco_auth() {
		return reg_jco_auth;
	}
	public void setReg_jco_auth(String reg_jco_auth) {
		this.reg_jco_auth = reg_jco_auth;
	}
	public String getReg_jco_posted() {
		return reg_jco_posted;
	}
	public void setReg_jco_posted(String reg_jco_posted) {
		this.reg_jco_posted = reg_jco_posted;
	}
	public String getReg_jco_sur() {
		return reg_jco_sur;
	}
	public void setReg_jco_sur(String reg_jco_sur) {
		this.reg_jco_sur = reg_jco_sur;
	}
	public String getReg_jco_defi() {
		return reg_jco_defi;
	}
	public void setReg_jco_defi(String reg_jco_defi) {
		this.reg_jco_defi = reg_jco_defi;
	}
	public String getReg_jco_medcat() {
		return reg_jco_medcat;
	}
	public void setReg_jco_medcat(String reg_jco_medcat) {
		this.reg_jco_medcat = reg_jco_medcat;
	}
	public String getReg_or_auth() {
		return reg_or_auth;
	}
	public void setReg_or_auth(String reg_or_auth) {
		this.reg_or_auth = reg_or_auth;
	}
	public String getReg_or_posted() {
		return reg_or_posted;
	}
	public void setReg_or_posted(String reg_or_posted) {
		this.reg_or_posted = reg_or_posted;
	}
	public String getReg_or_sur() {
		return reg_or_sur;
	}
	public void setReg_or_sur(String reg_or_sur) {
		this.reg_or_sur = reg_or_sur;
	}
	public String getReg_or_defi() {
		return reg_or_defi;
	}
	public void setReg_or_defi(String reg_or_defi) {
		this.reg_or_defi = reg_or_defi;
	}
	public String getReg_or_medcat() {
		return reg_or_medcat;
	}
	public void setReg_or_medcat(String reg_or_medcat) {
		this.reg_or_medcat = reg_or_medcat;
	}
	public String getReg_civ_auth() {
		return reg_civ_auth;
	}
	public void setReg_civ_auth(String reg_civ_auth) {
		this.reg_civ_auth = reg_civ_auth;
	}
	public String getReg_civ_posted() {
		return reg_civ_posted;
	}
	public void setReg_civ_posted(String reg_civ_posted) {
		this.reg_civ_posted = reg_civ_posted;
	}
	public String getReg_civ_sur() {
		return reg_civ_sur;
	}
	public void setReg_civ_sur(String reg_civ_sur) {
		this.reg_civ_sur = reg_civ_sur;
	}
	public String getReg_civ_defi() {
		return reg_civ_defi;
	}
	public void setReg_civ_defi(String reg_civ_defi) {
		this.reg_civ_defi = reg_civ_defi;
	}
	public String getReg_civ_medcat() {
		return reg_civ_medcat;
	}
	public void setReg_civ_medcat(String reg_civ_medcat) {
		this.reg_civ_medcat = reg_civ_medcat;
	}
	public String getReg_warrant_auth() {
		return reg_warrant_auth;
	}
	public void setReg_warrant_auth(String reg_warrant_auth) {
		this.reg_warrant_auth = reg_warrant_auth;
	}
	public String getReg_warrant_posted() {
		return reg_warrant_posted;
	}
	public void setReg_warrant_posted(String reg_warrant_posted) {
		this.reg_warrant_posted = reg_warrant_posted;
	}
	public String getReg_warrant_sur() {
		return reg_warrant_sur;
	}
	public void setReg_warrant_sur(String reg_warrant_sur) {
		this.reg_warrant_sur = reg_warrant_sur;
	}
	public String getReg_warrant_defi() {
		return reg_warrant_defi;
	}
	public void setReg_warrant_defi(String reg_warrant_defi) {
		this.reg_warrant_defi = reg_warrant_defi;
	}
	public String getReg_warrant_medcat() {
		return reg_warrant_medcat;
	}
	public void setReg_warrant_medcat(String reg_warrant_medcat) {
		this.reg_warrant_medcat = reg_warrant_medcat;
	}
	public String getAtted_offrs_auth() {
		return atted_offrs_auth;
	}
	public void setAtted_offrs_auth(String atted_offrs_auth) {
		this.atted_offrs_auth = atted_offrs_auth;
	}
	public String getAtted_offrs_posted() {
		return atted_offrs_posted;
	}
	public void setAtted_offrs_posted(String atted_offrs_posted) {
		this.atted_offrs_posted = atted_offrs_posted;
	}
	public String getAtted_offrs_sur() {
		return atted_offrs_sur;
	}
	public void setAtted_offrs_sur(String atted_offrs_sur) {
		this.atted_offrs_sur = atted_offrs_sur;
	}
	public String getAtted_offrs_defi() {
		return atted_offrs_defi;
	}
	public void setAtted_offrs_defi(String atted_offrs_defi) {
		this.atted_offrs_defi = atted_offrs_defi;
	}
	public String getAtted_offrs_medcat() {
		return atted_offrs_medcat;
	}
	public void setAtted_offrs_medcat(String atted_offrs_medcat) {
		this.atted_offrs_medcat = atted_offrs_medcat;
	}
	public String getAtted_jco_auth() {
		return atted_jco_auth;
	}
	public void setAtted_jco_auth(String atted_jco_auth) {
		this.atted_jco_auth = atted_jco_auth;
	}
	public String getAtted_jco_posted() {
		return atted_jco_posted;
	}
	public void setAtted_jco_posted(String atted_jco_posted) {
		this.atted_jco_posted = atted_jco_posted;
	}
	public String getAtted_jco_sur() {
		return atted_jco_sur;
	}
	public void setAtted_jco_sur(String atted_jco_sur) {
		this.atted_jco_sur = atted_jco_sur;
	}
	public String getAtted_jco_defi() {
		return atted_jco_defi;
	}
	public void setAtted_jco_defi(String atted_jco_defi) {
		this.atted_jco_defi = atted_jco_defi;
	}
	public String getAtted_jco_medcat() {
		return atted_jco_medcat;
	}
	public void setAtted_jco_medcat(String atted_jco_medcat) {
		this.atted_jco_medcat = atted_jco_medcat;
	}
	public String getAtted_or_auth() {
		return atted_or_auth;
	}
	public void setAtted_or_auth(String atted_or_auth) {
		this.atted_or_auth = atted_or_auth;
	}
	public String getAtted_or_posted() {
		return atted_or_posted;
	}
	public void setAtted_or_posted(String atted_or_posted) {
		this.atted_or_posted = atted_or_posted;
	}
	public String getAtted_or_sur() {
		return atted_or_sur;
	}
	public void setAtted_or_sur(String atted_or_sur) {
		this.atted_or_sur = atted_or_sur;
	}
	public String getAtted_or_defi() {
		return atted_or_defi;
	}
	public void setAtted_or_defi(String atted_or_defi) {
		this.atted_or_defi = atted_or_defi;
	}
	public String getAtted_or_medcat() {
		return atted_or_medcat;
	}
	public void setAtted_or_medcat(String atted_or_medcat) {
		this.atted_or_medcat = atted_or_medcat;
	}
	public String getAtted_civ_auth() {
		return atted_civ_auth;
	}
	public void setAtted_civ_auth(String atted_civ_auth) {
		this.atted_civ_auth = atted_civ_auth;
	}
	public String getAtted_civ_posted() {
		return atted_civ_posted;
	}
	public void setAtted_civ_posted(String atted_civ_posted) {
		this.atted_civ_posted = atted_civ_posted;
	}
	public String getAtted_civ_sur() {
		return atted_civ_sur;
	}
	public void setAtted_civ_sur(String atted_civ_sur) {
		this.atted_civ_sur = atted_civ_sur;
	}
	public String getAtted_civ_defi() {
		return atted_civ_defi;
	}
	public void setAtted_civ_defi(String atted_civ_defi) {
		this.atted_civ_defi = atted_civ_defi;
	}
	public String getAtted_civ_medcat() {
		return atted_civ_medcat;
	}
	public void setAtted_civ_medcat(String atted_civ_medcat) {
		this.atted_civ_medcat = atted_civ_medcat;
	}
	public String getAtted_warrant_auth() {
		return atted_warrant_auth;
	}
	public void setAtted_warrant_auth(String atted_warrant_auth) {
		this.atted_warrant_auth = atted_warrant_auth;
	}
	public String getAtted_warrant_posted() {
		return atted_warrant_posted;
	}
	public void setAtted_warrant_posted(String atted_warrant_posted) {
		this.atted_warrant_posted = atted_warrant_posted;
	}
	public String getAtted_warrant_sur() {
		return atted_warrant_sur;
	}
	public void setAtted_warrant_sur(String atted_warrant_sur) {
		this.atted_warrant_sur = atted_warrant_sur;
	}
	public String getAtted_warrant_defi() {
		return atted_warrant_defi;
	}
	public void setAtted_warrant_defi(String atted_warrant_defi) {
		this.atted_warrant_defi = atted_warrant_defi;
	}
	public String getAtted_warrant_medcat() {
		return atted_warrant_medcat;
	}
	public void setAtted_warrant_medcat(String atted_warrant_medcat) {
		this.atted_warrant_medcat = atted_warrant_medcat;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getUser_remarks() {
		return user_remarks;
	}
	public void setUser_remarks(String user_remarks) {
		this.user_remarks = user_remarks;
	}


	
}
