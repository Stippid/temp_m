package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_mstr_civilian_designation", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_PSG_MSTR_CIVILIAN_DESIGNATION {

	private int id;
	private String c_group;
	private String classification_services;
	private String status;
	private int civilian_trade;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	private String designation;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getClassification_services() {
		return classification_services;
	}
	public void setClassification_services(String classification_services) {
		this.classification_services = classification_services;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCivilian_trade() {
		return civilian_trade;
	}
	public void setCivilian_trade(int civilian_trade) {
		this.civilian_trade = civilian_trade;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getC_group() {
		return c_group;
	}
	public void setC_group(String c_group) {
		this.c_group = c_group;
	}
	
	
}
