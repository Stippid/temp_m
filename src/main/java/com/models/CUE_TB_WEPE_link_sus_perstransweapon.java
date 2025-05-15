package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_wepe_link_sus_perstransweapon", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_WEPE_link_sus_perstransweapon {
	
	private int id;
	private String  sus_no;
	private String wepe_pers_no;
	private String wepe_trans_no;
	private String wepe_weapon_no;
	private String  created_on;
	private String  created_on_trans;
	private String  created_on_wepon;
	private String created_by;
	private String created_by_trans;
	private String created_by_wepon;
	private String status_pers;
	private String status_trans;
	private String status_weap;
	
	private int roleid;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getWepe_pers_no() {
		return wepe_pers_no;
	}
	public void setWepe_pers_no(String wepe_pers_no) {
		this.wepe_pers_no = wepe_pers_no;
	}
	public String getWepe_trans_no() {
		return wepe_trans_no;
	}
	public void setWepe_trans_no(String wepe_trans_no) {
		this.wepe_trans_no = wepe_trans_no;
	}
	public String getWepe_weapon_no() {
		return wepe_weapon_no;
	}
	public void setWepe_weapon_no(String wepe_weapon_no) {
		this.wepe_weapon_no = wepe_weapon_no;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getCreated_on_trans() {
		return created_on_trans;
	}
	public void setCreated_on_trans(String created_on_trans) {
		this.created_on_trans = created_on_trans;
	}
	public String getCreated_on_wepon() {
		return created_on_wepon;
	}
	public void setCreated_on_wepon(String created_on_wepon) {
		this.created_on_wepon = created_on_wepon;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_by_trans() {
		return created_by_trans;
	}
	public void setCreated_by_trans(String created_by_trans) {
		this.created_by_trans = created_by_trans;
	}
	public String getCreated_by_wepon() {
		return created_by_wepon;
	}
	public void setCreated_by_wepon(String created_by_wepon) {
		this.created_by_wepon = created_by_wepon;
	}
	public String getStatus_pers() {
		return status_pers;
	}
	public void setStatus_pers(String status_pers) {
		this.status_pers = status_pers;
	}
	public String getStatus_trans() {
		return status_trans;
	}
	public void setStatus_trans(String status_trans) {
		this.status_trans = status_trans;
	}
	public String getStatus_weap() {
		return status_weap;
	}
	public void setStatus_weap(String status_weap) {
		this.status_weap = status_weap;
	}
	
	private String modified_by;
	private String modified_on;
	
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	
	private String modified_by_trans;
	private String modified_on_trans;
	
	
	
	private String modified_by_weap;
	private String modified_on_weap;
	
	
	public String getModified_by_trans() {
		return modified_by_trans;
	}
	public void setModified_by_trans(String modified_by_trans) {
		this.modified_by_trans = modified_by_trans;
	}
	public String getModified_on_trans() {
		return modified_on_trans;
	}
	public void setModified_on_trans(String modified_on_trans) {
		this.modified_on_trans = modified_on_trans;
	}
	public String getModified_by_weap() {
		return modified_by_weap;
	}
	public void setModified_by_weap(String modified_by_weap) {
		this.modified_by_weap = modified_by_weap;
	}
	public String getModified_on_weap() {
		return modified_on_weap;
	}
	public void setModified_on_weap(String modified_on_weap) {
		this.modified_on_weap = modified_on_weap;
	}
	private String remarks;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	
}
