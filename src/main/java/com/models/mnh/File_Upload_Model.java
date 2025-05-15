package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_upload_data", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class File_Upload_Model {
	
	private int id;
	
  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//private String unit_name;
	private String upload_file;
	private String upload_type;
	private String sus_no;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date upload_date;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_on = new Date();
	private String created_by;
	private int roleid;

	/*
	 * public String getUnit_name() { return unit_name; } public void
	 * setUnit_name(String unit_name) { this.unit_name = unit_name; }
	 */
	@Column(length = 150)
	public String getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(String upload_file) {
		this.upload_file = upload_file;
	}
	@Column(length = 100)
	public String getUpload_type() {
		return upload_type;
	}
	public void setUpload_type(String upload_type) {
		this.upload_type = upload_type;
	}
	@Column(length = 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
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
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}