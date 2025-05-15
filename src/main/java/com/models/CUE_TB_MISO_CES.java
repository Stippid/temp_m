package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "cue_tb_miso_ces", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class CUE_TB_MISO_CES {

	private String  ces_cces_no;
	private String  ces_cces;
	
	
	public String getCes_cces() {
		return ces_cces;
	}
	public void setCes_cces(String ces_cces) {
		this.ces_cces = ces_cces;
	}
	public String getCes_cces_no() {
		return ces_cces_no;
	}
	public void setCes_cces_no(String ces_cces_no) {
		this.ces_cces_no = ces_cces_no;
	}
	public String getCes_cces_name() {
		return ces_cces_name;
	}
	public void setCes_cces_name(String ces_cces_name) {
		this.ces_cces_name = ces_cces_name;
	}
	public String getEfctiv_date() {
		return efctiv_date;
	}
	public void setEfctiv_date(String efctiv_date) {
		this.efctiv_date = efctiv_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public Double getAuth_proposed() {
		return auth_proposed;
	}
	public void setAuth_proposed(Double auth_proposed) {
		this.auth_proposed = auth_proposed;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	private String  ces_cces_name;
	private String  efctiv_date;
	private String  expiry_date;
	private Double  auth_proposed;
	private Integer  version_no;
	private String  softdelete;
	private String  remarks;
	private String  creation_by;
	private String  creation_date;
	private String  approve_by;
	private String  approve_date;
	private String  update_by;
	private String  update_date;
	private String  item_seq_no;
	private Integer  status;
	private int roleid;
	private String modified_by;
	private String modified_on;


	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	
}
