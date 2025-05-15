package com.models.psg.Jco_Transaction;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_marital_discord_status_child_jco", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_PSG_MARITAL_DISCORD_STATUS_CHILD_JCO {
	
	private int id;
	private int marital_id;
	private String status_of_case;
	private int status;
	private int cancel_status = -1;
	private String cancel_by;
	private Date cancel_date;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarital_id() {
		return marital_id;
	}
	public void setMarital_id(int marital_id) {
		this.marital_id = marital_id;
	}
	public String getStatus_of_case() {
		return status_of_case;
	}
	public void setStatus_of_case(String status_of_case) {
		this.status_of_case = status_of_case;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCancel_status() {
		return cancel_status;
	}
	public void setCancel_status(int cancel_status) {
		this.cancel_status = cancel_status;
	}
	public String getCancel_by() {
		return cancel_by;
	}
	public void setCancel_by(String cancel_by) {
		this.cancel_by = cancel_by;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
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
	
	

}
