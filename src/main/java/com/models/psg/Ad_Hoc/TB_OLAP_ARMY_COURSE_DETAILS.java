package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_army_course_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_ARMY_COURSE_DETAILS {

	String authority;
	Date date_of_authority;
	String course_name;
	String div_grade;
	String course_type;
	Date start_date;
	Date date_of_completion;
	String course_type_ot;
	String course_abbreviation;
	String course_institute;
	String personal_no;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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

	public String getCourse_type_ot() {
		return course_type_ot;
	}

	public void setCourse_type_ot(String course_type_ot) {
		this.course_type_ot = course_type_ot;
	}

	public String getCourse_abbreviation() {
		return course_abbreviation;
	}

	public void setCourse_abbreviation(String course_abbreviation) {
		this.course_abbreviation = course_abbreviation;
	}

	public String getCourse_institute() {
		return course_institute;
	}

	public void setCourse_institute(String course_institute) {
		this.course_institute = course_institute;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

	public Date getDate_of_authority() {
		return date_of_authority;
	}

	public void setDate_of_authority(Date date_of_authority) {
		this.date_of_authority = date_of_authority;
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
}
