package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_wepe_link_sus_weapon_mdfs", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_WEPE_link_sus_weapon_mdfs {

	
	
	private int id;
	private String  sus_no;
	private String we_pe_no;
	private int fk_weapon_mdfs;
	private String status;
	private String  created_on;
	private String  created_by;
	private String modification;
	
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
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	public int getFk_weapon_mdfs() {
		return fk_weapon_mdfs;
	}
	public void setFk_weapon_mdfs(int fk_weapon_mdfs) {
		this.fk_weapon_mdfs = fk_weapon_mdfs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	
	
	
	
}
