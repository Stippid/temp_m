package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_tms_ro_vehicle_dtls" , uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_RO_VEHICLE_DTLS {
	
	private int id;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String ro_no;
	private BigInteger mct;
	private int quantity;
	private String inliuemct;
	private Date creationdate;
	private String modify_by;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_date ;
	private int version_no ;
	private String class_vehicle;
	private String sus_no;
	private String depot_sus_no;
	private String command_sus_no;
	//private String nrs;
	private String std_nomclature;
	private String type_of_release;
	private String accounting;
	private String issue_precedence;
	private String issue_stock;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issue_date;
	private String status;
	private String other_issue_type;
	private String remarks;
	private String type_of_issue;
	private int loan_duration;
	private String loan_auth;
	private String sur_def; 
	private String creation_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_submission;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date loan_from_date;
	private String type_of_ro;
	
	

    private String corps_sus_no;

	private String type_of_vehicle;
	
	public String getRo_no() {
		return ro_no;
	}
	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}
	public BigInteger getMct() {
		return mct;
	}
	public void setMct(BigInteger mct) {
		this.mct = mct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInliuemct() {
		return inliuemct;
	}
	public void setInliuemct(String inliuemct) {
		this.inliuemct = inliuemct;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}	
	public String getClass_vehicle() {
		return class_vehicle;
	}
	public void setClass_vehicle(String class_vehicle) {
		this.class_vehicle = class_vehicle;
	}	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getDepot_sus_no() {
		return depot_sus_no;
	}
	public void setDepot_sus_no(String depot_sus_no) {
		this.depot_sus_no = depot_sus_no;
	}
	public String getCommand_sus_no() {
		return command_sus_no;
	}
	public void setCommand_sus_no(String command_sus_no) {
		this.command_sus_no = command_sus_no;
	}
	/*public String getNrs() {
		return nrs;
	}
	public void setNrs(String nrs) {
		this.nrs = nrs;
	}*/
	public String getStd_nomclature() {
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature) {
		this.std_nomclature = std_nomclature;
	}
	public String getType_of_release() {
		return type_of_release;
	}
	public void setType_of_release(String type_of_release) {
		this.type_of_release = type_of_release;
	}
	public String getAccounting() {
		return accounting;
	}
	public void setAccounting(String accounting) {
		this.accounting = accounting;
	}
	public String getIssue_precedence() {
		return issue_precedence;
	}
	public void setIssue_precedence(String issue_precedence) {
		this.issue_precedence = issue_precedence;
	}	
	public String getIssue_stock() {
		return issue_stock;
	}
	public void setIssue_stock(String issue_stock) {
		this.issue_stock = issue_stock;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOther_issue_type() {
		return other_issue_type;
	}
	public void setOther_issue_type(String other_issue_type) {
		this.other_issue_type = other_issue_type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getType_of_issue() {
		return type_of_issue;
	}
	public void setType_of_issue(String type_of_issue) {
		this.type_of_issue = type_of_issue;
	}
	public int getLoan_duration() {
		return loan_duration;
	}
	public void setLoan_duration(int loan_duration) {
		this.loan_duration = loan_duration;
	}
	public String getLoan_auth() {
		return loan_auth;
	}
	public void setLoan_auth(String loan_auth) {
		this.loan_auth = loan_auth;
	}
	public String getSur_def() {
		return sur_def;
	}
	public void setSur_def(String sur_def) {
		this.sur_def = sur_def;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getDate_of_submission() {
		return date_of_submission;
	}
	public void setDate_of_submission(Date date_of_submission) {
		this.date_of_submission = date_of_submission;
	}	
	public Date getLoan_from_date() {
		return loan_from_date;
	}
	public void setLoan_from_date(Date loan_from_date) {
		this.loan_from_date = loan_from_date;
	}
	
	public String getType_of_vehicle() {
		return type_of_vehicle;
	}
	public void setType_of_vehicle(String type_of_vehicle) {
		this.type_of_vehicle = type_of_vehicle;
	}
	public String getType_of_ro()
	{
		return type_of_ro;
	}
	public void setType_of_ro(String type_of_ro)
	{
		this.type_of_ro = type_of_ro;
	}
	public String getCorps_sus_no() {
		return corps_sus_no;
	}
	public void setCorps_sus_no(String corps_sus_no) {
		this.corps_sus_no = corps_sus_no;
	}
	
	
}