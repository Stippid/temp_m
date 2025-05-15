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
@Table(name = "tb_miso_insp_main_phase_ii_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MAIN_PHASE_II_TBL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Integer op_preparedness_item;
	private Integer training_item;
	private Integer state_weapon_item;
	private Integer state_personnel_item;
	private Integer administation_item;
	private Integer aspect_item;
	private Integer interior_item;

	private Integer major_achievements_item;
	private Integer strength_of_unit_item;
	private Integer challenges_item;
	private Integer improve_following_item;
	private Integer attention_of_higher_item;
	private Integer mitigation_item;
	private Integer points_discussion_item;


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
	public Integer getOp_preparedness_item() {
		return op_preparedness_item;
	}
	public void setOp_preparedness_item(Integer op_preparedness_item) {
		this.op_preparedness_item = op_preparedness_item;
	}
	public Integer getTraining_item() {
		return training_item;
	}
	public void setTraining_item(Integer training_item) {
		this.training_item = training_item;
	}
	public Integer getState_weapon_item() {
		return state_weapon_item;
	}
	public void setState_weapon_item(Integer state_weapon_item) {
		this.state_weapon_item = state_weapon_item;
	}
	public Integer getState_personnel_item() {
		return state_personnel_item;
	}
	public void setState_personnel_item(Integer state_personnel_item) {
		this.state_personnel_item = state_personnel_item;
	}
	public Integer getAdministation_item() {
		return administation_item;
	}
	public void setAdministation_item(Integer administation_item) {
		this.administation_item = administation_item;
	}
	public Integer getAspect_item() {
		return aspect_item;
	}
	public void setAspect_item(Integer aspect_item) {
		this.aspect_item = aspect_item;
	}
	public Integer getInterior_item() {
		return interior_item;
	}
	public void setInterior_item(Integer interior_item) {
		this.interior_item = interior_item;
	}
	public Integer getMajor_achievements_item() {
		return major_achievements_item;
	}
	public void setMajor_achievements_item(Integer major_achievements_item) {
		this.major_achievements_item = major_achievements_item;
	}
	public Integer getStrength_of_unit_item() {
		return strength_of_unit_item;
	}
	public void setStrength_of_unit_item(Integer strength_of_unit_item) {
		this.strength_of_unit_item = strength_of_unit_item;
	}
	public Integer getChallenges_item() {
		return challenges_item;
	}
	public void setChallenges_item(Integer challenges_item) {
		this.challenges_item = challenges_item;
	}
	public Integer getImprove_following_item() {
		return improve_following_item;
	}
	public void setImprove_following_item(Integer improve_following_item) {
		this.improve_following_item = improve_following_item;
	}
	public Integer getAttention_of_higher_item() {
		return attention_of_higher_item;
	}
	public void setAttention_of_higher_item(Integer attention_of_higher_item) {
		this.attention_of_higher_item = attention_of_higher_item;
	}
	public Integer getMitigation_item() {
		return mitigation_item;
	}
	public void setMitigation_item(Integer mitigation_item) {
		this.mitigation_item = mitigation_item;
	}
	public Integer getPoints_discussion_item() {
		return points_discussion_item;
	}
	public void setPoints_discussion_item(Integer points_discussion_item) {
		this.points_discussion_item = points_discussion_item;
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



}
