package com.models.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_it_asset_personal_upload", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_IT_ASEET_PERSONAL_UPLOAD {

	@Id
	@GeneratedValue
	private int id;
	private String batch_id;
	private String controlling_hq_type;
	private String unit_sus_no;
	private int unit_deo_status;
	private int unit_app_status;
	private String bde_status;
	private String div_status;
	private String corps_status;
	private String comd_status;
	private int final_status;
	private String control_id;
	private String senction_file;
	private String form_code_admin;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String unit_approved_by;
	private Date unit_approved_date;
	private String bde_approved_by;
	private Date bde_approved_date;
	private String div_approved_by;
	private Date div_approved_date;
	private String corps_approved_by;
	private Date corps_approved_date;
	private String rejected_by;
	private Date rejected_date;
	private String con_hq_approved_by;
	private Date con_hq_approved_date;
	private String reject_remarks;
	private String final_sanctioning_icno;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getControlling_hq_type() {
		return controlling_hq_type;
	}
	public void setControlling_hq_type(String controlling_hq_type) {
		this.controlling_hq_type = controlling_hq_type;
	}
	public String getUnit_sus_no() {
		return unit_sus_no;
	}
	public void setUnit_sus_no(String unit_sus_no) {
		this.unit_sus_no = unit_sus_no;
	}
	public int getUnit_deo_status() {
		return unit_deo_status;
	}
	public void setUnit_deo_status(int unit_deo_status) {
		this.unit_deo_status = unit_deo_status;
	}
	public int getUnit_app_status() {
		return unit_app_status;
	}
	public void setUnit_app_status(int unit_app_status) {
		this.unit_app_status = unit_app_status;
	}
	public String getBde_status() {
		return bde_status;
	}
	public void setBde_status(String bde_status) {
		this.bde_status = bde_status;
	}
	public String getDiv_status() {
		return div_status;
	}
	public void setDiv_status(String div_status) {
		this.div_status = div_status;
	}
	public String getCorps_status() {
		return corps_status;
	}
	public void setCorps_status(String corps_status) {
		this.corps_status = corps_status;
	}
	public String getComd_status() {
		return comd_status;
	}
	public void setComd_status(String comd_status) {
		this.comd_status = comd_status;
	}
	public int getFinal_status() {
		return final_status;
	}
	public void setFinal_status(int final_status) {
		this.final_status = final_status;
	}
	public String getControl_id() {
		return control_id;
	}
	public void setControl_id(String control_id) {
		this.control_id = control_id;
	}
	public String getSenction_file() {
		return senction_file;
	}
	public void setSenction_file(String senction_file) {
		this.senction_file = senction_file;
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
	public String getUnit_approved_by() {
		return unit_approved_by;
	}
	public void setUnit_approved_by(String unit_approved_by) {
		this.unit_approved_by = unit_approved_by;
	}
	public Date getUnit_approved_date() {
		return unit_approved_date;
	}
	public void setUnit_approved_date(Date unit_approved_date) {
		this.unit_approved_date = unit_approved_date;
	}
	public String getBde_approved_by() {
		return bde_approved_by;
	}
	public void setBde_approved_by(String bde_approved_by) {
		this.bde_approved_by = bde_approved_by;
	}
	public Date getBde_approved_date() {
		return bde_approved_date;
	}
	public void setBde_approved_date(Date bde_approved_date) {
		this.bde_approved_date = bde_approved_date;
	}
	public String getDiv_approved_by() {
		return div_approved_by;
	}
	public void setDiv_approved_by(String div_approved_by) {
		this.div_approved_by = div_approved_by;
	}
	public Date getDiv_approved_date() {
		return div_approved_date;
	}
	public void setDiv_approved_date(Date div_approved_date) {
		this.div_approved_date = div_approved_date;
	}
	public String getCorps_approved_by() {
		return corps_approved_by;
	}
	public void setCorps_approved_by(String corps_approved_by) {
		this.corps_approved_by = corps_approved_by;
	}
	public Date getCorps_approved_date() {
		return corps_approved_date;
	}
	public void setCorps_approved_date(Date corps_approved_date) {
		this.corps_approved_date = corps_approved_date;
	}
	public String getRejected_by() {
		return rejected_by;
	}
	public void setRejected_by(String rejected_by) {
		this.rejected_by = rejected_by;
	}
	public Date getRejected_date() {
		return rejected_date;
	}
	public void setRejected_date(Date rejected_date) {
		this.rejected_date = rejected_date;
	}
	public String getCon_hq_approved_by() {
		return con_hq_approved_by;
	}
	public void setCon_hq_approved_by(String con_hq_approved_by) {
		this.con_hq_approved_by = con_hq_approved_by;
	}
	public Date getCon_hq_approved_date() {
		return con_hq_approved_date;
	}
	public void setCon_hq_approved_date(Date con_hq_approved_date) {
		this.con_hq_approved_date = con_hq_approved_date;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getFinal_sanctioning_icno() {
		return final_sanctioning_icno;
	}
	public void setFinal_sanctioning_icno(String final_sanctioning_icno) {
		this.final_sanctioning_icno = final_sanctioning_icno;
	}
	public String getForm_code_admin() {
		return form_code_admin;
	}
	public void setForm_code_admin(String form_code_admin) {
		this.form_code_admin = form_code_admin;
	}
	
	

}
