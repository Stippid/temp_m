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
@Table(name = "tb_miso_insp_bpet_result", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_BPET_RESULT {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String personnel;
	private String total_no;
	private String excellent;
	private String good;
	private String satisfactory;
	private String poor;
	private String fail;
	private String number_yet_to_tested;
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
	public String getPersonnel() {
		return personnel;
	}
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
	public String getTotal_no() {
		return total_no;
	}
	public void setTotal_no(String total_no) {
		this.total_no = total_no;
	}
	public String getExcellent() {
		return excellent;
	}
	public void setExcellent(String excellent) {
		this.excellent = excellent;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getSatisfactory() {
		return satisfactory;
	}
	public void setSatisfactory(String satisfactory) {
		this.satisfactory = satisfactory;
	}
	public String getPoor() {
		return poor;
	}
	public void setPoor(String poor) {
		this.poor = poor;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public String getNumber_yet_to_tested() {
		return number_yet_to_tested;
	}
	public void setNumber_yet_to_tested(String number_yet_to_tested) {
		this.number_yet_to_tested = number_yet_to_tested;
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
