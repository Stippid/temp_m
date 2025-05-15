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
@Table(name = "tb_insp_financial_grants", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_INSP_FINANCIAL_GRANTS {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String type_of_grant;
	private String amt_auth;
	private String amt_alloted;
	private String reason_for_over_under;
	private String amt_expended;
	private String amt_utilised;
	private String reason_for_non_util;
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
	public String getType_of_grant() {
		return type_of_grant;
	}
	public void setType_of_grant(String type_of_grant) {
		this.type_of_grant = type_of_grant;
	}
	public String getAmt_auth() {
		return amt_auth;
	}
	public void setAmt_auth(String amt_auth) {
		this.amt_auth = amt_auth;
	}
	public String getAmt_alloted() {
		return amt_alloted;
	}
	public void setAmt_alloted(String amt_alloted) {
		this.amt_alloted = amt_alloted;
	}
	public String getReason_for_over_under() {
		return reason_for_over_under;
	}
	public void setReason_for_over_under(String reason_for_over_under) {
		this.reason_for_over_under = reason_for_over_under;
	}
	public String getAmt_expended() {
		return amt_expended;
	}
	public void setAmt_expended(String amt_expended) {
		this.amt_expended = amt_expended;
	}
	public String getAmt_utilised() {
		return amt_utilised;
	}
	public void setAmt_utilised(String amt_utilised) {
		this.amt_utilised = amt_utilised;
	}
	public String getReason_for_non_util() {
		return reason_for_non_util;
	}
	public void setReason_for_non_util(String reason_for_non_util) {
		this.reason_for_non_util = reason_for_non_util;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
