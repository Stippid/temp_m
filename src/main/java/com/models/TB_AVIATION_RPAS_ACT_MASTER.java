package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_aviation_rpas_act_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_AVIATION_RPAS_ACT_MASTER {
	
	private int id;
	private BigInteger act;
	private String std_nomclature;
	private String abc_group;
	private String type_of_aircraft;
	private int max_take_off_weight;
	private int max_payload_weight;
	private double wing_span;
	private double length;
	private double height;
	private String type_of_fuel;
	private String fuel_tank_capacity;
	private String auth_letter_no;
	private String creation_by;
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigInteger getAct() {
		return act;
	}
	public void setAct(BigInteger act) {
		this.act = act;
	}
	public String getStd_nomclature() {
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature) {
		this.std_nomclature = std_nomclature;
	}
	public String getAbc_group() {
		return abc_group;
	}
	public void setAbc_group(String abc_group) {
		this.abc_group = abc_group;
	}
	public String getType_of_aircraft() {
		return type_of_aircraft;
	}
	public void setType_of_aircraft(String type_of_aircraft) {
		this.type_of_aircraft = type_of_aircraft;
	}
	public int getMax_take_off_weight() {
		return max_take_off_weight;
	}
	public void setMax_take_off_weight(int max_take_off_weight) {
		this.max_take_off_weight = max_take_off_weight;
	}
	public int getMax_payload_weight() {
		return max_payload_weight;
	}
	public void setMax_payload_weight(int max_payload_weight) {
		this.max_payload_weight = max_payload_weight;
	}
	public double getWing_span() {
		return wing_span;
	}
	public void setWing_span(double wing_span) {
		this.wing_span = wing_span;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getType_of_fuel() {
		return type_of_fuel;
	}
	public void setType_of_fuel(String type_of_fuel) {
		this.type_of_fuel = type_of_fuel;
	}
	public String getFuel_tank_capacity() {
		return fuel_tank_capacity;
	}
	public void setFuel_tank_capacity(String fuel_tank_capacity) {
		this.fuel_tank_capacity = fuel_tank_capacity;
	}
	public String getAuth_letter_no() {
		return auth_letter_no;
	}
	public void setAuth_letter_no(String auth_letter_no) {
		this.auth_letter_no = auth_letter_no;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
}
