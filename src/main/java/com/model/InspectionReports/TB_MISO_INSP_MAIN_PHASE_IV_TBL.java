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
@Table(name = "tb_miso_insp_main_phase_iv_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MAIN_PHASE_IV_TBL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Integer unit_fitness_items;
	private Integer unit_data_items;
	private Integer critical_issues_items;

	private Integer status;
	private String year;
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Integer getUnit_fitness_items() {
		return unit_fitness_items;
	}
	public void setUnit_fitness_items(Integer unit_fitness_items) {
		this.unit_fitness_items = unit_fitness_items;
	}
	public Integer getUnit_data_items() {
		return unit_data_items;
	}
	public void setUnit_data_items(Integer unit_data_items) {
		this.unit_data_items = unit_data_items;
	}
	public Integer getCritical_issues_items() {
		return critical_issues_items;
	}
	public void setCritical_issues_items(Integer critical_issues_items) {
		this.critical_issues_items = critical_issues_items;
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
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}



}
