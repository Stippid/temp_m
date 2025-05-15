package com.models.psg.update_census_data;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_psg_change_of_appointment", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CHANGE_OF_APPOINTMENT {

	private int id;
	private String appt_authority;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appt_date_of_authority;
	private int appointment;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_of_appointment;
	private BigInteger comm_id;
	private int census_id;
	private int status;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int cancel_status=-1;
	private String cancel_by;
	private Date cancel_date;
	private String reject_remarks;
	private String parent_sus_no;
	private String parent_unit;
	private String x_list_loc;
	
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
	@Id
 	@GeneratedValue(strategy = IDENTITY)
 	@Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppt_authority() {
		return appt_authority;
	}
	public void setAppt_authority(String appt_authority) {
		this.appt_authority = appt_authority;
	}
	public Date getAppt_date_of_authority() {
		return appt_date_of_authority;
	}
	public void setAppt_date_of_authority(Date appt_date_of_authority) {
		this.appt_date_of_authority = appt_date_of_authority;
	}	
	public int getAppointment() {
		return appointment;
	}
	public void setAppointment(int appointment) {
		this.appointment = appointment;
	}
	public Date getDate_of_appointment() {
		return date_of_appointment;
	}
	public void setDate_of_appointment(Date date_of_appointment) {
		this.date_of_appointment = date_of_appointment;
	}
	
	public BigInteger getComm_id() {
		return comm_id;
	}
	public void setComm_id(BigInteger comm_id) {
		this.comm_id = comm_id;
	}
	public int getCensus_id() {
		return census_id;
	}
	public void setCensus_id(int census_id) {
		this.census_id = census_id;
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
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getParent_sus_no() {
		return parent_sus_no;
	}
	public void setParent_sus_no(String parent_sus_no) {
		this.parent_sus_no = parent_sus_no;
	}
	public String getParent_unit() {
		return parent_unit;
	}
	public void setParent_unit(String parent_unit) {
		this.parent_unit = parent_unit;
	}
	public String getX_list_loc() {
		return x_list_loc;
	}
	public void setX_list_loc(String x_list_loc) {
		this.x_list_loc = x_list_loc;
	}
	
	
}
