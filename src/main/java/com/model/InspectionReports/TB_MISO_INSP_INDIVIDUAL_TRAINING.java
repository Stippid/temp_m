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
@Table(name = "tb_miso_insp_individual_training", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_INDIVIDUAL_TRAINING {

	@Id
	@GeneratedValue
	private int id;

	
	private String physical_training;
	private String weapon_training;
	private String weapon_training_results;
	private String night_training;
	private String specialist_training;
	private String yos_training;
	private String officers_training;
	private String training_jco_nco;
	private String training_acc_sco;
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

	public String getPhysical_training() {
		return physical_training;
	}
	public void setPhysical_training(String physical_training) {
		this.physical_training = physical_training;
	}
	public String getWeapon_training() {
		return weapon_training;
	}
	public void setWeapon_training(String weapon_training) {
		this.weapon_training = weapon_training;
	}
	public String getWeapon_training_results() {
		return weapon_training_results;
	}
	public void setWeapon_training_results(String weapon_training_results) {
		this.weapon_training_results = weapon_training_results;
	}
	public String getNight_training() {
		return night_training;
	}
	public void setNight_training(String night_training) {
		this.night_training = night_training;
	}
	public String getSpecialist_training() {
		return specialist_training;
	}
	public void setSpecialist_training(String specialist_training) {
		this.specialist_training = specialist_training;
	}
	public String getYos_training() {
		return yos_training;
	}
	public void setYos_training(String yos_training) {
		this.yos_training = yos_training;
	}
	public String getOfficers_training() {
		return officers_training;
	}
	public void setOfficers_training(String officers_training) {
		this.officers_training = officers_training;
	}
	public String getTraining_jco_nco() {
		return training_jco_nco;
	}
	public void setTraining_jco_nco(String training_jco_nco) {
		this.training_jco_nco = training_jco_nco;
	}
	public String getTraining_acc_sco() {
		return training_acc_sco;
	}
	public void setTraining_acc_sco(String training_acc_sco) {
		this.training_acc_sco = training_acc_sco;
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
