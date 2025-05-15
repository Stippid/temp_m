package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_orbat_unit_other_function", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class Tb_Miso_Orbat_Unit_Other_Function {

	private int id ;
	private String sus_no;
	//private String unit_name;
	private String role;
	private String fuction1;
	private String function2;
	private String function_mms;
	private String function_tms;
	private String function_pers;
	private String function_auto;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFuction1() {
		return fuction1;
	}
	public void setFuction1(String fuction1) {
		this.fuction1 = fuction1;
	}
	public String getFunction2() {
		return function2;
	}
	public void setFunction2(String function2) {
		this.function2 = function2;
	}
	public String getFunction_mms() {
		return function_mms;
	}
	public void setFunction_mms(String function_mms) {
		this.function_mms = function_mms;
	}
	public String getFunction_tms() {
		return function_tms;
	}
	public void setFunction_tms(String function_tms) {
		this.function_tms = function_tms;
	}
	public String getFunction_pers() {
		return function_pers;
	}
	public void setFunction_pers(String function_pers) {
		this.function_pers = function_pers;
	}
	public String getFunction_auto() {
		return function_auto;
	}
	public void setFunction_auto(String function_auto) {
		this.function_auto = function_auto;
	}
}
