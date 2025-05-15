package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_med_ch_milk_quality", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Tb_Med_Ch_milk_quality {
	
	int id;
	String sample_number;
	int specific_gravity;
	int fat_content;
	int total_solids;
	int p_id;
	
	
	String created_by;
	String modified_by;
	Date created_date;
	Date modified_date;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSample_number() {
		return sample_number;
	}
	public void setSample_number(String sample_number) {
		this.sample_number = sample_number;
	}
	public int getSpecific_gravity() {
		return specific_gravity;
	}
	public void setSpecific_gravity(int specific_gravity) {
		this.specific_gravity = specific_gravity;
	}
	public int getFat_content() {
		return fat_content;
	}
	public void setFat_content(int fat_content) {
		this.fat_content = fat_content;
	}
	public int getTotal_solids() {
		return total_solids;
	}
	public void setTotal_solids(int total_solids) {
		this.total_solids = total_solids;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
}
