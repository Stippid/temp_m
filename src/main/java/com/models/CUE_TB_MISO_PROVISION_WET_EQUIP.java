package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_provision_wet_equip", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_PROVISION_WET_EQUIP {
	private int id;
	private double auth_weapon;
	private String  created_by;
	private String  created_on;
	private String  item_seq_no;
	private String  modified_by;
	private String   modified_on ;
	private int  provision_id;
	private String wet_pet_no;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	 
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	
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
	public int getProvision_id() {
		return provision_id;
	}
	public void setProvision_id(int provision_id) {
		this.provision_id = provision_id;
	}
	
	public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}
	public String getItem_seq_no() {
		return item_seq_no;
	}
	public void setItem_seq_no(String item_seq_no) {
		this.item_seq_no = item_seq_no;
	}
	
	public Double getAuth_weapon() {
		return auth_weapon;
	}
	public void setAuth_weapon(Double auth_weapon) {
		this.auth_weapon = auth_weapon;
	}
	
	
}
