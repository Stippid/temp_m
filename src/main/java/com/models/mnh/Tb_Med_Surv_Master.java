package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_surv_master", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Surv_Master {
	
private int id;
private String target_diseases;
private String target_diseases_sub;
private String investigation;
private String created_by;
private Date created_on;
private String modify_by;
private Date modify_on;

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 100)
	public String getTarget_diseases() {
		return target_diseases;
	}
	public void setTarget_diseases(String target_diseases) {
		this.target_diseases = target_diseases;
	}
	@Column(length = 100)
	public String getTarget_diseases_sub() {
		return target_diseases_sub;
	}
	public void setTarget_diseases_sub(String target_diseases_sub) {
		this.target_diseases_sub = target_diseases_sub;
	}
	@Column(length = 100)
	public String getInvestigation() {
		return investigation;
	}
	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}
	@Column(length = 35)
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
	@Column(length = 35)
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_on() {
		return modify_on;
	}
	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
	}
}