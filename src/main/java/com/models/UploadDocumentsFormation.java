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
@Table(name = "upload_documents_frm_tbl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class UploadDocumentsFormation {
	private int id;
    private String created_by;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createddate;
    
    private String upload_docs_name;
    private String upload_docs_fullname;
    private String upload_docs_path;
    
 
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false) 
    public int getId() {
    	return id;
    }
    public void setId(int id) {
	   this.id = id;
    }
	 
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	public String getUpload_docs_name() {
		return upload_docs_name;
	}
	public void setUpload_docs_name(String upload_docs_name) {
		this.upload_docs_name = upload_docs_name;
	}
	public String getUpload_docs_path() {
		return upload_docs_path;
	}
	public void setUpload_docs_path(String upload_docs_path) {
		this.upload_docs_path = upload_docs_path;
	}
	public String getUpload_docs_fullname() {
		return upload_docs_fullname;
	}
	public void setUpload_docs_fullname(String upload_docs_fullname) {
		this.upload_docs_fullname = upload_docs_fullname;
	}
	
	 
	 
}
