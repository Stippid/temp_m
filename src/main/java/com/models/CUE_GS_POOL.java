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
@Table(name = "cue_gs_pool", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_GS_POOL {
	
	private int id;
	private String month;
	private String year;
	private String arm;
	private String rank_cat;
	private String rank;
	private Double scale;
	private String created_by;
	private String created_on;
	private String status;
	private String reject_remarks;
	private String reject_letter_no;
	private int roleid;
	private String remarks;
	private String arm_desc;
	private String ct_part_i_ii;
	private int auth_amt;

	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getArm() {
		return arm;
	}
	public void setArm(String arm) {
		this.arm = arm;
	}
	public String getRank_cat() {
		return rank_cat;
	}
	public void setRank_cat(String rank_cat) {
		this.rank_cat = rank_cat;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getReject_letter_no() {
		return reject_letter_no;
	}
	public void setReject_letter_no(String reject_letter_no) {
		this.reject_letter_no = reject_letter_no;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getArm_desc() {
		return arm_desc;
	}
	public void setArm_desc(String arm_desc) {
		this.arm_desc = arm_desc;
	}
	public String getCt_part_i_ii() {
		return ct_part_i_ii;
	}
	public void setCt_part_i_ii(String ct_part_i_ii) {
		this.ct_part_i_ii = ct_part_i_ii;
	}
	public int getAuth_amt() {
		return auth_amt;
	}
	public void setAuth_amt(int auth_amt) {
		this.auth_amt = auth_amt;
	}


	

	

	

	
	
}
