package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_animals_ue_master", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class TMS_TB_UE_MASTER {

	
	   private int id;
	   private String sus_no;
	   //private String unit_name;
	   private String ue_of_dogs;
	   private String ue_of_equins;
	   private String anml_type;
	   private String created_by;
	   private String modify_by;
	   private Date created_date;
	   private Date modify_date;
	   private int specialization;
	   private int type_equines;
	   
	   
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
	/*public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}*/
	
	public String getAnml_type() {
		return anml_type;
	}
	public void setAnml_type(String anml_type) {
		this.anml_type = anml_type;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getUe_of_dogs() {
		return ue_of_dogs;
	}
	public void setUe_of_dogs(String ue_of_dogs) {
		this.ue_of_dogs = ue_of_dogs;
	}
	public String getUe_of_equins() {
		return ue_of_equins;
	}
	public void setUe_of_equins(String ue_of_equins) {
		this.ue_of_equins = ue_of_equins;
	}
	public int getSpecialization() {
		return specialization;
	}
	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}
	public int getType_equines() {
		return type_equines;
	}
	public void setType_equines(int type_equines) {
		this.type_equines = type_equines;
	}
	
	   
	   
	   
}
