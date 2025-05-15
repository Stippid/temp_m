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
@Table(name = "tb_tms_census_retrn_main", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})

public class TB_TMS_CENSUS_RETURN_MAIN {
	
	
	private int id;
	private String sus_no;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_cens_retrn;
	private Date dt_of_submsn;
	private String remarks;
	private String status;
	private String aprv_rejec_remarks;
	private String approved_by;
	private Date approve_date;
	private String creation_by;
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
	public Date getDate_of_cens_retrn() {
		return date_of_cens_retrn;
	}
	public void setDate_of_cens_retrn(Date date_of_cens_retrn) {
		this.date_of_cens_retrn = date_of_cens_retrn;
	}
	public Date getDt_of_submsn() {
		return dt_of_submsn;
	}
	public void setDt_of_submsn(Date dt_of_submsn) {
		this.dt_of_submsn = dt_of_submsn;
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
	
	
	
		

}
