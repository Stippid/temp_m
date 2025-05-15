package com.models.psg.Master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_mstr_language", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_LANGUAGE {
	
	private int  id;
	@NotBlank(message="Please Enter Language")
    private String language;
    private String created_by;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_on; 
    private String modify_by; 
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  modify_on;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_on() {
		return modify_on;
	}
	public void setModify_on(Date modify_on) {
		this.modify_on = modify_on;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
