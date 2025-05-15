package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tms_bveh_mvcr_update_state_view" )
public class View_TMS_Bveh_Mvcr_Update_Status_Details {
	
	  private String sus_no;
	  private String cmd_name;
	  private String coprs_name;
	  private String div_name;
	  private String bde_name;
	  private String unit_name;
	  private String status;
	  private String app_date;
	  private String form_code_control;
	  private String approved_by;
	  private String type_of_force;
	
    @Id
    public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getApp_date() {
		return app_date;
	}
	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}
	
	public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getType_of_force() {
		return type_of_force;
	}
	public void setType_of_force(String type_of_force) {
		this.type_of_force = type_of_force;
	}
	
}
