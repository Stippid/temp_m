package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_tms_mct_nodal_dir_master", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TB_TMS_MCT_NODAL_DIR_MASTER {
	
	  private int id;
	  //private String nodal_dir;
	  private String created_by;
	  private String created_on;
	  
	  private String sus_no;
	  
	 
	  private String type_of_agncy;
	  
	  private String depot_code;
	  private String type_of_veh;
	  
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
/*	@Column(name="nodal_dir" )
	public String getNodal_dir() {
		return nodal_dir;
	}
	public void setNodal_dir(String nodal_dir) {
		this.nodal_dir = nodal_dir;
	}*/
	
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
	
	@Column(name="sus_no" )
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	@Column(name="type_of_agncy" , nullable = false)
	public String getType_of_agncy() {
		return type_of_agncy;
	}
	public void setType_of_agncy(String type_of_agncy) {
		this.type_of_agncy = type_of_agncy;
	}
	
	public String getDepot_code() {
		return depot_code;
	}
	public void setDepot_code(String depot_code) {
		this.depot_code = depot_code;
	}
	
	public String getType_of_veh() {
		return type_of_veh;
	}
	public void setType_of_veh(String type_of_veh) {
		this.type_of_veh = type_of_veh;
	}
	  
	  
	  

}
