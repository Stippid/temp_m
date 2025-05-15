package com.models.mnh;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_med_surveillance_data", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_Med_Serv_Data {

	
	private int id;
	private int test_self;
	private int test_wife;
	private int test_husband;
	private int test_child_less;
	private int test_child_greater;
	private int test_mother;
	private int test_father;
	private int test_sister;
	private int test_brother;
	private int positive_self;
	private int positive_wife;
	private int positive_husband;
	private int positive_child_less;
	private int positive_child_greater;
	private int positive_mother;
	private int positive_father;
	private int positive_sister;
	private int positive_brother;
	private int surv_master_id;
	

	private String sus_no;
	//private String unit_name;
	
	private Date date_from;
	private Date date_to;
	private String type;
	private String created_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_on  = new Date();

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTest_self() {
		return test_self;
	}
	public void setTest_self(int test_self) {
		this.test_self = test_self;
	}
	public int getTest_wife() {
		return test_wife;
	}
	public void setTest_wife(int test_wife) {
		this.test_wife = test_wife;
	}
	public int getTest_husband() {
		return test_husband;
	}
	public void setTest_husband(int test_husband) {
		this.test_husband = test_husband;
	}
	public int getTest_child_less() {
		return test_child_less;
	}
	public void setTest_child_less(int test_child_less) {
		this.test_child_less = test_child_less;
	}
	public int getTest_child_greater() {
		return test_child_greater;
	}
	public void setTest_child_greater(int test_child_greater) {
		this.test_child_greater = test_child_greater;
	}
	public int getTest_mother() {
		return test_mother;
	}
	public void setTest_mother(int test_mother) {
		this.test_mother = test_mother;
	}
	public int getTest_father() {
		return test_father;
	}
	public void setTest_father(int test_father) {
		this.test_father = test_father;
	}
	public int getTest_sister() {
		return test_sister;
	}
	public void setTest_sister(int test_sister) {
		this.test_sister = test_sister;
	}
	public int getTest_brother() {
		return test_brother;
	}
	public void setTest_brother(int test_brother) {
		this.test_brother = test_brother;
	}
	public int getPositive_self() {
		return positive_self;
	}
	public void setPositive_self(int positive_self) {
		this.positive_self = positive_self;
	}
	public int getPositive_wife() {
		return positive_wife;
	}
	public void setPositive_wife(int positive_wife) {
		this.positive_wife = positive_wife;
	}
	public int getPositive_husband() {
		return positive_husband;
	}
	public void setPositive_husband(int positive_husband) {
		this.positive_husband = positive_husband;
	}
	public int getPositive_child_less() {
		return positive_child_less;
	}
	public void setPositive_child_less(int positive_child_less) {
		this.positive_child_less = positive_child_less;
	}
	public int getPositive_child_greater() {
		return positive_child_greater;
	}
	public void setPositive_child_greater(int positive_child_greater) {
		this.positive_child_greater = positive_child_greater;
	}
	public int getPositive_mother() {
		return positive_mother;
	}
	public void setPositive_mother(int positive_mother) {
		this.positive_mother = positive_mother;
	}
	public int getPositive_father() {
		return positive_father;
	}
	public void setPositive_father(int positive_father) {
		this.positive_father = positive_father;
	}
	public int getPositive_sister() {
		return positive_sister;
	}
	public void setPositive_sister(int positive_sister) {
		this.positive_sister = positive_sister;
	}
	public int getPositive_brother() {
		return positive_brother;
	}
	public void setPositive_brother(int positive_brother) {
		this.positive_brother = positive_brother;
	}
	
	@Column(length= 8)
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	/*
	 * public String getUnit_name() { return unit_name; } public void
	 * setUnit_name(String unit_name) { this.unit_name = unit_name; }
	 */
	@Column(length= 10)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(length= 35)
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public int getSurv_master_id() {
		return surv_master_id;
	}
	public void setSurv_master_id(int surv_master_id) {
		this.surv_master_id = surv_master_id;
	}	
	public Date getDate_from() {
		return date_from;
	}
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	public Date getDate_to() {
		return date_to;
	}
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
}