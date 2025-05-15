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
@Table(name = "tb_insp_td_proceeded", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_INSP_TD_PROCEEDED {

	private int id;
	private String rank_and_name;
	private String nature_duty;
	private String ordered_by;
	private String detailed_remarks;
	private String sus_no;
	private String year;

	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Integer status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRank_and_name() {
		return rank_and_name;
	}
	public void setRank_and_name(String rank_and_name) {
		this.rank_and_name = rank_and_name;
	}
	public String getNature_duty() {
		return nature_duty;
	}
	public void setNature_duty(String nature_duty) {
		this.nature_duty = nature_duty;
	}
	public String getOrdered_by() {
		return ordered_by;
	}
	public void setOrdered_by(String ordered_by) {
		this.ordered_by = ordered_by;
	}
	public String getDetailed_remarks() {
		return detailed_remarks;
	}
	public void setDetailed_remarks(String detailed_remarks) {
		this.detailed_remarks = detailed_remarks;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
