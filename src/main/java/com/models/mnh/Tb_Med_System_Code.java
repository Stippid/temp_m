package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_system_code", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class Tb_Med_System_Code {
	private int id;
	private String sys_code;
	private String sys_code_value;
	private int order_index;
	private String sys_code_desc;
	private String sys_code_value_desc;
	private String status;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 30)
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	@Column(length = 50)
	public String getSys_code_value() {
		return sys_code_value;
	}
	public void setSys_code_value(String sys_code_value) {
		this.sys_code_value = sys_code_value;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	@Column(length = 100)
	public String getSys_code_desc() {
		return sys_code_desc;
	}
	public void setSys_code_desc(String sys_code_desc) {
		this.sys_code_desc = sys_code_desc;
	}
	@Column(length = 150)
	public String getSys_code_value_desc() {
		return sys_code_value_desc;
	}
	public void setSys_code_value_desc(String sys_code_value_desc) {
		this.sys_code_value_desc = sys_code_value_desc;
	}
	@Column(length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
}
