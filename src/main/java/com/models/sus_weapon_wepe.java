/*
package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;



@Entity

@Table(name = "sus_weapon_wepe")
public class sus_weapon_wepe {
	
	private int id;
	private String sus_no;
	private String we_pe_no;
	
	private int base_auth;
	
	
	private int mod_auth;
	
	private int foot_auth;
	private String item_type;
	private String item_seq_no;
	
	
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
	
	

	
	public int getMod_auth() {
		return mod_auth;
	}
	public void setMod_auth(int mod_auth) {
		this.mod_auth = mod_auth;
	}
	public int getBase_auth() {
		return base_auth;
	}
	public void setBase_auth(int base_auth) {
		this.base_auth = base_auth;
	}
	
	
	
	public int getFoot_auth() {
		return foot_auth;
	}
	public void setFoot_auth(int foot_auth) {
		this.foot_auth = foot_auth;
	}
	
	
	
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	
	
	
}
*/