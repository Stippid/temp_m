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
@Table(name = "tb_miso_insp_material_management", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MATERIAL_MANAGEMENT {
	
	
	@Id
	@GeneratedValue
	private int id;
	
	private String vehicles;
	private String eqpt_six_months;
	private String maintenance_arms;
	private String maintenance_amn;
	private String maintenance_ordnance_stores;
	private String management_public_funds;
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
	public String getVehicles() {
		return vehicles;
	}
	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
	}
	public String getEqpt_six_months() {
		return eqpt_six_months;
	}
	public void setEqpt_six_months(String eqpt_six_months) {
		this.eqpt_six_months = eqpt_six_months;
	}
	public String getMaintenance_arms() {
		return maintenance_arms;
	}
	public void setMaintenance_arms(String maintenance_arms) {
		this.maintenance_arms = maintenance_arms;
	}
	public String getMaintenance_amn() {
		return maintenance_amn;
	}
	public void setMaintenance_amn(String maintenance_amn) {
		this.maintenance_amn = maintenance_amn;
	}
	public String getMaintenance_ordnance_stores() {
		return maintenance_ordnance_stores;
	}
	public void setMaintenance_ordnance_stores(String maintenance_ordnance_stores) {
		this.maintenance_ordnance_stores = maintenance_ordnance_stores;
	}
	public String getManagement_public_funds() {
		return management_public_funds;
	}
	public void setManagement_public_funds(String management_public_funds) {
		this.management_public_funds = management_public_funds;
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
