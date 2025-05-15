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
@Table(name = "TB_MISO_INSP_CRITICAL_DEFI_MANPOWER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_CRITICAL_DEFI_MANPOWER {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String manpower_deficiencyoffrs;
	private String manpower_deficiencyjco;
	private String manpower_deficiencyor;
	private String action_taken;
	private String training_effect;
	private String remarks;
	private String status;
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
	public String getManpower_deficiencyoffrs() {
		return manpower_deficiencyoffrs;
	}
	public void setManpower_deficiencyoffrs(String manpower_deficiencyoffrs) {
		this.manpower_deficiencyoffrs = manpower_deficiencyoffrs;
	}
	public String getManpower_deficiencyjco() {
		return manpower_deficiencyjco;
	}
	public void setManpower_deficiencyjco(String manpower_deficiencyjco) {
		this.manpower_deficiencyjco = manpower_deficiencyjco;
	}
	public String getManpower_deficiencyor() {
		return manpower_deficiencyor;
	}
	public void setManpower_deficiencyor(String manpower_deficiencyor) {
		this.manpower_deficiencyor = manpower_deficiencyor;
	}
	public String getAction_taken() {
		return action_taken;
	}
	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}
	public String getTraining_effect() {
		return training_effect;
	}
	public void setTraining_effect(String training_effect) {
		this.training_effect = training_effect;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
