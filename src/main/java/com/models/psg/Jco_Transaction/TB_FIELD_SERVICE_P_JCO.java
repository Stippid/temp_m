package com.models.psg.Jco_Transaction;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_field_service_p_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_FIELD_SERVICE_P_JCO {
	private int id;
	private int fd_services;
	private String unit_location;
	private int operation;
	private String authority;
	private Date authority_date;
	private String sus_id;
	private int status;
	private Date created_date;
	private String created_by;
	private Date modified_date;
	private String modified_by;
	private int cancel_status = -1;
	private Date cancel_date;
	private String cancel_by;
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
	public int getFd_services() {
		return fd_services;
	}
	public void setFd_services(int fd_services) {
		this.fd_services = fd_services;
	}
	public String getUnit_location() {
		return unit_location;
	}
	public void setUnit_location(String unit_location) {
		this.unit_location = unit_location;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getAuthority_date() {
		return authority_date;
	}
	public void setAuthority_date(Date authority_date) {
		this.authority_date = authority_date;
	}
	public String getSus_id() {
		return sus_id;
	}
	public void setSus_id(String sus_id) {
		this.sus_id = sus_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}

	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}

}
