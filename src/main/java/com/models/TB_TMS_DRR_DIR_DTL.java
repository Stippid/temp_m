package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_tms_drr_dir_dtl", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_DRR_DIR_DTL {

	private int id;
	private String sus_no;
	private String drr_dir_ser_no;
	private String ba_no;
	private String classification;
	private String typ_of_retrn;
	private String issue_against_rio;
	private String unit_sus_no;
	private String remarks;
	private String status;
	private String aprv_rejec_remarks;
	private String approved_by;
	private Date approve_date;
	private String creation_by;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	private String deleted_by;
	private Date deleted_date;
	private int version_no;
	private String softdelete;
	private String filler_1;
	private String filler_2;
	private String filler_3;
	private String type_of_issue;
	private String authority;
	private String qfd;
	private int kms_run;
	private String issuetype;
	private String type_of_stock;
	
	
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
	public String getDrr_dir_ser_no() {
		return drr_dir_ser_no;
	}
	public void setDrr_dir_ser_no(String drr_dir_ser_no) {
		this.drr_dir_ser_no = drr_dir_ser_no;
	}
	public String getBa_no() {
		return ba_no;
	}
	public void setBa_no(String ba_no) {
		this.ba_no = ba_no;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getTyp_of_retrn() {
		return typ_of_retrn;
	}
	public void setTyp_of_retrn(String typ_of_retrn) {
		this.typ_of_retrn = typ_of_retrn;
	}
	public String getIssue_against_rio() {
		return issue_against_rio;
	}
	public void setIssue_against_rio(String issue_against_rio) {
		this.issue_against_rio = issue_against_rio;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
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
	public String getAprv_rejec_remarks() {
		return aprv_rejec_remarks;
	}
	public void setAprv_rejec_remarks(String aprv_rejec_remarks) {
		this.aprv_rejec_remarks = aprv_rejec_remarks;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getDeleted_by() {
		return deleted_by;
	}
	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDeleted_date() {
		return deleted_date;
	}
	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getFiller_1() {
		return filler_1;
	}
	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}
	public String getFiller_2() {
		return filler_2;
	}
	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}
	public String getFiller_3() {
		return filler_3;
	}
	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
	}
	public String getType_of_issue() {
		return type_of_issue;
	}
	public void setType_of_issue(String type_of_issue) {
		this.type_of_issue = type_of_issue;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getQfd() {
		return qfd;
	}
	public void setQfd(String qfd) {
		this.qfd = qfd;
	}
	public int getKms_run() {
		return kms_run;
	}
	public void setKms_run(int kms_run) {
		this.kms_run = kms_run;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	
	public String getType_of_stock() {
		return type_of_stock;
	}
	public void setType_of_stock(String type_of_stock) {
		this.type_of_stock = type_of_stock;
	}
}
