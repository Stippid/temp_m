package com.models;

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
@Table(name = "tb_tms_mct_discard_condition", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MCT_DISCARD_CON {
	
	private int id;
	private String force_type; 
	private String mct_main_id;
	private int vintage;
	private int km;
	private String created_by;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date created_on;
	private String modified_by;
	@DateTimeFormat(pattern = "dd-mm-yyyy")
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
	public String getForce_type() {
		return force_type;
	}
	public void setForce_type(String force_type) {
		this.force_type = force_type;
	}
	public String getMct_main_id() {
		return mct_main_id;
	}
	public void setMct_main_id(String mct_main_id) {
		this.mct_main_id = mct_main_id;
	}
	public int getVintage() {
		return vintage;
	}
	public void setVintage(int vintage) {
		this.vintage = vintage;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
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
