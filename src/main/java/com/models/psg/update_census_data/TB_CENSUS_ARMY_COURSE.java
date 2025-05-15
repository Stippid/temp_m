package com.models.psg.update_census_data;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_census_army_course", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_ARMY_COURSE {
	
	private int id;
	private int census_id;
	private BigInteger comm_id;
	private int status;
	private String course_name;
	private String course_abbreviation;
	private int course_institute;
	private String course_institute_other;
	private String div_grade;
	private String course_type;
	private Date start_date;
	private Date date_of_completion;
	private String reject_remarks;
	
	public String getCourse_abbreviation() {
		return course_abbreviation;
	}
	public void setCourse_abbreviation(String course_abbreviation) {
		this.course_abbreviation = course_abbreviation;
	}
	public int getCourse_institute() {
		return course_institute;
	}
	public void setCourse_institute(int course_institute) {
		this.course_institute = course_institute;
	}
	public String getCourse_institute_other() {
		return course_institute_other;
	}
	public void setCourse_institute_other(String course_institute_other) {
		this.course_institute_other = course_institute_other;
	}
	private String created_by;
	private Date created_on;
	private String modify_by;
	private Date modify_on;
	private String authority;
	private Date date_of_authority;
	private String ar_course_div_ot;
	private String course_type_ot;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	
	
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
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getDiv_grade() {
		return div_grade;
	}
	public void setDiv_grade(String div_grade) {
		this.div_grade = div_grade;
	}
	public String getCourse_type() {
		return course_type;
	}
	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getDate_of_completion() {
		return date_of_completion;
	}
	public void setDate_of_completion(Date date_of_completion) {
		this.date_of_completion = date_of_completion;
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
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_on() {
		return modify_on;
	}
	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getDate_of_authority() {
		return date_of_authority;
	}
	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
	}
	public String getAr_course_div_ot() {
		return ar_course_div_ot;
	}
	public void setAr_course_div_ot(String ar_course_div_ot) {
		this.ar_course_div_ot = ar_course_div_ot;
	}
	public String getCourse_type_ot() {
		return course_type_ot;
	}
	public void setCourse_type_ot(String course_type_ot) {
		this.course_type_ot = course_type_ot;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	
	
	
	
}
