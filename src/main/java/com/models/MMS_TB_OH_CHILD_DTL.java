package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mms_tb_oh_child_dtl", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), })
public class MMS_TB_OH_CHILD_DTL {

	private int id;
	private String oh_type;
	private Date oh_due_dt;
	private Date oh_done_dt;
	private String eqpt_regn_no;
	private Date dispatch_dt;
	private Date boh_compl_dt;
	private Date gun_recd_dt;
	private Date dt_of_intro;
	private String sus_no;
	private String wksp_name;
	private Date wksp_in_dt;
	private String created_by;
	private Date created_on;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOh_type() {
		return oh_type;
	}

	public void setOh_type(String oh_type) {
		this.oh_type = oh_type;
	}

	public Date getOh_due_dt() {
		return oh_due_dt;
	}

	public void setOh_due_dt(Date oh_due_dt) {
		this.oh_due_dt = oh_due_dt;
	}

	public Date getOh_done_dt() {
		return oh_done_dt;
	}

	public void setOh_done_dt(Date oh_done_dt) {
		this.oh_done_dt = oh_done_dt;
	}

	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}

	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}

	public Date getDispatch_dt() {
		return dispatch_dt;
	}

	public void setDispatch_dt(Date dispatch_dt) {
		this.dispatch_dt = dispatch_dt;
	}

	public Date getBoh_compl_dt() {
		return boh_compl_dt;
	}

	public void setBoh_compl_dt(Date boh_compl_dt) {
		this.boh_compl_dt = boh_compl_dt;
	}

	public Date getGun_recd_dt() {
		return gun_recd_dt;
	}

	public void setGun_recd_dt(Date gun_recd_dt) {
		this.gun_recd_dt = gun_recd_dt;
	}

	public Date getDt_of_intro() {
		return dt_of_intro;
	}

	public void setDt_of_intro(Date dt_of_intro) {
		this.dt_of_intro = dt_of_intro;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getWksp_name() {
		return wksp_name;
	}

	public void setWksp_name(String wksp_name) {
		this.wksp_name = wksp_name;
	}

	public Date getWksp_in_dt() {
		return wksp_in_dt;
	}

	public void setWksp_in_dt(Date wksp_in_dt) {
		this.wksp_in_dt = wksp_in_dt;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
}
