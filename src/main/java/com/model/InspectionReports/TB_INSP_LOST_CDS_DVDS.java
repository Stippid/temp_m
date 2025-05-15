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
@Table(name = "tb_insp_lost_cds_dvds", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_LOST_CDS_DVDS {

	private int id;
	
	private String untraceble_cd_dvd;
	private String classification;
	private String originator_cd;
	private String custodian_cd;
	private String current_status;
	private String remarks;
	
	
	private String created_by;
	private Date created_on;
	private String year;
	private Integer status;
	private String sus_no;
	
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	


	public String getUntraceble_cd_dvd() {
		return untraceble_cd_dvd;
	}

	public void setUntraceble_cd_dvd(String untraceble_cd_dvd) {
		this.untraceble_cd_dvd = untraceble_cd_dvd;
	}

	public String getOriginator_cd() {
		return originator_cd;
	}

	public void setOriginator_cd(String originator_cd) {
		this.originator_cd = originator_cd;
	}

	public String getCustodian_cd() {
		return custodian_cd;
	}

	public void setCustodian_cd(String custodian_cd) {
		this.custodian_cd = custodian_cd;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}


	
}
