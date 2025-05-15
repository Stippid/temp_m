package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_miso_orbat_relief_prgm", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})

public class Miso_Orbat_Relief_Prgm {

	
	 private int id;
	 private String auth_let_no;
	 
	 @NotNull
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 private String date1;
	 private String sus_no;
	// private String unit_name;
	 private String imdt_fmn;
	 private String arm_name;
	 private String mode_of_tpt;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private String nmb_date;
	 private String indn_de_indn_pd;
	 private String loc;
	 //private String nrs;
	 private String typ_of_stn;
	 private String typ_of_terrain;
	 private String mov_of_adv_party_dt;
	 private String rplc_by_unit_sus_no;
	//private String rplc_by_unit_name;
	 private String upload_document;
	 private String period_from;
	 private String period_to;
	 private String nrs_code;
	 private String sd_status;
	 private String sd_created_by;
	 private String sd_created_on;
	 private String sd_updated_on;
	 private String sd_updated_by;
	 private String approved_by_sd;
	 private String approved_on_sd;
	 private String rejected_by_sd;
	 private String rejected_on_sd;
	    
	 private String miso_status;
	 private String miso_approved_on;
	 private String miso_approved_by;

	// added by satya	 
	 private String relief_yes_no;
	 private String remarks;
	 private String type_of_cl;
	 private String ser_no;
		// added by satya	 
	
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		@Column(name = "auth_let_no", nullable = false)
		public String getAuth_let_no() {
			return auth_let_no;
		}
		public void setAuth_let_no(String auth_let_no) {
			this.auth_let_no = auth_let_no;
		}
		
		@Column(name = "date1",  nullable = false)
		public String getDate1() {
			return date1;
		}
		public void setDate1(String date1) {
			this.date1 = date1;
		}
		
		@Column(name = "sus_no", nullable = false)
		public String getSus_no() {
			return sus_no;
		}
		public void setSus_no(String sus_no) {
			this.sus_no = sus_no;
		}
		
		
		/*@Column(name = "unit_name",  nullable = false)
		public String getUnit_name() {
			return unit_name;
		}
		public void setUnit_name(String unit_name) {
			this.unit_name = unit_name;
		}*/
		@Column(name = "imdt_fmn",  nullable = false)
		public String getImdt_fmn() {
			return imdt_fmn;
		}
		public void setImdt_fmn(String imdt_fmn) {
			this.imdt_fmn = imdt_fmn;
		}
		
		@Column(name = "arm_name",  nullable = false)
		public String getArm_name() {
			return arm_name;
		}
		public void setArm_name(String arm_name) {
			this.arm_name = arm_name;
		}
		
		@Column(name = "mode_of_tpt", nullable = false)
		public String getMode_of_tpt() {
			return mode_of_tpt;
		}
		public void setMode_of_tpt(String mode_of_tpt) {
			this.mode_of_tpt = mode_of_tpt;
		}
		
		@Column(name = "nmb_date",  nullable = false)
		public String getNmb_date() {
			return nmb_date;
		}
		public void setNmb_date(String nmb_date) {
			this.nmb_date = nmb_date;
		}
		
		
		@Column(name = "indn_de_indn_pd", nullable = false)
		public String getIndn_de_indn_pd() {
			return indn_de_indn_pd;
		}
		public void setIndn_de_indn_pd(String indn_de_indn_pd) {
			this.indn_de_indn_pd = indn_de_indn_pd;
		}
		
		@Column(name = "loc", nullable = false)
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		
		/*@Column(name = "nrs",  nullable = false)
		public String getNrs() {
			return nrs;
		}
		public void setNrs(String nrs) {
			this.nrs = nrs;
		}*/
		
		@Column(name = "typ_of_stn", nullable = false)
		public String getTyp_of_stn() {
			return typ_of_stn;
		}
		public void setTyp_of_stn(String typ_of_stn) {
			this.typ_of_stn = typ_of_stn;
		}
		
		@Column(name = "typ_of_terrain", nullable = false)
		public String getTyp_of_terrain() {
			return typ_of_terrain;
		}
		public void setTyp_of_terrain(String typ_of_terrain) {
			this.typ_of_terrain = typ_of_terrain;
		}
		
		@Column(name = "mov_of_adv_party_dt", nullable = false)
		public String getMov_of_adv_party_dt() {
			return mov_of_adv_party_dt;
		}
		public void setMov_of_adv_party_dt(String mov_of_adv_party_dt) {
			this.mov_of_adv_party_dt = mov_of_adv_party_dt;
		}
		
		
		@Column(name = "rplc_by_unit_sus_no", nullable = false)
		public String getRplc_by_unit_sus_no() {
			return rplc_by_unit_sus_no;
		}
		public void setRplc_by_unit_sus_no(String rplc_by_unit_sus_no) {
			this.rplc_by_unit_sus_no = rplc_by_unit_sus_no;
		}
		
		/*@Column(name = "rplc_by_unit_name", nullable = false)
		public String getRplc_by_unit_name() {
			return rplc_by_unit_name;
		}
		public void setRplc_by_unit_name(String rplc_by_unit_name) {
			this.rplc_by_unit_name = rplc_by_unit_name;
		}*/
		
		@Column(name = "upload_document",  nullable = false)
		public String getUpload_document() {
			return upload_document;
		}
		public void setUpload_document(String upload_document) {
			this.upload_document = upload_document;
		}
		
