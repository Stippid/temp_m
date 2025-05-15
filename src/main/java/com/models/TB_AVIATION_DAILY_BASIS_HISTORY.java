package com.models;
//05-12-24
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_aviation_daily_basis_history", uniqueConstraints = {@UniqueConstraint(columnNames = "id") })
public class TB_AVIATION_DAILY_BASIS_HISTORY {
	
	 
		private Integer id;
		private String authority;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date date_of_authority;	
		private String sus_no;
		private String acc_no;
		private String status;
		private String falf_hrs_day;
		private String falf_hrs_night;
		private String g_run;
		private String af_hrs;
		private String created_by;
		private Date created_date;
		private String modified_by;
		private Date modified_date;
		private String eng_hrs_left;
		private String eng_hrs_rigth;
		private String lh_ser_no;
		private String rh_ser_no;
		private String hrs_left;
		private String next_insp;
		private String hrs_monthly;
		private String hrs_qtrly;
		private String hrs_half_year;
		private String hrs_qtrly_flow;
		private String remarks;
		private String bal_hrs;
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private Date date_goi_letter;
		private Date date_of_pdc;
		private Date ason_dt;
		private String status1;
		private Date approved_dt;
		private String approved_by;
		private Integer dacr_id;
		private String loc_code;
		private String aircraft_state;

		
		
		public Integer getDacr_id() {
			return dacr_id;
		}
		public void setDacr_id(Integer dacr_id) {
			this.dacr_id = dacr_id;
		}
		
		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
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
		public String getSus_no() {
			return sus_no;
		}
		public void setSus_no(String sus_no) {
			this.sus_no = sus_no;
		}
		public String getAcc_no() {
			return acc_no;
		}
		public void setAcc_no(String acc_no) {
			this.acc_no = acc_no;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getFalf_hrs_day() {
			return falf_hrs_day;
		}
		public void setFalf_hrs_day(String falf_hrs_day) {
			this.falf_hrs_day = falf_hrs_day;
		}
		public String getFalf_hrs_night() {
			return falf_hrs_night;
		}
		public void setFalf_hrs_night(String falf_hrs_night) {
			this.falf_hrs_night = falf_hrs_night;
		}
		public String getG_run() {
			return g_run;
		}
		public void setG_run(String g_run) {
			this.g_run = g_run;
		}
		public String getAf_hrs() {
			return af_hrs;
		}
		public void setAf_hrs(String af_hrs) {
			this.af_hrs = af_hrs;
		}
		public String getCreated_by() {
			return created_by;
		}
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}
		public String getModified_by() {
			return modified_by;
		}
		public void setModified_by(String modified_by) {
			this.modified_by = modified_by;
		}
		public Date getModified_date() {
			return modified_date;
		}
		public void setModified_date(Date modified_date) {
			this.modified_date = modified_date;
		}
		public String getEng_hrs_left() {
			return eng_hrs_left;
		}
		public void setEng_hrs_left(String eng_hrs_left) {
			this.eng_hrs_left = eng_hrs_left;
		}
		public String getEng_hrs_rigth() {
			return eng_hrs_rigth;
		}
		public void setEng_hrs_rigth(String eng_hrs_rigth) {
			this.eng_hrs_rigth = eng_hrs_rigth;
		}
		public String getHrs_left() {
			return hrs_left;
		}
		public void setHrs_left(String hrs_left) {
			this.hrs_left = hrs_left;
		}
		public String getNext_insp() {
			return next_insp;
		}
		public void setNext_insp(String next_insp) {
			this.next_insp = next_insp;
		}
		public String getHrs_monthly() {
			return hrs_monthly;
		}
		public void setHrs_monthly(String hrs_monthly) {
			this.hrs_monthly = hrs_monthly;
		}
		public String getHrs_qtrly() {
			return hrs_qtrly;
		}
		public void setHrs_qtrly(String hrs_qtrly) {
			this.hrs_qtrly = hrs_qtrly;
		}
		public String getHrs_half_year() {
			return hrs_half_year;
		}
		public void setHrs_half_year(String hrs_half_year) {
			this.hrs_half_year = hrs_half_year;
		}
		public String getHrs_qtrly_flow() {
			return hrs_qtrly_flow;
		}
		public void setHrs_qtrly_flow(String hrs_qtrly_flow) {
			this.hrs_qtrly_flow = hrs_qtrly_flow;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getBal_hrs() {
			return bal_hrs;
		}
		public void setBal_hrs(String bal_hrs) {
			this.bal_hrs = bal_hrs;
		}
		public Date getDate_goi_letter() {
			return date_goi_letter;
		}
		public void setDate_goi_letter(Date date_goi_letter) {
			this.date_goi_letter = date_goi_letter;
		}
		public String getStatus1() {
			return status1;
		}
		public void setStatus1(String status1) {
			this.status1 = status1;
		}
		public Date getApproved_dt() {
			return approved_dt;
		}
		public void setApproved_dt(Date approved_dt) {
			this.approved_dt = approved_dt;
		}
		public String getApproved_by() {
			return approved_by;
		}
		public void setApproved_by(String approved_by) {
			this.approved_by = approved_by;
		}
		public String getLh_ser_no() {
			return lh_ser_no;
		}
		public void setLh_ser_no(String lh_ser_no) {
			this.lh_ser_no = lh_ser_no;
		}
		public String getRh_ser_no() {
			return rh_ser_no;
		}
		public void setRh_ser_no(String rh_ser_no) {
			this.rh_ser_no = rh_ser_no;
		}
		public Date getDate_of_pdc() {
			return date_of_pdc;
		}
		public void setDate_of_pdc(Date date_of_pdc) {
			this.date_of_pdc = date_of_pdc;
		}
		public String getLoc_code() {
			return loc_code;
		}
		public void setLoc_code(String loc_code) {
			this.loc_code = loc_code;
		}
		public Date getAson_dt() {
			return ason_dt;
		}
		public void setAson_dt(Date ason_dt) {
			this.ason_dt = ason_dt;
		}
		public String getAircraft_state() {
			return aircraft_state;
		}
		public void setAircraft_state(String aircraft_state) {
			this.aircraft_state = aircraft_state;
		}
		
	
}
