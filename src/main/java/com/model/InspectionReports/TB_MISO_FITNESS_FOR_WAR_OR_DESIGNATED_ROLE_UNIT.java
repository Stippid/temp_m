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
@Table(name = "tb_miso_fitness_for_war_or_designated_role_unit", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE_UNIT {
	
	private int id;
	private String fully_fit_for_war;
	private String fit_or_unfit;
	private String recommendations;
	private Date created_date;
	private String created_by;
	private Date modify_date;
	private String modify_by;
	private int status;
	private String sus_no;
	private String year;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFully_fit_for_war() {
		return fully_fit_for_war;
	}
	public void setFully_fit_for_war(String fully_fit_for_war) {
		this.fully_fit_for_war = fully_fit_for_war;
	}
	public String getFit_or_unfit() {
		return fit_or_unfit;
	}
	public void setFit_or_unfit(String fit_or_unfit) {
		this.fit_or_unfit = fit_or_unfit;
	}
	public String getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	
	

}
