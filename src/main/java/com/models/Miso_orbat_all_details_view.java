package com.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Immutable
@Table(name = "orbat_all_details_view" )
public class Miso_orbat_all_details_view {
	
	  private int id;
	  private String sus_no;
	  private String unit_name;
	  private String cmd_name;
	  private String coprs_name;
	  private String div_name;
	  private String bde_name;
	  private String location;
	  private String nrs;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date approved_rejected_on;
	  private String status_sus_no;
	  private String type_of_force;
	  private String ct_part_i_ii;
	  private int sus_version;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date comm_depart_date;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date compltn_arrv_date;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date creation_on;
	  private String action;
	  private String arm_code;
	  private String arm_desc;
	  private String form_code_control;
	 
	
	@Id
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
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getCmd_name() {
		return cmd_name;
	}
	public void setCmd_name(String cmd_name) {
		this.cmd_name = cmd_name;
	}
	public String getCoprs_name() {
		return coprs_name;
	}
	public void setCoprs_name(String coprs_name) {
		this.coprs_name = coprs_name;
	}
	public String getDiv_name() {
		return div_name;
	}
	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}
	public String getBde_name() {
		return bde_name;
	}
	public void setBde_name(String bde_name) {
		this.bde_name = bde_name;
	}
	
	public String getNrs() {
		return nrs;
	}
	public void setNrs(String nrs) {
		this.nrs = nrs;
	}
	
	public String getStatus_sus_no() {
		return status_sus_no;
	}
	public void setStatus_sus_no(String status_sus_no) {
		this.status_sus_no = status_sus_no;
	}
	public String getType_of_force() {
		return type_of_force;
	}
	public void setType_of_force(String type_of_force) {
		this.type_of_force = type_of_force;
	}
	public String getCt_part_i_ii() {
		return ct_part_i_ii;
	}
	public void setCt_part_i_ii(String ct_part_i_ii) {
		this.ct_part_i_ii = ct_part_i_ii;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public String getArm_desc() {
		return arm_desc;
	}
	public void setArm_desc(String arm_desc) {
		this.arm_desc = arm_desc;
	}
	public Date getApproved_rejected_on() {
		return approved_rejected_on;
	}
	public void setApproved_rejected_on(Date approved_rejected_on) {
		this.approved_rejected_on = approved_rejected_on;
	}
	public Date getComm_depart_date() {
		return comm_depart_date;
	}
	public void setComm_depart_date(Date comm_depart_date) {
		this.comm_depart_date = comm_depart_date;
	}
	public Date getCompltn_arrv_date() {
		return compltn_arrv_date;
	}
	public void setCompltn_arrv_date(Date compltn_arrv_date) {
		this.compltn_arrv_date = compltn_arrv_date;
	}
	public int getSus_version() {
	return sus_version;
	}
	public void setSus_version(int sus_version) {
		this.sus_version = sus_version;
	}
	public Date getCreation_on() {
	return creation_on;
	}
	public void setCreation_on(Date creation_on) {
		this.creation_on = creation_on;
	}
	public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
