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
@Table(name = "tb_detail_courses", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_DETAIL_COURSES {

	private int id;
	private String course_name;
	private String number_of_course;
	private Date period_from;
	private Date period_to;
	private String total_allotted;
	private String attended;
	private String rtu;
	private String detailed_remarks;
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
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getNumber_of_course() {
		return number_of_course;
	}
	public void setNumber_of_course(String number_of_course) {
		this.number_of_course = number_of_course;
	}
	public Date getPeriod_from() {
		return period_from;
	}
	public void setPeriod_from(Date period_from) {
		this.period_from = period_from;
	}
	public Date getPeriod_to() {
		return period_to;
	}
	public void setPeriod_to(Date period_to) {
		this.period_to = period_to;
	}
	public String getTotal_allotted() {
		return total_allotted;
	}
	public void setTotal_allotted(String total_allotted) {
		this.total_allotted = total_allotted;
	}
	public String getAttended() {
		return attended;
	}
	public void setAttended(String attended) {
		this.attended = attended;
	}
	public String getRtu() {
		return rtu;
	}
	public void setRtu(String rtu) {
		this.rtu = rtu;
	}
	public String getDetailed_remarks() {
		return detailed_remarks;
	}
	public void setDetailed_remarks(String detailed_remarks) {
		this.detailed_remarks = detailed_remarks;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}









}
