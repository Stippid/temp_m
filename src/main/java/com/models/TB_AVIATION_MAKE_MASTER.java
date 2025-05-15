package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_aviation_make_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_AVIATION_MAKE_MASTER {
	
	private int id;
	private String make_no;
	private String description;
	private String act_slot_id;
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
	public String getMake_no() {
		return make_no;
	}
	public void setMake_no(String make_no) {
		this.make_no = make_no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAct_slot_id() {
		return act_slot_id;
	}
	public void setAct_slot_id(String act_slot_id) {
		this.act_slot_id = act_slot_id;
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
