package com.models.fpmis;

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
@Table(name = "fp.fp_tb_advisory", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tb_advisory_model {
	
	private int id;
	private String sus_no;
	private String advisory_in_details;
	private String file_name;
	private String adv_type;
	private Date upload_from_date;
	private Date upload_to_date;
	private Date upload_date;
	private String upload_by;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getAdvisory_in_details() {
		return advisory_in_details;
	}
	public void setAdvisory_in_details(String advisory_in_details) {
		this.advisory_in_details = advisory_in_details;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Date getUpload_from_date() {
		return upload_from_date;
	}
	public void setUpload_from_date(Date upload_from_date) {
		this.upload_from_date = upload_from_date;
	}
	public Date getUpload_to_date() {
		return upload_to_date;
	}
	public void setUpload_to_date(Date upload_to_date) {
		this.upload_to_date = upload_to_date;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getUpload_by() {
		return upload_by;
	}
	public void setUpload_by(String upload_by) {
		this.upload_by = upload_by;
	}
	public String getAdv_type() {
		return adv_type;
	}
	public void setAdv_type(String adv_type) {
		this.adv_type = adv_type;
	}
	
}