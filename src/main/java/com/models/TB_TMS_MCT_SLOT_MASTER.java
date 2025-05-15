package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "tb_tms_mct_slot_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MCT_SLOT_MASTER {
	
	private int id;
	private int code_no_from; 
	private int code_no_to;
	private String group_name;
	private String created_on;
	private String created_by;
	private String prf_code;
	private String vehicle_class_code;
	private String type_of_veh;
	
	
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
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public String getVehicle_class_code() {
		return vehicle_class_code;
	}
	public void setVehicle_class_code(String vehicle_class_code) {
		this.vehicle_class_code = vehicle_class_code;
	}
	public String getType_of_veh() {
		return type_of_veh;
	}
	public void setType_of_veh(String type_of_veh) {
		this.type_of_veh = type_of_veh;
	}
	
	

}
