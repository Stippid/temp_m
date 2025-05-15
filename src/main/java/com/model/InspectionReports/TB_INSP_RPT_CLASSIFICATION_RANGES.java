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
@Table(name = "tb_insp_rpt_classification_ranges", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_INSP_RPT_CLASSIFICATION_RANGES {

	private int id;
	private String number_of_ranges;
	private Date  when_used;
	private String difficulties_experienced;
	private String sus_no;
	private String created_by;
	private String created_on;

	private Integer status;
	private String year;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber_of_ranges() {
		return number_of_ranges;
	}
	public void setNumber_of_ranges(String number_of_ranges) {
		this.number_of_ranges = number_of_ranges;
	}
	public Date getWhen_used() {
		return when_used;
	}
	public void setWhen_used(Date when_used) {
		this.when_used = when_used;
	}
	public String getDifficulties_experienced() {
		return difficulties_experienced;
	}
	public void setDifficulties_experienced(String difficulties_experienced) {
		this.difficulties_experienced = difficulties_experienced;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}





}
