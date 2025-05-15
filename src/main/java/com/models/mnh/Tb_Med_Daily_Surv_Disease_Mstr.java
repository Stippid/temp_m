package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_daily_surv_disease_mstr", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Daily_Surv_Disease_Mstr {
	
	private int id;
	private String disease_name;
	private String status;
	private String created_by;
	private String modified_by;
	private Date created_on;
	private Date modified_on;
	private String disease_type;
	private int code;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 150)
	public String getDisease_name() {
		return disease_name;
	}
	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}
	@Column(length = 10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(length = 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	@Column(length = 35)
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	@Column(length = 50)
	public String getDisease_type() {
		return disease_type;
	}
	public void setDisease_type(String disease_type) {
		this.disease_type = disease_type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	private Set<Tb_Med_Daily_Disease_Surv_Details> disease = new HashSet<Tb_Med_Daily_Disease_Surv_Details>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<Tb_Med_Daily_Disease_Surv_Details> getDisease() {
		return disease;
	}
	public void setDisease(Set<Tb_Med_Daily_Disease_Surv_Details> disease) {
		this.disease = disease;
	}
	
}