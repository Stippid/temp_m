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
@Table(name = "tb_miso_insp_main_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class TB_MISO_INSP_MAIN_TBL {

	@Id
	@GeneratedValue
	private int id;
	private String sus_no;
	private Integer establishment_item;
	private Integer equipment_item;
	private Integer animals_item;
	private Integer deficiencies_of_equipment2_item;
	private Integer deficiencies_of_equipment_item;
	private Integer state_of_sector_stores_item;
	private Integer wt_results_item;
	private Integer education_standards_item;
	private Integer category_item;	
	private Integer up_gradation_item;
	private Integer regi_language_exam_item;
	private Integer bpet_result_item;
	private Integer ppt_result_item;
	private Integer promotion_exam_officers_item;
	private Integer financial_grants_item;
	private Integer training_ammunition_item;
	private Integer availability_of_ranges_item;
	private Integer outstanding_audit_objections_observations_item;	
	private Integer courses_item;
	
	@Column(name = "summary_item")
	private Integer summarybtn;
	private Integer outstanding_item;
	private Integer mt_accidents_item;
	private Integer details_of_suicides_item;
	private Integer security_lapses_item;
	private Integer details_of_attachments_item;
	private Integer details_of_officers_item;	
	private Integer social_media_violation_item;
	private Integer web_messenger_apps_item;
	private Integer espionage_item;
	private Integer compromise_of_cell_phone_item;
	private Integer untraceable_item;
	private Integer loss_of_cd_item;
	private Integer loss_of_identity_item;
	private Integer land_item;
	private Integer summary_of_report_last_five_year_item;	
	private Integer recruit_training_b_appendix_item;
	private Integer deffi_exp_resp_to_trainng_item;
	private Integer emp_of_unit_during_period_item;
	private Integer regt_funds_item;
	
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
	public Integer getEquipment_item() {
		return equipment_item;
	}
	public void setEquipment_item(Integer equipment_item) {
		this.equipment_item = equipment_item;
	}
	public Integer getAnimals_item() {
		return animals_item;
	}
	public void setAnimals_item(Integer animals_item) {
		this.animals_item = animals_item;
	}
	public Integer getDeficiencies_of_equipment2_item() {
		return deficiencies_of_equipment2_item;
	}
	public void setDeficiencies_of_equipment2_item(Integer deficiencies_of_equipment2_item) {
		this.deficiencies_of_equipment2_item = deficiencies_of_equipment2_item;
	}
	public Integer getDeficiencies_of_equipment_item() {
		return deficiencies_of_equipment_item;
	}
	public void setDeficiencies_of_equipment_item(Integer deficiencies_of_equipment_item) {
		this.deficiencies_of_equipment_item = deficiencies_of_equipment_item;
	}
	public Integer getState_of_sector_stores_item() {
		return state_of_sector_stores_item;
	}
	public void setState_of_sector_stores_item(Integer state_of_sector_stores_item) {
		this.state_of_sector_stores_item = state_of_sector_stores_item;
	}
	public Integer getWt_results_item() {
		return wt_results_item;
	}
	public void setWt_results_item(Integer wt_results_item) {
		this.wt_results_item = wt_results_item;
	}
	public Integer getEducation_standards_item() {
		return education_standards_item;
	}
	public void setEducation_standards_item(Integer education_standards_item) {
		this.education_standards_item = education_standards_item;
	}
	public Integer getCategory_item() {
		return category_item;
	}
	public void setCategory_item(Integer category_item) {
		this.category_item = category_item;
	}
	public Integer getUp_gradation_item() {
		return up_gradation_item;
	}
	public void setUp_gradation_item(Integer up_gradation_item) {
		this.up_gradation_item = up_gradation_item;
	}
	public Integer getRegi_language_exam_item() {
		return regi_language_exam_item;
	}
	public void setRegi_language_exam_item(Integer regi_language_exam_item) {
		this.regi_language_exam_item = regi_language_exam_item;
	}
	public Integer getBpet_result_item() {
		return bpet_result_item;
	}
	public void setBpet_result_item(Integer bpet_result_item) {
		this.bpet_result_item = bpet_result_item;
	}
	public Integer getPpt_result_item() {
		return ppt_result_item;
	}
	public void setPpt_result_item(Integer ppt_result_item) {
		this.ppt_result_item = ppt_result_item;
	}
	public Integer getPromotion_exam_officers_item() {
		return promotion_exam_officers_item;
	}
	public void setPromotion_exam_officers_item(Integer promotion_exam_officers_item) {
		this.promotion_exam_officers_item = promotion_exam_officers_item;
	}
	public Integer getFinancial_grants_item() {
		return financial_grants_item;
	}
	public void setFinancial_grants_item(Integer financial_grants_item) {
		this.financial_grants_item = financial_grants_item;
	}
	public Integer getTraining_ammunition_item() {
		return training_ammunition_item;
	}
	public void setTraining_ammunition_item(Integer training_ammunition_item) {
		this.training_ammunition_item = training_ammunition_item;
	}
	public Integer getAvailability_of_ranges_item() {
		return availability_of_ranges_item;
	}
	public void setAvailability_of_ranges_item(Integer availability_of_ranges_item) {
		this.availability_of_ranges_item = availability_of_ranges_item;
	}
	public Integer getOutstanding_audit_objections_observations_item() {
		return outstanding_audit_objections_observations_item;
	}
	public void setOutstanding_audit_objections_observations_item(Integer outstanding_audit_objections_observations_item) {
		this.outstanding_audit_objections_observations_item = outstanding_audit_objections_observations_item;
	}
	public Integer getCourses_item() {
		return courses_item;
	}
	public void setCourses_item(Integer courses_item) {
		this.courses_item = courses_item;
	}

	public Integer getOutstanding_item() {
		return outstanding_item;
	}
	public void setOutstanding_item(Integer outstanding_item) {
		this.outstanding_item = outstanding_item;
	}
	public Integer getMt_accidents_item() {
		return mt_accidents_item;
	}
	public void setMt_accidents_item(Integer mt_accidents_item) {
		this.mt_accidents_item = mt_accidents_item;
	}
	public Integer getDetails_of_suicides_item() {
		return details_of_suicides_item;
	}
	public void setDetails_of_suicides_item(Integer details_of_suicides_item) {
		this.details_of_suicides_item = details_of_suicides_item;
	}
	public Integer getSecurity_lapses_item() {
		return security_lapses_item;
	}
	public void setSecurity_lapses_item(Integer security_lapses_item) {
		this.security_lapses_item = security_lapses_item;
	}
	public Integer getDetails_of_attachments_item() {
		return details_of_attachments_item;
	}
	public void setDetails_of_attachments_item(Integer details_of_attachments_item) {
		this.details_of_attachments_item = details_of_attachments_item;
	}
	public Integer getDetails_of_officers_item() {
		return details_of_officers_item;
	}
	public void setDetails_of_officers_item(Integer details_of_officers_item) {
		this.details_of_officers_item = details_of_officers_item;
	}
	public Integer getSocial_media_violation_item() {
		return social_media_violation_item;
	}
	public void setSocial_media_violation_item(Integer social_media_violation_item) {
		this.social_media_violation_item = social_media_violation_item;
	}
	public Integer getWeb_messenger_apps_item() {
		return web_messenger_apps_item;
	}
	public void setWeb_messenger_apps_item(Integer web_messenger_apps_item) {
		this.web_messenger_apps_item = web_messenger_apps_item;
	}
	public Integer getEspionage_item() {
		return espionage_item;
	}
	public void setEspionage_item(Integer espionage_item) {
		this.espionage_item = espionage_item;
	}
	public Integer getCompromise_of_cell_phone_item() {
		return compromise_of_cell_phone_item;
	}
	public void setCompromise_of_cell_phone_item(Integer compromise_of_cell_phone_item) {
		this.compromise_of_cell_phone_item = compromise_of_cell_phone_item;
	}
	public Integer getUntraceable_item() {
		return untraceable_item;
	}
	public void setUntraceable_item(Integer untraceable_item) {
		this.untraceable_item = untraceable_item;
	}
	public Integer getLoss_of_cd_item() {
		return loss_of_cd_item;
	}
	public void setLoss_of_cd_item(Integer loss_of_cd_item) {
		this.loss_of_cd_item = loss_of_cd_item;
	}
	public Integer getLoss_of_identity_item() {
		return loss_of_identity_item;
	}
	public void setLoss_of_identity_item(Integer loss_of_identity_item) {
		this.loss_of_identity_item = loss_of_identity_item;
	}
	public Integer getLand_item() {
		return land_item;
	}
	public void setLand_item(Integer land_item) {
		this.land_item = land_item;
	}
	public Integer getSummary_of_report_last_five_year_item() {
		return summary_of_report_last_five_year_item;
	}
	public void setSummary_of_report_last_five_year_item(Integer summary_of_report_last_five_year_item) {
		this.summary_of_report_last_five_year_item = summary_of_report_last_five_year_item;
	}
	public Integer getStatus() {
		return status;
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
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEstablishment_item() {
		return establishment_item;
	}
	public void setEstablishment_item(Integer establishment_item) {
		this.establishment_item = establishment_item;
	}
	public Integer getSummarybtn() {
		return summarybtn;
	}
	public void setSummarybtn(Integer summarybtn) {
		this.summarybtn = summarybtn;
	}
	public Integer getRecruit_training_b_appendix_item() {
		return recruit_training_b_appendix_item;
	}
	public void setRecruit_training_b_appendix_item(Integer recruit_training_b_appendix_item) {
		this.recruit_training_b_appendix_item = recruit_training_b_appendix_item;
	}
	public Integer getDeffi_exp_resp_to_trainng_item() {
		return deffi_exp_resp_to_trainng_item;
	}
	public void setDeffi_exp_resp_to_trainng_item(Integer deffi_exp_resp_to_trainng_item) {
		this.deffi_exp_resp_to_trainng_item = deffi_exp_resp_to_trainng_item;
	}
	public Integer getEmp_of_unit_during_period_item() {
		return emp_of_unit_during_period_item;
	}
	public void setEmp_of_unit_during_period_item(Integer emp_of_unit_during_period_item) {
		this.emp_of_unit_during_period_item = emp_of_unit_during_period_item;
	}
	public Integer getRegt_funds_item() {
		return regt_funds_item;
	}
	public void setRegt_funds_item(Integer regt_funds_item) {
		this.regt_funds_item = regt_funds_item;
	}



	
}
