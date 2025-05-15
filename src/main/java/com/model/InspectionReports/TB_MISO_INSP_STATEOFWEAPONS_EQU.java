package com.model.InspectionReports;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_insp_stateofweapons_equ", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_STATEOFWEAPONS_EQU {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private String nature_deficiency;
	private String action_deficiency;
	private String effect_conduct;
	private String remarks;
	private String status;
	private String year;
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;


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
	public String getNature_deficiency() {
		return nature_deficiency;
	}
	public void setNature_deficiency(String nature_deficiency) {
		this.nature_deficiency = nature_deficiency;
	}
	public String getAction_deficiency() {
		return action_deficiency;
	}
	public void setAction_deficiency(String action_deficiency) {
		this.action_deficiency = action_deficiency;
	}
	public String getEffect_conduct() {
		return effect_conduct;
	}
	public void setEffect_conduct(String effect_conduct) {
		this.effect_conduct = effect_conduct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
