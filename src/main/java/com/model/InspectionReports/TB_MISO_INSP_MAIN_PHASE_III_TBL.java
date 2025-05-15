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
@Table(name = "tb_miso_insp_main_phase_iii_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MAIN_PHASE_III_TBL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Integer individual_training;
	private Integer collective_training;
	private Integer management_training;
	private Integer personnel_management;
	private Integer interior_economy;
	private Integer morale_motivation;
	private Integer material_management;

	private Integer other_miscellaneous_aspects;
	private Integer measures_core_values;
	private Integer human_resource_developement;
	private Integer additional_information;
	private Integer comments_insp_offr;
	private Integer overall_strg_chall;


	private Integer status;
	private String year;
	private Date created_date;
	private String created_by;
	private Date approved_date;
	private String approved_by;

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
	
	public Integer getIndividual_training() {
		return individual_training;
	}
	public void setIndividual_training(Integer individual_training) {
		this.individual_training = individual_training;
	}
	public Integer getCollective_training() {
		return collective_training;
	}
	public void setCollective_training(Integer collective_training) {
		this.collective_training = collective_training;
	}
	public Integer getManagement_training() {
		return management_training;
	}
	public void setManagement_training(Integer management_training) {
		this.management_training = management_training;
	}
	public Integer getPersonnel_management() {
		return personnel_management;
	}
	public void setPersonnel_management(Integer personnel_management) {
		this.personnel_management = personnel_management;
	}
	public Integer getInterior_economy() {
		return interior_economy;
	}
	public void setInterior_economy(Integer interior_economy) {
		this.interior_economy = interior_economy;
	}
	public Integer getMorale_motivation() {
		return morale_motivation;
	}
	public void setMorale_motivation(Integer morale_motivation) {
		this.morale_motivation = morale_motivation;
	}
	public Integer getMaterial_management() {
		return material_management;
	}
	public void setMaterial_management(Integer material_management) {
		this.material_management = material_management;
	}
	public Integer getOther_miscellaneous_aspects() {
		return other_miscellaneous_aspects;
	}
	public void setOther_miscellaneous_aspects(Integer other_miscellaneous_aspects) {
		this.other_miscellaneous_aspects = other_miscellaneous_aspects;
	}
	public Integer getMeasures_core_values() {
		return measures_core_values;
	}
	public void setMeasures_core_values(Integer measures_core_values) {
		this.measures_core_values = measures_core_values;
	}
	public Integer getHuman_resource_developement() {
		return human_resource_developement;
	}
	public void setHuman_resource_developement(Integer human_resource_developement) {
		this.human_resource_developement = human_resource_developement;
	}
	public Integer getAdditional_information() {
		return additional_information;
	}
	public void setAdditional_information(Integer additional_information) {
		this.additional_information = additional_information;
	}
	public Integer getComments_insp_offr() {
		return comments_insp_offr;
	}
	public void setComments_insp_offr(Integer comments_insp_offr) {
		this.comments_insp_offr = comments_insp_offr;
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
	public Integer getOverall_strg_chall() {
		return overall_strg_chall;
	}
	public void setOverall_strg_chall(Integer overall_strg_chall) {
		this.overall_strg_chall = overall_strg_chall;
	}



}
