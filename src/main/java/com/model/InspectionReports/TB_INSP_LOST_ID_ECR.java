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
@Table(name = "tb_insp_lost_id_ecr", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_LOST_ID_ECR {

	private int id;
	
	private String untraceable_document_token;
	private String classification;
	private String originator_doc_ecr;
	private String custodian_doc_ecr;
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


	public String getUntraceable_document_token() {
		return untraceable_document_token;
	}

	public void setUntraceable_document_token(String untraceable_document_token) {
		this.untraceable_document_token = untraceable_document_token;
	}

	public String getOriginator_doc_ecr() {
		return originator_doc_ecr;
	}

	public void setOriginator_doc_ecr(String originator_doc_ecr) {
		this.originator_doc_ecr = originator_doc_ecr;
	}

	public String getCustodian_doc_ecr() {
		return custodian_doc_ecr;
	}

	public void setCustodian_doc_ecr(String custodian_doc_ecr) {
		this.custodian_doc_ecr = custodian_doc_ecr;
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
