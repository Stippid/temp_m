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
@Table(name = "tb_insp_pio_call_lapses", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_PIO_CALL_LAPSES {

	private int id;
	private Date date_violation_initial;
	private Date date_violation_fmn;
	private String number_rank_name;
	private String curr_status;
	private String loss_info;
	private String remarks;
	private String sus_no;
	private String year;

	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Integer status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_violation_initial() {
		return date_violation_initial;
	}
	public void setDate_violation_initial(Date date_violation_initial) {
		this.date_violation_initial = date_violation_initial;
	}
	public Date getDate_violation_fmn() {
		return date_violation_fmn;
	}
	public void setDate_violation_fmn(Date date_violation_fmn) {
		this.date_violation_fmn = date_violation_fmn;
	}
	public String getNumber_rank_name() {
		return number_rank_name;
	}
	public void setNumber_rank_name(String number_rank_name) {
		this.number_rank_name = number_rank_name;
	}
	public String getCurr_status() {
		return curr_status;
	}
	public void setCurr_status(String curr_status) {
		this.curr_status = curr_status;
	}
	public String getLoss_info() {
		return loss_info;
	}
	public void setLoss_info(String loss_info) {
		this.loss_info = loss_info;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