		@Column(name = "period_from", nullable = false)
		public String getPeriod_from() {
			return period_from;
		}
		public void setPeriod_from(String period_from) {
			this.period_from = period_from;
		}
		
		@Column(name = "period_to", nullable = false)
		public String getPeriod_to() {
			return period_to;
		}
		public void setPeriod_to(String period_to) {
			this.period_to = period_to;
		}
		
		public String getSd_status() {
			return sd_status;
		}
		public void setSd_status(String sd_status) {
			this.sd_status = sd_status;
		}
			
		public String getNrs_code() {
			return nrs_code;
		}
		public void setNrs_code(String nrs_code) {
			this.nrs_code = nrs_code;
		}
		
		public String getSd_created_on() {
			return sd_created_on;
		}
		public void setSd_created_on(String sd_created_on) {
			this.sd_created_on = sd_created_on;
		}
		public String getMiso_status() {
			return miso_status;
		}
		public void setMiso_status(String miso_status) {
			this.miso_status = miso_status;
		}
		public String getMiso_approved_on() {
			return miso_approved_on;
		}
		public void setMiso_approved_on(String miso_approved_on) {
			this.miso_approved_on = miso_approved_on;
		}
		public String getMiso_approved_by() {
			return miso_approved_by;
		}
		public void setMiso_approved_by(String miso_approved_by) {
			this.miso_approved_by = miso_approved_by;
		}
//==============Main Body Move ================================
		
		private String unit_auth_letter_no;
		private String unit_auth_letter_date;
		private String dep_date;
		private String arr_date; 
		private String arr_dep_report;
		private String unit_created_on;
		private String unit_created_by;
		private String unit_status;
		private String approved_by_unit;
		private String approved_on_unit;

		public String getSd_created_by() {
			return sd_created_by;
		}
		public void setSd_created_by(String sd_created_by) {
			this.sd_created_by = sd_created_by;
		}

		
		public String getDep_date() {
			return dep_date;
		}

		public void setDep_date(String dep_date) {
			this.dep_date = dep_date;
		}

		public String getArr_date() {
			return arr_date;
		}

		public void setArr_date(String arr_date) {
			this.arr_date = arr_date;
		}

		public String getArr_dep_report() {
			return arr_dep_report;
		}

		public void setArr_dep_report(String arr_dep_report) {
			this.arr_dep_report = arr_dep_report;
		}

		public String getUnit_created_on() {
			return unit_created_on;
		}

		public void setUnit_created_on(String unit_created_on) {
			this.unit_created_on = unit_created_on;
		}

		public String getUnit_created_by() {
			return unit_created_by;
		}
		public void setUnit_created_by(String unit_created_by) {
			this.unit_created_by = unit_created_by;
		}
		public String getUnit_status() {
			return unit_status;
		}

		public void setUnit_status(String unit_status) {
			this.unit_status = unit_status;
		}
		public String getSd_updated_on() {
			return sd_updated_on;
		}
		public void setSd_updated_on(String sd_updated_on) {
			this.sd_updated_on = sd_updated_on;
		}
		public String getSd_updated_by() {
			return sd_updated_by;
		}
		public void setSd_updated_by(String sd_updated_by) {
			this.sd_updated_by = sd_updated_by;
		}
		public String getApproved_by_sd() {
			return approved_by_sd;
		}
		public void setApproved_by_sd(String approved_by_sd) {
			this.approved_by_sd = approved_by_sd;
		}
		public String getApproved_on_sd() {
			return approved_on_sd;
		}
		public void setApproved_on_sd(String approved_on_sd) {
			this.approved_on_sd = approved_on_sd;
		}
		public String getApproved_by_unit() {
			return approved_by_unit;
		}
		public void setApproved_by_unit(String approved_by_unit) {
			this.approved_by_unit = approved_by_unit;
		}
		public String getApproved_on_unit() {
			return approved_on_unit;
		}
		public void setApproved_on_unit(String approved_on_unit) {
			this.approved_on_unit = approved_on_unit;
		}
		public String getUnit_auth_letter_no() {
			return unit_auth_letter_no;
		}
		public void setUnit_auth_letter_no(String unit_auth_letter_no) {
			this.unit_auth_letter_no = unit_auth_letter_no;
		}
		public String getUnit_auth_letter_date() {
			return unit_auth_letter_date;
		}
		public void setUnit_auth_letter_date(String unit_auth_letter_date) {
			this.unit_auth_letter_date = unit_auth_letter_date;
		}
		 
		 
		public String getRejected_by_sd() {
			return rejected_by_sd;
		}
		public void setRejected_by_sd(String rejected_by_sd) {
			this.rejected_by_sd = rejected_by_sd;
		}
		public String getRejected_on_sd() {
			return rejected_on_sd;
		}
		public void setRejected_on_sd(String rejected_on_sd) {
			this.rejected_on_sd = rejected_on_sd;
		}
		
		// added by satya
		public String getRelief_yes_no() {
			return relief_yes_no;
		}
		public void setRelief_yes_no(String relief_yes_no) {
			this.relief_yes_no = relief_yes_no;
		}
		public String getRemarks() { 
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getType_of_cl() {
			return type_of_cl; 
		}
		public void setType_of_cl(String type_of_cl) {
			this.type_of_cl = type_of_cl;
		}
		public String getSer_no() {
			return ser_no;
		}
		public void setSer_no(String ser_no) {
			this.ser_no = ser_no;
		}
		// added by satya
}
