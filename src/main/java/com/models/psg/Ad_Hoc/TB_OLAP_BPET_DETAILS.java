package com.models.psg.Ad_Hoc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_bpet_details", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_BPET_DETAILS {

	String bpet_qtr;
	String bpet_result;
	String year;
	String conducted_at_unit;
	String personal_no;

	public String getBpet_qtr() {
		return bpet_qtr;
	}

	public void setBpet_qtr(String bpet_qtr) {
		this.bpet_qtr = bpet_qtr;
	}

	public String getBpet_result() {
		return bpet_result;
	}

	public void setBpet_result(String bpet_result) {
		this.bpet_result = bpet_result;
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
