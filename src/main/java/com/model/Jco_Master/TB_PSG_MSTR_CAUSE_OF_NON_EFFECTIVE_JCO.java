package com.model.Jco_Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "tb_psg_mstr_cause_of_non_effective_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_PSG_MSTR_CAUSE_OF_NON_EFFECTIVE_JCO {
	
	private int id;
	private String causes_name;
	private int type_of_officer;
	private String status;
	private String created_by;
	private Date created_dt;
	private String modified_by;
	private Date modified_dt;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false )
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCauses_name() {
		return causes_name;
	}
	public void setCauses_name(String causes_name) {
		this.causes_name = causes_name;
	}
	public int getType_of_officer() {
		return type_of_officer;
	}
	public void setType_of_officer(int type_of_officer) {
		this.type_of_officer = type_of_officer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
}
