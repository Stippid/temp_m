package com.models.fpmis;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "fp.fp_tv_orbat_dtl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tv_orbat_dtl_Model {
	
	private int id;
	private String hq_type;
	private String sus_no;
	private String form_code_control;
	private String unit_name;
	private String status_sus_no;
	private String remarks;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHq_type() {
		return hq_type;
	}
	public void setHq_type(String hq_type) {
		this.hq_type = hq_type;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getStatus_sus_no() {
		return status_sus_no;
	}
	public void setStatus_sus_no(String status_sus_no) {
		this.status_sus_no = status_sus_no;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}