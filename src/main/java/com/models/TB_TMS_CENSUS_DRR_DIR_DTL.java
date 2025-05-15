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
@Table(name = "tb_tms_census_drr_dir_dtl", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_CENSUS_DRR_DIR_DTL {
	private int id;
	private String sus_no;
	private String drr_dir_ser_no;
	private String ba_no;
	private String classification;
	private String issue_condition;
	private String issue_agnst_rio;
	private String unit_sus_no;
	private String remarks;
	private String status;
	private String aprv_rejec_remarks;
	private String approved_by;
	private Date approved_date;
	private String creation_by;
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	private String deleted_by;
	private Date deleted_date;
	private String other_agency;
	private String authority;
	private int kms_run;
    private String type_of_stock;	
	private String issue_type;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
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

	public String getIssue_condition() {
		return issue_condition;
	}

	public void setIssue_condition(String issue_condition) {
		this.issue_condition = issue_condition;
	}

	public String getIssue_agnst_rio() {
		return issue_agnst_rio;
	}

	public void setIssue_agnst_rio(String issue_against_rio) {
		this.issue_agnst_rio = issue_against_rio;
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

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public String getCreation_by() {
		return creation_by;
	}

	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}

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

	public Date getDeleted_date() {
		return deleted_date;
	}

	public void setDeleted_date(Date deleted_date) {
		this.deleted_date = deleted_date;
	}

	public String getOther_agency() {
		return other_agency;
	}

	public void setOther_agency(String other_agency) {
		this.other_agency = other_agency;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getKms_run() {
		return kms_run;
	}

	public void setKms_run(int kms_run) {
		this.kms_run = kms_run;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssue_type() {
		return issue_type;
	}

	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getType_of_stock() {
		return type_of_stock;
	}
	public void setType_of_stock(String type_of_stock) {
		this.type_of_stock = type_of_stock;
	}

}
