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
@Table(name = "tb_miso_insp_promotion_exam", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_PROMOTION_EXAM {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String type_of_exam;
	private String number_appear;
	private String number_successful;
	private String number_yet_to_appear;
	private String remarks;	
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
	public String getType_of_exam() {
		return type_of_exam;
	}
	public void setType_of_exam(String type_of_exam) {
		this.type_of_exam = type_of_exam;
	}
	public String getNumber_appear() {
		return number_appear;
	}
	public void setNumber_appear(String number_appear) {
		this.number_appear = number_appear;
	}
	public String getNumber_successful() {
		return number_successful;
	}
	public void setNumber_successful(String number_successful) {
		this.number_successful = number_successful;
	}
	public String getNumber_yet_to_appear() {
		return number_yet_to_appear;
	}
	public void setNumber_yet_to_appear(String number_yet_to_appear) {
		this.number_yet_to_appear = number_yet_to_appear;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
