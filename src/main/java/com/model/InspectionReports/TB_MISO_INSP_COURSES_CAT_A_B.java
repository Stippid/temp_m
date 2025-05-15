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
@Table(name = "tb_miso_insp_courses_cat_a_b", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_COURSES_CAT_A_B {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String course_name;
	private String officers_period;
	private String jcos_period;
	private String ncos_period;
	private String or_period;
	private String officers_preceding;
	private String jcos_preceding;
	private String ncos_preceding;
	private String or_preceding;
	private String totalofficers;
	private String totaljcos;
	private String totalncos;
	private String totalor;
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
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getOfficers_period() {
		return officers_period;
	}
	public void setOfficers_period(String officers_period) {
		this.officers_period = officers_period;
	}
	public String getJcos_period() {
		return jcos_period;
	}
	public void setJcos_period(String jcos_period) {
		this.jcos_period = jcos_period;
	}
	public String getNcos_period() {
		return ncos_period;
	}
	public void setNcos_period(String ncos_period) {
		this.ncos_period = ncos_period;
	}
	public String getOr_period() {
		return or_period;
	}
	public void setOr_period(String or_period) {
		this.or_period = or_period;
	}
	public String getOfficers_preceding() {
		return officers_preceding;
	}
	public void setOfficers_preceding(String officers_preceding) {
		this.officers_preceding = officers_preceding;
	}
	public String getJcos_preceding() {
		return jcos_preceding;
	}
	public void setJcos_preceding(String jcos_preceding) {
		this.jcos_preceding = jcos_preceding;
	}
	public String getNcos_preceding() {
		return ncos_preceding;
	}
	public void setNcos_preceding(String ncos_preceding) {
		this.ncos_preceding = ncos_preceding;
	}
	public String getOr_preceding() {
		return or_preceding;
	}
	public void setOr_preceding(String or_preceding) {
		this.or_preceding = or_preceding;
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
	public String getTotalofficers() {
		return totalofficers;
	}
	public void setTotalofficers(String totalofficers) {
		this.totalofficers = totalofficers;
	}
	public String getTotaljcos() {
		return totaljcos;
	}
	public void setTotaljcos(String totaljcos) {
		this.totaljcos = totaljcos;
	}
	public String getTotalncos() {
		return totalncos;
	}
	public void setTotalncos(String totalncos) {
		this.totalncos = totalncos;
	}
	public String getTotalor() {
		return totalor;
	}
	public void setTotalor(String totalor) {
		this.totalor = totalor;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
