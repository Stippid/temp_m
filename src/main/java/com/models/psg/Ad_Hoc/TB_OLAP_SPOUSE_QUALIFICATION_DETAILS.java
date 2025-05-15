package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_spouse_qualification_details", uniqueConstraints = {
		@UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_SPOUSE_QUALIFICATION_DETAILS {

	String qualification_type;
	String passing_year;
	String div_class;
	String subject;
	String institute;
	String authority;
	Date date_of_authority;
	String examination_pass;
	String specialization;
	String degree;
	String personal_no;

	public String getQualification_type() {
		return qualification_type;
	}

	public void setQualification_type(String qualification_type) {
		this.qualification_type = qualification_type;
	}

	public String getPassing_year() {
		return passing_year;
	}

	public void setPassing_year(String passing_year) {
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

	public String getExamination_pass() {
		return examination_pass;
	}

	public void setExamination_pass(String examination_pass) {
		this.examination_pass = examination_pass;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
