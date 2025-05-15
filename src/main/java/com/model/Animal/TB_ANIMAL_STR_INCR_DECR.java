package com.model.Animal;


import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_animal_str_incr_decr", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })

public class TB_ANIMAL_STR_INCR_DECR {
	
	private int id;
	private int census_id; 
	private String scenario;
	private String to_sus_no;
	private String from_sus_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date dt_of_tos;
	private Date dt_of_sos;
	private int status;
	private String auth_no;	
	private Date auth_dt;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private Date tenure_date;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String reject_remarks;
	private String rejected_by;
	private Date rejected_date;
	
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
	
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	
	public String getTo_sus_no() {
		return to_sus_no;
	}
	public void setTo_sus_no(String to_sus_no) {
		this.to_sus_no = to_sus_no;
	}
	public String getFrom_sus_no() {
		return from_sus_no;
	}
	public void setFrom_sus_no(String from_sus_no) {
		this.from_sus_no = from_sus_no;
	}
	
	public Date getDt_of_tos() {
		return dt_of_tos;
	}
	public void setDt_of_tos(Date dt_of_tos) {
		this.dt_of_tos = dt_of_tos;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public Date getDt_of_sos() {
		return dt_of_sos;
	}
	public void setDt_of_sos(Date dt_of_sos) {
		this.dt_of_sos = dt_of_sos;
	}
	
	public void setTenure_date(Date tenure_date) {
		this.tenure_date = tenure_date;
	}
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	public Date getAuth_dt() {
		return auth_dt;
	}
	public void setAuth_dt(Date auth_dt) {
		this.auth_dt = auth_dt;
	}
	public String getRejected_by() {
		return rejected_by;
	}
	public void setRejected_by(String rejected_by) {
		this.rejected_by = rejected_by;
	}
	public Date getRejected_date() {
		return rejected_date;
	}
	public void setRejected_date(Date rejected_date) {
		this.rejected_date = rejected_date;
	}
	

}
