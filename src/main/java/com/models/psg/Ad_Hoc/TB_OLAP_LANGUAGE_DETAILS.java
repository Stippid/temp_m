package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_language_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_LANGUAGE_DETAILS {

	String foreign_language_proficiency;
	String foreign_language;
	String foreign_language_standard;
	String language_standard;
	String language;
	String authority;
	Date date_of_authority;
	String foreign_language_exam_pass;
	String personal_no;

	public String getForeign_language_proficiency() {
		return foreign_language_proficiency;
	}

	public void setForeign_language_proficiency(String foreign_language_proficiency) {
		this.foreign_language_proficiency = foreign_language_proficiency;
	}

	public String getForeign_language() {
		return foreign_language;
	}

	public void setForeign_language(String foreign_language) {
		this.foreign_language = foreign_language;
	}

	public String getForeign_language_standard() {
		return foreign_language_standard;
	}

	public void setForeign_language_standard(String foreign_language_standard) {
		this.foreign_language_standard = foreign_language_standard;
	}

	public String getLanguage_standard() {
		return language_standard;
	}

	public void setLanguage_standard(String language_standard) {
		this.language_standard = language_standard;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getForeign_language_exam_pass() {
		return foreign_language_exam_pass;
	}

	public void setForeign_language_exam_pass(String foreign_language_exam_pass) {
		this.foreign_language_exam_pass = foreign_language_exam_pass;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
