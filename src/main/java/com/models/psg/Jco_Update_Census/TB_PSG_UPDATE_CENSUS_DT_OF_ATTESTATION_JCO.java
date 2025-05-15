package com.models.psg.Jco_Update_Census;

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
@Table(name = "tb_psg_update_census_dt_of_attestation_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") }) 

public class TB_PSG_UPDATE_CENSUS_DT_OF_ATTESTATION_JCO {
	
	private int id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_attestation;
	private int jco_id;
	private int status;
	private String authority;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date_of_authority;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	private String reject_remarks;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_of_attestation() {
		return date_of_attestation;
	}
	public void setDate_of_attestation(Date date_of_attestation) {
		this.date_of_attestation = date_of_attestation;
	}
	public int getJco_id() {
		return jco_id;
	}
	public void setJco_id(int jco_id) {
		this.jco_id = jco_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
}
