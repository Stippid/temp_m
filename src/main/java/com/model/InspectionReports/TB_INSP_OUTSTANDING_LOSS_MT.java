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
@Table(name = "tb_insp_outstanding_loss_mt", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_OUTSTANDING_LOSS_MT {

	private int id;
	
	private String year;
	private String nature_of_loss;
	private String value;
	private String remarks;
	
	private String outstanding_year;
	
	private String created_by;
	private Date created_on;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	public String getNature_of_loss() {
		return nature_of_loss;
	}

	public void setNature_of_loss(String nature_of_loss) {
		this.nature_of_loss = nature_of_loss;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getOutstanding_year() {
		return outstanding_year;
	}

	public void setOutstanding_year(String outstanding_year) {
		this.outstanding_year = outstanding_year;
	}

	
}
