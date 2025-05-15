package com.models.psg.Transaction;

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
@Table(name = "tb_psg_maritial_discord_status_child", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_Maritial_Discord_Status_Child {
	
	private int id;
	private int maritial_id;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String status_of_case;
	private int close_status;
	private int cancel_status = -1;
	private Date cancel_date;
	private String cancel_by;
	private Date dt_of_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaritial_id() {
		return maritial_id;
	}
	public void setMaritial_id(int maritial_id) {
		this.maritial_id = maritial_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
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
	public String getStatus_of_case() {
		return status_of_case;
	}
	public void setStatus_of_case(String status_of_case) {
		this.status_of_case = status_of_case;
	}
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public Date getDt_of_status() {
		return dt_of_status;
	}
	public void setDt_of_status(Date dt_of_status) {
		this.dt_of_status = dt_of_status;
	}
	public int getClose_status() {
		return close_status;
	}
	public void setClose_status(int close_status) {
		this.close_status = close_status;
	}
	

}
