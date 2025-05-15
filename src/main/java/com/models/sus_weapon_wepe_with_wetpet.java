
package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "sus_weapon_wepe_with_wetpet")
public class sus_weapon_wepe_with_wetpet {
	
	private String sus_no;
	private String we_wet_no;
	
	private int base_auth;
	
	
	private int mod_auth;
	
	private int foot_auth;
	private String item_type;
	private String item_seq_no;
	private String type_we_wet;
	private String ces_cces;
	private String ces_cces_no;
	private int pers_id;
	
	@Id
	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
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
	public String getWe_wet_no() {
		return we_wet_no;
	}
	public void setWe_wet_no(String we_wet_no) {
		this.we_wet_no = we_wet_no;
	}
	public String getType_we_wet() {
		return type_we_wet;
	}
	public void setType_we_wet(String type_we_wet) {
		this.type_we_wet = type_we_wet;
	}
	public String getCes_cces_no() {
		return ces_cces_no;
	}
	public void setCes_cces_no(String ces_cces_no) {
		this.ces_cces_no = ces_cces_no;
	}
	public String getCes_cces() {
		return ces_cces;
	}
	public void setCes_cces(String ces_cces) {
		this.ces_cces = ces_cces;
	}
	public int getPers_id() {
		return pers_id;
	}
	public void setPers_id(int pers_id) {
		this.pers_id = pers_id;
	}

	
	
	
}
