package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_aviation_act_main_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_AVIATION_ACT_MAIN_MASTER {
	
	private int id;
	private String act_main_id; 
	private String act_nomen;
	private String abc_code;
	private String created_on;
	private String created_by;
	private String sus_no;
	private String type_of_aircraft;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAct_main_id() {
		return act_main_id;
	}
	public void setAct_main_id(String act_main_id) {
		this.act_main_id = act_main_id;
	}
	public String getAct_nomen() {
		return act_nomen;
	}
	public void setAct_nomen(String act_nomen) {
		this.act_nomen = act_nomen;
	}
	public String getAbc_code() {
		return abc_code;
	}
	public void setAbc_code(String abc_code) {
		this.abc_code = abc_code;
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getType_of_aircraft() {
		return type_of_aircraft;
	}
	public void setType_of_aircraft(String type_of_aircraft) {
		this.type_of_aircraft = type_of_aircraft;
	}
	
}
