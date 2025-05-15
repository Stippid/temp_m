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
@Table(name = "TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_DISCIPLINE_COURTMARITAL_SUMMARYDISPOSAL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String ongoing_count;
	private String pending_cases;
	private String cases_current;
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
	public String getOngoing_count() {
		return ongoing_count;
	}
	public void setOngoing_count(String ongoing_count) {
		this.ongoing_count = ongoing_count;
	}
	public String getPending_cases() {
		return pending_cases;
	}
	public void setPending_cases(String pending_cases) {
		this.pending_cases = pending_cases;
	}
	public String getCases_current() {
		return cases_current;
	}
	public void setCases_current(String cases_current) {
		this.cases_current = cases_current;
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
