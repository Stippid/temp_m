package com.models.psg.update_census_data;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;



import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_re_employment", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_REEMPLOYMENT {

	private int id;
	private int census_id;
	private BigInteger comm_id;
	private Date granted_fr_dt;
	private int re_emp_select;
	private String unit_sus_no;
	private String unit_posted_to;
	private int status;
	private Date date_of_tos;
	private String authority;
	private Date auth_dt;
	private String created_by;
	private Date created_date;
	private String modified_by;
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
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
	}
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public Date getGranted_fr_dt() {
		return granted_fr_dt;
	}
	public void setGranted_fr_dt(Date granted_fr_dt) {
		this.granted_fr_dt = granted_fr_dt;
	}
	public int getRe_emp_select() {
		return re_emp_select;
	}
	public void setRe_emp_select(int re_emp_select) {
		this.re_emp_select = re_emp_select;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getAuth_dt() {
		return auth_dt;
	}
	public void setAuth_dt(Date auth_dt) {
		this.auth_dt = auth_dt;
	}
/*	public int getCause_of_release() {
		return cause_of_release;
	}
	public void setCause_of_release(int cause_of_release) {
		this.cause_of_release = cause_of_release;
	}
	public Date getDt_of_release() {
		return dt_of_release;
	}
	public void setDt_of_release(Date dt_of_release) {
		this.dt_of_release = dt_of_release;
	}*/
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public String getUnit_posted_to() {
		return unit_posted_to;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setUnit_posted_to(String unit_posted_to) {
		this.unit_posted_to = unit_posted_to;
	}
	public Date getDate_of_tos() {
		return date_of_tos;
	}
	public void setDate_of_tos(Date date_of_tos) {
		this.date_of_tos = date_of_tos;
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
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}		
}

