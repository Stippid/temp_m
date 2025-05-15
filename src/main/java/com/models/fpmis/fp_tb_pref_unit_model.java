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
@Table(name = "fp.fp_tb_pref_unit", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tb_pref_unit_model {
	
	private int id;
	private String sus_no;
	private String chl_sus_no;
	private String usr_id;

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
	
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getChl_sus_no() {
		return chl_sus_no;
	}
	public void setChl_sus_no(String chl_sus_no) {
		this.chl_sus_no = chl_sus_no;
	}
}