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
@Table(name = "tb_tms_banum_req_prnt", uniqueConstraints = {
		@UniqueConstraint(columnNames = "parent_req_id")})


public class Tms_Banum_Req_Prnt {

	 private int parent_req_id; 
	 private String type_of_request; 
	 private String requesting_agency; 
	 private String sus_no;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date dte_of_reqst; 
	 
	 private String  status;
	 private String creation_by; 
	 private Date creation_date; 
	 private String modify_by;
	 private Date modify_date; 
	 private int version_no; 
	 private String softdelete; 
	 private int ba_no_type; 
	
	 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getParent_req_id() {
		return parent_req_id;
	}
	public void setParent_req_id(int parent_req_id) {
		this.parent_req_id = parent_req_id;
	}
	public String getType_of_request() {
		return type_of_request;
	}
	public void setType_of_request(String type_of_request) {
		this.type_of_request = type_of_request;
	}
	public String getRequesting_agency() {
		return requesting_agency;
	}
	public void setRequesting_agency(String requesting_agency) {
		this.requesting_agency = requesting_agency;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Date getDte_of_reqst() {
		return dte_of_reqst;
	}
	public void setDte_of_reqst(Date dte_of_reqst) {
		this.dte_of_reqst = dte_of_reqst;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getBa_no_type() {
		return ba_no_type;
	}
	public void setBa_no_type(int ba_no_type) {
		this.ba_no_type = ba_no_type;
	}	
}
