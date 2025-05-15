package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_tms_mct_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_TMS_MCT_MASTER {
	
	private int id;
	private BigInteger mct;
	private String std_nomclature;
	//private int prf_group1;
	private String prf_group;
	private String type_of_vehicle;
	private String category_of_vehicle;
	private String purpose_of_vehicle;
	private String veh_class_code;
	private String auth_letter_no;
	private String status;
	private int no_of_wheels;
	private int no_of_axles;
	private String axle_wts;
	private String drive;
	private int towing_capacity;
	private int tonnage;
	private int lift_capacity;
	private String type_fuel;
	private String sponsoring_dte;
	private String creation_by;
	private Date creation_date;
	private String modify_by;
	private Date modify_date;
	
	private int yr_of_svc_for_discard;
	private int kms_run_for_discard;
	
	private int yr_of_service_nff;
	private int kms_run_for_discard_nff;


	private String wheel_track ;
	private String no_of_bogie_wheel ;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigInteger getMct() {
		return mct;
	}
	public void setMct(BigInteger mct) {
		this.mct = mct;
	}
	public String getStd_nomclature() {
		return std_nomclature;
	}
	public void setStd_nomclature(String std_nomclature) {
		this.std_nomclature = std_nomclature;
	}
	public String getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}
	public String getType_of_vehicle() {
		return type_of_vehicle;
	}
	public void setType_of_vehicle(String type_of_vehicle) {
		this.type_of_vehicle = type_of_vehicle;
	}
	public String getCategory_of_vehicle() {
		return category_of_vehicle;
	}
	public void setCategory_of_vehicle(String category_of_vehicle) {
		this.category_of_vehicle = category_of_vehicle;
	}
	public String getPurpose_of_vehicle() {
		return purpose_of_vehicle;
	}
	public void setPurpose_of_vehicle(String purpose_of_vehicle) {
		this.purpose_of_vehicle = purpose_of_vehicle;
	}
	public String getVeh_class_code() {
		return veh_class_code;
	}
	public void setVeh_class_code(String veh_class_code) {
		this.veh_class_code = veh_class_code;
	}
	public String getAuth_letter_no() {
		return auth_letter_no;
	}
	public void setAuth_letter_no(String auth_letter_no) {
		this.auth_letter_no = auth_letter_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
		

	public int getNo_of_wheels() {
		return no_of_wheels;
	}
	public void setNo_of_wheels(int no_of_wheels) {
		this.no_of_wheels = no_of_wheels;
	}
	public int getNo_of_axles() {
		return no_of_axles;
	}
	public void setNo_of_axles(int no_of_axles) {
		this.no_of_axles = no_of_axles;
	}
	public String getAxle_wts() {
		return axle_wts;
	}
	public void setAxle_wts(String axle_wts) {
		this.axle_wts = axle_wts;
	}
	public String getDrive() {
		return drive;
	}
	public void setDrive(String drive) {
		this.drive = drive;
	}
	public int getTowing_capacity() {
		return towing_capacity;
	}
	public void setTowing_capacity(int towing_capacity) {
		this.towing_capacity = towing_capacity;
	}
	public int getTonnage() {
		return tonnage;
	}
	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}
	public int getLift_capacity() {
		return lift_capacity;
	}
	public void setLift_capacity(int lift_capacity) {
		this.lift_capacity = lift_capacity;
	}
	
	public String getType_fuel() {
		return type_fuel;
	}
	public void setType_fuel(String type_fuel) {
		this.type_fuel = type_fuel;
	}
	
	
	public String getSponsoring_dte() {
		return sponsoring_dte;
	}
	public void setSponsoring_dte(String sponsoring_dte) {
		this.sponsoring_dte = sponsoring_dte;
	}
	public String getModify_by() {
		return modify_by;
	}
	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	
	public int getYr_of_svc_for_discard() {
		return yr_of_svc_for_discard;
	}
	public void setYr_of_svc_for_discard(int yr_of_svc_for_discard) {
		this.yr_of_svc_for_discard = yr_of_svc_for_discard;
	}
	
	public int getKms_run_for_discard() {
		return kms_run_for_discard;
	}
	public void setKms_run_for_discard(int kms_run_for_discard) {
		this.kms_run_for_discard = kms_run_for_discard;
	}
	
	public int getYr_of_service_nff() {
		return yr_of_service_nff;
	}
	public void setYr_of_service_nff(int yr_of_service_nff) {
		this.yr_of_service_nff = yr_of_service_nff;
	}
	public int getKms_run_for_discard_nff() {
		return kms_run_for_discard_nff;
	}
	public void setKms_run_for_discard_nff(int kms_run_for_discard_nff) {
		this.kms_run_for_discard_nff = kms_run_for_discard_nff;
	}
	public String getWheel_track() {
		return wheel_track;
	}
	public void setWheel_track(String wheel_track) {
		this.wheel_track = wheel_track;
	}
	public String getNo_of_bogie_wheel() {
		return no_of_bogie_wheel;
	}
	public void setNo_of_bogie_wheel(String no_of_bogie_wheel) {
		this.no_of_bogie_wheel = no_of_bogie_wheel;
	}
	
	/*public int getPrf_group1() {
		return prf_group1;
	}
	public void setPrf_group1(int prf_group1) {
		this.prf_group1 = prf_group1;
	}*/
}
