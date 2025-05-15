package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_model_master", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class TB_TMS_MODEL_MASTER {

	private int id;
	private String mct_main_id;
	private String make_id;
	private String model_id;
	private String description;
	private String created_by;
	private String created_on;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMct_main_id() {
		return mct_main_id;
	}
	public void setMct_main_id(String mct_main_id) {
		this.mct_main_id = mct_main_id;
	}
	public String getMake_id() {
		return make_id;
	}
	public void setMake_id(String make_id) {
		this.make_id = make_id;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	
}
