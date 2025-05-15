package com.models.psg.Transaction;
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
@Table(name = "tb_psg_census_spouse_qualification", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_CENSUS_SPOUSE_QUALIFICATION {
	private int id;
	private int cen_id;
	private int type;
	private int examination_pass;
	private int passing_year;
	private String div_class;
	private String subject;
	private String institute;
	private int degree;
	private int specialization;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int status;
	private BigInteger comm_id;
	private int spouse_id;
	private String authority;
	private Date date_of_authority;
	private String exam_other;
	private String degree_other;
	private String specialization_other;
	private String class_other;
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
	public int getCen_id() {
		return cen_id;
	}
	public void setCen_id(int cen_id) {
		this.cen_id = cen_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getExamination_pass() {
		return examination_pass;
	}
	public void setExamination_pass(int examination_pass) {
		this.examination_pass = examination_pass;
	}
	public int getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(int passing_year) {
		this.passing_year = passing_year;
	}
	public String getDiv_class() {
		return div_class;
	}
	public void setDiv_class(String div_class) {
		this.div_class = div_class;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getSpecialization() {
		return specialization;
	}
	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getSpouse_id() {
		return spouse_id;
	}
	public void setSpouse_id(int spouse_id) {
		this.spouse_id = spouse_id;
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
	public String getExam_other() {
		return exam_other;
	}
	public void setExam_other(String exam_other) {
		this.exam_other = exam_other;
	}
	public String getDegree_other() {
		return degree_other;
	}
	public void setDegree_other(String degree_other) {
		this.degree_other = degree_other;
	}
	public String getSpecialization_other() {
		return specialization_other;
	}
	public void setSpecialization_other(String specialization_other) {
		this.specialization_other = specialization_other;
	}
	public String getClass_other() {
		return class_other;
	}
	public void setClass_other(String class_other) {
		this.class_other = class_other;
	}
}
