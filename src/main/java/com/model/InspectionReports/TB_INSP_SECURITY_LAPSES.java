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
@Table(name = "tb_insp_security_lapses", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_SECURITY_LAPSES {

	private int id;
	
	private String dateof;
	private String nature_of_security_lapse;
	private String resulted_in;
	private String remarks;
	
	
	private String created_by;
	private Date created_on;
	private String year;
	private Integer status;
	private String sus_no;
	
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

	public String getDate() {
		return dateof;
	}

	public void setDate(String date) {
		this.dateof = date;
	}

	

	public String getDateof() {
		return dateof;
	}

	public void setDateof(String dateof) {
		this.dateof = dateof;
	}

	public String getNature_of_security_lapse() {
		return nature_of_security_lapse;
	}

	public void setNature_of_security_lapse(String nature_of_security_lapse) {
		this.nature_of_security_lapse = nature_of_security_lapse;
	}

	public String getResulted_in() {
		return resulted_in;
	}

	public void setResulted_in(String resulted_in) {
		this.resulted_in = resulted_in;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}


	
}
