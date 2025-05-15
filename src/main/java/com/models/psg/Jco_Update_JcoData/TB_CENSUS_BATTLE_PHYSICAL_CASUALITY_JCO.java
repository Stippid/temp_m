package com.models.psg.Jco_Update_JcoData;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_psg_census_battle_physical_casuality_jco", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_CENSUS_BATTLE_PHYSICAL_CASUALITY_JCO {

      private int id;
      private String authority;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_authority;
      private int jco_id;
      private String classification_of_casuality;
      private String nature_of_casuality;
      private String name_of_operation;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_casuality;
      private String cause_of_casuality;
      private String exact_place;
      private String whether_on;
      private String unit;
      private String bde;
      private String div_subarea;
      private String corps_area;
      private String command;
      private String state;
      private String district;
      private String tehsil;
      private String village;
      private int pin;
      private int status;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_on;
      private String modify_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modify_on;
      private String diagnosis;
      private String country;
      private String description;
      private String desc_value;
      private String desc_others;
      private String country_other;
      private String state_other;
      private String district_other;
      private String tehsil_other;
      private int cancel_status= -1;
      private String cancel_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date cancel_date;
      private String missing_desc;
      private String onduty;
      private String onduityother;
      private String mission_expedition;
      private String area_post_town;
      private String sector_bde;
      private String sector;
      private String field_services;
      private String hospital_name;
      private String hospital_location;
      private String circumstances;
      private String diagnosis_others;
      private String aid_to_civ;
      private String nok_informed;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date date_of_informing;
      private String methodofinforming;
      private String cause_of_casuality_1;
      private String cause_of_casuality_2;
      private String cause_of_casuality_3;
      private String time_of_informing;
      private String time_of_casuality;
      private String initiated_from;
      private String reject_remarks;

   

      @Id
      @GeneratedValue(strategy = IDENTITY)
      @Column(name = "id", unique = true, nullable = false)


      public int getId() {
           return id;
      }
      public void setId(int id) {
	  this.id = id;
      }
      public String getAuthority() {
           return authority;
      }
      public void setAuthority(String authority) {
	  this.authority = authority;
      }
      public Date getDate_of_authority() {
           return date_of_authority;
      }
      public void setDate_of_authority(Date date_of_authority) {
	  this.date_of_authority = date_of_authority;
      }
      public int getJco_id() {
           return jco_id;
      }
      public void setJco_id(int jco_id) {
	  this.jco_id = jco_id;
      }
      public String getClassification_of_casuality() {
           return classification_of_casuality;
      }
      public void setClassification_of_casuality(String classification_of_casuality) {
	  this.classification_of_casuality = classification_of_casuality;
      }
      public String getNature_of_casuality() {
           return nature_of_casuality;
      }
      public void setNature_of_casuality(String nature_of_casuality) {
	  this.nature_of_casuality = nature_of_casuality;
      }
      public String getName_of_operation() {
           return name_of_operation;
      }
      public void setName_of_operation(String name_of_operation) {
	  this.name_of_operation = name_of_operation;
      }
      public Date getDate_of_casuality() {
           return date_of_casuality;
      }
      public void setDate_of_casuality(Date date_of_casuality) {
	  this.date_of_casuality = date_of_casuality;
      }
      public String getCause_of_casuality() {
           return cause_of_casuality;
      }
      public void setCause_of_casuality(String cause_of_casuality) {
	  this.cause_of_casuality = cause_of_casuality;
      }
      public String getExact_place() {
           return exact_place;
      }
      public void setExact_place(String exact_place) {
	  this.exact_place = exact_place;
      }
      public String getWhether_on() {
           return whether_on;
      }
      public void setWhether_on(String whether_on) {
	  this.whether_on = whether_on;
      }
      public String getUnit() {
           return unit;
      }
      public void setUnit(String unit) {
	  this.unit = unit;
      }
      public String getBde() {
           return bde;
      }
      public void setBde(String bde) {
	  this.bde = bde;
      }
      public String getDiv_subarea() {
           return div_subarea;
      }
      public void setDiv_subarea(String div_subarea) {
	  this.div_subarea = div_subarea;
      }
      public String getCorps_area() {
           return corps_area;
      }
      public void setCorps_area(String corps_area) {
	  this.corps_area = corps_area;
      }
      public String getCommand() {
           return command;
      }
      public void setCommand(String command) {
	  this.command = command;
      }
      public String getState() {
           return state;
      }
      public void setState(String state) {
	  this.state = state;
      }
      public String getDistrict() {
           return district;
      }
      public void setDistrict(String district) {
	  this.district = district;
      }
      public String getTehsil() {
           return tehsil;
      }
      public void setTehsil(String tehsil) {
	  this.tehsil = tehsil;
      }
      public String getVillage() {
           return village;
      }
      public void setVillage(String village) {
	  this.village = village;
      }
      public int getPin() {
           return pin;
      }
      public void setPin(int pin) {
	  this.pin = pin;
      }
      public int getStatus() {
           return status;
      }
      public void setStatus(int status) {
	  this.status = status;
      }
      public String getCreated_by() {
           return created_by;
      }
      public void setCreated_by(String created_by) {
	  this.created_by = created_by;
      }
      public Date getCreated_on() {
           return created_on;
      }
      public void setCreated_on(Date created_on) {
	  this.created_on = created_on;
      }
      public String getModify_by() {
           return modify_by;
      }
      public void setModify_by(String modify_by) {
	  this.modify_by = modify_by;
      }
      public Date getModify_on() {
           return modify_on;
      }
      public void setModify_on(Date modify_on) {
	  this.modify_on = modify_on;
      }
      public String getDiagnosis() {
           return diagnosis;
      }
      public void setDiagnosis(String diagnosis) {
	  this.diagnosis = diagnosis;
      }
      public String getCountry() {
           return country;
      }
      public void setCountry(String country) {
	  this.country = country;
      }
      public String getDescription() {
           return description;
      }
      public void setDescription(String description) {
	  this.description = description;
      }
      public String getDesc_value() {
           return desc_value;
      }
      public void setDesc_value(String desc_value) {
	  this.desc_value = desc_value;
      }
      public String getDesc_others() {
           return desc_others;
      }
      public void setDesc_others(String desc_others) {
	  this.desc_others = desc_others;
      }
      public String getCountry_other() {
           return country_other;
      }
      public void setCountry_other(String country_other) {
	  this.country_other = country_other;
      }
      public String getState_other() {
           return state_other;
      }
      public void setState_other(String state_other) {
	  this.state_other = state_other;
      }
      public String getDistrict_other() {
           return district_other;
      }
      public void setDistrict_other(String district_other) {
	  this.district_other = district_other;
      }
      public String getTehsil_other() {
           return tehsil_other;
      }
      public void setTehsil_other(String tehsil_other) {
	  this.tehsil_other = tehsil_other;
      }
      public int getCancel_status() {
           return cancel_status;
      }
      public void setCancel_status(int cancel_status) {
	  this.cancel_status = cancel_status;
      }
      public String getCancel_by() {
           return cancel_by;
      }
      public void setCancel_by(String cancel_by) {
	  this.cancel_by = cancel_by;
      }
      public Date getCancel_date() {
           return cancel_date;
      }
      public void setCancel_date(Date cancel_date) {
	  this.cancel_date = cancel_date;
      }
      public String getMissing_desc() {
           return missing_desc;
      }
      public void setMissing_desc(String missing_desc) {
	  this.missing_desc = missing_desc;
      }
      public String getOnduty() {
           return onduty;
      }
      public void setOnduty(String onduty) {
	  this.onduty = onduty;
      }
      public String getOnduityother() {
           return onduityother;
      }
      public void setOnduityother(String onduityother) {
	  this.onduityother = onduityother;
      }
      public String getMission_expedition() {
           return mission_expedition;
      }
      public void setMission_expedition(String mission_expedition) {
	  this.mission_expedition = mission_expedition;
      }
      public String getArea_post_town() {
           return area_post_town;
      }
      public void setArea_post_town(String area_post_town) {
	  this.area_post_town = area_post_town;
      }
      public String getSector_bde() {
           return sector_bde;
      }
      public void setSector_bde(String sector_bde) {
	  this.sector_bde = sector_bde;
      }
      public String getSector() {
           return sector;
      }
      public void setSector(String sector) {
	  this.sector = sector;
      }
      public String getField_services() {
           return field_services;
      }
      public void setField_services(String field_services) {
	  this.field_services = field_services;
      }
      public String getHospital_name() {
           return hospital_name;
      }
      public void setHospital_name(String hospital_name) {
	  this.hospital_name = hospital_name;
      }
      public String getHospital_location() {
           return hospital_location;
      }
      public void setHospital_location(String hospital_location) {
	  this.hospital_location = hospital_location;
      }
      public String getCircumstances() {
           return circumstances;
      }
      public void setCircumstances(String circumstances) {
	  this.circumstances = circumstances;
      }
      public String getDiagnosis_others() {
           return diagnosis_others;
      }
      public void setDiagnosis_others(String diagnosis_others) {
	  this.diagnosis_others = diagnosis_others;
      }
      public String getAid_to_civ() {
           return aid_to_civ;
      }
      public void setAid_to_civ(String aid_to_civ) {
	  this.aid_to_civ = aid_to_civ;
      }
      public String getNok_informed() {
           return nok_informed;
      }
      public void setNok_informed(String nok_informed) {
	  this.nok_informed = nok_informed;
      }
      public Date getDate_of_informing() {
           return date_of_informing;
      }
      public void setDate_of_informing(Date date_of_informing) {
	  this.date_of_informing = date_of_informing;
      }
      public String getMethodofinforming() {
           return methodofinforming;
      }
      public void setMethodofinforming(String methodofinforming) {
	  this.methodofinforming = methodofinforming;
      }
      public String getCause_of_casuality_1() {
           return cause_of_casuality_1;
      }
      public void setCause_of_casuality_1(String cause_of_casuality_1) {
	  this.cause_of_casuality_1 = cause_of_casuality_1;
      }
      public String getCause_of_casuality_2() {
           return cause_of_casuality_2;
      }
      public void setCause_of_casuality_2(String cause_of_casuality_2) {
	  this.cause_of_casuality_2 = cause_of_casuality_2;
      }
      public String getCause_of_casuality_3() {
           return cause_of_casuality_3;
      }
      public void setCause_of_casuality_3(String cause_of_casuality_3) {
	  this.cause_of_casuality_3 = cause_of_casuality_3;
      }
      public String getTime_of_informing() {
           return time_of_informing;
      }
      public void setTime_of_informing(String time_of_informing) {
	  this.time_of_informing = time_of_informing;
      }
      public String getTime_of_casuality() {
           return time_of_casuality;
      }
      public void setTime_of_casuality(String time_of_casuality) {
	  this.time_of_casuality = time_of_casuality;
      }
      public String getInitiated_from() {
           return initiated_from;
      }
      public void setInitiated_from(String initiated_from) {
	  this.initiated_from = initiated_from;
      }
      public String getReject_remarks() {
			return reject_remarks;
		}
		public void setReject_remarks(String reject_remarks) {
			this.reject_remarks = reject_remarks;
		}



}
