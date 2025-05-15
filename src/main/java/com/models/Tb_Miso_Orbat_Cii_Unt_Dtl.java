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
@Table(name = "tb_miso_orbat_cii_unt_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class Tb_Miso_Orbat_Cii_Unt_Dtl {
	
	private int id;
	private String sus_no;
	private String unit_name;
	private String arm_code;
	private String fmn_code;
	private String loc_code;
	private String nrs_code;
	private String status_sus_no;
	private String created_by;	
	private Date created_on;
	private String modified_by;	
	private Date modified_on;
	private String approved_rejected_by;
	private Date approved_rejected_on;
	private int version_no;
	private String authority;
	private Date from_date;
	private Date to_date;
	private String fmn_name;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
	
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	
	
	public String getApproved_rejected_by() {
		return approved_rejected_by;
	}
	public void setApproved_rejected_by(String approved_rejected_by) {
		this.approved_rejected_by = approved_rejected_by;
	}
	
	
	public Date getApproved_rejected_on() {
		return approved_rejected_on;
	}
	public void setApproved_rejected_on(Date approved_rejected_on) {
		this.approved_rejected_on = approved_rejected_on;
	}
	
	
	public String getFmn_code() {
		return fmn_code;
	}
	public void setFmn_code(String fmn_code) {
		this.fmn_code = fmn_code;
	}
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}
	public String getNrs_code() {
		return nrs_code;
	}
	public void setNrs_code(String nrs_code) {
		this.nrs_code = nrs_code;
	}
	public String getStatus_sus_no() {
		return status_sus_no;
	}
	public void setStatus_sus_no(String status_sus_no) {
		this.status_sus_no = status_sus_no;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	
	public String getFmn_name() {
		return fmn_name;
	}
	public void setFmn_name(String fmn_name) {
		this.fmn_name = fmn_name;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	

}
