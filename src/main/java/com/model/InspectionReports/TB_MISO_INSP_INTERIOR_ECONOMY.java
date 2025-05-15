package com.model.InspectionReports;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_miso_insp_interior_economy", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_INTERIOR_ECONOMY {

	@Id
	@GeneratedValue
	private int id;

	private String living_condition;
	private String state_personal_clothing;
	private String initiative_units;
	private String facilities_living_area;
	private String modern_equipment_Company;
	private String equipment_procured_manpower;
	private String timely_correct_Publication;
	private String state_documentation;
	private String personal_no;
	
	private Integer status;
	private String year;	
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;
private String sus_no;
	
	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLiving_condition() {
		return living_condition;
	}
	public void setLiving_condition(String living_condition) {
		this.living_condition = living_condition;
	}
	public String getState_personal_clothing() {
		return state_personal_clothing;
	}
	public void setState_personal_clothing(String state_personal_clothing) {
		this.state_personal_clothing = state_personal_clothing;
	}
	public String getInitiative_units() {
		return initiative_units;
	}
	public void setInitiative_units(String initiative_units) {
		this.initiative_units = initiative_units;
	}
	public String getFacilities_living_area() {
		return facilities_living_area;
	}
	public void setFacilities_living_area(String facilities_living_area) {
		this.facilities_living_area = facilities_living_area;
	}
	public String getModern_equipment_Company() {
		return modern_equipment_Company;
	}
	public void setModern_equipment_Company(String modern_equipment_Company) {
		this.modern_equipment_Company = modern_equipment_Company;
	}
	public String getEquipment_procured_manpower() {
		return equipment_procured_manpower;
	}
	public void setEquipment_procured_manpower(String equipment_procured_manpower) {
		this.equipment_procured_manpower = equipment_procured_manpower;
	}
	public String getTimely_correct_Publication() {
		return timely_correct_Publication;
	}
	public void setTimely_correct_Publication(String timely_correct_Publication) {
		this.timely_correct_Publication = timely_correct_Publication;
	}
	public String getState_documentation() {
		return state_documentation;
	}
	public void setState_documentation(String state_documentation) {
		this.state_documentation = state_documentation;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getPersonal_no() {
		return personal_no;
	}
	public void setPersonal_no(String personal_no) {
		this.personal_no = personal_no;
	}
	
}
