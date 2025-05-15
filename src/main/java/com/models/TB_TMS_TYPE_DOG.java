package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_dog_type", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_TYPE_DOG {

	private int id;
	private int userid;
	private String type_dog;
	private String anml_type;
	private String created_by;
	private String modify_by;
	private Date created_on;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAnml_type() {
		return anml_type;
	}
	public void setAnml_type(String anml_type) {
		this.anml_type = anml_type;
	}
	public String getType_dog() {
		return type_dog;
	}
	public void setType_dog(String type_dog) {
		this.type_dog = type_dog;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
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
	public Date getModify_on() {
		return modify_on;
	}
	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
	}
	
	
}
