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
@Table(name = "tb_miso_insp_state_funds", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_STATE_FUNDS {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String nomenclature;
	private String au;
	private String auth;
	private String held;
	private String defi;
	private String ser_unser;
	private String reason_offrd;
	private String remarks;
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
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public String getAu() {
		return au;
	}
	public void setAu(String au) {
		this.au = au;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getHeld() {
		return held;
	}
	public void setHeld(String held) {
		this.held = held;
	}
	public String getDefi() {
		return defi;
	}
	public void setDefi(String defi) {
		this.defi = defi;
	}
	public String getSer_unser() {
		return ser_unser;
	}
	public void setSer_unser(String ser_unser) {
		this.ser_unser = ser_unser;
	}
	public String getReason_offrd() {
		return reason_offrd;
	}
	public void setReason_offrd(String reason_offrd) {
		this.reason_offrd = reason_offrd;
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
	
	
}
