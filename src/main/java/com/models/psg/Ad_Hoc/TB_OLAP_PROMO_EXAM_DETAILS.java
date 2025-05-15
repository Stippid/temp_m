package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_promo_exam_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_PROMO_EXAM_DETAILS {

	String authority;
	Date date_of_authority;
	String exam;
	Date date_of_passing;
	String personal_no;

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

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public Date getDate_of_passing() {
		return date_of_passing;
	}

	public void setDate_of_passing(Date date_of_passing) {
		this.date_of_passing = date_of_passing;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
