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
@Table(name = "mms_tb_barrel_child_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class MMS_TB_BARREL_CHILD_DTL {
	private int id;
	private String barrel_regn_no;
	private String eqpt_regn_no;
	private String op_clear;
	private Date op_clear_dt;
	private String wksp_name;
	private Date wksp_in_dt;
	private String rifling_vertical;
	private String rifling_horizontal;
	private String efc;
	private String qtr_of_life;
	private String total_rds;
	private Date dt_last_fired;
	private String sus_no;
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
	public String getBarrel_regn_no() {
		return barrel_regn_no;
	}
	public void setBarrel_regn_no(String barrel_regn_no) {
		this.barrel_regn_no = barrel_regn_no;
	}
	public String getEqpt_regn_no() {
		return eqpt_regn_no;
	}
	public void setEqpt_regn_no(String eqpt_regn_no) {
		this.eqpt_regn_no = eqpt_regn_no;
	}
	public String getOp_clear() {
		return op_clear;
	}
	public void setOp_clear(String op_clear) {
		this.op_clear = op_clear;
	}
	public Date getOp_clear_dt() {
		return op_clear_dt;
	}
	public void setOp_clear_dt(Date op_clear_dt) {
		this.op_clear_dt = op_clear_dt;
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
	public String getRifling_vertical() {
		return rifling_vertical;
	}
	public void setRifling_vertical(String rifling_vertical) {
		this.rifling_vertical = rifling_vertical;
	}
	public String getRifling_horizontal() {
		return rifling_horizontal;
	}
	public void setRifling_horizontal(String rifling_horizontal) {
		this.rifling_horizontal = rifling_horizontal;
	}
	public String getEfc() {
		return efc;
	}
	public void setEfc(String efc) {
		this.efc = efc;
	}
	public String getQtr_of_life() {
		return qtr_of_life;
	}
	public void setQtr_of_life(String qtr_of_life) {
		this.qtr_of_life = qtr_of_life;
	}
	public String getTotal_rds() {
		return total_rds;
	}
	public void setTotal_rds(String total_rds) {
		this.total_rds = total_rds;
	}
	public Date getDt_last_fired() {
		return dt_last_fired;
	}
	public void setDt_last_fired(Date dt_last_fired) {
		this.dt_last_fired = dt_last_fired;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
