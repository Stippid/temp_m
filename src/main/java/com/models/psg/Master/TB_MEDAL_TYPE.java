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
@Table(name = "tb_psg_mstr_medal", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_MEDAL_TYPE {

	private int id;
	private String medal_name;
	private String medal_abberivation;
	private String medal_type;
	private String decoration;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date; 
	private String status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedal_name() {
		return medal_name;
	}
	public void setMedal_name(String medal_name) {
		this.medal_name = medal_name;
	}
	public String getMedal_abberivation() {
		return medal_abberivation;
	}
	public void setMedal_abberivation(String medal_abberivation) {
		this.medal_abberivation = medal_abberivation;
	}
	public String getMedal_type() {
		return medal_type;
	}
	public void setMedal_type(String medal_type) {
		this.medal_type = medal_type;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
}
