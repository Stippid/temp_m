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
@Table(name = "tb_miso_fitness_for_war_or_designated_role", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class TB_MISO_FITNESS_FOR_WAR_OR_DESIGNATED_ROLE {
	
	private int id;
	private String training_and_operational;
	private String equipment_profile;
	private String administration;
	private Date created_date;
	private String created_by;
	private Date modify_date;
	private String modify_by;
	private int status;
	private String sus_no;
	private String year;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTraining_and_operational() {
		return training_and_operational;
	}
	public void setTraining_and_operational(String training_and_operational) {
		this.training_and_operational = training_and_operational;
	}
	public String getEquipment_profile() {
		return equipment_profile;
	}
	public void setEquipment_profile(String equipment_profile) {
		this.equipment_profile = equipment_profile;
	}
	public String getAdministration() {
		return administration;
	}
	public void setAdministration(String administration) {
		this.administration = administration;
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
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	
}
