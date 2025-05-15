package com.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_aviation_act_slot_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_AVIATION_ACT_SLOT_MASTER {
	
	
	
	
	
	private int id;
	private int code_no_from; 
	private int code_no_to;
	private String group_name;
	private String created_on;
	private String created_by;
	private String abc_code;
	private String type_of_aircraft;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCode_no_from() {
		return code_no_from;
	}
	public void setCode_no_from(int code_no_from) {
		this.code_no_from = code_no_from;
	}
	public int getCode_no_to() {
		return code_no_to;
	}
	public void setCode_no_to(int code_no_to) {
		this.code_no_to = code_no_to;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
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
	public String getAbc_code() {
		return abc_code;
	}
	public void setAbc_code(String abc_code) {
		this.abc_code = abc_code;
	}
//	public String getVehicle_class_code() {
//		return vehicle_class_code;
//	}
//	public void setVehicle_class_code(String vehicle_class_code) {
//		this.vehicle_class_code = vehicle_class_code;
//	}
	public String getType_of_aircraft() {
		return type_of_aircraft;
	}
	public void setType_of_aircraft(String type_of_aircraft) {
		this.type_of_aircraft = type_of_aircraft;
	}

}


