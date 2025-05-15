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
@Table(name = "fp.fp_tb_adm_control", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class fp_tb_admin_control_model {
	
	private int id;
	private String est_type;
	private Date date_from;
	private Date date_to;
	private String fin_year;
	private String data_upd_by;
	private Date data_upd_date;
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
	public String getEst_type() {
		return est_type;
	}
	public void setEst_type(String est_type) {
		this.est_type = est_type;
	}
	public Date getDate_from() {
		return date_from;
	}
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	public Date getDate_to() {
		return date_to;
	}
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	public String getFin_year() {
		return fin_year;
	}
	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}
	public String getData_upd_by() {
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}
	public Date getData_upd_date() {
		return data_upd_date;
	}
	public void setData_upd_date(Date data_upd_date) {
		this.data_upd_date = data_upd_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}