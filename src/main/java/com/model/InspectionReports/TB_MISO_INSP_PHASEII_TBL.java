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
@Table(name = "tb_miso_insp_phaseII_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_PHASEII_TBL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;

	private String op_preparedness_item;
	private String training_item;
	private String state_weapon_item;
	private String state_weapon_item_ii;
	private String state_personnel_item;
	private String administation_item;
	private String aspect_item;
	private String interior_item;
	private String major_achievements_item;
	private String strength_of_unit_item;
	private String challenges_item;
	private String improve_following_item;
	private String attention_of_higher_item;
	private String mitigation_item;
	private String points_discussion_item;
	private int status;
	private String year;
	private Date created_date;
	private String created_by;

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
	public String getOp_preparedness_item() {
		return op_preparedness_item;
	}
	public void setOp_preparedness_item(String op_preparedness_item) {
		this.op_preparedness_item = op_preparedness_item;
	}
	public String getTraining_item() {
		return training_item;
	}
	public void setTraining_item(String training_item) {
		this.training_item = training_item;
	}
	public String getState_weapon_item() {
		return state_weapon_item;
	}
	public void setState_weapon_item(String state_weapon_item) {
		this.state_weapon_item = state_weapon_item;
	}
	public String getState_personnel_item() {
		return state_personnel_item;
	}
	public void setState_personnel_item(String state_personnel_item) {
		this.state_personnel_item = state_personnel_item;
	}
	public String getAdministation_item() {
		return administation_item;
	}
	public void setAdministation_item(String administation_item) {
		this.administation_item = administation_item;
	}
	public String getAspect_item() {
		return aspect_item;
	}
	public void setAspect_item(String aspect_item) {
		this.aspect_item = aspect_item;
	}
	public String getInterior_item() {
		return interior_item;
	}
	public void setInterior_item(String interior_item) {
		this.interior_item = interior_item;
	}
	public String getMajor_achievements_item() {
		return major_achievements_item;
	}
	public void setMajor_achievements_item(String major_achievements_item) {
		this.major_achievements_item = major_achievements_item;
	}
	public String getStrength_of_unit_item() {
		return strength_of_unit_item;
	}
	public void setStrength_of_unit_item(String strength_of_unit_item) {
		this.strength_of_unit_item = strength_of_unit_item;
	}
	public String getChallenges_item() {
		return challenges_item;
	}
	public void setChallenges_item(String challenges_item) {
		this.challenges_item = challenges_item;
	}
	public String getImprove_following_item() {
		return improve_following_item;
	}
	public void setImprove_following_item(String improve_following_item) {
		this.improve_following_item = improve_following_item;
	}
	public String getAttention_of_higher_item() {
		return attention_of_higher_item;
	}
	public void setAttention_of_higher_item(String attention_of_higher_item) {
		this.attention_of_higher_item = attention_of_higher_item;
	}
	public String getMitigation_item() {
		return mitigation_item;
	}
	public void setMitigation_item(String mitigation_item) {
		this.mitigation_item = mitigation_item;
	}
	public String getPoints_discussion_item() {
		return points_discussion_item;
	}
	public void setPoints_discussion_item(String points_discussion_item) {
		this.points_discussion_item = points_discussion_item;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getState_weapon_item_ii() {
		return state_weapon_item_ii;
	}
	public void setState_weapon_item_ii(String state_weapon_item_ii) {
		this.state_weapon_item_ii = state_weapon_item_ii;
	}



}