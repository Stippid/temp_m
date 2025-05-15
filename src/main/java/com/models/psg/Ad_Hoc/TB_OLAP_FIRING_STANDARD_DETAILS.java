package com.models.psg.Ad_Hoc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_firing_standard_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_FIRING_STANDARD_DETAILS {

	String firing_event_qtr;
	String firing_grade;
	String year;
	String conducted_at_unit;
	String personal_no;

	public String getFiring_event_qtr() {
		return firing_event_qtr;
	}

	public void setFiring_event_qtr(String firing_event_qtr) {
		this.firing_event_qtr = firing_event_qtr;
	}

	public String getFiring_grade() {
		return firing_grade;
	}

	public void setFiring_grade(String firing_grade) {
		this.firing_grade = firing_grade;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConducted_at_unit() {
		return conducted_at_unit;
	}

	public void setConducted_at_unit(String conducted_at_unit) {
		this.conducted_at_unit = conducted_at_unit;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
