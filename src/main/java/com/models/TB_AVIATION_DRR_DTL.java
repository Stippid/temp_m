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
@Table(name = "tb_aviation_drr_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_AVIATION_DRR_DTL {
	
	private int id;
	private String drr_ser_no;
	private Date created_on;
	private String created_by;
	private String tail_no;
	private String authority;
	private String remarks;
	private String unit_sus_no;
	private String classification;
	private String status;
	private String agency_name;
	private String approved_by;
	private String approved_on;
	private String std_nomen;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrr_ser_no() {
		return drr_ser_no;
	}
	public void setDrr_ser_no(String drr_ser_no) {
		this.drr_ser_no = drr_ser_no;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getTail_no() {
		return tail_no;
	}
	public void setTail_no(String tail_no) {
		this.tail_no = tail_no;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAgency_name() {
		return agency_name;
	}
	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getApproved_on() {
		return approved_on;
	}
	public void setApproved_on(String approved_on) {
		this.approved_on = approved_on;
	}
	public String getStd_nomen() {
		return std_nomen;
	}
	public void setStd_nomen(String std_nomen) {
		this.std_nomen = std_nomen;
	}
}
