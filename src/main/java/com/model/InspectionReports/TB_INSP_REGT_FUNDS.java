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
@Table(name = "tb_insp_regt_funds", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_INSP_REGT_FUNDS {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String name_of_acct;
	private String asset_i;
	private String liability_i;
	private String asset_ii;
	private String liability_ii;
	private String asset_iii;
	private String liability_iii;
	private String incr_decr_acct;
	private String remarks;
	private Integer status;
	private String year;
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

	private String yeari;
	private String yearii;
	private String yeariii;

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
	public String getName_of_acct() {
		return name_of_acct;
	}
	public void setName_of_acct(String name_of_acct) {
		this.name_of_acct = name_of_acct;
	}
	public String getAsset_i() {
		return asset_i;
	}
	public void setAsset_i(String asset_i) {
		this.asset_i = asset_i;
	}
	public String getLiability_i() {
		return liability_i;
	}
	public void setLiability_i(String liability_i) {
		this.liability_i = liability_i;
	}
	public String getAsset_ii() {
		return asset_ii;
	}
	public void setAsset_ii(String asset_ii) {
		this.asset_ii = asset_ii;
	}
	public String getLiability_ii() {
		return liability_ii;
	}
	public void setLiability_ii(String liability_ii) {
		this.liability_ii = liability_ii;
	}
	public String getAsset_iii() {
		return asset_iii;
	}
	public void setAsset_iii(String asset_iii) {
		this.asset_iii = asset_iii;
	}
	public String getLiability_iii() {
		return liability_iii;
	}
	public void setLiability_iii(String liability_iii) {
		this.liability_iii = liability_iii;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getIncr_decr_acct() {
		return incr_decr_acct;
	}
	public void setIncr_decr_acct(String incr_decr_acct) {
		this.incr_decr_acct = incr_decr_acct;
	}
	public String getYeari() {
		return yeari;
	}
	public void setYeari(String yeari) {
		this.yeari = yeari;
	}
	public String getYearii() {
		return yearii;
	}
	public void setYearii(String yearii) {
		this.yearii = yearii;
	}
	public String getYeariii() {
		return yeariii;
	}
	public void setYeariii(String yeariii) {
		this.yeariii = yeariii;
	}


}
