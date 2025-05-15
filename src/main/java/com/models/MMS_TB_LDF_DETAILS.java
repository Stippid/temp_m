/*package com.models;


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
@Table(name = "mms_tb_ldf_details", uniqueConstraints = {@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_LDF_DETAILS {

	private int id;
	private String sus_no;
	private Date fire_date;
	private String fire_time;
	private String fire_cause;
	private String nature_occupancy;
	private int persons_killed;
	private int persons_injured;
	private int auth_fire_equpmnt;
	private int hold_civilian_fire_staff;
	private String persns_blamed;
	private int persons_blamed_no;
	private String action_taken ;
	private String measures_taken;
	private Date sys_date;
	private int version_no;
	private String softdelete;
	private int status_as_on_month;
	private int status_as_on_year;
	private String remarks;
	private String coi_held;
	private String formation_sus_no;
	private String creation_by;
	private Date creation_date;
	private String approve_by;
	private Date approve_date;
	private String upadte_by;
	private Date update_date;
	private String status;
	private int auth_civilan_fire_staff;
	private String details_coi;
	private String approve_remarks;
	private int no_nature_occupancy;
	private int held_fire_equpmnt;
	
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
	public Date getFire_date() {
		return fire_date;
	}
	public void setFire_date(Date fire_date) {
		this.fire_date = fire_date;
	}
	public String getFire_time() {
		return fire_time;
	}
	public void setFire_time(String fire_time) {
		this.fire_time = fire_time;
	}
	public String getFire_cause() {
		return fire_cause;
	}
	public void setFire_cause(String fire_cause) {
		this.fire_cause = fire_cause;
	}
	public String getNature_occupancy() {
		return nature_occupancy;
	}
	public void setNature_occupancy(String nature_occupancy) {
		this.nature_occupancy = nature_occupancy;
	}
	public int getPersons_killed() {
		return persons_killed;
	}
	public void setPersons_killed(int persons_killed) {
		this.persons_killed = persons_killed;
	}
	public int getPersons_injured() {
		return persons_injured;
	}
	public void setPersons_injured(int persons_injured) {
		this.persons_injured = persons_injured;
	}
	public int getAuth_fire_equpmnt() {
		return auth_fire_equpmnt;
	}
	public void setAuth_fire_equpmnt(int auth_fire_equpmnt) {
		this.auth_fire_equpmnt = auth_fire_equpmnt;
	}
	public int getHold_civilian_fire_staff() {
		return hold_civilian_fire_staff;
	}
	public void setHold_civilian_fire_staff(int hold_civilian_fire_staff) {
		this.hold_civilian_fire_staff = hold_civilian_fire_staff;
	}
	public String getPersns_blamed() {
		return persns_blamed;
	}
	public void setPersns_blamed(String persns_blamed) {
		this.persns_blamed = persns_blamed;
	}
	public int getPersons_blamed_no() {
		return persons_blamed_no;
	}
	public void setPersons_blamed_no(int persons_blamed_no) {
		this.persons_blamed_no = persons_blamed_no;
	}
	public String getAction_taken() {
		return action_taken;
	}
	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}
	public String getMeasures_taken() {
		return measures_taken;
	}
	public void setMeasures_taken(String measures_taken) {
		this.measures_taken = measures_taken;
	}
	public Date getSys_date() {
		return sys_date;
	}
	public void setSys_date(Date sys_date) {
		this.sys_date = sys_date;
	}
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	public int getStatus_as_on_month() {
		return status_as_on_month;
	}
	public void setStatus_as_on_month(int status_as_on_month) {
		this.status_as_on_month = status_as_on_month;
	}
	public int getStatus_as_on_year() {
		return status_as_on_year;
	}
	public void setStatus_as_on_year(int status_as_on_year) {
		this.status_as_on_year = status_as_on_year;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCoi_held() {
		return coi_held;
	}
	public void setCoi_held(String coi_held) {
		this.coi_held = coi_held;
	}
	public String getFormation_sus_no() {
		return formation_sus_no;
	}
	public void setFormation_sus_no(String formation_sus_no) {
		this.formation_sus_no = formation_sus_no;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getApprove_by() {
		return approve_by;
	}
	public void setApprove_by(String approve_by) {
		this.approve_by = approve_by;
	}
	public Date getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(Date approve_date) {
		this.approve_date = approve_date;
	}
	public String getUpadte_by() {
		return upadte_by;
	}
	public void setUpadte_by(String upadte_by) {
		this.upadte_by = upadte_by;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAuth_civilan_fire_staff() {
		return auth_civilan_fire_staff;
	}
	public void setAuth_civilan_fire_staff(int auth_civilan_fire_staff) {
		this.auth_civilan_fire_staff = auth_civilan_fire_staff;
	}
	public String getDetails_coi() {
		return details_coi;
	}
	public void setDetails_coi(String details_coi) {
		this.details_coi = details_coi;
	}
	public String getApprove_remarks() {
		return approve_remarks;
	}
	public void setApprove_remarks(String approve_remarks) {
		this.approve_remarks = approve_remarks;
	}
	public int getNo_nature_occupancy() {
		return no_nature_occupancy;
	}
	public void setNo_nature_occupancy(int no_nature_occupancy) {
		this.no_nature_occupancy = no_nature_occupancy;
	}
	public int getHeld_fire_equpmnt() {
		return held_fire_equpmnt;
	}
	public void setHeld_fire_equpmnt(int held_fire_equpmnt) {
		this.held_fire_equpmnt = held_fire_equpmnt;
	}
	
}
*/