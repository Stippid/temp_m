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
@Table(name = "fp.fp_tb_pref_head", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tb_pref_head_model {
	
	private int id;
	private String sus_no;
	private String chl_head_code;
	private String usr_id;
	private String psus_no;
	

	public String getPsus_no() {
		return psus_no;
	}
	public void setPsus_no(String psus_no) {
		this.psus_no = psus_no;
	}
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
	public String getChl_head_code() {
		return chl_head_code;
	}
	public void setChl_head_code(String chl_head_code) {
		this.chl_head_code = chl_head_code;
	}
	
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
}