package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "tb_tms_mct_main_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MCT_MAIN_MASTER {
	
	private int id;
	private String mct_main_id; 
	private String mct_nomen;
	private String prf_code;
	private String vehicle_class_code;
	private String created_on;
	private String created_by;
	private String type_of_veh;
	private String sus_no;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMct_main_id() {
		return mct_main_id;
	}
	public void setMct_main_id(String mct_main_id) {
		this.mct_main_id = mct_main_id;
	}
	public String getMct_nomen() {
		return mct_nomen;
	}
	public void setMct_nomen(String mct_nomen) {
		this.mct_nomen = mct_nomen;
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
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

}
