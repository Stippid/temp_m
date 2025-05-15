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
@Table(name = "tb_miso_insp_morale_motivation", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MORALE_MOTIVATION {

	
	@Id
	@GeneratedValue
	private int id;
	
	private String state_leave;
	private String discipline_state;
	private String sick_report;
	private String performance_trg_professional;
	private String performance_course;
	private String performance_sports;
	private String state_eqpt_maint;
	private String interior_economy;
	private String documentation;
	private String involvement_junior_leader;
	private String state_regimental;	
	private String pers_discipline;	
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
	public String getState_leave() {
		return state_leave;
	}
	public void setState_leave(String state_leave) {
		this.state_leave = state_leave;
	}
	public String getDiscipline_state() {
		return discipline_state;
	}
	public void setDiscipline_state(String discipline_state) {
		this.discipline_state = discipline_state;
	}
	public String getSick_report() {
		return sick_report;
	}
	public void setSick_report(String sick_report) {
		this.sick_report = sick_report;
	}
	public String getPerformance_trg_professional() {
		return performance_trg_professional;
	}
	public void setPerformance_trg_professional(String performance_trg_professional) {
		this.performance_trg_professional = performance_trg_professional;
	}
	public String getPerformance_course() {
		return performance_course;
	}
	public void setPerformance_course(String performance_course) {
		this.performance_course = performance_course;
	}
	public String getPerformance_sports() {
		return performance_sports;
	}
	public void setPerformance_sports(String performance_sports) {
		this.performance_sports = performance_sports;
	}
	public String getState_eqpt_maint() {
		return state_eqpt_maint;
	}
	public void setState_eqpt_maint(String state_eqpt_maint) {
		this.state_eqpt_maint = state_eqpt_maint;
	}
	public String getInterior_economy() {
		return interior_economy;
	}
	public void setInterior_economy(String interior_economy) {
		this.interior_economy = interior_economy;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	public String getInvolvement_junior_leader() {
		return involvement_junior_leader;
	}
	public void setInvolvement_junior_leader(String involvement_junior_leader) {
		this.involvement_junior_leader = involvement_junior_leader;
	}
	public String getState_regimental() {
		return state_regimental;
	}
	public void setState_regimental(String state_regimental) {
		this.state_regimental = state_regimental;
	}
	public String getPers_discipline() {
		return pers_discipline;
	}
	public void setPers_discipline(String pers_discipline) {
		this.pers_discipline = pers_discipline;
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
