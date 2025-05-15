package com.models;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cue_tb_miso_provision_weapon_footnotes", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CUE_TB_MISO_PROVISION_WEAPON_FOOTNOTES {
	
	private int id;
	private int provision_id;
	private String  we_pe_no;
	private Double  amt_inc_dec;
	private String  condition;
	private String  created_by;
	private String  created_on;
	private String  modified_by;
	private String  modified_on;
	private String  item_code;
	private String  no_of_units;
	private String status;
	private int foot_id;
	private String scenario;
	private String loc_for_unit;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}
	
	public Double getAmt_inc_dec() {
		return amt_inc_dec;
	}
	public void setAmt_inc_dec(Double amt_inc_dec) {
		this.amt_inc_dec = amt_inc_dec;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getNo_of_units() {
		return no_of_units;
	}
	public void setNo_of_units(String no_of_units) {
		this.no_of_units = no_of_units;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFoot_id() {
		return foot_id;
	}
	public void setFoot_id(int foot_id) {
		this.foot_id = foot_id;
	}

	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getLoc_for_unit() {
		return loc_for_unit;
	}
	public void setLoc_for_unit(String loc_for_unit) {
		this.loc_for_unit = loc_for_unit;
	}
	
}
