package com.models.psg.Ad_Hoc;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_olap_foreign_country", uniqueConstraints = { @UniqueConstraint(columnNames = "personal_no") })
public class TB_OLAP_FOREIGN_COUNTRY_DETAILS {

	String country;
	String period;
	Date date_from;
	Date date_to;
	String purpose_visit;
	String personal_no;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	public String getPurpose_visit() {
		return purpose_visit;
	}

	public void setPurpose_visit(String purpose_visit) {
		this.purpose_visit = purpose_visit;
	}

	public String getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}

}
