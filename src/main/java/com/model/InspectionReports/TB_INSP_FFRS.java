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
@Table(name = "tb_insp_ffrs", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_FFRS {

	private int id;

	private String range_available;
	private String range_utilized;
	private Date whenusedffrs;
	private String difficulties_experienced;


	private Integer status;
	private String year;
	private String created_by;
	private Date created_on;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRange_available() {
		return range_available;
	}
	public void setRange_available(String range_available) {
		this.range_available = range_available;
	}
	public String getRange_utilized() {
		return range_utilized;
	}
	public void setRange_utilized(String range_utilized) {
		this.range_utilized = range_utilized;
	}
	public Date getWhenusedffrs() {
		return whenusedffrs;
	}
	public void setWhenusedffrs(Date whenusedffrs) {
		this.whenusedffrs = whenusedffrs;
	}
	public String getDifficulties_experienced() {
		return difficulties_experienced;
	}
	public void setDifficulties_experienced(String difficulties_experienced) {
		this.difficulties_experienced = difficulties_experienced;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	private String modified_by;
	private Date modified_on;
	private String sus_no;

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
