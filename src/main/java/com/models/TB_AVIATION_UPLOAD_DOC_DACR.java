package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_aviation_upload_document_dacr", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class TB_AVIATION_UPLOAD_DOC_DACR {
	
	private int id;
	private String createdby;
	private String createddatetime;
	private String document;
	private String sus_no;
	private String status;
	private String downloadby;
	private String downloadon;
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddatetime() {
		return createddatetime;
	}
	public void setCreateddatetime(String createddatetime) {
		this.createddatetime = createddatetime;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDownloadby() {
		return downloadby;
	}
	public void setDownloadby(String downloadby) {
		this.downloadby = downloadby;
	}
	public String getDownloadon() {
		return downloadon;
	}
	public void setDownloadon(String downloadon) {
		this.downloadon = downloadon;
	}
	
	

}
